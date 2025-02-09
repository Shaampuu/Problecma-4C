package co.edu.uniquindio.poo;

import java.time.LocalDateTime;


/**
 * Registro que agrupa los datos de una clase de un curso
 * 
 * @author Área de programación UQ
 * @since 2024-01
 * 
 *        Licencia GNU/GPL V3.0
 *        (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE)
 */
public record ClaseCurso(LocalDateTime fechaClase) {

    public ClaseCurso(LocalDateTime fechaClase) {
        if (fechaClase == null) {
            throw new IllegalArgumentException("La fecha de la clase no puede ser nula");
        }
        this.fechaClase = fechaClase;
    }
} 
