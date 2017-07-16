package com.backend.model;

import java.util.List;

public interface VideoDAO_interface {

    public void insert(Video video);

    public void update(Video video);
    public void delete(String vidNo);
    public Video findByPrimaryKey(String vidNo);

    public List<Video> getAll();

}
