// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import com.jetbrains.cidr.lang.types.visitors.OCTypeVisitor;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCLambdaExpression;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.symbols.expression.OCExpressionSymbol;
import com.jetbrains.cidr.lang.types.visitors.OCTypeSubstitution;
import org.jetbrains.annotations.Nullable;

public class OCAutoType extends OCType
{
    @Nullable
    private OCType myIncompleteType;
    private OCTypeSubstitution mySubstitution;
    private OCExpressionSymbol myExpressionSymbol;
    private OCExpression myExpressionElement;
    private OCLambdaExpression myLambdaExpression;
    private int myParameterIndex;
    private boolean myNeedsAutoParamsResolving;
    
    public OCAutoType(@Nullable final OCExpressionSymbol myExpressionSymbol, @Nullable final OCExpression myExpressionElement, @Nullable final OCType myIncompleteType, final OCLambdaExpression myLambdaExpression, final int myParameterIndex, final boolean myNeedsAutoParamsResolving, final boolean b, final boolean b2) {
        super(b, b2);
        this.mySubstitution = OCTypeSubstitution.ID;
        this.myParameterIndex = -1;
        this.myIncompleteType = myIncompleteType;
        this.myExpressionSymbol = myExpressionSymbol;
        this.myExpressionElement = myExpressionElement;
        this.myLambdaExpression = myLambdaExpression;
        this.myParameterIndex = myParameterIndex;
        this.myNeedsAutoParamsResolving = myNeedsAutoParamsResolving;
    }
    
    public OCAutoType(@Nullable final OCExpressionSymbol myExpressionSymbol, @Nullable final OCExpression myExpressionElement, @Nullable final OCType myIncompleteType, final boolean b, final boolean b2) {
        super(b, b2);
        this.mySubstitution = OCTypeSubstitution.ID;
        this.myParameterIndex = -1;
        this.myExpressionSymbol = myExpressionSymbol;
        this.myExpressionElement = myExpressionElement;
        this.myIncompleteType = myIncompleteType;
    }
    
