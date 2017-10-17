// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution.remote;

import java.util.List;
import com.intellij.ui.AddEditRemovePanel$TableModel;
import org.jetbrains.annotations.NotNull;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
import kotlin.Metadata;
import com.jetbrains.cidr.execution.debugger.remote.CidrRemotePathMapping;
import com.intellij.ui.InplaceAddEditRemovePanel;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\n\u0010\u0004\u001a\u0004\u0018\u00010\u0002H\u0014J\u0012\u0010\u0005\u001a\u00020\u00022\b\u0010\u0006\u001a\u0004\u0018\u00010\u0002H\u0014J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0012\u0010\t\u001a\u00020\b2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0002H\u0014¨\u0006\n" }, d2 = { "com/jetbrains/cidr/cpp/execution/remote/CLionRemoteRunConfigurationEditor$createEditor$2", "Lcom/intellij/ui/InplaceAddEditRemovePanel;", "Lcom/jetbrains/cidr/execution/debugger/remote/CidrRemotePathMapping;", "(Lcom/jetbrains/cidr/cpp/execution/remote/CLionRemoteRunConfigurationEditor$createEditor$model$1;Lcom/intellij/ui/AddEditRemovePanel$TableModel;Ljava/util/List;)V", "addItem", "editItem", "o", "isUpDownSupported", "", "removeItem", "clion" })
public static final class CLionRemoteRunConfigurationEditor$createEditor$2 extends InplaceAddEditRemovePanel<CidrRemotePathMapping> {
    @Nullable
    protected CidrRemotePathMapping addItem() {
        return new CidrRemotePathMapping("", "");
    }
    
    protected boolean removeItem(@Nullable final CidrRemotePathMapping cidrRemotePathMapping) {
        return true;
    }
    
    @NotNull
    protected CidrRemotePathMapping editItem(@Nullable final CidrRemotePathMapping cidrRemotePathMapping) {
        if (cidrRemotePathMapping == null) {
            Intrinsics.throwNpe();
        }
        return cidrRemotePathMapping;
    }
    
    public boolean isUpDownSupported() {
        return true;
    }
}