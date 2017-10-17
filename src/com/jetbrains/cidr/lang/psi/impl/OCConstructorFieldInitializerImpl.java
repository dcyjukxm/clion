// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.jetbrains.cidr.lang.psi.OCCompoundInitializer;
import java.util.Collections;
import com.jetbrains.cidr.lang.psi.OCExpression;
import java.util.List;
import com.jetbrains.cidr.lang.psi.OCArgumentList;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCConstructorFieldInitializer;

public class OCConstructorFieldInitializerImpl extends OCElementBase implements OCConstructorFieldInitializer
{
    public OCConstructorFieldInitializerImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCConstructorFieldInitializerImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Nullable
    @Override
    public OCReferenceElement getReferenceElement() {
        return this.findChildByType(OCElementTypes.REFERENCE_ELEMENT);
    }
    
    @Nullable
    @Override
    public OCArgumentList getArgumentList() {
        return this.findChildByType(OCElementTypes.ARGUMENT_LIST);
    }
    
    @NotNull
    @Override
    public List<OCExpression> getArguments() {
        final OCArgumentList argumentList = this.getArgumentList();
        List<OCExpression> list = null;
        Label_0025: {
            try {
                if (argumentList != null) {
                    final List<OCExpression> list2;
                    list = (list2 = argumentList.getArguments());
                    break Label_0025;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            List<OCExpression> list2;
            list = (list2 = Collections.emptyList());
            try {
                if (list2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCConstructorFieldInitializerImpl", "getArguments"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return list;
    }
    
    public OCCompoundInitializer getInitializerList() {
        return this.findChildByType(OCElementTypes.COMPOUND_INITIALIZER);
    }
    
    @NotNull
    @Override
    public List<OCExpression> getInitializers() {
        final OCArgumentList argumentList = this.getArgumentList();
        Label_0061: {
            List<OCExpression> list2 = null;
            Label_0026: {
                try {
                    if (argumentList == null) {
                        break Label_0061;
                    }
                    final OCArgumentList list = argumentList;
                    list2 = list.getArguments();
                    if (list2 == null) {
                        break Label_0026;
                    }
                    return list2;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final OCArgumentList list = argumentList;
                    list2 = list.getArguments();
                    if (list2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCConstructorFieldInitializerImpl", "getInitializers"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return list2;
        }
        final OCCompoundInitializer initializerList = this.getInitializerList();
        List<OCCompoundInitializer> list3 = null;
        Label_0084: {
            try {
                if (initializerList != null) {
                    final List<OCCompoundInitializer> list4;
                    list3 = (list4 = Collections.singletonList(initializerList));
                    break Label_0084;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            List<OCCompoundInitializer> list4;
            list3 = (list4 = Collections.emptyList());
            try {
                if (list4 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCConstructorFieldInitializerImpl", "getInitializers"));
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return (List<OCExpression>)list3;
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCConstructorFieldInitializerImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ocVisitor.visitConstructorFieldInitializer(this);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
