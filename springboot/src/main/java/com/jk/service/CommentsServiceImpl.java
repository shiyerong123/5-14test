/** 
 * <pre>项目名称:ssm-task-syr 
 * 文件名称:CommentsServiceImpl.java 
 * 包名:com.jk.syr.service 
 * 创建日期:2019年3月22日下午9:16:50 
 * Copyright (c) 2019, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;



import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.jk.mapper.CommentsMapper;
import com.jk.model.CommentBean;
import com.jk.model.GoodBean;
import com.jk.model.User;

import com.jk.utils.OSSClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;


@Service
public class CommentsServiceImpl implements CommentsService {
      @Autowired
      private MongoTemplate mongoTemplate;
      @Autowired
	  private CommentsMapper commentsMapper;



      @Autowired
	  private RedisTemplate redisTemplate;
      @Reference
	  private UserService userService;
	@Autowired
	private OSSClientUtil OSSClientUtil;


	@Override
	public HashMap<String, Object> findGoods(Integer page, Integer rows, GoodBean good) {
		HashMap<String, Object> hashMap = new HashMap<>();
		if (redisTemplate.hasKey("queryGood"+page)){
			System.out.println("ertyui");
			hashMap = (HashMap<String, Object>) redisTemplate.opsForValue().get("queryBook"+page);
		}else {
			Query query = new Query();
			long count = mongoTemplate.count(query, GoodBean.class);
			query.skip((page - 1) * rows).limit(rows);
			List<GoodBean> findGood = mongoTemplate.find(query, GoodBean.class);
			for (GoodBean goodBean : findGood) {
				String goodId = goodBean.getGoodId();
				Query query2 = new Query();
				query2.addCriteria(Criteria.where("goodsId").is(goodId));
				long count2 = mongoTemplate.count(query2, CommentBean.class);
				goodBean.setCount(count);
			}
			hashMap.put("total", count);
			hashMap.put("rows", findGood);
			redisTemplate.opsForValue().set("queryGood"+page,hashMap);
		}
		return hashMap;
	}

	@Override
	public HashMap<String, Object> findComm(Integer page, Integer rows, CommentBean comment) {
		Query query = new Query();
		if(comment.getGoodsId()!=null) {
			query.addCriteria(Criteria.where("goodsId").is(comment.getGoodsId()));
		}
		//评论内容模糊搜索
		if(StringUtils.isNotEmpty(comment.getComments())) {
			query.addCriteria(Criteria.where("comments").regex(comment.getComments()));
			//query.addCriteria(Criteria.where("comments").regex(comment.getComments()+"$", "i"));
		}
		//评论时间区间搜索
		if(comment.getStartDate()!=null && comment.getEndDate()!=null) {
			query.addCriteria(Criteria.where("commentsDate").gte(comment.getStartDate()).lte(comment.getEndDate()));
		}
		long count = mongoTemplate.count(query, CommentBean.class);
		query.skip((page-1)*rows).limit(rows);
		List<CommentBean> findComm = mongoTemplate.find(query, CommentBean.class);
		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("total", count);
		hashMap.put("rows", findComm);
		return hashMap;
	}

	@Override
	public void updateComments(CommentBean comment) {
		Query query = new Query();
		Update update = new Update();
		update.set("comments", comment.getComments());
		update.set("commentsName", comment.getCommentsName());
		update.set("commentsLevel", comment.getCommentsLevel());
		update.set("commentsStars", comment.getCommentsStars());
		//comment.setCommentsDate(new Date());
		mongoTemplate.updateFirst(new Query().addCriteria(Criteria.where("commentId").is(comment.getCommentId())), update, CommentBean.class);
	}

	@Override
	public void addComments(CommentBean comment) {
		comment.setCommentId(UUID.randomUUID().toString());
		comment.setCommentsDate(new Date());
		mongoTemplate.save(comment);

	}

	@Override
	public CommentBean findCommentsById(String commentId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("commentId").is(commentId));
		List<CommentBean> find = mongoTemplate.find(query, CommentBean.class);
		//return 	mongoTemplate.findOne(new Query().addCriteria(Criteria.where("commentId").is(commentId)), CommentBean.class);
		return find.get(0);
	}

	@Override
	public String reg(User user) {
		return userService.reg(user);
	}

	/*@Override
	public User queryUserByName(String loginNumber) {
		return commentsMapper.queryUserByName(loginNumber);
	}*/



	@Override
	public String updateHead(MultipartFile file, int i) {
		if(file ==null || file.getSize()<=0){
			System.out.println("图片不能为空");
		}
		String name=OSSClientUtil.uploadImg2Oss(file);
		String imgUrl = OSSClientUtil.getImgUrl(name);
		return imgUrl;
	}
	@Resource
	private JavaMailSender mailSender;
	@Value("${spring.mail.username}")
	private String from;

	Logger logger = LoggerFactory.getLogger(this.getClass());
	//发送普通邮件

	@Override
	public void sendSimpleMail(String to, String title, String content) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(title);
		message.setText(content);
		mailSender.send(message);
		logger.info("邮件发送成功");
	}
}
