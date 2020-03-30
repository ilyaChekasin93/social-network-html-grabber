package ru.post.grabber.params.post.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.post.grabber.params.post.PostLocators;


@Getter
@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VkPostLocators implements PostLocators {

    @Value("${vkontakte.post.locators.baseNode}")
    String postNodeLocator;

    @Value("${vkontakte.post.locators.textContentNode}")
    String textContentNodeLocator;

    @Value("${vkontakte.post.locators.photoNode}")
    String photoNodeLocator;

    @Value("${vkontakte.post.locators.videoPageRefNode}")
    String videoPageRefNodeLocator;

    @Value("${vkontakte.post.locators.videoRefNode}")
    String videoRefNodeLocator;

    @Value("${vkontakte.post.locators.likeNode}")
    String likeNodeLocator;

    @Value("${vkontakte.post.locators.photoRefAttributeName}")
    String photoRefAttributeName;

    @Value("${vkontakte.post.locators.videoPageRefAttributeName}")
    String videoPageRefAttributeName;

    @Value("${vkontakte.post.locators.videoRefAttributeName}")
    String videoRefAttributeName;

}
