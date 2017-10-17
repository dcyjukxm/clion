// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search.constructors;

import com.jetbrains.cidr.lang.symbols.OCSymbolBase;
import com.jetbrains.cidr.lang.psi.OCCppBaseClause;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCParameterList;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.psi.OCTypeElement;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import java.util.Iterator;
import com.jetbrains.cidr.lang.psi.OCConstructorInitializationList;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.resolve.references.OCOperatorReference;
import com.jetbrains.cidr.lang.resolve.OCExprValueCategory;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.resolve.OCResolveOverloadsUtil;
import com.jetbrains.cidr.lang.resolve.OCArgumentsList;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.OCStruct;
import com.jetbrains.cidr.lang.psi.OCConstructorFieldInitializer;
import com.intellij.psi.PsiFile;
import com.intellij.psi.search.LocalSearchScope;
import com.intellij.psi.search.GlobalSearchScope;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import java.util.Collection;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.search.TextOccurenceProcessor;
import com.intellij.psi.search.SearchScope;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.intellij.psi.PsiElement;
import com.intellij.psi.search.PsiSearchHelper;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;
import com.jetbrains.cidr.lang.psi.OCFunctionDefinition;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.application.ReadAction;
import com.intellij.util.Processor;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.search.searches.ReferencesSearch;
import com.intellij.psi.PsiReference;
import com.intellij.util.QueryExecutor;

