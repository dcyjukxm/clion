// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import com.intellij.codeInsight.CodeInsightBundle;
import com.intellij.lang.parameterInfo.ParameterInfoUIContext;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.lang.parameterInfo.UpdateParameterInfoContext;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.diagnostic.Attachment;
import com.jetbrains.cidr.lang.OCLog;
import com.intellij.lang.parameterInfo.ParameterInfoContext;
import com.intellij.codeInsight.lookup.LookupElement;
import java.util.List;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCMacroCallArgument;
import com.intellij.featureStatistics.FeatureUsageTracker;
import com.intellij.lang.parameterInfo.CreateParameterInfoContext;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.cpp.OCMacroSymbol;
import com.jetbrains.cidr.lang.psi.OCMacroCall;
import com.intellij.lang.parameterInfo.ParameterInfoHandler;

public class OCMacroParameterInfoHandler implements ParameterInfoHandler<OCMacroCall, OCMacroSymbol>
{
    public boolean couldShowInLookup() {
        return true;
    }
    
    public boolean tracksParameterIndex() {
        return false;
    }
    
    public void showParameterInfo(@NotNull final OCMacroCall ocMacroCall, @NotNull final CreateParameterInfoContext createParameterInfoContext) {
        try {
            if (ocMacroCall == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/editor/OCMacroParameterInfoHandler", "showParameterInfo"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (createParameterInfoContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/editor/OCMacroParameterInfoHandler", "showParameterInfo"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final List<OCMacroCallArgument> arguments = ocMacroCall.getArguments();
        try {
            if (!arguments.isEmpty()) {
                FeatureUsageTracker.getInstance().triggerFeatureUsed("codeassists.parameterInfo");
                createParameterInfoContext.showHint((PsiElement)ocMacroCall, arguments.get(0).getTextRange().getStartOffset(), (ParameterInfoHandler)this);
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
    }
    
    public Object[] getParametersForLookup(final LookupElement lookupElement, final ParameterInfoContext parameterInfoContext) {
        final Object object = lookupElement.getObject();
        try {
            if (object instanceof OCMacroSymbol) {
                return new Object[] { object };
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return null;
    }
    
    public OCMacroCall findElementForParameterInfo(@NotNull final CreateParameterInfoContext createParameterInfoContext) {
        try {
            if (createParameterInfoContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/editor/OCMacroParameterInfoHandler", "findElementForParameterInfo"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCMacroCall a = a(createParameterInfoContext.getFile(), createParameterInfoContext.getOffset(), createParameterInfoContext.getParameterListStart());
        if (a != null) {
            final OCMacroSymbol resolveToSymbol = a.resolveToSymbol();
            try {
                if (resolveToSymbol == null) {
                    OCLog.LOG.error("can't resolve macro for '" + a.getText() + "'", new Attachment[] { new Attachment("file.mm", a.getContainingFile().getText()) });
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            createParameterInfoContext.setItemsToShow(new Object[] { resolveToSymbol });
            return a;
        }
        return null;
    }
    
    @Nullable
    private static OCMacroCall a(final PsiFile p0, final int p1, final int p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iload_2        
        //     1: iconst_m1      
        //     2: if_icmpne       7
        //     5: iload_1        
        //     6: istore_2       
        //     7: aload_0        
        //     8: iload_1        
        //     9: invokeinterface com/intellij/psi/PsiFile.findElementAt:(I)Lcom/intellij/psi/PsiElement;
        //    14: astore_3       
        //    15: aload_0        
        //    16: iload_2        
        //    17: invokeinterface com/intellij/psi/PsiFile.findElementAt:(I)Lcom/intellij/psi/PsiElement;
        //    22: astore          4
        //    24: aload_3        
        //    25: ifnull          53
        //    28: aload           4
        //    30: ifnull          53
        //    33: goto            40
        //    36: invokestatic    com/jetbrains/cidr/lang/editor/OCMacroParameterInfoHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    39: athrow         
        //    40: aload_3        
        //    41: aload           4
        //    43: invokestatic    com/intellij/psi/util/PsiTreeUtil.findCommonContext:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //    46: goto            54
        //    49: invokestatic    com/jetbrains/cidr/lang/editor/OCMacroParameterInfoHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    52: athrow         
        //    53: aconst_null    
        //    54: astore          5
        //    56: aload           5
        //    58: ifnonnull       67
        //    61: aconst_null    
        //    62: areturn        
        //    63: invokestatic    com/jetbrains/cidr/lang/editor/OCMacroParameterInfoHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    66: athrow         
        //    67: aload           5
        //    69: ifnull          135
        //    72: aload           5
        //    74: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMacroCall;
        //    77: ifne            135
        //    80: goto            87
        //    83: invokestatic    com/jetbrains/cidr/lang/editor/OCMacroParameterInfoHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    86: athrow         
        //    87: aload           5
        //    89: instanceof      Lcom/jetbrains/cidr/lang/psi/OCBlockExpression;
        //    92: ifne            117
        //    95: goto            102
        //    98: invokestatic    com/jetbrains/cidr/lang/editor/OCMacroParameterInfoHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   101: athrow         
        //   102: aload           5
        //   104: instanceof      Lcom/jetbrains/cidr/lang/psi/OCLambdaExpression;
        //   107: ifeq            123
        //   110: goto            117
        //   113: invokestatic    com/jetbrains/cidr/lang/editor/OCMacroParameterInfoHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   116: athrow         
        //   117: aconst_null    
        //   118: areturn        
        //   119: invokestatic    com/jetbrains/cidr/lang/editor/OCMacroParameterInfoHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   122: athrow         
        //   123: aload           5
        //   125: invokeinterface com/intellij/psi/PsiElement.getContext:()Lcom/intellij/psi/PsiElement;
        //   130: astore          5
        //   132: goto            67
        //   135: aload           5
        //   137: ifnonnull       146
        //   140: aconst_null    
        //   141: areturn        
        //   142: invokestatic    com/jetbrains/cidr/lang/editor/OCMacroParameterInfoHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   145: athrow         
        //   146: aload           5
        //   148: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMacroCall;
        //   151: astore          6
        //   153: aload           6
        //   155: invokeinterface com/jetbrains/cidr/lang/psi/OCMacroCall.getMacroReferenceElement:()Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //   160: astore          7
        //   162: aload           7
        //   164: ifnull          188
        //   167: aload           7
        //   169: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceElement.getRangeWithMacros:()Lcom/intellij/openapi/util/TextRange;
        //   174: iload_1        
        //   175: invokevirtual   com/intellij/openapi/util/TextRange.containsOffset:(I)Z
        //   178: ifeq            196
        //   181: goto            188
        //   184: invokestatic    com/jetbrains/cidr/lang/editor/OCMacroParameterInfoHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   187: athrow         
        //   188: aconst_null    
        //   189: goto            198
        //   192: invokestatic    com/jetbrains/cidr/lang/editor/OCMacroParameterInfoHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   195: athrow         
        //   196: aload           6
        //   198: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  24     33     36     40     Ljava/lang/IllegalArgumentException;
        //  28     49     49     53     Ljava/lang/IllegalArgumentException;
        //  56     63     63     67     Ljava/lang/IllegalArgumentException;
        //  67     80     83     87     Ljava/lang/IllegalArgumentException;
        //  72     95     98     102    Ljava/lang/IllegalArgumentException;
        //  87     110    113    117    Ljava/lang/IllegalArgumentException;
        //  102    119    119    123    Ljava/lang/IllegalArgumentException;
        //  135    142    142    146    Ljava/lang/IllegalArgumentException;
        //  162    181    184    188    Ljava/lang/IllegalArgumentException;
        //  167    192    192    196    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0087:
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
    
    public Object[] getParametersForDocumentation(final OCMacroSymbol ocMacroSymbol, final ParameterInfoContext parameterInfoContext) {
        return ocMacroSymbol.getParameterNames().toArray();
    }
    
    public OCMacroCall findElementForUpdatingParameterInfo(@NotNull final UpdateParameterInfoContext updateParameterInfoContext) {
        try {
            if (updateParameterInfoContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/editor/OCMacroParameterInfoHandler", "findElementForUpdatingParameterInfo"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return a(updateParameterInfoContext.getFile(), updateParameterInfoContext.getOffset(), updateParameterInfoContext.getParameterListStart());
    }
    
    public void updateParameterInfo(@NotNull final OCMacroCall ocMacroCall, @NotNull final UpdateParameterInfoContext updateParameterInfoContext) {
        try {
            if (ocMacroCall == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "call", "com/jetbrains/cidr/lang/editor/OCMacroParameterInfoHandler", "updateParameterInfo"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (updateParameterInfoContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/editor/OCMacroParameterInfoHandler", "updateParameterInfo"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final int offset = updateParameterInfoContext.getEditor().getCaretModel().getOffset();
        try {
            if (!ocMacroCall.getRangeWithMacros().contains(offset)) {
                updateParameterInfoContext.removeHint();
                return;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        int currentParameter = 0;
        ASTNode astNode = ocMacroCall.getNode().getFirstChildNode();
        while (true) {
            Label_0175: {
                Label_0168: {
                    try {
                        if (astNode == null) {
                            break;
                        }
                        final ASTNode astNode2 = astNode;
                        final int n = astNode2.getStartOffset();
                        final int n2 = offset;
                        if (n >= n2) {
                            break Label_0168;
                        }
                        break Label_0175;
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                    try {
                        final ASTNode astNode2 = astNode;
                        final int n = astNode2.getStartOffset();
                        final int n2 = offset;
                        if (n >= n2) {
                            break;
                        }
                    }
                    catch (IllegalArgumentException ex5) {
                        throw a(ex5);
                    }
                }
                try {
                    if (astNode.getElementType() == OCTokenTypes.COMMA) {
                        ++currentParameter;
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
            }
            astNode = astNode.getTreeNext();
        }
        updateParameterInfoContext.setCurrentParameter(currentParameter);
        final Object[] objectsToView = updateParameterInfoContext.getObjectsToView();
        for (int i = 0; i < objectsToView.length; ++i) {
            final OCMacroCall.ParameterCheckResult checkParameters = ocMacroCall.checkParameters((OCMacroSymbol)objectsToView[i]);
            int n3 = 0;
            boolean b = false;
            Label_0282: {
                try {
                    n3 = i;
                    if (checkParameters.getActualCount() <= checkParameters.getAllowedCount()) {
                        b = true;
                        break Label_0282;
                    }
                }
                catch (IllegalArgumentException ex7) {
                    throw a(ex7);
                }
                b = false;
            }
            updateParameterInfoContext.setUIComponentEnabled(n3, b);
        }
    }
    
    public String getParameterCloseChars() {
        return ",){}";
    }
    
    public void updateUI(final OCMacroSymbol ocMacroSymbol, @NotNull final ParameterInfoUIContext parameterInfoUIContext) {
        try {
            if (parameterInfoUIContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/editor/OCMacroParameterInfoHandler", "updateUI"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final StringBuilder sb = new StringBuilder();
        final List<String> userFriendlyParameterNames = ocMacroSymbol.getUserFriendlyParameterNames();
        final int size = userFriendlyParameterNames.size();
        final int min = Math.min(parameterInfoUIContext.getCurrentParameterIndex(), size - 1);
        int n = -1;
        int n2 = -1;
        String string = null;
        int n3 = 0;
        int n4 = 0;
        boolean b = false;
        Label_0240: {
            Label_0213: {
                try {
                    if (size == 0) {
                        sb.append(CodeInsightBundle.message("parameter.info.no.parameters", new Object[0]));
                        break Label_0213;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                for (int i = 0; i < size; ++i) {
                    final int length = sb.length();
                    sb.append(userFriendlyParameterNames.get(i));
                    final int length2 = sb.length();
                    try {
                        if (i < size - 1) {
                            sb.append(", ");
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                    try {
                        if (i != min || !parameterInfoUIContext.isUIComponentEnabled()) {
                            continue;
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                    n = length;
                    n2 = length2;
                }
                try {
                    string = sb.toString();
                    n3 = n;
                    n4 = n2;
                    if (!parameterInfoUIContext.isUIComponentEnabled()) {
                        b = true;
                        break Label_0240;
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
            }
            b = false;
        }
        parameterInfoUIContext.setupUIComponentPresentation(string, n3, n4, b, false, false, parameterInfoUIContext.getDefaultParameterColor());
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
