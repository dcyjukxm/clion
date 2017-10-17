// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor;

import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.OCLog;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.editor.RawText;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiFile;
import com.intellij.codeInsight.editorActions.CopyPastePreProcessor;

public class OCStringLiteralCopyPasteProcessor implements CopyPastePreProcessor
{
    @Override
    public String preprocessOnCopy(final PsiFile psiFile, final int[] array, final int[] array2, final String s) {
        int n = 1;
        int n2 = 0;
        while (true) {
            Label_0050: {
                Label_0025: {
                    try {
                        if (n2 >= array.length) {
                            break;
                        }
                        final int n3 = n;
                        if (n3 != 0) {
                            break Label_0025;
                        }
                        break;
                    }
                    catch (IllegalStateException ex) {
                        throw a(ex);
                    }
                    try {
                        final int n3 = n;
                        if (n3 == 0) {
                            break;
                        }
                        if (a(psiFile, array[n2], array2[n2]) != null) {
                            break Label_0050;
                        }
                    }
                    catch (IllegalStateException ex2) {
                        throw a(ex2);
                    }
                }
                n = 0;
            }
            ++n2;
        }
        try {
            if (n != 0) {
                return StringUtil.unescapeStringCharacters(s);
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        return null;
    }
    
    @NotNull
    @Override
    public String preprocessOnPaste(final Project p0, final PsiFile p1, final Editor p2, final String p3, final RawText p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_3        
        //     1: invokeinterface com/intellij/openapi/editor/Editor.getDocument:()Lcom/intellij/openapi/editor/Document;
        //     6: astore          6
        //     8: aload_1        
        //     9: invokestatic    com/intellij/psi/PsiDocumentManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/PsiDocumentManager;
        //    12: aload           6
        //    14: invokevirtual   com/intellij/psi/PsiDocumentManager.commitDocument:(Lcom/intellij/openapi/editor/Document;)V
        //    17: aload_3        
        //    18: invokeinterface com/intellij/openapi/editor/Editor.getSelectionModel:()Lcom/intellij/openapi/editor/SelectionModel;
        //    23: astore          7
        //    25: aload           7
        //    27: invokeinterface com/intellij/openapi/editor/SelectionModel.getSelectionStart:()I
        //    32: istore          8
        //    34: aload           7
        //    36: invokeinterface com/intellij/openapi/editor/SelectionModel.getSelectionEnd:()I
        //    41: istore          9
        //    43: aload_2        
        //    44: iload           8
        //    46: iload           9
        //    48: invokestatic    com/jetbrains/cidr/lang/editor/OCStringLiteralCopyPasteProcessor.a:(Lcom/intellij/psi/PsiFile;II)Lcom/intellij/psi/tree/IElementType;
        //    51: astore          10
        //    53: aload           10
        //    55: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.STRING_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    58: if_acmpne       239
        //    61: aload           5
        //    63: ifnull          139
        //    66: goto            73
        //    69: invokestatic    com/jetbrains/cidr/lang/editor/OCStringLiteralCopyPasteProcessor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    72: athrow         
        //    73: aload           5
        //    75: getfield        com/intellij/openapi/editor/RawText.rawText:Ljava/lang/String;
        //    78: ifnull          139
        //    81: goto            88
        //    84: invokestatic    com/jetbrains/cidr/lang/editor/OCStringLiteralCopyPasteProcessor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    87: athrow         
        //    88: aload           5
        //    90: getfield        com/intellij/openapi/editor/RawText.rawText:Ljava/lang/String;
        //    93: dup            
        //    94: ifnonnull       138
        //    97: goto            104
        //   100: invokestatic    com/jetbrains/cidr/lang/editor/OCStringLiteralCopyPasteProcessor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   103: athrow         
        //   104: new             Ljava/lang/IllegalStateException;
        //   107: dup            
        //   108: ldc             "@NotNull method %s.%s must not return null"
        //   110: ldc             2
        //   112: anewarray       Ljava/lang/Object;
        //   115: dup            
        //   116: ldc             0
        //   118: ldc             "com/jetbrains/cidr/lang/editor/OCStringLiteralCopyPasteProcessor"
        //   120: aastore        
        //   121: dup            
        //   122: ldc             1
        //   124: ldc             "preprocessOnPaste"
        //   126: aastore        
        //   127: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   130: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   133: athrow         
        //   134: invokestatic    com/jetbrains/cidr/lang/editor/OCStringLiteralCopyPasteProcessor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   137: athrow         
        //   138: areturn        
        //   139: new             Ljava/lang/StringBuilder;
        //   142: dup            
        //   143: aload           4
        //   145: invokevirtual   java/lang/String.length:()I
        //   148: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //   151: astore          11
        //   153: ldc             "\\n\"\n \""
        //   155: astore          12
        //   157: aload           4
        //   159: invokevirtual   java/lang/String.toCharArray:()[C
        //   162: iconst_0       
        //   163: iconst_1       
        //   164: invokestatic    com/intellij/openapi/util/text/LineTokenizer.tokenize:([CZZ)[Ljava/lang/String;
        //   167: astore          13
        //   169: iconst_0       
        //   170: istore          14
        //   172: iload           14
        //   174: aload           13
        //   176: arraylength    
        //   177: if_icmpge       229
        //   180: aload           13
        //   182: iload           14
        //   184: aaload         
        //   185: astore          15
        //   187: aload           11
        //   189: aload           15
        //   191: invokestatic    com/intellij/openapi/util/text/StringUtil.escapeStringCharacters:(Ljava/lang/String;)Ljava/lang/String;
        //   194: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   197: pop            
        //   198: iload           14
        //   200: aload           13
        //   202: arraylength    
        //   203: iconst_1       
        //   204: isub           
        //   205: if_icmpeq       223
        //   208: aload           11
        //   210: aload           12
        //   212: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   215: pop            
        //   216: goto            223
        //   219: invokestatic    com/jetbrains/cidr/lang/editor/OCStringLiteralCopyPasteProcessor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   222: athrow         
        //   223: iinc            14, 1
        //   226: goto            172
        //   229: aload           11
        //   231: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   234: astore          4
        //   236: goto            369
        //   239: aload           10
        //   241: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.CHARACTER_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   244: if_acmpne       369
        //   247: aload           5
        //   249: ifnull          325
        //   252: goto            259
        //   255: invokestatic    com/jetbrains/cidr/lang/editor/OCStringLiteralCopyPasteProcessor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   258: athrow         
        //   259: aload           5
        //   261: getfield        com/intellij/openapi/editor/RawText.rawText:Ljava/lang/String;
        //   264: ifnull          325
        //   267: goto            274
        //   270: invokestatic    com/jetbrains/cidr/lang/editor/OCStringLiteralCopyPasteProcessor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   273: athrow         
        //   274: aload           5
        //   276: getfield        com/intellij/openapi/editor/RawText.rawText:Ljava/lang/String;
        //   279: dup            
        //   280: ifnonnull       324
        //   283: goto            290
        //   286: invokestatic    com/jetbrains/cidr/lang/editor/OCStringLiteralCopyPasteProcessor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   289: athrow         
        //   290: new             Ljava/lang/IllegalStateException;
        //   293: dup            
        //   294: ldc             "@NotNull method %s.%s must not return null"
        //   296: ldc             2
        //   298: anewarray       Ljava/lang/Object;
        //   301: dup            
        //   302: ldc             0
        //   304: ldc             "com/jetbrains/cidr/lang/editor/OCStringLiteralCopyPasteProcessor"
        //   306: aastore        
        //   307: dup            
        //   308: ldc             1
        //   310: ldc             "preprocessOnPaste"
        //   312: aastore        
        //   313: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   316: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   319: athrow         
        //   320: invokestatic    com/jetbrains/cidr/lang/editor/OCStringLiteralCopyPasteProcessor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   323: athrow         
        //   324: areturn        
        //   325: aload           4
        //   327: invokestatic    com/jetbrains/cidr/lang/editor/OCStringLiteralCopyPasteProcessor.escapeCharCharacters:(Ljava/lang/String;)Ljava/lang/String;
        //   330: dup            
        //   331: ifnonnull       368
        //   334: new             Ljava/lang/IllegalStateException;
        //   337: dup            
        //   338: ldc             "@NotNull method %s.%s must not return null"
        //   340: ldc             2
        //   342: anewarray       Ljava/lang/Object;
        //   345: dup            
        //   346: ldc             0
        //   348: ldc             "com/jetbrains/cidr/lang/editor/OCStringLiteralCopyPasteProcessor"
        //   350: aastore        
        //   351: dup            
        //   352: ldc             1
        //   354: ldc             "preprocessOnPaste"
        //   356: aastore        
        //   357: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   360: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   363: athrow         
        //   364: invokestatic    com/jetbrains/cidr/lang/editor/OCStringLiteralCopyPasteProcessor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   367: athrow         
        //   368: areturn        
        //   369: aload           4
        //   371: dup            
        //   372: ifnonnull       409
        //   375: new             Ljava/lang/IllegalStateException;
        //   378: dup            
        //   379: ldc             "@NotNull method %s.%s must not return null"
        //   381: ldc             2
        //   383: anewarray       Ljava/lang/Object;
        //   386: dup            
        //   387: ldc             0
        //   389: ldc             "com/jetbrains/cidr/lang/editor/OCStringLiteralCopyPasteProcessor"
        //   391: aastore        
        //   392: dup            
        //   393: ldc             1
        //   395: ldc             "preprocessOnPaste"
        //   397: aastore        
        //   398: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   401: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   404: athrow         
        //   405: invokestatic    com/jetbrains/cidr/lang/editor/OCStringLiteralCopyPasteProcessor.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   408: athrow         
        //   409: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  53     66     69     73     Ljava/lang/IllegalStateException;
        //  61     81     84     88     Ljava/lang/IllegalStateException;
        //  73     97     100    104    Ljava/lang/IllegalStateException;
        //  88     134    134    138    Ljava/lang/IllegalStateException;
        //  187    216    219    223    Ljava/lang/IllegalStateException;
        //  239    252    255    259    Ljava/lang/IllegalStateException;
        //  247    267    270    274    Ljava/lang/IllegalStateException;
        //  259    283    286    290    Ljava/lang/IllegalStateException;
        //  274    320    320    324    Ljava/lang/IllegalStateException;
        //  325    364    364    368    Ljava/lang/IllegalStateException;
        //  369    405    405    409    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0073:
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
    private static IElementType a(final PsiFile psiFile, final int n, final int n2) {
        final PsiElement element = psiFile.findElementAt(n);
        try {
            if (element == null) {
                return null;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (element.getNode() == null) {
                OCLog.LOG.error("node is null for " + element + "\n\tin " + psiFile);
                return null;
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        final IElementType elementType = element.getNode().getElementType();
        Label_0103: {
            try {
                if (elementType == OCTokenTypes.STRING_LITERAL) {
                    break Label_0103;
                }
                final OCElementType ocElementType = (OCElementType)elementType;
                final OCElementType ocElementType2 = OCTokenTypes.CHARACTER_LITERAL;
                if (ocElementType != ocElementType2) {
                    break Label_0103;
                }
                break Label_0103;
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
            try {
                final OCElementType ocElementType = (OCElementType)elementType;
                final OCElementType ocElementType2 = OCTokenTypes.CHARACTER_LITERAL;
                if (ocElementType != ocElementType2) {
                    return null;
                }
            }
            catch (IllegalStateException ex4) {
                throw a(ex4);
            }
        }
        final TextRange textRange = element.getTextRange();
        Label_0142: {
            try {
                if (n <= textRange.getStartOffset()) {
                    break Label_0142;
                }
                final int n3 = n2;
                final TextRange textRange2 = textRange;
                final int n4 = textRange2.getEndOffset();
                if (n3 >= n4) {
                    break Label_0142;
                }
                return elementType;
            }
            catch (IllegalStateException ex5) {
                throw a(ex5);
            }
            try {
                final int n3 = n2;
                final TextRange textRange2 = textRange;
                final int n4 = textRange2.getEndOffset();
                if (n3 >= n4) {
                    return null;
                }
            }
            catch (IllegalStateException ex6) {
                throw a(ex6);
            }
        }
        return elementType;
    }
    
    @NotNull
    public static String escapeCharCharacters(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "s", "com/jetbrains/cidr/lang/editor/OCStringLiteralCopyPasteProcessor", "escapeCharCharacters"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final StringBuilder sb = new StringBuilder();
        String string;
        try {
            StringUtil.escapeStringCharacters(s.length(), s, "'", sb);
            string = sb.toString();
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/OCStringLiteralCopyPasteProcessor", "escapeCharCharacters"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return string;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
