// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution.remote;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.execution.CidrDebuggerBundle;
import kotlin.Metadata;
import com.jetbrains.cidr.execution.debugger.remote.CidrRemotePathMapping;
import com.intellij.ui.AddEditRemovePanel$TableModel;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u00005\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u00072\u0006\u0010\t\u001a\u00020\u0005H\u0016J\u001c\u0010\n\u001a\u0004\u0018\u00010\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\u00022\u0006\u0010\t\u001a\u00020\u0005H\u0016J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0005H\u0016J$\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00022\u0006\u0010\t\u001a\u00020\u0005H\u0016¨\u0006\u0014" }, d2 = { "com/jetbrains/cidr/cpp/execution/remote/CLionRemoteRunConfigurationEditor$createEditor$model$1", "Lcom/intellij/ui/AddEditRemovePanel$TableModel;", "Lcom/jetbrains/cidr/execution/debugger/remote/CidrRemotePathMapping;", "()V", "getColumnCount", "", "getColumnName", "", "kotlin.jvm.PlatformType", "columnIndex", "getField", "o", "isEditable", "", "column", "setValue", "", "aValue", "", "data", "clion" })
public static final class CLionRemoteRunConfigurationEditor$createEditor$model$1 extends AddEditRemovePanel$TableModel<CidrRemotePathMapping> {
    public String getColumnName(final int n) {
        return (n == 0) ? CidrDebuggerBundle.message("run.configuration.gdb.pathMappings.remote", new Object[0]) : CidrDebuggerBundle.message("run.configuration.gdb.pathMappings.local", new Object[0]);
    }
    
    @Nullable
    public String getField(@Nullable final CidrRemotePathMapping cidrRemotePathMapping, final int n) {
        return (n == 0) ? ((cidrRemotePathMapping != null) ? cidrRemotePathMapping.getRemote() : null) : ((cidrRemotePathMapping != null) ? cidrRemotePathMapping.getLocal() : null);
    }
    
    public int getColumnCount() {
        return 2;
    }
    
    public boolean isEditable(final int n) {
        return true;
    }
    
    public void setValue(@Nullable final Object o, @Nullable final CidrRemotePathMapping cidrRemotePathMapping, final int n) {
        if (n == 0) {
            if (cidrRemotePathMapping != null) {
                cidrRemotePathMapping.setRemote(String.valueOf(o));
            }
        }
        else if (cidrRemotePathMapping != null) {
            cidrRemotePathMapping.setLocal(String.valueOf(o));
        }
    }
}