// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractMessage;
import com.google.protobuf.SingleFieldBuilder;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.Message;
import java.io.InputStream;
import com.google.protobuf.ByteString;
import java.io.ObjectStreamException;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.MessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import java.io.IOException;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.GeneratedMessage;

public static final class CompositeResponse extends GeneratedMessage implements CompositeResponseOrBuilder
{
    private static final CompositeResponse defaultInstance;
    private final UnknownFieldSet unknownFields;
    public static Parser<CompositeResponse> PARSER;
    private int bitField0_;
    private int bitField1_;
    public static final int CREATE_TARGET_FIELD_NUMBER = 1;
    private CreateTarget_Res createTarget_;
    public static final int LAUNCH_FIELD_NUMBER = 2;
    private Launch_Res launch_;
    public static final int CONTINUE_FIELD_NUMBER = 3;
    private Continue_Res continue_;
    public static final int COMPOSITE_BROADCAST_FIELD_NUMBER = 4;
    private Broadcasts.CompositeBroadcast compositeBroadcast_;
    public static final int SUSPEND_FIELD_NUMBER = 5;
    private Suspend_Res suspend_;
    public static final int GET_THREADS_FIELD_NUMBER = 6;
    private GetThreads_Res getThreads_;
    public static final int GET_FRAMES_FIELD_NUMBER = 7;
    private GetFrames_Res getFrames_;
    public static final int ADD_BREAKPOINT_FIELD_NUMBER = 8;
    private AddBreakpoint_Res addBreakpoint_;
    public static final int REMOVE_BREAKPOINT_FIELD_NUMBER = 9;
    private RemoveBreakpoint_Res removeBreakpoint_;
    public static final int STEP_INTO_FIELD_NUMBER = 10;
    private StepInto_Res stepInto_;
    public static final int STEP_OVER_FIELD_NUMBER = 11;
    private StepOver_Res stepOver_;
    public static final int STEP_OUT_FIELD_NUMBER = 12;
    private StepOut_Res stepOut_;
    public static final int EVALUATE_EXPRESSION_FIELD_NUMBER = 13;
    private EvaluateExpression_Res evaluateExpression_;
    public static final int GET_VALUE_CHILDREN_FIELD_NUMBER = 14;
    private GetValueChildren_Res getValueChildren_;
    public static final int GET_VARS_FIELD_NUMBER = 15;
    private GetVars_Res getVars_;
    public static final int HANDLE_CONSOLE_COMMAND_FIELD_NUMBER = 16;
    private HandleConsoleCommand_Res handleConsoleCommand_;
    public static final int HANDLE_COMPLETION_FIELD_NUMBER = 17;
    private HandleCompletion_Res handleCompletion_;
    public static final int ATTACH_FIELD_NUMBER = 18;
    private Attach_Res attach_;
    public static final int ATTACH_BY_NAME_FIELD_NUMBER = 19;
    private AttachByName_Res attachByName_;
    public static final int DISPATCH_INPUT_FIELD_NUMBER = 20;
    private DispatchInput_Res dispatchInput_;
    public static final int ADD_WATCHPOINT_FIELD_NUMBER = 21;
    private AddWatchpoint_Res addWatchpoint_;
    public static final int REMOVE_WATCHPOINT_FIELD_NUMBER = 22;
    private RemoveWatchpoint_Res removeWatchpoint_;
    public static final int DETACH_FIELD_NUMBER = 23;
    private Detach_Res detach_;
    public static final int KILL_FIELD_NUMBER = 24;
    private Kill_Res kill_;
    public static final int GET_CHILDREN_COUNT_FIELD_NUMBER = 25;
    private GetChildrenCount_Res getChildrenCount_;
    public static final int GET_ARRAY_SLICE_FIELD_NUMBER = 26;
    private GetArraySlice_Res getArraySlice_;
    public static final int GET_VALUE_DATA_FIELD_NUMBER = 27;
    private GetValueData_Res getValueData_;
    public static final int GET_VALUE_DESCRIPTION_FIELD_NUMBER = 28;
    private GetValueDescription_Res getValueDescription_;
    public static final int VALUES_FILTERING_POLICY_FIELD_NUMBER = 29;
    private ValuesFilteringPolicy_Res valuesFilteringPolicy_;
    public static final int CONNECT_PLATFORM_FIELD_NUMBER = 30;
    private ConnectPlatform_Res connectPlatform_;
    public static final int GET_VALUE_ADDRESS_FIELD_NUMBER = 31;
    private GetValueAddress_Res getValueAddress_;
    public static final int HANDLE_SIGNAL_FIELD_NUMBER = 32;
    private HandleSignal_Res handleSignal_;
    public static final int EXECUTE_SHELL_COMMAND_FIELD_NUMBER = 33;
    private ExecuteShellCommand_Res executeShellCommand_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private static final long serialVersionUID = 0L;
    
    private CompositeResponse(final GeneratedMessage.Builder<?> builder) {
        super((GeneratedMessage.Builder)builder);
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }
    
    private CompositeResponse(final boolean b) {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }
    
    public static CompositeResponse getDefaultInstance() {
        return CompositeResponse.defaultInstance;
    }
    
