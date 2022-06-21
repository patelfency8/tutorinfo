package com.tutorinfo.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.tutorinfo.dto.StudentDto;
import com.tutorinfo.model.Student;
import com.tutorinfo.service.StudentService;

@Controller
public class StudentController {

	@Autowired(required = true)
	@Lazy(true)
	ModelMapper modelMapper;

	@Autowired
	private StudentService studentService;

	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}

	// handler method to handle list students and return mode and view
	@GetMapping("/index")
	public String listStudents(Model model) {
		model.addAttribute("students", studentService.getAllStudents());
		return "index";
	}

	@ModelAttribute("student")
	public StudentDto studentDto() {
		return new StudentDto();
	}

	@GetMapping("/students/new")
	public String showStudentForm(Model model) {
		Student student = new Student();
		model.addAttribute("student", student);
		return "create_student";
	}

	@PostMapping("/students")
	public String saveStudent(@ModelAttribute("student") @Valid StudentDto studentDto, BindingResult result) {

		if (result.hasErrors()) {

			return "create_student";
		}
		studentService.saveStudent(modelMapper.map(studentDto, Student.class));
		return "redirect:/index";
	}

	@GetMapping("/students/edit/{id}")
	public String editStudentForm(@PathVariable Long id, Model model) {
		model.addAttribute("student", studentService.getStudentById(id));
		model.addAttribute("id", id);
		return "edit_student";
	}

	@PostMapping("/studentsedit/{id}")
	public String updateStudent(@PathVariable Long id,@ModelAttribute("student") @Valid StudentDto studentDto, BindingResult result,
			Model model) {
		if (result.hasErrors()) {

			return "create_student";
		}
//		System.out.println(id);
		studentDto.setId(id);
		studentService.saveStudent(modelMapper.map(studentDto, Student.class));
		return "redirect:/index";
	}

//	@PutMapping("/studentsedit")
//	public String updateStudent( @ModelAttribute("student") StudentDto student, Model model) {
//
//		
////		// get student from database by id
//		
//		
//
//		// save updated student object
//		studentService.updateStudent(student);
//		return "redirect:/index";
//	}
//	

	// handler method to handle delete student request

	@GetMapping("/students/delete/{id}")
	public String deleteStudent(@PathVariable Long id) {
		studentService.deleteStudentById(id);
		return "redirect:/index";
	}

}