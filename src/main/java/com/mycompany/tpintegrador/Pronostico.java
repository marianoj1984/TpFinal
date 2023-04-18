/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tpintegrador;

/**
 *
 * @author Onna
 */
public class Pronostico {
    private int idPronostico;
    private Equipo equipo;
    private Partido partido;
    private char resultado;

    public Pronostico(int id, Equipo equipo, Partido partido, char resultado) {
        this.idPronostico=id;
        this.equipo = equipo;
        this.partido = partido;
        this.resultado = resultado;
    }
    
    public Pronostico(int id, Equipo equipo, Partido partido, char resultado, int idParticipante) {
        this.idPronostico=id;
        this.equipo = equipo;
        this.partido = partido;
        this.resultado = resultado;
    }
    
    public Pronostico() {
        this.idPronostico=0;
        this.equipo = null;
        this.partido = null;
        this.resultado = ' ';
    }

    public int getIdPronostico() {
        return idPronostico;
    }

    public void setIdPronostico(int idPronostico) {
        this.idPronostico = idPronostico;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public char getResultado() {
        return resultado;
    }

    public void setResultado(char resultado) {
        this.resultado = resultado;
    }
    
    public int getPuntaje(){
        if(this.resultado==this.partido.getResultado(this.equipo)){
            return 3;
        }else {
            return 0;
        }
    }
    @Override
    public String toString() {
        return "Pronostico{" + "idPronostico=" + idPronostico + ", equipo=" + equipo + ", partido=" + partido + ", resultado=" + resultado + '}';
    }

    
    
    
}
