// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb;

import java.util.Iterator;
import java.util.Map;
import com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated.Model;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.google.protobuf.GeneratedMessage;
import com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated.Protocol;

public class ProtobufMessageFactory
{
    public static Protocol.CompositeRequest getChildrenCount(final int id) {
        return Protocol.CompositeRequest.newBuilder().setGetChildrenCount(Protocol.GetChildrenCount_Req.newBuilder().setId(id)).build();
    }
    
    public static GeneratedMessage kill() {
        return Protocol.CompositeRequest.newBuilder().setKill(Protocol.Kill_Req.newBuilder()).build();
    }
    
    public static Protocol.CompositeRequest detach() {
        return Protocol.CompositeRequest.newBuilder().setDetach(Protocol.Detach_Req.newBuilder()).build();
    }
    
    public static Protocol.CompositeRequest attach(final int pid) {
        return Protocol.CompositeRequest.newBuilder().setAttach(Protocol.Attach_Req.newBuilder().setPid(pid)).build();
    }
    
    public static GeneratedMessage attachByName(final String name, final boolean toWait) {
        return Protocol.CompositeRequest.newBuilder().setAttachByName(Protocol.AttachByName_Req.newBuilder().setName(name).setToWait(toWait)).build();
    }
    
    public static Protocol.CompositeRequest remoteLaunch(final String s, final GeneralCommandLine generalCommandLine, final String fdPassingServiceUnixSocket) {
        final Protocol.Launch_Req.Builder a = a(s, generalCommandLine, null, null, null);
        a.setFdPassingServiceUnixSocket(fdPassingServiceUnixSocket);
        final Protocol.CompositeRequest.Builder builder = Protocol.CompositeRequest.newBuilder();
        builder.setLaunch(a);
        return builder.build();
    }
    
