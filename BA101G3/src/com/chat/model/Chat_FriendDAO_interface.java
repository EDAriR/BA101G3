package com.chat.model;

import java.util.List;

/**
 * Created by Java on 2017/6/10.
 */
public interface Chat_FriendDAO_interface {
    public void insert(Chat_FriendVO chat_FriendVO);
    public void update(Chat_FriendVO cf_no);
    public Chat_FriendVO findByPrimaryKey(String cf_no);
    public List<Chat_FriendVO> findByMemNoS(String mem_no_s);

    public List<Chat_FriendVO> findByMemNoO(String mem_no_o);

    public Chat_FriendVO findByMemNo(String mem_no_s, String mem_no_o);
    public List<Chat_FriendVO> getAll();
}
