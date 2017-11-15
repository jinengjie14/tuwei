package com.zyl.demo.model;

import java.sql.Timestamp;
/**
 * info实体类
 */
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Info {
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 32)
	private String id;
	private String title;
	private String markdown;
	private String image;
	private Timestamp ctime;
	private String userid;
	
	
	public void init(){
		this.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		this.setCtime(new Timestamp(System.currentTimeMillis()));
		this.setTitle("无标题文本");
		this.setMarkdown(null);
	}
	
	public void save(){
		this.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		this.setCtime(new Timestamp(System.currentTimeMillis()));
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMarkdown() {
		return markdown;
	}
	public void setMarkdown(String markdown) {
		this.markdown = markdown;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Timestamp getCtime() {
		return ctime;
	}
	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
}
