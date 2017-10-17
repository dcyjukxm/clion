// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move;

import com.jetbrains.cidr.lang.psi.OCStructLike;
import com.jetbrains.cidr.lang.psi.OCInstanceVariablesList;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.psi.OCCppUsingStatement;
import com.jetbrains.cidr.lang.psi.OCCppNamespace;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.psi.OCSynthesizePropertiesList;
import com.jetbrains.cidr.lang.psi.OCProperty;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.psi.PsiElement;
import com.intellij.codeInsight.editorActions.moveUpDown.LineRange;
import com.jetbrains.cidr.lang.util.OCElementsRange;
import com.intellij.codeInsight.editorActions.moveUpDown.StatementUpDownMover;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.editor.Editor;

public class OCDeclarationUpDownMover extends OCUpDownMover
{
    private static final Class[] ourDeclarationClasses;
    private static final Class[] ourContainerClasses;
    
    @Override
    public boolean checkAvailable(@NotNull final Editor p0, @NotNull final StatementUpDownMover.MoveInfo p1, @NotNull final OCElementsRange p2, final boolean p3) {
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
        //    18: ldc             "editor"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/refactoring/move/OCDeclarationUpDownMover"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "checkAvailable"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCDeclarationUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "info"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/refactoring/move/OCDeclarationUpDownMover"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "checkAvailable"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCDeclarationUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //   106: ldc             "range"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/refactoring/move/OCDeclarationUpDownMover"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "checkAvailable"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCDeclarationUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   131: athrow         
        //   132: aload_3        
        //   133: invokevirtual   com/jetbrains/cidr/lang/util/OCElementsRange.getFirstElement:()Lcom/intellij/psi/PsiElement;
        //   136: astore          5
        //   138: aload_3        
        //   139: invokevirtual   com/jetbrains/cidr/lang/util/OCElementsRange.getLastElement:()Lcom/intellij/psi/PsiElement;
        //   142: astore          6
        //   144: aload           5
        //   146: ifnull          176
        //   149: aload           5
        //   151: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCDeclarationUpDownMover.a:(Lcom/intellij/psi/PsiElement;)Z
        //   154: ifne            176
        //   157: goto            164
        //   160: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCDeclarationUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   163: athrow         
        //   164: aload           5
        //   166: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   171: astore          5
        //   173: goto            144
        //   176: aload           6
        //   178: ifnull          208
        //   181: aload           6
        //   183: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCDeclarationUpDownMover.a:(Lcom/intellij/psi/PsiElement;)Z
        //   186: ifne            208
        //   189: goto            196
        //   192: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCDeclarationUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   195: athrow         
        //   196: aload           6
        //   198: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   203: astore          6
        //   205: goto            176
        //   208: aload           5
        //   210: ifnull          225
        //   213: aload           6
        //   215: ifnonnull       231
        //   218: goto            225
        //   221: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCDeclarationUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   224: athrow         
        //   225: iconst_0       
        //   226: ireturn        
        //   227: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCDeclarationUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   230: athrow         
        //   231: aload           5
        //   233: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   238: aload           6
        //   240: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   245: invokestatic    com/intellij/psi/util/PsiTreeUtil.findCommonParent:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   248: astore          7
        //   250: aload           7
        //   252: getstatic       com/jetbrains/cidr/lang/refactoring/move/OCDeclarationUpDownMover.ourContainerClasses:[Ljava/lang/Class;
        //   255: invokestatic    com/intellij/psi/util/PsiTreeUtil.getNonStrictParentOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   258: astore          7
        //   260: aload           7
        //   262: ifnonnull       271
        //   265: iconst_0       
        //   266: ireturn        
        //   267: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCDeclarationUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   270: athrow         
        //   271: aload           5
        //   273: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   278: aload           7
        //   280: if_acmpeq       295
        //   283: aload           5
        //   285: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   290: astore          5
        //   292: goto            271
        //   295: aload           6
        //   297: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   302: aload           7
        //   304: if_acmpeq       319
        //   307: aload           6
        //   309: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   314: astore          6
        //   316: goto            295
        //   319: aload_1        
        //   320: aload_2        
        //   321: getfield        com/intellij/codeInsight/editorActions/moveUpDown/StatementUpDownMover$MoveInfo.toMove:Lcom/intellij/codeInsight/editorActions/moveUpDown/LineRange;
        //   324: aload           5
        //   326: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCDeclarationUpDownMover.a:(Lcom/intellij/openapi/editor/Editor;Lcom/intellij/codeInsight/editorActions/moveUpDown/LineRange;Lcom/intellij/psi/PsiElement;)Z
        //   329: ifeq            352
        //   332: aload_1        
        //   333: aload_2        
        //   334: getfield        com/intellij/codeInsight/editorActions/moveUpDown/StatementUpDownMover$MoveInfo.toMove:Lcom/intellij/codeInsight/editorActions/moveUpDown/LineRange;
        //   337: aload           6
        //   339: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCDeclarationUpDownMover.a:(Lcom/intellij/openapi/editor/Editor;Lcom/intellij/codeInsight/editorActions/moveUpDown/LineRange;Lcom/intellij/psi/PsiElement;)Z
        //   342: ifne            358
        //   345: goto            352
        //   348: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCDeclarationUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   351: athrow         
        //   352: iconst_0       
        //   353: ireturn        
        //   354: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCDeclarationUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   357: athrow         
        //   358: aload_2        
        //   359: new             Lcom/intellij/codeInsight/editorActions/moveUpDown/LineRange;
        //   362: dup            
        //   363: aload           5
        //   365: aload           6
        //   367: invokespecial   com/intellij/codeInsight/editorActions/moveUpDown/LineRange.<init>:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)V
        //   370: putfield        com/intellij/codeInsight/editorActions/moveUpDown/StatementUpDownMover$MoveInfo.toMove:Lcom/intellij/codeInsight/editorActions/moveUpDown/LineRange;
        //   373: aload_2        
        //   374: aconst_null    
        //   375: putfield        com/intellij/codeInsight/editorActions/moveUpDown/StatementUpDownMover$MoveInfo.toMove2:Lcom/intellij/codeInsight/editorActions/moveUpDown/LineRange;
        //   378: iload           4
        //   380: ifeq            397
        //   383: aload           6
        //   385: invokeinterface com/intellij/psi/PsiElement.getNextSibling:()Lcom/intellij/psi/PsiElement;
        //   390: goto            404
        //   393: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCDeclarationUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   396: athrow         
        //   397: aload           5
        //   399: invokeinterface com/intellij/psi/PsiElement.getPrevSibling:()Lcom/intellij/psi/PsiElement;
        //   404: astore          8
        //   406: aload           8
        //   408: ifnull          502
        //   411: aload           8
        //   413: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCDeclarationUpDownMover.a:(Lcom/intellij/psi/PsiElement;)Z
        //   416: ifne            502
        //   419: goto            426
        //   422: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCDeclarationUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   425: athrow         
        //   426: aload           8
        //   428: instanceof      Lcom/intellij/psi/PsiComment;
        //   431: ifeq            464
        //   434: goto            441
        //   437: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCDeclarationUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   440: athrow         
        //   441: aload_1        
        //   442: aload           8
        //   444: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCDeclarationUpDownMover.elementStartLine:(Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiElement;)I
        //   447: aload_2        
        //   448: getfield        com/intellij/codeInsight/editorActions/moveUpDown/StatementUpDownMover$MoveInfo.toMove:Lcom/intellij/codeInsight/editorActions/moveUpDown/LineRange;
        //   451: getfield        com/intellij/codeInsight/editorActions/moveUpDown/LineRange.startLine:I
        //   454: if_icmpne       502
        //   457: goto            464
        //   460: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCDeclarationUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   463: athrow         
        //   464: iload           4
        //   466: ifeq            490
        //   469: goto            476
        //   472: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCDeclarationUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   475: athrow         
        //   476: aload           8
        //   478: invokeinterface com/intellij/psi/PsiElement.getNextSibling:()Lcom/intellij/psi/PsiElement;
        //   483: goto            497
        //   486: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCDeclarationUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   489: athrow         
        //   490: aload           8
        //   492: invokeinterface com/intellij/psi/PsiElement.getPrevSibling:()Lcom/intellij/psi/PsiElement;
        //   497: astore          8
        //   499: goto            406
        //   502: aload_2        
        //   503: aload           8
        //   505: ifnull          524
        //   508: new             Lcom/intellij/codeInsight/editorActions/moveUpDown/LineRange;
        //   511: dup            
        //   512: aload           8
        //   514: invokespecial   com/intellij/codeInsight/editorActions/moveUpDown/LineRange.<init>:(Lcom/intellij/psi/PsiElement;)V
        //   517: goto            525
        //   520: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCDeclarationUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   523: athrow         
        //   524: aconst_null    
        //   525: putfield        com/intellij/codeInsight/editorActions/moveUpDown/StatementUpDownMover$MoveInfo.toMove2:Lcom/intellij/codeInsight/editorActions/moveUpDown/LineRange;
        //   528: iconst_1       
        //   529: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     128    128    132    Ljava/lang/IllegalArgumentException;
        //  144    157    160    164    Ljava/lang/IllegalArgumentException;
        //  176    189    192    196    Ljava/lang/IllegalArgumentException;
        //  208    218    221    225    Ljava/lang/IllegalArgumentException;
        //  213    227    227    231    Ljava/lang/IllegalArgumentException;
        //  260    267    267    271    Ljava/lang/IllegalArgumentException;
        //  319    345    348    352    Ljava/lang/IllegalArgumentException;
        //  332    354    354    358    Ljava/lang/IllegalArgumentException;
        //  358    393    393    397    Ljava/lang/IllegalArgumentException;
        //  406    419    422    426    Ljava/lang/IllegalArgumentException;
        //  411    434    437    441    Ljava/lang/IllegalArgumentException;
        //  426    457    460    464    Ljava/lang/IllegalArgumentException;
        //  441    469    472    476    Ljava/lang/IllegalArgumentException;
        //  464    486    486    490    Ljava/lang/IllegalArgumentException;
        //  502    520    520    524    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0426:
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
    
    private static boolean a(@NotNull final Editor p0, final LineRange p1, final PsiElement p2) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/refactoring/move/OCDeclarationUpDownMover"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isAtTheStartOrEnd"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCDeclarationUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: aload_2        
        //    46: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCDeclarationUpDownMover.elementStartLine:(Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiElement;)I
        //    49: istore_3       
        //    50: aload_0        
        //    51: aload_2        
        //    52: invokeinterface com/intellij/psi/PsiElement.getTextOffset:()I
        //    57: invokeinterface com/intellij/openapi/editor/Editor.offsetToLogicalPosition:(I)Lcom/intellij/openapi/editor/LogicalPosition;
        //    62: getfield        com/intellij/openapi/editor/LogicalPosition.line:I
        //    65: istore          4
        //    67: aload_0        
        //    68: aload_2        
        //    69: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCDeclarationUpDownMover.elementEndLine:(Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiElement;)I
        //    72: iconst_1       
        //    73: iadd           
        //    74: istore          5
        //    76: aload_2        
        //    77: invokestatic    com/intellij/psi/util/PsiTreeUtil.firstChild:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //    80: astore          6
        //    82: aload           6
        //    84: ifnull          112
        //    87: aload           6
        //    89: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isWhitespace:(Lcom/intellij/psi/PsiElement;)Z
        //    92: ifeq            112
        //    95: goto            102
        //    98: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCDeclarationUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   101: athrow         
        //   102: aload           6
        //   104: invokestatic    com/intellij/psi/util/PsiTreeUtil.nextLeaf:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   107: astore          6
        //   109: goto            82
        //   112: aload           6
        //   114: ifnull          130
        //   117: aload_0        
        //   118: aload           6
        //   120: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCDeclarationUpDownMover.elementStartLine:(Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiElement;)I
        //   123: goto            131
        //   126: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCDeclarationUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   129: athrow         
        //   130: iconst_m1      
        //   131: istore          7
        //   133: iload_3        
        //   134: aload_1        
        //   135: getfield        com/intellij/codeInsight/editorActions/moveUpDown/LineRange.startLine:I
        //   138: if_icmpeq       252
        //   141: iload           4
        //   143: aload_1        
        //   144: getfield        com/intellij/codeInsight/editorActions/moveUpDown/LineRange.startLine:I
        //   147: if_icmpeq       252
        //   150: goto            157
        //   153: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCDeclarationUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   156: athrow         
        //   157: iload           7
        //   159: aload_1        
        //   160: getfield        com/intellij/codeInsight/editorActions/moveUpDown/LineRange.startLine:I
        //   163: if_icmpeq       252
        //   166: goto            173
        //   169: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCDeclarationUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   172: athrow         
        //   173: iload           5
        //   175: aload_1        
        //   176: getfield        com/intellij/codeInsight/editorActions/moveUpDown/LineRange.startLine:I
        //   179: if_icmpeq       252
        //   182: goto            189
        //   185: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCDeclarationUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   188: athrow         
        //   189: iload_3        
        //   190: aload_1        
        //   191: getfield        com/intellij/codeInsight/editorActions/moveUpDown/LineRange.endLine:I
        //   194: if_icmpeq       252
        //   197: goto            204
        //   200: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCDeclarationUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   203: athrow         
        //   204: iload           4
        //   206: aload_1        
        //   207: getfield        com/intellij/codeInsight/editorActions/moveUpDown/LineRange.endLine:I
        //   210: if_icmpeq       252
        //   213: goto            220
        //   216: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCDeclarationUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   219: athrow         
        //   220: iload           7
        //   222: aload_1        
        //   223: getfield        com/intellij/codeInsight/editorActions/moveUpDown/LineRange.endLine:I
        //   226: if_icmpeq       252
        //   229: goto            236
        //   232: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCDeclarationUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   235: athrow         
        //   236: iload           5
        //   238: aload_1        
        //   239: getfield        com/intellij/codeInsight/editorActions/moveUpDown/LineRange.endLine:I
        //   242: if_icmpne       260
        //   245: goto            252
        //   248: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCDeclarationUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   251: athrow         
        //   252: iconst_1       
        //   253: goto            261
        //   256: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCDeclarationUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   259: athrow         
        //   260: iconst_0       
        //   261: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  82     95     98     102    Ljava/lang/IllegalArgumentException;
        //  112    126    126    130    Ljava/lang/IllegalArgumentException;
        //  133    150    153    157    Ljava/lang/IllegalArgumentException;
        //  141    166    169    173    Ljava/lang/IllegalArgumentException;
        //  157    182    185    189    Ljava/lang/IllegalArgumentException;
        //  173    197    200    204    Ljava/lang/IllegalArgumentException;
        //  189    213    216    220    Ljava/lang/IllegalArgumentException;
        //  204    229    232    236    Ljava/lang/IllegalArgumentException;
        //  220    245    248    252    Ljava/lang/IllegalArgumentException;
        //  236    256    256    260    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0157:
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
    
    private static boolean a(final PsiElement psiElement) {
        try {
            if (OCElementUtil.isVisibilityKeyword(psiElement.getNode())) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (OCElementTypes.DIRECTIVES.contains(OCElementUtil.getElementType(psiElement))) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (ContainerUtil.find((Object[])OCDeclarationUpDownMover.ourDeclarationClasses, clazz -> clazz.isInstance(psiElement)) != null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        return false;
    }
    
    @Override
    protected boolean isMacroWhiteElement(final PsiElement p0, final boolean p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aload_1        
        //     2: iload_2        
        //     3: invokespecial   com/jetbrains/cidr/lang/refactoring/move/OCUpDownMover.isMacroWhiteElement:(Lcom/intellij/psi/PsiElement;Z)Z
        //     6: ifne            43
        //     9: aload_1        
        //    10: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //    13: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COMMA:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    16: if_acmpeq       43
        //    19: goto            26
        //    22: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCDeclarationUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    25: athrow         
        //    26: aload_1        
        //    27: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //    30: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    33: if_acmpne       51
        //    36: goto            43
        //    39: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCDeclarationUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    42: athrow         
        //    43: iconst_1       
        //    44: goto            52
        //    47: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCDeclarationUpDownMover.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    50: athrow         
        //    51: iconst_0       
        //    52: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      19     22     26     Ljava/lang/IllegalArgumentException;
        //  9      36     39     43     Ljava/lang/IllegalArgumentException;
        //  26     47     47     51     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0026:
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
        ourDeclarationClasses = new Class[] { OCDeclaration.class, OCMethod.class, OCProperty.class, OCSynthesizePropertiesList.class, OCClassDeclaration.class, OCCppNamespace.class, OCCppUsingStatement.class };
        ourContainerClasses = new Class[] { OCFile.class, OCClassDeclaration.class, OCInstanceVariablesList.class, OCStructLike.class, OCCppNamespace.class };
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
