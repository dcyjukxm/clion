// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.MessageOrBuilder;

public interface CompositeRequestOrBuilder extends MessageOrBuilder
{
    boolean hasCreateTarget();
    
    CreateTarget_Req getCreateTarget();
    
    CreateTarget_ReqOrBuilder getCreateTargetOrBuilder();
    
    boolean hasLaunch();
    
    Launch_Req getLaunch();
    
    Launch_ReqOrBuilder getLaunchOrBuilder();
    
    boolean hasExit();
    
    Exit_Req getExit();
    
    Exit_ReqOrBuilder getExitOrBuilder();
    
    boolean hasContinue();
    
    Continue_Req getContinue();
    
    Continue_ReqOrBuilder getContinueOrBuilder();
    
    boolean hasSuspend();
    
    Suspend_Req getSuspend();
    
    Suspend_ReqOrBuilder getSuspendOrBuilder();
    
    boolean hasGetThreads();
    
    GetThreads_Req getGetThreads();
    
    GetThreads_ReqOrBuilder getGetThreadsOrBuilder();
    
    boolean hasGetFrames();
    
    GetFrames_Req getGetFrames();
    
    GetFrames_ReqOrBuilder getGetFramesOrBuilder();
    
    boolean hasAddBreakpoint();
    
    AddBreakpoint_Req getAddBreakpoint();
    
    AddBreakpoint_ReqOrBuilder getAddBreakpointOrBuilder();
    
    boolean hasRemoveBreakpoint();
    
    RemoveBreakpoint_Req getRemoveBreakpoint();
    
    RemoveBreakpoint_ReqOrBuilder getRemoveBreakpointOrBuilder();
    
    boolean hasStepInto();
    
    StepInto_Req getStepInto();
    
    StepInto_ReqOrBuilder getStepIntoOrBuilder();
    
    boolean hasStepOver();
    
    StepOver_Req getStepOver();
    
    StepOver_ReqOrBuilder getStepOverOrBuilder();
    
    boolean hasStepOut();
    
    StepOut_Req getStepOut();
    
    StepOut_ReqOrBuilder getStepOutOrBuilder();
    
    boolean hasEvaluateExpression();
    
    EvaluateExpression_Req getEvaluateExpression();
    
    EvaluateExpression_ReqOrBuilder getEvaluateExpressionOrBuilder();
    
    boolean hasGetValueChildren();
    
    GetValueChildren_Req getGetValueChildren();
    
    GetValueChildren_ReqOrBuilder getGetValueChildrenOrBuilder();
    
    boolean hasGetVars();
    
    GetVars_Req getGetVars();
    
    GetVars_ReqOrBuilder getGetVarsOrBuilder();
    
    boolean hasHandleConsoleCommand();
    
    HandleConsoleCommand_Req getHandleConsoleCommand();
    
    HandleConsoleCommand_ReqOrBuilder getHandleConsoleCommandOrBuilder();
    
    boolean hasHandleCompletion();
    
    HandleCompletion_Req getHandleCompletion();
    
    HandleCompletion_ReqOrBuilder getHandleCompletionOrBuilder();
    
    boolean hasAttach();
    
    Attach_Req getAttach();
    
    Attach_ReqOrBuilder getAttachOrBuilder();
    
    boolean hasAttachByName();
    
    AttachByName_Req getAttachByName();
    
    AttachByName_ReqOrBuilder getAttachByNameOrBuilder();
    
    boolean hasDispatchInput();
    
    DispatchInput_Req getDispatchInput();
    
    DispatchInput_ReqOrBuilder getDispatchInputOrBuilder();
    
    boolean hasAddWatchpoint();
    
    AddWatchpoint_Req getAddWatchpoint();
    
    AddWatchpoint_ReqOrBuilder getAddWatchpointOrBuilder();
    
    boolean hasRemoveWatchpoint();
    
    RemoveWatchpoint_Req getRemoveWatchpoint();
    
    RemoveWatchpoint_ReqOrBuilder getRemoveWatchpointOrBuilder();
    
    boolean hasDetach();
    
    Detach_Req getDetach();
    
    Detach_ReqOrBuilder getDetachOrBuilder();
    
    boolean hasKill();
    
    Kill_Req getKill();
    
    Kill_ReqOrBuilder getKillOrBuilder();
    
    boolean hasGetChildrenCount();
    
    GetChildrenCount_Req getGetChildrenCount();
    
    GetChildrenCount_ReqOrBuilder getGetChildrenCountOrBuilder();
    
    boolean hasGetArraySlice();
    
    GetArraySlice_Req getGetArraySlice();
    
    GetArraySlice_ReqOrBuilder getGetArraySliceOrBuilder();
    
    boolean hasGetValueData();
    
    GetValueData_Req getGetValueData();
    
    GetValueData_ReqOrBuilder getGetValueDataOrBuilder();
    
    boolean hasGetValueDescription();
    
    GetValueDescription_Req getGetValueDescription();
    
    GetValueDescription_ReqOrBuilder getGetValueDescriptionOrBuilder();
    
    boolean hasValuesFilteringPolicy();
    
    ValuesFilteringPolicy_Req getValuesFilteringPolicy();
    
    ValuesFilteringPolicy_ReqOrBuilder getValuesFilteringPolicyOrBuilder();
    
    boolean hasConnectPlatform();
    
    ConnectPlatform_Req getConnectPlatform();
    
    ConnectPlatform_ReqOrBuilder getConnectPlatformOrBuilder();
    
    boolean hasGetValueAddress();
    
    GetValueAddress_Req getGetValueAddress();
    
    GetValueAddress_ReqOrBuilder getGetValueAddressOrBuilder();
    
    boolean hasHandleSignal();
    
    HandleSignal_Req getHandleSignal();
    
    HandleSignal_ReqOrBuilder getHandleSignalOrBuilder();
    
    boolean hasExecuteShellCommand();
    
    ExecuteShellCommand_Req getExecuteShellCommand();
    
    ExecuteShellCommand_ReqOrBuilder getExecuteShellCommandOrBuilder();
}
