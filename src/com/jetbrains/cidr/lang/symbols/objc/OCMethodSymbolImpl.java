// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.objc;

import com.intellij.openapi.util.Comparing;
import com.intellij.psi.PsiFile;
import com.intellij.util.Processor;
import com.intellij.util.CommonProcessors;
import java.util.Collection;
import com.jetbrains.cidr.lang.refactoring.OCNameSuggester;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.OCIcons;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.OCTestFrameworks;
import javax.swing.Icon;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.types.OCObjectType;
import java.io.Serializable;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import java.util.Iterator;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.project.Project;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.visitors.OCTypeSubstitution;
import java.util.List;
import com.jetbrains.cidr.lang.types.OCType;

public class OCMethodSymbolImpl extends OCMemberSymbolImpl implements OCMethodSymbol
{
    private boolean myStatic;
    private boolean myOptional;
    private boolean myVararg;
    private OCType myReturnType;
    private OCPropertySymbol myGeneratedFromProperty;
    private List<SelectorPartSymbol> mySelectors;
    private OCTypeSubstitution mySubstitution;
    
    public OCMethodSymbolImpl() {
        this.mySubstitution = OCTypeSubstitution.ID;
    }
    
    public OCMethodSymbolImpl(@NotNull final OCMethodSymbolImpl ocMethodSymbolImpl, @NotNull final OCTypeSubstitution ocTypeSubstitution, @NotNull final OCResolveContext ocResolveContext) {
        if (ocMethodSymbolImpl == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "origin", "com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl", "<init>"));
        }
        if (ocTypeSubstitution == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "substitution", "com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl", "<init>"));
        }
        if (ocResolveContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl", "<init>"));
        }
        this(ocMethodSymbolImpl.getProject(), ocMethodSymbolImpl.getContainingFile(), ocMethodSymbolImpl.getOffset(), ocMethodSymbolImpl.getName(), ocMethodSymbolImpl.getAttributes(), ocMethodSymbolImpl.myParent, ocMethodSymbolImpl.myStatic, ocMethodSymbolImpl.myOptional, ocMethodSymbolImpl.myVararg, ocMethodSymbolImpl.myReturnType, ContainerUtil.newArrayList((Iterable)ocMethodSymbolImpl.mySelectors), ocMethodSymbolImpl.myGeneratedFromProperty);
        this.mySubstitution = OCTypeSubstitution.compose(ocMethodSymbolImpl.mySubstitution, ocTypeSubstitution, ocResolveContext);
    }
    
    public OCMethodSymbolImpl(final Project project, final VirtualFile virtualFile, final long n, @Nullable final String s, @NotNull final List<String> list, @NotNull final OCClassSymbol ocClassSymbol, final boolean myStatic, final boolean myOptional, final boolean myVararg, @NotNull final OCType myReturnType, @NotNull final List<SelectorPartSymbol> list2, @Nullable final OCPropertySymbol myGeneratedFromProperty) {
        if (list == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "attributes", "com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl", "<init>"));
        }
        if (ocClassSymbol == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parent", "com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl", "<init>"));
        }
        if (myReturnType == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "returnType", "com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl", "<init>"));
        }
        if (list2 == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "selectors", "com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl", "<init>"));
        }
        super(project, virtualFile, n, s, list, ocClassSymbol);
        this.mySubstitution = OCTypeSubstitution.ID;
        this.myStatic = myStatic;
        this.myGeneratedFromProperty = myGeneratedFromProperty;
        this.myReturnType = myReturnType;
        this.myOptional = myOptional;
        this.myVararg = myVararg;
        this.mySelectors = (List<SelectorPartSymbol>)ContainerUtil.trimToSize((List)list2);
        final Iterator<SelectorPartSymbol> iterator = list2.iterator();
        while (iterator.hasNext()) {
            final OCDeclaratorSymbol parameterWithoutSubstitution = iterator.next().getParameterWithoutSubstitution();
            try {
                if (parameterWithoutSubstitution == null) {
                    continue;
                }
                parameterWithoutSubstitution.setParentMethod(this);
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
        }
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl", "deepEqualStep"));
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
        final OCMethodSymbolImpl ocMethodSymbolImpl = (OCMethodSymbolImpl)o;
        final OCMethodSymbolImpl ocMethodSymbolImpl2 = (OCMethodSymbolImpl)o2;
        try {
            if (ocMethodSymbolImpl.myOptional != ocMethodSymbolImpl2.myOptional) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw c(ex5);
        }
        try {
            if (ocMethodSymbolImpl.myStatic != ocMethodSymbolImpl2.myStatic) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw c(ex6);
        }
        try {
            if (ocMethodSymbolImpl.myVararg != ocMethodSymbolImpl2.myVararg) {
                return false;
            }
        }
        catch (IllegalArgumentException ex7) {
            throw c(ex7);
        }
        try {
            if (!comparator.equalObjects(ocMethodSymbolImpl.myGeneratedFromProperty, (DeepEqual.Equality<Object>)ocMethodSymbolImpl2.myGeneratedFromProperty)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex8) {
            throw c(ex8);
        }
        try {
            if (!comparator.equalObjects(ocMethodSymbolImpl.myReturnType, (DeepEqual.Equality<Object>)ocMethodSymbolImpl2.myReturnType)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex9) {
            throw c(ex9);
        }
        try {
            if (!comparator.equalObjects(ocMethodSymbolImpl.mySubstitution, (DeepEqual.Equality<Object>)ocMethodSymbolImpl2.mySubstitution)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex10) {
            throw c(ex10);
        }
        return comparator.equalIterable(ocMethodSymbolImpl.mySelectors, ocMethodSymbolImpl2.mySelectors);
    }
    
    @Override
    public void updateOffset(final int n, final int n2, final int n3) {
        super.updateOffset(n, n2, n3);
        final Iterator<SelectorPartSymbol> iterator = this.mySelectors.iterator();
        while (iterator.hasNext()) {
            final OCDeclaratorSymbol parameter = iterator.next().getParameter();
            try {
                if (parameter == null) {
                    continue;
                }
                parameter.updateOffset(n, n2, n3);
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
        }
    }
    
    @Override
    public void compact() {
        super.compact();
        final Iterator<SelectorPartSymbol> iterator = this.mySelectors.iterator();
        while (iterator.hasNext()) {
            final OCDeclaratorSymbol parameter = iterator.next().getParameter();
            try {
                if (parameter == null) {
                    continue;
                }
                parameter.compact();
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
        }
    }
    
    @Override
    protected Class<? extends PsiElement> getPsiElementClass() {
        try {
            if (this.myGeneratedFromProperty == null) {
                final Serializable s = OCMethod.class;
                return (Class<? extends PsiElement>)s;
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        final Serializable s = OCDeclarator.class;
        return (Class<? extends PsiElement>)s;
    }
    
    @NotNull
    @Override
    public OCType getReturnType(@Nullable final OCObjectType p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl.myReturnType:Lcom/jetbrains/cidr/lang/types/OCType;
        //     4: instanceof      Lcom/jetbrains/cidr/lang/types/OCReferenceType;
        //     7: ifeq            211
        //    10: aload_0        
        //    11: getfield        com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl.myReturnType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    14: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:()Ljava/lang/String;
        //    17: ldc             "instancetype"
        //    19: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    22: ifeq            211
        //    25: goto            32
        //    28: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    31: athrow         
        //    32: aload_1        
        //    33: ifnull          140
        //    36: goto            43
        //    39: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    42: athrow         
        //    43: aload_1        
        //    44: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.getClassSymbol:()Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //    47: ifnull          140
        //    50: goto            57
        //    53: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    56: athrow         
        //    57: aload_1        
        //    58: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.getClassSymbol:()Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //    61: aload_0        
        //    62: invokevirtual   com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl.getParent:()Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //    65: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.isSubclass:(Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;)Z
        //    70: ifeq            140
        //    73: goto            80
        //    76: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    79: athrow         
        //    80: aload_1        
        //    81: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.to:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //    84: aload_0        
        //    85: getfield        com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl.myReturnType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    88: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getNullability:()Lcom/jetbrains/cidr/lang/types/OCNullability;
        //    91: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.cloneWithNullability:(Lcom/jetbrains/cidr/lang/types/OCNullability;)Lcom/jetbrains/cidr/lang/types/OCType;
        //    94: dup            
        //    95: ifnonnull       139
        //    98: goto            105
        //   101: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   104: athrow         
        //   105: new             Ljava/lang/IllegalStateException;
        //   108: dup            
        //   109: ldc             "@NotNull method %s.%s must not return null"
        //   111: ldc             2
        //   113: anewarray       Ljava/lang/Object;
        //   116: dup            
        //   117: ldc             0
        //   119: ldc             "com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl"
        //   121: aastore        
        //   122: dup            
        //   123: ldc             1
        //   125: ldc             "getReturnType"
        //   127: aastore        
        //   128: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   131: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   134: athrow         
        //   135: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   138: athrow         
        //   139: areturn        
        //   140: aload_0        
        //   141: invokevirtual   com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl.getParent:()Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   144: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   149: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.to:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   152: astore_2       
        //   153: aload_2        
        //   154: ldc             "instancetype"
        //   156: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.cloneWithAliasName:(Ljava/lang/String;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   159: astore_3       
        //   160: aload_3        
        //   161: aload_0        
        //   162: getfield        com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl.myReturnType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   165: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getNullability:()Lcom/jetbrains/cidr/lang/types/OCNullability;
        //   168: invokevirtual   com/jetbrains/cidr/lang/types/OCType.attachNullability:(Lcom/jetbrains/cidr/lang/types/OCNullability;)V
        //   171: aload_3        
        //   172: dup            
        //   173: ifnonnull       210
        //   176: new             Ljava/lang/IllegalStateException;
        //   179: dup            
        //   180: ldc             "@NotNull method %s.%s must not return null"
        //   182: ldc             2
        //   184: anewarray       Ljava/lang/Object;
        //   187: dup            
        //   188: ldc             0
        //   190: ldc             "com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl"
        //   192: aastore        
        //   193: dup            
        //   194: ldc             1
        //   196: ldc             "getReturnType"
        //   198: aastore        
        //   199: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   202: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   205: athrow         
        //   206: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   209: athrow         
        //   210: areturn        
        //   211: aload_0        
        //   212: getfield        com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl.mySubstitution:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;
        //   215: aload_0        
        //   216: getfield        com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl.myReturnType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   219: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   222: dup            
        //   223: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:()V
        //   226: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.substitute:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   229: dup            
        //   230: ifnonnull       267
        //   233: new             Ljava/lang/IllegalStateException;
        //   236: dup            
        //   237: ldc             "@NotNull method %s.%s must not return null"
        //   239: ldc             2
        //   241: anewarray       Ljava/lang/Object;
        //   244: dup            
        //   245: ldc             0
        //   247: ldc             "com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl"
        //   249: aastore        
        //   250: dup            
        //   251: ldc             1
        //   253: ldc             "getReturnType"
        //   255: aastore        
        //   256: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   259: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   262: athrow         
        //   263: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   266: athrow         
        //   267: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      25     28     32     Ljava/lang/IllegalArgumentException;
        //  10     36     39     43     Ljava/lang/IllegalArgumentException;
        //  32     50     53     57     Ljava/lang/IllegalArgumentException;
        //  43     73     76     80     Ljava/lang/IllegalArgumentException;
        //  57     98     101    105    Ljava/lang/IllegalArgumentException;
        //  80     135    135    139    Ljava/lang/IllegalArgumentException;
        //  160    206    206    210    Ljava/lang/IllegalArgumentException;
        //  211    263    263    267    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0032:
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
    public OCType getReturnType() {
        OCType returnType;
        try {
            returnType = this.getReturnType(null);
            if (returnType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl", "getReturnType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return returnType;
    }
    
    @Override
    public boolean isStatic() {
        return this.myStatic;
    }
    
    @Override
    public boolean isOptional() {
        return this.myOptional;
    }
    
    @Override
    public boolean isVararg() {
        return this.myVararg;
    }
    
    @Override
    public boolean isFactoryMethod() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl.getParent:()Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //     4: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getName:()Ljava/lang/String;
        //     9: ldc             "NS"
        //    11: invokestatic    com/intellij/openapi/util/text/StringUtil.trimStart:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    14: astore_1       
        //    15: aload_0        
        //    16: invokevirtual   com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl.getName:()Ljava/lang/String;
        //    19: astore_2       
        //    20: aload_0        
        //    21: invokevirtual   com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl.isStatic:()Z
        //    24: ifeq            259
        //    27: aload_1        
        //    28: invokestatic    com/intellij/psi/codeStyle/NameUtil.nameToWordsLowerCase:(Ljava/lang/String;)Ljava/util/List;
        //    31: astore_3       
        //    32: aload_2        
        //    33: invokestatic    com/intellij/psi/codeStyle/NameUtil.nameToWordsLowerCase:(Ljava/lang/String;)Ljava/util/List;
        //    36: astore          4
        //    38: aload           4
        //    40: invokestatic    com/intellij/util/containers/ContainerUtil.getFirstItem:(Ljava/util/List;)Ljava/lang/Object;
        //    43: checkcast       Ljava/lang/String;
        //    46: astore          5
        //    48: ldc             "default"
        //    50: aload           5
        //    52: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //    55: ifne            109
        //    58: ldc             "common"
        //    60: aload           5
        //    62: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //    65: ifne            109
        //    68: goto            75
        //    71: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    74: athrow         
        //    75: ldc             "shared"
        //    77: aload           5
        //    79: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //    82: ifne            109
        //    85: goto            92
        //    88: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    91: athrow         
        //    92: ldc             "main"
        //    94: aload           5
        //    96: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //    99: ifeq            125
        //   102: goto            109
        //   105: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   108: athrow         
        //   109: aload           4
        //   111: iconst_0       
        //   112: invokeinterface java/util/List.remove:(I)Ljava/lang/Object;
        //   117: pop            
        //   118: goto            125
        //   121: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   124: athrow         
        //   125: aload_3        
        //   126: invokeinterface java/util/List.isEmpty:()Z
        //   131: ifne            151
        //   134: aload           4
        //   136: invokeinterface java/util/List.isEmpty:()Z
        //   141: ifeq            157
        //   144: goto            151
        //   147: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   150: athrow         
        //   151: iconst_0       
        //   152: ireturn        
        //   153: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   156: athrow         
        //   157: iconst_1       
        //   158: istore          6
        //   160: iload           6
        //   162: aload_3        
        //   163: invokeinterface java/util/List.size:()I
        //   168: aload           4
        //   170: invokeinterface java/util/List.size:()I
        //   175: invokestatic    java/lang/Math.min:(II)I
        //   178: if_icmpgt       259
        //   181: iconst_0       
        //   182: istore          7
        //   184: iload           7
        //   186: iload           6
        //   188: if_icmpge       251
        //   191: aload_3        
        //   192: aload_3        
        //   193: invokeinterface java/util/List.size:()I
        //   198: iload           6
        //   200: isub           
        //   201: iload           7
        //   203: iadd           
        //   204: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   209: checkcast       Ljava/lang/String;
        //   212: astore          8
        //   214: aload           4
        //   216: iload           7
        //   218: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   223: checkcast       Ljava/lang/String;
        //   226: astore          9
        //   228: aload           9
        //   230: aload           8
        //   232: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   235: ifne            245
        //   238: goto            253
        //   241: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   244: athrow         
        //   245: iinc            7, 1
        //   248: goto            184
        //   251: iconst_1       
        //   252: ireturn        
        //   253: iinc            6, 1
        //   256: goto            160
        //   259: iconst_0       
        //   260: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  48     68     71     75     Ljava/lang/IllegalArgumentException;
        //  58     85     88     92     Ljava/lang/IllegalArgumentException;
        //  75     102    105    109    Ljava/lang/IllegalArgumentException;
        //  92     118    121    125    Ljava/lang/IllegalArgumentException;
        //  125    144    147    151    Ljava/lang/IllegalArgumentException;
        //  134    153    153    157    Ljava/lang/IllegalArgumentException;
        //  228    241    241    245    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0075:
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
    public boolean isConstructorMethod() {
        Label_0026: {
            try {
                if (this.isStatic()) {
                    return false;
                }
                final OCMethodSymbolImpl ocMethodSymbolImpl = this;
                final String s = ocMethodSymbolImpl.getName();
                final String s2 = "init";
                final boolean b = OCElementUtil.startsWithWord(s, s2);
                if (b) {
                    break Label_0026;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            try {
                final OCMethodSymbolImpl ocMethodSymbolImpl = this;
                final String s = ocMethodSymbolImpl.getName();
                final String s2 = "init";
                final boolean b = OCElementUtil.startsWithWord(s, s2);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
        }
        return false;
    }
    
    @Override
    public boolean isClassConstructorMethod() {
        Label_0026: {
            try {
                if (!this.isStatic()) {
                    return false;
                }
                final OCMethodSymbolImpl ocMethodSymbolImpl = this;
                final String s = ocMethodSymbolImpl.getName();
                final String s2 = "objectWith";
                final boolean b = OCElementUtil.startsWithWord(s, s2);
                if (b) {
                    break Label_0026;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            try {
                final OCMethodSymbolImpl ocMethodSymbolImpl = this;
                final String s = ocMethodSymbolImpl.getName();
                final String s2 = "objectWith";
                final boolean b = OCElementUtil.startsWithWord(s, s2);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
        }
        return false;
    }
    
    @Override
    public boolean isPredeclaration() {
        try {
            if (!(this.getParent() instanceof OCImplementationSymbol)) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return false;
    }
    
    @Nullable
    @Override
    public OCMethodSymbol getDefinitionSymbol() {
        try {
            if (this.isPredeclaration()) {
                return this.getAssociatedSymbol();
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return this;
    }
    
    @Nullable
    @Override
    public Icon getBaseIcon() {
        return this.a(false);
    }
    
    @Override
    public Icon computeFullIconNow(@Nullable final PsiElement psiElement) {
        final Icon a = this.a(true);
        try {
            if (a == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (OCTestFrameworks.isTestMethodOrFunction(this)) {
                return OCIcons.getTestIcon(a);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        return a;
    }
    
    private Icon a(final boolean b) {
        final OCPropertySymbol generatedFromProperty = this.getGeneratedFromProperty();
        Label_0041: {
            Label_0020: {
                try {
                    if (generatedFromProperty == null) {
                        break Label_0041;
                    }
                    final boolean b2 = b;
                    if (b2) {
                        break Label_0020;
                    }
                    return generatedFromProperty.getIcon();
                }
                catch (IllegalArgumentException ex) {
                    throw c(ex);
                }
                try {
                    final boolean b2 = b;
                    if (b2) {
                        return generatedFromProperty.computeFullIconNow(null);
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw c(ex2);
                }
            }
            return generatedFromProperty.getIcon();
            try {
                if (!b || !(this.myParent instanceof OCImplementationSymbol)) {
                    return OCIcons.getMethodIcon(this.isStatic(), this.isOptional(), false);
                }
            }
            catch (IllegalArgumentException ex3) {
                throw c(ex3);
            }
        }
        final OCMethodSymbol associatedSymbol = this.getAssociatedSymbol((PsiElement)this.myParent.getContainingOCFile());
        if (associatedSymbol != null) {
            final OCPropertySymbol generatedFromProperty2 = associatedSymbol.getGeneratedFromProperty();
            try {
                if (generatedFromProperty2 != null) {
                    return generatedFromProperty2.computeFullIconNow(null);
                }
            }
            catch (IllegalArgumentException ex4) {
                throw c(ex4);
            }
        }
        return OCIcons.getMethodIcon(this.isStatic(), this.isOptional(), false);
    }
    
    @NotNull
    @Override
    public OCSymbolKind getKind() {
        OCSymbolKind method;
        try {
            method = OCSymbolKind.METHOD;
            if (method == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl", "getKind"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return method;
    }
    
    @Override
    public boolean isGetter() {
        Label_0027: {
            try {
                if (!OCNameSuggester.isObjCGetter(this.myName)) {
                    return false;
                }
                final OCMethodSymbolImpl ocMethodSymbolImpl = this;
                final OCType ocType = ocMethodSymbolImpl.getEffectiveResolvedType();
                final boolean b = ocType.isVoid();
                if (!b) {
                    break Label_0027;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            try {
                final OCMethodSymbolImpl ocMethodSymbolImpl = this;
                final OCType ocType = ocMethodSymbolImpl.getEffectiveResolvedType();
                final boolean b = ocType.isVoid();
                if (!b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
        }
        return false;
    }
    
    @Override
    public boolean isSetter() {
        Label_0027: {
            try {
                if (!OCNameSuggester.isObjCSetter(this.myName)) {
                    return false;
                }
                final OCMethodSymbolImpl ocMethodSymbolImpl = this;
                final OCType ocType = ocMethodSymbolImpl.getEffectiveResolvedType();
                final boolean b = ocType.isVoid();
                if (b) {
                    break Label_0027;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            try {
                final OCMethodSymbolImpl ocMethodSymbolImpl = this;
                final OCType ocType = ocMethodSymbolImpl.getEffectiveResolvedType();
                final boolean b = ocType.isVoid();
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
        }
        return false;
    }
    
    @Override
    public OCPropertySymbol getGeneratedFromProperty() {
        return this.myGeneratedFromProperty;
    }
    
    @Override
    public boolean isSynthetic() {
        try {
            if (this.myGeneratedFromProperty != null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return false;
    }
    
    @Override
    public boolean isForbiddenByARC(@NotNull final PsiElement p0) {
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
        //    18: ldc             "context"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isForbiddenByARC"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: aload_1        
        //    46: invokespecial   com/jetbrains/cidr/lang/symbols/objc/OCMemberSymbolImpl.isForbiddenByARC:(Lcom/intellij/psi/PsiElement;)Z
        //    49: ifne            90
        //    52: ldc             "dealloc"
        //    54: aload_0        
        //    55: getfield        com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl.myName:Ljava/lang/String;
        //    58: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    61: ifeq            98
        //    64: goto            71
        //    67: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    70: athrow         
        //    71: aload_1        
        //    72: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //    77: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.isArcEnabled:(Lcom/intellij/psi/PsiFile;)Z
        //    80: ifeq            98
        //    83: goto            90
        //    86: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    89: athrow         
        //    90: iconst_1       
        //    91: goto            99
        //    94: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    97: athrow         
        //    98: iconst_0       
        //    99: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     64     67     71     Ljava/lang/IllegalArgumentException;
        //  52     83     86     90     Ljava/lang/IllegalArgumentException;
        //  71     94     94     98     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0071:
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
    public boolean isAccessorWithAliasedName() {
        final OCPropertySymbol generatedFromProperty = this.getGeneratedFromProperty();
        try {
            if (generatedFromProperty == null) {
                return false;
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        Label_0077: {
            Label_0051: {
                Label_0041: {
                    try {
                        if (!this.isGetter()) {
                            break Label_0051;
                        }
                        final OCPropertySymbol ocPropertySymbol = generatedFromProperty;
                        final OCPropertySymbol.PropertyAttribute propertyAttribute = OCPropertySymbol.PropertyAttribute.GETTER;
                        final String s = ocPropertySymbol.getAttributeValue(propertyAttribute);
                        if (s != null) {
                            break Label_0041;
                        }
                        return false;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw c(ex2);
                    }
                    try {
                        final OCPropertySymbol ocPropertySymbol = generatedFromProperty;
                        final OCPropertySymbol.PropertyAttribute propertyAttribute = OCPropertySymbol.PropertyAttribute.GETTER;
                        final String s = ocPropertySymbol.getAttributeValue(propertyAttribute);
                        if (s != null) {
                            return true;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw c(ex3);
                    }
                }
                return false;
                try {
                    if (!this.isSetter()) {
                        return false;
                    }
                    final OCPropertySymbol ocPropertySymbol2 = generatedFromProperty;
                    final OCPropertySymbol.PropertyAttribute propertyAttribute2 = OCPropertySymbol.PropertyAttribute.SETTER;
                    final String s2 = ocPropertySymbol2.getAttributeValue(propertyAttribute2);
                    if (s2 != null) {
                        break Label_0077;
                    }
                    return false;
                }
                catch (IllegalArgumentException ex4) {
                    throw c(ex4);
                }
            }
            try {
                final OCPropertySymbol ocPropertySymbol2 = generatedFromProperty;
                final OCPropertySymbol.PropertyAttribute propertyAttribute2 = OCPropertySymbol.PropertyAttribute.SETTER;
                final String s2 = ocPropertySymbol2.getAttributeValue(propertyAttribute2);
                if (s2 != null) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw c(ex5);
            }
        }
        return false;
    }
    
    @NotNull
    @Override
    public OCTypeSubstitution getSubstitution() {
        OCTypeSubstitution mySubstitution;
        try {
            mySubstitution = this.mySubstitution;
            if (mySubstitution == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl", "getSubstitution"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return mySubstitution;
    }
    
    @NotNull
    @Override
    public List<SelectorPartSymbol> getSelectors() {
        List<SelectorPartSymbol> list = null;
        Label_0034: {
            try {
                if (this.mySubstitution == OCTypeSubstitution.ID) {
                    final List<SelectorPartSymbol> list2;
                    list = (list2 = this.mySelectors);
                    break Label_0034;
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            List<SelectorPartSymbol> list2;
            list = (list2 = (List<SelectorPartSymbol>)ContainerUtil.map((Collection)this.mySelectors, selectorPartSymbol -> new SelectorPartSymbolImpl((SelectorPartSymbolImpl)selectorPartSymbol, this.mySubstitution, new OCResolveContext())));
            try {
                if (list2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl", "getSelectors"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
        }
        return list;
    }
    
    @NotNull
    @Override
    public List<OCDeclaratorSymbol> getParameterSymbols() {
        List map;
        try {
            map = ContainerUtil.map((Collection)this.mySelectors, selectorPartSymbol -> this.mySubstitution.substitute(selectorPartSymbol.getParameter(), new OCResolveContext()));
            if (map == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl", "getParameterSymbols"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return (List<OCDeclaratorSymbol>)map;
    }
    
    @NotNull
    @Override
    public String getSignature() {
        String string = null;
        Label_0025: {
            StringBuilder sb;
            try {
                sb = new StringBuilder();
                if (this.myStatic) {
                    final String s = "+";
                    break Label_0025;
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            final String s = "-";
            try {
                string = sb.append(s).append(this.myName).toString();
                if (string == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl", "getSignature"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
        }
        return string;
    }
    
    @NotNull
    @Override
    public String getNameWithParent() {
        String string = null;
        Label_0025: {
            StringBuilder sb;
            try {
                sb = new StringBuilder();
                if (this.myStatic) {
                    final String s = "+";
                    break Label_0025;
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            final String s = "-";
            try {
                string = sb.append(s).append("[").append(this.myParent.getName()).append(" ").append(this.getName()).append("]").toString();
                if (string == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl", "getNameWithParent"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
        }
        return string;
    }
    
    @Nullable
    @Override
    public OCMethodSymbol getAssociatedSymbol() {
        return this.getAssociatedSymbol(null);
    }
    
    @Nullable
    @Override
    public OCMethodSymbol getAssociatedSymbol(@Nullable final PsiElement psiElement) {
        final OCClassSymbol ocClassSymbol = (OCClassSymbol)this.getParent().getAssociatedSymbol();
        try {
            if (ocClassSymbol == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        final CommonProcessors.FindFirstProcessor<OCMethodSymbol> findFirstProcessor = new CommonProcessors.FindFirstProcessor<OCMethodSymbol>() {
            protected boolean accept(final OCMethodSymbol ocMethodSymbol) {
                return ocMethodSymbol.isStatic() == OCMethodSymbolImpl.this.isStatic();
            }
        };
        ocClassSymbol.processCategories((Processor<? super OCClassSymbol>)(ocClassSymbol -> this.mySubstitution.substitute(ocClassSymbol, new OCResolveContext()).processMembers(this.getName(), (Class<? extends OCMemberSymbol>)OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMemberSymbol>)findFirstProcessor)), true, psiElement);
        return (OCMethodSymbol)findFirstProcessor.getFoundValue();
    }
    
    @Override
    public boolean processSameSymbols(final Processor<OCSymbol> processor) {
        return this.myParent.processMembersInAllCategories(this.myName, (Class<? extends OCMemberSymbol>)this.getClass(), (com.intellij.util.Processor<? super OCMemberSymbol>)(ocMethodSymbol -> {
            Label_0044: {
                try {
                    if (ocMethodSymbol.isStatic() != this.isStatic()) {
                        break Label_0044;
                    }
                    final Processor processor2 = processor;
                    final OCMethodSymbolImpl ocMethodSymbolImpl = this;
                    final OCTypeSubstitution ocTypeSubstitution = ocMethodSymbolImpl.mySubstitution;
                    final OCMethodSymbol ocMethodSymbol2 = ocMethodSymbol;
                    final OCResolveContext ocResolveContext = new OCResolveContext();
                    final OCMethodSymbol ocMethodSymbol3 = ocTypeSubstitution.substitute(ocMethodSymbol2, ocResolveContext);
                    final boolean b = processor2.process((Object)ocMethodSymbol3);
                    if (b) {
                        break Label_0044;
                    }
                    return false;
                }
                catch (IllegalArgumentException ex) {
                    throw c(ex);
                }
                try {
                    final Processor processor2 = processor;
                    final OCMethodSymbolImpl ocMethodSymbolImpl = this;
                    final OCTypeSubstitution ocTypeSubstitution = ocMethodSymbolImpl.mySubstitution;
                    final OCMethodSymbol ocMethodSymbol2 = ocMethodSymbol;
                    final OCResolveContext ocResolveContext = new OCResolveContext();
                    final OCMethodSymbol ocMethodSymbol3 = ocTypeSubstitution.substitute(ocMethodSymbol2, ocResolveContext);
                    final boolean b = processor2.process((Object)ocMethodSymbol3);
                    if (b) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw c(ex2);
                }
            }
            return false;
        }), false);
    }
    
    @NotNull
    @Override
    public OCType getEffectiveResolvedType() {
        OCType resolve;
        try {
            resolve = this.getReturnType().resolve((PsiFile)this.getContainingOCFile());
            if (resolve == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl", "getEffectiveResolvedType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return resolve;
    }
    
    @Override
    public OCType getEffectiveType() {
        return this.getReturnType();
    }
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
    
    public static class SelectorPartSymbolImpl implements SelectorPartSymbol
    {
        @Nullable
        private String selectorName;
        @Nullable
        private OCDeclaratorSymbol parameter;
        @NotNull
        private OCTypeSubstitution substitution;
        
        public SelectorPartSymbolImpl() {
            this.substitution = OCTypeSubstitution.ID;
        }
        
        public SelectorPartSymbolImpl(@Nullable final OCDeclaratorSymbol parameter, @Nullable final String selectorName) {
            this.substitution = OCTypeSubstitution.ID;
            this.parameter = parameter;
            this.selectorName = selectorName;
        }
        
        public SelectorPartSymbolImpl(@NotNull final SelectorPartSymbolImpl selectorPartSymbolImpl, @NotNull final OCTypeSubstitution ocTypeSubstitution, @NotNull final OCResolveContext ocResolveContext) {
            if (selectorPartSymbolImpl == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "origin", "com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl$SelectorPartSymbolImpl", "<init>"));
            }
            if (ocTypeSubstitution == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "substitution", "com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl$SelectorPartSymbolImpl", "<init>"));
            }
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl$SelectorPartSymbolImpl", "<init>"));
            }
            this(selectorPartSymbolImpl.parameter, selectorPartSymbolImpl.selectorName);
            this.substitution = OCTypeSubstitution.compose(selectorPartSymbolImpl.substitution, ocTypeSubstitution, ocResolveContext);
        }
        
        @Nullable
        @Override
        public OCDeclaratorSymbol getParameter() {
            return this.substitution.substitute(this.parameter, new OCResolveContext());
        }
        
        @Nullable
        @Override
        public OCDeclaratorSymbol getParameterWithoutSubstitution() {
            return this.parameter;
        }
        
        @NotNull
        @Override
        public OCTypeSubstitution getSubstitution() {
            OCTypeSubstitution substitution;
            try {
                substitution = this.substitution;
                if (substitution == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl$SelectorPartSymbolImpl", "getSubstitution"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return substitution;
        }
        
        @Nullable
        @Override
        public String getSelectorName() {
            return this.selectorName;
        }
        
        @Override
        public boolean equals(final Object o) {
            return DeepEqual.equalObjects(this, o);
        }
        
        @Override
        public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final SelectorPartSymbol selectorPartSymbol, @NotNull final SelectorPartSymbol selectorPartSymbol2) {
            try {
                if (comparator == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl$SelectorPartSymbolImpl", "deepEqualStep"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (selectorPartSymbol == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl$SelectorPartSymbolImpl", "deepEqualStep"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                if (selectorPartSymbol2 == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbolImpl$SelectorPartSymbolImpl", "deepEqualStep"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                if (!Comparing.equal(selectorPartSymbol.getSelectorName(), selectorPartSymbol2.getSelectorName())) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            try {
                if (!comparator.equalObjects(selectorPartSymbol.getSubstitution(), (DeepEqual.Equality<Object>)selectorPartSymbol2.getSubstitution())) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
            return comparator.equalObjects(selectorPartSymbol.getParameter(), (DeepEqual.Equality<Object>)selectorPartSymbol2.getParameter());
        }
        
        @Override
        public int hashCode() {
            int hashCode = 0;
            Label_0022: {
                try {
                    if (this.selectorName != null) {
                        hashCode = this.selectorName.hashCode();
                        break Label_0022;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                hashCode = 0;
            }
            final int n = hashCode;
            int n2;
            try {
                n2 = 31 * n;
                if (this.parameter != null) {
                    final int hashCode2 = this.parameter.hashCode();
                    return n2 + hashCode2;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            final int hashCode2 = 0;
            return n2 + hashCode2;
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
}
