// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search;

import com.intellij.psi.PsiElement;
import com.intellij.psi.search.PsiSearchHelper;
import com.jetbrains.cidr.lang.psi.OCLocalizedString;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.application.ReadAction;
import com.intellij.util.Processor;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.search.searches.ReferencesSearch;
import com.intellij.psi.PsiReference;
import com.intellij.util.QueryExecutor;

public class OCLocalizedStringReferencesSearch implements QueryExecutor<PsiReference, ReferencesSearch.SearchParameters>
{
    public boolean execute(@NotNull final ReferencesSearch.SearchParameters searchParameters, @NotNull final Processor<PsiReference> processor) {
        try {
            if (searchParameters == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "queryParameters", "com/jetbrains/cidr/lang/search/OCLocalizedStringReferencesSearch", "execute"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "consumer", "com/jetbrains/cidr/lang/search/OCLocalizedStringReferencesSearch", "execute"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return (boolean)new ReadAction<Boolean>() {
            protected void run(@NotNull final Result<Boolean> result) {
                try {
                    if (result == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/search/OCLocalizedStringReferencesSearch$1", "run"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                result.setResult((Object)a(searchParameters, processor));
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        }.execute().getResultObject();
    }
    
    private static Boolean a(final ReferencesSearch.SearchParameters searchParameters, final Processor<PsiReference> processor) {
        final PsiElement elementToSearch = searchParameters.getElementToSearch();
        try {
            if (!(elementToSearch instanceof OCLocalizedString)) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final PsiSearchHelper instance = PsiSearchHelper.SERVICE.getInstance(elementToSearch.getProject());
        final String name = ((OCLocalizedString)elementToSearch).getName();
        Label_0062: {
            try {
                if (name == null) {
                    break Label_0062;
                }
                final String s = name;
                final boolean b = s.isEmpty();
                if (b) {
                    break Label_0062;
                }
                return instance.processElementsWithWord((p2, p3) -> {
                    // 
                    // This method could not be decompiled.
                    // 
                    // Original Bytecode:
                    // 
                    //     0: getstatic       com/intellij/psi/PsiReference.EMPTY_ARRAY:[Lcom/intellij/psi/PsiReference;
                    //     3: astore          4
                    //     5: aload_2        
                    //     6: instanceof      Lcom/jetbrains/cidr/lang/psi/OCLiteralExpression;
                    //     9: ifeq            23
                    //    12: aload_2        
                    //    13: invokeinterface com/intellij/psi/PsiElement.getReferences:()[Lcom/intellij/psi/PsiReference;
                    //    18: astore          4
                    //    20: goto            46
                    //    23: aload_2        
                    //    24: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMacroCall;
                    //    27: ifeq            46
                    //    30: iconst_1       
                    //    31: anewarray       Lcom/intellij/psi/PsiReference;
                    //    34: dup            
                    //    35: iconst_0       
                    //    36: aload_2        
                    //    37: iload_3        
                    //    38: invokeinterface com/intellij/psi/PsiElement.findReferenceAt:(I)Lcom/intellij/psi/PsiReference;
                    //    43: aastore        
                    //    44: astore          4
                    //    46: aload           4
                    //    48: astore          5
                    //    50: aload           5
                    //    52: arraylength    
                    //    53: istore          6
                    //    55: iconst_0       
                    //    56: istore          7
                    //    58: iload           7
                    //    60: iload           6
                    //    62: if_icmpge       125
                    //    65: aload           5
                    //    67: iload           7
                    //    69: aaload         
                    //    70: astore          8
                    //    72: aload           8
                    //    74: ifnull          119
                    //    77: aload           8
                    //    79: aload_0        
                    //    80: invokeinterface com/intellij/psi/PsiReference.isReferenceTo:(Lcom/intellij/psi/PsiElement;)Z
                    //    85: ifeq            119
                    //    88: goto            95
                    //    91: invokestatic    com/jetbrains/cidr/lang/search/OCLocalizedStringReferencesSearch.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                    //    94: athrow         
                    //    95: aload_1        
                    //    96: aload           8
                    //    98: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
                    //   103: ifne            119
                    //   106: goto            113
                    //   109: invokestatic    com/jetbrains/cidr/lang/search/OCLocalizedStringReferencesSearch.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                    //   112: athrow         
                    //   113: iconst_0       
                    //   114: ireturn        
                    //   115: invokestatic    com/jetbrains/cidr/lang/search/OCLocalizedStringReferencesSearch.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                    //   118: athrow         
                    //   119: iinc            7, 1
                    //   122: goto            58
                    //   125: iconst_1       
                    //   126: ireturn        
                    //    Exceptions:
                    //  Try           Handler
                    //  Start  End    Start  End    Type                                
                    //  -----  -----  -----  -----  ------------------------------------
                    //  72     88     91     95     Ljava/lang/IllegalArgumentException;
                    //  77     106    109    113    Ljava/lang/IllegalArgumentException;
                    //  95     115    115    119    Ljava/lang/IllegalArgumentException;
                    // 
                    // The error that occurred was:
                    // 
                    // java.lang.IllegalStateException: Expression is linked from several locations: Label_0095:
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
                }, searchParameters.getEffectiveSearchScope(), name, (short)4, true);
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final String s = name;
                final boolean b = s.isEmpty();
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return instance.processElementsWithWord((p2, p3) -> {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: getstatic       com/intellij/psi/PsiReference.EMPTY_ARRAY:[Lcom/intellij/psi/PsiReference;
            //     3: astore          4
            //     5: aload_2        
            //     6: instanceof      Lcom/jetbrains/cidr/lang/psi/OCLiteralExpression;
            //     9: ifeq            23
            //    12: aload_2        
            //    13: invokeinterface com/intellij/psi/PsiElement.getReferences:()[Lcom/intellij/psi/PsiReference;
            //    18: astore          4
            //    20: goto            46
            //    23: aload_2        
            //    24: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMacroCall;
            //    27: ifeq            46
            //    30: iconst_1       
            //    31: anewarray       Lcom/intellij/psi/PsiReference;
            //    34: dup            
            //    35: iconst_0       
            //    36: aload_2        
            //    37: iload_3        
            //    38: invokeinterface com/intellij/psi/PsiElement.findReferenceAt:(I)Lcom/intellij/psi/PsiReference;
            //    43: aastore        
            //    44: astore          4
            //    46: aload           4
            //    48: astore          5
            //    50: aload           5
            //    52: arraylength    
            //    53: istore          6
            //    55: iconst_0       
            //    56: istore          7
            //    58: iload           7
            //    60: iload           6
            //    62: if_icmpge       125
            //    65: aload           5
            //    67: iload           7
            //    69: aaload         
            //    70: astore          8
            //    72: aload           8
            //    74: ifnull          119
            //    77: aload           8
            //    79: aload_0        
            //    80: invokeinterface com/intellij/psi/PsiReference.isReferenceTo:(Lcom/intellij/psi/PsiElement;)Z
            //    85: ifeq            119
            //    88: goto            95
            //    91: invokestatic    com/jetbrains/cidr/lang/search/OCLocalizedStringReferencesSearch.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    94: athrow         
            //    95: aload_1        
            //    96: aload           8
            //    98: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
            //   103: ifne            119
            //   106: goto            113
            //   109: invokestatic    com/jetbrains/cidr/lang/search/OCLocalizedStringReferencesSearch.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   112: athrow         
            //   113: iconst_0       
            //   114: ireturn        
            //   115: invokestatic    com/jetbrains/cidr/lang/search/OCLocalizedStringReferencesSearch.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   118: athrow         
            //   119: iinc            7, 1
            //   122: goto            58
            //   125: iconst_1       
            //   126: ireturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  72     88     91     95     Ljava/lang/IllegalArgumentException;
            //  77     106    109    113    Ljava/lang/IllegalArgumentException;
            //  95     115    115    119    Ljava/lang/IllegalArgumentException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0095:
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
        }, searchParameters.getEffectiveSearchScope(), name, (short)4, true);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
