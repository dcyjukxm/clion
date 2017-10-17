// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution;

import com.jetbrains.cidr.execution.CidrBuildConfigurationHelper;
import com.jetbrains.cidr.execution.CidrRunConfiguration;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.util.ui.GridBag;
import javax.swing.JPanel;
import com.jetbrains.cidr.execution.CidrBuildTarget;
import javax.swing.JComboBox;
import com.jetbrains.cidr.execution.BuildTargetAndConfigurationData;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.application.ReadAction;
import java.util.ArrayList;
import java.util.Collection;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.Nullable;
import javax.swing.Icon;
import java.util.List;
import java.util.Collections;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.execution.CidrRunConfigurationExecutableEditor;
import com.jetbrains.cidr.cpp.cmake.model.CMakeTarget;
import com.jetbrains.cidr.cpp.cmake.model.CMakeConfiguration;
import com.jetbrains.cidr.execution.CidrRunConfigurationSettingsEditor;

public class CMakeAppRunConfigurationSettingsEditor extends CidrRunConfigurationSettingsEditor<CMakeAppRunConfiguration, CMakeConfiguration, CMakeTarget, CMakeBuildConfigurationHelper>
{
    private CidrRunConfigurationExecutableEditor<CMakeAppRunConfiguration, CMakeConfiguration, CMakeTarget, CMakeBuildConfigurationHelper> myExecutableEditor;
    private final CMakeTarget ALL_TARGETS_ITEM;
    
