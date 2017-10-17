// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve.v2;

enum ImplicitConversionKind
{
    ICK_Identity, 
    ICK_Lvalue_To_Rvalue, 
    ICK_Array_To_Pointer, 
    ICK_Function_To_Pointer, 
    ICK_NoReturn_Adjustment, 
    ICK_Qualification, 
    ICK_Integral_Promotion, 
    ICK_Floating_Promotion, 
    ICK_Complex_Promotion, 
    ICK_Integral_Conversion, 
    ICK_Floating_Conversion, 
    ICK_Complex_Conversion, 
    ICK_Floating_Integral, 
    ICK_Pointer_Conversion, 
    ICK_Pointer_Member, 
    ICK_Boolean_Conversion, 
    ICK_Compatible_Conversion, 
    ICK_Derived_To_Base, 
    ICK_Vector_Conversion, 
    ICK_Vector_Splat, 
    ICK_Complex_Real, 
    ICK_Block_Pointer_Conversion, 
    ICK_TransparentUnionConversion, 
    ICK_Writeback_Conversion, 
    ICK_Zero_Event_Conversion, 
    ICK_C_Only_Conversion, 
    ICK_Num_Conversion_Kinds;
}
