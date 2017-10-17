// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.doxygen;

import com.jetbrains.cidr.lang.psi.OCParameterDeclaration;
import com.jetbrains.cidr.lang.psi.OCTypeParameterDeclaration;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.psi.OCTemplateParameterList;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.intellij.psi.PsiNamedElement;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.Iterator;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.PsiDocCommentBase;
import com.jetbrains.cidr.lang.psi.OCCallable;
import org.jetbrains.annotations.Nullable;
import java.util.List;
import com.jetbrains.cidr.doxygen.psi.DxFile;
import com.intellij.openapi.util.Pair;
import com.intellij.psi.PsiElement;
import com.intellij.lang.injection.InjectedLanguageManager;
import com.jetbrains.cidr.doxygen.psi.DxDocComment;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiComment;

public class DoxygenUtil
{
    public static final String DOCUMENTATION_COMMENT_PREFIX = "/**";
    public static final String DOCUMENTATION_COMMENT_PREFIX2 = "/*!";
    public static final String BLOCK_COMMENT_SUFFIX = "*/";
    public static final String EOL_DOCUMENTATION_COMMENT_PREFIX = "///";
    public static final String EOL_DOCUMENTATION_COMMENT_PREFIX2 = "//!";
    public static final String ELLIPSIS = "...";
    
