// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.breakpoints;

import org.jetbrains.annotations.Nullable;
import com.intellij.xdebugger.breakpoints.XBreakpoint;
import com.jetbrains.cidr.execution.debugger.CidrDebuggerLanguageSupportFactory;
import com.intellij.xdebugger.evaluation.XDebuggerEditorsProvider;
import com.intellij.xdebugger.breakpoints.XLineBreakpoint;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.Collections;
import com.intellij.openapi.components.ServiceManager;
import com.jetbrains.cidr.execution.CidrDebuggerBundle;
import com.intellij.openapi.fileTypes.FileType;
import java.util.Set;
import com.intellij.xdebugger.breakpoints.XBreakpointProperties;
import com.intellij.xdebugger.breakpoints.XLineBreakpointType;

public class CidrLineBreakpointType extends XLineBreakpointType<XBreakpointProperties>
{
    public static final String TYPE_ID = "com.jetbrains.cidr.execution.debugger.OCBreakpointType";
    private final Set<FileType> myFileTypes;
    
    public CidrLineBreakpointType() {
        this("com.jetbrains.cidr.execution.debugger.OCBreakpointType", CidrDebuggerBundle.message("debug.breakpoint.line", new Object[0]));
    }
    
    public CidrLineBreakpointType(final String s, final String s2) {
        super(s, s2);
        final CidrLineBreakpointFileTypesProvider cidrLineBreakpointFileTypesProvider = (CidrLineBreakpointFileTypesProvider)ServiceManager.getService((Class)CidrLineBreakpointFileTypesProvider.class);
        Set<FileType> myFileTypes = null;
        Label_0036: {
            try {
                if (cidrLineBreakpointFileTypesProvider == null) {
                    myFileTypes = Collections.emptySet();
                    break Label_0036;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            myFileTypes = cidrLineBreakpointFileTypesProvider.getFileTypes();
        }
        this.myFileTypes = myFileTypes;
    }
    
    public boolean canPutAt(@NotNull final VirtualFile virtualFile, final int n, @NotNull final Project project) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrLineBreakpointType", "canPutAt"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrLineBreakpointType", "canPutAt"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return this.myFileTypes.contains(virtualFile.getFileType());
    }
    
    public XBreakpointProperties createBreakpointProperties(@NotNull final VirtualFile virtualFile, final int n) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrLineBreakpointType", "createBreakpointProperties"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return null;
    }
    
    public String getBreakpointsDialogHelpTopic() {
        return super.getBreakpointsDialogHelpTopic();
    }
    
    @Nullable
    public XDebuggerEditorsProvider getEditorsProvider(@NotNull final XLineBreakpoint<XBreakpointProperties> xLineBreakpoint, @NotNull final Project project) {
        try {
            if (xLineBreakpoint == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "breakpoint", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrLineBreakpointType", "getEditorsProvider"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/execution/debugger/breakpoints/CidrLineBreakpointType", "getEditorsProvider"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return CidrDebuggerLanguageSupportFactory.getEditorsProvider((XBreakpoint<? extends XBreakpointProperties>)xLineBreakpoint);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
