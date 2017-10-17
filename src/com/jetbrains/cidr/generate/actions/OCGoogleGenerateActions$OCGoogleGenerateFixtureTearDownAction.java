// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.generate.actions;

import com.jetbrains.cidr.generate.handlers.OCAbstractGenerateTestHandler;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.jetbrains.cidr.generate.handlers.OCGoogleGenerateFixtureMethodsHandler;

public static class OCGoogleGenerateFixtureTearDownAction extends OCBaseGenerateTestAction
{
    public OCGoogleGenerateFixtureTearDownAction() {
        super(new OCGoogleGenerateFixtureMethodsHandler("Generate TearDown", "Google Test Fixture TearDown Method.cc"), OCLanguageKind.CPP);
    }
}
