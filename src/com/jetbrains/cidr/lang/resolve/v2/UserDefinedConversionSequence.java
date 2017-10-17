// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve.v2;

import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;

class UserDefinedConversionSequence
{
    StandardConversionSequence Before;
    boolean EllipsisConversion;
    boolean HadMultipleCandidates;
    StandardConversionSequence After;
    OCFunctionSymbol ConversionFunction;
}
