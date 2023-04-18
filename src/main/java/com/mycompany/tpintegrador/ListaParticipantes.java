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
import java.sql.*;
/**
 *
 * @author Onna
 */
public class ListaParticipantes {
    private List<Participante> participantes;
    private String nombreArchivo;

    public ListaParticipantes(List<Participante> participantes, String nombreArchivo) {
        this.participantes = participantes;
        this.nombreArchivo = nombreArchivo;
    }

    public ListaParticipantes(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
        this.participantes= new ArrayList<Participante>();
    }

    public ListaParticipantes() {
        this.nombreArchivo = "participantes.csv";
        this.participantes= new ArrayList<Participante>();
    }
    
    
    public List<Participante> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Participante> participantes) {
        this.participantes = participantes;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
    
    public void addParticipante(Participante part){
        this.participantes.add(part);
    }
    
    public void removeParticipante(Participante part){
        this.participantes.remove(part);
    }
    @Override
    public String toString() {
        return "ListaParticipantes{" + "participantes=" + participantes + ", nombreArchivo=" + nombreArchivo + '}';
    }
    public String listar(){
        String res="";
        for(Participante partic : participantes){
            res+="\n"+partic.toString();
        }
        return res;
    }
    
    public Participante getParticipante(int idParticipante){
        Participante encontrado = null;       
        for (Participante part : participantes) {     
            if (part.getIdParticipante()==idParticipante) {
                encontrado = part;
                break;
            }
        }
        return encontrado;
    }
    
    public int getPuntaje(int idPart){
        for (Participante part : participantes) {     
            if (part.getIdParticipante()==idPart) {
                return part.getPuntaje();
            }
        }
        return 0;
    }
    public void cargarDeArchivo(){
       String datosParticipante;
       String vectorParticipante[];
       Participante participante;
       int fila=0;
       try{
           Scanner sc = new Scanner(new File(this.getNombreArchivo()));
           sc.useDelimiter("\n");
           while(sc.hasNext()){
    
               datosParticipante=sc.next(); 
               
               vectorParticipante=datosParticipante.split(",");
               
               if(fila>=1){
                    int id=Integer.parseInt(vectorParticipante[0]);
                    String nombre=vectorParticipante[1];
                    participante = new Participante(id,nombre);
                    this.addParticipante(participante);
               }
               fila++;
               }
        }
       catch(IOException ex){
           System.out.println("Error al abrir el archivo");
       }
    }
    
    public void cargarDeDB(){
        Connection conn=null;
        try{
            conn = DriverManager.getConnection("jdbc:sqlite:participantes.db");
            Statement stmt = conn.createStatement();
            String sql = "SELECT"
                    + "idPartido, Participante "
                    + "FROM participantes ";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                Participante participante = new Participante(
                        rs.getInt("idParticipante"),
                        rs.getString("Participante")
                );
                this.addParticipante(participante);
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        finally{
            try{
                if(conn!=null){
                    conn.close();
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
