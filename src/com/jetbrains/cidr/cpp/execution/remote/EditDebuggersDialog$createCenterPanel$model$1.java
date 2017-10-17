// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution.remote;

import org.jetbrains.annotations.Nullable;
import kotlin.Metadata;
import com.intellij.ui.AddEditRemovePanel$TableModel;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0016J\u001c\u0010\t\u001a\u0004\u0018\u00010\u00022\b\u0010\n\u001a\u0004\u0018\u00010\u00022\u0006\u0010\b\u001a\u00020\u0005H\u0016J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0005H\u0016¨\u0006\u000e" }, d2 = { "com/jetbrains/cidr/cpp/execution/remote/EditDebuggersDialog$createCenterPanel$model$1", "Lcom/intellij/ui/AddEditRemovePanel$TableModel;", "", "()V", "getColumnCount", "", "getColumnName", "", "columnIndex", "getField", "o", "isEditable", "", "column", "clion" })
public static final class EditDebuggersDialog$createCenterPanel$model$1 extends AddEditRemovePanel$TableModel<String> {
    public int getColumnCount() {
        return 1;
    }
    
    @Nullable
    public Void getColumnName(final int n) {
        return null;
    }
    
    @Nullable
    public String getField(@Nullable final String s, final int n) {
        return s;
    }
    
    public boolean isEditable(final int n) {
        return true;
    }
}