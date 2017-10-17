// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import com.jetbrains.cidr.lang.types.OCAutoType;
import java.util.Iterator;
import com.jetbrains.cidr.lang.symbols.OCSymbolReference;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.intellij.psi.PsiFile;

public class OCTypeParameterResolveVisitor extends OCNonPrimitiveTypeCloneVisitor
{
    private final PsiFile myContext;
    
    public OCTypeParameterResolveVisitor(final PsiFile myContext) {
        this.myContext = myContext;
    }
    
    @Override
    public OCType visitStructType(final OCStructType ocStructType) {
        return ocStructType;
    }
    
    @Override
    public OCType visitReferenceType(final OCReferenceType ocReferenceType) {
        final OCSymbolReference reference = ocReferenceType.getReference(this.myContext);
        final OCTypeSubstitution substitution = ocReferenceType.getSubstitution();
        if (this.myContext != null && substitution != OCTypeSubstitution.ID) {
            final Iterator<String> iterator = reference.getQualifiedName().flatten().iterator();
            while (iterator.hasNext()) {
                if (substitution.hasSubstitutionForName(iterator.next())) {
                    return ocReferenceType.resolve(this.myContext);
                }
            }
        }
        return ocReferenceType;
    }
    
    @Override
    public OCType visitAutoType(final OCAutoType ocAutoType) {
        return ocAutoType.resolve(this.myContext);
    }
}
