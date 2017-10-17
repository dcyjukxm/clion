// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.projectWizard;

import com.jetbrains.cidr.cpp.toolchains.CMake;
import com.jetbrains.cidr.cpp.CPPToolchains;
import com.intellij.ide.wizard.Step;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.intellij.ide.wizard.AbstractWizard;

public abstract class CMakeProjectWizard extends AbstractWizard<CMakeProjectStepAdapter>
{
    private final String myHelpId;
    private CMakeProjectStepAdapter myStep;
    
    public CMakeProjectWizard(@NotNull final String s, @Nullable final String myHelpId) {
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "title", "com/jetbrains/cidr/cpp/cmake/projectWizard/CMakeProjectWizard", "<init>"));
        }
        super(s, (Project)null);
        this.myHelpId = myHelpId;
    }
    
    @Nullable
    protected final String getHelpID() {
        return this.myHelpId;
    }
    
    protected final void initWithStep(final CMakeProjectStepAdapter myStep) {
        (this.myStep = myStep).init(this.getDisposable(), this::updateButtons);
        this.addStep((Step)this.myStep);
        this.init();
    }
    
    protected final boolean canFinish() {
        final Boolean validate = this.myStep.validate();
        try {
            if (validate != null) {
                return validate;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final String validateWithMessage = this.myStep.validateWithMessage();
        try {
            this.setErrorText(validateWithMessage);
            if (validateWithMessage == null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return false;
    }
    
    protected final void doOKAction() {
        try {
            if (this.tryFinish()) {
                super.doOKAction();
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
    }
    
    protected abstract boolean tryFinish();
    
    public final void runWizard() {
        try {
            this.beforeRunWizard();
            this.show();
            if (this.getExitCode() == 0) {
                this.doRunWizard();
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
    }
    
    protected void beforeRunWizard() {
    }
    
    protected abstract void doRunWizard();
    
    public static String getCMakeListsFileHeader(final String s) {
        final CMake cMake = CPPToolchains.getInstance().getCMake();
        StringBuilder sb;
        try {
            sb = new StringBuilder();
            if (cMake == null) {
                final String version = null;
                return sb.append(CLionProjectWizardUtils.getCMakeMinimumRequiredLine(version)).append("project(").append(s).append(")\n\nset(CMAKE_CXX_STANDARD 11)\n\n").toString();
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final String version = cMake.readVersion();
        return sb.append(CLionProjectWizardUtils.getCMakeMinimumRequiredLine(version)).append("project(").append(s).append(")\n\nset(CMAKE_CXX_STANDARD 11)\n\n").toString();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
