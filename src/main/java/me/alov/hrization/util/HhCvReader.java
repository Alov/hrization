package me.alov.hrization.util;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.alov.hrization.dto.CandidateDetails;
import me.alov.hrization.service.PdfService;
import org.apache.pdfbox.contentstream.operator.text.SetTextRise;
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
    public int setSalary(String[] text){
        String[] ss  = text[1].split(" ");
        String sss = ss[ss.length-2].replace(" ","");
        return Integer.parseInt(sss);
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
        String requiredJob = "";
        for (int i=0;i<content.size();i++){
            requiredJob+=content.get(i);
        }
        String s1 = requiredJob.replace("\r"," ");
        String[] educationSeparation = s1.split("Образование");
        String[] expSeparation = educationSeparation[0].split("Опыт работы");
        String[] skillSeparation = educationSeparation[1].split("Ключевые навыки");
        String[] salarySeparation = expSeparation[0].split("Желаемая должность и зарплата");
        String[] driveSeparation = skillSeparation[1].split("Опыт вождения");
        candidate.setDesiredSalary(setSalary(salarySeparation));
        candidate.setExperience(expSeparation[1]);
        candidate.setRequiredJob(salarySeparation[1]);
        candidate.setKeySkills(driveSeparation[0]);
        candidate.setEducation(skillSeparation[0]);
        candidate.setPersonalInfo(salarySeparation[0]);
        candidate.setDriversLicense(driveSeparation[1]);
        candidate.setRequiredJob(s1.toString());
   //     log.info("ВОТ ЖЕЛАЕМАЯ ДОЛЖНОСТЬ :"+s1);
        return candidate;
    }
}
