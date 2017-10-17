// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import com.jetbrains.cidr.lang.types.OCTypeArgument;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.types.OCExpansionPackType;
import com.jetbrains.cidr.lang.types.OCVariadicType;
import com.jetbrains.cidr.lang.types.OCAutoType;
import com.jetbrains.cidr.lang.types.OCTypeParameterType;
import com.jetbrains.cidr.lang.types.OCVoidType;
import com.jetbrains.cidr.lang.types.OCUnknownType;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.types.OCReferenceTypeBuilder;
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
import java.util.Iterator;
import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import com.jetbrains.cidr.lang.types.OCEllipsisType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.OCType;

public class OCTypeCloneVisitor implements OCTypeVisitor<OCType>
{
    private final boolean isShallow;
    @Nullable
    private final OCType myRootType;
    private final Boolean myForcedConst;
    private final Boolean myForcedVolatile;
    private int myDepth;
    
    public OCTypeCloneVisitor(final boolean b) {
        this(b, null, null, null);
    }
    
    public OCTypeCloneVisitor(final boolean isShallow, @Nullable final OCType myRootType, @Nullable final Boolean myForcedConst, @Nullable final Boolean myForcedVolatile) {
        this.isShallow = isShallow;
        this.myRootType = myRootType;
        this.myForcedConst = myForcedConst;
        this.myForcedVolatile = myForcedVolatile;
    }
    
