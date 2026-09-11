package com.eventify.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/*
 * @Getter y @Setter:
 *
 * Son anotaciones de Lombok.
 * Lombok genera automáticamente los métodos get y set
 * de todos los atributos de esta clase.
 *
 * Por ejemplo, para:
 *
 * private String nombre;
 *
 * Lombok genera automáticamente:
 *
 * public String getNombre() {
 *     return nombre;
 * }
 *
 * public void setNombre(String nombre) {
 *     this.nombre = nombre;
 * }
 *
 * Esto evita tener que escribir manualmente todos los getters y setters.
 */
@Setter
@Getter

/*
 * @NoArgsConstructor:
 *
 * Lombok genera un constructor vacío, es decir:
 *
 * public Event() {
 * }
 *
 * Es útil cuando necesitamos crear un objeto sin proporcionar
 * los valores inmediatamente.
 *
 * Ejemplo:
 *
 * Event event = new Event();
 * event.setNombre("Tech Conference");
 */
@NoArgsConstructor

/*
 * @AllArgsConstructor:
 *
 * Lombok genera un constructor que recibe todos los atributos
 * de la clase en el mismo orden en que están declarados.
 *
 * Es equivalente a:
 *
 * public Event(Long id, String nombre, LocalDateTime fecha,
 *              String descripcion) {
 *     this.id = id;
 *     this.nombre = nombre;
 *     this.fecha = fecha;
 *     this.descripcion = descripcion;
 * }
 *
 * Entonces podemos crear un Event directamente:
 *
 * Event event = new Event(
 *     1L,
 *     "Tech Conference",
 *     LocalDateTime.now(),
 *     "Evento tecnológico"
 * );
 */
@AllArgsConstructor

public class Event {

    private Long id;
    private String nombre;
    private LocalDateTime fecha;
    private String descripcion;

}
