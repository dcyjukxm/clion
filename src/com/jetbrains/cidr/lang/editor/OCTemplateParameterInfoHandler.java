// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.lang.parameterInfo.ParameterInfoContext;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.cpp.OCTypeParameterValueSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCTypeParameterTypeSymbol;
import com.intellij.lang.parameterInfo.ParameterInfoUIContext;
import java.util.Map;
import com.jetbrains.cidr.lang.types.visitors.OCTypeUnificationVisitor;
import com.jetbrains.cidr.lang.types.visitors.OCSimpleTypeSubstitution;
import com.jetbrains.cidr.lang.types.OCTypeArgument;
import com.jetbrains.cidr.lang.types.OCTypeParameterType;
import com.jetbrains.cidr.lang.symbols.OCTypeParameterSymbol;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.types.OCTypeUtils;
import com.jetbrains.cidr.lang.symbols.OCSymbolReferenceResolver;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCTemplateArgumentsOwner;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.parameterInfo.ParameterInfoUtils;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.lang.parameterInfo.UpdateParameterInfoContext;
import com.intellij.psi.PsiElement;
import com.intellij.featureStatistics.FeatureUsageTracker;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.Nullable;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.parameterInfo.CreateParameterInfoContext;
import com.jetbrains.cidr.lang.psi.OCTemplateArgumentList;
import com.intellij.lang.parameterInfo.ParameterInfoHandler;

