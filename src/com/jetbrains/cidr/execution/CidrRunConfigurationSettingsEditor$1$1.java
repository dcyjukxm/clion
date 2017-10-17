// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.application.ReadAction;

class CidrRunConfigurationSettingsEditor$1$1 extends ReadAction {
    final /* synthetic */ CidrBuildTarget val$target;
    
    protected void run(@NotNull final Result result) throws Throwable {
        try {
            if (result == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/execution/CidrRunConfigurationSettingsEditor$1$1", "run"));
            }
        }
        catch (Throwable t) {
            throw a(t);
        }
        ItemListener.this.this$0.myConfigurationCombo.setModel((ComboBoxModel)new DefaultComboBoxModel(((CidrBuildConfigurationHelper<CidrBuildConfiguration, TARGET>)ItemListener.this.this$0.myConfigHelper).getConfigurations((TARGET)this.val$target).toArray()));
        ItemListener.this.this$0.myConfigurationCombo.setSelectedItem((Object)null);
        ItemListener.this.this$0.myConfigurationCombo.setSelectedItem((Object)((CidrBuildConfigurationHelper<BC, TARGET>)ItemListener.this.this$0.myConfigHelper).getDefaultConfiguration((TARGET)this.val$target));
    }
    
    private static Throwable a(final Throwable t) {
        return t;
    }
}