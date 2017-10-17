// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.intentions;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.objc.OCImplementationSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCInterfaceSymbol;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.psi.OCClassDeclarationBase;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCInterface;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.openapi.util.Pair;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.psi.OCInstanceVariablesList;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.codeInsight.FileModificationService;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.IncorrectOperationException;
import com.intellij.codeInsight.intention.IntentionAction;

public class OCMoveIvarsToImplementationIntentionAction implements IntentionAction
{
    @NotNull
    public String getText() {
        String s;
        try {
            s = "Move instance variables to implementation";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCMoveIvarsToImplementationIntentionAction", "getText"));
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
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCMoveIvarsToImplementationIntentionAction", "getFamilyName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return text;
    }
    
    public boolean isAvailable(@NotNull final Project p0, final Editor p1, final PsiFile p2) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/intentions/OCMoveIvarsToImplementationIntentionAction"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isAvailable"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/intentions/OCMoveIvarsToImplementationIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    43: athrow         
        //    44: aload_0        
        //    45: aload_2        
        //    46: aload_3        
        //    47: invokevirtual   com/jetbrains/cidr/lang/intentions/OCMoveIvarsToImplementationIntentionAction.locateCandidate:(Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;)Lcom/intellij/openapi/util/Pair;
        //    50: astore          4
        //    52: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.supportsIvarsInImplementation:()Z
        //    55: ifeq            166
        //    58: aload           4
        //    60: ifnull          166
        //    63: goto            70
        //    66: invokestatic    com/jetbrains/cidr/lang/intentions/OCMoveIvarsToImplementationIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    69: athrow         
        //    70: aload           4
        //    72: invokevirtual   com/intellij/openapi/util/Pair.getFirst:()Ljava/lang/Object;
        //    75: checkcast       Lcom/intellij/psi/PsiElement;
        //    78: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.isValid:(Lcom/intellij/psi/PsiElement;)Z
        //    81: ifeq            166
        //    84: goto            91
        //    87: invokestatic    com/jetbrains/cidr/lang/intentions/OCMoveIvarsToImplementationIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    90: athrow         
        //    91: aload           4
        //    93: invokevirtual   com/intellij/openapi/util/Pair.getSecond:()Ljava/lang/Object;
        //    96: checkcast       Lcom/intellij/psi/PsiElement;
        //    99: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.isValid:(Lcom/intellij/psi/PsiElement;)Z
        //   102: ifeq            166
        //   105: goto            112
        //   108: invokestatic    com/jetbrains/cidr/lang/intentions/OCMoveIvarsToImplementationIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   111: athrow         
        //   112: aload           4
        //   114: invokevirtual   com/intellij/openapi/util/Pair.getFirst:()Ljava/lang/Object;
        //   117: checkcast       Lcom/jetbrains/cidr/lang/psi/OCInstanceVariablesList;
        //   120: invokeinterface com/jetbrains/cidr/lang/psi/OCInstanceVariablesList.isEmpty:()Z
        //   125: ifne            166
        //   128: goto            135
        //   131: invokestatic    com/jetbrains/cidr/lang/intentions/OCMoveIvarsToImplementationIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   134: athrow         
        //   135: aload           4
        //   137: invokevirtual   com/intellij/openapi/util/Pair.getSecond:()Ljava/lang/Object;
        //   140: checkcast       Lcom/jetbrains/cidr/lang/psi/OCInstanceVariablesList;
        //   143: invokeinterface com/jetbrains/cidr/lang/psi/OCInstanceVariablesList.isEmpty:()Z
        //   148: ifeq            166
        //   151: goto            158
        //   154: invokestatic    com/jetbrains/cidr/lang/intentions/OCMoveIvarsToImplementationIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   157: athrow         
        //   158: iconst_1       
        //   159: goto            167
        //   162: invokestatic    com/jetbrains/cidr/lang/intentions/OCMoveIvarsToImplementationIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   165: athrow         
        //   166: iconst_0       
        //   167: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  52     63     66     70     Lcom/intellij/util/IncorrectOperationException;
        //  58     84     87     91     Lcom/intellij/util/IncorrectOperationException;
        //  70     105    108    112    Lcom/intellij/util/IncorrectOperationException;
        //  91     128    131    135    Lcom/intellij/util/IncorrectOperationException;
        //  112    151    154    158    Lcom/intellij/util/IncorrectOperationException;
        //  135    162    162    166    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0070:
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
    
    public void invoke(@NotNull final Project project, final Editor editor, final PsiFile psiFile) throws IncorrectOperationException {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/intentions/OCMoveIvarsToImplementationIntentionAction", "invoke"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (!FileModificationService.getInstance().prepareFileForWrite(psiFile)) {
                return;
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        PsiDocumentManager.getInstance(project).commitAllDocuments();
        final Pair<OCInstanceVariablesList, OCInstanceVariablesList> locateCandidate = this.locateCandidate(editor, psiFile);
        if (locateCandidate != null) {
            final PsiFile containingFile = ((OCInstanceVariablesList)locateCandidate.getFirst()).getContainingFile();
            try {
                if (!FileModificationService.getInstance().prepareFileForWrite(containingFile)) {
                    return;
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
            final PsiFile containingFile2 = ((OCInstanceVariablesList)locateCandidate.getSecond()).getContainingFile();
            try {
                if (!FileModificationService.getInstance().prepareFileForWrite(containingFile2)) {
                    return;
                }
            }
            catch (IncorrectOperationException ex4) {
                throw a(ex4);
            }
            OCChangeUtil.changeText(project, containingFile2, ((OCInstanceVariablesList)locateCandidate.getSecond()).getTextOffset(), 0, ((OCInstanceVariablesList)locateCandidate.getFirst()).getTextWithMacros(), false);
            OCChangeUtil.changeText(project, containingFile, ((OCInstanceVariablesList)locateCandidate.getFirst()).getTextOffset(), ((OCInstanceVariablesList)locateCandidate.getFirst()).getTextLength(), "", false);
        }
    }
    
    public boolean startInWriteAction() {
        return true;
    }
    
    @Nullable
    protected Pair<OCInstanceVariablesList, OCInstanceVariablesList> locateCandidate(final Editor editor, final PsiFile psiFile) {
        final OCInstanceVariablesList list = OCElementUtil.getAdjacentParentOfType(psiFile.findElementAt(editor.getCaretModel().getOffset()), (Class<? extends OCInstanceVariablesList>)OCInstanceVariablesList.class);
        final OCInterface ocInterface = (OCInterface)PsiTreeUtil.getParentOfType((PsiElement)list, (Class)OCInterface.class);
        try {
            if (ocInterface == null) {
                return null;
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final OCInterfaceSymbol symbol = ocInterface.getSymbol();
        OCImplementationSymbol implementation = null;
        Label_0081: {
            try {
                if (symbol != null) {
                    implementation = symbol.getImplementation();
                    break Label_0081;
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
            implementation = null;
        }
        final OCImplementationSymbol ocImplementationSymbol = implementation;
        OCClassDeclarationBase ocClassDeclarationBase = null;
        Label_0106: {
            try {
                if (ocImplementationSymbol != null) {
                    ocClassDeclarationBase = ocImplementationSymbol.locateDefinition();
                    break Label_0106;
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
            ocClassDeclarationBase = null;
        }
        final OCClassDeclaration ocClassDeclaration = (OCClassDeclaration)ocClassDeclarationBase;
        try {
            if (ocClassDeclaration instanceof OCClassDeclaration) {
                return (Pair<OCInstanceVariablesList, OCInstanceVariablesList>)Pair.create((Object)list, (Object)ocClassDeclaration.getInstanceVariablesList());
            }
        }
        catch (IncorrectOperationException ex4) {
            throw a(ex4);
        }
        return null;
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
