// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCSymbolBase;
import com.jetbrains.cidr.lang.types.OCType;
import java.util.List;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.psi.OCFunctionDefinition;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.intellij.codeInsight.completion.InsertionContext;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.completion.InsertHandler;

public class FunctionDeclarationInsertHandler implements InsertHandler<LookupElement>
{
    @NotNull
    private final OCFunctionSymbol mySymbol;
    
    FunctionDeclarationInsertHandler(@NotNull final OCFunctionSymbol mySymbol) {
        if (mySymbol == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/editor/completion/FunctionDeclarationInsertHandler", "<init>"));
        }
        this.mySymbol = mySymbol;
    }
    
    public void handleInsert(final InsertionContext p0, final LookupElement p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getProject:()Lcom/intellij/openapi/project/Project;
        //     4: invokestatic    com/intellij/psi/PsiDocumentManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/PsiDocumentManager;
        //     7: invokevirtual   com/intellij/psi/PsiDocumentManager.commitAllDocuments:()V
        //    10: aload_1        
        //    11: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getDocument:()Lcom/intellij/openapi/editor/Document;
        //    14: astore_3       
        //    15: aload_0        
        //    16: aload_1        
        //    17: aload_2        
        //    18: invokespecial   com/jetbrains/cidr/lang/editor/completion/FunctionDeclarationInsertHandler.a:(Lcom/intellij/codeInsight/completion/InsertionContext;Lcom/intellij/codeInsight/lookup/LookupElement;)Ljava/lang/String;
        //    21: astore          4
        //    23: aload_3        
        //    24: aload_1        
        //    25: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getStartOffset:()I
        //    28: aload_1        
        //    29: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getTailOffset:()I
        //    32: aload           4
        //    34: invokeinterface com/intellij/openapi/editor/Document.replaceString:(IILjava/lang/CharSequence;)V
        //    39: aload_1        
        //    40: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getProject:()Lcom/intellij/openapi/project/Project;
        //    43: invokestatic    com/intellij/psi/PsiDocumentManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/PsiDocumentManager;
        //    46: astore          5
        //    48: aload           5
        //    50: aload_3        
        //    51: invokevirtual   com/intellij/psi/PsiDocumentManager.commitDocument:(Lcom/intellij/openapi/editor/Document;)V
        //    54: aload_1        
        //    55: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getEditor:()Lcom/intellij/openapi/editor/Editor;
        //    58: invokeinterface com/intellij/openapi/editor/Editor.getCaretModel:()Lcom/intellij/openapi/editor/CaretModel;
        //    63: astore          6
        //    65: aload_2        
        //    66: invokevirtual   com/intellij/codeInsight/lookup/LookupElement.getLookupString:()Ljava/lang/String;
        //    69: astore          7
        //    71: aload           6
        //    73: invokeinterface com/intellij/openapi/editor/CaretModel.getOffset:()I
        //    78: aload           7
        //    80: invokevirtual   java/lang/String.length:()I
        //    83: isub           
        //    84: istore          8
        //    86: aload_1        
        //    87: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getFile:()Lcom/intellij/psi/PsiFile;
        //    90: iload           8
        //    92: invokeinterface com/intellij/psi/PsiFile.findElementAt:(I)Lcom/intellij/psi/PsiElement;
        //    97: ldc             Lcom/jetbrains/cidr/lang/psi/OCDeclaration;.class
        //    99: iconst_0       
        //   100: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Z)Lcom/intellij/psi/PsiElement;
        //   103: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //   106: astore          9
        //   108: iconst_0       
        //   109: istore          10
        //   111: aload           9
        //   113: ifnull          312
        //   116: aload           9
        //   118: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getTypeElement:()Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //   123: astore          11
        //   125: aload           11
        //   127: ifnull          205
        //   130: aload           11
        //   132: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isElementEmpty:(Lcom/intellij/psi/PsiElement;)Z
        //   135: ifne            205
        //   138: goto            145
        //   141: invokestatic    com/jetbrains/cidr/lang/editor/completion/FunctionDeclarationInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   144: athrow         
        //   145: aload           9
        //   147: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getDeclarators:()Ljava/util/List;
        //   152: invokeinterface java/util/List.size:()I
        //   157: iconst_1       
        //   158: if_icmpne       205
        //   161: goto            168
        //   164: invokestatic    com/jetbrains/cidr/lang/editor/completion/FunctionDeclarationInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   167: athrow         
        //   168: aload           9
        //   170: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getDeclarators:()Ljava/util/List;
        //   175: iconst_0       
        //   176: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   181: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   184: astore          12
        //   186: aload_0        
        //   187: getfield        com/jetbrains/cidr/lang/editor/completion/FunctionDeclarationInsertHandler.mySymbol:Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   190: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getName:()Ljava/lang/String;
        //   193: aload           12
        //   195: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getName:()Ljava/lang/String;
        //   200: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   203: istore          10
        //   205: iload           10
        //   207: ifne            312
        //   210: aload_0        
        //   211: getfield        com/jetbrains/cidr/lang/editor/completion/FunctionDeclarationInsertHandler.mySymbol:Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   214: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   217: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isConstructorOrDestructor:()Z
        //   220: ifne            312
        //   223: goto            230
        //   226: invokestatic    com/jetbrains/cidr/lang/editor/completion/FunctionDeclarationInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   229: athrow         
        //   230: aload           11
        //   232: ifnull          251
        //   235: goto            242
        //   238: invokestatic    com/jetbrains/cidr/lang/editor/completion/FunctionDeclarationInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   241: athrow         
        //   242: aload           11
        //   244: goto            253
        //   247: invokestatic    com/jetbrains/cidr/lang/editor/completion/FunctionDeclarationInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   250: athrow         
        //   251: aload           9
        //   253: astore          12
        //   255: aload_0        
        //   256: getfield        com/jetbrains/cidr/lang/editor/completion/FunctionDeclarationInsertHandler.mySymbol:Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   259: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getEffectiveType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   262: aload           9
        //   264: aload_0        
        //   265: getfield        com/jetbrains/cidr/lang/editor/completion/FunctionDeclarationInsertHandler.mySymbol:Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   268: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getReturnTypeTextWithModifiers:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;)Ljava/lang/String;
        //   271: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getBestNameInContext:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;)Ljava/lang/String;
        //   274: astore          13
        //   276: aload_3        
        //   277: aload           12
        //   279: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   284: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   287: new             Ljava/lang/StringBuilder;
        //   290: dup            
        //   291: invokespecial   java/lang/StringBuilder.<init>:()V
        //   294: aload           13
        //   296: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   299: ldc             " "
        //   301: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   304: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   307: invokeinterface com/intellij/openapi/editor/Document.insertString:(ILjava/lang/CharSequence;)V
        //   312: aload_1        
        //   313: invokestatic    com/jetbrains/cidr/lang/editor/completion/CallableInsertUtils.moveCaretToCallableBody:(Lcom/intellij/codeInsight/completion/InsertionContext;)V
        //   316: aload_1        
        //   317: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getCompletionChar:()C
        //   320: bipush          123
        //   322: if_icmpne       337
        //   325: aload_1        
        //   326: iconst_0       
        //   327: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.setAddCompletionChar:(Z)V
        //   330: goto            337
        //   333: invokestatic    com/jetbrains/cidr/lang/editor/completion/FunctionDeclarationInsertHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   336: athrow         
        //   337: aload_1        
        //   338: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getFile:()Lcom/intellij/psi/PsiFile;
        //   341: aload_1        
        //   342: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getStartOffset:()I
        //   345: aload_1        
        //   346: invokevirtual   com/intellij/codeInsight/completion/InsertionContext.getStartOffset:()I
        //   349: aload           4
        //   351: invokevirtual   java/lang/String.length:()I
        //   354: iadd           
        //   355: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.reformatTextIfNotInjected:(Lcom/intellij/psi/PsiFile;II)V
        //   358: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  125    138    141    145    Ljava/lang/IllegalArgumentException;
        //  130    161    164    168    Ljava/lang/IllegalArgumentException;
        //  205    223    226    230    Ljava/lang/IllegalArgumentException;
        //  210    235    238    242    Ljava/lang/IllegalArgumentException;
        //  230    247    247    251    Ljava/lang/IllegalArgumentException;
        //  312    330    333    337    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0230:
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
    
    private String a(final InsertionContext insertionContext, final LookupElement lookupElement) {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.mySymbol.getName());
        boolean b = true;
        if (CallableInsertUtils.isReplaceCompletion(insertionContext)) {
            final OCDeclaration ocDeclaration = (OCDeclaration)PsiTreeUtil.getParentOfType(insertionContext.getFile().findElementAt(insertionContext.getEditor().getCaretModel().getOffset() - lookupElement.getLookupString().length()), (Class)OCDeclaration.class, false);
            if (ocDeclaration instanceof OCFunctionDefinition) {
                insertionContext.setTailOffset(((OCFunctionDefinition)ocDeclaration).getBody().getTextRange().getStartOffset());
                b = false;
            }
            try {
                if (CallableInsertUtils.isReplaceCompletionBeforeParen(insertionContext)) {
                    return sb.toString();
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        sb.append('(');
        if (!this.mySymbol.getType().hasNoParameters()) {
            final List<OCDeclaratorSymbol> parameterSymbols = this.mySymbol.getParameterSymbols();
            for (int i = 0; i < parameterSymbols.size(); ++i) {
                final OCDeclaratorSymbol ocDeclaratorSymbol = parameterSymbols.get(i);
                final OCType type = ocDeclaratorSymbol.getType();
                try {
                    if (i > 0) {
                        sb.append(", ");
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                String name;
                if (!ocDeclaratorSymbol.isUnnamed()) {
                    name = ocDeclaratorSymbol.getName();
                }
                else {
                    name = "";
                }
                sb.append(type.getBestNameInContext(((OCSymbolBase<PsiElement>)ocDeclaratorSymbol).locateDefinition(), OCElementUtil.getTypeTextWithModifiers(ocDeclaratorSymbol))).append(" ").append(name);
            }
        }
        try {
            sb.append(')');
            if (this.mySymbol.getType().isConst()) {
                sb.append(" const ");
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (this.mySymbol.getType().isVolatile()) {
                sb.append(" volatile ");
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        try {
            if (b) {
                sb.append("{ }");
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        return sb.toString();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
