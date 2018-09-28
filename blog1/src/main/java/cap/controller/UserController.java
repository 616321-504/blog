package cap.controller;

import cap.model.*;
import cap.service.*;
import cap.util.PageControl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private SysCategoryService scService;
    @Resource
    private ArticleService artService;
    @Resource
    private BlogInfoService biService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private ProfileService profileService;
    @Resource
    private CounterService counterService;

    private User u;
    private BlogInfo bi;
    //private int userId;
    private String curPage;
    private PageControl pc;
    private List<Category> cgList;
    //private Profile pf;

    private List<User> ulist;
    private List<Article> artList;
    private List<Article> tenList;
    private List<SysCategory> scList;

    private String searchStr;

    public String login() {
        return "login";
    }


    @RequestMapping("register")
    public String register(@ModelAttribute User u,HttpSession session) {
        User user1=userService.selectByUserName(u.getUsername());
        User user2=userService.selectByEmail(u.getEmail());
        if(user1!=null||user2!=null)
            session.setAttribute("existMsg", "用户名或邮箱已被注册，请重新填写！");
        else{
            int res=userService.register(u);
            if (res > 0) {
                session.setAttribute("succMsg", "注册成功");
            } else {
                session.setAttribute("errorMsg", "注册失败，请重新填写用户信息！");
            }
        }

        return "Register";
    }



    @RequestMapping("/index")
    public String index(Model model, @RequestParam(value = "curPage") String curPage) {
        model.addAttribute("scList", scService.getAllSysCategory());
        model.addAttribute("ulist", artService.getActiveUser(2)); //UserMapper.xml中相应的sql语句有点问题
        model.addAttribute("tenList", artService.topTenArticle());
        model.addAttribute("pcList", artService.getData("1").getList());
        model.addAttribute("pc", artService.getData(curPage));
        model.addAttribute("count", counterService.getCounter().getNum());
        return "Index";
    }

    public String apply() {
        return "";
    }

    @RequestMapping("/myblog")
    public String myblog(Model model,HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        BlogInfoWithBLOBs bi = biService.getBlogInfoByUserId(userId);
        List<Category> cgList = categoryService.getByUserId(userId);//获取用户的个人分类

        User u = userService.getUserById(userId);
        if (null != bi) {
            String curPageStr = request.getParameter("curPage");
            PageControl pc = artService.getArtilcePageByUserId(curPageStr, userId);
            model.addAttribute("user",u);
            model.addAttribute("bi",bi);
            model.addAttribute("pc",pc);
            model.addAttribute("cgList", cgList);

        }
        return "MyBlogIndex";
    }

    @RequestMapping("/profile")
    public String profile(Model model,@RequestParam("userId") int userId) {
        Profile pf=profileService.getByuserId(userId);
        model.addAttribute("pf",pf);
        return "Profile";
    }

    @RequestMapping("/updateprofile")
    public String updateprofile(ModelMap model, @ModelAttribute Profile pf, @RequestParam("userId") int userId) {
        User user=new User();
        user.setId(userId);
        pf.setUser(user);
        model.put("userId",userId);
        profileService.updateProfileById(pf);
        return "redirect:/user/profile";
    }

    @RequestMapping("/updatepass")
    public String updatepass(ModelMap map,HttpServletRequest request) {
        String oldPwd = request.getParameter("old_pwd");
        String newPwd1 = request.getParameter("new_pwd1");
        String newPwd2 = request.getParameter("new_pwd2");
        int userId = Integer.parseInt(request.getParameter("userId"));
        HttpSession session=request.getSession();
        if(newPwd1.equals(newPwd2)) {
            session.setAttribute("errorUpdateMsg", "修改密码失败！");
            User u = userService.getByIdPwd(userId, oldPwd);

            if (null != u) {    //验证成功，允许更新密码
                int res = userService.updatePwdById(userId, newPwd1);

                if (res > 0) {
                    session.setAttribute("succUpdateMsg", "修改密码成功！");
                } else {
                    session.setAttribute("errorUpdateMsg", "修改密码失败！");
                }

            } else {            //验证失败
                session.setAttribute("validPwdMsg", "旧密码验证失败，请重新填写！");
            }
        }
        map.put("userId",userId);


        return "redirect:/user/profile";
    }

    @RequestMapping("/search")
    public String search(Model model,@RequestParam("searchStr") String searchStr) {
        List<ArticleWithBLOBs> artList=artService.search(searchStr);
        model.addAttribute("artList",artList);
        return "SearchResult";
    }


    public String bloginfo() {
        return "";

    }

    public String updatebloginfo() {
        return "";
    }
}
