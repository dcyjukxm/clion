// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.smartEnter;

import com.jetbrains.cidr.lang.psi.OCCallable;
import com.jetbrains.cidr.lang.psi.OCStatementWithExpression;
import com.jetbrains.cidr.lang.psi.OCIfStatement;
import com.jetbrains.cidr.lang.psi.OCBlockOwner;
import com.intellij.openapi.editor.actionSystem.EditorActionManager;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiErrorElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.psi.OCCaseStatement;
import com.intellij.openapi.editor.ex.EditorEx;
import com.intellij.codeInsight.CodeInsightSettings;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.intellij.lang.SmartEnterProcessorWithFixers;

public class PlainEnterProcessor extends SmartEnterProcessorWithFixers.FixEnterProcessor
{
    @Override
    public boolean doEnter(@NotNull final PsiElement psiElement, @NotNull final PsiFile psiFile, @NotNull final Editor editor, final boolean b) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "atCaret", "com/jetbrains/cidr/lang/editor/smartEnter/PlainEnterProcessor", "doEnter"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/editor/smartEnter/PlainEnterProcessor", "doEnter"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/editor/smartEnter/PlainEnterProcessor", "doEnter"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        final boolean insert_BRACE_ON_ENTER = CodeInsightSettings.getInstance().INSERT_BRACE_ON_ENTER;
        try {
            CodeInsightSettings.getInstance().INSERT_BRACE_ON_ENTER = false;
            if (expandCodeBlock(editor, psiElement)) {
                return true;
            }
            a("EditorStartNewLine").execute(editor, ((EditorEx)editor).getDataContext());
            return true;
        }
        finally {
            CodeInsightSettings.getInstance().INSERT_BRACE_ON_ENTER = insert_BRACE_ON_ENTER;
        }
    }
    
    public static boolean expandCodeBlock(final Editor p0, final PsiElement p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokeinterface com/intellij/openapi/editor/Editor.getCaretModel:()Lcom/intellij/openapi/editor/CaretModel;
        //     6: invokeinterface com/intellij/openapi/editor/CaretModel.getOffset:()I
        //    11: istore_2       
        //    12: aload_1        
        //    13: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/PlainEnterProcessor.b:(Lcom/intellij/psi/PsiElement;)Z
        //    16: istore          4
        //    18: iload           4
        //    20: ifeq            40
        //    23: aconst_null    
        //    24: astore_3       
        //    25: aload_1        
        //    26: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/PlainEnterProcessor.a:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //    29: astore_1       
        //    30: aload_1        
        //    31: ifnonnull       46
        //    34: iconst_0       
        //    35: ireturn        
        //    36: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/PlainEnterProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    39: athrow         
        //    40: iload_2        
        //    41: aload_1        
        //    42: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/PlainEnterProcessor.a:(ILcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCBlockOwner;
        //    45: astore_3       
        //    46: aconst_null    
        //    47: astore          5
        //    49: iconst_0       
        //    50: istore          6
        //    52: aload_3        
        //    53: ifnull          217
        //    56: aload_3        
        //    57: invokeinterface com/jetbrains/cidr/lang/psi/OCBlockOwner.getOpeningBrace:()Lcom/intellij/psi/PsiElement;
        //    62: astore          5
        //    64: aload           5
        //    66: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.isRealLeafElement:(Lcom/intellij/psi/PsiElement;)Z
        //    69: istore          6
        //    71: aload_3        
        //    72: invokeinterface com/jetbrains/cidr/lang/psi/OCBlockOwner.getParent:()Lcom/intellij/psi/PsiElement;
        //    77: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDoWhileStatement;
        //    80: istore          7
        //    82: iload           6
        //    84: ifeq            99
        //    87: aload           5
        //    89: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.getRangeWithMacros:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/openapi/util/TextRange;
        //    92: goto            100
        //    95: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/PlainEnterProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    98: athrow         
        //    99: aconst_null    
        //   100: astore          8
        //   102: iload           6
        //   104: ifeq            158
        //   107: iload_2        
        //   108: aload           8
        //   110: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   113: if_icmple       135
        //   116: goto            123
        //   119: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/PlainEnterProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   122: athrow         
        //   123: iload           7
        //   125: ifeq            158
        //   128: goto            135
        //   131: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/PlainEnterProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   134: athrow         
        //   135: aload_0        
        //   136: invokeinterface com/intellij/openapi/editor/Editor.getCaretModel:()Lcom/intellij/openapi/editor/CaretModel;
        //   141: aload           8
        //   143: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   146: invokeinterface com/intellij/openapi/editor/CaretModel.moveToOffset:(I)V
        //   151: goto            217
        //   154: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/PlainEnterProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   157: athrow         
        //   158: aload_0        
        //   159: invokeinterface com/intellij/openapi/editor/Editor.getDocument:()Lcom/intellij/openapi/editor/Document;
        //   164: astore          9
        //   166: aload           9
        //   168: iload_2        
        //   169: invokeinterface com/intellij/openapi/editor/Document.getLineNumber:(I)I
        //   174: istore          10
        //   176: aload           9
        //   178: aload_3        
        //   179: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.getRangeWithMacros:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/openapi/util/TextRange;
        //   182: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   185: invokeinterface com/intellij/openapi/editor/Document.getLineNumber:(I)I
        //   190: istore          11
        //   192: iload           7
        //   194: ifne            217
        //   197: iload           10
        //   199: iload           11
        //   201: if_icmplt       217
        //   204: goto            211
        //   207: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/PlainEnterProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   210: athrow         
        //   211: iconst_0       
        //   212: ireturn        
        //   213: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/PlainEnterProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   216: athrow         
        //   217: iload           4
        //   219: ifne            270
        //   222: aload_3        
        //   223: ifnull          241
        //   226: goto            233
        //   229: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/PlainEnterProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   232: athrow         
        //   233: aload_3        
        //   234: goto            242
        //   237: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/PlainEnterProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   240: athrow         
        //   241: aload_1        
        //   242: invokestatic    com/intellij/psi/util/PsiTreeUtil.hasErrorElements:(Lcom/intellij/psi/PsiElement;)Z
        //   245: ifeq            270
        //   248: aload           5
        //   250: ifnonnull       268
        //   253: goto            260
        //   256: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/PlainEnterProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   259: athrow         
        //   260: iconst_1       
        //   261: goto            269
        //   264: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/PlainEnterProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   267: athrow         
        //   268: iconst_0       
        //   269: ireturn        
        //   270: aload_0        
        //   271: aload_3        
        //   272: aload_1        
        //   273: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/PlainEnterProcessor.a:(Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Z
        //   276: ifeq            285
        //   279: iconst_1       
        //   280: ireturn        
        //   281: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/PlainEnterProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   284: athrow         
        //   285: aload_3        
        //   286: ifnonnull       295
        //   289: iconst_0       
        //   290: ireturn        
        //   291: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/PlainEnterProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   294: athrow         
        //   295: iload           6
        //   297: ifeq            319
        //   300: aload_3        
        //   301: invokeinterface com/jetbrains/cidr/lang/psi/OCBlockOwner.getClosingBrace:()Lcom/intellij/psi/PsiElement;
        //   306: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.isRealLeafElement:(Lcom/intellij/psi/PsiElement;)Z
        //   309: ifne            325
        //   312: goto            319
        //   315: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/PlainEnterProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   318: athrow         
        //   319: iconst_1       
        //   320: ireturn        
        //   321: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/PlainEnterProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   324: athrow         
        //   325: ldc             "EditorStartNewLine"
        //   327: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/PlainEnterProcessor.a:(Ljava/lang/String;)Lcom/intellij/openapi/editor/actionSystem/EditorActionHandler;
        //   330: astore          7
        //   332: aload_3        
        //   333: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.getFirstBodyElement:(Lcom/jetbrains/cidr/lang/psi/OCBlockOwner;)Lcom/intellij/psi/PsiElement;
        //   336: astore          8
        //   338: aload           8
        //   340: ifnonnull       353
        //   343: ldc             "EditorEnter"
        //   345: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/PlainEnterProcessor.a:(Ljava/lang/String;)Lcom/intellij/openapi/editor/actionSystem/EditorActionHandler;
        //   348: astore          7
        //   350: goto            406
        //   353: aload_0        
        //   354: invokeinterface com/intellij/openapi/editor/Editor.getCaretModel:()Lcom/intellij/openapi/editor/CaretModel;
        //   359: aload           8
        //   361: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/OCFixer.getRangeWithMacros:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/openapi/util/TextRange;
        //   364: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   367: invokeinterface com/intellij/openapi/editor/CaretModel.moveToOffset:(I)V
        //   372: aload           8
        //   374: instanceof      Lcom/intellij/psi/PsiWhiteSpace;
        //   377: ifeq            399
        //   380: aload           8
        //   382: bipush          10
        //   384: invokeinterface com/intellij/psi/PsiElement.textContains:(C)Z
        //   389: ifne            406
        //   392: goto            399
        //   395: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/PlainEnterProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   398: athrow         
        //   399: ldc             "EditorEnter"
        //   401: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/PlainEnterProcessor.a:(Ljava/lang/String;)Lcom/intellij/openapi/editor/actionSystem/EditorActionHandler;
        //   404: astore          7
        //   406: aload           7
        //   408: aload_0        
        //   409: aload_0        
        //   410: checkcast       Lcom/intellij/openapi/editor/ex/EditorEx;
        //   413: invokeinterface com/intellij/openapi/editor/ex/EditorEx.getDataContext:()Lcom/intellij/openapi/actionSystem/DataContext;
        //   418: invokevirtual   com/intellij/openapi/editor/actionSystem/EditorActionHandler.execute:(Lcom/intellij/openapi/editor/Editor;Lcom/intellij/openapi/actionSystem/DataContext;)V
        //   421: iconst_1       
        //   422: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  30     36     36     40     Ljava/lang/IllegalArgumentException;
        //  82     95     95     99     Ljava/lang/IllegalArgumentException;
        //  102    116    119    123    Ljava/lang/IllegalArgumentException;
        //  107    128    131    135    Ljava/lang/IllegalArgumentException;
        //  123    154    154    158    Ljava/lang/IllegalArgumentException;
        //  192    204    207    211    Ljava/lang/IllegalArgumentException;
        //  197    213    213    217    Ljava/lang/IllegalArgumentException;
        //  217    226    229    233    Ljava/lang/IllegalArgumentException;
        //  222    237    237    241    Ljava/lang/IllegalArgumentException;
        //  242    253    256    260    Ljava/lang/IllegalArgumentException;
        //  248    264    264    268    Ljava/lang/IllegalArgumentException;
        //  270    281    281    285    Ljava/lang/IllegalArgumentException;
        //  285    291    291    295    Ljava/lang/IllegalArgumentException;
        //  295    312    315    319    Ljava/lang/IllegalArgumentException;
        //  300    321    321    325    Ljava/lang/IllegalArgumentException;
        //  353    392    395    399    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0123:
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
    
    @Nullable
    private static PsiElement a(@NotNull PsiElement psi) {
        try {
            if (psi == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "psiElement", "com/jetbrains/cidr/lang/editor/smartEnter/PlainEnterProcessor", "adjustNewLinePosition"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        if (psi instanceof OCCaseStatement) {
            final ASTNode childByType = ((PsiElement)psi).getNode().findChildByType((IElementType)OCTokenTypes.COLON);
            if (childByType == null) {
                return null;
            }
            psi = childByType.getPsi();
            final PsiErrorElement nextError = OCFixer.getNextError((PsiElement)psi);
            if (nextError != null) {
                psi = nextError;
            }
        }
        return (PsiElement)psi;
    }
    
    @Contract(value = "null -> false", pure = true)
    private static boolean b(final PsiElement psiElement) {
        return psiElement instanceof OCCaseStatement;
    }
    
    private static EditorActionHandler a(final String s) {
        return EditorActionManager.getInstance().getActionHandler(s);
    }
    
    @Nullable
    private static OCBlockOwner a(final int n, final PsiElement psiElement) {
        try {
            if (OCFixer.isCompleteBlockOwner(psiElement)) {
                return (OCBlockOwner)psiElement;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        Object o = null;
        Label_0145: {
            Label_0128: {
                Label_0089: {
                    try {
                        if (!(psiElement instanceof OCIfStatement) || ((OCIfStatement)psiElement).getRParenth() == null) {
                            break Label_0089;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw b(ex2);
                    }
                    o = ((OCIfStatement)psiElement).getThenBranch();
                    try {
                        if (o == null || n <= OCFixer.getRangeWithMacros((PsiElement)o).getEndOffset()) {
                            break Label_0145;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw b(ex3);
                    }
                    o = ((OCIfStatement)psiElement).getElseBranch();
                    break Label_0145;
                    try {
                        if (!(psiElement instanceof OCStatementWithExpression) || ((OCStatementWithExpression)psiElement).getRParenth() == null) {
                            break Label_0128;
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw b(ex4);
                    }
                }
                o = ((OCStatementWithExpression)psiElement).getBody();
                break Label_0145;
            }
            if (psiElement instanceof OCCallable) {
                o = ((OCCallable)psiElement).getBody();
            }
            try {
                if (o instanceof OCBlockOwner) {
                    return (OCBlockOwner)o;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw b(ex5);
            }
        }
        return null;
    }
    
    private static boolean a(@NotNull final Editor p0, @Nullable final PsiElement p1, @Nullable final PsiElement p2) {
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
        //    18: ldc             "editor"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/editor/smartEnter/PlainEnterProcessor"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "processExistingBlankLine"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/PlainEnterProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aconst_null    
        //    45: astore_3       
        //    46: aload_1        
        //    47: ifnonnull       84
        //    50: aload_2        
        //    51: ifnull          163
        //    54: goto            61
        //    57: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/PlainEnterProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    60: athrow         
        //    61: aload_2        
        //    62: invokestatic    com/intellij/psi/util/PsiTreeUtil.nextLeaf:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //    65: astore          4
        //    67: aload           4
        //    69: instanceof      Lcom/intellij/psi/PsiWhiteSpace;
        //    72: ifeq            81
        //    75: aload           4
        //    77: checkcast       Lcom/intellij/psi/PsiWhiteSpace;
        //    80: astore_3       
        //    81: goto            163
        //    84: aload_1        
        //    85: ldc             Lcom/intellij/psi/PsiWhiteSpace;.class
        //    87: invokestatic    com/intellij/psi/util/PsiTreeUtil.findChildOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //    90: checkcast       Lcom/intellij/psi/PsiWhiteSpace;
        //    93: astore_3       
        //    94: aload_3        
        //    95: ifnonnull       104
        //    98: iconst_0       
        //    99: ireturn        
        //   100: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/PlainEnterProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   103: athrow         
        //   104: aload_3        
        //   105: invokeinterface com/intellij/psi/PsiWhiteSpace.getPrevSibling:()Lcom/intellij/psi/PsiElement;
        //   110: astore          4
        //   112: aload           4
        //   114: ifnonnull       123
        //   117: iconst_0       
        //   118: ireturn        
        //   119: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/PlainEnterProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   122: athrow         
        //   123: aload           4
        //   125: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
        //   130: astore          5
        //   132: aload           5
        //   134: ifnull          157
        //   137: aload           5
        //   139: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   144: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   147: if_acmpeq       163
        //   150: goto            157
        //   153: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/PlainEnterProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   156: athrow         
        //   157: iconst_0       
        //   158: ireturn        
        //   159: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/PlainEnterProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   162: athrow         
        //   163: aload_3        
        //   164: ifnonnull       173
        //   167: iconst_0       
        //   168: ireturn        
        //   169: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/PlainEnterProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   172: athrow         
        //   173: aload_3        
        //   174: invokeinterface com/intellij/psi/PsiWhiteSpace.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   179: astore          4
        //   181: aload_0        
        //   182: invokeinterface com/intellij/openapi/editor/Editor.getDocument:()Lcom/intellij/openapi/editor/Document;
        //   187: astore          5
        //   189: aload           5
        //   191: invokeinterface com/intellij/openapi/editor/Document.getCharsSequence:()Ljava/lang/CharSequence;
        //   196: aload           4
        //   198: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   201: aload           4
        //   203: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   206: invokeinterface java/lang/CharSequence.subSequence:(II)Ljava/lang/CharSequence;
        //   211: astore          6
        //   213: aload           6
        //   215: invokestatic    com/intellij/openapi/util/text/StringUtil.countNewLines:(Ljava/lang/CharSequence;)I
        //   218: iconst_2       
        //   219: if_icmpge       228
        //   222: iconst_0       
        //   223: ireturn        
        //   224: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/PlainEnterProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   227: athrow         
        //   228: aload           6
        //   230: iconst_0       
        //   231: ldc             " \t"
        //   233: invokestatic    com/intellij/util/text/CharArrayUtil.shiftForward:(Ljava/lang/CharSequence;ILjava/lang/String;)I
        //   236: istore          7
        //   238: iload           7
        //   240: aload           6
        //   242: invokeinterface java/lang/CharSequence.length:()I
        //   247: iconst_1       
        //   248: isub           
        //   249: if_icmplt       322
        //   252: getstatic       com/jetbrains/cidr/lang/editor/smartEnter/PlainEnterProcessor.$assertionsDisabled:Z
        //   255: ifne            320
        //   258: goto            265
        //   261: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/PlainEnterProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   264: athrow         
        //   265: new             Ljava/lang/AssertionError;
        //   268: dup            
        //   269: ldc             "code block: %s, white space: %s"
        //   271: iconst_2       
        //   272: anewarray       Ljava/lang/Object;
        //   275: dup            
        //   276: iconst_0       
        //   277: aload_1        
        //   278: ifnonnull       297
        //   281: goto            288
        //   284: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/PlainEnterProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   287: athrow         
        //   288: ldc             "undefined"
        //   290: goto            303
        //   293: invokestatic    com/jetbrains/cidr/lang/editor/smartEnter/PlainEnterProcessor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   296: athrow         
        //   297: aload_1        
        //   298: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   303: aastore        
        //   304: dup            
        //   305: iconst_1       
        //   306: aload_3        
        //   307: invokeinterface com/intellij/psi/PsiWhiteSpace.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   312: aastore        
        //   313: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   316: invokespecial   java/lang/AssertionError.<init>:(Ljava/lang/Object;)V
        //   319: athrow         
        //   320: iconst_0       
        //   321: ireturn        
        //   322: aload_0        
        //   323: invokeinterface com/intellij/openapi/editor/Editor.getCaretModel:()Lcom/intellij/openapi/editor/CaretModel;
        //   328: iload           7
        //   330: iconst_1       
        //   331: iadd           
        //   332: aload           4
        //   334: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   337: iadd           
        //   338: invokeinterface com/intellij/openapi/editor/CaretModel.moveToOffset:(I)V
        //   343: invokestatic    com/intellij/openapi/editor/actionSystem/EditorActionManager.getInstance:()Lcom/intellij/openapi/editor/actionSystem/EditorActionManager;
        //   346: astore          8
        //   348: aload           8
        //   350: ldc             "EditorLineEnd"
        //   352: invokevirtual   com/intellij/openapi/editor/actionSystem/EditorActionManager.getActionHandler:(Ljava/lang/String;)Lcom/intellij/openapi/editor/actionSystem/EditorActionHandler;
        //   355: astore          9
        //   357: invokestatic    com/intellij/ide/DataManager.getInstance:()Lcom/intellij/ide/DataManager;
        //   360: aload_0        
        //   361: invokeinterface com/intellij/openapi/editor/Editor.getComponent:()Ljavax/swing/JComponent;
        //   366: invokevirtual   com/intellij/ide/DataManager.getDataContext:(Ljava/awt/Component;)Lcom/intellij/openapi/actionSystem/DataContext;
        //   369: astore          10
        //   371: aload           10
        //   373: ifnonnull       430
        //   376: aload           6
        //   378: iload           7
        //   380: ldc             "\n"
        //   382: invokestatic    com/intellij/util/text/CharArrayUtil.shiftForwardUntil:(Ljava/lang/CharSequence;ILjava/lang/String;)I
        //   385: istore          7
        //   387: iload           7
        //   389: aload           6
        //   391: invokeinterface java/lang/CharSequence.length:()I
        //   396: if_icmplt       408
        //   399: aload           6
        //   401: invokeinterface java/lang/CharSequence.length:()I
        //   406: istore          7
        //   408: aload_0        
        //   409: invokeinterface com/intellij/openapi/editor/Editor.getCaretModel:()Lcom/intellij/openapi/editor/CaretModel;
        //   414: iload           7
        //   416: aload           4
        //   418: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   421: iadd           
        //   422: invokeinterface com/intellij/openapi/editor/CaretModel.moveToOffset:(I)V
        //   427: goto            438
        //   430: aload           9
        //   432: aload_0        
        //   433: aload           10
        //   435: invokevirtual   com/intellij/openapi/editor/actionSystem/EditorActionHandler.execute:(Lcom/intellij/openapi/editor/Editor;Lcom/intellij/openapi/actionSystem/DataContext;)V
        //   438: iconst_1       
        //   439: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  46     54     57     61     Ljava/lang/IllegalArgumentException;
        //  94     100    100    104    Ljava/lang/IllegalArgumentException;
        //  112    119    119    123    Ljava/lang/IllegalArgumentException;
        //  132    150    153    157    Ljava/lang/IllegalArgumentException;
        //  137    159    159    163    Ljava/lang/IllegalArgumentException;
        //  163    169    169    173    Ljava/lang/IllegalArgumentException;
        //  213    224    224    228    Ljava/lang/IllegalArgumentException;
        //  238    258    261    265    Ljava/lang/IllegalArgumentException;
        //  252    281    284    288    Ljava/lang/IllegalArgumentException;
        //  265    293    293    297    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0265:
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
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!PlainEnterProcessor.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
