package com.example.newproject.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.experimental.SuperBuilder;


@Builder
public class CreateVideoResponse {
    @JsonProperty("video_id")
    private String videoId;
}
