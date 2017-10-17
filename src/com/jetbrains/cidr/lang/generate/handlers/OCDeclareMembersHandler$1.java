// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers;

import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.settings.OCOption;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.project.Project;
import java.util.List;
import com.jetbrains.cidr.lang.generate.OCMemberChooserObject;
import java.util.Map;
import com.jetbrains.cidr.lang.generate.OCCheckboxMemberChooser;

class OCDeclareMembersHandler$1 extends OCCheckboxMemberChooser {
    final /* synthetic */ OCDeclareActionContext val$actionContext;
    final /* synthetic */ Map val$parentsMap;
    
    @Override
    protected boolean isMemberEnabled(final OCMemberChooserObject ocMemberChooserObject) {
        final OCDeclareActionContext.Target target = this.val$actionContext.getTarget();
        final OCClassSymbol targetSymbol = this.val$actionContext.getTargetSymbol();
        final OCSymbol symbol = ocMemberChooserObject.getSymbol();
        return (targetSymbol == null || (!targetSymbol.equals(symbol) && !targetSymbol.equals(this.val$parentsMap.get(symbol)))) && (target != OCDeclareActionContext.Target.IMPLEMENTATION || !(symbol instanceof OCPropertySymbol)) && (target != OCDeclareActionContext.Target.PRIVATE_CATEGORY || !(symbol instanceof OCInstanceVariableSymbol) || OCCompilerFeatures.supportsIvarsInCategories());
    }
}