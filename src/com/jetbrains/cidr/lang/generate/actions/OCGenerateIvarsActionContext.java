// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.actions;

import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.objc.OCProtocolSymbol;
import java.util.Set;
import com.intellij.util.CommonProcessors;
import java.util.HashSet;
import java.util.Collection;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;

public class OCGenerateIvarsActionContext extends OCObjCActionContext<OCPropertySymbol>
{
    public OCGenerateIvarsActionContext(final OCClassSymbol ocClassSymbol, final OCObjectType ocObjectType, final PsiElement psiElement) {
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
        //     1: getfield        com/jetbrains/cidr/lang/generate/actions/OCGenerateIvarsActionContext.myInterfaceSymbol:Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //     4: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
        //     7: ifeq            65
        //    10: aload_0        
        //    11: getfield        com/jetbrains/cidr/lang/generate/actions/OCGenerateIvarsActionContext.myImplementationSymbol:Lcom/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol;
        //    14: ifnull          65
        //    17: goto            24
        //    20: invokestatic    com/jetbrains/cidr/lang/generate/actions/OCGenerateIvarsActionContext.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    23: athrow         
        //    24: aload_0        
        //    25: getfield        com/jetbrains/cidr/lang/generate/actions/OCGenerateIvarsActionContext.myInterfaceSymbol:Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //    28: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getCategoryName:()Ljava/lang/String;
        //    33: ifnonnull       65
        //    36: goto            43
        //    39: invokestatic    com/jetbrains/cidr/lang/generate/actions/OCGenerateIvarsActionContext.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    42: athrow         
        //    43: aload_0        
        //    44: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCGenerateIvarsActionContext.getType:()Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //    47: ifnull          65
        //    50: goto            57
        //    53: invokestatic    com/jetbrains/cidr/lang/generate/actions/OCGenerateIvarsActionContext.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    56: athrow         
        //    57: iconst_1       
        //    58: goto            66
        //    61: invokestatic    com/jetbrains/cidr/lang/generate/actions/OCGenerateIvarsActionContext.b:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    64: athrow         
        //    65: iconst_0       
        //    66: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      17     20     24     Ljava/lang/IllegalStateException;
        //  10     36     39     43     Ljava/lang/IllegalStateException;
        //  24     50     53     57     Ljava/lang/IllegalStateException;
        //  43     61     61     65     Ljava/lang/IllegalStateException;
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
    
    @Override
    protected Class<OCPropertySymbol> getMemberSymbolClass() {
        return OCPropertySymbol.class;
    }
    
    @NotNull
    @Override
    public Collection<OCPropertySymbol> getMemberCandidates() {
        final OCObjectType type = this.getType();
        final CommonProcessors.CollectProcessor<OCPropertySymbol> collectProcessor = new CommonProcessors.CollectProcessor<OCPropertySymbol>() {
            final /* synthetic */ Set val$names = new HashSet();
            
            protected boolean accept(final OCPropertySymbol ocPropertySymbol) {
                return (((OCSymbolWithParent<T, OCClassSymbol>)ocPropertySymbol).getParent().getName().equals(type.getClassName()) || ocPropertySymbol.getParent() instanceof OCProtocolSymbol) && this.val$names.add(ocPropertySymbol.getName());
            }
        };
        Collection results;
        try {
            type.processMembers(null, OCPropertySymbol.class, (com.intellij.util.Processor<? super OCPropertySymbol>)collectProcessor, true, false);
            results = collectProcessor.getResults();
            if (results == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/actions/OCGenerateIvarsActionContext", "getMemberCandidates"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return (Collection<OCPropertySymbol>)results;
    }
    
    private static IllegalStateException b(final IllegalStateException ex) {
        return ex;
    }
}
