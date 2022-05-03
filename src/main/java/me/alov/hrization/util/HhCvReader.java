package me.alov.hrization.util;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.alov.hrization.dto.CandidateDetails;
import me.alov.hrization.service.PdfService;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.stream.Stream;

@Slf4j
@Component
@AllArgsConstructor
public class HhCvReader implements CvReader{

    private static final String PDF_FILE_PREFIX = ".pdf";

    private final PdfService pdfService;

    @Override
    public CandidateDetails parseCv(File file) {
        if (!file.getName().endsWith(PDF_FILE_PREFIX)) {
            throw new IllegalArgumentException("Not implemented yet");
        }

        Stream<String> content = pdfService.parseTextFromPdf(file);
        String s = content.findFirst().get();
        log.info(s);
        String s1 = content.findFirst().get();
        log.info(s1);


        return CandidateDetails.builder()
                .build();
    }
}
