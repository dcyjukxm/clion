// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCFunctionDefinition;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;
import com.jetbrains.cidr.lang.util.OCCommonProcessors;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;

class OCFileImpl$1 extends OCRecursiveVisitor {
    @Override
    public void visitFunctionDeclaration(final OCFunctionDeclaration ocFunctionDeclaration) {
        this.a(((OCSymbolDeclarator<OCSymbol<?>>)ocFunctionDeclaration).getSymbol());
        super.visitFunctionDeclaration(ocFunctionDeclaration);
    }
    
    @Override
    public void visitFunctionDefinition(final OCFunctionDefinition ocFunctionDefinition) {
        this.a(ocFunctionDefinition.getSymbol());
        super.visitFunctionDefinition(ocFunctionDefinition);
    }
    
    @Override
    public void visitClassDeclaration(final OCClassDeclaration ocClassDeclaration) {
        this.a(ocClassDeclaration.getSymbol());
        super.visitClassDeclaration(ocClassDeclaration);
    }
    
    private void a(final OCSymbol<?> ocSymbol) {
        try {
            if (ocSymbol != null) {
                ocSymbol.processSameSymbols((com.intellij.util.Processor<OCSymbol<?>>)(p1 -> {
                    // 
                    // This method could not be decompiled.
                    // 
                    // Original Bytecode:
                    // 
                    //     0: aload_2        
                    //     1: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
                    //     6: astore_3       
                    //     7: aload_3        
                    //     8: ifnull          65
                    //    11: aload_3        
                    //    12: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.isHeader:()Z
                    //    17: aload_0        
                    //    18: getfield        com/jetbrains/cidr/lang/psi/impl/OCFileImpl$1.this$0:Lcom/jetbrains/cidr/lang/psi/impl/OCFileImpl;
                    //    21: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCFileImpl.isHeader:()Z
                    //    24: if_icmpeq       65
                    //    27: goto            34
                    //    30: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCFileImpl$1.b:(Lcom/jetbrains/cidr/lang/psi/impl/OCFileImpl$1StopException;)Lcom/jetbrains/cidr/lang/psi/impl/OCFileImpl$1StopException;
                    //    33: athrow         
                    //    34: aload_1        
                    //    35: aload_3        
                    //    36: invokevirtual   com/jetbrains/cidr/lang/util/OCCommonProcessors$OrderedProcessor.process:(Ljava/lang/Object;)Z
                    //    39: ifne            65
                    //    42: goto            49
                    //    45: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCFileImpl$1.b:(Lcom/jetbrains/cidr/lang/psi/impl/OCFileImpl$1StopException;)Lcom/jetbrains/cidr/lang/psi/impl/OCFileImpl$1StopException;
                    //    48: athrow         
                    //    49: new             Lcom/jetbrains/cidr/lang/psi/impl/OCFileImpl$1StopException;
                    //    52: dup            
                    //    53: aload_0        
                    //    54: getfield        com/jetbrains/cidr/lang/psi/impl/OCFileImpl$1.this$0:Lcom/jetbrains/cidr/lang/psi/impl/OCFileImpl;
                    //    57: invokespecial   com/jetbrains/cidr/lang/psi/impl/OCFileImpl$1StopException.<init>:(Lcom/jetbrains/cidr/lang/psi/impl/OCFileImpl;)V
                    //    60: athrow         
                    //    61: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCFileImpl$1.b:(Lcom/jetbrains/cidr/lang/psi/impl/OCFileImpl$1StopException;)Lcom/jetbrains/cidr/lang/psi/impl/OCFileImpl$1StopException;
                    //    64: athrow         
                    //    65: iconst_1       
                    //    66: ireturn        
                    //    Exceptions:
                    //  Try           Handler
                    //  Start  End    Start  End    Type                                                        
                    //  -----  -----  -----  -----  ------------------------------------------------------------
                    //  7      27     30     34     Lcom/jetbrains/cidr/lang/psi/impl/OCFileImpl$1StopException;
                    //  11     42     45     49     Lcom/jetbrains/cidr/lang/psi/impl/OCFileImpl$1StopException;
                    //  34     61     61     65     Lcom/jetbrains/cidr/lang/psi/impl/OCFileImpl$1StopException;
                    // 
                    // The error that occurred was:
                    // 
                    // java.lang.IllegalStateException: Expression is linked from several locations: Label_0034:
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
            }
        }
        catch (1StopException ex) {
            throw b(ex);
        }
    }
    
    private static 1StopException b(final 1StopException ex) {
        return ex;
    }
}