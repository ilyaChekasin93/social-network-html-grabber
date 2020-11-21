package ru.post.grabber.formatter;

import ru.post.grabber.html.element.RootHtmlElement;


public interface HtmlFormatter {

    RootHtmlElement clean(String html);

}
