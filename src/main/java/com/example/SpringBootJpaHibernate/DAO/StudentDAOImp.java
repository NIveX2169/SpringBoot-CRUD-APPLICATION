package com.example.SpringBootJpaHibernate.DAO;

import com.example.SpringBootJpaHibernate.Entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Repository
public class StudentDAOImp implements DAO{


    private EntityManager entityManager;


    @Autowired
    public StudentDAOImp(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    @Transactional
    public void save(Student theStudent) {
         entityManager.persist(theStudent);
    }
    @Override
    public List<Student> findAll() {

        TypedQuery<Student> theQuery = entityManager.createQuery("from Student",Student.class);

        return theQuery.getResultList();
    }

    @Transactional
    @Override
    public  void update(Student theStudent) {
        Student temp =  entityManager.merge(theStudent);
        System.out.println("Success!!");
        System.out.println(temp.toString());
    }

    public Student findById(int id)
    {
        return  entityManager.find(Student.class,id);
    }

    @Transactional
    @Override
    public int deleteUsingPrimaryKey(int key) {

       int rows =  entityManager.createQuery("DELETE FROM Student WHERE id =:key")
                .setParameter("key",key)
                .executeUpdate();

        if(rows > 0)
         System.out.println("No Of Rows Affected : "+rows);

        return rows;
    }

    @Transactional
    @Override
    public void deleteUsingQuery(String fName,String lName,String eMail) {
        int rows = 0;

        if(!lName.equals(" ")&& !fName.equals(" ")&& !eMail.equals(" "))
            rows = entityManager
                    .createQuery("DELETE FROM Student WHERE firstName =:fName  And lastName = :lName And email =: eMail")
                    .setParameter("lName", lName)
                    .setParameter("fName", fName)
                    .setParameter("eMail", eMail)
                    .executeUpdate();
        else if(!lName.equals(" ") && fName.equals(" ") && !eMail.equals(" "))
            rows = entityManager
                    .createQuery("DELETE FROM Student WHERE firstName =:fName  And email = :eMail")
                    .setParameter("lName", lName)
                    .setParameter("email", eMail)
                    .executeUpdate();
        else if(lName.equals(" ") && !fName.equals(" ")&&!eMail.equals(" "))
            rows = entityManager
                    .createQuery("DELETE FROM Student WHERE firstName =:fName  And email = :eMail")
                    .setParameter("email", eMail)
                    .setParameter("fName", fName)
                    .executeUpdate();
        else if(!fName.equals(" ")&& lName.equals(" ")&& eMail.equals(" "))
            rows = entityManager
                    .createQuery("DELETE FROM Student WHERE firstName = :fName")
                    .setParameter("fName", fName)  // Corrected parameter name and value
                    .executeUpdate();
        else if(!lName.equals(" ")&&fName.equals(" ")&& eMail.equals(" "))
            rows = entityManager
                    .createQuery("DELETE FROM Student WHERE lastName = :lName")
                    .setParameter("lName", lName)  // Corrected parameter name and value
                    .executeUpdate();
        else if(lName.equals(" ")&& fName.equals(" ")&& !eMail.equals(" "))
            rows = entityManager
                    .createQuery("DELETE FROM Student WHERE email  = :eMail")
                    .setParameter("eMail", eMail)  // Corrected parameter name and value
                    .executeUpdate();


        if (rows > 0) {
            System.out.println("No Of Rows Affected: " + rows);
        } else {
            System.out.println("No rows affected. Check your conditions.");
        }

    }

    @Override
    public List<Student> getDetailsOfAllStudents() {
        return entityManager.createQuery("from Student",Student.class)
                .getResultList();
    }

    @Override
    public List<Student> getDetailsUsingCol(String fName,String lName,String eMail) {

        List<Student> result = new ArrayList<Student>();

        if(!lName.equals(" ")&& !fName.equals(" ")&& !eMail.equals(" "))
            result = entityManager
                    .createQuery("FROM Student  WHERE firstName =:fName  And lastName = :lName And email =: eMail",Student.class)
                    .setParameter("lName", lName)
                    .setParameter("fName", fName)
                    .setParameter("eMail", eMail)
                    .getResultList();
        else if(!lName.equals(" ") && fName.equals(" ") && !eMail.equals(" "))
            result = entityManager
                    .createQuery("FROM Student WHERE firstName =:fName  And email = :eMail",Student.class)
                    .setParameter("lName", lName)
                    .setParameter("email", eMail)
                    .getResultList();
        else if(lName.equals(" ") && !fName.equals(" ")&&!eMail.equals(" "))
            result = entityManager
                    .createQuery("FROM Student WHERE firstName =:fName  And email = :eMail",Student.class)
                    .setParameter("email", eMail)
                    .setParameter("fName", fName)
                    .getResultList();
        else if(!fName.equals(" ")&& lName.equals(" ")&& eMail.equals(" "))
            result = entityManager
                    .createQuery("FROM Student  WHERE firstName =:fName",Student.class)
                    .setParameter("fName", fName)
                    .getResultList();
        else if(!lName.equals(" ")&&fName.equals(" ")&& eMail.equals(" "))
            result = entityManager
                    .createQuery("FROM Student  WHERE lastName = :lName",Student.class)
                    .setParameter("lName", lName)
                    .getResultList();
        else if(lName.equals(" ")&& fName.equals(" ")&& !eMail.equals(" "))
            result = entityManager
                    .createQuery("FROM Student WHERE email  = :eMail",Student.class)
                    .setParameter("eMail", eMail)  // Corrected parameter name and value
                    .getResultList();



        return result;
    }



}
