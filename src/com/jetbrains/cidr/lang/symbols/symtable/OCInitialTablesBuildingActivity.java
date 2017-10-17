// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.intellij.openapi.application.ApplicationManager;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.startup.StartupActivity;

public class OCInitialTablesBuildingActivity implements StartupActivity, DumbAware
{
    public void runActivity(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/symbols/symtable/OCInitialTablesBuildingActivity", "runActivity"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (!ApplicationManager.getApplication().isUnitTestMode()) {
                OCSymbolTablesBuildingActivity.getInstance(project).rebuildSymbols();
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
