// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.toolchains;

import com.intellij.execution.ExecutionException;
import com.jetbrains.cidr.lang.toolchains.CidrToolEnvironment;
import com.intellij.execution.configurations.GeneralCommandLine;
import java.util.Collections;
import com.jetbrains.cidr.toolchains.CidrToolSet;
import com.jetbrains.cidr.toolchains.CidrExecutableTool;
import com.jetbrains.cidr.cpp.CPPBundle;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.cpp.CPPToolchains;
import com.jetbrains.cidr.toolchains.EnvironmentProblems;
import org.jetbrains.annotations.NotNull;

public class GDBEnvironment extends CPPEnvironment
{
    @NotNull
    private final GDB myGDB;
    
    @Nullable
    public static GDBEnvironment create(@NotNull final EnvironmentProblems environmentProblems) {
        try {
            if (environmentProblems == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "problems", "com/jetbrains/cidr/cpp/toolchains/GDBEnvironment", "create"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return create(environmentProblems, CPPToolchains.getInstance().getGDB());
    }
    
    @Nullable
    public static GDBEnvironment create(@NotNull final EnvironmentProblems environmentProblems, @Nullable final GDB gdb) {
        try {
            if (environmentProblems == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "problems", "com/jetbrains/cidr/cpp/toolchains/GDBEnvironment", "create"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        final ValidationResult validate = CPPEnvironment.validate(CPPToolchains.getInstance(), environmentProblems, true);
        Label_0087: {
            try {
                CPPEnvironment.checkTool(gdb, CPPBundle.message("gdb", new Object[0]), environmentProblems);
                if (gdb == null) {
                    return null;
                }
                final EnvironmentProblems environmentProblems2 = environmentProblems;
                final boolean b = environmentProblems2.hasProblems();
                if (!b) {
                    break Label_0087;
                }
                return null;
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
            try {
                final EnvironmentProblems environmentProblems2 = environmentProblems;
                final boolean b = environmentProblems2.hasProblems();
                if (!b) {
                    return new GDBEnvironment(validate.toolSet, gdb);
                }
            }
            catch (IllegalArgumentException ex3) {
                throw c(ex3);
            }
        }
        return null;
    }
    
    public GDBEnvironment(@Nullable final CidrToolSet set, @NotNull final GDB myGDB) {
        if (myGDB == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "gdb", "com/jetbrains/cidr/cpp/toolchains/GDBEnvironment", "<init>"));
        }
        super(set, Collections.emptyList());
        this.myGDB = myGDB;
    }
    
    @Override
    public void prepare(@NotNull final GeneralCommandLine generalCommandLine, @NotNull final PrepareFor prepareFor) throws ExecutionException {
        try {
            if (generalCommandLine == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cl", "com/jetbrains/cidr/cpp/toolchains/GDBEnvironment", "prepare"));
            }
        }
        catch (ExecutionException ex) {
            throw c((Exception)ex);
        }
        try {
            if (prepareFor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "prepareFor", "com/jetbrains/cidr/cpp/toolchains/GDBEnvironment", "prepare"));
            }
        }
        catch (ExecutionException ex2) {
            throw c((Exception)ex2);
        }
        super.prepare(generalCommandLine, prepareFor);
        this.myGDB.prepare(generalCommandLine);
    }
    
    private static Exception c(final Exception ex) {
        return ex;
    }
}
