package com.tutorinfo.service;

import java.util.List;

import com.tutorinfo.dto.StudentDto;
import com.tutorinfo.model.Student;

public interface StudentService {

	List<Student> getAllStudents();

	Student saveStudent(Student registration);

	Student getStudentById(Long id);

	Student updateStudent(Student student);

	void deleteStudentById(Long id);



}
