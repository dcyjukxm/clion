// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.hierarchy.call;

import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.psi.OCElement;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.containers.HashMap;
import com.intellij.ide.hierarchy.HierarchyNodeDescriptor;
import java.util.Map;

public class OCCallHierarchyNodeAggregator
{
    private final Map<String, OCCallHierarchyNodeDescriptor> myMap;
    private final HierarchyNodeDescriptor myParentDescriptor;
    
    public OCCallHierarchyNodeAggregator(final OCCallHierarchyNodeDescriptor myParentDescriptor) {
        this.myParentDescriptor = myParentDescriptor;
        this.myMap = (Map<String, OCCallHierarchyNodeDescriptor>)new HashMap();
    }
    
    @Nullable
    public OCCallHierarchyNodeDescriptor addNodeDescriptor(final OCSymbol ocSymbol, @Nullable final String s, final OCElement ocElement) {
        String string;
        if (ocSymbol instanceof OCFunctionSymbol) {
            final OCQualifiedName resolvedQualifiedName = ((OCFunctionSymbol)ocSymbol).getResolvedQualifiedName();
            string = ((resolvedQualifiedName != null) ? (resolvedQualifiedName.toString() + ((OCFunctionSymbol)ocSymbol).getParametersSignature()) : ocSymbol.getSignature());
        }
        else if (ocSymbol instanceof OCMethodSymbol) {
            final OCMethodSymbol ocMethodSymbol = (OCMethodSymbol)ocSymbol;
            if (ocMethodSymbol.getDefinitionSymbol() == null && ocMethodSymbol.getGeneratedFromProperty() != null) {
                return null;
            }
            string = ((OCSymbolWithParent<T, OCClassSymbol>)ocMethodSymbol).getParent().getName() + "." + ocSymbol.getSignature();
        }
        else {
            string = s;
        }
        OCCallHierarchyNodeDescriptor ocCallHierarchyNodeDescriptor;
        if (this.myMap.containsKey(string)) {
            ocCallHierarchyNodeDescriptor = this.myMap.get(string);
            ocCallHierarchyNodeDescriptor.incrementUsageCount();
        }
        else {
            final Project project = this.myParentDescriptor.getProject();
            if (ocSymbol != null) {
                final OCElement locateDefinition = ocSymbol.locateDefinition();
                ocCallHierarchyNodeDescriptor = new OCCallHierarchyNodeDescriptor(project, this.myParentDescriptor, (PsiElement)((locateDefinition != null) ? locateDefinition : ocElement), false, true);
            }
            else {
                ocCallHierarchyNodeDescriptor = new OCCallHierarchyNodeDescriptor(project, this.myParentDescriptor, (PsiElement)ocElement, false, true);
            }
            ocCallHierarchyNodeDescriptor.addReference(ocElement.getReference());
            this.myMap.put(string, ocCallHierarchyNodeDescriptor);
        }
        return ocCallHierarchyNodeDescriptor;
    }
    
    public Object[] getChildren() {
        return this.myMap.values().toArray();
    }
}
