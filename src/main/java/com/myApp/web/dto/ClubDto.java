package com.myApp.web.dto;

import com.myApp.web.model.UserEntity;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ClubDto {
    private Long id;
    @NotEmpty(message="Club title should not be empty")
    private String title;
    @NotEmpty(message="Photo Url should not be empty")
    @Size(max = 255, message = "Photo Url too long")
    private String photoUrl;
    @Size(max = 255, message = "Content too long")
    @NotEmpty(message="Content should not be empty")
    private String content;
    @NotEmpty(message="City should not be empty")
    private String city;
    private UserEntity createdBy;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private List<EventDto> events;
}
