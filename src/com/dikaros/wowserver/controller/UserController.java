package com.dikaros.wowserver.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dikaros.wowserver.bean.User;
import com.dikaros.wowserver.dao.impl.UserDao;
import com.dikaros.wowserver.util.ResultUtil;
import com.dikaros.wowserver.websocket.SessionCenter;

import dikaros.json.JsonExtendedUtil;

@Controller
public class UserController {

	public final static String LOGIN_SUCCESS = "SUCCESS_200";

	/**
	 * 登录
	 * 
	 * @param jsonFile
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/login")
	public void login(String jsonFile, HttpServletResponse response,
			HttpSession session) throws Exception {
			
		String phone = JsonExtendedUtil.getParam(jsonFile, "phone");
		String password = JsonExtendedUtil.getParam(jsonFile, "password");
		UserDao userDao = new UserDao();
		User user = null;
		int code = 500;
		if ((user = userDao.query(phone)) != null) {
			if (user.getPassword().equals(password)) {
				code = 101;
			} else {
				code = 102;
			}
		} else {
			code = 103;
		}
		String jsonResult = ResultUtil.getResultById(code).toJson();
		if (code == 101) {
			String userJson = user.toJson();
			jsonResult = userJson.substring(0, userJson.length() - 2) + ","
					+ "\"sessionId\":" + session.getId() + ","
					+ jsonResult.substring(2);
			// 将用户session保存到sessionCenter中
			SessionCenter.userIdToHttpSessionMap.put(user.getId() + "",
					session.getId());
		}
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonResult);
	}

	/**
	 * 注册
	 * 
	 * @param jsonFile
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/regist")
	public void regist(String jsonFile, HttpServletResponse response)
			throws IOException {
		String phone = JsonExtendedUtil.getParam(jsonFile, "phone");
		String password = JsonExtendedUtil.getParam(jsonFile, "password");
		UserDao userDao = new UserDao();
		@SuppressWarnings("unused")
		User user = null;
		int code = 500;
		if ((user = userDao.query(phone)) == null) {
			boolean save = userDao.save(new User(phone, password));
			if (save) {
				code = 201;
			}
		} else {
			code = 202;
		}
		String jsonResult = ResultUtil.getResultById(code).toJson();
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonResult);
	}

}
