// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi;

import org.jetbrains.annotations.NotNull;
import java.util.List;

public interface OCBlockStatement extends OCStatement, OCLocalScopeable, OCLocalBlock, OCBlockOwner
{
    @NotNull
    List<OCStatement> getStatements();
}
