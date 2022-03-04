
package choi.web.springboot.controller;

import choi.web.springboot.domain.Sample;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class SampleController {

    @GetMapping("/sample/thymeleaf")
    public String list(Model model) {
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

        // Return Result
        return "sample/thymeleaf";
    }

}
