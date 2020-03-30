package ru.post.grabber.html.element;

import java.util.List;

public interface RootHtmlElement {

    HtmlElement getElement(String xPath);

    List<HtmlElement> getElements(String xPath);

}
