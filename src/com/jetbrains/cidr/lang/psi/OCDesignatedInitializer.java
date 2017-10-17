// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

public interface OCDesignatedInitializer extends OCCompoundInitializerMember
{
    OCQualifiedDesignator getDesignation();
    
    OCExpression getInitializer();
}
