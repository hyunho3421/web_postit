package com.khh.postit;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hyunhokim on 2017. 8. 27..
 */
@Controller
@RequestMapping("/postit")
public class postitController {

    @RequestMapping("/test")
    public String test() {
        return "/postit/test";
    }
}
