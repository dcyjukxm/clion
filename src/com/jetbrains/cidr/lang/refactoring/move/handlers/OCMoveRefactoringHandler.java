// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.handlers;

import java.util.Set;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.util.Pair;
import com.jetbrains.cidr.lang.refactoring.move.ui.OCAbstractMoveDialog;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.util.Disposer;
import com.jetbrains.cidr.lang.psi.OCClassDeclarationBase;
import com.jetbrains.cidr.lang.symbols.objc.OCInterfaceSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import com.intellij.openapi.editor.SelectionModel;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;
import com.intellij.openapi.editor.ScrollType;
import com.intellij.featureStatistics.FeatureUsageTracker;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.util.Condition;
import java.util.Collection;
import java.util.HashSet;
import java.util.Arrays;
import com.intellij.refactoring.util.CommonRefactoringUtil;
import com.intellij.refactoring.RefactoringBundle;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCStruct;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.refactoring.lang.ElementsHandler;
import com.intellij.refactoring.RefactoringActionHandler;

public abstract class OCMoveRefactoringHandler implements RefactoringActionHandler, ElementsHandler
{
    public void invoke(@NotNull final Project project, @NotNull final PsiElement[] array, final DataContext dataContext) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveRefactoringHandler", "invoke"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "elements", "com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveRefactoringHandler", "invoke"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        Editor editor = null;
        Label_0117: {
            try {
                PsiDocumentManager.getInstance(project).commitAllDocuments();
                if (dataContext != null) {
                    editor = (Editor)CommonDataKeys.EDITOR.getData(dataContext);
                    break Label_0117;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            editor = null;
        }
        final Editor editor2 = editor;
        final OCSymbolDeclarator ocSymbolDeclarator = (OCSymbolDeclarator)PsiTreeUtil.getNonStrictParentOfType(array[0], new Class[] { OCClassDeclaration.class, OCStruct.class });
        if (ocSymbolDeclarator == null) {
            CommonRefactoringUtil.showErrorHint(project, editor2, RefactoringBundle.getCannotRefactorMessage(getInvalidPositionMessage()), this.getTitle(), (String)null);
            return;
        }
        this.showDialog(ocSymbolDeclarator, (Condition<PsiElement>)(psiElement -> set.contains(psiElement)), editor2);
    }
    
    public void invoke(@NotNull final Project project, final Editor editor, final PsiFile psiFile, final DataContext dataContext) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveRefactoringHandler", "invoke"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        FeatureUsageTracker.getInstance().triggerFeatureUsed(this.getFeatureID());
        PsiDocumentManager.getInstance(project).commitAllDocuments();
        final int offset = editor.getCaretModel().getOffset();
        editor.getScrollingModel().scrollToCaret(ScrollType.MAKE_VISIBLE);
        Object o = psiFile.findElementAt(offset);
        while (true) {
            Label_0118: {
                Label_0139: {
                    try {
                        if (o != null) {
                            if (!(o instanceof PsiFile)) {
                                break Label_0139;
                            }
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    break Label_0118;
                    try {
                        if (!CommonRefactoringUtil.checkReadOnlyStatus(project, (PsiElement)o)) {
                            return;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                }
                Label_0181: {
                    try {
                        if (this.acceptsElement((PsiElement)o)) {
                            break Label_0181;
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                    o = ((PsiElement)o).getParent();
                    continue;
                }
                OCSymbolDeclarator ocSymbolDeclarator = (OCSymbolDeclarator)PsiTreeUtil.getNonStrictParentOfType((PsiElement)o, new Class[] { OCClassDeclaration.class, OCStruct.class });
                Label_0305: {
                    try {
                        if (ocSymbolDeclarator != null || !(o instanceof OCFunctionDeclaration)) {
                            break Label_0305;
                        }
                    }
                    catch (IllegalArgumentException ex5) {
                        throw a(ex5);
                    }
                    final OCSymbolWithQualifiedName symbol = ((OCFunctionDeclaration)o).getSymbol();
                    OCSymbol<OCFunctionDeclaration> associatedSymbol = null;
                    Label_0257: {
                        try {
                            if (symbol != null) {
                                associatedSymbol = (OCSymbol<OCFunctionDeclaration>)symbol.getAssociatedSymbol();
                                break Label_0257;
                            }
                        }
                        catch (IllegalArgumentException ex6) {
                            throw a(ex6);
                        }
                        associatedSymbol = null;
                    }
                    final OCSymbol<OCFunctionDeclaration> ocSymbol = associatedSymbol;
                    OCFunctionDeclaration locateDefinition = null;
                    Label_0279: {
                        try {
                            if (ocSymbol != null) {
                                locateDefinition = ocSymbol.locateDefinition();
                                break Label_0279;
                            }
                        }
                        catch (IllegalArgumentException ex7) {
                            throw a(ex7);
                        }
                        locateDefinition = null;
                    }
                    o = locateDefinition;
                    ocSymbolDeclarator = (OCSymbolDeclarator)PsiTreeUtil.getNonStrictParentOfType((PsiElement)o, new Class[] { OCClassDeclaration.class, OCStruct.class });
                }
                if (ocSymbolDeclarator == null) {
                    CommonRefactoringUtil.showErrorHint(project, editor, RefactoringBundle.getCannotRefactorMessage(getInvalidPositionMessage()), this.getTitle(), (String)null);
                    return;
                }
                final SelectionModel selectionModel = editor.getSelectionModel();
                if (selectionModel.hasSelection()) {
                    this.showDialog(ocSymbolDeclarator, (Condition<PsiElement>)(p2 -> {
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
                        //    20: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveRefactoringHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                        //    23: athrow         
                        //    24: aload_1        
                        //    25: aload_2        
                        //    26: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
                        //    31: invokevirtual   com/intellij/openapi/util/TextRange.contains:(Lcom/intellij/openapi/util/TextRange;)Z
                        //    34: ifeq            52
                        //    37: goto            44
                        //    40: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveRefactoringHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                        //    43: athrow         
                        //    44: iconst_1       
                        //    45: goto            53
                        //    48: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveRefactoringHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
                    this.showDialog(ocSymbolDeclarator, (Condition<PsiElement>)(psiElement2 -> {
                        try {
                            if (psiElement2 == o) {
                                return true;
                            }
                        }
                        catch (IllegalArgumentException ex) {
                            throw a(ex);
                        }
                        return false;
                    }), editor);
                }
                return;
            }
            CommonRefactoringUtil.showErrorHint(project, editor, RefactoringBundle.getCannotRefactorMessage(getInvalidPositionMessage()), this.getTitle(), (String)null);
        }
    }
    
    protected boolean acceptsElement(final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //     4: ifeq            84
        //     7: aload_1        
        //     8: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //    11: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    16: astore_2       
        //    17: aload_2        
        //    18: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //    21: ifne            74
        //    24: aload_2        
        //    25: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //    28: ifne            74
        //    31: goto            38
        //    34: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveRefactoringHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    37: athrow         
        //    38: aload_2        
        //    39: instanceof      Lcom/jetbrains/cidr/lang/symbols/OCSymbolWithParent;
        //    42: ifeq            82
        //    45: goto            52
        //    48: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveRefactoringHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    51: athrow         
        //    52: aload_2        
        //    53: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbolWithParent;
        //    56: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbolWithParent.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    61: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //    64: ifeq            82
        //    67: goto            74
        //    70: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveRefactoringHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    73: athrow         
        //    74: iconst_1       
        //    75: goto            83
        //    78: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveRefactoringHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    81: athrow         
        //    82: iconst_0       
        //    83: ireturn        
        //    84: aload_1        
        //    85: instanceof      Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
        //    88: ifne            147
        //    91: aload_1        
        //    92: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //    95: ifne            147
        //    98: goto            105
        //   101: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveRefactoringHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   104: athrow         
        //   105: aload_1        
        //   106: instanceof      Lcom/jetbrains/cidr/lang/psi/OCProperty;
        //   109: ifne            147
        //   112: goto            119
        //   115: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveRefactoringHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   118: athrow         
        //   119: aload_1        
        //   120: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStruct;
        //   123: ifne            147
        //   126: goto            133
        //   129: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveRefactoringHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   132: athrow         
        //   133: aload_1        
        //   134: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //   137: ifeq            155
        //   140: goto            147
        //   143: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveRefactoringHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   146: athrow         
        //   147: iconst_1       
        //   148: goto            156
        //   151: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCMoveRefactoringHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   154: athrow         
        //   155: iconst_0       
        //   156: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  17     31     34     38     Ljava/lang/IllegalArgumentException;
        //  24     45     48     52     Ljava/lang/IllegalArgumentException;
        //  38     67     70     74     Ljava/lang/IllegalArgumentException;
        //  52     78     78     82     Ljava/lang/IllegalArgumentException;
        //  84     98     101    105    Ljava/lang/IllegalArgumentException;
        //  91     112    115    119    Ljava/lang/IllegalArgumentException;
        //  105    126    129    133    Ljava/lang/IllegalArgumentException;
        //  119    140    143    147    Ljava/lang/IllegalArgumentException;
        //  133    151    151    155    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0038:
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
    
    protected void showDialog(OCSymbolDeclarator o, final Condition<PsiElement> condition, final Editor editor) {
        final Project project = ((OCSymbolDeclarator)o).getProject();
        OCInterfaceSymbol symbol = ((OCSymbolDeclarator<OCInterfaceSymbol>)o).getSymbol();
        try {
            if (!OCSearchScope.isInProjectSources((PsiElement)o)) {
                CommonRefactoringUtil.showErrorHint(project, editor, RefactoringBundle.message("error.out.of.project.element.default"), this.getTitle(), (String)null);
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        if (symbol == null) {
            CommonRefactoringUtil.showErrorHint(project, editor, RefactoringBundle.getCannotRefactorMessage("Can't find the symbol for the selected class"), this.getTitle(), (String)null);
            return;
        }
        Label_0186: {
            if (symbol instanceof OCClassSymbol) {
                final OCInterfaceSymbol ocInterfaceSymbol = symbol;
                OCInterfaceSymbol ocInterfaceSymbol2 = null;
                Label_0121: {
                    try {
                        if ("".equals(ocInterfaceSymbol.getCategoryName())) {
                            ocInterfaceSymbol2 = ocInterfaceSymbol.getMainInterface();
                            break Label_0121;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    ocInterfaceSymbol2 = ocInterfaceSymbol.getInterface();
                }
                final OCInterfaceSymbol ocInterfaceSymbol3 = ocInterfaceSymbol2;
                Object o2 = null;
                Label_0160: {
                    Label_0142: {
                        try {
                            if (ocInterfaceSymbol3 == symbol) {
                                break Label_0186;
                            }
                            final OCInterfaceSymbol ocInterfaceSymbol4 = ocInterfaceSymbol3;
                            if (ocInterfaceSymbol4 != null) {
                                break Label_0142;
                            }
                            break Label_0142;
                        }
                        catch (IllegalArgumentException ex3) {
                            throw a(ex3);
                        }
                        try {
                            final OCInterfaceSymbol ocInterfaceSymbol4 = ocInterfaceSymbol3;
                            if (ocInterfaceSymbol4 != null) {
                                o2 = ocInterfaceSymbol3.locateDefinition();
                                break Label_0160;
                            }
                        }
                        catch (IllegalArgumentException ex4) {
                            throw a(ex4);
                        }
                    }
                    o2 = null;
                }
                final PsiElement psiElement = (PsiElement)o2;
                try {
                    if (ocInterfaceSymbol3 == null || psiElement == null) {
                        break Label_0186;
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
                symbol = ocInterfaceSymbol3;
                o = psiElement;
            }
        }
        final OCAbstractMoveDialog dialog = this.createDialog((OCSymbolDeclarator)o, symbol, condition, editor);
        try {
            if (dialog == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        Label_0287: {
            try {
                if (dialog.allowsEmptySelection() || !dialog.getMemberInfos().isEmpty()) {
                    break Label_0287;
                }
            }
            catch (IllegalArgumentException ex7) {
                throw a(ex7);
            }
            CommonRefactoringUtil.showErrorHint(project, editor, RefactoringBundle.getCannotRefactorMessage(symbol.getNameWithKindUppercase() + " has no members to move"), this.getTitle(), (String)null);
            Disposer.dispose(dialog.getDisposable());
            return;
        }
        if (ApplicationManager.getApplication().isUnitTestMode()) {
            try {
                if (dialog.checkConflicts()) {
                    final Pair<OCFile[], String> invokeRefactoring = dialog.invokeRefactoring();
                    this.passToTest((String)invokeRefactoring.getSecond(), (OCFile[])invokeRefactoring.getFirst());
                }
                return;
            }
            finally {
                Disposer.dispose(dialog.getDisposable());
            }
        }
        dialog.show();
    }
    
    protected void passToTest(final String s, final OCFile[] array) {
    }
    
    public boolean isEnabledOnElements(final PsiElement[] array) {
        for (final PsiElement psiElement : array) {
            try {
                if (!this.acceptsElement(psiElement)) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        return true;
    }
    
    protected static String getInvalidPositionMessage() {
        return "The caret should be positioned inside a class";
    }
    
    protected abstract String getTitle();
    
    @Nullable
    protected abstract OCAbstractMoveDialog createDialog(final OCSymbolDeclarator p0, final OCSymbol p1, final Condition<PsiElement> p2, final Editor p3);
    
    @NotNull
    protected abstract String getFeatureID();
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
