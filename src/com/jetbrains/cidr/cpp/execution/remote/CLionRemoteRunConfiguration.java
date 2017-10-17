// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution.remote;

import com.intellij.execution.configurations.ConfigurationFactory;
import kotlin.jvm.internal.DefaultConstructorMarker;
import com.jetbrains.cidr.execution.debugger.remote.CidrRemotePathMapping;
import kotlin.TypeCastException;
import com.intellij.openapi.util.io.FileUtil;
import org.jdom.Element;
import com.intellij.execution.configurations.RunProfileState;
import com.jetbrains.cidr.execution.testing.CidrLauncher;
import com.jetbrains.cidr.execution.CidrCommandLineState;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.Executor;
import java.util.Iterator;
import kotlin.jvm.functions.Function1;
import kotlin.collections.CollectionsKt;
import java.util.ArrayList;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriver;
import java.util.List;
import com.intellij.execution.configurations.RuntimeConfigurationWarning;
import com.jetbrains.cidr.execution.CidrDebuggerBundle;
import java.io.File;
import com.intellij.execution.configurations.RuntimeConfigurationException;
import kotlin.text.StringsKt;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.debugger.remote.CidrRemoteDebugParameters;
import org.jetbrains.annotations.Nullable;
import kotlin.Metadata;
import com.intellij.execution.runners.RunConfigurationWithSuppressedDefaultRunAction;
import com.jetbrains.cidr.execution.CidrRunProfile;
import com.intellij.execution.configurations.RunConfigurationBase;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u001d\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u0016\u001a\u00020\u0017H\u0017J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\u001a\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0012\u0010 \u001a\u00020\u00172\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\u0012\u0010#\u001a\u00020\u00172\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006$" }, d2 = { "Lcom/jetbrains/cidr/cpp/execution/remote/CLionRemoteRunConfiguration;", "Lcom/jetbrains/cidr/execution/CidrRunProfile;", "Lcom/intellij/execution/runners/RunConfigurationWithSuppressedDefaultRunAction;", "Lcom/intellij/execution/configurations/RunConfigurationBase;", "project", "Lcom/intellij/openapi/project/Project;", "factory", "Lcom/intellij/execution/configurations/ConfigurationFactory;", "name", "", "(Lcom/intellij/openapi/project/Project;Lcom/intellij/execution/configurations/ConfigurationFactory;Ljava/lang/String;)V", "debugger", "getDebugger", "()Ljava/lang/String;", "setDebugger", "(Ljava/lang/String;)V", "params", "Lcom/jetbrains/cidr/execution/debugger/remote/CidrRemoteDebugParameters;", "getParams", "()Lcom/jetbrains/cidr/execution/debugger/remote/CidrRemoteDebugParameters;", "setParams", "(Lcom/jetbrains/cidr/execution/debugger/remote/CidrRemoteDebugParameters;)V", "checkConfiguration", "", "getConfigurationEditor", "Lcom/jetbrains/cidr/cpp/execution/remote/CLionRemoteRunConfigurationEditor;", "getState", "Lcom/jetbrains/cidr/execution/CidrCommandLineState;", "executor", "Lcom/intellij/execution/Executor;", "environment", "Lcom/intellij/execution/runners/ExecutionEnvironment;", "readExternal", "element", "Lorg/jdom/Element;", "writeExternal", "clion" })
public final class CLionRemoteRunConfiguration extends RunConfigurationBase implements CidrRunProfile, RunConfigurationWithSuppressedDefaultRunAction
{
    @Nullable
    private String debugger;
    @NotNull
    private CidrRemoteDebugParameters params;
    
    @Nullable
    public final String getDebugger() {
        return this.debugger;
    }
    
    public final void setDebugger(@Nullable final String debugger) {
        this.debugger = debugger;
    }
    
    @NotNull
    public final CidrRemoteDebugParameters getParams() {
        return this.params;
    }
    
    public final void setParams(@NotNull final CidrRemoteDebugParameters params) {
        Intrinsics.checkParameterIsNotNull((Object)params, "<set-?>");
        this.params = params;
    }
    
