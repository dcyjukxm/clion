// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.settings;

import com.jetbrains.cidr.lang.OCLanguage;
import com.intellij.openapi.components.State;

static final class PresentableNameGetter extends State.NameGetter
{
    public String get() {
        return OCLanguage.getInstance().getDisplayName() + " Code Folding";
    }
}
