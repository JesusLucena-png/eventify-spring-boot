package com.eventify.controller;

import com.eventify.model.Event;
import com.eventify.service.EventService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/events")
public class EventViewController {

    private final EventService eventService;

    public EventViewController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public String listar(Model model) {

        Page<Event> eventos = eventService.listar(
                PageRequest.of(0, 50)
        );

        model.addAttribute("eventos", eventos.getContent());

        return "admin/events";
    }

    @GetMapping("/new")
    public String mostrarFormulario(Model model) {

        model.addAttribute("event", new Event());

        return "admin/event-form";
    }

    @PostMapping
    public String guardar(@ModelAttribute Event event) {

        eventService.guardar(event);

        return "redirect:/admin/events";
    }
}