// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.cpp;

import com.jetbrains.cidr.lang.resolve.OCResolveUtil;
import com.jetbrains.cidr.lang.symbols.OCDelegatingSymbol;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import java.util.Iterator;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import java.util.Collections;
import com.intellij.psi.PsiFile;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.project.Project;
import java.util.Collection;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.containers.MostlySingularMultiMap;
import com.intellij.psi.PsiElement;

public class OCNamespaceSymbol extends OCSymbolWithQualifiedName<PsiElement> implements OCNamespaceLikeSymbol
{
    @Nullable
    private MostlySingularMultiMap<String, OCSymbol> myMembers;
    @Nullable
    private List<OCSymbol> myMembersList;
    private List<OCUsingSymbol> myNamespaceUsings;
    private Collection<OCNamespaceSymbol> myInlineNamespaces;
    private boolean myInlineNamespace;
    
    public OCNamespaceSymbol() {
    }
    
    public OCNamespaceSymbol(@Nullable final Project project, @Nullable final VirtualFile virtualFile, final long n, @Nullable final OCSymbolWithQualifiedName ocSymbolWithQualifiedName, @NotNull final OCQualifiedName ocQualifiedName, @NotNull final List<String> list, @Nullable final List<OCSymbol> list2, @Nullable final MostlySingularMultiMap<String, OCSymbol> myMembers, @Nullable final List<OCUsingSymbol> myNamespaceUsings, final boolean myInlineNamespace) {
        if (ocQualifiedName == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol", "<init>"));
        }
        if (list == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "attributes", "com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol", "<init>"));
        }
        super(project, virtualFile, n, ocSymbolWithQualifiedName, ocQualifiedName, list, null);
        this.myMembersList = (List<OCSymbol>)ContainerUtil.trimToSize((List)list2);
        this.myMembers = myMembers;
        this.myNamespaceUsings = myNamespaceUsings;
        this.myInlineNamespace = myInlineNamespace;
    }
    
    protected OCNamespaceSymbol(@Nullable final Project project, @Nullable final VirtualFile virtualFile, final long n, @Nullable final OCSymbolWithQualifiedName ocSymbolWithQualifiedName, @NotNull final OCQualifiedName ocQualifiedName, @NotNull final List<String> list, @Nullable final List<OCSymbol> myMembersList, @Nullable final MostlySingularMultiMap<String, OCSymbol> myMembers, @Nullable final List<OCUsingSymbol> myNamespaceUsings, final boolean myInlineNamespace, final OCVisibility ocVisibility) {
        if (ocQualifiedName == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol", "<init>"));
        }
        if (list == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "attributes", "com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol", "<init>"));
        }
        super(project, virtualFile, n, ocSymbolWithQualifiedName, ocQualifiedName, list, ocVisibility);
        this.myMembersList = myMembersList;
        this.myMembers = myMembers;
        this.myNamespaceUsings = myNamespaceUsings;
        this.myInlineNamespace = myInlineNamespace;
    }
    
    public static OCNamespaceSymbol createGlobalNamespaceSymbol(final PsiFile psiFile) {
        return new OCNamespaceSymbol(psiFile.getProject(), psiFile.getVirtualFile(), 0L, null, OCQualifiedName.GLOBAL, Collections.emptyList(), null, null, null, false);
    }
    
    public boolean isGlobalNamespace() {
        Label_0023: {
            try {
                if (this.myComplexOffset != 0L) {
                    return false;
                }
                final OCNamespaceSymbol ocNamespaceSymbol = this;
                final MostlySingularMultiMap<String, OCSymbol> mostlySingularMultiMap = ocNamespaceSymbol.myMembers;
                if (mostlySingularMultiMap == null) {
                    break Label_0023;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            try {
                final OCNamespaceSymbol ocNamespaceSymbol = this;
                final MostlySingularMultiMap<String, OCSymbol> mostlySingularMultiMap = ocNamespaceSymbol.myMembers;
                if (mostlySingularMultiMap == null) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
        }
        return false;
    }
    
    @NotNull
    @Override
    public OCSymbolKind getKind() {
        OCSymbolKind namespace;
        try {
            namespace = OCSymbolKind.NAMESPACE;
            if (namespace == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol", "getKind"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return namespace;
    }
    
    @Override
    public boolean isDefinition() {
        return false;
    }
    
    public boolean isPredefinition() {
        try {
            if (this.myMembers == null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return false;
    }
    
    @Nullable
    protected MostlySingularMultiMap<String, OCSymbol> getMembers() {
        return this.myMembers;
    }
    
    @Nullable
    public OCSymbol findMember(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol", "findMember"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        if (this.myMembers != null) {
            final Iterator<OCSymbol> iterator = (Iterator<OCSymbol>)this.myMembers.get((Object)s).iterator();
            try {
                if (iterator.hasNext()) {
                    return iterator.next().getDelegate();
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
            return null;
        }
        return null;
    }
    
    public void addMember(@NotNull final OCSymbol ocSymbol) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "member", "com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol", "addMember"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (this.myMembers != null) {
                this.myMembers.add((Object)ocSymbol.getName(), (Object)ocSymbol);
                this.myMembersList.add(ocSymbol);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
    }
    
    @Override
    public boolean isGlobal() {
        return true;
    }
    
    @Override
    public boolean isTopLevel() {
        return true;
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw c(ex3);
        }
        try {
            if (!super.deepEqualStep(comparator, o, o2)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw c(ex4);
        }
        final OCNamespaceSymbol ocNamespaceSymbol = (OCNamespaceSymbol)o;
        final OCNamespaceSymbol ocNamespaceSymbol2 = (OCNamespaceSymbol)o2;
        try {
            if (!comparator.equalIterable(ocNamespaceSymbol.myNamespaceUsings, ocNamespaceSymbol2.myNamespaceUsings)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw c(ex5);
        }
        try {
            if (!comparator.equalMultiMaps(ocNamespaceSymbol.myMembers, ocNamespaceSymbol2.myMembers)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw c(ex6);
        }
        return true;
    }
    
    @Override
    public void updateOffset(final int n, final int n2, final int n3) {
        try {
            super.updateOffset(n, n2, n3);
            if (this.myMembers != null) {
                this.myMembers.processAllValues(ocSymbol -> {
                    ocSymbol.updateOffset(n, n2, n3);
                    return true;
                });
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
    }
    
    @Override
    public void compact() {
        try {
            super.compact();
            if (this.myMembers != null) {
                this.myMembers.processAllValues(ocSymbol -> {
                    ocSymbol.compact();
                    return true;
                });
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
    }
    
    @Nullable
    public List<OCSymbol> getMembersList() {
        return this.myMembersList;
    }
    
    @Override
    public boolean processMembers(@Nullable final String s, @NotNull final Processor<OCSymbol> processor) {
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol", "processMembers"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (this.myMembers == null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        try {
            if (s != null) {
                return this.myMembers.processForKey((Object)s, (Processor)OCDelegatingSymbol.getDelegateProcessor(processor));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw c(ex3);
        }
        return this.myMembers.processAllValues((Processor)OCDelegatingSymbol.getDelegateProcessor(processor));
    }
    
    public boolean processMembers(@NotNull final Processor<OCSymbol> processor, final int n, final int n2) {
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol", "processMembers"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        Label_0071: {
            try {
                if (this.myMembersList == null) {
                    break Label_0071;
                }
                final Processor<OCSymbol> processor2 = processor;
                final OCNamespaceSymbol ocNamespaceSymbol = this;
                final List<OCSymbol> list = ocNamespaceSymbol.myMembersList;
                final int n3 = n;
                final int n4 = n2;
                final boolean b = OCResolveUtil.processSymbolsFromList(processor2, list, n3, n4);
                if (b) {
                    break Label_0071;
                }
                return false;
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
            try {
                final Processor<OCSymbol> processor2 = processor;
                final OCNamespaceSymbol ocNamespaceSymbol = this;
                final List<OCSymbol> list = ocNamespaceSymbol.myMembersList;
                final int n3 = n;
                final int n4 = n2;
                final boolean b = OCResolveUtil.processSymbolsFromList(processor2, list, n3, n4);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw c(ex3);
            }
        }
        return false;
    }
    
    public boolean processMembersAndUsings(@NotNull final Processor<OCSymbol> processor, final int n, final int n2) {
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol", "processMembersAndUsings"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return OCResolveUtil.processSymbolsFromTwoLists(processor, ContainerUtil.notNullize((List)this.getMembersList()), ContainerUtil.notNullize((List)this.getNamespaceUsings()), n, n2);
    }
    
    @Nullable
    @Override
    public List<OCUsingSymbol> getNamespaceUsings() {
        return this.myNamespaceUsings;
    }
    
    @Nullable
    @Override
    public Collection<OCNamespaceSymbol> getInlineNamespaces() {
        return this.myInlineNamespaces;
    }
    
    public Processor<OCSymbol> getBuilder() {
        return (Processor<OCSymbol>)(p0 -> {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_1        
            //     1: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
            //     6: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.NAMESPACE_USING_SYMBOL:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
            //     9: if_acmpne       61
            //    12: aload_0        
            //    13: getfield        com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol.myNamespaceUsings:Ljava/util/List;
            //    16: ifnonnull       44
            //    19: goto            26
            //    22: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    25: athrow         
            //    26: aload_0        
            //    27: new             Ljava/util/ArrayList;
            //    30: dup            
            //    31: invokespecial   java/util/ArrayList.<init>:()V
            //    34: putfield        com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol.myNamespaceUsings:Ljava/util/List;
            //    37: goto            44
            //    40: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    43: athrow         
            //    44: aload_0        
            //    45: getfield        com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol.myNamespaceUsings:Ljava/util/List;
            //    48: aload_1        
            //    49: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCUsingSymbol;
            //    52: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
            //    57: pop            
            //    58: goto            209
            //    61: aload_0        
            //    62: getfield        com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol.myMembers:Lcom/intellij/util/containers/MostlySingularMultiMap;
            //    65: ifnonnull       97
            //    68: aload_0        
            //    69: new             Lcom/intellij/util/containers/MostlySingularMultiMap;
            //    72: dup            
            //    73: invokespecial   com/intellij/util/containers/MostlySingularMultiMap.<init>:()V
            //    76: putfield        com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol.myMembers:Lcom/intellij/util/containers/MostlySingularMultiMap;
            //    79: aload_0        
            //    80: new             Ljava/util/ArrayList;
            //    83: dup            
            //    84: invokespecial   java/util/ArrayList.<init>:()V
            //    87: putfield        com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol.myMembersList:Ljava/util/List;
            //    90: goto            97
            //    93: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    96: athrow         
            //    97: aload_1        
            //    98: aload_0        
            //    99: if_acmpeq       209
            //   102: aload_1        
            //   103: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol;
            //   106: ifeq            179
            //   109: goto            116
            //   112: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   115: athrow         
            //   116: aload_1        
            //   117: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol;
            //   120: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol.isInlineNamespace:()Z
            //   123: ifeq            179
            //   126: goto            133
            //   129: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   132: athrow         
            //   133: aload_0        
            //   134: getfield        com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol.myInlineNamespaces:Ljava/util/Collection;
            //   137: ifnonnull       165
            //   140: goto            147
            //   143: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   146: athrow         
            //   147: aload_0        
            //   148: new             Ljava/util/ArrayList;
            //   151: dup            
            //   152: invokespecial   java/util/ArrayList.<init>:()V
            //   155: putfield        com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol.myInlineNamespaces:Ljava/util/Collection;
            //   158: goto            165
            //   161: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   164: athrow         
            //   165: aload_0        
            //   166: getfield        com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol.myInlineNamespaces:Ljava/util/Collection;
            //   169: aload_1        
            //   170: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol;
            //   173: invokeinterface java/util/Collection.add:(Ljava/lang/Object;)Z
            //   178: pop            
            //   179: aload_0        
            //   180: getfield        com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol.myMembers:Lcom/intellij/util/containers/MostlySingularMultiMap;
            //   183: aload_1        
            //   184: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
            //   189: aload_1        
            //   190: invokevirtual   com/intellij/util/containers/MostlySingularMultiMap.add:(Ljava/lang/Object;Ljava/lang/Object;)V
            //   193: aload_0        
            //   194: getfield        com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol.myMembersList:Ljava/util/List;
            //   197: aload_1        
            //   198: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getDelegate:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
            //   203: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
            //   208: pop            
            //   209: iconst_1       
            //   210: ireturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  0      19     22     26     Ljava/lang/IllegalArgumentException;
            //  12     37     40     44     Ljava/lang/IllegalArgumentException;
            //  61     90     93     97     Ljava/lang/IllegalArgumentException;
            //  97     109    112    116    Ljava/lang/IllegalArgumentException;
            //  102    126    129    133    Ljava/lang/IllegalArgumentException;
            //  116    140    143    147    Ljava/lang/IllegalArgumentException;
            //  133    158    161    165    Ljava/lang/IllegalArgumentException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0116:
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
        });
    }
    
    public boolean isInlineNamespace() {
        return this.myInlineNamespace;
    }
    
    public int getLastElementOffset() {
        OCSymbol ocSymbol = null;
        Label_0025: {
            try {
                if (this.myMembersList != null) {
                    ocSymbol = (OCSymbol)ContainerUtil.getLastItem((List)this.myMembersList);
                    break Label_0025;
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            ocSymbol = null;
        }
        final OCSymbol ocSymbol2 = ocSymbol;
        try {
            if (ocSymbol2 instanceof OCNamespaceSymbol) {
                return ((OCNamespaceSymbol)ocSymbol2).getLastElementOffset();
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        try {
            if (ocSymbol2 != null) {
                return ocSymbol2.getOffset();
            }
        }
        catch (IllegalArgumentException ex3) {
            throw c(ex3);
        }
        return this.getOffset();
    }
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}
