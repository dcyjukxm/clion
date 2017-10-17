// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.ui;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.jetbrains.cidr.lang.workspace.OCResolveConfiguration;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.actionSystem.AnAction;

public static class ConfigurationItem extends AnAction
{
    @NotNull
    private final PsiFile myFile;
    @NotNull
    private final OCResolveConfiguration myConfig;
    
    public ConfigurationItem(@NotNull final PsiFile myFile, @NotNull final OCResolveConfiguration myConfig) {
        if (myFile == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/ui/OCResolveContextPanel$ConfigurationItem", "<init>"));
        }
        if (myConfig == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "config", "com/jetbrains/cidr/lang/ui/OCResolveContextPanel$ConfigurationItem", "<init>"));
        }
        super(myConfig.getDisplayName(false));
        this.myFile = myFile;
        this.myConfig = myConfig;
    }
    
    public void actionPerformed(final AnActionEvent anActionEvent) {
        OCResolveContextPanel.access$1100(this.myFile, this.myConfig);
    }
    
    @NotNull
    public OCResolveConfiguration getConfig() {
        OCResolveConfiguration myConfig;
        try {
            myConfig = this.myConfig;
            if (myConfig == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/ui/OCResolveContextPanel$ConfigurationItem", "getConfig"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myConfig;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
