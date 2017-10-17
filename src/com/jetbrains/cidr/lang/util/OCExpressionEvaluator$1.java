// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.types.OCType;
import java.util.function.BiFunction;
import gnu.trove.THashMap;

static final class OCExpressionEvaluator$1 extends THashMap<String, BiFunction<OCType, OCResolveContext, Boolean>> {
    {
        Object value;
        this.put((Object)"__is_class", (ocType, ocResolveContext) -> {
            if (ocType.isMagicInside(ocResolveContext)) {
                value = null;
            }
            else {
                value = (ocType instanceof OCStructType && ocType.isCppStructType());
            }
            return value;
        });
        Object value2;
        this.put((Object)"__is_enum", (ocStructType, ocResolveContext2) -> {
            if (ocStructType.isMagicInside(ocResolveContext2)) {
                value2 = null;
            }
            else {
                value2 = (ocStructType instanceof OCStructType && ocStructType.getKind() == OCSymbolKind.ENUM);
            }
            return value2;
        });
        Object value3;
        this.put((Object)"__is_union", (ocStructType2, ocResolveContext3) -> {
            if (ocStructType2.isMagicInside(ocResolveContext3)) {
                value3 = null;
            }
            else {
                value3 = (ocStructType2 instanceof OCStructType && ocStructType2.getKind() == OCSymbolKind.UNION);
            }
            return value3;
        });
        Object value4;
        this.put((Object)"__is_pod", (ocStructType3, ocResolveContext4) -> {
            if (ocStructType3.isMagicInside(ocResolveContext4)) {
                value4 = null;
            }
            else {
                value4 = (ocStructType3 instanceof OCStructType && ocStructType3.isPOD(false));
            }
            return value4;
        });
        Object value5;
        this.put((Object)"__is_abstract", (ocStructType4, ocResolveContext5) -> {
            if (ocStructType4.isMagicInside(ocResolveContext5)) {
                value5 = null;
            }
            else {
                value5 = (ocStructType4 instanceof OCStructType && ocStructType4.isAbstract());
            }
            return value5;
        });
    }
}