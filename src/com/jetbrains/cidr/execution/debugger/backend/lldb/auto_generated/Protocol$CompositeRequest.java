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

public static final class CompositeRequest extends GeneratedMessage implements CompositeRequestOrBuilder
{
    private static final CompositeRequest defaultInstance;
    private final UnknownFieldSet unknownFields;
    public static Parser<CompositeRequest> PARSER;
    private int bitField0_;
    private int bitField1_;
    public static final int CREATE_TARGET_FIELD_NUMBER = 1;
    private CreateTarget_Req createTarget_;
    public static final int LAUNCH_FIELD_NUMBER = 2;
    private Launch_Req launch_;
    public static final int EXIT_FIELD_NUMBER = 3;
    private Exit_Req exit_;
    public static final int CONTINUE_FIELD_NUMBER = 4;
    private Continue_Req continue_;
    public static final int SUSPEND_FIELD_NUMBER = 5;
    private Suspend_Req suspend_;
    public static final int GET_THREADS_FIELD_NUMBER = 6;
    private GetThreads_Req getThreads_;
    public static final int GET_FRAMES_FIELD_NUMBER = 7;
    private GetFrames_Req getFrames_;
    public static final int ADD_BREAKPOINT_FIELD_NUMBER = 8;
    private AddBreakpoint_Req addBreakpoint_;
    public static final int REMOVE_BREAKPOINT_FIELD_NUMBER = 9;
    private RemoveBreakpoint_Req removeBreakpoint_;
    public static final int STEP_INTO_FIELD_NUMBER = 10;
    private StepInto_Req stepInto_;
    public static final int STEP_OVER_FIELD_NUMBER = 11;
    private StepOver_Req stepOver_;
    public static final int STEP_OUT_FIELD_NUMBER = 12;
    private StepOut_Req stepOut_;
    public static final int EVALUATE_EXPRESSION_FIELD_NUMBER = 13;
    private EvaluateExpression_Req evaluateExpression_;
    public static final int GET_VALUE_CHILDREN_FIELD_NUMBER = 14;
    private GetValueChildren_Req getValueChildren_;
    public static final int GET_VARS_FIELD_NUMBER = 15;
    private GetVars_Req getVars_;
    public static final int HANDLE_CONSOLE_COMMAND_FIELD_NUMBER = 16;
    private HandleConsoleCommand_Req handleConsoleCommand_;
    public static final int HANDLE_COMPLETION_FIELD_NUMBER = 17;
    private HandleCompletion_Req handleCompletion_;
    public static final int ATTACH_FIELD_NUMBER = 18;
    private Attach_Req attach_;
    public static final int ATTACH_BY_NAME_FIELD_NUMBER = 19;
    private AttachByName_Req attachByName_;
    public static final int DISPATCH_INPUT_FIELD_NUMBER = 20;
    private DispatchInput_Req dispatchInput_;
    public static final int ADD_WATCHPOINT_FIELD_NUMBER = 21;
    private AddWatchpoint_Req addWatchpoint_;
    public static final int REMOVE_WATCHPOINT_FIELD_NUMBER = 22;
    private RemoveWatchpoint_Req removeWatchpoint_;
    public static final int DETACH_FIELD_NUMBER = 23;
    private Detach_Req detach_;
    public static final int KILL_FIELD_NUMBER = 24;
    private Kill_Req kill_;
    public static final int GET_CHILDREN_COUNT_FIELD_NUMBER = 25;
    private GetChildrenCount_Req getChildrenCount_;
    public static final int GET_ARRAY_SLICE_FIELD_NUMBER = 26;
    private GetArraySlice_Req getArraySlice_;
    public static final int GET_VALUE_DATA_FIELD_NUMBER = 27;
    private GetValueData_Req getValueData_;
    public static final int GET_VALUE_DESCRIPTION_FIELD_NUMBER = 28;
    private GetValueDescription_Req getValueDescription_;
    public static final int VALUES_FILTERING_POLICY_FIELD_NUMBER = 29;
    private ValuesFilteringPolicy_Req valuesFilteringPolicy_;
    public static final int CONNECT_PLATFORM_FIELD_NUMBER = 30;
    private ConnectPlatform_Req connectPlatform_;
    public static final int GET_VALUE_ADDRESS_FIELD_NUMBER = 31;
    private GetValueAddress_Req getValueAddress_;
    public static final int HANDLE_SIGNAL_FIELD_NUMBER = 32;
    private HandleSignal_Req handleSignal_;
    public static final int EXECUTE_SHELL_COMMAND_FIELD_NUMBER = 33;
    private ExecuteShellCommand_Req executeShellCommand_;
    private byte memoizedIsInitialized;
    private int memoizedSerializedSize;
    private static final long serialVersionUID = 0L;
    
    private CompositeRequest(final GeneratedMessage.Builder<?> builder) {
        super((GeneratedMessage.Builder)builder);
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = builder.getUnknownFields();
    }
    
    private CompositeRequest(final boolean b) {
        this.memoizedIsInitialized = -1;
        this.memoizedSerializedSize = -1;
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }
    
    public static CompositeRequest getDefaultInstance() {
        return CompositeRequest.defaultInstance;
    }
    
