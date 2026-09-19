package com.eventify.controller;

import com.eventify.model.Event;
import com.eventify.service.EventService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/*
 * @RestController
 *
 * Indica que esta clase es un controlador REST.
 *
 * Spring registra esta clase como un Bean y permite que
 * reciba peticiones HTTP y devuelva datos directamente,
 * normalmente en formato JSON.
 */
@RestController

/*
 * @RequestMapping
 *
 * Define la ruta base para todos los endpoints.
 *
 * Ejemplo:
 *
 * /api/events
 */
@RequestMapping("/api/events")
public class EventController {

    /*
     * Dependencia del servicio.
     *
     * final indica que la referencia se establece
     * mediante el constructor y no cambia posteriormente.
     */
    private final EventService eventService;

    /*
     * Inyección de dependencias por constructor.
     *
     * Spring proporciona automáticamente EventService.
     */
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    /*
     * @GetMapping
     *
     * Obtiene una lista paginada de eventos.
     *
     * Ejemplo:
     *
     * GET /api/events?page=0&size=5&sort=nombre,asc
     *
     * Pageable permite recibir automáticamente:
     *
     * - page
     * - size
     * - sort
     */
    @GetMapping
    public Page<Event> listar(Pageable pageable) {
        return eventService.listar(pageable);
    }

    /*
     * @GetMapping("/{id}")
     *
     * Busca un evento específico utilizando su ID.
     *
     * Ejemplo:
     *
     * GET /api/events/1
     */
    @GetMapping("/{id}")
    public Event buscarPorId(@PathVariable Long id) {
        return eventService.buscarPorId(id);
    }

    /*
     * @PostMapping
     *
     * Crea un nuevo evento.
     *
     * @RequestBody convierte el JSON recibido en un objeto Event.
     *
     * @Valid activa las validaciones definidas en la entidad.
     *
     * HTTP 201 CREATED indica que el recurso fue creado.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Event guardar(@Valid @RequestBody Event event) {
        return eventService.guardar(event);
    }

    /*
     * @PutMapping("/{id}")
     *
     * Actualiza un evento existente.
     *
     * Ejemplo:
     *
     * PUT /api/events/1
     */
    @PutMapping("/{id}")
    public Event actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Event event) {

        return eventService.actualizar(id, event);
    }

    /*
     * @DeleteMapping("/{id}")
     *
     * Elimina un evento existente.
     *
     * HTTP 204 NO CONTENT indica que la eliminación
     * se realizó correctamente y no se devuelve contenido.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        eventService.eliminar(id);
    }
}