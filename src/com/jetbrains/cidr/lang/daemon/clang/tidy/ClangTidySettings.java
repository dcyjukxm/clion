// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon.clang.tidy;

import com.intellij.util.xmlb.XmlSerializerUtil;
import com.intellij.openapi.components.ServiceManager;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.PersistentStateComponent;

@State(name = "ClangTidySettings", storages = { @Storage("other.xml") })
public class ClangTidySettings implements PersistentStateComponent<ClangTidySettings>
{
    private boolean myUseExternalClangTidy;
    @Nullable
    private String myExternalClangTidyPath;
    
    public ClangTidySettings() {
        this.myUseExternalClangTidy = false;
        this.myExternalClangTidyPath = "";
    }
    
    public static ClangTidySettings getInstance() {
        return (ClangTidySettings)ServiceManager.getService((Class)ClangTidySettings.class);
    }
    
    public boolean getUseExternalClangTidy() {
        return this.myUseExternalClangTidy;
    }
    
    public void setUseExternalClangTidy(final boolean myUseExternalClangTidy) {
        this.myUseExternalClangTidy = myUseExternalClangTidy;
    }
    
    @Nullable
    public String getExternalClangTidyPath() {
        return this.myExternalClangTidyPath;
    }
    
    public void setExternalClangTidyPath(@Nullable final String myExternalClangTidyPath) {
        this.myExternalClangTidyPath = myExternalClangTidyPath;
    }
    
    @Nullable
    public ClangTidySettings getState() {
        return this;
    }
    
    public void loadState(final ClangTidySettings clangTidySettings) {
        XmlSerializerUtil.copyBean((Object)clangTidySettings, (Object)this);
    }
}
