// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.rename;

import com.intellij.refactoring.rename.inplace.InplaceRefactoring;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nullable;
import com.intellij.refactoring.rename.inplace.VariableInplaceRenamer;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;
import com.intellij.refactoring.rename.inplace.VariableInplaceRenameHandler;

public class OCInplaceRenameHandler extends VariableInplaceRenameHandler
{
    @Override
    protected boolean isAvailable(final PsiElement p0, final Editor p1, final PsiFile p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_3        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //     4: ifne            13
        //     7: iconst_0       
        //     8: ireturn        
        //     9: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    12: athrow         
        //    13: aload_1        
        //    14: ifnull          62
        //    17: aload_1        
        //    18: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //    23: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    26: ifeq            56
        //    29: goto            36
        //    32: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    35: athrow         
        //    36: aload_3        
        //    37: invokeinterface com/intellij/psi/PsiFile.getManager:()Lcom/intellij/psi/PsiManager;
        //    42: aload_1        
        //    43: invokevirtual   com/intellij/psi/PsiManager.isInProject:(Lcom/intellij/psi/PsiElement;)Z
        //    46: ifne            62
        //    49: goto            56
        //    52: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    55: athrow         
        //    56: iconst_0       
        //    57: ireturn        
        //    58: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    61: athrow         
        //    62: aload_2        
        //    63: invokeinterface com/intellij/openapi/editor/Editor.getCaretModel:()Lcom/intellij/openapi/editor/CaretModel;
        //    68: invokeinterface com/intellij/openapi/editor/CaretModel.getOffset:()I
        //    73: istore          4
        //    75: aload_2        
        //    76: iload           4
        //    78: invokestatic    com/intellij/codeInsight/TargetElementUtil.findReference:(Lcom/intellij/openapi/editor/Editor;I)Lcom/intellij/psi/PsiReference;
        //    81: astore          5
        //    83: aload           5
        //    85: ifnull          126
        //    88: aload           5
        //    90: invokeinterface com/intellij/psi/PsiReference.getElement:()Lcom/intellij/psi/PsiElement;
        //    95: astore          6
        //    97: aload           6
        //    99: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //   102: ifne            120
        //   105: aload           6
        //   107: instanceof      Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //   110: ifeq            126
        //   113: goto            120
        //   116: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   119: athrow         
        //   120: iconst_1       
        //   121: ireturn        
        //   122: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   125: athrow         
        //   126: aload_1        
        //   127: ifnonnull       136
        //   130: iconst_0       
        //   131: ireturn        
        //   132: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   135: athrow         
        //   136: aload_1        
        //   137: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //   140: ifeq            210
        //   143: aload_1        
        //   144: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //   147: invokeinterface com/jetbrains/cidr/lang/psi/OCMethod.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   152: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   155: astore          6
        //   157: aload           6
        //   159: ifnull          176
        //   162: aload           6
        //   164: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getAssociatedSymbol:()Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   169: goto            177
        //   172: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   175: athrow         
        //   176: aconst_null    
        //   177: astore          6
        //   179: aload           6
        //   181: ifnull          207
        //   184: aload           6
        //   186: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getGeneratedFromProperty:()Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   191: ifnull          207
        //   194: goto            201
        //   197: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   200: athrow         
        //   201: iconst_0       
        //   202: ireturn        
        //   203: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   206: athrow         
        //   207: goto            348
        //   210: aload_1        
        //   211: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   214: ifeq            348
        //   217: aload_1        
        //   218: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   221: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   226: astore          6
        //   228: aload           6
        //   230: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   233: ifeq            309
        //   236: aload           5
        //   238: ifnull          348
        //   241: goto            248
        //   244: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   247: athrow         
        //   248: aload           5
        //   250: invokeinterface com/intellij/psi/PsiReference.getElement:()Lcom/intellij/psi/PsiElement;
        //   255: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //   258: ifeq            348
        //   261: goto            268
        //   264: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   267: athrow         
        //   268: aload           6
        //   270: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //   275: aload           5
        //   277: invokeinterface com/intellij/psi/PsiReference.getElement:()Lcom/intellij/psi/PsiElement;
        //   282: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //   285: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getMessageSelector:()Ljava/lang/String;
        //   290: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   293: ifne            348
        //   296: goto            303
        //   299: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   302: athrow         
        //   303: iconst_0       
        //   304: ireturn        
        //   305: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   308: athrow         
        //   309: aload           6
        //   311: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   314: ifeq            348
        //   317: aload           6
        //   319: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   322: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isCppOperator:()Z
        //   325: ifeq            348
        //   328: goto            335
        //   331: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   334: athrow         
        //   335: aload           6
        //   337: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   340: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isUDL:()Z
        //   343: ireturn        
        //   344: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   347: athrow         
        //   348: aload_1        
        //   349: instanceof      Lcom/intellij/psi/PsiNameIdentifierOwner;
        //   352: ifeq            400
        //   355: aload_1        
        //   356: checkcast       Lcom/intellij/psi/PsiNameIdentifierOwner;
        //   359: invokeinterface com/intellij/psi/PsiNameIdentifierOwner.getNameIdentifier:()Lcom/intellij/psi/PsiElement;
        //   364: astore          6
        //   366: aload           6
        //   368: ifnull          398
        //   371: aload_1        
        //   372: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   377: invokevirtual   com/intellij/openapi/util/TextRange.isEmpty:()Z
        //   380: ifne            398
        //   383: goto            390
        //   386: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   389: athrow         
        //   390: iconst_1       
        //   391: goto            399
        //   394: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   397: athrow         
        //   398: iconst_0       
        //   399: ireturn        
        //   400: iconst_0       
        //   401: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      9      9      13     Ljava/lang/IllegalArgumentException;
        //  13     29     32     36     Ljava/lang/IllegalArgumentException;
        //  17     49     52     56     Ljava/lang/IllegalArgumentException;
        //  36     58     58     62     Ljava/lang/IllegalArgumentException;
        //  97     113    116    120    Ljava/lang/IllegalArgumentException;
        //  105    122    122    126    Ljava/lang/IllegalArgumentException;
        //  126    132    132    136    Ljava/lang/IllegalArgumentException;
        //  157    172    172    176    Ljava/lang/IllegalArgumentException;
        //  179    194    197    201    Ljava/lang/IllegalArgumentException;
        //  184    203    203    207    Ljava/lang/IllegalArgumentException;
        //  228    241    244    248    Ljava/lang/IllegalArgumentException;
        //  236    261    264    268    Ljava/lang/IllegalArgumentException;
        //  248    296    299    303    Ljava/lang/IllegalArgumentException;
        //  268    305    305    309    Ljava/lang/IllegalArgumentException;
        //  309    328    331    335    Ljava/lang/IllegalArgumentException;
        //  317    344    344    348    Ljava/lang/IllegalArgumentException;
        //  366    383    386    390    Ljava/lang/IllegalArgumentException;
        //  371    394    394    398    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0036:
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
    
    public VariableInplaceRenamer createRenamer(@NotNull final PsiElement psiElement, final Editor editor) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "elementToRename", "com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler", "createRenamer"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return createRenamer(psiElement, editor, null, null);
    }
    
