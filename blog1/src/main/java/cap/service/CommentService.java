package cap.service;

import cap.mapper.CommentMapper;
import cap.model.Comment;
import cap.util.PageControl;

import javax.annotation.Resource;
import java.util.List;

public class CommentService {
    @Resource
    private CommentMapper commentMapper;

    public PageControl getCommentByPageArtId(int artId, String curPage){
        int total=commentMapper.count(artId);
        PageControl pc=new PageControl(curPage, total);
        int startSize=(pc.getCurPage() - 1) * pc.getPageSize();
        List<Comment> commentList=commentMapper.selectCommentByArticleId(artId,startSize, pc.getPageSize());
        pc.setList(commentList);
        return pc;
    }

    public int insertSelective(Comment cmt){
        return commentMapper.insertSelective(cmt);
    }

    public PageControl getCommentByUserId(int userId,String curPage){
        int total=commentMapper.countByUserId(userId);
        PageControl pc=new PageControl(curPage,total);
        int startSize=(pc.getCurPage() - 1) * pc.getPageSize();
        List<Comment> commentList=commentMapper.selectCommentByUserId(userId,startSize,pc.getPageSize());
        pc.setList(commentList);
        return pc;
    }

    public int getTotalCount(){
        return commentMapper.getTotalCount();
    }

}
