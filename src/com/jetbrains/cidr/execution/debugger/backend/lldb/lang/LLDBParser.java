// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.lang;

import com.jetbrains.cidr.execution.debugger.backend.lang.GDBTokenType;
import com.intellij.util.ArrayUtil;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.PsiParser;

public class LLDBParser implements PsiParser
{
    private static final String[] LLDB_EXPRESSION_COMMANDS;
    private static final String[] LLDB_NOUNS;
    
    @NotNull
    public ASTNode parse(final IElementType elementType, final PsiBuilder psiBuilder) {
        final PsiBuilder.Marker mark = psiBuilder.mark();
        ASTNode treeBuilt;
        try {
            this.b(psiBuilder);
            mark.done(elementType);
            treeBuilt = psiBuilder.getTreeBuilt();
            if (treeBuilt == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/lldb/lang/LLDBParser", "parse"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return treeBuilt;
    }
    
    private void b(final PsiBuilder p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokeinterface com/intellij/lang/PsiBuilder.mark:()Lcom/intellij/lang/PsiBuilder$Marker;
        //     6: astore_2       
        //     7: aload_1        
        //     8: invokeinterface com/intellij/lang/PsiBuilder.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //    13: getstatic       com/jetbrains/cidr/execution/debugger/backend/lang/GDBTokenType.IDENTIFIER:Lcom/jetbrains/cidr/execution/debugger/backend/lang/GDBTokenType;
        //    16: if_acmpne       133
        //    19: aload_1        
        //    20: invokeinterface com/intellij/lang/PsiBuilder.getTokenText:()Ljava/lang/String;
        //    25: astore_3       
        //    26: aload_1        
        //    27: invokeinterface com/intellij/lang/PsiBuilder.mark:()Lcom/intellij/lang/PsiBuilder$Marker;
        //    32: astore          4
        //    34: aload_1        
        //    35: invokeinterface com/intellij/lang/PsiBuilder.advanceLexer:()V
        //    40: aload_0        
        //    41: aload_3        
        //    42: invokespecial   com/jetbrains/cidr/execution/debugger/backend/lldb/lang/LLDBParser.b:(Ljava/lang/String;)Z
        //    45: ifeq            96
        //    48: aload_1        
        //    49: invokeinterface com/intellij/lang/PsiBuilder.eof:()Z
        //    54: ifne            96
        //    57: goto            64
        //    60: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/lang/LLDBParser.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    63: athrow         
        //    64: aload_1        
        //    65: invokeinterface com/intellij/lang/PsiBuilder.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //    70: getstatic       com/jetbrains/cidr/execution/debugger/backend/lang/GDBTokenType.IDENTIFIER:Lcom/jetbrains/cidr/execution/debugger/backend/lang/GDBTokenType;
        //    73: if_acmpne       96
        //    76: goto            83
        //    79: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/lang/LLDBParser.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    82: athrow         
        //    83: aload_1        
        //    84: invokeinterface com/intellij/lang/PsiBuilder.advanceLexer:()V
        //    89: goto            96
        //    92: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/lang/LLDBParser.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    95: athrow         
        //    96: aload           4
        //    98: getstatic       com/jetbrains/cidr/execution/debugger/backend/lang/GDBTokenType.COMMAND_NAME:Lcom/jetbrains/cidr/execution/debugger/backend/lang/GDBTokenType;
        //   101: invokeinterface com/intellij/lang/PsiBuilder$Marker.done:(Lcom/intellij/psi/tree/IElementType;)V
        //   106: aload_1        
        //   107: invokeinterface com/intellij/lang/PsiBuilder.eof:()Z
        //   112: ifne            130
        //   115: aload_1        
        //   116: aload_3        
        //   117: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/lang/LLDBParser.a:(Ljava/lang/String;)Z
        //   120: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/lang/LLDBParser.a:(Lcom/intellij/lang/PsiBuilder;Z)V
        //   123: goto            130
        //   126: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/lang/LLDBParser.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   129: athrow         
        //   130: goto            137
        //   133: aload_1        
        //   134: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/lang/LLDBParser.c:(Lcom/intellij/lang/PsiBuilder;)V
        //   137: aload_2        
        //   138: getstatic       com/jetbrains/cidr/execution/debugger/backend/lang/GDBTokenType.DBG_COMMAND:Lcom/jetbrains/cidr/execution/debugger/backend/lang/GDBTokenType;
        //   141: invokeinterface com/intellij/lang/PsiBuilder$Marker.done:(Lcom/intellij/psi/tree/IElementType;)V
        //   146: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  34     57     60     64     Ljava/lang/IllegalStateException;
        //  48     76     79     83     Ljava/lang/IllegalStateException;
        //  64     89     92     96     Ljava/lang/IllegalStateException;
        //  96     123    126    130    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0064:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private boolean b(final String s) {
        return ArrayUtil.contains(s, LLDBParser.LLDB_NOUNS);
    }
    
    private static boolean a(final String s) {
        return ArrayUtil.contains(s, LLDBParser.LLDB_EXPRESSION_COMMANDS);
    }
    
    private static void a(final PsiBuilder psiBuilder, final boolean b) {
        final PsiBuilder.Marker mark = psiBuilder.mark();
        boolean b2 = false;
        if (d(psiBuilder)) {
            boolean e;
            do {
                e = e(psiBuilder);
                if (d(psiBuilder)) {
                    continue;
                }
                break;
            } while (e);
            Label_0079: {
                Label_0075: {
                    try {
                        if (e) {
                            break Label_0079;
                        }
                        if (!b) {
                            break Label_0075;
                        }
                    }
                    catch (IllegalStateException ex) {
                        throw a(ex);
                    }
                    final PsiBuilder.Marker mark2 = psiBuilder.mark();
                    c(psiBuilder);
                    mark2.done((IElementType)GDBTokenType.EXPRESSION_PLACEHOLDER);
                    break Label_0079;
                }
                c(psiBuilder);
            }
        }
        else {
            b2 = true;
            c(psiBuilder);
        }
        PsiBuilder.Marker marker = null;
        GDBTokenType gdbTokenType = null;
        Label_0123: {
            Label_0120: {
                Label_0104: {
                    try {
                        marker = mark;
                        if (!b2) {
                            break Label_0120;
                        }
                        final boolean b3 = b;
                        if (b3) {
                            break Label_0104;
                        }
                        break Label_0104;
                    }
                    catch (IllegalStateException ex2) {
                        throw a(ex2);
                    }
                    try {
                        final boolean b3 = b;
                        if (b3) {
                            gdbTokenType = GDBTokenType.EXPRESSION_PLACEHOLDER;
                            break Label_0123;
                        }
                    }
                    catch (IllegalStateException ex3) {
                        throw a(ex3);
                    }
                }
                gdbTokenType = GDBTokenType.COMMAND_ARGUMENTS;
                break Label_0123;
            }
            gdbTokenType = GDBTokenType.COMMAND_ARGUMENTS;
        }
        marker.done((IElementType)gdbTokenType);
    }
    
    private static boolean e(final PsiBuilder psiBuilder) {
        Label_0056: {
            try {
                if (psiBuilder.getTokenType() != GDBTokenType.DASHDASH || psiBuilder.rawLookup(1) != GDBTokenType.WHITE_SPACE) {
                    break Label_0056;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            final PsiBuilder.Marker mark = psiBuilder.mark();
            psiBuilder.advanceLexer();
            mark.done((IElementType)GDBTokenType.COMMAND_ARGS_END);
            return false;
        }
        final PsiBuilder.Marker mark2 = psiBuilder.mark();
        Label_0102: {
            try {
                psiBuilder.advanceLexer();
                if (psiBuilder.getTokenType() == GDBTokenType.IDENTIFIER) {
                    psiBuilder.advanceLexer();
                    break Label_0102;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            psiBuilder.error("identifier expected");
        }
        a(psiBuilder);
        mark2.done((IElementType)GDBTokenType.COMMAND_ARG);
        return true;
    }
    
    private static boolean d(final PsiBuilder p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokeinterface com/intellij/lang/PsiBuilder.eof:()Z
        //     6: ifne            55
        //     9: aload_0        
        //    10: invokeinterface com/intellij/lang/PsiBuilder.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //    15: getstatic       com/jetbrains/cidr/execution/debugger/backend/lang/GDBTokenType.DASH:Lcom/jetbrains/cidr/execution/debugger/backend/lang/GDBTokenType;
        //    18: if_acmpeq       47
        //    21: goto            28
        //    24: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/lang/LLDBParser.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    27: athrow         
        //    28: aload_0        
        //    29: invokeinterface com/intellij/lang/PsiBuilder.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //    34: getstatic       com/jetbrains/cidr/execution/debugger/backend/lang/GDBTokenType.DASHDASH:Lcom/jetbrains/cidr/execution/debugger/backend/lang/GDBTokenType;
        //    37: if_acmpne       55
        //    40: goto            47
        //    43: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/lang/LLDBParser.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    46: athrow         
        //    47: iconst_1       
        //    48: goto            56
        //    51: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/lang/LLDBParser.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    54: athrow         
        //    55: iconst_0       
        //    56: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      21     24     28     Ljava/lang/IllegalStateException;
        //  9      40     43     47     Ljava/lang/IllegalStateException;
        //  28     51     51     55     Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0028:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private static void a(final PsiBuilder psiBuilder) {
        try {
            if (psiBuilder.getTokenType() == GDBTokenType.DASHDASH) {
                return;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        do {
            psiBuilder.advanceLexer();
            if (psiBuilder.eof()) {
                return;
            }
        } while (psiBuilder.rawLookup(1) != GDBTokenType.WHITE_SPACE);
        psiBuilder.advanceLexer();
    }
    
    private static void c(final PsiBuilder psiBuilder) {
        try {
            while (!psiBuilder.eof()) {
                psiBuilder.advanceLexer();
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
    }
    
    static {
        LLDB_EXPRESSION_COMMANDS = new String[] { "print", "p", "po", "expression", "expr" };
        LLDB_NOUNS = new String[] { "breakpoint", "command", "frame", "log", "memory", "platform", "process", "register", "settings", "source", "target", "thread", "type" };
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
