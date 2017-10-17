// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.formatting;

import java.util.Iterator;
import com.intellij.lang.ASTNode;
import com.intellij.psi.impl.source.tree.LeafElement;
import com.intellij.psi.impl.source.codeStyle.CodeEditUtil;
import com.intellij.psi.impl.source.SourceTreeToPsiMap;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.intellij.util.StringBuilderSpinAllocator;
import com.intellij.util.SmartList;
import com.jetbrains.cidr.lang.psi.OCEmptyStatement;
import com.intellij.psi.impl.source.tree.ForeignLeafPsiElement;
import com.intellij.psi.impl.source.tree.TreeUtil;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCDoWhileStatement;
import com.jetbrains.cidr.lang.psi.OCWhileStatement;
import com.jetbrains.cidr.lang.psi.OCForeachStatement;
import com.jetbrains.cidr.lang.psi.OCForStatement;
import com.jetbrains.cidr.lang.psi.OCStatement;
import com.jetbrains.cidr.lang.psi.OCIfStatement;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.openapi.progress.ProgressIndicatorProvider;
import com.intellij.psi.PsiElement;
import com.intellij.lang.Language;
import com.jetbrains.cidr.lang.OCLanguage;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.impl.source.codeStyle.PostFormatProcessorHelper;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;

public class OCPostFormatVisitor extends OCRecursiveVisitor
{
    private final CommonCodeStyleSettings mySettings;
    private final PostFormatProcessorHelper myHelper;
    private boolean myReformat;
    
    public OCPostFormatVisitor(final CodeStyleSettings settings) {
        this.mySettings = settings.getCommonSettings((Language)OCLanguage.getInstance());
        this.myHelper = new PostFormatProcessorHelper((CommonCodeStyleSettings)settings);
    }
    
