// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.liveTemplates;

import java.util.List;
import java.util.Set;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.symtable.OCGlobalProjectSymbolsCache;
import com.intellij.codeInsight.completion.InsertHandler;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.editor.completion.SymbolLookupBuilderUtil;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import java.util.ArrayList;
import java.util.HashSet;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.codeInsight.lookup.LookupElement;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.util.OCExpectedTypeUtil;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.intellij.codeInsight.template.TextResult;
import com.intellij.codeInsight.template.Result;
import com.intellij.codeInsight.template.ExpressionContext;
import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.template.Expression;
import com.intellij.codeInsight.template.Macro;

public class OCClassNameMacro extends Macro
{
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public String getName() {
        return "className";
    }
    
    public String getPresentableName() {
        return "className()";
    }
    
    public String getDefaultValue() {
        return "NSObject";
    }
    
    public Result calculateResult(@NotNull final Expression[] array, final ExpressionContext expressionContext) {
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "params", "com/jetbrains/cidr/lang/editor/liveTemplates/OCClassNameMacro", "calculateResult"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCClassSymbol a = a(expressionContext);
        try {
            if (a != null) {
                final TextResult textResult = new TextResult(a.getName());
                return (Result)textResult;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final TextResult textResult = null;
        return (Result)textResult;
    }
    
    @Nullable
    private static OCClassSymbol a(final ExpressionContext expressionContext) {
        final PsiElement insertionPlace = OCTemplatesUtil.getInsertionPlace(expressionContext);
        final PsiFile containingFile = insertionPlace.getContainingFile();
        Label_0029: {
            try {
                if (OCClassNameMacro.$assertionsDisabled) {
                    break Label_0029;
                }
                final PsiFile psiFile = containingFile;
                if (psiFile == null) {
                    break Label_0029;
                }
                break Label_0029;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final PsiFile psiFile = containingFile;
                if (psiFile == null) {
                    throw new AssertionError();
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        final OCSendMessageExpression ocSendMessageExpression = (OCSendMessageExpression)PsiTreeUtil.getParentOfType(insertionPlace, (Class)OCSendMessageExpression.class);
        OCSendMessageExpression ocSendMessageExpression3 = null;
        Label_0091: {
            Label_0074: {
                try {
                    if (ocSendMessageExpression == null) {
                        break Label_0074;
                    }
                    final OCSendMessageExpression ocSendMessageExpression2 = ocSendMessageExpression;
                    final PsiElement psiElement = ocSendMessageExpression2.getParent();
                    final boolean b = psiElement instanceof OCSendMessageExpression;
                    if (b) {
                        break Label_0074;
                    }
                    break Label_0074;
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    final OCSendMessageExpression ocSendMessageExpression2 = ocSendMessageExpression;
                    final PsiElement psiElement = ocSendMessageExpression2.getParent();
                    final boolean b = psiElement instanceof OCSendMessageExpression;
                    if (b) {
                        ocSendMessageExpression3 = (OCSendMessageExpression)ocSendMessageExpression.getParent();
                        break Label_0091;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            ocSendMessageExpression3 = null;
        }
        final OCSendMessageExpression ocSendMessageExpression4 = ocSendMessageExpression3;
        OCType resolve = null;
        Label_0112: {
            try {
                if (ocSendMessageExpression4 != null) {
                    resolve = OCExpectedTypeUtil.getExpectedType(ocSendMessageExpression4).resolve(containingFile);
                    break Label_0112;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
            resolve = null;
        }
        final OCType ocType = resolve;
        Label_0137: {
            try {
                if (!(ocType instanceof OCPointerType)) {
                    return null;
                }
                final OCType ocType2 = ocType;
                final boolean b2 = ocType2.isPointerToObject();
                if (b2) {
                    break Label_0137;
                }
                return null;
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
            try {
                final OCType ocType2 = ocType;
                final boolean b2 = ocType2.isPointerToObject();
                if (b2) {
                    return ((OCObjectType)ocType.getTerminalType()).getClassSymbol();
                }
            }
            catch (IllegalArgumentException ex7) {
                throw a(ex7);
            }
        }
        return null;
    }
    
    public LookupElement[] calculateLookupItems(@NotNull final Expression[] array, final ExpressionContext expressionContext) {
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "params", "com/jetbrains/cidr/lang/editor/liveTemplates/OCClassNameMacro", "calculateLookupItems"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final PsiFile psiFile = PsiDocumentManager.getInstance(expressionContext.getProject()).getPsiFile(expressionContext.getEditor().getDocument());
        final HashSet set = new HashSet();
        final ArrayList<LookupElementBuilder> list = new ArrayList<LookupElementBuilder>();
        if (psiFile instanceof OCFile) {
            final OCClassSymbol a = a(expressionContext);
            try {
                if (a != null) {
                    list.add(SymbolLookupBuilderUtil.lookup(a).withInsertHandler((InsertHandler)null));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            OCGlobalProjectSymbolsCache.processTopLevelSymbols(psiFile.getProject(), (Processor<OCSymbol>)(p3 -> {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_3        
                //     1: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
                //     4: ifeq            98
                //     7: aload_3        
                //     8: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isPredeclaration:()Z
                //    13: ifne            98
                //    16: goto            23
                //    19: invokestatic    com/jetbrains/cidr/lang/editor/liveTemplates/OCClassNameMacro.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    22: athrow         
                //    23: aload_3        
                //    24: aload_0        
                //    25: if_acmpeq       98
                //    28: goto            35
                //    31: invokestatic    com/jetbrains/cidr/lang/editor/liveTemplates/OCClassNameMacro.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    34: athrow         
                //    35: aload_3        
                //    36: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
                //    39: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol.getCategoryName:()Ljava/lang/String;
                //    44: ifnonnull       98
                //    47: goto            54
                //    50: invokestatic    com/jetbrains/cidr/lang/editor/liveTemplates/OCClassNameMacro.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    53: athrow         
                //    54: aload_1        
                //    55: aload_3        
                //    56: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
                //    61: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
                //    66: ifeq            98
                //    69: goto            76
                //    72: invokestatic    com/jetbrains/cidr/lang/editor/liveTemplates/OCClassNameMacro.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    75: athrow         
                //    76: aload_2        
                //    77: aload_3        
                //    78: invokestatic    com/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil.lookup:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Lcom/intellij/codeInsight/lookup/LookupElementBuilder;
                //    81: aconst_null    
                //    82: invokevirtual   com/intellij/codeInsight/lookup/LookupElementBuilder.withInsertHandler:(Lcom/intellij/codeInsight/completion/InsertHandler;)Lcom/intellij/codeInsight/lookup/LookupElementBuilder;
                //    85: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
                //    90: pop            
                //    91: goto            98
                //    94: invokestatic    com/jetbrains/cidr/lang/editor/liveTemplates/OCClassNameMacro.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    97: athrow         
                //    98: iconst_1       
                //    99: ireturn        
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                                
                //  -----  -----  -----  -----  ------------------------------------
                //  0      16     19     23     Ljava/lang/IllegalArgumentException;
                //  7      28     31     35     Ljava/lang/IllegalArgumentException;
                //  23     47     50     54     Ljava/lang/IllegalArgumentException;
                //  35     69     72     76     Ljava/lang/IllegalArgumentException;
                //  54     91     94     98     Ljava/lang/IllegalArgumentException;
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0023:
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
            }), null);
            return list.toArray(new LookupElement[list.size()]);
        }
        return null;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCClassNameMacro.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
