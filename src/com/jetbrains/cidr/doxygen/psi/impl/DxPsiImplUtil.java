// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.doxygen.psi.impl;

import com.jetbrains.cidr.doxygen.psi.DxParam;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.impl.source.tree.LeafElement;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.doxygen.psi.DxParamId;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.doxygen.psi.DxTypes;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.doxygen.psi.DxDocTag;

public class DxPsiImplUtil
{
    public static final String UNKNOWN = "<unknown>";
    
    @NotNull
    public static String getName(@NotNull final DxDocTag dxDocTag) {
        try {
            if (dxDocTag == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "docTag", "com/jetbrains/cidr/doxygen/psi/impl/DxPsiImplUtil", "getName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        String s = StringUtil.trimStart(StringUtil.trimStart(getOriginalName(dxDocTag), "\\"), "@");
        final int index = s.indexOf(123);
        if (index != -1) {
            s = s.substring(0, index);
        }
        String s2;
        try {
            s2 = s;
            if (s2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/psi/impl/DxPsiImplUtil", "getName"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        return s2;
    }
    
    @NotNull
    public static String getOriginalName(@NotNull final DxDocTag dxDocTag) {
        try {
            if (dxDocTag == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "docTag", "com/jetbrains/cidr/doxygen/psi/impl/DxPsiImplUtil", "getOriginalName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final ASTNode childByType = dxDocTag.getNode().findChildByType(DxTypes.TAG_NAME);
        String s = null;
        Label_0078: {
            try {
                if (childByType != null) {
                    final String text;
                    s = (text = childByType.getText());
                    break Label_0078;
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
            String text;
            s = (text = "<unknown>");
            try {
                if (text == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/psi/impl/DxPsiImplUtil", "getOriginalName"));
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
        }
        return s;
    }
    
    @NotNull
    public static String getName(@NotNull final DxParamId dxParamId) {
        try {
            if (dxParamId == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/doxygen/psi/impl/DxPsiImplUtil", "getName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        String text;
        try {
            text = dxParamId.getText();
            if (text == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/psi/impl/DxPsiImplUtil", "getName"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        return text;
    }
    
    @NotNull
    public static PsiElement setName(@NotNull final DxParamId dxParamId, @NotNull final String s) {
        try {
            if (dxParamId == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/doxygen/psi/impl/DxPsiImplUtil", "setName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "newName", "com/jetbrains/cidr/doxygen/psi/impl/DxPsiImplUtil", "setName"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        final ASTNode childByType = dxParamId.getNode().findChildByType(DxTypes.TAG_PARAM);
        DxParamId dxParamId2 = null;
        Label_0131: {
            try {
                if (!(childByType instanceof LeafElement)) {
                    throw new IncorrectOperationException();
                }
                final ASTNode astNode = childByType;
                final LeafElement leafElement = (LeafElement)astNode;
                final String s2 = s;
                leafElement.replaceWithText(s2);
                dxParamId2 = dxParamId;
                if (dxParamId2 == null) {
                    break Label_0131;
                }
                return (PsiElement)dxParamId2;
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
            try {
                final ASTNode astNode = childByType;
                final LeafElement leafElement = (LeafElement)astNode;
                final String s2 = s;
                leafElement.replaceWithText(s2);
                dxParamId2 = dxParamId;
                if (dxParamId2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/psi/impl/DxPsiImplUtil", "setName"));
                }
            }
            catch (IncorrectOperationException ex4) {
                throw a(ex4);
            }
        }
        return (PsiElement)dxParamId2;
    }
    
    @Nullable
    public static PsiElement getNameIdentifier(@NotNull final DxParamId dxParamId) {
        try {
            if (dxParamId == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/doxygen/psi/impl/DxPsiImplUtil", "getNameIdentifier"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final ASTNode childByType = dxParamId.getNode().findChildByType(DxTypes.TAG_PARAM);
        try {
            if (childByType != null) {
                return childByType.getPsi();
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        return null;
    }
    
    @NotNull
    public static List<String> getOptions(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/doxygen/psi/impl/DxPsiImplUtil", "getOptions"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final ASTNode childByType = psiElement.getNode().findChildByType(DxTypes.TAG_OPTION);
        List<String> emptyList = null;
        Label_0113: {
            List<String> list = null;
            Label_0078: {
                try {
                    if (childByType == null) {
                        break Label_0113;
                    }
                    final ASTNode astNode = childByType;
                    list = extractOptions(astNode);
                    if (list == null) {
                        break Label_0078;
                    }
                    return list;
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
                try {
                    final ASTNode astNode = childByType;
                    list = extractOptions(astNode);
                    if (list == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/psi/impl/DxPsiImplUtil", "getOptions"));
                    }
                }
                catch (IncorrectOperationException ex3) {
                    throw a(ex3);
                }
            }
            return list;
            try {
                emptyList = Collections.emptyList();
                if (emptyList == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/psi/impl/DxPsiImplUtil", "getOptions"));
                }
            }
            catch (IncorrectOperationException ex4) {
                throw a(ex4);
            }
        }
        return emptyList;
    }
    
    public static List<String> extractOptions(@NotNull final ASTNode astNode) {
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "tagName", "com/jetbrains/cidr/doxygen/psi/impl/DxPsiImplUtil", "extractOptions"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return (List<String>)StringUtil.split(StringUtil.substringBefore(StringUtil.substringAfter(astNode.getText().trim(), "["), "]"), ",").stream().map(s -> {
            s = s.trim();
            if (s.startsWith("*")) {
                s = StringUtil.substringAfter(s, "*");
            }
            if (s.endsWith("*")) {
                s = StringUtil.substringBefore(s, "*");
            }
            return s.trim();
        }).collect(Collectors.toList());
    }
    
    public static String getName(@NotNull final DxParam dxParam) {
        try {
            if (dxParam == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/doxygen/psi/impl/DxPsiImplUtil", "getName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        boolean b = false;
        Label_0070: {
            try {
                if (dxParam.getNode().findChildByType(DxTypes.ELLIPSIS) != null) {
                    b = true;
                    break Label_0070;
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
            b = false;
        }
        final boolean b2 = b;
        String string = "";
        if (b2) {
            string = "...";
        }
        final DxParamId paramId = dxParam.getParamId();
        if (paramId != null) {
            string += paramId.getName();
        }
        return string;
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