    @NotNull
    public CLionRemoteRunConfigurationEditor getConfigurationEditor() {
        final Project project = this.getProject();
        Intrinsics.checkExpressionValueIsNotNull((Object)project, "project");
        return new CLionRemoteRunConfigurationEditor(project);
    }
    
    public void checkConfiguration() throws RuntimeConfigurationException {
        final String s = this.params.getSymbolFile();
        Label_0057: {
            Label_0027: {
                try {
                    if (!StringsKt.isBlank((CharSequence)s)) {
                        final boolean b = true;
                        break Label_0027;
                    }
                }
                catch (RuntimeConfigurationException ex) {
                    throw b((Exception)ex);
                }
                final boolean b = false;
                try {
                    if (!b) {
                        break Label_0057;
                    }
                    final CLionRemoteRunConfiguration cLionRemoteRunConfiguration = this;
                    final CidrRemoteDebugParameters cidrRemoteDebugParameters = cLionRemoteRunConfiguration.params;
                    final String s2 = cidrRemoteDebugParameters.getSymbolFile();
                    final File file = new File(s2);
                    final boolean b2 = file.exists();
                    if (!b2) {
                        break Label_0057;
                    }
                    break Label_0057;
                }
                catch (RuntimeConfigurationException ex2) {
                    throw b((Exception)ex2);
                }
            }
            try {
                final CLionRemoteRunConfiguration cLionRemoteRunConfiguration = this;
                final CidrRemoteDebugParameters cidrRemoteDebugParameters = cLionRemoteRunConfiguration.params;
                final String s2 = cidrRemoteDebugParameters.getSymbolFile();
                final File file = new File(s2);
                final boolean b2 = file.exists();
                if (!b2) {
                    throw (Throwable)new RuntimeConfigurationWarning(CidrDebuggerBundle.message("run.configuration.gdb.symbolFile.invalid", this.params.getSymbolFile()));
                }
            }
            catch (RuntimeConfigurationException ex3) {
                throw b((Exception)ex3);
            }
        }
        final String s3 = this.params.getSysroot();
        Label_0148: {
            Label_0118: {
                try {
                    if (!StringsKt.isBlank((CharSequence)s3)) {
                        final boolean b3 = true;
                        break Label_0118;
                    }
                }
                catch (RuntimeConfigurationException ex4) {
                    throw b((Exception)ex4);
                }
                final boolean b3 = false;
                try {
                    if (!b3) {
                        break Label_0148;
                    }
                    final CLionRemoteRunConfiguration cLionRemoteRunConfiguration2 = this;
                    final CidrRemoteDebugParameters cidrRemoteDebugParameters2 = cLionRemoteRunConfiguration2.params;
                    final String s4 = cidrRemoteDebugParameters2.getSysroot();
                    final File file2 = new File(s4);
                    final boolean b4 = file2.exists();
                    if (!b4) {
                        break Label_0148;
                    }
                    break Label_0148;
                }
                catch (RuntimeConfigurationException ex5) {
                    throw b((Exception)ex5);
                }
            }
            try {
                final CLionRemoteRunConfiguration cLionRemoteRunConfiguration2 = this;
                final CidrRemoteDebugParameters cidrRemoteDebugParameters2 = cLionRemoteRunConfiguration2.params;
                final String s4 = cidrRemoteDebugParameters2.getSysroot();
                final File file2 = new File(s4);
                final boolean b4 = file2.exists();
                if (!b4) {
                    throw (Throwable)new RuntimeConfigurationWarning(CidrDebuggerBundle.message("run.configuration.gdb.sysroot.invalid", this.params.getSysroot()));
                }
            }
            catch (RuntimeConfigurationException ex6) {
                throw b((Exception)ex6);
            }
        }
        final Iterator<Object> iterator = this.params.driverPathMapping().iterator();
        while (true) {
            while (iterator.hasNext()) {
                final String s5 = iterator.next().from;
                Label_0276: {
                    Label_0261: {
                        Label_0252: {
                            try {
                                if (s5 == null) {
                                    break Label_0252;
                                }
                                final String s6 = s5;
                                final boolean b5 = StringsKt.isBlank((CharSequence)s6);
                                if (b5) {
                                    break Label_0252;
                                }
                                break Label_0252;
                            }
                            catch (RuntimeConfigurationException ex7) {
                                throw b((Exception)ex7);
                            }
                            try {
                                final String s6 = s5;
                                final boolean b5 = StringsKt.isBlank((CharSequence)s6);
                                if (b5) {
                                    final boolean b6 = true;
                                    break Label_0261;
                                }
                            }
                            catch (RuntimeConfigurationException ex8) {
                                throw b((Exception)ex8);
                            }
                        }
                        final boolean b6 = false;
                        try {
                            if (b6) {
                                final boolean b7 = true;
                                break Label_0276;
                            }
                            continue;
                        }
                        catch (RuntimeConfigurationException ex9) {
                            throw b((Exception)ex9);
                        }
                    }
                    continue;
                    try {
                        final boolean b7;
                        if (b7) {
                            throw (Throwable)new RuntimeConfigurationException(CidrDebuggerBundle.message("run.configuration.gdb.pathMappings.invalid", new Object[0]));
                        }
                    }
                    catch (RuntimeConfigurationException ex10) {
                        throw b((Exception)ex10);
                    }
                }
                final List<DebuggerDriver.PathMapping> list = this.params.driverPathMapping();
                final ArrayList<DebuggerDriver.PathMapping> list2 = new ArrayList<DebuggerDriver.PathMapping>();
                for (final DebuggerDriver.PathMapping next : list) {
                    final DebuggerDriver.PathMapping pathMapping = next;
                    Label_0389: {
                        try {
                            if (!new File(pathMapping.to).exists()) {
                                final boolean b8 = true;
                                break Label_0389;
                            }
                        }
                        catch (RuntimeConfigurationException ex11) {
                            throw b((Exception)ex11);
                        }
                        final boolean b8 = false;
                        try {
                            if (!b8) {
                                continue;
                            }
                            list2.add(next);
                        }
                        catch (RuntimeConfigurationException ex12) {
                            throw b((Exception)ex12);
                        }
                    }
                }
                final ArrayList<DebuggerDriver.PathMapping> list4;
                final ArrayList<String> list3 = new ArrayList<String>(CollectionsKt.collectionSizeOrDefault((Iterable)(list4 = list2), 10));
                final Iterator<Object> iterator3 = list4.iterator();
                while (iterator3.hasNext()) {
                    list3.add(iterator3.next().to);
                }
                final ArrayList<String> list5 = list3;
                final ArrayList<String> list6 = list5;
                Label_0529: {
                    try {
                        if (!list6.isEmpty()) {
                            final boolean b9 = true;
                            break Label_0529;
                        }
                    }
                    catch (RuntimeConfigurationException ex13) {
                        throw b((Exception)ex13);
                    }
                    final boolean b9 = false;
                    try {
                        if (b9) {
                            throw (Throwable)new RuntimeConfigurationWarning(CidrDebuggerBundle.message("run.configuration.gdb.pathMappings.local.does.not.exist", CollectionsKt.joinToString$default((Iterable)list5, (CharSequence)"<br>", (CharSequence)null, (CharSequence)null, 0, (CharSequence)null, (Function1)null, 62, (Object)null)));
                        }
                    }
                    catch (RuntimeConfigurationException ex14) {
                        throw b((Exception)ex14);
                    }
                }
                return;
            }
            final boolean b7 = false;
            continue;
        }
    }
    
