// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi;

import org.jetbrains.annotations.NotNull;
import java.util.List;

public interface CMakeCommandArguments extends CMakeCommandArgumentsMixin
{
    @NotNull
    List<CMakeArgument> getCMakeArgumentList();
}
