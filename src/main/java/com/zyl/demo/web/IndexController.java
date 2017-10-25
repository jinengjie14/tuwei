package com.zyl.demo.web;


import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zyl.demo.utils.LoginResponse;
import com.zyl.demo.utils.Result;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * @author Zyl
 *登录界面控制器及主页跳转
 */
@Controller
public class IndexController {
	
	/**
	 * 进入登录界面
	 * @return
	 */
	@GetMapping("/index")
	public String index(){
		return "login";
	}
	/**
	 * 模拟post请求
	 * @param session
	 * @param account
	 * @param password
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/login")
	@ResponseBody
	public Result login(HttpSession session,String account,String password) throws Exception{
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
			session.setAttribute("id", loginResponse.getData().getId());
			session.setAttribute("account", loginResponse.getData().getAccount());
		}else if(loginResponse.getCode() == 412){
			if(account!=""||password!=""){
				return new Result("/", 412, "账号密码错误");
			}else{
				return new Result("/", 413, "账号密码不能为空");
			}
		}
		return new Result("/", 200, "登录成功");
	}
	
	/**
	 * 跳转到主页
	 * @return
	 */
	@GetMapping("/")
	public String aaa(){
		return "index1";
	}
}
