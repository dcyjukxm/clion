// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.intellij.util.ArrayUtil;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.refactoring.OCNameSuggester;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.IncorrectOperationException;
import com.intellij.openapi.util.TextRange;
import org.jetbrains.annotations.Contract;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.resolve.references.OCReferenceWithContext;

class OCQualifiedExpressionImpl$2 implements OCReferenceWithContext {
    @Contract(pure = true)
    public PsiElement getElement() {
        return (PsiElement)OCQualifiedExpressionImpl.this;
    }
    
    public TextRange getRangeInElement() {
        return OCQualifiedExpressionImpl.this.getRangeInCallElement();
    }
    
    public PsiElement resolve() {
        return OCQualifiedExpressionImpl.this.resolve();
    }
    
    @NotNull
    public String getCanonicalText() {
        String symbolName;
        try {
            symbolName = OCQualifiedExpressionImpl.this.getSymbolName();
            if (symbolName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl$2", "getCanonicalText"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return symbolName;
    }
    
    public PsiElement handleElementRename(final String name) throws IncorrectOperationException {
        return OCQualifiedExpressionImpl.this.setName(name);
    }
    
    @Override
    public PsiElement bindToSymbol(@NotNull final OCSymbol ocSymbol) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl$2", "bindToSymbol"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final String name = ocSymbol.getName();
        final String objCGetterFromSetter = OCNameSuggester.getObjCGetterFromSetter(name);
        try {
            if (objCGetterFromSetter != null) {
                this.handleElementRename(objCGetterFromSetter);
                return this.getElement();
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        this.handleElementRename(name);
        return this.getElement();
    }
    
    public PsiElement bindToElement(@NotNull final PsiElement psiElement) throws IncorrectOperationException {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl$2", "bindToElement"));
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
        //     9: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl$2.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    12: athrow         
        //    13: aload_1        
        //    14: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSymbolDeclarator;
        //    17: invokeinterface com/jetbrains/cidr/lang/psi/OCSymbolDeclarator.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    22: astore_2       
        //    23: aload_0        
        //    24: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl$2.resolveToSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    27: astore_3       
        //    28: aload_3        
        //    29: aload_2        
        //    30: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //    33: ifeq            42
        //    36: iconst_1       
        //    37: ireturn        
        //    38: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl$2.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    41: athrow         
        //    42: aload_2        
        //    43: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //    46: ifeq            181
        //    49: aload_3        
        //    50: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //    53: ifeq            181
        //    56: goto            63
        //    59: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl$2.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    62: athrow         
        //    63: new             Lcom/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector;
        //    66: dup            
        //    67: invokespecial   com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.<init>:()V
        //    70: aload_0        
        //    71: getfield        com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl$2.this$0:Lcom/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl;
        //    74: invokevirtual   com/jetbrains/cidr/lang/search/usages/OCReadWriteAccessDetector.getExpressionAccess:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/codeInsight/highlighting/ReadWriteAccessDetector$Access;
        //    77: astore          4
        //    79: aload           4
        //    81: getstatic       com/intellij/codeInsight/highlighting/ReadWriteAccessDetector$Access.Read:Lcom/intellij/codeInsight/highlighting/ReadWriteAccessDetector$Access;
        //    84: if_acmpne       112
        //    87: aload_2        
        //    88: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //    93: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.isObjCSetter:(Ljava/lang/String;)Z
        //    96: ifeq            112
        //    99: goto            106
        //   102: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl$2.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   105: athrow         
        //   106: iconst_0       
        //   107: ireturn        
        //   108: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl$2.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   111: athrow         
        //   112: aload           4
        //   114: getstatic       com/intellij/codeInsight/highlighting/ReadWriteAccessDetector$Access.Write:Lcom/intellij/codeInsight/highlighting/ReadWriteAccessDetector$Access;
        //   117: if_acmpne       145
        //   120: aload_2        
        //   121: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //   126: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.isObjCGetter:(Ljava/lang/String;)Z
        //   129: ifeq            145
        //   132: goto            139
        //   135: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl$2.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   138: athrow         
        //   139: iconst_0       
        //   140: ireturn        
        //   141: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl$2.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   144: athrow         
        //   145: aload_2        
        //   146: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getAssociatedSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   151: astore_2       
        //   152: aload_2        
        //   153: ifnull          166
        //   156: aload_2        
        //   157: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   160: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getGeneratedFromProperty:()Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   165: astore_2       
        //   166: aload_3        
        //   167: aload_2        
        //   168: if_acmpne       179
        //   171: iconst_1       
        //   172: goto            180
        //   175: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl$2.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   178: athrow         
        //   179: iconst_0       
        //   180: ireturn        
        //   181: aload_2        
        //   182: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   185: ifeq            272
        //   188: aload_3        
        //   189: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   192: ifeq            272
        //   195: goto            202
        //   198: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl$2.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   201: athrow         
        //   202: aload_2        
        //   203: aload_3        
        //   204: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   207: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getGeneratedFromProperty:()Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   212: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   215: ifeq            231
        //   218: goto            225
        //   221: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl$2.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   224: athrow         
        //   225: iconst_1       
        //   226: ireturn        
        //   227: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl$2.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   230: athrow         
        //   231: aload_3        
        //   232: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getAssociatedSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   237: astore_3       
        //   238: aload_3        
        //   239: ifnull          270
        //   242: aload_3        
        //   243: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   246: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getGeneratedFromProperty:()Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   251: aload_2        
        //   252: if_acmpne       270
        //   255: goto            262
        //   258: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl$2.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   261: athrow         
        //   262: iconst_1       
        //   263: goto            271
        //   266: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl$2.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   269: athrow         
        //   270: iconst_0       
        //   271: ireturn        
        //   272: aload_3        
        //   273: instanceof      Lcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol;
        //   276: ifeq            307
        //   279: aload_2        
        //   280: astore          4
        //   282: aload_3        
        //   283: checkcast       Lcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol;
        //   286: invokevirtual   com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol.getOverloads:()Ljava/util/List;
        //   289: invokeinterface java/util/List.stream:()Ljava/util/stream/Stream;
        //   294: aload           4
        //   296: invokedynamic   test:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Ljava/util/function/Predicate;
        //   301: invokeinterface java/util/stream/Stream.anyMatch:(Ljava/util/function/Predicate;)Z
        //   306: ireturn        
        //   307: aload_3        
        //   308: ifnull          336
        //   311: aload_3        
        //   312: aload_2        
        //   313: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isSameSymbol:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //   318: ifeq            336
        //   321: goto            328
        //   324: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl$2.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   327: athrow         
        //   328: iconst_1       
        //   329: goto            337
        //   332: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl$2.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   335: athrow         
        //   336: iconst_0       
        //   337: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      9      9      13     Lcom/intellij/util/IncorrectOperationException;
        //  28     38     38     42     Lcom/intellij/util/IncorrectOperationException;
        //  42     56     59     63     Lcom/intellij/util/IncorrectOperationException;
        //  79     99     102    106    Lcom/intellij/util/IncorrectOperationException;
        //  87     108    108    112    Lcom/intellij/util/IncorrectOperationException;
        //  112    132    135    139    Lcom/intellij/util/IncorrectOperationException;
        //  120    141    141    145    Lcom/intellij/util/IncorrectOperationException;
        //  166    175    175    179    Lcom/intellij/util/IncorrectOperationException;
        //  181    195    198    202    Lcom/intellij/util/IncorrectOperationException;
        //  188    218    221    225    Lcom/intellij/util/IncorrectOperationException;
        //  202    227    227    231    Lcom/intellij/util/IncorrectOperationException;
        //  238    255    258    262    Lcom/intellij/util/IncorrectOperationException;
        //  242    266    266    270    Lcom/intellij/util/IncorrectOperationException;
        //  307    321    324    328    Lcom/intellij/util/IncorrectOperationException;
        //  311    332    332    336    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0202:
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
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl$2", "getVariants"));
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
        return OCQualifiedExpressionImpl.this.resolveToSymbol();
    }
    
    @Nullable
    @Override
    public OCSymbol resolveToSymbol(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl$2", "resolveToSymbol"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return OCQualifiedExpressionImpl.this.resolveToSymbol(ocResolveContext, true, false, false);
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}