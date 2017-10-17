// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.intellij.psi.SmartPsiElementPointer;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import java.util.Collection;
import com.intellij.psi.PsiElement;
import java.util.List;

public class OCRemoveExtraInitializersIntentionAction extends OCPsiElementsQuickFix
{
    private int myStartIndex;
    
    public OCRemoveExtraInitializersIntentionAction(final List<? extends PsiElement> list, final int myStartIndex) {
        super(list);
        this.myStartIndex = myStartIndex;
    }
    
    @Override
    protected String getTextInternal() {
        return "Remove extra initializers";
    }
    
    @NotNull
    public String getFamilyName() {
        String textInternal;
        try {
            textInternal = this.getTextInternal();
            if (textInternal == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCRemoveExtraInitializersIntentionAction", "getFamilyName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return textInternal;
    }
    
    @Override
    public boolean isAvailable() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/quickfixes/OCRemoveExtraInitializersIntentionAction.myElementPointers:Ljava/util/List;
        //     4: ifnull          69
        //     7: aload_0        
        //     8: getfield        com/jetbrains/cidr/lang/quickfixes/OCRemoveExtraInitializersIntentionAction.myElementPointers:Ljava/util/List;
        //    11: invokeinterface java/util/List.size:()I
        //    16: aload_0        
        //    17: getfield        com/jetbrains/cidr/lang/quickfixes/OCRemoveExtraInitializersIntentionAction.myStartIndex:I
        //    20: if_icmple       69
        //    23: goto            30
        //    26: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCRemoveExtraInitializersIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    29: athrow         
        //    30: aload_0        
        //    31: getfield        com/jetbrains/cidr/lang/quickfixes/OCRemoveExtraInitializersIntentionAction.myElementPointers:Ljava/util/List;
        //    34: iconst_0       
        //    35: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //    40: checkcast       Lcom/intellij/psi/SmartPsiElementPointer;
        //    43: invokeinterface com/intellij/psi/SmartPsiElementPointer.getElement:()Lcom/intellij/psi/PsiElement;
        //    48: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.isValid:(Lcom/intellij/psi/PsiElement;)Z
        //    51: ifeq            69
        //    54: goto            61
        //    57: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCRemoveExtraInitializersIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    60: athrow         
        //    61: iconst_1       
        //    62: goto            70
        //    65: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCRemoveExtraInitializersIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    68: athrow         
        //    69: iconst_0       
        //    70: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      23     26     30     Ljava/lang/IllegalStateException;
        //  7      54     57     61     Ljava/lang/IllegalStateException;
        //  30     65     65     69     Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0030:
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
    protected void invoke(final PsiFile psiFile) {
        for (int i = this.myElementPointers.size() - 1; i >= this.myStartIndex; --i) {
            final PsiElement element = this.myElementPointers.get(i).getElement();
            try {
                if (element != null) {
                    OCChangeUtil.delete(element);
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
        }
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
