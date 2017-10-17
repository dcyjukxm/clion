// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import com.jetbrains.cidr.lang.preprocessor.OCInclusionContext;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import com.jetbrains.cidr.lang.types.visitors.OCTypeVisitor;
import java.util.Collections;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public class OCFunctionType extends OCType
{
    private OCType myReturnType;
    private List<OCType> myParameterTypes;
    private List<String> myParameterNames;
    
    public OCFunctionType() {
    }
    
    public OCFunctionType(@NotNull final OCType ocType, @NotNull final List<OCType> list) {
        if (ocType == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "returnType", "com/jetbrains/cidr/lang/types/OCFunctionType", "<init>"));
        }
        if (list == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameterTypes", "com/jetbrains/cidr/lang/types/OCFunctionType", "<init>"));
        }
        this(ocType, list, null, false, false, false, false);
    }
    
    public OCFunctionType(@NotNull final OCType ocType, @NotNull final List<OCType> list, @Nullable final List<String> list2) {
        if (ocType == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "returnType", "com/jetbrains/cidr/lang/types/OCFunctionType", "<init>"));
        }
        if (list == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameterTypes", "com/jetbrains/cidr/lang/types/OCFunctionType", "<init>"));
        }
        this(ocType, list, list2, false, false, false, false);
    }
    
    public OCFunctionType(@NotNull final OCType p0, @NotNull final List<OCType> p1, @Nullable final List<String> p2, final boolean p3, final boolean p4, final boolean p5, final boolean p6) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnonnull       40
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "returnType"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/types/OCFunctionType"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "<init>"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: aload_2        
        //    41: ifnonnull       80
        //    44: new             Ljava/lang/IllegalArgumentException;
        //    47: dup            
        //    48: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    50: ldc             3
        //    52: anewarray       Ljava/lang/Object;
        //    55: dup            
        //    56: ldc             0
        //    58: ldc             "parameterTypes"
        //    60: aastore        
        //    61: dup            
        //    62: ldc             1
        //    64: ldc             "com/jetbrains/cidr/lang/types/OCFunctionType"
        //    66: aastore        
        //    67: dup            
        //    68: ldc             2
        //    70: ldc             "<init>"
        //    72: aastore        
        //    73: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    76: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    79: athrow         
        //    80: aload_0        
        //    81: iload           4
        //    83: iload           5
        //    85: invokespecial   com/jetbrains/cidr/lang/types/OCType.<init>:(ZZ)V
        //    88: aload_0        
        //    89: aload_1        
        //    90: putfield        com/jetbrains/cidr/lang/types/OCFunctionType.myReturnType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    93: aload_0        
        //    94: aload_2        
        //    95: putfield        com/jetbrains/cidr/lang/types/OCFunctionType.myParameterTypes:Ljava/util/List;
        //    98: aload_0        
        //    99: aload_3        
        //   100: putfield        com/jetbrains/cidr/lang/types/OCFunctionType.myParameterNames:Ljava/util/List;
        //   103: iload           6
        //   105: ifeq            121
        //   108: aload_0        
        //   109: aload_0        
        //   110: getfield        com/jetbrains/cidr/lang/types/OCFunctionType.myTypeAttributes:I
        //   113: bipush          32
        //   115: invokestatic    com/jetbrains/cidr/lang/types/OCFunctionType.attachAttribute:(II)I
        //   118: putfield        com/jetbrains/cidr/lang/types/OCFunctionType.myTypeAttributes:I
        //   121: iload           7
        //   123: ifeq            146
        //   126: aload_0        
        //   127: aload_0        
        //   128: getfield        com/jetbrains/cidr/lang/types/OCFunctionType.myTypeAttributes:I
        //   131: bipush          64
        //   133: invokestatic    com/jetbrains/cidr/lang/types/OCFunctionType.attachAttribute:(II)I
        //   136: putfield        com/jetbrains/cidr/lang/types/OCFunctionType.myTypeAttributes:I
        //   139: goto            146
        //   142: invokestatic    com/jetbrains/cidr/lang/types/OCFunctionType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   145: athrow         
        //   146: getstatic       com/jetbrains/cidr/lang/types/OCFunctionType.$assertionsDisabled:Z
        //   149: ifne            206
        //   152: aload_0        
        //   153: getfield        com/jetbrains/cidr/lang/types/OCFunctionType.myParameterNames:Ljava/util/List;
        //   156: ifnull          206
        //   159: goto            166
        //   162: invokestatic    com/jetbrains/cidr/lang/types/OCFunctionType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   165: athrow         
        //   166: aload_0        
        //   167: getfield        com/jetbrains/cidr/lang/types/OCFunctionType.myParameterNames:Ljava/util/List;
        //   170: invokeinterface java/util/List.size:()I
        //   175: aload_0        
        //   176: getfield        com/jetbrains/cidr/lang/types/OCFunctionType.myParameterTypes:Ljava/util/List;
        //   179: invokeinterface java/util/List.size:()I
        //   184: if_icmpeq       206
        //   187: goto            194
        //   190: invokestatic    com/jetbrains/cidr/lang/types/OCFunctionType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   193: athrow         
        //   194: new             Ljava/lang/AssertionError;
        //   197: dup            
        //   198: invokespecial   java/lang/AssertionError.<init>:()V
        //   201: athrow         
        //   202: invokestatic    com/jetbrains/cidr/lang/types/OCFunctionType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   205: athrow         
        //   206: return         
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/types/OCType;Ljava/util/List<Lcom/jetbrains/cidr/lang/types/OCType;>;Ljava/util/List<Ljava/lang/String;>;ZZZZ)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  121    139    142    146    Ljava/lang/IllegalArgumentException;
        //  146    159    162    166    Ljava/lang/IllegalArgumentException;
        //  152    187    190    194    Ljava/lang/IllegalArgumentException;
        //  166    202    202    206    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0166:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createConstructor(AstBuilder.java:692)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:529)
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
    
    public static OCType convertArrayParameterType(OCType ocType) {
        if (ocType instanceof OCArrayType) {
            final OCArrayType ocArrayType = (OCArrayType)ocType;
            final OCPointerType to = OCPointerType.to(ocArrayType.getRefType(), ocArrayType.getARCAttribute(), ocArrayType.getClassQualifier(), false, ocArrayType.isVolatile());
            to.setLengthInBrackets(ocArrayType.getLength());
            ocType = to;
        }
        return ocType;
    }
    
    @NotNull
    public OCType getReturnType() {
        OCType myReturnType;
        try {
            myReturnType = this.myReturnType;
            if (myReturnType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCFunctionType", "getReturnType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myReturnType;
    }
    
    @NotNull
    public List<OCType> getParameterTypes(final boolean b) {
        Object o = null;
        Label_0032: {
            Label_0018: {
                try {
                    if (b) {
                        break Label_0018;
                    }
                    final OCFunctionType ocFunctionType = this;
                    final boolean b2 = ocFunctionType.hasNoParameters();
                    if (b2) {
                        break Label_0018;
                    }
                    break Label_0018;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final OCFunctionType ocFunctionType = this;
                    final boolean b2 = ocFunctionType.hasNoParameters();
                    if (b2) {
                        final Object o2;
                        o = (o2 = Collections.emptyList());
                        break Label_0032;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            Object o2;
            o = (o2 = this.myParameterTypes);
            try {
                if (o2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCFunctionType", "getParameterTypes"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return (List<OCType>)o;
    }
    
    @NotNull
    public List<OCType> getParameterTypes() {
        List<OCType> parameterTypes;
        try {
            parameterTypes = this.getParameterTypes(false);
            if (parameterTypes == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCFunctionType", "getParameterTypes"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return parameterTypes;
    }
    
    @Nullable
    public List<String> getParameterNames(final boolean b) {
        Label_0018: {
            try {
                if (b) {
                    return this.myParameterNames;
                }
                final OCFunctionType ocFunctionType = this;
                final boolean b2 = ocFunctionType.hasNoParameters();
                if (b2) {
                    break Label_0018;
                }
                return this.myParameterNames;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCFunctionType ocFunctionType = this;
                final boolean b2 = ocFunctionType.hasNoParameters();
                if (b2) {
                    return Collections.emptyList();
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return this.myParameterNames;
    }
    
    @Nullable
    public List<String> getParameterNames() {
        return this.getParameterNames(false);
    }
    
    public boolean isVararg() {
        if (this.myParameterTypes.size() > 0) {
            final OCType terminalType = this.myParameterTypes.get(this.myParameterTypes.size() - 1).getTerminalType();
            Label_0060: {
                try {
                    if (terminalType instanceof OCEllipsisType) {
                        break Label_0060;
                    }
                    final OCType ocType = terminalType;
                    final boolean b = ocType instanceof OCVariadicType;
                    if (b) {
                        break Label_0060;
                    }
                    return false;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final OCType ocType = terminalType;
                    final boolean b = ocType instanceof OCVariadicType;
                    if (b) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return false;
        }
        return false;
    }
    
    public boolean isLValueRef() {
        return this.checkAttribute(32);
    }
    
    public boolean isRValueRef() {
        return this.checkAttribute(64);
    }
    
    public boolean hasNoParameters() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/types/OCFunctionType.myParameterTypes:Ljava/util/List;
        //     4: invokeinterface java/util/List.isEmpty:()Z
        //     9: ifeq            18
        //    12: iconst_1       
        //    13: ireturn        
        //    14: invokestatic    com/jetbrains/cidr/lang/types/OCFunctionType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    17: athrow         
        //    18: aload_0        
        //    19: getfield        com/jetbrains/cidr/lang/types/OCFunctionType.myParameterTypes:Ljava/util/List;
        //    22: invokeinterface java/util/List.size:()I
        //    27: iconst_1       
        //    28: if_icmpne       103
        //    31: aload_0        
        //    32: getfield        com/jetbrains/cidr/lang/types/OCFunctionType.myParameterTypes:Ljava/util/List;
        //    35: iconst_0       
        //    36: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //    41: instanceof      Lcom/jetbrains/cidr/lang/types/OCVoidType;
        //    44: ifeq            101
        //    47: goto            54
        //    50: invokestatic    com/jetbrains/cidr/lang/types/OCFunctionType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    53: athrow         
        //    54: aload_0        
        //    55: getfield        com/jetbrains/cidr/lang/types/OCFunctionType.myParameterNames:Ljava/util/List;
        //    58: ifnull          93
        //    61: goto            68
        //    64: invokestatic    com/jetbrains/cidr/lang/types/OCFunctionType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    67: athrow         
        //    68: ldc             "<unnamed>"
        //    70: aload_0        
        //    71: getfield        com/jetbrains/cidr/lang/types/OCFunctionType.myParameterNames:Ljava/util/List;
        //    74: iconst_0       
        //    75: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //    80: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    83: ifeq            101
        //    86: goto            93
        //    89: invokestatic    com/jetbrains/cidr/lang/types/OCFunctionType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    92: athrow         
        //    93: iconst_1       
        //    94: goto            102
        //    97: invokestatic    com/jetbrains/cidr/lang/types/OCFunctionType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   100: athrow         
        //   101: iconst_0       
        //   102: ireturn        
        //   103: iconst_0       
        //   104: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      14     14     18     Ljava/lang/IllegalArgumentException;
        //  18     47     50     54     Ljava/lang/IllegalArgumentException;
        //  31     61     64     68     Ljava/lang/IllegalArgumentException;
        //  54     86     89     93     Ljava/lang/IllegalArgumentException;
        //  68     97     97     101    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0054:
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
    public <T> T accept(final OCTypeVisitor<T> ocTypeVisitor) {
        return ocTypeVisitor.visitFunctionType(this);
    }
    
    @Override
    public int hashCode() {
        return 31 * (31 * this.baseHashCode() + this.myReturnType.hashCode()) + this.myParameterTypes.hashCode();
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/types/OCFunctionType", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/types/OCFunctionType", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/types/OCFunctionType", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (!super.deepEqualStep(comparator, o, o2)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        final OCFunctionType ocFunctionType = (OCFunctionType)o;
        final OCFunctionType ocFunctionType2 = (OCFunctionType)o2;
        try {
            if (!Comparing.equal((Object)ocFunctionType.myParameterNames, (Object)ocFunctionType2.myParameterNames)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        try {
            if (!comparator.equalIterable(ocFunctionType.myParameterTypes, ocFunctionType2.myParameterTypes)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        try {
            if (!comparator.equalObjects(ocFunctionType.myReturnType, (DeepEqual.Equality<Object>)ocFunctionType2.myReturnType)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex7) {
            throw a(ex7);
        }
        return true;
    }
    
    @NotNull
    @Override
    protected OCType doGetLeastCommonType(final OCType p0, final PsiElement p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnonnull       53
        //     4: getstatic       com/jetbrains/cidr/lang/types/OCUnknownType.INSTANCE:Lcom/jetbrains/cidr/lang/types/OCUnknownType;
        //     7: dup            
        //     8: ifnonnull       52
        //    11: goto            18
        //    14: invokestatic    com/jetbrains/cidr/lang/types/OCFunctionType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    17: athrow         
        //    18: new             Ljava/lang/IllegalStateException;
        //    21: dup            
        //    22: ldc             "@NotNull method %s.%s must not return null"
        //    24: ldc             2
        //    26: anewarray       Ljava/lang/Object;
        //    29: dup            
        //    30: ldc             0
        //    32: ldc             "com/jetbrains/cidr/lang/types/OCFunctionType"
        //    34: aastore        
        //    35: dup            
        //    36: ldc             1
        //    38: ldc             "doGetLeastCommonType"
        //    40: aastore        
        //    41: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    44: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    47: athrow         
        //    48: invokestatic    com/jetbrains/cidr/lang/types/OCFunctionType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    51: athrow         
        //    52: areturn        
        //    53: aload_1        
        //    54: instanceof      Lcom/jetbrains/cidr/lang/types/OCMagicType;
        //    57: ifeq            107
        //    60: aload_1        
        //    61: dup            
        //    62: ifnonnull       106
        //    65: goto            72
        //    68: invokestatic    com/jetbrains/cidr/lang/types/OCFunctionType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    71: athrow         
        //    72: new             Ljava/lang/IllegalStateException;
        //    75: dup            
        //    76: ldc             "@NotNull method %s.%s must not return null"
        //    78: ldc             2
        //    80: anewarray       Ljava/lang/Object;
        //    83: dup            
        //    84: ldc             0
        //    86: ldc             "com/jetbrains/cidr/lang/types/OCFunctionType"
        //    88: aastore        
        //    89: dup            
        //    90: ldc             1
        //    92: ldc             "doGetLeastCommonType"
        //    94: aastore        
        //    95: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    98: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   101: athrow         
        //   102: invokestatic    com/jetbrains/cidr/lang/types/OCFunctionType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   105: athrow         
        //   106: areturn        
        //   107: aload_1        
        //   108: aload_2        
        //   109: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isNumberCompatible:(Lcom/intellij/psi/PsiElement;)Z
        //   112: ifeq            169
        //   115: getstatic       com/jetbrains/cidr/lang/types/OCIntType.INT:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   118: aload_1        
        //   119: aload_2        
        //   120: invokevirtual   com/jetbrains/cidr/lang/types/OCIntType.getLeastCommonType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   123: dup            
        //   124: ifnonnull       168
        //   127: goto            134
        //   130: invokestatic    com/jetbrains/cidr/lang/types/OCFunctionType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   133: athrow         
        //   134: new             Ljava/lang/IllegalStateException;
        //   137: dup            
        //   138: ldc             "@NotNull method %s.%s must not return null"
        //   140: ldc             2
        //   142: anewarray       Ljava/lang/Object;
        //   145: dup            
        //   146: ldc             0
        //   148: ldc             "com/jetbrains/cidr/lang/types/OCFunctionType"
        //   150: aastore        
        //   151: dup            
        //   152: ldc             1
        //   154: ldc             "doGetLeastCommonType"
        //   156: aastore        
        //   157: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   160: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   163: athrow         
        //   164: invokestatic    com/jetbrains/cidr/lang/types/OCFunctionType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   167: athrow         
        //   168: areturn        
        //   169: aload_1        
        //   170: aload_2        
        //   171: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerCompatible:(Lcom/intellij/psi/PsiElement;)Z
        //   174: ifeq            285
        //   177: aload_0        
        //   178: aload_1        
        //   179: aload_2        
        //   180: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.equals:(Ljava/lang/Object;Lcom/intellij/psi/PsiElement;)Z
        //   183: ifeq            240
        //   186: goto            193
        //   189: invokestatic    com/jetbrains/cidr/lang/types/OCFunctionType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   192: athrow         
        //   193: aload_0        
        //   194: dup            
        //   195: ifnonnull       239
        //   198: goto            205
        //   201: invokestatic    com/jetbrains/cidr/lang/types/OCFunctionType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   204: athrow         
        //   205: new             Ljava/lang/IllegalStateException;
        //   208: dup            
        //   209: ldc             "@NotNull method %s.%s must not return null"
        //   211: ldc             2
        //   213: anewarray       Ljava/lang/Object;
        //   216: dup            
        //   217: ldc             0
        //   219: ldc             "com/jetbrains/cidr/lang/types/OCFunctionType"
        //   221: aastore        
        //   222: dup            
        //   223: ldc             1
        //   225: ldc             "doGetLeastCommonType"
        //   227: aastore        
        //   228: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   231: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   234: athrow         
        //   235: invokestatic    com/jetbrains/cidr/lang/types/OCFunctionType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   238: athrow         
        //   239: areturn        
        //   240: invokestatic    com/jetbrains/cidr/lang/types/OCVoidType.instance:()Lcom/jetbrains/cidr/lang/types/OCVoidType;
        //   243: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.to:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   246: dup            
        //   247: ifnonnull       284
        //   250: new             Ljava/lang/IllegalStateException;
        //   253: dup            
        //   254: ldc             "@NotNull method %s.%s must not return null"
        //   256: ldc             2
        //   258: anewarray       Ljava/lang/Object;
        //   261: dup            
        //   262: ldc             0
        //   264: ldc             "com/jetbrains/cidr/lang/types/OCFunctionType"
        //   266: aastore        
        //   267: dup            
        //   268: ldc             1
        //   270: ldc             "doGetLeastCommonType"
        //   272: aastore        
        //   273: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   276: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   279: athrow         
        //   280: invokestatic    com/jetbrains/cidr/lang/types/OCFunctionType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   283: athrow         
        //   284: areturn        
        //   285: getstatic       com/jetbrains/cidr/lang/types/OCUnknownType.INSTANCE:Lcom/jetbrains/cidr/lang/types/OCUnknownType;
        //   288: dup            
        //   289: ifnonnull       326
        //   292: new             Ljava/lang/IllegalStateException;
        //   295: dup            
        //   296: ldc             "@NotNull method %s.%s must not return null"
        //   298: ldc             2
        //   300: anewarray       Ljava/lang/Object;
        //   303: dup            
        //   304: ldc             0
        //   306: ldc             "com/jetbrains/cidr/lang/types/OCFunctionType"
        //   308: aastore        
        //   309: dup            
        //   310: ldc             1
        //   312: ldc             "doGetLeastCommonType"
        //   314: aastore        
        //   315: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   318: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   321: athrow         
        //   322: invokestatic    com/jetbrains/cidr/lang/types/OCFunctionType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   325: athrow         
        //   326: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      11     14     18     Ljava/lang/IllegalArgumentException;
        //  4      48     48     52     Ljava/lang/IllegalArgumentException;
        //  53     65     68     72     Ljava/lang/IllegalArgumentException;
        //  60     102    102    106    Ljava/lang/IllegalArgumentException;
        //  107    127    130    134    Ljava/lang/IllegalArgumentException;
        //  115    164    164    168    Ljava/lang/IllegalArgumentException;
        //  169    186    189    193    Ljava/lang/IllegalArgumentException;
        //  177    198    201    205    Ljava/lang/IllegalArgumentException;
        //  193    235    235    239    Ljava/lang/IllegalArgumentException;
        //  240    280    280    284    Ljava/lang/IllegalArgumentException;
        //  285    322    322    326    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0193:
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
    public boolean isPointerCompatible(final PsiElement psiElement, final boolean b) {
        return true;
    }
    
    @Override
    public boolean isScalar() {
        return true;
    }
    
    @Override
    public boolean isUnresolved(@NotNull final OCResolveContext p0) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/types/OCFunctionType"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isUnresolved"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/types/OCFunctionType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: getfield        com/jetbrains/cidr/lang/types/OCFunctionType.myReturnType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    48: aload_1        
        //    49: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnresolved:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //    52: ifne            89
        //    55: aload_0        
        //    56: getfield        com/jetbrains/cidr/lang/types/OCFunctionType.myReturnType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    59: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //    62: ifne            89
        //    65: goto            72
        //    68: invokestatic    com/jetbrains/cidr/lang/types/OCFunctionType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    71: athrow         
        //    72: aload_0        
        //    73: getfield        com/jetbrains/cidr/lang/types/OCFunctionType.myReturnType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    76: instanceof      Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //    79: ifeq            95
        //    82: goto            89
        //    85: invokestatic    com/jetbrains/cidr/lang/types/OCFunctionType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    88: athrow         
        //    89: iconst_1       
        //    90: ireturn        
        //    91: invokestatic    com/jetbrains/cidr/lang/types/OCFunctionType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    94: athrow         
        //    95: aload_0        
        //    96: getfield        com/jetbrains/cidr/lang/types/OCFunctionType.myParameterTypes:Ljava/util/List;
        //    99: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   104: astore_2       
        //   105: aload_2        
        //   106: invokeinterface java/util/Iterator.hasNext:()Z
        //   111: ifeq            141
        //   114: aload_2        
        //   115: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   120: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   123: astore_3       
        //   124: aload_3        
        //   125: aload_1        
        //   126: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnresolved:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   129: ifeq            138
        //   132: iconst_1       
        //   133: ireturn        
        //   134: invokestatic    com/jetbrains/cidr/lang/types/OCFunctionType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   137: athrow         
        //   138: goto            105
        //   141: iconst_0       
        //   142: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     65     68     72     Ljava/lang/IllegalArgumentException;
        //  55     82     85     89     Ljava/lang/IllegalArgumentException;
        //  72     91     91     95     Ljava/lang/IllegalArgumentException;
        //  124    134    134    138    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0072:
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
    public String getFormatString() {
        return "%p";
    }
    
    @Override
    public int getSizeInBytes(@Nullable final PsiFile psiFile, @Nullable final OCInclusionContext ocInclusionContext) {
        return OCIntType.INT.getSizeInBytes(psiFile, ocInclusionContext);
    }
    
    @Override
    public boolean isMagicInside(@NotNull final OCResolveContext p0) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/types/OCFunctionType"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isMagicInside"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/types/OCFunctionType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: getfield        com/jetbrains/cidr/lang/types/OCFunctionType.myReturnType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    48: aload_1        
        //    49: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isMagicInside:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //    52: ifne            85
        //    55: aload_0        
        //    56: getfield        com/jetbrains/cidr/lang/types/OCFunctionType.myParameterTypes:Ljava/util/List;
        //    59: invokeinterface java/util/List.stream:()Ljava/util/stream/Stream;
        //    64: aload_1        
        //    65: invokedynamic   test:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Ljava/util/function/Predicate;
        //    70: invokeinterface java/util/stream/Stream.anyMatch:(Ljava/util/function/Predicate;)Z
        //    75: ifeq            93
        //    78: goto            85
        //    81: invokestatic    com/jetbrains/cidr/lang/types/OCFunctionType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    84: athrow         
        //    85: iconst_1       
        //    86: goto            94
        //    89: invokestatic    com/jetbrains/cidr/lang/types/OCFunctionType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    92: athrow         
        //    93: iconst_0       
        //    94: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     78     81     85     Ljava/lang/IllegalArgumentException;
        //  55     89     89     93     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Could not infer any expression.
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:374)
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
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCFunctionType.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
