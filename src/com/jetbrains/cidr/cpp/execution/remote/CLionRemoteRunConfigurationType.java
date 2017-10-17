// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution.remote;

import com.intellij.execution.configurations.ConfigurationTypeUtil;
import kotlin.jvm.internal.DefaultConstructorMarker;
import com.intellij.icons.AllIcons$RunConfigurations;
import com.jetbrains.cidr.execution.CidrDebuggerBundle;
import org.jetbrains.annotations.NotNull;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.ConfigurationFactory;
import kotlin.Metadata;
import com.intellij.execution.configurations.ConfigurationTypeBase;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0005¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b" }, d2 = { "Lcom/jetbrains/cidr/cpp/execution/remote/CLionRemoteRunConfigurationType;", "Lcom/intellij/execution/configurations/ConfigurationTypeBase;", "()V", "factory", "Lcom/intellij/execution/configurations/ConfigurationFactory;", "getFactory", "()Lcom/intellij/execution/configurations/ConfigurationFactory;", "Companion", "clion" })
public final class CLionRemoteRunConfigurationType extends ConfigurationTypeBase
{
    public static final Companion Companion;
    
    @NotNull
    public final ConfigurationFactory getFactory() {
        return (ConfigurationFactory)new CLionRemoteRunConfigurationType$factory.CLionRemoteRunConfigurationType$factory$1(this, (ConfigurationType)this);
    }
    
    public CLionRemoteRunConfigurationType() {
        super("CLion_Remote", CidrDebuggerBundle.message("run.configuration.gdb.remote", new Object[0]), (String)null, AllIcons$RunConfigurations.Remote);
        this.addFactory(this.getFactory());
    }
    
    static {
        Companion = new Companion(null);
    }
    
    @Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005" }, d2 = { "Lcom/jetbrains/cidr/cpp/execution/remote/CLionRemoteRunConfigurationType$Companion;", "", "()V", "getInstance", "Lcom/jetbrains/cidr/cpp/execution/remote/CLionRemoteRunConfigurationType;", "clion" })
    public static final class Companion
    {
        @NotNull
        public final CLionRemoteRunConfigurationType getInstance() {
            return (CLionRemoteRunConfigurationType)ConfigurationTypeUtil.findConfigurationType((Class)CLionRemoteRunConfigurationType.class);
        }
    }
}
