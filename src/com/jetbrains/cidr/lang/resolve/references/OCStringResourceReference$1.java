// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve.references;

import com.intellij.icons.AllIcons;
import com.jetbrains.cidr.lang.psi.OCStringsFile;
import com.intellij.util.ui.PlatformColors;
import com.intellij.codeInsight.lookup.LookupElementPresentation;
import com.jetbrains.cidr.lang.psi.OCLocalizedString;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementRenderer;

class OCStringResourceReference$1 extends LookupElementRenderer<LookupElement> {
    final /* synthetic */ String val$text;
    final /* synthetic */ OCLocalizedString val$pair;
    
    public void renderElement(final LookupElement lookupElement, final LookupElementPresentation lookupElementPresentation) {
        lookupElementPresentation.setItemText(this.val$text);
        lookupElementPresentation.setTailText("=" + this.val$pair.getValue(), PlatformColors.BLUE);
        lookupElementPresentation.setTypeText(((OCStringsFile)this.val$pair.getContainingFile()).getLocalizationName(), AllIcons.FileTypes.Properties);
    }
}