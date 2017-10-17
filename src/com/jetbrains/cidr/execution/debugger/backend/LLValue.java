// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.util.UserDataHolderBase;

public class LLValue extends UserDataHolderBase
{
    @NotNull
    private final String myName;
    @NotNull
    private final String myType;
    @Nullable
    private final TypeClass myTypeClass;
    @NotNull
    private final String myReferenceExpression;
    private boolean myValid;
    
    public LLValue(@NotNull final String myName, @NotNull final String myType, @Nullable final TypeClass myTypeClass, @NotNull final String myReferenceExpression) {
        if (myName == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/execution/debugger/backend/LLValue", "<init>"));
        }
        if (myType == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/execution/debugger/backend/LLValue", "<init>"));
        }
        if (myReferenceExpression == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "referenceExpression", "com/jetbrains/cidr/execution/debugger/backend/LLValue", "<init>"));
        }
        this.myValid = true;
        this.myName = myName;
        this.myType = myType;
        this.myTypeClass = myTypeClass;
        this.myReferenceExpression = myReferenceExpression;
    }
    
    @NotNull
    public String getName() {
        String myName;
        try {
            myName = this.myName;
            if (myName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/LLValue", "getName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myName;
    }
    
    @NotNull
    public String getType() {
        String myType;
        try {
            myType = this.myType;
            if (myType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/LLValue", "getType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myType;
    }
    
    @Nullable
    public TypeClass getTypeClass() {
        return this.myTypeClass;
    }
    
    @NotNull
    public String getReferenceExpression() {
        String myReferenceExpression;
        try {
            myReferenceExpression = this.myReferenceExpression;
            if (myReferenceExpression == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/LLValue", "getReferenceExpression"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myReferenceExpression;
    }
    
    public boolean isValid() {
        return this.myValid;
    }
    
    public void setValid(final boolean myValid) {
        this.myValid = myValid;
    }
    
    public String toString() {
        return this.myName + ":" + this.myType;
    }
    
    public boolean equals(final Object o) {
        try {
            if (this == o) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0039: {
            try {
                if (o == null) {
                    return false;
                }
                final LLValue llValue = this;
                final Class<? extends LLValue> clazz = llValue.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
                break Label_0039;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final LLValue llValue = this;
                final Class<? extends LLValue> clazz = llValue.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        final LLValue llValue2 = (LLValue)o;
        try {
            if (this.myValid != llValue2.myValid) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        try {
            if (!this.myName.equals(llValue2.myName)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        try {
            if (!this.myType.equals(llValue2.myType)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        try {
            if (this.myTypeClass != llValue2.myTypeClass) {
                return false;
            }
        }
        catch (IllegalArgumentException ex7) {
            throw a(ex7);
        }
        try {
            if (!this.myReferenceExpression.equals(llValue2.myReferenceExpression)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex8) {
            throw a(ex8);
        }
        return true;
    }
    
    public int hashCode() {
        final int n = 31 * this.myName.hashCode() + this.myType.hashCode();
        int n2 = 0;
        int hashCode = 0;
        Label_0047: {
            try {
                n2 = 31 * n;
                if (this.myTypeClass != null) {
                    hashCode = this.myTypeClass.hashCode();
                    break Label_0047;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            hashCode = 0;
        }
        final int n3 = 31 * (n2 + hashCode) + this.myReferenceExpression.hashCode();
        int n4;
        try {
            n4 = 31 * n3;
            if (this.myValid) {
                final int n5 = 1;
                return n4 + n5;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final int n5 = 0;
        return n4 + n5;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    public enum TypeClass
    {
        CLASS_STRUCT, 
        OBJC_POINTER, 
        FUNCTION, 
        BUILTIN;
    }
}
