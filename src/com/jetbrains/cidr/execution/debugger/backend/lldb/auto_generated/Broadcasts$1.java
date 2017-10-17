// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.Descriptors;

static final class Broadcasts$1 implements Descriptors.FileDescriptor.InternalDescriptorAssigner {
    public ExtensionRegistry assignDescriptors(final Descriptors.FileDescriptor fileDescriptor) {
        Broadcasts.access$9902(fileDescriptor);
        Broadcasts.access$002(Broadcasts.getDescriptor().getMessageTypes().get(0));
        Broadcasts.access$102(new GeneratedMessage.FieldAccessorTable(Broadcasts.access$000(), new String[] { "Thread", "Frame" }));
        Broadcasts.access$1002(Broadcasts.getDescriptor().getMessageTypes().get(1));
        Broadcasts.access$1102(new GeneratedMessage.FieldAccessorTable(Broadcasts.access$1000(), new String[0]));
        Broadcasts.access$1702(Broadcasts.getDescriptor().getMessageTypes().get(2));
        Broadcasts.access$1802(new GeneratedMessage.FieldAccessorTable(Broadcasts.access$1700(), new String[] { "ExitCode", "ExitDescription" }));
        Broadcasts.access$2702(Broadcasts.getDescriptor().getMessageTypes().get(3));
        Broadcasts.access$2802(new GeneratedMessage.FieldAccessorTable(Broadcasts.access$2700(), new String[] { "Text", "OutputType" }));
        Broadcasts.access$3702(Broadcasts.getDescriptor().getMessageTypes().get(4));
        Broadcasts.access$3802(new GeneratedMessage.FieldAccessorTable(Broadcasts.access$3700(), new String[] { "Verbosity", "Message" }));
        Broadcasts.access$4702(Broadcasts.getDescriptor().getMessageTypes().get(5));
        Broadcasts.access$4802(new GeneratedMessage.FieldAccessorTable(Broadcasts.access$4700(), new String[] { "Modules" }));
        Broadcasts.access$5502(Broadcasts.getDescriptor().getMessageTypes().get(6));
        Broadcasts.access$5602(new GeneratedMessage.FieldAccessorTable(Broadcasts.access$5500(), new String[] { "Prompt" }));
        Broadcasts.access$6402(Broadcasts.getDescriptor().getMessageTypes().get(7));
        Broadcasts.access$6502(new GeneratedMessage.FieldAccessorTable(Broadcasts.access$6400(), new String[] { "Ready" }));
        Broadcasts.access$7302(Broadcasts.getDescriptor().getMessageTypes().get(8));
        Broadcasts.access$7402(new GeneratedMessage.FieldAccessorTable(Broadcasts.access$7300(), new String[] { "Message" }));
        Broadcasts.access$8202(Broadcasts.getDescriptor().getMessageTypes().get(9));
        Broadcasts.access$8302(new GeneratedMessage.FieldAccessorTable(Broadcasts.access$8200(), new String[] { "ProcessInterrupted", "ProcessRunning", "ProcessExited", "LogMessage", "ChangePrompt", "ReadyForCommand", "InterpreterMessage", "TargetProcessOutput", "ModulesLoaded" }));
        return null;
    }
}