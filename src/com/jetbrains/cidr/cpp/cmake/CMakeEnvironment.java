// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake;

import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.toolchains.CidrExecutableTool;
import com.jetbrains.cidr.cpp.CPPBundle;
import com.jetbrains.cidr.cpp.CPPToolchains;
import com.jetbrains.cidr.toolchains.CidrToolSet;
import java.util.List;
import com.jetbrains.cidr.toolchains.EnvironmentProblems;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.cpp.toolchains.CMake;
import com.jetbrains.cidr.cpp.toolchains.CPPEnvironment;

public class CMakeEnvironment extends CPPEnvironment
{
    private static volatile boolean ourEmulateCMakeEnvironmentError;
    @NotNull
    private final CMake myCMake;
    
    public static void setEmulateCMakeEnvironmentErrorInTests(final boolean ourEmulateCMakeEnvironmentError) {
        CMakeEnvironment.ourEmulateCMakeEnvironmentError = ourEmulateCMakeEnvironmentError;
    }
    
    @Nullable
    public static CMakeEnvironment create(@NotNull final EnvironmentProblems environmentProblems, final boolean b, @NotNull final List<CidrToolSet.Option> list) {
        try {
            if (environmentProblems == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "problems", "com/jetbrains/cidr/cpp/cmake/CMakeEnvironment", "create"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "toolSetOptions", "com/jetbrains/cidr/cpp/cmake/CMakeEnvironment", "create"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final CPPToolchains instance = CPPToolchains.getInstance();
        final ValidationResult validate = CPPEnvironment.validate(instance, environmentProblems, b);
        final CMake cMake = instance.getCMake();
        try {
            if (CMakeEnvironment.ourEmulateCMakeEnvironmentError) {
                environmentProblems.addProblem("Emulated CMake environment problem");
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (b) {
                CPPEnvironment.checkTool(cMake, CPPBundle.message("cmake", new Object[0]), environmentProblems);
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        Label_0170: {
            try {
                if (cMake == null) {
                    return null;
                }
                final EnvironmentProblems environmentProblems2 = environmentProblems;
                final boolean b2 = environmentProblems2.hasProblems();
                if (!b2) {
                    break Label_0170;
                }
                return null;
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
            try {
                final EnvironmentProblems environmentProblems2 = environmentProblems;
                final boolean b2 = environmentProblems2.hasProblems();
                if (!b2) {
                    return new CMakeEnvironment(validate.toolSet, cMake, list);
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
        }
        return null;
    }
    
    public CMakeEnvironment(@Nullable final CidrToolSet set, @NotNull final CMake myCMake, @NotNull final List<CidrToolSet.Option> list) {
        if (myCMake == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cmake", "com/jetbrains/cidr/cpp/cmake/CMakeEnvironment", "<init>"));
        }
        if (list == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "toolSetOptions", "com/jetbrains/cidr/cpp/cmake/CMakeEnvironment", "<init>"));
        }
        super(set, list);
        this.myCMake = myCMake;
    }
    
    @NotNull
    public String getUniqueID() {
        final StringBuilder sb = new StringBuilder();
        try {
            if (this.getToolSet() != null) {
                sb.append("ToolSet: ").append(this.getToolSet().readVersion()).append("@").append(this.getToolSet().getHome()).append("\n");
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        String string;
        try {
            sb.append("Options: ");
            StringUtil.join((Iterable)this.myToolSetOptions, CidrToolSet.Option::getUniqueID, ", ", sb);
            sb.append("\n");
            sb.append("CMake: ").append(this.myCMake.readVersion()).append("@").append(this.myCMake.getExecutable()).append("\n");
            string = sb.toString();
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/CMakeEnvironment", "getUniqueID"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return string;
    }
    
    @NotNull
    public CMake getCMake() {
        CMake myCMake;
        try {
            myCMake = this.myCMake;
            if (myCMake == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/CMakeEnvironment", "getCMake"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myCMake;
    }
    
    static {
        CMakeEnvironment.ourEmulateCMakeEnvironmentError = false;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
