// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lang;

import com.intellij.psi.tree.IStubFileElementType;
import com.jetbrains.cidr.execution.debugger.backend.gdb.lang.GDBLanguage;
import com.intellij.lang.Language;
import com.jetbrains.cidr.execution.debugger.backend.lldb.lang.LLDBLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;

public class GDBTokenType extends IElementType implements TokenType
{
    public static final IFileElementType GDB_FILE;
    public static final IFileElementType LLDB_FILE;
    public static final GDBTokenType IDENTIFIER;
    public static final GDBTokenType NUMBER;
    public static final GDBTokenType HEX_NUMBER;
    public static final GDBTokenType STAR;
    public static final GDBTokenType FORMAT_SPECIFIER;
    public static final GDBTokenType QUOTED_STRING;
    public static final GDBTokenType AT;
    public static final GDBTokenType DASH;
    public static final GDBTokenType DASHDASH;
    public static final GDBTokenType SOME_CHARACTER;
    public static final GDBTokenType COMMAND_NAME;
    public static final GDBTokenType DBG_COMMAND;
    public static final GDBTokenType COMMAND_ARG;
    public static final GDBTokenType COMMAND_ARGS_END;
    public static final GDBTokenType COMMAND_ARGUMENTS;
    public static final GDBTokenType EXPRESSION_PLACEHOLDER;
    
    public GDBTokenType(@NotNull @NonNls final String s) {
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "debugName", "com/jetbrains/cidr/execution/debugger/backend/lang/GDBTokenType", "<init>"));
        }
        super(s, (Language)LLDBLanguage.INSTANCE);
    }
    
    static {
        GDB_FILE = (IFileElementType)new IStubFileElementType(GDBLanguage.INSTANCE);
        LLDB_FILE = (IFileElementType)new IStubFileElementType(LLDBLanguage.INSTANCE);
        IDENTIFIER = new GDBTokenType("IDENTIFIER");
        NUMBER = new GDBTokenType("NUMBER");
        HEX_NUMBER = new GDBTokenType("HEX_NUMBER");
        STAR = new GDBTokenType("STAR");
        FORMAT_SPECIFIER = new GDBTokenType("FORMAT_SPECIFIER");
        QUOTED_STRING = new GDBTokenType("QUOTED_STRING");
        AT = new GDBTokenType("AT");
        DASH = new GDBTokenType("DASH");
        DASHDASH = new GDBTokenType("DASHDASH");
        SOME_CHARACTER = new GDBTokenType("SOME_CHARACTER");
        COMMAND_NAME = new GDBTokenType("COMMAND_NAME");
        DBG_COMMAND = new GDBTokenType("GDB_COMMAND");
        COMMAND_ARG = new GDBTokenType("COMMAND_ARG");
        COMMAND_ARGS_END = new GDBTokenType("COMMAND_ARGS_END");
        COMMAND_ARGUMENTS = new GDBTokenType("COMMAND_ARGUMENTS");
        EXPRESSION_PLACEHOLDER = new GDBTokenType("EXPRESSION_PLACEHOLDER");
    }
}
