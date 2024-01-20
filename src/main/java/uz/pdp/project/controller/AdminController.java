package uz.pdp.project.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.project.daos.AdminDao;
import uz.pdp.project.daos.AuthUserDao;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final AuthUserDao authUserDao;
    private final AdminDao adminDao;

    public AdminController(AuthUserDao authUserDao, AdminDao adminDao) {
        this.authUserDao = authUserDao;
        this.adminDao = adminDao;
    }

    @GetMapping("/users/list")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView adminPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin");
        modelAndView.addObject("users", authUserDao.getAllUsers());

        return modelAndView;
    }

    @GetMapping("/activate/user")
    @PreAuthorize("hasRole('ADMIN')")
    public String activateUsers(@RequestParam("id") Long id) {
        adminDao.activate(id);
        return "redirect:/admin/users/list";
    }

    @GetMapping("deactivate/user")
    @PreAuthorize("hasRole('ADMIN')")
    public String deactivateUsers(@RequestParam("id") Long id){
        adminDao.deactivate(id);
        return "redirect:/admin/users/list";
    }


}
