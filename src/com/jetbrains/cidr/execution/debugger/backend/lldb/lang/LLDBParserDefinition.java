// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.lang;

import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.lang.Language;
import com.jetbrains.cidr.execution.debugger.backend.lang.GDBTokenType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.lang.PsiParser;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.debugger.backend.lang.lexer.GDBLexer;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.execution.debugger.backend.lang.DBParserDefinition;

public class LLDBParserDefinition extends DBParserDefinition
{
    @NotNull
    public Lexer createLexer(final Project project) {
        GDBLexer gdbLexer;
        try {
            gdbLexer = new GDBLexer();
            if (gdbLexer == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/lldb/lang/LLDBParserDefinition", "createLexer"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return (Lexer)gdbLexer;
    }
    
    public PsiParser createParser(final Project project) {
        return (PsiParser)new LLDBParser();
    }
    
    public IFileElementType getFileNodeType() {
        return GDBTokenType.LLDB_FILE;
    }
    
    @NotNull
    @Override
    protected Language getLanguage() {
        LLDBLanguage instance;
        try {
            instance = LLDBLanguage.INSTANCE;
            if (instance == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/lldb/lang/LLDBParserDefinition", "getLanguage"));
            }
        }
        catch (IllegalStateException ex) {
            throw b(ex);
        }
        return instance;
    }
    
    @Override
    protected LanguageFileType getFileType() {
        return LLDBFileType.INSTANCE;
    }
    
    private static IllegalStateException b(final IllegalStateException ex) {
        return ex;
    }
}
