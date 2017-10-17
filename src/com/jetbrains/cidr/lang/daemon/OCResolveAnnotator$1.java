// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon;

import com.jetbrains.cidr.lang.types.OCIdType;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.psi.OCLiteralExpression;
import com.jetbrains.cidr.lang.util.OCExpectedTypeUtil;

class OCResolveAnnotator$1 implements OCExpectedTypeUtil.Expectable {
    final /* synthetic */ OCLiteralExpression val$expression;
    
    @Override
    public OCType getExpectedType() {
        return OCIdType.pointerToID(this.val$expression.getProject());
    }
}