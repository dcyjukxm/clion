// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.openapi.util.TextRange;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import javax.swing.Icon;
import com.intellij.navigation.ItemPresentation;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import com.jetbrains.cidr.lang.psi.OCNoexceptSpecifier;
import com.jetbrains.cidr.lang.psi.OCParameterList;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCTypeElement;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.jetbrains.cidr.lang.types.OCIdType;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import org.jetbrains.annotations.NonNls;
import com.jetbrains.cidr.lang.symbols.OCSymbolOffsetUtil;
import java.util.List;
import java.util.Iterator;
import com.jetbrains.cidr.lang.psi.OCMethodSelectorPart;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.util.IncorrectOperationException;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCCallableKind;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCMethod;

public class OCMethodImpl extends OCElementBase implements OCMethod
{
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public OCMethodImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCMethodImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Override
    public OCCallableKind getKind() {
        return OCCallableKind.METHOD;
    }
    
    @NotNull
    @Override
    public OCClassDeclaration getContainingClass() {
        final OCClassDeclaration ocClassDeclaration = (OCClassDeclaration)PsiTreeUtil.getContextOfType((PsiElement)this, new Class[] { OCClassDeclaration.class });
        OCClassDeclaration ocClassDeclaration3 = null;
        Label_0046: {
            Label_0034: {
                try {
                    if (OCMethodImpl.$assertionsDisabled) {
                        break Label_0046;
                    }
                    final OCClassDeclaration ocClassDeclaration2 = ocClassDeclaration;
                    if (ocClassDeclaration2 == null) {
                        break Label_0034;
                    }
                    break Label_0046;
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                try {
                    final OCClassDeclaration ocClassDeclaration2 = ocClassDeclaration;
                    if (ocClassDeclaration2 == null) {
                        throw new AssertionError();
                    }
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
            }
            try {
                ocClassDeclaration3 = ocClassDeclaration;
                if (ocClassDeclaration3 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCMethodImpl", "getContainingClass"));
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
        }
        return ocClassDeclaration3;
    }
    
    @Override
    public boolean isInstanceMethod() {
        for (ASTNode astNode = this.getNode().getFirstChildNode(); astNode != null; astNode = astNode.getTreeNext()) {
            final IElementType elementType = astNode.getElementType();
            try {
                if (elementType == OCTokenTypes.MINUS) {
                    return true;
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            try {
                if (elementType == OCTokenTypes.PLUS) {
                    return false;
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    @Override
    public void setStatic(final boolean p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_2       
        //     2: aload_0        
        //     3: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCMethodImpl.getNode:()Lcom/intellij/lang/ASTNode;
        //     6: invokeinterface com/intellij/lang/ASTNode.getFirstChildNode:()Lcom/intellij/lang/ASTNode;
        //    11: astore_3       
        //    12: aload_3        
        //    13: ifnull          70
        //    16: aload_3        
        //    17: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //    22: astore          4
        //    24: aload           4
        //    26: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.MINUS:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    29: if_acmpne       42
        //    32: aload_3        
        //    33: invokeinterface com/intellij/lang/ASTNode.getPsi:()Lcom/intellij/psi/PsiElement;
        //    38: astore_2       
        //    39: goto            70
        //    42: aload           4
        //    44: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.PLUS:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    47: if_acmpne       60
        //    50: aload_3        
        //    51: invokeinterface com/intellij/lang/ASTNode.getPsi:()Lcom/intellij/psi/PsiElement;
        //    56: astore_2       
        //    57: goto            70
        //    60: aload_3        
        //    61: invokeinterface com/intellij/lang/ASTNode.getTreeNext:()Lcom/intellij/lang/ASTNode;
        //    66: astore_3       
        //    67: goto            12
        //    70: aload_2        
        //    71: ifnull          123
        //    74: aload_2        
        //    75: invokeinterface com/intellij/psi/PsiElement.isWritable:()Z
        //    80: ifeq            123
        //    83: goto            90
        //    86: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCMethodImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    89: athrow         
        //    90: aload_2        
        //    91: iload_1        
        //    92: ifeq            111
        //    95: goto            102
        //    98: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCMethodImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   101: athrow         
        //   102: ldc             "+"
        //   104: goto            113
        //   107: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCMethodImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   110: athrow         
        //   111: ldc             "-"
        //   113: aload_2        
        //   114: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.binaryOperatorFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   117: invokeinterface com/intellij/psi/PsiElement.replace:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   122: pop            
        //   123: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  70     83     86     90     Lcom/intellij/util/IncorrectOperationException;
        //  74     95     98     102    Lcom/intellij/util/IncorrectOperationException;
        //  90     107    107    111    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0090:
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
    public String getSelector() {
        final StringBuilder sb = new StringBuilder();
        final Iterator<OCMethodSelectorPart> iterator = this.findChildrenByType(OCElementTypes.METHOD_SELECTOR_PART).iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next().getSelectorPart());
        }
        String string;
        try {
            string = sb.toString();
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCMethodImpl", "getSelector"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return string;
    }
    
    @NotNull
    public String getName() {
        String selector;
        try {
            selector = this.getSelector();
            if (selector == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCMethodImpl", "getName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return selector;
    }
    
    public PsiElement getNameIdentifier() {
        final OCMethodSelectorPart ocMethodSelectorPart = this.findChildByType(OCElementTypes.METHOD_SELECTOR_PART);
        try {
            if (ocMethodSelectorPart != null) {
                return ocMethodSelectorPart.getSelectorIdentifier();
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return null;
    }
    
    @Override
    public int getTextOffset() {
        final List<OCMethodSelectorPart> childrenByType = this.findChildrenByType(OCElementTypes.METHOD_SELECTOR_PART);
        try {
            if (childrenByType.isEmpty()) {
                return super.getTextOffset();
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return childrenByType.get(0).getTextRange().getStartOffset();
    }
    
    @Override
    public long getComplexOffset() {
        final List<PsiElement> childrenByType = this.findChildrenByType(OCElementTypes.METHOD_SELECTOR_PART);
        try {
            if (childrenByType.isEmpty()) {
                return super.getComplexOffset();
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return OCSymbolOffsetUtil.getComplexOffset(childrenByType.get(0));
    }
    
    public PsiElement setName(@NonNls @NotNull final String s) throws IncorrectOperationException {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "newName", "com/jetbrains/cidr/lang/psi/impl/OCMethodImpl", "setName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final PsiElement selectorIdentifier = this.getParameters().get(0).getSelectorIdentifier();
        try {
            if (selectorIdentifier != null) {
                OCElementUtil.replaceWithIdentifier(selectorIdentifier, s, (PsiElement)this);
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        return (PsiElement)this;
    }
    
    @NotNull
    @Override
    public OCType getReturnType() {
        final OCTypeElement returnTypeElement = this.getReturnTypeElement();
        OCType ocType = null;
        Label_0029: {
            try {
                if (returnTypeElement != null) {
                    ocType = returnTypeElement.getType();
                    break Label_0029;
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            ocType = OCIdType.pointerToID(this.getProject());
        }
        final OCType ocType2 = ocType;
        OCType ocType3 = null;
        Label_0136: {
            try {
                if (!(ocType2 instanceof OCReferenceType) || !ocType2.getName().equals("instancetype")) {
                    break Label_0136;
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
            final OCClassDeclaration ocClassDeclaration = (OCClassDeclaration)PsiTreeUtil.getContextOfType((PsiElement)this, false, new Class[] { OCClassDeclaration.class });
            OCPointerType ocPointerType = null;
            Label_0101: {
                try {
                    if (ocClassDeclaration == null) {
                        break Label_0136;
                    }
                    final OCClassDeclaration ocClassDeclaration2 = ocClassDeclaration;
                    final String s = ocClassDeclaration2.getName();
                    final OCReferenceType ocReferenceType = OCReferenceType.fromText(s);
                    ocPointerType = OCPointerType.to(ocReferenceType);
                    if (ocPointerType == null) {
                        break Label_0101;
                    }
                    return ocPointerType;
                }
                catch (IncorrectOperationException ex3) {
                    throw a(ex3);
                }
                try {
                    final OCClassDeclaration ocClassDeclaration2 = ocClassDeclaration;
                    final String s = ocClassDeclaration2.getName();
                    final OCReferenceType ocReferenceType = OCReferenceType.fromText(s);
                    ocPointerType = OCPointerType.to(ocReferenceType);
                    if (ocPointerType == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCMethodImpl", "getReturnType"));
                    }
                }
                catch (IncorrectOperationException ex4) {
                    throw a(ex4);
                }
            }
            return ocPointerType;
            try {
                ocType3 = ocType2;
                if (ocType3 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCMethodImpl", "getReturnType"));
                }
            }
            catch (IncorrectOperationException ex5) {
                throw a(ex5);
            }
        }
        return ocType3;
    }
    
    @NotNull
    @Override
    public OCType getRawReturnType() {
        final OCTypeElement returnTypeElement = this.getReturnTypeElement();
        OCType ocType = null;
        Label_0029: {
            try {
                if (returnTypeElement != null) {
                    final OCType ocType2;
                    ocType = (ocType2 = returnTypeElement.getRawType());
                    break Label_0029;
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            OCType ocType2;
            ocType = (ocType2 = OCIdType.pointerToID(this.getProject()));
            try {
                if (ocType2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCMethodImpl", "getRawReturnType"));
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
        }
        return ocType;
    }
    
    @Nullable
    @Override
    public OCTypeElement getReturnTypeElement() {
        return this.findChildByType(OCElementTypes.TYPE_ELEMENT);
    }
    
    @Nullable
    @Override
    public OCParameterList getParameterList() {
        return null;
    }
    
    @Nullable
    @Override
    public OCNoexceptSpecifier getNoexceptSpecifier() {
        return null;
    }
    
    @NotNull
    @Override
    public List<OCMethodSelectorPart> getParameters() {
        List<PsiElement> childrenByType;
        try {
            childrenByType = (List<PsiElement>)this.findChildrenByType(OCElementTypes.METHOD_SELECTOR_PART);
            if (childrenByType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCMethodImpl", "getParameters"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return (List<OCMethodSelectorPart>)childrenByType;
    }
    
    @Override
    public OCBlockStatement getBody() {
        return this.findChildByType(OCElementTypes.BLOCK_STATEMENTS);
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCMethodImpl", "accept"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        ocVisitor.visitMethod(this);
    }
    
    @Override
    public ItemPresentation getPresentation() {
        return (ItemPresentation)new ItemPresentation() {
            public String getPresentableText() {
                return OCMethodImpl.this.getSelector();
            }
            
            public String getLocationString() {
                final ItemPresentation presentation = OCMethodImpl.this.getContainingClass().getPresentation();
                return (presentation != null) ? presentation.getPresentableText() : "";
            }
            
            public Icon getIcon(final boolean b) {
                return OCMethodImpl.this.getIcon(0);
            }
        };
    }
    
    public OCMethodSymbol getSymbol() {
        final OCClassSymbol symbol = this.getContainingClass().getSymbol();
        boolean b2 = false;
        Label_0037: {
            Label_0028: {
                try {
                    if (symbol == null) {
                        return null;
                    }
                    final OCMethodImpl ocMethodImpl = this;
                    final boolean b = ocMethodImpl.isInstanceMethod();
                    if (!b) {
                        break Label_0028;
                    }
                    break Label_0028;
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                try {
                    final OCMethodImpl ocMethodImpl = this;
                    final boolean b = ocMethodImpl.isInstanceMethod();
                    if (!b) {
                        b2 = true;
                        break Label_0037;
                    }
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
            }
            b2 = false;
        }
        final CommonProcessors.FindFirstProcessor<OCMethodSymbol> findFirstProcessor = new CommonProcessors.FindFirstProcessor<OCMethodSymbol>() {
            protected boolean accept(final OCMethodSymbol ocMethodSymbol) {
                return ocMethodSymbol.isStatic() == b2;
            }
        };
        symbol.processMembers(this.getSelector(), (Class<? extends OCMemberSymbol>)OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMemberSymbol>)findFirstProcessor);
        return (OCMethodSymbol)findFirstProcessor.getFoundValue();
    }
    
    @NotNull
    @Override
    public List<PsiElement> getSelectors() {
        final ArrayList<PsiElement> list = new ArrayList<PsiElement>();
        for (final OCMethodSelectorPart ocMethodSelectorPart : this.getParameters()) {
            try {
                if (ocMethodSelectorPart.getSelectorIdentifier() == null) {
                    continue;
                }
                list.add(ocMethodSelectorPart.getSelectorIdentifier());
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
        }
        ArrayList<PsiElement> list2;
        try {
            list2 = list;
            if (list2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCMethodImpl", "getSelectors"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        return list2;
    }
    
    @Override
    public TextRange getHeaderRange() {
        final List<PsiElement> selectors = this.getSelectors();
        try {
            if (selectors.isEmpty()) {
                return new TextRange(this.getTextRange().getStartOffset(), this.getTextRange().getStartOffset());
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return new TextRange(selectors.get(0).getTextRange().getStartOffset(), selectors.get(selectors.size() - 1).getTextRange().getEndOffset());
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCMethodImpl.class.desiredAssertionStatus()) {
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
