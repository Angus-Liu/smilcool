package com.smilcool.server.core.pojo.po;

public class ArticleWithBLOBs extends Article {
    private String markdownContent;

    private String htmlContent;

    public String getMarkdownContent() {
        return markdownContent;
    }

    public void setMarkdownContent(String markdownContent) {
        this.markdownContent = markdownContent;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }
}