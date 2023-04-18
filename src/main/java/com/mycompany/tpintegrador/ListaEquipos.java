/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tpintegrador;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
/**
 *
 * @author Onna
 */
public class ListaEquipos {
    private List<Equipo> equipos;
    private String nombreArchivo;

    public ListaEquipos(List<Equipo> equipos, String nombrerAchivo) {
        this.equipos = equipos;
        this.nombreArchivo = nombrerAchivo;
    }

    public ListaEquipos(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
        this.equipos=new ArrayList<Equipo>();
    }

    public ListaEquipos() {
        this.nombreArchivo = "equipos.csv";
        this.equipos=new ArrayList<Equipo>();
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
    
    public Equipo getEquipo(int idEquipo){
        Equipo encontrado = null;       
        for (Equipo eq : this.equipos) {     
            if (eq.getIdEquipo()==idEquipo) {
                encontrado = eq;
                return encontrado;
            }
        }
        return encontrado;
    }
    
    public void addEquipo(Equipo eq){
        this.equipos.add(eq);
    }
    
    public void removeEquipo(Equipo eq){
        this.equipos.remove(eq);
    }
    
    @Override
    public String toString() {
        return "ListaEquipos{" + "equipos=" + equipos + ", nombreArchivo=" + nombreArchivo + '}';
    }
    
    public String listar(){
        String res="";
        for(Equipo eq : this.equipos){
            res+="\n"+eq.toString();
        }
        return res;
    }
    
    public void cargarDeArchivo(){
       String datosEquipo;
       String vectorEquipo[];
       Equipo equipo;
       int fila=0;
       try{
               Scanner sc = new Scanner(new File(this.getNombreArchivo()));
               sc.useDelimiter("\n");
                
               while(sc.hasNext()){
               datosEquipo=sc.next();
               vectorEquipo=datosEquipo.split(",");
            
               if(fila>=1){ 
                   int id=Integer.parseInt(vectorEquipo[0]);
                   equipo = new Equipo(id, vectorEquipo[1], vectorEquipo[2]); 
                   this.addEquipo(equipo);
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
            conn = DriverManager.getConnection("jdbc:sqlite:equipos.db");
            Statement stmt = conn.createStatement();
            String sql = "SELECT"
                    + "idEquipo, Equipo, Descripcion"
                    + "FROM equipos";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                
                Equipo equipo = new Equipo(
                        rs.getInt("idEquipo"),
                        rs.getString("Equipo"), 
                        rs.getString("Descripcion")
                );
                this.addEquipo(equipo);
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
