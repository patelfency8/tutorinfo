package com.tutorinfo.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.tutorinfo.dto.TeacherRegistrationDto;
import com.tutorinfo.model.Teacher;

public interface TeacherService extends UserDetailsService {

	Teacher findByEmail(String email);

	Teacher save(TeacherRegistrationDto registration);
}
