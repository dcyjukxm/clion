// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution.remote;

import com.intellij.execution.configurations.ConfigurationType;
import org.jdom.Element;
import kotlin.jvm.internal.Intrinsics;
import com.intellij.execution.configurations.RunConfiguration;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import kotlin.Metadata;
import com.intellij.execution.configurations.ConfigurationFactory;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b" }, d2 = { "com/jetbrains/cidr/cpp/execution/remote/CLionRemoteRunConfigurationType$factory$1", "Lcom/intellij/execution/configurations/ConfigurationFactory;", "(Lcom/jetbrains/cidr/cpp/execution/remote/CLionRemoteRunConfigurationType;Lcom/intellij/execution/configurations/ConfigurationType;)V", "createConfiguration", "Lcom/intellij/execution/configurations/RunConfiguration;", "name", "", "template", "createTemplateConfiguration", "project", "Lcom/intellij/openapi/project/Project;", "clion" })
public static final class CLionRemoteRunConfigurationType$factory$1 extends ConfigurationFactory {
    @NotNull
    public RunConfiguration createTemplateConfiguration(@NotNull final Project project) {
        Intrinsics.checkParameterIsNotNull((Object)project, "project");
        final ConfigurationFactory configurationFactory = this;
        final String name = this.getName();
        Intrinsics.checkExpressionValueIsNotNull((Object)name, "name");
        return (RunConfiguration)new CLionRemoteRunConfiguration(project, configurationFactory, name);
    }
    
    @NotNull
    public RunConfiguration createConfiguration(@NotNull final String s, @NotNull final RunConfiguration runConfiguration) {
        Intrinsics.checkParameterIsNotNull((Object)s, "name");
        Intrinsics.checkParameterIsNotNull((Object)runConfiguration, "template");
        final Project project = runConfiguration.getProject();
        Intrinsics.checkExpressionValueIsNotNull((Object)project, "template.project");
        final CLionRemoteRunConfiguration cLionRemoteRunConfiguration = new CLionRemoteRunConfiguration(project, this, s);
        if (runConfiguration instanceof CLionRemoteRunConfiguration) {
            final Element element = new Element("copy");
            runConfiguration.writeExternal(element);
            cLionRemoteRunConfiguration.readExternal(element);
        }
        return (RunConfiguration)cLionRemoteRunConfiguration;
    }
}