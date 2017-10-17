// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution.debugger;

import com.jetbrains.cidr.cpp.execution.CMakeLauncher;
import java.util.Iterator;
import java.util.Collections;
import java.util.Collection;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.execution.debugger.CidrLocalAttachDebugger;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriverConfiguration;
import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.openapi.extensions.Extensions;
import com.jetbrains.cidr.cpp.execution.CLionCustomDebuggerProvider;
import java.util.ArrayList;
import com.intellij.openapi.util.UserDataHolder;
import com.intellij.execution.process.ProcessInfo;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.cpp.CPPToolchains;
import com.intellij.xdebugger.attach.XLocalAttachDebugger;
import java.util.List;
import com.intellij.openapi.util.Key;
import com.intellij.xdebugger.attach.XLocalAttachDebuggerProvider;

public class CLionLocalAttachDebuggerProvider implements XLocalAttachDebuggerProvider
{
    private static final Key<List<XLocalAttachDebugger>> DEBUGGERS_KEY;
    @NotNull
    private final CPPToolchains myToolchains;
    private final boolean myFilterUnsupported;
    
    public CLionLocalAttachDebuggerProvider() {
        this(CPPToolchains.getInstance(), true);
    }
    
    public CLionLocalAttachDebuggerProvider(@NotNull final CPPToolchains myToolchains, final boolean myFilterUnsupported) {
        if (myToolchains == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "toolchains", "com/jetbrains/cidr/cpp/execution/debugger/CLionLocalAttachDebuggerProvider", "<init>"));
        }
        this.myToolchains = myToolchains;
        this.myFilterUnsupported = myFilterUnsupported;
    }
    
    @NotNull
    @Override
    public List<XLocalAttachDebugger> getAvailableDebuggers(@NotNull final Project project, @NotNull final ProcessInfo processInfo, @NotNull final UserDataHolder userDataHolder) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/execution/debugger/CLionLocalAttachDebuggerProvider", "getAvailableDebuggers"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (processInfo == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processInfo", "com/jetbrains/cidr/cpp/execution/debugger/CLionLocalAttachDebuggerProvider", "getAvailableDebuggers"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (userDataHolder == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "contextHolder", "com/jetbrains/cidr/cpp/execution/debugger/CLionLocalAttachDebuggerProvider", "getAvailableDebuggers"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final List list = (List)userDataHolder.getUserData((Key)CLionLocalAttachDebuggerProvider.DEBUGGERS_KEY);
        Label_0199: {
            List list2 = null;
            Label_0164: {
                try {
                    if (list == null) {
                        break Label_0199;
                    }
                    list2 = list;
                    if (list2 == null) {
                        break Label_0164;
                    }
                    return (List<XLocalAttachDebugger>)list2;
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
                try {
                    list2 = list;
                    if (list2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/debugger/CLionLocalAttachDebuggerProvider", "getAvailableDebuggers"));
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
            }
            return (List<XLocalAttachDebugger>)list2;
        }
        List<XLocalAttachDebugger> filter = new ArrayList<XLocalAttachDebugger>();
        try {
            if (!this.myToolchains.isUseLLDB()) {
                filter.add(createGDBDebugger());
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        Label_0263: {
            try {
                if (this.myToolchains.isUseLLDB()) {
                    break Label_0263;
                }
                final CLionLocalAttachDebuggerProvider cLionLocalAttachDebuggerProvider = this;
                final CPPToolchains cppToolchains = cLionLocalAttachDebuggerProvider.myToolchains;
                final boolean b = cppToolchains.isLLDBAvailable();
                if (b) {
                    break Label_0263;
                }
                break Label_0263;
            }
            catch (IllegalArgumentException ex7) {
                throw a(ex7);
            }
            try {
                final CLionLocalAttachDebuggerProvider cLionLocalAttachDebuggerProvider = this;
                final CPPToolchains cppToolchains = cLionLocalAttachDebuggerProvider.myToolchains;
                final boolean b = cppToolchains.isLLDBAvailable();
                if (b) {
                    filter.add(createLLDBDebugger());
                }
            }
            catch (IllegalArgumentException ex8) {
                throw a(ex8);
            }
        }
        final CLionCustomDebuggerProvider[] array = (CLionCustomDebuggerProvider[])Extensions.getExtensions((ExtensionPointName)CLionCustomDebuggerProvider.EP_NAME);
        for (int length = array.length, i = 0; i < length; ++i) {
            final Iterator<DebuggerDriverConfiguration> iterator = array[i].getDebuggerConfigurations().iterator();
            while (iterator.hasNext()) {
                filter.add(new CidrLocalAttachDebugger(iterator.next()));
            }
        }
        if (this.myFilterUnsupported) {
            filter = (List<XLocalAttachDebugger>)ContainerUtil.filter((Collection)filter, cidrLocalAttachDebugger -> cidrLocalAttachDebugger.getConfiguration().isAttachSupported());
        }
        final List<Object> unmodifiableList = Collections.unmodifiableList((List<?>)filter);
        List<Object> list3;
        try {
            userDataHolder.putUserData((Key)CLionLocalAttachDebuggerProvider.DEBUGGERS_KEY, (Object)unmodifiableList);
            list3 = unmodifiableList;
            if (list3 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/debugger/CLionLocalAttachDebuggerProvider", "getAvailableDebuggers"));
            }
        }
        catch (IllegalArgumentException ex9) {
            throw a(ex9);
        }
        return (List<XLocalAttachDebugger>)list3;
    }
    
    @NotNull
    public static CidrLocalAttachDebugger createLLDBDebugger() {
        CidrLocalAttachDebugger cidrLocalAttachDebugger;
        try {
            cidrLocalAttachDebugger = new CidrLocalAttachDebugger(CMakeLauncher.createLLDBDriverConfiguration());
            if (cidrLocalAttachDebugger == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/debugger/CLionLocalAttachDebuggerProvider", "createLLDBDebugger"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return cidrLocalAttachDebugger;
    }
    
    @NotNull
    public static CidrLocalAttachDebugger createGDBDebugger() {
        CidrLocalAttachDebugger cidrLocalAttachDebugger;
        try {
            cidrLocalAttachDebugger = new CidrLocalAttachDebugger(CMakeLauncher.createGDBDriverConfiguration());
            if (cidrLocalAttachDebugger == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/execution/debugger/CLionLocalAttachDebuggerProvider", "createGDBDebugger"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return cidrLocalAttachDebugger;
    }
    
    static {
        DEBUGGERS_KEY = Key.create("CLionLocalAttachDebuggerProvider.DEBUGGERS");
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
