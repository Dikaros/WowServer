<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>模块测试jsp</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body style="margin-left: 20px;margin-right: 20px">
	<h2>注册登录模块测试</h2>
	<h4>移动端发送jsonFile测试</h4>
	<div>
		表单一(移动客户端登录)
		<form action="login.do" method="post">
			jsonFile:<input type="text" name="jsonFile"
				style="width:200px;margin-top:5px"
				value='jsonFile:{"phone":"123","password":"222"}'> <input
				type="submit" value="提交">
		</form>
	</div>
	<div>
		表单二(移动客户端注册)
		<form action="<%=path%>/user/regist.action" method="post">
			jsonFile:<input type="text" name="jsonFile"
				style="width:200px;margin-top:5px"
				value='[{"phone":"123","password":"222"}]'> <input
				type="submit" value="提交">
		</form>
	</div>
	<h4>网页测试</h4>
	<div>
		表单三(网页登录)
		<form action="<%=path%>/webuser/login.action" method="post">
			<table style="text-align:left">
				<tr>
					<th>用户名：</th>
					<th><input type="text" name="phone"></th>
				</tr>
				<tr>
					<th>密码：</th>
					<th><input type="password" name="password"></th>
				</tr>
			</table>
			<input type="submit" value="提交">
		</form>
	</div>
	<div>
		表单四(网页注册)
		<form action="<%=path%>/webuser/regist.action" method="post">
			<table style="text-align:left">
				<tr>
					<th>用户名：</th>
					<th><input type="text" name="phone"></th>
				</tr>
				<tr>
					<th>密码：</th>
					<th><input type="password" name="password"></th>
				</tr>
			</table>
			<input type="submit" value="提交">
		</form>
	</div>

	<h2>WebSocket模块测试</h2>
	<div>
		<button id="connect" onclick="connect()">点击链接服务器</button>
	</div>
	<div id="output"
		style="border: 2px solid rgba(104, 102, 105, 0.94);padding: 5px"></div>
	<div style="width:100%;margin-top:20px">
		<input id="sendMessage" type="text" name="msg">
		<button id="btnSend" onclick="buttonSend()">发送</button>
		<span id="sendForm" style="color:red;"></span>
	</div>


	<script language="javascript" type="text/javascript">
		var wsUrl = "ws://127.0.0.1:8080/WowServer/websocket?userName=456&userPassword=456";
		var output;
		var sendMessage;
		var sendForm;
		var btnConnect;
		/*注册时密码的正则表达式，表示为密码必须为0-9A-Za-z中同时包含数字和字母并且在6-12位的字符串*/
		var registRegex = /^(?![0-9]+$)(?![A-Za-z]+$)[0-9A-Za-z]{6,12}$/;
		function init() {
			output = document.getElementById("output");
			window.WebSocket = window.WebSocket || window.MozWebSocket;
			if (!window.WebSocket) {
				alert("浏览器不支持WebSocket");
				return;
			}
			sendMessage = document.getElementById("sendMessage");
			sendForm = document.getElementById("sendForm");
			btnConnect = document.getElementById("connect");
			sendMessage.onkeydown = keyDown;
			var re = new RegExp(registRegex);
			//var result = re.test("12312111");
			alert(result);
		}
		
		var ws;

		function connect() {
			if (!ws) {
				testWebSocket();

			} else {
				btnConnect.innerHTML = "服务器已连接";
			}
		}

		function buttonSend() {
			if (ws) {
				if (sendMessage.value) {
					sendForm.innerHTML = "";
					doSend(sendMessage.value);

				} else {
					sendForm.innerHTML = "数据不能为空";
				}
			}
		}
		function testWebSocket() {
			ws = new WebSocket(wsUrl);
			ws.onopen = function(evt) {
				onOpen(evt);
				doSend("this is a websocket connection from browse");

			};
			ws.onclose = function(evt) {
				onClose(evt);
			};
			ws.onerror = function(evt) {
				onError(evt);
			};
			ws.onmessage = function(evt) {
				onMessage(evt);
			};

		}
		function keyDown(e) {
			var action = e || window.event;
			var code = action.keyCode || action.which || action.charCode;
			if (code == 13) {
				buttonSend();
				return false;
			}
			return true;
		}
		function onOpen(evt) {
			writeToScreen("CONNECTED TO SERVER");
			btnConnect.innerHTML = "服务器已连接";
			doSend("websocket js client connected");
		}

		function onClose(evt) {
			writeToScreen("DISCONNECTED");
			btnConnect.innerHTML = "点击连接服务器";
			ws = null;
		}
		function onMessage(evt) {
			writeToScreen("<span style='color:blue;'>RESPONSE:" + evt.data
					+ "</span>");
		}
		function onError(evt) {
			btnConnect.innerHTML = "点击连接服务器";
			ws = null;
		}
		function doSend(evt) {
			ws.send(evt);
			writeToScreen("<span style='color:green;'>SEND:" + evt + "</span>");

		}
		function writeToScreen(evt) {
			var pre = document.createElement("p");
			pre.style.wordWrap = "break-word";
			pre.innerHTML = evt;
			output.appendChild(pre);
		}
		window.addEventListener("load", init, false);
	</script>
</body>
</html>
