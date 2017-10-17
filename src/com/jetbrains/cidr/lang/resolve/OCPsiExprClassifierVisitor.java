// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve;

import com.jetbrains.cidr.lang.psi.OCCppTypeidExpression;
import com.jetbrains.cidr.lang.psi.OCVariadicPackExpression;
import com.jetbrains.cidr.lang.psi.OCCppDeleteExpression;
import com.jetbrains.cidr.lang.psi.OCCppNewExpression;
import com.jetbrains.cidr.lang.psi.OCStatementExpression;
import com.jetbrains.cidr.lang.psi.OCLambdaExpression;
import com.jetbrains.cidr.lang.psi.OCBlockExpression;
import com.jetbrains.cidr.lang.psi.OCProtocolExpression;
import com.jetbrains.cidr.lang.psi.OCEncodeExpression;
import com.jetbrains.cidr.lang.psi.OCAvailabilityExpression;
import com.jetbrains.cidr.lang.psi.OCSelectorExpression;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.jetbrains.cidr.lang.psi.OCBoxedExpression;
import com.jetbrains.cidr.lang.psi.OCLiteralExpression;
import com.jetbrains.cidr.lang.psi.OCParenthesizedExpression;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.parser.OCPunctuatorElementType;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.intellij.psi.PsiElementVisitor;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.jetbrains.cidr.lang.psi.OCCallExpression;
import com.jetbrains.cidr.lang.psi.OCArraySelectionExpression;
import com.jetbrains.cidr.lang.psi.OCPostfixExpression;
import com.jetbrains.cidr.lang.psi.OCSizeofExpression;
import com.jetbrains.cidr.lang.psi.OCCompoundInitializer;
import com.jetbrains.cidr.lang.psi.OCCastExpression;
import com.jetbrains.cidr.lang.psi.OCPrefixExpression;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.jetbrains.cidr.lang.psi.OCUnaryExpression;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.psi.OCBinaryExpression;
import com.jetbrains.cidr.lang.psi.OCConditionalExpression;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.types.OCCppReferenceType;
import com.jetbrains.cidr.lang.psi.OCAssignmentExpression;
import com.jetbrains.cidr.lang.types.OCTypeOwner;
import com.jetbrains.cidr.lang.psi.OCCommaExpression;
import com.jetbrains.cidr.lang.psi.OCThrowExpression;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.OCLog;
import java.util.List;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.resolve.references.OCOperatorReference;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiReference;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;

public class OCPsiExprClassifierVisitor extends OCVisitor
{
    private OCExprValueCategory myResult;
    private final OCResolveContext myContext;
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public OCPsiExprClassifierVisitor(final OCResolveContext myContext) {
        this.myContext = myContext;
    }
    
