package ru.post.grabber.params.post;


public interface PostLocators {

    String getPostNodeLocator();

    String getTextContentNodeLocator();

    String getPhotoNodeLocator();

    String getVideoPageRefNodeLocator();

    String getPhotoRefAttributeName();

    String getVideoPageRefAttributeName();

    String getVideoRefNodeLocator();

    String getVideoRefAttributeName();

    String getLikeNodeLocator();

}
