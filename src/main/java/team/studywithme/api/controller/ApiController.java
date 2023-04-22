package team.studywithme.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class ApiController {

    @GetMapping("/api/open")
    public String open(){
        log.info("오픈 확인");
        return "open";
    }

}
