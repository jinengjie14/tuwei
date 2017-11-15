package com.zyl.demo.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Zyl
 *用户表
 */
@Entity
public class User {
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 32)
	private String id;//id
	@NotEmpty(message="用户名不能为空")  
	private String account;//用户名
	@NotEmpty(message="密码不能为空")  
	@Length(min=6, message="密码长度不能少于6位")  
	private String password;//密码
	private String image;//头像
	private String sex;//性别
	private String status;//状态
	private Timestamp ctime;//创建时间
	private Date birthday;//出生年月
	
	public void init(){
		this.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		this.setCtime(new Timestamp(System.currentTimeMillis()));
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Timestamp getCtime() {
		return ctime;
	}
	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
}
