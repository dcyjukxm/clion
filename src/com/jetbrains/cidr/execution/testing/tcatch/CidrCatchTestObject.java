// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing.tcatch;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.CidrBundle;
import com.jetbrains.cidr.execution.testing.CidrScopeInfo;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCFile;

class CidrCatchTestObject
{
    private final KIND myKind;
    private final CidrCatchTestCache myTestInfo;
    private final String myPattern;
    private final OCFile myFile;
    
    public CidrCatchTestObject(@NotNull final CidrCatchTestCache myTestInfo) {
        if (myTestInfo == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "testInfo", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestObject", "<init>"));
        }
        this.myKind = KIND.TEST;
        this.myTestInfo = myTestInfo;
        this.myPattern = null;
        this.myFile = null;
    }
    
    public CidrCatchTestObject(@NotNull final String myPattern, @NotNull final OCFile myFile) {
        if (myPattern == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "pattern", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestObject", "<init>"));
        }
        if (myFile == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestObject", "<init>"));
        }
        this.myKind = KIND.FILE;
        this.myTestInfo = null;
        this.myPattern = myPattern;
        this.myFile = myFile;
    }
    
    public CidrScopeInfo getScope() {
        try {
            if (this.myKind == KIND.TEST) {
                return new CidrScopeInfo(this.myTestInfo.getTags(), this.myTestInfo.getTestName(), this.myTestInfo.getTestName(), null);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return new CidrScopeInfo(null, null, CidrBundle.message("test.defaultName.allTestsIn", this.myFile.getName()), this.myPattern);
    }
    
    @NotNull
    public KIND getKind() {
        KIND myKind;
        try {
            myKind = this.myKind;
            if (myKind == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestObject", "getKind"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myKind;
    }
    
    @Nullable
    public CidrCatchTestCache getTestInfo() {
        return this.myTestInfo;
    }
    
    @Nullable
    public String getPattern() {
        return this.myPattern;
    }
    
    @Nullable
    public OCFile getFile() {
        return this.myFile;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    enum KIND
    {
        FILE, 
        TEST;
    }
}
