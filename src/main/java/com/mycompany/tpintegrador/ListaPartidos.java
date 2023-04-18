/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tpintegrador;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
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
public class ListaPartidos {
    private List<Partido> partidos;
    private String nombreArchivo;
    private ListaEquipos listaEquipos;

    public ListaPartidos(List<Partido> partidos, String nombreArchivo, String nombreArchiEquipos) {
        this.partidos = partidos;
        this.nombreArchivo = nombreArchivo;
        this.listaEquipos = new ListaEquipos(nombreArchiEquipos);
        this.listaEquipos.cargarDeArchivo();
    }

    public ListaPartidos(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
        this.partidos=new ArrayList<Partido>();
        this.listaEquipos = new ListaEquipos();
    }

    public ListaPartidos() {
        this.nombreArchivo = "partidos.csv";
        this.partidos=new ArrayList<Partido>();
        this.listaEquipos = new ListaEquipos();
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
    
    public void addPartido(Partido p){
        this.partidos.add(p);
    }
    
    public void removePartido(Partido p){
        this.partidos.remove(p);
    }

    @Override
    public String toString() {
        return "ListaPartidos{" + "partidos=" + partidos + ", nombreArchivo=" + nombreArchivo + '}';
    }
    
    public String listar(){
        String res="";
        for(Partido p : partidos){
            res+="\n"+p.toString();
        }
        return res;
    }
    
    public Partido getPartido(int idPartido){
        Partido encontrado = null;       
        for (Partido par : partidos) {     
            if (par.getIdPartido()==idPartido) {
                return encontrado = par;           
            }
        }
        return encontrado;
    }
    
    public void cargarDeArchivo(){
        String datosPartido;
        String vectorPartido[];
        Partido partido;
        int fila=0;
        try{
           Scanner sc = new Scanner(new File(this.getNombreArchivo()));
           sc.useDelimiter("\n");
                
               while(sc.hasNext()){
               datosPartido=sc.next();
               vectorPartido=datosPartido.split(",");
               
               if(fila>=1){ 
                   int id=Integer.parseInt(vectorPartido[0]);
                   Equipo eq1= this.listaEquipos.getEquipo(Integer.parseInt(vectorPartido[1]));
                   Equipo eq2= this.listaEquipos.getEquipo(Integer.parseInt(vectorPartido[2]));
                   partido=new Partido(id,eq1,eq2,Integer.parseInt(vectorPartido[2]), Integer.parseInt(vectorPartido[3]));
                   // public Partido(int idPartido, Equipo equipo1, Equipo equipo2, int goles1, int goles2)
                   this.addPartido(partido);
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
            conn = DriverManager.getConnection("jdbc:sqlite:partidos.db");
            Statement stmt = conn.createStatement();
            String sql = "SELECT"
                    + "idPartido, idEquipo1, idEquipo2, goles1, goles2 "
                    + "FROM partidos ";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                Equipo equipo1 = this.listaEquipos.getEquipo(rs.getInt("idEquipo1"));
                Equipo equipo2 = this.listaEquipos.getEquipo(rs.getInt("idEquipo2"));
                Partido partido = new Partido(
                        rs.getInt("idPartido"),
                        equipo1,
                        equipo2,
                        rs.getInt("goles1"),
                        rs.getInt("goles2")
                );
                this.addPartido(partido);
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
