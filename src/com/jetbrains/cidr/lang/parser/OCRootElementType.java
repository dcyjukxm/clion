// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;

public interface OCRootElementType
{
    Language getLanguage();
    
    ASTNode doParse(final ASTNode p0, final PsiBuilder p1);
}
