package ru.post.grabber.parser;

import ru.post.grabber.dto.PostDto;
import ru.post.grabber.html.page.HtmlPage;

import java.util.List;


public interface PageParser {

    List<PostDto> getPosts(HtmlPage page);

    PostDto getLastPost(HtmlPage page);

    String getServerVideoRefs(HtmlPage page);
}
