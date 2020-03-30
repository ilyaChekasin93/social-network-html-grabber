package ru.post.grabber.formatter.impl;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import ru.post.grabber.formatter.HtmlFormatter;
import ru.post.grabber.html.element.RootHtmlElement;
import ru.post.grabber.html.element.impl.RootHtmlElementImpl;

import javax.xml.parsers.ParserConfigurationException;


@Component
public class HtmlFormatterImpl implements HtmlFormatter {

    public RootHtmlElement clean(String html) {
        HtmlCleaner htmlCleaner = new HtmlCleaner();
        TagNode tagNode = htmlCleaner.clean(html);
        CleanerProperties cleanerProperties = new CleanerProperties();
        DomSerializer domSerializer = new DomSerializer(cleanerProperties);

        Element element;

        try {
            element = domSerializer.createDOM(tagNode).getDocumentElement();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e.getMessage());
        }

        return new RootHtmlElementImpl(element);
    }

}
