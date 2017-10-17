// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import java.util.List;
import java.util.Collection;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.types.OCAutoType;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;

protected static class TypeSubstituteVisitor extends OCNonPrimitiveTypeCloneVisitor
{
    private OCTypeSubstitution mySubstitution;
    @NotNull
    private OCResolveContext myContext;
    private boolean myOverwriteSubstitution;
    
    TypeSubstituteVisitor(final OCTypeSubstitution mySubstitution, final boolean myOverwriteSubstitution, @NotNull final OCResolveContext myContext) {
        if (myContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution$TypeSubstituteVisitor", "<init>"));
        }
        this.mySubstitution = mySubstitution;
        this.myOverwriteSubstitution = myOverwriteSubstitution;
        this.myContext = myContext;
    }
    
    @Override
    public OCType visitReferenceType(final OCReferenceType ocReferenceType) {
        return OCTypeSubstitution.substituteReferenceType(ocReferenceType, this.mySubstitution, this.myOverwriteSubstitution, this.myContext);
    }
    
    @Override
    public OCType visitAutoType(final OCAutoType ocAutoType) {
        return new OCAutoType(ocAutoType, this.mySubstitution, this.myContext);
    }
    
    @Override
    public OCType visitStructType(final OCStructType ocStructType) {
        return new OCStructType(ContainerUtil.map((Collection)ocStructType.getStructs(), ocStructSymbol -> this.mySubstitution.substitute(ocStructSymbol, this.myContext)), ocStructType.getTypedefName(), ocStructType.isConst(), ocStructType.isVolatile(), ocStructType.getArguments());
    }
}
