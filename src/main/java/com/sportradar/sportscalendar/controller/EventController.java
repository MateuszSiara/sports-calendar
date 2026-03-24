package com.sportradar.sportscalendar.controller;

import com.sportradar.sportscalendar.dto.CreateEventRequest;
import com.sportradar.sportscalendar.dto.EventDto;
import com.sportradar.sportscalendar.model.Competition;
import com.sportradar.sportscalendar.model.Stage;
import com.sportradar.sportscalendar.model.Team;
import com.sportradar.sportscalendar.repository.CompetitionRepository;
import com.sportradar.sportscalendar.repository.StageRepository;
import com.sportradar.sportscalendar.repository.TeamRepository;
import com.sportradar.sportscalendar.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;
    private final CompetitionRepository competitionRepository;
    private final StageRepository stageRepository;
    private final TeamRepository teamRepository;

    @GetMapping
    public String listEvents(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            Model model) {

        List<EventDto> events = eventService.getFilteredEvents(status, date);
        model.addAttribute("events", events);
        model.addAttribute("selectedStatus", status);
        model.addAttribute("selectedDate", date);
        return "events/list";
    }

    //szczegóły jednego eventu
    @GetMapping("/{id}")
    public String eventDetail(@PathVariable Integer id, Model model) {
        EventDto event = eventService.getEventById(id);
        model.addAttribute("event", event);
        return "events/detail";
    }

    //formularz dodawania
    @GetMapping("/new")
    public String newEventForm(Model model) {
        List<Competition> competitions = competitionRepository.findAll();
        List<Stage> stages = stageRepository.findAll();
        List<Team> teams = teamRepository.findAll();

        model.addAttribute("request", new CreateEventRequest());
        model.addAttribute("competitions", competitions);
        model.addAttribute("stages", stages);
        model.addAttribute("teams", teams);
        return "events/form";
    }

    //zapisanie nowego eventu
    @PostMapping
    public String createEvent(@ModelAttribute CreateEventRequest request) {
        eventService.createEvent(request);
        return "redirect:/events";
    }
}