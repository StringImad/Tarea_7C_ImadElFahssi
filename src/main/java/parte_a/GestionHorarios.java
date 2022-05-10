/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parte_a;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.xml.bind.JAXBException;

/**
 *
 * @author MSI
 */
public class GestionHorarios {

    public static void main(String[] args) throws JAXBException, IOException {
        //Declaracion de variables
        //Todos los registros se guardarán en una lista de objetos POJO
        ArrayList<HorarioProfesorado> listaHorarios = ServiciosNecesarios.leerFicheroScanner("hor_curso_1920_final.csv");
//      Depuracion:  mezclo los datos para ver si se hace correcta la ordenacion
//        Collections.shuffle(listaHorarios);
//        listaHorarios.forEach(System.out::println);

        MetodosUtiles.ordenandoListaDiaHora(listaHorarios);
        System.out.println("---------------------------------Lista ordenada--------------------------------------------------------");
        listaHorarios.forEach(System.out::println);

        SortedSet<String> conjuntoDeGrupos = new TreeSet<>();
        SortedSet<String> conjuntoInicialesProfesores = new TreeSet<>();

        // conjunto de grupos y de iniciales de profesores se guardarán en dos estructuras SET, ya que no debe haber duplicados.
        for (HorarioProfesorado registro : listaHorarios) {
            conjuntoDeGrupos.add(registro.getCurso());
            conjuntoInicialesProfesores.add(registro.getInicalesProfesor());
        }

        menu(conjuntoDeGrupos, conjuntoInicialesProfesores, listaHorarios);

    }

    private static void menu(SortedSet conjuntoDeGrupos, SortedSet conjuntoInicialesProfesores, ArrayList lista) throws JAXBException, IOException {
        boolean salir = true;
        String eleccion = "";
        String eleccionInicialGrupo = "";
        Scanner teclado = new Scanner(System.in);
        ArrayList<HorarioProfesorado> listaHorarios1 = lista;
        ArrayList<HorarioProfesorado> listaHorarios2 = new ArrayList<>();

        do {
            do {
                System.out.println("Que desea realizar:"
                        + "\n1.- Consultar horarios por profesor/a."
                        + "\n2.- Consultar horarios por grupo."
                        + "\n3.- Salir.");
                eleccion = teclado.nextLine();
                try {
                    switch (Integer.parseInt(eleccion)) {
                        case 1:
                             imprimirSet(conjuntoInicialesProfesores);
                            do {
                                System.out.println("Elige un profesor; ");
                                eleccionInicialGrupo = teclado.nextLine();

                            } while (!conjuntoInicialesProfesores.contains(eleccionInicialGrupo));
                            for (HorarioProfesorado registro : listaHorarios1) {
                                if (registro.getInicalesProfesor().equalsIgnoreCase(eleccionInicialGrupo)) {
                                    listaHorarios2.add(registro);
                                }
                            }
                            ServiciosNecesarios.generarFicheroJSON(listaHorarios2, "./" + eleccionInicialGrupo + ".json");

                            break;
                        case 2:
                            imprimirSet(conjuntoDeGrupos);
                            //bucle que se repite hasta que la introduccion del usuario se encuentre en la lista
                            do {
                                System.out.println("Elige un grupo; ");
                                eleccionInicialGrupo = teclado.nextLine();

                            } while (!conjuntoDeGrupos.contains(eleccionInicialGrupo));

                            ServiciosNecesarios.generaFicheroGrupo(listaHorarios1, eleccionInicialGrupo, "./" + eleccionInicialGrupo + ".csv");

                            break;
                        case 3:
                            salir = false;
                            break;

                    }
                } catch (NumberFormatException NMF) {

                    System.out.println("valor no valido");
                }
            } while (!eleccion.equalsIgnoreCase("3"));

        } while (salir);
    }

    private static void imprimirSet(SortedSet recibido) {
        for (Iterator it = recibido.iterator(); it.hasNext();) {

            // Notese que el orden del TreeSet refleja un orden descendente 
            // en sus elementos independientemente del orden de inserción.
            // Debido al uso de String's esto refleja un orden alfabético
            String x = (String) it.next();
            System.out.println(x);

        }
    }

}
