package com.dikaros.wowserver.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import antlr.collections.List;

import com.dikaros.wowserver.bean.Friend;
import com.dikaros.wowserver.bean.FriendView;
import com.dikaros.wowserver.bean.User;
import com.dikaros.wowserver.dao.impl.FriendDao;
import com.dikaros.wowserver.dao.impl.UserDao;
import com.dikaros.wowserver.util.ResultUtil;
import com.dikaros.wowserver.websocket.SessionCenter;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import dikaros.json.JsonExtendedUtil;
import dikaros.org.json.JSONArray;
import dikaros.org.json.JSONObject;

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
			
			
			jsonResult = userJson.substring(0, userJson.length() - 1) + ","
					+ "\"sessionId\":\"" + session.getId() + "\",\""
					+ jsonResult.substring(2);
			
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
	
	/**
	 * 增加好友
	 * @param jsonFile
	 * @param response
	 */
	@RequestMapping("/friend/add")
	public void addFriend(String jsonFile,HttpServletResponse response){
		Gson gson = new Gson();
		Friend friend = gson.fromJson(jsonFile, Friend.class);
		FriendDao dao = new FriendDao();
		Friend reFriend = friend.reverse();
		boolean r = dao.save(friend);
		boolean r2 = dao.save(reFriend);
		response.setCharacterEncoding("utf-8");
		try {
			response.getWriter().write(ResultUtil.getResultById(r&&r2?400:401).toJson());
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	/**
	 * 查找所有好友
	 * @param jsonFile
	 * @param response
	 */
	@RequestMapping("/friend/query")
	public void queryFriend(String jsonFile,HttpServletResponse response){
		JSONObject object = new JSONObject(jsonFile);
		long userId = object.getLong("userId");
		FriendDao friendDao = new FriendDao();
		java.util.List<FriendView> friendViews = friendDao.queryFriend(userId);
		JSONArray array = new JSONArray();
		for (FriendView friendView : friendViews) {
			JSONObject fObject = new JSONObject(friendView.toJson());
			array.put(fObject);
		}
		try {
			response.getWriter().write(array.toString());
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/user/query")
	public void queryUserById(String jsonFile,HttpServletResponse response) {
		JSONObject object = new JSONObject(jsonFile);
		long userId = object.getLong("userId");
		User user = new UserDao().query(userId);
		
	}

}
