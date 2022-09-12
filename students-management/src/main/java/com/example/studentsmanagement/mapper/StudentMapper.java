package com.example.studentsmanagement.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.studentsmanagement.model.Student;

@Mapper
public interface StudentMapper {
    // SELECT * FROM student where name LIKE %T%;
    @Select("SELECT * FROM student where name LIKE #{name}")
    List<Student> getStudentsContainsInName(@Param("name") String name);

    // SELECT * FROM student where university_class_id IN
    // (SELECT id FROM university_class where year = 2022 AND className = className)
    @Select("SELECT * FROM student where university_class_id IN"
            + " (SELECT id FROM university_class where year = #{year} AND class_name = #{className})")
    List<Student> getStudentInClass(@Param("year") int year, @Param("className") String className);
}
