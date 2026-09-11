package com.eventify.service;

import com.eventify.model.Venue;
import com.eventify.repository.VenueRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class VenueServiceTest {

    @Mock
    private VenueRepository venueRepository;

    @InjectMocks
    private VenueService venueService;

    @Test
    void shouldCreateVenueWhenDataIsValid() {

        Venue venue = new Venue(
                1L,
                "Centro de Convenciones",
                "Carrera 50 #80-90",
                500
        );

        Mockito.when(venueRepository.save(venue)).thenReturn(venue);

        Venue result = venueService.create(venue);

        Assertions.assertEquals(venue, result);

        Mockito.verify(venueRepository).save(venue);
    }

    @Test
    void shouldRejectVenueWhenNameIsEmpty() {

        Venue venue = new Venue(
                1L,
                "",
                "Carrera 50 #80-90",
                500
        );

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> venueService.create(venue)
        );

        Mockito.verify(
                venueRepository,
                Mockito.never()
        ).save(Mockito.any());
    }
}