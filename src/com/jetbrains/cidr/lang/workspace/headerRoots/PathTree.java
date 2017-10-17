// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.workspace.headerRoots;

import java.util.List;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.util.io.FileUtil;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import gnu.trove.THashMap;
import org.jetbrains.annotations.Nullable;
import gnu.trove.THashSet;

public abstract class PathTree<Self extends PathTree<Self, T>, T>
{
    @Nullable
    protected THashSet<T> myItems;
    @Nullable
    protected THashMap<String, Self> myChildren;
    
    public void addItem(@NotNull final String s, @NotNull final T t) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "path", "com/jetbrains/cidr/lang/workspace/headerRoots/PathTree", "addItem"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (t == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "item", "com/jetbrains/cidr/lang/workspace/headerRoots/PathTree", "addItem"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        this.getNotNullSubTree(s).a().add(t);
    }
    
    public int removeItem(@NotNull final String s, @NotNull final T t) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "path", "com/jetbrains/cidr/lang/workspace/headerRoots/PathTree", "removeItem"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (t == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ref", "com/jetbrains/cidr/lang/workspace/headerRoots/PathTree", "removeItem"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final PathTree<Self, T> subTree = (PathTree<Self, T>)this.getSubTree(s, SearchStrategy.NULL_IF_NOT_FOUND);
        if (subTree != null) {
            final gnu.trove.THashSet<T> myItems = subTree.myItems;
            try {
                if (myItems != null) {
                    myItems.remove((Object)t);
                    return myItems.size();
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return 0;
    }
    
    @NotNull
    public Set<T> getItems() {
        Object o = null;
        Label_0021: {
            try {
                if (this.myItems == null) {
                    final Object o2;
                    o = (o2 = Collections.emptySet());
                    break Label_0021;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            Object o2;
            o = (o2 = this.myItems);
            try {
                if (o2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/headerRoots/PathTree", "getItems"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return (Set<T>)o;
    }
    
    @NotNull
    public Collection<Self> getChildren() {
        Collection<Object> collection = null;
        Label_0024: {
            try {
                if (this.myChildren == null) {
                    final Collection<Object> collection2;
                    collection = (collection2 = Collections.emptySet());
                    break Label_0024;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            Collection<Object> collection2;
            collection = (collection2 = (Collection<Object>)this.myChildren.values());
            try {
                if (collection2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/headerRoots/PathTree", "getChildren"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return (Collection<Self>)collection;
    }
    
    @NotNull
    protected Self getNotNullSubTree(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "path", "com/jetbrains/cidr/lang/workspace/headerRoots/PathTree", "getNotNullSubTree"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        PathTree<PathTree<Self, T>, T> subTree;
        try {
            subTree = (PathTree<PathTree<Self, T>, T>)this.getSubTree(s, SearchStrategy.BUILD_IF_NOT_FOUND);
            if (subTree == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/headerRoots/PathTree", "getNotNullSubTree"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return (Self)subTree;
    }
    
    @Deprecated
    @Nullable
    public Self getSubTree(@NotNull final String s, final boolean b) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "path", "com/jetbrains/cidr/lang/workspace/headerRoots/PathTree", "getSubTree"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (b) {
                final SearchStrategy searchStrategy = SearchStrategy.BUILD_IF_NOT_FOUND;
                return this.getSubTree(s, searchStrategy);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final SearchStrategy searchStrategy = SearchStrategy.NULL_IF_NOT_FOUND;
        return this.getSubTree(s, searchStrategy);
    }
    
    @Nullable
    public Self getSubTree(@NotNull final String s, @NotNull final SearchStrategy searchStrategy) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "path", "com/jetbrains/cidr/lang/workspace/headerRoots/PathTree", "getSubTree"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (searchStrategy == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "strategy", "com/jetbrains/cidr/lang/workspace/headerRoots/PathTree", "getSubTree"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return this.a(StringUtil.split(FileUtil.toCanonicalPath(s), "/", true, false), searchStrategy);
    }
    
    @Nullable
    private Self a(@NotNull final List<String> list, final SearchStrategy searchStrategy) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "pathComps", "com/jetbrains/cidr/lang/workspace/headerRoots/PathTree", "getSubTree"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        PathTree<Self, T> self = (PathTree<Self, T>)this.self();
        final int size = list.size();
        int n = 0;
        while (true) {
            try {
                if (n >= size || self == null) {
                    break;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            final String s = list.get(n);
            final PathTree<Self, T> pathTree = self;
            PathTree<Self, T> pathTree2 = pathTree.b(s);
            Label_0134: {
                try {
                    if (pathTree2 != null) {
                        break Label_0134;
                    }
                    if (searchStrategy != SearchStrategy.BUILD_IF_NOT_FOUND) {
                        break Label_0134;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                pathTree2 = pathTree.a(s);
                break Label_0134;
                try {
                    if (searchStrategy == SearchStrategy.DEEPEST_EXISTING_PREFIX) {
                        return (Self)self;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            self = pathTree2;
            ++n;
        }
        return (Self)self;
    }
    
    protected Self self() {
        return (Self)this;
    }
    
    protected abstract Self createNewTree(@Nullable final Self p0);
    
    @NotNull
    private Set<T> a() {
        try {
            if (this.myItems == null) {
                this.myItems = (gnu.trove.THashSet<T>)new THashSet();
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        gnu.trove.THashSet<T> myItems;
        try {
            myItems = this.myItems;
            if (myItems == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/workspace/headerRoots/PathTree", "getItemsForWrite"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return (Set<T>)myItems;
    }
    
    @Nullable
    private Self b(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "key", "com/jetbrains/cidr/lang/workspace/headerRoots/PathTree", "getChild"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (this.myChildren == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return (Self)this.myChildren.get((Object)s);
    }
    
    private Self a(@NotNull final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "name"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/workspace/headerRoots/PathTree"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "createChild"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/workspace/headerRoots/PathTree.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: getfield        com/jetbrains/cidr/lang/workspace/headerRoots/PathTree.myChildren:Lgnu/trove/THashMap;
        //    48: ifnonnull       72
        //    51: aload_0        
        //    52: new             Lgnu/trove/THashMap;
        //    55: dup            
        //    56: getstatic       com/intellij/openapi/util/io/FileUtil.PATH_HASHING_STRATEGY:Lgnu/trove/TObjectHashingStrategy;
        //    59: invokespecial   gnu/trove/THashMap.<init>:(Lgnu/trove/TObjectHashingStrategy;)V
        //    62: putfield        com/jetbrains/cidr/lang/workspace/headerRoots/PathTree.myChildren:Lgnu/trove/THashMap;
        //    65: goto            72
        //    68: invokestatic    com/jetbrains/cidr/lang/workspace/headerRoots/PathTree.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    71: athrow         
        //    72: aload_0        
        //    73: aload_0        
        //    74: invokevirtual   com/jetbrains/cidr/lang/workspace/headerRoots/PathTree.self:()Lcom/jetbrains/cidr/lang/workspace/headerRoots/PathTree;
        //    77: invokevirtual   com/jetbrains/cidr/lang/workspace/headerRoots/PathTree.createNewTree:(Lcom/jetbrains/cidr/lang/workspace/headerRoots/PathTree;)Lcom/jetbrains/cidr/lang/workspace/headerRoots/PathTree;
        //    80: astore_2       
        //    81: aload_0        
        //    82: getfield        com/jetbrains/cidr/lang/workspace/headerRoots/PathTree.myChildren:Lgnu/trove/THashMap;
        //    85: aload_1        
        //    86: aload_2        
        //    87: invokevirtual   gnu/trove/THashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    90: pop            
        //    91: aload_2        
        //    92: areturn        
        //    Signature:
        //  (Ljava/lang/String;)TSelf;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     65     68     72     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.UnsupportedOperationException: The requested operation is not supported.
        //     at com.strobel.util.ContractUtils.unsupported(ContractUtils.java:27)
        //     at com.strobel.assembler.metadata.TypeReference.getRawType(TypeReference.java:276)
        //     at com.strobel.assembler.metadata.TypeReference.getRawType(TypeReference.java:271)
        //     at com.strobel.assembler.metadata.TypeReference.makeGenericType(TypeReference.java:150)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visitParameterizedType(TypeSubstitutionVisitor.java:187)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visitParameterizedType(TypeSubstitutionVisitor.java:25)
        //     at com.strobel.assembler.metadata.ParameterizedType.accept(ParameterizedType.java:103)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visit(TypeSubstitutionVisitor.java:39)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visitParameterizedType(TypeSubstitutionVisitor.java:173)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visitParameterizedType(TypeSubstitutionVisitor.java:25)
        //     at com.strobel.assembler.metadata.ParameterizedType.accept(ParameterizedType.java:103)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visit(TypeSubstitutionVisitor.java:39)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visitParameterizedType(TypeSubstitutionVisitor.java:173)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visitParameterizedType(TypeSubstitutionVisitor.java:25)
        //     at com.strobel.assembler.metadata.ParameterizedType.accept(ParameterizedType.java:103)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visit(TypeSubstitutionVisitor.java:39)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visitMethod(TypeSubstitutionVisitor.java:276)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2581)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2685)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:881)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:672)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:655)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:365)
        //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:96)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:344)
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
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    public enum SearchStrategy
    {
        NULL_IF_NOT_FOUND, 
        BUILD_IF_NOT_FOUND, 
        DEEPEST_EXISTING_PREFIX;
    }
}
