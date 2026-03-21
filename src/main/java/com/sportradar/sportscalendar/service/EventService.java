package com.sportradar.sportscalendar.service;

import com.sportradar.sportscalendar.dto.CreateEventRequest;
import com.sportradar.sportscalendar.dto.EventDto;
import java.util.List;

public interface EventService {

    List<EventDto> getAllEvents();

    EventDto getEventById(Integer id);

    EventDto createEvent(CreateEventRequest request);
}