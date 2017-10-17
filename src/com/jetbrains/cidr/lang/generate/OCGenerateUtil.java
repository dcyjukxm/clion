// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate;

import com.jetbrains.cidr.lang.OCLog;
import java.util.Set;
import java.util.Iterator;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import com.intellij.openapi.util.Condition;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.jetbrains.cidr.lang.quickfixes.OCImportSymbolFix;
import com.intellij.openapi.util.Segment;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.intellij.openapi.editor.RangeMarker;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.editor.Document;
import com.intellij.psi.PsiDocumentManager;
import java.util.Collection;
import com.intellij.util.containers.ContainerUtil;
import java.util.HashSet;
import com.intellij.openapi.project.Project;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.List;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.text.CharArrayUtil;
import com.intellij.util.ObjectUtils;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.editor.Editor;

public class OCGenerateUtil
{
    @NotNull
    public static PsiElement getElementAt(@Nullable final Editor editor, @NotNull final PsiFile psiFile) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/generate/OCGenerateUtil", "getElementAt"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        PsiElement psiElement;
        try {
            psiElement = (PsiElement)ObjectUtils.notNull((Object)findElementAt(editor, psiFile), (Object)psiFile);
            if (psiElement == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/OCGenerateUtil", "getElementAt"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return psiElement;
    }
    
    @Nullable
    public static PsiElement findElementAt(@Nullable final Editor editor, @NotNull final PsiFile psiFile) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/generate/OCGenerateUtil", "findElementAt"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (editor != null) {
                return psiFile.findElementAt(editor.getCaretModel().getOffset());
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return null;
    }
    
