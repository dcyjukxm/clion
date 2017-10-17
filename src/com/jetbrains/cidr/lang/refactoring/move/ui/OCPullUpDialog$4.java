// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.ui;

import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.refactoring.move.OCMemberInfo;
import java.util.Collection;
import java.util.List;
import com.jetbrains.cidr.lang.psi.OCStruct;
import com.jetbrains.cidr.lang.refactoring.move.OCMoveCppProcessor;

class OCPullUpDialog$4 extends OCMoveCppProcessor {
    @Override
    protected String getCommandName() {
        return OCPullUpDialog.this.getTitle();
    }
    
    @Override
    protected boolean removeConflictingMembers() {
        return OCPullUpDialog.access$300(OCPullUpDialog.this);
    }
}