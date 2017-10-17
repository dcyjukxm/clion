// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.inspections;

import com.intellij.codeInsight.highlighting.ReadWriteAccessDetector;
import com.jetbrains.cidr.lang.search.usages.OCReadWriteAccessDetector;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.jetbrains.cidr.lang.psi.OCSynthesizeProperty;
import com.jetbrains.cidr.lang.symbols.OCSymbolHolderVirtualPsiElement;
import com.intellij.util.ExecutorsQuery;
import java.util.Collections;
import com.jetbrains.cidr.lang.search.OCPropertyReferencesSearch;
import com.intellij.psi.search.searches.ReferencesSearch;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.util.OCCommonProcessors;
import com.intellij.openapi.util.Conditions;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.util.Ref;
import com.intellij.util.CommonProcessors;
import com.intellij.psi.PsiReference;
import com.intellij.util.Query;
import java.util.List;
import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.codeInspection.ProblemHighlightType;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.intellij.psi.PsiElement;
import com.intellij.psi.search.SearchScope;
import com.intellij.codeInspection.GlobalInspectionContext;
import com.intellij.codeInspection.ProblemDescriptionsProcessor;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import com.intellij.codeInspection.ProblemsHolder;
import org.jetbrains.annotations.Nullable;

public abstract class OCUnusedCodeInspection extends Cpp
{
    @Nullable
    public UnusedVisitor buildVisitor() {
        return null;
    }
    
