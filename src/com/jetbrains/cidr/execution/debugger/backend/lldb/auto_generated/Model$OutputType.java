// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Internal;
import com.google.protobuf.ProtocolMessageEnum;

public enum OutputType implements ProtocolMessageEnum
{
    OutputTypeStdout(0, 0), 
    OutputTypeStderr(1, 1);
    
    public static final int OutputTypeStdout_VALUE = 0;
    public static final int OutputTypeStderr_VALUE = 1;
    private static Internal.EnumLiteMap<OutputType> internalValueMap;
    private static final OutputType[] VALUES;
    private final int index;
    private final int value;
    
    public final int getNumber() {
        return this.value;
    }
    
    public static OutputType valueOf(final int n) {
        try {
            switch (n) {
                case 0: {
                    return OutputType.OutputTypeStdout;
                }
                case 1: {
                    break;
                }
                default: {
                    return null;
                }
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return OutputType.OutputTypeStderr;
    }
    
    public static Internal.EnumLiteMap<OutputType> internalGetValueMap() {
        return OutputType.internalValueMap;
    }
    
    public final Descriptors.EnumValueDescriptor getValueDescriptor() {
        return getDescriptor().getValues().get(this.index);
    }
    
    public final Descriptors.EnumDescriptor getDescriptorForType() {
        return getDescriptor();
    }
    
    public static final Descriptors.EnumDescriptor getDescriptor() {
        return Model.getDescriptor().getEnumTypes().get(0);
    }
    
    public static OutputType valueOf(final Descriptors.EnumValueDescriptor enumValueDescriptor) {
        try {
            if (enumValueDescriptor.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return OutputType.VALUES[enumValueDescriptor.getIndex()];
    }
    
    private OutputType(final int index, final int value) {
        this.index = index;
        this.value = value;
    }
    
    static {
        OutputType.internalValueMap = (Internal.EnumLiteMap<OutputType>)new Internal.EnumLiteMap<OutputType>() {
            public OutputType findValueByNumber(final int n) {
                return OutputType.valueOf(n);
            }
        };
        VALUES = values();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
