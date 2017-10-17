// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import java.util.Collection;
import com.jetbrains.cidr.lang.dfa.OCControlFlowGraph;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import java.util.Collections;
import com.jetbrains.cidr.lang.quickfixes.OCAddInitializerIntentionAction;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.dfa.OCInstruction;
import com.jetbrains.cidr.lang.dfa.OCLocalDefinitionsSearcher;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.intellij.codeInsight.intention.IntentionAction;
import java.util.List;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.dfa.contextSensitive.OCContextSensitiveControlFlowBuilder;

static final class OCDFAInspection$5 implements ElementFix {
    final /* synthetic */ OCContextSensitiveControlFlowBuilder val$flowBuilder;
    
    @Override
    public List<? extends IntentionAction> getFixes(final PsiElement psiElement) {
        final OCSymbol ocSymbol = (psiElement instanceof OCReferenceElement) ? ((OCReferenceElement)psiElement).resolveToSymbol() : null;
        if (ocSymbol != null) {
            final OCCallable ocCallable = (OCCallable)PsiTreeUtil.getParentOfType(psiElement, (Class)OCCallable.class);
            final OCControlFlowGraph ocControlFlowGraph = (ocCallable != null) ? this.val$flowBuilder.getControlFlowGraph(ocCallable) : null;
            if (ocControlFlowGraph != null) {
                final OCLocalDefinitionsSearcher ocLocalDefinitionsSearcher = new OCLocalDefinitionsSearcher(ocControlFlowGraph, ocSymbol, psiElement.getParent(), false, true, true);
                ocLocalDefinitionsSearcher.process();
                final Collection<OCInstruction> instructionsOfKind = ocLocalDefinitionsSearcher.getInstructionsOfKind(OCInstruction.InstructionKind.DECLARATOR);
                if (!instructionsOfKind.isEmpty()) {
                    final OCInstruction ocInstruction = instructionsOfKind.iterator().next();
                    if (ocInstruction.getRValue() instanceof OCDeclarator) {
                        return Collections.singletonList((IntentionAction)new OCAddInitializerIntentionAction((OCDeclarator)ocInstruction.getRValue(), ocSymbol));
                    }
                }
            }
        }
        return Collections.emptyList();
    }
}