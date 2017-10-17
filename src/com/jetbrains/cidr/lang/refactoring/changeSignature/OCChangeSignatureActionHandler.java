// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature;

import com.intellij.openapi.ui.Messages;
import com.jetbrains.cidr.lang.search.OCSearchUtil;
import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.intellij.openapi.application.ApplicationManager;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.refactoring.RefactoringBundle;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.intellij.refactoring.util.CommonRefactoringUtil;
import com.jetbrains.cidr.lang.resolve.OCResolveOverloadsUtil;
import com.jetbrains.cidr.lang.symbols.OCSymbolHolderVirtualPsiElement;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.OCBundle;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.ScrollType;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.openapi.actionSystem.DataContext;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.actionSystem.DataKey;
import com.intellij.refactoring.changeSignature.ChangeSignatureHandler;

public class OCChangeSignatureActionHandler implements ChangeSignatureHandler
{
    public static final DataKey<OCCallableKind> CALLABLE_KIND;
    
    public PsiElement findTargetMember(final PsiFile psiFile, final Editor editor) {
        return OCElementUtil.findRenameTargetDefinition(psiFile.findElementAt(editor.getCaretModel().getOffset()), true);
    }
    
    public PsiElement findTargetMember(final PsiElement psiElement) {
        return OCElementUtil.findRenameTargetDefinition(psiElement, true);
    }
    
