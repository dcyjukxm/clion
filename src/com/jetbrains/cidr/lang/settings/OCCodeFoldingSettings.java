// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.settings;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.components.ServiceManager;

public abstract class OCCodeFoldingSettings
{
    @NotNull
    public static OCCodeFoldingSettings getInstance() {
        OCCodeFoldingSettings ocCodeFoldingSettings;
        try {
            ocCodeFoldingSettings = (OCCodeFoldingSettings)ServiceManager.getService((Class)OCCodeFoldingSettings.class);
            if (ocCodeFoldingSettings == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/settings/OCCodeFoldingSettings", "getInstance"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return ocCodeFoldingSettings;
    }
    
    public abstract boolean isCollapseIvars();
    
    public abstract void setCollapseIvars(final boolean p0);
    
    public abstract boolean isCollapseSynthesizes();
    
    public abstract void setCollapseSynthesizes(final boolean p0);
    
    public abstract boolean isCollapseLocalizedStrings();
    
    public abstract void setCollapseLocalizedStrings(final boolean p0);
    
    public abstract boolean isCollapseMultilineComments();
    
    public abstract void setCollapseMultilineComments(final boolean p0);
    
    public abstract boolean isCollapseBlockExpressions();
    
    public abstract void setCollapseBlockExpressions(final boolean p0);
    
    public abstract boolean isCollapseTemplateParamList();
    
    public abstract void setCollapseTemplateParamList(final boolean p0);
    
    public abstract boolean isCollapseConditionallyNotCompiled();
    
    public abstract void setCollapseConditionallyNotCompiled(final boolean p0);
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
