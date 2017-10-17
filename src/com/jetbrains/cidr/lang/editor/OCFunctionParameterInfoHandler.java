// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor;

import com.intellij.lang.parameterInfo.ParameterInfoUIContext;
import com.jetbrains.cidr.lang.types.OCTypeOwner;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.OCLog;
import com.intellij.lang.parameterInfo.UpdateParameterInfoContext;
import com.intellij.lang.parameterInfo.ParameterInfoContext;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.featureStatistics.FeatureUsageTracker;
import com.jetbrains.cidr.lang.editor.parameterInfo.OCCompoundInitializerCallPlace;
import com.jetbrains.cidr.lang.psi.OCCompoundInitializer;
import com.jetbrains.cidr.lang.editor.parameterInfo.OCParameterListCallPlace;
import com.jetbrains.cidr.lang.psi.OCParameterList;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.editor.parameterInfo.OCArgumentListCallPlace;
import com.jetbrains.cidr.lang.psi.OCArgumentList;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCLambdaExpression;
import com.jetbrains.cidr.lang.psi.OCBlockExpression;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import com.jetbrains.cidr.lang.editor.parameterInfo.OCFunctionParameterInfo;
import java.util.Iterator;
import com.jetbrains.cidr.lang.editor.parameterInfo.OCFunctionCallPlace;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import java.util.List;
import java.util.Collections;
import com.intellij.openapi.util.Comparing;
import java.util.Collection;
import com.jetbrains.cidr.lang.editor.parameterInfo.OCFunctionCallOption;
import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.parameterInfo.CreateParameterInfoContext;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.PsiElement;
import com.intellij.lang.parameterInfo.ParameterInfoHandler;

public class OCFunctionParameterInfoHandler implements ParameterInfoHandler<PsiElement, FunctionInfoWrapper>
{
    public static final Logger LOG;
    private static final Condition<String> IS_UNNAMED;
    
    public boolean couldShowInLookup() {
        return true;
    }
    
    public boolean tracksParameterIndex() {
        return true;
    }
    
