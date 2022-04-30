
package choi.web.springboot.controller;

import choi.web.springboot.domain.Member;
import choi.web.springboot.domain.Sample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/sample")
@Slf4j
public class SampleController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/thymeleaf")
    public String thymeleaf(Model model) {
        // Set Value
        List<Sample> list = new ArrayList();
        list.add(new Sample(1, "테스트1", 1, 1, 1, 1));
        list.add(new Sample(2, "테스트2", 2, 6, 7, 7));
        list.add(new Sample(3, "테스트3", 3, 2, 5, 5));
        list.add(new Sample(4, "테스트4", 1, 5, 5, 8));
        list.add(new Sample(5, "테스트5", 2, 7, 1, 5));
        list.add(new Sample(6, "테스트6", 3, 3, 6, 5));
        list.add(new Sample(7, "테스트7", 1, 6, 2, 7));
        list.add(new Sample(8, "테스트8", 1, 7, 1, 2));
        list.add(new Sample(9, "테스트9", 2, 8, 9, 1));
        model.addAttribute("sampleList", list);

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

}
