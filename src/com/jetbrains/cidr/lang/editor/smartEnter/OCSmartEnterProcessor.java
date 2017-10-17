// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.smartEnter;

import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.psi.OCProperty;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.psi.OCStatement;
import com.jetbrains.cidr.lang.psi.OCCppStaticAssert;
import com.jetbrains.cidr.lang.psi.OCCppUsingStatement;
import com.jetbrains.cidr.lang.psi.OCCppNamespaceAlias;
import com.jetbrains.cidr.lang.psi.OCCppNamespace;
import com.jetbrains.cidr.lang.psi.OCStructLike;
import com.intellij.lang.Language;
import com.intellij.codeInsight.CodeInsightUtilCore;
import com.jetbrains.cidr.lang.OCLanguage;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.editor.RangeMarker;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;
import com.intellij.util.text.CharArrayUtil;
import com.jetbrains.cidr.lang.parser.OCPunctuatorElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.psi.OCMacroCall;
import com.intellij.psi.PsiComment;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.editor.Document;
import com.jetbrains.cidr.lang.psi.OCIfStatement;
import com.jetbrains.cidr.lang.psi.OCForStatement;
import java.util.Collection;
import com.intellij.openapi.util.TextRange;
import java.util.ArrayList;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.jetbrains.cidr.lang.psi.OCBlockOwner;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiElement;
import com.intellij.lang.SmartEnterProcessorWithFixers;

public class OCSmartEnterProcessor extends SmartEnterProcessorWithFixers
{
    private static final CommentBreakerEnterProcessor COMMENT_BREAKER_ENTER_PROCESSOR;
    private static final AfterSemicolonEnterProcessor SEMICOLON_ENTER_PROCESSOR;
    private static Class[] goodCandidateForCorrection;
    
