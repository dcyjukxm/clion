// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi;

import com.intellij.psi.PsiReference;
import com.intellij.openapi.diagnostic.Logger;
import com.jetbrains.cidr.lang.OCLog;
import org.jetbrains.annotations.Contract;
import com.intellij.util.Function;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.cpp.cmake.resolve.CMakeRenameUtils;
import com.jetbrains.cidr.cpp.cmake.resolve.CMakeCommandReference;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiPolyVariantReference;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;

public abstract class CMakeCommandImplMixin extends CMakeElementBase implements CMakeCommand
{
    public CMakeCommandImplMixin(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/cpp/cmake/psi/CMakeCommandImplMixin", "<init>"));
        }
        super(astNode);
    }
    
    @NotNull
    @Override
    public String getName() {
        String text;
        try {
            text = this.getNameElement().getText();
            if (text == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeCommandImplMixin", "getName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return text;
    }
    
    @NotNull
    @Override
    public PsiElement getNameElement() {
        PsiElement firstChild;
        try {
            firstChild = this.getFirstChild();
            if (firstChild == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeCommandImplMixin", "getNameElement"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return firstChild;
    }
    
    @Override
    public PsiPolyVariantReference getReference() {
        return (PsiPolyVariantReference)new CMakeCommandReference(this.getFirstChild(), new TextRange(0, this.getFirstChild().getTextRange().getLength()));
    }
    
    public PsiElement setName(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/cpp/cmake/psi/CMakeCommandImplMixin", "setName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        CMakeRenameUtils.renameCommandCall(this.getProject(), s, this.getNode());
        return (PsiElement)this;
    }
    
    @Override
    public boolean namesEqual(@Nullable final String s) {
        return StringUtil.equalsIgnoreCase((CharSequence)s, (CharSequence)this.getName());
    }
    
    @Nullable
    @Override
    public CMakeArgument getFirstArgument() {
        return (CMakeArgument)ContainerUtil.getFirstItem((List)this.getCMakeArgumentList());
    }
    
    @Nullable
    @Override
    public String getFirstArgumentValue() {
        return a(this.getFirstArgument());
    }
    
    @NotNull
    @Override
    public List<CMakeArgument> getTailArguments() {
        final List<CMakeArgument> cMakeArgumentList = this.getCMakeArgumentList();
        List<CMakeArgument> list2 = null;
        Label_0041: {
            Label_0026: {
                try {
                    if (cMakeArgumentList == null) {
                        break Label_0026;
                    }
                    final List<CMakeArgument> list = cMakeArgumentList;
                    final int n = list.size();
                    final int n2 = 1;
                    if (n > n2) {
                        break Label_0026;
                    }
                    break Label_0026;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final List<CMakeArgument> list = cMakeArgumentList;
                    final int n = list.size();
                    final int n2 = 1;
                    if (n > n2) {
                        final List<CMakeArgument> list3;
                        list2 = (list3 = (List<CMakeArgument>)ContainerUtil.subList((List)cMakeArgumentList, 1));
                        break Label_0041;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            List<CMakeArgument> list3;
            list2 = (list3 = Collections.emptyList());
            try {
                if (list3 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeCommandImplMixin", "getTailArguments"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return list2;
    }
    
    @Nullable
    @Override
    public List<CMakeArgument> getCMakeArgumentList() {
        final CMakeCommandArguments cMakeCommandArguments = this.getCMakeCommandArguments();
        try {
            if (cMakeCommandArguments == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return cMakeCommandArguments.getCMakeArgumentList();
    }
    
    @NotNull
    @Override
    public List<String> getTailArgumentsValues() {
        List map;
        try {
            map = ContainerUtil.map((Collection)this.getTailArguments(), (Function)(cMakeArgument -> a(cMakeArgument)));
            if (map == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeCommandImplMixin", "getTailArgumentsValues"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (List<String>)map;
    }
    
    @Contract("null -> null")
    private static String a(@Nullable final CMakeArgument cMakeArgument) {
        try {
            if (cMakeArgument == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return cMakeArgument.getValue();
    }
    
    @Override
    public boolean canAppendArguments() {
        final CMakeCommandArguments cMakeCommandArguments = this.getCMakeCommandArguments();
        Label_0025: {
            try {
                if (cMakeCommandArguments == null) {
                    return false;
                }
                final CMakeCommandArguments cMakeCommandArguments2 = cMakeCommandArguments;
                final boolean b = cMakeCommandArguments2.canAppendArguments();
                if (b) {
                    break Label_0025;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final CMakeCommandArguments cMakeCommandArguments2 = cMakeCommandArguments;
                final boolean b = cMakeCommandArguments2.canAppendArguments();
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    @Override
    public void appendArgument(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "argumentText", "com/jetbrains/cidr/cpp/cmake/psi/CMakeCommandImplMixin", "appendArgument"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final CMakeCommandArguments cMakeCommandArguments = this.getCMakeCommandArguments();
        Logger log = null;
        boolean b = false;
        Label_0065: {
            try {
                log = OCLog.LOG;
                if (cMakeCommandArguments != null) {
                    b = true;
                    break Label_0065;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            b = false;
        }
        log.assertTrue(b);
        OCLog.LOG.assertTrue(cMakeCommandArguments.canAppendArguments());
        cMakeCommandArguments.appendArgument(s);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
