package ru.post.grabber.mapper;

import org.mapstruct.Mapper;
import ru.post.grabber.dto.PostDto;
import ru.post.grabber.model.PostResponse;

import java.util.List;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface PostMapper {

    PostResponse postDto2Post(PostDto postDto);

    default List<PostResponse> postDtoList2Posts(List<PostDto> postDtoList) {
        return postDtoList.stream()
                .map(p -> postDto2Post(p))
                .collect(Collectors.toList());
    }

}
