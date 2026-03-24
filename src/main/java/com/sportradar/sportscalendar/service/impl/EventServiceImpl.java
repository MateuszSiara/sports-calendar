package com.sportradar.sportscalendar.service.impl;

import com.sportradar.sportscalendar.dto.CreateEventRequest;
import com.sportradar.sportscalendar.dto.EventDto;
import com.sportradar.sportscalendar.model.*;
import com.sportradar.sportscalendar.repository.*;
import com.sportradar.sportscalendar.service.EventService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final CompetitionRepository competitionRepository;
    private final TeamRepository teamRepository;
    private final StageRepository stageRepository;

    @Override
    public List<EventDto> getFilteredEvents(String status, LocalDate date) {
        String statusParam = (status != null && !status.isEmpty()) ? status : null;

        List<Event> events;
        if (statusParam == null && date == null) {
            events = eventRepository.findAllWithDetails();
        } else if (statusParam != null && date == null) {
            events = eventRepository.findByStatus(statusParam);
        } else if (statusParam == null) {
            events = eventRepository.findByDate(date);
        } else {
            events = eventRepository.findAllWithFilters(statusParam, date);
        }
        return events.stream().map(this::toDto).collect(Collectors.toList());
    }
    @Override
    public List<EventDto> getAllEvents() {
        return eventRepository.findAllWithDetails()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EventDto getEventById(Integer id) {
        Event event = eventRepository.findByIdWithDetails(id)
                .orElseThrow(() -> new EntityNotFoundException("Event not found: " + id));
        return toDto(event);
    }

    @Override
    @Transactional
    public EventDto createEvent(CreateEventRequest request) {
        Event event = new Event();
        event.setSeasonYear(request.getSeasonYear());
        event.setStatus(request.getStatus());
        event.setDateVenue(request.getDateVenue());
        event.setTimeVenueUtc(request.getTimeVenueUtc());
        event.setEventGroup(request.getEventGroup());

        Competition competition = competitionRepository.findById(request.getCompetitionId())
                .orElseThrow(() -> new EntityNotFoundException("Competition not found"));
        event.setCompetition(competition);

        Stage stage = stageRepository.findById(request.getStageId())
                .orElseThrow(() -> new EntityNotFoundException("Stage not found"));
        event.setStage(stage);

        if (request.getHomeTeamId() != null) {
            event.setHomeTeam(teamRepository.findById(request.getHomeTeamId())
                    .orElseThrow(() -> new EntityNotFoundException("Home team not found")));
        }

        if (request.getAwayTeamId() != null) {
            event.setAwayTeam(teamRepository.findById(request.getAwayTeamId())
                    .orElseThrow(() -> new EntityNotFoundException("Away team not found")));
        }

        Event saved = eventRepository.save(event);
        return toDto(saved);
    }

    private EventDto toDto(Event event) {
        EventDto dto = new EventDto();
        dto.setId(event.getId());
        dto.setSeasonYear(event.getSeasonYear());
        dto.setStatus(event.getStatus());
        dto.setDateVenue(event.getDateVenue());
        dto.setTimeVenueUtc(event.getTimeVenueUtc());
        dto.setEventGroup(event.getEventGroup());
        dto.setSportName(event.getCompetition().getSport().getName());
        dto.setCompetitionName(event.getCompetition().getOriginCompetitionName());
        dto.setStageName(event.getStage().getName());

        if (event.getStadium() != null) {
            dto.setStadiumName(event.getStadium().getName());
        }
        if (event.getHomeTeam() != null) {
            dto.setHomeTeamName(event.getHomeTeam().getName());
        }
        if (event.getAwayTeam() != null) {
            dto.setAwayTeamName(event.getAwayTeam().getName());
        }
        if (event.getResult() != null) {
            dto.setHomeGoals(event.getResult().getHomeGoals());
            dto.setAwayGoals(event.getResult().getAwayGoals());
            dto.setWinner(event.getResult().getWinner());
        }

        return dto;
    }
}