    public CMakeAppRunConfigurationSettingsEditor(final Project project, @NotNull final CMakeBuildConfigurationHelper cMakeBuildConfigurationHelper) {
        if (cMakeBuildConfigurationHelper == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configHelper", "com/jetbrains/cidr/cpp/execution/CMakeAppRunConfigurationSettingsEditor", "<init>"));
        }
        super(project, cMakeBuildConfigurationHelper);
        final CMakeTarget cMakeTarget = ((CidrBuildConfigurationHelper<BC, CMakeTarget>)this.myConfigHelper).getDefaultTarget();
        Object o = null;
        Label_0078: {
            try {
                if (cMakeTarget == null) {
                    o = Collections.emptyList();
                    break Label_0078;
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            o = cMakeTarget.getBuildConfigurations();
        }
        this.ALL_TARGETS_ITEM = new CMakeTarget(((CMakeBuildConfigurationHelper)this.myConfigHelper).getProjectName(), "All targets", o) {
            @Override
            public boolean isExecutable() {
                return false;
            }
            
            @Nullable
            @Override
            public Icon getIcon() {
                return CMakeTarget.META_TARGET_ICON;
            }
        };
    }
    
    @NotNull
    @Override
    protected List<CMakeTarget> getTargets() {
        final List<CMakeTarget> targets = super.getTargets();
        final ArrayList arrayListWithCapacity = ContainerUtil.newArrayListWithCapacity(targets.size() + 1);
        ArrayList<Object> list;
        try {
            arrayListWithCapacity.add(this.ALL_TARGETS_ITEM);
            arrayListWithCapacity.addAll(targets);
            list = (ArrayList<Object>)arrayListWithCapacity;
            if (list == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/CMakeAppRunConfigurationSettingsEditor", "getTargets"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return (List<CMakeTarget>)list;
    }
    
    @Override
    protected void resetBuildAndConfigurationFrom(@NotNull final CMakeAppRunConfiguration cMakeAppRunConfiguration) {
        try {
            if (cMakeAppRunConfiguration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "runConfiguration", "com/jetbrains/cidr/cpp/execution/CMakeAppRunConfigurationSettingsEditor", "resetBuildAndConfigurationFrom"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        if (cMakeAppRunConfiguration.isBuildAllTargets()) {
            final BuildTargetAndConfigurationData targetAndConfigurationData = cMakeAppRunConfiguration.getTargetAndConfigurationData();
            String configurationName = null;
            Label_0072: {
                try {
                    if (targetAndConfigurationData == null) {
                        configurationName = null;
                        break Label_0072;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw c(ex2);
                }
                configurationName = targetAndConfigurationData.configurationName;
            }
            final String s = configurationName;
            final CMakeConfiguration cMakeConfiguration = (CMakeConfiguration)new ReadAction<CMakeConfiguration>() {
                protected void run(@NotNull final Result<CMakeConfiguration> result) throws Throwable {
                    try {
                        if (result == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/cpp/execution/CMakeAppRunConfigurationSettingsEditor$2", "run"));
                        }
                    }
                    catch (Throwable t) {
                        throw a(t);
                    }
                    result.setResult((Object)((CMakeBuildConfigurationHelper)CMakeAppRunConfigurationSettingsEditor.this.myConfigHelper).findConfiguration(CMakeAppRunConfigurationSettingsEditor.this.ALL_TARGETS_ITEM, s));
                }
                
                private static Throwable a(final Throwable t) {
                    return t;
                }
            }.execute().getResultObject();
            CidrRunConfigurationSettingsEditor.setSelectedItem(this.myTargetCombo, this.ALL_TARGETS_ITEM, null);
            CidrRunConfigurationSettingsEditor.setSelectedItem(this.myConfigurationCombo, cMakeConfiguration, s);
        }
        else {
            super.resetBuildAndConfigurationFrom(cMakeAppRunConfiguration);
        }
    }
    
    @Override
    protected void applyBuildAndConfigurationTo(final CMakeAppRunConfiguration cMakeAppRunConfiguration) {
        boolean b = false;
        Label_0023: {
            try {
                if (CidrRunConfigurationSettingsEditor.getSelectedItem((JComboBox)this.myTargetCombo) == this.ALL_TARGETS_ITEM) {
                    b = true;
                    break Label_0023;
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            b = false;
        }
        final boolean b2 = b;
        Label_0039: {
            try {
                if (b2) {
                    final String explicitBuildTargetName = "all";
                    break Label_0039;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
            final String explicitBuildTargetName = null;
            try {
                cMakeAppRunConfiguration.setExplicitBuildTargetName(explicitBuildTargetName);
                if (!b2) {
                    super.applyBuildAndConfigurationTo(cMakeAppRunConfiguration);
                    return;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw c(ex3);
            }
        }
        final CMakeConfiguration cMakeConfiguration = (CMakeConfiguration)CidrRunConfigurationSettingsEditor.getSelectedItem((JComboBox)this.myConfigurationCombo);
        String name = null;
        Label_0094: {
            try {
                if (cMakeConfiguration != null) {
                    name = cMakeConfiguration.getName();
                    break Label_0094;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw c(ex4);
            }
            name = (String)CidrRunConfigurationSettingsEditor.getSelectedInvalidItemValue((JComboBox)this.myConfigurationCombo);
        }
        cMakeAppRunConfiguration.setTargetAndConfigurationData(new BuildTargetAndConfigurationData((CidrBuildTarget)null, name));
    }
    
    @Override
    protected void createAdditionalControls(final JPanel panel, final GridBag gridBag) {
        super.createAdditionalControls(panel, gridBag);
        (this.myExecutableEditor = new CidrRunConfigurationExecutableEditor<CMakeAppRunConfiguration, CMakeConfiguration, CMakeTarget, CMakeBuildConfigurationHelper>(this.myProject, (CMakeBuildConfigurationHelper)this.myConfigHelper, this.hasTargetsInSeveralProjects())).createAdditionalControls(panel, gridBag);
    }
    
    @Override
    protected void onTargetSelected(@Nullable final CMakeTarget cMakeTarget) {
        super.onTargetSelected(cMakeTarget);
        this.myExecutableEditor.onTargetSelected(cMakeTarget);
    }
    
    @Override
    protected void applyEditorTo(@NotNull final CMakeAppRunConfiguration cMakeAppRunConfiguration) throws ConfigurationException {
        try {
            if (cMakeAppRunConfiguration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "runConfiguration", "com/jetbrains/cidr/cpp/execution/CMakeAppRunConfigurationSettingsEditor", "applyEditorTo"));
            }
        }
        catch (ConfigurationException ex) {
            throw c((Exception)ex);
        }
        super.applyEditorTo(cMakeAppRunConfiguration);
        this.myExecutableEditor.applyEditorTo(cMakeAppRunConfiguration);
    }
    
    @Override
    protected void resetEditorFrom(@NotNull final CMakeAppRunConfiguration cMakeAppRunConfiguration) {
        try {
            if (cMakeAppRunConfiguration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "runConfiguration", "com/jetbrains/cidr/cpp/execution/CMakeAppRunConfigurationSettingsEditor", "resetEditorFrom"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        super.resetEditorFrom(cMakeAppRunConfiguration);
        this.myExecutableEditor.resetEditorFrom(cMakeAppRunConfiguration);
    }
    
    private static Exception c(final Exception ex) {
        return ex;
    }
}
