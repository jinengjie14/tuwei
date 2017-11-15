package com.zyl.demo.service;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.zyl.demo.dao.UserDao;
import com.zyl.demo.model.User;
import com.zyl.demo.utils.LoginResponse;
import com.zyl.demo.utils.Md5Utils;
import com.zyl.demo.utils.Result;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
/**
 * 远程连接登录
 * @author Zyl
 *
 */
@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	public Result register(HttpSession session,String account,String password,User user) throws Exception{
		String url = "http://10.1.65.33:81/login";
		OkHttpClient httpClient = new OkHttpClient();
		FormBody.Builder formBody = new FormBody.Builder();
		formBody.add("account", account);
		formBody.add("password", password);
		Request request = new Request.Builder()
				.url(url)
				.post(formBody.build())
				.build();
	    Response response = httpClient.newCall(request).execute();
		LoginResponse loginResponse = JSON.parseObject(response.body().string(), LoginResponse.class);
		if(loginResponse.getCode() == 200){
			session.setAttribute("sessionid", loginResponse.getData().getId());
			session.setAttribute("sessionaccount", loginResponse.getData().getAccount());
		}else if(loginResponse.getCode() == 412){
			if(account!=""||password!=""){
				return new Result("/info", 412, "账号密码错误");
			}else{
				return new Result("/info", 413, "账号密码不能为空");
			}
		}
		List<User> u = findByAccount(loginResponse.getData().getAccount());//第三方登录查用户名 有就登录 没就创建
		if(u.size() == 0){
			user.setAccount(loginResponse.getData().getAccount());
			user.setPassword(Md5Utils.MD5Encode(password, "UTF-8", true));
			user.init();
			userDao.create(user);
			return new Result("/info", 200, "登录成功");
		}
		return new Result("/info", 200, "登录成功");
	}
	 
	public void create(User user){
		try{
			userDao.create(user);
		}catch(Exception e){
			throw e;
		}
	}
	public List<User> findByAccount(String account){
		List<User> user = userDao.findByAccount(account);
		return user;
	}
	
	public void login(User user){
		
	}
	
	
}
