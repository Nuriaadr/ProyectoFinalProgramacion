/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entitys.Arbitro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

/**
 *
 * @author nuria
 */
public class ArbitroDAO {
     private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoFinalProgramacion");

    public void crear(Arbitro arbitro) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(arbitro);
        em.getTransaction().commit();
        em.close();
    }

    public Arbitro leer(int id) {
        EntityManager em = emf.createEntityManager();
        Arbitro arbitro = em.find(Arbitro.class, id);
        em.close();
        return arbitro;
    }

    public void actualizar(Arbitro arbitro) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(arbitro);
        em.getTransaction().commit();
        em.close();
    }

    public void eliminar(int id) {
        EntityManager em = emf.createEntityManager();
        Arbitro arbitro = em.find(Arbitro.class, id);
        if (arbitro != null) {
            em.getTransaction().begin();
            em.remove(arbitro);
            em.getTransaction().commit();
        }
        em.close();
    }

    public List<Arbitro> listarTodos() {
        EntityManager em = emf.createEntityManager();
        List<Arbitro> lista = em.createQuery("SELECT a FROM Arbitros a", Arbitro.class).getResultList();
        em.close();
        return lista;
    }
}
