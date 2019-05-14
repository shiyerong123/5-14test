/** 
 * <pre>项目名称:ssm-task-syr 
 * 文件名称:CommentsService.java 
 * 包名:com.jk.syr.service 
 * 创建日期:2019年3月22日下午9:16:20 
 * Copyright (c) 2019, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.service;

import com.jk.model.CommentBean;
import com.jk.model.GoodBean;
import com.jk.model.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.HashMap;




public interface CommentsService {

	HashMap<String, Object> findGoods(Integer page, Integer rows, GoodBean good);


	HashMap<String, Object> findComm(Integer page, Integer rows, CommentBean comment);

	void updateComments(CommentBean comment);

	void addComments(CommentBean comment);

	CommentBean findCommentsById(String commentId);

	String reg(User user);

	//User queryUserByName(String loginNumber);


	String updateHead(MultipartFile file, int i);

	void sendSimpleMail(String userEmail, String 注册验证码, String message);
}
