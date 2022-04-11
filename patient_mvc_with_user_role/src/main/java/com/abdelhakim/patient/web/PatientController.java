package com.abdelhakim.patient.web;

import com.abdelhakim.patient.entities.Patient;
import com.abdelhakim.patient.repositories.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class PatientController {

    private PatientRepository patientRepository;

    @GetMapping(name = "home", path = "/")
    public String home() {
        return "index";
    }

    @GetMapping(name = "patientList", path = "/user/patient_list")
    public String patientList(
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
        return "patientList";
    }

    @GetMapping(name= "patientAdd", path = "/admin/add")
    public String patientAdd(Model model) {
        model.addAttribute("patient", new Patient());
        model.addAttribute("title", "Add new patient :");
        return "patientForm";
    }

    @GetMapping(name="patientEdit", path = "/admin/edit")
    public String patientEdit(Model model, Long id) {
        Patient patient = patientRepository.findById(id).orElse(null);
        if(patient == null) return "redirect:patient_list";
        model.addAttribute("patient", patient);
        model.addAttribute("title", "Edit patient " + patient.getName() + " :");
        return "patientForm";
    }

    @PostMapping(name="patientSave", path = "/admin/save_patient")
    public String patientSave(@Valid Patient patient, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) return "patientForm";
        patientRepository.save(patient);
        return "redirect:/user/patient_list";
    }

    @GetMapping(name="patientDelete", path= "/admin/delete")
    public String patientDelete(Model model, long id) {
        patientRepository.deleteById(id);
        return "redirect:/user/patient_list";
    }
}
