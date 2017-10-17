// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.toolchains;

import com.intellij.util.LineSeparator;
import com.intellij.openapi.util.io.FileUtil;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.EnvironmentUtil;
import java.util.concurrent.ConcurrentHashMap;
import com.intellij.openapi.application.Application;
import com.intellij.execution.ExecutionException;
import java.io.File;
import com.intellij.util.containers.ContainerUtil;
import java.util.Collections;
import com.intellij.openapi.wm.IdeFrame;
import com.intellij.openapi.application.ApplicationActivationListener;
import com.intellij.openapi.application.ApplicationManager;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.List;
import java.util.Map;

private static class EnvCache
{
    private static final Map<List<String>, Map<String, String>> myCache;
    private static final AtomicBoolean myListenerAdded;
    
    public static Map<String, String> getEnvironment(final String s, final List<String> list) throws ExecutionException {
        final Application application = ApplicationManager.getApplication();
        Label_0025: {
            try {
                if (application == null) {
                    break Label_0025;
                }
                final AtomicBoolean atomicBoolean = EnvCache.myListenerAdded;
                final boolean b = true;
                final boolean b2 = atomicBoolean.getAndSet(b);
                if (!b2) {
                    break Label_0025;
                }
                break Label_0025;
            }
            catch (Exception ex) {
                throw b(ex);
            }
            try {
                final AtomicBoolean atomicBoolean = EnvCache.myListenerAdded;
                final boolean b = true;
                final boolean b2 = atomicBoolean.getAndSet(b);
                if (!b2) {
                    application.getMessageBus().connect().subscribe(ApplicationActivationListener.TOPIC, (Object)new ApplicationActivationListener() {
                        public void applicationDeactivated(final IdeFrame ideFrame) {
                            EnvCache.myCache.clear();
                        }
                    });
                }
            }
            catch (Exception ex2) {
                throw b(ex2);
            }
        }
        final List concat = ContainerUtil.concat((List)Collections.singletonList(s), (List)list);
        Map<String, String> batEnvironment = EnvCache.myCache.get(concat);
        if (batEnvironment == null) {
            try {
                batEnvironment = MyEnvReader.readBatEnvironment(new File(s), list);
                try {
                    if (application != null) {
                        EnvCache.myCache.put(concat, batEnvironment);
                    }
                }
                catch (Exception ex3) {
                    throw b(ex3);
                }
            }
            catch (Exception ex4) {
                throw new ExecutionException("Cannot load Visual C++ environment from " + s + ":\n" + ex4.getMessage(), (Throwable)ex4);
            }
        }
        return batEnvironment;
    }
    
    static {
        myCache = new ConcurrentHashMap<List<String>, Map<String, String>>();
        myListenerAdded = new AtomicBoolean(false);
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
    
    private static class MyEnvReader extends EnvironmentUtil.ShellEnvReader
    {
        @NotNull
        public static Map<String, String> readBatEnvironment(@NotNull final File file, final List<String> list) throws Exception {
            try {
                if (file == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "batToSetEnvironment", "com/jetbrains/cidr/cpp/toolchains/MSVC$EnvCache$MyEnvReader", "readBatEnvironment"));
                }
            }
            catch (Exception ex) {
                throw b(ex);
            }
            File tempFile = null;
            File tempFile2 = null;
            try {
                tempFile = FileUtil.createTempFile("intellij-cmd-env.", ".bat", false);
                tempFile2 = FileUtil.createTempFile("intellij-cmd-env.", ".tmp", false);
                final String separatorString = LineSeparator.CRLF.getSeparatorString();
                FileUtil.writeToFile(tempFile, "call \"" + file.getAbsolutePath() + "\" %*" + separatorString + "set > \"" + tempFile2.getAbsolutePath() + "\"");
                final Map runProcessAndReadEnvs = runProcessAndReadEnvs(ContainerUtil.concat((List)Collections.singletonList(tempFile.getAbsolutePath()), (List)list), file.getParentFile(), tempFile2, separatorString);
                Map map;
                try {
                    map = runProcessAndReadEnvs;
                    if (map == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/toolchains/MSVC$EnvCache$MyEnvReader", "readBatEnvironment"));
                    }
                }
                catch (Exception ex2) {
                    throw b(ex2);
                }
                return (Map<String, String>)map;
            }
            finally {
                try {
                    if (tempFile != null) {
                        FileUtil.delete(tempFile);
                    }
                }
                catch (Exception ex3) {
                    throw b(ex3);
                }
                try {
                    if (tempFile2 != null) {
                        FileUtil.delete(tempFile2);
                    }
                }
                catch (Exception ex4) {
                    throw b(ex4);
                }
            }
        }
        
        private static Exception b(final Exception ex) {
            return ex;
        }
    }
}
