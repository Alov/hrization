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
        String[] s2 = s1.split("Образование");
        String[] s3 = s2[0].split("Опыт работы");
        String[] s4 = s2[1].split("Ключевые навыки");
        String[] s5 = s3[0].split("Желаемая должность и зарплата");
        String[] s6 = s4[1].split("Опыт вождения");
        s5[1] = s5[1].replace("Java-разработчик","");
        String[] ss  = s5[1].split(" ");
        String sss = ss[ss.length-2].replace(" ","");
        candidate.setDesiredSalary(Integer.parseInt(sss));
        candidate.setExperience(s3[1]);
        candidate.setRequiredJob(s5[1]);
        candidate.setKeySkills(s6[0]);
        candidate.setEducation(s4[0]);
        candidate.setPersonalInfo(s5[0]);
        candidate.setDriversLicense(s6[1]);
        System.out.println(s5[0]); //Personal info
        System.out.println(s5[1]); //Required job
        System.out.println(s3[1]); //Experience
        System.out.println(s4[0]); //Education
        System.out.println(s6[0]); //KeySkills
        System.out.println(s6[1]); //Driver's license
        //log.info("ВОТ ЖЕЛАЕМАЯ ДОЛЖНОСТЬ :"+s2[0]);
       // log.info("ВОТ ЖЕЛАЕМАЯ ДОЛЖНОСТЬ :"+s2[1]);
        candidate.setRequiredJob(s1.toString());
   //     log.info("ВОТ ЖЕЛАЕМАЯ ДОЛЖНОСТЬ :"+s1);
        return candidate;
    }
}
