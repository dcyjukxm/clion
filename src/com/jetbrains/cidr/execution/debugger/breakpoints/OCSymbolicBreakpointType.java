// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.breakpoints;

import com.jetbrains.cidr.ui.OCSymbolicBreakpointPropertiesPanel;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;

public class OCSymbolicBreakpointType extends CidrSymbolicBreakpointType
{
    public OCSymbolicBreakpointType() {
        super("OCSymbolicBreakpointType");
    }
    
    @Override
    protected CidrSymbolicBreakpointPropertiesPanel createSymbolicBreakpointPropertiesPanel(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/execution/debugger/breakpoints/OCSymbolicBreakpointType", "createSymbolicBreakpointPropertiesPanel"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return new OCSymbolicBreakpointPropertiesPanel(project);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
