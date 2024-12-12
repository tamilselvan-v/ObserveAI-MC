package com.example.newproject.service;

import com.example.newproject.dto.request.CreateVideoRequest;
import com.example.newproject.dto.response.CreateVideoResponse;
import com.example.newproject.dto.response.GetVideoResponse;
import com.example.newproject.dto.response.WatchVideoResponse;
import com.example.newproject.exceptions.VideoNotFoundError;

public interface IVideoService {
    public CreateVideoResponse uploadVideo(CreateVideoRequest videoRequest);

    public GetVideoResponse getVideo(String id) throws VideoNotFoundError;

    public WatchVideoResponse watchVideo(String id) throws VideoNotFoundError;
}
