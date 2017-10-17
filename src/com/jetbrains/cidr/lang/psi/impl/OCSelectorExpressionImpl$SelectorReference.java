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
import org.jetbrains.annotations.NotNull;
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
import java.util.List;
import com.jetbrains.cidr.lang.refactoring.OCNameSuggester;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.intellij.util.IncorrectOperationException;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.resolve.references.OCPolyVariantReferenceImpl;

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
