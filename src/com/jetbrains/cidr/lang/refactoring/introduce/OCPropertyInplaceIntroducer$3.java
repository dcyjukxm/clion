// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.introduce;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCImplementationSymbol;
import com.jetbrains.cidr.lang.quickfixes.OCSynthesizePropertyQuickFix;

class OCPropertyInplaceIntroducer$3 extends OCSynthesizePropertyQuickFix {
    protected Boolean generateIvars(final Project project) {
        return OCPropertyInplaceIntroducer.access$100(OCPropertyInplaceIntroducer.this).isSelected() || ApplicationManager.getApplication().isUnitTestMode();
    }
}