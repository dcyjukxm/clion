// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing.tcatch;

import com.intellij.openapi.util.text.StringUtil;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.OCLiteralExpression;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.daemon.OCGetSymbolVisitor;
import com.jetbrains.cidr.lang.psi.OCCallExpression;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCArgumentList;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.psi.OCCppNamespace;
import java.util.Collections;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.psi.OCMacroCall;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.intellij.psi.PsiFile;
import java.util.Iterator;
import com.intellij.psi.PsiElementVisitor;
import com.jetbrains.cidr.lang.OCFileTypeHelpers;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.psi.PsiManager;
import java.util.function.Consumer;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.Collection;
import org.jetbrains.annotations.Contract;
import com.intellij.openapi.util.Pair;
import com.intellij.psi.search.SearchScope;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import com.intellij.openapi.util.Ref;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import java.util.regex.Pattern;
import java.util.Set;

public class CidrCatchTestUtil
{
    public static final String CATCH_HEADER_NAME = "catch.hpp";
    public static final String CATCH_ENABLE_DETECTION_KEY = "cidr.test.catch.enableDetection";
    public static final Set<String> CATCH_TEST_MACRO_NAMES;
    public static final String NULL_TAG_PRESENTATION_NAME = "non-tagged";
    public static final String NULL_TEST_PRESENTATION_NAME = "Anonymous test case *";
    private static final Pattern TAG;
    private static final Pattern TAGS_VALIDATION;
    
