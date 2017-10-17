// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.introduce;

import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.psi.OCCppNamespace;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;

class OCIntroduceTypedefAction$1 extends OCBaseIntroduceHandler<PsiElement> {
    @Override
    protected OCTypedefInplaceIntroducer createIntroducer(final Project project, final Editor editor, final PsiElement psiElement, final List<PsiElement> list, final String s) {
        return new OCTypedefInplaceIntroducer(project, editor, psiElement, list, s);
    }
    
    @NotNull
    @Override
    protected String getFeatureID() {
        String s;
        try {
            s = "refactoring.introduceTypedef";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/introduce/OCIntroduceTypedefAction$1", "getFeatureID"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    @Override
    protected PsiElement substituteElement(final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //     4: ifne            21
        //     7: aload_1        
        //     8: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //    11: ifeq            28
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCIntroduceTypedefAction$1.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    20: athrow         
        //    21: aload_1        
        //    22: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    27: astore_1       
        //    28: aload_1        
        //    29: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStructLike;
        //    32: ifeq            42
        //    35: aload_1        
        //    36: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    41: astore_1       
        //    42: aload_1        
        //    43: instanceof      Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //    46: ifne            91
        //    49: aload_1        
        //    50: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //    53: ifeq            77
        //    56: goto            63
        //    59: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCIntroduceTypedefAction$1.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    62: athrow         
        //    63: aload_1        
        //    64: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDefinition;
        //    67: ifeq            91
        //    70: goto            77
        //    73: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCIntroduceTypedefAction$1.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    76: athrow         
        //    77: aload_1        
        //    78: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCppNamespaceQualifier;
        //    81: ifeq            97
        //    84: goto            91
        //    87: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCIntroduceTypedefAction$1.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    90: athrow         
        //    91: aload_1        
        //    92: areturn        
        //    93: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCIntroduceTypedefAction$1.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    96: athrow         
        //    97: aconst_null    
        //    98: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      14     17     21     Ljava/lang/IllegalStateException;
        //  42     56     59     63     Ljava/lang/IllegalStateException;
        //  49     70     73     77     Ljava/lang/IllegalStateException;
        //  63     84     87     91     Ljava/lang/IllegalStateException;
        //  77     93     93     97     Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0063:
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
    
    public PsiElement getElementScope(final PsiElement psiElement) {
        return PsiTreeUtil.getParentOfType(psiElement, new Class[] { OCCppNamespace.class, OCFile.class });
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}