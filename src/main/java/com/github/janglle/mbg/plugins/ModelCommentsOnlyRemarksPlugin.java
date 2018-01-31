package com.github.janglle.mbg.plugins;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;

/**
 * rewrite model comments, remove any remarks on setter or getter methods, only keep database remarks on filed.
 * waring: this plugin will disable auto merge model on eclipse.
 *
 * @author janglle at 2018/1/31 10:17
 */
public class ModelCommentsOnlyRemarksPlugin extends PluginAdapter {
    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        List<String> javaDocLines = field.getJavaDocLines();
        String remarks = introspectedColumn.getRemarks();
        javaDocLines.clear();
        javaDocLines.add("/** " + remarks + " */");
        return true;
    }

    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        method.getJavaDocLines().clear();
        return true;
    }

    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        method.getJavaDocLines().clear();
        return true;
    }
}
