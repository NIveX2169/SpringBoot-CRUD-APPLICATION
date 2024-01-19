package com.example.SpringBootJpaHibernate.DAO;

import com.example.SpringBootJpaHibernate.Entity.Student;
import jakarta.persistence.EntityManager;

import java.util.List;


public interface DAO {
    void save(Student theStudent);

    List<Student> findAll();

    public void update(Student theStudent);

    public int deleteUsingPrimaryKey(int key);

    public void deleteUsingQuery(String firstName,String lastName,String email);

    public List<Student> getDetailsOfAllStudents();

    public List<Student> getDetailsUsingCol(String fName,String lName,String email);


}
