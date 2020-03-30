package ru.post.grabber.mapper;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.AbstractList;
import java.util.List;


public class NodeMapper {

    public static List<Node> nodes2ListNode(final NodeList list) {
        return new AbstractList<Node>() {

            public int size() {
                return list.getLength();
            }

            public Node get(int index) {
                Node item = list.item(index);

                if (item == null)
                    throw new IndexOutOfBoundsException();

                return item;
            }

        };
    }

}
