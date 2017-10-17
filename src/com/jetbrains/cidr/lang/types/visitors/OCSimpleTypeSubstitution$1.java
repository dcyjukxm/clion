// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import com.jetbrains.cidr.lang.types.OCMagicType;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.jetbrains.cidr.lang.types.OCTypeArgument;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.types.OCTypeParameterType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;

class OCSimpleTypeSubstitution$1 extends TypeSubstituteVisitor {
    final /* synthetic */ boolean val$overwriteSubstitution;
    final /* synthetic */ OCResolveContext val$context;
    
    @Override
    public OCType visitTypeParameterType(final OCTypeParameterType ocTypeParameterType) {
        final OCTypeArgument ocTypeArgument = OCSimpleTypeSubstitution.access$000(OCSimpleTypeSubstitution.this).get(ocTypeParameterType.getSymbol());
        if (ocTypeArgument == null) {
            return ocTypeParameterType;
        }
        if (ocTypeArgument instanceof OCType) {
            OCReferenceType substituteReferenceType = (OCReferenceType)ocTypeArgument;
            if (ocTypeArgument instanceof OCReferenceType) {
                substituteReferenceType = OCTypeSubstitution.substituteReferenceType((OCReferenceType)ocTypeArgument, OCSimpleTypeSubstitution.this, this.val$overwriteSubstitution, this.val$context);
            }
            return substituteReferenceType.cloneWithAddedCVQualifiers(ocTypeParameterType.getCVQualifiers(), this.val$context.getProject());
        }
        return new OCMagicType(ocTypeArgument.getNameForPresentation(OCType.Presentation.FULL, this.val$context, true, 0));
    }
}