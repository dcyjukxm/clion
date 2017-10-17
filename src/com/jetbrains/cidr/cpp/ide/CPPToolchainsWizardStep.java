// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.ide;

import com.intellij.openapi.options.OptionsBundle;
import com.intellij.CommonBundle;
import com.jetbrains.cidr.cpp.CPPBundle;
import com.intellij.openapi.util.SystemInfo;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.util.Disposer;
import com.jetbrains.cidr.cpp.CPPToolchainsConfigurable;
import com.intellij.ide.customize.CustomizeIDEWizardDialog;
import com.jetbrains.cidr.cpp.CPPToolchains;
import com.jetbrains.cidr.cpp.CPPToolchainsPanel;
import com.intellij.ide.customize.AbstractCustomizeWizardStep;

public class CPPToolchainsWizardStep extends AbstractCustomizeWizardStep
{
    private final CPPToolchainsPanel myPanel;
    private final CPPToolchains myToolchains;
    
    public CPPToolchainsWizardStep(final CustomizeIDEWizardDialog customizeIDEWizardDialog) {
        this.myPanel = new CPPToolchainsPanel((CPPToolchainsConfigurable.EnvironmentChangeListener)null);
        this.myToolchains = new CPPToolchains();
        Disposer.register(customizeIDEWizardDialog.getDisposable(), (Disposable)this.myPanel);
        this.setLayout(new BorderLayout());
        this.add(this.myPanel, "Center");
        this.myPanel.reset(this.myToolchains);
        this.myPanel.autoRecheckWithWizard(customizeIDEWizardDialog.getWindow());
    }
    
    @Override
    protected String getTitle() {
        return CPPToolchainsConfigurable.DISPLAY_NAME;
    }
    
    @Override
    protected String getHTMLHeader() {
        return "<html><body><h2>Configure " + (SystemInfo.isWindows ? "Environment, " : "") + CPPBundle.message("cmake", new Object[0]) + " and debugger</h2>&nbsp;</body></html>";
    }
    
    @Override
    protected String getHTMLFooter() {
        return CPPToolchainsConfigurable.DISPLAY_NAME + " can be changed later in " + CommonBundle.settingsTitle() + " | " + OptionsBundle.message("configurable.group.build.settings.display.name", new Object[0]) + " | " + CPPToolchainsConfigurable.DISPLAY_NAME;
    }
    
    @Override
    public boolean beforeOkAction() {
        this.myPanel.apply(this.myToolchains);
        CPPToolchains.InitialAppState = this.myToolchains.getState();
        return true;
    }
}
