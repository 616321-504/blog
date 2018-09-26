package cap.service;

import cap.mapper.CategoryMapper;
import cap.model.Category;
import cap.util.PageControl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    public PageControl getCategoryByPageUserId(String curPageStr, int userId){
        int total=categoryMapper.count(userId);
        PageControl pc=new PageControl(curPageStr,total);
        List<Category> categoryList=categoryMapper.getCategoryPageByUserId(pc.getCurPage(),pc.getPageSize(),userId);
        pc.setList(categoryList);
        return pc;

    }
    public List<Category> getByUserId(int userId){
        return categoryMapper.selectByUserId(userId);
    }

}
