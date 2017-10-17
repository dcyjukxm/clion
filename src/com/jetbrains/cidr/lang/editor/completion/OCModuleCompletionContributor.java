// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.intellij.psi.PsiElement;
import com.intellij.patterns.ElementPattern;
import com.intellij.codeInsight.completion.CompletionType;
import org.jetbrains.annotations.Nullable;
import icons.CidrLangIcons;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import kotlin.jvm.internal.Intrinsics;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.completion.PrefixMatcher;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.modulemap.symbols.ModuleMapModuleSymbol;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t" }, d2 = { "Lcom/jetbrains/cidr/lang/editor/completion/OCModuleCompletionContributor;", "Lcom/jetbrains/cidr/lang/editor/completion/OCCompletionContributorBase;", "()V", "createModuleLookupElement", "Lcom/intellij/codeInsight/lookup/LookupElement;", "module", "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapModuleSymbol;", "matcher", "Lcom/intellij/codeInsight/completion/PrefixMatcher;", "cidr-lang" })
public final class OCModuleCompletionContributor extends OCCompletionContributorBase
{
    @Nullable
    public final LookupElement createModuleLookupElement(@NotNull final ModuleMapModuleSymbol moduleMapModuleSymbol, @NotNull final PrefixMatcher prefixMatcher) {
        Intrinsics.checkParameterIsNotNull((Object)moduleMapModuleSymbol, "module");
        Intrinsics.checkParameterIsNotNull((Object)prefixMatcher, "matcher");
        final String qualifiedName = moduleMapModuleSymbol.getQualifiedName();
        return prefixMatcher.prefixMatches(qualifiedName) ? ((LookupElement)LookupElementBuilder.create((Object)moduleMapModuleSymbol, qualifiedName).withIcon(CidrLangIcons.Framework)) : null;
    }
    
    public OCModuleCompletionContributor() {
        final OCModuleCompletionContributor$provider.OCModuleCompletionContributor$provider$1 ocModuleCompletionContributor$provider$1 = new OCModuleCompletionContributor$provider.OCModuleCompletionContributor$provider$1(this);
        this.register(CompletionType.BASIC, (ElementPattern<? extends PsiElement>)OCCompletionPatterns.AT_IMPORT_MODULE_STATEMENT, (OCCompletionProvider)ocModuleCompletionContributor$provider$1);
        this.register(CompletionType.SMART, (ElementPattern<? extends PsiElement>)OCCompletionPatterns.AT_IMPORT_MODULE_STATEMENT, (OCCompletionProvider)ocModuleCompletionContributor$provider$1);
    }
}
