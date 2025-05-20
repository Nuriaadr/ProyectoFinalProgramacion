/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entitys;

import jakarta.persistence.*;
import java.util.List;

/**
 *
 * @author nuria
 */

@Entity
@Table(name = "Pueblos")
public class Pueblo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private String localizacion;

    @OneToMany(mappedBy = "pueblo")
    private List<Jugador> jugadores;

    public Pueblo( String nombre, String localizacion, List<Jugador> jugadores) {
       
        this.nombre = nombre;
        this.localizacion = localizacion;
        this.jugadores = jugadores;
    }

    public Pueblo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    @Override
    public String toString() {
        return "Pueblo{" + "id=" + id + ", nombre=" + nombre + ", localizacion=" + localizacion + ", jugadores=" + jugadores + '}';
    }
    
    
}