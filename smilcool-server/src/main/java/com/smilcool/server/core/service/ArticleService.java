package com.smilcool.server.core.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smilcool.server.base.config.elasticsearch.document.ArticleDocument;
import com.smilcool.server.common.enums.DicTypeEnum;
import com.smilcool.server.common.exception.SmilcoolException;
import com.smilcool.server.common.util.BeanUtil;
import com.smilcool.server.core.dao.ArticleMapper;
import com.smilcool.server.core.pojo.form.ArticleAddForm;
import com.smilcool.server.core.pojo.form.ArticleQueryForm;
import com.smilcool.server.core.pojo.po.Article;
import com.smilcool.server.core.pojo.vo.ArticleLatestCommentVO;
import com.smilcool.server.core.pojo.vo.ArticleVO;
import com.smilcool.server.core.pojo.vo.TagVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ArticleService {

    private final ArticleMapper articleMapper;
    private final ResourceService resourceService;
    private final UserService userService;

    @Transactional(rollbackFor = Exception.class)
    public Article addArticle(ArticleAddForm form) {
        // 获取当前登陆用户
        Integer currentUserId = userService.currentUserId();
        // 添加资源，获取资源ID
        Integer resourceId = resourceService
                .addResource(currentUserId, DicTypeEnum.ARTICLE_CATEGORY.name, form.getArticleCategory());
        // 添加文章
        Article article = BeanUtil.copyProp(form, Article.class);
        article.setUserId(currentUserId);
        article.setResourceId(resourceId);
        articleMapper.insertSelective(article);
        return getArticle(article.getId());
    }

    public Article getArticle(Integer id) {
        Article article = articleMapper.selectByPrimaryKey(id);
        if (article == null) {
            throw new SmilcoolException("文章不存在");
        }
        return article;
    }

    public List<Article> listArticle(ArticleQueryForm form) {
        return articleMapper.selectByQueryForm(form);
    }

    public ArticleVO getArticleVO(Integer id) {
        ArticleVO articleVO = articleMapper.selectArticleVOByPrimaryKey(id);
        if (articleVO == null) {
            throw new SmilcoolException("文章不存在");
        }
        return articleVO;
    }

    public Page<ArticleVO> pageArticleVO(Page page, ArticleQueryForm form) {
        return articleMapper.selectArticleVOByQueryForm(page, form);
    }

    public List<ArticleLatestCommentVO> listArticleLatestCommentVO() {
        return articleMapper.selectArticleLatestCommentVO();
    }

    public List<TagVO> listHotTag() {
//        List<TagVO> hotTagList = new ArrayList<>();
//        // 构建搜索条件
//        SearchQuery searchQuery = new NativeSearchQueryBuilder()
//                // 指定索引
//                .withIndices("article")
//                // 指定类型
//                .withTypes("_doc")
//                // 对 tagList 字段进行聚集搜索，查找热门标签（TOP20）
//                .addAggregation(AggregationBuilders.terms("tagList").field("tagList").size(20))
//                .build();
//        // 执行聚集搜索
//        Aggregations aggregations = elasticsearchTemplate.query(searchQuery, SearchResponse::getAggregations);
//        // 获取热门标签
//        StringTerms terms = (StringTerms) aggregations.getAsMap().get("tagList");
//        List<Bucket> buckets = terms.getBuckets();
//        buckets.forEach(bucket -> hotTagList.add(new TagVO(bucket.getKey().toString(), bucket.getDocCount())));

//        return hotTagList;
        return Collections.emptyList();
    }

    public org.springframework.data.domain.Page<ArticleDocument> search(String q, Integer page, Integer size) {
//        // 创建一个SearchQuery对象
//        SearchQuery searchQuery = new NativeSearchQueryBuilder()
//                // 设置查询条件，此处可以使用 QueryBuilders 创建多种查询
//                .withQuery(QueryBuilders.queryStringQuery(q))
//                // 高亮配置（未匹配时仍然显示 100 字符）
//                .withHighlightBuilder(new HighlightBuilder().noMatchSize(100))
//                // 高亮字段
//                .withHighlightFields(
//                        new HighlightBuilder.Field("title"),
//                        new HighlightBuilder.Field("brief"))
//                // 分页信息
//                .withPageable(PageRequest.of(page, size))
//                // 创建SearchQuery对象
//                .build();
//        return elasticsearchTemplate.queryForPage(searchQuery, ArticleDocument.class, new HighlightResultMapper());
        return null;
    }
}
