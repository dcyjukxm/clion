// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.util.OCNumber;
import com.jetbrains.cidr.lang.types.visitors.OCTypeVisitor;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContext;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.psi.OCElement;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;

public class OCIntType extends OCNumericType
{
    public static final OCIntType BOOL;
    public static final OCIntType BOOLEAN;
    public static final OCIntType BOOL_NATIVE;
    public static final OCIntType CHAR;
    public static final OCIntType CHAR_CONST;
    public static final OCIntType SCHAR;
    public static final OCIntType UCHAR;
    public static final OCIntType WCHAR;
    public static final OCIntType WCHAR_CONST;
    public static final OCIntType CHAR16;
    public static final OCIntType CHAR16_CONST;
    public static final OCIntType CHAR32;
    public static final OCIntType CHAR32_CONST;
    public static final OCIntType SHORT;
    public static final OCIntType USHORT;
    public static final OCIntType INT;
    public static final OCIntType UINT;
    public static final OCIntType LONG;
    public static final OCIntType ULONG;
    public static final OCIntType LONGLONG;
    public static final OCIntType ULONGLONG;
    public static final OCIntType INT128;
    public static final OCIntType UINT128;
    public static final OCIntType SSIZE_T;
    public static final OCIntType SIZE_T;
    public static final OCIntType PTRDIFF_T;
    private boolean myIsSigned;
    private String myText;
    private String myFormatString;
    
