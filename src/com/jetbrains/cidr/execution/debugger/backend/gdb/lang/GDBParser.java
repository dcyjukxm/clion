// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb.lang;

import com.intellij.util.ArrayUtil;
import com.jetbrains.cidr.execution.debugger.backend.lang.GDBTokenType;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.PsiParser;

public class GDBParser implements PsiParser
{
    private static final String[] GDB_EXPRESSION_COMMANDS;
    
    @NotNull
    public ASTNode parse(final IElementType elementType, final PsiBuilder psiBuilder) {
        final PsiBuilder.Marker mark = psiBuilder.mark();
        ASTNode treeBuilt;
        try {
            this.a(psiBuilder);
            mark.done(elementType);
            treeBuilt = psiBuilder.getTreeBuilt();
            if (treeBuilt == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/lang/GDBParser", "parse"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return treeBuilt;
    }
    
    private void a(final PsiBuilder psiBuilder) {
        final PsiBuilder.Marker mark = psiBuilder.mark();
        if (psiBuilder.getTokenType() == GDBTokenType.IDENTIFIER) {
            final String tokenText = psiBuilder.getTokenText();
            final PsiBuilder.Marker mark2 = psiBuilder.mark();
            psiBuilder.advanceLexer();
            mark2.done((IElementType)GDBTokenType.COMMAND_NAME);
            a(psiBuilder, a(tokenText));
        }
        else {
            b(psiBuilder);
        }
        mark.done((IElementType)GDBTokenType.DBG_COMMAND);
    }
    
    private static boolean a(final String s) {
        return ArrayUtil.contains(s, GDBParser.GDB_EXPRESSION_COMMANDS);
    }
    
    private static void b(final PsiBuilder psiBuilder) {
        try {
            while (!psiBuilder.eof()) {
                psiBuilder.advanceLexer();
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
    }
    
    private static void a(final PsiBuilder psiBuilder, final boolean b) {
        final PsiBuilder.Marker mark = psiBuilder.mark();
        try {
            while (!psiBuilder.eof()) {
                psiBuilder.advanceLexer();
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        PsiBuilder.Marker marker = null;
        GDBTokenType gdbTokenType = null;
        Label_0047: {
            try {
                marker = mark;
                if (b) {
                    gdbTokenType = GDBTokenType.EXPRESSION_PLACEHOLDER;
                    break Label_0047;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            gdbTokenType = GDBTokenType.COMMAND_ARGUMENTS;
        }
        marker.done((IElementType)gdbTokenType);
    }
    
    static {
        GDB_EXPRESSION_COMMANDS = new String[] { "print", "p", "print-object", "po", "call" };
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
