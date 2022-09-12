package com.example.studentsmanagement.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.example.studentsmanagement.model.Student;

public interface StudentDao extends CrudRepository<Student, Long> {
    List<Student> findByName(String name);
}
