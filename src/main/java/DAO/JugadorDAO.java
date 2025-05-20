/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entitys.Jugador;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

/**
 *
 * @author nuria
 */
public class JugadorDAO {
     private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoFinalProgramacion");

    public void crear(Jugador jugador) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(jugador);
        em.getTransaction().commit();
        em.close();
    }

    public Jugador leer(int id) {
        EntityManager em = emf.createEntityManager();
        Jugador jugador = em.find(Jugador.class, id);
        em.close();
        return jugador;
    }

    public void actualizar(Jugador jugador) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(jugador);
        em.getTransaction().commit();
        em.close();
    }

    public void eliminar(int id) {
        EntityManager em = emf.createEntityManager();
        Jugador jugador = em.find(Jugador.class, id);
        if (jugador != null) {
            em.getTransaction().begin();
            em.remove(jugador);
            em.getTransaction().commit();
        }
        em.close();
    }

    public List<Jugador> listarTodos() {
        EntityManager em = emf.createEntityManager();
        List<Jugador> lista = em.createQuery("SELECT j FROM Jugador j", Jugador.class).getResultList();
        em.close();
        return lista;
    }
}
