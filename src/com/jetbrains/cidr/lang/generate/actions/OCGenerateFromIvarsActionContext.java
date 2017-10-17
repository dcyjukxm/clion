// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.actions;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.util.OCCommonProcessors;
import com.intellij.openapi.util.Conditions;
import com.intellij.openapi.util.Condition;
import java.util.Set;
import com.intellij.util.CommonProcessors;
import java.util.HashSet;
import java.util.Collection;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;

public class OCGenerateFromIvarsActionContext extends OCObjCActionContext<OCInstanceVariableSymbol>
{
    public OCGenerateFromIvarsActionContext(final OCClassSymbol ocClassSymbol, final OCObjectType ocObjectType, final PsiElement psiElement) {
        super(ocClassSymbol, psiElement, ocObjectType);
    }
    
    @Override
    public boolean isValid() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/generate/actions/OCGenerateFromIvarsActionContext.myInterfaceSymbol:Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //     4: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
        //     7: ifeq            51
        //    10: aload_0        
        //    11: getfield        com/jetbrains/cidr/lang/generate/actions/OCGenerateFromIvarsActionContext.myInterfaceSymbol:Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //    14: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getCategoryName:()Ljava/lang/String;
        //    19: ifnonnull       51
        //    22: goto            29
        //    25: invokestatic    com/jetbrains/cidr/lang/generate/actions/OCGenerateFromIvarsActionContext.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    28: athrow         
        //    29: aload_0        
        //    30: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCGenerateFromIvarsActionContext.getType:()Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //    33: ifnull          51
        //    36: goto            43
        //    39: invokestatic    com/jetbrains/cidr/lang/generate/actions/OCGenerateFromIvarsActionContext.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    42: athrow         
        //    43: iconst_1       
        //    44: goto            52
        //    47: invokestatic    com/jetbrains/cidr/lang/generate/actions/OCGenerateFromIvarsActionContext.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    50: athrow         
        //    51: iconst_0       
        //    52: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      22     25     29     Ljava/lang/IllegalStateException;
        //  10     36     39     43     Ljava/lang/IllegalStateException;
        //  29     47     47     51     Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0029:
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
    protected Class getMemberSymbolClass() {
        return OCInstanceVariableSymbol.class;
    }
    
    @NotNull
    @Override
    public Collection<OCInstanceVariableSymbol> getMemberCandidates() {
        final CommonProcessors.CollectProcessor<OCInstanceVariableSymbol> collectProcessor = new CommonProcessors.CollectProcessor<OCInstanceVariableSymbol>() {
            final /* synthetic */ Set val$processedNames = new HashSet();
            
            protected boolean accept(final OCInstanceVariableSymbol ocInstanceVariableSymbol) {
                return !ocInstanceVariableSymbol.isForbiddenClang4ImplicitIvar() && this.val$processedNames.add(ocInstanceVariableSymbol.getName()) && OCGenerateFromIvarsActionContext.this.showSymbol(ocInstanceVariableSymbol);
            }
        };
        final OCCommonProcessors.OrderedProcessor orderedProcessor = new OCCommonProcessors.OrderedProcessor<Object>((com.intellij.util.Processor<? super Object>)collectProcessor, (com.intellij.openapi.util.Condition<Object>[])new Condition[] { ocInstanceVariableSymbol -> {
                try {
                    if (ocInstanceVariableSymbol.getGeneratedFromProperty() == null) {
                        return true;
                    }
                }
                catch (IllegalStateException ex) {
                    throw b(ex);
                }
                return false;
            }, Conditions.alwaysTrue() });
        Collection results;
        try {
            this.myInterfaceSymbol.processMembersInAllCategories((String)null, (Class<? extends OCMemberSymbol>)OCInstanceVariableSymbol.class, (com.intellij.util.Processor<? super OCMemberSymbol>)orderedProcessor, false);
            orderedProcessor.finish();
            results = collectProcessor.getResults();
            if (results == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/actions/OCGenerateFromIvarsActionContext", "getMemberCandidates"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return (Collection<OCInstanceVariableSymbol>)results;
    }
    
    protected boolean showSymbol(final OCInstanceVariableSymbol ocInstanceVariableSymbol) {
        return true;
    }
    
    private static IllegalStateException b(final IllegalStateException ex) {
        return ex;
    }
}
