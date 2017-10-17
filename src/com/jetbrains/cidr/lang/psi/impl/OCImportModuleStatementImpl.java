// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.resolve.references.OCImportModuleReference;
import com.intellij.psi.PsiReference;
import com.jetbrains.cidr.lang.psi.OCIncludeDirective;
import java.util.ArrayList;
import java.util.Collection;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;

public class OCImportModuleStatementImpl extends OCIncludeDirectiveImpl
{
    public OCImportModuleStatementImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCImportModuleStatementImpl", "<init>"));
        }
        super(astNode);
    }
    
    @NotNull
    @Override
    public PsiElement getHeaderToken() {
        final PsiElement childByType = this.findChildByType(OCTokenTypes.IMPORT_MODULE_KEYWORD);
        PsiElement psiElement = null;
        Label_0024: {
            try {
                if (childByType == null) {
                    final PsiElement headerToken;
                    psiElement = (headerToken = super.getHeaderToken());
                    break Label_0024;
                }
            }
            catch (IllegalArgumentException ex) {
                throw d(ex);
            }
            PsiElement headerToken;
            psiElement = (headerToken = childByType);
            try {
                if (headerToken == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCImportModuleStatementImpl", "getHeaderToken"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw d(ex2);
            }
        }
        return psiElement;
    }
    
    @NotNull
    private String a() {
        ASTNode astNode = this.getNode().getFirstChildNode();
        final ArrayList arrayList = ContainerUtil.newArrayList();
        while (astNode != null) {
            final IElementType elementType = astNode.getElementType();
            try {
                if (elementType == OCTokenTypes.IDENTIFIER) {
                    arrayList.add(astNode.getText());
                }
            }
            catch (IllegalArgumentException ex) {
                throw d(ex);
            }
            astNode = astNode.getTreeNext();
        }
        String join;
        try {
            join = StringUtil.join((Collection)arrayList, ".");
            if (join == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCImportModuleStatementImpl", "getModuleName"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw d(ex2);
        }
        return join;
    }
    
    @NotNull
    @Override
    public OCIncludeDirective.Delimiters getDelimiters() {
        OCIncludeDirective.Delimiters none;
        try {
            none = OCIncludeDirective.Delimiters.NONE;
            if (none == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCImportModuleStatementImpl", "getDelimiters"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return none;
    }
    
    @Override
    public boolean isAngleBrackets() {
        return true;
    }
    
    @NotNull
    @Override
    public PsiReference[] getReferences() {
        PsiReference[] array;
        try {
            array = new PsiReference[] { new OCImportModuleReference(this) };
            if (array == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCImportModuleStatementImpl", "getReferences"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return array;
    }
    
    @NotNull
    @Override
    public String getReferenceText() {
        String a;
        try {
            a = this.a();
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCImportModuleStatementImpl", "getReferenceText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return a;
    }
    
    @Override
    public boolean isValidDirective() {
        final PsiReference[] references;
        final PsiReference[] array = references = this.getReferences();
        for (final PsiReference psiReference : references) {
            Label_0057: {
                try {
                    if (!(psiReference instanceof OCImportModuleReference)) {
                        break Label_0057;
                    }
                    final OCImportModuleReference ocImportModuleReference = (OCImportModuleReference)psiReference;
                    final OCImportModuleReference ocImportModuleReference2 = ocImportModuleReference;
                    final boolean b = ocImportModuleReference2.isValid();
                    if (!b) {
                        return false;
                    }
                    break Label_0057;
                }
                catch (IllegalArgumentException ex) {
                    throw d(ex);
                }
                try {
                    final OCImportModuleReference ocImportModuleReference = (OCImportModuleReference)psiReference;
                    final OCImportModuleReference ocImportModuleReference2 = ocImportModuleReference;
                    final boolean b = ocImportModuleReference2.isValid();
                    if (!b) {
                        return false;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw d(ex2);
                }
            }
        }
        try {
            if (array.length > 0) {
                return true;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw d(ex3);
        }
        return false;
    }
    
    private static IllegalArgumentException d(final IllegalArgumentException ex) {
        return ex;
    }
}
