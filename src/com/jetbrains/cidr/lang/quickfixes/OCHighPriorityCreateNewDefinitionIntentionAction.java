// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCSymbolContext;
import com.intellij.codeInsight.intention.HighPriorityAction;

public class OCHighPriorityCreateNewDefinitionIntentionAction extends OCCreateNewDefinitionIntentionAction implements HighPriorityAction
{
    public OCHighPriorityCreateNewDefinitionIntentionAction(final OCSymbolContext ocSymbolContext, final PsiElement psiElement, final String s) {
        super(ocSymbolContext, psiElement, s);
    }
    
    public static boolean isHighPrioritySymbolKind(final OCSymbolKind ocSymbolKind) {
        return ocSymbolKind == OCSymbolKind.LOCAL_VARIABLE || ocSymbolKind.isFunctionOrConstructor();
    }
}
