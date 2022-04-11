package com.abdelhakim.gestionetudiants.web;

import com.abdelhakim.gestionetudiants.entities.Student;
import com.abdelhakim.gestionetudiants.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class StudentController {
    private StudentRepository studentRepository;

    @GetMapping("/user/student/list")
    public String list(
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size,
            @RequestParam(defaultValue = "") String keyword
    ) {
        Page<Student> patientPage = studentRepository.findByFirstNameContainsOrLastNameContains(keyword, keyword, PageRequest.of(page, size));
        model.addAttribute("students", patientPage.getContent());
        model.addAttribute("pages", new int[patientPage.getTotalPages()]);
        model.addAttribute("total", patientPage.getTotalPages());
        model.addAttribute("size", size);
        model.addAttribute("current", page);
        model.addAttribute("keyword", keyword);
        return "students/list";
    }

    @GetMapping("/admin/student/add")
    public String studentAdd(Model model) {
        model.addAttribute("title", "Add new student :");
        model.addAttribute("student", new Student());
        return "students/student-form";
    }

    @GetMapping("/admin/student/edit")
    public String studentEdit(Model model, Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        if(student == null) return "redirect:/user/student/list";
        model.addAttribute("title", "Edit student " + student.getFullName() + " :");
        model.addAttribute("student", student);
        return "students/student-form";
    }

    @PostMapping("/admin/student/save")
    public String patientSave(@Valid Student student, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) return "students/student-form";
        studentRepository.save(student);
        return "redirect:/user/student/list";
    }

    @DeleteMapping("/admin/student/delete")
    public String patientDelete(Model model, long id) {
        studentRepository.deleteById(id);
        return "redirect:/user/student/list";
    }

}
