package com.tutorinfo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tutorinfo.dto.TeacherRegistrationDto;
import com.tutorinfo.model.Teacher;
import com.tutorinfo.service.TeacherService;


@Controller
@RequestMapping("/registration")
public class TeacherRegistrationController {

    @Autowired
    @Lazy
    private TeacherService teacherService;

    @ModelAttribute("user")
    public TeacherRegistrationDto teacherRegistrationDto() {
        return new TeacherRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid TeacherRegistrationDto userDto,
                                      BindingResult result){

        Teacher existing = teacherService.findByEmail(userDto.getEmail());
        if (existing != null){
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()){
            return "registration";
        }

        teacherService.save(userDto);
        return "redirect:/registration?success";
    }

}
