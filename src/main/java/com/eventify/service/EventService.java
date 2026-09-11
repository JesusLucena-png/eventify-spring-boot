package com.eventify.service;

import com.eventify.model.Event;
import com.eventify.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * @Service
 *
 * Anotación de Spring que indica que esta clase pertenece
 * a la capa de servicios y contiene la lógica de negocio.
 *
 * También hace que Spring registre EventService como un Bean,
 * permitiendo que pueda ser inyectado en otras clases,
 * por ejemplo, en EventController.
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
@Service
public class EventService {

    /*
     * Dependencia del repositorio.
     *
     * El Service no guarda directamente los eventos.
     * Delega el almacenamiento al EventRepository.
     *
     * final indica que la referencia se establece una sola vez
     * mediante el constructor.
     */
    private final EventRepository eventRepository;

    /*
     * Inyección de dependencias por constructor.
     *
     * Spring detecta el EventRepository y lo entrega automáticamente
     * al crear el EventService.
     *
     * Esta forma cumple con el requisito de usar
     * inyección estricta por constructor.
     */
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    /*
     * Registra un nuevo evento.
     *
     * Aquí se encuentra la lógica de negocio, no en el Controller.
     */
    public Event create(Event event) {

        /*
         * Validamos que el nombre exista y no esté vacío.
         *
         * getNombre() == null:
         * evita intentar utilizar un valor que no existe.
         *
         * isBlank():
         * detecta una cadena vacía o que solo contiene espacios.
         *
         * Si la validación falla, se lanza una excepción
         * y el Repository NO llega a ejecutarse.
         */
        if (event.getNombre() == null || event.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del evento es obligatorio");
        }

        /*
         * Si los datos son válidos, delegamos el almacenamiento
         * al Repository.
         */
        return eventRepository.save(event);

    }

    /*
     * Obtiene todos los eventos registrados.
     *
     * El Service solicita los datos al Repository y los devuelve
     * al Controller.
     */
    public List<Event> finaAll() {

        return eventRepository.findAll();

    }

}
