// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import java.util.List;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.util.OCParenthesesUtils;
import com.jetbrains.cidr.lang.util.OCExpressionEvaluator;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.intellij.featureStatistics.FeatureUsageTracker;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;
import com.intellij.util.containers.MultiMap;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;

public class OCConvertObjCLiteralIntentionAction extends OCPsiElementQuickFix<OCSendMessageExpression>
{
    private static final Converter[] CONVERTERS;
    private static final MultiMap<String, Converter> ourConverters;
    private static final Converter NUMBER_CONST_CONVERTER;
    private static final Converter NUMBER_CONVERTER;
    private Converter myConverter;
    
    public OCConvertObjCLiteralIntentionAction(@Nullable final OCSendMessageExpression ocSendMessageExpression, final Converter myConverter) {
        super((PsiElement)ocSendMessageExpression);
        this.myConverter = myConverter;
    }
    
    @NotNull
    public String getFamilyName() {
        String s;
        try {
            s = "Switch to Objective-C literal";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction", "getFamilyName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    @Override
    protected String getTextInternal() {
        return this.myConverter.getIntentionName();
    }
    
    public boolean isAvailable(@NotNull final OCSendMessageExpression ocSendMessageExpression) {
        try {
            if (ocSendMessageExpression == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction", "isAvailable"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (this.getReplacement(ocSendMessageExpression) != null) {
                return true;
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return false;
    }
    
    @Override
    protected void invoke(final PsiFile psiFile, @NotNull final OCSendMessageExpression ocSendMessageExpression) {
        try {
            if (ocSendMessageExpression == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expr", "com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction", "invoke"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        FeatureUsageTracker.getInstance().triggerFeatureUsed("refactoring.convertToLiteral");
        final String replacement = this.getReplacement(ocSendMessageExpression);
        Object expressionFromText = null;
        Label_0075: {
            try {
                if (replacement != null) {
                    expressionFromText = OCElementFactory.expressionFromText(replacement, (PsiElement)ocSendMessageExpression);
                    break Label_0075;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            expressionFromText = null;
        }
        final Object o = expressionFromText;
        try {
            if (o != null) {
                OCChangeUtil.replaceHandlingMacros((PsiElement)ocSendMessageExpression, (PsiElement)o);
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
    }
    
    @Nullable
    public String getReplacement(@Nullable final OCSendMessageExpression p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.findConverter:(Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;)Lcom/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction$Converter;
        //     4: astore_2       
        //     5: aload_2        
        //     6: ifnonnull       15
        //     9: aconst_null    
        //    10: areturn        
        //    11: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    14: athrow         
        //    15: aload_1        
        //    16: aload_2        
        //    17: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.a:(Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;Lcom/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction$Converter;)Ljava/util/List;
        //    20: astore_3       
        //    21: aload_2        
        //    22: invokevirtual   com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction$Converter.getReplacement:()Ljava/lang/String;
        //    25: astore          4
        //    27: aload_1        
        //    28: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getReceiverExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    33: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.diveIntoParentheses:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    36: astore          5
        //    38: iconst_0       
        //    39: istore          6
        //    41: iconst_1       
        //    42: istore          7
        //    44: aload_3        
        //    45: ifnull          64
        //    48: aload_3        
        //    49: invokeinterface java/util/List.isEmpty:()Z
        //    54: ifeq            70
        //    57: goto            64
        //    60: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    63: athrow         
        //    64: aconst_null    
        //    65: areturn        
        //    66: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    69: athrow         
        //    70: aload           5
        //    72: ifnull          94
        //    75: aload           4
        //    77: ldc             "#R"
        //    79: aload_0        
        //    80: aload           5
        //    82: aload           5
        //    84: iconst_1       
        //    85: iconst_2       
        //    86: invokevirtual   com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.tryGetReplacement:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/psi/OCExpression;ZI)Ljava/lang/String;
        //    89: invokevirtual   java/lang/String.replace:(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //    92: astore          4
        //    94: iload           6
        //    96: ifne            462
        //    99: iconst_0       
        //   100: istore          8
        //   102: iload           8
        //   104: aload_3        
        //   105: invokeinterface java/util/List.size:()I
        //   110: if_icmpge       357
        //   113: aload_3        
        //   114: iload           8
        //   116: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   121: checkcast       Ljava/util/List;
        //   124: astore          9
        //   126: aload           9
        //   128: invokestatic    com/intellij/util/containers/ContainerUtil.getFirstItem:(Ljava/util/List;)Ljava/lang/Object;
        //   131: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   134: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.diveIntoParentheses:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   137: astore          10
        //   139: aload           10
        //   141: ifnull          349
        //   144: ldc             ""
        //   146: astore          11
        //   148: aload_2        
        //   149: invokevirtual   com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction$Converter.getSpecialFlag:()Lcom/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction$SpecialFlag;
        //   152: getstatic       com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction$SpecialFlag.INTERLEAVE:Lcom/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction$SpecialFlag;
        //   155: if_acmpne       252
        //   158: aload_3        
        //   159: invokeinterface java/util/List.size:()I
        //   164: iconst_2       
        //   165: if_icmpne       252
        //   168: goto            175
        //   171: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   174: athrow         
        //   175: aload_3        
        //   176: iconst_1       
        //   177: iload           8
        //   179: isub           
        //   180: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   185: checkcast       Ljava/util/List;
        //   188: invokestatic    com/intellij/util/containers/ContainerUtil.getFirstItem:(Ljava/util/List;)Ljava/lang/Object;
        //   191: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   194: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.diveIntoParentheses:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   197: astore          12
        //   199: aload           12
        //   201: ifnull          249
        //   204: aload_0        
        //   205: aload           10
        //   207: aload           12
        //   209: iload           7
        //   211: ifeq            242
        //   214: goto            221
        //   217: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   220: athrow         
        //   221: iload           8
        //   223: iconst_1       
        //   224: if_icmpne       242
        //   227: goto            234
        //   230: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   233: athrow         
        //   234: iconst_1       
        //   235: goto            243
        //   238: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   241: athrow         
        //   242: iconst_0       
        //   243: iconst_m1      
        //   244: invokevirtual   com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.tryGetReplacement:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/psi/OCExpression;ZI)Ljava/lang/String;
        //   247: astore          11
        //   249: goto            289
        //   252: aload_0        
        //   253: aload           10
        //   255: aload           10
        //   257: iload           7
        //   259: ifeq            282
        //   262: iload           8
        //   264: ifne            282
        //   267: goto            274
        //   270: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   273: athrow         
        //   274: iconst_1       
        //   275: goto            283
        //   278: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   281: athrow         
        //   282: iconst_0       
        //   283: iconst_m1      
        //   284: invokevirtual   com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.tryGetReplacement:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/psi/OCExpression;ZI)Ljava/lang/String;
        //   287: astore          11
        //   289: aload           4
        //   291: new             Ljava/lang/StringBuilder;
        //   294: dup            
        //   295: invokespecial   java/lang/StringBuilder.<init>:()V
        //   298: ldc             "#P"
        //   300: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   303: iload           8
        //   305: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   308: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   311: new             Ljava/lang/StringBuilder;
        //   314: dup            
        //   315: invokespecial   java/lang/StringBuilder.<init>:()V
        //   318: aload           11
        //   320: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   323: aload_2        
        //   324: aload_1        
        //   325: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getMessageSelector:()Ljava/lang/String;
        //   330: aload           10
        //   332: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.a:(Lcom/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction$Converter;Ljava/lang/String;Lcom/jetbrains/cidr/lang/psi/OCExpression;)Ljava/lang/String;
        //   335: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   338: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   341: invokevirtual   java/lang/String.replace:(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   344: astore          4
        //   346: goto            351
        //   349: aconst_null    
        //   350: areturn        
        //   351: iinc            8, 1
        //   354: goto            102
        //   357: aload_3        
        //   358: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   363: astore          8
        //   365: aload           8
        //   367: invokeinterface java/util/Iterator.hasNext:()Z
        //   372: ifeq            411
        //   375: aload           8
        //   377: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   382: checkcast       Ljava/util/List;
        //   385: astore          9
        //   387: aload           9
        //   389: iconst_0       
        //   390: invokeinterface java/util/List.remove:(I)Ljava/lang/Object;
        //   395: pop            
        //   396: iload           6
        //   398: aload           9
        //   400: invokeinterface java/util/List.isEmpty:()Z
        //   405: ior            
        //   406: istore          6
        //   408: goto            365
        //   411: aload           4
        //   413: ldc             "#*"
        //   415: iload           6
        //   417: ifeq            429
        //   420: ldc             ""
        //   422: goto            451
        //   425: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   428: athrow         
        //   429: new             Ljava/lang/StringBuilder;
        //   432: dup            
        //   433: invokespecial   java/lang/StringBuilder.<init>:()V
        //   436: aload_2        
        //   437: invokevirtual   com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction$Converter.getReplacementLoop:()Ljava/lang/String;
        //   440: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   443: ldc             "#*"
        //   445: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   448: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   451: invokevirtual   java/lang/String.replace:(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   454: astore          4
        //   456: iconst_0       
        //   457: istore          7
        //   459: goto            94
        //   462: aload           4
        //   464: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  5      11     11     15     Ljava/lang/IllegalStateException;
        //  44     57     60     64     Ljava/lang/IllegalStateException;
        //  48     66     66     70     Ljava/lang/IllegalStateException;
        //  148    168    171    175    Ljava/lang/IllegalStateException;
        //  199    214    217    221    Ljava/lang/IllegalStateException;
        //  204    227    230    234    Ljava/lang/IllegalStateException;
        //  221    238    238    242    Ljava/lang/IllegalStateException;
        //  252    267    270    274    Ljava/lang/IllegalStateException;
        //  262    278    278    282    Ljava/lang/IllegalStateException;
        //  411    425    425    429    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0221:
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
    
    private static String a(final Converter converter, final String s, final OCExpression ocExpression) {
        if (converter == OCConvertObjCLiteralIntentionAction.NUMBER_CONST_CONVERTER) {
            final Number evaluate = OCExpressionEvaluator.evaluate(ocExpression);
            Label_0058: {
                Label_0042: {
                    try {
                        if (!OCExpressionEvaluator.isIntValue(evaluate)) {
                            break Label_0058;
                        }
                        final String s2 = s;
                        final String s3 = "numberWithFloat:";
                        final boolean b = s2.equals(s3);
                        if (b) {
                            return ".0F";
                        }
                        break Label_0042;
                    }
                    catch (IllegalStateException ex) {
                        throw a(ex);
                    }
                    try {
                        final String s2 = s;
                        final String s3 = "numberWithFloat:";
                        final boolean b = s2.equals(s3);
                        if (b) {
                            return ".0F";
                        }
                    }
                    catch (IllegalStateException ex2) {
                        throw a(ex2);
                    }
                    try {
                        if (s.equals("numberWithDouble:")) {
                            return ".0";
                        }
                        return "";
                    }
                    catch (IllegalStateException ex3) {
                        throw a(ex3);
                    }
                }
                try {
                    if (s.equals("numberWithFloat:")) {
                        return "F";
                    }
                }
                catch (IllegalStateException ex4) {
                    throw a(ex4);
                }
            }
        }
        return "";
    }
    
    @NotNull
    public String tryGetReplacement(@NotNull final OCExpression ocExpression, @NotNull final OCExpression ocExpression2, final boolean b, final int n) {
        try {
            if (ocExpression == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expr", "com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction", "tryGetReplacement"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (ocExpression2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "spacesAndCommentsFrom", "com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction", "tryGetReplacement"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        String replacement = null;
        Label_0111: {
            try {
                if (ocExpression instanceof OCSendMessageExpression) {
                    replacement = this.getReplacement((OCSendMessageExpression)ocExpression);
                    break Label_0111;
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
            replacement = null;
        }
        String s = replacement;
        final int precedence = OCParenthesesUtils.getPrecedence(ocExpression, false);
        String string = null;
        Label_0194: {
            Label_0186: {
                Label_0138: {
                    try {
                        if (s != null) {
                            break Label_0194;
                        }
                        final int n2 = n;
                        final int n3 = -1;
                        if (n2 != n3) {
                            break Label_0138;
                        }
                        break Label_0186;
                    }
                    catch (IllegalStateException ex4) {
                        throw a(ex4);
                    }
                    try {
                        final int n2 = n;
                        final int n3 = -1;
                        if (n2 == n3) {
                            break Label_0186;
                        }
                        if (precedence <= n) {
                            break Label_0186;
                        }
                    }
                    catch (IllegalStateException ex5) {
                        throw a(ex5);
                    }
                }
                s = "(" + ocExpression.getTextWithMacros() + ")";
                break Label_0194;
            }
            s = ocExpression.getTextWithMacros();
            try {
                string = OCElementUtil.getLeadingCommentsAndWhitespaces((PsiElement)ocExpression2, b) + s + OCElementUtil.getTrailingCommentsAndWhitespaces((PsiElement)ocExpression2, b);
                if (string == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction", "tryGetReplacement"));
                }
            }
            catch (IllegalStateException ex6) {
                throw a(ex6);
            }
        }
        return string;
    }
    
    @Nullable
    public static Converter findConverter(@Nullable final OCSendMessageExpression p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnull          18
        //     4: aload_0        
        //     5: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.containsDirectives:(Lcom/intellij/psi/PsiElement;)Z
        //     8: ifeq            24
        //    11: goto            18
        //    14: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    17: athrow         
        //    18: aconst_null    
        //    19: areturn        
        //    20: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    23: athrow         
        //    24: aload_0        
        //    25: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getReceiverExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    30: astore_1       
        //    31: aload_1        
        //    32: ifnull          51
        //    35: aload_1        
        //    36: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    41: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getGuessedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    44: goto            52
        //    47: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    50: athrow         
        //    51: aconst_null    
        //    52: astore_2       
        //    53: aload_0        
        //    54: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getMessageSelector:()Ljava/lang/String;
        //    59: astore_3       
        //    60: aload_3        
        //    61: ldc             "numberWith"
        //    63: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //    66: ifeq            265
        //    69: aload_0        
        //    70: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getProbableResponders:()Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression$ProbableResponders;
        //    75: invokevirtual   com/jetbrains/cidr/lang/psi/OCSendMessageExpression$ProbableResponders.getKnownResponder:()Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //    78: astore          4
        //    80: aload_0        
        //    81: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getArgumentExpressions:()Ljava/util/List;
        //    86: invokestatic    com/intellij/util/containers/ContainerUtil.getFirstItem:(Ljava/util/List;)Ljava/lang/Object;
        //    89: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    92: astore          5
        //    94: aload           5
        //    96: ifnull          113
        //    99: aload           5
        //   101: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getTextWithMacros:()Ljava/lang/String;
        //   106: goto            114
        //   109: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   112: athrow         
        //   113: aconst_null    
        //   114: astore          6
        //   116: aload           4
        //   118: ifnull          141
        //   121: aload           4
        //   123: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getParameterSymbols:()Ljava/util/List;
        //   128: invokestatic    com/intellij/util/containers/ContainerUtil.getFirstItem:(Ljava/util/List;)Ljava/lang/Object;
        //   131: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   134: goto            142
        //   137: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   140: athrow         
        //   141: aconst_null    
        //   142: astore          7
        //   144: aload           7
        //   146: ifnull          205
        //   149: aload           5
        //   151: ifnull          205
        //   154: goto            161
        //   157: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   160: athrow         
        //   161: aload           7
        //   163: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   166: aload_0        
        //   167: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   172: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   175: aload           5
        //   177: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   182: aload           5
        //   184: aload           5
        //   186: invokevirtual   com/jetbrains/cidr/lang/types/OCType.checkCompatible:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckResult;
        //   189: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckResult.getState:()Lcom/jetbrains/cidr/lang/types/OCType$TypeCheckState;
        //   192: invokevirtual   com/jetbrains/cidr/lang/types/OCType$TypeCheckState.isOK:()Z
        //   195: ifne            211
        //   198: goto            205
        //   201: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   204: athrow         
        //   205: aconst_null    
        //   206: areturn        
        //   207: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   210: athrow         
        //   211: aload           5
        //   213: instanceof      Lcom/jetbrains/cidr/lang/psi/OCLiteralExpression;
        //   216: ifne            253
        //   219: ldc             "YES"
        //   221: aload           6
        //   223: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   226: ifne            253
        //   229: goto            236
        //   232: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   235: athrow         
        //   236: ldc             "NO"
        //   238: aload           6
        //   240: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   243: ifeq            261
        //   246: goto            253
        //   249: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   252: athrow         
        //   253: getstatic       com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.NUMBER_CONST_CONVERTER:Lcom/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction$Converter;
        //   256: areturn        
        //   257: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   260: athrow         
        //   261: getstatic       com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.NUMBER_CONVERTER:Lcom/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction$Converter;
        //   264: areturn        
        //   265: aload_2        
        //   266: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   269: ifeq            280
        //   272: aload_2        
        //   273: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   276: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   279: astore_2       
        //   280: aload_2        
        //   281: instanceof      Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   284: ifeq            301
        //   287: aload_2        
        //   288: checkcast       Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   291: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.getClassName:()Ljava/lang/String;
        //   294: goto            302
        //   297: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   300: athrow         
        //   301: aconst_null    
        //   302: astore          4
        //   304: aload_0        
        //   305: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   310: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.supportsObjectLiterals:(Lcom/intellij/psi/PsiFile;)Z
        //   313: istore          5
        //   315: aload_0        
        //   316: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   321: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.supportsSubscripting:(Lcom/intellij/psi/PsiFile;)Z
        //   324: istore          6
        //   326: getstatic       com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.ourConverters:Lcom/intellij/util/containers/MultiMap;
        //   329: aload_3        
        //   330: invokevirtual   com/intellij/util/containers/MultiMap.get:(Ljava/lang/Object;)Ljava/util/Collection;
        //   333: invokeinterface java/util/Collection.iterator:()Ljava/util/Iterator;
        //   338: astore          7
        //   340: aload           7
        //   342: invokeinterface java/util/Iterator.hasNext:()Z
        //   347: ifeq            575
        //   350: aload           7
        //   352: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   357: checkcast       Lcom/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction$Converter;
        //   360: astore          8
        //   362: aload           8
        //   364: invokevirtual   com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction$Converter.getFeature:()Lcom/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction$Feature;
        //   367: getstatic       com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction$Feature.COLLECTION:Lcom/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction$Feature;
        //   370: if_acmpne       392
        //   373: iload           5
        //   375: ifne            392
        //   378: goto            385
        //   381: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   384: athrow         
        //   385: goto            340
        //   388: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   391: athrow         
        //   392: aload           8
        //   394: invokevirtual   com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction$Converter.getFeature:()Lcom/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction$Feature;
        //   397: getstatic       com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction$Feature.SUBSCRIPTING:Lcom/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction$Feature;
        //   400: if_acmpne       422
        //   403: iload           6
        //   405: ifne            422
        //   408: goto            415
        //   411: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   414: athrow         
        //   415: goto            340
        //   418: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   421: athrow         
        //   422: aload           8
        //   424: invokevirtual   com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction$Converter.getReceiverType:()Ljava/lang/String;
        //   427: aload           4
        //   429: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   432: ifeq            572
        //   435: aload           8
        //   437: invokevirtual   com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction$Converter.getFeature:()Lcom/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction$Feature;
        //   440: getstatic       com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction$Feature.CONSTANT:Lcom/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction$Feature;
        //   443: if_acmpeq       569
        //   446: goto            453
        //   449: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   452: athrow         
        //   453: aload_0        
        //   454: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getArgumentExpressions:()Ljava/util/List;
        //   459: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   464: astore          9
        //   466: aload           9
        //   468: invokeinterface java/util/Iterator.hasNext:()Z
        //   473: ifeq            569
        //   476: aload           9
        //   478: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   483: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   486: astore          10
        //   488: aload           10
        //   490: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   495: astore          11
        //   497: aload           11
        //   499: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObject:()Z
        //   502: ifne            566
        //   505: aload           11
        //   507: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnknown:()Z
        //   510: ifne            566
        //   513: goto            520
        //   516: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   519: athrow         
        //   520: aload           10
        //   522: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   525: dup            
        //   526: aload           10
        //   528: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
        //   531: invokestatic    com/jetbrains/cidr/lang/util/OCExpressionEvaluator.isLikeNil:(Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   534: ifne            566
        //   537: goto            544
        //   540: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   543: athrow         
        //   544: aload           11
        //   546: aload_0        
        //   547: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isIntegerCompatible:(Lcom/intellij/psi/PsiElement;)Z
        //   550: ifne            566
        //   553: goto            560
        //   556: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   559: athrow         
        //   560: aconst_null    
        //   561: areturn        
        //   562: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   565: athrow         
        //   566: goto            466
        //   569: aload           8
        //   571: areturn        
        //   572: goto            340
        //   575: aconst_null    
        //   576: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      11     14     18     Ljava/lang/IllegalStateException;
        //  4      20     20     24     Ljava/lang/IllegalStateException;
        //  31     47     47     51     Ljava/lang/IllegalStateException;
        //  94     109    109    113    Ljava/lang/IllegalStateException;
        //  116    137    137    141    Ljava/lang/IllegalStateException;
        //  144    154    157    161    Ljava/lang/IllegalStateException;
        //  149    198    201    205    Ljava/lang/IllegalStateException;
        //  161    207    207    211    Ljava/lang/IllegalStateException;
        //  211    229    232    236    Ljava/lang/IllegalStateException;
        //  219    246    249    253    Ljava/lang/IllegalStateException;
        //  236    257    257    261    Ljava/lang/IllegalStateException;
        //  280    297    297    301    Ljava/lang/IllegalStateException;
        //  362    378    381    385    Ljava/lang/IllegalStateException;
        //  373    388    388    392    Ljava/lang/IllegalStateException;
        //  392    408    411    415    Ljava/lang/IllegalStateException;
        //  403    418    418    422    Ljava/lang/IllegalStateException;
        //  422    446    449    453    Ljava/lang/IllegalStateException;
        //  497    513    516    520    Ljava/lang/IllegalStateException;
        //  505    537    540    544    Ljava/lang/IllegalStateException;
        //  520    553    556    560    Ljava/lang/IllegalStateException;
        //  544    562    562    566    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0161:
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
    private static List<List<OCExpression>> a(final OCSendMessageExpression p0, final Converter p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Ljava/util/ArrayList;
        //     3: dup            
        //     4: invokespecial   java/util/ArrayList.<init>:()V
        //     7: astore_2       
        //     8: aconst_null    
        //     9: astore_3       
        //    10: aload_0        
        //    11: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getArguments:()Ljava/util/List;
        //    16: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    21: astore          4
        //    23: aload           4
        //    25: invokeinterface java/util/Iterator.hasNext:()Z
        //    30: ifeq            351
        //    33: aload           4
        //    35: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    40: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMessageArgument;
        //    43: astore          5
        //    45: aload           5
        //    47: invokeinterface com/jetbrains/cidr/lang/psi/OCMessageArgument.getArgumentSelector:()Lcom/jetbrains/cidr/lang/psi/OCArgumentSelector;
        //    52: invokeinterface com/jetbrains/cidr/lang/psi/OCArgumentSelector.getSelectorName:()Ljava/lang/String;
        //    57: astore          6
        //    59: aload           5
        //    61: invokeinterface com/jetbrains/cidr/lang/psi/OCMessageArgument.getArgumentExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    66: astore          7
        //    68: aload           7
        //    70: ifnull          348
        //    73: aload           6
        //    75: invokevirtual   java/lang/String.isEmpty:()Z
        //    78: ifeq            99
        //    81: goto            88
        //    84: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    87: athrow         
        //    88: aload_3        
        //    89: ifnonnull       133
        //    92: goto            99
        //    95: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    98: athrow         
        //    99: aload_3        
        //   100: ifnull          125
        //   103: goto            110
        //   106: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   109: athrow         
        //   110: aload_2        
        //   111: aload_3        
        //   112: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   117: pop            
        //   118: goto            125
        //   121: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   124: athrow         
        //   125: new             Ljava/util/ArrayList;
        //   128: dup            
        //   129: invokespecial   java/util/ArrayList.<init>:()V
        //   132: astore_3       
        //   133: aload_1        
        //   134: invokevirtual   com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction$Converter.getSpecialFlag:()Lcom/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction$SpecialFlag;
        //   137: getstatic       com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction$SpecialFlag.UNPACK_ARRAYS:Lcom/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction$SpecialFlag;
        //   140: if_acmpne       305
        //   143: aload           7
        //   145: instanceof      Lcom/jetbrains/cidr/lang/psi/OCLiteralExpression;
        //   148: ifeq            207
        //   151: goto            158
        //   154: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   157: athrow         
        //   158: aload           7
        //   160: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getFirstChild:()Lcom/intellij/psi/PsiElement;
        //   165: instanceof      Lcom/jetbrains/cidr/lang/psi/OCNSArrayLiteral;
        //   168: ifeq            207
        //   171: goto            178
        //   174: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   177: athrow         
        //   178: aload_3        
        //   179: aload           7
        //   181: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getFirstChild:()Lcom/intellij/psi/PsiElement;
        //   186: checkcast       Lcom/jetbrains/cidr/lang/psi/OCNSArrayLiteral;
        //   189: invokeinterface com/jetbrains/cidr/lang/psi/OCNSArrayLiteral.getElements:()Ljava/util/List;
        //   194: invokeinterface java/util/List.addAll:(Ljava/util/Collection;)Z
        //   199: pop            
        //   200: goto            348
        //   203: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   206: athrow         
        //   207: aload           7
        //   209: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //   212: ifeq            348
        //   215: aload           7
        //   217: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //   220: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getMessageSelector:()Ljava/lang/String;
        //   225: ldc             "arrayWithObject"
        //   227: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   230: ifeq            348
        //   233: goto            240
        //   236: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   239: athrow         
        //   240: aload           7
        //   242: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //   245: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.findConverter:(Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;)Lcom/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction$Converter;
        //   248: astore          8
        //   250: aload           8
        //   252: ifnull          278
        //   255: aload           7
        //   257: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //   260: aload           8
        //   262: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.a:(Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;Lcom/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction$Converter;)Ljava/util/List;
        //   265: invokestatic    com/intellij/util/containers/ContainerUtil.getFirstItem:(Ljava/util/List;)Ljava/lang/Object;
        //   268: checkcast       Ljava/util/List;
        //   271: goto            279
        //   274: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   277: athrow         
        //   278: aconst_null    
        //   279: astore          9
        //   281: aload           9
        //   283: ifnull          302
        //   286: aload_3        
        //   287: aload           9
        //   289: invokeinterface java/util/List.addAll:(Ljava/util/Collection;)Z
        //   294: pop            
        //   295: goto            302
        //   298: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   301: athrow         
        //   302: goto            348
        //   305: aload_1        
        //   306: invokevirtual   com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction$Converter.isRequireNilTermination:()Z
        //   309: ifeq            339
        //   312: aload           7
        //   314: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getTextWithMacros:()Ljava/lang/String;
        //   319: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.isLikeNull:(Ljava/lang/String;)Z
        //   322: ifeq            339
        //   325: goto            332
        //   328: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   331: athrow         
        //   332: goto            348
        //   335: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   338: athrow         
        //   339: aload_3        
        //   340: aload           7
        //   342: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   347: pop            
        //   348: goto            23
        //   351: aload_3        
        //   352: ifnull          486
        //   355: aload_1        
        //   356: invokevirtual   com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction$Converter.getSpecialFlag:()Lcom/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction$SpecialFlag;
        //   359: getstatic       com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction$SpecialFlag.INTERLEAVE:Lcom/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction$SpecialFlag;
        //   362: if_acmpne       478
        //   365: goto            372
        //   368: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   371: athrow         
        //   372: new             Ljava/util/ArrayList;
        //   375: dup            
        //   376: invokespecial   java/util/ArrayList.<init>:()V
        //   379: astore          4
        //   381: new             Ljava/util/ArrayList;
        //   384: dup            
        //   385: invokespecial   java/util/ArrayList.<init>:()V
        //   388: astore          5
        //   390: iconst_0       
        //   391: istore          6
        //   393: iload           6
        //   395: aload_3        
        //   396: invokeinterface java/util/List.size:()I
        //   401: if_icmpge       457
        //   404: aload_3        
        //   405: iload           6
        //   407: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   412: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   415: astore          7
        //   417: iload           6
        //   419: iconst_2       
        //   420: irem           
        //   421: ifne            441
        //   424: aload           4
        //   426: aload           7
        //   428: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   433: pop            
        //   434: goto            451
        //   437: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   440: athrow         
        //   441: aload           5
        //   443: aload           7
        //   445: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   450: pop            
        //   451: iinc            6, 1
        //   454: goto            393
        //   457: aload_2        
        //   458: aload           4
        //   460: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   465: pop            
        //   466: aload_2        
        //   467: aload           5
        //   469: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   474: pop            
        //   475: goto            486
        //   478: aload_2        
        //   479: aload_3        
        //   480: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   485: pop            
        //   486: iconst_m1      
        //   487: istore          4
        //   489: aload_2        
        //   490: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   495: astore          5
        //   497: aload           5
        //   499: invokeinterface java/util/Iterator.hasNext:()Z
        //   504: ifeq            558
        //   507: aload           5
        //   509: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   514: checkcast       Ljava/util/List;
        //   517: astore          6
        //   519: iload           4
        //   521: iconst_m1      
        //   522: if_icmpne       537
        //   525: aload           6
        //   527: invokeinterface java/util/List.size:()I
        //   532: istore          4
        //   534: goto            555
        //   537: iload           4
        //   539: aload           6
        //   541: invokeinterface java/util/List.size:()I
        //   546: if_icmpeq       555
        //   549: aconst_null    
        //   550: areturn        
        //   551: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   554: athrow         
        //   555: goto            497
        //   558: iload           4
        //   560: ifle            571
        //   563: aload_2        
        //   564: goto            572
        //   567: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   570: athrow         
        //   571: aconst_null    
        //   572: areturn        
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;Lcom/jetbrains/cidr/lang/quickfixes/OCConvertObjCLiteralIntentionAction$Converter;)Ljava/util/List<Ljava/util/List<Lcom/jetbrains/cidr/lang/psi/OCExpression;>;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  68     81     84     88     Ljava/lang/IllegalStateException;
        //  73     92     95     99     Ljava/lang/IllegalStateException;
        //  88     103    106    110    Ljava/lang/IllegalStateException;
        //  99     118    121    125    Ljava/lang/IllegalStateException;
        //  133    151    154    158    Ljava/lang/IllegalStateException;
        //  143    171    174    178    Ljava/lang/IllegalStateException;
        //  158    203    203    207    Ljava/lang/IllegalStateException;
        //  207    233    236    240    Ljava/lang/IllegalStateException;
        //  250    274    274    278    Ljava/lang/IllegalStateException;
        //  281    295    298    302    Ljava/lang/IllegalStateException;
        //  305    325    328    332    Ljava/lang/IllegalStateException;
        //  312    335    335    339    Ljava/lang/IllegalStateException;
        //  351    365    368    372    Ljava/lang/IllegalStateException;
        //  417    437    437    441    Ljava/lang/IllegalStateException;
        //  537    551    551    555    Ljava/lang/IllegalStateException;
        //  558    567    567    571    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0088:
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
        CONVERTERS = new Converter[] { new Converter(Feature.CONSTANT, "NSNumber", "numberWith*", "Switch to @()", "@#P0"), new Converter(Feature.CONSTANT, "NSNumber", "numberWith*", "Switch to @()", "@(#P0)"), new Converter(Feature.COLLECTION, "NSArray", "arrayWithObject:", "Switch to @[]", "@[#P0]"), new Converter(Feature.COLLECTION, "NSArray", "arrayWithObjects:", "Switch to @[]", "@[#P0#*]", ",#P0", true, (SpecialFlag)null), new Converter(Feature.COLLECTION, "NSArray", "initWithObjects:", "Switch to @[]", "@[#P0#*]", ",#P0", true, (SpecialFlag)null), new Converter(Feature.COLLECTION, "NSMutableArray", "arrayWithObject:", "Switch to @[]", "[@[#P0] mutableCopy]"), new Converter(Feature.COLLECTION, "NSMutableArray", "arrayWithObjects:", "Switch to @[]", "[@[#P0#*] mutableCopy]", ",#P0", true, (SpecialFlag)null), new Converter(Feature.COLLECTION, "NSMutableArray", "initWithObjects:", "Switch to @[]", "[@[#P0#*] mutableCopy]", ",#P0", true, (SpecialFlag)null), new Converter(Feature.COLLECTION, "NSDictionary", "dictionaryWithObject:forKey:", "Switch to @{}", "@{#P1:#P0}"), new Converter(Feature.COLLECTION, "NSDictionary", "dictionaryWithObjects:forKeys:", "Switch to @{}", "@{#P1:#P0#*}", ",#P1:#P0", false, SpecialFlag.UNPACK_ARRAYS), new Converter(Feature.COLLECTION, "NSDictionary", "initWithObjects:forKeys:", "Switch to @{}", "@{#P1:#P0#*}", ",#P1:#P0", false, SpecialFlag.UNPACK_ARRAYS), new Converter(Feature.COLLECTION, "NSDictionary", "dictionaryWithObjectsAndKeys:", "Switch to @{}", "@{#P1:#P0#*}", ",#P1:#P0", true, SpecialFlag.INTERLEAVE), new Converter(Feature.COLLECTION, "NSDictionary", "initWithObjectsAndKeys:", "Switch to @{}", "@{#P1:#P0#*}", ",#P1:#P0", true, SpecialFlag.INTERLEAVE), new Converter(Feature.COLLECTION, "NSMutableDictionary", "dictionaryWithObject:forKey:", "Switch to @{}", "[@{#P1:#P0} mutableCopy]"), new Converter(Feature.COLLECTION, "NSMutableDictionary", "dictionaryWithObjects:forKeys:", "Switch to @{}", "[@{#P1:#P0#*} mutableCopy]", ",#P1:#P0", false, SpecialFlag.UNPACK_ARRAYS), new Converter(Feature.COLLECTION, "NSMutableDictionary", "initWithObjects:forKeys:", "Switch to @{}", "[@{#P1:#P0#*} mutableCopy]", ",#P1:#P0", false, SpecialFlag.UNPACK_ARRAYS), new Converter(Feature.COLLECTION, "NSMutableDictionary", "dictionaryWithObjectsAndKeys:", "Switch to @{}", "[@{#P1:#P0#*} mutableCopy]", ",#P1:#P0", true, SpecialFlag.INTERLEAVE), new Converter(Feature.COLLECTION, "NSMutableDictionary", "initWithObjectsAndKeys:", "Switch to @{}", "[@{#P1:#P0#*} mutableCopy]", ",#P1:#P0", true, SpecialFlag.INTERLEAVE), new Converter(Feature.SUBSCRIPTING, "NSArray", "objectAtIndex:", "Switch to []", "#R[#P0]"), new Converter(Feature.SUBSCRIPTING, "NSArray", "objectAtIndexedSubscript:", "Switch to []", "#R[#P0]"), new Converter(Feature.SUBSCRIPTING, "NSMutableArray", "objectAtIndex:", "Switch to []", "#R[#P0]"), new Converter(Feature.SUBSCRIPTING, "NSMutableArray", "objectAtIndexedSubscript:", "Switch to []", "#R[#P0]"), new Converter(Feature.SUBSCRIPTING, "NSMutableArray", "replaceObjectAtIndex:withObject:", "Switch to []", "#R[#P0] = #P1"), new Converter(Feature.SUBSCRIPTING, "NSMutableArray", "setObject:atIndexedSubscript:", "Switch to []", "#R[#P1] = #P0"), new Converter(Feature.SUBSCRIPTING, "NSDictionary", "objectForKey:", "Switch to []", "#R[#P0]"), new Converter(Feature.SUBSCRIPTING, "NSMutableDictionary", "objectForKey:", "Switch to []", "#R[#P0]"), new Converter(Feature.SUBSCRIPTING, "NSMutableDictionary", "setObject:forKey:", "Switch to []", "#R[#P1] = #P0"), new Converter(Feature.SUBSCRIPTING, "NSMutableDictionary", "setObject:forKeyedSubscript:", "Switch to []", "#R[#P1] = #P0") };
        NUMBER_CONST_CONVERTER = OCConvertObjCLiteralIntentionAction.CONVERTERS[0];
        NUMBER_CONVERTER = OCConvertObjCLiteralIntentionAction.CONVERTERS[1];
        ourConverters = new MultiMap();
        for (final Converter converter : OCConvertObjCLiteralIntentionAction.CONVERTERS) {
            OCConvertObjCLiteralIntentionAction.ourConverters.putValue((Object)converter.getMessageName(), (Object)converter);
        }
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
    
    private enum Feature
    {
        CONSTANT, 
        COLLECTION, 
        SUBSCRIPTING;
    }
    
    private enum SpecialFlag
    {
        UNPACK_ARRAYS, 
        INTERLEAVE;
    }
    
    public static class Converter
    {
        private Feature myFeature;
        private String myReceiverType;
        private String myMessageName;
        private String myIntentionName;
        private String myReplacement;
        private String myReplacementLoop;
        private boolean myRequireNilTermination;
        private SpecialFlag mySpecialFlag;
        
        private Converter(final Feature myFeature, final String myReceiverType, final String myMessageName, final String myIntentionName, final String myReplacement) {
            this.myFeature = myFeature;
            this.myReceiverType = myReceiverType;
            this.myMessageName = myMessageName;
            this.myIntentionName = myIntentionName;
            this.myReplacement = myReplacement;
        }
        
        private Converter(final Feature feature, final String s, final String s2, final String s3, final String s4, @Nullable final String myReplacementLoop, final boolean myRequireNilTermination, @Nullable final SpecialFlag mySpecialFlag) {
            this(feature, s, s2, s3, s4);
            this.myReplacementLoop = myReplacementLoop;
            this.myRequireNilTermination = myRequireNilTermination;
            this.mySpecialFlag = mySpecialFlag;
        }
        
        public Feature getFeature() {
            return this.myFeature;
        }
        
        public String getMessageName() {
            return this.myMessageName;
        }
        
        public String getIntentionName() {
            return this.myIntentionName;
        }
        
        public String getReplacement() {
            return this.myReplacement;
        }
        
        public String getReplacementLoop() {
            return this.myReplacementLoop;
        }
        
        public String getReceiverType() {
            return this.myReceiverType;
        }
        
        public boolean isRequireNilTermination() {
            return this.myRequireNilTermination;
        }
        
        public SpecialFlag getSpecialFlag() {
            return this.mySpecialFlag;
        }
    }
}
