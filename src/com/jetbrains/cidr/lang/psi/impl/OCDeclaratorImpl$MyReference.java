// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.util.Producer;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.resolve.OCArgumentsList;
import com.jetbrains.cidr.lang.psi.OCStruct;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.types.OCCppReferenceType;
import com.intellij.psi.impl.source.resolve.ResolveCache;
import com.intellij.util.ArrayUtil;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.IncorrectOperationException;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCReference;

private class MyReference implements OCReference
{
    public PsiElement getElement() {
        return (PsiElement)OCDeclaratorImpl.this;
    }
    
    public TextRange getRangeInElement() {
        final PsiElement nameIdentifier = OCDeclaratorImpl.this.getNameIdentifier();
        try {
            if (nameIdentifier != null) {
                return TextRange.from(nameIdentifier.getStartOffsetInParent(), nameIdentifier.getTextLength());
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return TextRange.EMPTY_RANGE;
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
        String name;
        try {
            name = OCDeclaratorImpl.this.getName();
            if (name == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl$MyReference", "getCanonicalText"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return name;
    }
    
    public PsiElement handleElementRename(final String s) throws IncorrectOperationException {
        return (PsiElement)OCDeclaratorImpl.this;
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
        //    24: ldc             "com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl$MyReference"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "bindToSymbol"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl$MyReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    43: athrow         
        //    44: getstatic       com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl$MyReference.$assertionsDisabled:Z
        //    47: ifne            100
        //    50: aload_1        
        //    51: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    56: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.STRUCT:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    59: if_acmpeq       100
        //    62: goto            69
        //    65: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl$MyReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    68: athrow         
        //    69: aload_1        
        //    70: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    75: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isConstructorOrDestructor:()Z
        //    78: ifne            100
        //    81: goto            88
        //    84: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl$MyReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    87: athrow         
        //    88: new             Ljava/lang/AssertionError;
        //    91: dup            
        //    92: invokespecial   java/lang/AssertionError.<init>:()V
        //    95: athrow         
        //    96: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl$MyReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    99: athrow         
        //   100: aload_0        
        //   101: aload_1        
        //   102: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //   107: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl$MyReference.handleElementRename:(Ljava/lang/String;)Lcom/intellij/psi/PsiElement;
        //   110: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  44     62     65     69     Lcom/intellij/util/IncorrectOperationException;
        //  50     81     84     88     Lcom/intellij/util/IncorrectOperationException;
        //  69     96     96     100    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0069:
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
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl$MyReference", "bindToElement"));
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
        //     1: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //     4: ifne            13
        //     7: iconst_0       
        //     8: ireturn        
        //     9: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl$MyReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    12: athrow         
        //    13: aload_1        
        //    14: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //    17: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDeclaration.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    22: astore_2       
        //    23: aload_0        
        //    24: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl$MyReference.resolveToSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    27: astore_3       
        //    28: aload_3        
        //    29: aload_2        
        //    30: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //    33: ifeq            42
        //    36: iconst_1       
        //    37: ireturn        
        //    38: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl$MyReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    41: athrow         
        //    42: aload_3        
        //    43: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //    46: ifeq            157
        //    49: aload_2        
        //    50: ifnull          157
        //    53: goto            60
        //    56: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl$MyReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    59: athrow         
        //    60: aload_3        
        //    61: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isGlobal:()Z
        //    66: ifeq            157
        //    69: goto            76
        //    72: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl$MyReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    75: athrow         
        //    76: aload_2        
        //    77: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isGlobal:()Z
        //    82: ifeq            157
        //    85: goto            92
        //    88: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl$MyReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    91: athrow         
        //    92: aload_3        
        //    93: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //    96: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getResolvedQualifiedName:()Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //    99: astore          4
        //   101: aload_2        
        //   102: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   105: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getResolvedQualifiedName:()Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   108: astore          5
        //   110: aload           4
        //   112: aload           5
        //   114: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //   117: ifeq            155
        //   120: aload_3        
        //   121: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   126: astore          6
        //   128: aload_2        
        //   129: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   134: astore          7
        //   136: aload           6
        //   138: aload           7
        //   140: aload_1        
        //   141: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equals:(Ljava/lang/Object;Lcom/intellij/psi/PsiElement;)Z
        //   144: ifne            153
        //   147: iconst_0       
        //   148: ireturn        
        //   149: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl$MyReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   152: athrow         
        //   153: iconst_1       
        //   154: ireturn        
        //   155: iconst_0       
        //   156: ireturn        
        //   157: iconst_0       
        //   158: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      9      9      13     Lcom/intellij/util/IncorrectOperationException;
        //  28     38     38     42     Lcom/intellij/util/IncorrectOperationException;
        //  42     53     56     60     Lcom/intellij/util/IncorrectOperationException;
        //  49     69     72     76     Lcom/intellij/util/IncorrectOperationException;
        //  60     85     88     92     Lcom/intellij/util/IncorrectOperationException;
        //  136    149    149    153    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0060:
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
        Object[] empty_OBJECT_ARRAY;
        try {
            empty_OBJECT_ARRAY = ArrayUtil.EMPTY_OBJECT_ARRAY;
            if (empty_OBJECT_ARRAY == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCDeclaratorImpl$MyReference", "getVariants"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return empty_OBJECT_ARRAY;
    }
    
    public boolean isSoft() {
        return false;
    }
    
    public OCSymbol resolveToSymbol() {
        return ResolveCache.getInstance(OCDeclaratorImpl.this.getProject()).resolveWithCaching(this, (ResolveCache.AbstractResolver<MyReference, OCSymbol>)OCDeclaratorImpl.access$100(), false, false);
    }
    
    public OCSymbol doResolveToSymbol() {
        OCType ocType = OCDeclaratorImpl.this.getResolvedType();
        if (ocType instanceof OCCppReferenceType) {
            ocType = ((OCCppReferenceType)ocType).getRefType();
        }
        try {
            if (!(ocType instanceof OCStructType)) {
                return null;
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final PsiElement parent = OCDeclaratorImpl.this.getParent();
        Label_0076: {
            Label_0070: {
                try {
                    if (!(parent instanceof OCDeclaration)) {
                        break Label_0070;
                    }
                    final PsiElement psiElement = parent;
                    final OCDeclaration ocDeclaration = (OCDeclaration)psiElement;
                    final boolean b = ocDeclaration.isTypedef();
                    if (b) {
                        break Label_0070;
                    }
                    break Label_0076;
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
                try {
                    final PsiElement psiElement = parent;
                    final OCDeclaration ocDeclaration = (OCDeclaration)psiElement;
                    final boolean b = ocDeclaration.isTypedef();
                    if (b) {
                        return null;
                    }
                }
                catch (IncorrectOperationException ex3) {
                    throw a(ex3);
                }
            }
            try {
                if (parent.getParent() instanceof OCStruct) {
                    return null;
                }
            }
            catch (IncorrectOperationException ex4) {
                throw a(ex4);
            }
        }
        return ((OCStructType)ocType).findConstructor(OCArgumentsList.getArgumentList(OCDeclaratorImpl.this.getInitializers()), new OCResolveContext((PsiElement)OCDeclaratorImpl.this), OCDeclaratorImpl.this.isExplicitConstructorCall(), false, null);
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCDeclaratorImpl.class.desiredAssertionStatus()) {
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
