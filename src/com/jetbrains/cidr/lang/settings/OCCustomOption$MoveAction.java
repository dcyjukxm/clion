// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.settings;

public static class MoveAction
{
    public final String group;
    public final String[] options;
    
    public MoveAction(final String group, final String... options) {
        this.group = group;
        this.options = options;
    }
}
