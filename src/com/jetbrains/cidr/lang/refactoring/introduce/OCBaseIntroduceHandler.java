// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.introduce;

import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.psi.OCCppNamespace;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import java.util.Map;
import java.util.Collection;
import com.intellij.openapi.util.Pass;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.openapi.application.ApplicationManager;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import com.intellij.refactoring.util.CommonRefactoringUtil;
import com.jetbrains.cidr.lang.util.OCParenthesesUtils;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.refactoring.RefactoringBundle;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import com.intellij.util.text.CharArrayUtil;
import com.intellij.util.ArrayUtil;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.featureStatistics.FeatureUsageTracker;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.refactoring.introduce.inplace.OccurrencesChooser;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.util.TextRange;
import com.intellij.util.NotNullFunction;
import com.intellij.refactoring.RefactoringActionHandler;
import com.intellij.psi.PsiElement;

public abstract class OCBaseIntroduceHandler<E extends PsiElement> implements RefactoringActionHandler
{
    private Class<? extends E>[] myExpressionClasses;
    private String myExpressionKind;
    private String myRefactoringName;
    private static final NotNullFunction<PsiElement, TextRange> RANGER;
    
    protected OCBaseIntroduceHandler(final String myExpressionKind, final String myRefactoringName, final Class<? extends E>... myExpressionClasses) {
        this.myExpressionKind = myExpressionKind;
        this.myRefactoringName = myRefactoringName;
        this.myExpressionClasses = myExpressionClasses;
    }
    
    private OccurrencesChooser<E> a(final Editor editor) {
        return new OccurrencesChooser<E>(editor) {
            @Override
            protected TextRange getOccurrenceRange(final E e) {
                return OCElementUtil.getRangeWithMacros(e);
            }
        };
    }
    
