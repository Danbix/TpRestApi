package com.example.chapter4.repositories;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.chapter4.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findById(int id);

    @Query("SELECT YEAR(s.dateNaissance), COUNT(*) FROM Student s GROUP BY YEAR(s.dateNaissance)")
    Collection<Object[]> findNbrStudentByYear();
}
