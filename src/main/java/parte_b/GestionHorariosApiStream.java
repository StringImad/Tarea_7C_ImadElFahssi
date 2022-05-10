/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parte_b;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import parte_a.HorarioProfesorado;
import parte_a.ServiciosNecesarios;

/**
 *
 * @author MSI
 */
public class GestionHorariosApiStream {

    public static void main(String[] args) {
        ArrayList<HorarioProfesorado> listaHorarios = ServiciosNecesarios.leerFicheroScanner("hor_curso_1920_final.csv");
        List<HorarioProfesorado> lista1EsoA = listaHorarios;
        // Obtener todos los registros de 1ESOA que no son de la asignatura MUS.
        registros1ESOA(lista1EsoA);

        List<HorarioProfesorado> horasProg = listaHorarios;

        System.out.println("-------------------------------------------------------");
        System.out.println("Horas de programacion: " + contadorHorasProgram(horasProg));

        List<HorarioProfesorado> listaIniciales = listaHorarios;
        System.out.println("-----------Lista de profesores que imparten la asignatura REL");
        listaIniciales.stream()
                .filter(p -> p.getAsignatura().equalsIgnoreCase("REL"))
                .map(p -> p.getInicalesProfesor())
                .sorted(Comparator.reverseOrder()).distinct()
                .collect(Collectors.toList())
                .forEach(System.out::println);
        System.out.println("------------Lista de aulas donde imparte JFV-----------------");
        List<HorarioProfesorado> listaAulas = listaHorarios;
        listaAulas.stream()
                .filter(p -> p.getInicalesProfesor().equalsIgnoreCase("JFV"))
                .map(p -> p.getAula()).distinct()
                .collect(Collectors.toList())
                .forEach(System.out::println);

        System.out.println("Asignaturas distintas: " + contadorAsgnaturas(horasProg));

        System.out.println("Horas totales a ultima hora: " + totalHorasAUltima(horasProg));

        System.out.println("---------Profesores que tienen clase a primera hora");
        profesoresClaseAPrimera(listaIniciales);
    }

    private static void registros1ESOA(List<HorarioProfesorado> listaIniciales) {
        listaIniciales.stream()
                .filter(v -> v.getCurso().equalsIgnoreCase("1ESOA") && !("Mus".equalsIgnoreCase(v.getAsignatura())))
                .forEach(System.out::println);
    }

    private static long contadorHorasProgram(List<HorarioProfesorado> listaIniciales) {
        long countProg = listaIniciales.stream()
                .filter(v -> v.getAsignatura().equalsIgnoreCase("PROGR"))
                .map(p -> p.getHoraDelDia()).count();

        return countProg;
    }

    private static long contadorAsgnaturas(List<HorarioProfesorado> listaIniciales) {
        long countAsig = listaIniciales.stream()
                .map(p -> p.getAsignatura()).distinct().count();

        return countAsig;
    }

    private static long totalHorasAUltima(List<HorarioProfesorado> listaIniciales) {
        long countUltimHoras = listaIniciales.stream()
                .filter(p -> p.getHoraDelDia() == 6)
                .map(p -> p.getHoraDelDia()).count();

        return countUltimHoras;
    }

    private static void profesoresClaseAPrimera(List<HorarioProfesorado> listaIniciales) {
        listaIniciales.stream()
                .filter(p -> p.getHoraDelDia() == 1)
                .map(p -> p.getInicalesProfesor()).distinct()
                .forEach(System.out::println);
    }
}
