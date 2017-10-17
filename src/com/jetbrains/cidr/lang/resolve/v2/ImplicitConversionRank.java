// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve.v2;

enum ImplicitConversionRank
{
    ICR_Exact_Match, 
    ICR_Promotion, 
    ICR_Conversion, 
    ICR_Complex_Real_Conversion, 
    ICR_Writeback_Conversion, 
    ICR_C_Conversion;
}
