// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search;

import com.intellij.psi.search.SearchScope;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.intellij.psi.search.searches.ReferencesSearch;

public static class MySearchParameters extends ReferencesSearch.SearchParameters
{
    private boolean mySearchForRenameConflicts;
    
    public MySearchParameters(@NotNull final PsiElement psiElement, final SearchScope searchScope, final boolean b, final boolean mySearchForRenameConflicts) {
        if (psiElement == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "elementToSearch", "com/jetbrains/cidr/lang/search/OCElementInMacroSubstitutionReferenceSearch$MySearchParameters", "<init>"));
        }
        super(psiElement, searchScope, b);
        this.mySearchForRenameConflicts = mySearchForRenameConflicts;
    }
    
    public boolean searchForRenameConflicts() {
        return this.mySearchForRenameConflicts;
    }
}
