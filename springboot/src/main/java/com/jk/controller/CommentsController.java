
package com.jk.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

import com.aliyun.oss.model.OSSObject;
import com.jk.model.CommentBean;
import com.jk.model.GoodBean;
import com.jk.model.User;
import com.jk.service.CommentsService;


import com.jk.utils.OssFileUtils;
import com.mysql.jdbc.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class CommentsController {
     @Autowired
     private CommentsService commentsService;
    //发送验证码的  验证码存储在redis中设置三分钟过去时间
    @RequestMapping("getCheckCode")
    @ResponseBody
    public String getCheckCode(String userEmail, HttpServletRequest HttpServletRequest){
        String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
        String message = "商品用户注册验证码为："+checkCode+"验证码时效为三分钟";
        try {
            commentsService.sendSimpleMail(userEmail, "注册验证码", message);
        }catch (Exception e){
            return "";
        }
        HttpServletRequest.getSession().setAttribute("checkCode",checkCode);
        return checkCode;
    }

    @RequestMapping(value = "/headImgUpload.json", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> headImg(HttpServletRequest request , MultipartFile file){
        Map<String, Object> value = new HashMap<String, Object>();
        value.put("success", true);
        value.put("errorCode", 0);
        value.put("errorMsg", "");
        String head = commentsService.updateHead(file, 4);//此处是调用上传服务接口，4是需要更新的userId 测试数据。
        System.out.println(head);
        value.put("data", head);
        /**
         * 赋值进去
         */
        //files.setFileUrl(head);
        //  orderServiceFegin.addFile(files);
        return value;
    }


    @RequestMapping(value="findSmsCode",produces="application/json;charset=utf-8")
    @ResponseBody
    public String findSmsCode(String loginNumber, HttpSession session){
        Integer number= (int)(Math.random()*899999+100000);
        System.out.println(number);
        session.setAttribute("smsCode",number);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("mobile",loginNumber));
        params.add(new BasicNameValuePair("tpl_id","155810"));
        params.add(new BasicNameValuePair("tpl_value","%23code%23%3d"+number));
        params.add(new BasicNameValuePair("key","1a6e42a5f69804d4579e67930da79164"));
        UrlEncodedFormEntity Entity=null;
        try {
            Entity=new UrlEncodedFormEntity(params, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        HttpPost post = new HttpPost("http://v.juhe.cn/sms/send");
        post.setEntity(Entity);
        post.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1;"
                + " Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0");
        post.setHeader("Accept", "application/json");

        post.setHeader("Accept-Encoding", "gzip, deflate");
        post.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(30000)  		//设置链接超时的时间1秒钟
                .setSocketTimeout(30000) 		//设置读取超时1秒钟
                .build(); 						//RequestConfig静态方法  setProxy  设置代理



        post.setConfig(config);
        CloseableHttpResponse response=null;
        String jsonstr="";
        try {
            response=httpClient.execute(post);
            jsonstr= EntityUtils.toString(response.getEntity(),"UTF-8");
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            try {
                httpClient.close();
                response.close();
                post.abort();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return jsonstr;
    }
    //注册
    @RequestMapping("reg")
    @ResponseBody
    public String reg(User user, HttpSession session){
        String smsCode = session.getAttribute("smsCode").toString();
        if(smsCode.equals(user.getSmsCode())){
            return commentsService.reg(user);
        }else{
            return "失败";
        }
    }
    //登录
    @RequestMapping("login")
    public String login(HttpServletRequest request, ModelMap map){
        //认证器会根据对应的错误 返回对应的异常 根据异常判断对应错误
        String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");

        if(UnknownAccountException.class.getName().equals(exceptionClassName)
                || AuthenticationException.class.getName().equals(exceptionClassName)){
            System.out.println("request = [ 用户名异常]");
            map.put("message","用户名错误");
        }else{
            System.out.println("request = [密码异常]");
            map.put("message","密码错误");
        }
        return "login";
    }
    @RequestMapping("toLogin")
    public String toLogin(){
        return "login";
    }

     @RequestMapping("quanbu")
     public String quanbu(String url){
          return url;
     }
     @RequestMapping("findGood")
     @ResponseBody
     public HashMap<String, Object> findGood(Integer page, Integer rows, GoodBean good){
    	 return commentsService.findGoods(page,rows,good);
     }

    @RequestMapping("findComm")
    @ResponseBody
    public HashMap<String, Object> findComm(Integer page, Integer rows, CommentBean comment){
        return commentsService.findComm(page,rows,comment);
    }
    @RequestMapping("addComments")
    @ResponseBody
    public void addComments(CommentBean comment){
        if(StringUtils.isNullOrEmpty(comment.getCommentId())){
            commentsService.addComments(comment);
        }else{

            commentsService.updateComments(comment);
        }

    }
    @RequestMapping("findCommentsById")
    @ResponseBody
    public CommentBean findCommentsById(String commentId){
        return commentsService.findCommentsById(commentId);
    }
    //图片下载
    @RequestMapping("flieImgByID")
    @ResponseBody
    public  String downFile(String imgId, HttpServletResponse response) {
        //调用工具类
        OSSObject ossObject = OssFileUtils.downLoadImage(imgId);
        //给本地下载的时候生成文件名
        String fileName = new Date().toString().substring(0,5);
        //判断 返会文件件不为空
        if (ossObject != null) {
            //HTTPServletResponse 相应流
            InputStream inputStream = ossObject.getObjectContent();
            int buffer = 1024 * 10;
            byte data[] = new byte[buffer];
            try {
                response.setContentType("application/octet-stream");
                // 文件名可以任意指定
                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));//将文件名转为ASCLL编码
                int read;
                //迭代器
                while ((read = inputStream.read(data)) != -1) {
                    response.getOutputStream().write(data, 0, read);
                }
                response.getOutputStream().flush();//刷新
                response.getOutputStream().close();//关闭
                ossObject.close();//关闭
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }
}
