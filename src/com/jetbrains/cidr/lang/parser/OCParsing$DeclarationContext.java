// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.parser;

private enum DeclarationContext
{
    FILE, 
    STRUCT, 
    PARAMETER_LIST, 
    CODE_BLOCK, 
    CPP_NEW_EXPRESSION, 
    SIZEOF, 
    TRAILING_RETURN_TYPE;
}
