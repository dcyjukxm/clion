// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb;

import com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated.Model;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import com.intellij.util.Consumer;
import com.google.protobuf.GeneratedMessage;
import com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated.Protocol;
import com.intellij.openapi.util.Ref;
import com.intellij.execution.ExecutionException;
import com.jetbrains.cidr.execution.debugger.backend.LLValueData;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.debugger.backend.LLValue;

private class LLValueDataLoader
{
    @NotNull
    public LLValueData loadData(@NotNull final LLValue llValue) throws ExecutionException, DebuggerCommandException {
        try {
            if (llValue == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$LLValueDataLoader", "loadData"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        final Protocol.CompositeRequest valueData = ProtobufMessageFactory.getValueData(LLDBDriver.access$700(llValue), 1000);
        final Ref create = Ref.create();
        final Ref create2 = Ref.create();
        try {
            LLDBDriver.this.getProtobufClient().sendMessageAndWaitForReply(valueData, Protocol.GetValueData_Res.class, (com.intellij.util.Consumer<Protocol.GetValueData_Res>)(getValueData_Res -> {
                final Protocol.CommonResponse commonResponse = getValueData_Res.getCommonResponse();
                try {
                    if (!commonResponse.getIsValid()) {
                        create2.set((Object)new DebuggerCommandException(commonResponse.getErrorMessage()));
                        return;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                create.set((Object)getValueData_Res.getValue());
            }));
            if (!create2.isNull()) {
                throw (DebuggerCommandException)create2.get();
            }
        }
        catch (ExecutionException ex2) {
            throw b((Exception)ex2);
        }
        final Model.LLDBValueData lldbValueData = (Model.LLDBValueData)create.get();
        String value = null;
        String description = null;
        Label_0145: {
            try {
                value = lldbValueData.getValue();
                if (lldbValueData.hasDescription()) {
                    description = lldbValueData.getDescription();
                    break Label_0145;
                }
            }
            catch (ExecutionException ex3) {
                throw b((Exception)ex3);
            }
            description = null;
        }
        final LLValueData llValueData = new LLValueData(value, description, lldbValueData.getHasLongerDescription(), lldbValueData.getMayHaveChildren(), lldbValueData.getIsSynthetic());
        if (llValueData == null) {
            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/lldb/LLDBDriver$LLValueDataLoader", "loadData"));
        }
        return llValueData;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