    private OCType a(final OCType ocType) {
        Label_0030: {
            try {
                if (this.isShallow) {
                    return ocType;
                }
                final OCTypeCloneVisitor ocTypeCloneVisitor = this;
                final int n = ocTypeCloneVisitor.myDepth;
                final int n2 = 256;
                if (n >= n2) {
                    return ocType;
                }
                break Label_0030;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCTypeCloneVisitor ocTypeCloneVisitor = this;
                final int n = ocTypeCloneVisitor.myDepth;
                final int n2 = 256;
                if (n >= n2) {
                    return ocType;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        ++this.myDepth;
        final OCType transformType = ocType.transformType(this);
        --this.myDepth;
        return transformType;
    }
    
    private boolean c(@NotNull final OCType ocType) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "original", "com/jetbrains/cidr/lang/types/visitors/OCTypeCloneVisitor", "isConstCopy"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        boolean b = false;
        Label_0081: {
            Label_0072: {
                try {
                    if (this.myRootType == null) {
                        break Label_0072;
                    }
                    final OCTypeCloneVisitor ocTypeCloneVisitor = this;
                    final OCType ocType2 = ocTypeCloneVisitor.myRootType;
                    final OCType ocType3 = ocType2.getArrayElementType();
                    final OCType ocType4 = ocType;
                    final OCType ocType5 = ocType4.getArrayElementType();
                    if (ocType3 == ocType5) {
                        break Label_0072;
                    }
                    break Label_0072;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final OCTypeCloneVisitor ocTypeCloneVisitor = this;
                    final OCType ocType2 = ocTypeCloneVisitor.myRootType;
                    final OCType ocType3 = ocType2.getArrayElementType();
                    final OCType ocType4 = ocType;
                    final OCType ocType5 = ocType4.getArrayElementType();
                    if (ocType3 == ocType5) {
                        b = true;
                        break Label_0081;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            b = false;
        }
        final boolean b2 = b;
        Label_0100: {
            try {
                if (!b2) {
                    return ocType.isConst();
                }
                final OCTypeCloneVisitor ocTypeCloneVisitor2 = this;
                final Boolean b3 = ocTypeCloneVisitor2.myForcedConst;
                if (b3 != null) {
                    break Label_0100;
                }
                return ocType.isConst();
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            try {
                final OCTypeCloneVisitor ocTypeCloneVisitor2 = this;
                final Boolean b3 = ocTypeCloneVisitor2.myForcedConst;
                if (b3 != null) {
                    return this.myForcedConst;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        return ocType.isConst();
    }
    
    private boolean b(@NotNull final OCType ocType) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "original", "com/jetbrains/cidr/lang/types/visitors/OCTypeCloneVisitor", "isVolatileCopy"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (this.myForcedVolatile != null) {
                return this.myForcedVolatile;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return ocType.isVolatile();
    }
    
    @Override
    public OCType visitEllipsisReferenceType(final OCEllipsisType ocEllipsisType) {
        return OCEllipsisType.instance();
    }
    
    @Override
    public OCType visitFunctionType(final OCFunctionType ocFunctionType) {
        final List<OCType> parameterTypes = ocFunctionType.getParameterTypes(true);
        final List<String> parameterNames = ocFunctionType.getParameterNames(true);
        final ArrayList list = new ArrayList<OCType>(parameterTypes.size());
        final Iterator<OCType> iterator = parameterTypes.iterator();
        while (iterator.hasNext()) {
            list.add(this.a(iterator.next()));
        }
        try {
            if (parameterNames != null) {
                final List<String> list2 = new ArrayList<String>(parameterNames);
                return new OCFunctionType(this.a(ocFunctionType.getReturnType()), (List<OCType>)list, list2, this.c(ocFunctionType), this.b(ocFunctionType), ocFunctionType.isLValueRef(), ocFunctionType.isRValueRef());
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final List<String> list2 = null;
        return new OCFunctionType(this.a(ocFunctionType.getReturnType()), (List<OCType>)list, list2, this.c(ocFunctionType), this.b(ocFunctionType), ocFunctionType.isLValueRef(), ocFunctionType.isRValueRef());
    }
    
    @Override
    public OCType visitMagicType(final OCMagicType ocMagicType) {
        return new OCMagicType(ocMagicType.getName(), ocMagicType.getGuessedType(), false, false);
    }
    
    @Override
    public OCType visitObjectType(final OCObjectType ocObjectType) {
        final OCObjectType ocObjectType2 = new OCObjectType(ocObjectType.getInterface(), ocObjectType.getImplementation(), ocObjectType.getAllProtocols(), ocObjectType.getAugmentedProtocols(), ocObjectType.getCategoryInterfaces(), ocObjectType.getCategoryImplementations(), ocObjectType.getSuperType(), this.c(ocObjectType), this.b(ocObjectType), ocObjectType.isKindof());
        ocObjectType2.attachNullability(ocObjectType.getNullability());
        return ocObjectType2;
    }
    
    @Override
    public OCType visitArrayType(final OCArrayType ocArrayType) {
        return OCArrayType.to(ocArrayType.getRefType().transformType(this), ocArrayType.getLength(), ocArrayType.getARCAttribute());
    }
    
    @Override
    public OCType visitPointerType(final OCPointerType ocPointerType) {
        OCType a = null;
        Label_0023: {
            try {
                if (ocPointerType.getClassQualifier() != null) {
                    a = this.a(ocPointerType.getClassQualifier());
                    break Label_0023;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            a = null;
        }
        final OCPointerType to = OCPointerType.to(this.a(ocPointerType.getRefType()), ocPointerType.getARCAttribute(), a, ocPointerType.getNullability(), this.c(ocPointerType), this.b(ocPointerType));
        to.setLengthInBrackets(ocPointerType.getLengthInBrackets());
        return to;
    }
    
    @Override
    public OCType visitBlockPointerType(final OCBlockPointerType ocBlockPointerType) {
        return OCBlockPointerType.blockPtr(this.a(ocBlockPointerType.getRefType()), ocBlockPointerType.getARCAttribute(), ocBlockPointerType.getNullability(), this.c(ocBlockPointerType), this.b(ocBlockPointerType));
    }
    
    @Override
    public OCType visitCppReferenceType(final OCCppReferenceType ocCppReferenceType) {
        final OCType a = this.a(ocCppReferenceType.getRefType());
        final boolean c = this.c(ocCppReferenceType);
        final boolean b = this.b(ocCppReferenceType);
        try {
            if (ocCppReferenceType.isRvalueRef()) {
                return OCCppReferenceType.to(a, true, c, b);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return OCCppReferenceType.to(a, false, c, b);
    }
    
    @Override
    public OCType visitIdType(final OCIdType ocIdType) {
        final OCIdType ocIdType2 = new OCIdType(ocIdType.getAllProtocols(), ocIdType.getAugmentedProtocols(), ocIdType.getProject(), this.c(ocIdType), this.b(ocIdType));
        ocIdType2.attachNullability(ocIdType.getNullability());
        return ocIdType2;
    }
    
    @Override
    public OCType visitIntType(final OCIntType ocIntType) {
        return ocIntType.cloneType(this.c(ocIntType), this.b(ocIntType));
    }
    
    @Override
    public OCType visitRealType(final OCRealType ocRealType) {
        return ocRealType.cloneType(this.c(ocRealType), this.b(ocRealType));
    }
    
    @Override
    public OCType visitReferenceType(final OCReferenceType protocolSubstitutionARCFromType) {
        final OCReferenceTypeBuilder ocReferenceTypeBuilder = new OCReferenceTypeBuilder(protocolSubstitutionARCFromType.getReference());
        ocReferenceTypeBuilder.setProtocolSubstitutionARCFromType(protocolSubstitutionARCFromType);
        ocReferenceTypeBuilder.setConstVolatile(this.c(protocolSubstitutionARCFromType), this.b(protocolSubstitutionARCFromType));
        ocReferenceTypeBuilder.setFunctionParameterType(protocolSubstitutionARCFromType.isFunctionParameterType());
        ocReferenceTypeBuilder.setIsKindof(protocolSubstitutionARCFromType.isKindof());
        ocReferenceTypeBuilder.setNullability(protocolSubstitutionARCFromType.getNullability());
        return ocReferenceTypeBuilder.build();
    }
    
    @Override
    public OCType visitStructType(final OCStructType ocStructType) {
        return new OCStructType(ocStructType.getStructs(), ocStructType.getTypedefName(), this.c(ocStructType), this.b(ocStructType), ocStructType.getArguments());
    }
    
    @Override
    public OCType visitUnknownType(final OCUnknownType ocUnknownType) {
        return OCUnknownType.INSTANCE;
    }
    
    @Override
    public OCType visitVoidType(final OCVoidType ocVoidType) {
        return OCVoidType.instance(this.c(ocVoidType), this.b(ocVoidType));
    }
    
    @Override
    public OCType visitTypeParameterType(final OCTypeParameterType ocTypeParameterType) {
        return new OCTypeParameterType(ocTypeParameterType.getSymbol(), this.c(ocTypeParameterType), this.b(ocTypeParameterType));
    }
    
    @Override
    public OCType visitAutoType(final OCAutoType ocAutoType) {
        return new OCAutoType(ocAutoType.getExpressionSymbol(), ocAutoType.getExpressionElement(), ocAutoType.getIncompleteType(), ocAutoType.getLambdaExpression(), ocAutoType.getParameterIndex(), ocAutoType.needsAutoParamsResolving(), this.c(ocAutoType), this.b(ocAutoType));
    }
    
    @Override
    public OCType visitVariadicType(final OCVariadicType ocVariadicType) {
        return new OCVariadicType(this.a(ocVariadicType.getUnderlyingType()));
    }
    
    @Override
    public OCType visitExpansionPackType(final OCExpansionPackType ocExpansionPackType) {
        return new OCExpansionPackType(ContainerUtil.map((Collection)ocExpansionPackType.getExpansions(), ocTypeArgument -> {
            try {
                if (ocTypeArgument instanceof OCType) {
                    return ((OCType)ocTypeArgument).transformType(new OCTypeCloneVisitor(this.isShallow, (OCType)ocTypeArgument, this.myForcedConst, this.myForcedVolatile));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return ocTypeArgument;
        }));
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
