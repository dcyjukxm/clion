// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.generate.actions;

import com.jetbrains.cidr.generate.handlers.OCAbstractGenerateTestHandler;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.jetbrains.cidr.generate.handlers.OCGoogleGenerateFixtureMethodsHandler;

public static class OCGoogleGenerateFixtureSetUpAction extends OCBaseGenerateTestAction
{
    public OCGoogleGenerateFixtureSetUpAction() {
        super(new OCGoogleGenerateFixtureMethodsHandler("Generate SetUp", "Google Test Fixture SetUp Method.cc"), OCLanguageKind.CPP);
    }
}
