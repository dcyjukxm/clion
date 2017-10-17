// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.workspace;

import java.io.File;
import com.intellij.psi.PsiDocumentManager;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.editor.Document;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeElementFactory;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import java.io.IOException;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeFile;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;

class FileStamp
{
    public final long timestamp;
    public final long size;
    public final long contentCrc;
    
    public FileStamp(final long timestamp, final long size, final long contentCrc) {
        this.timestamp = timestamp;
        this.size = size;
        this.contentCrc = contentCrc;
    }
    
    @Override
    public boolean equals(final Object o) {
        try {
            if (this == o) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        Label_0039: {
            try {
                if (o == null) {
                    return false;
                }
                final FileStamp fileStamp = this;
                final Class<? extends FileStamp> clazz = fileStamp.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
                break Label_0039;
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            try {
                final FileStamp fileStamp = this;
                final Class<? extends FileStamp> clazz = fileStamp.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
        }
        final FileStamp fileStamp2 = (FileStamp)o;
        try {
            if (this.timestamp != fileStamp2.timestamp) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        try {
            if (this.size != fileStamp2.size) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw b(ex5);
        }
        try {
            if (this.contentCrc != fileStamp2.contentCrc) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw b(ex6);
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return 31 * (31 * (int)(this.timestamp ^ this.timestamp >>> 32) + (int)(this.size ^ this.size >>> 32)) + (int)(this.contentCrc ^ this.contentCrc >>> 32);
    }
    
    @Override
    public String toString() {
        return "Stamp{timestamp=" + this.timestamp + ", size=" + this.size + ", contentCrc=" + this.contentCrc + '}';
    }
    
    @Nullable
    public static CMakeFile createOrGetCMakeFile(@NotNull final Project project, @NotNull final VirtualFile virtualFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/cmake/workspace/FileStamp", "createOrGetCMakeFile"));
            }
        }
        catch (IOException ex) {
            throw b(ex);
        }
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/cpp/cmake/workspace/FileStamp", "createOrGetCMakeFile"));
            }
        }
        catch (IOException ex2) {
            throw b(ex2);
        }
        final FileDocumentManager instance = FileDocumentManager.getInstance();
        Document cachedDocument = null;
        Label_0113: {
            try {
                if (instance.isFileModified(virtualFile)) {
                    cachedDocument = null;
                    break Label_0113;
                }
            }
            catch (IOException ex3) {
                throw b(ex3);
            }
            cachedDocument = instance.getCachedDocument(virtualFile);
        }
        final Document document = cachedDocument;
        try {
            if (document != null) {
                return createOrGetCMakeFile(project, document);
            }
        }
        catch (IOException ex4) {
            throw b(ex4);
        }
        try {
            return CMakeElementFactory.createFile(project, VfsUtilCore.loadText(virtualFile));
        }
        catch (IOException ex5) {
            return null;
        }
    }
    
    @Nullable
    public static CMakeFile createOrGetCMakeFile(@NotNull final Project project, @NotNull final Document document) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/cmake/workspace/FileStamp", "createOrGetCMakeFile"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (document == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "document", "com/jetbrains/cidr/cpp/cmake/workspace/FileStamp", "createOrGetCMakeFile"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        final PsiDocumentManager instance = PsiDocumentManager.getInstance(project);
        Object cachedPsiFile = null;
        Label_0114: {
            try {
                if (instance.isCommitted(document)) {
                    cachedPsiFile = instance.getCachedPsiFile(document);
                    break Label_0114;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            cachedPsiFile = null;
        }
        final Object o = cachedPsiFile;
        try {
            if (o instanceof CMakeFile) {
                return (CMakeFile)o;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        return CMakeElementFactory.createFile(project, document.getText());
    }
    
    @NotNull
    public static FileStamp calcFileStamp(@NotNull final Project p0, @NotNull final File p1, @NotNull final FileStampFunction p2) {
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
        //    18: ldc             "project"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/cpp/cmake/workspace/FileStamp"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "calcFileStamp"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/FileStamp.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
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
        //    62: ldc             "file"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/cpp/cmake/workspace/FileStamp"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "calcFileStamp"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/FileStamp.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    87: athrow         
        //    88: aload_2        
        //    89: ifnonnull       132
        //    92: new             Ljava/lang/IllegalArgumentException;
        //    95: dup            
        //    96: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    98: ldc             3
        //   100: anewarray       Ljava/lang/Object;
        //   103: dup            
        //   104: ldc             0
        //   106: ldc             "fileFunction"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/cpp/cmake/workspace/FileStamp"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "calcFileStamp"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/FileStamp.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   131: athrow         
        //   132: aload_1        
        //   133: invokevirtual   java/io/File.lastModified:()J
        //   136: lstore_3       
        //   137: aload_1        
        //   138: invokevirtual   java/io/File.length:()J
        //   141: lstore          5
        //   143: aload_2        
        //   144: invokeinterface com/jetbrains/cidr/cpp/cmake/workspace/FileStamp$FileStampFunction.getAlreadyCalculatedStamp:()Lcom/jetbrains/cidr/cpp/cmake/workspace/FileStamp;
        //   149: astore          7
        //   151: aload           7
        //   153: ifnull          239
        //   156: lload_3        
        //   157: aload           7
        //   159: getfield        com/jetbrains/cidr/cpp/cmake/workspace/FileStamp.timestamp:J
        //   162: lcmp           
        //   163: ifne            239
        //   166: goto            173
        //   169: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/FileStamp.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   172: athrow         
        //   173: lload           5
        //   175: aload           7
        //   177: getfield        com/jetbrains/cidr/cpp/cmake/workspace/FileStamp.size:J
        //   180: lcmp           
        //   181: ifne            239
        //   184: goto            191
        //   187: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/FileStamp.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   190: athrow         
        //   191: aload           7
        //   193: dup            
        //   194: ifnonnull       238
        //   197: goto            204
        //   200: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/FileStamp.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   203: athrow         
        //   204: new             Ljava/lang/IllegalStateException;
        //   207: dup            
        //   208: ldc             "@NotNull method %s.%s must not return null"
        //   210: ldc             2
        //   212: anewarray       Ljava/lang/Object;
        //   215: dup            
        //   216: ldc             0
        //   218: ldc             "com/jetbrains/cidr/cpp/cmake/workspace/FileStamp"
        //   220: aastore        
        //   221: dup            
        //   222: ldc             1
        //   224: ldc             "calcFileStamp"
        //   226: aastore        
        //   227: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   230: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   233: athrow         
        //   234: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/FileStamp.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   237: athrow         
        //   238: areturn        
        //   239: lconst_0       
        //   240: lstore          8
        //   242: aload_2        
        //   243: aload_0        
        //   244: invokeinterface com/jetbrains/cidr/cpp/cmake/workspace/FileStamp$FileStampFunction.getCMakeFile:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeFile;
        //   249: astore          10
        //   251: aload           10
        //   253: ifnull          263
        //   256: aload           10
        //   258: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/FileStamp.calcFileHashCode:(Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeFile;)J
        //   261: lstore          8
        //   263: new             Lcom/jetbrains/cidr/cpp/cmake/workspace/FileStamp;
        //   266: dup            
        //   267: lload_3        
        //   268: lload           5
        //   270: lload           8
        //   272: invokespecial   com/jetbrains/cidr/cpp/cmake/workspace/FileStamp.<init>:(JJJ)V
        //   275: dup            
        //   276: ifnonnull       313
        //   279: new             Ljava/lang/IllegalStateException;
        //   282: dup            
        //   283: ldc             "@NotNull method %s.%s must not return null"
        //   285: ldc             2
        //   287: anewarray       Ljava/lang/Object;
        //   290: dup            
        //   291: ldc             0
        //   293: ldc             "com/jetbrains/cidr/cpp/cmake/workspace/FileStamp"
        //   295: aastore        
        //   296: dup            
        //   297: ldc             1
        //   299: ldc             "calcFileStamp"
        //   301: aastore        
        //   302: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   305: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   308: athrow         
        //   309: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/FileStamp.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   312: athrow         
        //   313: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     128    128    132    Ljava/lang/IllegalArgumentException;
        //  151    166    169    173    Ljava/lang/IllegalArgumentException;
        //  156    184    187    191    Ljava/lang/IllegalArgumentException;
        //  173    197    200    204    Ljava/lang/IllegalArgumentException;
        //  191    234    234    238    Ljava/lang/IllegalArgumentException;
        //  263    309    309    313    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0173:
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
    
    public static long calcFileHashCode(@NotNull final CMakeFile p0) {
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
        //    18: ldc             "psiFile"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/cpp/cmake/workspace/FileStamp"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "calcFileHashCode"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/FileStamp.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    43: athrow         
        //    44: new             Ljava/util/zip/CRC32;
        //    47: dup            
        //    48: invokespecial   java/util/zip/CRC32.<init>:()V
        //    51: astore_1       
        //    52: iconst_0       
        //    53: istore_2       
        //    54: aload_0        
        //    55: invokestatic    com/intellij/psi/util/PsiTreeUtil.firstChild:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //    58: astore_3       
        //    59: aload_3        
        //    60: ifnull          376
        //    63: aload_3        
        //    64: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    69: astore          4
        //    71: aload_3        
        //    72: instanceof      Lcom/intellij/psi/impl/source/tree/LeafPsiElement;
        //    75: ifeq            171
        //    78: aload_3        
        //    79: instanceof      Lcom/intellij/psi/PsiComment;
        //    82: ifne            171
        //    85: goto            92
        //    88: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/FileStamp.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    91: athrow         
        //    92: aload_3        
        //    93: instanceof      Lcom/intellij/psi/PsiWhiteSpace;
        //    96: ifne            171
        //    99: goto            106
        //   102: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/FileStamp.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   105: athrow         
        //   106: ldc             "\""
        //   108: aload_3        
        //   109: invokeinterface com/intellij/psi/PsiElement.getText:()Ljava/lang/String;
        //   114: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   117: ifne            171
        //   120: goto            127
        //   123: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/FileStamp.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   126: athrow         
        //   127: ldc             ";"
        //   129: aload_3        
        //   130: invokeinterface com/intellij/psi/PsiElement.getText:()Ljava/lang/String;
        //   135: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   138: ifeq            163
        //   141: goto            148
        //   144: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/FileStamp.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   147: athrow         
        //   148: aload           4
        //   150: instanceof      Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeCommandArguments;
        //   153: ifne            171
        //   156: goto            163
        //   159: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/FileStamp.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   162: athrow         
        //   163: iconst_1       
        //   164: goto            172
        //   167: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/FileStamp.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   170: athrow         
        //   171: iconst_0       
        //   172: istore          5
        //   174: iload           5
        //   176: ifeq            368
        //   179: aload_3        
        //   180: checkcast       Lcom/intellij/psi/impl/source/tree/TreeElement;
        //   183: invokevirtual   com/intellij/psi/impl/source/tree/TreeElement.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   186: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.EOL:Lcom/intellij/psi/tree/IElementType;
        //   189: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   192: istore          6
        //   194: iload           6
        //   196: ifne            327
        //   199: iload_2        
        //   200: ifeq            218
        //   203: goto            210
        //   206: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/FileStamp.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   209: athrow         
        //   210: aload_1        
        //   211: bipush          10
        //   213: invokevirtual   java/util/zip/CRC32.update:(I)V
        //   216: iconst_0       
        //   217: istore_2       
        //   218: aload_1        
        //   219: iconst_m1      
        //   220: invokevirtual   java/util/zip/CRC32.update:(I)V
        //   223: aload_3        
        //   224: invokeinterface com/intellij/psi/PsiElement.getText:()Ljava/lang/String;
        //   229: astore          7
        //   231: aload           4
        //   233: instanceof      Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeCommandName;
        //   236: ifeq            258
        //   239: aload           7
        //   241: getstatic       java/util/Locale.ENGLISH:Ljava/util/Locale;
        //   244: invokevirtual   java/lang/String.toLowerCase:(Ljava/util/Locale;)Ljava/lang/String;
        //   247: astore          7
        //   249: aload_1        
        //   250: bipush          -2
        //   252: invokevirtual   java/util/zip/CRC32.update:(I)V
        //   255: goto            318
        //   258: aload           4
        //   260: instanceof      Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeArgument;
        //   263: ifeq            287
        //   266: aload           4
        //   268: checkcast       Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeArgument;
        //   271: invokeinterface com/jetbrains/cidr/cpp/cmake/psi/CMakeArgument.getValue:()Ljava/lang/String;
        //   276: astore          7
        //   278: aload_1        
        //   279: bipush          -3
        //   281: invokevirtual   java/util/zip/CRC32.update:(I)V
        //   284: goto            318
        //   287: aload           4
        //   289: instanceof      Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeLiteral;
        //   292: ifeq            318
        //   295: aload           4
        //   297: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   302: checkcast       Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeArgument;
        //   305: invokeinterface com/jetbrains/cidr/cpp/cmake/psi/CMakeArgument.getValue:()Ljava/lang/String;
        //   310: astore          7
        //   312: aload_1        
        //   313: bipush          -4
        //   315: invokevirtual   java/util/zip/CRC32.update:(I)V
        //   318: aload_1        
        //   319: aload           7
        //   321: invokevirtual   java/lang/String.getBytes:()[B
        //   324: invokevirtual   java/util/zip/CRC32.update:([B)V
        //   327: iload_2        
        //   328: ifne            358
        //   331: iload           6
        //   333: ifeq            366
        //   336: goto            343
        //   339: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/FileStamp.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   342: athrow         
        //   343: aload           4
        //   345: instanceof      Lcom/jetbrains/cidr/cpp/cmake/psi/CMakeFile;
        //   348: ifne            366
        //   351: goto            358
        //   354: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/FileStamp.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   357: athrow         
        //   358: iconst_1       
        //   359: goto            367
        //   362: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/FileStamp.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   365: athrow         
        //   366: iconst_0       
        //   367: istore_2       
        //   368: aload_3        
        //   369: invokestatic    com/intellij/psi/util/PsiTreeUtil.nextLeaf:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   372: astore_3       
        //   373: goto            59
        //   376: aload_1        
        //   377: invokevirtual   java/util/zip/CRC32.getValue:()J
        //   380: lreturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  71     85     88     92     Ljava/lang/IllegalArgumentException;
        //  78     99     102    106    Ljava/lang/IllegalArgumentException;
        //  92     120    123    127    Ljava/lang/IllegalArgumentException;
        //  106    141    144    148    Ljava/lang/IllegalArgumentException;
        //  127    156    159    163    Ljava/lang/IllegalArgumentException;
        //  148    167    167    171    Ljava/lang/IllegalArgumentException;
        //  194    203    206    210    Ljava/lang/IllegalArgumentException;
        //  327    336    339    343    Ljava/lang/IllegalArgumentException;
        //  331    351    354    358    Ljava/lang/IllegalArgumentException;
        //  343    362    362    366    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0092:
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
    
    private static Exception b(final Exception ex) {
        return ex;
    }
    
    interface FileStampFunction
    {
        @Nullable
        FileStamp getAlreadyCalculatedStamp();
        
        @Nullable
        CMakeFile getCMakeFile(@NotNull final Project p0);
    }
}
