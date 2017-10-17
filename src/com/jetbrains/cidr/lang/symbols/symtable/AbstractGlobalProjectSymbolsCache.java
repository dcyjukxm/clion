// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.intellij.openapi.project.DumbService;
import com.intellij.psi.util.CachedValueProvider;
import com.intellij.openapi.util.Comparing;
import java.util.Set;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.workspace.OCWorkspace;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Collection;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.util.OCCommonProcessors;
import com.intellij.openapi.util.Conditions;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.workspace.OCWorkspaceManager;
import com.intellij.openapi.project.IndexNotReadyException;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.util.Condition;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.util.CachedValuesManager;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.util.MultiValuesMap;
import com.intellij.psi.util.CachedValue;
import com.intellij.openapi.components.AbstractProjectComponent;
import com.jetbrains.cidr.lang.symbols.OCSymbol;

public abstract class AbstractGlobalProjectSymbolsCache<T extends OCSymbol, S extends Symbols<T>> extends AbstractProjectComponent
{
    private final CachedValue<S> myGlobalSymbols;
    private final Object myGlobalSymbolsLock;
    private final CachedValue<MultiValuesMap<String, T>> myGlobalShortNames;
    private final Object myGlobalShortNamesLock;
    static final /* synthetic */ boolean $assertionsDisabled;
    
