// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.intentions;

import java.util.List;
import com.intellij.util.containers.ContainerUtil;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Iterator;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.daemon.OCPragmaSuppressionUtils;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.util.Pair;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.psi.OCPragma;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.IncorrectOperationException;
import com.intellij.codeInsight.intention.PsiElementBaseIntentionAction;

public class OCRemoveSuppressionIntentionAction extends PsiElementBaseIntentionAction
{
    @NotNull
    public String getFamilyName() {
        String s;
        try {
            s = "Remove suppression";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCRemoveSuppressionIntentionAction", "getFamilyName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return s;
    }
    
    @NotNull
    public String getText() {
        String familyName;
        try {
            familyName = this.getFamilyName();
            if (familyName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCRemoveSuppressionIntentionAction", "getText"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return familyName;
    }
    
    @Nullable
    private static OCPragma a(final PsiElement psiElement) {
        final OCPragma pragma = OCElementUtil.getPragmaAt(psiElement);
        Label_0025: {
            try {
                if (pragma == null) {
                    break Label_0025;
                }
                final OCPragma ocPragma = pragma;
                final Pair<OCPragma.Mode, String> pair = ocPragma.parsePragma();
                if (pair == null) {
                    break Label_0025;
                }
                return pragma;
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            try {
                final OCPragma ocPragma = pragma;
                final Pair<OCPragma.Mode, String> pair = ocPragma.parsePragma();
                if (pair == null) {
                    return null;
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
        }
        return pragma;
    }
    
    public boolean isAvailable(@NotNull final Project project, final Editor editor, @NotNull final PsiElement psiElement) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/intentions/OCRemoveSuppressionIntentionAction", "isAvailable"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/intentions/OCRemoveSuppressionIntentionAction", "isAvailable"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        Label_0111: {
            try {
                if (!psiElement.isValid()) {
                    return false;
                }
                final PsiElement psiElement2 = psiElement;
                final OCPragma ocPragma = a(psiElement2);
                if (ocPragma != null) {
                    break Label_0111;
                }
                return false;
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
            try {
                final PsiElement psiElement2 = psiElement;
                final OCPragma ocPragma = a(psiElement2);
                if (ocPragma != null) {
                    return true;
                }
            }
            catch (IncorrectOperationException ex4) {
                throw a(ex4);
            }
        }
        return false;
    }
    
    public void invoke(@NotNull final Project p0, final Editor p1, @NotNull final PsiElement p2) throws IncorrectOperationException {
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
        //    24: ldc             "com/jetbrains/cidr/lang/intentions/OCRemoveSuppressionIntentionAction"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "invoke"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/intentions/OCRemoveSuppressionIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    43: athrow         
        //    44: aload_3        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "element"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/intentions/OCRemoveSuppressionIntentionAction"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "invoke"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/intentions/OCRemoveSuppressionIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    87: athrow         
        //    88: aload_3        
        //    89: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //    94: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    97: astore          4
        //    99: aload_2        
        //   100: invokeinterface com/intellij/openapi/editor/Editor.getDocument:()Lcom/intellij/openapi/editor/Document;
        //   105: astore          5
        //   107: aload_3        
        //   108: invokestatic    com/jetbrains/cidr/lang/intentions/OCRemoveSuppressionIntentionAction.a:(Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCPragma;
        //   111: astore          6
        //   113: aload           6
        //   115: ifnonnull       123
        //   118: return         
        //   119: invokestatic    com/jetbrains/cidr/lang/intentions/OCRemoveSuppressionIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   122: athrow         
        //   123: aload           6
        //   125: invokeinterface com/jetbrains/cidr/lang/psi/OCPragma.parsePragma:()Lcom/intellij/openapi/util/Pair;
        //   130: astore          7
        //   132: aload           6
        //   134: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getRangeWithMacros:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/openapi/util/TextRange;
        //   137: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   140: istore          8
        //   142: aload           4
        //   144: iload           8
        //   146: invokestatic    com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils.findScope:(Lcom/jetbrains/cidr/lang/psi/OCFile;I)Lcom/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils$Scope;
        //   149: astore          9
        //   151: aload           7
        //   153: ifnull          255
        //   156: aload           7
        //   158: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //   161: getstatic       com/jetbrains/cidr/lang/psi/OCPragma$Mode.PUSH:Lcom/jetbrains/cidr/lang/psi/OCPragma$Mode;
        //   164: if_acmpeq       223
        //   167: goto            174
        //   170: invokestatic    com/jetbrains/cidr/lang/intentions/OCRemoveSuppressionIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   173: athrow         
        //   174: aload           7
        //   176: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //   179: getstatic       com/jetbrains/cidr/lang/psi/OCPragma$Mode.POP:Lcom/jetbrains/cidr/lang/psi/OCPragma$Mode;
        //   182: if_acmpeq       223
        //   185: goto            192
        //   188: invokestatic    com/jetbrains/cidr/lang/intentions/OCRemoveSuppressionIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   191: athrow         
        //   192: aload           9
        //   194: invokevirtual   com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils$Scope.isTopLevel:()Z
        //   197: ifne            237
        //   200: goto            207
        //   203: invokestatic    com/jetbrains/cidr/lang/intentions/OCRemoveSuppressionIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   206: athrow         
        //   207: aload           9
        //   209: invokevirtual   com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils$Scope.getOwnSettingsCnt:()I
        //   212: iconst_1       
        //   213: if_icmpgt       237
        //   216: goto            223
        //   219: invokestatic    com/jetbrains/cidr/lang/intentions/OCRemoveSuppressionIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   222: athrow         
        //   223: aload           5
        //   225: aload           9
        //   227: invokestatic    com/jetbrains/cidr/lang/intentions/OCRemoveSuppressionIntentionAction.a:(Lcom/intellij/openapi/editor/Document;Lcom/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils$Scope;)V
        //   230: goto            246
        //   233: invokestatic    com/jetbrains/cidr/lang/intentions/OCRemoveSuppressionIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   236: athrow         
        //   237: aload           5
        //   239: iload           8
        //   241: aload           9
        //   243: invokestatic    com/jetbrains/cidr/lang/intentions/OCRemoveSuppressionIntentionAction.a:(Lcom/intellij/openapi/editor/Document;ILcom/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils$Scope;)V
        //   246: aload_1        
        //   247: invokestatic    com/intellij/psi/PsiDocumentManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/PsiDocumentManager;
        //   250: aload           5
        //   252: invokevirtual   com/intellij/psi/PsiDocumentManager.commitDocument:(Lcom/intellij/openapi/editor/Document;)V
        //   255: return         
        //    Exceptions:
        //  throws com.intellij.util.IncorrectOperationException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  44     84     84     88     Lcom/intellij/util/IncorrectOperationException;
        //  113    119    119    123    Lcom/intellij/util/IncorrectOperationException;
        //  151    167    170    174    Lcom/intellij/util/IncorrectOperationException;
        //  156    185    188    192    Lcom/intellij/util/IncorrectOperationException;
        //  174    200    203    207    Lcom/intellij/util/IncorrectOperationException;
        //  192    216    219    223    Lcom/intellij/util/IncorrectOperationException;
        //  207    233    233    237    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0174:
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
    
    private static void a(final Document document, final int n, final OCPragmaSuppressionUtils.Scope scope) {
        for (final Pair pair : scope.getSettings().values()) {
            try {
                if (((TextRange)pair.first).getStartOffset() == n) {
                    document.deleteString(((TextRange)pair.first).getStartOffset(), ((TextRange)pair.first).getEndOffset());
                    return;
                }
                continue;
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
        }
    }
    
    private static void a(final Document document, final OCPragmaSuppressionUtils.Scope scope) {
        final ArrayList<TextRange> list = new ArrayList<TextRange>();
        final TextRange push = scope.getPush();
        final TextRange pop = scope.getPop();
        try {
            if (push != null) {
                list.add(push);
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (pop != null) {
                list.add(pop);
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        for (final Pair pair : scope.getSettings().values()) {
            Label_0124: {
                try {
                    if (push == null) {
                        break Label_0124;
                    }
                    final Pair pair2 = pair;
                    final Object o = pair2.first;
                    final TextRange textRange = (TextRange)o;
                    final int n = textRange.getStartOffset();
                    final TextRange textRange2 = push;
                    final int n2 = textRange2.getStartOffset();
                    if (n > n2) {
                        break Label_0124;
                    }
                    continue;
                }
                catch (IncorrectOperationException ex3) {
                    throw a(ex3);
                }
                try {
                    final Pair pair2 = pair;
                    final Object o = pair2.first;
                    final TextRange textRange = (TextRange)o;
                    final int n = textRange.getStartOffset();
                    final TextRange textRange2 = push;
                    final int n2 = textRange2.getStartOffset();
                    if (n <= n2) {
                        continue;
                    }
                    list.add((TextRange)pair.first);
                }
                catch (IncorrectOperationException ex4) {
                    throw a(ex4);
                }
            }
        }
        ContainerUtil.sort((List)list, (Comparator)Comparator.comparingInt(TextRange::getStartOffset));
        for (int i = list.size() - 1; i >= 0; --i) {
            final TextRange textRange3 = list.get(i);
            document.deleteString(textRange3.getStartOffset(), textRange3.getEndOffset());
        }
    }
    
    public boolean startInWriteAction() {
        return true;
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
