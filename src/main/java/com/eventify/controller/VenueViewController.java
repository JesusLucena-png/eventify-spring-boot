package com.eventify.controller;

import com.eventify.model.Venue;
import com.eventify.service.VenueService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/venues")
public class VenueViewController {

    private final VenueService venueService;

    public VenueViewController(VenueService venueService) {
        this.venueService = venueService;
    }

    @GetMapping
    public String listar(Model model) {

        Page<Venue> venues = venueService.listar(
                PageRequest.of(0, 50)
        );

        model.addAttribute("venues", venues.getContent());

        return "admin/venues";
    }

    @GetMapping("/new")
    public String mostrarFormulario(Model model) {

        model.addAttribute("venue", new Venue());

        return "admin/venue-form";
    }

    @PostMapping
    public String guardar(@ModelAttribute Venue venue) {

        venueService.guardar(venue);

        return "redirect:/admin/venues";
    }
}
