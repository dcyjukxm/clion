// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.cpp;

import com.jetbrains.cidr.lang.symbols.symtable.OCGlobalProjectSymbolsCache;
import com.jetbrains.cidr.lang.util.OCCommonProcessors;
import com.intellij.openapi.util.Conditions;
import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.intellij.util.FilteringProcessor;
import java.util.Iterator;
import com.intellij.util.Processor;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.symbols.OCSymbolReference;
import com.jetbrains.cidr.lang.OCIcons;
import javax.swing.Icon;
import com.intellij.util.Consumer;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import java.util.List;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.openapi.util.Condition;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.jetbrains.cidr.lang.symbols.OCSymbolImpl;
import com.intellij.psi.PsiElement;

public abstract class OCSymbolWithQualifiedName<T extends PsiElement> extends OCSymbolImpl<T> implements OCSymbolWithParent<T, OCSymbolWithQualifiedName<T>>
{
    @NotNull
    private OCQualifiedName myQualifiedName;
    @Nullable
    protected OCSymbolWithQualifiedName myParent;
    @Nullable
    protected OCVisibility myVisibility;
    public static final Condition<OCSymbol> WITHOUT_QUALIFIER;
    
    public OCSymbolWithQualifiedName() {
    }
    
    public OCSymbolWithQualifiedName(@Nullable final Project project, @Nullable final VirtualFile virtualFile, final long n, @Nullable final OCSymbolWithQualifiedName myParent, @NotNull final OCQualifiedName myQualifiedName, @NotNull final List<String> list, @Nullable final OCVisibility myVisibility) {
        if (myQualifiedName == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "qualifiedName", "com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName", "<init>"));
        }
        if (list == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "attributes", "com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName", "<init>"));
        }
        super(project, virtualFile, n, myQualifiedName.getName(), list);
        this.myParent = myParent;
        this.myQualifiedName = myQualifiedName;
        this.myVisibility = myVisibility;
    }
    
    @Nullable
    public OCQualifiedName getQualifier() {
        return this.myQualifiedName.getQualifier();
    }
    
    @NotNull
    public OCQualifiedName getQualifiedName() {
        OCQualifiedName myQualifiedName;
        try {
            myQualifiedName = this.myQualifiedName;
            if (myQualifiedName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName", "getQualifiedName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return myQualifiedName;
    }
    
    @NotNull
    @Override
    public String getNameWithParent() {
        String myName = null;
        Label_0149: {
            String s5 = null;
            Label_0114: {
                Label_0064: {
                    String s = null;
                    Label_0029: {
                        try {
                            if (this.myQualifiedName.getQualifier() == null) {
                                break Label_0064;
                            }
                            final OCSymbolWithQualifiedName ocSymbolWithQualifiedName = this;
                            final OCQualifiedName ocQualifiedName = ocSymbolWithQualifiedName.myQualifiedName;
                            final boolean b = true;
                            s = ocQualifiedName.getCanonicalName(b);
                            if (s == null) {
                                break Label_0029;
                            }
                            return s;
                        }
                        catch (IllegalArgumentException ex) {
                            throw b(ex);
                        }
                        try {
                            final OCSymbolWithQualifiedName ocSymbolWithQualifiedName = this;
                            final OCQualifiedName ocQualifiedName = ocSymbolWithQualifiedName.myQualifiedName;
                            final boolean b = true;
                            s = ocQualifiedName.getCanonicalName(b);
                            if (s == null) {
                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName", "getNameWithParent"));
                            }
                        }
                        catch (IllegalArgumentException ex2) {
                            throw b(ex2);
                        }
                    }
                    return s;
                    try {
                        if (this.myParent == null) {
                            break Label_0149;
                        }
                        final StringBuilder sb = new StringBuilder();
                        final OCSymbolWithQualifiedName ocSymbolWithQualifiedName2 = this;
                        final OCSymbolWithQualifiedName<T> ocSymbolWithQualifiedName3 = ocSymbolWithQualifiedName2.myParent;
                        final String s2 = ocSymbolWithQualifiedName3.getName();
                        final StringBuilder sb2 = sb.append(s2);
                        final String s3 = "::";
                        final StringBuilder sb3 = sb2.append(s3);
                        final OCSymbolWithQualifiedName ocSymbolWithQualifiedName4 = this;
                        final String s4 = ocSymbolWithQualifiedName4.myName;
                        final StringBuilder sb4 = sb3.append(s4);
                        s5 = sb4.toString();
                        if (s5 == null) {
                            break Label_0114;
                        }
                        return s5;
                    }
                    catch (IllegalArgumentException ex3) {
                        throw b(ex3);
                    }
                }
                try {
                    final StringBuilder sb = new StringBuilder();
                    final OCSymbolWithQualifiedName ocSymbolWithQualifiedName2 = this;
                    final OCSymbolWithQualifiedName<T> ocSymbolWithQualifiedName3 = ocSymbolWithQualifiedName2.myParent;
                    final String s2 = ocSymbolWithQualifiedName3.getName();
                    final StringBuilder sb2 = sb.append(s2);
                    final String s3 = "::";
                    final StringBuilder sb3 = sb2.append(s3);
                    final OCSymbolWithQualifiedName ocSymbolWithQualifiedName4 = this;
                    final String s4 = ocSymbolWithQualifiedName4.myName;
                    final StringBuilder sb4 = sb3.append(s4);
                    s5 = sb4.toString();
                    if (s5 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName", "getNameWithParent"));
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw b(ex4);
                }
            }
            return s5;
            try {
                myName = this.myName;
                if (myName == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName", "getNameWithParent"));
                }
            }
            catch (IllegalArgumentException ex5) {
                throw b(ex5);
            }
        }
        return myName;
    }
    
    @Nullable
    public OCVisibility getVisibility() {
        return this.myVisibility;
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName", "deepEqualStep"));
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
        final OCSymbolWithQualifiedName ocSymbolWithQualifiedName = (OCSymbolWithQualifiedName)o;
        final OCSymbolWithQualifiedName ocSymbolWithQualifiedName2 = (OCSymbolWithQualifiedName)o2;
        try {
            if (ocSymbolWithQualifiedName.myVisibility != ocSymbolWithQualifiedName2.myVisibility) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw b(ex5);
        }
        try {
            if (!comparator.equalObjects((DeepEqual.Equality<Object>)ocSymbolWithQualifiedName.myQualifiedName, (DeepEqual.Equality<Object>)ocSymbolWithQualifiedName2.myQualifiedName)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw b(ex6);
        }
        return comparator.equalObjects(ocSymbolWithQualifiedName.myParent, (DeepEqual.Equality<Object>)ocSymbolWithQualifiedName2.myParent);
    }
    
    public boolean resolvedNamesEqual(final OCSymbolWithQualifiedName p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aload_1        
        //     2: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.equals:(Ljava/lang/Object;)Z
        //     5: ifeq            14
        //     8: iconst_1       
        //     9: ireturn        
        //    10: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    13: athrow         
        //    14: aload_1        
        //    15: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getQualifiedName:()Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //    18: astore_2       
        //    19: aload_0        
        //    20: getfield        com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.myQualifiedName:Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //    23: invokevirtual   com/jetbrains/cidr/lang/symbols/OCQualifiedName.getName:()Ljava/lang/String;
        //    26: aload_2        
        //    27: invokevirtual   com/jetbrains/cidr/lang/symbols/OCQualifiedName.getName:()Ljava/lang/String;
        //    30: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/String;Ljava/lang/String;)Z
        //    33: ifne            42
        //    36: iconst_0       
        //    37: ireturn        
        //    38: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    41: athrow         
        //    42: aload_0        
        //    43: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.isPredeclaration:()Z
        //    46: ifne            63
        //    49: aload_1        
        //    50: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.isPredeclaration:()Z
        //    53: ifeq            69
        //    56: goto            63
        //    59: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    62: athrow         
        //    63: iconst_1       
        //    64: ireturn        
        //    65: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    68: athrow         
        //    69: aload_1        
        //    70: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //    73: ifeq            93
        //    76: aload_1        
        //    77: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //    80: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.isSpecialization:()Z
        //    83: ifne            124
        //    86: goto            93
        //    89: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    92: athrow         
        //    93: aload_0        
        //    94: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //    97: ifeq            140
        //   100: goto            107
        //   103: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   106: athrow         
        //   107: aload_0        
        //   108: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   111: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.isSpecialization:()Z
        //   114: ifeq            140
        //   117: goto            124
        //   120: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   123: athrow         
        //   124: aload_0        
        //   125: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getResolvedQualifiedNameWithoutInlineNamespaces:()Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   128: aload_1        
        //   129: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getResolvedQualifiedNameWithoutInlineNamespaces:()Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   132: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //   135: ireturn        
        //   136: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   139: athrow         
        //   140: aload_0        
        //   141: iconst_0       
        //   142: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getResolvedQualifiedName:(Z)Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   145: aload_1        
        //   146: iconst_0       
        //   147: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getResolvedQualifiedName:(Z)Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   150: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //   153: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      10     10     14     Ljava/lang/IllegalArgumentException;
        //  19     38     38     42     Ljava/lang/IllegalArgumentException;
        //  42     56     59     63     Ljava/lang/IllegalArgumentException;
        //  49     65     65     69     Ljava/lang/IllegalArgumentException;
        //  69     86     89     93     Ljava/lang/IllegalArgumentException;
        //  76     100    103    107    Ljava/lang/IllegalArgumentException;
        //  93     117    120    124    Ljava/lang/IllegalArgumentException;
        //  107    136    136    140    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0093:
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
    
    @Nullable
    public OCQualifiedName getResolvedQualifiedNameWithoutInlineNamespaces() {
        return this.getResolvedQualifiedName(true, new OCResolveContext((PsiElement)this.getContainingOCFile()), true, false, true, true, true);
    }
    
    @Nullable
    public OCQualifiedName getResolvedQualifiedName() {
        return this.getResolvedQualifiedName(new OCResolveContext((PsiElement)this.getContainingOCFile()));
    }
    
    @Nullable
    public OCQualifiedName getResolvedQualifiedName(final boolean b, @NotNull final Consumer<OCSymbolWithQualifiedName> consumer) {
        try {
            if (consumer == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "observer", "com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName", "getResolvedQualifiedName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return this.getResolvedQualifiedName(b, new OCResolveContext((PsiElement)this.getContainingOCFile()), true, true, true, true, true, consumer);
    }
    
    @Nullable
    public OCQualifiedName getResolvedQualifiedName(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName", "getResolvedQualifiedName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return this.getResolvedQualifiedName(false, ocResolveContext, true, true, true, true, true);
    }
    
    @Nullable
    public OCQualifiedName getResolvedQualifiedName(final boolean b) {
        return this.getResolvedQualifiedName(false, b);
    }
    
    @Nullable
    public OCQualifiedName getResolvedQualifiedName(final boolean b, final boolean b2) {
        return this.getResolvedQualifiedName(b, new OCResolveContext((PsiElement)this.getContainingOCFile()), true, b2, true, true, true);
    }
    
    @Nullable
    public OCQualifiedName getResolvedQualifiedName(@NotNull final OCResolveContext ocResolveContext, final boolean b) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName", "getResolvedQualifiedName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return this.getResolvedQualifiedName(false, ocResolveContext, true, b, true, true, true);
    }
    
    @Nullable
    public OCQualifiedName getResolvedQualifiedName(final boolean b, @NotNull final OCResolveContext ocResolveContext, final boolean b2, final boolean b3, final boolean b4, final boolean b5, final boolean b6) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName", "getResolvedQualifiedName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return this.getResolvedQualifiedName(b, ocResolveContext, b2, b3, b4, b5, b6, null);
    }
    
    @Nullable
    public OCQualifiedName getResolvedQualifiedName(final boolean p0, @NotNull final OCResolveContext p1, final boolean p2, final boolean p3, final boolean p4, final boolean p5, final boolean p6, @Nullable final Consumer<OCSymbolWithQualifiedName> p7) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
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
        //    24: ldc             "com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getResolvedQualifiedName"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aconst_null    
        //    45: astore          9
        //    47: aconst_null    
        //    48: astore          10
        //    50: aload_0        
        //    51: getfield        com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.myQualifiedName:Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //    54: getstatic       com/jetbrains/cidr/lang/symbols/OCQualifiedName.GLOBAL:Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //    57: if_acmpne       69
        //    60: aload_0        
        //    61: getfield        com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.myQualifiedName:Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //    64: astore          9
        //    66: goto            370
        //    69: iload           7
        //    71: ifeq            86
        //    74: aload_2        
        //    75: aload_0        
        //    76: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getNonMemberOperatorParent:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //    79: goto            87
        //    82: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    85: athrow         
        //    86: aconst_null    
        //    87: astore          11
        //    89: aload_0        
        //    90: getfield        com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.myQualifiedName:Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //    93: invokevirtual   com/jetbrains/cidr/lang/symbols/OCQualifiedName.getQualifier:()Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //    96: astore          12
        //    98: aload           12
        //   100: ifnull          224
        //   103: aload           11
        //   105: ifnonnull       224
        //   108: goto            115
        //   111: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   114: athrow         
        //   115: aload           12
        //   117: aload_0        
        //   118: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getParent:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   121: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolReference$SymbolKindFilter.ONLY_NAMESPACE_LIKE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference$SymbolKindFilter;
        //   124: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReference.getGlobalReference:(Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference$SymbolFilter;)Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference$GlobalReference;
        //   127: astore          13
        //   129: aload_2        
        //   130: aload           13
        //   132: iconst_1       
        //   133: iload_3        
        //   134: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.resolveToSymbols:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;ZZ)Ljava/util/List;
        //   137: astore          14
        //   139: aload           14
        //   141: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   146: astore          15
        //   148: aload           15
        //   150: invokeinterface java/util/Iterator.hasNext:()Z
        //   155: ifeq            221
        //   158: aload           15
        //   160: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   165: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   168: astore          16
        //   170: aload           16
        //   172: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol;
        //   175: ifeq            218
        //   178: aload           16
        //   180: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol;
        //   183: astore          10
        //   185: aload           10
        //   187: iload_1        
        //   188: aload_2        
        //   189: iload_3        
        //   190: iload           4
        //   192: iconst_1       
        //   193: iload           6
        //   195: iload           7
        //   197: aload           8
        //   199: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getResolvedQualifiedName:(ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;ZZZZZLcom/intellij/util/Consumer;)Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   202: astore          17
        //   204: aload           17
        //   206: aload_0        
        //   207: getfield        com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.myName:Ljava/lang/String;
        //   210: invokestatic    com/jetbrains/cidr/lang/symbols/OCQualifiedName.with:(Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;Ljava/lang/String;)Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   213: astore          9
        //   215: goto            221
        //   218: goto            148
        //   221: goto            370
        //   224: aload           11
        //   226: ifnull          238
        //   229: aload           11
        //   231: goto            242
        //   234: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   237: athrow         
        //   238: aload_0        
        //   239: getfield        com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.myParent:Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   242: astore          10
        //   244: iload_1        
        //   245: ifeq            281
        //   248: aload           10
        //   250: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol;
        //   253: ifeq            281
        //   256: goto            263
        //   259: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   262: athrow         
        //   263: aload           10
        //   265: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol;
        //   268: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol.isInlineNamespace:()Z
        //   271: ifne            311
        //   274: goto            281
        //   277: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   280: athrow         
        //   281: aload           10
        //   283: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   286: ifeq            321
        //   289: goto            296
        //   292: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   295: athrow         
        //   296: aload           10
        //   298: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.isTransparentUnion:()Z
        //   301: ifeq            321
        //   304: goto            311
        //   307: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   310: athrow         
        //   311: aload           10
        //   313: getfield        com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.myParent:Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   316: astore          10
        //   318: goto            244
        //   321: aload           10
        //   323: ifnonnull       344
        //   326: getstatic       com/jetbrains/cidr/lang/symbols/OCQualifiedName.GLOBAL:Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   329: aload_0        
        //   330: getfield        com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.myQualifiedName:Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   333: invokevirtual   com/jetbrains/cidr/lang/symbols/OCQualifiedName.getName:()Ljava/lang/String;
        //   336: invokestatic    com/jetbrains/cidr/lang/symbols/OCQualifiedName.with:(Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;Ljava/lang/String;)Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   339: astore          9
        //   341: goto            370
        //   344: aload           10
        //   346: iload_1        
        //   347: aload_2        
        //   348: iload_3        
        //   349: iload           4
        //   351: iconst_1       
        //   352: iload           6
        //   354: iload           7
        //   356: aload           8
        //   358: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getResolvedQualifiedName:(ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;ZZZZZLcom/intellij/util/Consumer;)Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   361: aload_0        
        //   362: getfield        com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.myName:Ljava/lang/String;
        //   365: invokestatic    com/jetbrains/cidr/lang/symbols/OCQualifiedName.with:(Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;Ljava/lang/String;)Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   368: astore          9
        //   370: aload           8
        //   372: ifnull          390
        //   375: aload           8
        //   377: aload_0        
        //   378: invokeinterface com/intellij/util/Consumer.consume:(Ljava/lang/Object;)V
        //   383: goto            390
        //   386: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   389: athrow         
        //   390: aload_0        
        //   391: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //   394: ifeq            484
        //   397: iload           4
        //   399: ifeq            484
        //   402: goto            409
        //   405: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   408: athrow         
        //   409: getstatic       com/jetbrains/cidr/lang/types/OCType$Presentation.BEST:Lcom/jetbrains/cidr/lang/types/OCType$Presentation;
        //   412: aload_0        
        //   413: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //   416: iload           5
        //   418: iconst_0       
        //   419: iload           6
        //   421: aload_2        
        //   422: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.getTypeArguments:(Lcom/jetbrains/cidr/lang/types/OCType$Presentation;Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;ZZZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Ljava/util/List;
        //   425: astore          11
        //   427: aload           9
        //   429: ifnull          484
        //   432: aload           11
        //   434: invokeinterface java/util/List.isEmpty:()Z
        //   439: ifeq            468
        //   442: goto            449
        //   445: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   448: athrow         
        //   449: aload_0        
        //   450: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //   453: invokeinterface com/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol.getTemplateSpecialization:()Ljava/util/List;
        //   458: ifnull          484
        //   461: goto            468
        //   464: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   467: athrow         
        //   468: new             Lcom/jetbrains/cidr/lang/symbols/OCQualifiedNameWithArguments;
        //   471: dup            
        //   472: aload           9
        //   474: aload           11
        //   476: invokespecial   com/jetbrains/cidr/lang/symbols/OCQualifiedNameWithArguments.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;Ljava/util/List;)V
        //   479: areturn        
        //   480: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   483: athrow         
        //   484: aload           9
        //   486: areturn        
        //    Signature:
        //  (ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;ZZZZZLcom/intellij/util/Consumer<Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;>;)Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  69     82     82     86     Ljava/lang/IllegalArgumentException;
        //  98     108    111    115    Ljava/lang/IllegalArgumentException;
        //  224    234    234    238    Ljava/lang/IllegalArgumentException;
        //  244    256    259    263    Ljava/lang/IllegalArgumentException;
        //  248    274    277    281    Ljava/lang/IllegalArgumentException;
        //  263    289    292    296    Ljava/lang/IllegalArgumentException;
        //  281    304    307    311    Ljava/lang/IllegalArgumentException;
        //  370    383    386    390    Ljava/lang/IllegalArgumentException;
        //  390    402    405    409    Ljava/lang/IllegalArgumentException;
        //  427    442    445    449    Ljava/lang/IllegalArgumentException;
        //  432    461    464    468    Ljava/lang/IllegalArgumentException;
        //  449    480    480    484    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0263:
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
    
    public void setQualifier(final OCQualifiedName ocQualifiedName) {
        this.myQualifiedName = this.myQualifiedName.changeQualifier(ocQualifiedName);
    }
    
    public boolean isLocalInFile() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //     4: ifne            21
        //     7: aload_0        
        //     8: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //    11: ifeq            119
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    20: athrow         
        //    21: aload_0        
        //    22: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.isFriendOrStatic:()Z
        //    25: ifeq            119
        //    28: goto            35
        //    31: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    34: athrow         
        //    35: aload_0        
        //    36: getfield        com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.myFile:Lcom/intellij/openapi/vfs/VirtualFile;
        //    39: ifnull          119
        //    42: goto            49
        //    45: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    48: athrow         
        //    49: aload_0        
        //    50: getfield        com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.myFile:Lcom/intellij/openapi/vfs/VirtualFile;
        //    53: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getName:()Ljava/lang/String;
        //    56: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCFileImpl.isSourceCodeFile:(Ljava/lang/String;)Z
        //    59: ifeq            119
        //    62: goto            69
        //    65: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    68: athrow         
        //    69: aload_0        
        //    70: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getResolvedKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    73: astore_1       
        //    74: aload_1        
        //    75: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isGlobalVariable:()Z
        //    78: ifne            109
        //    81: aload_1        
        //    82: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isFunction:()Z
        //    85: ifeq            117
        //    88: goto            95
        //    91: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    94: athrow         
        //    95: aload_0        
        //    96: getfield        com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.myParent:Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //    99: ifnonnull       117
        //   102: goto            109
        //   105: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   108: athrow         
        //   109: iconst_1       
        //   110: goto            118
        //   113: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   116: athrow         
        //   117: iconst_0       
        //   118: ireturn        
        //   119: iconst_0       
        //   120: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      14     17     21     Ljava/lang/IllegalArgumentException;
        //  7      28     31     35     Ljava/lang/IllegalArgumentException;
        //  21     42     45     49     Ljava/lang/IllegalArgumentException;
        //  35     62     65     69     Ljava/lang/IllegalArgumentException;
        //  74     88     91     95     Ljava/lang/IllegalArgumentException;
        //  81     102    105    109    Ljava/lang/IllegalArgumentException;
        //  95     113    113    117    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0021:
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
    
    public boolean isConst() {
        return false;
    }
    
    public boolean isStatic() {
        return false;
    }
    
    public final boolean isFriendOrStatic() {
        Label_0021: {
            try {
                if (this.isStatic()) {
                    break Label_0021;
                }
                final OCSymbolWithQualifiedName ocSymbolWithQualifiedName = this;
                final boolean b = ocSymbolWithQualifiedName.isFriend();
                if (b) {
                    break Label_0021;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                final OCSymbolWithQualifiedName ocSymbolWithQualifiedName = this;
                final boolean b = ocSymbolWithQualifiedName.isFriend();
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        return false;
    }
    
    public boolean isFriend() {
        return false;
    }
    
    @Nullable
    @Override
    public Icon getBaseIcon() {
        return OCIcons.getVisibilityIcon(this.getVisibility(), super.getBaseIcon());
    }
    
    public static boolean isFriend(final OCSymbol ocSymbol) {
        Label_0024: {
            try {
                if (!(ocSymbol instanceof OCSymbolWithQualifiedName)) {
                    return false;
                }
                final OCSymbolWithQualifiedName ocSymbolWithQualifiedName = (OCSymbolWithQualifiedName)ocSymbol;
                final OCSymbolWithQualifiedName ocSymbolWithQualifiedName2 = ocSymbolWithQualifiedName;
                final boolean b = ocSymbolWithQualifiedName2.isFriend();
                if (b) {
                    break Label_0024;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                final OCSymbolWithQualifiedName ocSymbolWithQualifiedName = (OCSymbolWithQualifiedName)ocSymbol;
                final OCSymbolWithQualifiedName ocSymbolWithQualifiedName2 = ocSymbolWithQualifiedName;
                final boolean b = ocSymbolWithQualifiedName2.isFriend();
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        return false;
    }
    
    @Nullable
    public OCSymbolWithQualifiedName getResolvedOwner() {
        return this.getResolvedOwner(new OCResolveContext((PsiElement)this.getContainingOCFile()), true);
    }
    
    @Nullable
    public OCSymbolWithQualifiedName getResolvedOwner(@NotNull final OCResolveContext ocResolveContext, final boolean b) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName", "getResolvedOwner"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        if (b) {
            final OCStructSymbol nonMemberOperatorParent = ocResolveContext.getNonMemberOperatorParent(this);
            try {
                if (nonMemberOperatorParent != null) {
                    return nonMemberOperatorParent;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        final OCQualifiedName qualifier = this.myQualifiedName.getQualifier();
        if (qualifier != null) {
            this.getContainingOCFile();
            for (final OCSymbol ocSymbol : OCSymbolReference.getGlobalReference(qualifier, this.getParent(), OCSymbolReference.SymbolKindFilter.ONLY_NAMESPACE_LIKE).resolveToSymbols(true, true, true, ocResolveContext)) {
                if (ocSymbol instanceof OCNamespaceSymbol) {
                    final OCNamespaceSymbol ocNamespaceSymbol = (OCNamespaceSymbol)ocSymbol;
                    if (ocNamespaceSymbol.isPredeclaration()) {
                        continue;
                    }
                    final CommonProcessors.FindFirstProcessor findFirstProcessor = new CommonProcessors.FindFirstProcessor();
                    try {
                        ocNamespaceSymbol.processMembers(this.myName, (Processor<OCSymbol>)findFirstProcessor);
                        if (findFirstProcessor.isFound()) {
                            return ocNamespaceSymbol;
                        }
                        continue;
                    }
                    catch (IllegalArgumentException ex3) {
                        throw b(ex3);
                    }
                }
            }
            return null;
        }
        return this.myParent;
    }
    
    @Nullable
    @Override
    public OCSymbolWithQualifiedName getParent() {
        return this.myParent;
    }
    
    @Nullable
    @Override
    public OCSymbol getDefinitionSymbol() {
        if (this.isPredeclaration()) {
            final CommonProcessors.FindFirstProcessor findFirstProcessor = new CommonProcessors.FindFirstProcessor();
            this.processSameSymbols((Processor<OCSymbol>)new FilteringProcessor((Condition)OCSymbol.NON_PREDIFINITION_CONDITION, (Processor)findFirstProcessor));
            return (OCSymbol)findFirstProcessor.getFoundValue();
        }
        return this;
    }
    
    @Override
    public String getKindUppercase() {
        return this.getResolvedKind().getNameUppercase();
    }
    
    public OCSymbolKind getResolvedKind() {
        OCSymbolKind ocSymbolKind = this.getKind();
        try {
            if (!ocSymbolKind.isGlobalVariable() || this.getQualifier() == null) {
                return ocSymbolKind;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final Iterator<OCSymbol> iterator = OCSymbolReference.getGlobalReference(this.getQualifier(), this.getParent(), OCSymbolReference.SymbolKindFilter.ONLY_NAMESPACE_LIKE).resolveToSymbols(true, true, (PsiFile)this.getContainingOCFile()).iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getKind().isStructLike()) {
                ocSymbolKind = OCSymbolKind.STRUCT_FIELD;
                break;
            }
        }
        return ocSymbolKind;
    }
    
    @Override
    public boolean processSameSymbols(final Processor<OCSymbol> processor) {
        return this.processSameSymbols(processor, null);
    }
    
    public boolean processSameSymbols(final Processor<OCSymbol> processor, @Nullable final PsiFile psiFile) {
        final Class<? extends OCSymbolWithQualifiedName> class1 = this.getClass();
        try {
            if (!processor.process((Object)this)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (!this.isGlobal()) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        final Processor<OCSymbol> processor2 = (Processor<OCSymbol>)new Processor<OCSymbol>() {
            private OCQualifiedName myResolvedName;
            private OCType myResolvedType;
            
            public boolean process(final OCSymbol ocSymbol) {
                if (ocSymbol.equals(OCSymbolWithQualifiedName.this)) {
                    return true;
                }
                if (!((OCSymbolWithQualifiedName)ocSymbol).getClass().equals(class1)) {
                    return true;
                }
                if (!(ocSymbol instanceof OCSymbolWithQualifiedName)) {
                    return true;
                }
                if (this.myResolvedName == null) {
                    this.myResolvedName = OCSymbolWithQualifiedName.this.getResolvedQualifiedName(false);
                    if (this.myResolvedName == null) {
                        return false;
                    }
                }
                if (this.myResolvedType == null) {
                    this.myResolvedType = OCSymbolWithQualifiedName.this.getType().resolve((PsiFile)OCSymbolWithQualifiedName.this.getContainingOCFile());
                }
                return !OCSymbolWithQualifiedName.this.a((OCSymbolWithQualifiedName)ocSymbol, this.myResolvedName, this.myResolvedType, new OCResolveContext((PsiElement)OCSymbolWithQualifiedName.this.getContainingOCFile())) || processor.process((Object)ocSymbol);
            }
        };
        if (psiFile != null) {
            final OCQualifiedName resolvedQualifiedName = this.getResolvedQualifiedName();
            return resolvedQualifiedName == null || OCSymbolReference.getGlobalReference(resolvedQualifiedName).processPossibleSymbols((Processor<OCSymbol>)(ocSymbol -> {
                Label_0025: {
                    try {
                        if (ocSymbol.equals(this)) {
                            break Label_0025;
                        }
                        final Processor processor2 = processor;
                        final OCSymbol ocSymbol2 = ocSymbol;
                        final boolean b = processor2.process((Object)ocSymbol2);
                        if (b) {
                            break Label_0025;
                        }
                        return false;
                    }
                    catch (IllegalArgumentException ex) {
                        throw b(ex);
                    }
                    try {
                        final Processor processor2 = processor;
                        final OCSymbol ocSymbol2 = ocSymbol;
                        final boolean b = processor2.process((Object)ocSymbol2);
                        if (b) {
                            return true;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw b(ex2);
                    }
                }
                return false;
            }), psiFile);
        }
        if (this.myProject != null) {
            final OCCommonProcessors.OrderedProcessor orderedProcessor = new OCCommonProcessors.OrderedProcessor<OCSymbol>((com.intellij.util.Processor<? super Object>)processor2, (com.intellij.openapi.util.Condition<Object>[])new Condition[] { ocSymbol -> {
                    Label_0024: {
                        try {
                            if (virtualFile == null) {
                                return false;
                            }
                            final VirtualFile virtualFile2 = virtualFile;
                            final OCSymbol ocSymbol2 = ocSymbol;
                            final VirtualFile virtualFile3 = ocSymbol2.getContainingFile();
                            final boolean b = virtualFile2.equals(virtualFile3);
                            if (b) {
                                break Label_0024;
                            }
                            return false;
                        }
                        catch (IllegalArgumentException ex) {
                            throw b(ex);
                        }
                        try {
                            final VirtualFile virtualFile2 = virtualFile;
                            final OCSymbol ocSymbol2 = ocSymbol;
                            final VirtualFile virtualFile3 = ocSymbol2.getContainingFile();
                            final boolean b = virtualFile2.equals(virtualFile3);
                            if (b) {
                                return true;
                            }
                        }
                        catch (IllegalArgumentException ex2) {
                            throw b(ex2);
                        }
                    }
                    return false;
                }, Conditions.alwaysTrue() });
            OCGlobalProjectSymbolsCache.processTopLevelAndMemberSymbols(this.myProject, (Processor<OCSymbol>)orderedProcessor, this.myName);
            return orderedProcessor.finish();
        }
        return true;
    }
    
    @Override
    public boolean processAssociatedSymbols(final Processor<OCSymbol> processor) {
        Label_0039: {
            if (this.isPredeclaration()) {
                final OCSymbol definitionSymbol = this.getDefinitionSymbol();
                try {
                    if (definitionSymbol == null) {
                        break Label_0039;
                    }
                    final Processor<OCSymbol> processor2 = processor;
                    final OCSymbol ocSymbol = definitionSymbol;
                    final boolean b = processor2.process((Object)ocSymbol);
                    if (!b) {
                        return false;
                    }
                    break Label_0039;
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                try {
                    final Processor<OCSymbol> processor2 = processor;
                    final OCSymbol ocSymbol = definitionSymbol;
                    final boolean b = processor2.process((Object)ocSymbol);
                    if (!b) {
                        return false;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
            }
        }
        final OCCommonProcessors.OrderedProcessor<OCSymbol> orderedProcessor = new OCCommonProcessors.OrderedProcessor<OCSymbol>((com.intellij.util.Processor<? super OCSymbol>)(ocSymbol -> {
            Label_0022: {
                try {
                    if (ocSymbol == this) {
                        break Label_0022;
                    }
                    final Processor processor2 = processor;
                    final OCSymbolWithQualifiedName ocSymbolWithQualifiedName = (OCSymbolWithQualifiedName)ocSymbol;
                    final boolean b = processor2.process((Object)ocSymbolWithQualifiedName);
                    if (b) {
                        break Label_0022;
                    }
                    return false;
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                try {
                    final Processor processor2 = processor;
                    final OCSymbolWithQualifiedName ocSymbolWithQualifiedName = (OCSymbolWithQualifiedName)ocSymbol;
                    final boolean b = processor2.process((Object)ocSymbolWithQualifiedName);
                    if (b) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
            }
            return false;
        }), (com.intellij.openapi.util.Condition<OCSymbol>[])new Condition[] { p1 -> {
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
                //    18: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    21: athrow         
                //    22: aload_0        
                //    23: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getNameWithoutExtension:()Ljava/lang/String;
                //    26: aload_2        
                //    27: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getNameWithoutExtension:()Ljava/lang/String;
                //    30: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
                //    33: ifeq            51
                //    36: goto            43
                //    39: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    42: athrow         
                //    43: iconst_1       
                //    44: goto            52
                //    47: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    50: athrow         
                //    51: iconst_0       
                //    52: ireturn        
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                                
                //  -----  -----  -----  -----  ------------------------------------
                //  7      15     18     22     Ljava/lang/IllegalArgumentException;
                //  11     36     39     43     Ljava/lang/IllegalArgumentException;
                //  22     47     47     51     Ljava/lang/IllegalArgumentException;
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
            }, Conditions.alwaysTrue() });
        this.processPredeclarations((Processor<OCSymbol>)orderedProcessor);
        return orderedProcessor.finish();
    }
    
    @Override
    public boolean isSameSymbol(@Nullable final OCSymbol ocSymbol) {
        return this.isSameSymbol(ocSymbol, new OCResolveContext((PsiElement)this.getContainingOCFile()));
    }
    
    public boolean isSameSymbol(@Nullable final OCSymbol ocSymbol, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName", "isSameSymbol"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (ocSymbol instanceof OCSymbolWithQualifiedName) {
                return this.a((OCSymbolWithQualifiedName<T>)ocSymbol, this.getResolvedQualifiedName(false, ocResolveContext, true, false, true, true, true), this.getType().resolve(ocResolveContext), ocResolveContext);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return false;
    }
    
    public static boolean isSameSymbol(@Nullable final OCSymbolWithQualifiedName p0, @Nullable final OCSymbolWithQualifiedName p1, @NotNull final OCResolveContext p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
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
        //    24: ldc             "com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isSameSymbol"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: ifnonnull       59
        //    48: aload_1        
        //    49: ifnull          97
        //    52: goto            59
        //    55: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    58: athrow         
        //    59: aload_0        
        //    60: ifnull          105
        //    63: goto            70
        //    66: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    69: athrow         
        //    70: aload_1        
        //    71: ifnull          105
        //    74: goto            81
        //    77: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    80: athrow         
        //    81: aload_0        
        //    82: aload_1        
        //    83: aload_2        
        //    84: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.isSameSymbol:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //    87: ifeq            105
        //    90: goto            97
        //    93: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    96: athrow         
        //    97: iconst_1       
        //    98: goto            106
        //   101: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   104: athrow         
        //   105: iconst_0       
        //   106: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     52     55     59     Ljava/lang/IllegalArgumentException;
        //  48     63     66     70     Ljava/lang/IllegalArgumentException;
        //  59     74     77     81     Ljava/lang/IllegalArgumentException;
        //  70     90     93     97     Ljava/lang/IllegalArgumentException;
        //  81     101    101    105    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0059:
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
    
    private static boolean a(@Nullable final OCType p0, @Nullable final OCType p1, final boolean p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnull          15
        //     4: aload_1        
        //     5: ifnonnull       37
        //     8: goto            15
        //    11: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    14: athrow         
        //    15: aload_0        
        //    16: aload_1        
        //    17: if_acmpne       35
        //    20: goto            27
        //    23: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    26: athrow         
        //    27: iconst_1       
        //    28: goto            36
        //    31: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    34: athrow         
        //    35: iconst_0       
        //    36: ireturn        
        //    37: iload_2        
        //    38: ifeq            65
        //    41: aload_0        
        //    42: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getCVQualifiers:()Lcom/jetbrains/cidr/lang/types/CVQualifiers;
        //    45: aload_1        
        //    46: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getCVQualifiers:()Lcom/jetbrains/cidr/lang/types/CVQualifiers;
        //    49: if_acmpeq       65
        //    52: goto            59
        //    55: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    58: athrow         
        //    59: iconst_0       
        //    60: ireturn        
        //    61: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    64: athrow         
        //    65: aload_0        
        //    66: instanceof      Lcom/jetbrains/cidr/lang/types/OCReferenceType;
        //    69: ifne            86
        //    72: aload_1        
        //    73: instanceof      Lcom/jetbrains/cidr/lang/types/OCReferenceType;
        //    76: ifeq            92
        //    79: goto            86
        //    82: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    85: athrow         
        //    86: iconst_1       
        //    87: ireturn        
        //    88: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    91: athrow         
        //    92: aload_0        
        //    93: instanceof      Lcom/jetbrains/cidr/lang/types/OCUnknownType;
        //    96: ifne            113
        //    99: aload_1        
        //   100: instanceof      Lcom/jetbrains/cidr/lang/types/OCUnknownType;
        //   103: ifeq            119
        //   106: goto            113
        //   109: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   112: athrow         
        //   113: iconst_0       
        //   114: ireturn        
        //   115: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   118: athrow         
        //   119: aload_0        
        //   120: instanceof      Lcom/jetbrains/cidr/lang/types/OCMagicType;
        //   123: ifne            140
        //   126: aload_1        
        //   127: instanceof      Lcom/jetbrains/cidr/lang/types/OCMagicType;
        //   130: ifeq            146
        //   133: goto            140
        //   136: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   139: athrow         
        //   140: iconst_1       
        //   141: ireturn        
        //   142: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   145: athrow         
        //   146: aload_0        
        //   147: instanceof      Lcom/jetbrains/cidr/lang/types/OCVoidType;
        //   150: ifne            167
        //   153: aload_1        
        //   154: instanceof      Lcom/jetbrains/cidr/lang/types/OCVoidType;
        //   157: ifeq            205
        //   160: goto            167
        //   163: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   166: athrow         
        //   167: aload_0        
        //   168: instanceof      Lcom/jetbrains/cidr/lang/types/OCVoidType;
        //   171: ifeq            203
        //   174: goto            181
        //   177: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   180: athrow         
        //   181: aload_1        
        //   182: instanceof      Lcom/jetbrains/cidr/lang/types/OCVoidType;
        //   185: ifeq            203
        //   188: goto            195
        //   191: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   194: athrow         
        //   195: iconst_1       
        //   196: goto            204
        //   199: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   202: athrow         
        //   203: iconst_0       
        //   204: ireturn        
        //   205: aload_0        
        //   206: instanceof      Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   209: ifne            226
        //   212: aload_1        
        //   213: instanceof      Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   216: ifeq            264
        //   219: goto            226
        //   222: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   225: athrow         
        //   226: aload_0        
        //   227: instanceof      Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   230: ifeq            262
        //   233: goto            240
        //   236: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   239: athrow         
        //   240: aload_1        
        //   241: instanceof      Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   244: ifeq            262
        //   247: goto            254
        //   250: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   253: athrow         
        //   254: iconst_1       
        //   255: goto            263
        //   258: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   261: athrow         
        //   262: iconst_0       
        //   263: ireturn        
        //   264: aload_0        
        //   265: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   268: ifne            285
        //   271: aload_1        
        //   272: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   275: ifeq            323
        //   278: goto            285
        //   281: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   284: athrow         
        //   285: aload_0        
        //   286: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   289: ifeq            321
        //   292: goto            299
        //   295: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   298: athrow         
        //   299: aload_1        
        //   300: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   303: ifeq            321
        //   306: goto            313
        //   309: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   312: athrow         
        //   313: iconst_1       
        //   314: goto            322
        //   317: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   320: athrow         
        //   321: iconst_0       
        //   322: ireturn        
        //   323: aload_0        
        //   324: instanceof      Lcom/jetbrains/cidr/lang/types/OCNumericType;
        //   327: ifne            344
        //   330: aload_1        
        //   331: instanceof      Lcom/jetbrains/cidr/lang/types/OCNumericType;
        //   334: ifeq            406
        //   337: goto            344
        //   340: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   343: athrow         
        //   344: aload_0        
        //   345: instanceof      Lcom/jetbrains/cidr/lang/types/OCNumericType;
        //   348: ifeq            404
        //   351: goto            358
        //   354: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   357: athrow         
        //   358: aload_1        
        //   359: instanceof      Lcom/jetbrains/cidr/lang/types/OCNumericType;
        //   362: ifeq            404
        //   365: goto            372
        //   368: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   371: athrow         
        //   372: aload_0        
        //   373: checkcast       Lcom/jetbrains/cidr/lang/types/OCNumericType;
        //   376: invokevirtual   com/jetbrains/cidr/lang/types/OCNumericType.getCTypeId:()Lcom/jetbrains/cidr/lang/types/CTypeId;
        //   379: aload_1        
        //   380: checkcast       Lcom/jetbrains/cidr/lang/types/OCNumericType;
        //   383: invokevirtual   com/jetbrains/cidr/lang/types/OCNumericType.getCTypeId:()Lcom/jetbrains/cidr/lang/types/CTypeId;
        //   386: if_acmpne       404
        //   389: goto            396
        //   392: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   395: athrow         
        //   396: iconst_1       
        //   397: goto            405
        //   400: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   403: athrow         
        //   404: iconst_0       
        //   405: ireturn        
        //   406: aload_0        
        //   407: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   410: ifne            427
        //   413: aload_1        
        //   414: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   417: ifeq            593
        //   420: goto            427
        //   423: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   426: athrow         
        //   427: aload_0        
        //   428: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   431: ifeq            455
        //   434: goto            441
        //   437: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   440: athrow         
        //   441: aload_1        
        //   442: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   445: ifne            461
        //   448: goto            455
        //   451: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   454: athrow         
        //   455: iconst_0       
        //   456: ireturn        
        //   457: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   460: athrow         
        //   461: aload_0        
        //   462: checkcast       Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   465: astore_3       
        //   466: aload_1        
        //   467: checkcast       Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   470: astore          4
        //   472: aload_3        
        //   473: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.isVararg:()Z
        //   476: aload           4
        //   478: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.isVararg:()Z
        //   481: if_icmpeq       490
        //   484: iconst_0       
        //   485: ireturn        
        //   486: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   489: athrow         
        //   490: aload_3        
        //   491: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.getParameterTypes:()Ljava/util/List;
        //   494: astore          5
        //   496: aload           4
        //   498: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.getParameterTypes:()Ljava/util/List;
        //   501: astore          6
        //   503: aload           5
        //   505: invokeinterface java/util/List.size:()I
        //   510: aload           6
        //   512: invokeinterface java/util/List.size:()I
        //   517: if_icmpeq       526
        //   520: iconst_0       
        //   521: ireturn        
        //   522: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   525: athrow         
        //   526: iconst_0       
        //   527: istore          7
        //   529: iload           7
        //   531: aload           5
        //   533: invokeinterface java/util/List.size:()I
        //   538: if_icmpge       591
        //   541: aload           5
        //   543: iload           7
        //   545: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   550: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   553: aload           6
        //   555: iload           7
        //   557: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   562: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   565: iconst_0       
        //   566: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.a:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Z)Z
        //   569: ifne            585
        //   572: goto            579
        //   575: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   578: athrow         
        //   579: iconst_0       
        //   580: ireturn        
        //   581: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   584: athrow         
        //   585: iinc            7, 1
        //   588: goto            529
        //   591: iconst_1       
        //   592: ireturn        
        //   593: aload_0        
        //   594: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   597: ifeq            685
        //   600: aload_1        
        //   601: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   604: ifne            620
        //   607: goto            614
        //   610: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   613: athrow         
        //   614: iconst_0       
        //   615: ireturn        
        //   616: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   619: athrow         
        //   620: aload_0        
        //   621: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   624: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.isRvalueRef:()Z
        //   627: aload_1        
        //   628: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   631: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.isRvalueRef:()Z
        //   634: if_icmpeq       643
        //   637: iconst_0       
        //   638: ireturn        
        //   639: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   642: athrow         
        //   643: aload_0        
        //   644: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   647: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.isReferenceToConst:()Z
        //   650: aload_1        
        //   651: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   654: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.isReferenceToConst:()Z
        //   657: if_icmpeq       666
        //   660: iconst_0       
        //   661: ireturn        
        //   662: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   665: athrow         
        //   666: aload_0        
        //   667: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   670: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   673: aload_1        
        //   674: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   677: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   680: iload_2        
        //   681: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.a:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Z)Z
        //   684: ireturn        
        //   685: aload_0        
        //   686: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   689: ifeq            731
        //   692: aload_1        
        //   693: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   696: ifne            712
        //   699: goto            706
        //   702: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   705: athrow         
        //   706: iconst_0       
        //   707: ireturn        
        //   708: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   711: athrow         
        //   712: aload_0        
        //   713: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   716: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   719: aload_1        
        //   720: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   723: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   726: iload_2        
        //   727: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.a:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Z)Z
        //   730: ireturn        
        //   731: iconst_1       
        //   732: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      8      11     15     Ljava/lang/IllegalArgumentException;
        //  4      20     23     27     Ljava/lang/IllegalArgumentException;
        //  15     31     31     35     Ljava/lang/IllegalArgumentException;
        //  37     52     55     59     Ljava/lang/IllegalArgumentException;
        //  41     61     61     65     Ljava/lang/IllegalArgumentException;
        //  65     79     82     86     Ljava/lang/IllegalArgumentException;
        //  72     88     88     92     Ljava/lang/IllegalArgumentException;
        //  92     106    109    113    Ljava/lang/IllegalArgumentException;
        //  99     115    115    119    Ljava/lang/IllegalArgumentException;
        //  119    133    136    140    Ljava/lang/IllegalArgumentException;
        //  126    142    142    146    Ljava/lang/IllegalArgumentException;
        //  146    160    163    167    Ljava/lang/IllegalArgumentException;
        //  153    174    177    181    Ljava/lang/IllegalArgumentException;
        //  167    188    191    195    Ljava/lang/IllegalArgumentException;
        //  181    199    199    203    Ljava/lang/IllegalArgumentException;
        //  205    219    222    226    Ljava/lang/IllegalArgumentException;
        //  212    233    236    240    Ljava/lang/IllegalArgumentException;
        //  226    247    250    254    Ljava/lang/IllegalArgumentException;
        //  240    258    258    262    Ljava/lang/IllegalArgumentException;
        //  264    278    281    285    Ljava/lang/IllegalArgumentException;
        //  271    292    295    299    Ljava/lang/IllegalArgumentException;
        //  285    306    309    313    Ljava/lang/IllegalArgumentException;
        //  299    317    317    321    Ljava/lang/IllegalArgumentException;
        //  323    337    340    344    Ljava/lang/IllegalArgumentException;
        //  330    351    354    358    Ljava/lang/IllegalArgumentException;
        //  344    365    368    372    Ljava/lang/IllegalArgumentException;
        //  358    389    392    396    Ljava/lang/IllegalArgumentException;
        //  372    400    400    404    Ljava/lang/IllegalArgumentException;
        //  406    420    423    427    Ljava/lang/IllegalArgumentException;
        //  413    434    437    441    Ljava/lang/IllegalArgumentException;
        //  427    448    451    455    Ljava/lang/IllegalArgumentException;
        //  441    457    457    461    Ljava/lang/IllegalArgumentException;
        //  472    486    486    490    Ljava/lang/IllegalArgumentException;
        //  503    522    522    526    Ljava/lang/IllegalArgumentException;
        //  529    572    575    579    Ljava/lang/IllegalArgumentException;
        //  541    581    581    585    Ljava/lang/IllegalArgumentException;
        //  593    607    610    614    Ljava/lang/IllegalArgumentException;
        //  600    616    616    620    Ljava/lang/IllegalArgumentException;
        //  620    639    639    643    Ljava/lang/IllegalArgumentException;
        //  643    662    662    666    Ljava/lang/IllegalArgumentException;
        //  685    699    702    706    Ljava/lang/IllegalArgumentException;
        //  692    708    708    712    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0015:
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
    
    private boolean a(final OCSymbolWithQualifiedName p0, @Nullable final OCQualifiedName p1, final OCType p2, @NotNull final OCResolveContext p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload           4
        //     2: ifnonnull       45
        //     5: new             Ljava/lang/IllegalArgumentException;
        //     8: dup            
        //     9: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    11: ldc             3
        //    13: anewarray       Ljava/lang/Object;
        //    16: dup            
        //    17: ldc             0
        //    19: ldc             "context"
        //    21: aastore        
        //    22: dup            
        //    23: ldc             1
        //    25: ldc             "com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName"
        //    27: aastore        
        //    28: dup            
        //    29: ldc             2
        //    31: ldc             "isSameSymbol"
        //    33: aastore        
        //    34: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    37: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    40: athrow         
        //    41: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    44: athrow         
        //    45: aload_0        
        //    46: aload_1        
        //    47: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.equals:(Ljava/lang/Object;)Z
        //    50: ifeq            59
        //    53: iconst_1       
        //    54: ireturn        
        //    55: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    58: athrow         
        //    59: aload_0        
        //    60: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    63: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isLocal:()Z
        //    66: ifne            86
        //    69: aload_1        
        //    70: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    73: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isLocal:()Z
        //    76: ifeq            92
        //    79: goto            86
        //    82: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    85: athrow         
        //    86: iconst_0       
        //    87: ireturn        
        //    88: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    91: athrow         
        //    92: aload_2        
        //    93: ifnonnull       102
        //    96: iconst_0       
        //    97: ireturn        
        //    98: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   101: athrow         
        //   102: aload_0        
        //   103: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.isLocalInFile:()Z
        //   106: ifeq            150
        //   109: aload_1        
        //   110: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.isLocalInFile:()Z
        //   113: ifeq            150
        //   116: goto            123
        //   119: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   122: athrow         
        //   123: aload_0        
        //   124: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getContainingFile:()Lcom/intellij/openapi/vfs/VirtualFile;
        //   127: aload_1        
        //   128: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getContainingFile:()Lcom/intellij/openapi/vfs/VirtualFile;
        //   131: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //   134: ifne            150
        //   137: goto            144
        //   140: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   143: athrow         
        //   144: iconst_0       
        //   145: ireturn        
        //   146: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   149: athrow         
        //   150: aload_0        
        //   151: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.isFriend:()Z
        //   154: ifne            171
        //   157: aload_1        
        //   158: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.isFriend:()Z
        //   161: ifeq            179
        //   164: goto            171
        //   167: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   170: athrow         
        //   171: iconst_1       
        //   172: goto            180
        //   175: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   178: athrow         
        //   179: iconst_0       
        //   180: istore          5
        //   182: iload           5
        //   184: ifeq            214
        //   187: aload_0        
        //   188: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getName:()Ljava/lang/String;
        //   191: aload_1        
        //   192: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getName:()Ljava/lang/String;
        //   195: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   198: ifne            214
        //   201: goto            208
        //   204: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   207: athrow         
        //   208: iconst_0       
        //   209: ireturn        
        //   210: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   213: athrow         
        //   214: aload_1        
        //   215: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   218: ifeq            318
        //   221: aload_0        
        //   222: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   225: ifeq            316
        //   228: goto            235
        //   231: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   234: athrow         
        //   235: aload_1        
        //   236: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   239: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isFriend:()Z
        //   242: ifeq            289
        //   245: goto            252
        //   248: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   251: athrow         
        //   252: aload_1        
        //   253: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.isPredeclaration:()Z
        //   256: ifeq            289
        //   259: goto            266
        //   262: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   265: athrow         
        //   266: aload_1        
        //   267: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   270: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isCppOperator:()Z
        //   273: ifeq            289
        //   276: goto            283
        //   279: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   282: athrow         
        //   283: iconst_0       
        //   284: ireturn        
        //   285: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   288: athrow         
        //   289: aload_0        
        //   290: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   293: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getTypeWithoutSubstitution:()Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   296: aload_1        
        //   297: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   300: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getTypeWithoutSubstitution:()Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   303: iconst_1       
        //   304: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.a:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Z)Z
        //   307: ifne            318
        //   310: iconst_0       
        //   311: ireturn        
        //   312: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   315: athrow         
        //   316: iconst_0       
        //   317: ireturn        
        //   318: iload           5
        //   320: ifne            364
        //   323: aload_2        
        //   324: aload_1        
        //   325: iconst_0       
        //   326: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   329: dup            
        //   330: aload_1        
        //   331: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   334: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
        //   337: iconst_1       
        //   338: iconst_0       
        //   339: iconst_1       
        //   340: iconst_1       
        //   341: iconst_1       
        //   342: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getResolvedQualifiedName:(ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;ZZZZZ)Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   345: invokevirtual   com/jetbrains/cidr/lang/symbols/OCQualifiedName.equals:(Ljava/lang/Object;)Z
        //   348: ifne            364
        //   351: goto            358
        //   354: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   357: athrow         
        //   358: iconst_0       
        //   359: ireturn        
        //   360: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   363: athrow         
        //   364: aload_1        
        //   365: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   368: ifeq            521
        //   371: aload_1        
        //   372: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   375: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getParameterSymbols:()Ljava/util/List;
        //   378: invokeinterface java/util/List.size:()I
        //   383: aload_0        
        //   384: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   387: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getParameterSymbols:()Ljava/util/List;
        //   390: invokeinterface java/util/List.size:()I
        //   395: if_icmpeq       455
        //   398: goto            405
        //   401: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   404: athrow         
        //   405: aload_1        
        //   406: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   409: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   412: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.hasNoParameters:()Z
        //   415: ifeq            453
        //   418: goto            425
        //   421: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   424: athrow         
        //   425: aload_0        
        //   426: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   429: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   432: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.hasNoParameters:()Z
        //   435: ifeq            453
        //   438: goto            445
        //   441: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   444: athrow         
        //   445: iconst_1       
        //   446: goto            454
        //   449: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   452: athrow         
        //   453: iconst_0       
        //   454: ireturn        
        //   455: aload_1        
        //   456: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   459: astore          6
        //   461: new             Lcom/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor;
        //   464: dup            
        //   465: aload           6
        //   467: iconst_1       
        //   468: aload           4
        //   470: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.<init>:(Lcom/jetbrains/cidr/lang/types/OCType;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)V
        //   473: astore          7
        //   475: aload_3        
        //   476: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   479: ifeq            508
        //   482: aload           6
        //   484: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   487: ifeq            508
        //   490: goto            497
        //   493: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   496: athrow         
        //   497: aload           7
        //   499: aload_3        
        //   500: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.isFunctionSignatureEqual:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   503: ireturn        
        //   504: invokestatic    com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   507: athrow         
        //   508: aload_3        
        //   509: aload           7
        //   511: invokevirtual   com/jetbrains/cidr/lang/types/OCType.accept:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeVisitor;)Ljava/lang/Object;
        //   514: checkcast       Ljava/lang/Boolean;
        //   517: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   520: ireturn        
        //   521: iconst_1       
        //   522: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      41     41     45     Ljava/lang/IllegalArgumentException;
        //  45     55     55     59     Ljava/lang/IllegalArgumentException;
        //  59     79     82     86     Ljava/lang/IllegalArgumentException;
        //  69     88     88     92     Ljava/lang/IllegalArgumentException;
        //  92     98     98     102    Ljava/lang/IllegalArgumentException;
        //  102    116    119    123    Ljava/lang/IllegalArgumentException;
        //  109    137    140    144    Ljava/lang/IllegalArgumentException;
        //  123    146    146    150    Ljava/lang/IllegalArgumentException;
        //  150    164    167    171    Ljava/lang/IllegalArgumentException;
        //  157    175    175    179    Ljava/lang/IllegalArgumentException;
        //  182    201    204    208    Ljava/lang/IllegalArgumentException;
        //  187    210    210    214    Ljava/lang/IllegalArgumentException;
        //  214    228    231    235    Ljava/lang/IllegalArgumentException;
        //  221    245    248    252    Ljava/lang/IllegalArgumentException;
        //  235    259    262    266    Ljava/lang/IllegalArgumentException;
        //  252    276    279    283    Ljava/lang/IllegalArgumentException;
        //  266    285    285    289    Ljava/lang/IllegalArgumentException;
        //  289    312    312    316    Ljava/lang/IllegalArgumentException;
        //  318    351    354    358    Ljava/lang/IllegalArgumentException;
        //  323    360    360    364    Ljava/lang/IllegalArgumentException;
        //  364    398    401    405    Ljava/lang/IllegalArgumentException;
        //  371    418    421    425    Ljava/lang/IllegalArgumentException;
        //  405    438    441    445    Ljava/lang/IllegalArgumentException;
        //  425    449    449    453    Ljava/lang/IllegalArgumentException;
        //  475    490    493    497    Ljava/lang/IllegalArgumentException;
        //  482    504    504    508    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0123:
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
    
    @Nullable
    @Override
    public String getLocationString() {
        Label_0086: {
            if (this.isGlobal()) {
                final OCQualifiedName resolvedQualifiedName = this.getResolvedQualifiedName(false, new OCResolveContext((PsiElement)this.getContainingOCFile()), true, false, true, true, false);
                if (resolvedQualifiedName != null) {
                    OCQualifiedName ocQualifiedName = resolvedQualifiedName.getQualifier();
                    Label_0067: {
                        try {
                            if (ocQualifiedName == null) {
                                break Label_0086;
                            }
                            if (ocQualifiedName.getSuperQualifier() != OCQualifiedName.GLOBAL.getName()) {
                                break Label_0067;
                            }
                        }
                        catch (IllegalArgumentException ex) {
                            throw b(ex);
                        }
                        ocQualifiedName = ocQualifiedName.dropSuperQualifier();
                    }
                    final String canonicalName = ocQualifiedName.getCanonicalName(true);
                    try {
                        if (!canonicalName.isEmpty()) {
                            return canonicalName;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw b(ex2);
                    }
                }
            }
            try {
                if (this.myFile == null) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
        }
        return this.myFile.getName();
    }
    
    @Override
    public OCSymbol getAssociatedSymbol() {
        final CommonProcessors.FindFirstProcessor findFirstProcessor = new CommonProcessors.FindFirstProcessor();
        this.processAssociatedSymbols((Processor<OCSymbol>)findFirstProcessor);
        return (OCSymbol)findFirstProcessor.getFoundValue();
    }
    
    static {
        WITHOUT_QUALIFIER = (ocSymbol -> {
            Label_0024: {
                try {
                    if (!(ocSymbol instanceof OCSymbolWithQualifiedName)) {
                        break Label_0024;
                    }
                    final OCSymbolWithQualifiedName ocSymbolWithQualifiedName = (OCSymbolWithQualifiedName)ocSymbol;
                    final OCSymbolWithQualifiedName ocSymbolWithQualifiedName2 = ocSymbolWithQualifiedName;
                    final OCQualifiedName ocQualifiedName = ocSymbolWithQualifiedName2.getQualifier();
                    if (ocQualifiedName == null) {
                        break Label_0024;
                    }
                    return false;
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                try {
                    final OCSymbolWithQualifiedName ocSymbolWithQualifiedName = (OCSymbolWithQualifiedName)ocSymbol;
                    final OCSymbolWithQualifiedName ocSymbolWithQualifiedName2 = ocSymbolWithQualifiedName;
                    final OCQualifiedName ocQualifiedName = ocSymbolWithQualifiedName2.getQualifier();
                    if (ocQualifiedName == null) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
            }
            return false;
        });
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