public class OCTemplateParameterInfoHandler implements ParameterInfoHandler<OCTemplateArgumentList, Info>
{
    @Nullable
    public OCTemplateArgumentList findElementForParameterInfo(@NotNull final CreateParameterInfoContext createParameterInfoContext) {
        try {
            if (createParameterInfoContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/editor/OCTemplateParameterInfoHandler", "findElementForParameterInfo"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        final List<Info> a = a(createParameterInfoContext.getFile(), createParameterInfoContext.getOffset(), createParameterInfoContext.getParameterListStart());
        boolean b = false;
        Label_0094: {
            try {
                if (!a.isEmpty()) {
                    b = true;
                    break Label_0094;
                }
            }
            catch (UnsupportedOperationException ex2) {
                throw b(ex2);
            }
            b = false;
        }
        final boolean b2 = b;
        try {
            if (b2) {
                createParameterInfoContext.setItemsToShow(a.toArray());
            }
        }
        catch (UnsupportedOperationException ex3) {
            throw b(ex3);
        }
        try {
            if (b2) {
                return a.get(0).getList();
            }
        }
        catch (UnsupportedOperationException ex4) {
            throw b(ex4);
        }
        return null;
    }
    
    @NotNull
    private static List<Info> a(@Nullable final PsiFile p0, final int p1, final int p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: iload_1        
        //     2: iload_2        
        //     3: invokestatic    com/jetbrains/cidr/lang/editor/OCTemplateParameterInfoHandler.b:(Lcom/intellij/psi/PsiFile;II)Lcom/jetbrains/cidr/lang/psi/OCTemplateArgumentList;
        //     6: astore_3       
        //     7: aload_3        
        //     8: ifnonnull       60
        //    11: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //    14: dup            
        //    15: ifnonnull       59
        //    18: goto            25
        //    21: invokestatic    com/jetbrains/cidr/lang/editor/OCTemplateParameterInfoHandler.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //    24: athrow         
        //    25: new             Ljava/lang/IllegalStateException;
        //    28: dup            
        //    29: ldc             "@NotNull method %s.%s must not return null"
        //    31: ldc             2
        //    33: anewarray       Ljava/lang/Object;
        //    36: dup            
        //    37: ldc             0
        //    39: ldc             "com/jetbrains/cidr/lang/editor/OCTemplateParameterInfoHandler"
        //    41: aastore        
        //    42: dup            
        //    43: ldc             1
        //    45: ldc             "findInfos"
        //    47: aastore        
        //    48: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    51: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    54: athrow         
        //    55: invokestatic    com/jetbrains/cidr/lang/editor/OCTemplateParameterInfoHandler.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //    58: athrow         
        //    59: areturn        
        //    60: aload_3        
        //    61: ldc             Lcom/jetbrains/cidr/lang/psi/OCTemplateArgumentsOwner;.class
        //    63: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //    66: checkcast       Lcom/jetbrains/cidr/lang/psi/OCTemplateArgumentsOwner;
        //    69: astore          4
        //    71: aload           4
        //    73: ifnull          289
        //    76: aconst_null    
        //    77: astore          5
        //    79: aload           4
        //    81: invokeinterface com/jetbrains/cidr/lang/psi/OCTemplateArgumentsOwner.resolveTemplateDeclarations:()Ljava/util/Collection;
        //    86: invokeinterface java/util/Collection.iterator:()Ljava/util/Iterator;
        //    91: astore          6
        //    93: aload           6
        //    95: invokeinterface java/util/Iterator.hasNext:()Z
        //   100: ifeq            225
        //   103: aload           6
        //   105: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   110: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   113: astore          7
        //   115: aload           7
        //   117: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //   120: ifeq            222
        //   123: aload           7
        //   125: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //   128: astore          8
        //   130: aload           8
        //   132: invokeinterface com/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol.getTemplateParameters:()Ljava/util/List;
        //   137: astore          9
        //   139: aload           8
        //   141: invokeinterface com/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol.isVariadicTemplate:()Z
        //   146: istore          10
        //   148: aload           9
        //   150: invokeinterface java/util/List.isEmpty:()Z
        //   155: ifne            222
        //   158: aload           5
        //   160: ifnull          206
        //   163: goto            170
        //   166: invokestatic    com/jetbrains/cidr/lang/editor/OCTemplateParameterInfoHandler.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //   169: athrow         
        //   170: iload           10
        //   172: ifne            206
        //   175: goto            182
        //   178: invokestatic    com/jetbrains/cidr/lang/editor/OCTemplateParameterInfoHandler.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //   181: athrow         
        //   182: aload           5
        //   184: invokeinterface java/util/List.size:()I
        //   189: aload           9
        //   191: invokeinterface java/util/List.size:()I
        //   196: if_icmpge       222
        //   199: goto            206
        //   202: invokestatic    com/jetbrains/cidr/lang/editor/OCTemplateParameterInfoHandler.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //   205: athrow         
        //   206: aload           9
        //   208: astore          5
        //   210: iload           10
        //   212: ifeq            222
        //   215: goto            225
        //   218: invokestatic    com/jetbrains/cidr/lang/editor/OCTemplateParameterInfoHandler.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //   221: athrow         
        //   222: goto            93
        //   225: aload           5
        //   227: ifnull          289
        //   230: new             Lcom/jetbrains/cidr/lang/editor/OCTemplateParameterInfoHandler$Info;
        //   233: dup            
        //   234: aload_3        
        //   235: aload           5
        //   237: invokespecial   com/jetbrains/cidr/lang/editor/OCTemplateParameterInfoHandler$Info.<init>:(Lcom/jetbrains/cidr/lang/psi/OCTemplateArgumentList;Ljava/util/List;)V
        //   240: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
        //   243: dup            
        //   244: ifnonnull       288
        //   247: goto            254
        //   250: invokestatic    com/jetbrains/cidr/lang/editor/OCTemplateParameterInfoHandler.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //   253: athrow         
        //   254: new             Ljava/lang/IllegalStateException;
        //   257: dup            
        //   258: ldc             "@NotNull method %s.%s must not return null"
        //   260: ldc             2
        //   262: anewarray       Ljava/lang/Object;
        //   265: dup            
        //   266: ldc             0
        //   268: ldc             "com/jetbrains/cidr/lang/editor/OCTemplateParameterInfoHandler"
        //   270: aastore        
        //   271: dup            
        //   272: ldc             1
        //   274: ldc             "findInfos"
        //   276: aastore        
        //   277: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   280: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   283: athrow         
        //   284: invokestatic    com/jetbrains/cidr/lang/editor/OCTemplateParameterInfoHandler.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //   287: athrow         
        //   288: areturn        
        //   289: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   292: dup            
        //   293: ifnonnull       330
        //   296: new             Ljava/lang/IllegalStateException;
        //   299: dup            
        //   300: ldc             "@NotNull method %s.%s must not return null"
        //   302: ldc             2
        //   304: anewarray       Ljava/lang/Object;
        //   307: dup            
        //   308: ldc             0
        //   310: ldc             "com/jetbrains/cidr/lang/editor/OCTemplateParameterInfoHandler"
        //   312: aastore        
        //   313: dup            
        //   314: ldc             1
        //   316: ldc             "findInfos"
        //   318: aastore        
        //   319: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   322: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   325: athrow         
        //   326: invokestatic    com/jetbrains/cidr/lang/editor/OCTemplateParameterInfoHandler.b:(Ljava/lang/UnsupportedOperationException;)Ljava/lang/UnsupportedOperationException;
        //   329: athrow         
        //   330: areturn        
        //    Signature:
        //  (Lcom/intellij/psi/PsiFile;II)Ljava/util/List<Lcom/jetbrains/cidr/lang/editor/OCTemplateParameterInfoHandler$Info;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  7      18     21     25     Ljava/lang/UnsupportedOperationException;
        //  11     55     55     59     Ljava/lang/UnsupportedOperationException;
        //  148    163    166    170    Ljava/lang/UnsupportedOperationException;
        //  158    175    178    182    Ljava/lang/UnsupportedOperationException;
        //  170    199    202    206    Ljava/lang/UnsupportedOperationException;
        //  210    218    218    222    Ljava/lang/UnsupportedOperationException;
        //  225    247    250    254    Ljava/lang/UnsupportedOperationException;
        //  230    284    284    288    Ljava/lang/UnsupportedOperationException;
        //  289    326    326    330    Ljava/lang/UnsupportedOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0170:
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
    
    public void showParameterInfo(@NotNull final OCTemplateArgumentList list, @NotNull final CreateParameterInfoContext createParameterInfoContext) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/editor/OCTemplateParameterInfoHandler", "showParameterInfo"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        try {
            if (createParameterInfoContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/editor/OCTemplateParameterInfoHandler", "showParameterInfo"));
            }
        }
        catch (UnsupportedOperationException ex2) {
            throw b(ex2);
        }
        FeatureUsageTracker.getInstance().triggerFeatureUsed("codeassists.parameterInfo");
        createParameterInfoContext.showHint((PsiElement)list, list.getTextRange().getStartOffset(), (ParameterInfoHandler)this);
    }
    
    @Nullable
    public OCTemplateArgumentList findElementForUpdatingParameterInfo(@NotNull final UpdateParameterInfoContext updateParameterInfoContext) {
        try {
            if (updateParameterInfoContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/editor/OCTemplateParameterInfoHandler", "findElementForUpdatingParameterInfo"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        final OCTemplateArgumentList b = b(updateParameterInfoContext.getFile(), updateParameterInfoContext.getOffset(), updateParameterInfoContext.getParameterListStart());
        try {
            if (b == updateParameterInfoContext.getParameterOwner()) {
                final Object o = b;
                return (OCTemplateArgumentList)o;
            }
        }
        catch (UnsupportedOperationException ex2) {
            throw b(ex2);
        }
        final Object o = null;
        return (OCTemplateArgumentList)o;
    }
    
    public void updateParameterInfo(@NotNull final OCTemplateArgumentList list, @NotNull final UpdateParameterInfoContext updateParameterInfoContext) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "list", "com/jetbrains/cidr/lang/editor/OCTemplateParameterInfoHandler", "updateParameterInfo"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        try {
            if (updateParameterInfoContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/editor/OCTemplateParameterInfoHandler", "updateParameterInfo"));
            }
        }
        catch (UnsupportedOperationException ex2) {
            throw b(ex2);
        }
        final int offset = updateParameterInfoContext.getOffset();
        try {
            if (!list.getTextRange().contains(offset)) {
                updateParameterInfoContext.removeHint();
                return;
            }
        }
        catch (UnsupportedOperationException ex3) {
            throw b(ex3);
        }
        final int currentParameterIndex = ParameterInfoUtils.getCurrentParameterIndex(list.getNode(), offset, (IElementType)OCTokenTypes.COMMA);
        updateParameterInfoContext.setCurrentParameter(currentParameterIndex);
        final Object[] objectsToView = updateParameterInfoContext.getObjectsToView();
        for (int i = 0; i < objectsToView.length; ++i) {
            updateParameterInfoContext.setUIComponentEnabled(i, a(currentParameterIndex, (Info)objectsToView[i], list));
        }
    }
    
    private static boolean a(final int n, @NotNull final Info info, @NotNull final OCTemplateArgumentList list) {
        try {
            if (info == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "info", "com/jetbrains/cidr/lang/editor/OCTemplateParameterInfoHandler", "isApplicable"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "list", "com/jetbrains/cidr/lang/editor/OCTemplateParameterInfoHandler", "isApplicable"));
            }
        }
        catch (UnsupportedOperationException ex2) {
            throw b(ex2);
        }
        final List<OCTypeParameterSymbol> parameters = info.getParameters();
        boolean b = false;
        Label_0114: {
            try {
                if (ContainerUtil.find((Iterable)parameters, ocTypeParameterSymbol -> ocTypeParameterSymbol.isVariadic()) != null) {
                    b = true;
                    break Label_0114;
                }
            }
            catch (UnsupportedOperationException ex3) {
                throw b(ex3);
            }
            b = false;
        }
        final boolean b2 = b;
        Label_0144: {
            try {
                if (b2) {
                    break Label_0144;
                }
                final int n2 = n;
                final List<OCTypeParameterSymbol> list2 = parameters;
                final int n3 = list2.size();
                if (n2 >= n3) {
                    return false;
                }
                break Label_0144;
            }
            catch (UnsupportedOperationException ex4) {
                throw b(ex4);
            }
            try {
                final int n2 = n;
                final List<OCTypeParameterSymbol> list2 = parameters;
                final int n3 = list2.size();
                if (n2 >= n3) {
                    return false;
                }
            }
            catch (UnsupportedOperationException ex5) {
                throw b(ex5);
            }
        }
        final int min = Math.min(n + 1, list.getArguments().size());
        final List<OCTypeArgument> typeArguments = OCSymbolReferenceResolver.getTypeArguments((OCTemplateArgumentsOwner)PsiTreeUtil.getParentOfType((PsiElement)list, (Class)OCTemplateArgumentsOwner.class));
        final Map<OCTypeParameterSymbol, OCTypeArgument> typeParameterMap = OCTypeUtils.newTypeParameterMap();
        final OCResolveContext ocResolveContext = new OCResolveContext((PsiElement)list);
        for (int i = 0; i < min; ++i) {
            final OCTypeUnificationVisitor.UnificationResult unify = OCSimpleTypeSubstitution.unify(new OCTypeParameterType(parameters.get(Math.min(i, parameters.size() - 1))), typeArguments.get(i), typeParameterMap, ocResolveContext);
            try {
                if (unify == OCTypeUnificationVisitor.NOT_UNIFIED) {
                    return false;
                }
            }
            catch (UnsupportedOperationException ex6) {
                throw b(ex6);
            }
        }
        return true;
    }
    
