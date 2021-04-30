package com.smilcool.server.base.config.elasticsearch.repository;

import com.smilcool.server.base.config.elasticsearch.document.ArticleDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends ElasticsearchRepository<ArticleDocument, Integer> {
}
