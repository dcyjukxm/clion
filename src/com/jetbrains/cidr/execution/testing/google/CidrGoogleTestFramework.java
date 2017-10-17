// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing.google;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.execution.testing.CidrTestFrameworkBase;

public class CidrGoogleTestFramework extends CidrTestFrameworkBase
{
    public CidrGoogleTestFramework() {
        super("GoogleTestFramework", (Class<? extends PsiFile>)OCFile.class, CidrTestFrameworkBase.getCidrTopLevelChangesModificationTracker());
    }
    
    @Override
    protected boolean checkFrameworkAvailability(@NotNull final PsiFile psiFile) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestFramework", "checkFrameworkAvailability"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return CidrTestFrameworkBase.checkFrameworkAvailabilityUsingImportedMacro(psiFile, "GTEST_TEST");
    }
    
    @Override
    public boolean isTestClassOrStruct(@Nullable final OCSymbol p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //     4: ifeq            52
        //     7: aload_0        
        //     8: aload_1        
        //     9: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    14: invokevirtual   com/jetbrains/cidr/execution/testing/google/CidrGoogleTestFramework.isAvailable:(Lcom/intellij/psi/PsiFile;)Z
        //    17: ifeq            52
        //    20: goto            27
        //    23: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestFramework.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    26: athrow         
        //    27: aload_1        
        //    28: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //    31: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil.extractGoogleTestName:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;)Lcom/intellij/openapi/util/Couple;
        //    34: ifnull          52
        //    37: goto            44
        //    40: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestFramework.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: iconst_1       
        //    45: goto            53
        //    48: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestFramework.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    51: athrow         
        //    52: iconst_0       
        //    53: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      20     23     27     Ljava/lang/IllegalArgumentException;
        //  7      37     40     44     Ljava/lang/IllegalArgumentException;
        //  27     48     48     52     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0027:
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
    public boolean isTestClass(@Nullable final PsiElement psiElement) {
        return false;
    }
    
    @Override
    public boolean isTestMethodOrFunction(@Nullable final OCSymbol p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //     4: ifeq            126
        //     7: aload_1        
        //     8: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    13: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.FUNCTION_DECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    16: if_acmpeq       45
        //    19: goto            26
        //    22: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestFramework.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    25: athrow         
        //    26: aload_1        
        //    27: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    32: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.FUNCTION_PREDECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    35: if_acmpne       126
        //    38: goto            45
        //    41: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestFramework.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    44: athrow         
        //    45: aload_0        
        //    46: aload_1        
        //    47: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    52: invokevirtual   com/jetbrains/cidr/execution/testing/google/CidrGoogleTestFramework.isAvailable:(Lcom/intellij/psi/PsiFile;)Z
        //    55: ifeq            126
        //    58: goto            65
        //    61: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestFramework.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    64: athrow         
        //    65: aload_1        
        //    66: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //    71: ldc             "TestBody"
        //    73: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    76: ifeq            126
        //    79: goto            86
        //    82: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestFramework.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    85: athrow         
        //    86: aload_1        
        //    87: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //    90: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getResolvedOwner:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //    93: dup            
        //    94: astore_2       
        //    95: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //    98: ifeq            126
        //   101: aload_2        
        //   102: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   105: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil.extractGoogleTestName:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;)Lcom/intellij/openapi/util/Couple;
        //   108: ifnull          126
        //   111: goto            118
        //   114: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestFramework.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   117: athrow         
        //   118: iconst_1       
        //   119: goto            127
        //   122: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestFramework.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   125: athrow         
        //   126: iconst_0       
        //   127: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      19     22     26     Ljava/lang/IllegalArgumentException;
        //  7      38     41     45     Ljava/lang/IllegalArgumentException;
        //  26     58     61     65     Ljava/lang/IllegalArgumentException;
        //  45     79     82     86     Ljava/lang/IllegalArgumentException;
        //  95     111    114    118    Ljava/lang/IllegalArgumentException;
        //  101    122    122    126    Ljava/lang/IllegalArgumentException;
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
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