    @Nullable
    public String getParameterCloseChars() {
        return ",>";
    }
    
    public void updateUI(@NotNull final Info info, @NotNull final ParameterInfoUIContext parameterInfoUIContext) {
        try {
            if (info == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "info", "com/jetbrains/cidr/lang/editor/OCTemplateParameterInfoHandler", "updateUI"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        try {
            if (parameterInfoUIContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/editor/OCTemplateParameterInfoHandler", "updateUI"));
            }
        }
        catch (UnsupportedOperationException ex2) {
            throw b(ex2);
        }
        final StringBuilder sb = new StringBuilder();
        final List<OCTypeParameterSymbol> parameters = info.getParameters();
        final int size = parameters.size();
        final int min = Math.min(parameterInfoUIContext.getCurrentParameterIndex(), size - 1);
        int n = -1;
        int n2 = -1;
        for (int i = 0; i < size; ++i) {
            final OCTypeParameterSymbol<OCTypeArgument> ocTypeParameterSymbol = parameters.get(i);
            final int length = sb.length();
            Label_0208: {
                Label_0194: {
                    try {
                        if (!(ocTypeParameterSymbol instanceof OCTypeParameterTypeSymbol)) {
                            break Label_0208;
                        }
                        final StringBuilder sb2 = sb;
                        final String s = "typename";
                        sb2.append(s);
                        final OCTypeParameterSymbol<OCTypeArgument> ocTypeParameterSymbol2 = ocTypeParameterSymbol;
                        final boolean b = ocTypeParameterSymbol2.isVariadic();
                        if (b) {
                            break Label_0194;
                        }
                        break Label_0208;
                    }
                    catch (UnsupportedOperationException ex3) {
                        throw b(ex3);
                    }
                    try {
                        final StringBuilder sb2 = sb;
                        final String s = "typename";
                        sb2.append(s);
                        final OCTypeParameterSymbol<OCTypeArgument> ocTypeParameterSymbol2 = ocTypeParameterSymbol;
                        final boolean b = ocTypeParameterSymbol2.isVariadic();
                        if (b) {
                            sb.append(" ...");
                        }
                        break Label_0208;
                    }
                    catch (UnsupportedOperationException ex4) {
                        throw b(ex4);
                    }
                }
                try {
                    if (ocTypeParameterSymbol instanceof OCTypeParameterValueSymbol) {
                        sb.append(((OCTypeParameterValueSymbol)ocTypeParameterSymbol).getType().getName());
                    }
                }
                catch (UnsupportedOperationException ex5) {
                    throw b(ex5);
                }
            }
            final String name = ocTypeParameterSymbol.getName();
            try {
                if (!"<unnamed>".equals(name)) {
                    sb.append(" ").append(name);
                }
            }
            catch (UnsupportedOperationException ex6) {
                throw b(ex6);
            }
            final OCTypeArgument defaultValue = ocTypeParameterSymbol.getDefaultValue();
            if (defaultValue != null) {
                sb.append(" = ").append(defaultValue.getNameForPresentation(OCType.Presentation.BEST, new OCResolveContext((PsiElement)info.getList().getContainingFile()), true, 0));
            }
            final int length2 = sb.length();
            try {
                if (i < size - 1) {
                    sb.append(", ");
                }
            }
            catch (UnsupportedOperationException ex7) {
                throw b(ex7);
            }
            String string = null;
            int n3 = 0;
            int n4 = 0;
            boolean b2 = false;
            Label_0424: {
                Label_0397: {
                    try {
                        if (i != min || !parameterInfoUIContext.isUIComponentEnabled()) {
                            break Label_0397;
                        }
                    }
                    catch (UnsupportedOperationException ex8) {
                        throw b(ex8);
                    }
                    n = length;
                    n2 = length2;
                    try {
                        string = sb.toString();
                        n3 = n;
                        n4 = n2;
                        if (!parameterInfoUIContext.isUIComponentEnabled()) {
                            b2 = true;
                            break Label_0424;
                        }
                    }
                    catch (UnsupportedOperationException ex9) {
                        throw b(ex9);
                    }
                }
                b2 = false;
            }
            parameterInfoUIContext.setupUIComponentPresentation(string, n3, n4, b2, false, false, parameterInfoUIContext.getDefaultParameterColor());
        }
    }
    
    public boolean tracksParameterIndex() {
        return true;
    }
    
    @Nullable
    public Object[] getParametersForDocumentation(final Info info, final ParameterInfoContext parameterInfoContext) {
        throw new UnsupportedOperationException();
    }
    
    public boolean couldShowInLookup() {
        return false;
    }
    
    @Nullable
    public Object[] getParametersForLookup(final LookupElement lookupElement, final ParameterInfoContext parameterInfoContext) {
        throw new UnsupportedOperationException();
    }
    
    @Nullable
    private static OCTemplateArgumentList b(@Nullable final PsiFile psiFile, final int n, int n2) {
        try {
            if (psiFile == null) {
                return null;
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        if (n2 == -1) {
            n2 = n;
        }
        final PsiElement element = psiFile.findElementAt(n);
        final PsiElement element2 = psiFile.findElementAt(n2);
        Label_0050: {
            try {
                if (element == null) {
                    break Label_0050;
                }
                final PsiElement psiElement = element2;
                if (psiElement != null) {
                    break Label_0050;
                }
                break Label_0050;
            }
            catch (UnsupportedOperationException ex2) {
                throw b(ex2);
            }
            try {
                final PsiElement psiElement = element2;
                if (psiElement != null) {
                    final PsiElement commonParent = PsiTreeUtil.findCommonParent(element, element2);
                    return (OCTemplateArgumentList)PsiTreeUtil.getContextOfType(commonParent, (Class)OCTemplateArgumentList.class, false);
                }
            }
            catch (UnsupportedOperationException ex3) {
                throw b(ex3);
            }
        }
        final PsiElement commonParent = null;
        return (OCTemplateArgumentList)PsiTreeUtil.getContextOfType(commonParent, (Class)OCTemplateArgumentList.class, false);
    }
    
    private static UnsupportedOperationException b(final UnsupportedOperationException ex) {
        return ex;
    }
    
    public static class Info
    {
        @NotNull
        private final OCTemplateArgumentList myList;
        @NotNull
        private final List<OCTypeParameterSymbol> myParameters;
        
        public Info(@NotNull final OCTemplateArgumentList myList, @NotNull final List<OCTypeParameterSymbol> myParameters) {
            if (myList == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "list", "com/jetbrains/cidr/lang/editor/OCTemplateParameterInfoHandler$Info", "<init>"));
            }
            if (myParameters == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "params", "com/jetbrains/cidr/lang/editor/OCTemplateParameterInfoHandler$Info", "<init>"));
            }
            this.myList = myList;
            this.myParameters = myParameters;
        }
        
        @NotNull
        public OCTemplateArgumentList getList() {
            OCTemplateArgumentList myList;
            try {
                myList = this.myList;
                if (myList == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/OCTemplateParameterInfoHandler$Info", "getList"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return myList;
        }
        
        @NotNull
        public List<OCTypeParameterSymbol> getParameters() {
            List<OCTypeParameterSymbol> myParameters;
            try {
                myParameters = this.myParameters;
                if (myParameters == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/OCTemplateParameterInfoHandler$Info", "getParameters"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return myParameters;
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
}
