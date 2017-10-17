// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.MessageOrBuilder;

public interface CompositeResponseOrBuilder extends MessageOrBuilder
{
    boolean hasCreateTarget();
    
    CreateTarget_Res getCreateTarget();
    
    CreateTarget_ResOrBuilder getCreateTargetOrBuilder();
    
    boolean hasLaunch();
    
    Launch_Res getLaunch();
    
    Launch_ResOrBuilder getLaunchOrBuilder();
    
    boolean hasContinue();
    
    Continue_Res getContinue();
    
    Continue_ResOrBuilder getContinueOrBuilder();
    
    boolean hasCompositeBroadcast();
    
    Broadcasts.CompositeBroadcast getCompositeBroadcast();
    
    Broadcasts.CompositeBroadcastOrBuilder getCompositeBroadcastOrBuilder();
    
    boolean hasSuspend();
    
    Suspend_Res getSuspend();
    
    Suspend_ResOrBuilder getSuspendOrBuilder();
    
    boolean hasGetThreads();
    
    GetThreads_Res getGetThreads();
    
    GetThreads_ResOrBuilder getGetThreadsOrBuilder();
    
    boolean hasGetFrames();
    
    GetFrames_Res getGetFrames();
    
    GetFrames_ResOrBuilder getGetFramesOrBuilder();
    
    boolean hasAddBreakpoint();
    
    AddBreakpoint_Res getAddBreakpoint();
    
    AddBreakpoint_ResOrBuilder getAddBreakpointOrBuilder();
    
    boolean hasRemoveBreakpoint();
    
    RemoveBreakpoint_Res getRemoveBreakpoint();
    
    RemoveBreakpoint_ResOrBuilder getRemoveBreakpointOrBuilder();
    
    boolean hasStepInto();
    
    StepInto_Res getStepInto();
    
    StepInto_ResOrBuilder getStepIntoOrBuilder();
    
    boolean hasStepOver();
    
    StepOver_Res getStepOver();
    
    StepOver_ResOrBuilder getStepOverOrBuilder();
    
    boolean hasStepOut();
    
    StepOut_Res getStepOut();
    
    StepOut_ResOrBuilder getStepOutOrBuilder();
    
    boolean hasEvaluateExpression();
    
    EvaluateExpression_Res getEvaluateExpression();
    
    EvaluateExpression_ResOrBuilder getEvaluateExpressionOrBuilder();
    
    boolean hasGetValueChildren();
    
    GetValueChildren_Res getGetValueChildren();
    
    GetValueChildren_ResOrBuilder getGetValueChildrenOrBuilder();
    
    boolean hasGetVars();
    
    GetVars_Res getGetVars();
    
    GetVars_ResOrBuilder getGetVarsOrBuilder();
    
    boolean hasHandleConsoleCommand();
    
    HandleConsoleCommand_Res getHandleConsoleCommand();
    
    HandleConsoleCommand_ResOrBuilder getHandleConsoleCommandOrBuilder();
    
    boolean hasHandleCompletion();
    
    HandleCompletion_Res getHandleCompletion();
    
    HandleCompletion_ResOrBuilder getHandleCompletionOrBuilder();
    
    boolean hasAttach();
    
    Attach_Res getAttach();
    
    Attach_ResOrBuilder getAttachOrBuilder();
    
    boolean hasAttachByName();
    
    AttachByName_Res getAttachByName();
    
    AttachByName_ResOrBuilder getAttachByNameOrBuilder();
    
    boolean hasDispatchInput();
    
    DispatchInput_Res getDispatchInput();
    
    DispatchInput_ResOrBuilder getDispatchInputOrBuilder();
    
    boolean hasAddWatchpoint();
    
    AddWatchpoint_Res getAddWatchpoint();
    
    AddWatchpoint_ResOrBuilder getAddWatchpointOrBuilder();
    
    boolean hasRemoveWatchpoint();
    
    RemoveWatchpoint_Res getRemoveWatchpoint();
    
    RemoveWatchpoint_ResOrBuilder getRemoveWatchpointOrBuilder();
    
    boolean hasDetach();
    
    Detach_Res getDetach();
    
    Detach_ResOrBuilder getDetachOrBuilder();
    
    boolean hasKill();
    
    Kill_Res getKill();
    
    Kill_ResOrBuilder getKillOrBuilder();
    
    boolean hasGetChildrenCount();
    
    GetChildrenCount_Res getGetChildrenCount();
    
    GetChildrenCount_ResOrBuilder getGetChildrenCountOrBuilder();
    
    boolean hasGetArraySlice();
    
    GetArraySlice_Res getGetArraySlice();
    
    GetArraySlice_ResOrBuilder getGetArraySliceOrBuilder();
    
    boolean hasGetValueData();
    
    GetValueData_Res getGetValueData();
    
    GetValueData_ResOrBuilder getGetValueDataOrBuilder();
    
    boolean hasGetValueDescription();
    
    GetValueDescription_Res getGetValueDescription();
    
    GetValueDescription_ResOrBuilder getGetValueDescriptionOrBuilder();
    
    boolean hasValuesFilteringPolicy();
    
    ValuesFilteringPolicy_Res getValuesFilteringPolicy();
    
    ValuesFilteringPolicy_ResOrBuilder getValuesFilteringPolicyOrBuilder();
    
    boolean hasConnectPlatform();
    
    ConnectPlatform_Res getConnectPlatform();
    
    ConnectPlatform_ResOrBuilder getConnectPlatformOrBuilder();
    
    boolean hasGetValueAddress();
    
    GetValueAddress_Res getGetValueAddress();
    
    GetValueAddress_ResOrBuilder getGetValueAddressOrBuilder();
    
    boolean hasHandleSignal();
    
    HandleSignal_Res getHandleSignal();
    
    HandleSignal_ResOrBuilder getHandleSignalOrBuilder();
    
    boolean hasExecuteShellCommand();
    
    ExecuteShellCommand_Res getExecuteShellCommand();
    
    ExecuteShellCommand_ResOrBuilder getExecuteShellCommandOrBuilder();
}
