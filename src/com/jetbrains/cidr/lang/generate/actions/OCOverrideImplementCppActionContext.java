// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.actions;

import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.types.visitors.OCTypeEqualityVisitor;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.openapi.util.Condition;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import java.util.ArrayList;
import java.util.Collection;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;

public class OCOverrideImplementCppActionContext extends OCCppActionContext<OCStructSymbol, OCFunctionSymbol>
{
    private boolean myImplementMode;
    
    public OCOverrideImplementCppActionContext(final OCStructSymbol ocStructSymbol, final PsiElement psiElement, final boolean myImplementMode) {
        super(ocStructSymbol, psiElement);
        this.myImplementMode = myImplementMode;
    }
    
    @NotNull
    @Override
    public Collection<OCFunctionSymbol> getMemberCandidates() {
        final ArrayList list = new ArrayList<OCFunctionSymbol>();
        ArrayList list2;
        try {
            OCStructType.processMembersInBaseTypes(((OCActionContext<OCStructSymbol, M>)this).getParent(), null, false, false, (Condition<OCSymbol>)(p0 -> {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_1        
                //     1: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
                //     4: ifeq            128
                //     7: aload_1        
                //     8: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
                //    11: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isFriendOrStatic:()Z
                //    14: ifne            128
                //    17: goto            24
                //    20: invokestatic    com/jetbrains/cidr/lang/generate/actions/OCOverrideImplementCppActionContext.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
                //    23: athrow         
                //    24: aload_1        
                //    25: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
                //    28: aload_0        
                //    29: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCOverrideImplementCppActionContext.getParent:()Lcom/jetbrains/cidr/lang/symbols/symtable/OCMembersContainer;
                //    32: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
                //    35: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.getOverridingFunctionName:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;)Ljava/lang/String;
                //    38: astore_2       
                //    39: new             Lcom/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor;
                //    42: dup            
                //    43: aload_1        
                //    44: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
                //    49: iconst_0       
                //    50: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
                //    53: dup            
                //    54: aload_0        
                //    55: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCOverrideImplementCppActionContext.getContext:()Lcom/intellij/psi/PsiElement;
                //    58: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
                //    61: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.<init>:(Lcom/jetbrains/cidr/lang/types/OCType;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)V
                //    64: astore_3       
                //    65: aload_0        
                //    66: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCOverrideImplementCppActionContext.getParent:()Lcom/jetbrains/cidr/lang/symbols/symtable/OCMembersContainer;
                //    69: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
                //    72: aload_2        
                //    73: aload_3        
                //    74: invokedynamic   process:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor;)Lcom/intellij/util/Processor;
                //    79: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.processMembers:(Ljava/lang/String;Lcom/intellij/util/Processor;)Z
                //    82: ifeq            128
                //    85: aload_1        
                //    86: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
                //    89: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getVisibility:()Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
                //    92: getstatic       com/jetbrains/cidr/lang/symbols/OCVisibility.PRIVATE:Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
                //    95: if_acmpne       122
                //    98: goto            105
                //   101: invokestatic    com/jetbrains/cidr/lang/generate/actions/OCOverrideImplementCppActionContext.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
                //   104: athrow         
                //   105: aload_1        
                //   106: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
                //   109: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isVirtual:()Z
                //   112: ifeq            128
                //   115: goto            122
                //   118: invokestatic    com/jetbrains/cidr/lang/generate/actions/OCOverrideImplementCppActionContext.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
                //   121: athrow         
                //   122: iconst_1       
                //   123: ireturn        
                //   124: invokestatic    com/jetbrains/cidr/lang/generate/actions/OCOverrideImplementCppActionContext.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
                //   127: athrow         
                //   128: iconst_0       
                //   129: ireturn        
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                             
                //  -----  -----  -----  -----  ---------------------------------
                //  0      17     20     24     Ljava/lang/IllegalStateException;
                //  65     98     101    105    Ljava/lang/IllegalStateException;
                //  85     115    118    122    Ljava/lang/IllegalStateException;
                //  105    124    124    128    Ljava/lang/IllegalStateException;
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0105:
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
            }), (Processor<OCSymbol>)(ocSymbol -> {
                Label_0024: {
                    try {
                        if (!this.myImplementMode) {
                            break Label_0024;
                        }
                        final OCFunctionSymbol ocFunctionSymbol = (OCFunctionSymbol)ocSymbol;
                        final OCFunctionSymbol ocFunctionSymbol2 = ocFunctionSymbol;
                        final boolean b = ocFunctionSymbol2.isPureVirtual();
                        if (b) {
                            break Label_0024;
                        }
                        return true;
                    }
                    catch (IllegalStateException ex) {
                        throw a(ex);
                    }
                    try {
                        final OCFunctionSymbol ocFunctionSymbol = (OCFunctionSymbol)ocSymbol;
                        final OCFunctionSymbol ocFunctionSymbol2 = ocFunctionSymbol;
                        final boolean b = ocFunctionSymbol2.isPureVirtual();
                        if (b) {
                            list.add(ocSymbol);
                        }
                    }
                    catch (IllegalStateException ex2) {
                        throw a(ex2);
                    }
                }
                return true;
            }), new OCResolveContext((PsiElement)this.getContext().getContainingFile()));
            list2 = list;
            if (list2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/actions/OCOverrideImplementCppActionContext", "getMemberCandidates"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (Collection<OCFunctionSymbol>)list2;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
