// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.intellij.openapi.extensions.ExtensionPointName;

public interface SerializerProvider
{
    public static final ExtensionPointName<SerializerProvider> EP_NAME = ExtensionPointName.create("cidr.lang.serializerProvider");
    
    void registerSerializers(final FileSymbolTableSerializer p0);
    
    int getVersion();
}