    @Nullable
    public CidrCommandLineState getState(@NotNull final Executor executor, @NotNull final ExecutionEnvironment executionEnvironment) {
        Intrinsics.checkParameterIsNotNull((Object)executor, "executor");
        Intrinsics.checkParameterIsNotNull((Object)executionEnvironment, "environment");
        final Project project = this.getProject();
        Intrinsics.checkExpressionValueIsNotNull((Object)project, "project");
        return new CidrCommandLineState(executionEnvironment, new RemoteGDBLauncher(project, this.debugger, this.params));
    }
    
    public void writeExternal(@Nullable final Element element) {
        super.writeExternal(element);
        final String debugger = this.debugger;
        if (debugger != null) {
            final String s = debugger;
            try {
                if (element != null) {
                    element.setAttribute("debugger", FileUtil.toSystemIndependentName(s));
                }
            }
            catch (TypeCastException ex) {
                throw b((Exception)ex);
            }
        }
        try {
            if (element != null) {
                element.setAttribute("remoteCommand", this.params.getRemoteCommand());
            }
        }
        catch (TypeCastException ex2) {
            throw b((Exception)ex2);
        }
        try {
            if (element != null) {
                element.setAttribute("symbolFile", FileUtil.toSystemIndependentName(this.params.getSymbolFile()));
            }
        }
        catch (TypeCastException ex3) {
            throw b((Exception)ex3);
        }
        try {
            if (element != null) {
                element.setAttribute("sysroot", FileUtil.toSystemIndependentName(this.params.getSysroot()));
            }
        }
        catch (TypeCastException ex4) {
            throw b((Exception)ex4);
        }
        final List<CidrRemotePathMapping> list = this.params.getPathMappings();
        boolean b = false;
        Label_0156: {
            try {
                if (!list.isEmpty()) {
                    b = true;
                    break Label_0156;
                }
            }
            catch (TypeCastException ex5) {
                throw b((Exception)ex5);
            }
            b = false;
        }
        if (b) {
            for (final CidrRemotePathMapping cidrRemotePathMapping : this.params.getPathMappings()) {
                final Element element2 = new Element("pathMapping");
                try {
                    element2.setAttribute("remote", ":" + cidrRemotePathMapping.getRemote());
                    element2.setAttribute("local", FileUtil.toSystemIndependentName(cidrRemotePathMapping.getLocal()));
                    if (element == null) {
                        continue;
                    }
                    element.addContent(element2);
                }
                catch (TypeCastException ex6) {
                    throw b((Exception)ex6);
                }
            }
        }
    }
    
