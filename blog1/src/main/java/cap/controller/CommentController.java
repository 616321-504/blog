package cap.controller;

import cap.model.*;
import cap.service.ArticleService;
import cap.util.PageControl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/comment")
public class CommentController {
    private String curPage;
    private PageControl pc;


    @Resource
    private ArticleService articleService;
    @Resource
    private CommentService commentService;

    @RequestMapping("/post")
    public String post(HttpSession session,
                       HttpServletRequest request,
                       Model model){
        int artId=Integer.parseInt(request.getParameter("artId"));
        curPage=request.getParameter("curPage");
        User u = (User) session.getAttribute("user");
        Article article = articleService.selectByPrimaryKey(artId);
        articleService.updateCount(artId);
        pc = commentService.getCommentByPageArtId(artId,curPage);
        model.addAttribute("article",article);
        model.addAttribute("pc",pc);
        return "Post";
    }
    @RequestMapping("/commit")
    public String commit(ModelMap model,
                         @RequestParam("userId") int userId,
                         @RequestParam("artId") int artId,
                         HttpSession session,
                         @ModelAttribute Comment cmt,
                         HttpServletRequest request) {
        User u= (User) session.getAttribute("user");
        cmt.setUser(u);
        Article article=new Article();
        article.setId(artId);
        cmt.setArticle(article);
        curPage=request.getParameter("curPage");
        if (u != null) {
            int res = commentService.insertSelective(cmt);
            if (res > 0) {
                session.setAttribute("succMsg", "评论文章成功！");
            } else {
                session.setAttribute("errorMsg","评论文章失败！");
            }
            Article art = articleService.selectByPrimaryKey(artId);
            pc = commentService.getCommentByPageArtId(artId, curPage);
            session.setAttribute("article", art);
            session.setAttribute("pc", pc);
            //下面用来传递参数
            model.put("userId",userId);
            model.put("artId",artId);
        }
        return "redirect:/comment/post";

    }
    @RequestMapping("/manage")
    public String manage(Model model, HttpSession session, HttpServletRequest request){
        User user= (User) session.getAttribute("user");
        int userId=Integer.parseInt(request.getParameter("userId"));
        String curPage=request.getParameter("curPage");
        pc=commentService.getCommentByUserId(userId,curPage);
        model.addAttribute("pc",pc);
        return "CommentManage";

    }
}