    public void invoke(@NotNull final Project project, final Editor editor, final PsiFile psiFile, final DataContext dataContext) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureActionHandler", "invoke"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        PsiDocumentManager.getInstance(project).commitAllDocuments();
        editor.getScrollingModel().scrollToCaret(ScrollType.MAKE_VISIBLE);
        a(project, editor, this.findTargetMember(psiFile, editor), (OCCallableKind)OCChangeSignatureActionHandler.CALLABLE_KIND.getData(dataContext));
    }
    
    public void invoke(@NotNull final Project project, @NotNull final PsiElement[] array, final DataContext dataContext) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureActionHandler", "invoke"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "elements", "com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureActionHandler", "invoke"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        a(project, (Editor)CommonDataKeys.EDITOR.getData(dataContext), array[0], (OCCallableKind)OCChangeSignatureActionHandler.CALLABLE_KIND.getData(dataContext));
    }
    
    @Nullable
    public String getTargetNotFoundMessage() {
        return OCBundle.message("changeSignature.targetNotFound", new Object[0]);
    }
    
    private static void a(final Project project, final Editor editor, final PsiElement psiElement, final OCCallableKind callableKind) {
        if (psiElement instanceof OCSymbolHolderVirtualPsiElement) {
            final OCSymbol symbol = ((OCSymbolHolderVirtualPsiElement)psiElement).getSymbol();
            try {
                if (symbol instanceof OCResolveOverloadsUtil.OCFunctionGroupSymbol) {
                    CommonRefactoringUtil.showErrorHint(project, editor, OCBundle.message("refactoring.ambiguous", new Object[0]), OCChangeSignatureActionHandler.REFACTORING_NAME, (String)null);
                    return;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        try {
            if (!(psiElement instanceof OCCallable)) {
                CommonRefactoringUtil.showErrorHint(project, editor, OCBundle.message("changeSignature.targetNotFound", new Object[0]), OCChangeSignatureActionHandler.REFACTORING_NAME, (String)null);
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (!OCCodeInsightUtil.isValid(psiElement)) {
                CommonRefactoringUtil.showErrorHint(project, editor, RefactoringBundle.message("error.out.of.project.element.default"), OCChangeSignatureActionHandler.REFACTORING_NAME, (String)null);
                return;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final OCChangeSignatureHandler handler = getHandler((OCCallable)psiElement, true);
        try {
            if (checkVariableArguments(project, editor, handler)) {
                return;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        Label_0192: {
            Label_0151: {
                try {
                    if (callableKind == null) {
                        break Label_0192;
                    }
                    final PsiElement psiElement2 = psiElement;
                    final OCCallable ocCallable = (OCCallable)psiElement2;
                    final OCBlockStatement ocBlockStatement = ocCallable.getBody();
                    if (ocBlockStatement == null) {
                        break Label_0151;
                    }
                    break Label_0151;
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
                try {
                    final PsiElement psiElement2 = psiElement;
                    final OCCallable ocCallable = (OCCallable)psiElement2;
                    final OCBlockStatement ocBlockStatement = ocCallable.getBody();
                    if (ocBlockStatement == null) {
                        CommonRefactoringUtil.showErrorHint(project, editor, "Select the implementation to convert to " + callableKind, OCChangeSignatureActionHandler.REFACTORING_NAME, (String)null);
                        return;
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
            }
            handler.setCallableKind(callableKind);
        }
        handler.invoke();
    }
    
    public static boolean checkVariableArguments(final Project p0, final Editor p1, final OCChangeSignatureHandler p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: invokeinterface com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandler.getMethodDescriptor:()Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCMethodDescriptor;
        //     6: astore_3       
        //     7: aload_3        
        //     8: ifnonnull       17
        //    11: iconst_0       
        //    12: ireturn        
        //    13: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureActionHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    16: athrow         
        //    17: aload_3        
        //    18: invokevirtual   com/jetbrains/cidr/lang/refactoring/changeSignature/OCMethodDescriptor.getMethodSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    21: astore          4
        //    23: aload           4
        //    25: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //    28: ifeq            51
        //    31: aload           4
        //    33: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //    36: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.isVararg:()Z
        //    41: ifne            84
        //    44: goto            51
        //    47: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureActionHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    50: athrow         
        //    51: aload           4
        //    53: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //    56: ifeq            132
        //    59: goto            66
        //    62: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureActionHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    65: athrow         
        //    66: aload           4
        //    68: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //    71: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isVararg:()Z
        //    74: ifeq            132
        //    77: goto            84
        //    80: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureActionHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    83: athrow         
        //    84: new             Ljava/lang/StringBuilder;
        //    87: dup            
        //    88: invokespecial   java/lang/StringBuilder.<init>:()V
        //    91: ldc             "Can't refactor a "
        //    93: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    96: aload           4
        //    98: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   103: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.getNameLowercase:()Ljava/lang/String;
        //   106: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   109: ldc             " with variable arguments"
        //   111: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   114: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   117: astore          5
        //   119: aload_0        
        //   120: aload_1        
        //   121: aload           5
        //   123: getstatic       com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureActionHandler.REFACTORING_NAME:Ljava/lang/String;
        //   126: aconst_null    
        //   127: invokestatic    com/intellij/refactoring/util/CommonRefactoringUtil.showErrorHint:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/editor/Editor;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   130: iconst_1       
        //   131: ireturn        
        //   132: iconst_0       
        //   133: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  7      13     13     17     Ljava/lang/IllegalArgumentException;
        //  23     44     47     51     Ljava/lang/IllegalArgumentException;
        //  31     59     62     66     Ljava/lang/IllegalArgumentException;
        //  51     77     80     84     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0051:
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
    
    public static OCChangeSignatureHandler getHandler(final OCCallable ocCallable, final PsiElement psiElement) {
        return a(ocCallable, psiElement, false, ApplicationManager.getApplication().isUnitTestMode());
    }
    
    public static OCChangeSignatureHandler getHandler(final OCCallable ocCallable, final boolean b) {
        return a(ocCallable, (PsiElement)ocCallable, b, ApplicationManager.getApplication().isUnitTestMode());
    }
    
    public static OCChangeSignatureHandler getHandler(final OCCallable ocCallable, final PsiElement psiElement, final boolean b) {
        return a(ocCallable, psiElement, false, b);
    }
    
    private static OCChangeSignatureHandler a(final OCCallable ocCallable, final PsiElement psiElement, final boolean b, final boolean b2) {
        try {
            if (!b2) {
                final boolean b3 = true;
                return getHandler(ocCallable, psiElement, b, b2, b3, false);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final boolean b3 = false;
        return getHandler(ocCallable, psiElement, b, b2, b3, false);
    }
    
    public static OCChangeSignatureHandler getHandler(final OCCallable ocCallable, final PsiElement psiElement, boolean changeCallableKindPossible, final boolean b, final boolean b2, final boolean sureIsConstructor) {
        final OCSymbolWithParent symbol = ocCallable.getSymbol();
        final Project project = ocCallable.getProject();
        boolean changeAncestors = false;
        Label_0316: {
            Label_0296: {
                if (symbol instanceof OCSymbolWithParent) {
                    final OCSearchUtil.Ancestor someAncestor = OCSearchUtil.findSomeAncestor(symbol);
                    if (someAncestor != null) {
                        final String nameLowercase = symbol.getKind().getNameLowercase();
                        StringBuilder append = null;
                        String s = null;
                        Label_0123: {
                            try {
                                append = new StringBuilder().append(symbol.getNameWithKindUppercase()).append(" overrides ").append(nameLowercase).append(" in ").append(someAncestor.getSymbol().getParent().getNameWithKindLowercase());
                                if (someAncestor.isOutOfProject()) {
                                    s = " which is out of project";
                                    break Label_0123;
                                }
                            }
                            catch (IllegalArgumentException ex) {
                                throw a(ex);
                            }
                            s = "";
                        }
                        final String string = append.append(s).toString();
                        Label_0199: {
                            try {
                                if (!someAncestor.isOutOfProject()) {
                                    break Label_0199;
                                }
                                if (!b2) {
                                    break Label_0296;
                                }
                            }
                            catch (IllegalArgumentException ex2) {
                                throw a(ex2);
                            }
                            final int showYesNoDialog = Messages.showYesNoDialog(project, string + ". Do you want to proceed the refactoring?", "Warning", Messages.getQuestionIcon());
                            try {
                                if (showYesNoDialog != 0) {
                                    return OCEmptyChangeSignatureHandler.INSTANCE;
                                }
                            }
                            catch (IllegalArgumentException ex3) {
                                throw a(ex3);
                            }
                            break Label_0296;
                        }
                        int showYesNoCancelDialog;
                        if (b2) {
                            showYesNoCancelDialog = Messages.showYesNoCancelDialog(project, string + ". Do you want to refactor the base " + nameLowercase + "s?", "Warning", Messages.getQuestionIcon());
                        }
                        else {
                            int n = 0;
                            Label_0269: {
                                try {
                                    if (ApplicationManager.getApplication().isUnitTestMode()) {
                                        n = 0;
                                        break Label_0269;
                                    }
                                }
                                catch (IllegalArgumentException ex4) {
                                    throw a(ex4);
                                }
                                n = 1;
                            }
                            showYesNoCancelDialog = n;
                        }
                        if (showYesNoCancelDialog == 0) {
                            changeAncestors = true;
                        }
                        else {
                            try {
                                if (showYesNoCancelDialog != 1) {
                                    return OCEmptyChangeSignatureHandler.INSTANCE;
                                }
                            }
                            catch (IllegalArgumentException ex5) {
                                throw a(ex5);
                            }
                        }
                    }
                }
                try {
                    if (!CommonRefactoringUtil.checkReadOnlyStatus(project, (PsiElement)ocCallable)) {
                        break Label_0316;
                    }
                    final PsiElement psiElement2 = psiElement;
                    if (psiElement2 == null) {
                        break Label_0316;
                    }
                    break Label_0316;
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
            }
            try {
                final PsiElement psiElement2 = psiElement;
                if (psiElement2 == null) {
                    return OCEmptyChangeSignatureHandler.INSTANCE;
                }
            }
            catch (IllegalArgumentException ex7) {
                throw a(ex7);
            }
        }
        if (!ocCallable.getContainingOCFile().getKind().isObjC()) {
            changeCallableKindPossible = false;
        }
        final OCMethodDescriptor methodDescriptor = OCMethodDescriptor.createMethodDescriptor(ocCallable);
        methodDescriptor.setChangeCallableKindPossible(changeCallableKindPossible);
        methodDescriptor.setSureIsConstructor(sureIsConstructor);
        OCChangeSignatureHandlerImpl handler;
        if (b) {
            handler = new OCChangeSignatureHandlerImpl(methodDescriptor, psiElement);
        }
        else {
            handler = new OCChangeSignatureDialog(project, methodDescriptor, psiElement).getHandler();
        }
        handler.setChangeAncestors(changeAncestors);
        return handler;
    }
    
    static {
        CALLABLE_KIND = DataKey.create("callable_kind");
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
