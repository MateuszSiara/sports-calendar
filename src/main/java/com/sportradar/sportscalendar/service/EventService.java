package com.sportradar.sportscalendar.service;

import com.sportradar.sportscalendar.dto.CreateEventRequest;
import com.sportradar.sportscalendar.dto.EventDto;
import java.time.LocalDate;
import java.util.List;

public interface EventService {

    List<EventDto> getAllEvents();
    List<EventDto> getFilteredEvents(String status, LocalDate date);

    EventDto getEventById(Integer id);

    EventDto createEvent(CreateEventRequest request);
}