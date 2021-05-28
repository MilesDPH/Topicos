package com.users.controller;

import com.users.models.entity.Student;
import com.users.service.StudentService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController

public class StudentsController {

    @Autowired
    private StudentService service;

    @GetMapping
    public ResponseEntity<?> list(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> ver(@PathVariable Long id){
        Optional<Student> optional_student = service.findById(id);
        if(optional_student.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(optional_student.get());
    }

    @PostMapping
    public ResponseEntity<?> store(@RequestBody Student student){
        Student student_db = service.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(student_db);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Student student, @PathVariable Long id){
        Optional<Student> optional_student = service.findById(id);
        if(optional_student.isEmpty())
            return ResponseEntity.notFound().build();

        Student student_db = optional_student.get();
        student_db.setName(student.getName());
        student_db.setLastName(student.getLastName());
        student_db.setEmail(student.getEmail());

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(student_db));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> destroy(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
