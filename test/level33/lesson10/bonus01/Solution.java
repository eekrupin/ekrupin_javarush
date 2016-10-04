package com.javarush.test.level33.lesson10.bonus01;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.util.regex.Pattern;

/* Комментарий внутри xml
Реализовать метод toXmlWithComment, который должен возвращать строку - xml представление объекта obj.
В строке перед каждым тэгом tagName должен быть вставлен комментарий comment.
Сериализация obj в xml может содержать CDATA с искомым тегом. Перед ним вставлять комментарий не нужно.

Пример вызова:  toXmlWithComment(firstSecondObject, "second", "it's a comment")
Пример результата:
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<first>
    <!--it's a comment-->
    <second>some string</second>
    <!--it's a comment-->
    <second>some string</second>
    <!--it's a comment-->
    <second><![CDATA[need CDATA because of < and >]]></second>
    <!--it's a comment-->
    <second/>
</first>
*/
public class Solution {

    public static void main(String[] args) {
        Cat cat = new Cat ();
        cat.name = "Murka";
        cat.age = 5;
        cat.weight = 3;

        System.out.println(toXmlWithComment(cat, "name", "!comment!"));
    }

    @XmlRootElement
    public static class Cat
    {
        public String name;
        public int age;
        public int weight;
    }

    public static String toXmlWithComment(Object obj, String tagName, String comment){


        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            marshaller.marshal(obj, doc);

            NodeList nodeList = doc.getElementsByTagName("*");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeName().equals(tagName)) {
                    Comment newComment = doc.createComment(comment);
                    node.getParentNode().insertBefore(newComment, node);
                }
                replaceTextWithCDATA(node, doc);
            }

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");


            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            return writer.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        return null;

    }

    private static void replaceTextWithCDATA(Node node, Document doc)
    {
        if ((node.getNodeType() == 3) && (Pattern.compile("[<>&'\"]").matcher(node.getTextContent()).find()))
        {
            Node cnode = doc.createCDATASection(node.getNodeValue());
            node.getParentNode().replaceChild(cnode, node);
        }
        NodeList list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); i++)
        {
            replaceTextWithCDATA(list.item(i), doc);
        }
    }
}

