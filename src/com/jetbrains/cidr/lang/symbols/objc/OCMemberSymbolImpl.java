// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.objc;

import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCSymbolImpl;

public abstract class OCMemberSymbolImpl extends OCSymbolImpl<PsiElement> implements OCMemberSymbol
{
    protected OCClassSymbol myParent;
    
    public OCMemberSymbolImpl() {
    }
    
    public OCMemberSymbolImpl(final Project project, final VirtualFile virtualFile, final long n, @Nullable final String s, @NotNull final List<String> list, @NotNull final OCClassSymbol myParent) {
        if (list == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "attributes", "com/jetbrains/cidr/lang/symbols/objc/OCMemberSymbolImpl", "<init>"));
        }
        if (myParent == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parent", "com/jetbrains/cidr/lang/symbols/objc/OCMemberSymbolImpl", "<init>"));
        }
        super(project, virtualFile, n, s, list);
        this.myParent = myParent;
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/symbols/objc/OCMemberSymbolImpl", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/symbols/objc/OCMemberSymbolImpl", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/symbols/objc/OCMemberSymbolImpl", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        try {
            if (!super.deepEqualStep(comparator, o, o2)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        return comparator.equalObjects(((OCMemberSymbolImpl)o).myParent, (DeepEqual.Equality<Object>)((OCMemberSymbolImpl)o2).myParent);
    }
    
    @Override
    public OCClassSymbol getParent() {
        return this.myParent;
    }
    
    @NotNull
    @Override
    public String getNameWithParent() {
        String string;
        try {
            string = this.myParent.getName() + "." + this.myName;
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/objc/OCMemberSymbolImpl", "getNameWithParent"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return string;
    }
    
    @Nullable
    @Override
    public OCMemberSymbol getAssociatedSymbol() {
        final OCClassSymbol ocClassSymbol = (OCClassSymbol)this.getParent().getAssociatedSymbol();
        try {
            if (ocClassSymbol == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final CommonProcessors.FindFirstProcessor findFirstProcessor = new CommonProcessors.FindFirstProcessor();
        ocClassSymbol.processMembers(this.getName(), (Class<? extends OCMemberSymbol>)this.getClass(), (com.intellij.util.Processor<? super OCMemberSymbol>)findFirstProcessor);
        return (OCMemberSymbol)findFirstProcessor.getFoundValue();
    }
    
    @Override
    public boolean processSameSymbols(final Processor<OCSymbol> processor) {
        return this.myParent.processMembersInAllCategories(this.myName, (Class<? extends OCMemberSymbol>)this.getClass(), (com.intellij.util.Processor<? super OCMemberSymbol>)processor, false);
    }
    
    @Override
    public boolean isSameSymbol(final OCSymbol p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;
        //     4: ifeq            66
        //     7: aload_0        
        //     8: getfield        com/jetbrains/cidr/lang/symbols/objc/OCMemberSymbolImpl.myName:Ljava/lang/String;
        //    11: aload_1        
        //    12: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //    17: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    20: ifeq            66
        //    23: goto            30
        //    26: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCMemberSymbolImpl.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    29: athrow         
        //    30: aload_0        
        //    31: getfield        com/jetbrains/cidr/lang/symbols/objc/OCMemberSymbolImpl.myParent:Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //    34: aload_1        
        //    35: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;
        //    38: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    43: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.isSameSymbol:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //    48: ifeq            66
        //    51: goto            58
        //    54: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCMemberSymbolImpl.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    57: athrow         
        //    58: iconst_1       
        //    59: goto            67
        //    62: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCMemberSymbolImpl.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    65: athrow         
        //    66: iconst_0       
        //    67: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      23     26     30     Ljava/lang/IllegalArgumentException;
        //  7      51     54     58     Ljava/lang/IllegalArgumentException;
        //  30     62     62     66     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0030:
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
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
