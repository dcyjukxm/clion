// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.project;

import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.roots.impl.ModuleLibraryOrderEntryImpl;
import com.intellij.openapi.roots.impl.libraries.LibraryEx;
import com.intellij.openapi.roots.OrderEntry;
import com.intellij.util.containers.FactoryMap;

class CidrRootsSynchronizer$2$1 extends FactoryMap<OrderEntry, LibraryEx.ModifiableModelEx> {
    @Nullable
    protected LibraryEx.ModifiableModelEx create(final OrderEntry orderEntry) {
        return (LibraryEx.ModifiableModelEx)((ModuleLibraryOrderEntryImpl)orderEntry).getLibrary().getModifiableModel();
    }
}