// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.rename;

import com.intellij.openapi.util.TextRange;
import com.intellij.refactoring.util.NonCodeUsageInfo;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import java.util.List;
import java.util.Iterator;
import com.intellij.refactoring.rename.RenameViewDescriptor;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import java.util.LinkedHashMap;
import com.intellij.usageView.UsageViewDescriptor;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.search.OCElementInMacroAndNonCompiledCodeReferencesSearch;
import com.intellij.psi.search.searches.ReferencesSearch;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import org.jetbrains.annotations.NotNull;
import com.intellij.usageView.UsageInfo;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.refactoring.rename.RenameProcessor;

class OCInplaceRenamer$2 extends RenameProcessor {
    private OCRenameProcessor.RenameUsages myOption;
    final /* synthetic */ PsiElement val$element;
    
    @Override
    public boolean preprocessUsages(@NotNull final Ref<UsageInfo[]> ref) {
        try {
            if (ref == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "refUsages", "com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer$2", "preprocessUsages"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (!super.preprocessUsages(ref)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            this.myOption = OCRenameProcessor.validateUsages(OCInplaceRenamer.access$800(OCInplaceRenamer.this), ref);
            if (this.myOption != OCRenameProcessor.RenameUsages.CANCEL) {
                return true;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return false;
    }
    
    @Override
    protected boolean isPreviewUsages(@NotNull final UsageInfo[] array) {
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "usages", "com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer$2", "isPreviewUsages"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (this.myOption == OCRenameProcessor.RenameUsages.SHOW_USAGES) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return false;
    }
    
    @NotNull
    @Override
    public UsageInfo[] findUsages() {
        final ArrayList list = new ArrayList((Collection<? extends E>)Arrays.asList(super.findUsages()));
        final ReferencesSearch.SearchParameters searchParameters = new ReferencesSearch.SearchParameters(this.val$element, this.val$element.getUseScope(), false);
        UsageInfo[] array;
        try {
            new OCElementInMacroAndNonCompiledCodeReferencesSearch().execute(searchParameters, (Processor<OCElementInMacroAndNonCompiledCodeReferencesSearch.Usage>)(usage -> {
                final PsiElement element = usage.getElement();
                if (OCElementUtil.getElementType(element) == OCTokenTypes.CONDITIONALLY_NON_COMPILED_COMMENT) {
                    final TextRange textRange = usage.getTextRange();
                    list.add(NonCodeUsageInfo.create(element.getContainingFile(), textRange.getStartOffset(), textRange.getEndOffset(), psiElement, s));
                }
                return true;
            }));
            array = (UsageInfo[])list.toArray(new UsageInfo[list.size()]);
            if (array == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer$2", "findUsages"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return array;
    }
    
    @NotNull
    @Override
    protected UsageViewDescriptor createUsageViewDescriptor(@NotNull final UsageInfo[] array) {
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "usages", "com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer$2", "createUsageViewDescriptor"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final LinkedHashMap<OCSymbolDeclarator<OCSymbol>, String> linkedHashMap = new LinkedHashMap<OCSymbolDeclarator<OCSymbol>, String>();
        for (final PsiElement psiElement : this.myAllRenames.keySet()) {
            Label_0113: {
                try {
                    if (!(psiElement instanceof OCSymbolDeclarator)) {
                        continue;
                    }
                    final OCSymbolDeclarator<OCSymbol> ocSymbolDeclarator = (OCSymbolDeclarator<OCSymbol>)psiElement;
                    final OCSymbolDeclarator<OCSymbol> ocSymbolDeclarator2 = ocSymbolDeclarator;
                    final OCSymbol ocSymbol = ocSymbolDeclarator2.getSymbol();
                    if (ocSymbol != null) {
                        break Label_0113;
                    }
                    continue;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final OCSymbolDeclarator<OCSymbol> ocSymbolDeclarator = (OCSymbolDeclarator<OCSymbol>)psiElement;
                    final OCSymbolDeclarator<OCSymbol> ocSymbolDeclarator2 = ocSymbolDeclarator;
                    final OCSymbol ocSymbol = ocSymbolDeclarator2.getSymbol();
                    if (ocSymbol == null) {
                        continue;
                    }
                    linkedHashMap.put((OCSymbolDeclarator<OCSymbol>)psiElement, this.myAllRenames.get(psiElement));
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
        }
        RenameViewDescriptor renameViewDescriptor;
        try {
            renameViewDescriptor = new RenameViewDescriptor((LinkedHashMap<PsiElement, String>)linkedHashMap);
            if (renameViewDescriptor == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer$2", "createUsageViewDescriptor"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return (UsageViewDescriptor)renameViewDescriptor;
    }
    
    @Override
    public void doRun() {
        try {
            super.doRun();
        }
        finally {
            OCInplaceRenamer.access$900(OCInplaceRenamer.this);
        }
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}