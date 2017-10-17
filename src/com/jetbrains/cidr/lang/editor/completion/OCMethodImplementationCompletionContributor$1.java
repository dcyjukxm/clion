// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.psi.OCTypeElement;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.symbols.objc.OCImplementationSymbol;
import java.util.Iterator;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.util.Conditions;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.types.OCUnknownType;
import com.intellij.psi.PsiFile;
import com.intellij.codeInsight.completion.CompletionType;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.intellij.util.Processor;
import java.util.HashSet;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.types.OCObjectTypeContext;
import com.jetbrains.cidr.lang.psi.OCImplementation;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.psi.OCMethodSelectorPart;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

class OCMethodImplementationCompletionContributor$1 extends OCCompletionProvider {
    @Override
    protected void addCompletions(final String s, @NotNull final OCCompletionParameters ocCompletionParameters, final ProcessingContext processingContext, final CompletionResultSet set) {
        try {
            if (ocCompletionParameters == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameters", "com/jetbrains/cidr/lang/editor/completion/OCMethodImplementationCompletionContributor$1", "addCompletions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final PsiElement parent = ocCompletionParameters.getPosition().getParent();
        if (parent instanceof OCMethodSelectorPart) {
            final OCMethod ocMethod = (OCMethod)parent.getParent();
            final StringBuilder sb = new StringBuilder();
            for (final OCMethodSelectorPart ocMethodSelectorPart : ocMethod.getParameters()) {
                try {
                    if (ocMethodSelectorPart == parent) {
                        break;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                sb.append(ocMethodSelectorPart.getSelectorPart());
            }
            final String string = sb.toString();
            final PsiElement parent2 = ocMethod.getParent();
            if (parent2 instanceof OCImplementation) {
                final OCImplementation ocImplementation = (OCImplementation)parent2;
                final OCImplementationSymbol symbol = ocImplementation.getSymbol();
                final OCObjectType type = ocImplementation.getType();
                try {
                    if (type == null) {
                        return;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                boolean b = false;
                Label_0232: {
                    try {
                        if (!ocMethod.isInstanceMethod()) {
                            b = true;
                            break Label_0232;
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                    b = false;
                }
                final OCObjectTypeContext ocObjectTypeContext = new OCObjectTypeContext(b, true, type, null);
                final HashSet set2 = new HashSet();
                try {
                    if (symbol != null) {
                        symbol.processMembers(null, (Processor<OCMemberSymbol>)(ocMemberSymbol -> {
                            set2.add(ocMemberSymbol.getSignature());
                            return true;
                        }));
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
                Condition and = ocMethodSymbol -> {
                    try {
                        if (!set2.contains(ocMethodSymbol.getSignature())) {
                            return true;
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    return false;
                };
                if (ocCompletionParameters.getCompletionType() == CompletionType.SMART) {
                    final OCTypeElement returnTypeElement = ocMethod.getReturnTypeElement();
                    if (returnTypeElement != null) {
                        final OCFile containingOCFile = ocMethod.getContainingOCFile();
                        final OCType resolve = returnTypeElement.getType().resolve((PsiFile)containingOCFile);
                        if (resolve != OCUnknownType.INSTANCE) {
                            and = Conditions.and(and, (Condition)new OCSmartCompletionContributor.TypeMatchingCondition(containingOCFile, parent, resolve, false, null));
                        }
                    }
                }
                OCMethodImplementationCompletionContributor.access$000(set, ocCompletionParameters, false, new MethodSelectorCompletionContributor.Context(string, null, ocObjectTypeContext, (Condition<OCMethodSymbol>)and));
            }
        }
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}