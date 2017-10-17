// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import com.jetbrains.cidr.lang.types.OCExpansionPackType;
import com.jetbrains.cidr.lang.types.OCVariadicType;
import com.jetbrains.cidr.lang.types.OCAutoType;
import com.jetbrains.cidr.lang.types.OCTypeParameterType;
import com.jetbrains.cidr.lang.types.OCVoidType;
import com.jetbrains.cidr.lang.types.OCUnknownType;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.jetbrains.cidr.lang.types.OCRealType;
import com.jetbrains.cidr.lang.types.OCIntType;
import com.jetbrains.cidr.lang.types.OCIdType;
import com.jetbrains.cidr.lang.types.OCCppReferenceType;
import com.jetbrains.cidr.lang.types.OCBlockPointerType;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.types.OCArrayType;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.types.OCMagicType;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import com.jetbrains.cidr.lang.types.OCEllipsisType;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.types.OCTypeOwner;
import com.jetbrains.cidr.lang.types.OCType;

private static class OCTypeCompatibilityVisitorCreator implements OCTypeVisitor<OCTypeCompatibilityVisitor<? extends OCType>>
{
    private final OCTypeOwner mySource;
    @Nullable
    private final PsiElement myContext;
    private final boolean myAllowImplicitConversions;
    private final boolean myAssumeNullSubstitutionsEquals;
    @NotNull
    private final OCResolveContext myResolveContext;
    
