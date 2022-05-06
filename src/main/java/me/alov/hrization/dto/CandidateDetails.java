package me.alov.hrization.dto;

import lombok.*;

import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
public class CandidateDetails {

    private String details;
    private String requiredJob;
    private String exp;
    private String education;
    private String skills;
    private String additionalInfo;
}
