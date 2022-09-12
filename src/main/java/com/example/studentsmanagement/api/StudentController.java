package com.example.studentsmanagement.api;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentsmanagement.exceptions.InvalidUniversityClassException;
import com.example.studentsmanagement.exceptions.StudentEmptyNameException;
import com.example.studentsmanagement.exceptions.StudentNonExistException;
import com.example.studentsmanagement.model.Student;
import com.example.studentsmanagement.service.StudentService;

@RestController
@RequestMapping("api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    @RequiresPermissions("student:read")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @RequestMapping("/register")
    @PostMapping
    public ResponseEntity<String> regesterStudent(@RequestBody Student student) {
        try {
            Student saveStudent = studentService.addStudent(student);
            return ResponseEntity.ok("Registered student. " + saveStudent.getName());
        } catch (StudentEmptyNameException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping(path = "assigncalss/{sid}/{cid}")
    public ResponseEntity<String> assignClass(@PathVariable("sid") Long studentId, @PathVariable("cid") Long classId) {
        try {
            Student updateStudent = studentService.assignClass(studentId, classId);
            return ResponseEntity.ok("Assigned class." + updateStudent.toString());
        } catch (StudentNonExistException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (InvalidUniversityClassException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/name")
    // localhost:8080/api/student/name?name=XX
    public List<Student> getStudents(@RequestParam String name) {
        return studentService.getStudentsByName(name);
    }

    @GetMapping("/contain_name")
    // localhost:8080/api/student/name?contian_name=T
    public List<Student> getStudentsContainName(@RequestParam String name) {
        return studentService.getStudentsContainsInName(name);
    }

    @GetMapping("/class")
    // localhost:8080/api/student/name?year=2022&className=CSCI571
    public List<Student> getStudentsContainName(@RequestParam int year, @RequestParam String className) {
        return studentService.getStudentsInClass(year, className);
    }
}
