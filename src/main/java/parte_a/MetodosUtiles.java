/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parte_a;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author MSI
 */
public class MetodosUtiles {
    //Metodo publico estatico que recibe una lista y compara dos de sus campos y ordenamos la lista siguiendo ese criterio
     public static void ordenandoListaDiaHora(ArrayList lista) {
        Comparator<HorarioProfesorado> ordenPorDia = (d1, d2) -> Integer.compare(d1.getDiaDeLaSemana(), d2.getDiaDeLaSemana());
        Comparator<HorarioProfesorado> ordenPorHora = (h1, h2) -> Integer.compare(h1.getHoraDelDia(), h2.getHoraDelDia());
        Collections.sort(lista, ordenPorDia.thenComparing(ordenPorHora));

    }
}
