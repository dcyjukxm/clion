// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.actions;

import com.jetbrains.cidr.lang.symbols.OCSymbol;
import org.jetbrains.annotations.Nls;
import com.intellij.psi.PsiFile;
import java.util.Collection;
import com.jetbrains.cidr.lang.generate.OCGenerateUtil;
import com.intellij.codeInsight.FileModificationService;
import com.jetbrains.cidr.lang.psi.OCCallable;
import java.util.List;
import java.util.Collections;
import com.jetbrains.cidr.lang.generate.OCCaretLocation;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.OCBundle;
import com.jetbrains.cidr.lang.generate.OCCppDefinitionsUtil;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.quickfixes.OCSymbolQuickFix;

public class OCGenerateDefinitionQuickFix extends OCSymbolQuickFix<OCFunctionSymbol>
{
    private final boolean myInline;
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public OCGenerateDefinitionQuickFix(@Nullable final OCFunctionSymbol ocFunctionSymbol, final boolean myInline) {
        super(ocFunctionSymbol);
        this.myInline = myInline;
    }
    
    @Override
    protected boolean isAvailable(final OCFunctionSymbol ocFunctionSymbol) {
        try {
            if (OCCppDefinitionsUtil.shouldGenerateDefinitionsFor(ocFunctionSymbol, true) == OCCppDefinitionsUtil.SHOULD_GENERATE_DEFINITION.REQUIRED) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    @Override
    protected String getTextInternal(final OCFunctionSymbol ocFunctionSymbol) {
        try {
            if (this.myInline) {
                return OCBundle.message("generate.definitions.quickfix.inlineText", ocFunctionSymbol.getNameWithKindLowercase());
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return OCBundle.message("generate.definitions.quickfix.outsideText", ocFunctionSymbol.getNameWithKindLowercase());
    }
    
    @Override
    protected void invoke(@NotNull final OCFunctionSymbol ocFunctionSymbol) {
        try {
            if (ocFunctionSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/generate/actions/OCGenerateDefinitionQuickFix", "invoke"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        if (this.isAvailable(ocFunctionSymbol)) {
            final PsiFile containingPsiFile = ocFunctionSymbol.getContainingPsiFile();
            Label_0074: {
                try {
                    if (OCGenerateDefinitionQuickFix.$assertionsDisabled) {
                        break Label_0074;
                    }
                    final PsiFile psiFile = containingPsiFile;
                    if (psiFile == null) {
                        break Label_0074;
                    }
                    break Label_0074;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final PsiFile psiFile = containingPsiFile;
                    if (psiFile == null) {
                        throw new AssertionError();
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            final List<OCGenerateUtil.Replacement> generateDefinitionReplacements = OCCppDefinitionsUtil.getGenerateDefinitionReplacements(OCCaretLocation.byFile(containingPsiFile), OCCppDefinitionsUtil.getFunctionParent(ocFunctionSymbol), Collections.singletonList(ocFunctionSymbol), Collections.singletonList(ocFunctionSymbol.locateFunctionDefinition()), OCCppDefinitionsUtil.InlinePolicy.get(this.myInline));
            try {
                if (FileModificationService.getInstance().preparePsiElementsForWrite((Collection)OCGenerateUtil.getAffectedFiles(generateDefinitionReplacements))) {
                    OCGenerateUtil.applyReplacements(containingPsiFile.getProject(), generateDefinitionReplacements, true);
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
    }
    
    @Nls
    @NotNull
    public String getFamilyName() {
        String message;
        try {
            message = OCBundle.message("generate.definitions.intention.familyName", new Object[0]);
            if (message == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/actions/OCGenerateDefinitionQuickFix", "getFamilyName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return message;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCGenerateDefinitionQuickFix.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
