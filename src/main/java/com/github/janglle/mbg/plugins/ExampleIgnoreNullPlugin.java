package com.github.janglle.mbg.plugins;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.Arrays;
import java.util.List;

/**
 * ignore null parameters in example class's Criteria#andXXX method instead of throws an exception
 * @author jangle at 2018/1/30 14:45
 */
public class ExampleIgnoreNullPlugin extends PluginAdapter {
    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        List<InnerClass> innerClasses = topLevelClass.getInnerClasses();
        InnerClass innerClass = innerClasses.get(0);
        List<Method> methods = innerClass.getMethods();
        for (Method method : methods) {
            String name = method.getName();
            if (name == null || !name.startsWith("and")) {
                continue;
            }
            List<Parameter> parameters = method.getParameters();
            if (parameters == null || parameters.isEmpty()) {
                continue;
            }
            StringBuilder sb = new StringBuilder("if (");
            for (Parameter parameter : parameters) {
                sb.append(parameter.getName()).append(" == null || ");
            }
            sb.delete(sb.length() - " || ".length(), sb.length()).append(") {");
            List<String> bodyLines = method.getBodyLines();
            bodyLines.addAll(0, Arrays.asList(sb.toString(), "return (Criteria) this;", "}"));
        }
        return true;
    }

}
