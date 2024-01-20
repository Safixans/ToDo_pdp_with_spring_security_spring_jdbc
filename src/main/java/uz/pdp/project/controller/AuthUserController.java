package uz.pdp.project.controller;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.project.daos.AuthUserDao;
import uz.pdp.project.domains.AuthUser;
import uz.pdp.project.dto.UserRegisterDto;

@Controller
public class AuthUserController {
    private final PasswordEncoder passwordEncoder;
    private final AuthUserDao authUserDao;

    public AuthUserController(PasswordEncoder passwordEncoder, AuthUserDao authUserDao) {
        this.passwordEncoder = passwordEncoder;
        this.authUserDao = authUserDao;
    }

    @GetMapping("/login")
    public ModelAndView loginPage(@RequestParam(required = false) String error){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/login");
        modelAndView.addObject("error message: ",error);
        return modelAndView;
    }


    @GetMapping("/register")
    public String register(@ModelAttribute UserRegisterDto dto){
        AuthUser user=AuthUser.builder()
                .username(dto.username())
                .password(passwordEncoder.encode(dto.password()))
                .build();
        Long id = authUserDao.save(user);
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logoutPage(){

        return "/logout";
    }

}
