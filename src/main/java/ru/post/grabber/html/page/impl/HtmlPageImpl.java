package ru.post.grabber.html.page.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import ru.post.grabber.html.element.HtmlElement;
import ru.post.grabber.html.element.RootHtmlElement;
import ru.post.grabber.html.page.HtmlPage;

import java.util.List;


@FieldDefaults(level = AccessLevel.PRIVATE)
public class HtmlPageImpl implements HtmlPage {

    static final String BODY = ".//body";

    static final String HEAD = ".//head";

    static final String META = ".//meta";

    static final String TITLE = ".//title";

    private HtmlElement body;

    private HtmlElement head;

    public HtmlPageImpl(RootHtmlElement rootElement) {
        body = rootElement.getElement(BODY);
        head = rootElement.getElement(HEAD);
    }

    public String getTitle() {
        return head.getElement(TITLE).getText();
    }

    public List<HtmlElement> getMeta() {
        return head.getElements(META);
    }

    public List<HtmlElement> getBodyElements(String xPath) {
        return body.getElements(xPath);
    }

    public HtmlElement getBodyElement(String xPath) {
        return body.getElement(xPath);
    }

}
