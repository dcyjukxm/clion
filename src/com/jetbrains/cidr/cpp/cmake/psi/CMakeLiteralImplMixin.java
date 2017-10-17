// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi;

import com.jetbrains.cidr.cpp.cmake.resolve.CMakeCommandReferenceHelper;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.ElementManipulator;
import com.intellij.psi.PsiReferenceProvider;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.psi.ElementManipulators;
import com.intellij.psi.impl.source.resolve.reference.impl.providers.FileReferenceSet;
import com.intellij.psi.impl.source.resolve.reference.impl.providers.FileReference;
import java.util.Collections;
import java.util.Collection;
import com.intellij.util.containers.ContainerUtil;
import java.util.ArrayList;
import com.jetbrains.cidr.cpp.cmake.resolve.CMakeCommandReference;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiReference;
import com.jetbrains.cidr.cpp.cmake.resolve.CMakeRenameUtils;
import com.intellij.util.IncorrectOperationException;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;

public class CMakeLiteralImplMixin extends CMakeElementBase implements CMakeLiteralMixin
{
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public CMakeLiteralImplMixin(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/cpp/cmake/psi/CMakeLiteralImplMixin", "<init>"));
        }
        super(astNode);
    }
    
    @NotNull
    public PsiElement setName(@NonNls @NotNull final String s) throws IncorrectOperationException {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/cpp/cmake/psi/CMakeLiteralImplMixin", "setName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            CMakeRenameUtils.renameArgument(this.getProject(), s, this.getNode());
            if (this == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeLiteralImplMixin", "setName"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        return (PsiElement)this;
    }
    
    @Nullable
    public PsiReference getReference() {
        final PsiReference a = this.a();
        try {
            if (a != null) {
                return a;
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return super.getReference();
    }
    
    @Nullable
    private PsiReference a() {
        if (this.getParent() instanceof CMakeArgument) {
            final CMakeArgument cMakeArgument = (CMakeArgument)this.getParent();
            Label_0047: {
                try {
                    if (cMakeArgument.isCommandDefinitionName()) {
                        break Label_0047;
                    }
                    final CMakeArgument cMakeArgument2 = cMakeArgument;
                    final CMakeLiteralImplMixin cMakeLiteralImplMixin = this;
                    final String s = cMakeLiteralImplMixin.getText();
                    final boolean b = cMakeArgument2.isEndCommandDefinitionName(s);
                    if (b) {
                        break Label_0047;
                    }
                    return null;
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                try {
                    final CMakeArgument cMakeArgument2 = cMakeArgument;
                    final CMakeLiteralImplMixin cMakeLiteralImplMixin = this;
                    final String s = cMakeLiteralImplMixin.getText();
                    final boolean b = cMakeArgument2.isEndCommandDefinitionName(s);
                    if (b) {
                        return (PsiReference)new CMakeCommandReference((PsiElement)cMakeArgument, cMakeArgument.getTextRange());
                    }
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
            }
        }
        return null;
    }
    
    @NotNull
    public String getName() {
        String text;
        try {
            text = this.getText();
            if (text == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeLiteralImplMixin", "getName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return text;
    }
    
    @NotNull
    public String getText() {
        Label_0062: {
            String s = null;
            Label_0027: {
                try {
                    if (this.getFirstChild() == null) {
                        break Label_0062;
                    }
                    final CMakeLiteralImplMixin cMakeLiteralImplMixin = this;
                    final PsiElement psiElement = cMakeLiteralImplMixin.getFirstChild();
                    s = psiElement.getText();
                    if (s == null) {
                        break Label_0027;
                    }
                    return s;
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                try {
                    final CMakeLiteralImplMixin cMakeLiteralImplMixin = this;
                    final PsiElement psiElement = cMakeLiteralImplMixin.getFirstChild();
                    s = psiElement.getText();
                    if (s == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeLiteralImplMixin", "getText"));
                    }
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
            }
            return s;
        }
        for (final PsiElement psiElement2 : this.getChildren()) {
            Label_0145: {
                String s2 = null;
                Label_0110: {
                    try {
                        if (psiElement2 == null) {
                            break Label_0145;
                        }
                        final PsiElement psiElement3 = psiElement2;
                        s2 = psiElement3.getText();
                        if (s2 == null) {
                            break Label_0110;
                        }
                        return s2;
                    }
                    catch (IncorrectOperationException ex3) {
                        throw a(ex3);
                    }
                    try {
                        final PsiElement psiElement3 = psiElement2;
                        s2 = psiElement3.getText();
                        if (s2 == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeLiteralImplMixin", "getText"));
                        }
                    }
                    catch (IncorrectOperationException ex4) {
                        throw a(ex4);
                    }
                }
                return s2;
            }
        }
        String s3;
        try {
            s3 = "";
            if (s3 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeLiteralImplMixin", "getText"));
            }
        }
        catch (IncorrectOperationException ex5) {
            throw a(ex5);
        }
        return s3;
    }
    
    @NotNull
    public PsiReference[] getReferences() {
        Label_0140: {
            try {
                if (!this.getArgument().isCommandDefinitionName()) {
                    if (!this.getArgument().isEndCommandDefinitionName(this.getText())) {
                        break Label_0140;
                    }
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            final PsiReference a = this.a();
            PsiReference[] empty_ARRAY = null;
            Label_0098: {
                PsiReference[] array = null;
                Label_0063: {
                    try {
                        if (a == null) {
                            break Label_0098;
                        }
                        final int n = 1;
                        array = new PsiReference[n];
                        final int n2 = 0;
                        final PsiReference psiReference = a;
                        array[n2] = psiReference;
                        if (array == null) {
                            break Label_0063;
                        }
                        return array;
                    }
                    catch (IncorrectOperationException ex2) {
                        throw a(ex2);
                    }
                    try {
                        final int n = 1;
                        array = new PsiReference[n];
                        final int n2 = 0;
                        final PsiReference psiReference = a;
                        array[n2] = psiReference;
                        if (array == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeLiteralImplMixin", "getReferences"));
                        }
                    }
                    catch (IncorrectOperationException ex3) {
                        throw a(ex3);
                    }
                }
                return array;
                try {
                    empty_ARRAY = PsiReference.EMPTY_ARRAY;
                    if (empty_ARRAY == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeLiteralImplMixin", "getReferences"));
                    }
                }
                catch (IncorrectOperationException ex4) {
                    throw a(ex4);
                }
            }
            return empty_ARRAY;
        }
        final FileReferenceSet set = this.createSet();
        final ArrayList<Object> list = new ArrayList<Object>();
        final PsiReference reference = this.getReference();
        PsiReference[] array2;
        try {
            ContainerUtil.addIfNotNull((Collection)list, (Object)reference);
            Collections.addAll(list, set.getAllReferences());
            array2 = list.toArray(new PsiReference[list.size()]);
            if (array2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeLiteralImplMixin", "getReferences"));
            }
        }
        catch (IncorrectOperationException ex5) {
            throw a(ex5);
        }
        return array2;
    }
    
    @NotNull
    public FileReferenceSet createSet() {
        final ElementManipulator manipulator = ElementManipulators.getManipulator((PsiElement)this);
        Label_0024: {
            try {
                if (CMakeLiteralImplMixin.$assertionsDisabled) {
                    break Label_0024;
                }
                final ElementManipulator elementManipulator = manipulator;
                if (elementManipulator == null) {
                    break Label_0024;
                }
                break Label_0024;
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            try {
                final ElementManipulator elementManipulator = manipulator;
                if (elementManipulator == null) {
                    throw new AssertionError();
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
        }
        final TextRange rangeInElement = manipulator.getRangeInElement((PsiElement)this);
        final int startOffset = rangeInElement.getStartOffset();
        final String substring = rangeInElement.substring(super.getText());
        CMakeFileReferenceSet set;
        try {
            set = new CMakeFileReferenceSet(substring, (PsiElement)this, startOffset, null, SystemInfo.isFileSystemCaseSensitive, false);
            if (set == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeLiteralImplMixin", "createSet"));
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        return set;
    }
    
    @Override
    public CMakeArgument getArgument() {
        return (CMakeArgument)this.getParent();
    }
    
    @Override
    public boolean equalByText(final CMakeLiteralMixin cMakeLiteralMixin) {
        return CMakeCommandReferenceHelper.commandNamesEqualByText((PsiElement)this, (PsiElement)cMakeLiteralMixin);
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!CMakeLiteralImplMixin.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
