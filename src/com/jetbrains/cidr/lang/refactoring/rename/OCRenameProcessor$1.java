// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.rename;

import com.jetbrains.cidr.lang.symbols.objc.OCCompatibilityAliasSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.intellij.psi.search.SearchScope;
import java.util.Map;

class OCRenameProcessor$1 implements AssociatedElementsProcessor {
    final /* synthetic */ String val$newName;
    final /* synthetic */ Map val$allRenames;
    final /* synthetic */ SearchScope val$scope;
    
    @Override
    public boolean processIvar(final OCInstanceVariableSymbol ocInstanceVariableSymbol, final OCPropertySymbol ocPropertySymbol) {
        OCRenameProcessor.access$000(OCRenameProcessor.this, this.val$newName, this.val$allRenames, ocPropertySymbol, ocInstanceVariableSymbol);
        return true;
    }
    
    @Override
    public boolean processPropertyAccessors(final OCPropertySymbol ocPropertySymbol) {
        OCRenameProcessor.access$100(this.val$newName, this.val$allRenames, ocPropertySymbol);
        return true;
    }
    
    @Override
    public boolean processProperty(final OCPropertySymbol ocPropertySymbol, final OCSymbol ocSymbol) {
        OCRenameProcessor.access$200(OCRenameProcessor.this, this.val$newName, this.val$allRenames, ocSymbol, ocPropertySymbol);
        return true;
    }
    
    @Override
    public boolean processClassAlias(final OCClassSymbol ocClassSymbol, final OCCompatibilityAliasSymbol ocCompatibilityAliasSymbol) {
        OCRenameProcessor.access$300(this.val$newName, this.val$allRenames, ocClassSymbol, ocCompatibilityAliasSymbol);
        return true;
    }
    
    @Override
    public boolean processClass(final OCCompatibilityAliasSymbol ocCompatibilityAliasSymbol, final OCClassSymbol ocClassSymbol) {
        OCRenameProcessor.access$400(this.val$newName, this.val$allRenames, ocCompatibilityAliasSymbol, ocClassSymbol, this.val$scope);
        return true;
    }
}