// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve.v2;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.types.OCUnknownType;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.types.OCType;

class StandardConversionSequence
{
    ImplicitConversionKind First;
    ImplicitConversionKind Second;
    ImplicitConversionKind Third;
    boolean DeprecatedStringLiteralToCharPtr;
    boolean QualificationIncludesObjCLifetime;
    boolean IncompatibleObjC;
    boolean ReferenceBinding;
    boolean DirectBinding;
    boolean IsLvalueReference;
    boolean BindsToFunctionLvalue;
    boolean BindsToRvalue;
    boolean BindsImplicitObjectArgumentWithoutRefQualifier;
    boolean ObjCLifetimeConversionBinding;
    OCType FromTypePtr;
    OCType[] ToTypePtrs;
    OCFunctionSymbol CopyConstructor;
    private static final ImplicitConversionRank[] Rank;
    static final /* synthetic */ boolean $assertionsDisabled;
    
    void setFromType(final OCType fromTypePtr) {
        this.FromTypePtr = fromTypePtr;
    }
    
    void setToType(final int n, final OCType ocType) {
        Label_0018: {
            try {
                if (StandardConversionSequence.$assertionsDisabled) {
                    break Label_0018;
                }
                final int n2 = n;
                final int n3 = 3;
                if (n2 >= n3) {
                    break Label_0018;
                }
                break Label_0018;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final int n2 = n;
                final int n3 = 3;
                if (n2 >= n3) {
                    throw new AssertionError((Object)"To type index is out of range");
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        this.ToTypePtrs[n] = ocType;
    }
    
    void setAllToTypes(final OCType ocType) {
        this.ToTypePtrs[0] = ocType;
        this.ToTypePtrs[1] = this.ToTypePtrs[0];
        this.ToTypePtrs[2] = this.ToTypePtrs[0];
    }
    
    OCType getFromType() {
        return this.FromTypePtr;
    }
    
    OCType getToType(final int n) {
        Label_0018: {
            try {
                if (StandardConversionSequence.$assertionsDisabled) {
                    return this.ToTypePtrs[n];
                }
                final int n2 = n;
                final int n3 = 3;
                if (n2 >= n3) {
                    break Label_0018;
                }
                return this.ToTypePtrs[n];
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final int n2 = n;
                final int n3 = 3;
                if (n2 >= n3) {
                    throw new AssertionError((Object)"To type index is out of range");
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return this.ToTypePtrs[n];
    }
    
    public StandardConversionSequence() {
        this.ToTypePtrs = new OCType[3];
        this.First = ImplicitConversionKind.ICK_Identity;
        this.Second = ImplicitConversionKind.ICK_Identity;
        this.Third = ImplicitConversionKind.ICK_Identity;
        this.DeprecatedStringLiteralToCharPtr = false;
        this.QualificationIncludesObjCLifetime = false;
        this.ReferenceBinding = false;
        this.DirectBinding = false;
        this.IsLvalueReference = true;
        this.BindsToFunctionLvalue = false;
        this.BindsToRvalue = false;
        this.BindsImplicitObjectArgumentWithoutRefQualifier = false;
        this.ObjCLifetimeConversionBinding = false;
        this.CopyConstructor = null;
        this.FromTypePtr = OCUnknownType.INSTANCE;
        this.setAllToTypes(OCUnknownType.INSTANCE);
    }
    
    public void setAsIdentityConversion() {
        this.First = ImplicitConversionKind.ICK_Identity;
        this.Second = ImplicitConversionKind.ICK_Identity;
        this.Third = ImplicitConversionKind.ICK_Identity;
        this.DeprecatedStringLiteralToCharPtr = false;
        this.QualificationIncludesObjCLifetime = false;
        this.ReferenceBinding = false;
        this.DirectBinding = false;
        this.IsLvalueReference = true;
        this.BindsToFunctionLvalue = false;
        this.BindsToRvalue = false;
        this.BindsImplicitObjectArgumentWithoutRefQualifier = false;
        this.ObjCLifetimeConversionBinding = false;
        this.CopyConstructor = null;
    }
    
    boolean isIdentityConversion() {
        Label_0027: {
            try {
                if (this.Second != ImplicitConversionKind.ICK_Identity) {
                    return false;
                }
                final StandardConversionSequence standardConversionSequence = this;
                final ImplicitConversionKind implicitConversionKind = standardConversionSequence.Third;
                final ImplicitConversionKind implicitConversionKind2 = ImplicitConversionKind.ICK_Identity;
                if (implicitConversionKind == implicitConversionKind2) {
                    break Label_0027;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final StandardConversionSequence standardConversionSequence = this;
                final ImplicitConversionKind implicitConversionKind = standardConversionSequence.Third;
                final ImplicitConversionKind implicitConversionKind2 = ImplicitConversionKind.ICK_Identity;
                if (implicitConversionKind == implicitConversionKind2) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    private static ImplicitConversionRank a(final ImplicitConversionKind implicitConversionKind) {
        return StandardConversionSequence.Rank[implicitConversionKind.ordinal()];
    }
    
    ImplicitConversionRank getRank() {
        ImplicitConversionRank implicitConversionRank = ImplicitConversionRank.ICR_Exact_Match;
        if (a(this.First).ordinal() > implicitConversionRank.ordinal()) {
            implicitConversionRank = a(this.First);
        }
        if (a(this.Second).ordinal() > implicitConversionRank.ordinal()) {
            implicitConversionRank = a(this.Second);
        }
        if (a(this.Third).ordinal() > implicitConversionRank.ordinal()) {
            implicitConversionRank = a(this.Third);
        }
        return implicitConversionRank;
    }
    
    boolean isPointerConversionToBool(@NotNull final OCResolveContext p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
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
        //    24: ldc             "com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isPointerConversionToBool"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: iconst_1       
        //    46: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.getToType:(I)Lcom/jetbrains/cidr/lang/types/OCType;
        //    49: aload_1        
        //    50: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getElement:()Lcom/intellij/psi/PsiElement;
        //    53: invokestatic    com/jetbrains/cidr/lang/types/OCIntType.isBool:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Z
        //    56: ifeq            116
        //    59: aload_0        
        //    60: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.getFromType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    63: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //    66: ifne            110
        //    69: goto            76
        //    72: invokestatic    com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    75: athrow         
        //    76: aload_0        
        //    77: getfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.First:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //    80: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind.ICK_Array_To_Pointer:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //    83: if_acmpeq       110
        //    86: goto            93
        //    89: invokestatic    com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    92: athrow         
        //    93: aload_0        
        //    94: getfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.First:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //    97: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind.ICK_Function_To_Pointer:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //   100: if_acmpne       116
        //   103: goto            110
        //   106: invokestatic    com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   109: athrow         
        //   110: iconst_1       
        //   111: ireturn        
        //   112: invokestatic    com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   115: athrow         
        //   116: iconst_0       
        //   117: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     69     72     76     Ljava/lang/IllegalArgumentException;
        //  59     86     89     93     Ljava/lang/IllegalArgumentException;
        //  76     103    106    110    Ljava/lang/IllegalArgumentException;
        //  93     112    112    116    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0076:
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
    
    public boolean isPointerConversionToVoidPointer(@NotNull final OCResolveContext p0) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isPointerConversionToVoidPointer"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.getFromType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    48: astore_2       
        //    49: aload_0        
        //    50: iconst_1       
        //    51: invokevirtual   com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.getToType:(I)Lcom/jetbrains/cidr/lang/types/OCType;
        //    54: astore_3       
        //    55: aload_0        
        //    56: getfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.First:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //    59: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind.ICK_Array_To_Pointer:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //    62: if_acmpne       74
        //    65: aload_2        
        //    66: checkcast       Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //    69: aload_1        
        //    70: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TypeProperties.getArrayDecayedType:(Lcom/jetbrains/cidr/lang/types/OCArrayType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //    73: astore_2       
        //    74: aload_0        
        //    75: getfield        com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.Second:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //    78: getstatic       com/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind.ICK_Pointer_Conversion:Lcom/jetbrains/cidr/lang/resolve/v2/ImplicitConversionKind;
        //    81: if_acmpne       127
        //    84: aload_2        
        //    85: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //    88: ifeq            127
        //    91: goto            98
        //    94: invokestatic    com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    97: athrow         
        //    98: aload_3        
        //    99: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   102: ifeq            127
        //   105: goto            112
        //   108: invokestatic    com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   111: athrow         
        //   112: aload_3        
        //   113: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   116: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   119: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isVoid:()Z
        //   122: ireturn        
        //   123: invokestatic    com/jetbrains/cidr/lang/resolve/v2/StandardConversionSequence.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   126: athrow         
        //   127: iconst_0       
        //   128: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  74     91     94     98     Ljava/lang/IllegalArgumentException;
        //  84     105    108    112    Ljava/lang/IllegalArgumentException;
        //  98     123    123    127    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0098:
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
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!StandardConversionSequence.class.desiredAssertionStatus()) {
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
        Rank = new ImplicitConversionRank[] { ImplicitConversionRank.ICR_Exact_Match, ImplicitConversionRank.ICR_Exact_Match, ImplicitConversionRank.ICR_Exact_Match, ImplicitConversionRank.ICR_Exact_Match, ImplicitConversionRank.ICR_Exact_Match, ImplicitConversionRank.ICR_Exact_Match, ImplicitConversionRank.ICR_Promotion, ImplicitConversionRank.ICR_Promotion, ImplicitConversionRank.ICR_Promotion, ImplicitConversionRank.ICR_Conversion, ImplicitConversionRank.ICR_Conversion, ImplicitConversionRank.ICR_Conversion, ImplicitConversionRank.ICR_Conversion, ImplicitConversionRank.ICR_Conversion, ImplicitConversionRank.ICR_Conversion, ImplicitConversionRank.ICR_Conversion, ImplicitConversionRank.ICR_Conversion, ImplicitConversionRank.ICR_Conversion, ImplicitConversionRank.ICR_Conversion, ImplicitConversionRank.ICR_Conversion, ImplicitConversionRank.ICR_Complex_Real_Conversion, ImplicitConversionRank.ICR_Conversion, ImplicitConversionRank.ICR_Conversion, ImplicitConversionRank.ICR_Writeback_Conversion, ImplicitConversionRank.ICR_Exact_Match, ImplicitConversionRank.ICR_C_Conversion };
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
