package com.example.project;

import jakarta.ejb.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Singleton
@Path("/singletonStudent")
public class SingletonStudentBean {
    @GET
    @Path("/getTopStudent")
    @Produces("text/plain")
    public String getTopStudent(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("studentPersistenceUnit");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Student> cq = cb.createQuery(Student.class);
        Root<Student> root = cq.from(Student.class);

        cq.select(root).orderBy(cb.desc(root.get("cgpa")));
        TypedQuery<Student> query = em.createQuery(cq);

        query.setMaxResults(1);

        Student student = query.getSingleResult();
        return student.toString();
    }
}
