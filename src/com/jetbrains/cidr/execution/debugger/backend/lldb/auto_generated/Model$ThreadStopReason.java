// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Internal;
import com.google.protobuf.ProtocolMessageEnum;

public enum ThreadStopReason implements ProtocolMessageEnum
{
    ThreadStopReasonInvalid(0, 0), 
    ThreadStopReasonNone(1, 1), 
    ThreadStopReasonTrace(2, 2), 
    ThreadStopReasonBreakpoint(3, 3), 
    ThreadStopReasonWatchpoint(4, 4), 
    ThreadStopReasonSignal(5, 5), 
    ThreadStopReasonException(6, 6), 
    ThreadStopReasonExec(7, 7), 
    ThreadStopReasonPlanComplete(8, 8), 
    ThreadStopReasonThreadExiting(9, 9), 
    ThreadStopReasonInstrumentation(10, 10);
    
    public static final int ThreadStopReasonInvalid_VALUE = 0;
    public static final int ThreadStopReasonNone_VALUE = 1;
    public static final int ThreadStopReasonTrace_VALUE = 2;
    public static final int ThreadStopReasonBreakpoint_VALUE = 3;
    public static final int ThreadStopReasonWatchpoint_VALUE = 4;
    public static final int ThreadStopReasonSignal_VALUE = 5;
    public static final int ThreadStopReasonException_VALUE = 6;
    public static final int ThreadStopReasonExec_VALUE = 7;
    public static final int ThreadStopReasonPlanComplete_VALUE = 8;
    public static final int ThreadStopReasonThreadExiting_VALUE = 9;
    public static final int ThreadStopReasonInstrumentation_VALUE = 10;
    private static Internal.EnumLiteMap<ThreadStopReason> internalValueMap;
    private static final ThreadStopReason[] VALUES;
    private final int index;
    private final int value;
    
    public final int getNumber() {
        return this.value;
    }
    
    public static ThreadStopReason valueOf(final int n) {
        try {
            switch (n) {
                case 0: {
                    return ThreadStopReason.ThreadStopReasonInvalid;
                }
                case 1: {
                    break;
                }
                case 2: {
                    return ThreadStopReason.ThreadStopReasonTrace;
                }
                case 3: {
                    return ThreadStopReason.ThreadStopReasonBreakpoint;
                }
                case 4: {
                    return ThreadStopReason.ThreadStopReasonWatchpoint;
                }
                case 5: {
                    return ThreadStopReason.ThreadStopReasonSignal;
                }
                case 6: {
                    return ThreadStopReason.ThreadStopReasonException;
                }
                case 7: {
                    return ThreadStopReason.ThreadStopReasonExec;
                }
                case 8: {
                    return ThreadStopReason.ThreadStopReasonPlanComplete;
                }
                case 9: {
                    return ThreadStopReason.ThreadStopReasonThreadExiting;
                }
                case 10: {
                    return ThreadStopReason.ThreadStopReasonInstrumentation;
                }
                default: {
                    return null;
                }
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return ThreadStopReason.ThreadStopReasonNone;
    }
    
    public static Internal.EnumLiteMap<ThreadStopReason> internalGetValueMap() {
        return ThreadStopReason.internalValueMap;
    }
    
    public final Descriptors.EnumValueDescriptor getValueDescriptor() {
        return getDescriptor().getValues().get(this.index);
    }
    
    public final Descriptors.EnumDescriptor getDescriptorForType() {
        return getDescriptor();
    }
    
    public static final Descriptors.EnumDescriptor getDescriptor() {
        return Model.getDescriptor().getEnumTypes().get(2);
    }
    
    public static ThreadStopReason valueOf(final Descriptors.EnumValueDescriptor enumValueDescriptor) {
        try {
            if (enumValueDescriptor.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return ThreadStopReason.VALUES[enumValueDescriptor.getIndex()];
    }
    
    private ThreadStopReason(final int index, final int value) {
        this.index = index;
        this.value = value;
    }
    
    static {
        ThreadStopReason.internalValueMap = (Internal.EnumLiteMap<ThreadStopReason>)new Internal.EnumLiteMap<ThreadStopReason>() {
            public ThreadStopReason findValueByNumber(final int n) {
                return ThreadStopReason.valueOf(n);
            }
        };
        VALUES = values();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
