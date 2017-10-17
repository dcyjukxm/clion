// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.toolchains;

import com.intellij.util.containers.ContainerUtil;
import java.util.Collections;
import com.intellij.util.LineSeparator;
import com.intellij.openapi.util.io.FileUtil;
import java.util.Map;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import java.io.File;
import com.intellij.util.EnvironmentUtil;

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
