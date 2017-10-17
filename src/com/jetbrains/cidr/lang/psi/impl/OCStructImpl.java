// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.intellij.openapi.util.Comparing;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCStruct;

public class OCStructImpl extends OCStructLikeImpl implements OCStruct
{
    public OCStructImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCStructImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCStructImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ocVisitor.visitStruct(this);
    }
    
    @Override
    public OCSymbolKind getKind() {
        return OCSymbolKind.STRUCT;
    }
    
    @NotNull
    @Override
    public List<OCDeclaration> getMembers() {
        List childrenOfTypeAsList;
        try {
            childrenOfTypeAsList = PsiTreeUtil.getChildrenOfTypeAsList((PsiElement)this, (Class)OCDeclaration.class);
            if (childrenOfTypeAsList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCStructImpl", "getMembers"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (List<OCDeclaration>)childrenOfTypeAsList;
    }
    
    @NotNull
    @Override
    public List<OCFunctionDeclaration> getConstructors() {
        final String name = this.getName();
        final ArrayList<OCFunctionDeclaration> list = new ArrayList<OCFunctionDeclaration>();
        final OCFunctionDeclaration[] array = (OCFunctionDeclaration[])PsiTreeUtil.getChildrenOfType((PsiElement)this, (Class)OCFunctionDeclaration.class);
        if (array != null) {
            for (final OCFunctionDeclaration ocFunctionDeclaration : array) {
                Label_0084: {
                    try {
                        if (!(ocFunctionDeclaration instanceof OCFunctionDeclaration)) {
                            break Label_0084;
                        }
                        final String s = name;
                        final OCFunctionDeclaration ocFunctionDeclaration2 = ocFunctionDeclaration;
                        final OCFunctionDeclaration ocFunctionDeclaration3 = ocFunctionDeclaration2;
                        final String s2 = ocFunctionDeclaration3.getName();
                        final boolean b = Comparing.equal(s, s2);
                        if (b) {
                            break Label_0084;
                        }
                        break Label_0084;
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        final String s = name;
                        final OCFunctionDeclaration ocFunctionDeclaration2 = ocFunctionDeclaration;
                        final OCFunctionDeclaration ocFunctionDeclaration3 = ocFunctionDeclaration2;
                        final String s2 = ocFunctionDeclaration3.getName();
                        final boolean b = Comparing.equal(s, s2);
                        if (b) {
                            list.add(ocFunctionDeclaration);
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
            }
        }
        ArrayList<OCFunctionDeclaration> list2;
        try {
            list2 = list;
            if (list2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCStructImpl", "getConstructors"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return list2;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
