package me.alov.hrization.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Slf4j
@Service
public class PdfService {

    public Stream<String> parseTextFromPdf(File file) {
        try {
            PDDocument document = PDDocument.load(file);
            PDFTextStripper stripper = new PDFTextStripper();
            String content = stripper.getText(document);
            return Arrays.stream(content.split("\n"));

        } catch (IOException e) {
            log.info("Can not parse file: {}", file.getAbsolutePath());
            throw new RuntimeException();
        }
    }

    public Map<String, BufferedImage> getImagesFromPdf(File file) throws IOException {

        Map<String, BufferedImage> resultMap = new HashMap<>();

        PDDocument pdfDocument = PDDocument.load(file);

        PDPageTree pages = pdfDocument.getPages();
        for (PDPage page : pages) {
            PDPage pdPage = pages.get(0);
            PDResources resources = pdPage.getResources();
            Iterable<COSName> xObjectNames = resources.getXObjectNames();

            xObjectNames.forEach(name -> {
                if (resources.isImageXObject(name)) {
                    log.info("Image found");
                    try {
                        PDImageXObject pdImage = (PDImageXObject) resources.getXObject(name);
                        resultMap.put(name.getName(), pdImage.getImage());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        return resultMap;
    }
}
