// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.console;

import com.intellij.execution.impl.ConsoleViewImpl;
import com.intellij.openapi.actionSystem.AnAction;
import javax.swing.JComponent;
import java.awt.Component;
import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.ActionManager;
import java.util.Collection;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.execution.ui.ConsoleView;
import com.jetbrains.cidr.execution.CidrPathConsoleFilter;
import com.intellij.execution.filters.Filter;
import java.io.File;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.toolchains.CidrToolEnvironment;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.execution.filters.TextConsoleBuilderImpl;

public class CMakeConsoleBuilder extends TextConsoleBuilderImpl
{
    public CMakeConsoleBuilder(@NotNull final Project project, @Nullable final CidrToolEnvironment cidrToolEnvironment, @Nullable final File file) {
        if (project == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/cmake/console/CMakeConsoleBuilder", "<init>"));
        }
        super(project);
        this.addFilter((Filter)new CMakeOutputFilter(project, cidrToolEnvironment, file));
        this.addFilter((Filter)new CidrPathConsoleFilter(project, cidrToolEnvironment, file));
    }
    
    @NotNull
    @Override
    protected ConsoleView createConsole() {
        final DefaultActionGroup defaultActionGroup = new DefaultActionGroup();
        final CMakeConsoleViewImpl cMakeConsoleViewImpl = new CMakeConsoleViewImpl(this.getProject(), this.getScope(), this.isViewer(), this.isUsePredefinedMessageFilter());
        final JComponent component = ((ConsoleView)cMakeConsoleViewImpl).getComponent();
        CMakeConsoleViewImpl cMakeConsoleViewImpl2;
        try {
            defaultActionGroup.addAll((Collection)ContainerUtil.filter((Object[])((ConsoleView)cMakeConsoleViewImpl).createConsoleActions(), anAction -> {
                try {
                    if (!(anAction instanceof ConsoleViewImpl.ClearAllAction)) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                return false;
            }));
            component.add(ActionManager.getInstance().createActionToolbar("CMakeConsole", (ActionGroup)defaultActionGroup, false).getComponent(), "West");
            cMakeConsoleViewImpl2 = cMakeConsoleViewImpl;
            if (cMakeConsoleViewImpl2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/console/CMakeConsoleBuilder", "createConsole"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return (ConsoleView)cMakeConsoleViewImpl2;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
