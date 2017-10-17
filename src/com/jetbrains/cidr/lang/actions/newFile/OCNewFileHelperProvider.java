// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.actions.newFile;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.extensions.ExtensionPointName;

public interface OCNewFileHelperProvider
{
    public static final ExtensionPointName<OCNewFileHelperProvider> EP_NAME = ExtensionPointName.create("cidr.lang.newFileHelperProvider");
    
    @NotNull
    OCNewFileHelper createHelper();
}
