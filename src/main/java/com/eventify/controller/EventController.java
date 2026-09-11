package com.eventify.controller;

import com.eventify.model.Event;
import com.eventify.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * @RestController
 *
 * Indica que esta clase es un controlador REST.
 *
 * Spring registra esta clase como un Bean y permite que
 * reciba peticiones HTTP y devuelva datos directamente,
 * normalmente en formato JSON.
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
@RestController

/*
 * @RequestMapping
 *
 * Define la ruta base para todos los endpoints de este controlador.
 *
 * Por ejemplo:
 * /api/events
 */
@RequestMapping("/api/events")
public class EventController {

    /*
     * Dependencia del servicio.
     *
     * Se utiliza 'final' porque la dependencia debe ser asignada
     * una sola vez cuando se crea el Controller.
     */
    private final EventService eventService;

    /*
     * Inyección de dependencias por constructor.
     *
     * Spring detecta que EventService es un Bean y lo proporciona
     * automáticamente al crear EventController.
     */
    public EventController(EventService eventService) {

        this.eventService = eventService;

    }

    /*
     * @PostMapping
     *
     * Indica que este método responde a peticiones HTTP POST.
     *
     * Como no tiene una ruta adicional, responde a:
     * POST /api/events
     */
    @PostMapping
    public ResponseEntity<Event> create(@RequestBody Event event) {

        /*
         * @RequestBody
         *
         * Indica que Spring debe tomar el JSON recibido en el
         * cuerpo de la petición y convertirlo en un objeto Event.
         */

        Event createdEvent = eventService.create(event);

        /*
         * HTTP 201 CREATED
         *
         * Indica que el recurso fue creado correctamente.
         */
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdEvent);

    }

    /*
     * @GetMapping
     *
     * Indica que este método responde a peticiones HTTP GET.
     *
     * Como no tiene una ruta adicional, responde a:
     * GET /api/events
     */
    @GetMapping
    public ResponseEntity<List<Event>> findAll() {

        /*
         * HTTP 200 OK
         *
         * Devuelve todos los eventos encontrados.
         */
        return ResponseEntity.ok(eventService.finaAll());

    }

}
