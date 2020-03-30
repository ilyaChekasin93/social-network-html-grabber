package ru.post.grabber.html.element.impl;

import org.w3c.dom.Node;
import ru.post.grabber.html.element.HtmlElement;


public class HtmlElementImpl extends RootHtmlElementImpl implements HtmlElement {

    private static final String EMPTY_VALUE = "";

    public HtmlElementImpl(Node node) {
        super(node);
    }

    public String getText() {
        return node != null ? node.getTextContent() : EMPTY_VALUE;
    }

    public String getAttribute(String name) {
        String result;
        try {
            result = node.getAttributes().getNamedItem(name).getNodeValue();
        } catch (NullPointerException e) {
            result = EMPTY_VALUE;
        }

        return result;
    }

}