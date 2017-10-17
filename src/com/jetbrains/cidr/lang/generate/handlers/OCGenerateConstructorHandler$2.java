// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers;

import org.jetbrains.annotations.Nullable;
import com.intellij.codeInsight.generation.MemberChooserObject;
import java.util.Map;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.generate.OCMemberChooserObject;

static final class OCGenerateConstructorHandler$2 extends OCMemberChooserObject {
    final /* synthetic */ OCFunctionSymbol val$symbol;
    
    @Nullable
    @Override
    public MemberChooserObject getParentNodeDelegate() {
        return (MemberChooserObject)new OCMemberChooserObject(this.val$symbol.getParent(), null);
    }
}