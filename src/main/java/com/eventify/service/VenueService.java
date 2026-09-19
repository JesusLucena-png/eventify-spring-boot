package com.eventify.service;

import com.eventify.model.Venue;
import com.eventify.repository.VenueRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VenueService {

    private final VenueRepository venueRepository;

    public VenueService(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    public Page<Venue> listar(Pageable pageable) {
        return venueRepository.findAll(pageable);
    }

    public Venue buscarPorId(Long id) {
        return venueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("id no encontrado"));
    }

    public Venue actualizar(Long id, Venue venue) {

        Venue venueExistente = buscarPorId(id);

        venueExistente.setNombre(venue.getNombre());
        venueExistente.setDireccion(venue.getDireccion());
        venueExistente.setCapacidad(venue.getCapacidad());

        return venueRepository.save(venueExistente);
    }

    public Venue guardar(Venue venue) {
        return venueRepository.save(venue);
    }

    public void eliminar(Long id) {
        buscarPorId(id);
        venueRepository.deleteById(id);
    }
}