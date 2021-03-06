package cap.mapper;

import cap.model.Article;
import cap.model.ArticleWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article
     *
     * @mbggenerated
     */
    int insert(ArticleWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article
     *
     * @mbggenerated
     */
    int insertSelective(ArticleWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article
     *
     * @mbggenerated
     */
    ArticleWithBLOBs selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article
     *
     * @mbggenerated
     */
    List<ArticleWithBLOBs> selectBySelective(ArticleWithBLOBs articleWithBLOBs);

    List<ArticleWithBLOBs> getArticleByPage(@Param("curPage") int curPage, @Param("size") int size);

    List<ArticleWithBLOBs> getArticlePageByUserId(@Param("curPage") int curPage, @Param("size") int size, @Param("userId") int userId);

    List<ArticleWithBLOBs> topTenArticle();
    int updateByPrimaryKeySelective(ArticleWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(ArticleWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Article record);

    public List<ArticleWithBLOBs> selectBySearch(@Param("title") String title,
                                                 @Param("content") String content,
                                                 @Param("summary") String summary);

    int count();
}