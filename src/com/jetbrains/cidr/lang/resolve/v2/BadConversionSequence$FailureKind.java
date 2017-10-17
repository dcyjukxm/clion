// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve.v2;

enum FailureKind
{
    no_conversion, 
    unrelated_class, 
    bad_qualifiers, 
    lvalue_ref_to_rvalue, 
    rvalue_ref_to_lvalue;
}
