/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entitys.Partido;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

/**
 *
 * @author nuria
 */
public class PartidoDAO {
     private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoFinalProgramacion");

    public void crear(Partido partido) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(partido);
        em.getTransaction().commit();
        em.close();
    }

    public Partido leer(int id) {
        EntityManager em = emf.createEntityManager();
        Partido partido = em.find(Partido.class, id);
        em.close();
        return partido;
    }

    public void actualizar(Partido partido) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(partido);
        em.getTransaction().commit();
        em.close();
    }

    public void eliminar(int id) {
        EntityManager em = emf.createEntityManager();
        Partido partido = em.find(Partido.class, id);
        if (partido != null) {
            em.getTransaction().begin();
            em.remove(partido);
            em.getTransaction().commit();
        }
        em.close();
    }

    public List<Partido> listarTodos() {
        EntityManager em = emf.createEntityManager();
        List<Partido> lista = em.createQuery("SELECT p FROM Partidos p", Partido.class).getResultList();
        em.close();
        return lista;
    }
}

