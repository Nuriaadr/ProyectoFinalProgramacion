/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entitys;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nuria
 */
@Entity
@Table(name = "Jugadores")
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private int edad;

    @ManyToOne
    @JoinColumn(name = "id_pueblo")
    private Pueblo pueblo;

    @OneToMany(mappedBy = "jugador")
    private List<Participacion> participaciones;

    public Jugador(String nombre, int edad, Pueblo pueblo, List<Participacion> participaciones) {

        this.nombre = nombre;
        this.edad = edad;
        this.pueblo = pueblo;
        this.participaciones = new ArrayList<>();
    }

    public Jugador() {
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Pueblo getPueblo() {
        return pueblo;
    }

    public void setPueblo(Pueblo pueblo) {
        this.pueblo = pueblo;
    }

    public List<Participacion> getParticipaciones() {
        return participaciones;
    }

    public void setParticipaciones(List<Participacion> participaciones) {
        this.participaciones = participaciones;
    }

    @Override
    public String toString() {
        return "Jugador{" + "id=" + id + ", nombre=" + nombre + ", edad=" + edad + ", pueblo=" + pueblo + ", participaciones=" + participaciones + '}';
    }

 

}