    public void invoke(@NotNull final Project p0, final Editor p1, final PsiFile p2, final DataContext p3) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/refactoring/introduce/OCBaseIntroduceHandler"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "invoke"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCBaseIntroduceHandler.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //    43: athrow         
        //    44: aload_1        
        //    45: invokestatic    com/intellij/psi/PsiDocumentManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/PsiDocumentManager;
        //    48: invokevirtual   com/intellij/psi/PsiDocumentManager.commitAllDocuments:()V
        //    51: aload_2        
        //    52: invokeinterface com/intellij/openapi/editor/Editor.getSelectionModel:()Lcom/intellij/openapi/editor/SelectionModel;
        //    57: astore          5
        //    59: aload           5
        //    61: invokeinterface com/intellij/openapi/editor/SelectionModel.hasSelection:()Z
        //    66: istore          6
        //    68: iload           6
        //    70: ifne            182
        //    73: aload_0        
        //    74: aload_2        
        //    75: aload_3        
        //    76: invokevirtual   com/jetbrains/cidr/lang/refactoring/introduce/OCBaseIntroduceHandler.collectElements:(Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;)Ljava/util/List;
        //    79: astore          7
        //    81: aload           7
        //    83: invokeinterface java/util/List.isEmpty:()Z
        //    88: ifeq            105
        //    91: aload           5
        //    93: invokeinterface com/intellij/openapi/editor/SelectionModel.selectLineAtCaret:()V
        //    98: goto            182
        //   101: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCBaseIntroduceHandler.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //   104: athrow         
        //   105: aload           7
        //   107: invokeinterface java/util/List.size:()I
        //   112: iconst_1       
        //   113: if_icmpne       152
        //   116: aload           7
        //   118: iconst_0       
        //   119: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   124: checkcast       Lcom/intellij/psi/PsiElement;
        //   127: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getRangeWithMacros:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/openapi/util/TextRange;
        //   130: astore          8
        //   132: aload           5
        //   134: aload           8
        //   136: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   139: aload           8
        //   141: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   144: invokeinterface com/intellij/openapi/editor/SelectionModel.setSelection:(II)V
        //   149: goto            182
        //   152: aload_2        
        //   153: aload           7
        //   155: new             Lcom/jetbrains/cidr/lang/refactoring/introduce/OCBaseIntroduceHandler$2;
        //   158: dup            
        //   159: aload_0        
        //   160: aload_1        
        //   161: aload_2        
        //   162: aload_3        
        //   163: invokespecial   com/jetbrains/cidr/lang/refactoring/introduce/OCBaseIntroduceHandler$2.<init>:(Lcom/jetbrains/cidr/lang/refactoring/introduce/OCBaseIntroduceHandler;Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;)V
        //   166: new             Lcom/jetbrains/cidr/lang/refactoring/OCExpressionTrimRenderer$RenderFunction;
        //   169: dup            
        //   170: invokespecial   com/jetbrains/cidr/lang/refactoring/OCExpressionTrimRenderer$RenderFunction.<init>:()V
        //   173: ldc             "Expressions"
        //   175: getstatic       com/jetbrains/cidr/lang/refactoring/introduce/OCBaseIntroduceHandler.RANGER:Lcom/intellij/util/NotNullFunction;
        //   178: invokestatic    com/intellij/refactoring/IntroduceTargetChooser.showChooser:(Lcom/intellij/openapi/editor/Editor;Ljava/util/List;Lcom/intellij/openapi/util/Pass;Lcom/intellij/util/Function;Ljava/lang/String;Lcom/intellij/util/NotNullFunction;)V
        //   181: return         
        //   182: aload_0        
        //   183: aload_1        
        //   184: aload_2        
        //   185: aload_3        
        //   186: aload           5
        //   188: invokeinterface com/intellij/openapi/editor/SelectionModel.getSelectionStart:()I
        //   193: aload           5
        //   195: invokeinterface com/intellij/openapi/editor/SelectionModel.getSelectionEnd:()I
        //   200: invokespecial   com/jetbrains/cidr/lang/refactoring/introduce/OCBaseIntroduceHandler.a:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;II)Z
        //   203: istore          7
        //   205: iload           7
        //   207: ifeq            250
        //   210: aload_2        
        //   211: invokestatic    com/intellij/codeInsight/lookup/LookupManager.getActiveLookup:(Lcom/intellij/openapi/editor/Editor;)Lcom/intellij/codeInsight/lookup/LookupEx;
        //   214: ifnonnull       250
        //   217: goto            224
        //   220: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCBaseIntroduceHandler.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //   223: athrow         
        //   224: iload           6
        //   226: ifne            250
        //   229: goto            236
        //   232: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCBaseIntroduceHandler.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //   235: athrow         
        //   236: aload           5
        //   238: invokeinterface com/intellij/openapi/editor/SelectionModel.removeSelection:()V
        //   243: goto            250
        //   246: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCBaseIntroduceHandler.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //   249: athrow         
        //   250: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  0      40     40     44     Ljava/lang/UnsupportedOperationException;
        //  81     101    101    105    Ljava/lang/UnsupportedOperationException;
        //  205    217    220    224    Ljava/lang/UnsupportedOperationException;
        //  210    229    232    236    Ljava/lang/UnsupportedOperationException;
        //  224    243    246    250    Ljava/lang/UnsupportedOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0224:
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
    
    private boolean a(final Project project, final Editor editor, final PsiFile psiFile, int shiftForward, int n) {
        String s = null;
        Label_0039: {
            try {
                FeatureUsageTracker.getInstance().triggerFeatureUsed("refactoring.introduceVariable");
                PsiDocumentManager.getInstance(project).commitAllDocuments();
                if (ArrayUtil.indexOf((Object[])this.myExpressionClasses, (Object)OCExpression.class) != -1) {
                    s = " \t\n;";
                    break Label_0039;
                }
            }
            catch (UnsupportedOperationException ex) {
                throw b(ex);
            }
            s = " \t\n";
        }
        final String s2 = s;
        shiftForward = CharArrayUtil.shiftForward(editor.getDocument().getCharsSequence(), shiftForward, s2);
        n = CharArrayUtil.shiftBackward(editor.getDocument().getCharsSequence(), n - 1, s2) + 1;
        final PsiElement elementAtRange = this.findElementAtRange(psiFile, shiftForward, n);
        try {
            if (!OCSearchScope.isInProjectSources((PsiElement)psiFile)) {
                this.showErrorMessage(project, editor, RefactoringBundle.message("error.out.of.project.element.default"));
                return false;
            }
        }
        catch (UnsupportedOperationException ex2) {
            throw b(ex2);
        }
        try {
            if (elementAtRange == null) {
                this.showErrorMessage(project, editor, "Selected block should represent " + this.myExpressionKind);
                return false;
            }
        }
        catch (UnsupportedOperationException ex3) {
            throw b(ex3);
        }
        return this.invoke(project, editor, (E)elementAtRange, null, false, true, null, null);
    }
    
