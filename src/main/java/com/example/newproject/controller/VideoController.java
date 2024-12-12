package com.example.newproject.controller;


import com.example.newproject.dto.request.CreateVideoRequest;
import com.example.newproject.dto.response.CreateVideoResponse;
import com.example.newproject.dto.response.GetVideoResponse;
import com.example.newproject.dto.response.WatchVideoResponse;
import com.example.newproject.exceptions.VideoNotFoundError;
import com.example.newproject.models.Video;
import com.example.newproject.service.IVideoService;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class VideoController {

    private final IVideoService videoService;
    public VideoController(IVideoService videoService) {
        this.videoService = videoService;
    }
    @PostMapping("v1/video")
    public ResponseEntity<CreateVideoResponse> uploadVideo(@Valid @RequestBody CreateVideoRequest body) {
        var resp = videoService.uploadVideo(body);
        return new ResponseEntity<CreateVideoResponse>(resp, HttpStatus.CREATED);
    }

    @GetMapping("v1/video/{id}")
    public ResponseEntity<Object> getVideo(@PathVariable("id") String id) {

        try {
            var resp = videoService.getVideo(id);
            return new ResponseEntity<>(resp, HttpStatus.OK);
        } catch (VideoNotFoundError e) {
            // This needs to be moved to proper error message
            var res = new HashMap<String, String>();
            res.put("message", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("v1/video/{id}/watch")
    public ResponseEntity<?> watchVideoResponse(@PathVariable("id") String id) throws VideoNotFoundError {
        try {
            var resp = videoService.watchVideo(id);
            return new ResponseEntity<>(resp, HttpStatus.OK);
        } catch (VideoNotFoundError e) {
            // This needs to be moved to proper error message
            var res = new HashMap<String, String>();
            res.put("message", e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
        }
    }

}
