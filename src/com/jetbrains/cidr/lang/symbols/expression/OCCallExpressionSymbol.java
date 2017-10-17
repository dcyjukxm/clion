// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.expression;

import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.resolve.OCResolveOverloadsUtil;
import com.jetbrains.cidr.lang.resolve.OCExprValueCategory;
import java.util.Iterator;
import com.jetbrains.cidr.lang.types.OCTypeParameterType;
import com.jetbrains.cidr.lang.resolve.references.OCOperatorReference;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import java.util.Collection;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.jetbrains.cidr.lang.types.OCTypeArgument;
import com.jetbrains.cidr.lang.symbols.OCTypeParameterSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import java.util.Collections;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.jetbrains.cidr.lang.types.OCReferenceTypeBuilder;
import com.jetbrains.cidr.lang.psi.impl.OCCallExpressionImpl;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.types.OCTypeOwner;
import com.jetbrains.cidr.lang.resolve.OCArgumentsList;
import com.jetbrains.cidr.lang.types.OCTypeUtils;
import com.jetbrains.cidr.lang.types.OCCppReferenceType;
import com.jetbrains.cidr.lang.psi.impl.OCReferenceExpressionImpl;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.util.OCExpressionEvaluator;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.project.Project;
import java.util.List;

public class OCCallExpressionSymbol extends OCExpressionSymbol
{
    private OCExpressionSymbol myCalleeSymbol;
    private List<OCExpressionSymbol> myArguments;
    
    public OCCallExpressionSymbol() {
    }
    