    protected AbstractGlobalProjectSymbolsCache(@NotNull final Class<Impl> clazz, final Project project) {
        if (clazz == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "clazz", "com/jetbrains/cidr/lang/symbols/symtable/AbstractGlobalProjectSymbolsCache", "<init>"));
        }
        super(project);
        final CachedValuesManager manager = CachedValuesManager.getManager(project);
        this.myGlobalSymbolsLock = new Object();
        this.myGlobalSymbols = (com.intellij.psi.util.CachedValue<S>)manager.createCachedValue(() -> {
            Label_0033: {
                Label_0021: {
                    try {
                        if (FileSymbolTablesCache.areSymbolsLoaded(project)) {
                            return CachedValueProvider.Result.create(this.buildGlobalSymbols(project), new Object[] { OCSymbolTablesBuildingActivity.getInstance(project).getModificationTracker(), FileSymbolTablesCache.getInstance(project).getOutOfBlockModificationTracker() });
                        }
                        final Project project2 = project;
                        final boolean b = DumbService.isDumb(project2);
                        if (b) {
                            break Label_0021;
                        }
                        break Label_0033;
                    }
                    catch (IndexNotReadyException ex) {
                        throw b(ex);
                    }
                    try {
                        final Project project2 = project;
                        final boolean b = DumbService.isDumb(project2);
                        if (b) {
                            throw new IndexNotReadyException();
                        }
                    }
                    catch (IndexNotReadyException ex2) {
                        throw b(ex2);
                    }
                }
                try {
                    assert false : "Symbols must be loaded";
                }
                catch (IndexNotReadyException ex3) {
                    throw b(ex3);
                }
            }
            return CachedValueProvider.Result.create(this.buildGlobalSymbols(project), new Object[] { OCSymbolTablesBuildingActivity.getInstance(project).getModificationTracker(), FileSymbolTablesCache.getInstance(project).getOutOfBlockModificationTracker() });
        }, false);
        this.myGlobalShortNamesLock = new Object();
        this.myGlobalShortNames = (com.intellij.psi.util.CachedValue<com.intellij.openapi.util.MultiValuesMap<String, T>>)manager.createCachedValue(() -> {
            try {
                if (clazz == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "clazz", "com/jetbrains/cidr/lang/symbols/symtable/AbstractGlobalProjectSymbolsCache", "lambda$new$2"));
                }
            }
            catch (IndexNotReadyException ex) {
                throw b(ex);
            }
            Label_0064: {
                try {
                    if (AbstractGlobalProjectSymbolsCache.$assertionsDisabled) {
                        break Label_0064;
                    }
                    final Project project2 = project;
                    final boolean b = FileSymbolTablesCache.areSymbolsLoaded(project2);
                    if (!b) {
                        break Label_0064;
                    }
                    break Label_0064;
                }
                catch (IndexNotReadyException ex2) {
                    throw b(ex2);
                }
                try {
                    final Project project2 = project;
                    final boolean b = FileSymbolTablesCache.areSymbolsLoaded(project2);
                    if (!b) {
                        throw new AssertionError((Object)"Symbols must be loaded");
                    }
                }
                catch (IndexNotReadyException ex3) {
                    throw b(ex3);
                }
            }
            final MultiValuesMap multiValuesMap = new MultiValuesMap();
            processTopLevelAndMemberSymbols((Class<AbstractGlobalProjectSymbolsCache>)clazz, project, (com.intellij.util.Processor<? super OCSymbol>)(ocSymbol -> {
                multiValuesMap.put((Object)ocSymbol.getName(), (Object)ocSymbol);
                return true;
            }), null);
            return CachedValueProvider.Result.create((Object)multiValuesMap, new Object[] { OCSymbolTablesBuildingActivity.getInstance(project).getModificationTracker(), FileSymbolTablesCache.getInstance(project).getOutOfBlockModificationTracker() });
        }, false);
    }
    
    @Nullable
    protected static <Impl extends AbstractGlobalProjectSymbolsCache, T extends OCSymbol> T findNearestTopLevelSymbol(@NotNull final Class<Impl> clazz, final Project project, @Nullable final String s, @Nullable final Condition<? super T> condition, @Nullable final VirtualFile virtualFile) {
        try {
            if (clazz == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "clazz", "com/jetbrains/cidr/lang/symbols/symtable/AbstractGlobalProjectSymbolsCache", "findNearestTopLevelSymbol"));
            }
        }
        catch (IndexNotReadyException ex) {
            throw b(ex);
        }
        final OCWorkspace workspace = OCWorkspaceManager.getWorkspace(project);
        final CommonProcessors.FindFirstProcessor findFirstProcessor = new CommonProcessors.FindFirstProcessor();
        final OCCommonProcessors.OrderedProcessor<T> orderedProcessor = new OCCommonProcessors.OrderedProcessor<T>(findFirstProcessor, true, new Condition[] { ocSymbol -> {
                Label_0024: {
                    try {
                        if (virtualFile == null) {
                            return false;
                        }
                        final OCSymbol ocSymbol2 = ocSymbol;
                        final VirtualFile virtualFile2 = ocSymbol2.getContainingFile();
                        final VirtualFile virtualFile3 = virtualFile;
                        final boolean b = Comparing.equal((Object)virtualFile2, (Object)virtualFile3);
                        if (b) {
                            break Label_0024;
                        }
                        return false;
                    }
                    catch (IndexNotReadyException ex) {
                        throw b(ex);
                    }
                    try {
                        final OCSymbol ocSymbol2 = ocSymbol;
                        final VirtualFile virtualFile2 = ocSymbol2.getContainingFile();
                        final VirtualFile virtualFile3 = virtualFile;
                        final boolean b = Comparing.equal((Object)virtualFile2, (Object)virtualFile3);
                        if (b) {
                            return true;
                        }
                    }
                    catch (IndexNotReadyException ex2) {
                        throw b(ex2);
                    }
                }
                return false;
            }, p1 -> {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_1        
                //     1: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getContainingFile:()Lcom/intellij/openapi/vfs/VirtualFile;
                //     6: astore_2       
                //     7: aload_0        
                //     8: ifnull          51
                //    11: aload_2        
                //    12: ifnull          51
                //    15: goto            22
                //    18: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/AbstractGlobalProjectSymbolsCache.b:(Lcom/intellij/openapi/project/IndexNotReadyException;)Lcom/intellij/openapi/project/IndexNotReadyException;
                //    21: athrow         
                //    22: aload_2        
                //    23: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getNameWithoutExtension:()Ljava/lang/String;
                //    26: aload_0        
                //    27: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getNameWithoutExtension:()Ljava/lang/String;
                //    30: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/String;Ljava/lang/String;)Z
                //    33: ifeq            51
                //    36: goto            43
                //    39: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/AbstractGlobalProjectSymbolsCache.b:(Lcom/intellij/openapi/project/IndexNotReadyException;)Lcom/intellij/openapi/project/IndexNotReadyException;
                //    42: athrow         
                //    43: iconst_1       
                //    44: goto            52
                //    47: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/AbstractGlobalProjectSymbolsCache.b:(Lcom/intellij/openapi/project/IndexNotReadyException;)Lcom/intellij/openapi/project/IndexNotReadyException;
                //    50: athrow         
                //    51: iconst_0       
                //    52: ireturn        
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                                                 
                //  -----  -----  -----  -----  -----------------------------------------------------
                //  7      15     18     22     Lcom/intellij/openapi/project/IndexNotReadyException;
                //  11     36     39     43     Lcom/intellij/openapi/project/IndexNotReadyException;
                //  22     47     47     51     Lcom/intellij/openapi/project/IndexNotReadyException;
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
            }, p1 -> {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_1        
                //     1: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getContainingFile:()Lcom/intellij/openapi/vfs/VirtualFile;
                //     6: astore_2       
                //     7: aload_0        
                //     8: ifnull          51
                //    11: aload_2        
                //    12: ifnull          51
                //    15: goto            22
                //    18: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/AbstractGlobalProjectSymbolsCache.b:(Lcom/intellij/openapi/project/IndexNotReadyException;)Lcom/intellij/openapi/project/IndexNotReadyException;
                //    21: athrow         
                //    22: aload_2        
                //    23: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getParent:()Lcom/intellij/openapi/vfs/VirtualFile;
                //    26: aload_0        
                //    27: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getParent:()Lcom/intellij/openapi/vfs/VirtualFile;
                //    30: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/Object;Ljava/lang/Object;)Z
                //    33: ifeq            51
                //    36: goto            43
                //    39: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/AbstractGlobalProjectSymbolsCache.b:(Lcom/intellij/openapi/project/IndexNotReadyException;)Lcom/intellij/openapi/project/IndexNotReadyException;
                //    42: athrow         
                //    43: iconst_1       
                //    44: goto            52
                //    47: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/AbstractGlobalProjectSymbolsCache.b:(Lcom/intellij/openapi/project/IndexNotReadyException;)Lcom/intellij/openapi/project/IndexNotReadyException;
                //    50: athrow         
                //    51: iconst_0       
                //    52: ireturn        
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                                                 
                //  -----  -----  -----  -----  -----------------------------------------------------
                //  7      15     18     22     Lcom/intellij/openapi/project/IndexNotReadyException;
                //  11     36     39     43     Lcom/intellij/openapi/project/IndexNotReadyException;
                //  22     47     47     51     Lcom/intellij/openapi/project/IndexNotReadyException;
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
            }, ocSymbol -> workspace.areFromSameProject(virtualFile, ocSymbol.getContainingFile()), Conditions.alwaysTrue() }) {
            @Override
            public Collection<T> sort(final Collection<T> collection) {
                final ArrayList<Object> list = new ArrayList<Object>(collection);
                Collections.sort(list, (ocSymbol, ocSymbol2) -> ocSymbol.hashCode() - ocSymbol2.hashCode());
                return (Collection<T>)list;
            }
        };
        processTopLevelAndMemberSymbols(clazz, project, (com.intellij.util.Processor<? super OCSymbol>)(ocSymbol -> {
            Label_0028: {
                try {
                    if (condition == null) {
                        break Label_0028;
                    }
                    final Condition condition2 = condition;
                    final OCSymbol ocSymbol2 = ocSymbol;
                    final boolean b = condition2.value((Object)ocSymbol2);
                    if (!b) {
                        return true;
                    }
                    break Label_0028;
                }
                catch (IndexNotReadyException ex) {
                    throw b(ex);
                }
                try {
                    final Condition condition2 = condition;
                    final OCSymbol ocSymbol2 = ocSymbol;
                    final boolean b = condition2.value((Object)ocSymbol2);
                    if (!b) {
                        return true;
                    }
                }
                catch (IndexNotReadyException ex2) {
                    throw b(ex2);
                }
                try {
                    if (workspace.isFromWrongSDK(ocSymbol, virtualFile)) {
                        return true;
                    }
                }
                catch (IndexNotReadyException ex3) {
                    throw b(ex3);
                }
            }
            return ((OCCommonProcessors.OrderedProcessor<OCSymbol>)orderedProcessor).process(ocSymbol);
        }), s, true);
        orderedProcessor.finish();
        return (T)findFirstProcessor.getFoundValue();
    }
    
    @NotNull
    public static <T> T getInstance(@NotNull final Class<T> clazz, @NotNull final Project project) {
        try {
            if (clazz == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "clazz", "com/jetbrains/cidr/lang/symbols/symtable/AbstractGlobalProjectSymbolsCache", "getInstance"));
            }
        }
        catch (IndexNotReadyException ex) {
            throw b(ex);
        }
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/symbols/symtable/AbstractGlobalProjectSymbolsCache", "getInstance"));
            }
        }
        catch (IndexNotReadyException ex2) {
            throw b(ex2);
        }
        Object component;
        try {
            component = project.getComponent((Class)clazz);
            if (component == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/symtable/AbstractGlobalProjectSymbolsCache", "getInstance"));
            }
        }
        catch (IndexNotReadyException ex3) {
            throw b(ex3);
        }
        return (T)component;
    }
    
    @NotNull
    protected S getGlobalSymbols() {
        synchronized (this.myGlobalSymbolsLock) {
            final Symbols symbols = (Symbols)this.myGlobalSymbols.getValue();
            // monitorexit(this.myGlobalSymbolsLock)
            if (symbols == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/symtable/AbstractGlobalProjectSymbolsCache", "getGlobalSymbols"));
            }
            return (S)symbols;
        }
    }
    
    @NotNull
    protected MultiValuesMap<String, T> getGlobalShortNames() {
        synchronized (this.myGlobalShortNamesLock) {
            final MultiValuesMap multiValuesMap = (MultiValuesMap)this.myGlobalShortNames.getValue();
            // monitorexit(this.myGlobalShortNamesLock)
            if (multiValuesMap == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/symtable/AbstractGlobalProjectSymbolsCache", "getGlobalShortNames"));
            }
            return (MultiValuesMap<String, T>)multiValuesMap;
        }
    }
    
    protected abstract S buildGlobalSymbols(final Project p0);
    
    protected static <Impl extends AbstractGlobalProjectSymbolsCache, T extends OCSymbol> boolean processTopLevelSymbols(@NotNull final Class<Impl> clazz, @NotNull final Project project, final Processor<? super T> processor, @Nullable final String s) {
        try {
            if (clazz == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "clazz", "com/jetbrains/cidr/lang/symbols/symtable/AbstractGlobalProjectSymbolsCache", "processTopLevelSymbols"));
            }
        }
        catch (IndexNotReadyException ex) {
            throw b(ex);
        }
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/symbols/symtable/AbstractGlobalProjectSymbolsCache", "processTopLevelSymbols"));
            }
        }
        catch (IndexNotReadyException ex2) {
            throw b(ex2);
        }
        return processTopLevelAndMemberSymbols(clazz, project, (com.intellij.util.Processor<? super OCSymbol>)processor, s, true);
    }
    
    protected static <Impl extends AbstractGlobalProjectSymbolsCache, T extends OCSymbol> boolean processTopLevelAndMemberSymbols(@NotNull final Class<Impl> clazz, final Project project, final Processor<? super T> processor, @Nullable final String s) {
        try {
            if (clazz == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "clazz", "com/jetbrains/cidr/lang/symbols/symtable/AbstractGlobalProjectSymbolsCache", "processTopLevelAndMemberSymbols"));
            }
        }
        catch (IndexNotReadyException ex) {
            throw b(ex);
        }
        return processTopLevelAndMemberSymbols(clazz, project, (com.intellij.util.Processor<? super OCSymbol>)processor, s, false);
    }
    
    protected static <Impl extends AbstractGlobalProjectSymbolsCache, T extends OCSymbol> boolean processByQualifiedName(@NotNull final Class<Impl> clazz, final Project project, final Processor<? super T> processor, @NotNull final String s) {
        try {
            if (clazz == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "clazz", "com/jetbrains/cidr/lang/symbols/symtable/AbstractGlobalProjectSymbolsCache", "processByQualifiedName"));
            }
        }
        catch (IndexNotReadyException ex) {
            throw b(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/symbols/symtable/AbstractGlobalProjectSymbolsCache", "processByQualifiedName"));
            }
        }
        catch (IndexNotReadyException ex2) {
            throw b(ex2);
        }
        final Collection value = getInstance(clazz, project).getGlobalShortNames().get((Object)s);
        Label_0126: {
            try {
                if (value == null) {
                    break Label_0126;
                }
                final Collection collection = value;
                final Processor<? super T> processor2 = processor;
                final boolean b = ContainerUtil.process((Iterable)collection, (Processor)processor2);
                if (b) {
                    break Label_0126;
                }
                return false;
            }
            catch (IndexNotReadyException ex3) {
                throw b(ex3);
            }
            try {
                final Collection collection = value;
                final Processor<? super T> processor2 = processor;
                final boolean b = ContainerUtil.process((Iterable)collection, (Processor)processor2);
                if (b) {
                    return true;
                }
            }
            catch (IndexNotReadyException ex4) {
                throw b(ex4);
            }
        }
        return false;
    }
    
    protected static <Impl extends AbstractGlobalProjectSymbolsCache> boolean processAliasNamesForType(@NotNull final Class<Impl> clazz, @NotNull final Project project, @NotNull final String s, @NotNull final Processor<String> processor) {
        try {
            if (clazz == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "clazz", "com/jetbrains/cidr/lang/symbols/symtable/AbstractGlobalProjectSymbolsCache", "processAliasNamesForType"));
            }
        }
        catch (IndexNotReadyException ex) {
            throw b(ex);
        }
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/symbols/symtable/AbstractGlobalProjectSymbolsCache", "processAliasNamesForType"));
            }
        }
        catch (IndexNotReadyException ex2) {
            throw b(ex2);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/symbols/symtable/AbstractGlobalProjectSymbolsCache", "processAliasNamesForType"));
            }
        }
        catch (IndexNotReadyException ex3) {
            throw b(ex3);
        }
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/symbols/symtable/AbstractGlobalProjectSymbolsCache", "processAliasNamesForType"));
            }
        }
        catch (IndexNotReadyException ex4) {
            throw b(ex4);
        }
        FileSymbolTablesCache.getInstance(project).ensurePendingFilesProcessed();
        return getInstance(clazz, project).getGlobalSymbols().processAliasNamesForType(s, processor);
    }
    
    public static <Impl extends AbstractGlobalProjectSymbolsCache, T extends OCSymbol> boolean processTopLevelAndMemberSymbols(@NotNull final Class<Impl> clazz, @NotNull final Project project, @NotNull final Processor<? super T> processor, @Nullable final String s, final boolean b) {
        try {
            if (clazz == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "clazz", "com/jetbrains/cidr/lang/symbols/symtable/AbstractGlobalProjectSymbolsCache", "processTopLevelAndMemberSymbols"));
            }
        }
        catch (IndexNotReadyException ex) {
            throw b(ex);
        }
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/symbols/symtable/AbstractGlobalProjectSymbolsCache", "processTopLevelAndMemberSymbols"));
            }
        }
        catch (IndexNotReadyException ex2) {
            throw b(ex2);
        }
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/symbols/symtable/AbstractGlobalProjectSymbolsCache", "processTopLevelAndMemberSymbols"));
            }
        }
        catch (IndexNotReadyException ex3) {
            throw b(ex3);
        }
        FileSymbolTablesCache.getInstance(project).ensurePendingFilesProcessed();
        final Symbols<OCSymbol> globalSymbols = getInstance(clazz, project).getGlobalSymbols();
        try {
            if (b) {
                return globalSymbols.processTopLevel((com.intellij.util.Processor<? super OCSymbol>)processor, s);
            }
        }
        catch (IndexNotReadyException ex4) {
            throw b(ex4);
        }
        return globalSymbols.processAllSymbols((com.intellij.util.Processor<? super OCSymbol>)processor, s);
    }
    
    public static <Impl extends AbstractGlobalProjectSymbolsCache> Set<String> getAllSymbolNames(@NotNull final Class<Impl> clazz, final Project project) {
        try {
            if (clazz == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "clazz", "com/jetbrains/cidr/lang/symbols/symtable/AbstractGlobalProjectSymbolsCache", "getAllSymbolNames"));
            }
        }
        catch (IndexNotReadyException ex) {
            throw b(ex);
        }
        return (Set<String>)getInstance(clazz, project).getGlobalShortNames().keySet();
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!AbstractGlobalProjectSymbolsCache.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IndexNotReadyException ex) {
                throw b(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IndexNotReadyException b(final IndexNotReadyException ex) {
        return ex;
    }
    
    public interface Symbols<T> extends Processor<T>
    {
        boolean processAliasNamesForType(@NotNull final String p0, @NotNull final Processor<String> p1);
        
        boolean processTopLevel(final Processor<? super T> p0, final String p1);
        
        boolean processAllSymbols(final Processor<? super T> p0, final String p1);
        
        boolean isEmpty();
    }
}