    public static boolean shouldInsertNewLineBefore(@NotNull final CharSequence p0, final int p1) {
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
        //    18: ldc             "sequence"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/generate/OCGenerateUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "shouldInsertNewLineBefore"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/generate/OCGenerateUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: iload_1        
        //    46: iconst_1       
        //    47: isub           
        //    48: ldc             " \t"
        //    50: invokestatic    com/intellij/util/text/CharArrayUtil.shiftBackward:(Ljava/lang/CharSequence;ILjava/lang/String;)I
        //    53: istore_2       
        //    54: iload_2        
        //    55: iflt            95
        //    58: iload_2        
        //    59: aload_0        
        //    60: invokeinterface java/lang/CharSequence.length:()I
        //    65: if_icmpge       95
        //    68: goto            75
        //    71: invokestatic    com/jetbrains/cidr/lang/generate/OCGenerateUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    74: athrow         
        //    75: aload_0        
        //    76: iload_2        
        //    77: invokeinterface java/lang/CharSequence.charAt:(I)C
        //    82: invokestatic    com/intellij/openapi/util/text/StringUtil.isLineBreak:(C)Z
        //    85: ifne            103
        //    88: goto            95
        //    91: invokestatic    com/jetbrains/cidr/lang/generate/OCGenerateUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    94: athrow         
        //    95: iconst_1       
        //    96: goto            104
        //    99: invokestatic    com/jetbrains/cidr/lang/generate/OCGenerateUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   102: athrow         
        //   103: iconst_0       
        //   104: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  54     68     71     75     Ljava/lang/IllegalArgumentException;
        //  58     88     91     95     Ljava/lang/IllegalArgumentException;
        //  75     99     99     103    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0075:
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
    
    public static boolean shouldInsertNewLineAfter(@NotNull final CharSequence charSequence, final int n) {
        try {
            if (charSequence == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sequence", "com/jetbrains/cidr/lang/generate/OCGenerateUtil", "shouldInsertNewLineAfter"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final int shiftForward = CharArrayUtil.shiftForward(charSequence, n, " \t");
        Label_0082: {
            try {
                if (shiftForward >= charSequence.length()) {
                    break Label_0082;
                }
                final CharSequence charSequence2 = charSequence;
                final int n2 = shiftForward;
                final char c = charSequence2.charAt(n2);
                final boolean b = StringUtil.isLineBreak(c);
                if (!b) {
                    break Label_0082;
                }
                return false;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final CharSequence charSequence2 = charSequence;
                final int n2 = shiftForward;
                final char c = charSequence2.charAt(n2);
                final boolean b = StringUtil.isLineBreak(c);
                if (!b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return false;
    }
    
    public static List<PsiFile> getAffectedFiles(@NotNull final List<Replacement> list) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "replacements", "com/jetbrains/cidr/lang/generate/OCGenerateUtil", "getAffectedFiles"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return list.stream().map(replacement -> replacement.position.file).distinct().collect((Collector<? super Object, ?, List<PsiFile>>)Collectors.toList());
    }
    
    public static void applyReplacements(@NotNull final Project project, @NotNull final List<Replacement> list, final boolean b) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/generate/OCGenerateUtil", "applyReplacements"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "replacements", "com/jetbrains/cidr/lang/generate/OCGenerateUtil", "applyReplacements"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final HashSet<Object> set = new HashSet<Object>();
        final List mapNotNull = ContainerUtil.mapNotNull((Collection)list, replacement -> {
            try {
                if (project == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/generate/OCGenerateUtil", "lambda$applyReplacements$1"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            final Document document = PsiDocumentManager.getInstance(project).getDocument(replacement.position.file);
            try {
                if (document == null) {
                    OCLog.LOG.warn("Can't get document for " + replacement.position.file);
                    return null;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            set.add(document);
            final RangeMarker rangeMarker = document.createRangeMarker(replacement.position.range);
            rangeMarker.setGreedyToRight(true);
            return Pair.create((Object)replacement, (Object)rangeMarker);
        });
        final Iterator<Document> iterator = set.iterator();
        while (iterator.hasNext()) {
            PsiDocumentManager.getInstance(project).doPostponedOperationsAndUnblockDocument((Document)iterator.next());
        }
        for (final Pair pair : mapNotNull) {
            final Replacement replacement = (Replacement)pair.first;
            final ReplacePosition position = replacement.position;
            final RangeMarker rangeMarker = (RangeMarker)pair.second;
            try {
                rangeMarker.getDocument().replaceString(rangeMarker.getStartOffset(), rangeMarker.getEndOffset(), (CharSequence)replacement.text);
                PsiDocumentManager.getInstance(project).commitDocument(rangeMarker.getDocument());
                OCChangeUtil.reformatTextIfNotInjected(position.file, rangeMarker.getStartOffset(), CharArrayUtil.shiftForward(rangeMarker.getDocument().getCharsSequence(), rangeMarker.getEndOffset(), " \t\n"));
                if (!b) {
                    continue;
                }
                OCImportSymbolFix.fixAllSymbolsRecursively((PsiElement)position.file, TextRange.create((Segment)rangeMarker));
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        boolean showCallableInEditorAndSelectBody = false;
        for (final Pair pair2 : mapNotNull) {
            if (!showCallableInEditorAndSelectBody) {
                showCallableInEditorAndSelectBody = OCCodeInsightUtil.showCallableInEditorAndSelectBody(((Replacement)pair2.first).position.file, (Segment)pair2.second, (Condition<OCBlockStatement>)(ocBlockStatement -> true));
            }
            ((RangeMarker)pair2.second).dispose();
        }
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    public static class ReplacePosition
    {
        @NotNull
        public final PsiFile file;
        @NotNull
        public final TextRange range;
        @NotNull
        public final PsiElement context;
        public final boolean addNewLineBefore;
        public final boolean addNewLineAfter;
        
        public ReplacePosition(@NotNull final PsiFile psiFile, @NotNull final TextRange textRange, @NotNull final PsiElement psiElement) {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/generate/OCGenerateUtil$ReplacePosition", "<init>"));
            }
            if (textRange == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "range", "com/jetbrains/cidr/lang/generate/OCGenerateUtil$ReplacePosition", "<init>"));
            }
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/generate/OCGenerateUtil$ReplacePosition", "<init>"));
            }
            this(psiFile, textRange, psiElement, true);
        }
        
        public ReplacePosition(@NotNull final PsiFile p0, @NotNull final TextRange p1, @NotNull final PsiElement p2, final boolean p3) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_1        
            //     1: ifnonnull       40
            //     4: new             Ljava/lang/IllegalArgumentException;
            //     7: dup            
            //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
            //    10: ldc             3
            //    12: anewarray       Ljava/lang/Object;
            //    15: dup            
            //    16: ldc             0
            //    18: ldc             "file"
            //    20: aastore        
            //    21: dup            
            //    22: ldc             1
            //    24: ldc             "com/jetbrains/cidr/lang/generate/OCGenerateUtil$ReplacePosition"
            //    26: aastore        
            //    27: dup            
            //    28: ldc             2
            //    30: ldc             "<init>"
            //    32: aastore        
            //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
            //    39: athrow         
            //    40: aload_2        
            //    41: ifnonnull       80
            //    44: new             Ljava/lang/IllegalArgumentException;
            //    47: dup            
            //    48: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
            //    50: ldc             3
            //    52: anewarray       Ljava/lang/Object;
            //    55: dup            
            //    56: ldc             0
            //    58: ldc             "range"
            //    60: aastore        
            //    61: dup            
            //    62: ldc             1
            //    64: ldc             "com/jetbrains/cidr/lang/generate/OCGenerateUtil$ReplacePosition"
            //    66: aastore        
            //    67: dup            
            //    68: ldc             2
            //    70: ldc             "<init>"
            //    72: aastore        
            //    73: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //    76: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
            //    79: athrow         
            //    80: aload_3        
            //    81: ifnonnull       120
            //    84: new             Ljava/lang/IllegalArgumentException;
            //    87: dup            
            //    88: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
            //    90: ldc             3
            //    92: anewarray       Ljava/lang/Object;
            //    95: dup            
            //    96: ldc             0
            //    98: ldc             "context"
            //   100: aastore        
            //   101: dup            
            //   102: ldc             1
            //   104: ldc             "com/jetbrains/cidr/lang/generate/OCGenerateUtil$ReplacePosition"
            //   106: aastore        
            //   107: dup            
            //   108: ldc             2
            //   110: ldc             "<init>"
            //   112: aastore        
            //   113: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //   116: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
            //   119: athrow         
            //   120: aload_0        
            //   121: invokespecial   java/lang/Object.<init>:()V
            //   124: aload_0        
            //   125: aload_1        
            //   126: putfield        com/jetbrains/cidr/lang/generate/OCGenerateUtil$ReplacePosition.file:Lcom/intellij/psi/PsiFile;
            //   129: aload_0        
            //   130: aload_2        
            //   131: putfield        com/jetbrains/cidr/lang/generate/OCGenerateUtil$ReplacePosition.range:Lcom/intellij/openapi/util/TextRange;
            //   134: aload_0        
            //   135: aload_3        
            //   136: putfield        com/jetbrains/cidr/lang/generate/OCGenerateUtil$ReplacePosition.context:Lcom/intellij/psi/PsiElement;
            //   139: aload_1        
            //   140: invokeinterface com/intellij/psi/PsiFile.getProject:()Lcom/intellij/openapi/project/Project;
            //   145: invokestatic    com/intellij/psi/PsiDocumentManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/PsiDocumentManager;
            //   148: aload_1        
            //   149: invokevirtual   com/intellij/psi/PsiDocumentManager.getDocument:(Lcom/intellij/psi/PsiFile;)Lcom/intellij/openapi/editor/Document;
            //   152: astore          5
            //   154: aload_0        
            //   155: iload           4
            //   157: ifeq            204
            //   160: aload           5
            //   162: ifnull          196
            //   165: goto            172
            //   168: invokestatic    com/jetbrains/cidr/lang/generate/OCGenerateUtil$ReplacePosition.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   171: athrow         
            //   172: aload           5
            //   174: invokeinterface com/intellij/openapi/editor/Document.getCharsSequence:()Ljava/lang/CharSequence;
            //   179: aload_2        
            //   180: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
            //   183: invokestatic    com/jetbrains/cidr/lang/generate/OCGenerateUtil.shouldInsertNewLineBefore:(Ljava/lang/CharSequence;I)Z
            //   186: ifeq            204
            //   189: goto            196
            //   192: invokestatic    com/jetbrains/cidr/lang/generate/OCGenerateUtil$ReplacePosition.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   195: athrow         
            //   196: iconst_1       
            //   197: goto            205
            //   200: invokestatic    com/jetbrains/cidr/lang/generate/OCGenerateUtil$ReplacePosition.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   203: athrow         
            //   204: iconst_0       
            //   205: putfield        com/jetbrains/cidr/lang/generate/OCGenerateUtil$ReplacePosition.addNewLineBefore:Z
            //   208: aload_0        
            //   209: aload           5
            //   211: ifnull          238
            //   214: aload           5
            //   216: invokeinterface com/intellij/openapi/editor/Document.getCharsSequence:()Ljava/lang/CharSequence;
            //   221: aload_2        
            //   222: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
            //   225: invokestatic    com/jetbrains/cidr/lang/generate/OCGenerateUtil.shouldInsertNewLineAfter:(Ljava/lang/CharSequence;I)Z
            //   228: ifeq            246
            //   231: goto            238
            //   234: invokestatic    com/jetbrains/cidr/lang/generate/OCGenerateUtil$ReplacePosition.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   237: athrow         
            //   238: iconst_1       
            //   239: goto            247
            //   242: invokestatic    com/jetbrains/cidr/lang/generate/OCGenerateUtil$ReplacePosition.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   245: athrow         
            //   246: iconst_0       
            //   247: putfield        com/jetbrains/cidr/lang/generate/OCGenerateUtil$ReplacePosition.addNewLineAfter:Z
            //   250: return         
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  154    165    168    172    Ljava/lang/IllegalArgumentException;
            //  160    189    192    196    Ljava/lang/IllegalArgumentException;
            //  172    200    200    204    Ljava/lang/IllegalArgumentException;
            //  205    231    234    238    Ljava/lang/IllegalArgumentException;
            //  214    242    242    246    Ljava/lang/IllegalArgumentException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0172:
            //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
            //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createConstructor(AstBuilder.java:692)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:529)
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
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    public static class Replacement
    {
        @NotNull
        public final ReplacePosition position;
        @NotNull
        public final String text;
        
        public Replacement(@NotNull final ReplacePosition position, @NotNull final String s) {
            if (position == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "position", "com/jetbrains/cidr/lang/generate/OCGenerateUtil$Replacement", "<init>"));
            }
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/lang/generate/OCGenerateUtil$Replacement", "<init>"));
            }
            this.position = position;
            final StringBuilder sb = new StringBuilder();
            String s2;
            if (position.addNewLineBefore) {
                s2 = "\n";
            }
            else {
                s2 = "";
            }
            StringBuilder append = null;
            String s3 = null;
            Label_0136: {
                try {
                    append = sb.append(s2).append(s);
                    if (position.addNewLineAfter) {
                        s3 = "\n";
                        break Label_0136;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                s3 = "";
            }
            this.text = append.append(s3).toString();
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
}
