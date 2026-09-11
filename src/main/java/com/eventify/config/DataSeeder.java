package com.eventify.config;

import com.eventify.model.Event;
import com.eventify.model.Venue;
import com.eventify.repository.EventRepository;
import com.eventify.repository.VenueRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;

/*
 * @Profile("seed")
 *
 * Indica que esta configuración solamente estará activa
 * cuando el perfil "seed" esté habilitado.
 *
 * Esto permite ejecutar el Seeder únicamente cuando
 * queramos cargar datos iniciales.
 */
@Profile("seed")

/*
 * @Configuration
 *
 * Indica que esta clase contiene configuración de Spring.
 *
 * Spring detecta esta clase y procesa los métodos que
 * estén marcados con @Bean.
 *
 * Bean:
 *
 * Un Bean es un objeto que es creado y administrado
 * por el contenedor de Spring.
 *
 * Spring se encarga de crear el objeto, mantenerlo
 * disponible y proporcionar sus dependencias cuando
 * otra clase las necesita.
 */
@Configuration
public class DataSeeder {

    /*
     * @Bean
     *
     * Indica que el objeto retornado por este método será
     * registrado y administrado por Spring como un Bean.
     *
     * En este caso, el Bean será un CommandLineRunner.
     *
     * Spring también proporciona automáticamente los
     * Repository que recibe este método como parámetros.
     */
    @Bean
    CommandLineRunner seedData(
            EventRepository eventRepository,
            VenueRepository venueRepository){

        /*
         * CommandLineRunner permite ejecutar código
         * automáticamente una vez que Spring Boot
         * termina de iniciar la aplicación.
         *
         * args contiene los argumentos enviados al iniciar
         * la aplicación.
         */
        return  args -> {

            /*
             * Carga un Venue inicial en el Repository.
             */
            venueRepository.save(
                    new Venue(
                            1L,
                            "Centro de Convenciones",
                            "Carrera 50 #80-90",
                            500
                    )
            );

            /*
             * Carga un Event inicial en el Repository.
             */
            eventRepository.save(
                    new Event(
                            1L,
                            "Tech Conference 2026",
                            LocalDateTime.of(2026, 10, 15, 9, 0),
                            "Conferencia sobre tecnología y desarrollo de software"
                    )
            );
        };
    }
}
