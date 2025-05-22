/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entitys;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 *
 * @author nuria
 */
@Entity //Marca la clase como una entidad JPA, es decir, 
//que se va a mapear a una tabla de la base de datos
@Table(name = "Arbitros") //Define el nombre de la tabla en la badat que representa
//esta entidad, si no la usaramos se tomaría el nombre de la clase
public class Arbitro {

    @Id //marca el campo id como clave primaria             
    @GeneratedValue(strategy = GenerationType.IDENTITY) //indica que el campo id
    //va a ser generado automatico por la tabla (autoincrement)
    private int id;

    private String nombre;

    @OneToOne(mappedBy = "arbitro") //define una relacion uno a uno con la entidad partido
    private Partido partido;

    public Arbitro(String nombre, Partido partido) {

        this.nombre = nombre;
        this.partido = partido;
    }

    public Arbitro() {
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

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    @Override
    public String toString() {
        return id + " " + nombre;
    }

}
