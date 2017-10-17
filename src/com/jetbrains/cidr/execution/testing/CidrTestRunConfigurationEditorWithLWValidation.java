// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing;

import com.intellij.openapi.application.ApplicationManager;
import javax.swing.JComponent;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Key;
import com.jetbrains.cidr.execution.CidrBuildConfigurationHelper;
import com.jetbrains.cidr.execution.CidrBuildTarget;
import com.jetbrains.cidr.execution.CidrBuildConfiguration;
import com.jetbrains.cidr.execution.CidrRunConfiguration;

public abstract class CidrTestRunConfigurationEditorWithLWValidation<SUITE, CONFIGURATION extends CidrRunConfiguration, BC extends CidrBuildConfiguration, TARGET extends CidrBuildTarget<BC>, BCH extends CidrBuildConfigurationHelper<BC, TARGET>> extends CidrTestRunConfigurationEditor<SUITE, CONFIGURATION, BC, TARGET, BCH>
{
    public static final Key<Boolean> TEST_LIGHTWEIGHT_VALIDATION;
    
    public CidrTestRunConfigurationEditorWithLWValidation(@NotNull final Project project, @NotNull final BCH bch) {
        if (project == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationEditorWithLWValidation", "<init>"));
        }
        if (bch == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configHelper", "com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationEditorWithLWValidation", "<init>"));
        }
        super(project, bch);
    }
    
    @NotNull
    @Override
    protected JComponent createEditor() {
        JComponent editor;
        try {
            this.myProject.putUserData((Key)CidrTestRunConfigurationEditorWithLWValidation.TEST_LIGHTWEIGHT_VALIDATION, (Object)true);
            editor = super.createEditor();
            if (editor == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationEditorWithLWValidation", "createEditor"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return editor;
    }
    
    @Override
    protected void disposeEditor() {
        this.myProject.putUserData((Key)CidrTestRunConfigurationEditorWithLWValidation.TEST_LIGHTWEIGHT_VALIDATION, (Object)null);
        super.disposeEditor();
    }
    
    @Override
    protected void updateSuiteAndMethodControls() {
        Label_0038: {
            try {
                ApplicationManager.getApplication().assertIsDispatchThread();
                super.updateSuiteAndMethodControls();
                if (this.myProject.isDisposed()) {
                    return;
                }
                final CidrTestRunConfigurationEditorWithLWValidation cidrTestRunConfigurationEditorWithLWValidation = this;
                final boolean b = cidrTestRunConfigurationEditorWithLWValidation.isDisposed();
                if (!b) {
                    break Label_0038;
                }
                return;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final CidrTestRunConfigurationEditorWithLWValidation cidrTestRunConfigurationEditorWithLWValidation = this;
                final boolean b = cidrTestRunConfigurationEditorWithLWValidation.isDisposed();
                if (!b) {
                    this.myProject.putUserData((Key)CidrTestRunConfigurationEditorWithLWValidation.TEST_LIGHTWEIGHT_VALIDATION, (Object)null);
                    this.myMethodCombo.fireSelectedItemChanged();
                    this.myProject.putUserData((Key)CidrTestRunConfigurationEditorWithLWValidation.TEST_LIGHTWEIGHT_VALIDATION, (Object)true);
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
    }
    
    public static boolean isLWValidationOn(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/execution/testing/CidrTestRunConfigurationEditorWithLWValidation", "isLWValidationOn"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (project.getUserData((Key)CidrTestRunConfigurationEditorWithLWValidation.TEST_LIGHTWEIGHT_VALIDATION) != null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return false;
    }
    
    static {
        TEST_LIGHTWEIGHT_VALIDATION = Key.create("TEST_LIGHTWEIGHT_VALIDATION");
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
