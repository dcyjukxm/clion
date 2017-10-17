// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature;

import com.intellij.openapi.project.Project;
import com.intellij.refactoring.changeSignature.ParameterTableModelItemBase;
import com.intellij.refactoring.changeSignature.ParameterTableModelBase;

private static class SelectorColumn extends NameColumn<OCParameterInfo, ParameterTableModelItemBase<OCParameterInfo>>
{
    public SelectorColumn(final Project project) {
        super(project);
        this.setName("Selector part:");
    }
    
    @Override
    public String valueOf(final ParameterTableModelItemBase<OCParameterInfo> parameterTableModelItemBase) {
        return parameterTableModelItemBase.parameter.getSelector();
    }
    
    @Override
    public void setValue(final ParameterTableModelItemBase<OCParameterInfo> parameterTableModelItemBase, final String selector) {
        parameterTableModelItemBase.parameter.setSelector(selector);
    }
}
