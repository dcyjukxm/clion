// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.projectWizard;

import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.util.Disposer;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.Disposable;
import com.intellij.ide.wizard.StepAdapter;

public abstract class CMakeProjectStepAdapter extends StepAdapter implements Disposable
{
    protected Runnable myValidateCallback;
    
    public final void init(@NotNull final Disposable disposable, @NotNull final Runnable myValidateCallback) {
        try {
            if (disposable == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parent", "com/jetbrains/cidr/cpp/cmake/projectWizard/CMakeProjectStepAdapter", "init"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (myValidateCallback == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "validateCallback", "com/jetbrains/cidr/cpp/cmake/projectWizard/CMakeProjectStepAdapter", "init"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        this.myValidateCallback = myValidateCallback;
        Disposer.register(disposable, (Disposable)this);
        this.init();
    }
    
    protected abstract void init();
    
    @Nullable
    public Boolean validate() {
        return null;
    }
    
    @Nullable
    public String validateWithMessage() {
        return null;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
