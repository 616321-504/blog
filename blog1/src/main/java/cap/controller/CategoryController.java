package cap.controller;

import cap.model.User;
import cap.service.CategoryService;
import cap.util.PageControl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    private PageControl pc;
    @RequestMapping("/manage")
    public String manage(Model model, HttpSession session, HttpServletRequest request){
        User user= (User) session.getAttribute("user");
        int userId=Integer.parseInt(request.getParameter("userId"));
        String curPage=request.getParameter("curPage");
        pc=categoryService.getCategoryByPageUserId(curPage,userId);
        model.addAttribute("pc",pc);
        return "CategoryManage";

    }
}
