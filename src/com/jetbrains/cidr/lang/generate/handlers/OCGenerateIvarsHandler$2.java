// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers;

import java.util.Collection;
import java.util.ArrayList;
import com.intellij.openapi.application.ApplicationManager;
import org.jetbrains.annotations.Nullable;
import com.intellij.usageView.UsageInfo;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.intellij.util.containers.MultiMap;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.jetbrains.cidr.lang.quickfixes.OCReleaseVariablesIntentionAction;
import java.util.Map;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.refactoring.OCConvertMemberRefactoringProcessor;

class OCGenerateIvarsHandler$2 extends OCConvertMemberRefactoringProcessor<OCPropertySymbol> {
    @Override
    protected boolean showConflicts(@NotNull final MultiMap<PsiElement, String> multiMap, @Nullable final UsageInfo[] array) {
        try {
            if (multiMap == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "conflicts", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateIvarsHandler$2", "showConflicts"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (ApplicationManager.getApplication().isUnitTestMode()) {
                OCGenerateIvarsHandler.this.passConflictsToTest(new ArrayList<String>(multiMap.values()));
                this.prepareSuccessful();
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return super.showConflicts(multiMap, array);
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}