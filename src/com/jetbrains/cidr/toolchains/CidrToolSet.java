// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.toolchains;

import java.util.Map;
import org.jdom.Element;
import gnu.trove.TObjectHashingStrategy;
import gnu.trove.THashMap;
import com.intellij.util.text.CaseInsensitiveStringHashingStrategy;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.execution.ExecutionException;
import java.util.List;
import com.jetbrains.cidr.lang.toolchains.CidrToolEnvironment;
import com.intellij.execution.configurations.GeneralCommandLine;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import java.io.File;

public abstract class CidrToolSet extends CidrTool
{
    protected static final int READ_VERSION_TIMEOUT = 10000;
    @NotNull
    protected final File myHome;
    
    public CidrToolSet(@NotNull final File myHome) {
        if (myHome == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "home", "com/jetbrains/cidr/toolchains/CidrToolSet", "<init>"));
        }
        this.myHome = myHome;
    }
    
    @NotNull
    public abstract String getName();
    
    public abstract String checkVersion(@NotNull final String p0);
    
    public boolean isMSVC() {
        return false;
    }
    
    public boolean isCygwin() {
        return false;
    }
    
    public boolean isMinGW() {
        return false;
    }
    
    public boolean isMinGW64() {
        return false;
    }
    
    @NotNull
    public File getHome() {
        File myHome;
        try {
            myHome = this.myHome;
            if (myHome == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/toolchains/CidrToolSet", "getHome"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return myHome;
    }
    
    @NotNull
    public String getHomePath() {
        String path;
        try {
            path = this.myHome.getPath();
            if (path == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/toolchains/CidrToolSet", "getHomePath"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return path;
    }
    
    @Contract("null -> null")
    public File getSubFile(@Nullable final String s) {
        try {
            if (s == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return new File(this.myHome, s);
    }
    
    @Contract("null -> null")
    public String getSubFilePath(@Nullable final String s) {
        final File subFile = this.getSubFile(s);
        try {
            if (subFile == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return subFile.getPath();
    }
    
    @NotNull
    public abstract char[] getSupportedFileSeparators();
    
    @NotNull
    public abstract File getGDBPath();
    
    public abstract boolean forceToolSetGDB();
    
    public void prepareEnvironment(@NotNull final GeneralCommandLine generalCommandLine, @NotNull final CidrToolEnvironment.PrepareFor prepareFor, @NotNull final List<Option> list) throws ExecutionException {
        try {
            if (generalCommandLine == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cl", "com/jetbrains/cidr/toolchains/CidrToolSet", "prepareEnvironment"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        try {
            if (prepareFor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "prepareFor", "com/jetbrains/cidr/toolchains/CidrToolSet", "prepareEnvironment"));
            }
        }
        catch (ExecutionException ex2) {
            throw b((Exception)ex2);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "options", "com/jetbrains/cidr/toolchains/CidrToolSet", "prepareEnvironment"));
            }
        }
        catch (ExecutionException ex3) {
            throw b((Exception)ex3);
        }
    }
    
    protected static void prependPathVariable(@NotNull final GeneralCommandLine generalCommandLine, @NotNull String s) {
        try {
            if (generalCommandLine == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cl", "com/jetbrains/cidr/toolchains/CidrToolSet", "prependPathVariable"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "path", "com/jetbrains/cidr/toolchains/CidrToolSet", "prependPathVariable"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        s = FileUtil.toSystemDependentName(s);
        final THashMap tHashMap = new THashMap(generalCommandLine.getParentEnvironment(), (TObjectHashingStrategy)CaseInsensitiveStringHashingStrategy.INSTANCE);
        Label_0167: {
            try {
                if (!generalCommandLine.isPassParentEnvironment() || !((Map)tHashMap).containsKey("PATH")) {
                    break Label_0167;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            s = s + ";" + ((Map<K, String>)tHashMap).get((Object)"PATH");
        }
        generalCommandLine.getEnvironment().put("PATH", s);
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
    
    public interface Option
    {
        @NotNull
        String getValue();
        
        void write(@NotNull final Element p0);
        
        @NotNull
        String getUniqueID();
    }
}