    public CompositeResponse getDefaultInstanceForType() {
        return CompositeResponse.defaultInstance;
    }
    
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }
    
    private CompositeResponse(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.a();
        final UnknownFieldSet.Builder builder = UnknownFieldSet.newBuilder();
        try {
            int i = 0;
            while (i == 0) {
                final int tag = codedInputStream.readTag();
                switch (tag) {
                    case 0: {
                        i = 1;
                        continue;
                    }
                    default: {
                        if (!this.parseUnknownField(codedInputStream, builder, extensionRegistryLite, tag)) {
                            i = 1;
                            continue;
                        }
                        continue;
                    }
                    case 10: {
                        CreateTarget_Res.Builder builder2 = null;
                        if ((this.bitField0_ & 0x1) == 0x1) {
                            builder2 = this.createTarget_.toBuilder();
                        }
                        try {
                            this.createTarget_ = (CreateTarget_Res)codedInputStream.readMessage((Parser)CreateTarget_Res.PARSER, extensionRegistryLite);
                            if (builder2 != null) {
                                builder2.mergeFrom(this.createTarget_);
                                this.createTarget_ = builder2.buildPartial();
                            }
                        }
                        catch (InvalidProtocolBufferException ex) {
                            throw a((IOException)ex);
                        }
                        this.bitField0_ |= 0x1;
                        continue;
                    }
                    case 18: {
                        Launch_Res.Builder builder3 = null;
                        if ((this.bitField0_ & 0x2) == 0x2) {
                            builder3 = this.launch_.toBuilder();
                        }
                        try {
                            this.launch_ = (Launch_Res)codedInputStream.readMessage((Parser)Launch_Res.PARSER, extensionRegistryLite);
                            if (builder3 != null) {
                                builder3.mergeFrom(this.launch_);
                                this.launch_ = builder3.buildPartial();
                            }
                        }
                        catch (InvalidProtocolBufferException ex2) {
                            throw a((IOException)ex2);
                        }
                        this.bitField0_ |= 0x2;
                        continue;
                    }
                    case 26: {
                        Continue_Res.Builder builder4 = null;
                        if ((this.bitField0_ & 0x4) == 0x4) {
                            builder4 = this.continue_.toBuilder();
                        }
                        try {
                            this.continue_ = (Continue_Res)codedInputStream.readMessage((Parser)Continue_Res.PARSER, extensionRegistryLite);
                            if (builder4 != null) {
                                builder4.mergeFrom(this.continue_);
                                this.continue_ = builder4.buildPartial();
                            }
                        }
                        catch (InvalidProtocolBufferException ex3) {
                            throw a((IOException)ex3);
                        }
                        this.bitField0_ |= 0x4;
                        continue;
                    }
                    case 34: {
                        Broadcasts.CompositeBroadcast.Builder builder5 = null;
                        if ((this.bitField0_ & 0x8) == 0x8) {
                            builder5 = this.compositeBroadcast_.toBuilder();
                        }
                        try {
                            this.compositeBroadcast_ = (Broadcasts.CompositeBroadcast)codedInputStream.readMessage((Parser)Broadcasts.CompositeBroadcast.PARSER, extensionRegistryLite);
                            if (builder5 != null) {
                                builder5.mergeFrom(this.compositeBroadcast_);
                                this.compositeBroadcast_ = builder5.buildPartial();
                            }
                        }
                        catch (InvalidProtocolBufferException ex4) {
                            throw a((IOException)ex4);
                        }
                        this.bitField0_ |= 0x8;
                        continue;
                    }
                    case 42: {
                        Suspend_Res.Builder builder6 = null;
                        if ((this.bitField0_ & 0x10) == 0x10) {
                            builder6 = this.suspend_.toBuilder();
                        }
                        try {
                            this.suspend_ = (Suspend_Res)codedInputStream.readMessage((Parser)Suspend_Res.PARSER, extensionRegistryLite);
                            if (builder6 != null) {
                                builder6.mergeFrom(this.suspend_);
                                this.suspend_ = builder6.buildPartial();
                            }
                        }
                        catch (InvalidProtocolBufferException ex5) {
                            throw a((IOException)ex5);
                        }
                        this.bitField0_ |= 0x10;
                        continue;
                    }
                    case 50: {
                        GetThreads_Res.Builder builder7 = null;
                        if ((this.bitField0_ & 0x20) == 0x20) {
                            builder7 = this.getThreads_.toBuilder();
                        }
                        try {
                            this.getThreads_ = (GetThreads_Res)codedInputStream.readMessage((Parser)GetThreads_Res.PARSER, extensionRegistryLite);
                            if (builder7 != null) {
                                builder7.mergeFrom(this.getThreads_);
                                this.getThreads_ = builder7.buildPartial();
                            }
                        }
                        catch (InvalidProtocolBufferException ex6) {
                            throw a((IOException)ex6);
                        }
                        this.bitField0_ |= 0x20;
                        continue;
                    }
                    case 58: {
                        GetFrames_Res.Builder builder8 = null;
                        if ((this.bitField0_ & 0x40) == 0x40) {
                            builder8 = this.getFrames_.toBuilder();
                        }
                        try {
                            this.getFrames_ = (GetFrames_Res)codedInputStream.readMessage((Parser)GetFrames_Res.PARSER, extensionRegistryLite);
                            if (builder8 != null) {
                                builder8.mergeFrom(this.getFrames_);
                                this.getFrames_ = builder8.buildPartial();
                            }
                        }
                        catch (InvalidProtocolBufferException ex7) {
                            throw a((IOException)ex7);
                        }
                        this.bitField0_ |= 0x40;
                        continue;
                    }
                    case 66: {
                        AddBreakpoint_Res.Builder builder9 = null;
                        if ((this.bitField0_ & 0x80) == 0x80) {
                            builder9 = this.addBreakpoint_.toBuilder();
                        }
                        try {
                            this.addBreakpoint_ = (AddBreakpoint_Res)codedInputStream.readMessage((Parser)AddBreakpoint_Res.PARSER, extensionRegistryLite);
                            if (builder9 != null) {
                                builder9.mergeFrom(this.addBreakpoint_);
                                this.addBreakpoint_ = builder9.buildPartial();
                            }
                        }
                        catch (InvalidProtocolBufferException ex8) {
                            throw a((IOException)ex8);
                        }
                        this.bitField0_ |= 0x80;
                        continue;
                    }
                    case 74: {
                        RemoveBreakpoint_Res.Builder builder10 = null;
                        if ((this.bitField0_ & 0x100) == 0x100) {
                            builder10 = this.removeBreakpoint_.toBuilder();
                        }
                        try {
                            this.removeBreakpoint_ = (RemoveBreakpoint_Res)codedInputStream.readMessage((Parser)RemoveBreakpoint_Res.PARSER, extensionRegistryLite);
                            if (builder10 != null) {
                                builder10.mergeFrom(this.removeBreakpoint_);
                                this.removeBreakpoint_ = builder10.buildPartial();
                            }
                        }
                        catch (InvalidProtocolBufferException ex9) {
                            throw a((IOException)ex9);
                        }
                        this.bitField0_ |= 0x100;
                        continue;
                    }
                    case 82: {
                        StepInto_Res.Builder builder11 = null;
                        if ((this.bitField0_ & 0x200) == 0x200) {
                            builder11 = this.stepInto_.toBuilder();
                        }
                        try {
                            this.stepInto_ = (StepInto_Res)codedInputStream.readMessage((Parser)StepInto_Res.PARSER, extensionRegistryLite);
                            if (builder11 != null) {
                                builder11.mergeFrom(this.stepInto_);
                                this.stepInto_ = builder11.buildPartial();
                            }
                        }
                        catch (InvalidProtocolBufferException ex10) {
                            throw a((IOException)ex10);
                        }
                        this.bitField0_ |= 0x200;
                        continue;
                    }
                    case 90: {
                        StepOver_Res.Builder builder12 = null;
                        if ((this.bitField0_ & 0x400) == 0x400) {
                            builder12 = this.stepOver_.toBuilder();
                        }
                        try {
                            this.stepOver_ = (StepOver_Res)codedInputStream.readMessage((Parser)StepOver_Res.PARSER, extensionRegistryLite);
                            if (builder12 != null) {
                                builder12.mergeFrom(this.stepOver_);
                                this.stepOver_ = builder12.buildPartial();
                            }
                        }
                        catch (InvalidProtocolBufferException ex11) {
                            throw a((IOException)ex11);
                        }
                        this.bitField0_ |= 0x400;
                        continue;
                    }
                    case 98: {
                        StepOut_Res.Builder builder13 = null;
                        if ((this.bitField0_ & 0x800) == 0x800) {
                            builder13 = this.stepOut_.toBuilder();
                        }
                        try {
                            this.stepOut_ = (StepOut_Res)codedInputStream.readMessage((Parser)StepOut_Res.PARSER, extensionRegistryLite);
                            if (builder13 != null) {
                                builder13.mergeFrom(this.stepOut_);
                                this.stepOut_ = builder13.buildPartial();
                            }
                        }
                        catch (InvalidProtocolBufferException ex12) {
                            throw a((IOException)ex12);
                        }
                        this.bitField0_ |= 0x800;
                        continue;
                    }
                    case 106: {
                        EvaluateExpression_Res.Builder builder14 = null;
                        if ((this.bitField0_ & 0x1000) == 0x1000) {
                            builder14 = this.evaluateExpression_.toBuilder();
                        }
                        try {
                            this.evaluateExpression_ = (EvaluateExpression_Res)codedInputStream.readMessage((Parser)EvaluateExpression_Res.PARSER, extensionRegistryLite);
                            if (builder14 != null) {
                                builder14.mergeFrom(this.evaluateExpression_);
                                this.evaluateExpression_ = builder14.buildPartial();
                            }
                        }
                        catch (InvalidProtocolBufferException ex13) {
                            throw a((IOException)ex13);
                        }
                        this.bitField0_ |= 0x1000;
                        continue;
                    }
                    case 114: {
                        GetValueChildren_Res.Builder builder15 = null;
                        if ((this.bitField0_ & 0x2000) == 0x2000) {
                            builder15 = this.getValueChildren_.toBuilder();
                        }
                        try {
                            this.getValueChildren_ = (GetValueChildren_Res)codedInputStream.readMessage((Parser)GetValueChildren_Res.PARSER, extensionRegistryLite);
                            if (builder15 != null) {
                                builder15.mergeFrom(this.getValueChildren_);
                                this.getValueChildren_ = builder15.buildPartial();
                            }
                        }
                        catch (InvalidProtocolBufferException ex14) {
                            throw a((IOException)ex14);
                        }
                        this.bitField0_ |= 0x2000;
                        continue;
                    }
                    case 122: {
                        GetVars_Res.Builder builder16 = null;
                        if ((this.bitField0_ & 0x4000) == 0x4000) {
                            builder16 = this.getVars_.toBuilder();
                        }
                        try {
                            this.getVars_ = (GetVars_Res)codedInputStream.readMessage((Parser)GetVars_Res.PARSER, extensionRegistryLite);
                            if (builder16 != null) {
                                builder16.mergeFrom(this.getVars_);
                                this.getVars_ = builder16.buildPartial();
                            }
                        }
                        catch (InvalidProtocolBufferException ex15) {
                            throw a((IOException)ex15);
                        }
                        this.bitField0_ |= 0x4000;
                        continue;
                    }
                    case 130: {
                        HandleConsoleCommand_Res.Builder builder17 = null;
                        if ((this.bitField0_ & 0x8000) == 0x8000) {
                            builder17 = this.handleConsoleCommand_.toBuilder();
                        }
                        try {
                            this.handleConsoleCommand_ = (HandleConsoleCommand_Res)codedInputStream.readMessage((Parser)HandleConsoleCommand_Res.PARSER, extensionRegistryLite);
                            if (builder17 != null) {
                                builder17.mergeFrom(this.handleConsoleCommand_);
                                this.handleConsoleCommand_ = builder17.buildPartial();
                            }
                        }
                        catch (InvalidProtocolBufferException ex16) {
                            throw a((IOException)ex16);
                        }
                        this.bitField0_ |= 0x8000;
                        continue;
                    }
                    case 138: {
                        HandleCompletion_Res.Builder builder18 = null;
                        if ((this.bitField0_ & 0x10000) == 0x10000) {
                            builder18 = this.handleCompletion_.toBuilder();
                        }
                        try {
                            this.handleCompletion_ = (HandleCompletion_Res)codedInputStream.readMessage((Parser)HandleCompletion_Res.PARSER, extensionRegistryLite);
                            if (builder18 != null) {
                                builder18.mergeFrom(this.handleCompletion_);
                                this.handleCompletion_ = builder18.buildPartial();
                            }
                        }
                        catch (InvalidProtocolBufferException ex17) {
                            throw a((IOException)ex17);
                        }
                        this.bitField0_ |= 0x10000;
                        continue;
                    }
                    case 146: {
                        Attach_Res.Builder builder19 = null;
                        if ((this.bitField0_ & 0x20000) == 0x20000) {
                            builder19 = this.attach_.toBuilder();
                        }
                        try {
                            this.attach_ = (Attach_Res)codedInputStream.readMessage((Parser)Attach_Res.PARSER, extensionRegistryLite);
                            if (builder19 != null) {
                                builder19.mergeFrom(this.attach_);
                                this.attach_ = builder19.buildPartial();
                            }
                        }
                        catch (InvalidProtocolBufferException ex18) {
                            throw a((IOException)ex18);
                        }
                        this.bitField0_ |= 0x20000;
                        continue;
                    }
                    case 154: {
                        AttachByName_Res.Builder builder20 = null;
                        if ((this.bitField0_ & 0x40000) == 0x40000) {
                            builder20 = this.attachByName_.toBuilder();
                        }
                        try {
                            this.attachByName_ = (AttachByName_Res)codedInputStream.readMessage((Parser)AttachByName_Res.PARSER, extensionRegistryLite);
                            if (builder20 != null) {
                                builder20.mergeFrom(this.attachByName_);
                                this.attachByName_ = builder20.buildPartial();
                            }
                        }
                        catch (InvalidProtocolBufferException ex19) {
                            throw a((IOException)ex19);
                        }
                        this.bitField0_ |= 0x40000;
                        continue;
                    }
                    case 162: {
                        DispatchInput_Res.Builder builder21 = null;
                        if ((this.bitField0_ & 0x80000) == 0x80000) {
                            builder21 = this.dispatchInput_.toBuilder();
                        }
                        try {
                            this.dispatchInput_ = (DispatchInput_Res)codedInputStream.readMessage((Parser)DispatchInput_Res.PARSER, extensionRegistryLite);
                            if (builder21 != null) {
                                builder21.mergeFrom(this.dispatchInput_);
                                this.dispatchInput_ = builder21.buildPartial();
                            }
                        }
                        catch (InvalidProtocolBufferException ex20) {
                            throw a((IOException)ex20);
                        }
                        this.bitField0_ |= 0x80000;
                        continue;
                    }
                    case 170: {
                        AddWatchpoint_Res.Builder builder22 = null;
                        if ((this.bitField0_ & 0x100000) == 0x100000) {
                            builder22 = this.addWatchpoint_.toBuilder();
                        }
                        try {
                            this.addWatchpoint_ = (AddWatchpoint_Res)codedInputStream.readMessage((Parser)AddWatchpoint_Res.PARSER, extensionRegistryLite);
                            if (builder22 != null) {
                                builder22.mergeFrom(this.addWatchpoint_);
                                this.addWatchpoint_ = builder22.buildPartial();
                            }
                        }
                        catch (InvalidProtocolBufferException ex21) {
                            throw a((IOException)ex21);
                        }
                        this.bitField0_ |= 0x100000;
                        continue;
                    }
                    case 178: {
                        RemoveWatchpoint_Res.Builder builder23 = null;
                        if ((this.bitField0_ & 0x200000) == 0x200000) {
                            builder23 = this.removeWatchpoint_.toBuilder();
                        }
                        try {
                            this.removeWatchpoint_ = (RemoveWatchpoint_Res)codedInputStream.readMessage((Parser)RemoveWatchpoint_Res.PARSER, extensionRegistryLite);
                            if (builder23 != null) {
                                builder23.mergeFrom(this.removeWatchpoint_);
                                this.removeWatchpoint_ = builder23.buildPartial();
                            }
                        }
                        catch (InvalidProtocolBufferException ex22) {
                            throw a((IOException)ex22);
                        }
                        this.bitField0_ |= 0x200000;
                        continue;
                    }
                    case 186: {
                        Detach_Res.Builder builder24 = null;
                        if ((this.bitField0_ & 0x400000) == 0x400000) {
                            builder24 = this.detach_.toBuilder();
                        }
                        try {
                            this.detach_ = (Detach_Res)codedInputStream.readMessage((Parser)Detach_Res.PARSER, extensionRegistryLite);
                            if (builder24 != null) {
                                builder24.mergeFrom(this.detach_);
                                this.detach_ = builder24.buildPartial();
                            }
                        }
                        catch (InvalidProtocolBufferException ex23) {
                            throw a((IOException)ex23);
                        }
                        this.bitField0_ |= 0x400000;
                        continue;
                    }
                    case 194: {
                        Kill_Res.Builder builder25 = null;
                        if ((this.bitField0_ & 0x800000) == 0x800000) {
                            builder25 = this.kill_.toBuilder();
                        }
                        try {
                            this.kill_ = (Kill_Res)codedInputStream.readMessage((Parser)Kill_Res.PARSER, extensionRegistryLite);
                            if (builder25 != null) {
                                builder25.mergeFrom(this.kill_);
                                this.kill_ = builder25.buildPartial();
                            }
                        }
                        catch (InvalidProtocolBufferException ex24) {
                            throw a((IOException)ex24);
                        }
                        this.bitField0_ |= 0x800000;
                        continue;
                    }
                    case 202: {
                        GetChildrenCount_Res.Builder builder26 = null;
                        if ((this.bitField0_ & 0x1000000) == 0x1000000) {
                            builder26 = this.getChildrenCount_.toBuilder();
                        }
                        try {
                            this.getChildrenCount_ = (GetChildrenCount_Res)codedInputStream.readMessage((Parser)GetChildrenCount_Res.PARSER, extensionRegistryLite);
                            if (builder26 != null) {
                                builder26.mergeFrom(this.getChildrenCount_);
                                this.getChildrenCount_ = builder26.buildPartial();
                            }
                        }
                        catch (InvalidProtocolBufferException ex25) {
                            throw a((IOException)ex25);
                        }
                        this.bitField0_ |= 0x1000000;
                        continue;
                    }
                    case 210: {
                        GetArraySlice_Res.Builder builder27 = null;
                        if ((this.bitField0_ & 0x2000000) == 0x2000000) {
                            builder27 = this.getArraySlice_.toBuilder();
                        }
                        try {
                            this.getArraySlice_ = (GetArraySlice_Res)codedInputStream.readMessage((Parser)GetArraySlice_Res.PARSER, extensionRegistryLite);
                            if (builder27 != null) {
                                builder27.mergeFrom(this.getArraySlice_);
                                this.getArraySlice_ = builder27.buildPartial();
                            }
                        }
                        catch (InvalidProtocolBufferException ex26) {
                            throw a((IOException)ex26);
                        }
                        this.bitField0_ |= 0x2000000;
                        continue;
                    }
                    case 218: {
                        GetValueData_Res.Builder builder28 = null;
                        if ((this.bitField0_ & 0x4000000) == 0x4000000) {
                            builder28 = this.getValueData_.toBuilder();
                        }
                        try {
                            this.getValueData_ = (GetValueData_Res)codedInputStream.readMessage((Parser)GetValueData_Res.PARSER, extensionRegistryLite);
                            if (builder28 != null) {
                                builder28.mergeFrom(this.getValueData_);
                                this.getValueData_ = builder28.buildPartial();
                            }
                        }
                        catch (InvalidProtocolBufferException ex27) {
                            throw a((IOException)ex27);
                        }
                        this.bitField0_ |= 0x4000000;
                        continue;
                    }
                    case 226: {
                        GetValueDescription_Res.Builder builder29 = null;
                        if ((this.bitField0_ & 0x8000000) == 0x8000000) {
                            builder29 = this.getValueDescription_.toBuilder();
                        }
                        try {
                            this.getValueDescription_ = (GetValueDescription_Res)codedInputStream.readMessage((Parser)GetValueDescription_Res.PARSER, extensionRegistryLite);
                            if (builder29 != null) {
                                builder29.mergeFrom(this.getValueDescription_);
                                this.getValueDescription_ = builder29.buildPartial();
                            }
                        }
                        catch (InvalidProtocolBufferException ex28) {
                            throw a((IOException)ex28);
                        }
                        this.bitField0_ |= 0x8000000;
                        continue;
                    }
                    case 234: {
                        ValuesFilteringPolicy_Res.Builder builder30 = null;
                        if ((this.bitField0_ & 0x10000000) == 0x10000000) {
                            builder30 = this.valuesFilteringPolicy_.toBuilder();
                        }
                        try {
                            this.valuesFilteringPolicy_ = (ValuesFilteringPolicy_Res)codedInputStream.readMessage((Parser)ValuesFilteringPolicy_Res.PARSER, extensionRegistryLite);
                            if (builder30 != null) {
                                builder30.mergeFrom(this.valuesFilteringPolicy_);
                                this.valuesFilteringPolicy_ = builder30.buildPartial();
                            }
                        }
                        catch (InvalidProtocolBufferException ex29) {
                            throw a((IOException)ex29);
                        }
                        this.bitField0_ |= 0x10000000;
                        continue;
                    }
                    case 242: {
                        ConnectPlatform_Res.Builder builder31 = null;
                        if ((this.bitField0_ & 0x20000000) == 0x20000000) {
                            builder31 = this.connectPlatform_.toBuilder();
                        }
                        try {
                            this.connectPlatform_ = (ConnectPlatform_Res)codedInputStream.readMessage((Parser)ConnectPlatform_Res.PARSER, extensionRegistryLite);
                            if (builder31 != null) {
                                builder31.mergeFrom(this.connectPlatform_);
                                this.connectPlatform_ = builder31.buildPartial();
                            }
                        }
                        catch (InvalidProtocolBufferException ex30) {
                            throw a((IOException)ex30);
                        }
                        this.bitField0_ |= 0x20000000;
                        continue;
                    }
                    case 250: {
                        GetValueAddress_Res.Builder builder32 = null;
                        if ((this.bitField0_ & 0x40000000) == 0x40000000) {
                            builder32 = this.getValueAddress_.toBuilder();
                        }
                        try {
                            this.getValueAddress_ = (GetValueAddress_Res)codedInputStream.readMessage((Parser)GetValueAddress_Res.PARSER, extensionRegistryLite);
                            if (builder32 != null) {
                                builder32.mergeFrom(this.getValueAddress_);
                                this.getValueAddress_ = builder32.buildPartial();
                            }
                        }
                        catch (InvalidProtocolBufferException ex31) {
                            throw a((IOException)ex31);
                        }
                        this.bitField0_ |= 0x40000000;
                        continue;
                    }
                    case 258: {
                        HandleSignal_Res.Builder builder33 = null;
                        if ((this.bitField0_ & Integer.MIN_VALUE) == Integer.MIN_VALUE) {
                            builder33 = this.handleSignal_.toBuilder();
                        }
                        try {
                            this.handleSignal_ = (HandleSignal_Res)codedInputStream.readMessage((Parser)HandleSignal_Res.PARSER, extensionRegistryLite);
                            if (builder33 != null) {
                                builder33.mergeFrom(this.handleSignal_);
                                this.handleSignal_ = builder33.buildPartial();
                            }
                        }
                        catch (InvalidProtocolBufferException ex32) {
                            throw a((IOException)ex32);
                        }
                        this.bitField0_ |= Integer.MIN_VALUE;
                        continue;
                    }
                    case 266: {
                        ExecuteShellCommand_Res.Builder builder34 = null;
                        if ((this.bitField1_ & 0x1) == 0x1) {
                            builder34 = this.executeShellCommand_.toBuilder();
                        }
                        try {
                            this.executeShellCommand_ = (ExecuteShellCommand_Res)codedInputStream.readMessage((Parser)ExecuteShellCommand_Res.PARSER, extensionRegistryLite);
                            if (builder34 != null) {
                                builder34.mergeFrom(this.executeShellCommand_);
                                this.executeShellCommand_ = builder34.buildPartial();
                            }
                        }
                        catch (InvalidProtocolBufferException ex33) {
                            throw a((IOException)ex33);
                        }
                        this.bitField1_ |= 0x1;
                        continue;
                    }
                }
            }
        }
        catch (InvalidProtocolBufferException ex34) {
            throw ex34.setUnfinishedMessage((MessageLite)this);
        }
        catch (IOException ex35) {
            throw new InvalidProtocolBufferException(ex35.getMessage()).setUnfinishedMessage((MessageLite)this);
        }
        finally {
            this.unknownFields = builder.build();
            this.makeExtensionsImmutable();
        }
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Protocol.access$69400();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$69500().ensureFieldAccessorsInitialized((Class)CompositeResponse.class, (Class)Builder.class);
    }
    
    public Parser<CompositeResponse> getParserForType() {
        return CompositeResponse.PARSER;
    }
    
    public boolean hasCreateTarget() {
        return (this.bitField0_ & 0x1) == 0x1;
    }
    
    public CreateTarget_Res getCreateTarget() {
        return this.createTarget_;
    }
    
    public CreateTarget_ResOrBuilder getCreateTargetOrBuilder() {
        return this.createTarget_;
    }
    
    public boolean hasLaunch() {
        return (this.bitField0_ & 0x2) == 0x2;
    }
    
    public Launch_Res getLaunch() {
        return this.launch_;
    }
    
    public Launch_ResOrBuilder getLaunchOrBuilder() {
        return this.launch_;
    }
    
    public boolean hasContinue() {
        return (this.bitField0_ & 0x4) == 0x4;
    }
    
    public Continue_Res getContinue() {
        return this.continue_;
    }
    
    public Continue_ResOrBuilder getContinueOrBuilder() {
        return this.continue_;
    }
    
    public boolean hasCompositeBroadcast() {
        return (this.bitField0_ & 0x8) == 0x8;
    }
    
    public Broadcasts.CompositeBroadcast getCompositeBroadcast() {
        return this.compositeBroadcast_;
    }
    
    public Broadcasts.CompositeBroadcastOrBuilder getCompositeBroadcastOrBuilder() {
        return this.compositeBroadcast_;
    }
    
    public boolean hasSuspend() {
        return (this.bitField0_ & 0x10) == 0x10;
    }
    
    public Suspend_Res getSuspend() {
        return this.suspend_;
    }
    
    public Suspend_ResOrBuilder getSuspendOrBuilder() {
        return this.suspend_;
    }
    
    public boolean hasGetThreads() {
        return (this.bitField0_ & 0x20) == 0x20;
    }
    
    public GetThreads_Res getGetThreads() {
        return this.getThreads_;
    }
    
    public GetThreads_ResOrBuilder getGetThreadsOrBuilder() {
        return this.getThreads_;
    }
    
    public boolean hasGetFrames() {
        return (this.bitField0_ & 0x40) == 0x40;
    }
    
    public GetFrames_Res getGetFrames() {
        return this.getFrames_;
    }
    
    public GetFrames_ResOrBuilder getGetFramesOrBuilder() {
        return this.getFrames_;
    }
    
    public boolean hasAddBreakpoint() {
        return (this.bitField0_ & 0x80) == 0x80;
    }
    
    public AddBreakpoint_Res getAddBreakpoint() {
        return this.addBreakpoint_;
    }
    
    public AddBreakpoint_ResOrBuilder getAddBreakpointOrBuilder() {
        return this.addBreakpoint_;
    }
    
    public boolean hasRemoveBreakpoint() {
        return (this.bitField0_ & 0x100) == 0x100;
    }
    
    public RemoveBreakpoint_Res getRemoveBreakpoint() {
        return this.removeBreakpoint_;
    }
    
    public RemoveBreakpoint_ResOrBuilder getRemoveBreakpointOrBuilder() {
        return this.removeBreakpoint_;
    }
    
    public boolean hasStepInto() {
        return (this.bitField0_ & 0x200) == 0x200;
    }
    
    public StepInto_Res getStepInto() {
        return this.stepInto_;
    }
    
    public StepInto_ResOrBuilder getStepIntoOrBuilder() {
        return this.stepInto_;
    }
    
    public boolean hasStepOver() {
        return (this.bitField0_ & 0x400) == 0x400;
    }
    
    public StepOver_Res getStepOver() {
        return this.stepOver_;
    }
    
    public StepOver_ResOrBuilder getStepOverOrBuilder() {
        return this.stepOver_;
    }
    
    public boolean hasStepOut() {
        return (this.bitField0_ & 0x800) == 0x800;
    }
    
    public StepOut_Res getStepOut() {
        return this.stepOut_;
    }
    
    public StepOut_ResOrBuilder getStepOutOrBuilder() {
        return this.stepOut_;
    }
    
    public boolean hasEvaluateExpression() {
        return (this.bitField0_ & 0x1000) == 0x1000;
    }
    
    public EvaluateExpression_Res getEvaluateExpression() {
        return this.evaluateExpression_;
    }
    
    public EvaluateExpression_ResOrBuilder getEvaluateExpressionOrBuilder() {
        return this.evaluateExpression_;
    }
    
    public boolean hasGetValueChildren() {
        return (this.bitField0_ & 0x2000) == 0x2000;
    }
    
    public GetValueChildren_Res getGetValueChildren() {
        return this.getValueChildren_;
    }
    
    public GetValueChildren_ResOrBuilder getGetValueChildrenOrBuilder() {
        return this.getValueChildren_;
    }
    
    public boolean hasGetVars() {
        return (this.bitField0_ & 0x4000) == 0x4000;
    }
    
    public GetVars_Res getGetVars() {
        return this.getVars_;
    }
    
    public GetVars_ResOrBuilder getGetVarsOrBuilder() {
        return this.getVars_;
    }
    
    public boolean hasHandleConsoleCommand() {
        return (this.bitField0_ & 0x8000) == 0x8000;
    }
    
    public HandleConsoleCommand_Res getHandleConsoleCommand() {
        return this.handleConsoleCommand_;
    }
    
    public HandleConsoleCommand_ResOrBuilder getHandleConsoleCommandOrBuilder() {
        return this.handleConsoleCommand_;
    }
    
    public boolean hasHandleCompletion() {
        return (this.bitField0_ & 0x10000) == 0x10000;
    }
    
    public HandleCompletion_Res getHandleCompletion() {
        return this.handleCompletion_;
    }
    
    public HandleCompletion_ResOrBuilder getHandleCompletionOrBuilder() {
        return this.handleCompletion_;
    }
    
    public boolean hasAttach() {
        return (this.bitField0_ & 0x20000) == 0x20000;
    }
    
    public Attach_Res getAttach() {
        return this.attach_;
    }
    
    public Attach_ResOrBuilder getAttachOrBuilder() {
        return this.attach_;
    }
    
    public boolean hasAttachByName() {
        return (this.bitField0_ & 0x40000) == 0x40000;
    }
    
    public AttachByName_Res getAttachByName() {
        return this.attachByName_;
    }
    
    public AttachByName_ResOrBuilder getAttachByNameOrBuilder() {
        return this.attachByName_;
    }
    
    public boolean hasDispatchInput() {
        return (this.bitField0_ & 0x80000) == 0x80000;
    }
    
    public DispatchInput_Res getDispatchInput() {
        return this.dispatchInput_;
    }
    
    public DispatchInput_ResOrBuilder getDispatchInputOrBuilder() {
        return this.dispatchInput_;
    }
    
    public boolean hasAddWatchpoint() {
        return (this.bitField0_ & 0x100000) == 0x100000;
    }
    
    public AddWatchpoint_Res getAddWatchpoint() {
        return this.addWatchpoint_;
    }
    
    public AddWatchpoint_ResOrBuilder getAddWatchpointOrBuilder() {
        return this.addWatchpoint_;
    }
    
    public boolean hasRemoveWatchpoint() {
        return (this.bitField0_ & 0x200000) == 0x200000;
    }
    
    public RemoveWatchpoint_Res getRemoveWatchpoint() {
        return this.removeWatchpoint_;
    }
    
    public RemoveWatchpoint_ResOrBuilder getRemoveWatchpointOrBuilder() {
        return this.removeWatchpoint_;
    }
    
    public boolean hasDetach() {
        return (this.bitField0_ & 0x400000) == 0x400000;
    }
    
    public Detach_Res getDetach() {
        return this.detach_;
    }
    
    public Detach_ResOrBuilder getDetachOrBuilder() {
        return this.detach_;
    }
    
    public boolean hasKill() {
        return (this.bitField0_ & 0x800000) == 0x800000;
    }
    
    public Kill_Res getKill() {
        return this.kill_;
    }
    
    public Kill_ResOrBuilder getKillOrBuilder() {
        return this.kill_;
    }
    
    public boolean hasGetChildrenCount() {
        return (this.bitField0_ & 0x1000000) == 0x1000000;
    }
    
    public GetChildrenCount_Res getGetChildrenCount() {
        return this.getChildrenCount_;
    }
    
    public GetChildrenCount_ResOrBuilder getGetChildrenCountOrBuilder() {
        return this.getChildrenCount_;
    }
    
    public boolean hasGetArraySlice() {
        return (this.bitField0_ & 0x2000000) == 0x2000000;
    }
    
    public GetArraySlice_Res getGetArraySlice() {
        return this.getArraySlice_;
    }
    
    public GetArraySlice_ResOrBuilder getGetArraySliceOrBuilder() {
        return this.getArraySlice_;
    }
    
    public boolean hasGetValueData() {
        return (this.bitField0_ & 0x4000000) == 0x4000000;
    }
    
    public GetValueData_Res getGetValueData() {
        return this.getValueData_;
    }
    
    public GetValueData_ResOrBuilder getGetValueDataOrBuilder() {
        return this.getValueData_;
    }
    
    public boolean hasGetValueDescription() {
        return (this.bitField0_ & 0x8000000) == 0x8000000;
    }
    
    public GetValueDescription_Res getGetValueDescription() {
        return this.getValueDescription_;
    }
    
    public GetValueDescription_ResOrBuilder getGetValueDescriptionOrBuilder() {
        return this.getValueDescription_;
    }
    
    public boolean hasValuesFilteringPolicy() {
        return (this.bitField0_ & 0x10000000) == 0x10000000;
    }
    
    public ValuesFilteringPolicy_Res getValuesFilteringPolicy() {
        return this.valuesFilteringPolicy_;
    }
    
    public ValuesFilteringPolicy_ResOrBuilder getValuesFilteringPolicyOrBuilder() {
        return this.valuesFilteringPolicy_;
    }
    
    public boolean hasConnectPlatform() {
        return (this.bitField0_ & 0x20000000) == 0x20000000;
    }
    
    public ConnectPlatform_Res getConnectPlatform() {
        return this.connectPlatform_;
    }
    
    public ConnectPlatform_ResOrBuilder getConnectPlatformOrBuilder() {
        return this.connectPlatform_;
    }
    
    public boolean hasGetValueAddress() {
        return (this.bitField0_ & 0x40000000) == 0x40000000;
    }
    
    public GetValueAddress_Res getGetValueAddress() {
        return this.getValueAddress_;
    }
    
    public GetValueAddress_ResOrBuilder getGetValueAddressOrBuilder() {
        return this.getValueAddress_;
    }
    
    public boolean hasHandleSignal() {
        return (this.bitField0_ & Integer.MIN_VALUE) == Integer.MIN_VALUE;
    }
    
    public HandleSignal_Res getHandleSignal() {
        return this.handleSignal_;
    }
    
    public HandleSignal_ResOrBuilder getHandleSignalOrBuilder() {
        return this.handleSignal_;
    }
    
    public boolean hasExecuteShellCommand() {
        return (this.bitField1_ & 0x1) == 0x1;
    }
    
    public ExecuteShellCommand_Res getExecuteShellCommand() {
        return this.executeShellCommand_;
    }
    
    public ExecuteShellCommand_ResOrBuilder getExecuteShellCommandOrBuilder() {
        return this.executeShellCommand_;
    }
    
    private void a() {
        this.createTarget_ = CreateTarget_Res.getDefaultInstance();
        this.launch_ = Launch_Res.getDefaultInstance();
        this.continue_ = Continue_Res.getDefaultInstance();
        this.compositeBroadcast_ = Broadcasts.CompositeBroadcast.getDefaultInstance();
        this.suspend_ = Suspend_Res.getDefaultInstance();
        this.getThreads_ = GetThreads_Res.getDefaultInstance();
        this.getFrames_ = GetFrames_Res.getDefaultInstance();
        this.addBreakpoint_ = AddBreakpoint_Res.getDefaultInstance();
        this.removeBreakpoint_ = RemoveBreakpoint_Res.getDefaultInstance();
        this.stepInto_ = StepInto_Res.getDefaultInstance();
        this.stepOver_ = StepOver_Res.getDefaultInstance();
        this.stepOut_ = StepOut_Res.getDefaultInstance();
        this.evaluateExpression_ = EvaluateExpression_Res.getDefaultInstance();
        this.getValueChildren_ = GetValueChildren_Res.getDefaultInstance();
        this.getVars_ = GetVars_Res.getDefaultInstance();
        this.handleConsoleCommand_ = HandleConsoleCommand_Res.getDefaultInstance();
        this.handleCompletion_ = HandleCompletion_Res.getDefaultInstance();
        this.attach_ = Attach_Res.getDefaultInstance();
        this.attachByName_ = AttachByName_Res.getDefaultInstance();
        this.dispatchInput_ = DispatchInput_Res.getDefaultInstance();
        this.addWatchpoint_ = AddWatchpoint_Res.getDefaultInstance();
        this.removeWatchpoint_ = RemoveWatchpoint_Res.getDefaultInstance();
        this.detach_ = Detach_Res.getDefaultInstance();
        this.kill_ = Kill_Res.getDefaultInstance();
        this.getChildrenCount_ = GetChildrenCount_Res.getDefaultInstance();
        this.getArraySlice_ = GetArraySlice_Res.getDefaultInstance();
        this.getValueData_ = GetValueData_Res.getDefaultInstance();
        this.getValueDescription_ = GetValueDescription_Res.getDefaultInstance();
        this.valuesFilteringPolicy_ = ValuesFilteringPolicy_Res.getDefaultInstance();
        this.connectPlatform_ = ConnectPlatform_Res.getDefaultInstance();
        this.getValueAddress_ = GetValueAddress_Res.getDefaultInstance();
        this.handleSignal_ = HandleSignal_Res.getDefaultInstance();
        this.executeShellCommand_ = ExecuteShellCommand_Res.getDefaultInstance();
    }
    
    public final boolean isInitialized() {
        final byte memoizedIsInitialized = this.memoizedIsInitialized;
        if (memoizedIsInitialized != -1) {
            return memoizedIsInitialized == 1;
        }
        if (this.hasCreateTarget() && !this.getCreateTarget().isInitialized()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (this.hasLaunch() && !this.getLaunch().isInitialized()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (this.hasContinue() && !this.getContinue().isInitialized()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (this.hasCompositeBroadcast() && !this.getCompositeBroadcast().isInitialized()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (this.hasSuspend() && !this.getSuspend().isInitialized()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (this.hasGetThreads() && !this.getGetThreads().isInitialized()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (this.hasGetFrames() && !this.getGetFrames().isInitialized()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (this.hasAddBreakpoint() && !this.getAddBreakpoint().isInitialized()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (this.hasRemoveBreakpoint() && !this.getRemoveBreakpoint().isInitialized()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (this.hasStepInto() && !this.getStepInto().isInitialized()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (this.hasStepOver() && !this.getStepOver().isInitialized()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (this.hasStepOut() && !this.getStepOut().isInitialized()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (this.hasEvaluateExpression() && !this.getEvaluateExpression().isInitialized()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (this.hasGetValueChildren() && !this.getGetValueChildren().isInitialized()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (this.hasGetVars() && !this.getGetVars().isInitialized()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (this.hasHandleConsoleCommand() && !this.getHandleConsoleCommand().isInitialized()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (this.hasHandleCompletion() && !this.getHandleCompletion().isInitialized()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (this.hasAttach() && !this.getAttach().isInitialized()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (this.hasAttachByName() && !this.getAttachByName().isInitialized()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (this.hasDispatchInput() && !this.getDispatchInput().isInitialized()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (this.hasAddWatchpoint() && !this.getAddWatchpoint().isInitialized()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (this.hasRemoveWatchpoint() && !this.getRemoveWatchpoint().isInitialized()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (this.hasDetach() && !this.getDetach().isInitialized()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (this.hasKill() && !this.getKill().isInitialized()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (this.hasGetChildrenCount() && !this.getGetChildrenCount().isInitialized()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (this.hasGetArraySlice() && !this.getGetArraySlice().isInitialized()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (this.hasGetValueData() && !this.getGetValueData().isInitialized()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (this.hasGetValueDescription() && !this.getGetValueDescription().isInitialized()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (this.hasValuesFilteringPolicy() && !this.getValuesFilteringPolicy().isInitialized()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (this.hasConnectPlatform() && !this.getConnectPlatform().isInitialized()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (this.hasGetValueAddress() && !this.getGetValueAddress().isInitialized()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (this.hasHandleSignal() && !this.getHandleSignal().isInitialized()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (this.hasExecuteShellCommand() && !this.getExecuteShellCommand().isInitialized()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        this.memoizedIsInitialized = 1;
        return true;
    }
    
    public void writeTo(final CodedOutputStream codedOutputStream) throws IOException {
        try {
            this.getSerializedSize();
            if ((this.bitField0_ & 0x1) == 0x1) {
                codedOutputStream.writeMessage(1, (MessageLite)this.createTarget_);
            }
        }
        catch (IOException ex) {
            throw a(ex);
        }
        try {
            if ((this.bitField0_ & 0x2) == 0x2) {
                codedOutputStream.writeMessage(2, (MessageLite)this.launch_);
            }
        }
        catch (IOException ex2) {
            throw a(ex2);
        }
        try {
            if ((this.bitField0_ & 0x4) == 0x4) {
                codedOutputStream.writeMessage(3, (MessageLite)this.continue_);
            }
        }
        catch (IOException ex3) {
            throw a(ex3);
        }
        try {
            if ((this.bitField0_ & 0x8) == 0x8) {
                codedOutputStream.writeMessage(4, (MessageLite)this.compositeBroadcast_);
            }
        }
        catch (IOException ex4) {
            throw a(ex4);
        }
        try {
            if ((this.bitField0_ & 0x10) == 0x10) {
                codedOutputStream.writeMessage(5, (MessageLite)this.suspend_);
            }
        }
        catch (IOException ex5) {
            throw a(ex5);
        }
        try {
            if ((this.bitField0_ & 0x20) == 0x20) {
                codedOutputStream.writeMessage(6, (MessageLite)this.getThreads_);
            }
        }
        catch (IOException ex6) {
            throw a(ex6);
        }
        try {
            if ((this.bitField0_ & 0x40) == 0x40) {
                codedOutputStream.writeMessage(7, (MessageLite)this.getFrames_);
            }
        }
        catch (IOException ex7) {
            throw a(ex7);
        }
        try {
            if ((this.bitField0_ & 0x80) == 0x80) {
                codedOutputStream.writeMessage(8, (MessageLite)this.addBreakpoint_);
            }
        }
        catch (IOException ex8) {
            throw a(ex8);
        }
        try {
            if ((this.bitField0_ & 0x100) == 0x100) {
                codedOutputStream.writeMessage(9, (MessageLite)this.removeBreakpoint_);
            }
        }
        catch (IOException ex9) {
            throw a(ex9);
        }
        try {
            if ((this.bitField0_ & 0x200) == 0x200) {
                codedOutputStream.writeMessage(10, (MessageLite)this.stepInto_);
            }
        }
        catch (IOException ex10) {
            throw a(ex10);
        }
        try {
            if ((this.bitField0_ & 0x400) == 0x400) {
                codedOutputStream.writeMessage(11, (MessageLite)this.stepOver_);
            }
        }
        catch (IOException ex11) {
            throw a(ex11);
        }
        try {
            if ((this.bitField0_ & 0x800) == 0x800) {
                codedOutputStream.writeMessage(12, (MessageLite)this.stepOut_);
            }
        }
        catch (IOException ex12) {
            throw a(ex12);
        }
        try {
            if ((this.bitField0_ & 0x1000) == 0x1000) {
                codedOutputStream.writeMessage(13, (MessageLite)this.evaluateExpression_);
            }
        }
        catch (IOException ex13) {
            throw a(ex13);
        }
        try {
            if ((this.bitField0_ & 0x2000) == 0x2000) {
                codedOutputStream.writeMessage(14, (MessageLite)this.getValueChildren_);
            }
        }
        catch (IOException ex14) {
            throw a(ex14);
        }
        try {
            if ((this.bitField0_ & 0x4000) == 0x4000) {
                codedOutputStream.writeMessage(15, (MessageLite)this.getVars_);
            }
        }
        catch (IOException ex15) {
            throw a(ex15);
        }
        try {
            if ((this.bitField0_ & 0x8000) == 0x8000) {
                codedOutputStream.writeMessage(16, (MessageLite)this.handleConsoleCommand_);
            }
        }
        catch (IOException ex16) {
            throw a(ex16);
        }
        try {
            if ((this.bitField0_ & 0x10000) == 0x10000) {
                codedOutputStream.writeMessage(17, (MessageLite)this.handleCompletion_);
            }
        }
        catch (IOException ex17) {
            throw a(ex17);
        }
        try {
            if ((this.bitField0_ & 0x20000) == 0x20000) {
                codedOutputStream.writeMessage(18, (MessageLite)this.attach_);
            }
        }
        catch (IOException ex18) {
            throw a(ex18);
        }
        try {
            if ((this.bitField0_ & 0x40000) == 0x40000) {
                codedOutputStream.writeMessage(19, (MessageLite)this.attachByName_);
            }
        }
        catch (IOException ex19) {
            throw a(ex19);
        }
        try {
            if ((this.bitField0_ & 0x80000) == 0x80000) {
                codedOutputStream.writeMessage(20, (MessageLite)this.dispatchInput_);
            }
        }
        catch (IOException ex20) {
            throw a(ex20);
        }
        try {
            if ((this.bitField0_ & 0x100000) == 0x100000) {
                codedOutputStream.writeMessage(21, (MessageLite)this.addWatchpoint_);
            }
        }
        catch (IOException ex21) {
            throw a(ex21);
        }
        try {
            if ((this.bitField0_ & 0x200000) == 0x200000) {
                codedOutputStream.writeMessage(22, (MessageLite)this.removeWatchpoint_);
            }
        }
        catch (IOException ex22) {
            throw a(ex22);
        }
        try {
            if ((this.bitField0_ & 0x400000) == 0x400000) {
                codedOutputStream.writeMessage(23, (MessageLite)this.detach_);
            }
        }
        catch (IOException ex23) {
            throw a(ex23);
        }
        try {
            if ((this.bitField0_ & 0x800000) == 0x800000) {
                codedOutputStream.writeMessage(24, (MessageLite)this.kill_);
            }
        }
        catch (IOException ex24) {
            throw a(ex24);
        }
        try {
            if ((this.bitField0_ & 0x1000000) == 0x1000000) {
                codedOutputStream.writeMessage(25, (MessageLite)this.getChildrenCount_);
            }
        }
        catch (IOException ex25) {
            throw a(ex25);
        }
        try {
            if ((this.bitField0_ & 0x2000000) == 0x2000000) {
                codedOutputStream.writeMessage(26, (MessageLite)this.getArraySlice_);
            }
        }
        catch (IOException ex26) {
            throw a(ex26);
        }
        try {
            if ((this.bitField0_ & 0x4000000) == 0x4000000) {
                codedOutputStream.writeMessage(27, (MessageLite)this.getValueData_);
            }
        }
        catch (IOException ex27) {
            throw a(ex27);
        }
        try {
            if ((this.bitField0_ & 0x8000000) == 0x8000000) {
                codedOutputStream.writeMessage(28, (MessageLite)this.getValueDescription_);
            }
        }
        catch (IOException ex28) {
            throw a(ex28);
        }
        try {
            if ((this.bitField0_ & 0x10000000) == 0x10000000) {
                codedOutputStream.writeMessage(29, (MessageLite)this.valuesFilteringPolicy_);
            }
        }
        catch (IOException ex29) {
            throw a(ex29);
        }
        try {
            if ((this.bitField0_ & 0x20000000) == 0x20000000) {
                codedOutputStream.writeMessage(30, (MessageLite)this.connectPlatform_);
            }
        }
        catch (IOException ex30) {
            throw a(ex30);
        }
        try {
            if ((this.bitField0_ & 0x40000000) == 0x40000000) {
                codedOutputStream.writeMessage(31, (MessageLite)this.getValueAddress_);
            }
        }
        catch (IOException ex31) {
            throw a(ex31);
        }
        try {
            if ((this.bitField0_ & Integer.MIN_VALUE) == Integer.MIN_VALUE) {
                codedOutputStream.writeMessage(32, (MessageLite)this.handleSignal_);
            }
        }
        catch (IOException ex32) {
            throw a(ex32);
        }
        try {
            if ((this.bitField1_ & 0x1) == 0x1) {
                codedOutputStream.writeMessage(33, (MessageLite)this.executeShellCommand_);
            }
        }
        catch (IOException ex33) {
            throw a(ex33);
        }
        this.getUnknownFields().writeTo(codedOutputStream);
    }
    
    public int getSerializedSize() {
        final int memoizedSerializedSize = this.memoizedSerializedSize;
        if (memoizedSerializedSize != -1) {
            return memoizedSerializedSize;
        }
        int n = 0;
        if ((this.bitField0_ & 0x1) == 0x1) {
            n += CodedOutputStream.computeMessageSize(1, (MessageLite)this.createTarget_);
        }
        if ((this.bitField0_ & 0x2) == 0x2) {
            n += CodedOutputStream.computeMessageSize(2, (MessageLite)this.launch_);
        }
        if ((this.bitField0_ & 0x4) == 0x4) {
            n += CodedOutputStream.computeMessageSize(3, (MessageLite)this.continue_);
        }
        if ((this.bitField0_ & 0x8) == 0x8) {
            n += CodedOutputStream.computeMessageSize(4, (MessageLite)this.compositeBroadcast_);
        }
        if ((this.bitField0_ & 0x10) == 0x10) {
            n += CodedOutputStream.computeMessageSize(5, (MessageLite)this.suspend_);
        }
        if ((this.bitField0_ & 0x20) == 0x20) {
            n += CodedOutputStream.computeMessageSize(6, (MessageLite)this.getThreads_);
        }
        if ((this.bitField0_ & 0x40) == 0x40) {
            n += CodedOutputStream.computeMessageSize(7, (MessageLite)this.getFrames_);
        }
        if ((this.bitField0_ & 0x80) == 0x80) {
            n += CodedOutputStream.computeMessageSize(8, (MessageLite)this.addBreakpoint_);
        }
        if ((this.bitField0_ & 0x100) == 0x100) {
            n += CodedOutputStream.computeMessageSize(9, (MessageLite)this.removeBreakpoint_);
        }
        if ((this.bitField0_ & 0x200) == 0x200) {
            n += CodedOutputStream.computeMessageSize(10, (MessageLite)this.stepInto_);
        }
        if ((this.bitField0_ & 0x400) == 0x400) {
            n += CodedOutputStream.computeMessageSize(11, (MessageLite)this.stepOver_);
        }
        if ((this.bitField0_ & 0x800) == 0x800) {
            n += CodedOutputStream.computeMessageSize(12, (MessageLite)this.stepOut_);
        }
        if ((this.bitField0_ & 0x1000) == 0x1000) {
            n += CodedOutputStream.computeMessageSize(13, (MessageLite)this.evaluateExpression_);
        }
        if ((this.bitField0_ & 0x2000) == 0x2000) {
            n += CodedOutputStream.computeMessageSize(14, (MessageLite)this.getValueChildren_);
        }
        if ((this.bitField0_ & 0x4000) == 0x4000) {
            n += CodedOutputStream.computeMessageSize(15, (MessageLite)this.getVars_);
        }
        if ((this.bitField0_ & 0x8000) == 0x8000) {
            n += CodedOutputStream.computeMessageSize(16, (MessageLite)this.handleConsoleCommand_);
        }
        if ((this.bitField0_ & 0x10000) == 0x10000) {
            n += CodedOutputStream.computeMessageSize(17, (MessageLite)this.handleCompletion_);
        }
        if ((this.bitField0_ & 0x20000) == 0x20000) {
            n += CodedOutputStream.computeMessageSize(18, (MessageLite)this.attach_);
        }
        if ((this.bitField0_ & 0x40000) == 0x40000) {
            n += CodedOutputStream.computeMessageSize(19, (MessageLite)this.attachByName_);
        }
        if ((this.bitField0_ & 0x80000) == 0x80000) {
            n += CodedOutputStream.computeMessageSize(20, (MessageLite)this.dispatchInput_);
        }
        if ((this.bitField0_ & 0x100000) == 0x100000) {
            n += CodedOutputStream.computeMessageSize(21, (MessageLite)this.addWatchpoint_);
        }
        if ((this.bitField0_ & 0x200000) == 0x200000) {
            n += CodedOutputStream.computeMessageSize(22, (MessageLite)this.removeWatchpoint_);
        }
        if ((this.bitField0_ & 0x400000) == 0x400000) {
            n += CodedOutputStream.computeMessageSize(23, (MessageLite)this.detach_);
        }
        if ((this.bitField0_ & 0x800000) == 0x800000) {
            n += CodedOutputStream.computeMessageSize(24, (MessageLite)this.kill_);
        }
        if ((this.bitField0_ & 0x1000000) == 0x1000000) {
            n += CodedOutputStream.computeMessageSize(25, (MessageLite)this.getChildrenCount_);
        }
        if ((this.bitField0_ & 0x2000000) == 0x2000000) {
            n += CodedOutputStream.computeMessageSize(26, (MessageLite)this.getArraySlice_);
        }
        if ((this.bitField0_ & 0x4000000) == 0x4000000) {
            n += CodedOutputStream.computeMessageSize(27, (MessageLite)this.getValueData_);
        }
        if ((this.bitField0_ & 0x8000000) == 0x8000000) {
            n += CodedOutputStream.computeMessageSize(28, (MessageLite)this.getValueDescription_);
        }
        if ((this.bitField0_ & 0x10000000) == 0x10000000) {
            n += CodedOutputStream.computeMessageSize(29, (MessageLite)this.valuesFilteringPolicy_);
        }
        if ((this.bitField0_ & 0x20000000) == 0x20000000) {
            n += CodedOutputStream.computeMessageSize(30, (MessageLite)this.connectPlatform_);
        }
        if ((this.bitField0_ & 0x40000000) == 0x40000000) {
            n += CodedOutputStream.computeMessageSize(31, (MessageLite)this.getValueAddress_);
        }
        if ((this.bitField0_ & Integer.MIN_VALUE) == Integer.MIN_VALUE) {
            n += CodedOutputStream.computeMessageSize(32, (MessageLite)this.handleSignal_);
        }
        if ((this.bitField1_ & 0x1) == 0x1) {
            n += CodedOutputStream.computeMessageSize(33, (MessageLite)this.executeShellCommand_);
        }
        return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
    }
    
    protected Object writeReplace() throws ObjectStreamException {
        return super.writeReplace();
    }
    
    public static CompositeResponse parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
        return (CompositeResponse)CompositeResponse.PARSER.parseFrom(byteString);
    }
    
    public static CompositeResponse parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CompositeResponse)CompositeResponse.PARSER.parseFrom(byteString, extensionRegistryLite);
    }
    
    public static CompositeResponse parseFrom(final byte[] array) throws InvalidProtocolBufferException {
        return (CompositeResponse)CompositeResponse.PARSER.parseFrom(array);
    }
    
    public static CompositeResponse parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CompositeResponse)CompositeResponse.PARSER.parseFrom(array, extensionRegistryLite);
    }
    
    public static CompositeResponse parseFrom(final InputStream inputStream) throws IOException {
        return (CompositeResponse)CompositeResponse.PARSER.parseFrom(inputStream);
    }
    
    public static CompositeResponse parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CompositeResponse)CompositeResponse.PARSER.parseFrom(inputStream, extensionRegistryLite);
    }
    
    public static CompositeResponse parseDelimitedFrom(final InputStream inputStream) throws IOException {
        return (CompositeResponse)CompositeResponse.PARSER.parseDelimitedFrom(inputStream);
    }
    
    public static CompositeResponse parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CompositeResponse)CompositeResponse.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }
    
    public static CompositeResponse parseFrom(final CodedInputStream codedInputStream) throws IOException {
        return (CompositeResponse)CompositeResponse.PARSER.parseFrom(codedInputStream);
    }
    
    public static CompositeResponse parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CompositeResponse)CompositeResponse.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }
    
    public static Builder newBuilder() {
        return j();
    }
    
    public Builder newBuilderForType() {
        return newBuilder();
    }
    
    public static Builder newBuilder(final CompositeResponse compositeResponse) {
        return newBuilder().mergeFrom(compositeResponse);
    }
    
    public Builder toBuilder() {
        return newBuilder(this);
    }
    
    protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
        return new Builder(builderParent);
    }
    
    static {
        CompositeResponse.PARSER = (Parser<CompositeResponse>)new AbstractParser<CompositeResponse>() {
            public CompositeResponse parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new CompositeResponse(codedInputStream, extensionRegistryLite);
            }
        };
        (defaultInstance = new CompositeResponse(true)).a();
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
    
    public static final class Builder extends GeneratedMessage.Builder<Builder> implements CompositeResponseOrBuilder
    {
        private int bitField0_;
        private int bitField1_;
        private CreateTarget_Res createTarget_;
        private SingleFieldBuilder<CreateTarget_Res, CreateTarget_Res.Builder, CreateTarget_ResOrBuilder> createTargetBuilder_;
        private Launch_Res launch_;
        private SingleFieldBuilder<Launch_Res, Launch_Res.Builder, Launch_ResOrBuilder> launchBuilder_;
        private Continue_Res continue_;
        private SingleFieldBuilder<Continue_Res, Continue_Res.Builder, Continue_ResOrBuilder> continueBuilder_;
        private Broadcasts.CompositeBroadcast compositeBroadcast_;
        private SingleFieldBuilder<Broadcasts.CompositeBroadcast, Broadcasts.CompositeBroadcast.Builder, Broadcasts.CompositeBroadcastOrBuilder> compositeBroadcastBuilder_;
        private Suspend_Res suspend_;
        private SingleFieldBuilder<Suspend_Res, Suspend_Res.Builder, Suspend_ResOrBuilder> suspendBuilder_;
        private GetThreads_Res getThreads_;
        private SingleFieldBuilder<GetThreads_Res, GetThreads_Res.Builder, GetThreads_ResOrBuilder> getThreadsBuilder_;
        private GetFrames_Res getFrames_;
        private SingleFieldBuilder<GetFrames_Res, GetFrames_Res.Builder, GetFrames_ResOrBuilder> getFramesBuilder_;
        private AddBreakpoint_Res addBreakpoint_;
        private SingleFieldBuilder<AddBreakpoint_Res, AddBreakpoint_Res.Builder, AddBreakpoint_ResOrBuilder> addBreakpointBuilder_;
        private RemoveBreakpoint_Res removeBreakpoint_;
        private SingleFieldBuilder<RemoveBreakpoint_Res, RemoveBreakpoint_Res.Builder, RemoveBreakpoint_ResOrBuilder> removeBreakpointBuilder_;
        private StepInto_Res stepInto_;
        private SingleFieldBuilder<StepInto_Res, StepInto_Res.Builder, StepInto_ResOrBuilder> stepIntoBuilder_;
        private StepOver_Res stepOver_;
        private SingleFieldBuilder<StepOver_Res, StepOver_Res.Builder, StepOver_ResOrBuilder> stepOverBuilder_;
        private StepOut_Res stepOut_;
        private SingleFieldBuilder<StepOut_Res, StepOut_Res.Builder, StepOut_ResOrBuilder> stepOutBuilder_;
        private EvaluateExpression_Res evaluateExpression_;
        private SingleFieldBuilder<EvaluateExpression_Res, EvaluateExpression_Res.Builder, EvaluateExpression_ResOrBuilder> evaluateExpressionBuilder_;
        private GetValueChildren_Res getValueChildren_;
        private SingleFieldBuilder<GetValueChildren_Res, GetValueChildren_Res.Builder, GetValueChildren_ResOrBuilder> getValueChildrenBuilder_;
        private GetVars_Res getVars_;
        private SingleFieldBuilder<GetVars_Res, GetVars_Res.Builder, GetVars_ResOrBuilder> getVarsBuilder_;
        private HandleConsoleCommand_Res handleConsoleCommand_;
        private SingleFieldBuilder<HandleConsoleCommand_Res, HandleConsoleCommand_Res.Builder, HandleConsoleCommand_ResOrBuilder> handleConsoleCommandBuilder_;
        private HandleCompletion_Res handleCompletion_;
        private SingleFieldBuilder<HandleCompletion_Res, HandleCompletion_Res.Builder, HandleCompletion_ResOrBuilder> handleCompletionBuilder_;
        private Attach_Res attach_;
        private SingleFieldBuilder<Attach_Res, Attach_Res.Builder, Attach_ResOrBuilder> attachBuilder_;
        private AttachByName_Res attachByName_;
        private SingleFieldBuilder<AttachByName_Res, AttachByName_Res.Builder, AttachByName_ResOrBuilder> attachByNameBuilder_;
        private DispatchInput_Res dispatchInput_;
        private SingleFieldBuilder<DispatchInput_Res, DispatchInput_Res.Builder, DispatchInput_ResOrBuilder> dispatchInputBuilder_;
        private AddWatchpoint_Res addWatchpoint_;
        private SingleFieldBuilder<AddWatchpoint_Res, AddWatchpoint_Res.Builder, AddWatchpoint_ResOrBuilder> addWatchpointBuilder_;
        private RemoveWatchpoint_Res removeWatchpoint_;
        private SingleFieldBuilder<RemoveWatchpoint_Res, RemoveWatchpoint_Res.Builder, RemoveWatchpoint_ResOrBuilder> removeWatchpointBuilder_;
        private Detach_Res detach_;
        private SingleFieldBuilder<Detach_Res, Detach_Res.Builder, Detach_ResOrBuilder> detachBuilder_;
        private Kill_Res kill_;
        private SingleFieldBuilder<Kill_Res, Kill_Res.Builder, Kill_ResOrBuilder> killBuilder_;
        private GetChildrenCount_Res getChildrenCount_;
        private SingleFieldBuilder<GetChildrenCount_Res, GetChildrenCount_Res.Builder, GetChildrenCount_ResOrBuilder> getChildrenCountBuilder_;
        private GetArraySlice_Res getArraySlice_;
        private SingleFieldBuilder<GetArraySlice_Res, GetArraySlice_Res.Builder, GetArraySlice_ResOrBuilder> getArraySliceBuilder_;
        private GetValueData_Res getValueData_;
        private SingleFieldBuilder<GetValueData_Res, GetValueData_Res.Builder, GetValueData_ResOrBuilder> getValueDataBuilder_;
        private GetValueDescription_Res getValueDescription_;
        private SingleFieldBuilder<GetValueDescription_Res, GetValueDescription_Res.Builder, GetValueDescription_ResOrBuilder> getValueDescriptionBuilder_;
        private ValuesFilteringPolicy_Res valuesFilteringPolicy_;
        private SingleFieldBuilder<ValuesFilteringPolicy_Res, ValuesFilteringPolicy_Res.Builder, ValuesFilteringPolicy_ResOrBuilder> valuesFilteringPolicyBuilder_;
        private ConnectPlatform_Res connectPlatform_;
        private SingleFieldBuilder<ConnectPlatform_Res, ConnectPlatform_Res.Builder, ConnectPlatform_ResOrBuilder> connectPlatformBuilder_;
        private GetValueAddress_Res getValueAddress_;
        private SingleFieldBuilder<GetValueAddress_Res, GetValueAddress_Res.Builder, GetValueAddress_ResOrBuilder> getValueAddressBuilder_;
        private HandleSignal_Res handleSignal_;
        private SingleFieldBuilder<HandleSignal_Res, HandleSignal_Res.Builder, HandleSignal_ResOrBuilder> handleSignalBuilder_;
        private ExecuteShellCommand_Res executeShellCommand_;
        private SingleFieldBuilder<ExecuteShellCommand_Res, ExecuteShellCommand_Res.Builder, ExecuteShellCommand_ResOrBuilder> executeShellCommandBuilder_;
        
        public static final Descriptors.Descriptor getDescriptor() {
            return Protocol.access$69400();
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Protocol.access$69500().ensureFieldAccessorsInitialized((Class)CompositeResponse.class, (Class)Builder.class);
        }
        
        private Builder() {
            this.createTarget_ = CreateTarget_Res.getDefaultInstance();
            this.launch_ = Launch_Res.getDefaultInstance();
            this.continue_ = Continue_Res.getDefaultInstance();
            this.compositeBroadcast_ = Broadcasts.CompositeBroadcast.getDefaultInstance();
            this.suspend_ = Suspend_Res.getDefaultInstance();
            this.getThreads_ = GetThreads_Res.getDefaultInstance();
            this.getFrames_ = GetFrames_Res.getDefaultInstance();
            this.addBreakpoint_ = AddBreakpoint_Res.getDefaultInstance();
            this.removeBreakpoint_ = RemoveBreakpoint_Res.getDefaultInstance();
            this.stepInto_ = StepInto_Res.getDefaultInstance();
            this.stepOver_ = StepOver_Res.getDefaultInstance();
            this.stepOut_ = StepOut_Res.getDefaultInstance();
            this.evaluateExpression_ = EvaluateExpression_Res.getDefaultInstance();
            this.getValueChildren_ = GetValueChildren_Res.getDefaultInstance();
            this.getVars_ = GetVars_Res.getDefaultInstance();
            this.handleConsoleCommand_ = HandleConsoleCommand_Res.getDefaultInstance();
            this.handleCompletion_ = HandleCompletion_Res.getDefaultInstance();
            this.attach_ = Attach_Res.getDefaultInstance();
            this.attachByName_ = AttachByName_Res.getDefaultInstance();
            this.dispatchInput_ = DispatchInput_Res.getDefaultInstance();
            this.addWatchpoint_ = AddWatchpoint_Res.getDefaultInstance();
            this.removeWatchpoint_ = RemoveWatchpoint_Res.getDefaultInstance();
            this.detach_ = Detach_Res.getDefaultInstance();
            this.kill_ = Kill_Res.getDefaultInstance();
            this.getChildrenCount_ = GetChildrenCount_Res.getDefaultInstance();
            this.getArraySlice_ = GetArraySlice_Res.getDefaultInstance();
            this.getValueData_ = GetValueData_Res.getDefaultInstance();
            this.getValueDescription_ = GetValueDescription_Res.getDefaultInstance();
            this.valuesFilteringPolicy_ = ValuesFilteringPolicy_Res.getDefaultInstance();
            this.connectPlatform_ = ConnectPlatform_Res.getDefaultInstance();
            this.getValueAddress_ = GetValueAddress_Res.getDefaultInstance();
            this.handleSignal_ = HandleSignal_Res.getDefaultInstance();
            this.executeShellCommand_ = ExecuteShellCommand_Res.getDefaultInstance();
            this.y();
        }
        
        private Builder(final GeneratedMessage.BuilderParent builderParent) {
            super(builderParent);
            this.createTarget_ = CreateTarget_Res.getDefaultInstance();
            this.launch_ = Launch_Res.getDefaultInstance();
            this.continue_ = Continue_Res.getDefaultInstance();
            this.compositeBroadcast_ = Broadcasts.CompositeBroadcast.getDefaultInstance();
            this.suspend_ = Suspend_Res.getDefaultInstance();
            this.getThreads_ = GetThreads_Res.getDefaultInstance();
            this.getFrames_ = GetFrames_Res.getDefaultInstance();
            this.addBreakpoint_ = AddBreakpoint_Res.getDefaultInstance();
            this.removeBreakpoint_ = RemoveBreakpoint_Res.getDefaultInstance();
            this.stepInto_ = StepInto_Res.getDefaultInstance();
            this.stepOver_ = StepOver_Res.getDefaultInstance();
            this.stepOut_ = StepOut_Res.getDefaultInstance();
            this.evaluateExpression_ = EvaluateExpression_Res.getDefaultInstance();
            this.getValueChildren_ = GetValueChildren_Res.getDefaultInstance();
            this.getVars_ = GetVars_Res.getDefaultInstance();
            this.handleConsoleCommand_ = HandleConsoleCommand_Res.getDefaultInstance();
            this.handleCompletion_ = HandleCompletion_Res.getDefaultInstance();
            this.attach_ = Attach_Res.getDefaultInstance();
            this.attachByName_ = AttachByName_Res.getDefaultInstance();
            this.dispatchInput_ = DispatchInput_Res.getDefaultInstance();
            this.addWatchpoint_ = AddWatchpoint_Res.getDefaultInstance();
            this.removeWatchpoint_ = RemoveWatchpoint_Res.getDefaultInstance();
            this.detach_ = Detach_Res.getDefaultInstance();
            this.kill_ = Kill_Res.getDefaultInstance();
            this.getChildrenCount_ = GetChildrenCount_Res.getDefaultInstance();
            this.getArraySlice_ = GetArraySlice_Res.getDefaultInstance();
            this.getValueData_ = GetValueData_Res.getDefaultInstance();
            this.getValueDescription_ = GetValueDescription_Res.getDefaultInstance();
            this.valuesFilteringPolicy_ = ValuesFilteringPolicy_Res.getDefaultInstance();
            this.connectPlatform_ = ConnectPlatform_Res.getDefaultInstance();
            this.getValueAddress_ = GetValueAddress_Res.getDefaultInstance();
            this.handleSignal_ = HandleSignal_Res.getDefaultInstance();
            this.executeShellCommand_ = ExecuteShellCommand_Res.getDefaultInstance();
            this.y();
        }
        
        private void y() {
            try {
                if (CompositeResponse.alwaysUseFieldBuilders) {
                    this.x();
                    this.r();
                    this.v();
                    this.B();
                    this.p();
                    this.C();
                    this.a();
                    this.I();
                    this.h();
                    this.k();
                    this.i();
                    this.F();
                    this.e();
                    this.q();
                    this.f();
                    this.H();
                    this.A();
                    this.G();
                    this.m();
                    this.n();
                    this.l();
                    this.b();
                    this.c();
                    this.g();
                    this.o();
                    this.d();
                    this.w();
                    this.s();
                    this.D();
                    this.E();
                    this.z();
                    this.u();
                    this.t();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
        }
        
        private static Builder j() {
            return new Builder();
        }
        
        public Builder clear() {
            Label_1322: {
                Label_1282: {
                    Label_1242: {
                        Label_1202: {
                            Label_1162: {
                                Label_1122: {
                                    Label_1082: {
                                        Label_1042: {
                                            Label_1002: {
                                                Label_0962: {
                                                    Label_0922: {
                                                        Label_0882: {
                                                            Label_0842: {
                                                                Label_0802: {
                                                                    Label_0762: {
                                                                        Label_0722: {
                                                                            Label_0682: {
                                                                                Label_0642: {
                                                                                    Label_0601: {
                                                                                        Label_0560: {
                                                                                            Label_0519: {
                                                                                                Label_0478: {
                                                                                                    Label_0437: {
                                                                                                        Label_0396: {
                                                                                                            Label_0355: {
                                                                                                                Label_0314: {
                                                                                                                    Label_0274: {
                                                                                                                        Label_0234: {
                                                                                                                            Label_0194: {
                                                                                                                                Label_0154: {
                                                                                                                                    Label_0114: {
                                                                                                                                        Label_0074: {
                                                                                                                                            Label_0034: {
                                                                                                                                                try {
                                                                                                                                                    super.clear();
                                                                                                                                                    if (this.createTargetBuilder_ == null) {
                                                                                                                                                        this.createTarget_ = CreateTarget_Res.getDefaultInstance();
                                                                                                                                                        break Label_0034;
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                                catch (NullPointerException ex) {
                                                                                                                                                    throw b(ex);
                                                                                                                                                }
                                                                                                                                                this.createTargetBuilder_.clear();
                                                                                                                                                try {
                                                                                                                                                    this.bitField0_ &= 0xFFFFFFFE;
                                                                                                                                                    if (this.launchBuilder_ == null) {
                                                                                                                                                        this.launch_ = Launch_Res.getDefaultInstance();
                                                                                                                                                        break Label_0074;
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                                catch (NullPointerException ex2) {
                                                                                                                                                    throw b(ex2);
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                            this.launchBuilder_.clear();
                                                                                                                                            try {
                                                                                                                                                this.bitField0_ &= 0xFFFFFFFD;
                                                                                                                                                if (this.continueBuilder_ == null) {
                                                                                                                                                    this.continue_ = Continue_Res.getDefaultInstance();
                                                                                                                                                    break Label_0114;
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                            catch (NullPointerException ex3) {
                                                                                                                                                throw b(ex3);
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                        this.continueBuilder_.clear();
                                                                                                                                        try {
                                                                                                                                            this.bitField0_ &= 0xFFFFFFFB;
                                                                                                                                            if (this.compositeBroadcastBuilder_ == null) {
                                                                                                                                                this.compositeBroadcast_ = Broadcasts.CompositeBroadcast.getDefaultInstance();
                                                                                                                                                break Label_0154;
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                        catch (NullPointerException ex4) {
                                                                                                                                            throw b(ex4);
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                    this.compositeBroadcastBuilder_.clear();
                                                                                                                                    try {
                                                                                                                                        this.bitField0_ &= 0xFFFFFFF7;
                                                                                                                                        if (this.suspendBuilder_ == null) {
                                                                                                                                            this.suspend_ = Suspend_Res.getDefaultInstance();
                                                                                                                                            break Label_0194;
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                    catch (NullPointerException ex5) {
                                                                                                                                        throw b(ex5);
                                                                                                                                    }
                                                                                                                                }
                                                                                                                                this.suspendBuilder_.clear();
                                                                                                                                try {
                                                                                                                                    this.bitField0_ &= 0xFFFFFFEF;
                                                                                                                                    if (this.getThreadsBuilder_ == null) {
                                                                                                                                        this.getThreads_ = GetThreads_Res.getDefaultInstance();
                                                                                                                                        break Label_0234;
                                                                                                                                    }
                                                                                                                                }
                                                                                                                                catch (NullPointerException ex6) {
                                                                                                                                    throw b(ex6);
                                                                                                                                }
                                                                                                                            }
                                                                                                                            this.getThreadsBuilder_.clear();
                                                                                                                            try {
                                                                                                                                this.bitField0_ &= 0xFFFFFFDF;
                                                                                                                                if (this.getFramesBuilder_ == null) {
                                                                                                                                    this.getFrames_ = GetFrames_Res.getDefaultInstance();
                                                                                                                                    break Label_0274;
                                                                                                                                }
                                                                                                                            }
                                                                                                                            catch (NullPointerException ex7) {
                                                                                                                                throw b(ex7);
                                                                                                                            }
                                                                                                                        }
                                                                                                                        this.getFramesBuilder_.clear();
                                                                                                                        try {
                                                                                                                            this.bitField0_ &= 0xFFFFFFBF;
                                                                                                                            if (this.addBreakpointBuilder_ == null) {
                                                                                                                                this.addBreakpoint_ = AddBreakpoint_Res.getDefaultInstance();
                                                                                                                                break Label_0314;
                                                                                                                            }
                                                                                                                        }
                                                                                                                        catch (NullPointerException ex8) {
                                                                                                                            throw b(ex8);
                                                                                                                        }
                                                                                                                    }
                                                                                                                    this.addBreakpointBuilder_.clear();
                                                                                                                    try {
                                                                                                                        this.bitField0_ &= 0xFFFFFF7F;
                                                                                                                        if (this.removeBreakpointBuilder_ == null) {
                                                                                                                            this.removeBreakpoint_ = RemoveBreakpoint_Res.getDefaultInstance();
                                                                                                                            break Label_0355;
                                                                                                                        }
                                                                                                                    }
                                                                                                                    catch (NullPointerException ex9) {
                                                                                                                        throw b(ex9);
                                                                                                                    }
                                                                                                                }
                                                                                                                this.removeBreakpointBuilder_.clear();
                                                                                                                try {
                                                                                                                    this.bitField0_ &= 0xFFFFFEFF;
                                                                                                                    if (this.stepIntoBuilder_ == null) {
                                                                                                                        this.stepInto_ = StepInto_Res.getDefaultInstance();
                                                                                                                        break Label_0396;
                                                                                                                    }
                                                                                                                }
                                                                                                                catch (NullPointerException ex10) {
                                                                                                                    throw b(ex10);
                                                                                                                }
                                                                                                            }
                                                                                                            this.stepIntoBuilder_.clear();
                                                                                                            try {
                                                                                                                this.bitField0_ &= 0xFFFFFDFF;
                                                                                                                if (this.stepOverBuilder_ == null) {
                                                                                                                    this.stepOver_ = StepOver_Res.getDefaultInstance();
                                                                                                                    break Label_0437;
                                                                                                                }
                                                                                                            }
                                                                                                            catch (NullPointerException ex11) {
                                                                                                                throw b(ex11);
                                                                                                            }
                                                                                                        }
                                                                                                        this.stepOverBuilder_.clear();
                                                                                                        try {
                                                                                                            this.bitField0_ &= 0xFFFFFBFF;
                                                                                                            if (this.stepOutBuilder_ == null) {
                                                                                                                this.stepOut_ = StepOut_Res.getDefaultInstance();
                                                                                                                break Label_0478;
                                                                                                            }
                                                                                                        }
                                                                                                        catch (NullPointerException ex12) {
                                                                                                            throw b(ex12);
                                                                                                        }
                                                                                                    }
                                                                                                    this.stepOutBuilder_.clear();
                                                                                                    try {
                                                                                                        this.bitField0_ &= 0xFFFFF7FF;
                                                                                                        if (this.evaluateExpressionBuilder_ == null) {
                                                                                                            this.evaluateExpression_ = EvaluateExpression_Res.getDefaultInstance();
                                                                                                            break Label_0519;
                                                                                                        }
                                                                                                    }
                                                                                                    catch (NullPointerException ex13) {
                                                                                                        throw b(ex13);
                                                                                                    }
                                                                                                }
                                                                                                this.evaluateExpressionBuilder_.clear();
                                                                                                try {
                                                                                                    this.bitField0_ &= 0xFFFFEFFF;
                                                                                                    if (this.getValueChildrenBuilder_ == null) {
                                                                                                        this.getValueChildren_ = GetValueChildren_Res.getDefaultInstance();
                                                                                                        break Label_0560;
                                                                                                    }
                                                                                                }
                                                                                                catch (NullPointerException ex14) {
                                                                                                    throw b(ex14);
                                                                                                }
                                                                                            }
                                                                                            this.getValueChildrenBuilder_.clear();
                                                                                            try {
                                                                                                this.bitField0_ &= 0xFFFFDFFF;
                                                                                                if (this.getVarsBuilder_ == null) {
                                                                                                    this.getVars_ = GetVars_Res.getDefaultInstance();
                                                                                                    break Label_0601;
                                                                                                }
                                                                                            }
                                                                                            catch (NullPointerException ex15) {
                                                                                                throw b(ex15);
                                                                                            }
                                                                                        }
                                                                                        this.getVarsBuilder_.clear();
                                                                                        try {
                                                                                            this.bitField0_ &= 0xFFFFBFFF;
                                                                                            if (this.handleConsoleCommandBuilder_ == null) {
                                                                                                this.handleConsoleCommand_ = HandleConsoleCommand_Res.getDefaultInstance();
                                                                                                break Label_0642;
                                                                                            }
                                                                                        }
                                                                                        catch (NullPointerException ex16) {
                                                                                            throw b(ex16);
                                                                                        }
                                                                                    }
                                                                                    this.handleConsoleCommandBuilder_.clear();
                                                                                    try {
                                                                                        this.bitField0_ &= 0xFFFF7FFF;
                                                                                        if (this.handleCompletionBuilder_ == null) {
                                                                                            this.handleCompletion_ = HandleCompletion_Res.getDefaultInstance();
                                                                                            break Label_0682;
                                                                                        }
                                                                                    }
                                                                                    catch (NullPointerException ex17) {
                                                                                        throw b(ex17);
                                                                                    }
                                                                                }
                                                                                this.handleCompletionBuilder_.clear();
                                                                                try {
                                                                                    this.bitField0_ &= 0xFFFEFFFF;
                                                                                    if (this.attachBuilder_ == null) {
                                                                                        this.attach_ = Attach_Res.getDefaultInstance();
                                                                                        break Label_0722;
                                                                                    }
                                                                                }
                                                                                catch (NullPointerException ex18) {
                                                                                    throw b(ex18);
                                                                                }
                                                                            }
                                                                            this.attachBuilder_.clear();
                                                                            try {
                                                                                this.bitField0_ &= 0xFFFDFFFF;
                                                                                if (this.attachByNameBuilder_ == null) {
                                                                                    this.attachByName_ = AttachByName_Res.getDefaultInstance();
                                                                                    break Label_0762;
                                                                                }
                                                                            }
                                                                            catch (NullPointerException ex19) {
                                                                                throw b(ex19);
                                                                            }
                                                                        }
                                                                        this.attachByNameBuilder_.clear();
                                                                        try {
                                                                            this.bitField0_ &= 0xFFFBFFFF;
                                                                            if (this.dispatchInputBuilder_ == null) {
                                                                                this.dispatchInput_ = DispatchInput_Res.getDefaultInstance();
                                                                                break Label_0802;
                                                                            }
                                                                        }
                                                                        catch (NullPointerException ex20) {
                                                                            throw b(ex20);
                                                                        }
                                                                    }
                                                                    this.dispatchInputBuilder_.clear();
                                                                    try {
                                                                        this.bitField0_ &= 0xFFF7FFFF;
                                                                        if (this.addWatchpointBuilder_ == null) {
                                                                            this.addWatchpoint_ = AddWatchpoint_Res.getDefaultInstance();
                                                                            break Label_0842;
                                                                        }
                                                                    }
                                                                    catch (NullPointerException ex21) {
                                                                        throw b(ex21);
                                                                    }
                                                                }
                                                                this.addWatchpointBuilder_.clear();
                                                                try {
                                                                    this.bitField0_ &= 0xFFEFFFFF;
                                                                    if (this.removeWatchpointBuilder_ == null) {
                                                                        this.removeWatchpoint_ = RemoveWatchpoint_Res.getDefaultInstance();
                                                                        break Label_0882;
                                                                    }
                                                                }
                                                                catch (NullPointerException ex22) {
                                                                    throw b(ex22);
                                                                }
                                                            }
                                                            this.removeWatchpointBuilder_.clear();
                                                            try {
                                                                this.bitField0_ &= 0xFFDFFFFF;
                                                                if (this.detachBuilder_ == null) {
                                                                    this.detach_ = Detach_Res.getDefaultInstance();
                                                                    break Label_0922;
                                                                }
                                                            }
                                                            catch (NullPointerException ex23) {
                                                                throw b(ex23);
                                                            }
                                                        }
                                                        this.detachBuilder_.clear();
                                                        try {
                                                            this.bitField0_ &= 0xFFBFFFFF;
                                                            if (this.killBuilder_ == null) {
                                                                this.kill_ = Kill_Res.getDefaultInstance();
                                                                break Label_0962;
                                                            }
                                                        }
                                                        catch (NullPointerException ex24) {
                                                            throw b(ex24);
                                                        }
                                                    }
                                                    this.killBuilder_.clear();
                                                    try {
                                                        this.bitField0_ &= 0xFF7FFFFF;
                                                        if (this.getChildrenCountBuilder_ == null) {
                                                            this.getChildrenCount_ = GetChildrenCount_Res.getDefaultInstance();
                                                            break Label_1002;
                                                        }
                                                    }
                                                    catch (NullPointerException ex25) {
                                                        throw b(ex25);
                                                    }
                                                }
                                                this.getChildrenCountBuilder_.clear();
                                                try {
                                                    this.bitField0_ &= 0xFEFFFFFF;
                                                    if (this.getArraySliceBuilder_ == null) {
                                                        this.getArraySlice_ = GetArraySlice_Res.getDefaultInstance();
                                                        break Label_1042;
                                                    }
                                                }
                                                catch (NullPointerException ex26) {
                                                    throw b(ex26);
                                                }
                                            }
                                            this.getArraySliceBuilder_.clear();
                                            try {
                                                this.bitField0_ &= 0xFDFFFFFF;
                                                if (this.getValueDataBuilder_ == null) {
                                                    this.getValueData_ = GetValueData_Res.getDefaultInstance();
                                                    break Label_1082;
                                                }
                                            }
                                            catch (NullPointerException ex27) {
                                                throw b(ex27);
                                            }
                                        }
                                        this.getValueDataBuilder_.clear();
                                        try {
                                            this.bitField0_ &= 0xFBFFFFFF;
                                            if (this.getValueDescriptionBuilder_ == null) {
                                                this.getValueDescription_ = GetValueDescription_Res.getDefaultInstance();
                                                break Label_1122;
                                            }
                                        }
                                        catch (NullPointerException ex28) {
                                            throw b(ex28);
                                        }
                                    }
                                    this.getValueDescriptionBuilder_.clear();
                                    try {
                                        this.bitField0_ &= 0xF7FFFFFF;
                                        if (this.valuesFilteringPolicyBuilder_ == null) {
                                            this.valuesFilteringPolicy_ = ValuesFilteringPolicy_Res.getDefaultInstance();
                                            break Label_1162;
                                        }
                                    }
                                    catch (NullPointerException ex29) {
                                        throw b(ex29);
                                    }
                                }
                                this.valuesFilteringPolicyBuilder_.clear();
                                try {
                                    this.bitField0_ &= 0xEFFFFFFF;
                                    if (this.connectPlatformBuilder_ == null) {
                                        this.connectPlatform_ = ConnectPlatform_Res.getDefaultInstance();
                                        break Label_1202;
                                    }
                                }
                                catch (NullPointerException ex30) {
                                    throw b(ex30);
                                }
                            }
                            this.connectPlatformBuilder_.clear();
                            try {
                                this.bitField0_ &= 0xDFFFFFFF;
                                if (this.getValueAddressBuilder_ == null) {
                                    this.getValueAddress_ = GetValueAddress_Res.getDefaultInstance();
                                    break Label_1242;
                                }
                            }
                            catch (NullPointerException ex31) {
                                throw b(ex31);
                            }
                        }
                        this.getValueAddressBuilder_.clear();
                        try {
                            this.bitField0_ &= 0xBFFFFFFF;
                            if (this.handleSignalBuilder_ == null) {
                                this.handleSignal_ = HandleSignal_Res.getDefaultInstance();
                                break Label_1282;
                            }
                        }
                        catch (NullPointerException ex32) {
                            throw b(ex32);
                        }
                    }
                    this.handleSignalBuilder_.clear();
                    try {
                        this.bitField0_ &= Integer.MAX_VALUE;
                        if (this.executeShellCommandBuilder_ == null) {
                            this.executeShellCommand_ = ExecuteShellCommand_Res.getDefaultInstance();
                            break Label_1322;
                        }
                    }
                    catch (NullPointerException ex33) {
                        throw b(ex33);
                    }
                }
                this.executeShellCommandBuilder_.clear();
            }
            this.bitField1_ &= 0xFFFFFFFE;
            return this;
        }
        
        public Builder clone() {
            return j().mergeFrom(this.buildPartial());
        }
        
        public Descriptors.Descriptor getDescriptorForType() {
            return Protocol.access$69400();
        }
        
        public CompositeResponse getDefaultInstanceForType() {
            return CompositeResponse.getDefaultInstance();
        }
        
        public CompositeResponse build() {
            final CompositeResponse buildPartial = this.buildPartial();
            try {
                if (!buildPartial.isInitialized()) {
                    throw newUninitializedMessageException((Message)buildPartial);
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return buildPartial;
        }
        
        public CompositeResponse buildPartial() {
            final CompositeResponse compositeResponse = new CompositeResponse((GeneratedMessage.Builder)this);
            final int bitField0_ = this.bitField0_;
            final int bitField1_ = this.bitField1_;
            int n = 0;
            int n2 = 0;
            if ((bitField0_ & 0x1) == 0x1) {
                n |= 0x1;
            }
            Label_0077: {
                try {
                    if (this.createTargetBuilder_ == null) {
                        compositeResponse.createTarget_ = this.createTarget_;
                        break Label_0077;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                compositeResponse.createTarget_ = (CreateTarget_Res)this.createTargetBuilder_.build();
            }
            if ((bitField0_ & 0x2) == 0x2) {
                n |= 0x2;
            }
            Label_0128: {
                try {
                    if (this.launchBuilder_ == null) {
                        compositeResponse.launch_ = this.launch_;
                        break Label_0128;
                    }
                }
                catch (NullPointerException ex2) {
                    throw b(ex2);
                }
                compositeResponse.launch_ = (Launch_Res)this.launchBuilder_.build();
            }
            if ((bitField0_ & 0x4) == 0x4) {
                n |= 0x4;
            }
            Label_0179: {
                try {
                    if (this.continueBuilder_ == null) {
                        compositeResponse.continue_ = this.continue_;
                        break Label_0179;
                    }
                }
                catch (NullPointerException ex3) {
                    throw b(ex3);
                }
                compositeResponse.continue_ = (Continue_Res)this.continueBuilder_.build();
            }
            if ((bitField0_ & 0x8) == 0x8) {
                n |= 0x8;
            }
            Label_0233: {
                try {
                    if (this.compositeBroadcastBuilder_ == null) {
                        compositeResponse.compositeBroadcast_ = this.compositeBroadcast_;
                        break Label_0233;
                    }
                }
                catch (NullPointerException ex4) {
                    throw b(ex4);
                }
                compositeResponse.compositeBroadcast_ = (Broadcasts.CompositeBroadcast)this.compositeBroadcastBuilder_.build();
            }
            if ((bitField0_ & 0x10) == 0x10) {
                n |= 0x10;
            }
            Label_0287: {
                try {
                    if (this.suspendBuilder_ == null) {
                        compositeResponse.suspend_ = this.suspend_;
                        break Label_0287;
                    }
                }
                catch (NullPointerException ex5) {
                    throw b(ex5);
                }
                compositeResponse.suspend_ = (Suspend_Res)this.suspendBuilder_.build();
            }
            if ((bitField0_ & 0x20) == 0x20) {
                n |= 0x20;
            }
            Label_0341: {
                try {
                    if (this.getThreadsBuilder_ == null) {
                        compositeResponse.getThreads_ = this.getThreads_;
                        break Label_0341;
                    }
                }
                catch (NullPointerException ex6) {
                    throw b(ex6);
                }
                compositeResponse.getThreads_ = (GetThreads_Res)this.getThreadsBuilder_.build();
            }
            if ((bitField0_ & 0x40) == 0x40) {
                n |= 0x40;
            }
            Label_0395: {
                try {
                    if (this.getFramesBuilder_ == null) {
                        compositeResponse.getFrames_ = this.getFrames_;
                        break Label_0395;
                    }
                }
                catch (NullPointerException ex7) {
                    throw b(ex7);
                }
                compositeResponse.getFrames_ = (GetFrames_Res)this.getFramesBuilder_.build();
            }
            if ((bitField0_ & 0x80) == 0x80) {
                n |= 0x80;
            }
            Label_0452: {
                try {
                    if (this.addBreakpointBuilder_ == null) {
                        compositeResponse.addBreakpoint_ = this.addBreakpoint_;
                        break Label_0452;
                    }
                }
                catch (NullPointerException ex8) {
                    throw b(ex8);
                }
                compositeResponse.addBreakpoint_ = (AddBreakpoint_Res)this.addBreakpointBuilder_.build();
            }
            if ((bitField0_ & 0x100) == 0x100) {
                n |= 0x100;
            }
            Label_0509: {
                try {
                    if (this.removeBreakpointBuilder_ == null) {
                        compositeResponse.removeBreakpoint_ = this.removeBreakpoint_;
                        break Label_0509;
                    }
                }
                catch (NullPointerException ex9) {
                    throw b(ex9);
                }
                compositeResponse.removeBreakpoint_ = (RemoveBreakpoint_Res)this.removeBreakpointBuilder_.build();
            }
            if ((bitField0_ & 0x200) == 0x200) {
                n |= 0x200;
            }
            Label_0566: {
                try {
                    if (this.stepIntoBuilder_ == null) {
                        compositeResponse.stepInto_ = this.stepInto_;
                        break Label_0566;
                    }
                }
                catch (NullPointerException ex10) {
                    throw b(ex10);
                }
                compositeResponse.stepInto_ = (StepInto_Res)this.stepIntoBuilder_.build();
            }
            if ((bitField0_ & 0x400) == 0x400) {
                n |= 0x400;
            }
            Label_0623: {
                try {
                    if (this.stepOverBuilder_ == null) {
                        compositeResponse.stepOver_ = this.stepOver_;
                        break Label_0623;
                    }
                }
                catch (NullPointerException ex11) {
                    throw b(ex11);
                }
                compositeResponse.stepOver_ = (StepOver_Res)this.stepOverBuilder_.build();
            }
            if ((bitField0_ & 0x800) == 0x800) {
                n |= 0x800;
            }
            Label_0680: {
                try {
                    if (this.stepOutBuilder_ == null) {
                        compositeResponse.stepOut_ = this.stepOut_;
                        break Label_0680;
                    }
                }
                catch (NullPointerException ex12) {
                    throw b(ex12);
                }
                compositeResponse.stepOut_ = (StepOut_Res)this.stepOutBuilder_.build();
            }
            if ((bitField0_ & 0x1000) == 0x1000) {
                n |= 0x1000;
            }
            Label_0737: {
                try {
                    if (this.evaluateExpressionBuilder_ == null) {
                        compositeResponse.evaluateExpression_ = this.evaluateExpression_;
                        break Label_0737;
                    }
                }
                catch (NullPointerException ex13) {
                    throw b(ex13);
                }
                compositeResponse.evaluateExpression_ = (EvaluateExpression_Res)this.evaluateExpressionBuilder_.build();
            }
            if ((bitField0_ & 0x2000) == 0x2000) {
                n |= 0x2000;
            }
            Label_0794: {
                try {
                    if (this.getValueChildrenBuilder_ == null) {
                        compositeResponse.getValueChildren_ = this.getValueChildren_;
                        break Label_0794;
                    }
                }
                catch (NullPointerException ex14) {
                    throw b(ex14);
                }
                compositeResponse.getValueChildren_ = (GetValueChildren_Res)this.getValueChildrenBuilder_.build();
            }
            if ((bitField0_ & 0x4000) == 0x4000) {
                n |= 0x4000;
            }
            Label_0851: {
                try {
                    if (this.getVarsBuilder_ == null) {
                        compositeResponse.getVars_ = this.getVars_;
                        break Label_0851;
                    }
                }
                catch (NullPointerException ex15) {
                    throw b(ex15);
                }
                compositeResponse.getVars_ = (GetVars_Res)this.getVarsBuilder_.build();
            }
            if ((bitField0_ & 0x8000) == 0x8000) {
                n |= 0x8000;
            }
            Label_0905: {
                try {
                    if (this.handleConsoleCommandBuilder_ == null) {
                        compositeResponse.handleConsoleCommand_ = this.handleConsoleCommand_;
                        break Label_0905;
                    }
                }
                catch (NullPointerException ex16) {
                    throw b(ex16);
                }
                compositeResponse.handleConsoleCommand_ = (HandleConsoleCommand_Res)this.handleConsoleCommandBuilder_.build();
            }
            if ((bitField0_ & 0x10000) == 0x10000) {
                n |= 0x10000;
            }
            Label_0959: {
                try {
                    if (this.handleCompletionBuilder_ == null) {
                        compositeResponse.handleCompletion_ = this.handleCompletion_;
                        break Label_0959;
                    }
                }
                catch (NullPointerException ex17) {
                    throw b(ex17);
                }
                compositeResponse.handleCompletion_ = (HandleCompletion_Res)this.handleCompletionBuilder_.build();
            }
            if ((bitField0_ & 0x20000) == 0x20000) {
                n |= 0x20000;
            }
            Label_1013: {
                try {
                    if (this.attachBuilder_ == null) {
                        compositeResponse.attach_ = this.attach_;
                        break Label_1013;
                    }
                }
                catch (NullPointerException ex18) {
                    throw b(ex18);
                }
                compositeResponse.attach_ = (Attach_Res)this.attachBuilder_.build();
            }
            if ((bitField0_ & 0x40000) == 0x40000) {
                n |= 0x40000;
            }
            Label_1067: {
                try {
                    if (this.attachByNameBuilder_ == null) {
                        compositeResponse.attachByName_ = this.attachByName_;
                        break Label_1067;
                    }
                }
                catch (NullPointerException ex19) {
                    throw b(ex19);
                }
                compositeResponse.attachByName_ = (AttachByName_Res)this.attachByNameBuilder_.build();
            }
            if ((bitField0_ & 0x80000) == 0x80000) {
                n |= 0x80000;
            }
            Label_1121: {
                try {
                    if (this.dispatchInputBuilder_ == null) {
                        compositeResponse.dispatchInput_ = this.dispatchInput_;
                        break Label_1121;
                    }
                }
                catch (NullPointerException ex20) {
                    throw b(ex20);
                }
                compositeResponse.dispatchInput_ = (DispatchInput_Res)this.dispatchInputBuilder_.build();
            }
            if ((bitField0_ & 0x100000) == 0x100000) {
                n |= 0x100000;
            }
            Label_1175: {
                try {
                    if (this.addWatchpointBuilder_ == null) {
                        compositeResponse.addWatchpoint_ = this.addWatchpoint_;
                        break Label_1175;
                    }
                }
                catch (NullPointerException ex21) {
                    throw b(ex21);
                }
                compositeResponse.addWatchpoint_ = (AddWatchpoint_Res)this.addWatchpointBuilder_.build();
            }
            if ((bitField0_ & 0x200000) == 0x200000) {
                n |= 0x200000;
            }
            Label_1229: {
                try {
                    if (this.removeWatchpointBuilder_ == null) {
                        compositeResponse.removeWatchpoint_ = this.removeWatchpoint_;
                        break Label_1229;
                    }
                }
                catch (NullPointerException ex22) {
                    throw b(ex22);
                }
                compositeResponse.removeWatchpoint_ = (RemoveWatchpoint_Res)this.removeWatchpointBuilder_.build();
            }
            if ((bitField0_ & 0x400000) == 0x400000) {
                n |= 0x400000;
            }
            Label_1283: {
                try {
                    if (this.detachBuilder_ == null) {
                        compositeResponse.detach_ = this.detach_;
                        break Label_1283;
                    }
                }
                catch (NullPointerException ex23) {
                    throw b(ex23);
                }
                compositeResponse.detach_ = (Detach_Res)this.detachBuilder_.build();
            }
            if ((bitField0_ & 0x800000) == 0x800000) {
                n |= 0x800000;
            }
            Label_1337: {
                try {
                    if (this.killBuilder_ == null) {
                        compositeResponse.kill_ = this.kill_;
                        break Label_1337;
                    }
                }
                catch (NullPointerException ex24) {
                    throw b(ex24);
                }
                compositeResponse.kill_ = (Kill_Res)this.killBuilder_.build();
            }
            if ((bitField0_ & 0x1000000) == 0x1000000) {
                n |= 0x1000000;
            }
            Label_1391: {
                try {
                    if (this.getChildrenCountBuilder_ == null) {
                        compositeResponse.getChildrenCount_ = this.getChildrenCount_;
                        break Label_1391;
                    }
                }
                catch (NullPointerException ex25) {
                    throw b(ex25);
                }
                compositeResponse.getChildrenCount_ = (GetChildrenCount_Res)this.getChildrenCountBuilder_.build();
            }
            if ((bitField0_ & 0x2000000) == 0x2000000) {
                n |= 0x2000000;
            }
            Label_1445: {
                try {
                    if (this.getArraySliceBuilder_ == null) {
                        compositeResponse.getArraySlice_ = this.getArraySlice_;
                        break Label_1445;
                    }
                }
                catch (NullPointerException ex26) {
                    throw b(ex26);
                }
                compositeResponse.getArraySlice_ = (GetArraySlice_Res)this.getArraySliceBuilder_.build();
            }
            if ((bitField0_ & 0x4000000) == 0x4000000) {
                n |= 0x4000000;
            }
            Label_1499: {
                try {
                    if (this.getValueDataBuilder_ == null) {
                        compositeResponse.getValueData_ = this.getValueData_;
                        break Label_1499;
                    }
                }
                catch (NullPointerException ex27) {
                    throw b(ex27);
                }
                compositeResponse.getValueData_ = (GetValueData_Res)this.getValueDataBuilder_.build();
            }
            if ((bitField0_ & 0x8000000) == 0x8000000) {
                n |= 0x8000000;
            }
            Label_1553: {
                try {
                    if (this.getValueDescriptionBuilder_ == null) {
                        compositeResponse.getValueDescription_ = this.getValueDescription_;
                        break Label_1553;
                    }
                }
                catch (NullPointerException ex28) {
                    throw b(ex28);
                }
                compositeResponse.getValueDescription_ = (GetValueDescription_Res)this.getValueDescriptionBuilder_.build();
            }
            if ((bitField0_ & 0x10000000) == 0x10000000) {
                n |= 0x10000000;
            }
            Label_1607: {
                try {
                    if (this.valuesFilteringPolicyBuilder_ == null) {
                        compositeResponse.valuesFilteringPolicy_ = this.valuesFilteringPolicy_;
                        break Label_1607;
                    }
                }
                catch (NullPointerException ex29) {
                    throw b(ex29);
                }
                compositeResponse.valuesFilteringPolicy_ = (ValuesFilteringPolicy_Res)this.valuesFilteringPolicyBuilder_.build();
            }
            if ((bitField0_ & 0x20000000) == 0x20000000) {
                n |= 0x20000000;
            }
            Label_1661: {
                try {
                    if (this.connectPlatformBuilder_ == null) {
                        compositeResponse.connectPlatform_ = this.connectPlatform_;
                        break Label_1661;
                    }
                }
                catch (NullPointerException ex30) {
                    throw b(ex30);
                }
                compositeResponse.connectPlatform_ = (ConnectPlatform_Res)this.connectPlatformBuilder_.build();
            }
            if ((bitField0_ & 0x40000000) == 0x40000000) {
                n |= 0x40000000;
            }
            Label_1715: {
                try {
                    if (this.getValueAddressBuilder_ == null) {
                        compositeResponse.getValueAddress_ = this.getValueAddress_;
                        break Label_1715;
                    }
                }
                catch (NullPointerException ex31) {
                    throw b(ex31);
                }
                compositeResponse.getValueAddress_ = (GetValueAddress_Res)this.getValueAddressBuilder_.build();
            }
            if ((bitField0_ & Integer.MIN_VALUE) == Integer.MIN_VALUE) {
                n |= Integer.MIN_VALUE;
            }
            Label_1769: {
                try {
                    if (this.handleSignalBuilder_ == null) {
                        compositeResponse.handleSignal_ = this.handleSignal_;
                        break Label_1769;
                    }
                }
                catch (NullPointerException ex32) {
                    throw b(ex32);
                }
                compositeResponse.handleSignal_ = (HandleSignal_Res)this.handleSignalBuilder_.build();
            }
            if ((bitField1_ & 0x1) == 0x1) {
                n2 |= 0x1;
            }
            Label_1820: {
                try {
                    if (this.executeShellCommandBuilder_ == null) {
                        compositeResponse.executeShellCommand_ = this.executeShellCommand_;
                        break Label_1820;
                    }
                }
                catch (NullPointerException ex33) {
                    throw b(ex33);
                }
                compositeResponse.executeShellCommand_ = (ExecuteShellCommand_Res)this.executeShellCommandBuilder_.build();
            }
            compositeResponse.bitField0_ = n;
            compositeResponse.bitField1_ = n2;
            this.onBuilt();
            return compositeResponse;
        }
        
        public Builder mergeFrom(final Message message) {
            try {
                if (message instanceof CompositeResponse) {
                    return this.mergeFrom((CompositeResponse)message);
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            super.mergeFrom(message);
            return this;
        }
        
        public Builder mergeFrom(final CompositeResponse compositeResponse) {
            try {
                if (compositeResponse == CompositeResponse.getDefaultInstance()) {
                    return this;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            try {
                if (compositeResponse.hasCreateTarget()) {
                    this.mergeCreateTarget(compositeResponse.getCreateTarget());
                }
            }
            catch (NullPointerException ex2) {
                throw b(ex2);
            }
            try {
                if (compositeResponse.hasLaunch()) {
                    this.mergeLaunch(compositeResponse.getLaunch());
                }
            }
            catch (NullPointerException ex3) {
                throw b(ex3);
            }
            try {
                if (compositeResponse.hasContinue()) {
                    this.mergeContinue(compositeResponse.getContinue());
                }
            }
            catch (NullPointerException ex4) {
                throw b(ex4);
            }
            try {
                if (compositeResponse.hasCompositeBroadcast()) {
                    this.mergeCompositeBroadcast(compositeResponse.getCompositeBroadcast());
                }
            }
            catch (NullPointerException ex5) {
                throw b(ex5);
            }
            try {
                if (compositeResponse.hasSuspend()) {
                    this.mergeSuspend(compositeResponse.getSuspend());
                }
            }
            catch (NullPointerException ex6) {
                throw b(ex6);
            }
            try {
                if (compositeResponse.hasGetThreads()) {
                    this.mergeGetThreads(compositeResponse.getGetThreads());
                }
            }
            catch (NullPointerException ex7) {
                throw b(ex7);
            }
            try {
                if (compositeResponse.hasGetFrames()) {
                    this.mergeGetFrames(compositeResponse.getGetFrames());
                }
            }
            catch (NullPointerException ex8) {
                throw b(ex8);
            }
            try {
                if (compositeResponse.hasAddBreakpoint()) {
                    this.mergeAddBreakpoint(compositeResponse.getAddBreakpoint());
                }
            }
            catch (NullPointerException ex9) {
                throw b(ex9);
            }
            try {
                if (compositeResponse.hasRemoveBreakpoint()) {
                    this.mergeRemoveBreakpoint(compositeResponse.getRemoveBreakpoint());
                }
            }
            catch (NullPointerException ex10) {
                throw b(ex10);
            }
            try {
                if (compositeResponse.hasStepInto()) {
                    this.mergeStepInto(compositeResponse.getStepInto());
                }
            }
            catch (NullPointerException ex11) {
                throw b(ex11);
            }
            try {
                if (compositeResponse.hasStepOver()) {
                    this.mergeStepOver(compositeResponse.getStepOver());
                }
            }
            catch (NullPointerException ex12) {
                throw b(ex12);
            }
            try {
                if (compositeResponse.hasStepOut()) {
                    this.mergeStepOut(compositeResponse.getStepOut());
                }
            }
            catch (NullPointerException ex13) {
                throw b(ex13);
            }
            try {
                if (compositeResponse.hasEvaluateExpression()) {
                    this.mergeEvaluateExpression(compositeResponse.getEvaluateExpression());
                }
            }
            catch (NullPointerException ex14) {
                throw b(ex14);
            }
            try {
                if (compositeResponse.hasGetValueChildren()) {
                    this.mergeGetValueChildren(compositeResponse.getGetValueChildren());
                }
            }
            catch (NullPointerException ex15) {
                throw b(ex15);
            }
            try {
                if (compositeResponse.hasGetVars()) {
                    this.mergeGetVars(compositeResponse.getGetVars());
                }
            }
            catch (NullPointerException ex16) {
                throw b(ex16);
            }
            try {
                if (compositeResponse.hasHandleConsoleCommand()) {
                    this.mergeHandleConsoleCommand(compositeResponse.getHandleConsoleCommand());
                }
            }
            catch (NullPointerException ex17) {
                throw b(ex17);
            }
            try {
                if (compositeResponse.hasHandleCompletion()) {
                    this.mergeHandleCompletion(compositeResponse.getHandleCompletion());
                }
            }
            catch (NullPointerException ex18) {
                throw b(ex18);
            }
            try {
                if (compositeResponse.hasAttach()) {
                    this.mergeAttach(compositeResponse.getAttach());
                }
            }
            catch (NullPointerException ex19) {
                throw b(ex19);
            }
            try {
                if (compositeResponse.hasAttachByName()) {
                    this.mergeAttachByName(compositeResponse.getAttachByName());
                }
            }
            catch (NullPointerException ex20) {
                throw b(ex20);
            }
            try {
                if (compositeResponse.hasDispatchInput()) {
                    this.mergeDispatchInput(compositeResponse.getDispatchInput());
                }
            }
            catch (NullPointerException ex21) {
                throw b(ex21);
            }
            try {
                if (compositeResponse.hasAddWatchpoint()) {
                    this.mergeAddWatchpoint(compositeResponse.getAddWatchpoint());
                }
            }
            catch (NullPointerException ex22) {
                throw b(ex22);
            }
            try {
                if (compositeResponse.hasRemoveWatchpoint()) {
                    this.mergeRemoveWatchpoint(compositeResponse.getRemoveWatchpoint());
                }
            }
            catch (NullPointerException ex23) {
                throw b(ex23);
            }
            try {
                if (compositeResponse.hasDetach()) {
                    this.mergeDetach(compositeResponse.getDetach());
                }
            }
            catch (NullPointerException ex24) {
                throw b(ex24);
            }
            try {
                if (compositeResponse.hasKill()) {
                    this.mergeKill(compositeResponse.getKill());
                }
            }
            catch (NullPointerException ex25) {
                throw b(ex25);
            }
            try {
                if (compositeResponse.hasGetChildrenCount()) {
                    this.mergeGetChildrenCount(compositeResponse.getGetChildrenCount());
                }
            }
            catch (NullPointerException ex26) {
                throw b(ex26);
            }
            try {
                if (compositeResponse.hasGetArraySlice()) {
                    this.mergeGetArraySlice(compositeResponse.getGetArraySlice());
                }
            }
            catch (NullPointerException ex27) {
                throw b(ex27);
            }
            try {
                if (compositeResponse.hasGetValueData()) {
                    this.mergeGetValueData(compositeResponse.getGetValueData());
                }
            }
            catch (NullPointerException ex28) {
                throw b(ex28);
            }
            try {
                if (compositeResponse.hasGetValueDescription()) {
                    this.mergeGetValueDescription(compositeResponse.getGetValueDescription());
                }
            }
            catch (NullPointerException ex29) {
                throw b(ex29);
            }
            try {
                if (compositeResponse.hasValuesFilteringPolicy()) {
                    this.mergeValuesFilteringPolicy(compositeResponse.getValuesFilteringPolicy());
                }
            }
            catch (NullPointerException ex30) {
                throw b(ex30);
            }
            try {
                if (compositeResponse.hasConnectPlatform()) {
                    this.mergeConnectPlatform(compositeResponse.getConnectPlatform());
                }
            }
            catch (NullPointerException ex31) {
                throw b(ex31);
            }
            try {
                if (compositeResponse.hasGetValueAddress()) {
                    this.mergeGetValueAddress(compositeResponse.getGetValueAddress());
                }
            }
            catch (NullPointerException ex32) {
                throw b(ex32);
            }
            try {
                if (compositeResponse.hasHandleSignal()) {
                    this.mergeHandleSignal(compositeResponse.getHandleSignal());
                }
            }
            catch (NullPointerException ex33) {
                throw b(ex33);
            }
            try {
                if (compositeResponse.hasExecuteShellCommand()) {
                    this.mergeExecuteShellCommand(compositeResponse.getExecuteShellCommand());
                }
            }
            catch (NullPointerException ex34) {
                throw b(ex34);
            }
            this.mergeUnknownFields(compositeResponse.getUnknownFields());
            return this;
        }
        
        public final boolean isInitialized() {
            Label_0960: {
                Label_0930: {
                    Label_0900: {
                        Label_0870: {
                            Label_0840: {
                                Label_0810: {
                                    Label_0780: {
                                        Label_0750: {
                                            Label_0720: {
                                                Label_0690: {
                                                    Label_0660: {
                                                        Label_0630: {
                                                            Label_0600: {
                                                                Label_0570: {
                                                                    Label_0540: {
                                                                        Label_0510: {
                                                                            Label_0480: {
                                                                                Label_0450: {
                                                                                    Label_0420: {
                                                                                        Label_0390: {
                                                                                            Label_0360: {
                                                                                                Label_0330: {
                                                                                                    Label_0300: {
                                                                                                        Label_0270: {
                                                                                                            Label_0240: {
                                                                                                                Label_0210: {
                                                                                                                    Label_0180: {
                                                                                                                        Label_0150: {
                                                                                                                            Label_0120: {
                                                                                                                                Label_0090: {
                                                                                                                                    Label_0060: {
                                                                                                                                        Label_0030: {
                                                                                                                                            try {
                                                                                                                                                if (!this.hasCreateTarget()) {
                                                                                                                                                    break Label_0030;
                                                                                                                                                }
                                                                                                                                                final Builder builder = this;
                                                                                                                                                final CreateTarget_Res createTarget_Res = builder.getCreateTarget();
                                                                                                                                                final boolean b = createTarget_Res.isInitialized();
                                                                                                                                                if (!b) {
                                                                                                                                                    return false;
                                                                                                                                                }
                                                                                                                                                break Label_0030;
                                                                                                                                            }
                                                                                                                                            catch (NullPointerException ex) {
                                                                                                                                                throw b(ex);
                                                                                                                                            }
                                                                                                                                            try {
                                                                                                                                                final Builder builder = this;
                                                                                                                                                final CreateTarget_Res createTarget_Res = builder.getCreateTarget();
                                                                                                                                                final boolean b = createTarget_Res.isInitialized();
                                                                                                                                                if (!b) {
                                                                                                                                                    return false;
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                            catch (NullPointerException ex2) {
                                                                                                                                                throw b(ex2);
                                                                                                                                            }
                                                                                                                                            try {
                                                                                                                                                if (!this.hasLaunch()) {
                                                                                                                                                    break Label_0060;
                                                                                                                                                }
                                                                                                                                                final Builder builder2 = this;
                                                                                                                                                final Launch_Res launch_Res = builder2.getLaunch();
                                                                                                                                                final boolean b2 = launch_Res.isInitialized();
                                                                                                                                                if (!b2) {
                                                                                                                                                    return false;
                                                                                                                                                }
                                                                                                                                                break Label_0060;
                                                                                                                                            }
                                                                                                                                            catch (NullPointerException ex3) {
                                                                                                                                                throw b(ex3);
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                        try {
                                                                                                                                            final Builder builder2 = this;
                                                                                                                                            final Launch_Res launch_Res = builder2.getLaunch();
                                                                                                                                            final boolean b2 = launch_Res.isInitialized();
                                                                                                                                            if (!b2) {
                                                                                                                                                return false;
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                        catch (NullPointerException ex4) {
                                                                                                                                            throw b(ex4);
                                                                                                                                        }
                                                                                                                                        try {
                                                                                                                                            if (!this.hasContinue()) {
                                                                                                                                                break Label_0090;
                                                                                                                                            }
                                                                                                                                            final Builder builder3 = this;
                                                                                                                                            final Continue_Res continue_Res = builder3.getContinue();
                                                                                                                                            final boolean b3 = continue_Res.isInitialized();
                                                                                                                                            if (!b3) {
                                                                                                                                                return false;
                                                                                                                                            }
                                                                                                                                            break Label_0090;
                                                                                                                                        }
                                                                                                                                        catch (NullPointerException ex5) {
                                                                                                                                            throw b(ex5);
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                    try {
                                                                                                                                        final Builder builder3 = this;
                                                                                                                                        final Continue_Res continue_Res = builder3.getContinue();
                                                                                                                                        final boolean b3 = continue_Res.isInitialized();
                                                                                                                                        if (!b3) {
                                                                                                                                            return false;
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                    catch (NullPointerException ex6) {
                                                                                                                                        throw b(ex6);
                                                                                                                                    }
                                                                                                                                    try {
                                                                                                                                        if (!this.hasCompositeBroadcast()) {
                                                                                                                                            break Label_0120;
                                                                                                                                        }
                                                                                                                                        final Builder builder4 = this;
                                                                                                                                        final Broadcasts.CompositeBroadcast compositeBroadcast = builder4.getCompositeBroadcast();
                                                                                                                                        final boolean b4 = compositeBroadcast.isInitialized();
                                                                                                                                        if (!b4) {
                                                                                                                                            return false;
                                                                                                                                        }
                                                                                                                                        break Label_0120;
                                                                                                                                    }
                                                                                                                                    catch (NullPointerException ex7) {
                                                                                                                                        throw b(ex7);
                                                                                                                                    }
                                                                                                                                }
                                                                                                                                try {
                                                                                                                                    final Builder builder4 = this;
                                                                                                                                    final Broadcasts.CompositeBroadcast compositeBroadcast = builder4.getCompositeBroadcast();
                                                                                                                                    final boolean b4 = compositeBroadcast.isInitialized();
                                                                                                                                    if (!b4) {
                                                                                                                                        return false;
                                                                                                                                    }
                                                                                                                                }
                                                                                                                                catch (NullPointerException ex8) {
                                                                                                                                    throw b(ex8);
                                                                                                                                }
                                                                                                                                try {
                                                                                                                                    if (!this.hasSuspend()) {
                                                                                                                                        break Label_0150;
                                                                                                                                    }
                                                                                                                                    final Builder builder5 = this;
                                                                                                                                    final Suspend_Res suspend_Res = builder5.getSuspend();
                                                                                                                                    final boolean b5 = suspend_Res.isInitialized();
                                                                                                                                    if (!b5) {
                                                                                                                                        return false;
                                                                                                                                    }
                                                                                                                                    break Label_0150;
                                                                                                                                }
                                                                                                                                catch (NullPointerException ex9) {
                                                                                                                                    throw b(ex9);
                                                                                                                                }
                                                                                                                            }
                                                                                                                            try {
                                                                                                                                final Builder builder5 = this;
                                                                                                                                final Suspend_Res suspend_Res = builder5.getSuspend();
                                                                                                                                final boolean b5 = suspend_Res.isInitialized();
                                                                                                                                if (!b5) {
                                                                                                                                    return false;
                                                                                                                                }
                                                                                                                            }
                                                                                                                            catch (NullPointerException ex10) {
                                                                                                                                throw b(ex10);
                                                                                                                            }
                                                                                                                            try {
                                                                                                                                if (!this.hasGetThreads()) {
                                                                                                                                    break Label_0180;
                                                                                                                                }
                                                                                                                                final Builder builder6 = this;
                                                                                                                                final GetThreads_Res getThreads_Res = builder6.getGetThreads();
                                                                                                                                final boolean b6 = getThreads_Res.isInitialized();
                                                                                                                                if (!b6) {
                                                                                                                                    return false;
                                                                                                                                }
                                                                                                                                break Label_0180;
                                                                                                                            }
                                                                                                                            catch (NullPointerException ex11) {
                                                                                                                                throw b(ex11);
                                                                                                                            }
                                                                                                                        }
                                                                                                                        try {
                                                                                                                            final Builder builder6 = this;
                                                                                                                            final GetThreads_Res getThreads_Res = builder6.getGetThreads();
                                                                                                                            final boolean b6 = getThreads_Res.isInitialized();
                                                                                                                            if (!b6) {
                                                                                                                                return false;
                                                                                                                            }
                                                                                                                        }
                                                                                                                        catch (NullPointerException ex12) {
                                                                                                                            throw b(ex12);
                                                                                                                        }
                                                                                                                        try {
                                                                                                                            if (!this.hasGetFrames()) {
                                                                                                                                break Label_0210;
                                                                                                                            }
                                                                                                                            final Builder builder7 = this;
                                                                                                                            final GetFrames_Res getFrames_Res = builder7.getGetFrames();
                                                                                                                            final boolean b7 = getFrames_Res.isInitialized();
                                                                                                                            if (!b7) {
                                                                                                                                return false;
                                                                                                                            }
                                                                                                                            break Label_0210;
                                                                                                                        }
                                                                                                                        catch (NullPointerException ex13) {
                                                                                                                            throw b(ex13);
                                                                                                                        }
                                                                                                                    }
                                                                                                                    try {
                                                                                                                        final Builder builder7 = this;
                                                                                                                        final GetFrames_Res getFrames_Res = builder7.getGetFrames();
                                                                                                                        final boolean b7 = getFrames_Res.isInitialized();
                                                                                                                        if (!b7) {
                                                                                                                            return false;
                                                                                                                        }
                                                                                                                    }
                                                                                                                    catch (NullPointerException ex14) {
                                                                                                                        throw b(ex14);
                                                                                                                    }
                                                                                                                    try {
                                                                                                                        if (!this.hasAddBreakpoint()) {
                                                                                                                            break Label_0240;
                                                                                                                        }
                                                                                                                        final Builder builder8 = this;
                                                                                                                        final AddBreakpoint_Res addBreakpoint_Res = builder8.getAddBreakpoint();
                                                                                                                        final boolean b8 = addBreakpoint_Res.isInitialized();
                                                                                                                        if (!b8) {
                                                                                                                            return false;
                                                                                                                        }
                                                                                                                        break Label_0240;
                                                                                                                    }
                                                                                                                    catch (NullPointerException ex15) {
                                                                                                                        throw b(ex15);
                                                                                                                    }
                                                                                                                }
                                                                                                                try {
                                                                                                                    final Builder builder8 = this;
                                                                                                                    final AddBreakpoint_Res addBreakpoint_Res = builder8.getAddBreakpoint();
                                                                                                                    final boolean b8 = addBreakpoint_Res.isInitialized();
                                                                                                                    if (!b8) {
                                                                                                                        return false;
                                                                                                                    }
                                                                                                                }
                                                                                                                catch (NullPointerException ex16) {
                                                                                                                    throw b(ex16);
                                                                                                                }
                                                                                                                try {
                                                                                                                    if (!this.hasRemoveBreakpoint()) {
                                                                                                                        break Label_0270;
                                                                                                                    }
                                                                                                                    final Builder builder9 = this;
                                                                                                                    final RemoveBreakpoint_Res removeBreakpoint_Res = builder9.getRemoveBreakpoint();
                                                                                                                    final boolean b9 = removeBreakpoint_Res.isInitialized();
                                                                                                                    if (!b9) {
                                                                                                                        return false;
                                                                                                                    }
                                                                                                                    break Label_0270;
                                                                                                                }
                                                                                                                catch (NullPointerException ex17) {
                                                                                                                    throw b(ex17);
                                                                                                                }
                                                                                                            }
                                                                                                            try {
                                                                                                                final Builder builder9 = this;
                                                                                                                final RemoveBreakpoint_Res removeBreakpoint_Res = builder9.getRemoveBreakpoint();
                                                                                                                final boolean b9 = removeBreakpoint_Res.isInitialized();
                                                                                                                if (!b9) {
                                                                                                                    return false;
                                                                                                                }
                                                                                                            }
                                                                                                            catch (NullPointerException ex18) {
                                                                                                                throw b(ex18);
                                                                                                            }
                                                                                                            try {
                                                                                                                if (!this.hasStepInto()) {
                                                                                                                    break Label_0300;
                                                                                                                }
                                                                                                                final Builder builder10 = this;
                                                                                                                final StepInto_Res stepInto_Res = builder10.getStepInto();
                                                                                                                final boolean b10 = stepInto_Res.isInitialized();
                                                                                                                if (!b10) {
                                                                                                                    return false;
                                                                                                                }
                                                                                                                break Label_0300;
                                                                                                            }
                                                                                                            catch (NullPointerException ex19) {
                                                                                                                throw b(ex19);
                                                                                                            }
                                                                                                        }
                                                                                                        try {
                                                                                                            final Builder builder10 = this;
                                                                                                            final StepInto_Res stepInto_Res = builder10.getStepInto();
                                                                                                            final boolean b10 = stepInto_Res.isInitialized();
                                                                                                            if (!b10) {
                                                                                                                return false;
                                                                                                            }
                                                                                                        }
                                                                                                        catch (NullPointerException ex20) {
                                                                                                            throw b(ex20);
                                                                                                        }
                                                                                                        try {
                                                                                                            if (!this.hasStepOver()) {
                                                                                                                break Label_0330;
                                                                                                            }
                                                                                                            final Builder builder11 = this;
                                                                                                            final StepOver_Res stepOver_Res = builder11.getStepOver();
                                                                                                            final boolean b11 = stepOver_Res.isInitialized();
                                                                                                            if (!b11) {
                                                                                                                return false;
                                                                                                            }
                                                                                                            break Label_0330;
                                                                                                        }
                                                                                                        catch (NullPointerException ex21) {
                                                                                                            throw b(ex21);
                                                                                                        }
                                                                                                    }
                                                                                                    try {
                                                                                                        final Builder builder11 = this;
                                                                                                        final StepOver_Res stepOver_Res = builder11.getStepOver();
                                                                                                        final boolean b11 = stepOver_Res.isInitialized();
                                                                                                        if (!b11) {
                                                                                                            return false;
                                                                                                        }
                                                                                                    }
                                                                                                    catch (NullPointerException ex22) {
                                                                                                        throw b(ex22);
                                                                                                    }
                                                                                                    try {
                                                                                                        if (!this.hasStepOut()) {
                                                                                                            break Label_0360;
                                                                                                        }
                                                                                                        final Builder builder12 = this;
                                                                                                        final StepOut_Res stepOut_Res = builder12.getStepOut();
                                                                                                        final boolean b12 = stepOut_Res.isInitialized();
                                                                                                        if (!b12) {
                                                                                                            return false;
                                                                                                        }
                                                                                                        break Label_0360;
                                                                                                    }
                                                                                                    catch (NullPointerException ex23) {
                                                                                                        throw b(ex23);
                                                                                                    }
                                                                                                }
                                                                                                try {
                                                                                                    final Builder builder12 = this;
                                                                                                    final StepOut_Res stepOut_Res = builder12.getStepOut();
                                                                                                    final boolean b12 = stepOut_Res.isInitialized();
                                                                                                    if (!b12) {
                                                                                                        return false;
                                                                                                    }
                                                                                                }
                                                                                                catch (NullPointerException ex24) {
                                                                                                    throw b(ex24);
                                                                                                }
                                                                                                try {
                                                                                                    if (!this.hasEvaluateExpression()) {
                                                                                                        break Label_0390;
                                                                                                    }
                                                                                                    final Builder builder13 = this;
                                                                                                    final EvaluateExpression_Res evaluateExpression_Res = builder13.getEvaluateExpression();
                                                                                                    final boolean b13 = evaluateExpression_Res.isInitialized();
                                                                                                    if (!b13) {
                                                                                                        return false;
                                                                                                    }
                                                                                                    break Label_0390;
                                                                                                }
                                                                                                catch (NullPointerException ex25) {
                                                                                                    throw b(ex25);
                                                                                                }
                                                                                            }
                                                                                            try {
                                                                                                final Builder builder13 = this;
                                                                                                final EvaluateExpression_Res evaluateExpression_Res = builder13.getEvaluateExpression();
                                                                                                final boolean b13 = evaluateExpression_Res.isInitialized();
                                                                                                if (!b13) {
                                                                                                    return false;
                                                                                                }
                                                                                            }
                                                                                            catch (NullPointerException ex26) {
                                                                                                throw b(ex26);
                                                                                            }
                                                                                            try {
                                                                                                if (!this.hasGetValueChildren()) {
                                                                                                    break Label_0420;
                                                                                                }
                                                                                                final Builder builder14 = this;
                                                                                                final GetValueChildren_Res getValueChildren_Res = builder14.getGetValueChildren();
                                                                                                final boolean b14 = getValueChildren_Res.isInitialized();
                                                                                                if (!b14) {
                                                                                                    return false;
                                                                                                }
                                                                                                break Label_0420;
                                                                                            }
                                                                                            catch (NullPointerException ex27) {
                                                                                                throw b(ex27);
                                                                                            }
                                                                                        }
                                                                                        try {
                                                                                            final Builder builder14 = this;
                                                                                            final GetValueChildren_Res getValueChildren_Res = builder14.getGetValueChildren();
                                                                                            final boolean b14 = getValueChildren_Res.isInitialized();
                                                                                            if (!b14) {
                                                                                                return false;
                                                                                            }
                                                                                        }
                                                                                        catch (NullPointerException ex28) {
                                                                                            throw b(ex28);
                                                                                        }
                                                                                        try {
                                                                                            if (!this.hasGetVars()) {
                                                                                                break Label_0450;
                                                                                            }
                                                                                            final Builder builder15 = this;
                                                                                            final GetVars_Res getVars_Res = builder15.getGetVars();
                                                                                            final boolean b15 = getVars_Res.isInitialized();
                                                                                            if (!b15) {
                                                                                                return false;
                                                                                            }
                                                                                            break Label_0450;
                                                                                        }
                                                                                        catch (NullPointerException ex29) {
                                                                                            throw b(ex29);
                                                                                        }
                                                                                    }
                                                                                    try {
                                                                                        final Builder builder15 = this;
                                                                                        final GetVars_Res getVars_Res = builder15.getGetVars();
                                                                                        final boolean b15 = getVars_Res.isInitialized();
                                                                                        if (!b15) {
                                                                                            return false;
                                                                                        }
                                                                                    }
                                                                                    catch (NullPointerException ex30) {
                                                                                        throw b(ex30);
                                                                                    }
                                                                                    try {
                                                                                        if (!this.hasHandleConsoleCommand()) {
                                                                                            break Label_0480;
                                                                                        }
                                                                                        final Builder builder16 = this;
                                                                                        final HandleConsoleCommand_Res handleConsoleCommand_Res = builder16.getHandleConsoleCommand();
                                                                                        final boolean b16 = handleConsoleCommand_Res.isInitialized();
                                                                                        if (!b16) {
                                                                                            return false;
                                                                                        }
                                                                                        break Label_0480;
                                                                                    }
                                                                                    catch (NullPointerException ex31) {
                                                                                        throw b(ex31);
                                                                                    }
                                                                                }
                                                                                try {
                                                                                    final Builder builder16 = this;
                                                                                    final HandleConsoleCommand_Res handleConsoleCommand_Res = builder16.getHandleConsoleCommand();
                                                                                    final boolean b16 = handleConsoleCommand_Res.isInitialized();
                                                                                    if (!b16) {
                                                                                        return false;
                                                                                    }
                                                                                }
                                                                                catch (NullPointerException ex32) {
                                                                                    throw b(ex32);
                                                                                }
                                                                                try {
                                                                                    if (!this.hasHandleCompletion()) {
                                                                                        break Label_0510;
                                                                                    }
                                                                                    final Builder builder17 = this;
                                                                                    final HandleCompletion_Res handleCompletion_Res = builder17.getHandleCompletion();
                                                                                    final boolean b17 = handleCompletion_Res.isInitialized();
                                                                                    if (!b17) {
                                                                                        return false;
                                                                                    }
                                                                                    break Label_0510;
                                                                                }
                                                                                catch (NullPointerException ex33) {
                                                                                    throw b(ex33);
                                                                                }
                                                                            }
                                                                            try {
                                                                                final Builder builder17 = this;
                                                                                final HandleCompletion_Res handleCompletion_Res = builder17.getHandleCompletion();
                                                                                final boolean b17 = handleCompletion_Res.isInitialized();
                                                                                if (!b17) {
                                                                                    return false;
                                                                                }
                                                                            }
                                                                            catch (NullPointerException ex34) {
                                                                                throw b(ex34);
                                                                            }
                                                                            try {
                                                                                if (!this.hasAttach()) {
                                                                                    break Label_0540;
                                                                                }
                                                                                final Builder builder18 = this;
                                                                                final Attach_Res attach_Res = builder18.getAttach();
                                                                                final boolean b18 = attach_Res.isInitialized();
                                                                                if (!b18) {
                                                                                    return false;
                                                                                }
                                                                                break Label_0540;
                                                                            }
                                                                            catch (NullPointerException ex35) {
                                                                                throw b(ex35);
                                                                            }
                                                                        }
                                                                        try {
                                                                            final Builder builder18 = this;
                                                                            final Attach_Res attach_Res = builder18.getAttach();
                                                                            final boolean b18 = attach_Res.isInitialized();
                                                                            if (!b18) {
                                                                                return false;
                                                                            }
                                                                        }
                                                                        catch (NullPointerException ex36) {
                                                                            throw b(ex36);
                                                                        }
                                                                        try {
                                                                            if (!this.hasAttachByName()) {
                                                                                break Label_0570;
                                                                            }
                                                                            final Builder builder19 = this;
                                                                            final AttachByName_Res attachByName_Res = builder19.getAttachByName();
                                                                            final boolean b19 = attachByName_Res.isInitialized();
                                                                            if (!b19) {
                                                                                return false;
                                                                            }
                                                                            break Label_0570;
                                                                        }
                                                                        catch (NullPointerException ex37) {
                                                                            throw b(ex37);
                                                                        }
                                                                    }
                                                                    try {
                                                                        final Builder builder19 = this;
                                                                        final AttachByName_Res attachByName_Res = builder19.getAttachByName();
                                                                        final boolean b19 = attachByName_Res.isInitialized();
                                                                        if (!b19) {
                                                                            return false;
                                                                        }
                                                                    }
                                                                    catch (NullPointerException ex38) {
                                                                        throw b(ex38);
                                                                    }
                                                                    try {
                                                                        if (!this.hasDispatchInput()) {
                                                                            break Label_0600;
                                                                        }
                                                                        final Builder builder20 = this;
                                                                        final DispatchInput_Res dispatchInput_Res = builder20.getDispatchInput();
                                                                        final boolean b20 = dispatchInput_Res.isInitialized();
                                                                        if (!b20) {
                                                                            return false;
                                                                        }
                                                                        break Label_0600;
                                                                    }
                                                                    catch (NullPointerException ex39) {
                                                                        throw b(ex39);
                                                                    }
                                                                }
                                                                try {
                                                                    final Builder builder20 = this;
                                                                    final DispatchInput_Res dispatchInput_Res = builder20.getDispatchInput();
                                                                    final boolean b20 = dispatchInput_Res.isInitialized();
                                                                    if (!b20) {
                                                                        return false;
                                                                    }
                                                                }
                                                                catch (NullPointerException ex40) {
                                                                    throw b(ex40);
                                                                }
                                                                try {
                                                                    if (!this.hasAddWatchpoint()) {
                                                                        break Label_0630;
                                                                    }
                                                                    final Builder builder21 = this;
                                                                    final AddWatchpoint_Res addWatchpoint_Res = builder21.getAddWatchpoint();
                                                                    final boolean b21 = addWatchpoint_Res.isInitialized();
                                                                    if (!b21) {
                                                                        return false;
                                                                    }
                                                                    break Label_0630;
                                                                }
                                                                catch (NullPointerException ex41) {
                                                                    throw b(ex41);
                                                                }
                                                            }
                                                            try {
                                                                final Builder builder21 = this;
                                                                final AddWatchpoint_Res addWatchpoint_Res = builder21.getAddWatchpoint();
                                                                final boolean b21 = addWatchpoint_Res.isInitialized();
                                                                if (!b21) {
                                                                    return false;
                                                                }
                                                            }
                                                            catch (NullPointerException ex42) {
                                                                throw b(ex42);
                                                            }
                                                            try {
                                                                if (!this.hasRemoveWatchpoint()) {
                                                                    break Label_0660;
                                                                }
                                                                final Builder builder22 = this;
                                                                final RemoveWatchpoint_Res removeWatchpoint_Res = builder22.getRemoveWatchpoint();
                                                                final boolean b22 = removeWatchpoint_Res.isInitialized();
                                                                if (!b22) {
                                                                    return false;
                                                                }
                                                                break Label_0660;
                                                            }
                                                            catch (NullPointerException ex43) {
                                                                throw b(ex43);
                                                            }
                                                        }
                                                        try {
                                                            final Builder builder22 = this;
                                                            final RemoveWatchpoint_Res removeWatchpoint_Res = builder22.getRemoveWatchpoint();
                                                            final boolean b22 = removeWatchpoint_Res.isInitialized();
                                                            if (!b22) {
                                                                return false;
                                                            }
                                                        }
                                                        catch (NullPointerException ex44) {
                                                            throw b(ex44);
                                                        }
                                                        try {
                                                            if (!this.hasDetach()) {
                                                                break Label_0690;
                                                            }
                                                            final Builder builder23 = this;
                                                            final Detach_Res detach_Res = builder23.getDetach();
                                                            final boolean b23 = detach_Res.isInitialized();
                                                            if (!b23) {
                                                                return false;
                                                            }
                                                            break Label_0690;
                                                        }
                                                        catch (NullPointerException ex45) {
                                                            throw b(ex45);
                                                        }
                                                    }
                                                    try {
                                                        final Builder builder23 = this;
                                                        final Detach_Res detach_Res = builder23.getDetach();
                                                        final boolean b23 = detach_Res.isInitialized();
                                                        if (!b23) {
                                                            return false;
                                                        }
                                                    }
                                                    catch (NullPointerException ex46) {
                                                        throw b(ex46);
                                                    }
                                                    try {
                                                        if (!this.hasKill()) {
                                                            break Label_0720;
                                                        }
                                                        final Builder builder24 = this;
                                                        final Kill_Res kill_Res = builder24.getKill();
                                                        final boolean b24 = kill_Res.isInitialized();
                                                        if (!b24) {
                                                            return false;
                                                        }
                                                        break Label_0720;
                                                    }
                                                    catch (NullPointerException ex47) {
                                                        throw b(ex47);
                                                    }
                                                }
                                                try {
                                                    final Builder builder24 = this;
                                                    final Kill_Res kill_Res = builder24.getKill();
                                                    final boolean b24 = kill_Res.isInitialized();
                                                    if (!b24) {
                                                        return false;
                                                    }
                                                }
                                                catch (NullPointerException ex48) {
                                                    throw b(ex48);
                                                }
                                                try {
                                                    if (!this.hasGetChildrenCount()) {
                                                        break Label_0750;
                                                    }
                                                    final Builder builder25 = this;
                                                    final GetChildrenCount_Res getChildrenCount_Res = builder25.getGetChildrenCount();
                                                    final boolean b25 = getChildrenCount_Res.isInitialized();
                                                    if (!b25) {
                                                        return false;
                                                    }
                                                    break Label_0750;
                                                }
                                                catch (NullPointerException ex49) {
                                                    throw b(ex49);
                                                }
                                            }
                                            try {
                                                final Builder builder25 = this;
                                                final GetChildrenCount_Res getChildrenCount_Res = builder25.getGetChildrenCount();
                                                final boolean b25 = getChildrenCount_Res.isInitialized();
                                                if (!b25) {
                                                    return false;
                                                }
                                            }
                                            catch (NullPointerException ex50) {
                                                throw b(ex50);
                                            }
                                            try {
                                                if (!this.hasGetArraySlice()) {
                                                    break Label_0780;
                                                }
                                                final Builder builder26 = this;
                                                final GetArraySlice_Res getArraySlice_Res = builder26.getGetArraySlice();
                                                final boolean b26 = getArraySlice_Res.isInitialized();
                                                if (!b26) {
                                                    return false;
                                                }
                                                break Label_0780;
                                            }
                                            catch (NullPointerException ex51) {
                                                throw b(ex51);
                                            }
                                        }
                                        try {
                                            final Builder builder26 = this;
                                            final GetArraySlice_Res getArraySlice_Res = builder26.getGetArraySlice();
                                            final boolean b26 = getArraySlice_Res.isInitialized();
                                            if (!b26) {
                                                return false;
                                            }
                                        }
                                        catch (NullPointerException ex52) {
                                            throw b(ex52);
                                        }
                                        try {
                                            if (!this.hasGetValueData()) {
                                                break Label_0810;
                                            }
                                            final Builder builder27 = this;
                                            final GetValueData_Res getValueData_Res = builder27.getGetValueData();
                                            final boolean b27 = getValueData_Res.isInitialized();
                                            if (!b27) {
                                                return false;
                                            }
                                            break Label_0810;
                                        }
                                        catch (NullPointerException ex53) {
                                            throw b(ex53);
                                        }
                                    }
                                    try {
                                        final Builder builder27 = this;
                                        final GetValueData_Res getValueData_Res = builder27.getGetValueData();
                                        final boolean b27 = getValueData_Res.isInitialized();
                                        if (!b27) {
                                            return false;
                                        }
                                    }
                                    catch (NullPointerException ex54) {
                                        throw b(ex54);
                                    }
                                    try {
                                        if (!this.hasGetValueDescription()) {
                                            break Label_0840;
                                        }
                                        final Builder builder28 = this;
                                        final GetValueDescription_Res getValueDescription_Res = builder28.getGetValueDescription();
                                        final boolean b28 = getValueDescription_Res.isInitialized();
                                        if (!b28) {
                                            return false;
                                        }
                                        break Label_0840;
                                    }
                                    catch (NullPointerException ex55) {
                                        throw b(ex55);
                                    }
                                }
                                try {
                                    final Builder builder28 = this;
                                    final GetValueDescription_Res getValueDescription_Res = builder28.getGetValueDescription();
                                    final boolean b28 = getValueDescription_Res.isInitialized();
                                    if (!b28) {
                                        return false;
                                    }
                                }
                                catch (NullPointerException ex56) {
                                    throw b(ex56);
                                }
                                try {
                                    if (!this.hasValuesFilteringPolicy()) {
                                        break Label_0870;
                                    }
                                    final Builder builder29 = this;
                                    final ValuesFilteringPolicy_Res valuesFilteringPolicy_Res = builder29.getValuesFilteringPolicy();
                                    final boolean b29 = valuesFilteringPolicy_Res.isInitialized();
                                    if (!b29) {
                                        return false;
                                    }
                                    break Label_0870;
                                }
                                catch (NullPointerException ex57) {
                                    throw b(ex57);
                                }
                            }
                            try {
                                final Builder builder29 = this;
                                final ValuesFilteringPolicy_Res valuesFilteringPolicy_Res = builder29.getValuesFilteringPolicy();
                                final boolean b29 = valuesFilteringPolicy_Res.isInitialized();
                                if (!b29) {
                                    return false;
                                }
                            }
                            catch (NullPointerException ex58) {
                                throw b(ex58);
                            }
                            try {
                                if (!this.hasConnectPlatform()) {
                                    break Label_0900;
                                }
                                final Builder builder30 = this;
                                final ConnectPlatform_Res connectPlatform_Res = builder30.getConnectPlatform();
                                final boolean b30 = connectPlatform_Res.isInitialized();
                                if (!b30) {
                                    return false;
                                }
                                break Label_0900;
                            }
                            catch (NullPointerException ex59) {
                                throw b(ex59);
                            }
                        }
                        try {
                            final Builder builder30 = this;
                            final ConnectPlatform_Res connectPlatform_Res = builder30.getConnectPlatform();
                            final boolean b30 = connectPlatform_Res.isInitialized();
                            if (!b30) {
                                return false;
                            }
                        }
                        catch (NullPointerException ex60) {
                            throw b(ex60);
                        }
                        try {
                            if (!this.hasGetValueAddress()) {
                                break Label_0930;
                            }
                            final Builder builder31 = this;
                            final GetValueAddress_Res getValueAddress_Res = builder31.getGetValueAddress();
                            final boolean b31 = getValueAddress_Res.isInitialized();
                            if (!b31) {
                                return false;
                            }
                            break Label_0930;
                        }
                        catch (NullPointerException ex61) {
                            throw b(ex61);
                        }
                    }
                    try {
                        final Builder builder31 = this;
                        final GetValueAddress_Res getValueAddress_Res = builder31.getGetValueAddress();
                        final boolean b31 = getValueAddress_Res.isInitialized();
                        if (!b31) {
                            return false;
                        }
                    }
                    catch (NullPointerException ex62) {
                        throw b(ex62);
                    }
                    try {
                        if (!this.hasHandleSignal()) {
                            break Label_0960;
                        }
                        final Builder builder32 = this;
                        final HandleSignal_Res handleSignal_Res = builder32.getHandleSignal();
                        final boolean b32 = handleSignal_Res.isInitialized();
                        if (!b32) {
                            return false;
                        }
                        break Label_0960;
                    }
                    catch (NullPointerException ex63) {
                        throw b(ex63);
                    }
                }
                try {
                    final Builder builder32 = this;
                    final HandleSignal_Res handleSignal_Res = builder32.getHandleSignal();
                    final boolean b32 = handleSignal_Res.isInitialized();
                    if (!b32) {
                        return false;
                    }
                }
                catch (NullPointerException ex64) {
                    throw b(ex64);
                }
                try {
                    if (!this.hasExecuteShellCommand()) {
                        return true;
                    }
                    final Builder builder33 = this;
                    final ExecuteShellCommand_Res executeShellCommand_Res = builder33.getExecuteShellCommand();
                    final boolean b33 = executeShellCommand_Res.isInitialized();
                    if (!b33) {
                        return false;
                    }
                    return true;
                }
                catch (NullPointerException ex65) {
                    throw b(ex65);
                }
            }
            try {
                final Builder builder33 = this;
                final ExecuteShellCommand_Res executeShellCommand_Res = builder33.getExecuteShellCommand();
                final boolean b33 = executeShellCommand_Res.isInitialized();
                if (!b33) {
                    return false;
                }
            }
            catch (NullPointerException ex66) {
                throw b(ex66);
            }
            return true;
        }
        
        public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            CompositeResponse compositeResponse = null;
            try {
                compositeResponse = (CompositeResponse)CompositeResponse.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            }
            catch (InvalidProtocolBufferException ex) {
                compositeResponse = (CompositeResponse)ex.getUnfinishedMessage();
                throw ex;
            }
            finally {
                try {
                    if (compositeResponse != null) {
                        this.mergeFrom(compositeResponse);
                    }
                }
                catch (InvalidProtocolBufferException ex2) {
                    throw b((Exception)ex2);
                }
            }
            return this;
        }
        
        public boolean hasCreateTarget() {
            try {
                if ((this.bitField0_ & 0x1) == 0x1) {
                    return true;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return false;
        }
        
        public CreateTarget_Res getCreateTarget() {
            try {
                if (this.createTargetBuilder_ == null) {
                    return this.createTarget_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (CreateTarget_Res)this.createTargetBuilder_.getMessage();
        }
        
        public Builder setCreateTarget(final CreateTarget_Res createTarget_Res) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.createTargetBuilder_ != null) {
                                break Label_0042;
                            }
                            final CreateTarget_Res createTarget_Res2 = createTarget_Res;
                            if (createTarget_Res2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final CreateTarget_Res createTarget_Res2 = createTarget_Res;
                            if (createTarget_Res2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.createTarget_ = createTarget_Res;
                    this.onChanged();
                    break Label_0051;
                }
                this.createTargetBuilder_.setMessage((GeneratedMessage)createTarget_Res);
            }
            this.bitField0_ |= 0x1;
            return this;
        }
        
        public Builder setCreateTarget(final CreateTarget_Res.Builder builder) {
            Label_0038: {
                try {
                    if (this.createTargetBuilder_ == null) {
                        this.createTarget_ = builder.build();
                        this.onChanged();
                        break Label_0038;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.createTargetBuilder_.setMessage((GeneratedMessage)builder.build());
            }
            this.bitField0_ |= 0x1;
            return this;
        }
        
        public Builder mergeCreateTarget(final CreateTarget_Res p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.createTargetBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       78
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    11: iconst_1       
            //    12: iand           
            //    13: iconst_1       
            //    14: if_icmpne       66
            //    17: goto            24
            //    20: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    23: athrow         
            //    24: aload_0        
            //    25: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.createTarget_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CreateTarget_Res;
            //    28: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CreateTarget_Res.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CreateTarget_Res;
            //    31: if_acmpeq       66
            //    34: goto            41
            //    37: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    40: athrow         
            //    41: aload_0        
            //    42: aload_0        
            //    43: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.createTarget_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CreateTarget_Res;
            //    46: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CreateTarget_Res.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CreateTarget_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CreateTarget_Res$Builder;
            //    49: aload_1        
            //    50: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CreateTarget_Res$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CreateTarget_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CreateTarget_Res$Builder;
            //    53: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CreateTarget_Res$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CreateTarget_Res;
            //    56: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.createTarget_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CreateTarget_Res;
            //    59: goto            71
            //    62: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    65: athrow         
            //    66: aload_0        
            //    67: aload_1        
            //    68: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.createTarget_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CreateTarget_Res;
            //    71: aload_0        
            //    72: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.onChanged:()V
            //    75: goto            87
            //    78: aload_0        
            //    79: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.createTargetBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    82: aload_1        
            //    83: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    86: pop            
            //    87: aload_0        
            //    88: dup            
            //    89: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    92: iconst_1       
            //    93: ior            
            //    94: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    97: aload_0        
            //    98: areturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                            
            //  -----  -----  -----  -----  --------------------------------
            //  0      17     20     24     Ljava/lang/NullPointerException;
            //  7      34     37     41     Ljava/lang/NullPointerException;
            //  24     62     62     66     Ljava/lang/NullPointerException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0024:
            //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
            //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        public Builder clearCreateTarget() {
            Label_0033: {
                try {
                    if (this.createTargetBuilder_ == null) {
                        this.createTarget_ = CreateTarget_Res.getDefaultInstance();
                        this.onChanged();
                        break Label_0033;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.createTargetBuilder_.clear();
            }
            this.bitField0_ &= 0xFFFFFFFE;
            return this;
        }
        
        public CreateTarget_Res.Builder getCreateTargetBuilder() {
            this.bitField0_ |= 0x1;
            this.onChanged();
            return (CreateTarget_Res.Builder)this.x().getBuilder();
        }
        
        public CreateTarget_ResOrBuilder getCreateTargetOrBuilder() {
            try {
                if (this.createTargetBuilder_ != null) {
                    return (CreateTarget_ResOrBuilder)this.createTargetBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.createTarget_;
        }
        
        private SingleFieldBuilder<CreateTarget_Res, CreateTarget_Res.Builder, CreateTarget_ResOrBuilder> x() {
            try {
                if (this.createTargetBuilder_ == null) {
                    this.createTargetBuilder_ = (SingleFieldBuilder<CreateTarget_Res, CreateTarget_Res.Builder, CreateTarget_ResOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.createTarget_, this.getParentForChildren(), this.isClean());
                    this.createTarget_ = null;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.createTargetBuilder_;
        }
        
        public boolean hasLaunch() {
            try {
                if ((this.bitField0_ & 0x2) == 0x2) {
                    return true;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return false;
        }
        
        public Launch_Res getLaunch() {
            try {
                if (this.launchBuilder_ == null) {
                    return this.launch_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (Launch_Res)this.launchBuilder_.getMessage();
        }
        
        public Builder setLaunch(final Launch_Res launch_Res) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.launchBuilder_ != null) {
                                break Label_0042;
                            }
                            final Launch_Res launch_Res2 = launch_Res;
                            if (launch_Res2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final Launch_Res launch_Res2 = launch_Res;
                            if (launch_Res2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.launch_ = launch_Res;
                    this.onChanged();
                    break Label_0051;
                }
                this.launchBuilder_.setMessage((GeneratedMessage)launch_Res);
            }
            this.bitField0_ |= 0x2;
            return this;
        }
        
        public Builder setLaunch(final Launch_Res.Builder builder) {
            Label_0038: {
                try {
                    if (this.launchBuilder_ == null) {
                        this.launch_ = builder.build();
                        this.onChanged();
                        break Label_0038;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.launchBuilder_.setMessage((GeneratedMessage)builder.build());
            }
            this.bitField0_ |= 0x2;
            return this;
        }
        
        public Builder mergeLaunch(final Launch_Res p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.launchBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       78
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    11: iconst_2       
            //    12: iand           
            //    13: iconst_2       
            //    14: if_icmpne       66
            //    17: goto            24
            //    20: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    23: athrow         
            //    24: aload_0        
            //    25: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.launch_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Launch_Res;
            //    28: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Launch_Res.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Launch_Res;
            //    31: if_acmpeq       66
            //    34: goto            41
            //    37: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    40: athrow         
            //    41: aload_0        
            //    42: aload_0        
            //    43: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.launch_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Launch_Res;
            //    46: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Launch_Res.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Launch_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Launch_Res$Builder;
            //    49: aload_1        
            //    50: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Launch_Res$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Launch_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Launch_Res$Builder;
            //    53: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Launch_Res$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Launch_Res;
            //    56: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.launch_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Launch_Res;
            //    59: goto            71
            //    62: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    65: athrow         
            //    66: aload_0        
            //    67: aload_1        
            //    68: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.launch_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Launch_Res;
            //    71: aload_0        
            //    72: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.onChanged:()V
            //    75: goto            87
            //    78: aload_0        
            //    79: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.launchBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    82: aload_1        
            //    83: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    86: pop            
            //    87: aload_0        
            //    88: dup            
            //    89: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    92: iconst_2       
            //    93: ior            
            //    94: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    97: aload_0        
            //    98: areturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                            
            //  -----  -----  -----  -----  --------------------------------
            //  0      17     20     24     Ljava/lang/NullPointerException;
            //  7      34     37     41     Ljava/lang/NullPointerException;
            //  24     62     62     66     Ljava/lang/NullPointerException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0024:
            //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
            //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        public Builder clearLaunch() {
            Label_0033: {
                try {
                    if (this.launchBuilder_ == null) {
                        this.launch_ = Launch_Res.getDefaultInstance();
                        this.onChanged();
                        break Label_0033;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.launchBuilder_.clear();
            }
            this.bitField0_ &= 0xFFFFFFFD;
            return this;
        }
        
        public Launch_Res.Builder getLaunchBuilder() {
            this.bitField0_ |= 0x2;
            this.onChanged();
            return (Launch_Res.Builder)this.r().getBuilder();
        }
        
        public Launch_ResOrBuilder getLaunchOrBuilder() {
            try {
                if (this.launchBuilder_ != null) {
                    return (Launch_ResOrBuilder)this.launchBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.launch_;
        }
        
        private SingleFieldBuilder<Launch_Res, Launch_Res.Builder, Launch_ResOrBuilder> r() {
            try {
                if (this.launchBuilder_ == null) {
                    this.launchBuilder_ = (SingleFieldBuilder<Launch_Res, Launch_Res.Builder, Launch_ResOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.launch_, this.getParentForChildren(), this.isClean());
                    this.launch_ = null;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.launchBuilder_;
        }
        
        public boolean hasContinue() {
            try {
                if ((this.bitField0_ & 0x4) == 0x4) {
                    return true;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return false;
        }
        
        public Continue_Res getContinue() {
            try {
                if (this.continueBuilder_ == null) {
                    return this.continue_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (Continue_Res)this.continueBuilder_.getMessage();
        }
        
        public Builder setContinue(final Continue_Res continue_Res) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.continueBuilder_ != null) {
                                break Label_0042;
                            }
                            final Continue_Res continue_Res2 = continue_Res;
                            if (continue_Res2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final Continue_Res continue_Res2 = continue_Res;
                            if (continue_Res2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.continue_ = continue_Res;
                    this.onChanged();
                    break Label_0051;
                }
                this.continueBuilder_.setMessage((GeneratedMessage)continue_Res);
            }
            this.bitField0_ |= 0x4;
            return this;
        }
        
        public Builder setContinue(final Continue_Res.Builder builder) {
            Label_0038: {
                try {
                    if (this.continueBuilder_ == null) {
                        this.continue_ = builder.build();
                        this.onChanged();
                        break Label_0038;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.continueBuilder_.setMessage((GeneratedMessage)builder.build());
            }
            this.bitField0_ |= 0x4;
            return this;
        }
        
        public Builder mergeContinue(final Continue_Res p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.continueBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       78
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    11: iconst_4       
            //    12: iand           
            //    13: iconst_4       
            //    14: if_icmpne       66
            //    17: goto            24
            //    20: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    23: athrow         
            //    24: aload_0        
            //    25: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.continue_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Continue_Res;
            //    28: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Continue_Res.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Continue_Res;
            //    31: if_acmpeq       66
            //    34: goto            41
            //    37: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    40: athrow         
            //    41: aload_0        
            //    42: aload_0        
            //    43: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.continue_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Continue_Res;
            //    46: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Continue_Res.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Continue_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Continue_Res$Builder;
            //    49: aload_1        
            //    50: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Continue_Res$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Continue_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Continue_Res$Builder;
            //    53: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Continue_Res$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Continue_Res;
            //    56: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.continue_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Continue_Res;
            //    59: goto            71
            //    62: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    65: athrow         
            //    66: aload_0        
            //    67: aload_1        
            //    68: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.continue_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Continue_Res;
            //    71: aload_0        
            //    72: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.onChanged:()V
            //    75: goto            87
            //    78: aload_0        
            //    79: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.continueBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    82: aload_1        
            //    83: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    86: pop            
            //    87: aload_0        
            //    88: dup            
            //    89: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    92: iconst_4       
            //    93: ior            
            //    94: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    97: aload_0        
            //    98: areturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                            
            //  -----  -----  -----  -----  --------------------------------
            //  0      17     20     24     Ljava/lang/NullPointerException;
            //  7      34     37     41     Ljava/lang/NullPointerException;
            //  24     62     62     66     Ljava/lang/NullPointerException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0024:
            //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
            //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        public Builder clearContinue() {
            Label_0033: {
                try {
                    if (this.continueBuilder_ == null) {
                        this.continue_ = Continue_Res.getDefaultInstance();
                        this.onChanged();
                        break Label_0033;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.continueBuilder_.clear();
            }
            this.bitField0_ &= 0xFFFFFFFB;
            return this;
        }
        
        public Continue_Res.Builder getContinueBuilder() {
            this.bitField0_ |= 0x4;
            this.onChanged();
            return (Continue_Res.Builder)this.v().getBuilder();
        }
        
        public Continue_ResOrBuilder getContinueOrBuilder() {
            try {
                if (this.continueBuilder_ != null) {
                    return (Continue_ResOrBuilder)this.continueBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.continue_;
        }
        
        private SingleFieldBuilder<Continue_Res, Continue_Res.Builder, Continue_ResOrBuilder> v() {
            try {
                if (this.continueBuilder_ == null) {
                    this.continueBuilder_ = (SingleFieldBuilder<Continue_Res, Continue_Res.Builder, Continue_ResOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.continue_, this.getParentForChildren(), this.isClean());
                    this.continue_ = null;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.continueBuilder_;
        }
        
        public boolean hasCompositeBroadcast() {
            try {
                if ((this.bitField0_ & 0x8) == 0x8) {
                    return true;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return false;
        }
        
        public Broadcasts.CompositeBroadcast getCompositeBroadcast() {
            try {
                if (this.compositeBroadcastBuilder_ == null) {
                    return this.compositeBroadcast_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (Broadcasts.CompositeBroadcast)this.compositeBroadcastBuilder_.getMessage();
        }
        
        public Builder setCompositeBroadcast(final Broadcasts.CompositeBroadcast compositeBroadcast) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.compositeBroadcastBuilder_ != null) {
                                break Label_0042;
                            }
                            final Broadcasts.CompositeBroadcast compositeBroadcast2 = compositeBroadcast;
                            if (compositeBroadcast2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final Broadcasts.CompositeBroadcast compositeBroadcast2 = compositeBroadcast;
                            if (compositeBroadcast2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.compositeBroadcast_ = compositeBroadcast;
                    this.onChanged();
                    break Label_0051;
                }
                this.compositeBroadcastBuilder_.setMessage((GeneratedMessage)compositeBroadcast);
            }
            this.bitField0_ |= 0x8;
            return this;
        }
        
        public Builder setCompositeBroadcast(final Broadcasts.CompositeBroadcast.Builder builder) {
            Label_0038: {
                try {
                    if (this.compositeBroadcastBuilder_ == null) {
                        this.compositeBroadcast_ = builder.build();
                        this.onChanged();
                        break Label_0038;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.compositeBroadcastBuilder_.setMessage((GeneratedMessage)builder.build());
            }
            this.bitField0_ |= 0x8;
            return this;
        }
        
        public Builder mergeCompositeBroadcast(final Broadcasts.CompositeBroadcast p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.compositeBroadcastBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       80
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    11: bipush          8
            //    13: iand           
            //    14: bipush          8
            //    16: if_icmpne       68
            //    19: goto            26
            //    22: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    25: athrow         
            //    26: aload_0        
            //    27: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.compositeBroadcast_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast;
            //    30: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast;
            //    33: if_acmpeq       68
            //    36: goto            43
            //    39: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    42: athrow         
            //    43: aload_0        
            //    44: aload_0        
            //    45: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.compositeBroadcast_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast;
            //    48: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder;
            //    51: aload_1        
            //    52: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder;
            //    55: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast;
            //    58: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.compositeBroadcast_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast;
            //    61: goto            73
            //    64: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    67: athrow         
            //    68: aload_0        
            //    69: aload_1        
            //    70: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.compositeBroadcast_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Broadcasts$CompositeBroadcast;
            //    73: aload_0        
            //    74: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.onChanged:()V
            //    77: goto            89
            //    80: aload_0        
            //    81: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.compositeBroadcastBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    84: aload_1        
            //    85: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    88: pop            
            //    89: aload_0        
            //    90: dup            
            //    91: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    94: bipush          8
            //    96: ior            
            //    97: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //   100: aload_0        
            //   101: areturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                            
            //  -----  -----  -----  -----  --------------------------------
            //  0      19     22     26     Ljava/lang/NullPointerException;
            //  7      36     39     43     Ljava/lang/NullPointerException;
            //  26     64     64     68     Ljava/lang/NullPointerException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0026:
            //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
            //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        public Builder clearCompositeBroadcast() {
            Label_0033: {
                try {
                    if (this.compositeBroadcastBuilder_ == null) {
                        this.compositeBroadcast_ = Broadcasts.CompositeBroadcast.getDefaultInstance();
                        this.onChanged();
                        break Label_0033;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.compositeBroadcastBuilder_.clear();
            }
            this.bitField0_ &= 0xFFFFFFF7;
            return this;
        }
        
        public Broadcasts.CompositeBroadcast.Builder getCompositeBroadcastBuilder() {
            this.bitField0_ |= 0x8;
            this.onChanged();
            return (Broadcasts.CompositeBroadcast.Builder)this.B().getBuilder();
        }
        
        public Broadcasts.CompositeBroadcastOrBuilder getCompositeBroadcastOrBuilder() {
            try {
                if (this.compositeBroadcastBuilder_ != null) {
                    return (Broadcasts.CompositeBroadcastOrBuilder)this.compositeBroadcastBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.compositeBroadcast_;
        }
        
        private SingleFieldBuilder<Broadcasts.CompositeBroadcast, Broadcasts.CompositeBroadcast.Builder, Broadcasts.CompositeBroadcastOrBuilder> B() {
            try {
                if (this.compositeBroadcastBuilder_ == null) {
                    this.compositeBroadcastBuilder_ = (SingleFieldBuilder<Broadcasts.CompositeBroadcast, Broadcasts.CompositeBroadcast.Builder, Broadcasts.CompositeBroadcastOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.compositeBroadcast_, this.getParentForChildren(), this.isClean());
                    this.compositeBroadcast_ = null;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.compositeBroadcastBuilder_;
        }
        
        public boolean hasSuspend() {
            try {
                if ((this.bitField0_ & 0x10) == 0x10) {
                    return true;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return false;
        }
        
        public Suspend_Res getSuspend() {
            try {
                if (this.suspendBuilder_ == null) {
                    return this.suspend_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (Suspend_Res)this.suspendBuilder_.getMessage();
        }
        
        public Builder setSuspend(final Suspend_Res suspend_Res) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.suspendBuilder_ != null) {
                                break Label_0042;
                            }
                            final Suspend_Res suspend_Res2 = suspend_Res;
                            if (suspend_Res2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final Suspend_Res suspend_Res2 = suspend_Res;
                            if (suspend_Res2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.suspend_ = suspend_Res;
                    this.onChanged();
                    break Label_0051;
                }
                this.suspendBuilder_.setMessage((GeneratedMessage)suspend_Res);
            }
            this.bitField0_ |= 0x10;
            return this;
        }
        
        public Builder setSuspend(final Suspend_Res.Builder builder) {
            Label_0038: {
                try {
                    if (this.suspendBuilder_ == null) {
                        this.suspend_ = builder.build();
                        this.onChanged();
                        break Label_0038;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.suspendBuilder_.setMessage((GeneratedMessage)builder.build());
            }
            this.bitField0_ |= 0x10;
            return this;
        }
        
        public Builder mergeSuspend(final Suspend_Res p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.suspendBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       80
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    11: bipush          16
            //    13: iand           
            //    14: bipush          16
            //    16: if_icmpne       68
            //    19: goto            26
            //    22: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    25: athrow         
            //    26: aload_0        
            //    27: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.suspend_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Suspend_Res;
            //    30: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Suspend_Res.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Suspend_Res;
            //    33: if_acmpeq       68
            //    36: goto            43
            //    39: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    42: athrow         
            //    43: aload_0        
            //    44: aload_0        
            //    45: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.suspend_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Suspend_Res;
            //    48: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Suspend_Res.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Suspend_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Suspend_Res$Builder;
            //    51: aload_1        
            //    52: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Suspend_Res$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Suspend_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Suspend_Res$Builder;
            //    55: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Suspend_Res$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Suspend_Res;
            //    58: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.suspend_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Suspend_Res;
            //    61: goto            73
            //    64: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    67: athrow         
            //    68: aload_0        
            //    69: aload_1        
            //    70: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.suspend_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Suspend_Res;
            //    73: aload_0        
            //    74: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.onChanged:()V
            //    77: goto            89
            //    80: aload_0        
            //    81: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.suspendBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    84: aload_1        
            //    85: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    88: pop            
            //    89: aload_0        
            //    90: dup            
            //    91: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    94: bipush          16
            //    96: ior            
            //    97: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //   100: aload_0        
            //   101: areturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                            
            //  -----  -----  -----  -----  --------------------------------
            //  0      19     22     26     Ljava/lang/NullPointerException;
            //  7      36     39     43     Ljava/lang/NullPointerException;
            //  26     64     64     68     Ljava/lang/NullPointerException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0026:
            //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
            //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        public Builder clearSuspend() {
            Label_0033: {
                try {
                    if (this.suspendBuilder_ == null) {
                        this.suspend_ = Suspend_Res.getDefaultInstance();
                        this.onChanged();
                        break Label_0033;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.suspendBuilder_.clear();
            }
            this.bitField0_ &= 0xFFFFFFEF;
            return this;
        }
        
        public Suspend_Res.Builder getSuspendBuilder() {
            this.bitField0_ |= 0x10;
            this.onChanged();
            return (Suspend_Res.Builder)this.p().getBuilder();
        }
        
        public Suspend_ResOrBuilder getSuspendOrBuilder() {
            try {
                if (this.suspendBuilder_ != null) {
                    return (Suspend_ResOrBuilder)this.suspendBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.suspend_;
        }
        
        private SingleFieldBuilder<Suspend_Res, Suspend_Res.Builder, Suspend_ResOrBuilder> p() {
            try {
                if (this.suspendBuilder_ == null) {
                    this.suspendBuilder_ = (SingleFieldBuilder<Suspend_Res, Suspend_Res.Builder, Suspend_ResOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.suspend_, this.getParentForChildren(), this.isClean());
                    this.suspend_ = null;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.suspendBuilder_;
        }
        
        public boolean hasGetThreads() {
            try {
                if ((this.bitField0_ & 0x20) == 0x20) {
                    return true;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return false;
        }
        
        public GetThreads_Res getGetThreads() {
            try {
                if (this.getThreadsBuilder_ == null) {
                    return this.getThreads_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (GetThreads_Res)this.getThreadsBuilder_.getMessage();
        }
        
        public Builder setGetThreads(final GetThreads_Res getThreads_Res) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.getThreadsBuilder_ != null) {
                                break Label_0042;
                            }
                            final GetThreads_Res getThreads_Res2 = getThreads_Res;
                            if (getThreads_Res2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final GetThreads_Res getThreads_Res2 = getThreads_Res;
                            if (getThreads_Res2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.getThreads_ = getThreads_Res;
                    this.onChanged();
                    break Label_0051;
                }
                this.getThreadsBuilder_.setMessage((GeneratedMessage)getThreads_Res);
            }
            this.bitField0_ |= 0x20;
            return this;
        }
        
        public Builder setGetThreads(final GetThreads_Res.Builder builder) {
            Label_0038: {
                try {
                    if (this.getThreadsBuilder_ == null) {
                        this.getThreads_ = builder.build();
                        this.onChanged();
                        break Label_0038;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.getThreadsBuilder_.setMessage((GeneratedMessage)builder.build());
            }
            this.bitField0_ |= 0x20;
            return this;
        }
        
        public Builder mergeGetThreads(final GetThreads_Res p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getThreadsBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       80
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    11: bipush          32
            //    13: iand           
            //    14: bipush          32
            //    16: if_icmpne       68
            //    19: goto            26
            //    22: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    25: athrow         
            //    26: aload_0        
            //    27: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getThreads_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res;
            //    30: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res;
            //    33: if_acmpeq       68
            //    36: goto            43
            //    39: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    42: athrow         
            //    43: aload_0        
            //    44: aload_0        
            //    45: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getThreads_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res;
            //    48: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res$Builder;
            //    51: aload_1        
            //    52: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res$Builder;
            //    55: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res;
            //    58: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getThreads_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res;
            //    61: goto            73
            //    64: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    67: athrow         
            //    68: aload_0        
            //    69: aload_1        
            //    70: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getThreads_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Res;
            //    73: aload_0        
            //    74: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.onChanged:()V
            //    77: goto            89
            //    80: aload_0        
            //    81: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getThreadsBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    84: aload_1        
            //    85: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    88: pop            
            //    89: aload_0        
            //    90: dup            
            //    91: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    94: bipush          32
            //    96: ior            
            //    97: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //   100: aload_0        
            //   101: areturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                            
            //  -----  -----  -----  -----  --------------------------------
            //  0      19     22     26     Ljava/lang/NullPointerException;
            //  7      36     39     43     Ljava/lang/NullPointerException;
            //  26     64     64     68     Ljava/lang/NullPointerException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0026:
            //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
            //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        public Builder clearGetThreads() {
            Label_0033: {
                try {
                    if (this.getThreadsBuilder_ == null) {
                        this.getThreads_ = GetThreads_Res.getDefaultInstance();
                        this.onChanged();
                        break Label_0033;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.getThreadsBuilder_.clear();
            }
            this.bitField0_ &= 0xFFFFFFDF;
            return this;
        }
        
        public GetThreads_Res.Builder getGetThreadsBuilder() {
            this.bitField0_ |= 0x20;
            this.onChanged();
            return (GetThreads_Res.Builder)this.C().getBuilder();
        }
        
        public GetThreads_ResOrBuilder getGetThreadsOrBuilder() {
            try {
                if (this.getThreadsBuilder_ != null) {
                    return (GetThreads_ResOrBuilder)this.getThreadsBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.getThreads_;
        }
        
        private SingleFieldBuilder<GetThreads_Res, GetThreads_Res.Builder, GetThreads_ResOrBuilder> C() {
            try {
                if (this.getThreadsBuilder_ == null) {
                    this.getThreadsBuilder_ = (SingleFieldBuilder<GetThreads_Res, GetThreads_Res.Builder, GetThreads_ResOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.getThreads_, this.getParentForChildren(), this.isClean());
                    this.getThreads_ = null;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.getThreadsBuilder_;
        }
        
        public boolean hasGetFrames() {
            try {
                if ((this.bitField0_ & 0x40) == 0x40) {
                    return true;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return false;
        }
        
        public GetFrames_Res getGetFrames() {
            try {
                if (this.getFramesBuilder_ == null) {
                    return this.getFrames_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (GetFrames_Res)this.getFramesBuilder_.getMessage();
        }
        
        public Builder setGetFrames(final GetFrames_Res getFrames_Res) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.getFramesBuilder_ != null) {
                                break Label_0042;
                            }
                            final GetFrames_Res getFrames_Res2 = getFrames_Res;
                            if (getFrames_Res2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final GetFrames_Res getFrames_Res2 = getFrames_Res;
                            if (getFrames_Res2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.getFrames_ = getFrames_Res;
                    this.onChanged();
                    break Label_0051;
                }
                this.getFramesBuilder_.setMessage((GeneratedMessage)getFrames_Res);
            }
            this.bitField0_ |= 0x40;
            return this;
        }
        
        public Builder setGetFrames(final GetFrames_Res.Builder builder) {
            Label_0038: {
                try {
                    if (this.getFramesBuilder_ == null) {
                        this.getFrames_ = builder.build();
                        this.onChanged();
                        break Label_0038;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.getFramesBuilder_.setMessage((GeneratedMessage)builder.build());
            }
            this.bitField0_ |= 0x40;
            return this;
        }
        
        public Builder mergeGetFrames(final GetFrames_Res p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getFramesBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       80
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    11: bipush          64
            //    13: iand           
            //    14: bipush          64
            //    16: if_icmpne       68
            //    19: goto            26
            //    22: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    25: athrow         
            //    26: aload_0        
            //    27: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getFrames_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetFrames_Res;
            //    30: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetFrames_Res.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetFrames_Res;
            //    33: if_acmpeq       68
            //    36: goto            43
            //    39: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    42: athrow         
            //    43: aload_0        
            //    44: aload_0        
            //    45: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getFrames_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetFrames_Res;
            //    48: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetFrames_Res.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetFrames_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetFrames_Res$Builder;
            //    51: aload_1        
            //    52: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetFrames_Res$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetFrames_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetFrames_Res$Builder;
            //    55: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetFrames_Res$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetFrames_Res;
            //    58: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getFrames_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetFrames_Res;
            //    61: goto            73
            //    64: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    67: athrow         
            //    68: aload_0        
            //    69: aload_1        
            //    70: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getFrames_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetFrames_Res;
            //    73: aload_0        
            //    74: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.onChanged:()V
            //    77: goto            89
            //    80: aload_0        
            //    81: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getFramesBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    84: aload_1        
            //    85: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    88: pop            
            //    89: aload_0        
            //    90: dup            
            //    91: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    94: bipush          64
            //    96: ior            
            //    97: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //   100: aload_0        
            //   101: areturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                            
            //  -----  -----  -----  -----  --------------------------------
            //  0      19     22     26     Ljava/lang/NullPointerException;
            //  7      36     39     43     Ljava/lang/NullPointerException;
            //  26     64     64     68     Ljava/lang/NullPointerException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0026:
            //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
            //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        public Builder clearGetFrames() {
            Label_0033: {
                try {
                    if (this.getFramesBuilder_ == null) {
                        this.getFrames_ = GetFrames_Res.getDefaultInstance();
                        this.onChanged();
                        break Label_0033;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.getFramesBuilder_.clear();
            }
            this.bitField0_ &= 0xFFFFFFBF;
            return this;
        }
        
        public GetFrames_Res.Builder getGetFramesBuilder() {
            this.bitField0_ |= 0x40;
            this.onChanged();
            return (GetFrames_Res.Builder)this.a().getBuilder();
        }
        
        public GetFrames_ResOrBuilder getGetFramesOrBuilder() {
            try {
                if (this.getFramesBuilder_ != null) {
                    return (GetFrames_ResOrBuilder)this.getFramesBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.getFrames_;
        }
        
        private SingleFieldBuilder<GetFrames_Res, GetFrames_Res.Builder, GetFrames_ResOrBuilder> a() {
            try {
                if (this.getFramesBuilder_ == null) {
                    this.getFramesBuilder_ = (SingleFieldBuilder<GetFrames_Res, GetFrames_Res.Builder, GetFrames_ResOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.getFrames_, this.getParentForChildren(), this.isClean());
                    this.getFrames_ = null;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.getFramesBuilder_;
        }
        
        public boolean hasAddBreakpoint() {
            try {
                if ((this.bitField0_ & 0x80) == 0x80) {
                    return true;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return false;
        }
        
        public AddBreakpoint_Res getAddBreakpoint() {
            try {
                if (this.addBreakpointBuilder_ == null) {
                    return this.addBreakpoint_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (AddBreakpoint_Res)this.addBreakpointBuilder_.getMessage();
        }
        
        public Builder setAddBreakpoint(final AddBreakpoint_Res addBreakpoint_Res) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.addBreakpointBuilder_ != null) {
                                break Label_0042;
                            }
                            final AddBreakpoint_Res addBreakpoint_Res2 = addBreakpoint_Res;
                            if (addBreakpoint_Res2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final AddBreakpoint_Res addBreakpoint_Res2 = addBreakpoint_Res;
                            if (addBreakpoint_Res2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.addBreakpoint_ = addBreakpoint_Res;
                    this.onChanged();
                    break Label_0051;
                }
                this.addBreakpointBuilder_.setMessage((GeneratedMessage)addBreakpoint_Res);
            }
            this.bitField0_ |= 0x80;
            return this;
        }
        
        public Builder setAddBreakpoint(final AddBreakpoint_Res.Builder builder) {
            Label_0038: {
                try {
                    if (this.addBreakpointBuilder_ == null) {
                        this.addBreakpoint_ = builder.build();
                        this.onChanged();
                        break Label_0038;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.addBreakpointBuilder_.setMessage((GeneratedMessage)builder.build());
            }
            this.bitField0_ |= 0x80;
            return this;
        }
        
        public Builder mergeAddBreakpoint(final AddBreakpoint_Res p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.addBreakpointBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       82
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    11: sipush          128
            //    14: iand           
            //    15: sipush          128
            //    18: if_icmpne       70
            //    21: goto            28
            //    24: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    27: athrow         
            //    28: aload_0        
            //    29: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.addBreakpoint_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddBreakpoint_Res;
            //    32: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddBreakpoint_Res.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddBreakpoint_Res;
            //    35: if_acmpeq       70
            //    38: goto            45
            //    41: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    44: athrow         
            //    45: aload_0        
            //    46: aload_0        
            //    47: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.addBreakpoint_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddBreakpoint_Res;
            //    50: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddBreakpoint_Res.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddBreakpoint_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddBreakpoint_Res$Builder;
            //    53: aload_1        
            //    54: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddBreakpoint_Res$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddBreakpoint_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddBreakpoint_Res$Builder;
            //    57: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddBreakpoint_Res$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddBreakpoint_Res;
            //    60: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.addBreakpoint_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddBreakpoint_Res;
            //    63: goto            75
            //    66: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    69: athrow         
            //    70: aload_0        
            //    71: aload_1        
            //    72: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.addBreakpoint_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddBreakpoint_Res;
            //    75: aload_0        
            //    76: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.onChanged:()V
            //    79: goto            91
            //    82: aload_0        
            //    83: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.addBreakpointBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    86: aload_1        
            //    87: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    90: pop            
            //    91: aload_0        
            //    92: dup            
            //    93: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    96: sipush          128
            //    99: ior            
            //   100: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //   103: aload_0        
            //   104: areturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                            
            //  -----  -----  -----  -----  --------------------------------
            //  0      21     24     28     Ljava/lang/NullPointerException;
            //  7      38     41     45     Ljava/lang/NullPointerException;
            //  28     66     66     70     Ljava/lang/NullPointerException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0028:
            //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
            //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        public Builder clearAddBreakpoint() {
            Label_0033: {
                try {
                    if (this.addBreakpointBuilder_ == null) {
                        this.addBreakpoint_ = AddBreakpoint_Res.getDefaultInstance();
                        this.onChanged();
                        break Label_0033;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.addBreakpointBuilder_.clear();
            }
            this.bitField0_ &= 0xFFFFFF7F;
            return this;
        }
        
        public AddBreakpoint_Res.Builder getAddBreakpointBuilder() {
            this.bitField0_ |= 0x80;
            this.onChanged();
            return (AddBreakpoint_Res.Builder)this.I().getBuilder();
        }
        
        public AddBreakpoint_ResOrBuilder getAddBreakpointOrBuilder() {
            try {
                if (this.addBreakpointBuilder_ != null) {
                    return (AddBreakpoint_ResOrBuilder)this.addBreakpointBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.addBreakpoint_;
        }
        
        private SingleFieldBuilder<AddBreakpoint_Res, AddBreakpoint_Res.Builder, AddBreakpoint_ResOrBuilder> I() {
            try {
                if (this.addBreakpointBuilder_ == null) {
                    this.addBreakpointBuilder_ = (SingleFieldBuilder<AddBreakpoint_Res, AddBreakpoint_Res.Builder, AddBreakpoint_ResOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.addBreakpoint_, this.getParentForChildren(), this.isClean());
                    this.addBreakpoint_ = null;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.addBreakpointBuilder_;
        }
        
        public boolean hasRemoveBreakpoint() {
            try {
                if ((this.bitField0_ & 0x100) == 0x100) {
                    return true;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return false;
        }
        
        public RemoveBreakpoint_Res getRemoveBreakpoint() {
            try {
                if (this.removeBreakpointBuilder_ == null) {
                    return this.removeBreakpoint_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (RemoveBreakpoint_Res)this.removeBreakpointBuilder_.getMessage();
        }
        
        public Builder setRemoveBreakpoint(final RemoveBreakpoint_Res removeBreakpoint_Res) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.removeBreakpointBuilder_ != null) {
                                break Label_0042;
                            }
                            final RemoveBreakpoint_Res removeBreakpoint_Res2 = removeBreakpoint_Res;
                            if (removeBreakpoint_Res2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final RemoveBreakpoint_Res removeBreakpoint_Res2 = removeBreakpoint_Res;
                            if (removeBreakpoint_Res2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.removeBreakpoint_ = removeBreakpoint_Res;
                    this.onChanged();
                    break Label_0051;
                }
                this.removeBreakpointBuilder_.setMessage((GeneratedMessage)removeBreakpoint_Res);
            }
            this.bitField0_ |= 0x100;
            return this;
        }
        
        public Builder setRemoveBreakpoint(final RemoveBreakpoint_Res.Builder builder) {
            Label_0038: {
                try {
                    if (this.removeBreakpointBuilder_ == null) {
                        this.removeBreakpoint_ = builder.build();
                        this.onChanged();
                        break Label_0038;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.removeBreakpointBuilder_.setMessage((GeneratedMessage)builder.build());
            }
            this.bitField0_ |= 0x100;
            return this;
        }
        
        public Builder mergeRemoveBreakpoint(final RemoveBreakpoint_Res p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.removeBreakpointBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       82
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    11: sipush          256
            //    14: iand           
            //    15: sipush          256
            //    18: if_icmpne       70
            //    21: goto            28
            //    24: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    27: athrow         
            //    28: aload_0        
            //    29: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.removeBreakpoint_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveBreakpoint_Res;
            //    32: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveBreakpoint_Res.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveBreakpoint_Res;
            //    35: if_acmpeq       70
            //    38: goto            45
            //    41: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    44: athrow         
            //    45: aload_0        
            //    46: aload_0        
            //    47: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.removeBreakpoint_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveBreakpoint_Res;
            //    50: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveBreakpoint_Res.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveBreakpoint_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveBreakpoint_Res$Builder;
            //    53: aload_1        
            //    54: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveBreakpoint_Res$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveBreakpoint_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveBreakpoint_Res$Builder;
            //    57: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveBreakpoint_Res$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveBreakpoint_Res;
            //    60: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.removeBreakpoint_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveBreakpoint_Res;
            //    63: goto            75
            //    66: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    69: athrow         
            //    70: aload_0        
            //    71: aload_1        
            //    72: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.removeBreakpoint_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveBreakpoint_Res;
            //    75: aload_0        
            //    76: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.onChanged:()V
            //    79: goto            91
            //    82: aload_0        
            //    83: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.removeBreakpointBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    86: aload_1        
            //    87: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    90: pop            
            //    91: aload_0        
            //    92: dup            
            //    93: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    96: sipush          256
            //    99: ior            
            //   100: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //   103: aload_0        
            //   104: areturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                            
            //  -----  -----  -----  -----  --------------------------------
            //  0      21     24     28     Ljava/lang/NullPointerException;
            //  7      38     41     45     Ljava/lang/NullPointerException;
            //  28     66     66     70     Ljava/lang/NullPointerException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0028:
            //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
            //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        public Builder clearRemoveBreakpoint() {
            Label_0033: {
                try {
                    if (this.removeBreakpointBuilder_ == null) {
                        this.removeBreakpoint_ = RemoveBreakpoint_Res.getDefaultInstance();
                        this.onChanged();
                        break Label_0033;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.removeBreakpointBuilder_.clear();
            }
            this.bitField0_ &= 0xFFFFFEFF;
            return this;
        }
        
        public RemoveBreakpoint_Res.Builder getRemoveBreakpointBuilder() {
            this.bitField0_ |= 0x100;
            this.onChanged();
            return (RemoveBreakpoint_Res.Builder)this.h().getBuilder();
        }
        
        public RemoveBreakpoint_ResOrBuilder getRemoveBreakpointOrBuilder() {
            try {
                if (this.removeBreakpointBuilder_ != null) {
                    return (RemoveBreakpoint_ResOrBuilder)this.removeBreakpointBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.removeBreakpoint_;
        }
        
        private SingleFieldBuilder<RemoveBreakpoint_Res, RemoveBreakpoint_Res.Builder, RemoveBreakpoint_ResOrBuilder> h() {
            try {
                if (this.removeBreakpointBuilder_ == null) {
                    this.removeBreakpointBuilder_ = (SingleFieldBuilder<RemoveBreakpoint_Res, RemoveBreakpoint_Res.Builder, RemoveBreakpoint_ResOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.removeBreakpoint_, this.getParentForChildren(), this.isClean());
                    this.removeBreakpoint_ = null;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.removeBreakpointBuilder_;
        }
        
        public boolean hasStepInto() {
            try {
                if ((this.bitField0_ & 0x200) == 0x200) {
                    return true;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return false;
        }
        
        public StepInto_Res getStepInto() {
            try {
                if (this.stepIntoBuilder_ == null) {
                    return this.stepInto_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (StepInto_Res)this.stepIntoBuilder_.getMessage();
        }
        
        public Builder setStepInto(final StepInto_Res stepInto_Res) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.stepIntoBuilder_ != null) {
                                break Label_0042;
                            }
                            final StepInto_Res stepInto_Res2 = stepInto_Res;
                            if (stepInto_Res2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final StepInto_Res stepInto_Res2 = stepInto_Res;
                            if (stepInto_Res2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.stepInto_ = stepInto_Res;
                    this.onChanged();
                    break Label_0051;
                }
                this.stepIntoBuilder_.setMessage((GeneratedMessage)stepInto_Res);
            }
            this.bitField0_ |= 0x200;
            return this;
        }
        
        public Builder setStepInto(final StepInto_Res.Builder builder) {
            Label_0038: {
                try {
                    if (this.stepIntoBuilder_ == null) {
                        this.stepInto_ = builder.build();
                        this.onChanged();
                        break Label_0038;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.stepIntoBuilder_.setMessage((GeneratedMessage)builder.build());
            }
            this.bitField0_ |= 0x200;
            return this;
        }
        
        public Builder mergeStepInto(final StepInto_Res p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.stepIntoBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       82
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    11: sipush          512
            //    14: iand           
            //    15: sipush          512
            //    18: if_icmpne       70
            //    21: goto            28
            //    24: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    27: athrow         
            //    28: aload_0        
            //    29: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.stepInto_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepInto_Res;
            //    32: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepInto_Res.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepInto_Res;
            //    35: if_acmpeq       70
            //    38: goto            45
            //    41: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    44: athrow         
            //    45: aload_0        
            //    46: aload_0        
            //    47: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.stepInto_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepInto_Res;
            //    50: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepInto_Res.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepInto_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepInto_Res$Builder;
            //    53: aload_1        
            //    54: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepInto_Res$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepInto_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepInto_Res$Builder;
            //    57: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepInto_Res$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepInto_Res;
            //    60: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.stepInto_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepInto_Res;
            //    63: goto            75
            //    66: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    69: athrow         
            //    70: aload_0        
            //    71: aload_1        
            //    72: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.stepInto_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepInto_Res;
            //    75: aload_0        
            //    76: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.onChanged:()V
            //    79: goto            91
            //    82: aload_0        
            //    83: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.stepIntoBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    86: aload_1        
            //    87: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    90: pop            
            //    91: aload_0        
            //    92: dup            
            //    93: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    96: sipush          512
            //    99: ior            
            //   100: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //   103: aload_0        
            //   104: areturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                            
            //  -----  -----  -----  -----  --------------------------------
            //  0      21     24     28     Ljava/lang/NullPointerException;
            //  7      38     41     45     Ljava/lang/NullPointerException;
            //  28     66     66     70     Ljava/lang/NullPointerException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0028:
            //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
            //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        public Builder clearStepInto() {
            Label_0033: {
                try {
                    if (this.stepIntoBuilder_ == null) {
                        this.stepInto_ = StepInto_Res.getDefaultInstance();
                        this.onChanged();
                        break Label_0033;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.stepIntoBuilder_.clear();
            }
            this.bitField0_ &= 0xFFFFFDFF;
            return this;
        }
        
        public StepInto_Res.Builder getStepIntoBuilder() {
            this.bitField0_ |= 0x200;
            this.onChanged();
            return (StepInto_Res.Builder)this.k().getBuilder();
        }
        
        public StepInto_ResOrBuilder getStepIntoOrBuilder() {
            try {
                if (this.stepIntoBuilder_ != null) {
                    return (StepInto_ResOrBuilder)this.stepIntoBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.stepInto_;
        }
        
        private SingleFieldBuilder<StepInto_Res, StepInto_Res.Builder, StepInto_ResOrBuilder> k() {
            try {
                if (this.stepIntoBuilder_ == null) {
                    this.stepIntoBuilder_ = (SingleFieldBuilder<StepInto_Res, StepInto_Res.Builder, StepInto_ResOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.stepInto_, this.getParentForChildren(), this.isClean());
                    this.stepInto_ = null;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.stepIntoBuilder_;
        }
        
        public boolean hasStepOver() {
            try {
                if ((this.bitField0_ & 0x400) == 0x400) {
                    return true;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return false;
        }
        
        public StepOver_Res getStepOver() {
            try {
                if (this.stepOverBuilder_ == null) {
                    return this.stepOver_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (StepOver_Res)this.stepOverBuilder_.getMessage();
        }
        
        public Builder setStepOver(final StepOver_Res stepOver_Res) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.stepOverBuilder_ != null) {
                                break Label_0042;
                            }
                            final StepOver_Res stepOver_Res2 = stepOver_Res;
                            if (stepOver_Res2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final StepOver_Res stepOver_Res2 = stepOver_Res;
                            if (stepOver_Res2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.stepOver_ = stepOver_Res;
                    this.onChanged();
                    break Label_0051;
                }
                this.stepOverBuilder_.setMessage((GeneratedMessage)stepOver_Res);
            }
            this.bitField0_ |= 0x400;
            return this;
        }
        
        public Builder setStepOver(final StepOver_Res.Builder builder) {
            Label_0038: {
                try {
                    if (this.stepOverBuilder_ == null) {
                        this.stepOver_ = builder.build();
                        this.onChanged();
                        break Label_0038;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.stepOverBuilder_.setMessage((GeneratedMessage)builder.build());
            }
            this.bitField0_ |= 0x400;
            return this;
        }
        
        public Builder mergeStepOver(final StepOver_Res p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.stepOverBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       82
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    11: sipush          1024
            //    14: iand           
            //    15: sipush          1024
            //    18: if_icmpne       70
            //    21: goto            28
            //    24: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    27: athrow         
            //    28: aload_0        
            //    29: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.stepOver_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOver_Res;
            //    32: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOver_Res.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOver_Res;
            //    35: if_acmpeq       70
            //    38: goto            45
            //    41: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    44: athrow         
            //    45: aload_0        
            //    46: aload_0        
            //    47: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.stepOver_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOver_Res;
            //    50: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOver_Res.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOver_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOver_Res$Builder;
            //    53: aload_1        
            //    54: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOver_Res$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOver_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOver_Res$Builder;
            //    57: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOver_Res$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOver_Res;
            //    60: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.stepOver_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOver_Res;
            //    63: goto            75
            //    66: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    69: athrow         
            //    70: aload_0        
            //    71: aload_1        
            //    72: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.stepOver_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOver_Res;
            //    75: aload_0        
            //    76: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.onChanged:()V
            //    79: goto            91
            //    82: aload_0        
            //    83: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.stepOverBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    86: aload_1        
            //    87: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    90: pop            
            //    91: aload_0        
            //    92: dup            
            //    93: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    96: sipush          1024
            //    99: ior            
            //   100: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //   103: aload_0        
            //   104: areturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                            
            //  -----  -----  -----  -----  --------------------------------
            //  0      21     24     28     Ljava/lang/NullPointerException;
            //  7      38     41     45     Ljava/lang/NullPointerException;
            //  28     66     66     70     Ljava/lang/NullPointerException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0028:
            //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
            //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        public Builder clearStepOver() {
            Label_0033: {
                try {
                    if (this.stepOverBuilder_ == null) {
                        this.stepOver_ = StepOver_Res.getDefaultInstance();
                        this.onChanged();
                        break Label_0033;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.stepOverBuilder_.clear();
            }
            this.bitField0_ &= 0xFFFFFBFF;
            return this;
        }
        
        public StepOver_Res.Builder getStepOverBuilder() {
            this.bitField0_ |= 0x400;
            this.onChanged();
            return (StepOver_Res.Builder)this.i().getBuilder();
        }
        
        public StepOver_ResOrBuilder getStepOverOrBuilder() {
            try {
                if (this.stepOverBuilder_ != null) {
                    return (StepOver_ResOrBuilder)this.stepOverBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.stepOver_;
        }
        
        private SingleFieldBuilder<StepOver_Res, StepOver_Res.Builder, StepOver_ResOrBuilder> i() {
            try {
                if (this.stepOverBuilder_ == null) {
                    this.stepOverBuilder_ = (SingleFieldBuilder<StepOver_Res, StepOver_Res.Builder, StepOver_ResOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.stepOver_, this.getParentForChildren(), this.isClean());
                    this.stepOver_ = null;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.stepOverBuilder_;
        }
        
        public boolean hasStepOut() {
            try {
                if ((this.bitField0_ & 0x800) == 0x800) {
                    return true;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return false;
        }
        
        public StepOut_Res getStepOut() {
            try {
                if (this.stepOutBuilder_ == null) {
                    return this.stepOut_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (StepOut_Res)this.stepOutBuilder_.getMessage();
        }
        
        public Builder setStepOut(final StepOut_Res stepOut_Res) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.stepOutBuilder_ != null) {
                                break Label_0042;
                            }
                            final StepOut_Res stepOut_Res2 = stepOut_Res;
                            if (stepOut_Res2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final StepOut_Res stepOut_Res2 = stepOut_Res;
                            if (stepOut_Res2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.stepOut_ = stepOut_Res;
                    this.onChanged();
                    break Label_0051;
                }
                this.stepOutBuilder_.setMessage((GeneratedMessage)stepOut_Res);
            }
            this.bitField0_ |= 0x800;
            return this;
        }
        
        public Builder setStepOut(final StepOut_Res.Builder builder) {
            Label_0038: {
                try {
                    if (this.stepOutBuilder_ == null) {
                        this.stepOut_ = builder.build();
                        this.onChanged();
                        break Label_0038;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.stepOutBuilder_.setMessage((GeneratedMessage)builder.build());
            }
            this.bitField0_ |= 0x800;
            return this;
        }
        
        public Builder mergeStepOut(final StepOut_Res p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.stepOutBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       82
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    11: sipush          2048
            //    14: iand           
            //    15: sipush          2048
            //    18: if_icmpne       70
            //    21: goto            28
            //    24: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    27: athrow         
            //    28: aload_0        
            //    29: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.stepOut_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOut_Res;
            //    32: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOut_Res.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOut_Res;
            //    35: if_acmpeq       70
            //    38: goto            45
            //    41: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    44: athrow         
            //    45: aload_0        
            //    46: aload_0        
            //    47: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.stepOut_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOut_Res;
            //    50: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOut_Res.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOut_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOut_Res$Builder;
            //    53: aload_1        
            //    54: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOut_Res$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOut_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOut_Res$Builder;
            //    57: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOut_Res$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOut_Res;
            //    60: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.stepOut_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOut_Res;
            //    63: goto            75
            //    66: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    69: athrow         
            //    70: aload_0        
            //    71: aload_1        
            //    72: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.stepOut_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOut_Res;
            //    75: aload_0        
            //    76: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.onChanged:()V
            //    79: goto            91
            //    82: aload_0        
            //    83: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.stepOutBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    86: aload_1        
            //    87: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    90: pop            
            //    91: aload_0        
            //    92: dup            
            //    93: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    96: sipush          2048
            //    99: ior            
            //   100: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //   103: aload_0        
            //   104: areturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                            
            //  -----  -----  -----  -----  --------------------------------
            //  0      21     24     28     Ljava/lang/NullPointerException;
            //  7      38     41     45     Ljava/lang/NullPointerException;
            //  28     66     66     70     Ljava/lang/NullPointerException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0028:
            //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
            //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        public Builder clearStepOut() {
            Label_0033: {
                try {
                    if (this.stepOutBuilder_ == null) {
                        this.stepOut_ = StepOut_Res.getDefaultInstance();
                        this.onChanged();
                        break Label_0033;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.stepOutBuilder_.clear();
            }
            this.bitField0_ &= 0xFFFFF7FF;
            return this;
        }
        
        public StepOut_Res.Builder getStepOutBuilder() {
            this.bitField0_ |= 0x800;
            this.onChanged();
            return (StepOut_Res.Builder)this.F().getBuilder();
        }
        
        public StepOut_ResOrBuilder getStepOutOrBuilder() {
            try {
                if (this.stepOutBuilder_ != null) {
                    return (StepOut_ResOrBuilder)this.stepOutBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.stepOut_;
        }
        
        private SingleFieldBuilder<StepOut_Res, StepOut_Res.Builder, StepOut_ResOrBuilder> F() {
            try {
                if (this.stepOutBuilder_ == null) {
                    this.stepOutBuilder_ = (SingleFieldBuilder<StepOut_Res, StepOut_Res.Builder, StepOut_ResOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.stepOut_, this.getParentForChildren(), this.isClean());
                    this.stepOut_ = null;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.stepOutBuilder_;
        }
        
        public boolean hasEvaluateExpression() {
            try {
                if ((this.bitField0_ & 0x1000) == 0x1000) {
                    return true;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return false;
        }
        
        public EvaluateExpression_Res getEvaluateExpression() {
            try {
                if (this.evaluateExpressionBuilder_ == null) {
                    return this.evaluateExpression_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (EvaluateExpression_Res)this.evaluateExpressionBuilder_.getMessage();
        }
        
        public Builder setEvaluateExpression(final EvaluateExpression_Res evaluateExpression_Res) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.evaluateExpressionBuilder_ != null) {
                                break Label_0042;
                            }
                            final EvaluateExpression_Res evaluateExpression_Res2 = evaluateExpression_Res;
                            if (evaluateExpression_Res2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final EvaluateExpression_Res evaluateExpression_Res2 = evaluateExpression_Res;
                            if (evaluateExpression_Res2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.evaluateExpression_ = evaluateExpression_Res;
                    this.onChanged();
                    break Label_0051;
                }
                this.evaluateExpressionBuilder_.setMessage((GeneratedMessage)evaluateExpression_Res);
            }
            this.bitField0_ |= 0x1000;
            return this;
        }
        
        public Builder setEvaluateExpression(final EvaluateExpression_Res.Builder builder) {
            Label_0038: {
                try {
                    if (this.evaluateExpressionBuilder_ == null) {
                        this.evaluateExpression_ = builder.build();
                        this.onChanged();
                        break Label_0038;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.evaluateExpressionBuilder_.setMessage((GeneratedMessage)builder.build());
            }
            this.bitField0_ |= 0x1000;
            return this;
        }
        
        public Builder mergeEvaluateExpression(final EvaluateExpression_Res p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.evaluateExpressionBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       82
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    11: sipush          4096
            //    14: iand           
            //    15: sipush          4096
            //    18: if_icmpne       70
            //    21: goto            28
            //    24: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    27: athrow         
            //    28: aload_0        
            //    29: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.evaluateExpression_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Res;
            //    32: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Res.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Res;
            //    35: if_acmpeq       70
            //    38: goto            45
            //    41: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    44: athrow         
            //    45: aload_0        
            //    46: aload_0        
            //    47: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.evaluateExpression_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Res;
            //    50: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Res.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Res$Builder;
            //    53: aload_1        
            //    54: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Res$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Res$Builder;
            //    57: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Res$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Res;
            //    60: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.evaluateExpression_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Res;
            //    63: goto            75
            //    66: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    69: athrow         
            //    70: aload_0        
            //    71: aload_1        
            //    72: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.evaluateExpression_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Res;
            //    75: aload_0        
            //    76: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.onChanged:()V
            //    79: goto            91
            //    82: aload_0        
            //    83: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.evaluateExpressionBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    86: aload_1        
            //    87: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    90: pop            
            //    91: aload_0        
            //    92: dup            
            //    93: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    96: sipush          4096
            //    99: ior            
            //   100: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //   103: aload_0        
            //   104: areturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                            
            //  -----  -----  -----  -----  --------------------------------
            //  0      21     24     28     Ljava/lang/NullPointerException;
            //  7      38     41     45     Ljava/lang/NullPointerException;
            //  28     66     66     70     Ljava/lang/NullPointerException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0028:
            //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
            //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        public Builder clearEvaluateExpression() {
            Label_0033: {
                try {
                    if (this.evaluateExpressionBuilder_ == null) {
                        this.evaluateExpression_ = EvaluateExpression_Res.getDefaultInstance();
                        this.onChanged();
                        break Label_0033;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.evaluateExpressionBuilder_.clear();
            }
            this.bitField0_ &= 0xFFFFEFFF;
            return this;
        }
        
        public EvaluateExpression_Res.Builder getEvaluateExpressionBuilder() {
            this.bitField0_ |= 0x1000;
            this.onChanged();
            return (EvaluateExpression_Res.Builder)this.e().getBuilder();
        }
        
        public EvaluateExpression_ResOrBuilder getEvaluateExpressionOrBuilder() {
            try {
                if (this.evaluateExpressionBuilder_ != null) {
                    return (EvaluateExpression_ResOrBuilder)this.evaluateExpressionBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.evaluateExpression_;
        }
        
        private SingleFieldBuilder<EvaluateExpression_Res, EvaluateExpression_Res.Builder, EvaluateExpression_ResOrBuilder> e() {
            try {
                if (this.evaluateExpressionBuilder_ == null) {
                    this.evaluateExpressionBuilder_ = (SingleFieldBuilder<EvaluateExpression_Res, EvaluateExpression_Res.Builder, EvaluateExpression_ResOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.evaluateExpression_, this.getParentForChildren(), this.isClean());
                    this.evaluateExpression_ = null;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.evaluateExpressionBuilder_;
        }
        
        public boolean hasGetValueChildren() {
            try {
                if ((this.bitField0_ & 0x2000) == 0x2000) {
                    return true;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return false;
        }
        
        public GetValueChildren_Res getGetValueChildren() {
            try {
                if (this.getValueChildrenBuilder_ == null) {
                    return this.getValueChildren_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (GetValueChildren_Res)this.getValueChildrenBuilder_.getMessage();
        }
        
        public Builder setGetValueChildren(final GetValueChildren_Res getValueChildren_Res) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.getValueChildrenBuilder_ != null) {
                                break Label_0042;
                            }
                            final GetValueChildren_Res getValueChildren_Res2 = getValueChildren_Res;
                            if (getValueChildren_Res2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final GetValueChildren_Res getValueChildren_Res2 = getValueChildren_Res;
                            if (getValueChildren_Res2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.getValueChildren_ = getValueChildren_Res;
                    this.onChanged();
                    break Label_0051;
                }
                this.getValueChildrenBuilder_.setMessage((GeneratedMessage)getValueChildren_Res);
            }
            this.bitField0_ |= 0x2000;
            return this;
        }
        
        public Builder setGetValueChildren(final GetValueChildren_Res.Builder builder) {
            Label_0038: {
                try {
                    if (this.getValueChildrenBuilder_ == null) {
                        this.getValueChildren_ = builder.build();
                        this.onChanged();
                        break Label_0038;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.getValueChildrenBuilder_.setMessage((GeneratedMessage)builder.build());
            }
            this.bitField0_ |= 0x2000;
            return this;
        }
        
        public Builder mergeGetValueChildren(final GetValueChildren_Res p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getValueChildrenBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       82
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    11: sipush          8192
            //    14: iand           
            //    15: sipush          8192
            //    18: if_icmpne       70
            //    21: goto            28
            //    24: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    27: athrow         
            //    28: aload_0        
            //    29: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getValueChildren_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res;
            //    32: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res;
            //    35: if_acmpeq       70
            //    38: goto            45
            //    41: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    44: athrow         
            //    45: aload_0        
            //    46: aload_0        
            //    47: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getValueChildren_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res;
            //    50: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res$Builder;
            //    53: aload_1        
            //    54: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res$Builder;
            //    57: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res;
            //    60: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getValueChildren_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res;
            //    63: goto            75
            //    66: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    69: athrow         
            //    70: aload_0        
            //    71: aload_1        
            //    72: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getValueChildren_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Res;
            //    75: aload_0        
            //    76: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.onChanged:()V
            //    79: goto            91
            //    82: aload_0        
            //    83: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getValueChildrenBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    86: aload_1        
            //    87: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    90: pop            
            //    91: aload_0        
            //    92: dup            
            //    93: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    96: sipush          8192
            //    99: ior            
            //   100: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //   103: aload_0        
            //   104: areturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                            
            //  -----  -----  -----  -----  --------------------------------
            //  0      21     24     28     Ljava/lang/NullPointerException;
            //  7      38     41     45     Ljava/lang/NullPointerException;
            //  28     66     66     70     Ljava/lang/NullPointerException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0028:
            //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
            //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        public Builder clearGetValueChildren() {
            Label_0033: {
                try {
                    if (this.getValueChildrenBuilder_ == null) {
                        this.getValueChildren_ = GetValueChildren_Res.getDefaultInstance();
                        this.onChanged();
                        break Label_0033;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.getValueChildrenBuilder_.clear();
            }
            this.bitField0_ &= 0xFFFFDFFF;
            return this;
        }
        
        public GetValueChildren_Res.Builder getGetValueChildrenBuilder() {
            this.bitField0_ |= 0x2000;
            this.onChanged();
            return (GetValueChildren_Res.Builder)this.q().getBuilder();
        }
        
        public GetValueChildren_ResOrBuilder getGetValueChildrenOrBuilder() {
            try {
                if (this.getValueChildrenBuilder_ != null) {
                    return (GetValueChildren_ResOrBuilder)this.getValueChildrenBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.getValueChildren_;
        }
        
        private SingleFieldBuilder<GetValueChildren_Res, GetValueChildren_Res.Builder, GetValueChildren_ResOrBuilder> q() {
            try {
                if (this.getValueChildrenBuilder_ == null) {
                    this.getValueChildrenBuilder_ = (SingleFieldBuilder<GetValueChildren_Res, GetValueChildren_Res.Builder, GetValueChildren_ResOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.getValueChildren_, this.getParentForChildren(), this.isClean());
                    this.getValueChildren_ = null;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.getValueChildrenBuilder_;
        }
        
        public boolean hasGetVars() {
            try {
                if ((this.bitField0_ & 0x4000) == 0x4000) {
                    return true;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return false;
        }
        
        public GetVars_Res getGetVars() {
            try {
                if (this.getVarsBuilder_ == null) {
                    return this.getVars_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (GetVars_Res)this.getVarsBuilder_.getMessage();
        }
        
        public Builder setGetVars(final GetVars_Res getVars_Res) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.getVarsBuilder_ != null) {
                                break Label_0042;
                            }
                            final GetVars_Res getVars_Res2 = getVars_Res;
                            if (getVars_Res2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final GetVars_Res getVars_Res2 = getVars_Res;
                            if (getVars_Res2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.getVars_ = getVars_Res;
                    this.onChanged();
                    break Label_0051;
                }
                this.getVarsBuilder_.setMessage((GeneratedMessage)getVars_Res);
            }
            this.bitField0_ |= 0x4000;
            return this;
        }
        
        public Builder setGetVars(final GetVars_Res.Builder builder) {
            Label_0038: {
                try {
                    if (this.getVarsBuilder_ == null) {
                        this.getVars_ = builder.build();
                        this.onChanged();
                        break Label_0038;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.getVarsBuilder_.setMessage((GeneratedMessage)builder.build());
            }
            this.bitField0_ |= 0x4000;
            return this;
        }
        
        public Builder mergeGetVars(final GetVars_Res p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getVarsBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       82
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    11: sipush          16384
            //    14: iand           
            //    15: sipush          16384
            //    18: if_icmpne       70
            //    21: goto            28
            //    24: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    27: athrow         
            //    28: aload_0        
            //    29: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getVars_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetVars_Res;
            //    32: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetVars_Res.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetVars_Res;
            //    35: if_acmpeq       70
            //    38: goto            45
            //    41: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    44: athrow         
            //    45: aload_0        
            //    46: aload_0        
            //    47: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getVars_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetVars_Res;
            //    50: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetVars_Res.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetVars_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetVars_Res$Builder;
            //    53: aload_1        
            //    54: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetVars_Res$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetVars_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetVars_Res$Builder;
            //    57: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetVars_Res$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetVars_Res;
            //    60: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getVars_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetVars_Res;
            //    63: goto            75
            //    66: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    69: athrow         
            //    70: aload_0        
            //    71: aload_1        
            //    72: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getVars_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetVars_Res;
            //    75: aload_0        
            //    76: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.onChanged:()V
            //    79: goto            91
            //    82: aload_0        
            //    83: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getVarsBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    86: aload_1        
            //    87: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    90: pop            
            //    91: aload_0        
            //    92: dup            
            //    93: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    96: sipush          16384
            //    99: ior            
            //   100: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //   103: aload_0        
            //   104: areturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                            
            //  -----  -----  -----  -----  --------------------------------
            //  0      21     24     28     Ljava/lang/NullPointerException;
            //  7      38     41     45     Ljava/lang/NullPointerException;
            //  28     66     66     70     Ljava/lang/NullPointerException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0028:
            //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
            //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        public Builder clearGetVars() {
            Label_0033: {
                try {
                    if (this.getVarsBuilder_ == null) {
                        this.getVars_ = GetVars_Res.getDefaultInstance();
                        this.onChanged();
                        break Label_0033;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.getVarsBuilder_.clear();
            }
            this.bitField0_ &= 0xFFFFBFFF;
            return this;
        }
        
        public GetVars_Res.Builder getGetVarsBuilder() {
            this.bitField0_ |= 0x4000;
            this.onChanged();
            return (GetVars_Res.Builder)this.f().getBuilder();
        }
        
        public GetVars_ResOrBuilder getGetVarsOrBuilder() {
            try {
                if (this.getVarsBuilder_ != null) {
                    return (GetVars_ResOrBuilder)this.getVarsBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.getVars_;
        }
        
        private SingleFieldBuilder<GetVars_Res, GetVars_Res.Builder, GetVars_ResOrBuilder> f() {
            try {
                if (this.getVarsBuilder_ == null) {
                    this.getVarsBuilder_ = (SingleFieldBuilder<GetVars_Res, GetVars_Res.Builder, GetVars_ResOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.getVars_, this.getParentForChildren(), this.isClean());
                    this.getVars_ = null;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.getVarsBuilder_;
        }
        
        public boolean hasHandleConsoleCommand() {
            try {
                if ((this.bitField0_ & 0x8000) == 0x8000) {
                    return true;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return false;
        }
        
        public HandleConsoleCommand_Res getHandleConsoleCommand() {
            try {
                if (this.handleConsoleCommandBuilder_ == null) {
                    return this.handleConsoleCommand_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (HandleConsoleCommand_Res)this.handleConsoleCommandBuilder_.getMessage();
        }
        
        public Builder setHandleConsoleCommand(final HandleConsoleCommand_Res handleConsoleCommand_Res) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.handleConsoleCommandBuilder_ != null) {
                                break Label_0042;
                            }
                            final HandleConsoleCommand_Res handleConsoleCommand_Res2 = handleConsoleCommand_Res;
                            if (handleConsoleCommand_Res2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final HandleConsoleCommand_Res handleConsoleCommand_Res2 = handleConsoleCommand_Res;
                            if (handleConsoleCommand_Res2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.handleConsoleCommand_ = handleConsoleCommand_Res;
                    this.onChanged();
                    break Label_0051;
                }
                this.handleConsoleCommandBuilder_.setMessage((GeneratedMessage)handleConsoleCommand_Res);
            }
            this.bitField0_ |= 0x8000;
            return this;
        }
        
        public Builder setHandleConsoleCommand(final HandleConsoleCommand_Res.Builder builder) {
            Label_0038: {
                try {
                    if (this.handleConsoleCommandBuilder_ == null) {
                        this.handleConsoleCommand_ = builder.build();
                        this.onChanged();
                        break Label_0038;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.handleConsoleCommandBuilder_.setMessage((GeneratedMessage)builder.build());
            }
            this.bitField0_ |= 0x8000;
            return this;
        }
        
        public Builder mergeHandleConsoleCommand(final HandleConsoleCommand_Res p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.handleConsoleCommandBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       80
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    11: ldc             32768
            //    13: iand           
            //    14: ldc             32768
            //    16: if_icmpne       68
            //    19: goto            26
            //    22: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    25: athrow         
            //    26: aload_0        
            //    27: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.handleConsoleCommand_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleConsoleCommand_Res;
            //    30: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleConsoleCommand_Res.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleConsoleCommand_Res;
            //    33: if_acmpeq       68
            //    36: goto            43
            //    39: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    42: athrow         
            //    43: aload_0        
            //    44: aload_0        
            //    45: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.handleConsoleCommand_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleConsoleCommand_Res;
            //    48: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleConsoleCommand_Res.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleConsoleCommand_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleConsoleCommand_Res$Builder;
            //    51: aload_1        
            //    52: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleConsoleCommand_Res$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleConsoleCommand_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleConsoleCommand_Res$Builder;
            //    55: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleConsoleCommand_Res$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleConsoleCommand_Res;
            //    58: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.handleConsoleCommand_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleConsoleCommand_Res;
            //    61: goto            73
            //    64: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    67: athrow         
            //    68: aload_0        
            //    69: aload_1        
            //    70: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.handleConsoleCommand_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleConsoleCommand_Res;
            //    73: aload_0        
            //    74: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.onChanged:()V
            //    77: goto            89
            //    80: aload_0        
            //    81: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.handleConsoleCommandBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    84: aload_1        
            //    85: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    88: pop            
            //    89: aload_0        
            //    90: dup            
            //    91: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    94: ldc             32768
            //    96: ior            
            //    97: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //   100: aload_0        
            //   101: areturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                            
            //  -----  -----  -----  -----  --------------------------------
            //  0      19     22     26     Ljava/lang/NullPointerException;
            //  7      36     39     43     Ljava/lang/NullPointerException;
            //  26     64     64     68     Ljava/lang/NullPointerException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0026:
            //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
            //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        public Builder clearHandleConsoleCommand() {
            Label_0033: {
                try {
                    if (this.handleConsoleCommandBuilder_ == null) {
                        this.handleConsoleCommand_ = HandleConsoleCommand_Res.getDefaultInstance();
                        this.onChanged();
                        break Label_0033;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.handleConsoleCommandBuilder_.clear();
            }
            this.bitField0_ &= 0xFFFF7FFF;
            return this;
        }
        
        public HandleConsoleCommand_Res.Builder getHandleConsoleCommandBuilder() {
            this.bitField0_ |= 0x8000;
            this.onChanged();
            return (HandleConsoleCommand_Res.Builder)this.H().getBuilder();
        }
        
        public HandleConsoleCommand_ResOrBuilder getHandleConsoleCommandOrBuilder() {
            try {
                if (this.handleConsoleCommandBuilder_ != null) {
                    return (HandleConsoleCommand_ResOrBuilder)this.handleConsoleCommandBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.handleConsoleCommand_;
        }
        
        private SingleFieldBuilder<HandleConsoleCommand_Res, HandleConsoleCommand_Res.Builder, HandleConsoleCommand_ResOrBuilder> H() {
            try {
                if (this.handleConsoleCommandBuilder_ == null) {
                    this.handleConsoleCommandBuilder_ = (SingleFieldBuilder<HandleConsoleCommand_Res, HandleConsoleCommand_Res.Builder, HandleConsoleCommand_ResOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.handleConsoleCommand_, this.getParentForChildren(), this.isClean());
                    this.handleConsoleCommand_ = null;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.handleConsoleCommandBuilder_;
        }
        
        public boolean hasHandleCompletion() {
            try {
                if ((this.bitField0_ & 0x10000) == 0x10000) {
                    return true;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return false;
        }
        
        public HandleCompletion_Res getHandleCompletion() {
            try {
                if (this.handleCompletionBuilder_ == null) {
                    return this.handleCompletion_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (HandleCompletion_Res)this.handleCompletionBuilder_.getMessage();
        }
        
        public Builder setHandleCompletion(final HandleCompletion_Res handleCompletion_Res) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.handleCompletionBuilder_ != null) {
                                break Label_0042;
                            }
                            final HandleCompletion_Res handleCompletion_Res2 = handleCompletion_Res;
                            if (handleCompletion_Res2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final HandleCompletion_Res handleCompletion_Res2 = handleCompletion_Res;
                            if (handleCompletion_Res2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.handleCompletion_ = handleCompletion_Res;
                    this.onChanged();
                    break Label_0051;
                }
                this.handleCompletionBuilder_.setMessage((GeneratedMessage)handleCompletion_Res);
            }
            this.bitField0_ |= 0x10000;
            return this;
        }
        
        public Builder setHandleCompletion(final HandleCompletion_Res.Builder builder) {
            Label_0038: {
                try {
                    if (this.handleCompletionBuilder_ == null) {
                        this.handleCompletion_ = builder.build();
                        this.onChanged();
                        break Label_0038;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.handleCompletionBuilder_.setMessage((GeneratedMessage)builder.build());
            }
            this.bitField0_ |= 0x10000;
            return this;
        }
        
        public Builder mergeHandleCompletion(final HandleCompletion_Res p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.handleCompletionBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       80
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    11: ldc             65536
            //    13: iand           
            //    14: ldc             65536
            //    16: if_icmpne       68
            //    19: goto            26
            //    22: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    25: athrow         
            //    26: aload_0        
            //    27: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.handleCompletion_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleCompletion_Res;
            //    30: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleCompletion_Res.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleCompletion_Res;
            //    33: if_acmpeq       68
            //    36: goto            43
            //    39: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    42: athrow         
            //    43: aload_0        
            //    44: aload_0        
            //    45: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.handleCompletion_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleCompletion_Res;
            //    48: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleCompletion_Res.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleCompletion_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleCompletion_Res$Builder;
            //    51: aload_1        
            //    52: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleCompletion_Res$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleCompletion_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleCompletion_Res$Builder;
            //    55: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleCompletion_Res$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleCompletion_Res;
            //    58: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.handleCompletion_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleCompletion_Res;
            //    61: goto            73
            //    64: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    67: athrow         
            //    68: aload_0        
            //    69: aload_1        
            //    70: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.handleCompletion_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleCompletion_Res;
            //    73: aload_0        
            //    74: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.onChanged:()V
            //    77: goto            89
            //    80: aload_0        
            //    81: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.handleCompletionBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    84: aload_1        
            //    85: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    88: pop            
            //    89: aload_0        
            //    90: dup            
            //    91: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    94: ldc             65536
            //    96: ior            
            //    97: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //   100: aload_0        
            //   101: areturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                            
            //  -----  -----  -----  -----  --------------------------------
            //  0      19     22     26     Ljava/lang/NullPointerException;
            //  7      36     39     43     Ljava/lang/NullPointerException;
            //  26     64     64     68     Ljava/lang/NullPointerException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0026:
            //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
            //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        public Builder clearHandleCompletion() {
            Label_0033: {
                try {
                    if (this.handleCompletionBuilder_ == null) {
                        this.handleCompletion_ = HandleCompletion_Res.getDefaultInstance();
                        this.onChanged();
                        break Label_0033;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.handleCompletionBuilder_.clear();
            }
            this.bitField0_ &= 0xFFFEFFFF;
            return this;
        }
        
        public HandleCompletion_Res.Builder getHandleCompletionBuilder() {
            this.bitField0_ |= 0x10000;
            this.onChanged();
            return (HandleCompletion_Res.Builder)this.A().getBuilder();
        }
        
        public HandleCompletion_ResOrBuilder getHandleCompletionOrBuilder() {
            try {
                if (this.handleCompletionBuilder_ != null) {
                    return (HandleCompletion_ResOrBuilder)this.handleCompletionBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.handleCompletion_;
        }
        
        private SingleFieldBuilder<HandleCompletion_Res, HandleCompletion_Res.Builder, HandleCompletion_ResOrBuilder> A() {
            try {
                if (this.handleCompletionBuilder_ == null) {
                    this.handleCompletionBuilder_ = (SingleFieldBuilder<HandleCompletion_Res, HandleCompletion_Res.Builder, HandleCompletion_ResOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.handleCompletion_, this.getParentForChildren(), this.isClean());
                    this.handleCompletion_ = null;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.handleCompletionBuilder_;
        }
        
        public boolean hasAttach() {
            try {
                if ((this.bitField0_ & 0x20000) == 0x20000) {
                    return true;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return false;
        }
        
        public Attach_Res getAttach() {
            try {
                if (this.attachBuilder_ == null) {
                    return this.attach_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (Attach_Res)this.attachBuilder_.getMessage();
        }
        
        public Builder setAttach(final Attach_Res attach_Res) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.attachBuilder_ != null) {
                                break Label_0042;
                            }
                            final Attach_Res attach_Res2 = attach_Res;
                            if (attach_Res2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final Attach_Res attach_Res2 = attach_Res;
                            if (attach_Res2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.attach_ = attach_Res;
                    this.onChanged();
                    break Label_0051;
                }
                this.attachBuilder_.setMessage((GeneratedMessage)attach_Res);
            }
            this.bitField0_ |= 0x20000;
            return this;
        }
        
        public Builder setAttach(final Attach_Res.Builder builder) {
            Label_0038: {
                try {
                    if (this.attachBuilder_ == null) {
                        this.attach_ = builder.build();
                        this.onChanged();
                        break Label_0038;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.attachBuilder_.setMessage((GeneratedMessage)builder.build());
            }
            this.bitField0_ |= 0x20000;
            return this;
        }
        
        public Builder mergeAttach(final Attach_Res p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.attachBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       80
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    11: ldc             131072
            //    13: iand           
            //    14: ldc             131072
            //    16: if_icmpne       68
            //    19: goto            26
            //    22: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    25: athrow         
            //    26: aload_0        
            //    27: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.attach_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Attach_Res;
            //    30: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Attach_Res.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Attach_Res;
            //    33: if_acmpeq       68
            //    36: goto            43
            //    39: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    42: athrow         
            //    43: aload_0        
            //    44: aload_0        
            //    45: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.attach_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Attach_Res;
            //    48: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Attach_Res.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Attach_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Attach_Res$Builder;
            //    51: aload_1        
            //    52: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Attach_Res$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Attach_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Attach_Res$Builder;
            //    55: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Attach_Res$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Attach_Res;
            //    58: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.attach_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Attach_Res;
            //    61: goto            73
            //    64: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    67: athrow         
            //    68: aload_0        
            //    69: aload_1        
            //    70: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.attach_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Attach_Res;
            //    73: aload_0        
            //    74: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.onChanged:()V
            //    77: goto            89
            //    80: aload_0        
            //    81: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.attachBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    84: aload_1        
            //    85: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    88: pop            
            //    89: aload_0        
            //    90: dup            
            //    91: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    94: ldc             131072
            //    96: ior            
            //    97: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //   100: aload_0        
            //   101: areturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                            
            //  -----  -----  -----  -----  --------------------------------
            //  0      19     22     26     Ljava/lang/NullPointerException;
            //  7      36     39     43     Ljava/lang/NullPointerException;
            //  26     64     64     68     Ljava/lang/NullPointerException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0026:
            //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
            //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        public Builder clearAttach() {
            Label_0033: {
                try {
                    if (this.attachBuilder_ == null) {
                        this.attach_ = Attach_Res.getDefaultInstance();
                        this.onChanged();
                        break Label_0033;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.attachBuilder_.clear();
            }
            this.bitField0_ &= 0xFFFDFFFF;
            return this;
        }
        
        public Attach_Res.Builder getAttachBuilder() {
            this.bitField0_ |= 0x20000;
            this.onChanged();
            return (Attach_Res.Builder)this.G().getBuilder();
        }
        
        public Attach_ResOrBuilder getAttachOrBuilder() {
            try {
                if (this.attachBuilder_ != null) {
                    return (Attach_ResOrBuilder)this.attachBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.attach_;
        }
        
        private SingleFieldBuilder<Attach_Res, Attach_Res.Builder, Attach_ResOrBuilder> G() {
            try {
                if (this.attachBuilder_ == null) {
                    this.attachBuilder_ = (SingleFieldBuilder<Attach_Res, Attach_Res.Builder, Attach_ResOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.attach_, this.getParentForChildren(), this.isClean());
                    this.attach_ = null;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.attachBuilder_;
        }
        
        public boolean hasAttachByName() {
            try {
                if ((this.bitField0_ & 0x40000) == 0x40000) {
                    return true;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return false;
        }
        
        public AttachByName_Res getAttachByName() {
            try {
                if (this.attachByNameBuilder_ == null) {
                    return this.attachByName_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (AttachByName_Res)this.attachByNameBuilder_.getMessage();
        }
        
        public Builder setAttachByName(final AttachByName_Res attachByName_Res) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.attachByNameBuilder_ != null) {
                                break Label_0042;
                            }
                            final AttachByName_Res attachByName_Res2 = attachByName_Res;
                            if (attachByName_Res2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final AttachByName_Res attachByName_Res2 = attachByName_Res;
                            if (attachByName_Res2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.attachByName_ = attachByName_Res;
                    this.onChanged();
                    break Label_0051;
                }
                this.attachByNameBuilder_.setMessage((GeneratedMessage)attachByName_Res);
            }
            this.bitField0_ |= 0x40000;
            return this;
        }
        
        public Builder setAttachByName(final AttachByName_Res.Builder builder) {
            Label_0038: {
                try {
                    if (this.attachByNameBuilder_ == null) {
                        this.attachByName_ = builder.build();
                        this.onChanged();
                        break Label_0038;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.attachByNameBuilder_.setMessage((GeneratedMessage)builder.build());
            }
            this.bitField0_ |= 0x40000;
            return this;
        }
        
        public Builder mergeAttachByName(final AttachByName_Res p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.attachByNameBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       80
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    11: ldc             262144
            //    13: iand           
            //    14: ldc             262144
            //    16: if_icmpne       68
            //    19: goto            26
            //    22: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    25: athrow         
            //    26: aload_0        
            //    27: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.attachByName_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AttachByName_Res;
            //    30: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AttachByName_Res.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AttachByName_Res;
            //    33: if_acmpeq       68
            //    36: goto            43
            //    39: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    42: athrow         
            //    43: aload_0        
            //    44: aload_0        
            //    45: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.attachByName_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AttachByName_Res;
            //    48: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AttachByName_Res.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AttachByName_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AttachByName_Res$Builder;
            //    51: aload_1        
            //    52: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AttachByName_Res$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AttachByName_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AttachByName_Res$Builder;
            //    55: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AttachByName_Res$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AttachByName_Res;
            //    58: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.attachByName_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AttachByName_Res;
            //    61: goto            73
            //    64: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    67: athrow         
            //    68: aload_0        
            //    69: aload_1        
            //    70: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.attachByName_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AttachByName_Res;
            //    73: aload_0        
            //    74: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.onChanged:()V
            //    77: goto            89
            //    80: aload_0        
            //    81: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.attachByNameBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    84: aload_1        
            //    85: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    88: pop            
            //    89: aload_0        
            //    90: dup            
            //    91: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    94: ldc             262144
            //    96: ior            
            //    97: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //   100: aload_0        
            //   101: areturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                            
            //  -----  -----  -----  -----  --------------------------------
            //  0      19     22     26     Ljava/lang/NullPointerException;
            //  7      36     39     43     Ljava/lang/NullPointerException;
            //  26     64     64     68     Ljava/lang/NullPointerException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0026:
            //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
            //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        public Builder clearAttachByName() {
            Label_0033: {
                try {
                    if (this.attachByNameBuilder_ == null) {
                        this.attachByName_ = AttachByName_Res.getDefaultInstance();
                        this.onChanged();
                        break Label_0033;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.attachByNameBuilder_.clear();
            }
            this.bitField0_ &= 0xFFFBFFFF;
            return this;
        }
        
        public AttachByName_Res.Builder getAttachByNameBuilder() {
            this.bitField0_ |= 0x40000;
            this.onChanged();
            return (AttachByName_Res.Builder)this.m().getBuilder();
        }
        
        public AttachByName_ResOrBuilder getAttachByNameOrBuilder() {
            try {
                if (this.attachByNameBuilder_ != null) {
                    return (AttachByName_ResOrBuilder)this.attachByNameBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.attachByName_;
        }
        
        private SingleFieldBuilder<AttachByName_Res, AttachByName_Res.Builder, AttachByName_ResOrBuilder> m() {
            try {
                if (this.attachByNameBuilder_ == null) {
                    this.attachByNameBuilder_ = (SingleFieldBuilder<AttachByName_Res, AttachByName_Res.Builder, AttachByName_ResOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.attachByName_, this.getParentForChildren(), this.isClean());
                    this.attachByName_ = null;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.attachByNameBuilder_;
        }
        
        public boolean hasDispatchInput() {
            try {
                if ((this.bitField0_ & 0x80000) == 0x80000) {
                    return true;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return false;
        }
        
        public DispatchInput_Res getDispatchInput() {
            try {
                if (this.dispatchInputBuilder_ == null) {
                    return this.dispatchInput_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (DispatchInput_Res)this.dispatchInputBuilder_.getMessage();
        }
        
        public Builder setDispatchInput(final DispatchInput_Res dispatchInput_Res) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.dispatchInputBuilder_ != null) {
                                break Label_0042;
                            }
                            final DispatchInput_Res dispatchInput_Res2 = dispatchInput_Res;
                            if (dispatchInput_Res2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final DispatchInput_Res dispatchInput_Res2 = dispatchInput_Res;
                            if (dispatchInput_Res2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.dispatchInput_ = dispatchInput_Res;
                    this.onChanged();
                    break Label_0051;
                }
                this.dispatchInputBuilder_.setMessage((GeneratedMessage)dispatchInput_Res);
            }
            this.bitField0_ |= 0x80000;
            return this;
        }
        
        public Builder setDispatchInput(final DispatchInput_Res.Builder builder) {
            Label_0038: {
                try {
                    if (this.dispatchInputBuilder_ == null) {
                        this.dispatchInput_ = builder.build();
                        this.onChanged();
                        break Label_0038;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.dispatchInputBuilder_.setMessage((GeneratedMessage)builder.build());
            }
            this.bitField0_ |= 0x80000;
            return this;
        }
        
        public Builder mergeDispatchInput(final DispatchInput_Res p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.dispatchInputBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       80
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    11: ldc             524288
            //    13: iand           
            //    14: ldc             524288
            //    16: if_icmpne       68
            //    19: goto            26
            //    22: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    25: athrow         
            //    26: aload_0        
            //    27: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.dispatchInput_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$DispatchInput_Res;
            //    30: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$DispatchInput_Res.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$DispatchInput_Res;
            //    33: if_acmpeq       68
            //    36: goto            43
            //    39: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    42: athrow         
            //    43: aload_0        
            //    44: aload_0        
            //    45: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.dispatchInput_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$DispatchInput_Res;
            //    48: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$DispatchInput_Res.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$DispatchInput_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$DispatchInput_Res$Builder;
            //    51: aload_1        
            //    52: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$DispatchInput_Res$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$DispatchInput_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$DispatchInput_Res$Builder;
            //    55: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$DispatchInput_Res$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$DispatchInput_Res;
            //    58: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.dispatchInput_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$DispatchInput_Res;
            //    61: goto            73
            //    64: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    67: athrow         
            //    68: aload_0        
            //    69: aload_1        
            //    70: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.dispatchInput_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$DispatchInput_Res;
            //    73: aload_0        
            //    74: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.onChanged:()V
            //    77: goto            89
            //    80: aload_0        
            //    81: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.dispatchInputBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    84: aload_1        
            //    85: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    88: pop            
            //    89: aload_0        
            //    90: dup            
            //    91: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    94: ldc             524288
            //    96: ior            
            //    97: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //   100: aload_0        
            //   101: areturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                            
            //  -----  -----  -----  -----  --------------------------------
            //  0      19     22     26     Ljava/lang/NullPointerException;
            //  7      36     39     43     Ljava/lang/NullPointerException;
            //  26     64     64     68     Ljava/lang/NullPointerException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0026:
            //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
            //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        public Builder clearDispatchInput() {
            Label_0033: {
                try {
                    if (this.dispatchInputBuilder_ == null) {
                        this.dispatchInput_ = DispatchInput_Res.getDefaultInstance();
                        this.onChanged();
                        break Label_0033;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.dispatchInputBuilder_.clear();
            }
            this.bitField0_ &= 0xFFF7FFFF;
            return this;
        }
        
        public DispatchInput_Res.Builder getDispatchInputBuilder() {
            this.bitField0_ |= 0x80000;
            this.onChanged();
            return (DispatchInput_Res.Builder)this.n().getBuilder();
        }
        
        public DispatchInput_ResOrBuilder getDispatchInputOrBuilder() {
            try {
                if (this.dispatchInputBuilder_ != null) {
                    return (DispatchInput_ResOrBuilder)this.dispatchInputBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.dispatchInput_;
        }
        
        private SingleFieldBuilder<DispatchInput_Res, DispatchInput_Res.Builder, DispatchInput_ResOrBuilder> n() {
            try {
                if (this.dispatchInputBuilder_ == null) {
                    this.dispatchInputBuilder_ = (SingleFieldBuilder<DispatchInput_Res, DispatchInput_Res.Builder, DispatchInput_ResOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.dispatchInput_, this.getParentForChildren(), this.isClean());
                    this.dispatchInput_ = null;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.dispatchInputBuilder_;
        }
        
        public boolean hasAddWatchpoint() {
            try {
                if ((this.bitField0_ & 0x100000) == 0x100000) {
                    return true;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return false;
        }
        
        public AddWatchpoint_Res getAddWatchpoint() {
            try {
                if (this.addWatchpointBuilder_ == null) {
                    return this.addWatchpoint_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (AddWatchpoint_Res)this.addWatchpointBuilder_.getMessage();
        }
        
        public Builder setAddWatchpoint(final AddWatchpoint_Res addWatchpoint_Res) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.addWatchpointBuilder_ != null) {
                                break Label_0042;
                            }
                            final AddWatchpoint_Res addWatchpoint_Res2 = addWatchpoint_Res;
                            if (addWatchpoint_Res2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final AddWatchpoint_Res addWatchpoint_Res2 = addWatchpoint_Res;
                            if (addWatchpoint_Res2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.addWatchpoint_ = addWatchpoint_Res;
                    this.onChanged();
                    break Label_0051;
                }
                this.addWatchpointBuilder_.setMessage((GeneratedMessage)addWatchpoint_Res);
            }
            this.bitField0_ |= 0x100000;
            return this;
        }
        
        public Builder setAddWatchpoint(final AddWatchpoint_Res.Builder builder) {
            Label_0038: {
                try {
                    if (this.addWatchpointBuilder_ == null) {
                        this.addWatchpoint_ = builder.build();
                        this.onChanged();
                        break Label_0038;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.addWatchpointBuilder_.setMessage((GeneratedMessage)builder.build());
            }
            this.bitField0_ |= 0x100000;
            return this;
        }
        
        public Builder mergeAddWatchpoint(final AddWatchpoint_Res p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.addWatchpointBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       80
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    11: ldc             1048576
            //    13: iand           
            //    14: ldc             1048576
            //    16: if_icmpne       68
            //    19: goto            26
            //    22: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    25: athrow         
            //    26: aload_0        
            //    27: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.addWatchpoint_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddWatchpoint_Res;
            //    30: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddWatchpoint_Res.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddWatchpoint_Res;
            //    33: if_acmpeq       68
            //    36: goto            43
            //    39: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    42: athrow         
            //    43: aload_0        
            //    44: aload_0        
            //    45: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.addWatchpoint_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddWatchpoint_Res;
            //    48: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddWatchpoint_Res.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddWatchpoint_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddWatchpoint_Res$Builder;
            //    51: aload_1        
            //    52: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddWatchpoint_Res$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddWatchpoint_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddWatchpoint_Res$Builder;
            //    55: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddWatchpoint_Res$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddWatchpoint_Res;
            //    58: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.addWatchpoint_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddWatchpoint_Res;
            //    61: goto            73
            //    64: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    67: athrow         
            //    68: aload_0        
            //    69: aload_1        
            //    70: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.addWatchpoint_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddWatchpoint_Res;
            //    73: aload_0        
            //    74: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.onChanged:()V
            //    77: goto            89
            //    80: aload_0        
            //    81: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.addWatchpointBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    84: aload_1        
            //    85: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    88: pop            
            //    89: aload_0        
            //    90: dup            
            //    91: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    94: ldc             1048576
            //    96: ior            
            //    97: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //   100: aload_0        
            //   101: areturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                            
            //  -----  -----  -----  -----  --------------------------------
            //  0      19     22     26     Ljava/lang/NullPointerException;
            //  7      36     39     43     Ljava/lang/NullPointerException;
            //  26     64     64     68     Ljava/lang/NullPointerException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0026:
            //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
            //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        public Builder clearAddWatchpoint() {
            Label_0033: {
                try {
                    if (this.addWatchpointBuilder_ == null) {
                        this.addWatchpoint_ = AddWatchpoint_Res.getDefaultInstance();
                        this.onChanged();
                        break Label_0033;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.addWatchpointBuilder_.clear();
            }
            this.bitField0_ &= 0xFFEFFFFF;
            return this;
        }
        
        public AddWatchpoint_Res.Builder getAddWatchpointBuilder() {
            this.bitField0_ |= 0x100000;
            this.onChanged();
            return (AddWatchpoint_Res.Builder)this.l().getBuilder();
        }
        
        public AddWatchpoint_ResOrBuilder getAddWatchpointOrBuilder() {
            try {
                if (this.addWatchpointBuilder_ != null) {
                    return (AddWatchpoint_ResOrBuilder)this.addWatchpointBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.addWatchpoint_;
        }
        
        private SingleFieldBuilder<AddWatchpoint_Res, AddWatchpoint_Res.Builder, AddWatchpoint_ResOrBuilder> l() {
            try {
                if (this.addWatchpointBuilder_ == null) {
                    this.addWatchpointBuilder_ = (SingleFieldBuilder<AddWatchpoint_Res, AddWatchpoint_Res.Builder, AddWatchpoint_ResOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.addWatchpoint_, this.getParentForChildren(), this.isClean());
                    this.addWatchpoint_ = null;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.addWatchpointBuilder_;
        }
        
        public boolean hasRemoveWatchpoint() {
            try {
                if ((this.bitField0_ & 0x200000) == 0x200000) {
                    return true;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return false;
        }
        
        public RemoveWatchpoint_Res getRemoveWatchpoint() {
            try {
                if (this.removeWatchpointBuilder_ == null) {
                    return this.removeWatchpoint_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (RemoveWatchpoint_Res)this.removeWatchpointBuilder_.getMessage();
        }
        
        public Builder setRemoveWatchpoint(final RemoveWatchpoint_Res removeWatchpoint_Res) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.removeWatchpointBuilder_ != null) {
                                break Label_0042;
                            }
                            final RemoveWatchpoint_Res removeWatchpoint_Res2 = removeWatchpoint_Res;
                            if (removeWatchpoint_Res2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final RemoveWatchpoint_Res removeWatchpoint_Res2 = removeWatchpoint_Res;
                            if (removeWatchpoint_Res2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.removeWatchpoint_ = removeWatchpoint_Res;
                    this.onChanged();
                    break Label_0051;
                }
                this.removeWatchpointBuilder_.setMessage((GeneratedMessage)removeWatchpoint_Res);
            }
            this.bitField0_ |= 0x200000;
            return this;
        }
        
        public Builder setRemoveWatchpoint(final RemoveWatchpoint_Res.Builder builder) {
            Label_0038: {
                try {
                    if (this.removeWatchpointBuilder_ == null) {
                        this.removeWatchpoint_ = builder.build();
                        this.onChanged();
                        break Label_0038;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.removeWatchpointBuilder_.setMessage((GeneratedMessage)builder.build());
            }
            this.bitField0_ |= 0x200000;
            return this;
        }
        
        public Builder mergeRemoveWatchpoint(final RemoveWatchpoint_Res p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.removeWatchpointBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       80
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    11: ldc             2097152
            //    13: iand           
            //    14: ldc             2097152
            //    16: if_icmpne       68
            //    19: goto            26
            //    22: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    25: athrow         
            //    26: aload_0        
            //    27: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.removeWatchpoint_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveWatchpoint_Res;
            //    30: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveWatchpoint_Res.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveWatchpoint_Res;
            //    33: if_acmpeq       68
            //    36: goto            43
            //    39: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    42: athrow         
            //    43: aload_0        
            //    44: aload_0        
            //    45: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.removeWatchpoint_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveWatchpoint_Res;
            //    48: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveWatchpoint_Res.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveWatchpoint_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveWatchpoint_Res$Builder;
            //    51: aload_1        
            //    52: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveWatchpoint_Res$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveWatchpoint_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveWatchpoint_Res$Builder;
            //    55: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveWatchpoint_Res$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveWatchpoint_Res;
            //    58: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.removeWatchpoint_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveWatchpoint_Res;
            //    61: goto            73
            //    64: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    67: athrow         
            //    68: aload_0        
            //    69: aload_1        
            //    70: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.removeWatchpoint_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveWatchpoint_Res;
            //    73: aload_0        
            //    74: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.onChanged:()V
            //    77: goto            89
            //    80: aload_0        
            //    81: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.removeWatchpointBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    84: aload_1        
            //    85: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    88: pop            
            //    89: aload_0        
            //    90: dup            
            //    91: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    94: ldc             2097152
            //    96: ior            
            //    97: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //   100: aload_0        
            //   101: areturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                            
            //  -----  -----  -----  -----  --------------------------------
            //  0      19     22     26     Ljava/lang/NullPointerException;
            //  7      36     39     43     Ljava/lang/NullPointerException;
            //  26     64     64     68     Ljava/lang/NullPointerException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0026:
            //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
            //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        public Builder clearRemoveWatchpoint() {
            Label_0033: {
                try {
                    if (this.removeWatchpointBuilder_ == null) {
                        this.removeWatchpoint_ = RemoveWatchpoint_Res.getDefaultInstance();
                        this.onChanged();
                        break Label_0033;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.removeWatchpointBuilder_.clear();
            }
            this.bitField0_ &= 0xFFDFFFFF;
            return this;
        }
        
        public RemoveWatchpoint_Res.Builder getRemoveWatchpointBuilder() {
            this.bitField0_ |= 0x200000;
            this.onChanged();
            return (RemoveWatchpoint_Res.Builder)this.b().getBuilder();
        }
        
        public RemoveWatchpoint_ResOrBuilder getRemoveWatchpointOrBuilder() {
            try {
                if (this.removeWatchpointBuilder_ != null) {
                    return (RemoveWatchpoint_ResOrBuilder)this.removeWatchpointBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.removeWatchpoint_;
        }
        
        private SingleFieldBuilder<RemoveWatchpoint_Res, RemoveWatchpoint_Res.Builder, RemoveWatchpoint_ResOrBuilder> b() {
            try {
                if (this.removeWatchpointBuilder_ == null) {
                    this.removeWatchpointBuilder_ = (SingleFieldBuilder<RemoveWatchpoint_Res, RemoveWatchpoint_Res.Builder, RemoveWatchpoint_ResOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.removeWatchpoint_, this.getParentForChildren(), this.isClean());
                    this.removeWatchpoint_ = null;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.removeWatchpointBuilder_;
        }
        
        public boolean hasDetach() {
            try {
                if ((this.bitField0_ & 0x400000) == 0x400000) {
                    return true;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return false;
        }
        
        public Detach_Res getDetach() {
            try {
                if (this.detachBuilder_ == null) {
                    return this.detach_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (Detach_Res)this.detachBuilder_.getMessage();
        }
        
        public Builder setDetach(final Detach_Res detach_Res) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.detachBuilder_ != null) {
                                break Label_0042;
                            }
                            final Detach_Res detach_Res2 = detach_Res;
                            if (detach_Res2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final Detach_Res detach_Res2 = detach_Res;
                            if (detach_Res2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.detach_ = detach_Res;
                    this.onChanged();
                    break Label_0051;
                }
                this.detachBuilder_.setMessage((GeneratedMessage)detach_Res);
            }
            this.bitField0_ |= 0x400000;
            return this;
        }
        
        public Builder setDetach(final Detach_Res.Builder builder) {
            Label_0038: {
                try {
                    if (this.detachBuilder_ == null) {
                        this.detach_ = builder.build();
                        this.onChanged();
                        break Label_0038;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.detachBuilder_.setMessage((GeneratedMessage)builder.build());
            }
            this.bitField0_ |= 0x400000;
            return this;
        }
        
        public Builder mergeDetach(final Detach_Res p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.detachBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       80
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    11: ldc             4194304
            //    13: iand           
            //    14: ldc             4194304
            //    16: if_icmpne       68
            //    19: goto            26
            //    22: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    25: athrow         
            //    26: aload_0        
            //    27: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.detach_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Detach_Res;
            //    30: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Detach_Res.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Detach_Res;
            //    33: if_acmpeq       68
            //    36: goto            43
            //    39: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    42: athrow         
            //    43: aload_0        
            //    44: aload_0        
            //    45: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.detach_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Detach_Res;
            //    48: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Detach_Res.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Detach_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Detach_Res$Builder;
            //    51: aload_1        
            //    52: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Detach_Res$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Detach_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Detach_Res$Builder;
            //    55: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Detach_Res$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Detach_Res;
            //    58: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.detach_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Detach_Res;
            //    61: goto            73
            //    64: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    67: athrow         
            //    68: aload_0        
            //    69: aload_1        
            //    70: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.detach_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Detach_Res;
            //    73: aload_0        
            //    74: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.onChanged:()V
            //    77: goto            89
            //    80: aload_0        
            //    81: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.detachBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    84: aload_1        
            //    85: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    88: pop            
            //    89: aload_0        
            //    90: dup            
            //    91: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    94: ldc             4194304
            //    96: ior            
            //    97: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //   100: aload_0        
            //   101: areturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                            
            //  -----  -----  -----  -----  --------------------------------
            //  0      19     22     26     Ljava/lang/NullPointerException;
            //  7      36     39     43     Ljava/lang/NullPointerException;
            //  26     64     64     68     Ljava/lang/NullPointerException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0026:
            //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
            //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        public Builder clearDetach() {
            Label_0033: {
                try {
                    if (this.detachBuilder_ == null) {
                        this.detach_ = Detach_Res.getDefaultInstance();
                        this.onChanged();
                        break Label_0033;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.detachBuilder_.clear();
            }
            this.bitField0_ &= 0xFFBFFFFF;
            return this;
        }
        
        public Detach_Res.Builder getDetachBuilder() {
            this.bitField0_ |= 0x400000;
            this.onChanged();
            return (Detach_Res.Builder)this.c().getBuilder();
        }
        
        public Detach_ResOrBuilder getDetachOrBuilder() {
            try {
                if (this.detachBuilder_ != null) {
                    return (Detach_ResOrBuilder)this.detachBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.detach_;
        }
        
        private SingleFieldBuilder<Detach_Res, Detach_Res.Builder, Detach_ResOrBuilder> c() {
            try {
                if (this.detachBuilder_ == null) {
                    this.detachBuilder_ = (SingleFieldBuilder<Detach_Res, Detach_Res.Builder, Detach_ResOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.detach_, this.getParentForChildren(), this.isClean());
                    this.detach_ = null;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.detachBuilder_;
        }
        
        public boolean hasKill() {
            try {
                if ((this.bitField0_ & 0x800000) == 0x800000) {
                    return true;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return false;
        }
        
        public Kill_Res getKill() {
            try {
                if (this.killBuilder_ == null) {
                    return this.kill_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (Kill_Res)this.killBuilder_.getMessage();
        }
        
        public Builder setKill(final Kill_Res kill_Res) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.killBuilder_ != null) {
                                break Label_0042;
                            }
                            final Kill_Res kill_Res2 = kill_Res;
                            if (kill_Res2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final Kill_Res kill_Res2 = kill_Res;
                            if (kill_Res2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.kill_ = kill_Res;
                    this.onChanged();
                    break Label_0051;
                }
                this.killBuilder_.setMessage((GeneratedMessage)kill_Res);
            }
            this.bitField0_ |= 0x800000;
            return this;
        }
        
        public Builder setKill(final Kill_Res.Builder builder) {
            Label_0038: {
                try {
                    if (this.killBuilder_ == null) {
                        this.kill_ = builder.build();
                        this.onChanged();
                        break Label_0038;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.killBuilder_.setMessage((GeneratedMessage)builder.build());
            }
            this.bitField0_ |= 0x800000;
            return this;
        }
        
        public Builder mergeKill(final Kill_Res p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.killBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       80
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    11: ldc             8388608
            //    13: iand           
            //    14: ldc             8388608
            //    16: if_icmpne       68
            //    19: goto            26
            //    22: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    25: athrow         
            //    26: aload_0        
            //    27: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.kill_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Kill_Res;
            //    30: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Kill_Res.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Kill_Res;
            //    33: if_acmpeq       68
            //    36: goto            43
            //    39: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    42: athrow         
            //    43: aload_0        
            //    44: aload_0        
            //    45: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.kill_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Kill_Res;
            //    48: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Kill_Res.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Kill_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Kill_Res$Builder;
            //    51: aload_1        
            //    52: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Kill_Res$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Kill_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Kill_Res$Builder;
            //    55: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Kill_Res$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Kill_Res;
            //    58: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.kill_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Kill_Res;
            //    61: goto            73
            //    64: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    67: athrow         
            //    68: aload_0        
            //    69: aload_1        
            //    70: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.kill_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Kill_Res;
            //    73: aload_0        
            //    74: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.onChanged:()V
            //    77: goto            89
            //    80: aload_0        
            //    81: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.killBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    84: aload_1        
            //    85: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    88: pop            
            //    89: aload_0        
            //    90: dup            
            //    91: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    94: ldc             8388608
            //    96: ior            
            //    97: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //   100: aload_0        
            //   101: areturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                            
            //  -----  -----  -----  -----  --------------------------------
            //  0      19     22     26     Ljava/lang/NullPointerException;
            //  7      36     39     43     Ljava/lang/NullPointerException;
            //  26     64     64     68     Ljava/lang/NullPointerException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0026:
            //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
            //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        public Builder clearKill() {
            Label_0033: {
                try {
                    if (this.killBuilder_ == null) {
                        this.kill_ = Kill_Res.getDefaultInstance();
                        this.onChanged();
                        break Label_0033;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.killBuilder_.clear();
            }
            this.bitField0_ &= 0xFF7FFFFF;
            return this;
        }
        
        public Kill_Res.Builder getKillBuilder() {
            this.bitField0_ |= 0x800000;
            this.onChanged();
            return (Kill_Res.Builder)this.g().getBuilder();
        }
        
        public Kill_ResOrBuilder getKillOrBuilder() {
            try {
                if (this.killBuilder_ != null) {
                    return (Kill_ResOrBuilder)this.killBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.kill_;
        }
        
        private SingleFieldBuilder<Kill_Res, Kill_Res.Builder, Kill_ResOrBuilder> g() {
            try {
                if (this.killBuilder_ == null) {
                    this.killBuilder_ = (SingleFieldBuilder<Kill_Res, Kill_Res.Builder, Kill_ResOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.kill_, this.getParentForChildren(), this.isClean());
                    this.kill_ = null;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.killBuilder_;
        }
        
        public boolean hasGetChildrenCount() {
            try {
                if ((this.bitField0_ & 0x1000000) == 0x1000000) {
                    return true;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return false;
        }
        
        public GetChildrenCount_Res getGetChildrenCount() {
            try {
                if (this.getChildrenCountBuilder_ == null) {
                    return this.getChildrenCount_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (GetChildrenCount_Res)this.getChildrenCountBuilder_.getMessage();
        }
        
        public Builder setGetChildrenCount(final GetChildrenCount_Res getChildrenCount_Res) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.getChildrenCountBuilder_ != null) {
                                break Label_0042;
                            }
                            final GetChildrenCount_Res getChildrenCount_Res2 = getChildrenCount_Res;
                            if (getChildrenCount_Res2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final GetChildrenCount_Res getChildrenCount_Res2 = getChildrenCount_Res;
                            if (getChildrenCount_Res2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.getChildrenCount_ = getChildrenCount_Res;
                    this.onChanged();
                    break Label_0051;
                }
                this.getChildrenCountBuilder_.setMessage((GeneratedMessage)getChildrenCount_Res);
            }
            this.bitField0_ |= 0x1000000;
            return this;
        }
        
        public Builder setGetChildrenCount(final GetChildrenCount_Res.Builder builder) {
            Label_0038: {
                try {
                    if (this.getChildrenCountBuilder_ == null) {
                        this.getChildrenCount_ = builder.build();
                        this.onChanged();
                        break Label_0038;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.getChildrenCountBuilder_.setMessage((GeneratedMessage)builder.build());
            }
            this.bitField0_ |= 0x1000000;
            return this;
        }
        
        public Builder mergeGetChildrenCount(final GetChildrenCount_Res p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getChildrenCountBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       80
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    11: ldc             16777216
            //    13: iand           
            //    14: ldc             16777216
            //    16: if_icmpne       68
            //    19: goto            26
            //    22: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    25: athrow         
            //    26: aload_0        
            //    27: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getChildrenCount_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetChildrenCount_Res;
            //    30: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetChildrenCount_Res.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetChildrenCount_Res;
            //    33: if_acmpeq       68
            //    36: goto            43
            //    39: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    42: athrow         
            //    43: aload_0        
            //    44: aload_0        
            //    45: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getChildrenCount_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetChildrenCount_Res;
            //    48: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetChildrenCount_Res.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetChildrenCount_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetChildrenCount_Res$Builder;
            //    51: aload_1        
            //    52: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetChildrenCount_Res$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetChildrenCount_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetChildrenCount_Res$Builder;
            //    55: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetChildrenCount_Res$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetChildrenCount_Res;
            //    58: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getChildrenCount_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetChildrenCount_Res;
            //    61: goto            73
            //    64: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    67: athrow         
            //    68: aload_0        
            //    69: aload_1        
            //    70: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getChildrenCount_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetChildrenCount_Res;
            //    73: aload_0        
            //    74: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.onChanged:()V
            //    77: goto            89
            //    80: aload_0        
            //    81: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getChildrenCountBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    84: aload_1        
            //    85: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    88: pop            
            //    89: aload_0        
            //    90: dup            
            //    91: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    94: ldc             16777216
            //    96: ior            
            //    97: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //   100: aload_0        
            //   101: areturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                            
            //  -----  -----  -----  -----  --------------------------------
            //  0      19     22     26     Ljava/lang/NullPointerException;
            //  7      36     39     43     Ljava/lang/NullPointerException;
            //  26     64     64     68     Ljava/lang/NullPointerException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0026:
            //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
            //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        public Builder clearGetChildrenCount() {
            Label_0033: {
                try {
                    if (this.getChildrenCountBuilder_ == null) {
                        this.getChildrenCount_ = GetChildrenCount_Res.getDefaultInstance();
                        this.onChanged();
                        break Label_0033;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.getChildrenCountBuilder_.clear();
            }
            this.bitField0_ &= 0xFEFFFFFF;
            return this;
        }
        
        public GetChildrenCount_Res.Builder getGetChildrenCountBuilder() {
            this.bitField0_ |= 0x1000000;
            this.onChanged();
            return (GetChildrenCount_Res.Builder)this.o().getBuilder();
        }
        
        public GetChildrenCount_ResOrBuilder getGetChildrenCountOrBuilder() {
            try {
                if (this.getChildrenCountBuilder_ != null) {
                    return (GetChildrenCount_ResOrBuilder)this.getChildrenCountBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.getChildrenCount_;
        }
        
        private SingleFieldBuilder<GetChildrenCount_Res, GetChildrenCount_Res.Builder, GetChildrenCount_ResOrBuilder> o() {
            try {
                if (this.getChildrenCountBuilder_ == null) {
                    this.getChildrenCountBuilder_ = (SingleFieldBuilder<GetChildrenCount_Res, GetChildrenCount_Res.Builder, GetChildrenCount_ResOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.getChildrenCount_, this.getParentForChildren(), this.isClean());
                    this.getChildrenCount_ = null;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.getChildrenCountBuilder_;
        }
        
        public boolean hasGetArraySlice() {
            try {
                if ((this.bitField0_ & 0x2000000) == 0x2000000) {
                    return true;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return false;
        }
        
        public GetArraySlice_Res getGetArraySlice() {
            try {
                if (this.getArraySliceBuilder_ == null) {
                    return this.getArraySlice_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (GetArraySlice_Res)this.getArraySliceBuilder_.getMessage();
        }
        
        public Builder setGetArraySlice(final GetArraySlice_Res getArraySlice_Res) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.getArraySliceBuilder_ != null) {
                                break Label_0042;
                            }
                            final GetArraySlice_Res getArraySlice_Res2 = getArraySlice_Res;
                            if (getArraySlice_Res2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final GetArraySlice_Res getArraySlice_Res2 = getArraySlice_Res;
                            if (getArraySlice_Res2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.getArraySlice_ = getArraySlice_Res;
                    this.onChanged();
                    break Label_0051;
                }
                this.getArraySliceBuilder_.setMessage((GeneratedMessage)getArraySlice_Res);
            }
            this.bitField0_ |= 0x2000000;
            return this;
        }
        
        public Builder setGetArraySlice(final GetArraySlice_Res.Builder builder) {
            Label_0038: {
                try {
                    if (this.getArraySliceBuilder_ == null) {
                        this.getArraySlice_ = builder.build();
                        this.onChanged();
                        break Label_0038;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.getArraySliceBuilder_.setMessage((GeneratedMessage)builder.build());
            }
            this.bitField0_ |= 0x2000000;
            return this;
        }
        
        public Builder mergeGetArraySlice(final GetArraySlice_Res p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getArraySliceBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       80
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    11: ldc             33554432
            //    13: iand           
            //    14: ldc             33554432
            //    16: if_icmpne       68
            //    19: goto            26
            //    22: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    25: athrow         
            //    26: aload_0        
            //    27: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getArraySlice_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetArraySlice_Res;
            //    30: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetArraySlice_Res.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetArraySlice_Res;
            //    33: if_acmpeq       68
            //    36: goto            43
            //    39: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    42: athrow         
            //    43: aload_0        
            //    44: aload_0        
            //    45: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getArraySlice_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetArraySlice_Res;
            //    48: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetArraySlice_Res.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetArraySlice_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetArraySlice_Res$Builder;
            //    51: aload_1        
            //    52: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetArraySlice_Res$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetArraySlice_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetArraySlice_Res$Builder;
            //    55: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetArraySlice_Res$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetArraySlice_Res;
            //    58: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getArraySlice_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetArraySlice_Res;
            //    61: goto            73
            //    64: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    67: athrow         
            //    68: aload_0        
            //    69: aload_1        
            //    70: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getArraySlice_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetArraySlice_Res;
            //    73: aload_0        
            //    74: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.onChanged:()V
            //    77: goto            89
            //    80: aload_0        
            //    81: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getArraySliceBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    84: aload_1        
            //    85: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    88: pop            
            //    89: aload_0        
            //    90: dup            
            //    91: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    94: ldc             33554432
            //    96: ior            
            //    97: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //   100: aload_0        
            //   101: areturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                            
            //  -----  -----  -----  -----  --------------------------------
            //  0      19     22     26     Ljava/lang/NullPointerException;
            //  7      36     39     43     Ljava/lang/NullPointerException;
            //  26     64     64     68     Ljava/lang/NullPointerException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0026:
            //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
            //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        public Builder clearGetArraySlice() {
            Label_0033: {
                try {
                    if (this.getArraySliceBuilder_ == null) {
                        this.getArraySlice_ = GetArraySlice_Res.getDefaultInstance();
                        this.onChanged();
                        break Label_0033;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.getArraySliceBuilder_.clear();
            }
            this.bitField0_ &= 0xFDFFFFFF;
            return this;
        }
        
        public GetArraySlice_Res.Builder getGetArraySliceBuilder() {
            this.bitField0_ |= 0x2000000;
            this.onChanged();
            return (GetArraySlice_Res.Builder)this.d().getBuilder();
        }
        
        public GetArraySlice_ResOrBuilder getGetArraySliceOrBuilder() {
            try {
                if (this.getArraySliceBuilder_ != null) {
                    return (GetArraySlice_ResOrBuilder)this.getArraySliceBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.getArraySlice_;
        }
        
        private SingleFieldBuilder<GetArraySlice_Res, GetArraySlice_Res.Builder, GetArraySlice_ResOrBuilder> d() {
            try {
                if (this.getArraySliceBuilder_ == null) {
                    this.getArraySliceBuilder_ = (SingleFieldBuilder<GetArraySlice_Res, GetArraySlice_Res.Builder, GetArraySlice_ResOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.getArraySlice_, this.getParentForChildren(), this.isClean());
                    this.getArraySlice_ = null;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.getArraySliceBuilder_;
        }
        
        public boolean hasGetValueData() {
            try {
                if ((this.bitField0_ & 0x4000000) == 0x4000000) {
                    return true;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return false;
        }
        
        public GetValueData_Res getGetValueData() {
            try {
                if (this.getValueDataBuilder_ == null) {
                    return this.getValueData_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (GetValueData_Res)this.getValueDataBuilder_.getMessage();
        }
        
        public Builder setGetValueData(final GetValueData_Res getValueData_Res) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.getValueDataBuilder_ != null) {
                                break Label_0042;
                            }
                            final GetValueData_Res getValueData_Res2 = getValueData_Res;
                            if (getValueData_Res2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final GetValueData_Res getValueData_Res2 = getValueData_Res;
                            if (getValueData_Res2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.getValueData_ = getValueData_Res;
                    this.onChanged();
                    break Label_0051;
                }
                this.getValueDataBuilder_.setMessage((GeneratedMessage)getValueData_Res);
            }
            this.bitField0_ |= 0x4000000;
            return this;
        }
        
        public Builder setGetValueData(final GetValueData_Res.Builder builder) {
            Label_0038: {
                try {
                    if (this.getValueDataBuilder_ == null) {
                        this.getValueData_ = builder.build();
                        this.onChanged();
                        break Label_0038;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.getValueDataBuilder_.setMessage((GeneratedMessage)builder.build());
            }
            this.bitField0_ |= 0x4000000;
            return this;
        }
        
        public Builder mergeGetValueData(final GetValueData_Res p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getValueDataBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       80
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    11: ldc             67108864
            //    13: iand           
            //    14: ldc             67108864
            //    16: if_icmpne       68
            //    19: goto            26
            //    22: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    25: athrow         
            //    26: aload_0        
            //    27: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getValueData_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Res;
            //    30: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Res.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Res;
            //    33: if_acmpeq       68
            //    36: goto            43
            //    39: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    42: athrow         
            //    43: aload_0        
            //    44: aload_0        
            //    45: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getValueData_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Res;
            //    48: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Res.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Res$Builder;
            //    51: aload_1        
            //    52: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Res$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Res$Builder;
            //    55: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Res$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Res;
            //    58: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getValueData_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Res;
            //    61: goto            73
            //    64: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    67: athrow         
            //    68: aload_0        
            //    69: aload_1        
            //    70: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getValueData_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Res;
            //    73: aload_0        
            //    74: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.onChanged:()V
            //    77: goto            89
            //    80: aload_0        
            //    81: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getValueDataBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    84: aload_1        
            //    85: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    88: pop            
            //    89: aload_0        
            //    90: dup            
            //    91: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    94: ldc             67108864
            //    96: ior            
            //    97: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //   100: aload_0        
            //   101: areturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                            
            //  -----  -----  -----  -----  --------------------------------
            //  0      19     22     26     Ljava/lang/NullPointerException;
            //  7      36     39     43     Ljava/lang/NullPointerException;
            //  26     64     64     68     Ljava/lang/NullPointerException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0026:
            //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
            //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        public Builder clearGetValueData() {
            Label_0033: {
                try {
                    if (this.getValueDataBuilder_ == null) {
                        this.getValueData_ = GetValueData_Res.getDefaultInstance();
                        this.onChanged();
                        break Label_0033;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.getValueDataBuilder_.clear();
            }
            this.bitField0_ &= 0xFBFFFFFF;
            return this;
        }
        
        public GetValueData_Res.Builder getGetValueDataBuilder() {
            this.bitField0_ |= 0x4000000;
            this.onChanged();
            return (GetValueData_Res.Builder)this.w().getBuilder();
        }
        
        public GetValueData_ResOrBuilder getGetValueDataOrBuilder() {
            try {
                if (this.getValueDataBuilder_ != null) {
                    return (GetValueData_ResOrBuilder)this.getValueDataBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.getValueData_;
        }
        
        private SingleFieldBuilder<GetValueData_Res, GetValueData_Res.Builder, GetValueData_ResOrBuilder> w() {
            try {
                if (this.getValueDataBuilder_ == null) {
                    this.getValueDataBuilder_ = (SingleFieldBuilder<GetValueData_Res, GetValueData_Res.Builder, GetValueData_ResOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.getValueData_, this.getParentForChildren(), this.isClean());
                    this.getValueData_ = null;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.getValueDataBuilder_;
        }
        
        public boolean hasGetValueDescription() {
            try {
                if ((this.bitField0_ & 0x8000000) == 0x8000000) {
                    return true;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return false;
        }
        
        public GetValueDescription_Res getGetValueDescription() {
            try {
                if (this.getValueDescriptionBuilder_ == null) {
                    return this.getValueDescription_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (GetValueDescription_Res)this.getValueDescriptionBuilder_.getMessage();
        }
        
        public Builder setGetValueDescription(final GetValueDescription_Res getValueDescription_Res) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.getValueDescriptionBuilder_ != null) {
                                break Label_0042;
                            }
                            final GetValueDescription_Res getValueDescription_Res2 = getValueDescription_Res;
                            if (getValueDescription_Res2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final GetValueDescription_Res getValueDescription_Res2 = getValueDescription_Res;
                            if (getValueDescription_Res2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.getValueDescription_ = getValueDescription_Res;
                    this.onChanged();
                    break Label_0051;
                }
                this.getValueDescriptionBuilder_.setMessage((GeneratedMessage)getValueDescription_Res);
            }
            this.bitField0_ |= 0x8000000;
            return this;
        }
        
        public Builder setGetValueDescription(final GetValueDescription_Res.Builder builder) {
            Label_0038: {
                try {
                    if (this.getValueDescriptionBuilder_ == null) {
                        this.getValueDescription_ = builder.build();
                        this.onChanged();
                        break Label_0038;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.getValueDescriptionBuilder_.setMessage((GeneratedMessage)builder.build());
            }
            this.bitField0_ |= 0x8000000;
            return this;
        }
        
        public Builder mergeGetValueDescription(final GetValueDescription_Res p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getValueDescriptionBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       80
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    11: ldc             134217728
            //    13: iand           
            //    14: ldc             134217728
            //    16: if_icmpne       68
            //    19: goto            26
            //    22: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    25: athrow         
            //    26: aload_0        
            //    27: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getValueDescription_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueDescription_Res;
            //    30: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueDescription_Res.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueDescription_Res;
            //    33: if_acmpeq       68
            //    36: goto            43
            //    39: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    42: athrow         
            //    43: aload_0        
            //    44: aload_0        
            //    45: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getValueDescription_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueDescription_Res;
            //    48: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueDescription_Res.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueDescription_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueDescription_Res$Builder;
            //    51: aload_1        
            //    52: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueDescription_Res$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueDescription_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueDescription_Res$Builder;
            //    55: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueDescription_Res$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueDescription_Res;
            //    58: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getValueDescription_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueDescription_Res;
            //    61: goto            73
            //    64: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    67: athrow         
            //    68: aload_0        
            //    69: aload_1        
            //    70: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getValueDescription_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueDescription_Res;
            //    73: aload_0        
            //    74: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.onChanged:()V
            //    77: goto            89
            //    80: aload_0        
            //    81: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getValueDescriptionBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    84: aload_1        
            //    85: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    88: pop            
            //    89: aload_0        
            //    90: dup            
            //    91: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    94: ldc             134217728
            //    96: ior            
            //    97: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //   100: aload_0        
            //   101: areturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                            
            //  -----  -----  -----  -----  --------------------------------
            //  0      19     22     26     Ljava/lang/NullPointerException;
            //  7      36     39     43     Ljava/lang/NullPointerException;
            //  26     64     64     68     Ljava/lang/NullPointerException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0026:
            //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
            //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        public Builder clearGetValueDescription() {
            Label_0033: {
                try {
                    if (this.getValueDescriptionBuilder_ == null) {
                        this.getValueDescription_ = GetValueDescription_Res.getDefaultInstance();
                        this.onChanged();
                        break Label_0033;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.getValueDescriptionBuilder_.clear();
            }
            this.bitField0_ &= 0xF7FFFFFF;
            return this;
        }
        
        public GetValueDescription_Res.Builder getGetValueDescriptionBuilder() {
            this.bitField0_ |= 0x8000000;
            this.onChanged();
            return (GetValueDescription_Res.Builder)this.s().getBuilder();
        }
        
        public GetValueDescription_ResOrBuilder getGetValueDescriptionOrBuilder() {
            try {
                if (this.getValueDescriptionBuilder_ != null) {
                    return (GetValueDescription_ResOrBuilder)this.getValueDescriptionBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.getValueDescription_;
        }
        
        private SingleFieldBuilder<GetValueDescription_Res, GetValueDescription_Res.Builder, GetValueDescription_ResOrBuilder> s() {
            try {
                if (this.getValueDescriptionBuilder_ == null) {
                    this.getValueDescriptionBuilder_ = (SingleFieldBuilder<GetValueDescription_Res, GetValueDescription_Res.Builder, GetValueDescription_ResOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.getValueDescription_, this.getParentForChildren(), this.isClean());
                    this.getValueDescription_ = null;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.getValueDescriptionBuilder_;
        }
        
        public boolean hasValuesFilteringPolicy() {
            try {
                if ((this.bitField0_ & 0x10000000) == 0x10000000) {
                    return true;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return false;
        }
        
        public ValuesFilteringPolicy_Res getValuesFilteringPolicy() {
            try {
                if (this.valuesFilteringPolicyBuilder_ == null) {
                    return this.valuesFilteringPolicy_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (ValuesFilteringPolicy_Res)this.valuesFilteringPolicyBuilder_.getMessage();
        }
        
        public Builder setValuesFilteringPolicy(final ValuesFilteringPolicy_Res valuesFilteringPolicy_Res) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.valuesFilteringPolicyBuilder_ != null) {
                                break Label_0042;
                            }
                            final ValuesFilteringPolicy_Res valuesFilteringPolicy_Res2 = valuesFilteringPolicy_Res;
                            if (valuesFilteringPolicy_Res2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final ValuesFilteringPolicy_Res valuesFilteringPolicy_Res2 = valuesFilteringPolicy_Res;
                            if (valuesFilteringPolicy_Res2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.valuesFilteringPolicy_ = valuesFilteringPolicy_Res;
                    this.onChanged();
                    break Label_0051;
                }
                this.valuesFilteringPolicyBuilder_.setMessage((GeneratedMessage)valuesFilteringPolicy_Res);
            }
            this.bitField0_ |= 0x10000000;
            return this;
        }
        
        public Builder setValuesFilteringPolicy(final ValuesFilteringPolicy_Res.Builder builder) {
            Label_0038: {
                try {
                    if (this.valuesFilteringPolicyBuilder_ == null) {
                        this.valuesFilteringPolicy_ = builder.build();
                        this.onChanged();
                        break Label_0038;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.valuesFilteringPolicyBuilder_.setMessage((GeneratedMessage)builder.build());
            }
            this.bitField0_ |= 0x10000000;
            return this;
        }
        
        public Builder mergeValuesFilteringPolicy(final ValuesFilteringPolicy_Res p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.valuesFilteringPolicyBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       80
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    11: ldc             268435456
            //    13: iand           
            //    14: ldc             268435456
            //    16: if_icmpne       68
            //    19: goto            26
            //    22: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    25: athrow         
            //    26: aload_0        
            //    27: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.valuesFilteringPolicy_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ValuesFilteringPolicy_Res;
            //    30: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ValuesFilteringPolicy_Res.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ValuesFilteringPolicy_Res;
            //    33: if_acmpeq       68
            //    36: goto            43
            //    39: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    42: athrow         
            //    43: aload_0        
            //    44: aload_0        
            //    45: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.valuesFilteringPolicy_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ValuesFilteringPolicy_Res;
            //    48: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ValuesFilteringPolicy_Res.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ValuesFilteringPolicy_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ValuesFilteringPolicy_Res$Builder;
            //    51: aload_1        
            //    52: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ValuesFilteringPolicy_Res$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ValuesFilteringPolicy_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ValuesFilteringPolicy_Res$Builder;
            //    55: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ValuesFilteringPolicy_Res$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ValuesFilteringPolicy_Res;
            //    58: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.valuesFilteringPolicy_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ValuesFilteringPolicy_Res;
            //    61: goto            73
            //    64: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    67: athrow         
            //    68: aload_0        
            //    69: aload_1        
            //    70: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.valuesFilteringPolicy_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ValuesFilteringPolicy_Res;
            //    73: aload_0        
            //    74: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.onChanged:()V
            //    77: goto            89
            //    80: aload_0        
            //    81: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.valuesFilteringPolicyBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    84: aload_1        
            //    85: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    88: pop            
            //    89: aload_0        
            //    90: dup            
            //    91: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    94: ldc             268435456
            //    96: ior            
            //    97: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //   100: aload_0        
            //   101: areturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                            
            //  -----  -----  -----  -----  --------------------------------
            //  0      19     22     26     Ljava/lang/NullPointerException;
            //  7      36     39     43     Ljava/lang/NullPointerException;
            //  26     64     64     68     Ljava/lang/NullPointerException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0026:
            //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
            //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        public Builder clearValuesFilteringPolicy() {
            Label_0033: {
                try {
                    if (this.valuesFilteringPolicyBuilder_ == null) {
                        this.valuesFilteringPolicy_ = ValuesFilteringPolicy_Res.getDefaultInstance();
                        this.onChanged();
                        break Label_0033;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.valuesFilteringPolicyBuilder_.clear();
            }
            this.bitField0_ &= 0xEFFFFFFF;
            return this;
        }
        
        public ValuesFilteringPolicy_Res.Builder getValuesFilteringPolicyBuilder() {
            this.bitField0_ |= 0x10000000;
            this.onChanged();
            return (ValuesFilteringPolicy_Res.Builder)this.D().getBuilder();
        }
        
        public ValuesFilteringPolicy_ResOrBuilder getValuesFilteringPolicyOrBuilder() {
            try {
                if (this.valuesFilteringPolicyBuilder_ != null) {
                    return (ValuesFilteringPolicy_ResOrBuilder)this.valuesFilteringPolicyBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.valuesFilteringPolicy_;
        }
        
        private SingleFieldBuilder<ValuesFilteringPolicy_Res, ValuesFilteringPolicy_Res.Builder, ValuesFilteringPolicy_ResOrBuilder> D() {
            try {
                if (this.valuesFilteringPolicyBuilder_ == null) {
                    this.valuesFilteringPolicyBuilder_ = (SingleFieldBuilder<ValuesFilteringPolicy_Res, ValuesFilteringPolicy_Res.Builder, ValuesFilteringPolicy_ResOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.valuesFilteringPolicy_, this.getParentForChildren(), this.isClean());
                    this.valuesFilteringPolicy_ = null;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.valuesFilteringPolicyBuilder_;
        }
        
        public boolean hasConnectPlatform() {
            try {
                if ((this.bitField0_ & 0x20000000) == 0x20000000) {
                    return true;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return false;
        }
        
        public ConnectPlatform_Res getConnectPlatform() {
            try {
                if (this.connectPlatformBuilder_ == null) {
                    return this.connectPlatform_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (ConnectPlatform_Res)this.connectPlatformBuilder_.getMessage();
        }
        
        public Builder setConnectPlatform(final ConnectPlatform_Res connectPlatform_Res) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.connectPlatformBuilder_ != null) {
                                break Label_0042;
                            }
                            final ConnectPlatform_Res connectPlatform_Res2 = connectPlatform_Res;
                            if (connectPlatform_Res2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final ConnectPlatform_Res connectPlatform_Res2 = connectPlatform_Res;
                            if (connectPlatform_Res2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.connectPlatform_ = connectPlatform_Res;
                    this.onChanged();
                    break Label_0051;
                }
                this.connectPlatformBuilder_.setMessage((GeneratedMessage)connectPlatform_Res);
            }
            this.bitField0_ |= 0x20000000;
            return this;
        }
        
        public Builder setConnectPlatform(final ConnectPlatform_Res.Builder builder) {
            Label_0038: {
                try {
                    if (this.connectPlatformBuilder_ == null) {
                        this.connectPlatform_ = builder.build();
                        this.onChanged();
                        break Label_0038;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.connectPlatformBuilder_.setMessage((GeneratedMessage)builder.build());
            }
            this.bitField0_ |= 0x20000000;
            return this;
        }
        
        public Builder mergeConnectPlatform(final ConnectPlatform_Res p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.connectPlatformBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       80
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    11: ldc             536870912
            //    13: iand           
            //    14: ldc             536870912
            //    16: if_icmpne       68
            //    19: goto            26
            //    22: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    25: athrow         
            //    26: aload_0        
            //    27: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.connectPlatform_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ConnectPlatform_Res;
            //    30: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ConnectPlatform_Res.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ConnectPlatform_Res;
            //    33: if_acmpeq       68
            //    36: goto            43
            //    39: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    42: athrow         
            //    43: aload_0        
            //    44: aload_0        
            //    45: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.connectPlatform_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ConnectPlatform_Res;
            //    48: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ConnectPlatform_Res.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ConnectPlatform_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ConnectPlatform_Res$Builder;
            //    51: aload_1        
            //    52: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ConnectPlatform_Res$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ConnectPlatform_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ConnectPlatform_Res$Builder;
            //    55: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ConnectPlatform_Res$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ConnectPlatform_Res;
            //    58: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.connectPlatform_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ConnectPlatform_Res;
            //    61: goto            73
            //    64: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    67: athrow         
            //    68: aload_0        
            //    69: aload_1        
            //    70: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.connectPlatform_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ConnectPlatform_Res;
            //    73: aload_0        
            //    74: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.onChanged:()V
            //    77: goto            89
            //    80: aload_0        
            //    81: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.connectPlatformBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    84: aload_1        
            //    85: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    88: pop            
            //    89: aload_0        
            //    90: dup            
            //    91: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    94: ldc             536870912
            //    96: ior            
            //    97: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //   100: aload_0        
            //   101: areturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                            
            //  -----  -----  -----  -----  --------------------------------
            //  0      19     22     26     Ljava/lang/NullPointerException;
            //  7      36     39     43     Ljava/lang/NullPointerException;
            //  26     64     64     68     Ljava/lang/NullPointerException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0026:
            //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
            //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        public Builder clearConnectPlatform() {
            Label_0033: {
                try {
                    if (this.connectPlatformBuilder_ == null) {
                        this.connectPlatform_ = ConnectPlatform_Res.getDefaultInstance();
                        this.onChanged();
                        break Label_0033;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.connectPlatformBuilder_.clear();
            }
            this.bitField0_ &= 0xDFFFFFFF;
            return this;
        }
        
        public ConnectPlatform_Res.Builder getConnectPlatformBuilder() {
            this.bitField0_ |= 0x20000000;
            this.onChanged();
            return (ConnectPlatform_Res.Builder)this.E().getBuilder();
        }
        
        public ConnectPlatform_ResOrBuilder getConnectPlatformOrBuilder() {
            try {
                if (this.connectPlatformBuilder_ != null) {
                    return (ConnectPlatform_ResOrBuilder)this.connectPlatformBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.connectPlatform_;
        }
        
        private SingleFieldBuilder<ConnectPlatform_Res, ConnectPlatform_Res.Builder, ConnectPlatform_ResOrBuilder> E() {
            try {
                if (this.connectPlatformBuilder_ == null) {
                    this.connectPlatformBuilder_ = (SingleFieldBuilder<ConnectPlatform_Res, ConnectPlatform_Res.Builder, ConnectPlatform_ResOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.connectPlatform_, this.getParentForChildren(), this.isClean());
                    this.connectPlatform_ = null;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.connectPlatformBuilder_;
        }
        
        public boolean hasGetValueAddress() {
            try {
                if ((this.bitField0_ & 0x40000000) == 0x40000000) {
                    return true;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return false;
        }
        
        public GetValueAddress_Res getGetValueAddress() {
            try {
                if (this.getValueAddressBuilder_ == null) {
                    return this.getValueAddress_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (GetValueAddress_Res)this.getValueAddressBuilder_.getMessage();
        }
        
        public Builder setGetValueAddress(final GetValueAddress_Res getValueAddress_Res) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.getValueAddressBuilder_ != null) {
                                break Label_0042;
                            }
                            final GetValueAddress_Res getValueAddress_Res2 = getValueAddress_Res;
                            if (getValueAddress_Res2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final GetValueAddress_Res getValueAddress_Res2 = getValueAddress_Res;
                            if (getValueAddress_Res2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.getValueAddress_ = getValueAddress_Res;
                    this.onChanged();
                    break Label_0051;
                }
                this.getValueAddressBuilder_.setMessage((GeneratedMessage)getValueAddress_Res);
            }
            this.bitField0_ |= 0x40000000;
            return this;
        }
        
        public Builder setGetValueAddress(final GetValueAddress_Res.Builder builder) {
            Label_0038: {
                try {
                    if (this.getValueAddressBuilder_ == null) {
                        this.getValueAddress_ = builder.build();
                        this.onChanged();
                        break Label_0038;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.getValueAddressBuilder_.setMessage((GeneratedMessage)builder.build());
            }
            this.bitField0_ |= 0x40000000;
            return this;
        }
        
        public Builder mergeGetValueAddress(final GetValueAddress_Res p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getValueAddressBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       80
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    11: ldc             1073741824
            //    13: iand           
            //    14: ldc             1073741824
            //    16: if_icmpne       68
            //    19: goto            26
            //    22: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    25: athrow         
            //    26: aload_0        
            //    27: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getValueAddress_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueAddress_Res;
            //    30: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueAddress_Res.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueAddress_Res;
            //    33: if_acmpeq       68
            //    36: goto            43
            //    39: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    42: athrow         
            //    43: aload_0        
            //    44: aload_0        
            //    45: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getValueAddress_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueAddress_Res;
            //    48: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueAddress_Res.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueAddress_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueAddress_Res$Builder;
            //    51: aload_1        
            //    52: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueAddress_Res$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueAddress_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueAddress_Res$Builder;
            //    55: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueAddress_Res$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueAddress_Res;
            //    58: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getValueAddress_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueAddress_Res;
            //    61: goto            73
            //    64: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    67: athrow         
            //    68: aload_0        
            //    69: aload_1        
            //    70: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getValueAddress_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueAddress_Res;
            //    73: aload_0        
            //    74: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.onChanged:()V
            //    77: goto            89
            //    80: aload_0        
            //    81: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.getValueAddressBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    84: aload_1        
            //    85: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    88: pop            
            //    89: aload_0        
            //    90: dup            
            //    91: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    94: ldc             1073741824
            //    96: ior            
            //    97: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //   100: aload_0        
            //   101: areturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                            
            //  -----  -----  -----  -----  --------------------------------
            //  0      19     22     26     Ljava/lang/NullPointerException;
            //  7      36     39     43     Ljava/lang/NullPointerException;
            //  26     64     64     68     Ljava/lang/NullPointerException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0026:
            //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
            //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        public Builder clearGetValueAddress() {
            Label_0033: {
                try {
                    if (this.getValueAddressBuilder_ == null) {
                        this.getValueAddress_ = GetValueAddress_Res.getDefaultInstance();
                        this.onChanged();
                        break Label_0033;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.getValueAddressBuilder_.clear();
            }
            this.bitField0_ &= 0xBFFFFFFF;
            return this;
        }
        
        public GetValueAddress_Res.Builder getGetValueAddressBuilder() {
            this.bitField0_ |= 0x40000000;
            this.onChanged();
            return (GetValueAddress_Res.Builder)this.z().getBuilder();
        }
        
        public GetValueAddress_ResOrBuilder getGetValueAddressOrBuilder() {
            try {
                if (this.getValueAddressBuilder_ != null) {
                    return (GetValueAddress_ResOrBuilder)this.getValueAddressBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.getValueAddress_;
        }
        
        private SingleFieldBuilder<GetValueAddress_Res, GetValueAddress_Res.Builder, GetValueAddress_ResOrBuilder> z() {
            try {
                if (this.getValueAddressBuilder_ == null) {
                    this.getValueAddressBuilder_ = (SingleFieldBuilder<GetValueAddress_Res, GetValueAddress_Res.Builder, GetValueAddress_ResOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.getValueAddress_, this.getParentForChildren(), this.isClean());
                    this.getValueAddress_ = null;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.getValueAddressBuilder_;
        }
        
        public boolean hasHandleSignal() {
            try {
                if ((this.bitField0_ & Integer.MIN_VALUE) == Integer.MIN_VALUE) {
                    return true;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return false;
        }
        
        public HandleSignal_Res getHandleSignal() {
            try {
                if (this.handleSignalBuilder_ == null) {
                    return this.handleSignal_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (HandleSignal_Res)this.handleSignalBuilder_.getMessage();
        }
        
        public Builder setHandleSignal(final HandleSignal_Res handleSignal_Res) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.handleSignalBuilder_ != null) {
                                break Label_0042;
                            }
                            final HandleSignal_Res handleSignal_Res2 = handleSignal_Res;
                            if (handleSignal_Res2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final HandleSignal_Res handleSignal_Res2 = handleSignal_Res;
                            if (handleSignal_Res2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.handleSignal_ = handleSignal_Res;
                    this.onChanged();
                    break Label_0051;
                }
                this.handleSignalBuilder_.setMessage((GeneratedMessage)handleSignal_Res);
            }
            this.bitField0_ |= Integer.MIN_VALUE;
            return this;
        }
        
        public Builder setHandleSignal(final HandleSignal_Res.Builder builder) {
            Label_0038: {
                try {
                    if (this.handleSignalBuilder_ == null) {
                        this.handleSignal_ = builder.build();
                        this.onChanged();
                        break Label_0038;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.handleSignalBuilder_.setMessage((GeneratedMessage)builder.build());
            }
            this.bitField0_ |= Integer.MIN_VALUE;
            return this;
        }
        
        public Builder mergeHandleSignal(final HandleSignal_Res p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.handleSignalBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       80
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    11: ldc             -2147483648
            //    13: iand           
            //    14: ldc             -2147483648
            //    16: if_icmpne       68
            //    19: goto            26
            //    22: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    25: athrow         
            //    26: aload_0        
            //    27: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.handleSignal_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleSignal_Res;
            //    30: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleSignal_Res.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleSignal_Res;
            //    33: if_acmpeq       68
            //    36: goto            43
            //    39: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    42: athrow         
            //    43: aload_0        
            //    44: aload_0        
            //    45: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.handleSignal_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleSignal_Res;
            //    48: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleSignal_Res.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleSignal_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleSignal_Res$Builder;
            //    51: aload_1        
            //    52: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleSignal_Res$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleSignal_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleSignal_Res$Builder;
            //    55: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleSignal_Res$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleSignal_Res;
            //    58: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.handleSignal_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleSignal_Res;
            //    61: goto            73
            //    64: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    67: athrow         
            //    68: aload_0        
            //    69: aload_1        
            //    70: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.handleSignal_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleSignal_Res;
            //    73: aload_0        
            //    74: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.onChanged:()V
            //    77: goto            89
            //    80: aload_0        
            //    81: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.handleSignalBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    84: aload_1        
            //    85: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    88: pop            
            //    89: aload_0        
            //    90: dup            
            //    91: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //    94: ldc             -2147483648
            //    96: ior            
            //    97: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField0_:I
            //   100: aload_0        
            //   101: areturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                            
            //  -----  -----  -----  -----  --------------------------------
            //  0      19     22     26     Ljava/lang/NullPointerException;
            //  7      36     39     43     Ljava/lang/NullPointerException;
            //  26     64     64     68     Ljava/lang/NullPointerException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0026:
            //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
            //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        public Builder clearHandleSignal() {
            Label_0033: {
                try {
                    if (this.handleSignalBuilder_ == null) {
                        this.handleSignal_ = HandleSignal_Res.getDefaultInstance();
                        this.onChanged();
                        break Label_0033;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.handleSignalBuilder_.clear();
            }
            this.bitField0_ &= Integer.MAX_VALUE;
            return this;
        }
        
        public HandleSignal_Res.Builder getHandleSignalBuilder() {
            this.bitField0_ |= Integer.MIN_VALUE;
            this.onChanged();
            return (HandleSignal_Res.Builder)this.u().getBuilder();
        }
        
        public HandleSignal_ResOrBuilder getHandleSignalOrBuilder() {
            try {
                if (this.handleSignalBuilder_ != null) {
                    return (HandleSignal_ResOrBuilder)this.handleSignalBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.handleSignal_;
        }
        
        private SingleFieldBuilder<HandleSignal_Res, HandleSignal_Res.Builder, HandleSignal_ResOrBuilder> u() {
            try {
                if (this.handleSignalBuilder_ == null) {
                    this.handleSignalBuilder_ = (SingleFieldBuilder<HandleSignal_Res, HandleSignal_Res.Builder, HandleSignal_ResOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.handleSignal_, this.getParentForChildren(), this.isClean());
                    this.handleSignal_ = null;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.handleSignalBuilder_;
        }
        
        public boolean hasExecuteShellCommand() {
            try {
                if ((this.bitField1_ & 0x1) == 0x1) {
                    return true;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return false;
        }
        
        public ExecuteShellCommand_Res getExecuteShellCommand() {
            try {
                if (this.executeShellCommandBuilder_ == null) {
                    return this.executeShellCommand_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (ExecuteShellCommand_Res)this.executeShellCommandBuilder_.getMessage();
        }
        
        public Builder setExecuteShellCommand(final ExecuteShellCommand_Res executeShellCommand_Res) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.executeShellCommandBuilder_ != null) {
                                break Label_0042;
                            }
                            final ExecuteShellCommand_Res executeShellCommand_Res2 = executeShellCommand_Res;
                            if (executeShellCommand_Res2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final ExecuteShellCommand_Res executeShellCommand_Res2 = executeShellCommand_Res;
                            if (executeShellCommand_Res2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.executeShellCommand_ = executeShellCommand_Res;
                    this.onChanged();
                    break Label_0051;
                }
                this.executeShellCommandBuilder_.setMessage((GeneratedMessage)executeShellCommand_Res);
            }
            this.bitField1_ |= 0x1;
            return this;
        }
        
        public Builder setExecuteShellCommand(final ExecuteShellCommand_Res.Builder builder) {
            Label_0038: {
                try {
                    if (this.executeShellCommandBuilder_ == null) {
                        this.executeShellCommand_ = builder.build();
                        this.onChanged();
                        break Label_0038;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.executeShellCommandBuilder_.setMessage((GeneratedMessage)builder.build());
            }
            this.bitField1_ |= 0x1;
            return this;
        }
        
        public Builder mergeExecuteShellCommand(final ExecuteShellCommand_Res p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.executeShellCommandBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       78
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField1_:I
            //    11: iconst_1       
            //    12: iand           
            //    13: iconst_1       
            //    14: if_icmpne       66
            //    17: goto            24
            //    20: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    23: athrow         
            //    24: aload_0        
            //    25: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.executeShellCommand_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ExecuteShellCommand_Res;
            //    28: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ExecuteShellCommand_Res.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ExecuteShellCommand_Res;
            //    31: if_acmpeq       66
            //    34: goto            41
            //    37: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    40: athrow         
            //    41: aload_0        
            //    42: aload_0        
            //    43: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.executeShellCommand_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ExecuteShellCommand_Res;
            //    46: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ExecuteShellCommand_Res.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ExecuteShellCommand_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ExecuteShellCommand_Res$Builder;
            //    49: aload_1        
            //    50: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ExecuteShellCommand_Res$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ExecuteShellCommand_Res;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ExecuteShellCommand_Res$Builder;
            //    53: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ExecuteShellCommand_Res$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ExecuteShellCommand_Res;
            //    56: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.executeShellCommand_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ExecuteShellCommand_Res;
            //    59: goto            71
            //    62: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    65: athrow         
            //    66: aload_0        
            //    67: aload_1        
            //    68: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.executeShellCommand_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ExecuteShellCommand_Res;
            //    71: aload_0        
            //    72: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.onChanged:()V
            //    75: goto            87
            //    78: aload_0        
            //    79: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.executeShellCommandBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    82: aload_1        
            //    83: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    86: pop            
            //    87: aload_0        
            //    88: dup            
            //    89: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField1_:I
            //    92: iconst_1       
            //    93: ior            
            //    94: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeResponse$Builder.bitField1_:I
            //    97: aload_0        
            //    98: areturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                            
            //  -----  -----  -----  -----  --------------------------------
            //  0      17     20     24     Ljava/lang/NullPointerException;
            //  7      34     37     41     Ljava/lang/NullPointerException;
            //  24     62     62     66     Ljava/lang/NullPointerException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0024:
            //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
            //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        public Builder clearExecuteShellCommand() {
            Label_0033: {
                try {
                    if (this.executeShellCommandBuilder_ == null) {
                        this.executeShellCommand_ = ExecuteShellCommand_Res.getDefaultInstance();
                        this.onChanged();
                        break Label_0033;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.executeShellCommandBuilder_.clear();
            }
            this.bitField1_ &= 0xFFFFFFFE;
            return this;
        }
        
        public ExecuteShellCommand_Res.Builder getExecuteShellCommandBuilder() {
            this.bitField1_ |= 0x1;
            this.onChanged();
            return (ExecuteShellCommand_Res.Builder)this.t().getBuilder();
        }
        
        public ExecuteShellCommand_ResOrBuilder getExecuteShellCommandOrBuilder() {
            try {
                if (this.executeShellCommandBuilder_ != null) {
                    return (ExecuteShellCommand_ResOrBuilder)this.executeShellCommandBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.executeShellCommand_;
        }
        
        private SingleFieldBuilder<ExecuteShellCommand_Res, ExecuteShellCommand_Res.Builder, ExecuteShellCommand_ResOrBuilder> t() {
            try {
                if (this.executeShellCommandBuilder_ == null) {
                    this.executeShellCommandBuilder_ = (SingleFieldBuilder<ExecuteShellCommand_Res, ExecuteShellCommand_Res.Builder, ExecuteShellCommand_ResOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.executeShellCommand_, this.getParentForChildren(), this.isClean());
                    this.executeShellCommand_ = null;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.executeShellCommandBuilder_;
        }
        
        private static Exception b(final Exception ex) {
            return ex;
        }
    }
}
