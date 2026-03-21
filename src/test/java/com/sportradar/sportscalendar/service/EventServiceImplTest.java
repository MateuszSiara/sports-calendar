package com.sportradar.sportscalendar.service;

import com.sportradar.sportscalendar.dto.EventDto;
import com.sportradar.sportscalendar.model.*;
import com.sportradar.sportscalendar.repository.EventRepository;
import com.sportradar.sportscalendar.repository.CompetitionRepository;
import com.sportradar.sportscalendar.repository.TeamRepository;
import com.sportradar.sportscalendar.repository.StageRepository;
import com.sportradar.sportscalendar.service.impl.EventServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventServiceImplTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private CompetitionRepository competitionRepository;

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private StageRepository stageRepository;

    @InjectMocks
    private EventServiceImpl eventService;

    private Event testEvent;

    @BeforeEach
    void setUp() {
        Sport sport = new Sport();
        sport.setId(1);
        sport.setName("Football");

        Competition competition = new Competition();
        competition.setId(1);
        competition.setOriginCompetitionName("AFC Champions League");
        competition.setSport(sport);

        Stage stage = new Stage();
        stage.setId(1);
        stage.setName("ROUND OF 16");
        stage.setStageCode("ROUND OF 16");
        stage.setOrdering(4);

        Team homeTeam = new Team();
        homeTeam.setId(1);
        homeTeam.setName("Al Shabab");
        homeTeam.setOfficialName("Al Shabab FC");
        homeTeam.setSlug("al-shabab-fc");
        homeTeam.setAbbreviation("SHA");
        homeTeam.setTeamCountryCode("KSA");

        Team awayTeam = new Team();
        awayTeam.setId(2);
        awayTeam.setName("Nasaf");
        awayTeam.setOfficialName("FC Nasaf");
        awayTeam.setSlug("fc-nasaf-qarshi");
        awayTeam.setAbbreviation("NAS");
        awayTeam.setTeamCountryCode("UZB");

        testEvent = new Event();
        testEvent.setId(1);
        testEvent.setSeasonYear(2024);
        testEvent.setStatus("played");
        testEvent.setDateVenue(LocalDate.of(2024, 1, 3));
        testEvent.setTimeVenueUtc(LocalTime.of(0, 0));
        testEvent.setCompetition(competition);
        testEvent.setStage(stage);
        testEvent.setHomeTeam(homeTeam);
        testEvent.setAwayTeam(awayTeam);
    }

    @Test
    void getAllEvents_shouldReturnListOfEventDtos() {
        when(eventRepository.findAllWithDetails()).thenReturn(List.of(testEvent));

        List<EventDto> result = eventService.getAllEvents();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getHomeTeamName()).isEqualTo("Al Shabab");
        assertThat(result.get(0).getAwayTeamName()).isEqualTo("Nasaf");
        assertThat(result.get(0).getStatus()).isEqualTo("played");
    }

    @Test
    void getAllEvents_shouldReturnEmptyList_whenNoEvents() {
        when(eventRepository.findAllWithDetails()).thenReturn(List.of());

        List<EventDto> result = eventService.getAllEvents();

        assertThat(result).isEmpty();
    }

    @Test
    void getEventById_shouldReturnEventDto_whenExists() {
        when(eventRepository.findByIdWithDetails(1)).thenReturn(Optional.of(testEvent));

        EventDto result = eventService.getEventById(1);

        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getSeasonYear()).isEqualTo(2024);
        assertThat(result.getCompetitionName()).isEqualTo("AFC Champions League");
    }

    @Test
    void getEventById_shouldThrowException_whenNotFound() {
        when(eventRepository.findByIdWithDetails(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> eventService.getEventById(99))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Event not found: 99");
    }

    @Test
    void getAllEvents_shouldHandleEventWithNullHomeTeam() {
        testEvent.setHomeTeam(null);
        when(eventRepository.findAllWithDetails()).thenReturn(List.of(testEvent));

        List<EventDto> result = eventService.getAllEvents();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getHomeTeamName()).isNull();
    }

    @Test
    void getAllEvents_shouldHandleEventWithResult() {
        Result result = new Result();
        result.setHomeGoals(1);
        result.setAwayGoals(2);
        result.setWinner("Nasaf");
        testEvent.setResult(result);

        when(eventRepository.findAllWithDetails()).thenReturn(List.of(testEvent));

        List<EventDto> dtos = eventService.getAllEvents();

        assertThat(dtos.get(0).getHomeGoals()).isEqualTo(1);
        assertThat(dtos.get(0).getAwayGoals()).isEqualTo(2);
        assertThat(dtos.get(0).getWinner()).isEqualTo("Nasaf");
    }
}