    @Override
    public void visitElement(final PsiElement psiElement) {
        ProgressIndicatorProvider.checkCanceled();
        PsiElement nextSibling;
        for (PsiElement firstChild = psiElement.getFirstChild(); firstChild != null; firstChild = nextSibling) {
            nextSibling = firstChild.getNextSibling();
            try {
                if (this.myHelper.isElementPartlyInRange(firstChild)) {
                    firstChild.accept((PsiElementVisitor)this);
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
    }
    
    public PsiElement process(final PsiElement psiElement) {
        try {
            psiElement.accept((PsiElementVisitor)this);
            if (this.myReformat) {
                CodeStyleManager.getInstance(psiElement.getProject()).reformat(psiElement, true);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return psiElement;
    }
    
    public TextRange process(final PsiFile psiFile, TextRange resultTextRange) {
        this.myHelper.setResultTextRange(resultTextRange);
        psiFile.accept((PsiElementVisitor)this);
        resultTextRange = this.myHelper.getResultTextRange();
        try {
            if (this.myReformat) {
                CodeStyleManager.getInstance(psiFile.getProject()).reformatRange((PsiElement)psiFile.getContainingFile(), resultTextRange.getStartOffset(), resultTextRange.getEndOffset(), true);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return resultTextRange;
    }
    
    @Override
    public void visitIfStatement(final OCIfStatement ocIfStatement) {
        super.visitIfStatement(ocIfStatement);
        this.a(ocIfStatement, ocIfStatement.getThenBranch(), this.mySettings.IF_BRACE_FORCE);
        final OCStatement elseBranch = ocIfStatement.getElseBranch();
        Label_0054: {
            try {
                if (!this.mySettings.SPECIAL_ELSE_IF_TREATMENT) {
                    break Label_0054;
                }
                final OCStatement ocStatement = elseBranch;
                final boolean b = ocStatement instanceof OCIfStatement;
                if (!b) {
                    break Label_0054;
                }
                return;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCStatement ocStatement = elseBranch;
                final boolean b = ocStatement instanceof OCIfStatement;
                if (!b) {
                    this.a(ocIfStatement, elseBranch, this.mySettings.IF_BRACE_FORCE);
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
    }
    
    @Override
    public void visitForStatement(final OCForStatement ocForStatement) {
        super.visitForStatement(ocForStatement);
        this.a(ocForStatement, ocForStatement.getBody(), this.mySettings.FOR_BRACE_FORCE);
    }
    
    @Override
    public void visitForeachStatement(final OCForeachStatement ocForeachStatement) {
        super.visitForeachStatement(ocForeachStatement);
        this.a(ocForeachStatement, ocForeachStatement.getBody(), this.mySettings.FOR_BRACE_FORCE);
    }
    
    @Override
    public void visitWhileStatement(final OCWhileStatement ocWhileStatement) {
        super.visitWhileStatement(ocWhileStatement);
        this.a(ocWhileStatement, ocWhileStatement.getBody(), this.mySettings.WHILE_BRACE_FORCE);
    }
    
    @Override
    public void visitDoWhileStatement(final OCDoWhileStatement ocDoWhileStatement) {
        super.visitDoWhileStatement(ocDoWhileStatement);
        this.a(ocDoWhileStatement, ocDoWhileStatement.getBody(), this.mySettings.DOWHILE_BRACE_FORCE);
    }
    
    private void a(final OCStatement p0, final OCStatement p1, final int p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: ifnull          18
        //     4: aload_2        
        //     5: instanceof      Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //     8: ifeq            23
        //    11: goto            18
        //    14: invokestatic    com/jetbrains/cidr/lang/formatting/OCPostFormatVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    17: athrow         
        //    18: return         
        //    19: invokestatic    com/jetbrains/cidr/lang/formatting/OCPostFormatVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    22: athrow         
        //    23: aload_0        
        //    24: getfield        com/jetbrains/cidr/lang/formatting/OCPostFormatVisitor.myHelper:Lcom/intellij/psi/impl/source/codeStyle/PostFormatProcessorHelper;
        //    27: aload_2        
        //    28: invokevirtual   com/intellij/psi/impl/source/codeStyle/PostFormatProcessorHelper.isElementFullyInRange:(Lcom/intellij/psi/PsiElement;)Z
        //    31: ifne            39
        //    34: return         
        //    35: invokestatic    com/jetbrains/cidr/lang/formatting/OCPostFormatVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    38: athrow         
        //    39: iload_3        
        //    40: iconst_3       
        //    41: if_icmpeq       70
        //    44: iload_3        
        //    45: iconst_1       
        //    46: if_icmpne       83
        //    49: goto            56
        //    52: invokestatic    com/jetbrains/cidr/lang/formatting/OCPostFormatVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    55: athrow         
        //    56: aload_1        
        //    57: invokestatic    com/intellij/psi/impl/source/codeStyle/PostFormatProcessorHelper.isMultiline:(Lcom/intellij/psi/PsiElement;)Z
        //    60: ifeq            83
        //    63: goto            70
        //    66: invokestatic    com/jetbrains/cidr/lang/formatting/OCPostFormatVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    69: athrow         
        //    70: aload_0        
        //    71: aload_1        
        //    72: aload_2        
        //    73: invokespecial   com/jetbrains/cidr/lang/formatting/OCPostFormatVisitor.a:(Lcom/jetbrains/cidr/lang/psi/OCStatement;Lcom/intellij/psi/PsiElement;)V
        //    76: goto            83
        //    79: invokestatic    com/jetbrains/cidr/lang/formatting/OCPostFormatVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    82: athrow         
        //    83: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      11     14     18     Ljava/lang/IllegalArgumentException;
        //  4      19     19     23     Ljava/lang/IllegalArgumentException;
        //  23     35     35     39     Ljava/lang/IllegalArgumentException;
        //  39     49     52     56     Ljava/lang/IllegalArgumentException;
        //  44     63     66     70     Ljava/lang/IllegalArgumentException;
        //  56     76     79     83     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0056:
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
    
    private void a(@NotNull final OCStatement psiElement, @NotNull final PsiElement psiElement2) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parent", "com/jetbrains/cidr/lang/formatting/OCPostFormatVisitor", "replaceWithBlock"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (psiElement2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "statementToWrap", "com/jetbrains/cidr/lang/formatting/OCPostFormatVisitor", "replaceWithBlock"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final LeafElement firstLeaf = TreeUtil.findFirstLeaf(psiElement2.getNode());
        try {
            if (firstLeaf instanceof ForeignLeafPsiElement) {
                return;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        String text = null;
        Label_0132: {
            try {
                if (psiElement2 instanceof OCEmptyStatement) {
                    text = "";
                    break Label_0132;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            text = psiElement2.getText();
        }
        final String s = text;
        final SmartList list = new SmartList();
        final StringBuilder alloc = StringBuilderSpinAllocator.alloc();
        alloc.append('{');
        a(true, psiElement2, (SmartList<PsiElement>)list, alloc);
        alloc.append(s);
        a(false, psiElement2, (SmartList<PsiElement>)list, alloc);
        alloc.append("}");
        final String string = alloc.toString();
        StringBuilderSpinAllocator.dispose(alloc);
        final PsiElement context = psiElement2.getContext();
        try {
            if (context == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        final OCStatement statementFromText = OCElementFactory.statementFromText(string, context);
        try {
            if (statementFromText == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        OCElementFactory.initIndentFromContext(context, (PsiElement)statementFromText);
        final int textLength = psiElement.getTextLength();
        try {
            final ASTNode psiElementToTree = SourceTreeToPsiMap.psiElementToTree((PsiElement)psiElement);
            final Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                psiElementToTree.removeChild(SourceTreeToPsiMap.psiElementToTree(iterator.next()));
            }
            CodeEditUtil.replaceChild(psiElementToTree, SourceTreeToPsiMap.psiElementToTree(psiElement2), SourceTreeToPsiMap.psiElementToTree((PsiElement)statementFromText));
            this.myReformat = true;
        }
        finally {
            this.myHelper.updateResultRange(textLength, psiElement.getTextLength());
        }
    }
    
    private static void a(final boolean p0, @NotNull final PsiElement p1, @NotNull final SmartList<PsiElement> p2, @NotNull final StringBuilder p3) {
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
        //    18: ldc             "statement"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/formatting/OCPostFormatVisitor"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "collectSiblings"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/formatting/OCPostFormatVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_2        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "toRemove"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/formatting/OCPostFormatVisitor"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "collectSiblings"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/formatting/OCPostFormatVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_3        
        //    89: ifnonnull       132
        //    92: new             Ljava/lang/IllegalArgumentException;
        //    95: dup            
        //    96: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    98: ldc             3
        //   100: anewarray       Ljava/lang/Object;
        //   103: dup            
        //   104: ldc             0
        //   106: ldc             "builder"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/formatting/OCPostFormatVisitor"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "collectSiblings"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/formatting/OCPostFormatVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   131: athrow         
        //   132: new             Ljava/util/ArrayList;
        //   135: dup            
        //   136: invokespecial   java/util/ArrayList.<init>:()V
        //   139: astore          4
        //   141: iload_0        
        //   142: ifeq            158
        //   145: aload_1        
        //   146: invokeinterface com/intellij/psi/PsiElement.getPrevSibling:()Lcom/intellij/psi/PsiElement;
        //   151: goto            164
        //   154: invokestatic    com/jetbrains/cidr/lang/formatting/OCPostFormatVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   157: athrow         
        //   158: aload_1        
        //   159: invokeinterface com/intellij/psi/PsiElement.getNextSibling:()Lcom/intellij/psi/PsiElement;
        //   164: astore          5
        //   166: aload           5
        //   168: ifnull          239
        //   171: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET:Lcom/intellij/psi/tree/TokenSet;
        //   174: aload           5
        //   176: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //   179: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   182: ifeq            239
        //   185: goto            192
        //   188: invokestatic    com/jetbrains/cidr/lang/formatting/OCPostFormatVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   191: athrow         
        //   192: aload           4
        //   194: aload           5
        //   196: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   201: pop            
        //   202: iload_0        
        //   203: ifeq            227
        //   206: goto            213
        //   209: invokestatic    com/jetbrains/cidr/lang/formatting/OCPostFormatVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   212: athrow         
        //   213: aload           5
        //   215: invokeinterface com/intellij/psi/PsiElement.getPrevSibling:()Lcom/intellij/psi/PsiElement;
        //   220: goto            234
        //   223: invokestatic    com/jetbrains/cidr/lang/formatting/OCPostFormatVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   226: athrow         
        //   227: aload           5
        //   229: invokeinterface com/intellij/psi/PsiElement.getNextSibling:()Lcom/intellij/psi/PsiElement;
        //   234: astore          5
        //   236: goto            166
        //   239: new             Ljava/util/LinkedList;
        //   242: dup            
        //   243: invokespecial   java/util/LinkedList.<init>:()V
        //   246: astore          5
        //   248: aload           4
        //   250: invokeinterface java/util/List.size:()I
        //   255: iconst_1       
        //   256: if_icmpgt       270
        //   259: iload_0        
        //   260: ifeq            278
        //   263: goto            270
        //   266: invokestatic    com/jetbrains/cidr/lang/formatting/OCPostFormatVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   269: athrow         
        //   270: iconst_1       
        //   271: goto            279
        //   274: invokestatic    com/jetbrains/cidr/lang/formatting/OCPostFormatVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   277: athrow         
        //   278: iconst_0       
        //   279: istore          6
        //   281: aload           4
        //   283: invokestatic    com/intellij/util/containers/ContainerUtil.iterateBackward:(Ljava/util/List;)Ljava/lang/Iterable;
        //   286: invokeinterface java/lang/Iterable.iterator:()Ljava/util/Iterator;
        //   291: astore          7
        //   293: aload           7
        //   295: invokeinterface java/util/Iterator.hasNext:()Z
        //   300: ifeq            379
        //   303: aload           7
        //   305: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   310: checkcast       Lcom/intellij/psi/PsiElement;
        //   313: astore          8
        //   315: aload           8
        //   317: instanceof      Lcom/intellij/psi/PsiWhiteSpace;
        //   320: ifeq            335
        //   323: iload           6
        //   325: ifeq            376
        //   328: goto            335
        //   331: invokestatic    com/jetbrains/cidr/lang/formatting/OCPostFormatVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   334: athrow         
        //   335: aload           8
        //   337: invokeinterface com/intellij/psi/PsiElement.getText:()Ljava/lang/String;
        //   342: astore          9
        //   344: aload_2        
        //   345: aload           8
        //   347: invokevirtual   com/intellij/util/SmartList.add:(Ljava/lang/Object;)Z
        //   350: pop            
        //   351: iload_0        
        //   352: ifeq            369
        //   355: aload           5
        //   357: aload           9
        //   359: invokevirtual   java/util/LinkedList.addLast:(Ljava/lang/Object;)V
        //   362: goto            376
        //   365: invokestatic    com/jetbrains/cidr/lang/formatting/OCPostFormatVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   368: athrow         
        //   369: aload           5
        //   371: aload           9
        //   373: invokevirtual   java/util/LinkedList.addFirst:(Ljava/lang/Object;)V
        //   376: goto            293
        //   379: aload           5
        //   381: invokevirtual   java/util/LinkedList.iterator:()Ljava/util/Iterator;
        //   384: astore          7
        //   386: aload           7
        //   388: invokeinterface java/util/Iterator.hasNext:()Z
        //   393: ifeq            418
        //   396: aload           7
        //   398: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   403: checkcast       Ljava/lang/String;
        //   406: astore          8
        //   408: aload_3        
        //   409: aload           8
        //   411: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   414: pop            
        //   415: goto            386
        //   418: return         
        //    Signature:
        //  (ZLcom/intellij/psi/PsiElement;Lcom/intellij/util/SmartList<Lcom/intellij/psi/PsiElement;>;Ljava/lang/StringBuilder;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     128    128    132    Ljava/lang/IllegalArgumentException;
        //  141    154    154    158    Ljava/lang/IllegalArgumentException;
        //  166    185    188    192    Ljava/lang/IllegalArgumentException;
        //  171    206    209    213    Ljava/lang/IllegalArgumentException;
        //  192    223    223    227    Ljava/lang/IllegalArgumentException;
        //  248    263    266    270    Ljava/lang/IllegalArgumentException;
        //  259    274    274    278    Ljava/lang/IllegalArgumentException;
        //  315    328    331    335    Ljava/lang/IllegalArgumentException;
        //  344    365    365    369    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0192:
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
