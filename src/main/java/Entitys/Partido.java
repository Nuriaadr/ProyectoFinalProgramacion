/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entitys;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author nuria
 */
@Entity
@Table(name = "Partidos")
public class Partido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date fecha;
    private String lugar;

    @OneToOne
    @JoinColumn(name = "id_arbitro", unique = true)
    private Arbitro arbitro;

    @OneToMany(mappedBy = "partido")
    private List<Participacion> participaciones;

    public Partido(Date fecha, String lugar, Arbitro arbitro, List<Participacion> participaciones) {

        this.fecha = fecha;
        this.lugar = lugar;
        this.arbitro = arbitro;
        this.participaciones = new ArrayList();
    }

    public Partido() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Arbitro getArbitro() {
        return arbitro;
    }

    public void setArbitro(Arbitro arbitro) {
        this.arbitro = arbitro;
    }

    public List<Participacion> getParticipaciones() {
        return participaciones;
    }

    public void setParticipaciones(List<Participacion> participaciones) {
        this.participaciones = participaciones;
    }

    @Override
    public String toString() {
        return "Partido{" + "id=" + id + ", fecha=" + fecha + ", lugar=" + lugar + ", arbitro=" + arbitro + ", participaciones=" + participaciones + '}';
    }

}