    @Nullable
    static VariableInplaceRenamer createRenamer(@NotNull final PsiElement p0, final Editor p1, @Nullable final String p2, @Nullable final String p3) {
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
        //    18: ldc             "elementToRename"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "createRenamer"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCategoryName;
        //    48: ifeq            97
        //    51: aload_0        
        //    52: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    57: checkcast       Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
        //    60: invokeinterface com/jetbrains/cidr/lang/psi/OCClassDeclaration.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //    65: astore          4
        //    67: aload           4
        //    69: ifnull          94
        //    72: new             Lcom/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer;
        //    75: dup            
        //    76: aload_0        
        //    77: checkcast       Lcom/intellij/psi/PsiNamedElement;
        //    80: aload           4
        //    82: aload_1        
        //    83: iconst_1       
        //    84: aload_2        
        //    85: aload_3        
        //    86: invokespecial   com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.<init>:(Lcom/intellij/psi/PsiNamedElement;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/openapi/editor/Editor;ZLjava/lang/String;Ljava/lang/String;)V
        //    89: areturn        
        //    90: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    93: athrow         
        //    94: goto            815
        //    97: aload_0        
        //    98: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getRawSymbolFromNamedElement:(Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   101: astore          4
        //   103: aload           4
        //   105: ifnonnull       114
        //   108: aconst_null    
        //   109: areturn        
        //   110: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   113: athrow         
        //   114: aload           4
        //   116: instanceof      Lcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol;
        //   119: ifeq            153
        //   122: aload_0        
        //   123: invokeinterface com/intellij/psi/PsiElement.getProject:()Lcom/intellij/openapi/project/Project;
        //   128: aload_1        
        //   129: ldc             "refactoring.ambiguous"
        //   131: iconst_0       
        //   132: anewarray       Ljava/lang/Object;
        //   135: invokestatic    com/jetbrains/cidr/lang/OCBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   138: ldc             "rename.title"
        //   140: invokestatic    com/intellij/refactoring/RefactoringBundle.message:(Ljava/lang/String;)Ljava/lang/String;
        //   143: aconst_null    
        //   144: invokestatic    com/intellij/refactoring/util/CommonRefactoringUtil.showErrorHint:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/editor/Editor;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   147: aconst_null    
        //   148: areturn        
        //   149: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   152: athrow         
        //   153: aload_0        
        //   154: invokeinterface com/intellij/psi/PsiElement.getProject:()Lcom/intellij/openapi/project/Project;
        //   159: invokestatic    com/intellij/psi/PsiDocumentManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/PsiDocumentManager;
        //   162: aload_1        
        //   163: invokeinterface com/intellij/openapi/editor/Editor.getDocument:()Lcom/intellij/openapi/editor/Document;
        //   168: invokevirtual   com/intellij/psi/PsiDocumentManager.getPsiFile:(Lcom/intellij/openapi/editor/Document;)Lcom/intellij/psi/PsiFile;
        //   171: astore          5
        //   173: aload           5
        //   175: ifnull          203
        //   178: aload           5
        //   180: aload_1        
        //   181: invokeinterface com/intellij/openapi/editor/Editor.getCaretModel:()Lcom/intellij/openapi/editor/CaretModel;
        //   186: invokeinterface com/intellij/openapi/editor/CaretModel.getOffset:()I
        //   191: invokeinterface com/intellij/psi/PsiFile.findElementAt:(I)Lcom/intellij/psi/PsiElement;
        //   196: goto            204
        //   199: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   202: athrow         
        //   203: aconst_null    
        //   204: astore          6
        //   206: aload           5
        //   208: ifnull          251
        //   211: aload           6
        //   213: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //   216: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IDENTIFIER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   219: if_acmpeq       251
        //   222: goto            229
        //   225: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   228: athrow         
        //   229: aload           5
        //   231: aload_1        
        //   232: invokeinterface com/intellij/openapi/editor/Editor.getCaretModel:()Lcom/intellij/openapi/editor/CaretModel;
        //   237: invokeinterface com/intellij/openapi/editor/CaretModel.getOffset:()I
        //   242: iconst_1       
        //   243: isub           
        //   244: invokeinterface com/intellij/psi/PsiFile.findElementAt:(I)Lcom/intellij/psi/PsiElement;
        //   249: astore          6
        //   251: aload_0        
        //   252: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //   255: ifeq            524
        //   258: aload_0        
        //   259: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //   262: astore          7
        //   264: aload           4
        //   266: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   269: ifeq            506
        //   272: aload           6
        //   274: ifnull          506
        //   277: goto            284
        //   280: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   283: athrow         
        //   284: aload           6
        //   286: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //   289: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IDENTIFIER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   292: if_acmpne       506
        //   295: goto            302
        //   298: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   301: athrow         
        //   302: aload           6
        //   304: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   309: astore          8
        //   311: aload           4
        //   313: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   316: astore          9
        //   318: iconst_m1      
        //   319: istore          10
        //   321: aload           8
        //   323: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMethodSelectorPart;
        //   326: ifeq            370
        //   329: aload           8
        //   331: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMethodSelectorPart;
        //   334: invokeinterface com/jetbrains/cidr/lang/psi/OCMethodSelectorPart.getSelectorIdentifier:()Lcom/intellij/psi/PsiElement;
        //   339: aload           6
        //   341: if_acmpne       370
        //   344: goto            351
        //   347: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   350: athrow         
        //   351: aload           7
        //   353: invokeinterface com/jetbrains/cidr/lang/psi/OCMethod.getSelectors:()Ljava/util/List;
        //   358: aload           6
        //   360: invokeinterface java/util/List.indexOf:(Ljava/lang/Object;)I
        //   365: istore          10
        //   367: goto            477
        //   370: aload           8
        //   372: instanceof      Lcom/jetbrains/cidr/lang/psi/OCArgumentSelector;
        //   375: ifeq            436
        //   378: aload           8
        //   380: checkcast       Lcom/jetbrains/cidr/lang/psi/OCArgumentSelector;
        //   383: invokeinterface com/jetbrains/cidr/lang/psi/OCArgumentSelector.getSelectorIdentifier:()Lcom/intellij/psi/PsiElement;
        //   388: aload           6
        //   390: if_acmpne       436
        //   393: goto            400
        //   396: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   399: athrow         
        //   400: aload           8
        //   402: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   407: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   412: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //   415: astore          11
        //   417: aload           11
        //   419: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getArgumentSelectors:()Ljava/util/List;
        //   424: aload           8
        //   426: invokeinterface java/util/List.indexOf:(Ljava/lang/Object;)I
        //   431: istore          10
        //   433: goto            477
        //   436: aload           8
        //   438: instanceof      Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //   441: ifeq            450
        //   444: iconst_0       
        //   445: istore          10
        //   447: goto            477
        //   450: aload           8
        //   452: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSelectorExpression;
        //   455: ifeq            477
        //   458: aload           8
        //   460: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSelectorExpression;
        //   463: invokeinterface com/jetbrains/cidr/lang/psi/OCSelectorExpression.getSelectorParts:()Ljava/util/List;
        //   468: aload           6
        //   470: invokeinterface java/util/List.indexOf:(Ljava/lang/Object;)I
        //   475: istore          10
        //   477: iload           10
        //   479: iconst_m1      
        //   480: if_icmpeq       506
        //   483: new             Lcom/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer;
        //   486: dup            
        //   487: aload           7
        //   489: aload           9
        //   491: aload           6
        //   493: iload           10
        //   495: aload_1        
        //   496: aload_2        
        //   497: aload_3        
        //   498: invokespecial   com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.<init>:(Lcom/jetbrains/cidr/lang/psi/OCMethod;Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;Lcom/intellij/psi/PsiElement;ILcom/intellij/openapi/editor/Editor;Ljava/lang/String;Ljava/lang/String;)V
        //   501: areturn        
        //   502: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   505: athrow         
        //   506: new             Lcom/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler$1;
        //   509: dup            
        //   510: aload_0        
        //   511: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //   514: aload           4
        //   516: aload_1        
        //   517: iconst_0       
        //   518: aload_2        
        //   519: aload_3        
        //   520: invokespecial   com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler$1.<init>:(Lcom/intellij/psi/PsiNamedElement;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/openapi/editor/Editor;ZLjava/lang/String;Ljava/lang/String;)V
        //   523: areturn        
        //   524: aload           4
        //   526: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //   529: ifeq            573
        //   532: aload_0        
        //   533: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //   536: ifeq            573
        //   539: goto            546
        //   542: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   545: athrow         
        //   546: new             Lcom/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer;
        //   549: dup            
        //   550: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolHolderVirtualPsiElement;
        //   553: dup            
        //   554: aload           4
        //   556: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolHolderVirtualPsiElement.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   559: aload           4
        //   561: aload_1        
        //   562: iconst_0       
        //   563: aload_2        
        //   564: aload_3        
        //   565: invokespecial   com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.<init>:(Lcom/intellij/psi/PsiNamedElement;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/openapi/editor/Editor;ZLjava/lang/String;Ljava/lang/String;)V
        //   568: areturn        
        //   569: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   572: athrow         
        //   573: aload           4
        //   575: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   578: ifeq            647
        //   581: aload           6
        //   583: ifnull          647
        //   586: goto            593
        //   589: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   592: athrow         
        //   593: aload           6
        //   595: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   600: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSelectorExpression;
        //   603: ifeq            647
        //   606: goto            613
        //   609: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   612: athrow         
        //   613: aload           6
        //   615: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   620: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSelectorExpression;
        //   623: invokeinterface com/jetbrains/cidr/lang/psi/OCSelectorExpression.getSelector:()Ljava/lang/String;
        //   628: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.isObjCSetter:(Ljava/lang/String;)Z
        //   631: ifeq            647
        //   634: goto            641
        //   637: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   640: athrow         
        //   641: aconst_null    
        //   642: areturn        
        //   643: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   646: athrow         
        //   647: aload_0        
        //   648: instanceof      Lcom/jetbrains/cidr/lang/psi/OCLocalizedString;
        //   651: ifeq            797
        //   654: aload_0        
        //   655: invokeinterface com/intellij/psi/PsiElement.getFirstChild:()Lcom/intellij/psi/PsiElement;
        //   660: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //   663: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.STRING_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   666: if_acmpne       698
        //   669: goto            676
        //   672: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   675: athrow         
        //   676: aload_0        
        //   677: checkcast       Lcom/jetbrains/cidr/lang/psi/OCLocalizedString;
        //   680: invokeinterface com/jetbrains/cidr/lang/psi/OCLocalizedString.getKey:()Ljava/lang/String;
        //   685: invokevirtual   java/lang/String.isEmpty:()Z
        //   688: ifeq            704
        //   691: goto            698
        //   694: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   697: athrow         
        //   698: aconst_null    
        //   699: areturn        
        //   700: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   703: athrow         
        //   704: aload           5
        //   706: ifnull          797
        //   709: aload_1        
        //   710: invokeinterface com/intellij/openapi/editor/Editor.getCaretModel:()Lcom/intellij/openapi/editor/CaretModel;
        //   715: invokeinterface com/intellij/openapi/editor/CaretModel.getOffset:()I
        //   720: istore          7
        //   722: aload           5
        //   724: iload           7
        //   726: invokeinterface com/intellij/psi/PsiFile.findElementAt:(I)Lcom/intellij/psi/PsiElement;
        //   731: astore          6
        //   733: aload           6
        //   735: ifnull          797
        //   738: aload           6
        //   740: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //   743: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.STRING_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   746: if_acmpne       797
        //   749: goto            756
        //   752: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   755: athrow         
        //   756: iload           7
        //   758: aload           6
        //   760: invokeinterface com/intellij/psi/PsiElement.getTextOffset:()I
        //   765: if_icmpne       797
        //   768: goto            775
        //   771: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   774: athrow         
        //   775: aload_1        
        //   776: invokeinterface com/intellij/openapi/editor/Editor.getCaretModel:()Lcom/intellij/openapi/editor/CaretModel;
        //   781: iload           7
        //   783: iconst_1       
        //   784: iadd           
        //   785: invokeinterface com/intellij/openapi/editor/CaretModel.moveToOffset:(I)V
        //   790: goto            797
        //   793: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   796: athrow         
        //   797: new             Lcom/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer;
        //   800: dup            
        //   801: aload_0        
        //   802: checkcast       Lcom/intellij/psi/PsiNamedElement;
        //   805: aload           4
        //   807: aload_1        
        //   808: iconst_0       
        //   809: aload_2        
        //   810: aload_3        
        //   811: invokespecial   com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.<init>:(Lcom/intellij/psi/PsiNamedElement;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/openapi/editor/Editor;ZLjava/lang/String;Ljava/lang/String;)V
        //   814: areturn        
        //   815: aconst_null    
        //   816: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  67     90     90     94     Ljava/lang/IllegalArgumentException;
        //  103    110    110    114    Ljava/lang/IllegalArgumentException;
        //  114    149    149    153    Ljava/lang/IllegalArgumentException;
        //  173    199    199    203    Ljava/lang/IllegalArgumentException;
        //  206    222    225    229    Ljava/lang/IllegalArgumentException;
        //  264    277    280    284    Ljava/lang/IllegalArgumentException;
        //  272    295    298    302    Ljava/lang/IllegalArgumentException;
        //  321    344    347    351    Ljava/lang/IllegalArgumentException;
        //  370    393    396    400    Ljava/lang/IllegalArgumentException;
        //  477    502    502    506    Ljava/lang/IllegalArgumentException;
        //  524    539    542    546    Ljava/lang/IllegalArgumentException;
        //  532    569    569    573    Ljava/lang/IllegalArgumentException;
        //  573    586    589    593    Ljava/lang/IllegalArgumentException;
        //  581    606    609    613    Ljava/lang/IllegalArgumentException;
        //  593    634    637    641    Ljava/lang/IllegalArgumentException;
        //  613    643    643    647    Ljava/lang/IllegalArgumentException;
        //  647    669    672    676    Ljava/lang/IllegalArgumentException;
        //  654    691    694    698    Ljava/lang/IllegalArgumentException;
        //  676    700    700    704    Ljava/lang/IllegalArgumentException;
        //  733    749    752    756    Ljava/lang/IllegalArgumentException;
        //  738    768    771    775    Ljava/lang/IllegalArgumentException;
        //  756    790    793    797    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0593:
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
    
    @Override
    public void invoke(@NotNull final Project p0, final Editor p1, final PsiFile p2, final DataContext p3) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "invoke"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: invokestatic    com/intellij/psi/PsiDocumentManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/PsiDocumentManager;
        //    48: invokevirtual   com/intellij/psi/PsiDocumentManager.commitAllDocuments:()V
        //    51: aload           4
        //    53: ifnull          68
        //    56: aload           4
        //    58: invokestatic    com/intellij/refactoring/rename/PsiElementRenameHandler.getElement:(Lcom/intellij/openapi/actionSystem/DataContext;)Lcom/intellij/psi/PsiElement;
        //    61: goto            69
        //    64: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    67: athrow         
        //    68: aconst_null    
        //    69: astore          5
        //    71: aload           5
        //    73: ifnonnull       272
        //    76: aload_2        
        //    77: aload_2        
        //    78: invokeinterface com/intellij/openapi/editor/Editor.getCaretModel:()Lcom/intellij/openapi/editor/CaretModel;
        //    83: invokeinterface com/intellij/openapi/editor/CaretModel.getOffset:()I
        //    88: invokestatic    com/intellij/codeInsight/TargetElementUtil.findReference:(Lcom/intellij/openapi/editor/Editor;I)Lcom/intellij/psi/PsiReference;
        //    91: astore          6
        //    93: aload           6
        //    95: ifnull          129
        //    98: aload           6
        //   100: invokeinterface com/intellij/psi/PsiReference.resolve:()Lcom/intellij/psi/PsiElement;
        //   105: ifnonnull       129
        //   108: goto            115
        //   111: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   114: athrow         
        //   115: aload           6
        //   117: invokeinterface com/intellij/psi/PsiReference.getElement:()Lcom/intellij/psi/PsiElement;
        //   122: goto            130
        //   125: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   128: athrow         
        //   129: aconst_null    
        //   130: astore          5
        //   132: aload           5
        //   134: ifnull          272
        //   137: aload_2        
        //   138: invokeinterface com/intellij/openapi/editor/Editor.getScrollingModel:()Lcom/intellij/openapi/editor/ScrollingModel;
        //   143: getstatic       com/intellij/openapi/editor/ScrollType.MAKE_VISIBLE:Lcom/intellij/openapi/editor/ScrollType;
        //   146: invokeinterface com/intellij/openapi/editor/ScrollingModel.scrollToCaret:(Lcom/intellij/openapi/editor/ScrollType;)V
        //   151: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //   154: invokeinterface com/intellij/openapi/application/Application.isUnitTestMode:()Z
        //   159: ifne            188
        //   162: goto            169
        //   165: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   168: athrow         
        //   169: aload_0        
        //   170: aload           5
        //   172: aload_2        
        //   173: aload           4
        //   175: invokevirtual   com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler.checkAvailable:(Lcom/intellij/psi/PsiElement;Lcom/intellij/openapi/editor/Editor;Lcom/intellij/openapi/actionSystem/DataContext;)Z
        //   178: ifeq            271
        //   181: goto            188
        //   184: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   187: athrow         
        //   188: aload           4
        //   190: ifnull          218
        //   193: goto            200
        //   196: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   199: athrow         
        //   200: getstatic       com/intellij/refactoring/rename/PsiElementRenameHandler.DEFAULT_NAME:Lcom/intellij/openapi/actionSystem/DataKey;
        //   203: aload           4
        //   205: invokevirtual   com/intellij/openapi/actionSystem/DataKey.getData:(Lcom/intellij/openapi/actionSystem/DataContext;)Ljava/lang/Object;
        //   208: checkcast       Ljava/lang/String;
        //   211: goto            219
        //   214: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   217: athrow         
        //   218: aconst_null    
        //   219: astore          7
        //   221: new             Lcom/jetbrains/cidr/lang/refactoring/rename/OCUnresolvedReferenceRenamer;
        //   224: dup            
        //   225: aload           5
        //   227: instanceof      Lcom/intellij/psi/PsiNamedElement;
        //   230: ifeq            245
        //   233: aload           5
        //   235: checkcast       Lcom/intellij/psi/PsiNamedElement;
        //   238: goto            246
        //   241: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   244: athrow         
        //   245: aconst_null    
        //   246: aload_2        
        //   247: aload_3        
        //   248: aload_1        
        //   249: aload           6
        //   251: aload           7
        //   253: aload           6
        //   255: invokeinterface com/intellij/psi/PsiReference.getCanonicalText:()Ljava/lang/String;
        //   260: invokespecial   com/jetbrains/cidr/lang/refactoring/rename/OCUnresolvedReferenceRenamer.<init>:(Lcom/intellij/psi/PsiNamedElement;Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;Lcom/intellij/openapi/project/Project;Lcom/intellij/psi/PsiReference;Ljava/lang/String;Ljava/lang/String;)V
        //   263: astore          8
        //   265: aload           8
        //   267: invokevirtual   com/intellij/refactoring/rename/inplace/VariableInplaceRenamer.performInplaceRename:()Z
        //   270: pop            
        //   271: return         
        //   272: aload_0        
        //   273: aload_1        
        //   274: aload_2        
        //   275: aload_3        
        //   276: aload           4
        //   278: invokespecial   com/intellij/refactoring/rename/inplace/VariableInplaceRenameHandler.invoke:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;Lcom/intellij/openapi/actionSystem/DataContext;)V
        //   281: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     64     64     68     Ljava/lang/IllegalArgumentException;
        //  93     108    111    115    Ljava/lang/IllegalArgumentException;
        //  98     125    125    129    Ljava/lang/IllegalArgumentException;
        //  132    162    165    169    Ljava/lang/IllegalArgumentException;
        //  137    181    184    188    Ljava/lang/IllegalArgumentException;
        //  169    193    196    200    Ljava/lang/IllegalArgumentException;
        //  188    214    214    218    Ljava/lang/IllegalArgumentException;
        //  221    241    241    245    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0169:
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
    
    @Override
    public InplaceRefactoring doRename(@NotNull final PsiElement psiElement, final Editor editor, @Nullable final DataContext dataContext) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "elementToRename", "com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenameHandler", "doRename"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final VariableInplaceRenamer renamer = this.createRenamer(psiElement, editor);
        try {
            if (renamer != null) {
                renamer.performInplaceRename();
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return renamer;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
