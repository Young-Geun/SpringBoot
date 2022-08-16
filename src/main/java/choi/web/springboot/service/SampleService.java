package choi.web.springboot.service;

import choi.web.springboot.config.ConfigProp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class SampleService {

    static int accessCount = 1;
    private final ConfigProp configProp;

    public int retry() {
        return accessCount++;
    }

    public String convertPdfToImage(MultipartFile files) throws Exception {
        // PDF 파일업로드
        String dirPath = configProp.getFileDir() + "tmp";
        if (new File(dirPath).mkdirs()) {
            throw new Exception("사진 업로드 실패");
        }

        String pdfFilePath = dirPath + File.separator + files.getOriginalFilename();
        File pdfFile = new File(pdfFilePath);
        files.transferTo(pdfFile);

        // PDF -> Image 변환
        PDDocument pdfDoc = null;
        try {
            InputStream is = new FileInputStream(pdfFile);
            pdfDoc = PDDocument.load(is);
            PDFRenderer pdfRenderer = new PDFRenderer(pdfDoc);

            BufferedImage imageObj = pdfRenderer.renderImageWithDPI(0, 100, ImageType.RGB);
            File jpgFile = new File(dirPath + File.separator + "convert.jpg");
            ImageIO.write(imageObj, "jpg", jpgFile);
        } catch (Exception e) {
            log.error("Convert Error", e);
        } finally {
            if (pdfDoc != null) {
                pdfDoc.close();
            }
        }

        return dirPath + File.separator + "convert.jpg";
    }

}
