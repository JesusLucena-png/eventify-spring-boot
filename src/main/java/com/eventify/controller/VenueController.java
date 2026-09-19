package com.eventify.controller;

import com.eventify.model.Venue;
import com.eventify.service.VenueService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/venues")
public class VenueController {

    private final VenueService venueService;

    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @GetMapping
    public Page<Venue> listar(Pageable pageable) {
        return venueService.listar(pageable);
    }

    @GetMapping("/{id}")
    public Venue buscarPorId(@PathVariable Long id) {
        return venueService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Venue guardar(@Valid @RequestBody Venue venue) {
        return venueService.guardar(venue);
    }

    @PutMapping("/{id}")
    public Venue actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Venue venue) {

        return venueService.actualizar(id, venue);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        venueService.eliminar(id);
    }
}