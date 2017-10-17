// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import org.jetbrains.annotations.Nullable;
import java.util.Iterator;
import java.util.List;
import com.jetbrains.cidr.lang.types.OCTypeArgument;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.symbols.OCQualifiedNameWithArguments;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.jetbrains.cidr.lang.types.OCReferenceTypeBuilder;
import com.jetbrains.cidr.lang.symbols.OCSymbolReference;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;

public class OCTypeParentSymbolSetter extends OCTypeCloneVisitor
{
    private OCSymbolWithQualifiedName mySymbol;
    
    public OCTypeParentSymbolSetter(final OCSymbolWithQualifiedName mySymbol) {
        super(false);
        this.mySymbol = mySymbol;
    }
    
    @Override
    public OCType visitReferenceType(final OCReferenceType ocReferenceType) {
        final OCReferenceTypeBuilder ocReferenceTypeBuilder = new OCReferenceTypeBuilder(OCSymbolReference.getGlobalReference(this.replaceQualifiedName(ocReferenceType.getReference().getQualifiedName()), this.mySymbol, ocReferenceType.getReference().getFilter()));
        ocReferenceTypeBuilder.setProtocolSubstitutionARCFromType(ocReferenceType);
        ocReferenceTypeBuilder.setConstVolatile(ocReferenceType);
        return ocReferenceTypeBuilder.build();
    }
    
    @Nullable
    public OCQualifiedName replaceQualifiedName(final OCQualifiedName ocQualifiedName) {
        if (ocQualifiedName == null) {
            return null;
        }
        if (ocQualifiedName instanceof OCQualifiedNameWithArguments) {
            final ArrayList<OCTypeArgument> list = new ArrayList<OCTypeArgument>();
            for (final OCTypeArgument ocTypeArgument : ((OCQualifiedNameWithArguments)ocQualifiedName).getArguments()) {
                if (ocTypeArgument instanceof OCType) {
                    list.add(((OCType)ocTypeArgument).accept((OCTypeVisitor<OCTypeArgument>)this));
                }
                else {
                    list.add(ocTypeArgument);
                }
            }
            return new OCQualifiedNameWithArguments(this.replaceQualifiedName(ocQualifiedName.getQualifier()), ocQualifiedName.getName(), list);
        }
        final OCQualifiedName replaceQualifiedName = this.replaceQualifiedName(ocQualifiedName.getQualifier());
        if (replaceQualifiedName == ocQualifiedName.getQualifier()) {
            return ocQualifiedName;
        }
        return OCQualifiedName.with(replaceQualifiedName, ocQualifiedName.getName());
    }
}
