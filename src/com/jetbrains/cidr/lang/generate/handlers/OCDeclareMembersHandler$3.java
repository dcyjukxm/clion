// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.objc.OCImplementationSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.intellij.codeInsight.generation.MemberChooserObject;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import java.util.Map;
import com.jetbrains.cidr.lang.generate.OCMemberChooserObject;

class OCDeclareMembersHandler$3 extends OCMemberChooserObject {
    final /* synthetic */ Map val$parentsMap;
    final /* synthetic */ OCMemberSymbol val$symbol;
    
    @Nullable
    @Override
    public MemberChooserObject getParentNodeDelegate() {
        final OCClassSymbol ocClassSymbol = this.val$parentsMap.get(this.val$symbol);
        OCDeclareActionContext.Target target;
        if (ocClassSymbol instanceof OCImplementationSymbol) {
            target = OCDeclareActionContext.Target.IMPLEMENTATION;
        }
        else if ("".equals(ocClassSymbol.getCategoryName())) {
            target = OCDeclareActionContext.Target.PRIVATE_CATEGORY;
        }
        else {
            target = OCDeclareActionContext.Target.INTERFACE;
        }
        return (MemberChooserObject)new OCMemberChooserObject(ocClassSymbol, (target == OCDeclareActionContext.Target.IMPLEMENTATION) ? "Implementation (undeclared)" : target.getName(), ocClassSymbol.getIcon());
    }
}