    public OCCallExpressionSymbol(@Nullable final Project project, @Nullable final VirtualFile virtualFile, final long n, @Nullable final String s, @NotNull final OCExpressionSymbol myCalleeSymbol, @NotNull final List<OCExpressionSymbol> myArguments) {
        if (myCalleeSymbol == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "calleeSymbol", "com/jetbrains/cidr/lang/symbols/expression/OCCallExpressionSymbol", "<init>"));
        }
        if (myArguments == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "arguments", "com/jetbrains/cidr/lang/symbols/expression/OCCallExpressionSymbol", "<init>"));
        }
        super(project, virtualFile, n, s);
        this.myCalleeSymbol = myCalleeSymbol;
        this.myArguments = myArguments;
    }
    
    public OCExpressionSymbol getCalleeSymbol() {
        return this.myCalleeSymbol;
    }
    
    public List<OCExpressionSymbol> getArguments() {
        return this.myArguments;
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/symbols/expression/OCCallExpressionSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/symbols/expression/OCCallExpressionSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/symbols/expression/OCCallExpressionSymbol", "deepEqualStep"));
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
        final OCCallExpressionSymbol ocCallExpressionSymbol = (OCCallExpressionSymbol)o;
        final OCCallExpressionSymbol ocCallExpressionSymbol2 = (OCCallExpressionSymbol)o2;
        try {
            if (!comparator.equalObjects(ocCallExpressionSymbol.myCalleeSymbol, (DeepEqual.Equality<Object>)ocCallExpressionSymbol2.myCalleeSymbol)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw b(ex5);
        }
        try {
            if (!comparator.equalObjects(ocCallExpressionSymbol.myArguments, ocCallExpressionSymbol2.myArguments)) {
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
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "evaluator", "com/jetbrains/cidr/lang/symbols/expression/OCCallExpressionSymbol", "evaluate"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return cachingEvaluator.evalCall(this);
    }
    
    @Nullable
    @Override
    public OCType getResolvedType(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/expression/OCCallExpressionSymbol", "getResolvedType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        OCSymbol ocSymbol = null;
        try {
            if (this.myCalleeSymbol == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        if (this.myCalleeSymbol instanceof OCReferenceExpressionSymbol) {
            ocSymbol = ((OCReferenceExpressionSymbol)this.myCalleeSymbol).resolveToSymbol(ocResolveContext);
            OCResolveContext use = null;
            Label_0098: {
                try {
                    if (ocSymbol != null) {
                        use = ocResolveContext.useFor(ocSymbol);
                        break Label_0098;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw b(ex3);
                }
                use = null;
            }
            final OCResolveContext ocResolveContext2 = use;
            OCType resolve = null;
            Label_0121: {
                try {
                    if (ocSymbol != null) {
                        resolve = OCReferenceExpressionImpl.getReferenceExpressionType(ocSymbol).resolve(ocResolveContext2);
                        break Label_0121;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw b(ex4);
                }
                resolve = null;
            }
            OCType ocType = resolve;
            if (ocType instanceof OCCppReferenceType) {
                ocType = ((OCCppReferenceType)ocType).getRefType();
            }
            if (OCTypeUtils.isUnresolvedLambdaAutoType(ocType)) {
                final OCArgumentsList<OCExpressionSymbol> a = this.a(ocResolveContext);
                if (a != null) {
                    ocType = OCTypeUtils.resolveLambdaAutoType(ocType, ocResolveContext, a, false);
                }
            }
            if (ocType instanceof OCPointerType) {
                ocType = ((OCPointerType)ocType).getRefType();
            }
            try {
                if (ocType instanceof OCFunctionType) {
                    return OCCallExpressionImpl.getCallExprType(ocType, ocSymbol, ocResolveContext);
                }
            }
            catch (IllegalArgumentException ex5) {
                throw b(ex5);
            }
            if (ocSymbol == null) {
                final OCType resolve2 = new OCReferenceTypeBuilder(((OCReferenceExpressionSymbol)this.myCalleeSymbol).getReference()).build().resolve(ocResolveContext);
                try {
                    if (!resolve2.isUnknown()) {
                        return resolve2;
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw b(ex6);
                }
            }
        }
        final OCType resolvedType = this.myCalleeSymbol.getResolvedType(ocResolveContext);
        OCType terminalType = null;
        Label_0272: {
            try {
                if (resolvedType != null) {
                    terminalType = resolvedType.getTerminalType();
                    break Label_0272;
                }
            }
            catch (IllegalArgumentException ex7) {
                throw b(ex7);
            }
            terminalType = null;
        }
        OCType ocType2 = terminalType;
        if (ocType2 instanceof OCFunctionType) {
            final OCFunctionSymbol ocFunctionSymbol = new OCFunctionSymbol(null, null, 0L, null, OCQualifiedName.with(null), (List<OCTypeParameterSymbol>)Collections.emptyList(), null, 0, 0, Collections.emptyList(), (OCFunctionType)ocType2, Collections.emptyList(), OCSymbolKind.FUNCTION_DECLARATION, null);
            try {
                if (this.resolveOverloads((Collection<OCSymbol>)Collections.singletonList(ocFunctionSymbol), ocResolveContext) == null) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex8) {
                throw b(ex8);
            }
        }
        else if (ocType2 instanceof OCStructType) {
            final OCArgumentsList<OCExpressionSymbol> a2 = this.a(ocResolveContext);
            try {
                if (a2 == null) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex9) {
                throw b(ex9);
            }
            final ArrayList<OCFunctionType> list = new ArrayList<OCFunctionType>();
            List<Object> list2 = null;
            list.add((OCFunctionType)ocType2);
            list.addAll((Collection<?>)a2.getTypes());
            if (a2.getExprs() != null) {
                list2 = new ArrayList<Object>();
                list2.add(null);
                list2.addAll(a2.getExprs());
            }
            ocSymbol = OCOperatorReference.resolveOperator("()", OCOperatorReference.OperatorPlacement.POSTFIX, (List<OCType>)list, (List<? extends OCTypeOwner>)list2, ocResolveContext);
            OCResolveContext use2 = null;
            Label_0468: {
                try {
                    if (ocSymbol != null) {
                        use2 = ocResolveContext.useFor(ocSymbol);
                        break Label_0468;
                    }
                }
                catch (IllegalArgumentException ex10) {
                    throw b(ex10);
                }
                use2 = null;
            }
            final OCResolveContext ocResolveContext3 = use2;
            OCType resolve3 = null;
            Label_0491: {
                try {
                    if (ocSymbol != null) {
                        resolve3 = OCReferenceExpressionImpl.getReferenceExpressionType(ocSymbol).resolve(ocResolveContext3);
                        break Label_0491;
                    }
                }
                catch (IllegalArgumentException ex11) {
                    throw b(ex11);
                }
                resolve3 = null;
            }
            ocType2 = resolve3;
        }
        try {
            if (ocType2 != null) {
                return OCCallExpressionImpl.getCallExprType(ocType2, ocSymbol, ocResolveContext);
            }
        }
        catch (IllegalArgumentException ex12) {
            throw b(ex12);
        }
        return null;
    }
    
    @Nullable
    private OCArgumentsList<OCExpressionSymbol> a(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/expression/OCCallExpressionSymbol", "getArgumentList"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final OCArgumentsList<OCExpressionSymbol> expandVariadicExpressions = OCArgumentsList.expandVariadicExpressions(this.myArguments, ocResolveContext);
        for (final OCType ocType : expandVariadicExpressions.getTypes()) {
            try {
                if (!(ocType instanceof OCTypeParameterType)) {
                    continue;
                }
                ocResolveContext.addTypeDependency(((OCTypeParameterType)ocType).getSymbol());
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        for (final OCType ocType2 : expandVariadicExpressions.getTypes()) {
            try {
                if (ocType2 == null) {
                    return null;
                }
                continue;
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
        }
        return expandVariadicExpressions;
    }
    
    @Nullable
    public OCSymbol resolveOverloads(final Collection<OCSymbol> collection, final OCResolveContext ocResolveContext) {
        final OCArgumentsList<OCExpressionSymbol> a = this.a(ocResolveContext);
        OCType cloneWithAddedCVQualifiers = null;
        OCExprValueCategory classify = null;
        if (this.myCalleeSymbol instanceof OCQualifiedExpressionSymbol) {
            final OCExpressionSymbol qualifier = ((OCQualifiedExpressionSymbol)this.myCalleeSymbol).getQualifier();
            OCSymbol resolveToSymbol = null;
            Label_0059: {
                try {
                    if (qualifier instanceof OCReferenceExpressionSymbol) {
                        resolveToSymbol = ((OCReferenceExpressionSymbol)qualifier).resolveToSymbol(ocResolveContext);
                        break Label_0059;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                resolveToSymbol = null;
            }
            final OCSymbol ocSymbol = resolveToSymbol;
            OCType resolve = null;
            Label_0085: {
                try {
                    if (ocSymbol != null) {
                        resolve = ocSymbol.getType().resolve(ocResolveContext);
                        break Label_0085;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
                resolve = null;
            }
            cloneWithAddedCVQualifiers = resolve;
            classify = OCExprValueCategory.classify(qualifier, ocResolveContext);
        }
        final OCFunctionSymbol peekOuterFunction = ocResolveContext.peekOuterFunction();
        Label_0174: {
            Label_0150: {
                try {
                    if (peekOuterFunction == null) {
                        break Label_0174;
                    }
                    if (cloneWithAddedCVQualifiers != null) {
                        break Label_0150;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw b(ex3);
                }
                final OCSymbolWithQualifiedName<OCElement> resolvedOwner = peekOuterFunction.getResolvedOwner(ocResolveContext, false);
                OCType type = null;
                Label_0145: {
                    try {
                        if (resolvedOwner != null) {
                            type = resolvedOwner.getType();
                            break Label_0145;
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw b(ex4);
                    }
                    type = null;
                }
                cloneWithAddedCVQualifiers = type;
                classify = null;
            }
            if (cloneWithAddedCVQualifiers != null) {
                cloneWithAddedCVQualifiers = cloneWithAddedCVQualifiers.cloneWithAddedCVQualifiers(peekOuterFunction.getType().getCVQualifiers(), this.myProject);
            }
            try {
                if (a != null) {
                    return OCResolveOverloadsUtil.resolveOverloads(collection, a, cloneWithAddedCVQualifiers, classify, null, true, true, true, true, false, ocResolveContext);
                }
            }
            catch (IllegalArgumentException ex5) {
                throw b(ex5);
            }
        }
        return null;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
