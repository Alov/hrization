package me.alov.hrization.util;

import me.alov.hrization.dto.CandidateDetails;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public interface CvReader {
    CandidateDetails parseCv(File file);
}
