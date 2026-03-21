package com.sportradar.sportscalendar.controller;

import com.sportradar.sportscalendar.dto.EventDto;
import com.sportradar.sportscalendar.repository.CompetitionRepository;
import com.sportradar.sportscalendar.repository.StageRepository;
import com.sportradar.sportscalendar.repository.TeamRepository;
import com.sportradar.sportscalendar.service.EventService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EventController.class)
class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private EventService eventService;

    @MockitoBean
    private CompetitionRepository competitionRepository;

    @MockitoBean
    private StageRepository stageRepository;

    @MockitoBean
    private TeamRepository teamRepository;

    @Test
    void listEvents_shouldReturn200_andEventsView() throws Exception {
        when(eventService.getAllEvents()).thenReturn(List.of());

        mockMvc.perform(get("/events"))
                .andExpect(status().isOk())
                .andExpect(view().name("events/list"))
                .andExpect(model().attributeExists("events"));
    }

    @Test
    void listEvents_shouldReturnEvents_inModel() throws Exception {
        EventDto dto = new EventDto();
        dto.setId(1);
        dto.setHomeTeamName("Al Shabab");
        dto.setAwayTeamName("Nasaf");
        dto.setStatus("played");
        dto.setDateVenue(LocalDate.of(2024, 1, 3));
        dto.setTimeVenueUtc(LocalTime.of(0, 0));
        dto.setSeasonYear(2024);
        dto.setCompetitionName("AFC Champions League");
        dto.setStageName("ROUND OF 16");

        when(eventService.getAllEvents()).thenReturn(List.of(dto));

        mockMvc.perform(get("/events"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("events", List.of(dto)));
    }

    @Test
    void eventDetail_shouldReturn200_andDetailView() throws Exception {
        EventDto dto = new EventDto();
        dto.setId(1);
        dto.setHomeTeamName("Al Shabab");
        dto.setAwayTeamName("Nasaf");
        dto.setStatus("played");
        dto.setDateVenue(LocalDate.of(2024, 1, 3));
        dto.setTimeVenueUtc(LocalTime.of(0, 0));
        dto.setSeasonYear(2024);
        dto.setCompetitionName("AFC Champions League");
        dto.setStageName("ROUND OF 16");

        when(eventService.getEventById(1)).thenReturn(dto);

        mockMvc.perform(get("/events/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("events/detail"))
                .andExpect(model().attributeExists("event"));
    }

    @Test
    void newEventForm_shouldReturn200_andFormView() throws Exception {
        when(competitionRepository.findAll()).thenReturn(List.of());
        when(stageRepository.findAll()).thenReturn(List.of());
        when(teamRepository.findAll()).thenReturn(List.of());

        mockMvc.perform(get("/events/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("events/form"))
                .andExpect(model().attributeExists("request"))
                .andExpect(model().attributeExists("competitions"))
                .andExpect(model().attributeExists("stages"))
                .andExpect(model().attributeExists("teams"));
    }
}