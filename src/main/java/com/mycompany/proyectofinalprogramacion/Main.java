/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyectofinalprogramacion;

import Entitys.Jugador;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 *
 * @author nuria
 */
public class Main {

    public static void main(String[] args) {
        // Crea el EntityManagerFactory con el nombre del persistence-unit
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoFinalProgramacion");

        // Crea el EntityManager
        EntityManager em = emf.createEntityManager();

        // Abre transacción
        em.getTransaction().begin();

        // Crea un nuevo jugador (ejemplo)
        Jugador j = new Jugador();
        j.setNombre("Carlos");

        // Guarda en la base de datos
        em.persist(j);

        // Confirma la transacción
        em.getTransaction().commit();

        // Cierra
        em.close();
        emf.close();
    }
    
    }