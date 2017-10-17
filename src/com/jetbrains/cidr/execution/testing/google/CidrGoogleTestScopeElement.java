// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing.google;

import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.execution.testing.CidrTestScopeElement;

public class CidrGoogleTestScopeElement extends CidrTestScopeElement
{
    private final String myInstantiation;
    private final String myParam;
    
    public CidrGoogleTestScopeElement(@Nullable final String s, @Nullable final String s2, @Nullable final String myInstantiation, @Nullable final String myParam) {
        super(s, s2);
        this.myInstantiation = myInstantiation;
        this.myParam = myParam;
    }
    
    @Override
    public String toString() {
        final String notNullize = StringUtil.notNullize(this.myTestName, "*");
        final String notNullize2 = StringUtil.notNullize(this.myTestSuite, "*");
        final String notNullize3 = StringUtil.notNullize(this.myParam, "*");
        if (this.myInstantiation != null) {
            return String.format("%s/%s.%s/%s:%s/%s/%s.%s", this.myInstantiation, notNullize2, notNullize, notNullize3, this.myInstantiation, notNullize2, notNullize3, notNullize);
        }
        if (this.myTestSuite == null) {
            return "*";
        }
        return String.format("%s.%s:%s/%s.%s:*/%s.%s/*:*/%s/*.%s", notNullize2, notNullize, notNullize2, notNullize3, notNullize, notNullize2, notNullize, notNullize2, notNullize);
    }
}
