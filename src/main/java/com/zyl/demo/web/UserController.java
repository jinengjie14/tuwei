package com.zyl.demo.web;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zyl.demo.model.User;
import com.zyl.demo.service.UserService;
import com.zyl.demo.utils.Result;



/**
 * @author Zyl
 *登录界面控制器及主页跳转
 */
@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	/**
	 * 进入登录界面
	 * @return
	 */
	@GetMapping("/login")
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
	public Result login(HttpSession session,String account,String password,User user) throws Exception{
		Result result = new Result();
		return result = userService.register(session, account, password, user);
	}
	
	@GetMapping("/register")
	public String a(){
		return "register";
	}
}
