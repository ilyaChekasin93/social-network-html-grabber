package ru.post.grabber.parser.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import ru.post.grabber.dto.PostDto;
import ru.post.grabber.html.element.HtmlElement;
import ru.post.grabber.html.page.HtmlPage;
import ru.post.grabber.params.post.PostLocators;
import ru.post.grabber.parser.PageParser;

import java.util.List;
import java.util.stream.Collectors;


@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageParserImpl implements PageParser {

    String postNodeLocator;

    String textContentNodeLocator;

    String photoNodeLocator;

    String photoRefAttribute;

    String videoPageRefNodeLocator;

    String videoPageRefAttributeName;

    String videoRefNodeLocator;

    String videoRefAttributeName;

    String likeNodeLocator;


    public PageParserImpl(PostLocators options) {
        this.postNodeLocator = options.getPostNodeLocator();
        this.textContentNodeLocator = options.getTextContentNodeLocator();
        this.photoNodeLocator = options.getPhotoNodeLocator();
        this.photoRefAttribute = options.getPhotoRefAttributeName();
        this.videoPageRefNodeLocator = options.getVideoPageRefNodeLocator();
        this.videoPageRefAttributeName = options.getVideoPageRefAttributeName();
        this.videoRefNodeLocator = options.getVideoRefNodeLocator();
        this.videoRefAttributeName = options.getVideoRefAttributeName();
        this.likeNodeLocator = options.getLikeNodeLocator();
    }

    public List<PostDto> getPosts(HtmlPage page) {
        return page.getBodyElements(postNodeLocator).stream()
                .map(e -> new PostDto(
                        getTextContent(e),
                        getPhotoRefs(e),
                        getVideoRefs(e),
                        getLikeCount(e)
                )).collect(Collectors.toList());
    }

    public PostDto getLastPost(HtmlPage page) {
        HtmlElement postElement = page.getBodyElement(postNodeLocator);
        String postTextContent = getTextContent(postElement);
        List<String> postPhotos = getPhotoRefs(postElement);
        List<String> postVideos = getVideoRefs(postElement);
        String postLikeCount = getLikeCount(postElement);

        return new PostDto(postTextContent, postPhotos, postVideos, postLikeCount);
    }

    public String getServerVideoRefs(HtmlPage page) {
        return page.getBodyElement(videoRefNodeLocator).getAttribute(videoRefAttributeName);
    }

    private List<String> getPhotoRefs(HtmlElement element) {
        return element.getElements(photoNodeLocator).stream()
                .map(el -> el.getAttribute(photoRefAttribute))
                .collect(Collectors.toList());
    }

    private List<String> getVideoRefs(HtmlElement element) {
        return element.getElements(videoPageRefNodeLocator).stream()
                .map(vel -> vel.getAttribute(videoPageRefAttributeName))
                .collect(Collectors.toList());
    }

    private String getTextContent(HtmlElement element) {
        return element.getElement(textContentNodeLocator).getText();
    }

    private String getLikeCount(HtmlElement element) {
        return element.getElement(likeNodeLocator).getText();
    }

}
