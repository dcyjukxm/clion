// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.ide;

import com.intellij.ide.customize.PluginGroups;
import com.intellij.openapi.util.SystemInfo;
import java.util.Map;
import com.intellij.ide.customize.AbstractCustomizeWizardStep;
import java.util.List;
import com.intellij.ide.customize.CustomizeIDEWizardDialog;
import com.jetbrains.cidr.ide.CidrCustomizeIDEWizardStepsProvider;

public class CLionCustomizeIDEWizardStepsProvider extends CidrCustomizeIDEWizardStepsProvider
{
    @Override
    protected void customSteps(final CustomizeIDEWizardDialog customizeIDEWizardDialog, final List<AbstractCustomizeWizardStep> list) {
        list.add(new CPPToolchainsWizardStep(customizeIDEWizardDialog));
    }
    
    @Override
    protected void addFeaturedLangAndToolsPlugins(final Map<String, String> map) {
        super.addFeaturedLangAndToolsPlugins(map);
        if (!SystemInfo.isWindows) {
            map.put("Swift", "Custom Languages:Swift language support:com.intellij.clion-swift");
        }
        PluginGroups.addGoPlugin(map);
    }
}
