// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.actions;

import java.util.Collection;
import java.util.Collections;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import java.util.List;
import com.jetbrains.cidr.lang.settings.OCOption;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;

public abstract class OCActionContext<P extends OCMembersContainer, M extends OCSymbolWithParent<?, ?>>
{
    @Nullable
    private final P myParent;
    @NotNull
    private final PsiElement myContext;
    @Nullable
    private Map<OCOption, Object> myOptionValues;
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public OCActionContext(@Nullable final P myParent, @NotNull final PsiElement myContext) {
        if (myContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/generate/actions/OCActionContext", "<init>"));
        }
        this.myParent = myParent;
        this.myContext = myContext;
    }
    
    @NotNull
    public final P getParent() {
        OCMembersContainer myParent = null;
        Label_0032: {
            Label_0020: {
                try {
                    if (OCActionContext.$assertionsDisabled) {
                        break Label_0032;
                    }
                    final OCActionContext ocActionContext = this;
                    final OCMembersContainer ocMembersContainer = ocActionContext.myParent;
                    if (ocMembersContainer == null) {
                        break Label_0020;
                    }
                    break Label_0032;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final OCActionContext ocActionContext = this;
                    final OCMembersContainer ocMembersContainer = ocActionContext.myParent;
                    if (ocMembersContainer == null) {
                        throw new AssertionError();
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            try {
                myParent = this.myParent;
                if (myParent == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/actions/OCActionContext", "getParent"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return (P)myParent;
    }
    
    @NotNull
    public final PsiElement getContext() {
        PsiElement myContext;
        try {
            myContext = this.myContext;
            if (myContext == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/actions/OCActionContext", "getContext"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myContext;
    }
    
    public boolean isValid() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/generate/actions/OCActionContext.myParent:Lcom/jetbrains/cidr/lang/symbols/symtable/OCMembersContainer;
        //     4: ifnull          54
        //     7: aload_0        
        //     8: getfield        com/jetbrains/cidr/lang/generate/actions/OCActionContext.myParent:Lcom/jetbrains/cidr/lang/symbols/symtable/OCMembersContainer;
        //    11: instanceof      Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    14: ifeq            46
        //    17: goto            24
        //    20: invokestatic    com/jetbrains/cidr/lang/generate/actions/OCActionContext.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    23: athrow         
        //    24: aload_0        
        //    25: getfield        com/jetbrains/cidr/lang/generate/actions/OCActionContext.myParent:Lcom/jetbrains/cidr/lang/symbols/symtable/OCMembersContainer;
        //    28: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    31: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isPredeclaration:()Z
        //    36: ifne            54
        //    39: goto            46
        //    42: invokestatic    com/jetbrains/cidr/lang/generate/actions/OCActionContext.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    45: athrow         
        //    46: iconst_1       
        //    47: goto            55
        //    50: invokestatic    com/jetbrains/cidr/lang/generate/actions/OCActionContext.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    53: athrow         
        //    54: iconst_0       
        //    55: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      17     20     24     Ljava/lang/IllegalArgumentException;
        //  7      39     42     46     Ljava/lang/IllegalArgumentException;
        //  24     50     50     54     Ljava/lang/IllegalArgumentException;
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
    
    public List<? extends OCSymbol> getSymbolsToModify() {
        try {
            if (this.myParent instanceof OCSymbol) {
                return Collections.singletonList((OCSymbol)this.myParent);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return Collections.emptyList();
    }
    
    @Nullable
    public Map<OCOption, Object> getOptionValues() {
        return this.myOptionValues;
    }
    
    public void setOptionValues(@Nullable final Map<OCOption, Object> myOptionValues) {
        this.myOptionValues = myOptionValues;
    }
    
    @NotNull
    public abstract Collection<M> getMemberCandidates();
    
    public abstract Map<OCSymbol, OCSymbol> createParentsMap(final Collection<M> p0);
    
    public String getParentNameUppercase() {
        return "'" + this.getParent().getPresentableName() + "'";
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCActionContext.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
