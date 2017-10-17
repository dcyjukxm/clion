// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.navigation;

import com.intellij.openapi.editor.markup.GutterIconRenderer;
import com.intellij.openapi.editor.Editor;
import com.intellij.featureStatistics.FeatureUsageTracker;
import java.awt.event.MouseEvent;
import com.intellij.codeInsight.daemon.GutterIconNavigationHandler;
import java.util.ArrayList;
import javax.swing.Icon;
import com.jetbrains.cidr.lang.OCIcons;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.intellij.util.Processor;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.OCClassPredeclaration;
import java.util.LinkedList;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.psi.OCClassPredeclarationList;
import java.util.Iterator;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.intellij.util.containers.MultiMap;
import java.util.Collection;
import java.util.List;
import com.jetbrains.cidr.lang.psi.OCStructLike;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.psi.OCMethodSelectorPart;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.psi.PsiNameIdentifierOwner;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.codeInsight.daemon.LineMarkerInfo;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.codeInsight.daemon.DaemonCodeAnalyzerSettings;
import com.intellij.codeInsight.daemon.LineMarkerProvider;

public class OCLineMarkerProvider implements LineMarkerProvider
{
    private final DaemonCodeAnalyzerSettings myDaemonSettings;
    private final EditorColorsManager myColorsManager;
    
    public OCLineMarkerProvider(final DaemonCodeAnalyzerSettings myDaemonSettings, final EditorColorsManager myColorsManager) {
        this.myDaemonSettings = myDaemonSettings;
        this.myColorsManager = myColorsManager;
    }
    
