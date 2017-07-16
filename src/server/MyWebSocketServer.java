package server;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;


@ServerEndpoint("/MyWebSocketServer/{My_mem_no}/{F_mem_no}")
public class MyWebSocketServer {
	public String roomKey;
//	�쥻���g�k
//	private static final Set<Session> connectedSessions = Collections.synchronizedSet(new HashSet<>());
	private static final Map<String , Set<Session>> connectedSessions = Collections.synchronizedMap(new HashMap<>());
	@OnOpen
	public void onOpen(@PathParam("My_mem_no") String My_mem_no, @PathParam("F_mem_no") String F_mem_no, Session userSession) throws IOException {
//		�쥻���g�k
//		connectedSessions.add(userSession);
System.out.println("---------onOpen�}�l-----------");
		if(My_mem_no.compareTo(F_mem_no)< 0){
			roomKey = My_mem_no + F_mem_no;
		}else{
			roomKey = F_mem_no + My_mem_no;
		}
		if(connectedSessions.containsKey(roomKey)){
System.out.println("�i��containsKey,roomKey= "+roomKey);
			Set<Session> roomValues = connectedSessions.get(roomKey);
System.out.println("�i��containsKey��roomValues.size()"+roomValues.size());
			roomValues.add(userSession);
System.out.println("add��:�i��containsKey��roomValues.size()"+roomValues.size());
			connectedSessions.put(roomKey, roomValues);
System.out.println("put��:Map connectedSessions ���j�p= "+ connectedSessions.size());	
System.out.println("=================================");
		}else{
			System.out.println("roomKey= "+roomKey);
			Set<Session> roomValues = Collections.synchronizedSet(new HashSet<>());
			roomValues.add(userSession);
System.out.println("Set<Session> roomValues ���j�p= "+ roomValues.size());		
			
			connectedSessions.put(roomKey, roomValues);   			 //Key �n�ͪ��|���s��  Value:�۰ʲ��ͪ�websocket session
System.out.println("Map connectedSessions ���j�p= "+ connectedSessions.size());		
		}
	
		String text = String.format("Session ID = %s, connected; My_mem_no = %s; F_mem_no = %s", 
				userSession.getId(), My_mem_no, F_mem_no);
		System.out.println(text);
System.out.println("---------onOpen����-----------");
	}

	@OnMessage
	public void onMessage(Session userSession, String message) {
System.out.println("--------�i��onMessage-----------");
System.out.println("���մ��մ��մ��մ��մ��մ��մ��մ��� "+roomKey);
System.out.println("userSession(��ionMessage)= "+ userSession.getId() );
System.out.println("message(��ionMessage)= "+message );
			Set<Session> sessionSet = connectedSessions.get(roomKey);
//		Set<String> keySet = connectedSessions.keySet();
//		for(String key: keySet){
//			Set<Session> sessionSet = connectedSessions.get(key);
System.out.println("sessionSet.size()(forEach�]�X�Ҧ���sessionSet)= "+sessionSet.size() );
			for (Session session : sessionSet){
//				if(userSession.equals(session) && session.isOpen()){
System.out.println("!!!!!!!!!!!�i�JforEach");
System.out.println("userSession(�iif�P�_��)= "+userSession.getId() );
System.out.println("session= "+session.getId() );
					session.getAsyncRemote().sendText(message);
//				}
//			}

			System.out.println("-------------------");
		}
		
		
//		�쥻���g�k
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
