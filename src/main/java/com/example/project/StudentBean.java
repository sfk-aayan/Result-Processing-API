package com.example.project;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Stateless
@Path("/student")
public class StudentBean {

    @POST
    @Path("/add")
    @Produces("text/plain")
    public String addStudent(@QueryParam("studentId") int studentId, @QueryParam("name") String name, @QueryParam("semester") String semester, @QueryParam("cgpa") double cgpa){
        Student student = new Student(studentId, name, semester, cgpa);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("studentPersistenceUnit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();
        em.close();
        emf.close();

        return "Entry created in the database!";
    }

    @GET
    @Path("/{studentId}")
    @Produces("text/plain")
    public String getStudentById(@PathParam("studentId") int studentId){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("studentPersistenceUnit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Student student = em.find(Student.class, studentId);

        if(student == null) {
            em.getTransaction().commit();
            em.close();
            emf.close();
            return "No Student with this ID in the database!";
        }

        em.getTransaction().commit();
        em.close();
        emf.close();
        return student.toString();
    }

    @GET
    @Path("/cmpStudents")
    @Produces("text/plain")
    public String getHigherId(@QueryParam("studentId1") int studentId1, @QueryParam("studentId2") int studentId2){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("studentPersistenceUnit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Student student1 = em.find(Student.class, studentId1);
        Student student2 = em.find(Student.class, studentId2);

        if(student1 == null || student2 == null) {
            em.getTransaction().commit();
            em.close();
            emf.close();
            return "No Student with these IDs in the database!";
        }

        em.getTransaction().commit();
        em.close();
        emf.close();

        if(student1.getCgpa() >= student2.getCgpa())
            return student1.toString();
        else
            return student2.toString();
    }

    @PUT
    @Path("/updateStudent")
    @Produces("text/plain")
    public String updateById(@QueryParam("studentId") int studentId, @QueryParam("name") String name){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("studentPersistenceUnit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Student student = em.find(Student.class, studentId);

        if(student == null) {
            em.getTransaction().commit();
            em.close();
            emf.close();
            return "Student with this ID not found in the database!";
        }

        student.setName(name);
        em.merge(student);
        em.getTransaction().commit();
        em.close();
        emf.close();
        return student.toString();
    }

}
