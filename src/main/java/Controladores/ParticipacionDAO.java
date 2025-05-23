/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Entitys.Participacion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

/**
 *
 * @author nuria
 */
public class ParticipacionDAO {
      private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoFinalProgramacion");

    public void crear(Participacion participacion) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(participacion);
        em.getTransaction().commit();
        em.close();
    }

    public Participacion leer(int id) {
        EntityManager em = emf.createEntityManager();
        Participacion participacion = em.find(Participacion.class, id);
        em.close();
        return participacion;
    }

    public void actualizar(Participacion participacion) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(participacion);
        em.getTransaction().commit();
        em.close();
    }

    public void eliminar(int id) {
        EntityManager em = emf.createEntityManager();
        Participacion participacion = em.find(Participacion.class, id);
        if (participacion != null) {
            em.getTransaction().begin();
            em.remove(participacion);
            em.getTransaction().commit();
        }
        em.close();
    }

    public List<Participacion> listarTodos() {
        EntityManager em = emf.createEntityManager();
        List<Participacion> lista = em.createQuery("SELECT p FROM Participacion p", Participacion.class).getResultList();
        em.close();
        return lista;
    }
}
