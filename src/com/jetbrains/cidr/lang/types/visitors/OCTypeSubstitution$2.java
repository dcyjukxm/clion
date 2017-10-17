// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import com.jetbrains.cidr.lang.types.OCAutoType;
import com.jetbrains.cidr.lang.types.OCTypeParameterType;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;

class OCTypeSubstitution$2 extends OCBooleanTypeVisitor {
    final /* synthetic */ OCResolveContext val$context;
    
    @Override
    public Boolean visitReferenceType(final OCReferenceType ocReferenceType) {
        return OCTypeSubstitution.this.dependsOn(ocReferenceType.getReference(this.val$context), this.val$context) || ocReferenceType.getSubstitution().dependsOn(OCTypeSubstitution.this, this.val$context);
    }
    
    @Override
    public Boolean visitStructType(final OCStructType ocStructType) {
        return OCTypeSubstitution.this.dependsOn(ocStructType.getSymbol(), this.val$context);
    }
    
    @Override
    public Boolean visitObjectType(final OCObjectType ocObjectType) {
        return OCTypeSubstitution.this.dependsOn(ocObjectType.getClassSymbol(), this.val$context);
    }
    
    @Override
    public Boolean visitTypeParameterType(final OCTypeParameterType ocTypeParameterType) {
        return OCTypeSubstitution.this.dependsOn((OCSymbol)ocTypeParameterType.getSymbol(), this.val$context);
    }
    
    @Override
    public Boolean visitAutoType(final OCAutoType ocAutoType) {
        return OCTypeSubstitution.this.dependsOn(ocAutoType.getExpressionSymbol(), this.val$context) || OCTypeSubstitution.this.dependsOn(ocAutoType.getSubstitution(), this.val$context);
    }
}