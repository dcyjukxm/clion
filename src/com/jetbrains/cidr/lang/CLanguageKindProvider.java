// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang;

import java.util.Arrays;
import java.util.List;

public class CLanguageKindProvider extends OCLanguageKindProvider
{
    @Override
    protected List<OCLanguageKind> getLanguageKinds() {
        return (List<OCLanguageKind>)Arrays.asList(CLanguageKind.values());
    }
}
