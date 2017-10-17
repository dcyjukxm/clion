// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.hierarchy.call;

import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.intellij.codeInsight.highlighting.ReadWriteAccessDetector;
import java.util.HashSet;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.search.OCFunctionInheritorsSearch;
import com.jetbrains.cidr.lang.search.OCFunctionAncestorsQuery;
import com.jetbrains.cidr.lang.types.OCObjectTypeContext;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.jetbrains.cidr.lang.search.OCMemberInheritorsSearch;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.search.usages.OCReadWriteAccessDetector;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.search.OCFunctionReferenceSearch;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.psi.OCCppNewExpression;
import com.jetbrains.cidr.lang.psi.OCCallExpression;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import java.util.Iterator;
import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.psi.PsiElement;
import com.intellij.ide.hierarchy.HierarchyNodeDescriptor;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.intellij.openapi.project.Project;
import com.intellij.ide.hierarchy.HierarchyTreeStructure;

public final class OCCalleeMethodsTreeStructure extends HierarchyTreeStructure
{
    private final String myScopeType;
    
    public OCCalleeMethodsTreeStructure(final Project project, final OCCallable ocCallable, final String myScopeType) {
        super(project, new OCCallHierarchyNodeDescriptor(project, null, (PsiElement)a(ocCallable), true, false));
        this.myScopeType = myScopeType;
    }
    
