// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.actions.newFile;

import org.jetbrains.annotations.NotNull;
import java.util.Collection;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.openapi.util.Condition;
import java.util.Arrays;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public static class NewFileNames
{
    @Nullable
    private final String mySourceName;
    @Nullable
    private final String myHeaderName;
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public NewFileNames(@Nullable final String mySourceName, @Nullable final String myHeaderName) {
        Label_0039: {
            if (!NewFileNames.$assertionsDisabled) {
                Label_0025: {
                    try {
                        if (mySourceName != null) {
                            break Label_0039;
                        }
                        final String s = myHeaderName;
                        if (s == null) {
                            break Label_0025;
                        }
                        break Label_0039;
                    }
                    catch (IllegalStateException ex) {
                        throw a(ex);
                    }
                    try {
                        final String s = myHeaderName;
                        if (s == null) {
                            throw new AssertionError((Object)"either source or header name should be specified");
                        }
                    }
                    catch (IllegalStateException ex2) {
                        throw a(ex2);
                    }
                }
            }
        }
        this.mySourceName = mySourceName;
        this.myHeaderName = myHeaderName;
    }
    
    @Nullable
    public String getSourceName() {
        return this.mySourceName;
    }
    
    @Nullable
    public String getHeaderName() {
        return this.myHeaderName;
    }
    
    @Override
    public String toString() {
        return this.mySourceName + " & " + this.myHeaderName;
    }
    
    @NotNull
    public List<String> getNames() {
        List filter;
        try {
            filter = ContainerUtil.filter((Collection)Arrays.asList(this.mySourceName, this.myHeaderName), Condition.NOT_NULL);
            if (filter == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase$NewFileNames", "getNames"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (List<String>)filter;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCNewFileActionBase.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
