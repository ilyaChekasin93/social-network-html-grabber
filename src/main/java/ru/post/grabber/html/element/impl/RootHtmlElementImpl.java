package ru.post.grabber.html.element.impl;

import lombok.NoArgsConstructor;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.post.grabber.html.element.HtmlElement;
import ru.post.grabber.html.element.RootHtmlElement;
import ru.post.grabber.mapper.NodeMapper;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.util.List;
import java.util.stream.Collectors;


@NoArgsConstructor
public class RootHtmlElementImpl implements RootHtmlElement {

    protected Node node;

    private static final int FIRST_ELEMENT_INDEX = 0;

    public RootHtmlElementImpl(Node node) {
        this.node = node;
    }

    public List<HtmlElement> getElements(String xPath) {
        NodeList nodeList = executeExpression(xPath);

        return NodeMapper.nodes2ListNode(nodeList)
                .stream()
                .map(HtmlElementImpl::new)
                .collect(Collectors.toList());
    }

    public HtmlElement getElement(String xPath) {
        return new HtmlElementImpl(executeExpression(xPath).item(FIRST_ELEMENT_INDEX));
    }

    private XPathExpression getXPathExpression(String xPath) throws XPathExpressionException {
        return XPathFactory.newInstance().newXPath().compile(xPath);
    }

    private NodeList executeExpression(String xPath) {
        try {
            return (NodeList) getXPathExpression(xPath).evaluate(node, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            throw new RuntimeException(e);
        }
    }

}
