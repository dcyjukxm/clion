// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import com.jetbrains.cidr.lang.symbols.OCTypeParameterSymbol;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.symbols.cpp.OCTypeParameterTypeSymbol;
import java.util.Collections;
import java.util.Map;
import com.jetbrains.cidr.lang.types.visitors.OCNonPrimitiveTypeCloneVisitor;

static final class OCTypeUtils$9 extends OCNonPrimitiveTypeCloneVisitor {
    final /* synthetic */ Map val$map;
    
    @Override
    public OCType visitAutoType(final OCAutoType ocAutoType) {
        final OCTypeParameterType ocTypeParameterType = new OCTypeParameterType(new OCTypeParameterTypeSymbol(null, null, this.val$map.size(), "auto", null, Collections.emptyList(), null, false), ocAutoType.isConst(), ocAutoType.isVolatile());
        this.val$map.put(ocAutoType, ocTypeParameterType);
        return ocTypeParameterType;
    }
}