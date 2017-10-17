// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.workspace;

import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import org.jetbrains.annotations.NotNull;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import com.jetbrains.cidr.cpp.cmake.console.CMakeConsoleViewImpl;
import com.intellij.openapi.actionSystem.DataProvider;
import javax.swing.JPanel;

private static class DataProviderPanel extends JPanel implements DataProvider
{
    private final CMakeConsoleViewImpl myConsole;
    
    private DataProviderPanel(final CMakeConsoleViewImpl myConsole) {
        super(new BorderLayout());
        this.add(this.myConsole = myConsole, "Center");
    }
    
    @NotNull
    public CMakeConsoleViewImpl getConsole() {
        CMakeConsoleViewImpl myConsole;
        try {
            myConsole = this.myConsole;
            if (myConsole == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/workspace/CMakeOutputConsole$DataProviderPanel", "getConsole"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return myConsole;
    }
    
    @Nullable
    public Object getData(final String s) {
        try {
            if (PlatformDataKeys.HELP_ID.is(s)) {
                return "Cmake_Output";
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return null;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
