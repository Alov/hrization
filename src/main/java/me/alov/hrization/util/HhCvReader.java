package me.alov.hrization.util;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.alov.hrization.dto.CandidateDetails;
import me.alov.hrization.service.PdfService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@AllArgsConstructor
public class HhCvReader implements CvReader{

    private static final String PDF_FILE_PREFIX = ".pdf";

    private final PdfService pdfService;

    Map<String, String> keywords = new HashMap();

    @PostConstruct
    public void init(){
        keywords.put("Желаемая должность и зарплата", "requiredJob");
        keywords.put("Образование", "education");
        keywords.put("Ключевые навыки", "skills");
        keywords.put("Дополнительная информация", "additionalInfo");
    }

    @Override
    public CandidateDetails parseCv(File file) {
        var candidate = new CandidateDetails();

        if (!file.getName().endsWith(PDF_FILE_PREFIX)) {
            throw new IllegalArgumentException("Not implemented yet");
        }
        System.out.println("ya bomj");
        List<String> content = pdfService.parseTextFromPdf(file);

        var sb = new StringBuilder();

        for (String s : content) {
            var trimmedLine = s.replace("\r", "");
            if (keywords.containsKey(trimmedLine.replace("\n", ""))) break;
            sb.append(trimmedLine);
        }

        candidate.setDetails(sb.toString());
        return candidate;
    }
}
