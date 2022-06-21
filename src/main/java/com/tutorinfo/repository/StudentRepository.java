package com.tutorinfo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tutorinfo.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	public List<Student> findAllByOrderByLastNameAsc();



}
