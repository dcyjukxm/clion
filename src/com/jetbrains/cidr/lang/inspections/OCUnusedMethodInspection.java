// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import org.jetbrains.annotations.NotNull;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.quickfixes.OCChangeGCCAttributeIntentionAction;
import com.jetbrains.cidr.lang.quickfixes.OCSafeDeleteIntentionAction;
import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.codeInspection.ProblemHighlightType;
import java.util.Collections;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.jetbrains.cidr.lang.search.OCMemberInheritorsSearch;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.search.OCElementInMacroAndNonCompiledCodeReferencesSearch;
import com.intellij.util.CommonProcessors;
import com.intellij.psi.search.SearchScope;
import com.intellij.psi.search.searches.ReferencesSearch;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.psi.search.PsiSearchHelper;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCMethodSelectorPart;
import com.jetbrains.cidr.lang.psi.impl.OCArraySelectionExpressionImpl;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.OCTestFrameworks;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import com.intellij.openapi.util.Key;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.psi.OCMethod;

public class OCUnusedMethodInspection extends OCUnusedCodeInspection
{
    @NotNull
    @Override
    public UnusedVisitor buildVisitor() {
        UnusedVisitor unusedVisitor;
        try {
            unusedVisitor = new UnusedVisitor() {
                @Override
                public void visitMethod(final OCMethod ocMethod) {
                    final OCSymbol symbol = this.getSymbol(ocMethod);
                    if (!(symbol instanceof OCMethodSymbol) || symbol.hasAttribute("unused") || symbol.hasAttribute("used")) {
                        return;
                    }
                    final OCMethodSymbol ocMethodSymbol = (OCMethodSymbol)symbol;
                    final OCFile.UnusedChecksMode unusedChecksMode = (OCFile.UnusedChecksMode)ocMethod.getProject().getUserData((Key)OCFile.UNUSED_CHECKS);
                    if (!OCSearchScope.isInProjectSources(symbol) || unusedChecksMode == OCFile.UnusedChecksMode.DISABLED) {
                        return;
                    }
                    final String selector = ocMethod.getSelector();
                    final OCMethodSymbol associatedSymbol = ocMethodSymbol.getAssociatedSymbol();
                    if ((OCTestFrameworks.isTestMethodOrFunction(ocMethodSymbol) && OCTestFrameworks.isTestClassOrStruct(ocMethodSymbol.getParent())) || ocMethodSymbol.getName().startsWith("keyPathsForValuesAffecting") || OCArraySelectionExpressionImpl.isArraySubscriptMethod(ocMethodSymbol)) {
                        return;
                    }
                    if (associatedSymbol != null) {
                        if ((!this.myOnTheFly && (ocMethodSymbol.isDefinition() || ocMethodSymbol.getGeneratedFromProperty() != null)) || associatedSymbol.hasAttribute("unused") || associatedSymbol.hasAttribute("used")) {
                            return;
                        }
                        final OCPropertySymbol generatedFromProperty = associatedSymbol.getGeneratedFromProperty();
                        if (generatedFromProperty != null) {
                            this.checkSymbolUsed((PsiElement)ocMethod, ocMethod.getParameters().get(0).getSelectorIdentifier(), generatedFromProperty, false);
                            return;
                        }
                    }
                    final Object o = (this.myScope != null) ? this.myScope : OCSearchScope.getProjectSourcesScope(ocMethod.getProject());
                    if (o instanceof GlobalSearchScope && PsiSearchHelper.SERVICE.getInstance(ocMethod.getProject()).isCheapEnoughToSearch(selector, (GlobalSearchScope)o, ocMethod.getContainingFile(), (ProgressIndicator)null) == PsiSearchHelper.SearchCostResult.TOO_MANY_OCCURRENCES) {
                        return;
                    }
                    if (ReferencesSearch.search((PsiElement)ocMethod, (SearchScope)o, false).findFirst() != null) {
                        return;
                    }
                    final CommonProcessors.FindFirstProcessor findFirstProcessor = new CommonProcessors.FindFirstProcessor();
                    new OCElementInMacroAndNonCompiledCodeReferencesSearch().execute(new ReferencesSearch.SearchParameters((PsiElement)ocMethod, (SearchScope)OCSearchScope.getProjectSourcesScope(ocMethod.getProject()), false), (Processor<OCElementInMacroAndNonCompiledCodeReferencesSearch.Usage>)findFirstProcessor);
                    if (findFirstProcessor.isFound()) {
                        return;
                    }
                    final OCMemberInheritorsSearch.SearchParameters<OCMethodSymbol> parameters = OCMemberInheritorsSearch.getParameters(ocMethod);
                    parameters.setInheritors(false);
                    parameters.setAncestors(true);
                    parameters.setIncludeFromID(true);
                    if (OCMemberInheritorsSearch.search((OCMemberInheritorsSearch.SearchParameters<OCMemberSymbol>)parameters).findFirst() == null) {
                        List<PsiElement> list = ocMethod.getSelectors();
                        if (!this.myOnTheFly && !list.isEmpty()) {
                            list = Collections.singletonList(list.get(0));
                        }
                        this.registerProblems(list, symbol.getNameWithKindUppercase() + " is never used", ProblemHighlightType.LIKE_UNUSED_SYMBOL, new OCSafeDeleteIntentionAction((PsiElement)ocMethod, symbol.getNameWithKindLowercase()), new OCChangeGCCAttributeIntentionAction.SuppressFix(ocMethodSymbol, "unused", "__unused", true));
                    }
                }
            };
            if (unusedVisitor == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCUnusedMethodInspection", "buildVisitor"));
            }
        }
        catch (IllegalStateException ex) {
            throw c(ex);
        }
        return unusedVisitor;
    }
    
    private static IllegalStateException c(final IllegalStateException ex) {
        return ex;
    }
}
