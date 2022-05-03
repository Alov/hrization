package me.alov.hrization.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Getter
@Builder
@AllArgsConstructor
public class CandidateDetails {

    private final String firstName;
    private final String secondName;
    private final String middleName;
    private final String location;
    private final LocalDate birthDate;
    private final Integer age;
    private final String about;
    private final Optional<String> phone;
    private final Optional<String> email;
    private final Optional<BufferedImage> photo;
    private final List<String> jobHistory;
    private final List<String> skills;
}
