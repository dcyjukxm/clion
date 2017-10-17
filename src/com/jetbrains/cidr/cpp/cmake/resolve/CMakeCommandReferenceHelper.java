// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.resolve;

import com.jetbrains.cidr.cpp.cmake.psi.CMakeCommandNameMixin;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeLiteral;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeArgument;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;

public class CMakeCommandReferenceHelper
{
    public static boolean isArgument(@Nullable final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnull          38
        //     4: aload_0        
        //     5: instanceof      Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeArgument;
        //     8: ifne            32
        //    11: goto            18
        //    14: invokestatic    com/jetbrains/cidr/cpp/cmake/resolve/CMakeCommandReferenceHelper.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    17: athrow         
        //    18: aload_0        
        //    19: instanceof      Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeLiteral;
        //    22: ifeq            38
        //    25: goto            32
        //    28: invokestatic    com/jetbrains/cidr/cpp/cmake/resolve/CMakeCommandReferenceHelper.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    31: athrow         
        //    32: iconst_1       
        //    33: ireturn        
        //    34: invokestatic    com/jetbrains/cidr/cpp/cmake/resolve/CMakeCommandReferenceHelper.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    37: athrow         
        //    38: iconst_0       
        //    39: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      11     14     18     Ljava/lang/IllegalArgumentException;
        //  4      25     28     32     Ljava/lang/IllegalArgumentException;
        //  18     34     34     38     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0018:
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
    
    @NotNull
    public static CMakeArgument getCMakeArgument(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "literalOrArgument", "com/jetbrains/cidr/cpp/cmake/resolve/CMakeCommandReferenceHelper", "getCMakeArgument"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        CMakeArgument cMakeArgument;
        if (psiElement instanceof CMakeLiteral) {
            cMakeArgument = (CMakeArgument)psiElement.getParent();
        }
        else {
            cMakeArgument = (CMakeArgument)psiElement;
        }
        CMakeArgument cMakeArgument2;
        try {
            cMakeArgument2 = cMakeArgument;
            if (cMakeArgument2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/resolve/CMakeCommandReferenceHelper", "getCMakeArgument"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return cMakeArgument2;
    }
    
    public static boolean commandNamesEqualByText(@Nullable final PsiElement psiElement, @Nullable final PsiElement psiElement2) {
        Object cMakeLiteral = psiElement;
        Object cMakeLiteral2 = psiElement2;
        if (psiElement instanceof CMakeArgument) {
            cMakeLiteral = ((CMakeArgument)psiElement).getCMakeLiteral();
        }
        if (psiElement2 instanceof CMakeArgument) {
            cMakeLiteral2 = ((CMakeArgument)psiElement2).getCMakeLiteral();
        }
        Label_0053: {
            try {
                if (cMakeLiteral2 != null) {
                    return commandNamesEqualByText((PsiElement)cMakeLiteral, ((PsiElement)cMakeLiteral2).getText());
                }
                final PsiElement psiElement3 = (PsiElement)cMakeLiteral;
                if (psiElement3 == null) {
                    break Label_0053;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final PsiElement psiElement3 = (PsiElement)cMakeLiteral;
                if (psiElement3 == null) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    public static boolean commandNamesEqualByText(@Nullable final PsiElement psiElement, @Nullable final String s) {
        Label_0025: {
            Label_0015: {
                try {
                    if (psiElement != null) {
                        break Label_0025;
                    }
                    final String s2 = s;
                    if (s2 == null) {
                        break Label_0015;
                    }
                    return false;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final String s2 = s;
                    if (s2 == null) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return false;
        }
        Object cMakeLiteral = psiElement;
        if (psiElement instanceof CMakeArgument) {
            cMakeLiteral = ((CMakeArgument)psiElement).getCMakeLiteral();
        }
        Label_0068: {
            try {
                if (cMakeLiteral == null) {
                    return false;
                }
                final Object o = cMakeLiteral;
                final String s3 = ((PsiElement)o).getText();
                final String s4 = s;
                final boolean b2 = s3.equalsIgnoreCase(s4);
                if (b2) {
                    break Label_0068;
                }
                return false;
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                final Object o = cMakeLiteral;
                final String s3 = ((PsiElement)o).getText();
                final String s4 = s;
                final boolean b2 = s3.equalsIgnoreCase(s4);
                if (b2) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return false;
    }
    
    public static boolean isCommandCall(@Nullable final PsiElement psiElement) {
        return psiElement instanceof CMakeCommandNameMixin;
    }
    
    public static boolean isCommandDefinition(@Nullable final PsiElement psiElement) {
        try {
            if (!isArgument(psiElement)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0045: {
            try {
                if (!(psiElement instanceof CMakeArgument)) {
                    break Label_0045;
                }
                final PsiElement psiElement2 = psiElement;
                final CMakeArgument cMakeArgument = (CMakeArgument)psiElement2;
                final boolean b = cMakeArgument.isCommandDefinitionName();
                if (b) {
                    return true;
                }
                break Label_0045;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final PsiElement psiElement2 = psiElement;
                final CMakeArgument cMakeArgument = (CMakeArgument)psiElement2;
                final boolean b = cMakeArgument.isCommandDefinitionName();
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                if (!(psiElement instanceof CMakeLiteral)) {
                    return false;
                }
                final PsiElement psiElement3 = psiElement;
                final PsiElement psiElement4 = psiElement3.getParent();
                final CMakeArgument cMakeArgument2 = (CMakeArgument)psiElement4;
                final boolean b2 = cMakeArgument2.isCommandDefinitionName();
                if (b2) {
                    return true;
                }
                return false;
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        try {
            final PsiElement psiElement3 = psiElement;
            final PsiElement psiElement4 = psiElement3.getParent();
            final CMakeArgument cMakeArgument2 = (CMakeArgument)psiElement4;
            final boolean b2 = cMakeArgument2.isCommandDefinitionName();
            if (b2) {
                return true;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        return false;
    }
    
    public static boolean isEndCommandDefinition(@Nullable final PsiElement psiElement) {
        return isEndCommandDefinition(psiElement, null);
    }
    
    public static boolean isEndCommandDefinition(@Nullable final PsiElement psiElement, @Nullable final String s) {
        try {
            if (!isArgument(psiElement)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0046: {
            try {
                if (!(psiElement instanceof CMakeArgument)) {
                    break Label_0046;
                }
                final PsiElement psiElement2 = psiElement;
                final CMakeArgument cMakeArgument = (CMakeArgument)psiElement2;
                final String s2 = s;
                final boolean b = cMakeArgument.isEndCommandDefinitionName(s2);
                if (b) {
                    return true;
                }
                break Label_0046;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final PsiElement psiElement2 = psiElement;
                final CMakeArgument cMakeArgument = (CMakeArgument)psiElement2;
                final String s2 = s;
                final boolean b = cMakeArgument.isEndCommandDefinitionName(s2);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                if (!(psiElement instanceof CMakeLiteral)) {
                    return false;
                }
                final PsiElement psiElement3 = psiElement;
                final PsiElement psiElement4 = psiElement3.getParent();
                final CMakeArgument cMakeArgument2 = (CMakeArgument)psiElement4;
                final String s3 = s;
                final boolean b2 = cMakeArgument2.isEndCommandDefinitionName(s3);
                if (b2) {
                    return true;
                }
                return false;
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        try {
            final PsiElement psiElement3 = psiElement;
            final PsiElement psiElement4 = psiElement3.getParent();
            final CMakeArgument cMakeArgument2 = (CMakeArgument)psiElement4;
            final String s3 = s;
            final boolean b2 = cMakeArgument2.isEndCommandDefinitionName(s3);
            if (b2) {
                return true;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        return false;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