public abstract class OCConstructorReferenceSearchBase implements QueryExecutor<PsiReference, ReferencesSearch.SearchParameters>
{
    public boolean execute(@NotNull final ReferencesSearch.SearchParameters searchParameters, @NotNull final Processor<PsiReference> processor) {
        try {
            if (searchParameters == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "queryParameters", "com/jetbrains/cidr/lang/search/constructors/OCConstructorReferenceSearchBase", "execute"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "consumer", "com/jetbrains/cidr/lang/search/constructors/OCConstructorReferenceSearchBase", "execute"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return (boolean)new ReadAction<Boolean>() {
            protected void run(@NotNull final Result<Boolean> result) {
                try {
                    if (result == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/search/constructors/OCConstructorReferenceSearchBase$1", "run"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                result.setResult((Object)OCConstructorReferenceSearchBase.this.a(searchParameters, processor));
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        }.execute().getResultObject();
    }
    
    private Boolean a(final ReferencesSearch.SearchParameters searchParameters, final Processor<PsiReference> processor) {
        final PsiElement elementToSearch = searchParameters.getElementToSearch();
        try {
            if (!(elementToSearch.getParent() instanceof OCFunctionDefinition)) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCFunctionDeclaration ocFunctionDeclaration = (OCFunctionDeclaration)elementToSearch.getParent();
        final OCSymbolWithQualifiedName ocSymbolWithQualifiedName = ocFunctionDeclaration.getSymbol();
        Label_0075: {
            try {
                if (!(ocSymbolWithQualifiedName instanceof OCFunctionSymbol)) {
                    break Label_0075;
                }
                final OCSymbolWithQualifiedName ocSymbolWithQualifiedName2 = ocSymbolWithQualifiedName;
                final OCFunctionSymbol ocFunctionSymbol = (OCFunctionSymbol)ocSymbolWithQualifiedName2;
                final boolean b = ocFunctionSymbol.isCppConstructor();
                if (!b) {
                    break Label_0075;
                }
                break Label_0075;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final OCSymbolWithQualifiedName ocSymbolWithQualifiedName2 = ocSymbolWithQualifiedName;
                final OCFunctionSymbol ocFunctionSymbol = (OCFunctionSymbol)ocSymbolWithQualifiedName2;
                final boolean b = ocFunctionSymbol.isCppConstructor();
                if (!b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        final OCFunctionSymbol ocFunctionSymbol2 = (OCFunctionSymbol)ocSymbolWithQualifiedName.getFirstPredeclaration();
        OCFunctionSymbol ocFunctionSymbol3 = null;
        Label_0113: {
            try {
                if (ocFunctionSymbol2 != null) {
                    ocFunctionSymbol3 = ocFunctionSymbol2;
                    break Label_0113;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            ocFunctionSymbol3 = (OCFunctionSymbol)ocSymbolWithQualifiedName;
        }
        final OCFunctionSymbol ocFunctionSymbol4 = ocFunctionSymbol3;
        try {
            if (!(ocFunctionSymbol4.getParent() instanceof OCStructSymbol)) {
                return true;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        final OCStructSymbol ocStructSymbol = (OCStructSymbol)ocFunctionSymbol4.getParent();
        final OCStructType type = ocStructSymbol.getType();
        final CommonProcessors.CollectProcessor collectProcessor = new CommonProcessors.CollectProcessor();
        collectProcessor.process((Object)ocStructSymbol);
        ocStructSymbol.processConstructors((Processor<? super OCFunctionSymbol>)collectProcessor);
        final SearchScope effectiveSearchScope = searchParameters.getEffectiveSearchScope();
        final PsiSearchHelper instance = PsiSearchHelper.SERVICE.getInstance(elementToSearch.getProject());
        final TextOccurenceProcessor textOccurenceProcessor = (psiElement, n) -> {
            final PsiElement parent = psiElement.getParent();
            Label_0359: {
                try {
                    if (!(parent instanceof OCTypeElement) || !(parent.getContext() instanceof OCDeclaration)) {
                        break Label_0359;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                final OCDeclaration ocDeclaration = (OCDeclaration)parent.getContext();
                try {
                    if (PsiTreeUtil.getContextOfType((PsiElement)ocDeclaration, new Class[] { OCParameterList.class }) != null) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                if (ocDeclaration.getParent() instanceof OCStruct) {
                    final OCStructSymbol ocStructSymbol = ((OCStruct)ocDeclaration.getParent()).getSymbol();
                    for (final OCDeclarator ocDeclarator : ocDeclaration.getDeclarators()) {
                        try {
                            if (!ocDeclarator.getType().resolve(psiElement.getContainingFile()).equals(type, (PsiElement)ocDeclaration)) {
                                return true;
                            }
                        }
                        catch (IllegalArgumentException ex3) {
                            throw a(ex3);
                        }
                        try {
                            if (this.a(ocStructSymbol, ocDeclarator, type, ocFunctionSymbol4, collectProcessor.getResults(), processor, effectiveSearchScope)) {
                                continue;
                            }
                            this.onReferenceFromStruct((OCStruct)ocDeclaration.getParent(), ocDeclarator, ocFunctionSymbol4, processor);
                        }
                        catch (IllegalArgumentException ex4) {
                            throw a(ex4);
                        }
                    }
                    return true;
                }
                for (final OCDeclarator ocDeclarator2 : ocDeclaration.getDeclarators()) {
                    try {
                        if (ocDeclarator2.getInitializer() != null) {
                            continue;
                        }
                    }
                    catch (IllegalArgumentException ex5) {
                        throw a(ex5);
                    }
                    for (final PsiReference psiReference : ocDeclarator2.getReferences()) {
                        Label_0347: {
                            try {
                                if (!psiReference.isReferenceTo((PsiElement)ocFunctionDeclaration)) {
                                    break Label_0347;
                                }
                                final OCConstructorReferenceSearchBase ocConstructorReferenceSearchBase = this;
                                final OCDeclarator ocDeclarator3 = ocDeclarator2;
                                final OCFunctionSymbol ocFunctionSymbol2 = ocFunctionSymbol4;
                                final Processor processor2 = processor;
                                final boolean b = ocConstructorReferenceSearchBase.onReferenceFromDeclarator(ocDeclarator3, ocFunctionSymbol2, (Processor<PsiReference>)processor2);
                                if (!b) {
                                    return false;
                                }
                                break Label_0347;
                            }
                            catch (IllegalArgumentException ex6) {
                                throw a(ex6);
                            }
                            try {
                                final OCConstructorReferenceSearchBase ocConstructorReferenceSearchBase = this;
                                final OCDeclarator ocDeclarator3 = ocDeclarator2;
                                final OCFunctionSymbol ocFunctionSymbol2 = ocFunctionSymbol4;
                                final Processor processor2 = processor;
                                final boolean b = ocConstructorReferenceSearchBase.onReferenceFromDeclarator(ocDeclarator3, ocFunctionSymbol2, (Processor<PsiReference>)processor2);
                                if (!b) {
                                    return false;
                                }
                            }
                            catch (IllegalArgumentException ex7) {
                                throw a(ex7);
                            }
                        }
                    }
                }
                return true;
            }
            if (parent instanceof OCCppBaseClause) {
                final OCStruct ocStruct = (OCStruct)parent.getContext().getContext();
                OCStructSymbol ocStructSymbol2 = null;
                Label_0407: {
                    try {
                        if (ocStruct != null) {
                            ocStructSymbol2 = ocStruct.getSymbol();
                            break Label_0407;
                        }
                    }
                    catch (IllegalArgumentException ex8) {
                        throw a(ex8);
                    }
                    ocStructSymbol2 = null;
                }
                final OCStructSymbol ocStructSymbol3 = ocStructSymbol2;
                Label_0442: {
                    try {
                        if (ocStructSymbol3 == null) {
                            return true;
                        }
                        final OCConstructorReferenceSearchBase ocConstructorReferenceSearchBase2 = this;
                        final OCStructSymbol ocStructSymbol4 = ocStructSymbol3;
                        final OCFunctionDeclaration ocFunctionDeclaration2 = ocFunctionDeclaration;
                        final OCStructType ocStructType2 = type;
                        final OCFunctionSymbol ocFunctionSymbol3 = ocFunctionSymbol4;
                        final CommonProcessors.CollectProcessor collectProcessor2 = collectProcessor;
                        final Collection collection = collectProcessor2.getResults();
                        final Processor processor3 = processor;
                        final SearchScope searchScope2 = effectiveSearchScope;
                        final boolean b2 = ocConstructorReferenceSearchBase2.a(ocStructSymbol4, ocFunctionDeclaration2, ocStructType2, ocFunctionSymbol3, collection, (Processor<PsiReference>)processor3, searchScope2);
                        if (!b2) {
                            break Label_0442;
                        }
                        return true;
                    }
                    catch (IllegalArgumentException ex9) {
                        throw a(ex9);
                    }
                    try {
                        final OCConstructorReferenceSearchBase ocConstructorReferenceSearchBase2 = this;
                        final OCStructSymbol ocStructSymbol4 = ocStructSymbol3;
                        final OCFunctionDeclaration ocFunctionDeclaration2 = ocFunctionDeclaration;
                        final OCStructType ocStructType2 = type;
                        final OCFunctionSymbol ocFunctionSymbol3 = ocFunctionSymbol4;
                        final CommonProcessors.CollectProcessor collectProcessor2 = collectProcessor;
                        final Collection collection = collectProcessor2.getResults();
                        final Processor processor3 = processor;
                        final SearchScope searchScope2 = effectiveSearchScope;
                        final boolean b2 = ocConstructorReferenceSearchBase2.a(ocStructSymbol4, ocFunctionDeclaration2, ocStructType2, ocFunctionSymbol3, collection, (Processor<PsiReference>)processor3, searchScope2);
                        if (!b2) {
                            this.onReferenceFromStruct(ocStruct, ocFunctionDeclaration, ocFunctionSymbol4, processor);
                        }
                    }
                    catch (IllegalArgumentException ex10) {
                        throw a(ex10);
                    }
                }
            }
            return true;
        };
        final String name = ocFunctionDeclaration.getName();
        try {
            if (name == null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        return instance.processElementsWithWord(textOccurenceProcessor, effectiveSearchScope, name, (short)1, true);
    }
    
    private boolean a(@Nullable final OCStructSymbol ocStructSymbol, final OCSymbolDeclarator ocSymbolDeclarator, final OCStructType ocStructType, final OCFunctionSymbol ocFunctionSymbol, final Collection<OCSymbol> collection, final Processor<PsiReference> processor, final SearchScope searchScope) {
        try {
            if (ocStructSymbol == null) {
                return false;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final Ref ref = new Ref((Object)false);
        ocStructSymbol.processConstructors((Processor<? super OCFunctionSymbol>)(ocFunctionSymbol2 -> {
            try {
                ref.set((Object)true);
                if (!ocFunctionSymbol2.getKind().isConstructorOrDestructor() || ocFunctionSymbol2.isPredeclaration()) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            final PsiElement locateDefinition = ((OCSymbolBase<PsiElement>)ocFunctionSymbol2).locateDefinition();
            try {
                if (!(locateDefinition instanceof OCDeclarator)) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            final OCFunctionDefinition ocFunctionDefinition = (OCFunctionDefinition)locateDefinition.getParent();
            final OCConstructorInitializationList constructorInitializationList = ocFunctionDefinition.getConstructorInitializationList();
            boolean b = false;
            Label_0331: {
                Label_0309: {
                    Label_0106: {
                        try {
                            if (constructorInitializationList == null) {
                                break Label_0309;
                            }
                            final SearchScope searchScope2 = searchScope;
                            final Object o = constructorInitializationList;
                            final boolean b2 = a(searchScope2, (PsiElement)o);
                            if (!b2) {
                                return true;
                            }
                            break Label_0106;
                        }
                        catch (IllegalArgumentException ex3) {
                            throw a(ex3);
                        }
                        try {
                            final SearchScope searchScope2 = searchScope;
                            final Object o = constructorInitializationList;
                            final boolean b2 = a(searchScope2, (PsiElement)o);
                            if (!b2) {
                                return true;
                            }
                        }
                        catch (IllegalArgumentException ex4) {
                            throw a(ex4);
                        }
                    }
                    for (final OCConstructorFieldInitializer ocConstructorFieldInitializer : constructorInitializationList.getInitializers()) {
                        final OCReferenceElement referenceElement = ocConstructorFieldInitializer.getReferenceElement();
                        try {
                            if (referenceElement == null) {
                                continue;
                            }
                        }
                        catch (IllegalArgumentException ex5) {
                            throw a(ex5);
                        }
                        try {
                            if (!referenceElement.getReference().isReferenceTo((PsiElement)ocSymbolDeclarator)) {
                                continue;
                            }
                        }
                        catch (IllegalArgumentException ex6) {
                            throw a(ex6);
                        }
                        if (ocSymbolDeclarator instanceof OCFunctionDefinition) {
                            b = true;
                            break;
                        }
                        final OCSymbol resolveToSymbol = referenceElement.resolveToSymbol();
                        try {
                            if (resolveToSymbol == null) {
                                continue;
                            }
                        }
                        catch (IllegalArgumentException ex7) {
                            throw a(ex7);
                        }
                        if (ocStructType.equals(resolveToSymbol.getResolvedType(), (PsiElement)ocSymbolDeclarator) && Comparing.equal((Object)OCResolveOverloadsUtil.resolveOverloads(collection, OCArgumentsList.getArgumentList(ocConstructorFieldInitializer.getInitializers()), null, null, null, false, false, false, true, false, new OCResolveContext((PsiElement)referenceElement)), (Object)ocFunctionSymbol)) {
                            b = true;
                            this.onReferenceFromInitializer(ocConstructorFieldInitializer, ocSymbolDeclarator, ocFunctionSymbol, processor);
                            break;
                        }
                    }
                    try {
                        if (b) {
                            return true;
                        }
                        final OCFunctionSymbol ocFunctionSymbol3 = ocFunctionSymbol;
                        final OCResolveContext ocResolveContext2 = ocResolveContext;
                        final int n = ocFunctionSymbol3.getNonInitializedParametersCount(ocResolveContext2);
                        if (n == 0) {
                            break Label_0331;
                        }
                        return true;
                    }
                    catch (IllegalArgumentException ex8) {
                        throw a(ex8);
                    }
                }
                try {
                    final OCFunctionSymbol ocFunctionSymbol3 = ocFunctionSymbol;
                    final OCResolveContext ocResolveContext2 = ocResolveContext;
                    final int n = ocFunctionSymbol3.getNonInitializedParametersCount(ocResolveContext2);
                    if (n == 0) {
                        this.onReferenceFromOtherConstructor(ocFunctionDefinition, ocSymbolDeclarator, ocFunctionSymbol, processor);
                    }
                }
                catch (IllegalArgumentException ex9) {
                    throw a(ex9);
                }
            }
            return true;
        }), true);
        return (boolean)ref.get();
    }
    
    private static boolean a(final SearchScope searchScope, final PsiElement psiElement) {
        final PsiFile containingFile = psiElement.getContainingFile();
        try {
            if (searchScope instanceof GlobalSearchScope) {
                return searchScope.contains(containingFile.getVirtualFile());
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (searchScope instanceof LocalSearchScope) {
                return ((LocalSearchScope)searchScope).containsRange(containingFile, psiElement.getTextRange());
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return false;
    }
    
    protected abstract boolean onReferenceFromInitializer(final OCConstructorFieldInitializer p0, final OCSymbolDeclarator p1, final OCFunctionSymbol p2, final Processor<PsiReference> p3);
    
    protected abstract boolean onReferenceFromOtherConstructor(final OCFunctionDefinition p0, final OCSymbolDeclarator p1, final OCFunctionSymbol p2, final Processor<PsiReference> p3);
    
    protected abstract boolean onReferenceFromStruct(final OCStruct p0, final OCSymbolDeclarator p1, final OCFunctionSymbol p2, final Processor<PsiReference> p3);
    
    protected abstract boolean onReferenceFromDeclarator(final OCDeclarator p0, final OCFunctionSymbol p1, final Processor<PsiReference> p2);
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
