// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.liveTemplates;

import com.jetbrains.cidr.lang.editor.completion.OCCodeContextType;
import com.intellij.codeInsight.template.TemplateContextType;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import org.jetbrains.annotations.Nullable;
import java.util.Iterator;
import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.psi.PsiFile;
import java.util.Collection;
import com.intellij.psi.util.PsiUtilCore;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.psi.OCForeachStatement;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.jetbrains.cidr.lang.psi.OCExpression;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.OCIcons;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.psi.PsiNamedElement;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.intellij.codeInsight.template.Result;
import com.intellij.codeInsight.template.ExpressionContext;
import com.intellij.codeInsight.template.Expression;
import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.template.Macro;

public class OCFastEnumerationVariableMacro extends Macro
{
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public String getName() {
        return "fastEnumeration";
    }
    
    public String getPresentableName() {
        return "fastEnumeration()";
    }
    
    @NotNull
    public String getDefaultValue() {
        String s;
        try {
            s = "a";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/liveTemplates/OCFastEnumerationVariableMacro", "getDefaultValue"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    public Result calculateResult(@NotNull final Expression[] array, final ExpressionContext expressionContext) {
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "params", "com/jetbrains/cidr/lang/editor/liveTemplates/OCFastEnumerationVariableMacro", "calculateResult"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final PsiElement[] variables = getVariables(array, expressionContext);
        Label_0066: {
            try {
                if (variables == null) {
                    break Label_0066;
                }
                final PsiElement[] array2 = variables;
                final int n = array2.length;
                if (n == 0) {
                    break Label_0066;
                }
                return (Result)new OCElementResult((OCElement)variables[0]);
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            try {
                final PsiElement[] array2 = variables;
                final int n = array2.length;
                if (n == 0) {
                    return null;
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
        }
        return (Result)new OCElementResult((OCElement)variables[0]);
    }
    
    public LookupElement[] calculateLookupItems(@NotNull final Expression[] array, final ExpressionContext expressionContext) {
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "params", "com/jetbrains/cidr/lang/editor/liveTemplates/OCFastEnumerationVariableMacro", "calculateLookupItems"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final PsiElement[] variables = getVariables(array, expressionContext);
        Label_0067: {
            try {
                if (variables == null) {
                    break Label_0067;
                }
                final PsiElement[] array2 = variables;
                final int n = array2.length;
                final int n2 = 2;
                if (n < n2) {
                    break Label_0067;
                }
                break Label_0067;
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            try {
                final PsiElement[] array2 = variables;
                final int n = array2.length;
                final int n2 = 2;
                if (n < n2) {
                    return null;
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
        }
        final LookupElement[] array3 = new LookupElement[variables.length];
        for (int i = 0; i < variables.length; ++i) {
            final PsiElement psiElement = variables[i];
            try {
                if (psiElement instanceof PsiNamedElement) {
                    array3[i] = (LookupElement)LookupElementBuilder.create((PsiNamedElement)psiElement).withIcon(psiElement.getIcon(0));
                    continue;
                }
            }
            catch (IllegalStateException ex4) {
                throw a(ex4);
            }
            array3[i] = (LookupElement)LookupElementBuilder.create(psiElement.getText()).withIcon(OCIcons.CodeAssistantMember);
        }
        return array3;
    }
    
    @Nullable
    protected static PsiElement[] getVariables(final Expression[] array, final ExpressionContext expressionContext) {
        try {
            if (array.length != 0) {
                return null;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final ArrayList<OCExpression> list = new ArrayList<OCExpression>();
        final PsiElement insertionPlace = OCTemplatesUtil.getInsertionPlace(expressionContext);
        final PsiFile containingFile = insertionPlace.getContainingFile();
        Label_0050: {
            try {
                if (OCFastEnumerationVariableMacro.$assertionsDisabled) {
                    break Label_0050;
                }
                final PsiFile psiFile = containingFile;
                if (psiFile == null) {
                    break Label_0050;
                }
                break Label_0050;
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            try {
                final PsiFile psiFile = containingFile;
                if (psiFile == null) {
                    throw new AssertionError();
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
        }
        final OCType resolvedFromText = OCReferenceType.resolvedFromText("id", "NSFastEnumeration", containingFile);
        final OCPointerType to = OCPointerType.to(OCReferenceType.resolvedFromText("NSDictionary", containingFile));
        for (final OCDeclaratorSymbol ocDeclaratorSymbol : OCTemplatesUtil.getVariablesVisibleAt(insertionPlace, false)) {
            final OCType a = a(ocDeclaratorSymbol, containingFile);
            try {
                if (!a.isPointerToObject() || !resolvedFromText.isCompatible(a, insertionPlace)) {
                    continue;
                }
            }
            catch (IllegalStateException ex4) {
                throw a(ex4);
            }
            final PsiNameIdentifierOwner psiNameIdentifierOwner = ocDeclaratorSymbol.locateDefinition();
            try {
                if (psiNameIdentifierOwner == null) {
                    continue;
                }
            }
            catch (IllegalStateException ex5) {
                throw a(ex5);
            }
            final PsiElement parent = psiNameIdentifierOwner.getParent().getParent();
            try {
                if (parent instanceof OCForeachStatement) {
                    continue;
                }
            }
            catch (IllegalStateException ex6) {
                throw a(ex6);
            }
            Label_0244: {
                try {
                    list.add(psiNameIdentifierOwner);
                    if (a.isPointerToID()) {
                        continue;
                    }
                    final OCPointerType ocPointerType = to;
                    final OCType ocType = a;
                    final PsiElement psiElement = insertionPlace;
                    final boolean b = ocPointerType.isCompatible(ocType, psiElement);
                    if (b) {
                        break Label_0244;
                    }
                    continue;
                }
                catch (IllegalStateException ex7) {
                    throw a(ex7);
                }
                try {
                    final OCPointerType ocPointerType = to;
                    final OCType ocType = a;
                    final PsiElement psiElement = insertionPlace;
                    final boolean b = ocPointerType.isCompatible(ocType, psiElement);
                    if (!b) {
                        continue;
                    }
                    list.add((PsiNameIdentifierOwner)OCElementFactory.expressionFromText("[" + ocDeclaratorSymbol.getName() + " allKeys]", (PsiElement)containingFile));
                    list.add((PsiNameIdentifierOwner)OCElementFactory.expressionFromText("[" + ocDeclaratorSymbol.getName() + " allValues]", (PsiElement)containingFile));
                }
                catch (IllegalStateException ex8) {
                    throw a(ex8);
                }
            }
        }
        return PsiUtilCore.toPsiElementArray((Collection)list);
    }
    
    private static OCType a(final OCDeclaratorSymbol ocDeclaratorSymbol, final PsiFile psiFile) {
        final OCType resolvedType = ocDeclaratorSymbol.getResolvedType();
        try {
            if (!resolvedType.isPointerToID() || ocDeclaratorSymbol.getKind() != OCSymbolKind.LOCAL_VARIABLE) {
                return resolvedType;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final PsiNameIdentifierOwner psiNameIdentifierOwner = ocDeclaratorSymbol.locateDefinition();
        if (psiNameIdentifierOwner instanceof OCDeclarator) {
            final OCExpression initializer = ((OCDeclarator)psiNameIdentifierOwner).getInitializer();
            try {
                if (initializer != null) {
                    return initializer.getType().getGuessedType().resolve(psiFile);
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        return resolvedType;
    }
    
    public boolean isAcceptableInContext(final TemplateContextType templateContextType) {
        return templateContextType instanceof OCCodeContextType;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCFastEnumerationVariableMacro.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
