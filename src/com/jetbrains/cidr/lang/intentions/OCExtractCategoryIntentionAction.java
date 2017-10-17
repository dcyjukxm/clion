// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.intentions;

import com.intellij.openapi.actionSystem.impl.SimpleDataContext;
import com.jetbrains.cidr.lang.refactoring.move.handlers.OCExtractCategoryHandler;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.actions.newFile.OCNewFileActionBase;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.IncorrectOperationException;
import com.intellij.codeInsight.intention.IntentionAction;

public class OCExtractCategoryIntentionAction implements IntentionAction
{
    @NotNull
    public String getText() {
        String s;
        try {
            s = "Extract category of this class";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCExtractCategoryIntentionAction", "getText"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return s;
    }
    
    @NotNull
    public String getFamilyName() {
        String text;
        try {
            text = this.getText();
            if (text == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCExtractCategoryIntentionAction", "getFamilyName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return text;
    }
    
    public boolean isAvailable(@NotNull final Project project, final Editor editor, final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/intentions/OCExtractCategoryIntentionAction", "isAvailable"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final PsiElement element = psiFile.findElementAt(editor.getCaretModel().getOffset());
        Label_0084: {
            try {
                if (!OCNewFileActionBase.isNewFileActionSupported()) {
                    return false;
                }
                final PsiElement psiElement = element;
                final boolean b = isClassNameWithoutCategory(psiElement);
                if (b) {
                    break Label_0084;
                }
                return false;
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
            try {
                final PsiElement psiElement = element;
                final boolean b = isClassNameWithoutCategory(psiElement);
                if (b) {
                    return true;
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
        }
        return false;
    }
    
    public void invoke(@NotNull final Project project, final Editor editor, final PsiFile psiFile) throws IncorrectOperationException {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/intentions/OCExtractCategoryIntentionAction", "invoke"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        new OCExtractCategoryHandler().invoke(project, editor, psiFile, SimpleDataContext.getProjectContext(project));
    }
    
    public boolean startInWriteAction() {
        return false;
    }
    
    public static boolean isClassNameWithoutCategory(final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnull          119
        //     4: aload_0        
        //     5: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //    10: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.isValid:(Lcom/intellij/psi/PsiElement;)Z
        //    13: ifeq            119
        //    16: goto            23
        //    19: invokestatic    com/jetbrains/cidr/lang/intentions/OCExtractCategoryIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    22: athrow         
        //    23: aload_0        
        //    24: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //    27: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IDENTIFIER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    30: if_acmpeq       62
        //    33: goto            40
        //    36: invokestatic    com/jetbrains/cidr/lang/intentions/OCExtractCategoryIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    39: athrow         
        //    40: aload_0        
        //    41: invokeinterface com/intellij/psi/PsiElement.getPrevSibling:()Lcom/intellij/psi/PsiElement;
        //    46: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //    49: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IDENTIFIER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    52: if_acmpne       119
        //    55: goto            62
        //    58: invokestatic    com/jetbrains/cidr/lang/intentions/OCExtractCategoryIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    61: athrow         
        //    62: aload_0        
        //    63: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    68: astore_1       
        //    69: aload_1        
        //    70: instanceof      Lcom/jetbrains/cidr/lang/psi/OCInterface;
        //    73: ifne            90
        //    76: aload_1        
        //    77: instanceof      Lcom/jetbrains/cidr/lang/psi/OCImplementation;
        //    80: ifeq            117
        //    83: goto            90
        //    86: invokestatic    com/jetbrains/cidr/lang/intentions/OCExtractCategoryIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    89: athrow         
        //    90: aload_1        
        //    91: checkcast       Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
        //    94: invokeinterface com/jetbrains/cidr/lang/psi/OCClassDeclaration.getCategory:()Ljava/lang/String;
        //    99: ifnonnull       117
        //   102: goto            109
        //   105: invokestatic    com/jetbrains/cidr/lang/intentions/OCExtractCategoryIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   108: athrow         
        //   109: iconst_1       
        //   110: goto            118
        //   113: invokestatic    com/jetbrains/cidr/lang/intentions/OCExtractCategoryIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   116: athrow         
        //   117: iconst_0       
        //   118: ireturn        
        //   119: iconst_0       
        //   120: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      16     19     23     Lcom/intellij/util/IncorrectOperationException;
        //  4      33     36     40     Lcom/intellij/util/IncorrectOperationException;
        //  23     55     58     62     Lcom/intellij/util/IncorrectOperationException;
        //  69     83     86     90     Lcom/intellij/util/IncorrectOperationException;
        //  76     102    105    109    Lcom/intellij/util/IncorrectOperationException;
        //  90     113    113    117    Lcom/intellij/util/IncorrectOperationException;
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
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
