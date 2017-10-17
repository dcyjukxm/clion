// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Internal;
import com.google.protobuf.ProtocolMessageEnum;

public enum DispatchTarget implements ProtocolMessageEnum
{
    DispatchTargetProcess(0, 0), 
    DispatchTargetDebugger(1, 1);
    
    public static final int DispatchTargetProcess_VALUE = 0;
    public static final int DispatchTargetDebugger_VALUE = 1;
    private static Internal.EnumLiteMap<DispatchTarget> internalValueMap;
    private static final DispatchTarget[] VALUES;
    private final int index;
    private final int value;
    
    public final int getNumber() {
        return this.value;
    }
    
    public static DispatchTarget valueOf(final int n) {
        try {
            switch (n) {
                case 0: {
                    return DispatchTarget.DispatchTargetProcess;
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
        return DispatchTarget.DispatchTargetDebugger;
    }
    
    public static Internal.EnumLiteMap<DispatchTarget> internalGetValueMap() {
        return DispatchTarget.internalValueMap;
    }
    
    public final Descriptors.EnumValueDescriptor getValueDescriptor() {
        return getDescriptor().getValues().get(this.index);
    }
    
    public final Descriptors.EnumDescriptor getDescriptorForType() {
        return getDescriptor();
    }
    
    public static final Descriptors.EnumDescriptor getDescriptor() {
        return Model.getDescriptor().getEnumTypes().get(1);
    }
    
    public static DispatchTarget valueOf(final Descriptors.EnumValueDescriptor enumValueDescriptor) {
        try {
            if (enumValueDescriptor.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return DispatchTarget.VALUES[enumValueDescriptor.getIndex()];
    }
    
    private DispatchTarget(final int index, final int value) {
        this.index = index;
        this.value = value;
    }
    
    static {
        DispatchTarget.internalValueMap = (Internal.EnumLiteMap<DispatchTarget>)new Internal.EnumLiteMap<DispatchTarget>() {
            public DispatchTarget findValueByNumber(final int n) {
                return DispatchTarget.valueOf(n);
            }
        };
        VALUES = values();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