    public CompositeRequest getDefaultInstanceForType() {
        return CompositeRequest.defaultInstance;
    }
    
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }
    
    private CompositeRequest(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        CreateTarget_Req.Builder builder2 = null;
                        if ((this.bitField0_ & 0x1) == 0x1) {
                            builder2 = this.createTarget_.toBuilder();
                        }
                        try {
                            this.createTarget_ = (CreateTarget_Req)codedInputStream.readMessage((Parser)CreateTarget_Req.PARSER, extensionRegistryLite);
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
                        Launch_Req.Builder builder3 = null;
                        if ((this.bitField0_ & 0x2) == 0x2) {
                            builder3 = this.launch_.toBuilder();
                        }
                        try {
                            this.launch_ = (Launch_Req)codedInputStream.readMessage((Parser)Launch_Req.PARSER, extensionRegistryLite);
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
                        Exit_Req.Builder builder4 = null;
                        if ((this.bitField0_ & 0x4) == 0x4) {
                            builder4 = this.exit_.toBuilder();
                        }
                        try {
                            this.exit_ = (Exit_Req)codedInputStream.readMessage((Parser)Exit_Req.PARSER, extensionRegistryLite);
                            if (builder4 != null) {
                                builder4.mergeFrom(this.exit_);
                                this.exit_ = builder4.buildPartial();
                            }
                        }
                        catch (InvalidProtocolBufferException ex3) {
                            throw a((IOException)ex3);
                        }
                        this.bitField0_ |= 0x4;
                        continue;
                    }
                    case 34: {
                        Continue_Req.Builder builder5 = null;
                        if ((this.bitField0_ & 0x8) == 0x8) {
                            builder5 = this.continue_.toBuilder();
                        }
                        try {
                            this.continue_ = (Continue_Req)codedInputStream.readMessage((Parser)Continue_Req.PARSER, extensionRegistryLite);
                            if (builder5 != null) {
                                builder5.mergeFrom(this.continue_);
                                this.continue_ = builder5.buildPartial();
                            }
                        }
                        catch (InvalidProtocolBufferException ex4) {
                            throw a((IOException)ex4);
                        }
                        this.bitField0_ |= 0x8;
                        continue;
                    }
                    case 42: {
                        Suspend_Req.Builder builder6 = null;
                        if ((this.bitField0_ & 0x10) == 0x10) {
                            builder6 = this.suspend_.toBuilder();
                        }
                        try {
                            this.suspend_ = (Suspend_Req)codedInputStream.readMessage((Parser)Suspend_Req.PARSER, extensionRegistryLite);
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
                        GetThreads_Req.Builder builder7 = null;
                        if ((this.bitField0_ & 0x20) == 0x20) {
                            builder7 = this.getThreads_.toBuilder();
                        }
                        try {
                            this.getThreads_ = (GetThreads_Req)codedInputStream.readMessage((Parser)GetThreads_Req.PARSER, extensionRegistryLite);
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
                        GetFrames_Req.Builder builder8 = null;
                        if ((this.bitField0_ & 0x40) == 0x40) {
                            builder8 = this.getFrames_.toBuilder();
                        }
                        try {
                            this.getFrames_ = (GetFrames_Req)codedInputStream.readMessage((Parser)GetFrames_Req.PARSER, extensionRegistryLite);
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
                        AddBreakpoint_Req.Builder builder9 = null;
                        if ((this.bitField0_ & 0x80) == 0x80) {
                            builder9 = this.addBreakpoint_.toBuilder();
                        }
                        try {
                            this.addBreakpoint_ = (AddBreakpoint_Req)codedInputStream.readMessage((Parser)AddBreakpoint_Req.PARSER, extensionRegistryLite);
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
                        RemoveBreakpoint_Req.Builder builder10 = null;
                        if ((this.bitField0_ & 0x100) == 0x100) {
                            builder10 = this.removeBreakpoint_.toBuilder();
                        }
                        try {
                            this.removeBreakpoint_ = (RemoveBreakpoint_Req)codedInputStream.readMessage((Parser)RemoveBreakpoint_Req.PARSER, extensionRegistryLite);
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
                        StepInto_Req.Builder builder11 = null;
                        if ((this.bitField0_ & 0x200) == 0x200) {
                            builder11 = this.stepInto_.toBuilder();
                        }
                        try {
                            this.stepInto_ = (StepInto_Req)codedInputStream.readMessage((Parser)StepInto_Req.PARSER, extensionRegistryLite);
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
                        StepOver_Req.Builder builder12 = null;
                        if ((this.bitField0_ & 0x400) == 0x400) {
                            builder12 = this.stepOver_.toBuilder();
                        }
                        try {
                            this.stepOver_ = (StepOver_Req)codedInputStream.readMessage((Parser)StepOver_Req.PARSER, extensionRegistryLite);
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
                        StepOut_Req.Builder builder13 = null;
                        if ((this.bitField0_ & 0x800) == 0x800) {
                            builder13 = this.stepOut_.toBuilder();
                        }
                        try {
                            this.stepOut_ = (StepOut_Req)codedInputStream.readMessage((Parser)StepOut_Req.PARSER, extensionRegistryLite);
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
                        EvaluateExpression_Req.Builder builder14 = null;
                        if ((this.bitField0_ & 0x1000) == 0x1000) {
                            builder14 = this.evaluateExpression_.toBuilder();
                        }
                        try {
                            this.evaluateExpression_ = (EvaluateExpression_Req)codedInputStream.readMessage((Parser)EvaluateExpression_Req.PARSER, extensionRegistryLite);
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
                        GetValueChildren_Req.Builder builder15 = null;
                        if ((this.bitField0_ & 0x2000) == 0x2000) {
                            builder15 = this.getValueChildren_.toBuilder();
                        }
                        try {
                            this.getValueChildren_ = (GetValueChildren_Req)codedInputStream.readMessage((Parser)GetValueChildren_Req.PARSER, extensionRegistryLite);
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
                        GetVars_Req.Builder builder16 = null;
                        if ((this.bitField0_ & 0x4000) == 0x4000) {
                            builder16 = this.getVars_.toBuilder();
                        }
                        try {
                            this.getVars_ = (GetVars_Req)codedInputStream.readMessage((Parser)GetVars_Req.PARSER, extensionRegistryLite);
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
                        HandleConsoleCommand_Req.Builder builder17 = null;
                        if ((this.bitField0_ & 0x8000) == 0x8000) {
                            builder17 = this.handleConsoleCommand_.toBuilder();
                        }
                        try {
                            this.handleConsoleCommand_ = (HandleConsoleCommand_Req)codedInputStream.readMessage((Parser)HandleConsoleCommand_Req.PARSER, extensionRegistryLite);
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
                        HandleCompletion_Req.Builder builder18 = null;
                        if ((this.bitField0_ & 0x10000) == 0x10000) {
                            builder18 = this.handleCompletion_.toBuilder();
                        }
                        try {
                            this.handleCompletion_ = (HandleCompletion_Req)codedInputStream.readMessage((Parser)HandleCompletion_Req.PARSER, extensionRegistryLite);
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
                        Attach_Req.Builder builder19 = null;
                        if ((this.bitField0_ & 0x20000) == 0x20000) {
                            builder19 = this.attach_.toBuilder();
                        }
                        try {
                            this.attach_ = (Attach_Req)codedInputStream.readMessage((Parser)Attach_Req.PARSER, extensionRegistryLite);
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
                        AttachByName_Req.Builder builder20 = null;
                        if ((this.bitField0_ & 0x40000) == 0x40000) {
                            builder20 = this.attachByName_.toBuilder();
                        }
                        try {
                            this.attachByName_ = (AttachByName_Req)codedInputStream.readMessage((Parser)AttachByName_Req.PARSER, extensionRegistryLite);
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
                        DispatchInput_Req.Builder builder21 = null;
                        if ((this.bitField0_ & 0x80000) == 0x80000) {
                            builder21 = this.dispatchInput_.toBuilder();
                        }
                        try {
                            this.dispatchInput_ = (DispatchInput_Req)codedInputStream.readMessage((Parser)DispatchInput_Req.PARSER, extensionRegistryLite);
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
                        AddWatchpoint_Req.Builder builder22 = null;
                        if ((this.bitField0_ & 0x100000) == 0x100000) {
                            builder22 = this.addWatchpoint_.toBuilder();
                        }
                        try {
                            this.addWatchpoint_ = (AddWatchpoint_Req)codedInputStream.readMessage((Parser)AddWatchpoint_Req.PARSER, extensionRegistryLite);
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
                        RemoveWatchpoint_Req.Builder builder23 = null;
                        if ((this.bitField0_ & 0x200000) == 0x200000) {
                            builder23 = this.removeWatchpoint_.toBuilder();
                        }
                        try {
                            this.removeWatchpoint_ = (RemoveWatchpoint_Req)codedInputStream.readMessage((Parser)RemoveWatchpoint_Req.PARSER, extensionRegistryLite);
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
                        Detach_Req.Builder builder24 = null;
                        if ((this.bitField0_ & 0x400000) == 0x400000) {
                            builder24 = this.detach_.toBuilder();
                        }
                        try {
                            this.detach_ = (Detach_Req)codedInputStream.readMessage((Parser)Detach_Req.PARSER, extensionRegistryLite);
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
                        Kill_Req.Builder builder25 = null;
                        if ((this.bitField0_ & 0x800000) == 0x800000) {
                            builder25 = this.kill_.toBuilder();
                        }
                        try {
                            this.kill_ = (Kill_Req)codedInputStream.readMessage((Parser)Kill_Req.PARSER, extensionRegistryLite);
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
                        GetChildrenCount_Req.Builder builder26 = null;
                        if ((this.bitField0_ & 0x1000000) == 0x1000000) {
                            builder26 = this.getChildrenCount_.toBuilder();
                        }
                        try {
                            this.getChildrenCount_ = (GetChildrenCount_Req)codedInputStream.readMessage((Parser)GetChildrenCount_Req.PARSER, extensionRegistryLite);
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
                        GetArraySlice_Req.Builder builder27 = null;
                        if ((this.bitField0_ & 0x2000000) == 0x2000000) {
                            builder27 = this.getArraySlice_.toBuilder();
                        }
                        try {
                            this.getArraySlice_ = (GetArraySlice_Req)codedInputStream.readMessage((Parser)GetArraySlice_Req.PARSER, extensionRegistryLite);
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
                        GetValueData_Req.Builder builder28 = null;
                        if ((this.bitField0_ & 0x4000000) == 0x4000000) {
                            builder28 = this.getValueData_.toBuilder();
                        }
                        try {
                            this.getValueData_ = (GetValueData_Req)codedInputStream.readMessage((Parser)GetValueData_Req.PARSER, extensionRegistryLite);
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
                        GetValueDescription_Req.Builder builder29 = null;
                        if ((this.bitField0_ & 0x8000000) == 0x8000000) {
                            builder29 = this.getValueDescription_.toBuilder();
                        }
                        try {
                            this.getValueDescription_ = (GetValueDescription_Req)codedInputStream.readMessage((Parser)GetValueDescription_Req.PARSER, extensionRegistryLite);
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
                        ValuesFilteringPolicy_Req.Builder builder30 = null;
                        if ((this.bitField0_ & 0x10000000) == 0x10000000) {
                            builder30 = this.valuesFilteringPolicy_.toBuilder();
                        }
                        try {
                            this.valuesFilteringPolicy_ = (ValuesFilteringPolicy_Req)codedInputStream.readMessage((Parser)ValuesFilteringPolicy_Req.PARSER, extensionRegistryLite);
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
                        ConnectPlatform_Req.Builder builder31 = null;
                        if ((this.bitField0_ & 0x20000000) == 0x20000000) {
                            builder31 = this.connectPlatform_.toBuilder();
                        }
                        try {
                            this.connectPlatform_ = (ConnectPlatform_Req)codedInputStream.readMessage((Parser)ConnectPlatform_Req.PARSER, extensionRegistryLite);
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
                        GetValueAddress_Req.Builder builder32 = null;
                        if ((this.bitField0_ & 0x40000000) == 0x40000000) {
                            builder32 = this.getValueAddress_.toBuilder();
                        }
                        try {
                            this.getValueAddress_ = (GetValueAddress_Req)codedInputStream.readMessage((Parser)GetValueAddress_Req.PARSER, extensionRegistryLite);
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
                        HandleSignal_Req.Builder builder33 = null;
                        if ((this.bitField0_ & Integer.MIN_VALUE) == Integer.MIN_VALUE) {
                            builder33 = this.handleSignal_.toBuilder();
                        }
                        try {
                            this.handleSignal_ = (HandleSignal_Req)codedInputStream.readMessage((Parser)HandleSignal_Req.PARSER, extensionRegistryLite);
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
                        ExecuteShellCommand_Req.Builder builder34 = null;
                        if ((this.bitField1_ & 0x1) == 0x1) {
                            builder34 = this.executeShellCommand_.toBuilder();
                        }
                        try {
                            this.executeShellCommand_ = (ExecuteShellCommand_Req)codedInputStream.readMessage((Parser)ExecuteShellCommand_Req.PARSER, extensionRegistryLite);
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
        return Protocol.access$65200();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$65300().ensureFieldAccessorsInitialized((Class)CompositeRequest.class, (Class)Builder.class);
    }
    
    public Parser<CompositeRequest> getParserForType() {
        return CompositeRequest.PARSER;
    }
    
    public boolean hasCreateTarget() {
        return (this.bitField0_ & 0x1) == 0x1;
    }
    
    public CreateTarget_Req getCreateTarget() {
        return this.createTarget_;
    }
    
    public CreateTarget_ReqOrBuilder getCreateTargetOrBuilder() {
        return this.createTarget_;
    }
    
    public boolean hasLaunch() {
        return (this.bitField0_ & 0x2) == 0x2;
    }
    
    public Launch_Req getLaunch() {
        return this.launch_;
    }
    
    public Launch_ReqOrBuilder getLaunchOrBuilder() {
        return this.launch_;
    }
    
    public boolean hasExit() {
        return (this.bitField0_ & 0x4) == 0x4;
    }
    
    public Exit_Req getExit() {
        return this.exit_;
    }
    
    public Exit_ReqOrBuilder getExitOrBuilder() {
        return this.exit_;
    }
    
    public boolean hasContinue() {
        return (this.bitField0_ & 0x8) == 0x8;
    }
    
    public Continue_Req getContinue() {
        return this.continue_;
    }
    
    public Continue_ReqOrBuilder getContinueOrBuilder() {
        return this.continue_;
    }
    
    public boolean hasSuspend() {
        return (this.bitField0_ & 0x10) == 0x10;
    }
    
    public Suspend_Req getSuspend() {
        return this.suspend_;
    }
    
    public Suspend_ReqOrBuilder getSuspendOrBuilder() {
        return this.suspend_;
    }
    
    public boolean hasGetThreads() {
        return (this.bitField0_ & 0x20) == 0x20;
    }
    
    public GetThreads_Req getGetThreads() {
        return this.getThreads_;
    }
    
    public GetThreads_ReqOrBuilder getGetThreadsOrBuilder() {
        return this.getThreads_;
    }
    
    public boolean hasGetFrames() {
        return (this.bitField0_ & 0x40) == 0x40;
    }
    
    public GetFrames_Req getGetFrames() {
        return this.getFrames_;
    }
    
    public GetFrames_ReqOrBuilder getGetFramesOrBuilder() {
        return this.getFrames_;
    }
    
    public boolean hasAddBreakpoint() {
        return (this.bitField0_ & 0x80) == 0x80;
    }
    
    public AddBreakpoint_Req getAddBreakpoint() {
        return this.addBreakpoint_;
    }
    
    public AddBreakpoint_ReqOrBuilder getAddBreakpointOrBuilder() {
        return this.addBreakpoint_;
    }
    
    public boolean hasRemoveBreakpoint() {
        return (this.bitField0_ & 0x100) == 0x100;
    }
    
    public RemoveBreakpoint_Req getRemoveBreakpoint() {
        return this.removeBreakpoint_;
    }
    
    public RemoveBreakpoint_ReqOrBuilder getRemoveBreakpointOrBuilder() {
        return this.removeBreakpoint_;
    }
    
    public boolean hasStepInto() {
        return (this.bitField0_ & 0x200) == 0x200;
    }
    
    public StepInto_Req getStepInto() {
        return this.stepInto_;
    }
    
    public StepInto_ReqOrBuilder getStepIntoOrBuilder() {
        return this.stepInto_;
    }
    
    public boolean hasStepOver() {
        return (this.bitField0_ & 0x400) == 0x400;
    }
    
    public StepOver_Req getStepOver() {
        return this.stepOver_;
    }
    
    public StepOver_ReqOrBuilder getStepOverOrBuilder() {
        return this.stepOver_;
    }
    
    public boolean hasStepOut() {
        return (this.bitField0_ & 0x800) == 0x800;
    }
    
    public StepOut_Req getStepOut() {
        return this.stepOut_;
    }
    
    public StepOut_ReqOrBuilder getStepOutOrBuilder() {
        return this.stepOut_;
    }
    
    public boolean hasEvaluateExpression() {
        return (this.bitField0_ & 0x1000) == 0x1000;
    }
    
    public EvaluateExpression_Req getEvaluateExpression() {
        return this.evaluateExpression_;
    }
    
    public EvaluateExpression_ReqOrBuilder getEvaluateExpressionOrBuilder() {
        return this.evaluateExpression_;
    }
    
    public boolean hasGetValueChildren() {
        return (this.bitField0_ & 0x2000) == 0x2000;
    }
    
    public GetValueChildren_Req getGetValueChildren() {
        return this.getValueChildren_;
    }
    
    public GetValueChildren_ReqOrBuilder getGetValueChildrenOrBuilder() {
        return this.getValueChildren_;
    }
    
    public boolean hasGetVars() {
        return (this.bitField0_ & 0x4000) == 0x4000;
    }
    
    public GetVars_Req getGetVars() {
        return this.getVars_;
    }
    
    public GetVars_ReqOrBuilder getGetVarsOrBuilder() {
        return this.getVars_;
    }
    
    public boolean hasHandleConsoleCommand() {
        return (this.bitField0_ & 0x8000) == 0x8000;
    }
    
    public HandleConsoleCommand_Req getHandleConsoleCommand() {
        return this.handleConsoleCommand_;
    }
    
    public HandleConsoleCommand_ReqOrBuilder getHandleConsoleCommandOrBuilder() {
        return this.handleConsoleCommand_;
    }
    
    public boolean hasHandleCompletion() {
        return (this.bitField0_ & 0x10000) == 0x10000;
    }
    
    public HandleCompletion_Req getHandleCompletion() {
        return this.handleCompletion_;
    }
    
    public HandleCompletion_ReqOrBuilder getHandleCompletionOrBuilder() {
        return this.handleCompletion_;
    }
    
    public boolean hasAttach() {
        return (this.bitField0_ & 0x20000) == 0x20000;
    }
    
    public Attach_Req getAttach() {
        return this.attach_;
    }
    
    public Attach_ReqOrBuilder getAttachOrBuilder() {
        return this.attach_;
    }
    
    public boolean hasAttachByName() {
        return (this.bitField0_ & 0x40000) == 0x40000;
    }
    
    public AttachByName_Req getAttachByName() {
        return this.attachByName_;
    }
    
    public AttachByName_ReqOrBuilder getAttachByNameOrBuilder() {
        return this.attachByName_;
    }
    
    public boolean hasDispatchInput() {
        return (this.bitField0_ & 0x80000) == 0x80000;
    }
    
    public DispatchInput_Req getDispatchInput() {
        return this.dispatchInput_;
    }
    
    public DispatchInput_ReqOrBuilder getDispatchInputOrBuilder() {
        return this.dispatchInput_;
    }
    
    public boolean hasAddWatchpoint() {
        return (this.bitField0_ & 0x100000) == 0x100000;
    }
    
    public AddWatchpoint_Req getAddWatchpoint() {
        return this.addWatchpoint_;
    }
    
    public AddWatchpoint_ReqOrBuilder getAddWatchpointOrBuilder() {
        return this.addWatchpoint_;
    }
    
    public boolean hasRemoveWatchpoint() {
        return (this.bitField0_ & 0x200000) == 0x200000;
    }
    
    public RemoveWatchpoint_Req getRemoveWatchpoint() {
        return this.removeWatchpoint_;
    }
    
    public RemoveWatchpoint_ReqOrBuilder getRemoveWatchpointOrBuilder() {
        return this.removeWatchpoint_;
    }
    
    public boolean hasDetach() {
        return (this.bitField0_ & 0x400000) == 0x400000;
    }
    
    public Detach_Req getDetach() {
        return this.detach_;
    }
    
    public Detach_ReqOrBuilder getDetachOrBuilder() {
        return this.detach_;
    }
    
    public boolean hasKill() {
        return (this.bitField0_ & 0x800000) == 0x800000;
    }
    
    public Kill_Req getKill() {
        return this.kill_;
    }
    
    public Kill_ReqOrBuilder getKillOrBuilder() {
        return this.kill_;
    }
    
    public boolean hasGetChildrenCount() {
        return (this.bitField0_ & 0x1000000) == 0x1000000;
    }
    
    public GetChildrenCount_Req getGetChildrenCount() {
        return this.getChildrenCount_;
    }
    
    public GetChildrenCount_ReqOrBuilder getGetChildrenCountOrBuilder() {
        return this.getChildrenCount_;
    }
    
    public boolean hasGetArraySlice() {
        return (this.bitField0_ & 0x2000000) == 0x2000000;
    }
    
    public GetArraySlice_Req getGetArraySlice() {
        return this.getArraySlice_;
    }
    
    public GetArraySlice_ReqOrBuilder getGetArraySliceOrBuilder() {
        return this.getArraySlice_;
    }
    
    public boolean hasGetValueData() {
        return (this.bitField0_ & 0x4000000) == 0x4000000;
    }
    
    public GetValueData_Req getGetValueData() {
        return this.getValueData_;
    }
    
    public GetValueData_ReqOrBuilder getGetValueDataOrBuilder() {
        return this.getValueData_;
    }
    
    public boolean hasGetValueDescription() {
        return (this.bitField0_ & 0x8000000) == 0x8000000;
    }
    
    public GetValueDescription_Req getGetValueDescription() {
        return this.getValueDescription_;
    }
    
    public GetValueDescription_ReqOrBuilder getGetValueDescriptionOrBuilder() {
        return this.getValueDescription_;
    }
    
    public boolean hasValuesFilteringPolicy() {
        return (this.bitField0_ & 0x10000000) == 0x10000000;
    }
    
    public ValuesFilteringPolicy_Req getValuesFilteringPolicy() {
        return this.valuesFilteringPolicy_;
    }
    
    public ValuesFilteringPolicy_ReqOrBuilder getValuesFilteringPolicyOrBuilder() {
        return this.valuesFilteringPolicy_;
    }
    
    public boolean hasConnectPlatform() {
        return (this.bitField0_ & 0x20000000) == 0x20000000;
    }
    
    public ConnectPlatform_Req getConnectPlatform() {
        return this.connectPlatform_;
    }
    
    public ConnectPlatform_ReqOrBuilder getConnectPlatformOrBuilder() {
        return this.connectPlatform_;
    }
    
    public boolean hasGetValueAddress() {
        return (this.bitField0_ & 0x40000000) == 0x40000000;
    }
    
    public GetValueAddress_Req getGetValueAddress() {
        return this.getValueAddress_;
    }
    
    public GetValueAddress_ReqOrBuilder getGetValueAddressOrBuilder() {
        return this.getValueAddress_;
    }
    
    public boolean hasHandleSignal() {
        return (this.bitField0_ & Integer.MIN_VALUE) == Integer.MIN_VALUE;
    }
    
    public HandleSignal_Req getHandleSignal() {
        return this.handleSignal_;
    }
    
    public HandleSignal_ReqOrBuilder getHandleSignalOrBuilder() {
        return this.handleSignal_;
    }
    
    public boolean hasExecuteShellCommand() {
        return (this.bitField1_ & 0x1) == 0x1;
    }
    
    public ExecuteShellCommand_Req getExecuteShellCommand() {
        return this.executeShellCommand_;
    }
    
    public ExecuteShellCommand_ReqOrBuilder getExecuteShellCommandOrBuilder() {
        return this.executeShellCommand_;
    }
    
    private void a() {
        this.createTarget_ = CreateTarget_Req.getDefaultInstance();
        this.launch_ = Launch_Req.getDefaultInstance();
        this.exit_ = Exit_Req.getDefaultInstance();
        this.continue_ = Continue_Req.getDefaultInstance();
        this.suspend_ = Suspend_Req.getDefaultInstance();
        this.getThreads_ = GetThreads_Req.getDefaultInstance();
        this.getFrames_ = GetFrames_Req.getDefaultInstance();
        this.addBreakpoint_ = AddBreakpoint_Req.getDefaultInstance();
        this.removeBreakpoint_ = RemoveBreakpoint_Req.getDefaultInstance();
        this.stepInto_ = StepInto_Req.getDefaultInstance();
        this.stepOver_ = StepOver_Req.getDefaultInstance();
        this.stepOut_ = StepOut_Req.getDefaultInstance();
        this.evaluateExpression_ = EvaluateExpression_Req.getDefaultInstance();
        this.getValueChildren_ = GetValueChildren_Req.getDefaultInstance();
        this.getVars_ = GetVars_Req.getDefaultInstance();
        this.handleConsoleCommand_ = HandleConsoleCommand_Req.getDefaultInstance();
        this.handleCompletion_ = HandleCompletion_Req.getDefaultInstance();
        this.attach_ = Attach_Req.getDefaultInstance();
        this.attachByName_ = AttachByName_Req.getDefaultInstance();
        this.dispatchInput_ = DispatchInput_Req.getDefaultInstance();
        this.addWatchpoint_ = AddWatchpoint_Req.getDefaultInstance();
        this.removeWatchpoint_ = RemoveWatchpoint_Req.getDefaultInstance();
        this.detach_ = Detach_Req.getDefaultInstance();
        this.kill_ = Kill_Req.getDefaultInstance();
        this.getChildrenCount_ = GetChildrenCount_Req.getDefaultInstance();
        this.getArraySlice_ = GetArraySlice_Req.getDefaultInstance();
        this.getValueData_ = GetValueData_Req.getDefaultInstance();
        this.getValueDescription_ = GetValueDescription_Req.getDefaultInstance();
        this.valuesFilteringPolicy_ = ValuesFilteringPolicy_Req.getDefaultInstance();
        this.connectPlatform_ = ConnectPlatform_Req.getDefaultInstance();
        this.getValueAddress_ = GetValueAddress_Req.getDefaultInstance();
        this.handleSignal_ = HandleSignal_Req.getDefaultInstance();
        this.executeShellCommand_ = ExecuteShellCommand_Req.getDefaultInstance();
    }
    
    public final boolean isInitialized() {
        final byte memoizedIsInitialized = this.memoizedIsInitialized;
        if (memoizedIsInitialized != -1) {
            return memoizedIsInitialized == 1;
        }
        if (this.hasLaunch() && !this.getLaunch().isInitialized()) {
            this.memoizedIsInitialized = 0;
            return false;
        }
        if (this.hasGetFrames() && !this.getGetFrames().isInitialized()) {
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
                codedOutputStream.writeMessage(3, (MessageLite)this.exit_);
            }
        }
        catch (IOException ex3) {
            throw a(ex3);
        }
        try {
            if ((this.bitField0_ & 0x8) == 0x8) {
                codedOutputStream.writeMessage(4, (MessageLite)this.continue_);
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
            n += CodedOutputStream.computeMessageSize(3, (MessageLite)this.exit_);
        }
        if ((this.bitField0_ & 0x8) == 0x8) {
            n += CodedOutputStream.computeMessageSize(4, (MessageLite)this.continue_);
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
    
    public static CompositeRequest parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
        return (CompositeRequest)CompositeRequest.PARSER.parseFrom(byteString);
    }
    
    public static CompositeRequest parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CompositeRequest)CompositeRequest.PARSER.parseFrom(byteString, extensionRegistryLite);
    }
    
    public static CompositeRequest parseFrom(final byte[] array) throws InvalidProtocolBufferException {
        return (CompositeRequest)CompositeRequest.PARSER.parseFrom(array);
    }
    
    public static CompositeRequest parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CompositeRequest)CompositeRequest.PARSER.parseFrom(array, extensionRegistryLite);
    }
    
    public static CompositeRequest parseFrom(final InputStream inputStream) throws IOException {
        return (CompositeRequest)CompositeRequest.PARSER.parseFrom(inputStream);
    }
    
    public static CompositeRequest parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CompositeRequest)CompositeRequest.PARSER.parseFrom(inputStream, extensionRegistryLite);
    }
    
    public static CompositeRequest parseDelimitedFrom(final InputStream inputStream) throws IOException {
        return (CompositeRequest)CompositeRequest.PARSER.parseDelimitedFrom(inputStream);
    }
    
    public static CompositeRequest parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CompositeRequest)CompositeRequest.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
    }
    
    public static CompositeRequest parseFrom(final CodedInputStream codedInputStream) throws IOException {
        return (CompositeRequest)CompositeRequest.PARSER.parseFrom(codedInputStream);
    }
    
    public static CompositeRequest parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CompositeRequest)CompositeRequest.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
    }
    
    public static Builder newBuilder() {
        return s();
    }
    
    public Builder newBuilderForType() {
        return newBuilder();
    }
    
    public static Builder newBuilder(final CompositeRequest compositeRequest) {
        return newBuilder().mergeFrom(compositeRequest);
    }
    
    public Builder toBuilder() {
        return newBuilder(this);
    }
    
    protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
        return new Builder(builderParent);
    }
    
    static {
        CompositeRequest.PARSER = (Parser<CompositeRequest>)new AbstractParser<CompositeRequest>() {
            public CompositeRequest parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new CompositeRequest(codedInputStream, extensionRegistryLite);
            }
        };
        (defaultInstance = new CompositeRequest(true)).a();
    }
    
    private static IOException a(final IOException ex) {
        return ex;
    }
    
    public static final class Builder extends GeneratedMessage.Builder<Builder> implements CompositeRequestOrBuilder
    {
        private int bitField0_;
        private int bitField1_;
        private CreateTarget_Req createTarget_;
        private SingleFieldBuilder<CreateTarget_Req, CreateTarget_Req.Builder, CreateTarget_ReqOrBuilder> createTargetBuilder_;
        private Launch_Req launch_;
        private SingleFieldBuilder<Launch_Req, Launch_Req.Builder, Launch_ReqOrBuilder> launchBuilder_;
        private Exit_Req exit_;
        private SingleFieldBuilder<Exit_Req, Exit_Req.Builder, Exit_ReqOrBuilder> exitBuilder_;
        private Continue_Req continue_;
        private SingleFieldBuilder<Continue_Req, Continue_Req.Builder, Continue_ReqOrBuilder> continueBuilder_;
        private Suspend_Req suspend_;
        private SingleFieldBuilder<Suspend_Req, Suspend_Req.Builder, Suspend_ReqOrBuilder> suspendBuilder_;
        private GetThreads_Req getThreads_;
        private SingleFieldBuilder<GetThreads_Req, GetThreads_Req.Builder, GetThreads_ReqOrBuilder> getThreadsBuilder_;
        private GetFrames_Req getFrames_;
        private SingleFieldBuilder<GetFrames_Req, GetFrames_Req.Builder, GetFrames_ReqOrBuilder> getFramesBuilder_;
        private AddBreakpoint_Req addBreakpoint_;
        private SingleFieldBuilder<AddBreakpoint_Req, AddBreakpoint_Req.Builder, AddBreakpoint_ReqOrBuilder> addBreakpointBuilder_;
        private RemoveBreakpoint_Req removeBreakpoint_;
        private SingleFieldBuilder<RemoveBreakpoint_Req, RemoveBreakpoint_Req.Builder, RemoveBreakpoint_ReqOrBuilder> removeBreakpointBuilder_;
        private StepInto_Req stepInto_;
        private SingleFieldBuilder<StepInto_Req, StepInto_Req.Builder, StepInto_ReqOrBuilder> stepIntoBuilder_;
        private StepOver_Req stepOver_;
        private SingleFieldBuilder<StepOver_Req, StepOver_Req.Builder, StepOver_ReqOrBuilder> stepOverBuilder_;
        private StepOut_Req stepOut_;
        private SingleFieldBuilder<StepOut_Req, StepOut_Req.Builder, StepOut_ReqOrBuilder> stepOutBuilder_;
        private EvaluateExpression_Req evaluateExpression_;
        private SingleFieldBuilder<EvaluateExpression_Req, EvaluateExpression_Req.Builder, EvaluateExpression_ReqOrBuilder> evaluateExpressionBuilder_;
        private GetValueChildren_Req getValueChildren_;
        private SingleFieldBuilder<GetValueChildren_Req, GetValueChildren_Req.Builder, GetValueChildren_ReqOrBuilder> getValueChildrenBuilder_;
        private GetVars_Req getVars_;
        private SingleFieldBuilder<GetVars_Req, GetVars_Req.Builder, GetVars_ReqOrBuilder> getVarsBuilder_;
        private HandleConsoleCommand_Req handleConsoleCommand_;
        private SingleFieldBuilder<HandleConsoleCommand_Req, HandleConsoleCommand_Req.Builder, HandleConsoleCommand_ReqOrBuilder> handleConsoleCommandBuilder_;
        private HandleCompletion_Req handleCompletion_;
        private SingleFieldBuilder<HandleCompletion_Req, HandleCompletion_Req.Builder, HandleCompletion_ReqOrBuilder> handleCompletionBuilder_;
        private Attach_Req attach_;
        private SingleFieldBuilder<Attach_Req, Attach_Req.Builder, Attach_ReqOrBuilder> attachBuilder_;
        private AttachByName_Req attachByName_;
        private SingleFieldBuilder<AttachByName_Req, AttachByName_Req.Builder, AttachByName_ReqOrBuilder> attachByNameBuilder_;
        private DispatchInput_Req dispatchInput_;
        private SingleFieldBuilder<DispatchInput_Req, DispatchInput_Req.Builder, DispatchInput_ReqOrBuilder> dispatchInputBuilder_;
        private AddWatchpoint_Req addWatchpoint_;
        private SingleFieldBuilder<AddWatchpoint_Req, AddWatchpoint_Req.Builder, AddWatchpoint_ReqOrBuilder> addWatchpointBuilder_;
        private RemoveWatchpoint_Req removeWatchpoint_;
        private SingleFieldBuilder<RemoveWatchpoint_Req, RemoveWatchpoint_Req.Builder, RemoveWatchpoint_ReqOrBuilder> removeWatchpointBuilder_;
        private Detach_Req detach_;
        private SingleFieldBuilder<Detach_Req, Detach_Req.Builder, Detach_ReqOrBuilder> detachBuilder_;
        private Kill_Req kill_;
        private SingleFieldBuilder<Kill_Req, Kill_Req.Builder, Kill_ReqOrBuilder> killBuilder_;
        private GetChildrenCount_Req getChildrenCount_;
        private SingleFieldBuilder<GetChildrenCount_Req, GetChildrenCount_Req.Builder, GetChildrenCount_ReqOrBuilder> getChildrenCountBuilder_;
        private GetArraySlice_Req getArraySlice_;
        private SingleFieldBuilder<GetArraySlice_Req, GetArraySlice_Req.Builder, GetArraySlice_ReqOrBuilder> getArraySliceBuilder_;
        private GetValueData_Req getValueData_;
        private SingleFieldBuilder<GetValueData_Req, GetValueData_Req.Builder, GetValueData_ReqOrBuilder> getValueDataBuilder_;
        private GetValueDescription_Req getValueDescription_;
        private SingleFieldBuilder<GetValueDescription_Req, GetValueDescription_Req.Builder, GetValueDescription_ReqOrBuilder> getValueDescriptionBuilder_;
        private ValuesFilteringPolicy_Req valuesFilteringPolicy_;
        private SingleFieldBuilder<ValuesFilteringPolicy_Req, ValuesFilteringPolicy_Req.Builder, ValuesFilteringPolicy_ReqOrBuilder> valuesFilteringPolicyBuilder_;
        private ConnectPlatform_Req connectPlatform_;
        private SingleFieldBuilder<ConnectPlatform_Req, ConnectPlatform_Req.Builder, ConnectPlatform_ReqOrBuilder> connectPlatformBuilder_;
        private GetValueAddress_Req getValueAddress_;
        private SingleFieldBuilder<GetValueAddress_Req, GetValueAddress_Req.Builder, GetValueAddress_ReqOrBuilder> getValueAddressBuilder_;
        private HandleSignal_Req handleSignal_;
        private SingleFieldBuilder<HandleSignal_Req, HandleSignal_Req.Builder, HandleSignal_ReqOrBuilder> handleSignalBuilder_;
        private ExecuteShellCommand_Req executeShellCommand_;
        private SingleFieldBuilder<ExecuteShellCommand_Req, ExecuteShellCommand_Req.Builder, ExecuteShellCommand_ReqOrBuilder> executeShellCommandBuilder_;
        
        public static final Descriptors.Descriptor getDescriptor() {
            return Protocol.access$65200();
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Protocol.access$65300().ensureFieldAccessorsInitialized((Class)CompositeRequest.class, (Class)Builder.class);
        }
        
        private Builder() {
            this.createTarget_ = CreateTarget_Req.getDefaultInstance();
            this.launch_ = Launch_Req.getDefaultInstance();
            this.exit_ = Exit_Req.getDefaultInstance();
            this.continue_ = Continue_Req.getDefaultInstance();
            this.suspend_ = Suspend_Req.getDefaultInstance();
            this.getThreads_ = GetThreads_Req.getDefaultInstance();
            this.getFrames_ = GetFrames_Req.getDefaultInstance();
            this.addBreakpoint_ = AddBreakpoint_Req.getDefaultInstance();
            this.removeBreakpoint_ = RemoveBreakpoint_Req.getDefaultInstance();
            this.stepInto_ = StepInto_Req.getDefaultInstance();
            this.stepOver_ = StepOver_Req.getDefaultInstance();
            this.stepOut_ = StepOut_Req.getDefaultInstance();
            this.evaluateExpression_ = EvaluateExpression_Req.getDefaultInstance();
            this.getValueChildren_ = GetValueChildren_Req.getDefaultInstance();
            this.getVars_ = GetVars_Req.getDefaultInstance();
            this.handleConsoleCommand_ = HandleConsoleCommand_Req.getDefaultInstance();
            this.handleCompletion_ = HandleCompletion_Req.getDefaultInstance();
            this.attach_ = Attach_Req.getDefaultInstance();
            this.attachByName_ = AttachByName_Req.getDefaultInstance();
            this.dispatchInput_ = DispatchInput_Req.getDefaultInstance();
            this.addWatchpoint_ = AddWatchpoint_Req.getDefaultInstance();
            this.removeWatchpoint_ = RemoveWatchpoint_Req.getDefaultInstance();
            this.detach_ = Detach_Req.getDefaultInstance();
            this.kill_ = Kill_Req.getDefaultInstance();
            this.getChildrenCount_ = GetChildrenCount_Req.getDefaultInstance();
            this.getArraySlice_ = GetArraySlice_Req.getDefaultInstance();
            this.getValueData_ = GetValueData_Req.getDefaultInstance();
            this.getValueDescription_ = GetValueDescription_Req.getDefaultInstance();
            this.valuesFilteringPolicy_ = ValuesFilteringPolicy_Req.getDefaultInstance();
            this.connectPlatform_ = ConnectPlatform_Req.getDefaultInstance();
            this.getValueAddress_ = GetValueAddress_Req.getDefaultInstance();
            this.handleSignal_ = HandleSignal_Req.getDefaultInstance();
            this.executeShellCommand_ = ExecuteShellCommand_Req.getDefaultInstance();
            this.g();
        }
        
        private Builder(final GeneratedMessage.BuilderParent builderParent) {
            super(builderParent);
            this.createTarget_ = CreateTarget_Req.getDefaultInstance();
            this.launch_ = Launch_Req.getDefaultInstance();
            this.exit_ = Exit_Req.getDefaultInstance();
            this.continue_ = Continue_Req.getDefaultInstance();
            this.suspend_ = Suspend_Req.getDefaultInstance();
            this.getThreads_ = GetThreads_Req.getDefaultInstance();
            this.getFrames_ = GetFrames_Req.getDefaultInstance();
            this.addBreakpoint_ = AddBreakpoint_Req.getDefaultInstance();
            this.removeBreakpoint_ = RemoveBreakpoint_Req.getDefaultInstance();
            this.stepInto_ = StepInto_Req.getDefaultInstance();
            this.stepOver_ = StepOver_Req.getDefaultInstance();
            this.stepOut_ = StepOut_Req.getDefaultInstance();
            this.evaluateExpression_ = EvaluateExpression_Req.getDefaultInstance();
            this.getValueChildren_ = GetValueChildren_Req.getDefaultInstance();
            this.getVars_ = GetVars_Req.getDefaultInstance();
            this.handleConsoleCommand_ = HandleConsoleCommand_Req.getDefaultInstance();
            this.handleCompletion_ = HandleCompletion_Req.getDefaultInstance();
            this.attach_ = Attach_Req.getDefaultInstance();
            this.attachByName_ = AttachByName_Req.getDefaultInstance();
            this.dispatchInput_ = DispatchInput_Req.getDefaultInstance();
            this.addWatchpoint_ = AddWatchpoint_Req.getDefaultInstance();
            this.removeWatchpoint_ = RemoveWatchpoint_Req.getDefaultInstance();
            this.detach_ = Detach_Req.getDefaultInstance();
            this.kill_ = Kill_Req.getDefaultInstance();
            this.getChildrenCount_ = GetChildrenCount_Req.getDefaultInstance();
            this.getArraySlice_ = GetArraySlice_Req.getDefaultInstance();
            this.getValueData_ = GetValueData_Req.getDefaultInstance();
            this.getValueDescription_ = GetValueDescription_Req.getDefaultInstance();
            this.valuesFilteringPolicy_ = ValuesFilteringPolicy_Req.getDefaultInstance();
            this.connectPlatform_ = ConnectPlatform_Req.getDefaultInstance();
            this.getValueAddress_ = GetValueAddress_Req.getDefaultInstance();
            this.handleSignal_ = HandleSignal_Req.getDefaultInstance();
            this.executeShellCommand_ = ExecuteShellCommand_Req.getDefaultInstance();
            this.g();
        }
        
        private void g() {
            try {
                if (CompositeRequest.alwaysUseFieldBuilders) {
                    this.i();
                    this.o();
                    this.E();
                    this.b();
                    this.h();
                    this.F();
                    this.f();
                    this.u();
                    this.m();
                    this.H();
                    this.n();
                    this.l();
                    this.G();
                    this.C();
                    this.e();
                    this.c();
                    this.I();
                    this.z();
                    this.d();
                    this.w();
                    this.v();
                    this.a();
                    this.k();
                    this.q();
                    this.t();
                    this.p();
                    this.r();
                    this.A();
                    this.y();
                    this.j();
                    this.x();
                    this.D();
                    this.B();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
        }
        
        private static Builder s() {
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
                                                                                                                                                        this.createTarget_ = CreateTarget_Req.getDefaultInstance();
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
                                                                                                                                                        this.launch_ = Launch_Req.getDefaultInstance();
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
                                                                                                                                                if (this.exitBuilder_ == null) {
                                                                                                                                                    this.exit_ = Exit_Req.getDefaultInstance();
                                                                                                                                                    break Label_0114;
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                            catch (NullPointerException ex3) {
                                                                                                                                                throw b(ex3);
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                        this.exitBuilder_.clear();
                                                                                                                                        try {
                                                                                                                                            this.bitField0_ &= 0xFFFFFFFB;
                                                                                                                                            if (this.continueBuilder_ == null) {
                                                                                                                                                this.continue_ = Continue_Req.getDefaultInstance();
                                                                                                                                                break Label_0154;
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                        catch (NullPointerException ex4) {
                                                                                                                                            throw b(ex4);
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                    this.continueBuilder_.clear();
                                                                                                                                    try {
                                                                                                                                        this.bitField0_ &= 0xFFFFFFF7;
                                                                                                                                        if (this.suspendBuilder_ == null) {
                                                                                                                                            this.suspend_ = Suspend_Req.getDefaultInstance();
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
                                                                                                                                        this.getThreads_ = GetThreads_Req.getDefaultInstance();
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
                                                                                                                                    this.getFrames_ = GetFrames_Req.getDefaultInstance();
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
                                                                                                                                this.addBreakpoint_ = AddBreakpoint_Req.getDefaultInstance();
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
                                                                                                                            this.removeBreakpoint_ = RemoveBreakpoint_Req.getDefaultInstance();
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
                                                                                                                        this.stepInto_ = StepInto_Req.getDefaultInstance();
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
                                                                                                                    this.stepOver_ = StepOver_Req.getDefaultInstance();
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
                                                                                                                this.stepOut_ = StepOut_Req.getDefaultInstance();
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
                                                                                                            this.evaluateExpression_ = EvaluateExpression_Req.getDefaultInstance();
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
                                                                                                        this.getValueChildren_ = GetValueChildren_Req.getDefaultInstance();
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
                                                                                                    this.getVars_ = GetVars_Req.getDefaultInstance();
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
                                                                                                this.handleConsoleCommand_ = HandleConsoleCommand_Req.getDefaultInstance();
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
                                                                                            this.handleCompletion_ = HandleCompletion_Req.getDefaultInstance();
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
                                                                                        this.attach_ = Attach_Req.getDefaultInstance();
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
                                                                                    this.attachByName_ = AttachByName_Req.getDefaultInstance();
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
                                                                                this.dispatchInput_ = DispatchInput_Req.getDefaultInstance();
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
                                                                            this.addWatchpoint_ = AddWatchpoint_Req.getDefaultInstance();
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
                                                                        this.removeWatchpoint_ = RemoveWatchpoint_Req.getDefaultInstance();
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
                                                                    this.detach_ = Detach_Req.getDefaultInstance();
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
                                                                this.kill_ = Kill_Req.getDefaultInstance();
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
                                                            this.getChildrenCount_ = GetChildrenCount_Req.getDefaultInstance();
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
                                                        this.getArraySlice_ = GetArraySlice_Req.getDefaultInstance();
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
                                                    this.getValueData_ = GetValueData_Req.getDefaultInstance();
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
                                                this.getValueDescription_ = GetValueDescription_Req.getDefaultInstance();
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
                                            this.valuesFilteringPolicy_ = ValuesFilteringPolicy_Req.getDefaultInstance();
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
                                        this.connectPlatform_ = ConnectPlatform_Req.getDefaultInstance();
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
                                    this.getValueAddress_ = GetValueAddress_Req.getDefaultInstance();
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
                                this.handleSignal_ = HandleSignal_Req.getDefaultInstance();
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
                            this.executeShellCommand_ = ExecuteShellCommand_Req.getDefaultInstance();
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
            return s().mergeFrom(this.buildPartial());
        }
        
        public Descriptors.Descriptor getDescriptorForType() {
            return Protocol.access$65200();
        }
        
        public CompositeRequest getDefaultInstanceForType() {
            return CompositeRequest.getDefaultInstance();
        }
        
        public CompositeRequest build() {
            final CompositeRequest buildPartial = this.buildPartial();
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
        
        public CompositeRequest buildPartial() {
            final CompositeRequest compositeRequest = new CompositeRequest((GeneratedMessage.Builder)this);
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
                        compositeRequest.createTarget_ = this.createTarget_;
                        break Label_0077;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                compositeRequest.createTarget_ = (CreateTarget_Req)this.createTargetBuilder_.build();
            }
            if ((bitField0_ & 0x2) == 0x2) {
                n |= 0x2;
            }
            Label_0128: {
                try {
                    if (this.launchBuilder_ == null) {
                        compositeRequest.launch_ = this.launch_;
                        break Label_0128;
                    }
                }
                catch (NullPointerException ex2) {
                    throw b(ex2);
                }
                compositeRequest.launch_ = (Launch_Req)this.launchBuilder_.build();
            }
            if ((bitField0_ & 0x4) == 0x4) {
                n |= 0x4;
            }
            Label_0179: {
                try {
                    if (this.exitBuilder_ == null) {
                        compositeRequest.exit_ = this.exit_;
                        break Label_0179;
                    }
                }
                catch (NullPointerException ex3) {
                    throw b(ex3);
                }
                compositeRequest.exit_ = (Exit_Req)this.exitBuilder_.build();
            }
            if ((bitField0_ & 0x8) == 0x8) {
                n |= 0x8;
            }
            Label_0233: {
                try {
                    if (this.continueBuilder_ == null) {
                        compositeRequest.continue_ = this.continue_;
                        break Label_0233;
                    }
                }
                catch (NullPointerException ex4) {
                    throw b(ex4);
                }
                compositeRequest.continue_ = (Continue_Req)this.continueBuilder_.build();
            }
            if ((bitField0_ & 0x10) == 0x10) {
                n |= 0x10;
            }
            Label_0287: {
                try {
                    if (this.suspendBuilder_ == null) {
                        compositeRequest.suspend_ = this.suspend_;
                        break Label_0287;
                    }
                }
                catch (NullPointerException ex5) {
                    throw b(ex5);
                }
                compositeRequest.suspend_ = (Suspend_Req)this.suspendBuilder_.build();
            }
            if ((bitField0_ & 0x20) == 0x20) {
                n |= 0x20;
            }
            Label_0341: {
                try {
                    if (this.getThreadsBuilder_ == null) {
                        compositeRequest.getThreads_ = this.getThreads_;
                        break Label_0341;
                    }
                }
                catch (NullPointerException ex6) {
                    throw b(ex6);
                }
                compositeRequest.getThreads_ = (GetThreads_Req)this.getThreadsBuilder_.build();
            }
            if ((bitField0_ & 0x40) == 0x40) {
                n |= 0x40;
            }
            Label_0395: {
                try {
                    if (this.getFramesBuilder_ == null) {
                        compositeRequest.getFrames_ = this.getFrames_;
                        break Label_0395;
                    }
                }
                catch (NullPointerException ex7) {
                    throw b(ex7);
                }
                compositeRequest.getFrames_ = (GetFrames_Req)this.getFramesBuilder_.build();
            }
            if ((bitField0_ & 0x80) == 0x80) {
                n |= 0x80;
            }
            Label_0452: {
                try {
                    if (this.addBreakpointBuilder_ == null) {
                        compositeRequest.addBreakpoint_ = this.addBreakpoint_;
                        break Label_0452;
                    }
                }
                catch (NullPointerException ex8) {
                    throw b(ex8);
                }
                compositeRequest.addBreakpoint_ = (AddBreakpoint_Req)this.addBreakpointBuilder_.build();
            }
            if ((bitField0_ & 0x100) == 0x100) {
                n |= 0x100;
            }
            Label_0509: {
                try {
                    if (this.removeBreakpointBuilder_ == null) {
                        compositeRequest.removeBreakpoint_ = this.removeBreakpoint_;
                        break Label_0509;
                    }
                }
                catch (NullPointerException ex9) {
                    throw b(ex9);
                }
                compositeRequest.removeBreakpoint_ = (RemoveBreakpoint_Req)this.removeBreakpointBuilder_.build();
            }
            if ((bitField0_ & 0x200) == 0x200) {
                n |= 0x200;
            }
            Label_0566: {
                try {
                    if (this.stepIntoBuilder_ == null) {
                        compositeRequest.stepInto_ = this.stepInto_;
                        break Label_0566;
                    }
                }
                catch (NullPointerException ex10) {
                    throw b(ex10);
                }
                compositeRequest.stepInto_ = (StepInto_Req)this.stepIntoBuilder_.build();
            }
            if ((bitField0_ & 0x400) == 0x400) {
                n |= 0x400;
            }
            Label_0623: {
                try {
                    if (this.stepOverBuilder_ == null) {
                        compositeRequest.stepOver_ = this.stepOver_;
                        break Label_0623;
                    }
                }
                catch (NullPointerException ex11) {
                    throw b(ex11);
                }
                compositeRequest.stepOver_ = (StepOver_Req)this.stepOverBuilder_.build();
            }
            if ((bitField0_ & 0x800) == 0x800) {
                n |= 0x800;
            }
            Label_0680: {
                try {
                    if (this.stepOutBuilder_ == null) {
                        compositeRequest.stepOut_ = this.stepOut_;
                        break Label_0680;
                    }
                }
                catch (NullPointerException ex12) {
                    throw b(ex12);
                }
                compositeRequest.stepOut_ = (StepOut_Req)this.stepOutBuilder_.build();
            }
            if ((bitField0_ & 0x1000) == 0x1000) {
                n |= 0x1000;
            }
            Label_0737: {
                try {
                    if (this.evaluateExpressionBuilder_ == null) {
                        compositeRequest.evaluateExpression_ = this.evaluateExpression_;
                        break Label_0737;
                    }
                }
                catch (NullPointerException ex13) {
                    throw b(ex13);
                }
                compositeRequest.evaluateExpression_ = (EvaluateExpression_Req)this.evaluateExpressionBuilder_.build();
            }
            if ((bitField0_ & 0x2000) == 0x2000) {
                n |= 0x2000;
            }
            Label_0794: {
                try {
                    if (this.getValueChildrenBuilder_ == null) {
                        compositeRequest.getValueChildren_ = this.getValueChildren_;
                        break Label_0794;
                    }
                }
                catch (NullPointerException ex14) {
                    throw b(ex14);
                }
                compositeRequest.getValueChildren_ = (GetValueChildren_Req)this.getValueChildrenBuilder_.build();
            }
            if ((bitField0_ & 0x4000) == 0x4000) {
                n |= 0x4000;
            }
            Label_0851: {
                try {
                    if (this.getVarsBuilder_ == null) {
                        compositeRequest.getVars_ = this.getVars_;
                        break Label_0851;
                    }
                }
                catch (NullPointerException ex15) {
                    throw b(ex15);
                }
                compositeRequest.getVars_ = (GetVars_Req)this.getVarsBuilder_.build();
            }
            if ((bitField0_ & 0x8000) == 0x8000) {
                n |= 0x8000;
            }
            Label_0905: {
                try {
                    if (this.handleConsoleCommandBuilder_ == null) {
                        compositeRequest.handleConsoleCommand_ = this.handleConsoleCommand_;
                        break Label_0905;
                    }
                }
                catch (NullPointerException ex16) {
                    throw b(ex16);
                }
                compositeRequest.handleConsoleCommand_ = (HandleConsoleCommand_Req)this.handleConsoleCommandBuilder_.build();
            }
            if ((bitField0_ & 0x10000) == 0x10000) {
                n |= 0x10000;
            }
            Label_0959: {
                try {
                    if (this.handleCompletionBuilder_ == null) {
                        compositeRequest.handleCompletion_ = this.handleCompletion_;
                        break Label_0959;
                    }
                }
                catch (NullPointerException ex17) {
                    throw b(ex17);
                }
                compositeRequest.handleCompletion_ = (HandleCompletion_Req)this.handleCompletionBuilder_.build();
            }
            if ((bitField0_ & 0x20000) == 0x20000) {
                n |= 0x20000;
            }
            Label_1013: {
                try {
                    if (this.attachBuilder_ == null) {
                        compositeRequest.attach_ = this.attach_;
                        break Label_1013;
                    }
                }
                catch (NullPointerException ex18) {
                    throw b(ex18);
                }
                compositeRequest.attach_ = (Attach_Req)this.attachBuilder_.build();
            }
            if ((bitField0_ & 0x40000) == 0x40000) {
                n |= 0x40000;
            }
            Label_1067: {
                try {
                    if (this.attachByNameBuilder_ == null) {
                        compositeRequest.attachByName_ = this.attachByName_;
                        break Label_1067;
                    }
                }
                catch (NullPointerException ex19) {
                    throw b(ex19);
                }
                compositeRequest.attachByName_ = (AttachByName_Req)this.attachByNameBuilder_.build();
            }
            if ((bitField0_ & 0x80000) == 0x80000) {
                n |= 0x80000;
            }
            Label_1121: {
                try {
                    if (this.dispatchInputBuilder_ == null) {
                        compositeRequest.dispatchInput_ = this.dispatchInput_;
                        break Label_1121;
                    }
                }
                catch (NullPointerException ex20) {
                    throw b(ex20);
                }
                compositeRequest.dispatchInput_ = (DispatchInput_Req)this.dispatchInputBuilder_.build();
            }
            if ((bitField0_ & 0x100000) == 0x100000) {
                n |= 0x100000;
            }
            Label_1175: {
                try {
                    if (this.addWatchpointBuilder_ == null) {
                        compositeRequest.addWatchpoint_ = this.addWatchpoint_;
                        break Label_1175;
                    }
                }
                catch (NullPointerException ex21) {
                    throw b(ex21);
                }
                compositeRequest.addWatchpoint_ = (AddWatchpoint_Req)this.addWatchpointBuilder_.build();
            }
            if ((bitField0_ & 0x200000) == 0x200000) {
                n |= 0x200000;
            }
            Label_1229: {
                try {
                    if (this.removeWatchpointBuilder_ == null) {
                        compositeRequest.removeWatchpoint_ = this.removeWatchpoint_;
                        break Label_1229;
                    }
                }
                catch (NullPointerException ex22) {
                    throw b(ex22);
                }
                compositeRequest.removeWatchpoint_ = (RemoveWatchpoint_Req)this.removeWatchpointBuilder_.build();
            }
            if ((bitField0_ & 0x400000) == 0x400000) {
                n |= 0x400000;
            }
            Label_1283: {
                try {
                    if (this.detachBuilder_ == null) {
                        compositeRequest.detach_ = this.detach_;
                        break Label_1283;
                    }
                }
                catch (NullPointerException ex23) {
                    throw b(ex23);
                }
                compositeRequest.detach_ = (Detach_Req)this.detachBuilder_.build();
            }
            if ((bitField0_ & 0x800000) == 0x800000) {
                n |= 0x800000;
            }
            Label_1337: {
                try {
                    if (this.killBuilder_ == null) {
                        compositeRequest.kill_ = this.kill_;
                        break Label_1337;
                    }
                }
                catch (NullPointerException ex24) {
                    throw b(ex24);
                }
                compositeRequest.kill_ = (Kill_Req)this.killBuilder_.build();
            }
            if ((bitField0_ & 0x1000000) == 0x1000000) {
                n |= 0x1000000;
            }
            Label_1391: {
                try {
                    if (this.getChildrenCountBuilder_ == null) {
                        compositeRequest.getChildrenCount_ = this.getChildrenCount_;
                        break Label_1391;
                    }
                }
                catch (NullPointerException ex25) {
                    throw b(ex25);
                }
                compositeRequest.getChildrenCount_ = (GetChildrenCount_Req)this.getChildrenCountBuilder_.build();
            }
            if ((bitField0_ & 0x2000000) == 0x2000000) {
                n |= 0x2000000;
            }
            Label_1445: {
                try {
                    if (this.getArraySliceBuilder_ == null) {
                        compositeRequest.getArraySlice_ = this.getArraySlice_;
                        break Label_1445;
                    }
                }
                catch (NullPointerException ex26) {
                    throw b(ex26);
                }
                compositeRequest.getArraySlice_ = (GetArraySlice_Req)this.getArraySliceBuilder_.build();
            }
            if ((bitField0_ & 0x4000000) == 0x4000000) {
                n |= 0x4000000;
            }
            Label_1499: {
                try {
                    if (this.getValueDataBuilder_ == null) {
                        compositeRequest.getValueData_ = this.getValueData_;
                        break Label_1499;
                    }
                }
                catch (NullPointerException ex27) {
                    throw b(ex27);
                }
                compositeRequest.getValueData_ = (GetValueData_Req)this.getValueDataBuilder_.build();
            }
            if ((bitField0_ & 0x8000000) == 0x8000000) {
                n |= 0x8000000;
            }
            Label_1553: {
                try {
                    if (this.getValueDescriptionBuilder_ == null) {
                        compositeRequest.getValueDescription_ = this.getValueDescription_;
                        break Label_1553;
                    }
                }
                catch (NullPointerException ex28) {
                    throw b(ex28);
                }
                compositeRequest.getValueDescription_ = (GetValueDescription_Req)this.getValueDescriptionBuilder_.build();
            }
            if ((bitField0_ & 0x10000000) == 0x10000000) {
                n |= 0x10000000;
            }
            Label_1607: {
                try {
                    if (this.valuesFilteringPolicyBuilder_ == null) {
                        compositeRequest.valuesFilteringPolicy_ = this.valuesFilteringPolicy_;
                        break Label_1607;
                    }
                }
                catch (NullPointerException ex29) {
                    throw b(ex29);
                }
                compositeRequest.valuesFilteringPolicy_ = (ValuesFilteringPolicy_Req)this.valuesFilteringPolicyBuilder_.build();
            }
            if ((bitField0_ & 0x20000000) == 0x20000000) {
                n |= 0x20000000;
            }
            Label_1661: {
                try {
                    if (this.connectPlatformBuilder_ == null) {
                        compositeRequest.connectPlatform_ = this.connectPlatform_;
                        break Label_1661;
                    }
                }
                catch (NullPointerException ex30) {
                    throw b(ex30);
                }
                compositeRequest.connectPlatform_ = (ConnectPlatform_Req)this.connectPlatformBuilder_.build();
            }
            if ((bitField0_ & 0x40000000) == 0x40000000) {
                n |= 0x40000000;
            }
            Label_1715: {
                try {
                    if (this.getValueAddressBuilder_ == null) {
                        compositeRequest.getValueAddress_ = this.getValueAddress_;
                        break Label_1715;
                    }
                }
                catch (NullPointerException ex31) {
                    throw b(ex31);
                }
                compositeRequest.getValueAddress_ = (GetValueAddress_Req)this.getValueAddressBuilder_.build();
            }
            if ((bitField0_ & Integer.MIN_VALUE) == Integer.MIN_VALUE) {
                n |= Integer.MIN_VALUE;
            }
            Label_1769: {
                try {
                    if (this.handleSignalBuilder_ == null) {
                        compositeRequest.handleSignal_ = this.handleSignal_;
                        break Label_1769;
                    }
                }
                catch (NullPointerException ex32) {
                    throw b(ex32);
                }
                compositeRequest.handleSignal_ = (HandleSignal_Req)this.handleSignalBuilder_.build();
            }
            if ((bitField1_ & 0x1) == 0x1) {
                n2 |= 0x1;
            }
            Label_1820: {
                try {
                    if (this.executeShellCommandBuilder_ == null) {
                        compositeRequest.executeShellCommand_ = this.executeShellCommand_;
                        break Label_1820;
                    }
                }
                catch (NullPointerException ex33) {
                    throw b(ex33);
                }
                compositeRequest.executeShellCommand_ = (ExecuteShellCommand_Req)this.executeShellCommandBuilder_.build();
            }
            compositeRequest.bitField0_ = n;
            compositeRequest.bitField1_ = n2;
            this.onBuilt();
            return compositeRequest;
        }
        
        public Builder mergeFrom(final Message message) {
            try {
                if (message instanceof CompositeRequest) {
                    return this.mergeFrom((CompositeRequest)message);
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            super.mergeFrom(message);
            return this;
        }
        
        public Builder mergeFrom(final CompositeRequest compositeRequest) {
            try {
                if (compositeRequest == CompositeRequest.getDefaultInstance()) {
                    return this;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            try {
                if (compositeRequest.hasCreateTarget()) {
                    this.mergeCreateTarget(compositeRequest.getCreateTarget());
                }
            }
            catch (NullPointerException ex2) {
                throw b(ex2);
            }
            try {
                if (compositeRequest.hasLaunch()) {
                    this.mergeLaunch(compositeRequest.getLaunch());
                }
            }
            catch (NullPointerException ex3) {
                throw b(ex3);
            }
            try {
                if (compositeRequest.hasExit()) {
                    this.mergeExit(compositeRequest.getExit());
                }
            }
            catch (NullPointerException ex4) {
                throw b(ex4);
            }
            try {
                if (compositeRequest.hasContinue()) {
                    this.mergeContinue(compositeRequest.getContinue());
                }
            }
            catch (NullPointerException ex5) {
                throw b(ex5);
            }
            try {
                if (compositeRequest.hasSuspend()) {
                    this.mergeSuspend(compositeRequest.getSuspend());
                }
            }
            catch (NullPointerException ex6) {
                throw b(ex6);
            }
            try {
                if (compositeRequest.hasGetThreads()) {
                    this.mergeGetThreads(compositeRequest.getGetThreads());
                }
            }
            catch (NullPointerException ex7) {
                throw b(ex7);
            }
            try {
                if (compositeRequest.hasGetFrames()) {
                    this.mergeGetFrames(compositeRequest.getGetFrames());
                }
            }
            catch (NullPointerException ex8) {
                throw b(ex8);
            }
            try {
                if (compositeRequest.hasAddBreakpoint()) {
                    this.mergeAddBreakpoint(compositeRequest.getAddBreakpoint());
                }
            }
            catch (NullPointerException ex9) {
                throw b(ex9);
            }
            try {
                if (compositeRequest.hasRemoveBreakpoint()) {
                    this.mergeRemoveBreakpoint(compositeRequest.getRemoveBreakpoint());
                }
            }
            catch (NullPointerException ex10) {
                throw b(ex10);
            }
            try {
                if (compositeRequest.hasStepInto()) {
                    this.mergeStepInto(compositeRequest.getStepInto());
                }
            }
            catch (NullPointerException ex11) {
                throw b(ex11);
            }
            try {
                if (compositeRequest.hasStepOver()) {
                    this.mergeStepOver(compositeRequest.getStepOver());
                }
            }
            catch (NullPointerException ex12) {
                throw b(ex12);
            }
            try {
                if (compositeRequest.hasStepOut()) {
                    this.mergeStepOut(compositeRequest.getStepOut());
                }
            }
            catch (NullPointerException ex13) {
                throw b(ex13);
            }
            try {
                if (compositeRequest.hasEvaluateExpression()) {
                    this.mergeEvaluateExpression(compositeRequest.getEvaluateExpression());
                }
            }
            catch (NullPointerException ex14) {
                throw b(ex14);
            }
            try {
                if (compositeRequest.hasGetValueChildren()) {
                    this.mergeGetValueChildren(compositeRequest.getGetValueChildren());
                }
            }
            catch (NullPointerException ex15) {
                throw b(ex15);
            }
            try {
                if (compositeRequest.hasGetVars()) {
                    this.mergeGetVars(compositeRequest.getGetVars());
                }
            }
            catch (NullPointerException ex16) {
                throw b(ex16);
            }
            try {
                if (compositeRequest.hasHandleConsoleCommand()) {
                    this.mergeHandleConsoleCommand(compositeRequest.getHandleConsoleCommand());
                }
            }
            catch (NullPointerException ex17) {
                throw b(ex17);
            }
            try {
                if (compositeRequest.hasHandleCompletion()) {
                    this.mergeHandleCompletion(compositeRequest.getHandleCompletion());
                }
            }
            catch (NullPointerException ex18) {
                throw b(ex18);
            }
            try {
                if (compositeRequest.hasAttach()) {
                    this.mergeAttach(compositeRequest.getAttach());
                }
            }
            catch (NullPointerException ex19) {
                throw b(ex19);
            }
            try {
                if (compositeRequest.hasAttachByName()) {
                    this.mergeAttachByName(compositeRequest.getAttachByName());
                }
            }
            catch (NullPointerException ex20) {
                throw b(ex20);
            }
            try {
                if (compositeRequest.hasDispatchInput()) {
                    this.mergeDispatchInput(compositeRequest.getDispatchInput());
                }
            }
            catch (NullPointerException ex21) {
                throw b(ex21);
            }
            try {
                if (compositeRequest.hasAddWatchpoint()) {
                    this.mergeAddWatchpoint(compositeRequest.getAddWatchpoint());
                }
            }
            catch (NullPointerException ex22) {
                throw b(ex22);
            }
            try {
                if (compositeRequest.hasRemoveWatchpoint()) {
                    this.mergeRemoveWatchpoint(compositeRequest.getRemoveWatchpoint());
                }
            }
            catch (NullPointerException ex23) {
                throw b(ex23);
            }
            try {
                if (compositeRequest.hasDetach()) {
                    this.mergeDetach(compositeRequest.getDetach());
                }
            }
            catch (NullPointerException ex24) {
                throw b(ex24);
            }
            try {
                if (compositeRequest.hasKill()) {
                    this.mergeKill(compositeRequest.getKill());
                }
            }
            catch (NullPointerException ex25) {
                throw b(ex25);
            }
            try {
                if (compositeRequest.hasGetChildrenCount()) {
                    this.mergeGetChildrenCount(compositeRequest.getGetChildrenCount());
                }
            }
            catch (NullPointerException ex26) {
                throw b(ex26);
            }
            try {
                if (compositeRequest.hasGetArraySlice()) {
                    this.mergeGetArraySlice(compositeRequest.getGetArraySlice());
                }
            }
            catch (NullPointerException ex27) {
                throw b(ex27);
            }
            try {
                if (compositeRequest.hasGetValueData()) {
                    this.mergeGetValueData(compositeRequest.getGetValueData());
                }
            }
            catch (NullPointerException ex28) {
                throw b(ex28);
            }
            try {
                if (compositeRequest.hasGetValueDescription()) {
                    this.mergeGetValueDescription(compositeRequest.getGetValueDescription());
                }
            }
            catch (NullPointerException ex29) {
                throw b(ex29);
            }
            try {
                if (compositeRequest.hasValuesFilteringPolicy()) {
                    this.mergeValuesFilteringPolicy(compositeRequest.getValuesFilteringPolicy());
                }
            }
            catch (NullPointerException ex30) {
                throw b(ex30);
            }
            try {
                if (compositeRequest.hasConnectPlatform()) {
                    this.mergeConnectPlatform(compositeRequest.getConnectPlatform());
                }
            }
            catch (NullPointerException ex31) {
                throw b(ex31);
            }
            try {
                if (compositeRequest.hasGetValueAddress()) {
                    this.mergeGetValueAddress(compositeRequest.getGetValueAddress());
                }
            }
            catch (NullPointerException ex32) {
                throw b(ex32);
            }
            try {
                if (compositeRequest.hasHandleSignal()) {
                    this.mergeHandleSignal(compositeRequest.getHandleSignal());
                }
            }
            catch (NullPointerException ex33) {
                throw b(ex33);
            }
            try {
                if (compositeRequest.hasExecuteShellCommand()) {
                    this.mergeExecuteShellCommand(compositeRequest.getExecuteShellCommand());
                }
            }
            catch (NullPointerException ex34) {
                throw b(ex34);
            }
            this.mergeUnknownFields(compositeRequest.getUnknownFields());
            return this;
        }
        
        public final boolean isInitialized() {
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
                                                                                                                if (!this.hasLaunch()) {
                                                                                                                    break Label_0030;
                                                                                                                }
                                                                                                                final Builder builder = this;
                                                                                                                final Launch_Req launch_Req = builder.getLaunch();
                                                                                                                final boolean b = launch_Req.isInitialized();
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
                                                                                                                final Launch_Req launch_Req = builder.getLaunch();
                                                                                                                final boolean b = launch_Req.isInitialized();
                                                                                                                if (!b) {
                                                                                                                    return false;
                                                                                                                }
                                                                                                            }
                                                                                                            catch (NullPointerException ex2) {
                                                                                                                throw b(ex2);
                                                                                                            }
                                                                                                            try {
                                                                                                                if (!this.hasGetFrames()) {
                                                                                                                    break Label_0060;
                                                                                                                }
                                                                                                                final Builder builder2 = this;
                                                                                                                final GetFrames_Req getFrames_Req = builder2.getGetFrames();
                                                                                                                final boolean b2 = getFrames_Req.isInitialized();
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
                                                                                                            final GetFrames_Req getFrames_Req = builder2.getGetFrames();
                                                                                                            final boolean b2 = getFrames_Req.isInitialized();
                                                                                                            if (!b2) {
                                                                                                                return false;
                                                                                                            }
                                                                                                        }
                                                                                                        catch (NullPointerException ex4) {
                                                                                                            throw b(ex4);
                                                                                                        }
                                                                                                        try {
                                                                                                            if (!this.hasRemoveBreakpoint()) {
                                                                                                                break Label_0090;
                                                                                                            }
                                                                                                            final Builder builder3 = this;
                                                                                                            final RemoveBreakpoint_Req removeBreakpoint_Req = builder3.getRemoveBreakpoint();
                                                                                                            final boolean b3 = removeBreakpoint_Req.isInitialized();
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
                                                                                                        final RemoveBreakpoint_Req removeBreakpoint_Req = builder3.getRemoveBreakpoint();
                                                                                                        final boolean b3 = removeBreakpoint_Req.isInitialized();
                                                                                                        if (!b3) {
                                                                                                            return false;
                                                                                                        }
                                                                                                    }
                                                                                                    catch (NullPointerException ex6) {
                                                                                                        throw b(ex6);
                                                                                                    }
                                                                                                    try {
                                                                                                        if (!this.hasStepInto()) {
                                                                                                            break Label_0120;
                                                                                                        }
                                                                                                        final Builder builder4 = this;
                                                                                                        final StepInto_Req stepInto_Req = builder4.getStepInto();
                                                                                                        final boolean b4 = stepInto_Req.isInitialized();
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
                                                                                                    final StepInto_Req stepInto_Req = builder4.getStepInto();
                                                                                                    final boolean b4 = stepInto_Req.isInitialized();
                                                                                                    if (!b4) {
                                                                                                        return false;
                                                                                                    }
                                                                                                }
                                                                                                catch (NullPointerException ex8) {
                                                                                                    throw b(ex8);
                                                                                                }
                                                                                                try {
                                                                                                    if (!this.hasStepOver()) {
                                                                                                        break Label_0150;
                                                                                                    }
                                                                                                    final Builder builder5 = this;
                                                                                                    final StepOver_Req stepOver_Req = builder5.getStepOver();
                                                                                                    final boolean b5 = stepOver_Req.isInitialized();
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
                                                                                                final StepOver_Req stepOver_Req = builder5.getStepOver();
                                                                                                final boolean b5 = stepOver_Req.isInitialized();
                                                                                                if (!b5) {
                                                                                                    return false;
                                                                                                }
                                                                                            }
                                                                                            catch (NullPointerException ex10) {
                                                                                                throw b(ex10);
                                                                                            }
                                                                                            try {
                                                                                                if (!this.hasStepOut()) {
                                                                                                    break Label_0180;
                                                                                                }
                                                                                                final Builder builder6 = this;
                                                                                                final StepOut_Req stepOut_Req = builder6.getStepOut();
                                                                                                final boolean b6 = stepOut_Req.isInitialized();
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
                                                                                            final StepOut_Req stepOut_Req = builder6.getStepOut();
                                                                                            final boolean b6 = stepOut_Req.isInitialized();
                                                                                            if (!b6) {
                                                                                                return false;
                                                                                            }
                                                                                        }
                                                                                        catch (NullPointerException ex12) {
                                                                                            throw b(ex12);
                                                                                        }
                                                                                        try {
                                                                                            if (!this.hasEvaluateExpression()) {
                                                                                                break Label_0210;
                                                                                            }
                                                                                            final Builder builder7 = this;
                                                                                            final EvaluateExpression_Req evaluateExpression_Req = builder7.getEvaluateExpression();
                                                                                            final boolean b7 = evaluateExpression_Req.isInitialized();
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
                                                                                        final EvaluateExpression_Req evaluateExpression_Req = builder7.getEvaluateExpression();
                                                                                        final boolean b7 = evaluateExpression_Req.isInitialized();
                                                                                        if (!b7) {
                                                                                            return false;
                                                                                        }
                                                                                    }
                                                                                    catch (NullPointerException ex14) {
                                                                                        throw b(ex14);
                                                                                    }
                                                                                    try {
                                                                                        if (!this.hasGetValueChildren()) {
                                                                                            break Label_0240;
                                                                                        }
                                                                                        final Builder builder8 = this;
                                                                                        final GetValueChildren_Req getValueChildren_Req = builder8.getGetValueChildren();
                                                                                        final boolean b8 = getValueChildren_Req.isInitialized();
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
                                                                                    final GetValueChildren_Req getValueChildren_Req = builder8.getGetValueChildren();
                                                                                    final boolean b8 = getValueChildren_Req.isInitialized();
                                                                                    if (!b8) {
                                                                                        return false;
                                                                                    }
                                                                                }
                                                                                catch (NullPointerException ex16) {
                                                                                    throw b(ex16);
                                                                                }
                                                                                try {
                                                                                    if (!this.hasGetVars()) {
                                                                                        break Label_0270;
                                                                                    }
                                                                                    final Builder builder9 = this;
                                                                                    final GetVars_Req getVars_Req = builder9.getGetVars();
                                                                                    final boolean b9 = getVars_Req.isInitialized();
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
                                                                                final GetVars_Req getVars_Req = builder9.getGetVars();
                                                                                final boolean b9 = getVars_Req.isInitialized();
                                                                                if (!b9) {
                                                                                    return false;
                                                                                }
                                                                            }
                                                                            catch (NullPointerException ex18) {
                                                                                throw b(ex18);
                                                                            }
                                                                            try {
                                                                                if (!this.hasHandleConsoleCommand()) {
                                                                                    break Label_0300;
                                                                                }
                                                                                final Builder builder10 = this;
                                                                                final HandleConsoleCommand_Req handleConsoleCommand_Req = builder10.getHandleConsoleCommand();
                                                                                final boolean b10 = handleConsoleCommand_Req.isInitialized();
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
                                                                            final HandleConsoleCommand_Req handleConsoleCommand_Req = builder10.getHandleConsoleCommand();
                                                                            final boolean b10 = handleConsoleCommand_Req.isInitialized();
                                                                            if (!b10) {
                                                                                return false;
                                                                            }
                                                                        }
                                                                        catch (NullPointerException ex20) {
                                                                            throw b(ex20);
                                                                        }
                                                                        try {
                                                                            if (!this.hasHandleCompletion()) {
                                                                                break Label_0330;
                                                                            }
                                                                            final Builder builder11 = this;
                                                                            final HandleCompletion_Req handleCompletion_Req = builder11.getHandleCompletion();
                                                                            final boolean b11 = handleCompletion_Req.isInitialized();
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
                                                                        final HandleCompletion_Req handleCompletion_Req = builder11.getHandleCompletion();
                                                                        final boolean b11 = handleCompletion_Req.isInitialized();
                                                                        if (!b11) {
                                                                            return false;
                                                                        }
                                                                    }
                                                                    catch (NullPointerException ex22) {
                                                                        throw b(ex22);
                                                                    }
                                                                    try {
                                                                        if (!this.hasAttach()) {
                                                                            break Label_0360;
                                                                        }
                                                                        final Builder builder12 = this;
                                                                        final Attach_Req attach_Req = builder12.getAttach();
                                                                        final boolean b12 = attach_Req.isInitialized();
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
                                                                    final Attach_Req attach_Req = builder12.getAttach();
                                                                    final boolean b12 = attach_Req.isInitialized();
                                                                    if (!b12) {
                                                                        return false;
                                                                    }
                                                                }
                                                                catch (NullPointerException ex24) {
                                                                    throw b(ex24);
                                                                }
                                                                try {
                                                                    if (!this.hasAttachByName()) {
                                                                        break Label_0390;
                                                                    }
                                                                    final Builder builder13 = this;
                                                                    final AttachByName_Req attachByName_Req = builder13.getAttachByName();
                                                                    final boolean b13 = attachByName_Req.isInitialized();
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
                                                                final AttachByName_Req attachByName_Req = builder13.getAttachByName();
                                                                final boolean b13 = attachByName_Req.isInitialized();
                                                                if (!b13) {
                                                                    return false;
                                                                }
                                                            }
                                                            catch (NullPointerException ex26) {
                                                                throw b(ex26);
                                                            }
                                                            try {
                                                                if (!this.hasDispatchInput()) {
                                                                    break Label_0420;
                                                                }
                                                                final Builder builder14 = this;
                                                                final DispatchInput_Req dispatchInput_Req = builder14.getDispatchInput();
                                                                final boolean b14 = dispatchInput_Req.isInitialized();
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
                                                            final DispatchInput_Req dispatchInput_Req = builder14.getDispatchInput();
                                                            final boolean b14 = dispatchInput_Req.isInitialized();
                                                            if (!b14) {
                                                                return false;
                                                            }
                                                        }
                                                        catch (NullPointerException ex28) {
                                                            throw b(ex28);
                                                        }
                                                        try {
                                                            if (!this.hasAddWatchpoint()) {
                                                                break Label_0450;
                                                            }
                                                            final Builder builder15 = this;
                                                            final AddWatchpoint_Req addWatchpoint_Req = builder15.getAddWatchpoint();
                                                            final boolean b15 = addWatchpoint_Req.isInitialized();
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
                                                        final AddWatchpoint_Req addWatchpoint_Req = builder15.getAddWatchpoint();
                                                        final boolean b15 = addWatchpoint_Req.isInitialized();
                                                        if (!b15) {
                                                            return false;
                                                        }
                                                    }
                                                    catch (NullPointerException ex30) {
                                                        throw b(ex30);
                                                    }
                                                    try {
                                                        if (!this.hasRemoveWatchpoint()) {
                                                            break Label_0480;
                                                        }
                                                        final Builder builder16 = this;
                                                        final RemoveWatchpoint_Req removeWatchpoint_Req = builder16.getRemoveWatchpoint();
                                                        final boolean b16 = removeWatchpoint_Req.isInitialized();
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
                                                    final RemoveWatchpoint_Req removeWatchpoint_Req = builder16.getRemoveWatchpoint();
                                                    final boolean b16 = removeWatchpoint_Req.isInitialized();
                                                    if (!b16) {
                                                        return false;
                                                    }
                                                }
                                                catch (NullPointerException ex32) {
                                                    throw b(ex32);
                                                }
                                                try {
                                                    if (!this.hasGetChildrenCount()) {
                                                        break Label_0510;
                                                    }
                                                    final Builder builder17 = this;
                                                    final GetChildrenCount_Req getChildrenCount_Req = builder17.getGetChildrenCount();
                                                    final boolean b17 = getChildrenCount_Req.isInitialized();
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
                                                final GetChildrenCount_Req getChildrenCount_Req = builder17.getGetChildrenCount();
                                                final boolean b17 = getChildrenCount_Req.isInitialized();
                                                if (!b17) {
                                                    return false;
                                                }
                                            }
                                            catch (NullPointerException ex34) {
                                                throw b(ex34);
                                            }
                                            try {
                                                if (!this.hasGetArraySlice()) {
                                                    break Label_0540;
                                                }
                                                final Builder builder18 = this;
                                                final GetArraySlice_Req getArraySlice_Req = builder18.getGetArraySlice();
                                                final boolean b18 = getArraySlice_Req.isInitialized();
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
                                            final GetArraySlice_Req getArraySlice_Req = builder18.getGetArraySlice();
                                            final boolean b18 = getArraySlice_Req.isInitialized();
                                            if (!b18) {
                                                return false;
                                            }
                                        }
                                        catch (NullPointerException ex36) {
                                            throw b(ex36);
                                        }
                                        try {
                                            if (!this.hasGetValueData()) {
                                                break Label_0570;
                                            }
                                            final Builder builder19 = this;
                                            final GetValueData_Req getValueData_Req = builder19.getGetValueData();
                                            final boolean b19 = getValueData_Req.isInitialized();
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
                                        final GetValueData_Req getValueData_Req = builder19.getGetValueData();
                                        final boolean b19 = getValueData_Req.isInitialized();
                                        if (!b19) {
                                            return false;
                                        }
                                    }
                                    catch (NullPointerException ex38) {
                                        throw b(ex38);
                                    }
                                    try {
                                        if (!this.hasGetValueDescription()) {
                                            break Label_0600;
                                        }
                                        final Builder builder20 = this;
                                        final GetValueDescription_Req getValueDescription_Req = builder20.getGetValueDescription();
                                        final boolean b20 = getValueDescription_Req.isInitialized();
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
                                    final GetValueDescription_Req getValueDescription_Req = builder20.getGetValueDescription();
                                    final boolean b20 = getValueDescription_Req.isInitialized();
                                    if (!b20) {
                                        return false;
                                    }
                                }
                                catch (NullPointerException ex40) {
                                    throw b(ex40);
                                }
                                try {
                                    if (!this.hasValuesFilteringPolicy()) {
                                        break Label_0630;
                                    }
                                    final Builder builder21 = this;
                                    final ValuesFilteringPolicy_Req valuesFilteringPolicy_Req = builder21.getValuesFilteringPolicy();
                                    final boolean b21 = valuesFilteringPolicy_Req.isInitialized();
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
                                final ValuesFilteringPolicy_Req valuesFilteringPolicy_Req = builder21.getValuesFilteringPolicy();
                                final boolean b21 = valuesFilteringPolicy_Req.isInitialized();
                                if (!b21) {
                                    return false;
                                }
                            }
                            catch (NullPointerException ex42) {
                                throw b(ex42);
                            }
                            try {
                                if (!this.hasConnectPlatform()) {
                                    break Label_0660;
                                }
                                final Builder builder22 = this;
                                final ConnectPlatform_Req connectPlatform_Req = builder22.getConnectPlatform();
                                final boolean b22 = connectPlatform_Req.isInitialized();
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
                            final ConnectPlatform_Req connectPlatform_Req = builder22.getConnectPlatform();
                            final boolean b22 = connectPlatform_Req.isInitialized();
                            if (!b22) {
                                return false;
                            }
                        }
                        catch (NullPointerException ex44) {
                            throw b(ex44);
                        }
                        try {
                            if (!this.hasGetValueAddress()) {
                                break Label_0690;
                            }
                            final Builder builder23 = this;
                            final GetValueAddress_Req getValueAddress_Req = builder23.getGetValueAddress();
                            final boolean b23 = getValueAddress_Req.isInitialized();
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
                        final GetValueAddress_Req getValueAddress_Req = builder23.getGetValueAddress();
                        final boolean b23 = getValueAddress_Req.isInitialized();
                        if (!b23) {
                            return false;
                        }
                    }
                    catch (NullPointerException ex46) {
                        throw b(ex46);
                    }
                    try {
                        if (!this.hasHandleSignal()) {
                            break Label_0720;
                        }
                        final Builder builder24 = this;
                        final HandleSignal_Req handleSignal_Req = builder24.getHandleSignal();
                        final boolean b24 = handleSignal_Req.isInitialized();
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
                    final HandleSignal_Req handleSignal_Req = builder24.getHandleSignal();
                    final boolean b24 = handleSignal_Req.isInitialized();
                    if (!b24) {
                        return false;
                    }
                }
                catch (NullPointerException ex48) {
                    throw b(ex48);
                }
                try {
                    if (!this.hasExecuteShellCommand()) {
                        return true;
                    }
                    final Builder builder25 = this;
                    final ExecuteShellCommand_Req executeShellCommand_Req = builder25.getExecuteShellCommand();
                    final boolean b25 = executeShellCommand_Req.isInitialized();
                    if (!b25) {
                        return false;
                    }
                    return true;
                }
                catch (NullPointerException ex49) {
                    throw b(ex49);
                }
            }
            try {
                final Builder builder25 = this;
                final ExecuteShellCommand_Req executeShellCommand_Req = builder25.getExecuteShellCommand();
                final boolean b25 = executeShellCommand_Req.isInitialized();
                if (!b25) {
                    return false;
                }
            }
            catch (NullPointerException ex50) {
                throw b(ex50);
            }
            return true;
        }
        
        public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            CompositeRequest compositeRequest = null;
            try {
                compositeRequest = (CompositeRequest)CompositeRequest.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
            }
            catch (InvalidProtocolBufferException ex) {
                compositeRequest = (CompositeRequest)ex.getUnfinishedMessage();
                throw ex;
            }
            finally {
                try {
                    if (compositeRequest != null) {
                        this.mergeFrom(compositeRequest);
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
        
        public CreateTarget_Req getCreateTarget() {
            try {
                if (this.createTargetBuilder_ == null) {
                    return this.createTarget_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (CreateTarget_Req)this.createTargetBuilder_.getMessage();
        }
        
        public Builder setCreateTarget(final CreateTarget_Req createTarget_Req) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.createTargetBuilder_ != null) {
                                break Label_0042;
                            }
                            final CreateTarget_Req createTarget_Req2 = createTarget_Req;
                            if (createTarget_Req2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final CreateTarget_Req createTarget_Req2 = createTarget_Req;
                            if (createTarget_Req2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.createTarget_ = createTarget_Req;
                    this.onChanged();
                    break Label_0051;
                }
                this.createTargetBuilder_.setMessage((GeneratedMessage)createTarget_Req);
            }
            this.bitField0_ |= 0x1;
            return this;
        }
        
        public Builder setCreateTarget(final CreateTarget_Req.Builder builder) {
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
        
        public Builder mergeCreateTarget(final CreateTarget_Req p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.createTargetBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       78
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    11: iconst_1       
            //    12: iand           
            //    13: iconst_1       
            //    14: if_icmpne       66
            //    17: goto            24
            //    20: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    23: athrow         
            //    24: aload_0        
            //    25: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.createTarget_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CreateTarget_Req;
            //    28: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CreateTarget_Req.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CreateTarget_Req;
            //    31: if_acmpeq       66
            //    34: goto            41
            //    37: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    40: athrow         
            //    41: aload_0        
            //    42: aload_0        
            //    43: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.createTarget_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CreateTarget_Req;
            //    46: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CreateTarget_Req.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CreateTarget_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CreateTarget_Req$Builder;
            //    49: aload_1        
            //    50: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CreateTarget_Req$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CreateTarget_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CreateTarget_Req$Builder;
            //    53: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CreateTarget_Req$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CreateTarget_Req;
            //    56: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.createTarget_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CreateTarget_Req;
            //    59: goto            71
            //    62: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    65: athrow         
            //    66: aload_0        
            //    67: aload_1        
            //    68: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.createTarget_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CreateTarget_Req;
            //    71: aload_0        
            //    72: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.onChanged:()V
            //    75: goto            87
            //    78: aload_0        
            //    79: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.createTargetBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    82: aload_1        
            //    83: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    86: pop            
            //    87: aload_0        
            //    88: dup            
            //    89: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    92: iconst_1       
            //    93: ior            
            //    94: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
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
                        this.createTarget_ = CreateTarget_Req.getDefaultInstance();
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
        
        public CreateTarget_Req.Builder getCreateTargetBuilder() {
            this.bitField0_ |= 0x1;
            this.onChanged();
            return (CreateTarget_Req.Builder)this.i().getBuilder();
        }
        
        public CreateTarget_ReqOrBuilder getCreateTargetOrBuilder() {
            try {
                if (this.createTargetBuilder_ != null) {
                    return (CreateTarget_ReqOrBuilder)this.createTargetBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.createTarget_;
        }
        
        private SingleFieldBuilder<CreateTarget_Req, CreateTarget_Req.Builder, CreateTarget_ReqOrBuilder> i() {
            try {
                if (this.createTargetBuilder_ == null) {
                    this.createTargetBuilder_ = (SingleFieldBuilder<CreateTarget_Req, CreateTarget_Req.Builder, CreateTarget_ReqOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.createTarget_, this.getParentForChildren(), this.isClean());
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
        
        public Launch_Req getLaunch() {
            try {
                if (this.launchBuilder_ == null) {
                    return this.launch_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (Launch_Req)this.launchBuilder_.getMessage();
        }
        
        public Builder setLaunch(final Launch_Req launch_Req) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.launchBuilder_ != null) {
                                break Label_0042;
                            }
                            final Launch_Req launch_Req2 = launch_Req;
                            if (launch_Req2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final Launch_Req launch_Req2 = launch_Req;
                            if (launch_Req2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.launch_ = launch_Req;
                    this.onChanged();
                    break Label_0051;
                }
                this.launchBuilder_.setMessage((GeneratedMessage)launch_Req);
            }
            this.bitField0_ |= 0x2;
            return this;
        }
        
        public Builder setLaunch(final Launch_Req.Builder builder) {
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
        
        public Builder mergeLaunch(final Launch_Req p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.launchBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       78
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    11: iconst_2       
            //    12: iand           
            //    13: iconst_2       
            //    14: if_icmpne       66
            //    17: goto            24
            //    20: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    23: athrow         
            //    24: aload_0        
            //    25: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.launch_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Launch_Req;
            //    28: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Launch_Req.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Launch_Req;
            //    31: if_acmpeq       66
            //    34: goto            41
            //    37: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    40: athrow         
            //    41: aload_0        
            //    42: aload_0        
            //    43: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.launch_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Launch_Req;
            //    46: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Launch_Req.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Launch_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Launch_Req$Builder;
            //    49: aload_1        
            //    50: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Launch_Req$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Launch_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Launch_Req$Builder;
            //    53: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Launch_Req$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Launch_Req;
            //    56: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.launch_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Launch_Req;
            //    59: goto            71
            //    62: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    65: athrow         
            //    66: aload_0        
            //    67: aload_1        
            //    68: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.launch_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Launch_Req;
            //    71: aload_0        
            //    72: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.onChanged:()V
            //    75: goto            87
            //    78: aload_0        
            //    79: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.launchBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    82: aload_1        
            //    83: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    86: pop            
            //    87: aload_0        
            //    88: dup            
            //    89: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    92: iconst_2       
            //    93: ior            
            //    94: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
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
                        this.launch_ = Launch_Req.getDefaultInstance();
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
        
        public Launch_Req.Builder getLaunchBuilder() {
            this.bitField0_ |= 0x2;
            this.onChanged();
            return (Launch_Req.Builder)this.o().getBuilder();
        }
        
        public Launch_ReqOrBuilder getLaunchOrBuilder() {
            try {
                if (this.launchBuilder_ != null) {
                    return (Launch_ReqOrBuilder)this.launchBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.launch_;
        }
        
        private SingleFieldBuilder<Launch_Req, Launch_Req.Builder, Launch_ReqOrBuilder> o() {
            try {
                if (this.launchBuilder_ == null) {
                    this.launchBuilder_ = (SingleFieldBuilder<Launch_Req, Launch_Req.Builder, Launch_ReqOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.launch_, this.getParentForChildren(), this.isClean());
                    this.launch_ = null;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.launchBuilder_;
        }
        
        public boolean hasExit() {
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
        
        public Exit_Req getExit() {
            try {
                if (this.exitBuilder_ == null) {
                    return this.exit_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (Exit_Req)this.exitBuilder_.getMessage();
        }
        
        public Builder setExit(final Exit_Req exit_Req) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.exitBuilder_ != null) {
                                break Label_0042;
                            }
                            final Exit_Req exit_Req2 = exit_Req;
                            if (exit_Req2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final Exit_Req exit_Req2 = exit_Req;
                            if (exit_Req2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.exit_ = exit_Req;
                    this.onChanged();
                    break Label_0051;
                }
                this.exitBuilder_.setMessage((GeneratedMessage)exit_Req);
            }
            this.bitField0_ |= 0x4;
            return this;
        }
        
        public Builder setExit(final Exit_Req.Builder builder) {
            Label_0038: {
                try {
                    if (this.exitBuilder_ == null) {
                        this.exit_ = builder.build();
                        this.onChanged();
                        break Label_0038;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.exitBuilder_.setMessage((GeneratedMessage)builder.build());
            }
            this.bitField0_ |= 0x4;
            return this;
        }
        
        public Builder mergeExit(final Exit_Req p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.exitBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       78
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    11: iconst_4       
            //    12: iand           
            //    13: iconst_4       
            //    14: if_icmpne       66
            //    17: goto            24
            //    20: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    23: athrow         
            //    24: aload_0        
            //    25: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.exit_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Exit_Req;
            //    28: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Exit_Req.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Exit_Req;
            //    31: if_acmpeq       66
            //    34: goto            41
            //    37: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    40: athrow         
            //    41: aload_0        
            //    42: aload_0        
            //    43: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.exit_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Exit_Req;
            //    46: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Exit_Req.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Exit_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Exit_Req$Builder;
            //    49: aload_1        
            //    50: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Exit_Req$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Exit_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Exit_Req$Builder;
            //    53: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Exit_Req$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Exit_Req;
            //    56: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.exit_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Exit_Req;
            //    59: goto            71
            //    62: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    65: athrow         
            //    66: aload_0        
            //    67: aload_1        
            //    68: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.exit_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Exit_Req;
            //    71: aload_0        
            //    72: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.onChanged:()V
            //    75: goto            87
            //    78: aload_0        
            //    79: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.exitBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    82: aload_1        
            //    83: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    86: pop            
            //    87: aload_0        
            //    88: dup            
            //    89: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    92: iconst_4       
            //    93: ior            
            //    94: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
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
        
        public Builder clearExit() {
            Label_0033: {
                try {
                    if (this.exitBuilder_ == null) {
                        this.exit_ = Exit_Req.getDefaultInstance();
                        this.onChanged();
                        break Label_0033;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.exitBuilder_.clear();
            }
            this.bitField0_ &= 0xFFFFFFFB;
            return this;
        }
        
        public Exit_Req.Builder getExitBuilder() {
            this.bitField0_ |= 0x4;
            this.onChanged();
            return (Exit_Req.Builder)this.E().getBuilder();
        }
        
        public Exit_ReqOrBuilder getExitOrBuilder() {
            try {
                if (this.exitBuilder_ != null) {
                    return (Exit_ReqOrBuilder)this.exitBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.exit_;
        }
        
        private SingleFieldBuilder<Exit_Req, Exit_Req.Builder, Exit_ReqOrBuilder> E() {
            try {
                if (this.exitBuilder_ == null) {
                    this.exitBuilder_ = (SingleFieldBuilder<Exit_Req, Exit_Req.Builder, Exit_ReqOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.exit_, this.getParentForChildren(), this.isClean());
                    this.exit_ = null;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.exitBuilder_;
        }
        
        public boolean hasContinue() {
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
        
        public Continue_Req getContinue() {
            try {
                if (this.continueBuilder_ == null) {
                    return this.continue_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (Continue_Req)this.continueBuilder_.getMessage();
        }
        
        public Builder setContinue(final Continue_Req continue_Req) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.continueBuilder_ != null) {
                                break Label_0042;
                            }
                            final Continue_Req continue_Req2 = continue_Req;
                            if (continue_Req2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final Continue_Req continue_Req2 = continue_Req;
                            if (continue_Req2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.continue_ = continue_Req;
                    this.onChanged();
                    break Label_0051;
                }
                this.continueBuilder_.setMessage((GeneratedMessage)continue_Req);
            }
            this.bitField0_ |= 0x8;
            return this;
        }
        
        public Builder setContinue(final Continue_Req.Builder builder) {
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
            this.bitField0_ |= 0x8;
            return this;
        }
        
        public Builder mergeContinue(final Continue_Req p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.continueBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       80
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    11: bipush          8
            //    13: iand           
            //    14: bipush          8
            //    16: if_icmpne       68
            //    19: goto            26
            //    22: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    25: athrow         
            //    26: aload_0        
            //    27: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.continue_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Continue_Req;
            //    30: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Continue_Req.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Continue_Req;
            //    33: if_acmpeq       68
            //    36: goto            43
            //    39: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    42: athrow         
            //    43: aload_0        
            //    44: aload_0        
            //    45: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.continue_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Continue_Req;
            //    48: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Continue_Req.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Continue_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Continue_Req$Builder;
            //    51: aload_1        
            //    52: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Continue_Req$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Continue_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Continue_Req$Builder;
            //    55: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Continue_Req$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Continue_Req;
            //    58: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.continue_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Continue_Req;
            //    61: goto            73
            //    64: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    67: athrow         
            //    68: aload_0        
            //    69: aload_1        
            //    70: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.continue_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Continue_Req;
            //    73: aload_0        
            //    74: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.onChanged:()V
            //    77: goto            89
            //    80: aload_0        
            //    81: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.continueBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    84: aload_1        
            //    85: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    88: pop            
            //    89: aload_0        
            //    90: dup            
            //    91: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    94: bipush          8
            //    96: ior            
            //    97: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
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
        
        public Builder clearContinue() {
            Label_0033: {
                try {
                    if (this.continueBuilder_ == null) {
                        this.continue_ = Continue_Req.getDefaultInstance();
                        this.onChanged();
                        break Label_0033;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.continueBuilder_.clear();
            }
            this.bitField0_ &= 0xFFFFFFF7;
            return this;
        }
        
        public Continue_Req.Builder getContinueBuilder() {
            this.bitField0_ |= 0x8;
            this.onChanged();
            return (Continue_Req.Builder)this.b().getBuilder();
        }
        
        public Continue_ReqOrBuilder getContinueOrBuilder() {
            try {
                if (this.continueBuilder_ != null) {
                    return (Continue_ReqOrBuilder)this.continueBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.continue_;
        }
        
        private SingleFieldBuilder<Continue_Req, Continue_Req.Builder, Continue_ReqOrBuilder> b() {
            try {
                if (this.continueBuilder_ == null) {
                    this.continueBuilder_ = (SingleFieldBuilder<Continue_Req, Continue_Req.Builder, Continue_ReqOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.continue_, this.getParentForChildren(), this.isClean());
                    this.continue_ = null;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.continueBuilder_;
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
        
        public Suspend_Req getSuspend() {
            try {
                if (this.suspendBuilder_ == null) {
                    return this.suspend_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (Suspend_Req)this.suspendBuilder_.getMessage();
        }
        
        public Builder setSuspend(final Suspend_Req suspend_Req) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.suspendBuilder_ != null) {
                                break Label_0042;
                            }
                            final Suspend_Req suspend_Req2 = suspend_Req;
                            if (suspend_Req2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final Suspend_Req suspend_Req2 = suspend_Req;
                            if (suspend_Req2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.suspend_ = suspend_Req;
                    this.onChanged();
                    break Label_0051;
                }
                this.suspendBuilder_.setMessage((GeneratedMessage)suspend_Req);
            }
            this.bitField0_ |= 0x10;
            return this;
        }
        
        public Builder setSuspend(final Suspend_Req.Builder builder) {
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
        
        public Builder mergeSuspend(final Suspend_Req p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.suspendBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       80
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    11: bipush          16
            //    13: iand           
            //    14: bipush          16
            //    16: if_icmpne       68
            //    19: goto            26
            //    22: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    25: athrow         
            //    26: aload_0        
            //    27: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.suspend_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Suspend_Req;
            //    30: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Suspend_Req.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Suspend_Req;
            //    33: if_acmpeq       68
            //    36: goto            43
            //    39: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    42: athrow         
            //    43: aload_0        
            //    44: aload_0        
            //    45: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.suspend_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Suspend_Req;
            //    48: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Suspend_Req.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Suspend_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Suspend_Req$Builder;
            //    51: aload_1        
            //    52: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Suspend_Req$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Suspend_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Suspend_Req$Builder;
            //    55: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Suspend_Req$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Suspend_Req;
            //    58: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.suspend_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Suspend_Req;
            //    61: goto            73
            //    64: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    67: athrow         
            //    68: aload_0        
            //    69: aload_1        
            //    70: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.suspend_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Suspend_Req;
            //    73: aload_0        
            //    74: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.onChanged:()V
            //    77: goto            89
            //    80: aload_0        
            //    81: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.suspendBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    84: aload_1        
            //    85: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    88: pop            
            //    89: aload_0        
            //    90: dup            
            //    91: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    94: bipush          16
            //    96: ior            
            //    97: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
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
                        this.suspend_ = Suspend_Req.getDefaultInstance();
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
        
        public Suspend_Req.Builder getSuspendBuilder() {
            this.bitField0_ |= 0x10;
            this.onChanged();
            return (Suspend_Req.Builder)this.h().getBuilder();
        }
        
        public Suspend_ReqOrBuilder getSuspendOrBuilder() {
            try {
                if (this.suspendBuilder_ != null) {
                    return (Suspend_ReqOrBuilder)this.suspendBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.suspend_;
        }
        
        private SingleFieldBuilder<Suspend_Req, Suspend_Req.Builder, Suspend_ReqOrBuilder> h() {
            try {
                if (this.suspendBuilder_ == null) {
                    this.suspendBuilder_ = (SingleFieldBuilder<Suspend_Req, Suspend_Req.Builder, Suspend_ReqOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.suspend_, this.getParentForChildren(), this.isClean());
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
        
        public GetThreads_Req getGetThreads() {
            try {
                if (this.getThreadsBuilder_ == null) {
                    return this.getThreads_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (GetThreads_Req)this.getThreadsBuilder_.getMessage();
        }
        
        public Builder setGetThreads(final GetThreads_Req getThreads_Req) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.getThreadsBuilder_ != null) {
                                break Label_0042;
                            }
                            final GetThreads_Req getThreads_Req2 = getThreads_Req;
                            if (getThreads_Req2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final GetThreads_Req getThreads_Req2 = getThreads_Req;
                            if (getThreads_Req2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.getThreads_ = getThreads_Req;
                    this.onChanged();
                    break Label_0051;
                }
                this.getThreadsBuilder_.setMessage((GeneratedMessage)getThreads_Req);
            }
            this.bitField0_ |= 0x20;
            return this;
        }
        
        public Builder setGetThreads(final GetThreads_Req.Builder builder) {
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
        
        public Builder mergeGetThreads(final GetThreads_Req p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getThreadsBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       80
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    11: bipush          32
            //    13: iand           
            //    14: bipush          32
            //    16: if_icmpne       68
            //    19: goto            26
            //    22: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    25: athrow         
            //    26: aload_0        
            //    27: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getThreads_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Req;
            //    30: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Req.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Req;
            //    33: if_acmpeq       68
            //    36: goto            43
            //    39: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    42: athrow         
            //    43: aload_0        
            //    44: aload_0        
            //    45: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getThreads_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Req;
            //    48: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Req.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Req$Builder;
            //    51: aload_1        
            //    52: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Req$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Req$Builder;
            //    55: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Req$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Req;
            //    58: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getThreads_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Req;
            //    61: goto            73
            //    64: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    67: athrow         
            //    68: aload_0        
            //    69: aload_1        
            //    70: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getThreads_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetThreads_Req;
            //    73: aload_0        
            //    74: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.onChanged:()V
            //    77: goto            89
            //    80: aload_0        
            //    81: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getThreadsBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    84: aload_1        
            //    85: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    88: pop            
            //    89: aload_0        
            //    90: dup            
            //    91: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    94: bipush          32
            //    96: ior            
            //    97: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
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
                        this.getThreads_ = GetThreads_Req.getDefaultInstance();
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
        
        public GetThreads_Req.Builder getGetThreadsBuilder() {
            this.bitField0_ |= 0x20;
            this.onChanged();
            return (GetThreads_Req.Builder)this.F().getBuilder();
        }
        
        public GetThreads_ReqOrBuilder getGetThreadsOrBuilder() {
            try {
                if (this.getThreadsBuilder_ != null) {
                    return (GetThreads_ReqOrBuilder)this.getThreadsBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.getThreads_;
        }
        
        private SingleFieldBuilder<GetThreads_Req, GetThreads_Req.Builder, GetThreads_ReqOrBuilder> F() {
            try {
                if (this.getThreadsBuilder_ == null) {
                    this.getThreadsBuilder_ = (SingleFieldBuilder<GetThreads_Req, GetThreads_Req.Builder, GetThreads_ReqOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.getThreads_, this.getParentForChildren(), this.isClean());
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
        
        public GetFrames_Req getGetFrames() {
            try {
                if (this.getFramesBuilder_ == null) {
                    return this.getFrames_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (GetFrames_Req)this.getFramesBuilder_.getMessage();
        }
        
        public Builder setGetFrames(final GetFrames_Req getFrames_Req) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.getFramesBuilder_ != null) {
                                break Label_0042;
                            }
                            final GetFrames_Req getFrames_Req2 = getFrames_Req;
                            if (getFrames_Req2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final GetFrames_Req getFrames_Req2 = getFrames_Req;
                            if (getFrames_Req2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.getFrames_ = getFrames_Req;
                    this.onChanged();
                    break Label_0051;
                }
                this.getFramesBuilder_.setMessage((GeneratedMessage)getFrames_Req);
            }
            this.bitField0_ |= 0x40;
            return this;
        }
        
        public Builder setGetFrames(final GetFrames_Req.Builder builder) {
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
        
        public Builder mergeGetFrames(final GetFrames_Req p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getFramesBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       80
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    11: bipush          64
            //    13: iand           
            //    14: bipush          64
            //    16: if_icmpne       68
            //    19: goto            26
            //    22: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    25: athrow         
            //    26: aload_0        
            //    27: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getFrames_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetFrames_Req;
            //    30: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetFrames_Req.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetFrames_Req;
            //    33: if_acmpeq       68
            //    36: goto            43
            //    39: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    42: athrow         
            //    43: aload_0        
            //    44: aload_0        
            //    45: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getFrames_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetFrames_Req;
            //    48: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetFrames_Req.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetFrames_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetFrames_Req$Builder;
            //    51: aload_1        
            //    52: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetFrames_Req$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetFrames_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetFrames_Req$Builder;
            //    55: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetFrames_Req$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetFrames_Req;
            //    58: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getFrames_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetFrames_Req;
            //    61: goto            73
            //    64: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    67: athrow         
            //    68: aload_0        
            //    69: aload_1        
            //    70: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getFrames_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetFrames_Req;
            //    73: aload_0        
            //    74: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.onChanged:()V
            //    77: goto            89
            //    80: aload_0        
            //    81: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getFramesBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    84: aload_1        
            //    85: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    88: pop            
            //    89: aload_0        
            //    90: dup            
            //    91: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    94: bipush          64
            //    96: ior            
            //    97: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
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
                        this.getFrames_ = GetFrames_Req.getDefaultInstance();
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
        
        public GetFrames_Req.Builder getGetFramesBuilder() {
            this.bitField0_ |= 0x40;
            this.onChanged();
            return (GetFrames_Req.Builder)this.f().getBuilder();
        }
        
        public GetFrames_ReqOrBuilder getGetFramesOrBuilder() {
            try {
                if (this.getFramesBuilder_ != null) {
                    return (GetFrames_ReqOrBuilder)this.getFramesBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.getFrames_;
        }
        
        private SingleFieldBuilder<GetFrames_Req, GetFrames_Req.Builder, GetFrames_ReqOrBuilder> f() {
            try {
                if (this.getFramesBuilder_ == null) {
                    this.getFramesBuilder_ = (SingleFieldBuilder<GetFrames_Req, GetFrames_Req.Builder, GetFrames_ReqOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.getFrames_, this.getParentForChildren(), this.isClean());
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
        
        public AddBreakpoint_Req getAddBreakpoint() {
            try {
                if (this.addBreakpointBuilder_ == null) {
                    return this.addBreakpoint_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (AddBreakpoint_Req)this.addBreakpointBuilder_.getMessage();
        }
        
        public Builder setAddBreakpoint(final AddBreakpoint_Req addBreakpoint_Req) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.addBreakpointBuilder_ != null) {
                                break Label_0042;
                            }
                            final AddBreakpoint_Req addBreakpoint_Req2 = addBreakpoint_Req;
                            if (addBreakpoint_Req2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final AddBreakpoint_Req addBreakpoint_Req2 = addBreakpoint_Req;
                            if (addBreakpoint_Req2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.addBreakpoint_ = addBreakpoint_Req;
                    this.onChanged();
                    break Label_0051;
                }
                this.addBreakpointBuilder_.setMessage((GeneratedMessage)addBreakpoint_Req);
            }
            this.bitField0_ |= 0x80;
            return this;
        }
        
        public Builder setAddBreakpoint(final AddBreakpoint_Req.Builder builder) {
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
        
        public Builder mergeAddBreakpoint(final AddBreakpoint_Req p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.addBreakpointBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       82
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    11: sipush          128
            //    14: iand           
            //    15: sipush          128
            //    18: if_icmpne       70
            //    21: goto            28
            //    24: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    27: athrow         
            //    28: aload_0        
            //    29: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.addBreakpoint_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddBreakpoint_Req;
            //    32: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddBreakpoint_Req.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddBreakpoint_Req;
            //    35: if_acmpeq       70
            //    38: goto            45
            //    41: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    44: athrow         
            //    45: aload_0        
            //    46: aload_0        
            //    47: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.addBreakpoint_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddBreakpoint_Req;
            //    50: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddBreakpoint_Req.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddBreakpoint_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddBreakpoint_Req$Builder;
            //    53: aload_1        
            //    54: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddBreakpoint_Req$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddBreakpoint_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddBreakpoint_Req$Builder;
            //    57: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddBreakpoint_Req$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddBreakpoint_Req;
            //    60: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.addBreakpoint_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddBreakpoint_Req;
            //    63: goto            75
            //    66: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    69: athrow         
            //    70: aload_0        
            //    71: aload_1        
            //    72: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.addBreakpoint_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddBreakpoint_Req;
            //    75: aload_0        
            //    76: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.onChanged:()V
            //    79: goto            91
            //    82: aload_0        
            //    83: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.addBreakpointBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    86: aload_1        
            //    87: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    90: pop            
            //    91: aload_0        
            //    92: dup            
            //    93: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    96: sipush          128
            //    99: ior            
            //   100: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
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
                        this.addBreakpoint_ = AddBreakpoint_Req.getDefaultInstance();
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
        
        public AddBreakpoint_Req.Builder getAddBreakpointBuilder() {
            this.bitField0_ |= 0x80;
            this.onChanged();
            return (AddBreakpoint_Req.Builder)this.u().getBuilder();
        }
        
        public AddBreakpoint_ReqOrBuilder getAddBreakpointOrBuilder() {
            try {
                if (this.addBreakpointBuilder_ != null) {
                    return (AddBreakpoint_ReqOrBuilder)this.addBreakpointBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.addBreakpoint_;
        }
        
        private SingleFieldBuilder<AddBreakpoint_Req, AddBreakpoint_Req.Builder, AddBreakpoint_ReqOrBuilder> u() {
            try {
                if (this.addBreakpointBuilder_ == null) {
                    this.addBreakpointBuilder_ = (SingleFieldBuilder<AddBreakpoint_Req, AddBreakpoint_Req.Builder, AddBreakpoint_ReqOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.addBreakpoint_, this.getParentForChildren(), this.isClean());
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
        
        public RemoveBreakpoint_Req getRemoveBreakpoint() {
            try {
                if (this.removeBreakpointBuilder_ == null) {
                    return this.removeBreakpoint_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (RemoveBreakpoint_Req)this.removeBreakpointBuilder_.getMessage();
        }
        
        public Builder setRemoveBreakpoint(final RemoveBreakpoint_Req removeBreakpoint_Req) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.removeBreakpointBuilder_ != null) {
                                break Label_0042;
                            }
                            final RemoveBreakpoint_Req removeBreakpoint_Req2 = removeBreakpoint_Req;
                            if (removeBreakpoint_Req2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final RemoveBreakpoint_Req removeBreakpoint_Req2 = removeBreakpoint_Req;
                            if (removeBreakpoint_Req2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.removeBreakpoint_ = removeBreakpoint_Req;
                    this.onChanged();
                    break Label_0051;
                }
                this.removeBreakpointBuilder_.setMessage((GeneratedMessage)removeBreakpoint_Req);
            }
            this.bitField0_ |= 0x100;
            return this;
        }
        
        public Builder setRemoveBreakpoint(final RemoveBreakpoint_Req.Builder builder) {
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
        
        public Builder mergeRemoveBreakpoint(final RemoveBreakpoint_Req p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.removeBreakpointBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       82
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    11: sipush          256
            //    14: iand           
            //    15: sipush          256
            //    18: if_icmpne       70
            //    21: goto            28
            //    24: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    27: athrow         
            //    28: aload_0        
            //    29: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.removeBreakpoint_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveBreakpoint_Req;
            //    32: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveBreakpoint_Req.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveBreakpoint_Req;
            //    35: if_acmpeq       70
            //    38: goto            45
            //    41: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    44: athrow         
            //    45: aload_0        
            //    46: aload_0        
            //    47: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.removeBreakpoint_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveBreakpoint_Req;
            //    50: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveBreakpoint_Req.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveBreakpoint_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveBreakpoint_Req$Builder;
            //    53: aload_1        
            //    54: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveBreakpoint_Req$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveBreakpoint_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveBreakpoint_Req$Builder;
            //    57: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveBreakpoint_Req$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveBreakpoint_Req;
            //    60: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.removeBreakpoint_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveBreakpoint_Req;
            //    63: goto            75
            //    66: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    69: athrow         
            //    70: aload_0        
            //    71: aload_1        
            //    72: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.removeBreakpoint_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveBreakpoint_Req;
            //    75: aload_0        
            //    76: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.onChanged:()V
            //    79: goto            91
            //    82: aload_0        
            //    83: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.removeBreakpointBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    86: aload_1        
            //    87: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    90: pop            
            //    91: aload_0        
            //    92: dup            
            //    93: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    96: sipush          256
            //    99: ior            
            //   100: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
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
                        this.removeBreakpoint_ = RemoveBreakpoint_Req.getDefaultInstance();
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
        
        public RemoveBreakpoint_Req.Builder getRemoveBreakpointBuilder() {
            this.bitField0_ |= 0x100;
            this.onChanged();
            return (RemoveBreakpoint_Req.Builder)this.m().getBuilder();
        }
        
        public RemoveBreakpoint_ReqOrBuilder getRemoveBreakpointOrBuilder() {
            try {
                if (this.removeBreakpointBuilder_ != null) {
                    return (RemoveBreakpoint_ReqOrBuilder)this.removeBreakpointBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.removeBreakpoint_;
        }
        
        private SingleFieldBuilder<RemoveBreakpoint_Req, RemoveBreakpoint_Req.Builder, RemoveBreakpoint_ReqOrBuilder> m() {
            try {
                if (this.removeBreakpointBuilder_ == null) {
                    this.removeBreakpointBuilder_ = (SingleFieldBuilder<RemoveBreakpoint_Req, RemoveBreakpoint_Req.Builder, RemoveBreakpoint_ReqOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.removeBreakpoint_, this.getParentForChildren(), this.isClean());
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
        
        public StepInto_Req getStepInto() {
            try {
                if (this.stepIntoBuilder_ == null) {
                    return this.stepInto_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (StepInto_Req)this.stepIntoBuilder_.getMessage();
        }
        
        public Builder setStepInto(final StepInto_Req stepInto_Req) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.stepIntoBuilder_ != null) {
                                break Label_0042;
                            }
                            final StepInto_Req stepInto_Req2 = stepInto_Req;
                            if (stepInto_Req2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final StepInto_Req stepInto_Req2 = stepInto_Req;
                            if (stepInto_Req2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.stepInto_ = stepInto_Req;
                    this.onChanged();
                    break Label_0051;
                }
                this.stepIntoBuilder_.setMessage((GeneratedMessage)stepInto_Req);
            }
            this.bitField0_ |= 0x200;
            return this;
        }
        
        public Builder setStepInto(final StepInto_Req.Builder builder) {
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
        
        public Builder mergeStepInto(final StepInto_Req p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.stepIntoBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       82
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    11: sipush          512
            //    14: iand           
            //    15: sipush          512
            //    18: if_icmpne       70
            //    21: goto            28
            //    24: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    27: athrow         
            //    28: aload_0        
            //    29: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.stepInto_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepInto_Req;
            //    32: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepInto_Req.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepInto_Req;
            //    35: if_acmpeq       70
            //    38: goto            45
            //    41: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    44: athrow         
            //    45: aload_0        
            //    46: aload_0        
            //    47: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.stepInto_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepInto_Req;
            //    50: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepInto_Req.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepInto_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepInto_Req$Builder;
            //    53: aload_1        
            //    54: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepInto_Req$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepInto_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepInto_Req$Builder;
            //    57: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepInto_Req$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepInto_Req;
            //    60: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.stepInto_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepInto_Req;
            //    63: goto            75
            //    66: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    69: athrow         
            //    70: aload_0        
            //    71: aload_1        
            //    72: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.stepInto_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepInto_Req;
            //    75: aload_0        
            //    76: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.onChanged:()V
            //    79: goto            91
            //    82: aload_0        
            //    83: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.stepIntoBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    86: aload_1        
            //    87: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    90: pop            
            //    91: aload_0        
            //    92: dup            
            //    93: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    96: sipush          512
            //    99: ior            
            //   100: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
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
                        this.stepInto_ = StepInto_Req.getDefaultInstance();
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
        
        public StepInto_Req.Builder getStepIntoBuilder() {
            this.bitField0_ |= 0x200;
            this.onChanged();
            return (StepInto_Req.Builder)this.H().getBuilder();
        }
        
        public StepInto_ReqOrBuilder getStepIntoOrBuilder() {
            try {
                if (this.stepIntoBuilder_ != null) {
                    return (StepInto_ReqOrBuilder)this.stepIntoBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.stepInto_;
        }
        
        private SingleFieldBuilder<StepInto_Req, StepInto_Req.Builder, StepInto_ReqOrBuilder> H() {
            try {
                if (this.stepIntoBuilder_ == null) {
                    this.stepIntoBuilder_ = (SingleFieldBuilder<StepInto_Req, StepInto_Req.Builder, StepInto_ReqOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.stepInto_, this.getParentForChildren(), this.isClean());
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
        
        public StepOver_Req getStepOver() {
            try {
                if (this.stepOverBuilder_ == null) {
                    return this.stepOver_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (StepOver_Req)this.stepOverBuilder_.getMessage();
        }
        
        public Builder setStepOver(final StepOver_Req stepOver_Req) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.stepOverBuilder_ != null) {
                                break Label_0042;
                            }
                            final StepOver_Req stepOver_Req2 = stepOver_Req;
                            if (stepOver_Req2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final StepOver_Req stepOver_Req2 = stepOver_Req;
                            if (stepOver_Req2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.stepOver_ = stepOver_Req;
                    this.onChanged();
                    break Label_0051;
                }
                this.stepOverBuilder_.setMessage((GeneratedMessage)stepOver_Req);
            }
            this.bitField0_ |= 0x400;
            return this;
        }
        
        public Builder setStepOver(final StepOver_Req.Builder builder) {
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
        
        public Builder mergeStepOver(final StepOver_Req p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.stepOverBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       82
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    11: sipush          1024
            //    14: iand           
            //    15: sipush          1024
            //    18: if_icmpne       70
            //    21: goto            28
            //    24: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    27: athrow         
            //    28: aload_0        
            //    29: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.stepOver_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOver_Req;
            //    32: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOver_Req.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOver_Req;
            //    35: if_acmpeq       70
            //    38: goto            45
            //    41: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    44: athrow         
            //    45: aload_0        
            //    46: aload_0        
            //    47: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.stepOver_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOver_Req;
            //    50: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOver_Req.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOver_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOver_Req$Builder;
            //    53: aload_1        
            //    54: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOver_Req$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOver_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOver_Req$Builder;
            //    57: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOver_Req$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOver_Req;
            //    60: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.stepOver_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOver_Req;
            //    63: goto            75
            //    66: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    69: athrow         
            //    70: aload_0        
            //    71: aload_1        
            //    72: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.stepOver_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOver_Req;
            //    75: aload_0        
            //    76: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.onChanged:()V
            //    79: goto            91
            //    82: aload_0        
            //    83: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.stepOverBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    86: aload_1        
            //    87: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    90: pop            
            //    91: aload_0        
            //    92: dup            
            //    93: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    96: sipush          1024
            //    99: ior            
            //   100: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
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
                        this.stepOver_ = StepOver_Req.getDefaultInstance();
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
        
        public StepOver_Req.Builder getStepOverBuilder() {
            this.bitField0_ |= 0x400;
            this.onChanged();
            return (StepOver_Req.Builder)this.n().getBuilder();
        }
        
        public StepOver_ReqOrBuilder getStepOverOrBuilder() {
            try {
                if (this.stepOverBuilder_ != null) {
                    return (StepOver_ReqOrBuilder)this.stepOverBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.stepOver_;
        }
        
        private SingleFieldBuilder<StepOver_Req, StepOver_Req.Builder, StepOver_ReqOrBuilder> n() {
            try {
                if (this.stepOverBuilder_ == null) {
                    this.stepOverBuilder_ = (SingleFieldBuilder<StepOver_Req, StepOver_Req.Builder, StepOver_ReqOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.stepOver_, this.getParentForChildren(), this.isClean());
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
        
        public StepOut_Req getStepOut() {
            try {
                if (this.stepOutBuilder_ == null) {
                    return this.stepOut_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (StepOut_Req)this.stepOutBuilder_.getMessage();
        }
        
        public Builder setStepOut(final StepOut_Req stepOut_Req) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.stepOutBuilder_ != null) {
                                break Label_0042;
                            }
                            final StepOut_Req stepOut_Req2 = stepOut_Req;
                            if (stepOut_Req2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final StepOut_Req stepOut_Req2 = stepOut_Req;
                            if (stepOut_Req2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.stepOut_ = stepOut_Req;
                    this.onChanged();
                    break Label_0051;
                }
                this.stepOutBuilder_.setMessage((GeneratedMessage)stepOut_Req);
            }
            this.bitField0_ |= 0x800;
            return this;
        }
        
        public Builder setStepOut(final StepOut_Req.Builder builder) {
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
        
        public Builder mergeStepOut(final StepOut_Req p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.stepOutBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       82
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    11: sipush          2048
            //    14: iand           
            //    15: sipush          2048
            //    18: if_icmpne       70
            //    21: goto            28
            //    24: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    27: athrow         
            //    28: aload_0        
            //    29: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.stepOut_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOut_Req;
            //    32: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOut_Req.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOut_Req;
            //    35: if_acmpeq       70
            //    38: goto            45
            //    41: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    44: athrow         
            //    45: aload_0        
            //    46: aload_0        
            //    47: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.stepOut_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOut_Req;
            //    50: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOut_Req.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOut_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOut_Req$Builder;
            //    53: aload_1        
            //    54: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOut_Req$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOut_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOut_Req$Builder;
            //    57: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOut_Req$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOut_Req;
            //    60: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.stepOut_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOut_Req;
            //    63: goto            75
            //    66: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    69: athrow         
            //    70: aload_0        
            //    71: aload_1        
            //    72: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.stepOut_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$StepOut_Req;
            //    75: aload_0        
            //    76: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.onChanged:()V
            //    79: goto            91
            //    82: aload_0        
            //    83: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.stepOutBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    86: aload_1        
            //    87: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    90: pop            
            //    91: aload_0        
            //    92: dup            
            //    93: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    96: sipush          2048
            //    99: ior            
            //   100: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
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
                        this.stepOut_ = StepOut_Req.getDefaultInstance();
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
        
        public StepOut_Req.Builder getStepOutBuilder() {
            this.bitField0_ |= 0x800;
            this.onChanged();
            return (StepOut_Req.Builder)this.l().getBuilder();
        }
        
        public StepOut_ReqOrBuilder getStepOutOrBuilder() {
            try {
                if (this.stepOutBuilder_ != null) {
                    return (StepOut_ReqOrBuilder)this.stepOutBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.stepOut_;
        }
        
        private SingleFieldBuilder<StepOut_Req, StepOut_Req.Builder, StepOut_ReqOrBuilder> l() {
            try {
                if (this.stepOutBuilder_ == null) {
                    this.stepOutBuilder_ = (SingleFieldBuilder<StepOut_Req, StepOut_Req.Builder, StepOut_ReqOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.stepOut_, this.getParentForChildren(), this.isClean());
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
        
        public EvaluateExpression_Req getEvaluateExpression() {
            try {
                if (this.evaluateExpressionBuilder_ == null) {
                    return this.evaluateExpression_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (EvaluateExpression_Req)this.evaluateExpressionBuilder_.getMessage();
        }
        
        public Builder setEvaluateExpression(final EvaluateExpression_Req evaluateExpression_Req) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.evaluateExpressionBuilder_ != null) {
                                break Label_0042;
                            }
                            final EvaluateExpression_Req evaluateExpression_Req2 = evaluateExpression_Req;
                            if (evaluateExpression_Req2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final EvaluateExpression_Req evaluateExpression_Req2 = evaluateExpression_Req;
                            if (evaluateExpression_Req2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.evaluateExpression_ = evaluateExpression_Req;
                    this.onChanged();
                    break Label_0051;
                }
                this.evaluateExpressionBuilder_.setMessage((GeneratedMessage)evaluateExpression_Req);
            }
            this.bitField0_ |= 0x1000;
            return this;
        }
        
        public Builder setEvaluateExpression(final EvaluateExpression_Req.Builder builder) {
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
        
        public Builder mergeEvaluateExpression(final EvaluateExpression_Req p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.evaluateExpressionBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       82
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    11: sipush          4096
            //    14: iand           
            //    15: sipush          4096
            //    18: if_icmpne       70
            //    21: goto            28
            //    24: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    27: athrow         
            //    28: aload_0        
            //    29: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.evaluateExpression_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Req;
            //    32: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Req.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Req;
            //    35: if_acmpeq       70
            //    38: goto            45
            //    41: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    44: athrow         
            //    45: aload_0        
            //    46: aload_0        
            //    47: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.evaluateExpression_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Req;
            //    50: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Req.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Req$Builder;
            //    53: aload_1        
            //    54: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Req$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Req$Builder;
            //    57: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Req$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Req;
            //    60: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.evaluateExpression_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Req;
            //    63: goto            75
            //    66: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    69: athrow         
            //    70: aload_0        
            //    71: aload_1        
            //    72: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.evaluateExpression_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$EvaluateExpression_Req;
            //    75: aload_0        
            //    76: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.onChanged:()V
            //    79: goto            91
            //    82: aload_0        
            //    83: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.evaluateExpressionBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    86: aload_1        
            //    87: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    90: pop            
            //    91: aload_0        
            //    92: dup            
            //    93: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    96: sipush          4096
            //    99: ior            
            //   100: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
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
                        this.evaluateExpression_ = EvaluateExpression_Req.getDefaultInstance();
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
        
        public EvaluateExpression_Req.Builder getEvaluateExpressionBuilder() {
            this.bitField0_ |= 0x1000;
            this.onChanged();
            return (EvaluateExpression_Req.Builder)this.G().getBuilder();
        }
        
        public EvaluateExpression_ReqOrBuilder getEvaluateExpressionOrBuilder() {
            try {
                if (this.evaluateExpressionBuilder_ != null) {
                    return (EvaluateExpression_ReqOrBuilder)this.evaluateExpressionBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.evaluateExpression_;
        }
        
        private SingleFieldBuilder<EvaluateExpression_Req, EvaluateExpression_Req.Builder, EvaluateExpression_ReqOrBuilder> G() {
            try {
                if (this.evaluateExpressionBuilder_ == null) {
                    this.evaluateExpressionBuilder_ = (SingleFieldBuilder<EvaluateExpression_Req, EvaluateExpression_Req.Builder, EvaluateExpression_ReqOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.evaluateExpression_, this.getParentForChildren(), this.isClean());
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
        
        public GetValueChildren_Req getGetValueChildren() {
            try {
                if (this.getValueChildrenBuilder_ == null) {
                    return this.getValueChildren_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (GetValueChildren_Req)this.getValueChildrenBuilder_.getMessage();
        }
        
        public Builder setGetValueChildren(final GetValueChildren_Req getValueChildren_Req) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.getValueChildrenBuilder_ != null) {
                                break Label_0042;
                            }
                            final GetValueChildren_Req getValueChildren_Req2 = getValueChildren_Req;
                            if (getValueChildren_Req2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final GetValueChildren_Req getValueChildren_Req2 = getValueChildren_Req;
                            if (getValueChildren_Req2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.getValueChildren_ = getValueChildren_Req;
                    this.onChanged();
                    break Label_0051;
                }
                this.getValueChildrenBuilder_.setMessage((GeneratedMessage)getValueChildren_Req);
            }
            this.bitField0_ |= 0x2000;
            return this;
        }
        
        public Builder setGetValueChildren(final GetValueChildren_Req.Builder builder) {
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
        
        public Builder mergeGetValueChildren(final GetValueChildren_Req p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getValueChildrenBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       82
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    11: sipush          8192
            //    14: iand           
            //    15: sipush          8192
            //    18: if_icmpne       70
            //    21: goto            28
            //    24: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    27: athrow         
            //    28: aload_0        
            //    29: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getValueChildren_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Req;
            //    32: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Req.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Req;
            //    35: if_acmpeq       70
            //    38: goto            45
            //    41: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    44: athrow         
            //    45: aload_0        
            //    46: aload_0        
            //    47: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getValueChildren_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Req;
            //    50: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Req.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Req$Builder;
            //    53: aload_1        
            //    54: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Req$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Req$Builder;
            //    57: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Req$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Req;
            //    60: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getValueChildren_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Req;
            //    63: goto            75
            //    66: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    69: athrow         
            //    70: aload_0        
            //    71: aload_1        
            //    72: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getValueChildren_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueChildren_Req;
            //    75: aload_0        
            //    76: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.onChanged:()V
            //    79: goto            91
            //    82: aload_0        
            //    83: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getValueChildrenBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    86: aload_1        
            //    87: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    90: pop            
            //    91: aload_0        
            //    92: dup            
            //    93: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    96: sipush          8192
            //    99: ior            
            //   100: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
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
                        this.getValueChildren_ = GetValueChildren_Req.getDefaultInstance();
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
        
        public GetValueChildren_Req.Builder getGetValueChildrenBuilder() {
            this.bitField0_ |= 0x2000;
            this.onChanged();
            return (GetValueChildren_Req.Builder)this.C().getBuilder();
        }
        
        public GetValueChildren_ReqOrBuilder getGetValueChildrenOrBuilder() {
            try {
                if (this.getValueChildrenBuilder_ != null) {
                    return (GetValueChildren_ReqOrBuilder)this.getValueChildrenBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.getValueChildren_;
        }
        
        private SingleFieldBuilder<GetValueChildren_Req, GetValueChildren_Req.Builder, GetValueChildren_ReqOrBuilder> C() {
            try {
                if (this.getValueChildrenBuilder_ == null) {
                    this.getValueChildrenBuilder_ = (SingleFieldBuilder<GetValueChildren_Req, GetValueChildren_Req.Builder, GetValueChildren_ReqOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.getValueChildren_, this.getParentForChildren(), this.isClean());
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
        
        public GetVars_Req getGetVars() {
            try {
                if (this.getVarsBuilder_ == null) {
                    return this.getVars_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (GetVars_Req)this.getVarsBuilder_.getMessage();
        }
        
        public Builder setGetVars(final GetVars_Req getVars_Req) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.getVarsBuilder_ != null) {
                                break Label_0042;
                            }
                            final GetVars_Req getVars_Req2 = getVars_Req;
                            if (getVars_Req2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final GetVars_Req getVars_Req2 = getVars_Req;
                            if (getVars_Req2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.getVars_ = getVars_Req;
                    this.onChanged();
                    break Label_0051;
                }
                this.getVarsBuilder_.setMessage((GeneratedMessage)getVars_Req);
            }
            this.bitField0_ |= 0x4000;
            return this;
        }
        
        public Builder setGetVars(final GetVars_Req.Builder builder) {
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
        
        public Builder mergeGetVars(final GetVars_Req p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getVarsBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       82
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    11: sipush          16384
            //    14: iand           
            //    15: sipush          16384
            //    18: if_icmpne       70
            //    21: goto            28
            //    24: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    27: athrow         
            //    28: aload_0        
            //    29: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getVars_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetVars_Req;
            //    32: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetVars_Req.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetVars_Req;
            //    35: if_acmpeq       70
            //    38: goto            45
            //    41: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    44: athrow         
            //    45: aload_0        
            //    46: aload_0        
            //    47: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getVars_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetVars_Req;
            //    50: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetVars_Req.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetVars_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetVars_Req$Builder;
            //    53: aload_1        
            //    54: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetVars_Req$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetVars_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetVars_Req$Builder;
            //    57: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetVars_Req$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetVars_Req;
            //    60: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getVars_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetVars_Req;
            //    63: goto            75
            //    66: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    69: athrow         
            //    70: aload_0        
            //    71: aload_1        
            //    72: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getVars_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetVars_Req;
            //    75: aload_0        
            //    76: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.onChanged:()V
            //    79: goto            91
            //    82: aload_0        
            //    83: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getVarsBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    86: aload_1        
            //    87: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    90: pop            
            //    91: aload_0        
            //    92: dup            
            //    93: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    96: sipush          16384
            //    99: ior            
            //   100: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
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
                        this.getVars_ = GetVars_Req.getDefaultInstance();
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
        
        public GetVars_Req.Builder getGetVarsBuilder() {
            this.bitField0_ |= 0x4000;
            this.onChanged();
            return (GetVars_Req.Builder)this.e().getBuilder();
        }
        
        public GetVars_ReqOrBuilder getGetVarsOrBuilder() {
            try {
                if (this.getVarsBuilder_ != null) {
                    return (GetVars_ReqOrBuilder)this.getVarsBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.getVars_;
        }
        
        private SingleFieldBuilder<GetVars_Req, GetVars_Req.Builder, GetVars_ReqOrBuilder> e() {
            try {
                if (this.getVarsBuilder_ == null) {
                    this.getVarsBuilder_ = (SingleFieldBuilder<GetVars_Req, GetVars_Req.Builder, GetVars_ReqOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.getVars_, this.getParentForChildren(), this.isClean());
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
        
        public HandleConsoleCommand_Req getHandleConsoleCommand() {
            try {
                if (this.handleConsoleCommandBuilder_ == null) {
                    return this.handleConsoleCommand_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (HandleConsoleCommand_Req)this.handleConsoleCommandBuilder_.getMessage();
        }
        
        public Builder setHandleConsoleCommand(final HandleConsoleCommand_Req handleConsoleCommand_Req) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.handleConsoleCommandBuilder_ != null) {
                                break Label_0042;
                            }
                            final HandleConsoleCommand_Req handleConsoleCommand_Req2 = handleConsoleCommand_Req;
                            if (handleConsoleCommand_Req2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final HandleConsoleCommand_Req handleConsoleCommand_Req2 = handleConsoleCommand_Req;
                            if (handleConsoleCommand_Req2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.handleConsoleCommand_ = handleConsoleCommand_Req;
                    this.onChanged();
                    break Label_0051;
                }
                this.handleConsoleCommandBuilder_.setMessage((GeneratedMessage)handleConsoleCommand_Req);
            }
            this.bitField0_ |= 0x8000;
            return this;
        }
        
        public Builder setHandleConsoleCommand(final HandleConsoleCommand_Req.Builder builder) {
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
        
        public Builder mergeHandleConsoleCommand(final HandleConsoleCommand_Req p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.handleConsoleCommandBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       80
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    11: ldc             32768
            //    13: iand           
            //    14: ldc             32768
            //    16: if_icmpne       68
            //    19: goto            26
            //    22: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    25: athrow         
            //    26: aload_0        
            //    27: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.handleConsoleCommand_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleConsoleCommand_Req;
            //    30: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleConsoleCommand_Req.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleConsoleCommand_Req;
            //    33: if_acmpeq       68
            //    36: goto            43
            //    39: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    42: athrow         
            //    43: aload_0        
            //    44: aload_0        
            //    45: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.handleConsoleCommand_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleConsoleCommand_Req;
            //    48: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleConsoleCommand_Req.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleConsoleCommand_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleConsoleCommand_Req$Builder;
            //    51: aload_1        
            //    52: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleConsoleCommand_Req$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleConsoleCommand_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleConsoleCommand_Req$Builder;
            //    55: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleConsoleCommand_Req$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleConsoleCommand_Req;
            //    58: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.handleConsoleCommand_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleConsoleCommand_Req;
            //    61: goto            73
            //    64: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    67: athrow         
            //    68: aload_0        
            //    69: aload_1        
            //    70: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.handleConsoleCommand_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleConsoleCommand_Req;
            //    73: aload_0        
            //    74: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.onChanged:()V
            //    77: goto            89
            //    80: aload_0        
            //    81: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.handleConsoleCommandBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    84: aload_1        
            //    85: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    88: pop            
            //    89: aload_0        
            //    90: dup            
            //    91: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    94: ldc             32768
            //    96: ior            
            //    97: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
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
                        this.handleConsoleCommand_ = HandleConsoleCommand_Req.getDefaultInstance();
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
        
        public HandleConsoleCommand_Req.Builder getHandleConsoleCommandBuilder() {
            this.bitField0_ |= 0x8000;
            this.onChanged();
            return (HandleConsoleCommand_Req.Builder)this.c().getBuilder();
        }
        
        public HandleConsoleCommand_ReqOrBuilder getHandleConsoleCommandOrBuilder() {
            try {
                if (this.handleConsoleCommandBuilder_ != null) {
                    return (HandleConsoleCommand_ReqOrBuilder)this.handleConsoleCommandBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.handleConsoleCommand_;
        }
        
        private SingleFieldBuilder<HandleConsoleCommand_Req, HandleConsoleCommand_Req.Builder, HandleConsoleCommand_ReqOrBuilder> c() {
            try {
                if (this.handleConsoleCommandBuilder_ == null) {
                    this.handleConsoleCommandBuilder_ = (SingleFieldBuilder<HandleConsoleCommand_Req, HandleConsoleCommand_Req.Builder, HandleConsoleCommand_ReqOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.handleConsoleCommand_, this.getParentForChildren(), this.isClean());
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
        
        public HandleCompletion_Req getHandleCompletion() {
            try {
                if (this.handleCompletionBuilder_ == null) {
                    return this.handleCompletion_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (HandleCompletion_Req)this.handleCompletionBuilder_.getMessage();
        }
        
        public Builder setHandleCompletion(final HandleCompletion_Req handleCompletion_Req) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.handleCompletionBuilder_ != null) {
                                break Label_0042;
                            }
                            final HandleCompletion_Req handleCompletion_Req2 = handleCompletion_Req;
                            if (handleCompletion_Req2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final HandleCompletion_Req handleCompletion_Req2 = handleCompletion_Req;
                            if (handleCompletion_Req2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.handleCompletion_ = handleCompletion_Req;
                    this.onChanged();
                    break Label_0051;
                }
                this.handleCompletionBuilder_.setMessage((GeneratedMessage)handleCompletion_Req);
            }
            this.bitField0_ |= 0x10000;
            return this;
        }
        
        public Builder setHandleCompletion(final HandleCompletion_Req.Builder builder) {
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
        
        public Builder mergeHandleCompletion(final HandleCompletion_Req p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.handleCompletionBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       80
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    11: ldc             65536
            //    13: iand           
            //    14: ldc             65536
            //    16: if_icmpne       68
            //    19: goto            26
            //    22: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    25: athrow         
            //    26: aload_0        
            //    27: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.handleCompletion_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleCompletion_Req;
            //    30: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleCompletion_Req.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleCompletion_Req;
            //    33: if_acmpeq       68
            //    36: goto            43
            //    39: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    42: athrow         
            //    43: aload_0        
            //    44: aload_0        
            //    45: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.handleCompletion_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleCompletion_Req;
            //    48: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleCompletion_Req.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleCompletion_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleCompletion_Req$Builder;
            //    51: aload_1        
            //    52: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleCompletion_Req$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleCompletion_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleCompletion_Req$Builder;
            //    55: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleCompletion_Req$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleCompletion_Req;
            //    58: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.handleCompletion_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleCompletion_Req;
            //    61: goto            73
            //    64: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    67: athrow         
            //    68: aload_0        
            //    69: aload_1        
            //    70: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.handleCompletion_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleCompletion_Req;
            //    73: aload_0        
            //    74: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.onChanged:()V
            //    77: goto            89
            //    80: aload_0        
            //    81: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.handleCompletionBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    84: aload_1        
            //    85: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    88: pop            
            //    89: aload_0        
            //    90: dup            
            //    91: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    94: ldc             65536
            //    96: ior            
            //    97: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
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
                        this.handleCompletion_ = HandleCompletion_Req.getDefaultInstance();
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
        
        public HandleCompletion_Req.Builder getHandleCompletionBuilder() {
            this.bitField0_ |= 0x10000;
            this.onChanged();
            return (HandleCompletion_Req.Builder)this.I().getBuilder();
        }
        
        public HandleCompletion_ReqOrBuilder getHandleCompletionOrBuilder() {
            try {
                if (this.handleCompletionBuilder_ != null) {
                    return (HandleCompletion_ReqOrBuilder)this.handleCompletionBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.handleCompletion_;
        }
        
        private SingleFieldBuilder<HandleCompletion_Req, HandleCompletion_Req.Builder, HandleCompletion_ReqOrBuilder> I() {
            try {
                if (this.handleCompletionBuilder_ == null) {
                    this.handleCompletionBuilder_ = (SingleFieldBuilder<HandleCompletion_Req, HandleCompletion_Req.Builder, HandleCompletion_ReqOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.handleCompletion_, this.getParentForChildren(), this.isClean());
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
        
        public Attach_Req getAttach() {
            try {
                if (this.attachBuilder_ == null) {
                    return this.attach_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (Attach_Req)this.attachBuilder_.getMessage();
        }
        
        public Builder setAttach(final Attach_Req attach_Req) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.attachBuilder_ != null) {
                                break Label_0042;
                            }
                            final Attach_Req attach_Req2 = attach_Req;
                            if (attach_Req2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final Attach_Req attach_Req2 = attach_Req;
                            if (attach_Req2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.attach_ = attach_Req;
                    this.onChanged();
                    break Label_0051;
                }
                this.attachBuilder_.setMessage((GeneratedMessage)attach_Req);
            }
            this.bitField0_ |= 0x20000;
            return this;
        }
        
        public Builder setAttach(final Attach_Req.Builder builder) {
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
        
        public Builder mergeAttach(final Attach_Req p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.attachBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       80
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    11: ldc             131072
            //    13: iand           
            //    14: ldc             131072
            //    16: if_icmpne       68
            //    19: goto            26
            //    22: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    25: athrow         
            //    26: aload_0        
            //    27: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.attach_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Attach_Req;
            //    30: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Attach_Req.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Attach_Req;
            //    33: if_acmpeq       68
            //    36: goto            43
            //    39: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    42: athrow         
            //    43: aload_0        
            //    44: aload_0        
            //    45: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.attach_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Attach_Req;
            //    48: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Attach_Req.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Attach_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Attach_Req$Builder;
            //    51: aload_1        
            //    52: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Attach_Req$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Attach_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Attach_Req$Builder;
            //    55: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Attach_Req$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Attach_Req;
            //    58: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.attach_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Attach_Req;
            //    61: goto            73
            //    64: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    67: athrow         
            //    68: aload_0        
            //    69: aload_1        
            //    70: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.attach_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Attach_Req;
            //    73: aload_0        
            //    74: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.onChanged:()V
            //    77: goto            89
            //    80: aload_0        
            //    81: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.attachBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    84: aload_1        
            //    85: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    88: pop            
            //    89: aload_0        
            //    90: dup            
            //    91: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    94: ldc             131072
            //    96: ior            
            //    97: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
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
                        this.attach_ = Attach_Req.getDefaultInstance();
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
        
        public Attach_Req.Builder getAttachBuilder() {
            this.bitField0_ |= 0x20000;
            this.onChanged();
            return (Attach_Req.Builder)this.z().getBuilder();
        }
        
        public Attach_ReqOrBuilder getAttachOrBuilder() {
            try {
                if (this.attachBuilder_ != null) {
                    return (Attach_ReqOrBuilder)this.attachBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.attach_;
        }
        
        private SingleFieldBuilder<Attach_Req, Attach_Req.Builder, Attach_ReqOrBuilder> z() {
            try {
                if (this.attachBuilder_ == null) {
                    this.attachBuilder_ = (SingleFieldBuilder<Attach_Req, Attach_Req.Builder, Attach_ReqOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.attach_, this.getParentForChildren(), this.isClean());
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
        
        public AttachByName_Req getAttachByName() {
            try {
                if (this.attachByNameBuilder_ == null) {
                    return this.attachByName_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (AttachByName_Req)this.attachByNameBuilder_.getMessage();
        }
        
        public Builder setAttachByName(final AttachByName_Req attachByName_Req) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.attachByNameBuilder_ != null) {
                                break Label_0042;
                            }
                            final AttachByName_Req attachByName_Req2 = attachByName_Req;
                            if (attachByName_Req2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final AttachByName_Req attachByName_Req2 = attachByName_Req;
                            if (attachByName_Req2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.attachByName_ = attachByName_Req;
                    this.onChanged();
                    break Label_0051;
                }
                this.attachByNameBuilder_.setMessage((GeneratedMessage)attachByName_Req);
            }
            this.bitField0_ |= 0x40000;
            return this;
        }
        
        public Builder setAttachByName(final AttachByName_Req.Builder builder) {
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
        
        public Builder mergeAttachByName(final AttachByName_Req p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.attachByNameBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       80
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    11: ldc             262144
            //    13: iand           
            //    14: ldc             262144
            //    16: if_icmpne       68
            //    19: goto            26
            //    22: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    25: athrow         
            //    26: aload_0        
            //    27: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.attachByName_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AttachByName_Req;
            //    30: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AttachByName_Req.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AttachByName_Req;
            //    33: if_acmpeq       68
            //    36: goto            43
            //    39: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    42: athrow         
            //    43: aload_0        
            //    44: aload_0        
            //    45: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.attachByName_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AttachByName_Req;
            //    48: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AttachByName_Req.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AttachByName_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AttachByName_Req$Builder;
            //    51: aload_1        
            //    52: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AttachByName_Req$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AttachByName_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AttachByName_Req$Builder;
            //    55: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AttachByName_Req$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AttachByName_Req;
            //    58: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.attachByName_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AttachByName_Req;
            //    61: goto            73
            //    64: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    67: athrow         
            //    68: aload_0        
            //    69: aload_1        
            //    70: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.attachByName_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AttachByName_Req;
            //    73: aload_0        
            //    74: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.onChanged:()V
            //    77: goto            89
            //    80: aload_0        
            //    81: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.attachByNameBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    84: aload_1        
            //    85: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    88: pop            
            //    89: aload_0        
            //    90: dup            
            //    91: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    94: ldc             262144
            //    96: ior            
            //    97: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
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
                        this.attachByName_ = AttachByName_Req.getDefaultInstance();
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
        
        public AttachByName_Req.Builder getAttachByNameBuilder() {
            this.bitField0_ |= 0x40000;
            this.onChanged();
            return (AttachByName_Req.Builder)this.d().getBuilder();
        }
        
        public AttachByName_ReqOrBuilder getAttachByNameOrBuilder() {
            try {
                if (this.attachByNameBuilder_ != null) {
                    return (AttachByName_ReqOrBuilder)this.attachByNameBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.attachByName_;
        }
        
        private SingleFieldBuilder<AttachByName_Req, AttachByName_Req.Builder, AttachByName_ReqOrBuilder> d() {
            try {
                if (this.attachByNameBuilder_ == null) {
                    this.attachByNameBuilder_ = (SingleFieldBuilder<AttachByName_Req, AttachByName_Req.Builder, AttachByName_ReqOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.attachByName_, this.getParentForChildren(), this.isClean());
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
        
        public DispatchInput_Req getDispatchInput() {
            try {
                if (this.dispatchInputBuilder_ == null) {
                    return this.dispatchInput_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (DispatchInput_Req)this.dispatchInputBuilder_.getMessage();
        }
        
        public Builder setDispatchInput(final DispatchInput_Req dispatchInput_Req) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.dispatchInputBuilder_ != null) {
                                break Label_0042;
                            }
                            final DispatchInput_Req dispatchInput_Req2 = dispatchInput_Req;
                            if (dispatchInput_Req2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final DispatchInput_Req dispatchInput_Req2 = dispatchInput_Req;
                            if (dispatchInput_Req2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.dispatchInput_ = dispatchInput_Req;
                    this.onChanged();
                    break Label_0051;
                }
                this.dispatchInputBuilder_.setMessage((GeneratedMessage)dispatchInput_Req);
            }
            this.bitField0_ |= 0x80000;
            return this;
        }
        
        public Builder setDispatchInput(final DispatchInput_Req.Builder builder) {
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
        
        public Builder mergeDispatchInput(final DispatchInput_Req p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.dispatchInputBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       80
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    11: ldc             524288
            //    13: iand           
            //    14: ldc             524288
            //    16: if_icmpne       68
            //    19: goto            26
            //    22: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    25: athrow         
            //    26: aload_0        
            //    27: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.dispatchInput_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$DispatchInput_Req;
            //    30: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$DispatchInput_Req.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$DispatchInput_Req;
            //    33: if_acmpeq       68
            //    36: goto            43
            //    39: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    42: athrow         
            //    43: aload_0        
            //    44: aload_0        
            //    45: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.dispatchInput_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$DispatchInput_Req;
            //    48: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$DispatchInput_Req.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$DispatchInput_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$DispatchInput_Req$Builder;
            //    51: aload_1        
            //    52: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$DispatchInput_Req$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$DispatchInput_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$DispatchInput_Req$Builder;
            //    55: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$DispatchInput_Req$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$DispatchInput_Req;
            //    58: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.dispatchInput_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$DispatchInput_Req;
            //    61: goto            73
            //    64: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    67: athrow         
            //    68: aload_0        
            //    69: aload_1        
            //    70: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.dispatchInput_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$DispatchInput_Req;
            //    73: aload_0        
            //    74: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.onChanged:()V
            //    77: goto            89
            //    80: aload_0        
            //    81: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.dispatchInputBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    84: aload_1        
            //    85: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    88: pop            
            //    89: aload_0        
            //    90: dup            
            //    91: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    94: ldc             524288
            //    96: ior            
            //    97: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
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
                        this.dispatchInput_ = DispatchInput_Req.getDefaultInstance();
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
        
        public DispatchInput_Req.Builder getDispatchInputBuilder() {
            this.bitField0_ |= 0x80000;
            this.onChanged();
            return (DispatchInput_Req.Builder)this.w().getBuilder();
        }
        
        public DispatchInput_ReqOrBuilder getDispatchInputOrBuilder() {
            try {
                if (this.dispatchInputBuilder_ != null) {
                    return (DispatchInput_ReqOrBuilder)this.dispatchInputBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.dispatchInput_;
        }
        
        private SingleFieldBuilder<DispatchInput_Req, DispatchInput_Req.Builder, DispatchInput_ReqOrBuilder> w() {
            try {
                if (this.dispatchInputBuilder_ == null) {
                    this.dispatchInputBuilder_ = (SingleFieldBuilder<DispatchInput_Req, DispatchInput_Req.Builder, DispatchInput_ReqOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.dispatchInput_, this.getParentForChildren(), this.isClean());
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
        
        public AddWatchpoint_Req getAddWatchpoint() {
            try {
                if (this.addWatchpointBuilder_ == null) {
                    return this.addWatchpoint_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (AddWatchpoint_Req)this.addWatchpointBuilder_.getMessage();
        }
        
        public Builder setAddWatchpoint(final AddWatchpoint_Req addWatchpoint_Req) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.addWatchpointBuilder_ != null) {
                                break Label_0042;
                            }
                            final AddWatchpoint_Req addWatchpoint_Req2 = addWatchpoint_Req;
                            if (addWatchpoint_Req2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final AddWatchpoint_Req addWatchpoint_Req2 = addWatchpoint_Req;
                            if (addWatchpoint_Req2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.addWatchpoint_ = addWatchpoint_Req;
                    this.onChanged();
                    break Label_0051;
                }
                this.addWatchpointBuilder_.setMessage((GeneratedMessage)addWatchpoint_Req);
            }
            this.bitField0_ |= 0x100000;
            return this;
        }
        
        public Builder setAddWatchpoint(final AddWatchpoint_Req.Builder builder) {
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
        
        public Builder mergeAddWatchpoint(final AddWatchpoint_Req p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.addWatchpointBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       80
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    11: ldc             1048576
            //    13: iand           
            //    14: ldc             1048576
            //    16: if_icmpne       68
            //    19: goto            26
            //    22: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    25: athrow         
            //    26: aload_0        
            //    27: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.addWatchpoint_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddWatchpoint_Req;
            //    30: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddWatchpoint_Req.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddWatchpoint_Req;
            //    33: if_acmpeq       68
            //    36: goto            43
            //    39: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    42: athrow         
            //    43: aload_0        
            //    44: aload_0        
            //    45: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.addWatchpoint_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddWatchpoint_Req;
            //    48: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddWatchpoint_Req.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddWatchpoint_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddWatchpoint_Req$Builder;
            //    51: aload_1        
            //    52: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddWatchpoint_Req$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddWatchpoint_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddWatchpoint_Req$Builder;
            //    55: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddWatchpoint_Req$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddWatchpoint_Req;
            //    58: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.addWatchpoint_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddWatchpoint_Req;
            //    61: goto            73
            //    64: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    67: athrow         
            //    68: aload_0        
            //    69: aload_1        
            //    70: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.addWatchpoint_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$AddWatchpoint_Req;
            //    73: aload_0        
            //    74: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.onChanged:()V
            //    77: goto            89
            //    80: aload_0        
            //    81: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.addWatchpointBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    84: aload_1        
            //    85: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    88: pop            
            //    89: aload_0        
            //    90: dup            
            //    91: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    94: ldc             1048576
            //    96: ior            
            //    97: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
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
                        this.addWatchpoint_ = AddWatchpoint_Req.getDefaultInstance();
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
        
        public AddWatchpoint_Req.Builder getAddWatchpointBuilder() {
            this.bitField0_ |= 0x100000;
            this.onChanged();
            return (AddWatchpoint_Req.Builder)this.v().getBuilder();
        }
        
        public AddWatchpoint_ReqOrBuilder getAddWatchpointOrBuilder() {
            try {
                if (this.addWatchpointBuilder_ != null) {
                    return (AddWatchpoint_ReqOrBuilder)this.addWatchpointBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.addWatchpoint_;
        }
        
        private SingleFieldBuilder<AddWatchpoint_Req, AddWatchpoint_Req.Builder, AddWatchpoint_ReqOrBuilder> v() {
            try {
                if (this.addWatchpointBuilder_ == null) {
                    this.addWatchpointBuilder_ = (SingleFieldBuilder<AddWatchpoint_Req, AddWatchpoint_Req.Builder, AddWatchpoint_ReqOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.addWatchpoint_, this.getParentForChildren(), this.isClean());
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
        
        public RemoveWatchpoint_Req getRemoveWatchpoint() {
            try {
                if (this.removeWatchpointBuilder_ == null) {
                    return this.removeWatchpoint_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (RemoveWatchpoint_Req)this.removeWatchpointBuilder_.getMessage();
        }
        
        public Builder setRemoveWatchpoint(final RemoveWatchpoint_Req removeWatchpoint_Req) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.removeWatchpointBuilder_ != null) {
                                break Label_0042;
                            }
                            final RemoveWatchpoint_Req removeWatchpoint_Req2 = removeWatchpoint_Req;
                            if (removeWatchpoint_Req2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final RemoveWatchpoint_Req removeWatchpoint_Req2 = removeWatchpoint_Req;
                            if (removeWatchpoint_Req2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.removeWatchpoint_ = removeWatchpoint_Req;
                    this.onChanged();
                    break Label_0051;
                }
                this.removeWatchpointBuilder_.setMessage((GeneratedMessage)removeWatchpoint_Req);
            }
            this.bitField0_ |= 0x200000;
            return this;
        }
        
        public Builder setRemoveWatchpoint(final RemoveWatchpoint_Req.Builder builder) {
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
        
        public Builder mergeRemoveWatchpoint(final RemoveWatchpoint_Req p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.removeWatchpointBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       80
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    11: ldc             2097152
            //    13: iand           
            //    14: ldc             2097152
            //    16: if_icmpne       68
            //    19: goto            26
            //    22: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    25: athrow         
            //    26: aload_0        
            //    27: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.removeWatchpoint_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveWatchpoint_Req;
            //    30: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveWatchpoint_Req.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveWatchpoint_Req;
            //    33: if_acmpeq       68
            //    36: goto            43
            //    39: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    42: athrow         
            //    43: aload_0        
            //    44: aload_0        
            //    45: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.removeWatchpoint_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveWatchpoint_Req;
            //    48: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveWatchpoint_Req.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveWatchpoint_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveWatchpoint_Req$Builder;
            //    51: aload_1        
            //    52: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveWatchpoint_Req$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveWatchpoint_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveWatchpoint_Req$Builder;
            //    55: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveWatchpoint_Req$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveWatchpoint_Req;
            //    58: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.removeWatchpoint_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveWatchpoint_Req;
            //    61: goto            73
            //    64: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    67: athrow         
            //    68: aload_0        
            //    69: aload_1        
            //    70: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.removeWatchpoint_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$RemoveWatchpoint_Req;
            //    73: aload_0        
            //    74: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.onChanged:()V
            //    77: goto            89
            //    80: aload_0        
            //    81: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.removeWatchpointBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    84: aload_1        
            //    85: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    88: pop            
            //    89: aload_0        
            //    90: dup            
            //    91: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    94: ldc             2097152
            //    96: ior            
            //    97: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
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
                        this.removeWatchpoint_ = RemoveWatchpoint_Req.getDefaultInstance();
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
        
        public RemoveWatchpoint_Req.Builder getRemoveWatchpointBuilder() {
            this.bitField0_ |= 0x200000;
            this.onChanged();
            return (RemoveWatchpoint_Req.Builder)this.a().getBuilder();
        }
        
        public RemoveWatchpoint_ReqOrBuilder getRemoveWatchpointOrBuilder() {
            try {
                if (this.removeWatchpointBuilder_ != null) {
                    return (RemoveWatchpoint_ReqOrBuilder)this.removeWatchpointBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.removeWatchpoint_;
        }
        
        private SingleFieldBuilder<RemoveWatchpoint_Req, RemoveWatchpoint_Req.Builder, RemoveWatchpoint_ReqOrBuilder> a() {
            try {
                if (this.removeWatchpointBuilder_ == null) {
                    this.removeWatchpointBuilder_ = (SingleFieldBuilder<RemoveWatchpoint_Req, RemoveWatchpoint_Req.Builder, RemoveWatchpoint_ReqOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.removeWatchpoint_, this.getParentForChildren(), this.isClean());
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
        
        public Detach_Req getDetach() {
            try {
                if (this.detachBuilder_ == null) {
                    return this.detach_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (Detach_Req)this.detachBuilder_.getMessage();
        }
        
        public Builder setDetach(final Detach_Req detach_Req) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.detachBuilder_ != null) {
                                break Label_0042;
                            }
                            final Detach_Req detach_Req2 = detach_Req;
                            if (detach_Req2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final Detach_Req detach_Req2 = detach_Req;
                            if (detach_Req2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.detach_ = detach_Req;
                    this.onChanged();
                    break Label_0051;
                }
                this.detachBuilder_.setMessage((GeneratedMessage)detach_Req);
            }
            this.bitField0_ |= 0x400000;
            return this;
        }
        
        public Builder setDetach(final Detach_Req.Builder builder) {
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
        
        public Builder mergeDetach(final Detach_Req p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.detachBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       80
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    11: ldc             4194304
            //    13: iand           
            //    14: ldc             4194304
            //    16: if_icmpne       68
            //    19: goto            26
            //    22: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    25: athrow         
            //    26: aload_0        
            //    27: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.detach_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Detach_Req;
            //    30: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Detach_Req.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Detach_Req;
            //    33: if_acmpeq       68
            //    36: goto            43
            //    39: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    42: athrow         
            //    43: aload_0        
            //    44: aload_0        
            //    45: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.detach_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Detach_Req;
            //    48: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Detach_Req.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Detach_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Detach_Req$Builder;
            //    51: aload_1        
            //    52: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Detach_Req$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Detach_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Detach_Req$Builder;
            //    55: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Detach_Req$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Detach_Req;
            //    58: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.detach_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Detach_Req;
            //    61: goto            73
            //    64: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    67: athrow         
            //    68: aload_0        
            //    69: aload_1        
            //    70: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.detach_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Detach_Req;
            //    73: aload_0        
            //    74: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.onChanged:()V
            //    77: goto            89
            //    80: aload_0        
            //    81: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.detachBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    84: aload_1        
            //    85: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    88: pop            
            //    89: aload_0        
            //    90: dup            
            //    91: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    94: ldc             4194304
            //    96: ior            
            //    97: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
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
                        this.detach_ = Detach_Req.getDefaultInstance();
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
        
        public Detach_Req.Builder getDetachBuilder() {
            this.bitField0_ |= 0x400000;
            this.onChanged();
            return (Detach_Req.Builder)this.k().getBuilder();
        }
        
        public Detach_ReqOrBuilder getDetachOrBuilder() {
            try {
                if (this.detachBuilder_ != null) {
                    return (Detach_ReqOrBuilder)this.detachBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.detach_;
        }
        
        private SingleFieldBuilder<Detach_Req, Detach_Req.Builder, Detach_ReqOrBuilder> k() {
            try {
                if (this.detachBuilder_ == null) {
                    this.detachBuilder_ = (SingleFieldBuilder<Detach_Req, Detach_Req.Builder, Detach_ReqOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.detach_, this.getParentForChildren(), this.isClean());
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
        
        public Kill_Req getKill() {
            try {
                if (this.killBuilder_ == null) {
                    return this.kill_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (Kill_Req)this.killBuilder_.getMessage();
        }
        
        public Builder setKill(final Kill_Req kill_Req) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.killBuilder_ != null) {
                                break Label_0042;
                            }
                            final Kill_Req kill_Req2 = kill_Req;
                            if (kill_Req2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final Kill_Req kill_Req2 = kill_Req;
                            if (kill_Req2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.kill_ = kill_Req;
                    this.onChanged();
                    break Label_0051;
                }
                this.killBuilder_.setMessage((GeneratedMessage)kill_Req);
            }
            this.bitField0_ |= 0x800000;
            return this;
        }
        
        public Builder setKill(final Kill_Req.Builder builder) {
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
        
        public Builder mergeKill(final Kill_Req p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.killBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       80
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    11: ldc             8388608
            //    13: iand           
            //    14: ldc             8388608
            //    16: if_icmpne       68
            //    19: goto            26
            //    22: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    25: athrow         
            //    26: aload_0        
            //    27: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.kill_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Kill_Req;
            //    30: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Kill_Req.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Kill_Req;
            //    33: if_acmpeq       68
            //    36: goto            43
            //    39: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    42: athrow         
            //    43: aload_0        
            //    44: aload_0        
            //    45: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.kill_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Kill_Req;
            //    48: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Kill_Req.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Kill_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Kill_Req$Builder;
            //    51: aload_1        
            //    52: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Kill_Req$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Kill_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Kill_Req$Builder;
            //    55: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Kill_Req$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Kill_Req;
            //    58: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.kill_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Kill_Req;
            //    61: goto            73
            //    64: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    67: athrow         
            //    68: aload_0        
            //    69: aload_1        
            //    70: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.kill_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$Kill_Req;
            //    73: aload_0        
            //    74: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.onChanged:()V
            //    77: goto            89
            //    80: aload_0        
            //    81: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.killBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    84: aload_1        
            //    85: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    88: pop            
            //    89: aload_0        
            //    90: dup            
            //    91: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    94: ldc             8388608
            //    96: ior            
            //    97: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
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
                        this.kill_ = Kill_Req.getDefaultInstance();
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
        
        public Kill_Req.Builder getKillBuilder() {
            this.bitField0_ |= 0x800000;
            this.onChanged();
            return (Kill_Req.Builder)this.q().getBuilder();
        }
        
        public Kill_ReqOrBuilder getKillOrBuilder() {
            try {
                if (this.killBuilder_ != null) {
                    return (Kill_ReqOrBuilder)this.killBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.kill_;
        }
        
        private SingleFieldBuilder<Kill_Req, Kill_Req.Builder, Kill_ReqOrBuilder> q() {
            try {
                if (this.killBuilder_ == null) {
                    this.killBuilder_ = (SingleFieldBuilder<Kill_Req, Kill_Req.Builder, Kill_ReqOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.kill_, this.getParentForChildren(), this.isClean());
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
        
        public GetChildrenCount_Req getGetChildrenCount() {
            try {
                if (this.getChildrenCountBuilder_ == null) {
                    return this.getChildrenCount_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (GetChildrenCount_Req)this.getChildrenCountBuilder_.getMessage();
        }
        
        public Builder setGetChildrenCount(final GetChildrenCount_Req getChildrenCount_Req) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.getChildrenCountBuilder_ != null) {
                                break Label_0042;
                            }
                            final GetChildrenCount_Req getChildrenCount_Req2 = getChildrenCount_Req;
                            if (getChildrenCount_Req2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final GetChildrenCount_Req getChildrenCount_Req2 = getChildrenCount_Req;
                            if (getChildrenCount_Req2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.getChildrenCount_ = getChildrenCount_Req;
                    this.onChanged();
                    break Label_0051;
                }
                this.getChildrenCountBuilder_.setMessage((GeneratedMessage)getChildrenCount_Req);
            }
            this.bitField0_ |= 0x1000000;
            return this;
        }
        
        public Builder setGetChildrenCount(final GetChildrenCount_Req.Builder builder) {
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
        
        public Builder mergeGetChildrenCount(final GetChildrenCount_Req p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getChildrenCountBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       80
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    11: ldc             16777216
            //    13: iand           
            //    14: ldc             16777216
            //    16: if_icmpne       68
            //    19: goto            26
            //    22: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    25: athrow         
            //    26: aload_0        
            //    27: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getChildrenCount_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetChildrenCount_Req;
            //    30: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetChildrenCount_Req.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetChildrenCount_Req;
            //    33: if_acmpeq       68
            //    36: goto            43
            //    39: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    42: athrow         
            //    43: aload_0        
            //    44: aload_0        
            //    45: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getChildrenCount_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetChildrenCount_Req;
            //    48: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetChildrenCount_Req.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetChildrenCount_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetChildrenCount_Req$Builder;
            //    51: aload_1        
            //    52: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetChildrenCount_Req$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetChildrenCount_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetChildrenCount_Req$Builder;
            //    55: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetChildrenCount_Req$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetChildrenCount_Req;
            //    58: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getChildrenCount_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetChildrenCount_Req;
            //    61: goto            73
            //    64: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    67: athrow         
            //    68: aload_0        
            //    69: aload_1        
            //    70: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getChildrenCount_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetChildrenCount_Req;
            //    73: aload_0        
            //    74: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.onChanged:()V
            //    77: goto            89
            //    80: aload_0        
            //    81: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getChildrenCountBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    84: aload_1        
            //    85: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    88: pop            
            //    89: aload_0        
            //    90: dup            
            //    91: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    94: ldc             16777216
            //    96: ior            
            //    97: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
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
                        this.getChildrenCount_ = GetChildrenCount_Req.getDefaultInstance();
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
        
        public GetChildrenCount_Req.Builder getGetChildrenCountBuilder() {
            this.bitField0_ |= 0x1000000;
            this.onChanged();
            return (GetChildrenCount_Req.Builder)this.t().getBuilder();
        }
        
        public GetChildrenCount_ReqOrBuilder getGetChildrenCountOrBuilder() {
            try {
                if (this.getChildrenCountBuilder_ != null) {
                    return (GetChildrenCount_ReqOrBuilder)this.getChildrenCountBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.getChildrenCount_;
        }
        
        private SingleFieldBuilder<GetChildrenCount_Req, GetChildrenCount_Req.Builder, GetChildrenCount_ReqOrBuilder> t() {
            try {
                if (this.getChildrenCountBuilder_ == null) {
                    this.getChildrenCountBuilder_ = (SingleFieldBuilder<GetChildrenCount_Req, GetChildrenCount_Req.Builder, GetChildrenCount_ReqOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.getChildrenCount_, this.getParentForChildren(), this.isClean());
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
        
        public GetArraySlice_Req getGetArraySlice() {
            try {
                if (this.getArraySliceBuilder_ == null) {
                    return this.getArraySlice_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (GetArraySlice_Req)this.getArraySliceBuilder_.getMessage();
        }
        
        public Builder setGetArraySlice(final GetArraySlice_Req getArraySlice_Req) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.getArraySliceBuilder_ != null) {
                                break Label_0042;
                            }
                            final GetArraySlice_Req getArraySlice_Req2 = getArraySlice_Req;
                            if (getArraySlice_Req2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final GetArraySlice_Req getArraySlice_Req2 = getArraySlice_Req;
                            if (getArraySlice_Req2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.getArraySlice_ = getArraySlice_Req;
                    this.onChanged();
                    break Label_0051;
                }
                this.getArraySliceBuilder_.setMessage((GeneratedMessage)getArraySlice_Req);
            }
            this.bitField0_ |= 0x2000000;
            return this;
        }
        
        public Builder setGetArraySlice(final GetArraySlice_Req.Builder builder) {
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
        
        public Builder mergeGetArraySlice(final GetArraySlice_Req p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getArraySliceBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       80
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    11: ldc             33554432
            //    13: iand           
            //    14: ldc             33554432
            //    16: if_icmpne       68
            //    19: goto            26
            //    22: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    25: athrow         
            //    26: aload_0        
            //    27: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getArraySlice_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetArraySlice_Req;
            //    30: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetArraySlice_Req.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetArraySlice_Req;
            //    33: if_acmpeq       68
            //    36: goto            43
            //    39: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    42: athrow         
            //    43: aload_0        
            //    44: aload_0        
            //    45: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getArraySlice_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetArraySlice_Req;
            //    48: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetArraySlice_Req.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetArraySlice_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetArraySlice_Req$Builder;
            //    51: aload_1        
            //    52: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetArraySlice_Req$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetArraySlice_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetArraySlice_Req$Builder;
            //    55: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetArraySlice_Req$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetArraySlice_Req;
            //    58: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getArraySlice_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetArraySlice_Req;
            //    61: goto            73
            //    64: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    67: athrow         
            //    68: aload_0        
            //    69: aload_1        
            //    70: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getArraySlice_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetArraySlice_Req;
            //    73: aload_0        
            //    74: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.onChanged:()V
            //    77: goto            89
            //    80: aload_0        
            //    81: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getArraySliceBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    84: aload_1        
            //    85: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    88: pop            
            //    89: aload_0        
            //    90: dup            
            //    91: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    94: ldc             33554432
            //    96: ior            
            //    97: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
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
                        this.getArraySlice_ = GetArraySlice_Req.getDefaultInstance();
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
        
        public GetArraySlice_Req.Builder getGetArraySliceBuilder() {
            this.bitField0_ |= 0x2000000;
            this.onChanged();
            return (GetArraySlice_Req.Builder)this.p().getBuilder();
        }
        
        public GetArraySlice_ReqOrBuilder getGetArraySliceOrBuilder() {
            try {
                if (this.getArraySliceBuilder_ != null) {
                    return (GetArraySlice_ReqOrBuilder)this.getArraySliceBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.getArraySlice_;
        }
        
        private SingleFieldBuilder<GetArraySlice_Req, GetArraySlice_Req.Builder, GetArraySlice_ReqOrBuilder> p() {
            try {
                if (this.getArraySliceBuilder_ == null) {
                    this.getArraySliceBuilder_ = (SingleFieldBuilder<GetArraySlice_Req, GetArraySlice_Req.Builder, GetArraySlice_ReqOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.getArraySlice_, this.getParentForChildren(), this.isClean());
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
        
        public GetValueData_Req getGetValueData() {
            try {
                if (this.getValueDataBuilder_ == null) {
                    return this.getValueData_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (GetValueData_Req)this.getValueDataBuilder_.getMessage();
        }
        
        public Builder setGetValueData(final GetValueData_Req getValueData_Req) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.getValueDataBuilder_ != null) {
                                break Label_0042;
                            }
                            final GetValueData_Req getValueData_Req2 = getValueData_Req;
                            if (getValueData_Req2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final GetValueData_Req getValueData_Req2 = getValueData_Req;
                            if (getValueData_Req2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.getValueData_ = getValueData_Req;
                    this.onChanged();
                    break Label_0051;
                }
                this.getValueDataBuilder_.setMessage((GeneratedMessage)getValueData_Req);
            }
            this.bitField0_ |= 0x4000000;
            return this;
        }
        
        public Builder setGetValueData(final GetValueData_Req.Builder builder) {
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
        
        public Builder mergeGetValueData(final GetValueData_Req p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getValueDataBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       80
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    11: ldc             67108864
            //    13: iand           
            //    14: ldc             67108864
            //    16: if_icmpne       68
            //    19: goto            26
            //    22: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    25: athrow         
            //    26: aload_0        
            //    27: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getValueData_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Req;
            //    30: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Req.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Req;
            //    33: if_acmpeq       68
            //    36: goto            43
            //    39: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    42: athrow         
            //    43: aload_0        
            //    44: aload_0        
            //    45: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getValueData_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Req;
            //    48: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Req.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Req$Builder;
            //    51: aload_1        
            //    52: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Req$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Req$Builder;
            //    55: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Req$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Req;
            //    58: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getValueData_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Req;
            //    61: goto            73
            //    64: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    67: athrow         
            //    68: aload_0        
            //    69: aload_1        
            //    70: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getValueData_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueData_Req;
            //    73: aload_0        
            //    74: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.onChanged:()V
            //    77: goto            89
            //    80: aload_0        
            //    81: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getValueDataBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    84: aload_1        
            //    85: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    88: pop            
            //    89: aload_0        
            //    90: dup            
            //    91: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    94: ldc             67108864
            //    96: ior            
            //    97: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
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
                        this.getValueData_ = GetValueData_Req.getDefaultInstance();
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
        
        public GetValueData_Req.Builder getGetValueDataBuilder() {
            this.bitField0_ |= 0x4000000;
            this.onChanged();
            return (GetValueData_Req.Builder)this.r().getBuilder();
        }
        
        public GetValueData_ReqOrBuilder getGetValueDataOrBuilder() {
            try {
                if (this.getValueDataBuilder_ != null) {
                    return (GetValueData_ReqOrBuilder)this.getValueDataBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.getValueData_;
        }
        
        private SingleFieldBuilder<GetValueData_Req, GetValueData_Req.Builder, GetValueData_ReqOrBuilder> r() {
            try {
                if (this.getValueDataBuilder_ == null) {
                    this.getValueDataBuilder_ = (SingleFieldBuilder<GetValueData_Req, GetValueData_Req.Builder, GetValueData_ReqOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.getValueData_, this.getParentForChildren(), this.isClean());
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
        
        public GetValueDescription_Req getGetValueDescription() {
            try {
                if (this.getValueDescriptionBuilder_ == null) {
                    return this.getValueDescription_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (GetValueDescription_Req)this.getValueDescriptionBuilder_.getMessage();
        }
        
        public Builder setGetValueDescription(final GetValueDescription_Req getValueDescription_Req) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.getValueDescriptionBuilder_ != null) {
                                break Label_0042;
                            }
                            final GetValueDescription_Req getValueDescription_Req2 = getValueDescription_Req;
                            if (getValueDescription_Req2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final GetValueDescription_Req getValueDescription_Req2 = getValueDescription_Req;
                            if (getValueDescription_Req2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.getValueDescription_ = getValueDescription_Req;
                    this.onChanged();
                    break Label_0051;
                }
                this.getValueDescriptionBuilder_.setMessage((GeneratedMessage)getValueDescription_Req);
            }
            this.bitField0_ |= 0x8000000;
            return this;
        }
        
        public Builder setGetValueDescription(final GetValueDescription_Req.Builder builder) {
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
        
        public Builder mergeGetValueDescription(final GetValueDescription_Req p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getValueDescriptionBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       80
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    11: ldc             134217728
            //    13: iand           
            //    14: ldc             134217728
            //    16: if_icmpne       68
            //    19: goto            26
            //    22: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    25: athrow         
            //    26: aload_0        
            //    27: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getValueDescription_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueDescription_Req;
            //    30: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueDescription_Req.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueDescription_Req;
            //    33: if_acmpeq       68
            //    36: goto            43
            //    39: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    42: athrow         
            //    43: aload_0        
            //    44: aload_0        
            //    45: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getValueDescription_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueDescription_Req;
            //    48: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueDescription_Req.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueDescription_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueDescription_Req$Builder;
            //    51: aload_1        
            //    52: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueDescription_Req$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueDescription_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueDescription_Req$Builder;
            //    55: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueDescription_Req$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueDescription_Req;
            //    58: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getValueDescription_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueDescription_Req;
            //    61: goto            73
            //    64: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    67: athrow         
            //    68: aload_0        
            //    69: aload_1        
            //    70: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getValueDescription_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueDescription_Req;
            //    73: aload_0        
            //    74: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.onChanged:()V
            //    77: goto            89
            //    80: aload_0        
            //    81: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getValueDescriptionBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    84: aload_1        
            //    85: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    88: pop            
            //    89: aload_0        
            //    90: dup            
            //    91: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    94: ldc             134217728
            //    96: ior            
            //    97: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
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
                        this.getValueDescription_ = GetValueDescription_Req.getDefaultInstance();
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
        
        public GetValueDescription_Req.Builder getGetValueDescriptionBuilder() {
            this.bitField0_ |= 0x8000000;
            this.onChanged();
            return (GetValueDescription_Req.Builder)this.A().getBuilder();
        }
        
        public GetValueDescription_ReqOrBuilder getGetValueDescriptionOrBuilder() {
            try {
                if (this.getValueDescriptionBuilder_ != null) {
                    return (GetValueDescription_ReqOrBuilder)this.getValueDescriptionBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.getValueDescription_;
        }
        
        private SingleFieldBuilder<GetValueDescription_Req, GetValueDescription_Req.Builder, GetValueDescription_ReqOrBuilder> A() {
            try {
                if (this.getValueDescriptionBuilder_ == null) {
                    this.getValueDescriptionBuilder_ = (SingleFieldBuilder<GetValueDescription_Req, GetValueDescription_Req.Builder, GetValueDescription_ReqOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.getValueDescription_, this.getParentForChildren(), this.isClean());
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
        
        public ValuesFilteringPolicy_Req getValuesFilteringPolicy() {
            try {
                if (this.valuesFilteringPolicyBuilder_ == null) {
                    return this.valuesFilteringPolicy_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (ValuesFilteringPolicy_Req)this.valuesFilteringPolicyBuilder_.getMessage();
        }
        
        public Builder setValuesFilteringPolicy(final ValuesFilteringPolicy_Req valuesFilteringPolicy_Req) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.valuesFilteringPolicyBuilder_ != null) {
                                break Label_0042;
                            }
                            final ValuesFilteringPolicy_Req valuesFilteringPolicy_Req2 = valuesFilteringPolicy_Req;
                            if (valuesFilteringPolicy_Req2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final ValuesFilteringPolicy_Req valuesFilteringPolicy_Req2 = valuesFilteringPolicy_Req;
                            if (valuesFilteringPolicy_Req2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.valuesFilteringPolicy_ = valuesFilteringPolicy_Req;
                    this.onChanged();
                    break Label_0051;
                }
                this.valuesFilteringPolicyBuilder_.setMessage((GeneratedMessage)valuesFilteringPolicy_Req);
            }
            this.bitField0_ |= 0x10000000;
            return this;
        }
        
        public Builder setValuesFilteringPolicy(final ValuesFilteringPolicy_Req.Builder builder) {
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
        
        public Builder mergeValuesFilteringPolicy(final ValuesFilteringPolicy_Req p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.valuesFilteringPolicyBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       80
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    11: ldc             268435456
            //    13: iand           
            //    14: ldc             268435456
            //    16: if_icmpne       68
            //    19: goto            26
            //    22: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    25: athrow         
            //    26: aload_0        
            //    27: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.valuesFilteringPolicy_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ValuesFilteringPolicy_Req;
            //    30: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ValuesFilteringPolicy_Req.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ValuesFilteringPolicy_Req;
            //    33: if_acmpeq       68
            //    36: goto            43
            //    39: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    42: athrow         
            //    43: aload_0        
            //    44: aload_0        
            //    45: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.valuesFilteringPolicy_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ValuesFilteringPolicy_Req;
            //    48: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ValuesFilteringPolicy_Req.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ValuesFilteringPolicy_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ValuesFilteringPolicy_Req$Builder;
            //    51: aload_1        
            //    52: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ValuesFilteringPolicy_Req$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ValuesFilteringPolicy_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ValuesFilteringPolicy_Req$Builder;
            //    55: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ValuesFilteringPolicy_Req$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ValuesFilteringPolicy_Req;
            //    58: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.valuesFilteringPolicy_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ValuesFilteringPolicy_Req;
            //    61: goto            73
            //    64: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    67: athrow         
            //    68: aload_0        
            //    69: aload_1        
            //    70: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.valuesFilteringPolicy_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ValuesFilteringPolicy_Req;
            //    73: aload_0        
            //    74: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.onChanged:()V
            //    77: goto            89
            //    80: aload_0        
            //    81: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.valuesFilteringPolicyBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    84: aload_1        
            //    85: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    88: pop            
            //    89: aload_0        
            //    90: dup            
            //    91: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    94: ldc             268435456
            //    96: ior            
            //    97: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
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
                        this.valuesFilteringPolicy_ = ValuesFilteringPolicy_Req.getDefaultInstance();
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
        
        public ValuesFilteringPolicy_Req.Builder getValuesFilteringPolicyBuilder() {
            this.bitField0_ |= 0x10000000;
            this.onChanged();
            return (ValuesFilteringPolicy_Req.Builder)this.y().getBuilder();
        }
        
        public ValuesFilteringPolicy_ReqOrBuilder getValuesFilteringPolicyOrBuilder() {
            try {
                if (this.valuesFilteringPolicyBuilder_ != null) {
                    return (ValuesFilteringPolicy_ReqOrBuilder)this.valuesFilteringPolicyBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.valuesFilteringPolicy_;
        }
        
        private SingleFieldBuilder<ValuesFilteringPolicy_Req, ValuesFilteringPolicy_Req.Builder, ValuesFilteringPolicy_ReqOrBuilder> y() {
            try {
                if (this.valuesFilteringPolicyBuilder_ == null) {
                    this.valuesFilteringPolicyBuilder_ = (SingleFieldBuilder<ValuesFilteringPolicy_Req, ValuesFilteringPolicy_Req.Builder, ValuesFilteringPolicy_ReqOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.valuesFilteringPolicy_, this.getParentForChildren(), this.isClean());
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
        
        public ConnectPlatform_Req getConnectPlatform() {
            try {
                if (this.connectPlatformBuilder_ == null) {
                    return this.connectPlatform_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (ConnectPlatform_Req)this.connectPlatformBuilder_.getMessage();
        }
        
        public Builder setConnectPlatform(final ConnectPlatform_Req connectPlatform_Req) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.connectPlatformBuilder_ != null) {
                                break Label_0042;
                            }
                            final ConnectPlatform_Req connectPlatform_Req2 = connectPlatform_Req;
                            if (connectPlatform_Req2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final ConnectPlatform_Req connectPlatform_Req2 = connectPlatform_Req;
                            if (connectPlatform_Req2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.connectPlatform_ = connectPlatform_Req;
                    this.onChanged();
                    break Label_0051;
                }
                this.connectPlatformBuilder_.setMessage((GeneratedMessage)connectPlatform_Req);
            }
            this.bitField0_ |= 0x20000000;
            return this;
        }
        
        public Builder setConnectPlatform(final ConnectPlatform_Req.Builder builder) {
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
        
        public Builder mergeConnectPlatform(final ConnectPlatform_Req p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.connectPlatformBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       80
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    11: ldc             536870912
            //    13: iand           
            //    14: ldc             536870912
            //    16: if_icmpne       68
            //    19: goto            26
            //    22: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    25: athrow         
            //    26: aload_0        
            //    27: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.connectPlatform_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ConnectPlatform_Req;
            //    30: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ConnectPlatform_Req.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ConnectPlatform_Req;
            //    33: if_acmpeq       68
            //    36: goto            43
            //    39: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    42: athrow         
            //    43: aload_0        
            //    44: aload_0        
            //    45: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.connectPlatform_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ConnectPlatform_Req;
            //    48: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ConnectPlatform_Req.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ConnectPlatform_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ConnectPlatform_Req$Builder;
            //    51: aload_1        
            //    52: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ConnectPlatform_Req$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ConnectPlatform_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ConnectPlatform_Req$Builder;
            //    55: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ConnectPlatform_Req$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ConnectPlatform_Req;
            //    58: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.connectPlatform_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ConnectPlatform_Req;
            //    61: goto            73
            //    64: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    67: athrow         
            //    68: aload_0        
            //    69: aload_1        
            //    70: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.connectPlatform_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ConnectPlatform_Req;
            //    73: aload_0        
            //    74: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.onChanged:()V
            //    77: goto            89
            //    80: aload_0        
            //    81: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.connectPlatformBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    84: aload_1        
            //    85: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    88: pop            
            //    89: aload_0        
            //    90: dup            
            //    91: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    94: ldc             536870912
            //    96: ior            
            //    97: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
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
                        this.connectPlatform_ = ConnectPlatform_Req.getDefaultInstance();
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
        
        public ConnectPlatform_Req.Builder getConnectPlatformBuilder() {
            this.bitField0_ |= 0x20000000;
            this.onChanged();
            return (ConnectPlatform_Req.Builder)this.j().getBuilder();
        }
        
        public ConnectPlatform_ReqOrBuilder getConnectPlatformOrBuilder() {
            try {
                if (this.connectPlatformBuilder_ != null) {
                    return (ConnectPlatform_ReqOrBuilder)this.connectPlatformBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.connectPlatform_;
        }
        
        private SingleFieldBuilder<ConnectPlatform_Req, ConnectPlatform_Req.Builder, ConnectPlatform_ReqOrBuilder> j() {
            try {
                if (this.connectPlatformBuilder_ == null) {
                    this.connectPlatformBuilder_ = (SingleFieldBuilder<ConnectPlatform_Req, ConnectPlatform_Req.Builder, ConnectPlatform_ReqOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.connectPlatform_, this.getParentForChildren(), this.isClean());
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
        
        public GetValueAddress_Req getGetValueAddress() {
            try {
                if (this.getValueAddressBuilder_ == null) {
                    return this.getValueAddress_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (GetValueAddress_Req)this.getValueAddressBuilder_.getMessage();
        }
        
        public Builder setGetValueAddress(final GetValueAddress_Req getValueAddress_Req) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.getValueAddressBuilder_ != null) {
                                break Label_0042;
                            }
                            final GetValueAddress_Req getValueAddress_Req2 = getValueAddress_Req;
                            if (getValueAddress_Req2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final GetValueAddress_Req getValueAddress_Req2 = getValueAddress_Req;
                            if (getValueAddress_Req2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.getValueAddress_ = getValueAddress_Req;
                    this.onChanged();
                    break Label_0051;
                }
                this.getValueAddressBuilder_.setMessage((GeneratedMessage)getValueAddress_Req);
            }
            this.bitField0_ |= 0x40000000;
            return this;
        }
        
        public Builder setGetValueAddress(final GetValueAddress_Req.Builder builder) {
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
        
        public Builder mergeGetValueAddress(final GetValueAddress_Req p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getValueAddressBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       80
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    11: ldc             1073741824
            //    13: iand           
            //    14: ldc             1073741824
            //    16: if_icmpne       68
            //    19: goto            26
            //    22: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    25: athrow         
            //    26: aload_0        
            //    27: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getValueAddress_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueAddress_Req;
            //    30: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueAddress_Req.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueAddress_Req;
            //    33: if_acmpeq       68
            //    36: goto            43
            //    39: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    42: athrow         
            //    43: aload_0        
            //    44: aload_0        
            //    45: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getValueAddress_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueAddress_Req;
            //    48: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueAddress_Req.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueAddress_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueAddress_Req$Builder;
            //    51: aload_1        
            //    52: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueAddress_Req$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueAddress_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueAddress_Req$Builder;
            //    55: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueAddress_Req$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueAddress_Req;
            //    58: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getValueAddress_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueAddress_Req;
            //    61: goto            73
            //    64: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    67: athrow         
            //    68: aload_0        
            //    69: aload_1        
            //    70: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getValueAddress_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$GetValueAddress_Req;
            //    73: aload_0        
            //    74: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.onChanged:()V
            //    77: goto            89
            //    80: aload_0        
            //    81: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.getValueAddressBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    84: aload_1        
            //    85: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    88: pop            
            //    89: aload_0        
            //    90: dup            
            //    91: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    94: ldc             1073741824
            //    96: ior            
            //    97: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
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
                        this.getValueAddress_ = GetValueAddress_Req.getDefaultInstance();
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
        
        public GetValueAddress_Req.Builder getGetValueAddressBuilder() {
            this.bitField0_ |= 0x40000000;
            this.onChanged();
            return (GetValueAddress_Req.Builder)this.x().getBuilder();
        }
        
        public GetValueAddress_ReqOrBuilder getGetValueAddressOrBuilder() {
            try {
                if (this.getValueAddressBuilder_ != null) {
                    return (GetValueAddress_ReqOrBuilder)this.getValueAddressBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.getValueAddress_;
        }
        
        private SingleFieldBuilder<GetValueAddress_Req, GetValueAddress_Req.Builder, GetValueAddress_ReqOrBuilder> x() {
            try {
                if (this.getValueAddressBuilder_ == null) {
                    this.getValueAddressBuilder_ = (SingleFieldBuilder<GetValueAddress_Req, GetValueAddress_Req.Builder, GetValueAddress_ReqOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.getValueAddress_, this.getParentForChildren(), this.isClean());
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
        
        public HandleSignal_Req getHandleSignal() {
            try {
                if (this.handleSignalBuilder_ == null) {
                    return this.handleSignal_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (HandleSignal_Req)this.handleSignalBuilder_.getMessage();
        }
        
        public Builder setHandleSignal(final HandleSignal_Req handleSignal_Req) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.handleSignalBuilder_ != null) {
                                break Label_0042;
                            }
                            final HandleSignal_Req handleSignal_Req2 = handleSignal_Req;
                            if (handleSignal_Req2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final HandleSignal_Req handleSignal_Req2 = handleSignal_Req;
                            if (handleSignal_Req2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.handleSignal_ = handleSignal_Req;
                    this.onChanged();
                    break Label_0051;
                }
                this.handleSignalBuilder_.setMessage((GeneratedMessage)handleSignal_Req);
            }
            this.bitField0_ |= Integer.MIN_VALUE;
            return this;
        }
        
        public Builder setHandleSignal(final HandleSignal_Req.Builder builder) {
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
        
        public Builder mergeHandleSignal(final HandleSignal_Req p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.handleSignalBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       80
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    11: ldc             -2147483648
            //    13: iand           
            //    14: ldc             -2147483648
            //    16: if_icmpne       68
            //    19: goto            26
            //    22: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    25: athrow         
            //    26: aload_0        
            //    27: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.handleSignal_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleSignal_Req;
            //    30: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleSignal_Req.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleSignal_Req;
            //    33: if_acmpeq       68
            //    36: goto            43
            //    39: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    42: athrow         
            //    43: aload_0        
            //    44: aload_0        
            //    45: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.handleSignal_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleSignal_Req;
            //    48: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleSignal_Req.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleSignal_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleSignal_Req$Builder;
            //    51: aload_1        
            //    52: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleSignal_Req$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleSignal_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleSignal_Req$Builder;
            //    55: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleSignal_Req$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleSignal_Req;
            //    58: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.handleSignal_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleSignal_Req;
            //    61: goto            73
            //    64: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    67: athrow         
            //    68: aload_0        
            //    69: aload_1        
            //    70: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.handleSignal_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$HandleSignal_Req;
            //    73: aload_0        
            //    74: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.onChanged:()V
            //    77: goto            89
            //    80: aload_0        
            //    81: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.handleSignalBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    84: aload_1        
            //    85: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    88: pop            
            //    89: aload_0        
            //    90: dup            
            //    91: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
            //    94: ldc             -2147483648
            //    96: ior            
            //    97: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField0_:I
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
                        this.handleSignal_ = HandleSignal_Req.getDefaultInstance();
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
        
        public HandleSignal_Req.Builder getHandleSignalBuilder() {
            this.bitField0_ |= Integer.MIN_VALUE;
            this.onChanged();
            return (HandleSignal_Req.Builder)this.D().getBuilder();
        }
        
        public HandleSignal_ReqOrBuilder getHandleSignalOrBuilder() {
            try {
                if (this.handleSignalBuilder_ != null) {
                    return (HandleSignal_ReqOrBuilder)this.handleSignalBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.handleSignal_;
        }
        
        private SingleFieldBuilder<HandleSignal_Req, HandleSignal_Req.Builder, HandleSignal_ReqOrBuilder> D() {
            try {
                if (this.handleSignalBuilder_ == null) {
                    this.handleSignalBuilder_ = (SingleFieldBuilder<HandleSignal_Req, HandleSignal_Req.Builder, HandleSignal_ReqOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.handleSignal_, this.getParentForChildren(), this.isClean());
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
        
        public ExecuteShellCommand_Req getExecuteShellCommand() {
            try {
                if (this.executeShellCommandBuilder_ == null) {
                    return this.executeShellCommand_;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return (ExecuteShellCommand_Req)this.executeShellCommandBuilder_.getMessage();
        }
        
        public Builder setExecuteShellCommand(final ExecuteShellCommand_Req executeShellCommand_Req) {
            Label_0051: {
                Label_0042: {
                    Label_0018: {
                        try {
                            if (this.executeShellCommandBuilder_ != null) {
                                break Label_0042;
                            }
                            final ExecuteShellCommand_Req executeShellCommand_Req2 = executeShellCommand_Req;
                            if (executeShellCommand_Req2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final ExecuteShellCommand_Req executeShellCommand_Req2 = executeShellCommand_Req;
                            if (executeShellCommand_Req2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.executeShellCommand_ = executeShellCommand_Req;
                    this.onChanged();
                    break Label_0051;
                }
                this.executeShellCommandBuilder_.setMessage((GeneratedMessage)executeShellCommand_Req);
            }
            this.bitField1_ |= 0x1;
            return this;
        }
        
        public Builder setExecuteShellCommand(final ExecuteShellCommand_Req.Builder builder) {
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
        
        public Builder mergeExecuteShellCommand(final ExecuteShellCommand_Req p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.executeShellCommandBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //     4: ifnonnull       78
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField1_:I
            //    11: iconst_1       
            //    12: iand           
            //    13: iconst_1       
            //    14: if_icmpne       66
            //    17: goto            24
            //    20: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    23: athrow         
            //    24: aload_0        
            //    25: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.executeShellCommand_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ExecuteShellCommand_Req;
            //    28: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ExecuteShellCommand_Req.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ExecuteShellCommand_Req;
            //    31: if_acmpeq       66
            //    34: goto            41
            //    37: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    40: athrow         
            //    41: aload_0        
            //    42: aload_0        
            //    43: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.executeShellCommand_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ExecuteShellCommand_Req;
            //    46: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ExecuteShellCommand_Req.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ExecuteShellCommand_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ExecuteShellCommand_Req$Builder;
            //    49: aload_1        
            //    50: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ExecuteShellCommand_Req$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ExecuteShellCommand_Req;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ExecuteShellCommand_Req$Builder;
            //    53: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ExecuteShellCommand_Req$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ExecuteShellCommand_Req;
            //    56: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.executeShellCommand_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ExecuteShellCommand_Req;
            //    59: goto            71
            //    62: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
            //    65: athrow         
            //    66: aload_0        
            //    67: aload_1        
            //    68: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.executeShellCommand_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$ExecuteShellCommand_Req;
            //    71: aload_0        
            //    72: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.onChanged:()V
            //    75: goto            87
            //    78: aload_0        
            //    79: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.executeShellCommandBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
            //    82: aload_1        
            //    83: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
            //    86: pop            
            //    87: aload_0        
            //    88: dup            
            //    89: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField1_:I
            //    92: iconst_1       
            //    93: ior            
            //    94: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Protocol$CompositeRequest$Builder.bitField1_:I
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
                        this.executeShellCommand_ = ExecuteShellCommand_Req.getDefaultInstance();
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
        
        public ExecuteShellCommand_Req.Builder getExecuteShellCommandBuilder() {
            this.bitField1_ |= 0x1;
            this.onChanged();
            return (ExecuteShellCommand_Req.Builder)this.B().getBuilder();
        }
        
        public ExecuteShellCommand_ReqOrBuilder getExecuteShellCommandOrBuilder() {
            try {
                if (this.executeShellCommandBuilder_ != null) {
                    return (ExecuteShellCommand_ReqOrBuilder)this.executeShellCommandBuilder_.getMessageOrBuilder();
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            return this.executeShellCommand_;
        }
        
        private SingleFieldBuilder<ExecuteShellCommand_Req, ExecuteShellCommand_Req.Builder, ExecuteShellCommand_ReqOrBuilder> B() {
            try {
                if (this.executeShellCommandBuilder_ == null) {
                    this.executeShellCommandBuilder_ = (SingleFieldBuilder<ExecuteShellCommand_Req, ExecuteShellCommand_Req.Builder, ExecuteShellCommand_ReqOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.executeShellCommand_, this.getParentForChildren(), this.isClean());
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
