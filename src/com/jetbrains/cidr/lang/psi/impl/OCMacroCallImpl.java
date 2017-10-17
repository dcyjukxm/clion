// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.intellij.psi.impl.source.tree.LeafElement;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.psi.OCMacroCallArgument;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCMacroSymbol;
import com.jetbrains.cidr.lang.preprocessor.OCMacroForeignLeafElement;
import com.intellij.psi.impl.source.tree.ForeignLeafPsiElement;
import com.jetbrains.cidr.lang.parser.OCMacroRange;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCExpression;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCMacroCall;

public class OCMacroCallImpl extends OCElementBase implements OCMacroCall
{
    public OCMacroCallImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCMacroCallImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Nullable
    @Override
    public OCReferenceElement getMacroReferenceElement() {
        return this.findChildByType(OCElementTypes.MACRO_REF);
    }
    
    @Nullable
    @Override
    public OCExpression getExpansionExpression() {
        Object o = this;
        for (PsiElement psiElement = ((PsiElement)o).getNextSibling(); psiElement instanceof OCMacroCall; psiElement = ((PsiElement)o).getNextSibling()) {
            o = psiElement;
        }
        OCExpression ocExpression;
        for (ocExpression = (OCExpression)PsiTreeUtil.getContextOfType(a((PsiElement)o), new Class[] { OCExpression.class }); ocExpression != null; ocExpression = (OCExpression)PsiTreeUtil.getContextOfType((PsiElement)ocExpression, new Class[] { OCExpression.class })) {
            final OCMacroRange rangeInMacroCall = OCElementUtil.getRangeInMacroCall((PsiElement)ocExpression);
            Label_0087: {
                try {
                    if (rangeInMacroCall == null) {
                        continue;
                    }
                    final OCMacroRange ocMacroRange = rangeInMacroCall;
                    final OCMacroCall ocMacroCall = ocMacroRange.getMacroCall();
                    final OCMacroCallImpl ocMacroCallImpl = this;
                    final boolean b = ocMacroCall.equals(ocMacroCallImpl);
                    if (b) {
                        break Label_0087;
                    }
                    continue;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final OCMacroRange ocMacroRange = rangeInMacroCall;
                    final OCMacroCall ocMacroCall = ocMacroRange.getMacroCall();
                    final OCMacroCallImpl ocMacroCallImpl = this;
                    final boolean b = ocMacroCall.equals(ocMacroCallImpl);
                    if (b) {
                        break;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
        }
        while (ocExpression != null) {
            final OCExpression ocExpression2 = (OCExpression)PsiTreeUtil.getContextOfType((PsiElement)ocExpression, new Class[] { OCExpression.class });
            try {
                if (ocExpression2 == null) {
                    break;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            final OCMacroRange rangeInMacroCall2 = OCElementUtil.getRangeInMacroCall((PsiElement)ocExpression2);
            Label_0183: {
                try {
                    if (rangeInMacroCall2 == null) {
                        break;
                    }
                    final OCMacroRange ocMacroRange2 = rangeInMacroCall2;
                    final OCMacroCall ocMacroCall2 = ocMacroRange2.getMacroCall();
                    final OCMacroCallImpl ocMacroCallImpl2 = this;
                    final boolean b2 = ocMacroCall2.equals(ocMacroCallImpl2);
                    if (!b2) {
                        break Label_0183;
                    }
                    break Label_0183;
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
                try {
                    final OCMacroRange ocMacroRange2 = rangeInMacroCall2;
                    final OCMacroCall ocMacroCall2 = ocMacroRange2.getMacroCall();
                    final OCMacroCallImpl ocMacroCallImpl2 = this;
                    final boolean b2 = ocMacroCall2.equals(ocMacroCallImpl2);
                    if (!b2) {
                        break;
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
            }
            ocExpression = ocExpression2;
        }
        return ocExpression;
    }
    
    @Override
    public PsiElement getLastExpansionLeaf() {
        PsiElement firstExpansionLeaf = this.getFirstExpansionLeaf();
        while (true) {
            try {
                if (firstExpansionLeaf == null) {
                    break;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            final PsiElement a = a(firstExpansionLeaf);
            try {
                if (!(a instanceof ForeignLeafPsiElement)) {
                    break;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            firstExpansionLeaf = a;
        }
        return firstExpansionLeaf;
    }
    
    @Override
    public PsiElement getFirstExpansionLeaf() {
        Object o = this;
        for (PsiElement psiElement = ((PsiElement)o).getNextSibling(); psiElement instanceof OCMacroCall; psiElement = ((PsiElement)o).getNextSibling()) {
            o = psiElement;
        }
        final PsiElement a = a((PsiElement)o);
        try {
            if (a instanceof OCMacroForeignLeafElement) {
                return a;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return null;
    }
    
    @Override
    public OCMacroSymbol resolveToSymbol() {
        final OCMacroReferenceElementImpl ocMacroReferenceElementImpl = (OCMacroReferenceElementImpl)this.getMacroReferenceElement();
        try {
            if (ocMacroReferenceElementImpl == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCSymbol resolveToSymbol = ocMacroReferenceElementImpl.resolveToSymbol();
        try {
            if (resolveToSymbol instanceof OCMacroSymbol) {
                return (OCMacroSymbol)resolveToSymbol;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return null;
    }
    
    @Override
    public List<OCMacroCallArgument> getArguments() {
        return this.findChildrenByType(OCElementTypes.MACRO_ARGUMENT);
    }
    
    @NotNull
    @Override
    public ParameterCheckResult checkParameters(@NotNull final OCMacroSymbol p0) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/psi/impl/OCMacroCallImpl"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "checkParameters"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCMacroCallImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCMacroCallImpl.getArguments:()Ljava/util/List;
        //    48: astore_2       
        //    49: aload_2        
        //    50: invokeinterface java/util/List.size:()I
        //    55: istore_3       
        //    56: aload_1        
        //    57: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol.hasParameterList:()Z
        //    60: ifne            138
        //    63: iload_3        
        //    64: ifne            90
        //    67: goto            74
        //    70: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCMacroCallImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    73: athrow         
        //    74: new             Lcom/jetbrains/cidr/lang/psi/OCMacroCall$ParameterCheckResult;
        //    77: dup            
        //    78: iconst_m1      
        //    79: iconst_m1      
        //    80: invokespecial   com/jetbrains/cidr/lang/psi/OCMacroCall$ParameterCheckResult.<init>:(II)V
        //    83: goto            99
        //    86: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCMacroCallImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    89: athrow         
        //    90: new             Lcom/jetbrains/cidr/lang/psi/OCMacroCall$ParameterCheckResult;
        //    93: dup            
        //    94: iload_3        
        //    95: iconst_0       
        //    96: invokespecial   com/jetbrains/cidr/lang/psi/OCMacroCall$ParameterCheckResult.<init>:(II)V
        //    99: dup            
        //   100: ifnonnull       137
        //   103: new             Ljava/lang/IllegalStateException;
        //   106: dup            
        //   107: ldc             "@NotNull method %s.%s must not return null"
        //   109: ldc             2
        //   111: anewarray       Ljava/lang/Object;
        //   114: dup            
        //   115: ldc             0
        //   117: ldc             "com/jetbrains/cidr/lang/psi/impl/OCMacroCallImpl"
        //   119: aastore        
        //   120: dup            
        //   121: ldc             1
        //   123: ldc             "checkParameters"
        //   125: aastore        
        //   126: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   129: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   132: athrow         
        //   133: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCMacroCallImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   136: athrow         
        //   137: areturn        
        //   138: aload_1        
        //   139: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol.getParameterNames:()Lcom/jetbrains/cidr/lang/util/OCImmutableList;
        //   142: astore          4
        //   144: aload           4
        //   146: invokeinterface java/util/List.isEmpty:()Z
        //   151: ifeq            255
        //   154: iload_3        
        //   155: ifeq            200
        //   158: goto            165
        //   161: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCMacroCallImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   164: athrow         
        //   165: iload_3        
        //   166: iconst_1       
        //   167: if_icmpne       255
        //   170: goto            177
        //   173: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCMacroCallImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   176: athrow         
        //   177: aload_2        
        //   178: iconst_0       
        //   179: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   184: checkcast       Lcom/intellij/psi/PsiElement;
        //   187: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isWhitespace:(Lcom/intellij/psi/PsiElement;)Z
        //   190: ifeq            255
        //   193: goto            200
        //   196: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCMacroCallImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   199: athrow         
        //   200: new             Lcom/jetbrains/cidr/lang/psi/OCMacroCall$ParameterCheckResult;
        //   203: dup            
        //   204: iconst_0       
        //   205: iconst_0       
        //   206: invokespecial   com/jetbrains/cidr/lang/psi/OCMacroCall$ParameterCheckResult.<init>:(II)V
        //   209: dup            
        //   210: ifnonnull       254
        //   213: goto            220
        //   216: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCMacroCallImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   219: athrow         
        //   220: new             Ljava/lang/IllegalStateException;
        //   223: dup            
        //   224: ldc             "@NotNull method %s.%s must not return null"
        //   226: ldc             2
        //   228: anewarray       Ljava/lang/Object;
        //   231: dup            
        //   232: ldc             0
        //   234: ldc             "com/jetbrains/cidr/lang/psi/impl/OCMacroCallImpl"
        //   236: aastore        
        //   237: dup            
        //   238: ldc             1
        //   240: ldc             "checkParameters"
        //   242: aastore        
        //   243: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   246: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   249: athrow         
        //   250: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCMacroCallImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   253: athrow         
        //   254: areturn        
        //   255: aload_1        
        //   256: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol.isVararg:()Z
        //   259: istore          5
        //   261: iload           5
        //   263: ifeq            282
        //   266: aload           4
        //   268: invokeinterface java/util/List.size:()I
        //   273: iconst_1       
        //   274: isub           
        //   275: goto            289
        //   278: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCMacroCallImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   281: athrow         
        //   282: aload           4
        //   284: invokeinterface java/util/List.size:()I
        //   289: istore          6
        //   291: iload_3        
        //   292: iload           6
        //   294: if_icmplt       322
        //   297: iload_3        
        //   298: iload           6
        //   300: if_icmple       378
        //   303: goto            310
        //   306: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCMacroCallImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   309: athrow         
        //   310: iload           5
        //   312: ifne            378
        //   315: goto            322
        //   318: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCMacroCallImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   321: athrow         
        //   322: new             Lcom/jetbrains/cidr/lang/psi/OCMacroCall$ParameterCheckResult;
        //   325: dup            
        //   326: iload_3        
        //   327: iload           6
        //   329: invokespecial   com/jetbrains/cidr/lang/psi/OCMacroCall$ParameterCheckResult.<init>:(II)V
        //   332: dup            
        //   333: ifnonnull       377
        //   336: goto            343
        //   339: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCMacroCallImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   342: athrow         
        //   343: new             Ljava/lang/IllegalStateException;
        //   346: dup            
        //   347: ldc             "@NotNull method %s.%s must not return null"
        //   349: ldc             2
        //   351: anewarray       Ljava/lang/Object;
        //   354: dup            
        //   355: ldc             0
        //   357: ldc             "com/jetbrains/cidr/lang/psi/impl/OCMacroCallImpl"
        //   359: aastore        
        //   360: dup            
        //   361: ldc             1
        //   363: ldc             "checkParameters"
        //   365: aastore        
        //   366: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   369: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   372: athrow         
        //   373: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCMacroCallImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   376: athrow         
        //   377: areturn        
        //   378: new             Lcom/jetbrains/cidr/lang/psi/OCMacroCall$ParameterCheckResult;
        //   381: dup            
        //   382: iload_3        
        //   383: iload_3        
        //   384: invokespecial   com/jetbrains/cidr/lang/psi/OCMacroCall$ParameterCheckResult.<init>:(II)V
        //   387: dup            
        //   388: ifnonnull       425
        //   391: new             Ljava/lang/IllegalStateException;
        //   394: dup            
        //   395: ldc             "@NotNull method %s.%s must not return null"
        //   397: ldc             2
        //   399: anewarray       Ljava/lang/Object;
        //   402: dup            
        //   403: ldc             0
        //   405: ldc             "com/jetbrains/cidr/lang/psi/impl/OCMacroCallImpl"
        //   407: aastore        
        //   408: dup            
        //   409: ldc             1
        //   411: ldc             "checkParameters"
        //   413: aastore        
        //   414: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   417: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   420: athrow         
        //   421: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCMacroCallImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   424: athrow         
        //   425: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  56     67     70     74     Ljava/lang/IllegalArgumentException;
        //  63     86     86     90     Ljava/lang/IllegalArgumentException;
        //  99     133    133    137    Ljava/lang/IllegalArgumentException;
        //  144    158    161    165    Ljava/lang/IllegalArgumentException;
        //  154    170    173    177    Ljava/lang/IllegalArgumentException;
        //  165    193    196    200    Ljava/lang/IllegalArgumentException;
        //  177    213    216    220    Ljava/lang/IllegalArgumentException;
        //  200    250    250    254    Ljava/lang/IllegalArgumentException;
        //  261    278    278    282    Ljava/lang/IllegalArgumentException;
        //  291    303    306    310    Ljava/lang/IllegalArgumentException;
        //  297    315    318    322    Ljava/lang/IllegalArgumentException;
        //  310    336    339    343    Ljava/lang/IllegalArgumentException;
        //  322    373    373    377    Ljava/lang/IllegalArgumentException;
        //  378    421    421    425    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0165:
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
    public String getReplacementText() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCMacroCallImpl.a:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //     4: astore_1       
        //     5: aload_1        
        //     6: instanceof      Lcom/intellij/psi/PsiComment;
        //     9: ifeq            20
        //    12: aload_1        
        //    13: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCMacroCallImpl.a:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //    16: astore_1       
        //    17: goto            5
        //    20: new             Ljava/lang/StringBuilder;
        //    23: dup            
        //    24: invokespecial   java/lang/StringBuilder.<init>:()V
        //    27: astore_2       
        //    28: iconst_0       
        //    29: istore_3       
        //    30: aload_1        
        //    31: instanceof      Lcom/intellij/psi/impl/source/tree/ForeignLeafPsiElement;
        //    34: ifeq            179
        //    37: aload_1        
        //    38: iconst_1       
        //    39: anewarray       Ljava/lang/Class;
        //    42: dup            
        //    43: iconst_0       
        //    44: ldc             Lcom/jetbrains/cidr/lang/psi/OCMacroCall;.class
        //    46: aastore        
        //    47: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //    50: ifnonnull       171
        //    53: goto            60
        //    56: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCMacroCallImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    59: athrow         
        //    60: aload_1        
        //    61: checkcast       Lcom/intellij/psi/impl/source/tree/ForeignLeafPsiElement;
        //    64: invokevirtual   com/intellij/psi/impl/source/tree/ForeignLeafPsiElement.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //    67: astore          4
        //    69: aload           4
        //    71: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.AT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    74: if_acmpeq       158
        //    77: aload           4
        //    79: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IDENTIFIER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    82: if_acmpeq       128
        //    85: goto            92
        //    88: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCMacroCallImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    91: athrow         
        //    92: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.KEYWORDS:Lcom/intellij/psi/tree/TokenSet;
        //    95: aload           4
        //    97: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   100: ifne            128
        //   103: goto            110
        //   106: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCMacroCallImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   109: athrow         
        //   110: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LITERALS:Lcom/intellij/psi/tree/TokenSet;
        //   113: aload           4
        //   115: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   118: ifeq            158
        //   121: goto            128
        //   124: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCMacroCallImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   127: athrow         
        //   128: iload_3        
        //   129: ifeq            153
        //   132: goto            139
        //   135: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCMacroCallImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   138: athrow         
        //   139: aload_2        
        //   140: bipush          32
        //   142: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   145: pop            
        //   146: goto            153
        //   149: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCMacroCallImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   152: athrow         
        //   153: iconst_1       
        //   154: istore_3       
        //   155: goto            160
        //   158: iconst_0       
        //   159: istore_3       
        //   160: aload_2        
        //   161: aload_1        
        //   162: invokeinterface com/intellij/psi/PsiElement.getText:()Ljava/lang/String;
        //   167: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   170: pop            
        //   171: aload_1        
        //   172: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCMacroCallImpl.a:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   175: astore_1       
        //   176: goto            30
        //   179: aload_2        
        //   180: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   183: dup            
        //   184: ifnonnull       221
        //   187: new             Ljava/lang/IllegalStateException;
        //   190: dup            
        //   191: ldc             "@NotNull method %s.%s must not return null"
        //   193: ldc             2
        //   195: anewarray       Ljava/lang/Object;
        //   198: dup            
        //   199: ldc             0
        //   201: ldc             "com/jetbrains/cidr/lang/psi/impl/OCMacroCallImpl"
        //   203: aastore        
        //   204: dup            
        //   205: ldc             1
        //   207: ldc             "getReplacementText"
        //   209: aastore        
        //   210: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   213: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   216: athrow         
        //   217: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCMacroCallImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   220: athrow         
        //   221: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  30     53     56     60     Ljava/lang/IllegalArgumentException;
        //  69     85     88     92     Ljava/lang/IllegalArgumentException;
        //  77     103    106    110    Ljava/lang/IllegalArgumentException;
        //  92     121    124    128    Ljava/lang/IllegalArgumentException;
        //  110    132    135    139    Ljava/lang/IllegalArgumentException;
        //  128    146    149    153    Ljava/lang/IllegalArgumentException;
        //  179    217    217    221    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0092:
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
    public boolean processExpansionDependencies(@NotNull final Processor<PsiElement> processor) {
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/psi/impl/OCMacroCallImpl", "processExpansionDependencies"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.processExpansionDependenciesWithIdent(null, processor);
    }
    
    @Override
    public boolean processExpansionDependenciesWithIdent(@Nullable final String p0, @NotNull final Processor<PsiElement> p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "processor"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/psi/impl/OCMacroCallImpl"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "processExpansionDependenciesWithIdent"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCMacroCallImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCMacroCallImpl.a:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //    48: astore_3       
        //    49: aload_3        
        //    50: instanceof      Lcom/intellij/psi/PsiComment;
        //    53: ifeq            64
        //    56: aload_3        
        //    57: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCMacroCallImpl.a:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //    60: astore_3       
        //    61: goto            49
        //    64: new             Lcom/intellij/util/containers/HashSet;
        //    67: dup            
        //    68: invokespecial   com/intellij/util/containers/HashSet.<init>:()V
        //    71: astore          4
        //    73: aload_0        
        //    74: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCMacroCallImpl.getMacroReferenceElement:()Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //    77: astore          5
        //    79: aload           5
        //    81: ifnonnull       90
        //    84: iconst_1       
        //    85: ireturn        
        //    86: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCMacroCallImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    89: athrow         
        //    90: aload           5
        //    92: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceElement.getName:()Ljava/lang/String;
        //    97: astore          6
        //    99: aload_3        
        //   100: instanceof      Lcom/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafElement;
        //   103: ifeq            259
        //   106: aload           6
        //   108: aload_3        
        //   109: checkcast       Lcom/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafElement;
        //   112: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafElement.getMacroName:()Ljava/lang/String;
        //   115: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   118: ifeq            259
        //   121: goto            128
        //   124: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCMacroCallImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   127: athrow         
        //   128: aload_3        
        //   129: iconst_1       
        //   130: anewarray       Ljava/lang/Class;
        //   133: dup            
        //   134: iconst_0       
        //   135: ldc             Lcom/jetbrains/cidr/lang/psi/OCMacroCall;.class
        //   137: aastore        
        //   138: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   141: ifnonnull       251
        //   144: goto            151
        //   147: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCMacroCallImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   150: athrow         
        //   151: aload_3        
        //   152: astore          7
        //   154: aload_1        
        //   155: ifnull          178
        //   158: aload_3        
        //   159: invokeinterface com/intellij/psi/PsiElement.getText:()Ljava/lang/String;
        //   164: aload_1        
        //   165: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   168: ifeq            251
        //   171: goto            178
        //   174: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCMacroCallImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   177: athrow         
        //   178: aload           7
        //   180: ifnull          251
        //   183: goto            190
        //   186: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCMacroCallImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   189: athrow         
        //   190: aload           4
        //   192: aload           7
        //   194: invokevirtual   com/intellij/util/containers/HashSet.contains:(Ljava/lang/Object;)Z
        //   197: ifne            251
        //   200: goto            207
        //   203: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCMacroCallImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   206: athrow         
        //   207: aload_2        
        //   208: aload           7
        //   210: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
        //   215: ifne            231
        //   218: goto            225
        //   221: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCMacroCallImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   224: athrow         
        //   225: iconst_0       
        //   226: ireturn        
        //   227: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCMacroCallImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   230: athrow         
        //   231: aload           4
        //   233: aload           7
        //   235: invokevirtual   com/intellij/util/containers/HashSet.add:(Ljava/lang/Object;)Z
        //   238: pop            
        //   239: aload           7
        //   241: invokeinterface com/intellij/psi/PsiElement.getContext:()Lcom/intellij/psi/PsiElement;
        //   246: astore          7
        //   248: goto            178
        //   251: aload_3        
        //   252: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCMacroCallImpl.a:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   255: astore_3       
        //   256: goto            99
        //   259: iconst_1       
        //   260: ireturn        
        //    Signature:
        //  (Ljava/lang/String;Lcom/intellij/util/Processor<Lcom/intellij/psi/PsiElement;>;)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  79     86     86     90     Ljava/lang/IllegalArgumentException;
        //  99     121    124    128    Ljava/lang/IllegalArgumentException;
        //  106    144    147    151    Ljava/lang/IllegalArgumentException;
        //  154    171    174    178    Ljava/lang/IllegalArgumentException;
        //  158    183    186    190    Ljava/lang/IllegalArgumentException;
        //  178    200    203    207    Ljava/lang/IllegalArgumentException;
        //  190    218    221    225    Ljava/lang/IllegalArgumentException;
        //  207    227    227    231    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0178:
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
    
    @Nullable
    private static PsiElement a(PsiElement psiElement) {
        while (true) {
            final PsiElement nextLeaf = PsiTreeUtil.nextLeaf(psiElement);
            Label_0034: {
                try {
                    if (nextLeaf == null) {
                        return nextLeaf;
                    }
                    final PsiElement psiElement2 = nextLeaf;
                    final ASTNode astNode = psiElement2.getNode();
                    final boolean b = astNode instanceof LeafElement;
                    if (b) {
                        return nextLeaf;
                    }
                    break Label_0034;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final PsiElement psiElement2 = nextLeaf;
                    final ASTNode astNode = psiElement2.getNode();
                    final boolean b = astNode instanceof LeafElement;
                    if (b) {
                        return nextLeaf;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            psiElement = nextLeaf;
        }
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCMacroCallImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ocVisitor.visitMacroCall(this);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
