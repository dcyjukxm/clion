// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiNamedElement;
import com.jetbrains.cidr.lang.refactoring.rename.OCUnresolvedReferenceRenamer;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;

public class OCRenameReferenceIntentionAction extends OCPsiElementQuickFix<PsiElement>
{
    public OCRenameReferenceIntentionAction(@Nullable final PsiElement psiElement) {
        super(psiElement);
    }
    
    @Override
    protected String getTextInternal() {
        return "Rename Reference";
    }
    
    @NotNull
    public String getFamilyName() {
        String s;
        try {
            s = "Rename reference";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCRenameReferenceIntentionAction", "getFamilyName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw c(ex);
        }
        return s;
    }
    
    @Override
    protected boolean isAvailable(@NotNull final PsiElement p0) {
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
        //    18: ldc             "element"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/quickfixes/OCRenameReferenceIntentionAction"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isAvailable"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCRenameReferenceIntentionAction.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    43: athrow         
        //    44: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //    47: invokeinterface com/intellij/openapi/application/Application.isUnitTestMode:()Z
        //    52: ifne            91
        //    55: aload_1        
        //    56: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //    59: ifne            83
        //    62: goto            69
        //    65: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCRenameReferenceIntentionAction.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    68: athrow         
        //    69: aload_1        
        //    70: instanceof      Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //    73: ifeq            91
        //    76: goto            83
        //    79: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCRenameReferenceIntentionAction.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    82: athrow         
        //    83: iconst_1       
        //    84: goto            92
        //    87: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCRenameReferenceIntentionAction.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    90: athrow         
        //    91: iconst_0       
        //    92: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  44     62     65     69     Lcom/intellij/util/IncorrectOperationException;
        //  55     76     79     83     Lcom/intellij/util/IncorrectOperationException;
        //  69     87     87     91     Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0069:
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
    public void invoke(@NotNull final Project project, @Nullable final Editor editor, final PsiFile psiFile) throws IncorrectOperationException {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCRenameReferenceIntentionAction", "invoke"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw c(ex);
        }
        final PsiElement element = this.myElementPtr.getElement();
        PsiReference reference = null;
        Label_0075: {
            try {
                if (element != null) {
                    reference = element.getReference();
                    break Label_0075;
                }
            }
            catch (IncorrectOperationException ex2) {
                throw c(ex2);
            }
            reference = null;
        }
        final PsiReference psiReference = reference;
        OCUnresolvedReferenceRenamer ocUnresolvedReferenceRenamer = null;
        PsiNamedElement psiNamedElement = null;
        Label_0114: {
            Label_0101: {
                try {
                    if (psiReference == null) {
                        return;
                    }
                    ocUnresolvedReferenceRenamer = new(com.jetbrains.cidr.lang.refactoring.rename.OCUnresolvedReferenceRenamer.class);
                    final PsiElement psiElement = element;
                    final boolean b = psiElement instanceof PsiNamedElement;
                    if (b) {
                        break Label_0101;
                    }
                    break Label_0101;
                }
                catch (IncorrectOperationException ex3) {
                    throw c(ex3);
                }
                try {
                    ocUnresolvedReferenceRenamer = new(com.jetbrains.cidr.lang.refactoring.rename.OCUnresolvedReferenceRenamer.class);
                    final PsiElement psiElement = element;
                    final boolean b = psiElement instanceof PsiNamedElement;
                    if (b) {
                        psiNamedElement = (PsiNamedElement)element;
                        break Label_0114;
                    }
                }
                catch (IncorrectOperationException ex4) {
                    throw c(ex4);
                }
            }
            psiNamedElement = null;
        }
        new OCUnresolvedReferenceRenamer(psiNamedElement, editor, psiFile, project, psiReference, null, psiReference.getCanonicalText());
        ocUnresolvedReferenceRenamer.performInplaceRename();
    }
    
    private static IncorrectOperationException c(final IncorrectOperationException ex) {
        return ex;
    }
}
