// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.evaluation;

import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.util.UserDataHolderBase;
import com.intellij.openapi.util.UserDataHolder;
import com.intellij.util.containers.FactoryMap;

class EvaluationContext$1 extends FactoryMap<String, UserDataHolder> {
    @Nullable
    protected UserDataHolder create(final String s) {
        return (UserDataHolder)new UserDataHolderBase();
    }
}