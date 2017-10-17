// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.settings;

import com.jetbrains.cidr.lang.OCLanguage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.PersistentStateComponent;

@State(name = "OCCodeFoldingSettings", storages = { @Storage("editor.codeinsight.xml") }, presentableName = PresentableNameGetter.class)
public class OCCodeFoldingSettingsImpl extends OCCodeFoldingSettings implements PersistentStateComponent<OCCodeFoldingSettingsImpl>
{
    public boolean COLLAPSE_IVARS;
    public boolean COLLAPSE_SYNTHESIZES;
    public boolean COLLAPSE_LOCALIZED_STRINGS;
    public boolean COLLAPSE_MULTILINE_COMMENTS;
    public boolean COLLAPSE_BLOCK_EXPRESSIONS;
    public boolean COLLAPSE_TEMPLATE_PARAM_LIST;
    public boolean COLLAPSE_CONDITIONALLY_NOT_COMPILED;
    
    public OCCodeFoldingSettingsImpl() {
        this.COLLAPSE_IVARS = false;
        this.COLLAPSE_SYNTHESIZES = true;
        this.COLLAPSE_LOCALIZED_STRINGS = true;
        this.COLLAPSE_MULTILINE_COMMENTS = false;
        this.COLLAPSE_BLOCK_EXPRESSIONS = false;
        this.COLLAPSE_TEMPLATE_PARAM_LIST = true;
        this.COLLAPSE_CONDITIONALLY_NOT_COMPILED = true;
    }
    
    @Override
    public boolean isCollapseIvars() {
        return this.COLLAPSE_IVARS;
    }
    
    @Override
    public void setCollapseIvars(final boolean collapse_IVARS) {
        this.COLLAPSE_IVARS = collapse_IVARS;
    }
    
    @Override
    public boolean isCollapseSynthesizes() {
        return this.COLLAPSE_SYNTHESIZES;
    }
    
    @Override
    public void setCollapseSynthesizes(final boolean collapse_SYNTHESIZES) {
        this.COLLAPSE_SYNTHESIZES = collapse_SYNTHESIZES;
    }
    
    @Override
    public boolean isCollapseLocalizedStrings() {
        return this.COLLAPSE_LOCALIZED_STRINGS;
    }
    
    @Override
    public void setCollapseLocalizedStrings(final boolean collapse_LOCALIZED_STRINGS) {
        this.COLLAPSE_LOCALIZED_STRINGS = collapse_LOCALIZED_STRINGS;
    }
    
    @Override
    public boolean isCollapseMultilineComments() {
        return this.COLLAPSE_MULTILINE_COMMENTS;
    }
    
    @Override
    public void setCollapseMultilineComments(final boolean collapse_MULTILINE_COMMENTS) {
        this.COLLAPSE_MULTILINE_COMMENTS = collapse_MULTILINE_COMMENTS;
    }
    
    @Override
    public boolean isCollapseBlockExpressions() {
        return this.COLLAPSE_BLOCK_EXPRESSIONS;
    }
    
    @Override
    public void setCollapseBlockExpressions(final boolean collapse_BLOCK_EXPRESSIONS) {
        this.COLLAPSE_BLOCK_EXPRESSIONS = collapse_BLOCK_EXPRESSIONS;
    }
    
    @Override
    public boolean isCollapseTemplateParamList() {
        return this.COLLAPSE_TEMPLATE_PARAM_LIST;
    }
    
    @Override
    public void setCollapseTemplateParamList(final boolean collapse_TEMPLATE_PARAM_LIST) {
        this.COLLAPSE_TEMPLATE_PARAM_LIST = collapse_TEMPLATE_PARAM_LIST;
    }
    
    @Override
    public boolean isCollapseConditionallyNotCompiled() {
        return this.COLLAPSE_CONDITIONALLY_NOT_COMPILED;
    }
    
    @Override
    public void setCollapseConditionallyNotCompiled(final boolean collapse_CONDITIONALLY_NOT_COMPILED) {
        this.COLLAPSE_CONDITIONALLY_NOT_COMPILED = collapse_CONDITIONALLY_NOT_COMPILED;
    }
    
    public OCCodeFoldingSettingsImpl getState() {
        return this;
    }
    
    public void loadState(final OCCodeFoldingSettingsImpl ocCodeFoldingSettingsImpl) {
        XmlSerializerUtil.copyBean((Object)ocCodeFoldingSettingsImpl, (Object)this);
    }
    
    static final class PresentableNameGetter extends State.NameGetter
    {
        public String get() {
            return OCLanguage.getInstance().getDisplayName() + " Code Folding";
        }
    }
}