    @NotNull
    public OCExprValueCategory getResult() {
        OCExprValueCategory myResult = null;
        Label_0032: {
            Label_0020: {
                try {
                    if (OCPsiExprClassifierVisitor.$assertionsDisabled) {
                        break Label_0032;
                    }
                    final OCPsiExprClassifierVisitor ocPsiExprClassifierVisitor = this;
                    final OCExprValueCategory ocExprValueCategory = ocPsiExprClassifierVisitor.myResult;
                    if (ocExprValueCategory == null) {
                        break Label_0020;
                    }
                    break Label_0032;
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                try {
                    final OCPsiExprClassifierVisitor ocPsiExprClassifierVisitor = this;
                    final OCExprValueCategory ocExprValueCategory = ocPsiExprClassifierVisitor.myResult;
                    if (ocExprValueCategory == null) {
                        throw new AssertionError();
                    }
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
            }
            try {
                myResult = this.myResult;
                if (myResult == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor", "getResult"));
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
        }
        return myResult;
    }
    
    private void a(@NotNull final OCExprValueCategory myResult) {
        try {
            if (myResult == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor", "setResult"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        Label_0064: {
            try {
                if (OCPsiExprClassifierVisitor.$assertionsDisabled) {
                    break Label_0064;
                }
                final OCPsiExprClassifierVisitor ocPsiExprClassifierVisitor = this;
                final OCExprValueCategory ocExprValueCategory = ocPsiExprClassifierVisitor.myResult;
                if (ocExprValueCategory != null) {
                    break Label_0064;
                }
                break Label_0064;
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            try {
                final OCPsiExprClassifierVisitor ocPsiExprClassifierVisitor = this;
                final OCExprValueCategory ocExprValueCategory = ocPsiExprClassifierVisitor.myResult;
                if (ocExprValueCategory != null) {
                    throw new AssertionError();
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
        }
        this.myResult = myResult;
    }
    
    @Nullable
    private static OCFunctionSymbol a(@Nullable final PsiReference psiReference) {
        try {
            if (!(psiReference instanceof OCOperatorReference)) {
                return null;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final OCSymbol ocSymbol = (OCSymbol)ContainerUtil.getFirstItem((List)((OCOperatorReference)psiReference).resolveToSymbols());
        try {
            if (ocSymbol == null) {
                return null;
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        try {
            if (!(ocSymbol instanceof OCFunctionSymbol)) {
                OCLog.LOG.warn("Operator is not function symbol: " + ocSymbol);
                return null;
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        return (OCFunctionSymbol)ocSymbol;
    }
    
    public void visitElement(final PsiElement psiElement) {
        OCLog.LOG.error(String.format("Not an expression: %s(`%s`)", psiElement.toString(), psiElement.getText()));
        this.a(OCExprValueCategory.PRValue);
    }
    
    @Override
    public void visitExpression(final OCExpression ocExpression) {
        OCLog.LOG.error(String.format("Expression not handled: %s(`%s`)", ocExpression.toString(), ocExpression.getTextWithMacros()));
        this.a(OCExprValueCategory.PRValue);
    }
    
    @Override
    public void visitThrowExpression(final OCThrowExpression ocThrowExpression) {
        this.a(OCExprValueCategory.PRValue);
    }
    
    @Override
    public void visitCommaExpression(final OCCommaExpression ocCommaExpression) {
        OCExprValueCategory ocExprValueCategory = null;
        Label_0033: {
            try {
                if (OCExprValueCategory.classify(ocCommaExpression.getTailExpression(), this.myContext) == OCExprValueCategory.LValue) {
                    ocExprValueCategory = OCExprValueCategory.LValue;
                    break Label_0033;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            ocExprValueCategory = OCExprValueCategory.PRValue;
        }
        this.a(ocExprValueCategory);
    }
    
    @Override
    public void visitAssignmentExpression(final OCAssignmentExpression ocAssignmentExpression) {
        final OCFunctionSymbol a = a(ocAssignmentExpression.getReference());
        OCExprValueCategory ocExprValueCategory = null;
        Label_0030: {
            try {
                if (a == null) {
                    ocExprValueCategory = OCExprValueCategory.LValue;
                    break Label_0030;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            ocExprValueCategory = this.a(a);
        }
        this.a(ocExprValueCategory);
    }
    
    @NotNull
    private OCExprValueCategory a(@NotNull final OCFunctionSymbol ocFunctionSymbol) {
        try {
            if (ocFunctionSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor", "byReturnType"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final OCType resolve = ocFunctionSymbol.getType().getReturnType().resolve(this.myContext);
        OCExprValueCategory prValue = null;
        Label_0135: {
            OCExprValueCategory ocExprValueCategory = null;
            Label_0096: {
                Label_0083: {
                    try {
                        if (!(resolve instanceof OCCppReferenceType)) {
                            break Label_0135;
                        }
                        final OCType ocType = resolve;
                        final OCCppReferenceType ocCppReferenceType = (OCCppReferenceType)ocType;
                        final boolean b = ocCppReferenceType.isRvalueRef();
                        if (b) {
                            break Label_0083;
                        }
                        break Label_0083;
                    }
                    catch (IllegalStateException ex2) {
                        throw a(ex2);
                    }
                    try {
                        final OCType ocType = resolve;
                        final OCCppReferenceType ocCppReferenceType = (OCCppReferenceType)ocType;
                        final boolean b = ocCppReferenceType.isRvalueRef();
                        if (b) {
                            final OCExprValueCategory ocExprValueCategory2;
                            ocExprValueCategory = (ocExprValueCategory2 = OCExprValueCategory.XValue);
                            break Label_0096;
                        }
                    }
                    catch (IllegalStateException ex3) {
                        throw a(ex3);
                    }
                }
                OCExprValueCategory ocExprValueCategory2;
                ocExprValueCategory = (ocExprValueCategory2 = OCExprValueCategory.LValue);
                try {
                    if (ocExprValueCategory2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor", "byReturnType"));
                    }
                }
                catch (IllegalStateException ex4) {
                    throw a(ex4);
                }
            }
            return ocExprValueCategory;
            try {
                prValue = OCExprValueCategory.PRValue;
                if (prValue == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor", "byReturnType"));
                }
            }
            catch (IllegalStateException ex5) {
                throw a(ex5);
            }
        }
        return prValue;
    }
    
    @Override
    public void visitConditionalExpression(final OCConditionalExpression p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //     4: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.isCpp:()Z
        //     7: ifne            22
        //    10: aload_0        
        //    11: getstatic       com/jetbrains/cidr/lang/resolve/OCExprValueCategory.PRValue:Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //    14: invokespecial   com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor.a:(Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;)V
        //    17: return         
        //    18: invokestatic    com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    21: athrow         
        //    22: aload_1        
        //    23: iconst_1       
        //    24: invokeinterface com/jetbrains/cidr/lang/psi/OCConditionalExpression.getPositiveExpression:(Z)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    29: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.diveIntoParentheses:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    32: astore_2       
        //    33: aload_1        
        //    34: invokeinterface com/jetbrains/cidr/lang/psi/OCConditionalExpression.getNegativeExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    39: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.diveIntoParentheses:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    42: astore_3       
        //    43: aload_2        
        //    44: ifnull          58
        //    47: aload_3        
        //    48: ifnonnull       94
        //    51: goto            58
        //    54: invokestatic    com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    57: athrow         
        //    58: getstatic       com/jetbrains/cidr/lang/OCLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //    61: ldc             "conditional expr has null sub-expressions `%s`"
        //    63: iconst_1       
        //    64: anewarray       Ljava/lang/Object;
        //    67: dup            
        //    68: iconst_0       
        //    69: aload_1        
        //    70: invokeinterface com/jetbrains/cidr/lang/psi/OCConditionalExpression.getTextWithMacros:()Ljava/lang/String;
        //    75: aastore        
        //    76: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    79: invokevirtual   com/intellij/openapi/diagnostic/Logger.error:(Ljava/lang/String;)V
        //    82: aload_0        
        //    83: getstatic       com/jetbrains/cidr/lang/resolve/OCExprValueCategory.PRValue:Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //    86: invokespecial   com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor.a:(Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;)V
        //    89: return         
        //    90: invokestatic    com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    93: athrow         
        //    94: aload_2        
        //    95: instanceof      Lcom/jetbrains/cidr/lang/psi/OCThrowExpression;
        //    98: istore          4
        //   100: aload_3        
        //   101: instanceof      Lcom/jetbrains/cidr/lang/psi/OCThrowExpression;
        //   104: istore          5
        //   106: iload           4
        //   108: ifeq            137
        //   111: iload           5
        //   113: ifeq            137
        //   116: goto            123
        //   119: invokestatic    com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   122: athrow         
        //   123: aload_0        
        //   124: getstatic       com/jetbrains/cidr/lang/resolve/OCExprValueCategory.PRValue:Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //   127: invokespecial   com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor.a:(Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;)V
        //   130: goto            274
        //   133: invokestatic    com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   136: athrow         
        //   137: iload           4
        //   139: ifeq            156
        //   142: aload_3        
        //   143: aload_0        
        //   144: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.accept:(Lcom/intellij/psi/PsiElementVisitor;)V
        //   149: goto            274
        //   152: invokestatic    com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   155: athrow         
        //   156: iload           5
        //   158: ifeq            175
        //   161: aload_2        
        //   162: aload_0        
        //   163: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.accept:(Lcom/intellij/psi/PsiElementVisitor;)V
        //   168: goto            274
        //   171: invokestatic    com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   174: athrow         
        //   175: aload_2        
        //   176: aload_0        
        //   177: getfield        com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   180: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprValueCategory.classify:(Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //   183: astore          6
        //   185: aload_3        
        //   186: aload_0        
        //   187: getfield        com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   190: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprValueCategory.classify:(Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //   193: astore          7
        //   195: aload           6
        //   197: aload           7
        //   199: if_acmpne       267
        //   202: aload           6
        //   204: getstatic       com/jetbrains/cidr/lang/resolve/OCExprValueCategory.PRValue:Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //   207: if_acmpeq       267
        //   210: goto            217
        //   213: invokestatic    com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   216: athrow         
        //   217: aload_2        
        //   218: aload_0        
        //   219: getfield        com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   222: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getType:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   227: aload_3        
        //   228: aload_0        
        //   229: getfield        com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   232: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getType:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   237: aload_0        
        //   238: getfield        com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   241: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equalsAfterResolving:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   244: ifeq            267
        //   247: goto            254
        //   250: invokestatic    com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   253: athrow         
        //   254: aload_0        
        //   255: aload           6
        //   257: invokespecial   com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor.a:(Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;)V
        //   260: goto            274
        //   263: invokestatic    com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   266: athrow         
        //   267: aload_0        
        //   268: getstatic       com/jetbrains/cidr/lang/resolve/OCExprValueCategory.PRValue:Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //   271: invokespecial   com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor.a:(Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;)V
        //   274: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      18     18     22     Ljava/lang/IllegalStateException;
        //  43     51     54     58     Ljava/lang/IllegalStateException;
        //  47     90     90     94     Ljava/lang/IllegalStateException;
        //  106    116    119    123    Ljava/lang/IllegalStateException;
        //  111    133    133    137    Ljava/lang/IllegalStateException;
        //  137    152    152    156    Ljava/lang/IllegalStateException;
        //  156    171    171    175    Ljava/lang/IllegalStateException;
        //  195    210    213    217    Ljava/lang/IllegalStateException;
        //  202    247    250    254    Ljava/lang/IllegalStateException;
        //  217    263    263    267    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0217:
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
    public void visitBinaryExpression(final OCBinaryExpression ocBinaryExpression) {
        final OCFunctionSymbol a = a(ocBinaryExpression.getReference());
        OCExprValueCategory ocExprValueCategory = null;
        Label_0032: {
            try {
                if (a == null) {
                    ocExprValueCategory = this.a(ocBinaryExpression);
                    break Label_0032;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            ocExprValueCategory = this.a(a);
        }
        this.a(ocExprValueCategory);
    }
    
    @NotNull
    private OCExprValueCategory a(final OCBinaryExpression p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokeinterface com/jetbrains/cidr/lang/psi/OCBinaryExpression.getOperationSign:()Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //     6: astore_2       
        //     7: aload_2        
        //     8: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DOT_MUL:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    11: if_acmpne       125
        //    14: aload_1        
        //    15: invokeinterface com/jetbrains/cidr/lang/psi/OCBinaryExpression.getLeft:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    20: ifnull          125
        //    23: goto            30
        //    26: invokestatic    com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    29: athrow         
        //    30: aload_1        
        //    31: invokeinterface com/jetbrains/cidr/lang/psi/OCBinaryExpression.getRight:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    36: ifnull          66
        //    39: goto            46
        //    42: invokestatic    com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    45: athrow         
        //    46: aload_0        
        //    47: aload_1        
        //    48: invokeinterface com/jetbrains/cidr/lang/psi/OCBinaryExpression.getRight:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    53: invokespecial   com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor.a:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Z
        //    56: ifeq            125
        //    59: goto            66
        //    62: invokestatic    com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    65: athrow         
        //    66: aload_1        
        //    67: invokeinterface com/jetbrains/cidr/lang/psi/OCBinaryExpression.getLeft:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    72: aload_0        
        //    73: getfield        com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    76: invokestatic    com/jetbrains/cidr/lang/resolve/OCExprValueCategory.classify:(Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //    79: dup            
        //    80: ifnonnull       124
        //    83: goto            90
        //    86: invokestatic    com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    89: athrow         
        //    90: new             Ljava/lang/IllegalStateException;
        //    93: dup            
        //    94: ldc             "@NotNull method %s.%s must not return null"
        //    96: ldc             2
        //    98: anewarray       Ljava/lang/Object;
        //   101: dup            
        //   102: ldc             0
        //   104: ldc             "com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor"
        //   106: aastore        
        //   107: dup            
        //   108: ldc             1
        //   110: ldc             "byBinaryOperator"
        //   112: aastore        
        //   113: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   116: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   119: athrow         
        //   120: invokestatic    com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   123: athrow         
        //   124: areturn        
        //   125: aload_2        
        //   126: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DEREF_MUL:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   129: if_acmpne       217
        //   132: aload_1        
        //   133: invokeinterface com/jetbrains/cidr/lang/psi/OCBinaryExpression.getRight:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   138: ifnull          168
        //   141: goto            148
        //   144: invokestatic    com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   147: athrow         
        //   148: aload_0        
        //   149: aload_1        
        //   150: invokeinterface com/jetbrains/cidr/lang/psi/OCBinaryExpression.getRight:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   155: invokespecial   com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor.a:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Z
        //   158: ifeq            217
        //   161: goto            168
        //   164: invokestatic    com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   167: athrow         
        //   168: getstatic       com/jetbrains/cidr/lang/resolve/OCExprValueCategory.LValue:Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //   171: dup            
        //   172: ifnonnull       216
        //   175: goto            182
        //   178: invokestatic    com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   181: athrow         
        //   182: new             Ljava/lang/IllegalStateException;
        //   185: dup            
        //   186: ldc             "@NotNull method %s.%s must not return null"
        //   188: ldc             2
        //   190: anewarray       Ljava/lang/Object;
        //   193: dup            
        //   194: ldc             0
        //   196: ldc             "com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor"
        //   198: aastore        
        //   199: dup            
        //   200: ldc             1
        //   202: ldc             "byBinaryOperator"
        //   204: aastore        
        //   205: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   208: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   211: athrow         
        //   212: invokestatic    com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   215: athrow         
        //   216: areturn        
        //   217: getstatic       com/jetbrains/cidr/lang/resolve/OCExprValueCategory.PRValue:Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //   220: dup            
        //   221: ifnonnull       258
        //   224: new             Ljava/lang/IllegalStateException;
        //   227: dup            
        //   228: ldc             "@NotNull method %s.%s must not return null"
        //   230: ldc             2
        //   232: anewarray       Ljava/lang/Object;
        //   235: dup            
        //   236: ldc             0
        //   238: ldc             "com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor"
        //   240: aastore        
        //   241: dup            
        //   242: ldc             1
        //   244: ldc             "byBinaryOperator"
        //   246: aastore        
        //   247: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   250: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   253: athrow         
        //   254: invokestatic    com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   257: athrow         
        //   258: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  7      23     26     30     Ljava/lang/IllegalStateException;
        //  14     39     42     46     Ljava/lang/IllegalStateException;
        //  30     59     62     66     Ljava/lang/IllegalStateException;
        //  46     83     86     90     Ljava/lang/IllegalStateException;
        //  66     120    120    124    Ljava/lang/IllegalStateException;
        //  125    141    144    148    Ljava/lang/IllegalStateException;
        //  132    161    164    168    Ljava/lang/IllegalStateException;
        //  148    175    178    182    Ljava/lang/IllegalStateException;
        //  168    212    212    216    Ljava/lang/IllegalStateException;
        //  217    254    254    258    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0030:
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
    
    private boolean a(@NotNull final OCExpression ocExpression) {
        try {
            if (ocExpression == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "right", "com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor", "isPointerToDataMember"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final OCType resolvedType = ocExpression.getResolvedType(this.myContext);
        try {
            if (!(resolvedType instanceof OCPointerType)) {
                return true;
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        try {
            if (!(((OCPointerType)resolvedType).getRefType() instanceof OCFunctionType)) {
                return true;
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        return false;
    }
    
    @Override
    public void visitUnaryExpression(final OCUnaryExpression ocUnaryExpression) {
        final OCFunctionSymbol a = a(ocUnaryExpression.getReference());
        OCExprValueCategory ocExprValueCategory = null;
        Label_0037: {
            try {
                if (a == null) {
                    ocExprValueCategory = a(ocUnaryExpression.getOperationSign(), true);
                    break Label_0037;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            ocExprValueCategory = this.a(a);
        }
        this.a(ocExprValueCategory);
    }
    
    @NotNull
    private static OCExprValueCategory a(@NotNull final OCElementType p0, final boolean p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "sign"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "byBuiltInUnaryOperationSign"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    43: athrow         
        //    44: aload_0        
        //    45: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.MUL:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    48: if_acmpeq       90
        //    51: iload_1        
        //    52: ifeq            139
        //    55: goto            62
        //    58: invokestatic    com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    61: athrow         
        //    62: aload_0        
        //    63: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.PLUSPLUS:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    66: if_acmpeq       90
        //    69: goto            76
        //    72: invokestatic    com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    75: athrow         
        //    76: aload_0        
        //    77: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.MINUSMINUS:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    80: if_acmpne       139
        //    83: goto            90
        //    86: invokestatic    com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    89: athrow         
        //    90: getstatic       com/jetbrains/cidr/lang/resolve/OCExprValueCategory.LValue:Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //    93: dup            
        //    94: ifnonnull       138
        //    97: goto            104
        //   100: invokestatic    com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   103: athrow         
        //   104: new             Ljava/lang/IllegalStateException;
        //   107: dup            
        //   108: ldc             "@NotNull method %s.%s must not return null"
        //   110: ldc             2
        //   112: anewarray       Ljava/lang/Object;
        //   115: dup            
        //   116: ldc             0
        //   118: ldc             "com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor"
        //   120: aastore        
        //   121: dup            
        //   122: ldc             1
        //   124: ldc             "byBuiltInUnaryOperationSign"
        //   126: aastore        
        //   127: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   130: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   133: athrow         
        //   134: invokestatic    com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   137: athrow         
        //   138: areturn        
        //   139: getstatic       com/jetbrains/cidr/lang/resolve/OCExprValueCategory.PRValue:Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;
        //   142: dup            
        //   143: ifnonnull       180
        //   146: new             Ljava/lang/IllegalStateException;
        //   149: dup            
        //   150: ldc             "@NotNull method %s.%s must not return null"
        //   152: ldc             2
        //   154: anewarray       Ljava/lang/Object;
        //   157: dup            
        //   158: ldc             0
        //   160: ldc             "com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor"
        //   162: aastore        
        //   163: dup            
        //   164: ldc             1
        //   166: ldc             "byBuiltInUnaryOperationSign"
        //   168: aastore        
        //   169: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   172: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   175: athrow         
        //   176: invokestatic    com/jetbrains/cidr/lang/resolve/OCPsiExprClassifierVisitor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   179: athrow         
        //   180: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      40     40     44     Ljava/lang/IllegalStateException;
        //  44     55     58     62     Ljava/lang/IllegalStateException;
        //  51     69     72     76     Ljava/lang/IllegalStateException;
        //  62     83     86     90     Ljava/lang/IllegalStateException;
        //  76     97     100    104    Ljava/lang/IllegalStateException;
        //  90     134    134    138    Ljava/lang/IllegalStateException;
        //  139    176    176    180    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0062:
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
    public void visitPrefixExpression(final OCPrefixExpression ocPrefixExpression) {
        final OCFunctionSymbol a = a(ocPrefixExpression.getReference());
        OCExprValueCategory ocExprValueCategory = null;
        Label_0037: {
            try {
                if (a == null) {
                    ocExprValueCategory = a(ocPrefixExpression.getOperationSign(), true);
                    break Label_0037;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            ocExprValueCategory = this.a(a);
        }
        this.a(ocExprValueCategory);
    }
    
    @Override
    public void visitCastExpression(final OCCastExpression ocCastExpression) {
        Label_0029: {
            try {
                if (this.myContext.isCpp()) {
                    break Label_0029;
                }
                final OCCastExpression ocCastExpression2 = ocCastExpression;
                final OCExpression ocExpression = ocCastExpression2.getOperand();
                final boolean b = ocExpression instanceof OCCompoundInitializer;
                if (b) {
                    break Label_0029;
                }
                break Label_0029;
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            try {
                final OCCastExpression ocCastExpression2 = ocCastExpression;
                final OCExpression ocExpression = ocCastExpression2.getOperand();
                final boolean b = ocExpression instanceof OCCompoundInitializer;
                if (b) {
                    this.a(OCExprValueCategory.LValue);
                    return;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        final OCType resolve = ocCastExpression.getCastType().resolve(this.myContext);
        if (resolve instanceof OCCppReferenceType) {
            final OCCppReferenceType ocCppReferenceType = (OCCppReferenceType)resolve;
            try {
                if (ocCppReferenceType.isRvalueRef()) {
                    this.a(OCExprValueCategory.XValue);
                    return;
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
            this.a(OCExprValueCategory.LValue);
        }
        else {
            this.a(OCExprValueCategory.PRValue);
        }
    }
    
    @Override
    public void visitSizeofExpression(final OCSizeofExpression ocSizeofExpression) {
        this.a(OCExprValueCategory.PRValue);
    }
    
    @Override
    public void visitPostfixExpression(final OCPostfixExpression ocPostfixExpression) {
        final OCFunctionSymbol a = a(ocPostfixExpression.getReference());
        OCExprValueCategory ocExprValueCategory = null;
        Label_0037: {
            try {
                if (a == null) {
                    ocExprValueCategory = a(ocPostfixExpression.getOperationSign(), false);
                    break Label_0037;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            ocExprValueCategory = this.a(a);
        }
        this.a(ocExprValueCategory);
    }
    
    @Override
    public void visitArraySelectionExpression(final OCArraySelectionExpression ocArraySelectionExpression) {
        final OCExpression indexExpression = ocArraySelectionExpression.getIndexExpression();
        try {
            if (indexExpression == null) {
                this.a(OCExprValueCategory.LValue);
                return;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final OCFunctionSymbol a = a(ocArraySelectionExpression.getReference());
        try {
            if (a == null) {
                this.a(OCExprValueCategory.LValue);
                return;
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        this.a(this.a(a));
    }
    
    @Override
    public void visitCallExpression(final OCCallExpression ocCallExpression) {
        final OCExpression functionReferenceExpression = ocCallExpression.getFunctionReferenceExpression();
        OCSymbol ocSymbol = null;
        if (functionReferenceExpression instanceof OCReferenceExpression) {
            ocSymbol = ((OCReferenceExpression)functionReferenceExpression).resolveToSymbol(this.myContext);
        }
        else if (functionReferenceExpression instanceof OCQualifiedExpression) {
            ocSymbol = ((OCQualifiedExpression)functionReferenceExpression).resolveToSymbol(this.myContext, true, false, false);
        }
        try {
            if (ocSymbol instanceof OCFunctionSymbol) {
                this.a(this.a((OCFunctionSymbol)ocSymbol));
                return;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        this.a(OCExprValueCategory.PRValue);
    }
    
    @Override
    public void visitQualifiedExpression(final OCQualifiedExpression ocQualifiedExpression) {
        final OCSymbol resolveToSymbol = ocQualifiedExpression.resolveToSymbol();
        Label_0060: {
            Label_0033: {
                try {
                    if (resolveToSymbol instanceof OCSymbolWithQualifiedName) {
                        break Label_0060;
                    }
                    final OCQualifiedExpression ocQualifiedExpression2 = ocQualifiedExpression;
                    final OCPunctuatorElementType ocPunctuatorElementType = ocQualifiedExpression2.getQualifyingTokenKind();
                    final OCPunctuatorElementType ocPunctuatorElementType2 = OCTokenTypes.DEREF;
                    if (ocPunctuatorElementType == ocPunctuatorElementType2) {
                        break Label_0033;
                    }
                    break Label_0033;
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                try {
                    final OCQualifiedExpression ocQualifiedExpression2 = ocQualifiedExpression;
                    final OCPunctuatorElementType ocPunctuatorElementType = ocQualifiedExpression2.getQualifyingTokenKind();
                    final OCPunctuatorElementType ocPunctuatorElementType2 = OCTokenTypes.DEREF;
                    if (ocPunctuatorElementType == ocPunctuatorElementType2) {
                        this.a(OCExprValueCategory.LValue);
                        return;
                    }
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
            }
            ocQualifiedExpression.getQualifier().accept((PsiElementVisitor)this);
            return;
        }
        final OCSymbolWithQualifiedName ocSymbolWithQualifiedName = (OCSymbolWithQualifiedName)resolveToSymbol;
        try {
            if (ocSymbolWithQualifiedName.getType() instanceof OCCppReferenceType) {
                this.a(OCExprValueCategory.LValue);
                return;
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        try {
            if (ocSymbolWithQualifiedName.isStatic()) {
                this.a(OCExprValueCategory.LValue);
                return;
            }
        }
        catch (IllegalStateException ex4) {
            throw a(ex4);
        }
        Label_0164: {
            Label_0135: {
                try {
                    if (ocSymbolWithQualifiedName.getResolvedKind() != OCSymbolKind.STRUCT_FIELD) {
                        break Label_0164;
                    }
                    final OCQualifiedExpression ocQualifiedExpression3 = ocQualifiedExpression;
                    final OCPunctuatorElementType ocPunctuatorElementType3 = ocQualifiedExpression3.getQualifyingTokenKind();
                    final OCPunctuatorElementType ocPunctuatorElementType4 = OCTokenTypes.DEREF;
                    if (ocPunctuatorElementType3 == ocPunctuatorElementType4) {
                        break Label_0135;
                    }
                    break Label_0135;
                }
                catch (IllegalStateException ex5) {
                    throw a(ex5);
                }
                try {
                    final OCQualifiedExpression ocQualifiedExpression3 = ocQualifiedExpression;
                    final OCPunctuatorElementType ocPunctuatorElementType3 = ocQualifiedExpression3.getQualifyingTokenKind();
                    final OCPunctuatorElementType ocPunctuatorElementType4 = OCTokenTypes.DEREF;
                    if (ocPunctuatorElementType3 == ocPunctuatorElementType4) {
                        this.a(OCExprValueCategory.LValue);
                        return;
                    }
                }
                catch (IllegalStateException ex6) {
                    throw a(ex6);
                }
            }
            ocQualifiedExpression.getQualifier().accept((PsiElementVisitor)this);
            return;
        }
        this.a(OCExprValueCategory.PRValue);
    }
    
    @Override
    public void visitReferenceExpression(final OCReferenceExpression ocReferenceExpression) {
        OCExprValueCategory ocExprValueCategory = null;
        Label_0042: {
            Label_0029: {
                try {
                    if (ocReferenceExpression.isCppThis()) {
                        break Label_0029;
                    }
                    final OCReferenceExpression ocReferenceExpression2 = ocReferenceExpression;
                    final OCElementTypes.SelfSuperToken selfSuperToken = ocReferenceExpression2.getSelfSuperToken();
                    final OCElementTypes.SelfSuperToken selfSuperToken2 = OCElementTypes.SelfSuperToken.SUPER;
                    if (selfSuperToken == selfSuperToken2) {
                        break Label_0029;
                    }
                    break Label_0029;
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                try {
                    final OCReferenceExpression ocReferenceExpression2 = ocReferenceExpression;
                    final OCElementTypes.SelfSuperToken selfSuperToken = ocReferenceExpression2.getSelfSuperToken();
                    final OCElementTypes.SelfSuperToken selfSuperToken2 = OCElementTypes.SelfSuperToken.SUPER;
                    if (selfSuperToken == selfSuperToken2) {
                        ocExprValueCategory = OCExprValueCategory.PRValue;
                        break Label_0042;
                    }
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
            }
            ocExprValueCategory = OCExprValueCategory.LValue;
        }
        this.a(ocExprValueCategory);
    }
    
    @Override
    public void visitParenthesizedExpression(final OCParenthesizedExpression ocParenthesizedExpression) {
        final OCExpression operand = ocParenthesizedExpression.getOperand();
        try {
            if (operand != null) {
                operand.accept((PsiElementVisitor)this);
                return;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        this.a(OCExprValueCategory.PRValue);
    }
    
    @Override
    public void visitLiteralExpression(final OCLiteralExpression ocLiteralExpression) {
        Label_0025: {
            try {
                if (!ocLiteralExpression.isStringLiteral()) {
                    break Label_0025;
                }
                final OCLiteralExpression ocLiteralExpression2 = ocLiteralExpression;
                final boolean b = ocLiteralExpression2.isNSStringLiteral();
                if (!b) {
                    break Label_0025;
                }
                break Label_0025;
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            try {
                final OCLiteralExpression ocLiteralExpression2 = ocLiteralExpression;
                final boolean b = ocLiteralExpression2.isNSStringLiteral();
                if (!b) {
                    this.a(OCExprValueCategory.LValue);
                    return;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        this.a(OCExprValueCategory.PRValue);
    }
    
    @Override
    public void visitCompoundInitializer(final OCCompoundInitializer ocCompoundInitializer) {
        this.a(OCExprValueCategory.PRValue);
    }
    
    @Override
    public void visitBoxedExpression(final OCBoxedExpression ocBoxedExpression) {
        this.a(OCExprValueCategory.PRValue);
    }
    
    @Override
    public void visitSendMessageExpression(final OCSendMessageExpression ocSendMessageExpression) {
        this.a(OCExprValueCategory.PRValue);
    }
    
    @Override
    public void visitSelectorExpression(final OCSelectorExpression ocSelectorExpression) {
        this.a(OCExprValueCategory.PRValue);
    }
    
    @Override
    public void visitAvailabilityExpression(final OCAvailabilityExpression ocAvailabilityExpression) {
        this.a(OCExprValueCategory.PRValue);
    }
    
    @Override
    public void visitEncodeExpression(final OCEncodeExpression ocEncodeExpression) {
        this.a(OCExprValueCategory.LValue);
    }
    
    @Override
    public void visitProtocolExpression(final OCProtocolExpression ocProtocolExpression) {
        this.a(OCExprValueCategory.PRValue);
    }
    
    @Override
    public void visitBlockExpression(final OCBlockExpression ocBlockExpression) {
        this.a(OCExprValueCategory.PRValue);
    }
    
    @Override
    public void visitLambdaExpression(final OCLambdaExpression ocLambdaExpression) {
        this.a(OCExprValueCategory.PRValue);
    }
    
    @Override
    public void visitStatementExpression(final OCStatementExpression ocStatementExpression) {
        this.a(OCExprValueCategory.PRValue);
    }
    
    @Override
    public void visitCppNewExpression(final OCCppNewExpression ocCppNewExpression) {
        this.a(OCExprValueCategory.PRValue);
    }
    
    @Override
    public void visitCppDeleteExpression(final OCCppDeleteExpression ocCppDeleteExpression) {
        this.a(OCExprValueCategory.PRValue);
    }
    
    @Override
    public void visitVariadicPackExpression(final OCVariadicPackExpression ocVariadicPackExpression) {
        ocVariadicPackExpression.getOperand().accept((PsiElementVisitor)this);
    }
    
    @Override
    public void visitCppTypeIdExpression(final OCCppTypeidExpression ocCppTypeidExpression) {
        this.a(OCExprValueCategory.LValue);
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCPsiExprClassifierVisitor.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
