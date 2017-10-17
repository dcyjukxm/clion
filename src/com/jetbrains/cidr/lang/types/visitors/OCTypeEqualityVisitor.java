// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import com.intellij.util.containers.FactoryMap;
import com.jetbrains.cidr.lang.types.OCVoidType;
import com.jetbrains.cidr.lang.types.OCUnknownType;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import com.jetbrains.cidr.lang.types.OCExpansionPackType;
import com.jetbrains.cidr.lang.types.OCVariadicType;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import java.util.Iterator;
import com.intellij.util.Function;
import java.util.Set;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.jetbrains.cidr.lang.types.OCRealType;
import com.jetbrains.cidr.lang.types.OCIntType;
import com.jetbrains.cidr.lang.types.OCIdType;
import com.jetbrains.cidr.lang.types.OCCppReferenceType;
import com.jetbrains.cidr.lang.types.OCBlockPointerType;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.types.OCArrayType;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.types.OCAutoType;
import com.jetbrains.cidr.lang.types.OCTypeParameterType;
import com.jetbrains.cidr.lang.types.OCMagicType;
import java.util.List;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import com.jetbrains.cidr.lang.types.OCEllipsisType;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.types.OCType;

public class OCTypeEqualityVisitor implements OCTypeVisitor<Boolean>
{
    protected OCType myType;
    private final boolean myAssumeMagicTypesEquals;
    private final boolean myAssumeDifferentSubstitutionsEquals;
    private final boolean myAssumeNonExpandedVariadicsEquals;
    private final boolean myAssumeNullSubstitutionsEquals;
    private final boolean myAssumeTypeParametersEquals;
    private final boolean myEvaluateExpressionSubstitutions;
    protected final boolean myDontCheckCV;
    @NotNull
    protected final OCResolveContext myContext;
    
    public OCTypeEqualityVisitor(final OCType ocType, final boolean b, final boolean b2, final boolean b3, final boolean b4, @NotNull final OCResolveContext ocResolveContext) {
        if (ocResolveContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor", "<init>"));
        }
        this(ocType, b, b2, false, false, b3, true, b4, ocResolveContext);
    }
    
    public OCTypeEqualityVisitor(final OCType myType, final boolean myAssumeMagicTypesEquals, final boolean myAssumeDifferentSubstitutionsEquals, final boolean myAssumeNonExpandedVariadicsEquals, final boolean myAssumeNullSubstitutionsEquals, final boolean myAssumeTypeParametersEquals, final boolean myEvaluateExpressionSubstitutions, final boolean myDontCheckCV, @NotNull final OCResolveContext myContext) {
        if (myContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor", "<init>"));
        }
        this.myType = myType;
        this.myAssumeMagicTypesEquals = myAssumeMagicTypesEquals;
        this.myAssumeDifferentSubstitutionsEquals = myAssumeDifferentSubstitutionsEquals;
        this.myAssumeNonExpandedVariadicsEquals = myAssumeNonExpandedVariadicsEquals;
        this.myAssumeNullSubstitutionsEquals = myAssumeNullSubstitutionsEquals;
        this.myAssumeTypeParametersEquals = myAssumeTypeParametersEquals;
        this.myEvaluateExpressionSubstitutions = myEvaluateExpressionSubstitutions;
        this.myDontCheckCV = myDontCheckCV;
        this.myContext = myContext;
    }
    
    public OCTypeEqualityVisitor(final OCType ocType, final boolean b, @NotNull final OCResolveContext ocResolveContext) {
        if (ocResolveContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor", "<init>"));
        }
        this(ocType, b, false, false, false, ocResolveContext);
    }
    
