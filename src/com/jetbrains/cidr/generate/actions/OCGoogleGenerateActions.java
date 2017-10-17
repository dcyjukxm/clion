// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.generate.actions;

import com.jetbrains.cidr.generate.handlers.OCGoogleGenerateFixtureMethodsHandler;
import com.jetbrains.cidr.generate.handlers.OCAbstractGenerateTestHandler;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.jetbrains.cidr.generate.handlers.OCGoogleGenerateTestHandler;

public class OCGoogleGenerateActions
{
    public static class OCGoogleGenerateTestAction extends OCBaseGenerateTestAction
    {
        public OCGoogleGenerateTestAction() {
            super(new OCGoogleGenerateTestHandler("Generate Test", "Google Test Test.cc"), OCLanguageKind.CPP);
        }
    }
    
    public static class OCGoogleGenerateTestFixtureAction extends OCBaseGenerateTestAction
    {
        public OCGoogleGenerateTestFixtureAction() {
            super(new OCGoogleGenerateTestHandler("Generate Test Fixture", "Google Test Fixture.cc"), OCLanguageKind.CPP);
        }
    }
    
    public static class OCGoogleGenerateFixtureSetUpAction extends OCBaseGenerateTestAction
    {
        public OCGoogleGenerateFixtureSetUpAction() {
            super(new OCGoogleGenerateFixtureMethodsHandler("Generate SetUp", "Google Test Fixture SetUp Method.cc"), OCLanguageKind.CPP);
        }
    }
    
    public static class OCGoogleGenerateFixtureTearDownAction extends OCBaseGenerateTestAction
    {
        public OCGoogleGenerateFixtureTearDownAction() {
            super(new OCGoogleGenerateFixtureMethodsHandler("Generate TearDown", "Google Test Fixture TearDown Method.cc"), OCLanguageKind.CPP);
        }
    }
}
