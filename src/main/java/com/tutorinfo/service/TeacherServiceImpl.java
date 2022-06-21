package com.tutorinfo.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tutorinfo.dto.TeacherRegistrationDto;
import com.tutorinfo.model.Role;
import com.tutorinfo.model.Teacher;
import com.tutorinfo.repository.TeacherRepository;

@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	@Lazy
	private TeacherRepository teacherRepository;

	@Autowired
	@Lazy
	private BCryptPasswordEncoder passwordEncoder;

	public Teacher findByEmail(String email) {
		return teacherRepository.findByEmail(email);
	}

	public Teacher save(TeacherRegistrationDto registration) {
		Teacher teacher = new Teacher();
		teacher.setFirstName(registration.getFirstName());
		teacher.setLastName(registration.getLastName());
		teacher.setEmail(registration.getEmail());
		teacher.setPassword(passwordEncoder.encode(registration.getPassword()));
		teacher.setRoles(Arrays.asList(new Role("ROLE_USER")));
		return teacherRepository.save(teacher);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Teacher teacher = teacherRepository.findByEmail(email);
		if (teacher == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(teacher.getEmail(), teacher.getPassword(),
				mapRolesToAuthorities(teacher.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
}
