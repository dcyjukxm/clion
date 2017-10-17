// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.preprocessor;

private enum CurrentState
{
    NONE, 
    AFTER_EXTERN, 
    AFTER_EXTERN_C, 
    AFTER_PROTOCOL, 
    AFTER_AT, 
    AFTER_MODULE_IMPORT;
}
