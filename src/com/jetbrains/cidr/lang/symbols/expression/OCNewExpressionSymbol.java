// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.expression;

import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Producer;
import com.jetbrains.cidr.lang.psi.impl.OCCppNewExpressionImpl;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.resolve.OCArgumentsList;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.util.OCExpressionEvaluator;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.project.Project;
import java.util.List;
import com.jetbrains.cidr.lang.types.OCType;

public class OCNewExpressionSymbol extends OCExpressionSymbol
{
    private OCType myType;
    private int myArrayLengths;
    private List<OCExpressionSymbol> myArguments;
    
    public OCNewExpressionSymbol() {
    }
    
    public OCNewExpressionSymbol(@Nullable final Project project, @Nullable final VirtualFile virtualFile, final long n, @Nullable final String s, @NotNull final OCType myType, final int myArrayLengths, @NotNull final List<OCExpressionSymbol> myArguments) {
        if (myType == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/symbols/expression/OCNewExpressionSymbol", "<init>"));
        }
        if (myArguments == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "arguments", "com/jetbrains/cidr/lang/symbols/expression/OCNewExpressionSymbol", "<init>"));
        }
        super(project, virtualFile, n, s);
        this.myType = myType;
        this.myArrayLengths = myArrayLengths;
        this.myArguments = myArguments;
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/symbols/expression/OCNewExpressionSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/symbols/expression/OCNewExpressionSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/symbols/expression/OCNewExpressionSymbol", "deepEqualStep"));
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
        final OCNewExpressionSymbol ocNewExpressionSymbol = (OCNewExpressionSymbol)o;
        final OCNewExpressionSymbol ocNewExpressionSymbol2 = (OCNewExpressionSymbol)o2;
        try {
            if (ocNewExpressionSymbol.myArrayLengths != ocNewExpressionSymbol2.myArrayLengths) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw b(ex5);
        }
        try {
            if (!comparator.equalObjects(ocNewExpressionSymbol.myType, (DeepEqual.Equality<Object>)ocNewExpressionSymbol2.myType)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw b(ex6);
        }
        try {
            if (!comparator.equalObjects(ocNewExpressionSymbol.myArguments, ocNewExpressionSymbol2.myArguments)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex7) {
            throw b(ex7);
        }
        return true;
    }
    
    @Nullable
    @Override
    public <T> T evaluate(@NotNull final OCExpressionEvaluator.CachingEvaluator<T> cachingEvaluator) {
        try {
            if (cachingEvaluator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "evaluator", "com/jetbrains/cidr/lang/symbols/expression/OCNewExpressionSymbol", "evaluate"));
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
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/expression/OCNewExpressionSymbol", "getResolvedType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final OCType resolve = this.myType.resolve(ocResolveContext);
        final OCArgumentsList<OCExpressionSymbol> expandVariadicExpressions = OCArgumentsList.expandVariadicExpressions(this.myArguments, ocResolveContext);
        Label_0136: {
            Label_0097: {
                Label_0091: {
                    try {
                        if (!(resolve instanceof OCStructType)) {
                            break Label_0097;
                        }
                        final OCStructType ocStructType = (OCStructType)resolve;
                        final OCStructType ocStructType2 = ocStructType;
                        final OCArgumentsList<OCExpressionSymbol> list = expandVariadicExpressions;
                        final OCResolveContext ocResolveContext2 = ocResolveContext;
                        final boolean b = true;
                        final boolean b2 = true;
                        final Producer<Boolean> producer = null;
                        final OCSymbol ocSymbol = ocStructType2.findConstructor(list, ocResolveContext2, b, b2, producer);
                        if (ocSymbol == null) {
                            break Label_0091;
                        }
                        return OCCppNewExpressionImpl.getNewExprType(resolve, this.myArrayLengths);
                    }
                    catch (IllegalArgumentException ex2) {
                        throw b(ex2);
                    }
                    try {
                        final OCStructType ocStructType = (OCStructType)resolve;
                        final OCStructType ocStructType2 = ocStructType;
                        final OCArgumentsList<OCExpressionSymbol> list = expandVariadicExpressions;
                        final OCResolveContext ocResolveContext2 = ocResolveContext;
                        final boolean b = true;
                        final boolean b2 = true;
                        final Producer<Boolean> producer = null;
                        final OCSymbol ocSymbol = ocStructType2.findConstructor(list, ocResolveContext2, b, b2, producer);
                        if (ocSymbol == null) {
                            return null;
                        }
                        return OCCppNewExpressionImpl.getNewExprType(resolve, this.myArrayLengths);
                    }
                    catch (IllegalArgumentException ex3) {
                        throw b(ex3);
                    }
                }
                try {
                    if (expandVariadicExpressions.getCount() != 1) {
                        return OCCppNewExpressionImpl.getNewExprType(resolve, this.myArrayLengths);
                    }
                    final OCStructType ocStructType3 = (OCStructType)resolve;
                    final OCArgumentsList<OCExpressionSymbol> list2 = expandVariadicExpressions;
                    final List<OCType> list3 = list2.getTypes();
                    final int n = 0;
                    final OCType ocType = list3.get(n);
                    final OCType ocType2 = ocType;
                    final OCResolveContext ocResolveContext3 = ocResolveContext;
                    final PsiElement psiElement = ocResolveContext3.getElement();
                    final boolean b3 = ocStructType3.isCompatible(ocType2, psiElement);
                    if (!b3) {
                        break Label_0136;
                    }
                    return OCCppNewExpressionImpl.getNewExprType(resolve, this.myArrayLengths);
                }
                catch (IllegalArgumentException ex4) {
                    throw b(ex4);
                }
            }
            try {
                final OCStructType ocStructType3 = (OCStructType)resolve;
                final OCArgumentsList<OCExpressionSymbol> list2 = expandVariadicExpressions;
                final List<OCType> list3 = list2.getTypes();
                final int n = 0;
                final OCType ocType = list3.get(n);
                final OCType ocType2 = ocType;
                final OCResolveContext ocResolveContext3 = ocResolveContext;
                final PsiElement psiElement = ocResolveContext3.getElement();
                final boolean b3 = ocStructType3.isCompatible(ocType2, psiElement);
                if (!b3) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw b(ex5);
            }
        }
        return OCCppNewExpressionImpl.getNewExprType(resolve, this.myArrayLengths);
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
