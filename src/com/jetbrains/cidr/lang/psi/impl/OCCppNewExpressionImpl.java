// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import java.util.Iterator;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCCompoundInitializer;
import java.util.Collections;
import com.jetbrains.cidr.lang.psi.OCExpression;
import java.util.List;
import com.jetbrains.cidr.lang.psi.OCArgumentList;
import com.jetbrains.cidr.lang.types.OCUnknownType;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.psi.OCTypeElement;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCCppNewExpression;

public class OCCppNewExpressionImpl extends OCExpressionBase implements OCCppNewExpression
{
    public OCCppNewExpressionImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCCppNewExpressionImpl", "<init>"));
        }
        super(astNode);
    }
    
    @NotNull
    @Override
    public OCType getType(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCCppNewExpressionImpl", "getType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCTypeElement ocTypeElement = this.findChildByClass(OCTypeElement.class);
        OCType ocType = null;
        Label_0085: {
            try {
                if (ocTypeElement != null) {
                    final OCType ocType2;
                    ocType = (ocType2 = getNewExprType(ocTypeElement.getType(), ocTypeElement.getArrayLengths()));
                    break Label_0085;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            OCType ocType2;
            ocType = (ocType2 = super.getType(ocResolveContext));
            try {
                if (ocType2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCCppNewExpressionImpl", "getType"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return ocType;
    }
    
    @NotNull
    public static OCType getNewExprType(@NotNull OCType refType, final int n) {
        try {
            if (refType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/psi/impl/OCCppNewExpressionImpl", "getNewExprType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        int n2 = 0;
        while (true) {
            try {
                if (n2 >= n || !(refType instanceof OCPointerType)) {
                    break;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            refType = ((OCPointerType)refType).getRefType();
            ++n2;
        }
        OCPointerType to;
        try {
            to = OCPointerType.to(refType);
            if (to == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCCppNewExpressionImpl", "getNewExprType"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return to;
    }
    
    @Nullable
    @Override
    public OCTypeElement getConstructingTypeElement() {
        return this.findChildByType(OCElementTypes.TYPE_ELEMENT);
    }
    
    @Nullable
    @Override
    public OCReferenceElement getReferenceElement() {
        final OCTypeElement constructingTypeElement = this.getConstructingTypeElement();
        ASTNode childByType = null;
        Label_0031: {
            try {
                if (constructingTypeElement != null) {
                    childByType = constructingTypeElement.getNode().findChildByType((IElementType)OCElementTypes.REFERENCE_ELEMENT);
                    break Label_0031;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            childByType = null;
        }
        final ASTNode astNode = childByType;
        try {
            if (astNode != null) {
                return (OCReferenceElement)astNode.getPsi();
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return null;
    }
    
    @NotNull
    @Override
    public OCType getConstructingType() {
        final OCTypeElement constructingTypeElement = this.getConstructingTypeElement();
        OCType ocType = null;
        Label_0025: {
            try {
                if (constructingTypeElement != null) {
                    final OCType ocType2;
                    ocType = (ocType2 = constructingTypeElement.getType());
                    break Label_0025;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            OCType ocType2;
            ocType = (ocType2 = OCUnknownType.INSTANCE);
            try {
                if (ocType2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCCppNewExpressionImpl", "getConstructingType"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return ocType;
    }
    
    @Nullable
    @Override
    public OCArgumentList getArgumentList() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCCppNewExpressionImpl.getConstructingTypeElement:()Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //     4: astore_1       
        //     5: aload_0        
        //     6: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCCppNewExpressionImpl.getLastChild:()Lcom/intellij/psi/PsiElement;
        //     9: astore_2       
        //    10: aload_2        
        //    11: ifnull          59
        //    14: aload_2        
        //    15: aload_1        
        //    16: if_acmpeq       59
        //    19: goto            26
        //    22: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCCppNewExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    25: athrow         
        //    26: aload_2        
        //    27: instanceof      Lcom/jetbrains/cidr/lang/psi/OCArgumentList;
        //    30: ifeq            49
        //    33: goto            40
        //    36: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCCppNewExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    39: athrow         
        //    40: aload_2        
        //    41: checkcast       Lcom/jetbrains/cidr/lang/psi/OCArgumentList;
        //    44: areturn        
        //    45: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCCppNewExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    48: athrow         
        //    49: aload_2        
        //    50: invokeinterface com/intellij/psi/PsiElement.getPrevSibling:()Lcom/intellij/psi/PsiElement;
        //    55: astore_2       
        //    56: goto            10
        //    59: aconst_null    
        //    60: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  10     19     22     26     Ljava/lang/IllegalArgumentException;
        //  14     33     36     40     Ljava/lang/IllegalArgumentException;
        //  26     45     45     49     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0026:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
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
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCCppNewExpressionImpl", "getArguments"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return list;
    }
    
    public OCCompoundInitializer getInitializerList() {
        final OCCompoundInitializer ocCompoundInitializer = this.findChildByType(OCElementTypes.COMPOUND_INITIALIZER);
        try {
            if (ocCompoundInitializer != null) {
                return ocCompoundInitializer;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCTypeElement constructingTypeElement = this.getConstructingTypeElement();
        if (constructingTypeElement != null) {
            PsiElement psiElement = constructingTypeElement.getLastChild();
            while (true) {
                Label_0055: {
                    try {
                        if (psiElement == null) {
                            break;
                        }
                        final PsiElement psiElement2 = psiElement;
                        final boolean b = psiElement2 instanceof OCCompoundInitializer;
                        if (b) {
                            break Label_0055;
                        }
                        break Label_0055;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    try {
                        final PsiElement psiElement2 = psiElement;
                        final boolean b = psiElement2 instanceof OCCompoundInitializer;
                        if (b) {
                            return (OCCompoundInitializer)psiElement;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                }
                psiElement = psiElement.getNextSibling();
            }
        }
        return null;
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
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCCppNewExpressionImpl", "getInitializers"));
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
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCCppNewExpressionImpl", "getInitializers"));
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return (List<OCExpression>)list3;
    }
    
    @Override
    public boolean isNoThrow() {
        final OCTypeElement constructingTypeElement = this.getConstructingTypeElement();
        try {
            if (constructingTypeElement == null) {
                return false;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final PsiElement firstChild = this.getFirstChild();
        PsiElement psiElement = constructingTypeElement.getPrevSibling();
        while (true) {
            Label_0155: {
                Label_0043: {
                    try {
                        if (psiElement == null) {
                            break;
                        }
                        final PsiElement psiElement2 = psiElement;
                        final PsiElement psiElement3 = firstChild;
                        if (psiElement2 != psiElement3) {
                            break Label_0043;
                        }
                        break;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    try {
                        final PsiElement psiElement2 = psiElement;
                        final PsiElement psiElement3 = firstChild;
                        if (psiElement2 == psiElement3) {
                            break;
                        }
                        if (!(psiElement instanceof OCArgumentList)) {
                            break Label_0155;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                }
                final Iterator<OCExpression> iterator = ((OCArgumentList)psiElement).getArguments().iterator();
                while (iterator.hasNext()) {
                    final OCType resolvedType = iterator.next().getResolvedType();
                    try {
                        if (!(resolvedType instanceof OCStructType)) {
                            continue;
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                    final String name = ((OCStructType)resolvedType).getSymbol().getName();
                    try {
                        if (name.equals("nothrow_t")) {
                            return true;
                        }
                        continue;
                    }
                    catch (IllegalArgumentException ex5) {
                        throw a(ex5);
                    }
                }
            }
            psiElement = psiElement.getPrevSibling();
        }
        return false;
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCCppNewExpressionImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ocVisitor.visitCppNewExpression(this);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
