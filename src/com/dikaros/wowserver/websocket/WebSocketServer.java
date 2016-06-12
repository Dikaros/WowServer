package com.dikaros.wowserver.websocket;

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
	private static SimpleDateFormat DATH_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm");

	@OnOpen
	public void open(Session session) {
		Map<String, List<String>> requestMap = session.getRequestParameterMap();
		// 获取userId
		String userId = requestMap.get("userId").get(0);
		// 如果用户已经登录成功，将session增加到center中
		if (SessionCenter.userIdToHttpSessionMap.containsKey(userId)) {
			SessionCenter.addSession(
					SessionCenter.userIdToHttpSessionMap.get(userId), session);
		}

	}

	@OnMessage
	public void onMessage(String message, Session session) {
		Gson gson = new Gson();
		// 获得消息
		Message m = gson.fromJson(message, Message.class);

		// 获得接收人的httpSessionId
		String httpSessionId = SessionCenter.userIdToHttpSessionMap.get(m
				.getReceiverId());
		// 获得接收人的session
		Session receiverSession = SessionCenter.getSession(httpSessionId);
		SessionCenter.sendMessage(message, receiverSession);

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
