// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.SmartPsiElementPointer;
import com.intellij.psi.PsiElement;

public abstract class OCPsiElementQuickFix<T extends PsiElement> extends OCQuickFix
{
    protected SmartPsiElementPointer<T> myElementPtr;
    
    protected OCPsiElementQuickFix(@Nullable final T t) {
        this.myElementPtr = OCElementUtil.createPsiElementPointer(t);
    }
    
    protected boolean isAvailable(@NotNull final T t) {
        try {
            if (t == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/quickfixes/OCPsiElementQuickFix", "isAvailable"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return true;
    }
    
    protected void invoke(final PsiFile psiFile, @NotNull final T t) {
        try {
            if (t == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/quickfixes/OCPsiElementQuickFix", "invoke"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
    }
    
    @Override
    public boolean isAvailable() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/quickfixes/OCPsiElementQuickFix.myElementPtr:Lcom/intellij/psi/SmartPsiElementPointer;
        //     4: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getPsiElementByPointer:(Lcom/intellij/psi/SmartPsiElementPointer;)Lcom/intellij/psi/PsiElement;
        //     7: astore_1       
        //     8: aload_1        
        //     9: ifnull          49
        //    12: aload_1        
        //    13: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.isValid:(Lcom/intellij/psi/PsiElement;)Z
        //    16: ifeq            49
        //    19: goto            26
        //    22: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCPsiElementQuickFix.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    25: athrow         
        //    26: aload_0        
        //    27: aload_1        
        //    28: invokevirtual   com/jetbrains/cidr/lang/quickfixes/OCPsiElementQuickFix.isAvailable:(Lcom/intellij/psi/PsiElement;)Z
        //    31: ifeq            49
        //    34: goto            41
        //    37: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCPsiElementQuickFix.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    40: athrow         
        //    41: iconst_1       
        //    42: goto            50
        //    45: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCPsiElementQuickFix.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    48: athrow         
        //    49: iconst_0       
        //    50: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  8      19     22     26     Ljava/lang/IllegalArgumentException;
        //  12     34     37     41     Ljava/lang/IllegalArgumentException;
        //  26     45     45     49     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0026:
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
        final PsiElement psiElementByPointer = OCElementUtil.getPsiElementByPointer(this.myElementPtr);
        try {
            if (psiElementByPointer != null) {
                this.invoke(psiFile, (T)psiElementByPointer);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
