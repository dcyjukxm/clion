// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContext;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;

public abstract class OCNumericType extends OCType
{
    protected CTypeId myTypeId;
    
    public abstract int getRank(@NotNull final PsiElement p0);
    
    public abstract boolean isSigned();
    
    public abstract boolean isComplex();
    
    public int getBits(@Nullable final PsiElement psiElement, @Nullable final OCInclusionContext ocInclusionContext) {
        try {
            if (psiElement == null) {
                final PsiFile containingFile = null;
                return this.getSizeInBytes(containingFile, ocInclusionContext) * 8;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final PsiFile containingFile = psiElement.getContainingFile();
        return this.getSizeInBytes(containingFile, ocInclusionContext) * 8;
    }
    
    @Override
    public int getSizeInBytes(@Nullable final PsiFile psiFile, @Nullable final OCInclusionContext ocInclusionContext) {
        return this.myTypeId.getBytes(psiFile, ocInclusionContext);
    }
    
    @NotNull
    public CTypeId getCTypeId() {
        CTypeId myTypeId;
        try {
            myTypeId = this.myTypeId;
            if (myTypeId == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/OCNumericType", "getCTypeId"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return myTypeId;
    }
    
    public OCNumericType() {
    }
    
    public OCNumericType(final boolean b, final boolean b2) {
        super(b, b2);
    }
    
    @NotNull
    @Override
    protected OCType doGetLeastCommonType(final OCType p0, @NotNull final PsiElement p1) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/types/OCNumericType"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "doGetLeastCommonType"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/types/OCNumericType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    43: athrow         
        //    44: aload_1        
        //    45: ifnonnull       97
        //    48: getstatic       com/jetbrains/cidr/lang/types/OCUnknownType.INSTANCE:Lcom/jetbrains/cidr/lang/types/OCUnknownType;
        //    51: dup            
        //    52: ifnonnull       96
        //    55: goto            62
        //    58: invokestatic    com/jetbrains/cidr/lang/types/OCNumericType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    61: athrow         
        //    62: new             Ljava/lang/IllegalStateException;
        //    65: dup            
        //    66: ldc             "@NotNull method %s.%s must not return null"
        //    68: ldc             2
        //    70: anewarray       Ljava/lang/Object;
        //    73: dup            
        //    74: ldc             0
        //    76: ldc             "com/jetbrains/cidr/lang/types/OCNumericType"
        //    78: aastore        
        //    79: dup            
        //    80: ldc             1
        //    82: ldc             "doGetLeastCommonType"
        //    84: aastore        
        //    85: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    88: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    91: athrow         
        //    92: invokestatic    com/jetbrains/cidr/lang/types/OCNumericType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    95: athrow         
        //    96: areturn        
        //    97: aload_1        
        //    98: instanceof      Lcom/jetbrains/cidr/lang/types/OCNumericType;
        //   101: ifeq            254
        //   104: aload_0        
        //   105: aload_2        
        //   106: invokevirtual   com/jetbrains/cidr/lang/types/OCNumericType.getRank:(Lcom/intellij/psi/PsiElement;)I
        //   109: aload_1        
        //   110: checkcast       Lcom/jetbrains/cidr/lang/types/OCNumericType;
        //   113: aload_2        
        //   114: invokevirtual   com/jetbrains/cidr/lang/types/OCNumericType.getRank:(Lcom/intellij/psi/PsiElement;)I
        //   117: if_icmpge       135
        //   120: goto            127
        //   123: invokestatic    com/jetbrains/cidr/lang/types/OCNumericType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   126: athrow         
        //   127: aload_1        
        //   128: goto            136
        //   131: invokestatic    com/jetbrains/cidr/lang/types/OCNumericType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   134: athrow         
        //   135: aload_0        
        //   136: astore_3       
        //   137: aload_0        
        //   138: invokevirtual   com/jetbrains/cidr/lang/types/OCNumericType.isComplex:()Z
        //   141: ifne            161
        //   144: aload_1        
        //   145: checkcast       Lcom/jetbrains/cidr/lang/types/OCNumericType;
        //   148: invokevirtual   com/jetbrains/cidr/lang/types/OCNumericType.isComplex:()Z
        //   151: ifeq            214
        //   154: goto            161
        //   157: invokestatic    com/jetbrains/cidr/lang/types/OCNumericType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   160: athrow         
        //   161: aload_3        
        //   162: checkcast       Lcom/jetbrains/cidr/lang/types/OCRealType;
        //   165: invokevirtual   com/jetbrains/cidr/lang/types/OCRealType.cloneWithComplexModifier:()Lcom/jetbrains/cidr/lang/types/OCRealType;
        //   168: dup            
        //   169: ifnonnull       213
        //   172: goto            179
        //   175: invokestatic    com/jetbrains/cidr/lang/types/OCNumericType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   178: athrow         
        //   179: new             Ljava/lang/IllegalStateException;
        //   182: dup            
        //   183: ldc             "@NotNull method %s.%s must not return null"
        //   185: ldc             2
        //   187: anewarray       Ljava/lang/Object;
        //   190: dup            
        //   191: ldc             0
        //   193: ldc             "com/jetbrains/cidr/lang/types/OCNumericType"
        //   195: aastore        
        //   196: dup            
        //   197: ldc             1
        //   199: ldc             "doGetLeastCommonType"
        //   201: aastore        
        //   202: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   205: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   208: athrow         
        //   209: invokestatic    com/jetbrains/cidr/lang/types/OCNumericType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   212: athrow         
        //   213: areturn        
        //   214: aload_3        
        //   215: dup            
        //   216: ifnonnull       253
        //   219: new             Ljava/lang/IllegalStateException;
        //   222: dup            
        //   223: ldc             "@NotNull method %s.%s must not return null"
        //   225: ldc             2
        //   227: anewarray       Ljava/lang/Object;
        //   230: dup            
        //   231: ldc             0
        //   233: ldc             "com/jetbrains/cidr/lang/types/OCNumericType"
        //   235: aastore        
        //   236: dup            
        //   237: ldc             1
        //   239: ldc             "doGetLeastCommonType"
        //   241: aastore        
        //   242: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   245: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   248: athrow         
        //   249: invokestatic    com/jetbrains/cidr/lang/types/OCNumericType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   252: athrow         
        //   253: areturn        
        //   254: aload_1        
        //   255: aload_2        
        //   256: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerCompatible:(Lcom/intellij/psi/PsiElement;)Z
        //   259: ifeq            309
        //   262: aload_1        
        //   263: dup            
        //   264: ifnonnull       308
        //   267: goto            274
        //   270: invokestatic    com/jetbrains/cidr/lang/types/OCNumericType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   273: athrow         
        //   274: new             Ljava/lang/IllegalStateException;
        //   277: dup            
        //   278: ldc             "@NotNull method %s.%s must not return null"
        //   280: ldc             2
        //   282: anewarray       Ljava/lang/Object;
        //   285: dup            
        //   286: ldc             0
        //   288: ldc             "com/jetbrains/cidr/lang/types/OCNumericType"
        //   290: aastore        
        //   291: dup            
        //   292: ldc             1
        //   294: ldc             "doGetLeastCommonType"
        //   296: aastore        
        //   297: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   300: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   303: athrow         
        //   304: invokestatic    com/jetbrains/cidr/lang/types/OCNumericType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   307: athrow         
        //   308: areturn        
        //   309: aload_1        
        //   310: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   313: ifeq            383
        //   316: aload_1        
        //   317: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   320: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   323: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.ENUM:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   326: if_acmpne       383
        //   329: goto            336
        //   332: invokestatic    com/jetbrains/cidr/lang/types/OCNumericType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   335: athrow         
        //   336: aload_0        
        //   337: dup            
        //   338: ifnonnull       382
        //   341: goto            348
        //   344: invokestatic    com/jetbrains/cidr/lang/types/OCNumericType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   347: athrow         
        //   348: new             Ljava/lang/IllegalStateException;
        //   351: dup            
        //   352: ldc             "@NotNull method %s.%s must not return null"
        //   354: ldc             2
        //   356: anewarray       Ljava/lang/Object;
        //   359: dup            
        //   360: ldc             0
        //   362: ldc             "com/jetbrains/cidr/lang/types/OCNumericType"
        //   364: aastore        
        //   365: dup            
        //   366: ldc             1
        //   368: ldc             "doGetLeastCommonType"
        //   370: aastore        
        //   371: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   374: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   377: athrow         
        //   378: invokestatic    com/jetbrains/cidr/lang/types/OCNumericType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   381: athrow         
        //   382: areturn        
        //   383: getstatic       com/jetbrains/cidr/lang/types/OCUnknownType.INSTANCE:Lcom/jetbrains/cidr/lang/types/OCUnknownType;
        //   386: dup            
        //   387: ifnonnull       424
        //   390: new             Ljava/lang/IllegalStateException;
        //   393: dup            
        //   394: ldc             "@NotNull method %s.%s must not return null"
        //   396: ldc             2
        //   398: anewarray       Ljava/lang/Object;
        //   401: dup            
        //   402: ldc             0
        //   404: ldc             "com/jetbrains/cidr/lang/types/OCNumericType"
        //   406: aastore        
        //   407: dup            
        //   408: ldc             1
        //   410: ldc             "doGetLeastCommonType"
        //   412: aastore        
        //   413: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   416: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   419: athrow         
        //   420: invokestatic    com/jetbrains/cidr/lang/types/OCNumericType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   423: athrow         
        //   424: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      40     40     44     Ljava/lang/IllegalStateException;
        //  44     55     58     62     Ljava/lang/IllegalStateException;
        //  48     92     92     96     Ljava/lang/IllegalStateException;
        //  97     120    123    127    Ljava/lang/IllegalStateException;
        //  104    131    131    135    Ljava/lang/IllegalStateException;
        //  137    154    157    161    Ljava/lang/IllegalStateException;
        //  144    172    175    179    Ljava/lang/IllegalStateException;
        //  161    209    209    213    Ljava/lang/IllegalStateException;
        //  214    249    249    253    Ljava/lang/IllegalStateException;
        //  254    267    270    274    Ljava/lang/IllegalStateException;
        //  262    304    304    308    Ljava/lang/IllegalStateException;
        //  309    329    332    336    Ljava/lang/IllegalStateException;
        //  316    341    344    348    Ljava/lang/IllegalStateException;
        //  336    378    378    382    Ljava/lang/IllegalStateException;
        //  383    420    420    424    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0161:
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
    public String getAliasName() {
        Label_0026: {
            try {
                if (this.myAliasName == null) {
                    return this.myAliasName;
                }
                final OCNumericType ocNumericType = this;
                final String s = ocNumericType.myAliasName;
                final String s2 = "<";
                final boolean b = s.contains(s2);
                if (b) {
                    break Label_0026;
                }
                return this.myAliasName;
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            try {
                final OCNumericType ocNumericType = this;
                final String s = ocNumericType.myAliasName;
                final String s2 = "<";
                final boolean b = s.contains(s2);
                if (b) {
                    return null;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        return this.myAliasName;
    }
    
    @Override
    public boolean isNumberCompatible(final PsiElement psiElement) {
        return true;
    }
    
    @Override
    public boolean isScalar() {
        return true;
    }
    
    @Override
    public boolean isInstanceable() {
        return true;
    }
    
    @Override
    public int hashCode() {
        return this.baseHashCode() * 31 + this.myTypeId.hashCode();
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
