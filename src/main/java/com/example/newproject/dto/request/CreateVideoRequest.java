package com.example.newproject.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import java.util.Date;

@Getter
@Setter
@Builder
public class CreateVideoRequest {

    @JsonProperty("url")
    @URL
    @NotBlank
    private String url;

    @Size(min=3, max=200)
    @NotBlank
    @JsonProperty("title")
    private String title;

    @Size(min = 5, max = 500)
    @NotBlank
    @JsonProperty("description")
    private String description;

}
