/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entitys.Pueblo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

/**
 *
 * @author nuria
 */
public class PuebloDAO {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoFinalProgramacion");

    public void crear(Pueblo pueblo) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(pueblo);
        em.getTransaction().commit();
        em.close();
    }

    public Pueblo leer(int id) {
        EntityManager em = emf.createEntityManager();
        Pueblo pueblo = em.find(Pueblo.class, id);
        em.close();
        return pueblo;
    }

    public void actualizar(Pueblo pueblo) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(pueblo);
        em.getTransaction().commit();
        em.close();
    }

    public void eliminar(int id) {
        EntityManager em = emf.createEntityManager();
        Pueblo pueblo = em.find(Pueblo.class, id);
        if (pueblo != null) {
            em.getTransaction().begin();
            em.remove(pueblo);
            em.getTransaction().commit();
        }
        em.close();
    }

    public List<Pueblo> listarTodos() {
        EntityManager em = emf.createEntityManager();
        List<Pueblo> lista = em.createQuery("SELECT p FROM Pueblo p", Pueblo.class).getResultList();
        em.close();
        return lista;
    }
}
