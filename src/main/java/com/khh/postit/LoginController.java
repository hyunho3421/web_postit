package com.khh.postit;

import com.khh.domain.LoginDTO;
import com.khh.domain.User;
import com.khh.repo.UserRepo;
import com.khh.service.UserDetailService;
import com.khh.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by hyunhokim on 2017. 8. 27..
 */
@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailService userDetailService;

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String signInGET(@ModelAttribute("dto") LoginDTO dto) {
        logger.info("[info] loginGET ...");

        return "/login/signin";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signUpGET(Model model) throws Exception {

        return "/login/signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signUpPOST(User user, HttpServletRequest request) throws Exception {

        userService.create(user);

        UserDetails ckUserDetails = userDetailService.loadUserByUsername(user.getId());
        Authentication authentication = new UsernamePasswordAuthenticationToken(ckUserDetails, user.getPassword(), ckUserDetails.getAuthorities());

        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

        return "redirect:/";
    }
}
