// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search;

import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.psi.OCDefineDirective;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCMacroCallArgument;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.project.DumbServiceImpl;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.project.Project;
import com.intellij.psi.search.SearchScope;
import com.intellij.util.Processor;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.search.searches.ReferencesSearch;
import com.intellij.util.QueryExecutor;

public class OCElementInMacroAndNonCompiledCodeReferencesSearch implements QueryExecutor<Usage, ReferencesSearch.SearchParameters>
{
    public boolean execute(@NotNull final ReferencesSearch.SearchParameters searchParameters, @NotNull final Processor<Usage> processor) {
        try {
            if (searchParameters == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "queryParameters", "com/jetbrains/cidr/lang/search/OCElementInMacroAndNonCompiledCodeReferencesSearch", "execute"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "consumer", "com/jetbrains/cidr/lang/search/OCElementInMacroAndNonCompiledCodeReferencesSearch", "execute"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return this.execute(searchParameters, searchParameters.getEffectiveSearchScope(), processor);
    }
    
    public boolean execute(@NotNull final ReferencesSearch.SearchParameters p0, @NotNull final SearchScope p1, @NotNull final Processor<Usage> p2) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/search/OCElementInMacroAndNonCompiledCodeReferencesSearch"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "execute"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/search/OCElementInMacroAndNonCompiledCodeReferencesSearch.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "searchScope"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/search/OCElementInMacroAndNonCompiledCodeReferencesSearch"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "execute"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/search/OCElementInMacroAndNonCompiledCodeReferencesSearch.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //   106: ldc             "consumer"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/search/OCElementInMacroAndNonCompiledCodeReferencesSearch"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "execute"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/search/OCElementInMacroAndNonCompiledCodeReferencesSearch.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   131: athrow         
        //   132: aload_1        
        //   133: invokevirtual   com/intellij/psi/search/searches/ReferencesSearch$SearchParameters.getElementToSearch:()Lcom/intellij/psi/PsiElement;
        //   136: astore          4
        //   138: aload           4
        //   140: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSymbolDeclarator;
        //   143: ifeq            166
        //   146: aload           4
        //   148: invokeinterface com/intellij/psi/PsiElement.getProject:()Lcom/intellij/openapi/project/Project;
        //   153: invokestatic    com/jetbrains/cidr/lang/search/OCElementInMacroAndNonCompiledCodeReferencesSearch.a:(Lcom/intellij/openapi/project/Project;)Z
        //   156: ifeq            172
        //   159: goto            166
        //   162: invokestatic    com/jetbrains/cidr/lang/search/OCElementInMacroAndNonCompiledCodeReferencesSearch.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   165: athrow         
        //   166: iconst_1       
        //   167: ireturn        
        //   168: invokestatic    com/jetbrains/cidr/lang/search/OCElementInMacroAndNonCompiledCodeReferencesSearch.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   171: athrow         
        //   172: aload           4
        //   174: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSymbolDeclarator;
        //   177: invokeinterface com/jetbrains/cidr/lang/psi/OCSymbolDeclarator.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   182: astore          5
        //   184: aload           5
        //   186: ifnonnull       195
        //   189: iconst_1       
        //   190: ireturn        
        //   191: invokestatic    com/jetbrains/cidr/lang/search/OCElementInMacroAndNonCompiledCodeReferencesSearch.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   194: athrow         
        //   195: aload           4
        //   197: invokeinterface com/intellij/psi/PsiElement.getProject:()Lcom/intellij/openapi/project/Project;
        //   202: invokestatic    com/intellij/psi/search/PsiSearchHelper$SERVICE.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/search/PsiSearchHelper;
        //   205: astore          6
        //   207: aload           5
        //   209: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //   214: astore          7
        //   216: aload           4
        //   218: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDefineDirective;
        //   221: istore          8
        //   223: aload           5
        //   225: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   228: ifne            266
        //   231: aload           5
        //   233: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   236: ifne            266
        //   239: goto            246
        //   242: invokestatic    com/jetbrains/cidr/lang/search/OCElementInMacroAndNonCompiledCodeReferencesSearch.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   245: athrow         
        //   246: aload           5
        //   248: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   253: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isType:()Z
        //   256: ifeq            274
        //   259: goto            266
        //   262: invokestatic    com/jetbrains/cidr/lang/search/OCElementInMacroAndNonCompiledCodeReferencesSearch.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   265: athrow         
        //   266: iconst_1       
        //   267: goto            275
        //   270: invokestatic    com/jetbrains/cidr/lang/search/OCElementInMacroAndNonCompiledCodeReferencesSearch.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   273: athrow         
        //   274: iconst_0       
        //   275: istore          9
        //   277: aload           5
        //   279: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   282: ifeq            396
        //   285: aload           5
        //   287: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   290: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getSelectors:()Ljava/util/List;
        //   295: astore          10
        //   297: aload           10
        //   299: invokeinterface java/util/List.isEmpty:()Z
        //   304: ifeq            315
        //   307: aconst_null    
        //   308: goto            326
        //   311: invokestatic    com/jetbrains/cidr/lang/search/OCElementInMacroAndNonCompiledCodeReferencesSearch.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   314: athrow         
        //   315: aload           10
        //   317: iconst_0       
        //   318: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   323: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol$SelectorPartSymbol;
        //   326: astore          11
        //   328: aload           11
        //   330: ifnull          347
        //   333: aload           11
        //   335: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol$SelectorPartSymbol.getSelectorName:()Ljava/lang/String;
        //   340: goto            348
        //   343: invokestatic    com/jetbrains/cidr/lang/search/OCElementInMacroAndNonCompiledCodeReferencesSearch.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   346: athrow         
        //   347: aconst_null    
        //   348: astore          12
        //   350: aload           12
        //   352: ifnull          396
        //   355: aload           12
        //   357: ldc             ":"
        //   359: invokevirtual   java/lang/String.endsWith:(Ljava/lang/String;)Z
        //   362: ifeq            392
        //   365: goto            372
        //   368: invokestatic    com/jetbrains/cidr/lang/search/OCElementInMacroAndNonCompiledCodeReferencesSearch.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   371: athrow         
        //   372: aload           12
        //   374: iconst_0       
        //   375: aload           12
        //   377: invokevirtual   java/lang/String.length:()I
        //   380: iconst_1       
        //   381: isub           
        //   382: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   385: goto            394
        //   388: invokestatic    com/jetbrains/cidr/lang/search/OCElementInMacroAndNonCompiledCodeReferencesSearch.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   391: athrow         
        //   392: aload           12
        //   394: astore          7
        //   396: aload           7
        //   398: invokevirtual   java/lang/String.isEmpty:()Z
        //   401: ifne            436
        //   404: aload           7
        //   406: invokevirtual   java/lang/String.length:()I
        //   409: istore          10
        //   411: aload           6
        //   413: iload           10
        //   415: aload_3        
        //   416: iload           9
        //   418: iload           8
        //   420: invokedynamic   execute:(ILcom/intellij/util/Processor;ZZ)Lcom/intellij/psi/search/TextOccurenceProcessor;
        //   425: aload_2        
        //   426: aload           7
        //   428: iconst_1       
        //   429: iconst_1       
        //   430: invokeinterface com/intellij/psi/search/PsiSearchHelper.processElementsWithWord:(Lcom/intellij/psi/search/TextOccurenceProcessor;Lcom/intellij/psi/search/SearchScope;Ljava/lang/String;SZ)Z
        //   435: ireturn        
        //   436: iconst_1       
        //   437: ireturn        
        //    Signature:
        //  (Lcom/intellij/psi/search/searches/ReferencesSearch$SearchParameters;Lcom/intellij/psi/search/SearchScope;Lcom/intellij/util/Processor<Lcom/jetbrains/cidr/lang/search/OCElementInMacroAndNonCompiledCodeReferencesSearch$Usage;>;)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     128    128    132    Ljava/lang/IllegalArgumentException;
        //  138    159    162    166    Ljava/lang/IllegalArgumentException;
        //  146    168    168    172    Ljava/lang/IllegalArgumentException;
        //  184    191    191    195    Ljava/lang/IllegalArgumentException;
        //  223    239    242    246    Ljava/lang/IllegalArgumentException;
        //  231    259    262    266    Ljava/lang/IllegalArgumentException;
        //  246    270    270    274    Ljava/lang/IllegalArgumentException;
        //  297    311    311    315    Ljava/lang/IllegalArgumentException;
        //  328    343    343    347    Ljava/lang/IllegalArgumentException;
        //  350    365    368    372    Ljava/lang/IllegalArgumentException;
        //  355    388    388    392    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0246:
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
    
    private static boolean a(@Nullable final Project project) {
        try {
            if (project != null) {
                return DumbServiceImpl.getInstance(project).isDumb();
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        for (final Project project2 : ProjectManager.getInstance().getOpenProjects()) {
            try {
                if (DumbServiceImpl.getInstance(project2).isDumb()) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    public static class Usage
    {
        @NotNull
        private final PsiElement myElement;
        @Nullable
        private final TextRange myTextRange;
        
        public Usage(@NotNull final PsiElement myElement, @Nullable final TextRange myTextRange) {
            if (myElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/search/OCElementInMacroAndNonCompiledCodeReferencesSearch$Usage", "<init>"));
            }
            this.myElement = myElement;
            this.myTextRange = myTextRange;
        }
        
        public Usage(@NotNull final PsiElement myElement) {
            if (myElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/search/OCElementInMacroAndNonCompiledCodeReferencesSearch$Usage", "<init>"));
            }
            this.myElement = myElement;
            this.myTextRange = null;
        }
        
        @NotNull
        public PsiElement getElement() {
            PsiElement myElement;
            try {
                myElement = this.myElement;
                if (myElement == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/search/OCElementInMacroAndNonCompiledCodeReferencesSearch$Usage", "getElement"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return myElement;
        }
        
        @NotNull
        public TextRange getTextRange() {
            TextRange textRange = null;
            Label_0027: {
                try {
                    if (this.myTextRange == null) {
                        final TextRange textRange2;
                        textRange = (textRange2 = this.myElement.getTextRange());
                        break Label_0027;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                TextRange textRange2;
                textRange = (textRange2 = this.myTextRange);
                try {
                    if (textRange2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/search/OCElementInMacroAndNonCompiledCodeReferencesSearch$Usage", "getTextRange"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return textRange;
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
}