    public boolean equal(final OCType ocType) {
        try {
            if (!this.myDontCheckCV) {
                final boolean b = true;
                return this.equal(ocType, b);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final boolean b = false;
        return this.equal(ocType, b);
    }
    
    public boolean equal(final OCType p0, final boolean p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iload_2        
        //     1: ifeq            31
        //     4: aload_0        
        //     5: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.myType:Lcom/jetbrains/cidr/lang/types/OCType;
        //     8: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isConst:()Z
        //    11: aload_1        
        //    12: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isConst:()Z
        //    15: if_icmpeq       31
        //    18: goto            25
        //    21: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    24: athrow         
        //    25: iconst_0       
        //    26: ireturn        
        //    27: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    30: athrow         
        //    31: aload_0        
        //    32: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.myAssumeMagicTypesEquals:Z
        //    35: ifeq            75
        //    38: aload_0        
        //    39: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.myType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    42: instanceof      Lcom/jetbrains/cidr/lang/types/OCMagicType;
        //    45: ifne            69
        //    48: goto            55
        //    51: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    54: athrow         
        //    55: aload_1        
        //    56: instanceof      Lcom/jetbrains/cidr/lang/types/OCMagicType;
        //    59: ifeq            75
        //    62: goto            69
        //    65: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    68: athrow         
        //    69: iconst_1       
        //    70: ireturn        
        //    71: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    74: athrow         
        //    75: aload_0        
        //    76: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.myAssumeTypeParametersEquals:Z
        //    79: ifeq            119
        //    82: aload_0        
        //    83: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.myType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    86: instanceof      Lcom/jetbrains/cidr/lang/types/OCTypeParameterType;
        //    89: ifne            113
        //    92: goto            99
        //    95: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    98: athrow         
        //    99: aload_1        
        //   100: instanceof      Lcom/jetbrains/cidr/lang/types/OCTypeParameterType;
        //   103: ifeq            119
        //   106: goto            113
        //   109: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   112: athrow         
        //   113: iconst_1       
        //   114: ireturn        
        //   115: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   118: athrow         
        //   119: iload_2        
        //   120: ifeq            150
        //   123: aload_0        
        //   124: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.myType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   127: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isVolatile:()Z
        //   130: aload_1        
        //   131: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isVolatile:()Z
        //   134: if_icmpeq       150
        //   137: goto            144
        //   140: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   143: athrow         
        //   144: iconst_0       
        //   145: ireturn        
        //   146: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   149: athrow         
        //   150: aload_1        
        //   151: aload_0        
        //   152: invokevirtual   com/jetbrains/cidr/lang/types/OCType.accept:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeVisitor;)Ljava/lang/Object;
        //   155: checkcast       Ljava/lang/Boolean;
        //   158: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   161: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      18     21     25     Ljava/lang/IllegalArgumentException;
        //  4      27     27     31     Ljava/lang/IllegalArgumentException;
        //  31     48     51     55     Ljava/lang/IllegalArgumentException;
        //  38     62     65     69     Ljava/lang/IllegalArgumentException;
        //  55     71     71     75     Ljava/lang/IllegalArgumentException;
        //  75     92     95     99     Ljava/lang/IllegalArgumentException;
        //  82     106    109    113    Ljava/lang/IllegalArgumentException;
        //  99     115    115    119    Ljava/lang/IllegalArgumentException;
        //  119    137    140    144    Ljava/lang/IllegalArgumentException;
        //  123    146    146    150    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0055:
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
    
    protected boolean equal(@NotNull final OCType ocType, @NotNull final OCType ocType2) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type1", "com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor", "equal"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocType2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type2", "com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor", "equal"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (!this.myDontCheckCV) {
                final boolean b = true;
                return this.equal(ocType, ocType2, b);
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final boolean b = false;
        return this.equal(ocType, ocType2, b);
    }
    
    protected boolean equal(@NotNull final OCType ocType, @NotNull final OCType myType, final boolean b) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type1", "com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor", "equal"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (myType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type2", "com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor", "equal"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final OCType myType2 = this.myType;
        this.myType = myType;
        final boolean equal = this.equal(ocType, b);
        this.myType = myType2;
        return equal;
    }
    
    @Override
    public Boolean visitEllipsisReferenceType(final OCEllipsisType ocEllipsisType) {
        Label_0028: {
            try {
                if (this.myType == null) {
                    break Label_0028;
                }
                final OCEllipsisType ocEllipsisType2 = ocEllipsisType;
                final Class<? extends OCEllipsisType> clazz = ocEllipsisType2.getClass();
                final OCTypeEqualityVisitor ocTypeEqualityVisitor = this;
                final OCType ocType = ocTypeEqualityVisitor.myType;
                final Class<? extends OCType> clazz2 = ocType.getClass();
                if (clazz == clazz2) {
                    break Label_0028;
                }
                break Label_0028;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCEllipsisType ocEllipsisType2 = ocEllipsisType;
                final Class<? extends OCEllipsisType> clazz = ocEllipsisType2.getClass();
                final OCTypeEqualityVisitor ocTypeEqualityVisitor = this;
                final OCType ocType = ocTypeEqualityVisitor.myType;
                final Class<? extends OCType> clazz2 = ocType.getClass();
                if (clazz == clazz2) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    @Override
    public Boolean visitFunctionType(final OCFunctionType ocFunctionType) {
        try {
            if (ocFunctionType == this.myType) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0045: {
            try {
                if (this.myType == null) {
                    break Label_0045;
                }
                final OCFunctionType ocFunctionType2 = ocFunctionType;
                final Class<? extends OCFunctionType> clazz = ocFunctionType2.getClass();
                final OCTypeEqualityVisitor ocTypeEqualityVisitor = this;
                final OCType ocType = ocTypeEqualityVisitor.myType;
                final Class<? extends OCType> clazz2 = ocType.getClass();
                if (clazz != clazz2) {
                    break Label_0045;
                }
                break Label_0045;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final OCFunctionType ocFunctionType2 = ocFunctionType;
                final Class<? extends OCFunctionType> clazz = ocFunctionType2.getClass();
                final OCTypeEqualityVisitor ocTypeEqualityVisitor = this;
                final OCType ocType = ocTypeEqualityVisitor.myType;
                final Class<? extends OCType> clazz2 = ocType.getClass();
                if (clazz != clazz2) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        final OCFunctionType ocFunctionType3 = (OCFunctionType)this.myType;
        try {
            if (!this.isFunctionSignatureEqual(ocFunctionType)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        try {
            if (!this.equal(ocFunctionType.getReturnType(), ocFunctionType3.getReturnType(), false)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        return true;
    }
    
    public boolean isFunctionSignatureEqual(final OCType ocType) {
        try {
            if (!(ocType instanceof OCFunctionType)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (!(this.myType instanceof OCFunctionType)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (this.myType.isConst() != ocType.isConst()) {
                return false;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (this.myType.isVolatile() != ocType.isVolatile()) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        final OCFunctionType ocFunctionType = (OCFunctionType)ocType;
        final OCFunctionType ocFunctionType2 = (OCFunctionType)this.myType;
        final List<OCType> parameterTypes = ocFunctionType.getParameterTypes();
        final List<OCType> parameterTypes2 = ocFunctionType2.getParameterTypes();
        try {
            if (((OCFunctionType)ocType).isLValueRef() != ((OCFunctionType)this.myType).isLValueRef()) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        try {
            if (((OCFunctionType)ocType).isRValueRef() != ((OCFunctionType)this.myType).isRValueRef()) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        try {
            if (parameterTypes.size() != parameterTypes2.size()) {
                return false;
            }
        }
        catch (IllegalArgumentException ex7) {
            throw a(ex7);
        }
        try {
            if (ocFunctionType.isVararg() != ocFunctionType2.isVararg()) {
                return false;
            }
        }
        catch (IllegalArgumentException ex8) {
            throw a(ex8);
        }
        int n = 0;
        while (true) {
            Label_0246: {
                try {
                    if (n >= parameterTypes.size()) {
                        break;
                    }
                    final OCTypeEqualityVisitor ocTypeEqualityVisitor = this;
                    final List<OCType> list = parameterTypes;
                    final int n2 = n;
                    final OCType ocType2 = list.get(n2);
                    final OCType ocType3 = ocType2;
                    final List<OCType> list2 = parameterTypes2;
                    final int n3 = n;
                    final OCType ocType4 = list2.get(n3);
                    final OCType ocType5 = ocType4;
                    final boolean b = false;
                    final boolean b2 = ocTypeEqualityVisitor.equal(ocType3, ocType5, b);
                    if (!b2) {
                        return false;
                    }
                    break Label_0246;
                }
                catch (IllegalArgumentException ex9) {
                    throw a(ex9);
                }
                try {
                    final OCTypeEqualityVisitor ocTypeEqualityVisitor = this;
                    final List<OCType> list = parameterTypes;
                    final int n2 = n;
                    final OCType ocType2 = list.get(n2);
                    final OCType ocType3 = ocType2;
                    final List<OCType> list2 = parameterTypes2;
                    final int n3 = n;
                    final OCType ocType4 = list2.get(n3);
                    final OCType ocType5 = ocType4;
                    final boolean b = false;
                    final boolean b2 = ocTypeEqualityVisitor.equal(ocType3, ocType5, b);
                    if (!b2) {
                        return false;
                    }
                }
                catch (IllegalArgumentException ex10) {
                    throw a(ex10);
                }
            }
            ++n;
        }
        return true;
    }
    
    @Override
    public Boolean visitMagicType(final OCMagicType ocMagicType) {
        Label_0072: {
            Label_0037: {
                Label_0028: {
                    try {
                        if (this.myType == null) {
                            break Label_0028;
                        }
                        final OCMagicType ocMagicType2 = ocMagicType;
                        final Class<? extends OCMagicType> clazz = ocMagicType2.getClass();
                        final OCTypeEqualityVisitor ocTypeEqualityVisitor = this;
                        final OCType ocType = ocTypeEqualityVisitor.myType;
                        final Class<? extends OCType> clazz2 = ocType.getClass();
                        if (clazz != clazz2) {
                            break Label_0028;
                        }
                        break Label_0037;
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        final OCMagicType ocMagicType2 = ocMagicType;
                        final Class<? extends OCMagicType> clazz = ocMagicType2.getClass();
                        final OCTypeEqualityVisitor ocTypeEqualityVisitor = this;
                        final OCType ocType = ocTypeEqualityVisitor.myType;
                        final Class<? extends OCType> clazz2 = ocType.getClass();
                        if (clazz != clazz2) {
                            return false;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
                try {
                    if (this.myType == ocMagicType) {
                        break Label_0072;
                    }
                    final OCTypeEqualityVisitor ocTypeEqualityVisitor2 = this;
                    final OCType ocType2 = ocTypeEqualityVisitor2.myType;
                    final OCMagicType ocMagicType3 = (OCMagicType)ocType2;
                    final String s = ocMagicType3.getMagicName();
                    final OCMagicType ocMagicType4 = ocMagicType;
                    final String s2 = ocMagicType4.getMagicName();
                    final boolean b = s.equals(s2);
                    if (b) {
                        break Label_0072;
                    }
                    break Label_0072;
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            try {
                final OCTypeEqualityVisitor ocTypeEqualityVisitor2 = this;
                final OCType ocType2 = ocTypeEqualityVisitor2.myType;
                final OCMagicType ocMagicType3 = (OCMagicType)ocType2;
                final String s = ocMagicType3.getMagicName();
                final OCMagicType ocMagicType4 = ocMagicType;
                final String s2 = ocMagicType4.getMagicName();
                final boolean b = s.equals(s2);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return false;
    }
    
    @Override
    public Boolean visitTypeParameterType(final OCTypeParameterType p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.myType:Lcom/jetbrains/cidr/lang/types/OCType;
        //     4: ifnull          28
        //     7: aload_1        
        //     8: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //    11: aload_0        
        //    12: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.myType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    15: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //    18: if_acmpeq       37
        //    21: goto            28
        //    24: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    27: athrow         
        //    28: iconst_0       
        //    29: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //    32: areturn        
        //    33: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    36: athrow         
        //    37: aload_0        
        //    38: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.myType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    41: aload_1        
        //    42: if_acmpeq       130
        //    45: aload_0        
        //    46: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.myType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    49: checkcast       Lcom/jetbrains/cidr/lang/types/OCTypeParameterType;
        //    52: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeParameterType.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;
        //    55: aload_1        
        //    56: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeParameterType.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;
        //    59: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //    62: ifeq            138
        //    65: goto            72
        //    68: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    71: athrow         
        //    72: aload_1        
        //    73: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeParameterType.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;
        //    76: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    79: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isSynthetic:()Z
        //    84: aload_0        
        //    85: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.myType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    88: checkcast       Lcom/jetbrains/cidr/lang/types/OCTypeParameterType;
        //    91: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeParameterType.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;
        //    94: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    97: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isSynthetic:()Z
        //   102: if_icmpne       138
        //   105: goto            112
        //   108: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   111: athrow         
        //   112: aload_0        
        //   113: aload_1        
        //   114: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.visitMagicType:(Lcom/jetbrains/cidr/lang/types/OCMagicType;)Ljava/lang/Boolean;
        //   117: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   120: ifeq            138
        //   123: goto            130
        //   126: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   129: athrow         
        //   130: iconst_1       
        //   131: goto            139
        //   134: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   137: athrow         
        //   138: iconst_0       
        //   139: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   142: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      21     24     28     Ljava/lang/IllegalArgumentException;
        //  7      33     33     37     Ljava/lang/IllegalArgumentException;
        //  37     65     68     72     Ljava/lang/IllegalArgumentException;
        //  45     105    108    112    Ljava/lang/IllegalArgumentException;
        //  72     123    126    130    Ljava/lang/IllegalArgumentException;
        //  112    134    134    138    Ljava/lang/IllegalArgumentException;
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
    public Boolean visitAutoType(final OCAutoType p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_0        
        //     2: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.myType:Lcom/jetbrains/cidr/lang/types/OCType;
        //     5: if_acmpne       17
        //     8: iconst_1       
        //     9: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //    12: areturn        
        //    13: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    16: athrow         
        //    17: aload_0        
        //    18: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.myType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    21: ifnull          45
        //    24: aload_1        
        //    25: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //    28: aload_0        
        //    29: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.myType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    32: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //    35: if_acmpeq       54
        //    38: goto            45
        //    41: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    44: athrow         
        //    45: iconst_0       
        //    46: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //    49: areturn        
        //    50: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    53: athrow         
        //    54: aload_0        
        //    55: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.myType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    58: checkcast       Lcom/jetbrains/cidr/lang/types/OCAutoType;
        //    61: astore_2       
        //    62: aload_1        
        //    63: invokevirtual   com/jetbrains/cidr/lang/types/OCAutoType.getExpressionSymbol:()Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //    66: aload_2        
        //    67: invokevirtual   com/jetbrains/cidr/lang/types/OCAutoType.getExpressionSymbol:()Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //    70: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //    73: ifne            85
        //    76: iconst_0       
        //    77: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //    80: areturn        
        //    81: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    84: athrow         
        //    85: aload_1        
        //    86: invokevirtual   com/jetbrains/cidr/lang/types/OCAutoType.getIncompleteType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    89: astore_3       
        //    90: aload_2        
        //    91: invokevirtual   com/jetbrains/cidr/lang/types/OCAutoType.getIncompleteType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    94: astore          4
        //    96: aload_3        
        //    97: ifnonnull       112
        //   100: aload           4
        //   102: ifnull          152
        //   105: goto            112
        //   108: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   111: athrow         
        //   112: aload_3        
        //   113: ifnull          143
        //   116: goto            123
        //   119: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   122: athrow         
        //   123: aload_3        
        //   124: aload           4
        //   126: aload_0        
        //   127: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   130: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equals:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   133: ifne            152
        //   136: goto            143
        //   139: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   142: athrow         
        //   143: iconst_0       
        //   144: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   147: areturn        
        //   148: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   151: athrow         
        //   152: aload_1        
        //   153: invokevirtual   com/jetbrains/cidr/lang/types/OCAutoType.getSubstitution:()Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;
        //   156: astore          5
        //   158: aload_2        
        //   159: invokevirtual   com/jetbrains/cidr/lang/types/OCAutoType.getSubstitution:()Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;
        //   162: astore          6
        //   164: aload_1        
        //   165: invokevirtual   com/jetbrains/cidr/lang/types/OCAutoType.getExpressionSymbol:()Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //   168: ifnull          233
        //   171: aload           5
        //   173: ifnonnull       195
        //   176: goto            183
        //   179: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   182: athrow         
        //   183: aload           6
        //   185: ifnull          233
        //   188: goto            195
        //   191: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   194: athrow         
        //   195: aload           5
        //   197: ifnull          224
        //   200: goto            207
        //   203: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   206: athrow         
        //   207: aload           5
        //   209: aload           6
        //   211: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.equals:(Ljava/lang/Object;)Z
        //   214: ifne            233
        //   217: goto            224
        //   220: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   223: athrow         
        //   224: iconst_0       
        //   225: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   228: areturn        
        //   229: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   232: athrow         
        //   233: iconst_1       
        //   234: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   237: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      13     13     17     Ljava/lang/IllegalArgumentException;
        //  17     38     41     45     Ljava/lang/IllegalArgumentException;
        //  24     50     50     54     Ljava/lang/IllegalArgumentException;
        //  62     81     81     85     Ljava/lang/IllegalArgumentException;
        //  96     105    108    112    Ljava/lang/IllegalArgumentException;
        //  100    116    119    123    Ljava/lang/IllegalArgumentException;
        //  112    136    139    143    Ljava/lang/IllegalArgumentException;
        //  123    148    148    152    Ljava/lang/IllegalArgumentException;
        //  164    176    179    183    Ljava/lang/IllegalArgumentException;
        //  171    188    191    195    Ljava/lang/IllegalArgumentException;
        //  183    200    203    207    Ljava/lang/IllegalArgumentException;
        //  195    217    220    224    Ljava/lang/IllegalArgumentException;
        //  207    229    229    233    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0112:
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
    public Boolean visitObjectType(final OCObjectType ocObjectType) {
        try {
            if (ocObjectType == this.myType) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0045: {
            try {
                if (this.myType == null) {
                    break Label_0045;
                }
                final OCObjectType ocObjectType2 = ocObjectType;
                final Class<? extends OCObjectType> clazz = ocObjectType2.getClass();
                final OCTypeEqualityVisitor ocTypeEqualityVisitor = this;
                final OCType ocType = ocTypeEqualityVisitor.myType;
                final Class<? extends OCType> clazz2 = ocType.getClass();
                if (clazz != clazz2) {
                    break Label_0045;
                }
                break Label_0045;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final OCObjectType ocObjectType2 = ocObjectType;
                final Class<? extends OCObjectType> clazz = ocObjectType2.getClass();
                final OCTypeEqualityVisitor ocTypeEqualityVisitor = this;
                final OCType ocType = ocTypeEqualityVisitor.myType;
                final Class<? extends OCType> clazz2 = ocType.getClass();
                if (clazz != clazz2) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        final OCObjectType ocObjectType3 = (OCObjectType)this.myType;
        try {
            if (!Comparing.equal((Object)ocObjectType.getInterface(), (Object)ocObjectType3.getInterface())) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        try {
            if (!Comparing.equal((Object)ocObjectType.getImplementation(), (Object)ocObjectType3.getImplementation())) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        try {
            if (!ocObjectType.getAllProtocols().equals(ocObjectType3.getAllProtocols())) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        return true;
    }
    
    @Override
    public Boolean visitArrayType(final OCArrayType p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.myType:Lcom/jetbrains/cidr/lang/types/OCType;
        //     4: aload_1        
        //     5: if_acmpne       17
        //     8: iconst_1       
        //     9: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //    12: areturn        
        //    13: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    16: athrow         
        //    17: aload_0        
        //    18: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.myType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    21: ifnull          45
        //    24: aload_1        
        //    25: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //    28: aload_0        
        //    29: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.myType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    32: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //    35: if_acmpeq       54
        //    38: goto            45
        //    41: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    44: athrow         
        //    45: iconst_0       
        //    46: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //    49: areturn        
        //    50: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    53: athrow         
        //    54: aload_0        
        //    55: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.myType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    58: checkcast       Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //    61: astore_2       
        //    62: aload_1        
        //    63: invokevirtual   com/jetbrains/cidr/lang/types/OCArrayType.hasLength:()Z
        //    66: ifeq            110
        //    69: aload_2        
        //    70: invokevirtual   com/jetbrains/cidr/lang/types/OCArrayType.hasLength:()Z
        //    73: ifeq            110
        //    76: goto            83
        //    79: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    82: athrow         
        //    83: aload_1        
        //    84: invokevirtual   com/jetbrains/cidr/lang/types/OCArrayType.getLength:()I
        //    87: aload_2        
        //    88: invokevirtual   com/jetbrains/cidr/lang/types/OCArrayType.getLength:()I
        //    91: if_icmpeq       110
        //    94: goto            101
        //    97: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   100: athrow         
        //   101: iconst_0       
        //   102: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   105: areturn        
        //   106: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   109: athrow         
        //   110: aload_1        
        //   111: invokevirtual   com/jetbrains/cidr/lang/types/OCArrayType.getARCAttribute:()Lcom/jetbrains/cidr/lang/types/ARCAttribute;
        //   114: aload_2        
        //   115: invokevirtual   com/jetbrains/cidr/lang/types/OCArrayType.getARCAttribute:()Lcom/jetbrains/cidr/lang/types/ARCAttribute;
        //   118: if_acmpeq       130
        //   121: iconst_0       
        //   122: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   125: areturn        
        //   126: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   129: athrow         
        //   130: aload_0        
        //   131: aload_1        
        //   132: invokevirtual   com/jetbrains/cidr/lang/types/OCArrayType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   135: aload_2        
        //   136: invokevirtual   com/jetbrains/cidr/lang/types/OCArrayType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   139: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.equal:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   142: ifne            154
        //   145: iconst_0       
        //   146: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   149: areturn        
        //   150: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   153: athrow         
        //   154: iconst_1       
        //   155: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   158: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      13     13     17     Ljava/lang/IllegalArgumentException;
        //  17     38     41     45     Ljava/lang/IllegalArgumentException;
        //  24     50     50     54     Ljava/lang/IllegalArgumentException;
        //  62     76     79     83     Ljava/lang/IllegalArgumentException;
        //  69     94     97     101    Ljava/lang/IllegalArgumentException;
        //  83     106    106    110    Ljava/lang/IllegalArgumentException;
        //  110    126    126    130    Ljava/lang/IllegalArgumentException;
        //  130    150    150    154    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0083:
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
    public Boolean visitPointerType(final OCPointerType ocPointerType) {
        try {
            if (this.myType == ocPointerType) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0045: {
            try {
                if (this.myType == null) {
                    break Label_0045;
                }
                final OCPointerType ocPointerType2 = ocPointerType;
                final Class<? extends OCPointerType> clazz = ocPointerType2.getClass();
                final OCTypeEqualityVisitor ocTypeEqualityVisitor = this;
                final OCType ocType = ocTypeEqualityVisitor.myType;
                final Class<? extends OCType> clazz2 = ocType.getClass();
                if (clazz != clazz2) {
                    break Label_0045;
                }
                break Label_0045;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final OCPointerType ocPointerType2 = ocPointerType;
                final Class<? extends OCPointerType> clazz = ocPointerType2.getClass();
                final OCTypeEqualityVisitor ocTypeEqualityVisitor = this;
                final OCType ocType = ocTypeEqualityVisitor.myType;
                final Class<? extends OCType> clazz2 = ocType.getClass();
                if (clazz != clazz2) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        final OCPointerType ocPointerType3 = (OCPointerType)this.myType;
        try {
            if (ocPointerType.getARCAttribute() != ocPointerType3.getARCAttribute()) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        try {
            if (!this.equal(ocPointerType.getRefType(), ocPointerType3.getRefType(), true)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        return true;
    }
    
    @Override
    public Boolean visitBlockPointerType(final OCBlockPointerType ocBlockPointerType) {
        Label_0028: {
            try {
                if (this.myType == null) {
                    break Label_0028;
                }
                final OCBlockPointerType ocBlockPointerType2 = ocBlockPointerType;
                final Class<? extends OCBlockPointerType> clazz = ocBlockPointerType2.getClass();
                final OCTypeEqualityVisitor ocTypeEqualityVisitor = this;
                final OCType ocType = ocTypeEqualityVisitor.myType;
                final Class<? extends OCType> clazz2 = ocType.getClass();
                if (clazz != clazz2) {
                    break Label_0028;
                }
                return this.visitPointerType((OCPointerType)ocBlockPointerType);
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCBlockPointerType ocBlockPointerType2 = ocBlockPointerType;
                final Class<? extends OCBlockPointerType> clazz = ocBlockPointerType2.getClass();
                final OCTypeEqualityVisitor ocTypeEqualityVisitor = this;
                final OCType ocType = ocTypeEqualityVisitor.myType;
                final Class<? extends OCType> clazz2 = ocType.getClass();
                if (clazz != clazz2) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return this.visitPointerType((OCPointerType)ocBlockPointerType);
    }
    
    @Override
    public Boolean visitCppReferenceType(final OCCppReferenceType ocCppReferenceType) {
        try {
            if (this.myType == ocCppReferenceType) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0045: {
            try {
                if (this.myType == null) {
                    break Label_0045;
                }
                final OCCppReferenceType ocCppReferenceType2 = ocCppReferenceType;
                final Class<? extends OCCppReferenceType> clazz = ocCppReferenceType2.getClass();
                final OCTypeEqualityVisitor ocTypeEqualityVisitor = this;
                final OCType ocType = ocTypeEqualityVisitor.myType;
                final Class<? extends OCType> clazz2 = ocType.getClass();
                if (clazz != clazz2) {
                    break Label_0045;
                }
                break Label_0045;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final OCCppReferenceType ocCppReferenceType2 = ocCppReferenceType;
                final Class<? extends OCCppReferenceType> clazz = ocCppReferenceType2.getClass();
                final OCTypeEqualityVisitor ocTypeEqualityVisitor = this;
                final OCType ocType = ocTypeEqualityVisitor.myType;
                final Class<? extends OCType> clazz2 = ocType.getClass();
                if (clazz != clazz2) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        final OCCppReferenceType ocCppReferenceType3 = (OCCppReferenceType)this.myType;
        try {
            if (!this.equal(ocCppReferenceType.getRefType(), ocCppReferenceType3.getRefType(), true)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        try {
            if (ocCppReferenceType.isRvalueRef() != ocCppReferenceType3.isRvalueRef()) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        return true;
    }
    
    @Override
    public Boolean visitIdType(final OCIdType ocIdType) {
        Label_0028: {
            try {
                if (this.myType == null) {
                    break Label_0028;
                }
                final OCIdType ocIdType2 = ocIdType;
                final Class<? extends OCIdType> clazz = ocIdType2.getClass();
                final OCTypeEqualityVisitor ocTypeEqualityVisitor = this;
                final OCType ocType = ocTypeEqualityVisitor.myType;
                final Class<? extends OCType> clazz2 = ocType.getClass();
                if (clazz != clazz2) {
                    break Label_0028;
                }
                return this.visitObjectType((OCObjectType)ocIdType);
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCIdType ocIdType2 = ocIdType;
                final Class<? extends OCIdType> clazz = ocIdType2.getClass();
                final OCTypeEqualityVisitor ocTypeEqualityVisitor = this;
                final OCType ocType = ocTypeEqualityVisitor.myType;
                final Class<? extends OCType> clazz2 = ocType.getClass();
                if (clazz != clazz2) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return this.visitObjectType((OCObjectType)ocIdType);
    }
    
    @Override
    public Boolean visitIntType(final OCIntType ocIntType) {
        try {
            if (ocIntType == this.myType) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0045: {
            try {
                if (this.myType == null) {
                    break Label_0045;
                }
                final OCIntType ocIntType2 = ocIntType;
                final Class<? extends OCIntType> clazz = ocIntType2.getClass();
                final OCTypeEqualityVisitor ocTypeEqualityVisitor = this;
                final OCType ocType = ocTypeEqualityVisitor.myType;
                final Class<? extends OCType> clazz2 = ocType.getClass();
                if (clazz != clazz2) {
                    break Label_0045;
                }
                break Label_0045;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final OCIntType ocIntType2 = ocIntType;
                final Class<? extends OCIntType> clazz = ocIntType2.getClass();
                final OCTypeEqualityVisitor ocTypeEqualityVisitor = this;
                final OCType ocType = ocTypeEqualityVisitor.myType;
                final Class<? extends OCType> clazz2 = ocType.getClass();
                if (clazz != clazz2) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        final OCIntType ocIntType3 = (OCIntType)this.myType;
        try {
            if (ocIntType.isSigned() != ocIntType3.isSigned()) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        try {
            if (!ocIntType.getCTypeId().equals(ocIntType3.getCTypeId())) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        return true;
    }
    
    @Override
    public Boolean visitRealType(final OCRealType ocRealType) {
        Label_0081: {
            Label_0037: {
                Label_0028: {
                    try {
                        if (this.myType == null) {
                            break Label_0028;
                        }
                        final OCRealType ocRealType2 = ocRealType;
                        final Class<? extends OCRealType> clazz = ocRealType2.getClass();
                        final OCTypeEqualityVisitor ocTypeEqualityVisitor = this;
                        final OCType ocType = ocTypeEqualityVisitor.myType;
                        final Class<? extends OCType> clazz2 = ocType.getClass();
                        if (clazz != clazz2) {
                            break Label_0028;
                        }
                        break Label_0037;
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        final OCRealType ocRealType2 = ocRealType;
                        final Class<? extends OCRealType> clazz = ocRealType2.getClass();
                        final OCTypeEqualityVisitor ocTypeEqualityVisitor = this;
                        final OCType ocType = ocTypeEqualityVisitor.myType;
                        final Class<? extends OCType> clazz2 = ocType.getClass();
                        if (clazz != clazz2) {
                            return false;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
                try {
                    if (!ocRealType.getCTypeId().equals(((OCRealType)this.myType).getCTypeId())) {
                        break Label_0081;
                    }
                    final OCRealType ocRealType3 = ocRealType;
                    final boolean b = ocRealType3.isComplex();
                    final OCTypeEqualityVisitor ocTypeEqualityVisitor2 = this;
                    final OCType ocType2 = ocTypeEqualityVisitor2.myType;
                    final OCRealType ocRealType4 = (OCRealType)ocType2;
                    final boolean b2 = ocRealType4.isComplex();
                    if (b == b2) {
                        break Label_0081;
                    }
                    break Label_0081;
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            try {
                final OCRealType ocRealType3 = ocRealType;
                final boolean b = ocRealType3.isComplex();
                final OCTypeEqualityVisitor ocTypeEqualityVisitor2 = this;
                final OCType ocType2 = ocTypeEqualityVisitor2.myType;
                final OCRealType ocRealType4 = (OCRealType)ocType2;
                final boolean b2 = ocRealType4.isComplex();
                if (b == b2) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return false;
    }
    
    @Override
    public Boolean visitReferenceType(final OCReferenceType ocReferenceType) {
        try {
            if (ocReferenceType == this.myType) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0045: {
            try {
                if (this.myType == null) {
                    break Label_0045;
                }
                final OCReferenceType ocReferenceType2 = ocReferenceType;
                final Class<? extends OCReferenceType> clazz = ocReferenceType2.getClass();
                final OCTypeEqualityVisitor ocTypeEqualityVisitor = this;
                final OCType ocType = ocTypeEqualityVisitor.myType;
                final Class<? extends OCType> clazz2 = ocType.getClass();
                if (clazz != clazz2) {
                    break Label_0045;
                }
                break Label_0045;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final OCReferenceType ocReferenceType2 = ocReferenceType;
                final Class<? extends OCReferenceType> clazz = ocReferenceType2.getClass();
                final OCTypeEqualityVisitor ocTypeEqualityVisitor = this;
                final OCType ocType = ocTypeEqualityVisitor.myType;
                final Class<? extends OCType> clazz2 = ocType.getClass();
                if (clazz != clazz2) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        final OCReferenceType ocReferenceType3 = (OCReferenceType)this.myType;
        try {
            if (!ocReferenceType.getProtocolNames().equals(ocReferenceType3.getProtocolNames())) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        try {
            if (!ocReferenceType.getReference().equals(ocReferenceType3.getReference())) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        try {
            if (!ocReferenceType.getSubstitution().equals(ocReferenceType3.getSubstitution())) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        try {
            if (ocReferenceType.getARCAttribute() != ocReferenceType3.getARCAttribute()) {
                return false;
            }
        }
        catch (IllegalArgumentException ex7) {
            throw a(ex7);
        }
        return true;
    }
    
    @Override
    public Boolean visitStructType(final OCStructType p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.myType:Lcom/jetbrains/cidr/lang/types/OCType;
        //     4: ifnull          28
        //     7: aload_1        
        //     8: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //    11: aload_0        
        //    12: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.myType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    15: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //    18: if_acmpeq       37
        //    21: goto            28
        //    24: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    27: athrow         
        //    28: iconst_0       
        //    29: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //    32: areturn        
        //    33: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    36: athrow         
        //    37: invokestatic    com/intellij/openapi/progress/ProgressManager.checkCanceled:()V
        //    40: aload_0        
        //    41: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.myType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    44: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //    47: astore_2       
        //    48: aload_1        
        //    49: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getTypedefName:()Ljava/lang/String;
        //    52: ifnull          99
        //    55: aload_2        
        //    56: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getTypedefName:()Ljava/lang/String;
        //    59: ifnull          99
        //    62: goto            69
        //    65: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    68: athrow         
        //    69: aload_1        
        //    70: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getTypedefName:()Ljava/lang/String;
        //    73: aload_2        
        //    74: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getTypedefName:()Ljava/lang/String;
        //    77: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    80: ifne            99
        //    83: goto            90
        //    86: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    89: athrow         
        //    90: iconst_0       
        //    91: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //    94: areturn        
        //    95: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    98: athrow         
        //    99: new             Lcom/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor$1;
        //   102: dup            
        //   103: aload_0        
        //   104: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor$1.<init>:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor;)V
        //   107: astore_3       
        //   108: aload_3        
        //   109: invokedynamic   fun:(Lcom/intellij/util/containers/FactoryMap;)Lcom/intellij/util/Function;
        //   114: astore          4
        //   116: new             Ljava/util/HashSet;
        //   119: dup            
        //   120: aload_1        
        //   121: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getStructs:()Ljava/util/List;
        //   124: aload           4
        //   126: invokestatic    com/intellij/util/containers/ContainerUtil.map:(Ljava/util/Collection;Lcom/intellij/util/Function;)Ljava/util/List;
        //   129: invokespecial   java/util/HashSet.<init>:(Ljava/util/Collection;)V
        //   132: astore          5
        //   134: aload           5
        //   136: aload_2        
        //   137: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getStructs:()Ljava/util/List;
        //   140: aload           4
        //   142: invokestatic    com/intellij/util/containers/ContainerUtil.map:(Ljava/util/Collection;Lcom/intellij/util/Function;)Ljava/util/List;
        //   145: invokeinterface java/util/Set.retainAll:(Ljava/util/Collection;)Z
        //   150: pop            
        //   151: aload_1        
        //   152: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getStructs:()Ljava/util/List;
        //   155: aload           5
        //   157: aload           4
        //   159: aload_0        
        //   160: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   163: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/util/List;Ljava/util/Set;Lcom/intellij/util/Function;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   166: astore          6
        //   168: aload_2        
        //   169: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getStructs:()Ljava/util/List;
        //   172: aload           5
        //   174: aload           4
        //   176: aload_0        
        //   177: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   180: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/util/List;Ljava/util/Set;Lcom/intellij/util/Function;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   183: astore          7
        //   185: aload           6
        //   187: aload           7
        //   189: if_acmpne       201
        //   192: iconst_1       
        //   193: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   196: areturn        
        //   197: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   200: athrow         
        //   201: aload           6
        //   203: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.isUnnamed:()Z
        //   206: ifeq            239
        //   209: aload           7
        //   211: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.isUnnamed:()Z
        //   214: ifeq            239
        //   217: goto            224
        //   220: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   223: athrow         
        //   224: aload           6
        //   226: aload           7
        //   228: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.equals:(Ljava/lang/Object;)Z
        //   231: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   234: areturn        
        //   235: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   238: athrow         
        //   239: aload           6
        //   241: aload           7
        //   243: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.resolvedNamesEqual:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;)Z
        //   246: ifne            258
        //   249: iconst_0       
        //   250: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   253: areturn        
        //   254: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   257: athrow         
        //   258: aload_0        
        //   259: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.myAssumeDifferentSubstitutionsEquals:Z
        //   262: ifeq            274
        //   265: iconst_1       
        //   266: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   269: areturn        
        //   270: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   273: athrow         
        //   274: aload           6
        //   276: aload_0        
        //   277: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   280: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getTemplateArguments:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Ljava/util/List;
        //   283: astore          8
        //   285: aload           7
        //   287: aload_0        
        //   288: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   291: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getTemplateArguments:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Ljava/util/List;
        //   294: astore          9
        //   296: aload           8
        //   298: invokeinterface java/util/List.size:()I
        //   303: aload           9
        //   305: invokeinterface java/util/List.size:()I
        //   310: if_icmpeq       372
        //   313: aload_0        
        //   314: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.myAssumeNonExpandedVariadicsEquals:Z
        //   317: ifeq            363
        //   320: goto            327
        //   323: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   326: athrow         
        //   327: aload           8
        //   329: invokestatic    com/intellij/util/containers/ContainerUtil.getLastItem:(Ljava/util/List;)Ljava/lang/Object;
        //   332: instanceof      Lcom/jetbrains/cidr/lang/types/OCVariadicType;
        //   335: ifne            372
        //   338: goto            345
        //   341: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   344: athrow         
        //   345: aload           9
        //   347: invokestatic    com/intellij/util/containers/ContainerUtil.getLastItem:(Ljava/util/List;)Ljava/lang/Object;
        //   350: instanceof      Lcom/jetbrains/cidr/lang/types/OCVariadicType;
        //   353: ifne            372
        //   356: goto            363
        //   359: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   362: athrow         
        //   363: iconst_0       
        //   364: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   367: areturn        
        //   368: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   371: athrow         
        //   372: aload           8
        //   374: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   379: astore          10
        //   381: aload           9
        //   383: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   388: astore          11
        //   390: aload           10
        //   392: invokeinterface java/util/Iterator.hasNext:()Z
        //   397: ifeq            987
        //   400: aload           11
        //   402: invokeinterface java/util/Iterator.hasNext:()Z
        //   407: ifeq            987
        //   410: goto            417
        //   413: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   416: athrow         
        //   417: aload           10
        //   419: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   424: checkcast       Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   427: astore          12
        //   429: aload           11
        //   431: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   436: checkcast       Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   439: astore          13
        //   441: aload_0        
        //   442: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.myAssumeNonExpandedVariadicsEquals:Z
        //   445: ifeq            485
        //   448: aload           12
        //   450: instanceof      Lcom/jetbrains/cidr/lang/types/OCVariadicType;
        //   453: ifne            987
        //   456: goto            463
        //   459: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   462: athrow         
        //   463: aload           13
        //   465: instanceof      Lcom/jetbrains/cidr/lang/types/OCVariadicType;
        //   468: ifeq            485
        //   471: goto            478
        //   474: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   477: athrow         
        //   478: goto            987
        //   481: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   484: athrow         
        //   485: aload           12
        //   487: ifnull          532
        //   490: aload           12
        //   492: instanceof      Lcom/jetbrains/cidr/lang/types/OCUnknownType;
        //   495: ifne            532
        //   498: goto            505
        //   501: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   504: athrow         
        //   505: aload           13
        //   507: ifnull          532
        //   510: goto            517
        //   513: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   516: athrow         
        //   517: aload           13
        //   519: instanceof      Lcom/jetbrains/cidr/lang/types/OCUnknownType;
        //   522: ifeq            624
        //   525: goto            532
        //   528: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   531: athrow         
        //   532: aload_0        
        //   533: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.myAssumeNullSubstitutionsEquals:Z
        //   536: ifeq            553
        //   539: goto            546
        //   542: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   545: athrow         
        //   546: goto            390
        //   549: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   552: athrow         
        //   553: aload_0        
        //   554: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   557: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getElement:()Lcom/intellij/psi/PsiElement;
        //   560: ldc             Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;.class
        //   562: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   565: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //   568: astore          14
        //   570: aload           14
        //   572: ifnull          592
        //   575: aload           14
        //   577: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDeclaration.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   582: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   585: goto            593
        //   588: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   591: athrow         
        //   592: aconst_null    
        //   593: astore          15
        //   595: aload           15
        //   597: ifnull          624
        //   600: aload           15
        //   602: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getResolvedOwner:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   605: aload           7
        //   607: if_acmpne       624
        //   610: goto            617
        //   613: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   616: athrow         
        //   617: goto            390
        //   620: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   623: athrow         
        //   624: aload           12
        //   626: instanceof      Lcom/jetbrains/cidr/lang/types/OCTypeParameterType;
        //   629: ifne            647
        //   632: aload           13
        //   634: instanceof      Lcom/jetbrains/cidr/lang/types/OCTypeParameterType;
        //   637: ifeq            731
        //   640: goto            647
        //   643: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   646: athrow         
        //   647: aload_0        
        //   648: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.myAssumeTypeParametersEquals:Z
        //   651: ifne            984
        //   654: goto            661
        //   657: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   660: athrow         
        //   661: aload           13
        //   663: instanceof      Lcom/jetbrains/cidr/lang/types/OCType;
        //   666: ifeq            722
        //   669: goto            676
        //   672: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   675: athrow         
        //   676: aload           12
        //   678: instanceof      Lcom/jetbrains/cidr/lang/types/OCType;
        //   681: ifeq            722
        //   684: goto            691
        //   687: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   690: athrow         
        //   691: aload_0        
        //   692: aload           12
        //   694: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   697: aload           13
        //   699: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   702: aload_0        
        //   703: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   706: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.substitutionTypesEqual:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Ljava/lang/Boolean;
        //   709: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   712: ifne            984
        //   715: goto            722
        //   718: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   721: athrow         
        //   722: iconst_0       
        //   723: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   726: areturn        
        //   727: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   730: athrow         
        //   731: aload           12
        //   733: instanceof      Lcom/jetbrains/cidr/lang/types/OCType;
        //   736: ifeq            794
        //   739: aload           13
        //   741: instanceof      Lcom/jetbrains/cidr/lang/types/OCType;
        //   744: ifeq            794
        //   747: goto            754
        //   750: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   753: athrow         
        //   754: aload_0        
        //   755: aload           12
        //   757: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   760: aload           13
        //   762: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   765: aload_0        
        //   766: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   769: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.substitutionTypesEqual:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Ljava/lang/Boolean;
        //   772: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   775: ifne            984
        //   778: goto            785
        //   781: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   784: athrow         
        //   785: iconst_0       
        //   786: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   789: areturn        
        //   790: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   793: athrow         
        //   794: aload           12
        //   796: instanceof      Lcom/jetbrains/cidr/lang/types/OCType;
        //   799: ifne            817
        //   802: aload           13
        //   804: instanceof      Lcom/jetbrains/cidr/lang/types/OCType;
        //   807: ifeq            826
        //   810: goto            817
        //   813: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   816: athrow         
        //   817: iconst_0       
        //   818: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   821: areturn        
        //   822: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   825: athrow         
        //   826: aload_0        
        //   827: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.myAssumeMagicTypesEquals:Z
        //   830: ifne            984
        //   833: aload_0        
        //   834: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.myEvaluateExpressionSubstitutions:Z
        //   837: ifeq            965
        //   840: goto            847
        //   843: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   846: athrow         
        //   847: aload           12
        //   849: instanceof      Lcom/jetbrains/cidr/lang/types/OCExpressionTypeArgument;
        //   852: ifeq            965
        //   855: goto            862
        //   858: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   861: athrow         
        //   862: aload           13
        //   864: instanceof      Lcom/jetbrains/cidr/lang/types/OCExpressionTypeArgument;
        //   867: ifeq            965
        //   870: goto            877
        //   873: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   876: athrow         
        //   877: aload           12
        //   879: aload           13
        //   881: aload_0        
        //   882: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   885: invokeinterface com/jetbrains/cidr/lang/types/OCTypeArgument.equals:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   890: ifeq            909
        //   893: goto            900
        //   896: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   899: athrow         
        //   900: iconst_1       
        //   901: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   904: areturn        
        //   905: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   908: athrow         
        //   909: aload           12
        //   911: checkcast       Lcom/jetbrains/cidr/lang/types/OCExpressionTypeArgument;
        //   914: invokevirtual   com/jetbrains/cidr/lang/types/OCExpressionTypeArgument.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //   917: aload_0        
        //   918: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   921: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.evaluate:(Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Ljava/lang/Number;
        //   924: astore          14
        //   926: aload           13
        //   928: checkcast       Lcom/jetbrains/cidr/lang/types/OCExpressionTypeArgument;
        //   931: invokevirtual   com/jetbrains/cidr/lang/types/OCExpressionTypeArgument.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //   934: aload_0        
        //   935: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   938: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.evaluate:(Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Ljava/lang/Number;
        //   941: astore          15
        //   943: aload           14
        //   945: aload           15
        //   947: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.isSameValue:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //   950: ifne            962
        //   953: iconst_0       
        //   954: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   957: areturn        
        //   958: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   961: athrow         
        //   962: goto            984
        //   965: aload           12
        //   967: aload           13
        //   969: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //   972: ifne            984
        //   975: iconst_0       
        //   976: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   979: areturn        
        //   980: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   983: athrow         
        //   984: goto            390
        //   987: aload           10
        //   989: invokeinterface java/util/Iterator.hasNext:()Z
        //   994: ifeq            1040
        //   997: aload_0        
        //   998: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.myAssumeNonExpandedVariadicsEquals:Z
        //  1001: ifeq            1031
        //  1004: goto            1011
        //  1007: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1010: athrow         
        //  1011: aload           10
        //  1013: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  1018: instanceof      Lcom/jetbrains/cidr/lang/types/OCVariadicType;
        //  1021: ifne            1040
        //  1024: goto            1031
        //  1027: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1030: athrow         
        //  1031: iconst_0       
        //  1032: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //  1035: areturn        
        //  1036: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1039: athrow         
        //  1040: aload           11
        //  1042: invokeinterface java/util/Iterator.hasNext:()Z
        //  1047: ifeq            1093
        //  1050: aload_0        
        //  1051: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.myAssumeNonExpandedVariadicsEquals:Z
        //  1054: ifeq            1084
        //  1057: goto            1064
        //  1060: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1063: athrow         
        //  1064: aload           11
        //  1066: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  1071: instanceof      Lcom/jetbrains/cidr/lang/types/OCVariadicType;
        //  1074: ifne            1093
        //  1077: goto            1084
        //  1080: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1083: athrow         
        //  1084: iconst_0       
        //  1085: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //  1088: areturn        
        //  1089: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1092: athrow         
        //  1093: iconst_1       
        //  1094: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //  1097: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      21     24     28     Ljava/lang/IllegalArgumentException;
        //  7      33     33     37     Ljava/lang/IllegalArgumentException;
        //  48     62     65     69     Ljava/lang/IllegalArgumentException;
        //  55     83     86     90     Ljava/lang/IllegalArgumentException;
        //  69     95     95     99     Ljava/lang/IllegalArgumentException;
        //  185    197    197    201    Ljava/lang/IllegalArgumentException;
        //  201    217    220    224    Ljava/lang/IllegalArgumentException;
        //  209    235    235    239    Ljava/lang/IllegalArgumentException;
        //  239    254    254    258    Ljava/lang/IllegalArgumentException;
        //  258    270    270    274    Ljava/lang/IllegalArgumentException;
        //  296    320    323    327    Ljava/lang/IllegalArgumentException;
        //  313    338    341    345    Ljava/lang/IllegalArgumentException;
        //  327    356    359    363    Ljava/lang/IllegalArgumentException;
        //  345    368    368    372    Ljava/lang/IllegalArgumentException;
        //  390    410    413    417    Ljava/lang/IllegalArgumentException;
        //  441    456    459    463    Ljava/lang/IllegalArgumentException;
        //  448    471    474    478    Ljava/lang/IllegalArgumentException;
        //  463    481    481    485    Ljava/lang/IllegalArgumentException;
        //  485    498    501    505    Ljava/lang/IllegalArgumentException;
        //  490    510    513    517    Ljava/lang/IllegalArgumentException;
        //  505    525    528    532    Ljava/lang/IllegalArgumentException;
        //  517    539    542    546    Ljava/lang/IllegalArgumentException;
        //  532    549    549    553    Ljava/lang/IllegalArgumentException;
        //  570    588    588    592    Ljava/lang/IllegalArgumentException;
        //  595    610    613    617    Ljava/lang/IllegalArgumentException;
        //  600    620    620    624    Ljava/lang/IllegalArgumentException;
        //  624    640    643    647    Ljava/lang/IllegalArgumentException;
        //  632    654    657    661    Ljava/lang/IllegalArgumentException;
        //  647    669    672    676    Ljava/lang/IllegalArgumentException;
        //  661    684    687    691    Ljava/lang/IllegalArgumentException;
        //  676    715    718    722    Ljava/lang/IllegalArgumentException;
        //  691    727    727    731    Ljava/lang/IllegalArgumentException;
        //  731    747    750    754    Ljava/lang/IllegalArgumentException;
        //  739    778    781    785    Ljava/lang/IllegalArgumentException;
        //  754    790    790    794    Ljava/lang/IllegalArgumentException;
        //  794    810    813    817    Ljava/lang/IllegalArgumentException;
        //  802    822    822    826    Ljava/lang/IllegalArgumentException;
        //  826    840    843    847    Ljava/lang/IllegalArgumentException;
        //  833    855    858    862    Ljava/lang/IllegalArgumentException;
        //  847    870    873    877    Ljava/lang/IllegalArgumentException;
        //  862    893    896    900    Ljava/lang/IllegalArgumentException;
        //  877    905    905    909    Ljava/lang/IllegalArgumentException;
        //  943    958    958    962    Ljava/lang/IllegalArgumentException;
        //  965    980    980    984    Ljava/lang/IllegalArgumentException;
        //  987    1004   1007   1011   Ljava/lang/IllegalArgumentException;
        //  997    1024   1027   1031   Ljava/lang/IllegalArgumentException;
        //  1011   1036   1036   1040   Ljava/lang/IllegalArgumentException;
        //  1040   1057   1060   1064   Ljava/lang/IllegalArgumentException;
        //  1050   1077   1080   1084   Ljava/lang/IllegalArgumentException;
        //  1064   1089   1089   1093   Ljava/lang/IllegalArgumentException;
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
    
    protected Boolean substitutionTypesEqual(final OCType ocType, final OCType ocType2, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor", "substitutionTypesEqual"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return new OCTypeEqualityVisitor(ocType2, this.myAssumeMagicTypesEquals, false, this.myAssumeTypeParametersEquals, false, this.myContext).equal(ocType);
    }
    
    private static OCStructSymbol a(final List<OCStructSymbol> list, final Set<String> set, final Function<OCStructSymbol, String> function, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityVisitor", "getTopmostStruct"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (list.size() == 1) {
                return list.get(0);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        for (final OCStructSymbol ocStructSymbol : list) {
            try {
                if (ocStructSymbol.isPredeclaration()) {
                    continue;
                }
                final Set<String> set2 = set;
                final Function<OCStructSymbol, String> function2 = function;
                final OCStructSymbol ocStructSymbol2 = ocStructSymbol;
                final Object o = function2.fun((Object)ocStructSymbol2);
                final boolean b = set2.contains(o);
                if (b) {
                    return ocStructSymbol;
                }
                continue;
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                final Set<String> set2 = set;
                final Function<OCStructSymbol, String> function2 = function;
                final OCStructSymbol ocStructSymbol2 = ocStructSymbol;
                final Object o = function2.fun((Object)ocStructSymbol2);
                final boolean b = set2.contains(o);
                if (b) {
                    return ocStructSymbol;
                }
                continue;
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        int size = Integer.MAX_VALUE;
        OCStructSymbol ocStructSymbol3 = null;
        for (final OCStructSymbol ocStructSymbol4 : list) {
            final OCQualifiedName resolvedQualifiedName = ocStructSymbol4.getResolvedQualifiedName(ocResolveContext, false);
            try {
                if (resolvedQualifiedName == null || resolvedQualifiedName.flatten().size() >= size) {
                    continue;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
            ocStructSymbol3 = ocStructSymbol4;
            size = resolvedQualifiedName.flatten().size();
        }
        return ocStructSymbol3;
    }
    
    @Override
    public Boolean visitVariadicType(final OCVariadicType ocVariadicType) {
        try {
            if (this.myType == ocVariadicType) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0045: {
            try {
                if (this.myType == null) {
                    break Label_0045;
                }
                final OCVariadicType ocVariadicType2 = ocVariadicType;
                final Class<? extends OCVariadicType> clazz = ocVariadicType2.getClass();
                final OCTypeEqualityVisitor ocTypeEqualityVisitor = this;
                final OCType ocType = ocTypeEqualityVisitor.myType;
                final Class<? extends OCType> clazz2 = ocType.getClass();
                if (clazz != clazz2) {
                    break Label_0045;
                }
                return this.equal(ocVariadicType.getUnderlyingType(), ((OCVariadicType)this.myType).getUnderlyingType(), true);
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final OCVariadicType ocVariadicType2 = ocVariadicType;
                final Class<? extends OCVariadicType> clazz = ocVariadicType2.getClass();
                final OCTypeEqualityVisitor ocTypeEqualityVisitor = this;
                final OCType ocType = ocTypeEqualityVisitor.myType;
                final Class<? extends OCType> clazz2 = ocType.getClass();
                if (clazz != clazz2) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return this.equal(ocVariadicType.getUnderlyingType(), ((OCVariadicType)this.myType).getUnderlyingType(), true);
    }
    
    @Override
    public Boolean visitExpansionPackType(final OCExpansionPackType ocExpansionPackType) {
        try {
            if (this.myType == ocExpansionPackType) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0045: {
            try {
                if (this.myType == null) {
                    break Label_0045;
                }
                final OCExpansionPackType ocExpansionPackType2 = ocExpansionPackType;
                final Class<? extends OCExpansionPackType> clazz = ocExpansionPackType2.getClass();
                final OCTypeEqualityVisitor ocTypeEqualityVisitor = this;
                final OCType ocType = ocTypeEqualityVisitor.myType;
                final Class<? extends OCType> clazz2 = ocType.getClass();
                if (clazz != clazz2) {
                    break Label_0045;
                }
                return DeepEqual.equalObjects(ocExpansionPackType.getExpansions(), ((OCExpansionPackType)this.myType).getExpansions());
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final OCExpansionPackType ocExpansionPackType2 = ocExpansionPackType;
                final Class<? extends OCExpansionPackType> clazz = ocExpansionPackType2.getClass();
                final OCTypeEqualityVisitor ocTypeEqualityVisitor = this;
                final OCType ocType = ocTypeEqualityVisitor.myType;
                final Class<? extends OCType> clazz2 = ocType.getClass();
                if (clazz != clazz2) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return DeepEqual.equalObjects(ocExpansionPackType.getExpansions(), ((OCExpansionPackType)this.myType).getExpansions());
    }
    
    @Override
    public Boolean visitUnknownType(final OCUnknownType ocUnknownType) {
        Label_0028: {
            try {
                if (this.myType == null) {
                    break Label_0028;
                }
                final OCUnknownType ocUnknownType2 = ocUnknownType;
                final Class<? extends OCUnknownType> clazz = ocUnknownType2.getClass();
                final OCTypeEqualityVisitor ocTypeEqualityVisitor = this;
                final OCType ocType = ocTypeEqualityVisitor.myType;
                final Class<? extends OCType> clazz2 = ocType.getClass();
                if (clazz == clazz2) {
                    break Label_0028;
                }
                break Label_0028;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCUnknownType ocUnknownType2 = ocUnknownType;
                final Class<? extends OCUnknownType> clazz = ocUnknownType2.getClass();
                final OCTypeEqualityVisitor ocTypeEqualityVisitor = this;
                final OCType ocType = ocTypeEqualityVisitor.myType;
                final Class<? extends OCType> clazz2 = ocType.getClass();
                if (clazz == clazz2) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    @Override
    public Boolean visitVoidType(final OCVoidType ocVoidType) {
        Label_0028: {
            try {
                if (this.myType == null) {
                    break Label_0028;
                }
                final OCVoidType ocVoidType2 = ocVoidType;
                final Class<? extends OCVoidType> clazz = ocVoidType2.getClass();
                final OCTypeEqualityVisitor ocTypeEqualityVisitor = this;
                final OCType ocType = ocTypeEqualityVisitor.myType;
                final Class<? extends OCType> clazz2 = ocType.getClass();
                if (clazz == clazz2) {
                    break Label_0028;
                }
                break Label_0028;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCVoidType ocVoidType2 = ocVoidType;
                final Class<? extends OCVoidType> clazz = ocVoidType2.getClass();
                final OCTypeEqualityVisitor ocTypeEqualityVisitor = this;
                final OCType ocType = ocTypeEqualityVisitor.myType;
                final Class<? extends OCType> clazz2 = ocType.getClass();
                if (clazz == clazz2) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
