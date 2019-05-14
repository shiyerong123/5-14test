/** 
 * <pre>项目名称:ssm-task-syr 
 * 文件名称:CommentBean.java 
 * 包名:com.jk.syr.model 
 * 创建日期:2019年3月22日下午9:02:57 
 * Copyright (c) 2019, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/** 
 * <pre>项目名称：ssm-task-syr    
 * 类名称：CommentBean    
 * 类描述：    
 * 创建人：史叶荣 
 * 创建时间：2019年3月22日 下午9:02:57    
 * 修改人：史叶荣 
 * 修改时间：2019年3月22日 下午9:02:57    
 * 修改备注：       
 * @version </pre>    
 */
@Document(collection="t_comments")
public class CommentBean {
    private String commentId;
    
    private String comments;
    
    private String commentsName;
    
    private Integer commentsLevel;
    
    private Integer commentsStars;
    
    private String goodsId;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GTM+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date commentsDate;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GTM+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startDate;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GTM+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDate;
    
    

	public Date getCommentsDate() {
		return commentsDate;
	}

	public void setCommentsDate(Date commentsDate) {
		this.commentsDate = commentsDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCommentsName() {
		return commentsName;
	}

	public void setCommentsName(String commentsName) {
		this.commentsName = commentsName;
	}

	public Integer getCommentsLevel() {
		return commentsLevel;
	}

	public void setCommentsLevel(Integer commentsLevel) {
		this.commentsLevel = commentsLevel;
	}

	public Integer getCommentsStars() {
		return commentsStars;
	}

	public void setCommentsStars(Integer commentsStars) {
		this.commentsStars = commentsStars;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	/* (non-Javadoc)    
	 * @see java.lang.Object#toString()    
	 */
	@Override
	public String toString() {
		return "CommentBean [commentId=" + commentId + ", comments=" + comments + ", commentsName=" + commentsName
				+ ", commentsLevel=" + commentsLevel + ", commentsStars=" + commentsStars + ", goodsId=" + goodsId
				+ ", commentsDate=" + commentsDate + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}

}   
