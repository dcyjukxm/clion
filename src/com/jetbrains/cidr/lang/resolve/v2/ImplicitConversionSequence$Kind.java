// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve.v2;

enum Kind
{
    StandardConversion, 
    UserDefinedConversion, 
    AmbiguousConversion, 
    EllipsisConversion, 
    BadConversion, 
    Uninitialized;
}
