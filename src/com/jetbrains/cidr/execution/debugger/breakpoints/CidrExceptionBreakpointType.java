// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.breakpoints;

import com.intellij.util.PlatformUtils;
import org.jetbrains.annotations.Nullable;
import com.intellij.util.xmlb.annotations.Attribute;
import com.intellij.xdebugger.XDebuggerManager;
import com.intellij.openapi.application.WriteAction;
import javax.swing.JComponent;
import com.intellij.openapi.project.Project;
import com.intellij.xdebugger.breakpoints.ui.XBreakpointCustomPropertiesPanel;
import com.intellij.xdebugger.breakpoints.XBreakpointProperties;
import com.intellij.icons.AllIcons;
import javax.swing.Icon;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.StringBuilderSpinAllocator;
import com.intellij.xdebugger.breakpoints.XBreakpoint;
import com.intellij.xdebugger.breakpoints.XBreakpointType;

public class CidrExceptionBreakpointType extends XBreakpointType<XBreakpoint<Properties>, Properties>
{
    public CidrExceptionBreakpointType() {
        this("Exception Breakpoints");
    }
    
    protected CidrExceptionBreakpointType(final String s) {
        super("CidrExceptionBreakpoint", s);
    }
    
    public String getDisplayText(final XBreakpoint<Properties> xBreakpoint) {
        final Properties properties = (Properties)xBreakpoint.getProperties();
        try {
            if (properties == null) {
                return "Invalid Exception Breakpoint";
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        final Properties.Type whenThrown = properties.getWhenThrown();
        final Properties.Type whenCaught = properties.getWhenCaught();
        Label_0055: {
            try {
                if (whenThrown != null) {
                    break Label_0055;
                }
                final Properties.Type type = whenCaught;
                if (type == null) {
                    return "Exception Breakpoint";
                }
                break Label_0055;
            }
            catch (RuntimeException ex2) {
                throw b(ex2);
            }
            try {
                final Properties.Type type = whenCaught;
                if (type == null) {
                    return "Exception Breakpoint";
                }
            }
            catch (RuntimeException ex3) {
                throw b(ex3);
            }
        }
        final StringBuilder alloc = StringBuilderSpinAllocator.alloc();
        try {
            try {
                alloc.append("When ");
                if (whenThrown != null) {
                    alloc.append(a(whenThrown)).append(" is thrown");
                }
            }
            catch (RuntimeException ex4) {
                throw b(ex4);
            }
            Label_0110: {
                try {
                    if (whenCaught == null) {
                        return alloc.toString();
                    }
                    final Properties.Type type2 = whenThrown;
                    if (type2 != null) {
                        break Label_0110;
                    }
                    break Label_0110;
                }
                catch (RuntimeException ex5) {
                    throw b(ex5);
                }
                try {
                    final Properties.Type type2 = whenThrown;
                    if (type2 != null) {
                        alloc.append("; ");
                    }
                }
                catch (RuntimeException ex6) {
                    throw b(ex6);
                }
            }
            alloc.append(a(whenCaught)).append(" is caught");
            return alloc.toString();
        }
        finally {
            StringBuilderSpinAllocator.dispose(alloc);
        }
    }
    
    private static String a(@NotNull final Properties.Type type) {
        try {
            if (type == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "thrown", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrExceptionBreakpointType", "formatType"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        final String displayString = type.getDisplayString();
        try {
            if (type == Properties.Type.ANY_EXCEPTION) {
                return displayString.toLowerCase();
            }
        }
        catch (RuntimeException ex2) {
            throw b(ex2);
        }
        return displayString;
    }
    
    @NotNull
    public Icon getEnabledIcon() {
        Icon db_exception_breakpoint;
        try {
            db_exception_breakpoint = AllIcons.Debugger.Db_exception_breakpoint;
            if (db_exception_breakpoint == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrExceptionBreakpointType", "getEnabledIcon"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        return db_exception_breakpoint;
    }
    
    @NotNull
    public Icon getDisabledIcon() {
        Icon db_disabled_exception_breakpoint;
        try {
            db_disabled_exception_breakpoint = AllIcons.Debugger.Db_disabled_exception_breakpoint;
            if (db_disabled_exception_breakpoint == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrExceptionBreakpointType", "getDisabledIcon"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        return db_disabled_exception_breakpoint;
    }
    
    @NotNull
    public Icon getInactiveDependentIcon() {
        Icon db_dep_exception_breakpoint;
        try {
            db_dep_exception_breakpoint = AllIcons.Debugger.Db_dep_exception_breakpoint;
            if (db_dep_exception_breakpoint == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrExceptionBreakpointType", "getInactiveDependentIcon"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        return db_dep_exception_breakpoint;
    }
    
    public Properties createProperties() {
        return new Properties();
    }
    
    public XBreakpoint<Properties> createDefaultBreakpoint(@NotNull final XBreakpointType.XBreakpointCreator<Properties> xBreakpointCreator) {
        try {
            if (xBreakpointCreator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "creator", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrExceptionBreakpointType", "createDefaultBreakpoint"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        final XBreakpoint breakpoint = xBreakpointCreator.createBreakpoint((XBreakpointProperties)this.createProperties());
        breakpoint.setEnabled(false);
        return (XBreakpoint<Properties>)breakpoint;
    }
    
    public XBreakpointCustomPropertiesPanel<XBreakpoint<Properties>> createCustomPropertiesPanel() {
        return new CidrExceptionBreakpointPropertiesPanel();
    }
    
    public XBreakpoint<Properties> addBreakpoint(final Project project, final JComponent component) {
        return (XBreakpoint<Properties>)WriteAction.compute(() -> XDebuggerManager.getInstance(project).getBreakpointManager().addBreakpoint((XBreakpointType)this, (XBreakpointProperties)new Properties()));
    }
    
    public boolean isAddBreakpointButtonVisible() {
        return true;
    }
    
    private static RuntimeException b(final RuntimeException ex) {
        return ex;
    }
    
    public static class Properties extends XBreakpointProperties<Properties>
    {
        @Attribute("thrown")
        @Nullable
        private Type myWhenThrown;
        @Attribute("caught")
        @Nullable
        private Type myWhenCaught;
        
        public Properties() {
            this(Type.ANY_EXCEPTION, null);
        }
        
        public Properties(@Nullable final Type myWhenThrown, @Nullable final Type myWhenCaught) {
            this.myWhenThrown = myWhenThrown;
            this.myWhenCaught = myWhenCaught;
        }
        
        @Nullable
        public Type getWhenThrown() {
            return this.myWhenThrown;
        }
        
        public void setWhenThrown(@Nullable final Type myWhenThrown) {
            this.myWhenThrown = myWhenThrown;
        }
        
        @Nullable
        public Type getWhenCaught() {
            return this.myWhenCaught;
        }
        
        public void setWhenCaught(@Nullable final Type myWhenCaught) {
            this.myWhenCaught = myWhenCaught;
        }
        
        @NotNull
        public Properties getState() {
            try {
                if (this == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrExceptionBreakpointType$Properties", "getState"));
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
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "state", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrExceptionBreakpointType$Properties", "loadState"));
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            this.myWhenThrown = properties.myWhenThrown;
            this.myWhenCaught = properties.myWhenCaught;
        }
        
        private static IllegalStateException a(final IllegalStateException ex) {
            return ex;
        }
        
        public enum Type
        {
            ANY_EXCEPTION("Any"), 
            OBJC_EXCEPTION("Objective-C") {
                @Override
                public boolean isAvailable() {
                    return PlatformUtils.isAppCode() || PlatformUtils.isRubyMine();
                }
            };
            
            private final String myDisplayString;
            
            private Type(final String myDisplayString) {
                this.myDisplayString = myDisplayString;
            }
            
            @NotNull
            public String getDisplayString() {
                String myDisplayString;
                try {
                    myDisplayString = this.myDisplayString;
                    if (myDisplayString == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrExceptionBreakpointType$Properties$Type", "getDisplayString"));
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                return myDisplayString;
            }
            
            public boolean isAvailable() {
                return true;
            }
            
            private static IllegalStateException a(final IllegalStateException ex) {
                return ex;
            }
        }
    }
}
