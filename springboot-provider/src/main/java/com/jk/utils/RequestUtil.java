/** 
 * <pre>项目名称:mav_1 
 * 文件名称:RequestUtil.java 
 * 包名:com.jk.utils 
 * 创建日期:2019年3月15日上午11:40:15 
 * Copyright (c) 2019, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.utils;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/** 
 * <pre>项目名称：mav_1    
 * 类名称：RequestUtil    
 * 类描述：    
 * 创建人：史叶荣 
 * 创建时间：2019年3月15日 上午11:40:15    
 * 修改人：史叶荣 
 * 修改时间：2019年3月15日 上午11:40:15    
 * 修改备注：       
 * @version </pre>    
 */
public class RequestUtil {

	 
	 public static HttpServletRequest getInstance(){
		 return getRequestAttributes().getRequest();
	 }
	 public static ServletRequestAttributes getRequestAttributes() {
	        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
	 }

	 public static ServletContext getServletContext() {
	        return ContextLoader.getCurrentWebApplicationContext().getServletContext();
	 }
	

}
