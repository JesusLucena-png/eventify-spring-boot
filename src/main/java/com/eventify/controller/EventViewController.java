package com.eventify.controller;

import com.eventify.model.Event;
import com.eventify.service.EventService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/*
 * @Controller
 *
 * Indica que esta clase es un controlador de Spring MVC.
 *
 * A diferencia de @RestController, este controlador no devuelve
 * directamente datos JSON.
 *
 * Su función es procesar las peticiones del administrador,
 * preparar los datos y devolver una vista HTML mediante Thymeleaf.
 */
@Controller

/*
 * @RequestMapping
 *
 * Define la ruta base para las vistas administrativas
 * relacionadas con los eventos.
 *
 * Ejemplo:
 *
 * /admin/events
 */
@RequestMapping("/admin/events")
public class EventViewController {

    /*
     * Dependencia del servicio.
     *
     * EventService contiene la lógica necesaria para
     * consultar y guardar los eventos.
     */
    private final EventService eventService;

    /*
     * Inyección de dependencias por constructor.
     *
     * Spring proporciona automáticamente una instancia
     * de EventService.
     */
    public EventViewController(EventService eventService) {
        this.eventService = eventService;
    }

    /*
     * @GetMapping
     *
     * Atiende las peticiones GET realizadas a:
     *
     * GET /admin/events
     *
     * Obtiene los eventos desde el servicio y los envía
     * a la vista HTML.
     */
    @GetMapping
    public String listar(Model model) {

        /*
         * Se solicita la primera página de resultados.
         *
         * PageRequest.of(0, 50) significa:
         *
         * - Página: 0
         * - Cantidad de elementos: 50
         *
         * El resultado se almacena en un objeto Page.
         */
        Page<Event> eventos = eventService.listar(
                PageRequest.of(0, 50)
        );

        /*
         * model.addAttribute()
         *
         * Envía información desde el controlador hacia
         * la vista Thymeleaf.
         *
         * "eventos" será el nombre utilizado dentro
         * del archivo events.html.
         *
         * eventos.getContent() obtiene únicamente la lista
         * de eventos de la página actual.
         */
        model.addAttribute("eventos", eventos.getContent());

        /*
         * Devuelve el nombre de la vista que debe renderizarse.
         *
         * Spring buscará:
         *
         * src/main/resources/templates/admin/events.html
         */
        return "admin/events";
    }

    /*
     * @GetMapping("/new")
     *
     * Atiende la petición:
     *
     * GET /admin/events/new
     *
     * Su función es mostrar el formulario para registrar
     * un nuevo evento.
     */
    @GetMapping("/new")
    public String mostrarFormulario(Model model) {

        /*
         * Se crea un objeto Event vacío.
         *
         * Thymeleaf utilizará este objeto para enlazar
         * los campos del formulario mediante:
         *
         * th:object="${event}"
         *
         * y:
         *
         * th:field="*{nombre}"
         */
        model.addAttribute("event", new Event());

        /*
         * Devuelve la vista del formulario.
         *
         * Spring buscará:
         *
         * src/main/resources/templates/admin/event-form.html
         */
        return "admin/event-form";
    }

    /*
     * @PostMapping
     *
     * Atiende las peticiones POST realizadas a:
     *
     * POST /admin/events
     *
     * Se utiliza cuando el usuario envía el formulario
     * para registrar un nuevo evento.
     */
    @PostMapping
    public String guardar(@ModelAttribute Event event) {

        /*
         * @ModelAttribute
         *
         * Spring toma los datos enviados por el formulario
         * y los convierte en un objeto Event.
         *
         * Por ejemplo:
         *
         * nombre      → event.nombre
         * fecha       → event.fecha
         * direccion   → event.direccion
         * descripcion → event.descripcion
         */

        /*
         * Se envía el evento al servicio para guardarlo
         * en la base de datos.
         */
        eventService.guardar(event);

        /*
         * Después de guardar correctamente el evento,
         * se redirige al listado.
         *
         * Esto evita volver a enviar el formulario si el
         * usuario actualiza la página.
         */
        return "redirect:/admin/events";
    }
}