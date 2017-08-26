package com.khh.postit;

import com.khh.domain.LoginDTO;
import com.khh.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by hyunhokim on 2017. 8. 27..
 */
@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/signin",  method = RequestMethod.GET)
    public String loginGET(@ModelAttribute("dto") LoginDTO dto) {
        logger.info("[info] loginGET ...");

        return "login/signin";
    }
}
