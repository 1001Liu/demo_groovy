package com.liux.groovy.croe.constant;

import com.liux.groovy.croe.config.DynamicBean;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Iterator;

/**
 * @author :liuxin
 * @version :V1.0
 * @program : demo_groovy
 * @date :Create in 2022/7/29 15:17
 * @description :xml文 件配置文件以Document对象保存在内存中，并不真正IO输出到本地文件系统
 */
public class GroovyConfigurationXmlWriter {
    public static String DDD = "http://apache.org/xml/features/disallow-doctype-decl";

    /**
     * 配置文件内容
     */
    private String content;

    /**
     * 配置文件文档类
     */
    private Document document = null;

    /**
     * 构造函数
     */
    public GroovyConfigurationXmlWriter() {
        try {
            initDocument();
        } catch (Exception e) {
            throw new RuntimeException("加载groovy配置文件出错");
        }
    }

    /**
     * 将bean写入配置文件
     *
     * @param tagName     标签
     * @param dynamicBean 动态bean信息
     */
    public void write(String tagName, DynamicBean dynamicBean) {
        try {
            String key = "id";
            //如果配置文件中已经存在，则直接返回
            if (isExist(dynamicBean.get(key))) {
                return;
            }

            this.doWrite(tagName, dynamicBean);
            this.saveDocument();

        } catch (Exception e) {
            throw new RuntimeException("加载groovy配置文件出错");
        }
    }

    /**
     * 检查指定的bean是否已经在配置文件中
     *
     * @param beanName 脚本名称
     * @return
     */
    public boolean isExist(String beanName) {
        try {
            Node bean = this.getElementById(beanName);
            return (bean != null);
        } catch (Exception e) {
            throw new RuntimeException("加载groovy配置文件出错");
        }
    }

    /**
     * 移除所有标签节点
     *
     * @param tagName 标签
     */
    public void remove(String tagName) {
        try {
            this.doRemove(tagName);
            this.saveDocument();
        } catch (Exception e) {
            throw new RuntimeException("从配置文件中移除bean出错");
        }
    }

    /**
     * 从配置文件中移除特定的bean
     *
     * @param beanName bean名称
     */
    public void removeBean(String beanName) {
        try {
            //如果不在配置文件中，则直接返回
            if (!isExist(beanName)) {
                return;
            }

            this.doRemoveBean(beanName);
            this.saveDocument();

        } catch (Exception e) {
            throw new RuntimeException("从配置文件中移除特定的bean出错");
        }
    }

    /**
     * 执行XML文档操作
     *
     * @param tagName     标签名
     * @param dynamicBean 动态bean
     */
    private void doWrite(String tagName, DynamicBean dynamicBean) {
        try {
            Element bean = document.createElement(tagName);
            Iterator<String> iterator = dynamicBean.keyIterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                bean.setAttribute(key, dynamicBean.get(key));
            }
            NodeList list = document.getElementsByTagName("beans");
            Node beans = list.item(0);
            beans.appendChild(bean);
        } catch (Exception e) {
            throw new RuntimeException("生成XML文档出错");
        }
    }

    /**
     * 执行移除操作
     *
     * @param tagName 标签名
     */
    private void doRemove(String tagName) {
        NodeList list = document.getElementsByTagName(tagName);
        while (list.getLength() > 0) {
            list.item(0).getParentNode().removeChild(list.item(0));
        }
    }

    /**
     * 执行移除bean配置操作
     *
     * @param beanName bean名字
     */
    private void doRemoveBean(String beanName) {
        Node bean = this.getElementById(beanName);
        if (bean == null) {
            return;
        }

        bean.getParentNode().removeChild(bean);
    }

    /**
     * 保存Document
     *
     * @throws Exception
     */
    private void saveDocument() throws Exception {
        DOMSource source = new DOMSource(this.document);
        StringWriter writer = new StringWriter();
        try {
            Result result = new StreamResult(writer);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
            this.content = writer.getBuffer().toString();
        } finally {
            IOUtils.closeQuietly(writer);
        }
    }

    /**
     * 初始化document
     *
     * @throws Exception
     */
    private void initDocument() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setFeature(DDD, true);
        factory.setValidating(true);
        factory.setNamespaceAware(true);

        DocumentBuilder builder = factory.newDocumentBuilder();
        StringReader reader = new StringReader(GroovyConstant.BEANS_FILE_CONTENT);
        this.document = builder.parse(new InputSource(reader));
    }

    /**
     * 根据Id查找节点
     *
     * @param id
     * @return
     */
    private Node getElementById(String id) {
        NodeList list = document.getElementsByTagName(GroovyConstant.SPRING_TAG);
        for (int i = 0; i < list.getLength(); i++) {
            if (list.item(i).getAttributes().getNamedItem("id").getNodeValue().equals(id)) {
                return list.item(i);
            }
        }
        return null;
    }


    public String getContent() {
        return content;
    }
}