    public OCTypeCompatibilityVisitorCreator(@Nullable final OCTypeOwner mySource, @Nullable final PsiElement myContext, final boolean myAllowImplicitConversions, final boolean myAssumeNullSubstitutionsEquals, @NotNull final OCResolveContext myResolveContext) {
        if (myResolveContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "resolveContext", "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor$OCTypeCompatibilityVisitorCreator", "<init>"));
        }
        this.mySource = mySource;
        this.myContext = myContext;
        this.myAllowImplicitConversions = myAllowImplicitConversions;
        this.myAssumeNullSubstitutionsEquals = myAssumeNullSubstitutionsEquals;
        this.myResolveContext = myResolveContext;
    }
    
    @Override
    public OCTypeCompatibilityVisitor<? extends OCType> visitEllipsisReferenceType(final OCEllipsisType ocEllipsisType) {
        return new OCTypeCompatibilityVisitor_OCEllipsisType(ocEllipsisType, this.mySource, this.myContext, this.myAllowImplicitConversions, this.myAssumeNullSubstitutionsEquals, this.myResolveContext);
    }
    
    @Override
    public OCTypeCompatibilityVisitor<? extends OCType> visitFunctionType(final OCFunctionType ocFunctionType) {
        return new OCTypeCompatibilityVisitor_OCFunctionType(ocFunctionType, this.mySource, this.myContext, this.myAllowImplicitConversions, this.myAssumeNullSubstitutionsEquals, this.myResolveContext);
    }
    
    @Override
    public OCTypeCompatibilityVisitor<? extends OCType> visitMagicType(final OCMagicType ocMagicType) {
        return new OCTypeCompatibilityVisitor_AlwaysOk<OCType>((OCType)ocMagicType, this.mySource, this.myContext, this.myAllowImplicitConversions, this.myAssumeNullSubstitutionsEquals, this.myResolveContext);
    }
    
    @Override
    public OCTypeCompatibilityVisitor<? extends OCType> visitObjectType(final OCObjectType ocObjectType) {
        return new OCTypeCompatibilityVisitor_OCObjectType(ocObjectType, this.mySource, this.myContext, this.myAllowImplicitConversions, this.myAssumeNullSubstitutionsEquals, this.myResolveContext);
    }
    
    @Override
    public OCTypeCompatibilityVisitor<? extends OCType> visitArrayType(final OCArrayType ocArrayType) {
        return new OCTypeCompatibilityVisitor_OCArrayType(ocArrayType, this.mySource, this.myContext, this.myAllowImplicitConversions, this.myAssumeNullSubstitutionsEquals, this.myResolveContext);
    }
    
    @Override
    public OCTypeCompatibilityVisitor<? extends OCType> visitPointerType(final OCPointerType ocPointerType) {
        return new OCTypeCompatibilityVisitor_OCPointerType(ocPointerType, this.mySource, this.myContext, this.myAllowImplicitConversions, this.myAssumeNullSubstitutionsEquals, this.myResolveContext);
    }
    
    @Override
    public OCTypeCompatibilityVisitor<? extends OCType> visitBlockPointerType(final OCBlockPointerType ocBlockPointerType) {
        return new OCTypeCompatibilityVisitor_OCBlockPointerType(ocBlockPointerType, this.mySource, this.myContext, this.myAllowImplicitConversions, this.myAssumeNullSubstitutionsEquals, this.myResolveContext);
    }
    
    @Override
    public OCTypeCompatibilityVisitor<? extends OCType> visitCppReferenceType(final OCCppReferenceType ocCppReferenceType) {
        return ocCppReferenceType.getRefType(this.myContext).accept((OCTypeVisitor<OCTypeCompatibilityVisitor<? extends OCType>>)this);
    }
    
    @Override
    public OCTypeCompatibilityVisitor<? extends OCType> visitIdType(final OCIdType ocIdType) {
        return new OCTypeCompatibilityVisitor_OCIdType(ocIdType, this.mySource, this.myContext, this.myAllowImplicitConversions, this.myAssumeNullSubstitutionsEquals, this.myResolveContext);
    }
    
    @Override
    public OCTypeCompatibilityVisitor<? extends OCType> visitIntType(final OCIntType ocIntType) {
        return new OCTypeCompatibilityVisitor_OCIntType(ocIntType, this.mySource, this.myContext, this.myAllowImplicitConversions, this.myAssumeNullSubstitutionsEquals, this.myResolveContext);
    }
    
    @Override
    public OCTypeCompatibilityVisitor<? extends OCType> visitRealType(final OCRealType ocRealType) {
        return new OCTypeCompatibilityVisitor_OCRealType(ocRealType, this.mySource, this.myContext, this.myAllowImplicitConversions, this.myAssumeNullSubstitutionsEquals, this.myResolveContext);
    }
    
    @Override
    public OCTypeCompatibilityVisitor<? extends OCType> visitReferenceType(final OCReferenceType ocReferenceType) {
        return new OCTypeCompatibilityVisitor_OCReferenceType(ocReferenceType, this.mySource, this.myContext, this.myAllowImplicitConversions, this.myAssumeNullSubstitutionsEquals, this.myResolveContext);
    }
    
    @Override
    public OCTypeCompatibilityVisitor<? extends OCType> visitStructType(final OCStructType ocStructType) {
        return new OCTypeCompatibilityVisitor_OCStructType(ocStructType, this.mySource, this.myContext, this.myAllowImplicitConversions, this.myAssumeNullSubstitutionsEquals, this.myResolveContext);
    }
    
    @Override
    public OCTypeCompatibilityVisitor<? extends OCType> visitUnknownType(final OCUnknownType ocUnknownType) {
        return new OCTypeCompatibilityVisitor_AlwaysOk<OCType>((OCType)ocUnknownType, this.mySource, this.myContext, this.myAllowImplicitConversions, this.myAssumeNullSubstitutionsEquals, this.myResolveContext);
    }
    
    @Override
    public OCTypeCompatibilityVisitor<? extends OCType> visitVoidType(final OCVoidType ocVoidType) {
        return new OCTypeCompatibilityVisitor_OCVoidType(ocVoidType, this.mySource, this.myContext, this.myAllowImplicitConversions, this.myAssumeNullSubstitutionsEquals, this.myResolveContext);
    }
    
    @Override
    public OCTypeCompatibilityVisitor<? extends OCType> visitTypeParameterType(final OCTypeParameterType ocTypeParameterType) {
        return new OCTypeCompatibilityVisitor_AlwaysOk<OCType>((OCType)ocTypeParameterType, this.mySource, this.myContext, this.myAllowImplicitConversions, this.myAssumeNullSubstitutionsEquals, this.myResolveContext);
    }
    
    @Override
    public OCTypeCompatibilityVisitor<? extends OCType> visitAutoType(final OCAutoType ocAutoType) {
        return new OCTypeCompatibilityVisitor_OCAutoType(ocAutoType, this.mySource, this.myContext, this.myAllowImplicitConversions, this.myAssumeNullSubstitutionsEquals, this.myResolveContext);
    }
    
    @Override
    public OCTypeCompatibilityVisitor<? extends OCType> visitVariadicType(final OCVariadicType ocVariadicType) {
        return new OCTypeCompatibilityVisitor_AlwaysOk<OCType>((OCType)ocVariadicType, this.mySource, this.myContext, this.myAllowImplicitConversions, this.myAssumeNullSubstitutionsEquals, this.myResolveContext);
    }
    
    @Override
    public OCTypeCompatibilityVisitor<? extends OCType> visitExpansionPackType(final OCExpansionPackType ocExpansionPackType) {
        return new OCTypeCompatibilityVisitor_AlwaysOk<OCType>((OCType)ocExpansionPackType, this.mySource, this.myContext, this.myAllowImplicitConversions, this.myAssumeNullSubstitutionsEquals, this.myResolveContext);
    }
}
