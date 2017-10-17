// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

import com.jetbrains.cidr.lang.symbols.expression.OCExpressionSymbol;
import com.jetbrains.cidr.lang.types.OCAutoType;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceAliasSymbol;
import com.jetbrains.cidr.lang.symbols.symtable.OCFileSymbols;
import com.jetbrains.cidr.lang.symbols.cpp.OCUsingSymbol;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.types.OCMagicType;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.lang.symbols.cpp.OCTypeParameterTypeSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceLikeSymbol;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceSymbol;
import gnu.trove.TObjectHashingStrategy;
import gnu.trove.THashSet;
import com.jetbrains.cidr.lang.types.visitors.OCTypeSubstitution;
import com.jetbrains.cidr.lang.resolve.OCResolveUtil;
import com.jetbrains.cidr.lang.symbols.cpp.OCThisSelfSuperSymbol;
import com.intellij.openapi.util.Comparing;
import com.intellij.psi.util.CachedValueProvider;
import com.jetbrains.cidr.lang.util.OCCommonProcessors;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import java.util.Set;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.types.OCTypeUtils;
import com.intellij.openapi.progress.ProgressManager;
import java.util.Iterator;
import com.jetbrains.cidr.lang.types.visitors.OCSimpleTypeSubstitution;
import com.jetbrains.cidr.lang.symbols.cpp.OCTemplateSymbol;
import java.util.ArrayList;
import com.intellij.openapi.util.Condition;
import com.jetbrains.cidr.lang.symbols.objc.OCInterfaceSymbol;
import java.util.Collection;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import java.util.Collections;
import com.intellij.openapi.util.Computable;
import com.jetbrains.cidr.lang.ui.OCLongActionUtil;
import com.intellij.psi.util.PsiModificationTracker;
import com.intellij.psi.util.CachedValuesManager;
import com.jetbrains.cidr.lang.symbols.symtable.FileSymbolTablesCache;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.symbols.expression.OCLambdaExpressionSymbol;
import com.jetbrains.cidr.lang.psi.OCNamespaceQualifierOwner;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.types.OCTypeArgument;
import com.intellij.psi.PsiFile;
import java.util.List;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.Processor;
import java.util.Map;
import com.intellij.psi.util.CachedValue;
import com.intellij.openapi.util.Key;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.parser.OCElementType;
import java.io.Serializable;

public abstract class OCSymbolReference implements Serializable, DeepEqual.Equality<OCSymbolReference>
{
    public static final int MAX_RESOLVE_NESTING_DEPTH = 256;
    public static final int MAX_DEPENDENT_RESOLVE_NESTING_DEPTH = 6;
    public static final OCElementType[] TYPE_TOKENS;
    @NotNull
    protected OCQualifiedName myQualifiedName;
    @NotNull
    protected SymbolFilter myFilter;
    private static final Key<CachedValue<Map<ReferenceInfo, ResultInfo>>>[] RESOLVE_CACHES;
    
