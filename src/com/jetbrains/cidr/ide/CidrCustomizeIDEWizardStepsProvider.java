// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.ide;

import com.intellij.ide.customize.CustomizeUIThemeStepPanel;
import com.intellij.ide.customize.CustomizeDesktopEntryStep;
import com.intellij.ide.customize.CustomizeFeaturedPluginsStepPanel;
import com.intellij.ide.customize.CustomizePluginsStepPanel;
import java.util.Arrays;
import icons.PlatformImplIcons;
import javax.swing.Icon;
import com.intellij.openapi.util.Pair;
import java.util.Map;
import com.intellij.ide.customize.PluginGroups;
import com.intellij.ide.customize.AbstractCustomizeWizardStep;
import java.util.List;
import com.intellij.ide.customize.CustomizeIDEWizardDialog;
import com.intellij.ide.customize.CustomizeIDEWizardStepsProvider;

public class CidrCustomizeIDEWizardStepsProvider implements CustomizeIDEWizardStepsProvider
{
    @Override
    public void initSteps(final CustomizeIDEWizardDialog customizeIDEWizardDialog, final List<AbstractCustomizeWizardStep> list) {
        this.themeStep(list);
        this.customSteps(customizeIDEWizardDialog, list);
        final PluginGroups pluginGroups = new PluginGroups() {
            @Override
            protected void initGroups(final Map<String, Pair<Icon, List<String>>> map, final Map<String, String> map2) {
                PluginGroups.addVcsGroup(map);
                map.put("Web Development", (Pair<Icon, List<String>>)Pair.create((Object)PlatformImplIcons.WebDevelopment, (Object)Arrays.asList("HTML:HtmlTools", "com.intellij.css", "JavaScript:JavaScript,JSIntentionPowerPack", "com.jetbrains.restClient")));
                map.put("Other Tools", (Pair<Icon, List<String>>)Pair.create((Object)PlatformImplIcons.OtherTools, (Object)CidrCustomizeIDEWizardStepsProvider.this.getBundledTools()));
                PluginGroups.addVimPlugin(map2);
                PluginGroups.addLuaPlugin(map2);
                PluginGroups.addMarkdownPlugin(map2);
                CidrCustomizeIDEWizardStepsProvider.this.addFeaturedLangAndToolsPlugins(map2);
                PluginGroups.addConfigurationServerPlugin(map2);
                PluginGroups.addTeamCityPlugin(map2);
            }
        };
        list.add(new CustomizePluginsStepPanel(pluginGroups));
        list.add(new CustomizeFeaturedPluginsStepPanel(pluginGroups));
        if (CustomizeDesktopEntryStep.isAvailable()) {
            list.add(new CustomizeDesktopEntryStep("/UbuntuDesktopEntry.png"));
        }
    }
    
    protected void themeStep(final List<AbstractCustomizeWizardStep> list) {
        list.add(new CustomizeUIThemeStepPanel());
    }
    
    protected void customSteps(final CustomizeIDEWizardDialog customizeIDEWizardDialog, final List<AbstractCustomizeWizardStep> list) {
    }
    
    protected List<String> getBundledTools() {
        return Arrays.asList("Task Management:com.intellij.tasks", "org.jetbrains.plugins.terminal", "XSLT and XPath:XPathView");
    }
    
    protected void addFeaturedLangAndToolsPlugins(final Map<String, String> map) {
    }
}
