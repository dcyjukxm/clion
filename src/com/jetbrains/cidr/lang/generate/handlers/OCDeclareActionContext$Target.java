// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers;

import com.jetbrains.cidr.lang.OCIcons;
import javax.swing.Icon;

public enum Target
{
    INTERFACE("Interface", OCIcons.CodeAssistantClass), 
    PRIVATE_CATEGORY("Private Category", OCIcons.CodeAssistantClassExtension), 
    IMPLEMENTATION("Implementation (don't declare)", OCIcons.CodeAssistantClass);
    
    private final String myName;
    private Icon myIcon;
    
    private Target(final String myName, final Icon myIcon) {
        this.myName = myName;
        this.myIcon = myIcon;
    }
    
    public String getName() {
        return this.myName;
    }
    
    public Icon getIcon() {
        return this.myIcon;
    }
}
