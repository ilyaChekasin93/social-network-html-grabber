package ru.post.grabber.client;

import ru.post.grabber.html.page.HtmlPage;

import java.util.Map;

public interface WebClient {

    void addQueryParam(String name, String value);

    void addQueryParams(Map<String, String> params);

    void addHeader(String name, String value);

    HtmlPage getPage(String url);

}
