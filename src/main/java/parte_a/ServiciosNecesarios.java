/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parte_a;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;

/**
 *
 * @author MSI
 */
public class ServiciosNecesarios {

    public static ArrayList<HorarioProfesorado> leerFicheroScanner(String fichero) {
        ArrayList<HorarioProfesorado> listaPojoHorarios = new ArrayList<>();

        String[] tokens;
        String linea;

        System.out.println("Leyendo el fichero CSV: " + fichero);

        // Inicialización del flujo "datosFichero" en función del archivo llamado "idFichero"
        // Estructura try-with-resources. Permite cerrar los recursos una vez finalizadas
        // las operaciones con el archivo
        try (Scanner datosFichero = new Scanner(new File(fichero), "ISO-8859-1")) {
            // hasNextLine devuelve true mientras haya líneas por leer
            //Omitimos la primera linea
            datosFichero.nextLine();

            while (datosFichero.hasNextLine()) {
                // Guarda la línea completa en un Stringç
                linea = datosFichero.nextLine();
                //remplazamos todas las comillas que haya en las lineas que se lean
                linea = linea.replace("\"", "");
                // Se guarda en el array de String cada elemento de la
                // línea en función del carácter separador de campos del fichero CSV
                tokens = linea.split(";");
                HorarioProfesorado hor = new HorarioProfesorado();

                for (String string : tokens) {
                    hor.setCurso(tokens[1].trim());
                    hor.setInicalesProfesor(tokens[2].trim());
                    hor.setAsignatura(tokens[3].trim());
                    hor.setAula(tokens[4].trim());
                    hor.setDiaDeLaSemana(Integer.parseInt(tokens[5]));
                    hor.setHoraDelDia(arreglarNumerosHoraDelDia(tokens[6]));

                }
                listaPojoHorarios.add(hor);

            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return listaPojoHorarios;
    }

    private static int arreglarNumerosHoraDelDia(String hora) {
        int horaArreglada = 0;
        switch (Integer.parseInt(hora)) {
            case 5:
                horaArreglada = 4;
                break;
            case 6:
                horaArreglada = 5;
                break;
            case 7:
                horaArreglada = 6;
                break;

            default:
                horaArreglada = Integer.parseInt(hora);
        }
        return horaArreglada;
    }

    public static void generarFicheroJSON(ArrayList lista, String ruta) {

        //Creamos un objeto mapeador para poder configurar el archivo JSON
        //y para llevar a cabo la creación del mismo
        ObjectMapper mapeador = new ObjectMapper();

        //Utilizamos el método configure para que la estructura del JSON
        //este bien tabulada al guardar los objetos de la lista que le pasamos
        mapeador.configure(SerializationFeature.INDENT_OUTPUT, true);

        try {
            //llamamos al método writeValue que se le pasa como parametros
            //un constructor new File con el idFichero que pasamos anteriormente
            //y la lista de donde sacará los objetos en cuestión
            System.out.println("------------------generando 1");
            mapeador.writeValue(new File(ruta), lista);
            System.out.println("Archivo JSON creado correctamente");
        } catch (IOException ex) {
            Logger.getLogger(HorarioProfesorado.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error");
        }

    }

    public static void escrituraJson(String idFichero, HorarioProfesorado lista) throws JAXBException, IOException {
        ObjectMapper mapeador = new ObjectMapper();

        // Formato JSON bien formateado. Si se comenta, el fichero queda minificado
        mapeador.configure(SerializationFeature.INDENT_OUTPUT, true);

        // Escribe en un fichero JSON el catálogo de muebles
        mapeador.writeValue(new File(idFichero), lista);
    }

    public static void escrituraObjetoCsv(String idFichero, HorarioProfesorado lista) {

        // Estructura try-with-resources. Inicializa un objeto de tipo ObjectOutputStream
        // en función de un flujo FileOutputStream, identificado por "idFichero"
        try (ObjectOutputStream flujo = new ObjectOutputStream(new FileOutputStream(idFichero))) {
            System.out.println("curso: " + lista.getCurso());
            flujo.writeObject((lista.getCurso() + ";" + lista.getInicalesProfesor() + ";"
                    + lista.getAsignatura() + ";" + lista.getAula() + ";"
                    + lista.getDiaDeLaSemana() + ";" + lista.getHoraDelDia()));
        } catch (FileNotFoundException e) {
            System.out.println("El fichero no existe");
        } catch (IOException e) {
        }
        System.out.println("Fichero creado correctamente");
    }

    public static void generaFicheroGrupo(ArrayList<HorarioProfesorado> lista, String grupo, String idFichero) {

        try (BufferedWriter flujo = new BufferedWriter(new FileWriter(idFichero))) {
            flujo.write("PROFESOR;ASIGNATURA;AULA;DIA;HORA");
            flujo.newLine();
            for (HorarioProfesorado registro : lista) {
                System.out.println(registro.getCurso() + "---" + grupo);
                if (registro.getCurso().equals(grupo)) {
                    System.out.println("----------escribiendo-..........----------");
                    //se escribe el fichero de ese grupo
                    flujo.write(registro.getInicalesProfesor() + ";" + registro.getAsignatura() + ";" + registro.getAula() + ";" + registro.getDiaDeLaSemana() + ";" + registro.getHoraDelDia());
                    flujo.newLine();

                }
            }

            System.out.println(idFichero + " se ha creado correctamente");
        } catch (IOException e) {

            System.out.println(e.getMessage());
        }
    }
}
