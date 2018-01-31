package com.github.janglle.mbg.plugins;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;

/**
 * rewrite setter methods in model to fluent setters, like: entity.setId(1).setName("lily").setXXX()
 *
 * @author janglle at 2018/1/31 11:33
 */
public class ModelFluentSetterPlugin extends PluginAdapter {
    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        FullyQualifiedJavaType returnType = method.getReturnType();
        if (returnType == null) {
            FullyQualifiedJavaType type = topLevelClass.getType();
            method.setReturnType(type);
            List<String> bodyLines = method.getBodyLines();
            bodyLines.add("return this;");
        }
        return true;
    }
}
