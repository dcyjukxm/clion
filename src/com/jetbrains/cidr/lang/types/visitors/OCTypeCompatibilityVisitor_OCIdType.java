// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.inspections.OCInspection;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.quickfixes.OCSetSuperclassIntentionAction;
import com.intellij.codeInsight.intention.IntentionAction;
import com.jetbrains.cidr.lang.inspections.OCInspections;
import java.util.Collection;
import com.intellij.util.containers.HashSet;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.OCTypeOwner;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.OCIdType;

class OCTypeCompatibilityVisitor_OCIdType extends OCTypeCompatibilityVisitor<OCIdType>
{
    protected OCTypeCompatibilityVisitor_OCIdType(@NotNull final OCIdType ocIdType, @Nullable final OCTypeOwner ocTypeOwner, @Nullable final PsiElement psiElement, final boolean b, final boolean b2, @NotNull final OCResolveContext ocResolveContext) {
        if (ocIdType == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sourceType", "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCIdType", "<init>"));
        }
        if (ocResolveContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "resolveContext", "com/jetbrains/cidr/lang/types/visitors/OCTypeCompatibilityVisitor_OCIdType", "<init>"));
        }
        super(ocIdType, ocTypeOwner, psiElement, b, b2, ocResolveContext);
    }
    
    @Override
    public OCType.TypeCheckResult visitFunctionType(final OCFunctionType ocFunctionType) {
        return this.visitType(ocFunctionType);
    }
    
    @Override
    public OCType.TypeCheckResult visitObjectType(final OCObjectType ocObjectType) {
        final HashSet set = new HashSet((Collection)((OCIdType)this.mySourceType).getAllProtocols());
        try {
            if (!(ocObjectType instanceof OCIdType)) {
                set.removeAll((Collection)ocObjectType.getAllProtocols());
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (set.isEmpty()) {
                return OCTypeCompatibilityVisitor_OCIdType.OK_RESULT;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (!ocObjectType.isAncestorOf((OCObjectType)this.mySourceType)) {
                return new OCType.TypeCheckResult(OCType.TypeCheckState.WARNING, OCInspections.NotSuperclass.class, "CIDR", new IntentionAction[] { new OCSetSuperclassIntentionAction(((OCIdType)this.mySourceType).getInterface(), ocObjectType.getInterface(), null) }) {
                    @Override
                    public String getMessage() {
                        return "Interface '" + OCTypeCompatibilityVisitor_OCIdType.this.getSourceTypeName() + "' is not a successor of '" + ocObjectType.getName(OCTypeCompatibilityVisitor_OCIdType.this.myContext) + "'";
                    }
                };
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        return this.getProtocolCompatibilityCheckResult((OCObjectType)this.mySourceType, ocObjectType);
    }
    
    @Override
    public OCType.TypeCheckResult visitPointerType(final OCPointerType ocPointerType) {
        return this.visitType(ocPointerType);
    }
    
    @Override
    public OCType.TypeCheckResult visitStructType(final OCStructType ocStructType) {
        return this.checkStructCompatibleCtor(ocStructType);
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
