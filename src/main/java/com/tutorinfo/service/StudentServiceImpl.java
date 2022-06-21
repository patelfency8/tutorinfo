package com.tutorinfo.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.tutorinfo.dto.StudentDto;
import com.tutorinfo.model.Student;
import com.tutorinfo.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired(required = true)
	@Lazy(true)
	private ModelMapper modelMapper;

	private StudentRepository studentRepository;

	public StudentServiceImpl(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	public Student saveStudent(Student registration) {
		Student student = null;
		if (registration != null && registration.getId() != null) {
			student = studentRepository.findById(Long.valueOf(registration.getId())).orElse(null);
			System.out.println(student);
			if (student != null) {

				student.setEnrollmentNo(registration.getEnrollmentNo());
				student.setFirstName(registration.getFirstName());
				student.setLastName(registration.getLastName());
				student.setAddress(registration.getAddress());
				student.setEmail(registration.getEmail());
				studentRepository.save(student);
				return student;
			}
		}
		student = modelMapper.map(registration, Student.class);

		return studentRepository.save(student);
	}

	@Override
	public Student getStudentById(Long id) {
		return studentRepository.findById(id).orElse(null);
	}

	public Student updateStudent(Student student) {
		Student existingStudent = null;
		System.out.println(student.getId());
		existingStudent = studentRepository.findById(student.getId()).orElse(null);
		System.out.println("existingStudent  " + existingStudent);
		if (existingStudent != null) {
			existingStudent.setEnrollmentNo(student.getEnrollmentNo());
			existingStudent.setFirstName(student.getFirstName());
			existingStudent.setLastName(student.getLastName());
			existingStudent.setAddress(student.getAddress());

			existingStudent.setEmail(student.getEmail());
			studentRepository.save(existingStudent);
			return existingStudent;
		}
		return student;
	}
//	@Override
//	public Student findById(Long theId) {
//		java.util.Optional<Student> result = studentRepository.findById(theId);
//		
//		Student theStudent = null;
//		
//		if (result.isPresent()) {
//			theStudent = result.get();
//		}
//		else {
//			// we didn't find the student
//			throw new RuntimeException("Did not find student id - " + theId);
//		}
//		
//		return theStudent;
//	}

	@Override
	public void deleteStudentById(Long id) {
		studentRepository.deleteById(id);
	}

}