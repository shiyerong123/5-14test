/** 
 * <pre>项目名称:mav_1 
 * 文件名称:ConstantConf.java 
 * 包名:com.jk 
 * 创建日期:2019年3月8日下午7:10:16 
 * Copyright (c) 2019, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk;

/** 
 * <pre>项目名称：mav_1    
 * 类名称：ConstantConf    
 * 类描述：    
 * 创建人：史叶荣 
 * 创建时间：2019年3月8日 下午7:10:16    
 * 修改人：史叶荣 
 * 修改时间：2019年3月8日 下午7:10:16    
 * 修改备注：       
 * @version </pre>    
 */
public class ConstantConf {
	//天气
    public static final String WEATHER_PATH="http://t.weather.sojson.com/api/weather/city";
    public static String WEATHER_RESULT="今天是{0},{1},天气{2},{3},{4},风向{5},风力{6},日出时间{7},日落时间{8},注意：{9}";
  //机器人接口
  	public static final String JIQIREN_PATH ="http://api.qingyunke.com/api.php?key=free&appid=0&msg=";


    public  static  final  String  SMS_URL="https://api.miaodiyun.com/20150822/industrySMS/sendSMS";
	 
    public static final String ACCOUNTSID = "0374867b2c1844dbbe0bf019bf0def28";
	 	
	public static final String AUTH_TOKEN = "d05d06f418974fc6aceb9233e38b7539";
	 	
	public static final String TEMPLATEID = "164547838";
	
	public static final String SMS_SUCCESS = "00000";
	
	public static final String SMS_LOGIN_CODE = "dlyzm";
	
	public static final String SMS_LOGIN_LOCK = "lock";
	
	public static final String USER_POWER_LIST = "userPower";
	
	public static final String USER_NAV_LIST = "userNavList";
	
	public static final String SMS_WEATHER = "weather";
	
	public static final String SMS_DEPT = "dept";
	
	public static final String SMS_PRIVINCE = "privince";
	
	public static final String SMS_CITY = "city";
	
	//部门下拉列表框缓存30分钟
	public static final Integer SMS_CITY_TIME_OUT = 24;
	
	//部门下拉列表框缓存30分钟
	public static final Integer SMS_DEPT_TIME_OUT = 30;

    //天气1小时更新一次
	public static final Integer SMS_WEATHER_TIME_OUT = 1;
	//短信验证码有效期5分钟
	public static final Integer SMS_LOGIN_CODE_TIME_OUT = 5;
    //短信验证码锁一分钟
	public static final Integer SMS_LOGIN_LOCK_TIME_OUT = 1;
	
}