    @NotNull
    public static Protocol.CompositeRequest createRemoteTarget(@NotNull final String exePath, @Nullable final String s, @NotNull final String platform, @Nullable final String s2, @NotNull final String remoteExePath) {
        try {
            if (exePath == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "exePath", "com/jetbrains/cidr/execution/debugger/backend/lldb/ProtobufMessageFactory", "createRemoteTarget"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (platform == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "platform", "com/jetbrains/cidr/execution/debugger/backend/lldb/ProtobufMessageFactory", "createRemoteTarget"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (remoteExePath == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "remoteExePath", "com/jetbrains/cidr/execution/debugger/backend/lldb/ProtobufMessageFactory", "createRemoteTarget"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final Protocol.CreateTarget_Req.Builder setRemoteExePath = Protocol.CreateTarget_Req.newBuilder().setExePath(exePath).setPlatform(platform).setRemoteExePath(remoteExePath);
        try {
            if (s != null) {
                setRemoteExePath.setArch(s);
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        try {
            if (s2 != null) {
                setRemoteExePath.setPlatformSdkRoot(s);
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        Protocol.CompositeRequest build;
        try {
            build = Protocol.CompositeRequest.newBuilder().setCreateTarget(setRemoteExePath.build()).build();
            if (build == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/lldb/ProtobufMessageFactory", "createRemoteTarget"));
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        return build;
    }
    
    @NotNull
    public static Protocol.CompositeRequest createRemoteTarget(@NotNull final String s, @NotNull final String s2, @NotNull final String s3) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "exePath", "com/jetbrains/cidr/execution/debugger/backend/lldb/ProtobufMessageFactory", "createRemoteTarget"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "platform", "com/jetbrains/cidr/execution/debugger/backend/lldb/ProtobufMessageFactory", "createRemoteTarget"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (s3 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "remoteExePath", "com/jetbrains/cidr/execution/debugger/backend/lldb/ProtobufMessageFactory", "createRemoteTarget"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        Protocol.CompositeRequest remoteTarget;
        try {
            remoteTarget = createRemoteTarget(s, null, s2, null, s3);
            if (remoteTarget == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/lldb/ProtobufMessageFactory", "createRemoteTarget"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return remoteTarget;
    }
    
    public static Protocol.CompositeRequest suspend() {
        return Protocol.CompositeRequest.newBuilder().setSuspend(Protocol.Suspend_Req.newBuilder()).build();
    }
    
    public static Protocol.CompositeRequest removeBreakpoint(final int id) {
        return Protocol.CompositeRequest.newBuilder().setRemoveBreakpoint(Protocol.RemoveBreakpoint_Req.newBuilder().setId(id)).build();
    }
    
    public static Protocol.CompositeRequest removeWatchpoint(final int id) {
        return Protocol.CompositeRequest.newBuilder().setRemoveWatchpoint(Protocol.RemoveWatchpoint_Req.newBuilder().setId(id)).build();
    }
    
    public static Protocol.CompositeRequest addBreakpoint(final String file, final int line, @Nullable final String condition) {
        final Protocol.AddBreakpoint_Req.Builder setLine = Protocol.AddBreakpoint_Req.newBuilder().setFile(file).setLine(line);
        try {
            if (condition != null) {
                setLine.setCondition(condition);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return Protocol.CompositeRequest.newBuilder().setAddBreakpoint(setLine).build();
    }
    
    @NotNull
    public static Protocol.CompositeRequest addBreakpoint(@NotNull final String symbolPattern, final boolean regexp, @Nullable final String moduleName, @Nullable final String condition, final long n) {
        try {
            if (symbolPattern == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "pattern", "com/jetbrains/cidr/execution/debugger/backend/lldb/ProtobufMessageFactory", "addBreakpoint"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final Protocol.AddBreakpoint_Req.Builder setSymbolPattern = Protocol.AddBreakpoint_Req.newBuilder().setSymbolPattern(symbolPattern);
        try {
            setSymbolPattern.setRegexp(regexp);
            setSymbolPattern.setThreadId((int)n);
            if (moduleName != null) {
                setSymbolPattern.setModuleName(moduleName);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (condition != null) {
                setSymbolPattern.setCondition(condition);
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        Protocol.CompositeRequest build;
        try {
            build = Protocol.CompositeRequest.newBuilder().setAddBreakpoint(setSymbolPattern).build();
            if (build == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/lldb/ProtobufMessageFactory", "addBreakpoint"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return build;
    }
    
    public static Protocol.CompositeRequest getThreads() {
        return Protocol.CompositeRequest.newBuilder().setGetThreads(Protocol.GetThreads_Req.newBuilder()).build();
    }
    
    public static Protocol.CompositeRequest getFrames(final long n, final int fromIndex, final int count, final boolean untilValidLineEntry) {
        return Protocol.CompositeRequest.newBuilder().setGetFrames(Protocol.GetFrames_Req.newBuilder().setThreadId((int)n).setFromIndex(fromIndex).setCount(count).setUntilValidLineEntry(untilValidLineEntry)).build();
    }
    
    public static Protocol.CompositeRequest exit() {
        return Protocol.CompositeRequest.newBuilder().setExit(Protocol.Exit_Req.newBuilder()).build();
    }
    
    public static Protocol.CompositeRequest launch(final GeneralCommandLine generalCommandLine, @Nullable final String s, @Nullable final String s2, @Nullable final String s3) {
        final Protocol.CompositeRequest.Builder builder = Protocol.CompositeRequest.newBuilder();
        builder.setLaunch(a(generalCommandLine.getExePath(), generalCommandLine, s, s2, s3));
        return builder.build();
    }
    
    private static Protocol.Launch_Req.Builder a(final String exePath, final GeneralCommandLine generalCommandLine, @Nullable final String stdinPath, @Nullable final String stdoutPath, @Nullable final String stderrPath) {
        final Protocol.Launch_Req.Builder builder = Protocol.Launch_Req.newBuilder();
        final Model.CommandLine.Builder builder2 = Model.CommandLine.newBuilder();
        builder2.setExePath(exePath);
        builder2.setWorkingDir(generalCommandLine.getWorkDirectory().getAbsolutePath());
        final Map effectiveEnvironment = generalCommandLine.getEffectiveEnvironment();
        for (final String name : effectiveEnvironment.keySet()) {
            builder2.addEnv(Model.EnvParam.newBuilder().setName(name).setValue((String)effectiveEnvironment.get(name)));
        }
        final String[] array = generalCommandLine.getParametersList().getArray();
        for (int length = array.length, i = 0; i < length; ++i) {
            builder2.addParam(array[i]);
        }
        try {
            if (stdinPath != null) {
                builder2.setStdinPath(stdinPath);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (stdoutPath != null) {
                builder2.setStdoutPath(stdoutPath);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (stderrPath != null) {
                builder2.setStderrPath(stderrPath);
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        builder.setCommandLine(builder2);
        return builder;
    }
    
    public static Protocol.CompositeRequest createTarget(final String exePath, final String arch) {
        return Protocol.CompositeRequest.newBuilder().setCreateTarget(Protocol.CreateTarget_Req.newBuilder().setArch(arch).setExePath(exePath).build()).build();
    }
    
    public static Protocol.CompositeRequest connectPlatform(final String platform, final String url) {
        return Protocol.CompositeRequest.newBuilder().setConnectPlatform(Protocol.ConnectPlatform_Req.newBuilder().setPlatform(platform).setUrl(url).build()).build();
    }
    
    public static Protocol.CompositeRequest executeShellCommand(final String command, final String workingDir, final int timeoutSecs) {
        final Protocol.ExecuteShellCommand_Req.Builder setTimeoutSecs = Protocol.ExecuteShellCommand_Req.newBuilder().setCommand(command).setTimeoutSecs(timeoutSecs);
        try {
            if (workingDir != null) {
                setTimeoutSecs.setWorkingDir(workingDir);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return Protocol.CompositeRequest.newBuilder().setExecuteShellCommand(setTimeoutSecs.build()).build();
    }
    
    public static Protocol.CompositeRequest resume() {
        return Protocol.CompositeRequest.newBuilder().setContinue(Protocol.Continue_Req.newBuilder()).build();
    }
    
    public static Protocol.CompositeRequest stepInto(final long n) {
        return Protocol.CompositeRequest.newBuilder().setStepInto(Protocol.StepInto_Req.newBuilder().setThreadId((int)n)).build();
    }
    
    public static Protocol.CompositeRequest stepOut(final long n) {
        return Protocol.CompositeRequest.newBuilder().setStepOut(Protocol.StepOut_Req.newBuilder().setThreadId((int)n)).build();
    }
    
    public static Protocol.CompositeRequest stepOver(final long n) {
        return Protocol.CompositeRequest.newBuilder().setStepOver(Protocol.StepOver_Req.newBuilder().setThreadId((int)n)).build();
    }
    
    public static Protocol.CompositeRequest evaluateExpression(final long n, final int frameIndex, final String expression, @Nullable final Model.Language language) {
        final Protocol.EvaluateExpression_Req.Builder setFrameIndex = Protocol.EvaluateExpression_Req.newBuilder().setExpression(expression).setThreadId((int)n).setFrameIndex(frameIndex);
        try {
            if (language != null) {
                setFrameIndex.setLanguage(language);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return Protocol.CompositeRequest.newBuilder().setEvaluateExpression(setFrameIndex).build();
    }
    
    public static Protocol.CompositeRequest handleConsoleCommand(final long n, final int frameIndex, final String command) {
        return Protocol.CompositeRequest.newBuilder().setHandleConsoleCommand(Protocol.HandleConsoleCommand_Req.newBuilder().setCommand(command).setThreadId((int)n).setFrameIndex(frameIndex)).build();
    }
    
    public static Protocol.CompositeRequest dispatchInput(final String input, final Model.DispatchTarget target) {
        return Protocol.CompositeRequest.newBuilder().setDispatchInput(Protocol.DispatchInput_Req.newBuilder().setInput(input).setTarget(target)).build();
    }
    
    public static Protocol.CompositeRequest handleCompletion(final String command, final int pos) {
        return Protocol.CompositeRequest.newBuilder().setHandleCompletion(Protocol.HandleCompletion_Req.newBuilder().setCommand(command).setPos(pos)).build();
    }
    
    public static Protocol.CompositeRequest handleSignal(final String signal, final boolean stop, final boolean pass, final boolean notify) {
        return Protocol.CompositeRequest.newBuilder().setHandleSignal(Protocol.HandleSignal_Req.newBuilder().setSignal(signal).setStop(stop).setPass(pass).setNotify(notify)).build();
    }
    
    public static Protocol.CompositeRequest getValueChildren(final int id, final int offset, final int count) {
        return Protocol.CompositeRequest.newBuilder().setGetValueChildren(Protocol.GetValueChildren_Req.newBuilder().setId(id).setOffset(offset).setCount(count)).build();
    }
    
    public static Protocol.CompositeRequest getValueData(final int id, final int maxDescriptionLength) {
        return Protocol.CompositeRequest.newBuilder().setGetValueData(Protocol.GetValueData_Req.newBuilder().setId(id).setMaxDescriptionLength(maxDescriptionLength)).build();
    }
    
    public static Protocol.CompositeRequest getValueDescription(final int id, final int maxLength) {
        return Protocol.CompositeRequest.newBuilder().setGetValueDescription(Protocol.GetValueDescription_Req.newBuilder().setId(id).setMaxLength(maxLength)).build();
    }
    
    public static Protocol.CompositeRequest arraySlice(final int valueId, final int offset, final int count) {
        return Protocol.CompositeRequest.newBuilder().setGetArraySlice(Protocol.GetArraySlice_Req.newBuilder().setValueId(valueId).setOffset(offset).setCount(count)).build();
    }
    
    public static Protocol.CompositeRequest addWatchpoint(final int valueId, @Nullable final String condition, final boolean b, final boolean b2, final boolean b3) {
        Protocol.AddWatchpoint_Req.Builder setWrite = null;
        int toResolveLocation = 0;
        Label_0049: {
            Label_0032: {
                Protocol.AddWatchpoint_Req.Builder setRead = null;
                Label_0016: {
                    Protocol.AddWatchpoint_Req.Builder builder;
                    try {
                        builder = Protocol.AddWatchpoint_Req.newBuilder();
                        if (b) {
                            final int read = 1;
                            break Label_0016;
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    final int read = 0;
                    try {
                        setRead = builder.setRead(read);
                        if (b2) {
                            final int write = 1;
                            break Label_0032;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
                final int write = 0;
                try {
                    setWrite = setRead.setWrite(write);
                    if (b3) {
                        toResolveLocation = 1;
                        break Label_0049;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            toResolveLocation = 0;
        }
        final Protocol.AddWatchpoint_Req.Builder setValueId = setWrite.setToResolveLocation(toResolveLocation).setValueId(valueId);
        try {
            if (condition != null) {
                setValueId.setCondition(condition);
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return Protocol.CompositeRequest.newBuilder().setAddWatchpoint(setValueId).build();
    }
    
    public static Protocol.CompositeRequest getVars(final long n, final int frameIndex, final boolean statics, final boolean globals) {
        return Protocol.CompositeRequest.newBuilder().setGetVars(Protocol.GetVars_Req.newBuilder().setThreadId((int)n).setFrameIndex(frameIndex).setStatics(statics).setGlobals(globals)).build();
    }
    
    public static Protocol.CompositeRequest setValuesFilteringEnabled(final boolean b) {
        Protocol.CompositeRequest.Builder builder;
        Protocol.ValuesFilteringPolicy_Req.Builder builder2;
        try {
            builder = Protocol.CompositeRequest.newBuilder();
            builder2 = Protocol.ValuesFilteringPolicy_Req.newBuilder();
            if (b) {
                final int n = 1;
                return builder.setValuesFilteringPolicy(builder2.setFilterEnabled(n)).build();
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final int n = 0;
        return builder.setValuesFilteringPolicy(builder2.setFilterEnabled(n)).build();
    }
    
    public static Protocol.CompositeRequest getValueAddress(final int id) {
        return Protocol.CompositeRequest.newBuilder().setGetValueAddress(Protocol.GetValueAddress_Req.newBuilder().setId(id)).build();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
