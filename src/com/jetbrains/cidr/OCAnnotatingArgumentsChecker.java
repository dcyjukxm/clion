// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr;

import com.intellij.lang.annotation.Annotation;
import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.codeInspection.ProblemHighlightType;
import com.jetbrains.cidr.lang.inspections.OCInspection;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCCompoundInitializer;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.daemon.OCCppChecker;
import com.jetbrains.cidr.lang.daemon.OCArgumentsChecker;

public class OCAnnotatingArgumentsChecker extends OCArgumentsChecker
{
    @NotNull
    private final OCCppChecker myCppChecker;
    
    public OCAnnotatingArgumentsChecker(@NotNull final OCCppChecker myCppChecker) {
        if (myCppChecker == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cppChecker", "com/jetbrains/cidr/OCAnnotatingArgumentsChecker", "<init>"));
        }
        this.myCppChecker = myCppChecker;
    }
    
    @Override
    protected void checkConstructor(final OCCompoundInitializer p0, final OCSymbol p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.topmostParenthesized:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //     4: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getParent:()Lcom/intellij/psi/PsiElement;
        //     9: astore_3       
        //    10: aload_3        
        //    11: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //    14: ifne            113
        //    17: aload_3        
        //    18: instanceof      Lcom/jetbrains/cidr/lang/psi/OCArgumentList;
        //    21: ifne            113
        //    24: goto            31
        //    27: invokestatic    com/jetbrains/cidr/OCAnnotatingArgumentsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    30: athrow         
        //    31: aload_3        
        //    32: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCompoundInitializer;
        //    35: ifne            113
        //    38: goto            45
        //    41: invokestatic    com/jetbrains/cidr/OCAnnotatingArgumentsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    44: athrow         
        //    45: aload_3        
        //    46: instanceof      Lcom/jetbrains/cidr/lang/psi/OCConstructorFieldInitializer;
        //    49: ifne            113
        //    52: goto            59
        //    55: invokestatic    com/jetbrains/cidr/OCAnnotatingArgumentsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    58: athrow         
        //    59: aload_3        
        //    60: instanceof      Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //    63: ifeq            92
        //    66: goto            73
        //    69: invokestatic    com/jetbrains/cidr/OCAnnotatingArgumentsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    72: athrow         
        //    73: aload_3        
        //    74: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    79: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCppNewExpression;
        //    82: ifne            113
        //    85: goto            92
        //    88: invokestatic    com/jetbrains/cidr/OCAnnotatingArgumentsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    91: athrow         
        //    92: aload_0        
        //    93: getfield        com/jetbrains/cidr/OCAnnotatingArgumentsChecker.myCppChecker:Lcom/jetbrains/cidr/lang/daemon/OCCppChecker;
        //    96: aload_1        
        //    97: aload_1        
        //    98: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
        //   101: aload_2        
        //   102: invokevirtual   com/jetbrains/cidr/lang/daemon/OCCppChecker.checkConstructorCall:(Lcom/jetbrains/cidr/lang/psi/OCElement;Ljava/util/List;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //   105: pop            
        //   106: goto            113
        //   109: invokestatic    com/jetbrains/cidr/OCAnnotatingArgumentsChecker.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   112: athrow         
        //   113: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  10     24     27     31     Ljava/lang/IllegalArgumentException;
        //  17     38     41     45     Ljava/lang/IllegalArgumentException;
        //  31     52     55     59     Ljava/lang/IllegalArgumentException;
        //  45     66     69     73     Ljava/lang/IllegalArgumentException;
        //  59     85     88     92     Ljava/lang/IllegalArgumentException;
        //  73     106    109    113    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0031:
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
    protected void checkAssignment(final OCExpression ocExpression, final PsiElement psiElement, final OCType ocType, final OCType ocType2, final OCSymbol ocSymbol, final OCType ocType3, final boolean b, final String s) {
        this.myCppChecker.checkAssignment(ocExpression, psiElement, ocType, ocType2, ocSymbol, ocType3, false, s);
    }
    
    @Nullable
    @Override
    protected Annotation addWarningAnnotation(@Nullable final PsiElement psiElement, @Nullable final Class<? extends OCInspection> clazz, @Nullable final String s, @NotNull final String s2, @Nullable final ProblemHighlightType problemHighlightType, final IntentionAction... array) {
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "message", "com/jetbrains/cidr/OCAnnotatingArgumentsChecker", "addWarningAnnotation"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final Annotation addWarningAnnotation = this.myCppChecker.addWarningAnnotation(psiElement, clazz, s, s2, problemHighlightType);
        for (int length = array.length, i = 0; i < length; ++i) {
            this.myCppChecker.registerQuickFix(addWarningAnnotation, array[i]);
        }
        return addWarningAnnotation;
    }
    
    @Nullable
    @Override
    protected Annotation addErrorAnnotation(@Nullable final PsiElement psiElement, @Nullable final Class<? extends OCInspection> clazz, @Nullable final String s, @NotNull final String s2, final IntentionAction... array) {
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "message", "com/jetbrains/cidr/OCAnnotatingArgumentsChecker", "addErrorAnnotation"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final Annotation addErrorAnnotation = this.myCppChecker.addErrorAnnotation(psiElement, clazz, s, s2);
        for (int length = array.length, i = 0; i < length; ++i) {
            this.myCppChecker.registerQuickFix(addErrorAnnotation, array[i]);
        }
        return addErrorAnnotation;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