    public void readExternal(@Nullable final Element element) {
        CidrRemoteDebugParameters params3 = null;
        String sysroot = null;
        Label_0157: {
            Label_0147: {
                Label_0119: {
                    CidrRemoteDebugParameters params2 = null;
                    Label_0109: {
                        Label_0081: {
                            CidrRemoteDebugParameters params = null;
                            Label_0071: {
                                Label_0025: {
                                    try {
                                        super.readExternal(element);
                                        if (element != null) {
                                            final String attributeValue = element.getAttributeValue("debugger");
                                            break Label_0025;
                                        }
                                    }
                                    catch (TypeCastException ex) {
                                        throw b((Exception)ex);
                                    }
                                    final String attributeValue = null;
                                    try {
                                        this.debugger = attributeValue;
                                        this.params = new CidrRemoteDebugParameters(null, null, null, null, 15, null);
                                        params = this.params;
                                        if (element == null) {
                                            break Label_0071;
                                        }
                                        final String s = "remoteCommand";
                                        final String s2;
                                        final String remoteCommand = s2 = element.getAttributeValue(s);
                                        if (s2 != null) {
                                            break Label_0071;
                                        }
                                        break Label_0071;
                                    }
                                    catch (TypeCastException ex2) {
                                        throw b((Exception)ex2);
                                    }
                                }
                                try {
                                    final String s = "remoteCommand";
                                    final String s2;
                                    final String remoteCommand = s2 = element.getAttributeValue(s);
                                    if (s2 != null) {
                                        break Label_0081;
                                    }
                                }
                                catch (TypeCastException ex3) {
                                    throw b((Exception)ex3);
                                }
                            }
                            final String remoteCommand = "";
                            try {
                                params.setRemoteCommand(remoteCommand);
                                params2 = this.params;
                                if (element == null) {
                                    break Label_0109;
                                }
                                final String s3 = "symbolFile";
                                final String s4;
                                final String symbolFile = s4 = element.getAttributeValue(s3);
                                if (s4 != null) {
                                    break Label_0109;
                                }
                                break Label_0109;
                            }
                            catch (TypeCastException ex4) {
                                throw b((Exception)ex4);
                            }
                        }
                        try {
                            final String s3 = "symbolFile";
                            final String s4;
                            final String symbolFile = s4 = element.getAttributeValue(s3);
                            if (s4 != null) {
                                break Label_0119;
                            }
                        }
                        catch (TypeCastException ex5) {
                            throw b((Exception)ex5);
                        }
                    }
                    final String symbolFile = "";
                    try {
                        params2.setSymbolFile(symbolFile);
                        params3 = this.params;
                        if (element == null) {
                            break Label_0147;
                        }
                        final String s5 = "sysroot";
                        final String s6;
                        sysroot = (s6 = element.getAttributeValue(s5));
                        if (s6 != null) {
                            break Label_0147;
                        }
                        break Label_0147;
                    }
                    catch (TypeCastException ex6) {
                        throw b((Exception)ex6);
                    }
                }
                try {
                    final String s5 = "sysroot";
                    final String s6;
                    sysroot = (s6 = element.getAttributeValue(s5));
                    if (s6 != null) {
                        break Label_0157;
                    }
                }
                catch (TypeCastException ex7) {
                    throw b((Exception)ex7);
                }
            }
            sysroot = "";
        }
        params3.setSysroot(sysroot);
        final ArrayList<CidrRemotePathMapping> list = new ArrayList<CidrRemotePathMapping>();
        List children = null;
        Label_0187: {
            try {
                if (element != null) {
                    children = element.getChildren("pathMapping");
                    break Label_0187;
                }
            }
            catch (TypeCastException ex8) {
                throw b((Exception)ex8);
            }
            children = null;
        }
        final List list2 = children;
        if (list2 != null) {
            for (final Element element2 : (List<Object>)list2) {
                String attributeValue2 = null;
                Label_0254: {
                    try {
                        if ((attributeValue2 = element2.getAttributeValue("remote")) != null) {
                            break Label_0254;
                        }
                    }
                    catch (TypeCastException ex9) {
                        throw b((Exception)ex9);
                    }
                    attributeValue2 = "";
                }
                String s7 = attributeValue2;
                if (StringsKt.startsWith$default(s7, ":", false, 2, (Object)null)) {
                    final String s8 = s7;
                    final int n = 1;
                    String s9;
                    try {
                        s9 = s8;
                        if (s9 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                        }
                    }
                    catch (TypeCastException ex10) {
                        throw b((Exception)ex10);
                    }
                    final String substring = s9.substring(n);
                    Intrinsics.checkExpressionValueIsNotNull((Object)substring, "(this as java.lang.String).substring(startIndex)");
                    s7 = substring;
                }
                ArrayList<CidrRemotePathMapping> list3 = null;
                String s10 = null;
                String attributeValue3 = null;
                Label_0337: {
                    try {
                        list3 = list;
                        s10 = s7;
                        if ((attributeValue3 = element2.getAttributeValue("local")) != null) {
                            break Label_0337;
                        }
                    }
                    catch (TypeCastException ex11) {
                        throw b((Exception)ex11);
                    }
                    attributeValue3 = "";
                }
                list3.add(new CidrRemotePathMapping(s10, attributeValue3));
            }
        }
        this.params.setPathMappings(list);
    }
    
    public CLionRemoteRunConfiguration(@NotNull final Project project, @NotNull final ConfigurationFactory configurationFactory, @NotNull final String s) {
        Intrinsics.checkParameterIsNotNull((Object)project, "project");
        Intrinsics.checkParameterIsNotNull((Object)configurationFactory, "factory");
        Intrinsics.checkParameterIsNotNull((Object)s, "name");
        super(project, configurationFactory, s);
        this.params = new CidrRemoteDebugParameters(null, null, null, null, 15, null);
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
