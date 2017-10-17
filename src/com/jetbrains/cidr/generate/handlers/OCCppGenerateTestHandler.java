// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.generate.handlers;

import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;
import com.jetbrains.cidr.lang.generate.actions.OCActionContext;
import com.jetbrains.cidr.lang.psi.OCStruct;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.Collections;
import java.util.Collection;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.generate.actions.OCCppActionContext;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;

public abstract class OCCppGenerateTestHandler extends OCAbstractGenerateTestHandler<OCStructSymbol, OCFunctionSymbol, OCCppActionContext<OCStructSymbol, OCFunctionSymbol>>
{
    protected OCCppGenerateTestHandler(final String s, final String s2) {
        super(s, s2);
    }
    
    @Nullable
    @Override
    protected OCCppActionContext<OCStructSymbol, OCFunctionSymbol> evaluateActionContext(final OCStructSymbol ocStructSymbol, final PsiElement psiElement) {
        return new OCCppActionContext<OCStructSymbol, OCFunctionSymbol>(ocStructSymbol, psiElement) {
            @NotNull
            @Override
            public Collection<OCFunctionSymbol> getMemberCandidates() {
                List<OCFunctionSymbol> emptyList;
                try {
                    emptyList = Collections.emptyList();
                    if (emptyList == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/generate/handlers/OCCppGenerateTestHandler$1", "getMemberCandidates"));
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                return emptyList;
            }
            
            @Override
            public boolean isValid() {
                return true;
            }
            
            private static IllegalStateException a(final IllegalStateException ex) {
                return ex;
            }
        };
    }
    
    @Override
    public boolean isValidFor(final Editor p0, final PsiFile p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //     4: ifne            13
        //     7: iconst_0       
        //     8: ireturn        
        //     9: invokestatic    com/jetbrains/cidr/generate/handlers/OCCppGenerateTestHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    12: athrow         
        //    13: aload_2        
        //    14: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    17: astore_3       
        //    18: aload_2        
        //    19: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.isValid:(Lcom/intellij/psi/PsiElement;)Z
        //    22: ifeq            81
        //    25: aload_3        
        //    26: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.isCpp:()Z
        //    31: ifeq            81
        //    34: goto            41
        //    37: invokestatic    com/jetbrains/cidr/generate/handlers/OCCppGenerateTestHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    40: athrow         
        //    41: aload_3        
        //    42: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.isHeader:()Z
        //    47: ifne            81
        //    50: goto            57
        //    53: invokestatic    com/jetbrains/cidr/generate/handlers/OCCppGenerateTestHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    56: athrow         
        //    57: aload_0        
        //    58: aload_1        
        //    59: aload_2        
        //    60: invokespecial   com/jetbrains/cidr/generate/handlers/OCAbstractGenerateTestHandler.isValidFor:(Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;)Z
        //    63: ifeq            81
        //    66: goto            73
        //    69: invokestatic    com/jetbrains/cidr/generate/handlers/OCCppGenerateTestHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    72: athrow         
        //    73: iconst_1       
        //    74: goto            82
        //    77: invokestatic    com/jetbrains/cidr/generate/handlers/OCCppGenerateTestHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    80: athrow         
        //    81: iconst_0       
        //    82: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      9      9      13     Ljava/lang/IllegalArgumentException;
        //  18     34     37     41     Ljava/lang/IllegalArgumentException;
        //  25     50     53     57     Ljava/lang/IllegalArgumentException;
        //  41     66     69     73     Ljava/lang/IllegalArgumentException;
        //  57     77     77     81     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0041:
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
    protected Class<? extends OCSymbolDeclarator> getParentClass() {
        return OCStruct.class;
    }
    
    @Override
    protected PsiElement getElementToModify(@NotNull final OCCppActionContext<OCStructSymbol, OCFunctionSymbol> ocCppActionContext) {
        try {
            if (ocCppActionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/generate/handlers/OCCppGenerateTestHandler", "getElementToModify"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return ocCppActionContext.getContext();
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
