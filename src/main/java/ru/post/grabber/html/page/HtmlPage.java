package ru.post.grabber.html.page;

import ru.post.grabber.html.element.HtmlElement;

import java.util.List;


public interface HtmlPage {

    String getTitle();

    List<HtmlElement> getMeta();

    List<HtmlElement> getBodyElements(String xPath);

    HtmlElement getBodyElement(String xPath);

}
