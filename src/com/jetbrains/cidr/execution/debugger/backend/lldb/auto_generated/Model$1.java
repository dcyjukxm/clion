// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.Descriptors;

static final class Model$1 implements Descriptors.FileDescriptor.InternalDescriptorAssigner {
    public ExtensionRegistry assignDescriptors(final Descriptors.FileDescriptor fileDescriptor) {
        Model.access$9802(fileDescriptor);
        Model.access$002(Model.getDescriptor().getMessageTypes().get(0));
        Model.access$102(new GeneratedMessage.FieldAccessorTable(Model.access$000(), new String[0]));
        Model.access$702(Model.getDescriptor().getMessageTypes().get(1));
        Model.access$802(new GeneratedMessage.FieldAccessorTable(Model.access$700(), new String[] { "Name", "Value" }));
        Model.access$1702(Model.getDescriptor().getMessageTypes().get(2));
        Model.access$1802(new GeneratedMessage.FieldAccessorTable(Model.access$1700(), new String[] { "Env", "ExePath", "WorkingDir", "Param", "StdinPath", "StdoutPath", "StderrPath" }));
        Model.access$3302(Model.getDescriptor().getMessageTypes().get(3));
        Model.access$3402(new GeneratedMessage.FieldAccessorTable(Model.access$3300(), new String[] { "StopReason", "StopDescription", "Signal", "SignalName", "CodepointId" }));
        Model.access$4602(Model.getDescriptor().getMessageTypes().get(4));
        Model.access$4702(new GeneratedMessage.FieldAccessorTable(Model.access$4600(), new String[] { "Id", "Name", "Queue", "StopReasonInfo" }));
        Model.access$5802(Model.getDescriptor().getMessageTypes().get(5));
        Model.access$5902(new GeneratedMessage.FieldAccessorTable(Model.access$5800(), new String[] { "Number", "Function", "File", "Line", "Pc", "Language", "Optimized" }));
        Model.access$7302(Model.getDescriptor().getMessageTypes().get(6));
        Model.access$7402(new GeneratedMessage.FieldAccessorTable(Model.access$7300(), new String[] { "Id", "Name", "Type", "TypeClass" }));
        Model.access$8502(Model.getDescriptor().getMessageTypes().get(7));
        Model.access$8602(new GeneratedMessage.FieldAccessorTable(Model.access$8500(), new String[] { "Value", "Description", "HasLongerDescription", "MayHaveChildren", "IsSynthetic" }));
        return null;
    }
}