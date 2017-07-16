package server;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;


@ServerEndpoint("/MyWebSocketServer/{My_mem_no}/{F_mem_no}")
public class MyWebSocketServer {
	public String roomKey;
//	原本的寫法
//	private static final Set<Session> connectedSessions = Collections.synchronizedSet(new HashSet<>());
	private static final Map<String , Set<Session>> connectedSessions = Collections.synchronizedMap(new HashMap<>());
	@OnOpen
	public void onOpen(@PathParam("My_mem_no") String My_mem_no, @PathParam("F_mem_no") String F_mem_no, Session userSession) throws IOException {
//		原本的寫法
//		connectedSessions.add(userSession);
System.out.println("---------onOpen開始-----------");
		if(My_mem_no.compareTo(F_mem_no)< 0){
			roomKey = My_mem_no + F_mem_no;
		}else{
			roomKey = F_mem_no + My_mem_no;
		}
		if(connectedSessions.containsKey(roomKey)){
System.out.println("進來containsKey,roomKey= "+roomKey);
			Set<Session> roomValues = connectedSessions.get(roomKey);
System.out.println("進來containsKey的roomValues.size()"+roomValues.size());
			roomValues.add(userSession);
System.out.println("add後:進來containsKey的roomValues.size()"+roomValues.size());
			connectedSessions.put(roomKey, roomValues);
System.out.println("put後:Map connectedSessions 的大小= "+ connectedSessions.size());	
System.out.println("=================================");
		}else{
			System.out.println("roomKey= "+roomKey);
			Set<Session> roomValues = Collections.synchronizedSet(new HashSet<>());
			roomValues.add(userSession);
System.out.println("Set<Session> roomValues 的大小= "+ roomValues.size());		
			
			connectedSessions.put(roomKey, roomValues);   			 //Key 好友的會員編號  Value:自動產生的websocket session
System.out.println("Map connectedSessions 的大小= "+ connectedSessions.size());		
		}
	
		String text = String.format("Session ID = %s, connected; My_mem_no = %s; F_mem_no = %s", 
				userSession.getId(), My_mem_no, F_mem_no);
		System.out.println(text);
System.out.println("---------onOpen結束-----------");
	}

	@OnMessage
	public void onMessage(Session userSession, String message) {
System.out.println("--------進來onMessage-----------");
System.out.println("測試測試測試測試測試測試測試測試測試 "+roomKey);
System.out.println("userSession(剛進onMessage)= "+ userSession.getId() );
System.out.println("message(剛進onMessage)= "+message );
			Set<Session> sessionSet = connectedSessions.get(roomKey);
//		Set<String> keySet = connectedSessions.keySet();
//		for(String key: keySet){
//			Set<Session> sessionSet = connectedSessions.get(key);
System.out.println("sessionSet.size()(forEach跑出所有的sessionSet)= "+sessionSet.size() );
			for (Session session : sessionSet){
//				if(userSession.equals(session) && session.isOpen()){
System.out.println("!!!!!!!!!!!進入forEach");
System.out.println("userSession(進if判斷的)= "+userSession.getId() );
System.out.println("session= "+session.getId() );
					session.getAsyncRemote().sendText(message);
//				}
//			}

			System.out.println("-------------------");
		}
		
		
//		原本的寫法
//		for (Session session : connectedSessions) {
//
//			if (session.isOpen())
//				session.getAsyncRemote().sendText(message);
//		}
//		System.out.println("Message received: " + message);
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		Set<Session> sessionSet = connectedSessions.get(roomKey);
		sessionSet.remove(userSession);
		String text = String.format("session ID = %s, disconnected; close code = %d", userSession.getId(),
				reason.getCloseCode().getCode());
		System.out.println(text);
	}
}
