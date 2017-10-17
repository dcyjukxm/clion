// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.MessageOrBuilder;

public interface CompositeBroadcastOrBuilder extends MessageOrBuilder
{
    boolean hasProcessInterrupted();
    
    ProcessInterrupted_Broadcast getProcessInterrupted();
    
    ProcessInterrupted_BroadcastOrBuilder getProcessInterruptedOrBuilder();
    
    boolean hasProcessRunning();
    
    ProcessRunning_Broadcast getProcessRunning();
    
    ProcessRunning_BroadcastOrBuilder getProcessRunningOrBuilder();
    
    boolean hasProcessExited();
    
    ProcessExited_Broadcast getProcessExited();
    
    ProcessExited_BroadcastOrBuilder getProcessExitedOrBuilder();
    
    boolean hasLogMessage();
    
    LogMessage_Broadcast getLogMessage();
    
    LogMessage_BroadcastOrBuilder getLogMessageOrBuilder();
    
    boolean hasChangePrompt();
    
    ChangePrompt_Broadcast getChangePrompt();
    
    ChangePrompt_BroadcastOrBuilder getChangePromptOrBuilder();
    
    boolean hasReadyForCommand();
    
    ReadyForCommands_Broadcast getReadyForCommand();
    
    ReadyForCommands_BroadcastOrBuilder getReadyForCommandOrBuilder();
    
    boolean hasInterpreterMessage();
    
    CommandsInterpreter_Broadcast getInterpreterMessage();
    
    CommandsInterpreter_BroadcastOrBuilder getInterpreterMessageOrBuilder();
    
    boolean hasTargetProcessOutput();
    
    TargetProcessOutput_Broadcast getTargetProcessOutput();
    
    TargetProcessOutput_BroadcastOrBuilder getTargetProcessOutputOrBuilder();
    
    boolean hasModulesLoaded();
    
    ModulesLoaded_Broadcast getModulesLoaded();
    
    ModulesLoaded_BroadcastOrBuilder getModulesLoadedOrBuilder();
}
