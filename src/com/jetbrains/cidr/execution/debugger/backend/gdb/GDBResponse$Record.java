// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import org.jetbrains.annotations.NotNull;

public abstract static class Record<CATEGORY_TYPE extends RecordCategory, TYPE_TYPE extends RecordType> extends GDBResponse
{
    @NotNull
    private final CATEGORY_TYPE myCategory;
    @NotNull
    private final TYPE_TYPE myType;
    @NotNull
    private final GDBTuple myResultList;
    
    public Record(@NotNull final CATEGORY_TYPE myCategory, @NotNull final TYPE_TYPE myType, @NotNull final GDBTuple myResultList) {
        if (myCategory == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "category", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$Record", "<init>"));
        }
        if (myType == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$Record", "<init>"));
        }
        if (myResultList == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "resultList", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$Record", "<init>"));
        }
        this.myCategory = myCategory;
        this.myType = myType;
        this.myResultList = myResultList;
    }
    
    @NotNull
    public CATEGORY_TYPE getCategory() {
        RecordCategory myCategory;
        try {
            myCategory = this.myCategory;
            if (myCategory == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$Record", "getCategory"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (CATEGORY_TYPE)myCategory;
    }
    
    @NotNull
    public TYPE_TYPE getType() {
        RecordType myType;
        try {
            myType = this.myType;
            if (myType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$Record", "getType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (TYPE_TYPE)myType;
    }
    
    @NotNull
    public GDBTuple getResultList() {
        GDBTuple myResultList;
        try {
            myResultList = this.myResultList;
            if (myResultList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$Record", "getResultList"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myResultList;
    }
    
    @Override
    public String toString() {
        return GDBResponse.access$100(this.getCategory(), this.getType(), this.myResultList);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
