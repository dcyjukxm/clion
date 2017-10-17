// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve;

import com.intellij.util.containers.hash.HashSet;
import java.util.Set;
import com.jetbrains.cidr.lang.types.visitors.OCTypeSubstitution;
import com.jetbrains.cidr.lang.types.OCTypeUtils;
import com.intellij.openapi.util.Pair;
import com.intellij.util.Function;
import com.jetbrains.cidr.lang.types.OCUnknownType;
import com.jetbrains.cidr.lang.types.visitors.OCNonPrimitiveTypeCloneVisitor;
import com.jetbrains.cidr.lang.types.visitors.OCSimpleTypeSubstitution;
import com.jetbrains.cidr.lang.types.visitors.OCTypeVisitor;
import com.jetbrains.cidr.lang.symbols.OCTypeParameterSymbol;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.jetbrains.cidr.lang.types.OCExpansionPackType;
import java.util.Map;
import com.jetbrains.cidr.lang.types.visitors.OCBooleanTypeVisitor;
import java.util.HashMap;
import com.intellij.openapi.util.Ref;
import java.util.Collection;
import com.jetbrains.cidr.lang.types.OCExpressionTypeArgument;
import com.jetbrains.cidr.lang.types.OCVariadicType;
import com.jetbrains.cidr.lang.types.OCTypeArgument;
import com.jetbrains.cidr.lang.symbols.expression.OCVariadicPackExpressionSymbol;
import com.jetbrains.cidr.lang.symbols.expression.OCExpressionSymbol;
import java.util.Iterator;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCVariadicPackExpression;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.psi.OCExpression;
import java.util.Collections;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.OCType;
import java.util.List;
import com.jetbrains.cidr.lang.types.OCTypeOwner;

public class OCArgumentsList<E extends OCTypeOwner>
{
    @NotNull
    private List<OCType> types;
    @Nullable
    private List<E> exprs;
    private boolean nonExpandedVariadics;
    
