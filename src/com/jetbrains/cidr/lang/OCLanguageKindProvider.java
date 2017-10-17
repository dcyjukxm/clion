// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Collection;
import com.intellij.util.containers.ContainerUtil;
import java.util.List;
import com.intellij.openapi.extensions.ExtensionPointName;

public abstract class OCLanguageKindProvider
{
    public static final ExtensionPointName<OCLanguageKindProvider> EP_NAME;
    private static volatile List<OCLanguageKind> ourCache;
    
    protected abstract List<OCLanguageKind> getLanguageKinds();
    
    public static List<OCLanguageKind> getAllLanguageKinds() {
        if (OCLanguageKindProvider.ourCache != null) {
            return OCLanguageKindProvider.ourCache;
        }
        final ArrayList arrayList = ContainerUtil.newArrayList();
        final OCLanguageKindProvider[] array = (OCLanguageKindProvider[])OCLanguageKindProvider.EP_NAME.getExtensions();
        for (int length = array.length, i = 0; i < length; ++i) {
            arrayList.addAll(array[i].getLanguageKinds());
        }
        if (OCLanguageKindProvider.ourCache == null) {
            OCLanguageKindProvider.ourCache = Collections.unmodifiableList((List<? extends OCLanguageKind>)arrayList);
        }
        return OCLanguageKindProvider.ourCache;
    }
    
    static {
        EP_NAME = ExtensionPointName.create("cidr.lang.languageKindProvider");
        OCLanguageKindProvider.ourCache = null;
    }
}
