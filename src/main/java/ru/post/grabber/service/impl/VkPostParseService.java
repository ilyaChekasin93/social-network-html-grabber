package ru.post.grabber.service.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.post.grabber.client.WebClient;
import ru.post.grabber.dto.PostDto;
import ru.post.grabber.html.page.HtmlPage;
import ru.post.grabber.params.resource.ResourceParams;
import ru.post.grabber.parser.PageParser;
import ru.post.grabber.service.PostParseService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;


@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VkPostParseService implements PostParseService {

    static final String PHOTO_REF_DELIMITER = "\\|";

    static final int PHOTO_REF_INDEX = 0;

    PageParser pageParser;

    WebClient client;

    String baseUrl;

    String postQueryParamName;

    Map<String, String> queryParams;


    public VkPostParseService(PageParser pageParser, WebClient client, ResourceParams options) {
        this.pageParser = pageParser;
        this.client = client;
        this.baseUrl = options.getUrl();
        this.postQueryParamName = options.getPostQueryParamName();
        this.queryParams = options.getQueryParams();
    }


    public List<PostDto> getPosts(String publicName, int postQuantity) {
        List<PostDto> posts = new ArrayList<>();

        while (posts.size() < postQuantity){
            client.addQueryParams(queryParams);
            client.addQueryParam(postQueryParamName, String.valueOf(posts.size()));

            String url = String.format("%s/%s", baseUrl, publicName);
            HtmlPage page = client.getPage(url);
            List<PostDto> receivedPosts = pageParser.getPosts(page);

            if(receivedPosts.size() == 0){
                break;
            }

            posts.addAll(receivedPosts);
        }

        if(posts.size() != postQuantity)
            posts = posts.stream().limit(postQuantity).collect(toList());

        return posts.stream()
                .map(p -> {
                    String postContent = p.getContent();
                    List<String> postPhoto = getClearPostPhotoRefs(p.getPhotos());
                    List<String> postVideo = getPostVideoRefs(p.getVideos());
                    String postLikeCount = p.getLikeCount();

                    return new PostDto(postContent, postPhoto, postVideo, postLikeCount);
                }).collect(toList());

    }

    public PostDto getLastPost(String publicName){
        client.addQueryParams(queryParams);
        String url = String.format("%s/%s", baseUrl, publicName);
        HtmlPage page = client.getPage(url);

        return pageParser.getLastPost(page);
    }

    private List<String> getPostVideoRefs(List<String> videoRefs){
        return videoRefs.stream()
                .map(v -> {
                    HtmlPage page = client.getPage(baseUrl + v);
                    return pageParser.getServerVideoRefs(page);
                }).collect(toList());
    }

    private List<String> getClearPostPhotoRefs(List<String> photoRefs){
        return photoRefs.stream()
                .map(r -> r.split(PHOTO_REF_DELIMITER)[PHOTO_REF_INDEX])
                .collect(toList());
    }

}