    public OCArgumentsList(@NotNull final List<OCType> list, @Nullable final List<E> list2, final boolean nonExpandedVariadics) {
        if (list == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "types", "com/jetbrains/cidr/lang/resolve/OCArgumentsList", "<init>"));
        }
        this.types = Collections.unmodifiableList((List<? extends OCType>)list);
        this.exprs = ((list2 != null) ? Collections.unmodifiableList((List<? extends E>)list2) : null);
        this.nonExpandedVariadics = nonExpandedVariadics;
    }
    
    public OCArgumentsList(@NotNull final List<OCType> list, @Nullable final List<E> list2) {
        if (list == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "types", "com/jetbrains/cidr/lang/resolve/OCArgumentsList", "<init>"));
        }
        this(list, list2, false);
    }
    
    @NotNull
    public static OCArgumentsList<OCExpression> getArgumentList(@NotNull final List<OCExpression> list) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expressionsList", "com/jetbrains/cidr/lang/resolve/OCArgumentsList", "getArgumentList"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCArgumentsList<OCExpression> argumentList;
        try {
            argumentList = getArgumentList(list, null);
            if (argumentList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCArgumentsList", "getArgumentList"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return argumentList;
    }
    
    @NotNull
    public static OCArgumentsList<OCExpression> getArgumentList(@NotNull final List<OCExpression> list, @Nullable final OCResolveContext ocResolveContext) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expressionsList", "com/jetbrains/cidr/lang/resolve/OCArgumentsList", "getArgumentList"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final ArrayList<OCType> list2 = new ArrayList<OCType>();
        boolean b = false;
        final ArrayList<OCExpression> list3 = new ArrayList<OCExpression>();
        for (final OCExpression ocExpression : list) {
            if (ocExpression instanceof OCVariadicPackExpression) {
                b = true;
            }
            else {
                ArrayList<OCType> list4 = null;
                OCExpression ocExpression2 = null;
                OCResolveContext ocResolveContext2 = null;
                Label_0140: {
                    try {
                        list3.add(ocExpression);
                        list4 = list2;
                        ocExpression2 = ocExpression;
                        if (ocResolveContext != null) {
                            ocResolveContext2 = ocResolveContext;
                            break Label_0140;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    ocResolveContext2 = new OCResolveContext((PsiElement)ocExpression);
                }
                list4.add(ocExpression2.getResolvedType(ocResolveContext2));
            }
        }
        OCArgumentsList list5;
        try {
            list5 = new OCArgumentsList(list2, (List<OCTypeOwner>)list3, b);
            if (list5 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCArgumentsList", "getArgumentList"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return (OCArgumentsList<OCExpression>)list5;
    }
    
    @NotNull
    public static OCArgumentsList<OCExpressionSymbol> expandVariadicExpressions(@NotNull final List<OCExpressionSymbol> list, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expressionsList", "com/jetbrains/cidr/lang/resolve/OCArgumentsList", "expandVariadicExpressions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/OCArgumentsList", "expandVariadicExpressions"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final ArrayList<OCType> list2 = new ArrayList<OCType>();
        final ArrayList<OCVariadicPackExpressionSymbol> list3 = new ArrayList<OCVariadicPackExpressionSymbol>();
        boolean b = false;
        for (final OCExpressionSymbol ocExpressionSymbol : list) {
            if (ocExpressionSymbol instanceof OCVariadicPackExpressionSymbol) {
                final List<OCType> expandedResolvedTypes = ((OCVariadicPackExpressionSymbol)ocExpressionSymbol).getExpandedResolvedTypes(ocResolveContext);
                if (expandedResolvedTypes != null) {
                    for (final OCType ocType : expandedResolvedTypes) {
                        list3.add((OCVariadicPackExpressionSymbol)((OCVariadicPackExpressionSymbol)ocExpressionSymbol).getExpression());
                        list2.add(ocType);
                    }
                }
                else {
                    b = true;
                }
            }
            else {
                list3.add((OCVariadicPackExpressionSymbol)ocExpressionSymbol);
                list2.add(ocExpressionSymbol.getResolvedType(ocResolveContext));
            }
        }
        OCArgumentsList list4;
        try {
            list4 = new OCArgumentsList(list2, (List<OCTypeOwner>)list3, b);
            if (list4 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCArgumentsList", "expandVariadicExpressions"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return (OCArgumentsList<OCExpressionSymbol>)list4;
    }
    
    @NotNull
    public static <T extends OCTypeArgument> List<T> expandVariadicTypes(@NotNull final List<T> list, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "types", "com/jetbrains/cidr/lang/resolve/OCArgumentsList", "expandVariadicTypes"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/OCArgumentsList", "expandVariadicTypes"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final ArrayList<T> list2 = new ArrayList<T>();
        Label_0150: {
            List<T> list3 = null;
            Label_0115: {
                try {
                    if (!ocResolveContext.isDontExpandVariadics()) {
                        break Label_0150;
                    }
                    list3 = list;
                    if (list3 == null) {
                        break Label_0115;
                    }
                    return list3;
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    list3 = list;
                    if (list3 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCArgumentsList", "expandVariadicTypes"));
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            return list3;
        }
        for (final OCTypeArgument ocTypeArgument : list) {
            Collection<?> collection = null;
            if (ocTypeArgument instanceof OCVariadicType) {
                final OCType underlyingType = ((OCVariadicType)ocTypeArgument).getUnderlyingType();
                collection = b(underlyingType, ocResolveContext);
                if (collection == null) {
                    collection = a(underlyingType, ocResolveContext);
                }
            }
            else if (ocTypeArgument.isVariadic()) {
                collection = a((OCVariadicPackExpressionSymbol)((OCExpressionTypeArgument)ocTypeArgument).getSymbol(), ocResolveContext);
            }
            try {
                if (collection != null) {
                    list2.addAll(collection);
                    continue;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
            list2.add((T)ocTypeArgument);
        }
        ArrayList<T> list4;
        try {
            list4 = list2;
            if (list4 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCArgumentsList", "expandVariadicTypes"));
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        return list4;
    }
    
    @Nullable
    private static List<? extends OCTypeArgument> b(@NotNull final OCType ocType, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/resolve/OCArgumentsList", "expandExpansionPacks"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/OCArgumentsList", "expandExpansionPacks"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final Ref ref = new Ref();
        final HashMap hashMap = new HashMap();
        ocType.accept((OCTypeVisitor<Object>)new OCBooleanTypeVisitor() {
            @Override
            public Boolean visitExpansionPackType(final OCExpansionPackType ocExpansionPackType) {
                if (ref.isNull() || (int)ref.get() < ocExpansionPackType.getExpansionsCnt()) {
                    ref.set((Object)ocExpansionPackType.getExpansionsCnt());
                }
                return true;
            }
            
            @Override
            public Boolean visitReferenceType(final OCReferenceType ocReferenceType) {
                ocResolveContext.setDontUseSymbolContextsInDepends(true);
                ocReferenceType.getSubstitution().getMinimalDependentSubstitution(ocReferenceType, ocResolveContext).processSubstitutions((Processor<Map.Entry<OCTypeParameterSymbol, OCTypeArgument>>)(entry -> {
                    final OCTypeArgument ocTypeArgument = entry.getValue();
                    if (ocTypeArgument instanceof OCExpansionPackType) {
                        map.put(entry.getKey(), ocTypeArgument);
                    }
                    if (ocTypeArgument instanceof OCType) {
                        ((OCExpansionPackType)ocTypeArgument).accept((OCTypeVisitor<Object>)this);
                    }
                    return true;
                }));
                ocResolveContext.setDontUseSymbolContextsInDepends(false);
                return true;
            }
        });
        if (!ref.isNull()) {
            final ArrayList<Object> list = (ArrayList<Object>)new ArrayList<OCTypeArgument>();
            for (int i = 0; i < (int)ref.get(); ++i) {
                final int n = i;
                OCType resolve = ocType;
                if (!hashMap.isEmpty()) {
                    final HashMap<OCTypeParameterSymbol, OCTypeArgument> hashMap2 = new HashMap<OCTypeParameterSymbol, OCTypeArgument>();
                    final int n2;
                    final Map<OCTypeParameterSymbol, OCTypeArgument> map;
                    hashMap.forEach((ocTypeParameterSymbol, ocExpansionPackType) -> {
                        try {
                            if (n2 < ocExpansionPackType.getExpansionsCnt()) {
                                map.put(ocTypeParameterSymbol, ocExpansionPackType.getExpansions().get(n2));
                            }
                        }
                        catch (IllegalArgumentException ex3) {
                            throw a(ex3);
                        }
                        return;
                    });
                    final OCSimpleTypeSubstitution create = OCSimpleTypeSubstitution.create(hashMap2);
                    resolve = create.substitute(ocType, true, ocResolveContext).resolve(ocResolveContext);
                    if (resolve instanceof OCVariadicType) {
                        resolve = new OCVariadicType(create.substitute(((OCVariadicType)resolve).getUnderlyingType(), ocResolveContext));
                    }
                }
                list.add(resolve.accept((OCTypeVisitor<Object>)new OCNonPrimitiveTypeCloneVisitor() {
                    @Override
                    public OCType visitExpansionPackType(final OCExpansionPackType ocExpansionPackType) {
                        if (n < ocExpansionPackType.getExpansionsCnt()) {
                            final OCTypeArgument ocTypeArgument = ocExpansionPackType.getExpansions().get(n);
                            if (ocTypeArgument instanceof OCType) {
                                return (OCType)ocTypeArgument;
                            }
                        }
                        return OCUnknownType.INSTANCE;
                    }
                }));
            }
            return (List<? extends OCTypeArgument>)list;
        }
        return null;
    }
    
    @Nullable
    private static List<? extends OCTypeArgument> a(@NotNull final OCVariadicPackExpressionSymbol ocVariadicPackExpressionSymbol, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocVariadicPackExpressionSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expressionSymbol", "com/jetbrains/cidr/lang/resolve/OCArgumentsList", "expandVariadicPackExpression"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/OCArgumentsList", "expandVariadicPackExpression"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final OCExpressionSymbol expression = ocVariadicPackExpressionSymbol.getExpression();
        return getExpandedResolvedTypes((com.intellij.util.Function<OCResolveContext, ? extends OCTypeArgument>)(ocResolveContext -> expression.evaluateToTypeArgument(ocResolveContext)), new OCTypeOwner() {
            @Nullable
            @Override
            public OCType getResolvedType(@NotNull final OCResolveContext ocResolveContext) {
                try {
                    if (ocResolveContext == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/OCArgumentsList$3", "getResolvedType"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                return expression.getResolvedType(ocResolveContext);
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        }, ocResolveContext);
    }
    
    @Nullable
    private static List<? extends OCTypeArgument> a(@NotNull final OCType ocType, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "underlyingType", "com/jetbrains/cidr/lang/resolve/OCArgumentsList", "expandVariadicType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/OCArgumentsList", "expandVariadicType"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return getExpandedResolvedTypes((com.intellij.util.Function<OCResolveContext, ? extends OCTypeArgument>)(ocResolveContext -> {
            try {
                if (ocType == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "underlyingType", "com/jetbrains/cidr/lang/resolve/OCArgumentsList", "lambda$expandVariadicType$2"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return ocResolveContext.getSubstitution().substitute(ocType, ocResolveContext);
        }), new OCTypeOwner() {
            @Nullable
            @Override
            public OCType getResolvedType(@NotNull final OCResolveContext ocResolveContext) {
                try {
                    if (ocResolveContext == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context2", "com/jetbrains/cidr/lang/resolve/OCArgumentsList$4", "getResolvedType"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                return ocType.resolve(ocResolveContext);
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        }, ocResolveContext);
    }
    
    @Nullable
    public static List<OCType> getExpandedResolvedTypes(@NotNull final OCTypeOwner ocTypeOwner, @NotNull final OCTypeOwner ocTypeOwner2, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocTypeOwner == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "operand", "com/jetbrains/cidr/lang/resolve/OCArgumentsList", "getExpandedResolvedTypes"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocTypeOwner2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "dependency", "com/jetbrains/cidr/lang/resolve/OCArgumentsList", "getExpandedResolvedTypes"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/OCArgumentsList", "getExpandedResolvedTypes"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return getExpandedResolvedTypes((com.intellij.util.Function<OCResolveContext, OCType>)(ocResolveContext -> {
            try {
                if (ocTypeOwner == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "operand", "com/jetbrains/cidr/lang/resolve/OCArgumentsList", "lambda$getExpandedResolvedTypes$3"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return ocTypeOwner.getResolvedType(ocResolveContext);
        }), ocTypeOwner2, ocResolveContext);
    }
    
    @Nullable
    public static <T extends OCTypeArgument> List<T> getExpandedResolvedTypes(@NotNull final Function<OCResolveContext, T> function, @NotNull final OCTypeOwner ocTypeOwner, @NotNull OCResolveContext originalContext) {
        try {
            if (function == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "operand", "com/jetbrains/cidr/lang/resolve/OCArgumentsList", "getExpandedResolvedTypes"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocTypeOwner == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "dependency", "com/jetbrains/cidr/lang/resolve/OCArgumentsList", "getExpandedResolvedTypes"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (originalContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/OCArgumentsList", "getExpandedResolvedTypes"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        while (originalContext.isVariadicExpansionMode()) {
            originalContext = originalContext.getOriginalContext();
        }
        final List<Pair<OCTypeParameterSymbol, OCExpansionPackType>> a = a(b(ocTypeOwner, originalContext), originalContext);
        Label_0182: {
            try {
                if (a == null) {
                    break Label_0182;
                }
                final List<Pair<OCTypeParameterSymbol, OCExpansionPackType>> list = a;
                final boolean b = list.isEmpty();
                if (b) {
                    break Label_0182;
                }
                break Label_0182;
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            try {
                final List<Pair<OCTypeParameterSymbol, OCExpansionPackType>> list = a;
                final boolean b = list.isEmpty();
                if (b) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        final ArrayList<OCTypeArgument> list2 = (ArrayList<OCTypeArgument>)new ArrayList<T>();
        for (int i = 0; i < ((OCExpansionPackType)a.get(0).second).getExpansionsCnt(); ++i) {
            final Map<OCTypeParameterSymbol, OCTypeArgument> typeParameterMap = OCTypeUtils.newTypeParameterMap();
            for (final Pair<OCTypeParameterSymbol, OCExpansionPackType> pair : a) {
                typeParameterMap.put((OCTypeParameterSymbol)pair.first, ((OCExpansionPackType)pair.second).getExpansions().get(i));
            }
            final OCResolveContext substitute = originalContext.substitute(OCSimpleTypeSubstitution.create(typeParameterMap), true, false);
            substitute.setVariadicExpansionMode(true);
            final OCTypeArgument ocTypeArgument = (OCTypeArgument)function.fun((Object)substitute);
            try {
                if (ocTypeArgument == null) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
            list2.add(ocTypeArgument);
        }
        return (List<T>)list2;
    }
    
    @Nullable
    private static List<Pair<OCTypeParameterSymbol, OCExpansionPackType>> a(@NotNull final Set<OCTypeParameterSymbol> set, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (set == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "dependencies", "com/jetbrains/cidr/lang/resolve/OCArgumentsList", "getExpansions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/OCArgumentsList", "getExpansions"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final HashSet set2 = new HashSet();
        final ArrayList<Pair<OCTypeParameterSymbol, OCExpansionPackType>> list = new ArrayList<Pair<OCTypeParameterSymbol, OCExpansionPackType>>();
        try {
            if (!ocResolveContext.getSubstitution().processSubstitutions((Processor<Map.Entry<OCTypeParameterSymbol, OCTypeArgument>>)(p3 -> {
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
                //    18: ldc             "dependencies"
                //    20: aastore        
                //    21: dup            
                //    22: ldc             1
                //    24: ldc             "com/jetbrains/cidr/lang/resolve/OCArgumentsList"
                //    26: aastore        
                //    27: dup            
                //    28: ldc             2
                //    30: ldc             "lambda$getExpansions$4"
                //    32: aastore        
                //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
                //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
                //    39: athrow         
                //    40: invokestatic    com/jetbrains/cidr/lang/resolve/OCArgumentsList.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    43: athrow         
                //    44: aload_3        
                //    45: invokeinterface java/util/Map$Entry.getKey:()Ljava/lang/Object;
                //    50: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;
                //    53: astore          4
                //    55: aload_3        
                //    56: invokeinterface java/util/Map$Entry.getValue:()Ljava/lang/Object;
                //    61: checkcast       Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
                //    64: astore          5
                //    66: aload_0        
                //    67: aload           4
                //    69: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
                //    74: ifeq            195
                //    77: aload_1        
                //    78: aload           4
                //    80: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
                //    85: ifne            195
                //    88: goto            95
                //    91: invokestatic    com/jetbrains/cidr/lang/resolve/OCArgumentsList.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    94: athrow         
                //    95: aload           5
                //    97: instanceof      Lcom/jetbrains/cidr/lang/types/OCExpansionPackType;
                //   100: ifeq            195
                //   103: goto            110
                //   106: invokestatic    com/jetbrains/cidr/lang/resolve/OCArgumentsList.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   109: athrow         
                //   110: aload_1        
                //   111: aload           4
                //   113: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
                //   118: pop            
                //   119: aload_2        
                //   120: invokeinterface java/util/List.isEmpty:()Z
                //   125: ifne            178
                //   128: goto            135
                //   131: invokestatic    com/jetbrains/cidr/lang/resolve/OCArgumentsList.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   134: athrow         
                //   135: aload_2        
                //   136: iconst_0       
                //   137: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
                //   142: checkcast       Lcom/intellij/openapi/util/Pair;
                //   145: getfield        com/intellij/openapi/util/Pair.second:Ljava/lang/Object;
                //   148: checkcast       Lcom/jetbrains/cidr/lang/types/OCExpansionPackType;
                //   151: invokevirtual   com/jetbrains/cidr/lang/types/OCExpansionPackType.getExpansionsCnt:()I
                //   154: aload           5
                //   156: checkcast       Lcom/jetbrains/cidr/lang/types/OCExpansionPackType;
                //   159: invokevirtual   com/jetbrains/cidr/lang/types/OCExpansionPackType.getExpansionsCnt:()I
                //   162: if_icmpeq       178
                //   165: goto            172
                //   168: invokestatic    com/jetbrains/cidr/lang/resolve/OCArgumentsList.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   171: athrow         
                //   172: iconst_0       
                //   173: ireturn        
                //   174: invokestatic    com/jetbrains/cidr/lang/resolve/OCArgumentsList.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   177: athrow         
                //   178: aload_2        
                //   179: aload           4
                //   181: aload           5
                //   183: checkcast       Lcom/jetbrains/cidr/lang/types/OCExpansionPackType;
                //   186: invokestatic    com/intellij/openapi/util/Pair.create:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/intellij/openapi/util/Pair;
                //   189: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
                //   194: pop            
                //   195: iconst_1       
                //   196: ireturn        
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                                
                //  -----  -----  -----  -----  ------------------------------------
                //  0      40     40     44     Ljava/lang/IllegalArgumentException;
                //  66     88     91     95     Ljava/lang/IllegalArgumentException;
                //  77     103    106    110    Ljava/lang/IllegalArgumentException;
                //  95     128    131    135    Ljava/lang/IllegalArgumentException;
                //  110    165    168    172    Ljava/lang/IllegalArgumentException;
                //  135    174    174    178    Ljava/lang/IllegalArgumentException;
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0095:
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
            }))) {
                return null;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return list;
    }
    
    @NotNull
    private static Set<OCTypeParameterSymbol> b(@NotNull final OCTypeOwner ocTypeOwner, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocTypeOwner == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "dependency", "com/jetbrains/cidr/lang/resolve/OCArgumentsList", "getTypeParameterDependencies"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/OCArgumentsList", "getTypeParameterDependencies"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final OCResolveContext clearSubstitution = ocResolveContext.clearSubstitution();
        clearSubstitution.setDontExpandVariadics(true);
        final OCType resolvedType = ocTypeOwner.getResolvedType(clearSubstitution);
        Set<OCTypeParameterSymbol> typeDependencies;
        try {
            typeDependencies = clearSubstitution.getTypeDependencies(resolvedType);
            if (typeDependencies == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCArgumentsList", "getTypeParameterDependencies"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return typeDependencies;
    }
    
    @NotNull
    public List<OCType> getTypes() {
        List<OCType> types;
        try {
            types = this.types;
            if (types == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCArgumentsList", "getTypes"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return types;
    }
    
    @Nullable
    public List<E> getExprs() {
        return this.exprs;
    }
    
    public int getCount() {
        return this.types.size();
    }
    
    public boolean hasNonExpandedVariadics() {
        return this.nonExpandedVariadics;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
