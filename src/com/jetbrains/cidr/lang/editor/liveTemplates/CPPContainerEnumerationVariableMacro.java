// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.liveTemplates;

import com.jetbrains.cidr.lang.symbols.OCSymbolBase;
import com.jetbrains.cidr.lang.editor.completion.OCCodeContextType;
import com.intellij.codeInsight.template.TemplateContextType;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.codeInsight.lookup.LookupElement;
import org.jetbrains.annotations.Nullable;
import java.util.Iterator;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.intellij.codeInsight.template.Result;
import com.intellij.codeInsight.template.ExpressionContext;
import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.template.Expression;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import java.util.List;
import com.intellij.openapi.util.Key;
import com.intellij.codeInsight.template.Macro;

public class CPPContainerEnumerationVariableMacro extends Macro
{
    public static final String MACRO_NAME = "variableForIteration";
    private static final Key<List<OCDeclaratorSymbol>> MACRO_LAST_RESULT_KEY;
    
    public String getName() {
        return "variableForIteration";
    }
    
    public String getPresentableName() {
        return "variableForIteration()";
    }
    
    @Nullable
    public Result calculateResult(@NotNull final Expression[] array, @NotNull final ExpressionContext expressionContext) {
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "params", "com/jetbrains/cidr/lang/editor/liveTemplates/CPPContainerEnumerationVariableMacro", "calculateResult"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (expressionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/editor/liveTemplates/CPPContainerEnumerationVariableMacro", "calculateResult"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final List<OCDeclaratorSymbol> a = a(expressionContext);
        try {
            if (a.isEmpty()) {
                return null;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final Iterator<OCDeclaratorSymbol> iterator = a.iterator();
        while (iterator.hasNext()) {
            final OCElement ocElement = ((OCSymbolBase<OCElement>)iterator.next()).locateDefinition();
            try {
                if (ocElement != null) {
                    return (Result)new OCElementResult(ocElement);
                }
                continue;
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return null;
    }
    
    @Nullable
    public LookupElement[] calculateLookupItems(@NotNull final Expression[] array, @NotNull final ExpressionContext expressionContext) {
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "params", "com/jetbrains/cidr/lang/editor/liveTemplates/CPPContainerEnumerationVariableMacro", "calculateLookupItems"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (expressionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/editor/liveTemplates/CPPContainerEnumerationVariableMacro", "calculateLookupItems"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final List<OCDeclaratorSymbol> a = a(expressionContext);
        try {
            if (a.size() < 2) {
                return null;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final LookupElement[] array2 = new LookupElement[a.size()];
        int n = 0;
        for (final OCDeclaratorSymbol ocDeclaratorSymbol : a) {
            array2[n++] = (LookupElement)LookupElementBuilder.create(ocDeclaratorSymbol.getName()).withIcon(ocDeclaratorSymbol.getIcon());
        }
        return array2;
    }
    
    @NotNull
    private static List<OCDeclaratorSymbol> a(@NotNull final ExpressionContext p0) {
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
        //    18: ldc             "context"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/editor/liveTemplates/CPPContainerEnumerationVariableMacro"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getVariables"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/editor/liveTemplates/CPPContainerEnumerationVariableMacro.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: invokestatic    com/jetbrains/cidr/lang/editor/liveTemplates/OCTemplatesUtil.getInsertionPlace:(Lcom/intellij/codeInsight/template/ExpressionContext;)Lcom/intellij/psi/PsiElement;
        //    48: astore_1       
        //    49: getstatic       com/jetbrains/cidr/lang/editor/liveTemplates/CPPContainerEnumerationVariableMacro.MACRO_LAST_RESULT_KEY:Lcom/intellij/openapi/util/Key;
        //    52: aload_1        
        //    53: invokevirtual   com/intellij/openapi/util/Key.get:(Lcom/intellij/openapi/util/UserDataHolder;)Ljava/lang/Object;
        //    56: checkcast       Ljava/util/List;
        //    59: astore_2       
        //    60: aload_2        
        //    61: ifnull          111
        //    64: aload_2        
        //    65: dup            
        //    66: ifnonnull       110
        //    69: goto            76
        //    72: invokestatic    com/jetbrains/cidr/lang/editor/liveTemplates/CPPContainerEnumerationVariableMacro.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    75: athrow         
        //    76: new             Ljava/lang/IllegalStateException;
        //    79: dup            
        //    80: ldc             "@NotNull method %s.%s must not return null"
        //    82: ldc             2
        //    84: anewarray       Ljava/lang/Object;
        //    87: dup            
        //    88: ldc             0
        //    90: ldc             "com/jetbrains/cidr/lang/editor/liveTemplates/CPPContainerEnumerationVariableMacro"
        //    92: aastore        
        //    93: dup            
        //    94: ldc             1
        //    96: ldc             "getVariables"
        //    98: aastore        
        //    99: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   102: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   105: athrow         
        //   106: invokestatic    com/jetbrains/cidr/lang/editor/liveTemplates/CPPContainerEnumerationVariableMacro.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   109: athrow         
        //   110: areturn        
        //   111: aload_1        
        //   112: iconst_1       
        //   113: invokestatic    com/jetbrains/cidr/lang/editor/liveTemplates/OCTemplatesUtil.getVariablesVisibleAt:(Lcom/intellij/psi/PsiElement;Z)Ljava/util/List;
        //   116: astore_3       
        //   117: new             Ljava/util/ArrayList;
        //   120: dup            
        //   121: invokespecial   java/util/ArrayList.<init>:()V
        //   124: astore_2       
        //   125: aload_3        
        //   126: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   131: astore          4
        //   133: aload           4
        //   135: invokeinterface java/util/Iterator.hasNext:()Z
        //   140: ifeq            230
        //   143: aload           4
        //   145: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   150: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   153: astore          5
        //   155: aload           5
        //   157: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   160: astore          6
        //   162: aload           6
        //   164: aload_1        
        //   165: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnresolved:(Lcom/intellij/psi/PsiElement;)Z
        //   168: ifne            227
        //   171: aload           6
        //   173: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnknown:()Z
        //   176: ifne            227
        //   179: goto            186
        //   182: invokestatic    com/jetbrains/cidr/lang/editor/liveTemplates/CPPContainerEnumerationVariableMacro.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   185: athrow         
        //   186: aload           5
        //   188: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getName:()Ljava/lang/String;
        //   191: aload_1        
        //   192: iconst_0       
        //   193: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.expressionFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;Z)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   196: aload           6
        //   198: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.getCollectionElementType:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   201: ifnull          227
        //   204: goto            211
        //   207: invokestatic    com/jetbrains/cidr/lang/editor/liveTemplates/CPPContainerEnumerationVariableMacro.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   210: athrow         
        //   211: aload_2        
        //   212: aload           5
        //   214: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   219: pop            
        //   220: goto            227
        //   223: invokestatic    com/jetbrains/cidr/lang/editor/liveTemplates/CPPContainerEnumerationVariableMacro.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   226: athrow         
        //   227: goto            133
        //   230: getstatic       com/jetbrains/cidr/lang/editor/liveTemplates/CPPContainerEnumerationVariableMacro.MACRO_LAST_RESULT_KEY:Lcom/intellij/openapi/util/Key;
        //   233: aload_1        
        //   234: aload_2        
        //   235: invokevirtual   com/intellij/openapi/util/Key.set:(Lcom/intellij/openapi/util/UserDataHolder;Ljava/lang/Object;)V
        //   238: aload_2        
        //   239: dup            
        //   240: ifnonnull       277
        //   243: new             Ljava/lang/IllegalStateException;
        //   246: dup            
        //   247: ldc             "@NotNull method %s.%s must not return null"
        //   249: ldc             2
        //   251: anewarray       Ljava/lang/Object;
        //   254: dup            
        //   255: ldc             0
        //   257: ldc             "com/jetbrains/cidr/lang/editor/liveTemplates/CPPContainerEnumerationVariableMacro"
        //   259: aastore        
        //   260: dup            
        //   261: ldc             1
        //   263: ldc             "getVariables"
        //   265: aastore        
        //   266: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   269: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   272: athrow         
        //   273: invokestatic    com/jetbrains/cidr/lang/editor/liveTemplates/CPPContainerEnumerationVariableMacro.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   276: athrow         
        //   277: areturn        
        //    Signature:
        //  (Lcom/intellij/codeInsight/template/ExpressionContext;)Ljava/util/List<Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  60     69     72     76     Ljava/lang/IllegalArgumentException;
        //  64     106    106    110    Ljava/lang/IllegalArgumentException;
        //  162    179    182    186    Ljava/lang/IllegalArgumentException;
        //  171    204    207    211    Ljava/lang/IllegalArgumentException;
        //  186    220    223    227    Ljava/lang/IllegalArgumentException;
        //  230    273    273    277    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0186:
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
    
    public boolean isAcceptableInContext(final TemplateContextType templateContextType) {
        return templateContextType instanceof OCCodeContextType;
    }
    
    static {
        MACRO_LAST_RESULT_KEY = Key.create("variableForIteration");
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
