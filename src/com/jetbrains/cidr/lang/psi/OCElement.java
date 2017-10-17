// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;

public interface OCElement extends PsiElement
{
    OCFile getContainingOCFile();
    
    String getTextWithMacros();
    
    TextRange getRangeWithMacros();
    
    boolean isEmpty();
    
    int getTextOffset();
    
    long getComplexOffset();
}
