// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor;

import com.intellij.util.text.CharArrayUtil;
import java.awt.Component;
import com.intellij.openapi.application.ModalityState;
import com.intellij.openapi.util.Key;
import com.jetbrains.cidr.lang.editor.completion.SymbolLookupBuilderUtil;
import java.util.List;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.completion.CompletionProgressIndicator;
import com.intellij.codeInsight.completion.NextPrevParameterAction;
import com.intellij.codeInsight.completion.CodeCompletionHandlerBase;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.application.ApplicationManager;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Caret;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import com.intellij.codeInsight.editorActions.BaseEnterHandler;

public class OCSmartCompletionInPlaceholderEnterHandler extends BaseEnterHandler
{
    private final EditorActionHandler myOriginalHandler;
    
    public OCSmartCompletionInPlaceholderEnterHandler(final EditorActionHandler myOriginalHandler) {
        this.myOriginalHandler = myOriginalHandler;
    }
    
    public boolean isEnabledForCaret(@NotNull final Editor editor, @NotNull final Caret caret, final DataContext dataContext) {
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/editor/OCSmartCompletionInPlaceholderEnterHandler", "isEnabledForCaret"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (caret == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "caret", "com/jetbrains/cidr/lang/editor/OCSmartCompletionInPlaceholderEnterHandler", "isEnabledForCaret"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return this.myOriginalHandler.isEnabled(editor, caret, dataContext);
    }
    
    public void executeWriteAction(final Editor editor, final Caret caret, final DataContext dataContext) {
        final PsiFile psiFile = (PsiFile)CommonDataKeys.PSI_FILE.getData(dataContext);
        if (psiFile instanceof OCFile) {
            final Project project = psiFile.getProject();
            try {
                if (b(editor)) {
                    ApplicationManager.getApplication().invokeLater((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            new CodeCompletionHandlerBase(CompletionType.SMART, false, false, true) {
                                final /* synthetic */ OCSmartCompletionInPlaceholderEnterHandler$1 this$1;
                                
                                private void b() {
                                    Label_0033: {
                                        try {
                                            if (b(editor)) {
                                                return;
                                            }
                                            final CodeCompletionHandlerBase codeCompletionHandlerBase = this;
                                            final Runnable runnable = codeCompletionHandlerBase.this$1;
                                            final Editor editor = editor;
                                            final boolean b = a(editor);
                                            if (b) {
                                                break Label_0033;
                                            }
                                            return;
                                        }
                                        catch (IllegalArgumentException ex) {
                                            throw a(ex);
                                        }
                                        try {
                                            final CodeCompletionHandlerBase codeCompletionHandlerBase = this;
                                            final Runnable runnable = codeCompletionHandlerBase.this$1;
                                            final Editor editor = editor;
                                            final boolean b = a(editor);
                                            if (b) {
                                                new NextPrevParameterAction.Next().getHandler().invoke(project, editor, psiFile);
                                            }
                                        }
                                        catch (IllegalArgumentException ex2) {
                                            throw a(ex2);
                                        }
                                    }
                                }
                                
                                @Override
                                protected void lookupItemSelected(final CompletionProgressIndicator completionProgressIndicator, @NotNull final LookupElement lookupElement, final char c, final List<LookupElement> list) {
                                    try {
                                        if (lookupElement == null) {
                                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "item", "com/jetbrains/cidr/lang/editor/OCSmartCompletionInPlaceholderEnterHandler$1$1", "lookupItemSelected"));
                                        }
                                    }
                                    catch (IllegalArgumentException ex) {
                                        throw a(ex);
                                    }
                                    try {
                                        super.lookupItemSelected(completionProgressIndicator, lookupElement, c, list);
                                        if (lookupElement.getUserData((Key)SymbolLookupBuilderUtil.DONT_GO_NEXT_TEMPLATE) != Boolean.TRUE) {
                                            this.b();
                                        }
                                    }
                                    catch (IllegalArgumentException ex2) {
                                        throw a(ex2);
                                    }
                                }
                                
                                @Override
                                protected void completionFinished(final CompletionProgressIndicator completionProgressIndicator, final boolean b) {
                                    super.completionFinished(completionProgressIndicator, b);
                                    this.b();
                                }
                                
                                private static IllegalArgumentException a(final IllegalArgumentException ex) {
                                    return ex;
                                }
                            }.invokeCompletion(project, editor, 1, true, false);
                        }
                    }, ModalityState.stateForComponent((Component)editor.getComponent()));
                    return;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        this.myOriginalHandler.execute(editor, caret, dataContext);
    }
    
    private static boolean a(final Editor editor) {
        final int offset = editor.getCaretModel().getOffset();
        final CharSequence charsSequence = editor.getDocument().getCharsSequence();
        try {
            if (CharArrayUtil.indexOf(charsSequence, (CharSequence)"<#", offset) > 0) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    private static boolean b(final Editor p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokeinterface com/intellij/openapi/editor/Editor.getSelectionModel:()Lcom/intellij/openapi/editor/SelectionModel;
        //     6: invokeinterface com/intellij/openapi/editor/SelectionModel.getSelectedText:()Ljava/lang/String;
        //    11: astore_1       
        //    12: aload_1        
        //    13: ifnull          56
        //    16: aload_1        
        //    17: ldc             "<#"
        //    19: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //    22: ifeq            56
        //    25: goto            32
        //    28: invokestatic    com/jetbrains/cidr/lang/editor/OCSmartCompletionInPlaceholderEnterHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    31: athrow         
        //    32: aload_1        
        //    33: ldc             "#>"
        //    35: invokevirtual   java/lang/String.endsWith:(Ljava/lang/String;)Z
        //    38: ifeq            56
        //    41: goto            48
        //    44: invokestatic    com/jetbrains/cidr/lang/editor/OCSmartCompletionInPlaceholderEnterHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    47: athrow         
        //    48: iconst_1       
        //    49: goto            57
        //    52: invokestatic    com/jetbrains/cidr/lang/editor/OCSmartCompletionInPlaceholderEnterHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    55: athrow         
        //    56: iconst_0       
        //    57: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  12     25     28     32     Ljava/lang/IllegalArgumentException;
        //  16     41     44     48     Ljava/lang/IllegalArgumentException;
        //  32     52     52     56     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0032:
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
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
