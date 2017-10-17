// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.search;

import com.intellij.lang.cacheBuilder.WordOccurrence;
import com.intellij.util.Processor;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.cacheBuilder.DefaultWordsScanner;
import com.intellij.psi.PsiNamedElement;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeArgument;
import com.jetbrains.cidr.cpp.CPPBundle;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeCommandNameMixin;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;
import com.intellij.lexer.Lexer;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeElementTypes;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeLexer;
import com.intellij.lang.cacheBuilder.WordsScanner;
import com.intellij.lang.findUsages.FindUsagesProvider;

public class CMakeFindUsagesProvider implements FindUsagesProvider
{
    @Nullable
    public WordsScanner getWordsScanner() {
        return (WordsScanner)new DollarSignSplittingScanner((Lexer)new CMakeLexer(), CMakeElementTypes.IDENTIFIERS, CMakeElementTypes.COMMENTS, CMakeElementTypes.LITERALS);
    }
    
    public boolean canFindUsagesFor(@NotNull final PsiElement p0) {
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
        //    18: ldc             "psiElement"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/cpp/cmake/search/CMakeFindUsagesProvider"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "canFindUsagesFor"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/cpp/cmake/search/CMakeFindUsagesProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: instanceof      Lcom/intellij/psi/PsiNamedElement;
        //    48: ifne            99
        //    51: aload_1        
        //    52: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
        //    57: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //    62: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.ID:Lcom/intellij/psi/tree/IElementType;
        //    65: if_acmpeq       99
        //    68: goto            75
        //    71: invokestatic    com/jetbrains/cidr/cpp/cmake/search/CMakeFindUsagesProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    74: athrow         
        //    75: aload_1        
        //    76: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
        //    81: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //    86: getstatic       com/jetbrains/cidr/cpp/cmake/psi/CMakeTokenTypes.LITERAL:Lcom/intellij/psi/tree/IElementType;
        //    89: if_acmpne       107
        //    92: goto            99
        //    95: invokestatic    com/jetbrains/cidr/cpp/cmake/search/CMakeFindUsagesProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    98: athrow         
        //    99: iconst_1       
        //   100: goto            108
        //   103: invokestatic    com/jetbrains/cidr/cpp/cmake/search/CMakeFindUsagesProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   106: athrow         
        //   107: iconst_0       
        //   108: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     68     71     75     Ljava/lang/IllegalArgumentException;
        //  51     92     95     99     Ljava/lang/IllegalArgumentException;
        //  75     103    103    107    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0075:
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
    public String getHelpId(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "psiElement", "com/jetbrains/cidr/cpp/cmake/search/CMakeFindUsagesProvider", "getHelpId"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return "reference.dialogs.findUsages.other";
    }
    
    @NotNull
    public String getType(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/cpp/cmake/search/CMakeFindUsagesProvider", "getType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        String message = null;
        Label_0168: {
            String s4 = null;
            Label_0133: {
                Label_0106: {
                    String s2 = null;
                    Label_0071: {
                        try {
                            if (!(psiElement instanceof CMakeCommandNameMixin)) {
                                break Label_0106;
                            }
                            final String s = "cmake.command.name";
                            final int n = 0;
                            final Object[] array = new Object[n];
                            s2 = CPPBundle.message(s, array);
                            if (s2 == null) {
                                break Label_0071;
                            }
                            return s2;
                        }
                        catch (IllegalArgumentException ex2) {
                            throw a(ex2);
                        }
                        try {
                            final String s = "cmake.command.name";
                            final int n = 0;
                            final Object[] array = new Object[n];
                            s2 = CPPBundle.message(s, array);
                            if (s2 == null) {
                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/search/CMakeFindUsagesProvider", "getType"));
                            }
                        }
                        catch (IllegalArgumentException ex3) {
                            throw a(ex3);
                        }
                    }
                    return s2;
                    try {
                        if (!(psiElement instanceof CMakeArgument)) {
                            break Label_0168;
                        }
                        final String s3 = "cmake.argument.command.name";
                        final int n2 = 0;
                        final Object[] array2 = new Object[n2];
                        s4 = CPPBundle.message(s3, array2);
                        if (s4 == null) {
                            break Label_0133;
                        }
                        return s4;
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                }
                try {
                    final String s3 = "cmake.argument.command.name";
                    final int n2 = 0;
                    final Object[] array2 = new Object[n2];
                    s4 = CPPBundle.message(s3, array2);
                    if (s4 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/search/CMakeFindUsagesProvider", "getType"));
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
            }
            return s4;
            try {
                message = CPPBundle.message("cmake.search.element", new Object[0]);
                if (message == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/search/CMakeFindUsagesProvider", "getType"));
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
        }
        return message;
    }
    
    @NotNull
    public String getDescriptiveName(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/cpp/cmake/search/CMakeFindUsagesProvider", "getDescriptiveName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        String string = null;
        Label_0135: {
            if (psiElement instanceof PsiNamedElement) {
                final String name = ((PsiNamedElement)psiElement).getName();
                String s2 = null;
                Label_0100: {
                    try {
                        if (name == null) {
                            break Label_0135;
                        }
                        final StringBuilder sb = new StringBuilder();
                        final char c = '\'';
                        final StringBuilder sb2 = sb.append(c);
                        final String s = name;
                        final StringBuilder sb3 = sb2.append(s);
                        final char c2 = '\'';
                        final StringBuilder sb4 = sb3.append(c2);
                        s2 = sb4.toString();
                        if (s2 == null) {
                            break Label_0100;
                        }
                        return s2;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    try {
                        final StringBuilder sb = new StringBuilder();
                        final char c = '\'';
                        final StringBuilder sb2 = sb.append(c);
                        final String s = name;
                        final StringBuilder sb3 = sb2.append(s);
                        final char c2 = '\'';
                        final StringBuilder sb4 = sb3.append(c2);
                        s2 = sb4.toString();
                        if (s2 == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/search/CMakeFindUsagesProvider", "getDescriptiveName"));
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                }
                return s2;
            }
            try {
                string = '\'' + psiElement.getText() + '\'';
                if (string == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/search/CMakeFindUsagesProvider", "getDescriptiveName"));
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return string;
    }
    
    @NotNull
    public String getNodeText(@NotNull final PsiElement psiElement, final boolean b) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/cpp/cmake/search/CMakeFindUsagesProvider", "getNodeText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        String descriptiveName;
        try {
            descriptiveName = this.getDescriptiveName(psiElement);
            if (descriptiveName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/search/CMakeFindUsagesProvider", "getNodeText"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return descriptiveName;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    private static class DollarSignSplittingScanner extends DefaultWordsScanner
    {
        public DollarSignSplittingScanner(final Lexer lexer, final TokenSet set, final TokenSet set2, final TokenSet set3) {
            this(lexer, set, set2, set3, TokenSet.EMPTY);
        }
        
        public DollarSignSplittingScanner(final Lexer lexer, final TokenSet set, final TokenSet set2, final TokenSet set3, final TokenSet set4) {
            super(lexer, set, set2, set3, set4);
        }
        
        public void processWords(final CharSequence charSequence, final Processor<WordOccurrence> processor) {
            super.processWords(charSequence, wordOccurrence -> {
                final CharSequence subSequence = wordOccurrence.getBaseText().subSequence(wordOccurrence.getStart(), wordOccurrence.getEnd());
                if (!processor.process((Object)wordOccurrence)) {
                    return false;
                }
                int start = wordOccurrence.getStart();
                for (int i = 0; i < subSequence.length(); ++i) {
                    if (subSequence.charAt(i) == '$') {
                        if (wordOccurrence.getBaseText().subSequence(start, wordOccurrence.getStart() + i).length() > 0 && !processor.process((Object)new WordOccurrence(wordOccurrence.getBaseText(), start, wordOccurrence.getStart() + i, wordOccurrence.getKind()))) {
                            return false;
                        }
                        start = i + 1;
                    }
                }
                return start == wordOccurrence.getStart() || wordOccurrence.getBaseText().subSequence(wordOccurrence.getStart() + start, wordOccurrence.getEnd()).length() <= 0 || processor.process((Object)new WordOccurrence(wordOccurrence.getBaseText(), wordOccurrence.getStart() + start, wordOccurrence.getEnd(), wordOccurrence.getKind()));
            });
        }
    }
}
