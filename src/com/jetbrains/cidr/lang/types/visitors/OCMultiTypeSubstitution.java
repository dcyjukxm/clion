// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.containers.ContainerUtil;
import java.util.Collections;
import gnu.trove.THashSet;
import java.util.ArrayList;
import gnu.trove.THashMap;
import com.intellij.util.containers.HashMap;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import com.jetbrains.cidr.lang.symbols.OCTypeParameterSymbol;
import java.util.Map;
import com.intellij.util.Processor;
import java.util.Set;
import com.jetbrains.cidr.lang.types.OCTypeUtils;
import java.util.Collection;
import com.jetbrains.cidr.lang.types.OCTypeArgument;
import java.util.Iterator;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.jetbrains.cidr.lang.types.OCMagicType;
import com.jetbrains.cidr.lang.types.OCTypeParameterType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.OCType;
import java.util.List;

public class OCMultiTypeSubstitution extends OCTypeSubstitution
{
    private List<OCSimpleTypeSubstitution> mySubstitutions;
    
    public OCMultiTypeSubstitution(final List<OCSimpleTypeSubstitution> mySubstitutions) {
        this.mySubstitutions = mySubstitutions;
    }
    
    @Override
    public OCType substitute(@NotNull final OCType ocType, final boolean b, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/types/visitors/OCMultiTypeSubstitution", "substitute"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/visitors/OCMultiTypeSubstitution", "substitute"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return ocType.accept((OCTypeVisitor<OCType>)new TypeSubstituteVisitor(this, b, ocResolveContext) {
            @Override
            public OCType visitTypeParameterType(final OCTypeParameterType ocTypeParameterType) {
                OCType substitute = null;
                for (final OCSimpleTypeSubstitution ocSimpleTypeSubstitution : OCMultiTypeSubstitution.this.mySubstitutions) {
                    if (substitute != null) {
                        substitute = ocSimpleTypeSubstitution.substitute(substitute, ocResolveContext);
                    }
                    else {
                        final OCTypeArgument substitution = ocSimpleTypeSubstitution.getSubstitutionFor(ocTypeParameterType.getSymbol());
                        if (substitution == null) {
                            continue;
                        }
                        if (!(substitution instanceof OCType)) {
                            return new OCMagicType(substitution.getNameForPresentation(OCType.Presentation.FULL, ocResolveContext, true, 0));
                        }
                        if (substitution instanceof OCReferenceType) {
                            return OCTypeSubstitution.substituteReferenceType((OCReferenceType)substitution, OCMultiTypeSubstitution.this, b, ocResolveContext).cloneWithAddedCVQualifiers(ocTypeParameterType.getCVQualifiers(), ocResolveContext.getProject());
                        }
                        substitute = (OCReferenceType)substitution;
                    }
                }
                return (substitute != null) ? substitute.cloneWithAddedCVQualifiers(ocTypeParameterType.getCVQualifiers(), ocResolveContext.getProject()) : ocTypeParameterType;
            }
        });
    }
    
    @Override
    public Collection<OCTypeArgument> getSubstitutedTypes() {
        final Set<OCTypeArgument> typeSet = OCTypeUtils.newTypeSet();
        final Iterator<OCSimpleTypeSubstitution> iterator = this.mySubstitutions.iterator();
        while (iterator.hasNext()) {
            typeSet.addAll(iterator.next().getSubstitutedTypes());
        }
        return typeSet;
    }
    
