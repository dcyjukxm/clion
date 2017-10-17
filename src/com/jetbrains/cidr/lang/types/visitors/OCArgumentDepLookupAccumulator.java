// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceLikeSymbol;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.jetbrains.cidr.lang.psi.OCUnaryExpression;
import com.jetbrains.cidr.lang.util.OCParenthesesUtils;
import com.intellij.util.Processor;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.CommonProcessors;
import java.util.LinkedHashSet;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import com.jetbrains.cidr.lang.symbols.cpp.OCLocalFunctionSymbol;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.jetbrains.cidr.lang.psi.OCExpression;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.types.OCAutoType;
import com.jetbrains.cidr.lang.types.OCTypeParameterType;
import com.jetbrains.cidr.lang.types.OCVoidType;
import com.jetbrains.cidr.lang.types.OCUnknownType;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.symbols.OCTypeParameterSymbol;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.jetbrains.cidr.lang.types.OCRealType;
import com.jetbrains.cidr.lang.types.OCIntType;
import com.jetbrains.cidr.lang.types.OCIdType;
import com.jetbrains.cidr.lang.types.OCTypeArgument;
import com.jetbrains.cidr.lang.types.OCExpansionPackType;
import com.jetbrains.cidr.lang.types.OCVariadicType;
import com.jetbrains.cidr.lang.types.OCCppReferenceType;
import com.jetbrains.cidr.lang.types.OCBlockPointerType;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.types.OCArrayType;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.types.OCMagicType;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.OCEllipsisType;
import java.util.Iterator;
import com.jetbrains.cidr.lang.types.OCType;
import java.util.Collection;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import java.util.HashSet;

public class OCArgumentDepLookupAccumulator implements OCTypeVisitor<Void>
{
    private HashSet<OCSymbolWithQualifiedName> mySymbols;
    
    public OCArgumentDepLookupAccumulator() {
        this.mySymbols = new HashSet<OCSymbolWithQualifiedName>();
    }
    
    public HashSet<OCSymbolWithQualifiedName> getAccumulatedSymbols() {
        return this.mySymbols;
    }
    
    public void visitTypes(final Collection<OCType> collection) {
        final Iterator<OCType> iterator = collection.iterator();
        while (iterator.hasNext()) {
            iterator.next().accept((OCTypeVisitor<Object>)this);
        }
    }
    
    @Nullable
    @Override
    public Void visitEllipsisReferenceType(final OCEllipsisType ocEllipsisType) {
        return null;
    }
    
    @Nullable
    @Override
    public Void visitFunctionType(final OCFunctionType ocFunctionType) {
        ocFunctionType.getReturnType().accept((OCTypeVisitor<Object>)this);
        final Iterator<OCType> iterator = ocFunctionType.getParameterTypes().iterator();
        while (iterator.hasNext()) {
            iterator.next().accept((OCTypeVisitor<Object>)this);
        }
        return null;
    }
    
    @Nullable
    @Override
    public Void visitMagicType(final OCMagicType ocMagicType) {
        return null;
    }
    
    @Nullable
    @Override
    public Void visitObjectType(final OCObjectType ocObjectType) {
        return null;
    }
    
    @Nullable
    @Override
    public Void visitArrayType(final OCArrayType ocArrayType) {
        return ocArrayType.getRefType().accept((OCTypeVisitor<Void>)this);
    }
    
    @Nullable
    @Override
    public Void visitPointerType(final OCPointerType ocPointerType) {
        return ocPointerType.getRefType().accept((OCTypeVisitor<Void>)this);
    }
    
    @Nullable
    @Override
    public Void visitBlockPointerType(final OCBlockPointerType ocBlockPointerType) {
        return ocBlockPointerType.getRefType().accept((OCTypeVisitor<Void>)this);
    }
    
    @Nullable
    @Override
    public Void visitCppReferenceType(final OCCppReferenceType ocCppReferenceType) {
        return ocCppReferenceType.getRefType().accept((OCTypeVisitor<Void>)this);
    }
    
    @Override
    public Void visitVariadicType(final OCVariadicType ocVariadicType) {
        return ocVariadicType.getUnderlyingType().accept((OCTypeVisitor<Void>)this);
    }
    
    @Override
    public Void visitExpansionPackType(final OCExpansionPackType ocExpansionPackType) {
        for (final OCTypeArgument ocTypeArgument : ocExpansionPackType.getExpansions()) {
            if (ocTypeArgument instanceof OCType) {
                ((OCType)ocTypeArgument).accept((OCTypeVisitor<Object>)this);
            }
        }
        return null;
    }
    
    @Nullable
    @Override
    public Void visitIdType(final OCIdType ocIdType) {
        return null;
    }
    
    @Nullable
    @Override
    public Void visitIntType(final OCIntType ocIntType) {
        return null;
    }
    
    @Nullable
    @Override
    public Void visitRealType(final OCRealType ocRealType) {
        return null;
    }
    
    @Nullable
    @Override
    public Void visitReferenceType(final OCReferenceType ocReferenceType) {
        return null;
    }
    
