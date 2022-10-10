
package choi.web.springboot.controller;

import choi.web.springboot.aop.annotation.Retry;
import choi.web.springboot.config.ConfigProp;
import choi.web.springboot.domain.Member;
import choi.web.springboot.domain.Sample;
import choi.web.springboot.domain.Test;
import choi.web.springboot.service.MybatisService;
import choi.web.springboot.service.SampleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.context.MessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@RequiredArgsConstructor
@Controller
@RequestMapping("/sample")
@Slf4j
public class SampleController {

    private final ConfigProp configProp;
    private final MessageSource messageSource;
    private final MybatisService mybatisService;
    private final SampleService sampleService;

    @GetMapping("/thymeleaf")
    public String thymeleaf(Model model) {
        // Set Value
        List<Test> list = new ArrayList();
        list.add(new Test(1, "테스트1", 1, 1, 1, 1));
        list.add(new Test(2, "테스트2", 2, 6, 7, 7));
        list.add(new Test(3, "테스트3", 3, 2, 5, 5));
        list.add(new Test(4, "테스트4", 1, 5, 5, 8));
        list.add(new Test(5, "테스트5", 2, 7, 1, 5));
        list.add(new Test(6, "테스트6", 3, 3, 6, 5));
        list.add(new Test(7, "테스트7", 1, 6, 2, 7));
        list.add(new Test(8, "테스트8", 1, 7, 1, 2));
        list.add(new Test(9, "테스트9", 2, 8, 9, 1));
        model.addAttribute("testList", list);

        // Set Value
        model.addAttribute("alphabetList", Arrays.asList("a", "b", "c", "d", "e", "f", "g"));

        // Set Value
        List<Object> numList = new ArrayList<>();
        numList.add(123);
        numList.add(1234);
        numList.add(123.0);
        numList.add(1234.0);
        numList.add(1234.5);
        numList.add(1234.56);
        numList.add(567L);
        numList.add(5678L);
        model.addAttribute("numList", numList);

        // Set Value
        model.addAttribute("intNum1", 0);
        model.addAttribute("intNum2", 10);
        model.addAttribute("doubleNum1", 0.0);
        model.addAttribute("doubleNum2", 0.01);
        model.addAttribute("longNum1", 0L);
        model.addAttribute("longNum2", 10L);

        // Set Value (반올림 테스트)
        model.addAttribute("roundsTest1", 12.46);
        model.addAttribute("roundsTest2", 34.56);
        model.addAttribute("roundsTest3", 1234.46);
        model.addAttribute("roundsTest4", 4567.56);

        // Return Result
        return "sample/thymeleaf";
    }

    @GetMapping("/messages")
    public String messages(@RequestParam String lang, Model model, @SessionAttribute(required = false) Member loginMember) {
        String[] param = new String[]{loginMember.getMemberName(), loginMember.getMemberEmail()};

        log.info("lang = {}", lang);
        String title = messageSource.getMessage("sample.messages.title", null, "en".equals(lang) ? Locale.ENGLISH : Locale.KOREAN);
        String content = messageSource.getMessage("sample.messages.content", param, "en".equals(lang) ? Locale.ENGLISH : Locale.KOREAN);
        String optionKo = messageSource.getMessage("sample.messages.ko", null, "en".equals(lang) ? Locale.ENGLISH : Locale.KOREAN);
        String optionEn = messageSource.getMessage("sample.messages.en", null, "en".equals(lang) ? Locale.ENGLISH : Locale.KOREAN);

        model.addAttribute("title", title);
        model.addAttribute("content", content);
        model.addAttribute("optionKo", optionKo);
        model.addAttribute("optionEn", optionEn);
        log.info("Messages = {}", model);

        return "sample/messages";
    }

    @GetMapping("/scroll")
    public String scroll() {
        return "sample/scroll";
    }

    @GetMapping("/drag-to-scroll")
    public String dragToScroll() {
        return "sample/dragToScroll";
    }

    @GetMapping("/click-to-scroll")
    public String clickToScroll() {
        return "sample/clickToScroll";
    }

    @GetMapping("/mybatis")
    public String mybatis(Model model) {
        model.addAttribute("sample", mybatisService.findByFirstRow());
        return "sample/mybatis";
    }

    @PostMapping("/mybatis")
    public String mybatis(Sample sample) {
        mybatisService.update(sample);
        return "redirect:/sample/mybatis";
    }

    @GetMapping("/resize")
    public String resize() {
        return "sample/resize";
    }

    @GetMapping("/fix-header")
    public String fixedHeader() {
        return "sample/fixedHeader";
    }

    @GetMapping("/multi-datasource")
    public String multiDatasource(Model model) {
        model.addAttribute("result", mybatisService.multiDatasource());
        return "sample/multiDatasource";
    }

    @Retry(value = 3)
    @GetMapping("/retry")
    public String retry(Model model) throws Exception {
        int result = sampleService.retry();
        if (result % 5 == 0) {
            throw new Exception("retry Failure!");
        }
        model.addAttribute("result", result);
        return "sample/retry";
    }

    @GetMapping("/form-reset")
    public String formReset() {
        return "sample/formReset";
    }

    @GetMapping("/css")
    public String css() {
        return "sample/css";
    }

    @GetMapping("/pdf-to-image")
    public String pdfToImage() {
        return "sample/pdfToImage";
    }

    @PostMapping("/pdf-to-image")
    public ResponseEntity<Resource> pdfToImage(@RequestParam("file") MultipartFile files) {
        try {
            String filePath = sampleService.convertPdfToImage(files);
            Resource resource = new UrlResource("file:" + filePath);
            if (resource.exists()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=convert.jpg")
                        .body(resource);
            } else {
                log.debug("PDF File not Exist!");
                return null;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @GetMapping("/format")
    public String format() {
        return "sample/format";
    }

    @GetMapping("/ajax-sync")
    public String ajaxSync() {
        return "sample/ajaxSync";
    }

    @GetMapping("/execute-1")
    @ResponseBody
    public String execute1() {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            // ignore
        }

        return "1";
    }

    @GetMapping("/execute-2")
    @ResponseBody
    public String execute2() {
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            // ignore
        }

        return "2";
    }

    @GetMapping("/execute-3")
    @ResponseBody
    public String execute3() {
        return "3";
    }

    @GetMapping("/img-to-base64")
    public String imgToBase64(Model model) {
        String fileDirPath = configProp.getFileDir();
        String imgPath = fileDirPath + "sample" + File.separator + "test_img.png";
        String base64Str = "";

        FileInputStream inputStream = null;
        ByteArrayOutputStream byteOutStream = null;

        try {
            File file = new File(imgPath);

            if (file.exists()) {
                inputStream = new FileInputStream(file);
                byteOutStream = new ByteArrayOutputStream();

                int len = 0;
                byte[] buf = new byte[1024];
                while ((len = inputStream.read(buf)) != -1) {
                    byteOutStream.write(buf, 0, len);
                }

                byte[] fileArray = byteOutStream.toByteArray();
                base64Str = new String(Base64.encodeBase64(fileArray));
                base64Str = "data:image/png;base64, " + base64Str;
            }
        } catch (Exception e) {
            log.error("imgToBase64 Err", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                }
            }
            if (byteOutStream != null) {
                try {
                    byteOutStream.close();
                } catch (Exception e) {
                }
            }

        }
        model.addAttribute("base64Str", base64Str);

        return "sample/imgToBase64";
    }

}
