// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.NotNull;

public static class StreamRecord extends GDBResponse
{
    private final Category myCategory;
    private final String myText;
    
    public StreamRecord(@NotNull final Category myCategory, @NotNull final String myText) {
        if (myCategory == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "category", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$StreamRecord", "<init>"));
        }
        if (myText == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$StreamRecord", "<init>"));
        }
        this.myCategory = myCategory;
        this.myText = myText;
    }
    
    @NotNull
    public Category getCategory() {
        Category myCategory;
        try {
            myCategory = this.myCategory;
            if (myCategory == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$StreamRecord", "getCategory"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myCategory;
    }
    
    @NotNull
    public String getText() {
        String myText;
        try {
            myText = this.myText;
            if (myText == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$StreamRecord", "getText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myText;
    }
    
    @Override
    public String toString() {
        return this.myCategory.myPrefix + "\"" + StringUtil.escapeStringCharacters(this.myText) + "\"";
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    public enum Category implements RecordCategory
    {
        console("~"), 
        target("@"), 
        log("&");
        
        private final String myPrefix;
        
        private Category(final String myPrefix) {
            this.myPrefix = myPrefix;
        }
        
        @NotNull
        @Override
        public String getPrefix() {
            String myPrefix;
            try {
                myPrefix = this.myPrefix;
                if (myPrefix == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$StreamRecord$Category", "getPrefix"));
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            return myPrefix;
        }
        
        @Override
        public String toString() {
            return this.myPrefix;
        }
        
        static Category forPrefix(@NotNull final String s) {
            try {
                if (s == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "prefix", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$StreamRecord$Category", "forPrefix"));
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            return RecordCategory.forPrefix(values(), s);
        }
        
        private static IllegalStateException a(final IllegalStateException ex) {
            return ex;
        }
    }
}