    public static boolean isBool(final OCType p0, @Nullable final PsiElement p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       com/jetbrains/cidr/lang/types/OCIntType.BOOL:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //     3: aload_0        
        //     4: aload_1        
        //     5: invokevirtual   com/jetbrains/cidr/lang/types/OCIntType.equalsWithAliasName:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Z
        //     8: ifne            47
        //    11: getstatic       com/jetbrains/cidr/lang/types/OCIntType.BOOLEAN:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //    14: aload_0        
        //    15: aload_1        
        //    16: invokevirtual   com/jetbrains/cidr/lang/types/OCIntType.equalsWithAliasName:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Z
        //    19: ifne            47
        //    22: goto            29
        //    25: invokestatic    com/jetbrains/cidr/lang/types/OCIntType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    28: athrow         
        //    29: getstatic       com/jetbrains/cidr/lang/types/OCIntType.BOOL_NATIVE:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //    32: aload_0        
        //    33: aload_1        
        //    34: invokevirtual   com/jetbrains/cidr/lang/types/OCIntType.equalsWithAliasName:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Z
        //    37: ifeq            55
        //    40: goto            47
        //    43: invokestatic    com/jetbrains/cidr/lang/types/OCIntType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    46: athrow         
        //    47: iconst_1       
        //    48: goto            56
        //    51: invokestatic    com/jetbrains/cidr/lang/types/OCIntType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    54: athrow         
        //    55: iconst_0       
        //    56: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      22     25     29     Ljava/lang/IllegalArgumentException;
        //  11     40     43     47     Ljava/lang/IllegalArgumentException;
        //  29     51     51     55     Ljava/lang/IllegalArgumentException;
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
    
    public static OCIntType getAppropriateBool(@Nullable final OCElement ocElement) {
        Label_0030: {
            try {
                if (ocElement == null) {
                    return OCIntType.BOOL;
                }
                final OCElement ocElement2 = ocElement;
                final OCFile ocFile = ocElement2.getContainingOCFile();
                final OCLanguageKind ocLanguageKind = ocFile.getKind();
                final boolean b = ocLanguageKind.isObjC();
                if (!b) {
                    break Label_0030;
                }
                return OCIntType.BOOL;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCElement ocElement2 = ocElement;
                final OCFile ocFile = ocElement2.getContainingOCFile();
                final OCLanguageKind ocLanguageKind = ocFile.getKind();
                final boolean b = ocLanguageKind.isObjC();
                if (!b) {
                    return OCIntType.BOOL_NATIVE;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return OCIntType.BOOL;
    }
    
    @Override
    public int getRank(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/types/OCIntType", "getRank"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final int sizeInBytes = this.getSizeInBytes(psiElement.getContainingFile(), null);
        int n = 0;
        Label_0078: {
            try {
                n = sizeInBytes << 3;
                if (sizeInBytes <= 1) {
                    break Label_0078;
                }
                final OCIntType ocIntType = this;
                final boolean b = ocIntType.isSigned();
                if (b) {
                    break Label_0078;
                }
                break Label_0078;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final OCIntType ocIntType = this;
                final boolean b = ocIntType.isSigned();
                if (b) {
                    final int n2 = 0;
                    return n + n2;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        final int n2 = 1;
        return n + n2;
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/types/OCIntType", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/types/OCIntType", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/types/OCIntType", "deepEqualStep"));
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
        final OCIntType ocIntType = (OCIntType)o;
        final OCIntType ocIntType2 = (OCIntType)o2;
        try {
            if (ocIntType.myIsSigned != ocIntType2.myIsSigned) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        try {
            if (!Comparing.equal(ocIntType.myFormatString, ocIntType2.myFormatString)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        try {
            if (!Comparing.equal(ocIntType.myText, ocIntType2.myText)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex7) {
            throw a(ex7);
        }
        try {
            if (!ocIntType.myTypeId.equals(ocIntType2.myTypeId)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex8) {
            throw a(ex8);
        }
        return true;
    }
    
    public OCIntType() {
    }
    
    private OCIntType(final boolean b, final CTypeId cTypeId, final String s, final String s2) {
        this(b, cTypeId, s, s2, false, false);
    }
    
    private OCIntType(final boolean myIsSigned, final CTypeId myTypeId, final String myText, final String myFormatString, final boolean b, final boolean b2) {
        super(b, b2);
        this.myIsSigned = myIsSigned;
        this.myTypeId = myTypeId;
        this.myText = myText;
        this.myFormatString = myFormatString;
    }
    
    public OCIntType cloneType(final boolean b, final boolean b2) {
        return new OCIntType(this.isSigned(), this.getCTypeId(), this.getText(), this.getFormatString(), b, b2);
    }
    
    @Override
    public <T> T accept(final OCTypeVisitor<T> ocTypeVisitor) {
        return ocTypeVisitor.visitIntType(this);
    }
    
    @Override
    public boolean isSigned() {
        return this.myIsSigned;
    }
    
    @Override
    public boolean isComplex() {
        return false;
    }
    
    @Override
    public boolean isChar() {
        return CTypeId.CHAR.equals(this.myTypeId);
    }
    
    public String getText() {
        return this.myText;
    }
    
    @Override
    public boolean isIntegerCompatible(final PsiElement psiElement, final boolean b) {
        return true;
    }
    
    @NotNull
    @Override
    public String getFormatString() {
        String myFormatString;
        try {
            myFormatString = this.myFormatString;
            if (myFormatString == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCIntType", "getFormatString"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myFormatString;
    }
    
    @Override
    public int hashCode() {
        int n;
        try {
            n = 31 * super.hashCode();
            if (this.myIsSigned) {
                final int n2 = 1;
                return n + n2;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final int n2 = 0;
        return n + n2;
    }
    
    @NotNull
    public static OCIntType literalType(@NotNull final String p0, @Nullable final PsiElement p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "text"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/types/OCIntType"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "literalType"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/types/OCIntType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: getstatic       java/util/Locale.ROOT:Ljava/util/Locale;
        //    48: invokevirtual   java/lang/String.toUpperCase:(Ljava/util/Locale;)Ljava/lang/String;
        //    51: astore_0       
        //    52: ldc             "TRUE"
        //    54: aload_0        
        //    55: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    58: ifne            77
        //    61: ldc             "FALSE"
        //    63: aload_0        
        //    64: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    67: ifeq            126
        //    70: goto            77
        //    73: invokestatic    com/jetbrains/cidr/lang/types/OCIntType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    76: athrow         
        //    77: getstatic       com/jetbrains/cidr/lang/types/OCIntType.BOOL:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //    80: dup            
        //    81: ifnonnull       125
        //    84: goto            91
        //    87: invokestatic    com/jetbrains/cidr/lang/types/OCIntType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    90: athrow         
        //    91: new             Ljava/lang/IllegalStateException;
        //    94: dup            
        //    95: ldc             "@NotNull method %s.%s must not return null"
        //    97: ldc             2
        //    99: anewarray       Ljava/lang/Object;
        //   102: dup            
        //   103: ldc             0
        //   105: ldc             "com/jetbrains/cidr/lang/types/OCIntType"
        //   107: aastore        
        //   108: dup            
        //   109: ldc             1
        //   111: ldc             "literalType"
        //   113: aastore        
        //   114: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   117: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   120: athrow         
        //   121: invokestatic    com/jetbrains/cidr/lang/types/OCIntType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   124: athrow         
        //   125: areturn        
        //   126: aload_0        
        //   127: aload_1        
        //   128: aconst_null    
        //   129: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.parseInteger:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContext;)Lcom/intellij/openapi/util/Pair;
        //   132: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //   135: checkcast       Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   138: dup            
        //   139: ifnonnull       176
        //   142: new             Ljava/lang/IllegalStateException;
        //   145: dup            
        //   146: ldc             "@NotNull method %s.%s must not return null"
        //   148: ldc             2
        //   150: anewarray       Ljava/lang/Object;
        //   153: dup            
        //   154: ldc             0
        //   156: ldc             "com/jetbrains/cidr/lang/types/OCIntType"
        //   158: aastore        
        //   159: dup            
        //   160: ldc             1
        //   162: ldc             "literalType"
        //   164: aastore        
        //   165: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   168: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   171: athrow         
        //   172: invokestatic    com/jetbrains/cidr/lang/types/OCIntType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   175: athrow         
        //   176: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  52     70     73     77     Ljava/lang/IllegalArgumentException;
        //  61     84     87     91     Ljava/lang/IllegalArgumentException;
        //  77     121    121    125    Ljava/lang/IllegalArgumentException;
        //  126    172    172    176    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0077:
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
    
    public boolean canRepresent(@NotNull final OCNumber p0, @NotNull final PsiElement p1) {
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
        //    18: ldc             "value"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/types/OCIntType"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "canRepresent"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/types/OCIntType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_2        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "element"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/types/OCIntType"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "canRepresent"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/types/OCIntType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_1        
        //    89: invokevirtual   com/jetbrains/cidr/lang/util/OCNumber.isSigned:()Z
        //    92: ifeq            130
        //    95: aload_1        
        //    96: invokevirtual   com/jetbrains/cidr/lang/util/OCNumber.signum:()I
        //    99: iconst_m1      
        //   100: if_icmpne       130
        //   103: goto            110
        //   106: invokestatic    com/jetbrains/cidr/lang/types/OCIntType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   109: athrow         
        //   110: aload_0        
        //   111: getfield        com/jetbrains/cidr/lang/types/OCIntType.myIsSigned:Z
        //   114: ifne            130
        //   117: goto            124
        //   120: invokestatic    com/jetbrains/cidr/lang/types/OCIntType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   123: athrow         
        //   124: iconst_0       
        //   125: ireturn        
        //   126: invokestatic    com/jetbrains/cidr/lang/types/OCIntType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   129: athrow         
        //   130: aload_1        
        //   131: invokevirtual   com/jetbrains/cidr/lang/util/OCNumber.bitLength:()I
        //   134: istore_3       
        //   135: aload_0        
        //   136: aload_2        
        //   137: aconst_null    
        //   138: invokevirtual   com/jetbrains/cidr/lang/types/OCIntType.getBits:(Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/preprocessor/OCInclusionContext;)I
        //   141: aload_0        
        //   142: getfield        com/jetbrains/cidr/lang/types/OCIntType.myIsSigned:Z
        //   145: ifeq            156
        //   148: iconst_1       
        //   149: goto            157
        //   152: invokestatic    com/jetbrains/cidr/lang/types/OCIntType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   155: athrow         
        //   156: iconst_0       
        //   157: isub           
        //   158: istore          4
        //   160: iload_3        
        //   161: iload           4
        //   163: if_icmpgt       174
        //   166: iconst_1       
        //   167: goto            175
        //   170: invokestatic    com/jetbrains/cidr/lang/types/OCIntType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   173: athrow         
        //   174: iconst_0       
        //   175: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     103    106    110    Ljava/lang/IllegalArgumentException;
        //  95     117    120    124    Ljava/lang/IllegalArgumentException;
        //  110    126    126    130    Ljava/lang/IllegalArgumentException;
        //  135    152    152    156    Ljava/lang/IllegalArgumentException;
        //  160    170    170    174    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0110:
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
    public String getDefaultValue(@Nullable final PsiElement psiElement) {
        String value;
        try {
            value = this.getValue(false, psiElement);
            if (value == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCIntType", "getDefaultValue"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return value;
    }
    
    public String getValue(final boolean p0, @Nullable final PsiElement p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/types/OCIntType.myAliasName:Ljava/lang/String;
        //     4: ldc             "BOOL"
        //     6: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/String;Ljava/lang/String;)Z
        //     9: ifeq            35
        //    12: iload_1        
        //    13: ifeq            32
        //    16: goto            23
        //    19: invokestatic    com/jetbrains/cidr/lang/types/OCIntType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    22: athrow         
        //    23: ldc             "YES"
        //    25: goto            34
        //    28: invokestatic    com/jetbrains/cidr/lang/types/OCIntType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    31: athrow         
        //    32: ldc             "NO"
        //    34: areturn        
        //    35: aload_0        
        //    36: getfield        com/jetbrains/cidr/lang/types/OCIntType.myAliasName:Ljava/lang/String;
        //    39: ldc             "Boolean"
        //    41: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/String;Ljava/lang/String;)Z
        //    44: ifne            80
        //    47: aload_2        
        //    48: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.isInPlainOldC:(Lcom/intellij/psi/PsiElement;)Z
        //    51: ifne            103
        //    54: goto            61
        //    57: invokestatic    com/jetbrains/cidr/lang/types/OCIntType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    60: athrow         
        //    61: aload_0        
        //    62: getfield        com/jetbrains/cidr/lang/types/OCIntType.myAliasName:Ljava/lang/String;
        //    65: ldc             "bool"
        //    67: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/String;Ljava/lang/String;)Z
        //    70: ifeq            103
        //    73: goto            80
        //    76: invokestatic    com/jetbrains/cidr/lang/types/OCIntType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    79: athrow         
        //    80: iload_1        
        //    81: ifeq            100
        //    84: goto            91
        //    87: invokestatic    com/jetbrains/cidr/lang/types/OCIntType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    90: athrow         
        //    91: ldc             "true"
        //    93: goto            102
        //    96: invokestatic    com/jetbrains/cidr/lang/types/OCIntType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    99: athrow         
        //   100: ldc             "false"
        //   102: areturn        
        //   103: iload_1        
        //   104: ifeq            116
        //   107: ldc             "1"
        //   109: goto            118
        //   112: invokestatic    com/jetbrains/cidr/lang/types/OCIntType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   115: athrow         
        //   116: ldc             "0"
        //   118: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      16     19     23     Ljava/lang/IllegalArgumentException;
        //  12     28     28     32     Ljava/lang/IllegalArgumentException;
        //  35     54     57     61     Ljava/lang/IllegalArgumentException;
        //  47     73     76     80     Ljava/lang/IllegalArgumentException;
        //  61     84     87     91     Ljava/lang/IllegalArgumentException;
        //  80     96     96     100    Ljava/lang/IllegalArgumentException;
        //  103    112    112    116    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0061:
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
    public OCIntType promoteToSigned() {
        OCIntType longlong = null;
        Label_0185: {
            OCIntType ocIntType3 = null;
            Label_0150: {
                Label_0119: {
                    OCIntType ocIntType2 = null;
                    Label_0084: {
                        Label_0054: {
                            OCIntType ocIntType = null;
                            Label_0019: {
                                try {
                                    if (!this.myIsSigned) {
                                        break Label_0054;
                                    }
                                    ocIntType = this;
                                    if (ocIntType == null) {
                                        break Label_0019;
                                    }
                                    return ocIntType;
                                }
                                catch (IllegalArgumentException ex) {
                                    throw a(ex);
                                }
                                try {
                                    ocIntType = this;
                                    if (ocIntType == null) {
                                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCIntType", "promoteToSigned"));
                                    }
                                }
                                catch (IllegalArgumentException ex2) {
                                    throw a(ex2);
                                }
                            }
                            return ocIntType;
                            try {
                                if (this.myTypeId.ordinal() > CTypeId.INT.ordinal()) {
                                    break Label_0119;
                                }
                                ocIntType2 = OCIntType.INT;
                                if (ocIntType2 == null) {
                                    break Label_0084;
                                }
                                return ocIntType2;
                            }
                            catch (IllegalArgumentException ex3) {
                                throw a(ex3);
                            }
                        }
                        try {
                            ocIntType2 = OCIntType.INT;
                            if (ocIntType2 == null) {
                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCIntType", "promoteToSigned"));
                            }
                        }
                        catch (IllegalArgumentException ex4) {
                            throw a(ex4);
                        }
                    }
                    return ocIntType2;
                    try {
                        if (!this.equals(OCIntType.ULONG, new OCResolveContext())) {
                            break Label_0185;
                        }
                        ocIntType3 = OCIntType.LONG;
                        if (ocIntType3 == null) {
                            break Label_0150;
                        }
                        return ocIntType3;
                    }
                    catch (IllegalArgumentException ex5) {
                        throw a(ex5);
                    }
                }
                try {
                    ocIntType3 = OCIntType.LONG;
                    if (ocIntType3 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCIntType", "promoteToSigned"));
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
            }
            return ocIntType3;
            try {
                longlong = OCIntType.LONGLONG;
                if (longlong == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCIntType", "promoteToSigned"));
                }
            }
            catch (IllegalArgumentException ex7) {
                throw a(ex7);
            }
        }
        return longlong;
    }
    
    static {
        BOOL = new OCIntType(true, CTypeId.SIGNED_CHAR, "signed char", "%d");
        BOOLEAN = new OCIntType(false, CTypeId.CHAR, "unsigned char", "%d");
        BOOL_NATIVE = new OCIntType(true, CTypeId.BOOL, "bool", "%d");
        CHAR = new OCIntType(true, CTypeId.CHAR, "char", "%c");
        CHAR_CONST = new OCIntType(true, CTypeId.CHAR, "char", "%c", true, false);
        SCHAR = new OCIntType(true, CTypeId.SIGNED_CHAR, "signed char", "%c");
        UCHAR = new OCIntType(false, CTypeId.CHAR, "unsigned char", "%c");
        WCHAR = new OCIntType(true, CTypeId.WCHAR_T, "wchar_t", "%lc");
        WCHAR_CONST = new OCIntType(true, CTypeId.WCHAR_T, "wchar_t", "%lc", true, false);
        CHAR16 = new OCIntType(false, CTypeId.CHAR16_T, "char16_t", "%lc");
        CHAR16_CONST = new OCIntType(false, CTypeId.CHAR16_T, "char16_t", "%lc", true, false);
        CHAR32 = new OCIntType(false, CTypeId.CHAR32_T, "char32_t", "%lc");
        CHAR32_CONST = new OCIntType(false, CTypeId.CHAR32_T, "char32_t", "%lc", true, false);
        SHORT = new OCIntType(true, CTypeId.SHORT, "short", "%hi");
        USHORT = new OCIntType(false, CTypeId.SHORT, "unsigned short", "%hu");
        INT = new OCIntType(true, CTypeId.INT, "int", "%i");
        UINT = new OCIntType(false, CTypeId.INT, "unsigned int", "%u");
        LONG = new OCIntType(true, CTypeId.LONG, "long", "%li");
        ULONG = new OCIntType(false, CTypeId.LONG, "unsigned long", "%lu");
        LONGLONG = new OCIntType(true, CTypeId.LONG_LONG, "long long int", "%lli");
        ULONGLONG = new OCIntType(false, CTypeId.LONG_LONG, "unsigned long long int", "%llu");
        INT128 = new OCIntType(true, CTypeId.INT128_T, "__int128_t", "%ji");
        UINT128 = new OCIntType(false, CTypeId.INT128_T, "__uint128_t", "%ju");
        SSIZE_T = new OCIntType(true, CTypeId.SIZE_T, "ssize_t", "%zi");
        SIZE_T = new OCIntType(false, CTypeId.SIZE_T, "size_t", "%zu");
        PTRDIFF_T = new OCIntType(true, CTypeId.PTRDIFF_T, "ptrdiff_t", "%ti");
        OCIntType.BOOL.attachAliasName("BOOL");
        OCIntType.BOOLEAN.attachAliasName("Boolean");
        OCIntType.BOOL_NATIVE.attachAliasName("bool");
        OCIntType.SIZE_T.attachAliasName("std::size_t");
        OCIntType.PTRDIFF_T.attachAliasName("std::ptrdiff_t");
        OCIntType.INT128.attachAliasName("__int128");
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
