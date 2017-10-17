// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import java.util.List;
import com.intellij.lang.ASTNode;

public interface OCLambdaIntroducer extends OCElement
{
    ASTNode getCaptureDefault();
    
    List<OCReferenceElement> getReferencedElements();
}
