package ru.post.grabber.controller;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.post.grabber.dto.PostDto;
import ru.post.grabber.mapper.PostMapper;
import ru.post.grabber.model.PostResponse;
import ru.post.grabber.service.PostParseService;

import java.util.List;


@RestController
@RequestMapping("/vk")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VkPostController {

    PostParseService service;

    PostMapper mapper;

    public VkPostController(PostParseService service, PostMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping(path = "/posts")
    public List<PostResponse> getPosts(String group, Integer quantity) {
        List<PostDto> postDtoList = service.getPosts(group, quantity);
        return mapper.postDtoList2Posts(postDtoList);
    }

    @GetMapping(path = "/post")
    public PostResponse getPost(String group) {
        PostDto lastPost = service.getLastPost(group);
        return mapper.postDto2Post(lastPost);
    }

}
