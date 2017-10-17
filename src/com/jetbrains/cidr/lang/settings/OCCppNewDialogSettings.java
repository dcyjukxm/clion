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

@State(name = "CppNewDialogSettings", storages = { @Storage("other.xml") })
public class OCCppNewDialogSettings implements PersistentStateComponent<OCCppNewDialogSettings>
{
    public boolean addNewFilesToProject;
    
    public OCCppNewDialogSettings() {
        this.addNewFilesToProject = true;
    }
    
    public static OCCppNewDialogSettings getInstance() {
        return (OCCppNewDialogSettings)ServiceManager.getService((Class)OCCppNewDialogSettings.class);
    }
    
    @Nullable
    public OCCppNewDialogSettings getState() {
        return this;
    }
    
    public void loadState(final OCCppNewDialogSettings ocCppNewDialogSettings) {
        XmlSerializerUtil.copyBean((Object)ocCppNewDialogSettings, (Object)this);
    }
    
    public boolean shouldAddNewFilesToTheProject() {
        return this.addNewFilesToProject;
    }
    
    public void setAddNewFilesToTheProject(final boolean addNewFilesToProject) {
        this.addNewFilesToProject = addNewFilesToProject;
    }
}
