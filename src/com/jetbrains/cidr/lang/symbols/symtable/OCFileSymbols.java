// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.intellij.psi.PsiFile;
import java.util.Collections;
import com.intellij.psi.PsiElementVisitor;
import com.jetbrains.cidr.lang.psi.impl.symbols.OCFileGlobalSymbolsCache;
import com.jetbrains.cidr.lang.psi.impl.symbols.OCFileGlobalSymbols;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.psi.OCIncludeDirective;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.jetbrains.cidr.lang.inspections.OCGlobalUnusedInspection;
import java.util.Iterator;
import java.util.Collection;
import org.jetbrains.annotations.Nullable;
import java.util.Comparator;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.symbols.OCSymbolOffsetUtil;
import com.intellij.openapi.util.UserDataHolder;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.psi.OCMacroCallArgument;
import com.intellij.psi.PsiElement;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.psi.OCEnum;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.psi.OCStructLike;
import com.jetbrains.cidr.lang.psi.OCLocalSymbolDeclarator;
import com.jetbrains.cidr.lang.psi.OCTemplateParameterList;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.jetbrains.cidr.lang.psi.OCElement;
import java.util.HashSet;
import java.util.ArrayList;
import com.intellij.lang.annotation.AnnotationSession;
import com.intellij.openapi.util.NotNullLazyKey;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.Set;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.containers.MostlySingularMultiMap;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;

public class OCFileSymbols extends OCRecursiveVisitor
{
    private MostlySingularMultiMap<String, OCSymbol> myLocalSymbolsMap;
    private List<OCSymbol> myLocalNamespaceUsings;
    private boolean myIsInsideLocalScope;
    private Set<VirtualFile> myUsedFiles;
    private static final NotNullLazyKey<OCFileSymbols, AnnotationSession> CACHE;
    
    private OCFileSymbols() {
        this.myLocalSymbolsMap = (MostlySingularMultiMap<String, OCSymbol>)new MostlySingularMultiMap();
        this.myLocalNamespaceUsings = new ArrayList<OCSymbol>();
        this.myIsInsideLocalScope = false;
        this.myUsedFiles = new HashSet<VirtualFile>();
    }
    
