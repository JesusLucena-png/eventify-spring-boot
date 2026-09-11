package com.eventify.repository;

import com.eventify.model.Event;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/*
 * @Repository:
 *
 * Es una anotación de Spring que indica que esta clase pertenece
 * a la capa de acceso a datos.
 *
 * En este proyecto no usamos una base de datos todavía.
 * Por eso, el Repository funciona como una base de datos temporal
 * utilizando una lista en memoria.
 *
 * Spring también registra esta clase como un Bean, por lo que
 * podemos inyectarla mediante el constructor en EventService.
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
@Repository
public class EventRepository {

    /*
     * Lista que funciona como almacenamiento temporal de eventos.
     *
     * ArrayList permite agregar y almacenar objetos Event en memoria.
     *
     * Ejemplo:
     *
     * events.add(event);
     *
     * sería equivalente a guardar el evento en nuestra "base de datos"
     * temporal.
     */
    private final List<Event> events = new ArrayList<>();

    /*
     * Guarda un evento en la lista.
     *
     * add() agrega el objeto Event al final de la lista.
     *
     * Ejemplo:
     *
     * Event event = new Event(...);
     *
     * events.add(event);
     *
     * Después de ejecutar save(), el evento queda almacenado
     * temporalmente en memoria.
     */
    public Event save(Event event) {
        events.add(event);
        return  event;
    }

    /*
     * Obtiene todos los eventos almacenados.
     *
     * List.copyOf(events) crea una copia no modificable de la lista.
     *
     * Esto evita que otra parte de la aplicación pueda modificar
     * directamente nuestra lista interna.
     *
     * En lugar de entregar directamente:
     *
     * return events;
     *
     * usamos:
     *
     * return List.copyOf(events);
     */
    public List<Event> findAll() {
        return List.copyOf(events);
    }

}
