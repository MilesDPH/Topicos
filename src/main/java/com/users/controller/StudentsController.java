package com.users.controller;

import com.users.models.entity.Student;
import com.users.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
