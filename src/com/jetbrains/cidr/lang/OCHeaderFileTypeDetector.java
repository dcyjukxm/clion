// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang;

import com.intellij.openapi.fileTypes.FileType;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.util.io.ByteSequence;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.fileTypes.FileTypeRegistry;

public class OCHeaderFileTypeDetector implements FileTypeRegistry.FileTypeDetector
{
    public FileType detect(@NotNull final VirtualFile p0, @NotNull final ByteSequence p1, @Nullable final CharSequence p2) {
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
        //    18: ldc             "file"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/OCHeaderFileTypeDetector"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "detect"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/OCHeaderFileTypeDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "firstBytes"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/OCHeaderFileTypeDetector"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "detect"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/OCHeaderFileTypeDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_3        
        //    89: ifnonnull       98
        //    92: aconst_null    
        //    93: areturn        
        //    94: invokestatic    com/jetbrains/cidr/lang/OCHeaderFileTypeDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    97: athrow         
        //    98: invokestatic    com/jetbrains/cidr/lang/OCFileTypeHelpers.getHelpers:()[Lcom/jetbrains/cidr/lang/OCFileTypeHelper;
        //   101: astore          4
        //   103: aload           4
        //   105: arraylength    
        //   106: istore          5
        //   108: iconst_0       
        //   109: istore          6
        //   111: iload           6
        //   113: iload           5
        //   115: if_icmpge       167
        //   118: aload           4
        //   120: iload           6
        //   122: aaload         
        //   123: astore          7
        //   125: aload           7
        //   127: aload_1        
        //   128: invokeinterface com/jetbrains/cidr/lang/OCFileTypeHelper.getFileType:(Lcom/intellij/openapi/vfs/VirtualFile;)Lcom/intellij/openapi/fileTypes/FileType;
        //   133: astore          8
        //   135: aload           8
        //   137: ifnonnull       146
        //   140: aconst_null    
        //   141: areturn        
        //   142: invokestatic    com/jetbrains/cidr/lang/OCHeaderFileTypeDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   145: athrow         
        //   146: aload           8
        //   148: getstatic       com/intellij/openapi/fileTypes/FileTypes.UNKNOWN:Lcom/intellij/openapi/fileTypes/FileType;
        //   151: if_acmpeq       161
        //   154: aload           8
        //   156: areturn        
        //   157: invokestatic    com/jetbrains/cidr/lang/OCHeaderFileTypeDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   160: athrow         
        //   161: iinc            6, 1
        //   164: goto            111
        //   167: invokestatic    com/jetbrains/cidr/lang/lexer/OCLexer.createRawLexerForPreprocessor:()Lcom/intellij/lexer/Lexer;
        //   170: astore          4
        //   172: aload           4
        //   174: aload_3        
        //   175: invokevirtual   com/intellij/lexer/Lexer.start:(Ljava/lang/CharSequence;)V
        //   178: iconst_1       
        //   179: istore          5
        //   181: iconst_0       
        //   182: istore          6
        //   184: iconst_0       
        //   185: istore          7
        //   187: aload           4
        //   189: invokevirtual   com/intellij/lexer/Lexer.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //   192: astore          8
        //   194: aload           8
        //   196: ifnonnull       206
        //   199: goto            395
        //   202: invokestatic    com/jetbrains/cidr/lang/OCHeaderFileTypeDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   205: athrow         
        //   206: aload           8
        //   208: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.UNKNOWN_DIRECTIVE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   211: if_acmpeq       229
        //   214: aload           8
        //   216: getstatic       com/intellij/psi/TokenType.BAD_CHARACTER:Lcom/intellij/psi/tree/IElementType;
        //   219: if_acmpne       255
        //   222: goto            229
        //   225: invokestatic    com/jetbrains/cidr/lang/OCHeaderFileTypeDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   228: athrow         
        //   229: aload           4
        //   231: invokevirtual   com/intellij/lexer/Lexer.advance:()V
        //   234: aload           4
        //   236: invokevirtual   com/intellij/lexer/Lexer.getTokenType:()Lcom/intellij/psi/tree/IElementType;
        //   239: ifnull          395
        //   242: goto            249
        //   245: invokestatic    com/jetbrains/cidr/lang/OCHeaderFileTypeDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   248: athrow         
        //   249: aconst_null    
        //   250: areturn        
        //   251: invokestatic    com/jetbrains/cidr/lang/OCHeaderFileTypeDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   254: athrow         
        //   255: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.COMMENTS:Lcom/intellij/psi/tree/TokenSet;
        //   258: aload           8
        //   260: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   263: ifeq            272
        //   266: iconst_0       
        //   267: istore          7
        //   269: goto            387
        //   272: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.WHITESPACES:Lcom/intellij/psi/tree/TokenSet;
        //   275: aload           8
        //   277: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   280: ifeq            309
        //   283: aload           4
        //   285: invokevirtual   com/intellij/lexer/Lexer.getTokenText:()Ljava/lang/String;
        //   288: ldc             "\n"
        //   290: invokevirtual   java/lang/String.contains:(Ljava/lang/CharSequence;)Z
        //   293: ifeq            387
        //   296: goto            303
        //   299: invokestatic    com/jetbrains/cidr/lang/OCHeaderFileTypeDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   302: athrow         
        //   303: iconst_1       
        //   304: istore          5
        //   306: goto            387
        //   309: aload           8
        //   311: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IDENTIFIER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   314: if_acmpne       346
        //   317: iinc            7, 1
        //   320: iload           7
        //   322: bipush          7
        //   324: if_icmple       340
        //   327: goto            334
        //   330: invokestatic    com/jetbrains/cidr/lang/OCHeaderFileTypeDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   333: athrow         
        //   334: aconst_null    
        //   335: areturn        
        //   336: invokestatic    com/jetbrains/cidr/lang/OCHeaderFileTypeDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   339: athrow         
        //   340: iconst_0       
        //   341: istore          5
        //   343: goto            387
        //   346: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DIRECTIVES:Lcom/intellij/psi/tree/TokenSet;
        //   349: aload           8
        //   351: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   354: ifeq            381
        //   357: iload           5
        //   359: ifeq            381
        //   362: goto            369
        //   365: invokestatic    com/jetbrains/cidr/lang/OCHeaderFileTypeDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   368: athrow         
        //   369: iconst_1       
        //   370: istore          6
        //   372: iconst_0       
        //   373: istore          5
        //   375: iconst_0       
        //   376: istore          7
        //   378: goto            387
        //   381: iconst_0       
        //   382: istore          5
        //   384: iconst_0       
        //   385: istore          7
        //   387: aload           4
        //   389: invokevirtual   com/intellij/lexer/Lexer.advance:()V
        //   392: goto            187
        //   395: iload           6
        //   397: ifeq            410
        //   400: getstatic       com/jetbrains/cidr/lang/OCFileType.INSTANCE:Lcom/jetbrains/cidr/lang/OCFileType;
        //   403: goto            411
        //   406: invokestatic    com/jetbrains/cidr/lang/OCHeaderFileTypeDetector.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   409: athrow         
        //   410: aconst_null    
        //   411: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     94     94     98     Ljava/lang/IllegalArgumentException;
        //  135    142    142    146    Ljava/lang/IllegalArgumentException;
        //  146    157    157    161    Ljava/lang/IllegalArgumentException;
        //  194    202    202    206    Ljava/lang/IllegalArgumentException;
        //  206    222    225    229    Ljava/lang/IllegalArgumentException;
        //  214    242    245    249    Ljava/lang/IllegalArgumentException;
        //  229    251    251    255    Ljava/lang/IllegalArgumentException;
        //  272    296    299    303    Ljava/lang/IllegalArgumentException;
        //  309    327    330    334    Ljava/lang/IllegalArgumentException;
        //  317    336    336    340    Ljava/lang/IllegalArgumentException;
        //  346    362    365    369    Ljava/lang/IllegalArgumentException;
        //  395    406    406    410    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0229:
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
    
    public int getVersion() {
        return 1;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
