package me.alov.hrization;

import me.alov.hrization.service.PdfService;
import me.alov.hrization.util.CvReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class HrizationApplication implements CommandLineRunner {

    @Autowired
    private CvReader cvReader;

    public static void main(String[] args) {
        SpringApplication.run(HrizationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
          cvReader.parseCv(new File("C:/Users/Ilya/Documents/cv.pdf"));
    }
}
