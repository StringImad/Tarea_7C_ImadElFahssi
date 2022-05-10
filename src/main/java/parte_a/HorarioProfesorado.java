/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parte_a;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author MSI
 */
public class HorarioProfesorado implements Serializable {

    private static final long serialVersionUID = 1L;
    private String curso;
    private String inicalesProfesor;
    private String asignatura;
    private String aula;
    private int diaDeLaSemana;
    private int horaDelDia;

    public HorarioProfesorado() {
    }

    public HorarioProfesorado(String curso, String inicalesProfesor, String asignatura, String aula, int diaDeLaSemana, int horaDelDia) {
        this.curso = curso;
        this.inicalesProfesor = inicalesProfesor;
        this.asignatura = asignatura;
        this.aula = aula;
        this.diaDeLaSemana = diaDeLaSemana;
        this.horaDelDia = horaDelDia;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getInicalesProfesor() {
        return inicalesProfesor;
    }

    public void setInicalesProfesor(String inicalesProfesor) {
        this.inicalesProfesor = inicalesProfesor;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public int getDiaDeLaSemana() {
        return diaDeLaSemana;
    }

    public void setDiaDeLaSemana(int diaDeLaSemana) {
        this.diaDeLaSemana = diaDeLaSemana;
    }

    public int getHoraDelDia() {
        return horaDelDia;
    }

    public void setHoraDelDia(int horaDelDia) {
        this.horaDelDia = horaDelDia;
    }

    @Override
    public String toString() {
        return curso + ";" + inicalesProfesor + ";" + asignatura + ";" + aula + ";" + diaDeLaSemana + ";" + horaDelDia;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.curso);
        hash = 37 * hash + Objects.hashCode(this.inicalesProfesor);
        hash = 37 * hash + Objects.hashCode(this.asignatura);
        hash = 37 * hash + Objects.hashCode(this.aula);
        hash = 37 * hash + this.diaDeLaSemana;
        hash = 37 * hash + this.horaDelDia;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HorarioProfesorado other = (HorarioProfesorado) obj;
        if (this.diaDeLaSemana != other.diaDeLaSemana) {
            return false;
        }
        if (this.horaDelDia != other.horaDelDia) {
            return false;
        }
        if (!Objects.equals(this.curso, other.curso)) {
            return false;
        }
        if (!Objects.equals(this.inicalesProfesor, other.inicalesProfesor)) {
            return false;
        }
        if (!Objects.equals(this.asignatura, other.asignatura)) {
            return false;
        }
        if (!Objects.equals(this.aula, other.aula)) {
            return false;
        }
        return true;
    }

}
