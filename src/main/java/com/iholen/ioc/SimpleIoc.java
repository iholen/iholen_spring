package com.iholen.ioc;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author huliang
 * @date 2019-07-24 11:20
 */
public class SimpleIoc {

    private Map<String, Object> beanMap = new HashMap<>();

    public SimpleIoc(String configPath) {
        loadBeans(configPath);
    }

    public Object getBean(String beanName) {
        return beanMap.get(beanName);
    }

    private void loadBeans(String configPath) {
        // 加载 xml 配置文件
        try (InputStream inputStream = new FileInputStream(configPath)) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = factory.newDocumentBuilder();
            Document doc = docBuilder.parse(inputStream);
            Element root = doc.getDocumentElement();
            NodeList beanNodes = root.getChildNodes();

            for (int i = 0; i < beanNodes.getLength(); i++) {
                Node node = beanNodes.item(i);
                if (node instanceof Element) {
                    Element element = (Element) node;
                    String id = element.getAttribute("id");
                    String className = element.getAttribute("class");

                    System.out.println("id:" + id + "className:" + className);

                    Object bean;
                    try {
                        Class clazz = Class.forName(className);
                        bean = clazz.newInstance();
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                        e.printStackTrace();
                        return;
                    }

                    NodeList properties = element.getElementsByTagName("property");
                    for (int j = 0; j < properties.getLength(); j++) {
                        Node propertyNode = properties.item(j);
                        if (propertyNode instanceof Element) {
                            Element propertyElement = (Element)propertyNode;
                            String name = propertyElement.getAttribute("name");
                            String value = propertyElement.getAttribute("value");

                            try {
                                Field declaredField = bean.getClass().getDeclaredField(name);
                                declaredField.setAccessible(true);

                                if (existsAttribute(value)) {
                                    declaredField.set(bean, value);
                                } else {
                                    String ref = propertyElement.getAttribute("ref");
                                    if (existsAttribute(ref)) {
                                        declaredField.set(bean, getBean(ref));
                                    }
                                }

                                // register bean
                                beanMap.put(id, bean);
                            } catch (NoSuchFieldException | IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }

        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }

    }

    private boolean existsAttribute(String attribute) {
        return attribute != null && attribute.length() > 0;
    }

}
