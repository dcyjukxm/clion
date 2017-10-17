// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing.tcatch;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.execution.testing.CidrTestFrameworkBase;

public class CidrCatchTestFramework extends CidrTestFrameworkBase
{
    public CidrCatchTestFramework() {
        super("CatchFramework", (Class<? extends PsiFile>)OCFile.class, CidrTestFrameworkBase.getCidrTopLevelChangesModificationTracker());
    }
    
    @Override
    protected boolean checkFrameworkAvailability(@NotNull final PsiFile psiFile) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestFramework", "checkFrameworkAvailability"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return CidrTestFrameworkBase.checkFrameworkAvailabilityUsingImportedMacro(psiFile, "TEST_CASE");
    }
    
    @Override
    public boolean isTestClassOrStruct(@Nullable final OCSymbol ocSymbol) {
        return false;
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
        //     4: ifeq            130
        //     7: aload_1        
        //     8: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    13: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.FUNCTION_DECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    16: if_acmpne       130
        //    19: goto            26
        //    22: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestFramework.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    25: athrow         
        //    26: aload_0        
        //    27: aload_1        
        //    28: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    33: invokevirtual   com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestFramework.isAvailable:(Lcom/intellij/psi/PsiFile;)Z
        //    36: ifeq            130
        //    39: goto            46
        //    42: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestFramework.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    45: athrow         
        //    46: aload_1        
        //    47: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //    52: ldc             "____C_A_T_C_H____T_E_S_T____"
        //    54: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //    57: ifne            122
        //    60: goto            67
        //    63: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestFramework.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    66: athrow         
        //    67: aload_1        
        //    68: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //    73: ldc             "test"
        //    75: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    78: ifeq            130
        //    81: goto            88
        //    84: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestFramework.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_1        
        //    89: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //    92: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getResolvedOwner:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //    95: dup            
        //    96: astore_2       
        //    97: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   100: ifeq            130
        //   103: aload_2        
        //   104: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getName:()Ljava/lang/String;
        //   107: ldc             "____C_A_T_C_H____T_E_S_T____"
        //   109: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   112: ifeq            130
        //   115: goto            122
        //   118: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestFramework.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   121: athrow         
        //   122: iconst_1       
        //   123: goto            131
        //   126: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestFramework.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   129: athrow         
        //   130: iconst_0       
        //   131: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      19     22     26     Ljava/lang/IllegalArgumentException;
        //  7      39     42     46     Ljava/lang/IllegalArgumentException;
        //  26     60     63     67     Ljava/lang/IllegalArgumentException;
        //  46     81     84     88     Ljava/lang/IllegalArgumentException;
        //  97     115    118    122    Ljava/lang/IllegalArgumentException;
        //  103    126    126    130    Ljava/lang/IllegalArgumentException;
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
