// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.objc;

import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import com.jetbrains.cidr.lang.symbols.OCTypeParameterSymbol;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.symbols.symtable.OCGlobalProjectSymbolsCache;
import com.intellij.util.Processor;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.resolve.OCResolveUtil;
import com.intellij.openapi.util.Condition;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.types.OCTypeArgument;
import com.jetbrains.cidr.lang.types.OCReferenceTypeBuilder;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.jetbrains.cidr.lang.symbols.OCQualifiedNameWithArguments;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.intellij.util.containers.MostlySingularMultiMap;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.visitors.OCTypeSubstitution;
import java.util.List;

public class OCInterfaceSymbolImpl extends OCClassSymbolImpl implements OCInterfaceSymbol
{
    private List<OCGenericParameterSymbol> myGenericParameters;
    @NotNull
    private OCTypeSubstitution mySubstitution;
    
    public OCInterfaceSymbolImpl() {
        this.mySubstitution = OCTypeSubstitution.ID;
    }
    
    public OCInterfaceSymbolImpl(@NotNull final Project project, @Nullable final VirtualFile virtualFile, final long n, @NotNull final String s, @NotNull final List<String> list, @Nullable final String s2, @Nullable final MostlySingularMultiMap<String, OCMemberSymbol> mostlySingularMultiMap, @NotNull final List<String> list2, @Nullable final OCReferenceType ocReferenceType, @NotNull final List<OCGenericParameterSymbol> myGenericParameters) {
        if (project == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbolImpl", "<init>"));
        }
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbolImpl", "<init>"));
        }
        if (list == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "attributes", "com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbolImpl", "<init>"));
        }
        if (list2 == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "protocolNames", "com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbolImpl", "<init>"));
        }
        if (myGenericParameters == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "genericParameters", "com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbolImpl", "<init>"));
        }
        super(project, virtualFile, n, s, list, s2, mostlySingularMultiMap, list2, ocReferenceType);
        this.mySubstitution = OCTypeSubstitution.ID;
        this.myGenericParameters = myGenericParameters;
    }
    
    public OCInterfaceSymbolImpl(@NotNull final OCInterfaceSymbolImpl ocInterfaceSymbolImpl, @NotNull final OCTypeSubstitution ocTypeSubstitution, final boolean b, @NotNull final OCResolveContext ocResolveContext) {
        if (ocInterfaceSymbolImpl == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "origin", "com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbolImpl", "<init>"));
        }
        if (ocTypeSubstitution == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "substitution", "com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbolImpl", "<init>"));
        }
        if (ocResolveContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbolImpl", "<init>"));
        }
        this(ocInterfaceSymbolImpl.getProject(), ocInterfaceSymbolImpl.getContainingFile(), ocInterfaceSymbolImpl.getOffset(), ocInterfaceSymbolImpl.getName(), ocInterfaceSymbolImpl.getAttributes(), ocInterfaceSymbolImpl.getCategoryName(), ocInterfaceSymbolImpl.getMembers(), ocInterfaceSymbolImpl.getProtocolNames(), ocInterfaceSymbolImpl.getSuperType(), ocInterfaceSymbolImpl.getGenericParameters());
        this.mySubstitution = OCTypeSubstitution.compose(ocInterfaceSymbolImpl.mySubstitution, ocTypeSubstitution, b, ocResolveContext);
    }
    
    @NotNull
    @Override
    public OCSymbolKind getKind() {
        OCSymbolKind interface1;
        try {
            interface1 = OCSymbolKind.INTERFACE;
            if (interface1 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbolImpl", "getKind"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return interface1;
    }
    
    @NotNull
    @Override
    public OCType getType() {
        final List<OCTypeArgument> genericArguments = this.getGenericArguments();
        OCQualifiedName qualifiedName;
        if (genericArguments.isEmpty()) {
            qualifiedName = this.getQualifiedName();
        }
        else {
            qualifiedName = new OCQualifiedNameWithArguments(null, this.myName, genericArguments);
        }
        OCReferenceType build;
        try {
            build = new OCReferenceTypeBuilder(qualifiedName).setSubstitution(this.mySubstitution).build();
            if (build == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbolImpl", "getType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return build;
    }
    
    @NotNull
    @Override
    public List<OCTypeArgument> getGenericArguments() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbolImpl.myGenericParameters:Ljava/util/List;
        //     4: invokeinterface java/util/List.isEmpty:()Z
        //     9: ifne            29
        //    12: aload_0        
        //    13: getfield        com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbolImpl.mySubstitution:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;
        //    16: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.ID:Lcom/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution;
        //    19: if_acmpne       78
        //    22: goto            29
        //    25: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbolImpl.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    28: athrow         
        //    29: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //    32: dup            
        //    33: ifnonnull       77
        //    36: goto            43
        //    39: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbolImpl.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    42: athrow         
        //    43: new             Ljava/lang/IllegalStateException;
        //    46: dup            
        //    47: ldc             "@NotNull method %s.%s must not return null"
        //    49: ldc             2
        //    51: anewarray       Ljava/lang/Object;
        //    54: dup            
        //    55: ldc             0
        //    57: ldc             "com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbolImpl"
        //    59: aastore        
        //    60: dup            
        //    61: ldc             1
        //    63: ldc             "getGenericArguments"
        //    65: aastore        
        //    66: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    69: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    72: athrow         
        //    73: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbolImpl.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    76: athrow         
        //    77: areturn        
        //    78: invokestatic    com/intellij/util/containers/ContainerUtil.newArrayList:()Ljava/util/ArrayList;
        //    81: astore_1       
        //    82: aload_0        
        //    83: getfield        com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbolImpl.myGenericParameters:Ljava/util/List;
        //    86: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    91: astore_2       
        //    92: aload_2        
        //    93: invokeinterface java/util/Iterator.hasNext:()Z
        //    98: ifeq            151
        //   101: aload_2        
        //   102: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   107: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCGenericParameterSymbol;
        //   110: astore_3       
        //   111: aload_0        
        //   112: getfield        com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbolImpl.mySubstitution:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;
        //   115: aload_3        
        //   116: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.getSubstitutionFor:(Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;)Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   119: astore          4
        //   121: aload_1        
        //   122: aload           4
        //   124: ifnull          136
        //   127: aload           4
        //   129: goto            142
        //   132: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbolImpl.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   135: athrow         
        //   136: aload_3        
        //   137: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCGenericParameterSymbol.getDefaultValue:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   142: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   147: pop            
        //   148: goto            92
        //   151: aload_1        
        //   152: invokestatic    java/util/Collections.unmodifiableList:(Ljava/util/List;)Ljava/util/List;
        //   155: dup            
        //   156: ifnonnull       193
        //   159: new             Ljava/lang/IllegalStateException;
        //   162: dup            
        //   163: ldc             "@NotNull method %s.%s must not return null"
        //   165: ldc             2
        //   167: anewarray       Ljava/lang/Object;
        //   170: dup            
        //   171: ldc             0
        //   173: ldc             "com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbolImpl"
        //   175: aastore        
        //   176: dup            
        //   177: ldc             1
        //   179: ldc             "getGenericArguments"
        //   181: aastore        
        //   182: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   185: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   188: athrow         
        //   189: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbolImpl.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   192: athrow         
        //   193: areturn        
        //    Signature:
        //  ()Ljava/util/List<Lcom/jetbrains/cidr/lang/types/OCTypeArgument;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      22     25     29     Ljava/lang/IllegalArgumentException;
        //  12     36     39     43     Ljava/lang/IllegalArgumentException;
        //  29     73     73     77     Ljava/lang/IllegalArgumentException;
        //  121    132    132    136    Ljava/lang/IllegalArgumentException;
        //  151    189    189    193    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0029:
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
    
    @NotNull
    @Override
    public OCReferenceType getSuperType() {
        OCReferenceType ocReferenceType;
        try {
            ocReferenceType = (OCReferenceType)this.mySubstitution.substitute((OCType)super.getSuperType(), new OCResolveContext());
            if (ocReferenceType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbolImpl", "getSuperType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return ocReferenceType;
    }
    
    @Override
    public OCInterfaceSymbol getInterface() {
        return this;
    }
    
    @Override
    public OCSymbol getAssociatedSymbol() {
        return this.getImplementation();
    }
    
    @Nullable
    @Override
    public OCImplementationSymbol getImplementation() {
        OCImplementationSymbol ocImplementationSymbol = this.getImplementation(this.getCategoryName());
        try {
            if (ocImplementationSymbol != null || this.getCategoryName() == null) {
                return ocImplementationSymbol;
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        ocImplementationSymbol = this.getImplementation(null);
        return ocImplementationSymbol;
    }
    
    @Nullable
    @Override
    public OCImplementationSymbol getImplementation(@Nullable final String s) {
        final Condition condition = p2 -> {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_2        
            //     1: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol;
            //     4: ifeq            67
            //     7: iload_0        
            //     8: aload_2        
            //     9: invokestatic    com/jetbrains/cidr/lang/search/scopes/OCSearchScope.isInProjectSources:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
            //    12: if_icmpne       67
            //    15: goto            22
            //    18: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbolImpl.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    21: athrow         
            //    22: aload_2        
            //    23: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isPredeclaration:()Z
            //    28: ifne            67
            //    31: goto            38
            //    34: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbolImpl.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    37: athrow         
            //    38: aload_1        
            //    39: aload_2        
            //    40: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol;
            //    43: invokevirtual   com/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol.getCategoryName:()Ljava/lang/String;
            //    46: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/String;Ljava/lang/String;)Z
            //    49: ifeq            67
            //    52: goto            59
            //    55: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbolImpl.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    58: athrow         
            //    59: iconst_1       
            //    60: goto            68
            //    63: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbolImpl.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    66: athrow         
            //    67: iconst_0       
            //    68: ireturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  0      15     18     22     Ljava/lang/IllegalArgumentException;
            //  7      31     34     38     Ljava/lang/IllegalArgumentException;
            //  22     52     55     59     Ljava/lang/IllegalArgumentException;
            //  38     63     63     67     Ljava/lang/IllegalArgumentException;
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
        };
        final CommonProcessors.FindFirstProcessor<OCSymbol> findFirstProcessor = new CommonProcessors.FindFirstProcessor<OCSymbol>() {
            protected boolean accept(final OCSymbol ocSymbol) {
                return condition.value((Object)ocSymbol);
            }
        };
        final OCFile containingOCFile = this.getContainingOCFile();
        try {
            if (containingOCFile != null) {
                OCResolveUtil.processGlobalSymbols(this.myName, (PsiElement)containingOCFile, (Processor<OCSymbol>)findFirstProcessor);
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        try {
            if (!findFirstProcessor.isFound()) {
                return (OCImplementationSymbol)OCGlobalProjectSymbolsCache.findNearestTopLevelSymbol(this.myProject, this.myName, (Condition<OCSymbol>)condition, this.myFile);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw d(ex2);
        }
        return (OCImplementationSymbol)findFirstProcessor.getFoundValue();
    }
    
    @NotNull
    @Override
    public List<OCGenericParameterSymbol> getGenericParameters() {
        List unmodifiableList;
        try {
            unmodifiableList = ContainerUtil.newUnmodifiableList((List)this.myGenericParameters);
            if (unmodifiableList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbolImpl", "getGenericParameters"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return (List<OCGenericParameterSymbol>)unmodifiableList;
    }
    
    @NotNull
    @Override
    public OCTypeSubstitution getSubstitution() {
        OCTypeSubstitution mySubstitution;
        try {
            mySubstitution = this.mySubstitution;
            if (mySubstitution == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbolImpl", "getSubstitution"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return mySubstitution;
    }
    
    @Override
    public boolean processMembers(final String s, @NotNull final Processor<OCMemberSymbol> processor) {
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbolImpl", "processMembers"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return super.processMembers(s, this.getSubstitutingProcessor(processor));
    }
    
    @Override
    public <T extends OCMemberSymbol> boolean processMembers(@Nullable final String s, @NotNull final Class<? extends T> clazz, @NotNull final Processor<? super T> processor) {
        try {
            if (clazz == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "memberClass", "com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbolImpl", "processMembers"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbolImpl", "processMembers"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw d(ex2);
        }
        return super.processMembers(s, (Class<? extends OCMemberSymbol>)clazz, this.getSubstitutingProcessor((com.intellij.util.Processor<? super OCMemberSymbol>)processor));
    }
    
    @NotNull
    protected <T extends OCMemberSymbol> Processor<T> getSubstitutingProcessor(@NotNull final Processor<? super T> processor) {
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbolImpl", "getSubstitutingProcessor"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        Label_0107: {
            Processor processor3 = null;
            Label_0072: {
                try {
                    if (this.mySubstitution == OCTypeSubstitution.ID) {
                        break Label_0107;
                    }
                    final OCInterfaceSymbolImpl ocInterfaceSymbolImpl = this;
                    final Processor<T> processor2 = (Processor<T>)processor;
                    processor3 = (ocMemberSymbol -> {
                        try {
                            if (processor2 == null) {
                                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbolImpl", "lambda$getSubstitutingProcessor$1"));
                            }
                        }
                        catch (IllegalArgumentException ex) {
                            throw d(ex);
                        }
                        return processor2.process((Object)this.mySubstitution.substitute(ocMemberSymbol, null, false, new OCResolveContext()));
                    });
                    if (processor3 == null) {
                        break Label_0072;
                    }
                    return (Processor<T>)processor3;
                }
                catch (IllegalArgumentException ex2) {
                    throw d(ex2);
                }
                try {
                    final OCInterfaceSymbolImpl ocInterfaceSymbolImpl = this;
                    final Processor<T> processor2 = (Processor<T>)processor;
                    processor3 = (ocMemberSymbol -> {
                        try {
                            if (processor2 == null) {
                                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbolImpl", "lambda$getSubstitutingProcessor$1"));
                            }
                        }
                        catch (IllegalArgumentException ex) {
                            throw d(ex);
                        }
                        return processor2.process((Object)this.mySubstitution.substitute(ocMemberSymbol, null, false, new OCResolveContext()));
                    });
                    if (processor3 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbolImpl", "getSubstitutingProcessor"));
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw d(ex3);
                }
            }
            return (Processor<T>)processor3;
            try {
                if (processor == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbolImpl", "getSubstitutingProcessor"));
                }
            }
            catch (IllegalArgumentException ex4) {
                throw d(ex4);
            }
        }
        return (Processor<T>)processor;
    }
    
    @NotNull
    @Override
    public List<OCTypeParameterSymbol> getTemplateParameters() {
        List<OCGenericParameterSymbol> genericParameters;
        try {
            genericParameters = this.getGenericParameters();
            if (genericParameters == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbolImpl", "getTemplateParameters"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return (List<OCTypeParameterSymbol>)genericParameters;
    }
    
    @Nullable
    @Override
    public List<OCTypeArgument> getTemplateSpecialization() {
        return null;
    }
    
    @Override
    public int getRequiredTemplateArgumentsCnt() {
        return this.getTemplateParameters().size();
    }
    
    @Override
    public boolean isTemplateSymbol() {
        try {
            if (this.myGenericParameters.size() != 0) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return false;
    }
    
    @Override
    public boolean isVariadicTemplate() {
        return false;
    }
    
    @Override
    public boolean isSpecialization() {
        return false;
    }
    
    @Override
    public boolean isExplicitInstantiation() {
        return false;
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbolImpl", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbolImpl", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw d(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbolImpl", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw d(ex3);
        }
        try {
            if (!super.deepEqualStep(comparator, o, o2)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw d(ex4);
        }
        final OCInterfaceSymbolImpl ocInterfaceSymbolImpl = (OCInterfaceSymbolImpl)o;
        final OCInterfaceSymbolImpl ocInterfaceSymbolImpl2 = (OCInterfaceSymbolImpl)o2;
        try {
            if (!comparator.equalObjects(ocInterfaceSymbolImpl.mySubstitution, (DeepEqual.Equality<Object>)ocInterfaceSymbolImpl2.mySubstitution)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw d(ex5);
        }
        return comparator.equalIterable(ocInterfaceSymbolImpl.myGenericParameters, ocInterfaceSymbolImpl2.myGenericParameters);
    }
    
    private static IllegalArgumentException d(final IllegalArgumentException ex) {
        return ex;
    }
}
