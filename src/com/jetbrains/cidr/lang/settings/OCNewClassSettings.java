// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.settings;

import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.PersistentStateComponent;

@State(name = "OCNewClassSettings", storages = { @Storage("other.xml") })
public class OCNewClassSettings implements PersistentStateComponent<OCNewClassSettings>
{
    public int myLanguageIndex;
    
    public OCNewClassSettings() {
        this.myLanguageIndex = 0;
    }
    
    public static OCNewClassSettings getInstance() {
        return (OCNewClassSettings)ServiceManager.getService((Class)OCNewClassSettings.class);
    }
    
    @Nullable
    public OCNewClassSettings getState() {
        return this;
    }
    
    public void loadState(final OCNewClassSettings ocNewClassSettings) {
        XmlSerializerUtil.copyBean((Object)ocNewClassSettings, (Object)this);
    }
}
