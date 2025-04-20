package com.proclus.climasphere.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Video {
    private int id;
    private int width;
    private int height;
    private int duration;
    private User user;
    private String url;
    private String image;

    @JsonProperty("video_files")
    private List<VideoFile> videoFiles;

    @JsonProperty("video_pictures")
    private List<VideoPicture> videoPictures;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<VideoFile> getVideoFiles() {
        return videoFiles;
    }

    public void setVideoFiles(List<VideoFile> videoFiles) {
        this.videoFiles = videoFiles;
    }

    public List<VideoPicture> getVideoPictures() {
        return videoPictures;
    }

    public void setVideoPictures(List<VideoPicture> videoPictures) {
        this.videoPictures = videoPictures;
    }
}
