// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.intellij.util.Processor;
import java.util.Iterator;
import com.intellij.psi.SmartPointerManager;
import java.util.ArrayList;
import com.intellij.psi.PsiElement;
import java.util.Collection;
import com.intellij.psi.SmartPsiElementPointer;
import java.util.List;

public abstract class OCPsiElementsQuickFix extends OCQuickFix
{
    protected List<SmartPsiElementPointer> myElementPointers;
    
    protected OCPsiElementsQuickFix(final Collection<? extends PsiElement> collection) {
        if (collection != null) {
            this.myElementPointers = new ArrayList<SmartPsiElementPointer>(collection.size());
            for (final PsiElement psiElement : collection) {
                if (psiElement != null) {
                    this.myElementPointers.add(SmartPointerManager.getInstance(psiElement.getProject()).createSmartPsiElementPointer(psiElement));
                }
            }
        }
    }
    
    protected OCPsiElementsQuickFix(final PsiElement... array) {
        this.myElementPointers = new ArrayList<SmartPsiElementPointer>();
        for (final PsiElement psiElement : array) {
            this.myElementPointers.add(SmartPointerManager.getInstance(psiElement.getProject()).createSmartPsiElementPointer(psiElement));
        }
    }
    
    @Override
    public boolean isAvailable() {
        final boolean[] array = { false };
        return this.processElements((Processor<PsiElement>)(psiElement -> {
            array[0] = true;
            return OCCodeInsightUtil.isValid(psiElement);
        })) && array[0];
    }
    
    protected boolean processElements(final Processor<PsiElement> processor) {
        final Iterator<SmartPsiElementPointer> iterator = this.myElementPointers.iterator();
        while (iterator.hasNext()) {
            final PsiElement element = iterator.next().getElement();
            if (element != null && !processor.process((Object)element)) {
                return false;
            }
        }
        return true;
    }
    
    public List<? extends PsiElement> getElements() {
        final ArrayList<PsiElement> list = new ArrayList<PsiElement>();
        final Iterator<SmartPsiElementPointer> iterator = this.myElementPointers.iterator();
        while (iterator.hasNext()) {
            final PsiElement element = iterator.next().getElement();
            if (element != null) {
                list.add(element);
            }
        }
        return list;
    }
}
