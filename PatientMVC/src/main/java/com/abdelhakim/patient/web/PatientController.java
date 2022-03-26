package com.abdelhakim.patient.web;

import com.abdelhakim.patient.entities.Patient;
import com.abdelhakim.patient.repositories.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class PatientController {

    private PatientRepository patientRepository;

    @GetMapping("/index")
    public String index(
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size,
            @RequestParam(defaultValue = "") String keyword
    ) {
        Page<Patient> patientPage = patientRepository.findByNameContains(keyword, PageRequest.of(page, size));
        model.addAttribute("patients", patientPage.getContent());
        model.addAttribute("pages", new int[patientPage.getTotalPages()]);
        model.addAttribute("total", patientPage.getTotalPages());
        model.addAttribute("size", size);
        model.addAttribute("current", page);
        model.addAttribute("keyword", keyword);
        return "index";
    }

    @GetMapping("/add")
    public String addPatientView(Model model) {
        model.addAttribute("patient", new Patient());
        return "addPatient";
    }

    @PostMapping("/add")
    public String addPatient(@ModelAttribute Patient patient) {
        patientRepository.save(patient);
        return "redirect:index";
    }

    @GetMapping("/delete")
    public String delete(Model model, long id) {
        patientRepository.deleteById(id);
        return "redirect:index";
    }
}
