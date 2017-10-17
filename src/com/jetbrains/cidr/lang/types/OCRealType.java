// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import com.jetbrains.cidr.lang.preprocessor.OCInclusionContext;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.types.visitors.OCTypeVisitor;
import java.math.BigDecimal;
import java.math.MathContext;
import com.intellij.openapi.util.Comparing;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.DeepEqual;

public class OCRealType extends OCNumericType
{
    public static final OCRealType FLOAT;
    public static final OCRealType DOUBLE;
    public static final OCRealType LONG_DOUBLE;
    public static final OCRealType COMPLEX_FLOAT;
    public static final OCRealType COMPLEX_DOUBLE;
    public static final OCRealType COMPLEX_LONG_DOUBLE;
    private boolean myComplex;
    private String myText;
    private String myFormatString;
    
    public OCRealType() {
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/types/OCRealType", "deepEqualStep"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/types/OCRealType", "deepEqualStep"));
            }
        }
        catch (NumberFormatException ex2) {
            throw b(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/types/OCRealType", "deepEqualStep"));
            }
        }
        catch (NumberFormatException ex3) {
            throw b(ex3);
        }
        try {
            if (!super.deepEqualStep(comparator, o, o2)) {
                return false;
            }
        }
        catch (NumberFormatException ex4) {
            throw b(ex4);
        }
        final OCRealType ocRealType = (OCRealType)o;
        final OCRealType ocRealType2 = (OCRealType)o2;
        try {
            if (!ocRealType.myTypeId.equals(ocRealType2.myTypeId)) {
                return false;
            }
        }
        catch (NumberFormatException ex5) {
            throw b(ex5);
        }
        try {
            if (!Comparing.equal(ocRealType.myText, ocRealType2.myText)) {
                return false;
            }
        }
        catch (NumberFormatException ex6) {
            throw b(ex6);
        }
        return true;
    }
    
    @NotNull
    public static OCRealType literalType(@NotNull final String p0) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/types/OCRealType"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "literalType"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/types/OCRealType.b:(Ljava/lang/NumberFormatException;)Ljava/lang/NumberFormatException;
        //    43: athrow         
        //    44: aload_0        
        //    45: aload_0        
        //    46: invokevirtual   java/lang/String.length:()I
        //    49: iconst_1       
        //    50: isub           
        //    51: invokevirtual   java/lang/String.charAt:(I)C
        //    54: istore_1       
        //    55: iload_1        
        //    56: bipush          108
        //    58: if_icmpeq       74
        //    61: iload_1        
        //    62: bipush          76
        //    64: if_icmpne       123
        //    67: goto            74
        //    70: invokestatic    com/jetbrains/cidr/lang/types/OCRealType.b:(Ljava/lang/NumberFormatException;)Ljava/lang/NumberFormatException;
        //    73: athrow         
        //    74: getstatic       com/jetbrains/cidr/lang/types/OCRealType.LONG_DOUBLE:Lcom/jetbrains/cidr/lang/types/OCRealType;
        //    77: dup            
        //    78: ifnonnull       122
        //    81: goto            88
        //    84: invokestatic    com/jetbrains/cidr/lang/types/OCRealType.b:(Ljava/lang/NumberFormatException;)Ljava/lang/NumberFormatException;
        //    87: athrow         
        //    88: new             Ljava/lang/IllegalStateException;
        //    91: dup            
        //    92: ldc             "@NotNull method %s.%s must not return null"
        //    94: ldc             2
        //    96: anewarray       Ljava/lang/Object;
        //    99: dup            
        //   100: ldc             0
        //   102: ldc             "com/jetbrains/cidr/lang/types/OCRealType"
        //   104: aastore        
        //   105: dup            
        //   106: ldc             1
        //   108: ldc             "literalType"
        //   110: aastore        
        //   111: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   114: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   117: athrow         
        //   118: invokestatic    com/jetbrains/cidr/lang/types/OCRealType.b:(Ljava/lang/NumberFormatException;)Ljava/lang/NumberFormatException;
        //   121: athrow         
        //   122: areturn        
        //   123: iload_1        
        //   124: bipush          102
        //   126: if_icmpeq       142
        //   129: iload_1        
        //   130: bipush          70
        //   132: if_icmpne       191
        //   135: goto            142
        //   138: invokestatic    com/jetbrains/cidr/lang/types/OCRealType.b:(Ljava/lang/NumberFormatException;)Ljava/lang/NumberFormatException;
        //   141: athrow         
        //   142: getstatic       com/jetbrains/cidr/lang/types/OCRealType.FLOAT:Lcom/jetbrains/cidr/lang/types/OCRealType;
        //   145: dup            
        //   146: ifnonnull       190
        //   149: goto            156
        //   152: invokestatic    com/jetbrains/cidr/lang/types/OCRealType.b:(Ljava/lang/NumberFormatException;)Ljava/lang/NumberFormatException;
        //   155: athrow         
        //   156: new             Ljava/lang/IllegalStateException;
        //   159: dup            
        //   160: ldc             "@NotNull method %s.%s must not return null"
        //   162: ldc             2
        //   164: anewarray       Ljava/lang/Object;
        //   167: dup            
        //   168: ldc             0
        //   170: ldc             "com/jetbrains/cidr/lang/types/OCRealType"
        //   172: aastore        
        //   173: dup            
        //   174: ldc             1
        //   176: ldc             "literalType"
        //   178: aastore        
        //   179: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   182: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   185: athrow         
        //   186: invokestatic    com/jetbrains/cidr/lang/types/OCRealType.b:(Ljava/lang/NumberFormatException;)Ljava/lang/NumberFormatException;
        //   189: athrow         
        //   190: areturn        
        //   191: getstatic       com/jetbrains/cidr/lang/types/OCRealType.DOUBLE:Lcom/jetbrains/cidr/lang/types/OCRealType;
        //   194: dup            
        //   195: ifnonnull       232
        //   198: new             Ljava/lang/IllegalStateException;
        //   201: dup            
        //   202: ldc             "@NotNull method %s.%s must not return null"
        //   204: ldc             2
        //   206: anewarray       Ljava/lang/Object;
        //   209: dup            
        //   210: ldc             0
        //   212: ldc             "com/jetbrains/cidr/lang/types/OCRealType"
        //   214: aastore        
        //   215: dup            
        //   216: ldc             1
        //   218: ldc             "literalType"
        //   220: aastore        
        //   221: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   224: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   227: athrow         
        //   228: invokestatic    com/jetbrains/cidr/lang/types/OCRealType.b:(Ljava/lang/NumberFormatException;)Ljava/lang/NumberFormatException;
        //   231: athrow         
        //   232: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      40     40     44     Ljava/lang/NumberFormatException;
        //  55     67     70     74     Ljava/lang/NumberFormatException;
        //  61     81     84     88     Ljava/lang/NumberFormatException;
        //  74     118    118    122    Ljava/lang/NumberFormatException;
        //  123    135    138    142    Ljava/lang/NumberFormatException;
        //  129    149    152    156    Ljava/lang/NumberFormatException;
        //  142    186    186    190    Ljava/lang/NumberFormatException;
        //  191    228    228    232    Ljava/lang/NumberFormatException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0074:
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
    
    public static OCRealType narrowestLiteralType(final String s) {
        try {
            if (!Character.isDigit(s.charAt(s.length() - 1))) {
                return literalType(s);
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        try {
            if (new BigDecimal(s, MathContext.DECIMAL64).equals(new BigDecimal(s, MathContext.DECIMAL32))) {
                return OCRealType.FLOAT;
            }
        }
        catch (NumberFormatException ex2) {}
        return OCRealType.DOUBLE;
    }
    
    private OCRealType(final CTypeId cTypeId, final String s, final String s2, final boolean b) {
        this(cTypeId, s, s2, b, false, false);
    }
    
    private OCRealType(final CTypeId myTypeId, final String myText, final String myFormatString, final boolean myComplex, final boolean b, final boolean b2) {
        super(b, b2);
        this.myFormatString = myFormatString;
        this.myTypeId = myTypeId;
        this.myText = myText;
        this.myComplex = myComplex;
    }
    
    public OCRealType cloneType(final boolean b, final boolean b2) {
        return new OCRealType(this.getCTypeId(), this.getText(), this.getFormatString(), this.isComplex(), b, b2);
    }
    
    @Override
    public boolean isComplex() {
        return this.myComplex;
    }
    
    public String getText() {
        return this.myText;
    }
    
    public OCRealType cloneWithComplexModifier() {
        try {
            if (this.myComplex) {
                return this;
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        final OCRealType ocRealType = (OCRealType)this.getShallowCopy();
        ocRealType.myComplex = true;
        return ocRealType;
    }
    
    public OCRealType cloneWithoutComplexModifier() {
        try {
            if (!this.myComplex) {
                return this;
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        final OCRealType ocRealType = (OCRealType)this.getShallowCopy();
        ocRealType.myComplex = false;
        return ocRealType;
    }
    
    @Override
    public <T> T accept(final OCTypeVisitor<T> ocTypeVisitor) {
        return ocTypeVisitor.visitRealType(this);
    }
    
    @Override
    public int getRank(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/types/OCRealType", "getRank"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        return this.getSizeInBytes(psiElement.getContainingFile(), null) << 6;
    }
    
    @Override
    public boolean isSigned() {
        return true;
    }
    
    @Override
    public String getFormatString() {
        return this.myFormatString;
    }
    
    @NotNull
    @Override
    public String getDefaultValue(final PsiElement psiElement) {
        String s;
        try {
            s = "0";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCRealType", "getDefaultValue"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        return s;
    }
    
    @Override
    public int hashCode() {
        try {
            if (this.myComplex) {
                final int n = 997;
                return n * super.hashCode();
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        final int n = 991;
        return n * super.hashCode();
    }
    
    static {
        FLOAT = new OCRealType(CTypeId.FLOAT, "float", "%f", false);
        DOUBLE = new OCRealType(CTypeId.DOUBLE, "double", "%lf", false);
        LONG_DOUBLE = new OCRealType(CTypeId.LONG_DOUBLE, "long double", "%Lf", false);
        COMPLEX_FLOAT = new OCRealType(CTypeId.FLOAT, "float", null, true);
        COMPLEX_DOUBLE = new OCRealType(CTypeId.DOUBLE, "double", null, true);
        COMPLEX_LONG_DOUBLE = new OCRealType(CTypeId.LONG_DOUBLE, "long double", null, true);
    }
    
    private static NumberFormatException b(final NumberFormatException ex) {
        return ex;
    }
}
