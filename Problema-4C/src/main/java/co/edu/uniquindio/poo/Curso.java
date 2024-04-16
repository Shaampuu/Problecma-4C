package co.edu.uniquindio.poo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Registro que agrupa los datos de un Curso
 * 
 * @author Área de programación UQ
 * @since 2024-01
 * 
 *        Licencia GNU/GPL V3.0
 *        (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE)
 */

public class Curso {
    private final String nombre;
    private final Collection<Estudiante> estudiantes;
    private final Collection<ClaseCurso> clases;

    /**
     * Método constructor de la clase Curso
     * 
     * @param nombre Nombre del curso
     */
    public Curso(String nombre) {
        if (nombre == null) {
            throw new IllegalArgumentException("El nombre del curso no puede ser nulo");
        }
        this.nombre = nombre;
        estudiantes = new LinkedList<>();
        clases = new LinkedList<>();
    }

    /**
     * Método para obtener el nombre del curso
     * 
     * @return Nombre del curso
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método para agregar a un estudiante al curso
     * 
     * @param estudiante Estudiante que se desea agregar
     */
    public void agregarEstudiante(Estudiante estudiante) {
        if (validarNumeroIdentificacionExiste(estudiante.getNumeroIdentificacion())) {
            throw new IllegalArgumentException("El número de identificación ya existe.");
        }
        estudiantes.add(estudiante);
    }
 
    /**
     * Método para buscar un estudiante dado el número de indicación
     * 
     * @param numeroIdenficacion Número de identificación del estudiante a buscar
     * @return Estudiante con el número de indicación indicado o null
     */
    public Estudiante getEstudiante(String numeroIdenficacion) {
        Estudiante estudianteInteres = null;

        Estudiante[] estudiantesArray = estudiantes.toArray(new Estudiante[estudiantes.size()]);
        int index = 0;
        while (index < estudiantesArray.length) {
            Estudiante estudiante = estudiantesArray[index];
            if (estudiante.getNumeroIdentificacion().equals(numeroIdenficacion)) {
                estudianteInteres = estudiante;
                break;
            }
            index++;
        }
    
        return estudianteInteres;
    }

    /**
     * Método para obtener la colección NO modificable de los estudiantes del curso
     * 
     * @return la colección NO modificable de los estudiantes del curso
     */
    public Collection<Estudiante> getEstudiantes() {
        List<Estudiante> estudiantesList = new ArrayList<>(estudiantes);
        return estudiantesList;
    }

    /**
     * Método privado para determinar si ya existe un estudiante registro en el
     * mismo número de identificación
     * 
     * @param numeroIdentificacion Número de identificación a buscar
     * @return Valor boolean que indica si el número de identificación ya está o no
     *         registrado.
     */
    private boolean validarNumeroIdentificacionExiste(String numeroIdentificacion) {
        Estudiante[] estudiantesArray = estudiantes.toArray(new Estudiante[estudiantes.size()]);
        boolean existe = false;

        for (int i = 0; i < estudiantesArray.length; i++) {
            if (estudiantesArray[i].getNumeroIdentificacion().equals(numeroIdentificacion)) {
                existe = true;
                break;
            }
        }
        return existe;  
    }

    /**
     * Método para programar (agregar) una clase a un curso.
     *
     * 
     * @param claseCurso claseCurso que se desea programar
     */
    public void programarClase(ClaseCurso claseCurso) {
        boolean existe = false;
        Iterator<ClaseCurso> iterator = clases.iterator();
    
        while (iterator.hasNext()) {
            ClaseCurso clase = iterator.next();
            if (clase.equals(claseCurso)) {
                existe = true;
                break;
            }
        }
    
        if (!existe) {
            clases.add(claseCurso);
        }
    }

    /**
     * Método para obtener la colección NO modificable de los estudiantes del curso
     * 
     * @return la colección NO modificable de las clases del curso
     */
    public Collection<ClaseCurso> getClases() {
        List<ClaseCurso> clasesList = new ArrayList<>();
        Iterator<ClaseCurso> iterator = clases.iterator();
    
        while (iterator.hasNext()) {
            ClaseCurso clase = iterator.next();
            clasesList.add(clase);
        }
    
        return Collections.unmodifiableCollection(clasesList);
    }

    /**
     * Método que obtiene la colección de estudiantes que asistieron a una clase
     * 
     * @param claseCurso la clase de interés
     * @return colección de los estudiantes que asistieron a una clase interés
     */
    public Collection<Estudiante> getAsistentes(ClaseCurso claseCurso) {
    List<Estudiante> asistentes = new ArrayList<>();
    Iterator<Estudiante> iterator = estudiantes.iterator();

    while (iterator.hasNext()) {
        Estudiante estudiante = iterator.next();
        if (estudiante.asistioClase(claseCurso)) {
            asistentes.add(estudiante);
        }
    }

    return asistentes;
    }

    /**
     * Método que obtiene la colección de estudiantes que estaban ausentes a una
     * clase
     * 
     * @param claseCurso la clase de interés
     * @return colección de los estudiantes que estuvieron ausentes a una clase
     *         interés
     */
    public Collection<Estudiante> getAusentes(ClaseCurso claseCurso) {
    List<Estudiante> ausentes = new ArrayList<>();
    Iterator<Estudiante> iterator = estudiantes.iterator();

    while (iterator.hasNext()) {
        Estudiante estudiante = iterator.next();
        if (!estudiante.asistioClase(claseCurso)) {
            ausentes.add(estudiante);
        }
    }

    return ausentes;
    }


    public double calcularPorcentajeAsistencia(ClaseCurso claseCurso) {
        int cantidadEstudiantes = estudiantes.size();
        int cantidadAsistentes = 0;
        Iterator<Estudiante> iterator = estudiantes.iterator();
    
        while (iterator.hasNext()) {
            Estudiante estudiante = iterator.next();
            if (estudiante.asistioClase(claseCurso)) {
                cantidadAsistentes++;
            }
        }
    
        return (double) cantidadAsistentes / cantidadEstudiantes;
    }

}
