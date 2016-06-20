package com.dikaros.wowserver.websocket;

import java.util.Scanner;


public class Commander extends Thread{
	Scanner scanner = new Scanner(System.in);
	
	public Commander() {
	}
	@Override
	public void run() {
		
		System.out.println("信息: im控制器已经启动");
		while (true) {
			String i = scanner.nextLine().trim().toLowerCase();
			
			if (i.startsWith("-push")) {
				if (i.length()>6) {
					SessionCenter.pustToAll(i.substring(6));
					System.out.println("消息已发送");
				}else {
					System.out.println("请输入需要群发的信息：");
					String m = scanner.nextLine();
					SessionCenter.pustToAll(m);
					System.out.println("消息已发送");
				}
			}else if (i.equals("-count")) {
				System.out.println("当前在线的用户数量为:"+SessionCenter.size());
			}else if (i.equals("-show")) {
				System.out.println("userId\tsessionId\tsession message");
				SessionCenter.showSession();
			}
//			else if (i.equals("-user")) {
//				System.out.println("当前在线的用户数为："+SessionCenter.userIdToHttpSessionMap.size());
//				System.out.println("userId\thttp session");
////				SessionCenter.showLoginUser();
//			}
			else {
				System.out.println("命令\t说明");
				System.out.println("-count\t查询当前在线用户数");
				System.out.println("-show\t显示当前建立的所有对话");
				System.out.println("-push\t向所有客户端推送消息");
//				System.out.println("-user\t查询登录的用户");
			}
		}
	}
}