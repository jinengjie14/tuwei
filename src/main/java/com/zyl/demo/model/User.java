package com.zyl.demo.model;

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
	public String id;
	@NotEmpty(message="用户名不能为空")  
	public String account;
	@NotEmpty(message="密码不能为空")  
	@Length(min=6, message="密码长度不能少于6位")  
	public String password;
	
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
	
	
}
