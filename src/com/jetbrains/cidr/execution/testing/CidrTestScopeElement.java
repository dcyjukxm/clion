// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing;

import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.Nullable;

public abstract class CidrTestScopeElement implements Comparable<CidrTestScopeElement>
{
    @Nullable
    protected final String myTestSuite;
    @Nullable
    protected final String myTestName;
    
    public CidrTestScopeElement(@Nullable final String myTestSuite, @Nullable final String myTestName) {
        this.myTestSuite = myTestSuite;
        this.myTestName = myTestName;
    }
    
    public CidrTestScopeElement() {
        this(null, null);
    }
    
    @Override
    public abstract String toString();
    
    @Nullable
    public String getTestSuite() {
        return this.myTestSuite;
    }
    
    @Nullable
    public String getTestName() {
        return this.myTestName;
    }
    
    @Override
    public boolean equals(final Object o) {
        return this == o || (o instanceof CidrTestScopeElement && this.toString().equals(((CidrTestScopeElement)o).toString()));
    }
    
    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
    
    @Override
    public int compareTo(final CidrTestScopeElement cidrTestScopeElement) {
        return StringUtil.compare(this.toString(), cidrTestScopeElement.toString(), false);
    }
}
