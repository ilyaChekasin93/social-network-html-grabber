package ru.post.grabber.params.resource;

import java.util.Map;

public interface ResourceParams {

    String getUrl();

    Map<String, String> getQueryParams();

    String getPostQueryParamName();

}
