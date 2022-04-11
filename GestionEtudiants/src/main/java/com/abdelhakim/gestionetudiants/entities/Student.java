package com.abdelhakim.gestionetudiants.entities;

import com.abdelhakim.gestionetudiants.enumerations.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    @Size(min=4, max = 50)
    private String firstName;

    @Column(length = 50)
    @Size(min=4, max = 50)
    private String lastName;

    @Column(length = 80, unique = true)
    private String email;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDay;

    @Enumerated(EnumType.ORDINAL)
    private Gender gender = Gender.Male;

    private boolean status;

    public String getFullName() {
        return  this.firstName + " " + this.lastName;
    }
}
