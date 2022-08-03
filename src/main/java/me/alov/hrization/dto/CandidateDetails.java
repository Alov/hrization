package me.alov.hrization.dto;

import lombok.*;

import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
public class CandidateDetails {
    private String personalInfo;
    private String requiredJob;
    private String experience;
    private String education;
    private String keySkills;
    private String driversLicense;
    private int desiredSalary;

}