    @Override
    public boolean processSubstitutions(final Processor<Map.Entry<OCTypeParameterSymbol, OCTypeArgument>> processor) {
        for (final OCSimpleTypeSubstitution ocSimpleTypeSubstitution : this.mySubstitutions) {
            try {
                if (!ocSimpleTypeSubstitution.processSubstitutions(processor)) {
                    return false;
                }
                continue;
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
        }
        return true;
    }
    
    @Override
    public OCTypeArgument getSubstitutionFor(@NotNull final OCTypeParameterSymbol ocTypeParameterSymbol) {
        try {
            if (ocTypeParameterSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "argument", "com/jetbrains/cidr/lang/types/visitors/OCMultiTypeSubstitution", "getSubstitutionFor"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final Iterator<OCSimpleTypeSubstitution> iterator = this.mySubstitutions.iterator();
        while (iterator.hasNext()) {
            final OCTypeArgument substitution = iterator.next().getSubstitutionFor(ocTypeParameterSymbol);
            try {
                if (substitution != null) {
                    return substitution;
                }
                continue;
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        return null;
    }
    
    @Override
    public boolean hasSubstitutionForName(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/types/visitors/OCMultiTypeSubstitution", "hasSubstitutionForName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        for (final OCSimpleTypeSubstitution ocSimpleTypeSubstitution : this.mySubstitutions) {
            try {
                if (ocSimpleTypeSubstitution.hasSubstitutionForName(s)) {
                    return true;
                }
                continue;
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        return false;
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/types/visitors/OCMultiTypeSubstitution", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/types/visitors/OCMultiTypeSubstitution", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/types/visitors/OCMultiTypeSubstitution", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        return comparator.equalIterable(((OCMultiTypeSubstitution)o).mySubstitutions, ((OCMultiTypeSubstitution)o2).mySubstitutions);
    }
    
    @Override
    public OCTypeSubstitution getMinimalDependentSubstitution(final Object o, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/visitors/OCMultiTypeSubstitution", "getMinimalDependentSubstitution"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final HashMap hashMap = new HashMap();
        final THashMap tHashMap = new THashMap();
        ArrayList<OCTypeArgument> list = new ArrayList<OCTypeArgument>();
        list.add((OCTypeArgument)o);
        final Iterator<OCSimpleTypeSubstitution> iterator = this.mySubstitutions.iterator();
        while (iterator.hasNext()) {
            ((Map<OCTypeParameterSymbol<?>, OCTypeArgument>)tHashMap).putAll((Map<? extends OCTypeParameterSymbol<?>, ? extends OCTypeArgument>)iterator.next().getSubstitutions());
        }
        while (!list.isEmpty()) {
            final ArrayList<OCTypeArgument> list2 = new ArrayList<OCTypeArgument>();
            final THashSet set = new THashSet();
            for (final OCTypeParameterSymbol<?> ocTypeParameterSymbol : ((Map<OCTypeParameterSymbol<?>, OCTypeArgument>)tHashMap).keySet()) {
                final OCTypeArgument ocTypeArgument = ((Map<OCTypeParameterSymbol<?>, OCTypeArgument>)tHashMap).get(ocTypeParameterSymbol);
                try {
                    if (!new OCSimpleTypeSubstitution((Map<OCTypeParameterSymbol, OCTypeArgument>)Collections.singletonMap(ocTypeParameterSymbol, ocTypeArgument)).dependsOn((Collection<Object>)list, ocResolveContext)) {
                        continue;
                    }
                    ((Map<OCTypeParameterSymbol<?>, OCTypeArgument>)hashMap).put(ocTypeParameterSymbol, ocTypeArgument);
                    list2.add(ocTypeArgument);
                    ((Set<OCTypeParameterSymbol<?>>)set).add(ocTypeParameterSymbol);
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
            }
            final Iterator<OCTypeParameterSymbol<?>> iterator3 = ((Set<OCTypeParameterSymbol<?>>)set).iterator();
            while (iterator3.hasNext()) {
                ((Map<OCTypeParameterSymbol<?>, OCTypeArgument>)tHashMap).remove(iterator3.next());
            }
            list = list2;
        }
        try {
            if (((Map)hashMap).isEmpty()) {
                return OCMultiTypeSubstitution.ID;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        return new OCSimpleTypeSubstitution((Map<OCTypeParameterSymbol, OCTypeArgument>)hashMap);
    }
    
    @Override
    public String toString() {
        return "{" + StringUtil.join((Collection)ContainerUtil.map((Collection)this.mySubstitutions, ocSimpleTypeSubstitution -> ocSimpleTypeSubstitution.substList()), " // ") + "}";
    }
    
    @Override
    public int hashCode() {
        try {
            if (this.mySubstitutions != null) {
                return this.mySubstitutions.hashCode();
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return 0;
    }
    
    public List<OCSimpleTypeSubstitution> getSubstitutions() {
        return this.mySubstitutions;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
