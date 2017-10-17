// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve.references;

import java.util.List;
import com.intellij.openapi.extensions.ExtensionPointName;

public interface OCResourceCompletionProviders
{
    public static final ExtensionPointName<OCResourceCompletionProviders> EP_NAME = ExtensionPointName.create("cidr.lang.resourceCompletionProviders");
    
    List<OCResourceCompletionProvider> getProviders();
}