    public OCAutoType(@NotNull final OCAutoType ocAutoType, final OCTypeSubstitution ocTypeSubstitution, @NotNull final OCResolveContext ocResolveContext) {
        if (ocAutoType == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "origin", "com/jetbrains/cidr/lang/types/OCAutoType", "<init>"));
        }
        if (ocResolveContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/OCAutoType", "<init>"));
        }
        this.mySubstitution = OCTypeSubstitution.ID;
        this.myParameterIndex = -1;
        this.myIncompleteType = ocAutoType.getIncompleteType();
        this.myExpressionSymbol = ocAutoType.getExpressionSymbol();
        this.myExpressionElement = ocAutoType.getExpressionElement();
        this.mySubstitution = OCTypeSubstitution.compose(ocAutoType.mySubstitution, ocTypeSubstitution, ocResolveContext);
    }
    
    public OCAutoType(final OCLambdaExpression myLambdaExpression, final int myParameterIndex) {
        this.mySubstitution = OCTypeSubstitution.ID;
        this.myParameterIndex = -1;
        this.myLambdaExpression = myLambdaExpression;
        this.myParameterIndex = myParameterIndex;
    }
    
    public OCAutoType() {
        this.mySubstitution = OCTypeSubstitution.ID;
        this.myParameterIndex = -1;
    }
    
    public OCTypeSubstitution getSubstitution() {
        return this.mySubstitution;
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/types/OCAutoType", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/types/OCAutoType", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/types/OCAutoType", "deepEqualStep"));
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
        final OCAutoType ocAutoType = (OCAutoType)o;
        final OCAutoType ocAutoType2 = (OCAutoType)o2;
        try {
            if (!comparator.equalObjects(ocAutoType.myExpressionSymbol, (DeepEqual.Equality<Object>)ocAutoType2.myExpressionSymbol)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        try {
            if (!comparator.equalObjects(ocAutoType.mySubstitution, (DeepEqual.Equality<Object>)ocAutoType2.mySubstitution)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        try {
            if (this.myExpressionElement != ocAutoType2.myExpressionElement) {
                return false;
            }
        }
        catch (IllegalArgumentException ex7) {
            throw a(ex7);
        }
        try {
            if (this.myLambdaExpression != ocAutoType2.myLambdaExpression) {
                return false;
            }
        }
        catch (IllegalArgumentException ex8) {
            throw a(ex8);
        }
        try {
            if (this.myParameterIndex != ocAutoType2.myParameterIndex) {
                return false;
            }
        }
        catch (IllegalArgumentException ex9) {
            throw a(ex9);
        }
        try {
            if (this.myNeedsAutoParamsResolving != ocAutoType2.myNeedsAutoParamsResolving) {
                return false;
            }
        }
        catch (IllegalArgumentException ex10) {
            throw a(ex10);
        }
        return true;
    }
    
    @Nullable
    public OCType getIncompleteType() {
        return this.myIncompleteType;
    }
    
    @Nullable
    public OCExpressionSymbol getExpressionSymbol() {
        return this.myExpressionSymbol;
    }
    
    public void setExpressionSymbol(@NotNull final OCExpressionSymbol myExpressionSymbol) {
        try {
            if (myExpressionSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expressionSymbol", "com/jetbrains/cidr/lang/types/OCAutoType", "setExpressionSymbol"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.myExpressionSymbol = myExpressionSymbol;
    }
    
    public OCLambdaExpression getLambdaExpression() {
        return this.myLambdaExpression;
    }
    
    public int getParameterIndex() {
        return this.myParameterIndex;
    }
    
    public boolean needsAutoParamsResolving() {
        return this.myNeedsAutoParamsResolving;
    }
    
    public void setNeedsAutoParamsResolving(final boolean myNeedsAutoParamsResolving) {
        this.myNeedsAutoParamsResolving = myNeedsAutoParamsResolving;
    }
    
    @Nullable
    public OCExpression getExpressionElement() {
        return this.myExpressionElement;
    }
    
    @Override
    public boolean isInstanceable() {
        return true;
    }
    
    @Override
    public boolean isMagicInside(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/OCAutoType", "isMagicInside"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return true;
    }
    
    @Override
    public boolean isUnknown() {
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
        //    24: ldc             "com/jetbrains/cidr/lang/types/OCAutoType"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isUnresolved"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/types/OCAutoType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: getfield        com/jetbrains/cidr/lang/types/OCAutoType.myLambdaExpression:Lcom/jetbrains/cidr/lang/psi/OCLambdaExpression;
        //    48: ifnull          66
        //    51: aload_0        
        //    52: getfield        com/jetbrains/cidr/lang/types/OCAutoType.myParameterIndex:I
        //    55: iconst_m1      
        //    56: if_icmpne       102
        //    59: goto            66
        //    62: invokestatic    com/jetbrains/cidr/lang/types/OCAutoType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    65: athrow         
        //    66: aload_0        
        //    67: getfield        com/jetbrains/cidr/lang/types/OCAutoType.myExpressionSymbol:Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //    70: ifnull          94
        //    73: goto            80
        //    76: invokestatic    com/jetbrains/cidr/lang/types/OCAutoType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    79: athrow         
        //    80: aload_0        
        //    81: getfield        com/jetbrains/cidr/lang/types/OCAutoType.myNeedsAutoParamsResolving:Z
        //    84: ifne            102
        //    87: goto            94
        //    90: invokestatic    com/jetbrains/cidr/lang/types/OCAutoType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    93: athrow         
        //    94: iconst_1       
        //    95: goto            103
        //    98: invokestatic    com/jetbrains/cidr/lang/types/OCAutoType.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   101: athrow         
        //   102: iconst_0       
        //   103: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     59     62     66     Ljava/lang/IllegalArgumentException;
        //  51     73     76     80     Ljava/lang/IllegalArgumentException;
        //  66     87     90     94     Ljava/lang/IllegalArgumentException;
        //  80     98     98     102    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0066:
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
        return ocTypeVisitor.visitAutoType(this);
    }
    
    @Override
    public int hashCode() {
        final int baseHashCode = this.baseHashCode();
        int n = 0;
        int hashCode = 0;
        Label_0031: {
            try {
                n = 31 * baseHashCode;
                if (this.myIncompleteType != null) {
                    hashCode = this.myIncompleteType.hashCode();
                    break Label_0031;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            hashCode = 0;
        }
        final int n2 = n + hashCode;
        int n3 = 0;
        int hashCode2 = 0;
        Label_0059: {
            try {
                n3 = 31 * n2;
                if (this.mySubstitution != null) {
                    hashCode2 = this.mySubstitution.hashCode();
                    break Label_0059;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            hashCode2 = 0;
        }
        final int n4 = n3 + hashCode2;
        int n5 = 0;
        int hashCode3 = 0;
        Label_0087: {
            try {
                n5 = 31 * n4;
                if (this.myExpressionSymbol != null) {
                    hashCode3 = this.myExpressionSymbol.hashCode();
                    break Label_0087;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            hashCode3 = 0;
        }
        final int n6 = 31 * (n5 + hashCode3) + this.myParameterIndex;
        int n7;
        try {
            n7 = 31 * n6;
            if (this.myNeedsAutoParamsResolving) {
                final int n8 = 0;
                return n7 + n8;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        final int n8 = 1;
        return n7 + n8;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
