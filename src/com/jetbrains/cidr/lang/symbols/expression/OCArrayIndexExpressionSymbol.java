// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.expression;

import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.psi.impl.OCArraySelectionExpressionImpl;
import com.jetbrains.cidr.lang.psi.impl.OCCallExpressionImpl;
import com.jetbrains.cidr.lang.psi.impl.OCReferenceExpressionImpl;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.types.OCTypeOwner;
import java.util.List;
import java.util.Arrays;
import com.jetbrains.cidr.lang.resolve.references.OCOperatorReference;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.types.OCCppReferenceType;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.util.OCExpressionEvaluator;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.project.Project;

public class OCArrayIndexExpressionSymbol extends OCExpressionSymbol
{
    private OCExpressionSymbol myArraySymbol;
    private OCExpressionSymbol myIndexSymbol;
    
    public OCArrayIndexExpressionSymbol() {
    }
    
    public OCArrayIndexExpressionSymbol(@Nullable final Project project, @Nullable final VirtualFile virtualFile, final long n, @Nullable final String s, @NotNull final OCExpressionSymbol myArraySymbol, @NotNull final OCExpressionSymbol myIndexSymbol) {
        if (myArraySymbol == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "arraySymbol", "com/jetbrains/cidr/lang/symbols/expression/OCArrayIndexExpressionSymbol", "<init>"));
        }
        if (myIndexSymbol == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "indexSymbol", "com/jetbrains/cidr/lang/symbols/expression/OCArrayIndexExpressionSymbol", "<init>"));
        }
        super(project, virtualFile, n, s);
        this.myArraySymbol = myArraySymbol;
        this.myIndexSymbol = myIndexSymbol;
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/symbols/expression/OCArrayIndexExpressionSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/symbols/expression/OCArrayIndexExpressionSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/symbols/expression/OCArrayIndexExpressionSymbol", "deepEqualStep"));
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
        final OCArrayIndexExpressionSymbol ocArrayIndexExpressionSymbol = (OCArrayIndexExpressionSymbol)o;
        final OCArrayIndexExpressionSymbol ocArrayIndexExpressionSymbol2 = (OCArrayIndexExpressionSymbol)o2;
        try {
            if (!comparator.equalObjects(ocArrayIndexExpressionSymbol.myArraySymbol, (DeepEqual.Equality<Object>)ocArrayIndexExpressionSymbol2.myArraySymbol)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw b(ex5);
        }
        try {
            if (!comparator.equalObjects(ocArrayIndexExpressionSymbol.myIndexSymbol, (DeepEqual.Equality<Object>)ocArrayIndexExpressionSymbol2.myIndexSymbol)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw b(ex6);
        }
        return true;
    }
    
    @Nullable
    @Override
    public <T> T evaluate(@NotNull final OCExpressionEvaluator.CachingEvaluator<T> cachingEvaluator) {
        try {
            if (cachingEvaluator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "evaluator", "com/jetbrains/cidr/lang/symbols/expression/OCArrayIndexExpressionSymbol", "evaluate"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return null;
    }
    
    @Nullable
    @Override
    public OCType getResolvedType(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/expression/OCArrayIndexExpressionSymbol", "getResolvedType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return this.getResolvedTypeWithKind(ocResolveContext).type;
    }
    
    @NotNull
    public ResolveResult getResolvedTypeWithKind(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/expression/OCArrayIndexExpressionSymbol", "getResolvedTypeWithKind"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        OCType ocType = this.myArraySymbol.getResolvedType(ocResolveContext);
        final OCType resolvedType = this.myIndexSymbol.getResolvedType(ocResolveContext);
        if (ocType instanceof OCCppReferenceType) {
            ocType = ((OCCppReferenceType)ocType).getRefType();
        }
        Kind builtin = null;
        OCType arrayIndexExprType = null;
        Label_0281: {
            Label_0257: {
                try {
                    if (!(ocType instanceof OCStructType) || resolvedType == null) {
                        break Label_0257;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
                final OCFunctionSymbol resolveOperator = OCOperatorReference.resolveOperator("[]", OCOperatorReference.OperatorPlacement.POSTFIX, Arrays.asList(ocType, resolvedType), Arrays.asList(this.myArraySymbol, this.myIndexSymbol), ocResolveContext);
                OCResolveContext use = null;
                Label_0161: {
                    try {
                        if (resolveOperator != null) {
                            use = ocResolveContext.useFor(resolveOperator);
                            break Label_0161;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw b(ex3);
                    }
                    use = null;
                }
                final OCResolveContext ocResolveContext2 = use;
                OCType resolve = null;
                Label_0186: {
                    try {
                        if (resolveOperator != null) {
                            resolve = OCReferenceExpressionImpl.getReferenceExpressionType(resolveOperator).resolve(ocResolveContext2);
                            break Label_0186;
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw b(ex4);
                    }
                    resolve = null;
                }
                final OCType ocType2 = resolve;
                ResolveResult resolveResult = null;
                Label_0222: {
                    try {
                        if (ocType2 == null) {
                            break Label_0257;
                        }
                        final Kind kind = Kind.Custom;
                        final OCType ocType3 = ocType2;
                        final OCFunctionSymbol ocFunctionSymbol = resolveOperator;
                        final OCResolveContext ocResolveContext3 = ocResolveContext;
                        final OCType ocType4 = OCCallExpressionImpl.getCallExprType(ocType3, ocFunctionSymbol, ocResolveContext3);
                        resolveResult = new ResolveResult(kind, ocType4);
                        if (resolveResult == null) {
                            break Label_0222;
                        }
                        return resolveResult;
                    }
                    catch (IllegalArgumentException ex5) {
                        throw b(ex5);
                    }
                    try {
                        final Kind kind = Kind.Custom;
                        final OCType ocType3 = ocType2;
                        final OCFunctionSymbol ocFunctionSymbol = resolveOperator;
                        final OCResolveContext ocResolveContext3 = ocResolveContext;
                        final OCType ocType4 = OCCallExpressionImpl.getCallExprType(ocType3, ocFunctionSymbol, ocResolveContext3);
                        resolveResult = new ResolveResult(kind, ocType4);
                        if (resolveResult == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/expression/OCArrayIndexExpressionSymbol", "getResolvedTypeWithKind"));
                        }
                    }
                    catch (IllegalArgumentException ex6) {
                        throw b(ex6);
                    }
                }
                return resolveResult;
                try {
                    builtin = Kind.Builtin;
                    if (ocType != null) {
                        arrayIndexExprType = OCArraySelectionExpressionImpl.getArrayIndexExprType(ocType, ocResolveContext);
                        break Label_0281;
                    }
                }
                catch (IllegalArgumentException ex7) {
                    throw b(ex7);
                }
            }
            arrayIndexExprType = null;
        }
        final ResolveResult resolveResult2 = new ResolveResult(builtin, arrayIndexExprType);
        if (resolveResult2 == null) {
            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/expression/OCArrayIndexExpressionSymbol", "getResolvedTypeWithKind"));
        }
        return resolveResult2;
    }
    
    public OCExpressionSymbol getIndexSymbol() {
        return this.myIndexSymbol;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
    
    public enum Kind
    {
        Builtin, 
        Custom;
    }
    
    public static class ResolveResult
    {
        @NotNull
        public final Kind kind;
        @Nullable
        public final OCType type;
        
        public ResolveResult(@NotNull final Kind kind, @Nullable final OCType type) {
            if (kind == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "kind", "com/jetbrains/cidr/lang/symbols/expression/OCArrayIndexExpressionSymbol$ResolveResult", "<init>"));
            }
            this.kind = kind;
            this.type = type;
        }
    }
}
