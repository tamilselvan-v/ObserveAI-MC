package com.example.newproject.service;

import com.example.newproject.dto.request.CreateVideoRequest;
import com.example.newproject.dto.response.CreateVideoResponse;
import com.example.newproject.dto.response.GetVideoResponse;
import com.example.newproject.dto.response.WatchVideoResponse;
import com.example.newproject.exceptions.VideoNotFoundError;
import com.example.newproject.models.Video;
import lombok.Synchronized;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
public class VideoService implements IVideoService {

    private final Map<String, Video> videos;
    public VideoService() {
        videos = new HashMap<>();
    }

    @Override
    public CreateVideoResponse uploadVideo(CreateVideoRequest videoRequest) {
        Video video = Video.builder()
                .url(videoRequest.getUrl())
                .title(videoRequest.getTitle())
                .description(videoRequest.getDescription())
                .uploadDate(Date.from(Instant.now()))
                .watchCount(0)
                .build();

        String videoId = UUID.randomUUID().toString();

        videos.put(videoId, video);

        return CreateVideoResponse.builder()
                .videoId(videoId)
                .build();
    }

    @Override
    public GetVideoResponse getVideo(String id) throws VideoNotFoundError {
        Video video = videos.get(id);
        if (Objects.isNull(video)) {
            throw new VideoNotFoundError("Video with provided id not found");
        }
        return GetVideoResponse.builder()
                .url(video.getUrl())
                .title(video.getTitle())
                .description(video.getDescription())
                .uploadDate(video.getUploadDate().toString()) // We can use any specific date format if needed. For now casting to string to give ISO date
                .watchCount(video.getWatchCount())
                .build();
    }

    @Override
    public WatchVideoResponse watchVideo(String id) throws VideoNotFoundError {
        Video video = videos.get(id);
        if (Objects.isNull(video)) {
            throw new VideoNotFoundError("Video with provided id not found");
        }
        updateWatchCount(video);
        return WatchVideoResponse.builder()
                .response("video is being streamed")
                .build();
    }


    // Need to check how can we make it sync for individual videos, now it blocks everyone for any video
    @Synchronized
    private void updateWatchCount(Video video) {
        video.setWatchCount(video.getWatchCount() + 1);
    }
}