    @NotNull
    public SymbolFilter getFilter() {
        SymbolFilter myFilter;
        try {
            myFilter = this.myFilter;
            if (myFilter == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolReference", "getFilter"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return myFilter;
    }
    
    public OCSymbolReference(@NotNull final OCQualifiedName myQualifiedName, @NotNull final SymbolFilter myFilter) {
        if (myQualifiedName == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "qualifiedName", "com/jetbrains/cidr/lang/symbols/OCSymbolReference", "<init>"));
        }
        if (myFilter == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "filter", "com/jetbrains/cidr/lang/symbols/OCSymbolReference", "<init>"));
        }
        this.myQualifiedName = myQualifiedName;
        this.myFilter = myFilter;
    }
    
    public OCSymbolReference() {
    }
    
    @NotNull
    public OCQualifiedName getQualifiedName() {
        OCQualifiedName myQualifiedName;
        try {
            myQualifiedName = this.myQualifiedName;
            if (myQualifiedName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolReference", "getQualifiedName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return myQualifiedName;
    }
    
    public boolean processPossibleSymbols(final Processor<OCSymbol> processor, final boolean b, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/OCSymbolReference", "processPossibleSymbols"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        List<OCSymbol> list = this.resolveToSymbols(false, false, true, ocResolveContext);
        try {
            if (!list.isEmpty() || b) {
                return ContainerUtil.process((List)list, (Processor)processor);
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        list = this.resolveToSymbols(false, false, false, ocResolveContext);
        return ContainerUtil.process((List)list, (Processor)processor);
    }
    
    public boolean processPossibleSymbols(final Processor<OCSymbol> processor, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/OCSymbolReference", "processPossibleSymbols"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return this.processPossibleSymbols(processor, true, ocResolveContext);
    }
    
    public boolean processPossibleSymbols(final Processor<OCSymbol> processor, @NotNull final PsiFile psiFile) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/symbols/OCSymbolReference", "processPossibleSymbols"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return ContainerUtil.process((List)this.resolveToSymbols(psiFile), (Processor)processor);
    }
    
    @NotNull
    public List<OCSymbol> resolveToSymbols(@NotNull final PsiFile psiFile) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/symbols/OCSymbolReference", "resolveToSymbols"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        List<OCSymbol> resolveToSymbols;
        try {
            resolveToSymbols = this.resolveToSymbols(this.a(psiFile));
            if (resolveToSymbols == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolReference", "resolveToSymbols"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return resolveToSymbols;
    }
    
    @NotNull
    public List<OCSymbol> resolveToSymbols(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/OCSymbolReference", "resolveToSymbols"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        List<OCSymbol> resolveToSymbols;
        try {
            resolveToSymbols = this.resolveToSymbols(ocResolveContext, false, false, true);
            if (resolveToSymbols == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolReference", "resolveToSymbols"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return resolveToSymbols;
    }
    
    @NotNull
    public List<OCSymbol> resolveToSymbols(final boolean b, final boolean b2, final PsiFile psiFile) {
        List<OCSymbol> resolveToSymbols;
        try {
            resolveToSymbols = this.resolveToSymbols(b, b2, true, this.a(psiFile));
            if (resolveToSymbols == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolReference", "resolveToSymbols"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return resolveToSymbols;
    }
    
    @NotNull
    public List<OCSymbol> resolveToSymbols(final boolean b, final boolean b2, final boolean b3, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/OCSymbolReference", "resolveToSymbols"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final List<OCSymbol> resolveToSymbols = this.resolveToSymbols(ocResolveContext, false, b2, b3);
        List<OCSymbol> lookupUsingsAndTypedefs;
        try {
            lookupUsingsAndTypedefs = lookupUsingsAndTypedefs(b, b2, ocResolveContext, resolveToSymbols, this, true);
            if (lookupUsingsAndTypedefs == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolReference", "resolveToSymbols"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return lookupUsingsAndTypedefs;
    }
    
    @NotNull
    public static List<OCSymbol> lookupUsingsAndTypedefs(final boolean b, final boolean b2, @NotNull final OCResolveContext ocResolveContext, @NotNull final List<OCSymbol> list, @NotNull final OCSymbolReference ocSymbolReference, final boolean b3) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/OCSymbolReference", "lookupUsingsAndTypedefs"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbols", "com/jetbrains/cidr/lang/symbols/OCSymbolReference", "lookupUsingsAndTypedefs"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        try {
            if (ocSymbolReference == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "reference", "com/jetbrains/cidr/lang/symbols/OCSymbolReference", "lookupUsingsAndTypedefs"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        final OCQualifiedName qualifiedName = ocSymbolReference.getQualifiedName();
        List<OCTypeArgument> arguments = null;
        if (qualifiedName instanceof OCQualifiedNameWithArguments) {
            arguments = ((OCQualifiedNameWithArguments)qualifiedName).getArguments();
        }
        final UsingAndTypedefSymbolsResolver usingAndTypedefSymbolsResolver = new UsingAndTypedefSymbolsResolver(b, b3, b2, arguments, ocResolveContext);
        List<OCSymbol> answer;
        try {
            ContainerUtil.process((List)list, (Processor)usingAndTypedefSymbolsResolver);
            answer = usingAndTypedefSymbolsResolver.getAnswer();
            if (answer == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolReference", "lookupUsingsAndTypedefs"));
            }
        }
        catch (IllegalStateException ex4) {
            throw a(ex4);
        }
        return answer;
    }
    
    private OCResolveContext a(final PsiFile psiFile) {
        PsiElement localContext = null;
        Label_0022: {
            try {
                if (this instanceof LocalReference) {
                    localContext = ((LocalReference)this).getLocalContext();
                    break Label_0022;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            localContext = null;
        }
        final PsiElement psiElement = localContext;
        try {
            if (psiElement != null) {
                final Object o = psiElement;
                return new OCResolveContext((PsiElement)o);
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        final Object o = psiFile;
        return new OCResolveContext((PsiElement)o);
    }
    
    public static GlobalReference getGlobalReference(@NotNull final OCQualifiedName ocQualifiedName) {
        try {
            if (ocQualifiedName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "qualifiedName", "com/jetbrains/cidr/lang/symbols/OCSymbolReference", "getGlobalReference"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return new GlobalReference(ocQualifiedName, null, SymbolFilter.NONE);
    }
    
    public static GlobalReference getGlobalReference(@NotNull final OCQualifiedName ocQualifiedName, final SymbolFilter symbolFilter) {
        try {
            if (ocQualifiedName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "qualifiedName", "com/jetbrains/cidr/lang/symbols/OCSymbolReference", "getGlobalReference"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return new GlobalReference(ocQualifiedName, null, symbolFilter);
    }
    
    public static GlobalReference getGlobalReference(final String s, final OCSymbolWithQualifiedName ocSymbolWithQualifiedName) {
        return new GlobalReference(OCQualifiedName.with(s), ocSymbolWithQualifiedName, SymbolFilter.NONE);
    }
    
    public static GlobalReference getGlobalReference(@NotNull final OCQualifiedName ocQualifiedName, final OCSymbolWithQualifiedName ocSymbolWithQualifiedName) {
        try {
            if (ocQualifiedName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "qualifiedName", "com/jetbrains/cidr/lang/symbols/OCSymbolReference", "getGlobalReference"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return new GlobalReference(ocQualifiedName, ocSymbolWithQualifiedName, SymbolFilter.NONE);
    }
    
    public static BaseClauseReference getBaseClauseReference(@NotNull final OCQualifiedName ocQualifiedName, @Nullable final OCSymbolWithQualifiedName ocSymbolWithQualifiedName) {
        try {
            if (ocQualifiedName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "qualifiedName", "com/jetbrains/cidr/lang/symbols/OCSymbolReference", "getBaseClauseReference"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return new BaseClauseReference(ocQualifiedName, ocSymbolWithQualifiedName, SymbolFilter.NONE);
    }
    
    public static TemplateParamsReference getTemplateParamsReference(@NotNull final OCQualifiedName ocQualifiedName, @Nullable final OCSymbolWithQualifiedName ocSymbolWithQualifiedName) {
        try {
            if (ocQualifiedName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "qualifiedName", "com/jetbrains/cidr/lang/symbols/OCSymbolReference", "getTemplateParamsReference"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return new TemplateParamsReference(ocQualifiedName, ocSymbolWithQualifiedName, SymbolFilter.NONE);
    }
    
    public static GlobalReference getGlobalReference(@NotNull final OCQualifiedName ocQualifiedName, final OCSymbolWithQualifiedName ocSymbolWithQualifiedName, final SymbolFilter symbolFilter) {
        try {
            if (ocQualifiedName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "qualifiedName", "com/jetbrains/cidr/lang/symbols/OCSymbolReference", "getGlobalReference"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return new GlobalReference(ocQualifiedName, ocSymbolWithQualifiedName, symbolFilter);
    }
    
    public static LocalReference getLocalReference(@NotNull final OCQualifiedName ocQualifiedName, final PsiElement psiElement) {
        try {
            if (ocQualifiedName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "qualifiedName", "com/jetbrains/cidr/lang/symbols/OCSymbolReference", "getLocalReference"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return new LocalReference(ocQualifiedName, psiElement, (SymbolFilter)SymbolFilter.NONE);
    }
    
    public static LocalReference getLocalReference(@NotNull final OCQualifiedName ocQualifiedName, final PsiElement psiElement, final SymbolFilter symbolFilter) {
        try {
            if (ocQualifiedName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "qualifiedName", "com/jetbrains/cidr/lang/symbols/OCSymbolReference", "getLocalReference"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return new LocalReference(ocQualifiedName, psiElement, symbolFilter);
    }
    
    public static LocalReference getLocalReference(final String s, final PsiElement psiElement) {
        return getLocalReference(OCQualifiedName.with(s), psiElement, SymbolFilter.NONE);
    }
    
    public static LocalReference getLocalReference(@NotNull final OCNamespaceQualifierOwner ocNamespaceQualifierOwner, final SymbolFilter symbolFilter) {
        try {
            if (ocNamespaceQualifierOwner == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/symbols/OCSymbolReference", "getLocalReference"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return getLocalReference(OCSymbolReferenceResolver.getQualifiedName(ocNamespaceQualifierOwner), (PsiElement)ocNamespaceQualifierOwner, symbolFilter);
    }
    
    public static LambdaLocalReference getLambdaLocalReference(@NotNull final OCQualifiedName ocQualifiedName, final long n, @NotNull final OCLambdaExpressionSymbol ocLambdaExpressionSymbol, @NotNull final OCSymbolReference ocSymbolReference) {
        try {
            if (ocQualifiedName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "qualifiedName", "com/jetbrains/cidr/lang/symbols/OCSymbolReference", "getLambdaLocalReference"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (ocLambdaExpressionSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "lambda", "com/jetbrains/cidr/lang/symbols/OCSymbolReference", "getLambdaLocalReference"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        try {
            if (ocSymbolReference == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parentScopeReference", "com/jetbrains/cidr/lang/symbols/OCSymbolReference", "getLambdaLocalReference"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        return new LambdaLocalReference(ocQualifiedName, n, ocLambdaExpressionSymbol, SymbolFilter.NONE, ocSymbolReference);
    }
    
    public abstract OCSymbolReference getSymbolReferenceToQualifier();
    
    @NotNull
    protected SymbolKindFilter getFilterForQualifier() {
        SymbolKindFilter symbolKindFilter = null;
        Label_0023: {
            try {
                if (this.myFilter == SymbolKindFilter.ONLY_NAMESPACE) {
                    final SymbolKindFilter symbolKindFilter2;
                    symbolKindFilter = (symbolKindFilter2 = SymbolKindFilter.ONLY_NAMESPACE);
                    break Label_0023;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            SymbolKindFilter symbolKindFilter2;
            symbolKindFilter = (symbolKindFilter2 = SymbolKindFilter.ONLY_NAMESPACE_LIKE);
            try {
                if (symbolKindFilter2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolReference", "getFilterForQualifier"));
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        return symbolKindFilter;
    }
    
    @NotNull
    public OCSymbolReference applyTypeArguments(final List<OCTypeArgument> list) {
        OCSymbolReference referenceInSameContext;
        try {
            referenceInSameContext = this.createReferenceInSameContext(new OCQualifiedNameWithArguments(this.getQualifiedName(), list));
            if (referenceInSameContext == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolReference", "applyTypeArguments"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return referenceInSameContext;
    }
    
    @NotNull
    public abstract OCSymbolReference createReferenceInSameContext(@NotNull final OCQualifiedName p0);
    
    @Override
    public boolean equals(final Object o) {
        try {
            if (this == o) {
                return true;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        Label_0039: {
            try {
                if (o == null) {
                    return false;
                }
                final OCSymbolReference ocSymbolReference = this;
                final Class<? extends OCSymbolReference> clazz = ocSymbolReference.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
                break Label_0039;
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            try {
                final OCSymbolReference ocSymbolReference = this;
                final Class<? extends OCSymbolReference> clazz = ocSymbolReference.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
        }
        final OCSymbolReference ocSymbolReference2 = (OCSymbolReference)o;
        try {
            if (!this.myQualifiedName.equals(ocSymbolReference2.myQualifiedName)) {
                return false;
            }
        }
        catch (IllegalStateException ex4) {
            throw a(ex4);
        }
        try {
            if (!this.myFilter.equals(ocSymbolReference2.myFilter)) {
                return false;
            }
        }
        catch (IllegalStateException ex5) {
            throw a(ex5);
        }
        return true;
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final OCSymbolReference ocSymbolReference, @NotNull final OCSymbolReference ocSymbolReference2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/symbols/OCSymbolReference", "deepEqualStep"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (ocSymbolReference == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/symbols/OCSymbolReference", "deepEqualStep"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        try {
            if (ocSymbolReference2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/symbols/OCSymbolReference", "deepEqualStep"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        try {
            if (!comparator.equalObjects((DeepEqual.Equality<Object>)ocSymbolReference.myQualifiedName, (DeepEqual.Equality<Object>)ocSymbolReference2.myQualifiedName)) {
                return false;
            }
        }
        catch (IllegalStateException ex4) {
            throw a(ex4);
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return this.myQualifiedName.hashCode();
    }
    
    @NotNull
    List<OCSymbol> resolveToSymbols(@NotNull final OCResolveContext p0, final boolean p1, final boolean p2, final boolean p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
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
        //    24: ldc             "com/jetbrains/cidr/lang/symbols/OCSymbolReference"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "resolveToSymbols"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReference.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    43: athrow         
        //    44: aload_1        
        //    45: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getFile:()Lcom/intellij/psi/PsiFile;
        //    48: astore          5
        //    50: aload           5
        //    52: ifnonnull       104
        //    55: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //    58: dup            
        //    59: ifnonnull       103
        //    62: goto            69
        //    65: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReference.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    68: athrow         
        //    69: new             Ljava/lang/IllegalStateException;
        //    72: dup            
        //    73: ldc             "@NotNull method %s.%s must not return null"
        //    75: ldc             2
        //    77: anewarray       Ljava/lang/Object;
        //    80: dup            
        //    81: ldc             0
        //    83: ldc             "com/jetbrains/cidr/lang/symbols/OCSymbolReference"
        //    85: aastore        
        //    86: dup            
        //    87: ldc             1
        //    89: ldc             "resolveToSymbols"
        //    91: aastore        
        //    92: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    95: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    98: athrow         
        //    99: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReference.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   102: athrow         
        //   103: areturn        
        //   104: aload_1        
        //   105: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.isProcessNonImported:()Z
        //   108: istore          6
        //   110: aload_0        
        //   111: aload           5
        //   113: invokeinterface com/intellij/psi/PsiFile.getProject:()Lcom/intellij/openapi/project/Project;
        //   118: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolReference.getCacheDependency:(Lcom/intellij/openapi/project/Project;)Ljava/lang/Object;
        //   121: astore          7
        //   123: aload           5
        //   125: iload_2        
        //   126: iload_3        
        //   127: iload           6
        //   129: aload           7
        //   131: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReference.a:(Lcom/intellij/psi/PsiFile;ZZZLjava/lang/Object;)Lcom/intellij/psi/util/CachedValue;
        //   134: invokeinterface com/intellij/psi/util/CachedValue.getValue:()Ljava/lang/Object;
        //   139: checkcast       Ljava/util/Map;
        //   142: astore          8
        //   144: aload_1        
        //   145: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getSubstitution:()Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;
        //   148: astore          9
        //   150: aload           9
        //   152: aload_0        
        //   153: aload_1        
        //   154: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.getMinimalDependentSubstitution:(Ljava/lang/Object;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;
        //   157: astore          9
        //   159: invokestatic    com/intellij/openapi/progress/ProgressManager.checkCanceled:()V
        //   162: aload           8
        //   164: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference$ReferenceInfo;
        //   167: dup            
        //   168: aload_0        
        //   169: aload           9
        //   171: aload_1        
        //   172: iload           4
        //   174: aconst_null    
        //   175: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolReference$ReferenceInfo.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;ZLcom/jetbrains/cidr/lang/symbols/OCSymbolReference$1;)V
        //   178: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   183: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference$ResultInfo;
        //   186: astore          10
        //   188: aload           10
        //   190: ifnonnull       334
        //   193: aload_1        
        //   194: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getTypeDependencies:()Ljava/util/Set;
        //   197: astore          11
        //   199: aload_1        
        //   200: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.clearTypeDependencies:()V
        //   203: aload_0        
        //   204: aload_1        
        //   205: iload_2        
        //   206: iload_3        
        //   207: iload           4
        //   209: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolReference.doResolve:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;ZZZ)Ljava/util/List;
        //   212: astore          12
        //   214: aload_1        
        //   215: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getTypeDependencies:()Ljava/util/Set;
        //   218: astore          13
        //   220: aload           13
        //   222: invokeinterface java/util/Set.isEmpty:()Z
        //   227: ifne            239
        //   230: aload           13
        //   232: goto            240
        //   235: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReference.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   238: athrow         
        //   239: aconst_null    
        //   240: astore          13
        //   242: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference$ResultInfo;
        //   245: dup            
        //   246: aload           12
        //   248: aload           13
        //   250: aconst_null    
        //   251: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolReference$ResultInfo.<init>:(Ljava/util/List;Ljava/util/Set;Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference$1;)V
        //   254: astore          10
        //   256: aload_1        
        //   257: aload           11
        //   259: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.addTypeDependencies:(Ljava/util/Set;)V
        //   262: aload_1        
        //   263: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.wasCollision:()Z
        //   266: ifeq            300
        //   269: aload_1        
        //   270: getfield        com/jetbrains/cidr/lang/symbols/OCResolveContext.myInsideUsingNamespaceCounter:I
        //   273: ifne            331
        //   276: goto            283
        //   279: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReference.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   282: athrow         
        //   283: aload           12
        //   285: invokeinterface java/util/List.isEmpty:()Z
        //   290: ifne            331
        //   293: goto            300
        //   296: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReference.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   299: athrow         
        //   300: aload           8
        //   302: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference$ReferenceInfo;
        //   305: dup            
        //   306: aload_0        
        //   307: aload           9
        //   309: aconst_null    
        //   310: iload           4
        //   312: aconst_null    
        //   313: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolReference$ReferenceInfo.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;ZLcom/jetbrains/cidr/lang/symbols/OCSymbolReference$1;)V
        //   316: aload           10
        //   318: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   323: pop            
        //   324: goto            331
        //   327: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReference.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   330: athrow         
        //   331: goto            358
        //   334: aload           10
        //   336: getfield        com/jetbrains/cidr/lang/symbols/OCSymbolReference$ResultInfo.typeDependencies:Ljava/util/Set;
        //   339: ifnull          358
        //   342: aload_1        
        //   343: aload           10
        //   345: getfield        com/jetbrains/cidr/lang/symbols/OCSymbolReference$ResultInfo.typeDependencies:Ljava/util/Set;
        //   348: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.addTypeDependencies:(Ljava/util/Set;)V
        //   351: goto            358
        //   354: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReference.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   357: athrow         
        //   358: aload           10
        //   360: getfield        com/jetbrains/cidr/lang/symbols/OCSymbolReference$ResultInfo.symbols:Ljava/util/List;
        //   363: invokestatic    java/util/Collections.unmodifiableList:(Ljava/util/List;)Ljava/util/List;
        //   366: dup            
        //   367: ifnonnull       404
        //   370: new             Ljava/lang/IllegalStateException;
        //   373: dup            
        //   374: ldc             "@NotNull method %s.%s must not return null"
        //   376: ldc             2
        //   378: anewarray       Ljava/lang/Object;
        //   381: dup            
        //   382: ldc             0
        //   384: ldc             "com/jetbrains/cidr/lang/symbols/OCSymbolReference"
        //   386: aastore        
        //   387: dup            
        //   388: ldc             1
        //   390: ldc             "resolveToSymbols"
        //   392: aastore        
        //   393: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   396: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   399: athrow         
        //   400: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReference.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   403: athrow         
        //   404: areturn        
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;ZZZ)Ljava/util/List<Lcom/jetbrains/cidr/lang/symbols/OCSymbol;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      40     40     44     Ljava/lang/IllegalStateException;
        //  50     62     65     69     Ljava/lang/IllegalStateException;
        //  55     99     99     103    Ljava/lang/IllegalStateException;
        //  220    235    235    239    Ljava/lang/IllegalStateException;
        //  256    276    279    283    Ljava/lang/IllegalStateException;
        //  269    293    296    300    Ljava/lang/IllegalStateException;
        //  283    324    327    331    Ljava/lang/IllegalStateException;
        //  334    351    354    358    Ljava/lang/IllegalStateException;
        //  358    400    400    404    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0283:
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
    
    protected Object getCacheDependency(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/symbols/OCSymbolReference", "getCacheDependency"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return FileSymbolTablesCache.getInstance(project).getOutOfBlockModificationTracker();
    }
    
    @NotNull
    private static CachedValue<Map<ReferenceInfo, ResultInfo>> a(@NotNull final PsiFile psiFile, final boolean b, final boolean b2, final boolean b3, @NotNull final Object o) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/symbols/OCSymbolReference", "getCache"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "dependency", "com/jetbrains/cidr/lang/symbols/OCSymbolReference", "getCache"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        final Key<CachedValue<Map<ReferenceInfo, ResultInfo>>> a = a(b, b2, b3, o);
        CachedValue a2 = (CachedValue)psiFile.getUserData((Key)a);
        if (a2 == null) {
            a2 = a(psiFile.getProject(), o);
            psiFile.putUserData((Key)a, (Object)a2);
        }
        CachedValue cachedValue;
        try {
            cachedValue = a2;
            if (cachedValue == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolReference", "getCache"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        return (CachedValue<Map<ReferenceInfo, ResultInfo>>)cachedValue;
    }
    
    @NotNull
    private static CachedValue<Map<ReferenceInfo, ResultInfo>> a(final Project project, @NotNull final Object o) {
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "dependency", "com/jetbrains/cidr/lang/symbols/OCSymbolReference", "createCache"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        CachedValue cachedValue;
        try {
            cachedValue = CachedValuesManager.getManager(project).createCachedValue(() -> {
                try {
                    if (o == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "dependency", "com/jetbrains/cidr/lang/symbols/OCSymbolReference", "lambda$createCache$0"));
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                return CachedValueProvider.Result.create((Object)ContainerUtil.newConcurrentMap(), new Object[] { o });
            }, false);
            if (cachedValue == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolReference", "createCache"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return (CachedValue<Map<ReferenceInfo, ResultInfo>>)cachedValue;
    }
    
    @NotNull
    private static Key<CachedValue<Map<ReferenceInfo, ResultInfo>>> a(final boolean b, final boolean b2, final boolean b3, final Object o) {
        int n = 0;
        Label_0025: {
            try {
                if (b3) {
                    n = 0;
                    break Label_0025;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            try {
                if (b2) {
                    n = 1;
                    break Label_0025;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            n = 2;
        }
        final int n2 = n;
        int n5 = 0;
        int n6 = 0;
        Label_0063: {
            Label_0044: {
                int n3;
                try {
                    n3 = n2 * 2;
                    if (b) {
                        final int n4 = 1;
                        break Label_0044;
                    }
                }
                catch (IllegalStateException ex3) {
                    throw a(ex3);
                }
                final int n4 = 0;
                try {
                    n5 = (n3 + n4) * 2;
                    if (o == PsiModificationTracker.MODIFICATION_COUNT) {
                        n6 = 0;
                        break Label_0063;
                    }
                }
                catch (IllegalStateException ex4) {
                    throw a(ex4);
                }
            }
            n6 = 1;
        }
        final int n7 = n5 + n6;
        Key<CachedValue<Map<ReferenceInfo, ResultInfo>>> key;
        try {
            key = OCSymbolReference.RESOLVE_CACHES[n7];
            if (key == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolReference", "getCacheKey"));
            }
        }
        catch (IllegalStateException ex5) {
            throw a(ex5);
        }
        return key;
    }
    
    @NotNull
    public List<OCSymbol> doResolve(@NotNull final OCResolveContext ocResolveContext, final boolean b, final boolean b2, final boolean b3) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/OCSymbolReference", "doResolve"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final List<Object> list = OCLongActionUtil.execWithTimeoutProgressInDispatch("progressbar.long.resolve.description", "cidr.resolve.in.ui.timeout", ocResolveContext.getProject(), (com.intellij.openapi.util.Computable<List<Object>>)(() -> {
            try {
                if (ocResolveContext == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/OCSymbolReference", "lambda$doResolve$1"));
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            try {
                ProgressManager.checkCanceled();
                if (ocResolveContext.getNestingDepth() > 256) {
                    return Collections.emptyList();
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            boolean b4 = false;
            Label_0082: {
                try {
                    if (!b) {
                        b4 = true;
                        break Label_0082;
                    }
                }
                catch (IllegalStateException ex3) {
                    throw a(ex3);
                }
                b4 = false;
            }
            final OCSymbolReferenceResolver ocSymbolReferenceResolver = new OCSymbolReferenceResolver(b4, b2, ocResolveContext);
            final CommonProcessors.CollectProcessor<OCSymbol> collectProcessor = new CommonProcessors.CollectProcessor<OCSymbol>() {
                final /* synthetic */ Set val$set;
                
                protected boolean accept(final OCSymbol ocSymbol) {
                    return this.val$set.add(ocSymbol);
                }
            };
            SymbolFilter symbolFilter = this.myFilter;
            final NameWithToken removeTypeToken = removeTypeToken(this.myQualifiedName.getName());
            final String name = removeTypeToken.name;
            if (removeTypeToken.typeToken != null) {
                symbolFilter = SymbolKindFilter.parse(removeTypeToken.typeToken);
            }
            final OCQualifiedName qualifier = this.myQualifiedName.getQualifier();
            final boolean b5 = this.myQualifiedName instanceof OCQualifiedNameWithArguments;
            boolean b7 = false;
            Label_0197: {
                Label_0188: {
                    try {
                        if (this instanceof BaseClauseReference) {
                            break Label_0188;
                        }
                        final OCSymbolReference ocSymbolReference = this;
                        final boolean b6 = ocSymbolReference instanceof TemplateParamsReference;
                        if (b6) {
                            break Label_0188;
                        }
                        break Label_0188;
                    }
                    catch (IllegalStateException ex4) {
                        throw a(ex4);
                    }
                    try {
                        final OCSymbolReference ocSymbolReference = this;
                        final boolean b6 = ocSymbolReference instanceof TemplateParamsReference;
                        if (b6) {
                            b7 = true;
                            break Label_0197;
                        }
                    }
                    catch (IllegalStateException ex5) {
                        throw a(ex5);
                    }
                }
                b7 = false;
            }
            final boolean b8 = b7;
            Label_0564: {
                Label_0418: {
                    Label_0222: {
                        try {
                            if (qualifier == null) {
                                break Label_0418;
                            }
                            final OCQualifiedName ocQualifiedName = qualifier;
                            final OCQualifiedName ocQualifiedName2 = OCQualifiedName.GLOBAL;
                            final boolean b9 = ocQualifiedName.equals(ocQualifiedName2);
                            if (b9) {
                                break Label_0222;
                            }
                            break Label_0222;
                        }
                        catch (IllegalStateException ex6) {
                            throw a(ex6);
                        }
                        try {
                            final OCQualifiedName ocQualifiedName = qualifier;
                            final OCQualifiedName ocQualifiedName2 = OCQualifiedName.GLOBAL;
                            final boolean b9 = ocQualifiedName.equals(ocQualifiedName2);
                            if (b9) {
                                ocSymbolReferenceResolver.processSymbolsForGlobalRef(name, symbolFilter, null, b8, (Processor<OCSymbol>)collectProcessor, b5);
                                break Label_0564;
                            }
                        }
                        catch (IllegalStateException ex7) {
                            throw a(ex7);
                        }
                    }
                    final OCSymbolReference symbolReferenceToQualifier = this.getSymbolReferenceToQualifier();
                    final List<OCSymbol> resolveToSymbols = ocResolveContext.resolveToSymbols(symbolReferenceToQualifier, true, true, b, b2);
                    final boolean setNonImportedFlag = OCResolveContext.setNonImportedFlag(ocResolveContext, false);
                    final OCCommonProcessors.OrderedProcessor<OCSymbol> filteredByKindProcessor = ocSymbolReferenceResolver.getFilteredByKindProcessor(symbolFilter, (Processor<OCSymbol>)collectProcessor);
                    if (symbolReferenceToQualifier.getQualifiedName().equals(OCQualifiedName.with(name))) {
                        for (final OCSymbol ocSymbol : resolveToSymbols) {
                            Label_0359: {
                                try {
                                    if (!(ocSymbol instanceof OCStructSymbol)) {
                                        continue;
                                    }
                                    final OCSymbol ocSymbol2 = ocSymbol;
                                    final String s = ocSymbol2.getName();
                                    final String s2 = name;
                                    final boolean b10 = s.equals(s2);
                                    if (!b10) {
                                        break Label_0359;
                                    }
                                    continue;
                                }
                                catch (IllegalStateException ex8) {
                                    throw a(ex8);
                                }
                                try {
                                    final OCSymbol ocSymbol2 = ocSymbol;
                                    final String s = ocSymbol2.getName();
                                    final String s2 = name;
                                    final boolean b10 = s.equals(s2);
                                    if (b10) {
                                        continue;
                                    }
                                    ((OCStructSymbol)ocSymbol).processConstructors((Processor<? super OCFunctionSymbol>)filteredByKindProcessor, true, ocResolveContext);
                                }
                                catch (IllegalStateException ex9) {
                                    throw a(ex9);
                                }
                            }
                        }
                    }
                    ContainerUtil.process((List)resolveToSymbols, (Processor)new SymbolMembersProcessor(name, (Processor<OCSymbol>)filteredByKindProcessor, b, b2, ocResolveContext));
                    filteredByKindProcessor.finish();
                    OCResolveContext.setNonImportedFlag(ocResolveContext, setNonImportedFlag);
                    break Label_0564;
                }
                if (this instanceof LocalReference) {
                    final PsiElement localContext = ((LocalReference)this).getLocalContext();
                    Label_0534: {
                        try {
                            if (localContext == null || !localContext.isValid()) {
                                break Label_0534;
                            }
                        }
                        catch (IllegalStateException ex10) {
                            throw a(ex10);
                        }
                        final OCCommonProcessors.OrderedProcessor<OCSymbol> filteredByKindProcessor2 = ocSymbolReferenceResolver.getFilteredByKindProcessor(symbolFilter, (Processor<OCSymbol>)OCSymbolReferenceResolver.createResolveFilteringProcessor((Processor<OCSymbol>)collectProcessor, ocResolveContext));
                        ocSymbolReferenceResolver.processSymbolsForLocalRef(name, localContext, (Processor<OCSymbol>)filteredByKindProcessor2);
                        if (filteredByKindProcessor2.finish()) {
                            ocSymbolReferenceResolver.processSymbolsForGlobalRef(name, symbolFilter, ((GlobalReference)OCSymbolReferenceResolver.getGlobalReferenceFromLocal(OCQualifiedName.with(name), localContext, symbolFilter)).getSymbolContext(), b8, (Processor<OCSymbol>)filteredByKindProcessor2, b5);
                            filteredByKindProcessor2.finish();
                        }
                    }
                }
                else {
                    ocSymbolReferenceResolver.processSymbolsForGlobalRef(name, symbolFilter, ((GlobalReference)this).getSymbolContext(), b8, (Processor<OCSymbol>)collectProcessor, b5);
                }
            }
            Collection<OCSymbol> collection = (Collection<OCSymbol>)collectProcessor.getResults();
            if (b3) {
                collection = this.resolveTemplateSpecialization(ocResolveContext, collection);
            }
            return findDefinitions(ocSymbolReferenceResolver, collection);
        }));
        List<Object> list2 = null;
        Label_0088: {
            try {
                if (list == null) {
                    final List<Object> emptyList;
                    list2 = (emptyList = Collections.emptyList());
                    break Label_0088;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            List<Object> emptyList;
            list2 = (emptyList = list);
            try {
                if (emptyList == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolReference", "doResolve"));
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
        }
        return (List<OCSymbol>)list2;
    }
    
    @NotNull
    public static NameWithToken removeTypeToken(@Nullable String string) {
        String name = null;
        if (string != null) {
            String substring = string;
            int i = 1;
            while (i != 0) {
                i = 0;
                final IElementType[] types = OCTokenTypes.TYPE_QUALIFIERS.getTypes();
                for (int length = types.length, j = 0; j < length; ++j) {
                    final String name2 = ((OCElementType)types[j]).getName();
                    if (substring.startsWith(name2 + " ")) {
                        substring = substring.substring(name2.length() + 1);
                        i = 1;
                    }
                }
            }
            for (final OCElementType ocElementType : OCSymbolReference.TYPE_TOKENS) {
                if (substring.startsWith(ocElementType.getName() + " ")) {
                    string = string.substring(0, string.length() - substring.length()) + substring.substring(ocElementType.getName().length() + 1);
                    name = ocElementType.getName();
                    break;
                }
            }
        }
        NameWithToken nameWithToken;
        try {
            nameWithToken = new NameWithToken(string, name);
            if (nameWithToken == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolReference", "removeTypeToken"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return nameWithToken;
    }
    
    protected Collection<OCSymbol> resolveTemplateSpecialization(@NotNull final OCResolveContext ocResolveContext, final Collection<OCSymbol> collection) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "resolver", "com/jetbrains/cidr/lang/symbols/OCSymbolReference", "resolveTemplateSpecialization"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (collection.isEmpty()) {
                return collection;
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        boolean b = false;
        List<OCTypeArgument> list = Collections.emptyList();
        final boolean b2 = this.myQualifiedName instanceof OCQualifiedNameWithArguments;
        Label_0123: {
            if (b2) {
                b = true;
                list = ((OCQualifiedNameWithArguments)this.myQualifiedName).getArguments();
            }
            else {
                try {
                    if (!ocResolveContext.isObjc() || ContainerUtil.find((Iterable)collection, (Condition)OCInterfaceSymbol.IS_GENERIC_OBJC_CLASS) == null) {
                        break Label_0123;
                    }
                }
                catch (IllegalStateException ex3) {
                    throw a(ex3);
                }
                b = true;
            }
            try {
                if (!b) {
                    return collection;
                }
            }
            catch (IllegalStateException ex4) {
                throw a(ex4);
            }
        }
        final ArrayList<OCTemplateSymbol<?>> list2 = new ArrayList<OCTemplateSymbol<?>>();
        final ArrayList<OCSymbol> list3 = new ArrayList<OCSymbol>(collection);
        final ArrayList<OCTemplateSymbol> list4 = new ArrayList<OCTemplateSymbol>();
        for (final OCSymbol ocSymbol : list3) {
            try {
                switch (a(ocSymbol, b2)) {
                    case NEVER_CHECK: {
                        break;
                    }
                    default: {
                        continue;
                    }
                    case ALWAYS_CHECK: {
                        list4.add((OCTemplateSymbol<?>)ocSymbol);
                        continue;
                    }
                }
            }
            catch (IllegalStateException ex5) {
                throw a(ex5);
            }
            list2.add((OCTemplateSymbol<?>)ocSymbol);
        }
        final Iterator<OCTemplateSymbol> iterator2 = OCSimpleTypeSubstitution.resolveTemplateSpecialization(list4, list, ocResolveContext).iterator();
        while (iterator2.hasNext()) {
            list2.add(ocResolveContext.getSubstitution().substitute(iterator2.next(), ocResolveContext));
        }
        return (Collection<OCSymbol>)list2;
    }
    
    private static SpecializationCheckType a(@NotNull final OCSymbol p0, final boolean p1) {
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
        //    18: ldc             "symbol"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/symbols/OCSymbolReference"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "shouldCheckSpecialization"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReference.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    43: athrow         
        //    44: aload_0        
        //    45: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    50: astore_2       
        //    51: aload_2        
        //    52: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.TEMPLATE_TYPE_PARAMETER:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    55: if_acmpeq       72
        //    58: aload_2        
        //    59: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.SYMBOL_USING_SYMBOL:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    62: if_acmpne       80
        //    65: goto            72
        //    68: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReference.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    71: athrow         
        //    72: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolReference$SpecializationCheckType.NEVER_CHECK:Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference$SpecializationCheckType;
        //    75: areturn        
        //    76: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReference.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    79: athrow         
        //    80: aload_0        
        //    81: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //    84: ifeq            132
        //    87: aload_0        
        //    88: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
        //    91: ifeq            128
        //    94: goto            101
        //    97: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReference.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   100: athrow         
        //   101: aload_0        
        //   102: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
        //   105: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol.isTemplateSymbol:()Z
        //   110: ifne            128
        //   113: goto            120
        //   116: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReference.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   119: athrow         
        //   120: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolReference$SpecializationCheckType.NEVER_CHECK:Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference$SpecializationCheckType;
        //   123: areturn        
        //   124: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReference.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   127: athrow         
        //   128: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolReference$SpecializationCheckType.ALWAYS_CHECK:Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference$SpecializationCheckType;
        //   131: areturn        
        //   132: aload_0        
        //   133: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol;
        //   136: ifeq            147
        //   139: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolReference$SpecializationCheckType.NEVER_CHECK:Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference$SpecializationCheckType;
        //   142: areturn        
        //   143: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReference.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   146: athrow         
        //   147: iload_1        
        //   148: ifeq            161
        //   151: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolReference$SpecializationCheckType.SKIP:Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference$SpecializationCheckType;
        //   154: goto            164
        //   157: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReference.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   160: athrow         
        //   161: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolReference$SpecializationCheckType.NEVER_CHECK:Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference$SpecializationCheckType;
        //   164: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      40     40     44     Ljava/lang/IllegalStateException;
        //  51     65     68     72     Ljava/lang/IllegalStateException;
        //  58     76     76     80     Ljava/lang/IllegalStateException;
        //  80     94     97     101    Ljava/lang/IllegalStateException;
        //  87     113    116    120    Ljava/lang/IllegalStateException;
        //  101    124    124    128    Ljava/lang/IllegalStateException;
        //  132    143    143    147    Ljava/lang/IllegalStateException;
        //  147    157    157    161    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0101:
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
    
    protected static List<OCSymbol> findDefinitions(final OCSymbolReferenceResolver ocSymbolReferenceResolver, final Collection<OCSymbol> collection) {
        if (!collection.isEmpty()) {
            for (final OCSymbol ocSymbol : collection) {
                try {
                    if (!ocSymbol.isPredeclaration()) {
                        return (List<OCSymbol>)collection;
                    }
                    continue;
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
            }
            final OCSymbol ocSymbol2 = collection.iterator().next();
            if (ocSymbol2 instanceof OCSymbolWithQualifiedName) {
                final Collection<OCSymbol> implementationsOfSymbol = ocSymbolReferenceResolver.getImplementationsOfSymbol((OCSymbolWithQualifiedName)ocSymbol2, false);
                try {
                    if (!implementationsOfSymbol.isEmpty()) {
                        collection.addAll((Collection<?>)implementationsOfSymbol);
                        return (List<OCSymbol>)collection;
                    }
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
            }
        }
        return (List<OCSymbol>)collection;
    }
    
    static {
        TYPE_TOKENS = new OCElementType[] { OCTokenTypes.STRUCT_KEYWORD, OCTokenTypes.ENUM_KEYWORD, OCTokenTypes.UNION_KEYWORD, OCTokenTypes.CLASS_KEYWORD };
        RESOLVE_CACHES = new Key[12];
        int i = 0;
        try {
            while (i < OCSymbolReference.RESOLVE_CACHES.length) {
                OCSymbolReference.RESOLVE_CACHES[i] = (Key<CachedValue<Map<ReferenceInfo, ResultInfo>>>)Key.create("SYMBOL_RESOLVE_CACHE_IN_FILE" + i);
                ++i;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
    
    @FunctionalInterface
    public interface SymbolFilter
    {
        public static final TrueSymbolFilter NONE = new TrueSymbolFilter();
        
        boolean accept(final OCSymbol p0);
    }
    
    public static class TrueSymbolFilter implements SymbolFilter
    {
        @Override
        public boolean accept(final OCSymbol ocSymbol) {
            return true;
        }
        
        @Override
        public String toString() {
            return "*any symbol*";
        }
    }
    
    public enum SymbolKindFilter implements SymbolFilter
    {
        NONE, 
        ONLY_NAMESPACE, 
        ONLY_NAMESPACE_LIKE, 
        ONLY_STRUCT, 
        ONLY_ENUM, 
        ONLY_UNION;
        
        public static SymbolFilter parse(final String s) {
            if (s == null) {
                return SymbolKindFilter.NONE;
            }
            if (s.equals("struct") || s.equals("class")) {
                return SymbolKindFilter.ONLY_STRUCT;
            }
            if (s.equals("enum")) {
                return SymbolKindFilter.ONLY_ENUM;
            }
            if (s.equals("union")) {
                return SymbolKindFilter.ONLY_UNION;
            }
            return SymbolKindFilter.NONE;
        }
        
        @Override
        public boolean accept(final OCSymbol ocSymbol) {
            final OCSymbolKind kind = ocSymbol.getKind();
            switch (this) {
                case NONE: {
                    return true;
                }
                case ONLY_NAMESPACE: {
                    return kind == OCSymbolKind.NAMESPACE || kind == OCSymbolKind.NAMESPACE_ALIAS;
                }
                case ONLY_NAMESPACE_LIKE: {
                    return kind.canBeNamespace() || kind == OCSymbolKind.USING_SYMBOL_ALIAS || kind == OCSymbolKind.SYMBOL_USING_SYMBOL;
                }
                case ONLY_STRUCT: {
                    return kind == OCSymbolKind.STRUCT;
                }
                case ONLY_ENUM: {
                    return kind == OCSymbolKind.ENUM;
                }
                case ONLY_UNION: {
                    return kind == OCSymbolKind.UNION;
                }
                default: {
                    return false;
                }
            }
        }
        
        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    }
    
    private enum SpecializationCheckType
    {
        ALWAYS_CHECK, 
        NEVER_CHECK, 
        SKIP;
    }
    
    public static class GlobalReference extends OCSymbolReference
    {
        @Nullable
        protected OCSymbolWithQualifiedName mySymbolContext;
        
        public GlobalReference() {
        }
        
        public GlobalReference(@NotNull final OCQualifiedName ocQualifiedName, @Nullable final OCSymbolWithQualifiedName mySymbolContext, final SymbolFilter symbolFilter) {
            if (ocQualifiedName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "qualifiedName", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$GlobalReference", "<init>"));
            }
            super(ocQualifiedName, symbolFilter);
            if (this.mySymbolContext == null) {
                this.mySymbolContext = mySymbolContext;
            }
        }
        
        @Nullable
        public OCSymbolWithQualifiedName getSymbolContext() {
            return this.mySymbolContext;
        }
        
        public void setSymbolContext(@Nullable final OCSymbolWithQualifiedName mySymbolContext) {
            this.mySymbolContext = mySymbolContext;
        }
        
        @Override
        public OCSymbolReference getSymbolReferenceToQualifier() {
            return new GlobalReference(this.getQualifiedName().getQualifier(), this.mySymbolContext, this.getFilterForQualifier());
        }
        
        @NotNull
        @Override
        public OCSymbolReference createReferenceInSameContext(@NotNull final OCQualifiedName ocQualifiedName) {
            try {
                if (ocQualifiedName == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$GlobalReference", "createReferenceInSameContext"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            GlobalReference globalReference;
            try {
                globalReference = new GlobalReference(ocQualifiedName, this.mySymbolContext, this.myFilter);
                if (globalReference == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$GlobalReference", "createReferenceInSameContext"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            return globalReference;
        }
        
        @Override
        public boolean equals(final Object o) {
            try {
                if (this == o) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            Label_0039: {
                try {
                    if (o == null) {
                        return false;
                    }
                    final GlobalReference globalReference = this;
                    final Class<? extends GlobalReference> clazz = globalReference.getClass();
                    final Object o2 = o;
                    final Class<?> clazz2 = o2.getClass();
                    if (clazz != clazz2) {
                        return false;
                    }
                    break Label_0039;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final GlobalReference globalReference = this;
                    final Class<? extends GlobalReference> clazz = globalReference.getClass();
                    final Object o2 = o;
                    final Class<?> clazz2 = o2.getClass();
                    if (clazz != clazz2) {
                        return false;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    if (!super.equals(o)) {
                        return false;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            final GlobalReference globalReference2 = (GlobalReference)o;
            Label_0093: {
                Label_0086: {
                    try {
                        if (this.mySymbolContext == null) {
                            break Label_0093;
                        }
                        final GlobalReference globalReference3 = this;
                        final OCSymbolWithQualifiedName ocSymbolWithQualifiedName = globalReference3.mySymbolContext;
                        final GlobalReference globalReference4 = globalReference2;
                        final OCSymbolWithQualifiedName ocSymbolWithQualifiedName2 = globalReference4.mySymbolContext;
                        final boolean b = ocSymbolWithQualifiedName.equals(ocSymbolWithQualifiedName2);
                        if (!b) {
                            break Label_0086;
                        }
                        return true;
                    }
                    catch (IllegalArgumentException ex5) {
                        throw a(ex5);
                    }
                    try {
                        final GlobalReference globalReference3 = this;
                        final OCSymbolWithQualifiedName ocSymbolWithQualifiedName = globalReference3.mySymbolContext;
                        final GlobalReference globalReference4 = globalReference2;
                        final OCSymbolWithQualifiedName ocSymbolWithQualifiedName2 = globalReference4.mySymbolContext;
                        final boolean b = ocSymbolWithQualifiedName.equals(ocSymbolWithQualifiedName2);
                        if (!b) {
                            return false;
                        }
                        return true;
                    }
                    catch (IllegalArgumentException ex6) {
                        throw a(ex6);
                    }
                }
                try {
                    if (globalReference2.mySymbolContext != null) {
                        return false;
                    }
                }
                catch (IllegalArgumentException ex7) {
                    throw a(ex7);
                }
            }
            return true;
        }
        
        @Override
        public int hashCode() {
            final int hashCode = super.hashCode();
            int n;
            try {
                n = 31 * hashCode;
                if (this.mySymbolContext != null) {
                    final int hashCode2 = this.mySymbolContext.hashCode();
                    return n + hashCode2;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            final int hashCode2 = 0;
            return n + hashCode2;
        }
        
        @Override
        public String toString() {
            return "GLOBAL (" + this.getQualifiedName() + "):" + this.mySymbolContext;
        }
        
        @Override
        public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final OCSymbolReference ocSymbolReference, @NotNull final OCSymbolReference ocSymbolReference2) {
            try {
                if (comparator == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$GlobalReference", "deepEqualStep"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (ocSymbolReference == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$GlobalReference", "deepEqualStep"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                if (ocSymbolReference2 == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$GlobalReference", "deepEqualStep"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                if (!super.deepEqualStep(comparator, ocSymbolReference, ocSymbolReference2)) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            final GlobalReference globalReference = (GlobalReference)ocSymbolReference;
            final GlobalReference globalReference2 = (GlobalReference)ocSymbolReference2;
            try {
                if (!comparator.equalObjects(globalReference.mySymbolContext, (DeepEqual.Equality<Object>)globalReference2.mySymbolContext)) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
            return true;
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    public static class LocalReference extends OCSymbolReference
    {
        @Nullable
        private PsiElement myLocalContext;
        
        private LocalReference(@NotNull final OCQualifiedName ocQualifiedName, @Nullable final PsiElement myLocalContext, final SymbolFilter symbolFilter) {
            if (ocQualifiedName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "qualifiedName", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$LocalReference", "<init>"));
            }
            super(ocQualifiedName, symbolFilter);
            this.myLocalContext = myLocalContext;
        }
        
        @Override
        protected Object getCacheDependency(@NotNull final Project project) {
            try {
                if (project == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$LocalReference", "getCacheDependency"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return PsiModificationTracker.MODIFICATION_COUNT;
        }
        
        @Nullable
        public PsiElement getLocalContext() {
            return this.myLocalContext;
        }
        
        @Override
        public OCSymbolReference getSymbolReferenceToQualifier() {
            return new LocalReference(this.getQualifiedName().getQualifier(), this.myLocalContext, this.getFilterForQualifier());
        }
        
        @NotNull
        @Override
        public OCSymbolReference createReferenceInSameContext(@NotNull final OCQualifiedName ocQualifiedName) {
            try {
                if (ocQualifiedName == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$LocalReference", "createReferenceInSameContext"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            LocalReference localReference;
            try {
                localReference = new LocalReference(ocQualifiedName, this.myLocalContext, this.myFilter);
                if (localReference == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$LocalReference", "createReferenceInSameContext"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            return localReference;
        }
        
        @Override
        public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final OCSymbolReference ocSymbolReference, @NotNull final OCSymbolReference ocSymbolReference2) {
            try {
                if (comparator == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$LocalReference", "deepEqualStep"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (ocSymbolReference == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$LocalReference", "deepEqualStep"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                if (ocSymbolReference2 == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$LocalReference", "deepEqualStep"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                if (!super.deepEqualStep(comparator, ocSymbolReference, ocSymbolReference2)) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            final LocalReference localReference = (LocalReference)ocSymbolReference;
            final LocalReference localReference2 = (LocalReference)ocSymbolReference2;
            try {
                if (!Comparing.equal((Object)localReference.myLocalContext, (Object)localReference2.myLocalContext)) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
            return true;
        }
        
        @Override
        public int hashCode() {
            final int hashCode = super.hashCode();
            int n;
            try {
                n = 31 * hashCode;
                if (this.myLocalContext != null) {
                    final int hashCode2 = this.myLocalContext.hashCode();
                    return n + hashCode2;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            final int hashCode2 = 0;
            return n + hashCode2;
        }
        
        @NotNull
        @Override
        public List<OCSymbol> doResolve(@NotNull final OCResolveContext ocResolveContext, final boolean b, final boolean b2, final boolean b3) {
            try {
                if (ocResolveContext == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$LocalReference", "doResolve"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            Label_0124: {
                if (this.myLocalContext != null) {
                    final OCSymbol tryResolveThisSelfSuper = OCThisSelfSuperSymbol.tryResolveThisSelfSuper(this.getQualifiedName().toString(), this.myLocalContext, ocResolveContext);
                    List<OCSymbol> list = null;
                    Label_0089: {
                        try {
                            if (tryResolveThisSelfSuper == null) {
                                break Label_0124;
                            }
                            final OCSymbol ocSymbol = tryResolveThisSelfSuper;
                            list = Collections.singletonList(ocSymbol);
                            if (list == null) {
                                break Label_0089;
                            }
                            return (List<OCSymbol>)list;
                        }
                        catch (IllegalArgumentException ex2) {
                            throw a(ex2);
                        }
                        try {
                            final OCSymbol ocSymbol = tryResolveThisSelfSuper;
                            list = Collections.singletonList(ocSymbol);
                            if (list == null) {
                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$LocalReference", "doResolve"));
                            }
                        }
                        catch (IllegalArgumentException ex3) {
                            throw a(ex3);
                        }
                    }
                    return (List<OCSymbol>)list;
                }
            }
            final PsiElement element = ocResolveContext.getElement();
            PsiElement myLocalContext = null;
            Label_0151: {
                try {
                    if (this.myLocalContext != null) {
                        myLocalContext = this.myLocalContext;
                        break Label_0151;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
                myLocalContext = element;
            }
            ocResolveContext.setElement(myLocalContext);
            final List<OCSymbol> doResolve = super.doResolve(ocResolveContext, b, b2, b3);
            List<OCSymbol> list2;
            try {
                ocResolveContext.setElement(element);
                list2 = doResolve;
                if (list2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$LocalReference", "doResolve"));
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
            return list2;
        }
        
        @Override
        public String toString() {
            return "LOCAL (" + this.getQualifiedName() + "):" + this.myLocalContext;
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    public static class BaseClauseReference extends GlobalReference
    {
        public BaseClauseReference() {
        }
        
        public BaseClauseReference(@NotNull final OCQualifiedName ocQualifiedName, @Nullable final OCSymbolWithQualifiedName ocSymbolWithQualifiedName, final SymbolFilter symbolFilter) {
            if (ocQualifiedName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "qualifiedName", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$BaseClauseReference", "<init>"));
            }
            super(ocQualifiedName, ocSymbolWithQualifiedName, symbolFilter);
        }
        
        @Override
        public OCSymbolReference getSymbolReferenceToQualifier() {
            return new BaseClauseReference(this.getQualifiedName().getQualifier(), this.getSymbolContext(), this.getFilterForQualifier());
        }
        
        @NotNull
        @Override
        public OCSymbolReference createReferenceInSameContext(@NotNull final OCQualifiedName ocQualifiedName) {
            try {
                if (ocQualifiedName == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$BaseClauseReference", "createReferenceInSameContext"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            BaseClauseReference baseClauseReference;
            try {
                baseClauseReference = new BaseClauseReference(ocQualifiedName, this.getSymbolContext(), this.myFilter);
                if (baseClauseReference == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$BaseClauseReference", "createReferenceInSameContext"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            return baseClauseReference;
        }
        
        @Override
        public String toString() {
            return "BASE CLAUSE (" + this.getQualifiedName() + "):" + this.getSymbolContext();
        }
        
        private static IllegalArgumentException b(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    public static class TemplateParamsReference extends GlobalReference
    {
        public TemplateParamsReference() {
        }
        
        public TemplateParamsReference(@NotNull final OCQualifiedName ocQualifiedName, @Nullable final OCSymbolWithQualifiedName ocSymbolWithQualifiedName, final SymbolFilter symbolFilter) {
            if (ocQualifiedName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "qualifiedName", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$TemplateParamsReference", "<init>"));
            }
            super(ocQualifiedName, ocSymbolWithQualifiedName, symbolFilter);
        }
        
        @Override
        public OCSymbolReference getSymbolReferenceToQualifier() {
            return new TemplateParamsReference(this.getQualifiedName().getQualifier(), this.getSymbolContext(), this.getFilterForQualifier());
        }
        
        @NotNull
        @Override
        public OCSymbolReference createReferenceInSameContext(@NotNull final OCQualifiedName ocQualifiedName) {
            try {
                if (ocQualifiedName == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$TemplateParamsReference", "createReferenceInSameContext"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            TemplateParamsReference templateParamsReference;
            try {
                templateParamsReference = new TemplateParamsReference(ocQualifiedName, this.getSymbolContext(), this.myFilter);
                if (templateParamsReference == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$TemplateParamsReference", "createReferenceInSameContext"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            return templateParamsReference;
        }
        
        @Override
        public String toString() {
            return "TEMPLATE (" + this.getQualifiedName() + "):" + this.getSymbolContext();
        }
        
        private static IllegalArgumentException b(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    public static class LambdaLocalReference extends OCSymbolReference
    {
        private OCLambdaExpressionSymbol myLambda;
        private long myOffset;
        private OCSymbolReference myParentScopeReference;
        
        public LambdaLocalReference() {
        }
        
        public LambdaLocalReference(@NotNull final OCQualifiedName ocQualifiedName, final long myOffset, @NotNull final OCLambdaExpressionSymbol myLambda, @NotNull final SymbolFilter symbolFilter, @NotNull final OCSymbolReference myParentScopeReference) {
            if (ocQualifiedName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "qualifiedName", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$LambdaLocalReference", "<init>"));
            }
            if (myLambda == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "lambda", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$LambdaLocalReference", "<init>"));
            }
            if (symbolFilter == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "filter", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$LambdaLocalReference", "<init>"));
            }
            if (myParentScopeReference == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parentScopeReference", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$LambdaLocalReference", "<init>"));
            }
            super(ocQualifiedName, symbolFilter);
            this.myOffset = myOffset;
            this.myLambda = myLambda;
            this.myParentScopeReference = myParentScopeReference;
        }
        
        @Override
        public OCSymbolReference getSymbolReferenceToQualifier() {
            return new LambdaLocalReference(this.getQualifiedName().getQualifier(), this.myOffset, this.myLambda, this.getFilterForQualifier(), this.myParentScopeReference);
        }
        
        @NotNull
        @Override
        public OCSymbolReference createReferenceInSameContext(@NotNull final OCQualifiedName ocQualifiedName) {
            try {
                if (ocQualifiedName == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$LambdaLocalReference", "createReferenceInSameContext"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            LambdaLocalReference lambdaLocalReference;
            try {
                lambdaLocalReference = new LambdaLocalReference(ocQualifiedName, this.myOffset, this.myLambda, this.myFilter, this.myParentScopeReference);
                if (lambdaLocalReference == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$LambdaLocalReference", "createReferenceInSameContext"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            return lambdaLocalReference;
        }
        
        @Override
        public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final OCSymbolReference ocSymbolReference, @NotNull final OCSymbolReference ocSymbolReference2) {
            try {
                if (comparator == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$LambdaLocalReference", "deepEqualStep"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (ocSymbolReference == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$LambdaLocalReference", "deepEqualStep"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                if (ocSymbolReference2 == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$LambdaLocalReference", "deepEqualStep"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                if (!super.deepEqualStep(comparator, ocSymbolReference, ocSymbolReference2)) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            final LambdaLocalReference lambdaLocalReference = (LambdaLocalReference)ocSymbolReference;
            final LambdaLocalReference lambdaLocalReference2 = (LambdaLocalReference)ocSymbolReference2;
            try {
                if (!Comparing.equal((Object)lambdaLocalReference.myLambda, (Object)lambdaLocalReference2.myLambda)) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
            try {
                if (!Comparing.equal((Object)lambdaLocalReference.myOffset, (Object)lambdaLocalReference2.myOffset)) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
            return true;
        }
        
        @Override
        public int hashCode() {
            final int hashCode = super.hashCode();
            int n = 0;
            int hashCode2 = 0;
            Label_0031: {
                try {
                    n = 31 * hashCode;
                    if (this.myLambda != null) {
                        hashCode2 = this.myLambda.hashCode();
                        break Label_0031;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                hashCode2 = 0;
            }
            final int n2 = (int)(31 * (n + hashCode2) + this.myOffset);
            int n3;
            try {
                n3 = 31 * n2;
                if (this.myParentScopeReference != null) {
                    final int hashCode3 = this.myParentScopeReference.hashCode();
                    return n3 + hashCode3;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            final int hashCode3 = 0;
            return n3 + hashCode3;
        }
        
        @NotNull
        @Override
        public List<OCSymbol> doResolve(@NotNull final OCResolveContext ocResolveContext, final boolean b, final boolean b2, final boolean b3) {
            try {
                if (ocResolveContext == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$LambdaLocalReference", "doResolve"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            List<OCSymbol> doResolve = null;
            Label_0144: {
                try {
                    if (this.myLambda == null || this.myQualifiedName.getQualifier() != null) {
                        break Label_0144;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                final OCSymbol resolveLambdaLocalSymbolInTable = OCResolveUtil.resolveLambdaLocalSymbolInTable(this.myLambda.getLocalVarsAndParams(), this.myQualifiedName, this.myOffset);
                List<OCSymbol> list = null;
                Label_0109: {
                    try {
                        if (resolveLambdaLocalSymbolInTable == null) {
                            break Label_0144;
                        }
                        final OCSymbol ocSymbol = resolveLambdaLocalSymbolInTable;
                        list = Collections.singletonList(ocSymbol);
                        if (list == null) {
                            break Label_0109;
                        }
                        return (List<OCSymbol>)list;
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                    try {
                        final OCSymbol ocSymbol = resolveLambdaLocalSymbolInTable;
                        list = Collections.singletonList(ocSymbol);
                        if (list == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$LambdaLocalReference", "doResolve"));
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                }
                return (List<OCSymbol>)list;
                try {
                    doResolve = this.myParentScopeReference.doResolve(ocResolveContext, b, b2, b3);
                    if (doResolve == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$LambdaLocalReference", "doResolve"));
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
            }
            return doResolve;
        }
        
        @Override
        public String toString() {
            return "LAMBDA LOCAL (" + this.getQualifiedName() + ")";
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    private static class ReferenceInfo
    {
        @NotNull
        final OCSymbolReference symbolReference;
        @NotNull
        final OCTypeSubstitution substitution;
        @Nullable
        final OCResolveContext context;
        private final boolean resolveSpecialization;
        
        private ReferenceInfo(@NotNull final OCSymbolReference symbolReference, @NotNull final OCTypeSubstitution substitution, @Nullable final OCResolveContext context, final boolean resolveSpecialization) {
            if (symbolReference == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbolReference", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$ReferenceInfo", "<init>"));
            }
            if (substitution == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "substitution", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$ReferenceInfo", "<init>"));
            }
            this.symbolReference = symbolReference;
            this.substitution = substitution;
            this.context = context;
            this.resolveSpecialization = resolveSpecialization;
        }
        
        @Override
        public boolean equals(final Object p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_1        
            //     1: instanceof      Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference$ReferenceInfo;
            //     4: ifne            13
            //     7: iconst_0       
            //     8: ireturn        
            //     9: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReference$ReferenceInfo.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    12: athrow         
            //    13: aload_1        
            //    14: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference$ReferenceInfo;
            //    17: astore_2       
            //    18: aload_0        
            //    19: getfield        com/jetbrains/cidr/lang/symbols/OCSymbolReference$ReferenceInfo.resolveSpecialization:Z
            //    22: aload_2        
            //    23: getfield        com/jetbrains/cidr/lang/symbols/OCSymbolReference$ReferenceInfo.resolveSpecialization:Z
            //    26: if_icmpne       93
            //    29: aload_0        
            //    30: getfield        com/jetbrains/cidr/lang/symbols/OCSymbolReference$ReferenceInfo.context:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
            //    33: ifnull          93
            //    36: goto            43
            //    39: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReference$ReferenceInfo.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    42: athrow         
            //    43: aload_0        
            //    44: getfield        com/jetbrains/cidr/lang/symbols/OCSymbolReference$ReferenceInfo.symbolReference:Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;
            //    47: aload_2        
            //    48: getfield        com/jetbrains/cidr/lang/symbols/OCSymbolReference$ReferenceInfo.symbolReference:Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;
            //    51: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolReference.equals:(Ljava/lang/Object;)Z
            //    54: ifeq            93
            //    57: goto            64
            //    60: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReference$ReferenceInfo.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    63: athrow         
            //    64: aload_0        
            //    65: getfield        com/jetbrains/cidr/lang/symbols/OCSymbolReference$ReferenceInfo.substitution:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;
            //    68: aload_2        
            //    69: getfield        com/jetbrains/cidr/lang/symbols/OCSymbolReference$ReferenceInfo.substitution:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;
            //    72: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.equals:(Ljava/lang/Object;)Z
            //    75: ifeq            93
            //    78: goto            85
            //    81: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReference$ReferenceInfo.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    84: athrow         
            //    85: iconst_1       
            //    86: goto            94
            //    89: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReference$ReferenceInfo.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    92: athrow         
            //    93: iconst_0       
            //    94: ireturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  0      9      9      13     Ljava/lang/IllegalArgumentException;
            //  18     36     39     43     Ljava/lang/IllegalArgumentException;
            //  29     57     60     64     Ljava/lang/IllegalArgumentException;
            //  43     78     81     85     Ljava/lang/IllegalArgumentException;
            //  64     89     89     93     Ljava/lang/IllegalArgumentException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0043:
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
        public int hashCode() {
            final int n = 31 * this.symbolReference.hashCode() + this.substitution.hashCode();
            int n2;
            try {
                n2 = 31 * n;
                if (this.resolveSpecialization) {
                    final int n3 = 1;
                    return n2 + n3;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            final int n3 = 0;
            return n2 + n3;
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    private static class ResultInfo
    {
        final List<OCSymbol> symbols;
        final Set<OCTypeParameterSymbol> typeDependencies;
        
        private ResultInfo(@NotNull final List<OCSymbol> symbols, @Nullable final Set<OCTypeParameterSymbol> typeDependencies) {
            if (symbols == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbols", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$ResultInfo", "<init>"));
            }
            this.symbols = symbols;
            this.typeDependencies = typeDependencies;
        }
    }
    
    public static class NameWithToken
    {
        public final String name;
        public final String typeToken;
        
        public NameWithToken(final String name, final String typeToken) {
            this.name = name;
            this.typeToken = typeToken;
        }
    }
    
    private static class SymbolMembersProcessor implements Processor<OCSymbol>
    {
        private final String myName;
        private final Processor<OCSymbol> myProcessor;
        private final boolean myOnlySimpleNamespaces;
        private final boolean myOnlyTypes;
        private OCResolveContext myMemoization;
        private final THashSet<OCSymbol> myProcessed;
        
        public SymbolMembersProcessor(@Nullable final String myName, final Processor<OCSymbol> myProcessor, final boolean myOnlySimpleNamespaces, final boolean myOnlyTypes, @NotNull final OCResolveContext myMemoization) {
            if (myMemoization == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$SymbolMembersProcessor", "<init>"));
            }
            this.myName = myName;
            this.myProcessor = myProcessor;
            this.myOnlySimpleNamespaces = myOnlySimpleNamespaces;
            this.myOnlyTypes = myOnlyTypes;
            this.myMemoization = myMemoization;
            this.myProcessed = (THashSet<OCSymbol>)new THashSet((TObjectHashingStrategy)new TObjectHashingStrategy<OCSymbol>() {
                public int computeHashCode(final OCSymbol ocSymbol) {
                    return ocSymbol.hashCode();
                }
                
                public boolean equals(final OCSymbol ocSymbol, final OCSymbol ocSymbol2) {
                    return ocSymbol == ocSymbol2;
                }
            });
        }
        
        public boolean process(final OCSymbol ocSymbol) {
            try {
                if (!this.myProcessed.add((Object)ocSymbol)) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            final OCResolveContext myMemoization = this.myMemoization;
            try {
                OCTypeParameterTypeSymbol ocTypeParameterTypeSymbol = null;
                Label_0128: {
                    Label_0116: {
                        Label_0095: {
                            OCNamespaceSymbol ocNamespaceSymbol2 = null;
                            String s = null;
                            Label_0063: {
                                try {
                                    this.myMemoization = this.myMemoization.useFor(ocSymbol);
                                    if (!(ocSymbol instanceof OCNamespaceSymbol)) {
                                        break Label_0095;
                                    }
                                    final OCNamespaceSymbol ocNamespaceSymbol = (OCNamespaceSymbol)ocSymbol;
                                    ocNamespaceSymbol2 = ocNamespaceSymbol;
                                    final SymbolMembersProcessor symbolMembersProcessor = this;
                                    s = symbolMembersProcessor.myName;
                                    final SymbolMembersProcessor symbolMembersProcessor2 = this;
                                    final boolean b = symbolMembersProcessor2.myOnlySimpleNamespaces;
                                    if (!b) {
                                        break Label_0063;
                                    }
                                    break Label_0063;
                                }
                                catch (IllegalArgumentException ex2) {
                                    throw a(ex2);
                                }
                                try {
                                    final OCNamespaceSymbol ocNamespaceSymbol = (OCNamespaceSymbol)ocSymbol;
                                    ocNamespaceSymbol2 = ocNamespaceSymbol;
                                    final SymbolMembersProcessor symbolMembersProcessor = this;
                                    s = symbolMembersProcessor.myName;
                                    final SymbolMembersProcessor symbolMembersProcessor2 = this;
                                    final boolean b = symbolMembersProcessor2.myOnlySimpleNamespaces;
                                    if (!b) {
                                        final boolean b2 = true;
                                        return OCStructType.processMembersOfNamespace(ocNamespaceSymbol2, s, b2, this.myOnlyTypes, this.myProcessor, this.myMemoization);
                                    }
                                }
                                catch (IllegalArgumentException ex3) {
                                    throw a(ex3);
                                }
                            }
                            final boolean b2 = false;
                            return OCStructType.processMembersOfNamespace(ocNamespaceSymbol2, s, b2, this.myOnlyTypes, this.myProcessor, this.myMemoization);
                            try {
                                if (!(ocSymbol instanceof OCTypeParameterSymbol)) {
                                    return true;
                                }
                                final OCNamespaceSymbol ocNamespaceSymbol3 = (OCNamespaceSymbol)ocSymbol;
                                final boolean b3 = ocNamespaceSymbol3 instanceof OCTypeParameterTypeSymbol;
                                if (b3) {
                                    break Label_0116;
                                }
                                break Label_0116;
                            }
                            catch (IllegalArgumentException ex4) {
                                throw a(ex4);
                            }
                        }
                        try {
                            final OCNamespaceSymbol ocNamespaceSymbol3 = (OCNamespaceSymbol)ocSymbol;
                            final boolean b3 = ocNamespaceSymbol3 instanceof OCTypeParameterTypeSymbol;
                            if (b3) {
                                ocTypeParameterTypeSymbol = (OCTypeParameterTypeSymbol)ocSymbol;
                                break Label_0128;
                            }
                        }
                        catch (IllegalArgumentException ex5) {
                            throw a(ex5);
                        }
                    }
                    ocTypeParameterTypeSymbol = null;
                }
                if (!this.myProcessor.process((Object)new OCTypeParameterTypeSymbol(null, null, 0L, this.myName, ocTypeParameterTypeSymbol, null, Collections.emptyList(), null, false))) {
                    return false;
                }
                if (!this.myProcessor.process((Object)new OCDeclaratorSymbol(null, null, 0, null, this.myName, Collections.emptyList(), new OCMagicType(this.myName), OCSymbolKind.TEMPLATE_VALUE_PARAMETER))) {
                    return false;
                }
                return true;
            }
            finally {
                this.myMemoization = myMemoization;
            }
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    public static class UsingAndTypedefSymbolsResolver implements Processor<OCSymbol>
    {
        private final Set<OCSymbol> myProcessedSymbols;
        private final ArrayList<OCSymbol> myAnswer;
        @NotNull
        private OCResolveContext myMemoization;
        private final boolean myProcessTypdefs;
        private final boolean myProcessTypeParameters;
        private final boolean myOnlyTypes;
        private List<OCTypeArgument> myArguments;
        
        public UsingAndTypedefSymbolsResolver(final boolean myProcessTypdefs, final boolean myProcessTypeParameters, final boolean myOnlyTypes, @Nullable final List<OCTypeArgument> myArguments, @NotNull final OCResolveContext myMemoization) {
            if (myMemoization == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$UsingAndTypedefSymbolsResolver", "<init>"));
            }
            this.myProcessedSymbols = (Set<OCSymbol>)OCTypeUtils.newSymbolWithSubstitutionSet();
            this.myAnswer = new ArrayList<OCSymbol>();
            this.myProcessTypdefs = myProcessTypdefs;
            this.myProcessTypeParameters = myProcessTypeParameters;
            this.myOnlyTypes = myOnlyTypes;
            this.myMemoization = myMemoization;
            this.myArguments = myArguments;
        }
        
        public boolean process(final OCSymbol ocSymbol) {
            if (this.myProcessedSymbols.add(ocSymbol)) {
                final OCFile ocFile = (OCFile)this.myMemoization.getFile();
                try {
                    if (ocFile == null) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                if (ocSymbol instanceof OCUsingSymbol) {
                    OCFileSymbols.markImportNeeded(ocFile, ocSymbol);
                    OCSymbolReference ocSymbolReference = ((OCUsingSymbol)ocSymbol).getSymbolReference();
                    if (this.myArguments != null) {
                        ocSymbolReference = ocSymbolReference.applyTypeArguments(this.myArguments);
                    }
                    final OCResolveContext substitute = this.myMemoization.substitute(((OCUsingSymbol)ocSymbol).getSubstitution());
                    ContainerUtil.process((Iterable)ocSymbolReference.resolveTemplateSpecialization(substitute, substitute.doResolveToSymbols(ocSymbolReference, false, this.myOnlyTypes)), (Processor)this);
                }
                else {
                    Label_0308: {
                        Label_0223: {
                            Label_0168: {
                                Label_0145: {
                                    try {
                                        if (!ocSymbol.getKind().isTypedefOrAlias()) {
                                            break Label_0168;
                                        }
                                        final UsingAndTypedefSymbolsResolver usingAndTypedefSymbolsResolver = this;
                                        final boolean b = usingAndTypedefSymbolsResolver.myProcessTypdefs;
                                        if (b) {
                                            break Label_0145;
                                        }
                                        break Label_0168;
                                    }
                                    catch (IllegalArgumentException ex2) {
                                        throw a(ex2);
                                    }
                                    try {
                                        final UsingAndTypedefSymbolsResolver usingAndTypedefSymbolsResolver = this;
                                        final boolean b = usingAndTypedefSymbolsResolver.myProcessTypdefs;
                                        if (b) {
                                            OCFileSymbols.markImportNeeded(ocFile, ocSymbol);
                                            this.processType(ocSymbol, ocSymbol.getType());
                                            return true;
                                        }
                                    }
                                    catch (IllegalArgumentException ex3) {
                                        throw a(ex3);
                                    }
                                }
                                try {
                                    if (!(ocSymbol instanceof OCNamespaceAliasSymbol) || !this.myProcessTypdefs) {
                                        break Label_0223;
                                    }
                                }
                                catch (IllegalArgumentException ex4) {
                                    throw a(ex4);
                                }
                            }
                            OCFileSymbols.markImportNeeded(ocFile, ocSymbol);
                            ContainerUtil.process((List)this.myMemoization.doResolveToSymbols(((OCNamespaceAliasSymbol)ocSymbol).getNamespaceReference(), false, this.myOnlyTypes), (Processor)this);
                            return true;
                            try {
                                if (!(ocSymbol instanceof OCTypeParameterSymbol) || !this.myProcessTypeParameters) {
                                    break Label_0308;
                                }
                            }
                            catch (IllegalArgumentException ex5) {
                                throw a(ex5);
                            }
                        }
                        final OCTypeArgument substitution = this.myMemoization.getSubstitution().getSubstitutionFor((OCTypeParameterSymbol)ocSymbol);
                        Label_0280: {
                            try {
                                if (!(ocSymbol instanceof OCTypeParameterTypeSymbol)) {
                                    break Label_0280;
                                }
                                final OCTypeArgument ocTypeArgument = substitution;
                                final boolean b2 = ocTypeArgument instanceof OCType;
                                if (b2) {
                                    break Label_0280;
                                }
                                break Label_0280;
                            }
                            catch (IllegalArgumentException ex6) {
                                throw a(ex6);
                            }
                            try {
                                final OCTypeArgument ocTypeArgument = substitution;
                                final boolean b2 = ocTypeArgument instanceof OCType;
                                if (b2) {
                                    this.processType(ocSymbol, (OCType)substitution);
                                    return true;
                                }
                            }
                            catch (IllegalArgumentException ex7) {
                                throw a(ex7);
                            }
                        }
                        this.myAnswer.add(ocSymbol);
                        return true;
                    }
                    this.myAnswer.add(ocSymbol);
                }
            }
            return true;
        }
        
        protected void processType(final OCSymbol ocSymbol, final OCType ocType) {
            try {
                if (ocType instanceof OCStructType) {
                    this.myAnswer.addAll(((OCStructType)ocType).getStructs());
                    return;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            if (ocType instanceof OCReferenceType) {
                final OCSymbolReference reference = ((OCReferenceType)ocType).getReference(this.myMemoization);
                final OCResolveContext myMemoization = this.myMemoization;
                try {
                    if (ocSymbol instanceof OCSymbolWithSubstitution) {
                        this.myMemoization = this.myMemoization.substitute(((OCSymbolWithSubstitution)ocSymbol).getSubstitution());
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                final Collection<OCSymbol> resolveTemplateSpecialization = reference.resolveTemplateSpecialization(this.myMemoization, this.myMemoization.doResolveToSymbols(reference, false, this.myOnlyTypes));
                final List<OCTypeArgument> myArguments = this.myArguments;
                Label_0145: {
                    try {
                        if (this.myArguments != null) {
                            break Label_0145;
                        }
                        final OCSymbolReference ocSymbolReference = reference;
                        final OCQualifiedName ocQualifiedName = ocSymbolReference.getQualifiedName();
                        final boolean b = ocQualifiedName instanceof OCQualifiedNameWithArguments;
                        if (b) {
                            break Label_0145;
                        }
                        break Label_0145;
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                    try {
                        final OCSymbolReference ocSymbolReference = reference;
                        final OCQualifiedName ocQualifiedName = ocSymbolReference.getQualifiedName();
                        final boolean b = ocQualifiedName instanceof OCQualifiedNameWithArguments;
                        if (b) {
                            this.myArguments = ((OCQualifiedNameWithArguments)reference.getQualifiedName()).getArguments();
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                }
                ContainerUtil.process((Iterable)resolveTemplateSpecialization, (Processor)this);
                this.myArguments = myArguments;
                this.myMemoization = myMemoization;
            }
            else if (ocType instanceof OCAutoType) {
                final OCExpressionSymbol expressionSymbol = ((OCAutoType)ocType).getExpressionSymbol();
                try {
                    if (expressionSymbol != null) {
                        this.processType(expressionSymbol, expressionSymbol.getResolvedType(this.myMemoization.substitute(((OCAutoType)ocType).getSubstitution())));
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
            }
            else {
                try {
                    if (ocType instanceof OCMagicType) {
                        this.myAnswer.add(new OCTypeParameterTypeSymbol(null, null, 0L, ocSymbol.getName(), null, Collections.emptyList(), null, false));
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
            }
        }
        
        @NotNull
        public List<OCSymbol> getAnswer() {
            ArrayList<OCSymbol> myAnswer = null;
            Label_0059: {
                List<OCSymbol> list = null;
                Label_0024: {
                    try {
                        if (this.myAnswer.size() != 0) {
                            break Label_0059;
                        }
                        list = Collections.emptyList();
                        if (list == null) {
                            break Label_0024;
                        }
                        return (List<OCSymbol>)list;
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        list = Collections.emptyList();
                        if (list == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$UsingAndTypedefSymbolsResolver", "getAnswer"));
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
                return (List<OCSymbol>)list;
                try {
                    this.myAnswer.trimToSize();
                    myAnswer = this.myAnswer;
                    if (myAnswer == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolReference$UsingAndTypedefSymbolsResolver", "getAnswer"));
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            return myAnswer;
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
}
