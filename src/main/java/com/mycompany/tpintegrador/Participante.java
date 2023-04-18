/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tpintegrador;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
/**
 *
 * @author Onna
 */
public class Participante {
    private Integer idParticipante;
    private String nombre;
    private ListaPronosticos pronosticos;
    
    public Participante(int idParticipante, String nombre, ListaPronosticos pronosticos) {
        this.idParticipante = idParticipante;
        this.nombre = nombre;
        this.pronosticos = pronosticos;
    }

    public Participante(int idParticipante, String nombre) {
        this.idParticipante = idParticipante;
        this.nombre = nombre;
        this.pronosticos = new ListaPronosticos();
    }

    public Participante() {
        this.idParticipante = null;
        this.nombre = null;
        this.pronosticos = new ListaPronosticos();
    }

    public Integer getIdParticipante() {
        return idParticipante;
    }

    public void setIdParticipante(Integer idParticipante) {
        this.idParticipante = idParticipante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ListaPronosticos getPronosticos() {
        return pronosticos;
    }

    public void setPronosticos(ListaPronosticos pronosticos) {
        this.pronosticos = pronosticos;
    }

    // retorna el puntaje del participando calculando los valores de los pronosticos
    public int getPuntaje() {
        // Para ver el puntaje debo recorrer los pronosticos y ver el puntaje
        // de cada uno y acumularlo. Debo devolver el total.
        int puntaje = 0;
        // el primer mensaje corresponde al atributo pronosticos de parrticipante
        // el segundo mensaje corresponde a la lista que tiene el atributo pronosticos
        // de esa lista se obtiene cada pronostico y se saca el puntaje acumulandolo en 
        // la variable puntaje
        for (Pronostico p : this.getPronosticos().getPronosticos()) {
            puntaje += p.getPuntaje();
        }
        return puntaje;
    }

    @Override
    public String toString() {
        return "Participante{" + "idParticipante=" + idParticipante + ", nombre=" + nombre + ", pronosticos=" + pronosticos + ", puntaje=" + getPuntaje() + '}';
    }    
    
    void cargarPronosticos (ListaEquipos equipos, ListaPartidos partidos) {
        this.pronosticos.cargarDeArchivo(this.getIdParticipante(), equipos, partidos);
    }
}
    
