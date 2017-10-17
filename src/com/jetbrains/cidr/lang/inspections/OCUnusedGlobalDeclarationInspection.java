// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCTemplateParameterList;
import com.jetbrains.cidr.lang.psi.OCParameterDeclaration;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.psi.OCDeclarator;

public class OCUnusedGlobalDeclarationInspection extends OCUnusedCodeInspection
{
    @NotNull
    @Override
    public UnusedVisitor buildVisitor() {
        UnusedVisitor unusedVisitor;
        try {
            unusedVisitor = new UnusedVisitor() {
                @Override
                public void visitDeclarator(final OCDeclarator ocDeclarator) {
                    final OCSymbol symbol = this.getSymbol(ocDeclarator);
                    if (symbol != null && symbol.isGlobal()) {
                        if (!this.myOnTheFly && (symbol instanceof OCDeclaratorSymbol || symbol instanceof OCFunctionSymbol) && !symbol.processSameSymbols((com.intellij.util.Processor<OCSymbol<OCElement>>)new CommonProcessors.FindFirstProcessor<OCSymbol>() {
                            protected boolean accept(final OCSymbol ocSymbol) {
                                return (symbol instanceof OCFunctionSymbol && symbol.isDefinition() && ocSymbol.isPredeclaration()) || (symbol instanceof OCDeclaratorSymbol && symbol.isPredeclaration() && ocSymbol.isDefinition()) || (ocSymbol instanceof OCDeclaratorSymbol && ((OCDeclaratorSymbol)ocSymbol).isExtern());
                            }
                        })) {
                            return;
                        }
                        if (symbol instanceof OCFunctionSymbol && a((OCFunctionSymbol)symbol)) {
                            return;
                        }
                        if (ocDeclarator.getParent() instanceof OCParameterDeclaration && ocDeclarator.getParent().getParent() instanceof OCTemplateParameterList && OCUnusedTemplateParameterInspection.isTraitTemplateParameter((OCTemplateParameterList)ocDeclarator.getParent().getParent())) {
                            return;
                        }
                        this.checkSymbolUsed((PsiElement)ocDeclarator, symbol, this.myHasWrites);
                    }
                }
            };
            if (unusedVisitor == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCUnusedGlobalDeclarationInspection", "buildVisitor"));
            }
        }
        catch (IllegalStateException ex) {
            throw c(ex);
        }
        return unusedVisitor;
    }
    
    private static boolean a(final OCFunctionSymbol p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isMainFunction:()Z
        //     4: ifeq            13
        //     7: iconst_1       
        //     8: ireturn        
        //     9: invokestatic    com/jetbrains/cidr/lang/inspections/OCUnusedGlobalDeclarationInspection.c:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    12: athrow         
        //    13: aload_0        
        //    14: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    17: astore_1       
        //    18: aload_1        
        //    19: ifnull          38
        //    22: aload_1        
        //    23: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.isCpp:()Z
        //    28: ifeq            100
        //    31: goto            38
        //    34: invokestatic    com/jetbrains/cidr/lang/inspections/OCUnusedGlobalDeclarationInspection.c:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    37: athrow         
        //    38: aload_0        
        //    39: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isCppOperator:()Z
        //    42: ifne            80
        //    45: goto            52
        //    48: invokestatic    com/jetbrains/cidr/lang/inspections/OCUnusedGlobalDeclarationInspection.c:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    51: athrow         
        //    52: aload_0        
        //    53: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isCppConstructor:()Z
        //    56: ifne            80
        //    59: goto            66
        //    62: invokestatic    com/jetbrains/cidr/lang/inspections/OCUnusedGlobalDeclarationInspection.c:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    65: athrow         
        //    66: aload_0        
        //    67: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isCppDestructor:()Z
        //    70: ifeq            86
        //    73: goto            80
        //    76: invokestatic    com/jetbrains/cidr/lang/inspections/OCUnusedGlobalDeclarationInspection.c:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    79: athrow         
        //    80: iconst_1       
        //    81: ireturn        
        //    82: invokestatic    com/jetbrains/cidr/lang/inspections/OCUnusedGlobalDeclarationInspection.c:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    85: athrow         
        //    86: aload_0        
        //    87: iconst_0       
        //    88: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionAncestorsQuery.findFirstVirtual:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;Z)Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //    91: ifnull          100
        //    94: iconst_1       
        //    95: ireturn        
        //    96: invokestatic    com/jetbrains/cidr/lang/inspections/OCUnusedGlobalDeclarationInspection.c:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    99: athrow         
        //   100: iconst_0       
        //   101: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      9      9      13     Ljava/lang/IllegalStateException;
        //  18     31     34     38     Ljava/lang/IllegalStateException;
        //  22     45     48     52     Ljava/lang/IllegalStateException;
        //  38     59     62     66     Ljava/lang/IllegalStateException;
        //  52     73     76     80     Ljava/lang/IllegalStateException;
        //  66     82     82     86     Ljava/lang/IllegalStateException;
        //  86     96     96     100    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0038:
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
    
    private static IllegalStateException c(final IllegalStateException ex) {
        return ex;
    }
}
