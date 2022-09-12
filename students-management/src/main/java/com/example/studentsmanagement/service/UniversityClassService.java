package com.example.studentsmanagement.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studentsmanagement.dao.UniversityClassDao;
import com.example.studentsmanagement.exceptions.InvalidUniversityClassException;
import com.example.studentsmanagement.model.UniversityClass;

@Service
public class UniversityClassService {
    UniversityClassDao universityClassDao;

    @Autowired
    public UniversityClassService(UniversityClassDao universityClassDao) {
        this.universityClassDao = universityClassDao;
    }

    public List<UniversityClass> getAllClasses() {
        return (List<UniversityClass>) universityClassDao.findAll();
    }

    public UniversityClass addClass(UniversityClass universityClass) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        if (universityClass.getYear() < currentYear) {
            throw new InvalidUniversityClassException("Cannot add class in the past");
        }
        if (universityClass.getYear() > currentYear + 1) {
            throw new InvalidUniversityClassException("Cannot add class in the future");

        }
        return universityClassDao.save(universityClass);
    }
}
