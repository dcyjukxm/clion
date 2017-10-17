// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.actions;

import java.util.List;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.types.visitors.OCTypeEqualityAfterResolvingVisitor;
import com.jetbrains.cidr.lang.types.OCCppReferenceType;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.refactoring.OCNameSuggester;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;

public class OCGenerateGetterSetterContext extends OCCppActionContext<OCStructSymbol, OCDeclaratorSymbol>
{
    private Mode myMode;
    
    public OCGenerateGetterSetterContext(final OCStructSymbol ocStructSymbol, final PsiElement psiElement, final Mode myMode) {
        super(ocStructSymbol, psiElement);
        this.myMode = myMode;
    }
    
    public boolean hasGetter(final OCDeclaratorSymbol ocDeclaratorSymbol) {
        try {
            if (!((OCActionContext<OCStructSymbol, M>)this).getParent().processFunctions(OCNameSuggester.getCppGetterName(ocDeclaratorSymbol), (Processor<OCFunctionSymbol>)new CommonProcessors.FindFirstProcessor<OCFunctionSymbol>() {
                protected boolean accept(final OCFunctionSymbol ocFunctionSymbol) {
                    return ocFunctionSymbol.getParameterSymbols().isEmpty();
                }
            })) {
                return true;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return false;
    }
    
    public boolean hasSetter(final OCDeclaratorSymbol ocDeclaratorSymbol) {
        try {
            if (!((OCActionContext<OCStructSymbol, M>)this).getParent().processFunctions(OCNameSuggester.getCppSetterName(ocDeclaratorSymbol), (Processor<OCFunctionSymbol>)new CommonProcessors.FindFirstProcessor<OCFunctionSymbol>() {
                protected boolean accept(final OCFunctionSymbol ocFunctionSymbol) {
                    if (ocFunctionSymbol.getParameterSymbols().size() == 1) {
                        OCType ocType;
                        for (ocType = ocFunctionSymbol.getParameterSymbols().get(0).getType(); ocType instanceof OCCppReferenceType; ocType = ((OCCppReferenceType)ocType).getRefType()) {}
                        if (new OCTypeEqualityAfterResolvingVisitor(ocDeclaratorSymbol.getType(), false, false, true, true, true, (PsiFile)ocFunctionSymbol.getContainingOCFile()).equal(ocType)) {
                            return true;
                        }
                    }
                    return false;
                }
            })) {
                return true;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return false;
    }
    
    public boolean needGetter(final OCDeclaratorSymbol p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/generate/actions/OCGenerateGetterSetterContext.myMode:Lcom/jetbrains/cidr/lang/generate/actions/OCGenerateGetterSetterContext$Mode;
        //     4: getstatic       com/jetbrains/cidr/lang/generate/actions/OCGenerateGetterSetterContext$Mode.GETTER:Lcom/jetbrains/cidr/lang/generate/actions/OCGenerateGetterSetterContext$Mode;
        //     7: if_acmpeq       27
        //    10: aload_0        
        //    11: getfield        com/jetbrains/cidr/lang/generate/actions/OCGenerateGetterSetterContext.myMode:Lcom/jetbrains/cidr/lang/generate/actions/OCGenerateGetterSetterContext$Mode;
        //    14: getstatic       com/jetbrains/cidr/lang/generate/actions/OCGenerateGetterSetterContext$Mode.GETTER_SETTER:Lcom/jetbrains/cidr/lang/generate/actions/OCGenerateGetterSetterContext$Mode;
        //    17: if_acmpne       64
        //    20: goto            27
        //    23: invokestatic    com/jetbrains/cidr/lang/generate/actions/OCGenerateGetterSetterContext.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    26: athrow         
        //    27: aload_0        
        //    28: aload_1        
        //    29: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCGenerateGetterSetterContext.hasGetter:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;)Z
        //    32: ifne            64
        //    35: goto            42
        //    38: invokestatic    com/jetbrains/cidr/lang/generate/actions/OCGenerateGetterSetterContext.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    41: athrow         
        //    42: aload_1        
        //    43: invokestatic    com/jetbrains/cidr/lang/generate/actions/OCGenerateGetterSetterContext.b:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;)Z
        //    46: ifne            64
        //    49: goto            56
        //    52: invokestatic    com/jetbrains/cidr/lang/generate/actions/OCGenerateGetterSetterContext.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    55: athrow         
        //    56: iconst_1       
        //    57: goto            65
        //    60: invokestatic    com/jetbrains/cidr/lang/generate/actions/OCGenerateGetterSetterContext.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    63: athrow         
        //    64: iconst_0       
        //    65: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      20     23     27     Ljava/lang/IllegalStateException;
        //  10     35     38     42     Ljava/lang/IllegalStateException;
        //  27     49     52     56     Ljava/lang/IllegalStateException;
        //  42     60     60     64     Ljava/lang/IllegalStateException;
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
    
    public boolean needSetter(final OCDeclaratorSymbol p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/generate/actions/OCGenerateGetterSetterContext.myMode:Lcom/jetbrains/cidr/lang/generate/actions/OCGenerateGetterSetterContext$Mode;
        //     4: getstatic       com/jetbrains/cidr/lang/generate/actions/OCGenerateGetterSetterContext$Mode.SETTER:Lcom/jetbrains/cidr/lang/generate/actions/OCGenerateGetterSetterContext$Mode;
        //     7: if_acmpeq       27
        //    10: aload_0        
        //    11: getfield        com/jetbrains/cidr/lang/generate/actions/OCGenerateGetterSetterContext.myMode:Lcom/jetbrains/cidr/lang/generate/actions/OCGenerateGetterSetterContext$Mode;
        //    14: getstatic       com/jetbrains/cidr/lang/generate/actions/OCGenerateGetterSetterContext$Mode.GETTER_SETTER:Lcom/jetbrains/cidr/lang/generate/actions/OCGenerateGetterSetterContext$Mode;
        //    17: if_acmpne       78
        //    20: goto            27
        //    23: invokestatic    com/jetbrains/cidr/lang/generate/actions/OCGenerateGetterSetterContext.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    26: athrow         
        //    27: aload_0        
        //    28: aload_1        
        //    29: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCGenerateGetterSetterContext.hasSetter:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;)Z
        //    32: ifne            78
        //    35: goto            42
        //    38: invokestatic    com/jetbrains/cidr/lang/generate/actions/OCGenerateGetterSetterContext.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    41: athrow         
        //    42: aload_1        
        //    43: invokestatic    com/jetbrains/cidr/lang/generate/actions/OCGenerateGetterSetterContext.a:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;)Z
        //    46: ifne            78
        //    49: goto            56
        //    52: invokestatic    com/jetbrains/cidr/lang/generate/actions/OCGenerateGetterSetterContext.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    55: athrow         
        //    56: aload_1        
        //    57: invokestatic    com/jetbrains/cidr/lang/generate/actions/OCGenerateGetterSetterContext.c:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;)Z
        //    60: ifne            78
        //    63: goto            70
        //    66: invokestatic    com/jetbrains/cidr/lang/generate/actions/OCGenerateGetterSetterContext.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    69: athrow         
        //    70: iconst_1       
        //    71: goto            79
        //    74: invokestatic    com/jetbrains/cidr/lang/generate/actions/OCGenerateGetterSetterContext.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    77: athrow         
        //    78: iconst_0       
        //    79: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      20     23     27     Ljava/lang/IllegalStateException;
        //  10     35     38     42     Ljava/lang/IllegalStateException;
        //  27     49     52     56     Ljava/lang/IllegalStateException;
        //  42     63     66     70     Ljava/lang/IllegalStateException;
        //  56     74     74     78     Ljava/lang/IllegalStateException;
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
    
    private static boolean b(final OCDeclaratorSymbol ocDeclaratorSymbol) {
        try {
            if (ocDeclaratorSymbol.getArrayLengths().length > 1) {
                return true;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return false;
    }
    
    private static boolean c(final OCDeclaratorSymbol ocDeclaratorSymbol) {
        try {
            if (ocDeclaratorSymbol.getArrayLengths().length > 0) {
                return true;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return false;
    }
    
    private static boolean a(final OCDeclaratorSymbol p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.isConst:()Z
        //     4: ifne            44
        //     7: aload_0        
        //     8: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    11: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //    14: ifeq            52
        //    17: goto            24
        //    20: invokestatic    com/jetbrains/cidr/lang/generate/actions/OCGenerateGetterSetterContext.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    23: athrow         
        //    24: aload_0        
        //    25: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    28: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //    31: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.isReferenceToConst:()Z
        //    34: ifeq            52
        //    37: goto            44
        //    40: invokestatic    com/jetbrains/cidr/lang/generate/actions/OCGenerateGetterSetterContext.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    43: athrow         
        //    44: iconst_1       
        //    45: goto            53
        //    48: invokestatic    com/jetbrains/cidr/lang/generate/actions/OCGenerateGetterSetterContext.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    51: athrow         
        //    52: iconst_0       
        //    53: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      17     20     24     Ljava/lang/IllegalStateException;
        //  7      37     40     44     Ljava/lang/IllegalStateException;
        //  24     48     48     52     Ljava/lang/IllegalStateException;
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
                //     0: aload_2        
                //     1: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
                //     4: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.STRUCT_FIELD:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
                //     7: if_acmpne       69
                //    10: aload_2        
                //    11: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.isUnnamed:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;)Z
                //    14: ifne            69
                //    17: goto            24
                //    20: invokestatic    com/jetbrains/cidr/lang/generate/actions/OCGenerateGetterSetterContext.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
                //    23: athrow         
                //    24: aload_0        
                //    25: aload_2        
                //    26: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCGenerateGetterSetterContext.needGetter:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;)Z
                //    29: ifne            54
                //    32: goto            39
                //    35: invokestatic    com/jetbrains/cidr/lang/generate/actions/OCGenerateGetterSetterContext.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
                //    38: athrow         
                //    39: aload_0        
                //    40: aload_2        
                //    41: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCGenerateGetterSetterContext.needSetter:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;)Z
                //    44: ifeq            69
                //    47: goto            54
                //    50: invokestatic    com/jetbrains/cidr/lang/generate/actions/OCGenerateGetterSetterContext.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
                //    53: athrow         
                //    54: aload_1        
                //    55: aload_2        
                //    56: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
                //    61: pop            
                //    62: goto            69
                //    65: invokestatic    com/jetbrains/cidr/lang/generate/actions/OCGenerateGetterSetterContext.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
                //    68: athrow         
                //    69: iconst_1       
                //    70: ireturn        
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                             
                //  -----  -----  -----  -----  ---------------------------------
                //  0      17     20     24     Ljava/lang/IllegalStateException;
                //  10     32     35     39     Ljava/lang/IllegalStateException;
                //  24     47     50     54     Ljava/lang/IllegalStateException;
                //  39     62     65     69     Ljava/lang/IllegalStateException;
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
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/actions/OCGenerateGetterSetterContext", "getMemberCandidates"));
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
    
    public enum Mode
    {
        GETTER, 
        SETTER, 
        GETTER_SETTER;
    }
}
