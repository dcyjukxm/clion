// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.rename;

import com.intellij.psi.PsiFileSystemItem;
import com.jetbrains.cidr.lang.OCLog;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import java.util.Collection;

public static class Conflict
{
    public final String name;
    public final Collection<PsiElement> elements;
    
    public Conflict(final String name, final Collection<PsiElement> elements) {
        this.name = name;
        this.elements = elements;
    }
    
    private static String a(final String s) {
        return StringUtil.wrapWithDoubleQuote(s);
    }
    
    @Override
    public String toString() {
        return "Trying to rename " + StringUtil.join((Collection)ContainerUtil.map((Collection)ContainerUtil.filter((Collection)this.elements, psiElement -> psiElement instanceof PsiFileSystemItem && !((PsiFileSystemItem)psiElement).getName().equals(this.name)), psiElement -> {
            OCLog.LOG.assertTrue(psiElement instanceof PsiFileSystemItem);
            return a(((PsiFileSystemItem)psiElement).getName());
        }), ", ") + " to existing file name " + a(this.name);
    }
}
