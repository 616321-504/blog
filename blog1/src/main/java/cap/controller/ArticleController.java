package cap.controller;

import cap.model.User;
import cap.service.ArticleService;
import cap.util.PageControl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author 刘贤熔
 * @version 1.0.0
 * @createTIme 2018/9/20 10:54
 **/
@Controller
@RequestMapping("/article")
public class ArticleController {
    @Resource
    private ArticleService artService;

    @RequestMapping(value = "/manage")
    public String manage(Model model, HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        String curPage = request.getParameter("curPage");
        User u = (User) request.getSession().getAttribute("user");
        PageControl pc = artService.getArtilcePageByUserId(curPage, userId);
        model.addAttribute("artList", pc.getList());
        model.addAttribute("curPage", pc.getCurPage());
        model.addAttribute("totalPages", pc.getTotalPages());
        model.addAttribute("user", u);
        return "ArticleManage";

    }
}
