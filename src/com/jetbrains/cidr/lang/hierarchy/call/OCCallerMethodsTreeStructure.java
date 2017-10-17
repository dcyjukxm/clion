// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.hierarchy.call;

import com.jetbrains.cidr.lang.search.OCFunctionReferenceSearch;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.search.OCMethodReferencesSearch;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.intellij.codeInsight.highlighting.ReadWriteAccessDetector;
import com.jetbrains.cidr.lang.search.usages.OCReadWriteAccessDetector;
import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import java.util.List;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.psi.OCProperty;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.search.searches.ReferencesSearch;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbolImpl;
import com.jetbrains.cidr.lang.psi.OCPropertyAttribute;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.intellij.psi.search.SearchScope;
import java.util.HashSet;
import com.intellij.psi.PsiReference;
import com.intellij.util.Processor;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.intellij.ide.hierarchy.HierarchyNodeDescriptor;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.intellij.openapi.project.Project;
import com.intellij.ide.hierarchy.HierarchyTreeStructure;

public final class OCCallerMethodsTreeStructure extends HierarchyTreeStructure
{
    private final String myScopeType;
    
    public OCCallerMethodsTreeStructure(final Project project, final OCCallable ocCallable, final String myScopeType) {
        super(project, new OCCallHierarchyNodeDescriptor(project, null, (PsiElement)ocCallable, true, false));
        this.myScopeType = myScopeType;
    }
    
    @NotNull
    @Override
    protected final Object[] buildChildren(@NotNull final HierarchyNodeDescriptor hierarchyNodeDescriptor) {
        try {
            if (hierarchyNodeDescriptor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "descriptor", "com/jetbrains/cidr/lang/hierarchy/call/OCCallerMethodsTreeStructure", "buildChildren"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCCallHierarchyNodeDescriptor ocCallHierarchyNodeDescriptor = (OCCallHierarchyNodeDescriptor)hierarchyNodeDescriptor;
        final OCCallHierarchyNodeAggregator ocCallHierarchyNodeAggregator = new OCCallHierarchyNodeAggregator(ocCallHierarchyNodeDescriptor);
        final OCSymbol symbol = ocCallHierarchyNodeDescriptor.getEnclosingElement().getSymbol();
        final SearchScope searchScope = this.getSearchScope(this.myScopeType, (PsiElement)((OCCallHierarchyNodeDescriptor)this.getBaseDescriptor()).getTargetElement().getContainingFile());
        Object[] children = null;
        Label_0225: {
            if (symbol != null) {
                final PsiElement locateDefinition = symbol.locateDefinition();
                class 1Consumer implements Processor<PsiReference>
                {
                    private final HashSet<PsiElement> myProcessed;
                    
                    1Consumer() {
                        this.myProcessed = new HashSet<PsiElement>();
                    }
                    
                    public boolean process(final PsiReference psiReference) {
                        final PsiElement element = psiReference.getElement();
                        String messageSelector = null;
                        if (element instanceof OCElement && this.myProcessed.add(element)) {
                            if (element instanceof OCSendMessageExpression) {
                                messageSelector = ((OCSendMessageExpression)element).getMessageSelector();
                            }
                            else if (element instanceof OCPropertyAttribute) {
                                final OCProperty parentProperty = ((OCPropertyAttribute)element).getParentProperty();
                                final String name = ((OCPropertyAttribute)element).getName();
                                if (name == null) {
                                    return true;
                                }
                                final OCPropertySymbol.PropertyAttribute attribute = OCPropertySymbolImpl.parseAttribute(name);
                                final boolean b = attribute == OCPropertySymbol.PropertyAttribute.GETTER;
                                final boolean b2 = attribute == OCPropertySymbol.PropertyAttribute.SETTER;
                                if (parentProperty == null) {
                                    return true;
                                }
                                final OCDeclaration declaration = parentProperty.getDeclaration();
                                if (declaration == null) {
                                    return true;
                                }
                                final List<OCDeclarator> declarators = declaration.getDeclarators();
                                if (declarators.size() != 1) {
                                    return true;
                                }
                                ReferencesSearch.search(new ReferencesSearch.SearchParameters((PsiElement)declarators.get(0), searchScope, false)).forEach(psiReference -> {
                                    final PsiElement element = psiReference.getElement();
                                    if (element instanceof OCReferenceElement || element instanceof OCQualifiedExpression) {
                                        final ReadWriteAccessDetector.Access expressionAccess = new OCReadWriteAccessDetector().getExpressionAccess(element);
                                        if ((expressionAccess == ReadWriteAccessDetector.Access.Read && b) || (expressionAccess == ReadWriteAccessDetector.Access.Write && b2) || (expressionAccess == ReadWriteAccessDetector.Access.ReadWrite && (b || b2))) {
                                            return this.process(psiReference);
                                        }
                                    }
                                    return true;
                                });
                                return true;
                            }
                            final OCCallable ocCallable = (OCCallable)PsiTreeUtil.getContextOfType(element, new Class[] { OCCallable.class });
                            if (ocCallable != null) {
                                final OCSymbol symbol = ocCallable.getSymbol();
                                if (symbol != null) {
                                    ocCallHierarchyNodeAggregator.addNodeDescriptor(symbol, messageSelector, (OCElement)element);
                                }
                            }
                        }
                        return true;
                    }
                }
                Label_0178: {
                    try {
                        if (locateDefinition == null) {
                            break Label_0225;
                        }
                        if (!(symbol instanceof OCMethodSymbol)) {
                            break Label_0178;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    new OCMethodReferencesSearch(true).execute(new ReferencesSearch.SearchParameters(locateDefinition, searchScope, false), (Processor<PsiReference>)new 1Consumer());
                    break Label_0225;
                }
                if (symbol instanceof OCFunctionSymbol) {
                    new OCFunctionReferenceSearch(true).execute(new ReferencesSearch.SearchParameters(locateDefinition, searchScope, false), (Processor<PsiReference>)new 1Consumer(ocCallHierarchyNodeAggregator));
                }
            }
            try {
                children = ocCallHierarchyNodeAggregator.getChildren();
                if (children == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/hierarchy/call/OCCallerMethodsTreeStructure", "buildChildren"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return children;
    }
    
    @Override
    public boolean isAlwaysShowPlus() {
        return true;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
