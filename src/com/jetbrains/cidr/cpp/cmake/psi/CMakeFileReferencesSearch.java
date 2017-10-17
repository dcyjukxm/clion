// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFileSystemItem;
import com.intellij.util.Processor;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.search.searches.ReferencesSearch;
import com.intellij.psi.PsiReference;
import com.intellij.util.QueryExecutor;

public class CMakeFileReferencesSearch implements QueryExecutor<PsiReference, ReferencesSearch.SearchParameters>
{
    public boolean execute(@NotNull final ReferencesSearch.SearchParameters p0, @NotNull final Processor<PsiReference> p1) {
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
        //    18: ldc             "queryParameters"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/cpp/cmake/psi/CMakeFileReferencesSearch"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "execute"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/CMakeFileReferencesSearch.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "consumer"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/cpp/cmake/psi/CMakeFileReferencesSearch"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "execute"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/CMakeFileReferencesSearch.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_1        
        //    89: invokevirtual   com/intellij/psi/search/searches/ReferencesSearch$SearchParameters.getElementToSearch:()Lcom/intellij/psi/PsiElement;
        //    92: astore_3       
        //    93: aload_3        
        //    94: instanceof      Lcom/intellij/psi/PsiFileSystemItem;
        //    97: ifne            106
        //   100: iconst_1       
        //   101: ireturn        
        //   102: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/CMakeFileReferencesSearch.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   105: athrow         
        //   106: aload_3        
        //   107: checkcast       Lcom/intellij/psi/PsiFileSystemItem;
        //   110: astore          4
        //   112: aload           4
        //   114: invokeinterface com/intellij/psi/PsiFileSystemItem.getName:()Ljava/lang/String;
        //   119: astore          5
        //   121: aload           5
        //   123: bipush          36
        //   125: bipush          32
        //   127: invokevirtual   java/lang/String.replace:(CC)Ljava/lang/String;
        //   130: invokestatic    com/intellij/openapi/util/text/StringUtil.getWordsIn:(Ljava/lang/String;)Ljava/util/List;
        //   133: astore          6
        //   135: aload           6
        //   137: invokeinterface java/util/List.size:()I
        //   142: iconst_1       
        //   143: if_icmplt       185
        //   146: aload           5
        //   148: ldc             "$"
        //   150: invokevirtual   java/lang/String.contains:(Ljava/lang/CharSequence;)Z
        //   153: ifne            191
        //   156: goto            163
        //   159: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/CMakeFileReferencesSearch.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   162: athrow         
        //   163: aload           6
        //   165: ldc             "."
        //   167: invokestatic    com/intellij/openapi/util/text/StringUtil.join:(Ljava/util/Collection;Ljava/lang/String;)Ljava/lang/String;
        //   170: aload           5
        //   172: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   175: ifeq            191
        //   178: goto            185
        //   181: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/CMakeFileReferencesSearch.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   184: athrow         
        //   185: iconst_1       
        //   186: ireturn        
        //   187: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/CMakeFileReferencesSearch.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   190: athrow         
        //   191: aload           4
        //   193: invokeinterface com/intellij/psi/PsiFileSystemItem.getProject:()Lcom/intellij/openapi/project/Project;
        //   198: invokestatic    com/intellij/psi/search/PsiSearchHelper$SERVICE.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/search/PsiSearchHelper;
        //   201: astore          7
        //   203: aconst_null    
        //   204: astore          8
        //   206: aload           6
        //   208: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   213: astore          9
        //   215: aload           9
        //   217: invokeinterface java/util/Iterator.hasNext:()Z
        //   222: ifeq            257
        //   225: aload           9
        //   227: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   232: checkcast       Ljava/lang/String;
        //   235: astore          10
        //   237: aload           10
        //   239: ldc             "$"
        //   241: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   244: ifne            254
        //   247: aload           10
        //   249: astore          8
        //   251: goto            257
        //   254: goto            215
        //   257: aload           8
        //   259: ifnonnull       268
        //   262: iconst_1       
        //   263: ireturn        
        //   264: invokestatic    com/jetbrains/cidr/cpp/cmake/psi/CMakeFileReferencesSearch.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   267: athrow         
        //   268: aload_1        
        //   269: invokevirtual   com/intellij/psi/search/searches/ReferencesSearch$SearchParameters.getEffectiveSearchScope:()Lcom/intellij/psi/search/SearchScope;
        //   272: astore          9
        //   274: aload           9
        //   276: instanceof      Lcom/intellij/psi/search/GlobalSearchScope;
        //   279: ifeq            302
        //   282: aload           9
        //   284: checkcast       Lcom/intellij/psi/search/GlobalSearchScope;
        //   287: iconst_1       
        //   288: anewarray       Lcom/intellij/openapi/fileTypes/FileType;
        //   291: dup            
        //   292: iconst_0       
        //   293: getstatic       com/jetbrains/cidr/cpp/cmake/CMakeListsFileType.INSTANCE:Lcom/intellij/openapi/fileTypes/FileType;
        //   296: aastore        
        //   297: invokestatic    com/intellij/psi/search/GlobalSearchScope.getScopeRestrictedByFileTypes:(Lcom/intellij/psi/search/GlobalSearchScope;[Lcom/intellij/openapi/fileTypes/FileType;)Lcom/intellij/psi/search/GlobalSearchScope;
        //   300: astore          9
        //   302: aload           7
        //   304: aload           4
        //   306: aload_2        
        //   307: invokedynamic   execute:(Lcom/intellij/psi/PsiFileSystemItem;Lcom/intellij/util/Processor;)Lcom/intellij/psi/search/TextOccurenceProcessor;
        //   312: aload           9
        //   314: aload           8
        //   316: iconst_1       
        //   317: iconst_1       
        //   318: invokeinterface com/intellij/psi/search/PsiSearchHelper.processElementsWithWord:(Lcom/intellij/psi/search/TextOccurenceProcessor;Lcom/intellij/psi/search/SearchScope;Ljava/lang/String;SZ)Z
        //   323: ireturn        
        //    Signature:
        //  (Lcom/intellij/psi/search/searches/ReferencesSearch$SearchParameters;Lcom/intellij/util/Processor<Lcom/intellij/psi/PsiReference;>;)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  93     102    102    106    Ljava/lang/IllegalArgumentException;
        //  135    156    159    163    Ljava/lang/IllegalArgumentException;
        //  146    178    181    185    Ljava/lang/IllegalArgumentException;
        //  163    187    187    191    Ljava/lang/IllegalArgumentException;
        //  257    264    264    268    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0163:
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
