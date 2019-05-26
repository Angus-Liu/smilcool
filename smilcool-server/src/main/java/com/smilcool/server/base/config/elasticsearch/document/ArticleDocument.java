package com.smilcool.server.base.config.elasticsearch.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.List;

/**
 * @author Angus
 * @date 2019/4/29
 */
@Data
@Document(indexName = "article", type = "_doc")
public class ArticleDocument {
    @Id
    private Integer id;

    private Integer userId;

    private Integer resourceId;

    private String articleCategory;

    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String title;

    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String brief;

    private String cover;

    private String tags;

    @Field(type = FieldType.Keyword)
    private List<String> tagList;

    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String markdownContent;

    private String htmlContent;

    private Date createTime;

    private Date updateTime;
}
