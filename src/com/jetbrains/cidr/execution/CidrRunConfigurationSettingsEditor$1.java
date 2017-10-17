// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.application.ReadAction;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

class CidrRunConfigurationSettingsEditor$1 implements ItemListener {
    @Override
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getStateChange() == 2 && CidrRunConfigurationSettingsEditor.this.myTargetCombo.getItemCount() > 0) {
            return;
        }
        final CidrBuildTarget cidrBuildTarget = (itemEvent.getStateChange() == 1) ? ((CidrBuildTarget)CidrRunConfigurationSettingsEditor.getItem(itemEvent)) : null;
        new ReadAction() {
            protected void run(@NotNull final Result result) throws Throwable {
                try {
                    if (result == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/execution/CidrRunConfigurationSettingsEditor$1$1", "run"));
                    }
                }
                catch (Throwable t) {
                    throw a(t);
                }
                CidrRunConfigurationSettingsEditor.this.myConfigurationCombo.setModel((ComboBoxModel)new DefaultComboBoxModel(((CidrBuildConfigurationHelper<CidrBuildConfiguration, TARGET>)CidrRunConfigurationSettingsEditor.this.myConfigHelper).getConfigurations((TARGET)cidrBuildTarget).toArray()));
                CidrRunConfigurationSettingsEditor.this.myConfigurationCombo.setSelectedItem((Object)null);
                CidrRunConfigurationSettingsEditor.this.myConfigurationCombo.setSelectedItem((Object)((CidrBuildConfigurationHelper<BC, TARGET>)CidrRunConfigurationSettingsEditor.this.myConfigHelper).getDefaultConfiguration((TARGET)cidrBuildTarget));
            }
            
            private static Throwable a(final Throwable t) {
                return t;
            }
        }.execute();
        CidrRunConfigurationSettingsEditor.this.onTargetSelected(cidrBuildTarget);
    }
}