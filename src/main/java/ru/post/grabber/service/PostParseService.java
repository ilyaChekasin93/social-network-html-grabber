package ru.post.grabber.service;

import ru.post.grabber.dto.PostDto;

import java.util.List;


public interface PostParseService {

    List<PostDto> getPosts(String publicName, int postQuantity);

    PostDto getLastPost(String publicName);

}
