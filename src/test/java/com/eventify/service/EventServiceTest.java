package com.eventify.service;

import com.eventify.model.Event;
import com.eventify.repository.EventRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

/*
 * Mockito:
 *
 * Es un framework de Java utilizado para crear objetos simulados
 * (Mocks) durante las pruebas.
 *
 * Permite probar una clase de forma aislada, simulando las
 * dependencias que utiliza, sin ejecutar su implementación real.
 *
 * En este caso, Mockito simula EventRepository para poder probar
 * únicamente la lógica de EventService.
 */

/*
 * @ExtendWith(MockitoExtension.class)
 *
 * Activa la integración de Mockito con JUnit 5.
 *
 * Permite que Mockito inicialice automáticamente los
 * objetos marcados con @Mock y @InjectMocks.
 */
@ExtendWith(MockitoExtension.class)
class EventServiceTest {

    /*
     * @Mock
     *
     * Crea un objeto simulado de EventRepository.
     *
     * No utiliza el Repository real ni su List<Event>.
     * Esto permite probar únicamente la lógica de EventService.
     */
    @Mock
    private EventRepository eventRepository;

    /*
     * @InjectMocks
     *
     * Crea una instancia real de EventService e inyecta
     * automáticamente el Mock de EventRepository.
     *
     * Equivale conceptualmente a:
     *
     * new EventService(eventRepository);
     */
    @InjectMocks
    private EventService eventService;


    /*
     * Verifica que un evento válido pueda ser creado.
     */
    @Test
    void shouldCreateEventWhenDataIsValid() {

        Event event = new Event(
                1L,
                "Tech Conference",
                LocalDateTime.now(),
                "Evento tecnológico"
        );


        /*
         * Configuramos el comportamiento del Mock.
         *
         * Cuando EventService llame a:
         *
         * eventRepository.save(event)
         *
         * Mockito devolverá el mismo evento.
         */
        Mockito.when(eventRepository.save(event)).thenReturn(event);

        /*
         * Ejecutamos el método real del Service.
         */
        Event result = eventService.create(event);

        /*
         * Verificamos que el resultado sea el evento esperado.
         */
        Assertions.assertEquals(event, result);

        /*
         * Verificamos que el Repository haya sido utilizado
         * exactamente para guardar el evento.
         */
        Mockito.verify(eventRepository).save(event);
    }

    /*
     * Verifica que el Service rechace un evento cuyo nombre
     * está vacío.
     */
    @Test
    void shouldRejectEventWhenNameIsEmpty() {

        Event event = new Event(
                1L,
                "",
                LocalDateTime.now(),
                "Evento tecnológico"
        );

        /*
         * assertThrows verifica que el método produzca
         * la excepción indicada.
         */
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> eventService.create(event)
        );

        /*
         * Mockito.never() verifica que save() NO haya sido
         * ejecutado.
         *
         * Esto demuestra que la validación ocurre en el Service
         * antes de llegar al Repository.
         *
         * Mockito.any() significa "cualquier objeto Event".
         */
        Mockito.verify(eventRepository, Mockito.never()).save(Mockito.any());
    }
}