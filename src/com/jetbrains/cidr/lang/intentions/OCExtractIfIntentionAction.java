// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.intentions;

import com.jetbrains.cidr.lang.util.OCElementFactory;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.IncorrectOperationException;

public class OCExtractIfIntentionAction extends OCConvertToIfIntentionActionBase
{
    @NotNull
    public String getFamilyName() {
        String s;
        try {
            s = "Extract 'if'";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCExtractIfIntentionAction", "getFamilyName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return s;
    }
    
    public boolean isAvailable(@NotNull final Project project, final Editor editor, @NotNull final PsiElement psiElement) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/intentions/OCExtractIfIntentionAction", "isAvailable"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/intentions/OCExtractIfIntentionAction", "isAvailable"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        final OCExpression a = a(editor, psiElement);
        try {
            if (a != null) {
                this.setText("Extract 'if (" + a.getTextWithMacros() + ")'");
                return true;
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        return false;
    }
    
    @Nullable
    private static OCExpression a(final Editor p0, final PsiElement p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //     6: astore_2       
        //     7: aload_2        
        //     8: ifnull          145
        //    11: aload_1        
        //    12: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    15: ifeq            133
        //    18: goto            25
        //    21: invokestatic    com/jetbrains/cidr/lang/intentions/OCExtractIfIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    24: athrow         
        //    25: aload_2        
        //    26: instanceof      Lcom/jetbrains/cidr/lang/psi/OCBinaryExpression;
        //    29: ifeq            133
        //    32: goto            39
        //    35: invokestatic    com/jetbrains/cidr/lang/intentions/OCExtractIfIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    38: athrow         
        //    39: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LOGIC_OPERATIONS:Lcom/intellij/psi/tree/TokenSet;
        //    42: aload_2        
        //    43: checkcast       Lcom/jetbrains/cidr/lang/psi/OCBinaryExpression;
        //    46: invokeinterface com/jetbrains/cidr/lang/psi/OCBinaryExpression.getOperationSign:()Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    51: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //    54: ifeq            133
        //    57: goto            64
        //    60: invokestatic    com/jetbrains/cidr/lang/intentions/OCExtractIfIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    63: athrow         
        //    64: aload_1        
        //    65: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //    70: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //    73: aload_0        
        //    74: invokeinterface com/intellij/openapi/editor/Editor.getSelectionModel:()Lcom/intellij/openapi/editor/SelectionModel;
        //    79: invokeinterface com/intellij/openapi/editor/SelectionModel.getSelectionStart:()I
        //    84: if_icmpgt       133
        //    87: goto            94
        //    90: invokestatic    com/jetbrains/cidr/lang/intentions/OCExtractIfIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    93: athrow         
        //    94: aload_1        
        //    95: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   100: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   103: aload_0        
        //   104: invokeinterface com/intellij/openapi/editor/Editor.getSelectionModel:()Lcom/intellij/openapi/editor/SelectionModel;
        //   109: invokeinterface com/intellij/openapi/editor/SelectionModel.getSelectionEnd:()I
        //   114: if_icmplt       133
        //   117: goto            124
        //   120: invokestatic    com/jetbrains/cidr/lang/intentions/OCExtractIfIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   123: athrow         
        //   124: aload_1        
        //   125: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   128: areturn        
        //   129: invokestatic    com/jetbrains/cidr/lang/intentions/OCExtractIfIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   132: athrow         
        //   133: aload_2        
        //   134: astore_1       
        //   135: aload_2        
        //   136: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   141: astore_2       
        //   142: goto            7
        //   145: aconst_null    
        //   146: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  7      18     21     25     Lcom/intellij/util/IncorrectOperationException;
        //  11     32     35     39     Lcom/intellij/util/IncorrectOperationException;
        //  25     57     60     64     Lcom/intellij/util/IncorrectOperationException;
        //  39     87     90     94     Lcom/intellij/util/IncorrectOperationException;
        //  64     117    120    124    Lcom/intellij/util/IncorrectOperationException;
        //  94     129    129    133    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0025:
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
    
    public void invoke(@NotNull final Project project, final Editor editor, @NotNull final PsiElement psiElement) throws IncorrectOperationException {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/intentions/OCExtractIfIntentionAction", "invoke"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/intentions/OCExtractIfIntentionAction", "invoke"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        final OCExpression a = a(editor, psiElement);
        if (a != null) {
            this.invoke(a, a, OCElementFactory.expressionFromText("1", psiElement), OCElementFactory.expressionFromText("0", psiElement));
        }
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
