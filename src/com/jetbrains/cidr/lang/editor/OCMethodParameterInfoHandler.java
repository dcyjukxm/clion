// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor;

import com.intellij.lang.parameterInfo.ParameterInfoUIContext;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.intellij.lang.parameterInfo.UpdateParameterInfoContext;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiFile;
import com.intellij.lang.parameterInfo.ParameterInfoContext;
import com.intellij.codeInsight.lookup.LookupElement;
import java.util.List;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCMessageArgument;
import com.intellij.featureStatistics.FeatureUsageTracker;
import com.intellij.lang.parameterInfo.CreateParameterInfoContext;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.intellij.lang.parameterInfo.ParameterInfoHandler;

public class OCMethodParameterInfoHandler implements ParameterInfoHandler<OCSendMessageExpression, OCMethodSymbol>
{
    public boolean couldShowInLookup() {
        return true;
    }
    
    public boolean tracksParameterIndex() {
        return false;
    }
    
    public void showParameterInfo(@NotNull final OCSendMessageExpression ocSendMessageExpression, @NotNull final CreateParameterInfoContext createParameterInfoContext) {
        try {
            if (ocSendMessageExpression == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/editor/OCMethodParameterInfoHandler", "showParameterInfo"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (createParameterInfoContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/editor/OCMethodParameterInfoHandler", "showParameterInfo"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final List<OCMessageArgument> arguments = ocSendMessageExpression.getArguments();
        try {
            if (!arguments.isEmpty()) {
                FeatureUsageTracker.getInstance().triggerFeatureUsed("codeassists.parameterInfo");
                createParameterInfoContext.showHint((PsiElement)ocSendMessageExpression, arguments.get(0).getTextRange().getStartOffset(), (ParameterInfoHandler)this);
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
    }
    
    public Object[] getParametersForLookup(final LookupElement lookupElement, final ParameterInfoContext parameterInfoContext) {
        final Object object = lookupElement.getObject();
        try {
            if (object instanceof OCMethodSymbol) {
                return new Object[] { object };
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return null;
    }
    
    public OCSendMessageExpression findElementForParameterInfo(@NotNull final CreateParameterInfoContext createParameterInfoContext) {
        try {
            if (createParameterInfoContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/editor/OCMethodParameterInfoHandler", "findElementForParameterInfo"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCSendMessageExpression a = a(createParameterInfoContext.getFile(), createParameterInfoContext.getOffset(), createParameterInfoContext.getParameterListStart());
        if (a != null) {
            createParameterInfoContext.setItemsToShow(a.getProbableResponders().getFilteredByStaticnessResponders().toArray());
            return a;
        }
        return null;
    }
    
    @Nullable
    private static OCSendMessageExpression a(final PsiFile p0, final int p1, final int p2) {
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
        //    25: ifnull          40
        //    28: aload           4
        //    30: ifnonnull       46
        //    33: goto            40
        //    36: invokestatic    com/jetbrains/cidr/lang/editor/OCMethodParameterInfoHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    39: athrow         
        //    40: aconst_null    
        //    41: areturn        
        //    42: invokestatic    com/jetbrains/cidr/lang/editor/OCMethodParameterInfoHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    45: athrow         
        //    46: aload_3        
        //    47: aload           4
        //    49: invokestatic    com/intellij/psi/util/PsiTreeUtil.findCommonContext:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //    52: astore          5
        //    54: aload           5
        //    56: ifnonnull       65
        //    59: aconst_null    
        //    60: areturn        
        //    61: invokestatic    com/jetbrains/cidr/lang/editor/OCMethodParameterInfoHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    64: athrow         
        //    65: aload           5
        //    67: ifnull          157
        //    70: aload           5
        //    72: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //    75: ifeq            116
        //    78: goto            85
        //    81: invokestatic    com/jetbrains/cidr/lang/editor/OCMethodParameterInfoHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    84: athrow         
        //    85: aload           5
        //    87: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //    92: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //    95: iload_1        
        //    96: if_icmpge       116
        //    99: goto            106
        //   102: invokestatic    com/jetbrains/cidr/lang/editor/OCMethodParameterInfoHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   105: athrow         
        //   106: aload           5
        //   108: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //   111: areturn        
        //   112: invokestatic    com/jetbrains/cidr/lang/editor/OCMethodParameterInfoHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   115: athrow         
        //   116: aload           5
        //   118: instanceof      Lcom/jetbrains/cidr/lang/psi/OCBlockExpression;
        //   121: ifne            139
        //   124: aload           5
        //   126: instanceof      Lcom/jetbrains/cidr/lang/psi/OCLambdaExpression;
        //   129: ifeq            145
        //   132: goto            139
        //   135: invokestatic    com/jetbrains/cidr/lang/editor/OCMethodParameterInfoHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   138: athrow         
        //   139: aconst_null    
        //   140: areturn        
        //   141: invokestatic    com/jetbrains/cidr/lang/editor/OCMethodParameterInfoHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   144: athrow         
        //   145: aload           5
        //   147: invokeinterface com/intellij/psi/PsiElement.getContext:()Lcom/intellij/psi/PsiElement;
        //   152: astore          5
        //   154: goto            65
        //   157: aconst_null    
        //   158: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  24     33     36     40     Ljava/lang/IllegalArgumentException;
        //  28     42     42     46     Ljava/lang/IllegalArgumentException;
        //  54     61     61     65     Ljava/lang/IllegalArgumentException;
        //  65     78     81     85     Ljava/lang/IllegalArgumentException;
        //  70     99     102    106    Ljava/lang/IllegalArgumentException;
        //  85     112    112    116    Ljava/lang/IllegalArgumentException;
        //  116    132    135    139    Ljava/lang/IllegalArgumentException;
        //  124    141    141    145    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0085:
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
    
    public Object[] getParametersForDocumentation(final OCMethodSymbol ocMethodSymbol, final ParameterInfoContext parameterInfoContext) {
        return ocMethodSymbol.getSelectors().toArray();
    }
    
    public OCSendMessageExpression findElementForUpdatingParameterInfo(@NotNull final UpdateParameterInfoContext updateParameterInfoContext) {
        try {
            if (updateParameterInfoContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/editor/OCMethodParameterInfoHandler", "findElementForUpdatingParameterInfo"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return a(updateParameterInfoContext.getFile(), updateParameterInfoContext.getOffset(), updateParameterInfoContext.getParameterListStart());
    }
    
    public void updateParameterInfo(@NotNull final OCSendMessageExpression ocSendMessageExpression, @NotNull final UpdateParameterInfoContext updateParameterInfoContext) {
        try {
            if (ocSendMessageExpression == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameterOwner", "com/jetbrains/cidr/lang/editor/OCMethodParameterInfoHandler", "updateParameterInfo"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (updateParameterInfoContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/editor/OCMethodParameterInfoHandler", "updateParameterInfo"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final int offset = updateParameterInfoContext.getEditor().getCaretModel().getOffset();
        try {
            if (!ocSendMessageExpression.getRangeWithMacros().contains(offset)) {
                updateParameterInfoContext.removeHint();
                return;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        int currentParameter = -1;
        for (ASTNode astNode = ocSendMessageExpression.getNode().getFirstChildNode(); astNode != null; astNode = astNode.getTreeNext()) {
            final IElementType elementType = astNode.getElementType();
            Label_0198: {
                Label_0191: {
                    try {
                        if (elementType != OCElementTypes.MESSAGE_ARGUMENT) {
                            break Label_0198;
                        }
                        ++currentParameter;
                        final ASTNode astNode2 = astNode;
                        final TextRange textRange = astNode2.getTextRange();
                        final int n = offset;
                        final boolean b = textRange.contains(n);
                        if (b) {
                            break Label_0191;
                        }
                        break Label_0198;
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                    try {
                        ++currentParameter;
                        final ASTNode astNode2 = astNode;
                        final TextRange textRange = astNode2.getTextRange();
                        final int n = offset;
                        final boolean b = textRange.contains(n);
                        if (b) {
                            break;
                        }
                    }
                    catch (IllegalArgumentException ex5) {
                        throw a(ex5);
                    }
                }
                try {
                    if (astNode.getStartOffset() >= offset) {
                        break;
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
            }
        }
        updateParameterInfoContext.setCurrentParameter(currentParameter);
        final Object[] objectsToView = updateParameterInfoContext.getObjectsToView();
        for (int i = 0; i < objectsToView.length; ++i) {
            final OCMethodSymbol ocMethodSymbol = (OCMethodSymbol)objectsToView[i];
            int n2 = 0;
            boolean b2 = false;
            Label_0294: {
                try {
                    n2 = i;
                    if (ocMethodSymbol.getSelectors().size() >= currentParameter) {
                        b2 = true;
                        break Label_0294;
                    }
                }
                catch (IllegalArgumentException ex7) {
                    throw a(ex7);
                }
                b2 = false;
            }
            updateParameterInfoContext.setUIComponentEnabled(n2, b2);
        }
    }
    
    public String getParameterCloseChars() {
        return ",){}";
    }
    
    public void updateUI(final OCMethodSymbol p0, @NotNull final ParameterInfoUIContext p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "context"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/editor/OCMethodParameterInfoHandler"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "updateUI"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/editor/OCMethodParameterInfoHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: new             Ljava/lang/StringBuilder;
        //    47: dup            
        //    48: invokespecial   java/lang/StringBuilder.<init>:()V
        //    51: astore_3       
        //    52: aload_2        
        //    53: invokeinterface com/intellij/lang/parameterInfo/ParameterInfoUIContext.getCurrentParameterIndex:()I
        //    58: istore          4
        //    60: aload_1        
        //    61: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getSelectors:()Ljava/util/List;
        //    66: astore          5
        //    68: aload           5
        //    70: invokeinterface java/util/List.size:()I
        //    75: istore          6
        //    77: iconst_m1      
        //    78: istore          7
        //    80: iconst_m1      
        //    81: istore          8
        //    83: iload           6
        //    85: ifeq            127
        //    88: iload           6
        //    90: iconst_1       
        //    91: if_icmpne       148
        //    94: goto            101
        //    97: invokestatic    com/jetbrains/cidr/lang/editor/OCMethodParameterInfoHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   100: athrow         
        //   101: aload           5
        //   103: iconst_0       
        //   104: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   109: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol$SelectorPartSymbol;
        //   112: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol$SelectorPartSymbol.getParameter:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   117: ifnonnull       148
        //   120: goto            127
        //   123: invokestatic    com/jetbrains/cidr/lang/editor/OCMethodParameterInfoHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   126: athrow         
        //   127: aload_3        
        //   128: ldc             "parameter.info.no.parameters"
        //   130: iconst_0       
        //   131: anewarray       Ljava/lang/Object;
        //   134: invokestatic    com/intellij/codeInsight/CodeInsightBundle.message:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   137: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   140: pop            
        //   141: goto            323
        //   144: invokestatic    com/jetbrains/cidr/lang/editor/OCMethodParameterInfoHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   147: athrow         
        //   148: iconst_0       
        //   149: istore          9
        //   151: iload           9
        //   153: iload           6
        //   155: if_icmpge       323
        //   158: aload           5
        //   160: iload           9
        //   162: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   167: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol$SelectorPartSymbol;
        //   170: astore          10
        //   172: aload_3        
        //   173: invokevirtual   java/lang/StringBuilder.length:()I
        //   176: istore          11
        //   178: aload_3        
        //   179: aload           10
        //   181: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol$SelectorPartSymbol.getSelectorName:()Ljava/lang/String;
        //   186: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   189: pop            
        //   190: aload           10
        //   192: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol$SelectorPartSymbol.getParameter:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   197: astore          12
        //   199: aload           12
        //   201: ifnull          234
        //   204: aload_3        
        //   205: bipush          40
        //   207: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   210: aload           12
        //   212: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   215: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:()Ljava/lang/String;
        //   218: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   221: ldc             ")"
        //   223: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   226: pop            
        //   227: goto            234
        //   230: invokestatic    com/jetbrains/cidr/lang/editor/OCMethodParameterInfoHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   233: athrow         
        //   234: aload_3        
        //   235: invokevirtual   java/lang/StringBuilder.length:()I
        //   238: istore          13
        //   240: iload           9
        //   242: iload           6
        //   244: iconst_1       
        //   245: isub           
        //   246: if_icmpge       263
        //   249: aload_3        
        //   250: ldc             " "
        //   252: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   255: pop            
        //   256: goto            263
        //   259: invokestatic    com/jetbrains/cidr/lang/editor/OCMethodParameterInfoHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   262: athrow         
        //   263: aload_2        
        //   264: invokeinterface com/intellij/lang/parameterInfo/ParameterInfoUIContext.isUIComponentEnabled:()Z
        //   269: ifeq            317
        //   272: iload           9
        //   274: iload           4
        //   276: if_icmpeq       309
        //   279: goto            286
        //   282: invokestatic    com/jetbrains/cidr/lang/editor/OCMethodParameterInfoHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   285: athrow         
        //   286: iload           9
        //   288: iload           6
        //   290: iconst_1       
        //   291: isub           
        //   292: if_icmpne       317
        //   295: goto            302
        //   298: invokestatic    com/jetbrains/cidr/lang/editor/OCMethodParameterInfoHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   301: athrow         
        //   302: goto            317
        //   305: invokestatic    com/jetbrains/cidr/lang/editor/OCMethodParameterInfoHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   308: athrow         
        //   309: iload           11
        //   311: istore          7
        //   313: iload           13
        //   315: istore          8
        //   317: iinc            9, 1
        //   320: goto            151
        //   323: aload_2        
        //   324: aload_3        
        //   325: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   328: iload           7
        //   330: iload           8
        //   332: aload_2        
        //   333: invokeinterface com/intellij/lang/parameterInfo/ParameterInfoUIContext.isUIComponentEnabled:()Z
        //   338: ifne            349
        //   341: iconst_1       
        //   342: goto            350
        //   345: invokestatic    com/jetbrains/cidr/lang/editor/OCMethodParameterInfoHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   348: athrow         
        //   349: iconst_0       
        //   350: iconst_0       
        //   351: iconst_0       
        //   352: aload_2        
        //   353: invokeinterface com/intellij/lang/parameterInfo/ParameterInfoUIContext.getDefaultParameterColor:()Ljava/awt/Color;
        //   358: invokeinterface com/intellij/lang/parameterInfo/ParameterInfoUIContext.setupUIComponentPresentation:(Ljava/lang/String;IIZZZLjava/awt/Color;)Ljava/lang/String;
        //   363: pop            
        //   364: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  83     94     97     101    Ljava/lang/IllegalArgumentException;
        //  88     120    123    127    Ljava/lang/IllegalArgumentException;
        //  101    144    144    148    Ljava/lang/IllegalArgumentException;
        //  199    227    230    234    Ljava/lang/IllegalArgumentException;
        //  240    256    259    263    Ljava/lang/IllegalArgumentException;
        //  263    279    282    286    Ljava/lang/IllegalArgumentException;
        //  272    295    298    302    Ljava/lang/IllegalArgumentException;
        //  286    305    305    309    Ljava/lang/IllegalArgumentException;
        //  323    345    345    349    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0101:
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
