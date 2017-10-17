// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate;

import com.jetbrains.cidr.lang.generate.actions.OCActionContext;
import com.intellij.util.Processor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.generate.actions.OCCppActionContext;

public class OCGenerateComparisonOperatorsContext extends OCCppActionContext<OCStructSymbol, OCDeclaratorSymbol>
{
    @NotNull
    private List<String> myOperatorsToGenerate;
    @NotNull
    private List<String> myExistingOperators;
    
    public OCGenerateComparisonOperatorsContext(final OCStructSymbol ocStructSymbol, final PsiElement psiElement) {
        super(ocStructSymbol, psiElement);
        this.myOperatorsToGenerate = Collections.emptyList();
        this.myExistingOperators = Collections.emptyList();
    }
    
    @Override
    public boolean allFunctionsExist() {
        try {
            if (this.myOperatorsToGenerate.size() == this.myExistingOperators.size()) {
                return true;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return false;
    }
    
    @NotNull
    public List<String> getOperatorsToGenerate() {
        List<String> myOperatorsToGenerate;
        try {
            myOperatorsToGenerate = this.myOperatorsToGenerate;
            if (myOperatorsToGenerate == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/OCGenerateComparisonOperatorsContext", "getOperatorsToGenerate"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return myOperatorsToGenerate;
    }
    
    public void setOperatorsToGenerate(@NotNull final List<String> myOperatorsToGenerate) {
        try {
            if (myOperatorsToGenerate == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "operatorsToGenerate", "com/jetbrains/cidr/lang/generate/OCGenerateComparisonOperatorsContext", "setOperatorsToGenerate"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        this.myOperatorsToGenerate = myOperatorsToGenerate;
    }
    
    @NotNull
    public Collection<String> getExistingOperators() {
        List<String> myExistingOperators;
        try {
            myExistingOperators = this.myExistingOperators;
            if (myExistingOperators == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/OCGenerateComparisonOperatorsContext", "getExistingOperators"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return myExistingOperators;
    }
    
    public void setExistingOperators(@NotNull final List<String> myExistingOperators) {
        try {
            if (myExistingOperators == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "existingOperators", "com/jetbrains/cidr/lang/generate/OCGenerateComparisonOperatorsContext", "setExistingOperators"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        this.myExistingOperators = myExistingOperators;
    }
    
    @NotNull
    @Override
    public Collection<OCDeclaratorSymbol> getMemberCandidates() {
        final ArrayList list = new ArrayList<OCDeclaratorSymbol>();
        ArrayList list2;
        try {
            ((OCActionContext<OCStructSymbol, M>)this).getParent().processFields((Processor<OCDeclaratorSymbol>)(p1 -> {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_1        
                //     1: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
                //     4: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.STRUCT_FIELD:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
                //     7: if_acmpne       53
                //    10: aload_1        
                //    11: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.isFriendOrStatic:()Z
                //    14: ifne            53
                //    17: goto            24
                //    20: invokestatic    com/jetbrains/cidr/lang/generate/OCGenerateComparisonOperatorsContext.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
                //    23: athrow         
                //    24: aload_1        
                //    25: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.isUnnamed:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;)Z
                //    28: ifne            53
                //    31: goto            38
                //    34: invokestatic    com/jetbrains/cidr/lang/generate/OCGenerateComparisonOperatorsContext.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
                //    37: athrow         
                //    38: aload_0        
                //    39: aload_1        
                //    40: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
                //    45: pop            
                //    46: goto            53
                //    49: invokestatic    com/jetbrains/cidr/lang/generate/OCGenerateComparisonOperatorsContext.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
                //    52: athrow         
                //    53: iconst_1       
                //    54: ireturn        
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                             
                //  -----  -----  -----  -----  ---------------------------------
                //  0      17     20     24     Ljava/lang/IllegalStateException;
                //  10     31     34     38     Ljava/lang/IllegalStateException;
                //  24     46     49     53     Ljava/lang/IllegalStateException;
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0024:
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
            }));
            list2 = list;
            if (list2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/OCGenerateComparisonOperatorsContext", "getMemberCandidates"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (Collection<OCDeclaratorSymbol>)list2;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
