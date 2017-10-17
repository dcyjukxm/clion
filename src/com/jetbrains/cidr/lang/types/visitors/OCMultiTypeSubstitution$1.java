// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import com.jetbrains.cidr.lang.types.OCTypeArgument;
import java.util.Iterator;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.jetbrains.cidr.lang.types.OCMagicType;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.types.OCTypeParameterType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;

class OCMultiTypeSubstitution$1 extends TypeSubstituteVisitor {
    final /* synthetic */ OCResolveContext val$context;
    final /* synthetic */ boolean val$overwriteSubstitution;
    
    @Override
    public OCType visitTypeParameterType(final OCTypeParameterType ocTypeParameterType) {
        OCType substitute = null;
        for (final OCSimpleTypeSubstitution ocSimpleTypeSubstitution : OCMultiTypeSubstitution.access$000(OCMultiTypeSubstitution.this)) {
            if (substitute != null) {
                substitute = ocSimpleTypeSubstitution.substitute(substitute, this.val$context);
            }
            else {
                final OCTypeArgument substitution = ocSimpleTypeSubstitution.getSubstitutionFor(ocTypeParameterType.getSymbol());
                if (substitution == null) {
                    continue;
                }
                if (!(substitution instanceof OCType)) {
                    return new OCMagicType(substitution.getNameForPresentation(OCType.Presentation.FULL, this.val$context, true, 0));
                }
                if (substitution instanceof OCReferenceType) {
                    return OCTypeSubstitution.substituteReferenceType((OCReferenceType)substitution, OCMultiTypeSubstitution.this, this.val$overwriteSubstitution, this.val$context).cloneWithAddedCVQualifiers(ocTypeParameterType.getCVQualifiers(), this.val$context.getProject());
                }
                substitute = (OCReferenceType)substitution;
            }
        }
        return (substitute != null) ? substitute.cloneWithAddedCVQualifiers(ocTypeParameterType.getCVQualifiers(), this.val$context.getProject()) : ocTypeParameterType;
    }
}