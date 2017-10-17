// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.MessageLite;
import com.google.protobuf.AbstractMessage;
import java.io.IOException;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.Message;
import com.google.protobuf.Descriptors;
import com.google.protobuf.SingleFieldBuilder;
import com.google.protobuf.GeneratedMessage;

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
            if (CompositeRequest.access$65700()) {
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
                    CompositeRequest.access$65902(compositeRequest, this.createTarget_);
                    break Label_0077;
                }
            }
            catch (NullPointerException ex) {
                throw b(ex);
            }
            CompositeRequest.access$65902(compositeRequest, (CreateTarget_Req)this.createTargetBuilder_.build());
        }
        if ((bitField0_ & 0x2) == 0x2) {
            n |= 0x2;
        }
        Label_0128: {
            try {
                if (this.launchBuilder_ == null) {
                    CompositeRequest.access$66002(compositeRequest, this.launch_);
                    break Label_0128;
                }
            }
            catch (NullPointerException ex2) {
                throw b(ex2);
            }
            CompositeRequest.access$66002(compositeRequest, (Launch_Req)this.launchBuilder_.build());
        }
        if ((bitField0_ & 0x4) == 0x4) {
            n |= 0x4;
        }
        Label_0179: {
            try {
                if (this.exitBuilder_ == null) {
                    CompositeRequest.access$66102(compositeRequest, this.exit_);
                    break Label_0179;
                }
            }
            catch (NullPointerException ex3) {
                throw b(ex3);
            }
            CompositeRequest.access$66102(compositeRequest, (Exit_Req)this.exitBuilder_.build());
        }
        if ((bitField0_ & 0x8) == 0x8) {
            n |= 0x8;
        }
        Label_0233: {
            try {
                if (this.continueBuilder_ == null) {
                    CompositeRequest.access$66202(compositeRequest, this.continue_);
                    break Label_0233;
                }
            }
            catch (NullPointerException ex4) {
                throw b(ex4);
            }
            CompositeRequest.access$66202(compositeRequest, (Continue_Req)this.continueBuilder_.build());
        }
        if ((bitField0_ & 0x10) == 0x10) {
            n |= 0x10;
        }
        Label_0287: {
            try {
                if (this.suspendBuilder_ == null) {
                    CompositeRequest.access$66302(compositeRequest, this.suspend_);
                    break Label_0287;
                }
            }
            catch (NullPointerException ex5) {
                throw b(ex5);
            }
            CompositeRequest.access$66302(compositeRequest, (Suspend_Req)this.suspendBuilder_.build());
        }
        if ((bitField0_ & 0x20) == 0x20) {
            n |= 0x20;
        }
        Label_0341: {
            try {
                if (this.getThreadsBuilder_ == null) {
                    CompositeRequest.access$66402(compositeRequest, this.getThreads_);
                    break Label_0341;
                }
            }
            catch (NullPointerException ex6) {
                throw b(ex6);
            }
            CompositeRequest.access$66402(compositeRequest, (GetThreads_Req)this.getThreadsBuilder_.build());
        }
        if ((bitField0_ & 0x40) == 0x40) {
            n |= 0x40;
        }
        Label_0395: {
            try {
                if (this.getFramesBuilder_ == null) {
                    CompositeRequest.access$66502(compositeRequest, this.getFrames_);
                    break Label_0395;
                }
            }
            catch (NullPointerException ex7) {
                throw b(ex7);
            }
            CompositeRequest.access$66502(compositeRequest, (GetFrames_Req)this.getFramesBuilder_.build());
        }
        if ((bitField0_ & 0x80) == 0x80) {
            n |= 0x80;
        }
        Label_0452: {
            try {
                if (this.addBreakpointBuilder_ == null) {
                    CompositeRequest.access$66602(compositeRequest, this.addBreakpoint_);
                    break Label_0452;
                }
            }
            catch (NullPointerException ex8) {
                throw b(ex8);
            }
            CompositeRequest.access$66602(compositeRequest, (AddBreakpoint_Req)this.addBreakpointBuilder_.build());
        }
        if ((bitField0_ & 0x100) == 0x100) {
            n |= 0x100;
        }
        Label_0509: {
            try {
                if (this.removeBreakpointBuilder_ == null) {
                    CompositeRequest.access$66702(compositeRequest, this.removeBreakpoint_);
                    break Label_0509;
                }
            }
            catch (NullPointerException ex9) {
                throw b(ex9);
            }
            CompositeRequest.access$66702(compositeRequest, (RemoveBreakpoint_Req)this.removeBreakpointBuilder_.build());
        }
        if ((bitField0_ & 0x200) == 0x200) {
            n |= 0x200;
        }
        Label_0566: {
            try {
                if (this.stepIntoBuilder_ == null) {
                    CompositeRequest.access$66802(compositeRequest, this.stepInto_);
                    break Label_0566;
                }
            }
            catch (NullPointerException ex10) {
                throw b(ex10);
            }
            CompositeRequest.access$66802(compositeRequest, (StepInto_Req)this.stepIntoBuilder_.build());
        }
        if ((bitField0_ & 0x400) == 0x400) {
            n |= 0x400;
        }
        Label_0623: {
            try {
                if (this.stepOverBuilder_ == null) {
                    CompositeRequest.access$66902(compositeRequest, this.stepOver_);
                    break Label_0623;
                }
            }
            catch (NullPointerException ex11) {
                throw b(ex11);
            }
            CompositeRequest.access$66902(compositeRequest, (StepOver_Req)this.stepOverBuilder_.build());
        }
        if ((bitField0_ & 0x800) == 0x800) {
            n |= 0x800;
        }
        Label_0680: {
            try {
                if (this.stepOutBuilder_ == null) {
                    CompositeRequest.access$67002(compositeRequest, this.stepOut_);
                    break Label_0680;
                }
            }
            catch (NullPointerException ex12) {
                throw b(ex12);
            }
            CompositeRequest.access$67002(compositeRequest, (StepOut_Req)this.stepOutBuilder_.build());
        }
        if ((bitField0_ & 0x1000) == 0x1000) {
            n |= 0x1000;
        }
        Label_0737: {
            try {
                if (this.evaluateExpressionBuilder_ == null) {
                    CompositeRequest.access$67102(compositeRequest, this.evaluateExpression_);
                    break Label_0737;
                }
            }
            catch (NullPointerException ex13) {
                throw b(ex13);
            }
            CompositeRequest.access$67102(compositeRequest, (EvaluateExpression_Req)this.evaluateExpressionBuilder_.build());
        }
        if ((bitField0_ & 0x2000) == 0x2000) {
            n |= 0x2000;
        }
        Label_0794: {
            try {
                if (this.getValueChildrenBuilder_ == null) {
                    CompositeRequest.access$67202(compositeRequest, this.getValueChildren_);
                    break Label_0794;
                }
            }
            catch (NullPointerException ex14) {
                throw b(ex14);
            }
            CompositeRequest.access$67202(compositeRequest, (GetValueChildren_Req)this.getValueChildrenBuilder_.build());
        }
        if ((bitField0_ & 0x4000) == 0x4000) {
            n |= 0x4000;
        }
        Label_0851: {
            try {
                if (this.getVarsBuilder_ == null) {
                    CompositeRequest.access$67302(compositeRequest, this.getVars_);
                    break Label_0851;
                }
            }
            catch (NullPointerException ex15) {
                throw b(ex15);
            }
            CompositeRequest.access$67302(compositeRequest, (GetVars_Req)this.getVarsBuilder_.build());
        }
        if ((bitField0_ & 0x8000) == 0x8000) {
            n |= 0x8000;
        }
        Label_0905: {
            try {
                if (this.handleConsoleCommandBuilder_ == null) {
                    CompositeRequest.access$67402(compositeRequest, this.handleConsoleCommand_);
                    break Label_0905;
                }
            }
            catch (NullPointerException ex16) {
                throw b(ex16);
            }
            CompositeRequest.access$67402(compositeRequest, (HandleConsoleCommand_Req)this.handleConsoleCommandBuilder_.build());
        }
        if ((bitField0_ & 0x10000) == 0x10000) {
            n |= 0x10000;
        }
        Label_0959: {
            try {
                if (this.handleCompletionBuilder_ == null) {
                    CompositeRequest.access$67502(compositeRequest, this.handleCompletion_);
                    break Label_0959;
                }
            }
            catch (NullPointerException ex17) {
                throw b(ex17);
            }
            CompositeRequest.access$67502(compositeRequest, (HandleCompletion_Req)this.handleCompletionBuilder_.build());
        }
        if ((bitField0_ & 0x20000) == 0x20000) {
            n |= 0x20000;
        }
        Label_1013: {
            try {
                if (this.attachBuilder_ == null) {
                    CompositeRequest.access$67602(compositeRequest, this.attach_);
                    break Label_1013;
                }
            }
            catch (NullPointerException ex18) {
                throw b(ex18);
            }
            CompositeRequest.access$67602(compositeRequest, (Attach_Req)this.attachBuilder_.build());
        }
        if ((bitField0_ & 0x40000) == 0x40000) {
            n |= 0x40000;
        }
        Label_1067: {
            try {
                if (this.attachByNameBuilder_ == null) {
                    CompositeRequest.access$67702(compositeRequest, this.attachByName_);
                    break Label_1067;
                }
            }
            catch (NullPointerException ex19) {
                throw b(ex19);
            }
            CompositeRequest.access$67702(compositeRequest, (AttachByName_Req)this.attachByNameBuilder_.build());
        }
        if ((bitField0_ & 0x80000) == 0x80000) {
            n |= 0x80000;
        }
        Label_1121: {
            try {
                if (this.dispatchInputBuilder_ == null) {
                    CompositeRequest.access$67802(compositeRequest, this.dispatchInput_);
                    break Label_1121;
                }
            }
            catch (NullPointerException ex20) {
                throw b(ex20);
            }
            CompositeRequest.access$67802(compositeRequest, (DispatchInput_Req)this.dispatchInputBuilder_.build());
        }
        if ((bitField0_ & 0x100000) == 0x100000) {
            n |= 0x100000;
        }
        Label_1175: {
            try {
                if (this.addWatchpointBuilder_ == null) {
                    CompositeRequest.access$67902(compositeRequest, this.addWatchpoint_);
                    break Label_1175;
                }
            }
            catch (NullPointerException ex21) {
                throw b(ex21);
            }
            CompositeRequest.access$67902(compositeRequest, (AddWatchpoint_Req)this.addWatchpointBuilder_.build());
        }
        if ((bitField0_ & 0x200000) == 0x200000) {
            n |= 0x200000;
        }
        Label_1229: {
            try {
                if (this.removeWatchpointBuilder_ == null) {
                    CompositeRequest.access$68002(compositeRequest, this.removeWatchpoint_);
                    break Label_1229;
                }
            }
            catch (NullPointerException ex22) {
                throw b(ex22);
            }
            CompositeRequest.access$68002(compositeRequest, (RemoveWatchpoint_Req)this.removeWatchpointBuilder_.build());
        }
        if ((bitField0_ & 0x400000) == 0x400000) {
            n |= 0x400000;
        }
        Label_1283: {
            try {
                if (this.detachBuilder_ == null) {
                    CompositeRequest.access$68102(compositeRequest, this.detach_);
                    break Label_1283;
                }
            }
            catch (NullPointerException ex23) {
                throw b(ex23);
            }
            CompositeRequest.access$68102(compositeRequest, (Detach_Req)this.detachBuilder_.build());
        }
        if ((bitField0_ & 0x800000) == 0x800000) {
            n |= 0x800000;
        }
        Label_1337: {
            try {
                if (this.killBuilder_ == null) {
                    CompositeRequest.access$68202(compositeRequest, this.kill_);
                    break Label_1337;
                }
            }
            catch (NullPointerException ex24) {
                throw b(ex24);
            }
            CompositeRequest.access$68202(compositeRequest, (Kill_Req)this.killBuilder_.build());
        }
        if ((bitField0_ & 0x1000000) == 0x1000000) {
            n |= 0x1000000;
        }
        Label_1391: {
            try {
                if (this.getChildrenCountBuilder_ == null) {
                    CompositeRequest.access$68302(compositeRequest, this.getChildrenCount_);
                    break Label_1391;
                }
            }
            catch (NullPointerException ex25) {
                throw b(ex25);
            }
            CompositeRequest.access$68302(compositeRequest, (GetChildrenCount_Req)this.getChildrenCountBuilder_.build());
        }
        if ((bitField0_ & 0x2000000) == 0x2000000) {
            n |= 0x2000000;
        }
        Label_1445: {
            try {
                if (this.getArraySliceBuilder_ == null) {
                    CompositeRequest.access$68402(compositeRequest, this.getArraySlice_);
                    break Label_1445;
                }
            }
            catch (NullPointerException ex26) {
                throw b(ex26);
            }
            CompositeRequest.access$68402(compositeRequest, (GetArraySlice_Req)this.getArraySliceBuilder_.build());
        }
        if ((bitField0_ & 0x4000000) == 0x4000000) {
            n |= 0x4000000;
        }
        Label_1499: {
            try {
                if (this.getValueDataBuilder_ == null) {
                    CompositeRequest.access$68502(compositeRequest, this.getValueData_);
                    break Label_1499;
                }
            }
            catch (NullPointerException ex27) {
                throw b(ex27);
            }
            CompositeRequest.access$68502(compositeRequest, (GetValueData_Req)this.getValueDataBuilder_.build());
        }
        if ((bitField0_ & 0x8000000) == 0x8000000) {
            n |= 0x8000000;
        }
        Label_1553: {
            try {
                if (this.getValueDescriptionBuilder_ == null) {
                    CompositeRequest.access$68602(compositeRequest, this.getValueDescription_);
                    break Label_1553;
                }
            }
            catch (NullPointerException ex28) {
                throw b(ex28);
            }
            CompositeRequest.access$68602(compositeRequest, (GetValueDescription_Req)this.getValueDescriptionBuilder_.build());
        }
        if ((bitField0_ & 0x10000000) == 0x10000000) {
            n |= 0x10000000;
        }
        Label_1607: {
            try {
                if (this.valuesFilteringPolicyBuilder_ == null) {
                    CompositeRequest.access$68702(compositeRequest, this.valuesFilteringPolicy_);
                    break Label_1607;
                }
            }
            catch (NullPointerException ex29) {
                throw b(ex29);
            }
            CompositeRequest.access$68702(compositeRequest, (ValuesFilteringPolicy_Req)this.valuesFilteringPolicyBuilder_.build());
        }
        if ((bitField0_ & 0x20000000) == 0x20000000) {
            n |= 0x20000000;
        }
        Label_1661: {
            try {
                if (this.connectPlatformBuilder_ == null) {
                    CompositeRequest.access$68802(compositeRequest, this.connectPlatform_);
                    break Label_1661;
                }
            }
            catch (NullPointerException ex30) {
                throw b(ex30);
            }
            CompositeRequest.access$68802(compositeRequest, (ConnectPlatform_Req)this.connectPlatformBuilder_.build());
        }
        if ((bitField0_ & 0x40000000) == 0x40000000) {
            n |= 0x40000000;
        }
        Label_1715: {
            try {
                if (this.getValueAddressBuilder_ == null) {
                    CompositeRequest.access$68902(compositeRequest, this.getValueAddress_);
                    break Label_1715;
                }
            }
            catch (NullPointerException ex31) {
                throw b(ex31);
            }
            CompositeRequest.access$68902(compositeRequest, (GetValueAddress_Req)this.getValueAddressBuilder_.build());
        }
        if ((bitField0_ & Integer.MIN_VALUE) == Integer.MIN_VALUE) {
            n |= Integer.MIN_VALUE;
        }
        Label_1769: {
            try {
                if (this.handleSignalBuilder_ == null) {
                    CompositeRequest.access$69002(compositeRequest, this.handleSignal_);
                    break Label_1769;
                }
            }
            catch (NullPointerException ex32) {
                throw b(ex32);
            }
            CompositeRequest.access$69002(compositeRequest, (HandleSignal_Req)this.handleSignalBuilder_.build());
        }
        if ((bitField1_ & 0x1) == 0x1) {
            n2 |= 0x1;
        }
        Label_1820: {
            try {
                if (this.executeShellCommandBuilder_ == null) {
                    CompositeRequest.access$69102(compositeRequest, this.executeShellCommand_);
                    break Label_1820;
                }
            }
            catch (NullPointerException ex33) {
                throw b(ex33);
            }
            CompositeRequest.access$69102(compositeRequest, (ExecuteShellCommand_Req)this.executeShellCommandBuilder_.build());
        }
        CompositeRequest.access$69202(compositeRequest, n);
        CompositeRequest.access$69302(compositeRequest, n2);
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