    private static OCCallable a(OCCallable ocCallable) {
        OCSymbol<PsiElement> symbol = ocCallable.getSymbol();
        try {
            if (symbol == null || !symbol.isPredeclaration()) {
                return ocCallable;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCSymbol<PsiElement> definitionSymbol = symbol.getDefinitionSymbol();
        if (definitionSymbol != null) {
            symbol = definitionSymbol;
        }
        final PsiElement locateDefinition = symbol.locateDefinition();
        if (locateDefinition instanceof OCCallable) {
            ocCallable = (OCCallable<OCSymbol<PsiElement>>)locateDefinition;
        }
        try {
            if (locateDefinition == null || !(locateDefinition.getParent() instanceof OCCallable)) {
                return ocCallable;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        ocCallable = (OCCallable<OCSymbol<PsiElement>>)locateDefinition.getParent();
        return ocCallable;
    }
    
    @NotNull
    @Override
    protected final Object[] buildChildren(@NotNull final HierarchyNodeDescriptor hierarchyNodeDescriptor) {
        try {
            if (hierarchyNodeDescriptor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "descriptor", "com/jetbrains/cidr/lang/hierarchy/call/OCCalleeMethodsTreeStructure", "buildChildren"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCCallHierarchyNodeDescriptor ocCallHierarchyNodeDescriptor = (OCCallHierarchyNodeDescriptor)hierarchyNodeDescriptor;
        if (ocCallHierarchyNodeDescriptor.getPossibleResponders().size() > 1) {
            final ArrayList<OCCallHierarchyNodeDescriptor> list = new ArrayList<OCCallHierarchyNodeDescriptor>();
            for (OCSymbol<PsiElement> ocSymbol : ocCallHierarchyNodeDescriptor.getPossibleResponders()) {
                if (ocSymbol.isPredeclaration()) {
                    final OCSymbol<PsiElement> definitionSymbol = ocSymbol.getDefinitionSymbol();
                    if (definitionSymbol != null) {
                        ocSymbol = definitionSymbol;
                    }
                }
                final PsiElement locateDefinition = ocSymbol.locateDefinition();
                try {
                    if (locateDefinition == null) {
                        continue;
                    }
                    list.add(new OCCallHierarchyNodeDescriptor(this.myProject, hierarchyNodeDescriptor, locateDefinition, false, false));
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            Object[] array;
            try {
                array = list.toArray();
                if (array == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/hierarchy/call/OCCalleeMethodsTreeStructure", "buildChildren"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            return array;
        }
        final ChildrenCalculator childrenCalculator = new ChildrenCalculator(ocCallHierarchyNodeDescriptor);
        final OCCallable enclosingElement = ocCallHierarchyNodeDescriptor.getEnclosingElement();
        if (enclosingElement != null) {
            final OCBlockStatement body = a(enclosingElement).getBody();
            try {
                if (body != null) {
                    childrenCalculator.a((PsiElement)body);
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        Object[] children;
        try {
            children = childrenCalculator.getChildren();
            if (children == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/hierarchy/call/OCCalleeMethodsTreeStructure", "buildChildren"));
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        return children;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    private class ChildrenCalculator extends OCCallHierarchyNodeAggregator
    {
        private final boolean myParentIsConstructorDestructor;
        
        private ChildrenCalculator(final OCCallHierarchyNodeDescriptor ocCallHierarchyNodeDescriptor) {
            super(ocCallHierarchyNodeDescriptor);
            final OCCallable enclosingElement = ocCallHierarchyNodeDescriptor.getEnclosingElement();
            if (enclosingElement != null) {
                final OCFunctionSymbol symbol = enclosingElement.getSymbol();
                if (symbol instanceof OCFunctionSymbol) {
                    final OCFunctionSymbol ocFunctionSymbol = symbol;
                    this.myParentIsConstructorDestructor = (ocFunctionSymbol.isCppConstructor() || ocFunctionSymbol.isCppDestructor());
                }
                else {
                    this.myParentIsConstructorDestructor = false;
                }
            }
            else {
                this.myParentIsConstructorDestructor = false;
            }
        }
        
        private void a(final PsiElement psiElement) {
            for (final PsiElement psiElement2 : psiElement.getChildren()) {
                this.a(psiElement2);
                if (psiElement2 instanceof OCReferenceElement) {
                    final PsiElement parent = psiElement2.getParent().getParent();
                    if (parent instanceof OCCallExpression || parent instanceof OCCppNewExpression) {
                        final OCSymbol resolveToSymbol = ((OCReferenceElement)psiElement2).resolveToSymbol();
                        if (resolveToSymbol instanceof OCFunctionSymbol) {
                            this.a((OCFunctionSymbol)resolveToSymbol, (OCElement)psiElement2, !this.myParentIsConstructorDestructor && ((OCReferenceElement)psiElement2).getNamespaceQualifier() == null);
                        }
                    }
                }
                else if (psiElement2 instanceof OCQualifiedExpression) {
                    final OCSymbol resolveToSymbol2 = ((OCQualifiedExpression)psiElement2).resolveToSymbol();
                    if (resolveToSymbol2 instanceof OCMethodSymbol) {
                        this.a((OCMethodSymbol)resolveToSymbol2, (OCElement)psiElement2);
                    }
                    else if (psiElement2.getParent() instanceof OCCallExpression && resolveToSymbol2 instanceof OCFunctionSymbol) {
                        this.a((OCFunctionSymbol)resolveToSymbol2, (OCElement)psiElement2, !this.myParentIsConstructorDestructor && OCFunctionReferenceSearch.isCallViaReference(((OCQualifiedExpression)psiElement2).getQualifier()));
                    }
                    else if (resolveToSymbol2 instanceof OCPropertySymbol) {
                        final OCPropertySymbol ocPropertySymbol = (OCPropertySymbol)resolveToSymbol2;
                        ((OCSymbolWithParent<T, OCClassSymbol>)ocPropertySymbol).getParent().processMembers(OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMethodSymbol>)(ocMethodSymbol -> {
                            if (ocMethodSymbol.getGeneratedFromProperty() == ocPropertySymbol && ((access == ReadWriteAccessDetector.Access.Read && ocMethodSymbol.isGetter()) || (access == ReadWriteAccessDetector.Access.Write && ocMethodSymbol.isSetter()) || access == ReadWriteAccessDetector.Access.ReadWrite)) {
                                this.a(ocMethodSymbol, (OCElement)psiElement2);
                            }
                            return true;
                        }));
                    }
                }
                else if (psiElement2 instanceof OCSendMessageExpression) {
                    final OCSendMessageExpression ocSendMessageExpression = (OCSendMessageExpression)psiElement2;
                    final OCSendMessageExpression.ProbableResponders probableResponders = ocSendMessageExpression.getProbableResponders();
                    final OCObjectTypeContext receiverContext = ocSendMessageExpression.getReceiverContext();
                    if (receiverContext != null) {
                        OCMethodSymbol knownResponder = probableResponders.getKnownResponder();
                        if (knownResponder != null && !knownResponder.isDefinition()) {
                            final OCMethodSymbol definitionSymbol = knownResponder.getDefinitionSymbol();
                            if (definitionSymbol instanceof OCMethodSymbol) {
                                knownResponder = definitionSymbol;
                            }
                        }
                        final String messageSelector = ocSendMessageExpression.getMessageSelector();
                        final OCCallHierarchyNodeDescriptor addNodeDescriptor = this.addNodeDescriptor(knownResponder, messageSelector, (OCElement)psiElement2);
                        if (addNodeDescriptor != null) {
                            final OCMemberInheritorsSearch.SearchParameters<OCMemberSymbol> parameters = OCMemberInheritorsSearch.getParameters(messageSelector, receiverContext.getType().getClassSymbol(), OCCalleeMethodsTreeStructure.this.myProject, (Class<? extends OCMemberSymbol>)OCMethodSymbol.class, receiverContext.getStaticMode());
                            this.a(parameters);
                            OCMemberInheritorsSearch.search(parameters).forEach(ocMethodSymbol -> {
                                if (receiverContext.fitsStaticness(ocMethodSymbol) && ocMethodSymbol.isDefinition()) {
                                    addNodeDescriptor.addPossibleResponder(ocMethodSymbol);
                                }
                                return true;
                            });
                        }
                    }
                }
            }
        }
        
        private void a(OCMethodSymbol ocMethodSymbol, final OCElement ocElement) {
            if (!ocMethodSymbol.isDefinition()) {
                final OCMethodSymbol definitionSymbol = ocMethodSymbol.getDefinitionSymbol();
                if (definitionSymbol instanceof OCMethodSymbol) {
                    ocMethodSymbol = definitionSymbol;
                }
            }
            final OCCallHierarchyNodeDescriptor addNodeDescriptor = this.addNodeDescriptor(ocMethodSymbol, null, ocElement);
            if (addNodeDescriptor != null) {
                final OCMemberInheritorsSearch.SearchParameters<OCMethodSymbol> parameters = OCMemberInheritorsSearch.getParameters(ocMethodSymbol);
                this.a(parameters);
                OCMemberInheritorsSearch.search((OCMemberInheritorsSearch.SearchParameters<OCMemberSymbol>)parameters).forEach(ocMemberSymbol -> {
                    if (ocMemberSymbol.isDefinition()) {
                        addNodeDescriptor.addPossibleResponder(ocMemberSymbol);
                    }
                    return true;
                });
            }
        }
        
        private void a(OCFunctionSymbol ocFunctionSymbol, final OCElement ocElement, final boolean b) {
            if (ocFunctionSymbol.isPredeclaration()) {
                final OCSymbol definitionSymbol = ocFunctionSymbol.getDefinitionSymbol();
                if (definitionSymbol instanceof OCFunctionSymbol) {
                    ocFunctionSymbol = (OCFunctionSymbol)definitionSymbol;
                }
            }
            final OCCallHierarchyNodeDescriptor addNodeDescriptor = this.addNodeDescriptor(ocFunctionSymbol, null, ocElement);
            if (addNodeDescriptor != null && b && OCFunctionAncestorsQuery.findFirstVirtual(ocFunctionSymbol, true) != null) {
                final OCFunctionInheritorsSearch.SearchParameters parameters = OCFunctionInheritorsSearch.getParameters(ocFunctionSymbol, ocElement.getContainingOCFile(), true);
                final HashSet set = new HashSet();
                parameters.setImplementationsThenPredeclarations(true);
                parameters.setIncludeSameSymbols(true);
                OCFunctionInheritorsSearch.search(parameters).forEach(ocFunctionSymbol -> {
                    if (set.add(ocFunctionSymbol.getResolvedQualifiedName())) {
                        addNodeDescriptor.addPossibleResponder(ocFunctionSymbol);
                    }
                    return true;
                });
            }
        }
        
        private void a(final OCMemberInheritorsSearch.SearchParameters searchParameters) {
            searchParameters.setIncludeSelfImplementation(true);
            searchParameters.setInterfacesThenImplementations(false);
            searchParameters.setIncludeFromID(true);
            searchParameters.setInheritors(true);
            searchParameters.setAncestors(false);
        }
    }
}
