/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tpintegrador;

/**
 *
 * @author Onna
 */
public class PronosticoDeportivo {
    private ListaEquipos equipos;
    private ListaPartidos partidos;
    private ListaParticipantes participantes;
    private ListaPronosticos pronosticos;

    public PronosticoDeportivo() {
        equipos = new ListaEquipos();
        partidos = new ListaPartidos();
        participantes = new ListaParticipantes();
        pronosticos = new ListaPronosticos();
    }

    public void play(){
        // cargar y listar los equipos
        //equipos.cargarDeArchivo();
        equipos.cargarDeDB();
        System.out.println("Los equipos cargados son: " + equipos.listar());
        
        //partidos.cargarDeArchivo();
        partidos.cargarDeDB();
        System.out.println("Los partidos cargados son: " + partidos.listar());

        //participantes.cargarDeArchivo();
        participantes.cargarDeDB();
        // Una vez cargados los participantes, para cada uno de ellos
        // cargar sus pronósticos
        for (Participante p : participantes.getParticipantes()) {
            p.cargarPronosticos(equipos, partidos);
        }
        
        System.out.println("Los participantes cargados son: " + participantes.listar());
        
        // agregar y/o modificar el codigo que quieran
        
    }  
    
}
