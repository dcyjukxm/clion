// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.settings;

import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.PersistentStateComponent;

@State(name = "OCExternalDocSettings", storages = { @Storage("other.xml") })
public class OCExternalDocSettings implements PersistentStateComponent<OCExternalDocSettings>
{
    @NotNull
    Browser myBrowser;
    
    public OCExternalDocSettings() {
        this.myBrowser = Browser.WEB_BROWSER;
    }
    
    @NotNull
    public Browser getBrowser() {
        Browser myBrowser;
        try {
            myBrowser = this.myBrowser;
            if (myBrowser == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/settings/OCExternalDocSettings", "getBrowser"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return myBrowser;
    }
    
    public void setBrowser(@NotNull final Browser myBrowser) {
        try {
            if (myBrowser == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "browser", "com/jetbrains/cidr/lang/settings/OCExternalDocSettings", "setBrowser"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        this.myBrowser = myBrowser;
    }
    
    @Nullable
    public OCExternalDocSettings getState() {
        return this;
    }
    
    public void loadState(final OCExternalDocSettings ocExternalDocSettings) {
        XmlSerializerUtil.copyBean((Object)ocExternalDocSettings, (Object)this);
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
    
    public enum Browser
    {
        WEB_BROWSER, 
        DASH, 
        INGREDIENTS;
    }
}
