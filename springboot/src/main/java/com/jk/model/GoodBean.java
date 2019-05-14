/** 
 * <pre>项目名称:ssm-task-syr 
 * 文件名称:GoodBean.java 
 * 包名:com.jk.syr.model 
 * 创建日期:2019年3月22日下午9:01:17 
 * Copyright (c) 2019, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.model;

import org.springframework.data.mongodb.core.mapping.Document;

/** 
 * <pre>项目名称：ssm-task-syr    
 * 类名称：GoodBean    
 * 类描述：    
 * 创建人：史叶荣 
 * 创建时间：2019年3月22日 下午9:01:17    
 * 修改人：史叶荣 
 * 修改时间：2019年3月22日 下午9:01:17    
 * 修改备注：       
 * @version </pre>    
 */
@Document(collection="t_good")
public class GoodBean {
   private String goodId;
   
   private String name;
   
   private long count;

   private String imgsrc;

public String getGoodId() {
	return goodId;
}

public void setGoodId(String goodId) {
	this.goodId = goodId;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}


public long getCount() {
	return count;
}

public void setCount(long count) {
	this.count = count;
}

	public String getImgsrc() {
		return imgsrc;
	}

	public void setImgsrc(String imgsrc) {
		this.imgsrc = imgsrc;
	}


	@Override
	public String toString() {
		return "GoodBean{" +
				"goodId='" + goodId + '\'' +
				", name='" + name + '\'' +
				", count=" + count +
				", imgsrc='" + imgsrc + '\'' +
				'}';
	}
}
