// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing;

import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.Nullable;
import java.util.Collections;
import com.intellij.openapi.diagnostic.Logger;
import com.jetbrains.cidr.lang.OCLog;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;
import java.util.SortedSet;

public class CidrTestScope
{
    private final String mySeparator;
    private final SortedSet<CidrTestScopeElement> myElements;
    private final String myPreRenderedScope;
    
    public CidrTestScope(@NotNull final String myPreRenderedScope) {
        if (myPreRenderedScope == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "preRenderedScope", "com/jetbrains/cidr/execution/testing/CidrTestScope", "<init>"));
        }
        this.myElements = (SortedSet<CidrTestScopeElement>)ContainerUtil.newTreeSet();
        this.mySeparator = null;
        this.myPreRenderedScope = myPreRenderedScope;
    }
    
    public CidrTestScope(final char c) {
        this.myElements = (SortedSet<CidrTestScopeElement>)ContainerUtil.newTreeSet();
        this.mySeparator = String.valueOf(c);
        this.myPreRenderedScope = null;
    }
    
    public CidrTestScope() {
        this(',');
    }
    
    public CidrTestScope(@NotNull final CidrTestScopeElement cidrTestScopeElement) {
        if (cidrTestScopeElement == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/execution/testing/CidrTestScope", "<init>"));
        }
        this();
        this.myElements.add(cidrTestScopeElement);
    }
    
    public void add(final CidrTestScopeElement cidrTestScopeElement) {
        Logger log = null;
        boolean b = false;
        Label_0019: {
            try {
                log = OCLog.LOG;
                if (this.myPreRenderedScope == null) {
                    b = true;
                    break Label_0019;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            b = false;
        }
        log.assertTrue(b);
        this.myElements.add(cidrTestScopeElement);
    }
    
    public SortedSet<CidrTestScopeElement> getElements() {
        Logger log = null;
        boolean b = false;
        Label_0019: {
            try {
                log = OCLog.LOG;
                if (this.myPreRenderedScope == null) {
                    b = true;
                    break Label_0019;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            b = false;
        }
        log.assertTrue(b);
        return Collections.unmodifiableSortedSet(this.myElements);
    }
    
    @Nullable
    public String getPreRenderedScope() {
        return this.myPreRenderedScope;
    }
    
    @Override
    public String toString() {
        try {
            if (this.myPreRenderedScope != null) {
                return this.myPreRenderedScope;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return StringUtil.join((Iterable)this.myElements, this.mySeparator);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
