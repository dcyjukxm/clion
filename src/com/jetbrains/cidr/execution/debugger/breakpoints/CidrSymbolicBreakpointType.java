// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.breakpoints;

import com.intellij.xdebugger.XDebuggerManager;
import com.intellij.xdebugger.breakpoints.XBreakpointProperties;
import com.jetbrains.cidr.execution.debugger.CidrDebuggerLanguageSupportFactory;
import com.intellij.xdebugger.evaluation.XDebuggerEditorsProvider;
import com.intellij.xdebugger.breakpoints.ui.XBreakpointCustomPropertiesPanel;
import com.intellij.openapi.application.WriteAction;
import javax.swing.JComponent;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.intellij.icons.AllIcons;
import javax.swing.Icon;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.xdebugger.breakpoints.XBreakpoint;
import com.intellij.xdebugger.breakpoints.XBreakpointType;

public abstract class CidrSymbolicBreakpointType extends XBreakpointType<XBreakpoint<Properties>, Properties>
{
    public CidrSymbolicBreakpointType(final String s) {
        this(s, "Symbolic Breakpoints");
    }
    
    protected CidrSymbolicBreakpointType(final String s, final String s2) {
        super(s, s2);
    }
    
    public String getDisplayText(final XBreakpoint<Properties> xBreakpoint) {
        final Properties properties = (Properties)xBreakpoint.getProperties();
        try {
            if (properties == null) {
                return "Invalid breakpoint";
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        String symbolPattern = null;
        Label_0044: {
            try {
                if (StringUtil.isEmpty(properties.getSymbolPattern())) {
                    symbolPattern = "<Empty>";
                    break Label_0044;
                }
            }
            catch (RuntimeException ex2) {
                throw b(ex2);
            }
            symbolPattern = properties.getSymbolPattern();
        }
        String string = symbolPattern;
        if (properties.getModuleName() != null) {
            string = properties.getModuleName() + ": " + string;
        }
        return string;
    }
    
    @NotNull
    public Icon getEnabledIcon() {
        Icon db_method_breakpoint;
        try {
            db_method_breakpoint = AllIcons.Debugger.Db_method_breakpoint;
            if (db_method_breakpoint == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrSymbolicBreakpointType", "getEnabledIcon"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        return db_method_breakpoint;
    }
    
    @NotNull
    public Icon getDisabledIcon() {
        Icon db_disabled_method_breakpoint;
        try {
            db_disabled_method_breakpoint = AllIcons.Debugger.Db_disabled_method_breakpoint;
            if (db_disabled_method_breakpoint == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrSymbolicBreakpointType", "getDisabledIcon"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        return db_disabled_method_breakpoint;
    }
    
    @NotNull
    public Icon getInactiveDependentIcon() {
        Icon db_dep_method_breakpoint;
        try {
            db_dep_method_breakpoint = AllIcons.Debugger.Db_dep_method_breakpoint;
            if (db_dep_method_breakpoint == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrSymbolicBreakpointType", "getInactiveDependentIcon"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        return db_dep_method_breakpoint;
    }
    
    public boolean isAddBreakpointButtonVisible() {
        return true;
    }
    
    @Nullable
    public Properties createProperties() {
        return new Properties();
    }
    
    @NotNull
    public XBreakpoint<Properties> addBreakpoint(final Project project, final JComponent component) {
        XBreakpoint xBreakpoint;
        try {
            xBreakpoint = (XBreakpoint)WriteAction.compute(() -> XDebuggerManager.getInstance(project).getBreakpointManager().addBreakpoint((XBreakpointType)this, (XBreakpointProperties)new Properties()));
            if (xBreakpoint == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrSymbolicBreakpointType", "addBreakpoint"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        return (XBreakpoint<Properties>)xBreakpoint;
    }
    
    @Nullable
    public XBreakpointCustomPropertiesPanel<XBreakpoint<Properties>> createCustomTopPropertiesPanel(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrSymbolicBreakpointType", "createCustomTopPropertiesPanel"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        return this.createSymbolicBreakpointPropertiesPanel(project);
    }
    
    protected abstract CidrSymbolicBreakpointPropertiesPanel createSymbolicBreakpointPropertiesPanel(@NotNull final Project p0);
    
    @Nullable
    public XDebuggerEditorsProvider getEditorsProvider(@NotNull final XBreakpoint<Properties> xBreakpoint, @NotNull final Project project) {
        try {
            if (xBreakpoint == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "breakpoint", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrSymbolicBreakpointType", "getEditorsProvider"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrSymbolicBreakpointType", "getEditorsProvider"));
            }
        }
        catch (RuntimeException ex2) {
            throw b(ex2);
        }
        return CidrDebuggerLanguageSupportFactory.getEditorsProvider(xBreakpoint);
    }
    
    private static RuntimeException b(final RuntimeException ex) {
        return ex;
    }
    
    public static class Properties extends XBreakpointProperties<Properties>
    {
        @Nullable
        private String mySymbolPattern;
        @Nullable
        private String myModuleName;
        
        public Properties() {
        }
        
        public Properties(@Nullable final String mySymbolPattern, @Nullable final String myModuleName) {
            this.mySymbolPattern = mySymbolPattern;
            this.myModuleName = myModuleName;
        }
        
        @Nullable
        public String getSymbolPattern() {
            return this.mySymbolPattern;
        }
        
        public void setSymbolPattern(@Nullable final String mySymbolPattern) {
            this.mySymbolPattern = mySymbolPattern;
        }
        
        @Nullable
        public String getModuleName() {
            return this.myModuleName;
        }
        
        public void setModuleName(@Nullable final String myModuleName) {
            this.myModuleName = myModuleName;
        }
        
        @NotNull
        public Properties getState() {
            try {
                if (this == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrSymbolicBreakpointType$Properties", "getState"));
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            return this;
        }
        
        public void loadState(@NotNull final Properties properties) {
            try {
                if (properties == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "state", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrSymbolicBreakpointType$Properties", "loadState"));
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            this.mySymbolPattern = properties.mySymbolPattern;
            this.myModuleName = properties.myModuleName;
        }
        
        private static IllegalStateException a(final IllegalStateException ex) {
            return ex;
        }
    }
}
