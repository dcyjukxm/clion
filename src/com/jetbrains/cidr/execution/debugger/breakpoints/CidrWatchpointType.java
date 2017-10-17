// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.breakpoints;

import com.jetbrains.cidr.execution.debugger.CidrDebugProcess;
import com.intellij.xdebugger.breakpoints.XBreakpointProperties;
import com.intellij.xdebugger.breakpoints.ui.XBreakpointCustomPropertiesPanel;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.execution.debugger.backend.LLWatchpoint;
import com.jetbrains.cidr.execution.debugger.backend.LLValue;
import com.intellij.xdebugger.XSourcePosition;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import icons.CidrDebuggerIcons;
import javax.swing.Icon;
import com.intellij.xdebugger.breakpoints.XBreakpoint;
import com.intellij.xdebugger.breakpoints.XBreakpointType;

public class CidrWatchpointType extends XBreakpointType<XBreakpoint<CidrWatchpointProperties>, CidrWatchpointProperties>
{
    protected CidrWatchpointType() {
        super("CidrWatchpoint", "Watchpoints");
    }
    
    @NotNull
    public Icon getEnabledIcon() {
        Icon watchpoint;
        try {
            watchpoint = CidrDebuggerIcons.Watchpoint;
            if (watchpoint == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrWatchpointType", "getEnabledIcon"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return watchpoint;
    }
    
    @NotNull
    public Icon getDisabledIcon() {
        Icon watchpoint_disabled;
        try {
            watchpoint_disabled = CidrDebuggerIcons.Watchpoint_disabled;
            if (watchpoint_disabled == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrWatchpointType", "getDisabledIcon"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return watchpoint_disabled;
    }
    
    @NotNull
    public Icon getInactiveDependentIcon() {
        Icon watchpoint_dependent;
        try {
            watchpoint_dependent = CidrDebuggerIcons.Watchpoint_dependent;
            if (watchpoint_dependent == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrWatchpointType", "getInactiveDependentIcon"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return watchpoint_dependent;
    }
    
    @Nullable
    public CidrWatchpointProperties createProperties() {
        return null;
    }
    
    public XSourcePosition getSourcePosition(@NotNull final XBreakpoint<CidrWatchpointProperties> xBreakpoint) {
        try {
            if (xBreakpoint == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "breakpoint", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrWatchpointType", "getSourcePosition"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final CidrWatchpointProperties cidrWatchpointProperties = (CidrWatchpointProperties)xBreakpoint.getProperties();
        try {
            if (cidrWatchpointProperties != null) {
                return cidrWatchpointProperties.getSourcePosition();
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return null;
    }
    
    public String getDisplayText(final XBreakpoint<CidrWatchpointProperties> xBreakpoint) {
        final CidrWatchpointProperties cidrWatchpointProperties = (CidrWatchpointProperties)xBreakpoint.getProperties();
        StringBuilder sb5 = null;
        Label_0062: {
            try {
                if (cidrWatchpointProperties == null) {
                    return "Obsolete watchpoint, please remove";
                }
                final StringBuilder sb = new StringBuilder();
                final CidrWatchpointProperties cidrWatchpointProperties2 = cidrWatchpointProperties;
                final LLValue llValue = cidrWatchpointProperties2.getLLValue();
                final String s = llValue.getName();
                final StringBuilder sb2 = sb.append(s);
                final String s2 = " (Access Type: \"";
                final StringBuilder sb3 = sb2.append(s2);
                final CidrWatchpointProperties cidrWatchpointProperties3 = cidrWatchpointProperties;
                final LLWatchpoint.AccessType accessType = cidrWatchpointProperties3.getAccessType();
                final StringBuilder sb4 = sb3.append(accessType);
                final String s3 = "\"";
                sb5 = sb4.append(s3);
                final CidrWatchpointProperties cidrWatchpointProperties4 = cidrWatchpointProperties;
                final LLWatchpoint.Lifetime lifetime = cidrWatchpointProperties4.getLifetime();
                if (lifetime == null) {
                    break Label_0062;
                }
                break Label_0062;
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            try {
                final StringBuilder sb = new StringBuilder();
                final CidrWatchpointProperties cidrWatchpointProperties2 = cidrWatchpointProperties;
                final LLValue llValue = cidrWatchpointProperties2.getLLValue();
                final String s = llValue.getName();
                final StringBuilder sb2 = sb.append(s);
                final String s2 = " (Access Type: \"";
                final StringBuilder sb3 = sb2.append(s2);
                final CidrWatchpointProperties cidrWatchpointProperties3 = cidrWatchpointProperties;
                final LLWatchpoint.AccessType accessType = cidrWatchpointProperties3.getAccessType();
                final StringBuilder sb4 = sb3.append(accessType);
                final String s3 = "\"";
                sb5 = sb4.append(s3);
                final CidrWatchpointProperties cidrWatchpointProperties4 = cidrWatchpointProperties;
                final LLWatchpoint.Lifetime lifetime = cidrWatchpointProperties4.getLifetime();
                if (lifetime == null) {
                    final String string = "";
                    return sb5.append(string).append(")").toString();
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        final String string = ", Life Time: \"" + cidrWatchpointProperties.getLifetime() + "\"";
        return sb5.append(string).append(")").toString();
    }
    
    public XBreakpointCustomPropertiesPanel<XBreakpoint<CidrWatchpointProperties>> createCustomPropertiesPanel(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrWatchpointType", "createCustomPropertiesPanel"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return new CidrWatchpointPropertiesPanel(project);
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
    
    public static class CidrWatchpointProperties extends XBreakpointProperties<CidrWatchpointState>
    {
        private LLWatchpoint.AccessType myAccessType;
        private LLWatchpoint.Lifetime myLifetime;
        private LLValue myLLValue;
        private String myExpr;
        private XSourcePosition myOriginalPosition;
        private CidrDebugProcess myDebugProcess;
        @Nullable
        private String myError;
        
        public LLWatchpoint.AccessType getAccessType() {
            return this.myAccessType;
        }
        
        public void setAccessType(final LLWatchpoint.AccessType myAccessType) {
            this.myAccessType = myAccessType;
        }
        
        @Nullable
        public LLWatchpoint.Lifetime getLifetime() {
            return this.myLifetime;
        }
        
        public void setLifetime(@Nullable final LLWatchpoint.Lifetime myLifetime) {
            this.myLifetime = myLifetime;
        }
        
        public XSourcePosition getSourcePosition() {
            return this.myOriginalPosition;
        }
        
        public CidrWatchpointState getState() {
            return new CidrWatchpointState();
        }
        
        public void loadState(final CidrWatchpointState cidrWatchpointState) {
        }
        
        public void setDebugProcess(@Nullable final CidrDebugProcess myDebugProcess) {
            this.myDebugProcess = myDebugProcess;
        }
        
        public CidrDebugProcess getDebugProcess() {
            return this.myDebugProcess;
        }
        
        public void setOriginalPosition(final XSourcePosition myOriginalPosition) {
            this.myOriginalPosition = myOriginalPosition;
        }
        
        @Nullable
        public String getError() {
            return this.myError;
        }
        
        public void setError(@Nullable final String myError) {
            this.myError = myError;
        }
        
        public LLValue getLLValue() {
            return this.myLLValue;
        }
        
        public void setLLValue(final LLValue myLLValue) {
            this.myLLValue = myLLValue;
        }
        
        public String getExpr() {
            return this.myExpr;
        }
        
        public void setExpr(final String myExpr) {
            this.myExpr = myExpr;
        }
        
        public static class CidrWatchpointState
        {
        }
    }
}
