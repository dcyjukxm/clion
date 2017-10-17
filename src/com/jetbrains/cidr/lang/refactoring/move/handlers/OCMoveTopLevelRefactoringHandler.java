// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.handlers;

import java.util.Set;
import com.jetbrains.cidr.lang.psi.OCCppNamespace;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.refactoring.move.ui.OCAbstractMoveDialog;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.util.TextRange;
import com.intellij.refactoring.util.CommonRefactoringUtil;
import com.intellij.openapi.editor.ScrollType;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.featureStatistics.FeatureUsageTracker;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.util.Condition;
import java.util.Collection;
import java.util.HashSet;
import java.util.Arrays;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.refactoring.lang.ElementsHandler;
import com.intellij.refactoring.RefactoringActionHandler;

public abstract class OCMoveTopLevelRefactoringHandler implements RefactoringActionHandler, ElementsHandler
{
    public void invoke(@NotNull final Project project, @NotNull final PsiElement[] array, final DataContext dataContext) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveTopLevelRefactoringHandler", "invoke"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "elements", "com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveTopLevelRefactoringHandler", "invoke"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        Editor editor = null;
        Label_0110: {
            try {
                if (dataContext != null) {
                    editor = (Editor)CommonDataKeys.EDITOR.getData(dataContext);
                    break Label_0110;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            editor = null;
        }
        this.showDialog(array[0], (Condition<PsiElement>)(psiElement -> set.contains(psiElement)), editor);
    }
    
    public void invoke(@NotNull final Project project, final Editor editor, final PsiFile psiFile, final DataContext dataContext) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveTopLevelRefactoringHandler", "invoke"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        FeatureUsageTracker.getInstance().triggerFeatureUsed("refactoring.moveTopLevel");
        PsiDocumentManager.getInstance(project).commitAllDocuments();
        final int offset = editor.getCaretModel().getOffset();
        editor.getScrollingModel().scrollToCaret(ScrollType.MAKE_VISIBLE);
        final PsiElement element = psiFile.findElementAt(offset);
        Label_0122: {
            try {
                if (element == null) {
                    return;
                }
                final Project project2 = project;
                final PsiElement psiElement = element;
                final boolean b = CommonRefactoringUtil.checkReadOnlyStatus(project2, psiElement);
                if (!b) {
                    return;
                }
                break Label_0122;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final Project project2 = project;
                final PsiElement psiElement = element;
                final boolean b = CommonRefactoringUtil.checkReadOnlyStatus(project2, psiElement);
                if (!b) {
                    return;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        final SelectionModel selectionModel = editor.getSelectionModel();
        if (selectionModel.hasSelection()) {
            this.showDialog(element, (Condition<PsiElement>)(p2 -> {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_2        
                //     1: ifnull          52
                //     4: aload_0        
                //     5: aload_2        
                //     6: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
                //    11: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
                //    14: ifeq            52
                //    17: goto            24
                //    20: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveTopLevelRefactoringHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    23: athrow         
                //    24: aload_1        
                //    25: aload_2        
                //    26: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
                //    31: invokevirtual   com/intellij/openapi/util/TextRange.contains:(Lcom/intellij/openapi/util/TextRange;)Z
                //    34: ifeq            52
                //    37: goto            44
                //    40: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveTopLevelRefactoringHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    43: athrow         
                //    44: iconst_1       
                //    45: goto            53
                //    48: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveTopLevelRefactoringHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    51: athrow         
                //    52: iconst_0       
                //    53: ireturn        
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                                
                //  -----  -----  -----  -----  ------------------------------------
                //  0      17     20     24     Ljava/lang/IllegalArgumentException;
                //  4      37     40     44     Ljava/lang/IllegalArgumentException;
                //  24     48     48     52     Ljava/lang/IllegalArgumentException;
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0024:
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
            }), editor);
        }
        else {
            this.showDialog(element, (Condition<PsiElement>)(p2 -> {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_2        
                //     1: ifnull          52
                //     4: aload_0        
                //     5: aload_2        
                //     6: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
                //    11: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
                //    14: ifeq            52
                //    17: goto            24
                //    20: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveTopLevelRefactoringHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    23: athrow         
                //    24: aload_2        
                //    25: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
                //    30: iload_1        
                //    31: invokevirtual   com/intellij/openapi/util/TextRange.containsOffset:(I)Z
                //    34: ifeq            52
                //    37: goto            44
                //    40: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveTopLevelRefactoringHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    43: athrow         
                //    44: iconst_1       
                //    45: goto            53
                //    48: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveTopLevelRefactoringHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    51: athrow         
                //    52: iconst_0       
                //    53: ireturn        
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                                
                //  -----  -----  -----  -----  ------------------------------------
                //  0      17     20     24     Ljava/lang/IllegalArgumentException;
                //  4      37     40     44     Ljava/lang/IllegalArgumentException;
                //  24     48     48     52     Ljava/lang/IllegalArgumentException;
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0024:
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
            }), editor);
        }
    }
    
    protected void showDialog(final PsiElement p0, final Condition<PsiElement> p1, final Editor p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokeinterface com/intellij/psi/PsiElement.getProject:()Lcom/intellij/openapi/project/Project;
        //     6: astore          4
        //     8: aload_1        
        //     9: invokestatic    com/jetbrains/cidr/lang/search/scopes/OCSearchScope.isInProjectSources:(Lcom/intellij/psi/PsiElement;)Z
        //    12: ifne            36
        //    15: aload           4
        //    17: aload_3        
        //    18: ldc             "error.out.of.project.element.default"
        //    20: invokestatic    com/intellij/refactoring/RefactoringBundle.message:(Ljava/lang/String;)Ljava/lang/String;
        //    23: aload_0        
        //    24: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveTopLevelRefactoringHandler.getTitle:()Ljava/lang/String;
        //    27: aconst_null    
        //    28: invokestatic    com/intellij/refactoring/util/CommonRefactoringUtil.showErrorHint:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/editor/Editor;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //    31: return         
        //    32: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveTopLevelRefactoringHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    35: athrow         
        //    36: aload_1        
        //    37: iconst_2       
        //    38: anewarray       Ljava/lang/Class;
        //    41: dup            
        //    42: iconst_0       
        //    43: ldc             Lcom/jetbrains/cidr/lang/psi/OCCppNamespace;.class
        //    45: aastore        
        //    46: dup            
        //    47: iconst_1       
        //    48: ldc             Lcom/jetbrains/cidr/lang/psi/OCFile;.class
        //    50: aastore        
        //    51: invokestatic    com/intellij/psi/util/PsiTreeUtil.getNonStrictParentOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //    54: checkcast       Lcom/intellij/psi/PsiNamedElement;
        //    57: astore          5
        //    59: aload           5
        //    61: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCppNamespace;
        //    64: ifeq            84
        //    67: aload_0        
        //    68: aload           5
        //    70: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCppNamespace;
        //    73: aload_2        
        //    74: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveTopLevelRefactoringHandler.createDialog:(Lcom/jetbrains/cidr/lang/psi/OCCppNamespace;Lcom/intellij/openapi/util/Condition;)Lcom/jetbrains/cidr/lang/refactoring/move/ui/OCAbstractMoveDialog;
        //    77: goto            94
        //    80: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveTopLevelRefactoringHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    83: athrow         
        //    84: aload_0        
        //    85: aload           5
        //    87: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    90: aload_2        
        //    91: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveTopLevelRefactoringHandler.createDialog:(Lcom/jetbrains/cidr/lang/psi/OCFile;Lcom/intellij/openapi/util/Condition;)Lcom/jetbrains/cidr/lang/refactoring/move/ui/OCAbstractMoveDialog;
        //    94: astore          6
        //    96: aload           6
        //    98: ifnonnull       106
        //   101: return         
        //   102: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveTopLevelRefactoringHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   105: athrow         
        //   106: aload           6
        //   108: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/ui/OCAbstractMoveDialog.allowsEmptySelection:()Z
        //   111: ifne            207
        //   114: aload           6
        //   116: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/ui/OCAbstractMoveDialog.getMemberInfos:()Ljava/util/List;
        //   119: invokeinterface java/util/List.isEmpty:()Z
        //   124: ifeq            207
        //   127: goto            134
        //   130: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveTopLevelRefactoringHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   133: athrow         
        //   134: new             Ljava/lang/StringBuilder;
        //   137: dup            
        //   138: invokespecial   java/lang/StringBuilder.<init>:()V
        //   141: aload           5
        //   143: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCppNamespace;
        //   146: ifeq            165
        //   149: goto            156
        //   152: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveTopLevelRefactoringHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   155: athrow         
        //   156: ldc             "Namespace \""
        //   158: goto            167
        //   161: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveTopLevelRefactoringHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   164: athrow         
        //   165: ldc             "File \""
        //   167: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   170: aload           5
        //   172: invokeinterface com/intellij/psi/PsiNamedElement.getName:()Ljava/lang/String;
        //   177: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   180: ldc             "\" has no members to move"
        //   182: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   185: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   188: invokestatic    com/intellij/refactoring/RefactoringBundle.getCannotRefactorMessage:(Ljava/lang/String;)Ljava/lang/String;
        //   191: astore          7
        //   193: aload           4
        //   195: aload_3        
        //   196: aload           7
        //   198: aload_0        
        //   199: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveTopLevelRefactoringHandler.getTitle:()Ljava/lang/String;
        //   202: aconst_null    
        //   203: invokestatic    com/intellij/refactoring/util/CommonRefactoringUtil.showErrorHint:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/editor/Editor;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   206: return         
        //   207: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //   210: invokeinterface com/intellij/openapi/application/Application.isUnitTestMode:()Z
        //   215: ifeq            246
        //   218: aload           6
        //   220: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/ui/OCAbstractMoveDialog.invokeRefactoring:()Lcom/intellij/openapi/util/Pair;
        //   223: astore          7
        //   225: aload_0        
        //   226: aload           7
        //   228: invokevirtual   com/intellij/openapi/util/Pair.getSecond:()Ljava/lang/Object;
        //   231: checkcast       Ljava/lang/String;
        //   234: aload           7
        //   236: invokevirtual   com/intellij/openapi/util/Pair.getFirst:()Ljava/lang/Object;
        //   239: checkcast       [Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   242: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveTopLevelRefactoringHandler.passToTest:(Ljava/lang/String;[Lcom/jetbrains/cidr/lang/psi/OCFile;)V
        //   245: return         
        //   246: aload           6
        //   248: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/ui/OCAbstractMoveDialog.show:()V
        //   251: return         
        //    Signature:
        //  (Lcom/intellij/psi/PsiElement;Lcom/intellij/openapi/util/Condition<Lcom/intellij/psi/PsiElement;>;Lcom/intellij/openapi/editor/Editor;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  8      32     32     36     Ljava/lang/IllegalArgumentException;
        //  59     80     80     84     Ljava/lang/IllegalArgumentException;
        //  96     102    102    106    Ljava/lang/IllegalArgumentException;
        //  106    127    130    134    Ljava/lang/IllegalArgumentException;
        //  114    149    152    156    Ljava/lang/IllegalArgumentException;
        //  134    161    161    165    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0134:
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
    
    protected void passToTest(final String s, final OCFile[] array) {
    }
    
    public boolean isEnabledOnElements(final PsiElement[] array) {
        return true;
    }
    
    protected abstract String getTitle();
    
    @Nullable
    protected abstract OCAbstractMoveDialog createDialog(final OCFile p0, final Condition<PsiElement> p1);
    
    @Nullable
    protected abstract OCAbstractMoveDialog createDialog(final OCCppNamespace p0, final Condition<PsiElement> p1);
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
