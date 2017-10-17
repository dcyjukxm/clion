// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import java.util.Map;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.resolve.OCResolveUtil;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.psi.OCClassDeclarationBase;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.types.OCObjectTypeContext;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.symtable.OCGlobalProjectSymbolsCache;
import java.util.HashMap;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.resolve.OCSelectorAdHocResolver;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.intellij.util.IncorrectOperationException;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.resolve.references.OCPolyVariantReferenceImpl;
import com.intellij.psi.PsiReference;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import java.util.List;
import com.jetbrains.cidr.lang.parser.OCPunctuatorElementType;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.tree.IElementType;
import java.util.Collection;
import com.jetbrains.cidr.lang.refactoring.OCNameSuggester;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import java.util.HashSet;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.types.OCType;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCSelectorExpression;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.psi.OCPolyVariantReference;

public class OCSelectorExpressionImpl extends OCExpressionWithReferenceBase<OCPolyVariantReference<OCMethodSymbol>> implements OCSelectorExpression
{
    public OCSelectorExpressionImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCSelectorExpressionImpl", "<init>"));
        }
        super(astNode);
    }
    
    @NotNull
    @Override
    public String getSelector() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Ljava/lang/StringBuilder;
        //     3: dup            
        //     4: invokespecial   java/lang/StringBuilder.<init>:()V
        //     7: astore_1       
        //     8: aload_0        
        //     9: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCSelectorExpressionImpl.getNode:()Lcom/intellij/lang/ASTNode;
        //    12: invokeinterface com/intellij/lang/ASTNode.getFirstChildNode:()Lcom/intellij/lang/ASTNode;
        //    17: astore_2       
        //    18: aload_2        
        //    19: ifnull          109
        //    22: aload_2        
        //    23: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //    28: astore_3       
        //    29: aload_3        
        //    30: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.OBJC_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    33: if_acmpeq       99
        //    36: aload_3        
        //    37: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    40: if_acmpeq       99
        //    43: goto            50
        //    46: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCSelectorExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    49: athrow         
        //    50: aload_3        
        //    51: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RPAR:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    54: if_acmpeq       99
        //    57: goto            64
        //    60: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCSelectorExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    63: athrow         
        //    64: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET:Lcom/intellij/psi/tree/TokenSet;
        //    67: aload_3        
        //    68: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //    71: ifne            99
        //    74: goto            81
        //    77: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCSelectorExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    80: athrow         
        //    81: aload_1        
        //    82: aload_2        
        //    83: invokeinterface com/intellij/lang/ASTNode.getText:()Ljava/lang/String;
        //    88: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    91: pop            
        //    92: goto            99
        //    95: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCSelectorExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    98: athrow         
        //    99: aload_2        
        //   100: invokeinterface com/intellij/lang/ASTNode.getTreeNext:()Lcom/intellij/lang/ASTNode;
        //   105: astore_2       
        //   106: goto            18
        //   109: aload_1        
        //   110: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   113: invokevirtual   java/lang/String.trim:()Ljava/lang/String;
        //   116: dup            
        //   117: ifnonnull       154
        //   120: new             Ljava/lang/IllegalStateException;
        //   123: dup            
        //   124: ldc             "@NotNull method %s.%s must not return null"
        //   126: ldc             2
        //   128: anewarray       Ljava/lang/Object;
        //   131: dup            
        //   132: ldc             0
        //   134: ldc             "com/jetbrains/cidr/lang/psi/impl/OCSelectorExpressionImpl"
        //   136: aastore        
        //   137: dup            
        //   138: ldc             1
        //   140: ldc             "getSelector"
        //   142: aastore        
        //   143: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   146: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   149: athrow         
        //   150: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCSelectorExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   153: athrow         
        //   154: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  29     43     46     50     Ljava/lang/IllegalArgumentException;
        //  36     57     60     64     Ljava/lang/IllegalArgumentException;
        //  50     74     77     81     Ljava/lang/IllegalArgumentException;
        //  64     92     95     99     Ljava/lang/IllegalArgumentException;
        //  109    150    150    154    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0050:
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
    public OCType getExpectedReturnType() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokestatic    com/jetbrains/cidr/lang/resolve/OCSelectorAdHocResolver.getActionTargetContext:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/types/OCObjectTypeContext;
        //     4: ifnull          90
        //     7: aload_0        
        //     8: ldc             Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;.class
        //    10: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //    13: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //    16: astore_1       
        //    17: aload_1        
        //    18: ifnull          90
        //    21: aload_1        
        //    22: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getMessageSelector:()Ljava/lang/String;
        //    27: invokestatic    com/jetbrains/cidr/lang/resolve/OCSelectorAdHocResolver.isPerformSelectorMethod:(Ljava/lang/String;)Z
        //    30: ifeq            90
        //    33: goto            40
        //    36: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCSelectorExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    39: athrow         
        //    40: aload_1        
        //    41: invokestatic    com/jetbrains/cidr/lang/util/OCExpectedTypeUtil.getExpectedType:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/types/OCType;
        //    44: dup            
        //    45: ifnonnull       89
        //    48: goto            55
        //    51: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCSelectorExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    54: athrow         
        //    55: new             Ljava/lang/IllegalStateException;
        //    58: dup            
        //    59: ldc             "@NotNull method %s.%s must not return null"
        //    61: ldc             2
        //    63: anewarray       Ljava/lang/Object;
        //    66: dup            
        //    67: ldc             0
        //    69: ldc             "com/jetbrains/cidr/lang/psi/impl/OCSelectorExpressionImpl"
        //    71: aastore        
        //    72: dup            
        //    73: ldc             1
        //    75: ldc             "getExpectedReturnType"
        //    77: aastore        
        //    78: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    81: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    84: athrow         
        //    85: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCSelectorExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    88: athrow         
        //    89: areturn        
        //    90: invokestatic    com/jetbrains/cidr/lang/types/OCVoidType.instance:()Lcom/jetbrains/cidr/lang/types/OCVoidType;
        //    93: dup            
        //    94: ifnonnull       131
        //    97: new             Ljava/lang/IllegalStateException;
        //   100: dup            
        //   101: ldc             "@NotNull method %s.%s must not return null"
        //   103: ldc             2
        //   105: anewarray       Ljava/lang/Object;
        //   108: dup            
        //   109: ldc             0
        //   111: ldc             "com/jetbrains/cidr/lang/psi/impl/OCSelectorExpressionImpl"
        //   113: aastore        
        //   114: dup            
        //   115: ldc             1
        //   117: ldc             "getExpectedReturnType"
        //   119: aastore        
        //   120: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   123: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   126: athrow         
        //   127: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCSelectorExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   130: athrow         
        //   131: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  17     33     36     40     Ljava/lang/IllegalArgumentException;
        //  21     48     51     55     Ljava/lang/IllegalArgumentException;
        //  40     85     85     89     Ljava/lang/IllegalArgumentException;
        //  90     127    127    131    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0040:
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
    
    @Override
    public String getExpectedMethodSignature() {
        final StringBuilder sb = new StringBuilder();
        sb.append("-(");
        sb.append(this.getExpectedReturnType().getBestNameInContext((PsiElement)this));
        sb.append(")");
        ASTNode astNode = this.getNode().getFirstChildNode();
        String text = null;
        final HashSet<String> set = new HashSet<String>();
        while (astNode != null) {
            final IElementType elementType = astNode.getElementType();
            Label_0185: {
                if (elementType == OCTokenTypes.IDENTIFIER) {
                    text = astNode.getText();
                    sb.append(text);
                }
                else {
                    try {
                        if (elementType != OCTokenTypes.COLON) {
                            if (elementType != OCTokenTypes.COLON2X) {
                                break Label_0185;
                            }
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    int n = 0;
                    while (true) {
                        int n2 = 0;
                        int n3 = 0;
                        Label_0137: {
                            try {
                                n2 = n;
                                if (elementType == OCTokenTypes.COLON2X) {
                                    n3 = 2;
                                    break Label_0137;
                                }
                            }
                            catch (IllegalArgumentException ex2) {
                                throw a(ex2);
                            }
                            n3 = 1;
                        }
                        if (n2 >= n3) {
                            break;
                        }
                        final String suggestUniqueName = OCNameSuggester.suggestUniqueName(OCSymbolKind.PARAMETER, text, null, set);
                        set.add(suggestUniqueName);
                        sb.append(":(id)").append(suggestUniqueName).append(' ');
                        ++n;
                    }
                }
            }
            astNode = astNode.getTreeNext();
        }
        return sb.toString();
    }
    
    @NotNull
    @Override
    public TextRange getSelectorRange() {
        int startOffset = 0;
        int endOffset = 0;
        ASTNode astNode = this.getNode().getFirstChildNode();
        ASTNode astNode2 = null;
        OCPunctuatorElementType ocPunctuatorElementType = null;
        final int startOffset2 = this.getNode().getStartOffset();
        while (astNode != null) {
            final IElementType elementType = astNode.getElementType();
            if (OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET.contains(elementType)) {
                astNode = astNode.getTreeNext();
            }
            else {
                if (ocPunctuatorElementType == OCTokenTypes.LPAR) {
                    startOffset = astNode.getStartOffset();
                }
                if (elementType == OCTokenTypes.RPAR) {
                    endOffset = astNode2.getTextRange().getEndOffset();
                }
                astNode2 = astNode;
                ocPunctuatorElementType = (OCPunctuatorElementType)elementType;
                astNode = astNode.getTreeNext();
            }
        }
        if (endOffset < startOffset) {
            endOffset = startOffset;
        }
        final TextRange textRange = new TextRange(startOffset, endOffset);
        TextRange textRange2 = null;
        Label_0158: {
            try {
                if (textRange.isEmpty()) {
                    final TextRange shiftRight;
                    textRange2 = (shiftRight = textRange);
                    break Label_0158;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            TextRange shiftRight;
            textRange2 = (shiftRight = textRange.shiftRight(-startOffset2));
            try {
                if (shiftRight == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCSelectorExpressionImpl", "getSelectorRange"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return textRange2;
    }
    
    @Override
    public List<PsiElement> getSelectorParts() {
        return this.findChildrenByType(OCTokenTypes.IDENTIFIER);
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCSelectorExpressionImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ocVisitor.visitSelectorExpression(this);
    }
    
    @NotNull
    @Override
    public OCType getType(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCSelectorExpressionImpl", "getType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCReferenceType fromText;
        try {
            fromText = OCReferenceType.fromText("SEL");
            if (fromText == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCSelectorExpressionImpl", "getType"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return fromText;
    }
    
    @NotNull
    @Override
    protected OCPolyVariantReference<OCMethodSymbol> createReference() {
        SelectorReference selectorReference;
        try {
            selectorReference = new SelectorReference();
            if (selectorReference == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCSelectorExpressionImpl", "createReference"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return selectorReference;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    private class SelectorReference extends OCPolyVariantReferenceImpl<OCMethodSymbol>
    {
        final /* synthetic */ OCSelectorExpressionImpl this$0;
        
        public PsiElement getElement() {
            return (PsiElement)OCSelectorExpressionImpl.this;
        }
        
        public TextRange getRangeInElement() {
            return OCSelectorExpressionImpl.this.getSelectorRange();
        }
        
        @Override
        public boolean isReferenceTo(final PsiElement psiElement) {
            try {
                if (!(psiElement instanceof OCSymbolDeclarator)) {
                    return false;
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            final OCMethodSymbol symbol = ((OCSymbolDeclarator)psiElement).getSymbol();
            if (symbol instanceof OCPropertySymbol) {
                final String selector = OCSelectorExpressionImpl.this.getSelector();
                Label_0074: {
                    try {
                        if (symbol.getName().equals(selector)) {
                            break Label_0074;
                        }
                        final OCMethodSymbol ocMethodSymbol = symbol;
                        final String s = ocMethodSymbol.getName();
                        final String s2 = OCNameSuggester.getObjCSetterFromGetter(s);
                        final String s3 = selector;
                        final boolean b = s2.equals(s3);
                        if (b) {
                            break Label_0074;
                        }
                        return false;
                    }
                    catch (IncorrectOperationException ex2) {
                        throw a(ex2);
                    }
                    try {
                        final OCMethodSymbol ocMethodSymbol = symbol;
                        final String s = ocMethodSymbol.getName();
                        final String s2 = OCNameSuggester.getObjCSetterFromGetter(s);
                        final String s3 = selector;
                        final boolean b = s2.equals(s3);
                        if (b) {
                            return true;
                        }
                    }
                    catch (IncorrectOperationException ex3) {
                        throw a(ex3);
                    }
                }
                return false;
            }
            try {
                if (!(symbol instanceof OCMethodSymbol)) {
                    return false;
                }
            }
            catch (IncorrectOperationException ex4) {
                throw a(ex4);
            }
            final List<OCMethodSymbol> resolveToSymbols = this.resolveToSymbols();
            Label_0137: {
                try {
                    if (resolveToSymbols.contains(symbol)) {
                        break Label_0137;
                    }
                    final List<OCMethodSymbol> list = resolveToSymbols;
                    final OCMethodSymbol ocMethodSymbol2 = symbol;
                    final OCMethodSymbol ocMethodSymbol3 = ocMethodSymbol2;
                    final OCMethodSymbol ocMethodSymbol4 = ocMethodSymbol3.getAssociatedSymbol();
                    final boolean b3 = list.contains(ocMethodSymbol4);
                    if (b3) {
                        break Label_0137;
                    }
                    return false;
                }
                catch (IncorrectOperationException ex5) {
                    throw a(ex5);
                }
                try {
                    final List<OCMethodSymbol> list = resolveToSymbols;
                    final OCMethodSymbol ocMethodSymbol2 = symbol;
                    final OCMethodSymbol ocMethodSymbol3 = ocMethodSymbol2;
                    final OCMethodSymbol ocMethodSymbol4 = ocMethodSymbol3.getAssociatedSymbol();
                    final boolean b3 = list.contains(ocMethodSymbol4);
                    if (b3) {
                        return true;
                    }
                }
                catch (IncorrectOperationException ex6) {
                    throw a(ex6);
                }
            }
            return false;
        }
        
        @NotNull
        @Override
        public List<OCMethodSymbol> resolveToSymbols() {
            final OCObjectTypeContext actionTargetContext = OCSelectorAdHocResolver.getActionTargetContext(OCSelectorExpressionImpl.this);
            Label_0083: {
                List<OCMethodSymbol> list = null;
                Label_0048: {
                    try {
                        if (actionTargetContext == null) {
                            break Label_0083;
                        }
                        final OCObjectTypeContext ocObjectTypeContext = actionTargetContext;
                        ocObjectTypeContext.setStaticDoesntMatter();
                        final OCObjectTypeContext ocObjectTypeContext2 = actionTargetContext;
                        final SelectorReference selectorReference = this;
                        final OCSelectorExpressionImpl ocSelectorExpressionImpl = selectorReference.this$0;
                        final String s = ocSelectorExpressionImpl.getSelector();
                        final SelectorReference selectorReference2 = this;
                        final OCSelectorExpressionImpl ocSelectorExpressionImpl2 = selectorReference2.this$0;
                        final Project project = ocSelectorExpressionImpl2.getProject();
                        final OCSendMessageExpression.ProbableResponders probableResponders = ocObjectTypeContext2.getProbableResponders(s, project);
                        list = probableResponders.getAllResponders();
                        if (list == null) {
                            break Label_0048;
                        }
                        return list;
                    }
                    catch (IncorrectOperationException ex) {
                        throw a(ex);
                    }
                    try {
                        final OCObjectTypeContext ocObjectTypeContext = actionTargetContext;
                        ocObjectTypeContext.setStaticDoesntMatter();
                        final OCObjectTypeContext ocObjectTypeContext2 = actionTargetContext;
                        final SelectorReference selectorReference = this;
                        final OCSelectorExpressionImpl ocSelectorExpressionImpl = selectorReference.this$0;
                        final String s = ocSelectorExpressionImpl.getSelector();
                        final SelectorReference selectorReference2 = this;
                        final OCSelectorExpressionImpl ocSelectorExpressionImpl2 = selectorReference2.this$0;
                        final Project project = ocSelectorExpressionImpl2.getProject();
                        final OCSendMessageExpression.ProbableResponders probableResponders = ocObjectTypeContext2.getProbableResponders(s, project);
                        list = probableResponders.getAllResponders();
                        if (list == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCSelectorExpressionImpl$SelectorReference", "resolveToSymbols"));
                        }
                    }
                    catch (IncorrectOperationException ex2) {
                        throw a(ex2);
                    }
                }
                return list;
            }
            final HashMap<Object, OCMethodSymbol> hashMap = new HashMap<Object, OCMethodSymbol>();
            ArrayList list2;
            try {
                OCGlobalProjectSymbolsCache.processTopLevelAndMemberSymbols(OCSelectorExpressionImpl.this.getProject(), (Processor<OCSymbol>)(p1 -> {
                    // 
                    // This method could not be decompiled.
                    // 
                    // Original Bytecode:
                    // 
                    //     0: aload_2        
                    //     1: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
                    //     4: ifeq            140
                    //     7: aload_0        
                    //     8: aload_2        
                    //     9: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
                    //    12: invokespecial   com/jetbrains/cidr/lang/psi/impl/OCSelectorExpressionImpl$SelectorReference.a:(Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;)Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
                    //    15: astore_3       
                    //    16: aload_3        
                    //    17: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
                    //    22: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
                    //    25: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getPresentableName:()Ljava/lang/String;
                    //    30: astore          4
                    //    32: aload_3        
                    //    33: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
                    //    38: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCProtocolSymbol;
                    //    41: ifeq            66
                    //    44: new             Ljava/lang/StringBuilder;
                    //    47: dup            
                    //    48: invokespecial   java/lang/StringBuilder.<init>:()V
                    //    51: ldc             "protocol#"
                    //    53: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                    //    56: aload           4
                    //    58: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                    //    61: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                    //    64: astore          4
                    //    66: aload_1        
                    //    67: aload           4
                    //    69: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
                    //    74: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
                    //    77: astore          5
                    //    79: aload           5
                    //    81: ifnull          123
                    //    84: aload           5
                    //    86: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
                    //    91: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol;
                    //    94: ifeq            140
                    //    97: goto            104
                    //   100: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCSelectorExpressionImpl$SelectorReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
                    //   103: athrow         
                    //   104: aload_3        
                    //   105: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
                    //   110: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
                    //   113: ifeq            140
                    //   116: goto            123
                    //   119: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCSelectorExpressionImpl$SelectorReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
                    //   122: athrow         
                    //   123: aload_1        
                    //   124: aload           4
                    //   126: aload_3        
                    //   127: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
                    //   132: pop            
                    //   133: goto            140
                    //   136: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCSelectorExpressionImpl$SelectorReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
                    //   139: athrow         
                    //   140: iconst_1       
                    //   141: ireturn        
                    //    Exceptions:
                    //  Try           Handler
                    //  Start  End    Start  End    Type                                           
                    //  -----  -----  -----  -----  -----------------------------------------------
                    //  79     97     100    104    Lcom/intellij/util/IncorrectOperationException;
                    //  84     116    119    123    Lcom/intellij/util/IncorrectOperationException;
                    //  104    133    136    140    Lcom/intellij/util/IncorrectOperationException;
                    // 
                    // The error that occurred was:
                    // 
                    // java.lang.IllegalStateException: Expression is linked from several locations: Label_0104:
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
                    //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
                }), OCSelectorExpressionImpl.this.getSelector());
                list2 = new ArrayList(hashMap.values());
                if (list2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCSelectorExpressionImpl$SelectorReference", "resolveToSymbols"));
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
            return (List<OCMethodSymbol>)list2;
        }
        
        private OCMethodSymbol a(final OCMethodSymbol ocMethodSymbol) {
            final OCClassDeclarationBase ocClassDeclarationBase = ((OCSymbolWithParent<T, OCClassSymbol>)ocMethodSymbol).getParent().locateDefinition();
            try {
                if (ocClassDeclarationBase == null) {
                    return ocMethodSymbol;
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            final CommonProcessors.FindFirstProcessor<OCMemberSymbol> findFirstProcessor = new CommonProcessors.FindFirstProcessor<OCMemberSymbol>() {
                protected boolean accept(final OCMemberSymbol ocMemberSymbol) {
                    return ocMemberSymbol instanceof OCMethodSymbol;
                }
            };
            try {
                OCResolveUtil.processMemberSymbols(ocMethodSymbol.getName(), ocClassDeclarationBase.getLastChild(), (Processor<? super OCMemberSymbol>)findFirstProcessor);
                if (findFirstProcessor.isFound()) {
                    return (OCMethodSymbol)findFirstProcessor.getFoundValue();
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
            return ocMethodSymbol;
        }
        
        @NotNull
        public String getCanonicalText() {
            String selector;
            try {
                selector = OCSelectorExpressionImpl.this.getSelector();
                if (selector == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCSelectorExpressionImpl$SelectorReference", "getCanonicalText"));
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            return selector;
        }
        
        public PsiElement handleElementRename(String s) throws IncorrectOperationException {
            final OCSelectorExpressionImpl this$0 = OCSelectorExpressionImpl.this;
            String objCSetterFromGetter = null;
            Label_0044: {
                Label_0032: {
                    try {
                        if (!OCNameSuggester.isObjCSetter(OCSelectorExpressionImpl.this.getSelector())) {
                            break Label_0032;
                        }
                        final String s2 = s;
                        final boolean b = OCNameSuggester.isObjCSetter(s2);
                        if (!b) {
                            break Label_0032;
                        }
                        break Label_0032;
                    }
                    catch (IncorrectOperationException ex) {
                        throw a(ex);
                    }
                    try {
                        final String s2 = s;
                        final boolean b = OCNameSuggester.isObjCSetter(s2);
                        if (!b) {
                            objCSetterFromGetter = OCNameSuggester.getObjCSetterFromGetter(s);
                            break Label_0044;
                        }
                    }
                    catch (IncorrectOperationException ex2) {
                        throw a(ex2);
                    }
                }
                objCSetterFromGetter = s;
            }
            s = objCSetterFromGetter;
            return OCChangeUtil.replaceHandlingMacros((PsiElement)this$0, (PsiElement)OCElementFactory.expressionFromText("@selector(" + s + ")", (PsiElement)this$0));
        }
        
        @Override
        public PsiElement bindToSymbol(@NotNull final OCSymbol p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_1        
            //     1: ifnonnull       44
            //     4: new             Ljava/lang/IllegalArgumentException;
            //     7: dup            
            //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
            //    10: ldc             3
            //    12: anewarray       Ljava/lang/Object;
            //    15: dup            
            //    16: ldc             0
            //    18: ldc             "symbol"
            //    20: aastore        
            //    21: dup            
            //    22: ldc             1
            //    24: ldc             "com/jetbrains/cidr/lang/psi/impl/OCSelectorExpressionImpl$SelectorReference"
            //    26: aastore        
            //    27: dup            
            //    28: ldc             2
            //    30: ldc             "bindToSymbol"
            //    32: aastore        
            //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
            //    39: athrow         
            //    40: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCSelectorExpressionImpl$SelectorReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
            //    43: athrow         
            //    44: getstatic       com/jetbrains/cidr/lang/psi/impl/OCSelectorExpressionImpl$SelectorReference.$assertionsDisabled:Z
            //    47: ifne            90
            //    50: aload_1        
            //    51: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
            //    54: ifne            90
            //    57: goto            64
            //    60: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCSelectorExpressionImpl$SelectorReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
            //    63: athrow         
            //    64: aload_1        
            //    65: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
            //    68: ifne            90
            //    71: goto            78
            //    74: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCSelectorExpressionImpl$SelectorReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
            //    77: athrow         
            //    78: new             Ljava/lang/AssertionError;
            //    81: dup            
            //    82: invokespecial   java/lang/AssertionError.<init>:()V
            //    85: athrow         
            //    86: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCSelectorExpressionImpl$SelectorReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
            //    89: athrow         
            //    90: aload_0        
            //    91: aload_1        
            //    92: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
            //    97: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCSelectorExpressionImpl$SelectorReference.handleElementRename:(Ljava/lang/String;)Lcom/intellij/psi/PsiElement;
            //   100: areturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                           
            //  -----  -----  -----  -----  -----------------------------------------------
            //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
            //  44     57     60     64     Lcom/intellij/util/IncorrectOperationException;
            //  50     71     74     78     Lcom/intellij/util/IncorrectOperationException;
            //  64     86     86     90     Lcom/intellij/util/IncorrectOperationException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0064:
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
        
        public PsiElement bindToElement(@NotNull final PsiElement psiElement) throws IncorrectOperationException {
            try {
                if (psiElement == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/psi/impl/OCSelectorExpressionImpl$SelectorReference", "bindToElement"));
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            final OCSymbol symbol = ((OCSymbolDeclarator)psiElement).getSymbol();
            try {
                if (symbol != null) {
                    return this.bindToSymbol(symbol);
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
            return psiElement;
        }
        
        public boolean isSoft() {
            return false;
        }
        
        static {
            boolean $assertionsDisabled2 = false;
            Label_0017: {
                try {
                    if (!OCSelectorExpressionImpl.class.desiredAssertionStatus()) {
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
}
