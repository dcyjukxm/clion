// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon.clang.tidy;

import com.intellij.openapi.util.TextRange;
import com.intellij.util.DocumentUtil;
import com.intellij.openapi.editor.RangeMarker;
import java.util.Iterator;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nls;
import com.intellij.util.IncorrectOperationException;
import com.intellij.openapi.editor.Document;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.intention.IntentionAction;

public class ClangTidyIntentionAction implements IntentionAction
{
    @NotNull
    private final String myMessage;
    @NotNull
    private final String myCheckName;
    @NotNull
    private final List<Replacement> myReplacements;
    
    public ClangTidyIntentionAction(@NotNull final Document document, @NotNull final String myMessage, @NotNull final String myCheckName, @NotNull final List<ClangTidyReplacement> list) {
        if (document == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "document", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyIntentionAction", "<init>"));
        }
        if (myMessage == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "message", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyIntentionAction", "<init>"));
        }
        if (myCheckName == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "checkName", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyIntentionAction", "<init>"));
        }
        if (list == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "replacements", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyIntentionAction", "<init>"));
        }
        this.myMessage = myMessage;
        this.myCheckName = myCheckName;
        this.myReplacements = a(document, list);
    }
    
    @Nls
    @NotNull
    public String getText() {
        String myMessage;
        try {
            myMessage = this.myMessage;
            if (myMessage == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyIntentionAction", "getText"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return myMessage;
    }
    
    @Nls
    @NotNull
    public String getFamilyName() {
        String myCheckName;
        try {
            myCheckName = this.myCheckName;
            if (myCheckName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyIntentionAction", "getFamilyName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return myCheckName;
    }
    
    public boolean isAvailable(@NotNull final Project p0, final Editor p1, final PsiFile p2) {
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
        //    18: ldc             "project"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyIntentionAction"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isAvailable"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    43: athrow         
        //    44: aload_3        
        //    45: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    48: ifeq            107
        //    51: aload_0        
        //    52: getfield        com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyIntentionAction.myReplacements:Ljava/util/List;
        //    55: invokeinterface java/util/List.isEmpty:()Z
        //    60: ifne            107
        //    63: goto            70
        //    66: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    69: athrow         
        //    70: aload_0        
        //    71: getfield        com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyIntentionAction.myReplacements:Ljava/util/List;
        //    74: invokeinterface java/util/List.stream:()Ljava/util/stream/Stream;
        //    79: invokedynamic   test:()Ljava/util/function/Predicate;
        //    84: invokeinterface java/util/stream/Stream.allMatch:(Ljava/util/function/Predicate;)Z
        //    89: ifeq            107
        //    92: goto            99
        //    95: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    98: athrow         
        //    99: iconst_1       
        //   100: goto            108
        //   103: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   106: athrow         
        //   107: iconst_0       
        //   108: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  44     63     66     70     Lcom/intellij/util/IncorrectOperationException;
        //  51     92     95     99     Lcom/intellij/util/IncorrectOperationException;
        //  70     103    103    107    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0070:
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
    
    public void invoke(@NotNull final Project project, final Editor editor, final PsiFile psiFile) throws IncorrectOperationException {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyIntentionAction", "invoke"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (!this.isAvailable(project, editor, psiFile)) {
                return;
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        for (final Replacement replacement : this.myReplacements) {
            final RangeMarker access$000 = replacement.myRangeMarker;
            final int startOffset = access$000.getStartOffset();
            OCChangeUtil.changeText(project, psiFile, startOffset, access$000.getEndOffset() - startOffset, replacement.myReplacementText, true);
        }
    }
    
    public boolean startInWriteAction() {
        return true;
    }
    
    @NotNull
    private static List<Replacement> a(@NotNull final Document p0, @NotNull final List<ClangTidyReplacement> p1) {
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
        //    18: ldc             "document"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyIntentionAction"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "createReplacements"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
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
        //    62: ldc             "replacements"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyIntentionAction"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "createReplacements"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    87: athrow         
        //    88: aload_1        
        //    89: invokeinterface java/util/List.stream:()Ljava/util/stream/Stream;
        //    94: aload_0        
        //    95: invokedynamic   apply:(Lcom/intellij/openapi/editor/Document;)Ljava/util/function/Function;
        //   100: invokeinterface java/util/stream/Stream.map:(Ljava/util/function/Function;)Ljava/util/stream/Stream;
        //   105: invokestatic    java/util/stream/Collectors.toList:()Ljava/util/stream/Collector;
        //   108: invokeinterface java/util/stream/Stream.collect:(Ljava/util/stream/Collector;)Ljava/lang/Object;
        //   113: checkcast       Ljava/util/List;
        //   116: dup            
        //   117: ifnonnull       154
        //   120: new             Ljava/lang/IllegalStateException;
        //   123: dup            
        //   124: ldc             "@NotNull method %s.%s must not return null"
        //   126: ldc             2
        //   128: anewarray       Ljava/lang/Object;
        //   131: dup            
        //   132: ldc             0
        //   134: ldc             "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyIntentionAction"
        //   136: aastore        
        //   137: dup            
        //   138: ldc             1
        //   140: ldc             "createReplacements"
        //   142: aastore        
        //   143: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   146: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   149: athrow         
        //   150: invokestatic    com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   153: athrow         
        //   154: areturn        
        //    Signature:
        //  (Lcom/intellij/openapi/editor/Document;Ljava/util/List<Lcom/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyReplacement;>;)Ljava/util/List<Lcom/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyIntentionAction$Replacement;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  44     84     84     88     Lcom/intellij/util/IncorrectOperationException;
        //  88     150    150    154    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Could not infer any expression.
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:374)
        //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:96)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:344)
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
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
    
    static class Replacement
    {
        @NotNull
        private final RangeMarker myRangeMarker;
        @NotNull
        private final String myOldText;
        @NotNull
        private final String myReplacementText;
        
        public Replacement(@NotNull final RangeMarker myRangeMarker, @NotNull final String myOldText, @NotNull final String myReplacementText) {
            if (myRangeMarker == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "rangeMarker", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyIntentionAction$Replacement", "<init>"));
            }
            if (myOldText == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "oldText", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyIntentionAction$Replacement", "<init>"));
            }
            if (myReplacementText == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "replacementText", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyIntentionAction$Replacement", "<init>"));
            }
            this.myRangeMarker = myRangeMarker;
            this.myOldText = myOldText;
            this.myReplacementText = myReplacementText;
        }
        
        public boolean isValid() {
            try {
                if (!this.myRangeMarker.isValid()) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return this.myRangeMarker.getDocument().getText(new TextRange(this.myRangeMarker.getStartOffset(), this.myRangeMarker.getEndOffset())).equals(this.myOldText);
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
}
