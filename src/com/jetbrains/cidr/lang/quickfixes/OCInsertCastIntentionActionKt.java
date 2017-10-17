// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.CVQualifiers;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.types.OCTypeOwner;
import com.jetbrains.cidr.lang.resolve.OCExprValueCategory;
import com.jetbrains.cidr.lang.types.OCCppReferenceType;
import kotlin.jvm.internal.Intrinsics;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.types.OCType;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 2, d1 = { "\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u001a*\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002¨\u0006\u000e" }, d2 = { "decomposeCV", "Lcom/jetbrains/cidr/lang/quickfixes/CVDecompositionResult;", "type", "Lcom/jetbrains/cidr/lang/types/OCType;", "project", "Lcom/intellij/openapi/project/Project;", "getRefTypes", "Lcom/jetbrains/cidr/lang/quickfixes/RefTypes;", "old", "new", "expr", "Lcom/jetbrains/cidr/lang/psi/OCExpression;", "context", "Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;", "cidr-lang" })
public final class OCInsertCastIntentionActionKt
{
    private static final RefTypes a(final OCType ocType, final OCType ocType2, final OCExpression ocExpression, final OCResolveContext ocResolveContext) {
        RefTypes refTypes;
        if (ocType instanceof OCPointerType && ocType2 instanceof OCPointerType) {
            final OCType refType;
            final OCType refType2;
            refTypes = new RefTypes(refType, refType2, true);
            refType = ((OCPointerType)ocType).getRefType();
            Intrinsics.checkExpressionValueIsNotNull((Object)refType, "old.refType");
            refType2 = ((OCPointerType)ocType2).getRefType();
            Intrinsics.checkExpressionValueIsNotNull((Object)refType2, "new.refType");
        }
        else if (ocType2 instanceof OCCppReferenceType && OCExprValueCategory.classify(ocExpression, ocResolveContext).isLValue()) {
            final OCType refType3;
            refTypes = new RefTypes(ocType, refType3, false);
            refType3 = ((OCCppReferenceType)ocType2).getRefType();
            Intrinsics.checkExpressionValueIsNotNull((Object)refType3, "new.refType");
        }
        else {
            refTypes = null;
        }
        return refTypes;
    }
    
    private static final CVDecompositionResult a(final OCType ocType, final Project project) {
        OCType ocType2;
        CVQualifiers empty;
        if (ocType instanceof OCPointerType) {
            final OCType refType = ((OCPointerType)ocType).getRefType();
            Intrinsics.checkExpressionValueIsNotNull((Object)refType, "type.refType");
            final CVDecompositionResult a = a(refType, project);
            final OCType component1 = a.component1();
            final CVQualifiers component2 = a.component2();
            final OCPointerType to = OCPointerType.to(component1, ((OCPointerType)ocType).getARCAttribute(), ((OCPointerType)ocType).getClassQualifier(), ((OCPointerType)ocType).isConst(), ((OCPointerType)ocType).isVolatile());
            Intrinsics.checkExpressionValueIsNotNull((Object)to, "OCPointerType.to(nestedT\u2026isConst, type.isVolatile)");
            ocType2 = to;
            empty = component2;
        }
        else {
            ocType2 = ocType;
            empty = CVQualifiers.EMPTY;
        }
        final OCType cloneWithoutCVQualifiers = ocType2.cloneWithoutCVQualifiers(project);
        Intrinsics.checkExpressionValueIsNotNull((Object)cloneWithoutCVQualifiers, "resultType.cloneWithoutCVQualifiers(project)");
        final CVQualifiers or = empty.or(ocType.getCVQualifiers());
        Intrinsics.checkExpressionValueIsNotNull((Object)or, "resultCV.or(type.cvQualifiers)");
        return new CVDecompositionResult(cloneWithoutCVQualifiers, or);
    }
}