    @Nullable
    public static CidrCatchTestCache findCatchTestSymbol(@NotNull final String s, @NotNull final Project project, @Nullable final GlobalSearchScope globalSearchScope) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "topTestName", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestUtil", "findCatchTestSymbol"));
            }
        }
        catch (StopException ex) {
            throw b(ex);
        }
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestUtil", "findCatchTestSymbol"));
            }
        }
        catch (StopException ex2) {
            throw b(ex2);
        }
        final Ref ref = new Ref();
        final IllegalArgumentException ex3;
        final Ref ref2;
        consumeCatchTestSymbols(project, OCSearchScope.getExplicitlySpecifiedProjectSourceFiles(project), (SearchScope)globalSearchScope, cidrCatchTestCache -> {
            try {
                if (s == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "topTestName", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestUtil", "lambda$findCatchTestSymbol$0"));
                    throw ex3;
                }
            }
            catch (StopException ex4) {
                throw b(ex4);
            }
            try {
                if (s.equals(cidrCatchTestCache.getNotNullTestName())) {
                    ref2.set((Object)cidrCatchTestCache);
                    stopIteration();
                }
            }
            catch (StopException ex5) {
                throw b(ex5);
            }
            return;
        });
        return (CidrCatchTestCache)ref.get();
    }
    
    @NotNull
    @Contract(pure = true)
    public static Pair<String, Integer> splitPath(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "path", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestUtil", "splitPath"));
            }
        }
        catch (StopException ex) {
            throw b(ex);
        }
        final int lastIndex = s.lastIndexOf(58);
        Pair pair = null;
        Label_0089: {
            try {
                if (lastIndex < 0) {
                    final Pair pair2;
                    pair = (pair2 = Pair.create((Object)s, (Object)(-1)));
                    break Label_0089;
                }
            }
            catch (StopException ex2) {
                throw b(ex2);
            }
            Pair pair2;
            pair = (pair2 = Pair.create((Object)s.substring(0, lastIndex), (Object)Integer.valueOf(s.substring(lastIndex + 1))));
            try {
                if (pair2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestUtil", "splitPath"));
                }
            }
            catch (StopException ex3) {
                throw b(ex3);
            }
        }
        return (Pair<String, Integer>)pair;
    }
    
    @Contract(" -> fail")
    static void stopIteration() throws StopException {
        throw new StopException();
    }
    
    static boolean wasStoppedWhileIteration(final Runnable runnable) {
        try {
            runnable.run();
        }
        catch (StopException ex) {
            return true;
        }
        return false;
    }
    
    static void consumeCatchTestSymbols(@NotNull final Project project, @NotNull final Collection<VirtualFile> collection, @Nullable final SearchScope searchScope, @NotNull final Consumer<CidrCatchTestCache> consumer) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestUtil", "consumeCatchTestSymbols"));
            }
        }
        catch (StopException ex) {
            throw b(ex);
        }
        try {
            if (collection == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "potentialTestHolders", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestUtil", "consumeCatchTestSymbols"));
            }
        }
        catch (StopException ex2) {
            throw b(ex2);
        }
        try {
            if (consumer == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "consumer", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestUtil", "consumeCatchTestSymbols"));
            }
        }
        catch (StopException ex3) {
            throw b(ex3);
        }
        final PsiManager instance = PsiManager.getInstance(project);
        try {
            for (final VirtualFile virtualFile : collection) {
                Label_0191: {
                    try {
                        ProgressManager.checkCanceled();
                        if (searchScope == null) {
                            break Label_0191;
                        }
                        final SearchScope searchScope2 = searchScope;
                        final VirtualFile virtualFile2 = virtualFile;
                        final boolean b = searchScope2.contains(virtualFile2);
                        if (b) {
                            break Label_0191;
                        }
                        continue;
                    }
                    catch (StopException ex4) {
                        throw b(ex4);
                    }
                    try {
                        final SearchScope searchScope2 = searchScope;
                        final VirtualFile virtualFile2 = virtualFile;
                        final boolean b = searchScope2.contains(virtualFile2);
                        if (!b) {
                            continue;
                        }
                        if (!OCFileTypeHelpers.isSourceFile(virtualFile.getName())) {
                            continue;
                        }
                    }
                    catch (StopException ex5) {
                        throw b(ex5);
                    }
                }
                final PsiFile file = instance.findFile(virtualFile);
                try {
                    if (file == null) {
                        continue;
                    }
                    file.accept((PsiElementVisitor)getTestSymbolExtractorVisitor(consumer));
                }
                catch (StopException ex6) {
                    throw b(ex6);
                }
            }
        }
        catch (StopException ex7) {}
    }
    
    @Contract(pure = true)
    @NotNull
    protected static OCVisitor getTestSymbolExtractorVisitor(@NotNull final Consumer<CidrCatchTestCache> consumer) {
        try {
            if (consumer == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "declaratorConsumer", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestUtil", "getTestSymbolExtractorVisitor"));
            }
        }
        catch (StopException ex) {
            throw b(ex);
        }
        GlobalVisitor globalVisitor;
        try {
            globalVisitor = new GlobalVisitor(consumer);
            if (globalVisitor == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestUtil", "getTestSymbolExtractorVisitor"));
            }
        }
        catch (StopException ex2) {
            throw b(ex2);
        }
        return globalVisitor;
    }
    
    @Contract(pure = true)
    @NotNull
    protected static LocalVisitor getTestSymbolExtractorLocalVisitor(@NotNull final String s, @NotNull final Consumer<CidrCatchTestCache> consumer) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "testOwnerName", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestUtil", "getTestSymbolExtractorLocalVisitor"));
            }
        }
        catch (StopException ex) {
            throw b(ex);
        }
        try {
            if (consumer == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "declaratorConsumer", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestUtil", "getTestSymbolExtractorLocalVisitor"));
            }
        }
        catch (StopException ex2) {
            throw b(ex2);
        }
        LocalVisitor localVisitor;
        try {
            localVisitor = new LocalVisitor(s, consumer);
            if (localVisitor == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestUtil", "getTestSymbolExtractorLocalVisitor"));
            }
        }
        catch (StopException ex3) {
            throw b(ex3);
        }
        return localVisitor;
    }
    
    @Contract("null -> false")
    public static boolean isTestMacroCandidate(@Nullable final PsiElement psiElement) {
        if (psiElement instanceof OCMacroCall) {
            final OCReferenceElement macroReferenceElement = ((OCMacroCall)psiElement).getMacroReferenceElement();
            try {
                if (macroReferenceElement != null) {
                    return CidrCatchTestUtil.CATCH_TEST_MACRO_NAMES.contains(macroReferenceElement.getName());
                }
            }
            catch (StopException ex) {
                throw b(ex);
            }
        }
        return false;
    }
    
    @Contract("null -> true")
    public static boolean isValidStringOfTags(@Nullable final String s) {
        try {
            if (s == null) {
                return true;
            }
            final String s2 = s;
            final String s3 = "non-tagged";
            final boolean b = s2.equals(s3);
            if (b) {
                return true;
            }
            return CidrCatchTestUtil.TAGS_VALIDATION.matcher(s.trim()).matches();
        }
        catch (StopException ex) {
            throw b(ex);
        }
        try {
            final String s2 = s;
            final String s3 = "non-tagged";
            final boolean b = s2.equals(s3);
            if (b) {
                return true;
            }
        }
        catch (StopException ex2) {
            throw b(ex2);
        }
        return CidrCatchTestUtil.TAGS_VALIDATION.matcher(s.trim()).matches();
    }
    
    @NotNull
    public static Set<String> splitTags(@Nullable final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnull          20
        //     4: aload_0        
        //     5: ldc             "non-tagged"
        //     7: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    10: ifeq            71
        //    13: goto            20
        //    16: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestUtil.b:(Lcom/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestUtil$StopException;)Lcom/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestUtil$StopException;
        //    19: athrow         
        //    20: ldc             "non-tagged"
        //    22: invokestatic    java/util/Collections.singleton:(Ljava/lang/Object;)Ljava/util/Set;
        //    25: dup            
        //    26: ifnonnull       70
        //    29: goto            36
        //    32: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestUtil.b:(Lcom/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestUtil$StopException;)Lcom/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestUtil$StopException;
        //    35: athrow         
        //    36: new             Ljava/lang/IllegalStateException;
        //    39: dup            
        //    40: ldc             "@NotNull method %s.%s must not return null"
        //    42: ldc             2
        //    44: anewarray       Ljava/lang/Object;
        //    47: dup            
        //    48: ldc             0
        //    50: ldc             "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestUtil"
        //    52: aastore        
        //    53: dup            
        //    54: ldc             1
        //    56: ldc             "splitTags"
        //    58: aastore        
        //    59: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    62: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    65: athrow         
        //    66: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestUtil.b:(Lcom/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestUtil$StopException;)Lcom/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestUtil$StopException;
        //    69: athrow         
        //    70: areturn        
        //    71: new             Ljava/util/HashSet;
        //    74: dup            
        //    75: invokespecial   java/util/HashSet.<init>:()V
        //    78: astore_1       
        //    79: getstatic       com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestUtil.TAG:Ljava/util/regex/Pattern;
        //    82: aload_0        
        //    83: invokevirtual   java/util/regex/Pattern.matcher:(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
        //    86: astore_2       
        //    87: aload_2        
        //    88: invokevirtual   java/util/regex/Matcher.find:()Z
        //    91: ifeq            112
        //    94: aload_1        
        //    95: aload_2        
        //    96: invokevirtual   java/util/regex/Matcher.group:()Ljava/lang/String;
        //    99: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   104: pop            
        //   105: goto            87
        //   108: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestUtil.b:(Lcom/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestUtil$StopException;)Lcom/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestUtil$StopException;
        //   111: athrow         
        //   112: aload_1        
        //   113: dup            
        //   114: ifnonnull       151
        //   117: new             Ljava/lang/IllegalStateException;
        //   120: dup            
        //   121: ldc             "@NotNull method %s.%s must not return null"
        //   123: ldc             2
        //   125: anewarray       Ljava/lang/Object;
        //   128: dup            
        //   129: ldc             0
        //   131: ldc             "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestUtil"
        //   133: aastore        
        //   134: dup            
        //   135: ldc             1
        //   137: ldc             "splitTags"
        //   139: aastore        
        //   140: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   143: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   146: athrow         
        //   147: invokestatic    com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestUtil.b:(Lcom/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestUtil$StopException;)Lcom/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestUtil$StopException;
        //   150: athrow         
        //   151: areturn        
        //    Signature:
        //  (Ljava/lang/String;)Ljava/util/Set<Ljava/lang/String;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                                         
        //  -----  -----  -----  -----  -----------------------------------------------------------------------------
        //  0      13     16     20     Lcom/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestUtil$StopException;
        //  4      29     32     36     Lcom/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestUtil$StopException;
        //  20     66     66     70     Lcom/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestUtil$StopException;
        //  87     108    108    112    Lcom/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestUtil$StopException;
        //  112    147    147    151    Lcom/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestUtil$StopException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0020:
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
    
    public static boolean fileHasTest(@NotNull final OCFile ocFile) {
        try {
            if (ocFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "testFile", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestUtil", "fileHasTest"));
            }
        }
        catch (StopException ex) {
            throw b(ex);
        }
        final IllegalArgumentException ex2;
        return wasStoppedWhileIteration(() -> {
            try {
                if (ocFile == null) {
                    new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "testFile", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestUtil", "lambda$fileHasTest$2"));
                    throw ex2;
                }
            }
            catch (StopException ex3) {
                throw b(ex3);
            }
            ocFile.accept((PsiElementVisitor)getTestSymbolExtractorVisitor(p0 -> stopIteration()));
        });
    }
    
    public static boolean isTargetDetectionEnabled() {
        return Boolean.valueOf(System.getProperty("cidr.test.catch.enableDetection"));
    }
    
    public static boolean setTargetDetection(final boolean b) {
        return Boolean.valueOf(System.setProperty("cidr.test.catch.enableDetection", Boolean.toString(b)));
    }
    
    static {
        CATCH_TEST_MACRO_NAMES = Collections.unmodifiableSet((Set<? extends String>)ContainerUtil.newHashSet((Object[])new String[] { "SCENARIO", "SCENARIO_METHOD", "TEST_CASE", "TEST_CASE_METHOD", "METHOD_AS_TEST_CASE", "REGISTER_TEST_CASE", "ANON_TEST_CASE", "CATCH_SCENARIO", "CATCH_SCENARIO_METHOD", "CATCH_TEST_CASE", "CATCH_TEST_CASE_METHOD", "CATCH_METHOD_AS_TEST_CASE", "CATCH_REGISTER_TEST_CASE", "CATCH_ANON_TEST_CASE" }));
        TAG = Pattern.compile("(\\[[^\\[\\]]*\\])");
        TAGS_VALIDATION = Pattern.compile("(?:\\[[^\\[\\]]*\\]\\s*)*");
    }
    
    private static StopException b(final StopException ex) {
        return ex;
    }
    
    private static class GlobalVisitor extends OCVisitor
    {
        static final String AUTO_REG = "AutoReg";
        static final String NAME_AND_DESC = "NameAndDesc";
        protected final Consumer<CidrCatchTestCache> myDeclaratorConsumer;
        
        public GlobalVisitor(final Consumer<CidrCatchTestCache> myDeclaratorConsumer) {
            this.myDeclaratorConsumer = myDeclaratorConsumer;
        }
        
        public void visitElement(final PsiElement psiElement) {
            super.visitElement(psiElement);
            if (psiElement instanceof OCFile || psiElement instanceof OCCppNamespace) {
                for (PsiElement psiElement2 = psiElement.getFirstChild(); psiElement2 != null; psiElement2 = psiElement2.getNextSibling()) {
                    psiElement2.accept((PsiElementVisitor)this);
                }
            }
        }
        
        @Override
        public void visitDeclaration(final OCDeclaration ocDeclaration) {
            if ("AutoReg".equals(ocDeclaration.getType().getName())) {
                final OCArgumentList list = (OCArgumentList)PsiTreeUtil.findChildOfType((PsiElement)ocDeclaration, (Class)OCArgumentList.class);
                if (list != null) {
                    list.accept((PsiElementVisitor)this.createArgumentVisiter(ocDeclaration));
                }
            }
        }
        
        protected OCRecursiveVisitor createArgumentVisiter(final OCDeclaration ocDeclaration) {
            return new BaseArgVisitor(ocDeclaration, this.myDeclaratorConsumer);
        }
        
        static class BaseArgVisitor extends OCRecursiveVisitor
        {
            private OCDeclaration myDeclaration;
            private Consumer<CidrCatchTestCache> myDeclaratorConsumer;
            
            public BaseArgVisitor(final OCDeclaration myDeclaration, final Consumer<CidrCatchTestCache> myDeclaratorConsumer) {
                this.myDeclaration = myDeclaration;
                this.myDeclaratorConsumer = myDeclaratorConsumer;
            }
            
            @Override
            public void visitCallExpression(final OCCallExpression ocCallExpression) {
                final OCSymbol symbol = OCGetSymbolVisitor.getSymbol(ocCallExpression.getFunctionReferenceExpression());
                if (symbol != null && "NameAndDesc".equals(symbol.getName())) {
                    final List<OCExpression> arguments = ocCallExpression.getArgumentList().getArguments();
                    if (arguments.size() >= 1) {
                        final OCExpression ocExpression = arguments.get(0);
                        final OCExpression ocExpression2 = (arguments.size() > 1) ? arguments.get(1) : null;
                        final List<OCDeclarator> declarators = this.myDeclaration.getDeclarators();
                        if (ocExpression instanceof OCLiteralExpression && (ocExpression2 == null || ocExpression2 instanceof OCLiteralExpression) && declarators.size() == 1) {
                            this.myDeclaratorConsumer.accept(new CidrCatchTestCache(((OCLiteralExpression)ocExpression).getUnescapedLiteralText(), (ocExpression2 == null) ? "" : ((OCLiteralExpression)ocExpression2).getUnescapedLiteralText(), declarators.get(0)));
                        }
                    }
                }
            }
        }
    }
    
    private static class LocalVisitor extends GlobalVisitor
    {
        final String myStructName;
        final String myFunctionTestName;
        
        public LocalVisitor(@NotNull final String s, final Consumer<CidrCatchTestCache> consumer) {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "testOwnerName", "com/jetbrains/cidr/execution/testing/tcatch/CidrCatchTestUtil$LocalVisitor", "<init>"));
            }
            super(consumer);
            final List split = StringUtil.split(s, "::");
            try {
                if (split.size() == 2) {
                    this.myStructName = split.get(0);
                    this.myFunctionTestName = split.get(1);
                    return;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (split.size() == 1) {
                    this.myStructName = null;
                    this.myFunctionTestName = split.get(0);
                    return;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            this.myStructName = null;
            this.myFunctionTestName = null;
        }
        
        @Override
        protected OCRecursiveVisitor createArgumentVisiter(final OCDeclaration ocDeclaration) {
            return new BaseArgVisitor(ocDeclaration, this.myDeclaratorConsumer) {
                boolean found = false;
                
                @Override
                public void visitReferenceElement(final OCReferenceElement ocReferenceElement) {
                    final PsiElement nameIdentifier = ocReferenceElement.getNameIdentifier();
                    if (nameIdentifier != null && nameIdentifier.getText().equals(LocalVisitor.this.myFunctionTestName)) {
                        if (LocalVisitor.this.myStructName == null) {
                            this.found = true;
                        }
                        else if (ocReferenceElement.getNamespaceQualifier() != null && ocReferenceElement.getNamespaceQualifier().getName() != null) {
                            this.found = ocReferenceElement.getNamespaceQualifier().getName().equals(LocalVisitor.this.myStructName);
                        }
                    }
                }
                
                @Override
                public void visitCallExpression(final OCCallExpression ocCallExpression) {
                    if (this.found) {
                        super.visitCallExpression(ocCallExpression);
                    }
                }
            };
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    private static class StopException extends RuntimeException
    {
        public StopException() {
            super("catch stop iteration", null, false, false);
        }
    }
}
