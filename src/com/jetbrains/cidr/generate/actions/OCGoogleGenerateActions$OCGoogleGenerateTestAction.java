// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.generate.actions;

import com.jetbrains.cidr.generate.handlers.OCAbstractGenerateTestHandler;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.jetbrains.cidr.generate.handlers.OCGoogleGenerateTestHandler;

public static class OCGoogleGenerateTestAction extends OCBaseGenerateTestAction
{
    public OCGoogleGenerateTestAction() {
        super(new OCGoogleGenerateTestHandler("Generate Test", "Google Test Test.cc"), OCLanguageKind.CPP);
    }
}
