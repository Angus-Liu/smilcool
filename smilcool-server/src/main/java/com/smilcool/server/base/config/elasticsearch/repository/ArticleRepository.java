package com.smilcool.server.base.config.elasticsearch.repository;

import com.smilcool.server.base.config.elasticsearch.document.ArticleDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Angus
 * @date 2019/5/25
 */
@Repository
public interface ArticleRepository extends ElasticsearchRepository<ArticleDocument, Integer> {
}
