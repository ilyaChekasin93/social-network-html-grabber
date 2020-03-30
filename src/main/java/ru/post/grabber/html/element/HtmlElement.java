package ru.post.grabber.html.element;

public interface HtmlElement extends RootHtmlElement {

    String getText();

    String getAttribute(String name);

}
