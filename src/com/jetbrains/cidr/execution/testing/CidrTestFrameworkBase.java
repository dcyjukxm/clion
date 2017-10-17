// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing;

import com.jetbrains.cidr.lang.symbols.symtable.FileSymbolTablesCache;
import com.intellij.psi.util.CachedValueProvider;
import com.intellij.psi.util.CachedValuesManager;
import com.jetbrains.cidr.lang.symbols.symtable.OCFileSymbols;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.symbols.cpp.OCMacroSymbol;
import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.openapi.extensions.Extensions;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.symtable.AbstractGlobalProjectSymbolsCache;
import com.jetbrains.cidr.lang.symbols.symtable.OCGlobalProjectSymbolsCache;
import com.intellij.openapi.util.Ref;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Contract;
import com.intellij.openapi.util.ModificationTracker;
import com.intellij.util.Function;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.CachedValue;
import com.intellij.openapi.util.NotNullLazyKey;
import com.jetbrains.cidr.lang.OCTestFramework;

public abstract class CidrTestFrameworkBase implements OCTestFramework
{
    private final NotNullLazyKey<CachedValue<Boolean>, PsiFile> myFrameworkInFileKey;
    private final Class<? extends PsiFile> myFrameworkFileClass;
    
    @Contract(pure = true)
    @NotNull
    protected static Function<PsiFile, ModificationTracker[]> getCidrTopLevelChangesModificationTracker() {
        Function function;
        try {
            function = (psiFile -> new ModificationTracker[] { FileSymbolTablesCache.getInstance(psiFile.getProject()).getCidrOutOfBlockModificationTracker() });
            if (function == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/CidrTestFrameworkBase", "getCidrTopLevelChangesModificationTracker"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (Function<PsiFile, ModificationTracker[]>)function;
    }
    
    protected CidrTestFrameworkBase(@NotNull final String s, @NotNull final Class<? extends PsiFile> myFrameworkFileClass, @NotNull final Function<PsiFile, ModificationTracker[]> function) {
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "testFrameworkId", "com/jetbrains/cidr/execution/testing/CidrTestFrameworkBase", "<init>"));
        }
        if (myFrameworkFileClass == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fileClass", "com/jetbrains/cidr/execution/testing/CidrTestFrameworkBase", "<init>"));
        }
        if (function == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "modificationTrackerProducer", "com/jetbrains/cidr/execution/testing/CidrTestFrameworkBase", "<init>"));
        }
        this.myFrameworkFileClass = myFrameworkFileClass;
        this.myFrameworkInFileKey = (NotNullLazyKey<CachedValue<Boolean>, PsiFile>)NotNullLazyKey.create(s, psiFile -> {
            try {
                if (function == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "modificationTrackerProducer", "com/jetbrains/cidr/execution/testing/CidrTestFrameworkBase", "lambda$new$2"));
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            return CachedValuesManager.getManager(psiFile.getProject()).createCachedValue(() -> {
                try {
                    if (function == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "modificationTrackerProducer", "com/jetbrains/cidr/execution/testing/CidrTestFrameworkBase", "lambda$null$1"));
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                return new CachedValueProvider.Result((Object)this.checkFrameworkAvailability(psiFile), (Object[])function.fun((Object)psiFile));
            }, false);
        });
    }
    
    @Override
    public boolean isAvailable(@Nullable final PsiFile p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnull          54
        //     4: aload_0        
        //     5: getfield        com/jetbrains/cidr/execution/testing/CidrTestFrameworkBase.myFrameworkFileClass:Ljava/lang/Class;
        //     8: aload_1        
        //     9: invokevirtual   java/lang/Class.isInstance:(Ljava/lang/Object;)Z
        //    12: ifeq            62
        //    15: goto            22
        //    18: invokestatic    com/jetbrains/cidr/execution/testing/CidrTestFrameworkBase.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    21: athrow         
        //    22: aload_0        
        //    23: getfield        com/jetbrains/cidr/execution/testing/CidrTestFrameworkBase.myFrameworkInFileKey:Lcom/intellij/openapi/util/NotNullLazyKey;
        //    26: aload_1        
        //    27: invokevirtual   com/intellij/openapi/util/NotNullLazyKey.getValue:(Lcom/intellij/openapi/util/UserDataHolder;)Ljava/lang/Object;
        //    30: checkcast       Lcom/intellij/psi/util/CachedValue;
        //    33: invokeinterface com/intellij/psi/util/CachedValue.getValue:()Ljava/lang/Object;
        //    38: checkcast       Ljava/lang/Boolean;
        //    41: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //    44: ifeq            62
        //    47: goto            54
        //    50: invokestatic    com/jetbrains/cidr/execution/testing/CidrTestFrameworkBase.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    53: athrow         
        //    54: iconst_1       
        //    55: goto            63
        //    58: invokestatic    com/jetbrains/cidr/execution/testing/CidrTestFrameworkBase.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    61: athrow         
        //    62: iconst_0       
        //    63: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      15     18     22     Ljava/lang/IllegalStateException;
        //  4      47     50     54     Ljava/lang/IllegalStateException;
        //  22     58     58     62     Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0022:
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
    
    protected abstract boolean checkFrameworkAvailability(@NotNull final PsiFile p0);
    
    protected static boolean checkFrameworkAvailabilityUsingImportedMacro(@NotNull final PsiFile psiFile, @NotNull final String s) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/execution/testing/CidrTestFrameworkBase", "checkFrameworkAvailabilityUsingImportedMacro"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "macroName", "com/jetbrains/cidr/execution/testing/CidrTestFrameworkBase", "checkFrameworkAvailabilityUsingImportedMacro"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        final Ref ref = new Ref();
        try {
            AbstractGlobalProjectSymbolsCache.processTopLevelAndMemberSymbols(OCGlobalProjectSymbolsCache.class, psiFile.getProject(), (com.intellij.util.Processor<? super OCSymbol>)(ocSymbol -> {
                try {
                    if (psiFile == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/execution/testing/CidrTestFrameworkBase", "lambda$checkFrameworkAvailabilityUsingImportedMacro$3"));
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                Label_0069: {
                    try {
                        if (!(ocSymbol instanceof OCMacroSymbol)) {
                            return true;
                        }
                        final PsiFile psiFile2 = psiFile;
                        final OCFile ocFile = (OCFile)psiFile2;
                        final OCSymbol ocSymbol2 = ocSymbol;
                        final boolean b = OCFileSymbols.isSymbolImported(ocFile, ocSymbol2);
                        if (b) {
                            break Label_0069;
                        }
                        return true;
                    }
                    catch (IllegalStateException ex2) {
                        throw a(ex2);
                    }
                    try {
                        final PsiFile psiFile2 = psiFile;
                        final OCFile ocFile = (OCFile)psiFile2;
                        final OCSymbol ocSymbol2 = ocSymbol;
                        final boolean b = OCFileSymbols.isSymbolImported(ocFile, ocSymbol2);
                        if (b) {
                            ref.set((Object)Boolean.TRUE);
                            return false;
                        }
                    }
                    catch (IllegalStateException ex3) {
                        throw a(ex3);
                    }
                }
                return true;
            }), s, true);
            if (!ref.isNull()) {
                return true;
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        return false;
    }
    
    @NotNull
    public static <T extends OCTestFramework> T findExtension(@NotNull final Class<T> clazz) {
        try {
            if (clazz == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "extClass", "com/jetbrains/cidr/execution/testing/CidrTestFrameworkBase", "findExtension"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        OCTestFramework ocTestFramework;
        try {
            ocTestFramework = (OCTestFramework)Extensions.findExtension((ExtensionPointName)OCTestFramework.EP_NAME, (Class)clazz);
            if (ocTestFramework == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/CidrTestFrameworkBase", "findExtension"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return (T)ocTestFramework;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
