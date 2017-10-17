// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.objc;

import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.symbols.symtable.OCGlobalProjectSymbolsCache;
import com.intellij.util.Processor;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.resolve.OCResolveUtil;
import com.intellij.openapi.util.Condition;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import java.util.Collections;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.intellij.util.containers.MostlySingularMultiMap;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;

public class OCImplementationSymbol extends OCClassSymbolImpl
{
    public OCImplementationSymbol() {
    }
    
    public OCImplementationSymbol(@NotNull final Project project, @Nullable final VirtualFile virtualFile, final long n, @NotNull final String s, @NotNull final List<String> list, @Nullable final String s2, @Nullable final MostlySingularMultiMap<String, OCMemberSymbol> mostlySingularMultiMap, @Nullable final OCReferenceType ocReferenceType) {
        if (project == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol", "<init>"));
        }
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol", "<init>"));
        }
        if (list == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "attributes", "com/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol", "<init>"));
        }
        super(project, virtualFile, n, s, list, s2, mostlySingularMultiMap, Collections.emptyList(), ocReferenceType);
    }
    
    @NotNull
    @Override
    public OCSymbolKind getKind() {
        OCSymbolKind implementation;
        try {
            implementation = OCSymbolKind.IMPLEMENTATION;
            if (implementation == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol", "getKind"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return implementation;
    }
    
    @Override
    public boolean isDefinition() {
        return true;
    }
    
    @Nullable
    @Override
    public OCInterfaceSymbol getInterface() {
        OCInterfaceSymbol ocInterfaceSymbol = this.getInterface(false, this.getCategoryName());
        try {
            if (ocInterfaceSymbol != null || this.getCategoryName() == null) {
                return ocInterfaceSymbol;
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        ocInterfaceSymbol = this.getInterface(false, null);
        return ocInterfaceSymbol;
    }
    
    @Nullable
    public OCInterfaceSymbol getInterface(final boolean b, @Nullable final String s) {
        final Condition condition = p1 -> {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_1        
            //     1: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
            //     4: ifeq            54
            //     7: aload_1        
            //     8: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isPredeclaration:()Z
            //    13: ifne            54
            //    16: goto            23
            //    19: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    22: athrow         
            //    23: aload_0        
            //    24: aload_1        
            //    25: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
            //    28: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol.getCategoryName:()Ljava/lang/String;
            //    33: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/String;Ljava/lang/String;)Z
            //    36: ifeq            54
            //    39: goto            46
            //    42: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    45: athrow         
            //    46: iconst_1       
            //    47: goto            55
            //    50: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    53: athrow         
            //    54: iconst_0       
            //    55: ireturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  0      16     19     23     Ljava/lang/IllegalArgumentException;
            //  7      39     42     46     Ljava/lang/IllegalArgumentException;
            //  23     50     50     54     Ljava/lang/IllegalArgumentException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0023:
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
        };
        final CommonProcessors.FindFirstProcessor<OCSymbol> findFirstProcessor = new CommonProcessors.FindFirstProcessor<OCSymbol>() {
            protected boolean accept(final OCSymbol ocSymbol) {
                return condition.value((Object)ocSymbol);
            }
        };
        final OCFile containingOCFile = this.getContainingOCFile();
        try {
            if (containingOCFile == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        Label_0071: {
            try {
                OCResolveUtil.processGlobalSymbols(this.myName, null, containingOCFile, this.getOffset(), (Processor<OCSymbol>)findFirstProcessor);
                if (findFirstProcessor.isFound()) {
                    return (OCInterfaceSymbol)findFirstProcessor.getFoundValue();
                }
                final boolean b2 = b;
                if (b2) {
                    break Label_0071;
                }
                return (OCInterfaceSymbol)findFirstProcessor.getFoundValue();
            }
            catch (IllegalArgumentException ex2) {
                throw d(ex2);
            }
            try {
                final boolean b2 = b;
                if (b2) {
                    return (OCInterfaceSymbol)OCGlobalProjectSymbolsCache.findNearestTopLevelSymbol(this.myProject, this.myName, (Condition<OCSymbol>)condition, this.myFile);
                }
            }
            catch (IllegalArgumentException ex3) {
                throw d(ex3);
            }
        }
        return (OCInterfaceSymbol)findFirstProcessor.getFoundValue();
    }
    
    @Override
    public OCSymbol getAssociatedSymbol() {
        return this.getInterface();
    }
    
    @Override
    public OCImplementationSymbol getImplementation() {
        return this;
    }
    
    private static IllegalArgumentException d(final IllegalArgumentException ex) {
        return ex;
    }
}
