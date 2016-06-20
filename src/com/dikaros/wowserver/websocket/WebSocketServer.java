package com.dikaros.wowserver.websocket;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.dikaros.wowserver.bean.Message;
import com.google.gson.Gson;

@ServerEndpoint(value = "/websocket")
public class WebSocketServer {

	@OnOpen
	public void open(Session session) {
		Map<String, List<String>> requestMap = session.getRequestParameterMap();
		String userId = requestMap.get("userId").get(0);
//		String userPassword = requestMap.get("userPassword").get(0);
//		System.out.println(userName+"---"+userPassword);
		long uid = Long.parseLong(userId);
		SessionCenter.addSession(session.getId(),uid, session);
//		Map<String, List<String>> requestMap = session.getRequestParameterMap();
//		// 获取userId
//		String userId=null;
//		try {
//			 userId = requestMap.get("userId").get(0);
//		} catch (Exception e) {
//			try {
//				session.close();
//			} catch (IOException ie) {
//				ie.printStackTrace();
//			}
//		}
//		// 如果用户已经登录成功，将session增加到center中
//		if (userId==null||userId.equals("")) {
//			try {
//				session.close();
//			} catch (IOException e) {
//				// TODO 自动生成的 catch 块
//				e.printStackTrace();
//			}
//			return;
//		}
//		if (SessionCenter.userIdToHttpSessionMap.containsKey(userId)) {
//			SessionCenter.addSession(
//					SessionCenter.userIdToHttpSessionMap.get(userId), session);
//		}

	}

	@OnMessage(maxMessageSize=16*1024*1024)
	public void onMessage(String message, Session session) {
		if (!message.startsWith("{")&&!message.startsWith("[")) {
			return;
		}
		Gson gson = new Gson();
		// 获得消息
		Message m = gson.fromJson(message, Message.class);
//		System.out.println("收到信息"+message+m);
		// 获得接收人的httpSessionId
//		String httpSessionId = SessionCenter.userIdToHttpSessionMap.get(m
//				.getReceiverId());
//		// 获得接收人的session
//		Session receiverSession = SessionCenter.getSession(httpSessionId);
//		SessionCenter.sendMessage(message, receiverSession);
		Session session2 = SessionCenter.getSessionByUserId(m.getReceiverId());
		if (session2!=null) {
			SessionCenter.sendMessage(message, session2);
		}

	}

	@OnClose
	public void onClose(Session session) {
		SessionCenter.removerSession(session);
		System.out.println("关闭连接");
	}

	@OnError
	public void onError(Session session, Throwable t) {
		t.printStackTrace();
	}
}
