// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.disasm;

import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.xdebugger.impl.XSourcePositionImpl;
import com.intellij.pom.Navigatable;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.intellij.testFramework.LightVirtualFile;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.application.ReadAction;
import com.jetbrains.cidr.execution.debugger.memory.Address;
import com.intellij.xdebugger.XSourcePosition;

class CidrDisasmView$3 implements XSourcePosition {
    private boolean myRequested;
    final /* synthetic */ Address val$address;
    
    private void a() {
        try {
            if (!this.myRequested) {
                CidrDisasmView.access$200(CidrDisasmView.this, this.val$address);
                this.myRequested = true;
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
    }
    
    public int getLine() {
        this.a();
        return (int)ReadAction.compute(() -> {
            try {
                if (address == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "address", "com/jetbrains/cidr/execution/debugger/disasm/CidrDisasmView$3", "lambda$getLine$0"));
                }
            }
            catch (RuntimeException ex) {
                throw b(ex);
            }
            return CidrDisasmView.access$300(CidrDisasmView.this).get(address).getLineNumberInDocument(address);
        });
    }
    
    public int getOffset() {
        this.a();
        return (int)ReadAction.compute(() -> {
            try {
                if (address == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "address", "com/jetbrains/cidr/execution/debugger/disasm/CidrDisasmView$3", "lambda$getOffset$1"));
                }
            }
            catch (RuntimeException ex) {
                throw b(ex);
            }
            return CidrDisasmView.access$300(CidrDisasmView.this).get(address).getOffsetInDocument(address);
        });
    }
    
    @NotNull
    public VirtualFile getFile() {
        LightVirtualFile access$100;
        try {
            access$100 = CidrDisasmView.access$100(CidrDisasmView.this);
            if (access$100 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/disasm/CidrDisasmView$3", "getFile"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        return (VirtualFile)access$100;
    }
    
    @NotNull
    public Navigatable createNavigatable(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/execution/debugger/disasm/CidrDisasmView$3", "createNavigatable"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        OpenFileDescriptor doCreateOpenFileDescriptor;
        try {
            doCreateOpenFileDescriptor = XSourcePositionImpl.doCreateOpenFileDescriptor(project, (XSourcePosition)this);
            if (doCreateOpenFileDescriptor == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/disasm/CidrDisasmView$3", "createNavigatable"));
            }
        }
        catch (RuntimeException ex2) {
            throw b(ex2);
        }
        return (Navigatable)doCreateOpenFileDescriptor;
    }
    
    private static RuntimeException b(final RuntimeException ex) {
        return ex;
    }
}