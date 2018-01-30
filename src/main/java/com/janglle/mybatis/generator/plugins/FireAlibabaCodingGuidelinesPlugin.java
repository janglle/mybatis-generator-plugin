package com.janglle.mybatis.generator.plugins;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * fire AlibabaCodingGuidelines
 * @author jangle at 2018/1/30 11:32
 */
public class FireAlibabaCodingGuidelinesPlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    // ------------------------model primary key class--------------------

    @Override
    public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        modelCommentFireAlibaba(topLevelClass);
        return true;
    }

    // ------------------------model--------------------------------

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        modelCommentFireAlibaba(topLevelClass);
        return true;
    }

    @Override
    public boolean modelRecordWithBLOBsClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        modelCommentFireAlibaba(topLevelClass);
        return true;
    }

    private void modelCommentFireAlibaba(TopLevelClass topLevelClass) {
        List<String> javaDocLines = topLevelClass.getJavaDocLines();
        for (int i = 0; i< javaDocLines.size(); i++) {
            String s = javaDocLines.get(i);
            if (s.startsWith(" * @mbg.generated") && i > 0) {
                --i;
                javaDocLines.set(i, javaDocLines.get(i) + " @author mybatis-generator");
                break;
            }
        }
    }

    // -----------------------model example------------------------

    @Override
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        topLevelClass.getJavaDocLines().addAll(Arrays.asList("/**", " * example class", " * @author mybatis-generator", " */"));
        List<InnerClass> innerClasses = topLevelClass.getInnerClasses();
        for (InnerClass innerClass : innerClasses) {
            if (innerClass.isAbstract()) {
                innerClass.setAbstract(false);
                break;
            }
        }
        return true;
    }

    // ----------------------client----------------------------

    @Override
    public boolean clientCountByExampleMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        clientCommentFireAlibaba(method);
        return true;
    }

    @Override
    public boolean clientCountByExampleMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        clientCommentFireAlibaba(method);
        return true;
    }

    @Override
    public boolean clientDeleteByExampleMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        clientCommentFireAlibaba(method);
        return true;
    }

    @Override
    public boolean clientDeleteByExampleMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        clientCommentFireAlibaba(method);
        return true;
    }

    @Override
    public boolean clientDeleteByPrimaryKeyMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        clientCommentFireAlibaba(method);
        return true;
    }

    @Override
    public boolean clientDeleteByPrimaryKeyMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        clientCommentFireAlibaba(method);
        return true;
    }

    @Override
    public boolean clientInsertMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        clientCommentFireAlibaba(method);
        return true;
    }

    @Override
    public boolean clientInsertMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        clientCommentFireAlibaba(method);
        return true;
    }

    @Override
    public boolean clientSelectByExampleWithBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        clientCommentFireAlibaba(method);
        return true;
    }

    @Override
    public boolean clientSelectByExampleWithBLOBsMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        clientCommentFireAlibaba(method);
        return true;
    }

    @Override
    public boolean clientSelectByExampleWithoutBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        clientCommentFireAlibaba(method);
        return true;
    }

    @Override
    public boolean clientSelectByExampleWithoutBLOBsMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        clientCommentFireAlibaba(method);
        return true;
    }

    @Override
    public boolean clientSelectByPrimaryKeyMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        clientCommentFireAlibaba(method);
        return true;
    }

    @Override
    public boolean clientSelectByPrimaryKeyMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        clientCommentFireAlibaba(method);
        return true;
    }

    @Override
    public boolean clientUpdateByExampleSelectiveMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        clientCommentFireAlibaba(method);
        return true;
    }

    @Override
    public boolean clientUpdateByExampleSelectiveMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        clientCommentFireAlibaba(method);
        return true;
    }

    @Override
    public boolean clientUpdateByExampleWithBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        clientCommentFireAlibaba(method);
        return true;
    }

    @Override
    public boolean clientUpdateByExampleWithBLOBsMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        clientCommentFireAlibaba(method);
        return true;
    }

    @Override
    public boolean clientUpdateByExampleWithoutBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        clientCommentFireAlibaba(method);
        return true;
    }

    @Override
    public boolean clientUpdateByExampleWithoutBLOBsMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        clientCommentFireAlibaba(method);
        return true;
    }

    @Override
    public boolean clientUpdateByPrimaryKeySelectiveMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        clientCommentFireAlibaba(method);
        return true;
    }

    @Override
    public boolean clientUpdateByPrimaryKeySelectiveMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        clientCommentFireAlibaba(method);
        return true;
    }

    @Override
    public boolean clientUpdateByPrimaryKeyWithBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        clientCommentFireAlibaba(method);
        return true;
    }

    @Override
    public boolean clientUpdateByPrimaryKeyWithBLOBsMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        clientCommentFireAlibaba(method);
        return true;
    }

    @Override
    public boolean clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        clientCommentFireAlibaba(method);
        return true;
    }

    @Override
    public boolean clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        clientCommentFireAlibaba(method);
        return true;
    }

    @Override
    public boolean clientInsertSelectiveMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        clientCommentFireAlibaba(method);
        return true;
    }

    @Override
    public boolean clientInsertSelectiveMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        clientCommentFireAlibaba(method);
        return true;
    }

    @Override
    public boolean clientSelectAllMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        clientCommentFireAlibaba(method);
        return true;
    }

    @Override
    public boolean clientSelectAllMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        clientCommentFireAlibaba(method);
        return true;
    }

    private void clientCommentFireAlibaba(Method method) {
        List<Parameter> parameters = method.getParameters();
        if (parameters == null || parameters.isEmpty()) {
            return;
        }
        List<String> javaDocLines = method.getJavaDocLines();
        if (javaDocLines == null || javaDocLines.isEmpty()) {
            return;
        }
        List<String> list = new ArrayList<String>(3);
        for (Parameter parameter : parameters) {
            list.add(" * @param " + parameter.getName());
        }
        if (method.getReturnType() != null) {
            list.add(" * @return");
        }
        javaDocLines.addAll(4, list);
    }

    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        List<String> javaDocLines = interfaze.getJavaDocLines();
        javaDocLines.addAll(Arrays.asList("/**", " * mapper class", " * @author mybatis-generator", " */"));
        return true;
    }
}
