// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger;

import com.jetbrains.cidr.lang.psi.OCTypeElement;
import com.jetbrains.cidr.lang.psi.OCProtocolExpression;
import com.jetbrains.cidr.lang.refactoring.OCNameSuggester;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.psi.OCPostfixExpression;
import com.jetbrains.cidr.lang.psi.OCPrefixExpression;
import com.jetbrains.cidr.lang.psi.OCAssignmentExpression;
import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.util.OCExpressionEvaluator;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.jetbrains.cidr.lang.psi.OCParenthesizedExpression;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;
import com.intellij.execution.ExecutionException;
import com.intellij.openapi.diagnostic.Logger;
import java.util.Iterator;
import java.util.List;
import com.intellij.util.containers.ContainerUtil;
import java.util.ArrayList;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.application.WriteAction;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerCommandException;
import com.jetbrains.cidr.execution.debugger.backend.LLValue;
import com.intellij.xdebugger.XExpression;
import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriver;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElementVisitor;
import java.util.Collection;
import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.cidr.lang.psi.OCMacroCall;
import java.util.LinkedHashSet;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.Language;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.psi.OCCodeFragment;
import com.jetbrains.cidr.lang.OCLanguage;
import com.intellij.psi.PsiManager;
import com.jetbrains.cidr.lang.psi.OCCastExpression;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.CidrBundle;
import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.openapi.util.Pair;
import org.jetbrains.annotations.Nullable;
import com.intellij.xdebugger.XSourcePosition;

public class OCEvaluatorHelper extends CidrEvaluatorHelper
{
    @NotNull
    private static Pair<OCType, String> a(final CidrDebugProcess cidrDebugProcess, final String s, @Nullable final XSourcePosition xSourcePosition) throws ConversionException {
        Object resolve = null;
        try {
            if (xSourcePosition == null) {
                throw new ConversionException(CidrBundle.message("debug.evaluate.error.noExecutionPoint", new Object[0]));
            }
        }
        catch (ConversionException ex) {
            throw b(ex);
        }
        final PsiElement contextElement = cidrDebugProcess.getTypesHelper().getContextElement(xSourcePosition);
        try {
            if (contextElement == null) {
                throw new ConversionException(CidrBundle.message("debug.evaluate.error.debugInfoIsOutdated", new Object[0]));
            }
        }
        catch (ConversionException ex2) {
            throw b(ex2);
        }
        OCCodeFragment ocCodeFragment = OCElementFactory.expressionCodeFragment(s, contextElement.getProject(), contextElement, false, false);
        if (PsiTreeUtil.hasErrorElements((PsiElement)ocCodeFragment.getContainingFile())) {
            ocCodeFragment = OCElementFactory.expressionCodeFragmentCpp(s, contextElement.getProject(), contextElement, false, false);
        }
        final OCFile b = b(ocCodeFragment);
        final OCExpression ocExpression = (OCExpression)PsiTreeUtil.findChildOfType((PsiElement)b, (Class)OCExpression.class);
        Label_0231: {
            try {
                if (ocExpression == null || ocExpression instanceof OCCastExpression) {
                    break Label_0231;
                }
            }
            catch (ConversionException ex3) {
                throw b(ex3);
            }
            final PsiFile file = PsiManager.getInstance(cidrDebugProcess.getProject()).findFile(xSourcePosition.getFile());
            if (file != null) {
                final Language language = file.getLanguage();
                try {
                    if (language != OCLanguage.getInstance()) {
                        throw new ConversionException(CidrBundle.message("debug.evaluate.error.cannotEvaluateExpressionForLanguage", language.getDisplayName()));
                    }
                }
                catch (ConversionException ex4) {
                    throw b(ex4);
                }
                resolve = ocExpression.getType().resolve(file);
            }
        }
        final OCFile a = a(b);
        final String text = a.getText();
        try {
            if (text == null) {
                CidrDebuggerLog.LOG.error("Fragment text is 'null'" + a + "\noriginal expression: " + s);
                throw new ConversionException(CidrBundle.message("debug.evaluate.error.invalidExpression", new Object[0]));
            }
        }
        catch (ConversionException ex5) {
            throw b(ex5);
        }
        Pair create;
        try {
            create = Pair.create(resolve, (Object)text);
            if (create == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper", "convertExpressionPrivate"));
            }
        }
        catch (ConversionException ex6) {
            throw b(ex6);
        }
        return (Pair<OCType, String>)create;
    }
    
