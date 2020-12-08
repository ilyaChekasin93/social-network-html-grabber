package ru.post.grabber.client.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.post.grabber.client.WebClient;
import ru.post.grabber.client.impl.handler.DefaultErrorHandler;
import ru.post.grabber.formatter.HtmlFormatter;
import ru.post.grabber.html.element.RootHtmlElement;
import ru.post.grabber.html.page.HtmlPage;
import ru.post.grabber.html.page.impl.HtmlPageImpl;

import java.util.HashMap;
import java.util.Map;


@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WebClientImpl implements WebClient {

    RestTemplate restTemplate;

    HtmlFormatter formatter;

    Map<String, String> queryParams;

    HttpHeaders headers;


    public WebClientImpl(RestTemplateBuilder restTemplateBuilder, DefaultErrorHandler defaultErrorHandler, HtmlFormatter formatter) {
        restTemplate = restTemplateBuilder.build();
        restTemplate.setErrorHandler(defaultErrorHandler);

        queryParams = new HashMap<>();
        headers = new HttpHeaders();

        this.formatter = formatter;
    }

    public void addQueryParam(String name, String value) {
        queryParams.put(name, value);
    }

    public void addQueryParams(Map<String, String> params) {
        queryParams.putAll(params);
    }

    public void addHeader(String name, String value) {
        headers.add(name, value);
    }


    public HtmlPage getPage(String url) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);

        queryParams.forEach(uriBuilder::queryParam);

        String requestUrl = uriBuilder.toUriString();
        HttpEntity httpEntity = new HttpEntity(headers);

        ResponseEntity<String> responseEntity =
                restTemplate.exchange(requestUrl, HttpMethod.GET, httpEntity, String.class);

        queryParams.clear();

        String responseBody = responseEntity.getBody();
        RootHtmlElement rootHtmlElement = formatter.clean(responseBody);

        return new HtmlPageImpl(rootHtmlElement);
    }

}
