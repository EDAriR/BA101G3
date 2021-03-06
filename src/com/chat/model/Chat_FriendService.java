package com.chat.model;

import java.util.List;


public class Chat_FriendService {

    private Chat_FriendDAO_interface dao;

    public Chat_FriendService() {
        dao = new Chat_FriendDAO();
    }

    public Chat_FriendVO addChat_Friend(String mem_no_s, String mem_no_o, String cf_is_del) {

        Chat_FriendVO chat_FriendVO = new Chat_FriendVO();
        
//      cf_no  mem_no_s  mem_no_o  cf_is_del
        
        chat_FriendVO.setMem_no_s(mem_no_s);
        chat_FriendVO.setMem_no_o(mem_no_o);
        chat_FriendVO.setCf_is_del(cf_is_del);
        dao.insert(chat_FriendVO);

        return chat_FriendVO;
    }

    public Chat_FriendVO updateChat_Friend(String cf_no, String cf_is_del) {

        Chat_FriendVO chat_FriendVO = new Chat_FriendVO();

        chat_FriendVO.setCf_is_del(cf_is_del);
        chat_FriendVO.setCf_no(cf_no);
        dao.update(chat_FriendVO);
        return chat_FriendVO;
    }

    public Chat_FriendVO getOneChat_Friend(String cf_no) { return dao.findByPrimaryKey(cf_no); }

    public List<Chat_FriendVO> getOneMCF(String mem_no_s) { return dao.findByMemNoS(mem_no_s); }

    public List<Chat_FriendVO> getOtherMCF(String mem_no_o) { return dao.findByMemNoO(mem_no_o); }

    public Chat_FriendVO getOneF(String mem_no_s, String mem_no_o) { return dao.findByMemNo(mem_no_s, mem_no_o); }

    public List<Chat_FriendVO> getAll() { return dao.getAll(); }
}
