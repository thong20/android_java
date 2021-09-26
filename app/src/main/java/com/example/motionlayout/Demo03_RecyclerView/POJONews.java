package com.example.motionlayout.Demo03_RecyclerView;

public class POJONews {
    String img_thumb;

    String news_source;
    String news_title;
    String news_desc;

    String news_source_logo;

    String news_category;
    String tv_news_date;

    public POJONews(String img_thumb, String news_source, String news_title, String news_desc, String news_source_logo, String news_category, String tv_news_date) {
        this.img_thumb = img_thumb;
        this.news_source = news_source;
        this.news_title = news_title;
        this.news_desc = news_desc;
        this.news_source_logo = news_source_logo;
        this.news_category = news_category;
        this.tv_news_date = tv_news_date;
    }

    public String getImg_thumb() {
        return img_thumb;
    }

    public void setImg_thumb(String img_thumb) {
        this.img_thumb = img_thumb;
    }

    public String getNews_source() {
        return news_source;
    }

    public void setNews_source(String news_source) {
        this.news_source = news_source;
    }

    public String getNews_title() {
        return news_title;
    }

    public void setNews_title(String news_title) {
        this.news_title = news_title;
    }

    public String getNews_desc() {
        return news_desc;
    }

    public void setNews_desc(String news_desc) {
        this.news_desc = news_desc;
    }

    public String getNews_source_logo() {
        return news_source_logo;
    }

    public void setNews_source_logo(String news_source_logo) {
        this.news_source_logo = news_source_logo;
    }

    public String getNews_category() {
        return news_category;
    }

    public void setNews_category(String news_category) {
        this.news_category = news_category;
    }

    public String getTv_news_date() {
        return tv_news_date;
    }

    public void setTv_news_date(String tv_news_date) {
        this.tv_news_date = tv_news_date;
    }
}
