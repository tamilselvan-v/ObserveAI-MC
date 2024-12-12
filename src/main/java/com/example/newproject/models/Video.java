package com.example.newproject.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Builder
@Getter
@Setter
public class Video {
    private String url;
    private String title;
    private String description;
    private Date uploadDate;

    // Todo: see if we need to store this separately
    private Integer watchCount;
}