    @Override
    public void visitOCElement(final OCElement ocElement) {
        Label_0044: {
            try {
                if (!(ocElement instanceof OCCallable)) {
                    if (!(ocElement instanceof OCTemplateParameterList)) {
                        break Label_0044;
                    }
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            final boolean myIsInsideLocalScope = this.myIsInsideLocalScope;
            this.myIsInsideLocalScope = true;
            super.visitOCElement(ocElement);
            this.myIsInsideLocalScope = myIsInsideLocalScope;
            return;
        }
        super.visitOCElement(ocElement);
    }
    
    @Override
    public void visitLocalSymbolDeclarator(final OCLocalSymbolDeclarator p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/symbols/symtable/OCFileSymbols.myIsInsideLocalScope:Z
        //     4: ifne            19
        //     7: aload_0        
        //     8: aload_1        
        //     9: invokespecial   com/jetbrains/cidr/lang/psi/visitors/OCRecursiveVisitor.visitLocalSymbolDeclarator:(Lcom/jetbrains/cidr/lang/psi/OCLocalSymbolDeclarator;)V
        //    12: goto            97
        //    15: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/OCFileSymbols.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    18: athrow         
        //    19: aload_1        
        //    20: invokeinterface com/jetbrains/cidr/lang/psi/OCLocalSymbolDeclarator.getLocalSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    25: astore_2       
        //    26: aload_2        
        //    27: ifnull          97
        //    30: aload_2        
        //    31: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getScope:()Lcom/intellij/openapi/util/TextRange;
        //    36: ifnull          97
        //    39: goto            46
        //    42: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/OCFileSymbols.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    45: athrow         
        //    46: aload_2        
        //    47: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    52: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.NAMESPACE_USING_SYMBOL:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    55: if_acmpne       83
        //    58: goto            65
        //    61: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/OCFileSymbols.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    64: athrow         
        //    65: aload_0        
        //    66: getfield        com/jetbrains/cidr/lang/symbols/symtable/OCFileSymbols.myLocalNamespaceUsings:Ljava/util/List;
        //    69: aload_2        
        //    70: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //    75: pop            
        //    76: goto            97
        //    79: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/OCFileSymbols.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    82: athrow         
        //    83: aload_0        
        //    84: getfield        com/jetbrains/cidr/lang/symbols/symtable/OCFileSymbols.myLocalSymbolsMap:Lcom/intellij/util/containers/MostlySingularMultiMap;
        //    87: aload_2        
        //    88: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //    93: aload_2        
        //    94: invokevirtual   com/intellij/util/containers/MostlySingularMultiMap.add:(Ljava/lang/Object;Ljava/lang/Object;)V
        //    97: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      15     15     19     Ljava/lang/IllegalArgumentException;
        //  26     39     42     46     Ljava/lang/IllegalArgumentException;
        //  30     58     61     65     Ljava/lang/IllegalArgumentException;
        //  46     79     79     83     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0046:
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
    public void visitStructLike(final OCStructLike ocStructLike) {
        try {
            if (!this.myIsInsideLocalScope) {
                super.visitStructLike(ocStructLike);
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitLocalSymbolDeclarator(ocStructLike);
        final OCStructSymbol ocStructSymbol = ocStructLike.getLocalSymbol();
        try {
            if (ocStructLike instanceof OCEnum) {
                ((OCEnum)ocStructLike).processEnumConsts((Processor<OCSymbol>)(p1 -> {
                    // 
                    // This method could not be decompiled.
                    // 
                    // Original Bytecode:
                    // 
                    //     0: aload_2        
                    //     1: ifnull          90
                    //     4: aload_2        
                    //     5: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getScope:()Lcom/intellij/openapi/util/TextRange;
                    //    10: ifnull          90
                    //    13: goto            20
                    //    16: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/OCFileSymbols.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                    //    19: athrow         
                    //    20: aload_1        
                    //    21: ifnull          66
                    //    24: goto            31
                    //    27: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/OCFileSymbols.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                    //    30: athrow         
                    //    31: aload_1        
                    //    32: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
                    //    35: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.ENUM:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
                    //    38: if_acmpne       66
                    //    41: goto            48
                    //    44: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/OCFileSymbols.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                    //    47: athrow         
                    //    48: aload_2        
                    //    49: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
                    //    52: aload_1        
                    //    53: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCStructType;
                    //    56: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.setType:(Lcom/jetbrains/cidr/lang/types/OCType;)V
                    //    59: goto            76
                    //    62: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/OCFileSymbols.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                    //    65: athrow         
                    //    66: aload_2        
                    //    67: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
                    //    70: getstatic       com/jetbrains/cidr/lang/types/OCIntType.INT:Lcom/jetbrains/cidr/lang/types/OCIntType;
                    //    73: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.setType:(Lcom/jetbrains/cidr/lang/types/OCType;)V
                    //    76: aload_0        
                    //    77: getfield        com/jetbrains/cidr/lang/symbols/symtable/OCFileSymbols.myLocalSymbolsMap:Lcom/intellij/util/containers/MostlySingularMultiMap;
                    //    80: aload_2        
                    //    81: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
                    //    86: aload_2        
                    //    87: invokevirtual   com/intellij/util/containers/MostlySingularMultiMap.add:(Ljava/lang/Object;Ljava/lang/Object;)V
                    //    90: iconst_1       
                    //    91: ireturn        
                    //    Exceptions:
                    //  Try           Handler
                    //  Start  End    Start  End    Type                                
                    //  -----  -----  -----  -----  ------------------------------------
                    //  0      13     16     20     Ljava/lang/IllegalArgumentException;
                    //  4      24     27     31     Ljava/lang/IllegalArgumentException;
                    //  20     41     44     48     Ljava/lang/IllegalArgumentException;
                    //  31     62     62     66     Ljava/lang/IllegalArgumentException;
                    // 
                    // The error that occurred was:
                    // 
                    // java.lang.IllegalStateException: Expression is linked from several locations: Label_0020:
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
                }));
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        this.visitElement((PsiElement)ocStructLike);
    }
    
    @Override
    public void visitMacroCallArgument(final OCMacroCallArgument ocMacroCallArgument) {
    }
    
    public static boolean canBeLocalSymbol(@NotNull final OCFile ocFile, final String s) {
        try {
            if (ocFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/symbols/symtable/OCFileSymbols", "canBeLocalSymbol"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        synchronized (ocFile.getAnnotationSessionLock()) {
            final AnnotationSession currentAnnotationSession = ocFile.getCurrentAnnotationSession();
            if (currentAnnotationSession != null) {
                final OCFileSymbols ocFileSymbols = (OCFileSymbols)OCFileSymbols.CACHE.getValue((UserDataHolder)currentAnnotationSession);
                Label_0108: {
                    try {
                        if (ocFileSymbols.myLocalSymbolsMap.containsKey((Object)s)) {
                            break Label_0108;
                        }
                        final OCFileSymbols ocFileSymbols2 = ocFileSymbols;
                        final List<OCSymbol> list = ocFileSymbols2.myLocalNamespaceUsings;
                        final boolean b = list.isEmpty();
                        if (!b) {
                            break Label_0108;
                        }
                        return false;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    try {
                        final OCFileSymbols ocFileSymbols2 = ocFileSymbols;
                        final List<OCSymbol> list = ocFileSymbols2.myLocalNamespaceUsings;
                        final boolean b = list.isEmpty();
                        if (!b) {
                            return true;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                }
                return false;
            }
        }
        return true;
    }
    
    @Nullable
    public static Iterable<OCSymbol> getLocalSymbols(@NotNull final OCFile ocFile, final String s) {
        try {
            if (ocFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/symbols/symtable/OCFileSymbols", "getLocalSymbols"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        synchronized (ocFile.getAnnotationSessionLock()) {
            final AnnotationSession currentAnnotationSession = ocFile.getCurrentAnnotationSession();
            if (currentAnnotationSession != null) {
                final OCFileSymbols ocFileSymbols = (OCFileSymbols)OCFileSymbols.CACHE.getValue((UserDataHolder)currentAnnotationSession);
                if (ocFileSymbols.myLocalNamespaceUsings.size() == 0) {
                    return (Iterable<OCSymbol>)ocFileSymbols.myLocalSymbolsMap.get((Object)s);
                }
                final Iterable value = ocFileSymbols.myLocalSymbolsMap.get((Object)s);
                if (value instanceof Set) {
                    final ArrayList<Object> list = new ArrayList<Object>(1);
                    list.add(value.iterator().next());
                    return (Iterable<OCSymbol>)ContainerUtil.mergeSortedLists((List)ocFileSymbols.myLocalNamespaceUsings, (List)list, (Comparator)OCSymbolOffsetUtil.SYMBOL_COMPARATOR_BY_SCOPE, false);
                }
                if (value instanceof List) {
                    return (Iterable<OCSymbol>)ContainerUtil.mergeSortedLists((List)ocFileSymbols.myLocalNamespaceUsings, (List)(List<Object>)value, (Comparator)OCSymbolOffsetUtil.SYMBOL_COMPARATOR_BY_SCOPE, false);
                }
                return ocFileSymbols.myLocalNamespaceUsings;
            }
        }
        return null;
    }
    
    public static boolean isImportUsed(final OCFile ocFile, final VirtualFile virtualFile) {
        try {
            if (ocFile.getContext() != null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        synchronized (ocFile.getAnnotationSessionLock()) {
            final AnnotationSession currentAnnotationSession = ocFile.getCurrentAnnotationSession();
            if (currentAnnotationSession != null) {
                return ((OCFileSymbols)OCFileSymbols.CACHE.getValue((UserDataHolder)currentAnnotationSession)).myUsedFiles.contains(virtualFile);
            }
        }
        return true;
    }
    
    public static void markImportNeeded(final OCFile ocFile, final VirtualFile virtualFile) {
        synchronized (ocFile.getAnnotationSessionLock()) {
            final AnnotationSession currentAnnotationSession = ocFile.getCurrentAnnotationSession();
            Label_0031: {
                try {
                    if (currentAnnotationSession == null) {
                        return;
                    }
                    final VirtualFile virtualFile2 = virtualFile;
                    if (virtualFile2 != null) {
                        break Label_0031;
                    }
                    return;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final VirtualFile virtualFile2 = virtualFile;
                    if (virtualFile2 != null) {
                        ((OCFileSymbols)OCFileSymbols.CACHE.getValue((UserDataHolder)currentAnnotationSession)).myUsedFiles.add(virtualFile);
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
        }
    }
    
    public static void markImportsNeeded(final OCFile ocFile, final Collection<VirtualFile> collection) {
        try {
            if (collection.isEmpty()) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        synchronized (ocFile.getAnnotationSessionLock()) {
            final AnnotationSession currentAnnotationSession = ocFile.getCurrentAnnotationSession();
            if (currentAnnotationSession != null) {
                final OCFileSymbols ocFileSymbols = (OCFileSymbols)OCFileSymbols.CACHE.getValue((UserDataHolder)currentAnnotationSession);
                for (final VirtualFile virtualFile : collection) {
                    try {
                        if (virtualFile == null) {
                            continue;
                        }
                        ocFileSymbols.myUsedFiles.add(virtualFile);
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
            }
        }
    }
    
    public static void markImportNeeded(final OCFile ocFile, final OCSymbol ocSymbol) {
        markImportNeeded(ocFile, getFileToImport(ocFile, ocSymbol));
    }
    
    public static void markSymbolAsUsed(final OCFile ocFile, OCSymbol ocSymbolWithParent, final PsiElement psiElement) {
        Label_0025: {
            try {
                if (ocSymbolWithParent == null) {
                    return;
                }
                final OCFile ocFile2 = ocFile;
                final PsiElement psiElement2 = ocFile2.getContext();
                if (psiElement2 != null) {
                    return;
                }
                break Label_0025;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCFile ocFile2 = ocFile;
                final PsiElement psiElement2 = ocFile2.getContext();
                if (psiElement2 != null) {
                    return;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        OCGlobalUnusedInspection.markSymbolAsUsed(ocSymbolWithParent, psiElement);
        if (ocSymbolWithParent instanceof OCSymbolWithParent) {
            final OCSymbolWithParent parent = ((OCSymbolWithParent<T, OCSymbolWithParent>)ocSymbolWithParent).getParent();
            if (parent != null) {
                ocSymbolWithParent = parent;
            }
        }
        markImportNeeded(ocFile, ocSymbolWithParent);
    }
    
    public static boolean isSymbolImported(@NotNull final OCFile ocFile, @Nullable final OCSymbol ocSymbol) {
        try {
            if (ocFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "currentFile", "com/jetbrains/cidr/lang/symbols/symtable/OCFileSymbols", "isSymbolImported"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return isSymbolImported(ocFile, ocSymbol, null);
    }
    
    public static boolean isSymbolImported(@NotNull final OCFile ocFile, @Nullable final OCSymbol ocSymbol, @Nullable final PsiElement psiElement) {
        try {
            if (ocFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "currentFile", "com/jetbrains/cidr/lang/symbols/symtable/OCFileSymbols", "isSymbolImported"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0070: {
            try {
                if (ocSymbol == null) {
                    return false;
                }
                final OCFile ocFile2 = ocFile;
                final PsiElement psiElement2 = ocFile2.getContext();
                if (psiElement2 != null) {
                    return false;
                }
                break Label_0070;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final OCFile ocFile2 = ocFile;
                final PsiElement psiElement2 = ocFile2.getContext();
                if (psiElement2 != null) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                if (Comparing.equal((Object)ocSymbol.getContainingFile(), (Object)ocFile.getVirtualFile())) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        final VirtualFile fileToImport = getFileToImport(ocFile, ocSymbol);
        Label_0166: {
            try {
                if (fileToImport == null || psiElement == null) {
                    break Label_0166;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
            final OCIncludeDirective includeDirective = ocFile.findIncludeDirective(fileToImport);
            Label_0156: {
                try {
                    if (includeDirective == null) {
                        break Label_0156;
                    }
                    final OCIncludeDirective ocIncludeDirective = includeDirective;
                    final TextRange textRange = ocIncludeDirective.getRangeWithMacros();
                    final int n = textRange.getStartOffset();
                    final PsiElement psiElement3 = psiElement;
                    final TextRange textRange2 = OCElementUtil.getRangeWithMacros(psiElement3);
                    final int n2 = textRange2.getStartOffset();
                    if (n <= n2) {
                        break Label_0156;
                    }
                    return false;
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
                try {
                    final OCIncludeDirective ocIncludeDirective = includeDirective;
                    final TextRange textRange = ocIncludeDirective.getRangeWithMacros();
                    final int n = textRange.getStartOffset();
                    final PsiElement psiElement3 = psiElement;
                    final TextRange textRange2 = OCElementUtil.getRangeWithMacros(psiElement3);
                    final int n2 = textRange2.getStartOffset();
                    if (n <= n2) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex7) {
                    throw a(ex7);
                }
            }
            return false;
            try {
                if (fileToImport != null) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex8) {
                throw a(ex8);
            }
        }
        return false;
    }
    
    @NotNull
    private static OCFileGlobalSymbols a(@NotNull final OCFile ocFile) {
        try {
            if (ocFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "currentFile", "com/jetbrains/cidr/lang/symbols/symtable/OCFileSymbols", "getSymbols"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCFileGlobalSymbols forFile;
        try {
            forFile = OCFileGlobalSymbolsCache.getInstance(ocFile.getProject()).forFile(ocFile);
            if (forFile == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/symtable/OCFileSymbols", "getSymbols"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return forFile;
    }
    
    public static boolean isImportRequired(@NotNull final OCFile ocFile, @Nullable final VirtualFile virtualFile) {
        try {
            if (ocFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "currentFile", "com/jetbrains/cidr/lang/symbols/symtable/OCFileSymbols", "isImportRequired"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (virtualFile == null) {
                return false;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        Label_0086: {
            try {
                if (ocFile.getContext() != null) {
                    break Label_0086;
                }
                final OCFile ocFile2 = ocFile;
                final OCFileGlobalSymbols ocFileGlobalSymbols = a(ocFile2);
                final Set<VirtualFile> set = ocFileGlobalSymbols.getRequiredImports();
                final VirtualFile virtualFile2 = virtualFile;
                final boolean b = set.contains(virtualFile2);
                if (b) {
                    break Label_0086;
                }
                return false;
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                final OCFile ocFile2 = ocFile;
                final OCFileGlobalSymbols ocFileGlobalSymbols = a(ocFile2);
                final Set<VirtualFile> set = ocFileGlobalSymbols.getRequiredImports();
                final VirtualFile virtualFile2 = virtualFile;
                final boolean b = set.contains(virtualFile2);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return false;
    }
    
    @Nullable
    public static VirtualFile getFileToImport(@NotNull final OCFile ocFile, @Nullable final OCSymbol ocSymbol) {
        try {
            if (ocFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "currentFile", "com/jetbrains/cidr/lang/symbols/symtable/OCFileSymbols", "getFileToImport"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocSymbol == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return a(ocFile).getSymbolToImport().get(ocSymbol);
    }
    
    static {
        CACHE = NotNullLazyKey.create("LOCAL_SYMBOL_TABLE_DURING_ANNOTATION", annotationSession -> {
            final PsiFile file = annotationSession.getFile();
            final OCFileSymbols ocFileSymbols = new OCFileSymbols();
            file.accept((PsiElementVisitor)ocFileSymbols);
            final Iterator iterator = ocFileSymbols.myLocalSymbolsMap.keySet().iterator();
            while (iterator.hasNext()) {
                final Iterable value = ocFileSymbols.myLocalSymbolsMap.get((Object)iterator.next());
                Label_0093: {
                    try {
                        if (!(value instanceof List)) {
                            continue;
                        }
                        final Iterable iterable = value;
                        final List list = (List)iterable;
                        final int n = list.size();
                        final int n2 = 1;
                        if (n > n2) {
                            break Label_0093;
                        }
                        continue;
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        final Iterable iterable = value;
                        final List list = (List)iterable;
                        final int n = list.size();
                        final int n2 = 1;
                        if (n <= n2) {
                            continue;
                        }
                        Collections.sort((List<Object>)value, (Comparator<? super Object>)OCSymbolOffsetUtil.SYMBOL_COMPARATOR_BY_SCOPE);
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
            }
            Collections.sort(ocFileSymbols.myLocalNamespaceUsings, OCSymbolOffsetUtil.SYMBOL_COMPARATOR_BY_SCOPE);
            return ocFileSymbols;
        });
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
