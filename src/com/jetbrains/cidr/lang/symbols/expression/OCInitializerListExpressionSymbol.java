// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.expression;

import com.jetbrains.cidr.lang.types.OCTypeOwner;
import com.jetbrains.cidr.lang.psi.impl.OCCompoundInitializerImpl;
import com.jetbrains.cidr.lang.resolve.OCArgumentsList;
import com.jetbrains.cidr.lang.types.OCUnknownType;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.util.OCExpressionEvaluator;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.project.Project;
import java.util.List;

public class OCInitializerListExpressionSymbol extends OCExpressionSymbol
{
    private List<OCExpressionSymbol> myInitializers;
    
    public OCInitializerListExpressionSymbol() {
    }
    
    public OCInitializerListExpressionSymbol(@Nullable final Project project, @Nullable final VirtualFile virtualFile, final long n, @Nullable final String s, final List<OCExpressionSymbol> myInitializers) {
        super(project, virtualFile, n, s);
        this.myInitializers = myInitializers;
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/symbols/expression/OCInitializerListExpressionSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/symbols/expression/OCInitializerListExpressionSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/symbols/expression/OCInitializerListExpressionSymbol", "deepEqualStep"));
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
        final OCInitializerListExpressionSymbol ocInitializerListExpressionSymbol = (OCInitializerListExpressionSymbol)o;
        final OCInitializerListExpressionSymbol ocInitializerListExpressionSymbol2 = (OCInitializerListExpressionSymbol)o2;
        try {
            if (!comparator.equalObjects(ocInitializerListExpressionSymbol.myInitializers, ocInitializerListExpressionSymbol2.myInitializers)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw b(ex5);
        }
        return true;
    }
    
    @Nullable
    @Override
    public <T> T evaluate(@NotNull final OCExpressionEvaluator.CachingEvaluator<T> cachingEvaluator) {
        try {
            if (cachingEvaluator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "evaluator", "com/jetbrains/cidr/lang/symbols/expression/OCInitializerListExpressionSymbol", "evaluate"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (this.myInitializers.size() == 1) {
                return this.myInitializers.get(0).evaluate(cachingEvaluator);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return null;
    }
    
    @Nullable
    @Override
    public OCType getResolvedType(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/expression/OCInitializerListExpressionSymbol", "getResolvedType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (!OCCompilerFeatures.supportsInitializerLists((PsiFile)this.getContainingOCFile())) {
                return OCUnknownType.INSTANCE;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return OCCompoundInitializerImpl.getCompoundInitializerType(OCArgumentsList.expandVariadicExpressions(this.myInitializers, ocResolveContext), ocResolveContext).resolve(ocResolveContext);
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
