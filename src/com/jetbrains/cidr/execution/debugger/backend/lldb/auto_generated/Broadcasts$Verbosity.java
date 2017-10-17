// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Internal;
import com.google.protobuf.ProtocolMessageEnum;

public enum Verbosity implements ProtocolMessageEnum
{
    VerbosityDebug(0, 1), 
    VerbosityError(1, 2), 
    VerbosityDisplayText(2, 3);
    
    public static final int VerbosityDebug_VALUE = 1;
    public static final int VerbosityError_VALUE = 2;
    public static final int VerbosityDisplayText_VALUE = 3;
    private static Internal.EnumLiteMap<Verbosity> internalValueMap;
    private static final Verbosity[] VALUES;
    private final int index;
    private final int value;
    
    public final int getNumber() {
        return this.value;
    }
    
    public static Verbosity valueOf(final int n) {
        try {
            switch (n) {
                case 1: {
                    return Verbosity.VerbosityDebug;
                }
                case 2: {
                    break;
                }
                case 3: {
                    return Verbosity.VerbosityDisplayText;
                }
                default: {
                    return null;
                }
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return Verbosity.VerbosityError;
    }
    
    public static Internal.EnumLiteMap<Verbosity> internalGetValueMap() {
        return Verbosity.internalValueMap;
    }
    
    public final Descriptors.EnumValueDescriptor getValueDescriptor() {
        return getDescriptor().getValues().get(this.index);
    }
    
    public final Descriptors.EnumDescriptor getDescriptorForType() {
        return getDescriptor();
    }
    
    public static final Descriptors.EnumDescriptor getDescriptor() {
        return Broadcasts.getDescriptor().getEnumTypes().get(0);
    }
    
    public static Verbosity valueOf(final Descriptors.EnumValueDescriptor enumValueDescriptor) {
        try {
            if (enumValueDescriptor.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return Verbosity.VALUES[enumValueDescriptor.getIndex()];
    }
    
    private Verbosity(final int index, final int value) {
        this.index = index;
        this.value = value;
    }
    
    static {
        Verbosity.internalValueMap = (Internal.EnumLiteMap<Verbosity>)new Internal.EnumLiteMap<Verbosity>() {
            public Verbosity findValueByNumber(final int n) {
                return Verbosity.valueOf(n);
            }
        };
        VALUES = values();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
