// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers;

import java.util.Set;
import com.jetbrains.cidr.lang.generate.actions.OCActionContext;
import com.jetbrains.cidr.lang.generate.actions.OCObjCActionContext;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import java.util.Iterator;
import com.jetbrains.cidr.lang.symbols.objc.OCImplementationSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.util.OCCallableUtil;
import com.jetbrains.cidr.lang.psi.OCInterface;
import java.util.Collection;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.refactoring.OCNameSuggester;
import com.intellij.util.Processor;
import java.util.HashSet;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.generate.actions.OCGenerateMethodActionContext;

public class OCGenerateIsEqualAndHashHandler extends OCGenerateMethodHandler
{
    @Override
    protected String getActionTitle() {
        return "Generate -isEqual: and -hash";
    }
    
    @Override
    protected String[] getMethodNames() {
        return new String[] { "isEqual:", "hash" };
    }
    
    protected boolean allowEmptySelection(final OCGenerateMethodActionContext ocGenerateMethodActionContext) {
        return true;
    }
    
    @Override
    protected boolean isExistingMethod(final String s) {
        Label_0024: {
            try {
                if (super.isExistingMethod(s)) {
                    break Label_0024;
                }
                final String s2 = s;
                final String s3 = "isEqualTo";
                final boolean b = s2.startsWith(s3);
                if (b) {
                    break Label_0024;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw d(ex);
            }
            try {
                final String s2 = s;
                final String s3 = "isEqualTo";
                final boolean b = s2.startsWith(s3);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw d(ex2);
            }
        }
        return false;
    }
    
    @NotNull
    @Override
    protected String getInsertText(@NotNull final PsiElement psiElement, @Nullable final PsiElement psiElement2, @NotNull final List<OCInstanceVariableSymbol> list, @NotNull final OCGenerateMethodActionContext ocGenerateMethodActionContext) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateIsEqualAndHashHandler", "getInsertText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ivars", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateIsEqualAndHashHandler", "getInsertText"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw d(ex2);
        }
        try {
            if (ocGenerateMethodActionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateIsEqualAndHashHandler", "getInsertText"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw d(ex3);
        }
        final StringBuilder sb = new StringBuilder();
        final OCMethodSymbol ocMethodSymbol = ocGenerateMethodActionContext.getBaseMethods().get(0);
        final OCMethodSymbol ocMethodSymbol2 = ocGenerateMethodActionContext.getBaseMethods().get(1);
        final OCObjectType superType = ocGenerateMethodActionContext.getType().getSuperType();
        final HashSet<String> set = new HashSet<String>();
        try {
            if (superType != null) {
                superType.processMembers(OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMethodSymbol>)(ocMethodSymbol -> {
                    final String name = ocMethodSymbol.getName();
                    try {
                        if (name.endsWith(":")) {
                            set.add(name.substring(0, name.length() - 1));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw d(ex);
                    }
                    return true;
                }));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw d(ex4);
        }
        final Collection<String> suggestForType = OCNameSuggester.suggestForType(ocGenerateMethodActionContext.getType(), null, "");
        StringBuilder append = null;
        String s = null;
        Label_0279: {
            try {
                append = new StringBuilder().append("isEqualTo");
                if (suggestForType.isEmpty()) {
                    s = "other";
                    break Label_0279;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw d(ex5);
            }
            s = suggestForType.iterator().next();
        }
        final String suggestUniqueName = OCNameSuggester.suggestUniqueName(null, append.append(StringUtil.capitalize(s)).toString(), null, set);
        final Collection<String> suggestForType2 = OCNameSuggester.suggestForType(OCSymbolKind.PARAMETER, ocGenerateMethodActionContext.getType(), psiElement.getLastChild(), "");
        String s2 = null;
        Label_0356: {
            try {
                if (suggestForType2.isEmpty()) {
                    s2 = "other";
                    break Label_0356;
                }
            }
            catch (IllegalArgumentException ex6) {
                throw d(ex6);
            }
            s2 = suggestForType2.iterator().next();
        }
        final String s3 = s2;
        final String string = "-(BOOL)" + suggestUniqueName + ":(" + ocGenerateMethodActionContext.getInterfaceSymbol().getName() + "*)" + s3;
        String string2 = null;
        Label_0521: {
            try {
                if (psiElement instanceof OCInterface) {
                    sb.append(OCCallableUtil.methodSignature(ocMethodSymbol, psiElement)).append(";");
                    sb.append(string).append(";");
                    sb.append(OCCallableUtil.methodSignature(ocMethodSymbol2, psiElement)).append(";");
                    break Label_0521;
                }
            }
            catch (IllegalArgumentException ex7) {
                throw d(ex7);
            }
            final String a = a(list, s3, psiElement, ocGenerateMethodActionContext);
            sb.append(OCCallableUtil.methodText(ocMethodSymbol, suggestUniqueName, psiElement));
            sb.append(OCCallableUtil.methodText(string, a, psiElement));
            sb.append(a(list, psiElement, ocGenerateMethodActionContext));
            try {
                string2 = sb.toString();
                if (string2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateIsEqualAndHashHandler", "getInsertText"));
                }
            }
            catch (IllegalArgumentException ex8) {
                throw d(ex8);
            }
        }
        return string2;
    }
    
    private static String a(final List<OCInstanceVariableSymbol> list, final String s, final PsiElement psiElement, final OCGenerateMethodActionContext ocGenerateMethodActionContext) {
        final StringBuilder sb = new StringBuilder();
        sb.append("if (self == ").append(s).append(")\nreturn YES;\n");
        sb.append("if (").append(s).append(" == nil)\nreturn NO;\n");
        final OCObjectType superType = ocGenerateMethodActionContext.getType().getSuperType();
        OCImplementationSymbol implementation = null;
        Label_0070: {
            try {
                if (superType != null) {
                    implementation = superType.getImplementation();
                    break Label_0070;
                }
            }
            catch (IllegalArgumentException ex) {
                throw d(ex);
            }
            implementation = null;
        }
        final OCImplementationSymbol ocImplementationSymbol = implementation;
        Label_0106: {
            try {
                if (ocImplementationSymbol == null) {
                    break Label_0106;
                }
                final OCImplementationSymbol ocImplementationSymbol2 = ocImplementationSymbol;
                final String s2 = "isEqual:";
                final Class<OCMethodSymbol> clazz = OCMethodSymbol.class;
                final CommonProcessors.FindFirstProcessor findFirstProcessor = new CommonProcessors.FindFirstProcessor();
                final boolean b = true;
                final boolean b2 = ocImplementationSymbol2.processMembersInAllCategories(s2, (Class<? extends OCMemberSymbol>)clazz, (com.intellij.util.Processor<? super OCMemberSymbol>)findFirstProcessor, b);
                if (!b2) {
                    break Label_0106;
                }
                break Label_0106;
            }
            catch (IllegalArgumentException ex2) {
                throw d(ex2);
            }
            try {
                final OCImplementationSymbol ocImplementationSymbol2 = ocImplementationSymbol;
                final String s2 = "isEqual:";
                final Class<OCMethodSymbol> clazz = OCMethodSymbol.class;
                final CommonProcessors.FindFirstProcessor findFirstProcessor = new CommonProcessors.FindFirstProcessor();
                final boolean b = true;
                final boolean b2 = ocImplementationSymbol2.processMembersInAllCategories(s2, (Class<? extends OCMemberSymbol>)clazz, (com.intellij.util.Processor<? super OCMemberSymbol>)findFirstProcessor, b);
                if (!b2) {
                    sb.append("if (![super isEqual: ").append(s).append("])\nreturn NO;\n");
                }
            }
            catch (IllegalArgumentException ex3) {
                throw d(ex3);
            }
        }
        for (final OCInstanceVariableSymbol ocInstanceVariableSymbol : list) {
            final OCPropertySymbol associatedProperty = ocInstanceVariableSymbol.getAssociatedProperty();
            final OCType resolve = ocInstanceVariableSymbol.getType().resolve(psiElement.getContainingFile(), true);
            try {
                if (associatedProperty != null) {
                    a(sb, psiElement, "self." + associatedProperty.getName(), s + "." + associatedProperty.getName(), resolve);
                    continue;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw d(ex4);
            }
            a(sb, psiElement, ocInstanceVariableSymbol.getName(), s + "->" + ocInstanceVariableSymbol.getName(), resolve);
        }
        sb.append("return YES;");
        return sb.toString();
    }
    
    private static void a(final StringBuilder sb, final PsiElement psiElement, final String s, final String s2, final OCType ocType) {
        try {
            if (OCGenerateMethodHandler.processStructFields(ocType, (Processor<OCDeclaratorSymbol>)(ocDeclaratorSymbol -> {
                a(sb, psiElement, s + "." + ocDeclaratorSymbol.getName(), s2 + "." + ocDeclaratorSymbol.getName(), ocDeclaratorSymbol.getResolvedType());
                return true;
            }))) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        sb.append("if (").append(s).append("!=").append(s2);
        if (ocType.isPointerToObject()) {
            final OCObjectType ocObjectType = (OCObjectType)ocType.getTerminalType();
            final CommonProcessors.FindFirstProcessor<OCMethodSymbol> findFirstProcessor = new CommonProcessors.FindFirstProcessor<OCMethodSymbol>() {
                protected boolean accept(final OCMethodSymbol ocMethodSymbol) {
                    final String s = "isEqualTo";
                    if (ocMethodSymbol.getName().startsWith(s) && ocMethodSymbol.getName().length() > s.length() + 2 && ocMethodSymbol.getSelectors().size() == 1) {
                        final OCDeclaratorSymbol parameter = ocMethodSymbol.getSelectors().get(0).getParameter();
                        return parameter != null && parameter.getType().resolve(psiElement.getContainingFile()).isCompatible(ocType, psiElement);
                    }
                    return false;
                }
            };
            ocObjectType.processMembers(null, OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMethodSymbol>)findFirstProcessor);
            final OCMethodSymbol ocMethodSymbol = (OCMethodSymbol)findFirstProcessor.getFoundValue();
            OCDeclaratorSymbol parameter = null;
            Label_0127: {
                try {
                    if (ocMethodSymbol != null) {
                        parameter = ocMethodSymbol.getSelectors().get(0).getParameter();
                        break Label_0127;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw d(ex2);
                }
                parameter = null;
            }
            final OCDeclaratorSymbol ocDeclaratorSymbol = parameter;
            String name = null;
            Label_0176: {
                Label_0172: {
                    try {
                        if (ocDeclaratorSymbol == null || !ocDeclaratorSymbol.getResolvedType().isCompatible(ocType, (PsiElement)psiElement.getContainingFile())) {
                            break Label_0172;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw d(ex3);
                    }
                    name = ocMethodSymbol.getName();
                    break Label_0176;
                }
                name = "isEqual:";
            }
            sb.append("&&![").append(s).append(" ").append(name).append(s2).append("]");
        }
        sb.append(")\nreturn NO;\n");
    }
    
    private static String a(final List<OCInstanceVariableSymbol> list, final PsiElement psiElement, final OCGenerateMethodActionContext ocGenerateMethodActionContext) {
        final ArrayList<String> list2 = new ArrayList<String>();
        final OCObjectType superType = ocGenerateMethodActionContext.getType().getSuperType();
        OCImplementationSymbol implementation = null;
        Label_0035: {
            try {
                if (superType != null) {
                    implementation = superType.getImplementation();
                    break Label_0035;
                }
            }
            catch (IllegalArgumentException ex) {
                throw d(ex);
            }
            implementation = null;
        }
        final OCImplementationSymbol ocImplementationSymbol = implementation;
        Label_0071: {
            try {
                if (ocImplementationSymbol == null) {
                    break Label_0071;
                }
                final OCImplementationSymbol ocImplementationSymbol2 = ocImplementationSymbol;
                final String s = "hash";
                final Class<OCMethodSymbol> clazz = OCMethodSymbol.class;
                final CommonProcessors.FindFirstProcessor findFirstProcessor = new CommonProcessors.FindFirstProcessor();
                final boolean b = true;
                final boolean b2 = ocImplementationSymbol2.processMembersInAllCategories(s, (Class<? extends OCMemberSymbol>)clazz, (com.intellij.util.Processor<? super OCMemberSymbol>)findFirstProcessor, b);
                if (!b2) {
                    break Label_0071;
                }
                break Label_0071;
            }
            catch (IllegalArgumentException ex2) {
                throw d(ex2);
            }
            try {
                final OCImplementationSymbol ocImplementationSymbol2 = ocImplementationSymbol;
                final String s = "hash";
                final Class<OCMethodSymbol> clazz = OCMethodSymbol.class;
                final CommonProcessors.FindFirstProcessor findFirstProcessor = new CommonProcessors.FindFirstProcessor();
                final boolean b = true;
                final boolean b2 = ocImplementationSymbol2.processMembersInAllCategories(s, (Class<? extends OCMemberSymbol>)clazz, (com.intellij.util.Processor<? super OCMemberSymbol>)findFirstProcessor, b);
                if (!b2) {
                    list2.add("[super hash];\n");
                }
            }
            catch (IllegalArgumentException ex3) {
                throw d(ex3);
            }
        }
        for (final OCInstanceVariableSymbol ocInstanceVariableSymbol : list) {
            final OCPropertySymbol associatedProperty = ocInstanceVariableSymbol.getAssociatedProperty();
            final OCType resolve = ocInstanceVariableSymbol.getType().resolve(psiElement.getContainingFile(), true);
            ArrayList<String> list3 = null;
            String s2 = null;
            Label_0190: {
                try {
                    list3 = list2;
                    if (associatedProperty != null) {
                        s2 = "self." + associatedProperty.getName();
                        break Label_0190;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw d(ex4);
                }
                s2 = ocInstanceVariableSymbol.getName();
            }
            a(list3, s2, resolve, psiElement);
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(OCCallableUtil.methodSignature(ocGenerateMethodActionContext.getBaseMethods().get(1), psiElement)).append("{\n");
        if (list2.size() > 1) {
            int n = 1;
            for (final String s3 : list2) {
                Label_0307: {
                    try {
                        if (n != 0) {
                            sb.append("NSUInteger hash = ");
                            break Label_0307;
                        }
                    }
                    catch (IllegalArgumentException ex5) {
                        throw d(ex5);
                    }
                    sb.append("hash = hash * 31u + ");
                }
                sb.append(s3);
                n = 0;
            }
            sb.append("return hash;\n}\n");
        }
        else {
            try {
                if (list2.size() == 1) {
                    sb.append("return ").append(list2.get(0)).append("}\n");
                    return sb.toString();
                }
            }
            catch (IllegalArgumentException ex6) {
                throw d(ex6);
            }
            sb.append("return [super hash];}\n");
        }
        return sb.toString();
    }
    
    private static void a(final List<String> p0, final String p1, final OCType p2, final PsiElement p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObject:()Z
        //     4: ifeq            45
        //     7: aload_0        
        //     8: new             Ljava/lang/StringBuilder;
        //    11: dup            
        //    12: invokespecial   java/lang/StringBuilder.<init>:()V
        //    15: ldc             "["
        //    17: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    20: aload_1        
        //    21: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    24: ldc             " hash];\n"
        //    26: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    29: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    32: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //    37: pop            
        //    38: goto            371
        //    41: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateIsEqualAndHashHandler.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    44: athrow         
        //    45: aload_2        
        //    46: getstatic       com/jetbrains/cidr/lang/types/OCRealType.FLOAT:Lcom/jetbrains/cidr/lang/types/OCRealType;
        //    49: if_acmpne       90
        //    52: aload_0        
        //    53: new             Ljava/lang/StringBuilder;
        //    56: dup            
        //    57: invokespecial   java/lang/StringBuilder.<init>:()V
        //    60: ldc             "[[NSNumber numberWithFloat:"
        //    62: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    65: aload_1        
        //    66: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    69: ldc             "] hash];\n"
        //    71: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    74: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    77: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //    82: pop            
        //    83: goto            371
        //    86: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateIsEqualAndHashHandler.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    89: athrow         
        //    90: aload_2        
        //    91: instanceof      Lcom/jetbrains/cidr/lang/types/OCRealType;
        //    94: ifeq            135
        //    97: aload_0        
        //    98: new             Ljava/lang/StringBuilder;
        //   101: dup            
        //   102: invokespecial   java/lang/StringBuilder.<init>:()V
        //   105: ldc             "[[NSNumber numberWithDouble:"
        //   107: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   110: aload_1        
        //   111: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   114: ldc             "] hash];\n"
        //   116: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   119: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   122: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   127: pop            
        //   128: goto            371
        //   131: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateIsEqualAndHashHandler.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   134: athrow         
        //   135: aload_2        
        //   136: instanceof      Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   139: ifeq            259
        //   142: aload_0        
        //   143: invokeinterface java/util/List.isEmpty:()Z
        //   148: ifeq            208
        //   151: goto            158
        //   154: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateIsEqualAndHashHandler.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   157: athrow         
        //   158: aload_2        
        //   159: checkcast       Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   162: invokevirtual   com/jetbrains/cidr/lang/types/OCIntType.isSigned:()Z
        //   165: ifne            200
        //   168: goto            175
        //   171: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateIsEqualAndHashHandler.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   174: athrow         
        //   175: aload_2        
        //   176: checkcast       Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   179: aload_3        
        //   180: invokevirtual   com/jetbrains/cidr/lang/types/OCIntType.getRank:(Lcom/intellij/psi/PsiElement;)I
        //   183: getstatic       com/jetbrains/cidr/lang/types/OCIntType.UINT:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   186: aload_3        
        //   187: invokevirtual   com/jetbrains/cidr/lang/types/OCIntType.getRank:(Lcom/intellij/psi/PsiElement;)I
        //   190: if_icmple       208
        //   193: goto            200
        //   196: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateIsEqualAndHashHandler.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   199: athrow         
        //   200: iconst_1       
        //   201: goto            209
        //   204: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateIsEqualAndHashHandler.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   207: athrow         
        //   208: iconst_0       
        //   209: istore          4
        //   211: aload_0        
        //   212: new             Ljava/lang/StringBuilder;
        //   215: dup            
        //   216: invokespecial   java/lang/StringBuilder.<init>:()V
        //   219: iload           4
        //   221: ifeq            233
        //   224: ldc             "(NSUInteger)"
        //   226: goto            235
        //   229: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateIsEqualAndHashHandler.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   232: athrow         
        //   233: ldc             ""
        //   235: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   238: aload_1        
        //   239: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   242: ldc             ";\n"
        //   244: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   247: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   250: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   255: pop            
        //   256: goto            371
        //   259: aload_2        
        //   260: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   263: ifeq            340
        //   266: aload_2        
        //   267: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isScalar:()Z
        //   270: ifne            340
        //   273: goto            280
        //   276: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateIsEqualAndHashHandler.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   279: athrow         
        //   280: aload_2        
        //   281: aload_0        
        //   282: aload_1        
        //   283: aload_3        
        //   284: invokedynamic   process:(Ljava/util/List;Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/intellij/util/Processor;
        //   289: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateIsEqualAndHashHandler.processStructFields:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/util/Processor;)Z
        //   292: ifne            371
        //   295: goto            302
        //   298: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateIsEqualAndHashHandler.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   301: athrow         
        //   302: aload_0        
        //   303: new             Ljava/lang/StringBuilder;
        //   306: dup            
        //   307: invokespecial   java/lang/StringBuilder.<init>:()V
        //   310: ldc             "(NSUInteger)"
        //   312: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   315: aload_1        
        //   316: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   319: ldc             ";\n"
        //   321: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   324: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   327: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   332: pop            
        //   333: goto            371
        //   336: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateIsEqualAndHashHandler.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   339: athrow         
        //   340: aload_0        
        //   341: new             Ljava/lang/StringBuilder;
        //   344: dup            
        //   345: invokespecial   java/lang/StringBuilder.<init>:()V
        //   348: ldc             "(NSUInteger)"
        //   350: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   353: aload_1        
        //   354: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   357: ldc             ";\n"
        //   359: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   362: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   365: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   370: pop            
        //   371: return         
        //    Signature:
        //  (Ljava/util/List<Ljava/lang/String;>;Ljava/lang/String;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      41     41     45     Ljava/lang/IllegalArgumentException;
        //  45     86     86     90     Ljava/lang/IllegalArgumentException;
        //  90     131    131    135    Ljava/lang/IllegalArgumentException;
        //  135    151    154    158    Ljava/lang/IllegalArgumentException;
        //  142    168    171    175    Ljava/lang/IllegalArgumentException;
        //  158    193    196    200    Ljava/lang/IllegalArgumentException;
        //  175    204    204    208    Ljava/lang/IllegalArgumentException;
        //  211    229    229    233    Ljava/lang/IllegalArgumentException;
        //  259    273    276    280    Ljava/lang/IllegalArgumentException;
        //  266    295    298    302    Ljava/lang/IllegalArgumentException;
        //  280    336    336    340    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0158:
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
    
    private static IllegalArgumentException d(final IllegalArgumentException ex) {
        return ex;
    }
}
