package com.example.studentsmanagement.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "university_class")
public class UniversityClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    Integer year;

    @Column(nullable = false)
    String className;

    @OneToMany(mappedBy = "universityClass")
    List<Student> students;

    public UniversityClass(Long id, Integer year, String className) {
        this.id = id;
        this.year = year;
        this.className = className;
    }

    public UniversityClass() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    // public void setYear(Integer yaer) {
    // this.year = year;
    // }

    public String getClassName() {
        return className;
    }

    public void setclassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        String str = "";
        str += "Primary ID: " + getId();
        str += " Year : " + getYear();
        str += " Class Name: " + getClassName();
        return str;
    }

}
