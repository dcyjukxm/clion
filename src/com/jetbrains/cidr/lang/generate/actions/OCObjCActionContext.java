// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.actions;

import java.util.Iterator;
import com.jetbrains.cidr.lang.symbols.objc.OCProtocolSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCInterfaceSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.Processor;
import com.intellij.util.CommonProcessors;
import java.util.Collection;
import java.util.Arrays;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import java.util.List;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.symbols.objc.OCImplementationSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;

public class OCObjCActionContext<M extends OCMemberSymbol> extends OCActionContext<OCClassSymbol, M>
{
    protected OCClassSymbol myInterfaceSymbol;
    protected OCImplementationSymbol myImplementationSymbol;
    protected OCObjectType myType;
    
    public OCObjCActionContext(final OCClassSymbol ocClassSymbol, final PsiElement psiElement, final OCObjectType myType) {
        super(ocClassSymbol, psiElement);
        OCClassSymbol interfaceOrProtocol;
        if (ocClassSymbol != null) {
            interfaceOrProtocol = ocClassSymbol.getInterfaceOrProtocol();
        }
        else {
            interfaceOrProtocol = null;
        }
        OCImplementationSymbol implementation = null;
        Label_0043: {
            try {
                this.myInterfaceSymbol = interfaceOrProtocol;
                if (ocClassSymbol != null) {
                    implementation = ocClassSymbol.getImplementation();
                    break Label_0043;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            implementation = null;
        }
        this.myImplementationSymbol = implementation;
        this.myType = myType;
    }
    
    @Override
    public boolean isValid() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokespecial   com/jetbrains/cidr/lang/generate/actions/OCActionContext.isValid:()Z
        //     4: ifeq            57
        //     7: aload_0        
        //     8: getfield        com/jetbrains/cidr/lang/generate/actions/OCObjCActionContext.myImplementationSymbol:Lcom/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol;
        //    11: ifnull          57
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/generate/actions/OCObjCActionContext.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    20: athrow         
        //    21: aload_0        
        //    22: getfield        com/jetbrains/cidr/lang/generate/actions/OCObjCActionContext.myInterfaceSymbol:Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //    25: ifnull          57
        //    28: goto            35
        //    31: invokestatic    com/jetbrains/cidr/lang/generate/actions/OCObjCActionContext.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    34: athrow         
        //    35: aload_0        
        //    36: invokevirtual   com/jetbrains/cidr/lang/generate/actions/OCObjCActionContext.getType:()Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //    39: ifnull          57
        //    42: goto            49
        //    45: invokestatic    com/jetbrains/cidr/lang/generate/actions/OCObjCActionContext.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    48: athrow         
        //    49: iconst_1       
        //    50: goto            58
        //    53: invokestatic    com/jetbrains/cidr/lang/generate/actions/OCObjCActionContext.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    56: athrow         
        //    57: iconst_0       
        //    58: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      14     17     21     Ljava/lang/IllegalStateException;
        //  7      28     31     35     Ljava/lang/IllegalStateException;
        //  21     42     45     49     Ljava/lang/IllegalStateException;
        //  35     53     53     57     Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0021:
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
    public List<? extends OCSymbol> getSymbolsToModify() {
        return Arrays.asList(this.myInterfaceSymbol, this.myImplementationSymbol);
    }
    
    @NotNull
    @Override
    public Collection<M> getMemberCandidates() {
        final CommonProcessors.CollectProcessor collectProcessor = new CommonProcessors.CollectProcessor();
        Collection results;
        try {
            this.myInterfaceSymbol.processMembers((Class<OCMemberSymbol>)this.getMemberSymbolClass(), (com.intellij.util.Processor<? super OCMemberSymbol>)collectProcessor);
            results = collectProcessor.getResults();
            if (results == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/actions/OCObjCActionContext", "getMemberCandidates"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (Collection<M>)results;
    }
    
    protected Class getMemberSymbolClass() {
        return OCMemberSymbol.class;
    }
    
    public OCClassSymbol getInterfaceSymbol() {
        return this.myInterfaceSymbol;
    }
    
    public OCImplementationSymbol getImplementationSymbol() {
        return this.myImplementationSymbol;
    }
    
    public OCObjectType getType() {
        return this.myType;
    }
    
    @Override
    public Map<OCSymbol, OCSymbol> createParentsMap(final Collection<M> collection) {
        final HashMap<OCSymbolWithParent<T, OCClassSymbol>, OCInterfaceSymbol> hashMap = new HashMap<OCSymbolWithParent<T, OCClassSymbol>, OCInterfaceSymbol>();
        for (final OCMemberSymbol ocMemberSymbol : collection) {
            final OCClassSymbol ocClassSymbol = ((OCSymbolWithParent<T, OCClassSymbol>)ocMemberSymbol).getParent();
            try {
                hashMap.put((OCSymbolWithParent<T, OCClassSymbol>)ocMemberSymbol, (OCInterfaceSymbol)ocClassSymbol);
                if (ocClassSymbol.getCategoryName() == null) {
                    continue;
                }
                hashMap.put((OCSymbolWithParent<T, OCClassSymbol>)ocClassSymbol, ocClassSymbol.getMainInterface());
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
        }
        for (OCObjectType ocObjectType = this.myType; ocObjectType != null; ocObjectType = ocObjectType.getSuperType()) {
            for (final OCProtocolSymbol ocProtocolSymbol : ocObjectType.getAllProtocols()) {
                try {
                    if (hashMap.containsKey(ocProtocolSymbol)) {
                        continue;
                    }
                    hashMap.put((OCSymbolWithParent<T, OCClassSymbol>)ocProtocolSymbol, (OCInterfaceSymbol)ocObjectType.getClassSymbol());
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
            }
        }
        return (Map<OCSymbol, OCSymbol>)hashMap;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