    @NotNull
    public PsiElementVisitor buildVisitor(@NotNull final ProblemsHolder problemsHolder, final boolean b) {
        try {
            if (problemsHolder == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "holder", "com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection", "buildVisitor"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCVisitor ocVisitor = null;
        Label_0114: {
            if (b) {
                final UnusedVisitor buildVisitor = this.buildVisitor();
                UnusedVisitor unusedVisitor3 = null;
                Label_0079: {
                    try {
                        if (buildVisitor == null) {
                            break Label_0114;
                        }
                        final UnusedVisitor unusedVisitor = buildVisitor;
                        final ProblemsHolder problemsHolder2 = problemsHolder;
                        unusedVisitor.setHolder(problemsHolder2);
                        final UnusedVisitor unusedVisitor2 = buildVisitor;
                        final boolean b2 = b;
                        unusedVisitor2.setOnTheFly(b2);
                        unusedVisitor3 = buildVisitor;
                        if (unusedVisitor3 == null) {
                            break Label_0079;
                        }
                        return unusedVisitor3;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    try {
                        final UnusedVisitor unusedVisitor = buildVisitor;
                        final ProblemsHolder problemsHolder2 = problemsHolder;
                        unusedVisitor.setHolder(problemsHolder2);
                        final UnusedVisitor unusedVisitor2 = buildVisitor;
                        final boolean b2 = b;
                        unusedVisitor2.setOnTheFly(b2);
                        unusedVisitor3 = buildVisitor;
                        if (unusedVisitor3 == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection", "buildVisitor"));
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                }
                return unusedVisitor3;
            }
            try {
                ocVisitor = new OCVisitor();
                if (ocVisitor == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection", "buildVisitor"));
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return ocVisitor;
    }
    
    public static boolean isWritableSymbol(final OCSymbol p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //     4: ifne            35
        //     7: aload_0        
        //     8: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //    11: ifne            35
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    20: athrow         
        //    21: aload_0        
        //    22: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //    25: ifeq            43
        //    28: goto            35
        //    31: invokestatic    com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    34: athrow         
        //    35: iconst_1       
        //    36: goto            44
        //    39: invokestatic    com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    42: athrow         
        //    43: iconst_0       
        //    44: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      14     17     21     Ljava/lang/IllegalArgumentException;
        //  7      28     31     35     Ljava/lang/IllegalArgumentException;
        //  21     39     39     43     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0021:
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
    
    @Override
    public String getMainToolId() {
        final String mainToolId = super.getMainToolId();
        try {
            if (mainToolId != null) {
                return mainToolId;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return new OCGlobalUnusedInspection().getShortName();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    public class UnusedVisitor extends OCVisitor
    {
        private OCSymbol mySymbol;
        protected boolean myOnTheFly;
        protected boolean myHasWrites;
        @Nullable
        private ProblemsHolder myHolder;
        @Nullable
        private ProblemDescriptionsProcessor myProcessor;
        @Nullable
        private GlobalInspectionContext myGlobalContext;
        @Nullable
        protected SearchScope myScope;
        
        public void setUpForBatchMode(final ProblemDescriptionsProcessor myProcessor, final GlobalInspectionContext myGlobalContext, final SearchScope myScope) {
            this.myProcessor = myProcessor;
            this.myGlobalContext = myGlobalContext;
            this.myScope = myScope;
            this.myOnTheFly = false;
        }
        
        public void checkFromBatchMode(@NotNull final OCSymbol mySymbol, @NotNull final PsiElement psiElement, final boolean myHasWrites) {
            try {
                if (mySymbol == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor", "checkFromBatchMode"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (psiElement == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "declarator", "com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor", "checkFromBatchMode"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            this.mySymbol = mySymbol;
            this.myHasWrites = myHasWrites;
            psiElement.accept((PsiElementVisitor)this);
        }
        
        public void setHolder(@Nullable final ProblemsHolder myHolder) {
            this.myHolder = myHolder;
        }
        
        public void setOnTheFly(final boolean myOnTheFly) {
            this.myOnTheFly = myOnTheFly;
        }
        
        @Nullable
        protected OCSymbol getSymbol(final OCSymbolDeclarator ocSymbolDeclarator) {
            try {
                if (this.mySymbol != null) {
                    return this.mySymbol;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return ocSymbolDeclarator.getSymbol();
        }
        
        protected void registerProblem(@Nullable final PsiElement psiElement, final String s, final ProblemHighlightType problemHighlightType, final IntentionAction... array) {
            OCUnusedCodeInspection.this.registerProblem(this.myHolder, this.myProcessor, this.myGlobalContext, this.myOnTheFly, psiElement, s, "CIDR", problemHighlightType, array);
        }
        
        protected void registerProblems(@Nullable final List<PsiElement> list, final String s, final ProblemHighlightType problemHighlightType, final IntentionAction... array) {
            OCUnusedCodeInspection.this.registerProblems(this.myHolder, this.myProcessor, this.myGlobalContext, this.myOnTheFly, list, s, "CIDR", problemHighlightType, array);
        }
        
        protected void checkSymbolUsed(final PsiElement psiElement, final OCSymbol ocSymbol) {
            this.checkSymbolUsed(psiElement, psiElement, ocSymbol, false);
        }
        
        protected void checkSymbolUsed(final PsiElement psiElement, final OCSymbol ocSymbol, final boolean b) {
            this.checkSymbolUsed(psiElement, psiElement, ocSymbol, b);
        }
        
        protected void checkSymbolUsed(final PsiElement p0, final PsiElement p1, final OCSymbol p2, final boolean p3) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_1        
            //     1: ifnull          15
            //     4: aload_2        
            //     5: ifnonnull       20
            //     8: goto            15
            //    11: invokestatic    com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    14: athrow         
            //    15: return         
            //    16: invokestatic    com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    19: athrow         
            //    20: aload_1        
            //    21: invokeinterface com/intellij/psi/PsiElement.getProject:()Lcom/intellij/openapi/project/Project;
            //    26: getstatic       com/jetbrains/cidr/lang/psi/OCFile.UNUSED_CHECKS:Lcom/intellij/openapi/util/Key;
            //    29: invokeinterface com/intellij/openapi/project/Project.getUserData:(Lcom/intellij/openapi/util/Key;)Ljava/lang/Object;
            //    34: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile$UnusedChecksMode;
            //    37: astore          5
            //    39: aload_3        
            //    40: ifnull          94
            //    43: aload           5
            //    45: getstatic       com/jetbrains/cidr/lang/psi/OCFile$UnusedChecksMode.DISABLED:Lcom/jetbrains/cidr/lang/psi/OCFile$UnusedChecksMode;
            //    48: if_acmpeq       94
            //    51: goto            58
            //    54: invokestatic    com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    57: athrow         
            //    58: aload_3        
            //    59: ldc             "unused"
            //    61: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.hasAttribute:(Ljava/lang/String;)Z
            //    66: ifne            94
            //    69: goto            76
            //    72: invokestatic    com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    75: athrow         
            //    76: aload_3        
            //    77: ldc             "used"
            //    79: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.hasAttribute:(Ljava/lang/String;)Z
            //    84: ifeq            99
            //    87: goto            94
            //    90: invokestatic    com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    93: athrow         
            //    94: return         
            //    95: invokestatic    com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    98: athrow         
            //    99: aload_3        
            //   100: invokestatic    com/jetbrains/cidr/lang/search/scopes/OCSearchScope.isInProjectSources:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
            //   103: ifne            111
            //   106: return         
            //   107: invokestatic    com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   110: athrow         
            //   111: getstatic       com/intellij/codeInsight/daemon/ImplicitUsageProvider.EP_NAME:Lcom/intellij/openapi/extensions/ExtensionPointName;
            //   114: invokestatic    com/intellij/openapi/extensions/Extensions.getExtensions:(Lcom/intellij/openapi/extensions/ExtensionPointName;)[Ljava/lang/Object;
            //   117: checkcast       [Lcom/intellij/codeInsight/daemon/ImplicitUsageProvider;
            //   120: astore          6
            //   122: aload           6
            //   124: arraylength    
            //   125: istore          7
            //   127: iconst_0       
            //   128: istore          8
            //   130: iload           8
            //   132: iload           7
            //   134: if_icmpge       197
            //   137: aload           6
            //   139: iload           8
            //   141: aaload         
            //   142: astore          9
            //   144: aload           9
            //   146: aload_1        
            //   147: invokeinterface com/intellij/codeInsight/daemon/ImplicitUsageProvider.isImplicitUsage:(Lcom/intellij/psi/PsiElement;)Z
            //   152: ifne            173
            //   155: aload           9
            //   157: aload_1        
            //   158: invokeinterface com/intellij/codeInsight/daemon/ImplicitUsageProvider.isImplicitRead:(Lcom/intellij/psi/PsiElement;)Z
            //   163: ifeq            178
            //   166: goto            173
            //   169: invokestatic    com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   172: athrow         
            //   173: return         
            //   174: invokestatic    com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   177: athrow         
            //   178: iload           4
            //   180: aload           9
            //   182: aload_1        
            //   183: invokeinterface com/intellij/codeInsight/daemon/ImplicitUsageProvider.isImplicitWrite:(Lcom/intellij/psi/PsiElement;)Z
            //   188: ior            
            //   189: istore          4
            //   191: iinc            8, 1
            //   194: goto            130
            //   197: aload_1        
            //   198: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
            //   203: astore          6
            //   205: aload_0        
            //   206: getfield        com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor.myScope:Lcom/intellij/psi/search/SearchScope;
            //   209: astore          7
            //   211: aload           7
            //   213: ifnonnull       265
            //   216: aload           5
            //   218: getstatic       com/jetbrains/cidr/lang/psi/OCFile$UnusedChecksMode.IN_LOCAL_FILE:Lcom/jetbrains/cidr/lang/psi/OCFile$UnusedChecksMode;
            //   221: if_acmpne       247
            //   224: goto            231
            //   227: invokestatic    com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   230: athrow         
            //   231: new             Lcom/intellij/psi/search/LocalSearchScope;
            //   234: dup            
            //   235: aload           6
            //   237: invokespecial   com/intellij/psi/search/LocalSearchScope.<init>:(Lcom/intellij/psi/PsiElement;)V
            //   240: goto            263
            //   243: invokestatic    com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   246: athrow         
            //   247: new             Lcom/jetbrains/cidr/lang/inspections/OCGlobalSearchScopeForUnusedCode;
            //   250: dup            
            //   251: aload_1        
            //   252: invokeinterface com/intellij/psi/PsiElement.getProject:()Lcom/intellij/openapi/project/Project;
            //   257: invokestatic    com/jetbrains/cidr/lang/search/scopes/OCSearchScope.getProjectSourcesScope:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/search/GlobalSearchScope;
            //   260: invokespecial   com/jetbrains/cidr/lang/inspections/OCGlobalSearchScopeForUnusedCode.<init>:(Lcom/intellij/psi/search/GlobalSearchScope;)V
            //   263: astore          7
            //   265: new             Lcom/intellij/util/CommonProcessors$FindFirstProcessor;
            //   268: dup            
            //   269: invokespecial   com/intellij/util/CommonProcessors$FindFirstProcessor.<init>:()V
            //   272: astore          8
            //   274: aload_3        
            //   275: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getNameWithKindUppercase:()Ljava/lang/String;
            //   280: astore          9
            //   282: new             Lcom/jetbrains/cidr/lang/quickfixes/OCSafeDeleteIntentionAction;
            //   285: dup            
            //   286: aload_1        
            //   287: aload           9
            //   289: invokestatic    com/intellij/openapi/util/text/StringUtil.decapitalize:(Ljava/lang/String;)Ljava/lang/String;
            //   292: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCSafeDeleteIntentionAction.<init>:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;)V
            //   295: astore          10
            //   297: iconst_0       
            //   298: istore          11
            //   300: aload_3        
            //   301: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
            //   306: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.INSTANCE_VARIABLE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
            //   309: if_acmpne       327
            //   312: aload_0        
            //   313: aload_3        
            //   314: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
            //   317: aload           7
            //   319: invokespecial   com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor.a:(Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;Lcom/intellij/psi/search/SearchScope;)Lcom/intellij/psi/search/SearchScope;
            //   322: astore          7
            //   324: goto            679
            //   327: aload_3        
            //   328: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
            //   333: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.PROPERTY:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
            //   336: if_acmpne       480
            //   339: aload_1        
            //   340: astore          12
            //   342: new             Lcom/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor$1;
            //   345: dup            
            //   346: aload_0        
            //   347: aload           12
            //   349: invokespecial   com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor$1.<init>:(Lcom/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor;Lcom/intellij/psi/PsiElement;)V
            //   352: astore          8
            //   354: aload_1        
            //   355: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMethod;
            //   358: ifeq            477
            //   361: new             Ljava/lang/StringBuilder;
            //   364: dup            
            //   365: invokespecial   java/lang/StringBuilder.<init>:()V
            //   368: aload_1        
            //   369: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMethod;
            //   372: invokeinterface com/jetbrains/cidr/lang/psi/OCMethod.getSelector:()Ljava/lang/String;
            //   377: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.isObjCSetter:(Ljava/lang/String;)Z
            //   380: ifeq            399
            //   383: goto            390
            //   386: invokestatic    com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   389: athrow         
            //   390: ldc             "Setter"
            //   392: goto            401
            //   395: invokestatic    com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   398: athrow         
            //   399: ldc             "Getter"
            //   401: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   404: ldc             " method for "
            //   406: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   409: aload_3        
            //   410: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getNameWithKindLowercase:()Ljava/lang/String;
            //   415: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   418: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   421: astore          9
            //   423: new             Lcom/jetbrains/cidr/lang/quickfixes/OCRemoveElementsIntentionAction;
            //   426: dup            
            //   427: aload_1        
            //   428: new             Ljava/lang/StringBuilder;
            //   431: dup            
            //   432: invokespecial   java/lang/StringBuilder.<init>:()V
            //   435: ldc             "Delete "
            //   437: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   440: aload           9
            //   442: invokestatic    com/intellij/openapi/util/text/StringUtil.decapitalize:(Ljava/lang/String;)Ljava/lang/String;
            //   445: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   448: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   451: ldc             "Delete method"
            //   453: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCRemoveElementsIntentionAction.<init>:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljava/lang/String;)V
            //   456: astore          10
            //   458: aload_3        
            //   459: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.locateDefinition:()Lcom/intellij/psi/PsiElement;
            //   464: astore_1       
            //   465: iconst_1       
            //   466: istore          11
            //   468: aload_1        
            //   469: ifnonnull       477
            //   472: return         
            //   473: invokestatic    com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   476: athrow         
            //   477: goto            679
            //   480: aload_3        
            //   481: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
            //   484: ifne            501
            //   487: aload_3        
            //   488: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
            //   491: ifeq            520
            //   494: goto            501
            //   497: invokestatic    com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   500: athrow         
            //   501: aload_3        
            //   502: invokestatic    com/jetbrains/cidr/lang/OCTestFrameworks.isTestClassOrStruct:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
            //   505: ifeq            520
            //   508: goto            515
            //   511: invokestatic    com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   514: athrow         
            //   515: return         
            //   516: invokestatic    com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   519: athrow         
            //   520: aload_3        
            //   521: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
            //   526: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.STRUCT:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
            //   529: if_acmpne       601
            //   532: aload_1        
            //   533: astore          12
            //   535: new             Lcom/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor$2;
            //   538: dup            
            //   539: aload_0        
            //   540: aload           12
            //   542: invokespecial   com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor$2.<init>:(Lcom/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor;Lcom/intellij/psi/PsiElement;)V
            //   545: astore          8
            //   547: aload_1        
            //   548: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
            //   553: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
            //   558: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
            //   561: ifeq            598
            //   564: aload_1        
            //   565: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
            //   570: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
            //   575: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
            //   578: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.isTypedef:()Z
            //   583: ifeq            598
            //   586: goto            593
            //   589: invokestatic    com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   592: athrow         
            //   593: return         
            //   594: invokestatic    com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   597: athrow         
            //   598: goto            679
            //   601: aload_3        
            //   602: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
            //   607: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.ENUM:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
            //   610: if_acmpne       618
            //   613: return         
            //   614: invokestatic    com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   617: athrow         
            //   618: aload_3        
            //   619: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
            //   624: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.TYPEDEF:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
            //   627: if_acmpne       679
            //   630: aload_3        
            //   631: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
            //   636: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
            //   639: ifeq            679
            //   642: goto            649
            //   645: invokestatic    com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   648: athrow         
            //   649: aload_3        
            //   650: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
            //   655: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
            //   658: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
            //   661: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.ENUM:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
            //   664: if_acmpne       679
            //   667: goto            674
            //   670: invokestatic    com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   673: athrow         
            //   674: return         
            //   675: invokestatic    com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   678: athrow         
            //   679: aload           7
            //   681: instanceof      Lcom/intellij/psi/search/GlobalSearchScope;
            //   684: ifeq            733
            //   687: aload_1        
            //   688: invokeinterface com/intellij/psi/PsiElement.getProject:()Lcom/intellij/openapi/project/Project;
            //   693: invokestatic    com/intellij/psi/search/PsiSearchHelper$SERVICE.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/search/PsiSearchHelper;
            //   696: aload_3        
            //   697: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
            //   702: aload           7
            //   704: checkcast       Lcom/intellij/psi/search/GlobalSearchScope;
            //   707: aload           6
            //   709: aconst_null    
            //   710: invokeinterface com/intellij/psi/search/PsiSearchHelper.isCheapEnoughToSearch:(Ljava/lang/String;Lcom/intellij/psi/search/GlobalSearchScope;Lcom/intellij/psi/PsiFile;Lcom/intellij/openapi/progress/ProgressIndicator;)Lcom/intellij/psi/search/PsiSearchHelper$SearchCostResult;
            //   715: getstatic       com/intellij/psi/search/PsiSearchHelper$SearchCostResult.TOO_MANY_OCCURRENCES:Lcom/intellij/psi/search/PsiSearchHelper$SearchCostResult;
            //   718: if_acmpne       733
            //   721: goto            728
            //   724: invokestatic    com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   727: athrow         
            //   728: return         
            //   729: invokestatic    com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   732: athrow         
            //   733: new             Lcom/intellij/openapi/util/Ref;
            //   736: dup            
            //   737: iload           4
            //   739: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
            //   742: invokespecial   com/intellij/openapi/util/Ref.<init>:(Ljava/lang/Object;)V
            //   745: astore          12
            //   747: aload           7
            //   749: ifnull          887
            //   752: new             Lcom/intellij/psi/search/searches/ReferencesSearch$SearchParameters;
            //   755: dup            
            //   756: aload_1        
            //   757: aload           7
            //   759: iconst_0       
            //   760: invokespecial   com/intellij/psi/search/searches/ReferencesSearch$SearchParameters.<init>:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/search/SearchScope;Z)V
            //   763: astore          13
            //   765: aload_0        
            //   766: aload_3        
            //   767: aload           13
            //   769: invokestatic    com/intellij/psi/search/searches/ReferencesSearch.search:(Lcom/intellij/psi/search/searches/ReferencesSearch$SearchParameters;)Lcom/intellij/util/Query;
            //   772: aload           8
            //   774: aload           12
            //   776: invokespecial   com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor.a:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/util/Query;Lcom/intellij/util/CommonProcessors$FindFirstProcessor;Lcom/intellij/openapi/util/Ref;)Z
            //   779: ifeq            787
            //   782: return         
            //   783: invokestatic    com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   786: athrow         
            //   787: aload_3        
            //   788: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
            //   793: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.PROPERTY:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
            //   796: if_acmpne       830
            //   799: aload_0        
            //   800: aload_3        
            //   801: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
            //   804: aload           7
            //   806: aload           8
            //   808: aload           13
            //   810: aload           12
            //   812: invokespecial   com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor.a:(Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;Lcom/intellij/psi/search/SearchScope;Lcom/intellij/util/CommonProcessors$FindFirstProcessor;Lcom/intellij/psi/search/searches/ReferencesSearch$SearchParameters;Lcom/intellij/openapi/util/Ref;)Z
            //   815: ifeq            830
            //   818: goto            825
            //   821: invokestatic    com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   824: athrow         
            //   825: return         
            //   826: invokestatic    com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   829: athrow         
            //   830: new             Lcom/intellij/util/CommonProcessors$FindFirstProcessor;
            //   833: dup            
            //   834: invokespecial   com/intellij/util/CommonProcessors$FindFirstProcessor.<init>:()V
            //   837: astore          14
            //   839: new             Lcom/intellij/psi/search/searches/ReferencesSearch$SearchParameters;
            //   842: dup            
            //   843: aload_1        
            //   844: aload_1        
            //   845: invokeinterface com/intellij/psi/PsiElement.getProject:()Lcom/intellij/openapi/project/Project;
            //   850: invokestatic    com/jetbrains/cidr/lang/search/scopes/OCSearchScope.getProjectSourcesScope:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/search/GlobalSearchScope;
            //   853: iconst_0       
            //   854: invokespecial   com/intellij/psi/search/searches/ReferencesSearch$SearchParameters.<init>:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/search/SearchScope;Z)V
            //   857: astore          15
            //   859: new             Lcom/jetbrains/cidr/lang/search/OCElementInMacroAndNonCompiledCodeReferencesSearch;
            //   862: dup            
            //   863: invokespecial   com/jetbrains/cidr/lang/search/OCElementInMacroAndNonCompiledCodeReferencesSearch.<init>:()V
            //   866: aload           15
            //   868: aload           14
            //   870: invokevirtual   com/jetbrains/cidr/lang/search/OCElementInMacroAndNonCompiledCodeReferencesSearch.execute:(Lcom/intellij/psi/search/searches/ReferencesSearch$SearchParameters;Lcom/intellij/util/Processor;)Z
            //   873: pop            
            //   874: aload           14
            //   876: invokevirtual   com/intellij/util/CommonProcessors$FindFirstProcessor.isFound:()Z
            //   879: ifeq            887
            //   882: return         
            //   883: invokestatic    com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   886: athrow         
            //   887: iload           11
            //   889: ifeq            918
            //   892: aload           12
            //   894: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
            //   897: checkcast       Ljava/lang/Boolean;
            //   900: invokevirtual   java/lang/Boolean.booleanValue:()Z
            //   903: ifeq            918
            //   906: goto            913
            //   909: invokestatic    com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   912: athrow         
            //   913: return         
            //   914: invokestatic    com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   917: athrow         
            //   918: aload_2        
            //   919: instanceof      Lcom/intellij/psi/PsiNameIdentifierOwner;
            //   922: ifeq            949
            //   925: aload_2        
            //   926: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDefineDirective;
            //   929: ifne            949
            //   932: goto            939
            //   935: invokestatic    com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   938: athrow         
            //   939: aload_2        
            //   940: checkcast       Lcom/intellij/psi/PsiNameIdentifierOwner;
            //   943: invokeinterface com/intellij/psi/PsiNameIdentifierOwner.getNameIdentifier:()Lcom/intellij/psi/PsiElement;
            //   948: astore_2       
            //   949: aload_2        
            //   950: ifnull          1041
            //   953: new             Ljava/lang/StringBuilder;
            //   956: dup            
            //   957: invokespecial   java/lang/StringBuilder.<init>:()V
            //   960: aload           9
            //   962: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   965: aload           12
            //   967: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
            //   970: checkcast       Ljava/lang/Boolean;
            //   973: invokevirtual   java/lang/Boolean.booleanValue:()Z
            //   976: ifeq            995
            //   979: goto            986
            //   982: invokestatic    com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   985: athrow         
            //   986: ldc             " is assigned but never accessed"
            //   988: goto            997
            //   991: invokestatic    com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   994: athrow         
            //   995: ldc             " is never used"
            //   997: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //  1000: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //  1003: astore          13
            //  1005: aload_0        
            //  1006: aload_2        
            //  1007: aload           13
            //  1009: getstatic       com/intellij/codeInspection/ProblemHighlightType.LIKE_UNUSED_SYMBOL:Lcom/intellij/codeInspection/ProblemHighlightType;
            //  1012: iconst_2       
            //  1013: anewarray       Lcom/intellij/codeInsight/intention/IntentionAction;
            //  1016: dup            
            //  1017: iconst_0       
            //  1018: aload           10
            //  1020: aastore        
            //  1021: dup            
            //  1022: iconst_1       
            //  1023: new             Lcom/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor$3;
            //  1026: dup            
            //  1027: aload_0        
            //  1028: aload_3        
            //  1029: ldc             "unused"
            //  1031: ldc             "__unused"
            //  1033: iconst_1       
            //  1034: invokespecial   com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor$3.<init>:(Lcom/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Ljava/lang/String;Ljava/lang/String;Z)V
            //  1037: aastore        
            //  1038: invokevirtual   com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor.registerProblem:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Lcom/intellij/codeInspection/ProblemHighlightType;[Lcom/intellij/codeInsight/intention/IntentionAction;)V
            //  1041: return         
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  0      8      11     15     Ljava/lang/IllegalArgumentException;
            //  4      16     16     20     Ljava/lang/IllegalArgumentException;
            //  39     51     54     58     Ljava/lang/IllegalArgumentException;
            //  43     69     72     76     Ljava/lang/IllegalArgumentException;
            //  58     87     90     94     Ljava/lang/IllegalArgumentException;
            //  76     95     95     99     Ljava/lang/IllegalArgumentException;
            //  99     107    107    111    Ljava/lang/IllegalArgumentException;
            //  144    166    169    173    Ljava/lang/IllegalArgumentException;
            //  155    174    174    178    Ljava/lang/IllegalArgumentException;
            //  211    224    227    231    Ljava/lang/IllegalArgumentException;
            //  216    243    243    247    Ljava/lang/IllegalArgumentException;
            //  354    383    386    390    Ljava/lang/IllegalArgumentException;
            //  361    395    395    399    Ljava/lang/IllegalArgumentException;
            //  468    473    473    477    Ljava/lang/IllegalArgumentException;
            //  480    494    497    501    Ljava/lang/IllegalArgumentException;
            //  487    508    511    515    Ljava/lang/IllegalArgumentException;
            //  501    516    516    520    Ljava/lang/IllegalArgumentException;
            //  547    586    589    593    Ljava/lang/IllegalArgumentException;
            //  564    594    594    598    Ljava/lang/IllegalArgumentException;
            //  601    614    614    618    Ljava/lang/IllegalArgumentException;
            //  618    642    645    649    Ljava/lang/IllegalArgumentException;
            //  630    667    670    674    Ljava/lang/IllegalArgumentException;
            //  649    675    675    679    Ljava/lang/IllegalArgumentException;
            //  679    721    724    728    Ljava/lang/IllegalArgumentException;
            //  687    729    729    733    Ljava/lang/IllegalArgumentException;
            //  765    783    783    787    Ljava/lang/IllegalArgumentException;
            //  787    818    821    825    Ljava/lang/IllegalArgumentException;
            //  799    826    826    830    Ljava/lang/IllegalArgumentException;
            //  859    883    883    887    Ljava/lang/IllegalArgumentException;
            //  887    906    909    913    Ljava/lang/IllegalArgumentException;
            //  892    914    914    918    Ljava/lang/IllegalArgumentException;
            //  918    932    935    939    Ljava/lang/IllegalArgumentException;
            //  949    979    982    986    Ljava/lang/IllegalArgumentException;
            //  953    991    991    995    Ljava/lang/IllegalArgumentException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0058:
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
        
        private boolean a(final OCSymbol ocSymbol, final Query<PsiReference> query, final CommonProcessors.FindFirstProcessor<PsiReference> findFirstProcessor, final Ref<Boolean> ref) {
            if (OCUnusedCodeInspection.isWritableSymbol(ocSymbol)) {
                final Condition condition = psiReference -> {
                    try {
                        if (new OCReadWriteAccessDetector().getExpressionAccess(psiReference.getElement()) != ReadWriteAccessDetector.Access.Write) {
                            return true;
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    return false;
                };
                final OCCommonProcessors.OrderedProcessor orderedProcessor = new OCCommonProcessors.OrderedProcessor((com.intellij.util.Processor<? super Object>)findFirstProcessor, (com.intellij.openapi.util.Condition<Object>[])new Condition[] { condition, Conditions.alwaysTrue() });
                Label_0088: {
                    try {
                        query.forEach((Processor)orderedProcessor);
                        orderedProcessor.finish();
                        if (!findFirstProcessor.isFound()) {
                            return false;
                        }
                        final Condition condition2 = condition;
                        final com.intellij.util.Processor<? super Object> processor = (com.intellij.util.Processor<? super Object>)findFirstProcessor;
                        final Object o = ((CommonProcessors.FindFirstProcessor)processor).getFoundValue();
                        final boolean b = condition2.value(o);
                        if (b) {
                            return true;
                        }
                        break Label_0088;
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        final Condition condition2 = condition;
                        final com.intellij.util.Processor<? super Object> processor = (com.intellij.util.Processor<? super Object>)findFirstProcessor;
                        final Object o = ((CommonProcessors.FindFirstProcessor)processor).getFoundValue();
                        final boolean b = condition2.value(o);
                        if (b) {
                            return true;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
                ref.set((Object)true);
            }
            else {
                try {
                    query.forEach((Processor)findFirstProcessor);
                    if (findFirstProcessor.isFound()) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            return false;
        }
        
        private boolean a(final OCPropertySymbol ocPropertySymbol, SearchScope a, final CommonProcessors.FindFirstProcessor<PsiReference> findFirstProcessor, final ReferencesSearch.SearchParameters searchParameters, final Ref<Boolean> ref) {
            final ExecutorsQuery executorsQuery = new ExecutorsQuery((Object)searchParameters, (List)Collections.singletonList(new OCPropertyReferencesSearch()));
            try {
                if (this.a(ocPropertySymbol, (Query<PsiReference>)executorsQuery, findFirstProcessor, ref)) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            final OCInstanceVariableSymbol associatedIvar = ocPropertySymbol.getAssociatedIvar();
            Label_0076: {
                try {
                    if (associatedIvar == null) {
                        return false;
                    }
                    final OCInstanceVariableSymbol ocInstanceVariableSymbol = associatedIvar;
                    final String s = ocInstanceVariableSymbol.getGeneratedFromProperty();
                    if (s == null) {
                        return false;
                    }
                    break Label_0076;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final OCInstanceVariableSymbol ocInstanceVariableSymbol = associatedIvar;
                    final String s = ocInstanceVariableSymbol.getGeneratedFromProperty();
                    if (s == null) {
                        return false;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            a = this.a(associatedIvar, a);
            try {
                if (a == null) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            Object locateDefinition = ((OCSymbol<Object>)associatedIvar).locateDefinition();
            if (locateDefinition == null) {
                locateDefinition = new OCSymbolHolderVirtualPsiElement(associatedIvar);
            }
            final ReferencesSearch.SearchParameters searchParameters2 = new ReferencesSearch.SearchParameters((PsiElement)locateDefinition, a, false);
            final CommonProcessors.FindFirstProcessor<PsiReference> findFirstProcessor2 = new CommonProcessors.FindFirstProcessor<PsiReference>() {
                protected boolean accept(final PsiReference psiReference) {
                    return !(psiReference.getElement().getParent() instanceof OCSynthesizeProperty);
                }
            };
            try {
                if (this.a(associatedIvar, (Query<PsiReference>)ReferencesSearch.search(searchParameters2), findFirstProcessor2, ref)) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
            return false;
        }
        
        @Nullable
        private SearchScope a(final OCInstanceVariableSymbol p0, final SearchScope p1) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_1        
            //     1: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.getVisibility:()Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
            //     6: getstatic       com/jetbrains/cidr/lang/symbols/OCVisibility.PRIVATE:Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
            //     9: if_acmpne       112
            //    12: aload_1        
            //    13: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
            //    18: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
            //    21: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getImplementation:()Lcom/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol;
            //    26: astore_3       
            //    27: aload_3        
            //    28: ifnull          45
            //    31: aload_3        
            //    32: invokevirtual   com/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol.locateDefinition:()Lcom/intellij/psi/PsiElement;
            //    35: checkcast       Lcom/jetbrains/cidr/lang/psi/OCClassDeclarationBase;
            //    38: goto            46
            //    41: invokestatic    com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    44: athrow         
            //    45: aconst_null    
            //    46: astore          4
            //    48: aload           4
            //    50: ifnull          110
            //    53: aload_0        
            //    54: getfield        com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor.myScope:Lcom/intellij/psi/search/SearchScope;
            //    57: instanceof      Lcom/intellij/psi/search/GlobalSearchScope;
            //    60: ifeq            100
            //    63: goto            70
            //    66: invokestatic    com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    69: athrow         
            //    70: aload_0        
            //    71: getfield        com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor.myScope:Lcom/intellij/psi/search/SearchScope;
            //    74: checkcast       Lcom/intellij/psi/search/GlobalSearchScope;
            //    77: aload_3        
            //    78: invokevirtual   com/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol.getContainingFile:()Lcom/intellij/openapi/vfs/VirtualFile;
            //    81: invokevirtual   com/intellij/psi/search/GlobalSearchScope.accept:(Lcom/intellij/openapi/vfs/VirtualFile;)Z
            //    84: ifne            100
            //    87: goto            94
            //    90: invokestatic    com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    93: athrow         
            //    94: aconst_null    
            //    95: areturn        
            //    96: invokestatic    com/jetbrains/cidr/lang/inspections/OCUnusedCodeInspection$UnusedVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    99: athrow         
            //   100: new             Lcom/intellij/psi/search/LocalSearchScope;
            //   103: dup            
            //   104: aload           4
            //   106: invokespecial   com/intellij/psi/search/LocalSearchScope.<init>:(Lcom/intellij/psi/PsiElement;)V
            //   109: areturn        
            //   110: aconst_null    
            //   111: areturn        
            //   112: aload_2        
            //   113: areturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  27     41     41     45     Ljava/lang/IllegalArgumentException;
            //  48     63     66     70     Ljava/lang/IllegalArgumentException;
            //  53     87     90     94     Ljava/lang/IllegalArgumentException;
            //  70     96     96     100    Ljava/lang/IllegalArgumentException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0070:
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
}
