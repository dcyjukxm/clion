// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon.clang.tidy;

import java.util.List;

static class YamlDiagnostic
{
    public String Message;
    public String DiagnosticName;
    public String FilePath;
    public int FileOffset;
    public List<YamlReplacement> Replacements;
}
