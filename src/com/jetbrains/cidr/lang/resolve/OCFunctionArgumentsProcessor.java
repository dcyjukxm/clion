// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve;

import com.jetbrains.cidr.lang.types.visitors.OCTypeVisitor;
import com.jetbrains.cidr.lang.types.OCExpansionPackType;
import com.jetbrains.cidr.lang.types.OCTypeParameterType;
import com.jetbrains.cidr.lang.types.visitors.OCBooleanTypeVisitor;
import com.jetbrains.cidr.lang.types.OCVariadicType;
import com.jetbrains.cidr.lang.types.OCTypeOwner;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.OCType;
import java.util.List;

public class OCFunctionArgumentsProcessor
{
    public static <E extends OCTypeOwner> boolean processArguments(@NotNull final List<? extends OCType> list, @Nullable final List<OCDeclaratorSymbol> list2, @NotNull final OCArgumentsList<E> list3, @NotNull final OCResolveContext ocResolveContext, @NotNull final ArgumentsProcessor<E> argumentsProcessor) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameterTypes", "com/jetbrains/cidr/lang/resolve/OCFunctionArgumentsProcessor", "processArguments"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (list3 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "arguments", "com/jetbrains/cidr/lang/resolve/OCFunctionArgumentsProcessor", "processArguments"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/OCFunctionArgumentsProcessor", "processArguments"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (argumentsProcessor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/resolve/OCFunctionArgumentsProcessor", "processArguments"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        int i = 0;
        int n = 0;
        while (i < list.size()) {
            final OCType resolve = ((OCType)list.get(i)).resolve(ocResolveContext.clearSubstitution());
            OCDeclaratorSymbol ocDeclaratorSymbol = null;
            Label_0255: {
                Label_0236: {
                    try {
                        if (list2 == null) {
                            break Label_0236;
                        }
                        final int n2 = i;
                        final List<OCDeclaratorSymbol> list4 = list2;
                        final int n3 = list4.size();
                        if (n2 < n3) {
                            break Label_0236;
                        }
                        break Label_0236;
                    }
                    catch (IllegalArgumentException ex5) {
                        throw a(ex5);
                    }
                    try {
                        final int n2 = i;
                        final List<OCDeclaratorSymbol> list4 = list2;
                        final int n3 = list4.size();
                        if (n2 < n3) {
                            ocDeclaratorSymbol = list2.get(i);
                            break Label_0255;
                        }
                    }
                    catch (IllegalArgumentException ex6) {
                        throw a(ex6);
                    }
                }
                ocDeclaratorSymbol = null;
            }
            final OCDeclaratorSymbol ocDeclaratorSymbol2 = ocDeclaratorSymbol;
            Label_0460: {
                Label_0454: {
                    try {
                        if (!(resolve instanceof OCVariadicType)) {
                            break Label_0460;
                        }
                        if (i != list.size() - 1) {
                            break Label_0454;
                        }
                    }
                    catch (IllegalArgumentException ex7) {
                        throw a(ex7);
                    }
                    final OCType underlyingType = ((OCVariadicType)resolve).getUnderlyingType();
                    for (int j = n; j < list3.getCount(); ++j) {
                        OCType ocType = list3.getTypes().get(j).getGuessedType();
                        OCTypeOwner ocTypeOwner = null;
                        Label_0356: {
                            try {
                                if (list3.getExprs() != null) {
                                    ocTypeOwner = list3.getExprs().get(j);
                                    break Label_0356;
                                }
                            }
                            catch (IllegalArgumentException ex8) {
                                throw a(ex8);
                            }
                            ocTypeOwner = null;
                        }
                        final OCTypeOwner ocTypeOwner2 = ocTypeOwner;
                        if (ocType instanceof OCVariadicType) {
                            ocType = ((OCVariadicType)ocType).getUnderlyingType();
                        }
                        try {
                            if (!argumentsProcessor.process(underlyingType, ocDeclaratorSymbol2, ocType, (E)ocTypeOwner2, true)) {
                                return false;
                            }
                        }
                        catch (IllegalArgumentException ex9) {
                            throw a(ex9);
                        }
                    }
                    try {
                        if (n != list3.getCount()) {
                            break;
                        }
                        final OCType ocType2 = underlyingType;
                        final ArgumentsProcessor<E> argumentsProcessor2 = argumentsProcessor;
                        final OCDeclaratorSymbol ocDeclaratorSymbol3 = ocDeclaratorSymbol2;
                        final OCBooleanTypeVisitor ocBooleanTypeVisitor = new OCBooleanTypeVisitor() {
                            final /* synthetic */ ArgumentsProcessor val$processor;
                            final /* synthetic */ OCDeclaratorSymbol val$paramSymbol;
                            
                            @Override
                            public Boolean visitTypeParameterType(final OCTypeParameterType ocTypeParameterType) {
                                return !this.val$processor.process(ocTypeParameterType, this.val$paramSymbol, new OCExpansionPackType(), null, true);
                            }
                        };
                        final Boolean b = ocType2.accept((OCTypeVisitor<Boolean>)ocBooleanTypeVisitor);
                        final Boolean b2 = b;
                        final boolean b3 = b2;
                        if (b3) {
                            return false;
                        }
                        break;
                    }
                    catch (IllegalArgumentException ex10) {
                        throw a(ex10);
                    }
                    try {
                        final OCType ocType2 = underlyingType;
                        final ArgumentsProcessor<E> argumentsProcessor2 = argumentsProcessor;
                        final OCDeclaratorSymbol ocDeclaratorSymbol3 = ocDeclaratorSymbol2;
                        final OCBooleanTypeVisitor ocBooleanTypeVisitor = new OCBooleanTypeVisitor() {
                            final /* synthetic */ ArgumentsProcessor val$processor;
                            final /* synthetic */ OCDeclaratorSymbol val$paramSymbol;
                            
                            @Override
                            public Boolean visitTypeParameterType(final OCTypeParameterType ocTypeParameterType) {
                                return !argumentsProcessor2.process(ocTypeParameterType, ocDeclaratorSymbol3, new OCExpansionPackType(), null, true);
                            }
                        };
                        final Boolean b = ocType2.accept((OCTypeVisitor<Boolean>)ocBooleanTypeVisitor);
                        final Boolean b2 = b;
                        final boolean b3 = b2;
                        if (b3) {
                            return false;
                        }
                        break;
                    }
                    catch (IllegalArgumentException ex11) {
                        throw a(ex11);
                    }
                }
                ++i;
                continue;
                try {
                    if (n >= list3.getCount()) {
                        break;
                    }
                }
                catch (IllegalArgumentException ex12) {
                    throw a(ex12);
                }
            }
            final OCType guessedType = list3.getTypes().get(n).getGuessedType();
            OCTypeOwner ocTypeOwner3 = null;
            Label_0524: {
                try {
                    if (list3.getExprs() != null) {
                        ocTypeOwner3 = list3.getExprs().get(n);
                        break Label_0524;
                    }
                }
                catch (IllegalArgumentException ex13) {
                    throw a(ex13);
                }
                ocTypeOwner3 = null;
            }
            final OCTypeOwner ocTypeOwner4 = ocTypeOwner3;
            try {
                if (!argumentsProcessor.process(resolve, ocDeclaratorSymbol2, guessedType, (E)ocTypeOwner4, false)) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex14) {
                throw a(ex14);
            }
            ++i;
            ++n;
        }
        return true;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    @FunctionalInterface
    public interface ArgumentsProcessor<E extends OCTypeOwner>
    {
        boolean process(@NotNull final OCType p0, @Nullable final OCDeclaratorSymbol p1, @NotNull final OCType p2, @Nullable final E p3, final boolean p4);
    }
}
