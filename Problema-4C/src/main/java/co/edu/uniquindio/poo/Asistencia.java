package co.edu.uniquindio.poo;

/**
 * Registro que agrupa los datos de una asistencia a una clase
 * 
 * @author Área de programación UQ
 * @since 2024-01
 * 
 *        Licencia GNU/GPL V3.0
 *        (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE)
 */
public record Asistencia(ClaseCurso claseCurso, TipoAsistencia tipoAsistencia) {

    public Asistencia {
        if (claseCurso == null) {
            throw new IllegalArgumentException("La clase de curso no puede ser nula");
        }
    }
    
}