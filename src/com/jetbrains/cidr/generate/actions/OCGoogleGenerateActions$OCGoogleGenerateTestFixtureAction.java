// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.generate.actions;

import com.jetbrains.cidr.generate.handlers.OCAbstractGenerateTestHandler;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.jetbrains.cidr.generate.handlers.OCGoogleGenerateTestHandler;

public static class OCGoogleGenerateTestFixtureAction extends OCBaseGenerateTestAction
{
    public OCGoogleGenerateTestFixtureAction() {
        super(new OCGoogleGenerateTestHandler("Generate Test Fixture", "Google Test Fixture.cc"), OCLanguageKind.CPP);
    }
}
