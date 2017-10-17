// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.settings;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.ui.DialogWrapper;

static final class OCCodeStyleSettings$1 implements DialogWrapper.DoNotAskOption {
    final /* synthetic */ Ref val$save;
    
    public boolean isToBeShown() {
        return true;
    }
    
    public void setToBeShown(final boolean b, final int n) {
        Ref val$save = null;
        boolean b2 = false;
        Label_0017: {
            try {
                val$save = this.val$save;
                if (!b) {
                    b2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            b2 = false;
        }
        val$save.set((Object)b2);
    }
    
    public boolean canBeHidden() {
        return true;
    }
    
    public boolean shouldSaveOptionsOnCancel() {
        return true;
    }
    
    @NotNull
    public String getDoNotShowMessage() {
        String s;
        try {
            s = "Remember the choice";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/settings/OCCodeStyleSettings$1", "getDoNotShowMessage"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}