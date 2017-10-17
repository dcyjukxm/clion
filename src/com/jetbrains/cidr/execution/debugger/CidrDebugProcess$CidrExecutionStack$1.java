// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger;

import com.intellij.openapi.util.Comparing;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import javax.swing.Icon;
import com.intellij.openapi.editor.markup.GutterIconRenderer;

class CidrDebugProcess$CidrExecutionStack$1 extends GutterIconRenderer {
    @NotNull
    public Icon getIcon() {
        Icon icon;
        try {
            icon = CidrExecutionStack.access$600(CidrExecutionStack.this).icon;
            if (icon == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/CidrDebugProcess$CidrExecutionStack$1", "getIcon"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return icon;
    }
    
    @Nullable
    public String getTooltipText() {
        return CidrExecutionStack.access$600(CidrExecutionStack.this).getDisplayString();
    }
    
    public boolean equals(final Object o) {
        try {
            if (this == o) {
                return true;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (o == null) {
                return false;
            }
            final GutterIconRenderer gutterIconRenderer = this;
            final Class<? extends CidrDebugProcess$CidrExecutionStack$1> clazz = gutterIconRenderer.getClass();
            final Object o2 = o;
            final Class<?> clazz2 = o2.getClass();
            if (clazz != clazz2) {
                return false;
            }
            return Comparing.equal((Object)CidrExecutionStack.access$600(CidrExecutionStack.this), (Object)CidrExecutionStack.access$600((CidrExecutionStack)o));
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        try {
            final GutterIconRenderer gutterIconRenderer = this;
            final Class<? extends CidrDebugProcess$CidrExecutionStack$1> clazz = gutterIconRenderer.getClass();
            final Object o2 = o;
            final Class<?> clazz2 = o2.getClass();
            if (clazz != clazz2) {
                return false;
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        return Comparing.equal((Object)CidrExecutionStack.access$600(CidrExecutionStack.this), (Object)CidrExecutionStack.access$600((CidrExecutionStack)o));
    }
    
    public int hashCode() {
        return CidrExecutionStack.access$600(CidrExecutionStack.this).hashCode();
    }
    
    public boolean isDumbAware() {
        return true;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}