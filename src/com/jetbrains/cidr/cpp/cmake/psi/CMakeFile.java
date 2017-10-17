// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi;

import com.intellij.psi.PsiElementVisitor;
import com.intellij.util.SmartList;
import org.jetbrains.annotations.Nullable;
import java.util.List;
import com.jetbrains.cidr.cpp.cmake.CMakeListsFileType;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.lang.Language;
import com.jetbrains.cidr.cpp.cmake.CMakeLanguage;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.FileViewProvider;
import com.intellij.extapi.psi.PsiFileBase;

public class CMakeFile extends PsiFileBase
{
    public CMakeFile(@NotNull final FileViewProvider viewProvider) {
        if (viewProvider == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "viewProvider", "com/jetbrains/cidr/cpp/cmake/psi/CMakeFile", "<init>"));
        }
        super(viewProvider, CMakeLanguage.getInstance());
    }
    
    @NotNull
    public FileType getFileType() {
        FileType instance;
        try {
            instance = CMakeListsFileType.INSTANCE;
            if (instance == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeFile", "getFileType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return instance;
    }
    
    @NotNull
    public List<CMakeCommand> getTopLevelCommands() {
        List<CMakeCommand> topLevelCommands;
        try {
            topLevelCommands = this.getTopLevelCommands(null);
            if (topLevelCommands == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeFile", "getTopLevelCommands"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return topLevelCommands;
    }
    
    @NotNull
    public List<CMakeCommand> getTopLevelCommands(@Nullable final String s) {
        final SmartList list = new SmartList();
        SmartList list2;
        try {
            this.acceptChildren(new CMakeVisitor() {
                @Override
                public void visitCMakeCommand(@NotNull final CMakeCommand cMakeCommand) {
                    try {
                        if (cMakeCommand == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "each", "com/jetbrains/cidr/cpp/cmake/psi/CMakeFile$1", "visitCMakeCommand"));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw b(ex);
                    }
                    Label_0071: {
                        try {
                            if (s == null) {
                                break Label_0071;
                            }
                            final CMakeCommand cMakeCommand2 = cMakeCommand;
                            final CMakeVisitor cMakeVisitor = this;
                            final String s = s;
                            final boolean b = cMakeCommand2.namesEqual(s);
                            if (b) {
                                break Label_0071;
                            }
                            break Label_0071;
                        }
                        catch (IllegalArgumentException ex2) {
                            throw b(ex2);
                        }
                        try {
                            final CMakeCommand cMakeCommand2 = cMakeCommand;
                            final CMakeVisitor cMakeVisitor = this;
                            final String s = s;
                            final boolean b = cMakeCommand2.namesEqual(s);
                            if (b) {
                                ((List<CMakeCommand>)list).add(cMakeCommand);
                            }
                        }
                        catch (IllegalArgumentException ex3) {
                            throw b(ex3);
                        }
                    }
                    super.visitCMakeCommand(cMakeCommand);
                }
                
                private static IllegalArgumentException b(final IllegalArgumentException ex) {
                    return ex;
                }
            });
            list2 = list;
            if (list2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeFile", "getTopLevelCommands"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (List<CMakeCommand>)list2;
    }
    
    @NotNull
    public List<String> findFirstVariableValues(@Nullable final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnonnull       53
        //     4: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //     7: dup            
        //     8: ifnonnull       52
        //    11: goto            18
        //    14: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/CMakeFile.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    17: athrow         
        //    18: new             Ljava/lang/IllegalStateException;
        //    21: dup            
        //    22: ldc             "@NotNull method %s.%s must not return null"
        //    24: ldc             2
        //    26: anewarray       Ljava/lang/Object;
        //    29: dup            
        //    30: ldc             0
        //    32: ldc             "com/jetbrains/cidr/cpp/cmake/psi/CMakeFile"
        //    34: aastore        
        //    35: dup            
        //    36: ldc             1
        //    38: ldc             "findFirstVariableValues"
        //    40: aastore        
        //    41: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    44: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    47: athrow         
        //    48: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/CMakeFile.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    51: athrow         
        //    52: areturn        
        //    53: aload_0        
        //    54: ldc             "set"
        //    56: invokevirtual   com/jetbrains/cidr/cpp/cmake/psi/CMakeFile.getTopLevelCommands:(Ljava/lang/String;)Ljava/util/List;
        //    59: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    64: astore_2       
        //    65: aload_2        
        //    66: invokeinterface java/util/Iterator.hasNext:()Z
        //    71: ifeq            168
        //    74: aload_2        
        //    75: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    80: checkcast       Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeCommand;
        //    83: astore_3       
        //    84: aload_3        
        //    85: invokeinterface com/jetbrains/cidr/cpp/cmake/psi/CMakeCommand.getFirstArgumentValue:()Ljava/lang/String;
        //    90: astore          4
        //    92: aload           4
        //    94: ifnull          165
        //    97: aload           4
        //    99: aload_1        
        //   100: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //   103: ifeq            165
        //   106: goto            113
        //   109: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/CMakeFile.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   112: athrow         
        //   113: aload_3        
        //   114: invokeinterface com/jetbrains/cidr/cpp/cmake/psi/CMakeCommand.getTailArgumentsValues:()Ljava/util/List;
        //   119: dup            
        //   120: ifnonnull       164
        //   123: goto            130
        //   126: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/CMakeFile.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   129: athrow         
        //   130: new             Ljava/lang/IllegalStateException;
        //   133: dup            
        //   134: ldc             "@NotNull method %s.%s must not return null"
        //   136: ldc             2
        //   138: anewarray       Ljava/lang/Object;
        //   141: dup            
        //   142: ldc             0
        //   144: ldc             "com/jetbrains/cidr/cpp/cmake/psi/CMakeFile"
        //   146: aastore        
        //   147: dup            
        //   148: ldc             1
        //   150: ldc             "findFirstVariableValues"
        //   152: aastore        
        //   153: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   156: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   159: athrow         
        //   160: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/CMakeFile.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   163: athrow         
        //   164: areturn        
        //   165: goto            65
        //   168: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   171: dup            
        //   172: ifnonnull       209
        //   175: new             Ljava/lang/IllegalStateException;
        //   178: dup            
        //   179: ldc             "@NotNull method %s.%s must not return null"
        //   181: ldc             2
        //   183: anewarray       Ljava/lang/Object;
        //   186: dup            
        //   187: ldc             0
        //   189: ldc             "com/jetbrains/cidr/cpp/cmake/psi/CMakeFile"
        //   191: aastore        
        //   192: dup            
        //   193: ldc             1
        //   195: ldc             "findFirstVariableValues"
        //   197: aastore        
        //   198: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   201: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   204: athrow         
        //   205: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/CMakeFile.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   208: athrow         
        //   209: areturn        
        //    Signature:
        //  (Ljava/lang/String;)Ljava/util/List<Ljava/lang/String;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      11     14     18     Ljava/lang/IllegalArgumentException;
        //  4      48     48     52     Ljava/lang/IllegalArgumentException;
        //  92     106    109    113    Ljava/lang/IllegalArgumentException;
        //  97     123    126    130    Ljava/lang/IllegalArgumentException;
        //  113    160    160    164    Ljava/lang/IllegalArgumentException;
        //  168    205    205    209    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0113:
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
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
