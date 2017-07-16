
package com.backend.model;

import java.util.List;


public class VideoService {

    private VideoDAO_interface dao;

    public VideoService() {
        dao = new VideoDAO();
    }

//    public Video addVideo(Baby baby,  String vid_title,
//                            String vid_annot, byte[] vid_file,
//                            String vid_shr) {
//
//        Video videoVO = new Video();
//
////      vid_no, baby_no, vid_title, vid_date, vid_annot, vid_file, vid_shr
//        videoVO.setBaby(baby);
//        videoVO.setVidTitle(vid_title);
//        videoVO.setVidAnnot(vid_annot);
//        videoVO.setVidFile(vid_file);
//        videoVO.setVidShr(vid_shr);
//        dao.insert(videoVO);
//
//        return videoVO;
//    }

    public Video updateVideo(Baby baby,  String vid_title,
                            String vid_annot, byte[] vid_file,
                            String vid_shr) {

        Video videoVO = new Video();

        videoVO.setBaby(baby);
        videoVO.setVidTitle(vid_title);
        videoVO.setVidAnnot(vid_annot);
        videoVO.setVidShr(vid_shr);
        dao.update(videoVO);
        return videoVO;
    }
    public void deleteVideo(String vid_no) {

        Video videoVO = new Video();

//      baby_no, pho_title, pho_date, pho_annot, pho_file, pho_shr
        videoVO.setVidNo(vid_no);
        dao.delete(vid_no);
    }

    public Video getOneVideo(String vid_no) {
        return dao.findByPrimaryKey(vid_no);
    }

    public List<Video> getAll() {
        return dao.getAll();
    }
}
