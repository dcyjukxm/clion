// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.settings;

import com.intellij.util.xmlb.XmlSerializerUtil;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.PersistentStateComponent;

@State(name = "OCCodeInsightSettings", storages = { @Storage("other.xml") })
public class OCCodeInsightSettings implements PersistentStateComponent<OCCodeInsightSettings>
{
    public boolean SHOW_IMPORT_POPUP;
    public boolean ALLOW_IMPORT_IN_COMPLETION;
    public OCCodeStyleSettings.HeaderImportStyle HEADER_IMPORT_STYLE;
    
    public OCCodeInsightSettings() {
        this.SHOW_IMPORT_POPUP = true;
        this.ALLOW_IMPORT_IN_COMPLETION = true;
        this.HEADER_IMPORT_STYLE = OCCodeStyleSettings.HeaderImportStyle.PREDEFINE;
    }
    
    public static OCCodeInsightSettings getInstance() {
        return (OCCodeInsightSettings)ServiceManager.getService((Class)OCCodeInsightSettings.class);
    }
    
    public OCCodeInsightSettings getState() {
        return this;
    }
    
    public void loadState(final OCCodeInsightSettings ocCodeInsightSettings) {
        XmlSerializerUtil.copyBean((Object)ocCodeInsightSettings, (Object)this);
    }
}