    public static boolean isDoxygenComment(@NotNull final PsiComment psiComment) {
        try {
            if (psiComment == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "comment", "com/jetbrains/cidr/doxygen/DoxygenUtil", "isDoxygenComment"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0065: {
            try {
                if (isDoxygenBlockComment(psiComment)) {
                    break Label_0065;
                }
                final PsiComment psiComment2 = psiComment;
                final boolean b = isDoxygenEOLComment(psiComment2);
                if (b) {
                    break Label_0065;
                }
                return false;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final PsiComment psiComment2 = psiComment;
                final boolean b = isDoxygenEOLComment(psiComment2);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return false;
    }
    
    public static boolean isDoxygenBlockComment(@NotNull final PsiComment psiComment) {
        try {
            if (psiComment == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "comment", "com/jetbrains/cidr/doxygen/DoxygenUtil", "isDoxygenBlockComment"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final String text = psiComment.getText();
        try {
            if (text.equals("/**/")) {
                return false;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        Label_0091: {
            try {
                if (text.startsWith("/**")) {
                    break Label_0091;
                }
                final String s = text;
                final String s2 = "/*!";
                final boolean b = s.startsWith(s2);
                if (b) {
                    break Label_0091;
                }
                return false;
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                final String s = text;
                final String s2 = "/*!";
                final boolean b = s.startsWith(s2);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return false;
    }
    
    public static boolean isDoxygenEOLComment(@NotNull final PsiComment psiComment) {
        try {
            if (psiComment == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "comment", "com/jetbrains/cidr/doxygen/DoxygenUtil", "isDoxygenEOLComment"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0079: {
            try {
                if (psiComment.getText().startsWith("///")) {
                    break Label_0079;
                }
                final PsiComment psiComment2 = psiComment;
                final String s = psiComment2.getText();
                final String s2 = "//!";
                final boolean b = s.startsWith(s2);
                if (b) {
                    break Label_0079;
                }
                return false;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final PsiComment psiComment2 = psiComment;
                final String s = psiComment2.getText();
                final String s2 = "//!";
                final boolean b = s.startsWith(s2);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return false;
    }
    
    public static boolean hasArrow(@NotNull final PsiComment psiComment) {
        try {
            if (psiComment == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "comment", "com/jetbrains/cidr/doxygen/DoxygenUtil", "hasArrow"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final String text = psiComment.getText();
        Label_0076: {
            try {
                if (text.length() <= 3) {
                    return false;
                }
                final String s = text;
                final int n = 3;
                final char c = s.charAt(n);
                final char c2 = '<';
                if (c == c2) {
                    break Label_0076;
                }
                return false;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final String s = text;
                final int n = 3;
                final char c = s.charAt(n);
                final char c2 = '<';
                if (c == c2) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return false;
    }
    
    @Nullable
    public static DxDocComment convertToDoxygen(@NotNull final PsiComment psiComment) {
        try {
            if (psiComment == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "comment", "com/jetbrains/cidr/doxygen/DoxygenUtil", "convertToDoxygen"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final List injectedPsiFiles = InjectedLanguageManager.getInstance(psiComment.getProject()).getInjectedPsiFiles((PsiElement)psiComment);
        try {
            if (injectedPsiFiles == null || injectedPsiFiles.isEmpty()) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final PsiElement psiElement = (PsiElement)injectedPsiFiles.get(0).first;
        if (psiElement instanceof DxFile) {
            final PsiElement firstChild = psiElement.getFirstChild();
            try {
                if (firstChild instanceof DxDocComment) {
                    return (DxDocComment)firstChild;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return null;
    }
    
    @Nullable
    public static PsiComment getHostComment(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "dxPsiElement", "com/jetbrains/cidr/doxygen/DoxygenUtil", "getHostComment"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (PsiComment)InjectedLanguageManager.getInstance(psiElement.getProject()).getInjectionHost(psiElement);
    }
    
    @Nullable
    public static OCCallable<?> findFunction(@Nullable final PsiComment psiComment) {
        if (psiComment instanceof PsiDocCommentBase) {
            final PsiElement owner = ((PsiDocCommentBase)psiComment).getOwner();
            try {
                if (owner instanceof OCCallable) {
                    return (OCCallable<?>)owner;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        return null;
    }
    
    public static boolean hasNextDocCommentSibling(@Nullable final PsiComment psiComment) {
        if (psiComment != null) {
            PsiElement psiElement = psiComment.getNextSibling();
            while (true) {
                Label_0127: {
                    Label_0066: {
                        Label_0036: {
                            Label_0029: {
                                try {
                                    if (psiElement == null) {
                                        break;
                                    }
                                    final PsiElement psiElement2 = psiElement;
                                    final boolean b = psiElement2 instanceof PsiWhiteSpace;
                                    if (b) {
                                        break Label_0029;
                                    }
                                    break Label_0036;
                                }
                                catch (IllegalArgumentException ex) {
                                    throw a(ex);
                                }
                                try {
                                    final PsiElement psiElement2 = psiElement;
                                    final boolean b = psiElement2 instanceof PsiWhiteSpace;
                                    if (b) {
                                        break Label_0127;
                                    }
                                }
                                catch (IllegalArgumentException ex2) {
                                    throw a(ex2);
                                }
                            }
                            try {
                                if (!(psiElement instanceof PsiComment)) {
                                    break Label_0066;
                                }
                                final PsiElement psiElement3 = psiElement;
                                final PsiComment psiComment2 = (PsiComment)psiElement3;
                                final boolean b2 = isDoxygenComment(psiComment2);
                                if (b2) {
                                    return true;
                                }
                                break Label_0127;
                            }
                            catch (IllegalArgumentException ex3) {
                                throw a(ex3);
                            }
                        }
                        try {
                            final PsiElement psiElement3 = psiElement;
                            final PsiComment psiComment2 = (PsiComment)psiElement3;
                            final boolean b2 = isDoxygenComment(psiComment2);
                            if (b2) {
                                return true;
                            }
                            break Label_0127;
                        }
                        catch (IllegalArgumentException ex4) {
                            throw a(ex4);
                        }
                    }
                    if (psiElement instanceof OCCallable) {
                        for (final PsiComment psiComment3 : PsiTreeUtil.getChildrenOfTypeAsList(psiElement, (Class)PsiComment.class)) {
                            try {
                                if (isDoxygenComment(psiComment3)) {
                                    return true;
                                }
                                continue;
                            }
                            catch (IllegalArgumentException ex5) {
                                throw a(ex5);
                            }
                        }
                        break;
                    }
                    break;
                }
                psiElement = psiElement.getNextSibling();
            }
        }
        return false;
    }
    
    public static void traverseTemplateParametersList(@Nullable final PsiElement psiElement, @NotNull final Consumer<String> consumer) {
        try {
            if (consumer == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/doxygen/DoxygenUtil", "traverseTemplateParametersList"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final IllegalArgumentException ex2;
        traverseTemplateParametersList(psiElement, (p1, s) -> {
            try {
                if (consumer == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/doxygen/DoxygenUtil", "lambda$traverseTemplateParametersList$0"));
                    throw ex2;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            consumer.accept(s);
            return true;
        });
    }
    
    @Nullable
    public static PsiNamedElement traverseTemplateParametersList(@Nullable final PsiElement psiElement, @NotNull final BiFunction<PsiNamedElement, String, Boolean> biFunction) {
        try {
            if (biFunction == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "f", "com/jetbrains/cidr/doxygen/DoxygenUtil", "traverseTemplateParametersList"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        if (psiElement instanceof OCDeclaration) {
            final Iterator iterator = PsiTreeUtil.getChildrenOfTypeAsList(psiElement, (Class)OCTemplateParameterList.class).iterator();
            while (iterator.hasNext()) {
                for (final OCElement ocElement : iterator.next().getParameters()) {
                    String s = null;
                    Object declarator = null;
                    if (ocElement instanceof OCTypeParameterDeclaration) {
                        declarator = ocElement;
                        s = ((PsiNamedElement)declarator).getName();
                    }
                    else if (ocElement instanceof OCParameterDeclaration) {
                        declarator = ((OCParameterDeclaration)ocElement).getDeclarator();
                        if (declarator != null) {
                            s = ((PsiNamedElement)declarator).getName();
                        }
                    }
                    try {
                        if (!biFunction.apply((PsiNamedElement)declarator, getName(s, (PsiNamedElement)declarator))) {
                            return (PsiNamedElement)declarator;
                        }
                        continue;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
            }
        }
        return null;
    }
    
    @NotNull
    public static String getName(@Nullable final String p0, @Nullable final PsiNamedElement p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnull          81
        //     4: aload_0        
        //     5: invokevirtual   java/lang/String.isEmpty:()Z
        //     8: ifne            81
        //    11: goto            18
        //    14: invokestatic    com/jetbrains/cidr/doxygen/DoxygenUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    17: athrow         
        //    18: ldc             "<unnamed>"
        //    20: aload_0        
        //    21: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    24: ifne            81
        //    27: goto            34
        //    30: invokestatic    com/jetbrains/cidr/doxygen/DoxygenUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    33: athrow         
        //    34: aload_0        
        //    35: dup            
        //    36: ifnonnull       80
        //    39: goto            46
        //    42: invokestatic    com/jetbrains/cidr/doxygen/DoxygenUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    45: athrow         
        //    46: new             Ljava/lang/IllegalStateException;
        //    49: dup            
        //    50: ldc             "@NotNull method %s.%s must not return null"
        //    52: ldc             2
        //    54: anewarray       Ljava/lang/Object;
        //    57: dup            
        //    58: ldc             0
        //    60: ldc             "com/jetbrains/cidr/doxygen/DoxygenUtil"
        //    62: aastore        
        //    63: dup            
        //    64: ldc             1
        //    66: ldc             "getName"
        //    68: aastore        
        //    69: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    72: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    75: athrow         
        //    76: invokestatic    com/jetbrains/cidr/doxygen/DoxygenUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    79: athrow         
        //    80: areturn        
        //    81: aload_1        
        //    82: ifnull          154
        //    85: aload_1        
        //    86: invokeinterface com/intellij/psi/PsiNamedElement.getText:()Ljava/lang/String;
        //    91: ldc             "..."
        //    93: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    96: ifeq            154
        //    99: goto            106
        //   102: invokestatic    com/jetbrains/cidr/doxygen/DoxygenUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   105: athrow         
        //   106: ldc             "..."
        //   108: dup            
        //   109: ifnonnull       153
        //   112: goto            119
        //   115: invokestatic    com/jetbrains/cidr/doxygen/DoxygenUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   118: athrow         
        //   119: new             Ljava/lang/IllegalStateException;
        //   122: dup            
        //   123: ldc             "@NotNull method %s.%s must not return null"
        //   125: ldc             2
        //   127: anewarray       Ljava/lang/Object;
        //   130: dup            
        //   131: ldc             0
        //   133: ldc             "com/jetbrains/cidr/doxygen/DoxygenUtil"
        //   135: aastore        
        //   136: dup            
        //   137: ldc             1
        //   139: ldc             "getName"
        //   141: aastore        
        //   142: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   145: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   148: athrow         
        //   149: invokestatic    com/jetbrains/cidr/doxygen/DoxygenUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   152: athrow         
        //   153: areturn        
        //   154: aload_1        
        //   155: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSymbolDeclarator;
        //   158: ifeq            245
        //   161: aload_1        
        //   162: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSymbolDeclarator;
        //   165: invokeinterface com/jetbrains/cidr/lang/psi/OCSymbolDeclarator.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   170: astore_2       
        //   171: aload_2        
        //   172: instanceof      Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;
        //   175: ifeq            245
        //   178: aload_2        
        //   179: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;
        //   182: invokeinterface com/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol.isVariadic:()Z
        //   187: ifeq            245
        //   190: goto            197
        //   193: invokestatic    com/jetbrains/cidr/doxygen/DoxygenUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   196: athrow         
        //   197: ldc             "..."
        //   199: dup            
        //   200: ifnonnull       244
        //   203: goto            210
        //   206: invokestatic    com/jetbrains/cidr/doxygen/DoxygenUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   209: athrow         
        //   210: new             Ljava/lang/IllegalStateException;
        //   213: dup            
        //   214: ldc             "@NotNull method %s.%s must not return null"
        //   216: ldc             2
        //   218: anewarray       Ljava/lang/Object;
        //   221: dup            
        //   222: ldc             0
        //   224: ldc             "com/jetbrains/cidr/doxygen/DoxygenUtil"
        //   226: aastore        
        //   227: dup            
        //   228: ldc             1
        //   230: ldc             "getName"
        //   232: aastore        
        //   233: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   236: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   239: athrow         
        //   240: invokestatic    com/jetbrains/cidr/doxygen/DoxygenUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   243: athrow         
        //   244: areturn        
        //   245: ldc             "<unnamed>"
        //   247: dup            
        //   248: ifnonnull       285
        //   251: new             Ljava/lang/IllegalStateException;
        //   254: dup            
        //   255: ldc             "@NotNull method %s.%s must not return null"
        //   257: ldc             2
        //   259: anewarray       Ljava/lang/Object;
        //   262: dup            
        //   263: ldc             0
        //   265: ldc             "com/jetbrains/cidr/doxygen/DoxygenUtil"
        //   267: aastore        
        //   268: dup            
        //   269: ldc             1
        //   271: ldc             "getName"
        //   273: aastore        
        //   274: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   277: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   280: athrow         
        //   281: invokestatic    com/jetbrains/cidr/doxygen/DoxygenUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   284: athrow         
        //   285: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      11     14     18     Ljava/lang/IllegalArgumentException;
        //  4      27     30     34     Ljava/lang/IllegalArgumentException;
        //  18     39     42     46     Ljava/lang/IllegalArgumentException;
        //  34     76     76     80     Ljava/lang/IllegalArgumentException;
        //  81     99     102    106    Ljava/lang/IllegalArgumentException;
        //  85     112    115    119    Ljava/lang/IllegalArgumentException;
        //  106    149    149    153    Ljava/lang/IllegalArgumentException;
        //  171    190    193    197    Ljava/lang/IllegalArgumentException;
        //  178    203    206    210    Ljava/lang/IllegalArgumentException;
        //  197    240    240    244    Ljava/lang/IllegalArgumentException;
        //  245    281    281    285    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0018:
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
