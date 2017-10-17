// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.formatting;

import java.util.Iterator;
import com.jetbrains.cidr.cpp.cmake.settings.Case;
import com.intellij.util.PairProcessor;
import java.util.List;
import com.intellij.lang.injection.InjectedLanguageManager;
import com.intellij.injected.editor.DocumentWindow;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeElementTypes;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;
import com.jetbrains.cidr.cpp.cmake.settings.CMakeCodeStyleSettings;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.editor.Document;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeFile;
import com.intellij.openapi.util.TextRange;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.intellij.psi.impl.source.codeStyle.PreFormatProcessor;

public class CMakePreFormatProcessor implements PreFormatProcessor
{
    @NotNull
    @Override
    public TextRange process(@NotNull final ASTNode astNode, @NotNull final TextRange textRange) {
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor", "process"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (textRange == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "range", "com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor", "process"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final PsiElement psi = astNode.getPsi();
        PsiFile containingFile = null;
        Label_0129: {
            Label_0115: {
                try {
                    if (psi == null) {
                        break Label_0115;
                    }
                    final PsiElement psiElement = psi;
                    final boolean b = psiElement.isValid();
                    if (!b) {
                        break Label_0115;
                    }
                    break Label_0115;
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    final PsiElement psiElement = psi;
                    final boolean b = psiElement.isValid();
                    if (!b) {
                        containingFile = null;
                        break Label_0129;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            containingFile = psi.getContainingFile();
        }
        final PsiFile psiFile = containingFile;
        TextRange access$000 = null;
        Label_0186: {
            TextRange textRange2 = null;
            Label_0151: {
                try {
                    if (psiFile instanceof CMakeFile) {
                        break Label_0186;
                    }
                    textRange2 = textRange;
                    if (textRange2 == null) {
                        break Label_0151;
                    }
                    return textRange2;
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
                try {
                    textRange2 = textRange;
                    if (textRange2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor", "process"));
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
            }
            return textRange2;
            try {
                access$000 = new MyFormatter(psi).a(textRange);
                if (access$000 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor", "process"));
                }
            }
            catch (IllegalArgumentException ex7) {
                throw a(ex7);
            }
        }
        return access$000;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    private static class MyFormatter
    {
        @NotNull
        protected final Project myProject;
        @NotNull
        protected final PsiElement myElement;
        @NotNull
        protected final PsiDocumentManager myDocumentManager;
        @Nullable
        protected final Document myDocument;
        protected final CMakeCodeStyleSettings myCMakeCodeStyleSettings;
        
        public MyFormatter(@NotNull final PsiElement myElement) {
            if (myElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter", "<init>"));
            }
            this.myProject = myElement.getProject();
            this.myElement = myElement;
            this.myCMakeCodeStyleSettings = (CMakeCodeStyleSettings)CodeStyleSettingsManager.getSettings(this.myProject).getCustomSettings((Class)CMakeCodeStyleSettings.class);
            this.myDocumentManager = PsiDocumentManager.getInstance(this.myProject);
            this.myDocument = this.myDocumentManager.getDocument(myElement.getContainingFile());
        }
        
        @NotNull
        private TextRange a(@NotNull final TextRange p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_1        
            //     1: ifnonnull       44
            //     4: new             Ljava/lang/IllegalArgumentException;
            //     7: dup            
            //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
            //    10: ldc             3
            //    12: anewarray       Ljava/lang/Object;
            //    15: dup            
            //    16: ldc             0
            //    18: ldc             "range"
            //    20: aastore        
            //    21: dup            
            //    22: ldc             1
            //    24: ldc             "com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter"
            //    26: aastore        
            //    27: dup            
            //    28: ldc             2
            //    30: ldc             "process"
            //    32: aastore        
            //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
            //    39: athrow         
            //    40: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    43: athrow         
            //    44: aload_0        
            //    45: getfield        com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter.myDocument:Lcom/intellij/openapi/editor/Document;
            //    48: ifnull          82
            //    51: aload_0        
            //    52: getfield        com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter.myCMakeCodeStyleSettings:Lcom/jetbrains/cidr/cpp/cmake/settings/CMakeCodeStyleSettings;
            //    55: ifnull          82
            //    58: goto            65
            //    61: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    64: athrow         
            //    65: aload_0        
            //    66: getfield        com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter.myCMakeCodeStyleSettings:Lcom/jetbrains/cidr/cpp/cmake/settings/CMakeCodeStyleSettings;
            //    69: invokevirtual   com/jetbrains/cidr/cpp/cmake/settings/CMakeCodeStyleSettings.hasDoNotChangeFileSettings:()Z
            //    72: ifeq            129
            //    75: goto            82
            //    78: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    81: athrow         
            //    82: aload_1        
            //    83: dup            
            //    84: ifnonnull       128
            //    87: goto            94
            //    90: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    93: athrow         
            //    94: new             Ljava/lang/IllegalStateException;
            //    97: dup            
            //    98: ldc             "@NotNull method %s.%s must not return null"
            //   100: ldc             2
            //   102: anewarray       Ljava/lang/Object;
            //   105: dup            
            //   106: ldc             0
            //   108: ldc             "com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter"
            //   110: aastore        
            //   111: dup            
            //   112: ldc             1
            //   114: ldc             "process"
            //   116: aastore        
            //   117: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //   120: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
            //   123: athrow         
            //   124: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   127: athrow         
            //   128: areturn        
            //   129: invokestatic    com/intellij/util/containers/ContainerUtil.newArrayList:()Ljava/util/ArrayList;
            //   132: astore_2       
            //   133: aload_0        
            //   134: getfield        com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter.myElement:Lcom/intellij/psi/PsiElement;
            //   137: new             Lcom/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter$1;
            //   140: dup            
            //   141: aload_0        
            //   142: aload_1        
            //   143: aload_2        
            //   144: invokespecial   com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter$1.<init>:(Lcom/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter;Lcom/intellij/openapi/util/TextRange;Ljava/util/List;)V
            //   147: invokeinterface com/intellij/psi/PsiElement.accept:(Lcom/intellij/psi/PsiElementVisitor;)V
            //   152: aload_0        
            //   153: getfield        com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter.myDocument:Lcom/intellij/openapi/editor/Document;
            //   156: invokeinterface com/intellij/openapi/editor/Document.getCharsSequence:()Ljava/lang/CharSequence;
            //   161: astore_3       
            //   162: aload_0        
            //   163: getfield        com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter.myDocument:Lcom/intellij/openapi/editor/Document;
            //   166: iconst_1       
            //   167: aload_0        
            //   168: aload_2        
            //   169: aload_3        
            //   170: invokedynamic   run:(Lcom/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter;Ljava/util/List;Ljava/lang/CharSequence;)Ljava/lang/Runnable;
            //   175: invokestatic    com/intellij/util/DocumentUtil.executeInBulk:(Lcom/intellij/openapi/editor/Document;ZLjava/lang/Runnable;)V
            //   178: aload_0        
            //   179: getfield        com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter.myDocumentManager:Lcom/intellij/psi/PsiDocumentManager;
            //   182: aload_0        
            //   183: getfield        com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter.myDocument:Lcom/intellij/openapi/editor/Document;
            //   186: invokevirtual   com/intellij/psi/PsiDocumentManager.isUncommited:(Lcom/intellij/openapi/editor/Document;)Z
            //   189: ifeq            210
            //   192: aload_0        
            //   193: getfield        com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter.myDocumentManager:Lcom/intellij/psi/PsiDocumentManager;
            //   196: aload_0        
            //   197: getfield        com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter.myDocument:Lcom/intellij/openapi/editor/Document;
            //   200: invokevirtual   com/intellij/psi/PsiDocumentManager.commitDocument:(Lcom/intellij/openapi/editor/Document;)V
            //   203: goto            210
            //   206: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   209: athrow         
            //   210: aload_1        
            //   211: dup            
            //   212: ifnonnull       249
            //   215: new             Ljava/lang/IllegalStateException;
            //   218: dup            
            //   219: ldc             "@NotNull method %s.%s must not return null"
            //   221: ldc             2
            //   223: anewarray       Ljava/lang/Object;
            //   226: dup            
            //   227: ldc             0
            //   229: ldc             "com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter"
            //   231: aastore        
            //   232: dup            
            //   233: ldc             1
            //   235: ldc             "process"
            //   237: aastore        
            //   238: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //   241: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
            //   244: athrow         
            //   245: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   248: athrow         
            //   249: areturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  0      40     40     44     Ljava/lang/IllegalArgumentException;
            //  44     58     61     65     Ljava/lang/IllegalArgumentException;
            //  51     75     78     82     Ljava/lang/IllegalArgumentException;
            //  65     87     90     94     Ljava/lang/IllegalArgumentException;
            //  82     124    124    128    Ljava/lang/IllegalArgumentException;
            //  162    203    206    210    Ljava/lang/IllegalArgumentException;
            //  210    245    245    249    Ljava/lang/IllegalArgumentException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0065:
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
        
        private static boolean a(@NotNull final ASTNode p0, @NotNull final IElementType p1) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: ifnonnull       44
            //     4: new             Ljava/lang/IllegalArgumentException;
            //     7: dup            
            //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
            //    10: ldc             3
            //    12: anewarray       Ljava/lang/Object;
            //    15: dup            
            //    16: ldc             0
            //    18: ldc             "currentNode"
            //    20: aastore        
            //    21: dup            
            //    22: ldc             1
            //    24: ldc             "com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter"
            //    26: aastore        
            //    27: dup            
            //    28: ldc             2
            //    30: ldc             "isFunctionOrMacroName"
            //    32: aastore        
            //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
            //    39: athrow         
            //    40: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    43: athrow         
            //    44: aload_1        
            //    45: ifnonnull       88
            //    48: new             Ljava/lang/IllegalArgumentException;
            //    51: dup            
            //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
            //    54: ldc             3
            //    56: anewarray       Ljava/lang/Object;
            //    59: dup            
            //    60: ldc             0
            //    62: ldc             "currentNodeType"
            //    64: aastore        
            //    65: dup            
            //    66: ldc             1
            //    68: ldc             "com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter"
            //    70: aastore        
            //    71: dup            
            //    72: ldc             2
            //    74: ldc             "isFunctionOrMacroName"
            //    76: aastore        
            //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
            //    83: athrow         
            //    84: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    87: athrow         
            //    88: aload_1        
            //    89: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeElementTypes.C_MAKE_ARGUMENT:Lcom/intellij/psi/tree/IElementType;
            //    92: if_acmpeq       101
            //    95: iconst_0       
            //    96: ireturn        
            //    97: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   100: athrow         
            //   101: aload_0        
            //   102: invokeinterface com/intellij/lang/ASTNode.getTreeParent:()Lcom/intellij/lang/ASTNode;
            //   107: astore_2       
            //   108: aload_2        
            //   109: ifnull          153
            //   112: aload_2        
            //   113: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
            //   118: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeElementTypes.C_MAKE_COMMAND_ARGUMENTS:Lcom/intellij/psi/tree/IElementType;
            //   121: if_acmpne       153
            //   124: goto            131
            //   127: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   130: athrow         
            //   131: aload_2        
            //   132: invokeinterface com/intellij/lang/ASTNode.getFirstChildNode:()Lcom/intellij/lang/ASTNode;
            //   137: invokeinterface com/intellij/lang/ASTNode.getTreeNext:()Lcom/intellij/lang/ASTNode;
            //   142: aload_0        
            //   143: if_acmpeq       159
            //   146: goto            153
            //   149: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   152: athrow         
            //   153: iconst_0       
            //   154: ireturn        
            //   155: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   158: athrow         
            //   159: aload_2        
            //   160: invokeinterface com/intellij/lang/ASTNode.getTreeParent:()Lcom/intellij/lang/ASTNode;
            //   165: astore_3       
            //   166: aload_3        
            //   167: ifnull          192
            //   170: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeElementTypes.CMAKE_FUNCTION_AND_MACRO_NAME_HOLDERS:Lcom/intellij/psi/tree/TokenSet;
            //   173: aload_3        
            //   174: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
            //   179: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
            //   182: ifne            198
            //   185: goto            192
            //   188: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   191: athrow         
            //   192: iconst_0       
            //   193: ireturn        
            //   194: invokestatic    com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   197: athrow         
            //   198: iconst_1       
            //   199: ireturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  0      40     40     44     Ljava/lang/IllegalArgumentException;
            //  44     84     84     88     Ljava/lang/IllegalArgumentException;
            //  88     97     97     101    Ljava/lang/IllegalArgumentException;
            //  108    124    127    131    Ljava/lang/IllegalArgumentException;
            //  112    146    149    153    Ljava/lang/IllegalArgumentException;
            //  131    155    155    159    Ljava/lang/IllegalArgumentException;
            //  166    185    188    192    Ljava/lang/IllegalArgumentException;
            //  170    194    194    198    Ljava/lang/IllegalArgumentException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0131:
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
        
        private static boolean a(@NotNull final IElementType elementType) {
            try {
                if (elementType == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "currentNodeType", "com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter", "isKeyword"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (CMakeElementTypes.KEYWORDS.contains(elementType)) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            return false;
        }
        
        private boolean a(@NotNull final PsiElement psiElement) {
            try {
                if (psiElement == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter", "intersectWithEditable"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (!(this.myDocument instanceof DocumentWindow)) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                if (!InjectedLanguageManager.getInstance(this.myProject).intersectWithAllEditableFragments(psiElement.getContainingFile(), psiElement.getNode().getTextRange()).isEmpty()) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            return false;
        }
        
        private static void a(@NotNull final List<TextRange> list, @NotNull final CharSequence charSequence, @NotNull final PairProcessor<TextRange, String> pairProcessor, @Nullable final Case case1) {
            try {
                if (list == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "functionRanges", "com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter", "processNames"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (charSequence == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "charSequence", "com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter", "processNames"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                if (pairProcessor == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/cpp/cmake/formatting/CMakePreFormatProcessor$MyFormatter", "processNames"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                if (case1 == null) {
                    return;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            try {
                if (case1 == Case.DO_NOT_CHANGE) {
                    return;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
            for (final TextRange textRange : list) {
                pairProcessor.process((Object)textRange, (Object)case1.apply(charSequence.subSequence(textRange.getStartOffset(), textRange.getEndOffset()).toString()));
            }
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
}
