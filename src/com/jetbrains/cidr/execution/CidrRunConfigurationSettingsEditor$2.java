// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution;

import com.intellij.openapi.ui.ComboBox;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.application.ReadAction;

class CidrRunConfigurationSettingsEditor$2 extends ReadAction {
    final /* synthetic */ BuildTargetData val$buildTargetData;
    final /* synthetic */ BuildTargetAndConfigurationData val$buildData;
    
    protected void run(@NotNull final Result result) throws Throwable {
        try {
            if (result == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/execution/CidrRunConfigurationSettingsEditor$2", "run"));
            }
        }
        catch (Throwable t) {
            throw a(t);
        }
        final CidrBuildTarget<BC> target = ((CidrBuildConfigurationHelper<BC, CidrBuildTarget<BC>>)CidrRunConfigurationSettingsEditor.this.myConfigHelper).findTarget(this.val$buildTargetData);
        CidrBuildConfigurationHelper<BC, TARGET> myConfigHelper = null;
        CidrBuildTarget<BC> cidrBuildTarget = null;
        String configurationName = null;
        Label_0089: {
            try {
                myConfigHelper = CidrRunConfigurationSettingsEditor.this.myConfigHelper;
                cidrBuildTarget = target;
                if (this.val$buildData == null) {
                    configurationName = null;
                    break Label_0089;
                }
            }
            catch (Throwable t2) {
                throw a(t2);
            }
            configurationName = this.val$buildData.configurationName;
        }
        final CidrBuildConfiguration configuration = myConfigHelper.findConfiguration((TARGET)cidrBuildTarget, configurationName);
        ComboBox myConfigurationCombo = null;
        CidrBuildConfiguration cidrBuildConfiguration = null;
        Object configurationName2 = null;
        Label_0138: {
            try {
                CidrRunConfigurationSettingsEditor.setSelectedItem(CidrRunConfigurationSettingsEditor.this.myTargetCombo, target, this.val$buildTargetData);
                myConfigurationCombo = CidrRunConfigurationSettingsEditor.this.myConfigurationCombo;
                cidrBuildConfiguration = configuration;
                if (this.val$buildData == null) {
                    configurationName2 = null;
                    break Label_0138;
                }
            }
            catch (Throwable t3) {
                throw a(t3);
            }
            configurationName2 = this.val$buildData.configurationName;
        }
        CidrRunConfigurationSettingsEditor.setSelectedItem(myConfigurationCombo, cidrBuildConfiguration, configurationName2);
    }
    
    private static Throwable a(final Throwable t) {
        return t;
    }
}