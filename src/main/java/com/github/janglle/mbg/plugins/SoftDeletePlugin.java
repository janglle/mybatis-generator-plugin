package com.github.janglle.mbg.plugins;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Element;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * soft delete by a specified column, and select, update method generated will filter this column.
 * this column should be defined in mybatis generator config xml as below, where the words "deleted" should be replaced with your own:
 * <plugin type="com.mgzf.mybatis.generator.plugins.SoftDeletePlugin">
 * <property name="column" value="deleted"/>
 * </plugin>
 *
 * @author jangle at 2018/1/29 19:04
 */
public class SoftDeletePlugin extends PluginAdapter {
    /**
     * can be set from mybatis-generator config xml.
     * the column name which used to indicate whether the record is deleted or not.
     */
    private String col;
    /**
     * can be set from mybatis-generator config xml.
     * if true, the model(entity) class and example class will not generate this delete mark column.
     */
    private boolean ignoreColInModel = true;
    /**
     * used to determine whether it is needed to rewrite sqlMap
     */
    private boolean hasCol = false;

    /**
     * set col and ignoreColInModel
     *
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        col = properties.getProperty("column");
        String property = properties.getProperty("ignoreColInModel", "true");
        ignoreColInModel = Boolean.parseBoolean(property);
    }

    @Override
    public void initialized(IntrospectedTable introspectedTable) {
        List<IntrospectedColumn> baseColumns = introspectedTable.getBaseColumns();
        Iterator<IntrospectedColumn> it = baseColumns.iterator();
        while (it.hasNext()) {
            String actualColumnName = it.next().getActualColumnName();
            if (actualColumnName != null && actualColumnName.equalsIgnoreCase(col)) {
                hasCol = true;
                if (ignoreColInModel) {
                    it.remove();
                }
                break;
            }
        }
    }

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean sqlMapExampleWhereClauseElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        if (hasCol) {
            List<Element> elements = element.getElements();
            for (Element e : elements) {
                if (e instanceof XmlElement) {
                    XmlElement deleted = new XmlElement("if");
                    deleted.addAttribute(new Attribute("test", "true"));
                    deleted.addElement(new TextElement("AND " + col + " = 1"));
                    ((XmlElement) e).addElement(deleted);
                }
            }
        }
        return true;
    }

    @Override
    public boolean sqlMapSelectByPrimaryKeyElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        sqlMapWhereIdAndNotSoftDelete(element, introspectedTable);
        return true;
    }

    @Override
    public boolean sqlMapUpdateByPrimaryKeySelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        sqlMapWhereIdAndNotSoftDelete(element, introspectedTable);
        return true;
    }

    @Override
    public boolean sqlMapUpdateByPrimaryKeyWithBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        sqlMapWhereIdAndNotSoftDelete(element, introspectedTable);
        return true;
    }

    @Override
    public boolean sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        sqlMapWhereIdAndNotSoftDelete(element, introspectedTable);
        return true;
    }

    private void sqlMapWhereIdAndNotSoftDelete(XmlElement element, IntrospectedTable introspectedTable) {
        if (!hasCol) {
            return;
        }
        List<Element> elements = element.getElements();
        Iterator<Element> iterator = elements.iterator();
        while (iterator.hasNext()) {
            Element next = iterator.next();
            if (next instanceof TextElement) {
                TextElement e = (TextElement) next;
                String content = e.getContent();
                if (content != null && content.startsWith("where id")) {
                    iterator.remove();
                    elements.add(new TextElement(content + " AND " + col + " = 1"));
                    break;
                }
            }
        }
    }

    @Override
    public boolean sqlMapDeleteByPrimaryKeyElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        if (hasCol) {
            List<Element> elements = element.getElements();
            for (int i = 0; i < elements.size(); i++) {
                Element ele = elements.get(i);
                if (!(ele instanceof TextElement)) {
                    continue;
                }
                TextElement e = (TextElement) ele;
                String content = e.getContent();
                if (content == null) {
                    continue;
                }
                if (content.startsWith("delete")) {
                    elements.set(i, new TextElement(content.replace("delete from", "update") + " set " + col + " = 0"));
                }
                if (content.startsWith("where id = ")) {
                    elements.set(i, new TextElement(content + " AND " + col + " = 1"));
                }
            }
        }
        return true;
    }

    @Override
    public boolean sqlMapDeleteByExampleElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        if (hasCol) {
            List<Element> elements = element.getElements();
            for (int i = 0; i < elements.size(); i++) {
                Element ele = elements.get(i);
                if (!(ele instanceof TextElement)) {
                    continue;
                }
                TextElement e = (TextElement) ele;
                String content = e.getContent();
                if (content == null) {
                    continue;
                }
                if (content.startsWith("delete")) {
                    elements.set(i, new TextElement(content.replace("delete from", "update") + " set " + col + " = 0"));
                }
            }
        }
        return true;
    }

}