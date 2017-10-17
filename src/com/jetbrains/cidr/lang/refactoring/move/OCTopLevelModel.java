// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move;

import com.intellij.refactoring.classMembers.MemberInfoChange;
import com.intellij.refactoring.classMembers.MemberInfoBase;
import org.jetbrains.annotations.NotNull;
import com.intellij.refactoring.classMembers.AbstractUsesDependencyMemberInfoModel;
import com.intellij.refactoring.classMembers.MemberInfoModel;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCSymbolHolderVirtualPsiElement;
import com.intellij.refactoring.classMembers.UsedByDependencyMemberInfoModel;

public class OCTopLevelModel extends UsedByDependencyMemberInfoModel<OCSymbolHolderVirtualPsiElement, PsiElement, OCMemberInfo>
{
    private boolean myImportTargetFromSource;
    private boolean myImportSourceFromTarget;
    private VirtualFile mySourceFile;
    private MemberInfoModel<OCSymbolHolderVirtualPsiElement, OCMemberInfo> myUsesModel;
    
    public OCTopLevelModel(final PsiElement psiElement) {
        super(psiElement);
        this.mySourceFile = ((psiElement != null) ? psiElement.getContainingFile().getVirtualFile() : null);
        this.myUsesModel = (MemberInfoModel<OCSymbolHolderVirtualPsiElement, OCMemberInfo>)new AbstractUsesDependencyMemberInfoModel<OCSymbolHolderVirtualPsiElement, PsiElement, OCMemberInfo>(psiElement, null, false) {
            @Override
            protected int doCheck(@NotNull final OCMemberInfo ocMemberInfo, final int n) {
                try {
                    if (ocMemberInfo == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "memberInfo", "com/jetbrains/cidr/lang/refactoring/move/OCTopLevelModel$1", "doCheck"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw c(ex);
                }
                return n;
            }
            
            private static IllegalArgumentException c(final IllegalArgumentException ex) {
                return ex;
            }
        };
    }
    
    public boolean importTargetFromSource() {
        return this.myImportTargetFromSource;
    }
    
    public boolean importSourceFromTarget() {
        Label_0021: {
            try {
                if (!this.myImportSourceFromTarget) {
                    return false;
                }
                final OCTopLevelModel ocTopLevelModel = this;
                final boolean b = ocTopLevelModel.myImportTargetFromSource;
                if (!b) {
                    break Label_0021;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                final OCTopLevelModel ocTopLevelModel = this;
                final boolean b = ocTopLevelModel.myImportTargetFromSource;
                if (!b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        return false;
    }
    
    public int checkForProblems(@NotNull final OCMemberInfo p0) {
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
        //    18: ldc             "memberInfo"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/refactoring/move/OCTopLevelModel"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "checkForProblems"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCTopLevelModel.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: aload_1        
        //    46: invokespecial   com/intellij/refactoring/classMembers/UsedByDependencyMemberInfoModel.checkForProblems:(Lcom/intellij/refactoring/classMembers/MemberInfoBase;)I
        //    49: ifeq            107
        //    52: aload_0        
        //    53: aload_0        
        //    54: getfield        com/jetbrains/cidr/lang/refactoring/move/OCTopLevelModel.mySourceFile:Lcom/intellij/openapi/vfs/VirtualFile;
        //    57: ifnull          101
        //    60: goto            67
        //    63: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCTopLevelModel.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    66: athrow         
        //    67: aload_0        
        //    68: getfield        com/jetbrains/cidr/lang/refactoring/move/OCTopLevelModel.mySourceFile:Lcom/intellij/openapi/vfs/VirtualFile;
        //    71: aload_1        
        //    72: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/OCMemberInfo.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    75: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getContainingFile:()Lcom/intellij/openapi/vfs/VirtualFile;
        //    80: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //    83: ifeq            101
        //    86: goto            93
        //    89: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCTopLevelModel.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    92: athrow         
        //    93: iconst_1       
        //    94: goto            102
        //    97: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCTopLevelModel.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   100: athrow         
        //   101: iconst_0       
        //   102: putfield        com/jetbrains/cidr/lang/refactoring/move/OCTopLevelModel.myImportTargetFromSource:Z
        //   105: iconst_1       
        //   106: ireturn        
        //   107: aload_0        
        //   108: getfield        com/jetbrains/cidr/lang/refactoring/move/OCTopLevelModel.myUsesModel:Lcom/intellij/refactoring/classMembers/MemberInfoModel;
        //   111: aload_1        
        //   112: invokeinterface com/intellij/refactoring/classMembers/MemberInfoModel.checkForProblems:(Lcom/intellij/refactoring/classMembers/MemberInfoBase;)I
        //   117: ifeq            131
        //   120: aload_0        
        //   121: iconst_1       
        //   122: putfield        com/jetbrains/cidr/lang/refactoring/move/OCTopLevelModel.myImportSourceFromTarget:Z
        //   125: iconst_1       
        //   126: ireturn        
        //   127: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCTopLevelModel.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   130: athrow         
        //   131: iconst_0       
        //   132: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     60     63     67     Ljava/lang/IllegalArgumentException;
        //  52     86     89     93     Ljava/lang/IllegalArgumentException;
        //  67     97     97     101    Ljava/lang/IllegalArgumentException;
        //  107    127    127    131    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0067:
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
    public void memberInfoChanged(final MemberInfoChange<OCSymbolHolderVirtualPsiElement, OCMemberInfo> memberInfoChange) {
        final boolean b = false;
        this.myImportTargetFromSource = b;
        this.myImportSourceFromTarget = b;
        super.memberInfoChanged((com.intellij.refactoring.classMembers.MemberInfoChange<T, M>)memberInfoChange);
        this.myUsesModel.memberInfoChanged((MemberInfoChange)memberInfoChange);
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
