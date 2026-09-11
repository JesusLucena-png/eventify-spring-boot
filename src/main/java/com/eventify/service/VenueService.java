package com.eventify.service;

import com.eventify.model.Venue;
import com.eventify.repository.VenueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueService {

    private final VenueRepository venueRepository;

    public VenueService(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    public Venue create(Venue venue) {

        if (venue.getNombre() == null || venue.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del evento es obligatorio");
        }

        if (venue.getCapacidad() <= 0){
            throw new IllegalArgumentException("La capacidad tiene que ser mayora 0");
        }

        return venueRepository.save(venue);

    }

    public List<Venue> finaAll() {

        return venueRepository.findAll();

    }
}