    @NotNull
    private static OCFile b(@NotNull OCFile expressionCodeFragment) throws ConversionException {
        try {
            if (expressionCodeFragment == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "original", "com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper", "preConvertExpression"));
            }
        }
        catch (ConversionException ex) {
            throw b(ex);
        }
        final LinkedHashSet<String> set = new LinkedHashSet<String>();
        OCMacroCall ocMacroCall;
        while ((ocMacroCall = (OCMacroCall)PsiTreeUtil.findChildOfType((PsiElement)expressionCodeFragment, (Class)OCMacroCall.class)) != null) {
            final String replacementText = ocMacroCall.getReplacementText();
            final TextRange textRange = ocMacroCall.getTextRange();
            final String text = expressionCodeFragment.getText();
            Label_0119: {
                try {
                    if (!set.add(text)) {
                        break Label_0119;
                    }
                    final LinkedHashSet<String> set2 = set;
                    final int n = set2.size();
                    final int n2 = 1000;
                    if (n > n2) {
                        break Label_0119;
                    }
                    break Label_0119;
                }
                catch (ConversionException ex2) {
                    throw b(ex2);
                }
                try {
                    final LinkedHashSet<String> set2 = set;
                    final int n = set2.size();
                    final int n2 = 1000;
                    if (n > n2) {
                        CidrDebuggerLog.LOG.info("Cannot expands macros. Recursion guard (" + set.size() + "):\n" + StringUtil.join((Collection)set, "\n"));
                        throw new ConversionException(CidrBundle.message("debug.evaluate.error.cannotSubstituteMacros", new Object[0]));
                    }
                }
                catch (ConversionException ex3) {
                    throw b(ex3);
                }
            }
            expressionCodeFragment = OCElementFactory.expressionCodeFragment(StringUtil.replaceSubstring(text, textRange, replacementText), expressionCodeFragment.getProject(), expressionCodeFragment.getContext(), false, false);
        }
        OCFile ocFile;
        try {
            expressionCodeFragment.accept((PsiElementVisitor)new PreConverter());
            ocFile = expressionCodeFragment;
            if (ocFile == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper", "preConvertExpression"));
            }
        }
        catch (ConversionException ex4) {
            throw b(ex4);
        }
        return ocFile;
    }
    
    @NotNull
    private static OCFile a(@NotNull final OCFile ocFile) throws ConversionException {
        try {
            if (ocFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "original", "com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper", "postConvertExpression"));
            }
        }
        catch (ConversionException ex) {
            throw b(ex);
        }
        try {
            ocFile.accept((PsiElementVisitor)new PostConverter());
            if (ocFile == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper", "postConvertExpression"));
            }
        }
        catch (ConversionException ex2) {
            throw b(ex2);
        }
        return ocFile;
    }
    
    @Override
    public String convertExpression(@NotNull final CidrDebugProcess cidrDebugProcess, final String s, @Nullable final XSourcePosition xSourcePosition) throws ConversionException {
        try {
            if (cidrDebugProcess == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "process", "com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper", "convertExpression"));
            }
        }
        catch (ConversionException ex) {
            throw b(ex);
        }
        return (String)a(cidrDebugProcess, s, xSourcePosition).second;
    }
    
    @Override
    public Pair<LLValue, String> convertAndEvaluate(@NotNull final CidrDebugProcess cidrDebugProcess, @NotNull final DebuggerDriver debuggerDriver, @NotNull final XExpression xExpression, @Nullable final XSourcePosition xSourcePosition, final long n, final int n2) throws ExecutionException, DebuggerCommandException {
        try {
            if (cidrDebugProcess == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "process", "com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper", "convertAndEvaluate"));
            }
        }
        catch (DebuggerCommandException ex) {
            throw b(ex);
        }
        try {
            if (debuggerDriver == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "driver", "com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper", "convertAndEvaluate"));
            }
        }
        catch (DebuggerCommandException ex2) {
            throw b(ex2);
        }
        try {
            if (xExpression == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expression", "com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper", "convertAndEvaluate"));
            }
        }
        catch (DebuggerCommandException ex3) {
            throw b(ex3);
        }
        final Ref ref = new Ref();
        final Ref ref2 = new Ref();
        final LinkedHashSet<Object> set = new LinkedHashSet<Object>(3);
        final Ref ref3 = new Ref();
        new WriteAction() {
            protected void run(@NotNull final Result p0) throws Throwable {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_1        
                //     1: ifnonnull       44
                //     4: new             Ljava/lang/IllegalArgumentException;
                //     7: dup            
                //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
                //    10: ldc             3
                //    12: anewarray       Ljava/lang/Object;
                //    15: dup            
                //    16: ldc             0
                //    18: ldc             "result"
                //    20: aastore        
                //    21: dup            
                //    22: ldc             1
                //    24: ldc             "com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1"
                //    26: aastore        
                //    27: dup            
                //    28: ldc             2
                //    30: ldc             "run"
                //    32: aastore        
                //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
                //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
                //    39: athrow         
                //    40: invokestatic    com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.b:(Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;)Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;
                //    43: athrow         
                //    44: aload_0        
                //    45: getfield        com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.val$process:Lcom/jetbrains/cidr/execution/debugger/CidrDebugProcess;
                //    48: aload_0        
                //    49: getfield        com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.val$expression:Lcom/intellij/xdebugger/XExpression;
                //    52: invokeinterface com/intellij/xdebugger/XExpression.getExpression:()Ljava/lang/String;
                //    57: aload_0        
                //    58: getfield        com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.val$sourcePosition:Lcom/intellij/xdebugger/XSourcePosition;
                //    61: invokestatic    com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper.access$200:(Lcom/jetbrains/cidr/execution/debugger/CidrDebugProcess;Ljava/lang/String;Lcom/intellij/xdebugger/XSourcePosition;)Lcom/intellij/openapi/util/Pair;
                //    64: astore_2       
                //    65: aload_0        
                //    66: getfield        com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.val$convertedExpression:Lcom/intellij/openapi/util/Ref;
                //    69: aload_2        
                //    70: getfield        com/intellij/openapi/util/Pair.second:Ljava/lang/Object;
                //    73: invokevirtual   com/intellij/openapi/util/Ref.set:(Ljava/lang/Object;)V
                //    76: aload_0        
                //    77: getfield        com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.val$resolvedType:Lcom/intellij/openapi/util/Ref;
                //    80: aload_2        
                //    81: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
                //    84: invokevirtual   com/intellij/openapi/util/Ref.set:(Ljava/lang/Object;)V
                //    87: goto            107
                //    90: astore_2       
                //    91: aload_0        
                //    92: getfield        com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.val$lastException:Lcom/intellij/openapi/util/Ref;
                //    95: new             Lcom/jetbrains/cidr/execution/debugger/backend/DebuggerCommandException;
                //    98: dup            
                //    99: aload_2        
                //   100: invokespecial   com/jetbrains/cidr/execution/debugger/backend/DebuggerCommandException.<init>:(Ljava/lang/Throwable;)V
                //   103: invokevirtual   com/intellij/openapi/util/Ref.set:(Ljava/lang/Object;)V
                //   106: return         
                //   107: aload_0        
                //   108: getfield        com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.val$expressions:Ljava/util/LinkedHashSet;
                //   111: aload_0        
                //   112: getfield        com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.val$convertedExpression:Lcom/intellij/openapi/util/Ref;
                //   115: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
                //   118: invokevirtual   java/util/LinkedHashSet.add:(Ljava/lang/Object;)Z
                //   121: pop            
                //   122: aload_0        
                //   123: getfield        com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.val$resolvedType:Lcom/intellij/openapi/util/Ref;
                //   126: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
                //   129: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
                //   132: astore_2       
                //   133: aload_0        
                //   134: getfield        com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.val$process:Lcom/jetbrains/cidr/execution/debugger/CidrDebugProcess;
                //   137: aload_0        
                //   138: getfield        com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.val$sourcePosition:Lcom/intellij/xdebugger/XSourcePosition;
                //   141: invokevirtual   com/jetbrains/cidr/execution/debugger/CidrDebugProcess.getDebuggerContext:(Lcom/intellij/xdebugger/XSourcePosition;)Lcom/intellij/psi/PsiElement;
                //   144: astore_3       
                //   145: aload_0        
                //   146: getfield        com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.val$resolvedType:Lcom/intellij/openapi/util/Ref;
                //   149: invokevirtual   com/intellij/openapi/util/Ref.isNull:()Z
                //   152: ifne            533
                //   155: aload_2        
                //   156: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnknown:()Z
                //   159: ifne            533
                //   162: goto            169
                //   165: invokestatic    com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.b:(Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;)Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;
                //   168: athrow         
                //   169: aload_3        
                //   170: ifnull          193
                //   173: goto            180
                //   176: invokestatic    com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.b:(Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;)Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;
                //   179: athrow         
                //   180: aload_3        
                //   181: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
                //   186: goto            194
                //   189: invokestatic    com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.b:(Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;)Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;
                //   192: athrow         
                //   193: aconst_null    
                //   194: astore          4
                //   196: aload           4
                //   198: ifnull          433
                //   201: aload_0        
                //   202: getfield        com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.val$process:Lcom/jetbrains/cidr/execution/debugger/CidrDebugProcess;
                //   205: invokevirtual   com/jetbrains/cidr/execution/debugger/CidrDebugProcess.driverSupportsArrayEvaluation:()Z
                //   208: ifeq            244
                //   211: goto            218
                //   214: invokestatic    com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.b:(Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;)Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;
                //   217: athrow         
                //   218: aload_2        
                //   219: instanceof      Lcom/jetbrains/cidr/lang/types/OCArrayType;
                //   222: ifeq            244
                //   225: goto            232
                //   228: invokestatic    com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.b:(Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;)Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;
                //   231: athrow         
                //   232: aload_2        
                //   233: aload           4
                //   235: invokeinterface com/intellij/psi/PsiFile.getProject:()Lcom/intellij/openapi/project/Project;
                //   240: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.decayType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCType;
                //   243: astore_2       
                //   244: aload_2        
                //   245: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
                //   248: ifeq            276
                //   251: aload_2        
                //   252: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
                //   255: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.isRvalueRef:()Z
                //   258: ifeq            276
                //   261: goto            268
                //   264: invokestatic    com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.b:(Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;)Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;
                //   267: athrow         
                //   268: aload_2        
                //   269: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
                //   272: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
                //   275: astore_2       
                //   276: aload_2        
                //   277: instanceof      Lcom/jetbrains/cidr/lang/types/OCArrayType;
                //   280: ifne            375
                //   283: aload_2        
                //   284: astore          5
                //   286: aload           5
                //   288: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
                //   291: ifeq            307
                //   294: aload           5
                //   296: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
                //   299: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
                //   302: astore          5
                //   304: goto            286
                //   307: aload           5
                //   309: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
                //   312: ifeq            375
                //   315: aload_0        
                //   316: getfield        com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.val$expressions:Ljava/util/LinkedHashSet;
                //   319: new             Ljava/lang/StringBuilder;
                //   322: dup            
                //   323: invokespecial   java/lang/StringBuilder.<init>:()V
                //   326: ldc             "(("
                //   328: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   331: aload_2        
                //   332: aload           4
                //   334: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getCanonicalName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
                //   337: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   340: ldc             ")("
                //   342: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   345: aload_0        
                //   346: getfield        com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.val$convertedExpression:Lcom/intellij/openapi/util/Ref;
                //   349: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
                //   352: checkcast       Ljava/lang/String;
                //   355: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   358: ldc             "))"
                //   360: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   363: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                //   366: invokevirtual   java/util/LinkedHashSet.add:(Ljava/lang/Object;)Z
                //   369: pop            
                //   370: return         
                //   371: invokestatic    com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.b:(Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;)Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;
                //   374: athrow         
                //   375: aload_0        
                //   376: getfield        com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.val$expressions:Ljava/util/LinkedHashSet;
                //   379: new             Ljava/lang/StringBuilder;
                //   382: dup            
                //   383: invokespecial   java/lang/StringBuilder.<init>:()V
                //   386: ldc             "(("
                //   388: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   391: aload_2        
                //   392: aload           4
                //   394: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getCanonicalName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
                //   397: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   400: ldc             ")("
                //   402: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   405: aload_0        
                //   406: getfield        com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.val$convertedExpression:Lcom/intellij/openapi/util/Ref;
                //   409: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
                //   412: checkcast       Ljava/lang/String;
                //   415: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   418: ldc             "))"
                //   420: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   423: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                //   426: invokevirtual   java/util/LinkedHashSet.add:(Ljava/lang/Object;)Z
                //   429: pop            
                //   430: goto            480
                //   433: getstatic       com/jetbrains/cidr/execution/debugger/CidrDebuggerLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
                //   436: new             Ljava/lang/StringBuilder;
                //   439: dup            
                //   440: invokespecial   java/lang/StringBuilder.<init>:()V
                //   443: ldc             "Cannot get PsiFile for "
                //   445: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   448: aload_0        
                //   449: getfield        com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.val$sourcePosition:Lcom/intellij/xdebugger/XSourcePosition;
                //   452: ifnull          469
                //   455: aload_0        
                //   456: getfield        com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.val$sourcePosition:Lcom/intellij/xdebugger/XSourcePosition;
                //   459: invokevirtual   java/lang/Object.toString:()Ljava/lang/String;
                //   462: goto            471
                //   465: invokestatic    com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.b:(Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;)Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;
                //   468: athrow         
                //   469: ldc             "UNKNOWN POSITION"
                //   471: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   474: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                //   477: invokevirtual   com/intellij/openapi/diagnostic/Logger.warn:(Ljava/lang/String;)V
                //   480: aload_0        
                //   481: getfield        com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.val$expressions:Ljava/util/LinkedHashSet;
                //   484: new             Ljava/lang/StringBuilder;
                //   487: dup            
                //   488: invokespecial   java/lang/StringBuilder.<init>:()V
                //   491: ldc             "(("
                //   493: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   496: aload_2        
                //   497: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:()Ljava/lang/String;
                //   500: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   503: ldc             ")("
                //   505: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   508: aload_0        
                //   509: getfield        com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$1.val$convertedExpression:Lcom/intellij/openapi/util/Ref;
                //   512: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
                //   515: checkcast       Ljava/lang/String;
                //   518: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   521: ldc             "))"
                //   523: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   526: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                //   529: invokevirtual   java/util/LinkedHashSet.add:(Ljava/lang/Object;)Z
                //   532: pop            
                //   533: return         
                //    Exceptions:
                //  throws java.lang.Throwable
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                                                                           
                //  -----  -----  -----  -----  -------------------------------------------------------------------------------
                //  0      40     40     44     Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;
                //  44     87     90     107    Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;
                //  145    162    165    169    Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;
                //  155    173    176    180    Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;
                //  169    189    189    193    Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;
                //  196    211    214    218    Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;
                //  201    225    228    232    Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;
                //  244    261    264    268    Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;
                //  307    371    371    375    Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;
                //  433    465    465    469    Lcom/jetbrains/cidr/execution/debugger/CidrEvaluatorHelper$ConversionException;
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0169:
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
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1163)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1010)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
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
            
            private static ConversionException b(final ConversionException ex) {
                return ex;
            }
        }.execute();
        if (set.isEmpty()) {
            final DebuggerCommandException ex4 = (DebuggerCommandException)ref3.get();
            try {
                if (ex4 == null) {
                    CidrDebuggerLog.LOG.error("exception is null for: " + xExpression);
                    throw new DebuggerCommandException(CidrBundle.message("debug.evaluate.error.invalidExpression", new Object[0]));
                }
            }
            catch (DebuggerCommandException ex5) {
                throw b(ex5);
            }
            throw ex4;
        }
        Object evaluate = null;
        Object o = null;
        for (final String s : ContainerUtil.iterateBackward((List)new ArrayList(set))) {
            try {
                evaluate = debuggerDriver.evaluate(n, n2, s);
                o = s;
            }
            catch (DebuggerCommandException ex6) {
                ref3.set((Object)ex6);
                continue;
            }
            break;
        }
        Label_0375: {
            Logger log = null;
            Label_0366: {
                try {
                    log = CidrDebuggerLog.LOG;
                    if (evaluate != null) {
                        break Label_0366;
                    }
                    final Ref ref4 = ref3;
                    final boolean b = ref4.isNull();
                    if (!b) {
                        break Label_0366;
                    }
                    break Label_0366;
                }
                catch (DebuggerCommandException ex7) {
                    throw b(ex7);
                }
                try {
                    final Ref ref4 = ref3;
                    final boolean b = ref4.isNull();
                    if (!b) {
                        final boolean b2 = true;
                        break Label_0375;
                    }
                }
                catch (DebuggerCommandException ex8) {
                    throw b(ex8);
                }
            }
            final boolean b2 = false;
            try {
                log.assertTrue(b2);
                if (evaluate == null) {
                    CidrDebuggerLog.LOG.debug((Throwable)ref3.get());
                    throw (DebuggerCommandException)ref3.get();
                }
            }
            catch (DebuggerCommandException ex9) {
                throw b(ex9);
            }
        }
        return (Pair<LLValue, String>)Pair.create(evaluate, o);
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
    
    private abstract static class MyVisitor extends OCRecursiveVisitor
    {
        protected boolean replaceAndVisit(@NotNull final OCExpression ocExpression, @NotNull final String s) {
            try {
                if (ocExpression == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expression", "com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$MyVisitor", "replaceAndVisit"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (s == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "converted", "com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$MyVisitor", "replaceAndVisit"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            super.visitParenthesizedExpression(replace(ocExpression, s));
            return true;
        }
        
        @Nullable
        protected static OCParenthesizedExpression replace(@NotNull final OCExpression ocExpression, @NotNull final String s) {
            try {
                if (ocExpression == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expression", "com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$MyVisitor", "replace"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (s == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "converted", "com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$MyVisitor", "replace"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            final OCExpression expressionFromText = OCElementFactory.expressionFromText("(" + s + ")", (PsiElement)ocExpression, false);
            try {
                if (expressionFromText == null) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            return (OCParenthesizedExpression)ocExpression.replace((PsiElement)expressionFromText);
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    private static class PreConverter extends MyVisitor
    {
        @Override
        public void visitReferenceExpression(final OCReferenceExpression ocReferenceExpression) {
            final OCSymbol resolveToSymbol = ocReferenceExpression.resolveToSymbol();
            final OCSymbolKind ocSymbolKind = (resolveToSymbol == null) ? null : resolveToSymbol.getKind();
            if (ocSymbolKind == OCSymbolKind.INSTANCE_VARIABLE) {
                final String name = resolveToSymbol.getName();
                if (!"self".equals(name) && this.replaceAndVisit(ocReferenceExpression, "self->" + name)) {
                    return;
                }
            }
            else if (ocSymbolKind == OCSymbolKind.ENUM_CONST) {
                final Integer evaluateEnumConst = OCExpressionEvaluator.evaluateEnumConst(resolveToSymbol, ocReferenceExpression.getContainingFile());
                if (MyVisitor.replace(ocReferenceExpression, "((int)" + ((evaluateEnumConst == null) ? ocReferenceExpression.getText() : String.valueOf(evaluateEnumConst)) + ")") != null) {
                    return;
                }
            }
            super.visitReferenceExpression(ocReferenceExpression);
        }
        
        @Override
        public void visitQualifiedExpression(final OCQualifiedExpression ocQualifiedExpression) {
            final Prop a = a(ocQualifiedExpression);
            if (a != null && a.getter != null && this.replaceAndVisit(ocQualifiedExpression, "[" + a.receiver + " " + a.getter + "]")) {
                return;
            }
            super.visitQualifiedExpression(ocQualifiedExpression);
        }
        
        @Override
        public void visitAssignmentExpression(final OCAssignmentExpression ocAssignmentExpression) {
            final Prop a = a(ocAssignmentExpression.getReceiverExpression());
            final OCExpression sourceExpression = ocAssignmentExpression.getSourceExpression();
            if (a != null && a.isReadWrite() && sourceExpression != null) {
                String s = sourceExpression.getText();
                final String name = ocAssignmentExpression.getOperationSign().getName();
                if (name.endsWith("=") && name.length() == 2) {
                    s = "([" + a.receiver + " " + a.getter + "]" + name.substring(0, 1) + s + ")";
                }
                if (this.replaceAndVisit(ocAssignmentExpression, "[" + a.receiver + " " + a.setter + " " + s + "]")) {
                    return;
                }
            }
            super.visitAssignmentExpression(ocAssignmentExpression);
        }
        
        @Override
        public void visitPrefixExpression(final OCPrefixExpression ocPrefixExpression) {
            if (this.a(ocPrefixExpression, true)) {
                return;
            }
            super.visitPrefixExpression(ocPrefixExpression);
        }
        
        @Override
        public void visitPostfixExpression(final OCPostfixExpression ocPostfixExpression) {
            if (this.a(ocPostfixExpression, false)) {
                return;
            }
            super.visitPostfixExpression(ocPostfixExpression);
        }
        
        private boolean a(final OCExpression ocExpression, final boolean b) {
            final Prop a = a(b ? ((OCPrefixExpression)ocExpression).getOperand() : ((OCPostfixExpression)ocExpression).getOperand());
            return a != null && a.isReadWrite() && this.replaceAndVisit(ocExpression, "[" + a.receiver + " " + a.setter + " " + ("[" + a.receiver + " " + a.getter + "]" + ((b ? ((OCPrefixExpression)ocExpression).getOperationSign() : ((OCPostfixExpression)ocExpression).getOperationSign()).getName().equals("--") ? "-1" : "+1")) + "]");
        }
        
        @Nullable
        private static Prop a(@Nullable final OCExpression ocExpression) {
            if (ocExpression instanceof OCQualifiedExpression) {
                final OCSymbol resolveToSymbol = ((OCQualifiedExpression)ocExpression).resolveToSymbol();
                final String text = ((OCQualifiedExpression)ocExpression).getQualifier().getText();
                if (resolveToSymbol instanceof OCPropertySymbol) {
                    return new Prop(text, ((OCPropertySymbol)resolveToSymbol).getGetterName(), ((OCPropertySymbol)resolveToSymbol).getSetterName());
                }
                if (resolveToSymbol instanceof OCMethodSymbol) {
                    final OCMethodSymbol ocMethodSymbol = (OCMethodSymbol)resolveToSymbol;
                    final String name = resolveToSymbol.getName();
                    return new Prop(text, ocMethodSymbol.isGetter() ? name : OCNameSuggester.getObjCGetterFromSetter(name), ocMethodSymbol.isSetter() ? name : OCNameSuggester.getObjCSetterFromGetter(name));
                }
            }
            return null;
        }
        
        private static class Prop
        {
            @NotNull
            public final String receiver;
            @Nullable
            public final String getter;
            @Nullable
            public final String setter;
            
            private Prop(@NotNull final String receiver, @Nullable final String getter, @Nullable final String setter) {
                if (receiver == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "receiver", "com/jetbrains/cidr/execution/debugger/OCEvaluatorHelper$PreConverter$Prop", "<init>"));
                }
                this.receiver = receiver;
                this.getter = getter;
                this.setter = setter;
            }
            
            public boolean isReadWrite() {
                Label_0021: {
                    try {
                        if (this.getter == null) {
                            return false;
                        }
                        final Prop prop = this;
                        final String s = prop.setter;
                        if (s != null) {
                            break Label_0021;
                        }
                        return false;
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        final Prop prop = this;
                        final String s = prop.setter;
                        if (s != null) {
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
    
    private static class PostConverter extends MyVisitor
    {
        @Override
        public void visitReferenceExpression(final OCReferenceExpression ocReferenceExpression) {
            final OCSymbol resolveToSymbol = ocReferenceExpression.resolveToSymbol();
            final OCSymbolKind ocSymbolKind = (resolveToSymbol == null) ? null : resolveToSymbol.getKind();
            if (ocSymbolKind == OCSymbolKind.INTERFACE || ocSymbolKind == OCSymbolKind.IMPLEMENTATION) {
                if (this.a(ocReferenceExpression, resolveToSymbol.getName())) {
                    return;
                }
            }
            else if (ocSymbolKind == OCSymbolKind.PROTOCOL && this.a((OCExpression)ocReferenceExpression, resolveToSymbol.getName())) {
                return;
            }
            super.visitReferenceExpression(ocReferenceExpression);
        }
        
        @Override
        public void visitProtocolExpression(final OCProtocolExpression ocProtocolExpression) {
            final OCTypeElement typeElement = ocProtocolExpression.getTypeElement();
            if (typeElement != null && this.a(ocProtocolExpression, typeElement.getText())) {
                return;
            }
            super.visitProtocolExpression(ocProtocolExpression);
        }
        
        private boolean a(final OCReferenceExpression ocReferenceExpression, final String s) {
            return this.replaceAndVisit(ocReferenceExpression, "(id)NSClassFromString(@\"" + s + "\")");
        }
        
        private boolean a(final OCExpression ocExpression, final String s) {
            return this.replaceAndVisit(ocExpression, "(id)NSProtocolFromString(@\"" + s + "\")");
        }
    }
}
