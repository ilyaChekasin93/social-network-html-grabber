package ru.post.grabber.params.resource.impl;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import ru.post.grabber.params.resource.ResourceParams;

import java.util.HashMap;


@Data
@Component
@EnableConfigurationProperties
@FieldDefaults(level = AccessLevel.PRIVATE)
@ConfigurationProperties(prefix = "vkontakte.resource")
public class VkResourceParams implements ResourceParams {

    @Value("${vkontakte.resource.url}")
    String url;

    @Value("${vkontakte.resource.postQueryParamName}")
    String postQueryParamName;

    HashMap<String,String> queryParams;

}
