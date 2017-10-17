// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.ide;

import java.util.Arrays;
import icons.PlatformImplIcons;
import java.util.List;
import javax.swing.Icon;
import com.intellij.openapi.util.Pair;
import java.util.Map;
import com.intellij.ide.customize.PluginGroups;

class CidrCustomizeIDEWizardStepsProvider$1 extends PluginGroups {
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
}