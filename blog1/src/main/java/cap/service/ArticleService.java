package cap.service;

import cap.mapper.ArticleMapper;
import cap.mapper.UserMapper;
import cap.model.Article;
import cap.model.ArticleWithBLOBs;
import cap.model.User;
import cap.util.PageControl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 刘贤熔
 * @version 1.0.0
 * @createTIme 2018/9/20 10:56
 **/
@Service
public class ArticleService {
    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private UserMapper userMapper;

    public int insertArtical(ArticleWithBLOBs art) {
        art.setIsTop(false);
        art.setIsDelete(false);
        art.setCount(0);
        articleMapper.insertSelective(art);
        return 0;
    }

    public PageControl getData(String curPageStr) {
        int total = articleMapper.count();
        PageControl pc = new PageControl(curPageStr, total);
        List<ArticleWithBLOBs> artList = articleMapper.getArticleByPage((pc.getCurPage() - 1) * pc.getPageSize(), pc.getPageSize());
        pc.setList(artList);
        return pc;
    }

    public PageControl getArtilcePageByUserId(String curPageStr, int userId) {
        int total = articleMapper.count();
        PageControl pc = new PageControl(curPageStr, total);
        List<ArticleWithBLOBs> artList = articleMapper.getArticlePageByUserId((pc.getCurPage() - 1) * pc.getPageSize(), pc.getPageSize(), userId);
        pc.setList(artList);
        return pc;
    }

    public List<User> getActiveUser(int num) {
        return userMapper.getActiveUser(num);
    }

    public List<ArticleWithBLOBs> topTenArticle() {
        return articleMapper.topTenArticle();
    }

    public Article selectByPrimaryKey(int artId) {
        return articleMapper.selectByPrimaryKey(artId);
    }

    public void updateCount(int artId) {
        Article article = articleMapper.selectByPrimaryKey(artId);
        article.setCount(article.getCount() + 1);
        articleMapper.updateByPrimaryKey(article);
    }

    public List<ArticleWithBLOBs> search(String str) {
        return articleMapper.selectBySearch(str, str, str);
    }

    public int getTotalCount() {
        return articleMapper.count();
    }
}
