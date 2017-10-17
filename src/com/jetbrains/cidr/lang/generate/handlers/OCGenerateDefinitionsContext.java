// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers;

import com.jetbrains.cidr.lang.generate.actions.OCActionContext;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.util.OCCommonProcessors;
import java.util.ArrayList;
import java.util.Collection;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;
import com.jetbrains.cidr.lang.generate.actions.OCCppActionContext;

public class OCGenerateDefinitionsContext extends OCCppActionContext<OCMembersContainer, OCFunctionSymbol>
{
    public OCGenerateDefinitionsContext(final OCMembersContainer ocMembersContainer, final PsiElement psiElement) {
        super(ocMembersContainer, psiElement);
    }
    
    @NotNull
    @Override
    public Collection<OCFunctionSymbol> getMemberCandidates() {
        final ArrayList list = new ArrayList<OCFunctionSymbol>();
        ArrayList list2;
        try {
            ((OCActionContext<OCMembersContainer, M>)this).getParent().processMembers(null, (Processor)new OCCommonProcessors.TypeFilteredProcessor((com.intellij.util.Processor<Object>)(p1 -> {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_2        
                //     1: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
                //     4: astore_3       
                //     5: aload_3        
                //     6: ifnull          63
                //     9: aload_3        
                //    10: aload_0        
                //    11: invokevirtual   com/jetbrains/cidr/lang/generate/handlers/OCGenerateDefinitionsContext.getParent:()Lcom/jetbrains/cidr/lang/symbols/symtable/OCMembersContainer;
                //    14: invokeinterface com/jetbrains/cidr/lang/symbols/symtable/OCMembersContainer.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
                //    19: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
                //    22: ifeq            63
                //    25: goto            32
                //    28: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateDefinitionsContext.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
                //    31: athrow         
                //    32: aload_2        
                //    33: iconst_1       
                //    34: invokestatic    com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil.shouldGenerateDefinitionsFor:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;Z)Lcom/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$SHOULD_GENERATE_DEFINITION;
                //    37: getstatic       com/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$SHOULD_GENERATE_DEFINITION.NO:Lcom/jetbrains/cidr/lang/generate/OCCppDefinitionsUtil$SHOULD_GENERATE_DEFINITION;
                //    40: if_acmpeq       63
                //    43: goto            50
                //    46: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateDefinitionsContext.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
                //    49: athrow         
                //    50: aload_1        
                //    51: aload_2        
                //    52: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
                //    55: pop            
                //    56: goto            63
                //    59: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateDefinitionsContext.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
                //    62: athrow         
                //    63: iconst_1       
                //    64: ireturn        
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                             
                //  -----  -----  -----  -----  ---------------------------------
                //  5      25     28     32     Ljava/lang/IllegalStateException;
                //  9      43     46     50     Ljava/lang/IllegalStateException;
                //  32     56     59     63     Ljava/lang/IllegalStateException;
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0032:
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
            }), OCFunctionSymbol.class));
            list2 = list;
            if (list2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateDefinitionsContext", "getMemberCandidates"));
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