    public OCSmartEnterProcessor() {
        this.addFixers(new LiteralFixer(), new IfConditionFixer(), new ForStatementFixer(), new DefaultStatementWithExpressionFixer(), new DoWhileConditionFixer(), new CaseColonFixer(), new BlockBraceFixer(), new MissingIfBranchesFixer(), new DefaultStatementWithExpressionMissingBodyFixer(), new ParameterListFixer(), new MissingCallableBodyFixer(), new MissingClassNamespaceBodyFixer(), new ParenthesizedFixer(), new SemicolonFixer());
        this.addEnterProcessors(OCSmartEnterProcessor.COMMENT_BREAKER_ENTER_PROCESSOR, OCSmartEnterProcessor.SEMICOLON_ENTER_PROCESSOR, new PlainEnterProcessor());
        this.addAfterEnterProcessors(OCSmartEnterProcessor.SEMICOLON_ENTER_PROCESSOR, new FixEnterProcessor() {
            @Override
            public boolean doEnter(final PsiElement psiElement, final PsiFile psiFile, @NotNull final Editor editor, final boolean b) {
                try {
                    if (editor == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor$1", "doEnter"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                return PlainEnterProcessor.expandCodeBlock(editor, psiElement);
            }
            
            private static IllegalArgumentException b(final IllegalArgumentException ex) {
                return ex;
            }
        });
    }
    
    protected void reformat(PsiElement psiElement) throws IncorrectOperationException {
        try {
            if (psiElement == null) {
                return;
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final Object parent = psiElement.getParent();
        Label_0448: {
            if (parent instanceof OCBlockStatement) {
                final OCBlockStatement ocBlockStatement = (OCBlockStatement)parent;
                Label_0141: {
                    try {
                        if (ocBlockStatement.getStatements().size() <= 0 || ocBlockStatement.getStatements().get(0) != psiElement) {
                            break Label_0141;
                        }
                    }
                    catch (IncorrectOperationException ex2) {
                        throw a(ex2);
                    }
                    final PsiFile containingFile = psiElement.getContainingFile();
                    final Document document = containingFile.getViewProvider().getDocument();
                    if (document != null) {
                        final TextRange rangeWithMacros = OCFixer.getRangeWithMacros(psiElement);
                        CodeStyleManager.getInstance(psiElement.getProject()).reformatText(containingFile, rangeWithMacros.getStartOffset(), document.getLineEndOffset(document.getLineNumber(rangeWithMacros.getEndOffset())));
                        return;
                    }
                }
            }
            else {
                Label_0336: {
                    try {
                        if (!(psiElement instanceof OCBlockOwner) || !(psiElement instanceof PsiNameIdentifierOwner)) {
                            break Label_0336;
                        }
                    }
                    catch (IncorrectOperationException ex3) {
                        throw a(ex3);
                    }
                    final PsiElement openingBrace = ((OCBlockOwner)psiElement).getOpeningBrace();
                    final PsiElement closingBrace = ((OCBlockOwner)psiElement).getClosingBrace();
                    try {
                        if (!OCFixer.isRealLeafElement(openingBrace) || !OCFixer.isRealLeafElement(closingBrace)) {
                            return;
                        }
                    }
                    catch (IncorrectOperationException ex4) {
                        throw a(ex4);
                    }
                    final PsiFile containingFile2 = psiElement.getContainingFile();
                    if (containingFile2.getViewProvider().getDocument() != null) {
                        final ArrayList<TextRange> list = new ArrayList<TextRange>(2);
                        final PsiElement nameIdentifier = ((PsiNameIdentifierOwner)psiElement).getNameIdentifier();
                        try {
                            if (OCFixer.isRealLeafElement(nameIdentifier)) {
                                list.add(TextRange.create(OCFixer.getRangeWithMacros(psiElement).getStartOffset(), OCFixer.getRangeWithMacros(nameIdentifier).getEndOffset()));
                            }
                        }
                        catch (IncorrectOperationException ex5) {
                            throw a(ex5);
                        }
                        list.add(TextRange.create(OCFixer.getRangeWithMacros(openingBrace).getStartOffset(), OCFixer.getRangeWithMacros(closingBrace).getEndOffset()));
                        CodeStyleManager.getInstance(psiElement.getProject()).reformatText(containingFile2, (Collection)list);
                    }
                    return;
                }
                if (parent instanceof OCForStatement) {
                    psiElement = (PsiElement)parent;
                }
                else {
                    try {
                        if (!(parent instanceof OCIfStatement) || psiElement != ((OCIfStatement)parent).getElseBranch()) {
                            break Label_0448;
                        }
                    }
                    catch (IncorrectOperationException ex6) {
                        throw a(ex6);
                    }
                    final PsiFile containingFile3 = psiElement.getContainingFile();
                    final Document document2 = containingFile3.getViewProvider().getDocument();
                    if (document2 != null) {
                        final TextRange rangeWithMacros2 = OCFixer.getRangeWithMacros(psiElement);
                        CodeStyleManager.getInstance(psiElement.getProject()).reformatText(containingFile3, document2.getLineStartOffset(document2.getLineNumber(rangeWithMacros2.getStartOffset())), rangeWithMacros2.getEndOffset());
                        return;
                    }
                }
            }
        }
        super.reformat(psiElement);
    }
    
    public static void reformatBlockLike(@NotNull final OCBlockOwner ocBlockOwner) {
        try {
            if (ocBlockOwner == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "block", "com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor", "reformatBlockLike"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final PsiElement openingBrace = ocBlockOwner.getOpeningBrace();
        final PsiElement closingBrace = ocBlockOwner.getClosingBrace();
        Label_0078: {
            try {
                if (openingBrace == null) {
                    return;
                }
                final PsiElement psiElement = closingBrace;
                if (psiElement == null) {
                    return;
                }
                break Label_0078;
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
            try {
                final PsiElement psiElement = closingBrace;
                if (psiElement == null) {
                    return;
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
        }
        final PsiFile containingFile = ocBlockOwner.getContainingFile();
        CodeStyleManager.getInstance(ocBlockOwner.getProject()).reformatText(containingFile.getViewProvider().getPsi(containingFile.getViewProvider().getBaseLanguage()), OCFixer.getRangeWithMacros(openingBrace).getStartOffset(), OCFixer.getRangeWithMacros(closingBrace).getEndOffset());
    }
    
    @Override
    public boolean doNotStepInto(final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
        //     4: ifne            105
        //     7: aload_1        
        //     8: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //    11: ifne            105
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    20: athrow         
        //    21: aload_1        
        //    22: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //    25: ifne            105
        //    28: goto            35
        //    31: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    34: athrow         
        //    35: aload_1        
        //    36: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStructLike;
        //    39: ifne            105
        //    42: goto            49
        //    45: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    48: athrow         
        //    49: aload_1        
        //    50: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCppNamespace;
        //    53: ifne            105
        //    56: goto            63
        //    59: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    62: athrow         
        //    63: aload_1        
        //    64: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //    67: ifne            105
        //    70: goto            77
        //    73: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    76: athrow         
        //    77: aload_1        
        //    78: instanceof      Lcom/jetbrains/cidr/lang/psi/OCProperty;
        //    81: ifne            105
        //    84: goto            91
        //    87: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    90: athrow         
        //    91: aload_1        
        //    92: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.b:(Lcom/intellij/psi/PsiElement;)Z
        //    95: ifeq            113
        //    98: goto            105
        //   101: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   104: athrow         
        //   105: iconst_1       
        //   106: goto            114
        //   109: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   112: athrow         
        //   113: iconst_0       
        //   114: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      14     17     21     Lcom/intellij/util/IncorrectOperationException;
        //  7      28     31     35     Lcom/intellij/util/IncorrectOperationException;
        //  21     42     45     49     Lcom/intellij/util/IncorrectOperationException;
        //  35     56     59     63     Lcom/intellij/util/IncorrectOperationException;
        //  49     70     73     77     Lcom/intellij/util/IncorrectOperationException;
        //  63     84     87     91     Lcom/intellij/util/IncorrectOperationException;
        //  77     98     101    105    Lcom/intellij/util/IncorrectOperationException;
        //  91     109    109    113    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0021:
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
    protected boolean collectChildrenRecursively(@NotNull final PsiElement p0) {
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
        //    18: ldc             "atCaret"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "collectChildrenRecursively"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    43: athrow         
        //    44: aload_1        
        //    45: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.c:(Lcom/intellij/psi/PsiElement;)Z
        //    48: ifne            65
        //    51: aload_1        
        //    52: invokestatic    com/intellij/psi/util/PsiTreeUtil.hasErrorElements:(Lcom/intellij/psi/PsiElement;)Z
        //    55: ifne            115
        //    58: goto            65
        //    61: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    64: athrow         
        //    65: aload_1        
        //    66: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStructLike;
        //    69: ifne            115
        //    72: goto            79
        //    75: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    78: athrow         
        //    79: aload_1        
        //    80: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCppNamespace;
        //    83: ifne            115
        //    86: goto            93
        //    89: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    92: athrow         
        //    93: aload_1        
        //    94: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.isCompleteBlockOwner:(Lcom/intellij/psi/PsiElement;)Z
        //    97: ifne            115
        //   100: goto            107
        //   103: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   106: athrow         
        //   107: iconst_1       
        //   108: goto            116
        //   111: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   114: athrow         
        //   115: iconst_0       
        //   116: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  44     58     61     65     Lcom/intellij/util/IncorrectOperationException;
        //  51     72     75     79     Lcom/intellij/util/IncorrectOperationException;
        //  65     86     89     93     Lcom/intellij/util/IncorrectOperationException;
        //  79     100    103    107    Lcom/intellij/util/IncorrectOperationException;
        //  93     111    111    115    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0065:
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
    
    private static boolean b(final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //     6: astore_1       
        //     7: aload_1        
        //     8: instanceof      Lcom/jetbrains/cidr/lang/psi/OCIfStatement;
        //    11: ifeq            34
        //    14: aload_1        
        //    15: checkcast       Lcom/jetbrains/cidr/lang/psi/OCIfStatement;
        //    18: invokeinterface com/jetbrains/cidr/lang/psi/OCIfStatement.getCondition:()Lcom/jetbrains/cidr/lang/psi/OCDeclarationOrExpression;
        //    23: aload_0        
        //    24: if_acmpeq       68
        //    27: goto            34
        //    30: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    33: athrow         
        //    34: aload_1        
        //    35: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStatementWithExpression;
        //    38: ifeq            76
        //    41: goto            48
        //    44: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    47: athrow         
        //    48: aload_1        
        //    49: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStatementWithExpression;
        //    52: invokeinterface com/jetbrains/cidr/lang/psi/OCStatementWithExpression.getExpression:()Lcom/intellij/psi/PsiElement;
        //    57: aload_0        
        //    58: if_acmpne       76
        //    61: goto            68
        //    64: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    67: athrow         
        //    68: iconst_1       
        //    69: goto            77
        //    72: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    75: athrow         
        //    76: iconst_0       
        //    77: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  7      27     30     34     Lcom/intellij/util/IncorrectOperationException;
        //  14     41     44     48     Lcom/intellij/util/IncorrectOperationException;
        //  34     61     64     68     Lcom/intellij/util/IncorrectOperationException;
        //  48     72     72     76     Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0034:
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
    protected PsiElement getStatementAtCaret(final Editor p0, final PsiFile p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokeinterface com/intellij/openapi/editor/Editor.getCaretModel:()Lcom/intellij/openapi/editor/CaretModel;
        //     6: invokeinterface com/intellij/openapi/editor/CaretModel.getOffset:()I
        //    11: istore_3       
        //    12: aload_0        
        //    13: aload_1        
        //    14: aload_2        
        //    15: invokespecial   com/intellij/lang/SmartEnterProcessorWithFixers.getStatementAtCaret:(Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;)Lcom/intellij/psi/PsiElement;
        //    18: astore          4
        //    20: aload           4
        //    22: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //    25: astore          5
        //    27: aload           5
        //    29: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.isCompleteBlockOwner:(Lcom/intellij/psi/PsiElement;)Z
        //    32: ifeq            171
        //    35: aload           5
        //    37: checkcast       Lcom/jetbrains/cidr/lang/psi/OCBlockOwner;
        //    40: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.hasNonSpaceChildren:(Lcom/jetbrains/cidr/lang/psi/OCBlockOwner;)Z
        //    43: ifeq            171
        //    46: goto            53
        //    49: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    52: athrow         
        //    53: aload           5
        //    55: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStructLike;
        //    58: ifeq            92
        //    61: goto            68
        //    64: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    67: athrow         
        //    68: aload_1        
        //    69: aload           5
        //    71: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.getSemicolonInsertionOffset:(Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiElement;)I
        //    74: iconst_m1      
        //    75: if_icmpeq       92
        //    78: goto            85
        //    81: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    84: athrow         
        //    85: aload           5
        //    87: areturn        
        //    88: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    91: athrow         
        //    92: aload           5
        //    94: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.getRangeWithMacros:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/openapi/util/TextRange;
        //    97: astore          6
        //    99: iload_3        
        //   100: aload           6
        //   102: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   105: if_icmpge       126
        //   108: aload           4
        //   110: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //   113: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.RBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   116: if_acmpne       169
        //   119: goto            126
        //   122: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   125: athrow         
        //   126: aload_1        
        //   127: invokeinterface com/intellij/openapi/editor/Editor.getCaretModel:()Lcom/intellij/openapi/editor/CaretModel;
        //   132: aload           6
        //   134: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   137: invokeinterface com/intellij/openapi/editor/CaretModel.moveToOffset:(I)V
        //   142: aload           5
        //   144: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   149: astore          7
        //   151: aload           7
        //   153: instanceof      Lcom/jetbrains/cidr/lang/psi/OCLambdaExpression;
        //   156: ifeq            169
        //   159: aload           7
        //   161: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   164: areturn        
        //   165: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   168: athrow         
        //   169: aconst_null    
        //   170: areturn        
        //   171: aload           5
        //   173: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  27     46     49     53     Lcom/intellij/util/IncorrectOperationException;
        //  35     61     64     68     Lcom/intellij/util/IncorrectOperationException;
        //  53     78     81     85     Lcom/intellij/util/IncorrectOperationException;
        //  68     88     88     92     Lcom/intellij/util/IncorrectOperationException;
        //  99     119    122    126    Lcom/intellij/util/IncorrectOperationException;
        //  151    165    165    169    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0053:
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
    
    private static boolean c(final PsiElement psiElement) {
        final Class[] goodCandidateForCorrection = OCSmartEnterProcessor.goodCandidateForCorrection;
        final int length = goodCandidateForCorrection.length;
        int i = 0;
        while (i < length) {
            final Class clazz = goodCandidateForCorrection[i];
            Label_0052: {
                Label_0042: {
                    try {
                        if (!clazz.isInstance(psiElement)) {
                            break Label_0052;
                        }
                        final Class clazz2 = clazz;
                        final Class<PsiComment> clazz3 = PsiComment.class;
                        if (clazz2 != clazz3) {
                            break Label_0042;
                        }
                        return false;
                    }
                    catch (IncorrectOperationException ex) {
                        throw a(ex);
                    }
                    try {
                        final Class clazz2 = clazz;
                        final Class<PsiComment> clazz3 = PsiComment.class;
                        if (clazz2 != clazz3) {
                            return true;
                        }
                    }
                    catch (IncorrectOperationException ex2) {
                        throw a(ex2);
                    }
                }
                return false;
            }
            ++i;
            continue;
            return false;
        }
        return false;
    }
    
    @Nullable
    private static PsiElement a(final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: instanceof      Lcom/intellij/psi/PsiWhiteSpace;
        //     4: ifeq            13
        //     7: aconst_null    
        //     8: areturn        
        //     9: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    12: athrow         
        //    13: aload_0        
        //    14: ldc             Lcom/jetbrains/cidr/lang/psi/OCMacroCall;.class
        //    16: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //    19: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMacroCall;
        //    22: astore_1       
        //    23: aload_1        
        //    24: ifnull          54
        //    27: aload_1        
        //    28: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/jetbrains/cidr/lang/psi/OCMacroCall;)Z
        //    31: ifne            47
        //    34: goto            41
        //    37: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    40: athrow         
        //    41: aconst_null    
        //    42: areturn        
        //    43: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    46: athrow         
        //    47: aload_1        
        //    48: invokeinterface com/jetbrains/cidr/lang/psi/OCMacroCall.getFirstExpansionLeaf:()Lcom/intellij/psi/PsiElement;
        //    53: astore_0       
        //    54: aload_0        
        //    55: getstatic       com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.goodCandidateForCorrection:[Ljava/lang/Class;
        //    58: invokestatic    com/intellij/psi/util/PsiTreeUtil.getNonStrictParentOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //    61: astore_2       
        //    62: aload_2        
        //    63: ifnonnull       72
        //    66: aconst_null    
        //    67: areturn        
        //    68: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    71: athrow         
        //    72: aload_2        
        //    73: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    78: astore_3       
        //    79: aload_3        
        //    80: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarationOrExpression;
        //    83: ifne            114
        //    86: aload_3        
        //    87: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarationStatement;
        //    90: ifne            114
        //    93: goto            100
        //    96: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    99: athrow         
        //   100: aload_3        
        //   101: instanceof      Lcom/jetbrains/cidr/lang/psi/OCParameterDeclaration;
        //   104: ifeq            116
        //   107: goto            114
        //   110: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   113: athrow         
        //   114: aload_3        
        //   115: astore_2       
        //   116: aload_2        
        //   117: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   122: astore_3       
        //   123: aload_3        
        //   124: instanceof      Lcom/jetbrains/cidr/lang/psi/OCParameterList;
        //   127: ifeq            172
        //   130: aload_3        
        //   131: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   136: astore_2       
        //   137: aload_2        
        //   138: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   143: astore_3       
        //   144: aload_3        
        //   145: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //   148: ifeq            156
        //   151: aload_3        
        //   152: astore_2       
        //   153: goto            235
        //   156: aload_2        
        //   157: instanceof      Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //   160: ifeq            235
        //   163: aload_3        
        //   164: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   167: areturn        
        //   168: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   171: athrow         
        //   172: aload_2        
        //   173: aload_3        
        //   174: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Z
        //   177: ifne            192
        //   180: aload_3        
        //   181: astore_2       
        //   182: aload_2        
        //   183: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   188: astore_3       
        //   189: goto            235
        //   192: aload_2        
        //   193: instanceof      Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //   196: ifeq            235
        //   199: aload_1        
        //   200: ifnull          235
        //   203: goto            210
        //   206: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   209: athrow         
        //   210: aload_1        
        //   211: invokeinterface com/jetbrains/cidr/lang/psi/OCMacroCall.getLastExpansionLeaf:()Lcom/intellij/psi/PsiElement;
        //   216: astore          4
        //   218: aload           4
        //   220: ifnull          233
        //   223: aload           4
        //   225: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   228: areturn        
        //   229: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   232: athrow         
        //   233: aconst_null    
        //   234: areturn        
        //   235: aload_2        
        //   236: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //   239: ifne            256
        //   242: aload_2        
        //   243: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarationStatement;
        //   246: ifeq            308
        //   249: goto            256
        //   252: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   255: athrow         
        //   256: aload_2        
        //   257: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.getNextError:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiErrorElement;
        //   260: astore          4
        //   262: aload           4
        //   264: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isMissingRParError:(Lcom/intellij/psi/PsiElement;)Z
        //   267: ifne            299
        //   270: aload           4
        //   272: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isIncompleteExpressionError:(Lcom/intellij/psi/PsiElement;)Z
        //   275: ifne            299
        //   278: goto            285
        //   281: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   284: athrow         
        //   285: aload_3        
        //   286: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarationOrExpression;
        //   289: ifeq            308
        //   292: goto            299
        //   295: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   298: athrow         
        //   299: aload_3        
        //   300: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   303: areturn        
        //   304: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   307: athrow         
        //   308: aload_2        
        //   309: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   312: ifeq            324
        //   315: aload_2        
        //   316: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   319: areturn        
        //   320: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   323: athrow         
        //   324: aload_2        
        //   325: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStructLike;
        //   328: ifeq            373
        //   331: aload_2        
        //   332: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.isLikeStructVarDeclaration:(Lcom/intellij/psi/PsiElement;)Z
        //   335: ifne            364
        //   338: goto            345
        //   341: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   344: athrow         
        //   345: aload_3        
        //   346: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   351: instanceof      Lcom/jetbrains/cidr/lang/psi/OCParameterDeclaration;
        //   354: ifeq            373
        //   357: goto            364
        //   360: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   363: athrow         
        //   364: aload_3        
        //   365: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   368: areturn        
        //   369: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   372: athrow         
        //   373: aload_2        
        //   374: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.c:(Lcom/intellij/psi/PsiElement;)Z
        //   377: ifeq            388
        //   380: aload_2        
        //   381: goto            389
        //   384: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   387: athrow         
        //   388: aconst_null    
        //   389: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      9      9      13     Lcom/intellij/util/IncorrectOperationException;
        //  23     34     37     41     Lcom/intellij/util/IncorrectOperationException;
        //  27     43     43     47     Lcom/intellij/util/IncorrectOperationException;
        //  62     68     68     72     Lcom/intellij/util/IncorrectOperationException;
        //  79     93     96     100    Lcom/intellij/util/IncorrectOperationException;
        //  86     107    110    114    Lcom/intellij/util/IncorrectOperationException;
        //  156    168    168    172    Lcom/intellij/util/IncorrectOperationException;
        //  192    203    206    210    Lcom/intellij/util/IncorrectOperationException;
        //  218    229    229    233    Lcom/intellij/util/IncorrectOperationException;
        //  235    249    252    256    Lcom/intellij/util/IncorrectOperationException;
        //  262    278    281    285    Lcom/intellij/util/IncorrectOperationException;
        //  270    292    295    299    Lcom/intellij/util/IncorrectOperationException;
        //  285    304    304    308    Lcom/intellij/util/IncorrectOperationException;
        //  308    320    320    324    Lcom/intellij/util/IncorrectOperationException;
        //  324    338    341    345    Lcom/intellij/util/IncorrectOperationException;
        //  331    357    360    364    Lcom/intellij/util/IncorrectOperationException;
        //  345    369    369    373    Lcom/intellij/util/IncorrectOperationException;
        //  373    384    384    388    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0285:
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
    
    private static boolean a(@NotNull final OCMacroCall ocMacroCall) {
        try {
            if (ocMacroCall == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "macroCall", "com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor", "isValidMacroCall"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final ASTNode node = ocMacroCall.getNode();
        Label_0082: {
            try {
                if (node.findChildByType((IElementType)OCTokenTypes.LPAR) == null) {
                    break Label_0082;
                }
                final ASTNode astNode = node;
                final OCPunctuatorElementType ocPunctuatorElementType = OCTokenTypes.RPAR;
                final ASTNode astNode2 = astNode.findChildByType((IElementType)ocPunctuatorElementType);
                if (astNode2 != null) {
                    break Label_0082;
                }
                return false;
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
            try {
                final ASTNode astNode = node;
                final OCPunctuatorElementType ocPunctuatorElementType = OCTokenTypes.RPAR;
                final ASTNode astNode2 = astNode.findChildByType((IElementType)ocPunctuatorElementType);
                if (astNode2 != null) {
                    return true;
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
        }
        return false;
    }
    
    private static boolean a(final PsiElement p0, final PsiElement p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStatementWithExpression;
        //     4: ifeq            70
        //     7: aload_1        
        //     8: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDoWhileStatement;
        //    11: ifne            70
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    20: athrow         
        //    21: aload_1        
        //    22: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStatementWithExpression;
        //    25: invokeinterface com/jetbrains/cidr/lang/psi/OCStatementWithExpression.getRParenth:()Lcom/intellij/lang/ASTNode;
        //    30: ifnull          70
        //    33: goto            40
        //    36: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    39: athrow         
        //    40: aload_1        
        //    41: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStatementWithExpression;
        //    44: invokeinterface com/jetbrains/cidr/lang/psi/OCStatementWithExpression.getBody:()Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //    49: aload_0        
        //    50: if_acmpne       68
        //    53: goto            60
        //    56: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    59: athrow         
        //    60: iconst_1       
        //    61: goto            69
        //    64: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    67: athrow         
        //    68: iconst_0       
        //    69: ireturn        
        //    70: aload_1        
        //    71: instanceof      Lcom/jetbrains/cidr/lang/psi/OCIfStatement;
        //    74: ifeq            146
        //    77: aload_1        
        //    78: checkcast       Lcom/jetbrains/cidr/lang/psi/OCIfStatement;
        //    81: invokeinterface com/jetbrains/cidr/lang/psi/OCIfStatement.getRParenth:()Lcom/intellij/lang/ASTNode;
        //    86: ifnull          146
        //    89: goto            96
        //    92: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    95: athrow         
        //    96: aload_1        
        //    97: checkcast       Lcom/jetbrains/cidr/lang/psi/OCIfStatement;
        //   100: invokeinterface com/jetbrains/cidr/lang/psi/OCIfStatement.getThenBranch:()Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   105: aload_0        
        //   106: if_acmpeq       136
        //   109: goto            116
        //   112: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   115: athrow         
        //   116: aload_1        
        //   117: checkcast       Lcom/jetbrains/cidr/lang/psi/OCIfStatement;
        //   120: invokeinterface com/jetbrains/cidr/lang/psi/OCIfStatement.getElseBranch:()Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   125: aload_0        
        //   126: if_acmpne       144
        //   129: goto            136
        //   132: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   135: athrow         
        //   136: iconst_1       
        //   137: goto            145
        //   140: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   143: athrow         
        //   144: iconst_0       
        //   145: ireturn        
        //   146: aload_1        
        //   147: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStatementWithExpression;
        //   150: ifne            175
        //   153: aload_1        
        //   154: instanceof      Lcom/jetbrains/cidr/lang/psi/OCIfStatement;
        //   157: ifne            175
        //   160: goto            167
        //   163: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   166: athrow         
        //   167: iconst_1       
        //   168: goto            176
        //   171: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   174: athrow         
        //   175: iconst_0       
        //   176: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      14     17     21     Lcom/intellij/util/IncorrectOperationException;
        //  7      33     36     40     Lcom/intellij/util/IncorrectOperationException;
        //  21     53     56     60     Lcom/intellij/util/IncorrectOperationException;
        //  40     64     64     68     Lcom/intellij/util/IncorrectOperationException;
        //  70     89     92     96     Lcom/intellij/util/IncorrectOperationException;
        //  77     109    112    116    Lcom/intellij/util/IncorrectOperationException;
        //  96     129    132    136    Lcom/intellij/util/IncorrectOperationException;
        //  116    140    140    144    Lcom/intellij/util/IncorrectOperationException;
        //  146    160    163    167    Lcom/intellij/util/IncorrectOperationException;
        //  153    171    171    175    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0021:
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
    protected void moveCaretInsideBracesIfAny(@NotNull final Editor editor, @NotNull final PsiFile psiFile) throws IncorrectOperationException {
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor", "moveCaretInsideBracesIfAny"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor", "moveCaretInsideBracesIfAny"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        final int offset = editor.getCaretModel().getOffset();
        final CharSequence charsSequence = editor.getDocument().getCharsSequence();
        try {
            if (!CharArrayUtil.regionMatches(charsSequence, offset, (CharSequence)"{}")) {
                if (!CharArrayUtil.regionMatches(charsSequence, offset, (CharSequence)"{\n}")) {
                    return;
                }
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        this.commit(editor);
        final CodeStyleSettings settings = CodeStyleSettingsManager.getSettings(psiFile.getProject());
        final OCCodeStyleSettings ocCodeStyleSettings = (OCCodeStyleSettings)settings.getCustomSettings((Class)OCCodeStyleSettings.class);
        final boolean keep_SIMPLE_BLOCKS_IN_ONE_LINE = settings.KEEP_SIMPLE_BLOCKS_IN_ONE_LINE;
        final boolean keep_SIMPLE_LAMBDAS_IN_ONE_LINE = settings.KEEP_SIMPLE_LAMBDAS_IN_ONE_LINE;
        final boolean keep_SIMPLE_CLASSES_IN_ONE_LINE = settings.KEEP_SIMPLE_CLASSES_IN_ONE_LINE;
        final boolean keep_STRUCTURES_IN_ONE_LINE = ocCodeStyleSettings.KEEP_STRUCTURES_IN_ONE_LINE;
        try {
            final OCCodeStyleSettings ocCodeStyleSettings2 = ocCodeStyleSettings;
            final CodeStyleSettings codeStyleSettings = settings;
            final CodeStyleSettings codeStyleSettings2 = settings;
            final CodeStyleSettings codeStyleSettings3 = settings;
            final boolean b = false;
            codeStyleSettings3.KEEP_SIMPLE_LAMBDAS_IN_ONE_LINE = b;
            codeStyleSettings2.KEEP_SIMPLE_BLOCKS_IN_ONE_LINE = b;
            codeStyleSettings.KEEP_SIMPLE_CLASSES_IN_ONE_LINE = b;
            ocCodeStyleSettings2.KEEP_STRUCTURES_IN_ONE_LINE = b;
            final PsiElement element = psiFile.findElementAt(offset);
            final RangeMarker rangeMarker = this.createRangeMarker(element);
            Label_0295: {
                Label_0264: {
                    try {
                        if (element == null) {
                            break Label_0264;
                        }
                        final PsiElement psiElement = element;
                        final PsiElement psiElement2 = psiElement.getParent();
                        final boolean b2 = OCFixer.isCompleteBlockOwner(psiElement2);
                        if (b2) {
                            break Label_0264;
                        }
                        break Label_0264;
                    }
                    catch (IncorrectOperationException ex4) {
                        throw a(ex4);
                    }
                    try {
                        final PsiElement psiElement = element;
                        final PsiElement psiElement2 = psiElement.getParent();
                        final boolean b2 = OCFixer.isCompleteBlockOwner(psiElement2);
                        if (b2) {
                            reformatBlockLike((OCBlockOwner)element.getParent());
                            break Label_0295;
                        }
                    }
                    catch (IncorrectOperationException ex5) {
                        throw a(ex5);
                    }
                }
                this.reformat(PsiTreeUtil.getParentOfType(element, (Class)OCBlockStatement.class));
            }
            this.commit(editor);
            final PsiElement restoreElementAtCaret = this.restoreElementAtCaret(psiFile, element, rangeMarker);
            rangeMarker.dispose();
            editor.getCaretModel().moveToOffset(OCFixer.getRangeWithMacros(restoreElementAtCaret).getEndOffset());
        }
        finally {
            ocCodeStyleSettings.KEEP_STRUCTURES_IN_ONE_LINE = keep_STRUCTURES_IN_ONE_LINE;
            settings.KEEP_SIMPLE_BLOCKS_IN_ONE_LINE = keep_SIMPLE_BLOCKS_IN_ONE_LINE;
            settings.KEEP_SIMPLE_LAMBDAS_IN_ONE_LINE = keep_SIMPLE_LAMBDAS_IN_ONE_LINE;
            settings.KEEP_SIMPLE_CLASSES_IN_ONE_LINE = keep_SIMPLE_CLASSES_IN_ONE_LINE;
        }
    }
    
    @Override
    protected boolean reformatBeforeEnter(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "atCaret", "com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor", "reformatBeforeEnter"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (!PsiTreeUtil.hasErrorElements(psiElement)) {
                return true;
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        return false;
    }
    
    @Override
    protected void processDefaultEnter(@NotNull final Project p0, @NotNull final Editor p1, @NotNull final PsiFile p2) {
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
        //    18: ldc             "project"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "processDefaultEnter"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    43: athrow         
        //    44: aload_2        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "editor"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "processDefaultEnter"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    87: athrow         
        //    88: aload_3        
        //    89: ifnonnull       132
        //    92: new             Ljava/lang/IllegalArgumentException;
        //    95: dup            
        //    96: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    98: ldc             3
        //   100: anewarray       Ljava/lang/Object;
        //   103: dup            
        //   104: ldc             0
        //   106: ldc             "file"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "processDefaultEnter"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   131: athrow         
        //   132: aload_0        
        //   133: aload_2        
        //   134: aload_3        
        //   135: invokespecial   com/intellij/lang/SmartEnterProcessorWithFixers.getStatementAtCaret:(Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;)Lcom/intellij/psi/PsiElement;
        //   138: astore          4
        //   140: aload           4
        //   142: ldc             Lcom/jetbrains/cidr/lang/psi/OCMacroCall;.class
        //   144: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   147: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMacroCall;
        //   150: astore          5
        //   152: aload           5
        //   154: ifnull          172
        //   157: aload           5
        //   159: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/jetbrains/cidr/lang/psi/OCMacroCall;)Z
        //   162: ifeq            193
        //   165: goto            172
        //   168: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   171: athrow         
        //   172: getstatic       com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.COMMENT_BREAKER_ENTER_PROCESSOR:Lcom/jetbrains/cidr/lang/editor/smartEnter/CommentBreakerEnterProcessor;
        //   175: aload           4
        //   177: aload_3        
        //   178: aload_2        
        //   179: iconst_0       
        //   180: invokevirtual   com/jetbrains/cidr/lang/editor/smartEnter/CommentBreakerEnterProcessor.doEnter:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiFile;Lcom/intellij/openapi/editor/Editor;Z)Z
        //   183: ifeq            198
        //   186: goto            193
        //   189: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   192: athrow         
        //   193: return         
        //   194: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   197: athrow         
        //   198: aload_2        
        //   199: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCSmartEnterProcessor.plainEnter:(Lcom/intellij/openapi/editor/Editor;)V
        //   202: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  44     84     84     88     Lcom/intellij/util/IncorrectOperationException;
        //  88     128    128    132    Lcom/intellij/util/IncorrectOperationException;
        //  152    165    168    172    Lcom/intellij/util/IncorrectOperationException;
        //  157    186    189    193    Lcom/intellij/util/IncorrectOperationException;
        //  172    194    194    198    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0172:
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
    
    public boolean isFirstFixAttempt() {
        try {
            if (this.myAttempt == 0) {
                return true;
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return false;
    }
    
    @Override
    protected PsiElement restoreElementAtCaret(final PsiFile psiFile, final PsiElement psiElement, final RangeMarker rangeMarker) {
        return CodeInsightUtilCore.findElementInRange(psiFile, rangeMarker.getStartOffset(), rangeMarker.getEndOffset(), psiElement.getClass(), OCLanguage.getInstance());
    }
    
    static {
        COMMENT_BREAKER_ENTER_PROCESSOR = new CommentBreakerEnterProcessor();
        SEMICOLON_ENTER_PROCESSOR = new AfterSemicolonEnterProcessor();
        OCSmartEnterProcessor.goodCandidateForCorrection = new Class[] { OCStructLike.class, OCCppNamespace.class, OCCppNamespaceAlias.class, OCCppUsingStatement.class, OCCppStaticAssert.class, OCStatement.class, OCMethod.class, OCProperty.class, OCDeclaration.class, PsiComment.class };
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
