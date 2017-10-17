// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers.implement;

import java.util.Collection;
import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;
import com.jetbrains.cidr.lang.generate.actions.OCActionContext;
import com.jetbrains.cidr.lang.generate.actions.OCObjCActionContext;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.intellij.util.Processor;
import java.util.HashSet;
import com.intellij.openapi.util.Condition;
import java.util.Iterator;
import com.intellij.featureStatistics.FeatureUsageTracker;
import com.jetbrains.cidr.lang.psi.OCImplementation;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.generate.actions.OCOverrideImplementActionContext;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.generate.handlers.OCObjCClassTextActionHandlerBase;

public abstract class OCOverrideImplementMethodsHandlerBase extends OCObjCClassTextActionHandlerBase<OCMethodSymbol, OCOverrideImplementActionContext>
{
    static final /* synthetic */ boolean $assertionsDisabled;
    
    @Override
    protected String getActionTitle() {
        return "Override/Implement Methods";
    }
    
    @NotNull
    @Override
    protected OCOverrideImplementActionContext evaluateActionContext(final OCClassSymbol ocClassSymbol, final PsiElement psiElement) {
        OCOverrideImplementActionContext ocOverrideImplementActionContext;
        try {
            ocOverrideImplementActionContext = new OCOverrideImplementActionContext(ocClassSymbol, ocClassSymbol.getResolvedType(true), psiElement);
            if (ocOverrideImplementActionContext == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementMethodsHandlerBase", "evaluateActionContext"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return ocOverrideImplementActionContext;
    }
    
    @NotNull
    @Override
    protected String getInsertText(@NotNull final PsiElement psiElement, @Nullable final PsiElement psiElement2, @NotNull final List<OCMethodSymbol> list, @NotNull final OCOverrideImplementActionContext ocOverrideImplementActionContext) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementMethodsHandlerBase", "getInsertText"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "methods", "com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementMethodsHandlerBase", "getInsertText"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        try {
            if (ocOverrideImplementActionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementMethodsHandlerBase", "getInsertText"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        Label_0153: {
            try {
                if (OCOverrideImplementMethodsHandlerBase.$assertionsDisabled) {
                    break Label_0153;
                }
                final PsiElement psiElement3 = psiElement;
                final boolean b = psiElement3 instanceof OCImplementation;
                if (!b) {
                    break Label_0153;
                }
                break Label_0153;
            }
            catch (IllegalStateException ex4) {
                throw a(ex4);
            }
            try {
                final PsiElement psiElement3 = psiElement;
                final boolean b = psiElement3 instanceof OCImplementation;
                if (!b) {
                    throw new AssertionError();
                }
            }
            catch (IllegalStateException ex5) {
                throw a(ex5);
            }
        }
        final StringBuilder sb = new StringBuilder();
        final Iterator<OCMethodSymbol> iterator = list.iterator();
        while (iterator.hasNext()) {
            sb.append(this.generateMethodText(iterator.next(), psiElement.getLastChild(), ocOverrideImplementActionContext));
        }
        String string;
        try {
            FeatureUsageTracker.getInstance().triggerFeatureUsed("codeassists.overrideimplement");
            string = sb.toString();
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementMethodsHandlerBase", "getInsertText"));
            }
        }
        catch (IllegalStateException ex6) {
            throw a(ex6);
        }
        return string;
    }
    
    @NotNull
    protected Condition<OCMethodSymbol> getCandidatesFilter(@NotNull final OCOverrideImplementActionContext ocOverrideImplementActionContext) {
        try {
            if (ocOverrideImplementActionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementMethodsHandlerBase", "getCandidatesFilter"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final HashSet set = new HashSet();
        Condition condition;
        try {
            ocOverrideImplementActionContext.getImplementationSymbol().processMembers(OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMethodSymbol>)(ocMethodSymbol -> {
                set.add(ocMethodSymbol.getSignature());
                return true;
            }));
            condition = (ocMethodSymbol -> {
                try {
                    if (!set.contains(ocMethodSymbol.getSignature())) {
                        return true;
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                return false;
            });
            if (condition == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementMethodsHandlerBase", "getCandidatesFilter"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return (Condition<OCMethodSymbol>)condition;
    }
    
    @Override
    protected int getInsertPosition(final PsiElement psiElement, int startOffset, PsiElement bestMemberPlace, final List<OCMethodSymbol> list, final OCOverrideImplementActionContext ocOverrideImplementActionContext) {
        final OCMethodSymbol ocMethodSymbol = list.get(0);
        final OCClassDeclaration ocClassDeclaration = (OCClassDeclaration)psiElement;
        OCClassDeclaration ocClassDeclaration2 = null;
        Label_0101: {
            Label_0066: {
                try {
                    if (bestMemberPlace != null) {
                        if (startOffset >= ocClassDeclaration.getMethodsStartOffset(true)) {
                            break Label_0066;
                        }
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                bestMemberPlace = ocClassDeclaration.getBestMemberPlace(ocMethodSymbol);
                if (bestMemberPlace != null) {
                    startOffset = bestMemberPlace.getTextRange().getStartOffset();
                }
                try {
                    ocClassDeclaration2 = ocClassDeclaration;
                    if (list.size() != 1) {
                        break Label_0101;
                    }
                    final OCMethodSymbol ocMethodSymbol2 = ocMethodSymbol;
                    final String s = ocMethodSymbol2.getName();
                    final String s2 = "init";
                    final boolean b = s.startsWith(s2);
                    if (b) {
                        break Label_0101;
                    }
                    break Label_0101;
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
            }
            try {
                final OCMethodSymbol ocMethodSymbol2 = ocMethodSymbol;
                final String s = ocMethodSymbol2.getName();
                final String s2 = "init";
                final boolean b = s.startsWith(s2);
                if (b) {
                    final boolean b2 = true;
                    return ocClassDeclaration2.getMethodsInsertPosition(b2, bestMemberPlace, startOffset);
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
        }
        final boolean b2 = false;
        return ocClassDeclaration2.getMethodsInsertPosition(b2, bestMemberPlace, startOffset);
    }
    
    protected abstract String generateMethodText(final OCMethodSymbol p0, final PsiElement p1, final OCOverrideImplementActionContext p2);
    
    @Override
    protected boolean shouldSelectResult(@NotNull final OCBlockStatement ocBlockStatement) {
        try {
            if (ocBlockStatement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "body", "com/jetbrains/cidr/lang/generate/handlers/implement/OCOverrideImplementMethodsHandlerBase", "shouldSelectResult"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return true;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCOverrideImplementMethodsHandlerBase.class.desiredAssertionStatus()) {
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