    @Nullable
    @Override
    public Void visitStructType(final OCStructType ocStructType) {
        for (final OCStructSymbol ocStructSymbol2 : ocStructType.getStructs()) {
            this.mySymbols.add(ocStructSymbol2.getParent());
            final OCFile containingOCFile = ocStructSymbol2.getContainingOCFile();
            if (containingOCFile != null) {
                ocStructSymbol2.processBaseClasses(new OCResolveContext((PsiElement)containingOCFile), (ocStructSymbol, p1) -> {
                    if (ocStructSymbol instanceof OCStructSymbol) {
                        this.mySymbols.add(ocStructSymbol.getParent());
                    }
                    return true;
                });
            }
            final Iterator<OCTypeParameterSymbol> iterator2 = (Iterator<OCTypeParameterSymbol>)ocStructSymbol2.getTemplateParameters().iterator();
            while (iterator2.hasNext()) {
                final OCTypeArgument substitution = ocStructSymbol2.getSubstitution().getSubstitutionFor(iterator2.next());
                if (substitution instanceof OCType) {
                    ((OCType)substitution).accept((OCTypeVisitor<Object>)this);
                }
            }
        }
        return null;
    }
    
    @Nullable
    @Override
    public Void visitUnknownType(final OCUnknownType ocUnknownType) {
        return null;
    }
    
    @Nullable
    @Override
    public Void visitVoidType(final OCVoidType ocVoidType) {
        return null;
    }
    
    @Nullable
    @Override
    public Void visitTypeParameterType(final OCTypeParameterType ocTypeParameterType) {
        return null;
    }
    
    @Nullable
    @Override
    public Void visitAutoType(final OCAutoType ocAutoType) {
        return null;
    }
    
    public static Collection<OCSymbol> doArgDepLookup(final Collection<OCSymbol> collection, final List<OCType> list, final List<? extends OCExpression> list2, final OCQualifiedName ocQualifiedName, final OCResolveContext ocResolveContext) {
        for (final OCSymbol ocSymbol2 : collection) {
            if (ocSymbol2 instanceof OCLocalFunctionSymbol) {
                return collection.stream().filter(ocSymbol -> ocSymbol instanceof OCLocalFunctionSymbol).collect((Collector<? super OCSymbol, ?, Collection<OCSymbol>>)Collectors.toList());
            }
            if (ocSymbol2 instanceof OCFunctionSymbol && !((OCFunctionSymbol)ocSymbol2).isFriend() && ((OCFunctionSymbol)ocSymbol2).getResolvedOwner(ocResolveContext, false) instanceof OCStructSymbol) {
                return collection;
            }
            if (!(ocSymbol2 instanceof OCFunctionSymbol) && !(ocSymbol2 instanceof OCStructSymbol)) {
                return collection;
            }
        }
        final OCArgumentDepLookupAccumulator ocArgumentDepLookupAccumulator = new OCArgumentDepLookupAccumulator();
        ocArgumentDepLookupAccumulator.visitTypes(list);
        final CommonProcessors.CollectProcessor collectProcessor = new CommonProcessors.CollectProcessor((Collection)new LinkedHashSet());
        ContainerUtil.process((Iterable)collection, (Processor)collectProcessor);
        for (final OCExpression ocExpression : list2) {
            OCReferenceElement ocReferenceElement = null;
            final OCExpression diveIntoParentheses = OCParenthesesUtils.diveIntoParentheses(ocExpression);
            if (diveIntoParentheses instanceof OCUnaryExpression) {
                final OCUnaryExpression ocUnaryExpression = (OCUnaryExpression)diveIntoParentheses;
                if (ocUnaryExpression.isGetAddress() && ocUnaryExpression.getOperand() instanceof OCReferenceExpression) {
                    ocReferenceElement = ((OCReferenceExpression)ocUnaryExpression.getOperand()).getReferenceElement();
                }
            }
            else if (diveIntoParentheses instanceof OCReferenceExpression) {
                ocReferenceElement = ((OCReferenceExpression)diveIntoParentheses).getReferenceElement();
            }
            else if (diveIntoParentheses instanceof OCReferenceElement) {
                ocReferenceElement = (OCReferenceElement)diveIntoParentheses;
            }
            if (ocReferenceElement != null) {
                for (final OCSymbol ocSymbol3 : ocReferenceElement.resolveToOverloadsSymbols()) {
                    if (ocSymbol3 instanceof OCFunctionSymbol) {
                        ocSymbol3.getResolvedType().accept((OCTypeVisitor<Object>)ocArgumentDepLookupAccumulator);
                    }
                }
            }
        }
        final HashSet set = new HashSet();
        for (final OCSymbolWithQualifiedName ocSymbolWithQualifiedName : ocArgumentDepLookupAccumulator.getAccumulatedSymbols()) {
            if (ocSymbolWithQualifiedName == null) {
                OCStructType.processMembersOfNamespace(((OCFile)ocResolveContext.getFile()).getMembersContainer(false), ocQualifiedName.getName(), false, false, (Processor<OCSymbol>)collectProcessor, ocResolveContext);
            }
            else {
                if (!(ocSymbolWithQualifiedName instanceof OCNamespaceLikeSymbol) || ocQualifiedName.getQualifier() != null) {
                    continue;
                }
                ocSymbolWithQualifiedName.processSameSymbols(ocSymbol -> {
                    if (set.add(ocSymbol) && ocSymbol instanceof OCNamespaceLikeSymbol) {
                        OCStructType.processMembersOfNamespace((OCNamespaceLikeSymbol)ocSymbol, ocQualifiedName.getName(), false, false, (Processor<OCSymbol>)collectProcessor, ocResolveContext);
                    }
                    return true;
                }, ocResolveContext.getFile());
            }
        }
        return (Collection<OCSymbol>)collectProcessor.getResults();
    }
}
