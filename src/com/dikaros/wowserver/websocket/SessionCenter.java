package com.dikaros.wowserver.websocket;

import java.io.IOException;
import java.util.HashMap;

import javax.websocket.Session;

import oracle.net.aso.s;

public class SessionCenter {
	
//	//用户和session的映射
//	private static HashMap<String, Session> webSocketClientSessions = new HashMap<>();
//	
//	public static HashMap<String, String> userIdToHttpSessionMap = new HashMap<>();
//	/**
//	 * The size of the websocket session
//	 * @return
//	 */
//	public static int size(){
//		return webSocketClientSessions.size();
//	}
//	/**
//	 * return whether the session map contains provided id
//	 * @param id
//	 * @return
//	 */
//	public static boolean contains(String id){
//		return webSocketClientSessions.containsKey(id);
//	}
//	
//	
//	/**
//	 * return the websocket session by id from map 
//	 * @param id
//	 * @return
//	 */
//	public static Session getSession(String id){
//		if (contains(id)) {
//			return webSocketClientSessions.get(id);
//		}
//		return null;
//	}
//	
//	
//	/**
//	 * put session into the session map 
//	 * @param key
//	 * @param value
//	 */
//	public static void addSession(String httpSessionId,Session value){
//		if (contains(httpSessionId)) {
//			System.err.println("the session had been put into the map");
//		}else {
//			webSocketClientSessions.put(httpSessionId,value);
//		}
//	}
//	
//	public static void pustToAll(String message){
//		for (String key : webSocketClientSessions.keySet()) {
//			try {
//				webSocketClientSessions.get(key).getBasicRemote().sendText(message);
//			} catch (IOException e) {
////				e.printStackTrace();
//				continue;
//			}
//		}
//	}
//	
//	public static void showSession(){
//		for (String key : webSocketClientSessions.keySet()) {
//			System.out.println(key+"\t"+webSocketClientSessions.get(key).toString());
//		}
//		System.out.println();
//	}
//	
//	public static void showLoginUser() {
//		for (String key : userIdToHttpSessionMap.keySet()) {
//			System.out.println(key+"\t"+userIdToHttpSessionMap.get(key));
//		}
//	}
//	
//	public static void removerSession(Session session){
//		String key = session.getId();
//		if (contains(key)) {
//			webSocketClientSessions.remove(key);
//		}
//	}
//	
//	public static void sendMessage(String message,Session session){
//		try {
//			session.getBasicRemote().sendText(message);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	private static HashMap<String, Session> webSocketClientSessions = new HashMap<>();
	private static HashMap<Long, String> userToSessionIdmap = new HashMap<>();
	/**
	 * The size of the websocket session
	 * @return
	 */
	public static int size(){
		return webSocketClientSessions.size();
	}
	/**
	 * return whether the session map contains provided id
	 * @param id
	 * @return
	 */
	public static boolean contains(String id){
		return webSocketClientSessions.containsKey(id);
	}
	
	/**
	 * @param userId
	 * @return
	 */
	public static Session getSessionByUserId(long userId){
		if (userToSessionIdmap.containsKey(userId)) {
			return webSocketClientSessions.get(userToSessionIdmap.get(userId));
		}
		return null;
	}
	
	/**
	 * return the websocket session by id from map 
	 * @param id
	 * @return
	 */
	public static Session getSession(String id){
		if (contains(id)) {
			return webSocketClientSessions.get(id);
		}
		return null;
	}
	
	
	/**
	 * put session into the session map 
	 * @param key
	 * @param value
	 */
	public static void addSession(String key,long userId,Session value){
		if (contains(key)) {
			System.err.println("the session had been put into the map");
		}else {
			webSocketClientSessions.put(key,value);
			userToSessionIdmap.put(userId,key);
		}
	}
	
	public static void pustToAll(String message){
		for (String key : webSocketClientSessions.keySet()) {
			try {
				webSocketClientSessions.get(key).getBasicRemote().sendText(message);
			} catch (IOException e) {
//				e.printStackTrace();
				continue;
			}
		}
	}
	
	public static void showSession(){
		
		for (long key : userToSessionIdmap.keySet()) {
			System.out.println(key+"\t"+userToSessionIdmap.get(key).toString()+"\t"+webSocketClientSessions.get(userToSessionIdmap.get(key)));
		}
		System.out.println();
	}
	
	public static void removerSession(Session session){
		String key = session.getId();
		if (contains(key)) {
			webSocketClientSessions.remove(key);
			for (long ke : userToSessionIdmap.keySet()) {
				if (userToSessionIdmap.get(ke).equals(key)) {
					userToSessionIdmap.remove(ke);
					break;
				}
			}
		}
	}
	
	public static void sendMessage(String message,Session session){
		try {
			session.getBasicRemote().sendText(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
