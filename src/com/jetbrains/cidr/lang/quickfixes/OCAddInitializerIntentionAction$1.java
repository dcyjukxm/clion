// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.intellij.openapi.editor.Document;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.intellij.psi.PsiDocumentManager;
import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.lookup.LookupEvent;
import com.intellij.openapi.editor.Editor;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.intellij.codeInsight.lookup.LookupAdapter;

static final class OCAddInitializerIntentionAction$1 extends LookupAdapter {
    @Override
    public void lookupCanceled(final LookupEvent p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/quickfixes/OCAddInitializerIntentionAction$1.val$deleteOnEsc:Z
        //     4: ifeq            50
        //     7: aload_0        
        //     8: getfield        com/jetbrains/cidr/lang/quickfixes/OCAddInitializerIntentionAction$1.val$expression:Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    11: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.isValid:()Z
        //    16: ifeq            50
        //    19: goto            26
        //    22: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCAddInitializerIntentionAction$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    25: athrow         
        //    26: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //    29: aload_0        
        //    30: getfield        com/jetbrains/cidr/lang/quickfixes/OCAddInitializerIntentionAction$1.val$expression:Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    33: invokedynamic   run:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Ljava/lang/Runnable;
        //    38: invokeinterface com/intellij/openapi/application/Application.runWriteAction:(Ljava/lang/Runnable;)V
        //    43: goto            64
        //    46: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCAddInitializerIntentionAction$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    49: athrow         
        //    50: aload_0        
        //    51: getfield        com/jetbrains/cidr/lang/quickfixes/OCAddInitializerIntentionAction$1.val$editor:Lcom/intellij/openapi/editor/Editor;
        //    54: invokeinterface com/intellij/openapi/editor/Editor.getSelectionModel:()Lcom/intellij/openapi/editor/SelectionModel;
        //    59: invokeinterface com/intellij/openapi/editor/SelectionModel.removeSelection:()V
        //    64: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      19     22     26     Ljava/lang/IllegalArgumentException;
        //  7      46     46     50     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Could not infer any expression.
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:374)
        //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:96)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:344)
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