    @Nullable
    protected E substituteElement(final E e) {
        return e;
    }
    
    protected List<E> collectElements(final Editor p0, final PsiFile p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Ljava/util/ArrayList;
        //     3: dup            
        //     4: invokespecial   java/util/ArrayList.<init>:()V
        //     7: astore_3       
        //     8: aload_0        
        //     9: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCBaseIntroduceHandler.myExpressionClasses:[Ljava/lang/Class;
        //    12: astore          4
        //    14: aload           4
        //    16: arraylength    
        //    17: istore          5
        //    19: iconst_0       
        //    20: istore          6
        //    22: iload           6
        //    24: iload           5
        //    26: if_icmpge       200
        //    29: aload           4
        //    31: iload           6
        //    33: aaload         
        //    34: astore          7
        //    36: new             Ljava/util/ArrayList;
        //    39: dup            
        //    40: invokespecial   java/util/ArrayList.<init>:()V
        //    43: astore          8
        //    45: aload_2        
        //    46: aload_1        
        //    47: aload_1        
        //    48: invokeinterface com/intellij/openapi/editor/Editor.getCaretModel:()Lcom/intellij/openapi/editor/CaretModel;
        //    53: invokeinterface com/intellij/openapi/editor/CaretModel.getOffset:()I
        //    58: aload           7
        //    60: aload           8
        //    62: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.collectElements:(Lcom/intellij/psi/PsiFile;Lcom/intellij/openapi/editor/Editor;ILjava/lang/Class;Ljava/util/List;)V
        //    65: aload           8
        //    67: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    72: astore          9
        //    74: aload           9
        //    76: invokeinterface java/util/Iterator.hasNext:()Z
        //    81: ifeq            178
        //    84: aload           9
        //    86: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    91: checkcast       Lcom/intellij/psi/PsiElement;
        //    94: astore          10
        //    96: aload           10
        //    98: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //   101: ifne            119
        //   104: aload           10
        //   106: instanceof      Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //   109: ifeq            146
        //   112: goto            119
        //   115: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCBaseIntroduceHandler.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //   118: athrow         
        //   119: aload           10
        //   121: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   126: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCallExpression;
        //   129: ifeq            146
        //   132: goto            139
        //   135: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCBaseIntroduceHandler.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //   138: athrow         
        //   139: goto            74
        //   142: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCBaseIntroduceHandler.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //   145: athrow         
        //   146: aload_0        
        //   147: aload           10
        //   149: invokevirtual   com/jetbrains/cidr/lang/refactoring/introduce/OCBaseIntroduceHandler.substituteElement:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   152: astore          10
        //   154: aload           10
        //   156: ifnull          175
        //   159: aload_3        
        //   160: aload           10
        //   162: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   167: pop            
        //   168: goto            175
        //   171: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCBaseIntroduceHandler.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //   174: athrow         
        //   175: goto            74
        //   178: aload_3        
        //   179: invokeinterface java/util/List.isEmpty:()Z
        //   184: ifne            194
        //   187: goto            200
        //   190: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCBaseIntroduceHandler.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //   193: athrow         
        //   194: iinc            6, 1
        //   197: goto            22
        //   200: aload_3        
        //   201: areturn        
        //    Signature:
        //  (Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;)Ljava/util/List<TE;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  96     112    115    119    Ljava/lang/UnsupportedOperationException;
        //  104    132    135    139    Ljava/lang/UnsupportedOperationException;
        //  119    142    142    146    Ljava/lang/UnsupportedOperationException;
        //  154    168    171    175    Ljava/lang/UnsupportedOperationException;
        //  178    190    190    194    Ljava/lang/UnsupportedOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0119:
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
    protected E findElementAtRange(final PsiFile psiFile, final int n, final int n2) {
        final Class<? extends E>[] myExpressionClasses = this.myExpressionClasses;
        for (int length = myExpressionClasses.length, i = 0; i < length; ++i) {
            Object o = OCCodeInsightUtil.findElementAtRange(psiFile, n, n2, (Class<E>)myExpressionClasses[i], true);
            if (o != null) {
                o = this.substituteElement((OCExpression)o);
            }
            if (o instanceof OCExpression) {
                o = OCParenthesesUtils.topmostParenthesized((OCExpression)o);
            }
            try {
                if (o != null) {
                    return (E)o;
                }
            }
            catch (UnsupportedOperationException ex) {
                throw b(ex);
            }
        }
        return null;
    }
    
    protected List<E> findElementOccurrences(final E e) {
        return OCCodeInsightUtil.findElementOccurrences(this.getElementScope(e), e);
    }
    
    protected void showErrorMessage(final Project project, final Editor editor, final String s) {
        CommonRefactoringUtil.showErrorHint(project, editor, s, this.myRefactoringName, (String)null);
    }
    
    private boolean a(final E e, final List<E> list, final LinkedHashMap<OccurrencesChooser.ReplaceChoice, List<E>> linkedHashMap) {
        linkedHashMap.put(OccurrencesChooser.ReplaceChoice.NO, Collections.singletonList(e));
        final ArrayList<OCExpression> list2 = new ArrayList<OCExpression>();
        for (final PsiElement psiElement : list) {
            Label_0077: {
                try {
                    if (!(psiElement instanceof OCExpression)) {
                        break Label_0077;
                    }
                    final PsiElement psiElement2 = psiElement;
                    final OCExpression ocExpression = (OCExpression)psiElement2;
                    final boolean b = OCCodeInsightUtil.isAssignmentLHS(ocExpression);
                    if (!b) {
                        break Label_0077;
                    }
                    continue;
                }
                catch (UnsupportedOperationException ex) {
                    throw b(ex);
                }
                try {
                    final PsiElement psiElement2 = psiElement;
                    final OCExpression ocExpression = (OCExpression)psiElement2;
                    final boolean b = OCCodeInsightUtil.isAssignmentLHS(ocExpression);
                    if (b) {
                        continue;
                    }
                    list2.add((OCExpression)psiElement);
                }
                catch (UnsupportedOperationException ex2) {
                    throw b(ex2);
                }
            }
        }
        boolean b2 = false;
        Label_0139: {
            Label_0130: {
                try {
                    if (list.size() <= list2.size()) {
                        break Label_0130;
                    }
                    final ArrayList<Object> list3 = (ArrayList<Object>)list;
                    final int n = list3.size();
                    final int n2 = 1;
                    if (n > n2) {
                        break Label_0130;
                    }
                    break Label_0130;
                }
                catch (UnsupportedOperationException ex3) {
                    throw b(ex3);
                }
                try {
                    final ArrayList<Object> list3 = (ArrayList<Object>)list;
                    final int n = list3.size();
                    final int n2 = 1;
                    if (n > n2) {
                        b2 = true;
                        break Label_0139;
                    }
                }
                catch (UnsupportedOperationException ex4) {
                    throw b(ex4);
                }
            }
            b2 = false;
        }
        final boolean b3 = b2;
        Label_0180: {
            Label_0163: {
                try {
                    if (!b3) {
                        break Label_0180;
                    }
                    final ArrayList<OCExpression> list4 = list2;
                    final int n3 = list4.size();
                    if (n3 > 0) {
                        break Label_0163;
                    }
                    break Label_0180;
                }
                catch (UnsupportedOperationException ex5) {
                    throw b(ex5);
                }
                try {
                    final ArrayList<OCExpression> list4 = list2;
                    final int n3 = list4.size();
                    if (n3 > 0) {
                        linkedHashMap.put(OccurrencesChooser.ReplaceChoice.NO_WRITE, (ArrayList<E>)list2);
                    }
                }
                catch (UnsupportedOperationException ex6) {
                    throw b(ex6);
                }
            }
            try {
                if (list.size() > 1) {
                    linkedHashMap.put(OccurrencesChooser.ReplaceChoice.ALL, list);
                }
            }
            catch (UnsupportedOperationException ex7) {
                throw b(ex7);
            }
        }
        return b3;
    }
    
    protected boolean filterUsages(final E e) {
        try {
            if (!ApplicationManager.getApplication().isUnitTestMode()) {
                return true;
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        return false;
    }
    
    public boolean invoke(final Project project, final Editor editor, final E e, @Nullable final OCType exprType, final boolean createFromUsageMode, final boolean b, @Nullable final String usageName, @Nullable final OCSymbol parentSymbol) {
        try {
            FeatureUsageTracker.getInstance().triggerFeatureUsed(this.getFeatureID());
            if (e == null) {
                return false;
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        try {
            if (!CommonRefactoringUtil.checkReadOnlyStatus((PsiElement)e)) {
                return false;
            }
        }
        catch (UnsupportedOperationException ex2) {
            throw b(ex2);
        }
        final List<E> elementOccurrences = this.findElementOccurrences(e);
        final OCBaseInplaceIntroducer<?, E> introducer = this.createIntroducer(project, editor, e, elementOccurrences, this.myRefactoringName);
        introducer.setExprType(exprType);
        introducer.setCreateFromUsageMode(createFromUsageMode);
        introducer.setParentSymbol(parentSymbol);
        introducer.setUsageName(usageName);
        final String checkExpression = introducer.checkExpression(e);
        try {
            if (checkExpression != null) {
                introducer.cancelIntroduce();
                this.showErrorMessage(project, editor, checkExpression);
                return false;
            }
        }
        catch (UnsupportedOperationException ex3) {
            throw b(ex3);
        }
        final LinkedHashMap linkedHashMap = ContainerUtil.newLinkedHashMap();
        this.a(e, elementOccurrences, linkedHashMap);
        final Pass<OccurrencesChooser.ReplaceChoice> pass = new Pass<OccurrencesChooser.ReplaceChoice>() {
            public void pass(final OccurrencesChooser.ReplaceChoice replaceChoice) {
                introducer.setOccurrences((replaceChoice != null) ? linkedHashMap.get(replaceChoice) : elementOccurrences);
                final PsiElement evaluateAnchor = introducer.evaluateAnchor();
                if (evaluateAnchor != null) {
                    introducer.setAnchor(evaluateAnchor);
                    introducer.configurePanel();
                    introducer.startInplaceIntroduceTemplate();
                }
            }
        };
        Label_0167: {
            try {
                if (!this.filterUsages(e)) {
                    break Label_0167;
                }
                final boolean b2 = b;
                if (!b2) {
                    break Label_0167;
                }
                break Label_0167;
            }
            catch (UnsupportedOperationException ex4) {
                throw b(ex4);
            }
            try {
                final boolean b2 = b;
                if (!b2) {
                    pass.pass((Object)null);
                    return true;
                }
            }
            catch (UnsupportedOperationException ex5) {
                throw b(ex5);
            }
        }
        this.a(editor).showChooser(pass, linkedHashMap);
        return true;
    }
    
    protected PsiElement getElementScope(final E e) {
        final OCBlockStatement ocBlockStatement = (OCBlockStatement)PsiTreeUtil.getTopmostParentOfType((PsiElement)e, (Class)OCBlockStatement.class);
        try {
            if (ocBlockStatement != null) {
                final Object parentOfType = ocBlockStatement;
                return (PsiElement)parentOfType;
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        final Object parentOfType = PsiTreeUtil.getParentOfType((PsiElement)e, new Class[] { OCCppNamespace.class, OCFile.class });
        return (PsiElement)parentOfType;
    }
    
    protected abstract OCBaseInplaceIntroducer<?, E> createIntroducer(final Project p0, final Editor p1, final E p2, final List<E> p3, final String p4);
    
    @NotNull
    protected abstract String getFeatureID();
    
    public void invoke(@NotNull final Project project, @NotNull final PsiElement[] array, final DataContext dataContext) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/refactoring/introduce/OCBaseIntroduceHandler", "invoke"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "elements", "com/jetbrains/cidr/lang/refactoring/introduce/OCBaseIntroduceHandler", "invoke"));
            }
        }
        catch (UnsupportedOperationException ex2) {
            throw b(ex2);
        }
        throw new UnsupportedOperationException("invoke is not implemented");
    }
    
    static {
        RANGER = (psiElement -> OCElementUtil.getRangeWithMacros(psiElement));
    }
    
    private static UnsupportedOperationException b(final UnsupportedOperationException ex) {
        return ex;
    }
}
