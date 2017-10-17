// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import com.jetbrains.cidr.lang.preprocessor.OCInclusionContext;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.types.visitors.OCTypeVisitor;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class OCPointerType extends OCType
{
    protected OCType myRefType;
    private ARCAttribute myARCAttribute;
    private OCType myClassQualifier;
    private Integer lengthInBrackets;
    public static OCPointerType NULLPTR_T;
    
    public OCPointerType() {
        this.lengthInBrackets = null;
    }
    
    protected OCPointerType(final OCType myRefType, @Nullable final ARCAttribute myARCAttribute, @Nullable final OCType myClassQualifier, @Nullable final OCNullability ocNullability, final boolean b, final boolean b2) {
        super(b, b2, ocNullability);
        this.lengthInBrackets = null;
        this.myRefType = myRefType;
        this.myARCAttribute = myARCAttribute;
        this.myClassQualifier = myClassQualifier;
    }
    
    public static OCPointerType to(@NotNull final OCType ocType) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ref", "com/jetbrains/cidr/lang/types/OCPointerType", "to"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return new OCPointerType(ocType, null, null, null, false, false);
    }
    
    public static OCPointerType to(@NotNull final OCType ocType, @Nullable final ARCAttribute arcAttribute) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ref", "com/jetbrains/cidr/lang/types/OCPointerType", "to"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return new OCPointerType(ocType, arcAttribute, null, null, false, false);
    }
    
    public static OCPointerType to(@NotNull final OCType ocType, @Nullable final ARCAttribute arcAttribute, @Nullable final OCType ocType2) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ref", "com/jetbrains/cidr/lang/types/OCPointerType", "to"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return new OCPointerType(ocType, arcAttribute, ocType2, null, false, false);
    }
    
    public static OCPointerType to(@NotNull final OCType ocType, @Nullable final ARCAttribute arcAttribute, @Nullable final OCType ocType2, final boolean b, final boolean b2) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ref", "com/jetbrains/cidr/lang/types/OCPointerType", "to"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return new OCPointerType(ocType, arcAttribute, ocType2, null, b, b2);
    }
    
    public static OCPointerType to(@NotNull final OCType ocType, @Nullable final ARCAttribute arcAttribute, @Nullable final OCType ocType2, @Nullable final OCNullability ocNullability, final boolean b, final boolean b2) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ref", "com/jetbrains/cidr/lang/types/OCPointerType", "to"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return new OCPointerType(ocType, arcAttribute, ocType2, ocNullability, b, b2);
    }
    
    public static OCType to(@NotNull OCType to, final int n) {
        try {
            if (to == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ref", "com/jetbrains/cidr/lang/types/OCPointerType", "to"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        for (int i = 0; i < n; ++i) {
            to = to(to);
        }
        return to;
    }
    
    public boolean isPointerToConst() {
        return this.myRefType.isConst();
    }
    
    public boolean isPointerToVolatile() {
        return this.myRefType.isVolatile();
    }
    
    @NotNull
    public OCType getRefType() {
        OCType myRefType;
        try {
            myRefType = this.myRefType;
            if (myRefType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCPointerType", "getRefType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myRefType;
    }
    
    @NotNull
    @Override
    public OCType getTerminalType() {
        OCType terminalType;
        try {
            terminalType = this.myRefType.getTerminalType();
            if (terminalType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCPointerType", "getTerminalType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return terminalType;
    }
    
    @Override
    public int pointersDepth() {
        return this.myRefType.pointersDepth() + 1;
    }
    
    public OCType getClassQualifier() {
        return this.myClassQualifier;
    }
    
    @Override
    public int hashCode() {
        int n;
        try {
            n = (this.baseHashCode() * 31 + this.myRefType.hashCode()) * 31;
            if (this.myClassQualifier != null) {
                final int hashCode = this.myClassQualifier.hashCode();
                return n + hashCode;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final int hashCode = 0;
        return n + hashCode;
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/types/OCPointerType", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/types/OCPointerType", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/types/OCPointerType", "deepEqualStep"));
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
        final OCPointerType ocPointerType = (OCPointerType)o;
        final OCPointerType ocPointerType2 = (OCPointerType)o2;
        try {
            if (ocPointerType.myARCAttribute != ocPointerType2.myARCAttribute) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        try {
            if (!comparator.equalObjects(ocPointerType.myRefType, (DeepEqual.Equality<Object>)ocPointerType2.myRefType)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        try {
            if (!comparator.equalObjects(ocPointerType.myClassQualifier, (DeepEqual.Equality<Object>)ocPointerType2.myClassQualifier)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex7) {
            throw a(ex7);
        }
        return true;
    }
    
    @Override
    public boolean isPointerCompatible(final PsiElement psiElement, final boolean b) {
        return true;
    }
    
    @Override
    public boolean isPointer() {
        return true;
    }
    
    public ARCAttribute getARCAttribute() {
        return this.myARCAttribute;
    }
    
    @Override
    public <T> T accept(final OCTypeVisitor<T> ocTypeVisitor) {
        return ocTypeVisitor.visitPointerType(this);
    }
    
    @NotNull
    @Override
    protected OCType doGetLeastCommonType(final OCType p0, final PsiElement p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aload_1        
        //     2: aload_2        
        //     3: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.equals:(Ljava/lang/Object;Lcom/intellij/psi/PsiElement;)Z
        //     6: ifeq            56
        //     9: aload_0        
        //    10: dup            
        //    11: ifnonnull       55
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    20: athrow         
        //    21: new             Ljava/lang/IllegalStateException;
        //    24: dup            
        //    25: ldc             "@NotNull method %s.%s must not return null"
        //    27: ldc             2
        //    29: anewarray       Ljava/lang/Object;
        //    32: dup            
        //    33: ldc             0
        //    35: ldc             "com/jetbrains/cidr/lang/types/OCPointerType"
        //    37: aastore        
        //    38: dup            
        //    39: ldc             1
        //    41: ldc             "doGetLeastCommonType"
        //    43: aastore        
        //    44: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    47: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    50: athrow         
        //    51: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    54: athrow         
        //    55: areturn        
        //    56: aload_1        
        //    57: instanceof      Lcom/jetbrains/cidr/lang/types/OCMagicType;
        //    60: ifeq            110
        //    63: aload_1        
        //    64: dup            
        //    65: ifnonnull       109
        //    68: goto            75
        //    71: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    74: athrow         
        //    75: new             Ljava/lang/IllegalStateException;
        //    78: dup            
        //    79: ldc             "@NotNull method %s.%s must not return null"
        //    81: ldc             2
        //    83: anewarray       Ljava/lang/Object;
        //    86: dup            
        //    87: ldc             0
        //    89: ldc             "com/jetbrains/cidr/lang/types/OCPointerType"
        //    91: aastore        
        //    92: dup            
        //    93: ldc             1
        //    95: ldc             "doGetLeastCommonType"
        //    97: aastore        
        //    98: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   101: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   104: athrow         
        //   105: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   108: athrow         
        //   109: areturn        
        //   110: aload_0        
        //   111: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.isPointerToObject:()Z
        //   114: ifeq            131
        //   117: aload_1        
        //   118: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObject:()Z
        //   121: ifne            178
        //   124: goto            131
        //   127: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   130: athrow         
        //   131: aload_1        
        //   132: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   135: ifeq            242
        //   138: goto            145
        //   141: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   144: athrow         
        //   145: aload_0        
        //   146: getfield        com/jetbrains/cidr/lang/types/OCPointerType.myRefType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   149: aload_1        
        //   150: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   153: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   156: iconst_0       
        //   157: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   160: dup            
        //   161: aload_2        
        //   162: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
        //   165: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equals:(Ljava/lang/Object;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   168: ifeq            242
        //   171: goto            178
        //   174: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   177: athrow         
        //   178: aload_0        
        //   179: getfield        com/jetbrains/cidr/lang/types/OCPointerType.myRefType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   182: aload_1        
        //   183: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   186: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   189: aload_2        
        //   190: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getLeastCommonType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   193: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.to:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   196: dup            
        //   197: ifnonnull       241
        //   200: goto            207
        //   203: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   206: athrow         
        //   207: new             Ljava/lang/IllegalStateException;
        //   210: dup            
        //   211: ldc             "@NotNull method %s.%s must not return null"
        //   213: ldc             2
        //   215: anewarray       Ljava/lang/Object;
        //   218: dup            
        //   219: ldc             0
        //   221: ldc             "com/jetbrains/cidr/lang/types/OCPointerType"
        //   223: aastore        
        //   224: dup            
        //   225: ldc             1
        //   227: ldc             "doGetLeastCommonType"
        //   229: aastore        
        //   230: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   233: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   236: athrow         
        //   237: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   240: athrow         
        //   241: areturn        
        //   242: aload_1        
        //   243: aload_2        
        //   244: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isNumberCompatible:(Lcom/intellij/psi/PsiElement;)Z
        //   247: ifeq            297
        //   250: aload_0        
        //   251: dup            
        //   252: ifnonnull       296
        //   255: goto            262
        //   258: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   261: athrow         
        //   262: new             Ljava/lang/IllegalStateException;
        //   265: dup            
        //   266: ldc             "@NotNull method %s.%s must not return null"
        //   268: ldc             2
        //   270: anewarray       Ljava/lang/Object;
        //   273: dup            
        //   274: ldc             0
        //   276: ldc             "com/jetbrains/cidr/lang/types/OCPointerType"
        //   278: aastore        
        //   279: dup            
        //   280: ldc             1
        //   282: ldc             "doGetLeastCommonType"
        //   284: aastore        
        //   285: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   288: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   291: athrow         
        //   292: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   295: athrow         
        //   296: areturn        
        //   297: aload_1        
        //   298: aload_2        
        //   299: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerCompatible:(Lcom/intellij/psi/PsiElement;)Z
        //   302: ifeq            357
        //   305: invokestatic    com/jetbrains/cidr/lang/types/OCVoidType.instance:()Lcom/jetbrains/cidr/lang/types/OCVoidType;
        //   308: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.to:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   311: dup            
        //   312: ifnonnull       356
        //   315: goto            322
        //   318: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   321: athrow         
        //   322: new             Ljava/lang/IllegalStateException;
        //   325: dup            
        //   326: ldc             "@NotNull method %s.%s must not return null"
        //   328: ldc             2
        //   330: anewarray       Ljava/lang/Object;
        //   333: dup            
        //   334: ldc             0
        //   336: ldc             "com/jetbrains/cidr/lang/types/OCPointerType"
        //   338: aastore        
        //   339: dup            
        //   340: ldc             1
        //   342: ldc             "doGetLeastCommonType"
        //   344: aastore        
        //   345: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   348: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   351: athrow         
        //   352: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   355: athrow         
        //   356: areturn        
        //   357: getstatic       com/jetbrains/cidr/lang/types/OCUnknownType.INSTANCE:Lcom/jetbrains/cidr/lang/types/OCUnknownType;
        //   360: dup            
        //   361: ifnonnull       398
        //   364: new             Ljava/lang/IllegalStateException;
        //   367: dup            
        //   368: ldc             "@NotNull method %s.%s must not return null"
        //   370: ldc             2
        //   372: anewarray       Ljava/lang/Object;
        //   375: dup            
        //   376: ldc             0
        //   378: ldc             "com/jetbrains/cidr/lang/types/OCPointerType"
        //   380: aastore        
        //   381: dup            
        //   382: ldc             1
        //   384: ldc             "doGetLeastCommonType"
        //   386: aastore        
        //   387: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   390: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   393: athrow         
        //   394: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   397: athrow         
        //   398: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      14     17     21     Ljava/lang/IllegalArgumentException;
        //  9      51     51     55     Ljava/lang/IllegalArgumentException;
        //  56     68     71     75     Ljava/lang/IllegalArgumentException;
        //  63     105    105    109    Ljava/lang/IllegalArgumentException;
        //  110    124    127    131    Ljava/lang/IllegalArgumentException;
        //  117    138    141    145    Ljava/lang/IllegalArgumentException;
        //  131    171    174    178    Ljava/lang/IllegalArgumentException;
        //  145    200    203    207    Ljava/lang/IllegalArgumentException;
        //  178    237    237    241    Ljava/lang/IllegalArgumentException;
        //  242    255    258    262    Ljava/lang/IllegalArgumentException;
        //  250    292    292    296    Ljava/lang/IllegalArgumentException;
        //  297    315    318    322    Ljava/lang/IllegalArgumentException;
        //  305    352    352    356    Ljava/lang/IllegalArgumentException;
        //  357    394    394    398    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0131:
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
    
    public TypeCheckResult validateConstPointers(final OCType p0, final PsiElement p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: astore_3       
        //     2: aload_1        
        //     3: astore          4
        //     5: iconst_1       
        //     6: istore          5
        //     8: iconst_0       
        //     9: istore          6
        //    11: iconst_0       
        //    12: istore          7
        //    14: iconst_0       
        //    15: istore          8
        //    17: iconst_0       
        //    18: istore          9
        //    20: iconst_0       
        //    21: istore          10
        //    23: iconst_1       
        //    24: istore          11
        //    26: iconst_0       
        //    27: istore          12
        //    29: aload_3        
        //    30: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //    33: ifeq            377
        //    36: aload           4
        //    38: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //    41: ifeq            377
        //    44: goto            51
        //    47: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    50: athrow         
        //    51: iinc            12, 1
        //    54: aload_3        
        //    55: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //    58: astore          13
        //    60: aload           4
        //    62: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //    65: astore          14
        //    67: aload           13
        //    69: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.isPointerToConst:()Z
        //    72: ifne            93
        //    75: aload           14
        //    77: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.isPointerToConst:()Z
        //    80: ifeq            93
        //    83: goto            90
        //    86: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    89: athrow         
        //    90: iconst_1       
        //    91: istore          6
        //    93: aload           13
        //    95: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.isPointerToVolatile:()Z
        //    98: ifne            119
        //   101: aload           14
        //   103: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.isPointerToVolatile:()Z
        //   106: ifeq            119
        //   109: goto            116
        //   112: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   115: athrow         
        //   116: iconst_1       
        //   117: istore          7
        //   119: iload           12
        //   121: iconst_1       
        //   122: if_icmple       158
        //   125: aload           13
        //   127: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.isPointerToConst:()Z
        //   130: ifeq            158
        //   133: goto            140
        //   136: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   139: athrow         
        //   140: aload           14
        //   142: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.isPointerToConst:()Z
        //   145: ifne            158
        //   148: goto            155
        //   151: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   154: athrow         
        //   155: iconst_1       
        //   156: istore          6
        //   158: iload           12
        //   160: iconst_1       
        //   161: if_icmple       197
        //   164: aload           13
        //   166: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.isPointerToVolatile:()Z
        //   169: ifeq            197
        //   172: goto            179
        //   175: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   178: athrow         
        //   179: aload           14
        //   181: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.isPointerToVolatile:()Z
        //   184: ifne            197
        //   187: goto            194
        //   190: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   193: athrow         
        //   194: iconst_1       
        //   195: istore          7
        //   197: aload           13
        //   199: instanceof      Lcom/jetbrains/cidr/lang/types/OCBlockPointerType;
        //   202: aload           14
        //   204: instanceof      Lcom/jetbrains/cidr/lang/types/OCBlockPointerType;
        //   207: if_icmpeq       213
        //   210: iconst_0       
        //   211: istore          11
        //   213: aload           13
        //   215: instanceof      Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //   218: ifeq            289
        //   221: aload           13
        //   223: checkcast       Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //   226: astore          15
        //   228: iload           12
        //   230: iconst_1       
        //   231: if_icmple       275
        //   234: aload           14
        //   236: instanceof      Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //   239: ifeq            275
        //   242: goto            249
        //   245: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   248: athrow         
        //   249: aload           14
        //   251: checkcast       Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //   254: astore          16
        //   256: aload           15
        //   258: invokevirtual   com/jetbrains/cidr/lang/types/OCArrayType.getLength:()I
        //   261: aload           16
        //   263: invokevirtual   com/jetbrains/cidr/lang/types/OCArrayType.getLength:()I
        //   266: if_icmpeq       272
        //   269: iconst_1       
        //   270: istore          8
        //   272: goto            286
        //   275: aload           15
        //   277: invokevirtual   com/jetbrains/cidr/lang/types/OCArrayType.hasLength:()Z
        //   280: ifeq            286
        //   283: iconst_1       
        //   284: istore          10
        //   286: goto            328
        //   289: aload           14
        //   291: instanceof      Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //   294: ifeq            328
        //   297: iload           12
        //   299: iconst_1       
        //   300: if_icmpgt       322
        //   303: goto            310
        //   306: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   309: athrow         
        //   310: iload           5
        //   312: ifne            325
        //   315: goto            322
        //   318: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   321: athrow         
        //   322: iconst_1       
        //   323: istore          9
        //   325: iconst_0       
        //   326: istore          5
        //   328: aload           13
        //   330: getfield        com/jetbrains/cidr/lang/types/OCPointerType.myRefType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   333: astore_3       
        //   334: aload           14
        //   336: getfield        com/jetbrains/cidr/lang/types/OCPointerType.myRefType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   339: astore          4
        //   341: aload_3        
        //   342: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   345: ifeq            356
        //   348: aload_3        
        //   349: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   352: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   355: astore_3       
        //   356: aload           4
        //   358: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   361: ifeq            374
        //   364: aload           4
        //   366: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   369: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   372: astore          4
        //   374: goto            29
        //   377: aload_3        
        //   378: instanceof      Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   381: ifeq            422
        //   384: aload           4
        //   386: instanceof      Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   389: ifeq            422
        //   392: goto            399
        //   395: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   398: athrow         
        //   399: iload           11
        //   401: aload_3        
        //   402: aload           4
        //   404: iconst_0       
        //   405: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   408: dup            
        //   409: aload_2        
        //   410: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
        //   413: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equals:(Ljava/lang/Object;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   416: iand           
        //   417: istore          11
        //   419: goto            465
        //   422: iload           11
        //   424: aload_2        
        //   425: ifnull          461
        //   428: aload_3        
        //   429: aload           4
        //   431: iconst_0       
        //   432: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   435: dup            
        //   436: aload_2        
        //   437: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
        //   440: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equals:(Ljava/lang/Object;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   443: ifeq            461
        //   446: goto            453
        //   449: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   452: athrow         
        //   453: iconst_1       
        //   454: goto            462
        //   457: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   460: athrow         
        //   461: iconst_0       
        //   462: iand           
        //   463: istore          11
        //   465: aload_3        
        //   466: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isVoid:()Z
        //   469: ifeq            493
        //   472: iload           12
        //   474: iconst_1       
        //   475: if_icmpne       493
        //   478: goto            485
        //   481: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   484: athrow         
        //   485: iconst_1       
        //   486: goto            494
        //   489: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   492: athrow         
        //   493: iconst_0       
        //   494: istore          13
        //   496: new             Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   499: dup            
        //   500: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.ERROR:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   503: ldc             ""
        //   505: aconst_null    
        //   506: aconst_null    
        //   507: iconst_0       
        //   508: anewarray       Lcom/intellij/codeInsight/intention/IntentionAction;
        //   511: invokespecial   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.<init>:(Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;Ljava/lang/String;Ljava/lang/Class;Ljava/lang/String;[Lcom/intellij/codeInsight/intention/IntentionAction;)V
        //   514: astore          14
        //   516: iload           11
        //   518: ifne            569
        //   521: iload           13
        //   523: ifne            569
        //   526: goto            533
        //   529: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   532: athrow         
        //   533: aload_3        
        //   534: instanceof      Lcom/jetbrains/cidr/lang/types/OCMagicType;
        //   537: ifne            569
        //   540: goto            547
        //   543: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   546: athrow         
        //   547: aload           4
        //   549: instanceof      Lcom/jetbrains/cidr/lang/types/OCMagicType;
        //   552: ifne            569
        //   555: goto            562
        //   558: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   561: athrow         
        //   562: aload           14
        //   564: areturn        
        //   565: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   568: athrow         
        //   569: iload           6
        //   571: ifne            586
        //   574: iload           7
        //   576: ifeq            711
        //   579: goto            586
        //   582: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   585: athrow         
        //   586: aload_3        
        //   587: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnknown:()Z
        //   590: ifne            711
        //   593: goto            600
        //   596: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   599: athrow         
        //   600: aload           4
        //   602: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnknown:()Z
        //   605: ifne            711
        //   608: goto            615
        //   611: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   614: athrow         
        //   615: new             Ljava/lang/StringBuilder;
        //   618: dup            
        //   619: invokespecial   java/lang/StringBuilder.<init>:()V
        //   622: ldc             "Assigning '"
        //   624: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   627: aload_1        
        //   628: aload_2        
        //   629: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   632: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   635: ldc             "' to '"
        //   637: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   640: aload_0        
        //   641: aload_2        
        //   642: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   645: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   648: ldc             "' discards "
        //   650: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   653: iload           6
        //   655: ifeq            674
        //   658: goto            665
        //   661: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   664: athrow         
        //   665: ldc             "const"
        //   667: goto            676
        //   670: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   673: athrow         
        //   674: ldc             "volatile"
        //   676: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   679: ldc             " qualifier"
        //   681: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   684: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   687: astore          15
        //   689: new             Lcom/jetbrains/cidr/lang/types/OCPointerType$1;
        //   692: dup            
        //   693: aload_0        
        //   694: getstatic       com/jetbrains/cidr/lang/types/OCType$TypeCheckState.ERROR_IF_CPP:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   697: ldc             Lcom/jetbrains/cidr/lang/inspections/OCInspections$IncompatiblePointers;.class
        //   699: ldc             "ext_typecheck_convert_discards_qualifiers"
        //   701: iconst_0       
        //   702: anewarray       Lcom/intellij/codeInsight/intention/IntentionAction;
        //   705: aload           15
        //   707: invokespecial   com/jetbrains/cidr/lang/types/OCPointerType$1.<init>:(Lcom/jetbrains/cidr/lang/types/OCPointerType;Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;Ljava/lang/Class;Ljava/lang/String;[Lcom/intellij/codeInsight/intention/IntentionAction;Ljava/lang/String;)V
        //   710: areturn        
        //   711: iload           8
        //   713: ifne            740
        //   716: iload           9
        //   718: ifne            740
        //   721: goto            728
        //   724: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   727: athrow         
        //   728: iload           10
        //   730: ifeq            747
        //   733: goto            740
        //   736: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   739: athrow         
        //   740: aload           14
        //   742: areturn        
        //   743: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   746: athrow         
        //   747: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor.OK_RESULT:Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   750: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  29     44     47     51     Ljava/lang/IllegalArgumentException;
        //  67     83     86     90     Ljava/lang/IllegalArgumentException;
        //  93     109    112    116    Ljava/lang/IllegalArgumentException;
        //  119    133    136    140    Ljava/lang/IllegalArgumentException;
        //  125    148    151    155    Ljava/lang/IllegalArgumentException;
        //  158    172    175    179    Ljava/lang/IllegalArgumentException;
        //  164    187    190    194    Ljava/lang/IllegalArgumentException;
        //  228    242    245    249    Ljava/lang/IllegalArgumentException;
        //  289    303    306    310    Ljava/lang/IllegalArgumentException;
        //  297    315    318    322    Ljava/lang/IllegalArgumentException;
        //  377    392    395    399    Ljava/lang/IllegalArgumentException;
        //  422    446    449    453    Ljava/lang/IllegalArgumentException;
        //  428    457    457    461    Ljava/lang/IllegalArgumentException;
        //  465    478    481    485    Ljava/lang/IllegalArgumentException;
        //  472    489    489    493    Ljava/lang/IllegalArgumentException;
        //  516    526    529    533    Ljava/lang/IllegalArgumentException;
        //  521    540    543    547    Ljava/lang/IllegalArgumentException;
        //  533    555    558    562    Ljava/lang/IllegalArgumentException;
        //  547    565    565    569    Ljava/lang/IllegalArgumentException;
        //  569    579    582    586    Ljava/lang/IllegalArgumentException;
        //  574    593    596    600    Ljava/lang/IllegalArgumentException;
        //  586    608    611    615    Ljava/lang/IllegalArgumentException;
        //  600    658    661    665    Ljava/lang/IllegalArgumentException;
        //  615    670    670    674    Ljava/lang/IllegalArgumentException;
        //  711    721    724    728    Ljava/lang/IllegalArgumentException;
        //  716    733    736    740    Ljava/lang/IllegalArgumentException;
        //  728    743    743    747    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0533:
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
    public boolean isScalar() {
        return true;
    }
    
    @Override
    public boolean isCppStructType() {
        return false;
    }
    
    @Override
    public boolean isInstanceable() {
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
        //    24: ldc             "com/jetbrains/cidr/lang/types/OCPointerType"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isUnresolved"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: getfield        com/jetbrains/cidr/lang/types/OCPointerType.myRefType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    48: aload_1        
        //    49: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnresolved:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //    52: ifne            104
        //    55: aload_0        
        //    56: getfield        com/jetbrains/cidr/lang/types/OCPointerType.myClassQualifier:Lcom/jetbrains/cidr/lang/types/OCType;
        //    59: ifnull          112
        //    62: goto            69
        //    65: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    68: athrow         
        //    69: aload_0        
        //    70: getfield        com/jetbrains/cidr/lang/types/OCPointerType.myClassQualifier:Lcom/jetbrains/cidr/lang/types/OCType;
        //    73: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isCppStructType:()Z
        //    76: ifeq            104
        //    79: goto            86
        //    82: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    85: athrow         
        //    86: aload_0        
        //    87: getfield        com/jetbrains/cidr/lang/types/OCPointerType.myClassQualifier:Lcom/jetbrains/cidr/lang/types/OCType;
        //    90: aload_1        
        //    91: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnresolved:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //    94: ifeq            112
        //    97: goto            104
        //   100: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   103: athrow         
        //   104: iconst_1       
        //   105: goto            113
        //   108: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   111: athrow         
        //   112: iconst_0       
        //   113: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     62     65     69     Ljava/lang/IllegalArgumentException;
        //  55     79     82     86     Ljava/lang/IllegalArgumentException;
        //  69     97     100    104    Ljava/lang/IllegalArgumentException;
        //  86     108    108    112    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0069:
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
        //    24: ldc             "com/jetbrains/cidr/lang/types/OCPointerType"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isMagicInside"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: getfield        com/jetbrains/cidr/lang/types/OCPointerType.myRefType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    48: aload_1        
        //    49: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isMagicInside:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //    52: ifne            87
        //    55: aload_0        
        //    56: getfield        com/jetbrains/cidr/lang/types/OCPointerType.myClassQualifier:Lcom/jetbrains/cidr/lang/types/OCType;
        //    59: ifnull          95
        //    62: goto            69
        //    65: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    68: athrow         
        //    69: aload_0        
        //    70: getfield        com/jetbrains/cidr/lang/types/OCPointerType.myClassQualifier:Lcom/jetbrains/cidr/lang/types/OCType;
        //    73: aload_1        
        //    74: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isMagicInside:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //    77: ifeq            95
        //    80: goto            87
        //    83: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    86: athrow         
        //    87: iconst_1       
        //    88: goto            96
        //    91: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    94: athrow         
        //    95: iconst_0       
        //    96: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     62     65     69     Ljava/lang/IllegalArgumentException;
        //  55     80     83     87     Ljava/lang/IllegalArgumentException;
        //  69     91     91     95     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0069:
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
    public boolean isSubclassOfMagic(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/OCPointerType", "isSubclassOfMagic"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.myRefType.isSubclassOfMagic(ocResolveContext);
    }
    
    @NotNull
    @Override
    public OCType getGuessedUnmagicType() {
        OCPointerType to;
        try {
            to = to(this.myRefType.getGuessedUnmagicType());
            if (to == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCPointerType", "getGuessedUnmagicType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return to;
    }
    
    @Override
    public boolean isPointerToObject() {
        return this.myRefType instanceof OCObjectType;
    }
    
    @Override
    public boolean isPointerToCppStructType() {
        return this.myRefType.isCppStructType();
    }
    
    @Override
    public boolean isPointerToID(final boolean p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/types/OCPointerType.myRefType:Lcom/jetbrains/cidr/lang/types/OCType;
        //     4: instanceof      Lcom/jetbrains/cidr/lang/types/OCIdType;
        //     7: ifeq            54
        //    10: iload_1        
        //    11: ifeq            46
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    20: athrow         
        //    21: aload_0        
        //    22: getfield        com/jetbrains/cidr/lang/types/OCPointerType.myRefType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    25: checkcast       Lcom/jetbrains/cidr/lang/types/OCIdType;
        //    28: invokevirtual   com/jetbrains/cidr/lang/types/OCIdType.getAugmentedProtocols:()Ljava/util/List;
        //    31: invokeinterface java/util/List.isEmpty:()Z
        //    36: ifne            54
        //    39: goto            46
        //    42: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    45: athrow         
        //    46: iconst_1       
        //    47: goto            55
        //    50: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    53: athrow         
        //    54: iconst_0       
        //    55: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      14     17     21     Ljava/lang/IllegalArgumentException;
        //  10     39     42     46     Ljava/lang/IllegalArgumentException;
        //  21     50     50     54     Ljava/lang/IllegalArgumentException;
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
    
    @Override
    public boolean isPointerToChar() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //     3: dup            
        //     4: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:()V
        //     7: astore_1       
        //     8: aload_0        
        //     9: getfield        com/jetbrains/cidr/lang/types/OCPointerType.myRefType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    12: getstatic       com/jetbrains/cidr/lang/types/OCIntType.CHAR:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //    15: iconst_0       
        //    16: aload_1        
        //    17: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equals:(Ljava/lang/Object;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //    20: ifne            133
        //    23: aload_0        
        //    24: getfield        com/jetbrains/cidr/lang/types/OCPointerType.myRefType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    27: getstatic       com/jetbrains/cidr/lang/types/OCIntType.UCHAR:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //    30: iconst_0       
        //    31: aload_1        
        //    32: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equals:(Ljava/lang/Object;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //    35: ifne            133
        //    38: goto            45
        //    41: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    44: athrow         
        //    45: aload_0        
        //    46: getfield        com/jetbrains/cidr/lang/types/OCPointerType.myRefType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    49: getstatic       com/jetbrains/cidr/lang/types/OCIntType.SCHAR:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //    52: iconst_0       
        //    53: aload_1        
        //    54: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equals:(Ljava/lang/Object;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //    57: ifne            133
        //    60: goto            67
        //    63: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    66: athrow         
        //    67: aload_0        
        //    68: getfield        com/jetbrains/cidr/lang/types/OCPointerType.myRefType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    71: getstatic       com/jetbrains/cidr/lang/types/OCIntType.WCHAR:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //    74: iconst_0       
        //    75: aload_1        
        //    76: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equals:(Ljava/lang/Object;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //    79: ifne            133
        //    82: goto            89
        //    85: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    88: athrow         
        //    89: aload_0        
        //    90: getfield        com/jetbrains/cidr/lang/types/OCPointerType.myRefType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    93: getstatic       com/jetbrains/cidr/lang/types/OCIntType.CHAR16:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //    96: iconst_0       
        //    97: aload_1        
        //    98: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equals:(Ljava/lang/Object;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   101: ifne            133
        //   104: goto            111
        //   107: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   110: athrow         
        //   111: aload_0        
        //   112: getfield        com/jetbrains/cidr/lang/types/OCPointerType.myRefType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   115: getstatic       com/jetbrains/cidr/lang/types/OCIntType.CHAR32:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   118: iconst_0       
        //   119: aload_1        
        //   120: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equals:(Ljava/lang/Object;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   123: ifeq            141
        //   126: goto            133
        //   129: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   132: athrow         
        //   133: iconst_1       
        //   134: goto            142
        //   137: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   140: athrow         
        //   141: iconst_0       
        //   142: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  8      38     41     45     Ljava/lang/IllegalArgumentException;
        //  23     60     63     67     Ljava/lang/IllegalArgumentException;
        //  45     82     85     89     Ljava/lang/IllegalArgumentException;
        //  67     104    107    111    Ljava/lang/IllegalArgumentException;
        //  89     126    129    133    Ljava/lang/IllegalArgumentException;
        //  111    137    137    141    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0045:
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
    public boolean isCString() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //     3: dup            
        //     4: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:()V
        //     7: astore_1       
        //     8: aload_0        
        //     9: getfield        com/jetbrains/cidr/lang/types/OCPointerType.myRefType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    12: getstatic       com/jetbrains/cidr/lang/types/OCIntType.CHAR:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //    15: iconst_0       
        //    16: aload_1        
        //    17: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equals:(Ljava/lang/Object;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //    20: ifne            133
        //    23: aload_0        
        //    24: getfield        com/jetbrains/cidr/lang/types/OCPointerType.myRefType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    27: getstatic       com/jetbrains/cidr/lang/types/OCIntType.UCHAR:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //    30: iconst_0       
        //    31: aload_1        
        //    32: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equals:(Ljava/lang/Object;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //    35: ifne            133
        //    38: goto            45
        //    41: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    44: athrow         
        //    45: aload_0        
        //    46: getfield        com/jetbrains/cidr/lang/types/OCPointerType.myRefType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    49: getstatic       com/jetbrains/cidr/lang/types/OCIntType.SCHAR:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //    52: iconst_0       
        //    53: aload_1        
        //    54: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equals:(Ljava/lang/Object;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //    57: ifne            133
        //    60: goto            67
        //    63: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    66: athrow         
        //    67: aload_0        
        //    68: getfield        com/jetbrains/cidr/lang/types/OCPointerType.myRefType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    71: getstatic       com/jetbrains/cidr/lang/types/OCIntType.WCHAR:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //    74: iconst_0       
        //    75: aload_1        
        //    76: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equals:(Ljava/lang/Object;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //    79: ifne            133
        //    82: goto            89
        //    85: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    88: athrow         
        //    89: aload_0        
        //    90: getfield        com/jetbrains/cidr/lang/types/OCPointerType.myRefType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    93: getstatic       com/jetbrains/cidr/lang/types/OCIntType.CHAR16:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //    96: iconst_0       
        //    97: aload_1        
        //    98: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equals:(Ljava/lang/Object;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   101: ifne            133
        //   104: goto            111
        //   107: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   110: athrow         
        //   111: aload_0        
        //   112: getfield        com/jetbrains/cidr/lang/types/OCPointerType.myRefType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   115: getstatic       com/jetbrains/cidr/lang/types/OCIntType.CHAR32:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   118: iconst_0       
        //   119: aload_1        
        //   120: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equals:(Ljava/lang/Object;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   123: ifeq            141
        //   126: goto            133
        //   129: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   132: athrow         
        //   133: iconst_1       
        //   134: goto            142
        //   137: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   140: athrow         
        //   141: iconst_0       
        //   142: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  8      38     41     45     Ljava/lang/IllegalArgumentException;
        //  23     60     63     67     Ljava/lang/IllegalArgumentException;
        //  45     82     85     89     Ljava/lang/IllegalArgumentException;
        //  67     104    107    111    Ljava/lang/IllegalArgumentException;
        //  89     126    129    133    Ljava/lang/IllegalArgumentException;
        //  111    137    137    141    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0045:
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
    public boolean isPointerToVoid() {
        return this.myRefType.isVoid();
    }
    
    @Override
    public String getFormatString() {
        try {
            if (this.isClassType()) {
                return "%@";
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (this.myRefType.isChar()) {
                return "%s";
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (OCIntType.WCHAR.equals(this.myRefType, false, new OCResolveContext())) {
                return "%ls";
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (this.isPointerToObject()) {
                return this.getRefType().getFormatString();
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return "%p";
    }
    
    @Override
    public int getSizeInBytes(@Nullable final PsiFile psiFile, @Nullable final OCInclusionContext ocInclusionContext) {
        return CTypeId.POINTER.getBytes(psiFile, ocInclusionContext);
    }
    
    public Integer getLengthInBrackets() {
        return this.lengthInBrackets;
    }
    
    public void setLengthInBrackets(final Integer lengthInBrackets) {
        this.lengthInBrackets = lengthInBrackets;
    }
    
    static {
        OCPointerType.NULLPTR_T = to(OCVoidType.instance());
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
