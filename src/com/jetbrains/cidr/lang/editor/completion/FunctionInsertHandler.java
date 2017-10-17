// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import com.jetbrains.cidr.lang.psi.OCCppUsingStatement;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.util.OCExpectedTypeUtil;
import com.jetbrains.cidr.lang.psi.OCUnaryExpression;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.types.OCType;
import java.util.List;
import com.intellij.codeInsight.AutoPopupController;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.types.OCVoidType;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.intellij.codeInsight.completion.InsertionContext;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.completion.InsertHandler;

public class FunctionInsertHandler implements InsertHandler<LookupElement>
{
    @NotNull
    private final OCFunctionSymbol mySymbol;
    @Nullable
    private PsiElement myContextExpression;
    
    FunctionInsertHandler(@NotNull final OCFunctionSymbol mySymbol, @Nullable final PsiElement myContextExpression) {
        if (mySymbol == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/editor/completion/FunctionInsertHandler", "<init>"));
        }
        this.mySymbol = mySymbol;
        this.myContextExpression = myContextExpression;
    }
    
    public static void changeQualifyingTokenIfNeeded(final InsertionContext p0, final PsiElement p1, final LookupElement p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //     4: ifeq            184
        //     7: ldc             "cidr.completion.qualified"
        //     9: invokestatic    com/intellij/internal/statistic/UsageTrigger.trigger:(Ljava/lang/String;)V
        //    12: aload_1        
        //    13: checkcast       Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //    16: astore_3       
        //    17: aload_3        
        //    18: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.getQualifyingTokenKind:()Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    23: astore          4
        //    25: getstatic       com/jetbrains/cidr/lang/psi/OCQualifiedExpression.COMPLETION_QUALIFYING_TOKEN_KEY:Lcom/intellij/openapi/util/Key;
        //    28: aload_2        
        //    29: invokevirtual   com/intellij/openapi/util/Key.get:(Lcom/intellij/openapi/util/UserDataHolder;)Ljava/lang/Object;
        //    32: checkcast       Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    35: astore          5
        //    37: aload_3        
        //    38: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.canChangeQualifyingToken:()Z
        //    43: ifeq            72
        //    46: aload           5
        //    48: ifnull          72
        //    51: goto            58
        //    54: invokestatic    com/jetbrains/cidr/lang/editor/completion/FunctionInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    57: athrow         
        //    58: aload           5
        //    60: aload           4
        //    62: if_acmpne       77
        //    65: goto            72
        //    68: invokestatic    com/jetbrains/cidr/lang/editor/completion/FunctionInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    71: athrow         
        //    72: return         
        //    73: invokestatic    com/jetbrains/cidr/lang/editor/completion/FunctionInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    76: athrow         
        //    77: aload_0        
        //    78: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getDocument:()Lcom/intellij/openapi/editor/Document;
        //    81: astore          6
        //    83: aload_3        
        //    84: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.getQualifyingElement:()Lcom/jetbrains/cidr/lang/psi/OCElement;
        //    89: astore          7
        //    91: aload           7
        //    93: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getUserVisibleRangeInDocument:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/openapi/util/TextRange;
        //    96: astore          8
        //    98: aload           8
        //   100: ifnull          184
        //   103: new             Ljava/lang/StringBuilder;
        //   106: dup            
        //   107: invokespecial   java/lang/StringBuilder.<init>:()V
        //   110: ldc             "cidr.completion.qualified.changeQualifyingToken."
        //   112: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   115: aload           4
        //   117: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DOT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   120: if_acmpne       139
        //   123: goto            130
        //   126: invokestatic    com/jetbrains/cidr/lang/editor/completion/FunctionInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   129: athrow         
        //   130: ldc             "fromDot"
        //   132: goto            141
        //   135: invokestatic    com/jetbrains/cidr/lang/editor/completion/FunctionInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   138: athrow         
        //   139: ldc             "fromDereference"
        //   141: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   144: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   147: invokestatic    com/intellij/internal/statistic/UsageTrigger.trigger:(Ljava/lang/String;)V
        //   150: aload           6
        //   152: aload           8
        //   154: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   157: aload           8
        //   159: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   162: aload           5
        //   164: invokevirtual   com/jetbrains/cidr/lang/parser/OCPunctuatorElementType.getName:()Ljava/lang/String;
        //   167: invokeinterface com/intellij/openapi/editor/Document.replaceString:(IILjava/lang/CharSequence;)V
        //   172: aload_0        
        //   173: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getProject:()Lcom/intellij/openapi/project/Project;
        //   176: invokestatic    com/intellij/psi/PsiDocumentManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/PsiDocumentManager;
        //   179: aload           6
        //   181: invokevirtual   com/intellij/psi/PsiDocumentManager.commitDocument:(Lcom/intellij/openapi/editor/Document;)V
        //   184: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  37     51     54     58     Ljava/lang/IllegalArgumentException;
        //  46     65     68     72     Ljava/lang/IllegalArgumentException;
        //  58     73     73     77     Ljava/lang/IllegalArgumentException;
        //  98     123    126    130    Ljava/lang/IllegalArgumentException;
        //  103    135    135    139    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0058:
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
    
    public void handleInsert(final InsertionContext insertionContext, final LookupElement lookupElement) {
        try {
            if (a(this.mySymbol, this.myContextExpression)) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final List<OCDeclaratorSymbol> parameterSymbols = this.mySymbol.getParameterSymbols();
        boolean b = false;
        Label_0045: {
            try {
                if (!parameterSymbols.isEmpty()) {
                    b = true;
                    break Label_0045;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            b = false;
        }
        final boolean b2 = b;
        Label_0429: {
            try {
                CallableInsertUtils.addParensIfRequired(insertionContext, lookupElement, b2);
                if (!b2 || !CallableInsertUtils.shouldInsertPlaceholders(insertionContext)) {
                    break Label_0429;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            final StringBuilder sb = new StringBuilder();
            final int size = parameterSymbols.size();
            boolean b4 = false;
            Label_0135: {
                Label_0126: {
                    try {
                        if (!this.mySymbol.isVararg()) {
                            break Label_0126;
                        }
                        final List<OCDeclaratorSymbol> list = parameterSymbols;
                        final int n = size;
                        final int n2 = 1;
                        final int n3 = n - n2;
                        final OCDeclaratorSymbol ocDeclaratorSymbol = list.get(n3);
                        final OCDeclaratorSymbol ocDeclaratorSymbol2 = ocDeclaratorSymbol;
                        final boolean b3 = ocDeclaratorSymbol2.isUnnamed();
                        if (b3) {
                            break Label_0126;
                        }
                        break Label_0126;
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                    try {
                        final List<OCDeclaratorSymbol> list = parameterSymbols;
                        final int n = size;
                        final int n2 = 1;
                        final int n3 = n - n2;
                        final OCDeclaratorSymbol ocDeclaratorSymbol = list.get(n3);
                        final OCDeclaratorSymbol ocDeclaratorSymbol2 = ocDeclaratorSymbol;
                        final boolean b3 = ocDeclaratorSymbol2.isUnnamed();
                        if (b3) {
                            b4 = true;
                            break Label_0135;
                        }
                    }
                    catch (IllegalArgumentException ex5) {
                        throw a(ex5);
                    }
                }
                b4 = false;
            }
            final boolean b5 = b4;
            int n4 = 0;
            Label_0155: {
                try {
                    if (b5) {
                        n4 = size - 1;
                        break Label_0155;
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
                n4 = Integer.MAX_VALUE;
            }
            final int n5 = n4;
            int length = -1;
            int length2 = -1;
            for (int i = 0; i < size; ++i) {
                final OCDeclaratorSymbol ocDeclaratorSymbol3 = parameterSymbols.get(i);
                final OCType type = ocDeclaratorSymbol3.getType();
                try {
                    if (type instanceof OCVoidType) {
                        continue;
                    }
                }
                catch (IllegalArgumentException ex7) {
                    throw a(ex7);
                }
                try {
                    if (i > 0) {
                        sb.append(", ");
                    }
                }
                catch (IllegalArgumentException ex8) {
                    throw a(ex8);
                }
                Label_0354: {
                    Label_0275: {
                        Label_0267: {
                            Label_0247: {
                                try {
                                    if (i == 0) {
                                        break Label_0247;
                                    }
                                    final int n6 = i;
                                    final int n7 = n5;
                                    if (n6 < n7) {
                                        break Label_0247;
                                    }
                                    break Label_0275;
                                }
                                catch (IllegalArgumentException ex9) {
                                    throw a(ex9);
                                }
                                try {
                                    final int n6 = i;
                                    final int n7 = n5;
                                    if (n6 >= n7) {
                                        break Label_0275;
                                    }
                                    if (length != -1) {
                                        break Label_0267;
                                    }
                                }
                                catch (IllegalArgumentException ex10) {
                                    throw a(ex10);
                                }
                            }
                            length = sb.length();
                        }
                        sb.append("<#");
                        try {
                            if (i == n5) {
                                sb.append("...");
                                break Label_0354;
                            }
                        }
                        catch (IllegalArgumentException ex11) {
                            throw a(ex11);
                        }
                    }
                    try {
                        sb.append(ocDeclaratorSymbol3.getType().getBestNameInContext((PsiElement)insertionContext.getFile(), OCElementUtil.getTypeTextWithModifiers(ocDeclaratorSymbol3)));
                        if (!ocDeclaratorSymbol3.isUnnamed()) {
                            sb.append(' ');
                            sb.append(ocDeclaratorSymbol3.getName());
                        }
                    }
                    catch (IllegalArgumentException ex12) {
                        throw a(ex12);
                    }
                    try {
                        if (i == n5 - 1) {
                            continue;
                        }
                        sb.append("#>");
                        if (length2 != -1) {
                            continue;
                        }
                    }
                    catch (IllegalArgumentException ex13) {
                        throw a(ex13);
                    }
                }
                length2 = sb.length();
            }
            insertionContext.getDocument().insertString(insertionContext.getEditor().getCaretModel().getOffset(), (CharSequence)sb.toString());
            CallableInsertUtils.selectFirstPlaceholderIfPresent(insertionContext);
            try {
                changeQualifyingTokenIfNeeded(insertionContext, this.myContextExpression, lookupElement);
                if (b2) {
                    AutoPopupController.getInstance(insertionContext.getProject()).autoPopupParameterInfo(insertionContext.getEditor(), null);
                }
            }
            catch (IllegalArgumentException ex14) {
                throw a(ex14);
            }
        }
    }
    
    private static boolean a(@NotNull final OCFunctionSymbol ocFunctionSymbol, @Nullable final PsiElement psiElement) {
        try {
            if (ocFunctionSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/editor/completion/FunctionInsertHandler", "isUsedAsAName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        if (psiElement != null) {
            final PsiElement parent = psiElement.getParent();
            if (parent instanceof OCExpression) {
                final PsiElement parent2 = parent.getParent();
                try {
                    if (parent2 instanceof OCUnaryExpression) {
                        return ((OCUnaryExpression)parent2).isGetAddress();
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                final Iterator<OCType> iterator = OCExpectedTypeUtil.getExpectedTypes((OCExpression)parent, false).iterator();
                while (iterator.hasNext()) {
                    final OCType resolve = iterator.next().resolve(psiElement.getContainingFile());
                    try {
                        if (!(resolve instanceof OCPointerType) || !(((OCPointerType)resolve).getRefType() instanceof OCFunctionType)) {
                            continue;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                    final OCFunctionType ocFunctionType = (OCFunctionType)((OCPointerType)resolve).getRefType();
                    try {
                        if (ocFunctionType.checkCompatible(ocFunctionSymbol.getResolvedType(), psiElement).getState() == OCType.TypeCheckState.OK) {
                            return true;
                        }
                        continue;
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                }
            }
            else {
                try {
                    if (parent instanceof OCCppUsingStatement) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
            }
        }
        return false;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