    @Nullable
    public LineMarkerInfo getLineMarkerInfo(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider", "getLineMarkerInfo"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (OCElementUtil.isPartOfMacroSubstitution(psiElement)) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final LineMarkerInfo a = this.a(psiElement);
        final OCLineMarkerInfo c = c(psiElement);
        Label_0086: {
            try {
                if (c == null) {
                    return a;
                }
                final LineMarkerInfo lineMarkerInfo = a;
                final boolean b = lineMarkerInfo instanceof OCLineMarkerInfo;
                if (b) {
                    break Label_0086;
                }
                return c;
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                final LineMarkerInfo lineMarkerInfo = a;
                final boolean b = lineMarkerInfo instanceof OCLineMarkerInfo;
                if (b) {
                    return a(psiElement, (OCLineMarkerInfo)a, c);
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return c;
    }
    
    @Nullable
    private static PsiNameIdentifierOwner d(final PsiElement psiElement) {
        try {
            if (OCElementUtil.getElementType(psiElement) != OCTokenTypes.IDENTIFIER) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        PsiElement psiElement2 = psiElement.getParent();
        Label_0135: {
            Label_0108: {
                if (psiElement2 instanceof OCMethodSelectorPart) {
                    psiElement2 = psiElement2.getParent();
                    Label_0075: {
                        try {
                            if (!(psiElement2 instanceof OCMethod)) {
                                break Label_0075;
                            }
                            final PsiElement psiElement3 = psiElement2;
                            final OCMethod ocMethod = (OCMethod)psiElement3;
                            final List<OCMethodSelectorPart> list = ocMethod.getParameters();
                            final int n = 0;
                            final OCMethodSelectorPart ocMethodSelectorPart = list.get(n);
                            final PsiElement psiElement4 = psiElement;
                            final PsiElement psiElement5 = psiElement4.getParent();
                            if (ocMethodSelectorPart != psiElement5) {
                                break Label_0075;
                            }
                            break Label_0108;
                        }
                        catch (IllegalArgumentException ex2) {
                            throw a(ex2);
                        }
                        try {
                            final PsiElement psiElement3 = psiElement2;
                            final OCMethod ocMethod = (OCMethod)psiElement3;
                            final List<OCMethodSelectorPart> list = ocMethod.getParameters();
                            final int n = 0;
                            final OCMethodSelectorPart ocMethodSelectorPart = list.get(n);
                            final PsiElement psiElement4 = psiElement;
                            final PsiElement psiElement5 = psiElement4.getParent();
                            if (ocMethodSelectorPart != psiElement5) {
                                return null;
                            }
                            break Label_0108;
                        }
                        catch (IllegalArgumentException ex3) {
                            throw a(ex3);
                        }
                    }
                }
                if (psiElement2 instanceof OCReferenceElement) {
                    psiElement2 = psiElement2.getParent();
                    try {
                        if (!(psiElement2 instanceof OCStructLike)) {
                            return null;
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                }
                try {
                    if (!(psiElement2 instanceof PsiNameIdentifierOwner)) {
                        return null;
                    }
                    final PsiElement psiElement6 = psiElement2;
                    final PsiNameIdentifierOwner psiNameIdentifierOwner = (PsiNameIdentifierOwner)psiElement6;
                    final PsiElement psiElement7 = psiNameIdentifierOwner.getNameIdentifier();
                    final PsiElement psiElement8 = psiElement;
                    if (psiElement7 == psiElement8) {
                        break Label_0135;
                    }
                    return null;
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
            }
            try {
                final PsiElement psiElement6 = psiElement2;
                final PsiNameIdentifierOwner psiNameIdentifierOwner = (PsiNameIdentifierOwner)psiElement6;
                final PsiElement psiElement7 = psiNameIdentifierOwner.getNameIdentifier();
                final PsiElement psiElement8 = psiElement;
                if (psiElement7 == psiElement8) {
                    return (PsiNameIdentifierOwner)psiElement2;
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
        }
        return null;
    }
    
    private static boolean b(final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //     4: ifeq            26
        //     7: aload_0        
        //     8: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //    11: invokeinterface com/jetbrains/cidr/lang/psi/OCMethod.getBody:()Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //    16: ifnonnull       134
        //    19: goto            26
        //    22: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    25: athrow         
        //    26: aload_0        
        //    27: instanceof      Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
        //    30: ifne            134
        //    33: goto            40
        //    36: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    39: athrow         
        //    40: aload_0        
        //    41: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //    44: ifne            134
        //    47: goto            54
        //    50: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    53: athrow         
        //    54: aload_0        
        //    55: instanceof      Lcom/jetbrains/cidr/lang/psi/OCProperty;
        //    58: ifne            134
        //    61: goto            68
        //    64: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    67: athrow         
        //    68: aload_0        
        //    69: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSynthesizePropertiesList;
        //    72: ifne            134
        //    75: goto            82
        //    78: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    81: athrow         
        //    82: aload_0        
        //    83: instanceof      Lcom/jetbrains/cidr/lang/psi/OCClassPredeclarationList;
        //    86: ifne            134
        //    89: goto            96
        //    92: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    95: athrow         
        //    96: aload_0        
        //    97: instanceof      Lcom/jetbrains/cidr/lang/psi/OCInstanceVariablesList;
        //   100: ifeq            142
        //   103: goto            110
        //   106: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   109: athrow         
        //   110: aload_0        
        //   111: checkcast       Lcom/jetbrains/cidr/lang/psi/OCInstanceVariablesList;
        //   114: invokeinterface com/jetbrains/cidr/lang/psi/OCInstanceVariablesList.getDeclarations:()Ljava/util/List;
        //   119: invokeinterface java/util/List.isEmpty:()Z
        //   124: ifne            142
        //   127: goto            134
        //   130: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   133: athrow         
        //   134: iconst_1       
        //   135: goto            143
        //   138: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   141: athrow         
        //   142: iconst_0       
        //   143: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      19     22     26     Ljava/lang/IllegalArgumentException;
        //  7      33     36     40     Ljava/lang/IllegalArgumentException;
        //  26     47     50     54     Ljava/lang/IllegalArgumentException;
        //  40     61     64     68     Ljava/lang/IllegalArgumentException;
        //  54     75     78     82     Ljava/lang/IllegalArgumentException;
        //  68     89     92     96     Ljava/lang/IllegalArgumentException;
        //  82     103    106    110    Ljava/lang/IllegalArgumentException;
        //  96     127    130    134    Ljava/lang/IllegalArgumentException;
        //  110    138    138    142    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0026:
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
    
    protected static int getCategory(final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/psi/OCProperty;
        //     4: ifne            35
        //     7: aload_0        
        //     8: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSynthesizePropertiesList;
        //    11: ifne            35
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    20: athrow         
        //    21: aload_0        
        //    22: instanceof      Lcom/jetbrains/cidr/lang/psi/OCClassPredeclarationList;
        //    25: ifeq            41
        //    28: goto            35
        //    31: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    34: athrow         
        //    35: iconst_1       
        //    36: ireturn        
        //    37: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    40: athrow         
        //    41: aload_0        
        //    42: instanceof      Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
        //    45: ifne            62
        //    48: aload_0        
        //    49: instanceof      Lcom/jetbrains/cidr/lang/psi/OCInstanceVariablesList;
        //    52: ifeq            68
        //    55: goto            62
        //    58: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    61: athrow         
        //    62: iconst_2       
        //    63: ireturn        
        //    64: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    67: athrow         
        //    68: aload_0        
        //    69: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCppNamespace;
        //    72: ifeq            81
        //    75: iconst_2       
        //    76: ireturn        
        //    77: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    80: athrow         
        //    81: aload_0        
        //    82: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCppUsingStatement;
        //    85: ifeq            94
        //    88: iconst_1       
        //    89: ireturn        
        //    90: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    93: athrow         
        //    94: aload_0        
        //    95: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //    98: ifne            115
        //   101: aload_0        
        //   102: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //   105: ifeq            180
        //   108: goto            115
        //   111: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   114: athrow         
        //   115: aload_0        
        //   116: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   119: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getBody:()Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //   124: ifnonnull       140
        //   127: goto            134
        //   130: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   133: athrow         
        //   134: iconst_1       
        //   135: ireturn        
        //   136: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   139: athrow         
        //   140: aload_0        
        //   141: invokeinterface com/intellij/psi/PsiElement.getText:()Ljava/lang/String;
        //   146: astore_1       
        //   147: aload_1        
        //   148: bipush          10
        //   150: invokevirtual   java/lang/String.indexOf:(I)I
        //   153: ifge            178
        //   156: aload_1        
        //   157: bipush          13
        //   159: invokevirtual   java/lang/String.indexOf:(I)I
        //   162: ifge            178
        //   165: goto            172
        //   168: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   171: athrow         
        //   172: iconst_1       
        //   173: ireturn        
        //   174: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   177: athrow         
        //   178: iconst_2       
        //   179: ireturn        
        //   180: aload_0        
        //   181: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //   184: ifeq            235
        //   187: aload_0        
        //   188: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //   191: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getTypeElement:()Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //   196: astore_1       
        //   197: aload_1        
        //   198: ldc             Lcom/jetbrains/cidr/lang/psi/OCStructLike;.class
        //   200: invokestatic    com/intellij/psi/util/PsiTreeUtil.findChildOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   203: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStructLike;
        //   206: astore_2       
        //   207: aload_2        
        //   208: ifnull          233
        //   211: aload_2        
        //   212: invokeinterface com/jetbrains/cidr/lang/psi/OCStructLike.isDeclaration:()Z
        //   217: ifeq            233
        //   220: goto            227
        //   223: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   226: athrow         
        //   227: iconst_2       
        //   228: ireturn        
        //   229: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   232: athrow         
        //   233: iconst_1       
        //   234: ireturn        
        //   235: iconst_0       
        //   236: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      14     17     21     Ljava/lang/IllegalArgumentException;
        //  7      28     31     35     Ljava/lang/IllegalArgumentException;
        //  21     37     37     41     Ljava/lang/IllegalArgumentException;
        //  41     55     58     62     Ljava/lang/IllegalArgumentException;
        //  48     64     64     68     Ljava/lang/IllegalArgumentException;
        //  68     77     77     81     Ljava/lang/IllegalArgumentException;
        //  81     90     90     94     Ljava/lang/IllegalArgumentException;
        //  94     108    111    115    Ljava/lang/IllegalArgumentException;
        //  101    127    130    134    Ljava/lang/IllegalArgumentException;
        //  115    136    136    140    Ljava/lang/IllegalArgumentException;
        //  147    165    168    172    Ljava/lang/IllegalArgumentException;
        //  156    174    174    178    Ljava/lang/IllegalArgumentException;
        //  207    220    223    227    Ljava/lang/IllegalArgumentException;
        //  211    229    229    233    Ljava/lang/IllegalArgumentException;
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
    
    public void collectSlowLineMarkers(@NotNull final List<PsiElement> list, @NotNull final Collection<LineMarkerInfo> collection) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "elements", "com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider", "collectSlowLineMarkers"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (collection == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider", "collectSlowLineMarkers"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final MultiMap multiMap = new MultiMap();
        final Iterator<PsiElement> iterator = list.iterator();
        while (iterator.hasNext()) {
            final PsiNameIdentifierOwner d = d(iterator.next());
            Label_0153: {
                try {
                    if (d == null) {
                        continue;
                    }
                    final PsiElement psiElement = (PsiElement)d;
                    final boolean b = OCElementUtil.isPartOfMacroSubstitution(psiElement);
                    if (!b) {
                        break Label_0153;
                    }
                    continue;
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    final PsiElement psiElement = (PsiElement)d;
                    final boolean b = OCElementUtil.isPartOfMacroSubstitution(psiElement);
                    if (b) {
                        continue;
                    }
                    a(d, collection, (MultiMap<OCQualifiedName, OCQualifiedName>)multiMap);
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
        }
    }
    
    @Nullable
    private LineMarkerInfo a(final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.myDaemonSettings:Lcom/intellij/codeInsight/daemon/DaemonCodeAnalyzerSettings;
        //     4: ifnull          257
        //     7: aload_1        
        //     8: ifnull          257
        //    11: goto            18
        //    14: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    17: athrow         
        //    18: aload_0        
        //    19: getfield        com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.myDaemonSettings:Lcom/intellij/codeInsight/daemon/DaemonCodeAnalyzerSettings;
        //    22: getfield        com/intellij/codeInsight/daemon/DaemonCodeAnalyzerSettings.SHOW_METHOD_SEPARATORS:Z
        //    25: ifeq            257
        //    28: goto            35
        //    31: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    34: athrow         
        //    35: aload_1        
        //    36: invokeinterface com/intellij/psi/PsiElement.getFirstChild:()Lcom/intellij/psi/PsiElement;
        //    41: ifnonnull       257
        //    44: goto            51
        //    47: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    50: athrow         
        //    51: aload_1        
        //    52: astore_2       
        //    53: iconst_0       
        //    54: istore_3       
        //    55: aload_2        
        //    56: ifnull          101
        //    59: aload_2        
        //    60: instanceof      Lcom/intellij/psi/PsiFile;
        //    63: ifne            101
        //    66: aload_2        
        //    67: invokeinterface com/intellij/psi/PsiElement.getPrevSibling:()Lcom/intellij/psi/PsiElement;
        //    72: ifnonnull       101
        //    75: goto            82
        //    78: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    81: athrow         
        //    82: aload_2        
        //    83: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    88: astore_2       
        //    89: aload_2        
        //    90: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.b:(Lcom/intellij/psi/PsiElement;)Z
        //    93: ifeq            55
        //    96: iconst_1       
        //    97: istore_3       
        //    98: goto            101
        //   101: iload_3        
        //   102: ifeq            257
        //   105: iconst_0       
        //   106: istore          4
        //   108: aload_2        
        //   109: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.getCategory:(Lcom/intellij/psi/PsiElement;)I
        //   112: istore          5
        //   114: aload_2        
        //   115: invokeinterface com/intellij/psi/PsiElement.getPrevSibling:()Lcom/intellij/psi/PsiElement;
        //   120: astore          6
        //   122: aload           6
        //   124: ifnull          191
        //   127: aload           6
        //   129: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.getCategory:(Lcom/intellij/psi/PsiElement;)I
        //   132: istore          7
        //   134: iload           7
        //   136: ifne            146
        //   139: goto            179
        //   142: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   145: athrow         
        //   146: iload           5
        //   148: iconst_1       
        //   149: if_icmpne       165
        //   152: iload           7
        //   154: iconst_1       
        //   155: if_icmpeq       173
        //   158: goto            165
        //   161: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   164: athrow         
        //   165: iconst_1       
        //   166: goto            174
        //   169: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   172: athrow         
        //   173: iconst_0       
        //   174: istore          4
        //   176: goto            191
        //   179: aload           6
        //   181: invokeinterface com/intellij/psi/PsiElement.getPrevSibling:()Lcom/intellij/psi/PsiElement;
        //   186: astore          6
        //   188: goto            122
        //   191: iload           4
        //   193: ifeq            257
        //   196: new             Lcom/intellij/codeInsight/daemon/LineMarkerInfo;
        //   199: dup            
        //   200: aload_1        
        //   201: aload_1        
        //   202: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   207: aconst_null    
        //   208: bipush          11
        //   210: invokestatic    com/intellij/util/FunctionUtil.nullConstant:()Lcom/intellij/util/NullableFunction;
        //   213: aconst_null    
        //   214: getstatic       com/intellij/openapi/editor/markup/GutterIconRenderer$Alignment.RIGHT:Lcom/intellij/openapi/editor/markup/GutterIconRenderer$Alignment;
        //   217: invokespecial   com/intellij/codeInsight/daemon/LineMarkerInfo.<init>:(Lcom/intellij/psi/PsiElement;Lcom/intellij/openapi/util/TextRange;Ljavax/swing/Icon;ILcom/intellij/util/Function;Lcom/intellij/codeInsight/daemon/GutterIconNavigationHandler;Lcom/intellij/openapi/editor/markup/GutterIconRenderer$Alignment;)V
        //   220: astore          6
        //   222: aload_0        
        //   223: getfield        com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.myColorsManager:Lcom/intellij/openapi/editor/colors/EditorColorsManager;
        //   226: invokevirtual   com/intellij/openapi/editor/colors/EditorColorsManager.getGlobalScheme:()Lcom/intellij/openapi/editor/colors/EditorColorsScheme;
        //   229: astore          7
        //   231: aload           6
        //   233: aload           7
        //   235: getstatic       com/intellij/openapi/editor/colors/CodeInsightColors.METHOD_SEPARATORS_COLOR:Lcom/intellij/openapi/editor/colors/ColorKey;
        //   238: invokeinterface com/intellij/openapi/editor/colors/EditorColorsScheme.getColor:(Lcom/intellij/openapi/editor/colors/ColorKey;)Ljava/awt/Color;
        //   243: putfield        com/intellij/codeInsight/daemon/LineMarkerInfo.separatorColor:Ljava/awt/Color;
        //   246: aload           6
        //   248: getstatic       com/intellij/openapi/editor/markup/SeparatorPlacement.TOP:Lcom/intellij/openapi/editor/markup/SeparatorPlacement;
        //   251: putfield        com/intellij/codeInsight/daemon/LineMarkerInfo.separatorPlacement:Lcom/intellij/openapi/editor/markup/SeparatorPlacement;
        //   254: aload           6
        //   256: areturn        
        //   257: aconst_null    
        //   258: astore_2       
        //   259: aload_1        
        //   260: instanceof      Lcom/jetbrains/cidr/lang/psi/OCClassPredeclarationList;
        //   263: ifeq            279
        //   266: aload_1        
        //   267: checkcast       Lcom/jetbrains/cidr/lang/psi/OCClassPredeclarationList;
        //   270: invokeinterface com/jetbrains/cidr/lang/psi/OCClassPredeclarationList.getPredeclarations:()Ljava/util/List;
        //   275: astore_2       
        //   276: goto            316
        //   279: aload_1        
        //   280: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //   283: ifeq            299
        //   286: aload_1        
        //   287: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //   290: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getDeclarators:()Ljava/util/List;
        //   295: astore_2       
        //   296: goto            316
        //   299: aload_1        
        //   300: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSynthesizePropertiesList;
        //   303: ifeq            316
        //   306: aload_1        
        //   307: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSynthesizePropertiesList;
        //   310: invokeinterface com/jetbrains/cidr/lang/psi/OCSynthesizePropertiesList.getProperties:()Ljava/util/List;
        //   315: astore_2       
        //   316: aload_2        
        //   317: ifnull          424
        //   320: new             Ljava/util/LinkedList;
        //   323: dup            
        //   324: invokespecial   java/util/LinkedList.<init>:()V
        //   327: astore_3       
        //   328: aload_2        
        //   329: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   334: astore          4
        //   336: aload           4
        //   338: invokeinterface java/util/Iterator.hasNext:()Z
        //   343: ifeq            401
        //   346: aload           4
        //   348: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   353: checkcast       Lcom/intellij/psi/PsiElement;
        //   356: astore          5
        //   358: aload           5
        //   360: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.getAction:(Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/navigation/OCGotoAction;
        //   363: astore          6
        //   365: aload           6
        //   367: ifnull          398
        //   370: aload_3        
        //   371: new             Lcom/jetbrains/cidr/lang/navigation/OCLineMarkerProvider$OCLineMarkerInfo;
        //   374: dup            
        //   375: aload           6
        //   377: aload           5
        //   379: bipush          11
        //   381: aconst_null    
        //   382: invokespecial   com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider$OCLineMarkerInfo.<init>:(Lcom/jetbrains/cidr/lang/navigation/OCGotoAction;Lcom/intellij/psi/PsiElement;ILcom/jetbrains/cidr/lang/navigation/OCLineMarkerProvider$1;)V
        //   385: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   390: pop            
        //   391: goto            398
        //   394: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   397: athrow         
        //   398: goto            336
        //   401: aload_1        
        //   402: aload_3        
        //   403: aload_3        
        //   404: invokeinterface java/util/List.size:()I
        //   409: anewarray       Lcom/jetbrains/cidr/lang/navigation/OCLineMarkerProvider$OCLineMarkerInfo;
        //   412: invokeinterface java/util/List.toArray:([Ljava/lang/Object;)[Ljava/lang/Object;
        //   417: checkcast       [Lcom/jetbrains/cidr/lang/navigation/OCLineMarkerProvider$OCLineMarkerInfo;
        //   420: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Lcom/intellij/psi/PsiElement;[Lcom/jetbrains/cidr/lang/navigation/OCLineMarkerProvider$OCLineMarkerInfo;)Lcom/jetbrains/cidr/lang/navigation/OCLineMarkerProvider$OCLineMarkerInfo;
        //   423: areturn        
        //   424: aload_1        
        //   425: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.d:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiNameIdentifierOwner;
        //   428: astore_1       
        //   429: aload_1        
        //   430: instanceof      Lcom/jetbrains/cidr/lang/psi/OCClassPredeclaration;
        //   433: ifne            511
        //   436: aload_1        
        //   437: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSynthesizeProperty;
        //   440: ifne            511
        //   443: goto            450
        //   446: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   449: athrow         
        //   450: aload_1        
        //   451: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   454: ifne            511
        //   457: goto            464
        //   460: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   463: athrow         
        //   464: aload_1        
        //   465: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMethodSelectorPart;
        //   468: ifne            511
        //   471: goto            478
        //   474: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   477: athrow         
        //   478: aload_1        
        //   479: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //   482: ifeq            517
        //   485: goto            492
        //   488: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   491: athrow         
        //   492: aload_1        
        //   493: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   498: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSynthesizeProperty;
        //   501: ifeq            517
        //   504: goto            511
        //   507: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   510: athrow         
        //   511: aconst_null    
        //   512: areturn        
        //   513: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   516: athrow         
        //   517: aload_1        
        //   518: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.getAction:(Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/navigation/OCGotoAction;
        //   521: astore_3       
        //   522: aload_3        
        //   523: ifnull          543
        //   526: new             Lcom/jetbrains/cidr/lang/navigation/OCLineMarkerProvider$OCLineMarkerInfo;
        //   529: dup            
        //   530: aload_3        
        //   531: aload_1        
        //   532: bipush          11
        //   534: aconst_null    
        //   535: invokespecial   com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider$OCLineMarkerInfo.<init>:(Lcom/jetbrains/cidr/lang/navigation/OCGotoAction;Lcom/intellij/psi/PsiElement;ILcom/jetbrains/cidr/lang/navigation/OCLineMarkerProvider$1;)V
        //   538: areturn        
        //   539: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   542: athrow         
        //   543: aconst_null    
        //   544: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      11     14     18     Ljava/lang/IllegalArgumentException;
        //  7      28     31     35     Ljava/lang/IllegalArgumentException;
        //  18     44     47     51     Ljava/lang/IllegalArgumentException;
        //  59     75     78     82     Ljava/lang/IllegalArgumentException;
        //  134    142    142    146    Ljava/lang/IllegalArgumentException;
        //  146    158    161    165    Ljava/lang/IllegalArgumentException;
        //  152    169    169    173    Ljava/lang/IllegalArgumentException;
        //  365    391    394    398    Ljava/lang/IllegalArgumentException;
        //  429    443    446    450    Ljava/lang/IllegalArgumentException;
        //  436    457    460    464    Ljava/lang/IllegalArgumentException;
        //  450    471    474    478    Ljava/lang/IllegalArgumentException;
        //  464    485    488    492    Ljava/lang/IllegalArgumentException;
        //  478    504    507    511    Ljava/lang/IllegalArgumentException;
        //  492    513    513    517    Ljava/lang/IllegalArgumentException;
        //  522    539    539    543    Ljava/lang/IllegalArgumentException;
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
    
    @Nullable
    private static OCLineMarkerInfo c(final PsiElement psiElement) {
        Object o = null;
        if (psiElement instanceof OCClassPredeclarationList) {
            o = ((OCClassPredeclarationList)psiElement).getPredeclarations();
        }
        else if (psiElement instanceof OCDeclaration) {
            o = ((OCDeclaration)psiElement).getDeclarators();
        }
        if (o != null) {
            final LinkedList<OCLineMarkerInfo> list = new LinkedList<OCLineMarkerInfo>();
            for (final PsiElement psiElement2 : o) {
                final OCGotoAction action = OCGotoSuperHandler.getAction(psiElement2);
                try {
                    if (action == null) {
                        continue;
                    }
                    list.add(new OCLineMarkerInfo(action, psiElement2, 11));
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
            }
            return a(psiElement, (OCLineMarkerInfo[])list.toArray(new OCLineMarkerInfo[list.size()]));
        }
        final PsiNameIdentifierOwner d = d(psiElement);
        Label_0170: {
            try {
                if (d instanceof OCClassPredeclaration) {
                    break Label_0170;
                }
                final PsiNameIdentifierOwner psiNameIdentifierOwner = d;
                final boolean b = psiNameIdentifierOwner instanceof OCDeclarator;
                if (b) {
                    break Label_0170;
                }
                break Label_0170;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final PsiNameIdentifierOwner psiNameIdentifierOwner = d;
                final boolean b = psiNameIdentifierOwner instanceof OCDeclarator;
                if (b) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        final OCGotoAction action2 = OCGotoSuperHandler.getAction((PsiElement)d);
        Label_0196: {
            try {
                if (action2 == null) {
                    return null;
                }
                final PsiNameIdentifierOwner psiNameIdentifierOwner2 = d;
                if (psiNameIdentifierOwner2 != null) {
                    break Label_0196;
                }
                return null;
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            try {
                final PsiNameIdentifierOwner psiNameIdentifierOwner2 = d;
                if (psiNameIdentifierOwner2 != null) {
                    return new OCLineMarkerInfo(action2, (PsiElement)d, 11);
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        return null;
    }
    
    private static void a(final PsiNameIdentifierOwner p0, final Collection<LineMarkerInfo> p1, final MultiMap<OCQualifiedName, OCQualifiedName> p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
        //     4: ifeq            81
        //     7: aload_0        
        //     8: instanceof      Lcom/jetbrains/cidr/lang/psi/OCClassPredeclaration;
        //    11: ifne            61
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    20: athrow         
        //    21: aload_0        
        //    22: checkcast       Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
        //    25: invokeinterface com/jetbrains/cidr/lang/psi/OCClassDeclaration.getCategory:()Ljava/lang/String;
        //    30: ifnonnull       61
        //    33: goto            40
        //    36: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    39: athrow         
        //    40: ldc             "NSObject"
        //    42: aload_0        
        //    43: invokeinterface com/intellij/psi/PsiNameIdentifierOwner.getName:()Ljava/lang/String;
        //    48: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    51: ifeq            66
        //    54: goto            61
        //    57: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    60: athrow         
        //    61: return         
        //    62: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    65: athrow         
        //    66: aload_0        
        //    67: checkcast       Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
        //    70: invokestatic    com/jetbrains/cidr/lang/search/OCClassInheritorsSearch.search:(Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;)Lcom/intellij/util/Query;
        //    73: astore_3       
        //    74: ldc             "Go to subclass"
        //    76: astore          4
        //    78: goto            380
        //    81: aload_0        
        //    82: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //    85: ifeq            146
        //    88: aload_0        
        //    89: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //    92: astore          5
        //    94: aload           5
        //    96: invokestatic    com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch.getParameters:(Lcom/jetbrains/cidr/lang/psi/OCMethod;)Lcom/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$SearchParameters;
        //    99: astore          6
        //   101: aload           5
        //   103: invokeinterface com/jetbrains/cidr/lang/psi/OCMethod.getContainingClass:()Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
        //   108: instanceof      Lcom/jetbrains/cidr/lang/psi/OCImplementation;
        //   111: ifeq            127
        //   114: aload           6
        //   116: iconst_1       
        //   117: invokevirtual   com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$SearchParameters.setImplementationsThenInterfaces:(Z)V
        //   120: goto            133
        //   123: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   126: athrow         
        //   127: aload           6
        //   129: iconst_1       
        //   130: invokevirtual   com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$SearchParameters.setInterfacesThenImplementations:(Z)V
        //   133: aload           6
        //   135: invokestatic    com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch.search:(Lcom/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$SearchParameters;)Lcom/intellij/util/Query;
        //   138: astore_3       
        //   139: ldc             "Go to overridden methods"
        //   141: astore          4
        //   143: goto            380
        //   146: aload_0        
        //   147: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStruct;
        //   150: ifeq            188
        //   153: aload_0        
        //   154: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStruct;
        //   157: invokeinterface com/jetbrains/cidr/lang/psi/OCStruct.isDeclaration:()Z
        //   162: ifeq            187
        //   165: goto            172
        //   168: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   171: athrow         
        //   172: aload_0        
        //   173: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStruct;
        //   176: invokestatic    com/jetbrains/cidr/lang/search/OCStructInheritorsSearch.search:(Lcom/jetbrains/cidr/lang/psi/OCStruct;)Lcom/intellij/util/Query;
        //   179: astore_3       
        //   180: ldc             "Go to subclass"
        //   182: astore          4
        //   184: goto            380
        //   187: return         
        //   188: aload_0        
        //   189: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   192: ifeq            379
        //   195: aload_0        
        //   196: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   199: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   204: astore          5
        //   206: aload           5
        //   208: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;
        //   211: ifeq            266
        //   214: aload           5
        //   216: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;
        //   219: invokestatic    com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch.getParameters:(Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;)Lcom/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$SearchParameters;
        //   222: astore          6
        //   224: aload           6
        //   226: invokestatic    com/jetbrains/cidr/lang/search/OCMemberInheritorsSearch.search:(Lcom/jetbrains/cidr/lang/search/OCMemberInheritorsSearch$SearchParameters;)Lcom/intellij/util/Query;
        //   229: astore_3       
        //   230: new             Ljava/lang/StringBuilder;
        //   233: dup            
        //   234: invokespecial   java/lang/StringBuilder.<init>:()V
        //   237: ldc             "Go to overridden "
        //   239: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   242: aload           5
        //   244: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   249: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.getNameLowercase:()Ljava/lang/String;
        //   252: invokestatic    com/intellij/openapi/util/text/StringUtil.pluralize:(Ljava/lang/String;)Ljava/lang/String;
        //   255: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   258: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   261: astore          4
        //   263: goto            376
        //   266: aload_0        
        //   267: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   270: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getParameterList:()Lcom/jetbrains/cidr/lang/psi/OCParameterList;
        //   275: ifnull          375
        //   278: aload_0        
        //   279: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   282: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.isPossibleStructMember:()Z
        //   287: ifeq            375
        //   290: goto            297
        //   293: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   296: athrow         
        //   297: aload           5
        //   299: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   302: ifeq            375
        //   305: goto            312
        //   308: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   311: athrow         
        //   312: aload           5
        //   314: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   317: aload_0        
        //   318: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   321: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   326: iconst_1       
        //   327: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionInheritorsSearch.getParameters:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Lcom/jetbrains/cidr/lang/psi/OCFile;Z)Lcom/jetbrains/cidr/lang/search/OCFunctionInheritorsSearch$SearchParameters;
        //   330: astore          6
        //   332: aload           5
        //   334: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isPredeclaration:()Z
        //   339: ifeq            355
        //   342: aload           6
        //   344: iconst_1       
        //   345: invokevirtual   com/jetbrains/cidr/lang/search/OCFunctionInheritorsSearch$SearchParameters.setPredeclarationsThenImplementations:(Z)V
        //   348: goto            361
        //   351: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   354: athrow         
        //   355: aload           6
        //   357: iconst_1       
        //   358: invokevirtual   com/jetbrains/cidr/lang/search/OCFunctionInheritorsSearch$SearchParameters.setImplementationsThenPredeclarations:(Z)V
        //   361: aload           6
        //   363: aload_2        
        //   364: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionInheritorsSearch.search:(Lcom/jetbrains/cidr/lang/search/OCFunctionInheritorsSearch$SearchParameters;Lcom/intellij/util/containers/MultiMap;)Lcom/intellij/util/Query;
        //   367: astore_3       
        //   368: ldc             "Go to overridden functions"
        //   370: astore          4
        //   372: goto            376
        //   375: return         
        //   376: goto            380
        //   379: return         
        //   380: aload_3        
        //   381: invokeinterface com/intellij/util/Query.findFirst:()Ljava/lang/Object;
        //   386: ifnull          485
        //   389: aload_0        
        //   390: invokeinterface com/intellij/psi/PsiNameIdentifierOwner.getNameIdentifier:()Lcom/intellij/psi/PsiElement;
        //   395: astore          5
        //   397: getstatic       com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.$assertionsDisabled:Z
        //   400: ifne            427
        //   403: aload           5
        //   405: ifnonnull       427
        //   408: goto            415
        //   411: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   414: athrow         
        //   415: new             Ljava/lang/AssertionError;
        //   418: dup            
        //   419: invokespecial   java/lang/AssertionError.<init>:()V
        //   422: athrow         
        //   423: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   426: athrow         
        //   427: aload_0        
        //   428: instanceof      Lcom/jetbrains/cidr/lang/psi/OCProtocol;
        //   431: ifeq            444
        //   434: getstatic       com/intellij/icons/AllIcons$Gutter.ImplementedMethod:Ljavax/swing/Icon;
        //   437: goto            447
        //   440: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   443: athrow         
        //   444: getstatic       com/intellij/icons/AllIcons$Gutter.OverridenMethod:Ljavax/swing/Icon;
        //   447: astore          6
        //   449: new             Lcom/jetbrains/cidr/lang/navigation/OCGotoActionAsync;
        //   452: dup            
        //   453: aload_0        
        //   454: aload           4
        //   456: aload           6
        //   458: aload_3        
        //   459: invokespecial   com/jetbrains/cidr/lang/navigation/OCGotoActionAsync.<init>:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljavax/swing/Icon;Lcom/intellij/util/Query;)V
        //   462: astore          7
        //   464: aload_1        
        //   465: new             Lcom/jetbrains/cidr/lang/navigation/OCLineMarkerProvider$OCLineMarkerInfo;
        //   468: dup            
        //   469: aload           7
        //   471: aload           5
        //   473: bipush          11
        //   475: aconst_null    
        //   476: invokespecial   com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider$OCLineMarkerInfo.<init>:(Lcom/jetbrains/cidr/lang/navigation/OCGotoAction;Lcom/intellij/psi/PsiElement;ILcom/jetbrains/cidr/lang/navigation/OCLineMarkerProvider$1;)V
        //   479: invokeinterface java/util/Collection.add:(Ljava/lang/Object;)Z
        //   484: pop            
        //   485: return         
        //    Signature:
        //  (Lcom/intellij/psi/PsiNameIdentifierOwner;Ljava/util/Collection<Lcom/intellij/codeInsight/daemon/LineMarkerInfo;>;Lcom/intellij/util/containers/MultiMap<Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;>;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      14     17     21     Ljava/lang/IllegalArgumentException;
        //  7      33     36     40     Ljava/lang/IllegalArgumentException;
        //  21     54     57     61     Ljava/lang/IllegalArgumentException;
        //  40     62     62     66     Ljava/lang/IllegalArgumentException;
        //  101    123    123    127    Ljava/lang/IllegalArgumentException;
        //  146    165    168    172    Ljava/lang/IllegalArgumentException;
        //  266    290    293    297    Ljava/lang/IllegalArgumentException;
        //  278    305    308    312    Ljava/lang/IllegalArgumentException;
        //  332    351    351    355    Ljava/lang/IllegalArgumentException;
        //  397    408    411    415    Ljava/lang/IllegalArgumentException;
        //  403    423    423    427    Ljava/lang/IllegalArgumentException;
        //  427    440    440    444    Ljava/lang/IllegalArgumentException;
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
    
    @Nullable
    public static OCGotoAction getAction(final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSymbolDeclarator;
        //     4: ifne            13
        //     7: aconst_null    
        //     8: areturn        
        //     9: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    12: athrow         
        //    13: aload_0        
        //    14: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSymbolDeclarator;
        //    17: invokeinterface com/jetbrains/cidr/lang/psi/OCSymbolDeclarator.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    22: astore_1       
        //    23: aload_1        
        //    24: ifnull          46
        //    27: aload_1        
        //    28: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    33: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.PARAMETER:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    36: if_acmpne       52
        //    39: goto            46
        //    42: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    45: athrow         
        //    46: aconst_null    
        //    47: areturn        
        //    48: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    51: athrow         
        //    52: aload_1        
        //    53: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.getRelatedSymbol:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    56: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolBase.locateDefinition:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Lcom/intellij/psi/PsiElement;
        //    59: ifnonnull       93
        //    62: aload_1        
        //    63: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //    66: ifeq            112
        //    69: goto            76
        //    72: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    75: athrow         
        //    76: aload_1        
        //    77: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //    80: invokestatic    com/jetbrains/cidr/lang/search/OCSearchUtil.hasRelatedSymbols:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;)Z
        //    83: ifeq            112
        //    86: goto            93
        //    89: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    92: athrow         
        //    93: new             Lcom/jetbrains/cidr/lang/navigation/OCLineMarkerProvider$1;
        //    96: dup            
        //    97: aload_0        
        //    98: ldc             "Go to definition/declaration"
        //   100: getstatic       com/jetbrains/cidr/lang/OCIcons.AssocFile:Ljavax/swing/Icon;
        //   103: aload_0        
        //   104: invokespecial   com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider$1.<init>:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;Ljavax/swing/Icon;Lcom/intellij/psi/PsiElement;)V
        //   107: areturn        
        //   108: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   111: athrow         
        //   112: aconst_null    
        //   113: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      9      9      13     Ljava/lang/IllegalArgumentException;
        //  23     39     42     46     Ljava/lang/IllegalArgumentException;
        //  27     48     48     52     Ljava/lang/IllegalArgumentException;
        //  52     69     72     76     Ljava/lang/IllegalArgumentException;
        //  62     86     89     93     Ljava/lang/IllegalArgumentException;
        //  76     108    108    112    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0076:
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
    public static OCSymbol getRelatedSymbol(final OCSymbol ocSymbol) {
        OCSymbol<PsiElement> associatedSymbol = null;
        Label_0018: {
            try {
                if (ocSymbol != null) {
                    associatedSymbol = ocSymbol.getAssociatedSymbol();
                    break Label_0018;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            associatedSymbol = null;
        }
        final OCSymbol<PsiElement> ocSymbol2 = associatedSymbol;
        Label_0059: {
            Label_0045: {
                try {
                    if (!(ocSymbol2 instanceof OCInstanceVariableSymbol)) {
                        break Label_0059;
                    }
                    final OCSymbol<PsiElement> ocSymbol3 = ocSymbol2;
                    final OCInstanceVariableSymbol ocInstanceVariableSymbol = (OCInstanceVariableSymbol)ocSymbol3;
                    final String s = ocInstanceVariableSymbol.getGeneratedFromProperty();
                    if (s != null) {
                        break Label_0045;
                    }
                    break Label_0059;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final OCSymbol<PsiElement> ocSymbol3 = ocSymbol2;
                    final OCInstanceVariableSymbol ocInstanceVariableSymbol = (OCInstanceVariableSymbol)ocSymbol3;
                    final String s = ocInstanceVariableSymbol.getGeneratedFromProperty();
                    if (s != null) {
                        return ((OCInstanceVariableSymbol)ocSymbol2).getAssociatedProperty();
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            try {
                if (ocSymbol2 != null || !(ocSymbol instanceof OCPropertySymbol)) {
                    return ocSymbol2;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        final OCInstanceVariableSymbol associatedIvar = ((OCPropertySymbol)ocSymbol).getAssociatedIvar();
        Label_0113: {
            try {
                if (associatedIvar == null) {
                    break Label_0113;
                }
                final OCInstanceVariableSymbol ocInstanceVariableSymbol2 = associatedIvar;
                final boolean b = ocInstanceVariableSymbol2.isClang4ImplicitIvar();
                if (!b) {
                    return associatedIvar;
                }
                break Label_0113;
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
            try {
                final OCInstanceVariableSymbol ocInstanceVariableSymbol2 = associatedIvar;
                final boolean b = ocInstanceVariableSymbol2.isClang4ImplicitIvar();
                if (!b) {
                    return associatedIvar;
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
        }
        final CommonProcessors.FindFirstProcessor findFirstProcessor = new CommonProcessors.FindFirstProcessor();
        ((OCPropertySymbol)ocSymbol).processAccessorMethods((Processor<? super OCMethodSymbol>)findFirstProcessor, false);
        return (OCSymbol)findFirstProcessor.getFoundValue();
    }
    
    @Nullable
    private static OCLineMarkerInfo a(final PsiElement psiElement, final OCLineMarkerInfo... array) {
        Icon icon = null;
        try {
            if (array.length == 0) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (array.length == 1) {
                return array[0];
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        for (final OCLineMarkerInfo ocLineMarkerInfo : array) {
            if (icon == null) {
                icon = ocLineMarkerInfo.getAction().getIcon();
            }
            else {
                icon = OCIcons.GutterMultiArrows;
            }
        }
        return new OCLineMarkerInfo((OCGotoAction)new OCGotoActionSync(psiElement, "Go to...", icon) {
            @Override
            protected ArrayList<OCSymbol> evaluateTargets() {
                final ArrayList<OCSymbol> list = new ArrayList<OCSymbol>();
                final OCLineMarkerInfo[] val$markers = array;
                for (int length = val$markers.length, i = 0; i < length; ++i) {
                    final List<? extends OCSymbol> targets = val$markers[i].getAction().getTargets();
                    if (targets != null) {
                        list.addAll(targets);
                    }
                }
                return list;
            }
        }, psiElement, 11);
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCLineMarkerProvider.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    static class OCLineMarkerInfo extends LineMarkerInfo<PsiElement>
    {
        private OCGotoAction myAction;
        
        private OCLineMarkerInfo(@NotNull final OCGotoAction myAction, @NotNull final PsiElement psiElement, final int n) {
            if (myAction == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "action", "com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider$OCLineMarkerInfo", "<init>"));
            }
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider$OCLineMarkerInfo", "<init>"));
            }
            super(psiElement, OCElementUtil.getTextRangeWithoutComments(psiElement), myAction.getIcon(), n, psiElement -> {
                try {
                    if (myAction == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "action", "com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider$OCLineMarkerInfo", "lambda$new$0"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                return myAction.getName();
            }, (GutterIconNavigationHandler)new GutterIconNavigationHandler<PsiElement>() {
                public void navigate(final MouseEvent mouseEvent, final PsiElement psiElement) {
                    FeatureUsageTracker.getInstance().triggerFeatureUsed("navigation.class.hierarchy");
                    myAction.navigate(mouseEvent, null);
                }
            }, GutterIconRenderer.Alignment.RIGHT);
            this.myAction = myAction;
        }
        
        public OCGotoAction getAction() {
            return this.myAction;
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
}
