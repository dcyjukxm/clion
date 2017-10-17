// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.rename;

import com.jetbrains.cidr.lang.symbols.objc.OCCompatibilityAliasSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.refactoring.introduce.OCBaseInplaceIntroducer;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import javax.swing.JPanel;

class OCInplaceRenamer$1 implements OCRenameProcessor.AssociatedElementsProcessor {
    final /* synthetic */ JPanel val$panel;
    final /* synthetic */ OCCodeStyleSettings val$settings;
    
    @Override
    public boolean processIvar(final OCInstanceVariableSymbol ocInstanceVariableSymbol, final OCPropertySymbol ocPropertySymbol) {
        if (ocInstanceVariableSymbol.isClang4ImplicitIvar()) {
            return true;
        }
        OCBaseInplaceIntroducer.createCheckBox(OCInplaceRenamer.access$000(OCInplaceRenamer.this), OCInplaceRenamer.access$100(OCInplaceRenamer.this), this.val$panel, "&Rename " + ocInstanceVariableSymbol.getNameWithKindLowercase(), this.val$settings.REFACTOR_PROPERTIES_AND_IVARS, (Processor<Boolean>)(b -> ocCodeStyleSettings.REFACTOR_PROPERTIES_AND_IVARS = b));
        return false;
    }
    
    @Override
    public boolean processPropertyAccessors(final OCPropertySymbol ocPropertySymbol) {
        return false;
    }
    
    @Override
    public boolean processProperty(final OCPropertySymbol ocPropertySymbol, final OCSymbol ocSymbol) {
        if (!(ocSymbol instanceof OCInstanceVariableSymbol) || ((OCInstanceVariableSymbol)ocSymbol).isClang4ImplicitIvar()) {
            return true;
        }
        OCBaseInplaceIntroducer.createCheckBox(OCInplaceRenamer.access$200(OCInplaceRenamer.this), OCInplaceRenamer.access$300(OCInplaceRenamer.this), this.val$panel, "&Rename " + ocPropertySymbol.getNameWithKindLowercase(), this.val$settings.REFACTOR_PROPERTIES_AND_IVARS, (Processor<Boolean>)(b -> ocCodeStyleSettings.REFACTOR_PROPERTIES_AND_IVARS = b));
        return false;
    }
    
    @Override
    public boolean processClassAlias(final OCClassSymbol ocClassSymbol, final OCCompatibilityAliasSymbol ocCompatibilityAliasSymbol) {
        OCBaseInplaceIntroducer.createCheckBox(OCInplaceRenamer.access$400(OCInplaceRenamer.this), OCInplaceRenamer.access$500(OCInplaceRenamer.this), this.val$panel, "&Rename class aliases", this.val$settings.REFACTOR_PROPERTIES_AND_IVARS, (Processor<Boolean>)(b -> ocCodeStyleSettings.REFACTOR_COMPATIBILITY_ALIASES_AND_CLASSES = b));
        return false;
    }
    
    @Override
    public boolean processClass(final OCCompatibilityAliasSymbol ocCompatibilityAliasSymbol, final OCClassSymbol ocClassSymbol) {
        OCBaseInplaceIntroducer.createCheckBox(OCInplaceRenamer.access$600(OCInplaceRenamer.this), OCInplaceRenamer.access$700(OCInplaceRenamer.this), this.val$panel, "&Rename " + ocClassSymbol.getNameWithKindLowercase(), this.val$settings.REFACTOR_PROPERTIES_AND_IVARS, (Processor<Boolean>)(b -> ocCodeStyleSettings.REFACTOR_COMPATIBILITY_ALIASES_AND_CLASSES = b));
        return false;
    }
}