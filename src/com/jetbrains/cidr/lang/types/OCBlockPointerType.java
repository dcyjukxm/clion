// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import java.util.List;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.intellij.util.containers.ContainerUtil;
import java.util.Collection;
import com.jetbrains.cidr.lang.refactoring.OCNameSuggester;
import java.util.Collections;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.types.visitors.OCTypeVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class OCBlockPointerType extends OCPointerType
{
    public OCBlockPointerType() {
    }
    
    public OCBlockPointerType(final OCType ocType, final ARCAttribute arcAttribute, @Nullable final OCNullability ocNullability, final boolean b, final boolean b2) {
        super(ocType, arcAttribute, null, ocNullability, b, b2);
    }
    
    public static OCBlockPointerType blockPtr(@NotNull final OCType ocType) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "refType", "com/jetbrains/cidr/lang/types/OCBlockPointerType", "blockPtr"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return blockPtr(ocType, null, false, false);
    }
    
    public static OCBlockPointerType blockPtr(@NotNull final OCType ocType, final ARCAttribute arcAttribute, final boolean b, final boolean b2) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "refType", "com/jetbrains/cidr/lang/types/OCBlockPointerType", "blockPtr"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return blockPtr(ocType, arcAttribute, null, b, b2);
    }
    
    public static OCBlockPointerType blockPtr(@NotNull final OCType ocType, final ARCAttribute arcAttribute, @Nullable final OCNullability ocNullability, final boolean b, final boolean b2) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "refType", "com/jetbrains/cidr/lang/types/OCBlockPointerType", "blockPtr"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return new OCBlockPointerType(ocType, arcAttribute, ocNullability, b, b2);
    }
    
    @Override
    public <T> T accept(final OCTypeVisitor<T> ocTypeVisitor) {
        return ocTypeVisitor.visitBlockPointerType(this);
    }
    
    @NotNull
    @Override
    public String getDefaultValue(final PsiElement psiElement) {
        final OCType refType = this.getRefType();
        if (refType instanceof OCFunctionType) {
            final OCFunctionType ocFunctionType = (OCFunctionType)refType;
            final StringBuilder sb = new StringBuilder();
            sb.append("^");
            final OCType returnType = ocFunctionType.getReturnType();
            final String name = returnType.getName();
            try {
                if (!"void".equals(name)) {
                    sb.append(name);
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            final List<OCType> parameterTypes = ocFunctionType.getParameterTypes();
            if (!parameterTypes.isEmpty()) {
                sb.append("(");
                final List<String> parameterNames = ocFunctionType.getParameterNames();
                int n = 0;
                while (true) {
                    Label_0129: {
                        try {
                            if (n >= parameterTypes.size()) {
                                break;
                            }
                            final int n2 = n;
                            if (n2 > 0) {
                                break Label_0129;
                            }
                            break Label_0129;
                        }
                        catch (IllegalArgumentException ex2) {
                            throw b(ex2);
                        }
                        try {
                            final int n2 = n;
                            if (n2 > 0) {
                                sb.append(", ");
                            }
                        }
                        catch (IllegalArgumentException ex3) {
                            throw b(ex3);
                        }
                    }
                    final OCType ocType = parameterTypes.get(n);
                    String s = null;
                    Label_0184: {
                        try {
                            if (parameterNames != null) {
                                s = parameterNames.get(n);
                                break Label_0184;
                            }
                        }
                        catch (IllegalArgumentException ex4) {
                            throw b(ex4);
                        }
                        s = "<unnamed>";
                    }
                    String s2 = s;
                    if ("<unnamed>".equals(s2)) {
                        final Collection<String> suggestForType = OCNameSuggester.suggestForType(ocType, null, (Collection<String>)Collections.emptyList());
                        String s3 = null;
                        Label_0234: {
                            try {
                                if (suggestForType.isEmpty()) {
                                    s3 = "";
                                    break Label_0234;
                                }
                            }
                            catch (IllegalArgumentException ex5) {
                                throw b(ex5);
                            }
                            s3 = (String)ContainerUtil.getFirstItem((Collection)suggestForType);
                        }
                        s2 = s3;
                    }
                    StringBuilder sb2 = null;
                    String s4 = null;
                    Label_0254: {
                        try {
                            sb2 = sb;
                            if (s2 != null) {
                                s4 = s2;
                                break Label_0254;
                            }
                        }
                        catch (IllegalArgumentException ex6) {
                            throw b(ex6);
                        }
                        s4 = "";
                    }
                    sb2.append(OCElementFactory.declarationText(s4, ocType, psiElement));
                    ++n;
                }
                sb.append(") ");
            }
            try {
                sb.append("{\n");
                if (!returnType.isVoid()) {
                    sb.append("return ");
                    sb.append(returnType.getDefaultValue(psiElement));
                    sb.append(";\n");
                }
            }
            catch (IllegalArgumentException ex7) {
                throw b(ex7);
            }
            String string;
            try {
                sb.append("}");
                string = sb.toString();
                if (string == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCBlockPointerType", "getDefaultValue"));
                }
            }
            catch (IllegalArgumentException ex8) {
                throw b(ex8);
            }
            return string;
        }
        String defaultValue;
        try {
            defaultValue = super.getDefaultValue(psiElement);
            if (defaultValue == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCBlockPointerType", "getDefaultValue"));
            }
        }
        catch (IllegalArgumentException ex9) {
            throw b(ex9);
        }
        return defaultValue;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
