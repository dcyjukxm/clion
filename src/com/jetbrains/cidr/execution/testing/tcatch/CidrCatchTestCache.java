// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing.tcatch;

import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import org.jetbrains.annotations.Nullable;

public class CidrCatchTestCache
{
    @Nullable
    private final String myTags;
    @Nullable
    private final String myTestName;
    @NotNull
    private final OCDeclarator myDeclarator;
    
    public CidrCatchTestCache(@NotNull final String s, @NotNull final String s2, @NotNull final OCDeclarator myDeclarator) {
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "testName", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCache", "<init>"));
        }
        if (s2 == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "tags", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCache", "<init>"));
        }
        if (myDeclarator == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "declarator", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCache", "<init>"));
        }
        this.myTestName = StringUtil.nullize(s, true);
        this.myTags = StringUtil.nullize(s2, true);
        this.myDeclarator = myDeclarator;
    }
    
    @Nullable
    public String getTags() {
        return this.myTags;
    }
    
    @Nullable
    public String getTestName() {
        return this.myTestName;
    }
    
    @NotNull
    public String getNotNullTestName() {
        String notNullize;
        try {
            notNullize = StringUtil.notNullize(this.myTestName, "Anonymous test case *");
            if (notNullize == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCache", "getNotNullTestName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return notNullize;
    }
    
    @NotNull
    public OCDeclarator getDeclarator() {
        OCDeclarator myDeclarator;
        try {
            myDeclarator = this.myDeclarator;
            if (myDeclarator == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestCache", "getDeclarator"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myDeclarator;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
