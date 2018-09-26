package cap.controller;

import cap.model.Admin;
import cap.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;


/**
 * @author 刘贤熔
 * @version 1.0.0
 * @createTIme 2018/9/20 10:40
 **/

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private AdminService adminService;
    @Resource
    private CounterService counterService;
    @Resource
    private UserService userService;
    @Resource
    private CommentService commentService;
    @Resource
    private ArticleService articleService;


    @RequestMapping(value = "/login")
    public String login(HttpSession session, @ModelAttribute("admin") Admin admin) {

        Admin ad = adminService.login(admin);
        if (ad == null) {
            session.setAttribute("msg", "用户名或密码不正确！");
        } else {
            session.setAttribute("admin", ad);
        }
        return "redirect:/admin/index";
    }

    @RequestMapping("index")
    public String index(Model model) {
        int counter = commentService.getTotalCount();
        int userCount = userService.getTotalCount();
        int articleCount = articleService.getTotalCount();
        int cmtCount = commentService.getTotalCount();
        model.addAttribute("counter", counter);
        model.addAttribute("userCount", userCount);
        model.addAttribute("articleCount", articleCount);
        model.addAttribute("cmtCount", cmtCount);
        return "admin/Index";

    }
}
