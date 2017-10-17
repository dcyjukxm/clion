// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger;

import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.search.PsiElementProcessor;
import com.jetbrains.cidr.lang.psi.OCParenthesizedExpression;
import com.jetbrains.cidr.lang.psi.OCCastExpression;
import com.jetbrains.cidr.lang.psi.OCLiteralExpression;
import com.intellij.psi.util.PsiElementFilter;
import com.jetbrains.cidr.lang.psi.OCArraySelectionExpression;
import com.jetbrains.cidr.lang.psi.OCCallExpression;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.psi.PsiReference;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCMacroSymbol;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.jetbrains.cidr.lang.psi.OCReference;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.util.Computable;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.Project;
import com.intellij.execution.ExecutionException;
import com.intellij.xdebugger.frame.XValue;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriver;
import com.intellij.xdebugger.XExpression;
import com.intellij.xdebugger.impl.breakpoints.XExpressionImpl;
import org.jetbrains.annotations.Nullable;
import com.intellij.xdebugger.XSourcePosition;
import com.intellij.xdebugger.evaluation.XDebuggerEvaluator;
import org.jetbrains.annotations.NotNull;

public class OCEvaluator extends CidrEvaluator
{
    public OCEvaluator(@NotNull final CidrStackFrame cidrStackFrame) {
        if (cidrStackFrame == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "frame", "com/jetbrains/cidr/execution/debugger/OCEvaluator", "<init>"));
        }
        super(cidrStackFrame);
    }
    
    public void evaluate(@NotNull final String s, @NotNull final XDebuggerEvaluator.XEvaluationCallback xEvaluationCallback, @Nullable final XSourcePosition xSourcePosition) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expression", "com/jetbrains/cidr/execution/debugger/OCEvaluator", "evaluate"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (xEvaluationCallback == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "callback", "com/jetbrains/cidr/execution/debugger/OCEvaluator", "evaluate"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        this.evaluate((XExpression)XExpressionImpl.fromText(s), xEvaluationCallback, xSourcePosition);
    }
    
    public void evaluate(@NotNull final XExpression xExpression, @NotNull final XDebuggerEvaluator.XEvaluationCallback xEvaluationCallback, @Nullable final XSourcePosition xSourcePosition) {
        try {
            if (xExpression == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expression", "com/jetbrains/cidr/execution/debugger/OCEvaluator", "evaluate"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (xEvaluationCallback == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "callback", "com/jetbrains/cidr/execution/debugger/OCEvaluator", "evaluate"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        this.myFrame.getProcess().postCommand(new CidrDebugProcess.DebuggerUIUpdateCommand() {
            @Override
            public void run(@NotNull final DebuggerDriver debuggerDriver) throws ExecutionException {
                try {
                    if (debuggerDriver == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "driver", "com/jetbrains/cidr/execution/debugger/OCEvaluator$1", "run"));
                    }
                }
                catch (DebuggerCommandException ex) {
                    throw b(ex);
                }
                XSourcePosition xSourcePosition = null;
                Label_0072: {
                    try {
                        if (xSourcePosition == null) {
                            xSourcePosition = OCEvaluator.this.myFrame.getSourcePosition();
                            break Label_0072;
                        }
                    }
                    catch (DebuggerCommandException ex2) {
                        throw b(ex2);
                    }
                    xSourcePosition = xSourcePosition;
                }
                final XSourcePosition xSourcePosition2 = xSourcePosition;
                try {
                    xEvaluationCallback.evaluated((XValue)OCEvaluator.this.doEvaluate(debuggerDriver, xSourcePosition2, xExpression));
                }
                catch (DebuggerCommandException ex3) {
                    xEvaluationCallback.errorOccurred(ex3.getMessage());
                }
                catch (ExecutionException ex4) {
                    xEvaluationCallback.errorOccurred(CidrDebuggerUtil.getExceptionMessage((Exception)ex4));
                    throw ex4;
                }
            }
            
            @Override
            public void rejected(@NotNull final String s) {
                try {
                    if (s == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "reason", "com/jetbrains/cidr/execution/debugger/OCEvaluator$1", "rejected"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                xEvaluationCallback.errorOccurred(s);
            }
            
            private static Exception b(final Exception ex) {
                return ex;
            }
        });
    }
    
    public TextRange getExpressionRangeAtOffset(final Project project, final Document document, final int n, final boolean b) {
        return (TextRange)ApplicationManager.getApplication().runReadAction((Computable)(() -> {
            final PsiFile psiFile = PsiDocumentManager.getInstance(project).getPsiFile(document);
            try {
                if (psiFile == null) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            final PsiElement a = a(psiFile, n, b);
            try {
                if (a == null) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            return a.getTextRange();
        }));
    }
    
    @Nullable
    private static PsiElement a(final PsiFile psiFile, final int n, final boolean b) {
        Object o = null;
        if (b) {
            PsiElement psiElement = psiFile.findElementAt(n);
            Label_0083: {
                Label_0059: {
                    Label_0038: {
                        try {
                            if (psiElement == null) {
                                break Label_0038;
                            }
                            final TokenSet set = OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET;
                            final PsiElement psiElement2 = psiElement;
                            final IElementType elementType = OCElementUtil.getElementType(psiElement2);
                            final boolean b2 = set.contains(elementType);
                            if (b2) {
                                break Label_0038;
                            }
                            break Label_0083;
                        }
                        catch (IllegalArgumentException ex) {
                            throw a(ex);
                        }
                        try {
                            final TokenSet set = OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET;
                            final PsiElement psiElement2 = psiElement;
                            final IElementType elementType = OCElementUtil.getElementType(psiElement2);
                            final boolean b2 = set.contains(elementType);
                            if (!b2) {
                                break Label_0083;
                            }
                            if (n <= 0) {
                                break Label_0059;
                            }
                        }
                        catch (IllegalArgumentException ex2) {
                            throw a(ex2);
                        }
                    }
                    psiElement = psiFile.findElementAt(n - 1);
                    try {
                        if (psiElement == null) {
                            break Label_0083;
                        }
                        final TokenSet set2 = OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET;
                        final PsiElement psiElement3 = psiElement;
                        final IElementType elementType2 = OCElementUtil.getElementType(psiElement3);
                        final boolean b3 = set2.contains(elementType2);
                        if (b3) {
                            break Label_0083;
                        }
                        break Label_0083;
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                }
                try {
                    final TokenSet set2 = OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET;
                    final PsiElement psiElement3 = psiElement;
                    final IElementType elementType2 = OCElementUtil.getElementType(psiElement3);
                    final boolean b3 = set2.contains(elementType2);
                    if (b3) {
                        return null;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            final PsiElement b4 = b(psiElement);
            if (b4 != null) {
                o = b4;
            }
            else {
                o = PsiTreeUtil.getParentOfType(psiElement, (Class)OCExpression.class, false);
                final PsiElement a = a((PsiElement)o);
                if (a != null) {
                    o = a;
                }
            }
        }
        else {
            final PsiReference reference = psiFile.findReferenceAt(n);
            if (reference instanceof OCReference) {
                o = PsiTreeUtil.getParentOfType(reference.getElement(), (Class)OCReferenceExpression.class, false);
                Label_0203: {
                    if (o != null) {
                        final OCSymbol resolveToSymbol = ((OCReferenceExpression)o).resolveToSymbol();
                        Label_0197: {
                            try {
                                if (resolveToSymbol == null) {
                                    break Label_0197;
                                }
                                final OCSymbol ocSymbol = resolveToSymbol;
                                final boolean b5 = ocSymbol instanceof OCMacroSymbol;
                                if (b5) {
                                    break Label_0197;
                                }
                                break Label_0203;
                            }
                            catch (IllegalArgumentException ex5) {
                                throw a(ex5);
                            }
                            try {
                                final OCSymbol ocSymbol = resolveToSymbol;
                                final boolean b5 = ocSymbol instanceof OCMacroSymbol;
                                if (b5) {
                                    return null;
                                }
                            }
                            catch (IllegalArgumentException ex6) {
                                throw a(ex6);
                            }
                        }
                    }
                    try {
                        if (a((PsiElement)o) != null) {
                            return null;
                        }
                    }
                    catch (IllegalArgumentException ex7) {
                        throw a(ex7);
                    }
                }
            }
        }
        if (o instanceof OCReferenceExpression) {
            final OCSymbol resolveToSymbol2 = ((OCReferenceExpression)o).resolveToSymbol();
            try {
                if (resolveToSymbol2 instanceof OCClassSymbol) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex8) {
                throw a(ex8);
            }
        }
        final PsiElement a2 = a((PsiElement)o, b);
        if (a2 != null) {
            o = a2;
        }
        return (PsiElement)o;
    }
    
    @Nullable
    private static PsiElement a(@Nullable final PsiElement psiElement) {
        if (psiElement instanceof OCReferenceExpression) {
            final PsiElement parent = psiElement.getParent();
            try {
                if (parent instanceof OCCallExpression) {
                    return parent;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        return null;
    }
    
    @Nullable
    private static PsiElement b(@Nullable final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnonnull       12
        //     4: aconst_null    
        //     5: goto            18
        //     8: invokestatic    com/jetbrains/cidr/execution/debugger/OCEvaluator.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    11: athrow         
        //    12: aload_0        
        //    13: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    18: astore_1       
        //    19: aload_1        
        //    20: ifnull          87
        //    23: aload_0        
        //    24: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
        //    29: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //    34: instanceof      Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    37: ifeq            67
        //    40: goto            47
        //    43: invokestatic    com/jetbrains/cidr/execution/debugger/OCEvaluator.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    46: athrow         
        //    47: aload_1        
        //    48: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMacroCall;
        //    51: ifeq            67
        //    54: goto            61
        //    57: invokestatic    com/jetbrains/cidr/execution/debugger/OCEvaluator.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    60: athrow         
        //    61: aload_1        
        //    62: areturn        
        //    63: invokestatic    com/jetbrains/cidr/execution/debugger/OCEvaluator.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    66: athrow         
        //    67: aload_1        
        //    68: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    73: astore_2       
        //    74: aload_2        
        //    75: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMacroCall;
        //    78: ifeq            87
        //    81: aload_2        
        //    82: areturn        
        //    83: invokestatic    com/jetbrains/cidr/execution/debugger/OCEvaluator.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    86: athrow         
        //    87: aconst_null    
        //    88: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      8      8      12     Ljava/lang/IllegalArgumentException;
        //  19     40     43     47     Ljava/lang/IllegalArgumentException;
        //  23     54     57     61     Ljava/lang/IllegalArgumentException;
        //  47     63     63     67     Ljava/lang/IllegalArgumentException;
        //  74     83     83     87     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0047:
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
    private static PsiElement a(@Nullable final PsiElement psiElement, final boolean b) {
        try {
            if (psiElement == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final PsiElement parent = psiElement.getParent();
        Object o = null;
        if (psiElement instanceof OCArraySelectionExpression) {
            o = psiElement;
        }
        else if (parent instanceof OCArraySelectionExpression) {
            o = parent;
        }
        try {
            if (o == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        if (!b) {
            final OCExpression indexExpression = ((OCArraySelectionExpression)o).getIndexExpression();
            final PsiElementProcessor.FindFilteredElement findFilteredElement = new PsiElementProcessor.FindFilteredElement((PsiElementFilter)new PsiElementFilter() {
                public boolean isAccepted(final PsiElement psiElement) {
                    return psiElement instanceof OCExpression && !(psiElement instanceof OCLiteralExpression) && !(psiElement instanceof OCReferenceExpression) && !(psiElement instanceof OCCastExpression) && !(psiElement instanceof OCParenthesizedExpression);
                }
            });
            try {
                PsiTreeUtil.processElements((PsiElement)indexExpression, (PsiElementProcessor)findFilteredElement);
                if (findFilteredElement.isFound()) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return (PsiElement)o;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
