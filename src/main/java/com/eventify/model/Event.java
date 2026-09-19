package com.eventify.model;

import jakarta.persistence.*;
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

/*
 * @Entity:
 *
 * Indica que esta clase es una entidad de JPA.
 * Esto significa que la clase representa una tabla
 * dentro de la base de datos.
 *
 * JPA utilizará esta clase para guardar, consultar,
 * actualizar y eliminar registros de la tabla.
 *
 * En este caso, cada objeto Event representa un registro
 * de la tabla "events".
 */
@Entity

/*
 * @Table:
 *
 * Indica el nombre de la tabla que será utilizada
 * en la base de datos para esta entidad.
 *
 * En este caso:
 *
 * @Table(name = "events")
 *
 * significa que la clase Event estará relacionada
 * con la tabla "events".
 */
@Table(name = "events")
public class Event {

    /*
     * @Id:
     *
     * Indica que este atributo es la clave primaria
     * (Primary Key) de la tabla.
     *
     * La clave primaria permite identificar de forma
     * única cada registro dentro de la tabla.
     *
     * En este caso, el atributo "id" identifica
     * de forma única cada evento.
     */
    @Id
    /*
     * @GeneratedValue:
     *
     * Indica que el valor del ID será generado
     * automáticamente por la base de datos.
     *
     * GenerationType.IDENTITY utiliza la estrategia
     * de identidad de la base de datos para generar
     * automáticamente valores consecutivos para el ID.
     *
     * Por ejemplo:
     *
     * Primer evento  -> id = 1
     * Segundo evento -> id = 2
     * Tercer evento  -> id = 3
     *
     * Por esta razón, normalmente no necesitamos
     * enviar el ID cuando creamos un nuevo evento.
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * @Column:
     *
     * Permite configurar cómo será almacenado un atributo
     * dentro de la columna correspondiente de la base de datos.
     *
     * nullable = false:
     * Indica que la columna no puede aceptar valores NULL.
     *
     * length = 100:
     * Establece que la columna tendrá una longitud máxima
     * de 100 caracteres.
     *
     * Por lo tanto, el nombre del evento es obligatorio
     * y puede tener hasta 100 caracteres.
     */
    @Column(nullable = false, length = 100)
    private String nombre;

    private LocalDateTime fecha;

    private String descripcion;

}
