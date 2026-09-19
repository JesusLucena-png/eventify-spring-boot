package com.eventify.service;

import com.eventify.model.Event;
import com.eventify.repository.EventRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/*
 * @Service
 *
 * Indica que esta clase pertenece a la capa de servicios
 * y contiene la lógica de negocio de Eventify.
 *
 * Spring registra EventService como un Bean y permite
 * inyectarlo en otras clases, como EventController.
 */
@Service
public class EventService {

    /*
     * Dependencia del repositorio.
     *
     * El Service no se encarga directamente de almacenar
     * los eventos. Delega esta responsabilidad al repositorio.
     */
    private final EventRepository eventRepository;

    /*
     * Inyección de dependencias por constructor.
     *
     * Spring proporciona automáticamente el EventRepository
     * cuando crea una instancia de EventService.
     */
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    /*
     * Listar eventos.
     *
     * Pageable permite controlar:
     *
     * - Número de página.
     * - Cantidad de elementos por página.
     * - Ordenamiento.
     *
     * Ejemplo:
     *
     * ?page=0&size=5&sort=nombre,asc
     */
    public Page<Event> listar(Pageable pageable) {
        return eventRepository.findAll(pageable);
    }

    /*
     * Buscar un evento por su ID.
     *
     * Si el evento existe, se devuelve.
     *
     * Si no existe, se lanza una excepción para posteriormente
     * convertirla en una respuesta HTTP 404.
     */
    public Event buscarPorId(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Evento con ID " + id + " no encontrado"));
    }

    /*
     * Crear un nuevo evento.
     *
     * JpaRepository se encarga de guardar el objeto
     * dentro de la base de datos.
     */
    public Event guardar(Event event) {
        return eventRepository.save(event);
    }

    /*
     * Actualizar un evento existente.
     *
     * Primero se busca el evento para comprobar que exista.
     * Si no existe, buscarPorId() genera el error.
     *
     * Después se actualizan sus datos y se guarda nuevamente.
     */
    public Event actualizar(Long id, Event event) {

        Event eventoExistente = buscarPorId(id);

        eventoExistente.setNombre(event.getNombre());
        eventoExistente.setFecha(event.getFecha());
        eventoExistente.setDescripcion(event.getDescripcion());

        return eventRepository.save(eventoExistente);
    }

    /*
     * Eliminar un evento.
     *
     * Primero comprobamos que exista.
     * De esta manera, un ID inexistente puede generar
     * posteriormente una respuesta HTTP 404.
     */
    public void eliminar(Long id) {

        buscarPorId(id);

        eventRepository.deleteById(id);
    }
}