    public PsiElement findElementForParameterInfo(@NotNull final CreateParameterInfoContext createParameterInfoContext) {
        try {
            if (createParameterInfoContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/editor/OCFunctionParameterInfoHandler", "findElementForParameterInfo"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final PsiFile file = createParameterInfoContext.getFile();
        final OCFunctionCallPlace a = a(file, createParameterInfoContext.getOffset(), createParameterInfoContext.getParameterListStart());
        if (a != null) {
            final ArrayList<OCFunctionCallOption> list = new ArrayList<OCFunctionCallOption>();
            a.collectCallOptions(list);
            Collections.sort((List<Object>)list, (ocFunctionCallOption, ocFunctionCallOption2) -> Comparing.compare(ocFunctionCallOption.getOffset(), ocFunctionCallOption2.getOffset()));
            final ArrayList<Object> list2 = new ArrayList<Object>();
            final OCResolveContext ocResolveContext = new OCResolveContext((PsiElement)file);
            final Iterator<OCFunctionCallOption> iterator = list.iterator();
        Label_0132:
            while (iterator.hasNext()) {
                final OCFunctionParameterInfo parameterInfo = iterator.next().getParameterInfo();
                final OCFunctionType type = parameterInfo.getType();
                int i = 0;
                while (i < list2.size()) {
                    final OCFunctionType type2 = list2.get(i).getInfo().getType();
                    Label_0233: {
                        Label_0265: {
                            try {
                                if (!type2.equalsAfterResolving(type, (PsiElement)file)) {
                                    break Label_0265;
                                }
                                if (!a(type2)) {
                                    continue Label_0132;
                                }
                            }
                            catch (IllegalArgumentException ex2) {
                                throw a(ex2);
                            }
                            break Label_0233;
                        }
                        ++i;
                        continue;
                    }
                    if (!a(type)) {
                        list2.set(i, new FunctionInfoWrapper(parameterInfo, ocResolveContext));
                        continue Label_0132;
                    }
                    continue Label_0132;
                }
                list2.add(new FunctionInfoWrapper(parameterInfo, ocResolveContext));
            }
            createParameterInfoContext.setItemsToShow(list2.toArray());
        }
        try {
            if (a == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return a.getElement();
    }
    
    private static boolean a(@NotNull final OCFunctionType ocFunctionType) {
        try {
            if (ocFunctionType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "funcType", "com/jetbrains/cidr/lang/editor/OCFunctionParameterInfoHandler", "hasUnnamedParam"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final List<String> parameterNames = ocFunctionType.getParameterNames();
        Label_0082: {
            Label_0072: {
                try {
                    if (parameterNames != null) {
                        break Label_0082;
                    }
                    final OCFunctionType ocFunctionType2 = ocFunctionType;
                    final List<OCType> list = ocFunctionType2.getParameterTypes();
                    final int n = list.size();
                    if (n > 0) {
                        break Label_0072;
                    }
                    return false;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final OCFunctionType ocFunctionType2 = ocFunctionType;
                    final List<OCType> list = ocFunctionType2.getParameterTypes();
                    final int n = list.size();
                    if (n > 0) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            return false;
            try {
                if (ContainerUtil.find((Iterable)parameterNames, (Condition)OCFunctionParameterInfoHandler.IS_UNNAMED) != null) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return false;
    }
    
    @Nullable
    private static OCFunctionCallPlace a(final PsiFile psiFile, final int n, int n2) {
        try {
            if (psiFile == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
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
                if (psiElement == null) {
                    break Label_0050;
                }
                break Label_0050;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final PsiElement psiElement = element2;
                if (psiElement == null) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        PsiElement psiElement2 = PsiTreeUtil.findCommonContext(element, element2);
        try {
            if (psiElement2 == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        while (psiElement2 != null) {
            final OCFunctionCallPlace<? extends PsiElement> a = a(psiElement2);
            try {
                if (a != null) {
                    return a;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
            Label_0122: {
                try {
                    if (psiElement2 instanceof OCBlockExpression) {
                        break Label_0122;
                    }
                    final PsiElement psiElement3 = psiElement2;
                    final boolean b = psiElement3 instanceof OCLambdaExpression;
                    if (b) {
                        break Label_0122;
                    }
                    break Label_0122;
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
                try {
                    final PsiElement psiElement3 = psiElement2;
                    final boolean b = psiElement3 instanceof OCLambdaExpression;
                    if (b) {
                        return null;
                    }
                }
                catch (IllegalArgumentException ex7) {
                    throw a(ex7);
                }
            }
            psiElement2 = psiElement2.getContext();
        }
        return null;
    }
    
    @Nullable
    private static OCFunctionCallPlace<? extends PsiElement> a(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/editor/OCFunctionParameterInfoHandler", "callPlace"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (psiElement instanceof OCArgumentList) {
                return (OCFunctionCallPlace<? extends PsiElement>)new OCArgumentListCallPlace((OCArgumentList)psiElement);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (!(psiElement instanceof OCElement)) {
                return null;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (!((OCElement)psiElement).getContainingOCFile().isCpp()) {
                return null;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        try {
            if (psiElement instanceof OCParameterList) {
                return (OCFunctionCallPlace<? extends PsiElement>)new OCParameterListCallPlace((OCParameterList)psiElement);
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        try {
            if (psiElement instanceof OCCompoundInitializer) {
                return (OCFunctionCallPlace<? extends PsiElement>)new OCCompoundInitializerCallPlace((OCCompoundInitializer)psiElement);
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        return null;
    }
    
    public void showParameterInfo(@NotNull final PsiElement psiElement, @NotNull final CreateParameterInfoContext createParameterInfoContext) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/editor/OCFunctionParameterInfoHandler", "showParameterInfo"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (createParameterInfoContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/editor/OCFunctionParameterInfoHandler", "showParameterInfo"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        FeatureUsageTracker.getInstance().triggerFeatureUsed("codeassists.parameterInfo");
        createParameterInfoContext.showHint(psiElement, psiElement.getTextRange().getStartOffset(), (ParameterInfoHandler)this);
    }
    
    public Object[] getParametersForLookup(final LookupElement lookupElement, final ParameterInfoContext parameterInfoContext) {
        final Object object = lookupElement.getObject();
        Object[] array = null;
        try {
            if (object instanceof OCFunctionType) {
                array = new Object[] { object };
                return array;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return array;
    }
    
    public Object[] getParametersForDocumentation(final FunctionInfoWrapper functionInfoWrapper, final ParameterInfoContext parameterInfoContext) {
        return functionInfoWrapper.getInfo().getType().getParameterTypes().toArray();
    }
    
    public PsiElement findElementForUpdatingParameterInfo(@NotNull final UpdateParameterInfoContext updateParameterInfoContext) {
        try {
            if (updateParameterInfoContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/editor/OCFunctionParameterInfoHandler", "findElementForUpdatingParameterInfo"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCFunctionCallPlace a = a(updateParameterInfoContext.getFile(), updateParameterInfoContext.getOffset(), updateParameterInfoContext.getParameterListStart());
        try {
            if (a == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return a.getElement();
    }
    
    public void updateParameterInfo(@NotNull final PsiElement psiElement, @NotNull final UpdateParameterInfoContext updateParameterInfoContext) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameterOwner", "com/jetbrains/cidr/lang/editor/OCFunctionParameterInfoHandler", "updateParameterInfo"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (updateParameterInfoContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/editor/OCFunctionParameterInfoHandler", "updateParameterInfo"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final OCFunctionCallPlace<? extends PsiElement> a = a(psiElement);
        Logger log = null;
        boolean b = false;
        Label_0109: {
            try {
                log = OCLog.LOG;
                if (a != null) {
                    b = true;
                    break Label_0109;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            b = false;
        }
        log.assertTrue(b);
        final int offset = updateParameterInfoContext.getOffset();
        try {
            if (!psiElement.getTextRange().contains(offset)) {
                updateParameterInfoContext.removeHint();
                return;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        int currentParameter = 0;
        ASTNode astNode = psiElement.getNode().getFirstChildNode();
        while (true) {
            Label_0193: {
                Label_0186: {
                    try {
                        if (astNode == null) {
                            break;
                        }
                        final ASTNode astNode2 = astNode;
                        final int n = astNode2.getStartOffset();
                        final int n2 = offset;
                        if (n >= n2) {
                            break Label_0186;
                        }
                        break Label_0193;
                    }
                    catch (IllegalArgumentException ex5) {
                        throw a(ex5);
                    }
                    try {
                        final ASTNode astNode2 = astNode;
                        final int n = astNode2.getStartOffset();
                        final int n2 = offset;
                        if (n >= n2) {
                            break;
                        }
                    }
                    catch (IllegalArgumentException ex6) {
                        throw a(ex6);
                    }
                }
                try {
                    if (astNode.getElementType() == OCTokenTypes.COMMA) {
                        ++currentParameter;
                    }
                }
                catch (IllegalArgumentException ex7) {
                    throw a(ex7);
                }
            }
            astNode = astNode.getTreeNext();
        }
        updateParameterInfoContext.setCurrentParameter(currentParameter);
        final Object[] objectsToView = updateParameterInfoContext.getObjectsToView();
        final List argumentExpressions = a.getArgumentExpressions();
        for (int i = 0; i < objectsToView.length; ++i) {
            updateParameterInfoContext.setUIComponentEnabled(i, isApplicableBeforeIndex(((FunctionInfoWrapper)objectsToView[i]).getInfo().getType(), argumentExpressions, currentParameter));
        }
    }
    
    public static boolean isApplicableBeforeIndex(@NotNull final OCFunctionType p0, @NotNull final List<OCExpression> p1, final int p2) {
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
        //    18: ldc             "funcType"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/editor/OCFunctionParameterInfoHandler"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isApplicableBeforeIndex"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/editor/OCFunctionParameterInfoHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "argExpressions"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/editor/OCFunctionParameterInfoHandler"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "isApplicableBeforeIndex"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/editor/OCFunctionParameterInfoHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_0        
        //    89: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.getParameterTypes:()Ljava/util/List;
        //    92: astore_3       
        //    93: iload_2        
        //    94: ifeq            128
        //    97: aload_3        
        //    98: invokeinterface java/util/List.size:()I
        //   103: iload_2        
        //   104: if_icmpgt       128
        //   107: goto            114
        //   110: invokestatic    com/jetbrains/cidr/lang/editor/OCFunctionParameterInfoHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   113: athrow         
        //   114: aload_0        
        //   115: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.isVararg:()Z
        //   118: ifeq            154
        //   121: goto            128
        //   124: invokestatic    com/jetbrains/cidr/lang/editor/OCFunctionParameterInfoHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   127: athrow         
        //   128: aload_3        
        //   129: aload_1        
        //   130: iload_2        
        //   131: iconst_1       
        //   132: iadd           
        //   133: invokestatic    com/jetbrains/cidr/lang/editor/OCFunctionParameterInfoHandler.a:(Ljava/util/List;Ljava/util/List;I)Z
        //   136: ifeq            154
        //   139: goto            146
        //   142: invokestatic    com/jetbrains/cidr/lang/editor/OCFunctionParameterInfoHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   145: athrow         
        //   146: iconst_1       
        //   147: goto            155
        //   150: invokestatic    com/jetbrains/cidr/lang/editor/OCFunctionParameterInfoHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   153: athrow         
        //   154: iconst_0       
        //   155: ireturn        
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/types/OCFunctionType;Ljava/util/List<Lcom/jetbrains/cidr/lang/psi/OCExpression;>;I)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  93     107    110    114    Ljava/lang/IllegalArgumentException;
        //  97     121    124    128    Ljava/lang/IllegalArgumentException;
        //  114    139    142    146    Ljava/lang/IllegalArgumentException;
        //  128    150    150    154    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0114:
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
    
    private static boolean a(@NotNull final List<? extends OCType> list, @NotNull final List<OCExpression> list2, final int n) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "params", "com/jetbrains/cidr/lang/editor/OCFunctionParameterInfoHandler", "isAssignableParametersBeforeGivenIndex"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (list2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "args", "com/jetbrains/cidr/lang/editor/OCFunctionParameterInfoHandler", "isAssignableParametersBeforeGivenIndex"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        for (int min = Math.min(n, Math.min(list2.size(), list.size())), i = 0; i < min; ++i) {
            final OCType ocType = (OCType)list.get(i);
            final OCExpression ocExpression = list2.get(i);
            final OCType resolvedType = ocExpression.getResolvedType();
            try {
                if (ocType.checkCompatible(resolvedType, ocExpression, (PsiElement)ocExpression).getState().isError((PsiElement)ocExpression)) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return true;
    }
    
    public String getParameterCloseChars() {
        return ",){}";
    }
    
    public void updateUI(@NotNull final FunctionInfoWrapper functionInfoWrapper, @NotNull final ParameterInfoUIContext parameterInfoUIContext) {
        try {
            if (functionInfoWrapper == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "wrapper", "com/jetbrains/cidr/lang/editor/OCFunctionParameterInfoHandler", "updateUI"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (parameterInfoUIContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/editor/OCFunctionParameterInfoHandler", "updateUI"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        updateFunctionUI(functionInfoWrapper.getInfo(), parameterInfoUIContext);
    }
    
    public static String updateFunctionUI(@NotNull final OCFunctionParameterInfo p0, final ParameterInfoUIContext p1) {
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
        //    18: ldc             "info"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/editor/OCFunctionParameterInfoHandler"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "updateFunctionUI"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/editor/OCFunctionParameterInfoHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: new             Ljava/lang/StringBuilder;
        //    47: dup            
        //    48: invokespecial   java/lang/StringBuilder.<init>:()V
        //    51: astore_2       
        //    52: aload_1        
        //    53: invokeinterface com/intellij/lang/parameterInfo/ParameterInfoUIContext.getCurrentParameterIndex:()I
        //    58: istore_3       
        //    59: aload_0        
        //    60: invokevirtual   com/jetbrains/cidr/lang/editor/parameterInfo/OCFunctionParameterInfo.getType:()Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //    63: astore          4
        //    65: aload_0        
        //    66: invokevirtual   com/jetbrains/cidr/lang/editor/parameterInfo/OCFunctionParameterInfo.getDefaultParameterValues:()Ljava/util/List;
        //    69: astore          5
        //    71: aload           4
        //    73: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.getParameterTypes:()Ljava/util/List;
        //    76: astore          6
        //    78: aload           4
        //    80: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.getParameterNames:()Ljava/util/List;
        //    83: astore          7
        //    85: iconst_m1      
        //    86: istore          8
        //    88: iconst_m1      
        //    89: istore          9
        //    91: aload           6
        //    93: invokeinterface java/util/List.isEmpty:()Z
        //    98: ifeq            122
        //   101: aload_2        
        //   102: ldc             "parameter.info.no.parameters"
        //   104: iconst_0       
        //   105: anewarray       Ljava/lang/Object;
        //   108: invokestatic    com/intellij/codeInsight/CodeInsightBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   111: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   114: pop            
        //   115: goto            342
        //   118: invokestatic    com/jetbrains/cidr/lang/editor/OCFunctionParameterInfoHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   121: athrow         
        //   122: aload           6
        //   124: invokeinterface java/util/List.size:()I
        //   129: istore          10
        //   131: iconst_0       
        //   132: istore          11
        //   134: iload           11
        //   136: iload           10
        //   138: if_icmpge       342
        //   141: aload_2        
        //   142: invokevirtual   java/lang/StringBuilder.length:()I
        //   145: istore          12
        //   147: aload           6
        //   149: iload           11
        //   151: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   156: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   159: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:()Ljava/lang/String;
        //   162: astore          13
        //   164: aload           7
        //   166: ifnonnull       178
        //   169: ldc             "<unnamed>"
        //   171: goto            190
        //   174: invokestatic    com/jetbrains/cidr/lang/editor/OCFunctionParameterInfoHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   177: athrow         
        //   178: aload           7
        //   180: iload           11
        //   182: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   187: checkcast       Ljava/lang/String;
        //   190: astore          14
        //   192: aload           5
        //   194: ifnonnull       205
        //   197: aconst_null    
        //   198: goto            217
        //   201: invokestatic    com/jetbrains/cidr/lang/editor/OCFunctionParameterInfoHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   204: athrow         
        //   205: aload           5
        //   207: iload           11
        //   209: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   214: checkcast       Ljava/lang/String;
        //   217: astore          15
        //   219: aload_2        
        //   220: aload           13
        //   222: aload           14
        //   224: aload           15
        //   226: invokestatic    com/jetbrains/cidr/lang/util/OCDocUtil.parameterSignature:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   229: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   232: pop            
        //   233: aload_2        
        //   234: invokevirtual   java/lang/StringBuilder.length:()I
        //   237: istore          16
        //   239: iload           11
        //   241: iload           10
        //   243: iconst_1       
        //   244: isub           
        //   245: if_icmpge       262
        //   248: aload_2        
        //   249: ldc             ", "
        //   251: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   254: pop            
        //   255: goto            262
        //   258: invokestatic    com/jetbrains/cidr/lang/editor/OCFunctionParameterInfoHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   261: athrow         
        //   262: aload_1        
        //   263: invokeinterface com/intellij/lang/parameterInfo/ParameterInfoUIContext.isUIComponentEnabled:()Z
        //   268: ifeq            336
        //   271: iload           11
        //   273: iload_3        
        //   274: if_icmpeq       328
        //   277: goto            284
        //   280: invokestatic    com/jetbrains/cidr/lang/editor/OCFunctionParameterInfoHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   283: athrow         
        //   284: iload           11
        //   286: iload           10
        //   288: iconst_1       
        //   289: isub           
        //   290: if_icmpne       336
        //   293: goto            300
        //   296: invokestatic    com/jetbrains/cidr/lang/editor/OCFunctionParameterInfoHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   299: athrow         
        //   300: aload           4
        //   302: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.isVararg:()Z
        //   305: ifeq            336
        //   308: goto            315
        //   311: invokestatic    com/jetbrains/cidr/lang/editor/OCFunctionParameterInfoHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   314: athrow         
        //   315: iload_3        
        //   316: iload           10
        //   318: if_icmplt       336
        //   321: goto            328
        //   324: invokestatic    com/jetbrains/cidr/lang/editor/OCFunctionParameterInfoHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   327: athrow         
        //   328: iload           12
        //   330: istore          8
        //   332: iload           16
        //   334: istore          9
        //   336: iinc            11, 1
        //   339: goto            134
        //   342: aload_1        
        //   343: aload_2        
        //   344: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   347: iload           8
        //   349: iload           9
        //   351: aload_1        
        //   352: invokeinterface com/intellij/lang/parameterInfo/ParameterInfoUIContext.isUIComponentEnabled:()Z
        //   357: ifne            368
        //   360: iconst_1       
        //   361: goto            369
        //   364: invokestatic    com/jetbrains/cidr/lang/editor/OCFunctionParameterInfoHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   367: athrow         
        //   368: iconst_0       
        //   369: iconst_0       
        //   370: iconst_0       
        //   371: aload_1        
        //   372: invokeinterface com/intellij/lang/parameterInfo/ParameterInfoUIContext.getDefaultParameterColor:()Ljava/awt/Color;
        //   377: invokeinterface com/intellij/lang/parameterInfo/ParameterInfoUIContext.setupUIComponentPresentation:(Ljava/lang/String;IIZZZLjava/awt/Color;)Ljava/lang/String;
        //   382: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  91     118    118    122    Ljava/lang/IllegalArgumentException;
        //  164    174    174    178    Ljava/lang/IllegalArgumentException;
        //  192    201    201    205    Ljava/lang/IllegalArgumentException;
        //  239    255    258    262    Ljava/lang/IllegalArgumentException;
        //  262    277    280    284    Ljava/lang/IllegalArgumentException;
        //  271    293    296    300    Ljava/lang/IllegalArgumentException;
        //  284    308    311    315    Ljava/lang/IllegalArgumentException;
        //  300    321    324    328    Ljava/lang/IllegalArgumentException;
        //  342    364    364    368    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0284:
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
    
    static {
        LOG = Logger.getInstance("#com.jetbrains.cidr.lang.editor.OCFunctionParameterInfoHandler");
        IS_UNNAMED = (s -> "<unnamed>".equals(s));
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    public static class FunctionInfoWrapper
    {
        @NotNull
        private final OCFunctionParameterInfo myInfo;
        @NotNull
        private final OCResolveContext myContext;
        
        public FunctionInfoWrapper(@NotNull final OCFunctionParameterInfo myInfo, @NotNull final OCResolveContext myContext) {
            if (myInfo == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "info", "com/jetbrains/cidr/lang/editor/OCFunctionParameterInfoHandler$FunctionInfoWrapper", "<init>"));
            }
            if (myContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/editor/OCFunctionParameterInfoHandler$FunctionInfoWrapper", "<init>"));
            }
            this.myInfo = myInfo;
            this.myContext = myContext;
        }
        
        @NotNull
        public OCFunctionParameterInfo getInfo() {
            OCFunctionParameterInfo myInfo;
            try {
                myInfo = this.myInfo;
                if (myInfo == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/OCFunctionParameterInfoHandler$FunctionInfoWrapper", "getInfo"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return myInfo;
        }
        
        @Override
        public int hashCode() {
            return this.myInfo.hashCode();
        }
        
        @Override
        public boolean equals(final Object o) {
            Label_0041: {
                try {
                    if (!(o instanceof FunctionInfoWrapper)) {
                        return false;
                    }
                    final FunctionInfoWrapper functionInfoWrapper = this;
                    final OCFunctionParameterInfo ocFunctionParameterInfo = functionInfoWrapper.myInfo;
                    final OCFunctionType ocFunctionType = ocFunctionParameterInfo.getType();
                    final Object o2 = o;
                    final FunctionInfoWrapper functionInfoWrapper2 = (FunctionInfoWrapper)o2;
                    final OCFunctionParameterInfo ocFunctionParameterInfo2 = functionInfoWrapper2.getInfo();
                    final OCFunctionType ocFunctionType2 = ocFunctionParameterInfo2.getType();
                    final FunctionInfoWrapper functionInfoWrapper3 = this;
                    final OCResolveContext ocResolveContext = functionInfoWrapper3.myContext;
                    final boolean b = ocFunctionType.equals(ocFunctionType2, ocResolveContext);
                    if (b) {
                        break Label_0041;
                    }
                    return false;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final FunctionInfoWrapper functionInfoWrapper = this;
                    final OCFunctionParameterInfo ocFunctionParameterInfo = functionInfoWrapper.myInfo;
                    final OCFunctionType ocFunctionType = ocFunctionParameterInfo.getType();
                    final Object o2 = o;
                    final FunctionInfoWrapper functionInfoWrapper2 = (FunctionInfoWrapper)o2;
                    final OCFunctionParameterInfo ocFunctionParameterInfo2 = functionInfoWrapper2.getInfo();
                    final OCFunctionType ocFunctionType2 = ocFunctionParameterInfo2.getType();
                    final FunctionInfoWrapper functionInfoWrapper3 = this;
                    final OCResolveContext ocResolveContext = functionInfoWrapper3.myContext;
                    final boolean b = ocFunctionType.equals(ocFunctionType2, ocResolveContext);
                    if (b) {
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
    }
}
