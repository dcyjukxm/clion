// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import com.jetbrains.cidr.lang.symbols.objc.OCProtocolSymbol;
import java.util.List;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import java.util.Collection;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.types.OCObjectType;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiFile;

public static class OCObjectTypeReResolver extends OCTypeCloneVisitor
{
    private PsiFile myFile;
    
    public OCObjectTypeReResolver(@Nullable final PsiFile myFile) {
        super(false);
        this.myFile = myFile;
    }
    
    @Override
    public OCType visitObjectType(final OCObjectType ocObjectType) {
        return OCReferenceType.fromText(ocObjectType.getClassName(), ContainerUtil.map((Collection)ocObjectType.getAllProtocols(), ocProtocolSymbol -> ocProtocolSymbol.getName())).resolve(this.myFile, true);
    }
}
