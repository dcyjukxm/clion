// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import java.util.List;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.symbols.objc.OCImplementationSymbol;
import com.intellij.util.IncorrectOperationException;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCReference;

private class AccessorMethodReference implements OCReference
{
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public OCSymbol resolveToSymbol() {
        final OCImplementationSymbol a = this.a();
        final CommonProcessors.FindFirstProcessor<OCMethodSymbol> findFirstProcessor = new CommonProcessors.FindFirstProcessor<OCMethodSymbol>() {
            public boolean process(final OCMethodSymbol ocMethodSymbol) {
                return ocMethodSymbol.isStatic() || super.process((Object)ocMethodSymbol);
            }
        };
        try {
            if (a != null) {
                a.processMembers(this.getCanonicalText(), (Class<? extends OCMemberSymbol>)OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMemberSymbol>)findFirstProcessor);
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return (OCSymbol)findFirstProcessor.getFoundValue();
    }
    
    @Nullable
    private OCImplementationSymbol a() {
        final OCClassDeclaration ocClassDeclaration = (OCClassDeclaration)PsiTreeUtil.getParentOfType(this.getElement(), (Class)OCClassDeclaration.class);
        try {
            if (ocClassDeclaration == null) {
                return null;
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final OCClassSymbol symbol = ocClassDeclaration.getSymbol();
        try {
            if (symbol == null) {
                return null;
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        return symbol.getImplementation();
    }
    
    public PsiElement getElement() {
        return (PsiElement)OCPropertyAttributeImpl.this;
    }
    
    public TextRange getRangeInElement() {
        return OCElementUtil.getRangeInParent(OCPropertyAttributeImpl.this.getValueElement());
    }
    
    public PsiElement resolve() {
        final OCSymbol resolveToSymbol = this.resolveToSymbol();
        try {
            if (resolveToSymbol != null) {
                return resolveToSymbol.locateDefinition();
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return null;
    }
    
    @NotNull
    public String getCanonicalText() {
        String value;
        try {
            value = OCPropertyAttributeImpl.this.getValue();
            if (value == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCPropertyAttributeImpl$AccessorMethodReference", "getCanonicalText"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return value;
    }
    
    public PsiElement handleElementRename(final String s) throws IncorrectOperationException {
        try {
            if (!OCPropertyAttributeImpl.this.getName().equals(s)) {
                return OCPropertyAttributeImpl.this.getValueElement().replace(OCElementFactory.createIdentifier(s, (PsiElement)OCPropertyAttributeImpl.this));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return this.getElement();
    }
    
    @Override
    public PsiElement bindToSymbol(@NotNull final OCSymbol ocSymbol) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/psi/impl/OCPropertyAttributeImpl$AccessorMethodReference", "bindToSymbol"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        Label_0064: {
            try {
                if (AccessorMethodReference.$assertionsDisabled) {
                    return this.handleElementRename(ocSymbol.getName());
                }
                final OCSymbol ocSymbol2 = ocSymbol;
                final boolean b = ocSymbol2 instanceof OCMethodSymbol;
                if (!b) {
                    break Label_0064;
                }
                return this.handleElementRename(ocSymbol.getName());
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
            try {
                final OCSymbol ocSymbol2 = ocSymbol;
                final boolean b = ocSymbol2 instanceof OCMethodSymbol;
                if (!b) {
                    throw new AssertionError();
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
        }
        return this.handleElementRename(ocSymbol.getName());
    }
    
    public PsiElement bindToElement(@NotNull final PsiElement psiElement) throws IncorrectOperationException {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/psi/impl/OCPropertyAttributeImpl$AccessorMethodReference", "bindToElement"));
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
    
    public boolean isReferenceTo(final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSymbolDeclarator;
        //     4: ifne            13
        //     7: iconst_0       
        //     8: ireturn        
        //     9: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCPropertyAttributeImpl$AccessorMethodReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    12: athrow         
        //    13: aload_1        
        //    14: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSymbolDeclarator;
        //    17: invokeinterface com/jetbrains/cidr/lang/psi/OCSymbolDeclarator.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    22: astore_2       
        //    23: aload_0        
        //    24: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCPropertyAttributeImpl$AccessorMethodReference.resolveToSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    27: astore_3       
        //    28: aload_2        
        //    29: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //    32: ifeq            81
        //    35: aload_3        
        //    36: aload_2        
        //    37: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //    40: ifne            73
        //    43: goto            50
        //    46: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCPropertyAttributeImpl$AccessorMethodReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    49: athrow         
        //    50: aload_3        
        //    51: aload_2        
        //    52: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //    55: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getAssociatedSymbol:()Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //    60: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //    63: ifeq            81
        //    66: goto            73
        //    69: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCPropertyAttributeImpl$AccessorMethodReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    72: athrow         
        //    73: iconst_1       
        //    74: goto            82
        //    77: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCPropertyAttributeImpl$AccessorMethodReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    80: athrow         
        //    81: iconst_0       
        //    82: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      9      9      13     Lcom/intellij/util/IncorrectOperationException;
        //  28     43     46     50     Lcom/intellij/util/IncorrectOperationException;
        //  35     66     69     73     Lcom/intellij/util/IncorrectOperationException;
        //  50     77     77     81     Lcom/intellij/util/IncorrectOperationException;
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
    public Object[] getVariants() {
        final OCImplementationSymbol a = this.a();
        final boolean equals = OCPropertyAttributeImpl.this.getName().equals("setter");
        final ArrayList list = new ArrayList();
        try {
            if (a != null) {
                a.processMembers((String)null, (Class<? extends OCMemberSymbol>)OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMemberSymbol>)(p2 -> {
                    // 
                    // This method could not be decompiled.
                    // 
                    // Original Bytecode:
                    // 
                    //     0: aload_2        
                    //     1: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.isGetter:()Z
                    //     6: ifeq            20
                    //     9: iload_0        
                    //    10: ifeq            47
                    //    13: goto            20
                    //    16: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCPropertyAttributeImpl$AccessorMethodReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
                    //    19: athrow         
                    //    20: aload_2        
                    //    21: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.isSetter:()Z
                    //    26: ifeq            75
                    //    29: goto            36
                    //    32: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCPropertyAttributeImpl$AccessorMethodReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
                    //    35: athrow         
                    //    36: iload_0        
                    //    37: ifeq            75
                    //    40: goto            47
                    //    43: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCPropertyAttributeImpl$AccessorMethodReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
                    //    46: athrow         
                    //    47: aload_1        
                    //    48: aload_2        
                    //    49: invokestatic    com/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil.lookup:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Lcom/intellij/codeInsight/lookup/LookupElementBuilder;
                    //    52: new             Lcom/intellij/codeInsight/completion/BasicInsertHandler;
                    //    55: dup            
                    //    56: invokespecial   com/intellij/codeInsight/completion/BasicInsertHandler.<init>:()V
                    //    59: invokevirtual   com/intellij/codeInsight/lookup/LookupElementBuilder.withInsertHandler:(Lcom/intellij/codeInsight/completion/InsertHandler;)Lcom/intellij/codeInsight/lookup/LookupElementBuilder;
                    //    62: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
                    //    67: pop            
                    //    68: goto            75
                    //    71: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCPropertyAttributeImpl$AccessorMethodReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
                    //    74: athrow         
                    //    75: iconst_1       
                    //    76: ireturn        
                    //    Exceptions:
                    //  Try           Handler
                    //  Start  End    Start  End    Type                                           
                    //  -----  -----  -----  -----  -----------------------------------------------
                    //  0      13     16     20     Lcom/intellij/util/IncorrectOperationException;
                    //  9      29     32     36     Lcom/intellij/util/IncorrectOperationException;
                    //  20     40     43     47     Lcom/intellij/util/IncorrectOperationException;
                    //  36     68     71     75     Lcom/intellij/util/IncorrectOperationException;
                    // 
                    // The error that occurred was:
                    // 
                    // java.lang.IllegalStateException: Expression is linked from several locations: Label_0020:
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
                }));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        Object[] array;
        try {
            array = list.toArray();
            if (array == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCPropertyAttributeImpl$AccessorMethodReference", "getVariants"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        return array;
    }
    
    public boolean isSoft() {
        return false;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCPropertyAttributeImpl.class.desiredAssertionStatus()) {
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
