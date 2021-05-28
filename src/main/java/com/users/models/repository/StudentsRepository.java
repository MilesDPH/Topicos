package com.users.models.repository;

import com.users.models.entity.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentsRepository extends CrudRepository<Student, Long>{
}
