package com.myApp.web.response;

import com.myApp.web.model.Event;
import lombok.Data;

@Data
public class EventCreateFormResponse {
    private Long clubId;
    private Event event;
}
