package cap.controller;

import cap.model.Article;
import cap.model.ArticleWithBLOBs;
import cap.model.User;
import cap.service.ArticleService;
import cap.service.CounterService;
import cap.service.SysCategoryService;
import cap.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {
    @Resource
    private ArticleService artService;
    @Resource
    private SysCategoryService scService;
    @Resource
    private CounterService counterService;
    @Resource
    private UserService userService;

    @RequestMapping(value = "/adminlogin",method = RequestMethod.GET)
    public String adminLogin(){
        return "AdminLogin";
    }

    @RequestMapping("/")
    public String index(){
        return "redirect:/index";
    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(ModelMap model) {
        model.addAttribute("scList", scService.getAllSysCategory());
        model.addAttribute("ulist", artService.getActiveUser(2));
        List<ArticleWithBLOBs> tenList=(List<ArticleWithBLOBs>)artService.topTenArticle();
        model.addAttribute("tenList", tenList);
        model.addAttribute("pcList", artService.getData("1").getList());
        model.addAttribute("pc", artService.getData("1"));
        model.addAttribute("count", counterService.getCounter().getNum());
        return "Index";
    }

    @RequestMapping("/login")
    public String userLogin(User user, HttpSession session) {
        User u1 = userService.login(user);
        if (null != u1) {
            if (!u1.getIsDelete()) {
                session.setAttribute("user", u1);
                return "redirect:/index";
            } else {
                session.setAttribute("userIsDeleMsg", "该用户已被禁用，无法登录");
                return "Login";
            }
        } else {
            session.setAttribute("msg", "验证失败，请重新输入用户名或密码！");
            return "Login";
        }
    }
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "Login";
    }
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String register(){
        return "Register";
    }

    @RequestMapping("/logout")
    public String  logout(HttpServletRequest request, HttpServletResponse response) {
        try{
            request.setCharacterEncoding("utf-8");
            HttpSession session = request.getSession(false);    //防止创建Session
            if (null == session) {
                return "redirect:/index";
            }else{
                session.removeAttribute("user");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/index";
    }

}
