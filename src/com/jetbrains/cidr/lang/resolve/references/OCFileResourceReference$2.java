// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve.references;

import com.intellij.icons.AllIcons;
import com.intellij.codeInsight.lookup.LookupElementPresentation;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementRenderer;

class OCFileResourceReference$2 extends LookupElementRenderer<LookupElement> {
    final /* synthetic */ String val$text;
    final /* synthetic */ VirtualFile val$virtualFile;
    
    public void renderElement(final LookupElement lookupElement, final LookupElementPresentation lookupElementPresentation) {
        lookupElementPresentation.setIcon(AllIcons.FileTypes.UiForm);
        lookupElementPresentation.setItemText(this.val$text);
        lookupElementPresentation.setTypeText(this.val$virtualFile.getParent().getName());
    }
}