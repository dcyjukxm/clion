// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers;

import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.generate.actions.OCActionContext;
import com.jetbrains.cidr.lang.generate.actions.OCObjCActionContext;
import com.intellij.openapi.editor.Editor;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.quickfixes.OCReleaseVariablesIntentionAction;
import com.jetbrains.cidr.lang.psi.OCImplementation;
import com.intellij.openapi.project.Project;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import java.util.Iterator;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.intellij.util.Processor;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.symbols.objc.OCProtocolSymbol;
import com.jetbrains.cidr.lang.util.OCCallableUtil;
import com.jetbrains.cidr.lang.psi.OCInterface;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import java.util.List;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.generate.actions.OCGenerateMethodActionContext;
import org.jetbrains.annotations.Nullable;

public class OCGenerateEncodeHandler extends OCGenerateMethodHandler
{
    @Override
    protected String getActionTitle() {
        return "Generate -encodeWithCoder:/-initWithCoder:";
    }
    
    @Override
    protected String[] getMethodNames() {
        return new String[] { "initWithCoder:", "encodeWithCoder:" };
    }
    
    @Nullable
    @Override
    protected String getParentProtocol() {
        return "NSCoding";
    }
    
    protected String getNoMembersMessage(@NotNull final OCGenerateMethodActionContext ocGenerateMethodActionContext) {
        try {
            if (ocGenerateMethodActionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateEncodeHandler", "getNoMembersMessage"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return ocGenerateMethodActionContext.getParentNameUppercase() + " has no encodable members";
    }
    
    @NotNull
    @Override
    protected String getInsertText(@NotNull final PsiElement psiElement, @Nullable final PsiElement psiElement2, @NotNull final List<OCInstanceVariableSymbol> list, @NotNull final OCGenerateMethodActionContext ocGenerateMethodActionContext) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateEncodeHandler", "getInsertText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "members", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateEncodeHandler", "getInsertText"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw d(ex2);
        }
        try {
            if (ocGenerateMethodActionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "actionContext", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateEncodeHandler", "getInsertText"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw d(ex3);
        }
        final OCMethodSymbol ocMethodSymbol = ocGenerateMethodActionContext.getBaseMethods().get(0);
        final OCMethodSymbol ocMethodSymbol2 = ocGenerateMethodActionContext.getBaseMethods().get(1);
        final StringBuilder sb = new StringBuilder();
        String string = null;
        Label_0672: {
            try {
                if (psiElement instanceof OCInterface) {
                    sb.append(OCCallableUtil.methodSignature(ocMethodSymbol, psiElement)).append(";");
                    sb.append(OCCallableUtil.methodSignature(ocMethodSymbol2, psiElement)).append(";");
                    break Label_0672;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw d(ex4);
            }
            final StringBuilder sb2 = new StringBuilder();
            final StringBuilder sb3 = new StringBuilder();
            final OCObjectType superType = ocGenerateMethodActionContext.getType().getSuperType();
            final OCProtocolSymbol ocProtocolSymbol = ((OCSymbolWithParent<T, OCProtocolSymbol>)ocMethodSymbol).getParent();
            final OCType resolve = ocMethodSymbol.getSelectors().get(0).getParameter().getType().resolve(psiElement.getContainingFile(), true);
            OCMethodSymbol ocMethodSymbol3 = null;
            Label_0396: {
                Label_0335: {
                    try {
                        if (superType == null || !superType.implementsProtocol(ocProtocolSymbol)) {
                            break Label_0335;
                        }
                    }
                    catch (IllegalArgumentException ex5) {
                        throw d(ex5);
                    }
                    sb3.append("[super encodeWithCoder: coder];\n");
                    ocMethodSymbol3 = ocMethodSymbol;
                    break Label_0396;
                }
                final CommonProcessors.FindFirstProcessor findFirstProcessor = new CommonProcessors.FindFirstProcessor();
                try {
                    if (superType != null) {
                        superType.processMembers("init", OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMethodSymbol>)findFirstProcessor);
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw d(ex6);
                }
                final OCMethodSymbol ocMethodSymbol4 = (OCMethodSymbol)findFirstProcessor.getFoundValue();
                OCMethodSymbol ocMethodSymbol5 = null;
                Label_0394: {
                    try {
                        if (ocMethodSymbol4 != null) {
                            ocMethodSymbol5 = ocMethodSymbol4;
                            break Label_0394;
                        }
                    }
                    catch (IllegalArgumentException ex7) {
                        throw d(ex7);
                    }
                    ocMethodSymbol5 = ocMethodSymbol;
                }
                ocMethodSymbol3 = ocMethodSymbol5;
            }
            for (final OCInstanceVariableSymbol ocInstanceVariableSymbol : list) {
                final OCPropertySymbol associatedProperty = ocInstanceVariableSymbol.getAssociatedProperty();
                final OCType resolve2 = ocInstanceVariableSymbol.getType().resolve(psiElement.getContainingFile(), true);
                boolean b2 = false;
                Label_0485: {
                    Label_0476: {
                        try {
                            if (associatedProperty == null) {
                                break Label_0476;
                            }
                            final OCPropertySymbol ocPropertySymbol = associatedProperty;
                            final boolean b = ocPropertySymbol.isReadonly();
                            if (!b) {
                                break Label_0476;
                            }
                            break Label_0476;
                        }
                        catch (IllegalArgumentException ex8) {
                            throw d(ex8);
                        }
                        try {
                            final OCPropertySymbol ocPropertySymbol = associatedProperty;
                            final boolean b = ocPropertySymbol.isReadonly();
                            if (!b) {
                                b2 = true;
                                break Label_0485;
                            }
                        }
                        catch (IllegalArgumentException ex9) {
                            throw d(ex9);
                        }
                    }
                    b2 = false;
                }
                final boolean b3 = b2;
                String s = null;
                Label_0531: {
                    try {
                        if (b3) {
                            s = "self." + associatedProperty.getName();
                            break Label_0531;
                        }
                    }
                    catch (IllegalArgumentException ex10) {
                        throw d(ex10);
                    }
                    s = ocInstanceVariableSymbol.getName();
                }
                final String s2 = s;
                String s3 = null;
                Label_0577: {
                    try {
                        if (associatedProperty != null) {
                            s3 = "self." + associatedProperty.getName();
                            break Label_0577;
                        }
                    }
                    catch (IllegalArgumentException ex11) {
                        throw d(ex11);
                    }
                    s3 = ocInstanceVariableSymbol.getName();
                }
                final String s4 = s3;
                StringBuilder sb4 = null;
                StringBuilder sb5 = null;
                OCType ocType = null;
                String s5 = null;
                String s6 = null;
                OCProtocolSymbol ocProtocolSymbol2 = null;
                OCType ocType2 = null;
                OCInstanceVariableSymbol ocInstanceVariableSymbol2 = null;
                Label_0608: {
                    try {
                        sb4 = sb2;
                        sb5 = sb3;
                        ocType = resolve2;
                        s5 = s2;
                        s6 = s4;
                        ocProtocolSymbol2 = ocProtocolSymbol;
                        ocType2 = resolve;
                        if (b3) {
                            ocInstanceVariableSymbol2 = null;
                            break Label_0608;
                        }
                    }
                    catch (IllegalArgumentException ex12) {
                        throw d(ex12);
                    }
                    ocInstanceVariableSymbol2 = ocInstanceVariableSymbol;
                }
                a(sb4, sb5, ocType, s5, s6, ocProtocolSymbol2, ocType2, ocInstanceVariableSymbol2, ocGenerateMethodActionContext.getNonReleasedIvars(), psiElement);
            }
            sb.append(OCCallableUtil.methodText(ocMethodSymbol3, null, OCCallableUtil.methodSignature(ocMethodSymbol, psiElement), sb2.toString().trim(), psiElement));
            sb.append(OCCallableUtil.methodText(OCCallableUtil.methodSignature(ocMethodSymbol2, psiElement), sb3.toString().trim(), psiElement));
            try {
                string = sb.toString();
                if (string == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateEncodeHandler", "getInsertText"));
                }
            }
            catch (IllegalArgumentException ex13) {
                throw d(ex13);
            }
        }
        return string;
    }
    
    @Override
    protected boolean showSymbolInChooser(final OCInstanceVariableSymbol ocInstanceVariableSymbol, final OCGenerateMethodActionContext ocGenerateMethodActionContext) {
        try {
            if (!super.showSymbolInChooser(ocInstanceVariableSymbol, ocGenerateMethodActionContext)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        return a(new StringBuilder(), new StringBuilder(), ocInstanceVariableSymbol.getType().resolve(ocGenerateMethodActionContext.getContext().getContainingFile(), true), "", "", ((OCSymbolWithParent<T, OCProtocolSymbol>)ocGenerateMethodActionContext.getBaseMethods().get(0)).getParent(), null, null, new ArrayList<OCInstanceVariableSymbol>(), ocGenerateMethodActionContext.getContext());
    }
    
    @Override
    protected void performAction(@NotNull final Project project, @NotNull final PsiElement psiElement, final int n, final PsiElement psiElement2, @NotNull final List<OCInstanceVariableSymbol> list, @NotNull final OCGenerateMethodActionContext ocGenerateMethodActionContext) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateEncodeHandler", "performAction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateEncodeHandler", "performAction"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw d(ex2);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "members", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateEncodeHandler", "performAction"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw d(ex3);
        }
        try {
            if (ocGenerateMethodActionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateEncodeHandler", "performAction"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw d(ex4);
        }
        Label_0218: {
            try {
                super.performAction(project, psiElement, n, psiElement2, list, ocGenerateMethodActionContext);
                if (!(psiElement instanceof OCImplementation)) {
                    return;
                }
                final OCGenerateMethodActionContext ocGenerateMethodActionContext2 = ocGenerateMethodActionContext;
                final List<OCInstanceVariableSymbol> list2 = ocGenerateMethodActionContext2.getNonReleasedIvars();
                final boolean b = list2.isEmpty();
                if (!b) {
                    break Label_0218;
                }
                return;
            }
            catch (IllegalArgumentException ex5) {
                throw d(ex5);
            }
            try {
                final OCGenerateMethodActionContext ocGenerateMethodActionContext2 = ocGenerateMethodActionContext;
                final List<OCInstanceVariableSymbol> list2 = ocGenerateMethodActionContext2.getNonReleasedIvars();
                final boolean b = list2.isEmpty();
                if (!b) {
                    new OCReleaseVariablesIntentionAction(ocGenerateMethodActionContext.getNonReleasedIvars()).invoke(project, null, psiElement.getContainingFile());
                }
            }
            catch (IllegalArgumentException ex6) {
                throw d(ex6);
            }
        }
    }
    
    private static boolean a(final StringBuilder p0, final StringBuilder p1, final OCType p2, final String p3, final String p4, final OCProtocolSymbol p5, @Nullable final OCType p6, @Nullable final OCInstanceVariableSymbol p7, final List<OCInstanceVariableSymbol> p8, final PsiElement p9) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //     4: ifeq            345
        //     7: aload_2        
        //     8: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isScalar:()Z
        //    11: ifne            345
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateEncodeHandler.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    20: athrow         
        //    21: aload           6
        //    23: ifnull          345
        //    26: goto            33
        //    29: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateEncodeHandler.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    32: athrow         
        //    33: aload           6
        //    35: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObject:()Z
        //    38: ifeq            345
        //    41: goto            48
        //    44: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateEncodeHandler.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    47: athrow         
        //    48: aload           6
        //    50: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getTerminalType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    53: checkcast       Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //    56: astore          10
        //    58: aload_2        
        //    59: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:()Ljava/lang/String;
        //    62: astore          11
        //    64: aload           11
        //    66: ldc             "NS"
        //    68: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //    71: ifeq            87
        //    74: aload           11
        //    76: iconst_2       
        //    77: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //    80: goto            89
        //    83: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateEncodeHandler.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    86: athrow         
        //    87: aload           11
        //    89: astore          11
        //    91: aload           10
        //    93: new             Ljava/lang/StringBuilder;
        //    96: dup            
        //    97: invokespecial   java/lang/StringBuilder.<init>:()V
        //   100: ldc             "encode"
        //   102: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   105: aload           11
        //   107: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   110: ldc             ":forKey:"
        //   112: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   115: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   118: ldc             Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;.class
        //   120: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.findMember:(Ljava/lang/String;Ljava/lang/Class;)Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;
        //   123: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   126: astore          12
        //   128: aload           10
        //   130: new             Ljava/lang/StringBuilder;
        //   133: dup            
        //   134: invokespecial   java/lang/StringBuilder.<init>:()V
        //   137: ldc             "decode"
        //   139: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   142: aload           11
        //   144: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   147: ldc             "ForKey:"
        //   149: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   152: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   155: ldc             Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;.class
        //   157: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.findMember:(Ljava/lang/String;Ljava/lang/Class;)Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;
        //   160: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   163: astore          13
        //   165: aload           12
        //   167: ifnull          345
        //   170: aload           13
        //   172: ifnull          345
        //   175: goto            182
        //   178: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateEncodeHandler.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   181: athrow         
        //   182: aload           12
        //   184: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getSelectors:()Ljava/util/List;
        //   189: iconst_0       
        //   190: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   195: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol$SelectorPartSymbol;
        //   198: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol$SelectorPartSymbol.getParameter:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   203: astore          14
        //   205: aload           14
        //   207: ifnull          345
        //   210: aload_2        
        //   211: aload           14
        //   213: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   216: aload           9
        //   218: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   223: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equalsAfterResolving:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Z
        //   226: ifeq            345
        //   229: goto            236
        //   232: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateEncodeHandler.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   235: athrow         
        //   236: aload_2        
        //   237: aload           13
        //   239: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getReturnType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   244: aload           9
        //   246: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   251: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equalsAfterResolving:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Z
        //   254: ifeq            345
        //   257: goto            264
        //   260: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateEncodeHandler.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   263: athrow         
        //   264: aload_1        
        //   265: ldc             "[coder encode"
        //   267: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   270: aload           11
        //   272: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   275: ldc             ":"
        //   277: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   280: aload           4
        //   282: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   285: pop            
        //   286: aload_1        
        //   287: ldc             " forKey:@\""
        //   289: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   292: aload_3        
        //   293: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   296: ldc             "\"];\n"
        //   298: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   301: pop            
        //   302: aload_0        
        //   303: aload_3        
        //   304: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   307: ldc             "="
        //   309: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   312: ldc             "[coder decode"
        //   314: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   317: aload           11
        //   319: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   322: ldc             "ForKey:@\""
        //   324: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   327: pop            
        //   328: aload_0        
        //   329: aload_3        
        //   330: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   333: ldc             "\"];\n"
        //   335: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   338: pop            
        //   339: iconst_1       
        //   340: ireturn        
        //   341: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateEncodeHandler.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   344: athrow         
        //   345: aload_2        
        //   346: aload_0        
        //   347: aload_1        
        //   348: aload_3        
        //   349: aload           4
        //   351: aload           5
        //   353: aload           6
        //   355: aload           8
        //   357: aload           9
        //   359: invokedynamic   process:(Ljava/lang/StringBuilder;Ljava/lang/StringBuilder;Ljava/lang/String;Ljava/lang/String;Lcom/jetbrains/cidr/lang/symbols/objc/OCProtocolSymbol;Lcom/jetbrains/cidr/lang/types/OCType;Ljava/util/List;Lcom/intellij/psi/PsiElement;)Lcom/intellij/util/Processor;
        //   364: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateEncodeHandler.processStructFields:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/util/Processor;)Z
        //   367: ifeq            376
        //   370: iconst_1       
        //   371: ireturn        
        //   372: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateEncodeHandler.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   375: athrow         
        //   376: aload_2        
        //   377: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObject:()Z
        //   380: ifeq            556
        //   383: aload_2        
        //   384: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getTerminalType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   387: checkcast       Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   390: aload           5
        //   392: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.implementsProtocol:(Lcom/jetbrains/cidr/lang/symbols/objc/OCProtocolSymbol;)Z
        //   395: ifeq            556
        //   398: goto            405
        //   401: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateEncodeHandler.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   404: athrow         
        //   405: aload_1        
        //   406: ldc             "[coder encodeObject:"
        //   408: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   411: aload           4
        //   413: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   416: ldc             " forKey:@\""
        //   418: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   421: aload_3        
        //   422: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   425: ldc             "\"];\n"
        //   427: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   430: pop            
        //   431: aload_0        
        //   432: aload_3        
        //   433: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   436: ldc             "="
        //   438: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   441: pop            
        //   442: aload           7
        //   444: ifnull          538
        //   447: goto            454
        //   450: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateEncodeHandler.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   453: athrow         
        //   454: aload           9
        //   456: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   461: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.isArcDisabled:(Lcom/intellij/psi/PsiFile;)Z
        //   464: ifeq            538
        //   467: goto            474
        //   470: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateEncodeHandler.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   473: athrow         
        //   474: aload_0        
        //   475: ldc             "[[coder decodeObjectForKey:@\""
        //   477: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   480: aload_3        
        //   481: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   484: ldc             "\"]"
        //   486: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   489: pop            
        //   490: aload_0        
        //   491: aload_2        
        //   492: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToString:()Z
        //   495: ifeq            514
        //   498: goto            505
        //   501: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateEncodeHandler.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   504: athrow         
        //   505: ldc             "copy"
        //   507: goto            516
        //   510: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateEncodeHandler.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   513: athrow         
        //   514: ldc             "retain"
        //   516: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   519: ldc             "];\n"
        //   521: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   524: pop            
        //   525: aload           8
        //   527: aload           7
        //   529: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   534: pop            
        //   535: goto            554
        //   538: aload_0        
        //   539: ldc             "[coder decodeObjectForKey:@\""
        //   541: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   544: aload_3        
        //   545: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   548: ldc             "\"];\n"
        //   550: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   553: pop            
        //   554: iconst_1       
        //   555: ireturn        
        //   556: aload_2        
        //   557: aload           9
        //   559: invokestatic    com/jetbrains/cidr/lang/types/OCIntType.isBool:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Z
        //   562: ifeq            622
        //   565: aload_1        
        //   566: ldc             "[coder encodeBool:"
        //   568: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   571: aload           4
        //   573: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   576: ldc             " forKey:@\""
        //   578: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   581: aload_3        
        //   582: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   585: ldc             "\"];\n"
        //   587: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   590: pop            
        //   591: aload_0        
        //   592: aload_3        
        //   593: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   596: ldc             "="
        //   598: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   601: ldc             "[coder decodeBoolForKey:@\""
        //   603: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   606: aload_3        
        //   607: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   610: ldc             "\"];\n"
        //   612: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   615: pop            
        //   616: iconst_1       
        //   617: ireturn        
        //   618: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateEncodeHandler.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   621: athrow         
        //   622: aload_2        
        //   623: instanceof      Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   626: ifeq            713
        //   629: aload_2        
        //   630: checkcast       Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   633: aload           9
        //   635: invokevirtual   com/jetbrains/cidr/lang/types/OCIntType.getRank:(Lcom/intellij/psi/PsiElement;)I
        //   638: getstatic       com/jetbrains/cidr/lang/types/OCIntType.INT:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   641: aload           9
        //   643: invokevirtual   com/jetbrains/cidr/lang/types/OCIntType.getRank:(Lcom/intellij/psi/PsiElement;)I
        //   646: if_icmple       713
        //   649: goto            656
        //   652: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateEncodeHandler.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   655: athrow         
        //   656: aload_1        
        //   657: ldc             "[coder encodeInt64:"
        //   659: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   662: aload           4
        //   664: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   667: ldc             " forKey:@\""
        //   669: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   672: aload_3        
        //   673: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   676: ldc             "\"];\n"
        //   678: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   681: pop            
        //   682: aload_0        
        //   683: aload_3        
        //   684: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   687: ldc             "="
        //   689: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   692: ldc             "[coder decodeInt64ForKey:@\""
        //   694: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   697: aload_3        
        //   698: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   701: ldc             "\"];\n"
        //   703: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   706: pop            
        //   707: iconst_1       
        //   708: ireturn        
        //   709: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateEncodeHandler.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   712: athrow         
        //   713: aload_2        
        //   714: aload           9
        //   716: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isIntegerCompatible:(Lcom/intellij/psi/PsiElement;)Z
        //   719: ifeq            839
        //   722: aload_1        
        //   723: ldc             "[coder encodeInt:"
        //   725: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   728: aload           4
        //   730: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   733: ldc             " forKey:@\""
        //   735: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   738: aload_3        
        //   739: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   742: ldc             "\"];\n"
        //   744: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   747: pop            
        //   748: aload_2        
        //   749: getstatic       com/jetbrains/cidr/lang/types/OCIntType.INT:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   752: aload           9
        //   754: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isCompatible:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Z
        //   757: ifeq            776
        //   760: goto            767
        //   763: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateEncodeHandler.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   766: athrow         
        //   767: ldc             ""
        //   769: goto            805
        //   772: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateEncodeHandler.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   775: athrow         
        //   776: new             Ljava/lang/StringBuilder;
        //   779: dup            
        //   780: invokespecial   java/lang/StringBuilder.<init>:()V
        //   783: ldc             "("
        //   785: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   788: aload_2        
        //   789: aload           9
        //   791: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getBestNameInContext:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   794: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   797: ldc             ")"
        //   799: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   802: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   805: astore          10
        //   807: aload_0        
        //   808: aload_3        
        //   809: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   812: ldc             "="
        //   814: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   817: aload           10
        //   819: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   822: ldc             "[coder decodeIntForKey:@\""
        //   824: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   827: aload_3        
        //   828: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   831: ldc             "\"];\n"
        //   833: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   836: pop            
        //   837: iconst_1       
        //   838: ireturn        
        //   839: aload_2        
        //   840: getstatic       com/jetbrains/cidr/lang/types/OCRealType.FLOAT:Lcom/jetbrains/cidr/lang/types/OCRealType;
        //   843: aload           9
        //   845: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equals:(Ljava/lang/Object;Lcom/intellij/psi/PsiElement;)Z
        //   848: ifeq            908
        //   851: aload_1        
        //   852: ldc             "[coder encodeFloat:"
        //   854: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   857: aload           4
        //   859: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   862: ldc             " forKey:@\""
        //   864: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   867: aload_3        
        //   868: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   871: ldc             "\"];\n"
        //   873: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   876: pop            
        //   877: aload_0        
        //   878: aload_3        
        //   879: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   882: ldc             "="
        //   884: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   887: ldc             "[coder decodeFloatForKey:@\""
        //   889: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   892: aload_3        
        //   893: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   896: ldc             "\"];\n"
        //   898: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   901: pop            
        //   902: iconst_1       
        //   903: ireturn        
        //   904: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateEncodeHandler.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   907: athrow         
        //   908: aload_2        
        //   909: instanceof      Lcom/jetbrains/cidr/lang/types/OCRealType;
        //   912: ifeq            972
        //   915: aload_1        
        //   916: ldc             "[coder encodeDouble:"
        //   918: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   921: aload           4
        //   923: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   926: ldc             " forKey:@\""
        //   928: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   931: aload_3        
        //   932: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   935: ldc             "\"];\n"
        //   937: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   940: pop            
        //   941: aload_0        
        //   942: aload_3        
        //   943: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   946: ldc             "="
        //   948: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   951: ldc             "[coder decodeDoubleForKey:@\""
        //   953: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   956: aload_3        
        //   957: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   960: ldc             "\"];\n"
        //   962: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   965: pop            
        //   966: iconst_1       
        //   967: ireturn        
        //   968: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateEncodeHandler.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   971: athrow         
        //   972: aload_2        
        //   973: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToChar:()Z
        //   976: ifeq            1047
        //   979: aload_1        
        //   980: ldc             "[coder encodeObject: [NSData dataWithBytes: "
        //   982: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   985: aload_3        
        //   986: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   989: ldc             " length: strlen("
        //   991: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   994: pop            
        //   995: aload_1        
        //   996: aload_3        
        //   997: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1000: ldc             ")] forKey:@\""
        //  1002: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1005: aload           4
        //  1007: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1010: ldc             "\"];\n"
        //  1012: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1015: pop            
        //  1016: aload_0        
        //  1017: aload_3        
        //  1018: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1021: ldc             "=strdup("
        //  1023: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1026: ldc             "((NSData*)[coder decodeObjectForKey:@\""
        //  1028: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1031: aload_3        
        //  1032: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1035: ldc             "\"]).bytes);\n"
        //  1037: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1040: pop            
        //  1041: iconst_1       
        //  1042: ireturn        
        //  1043: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateEncodeHandler.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1046: athrow         
        //  1047: iconst_0       
        //  1048: ireturn        
        //    Signature:
        //  (Ljava/lang/StringBuilder;Ljava/lang/StringBuilder;Lcom/jetbrains/cidr/lang/types/OCType;Ljava/lang/String;Ljava/lang/String;Lcom/jetbrains/cidr/lang/symbols/objc/OCProtocolSymbol;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;Ljava/util/List<Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;>;Lcom/intellij/psi/PsiElement;)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      14     17     21     Ljava/lang/IllegalArgumentException;
        //  7      26     29     33     Ljava/lang/IllegalArgumentException;
        //  21     41     44     48     Ljava/lang/IllegalArgumentException;
        //  64     83     83     87     Ljava/lang/IllegalArgumentException;
        //  165    175    178    182    Ljava/lang/IllegalArgumentException;
        //  205    229    232    236    Ljava/lang/IllegalArgumentException;
        //  210    257    260    264    Ljava/lang/IllegalArgumentException;
        //  236    341    341    345    Ljava/lang/IllegalArgumentException;
        //  345    372    372    376    Ljava/lang/IllegalArgumentException;
        //  376    398    401    405    Ljava/lang/IllegalArgumentException;
        //  383    447    450    454    Ljava/lang/IllegalArgumentException;
        //  405    467    470    474    Ljava/lang/IllegalArgumentException;
        //  454    498    501    505    Ljava/lang/IllegalArgumentException;
        //  474    510    510    514    Ljava/lang/IllegalArgumentException;
        //  556    618    618    622    Ljava/lang/IllegalArgumentException;
        //  622    649    652    656    Ljava/lang/IllegalArgumentException;
        //  629    709    709    713    Ljava/lang/IllegalArgumentException;
        //  713    760    763    767    Ljava/lang/IllegalArgumentException;
        //  722    772    772    776    Ljava/lang/IllegalArgumentException;
        //  839    904    904    908    Ljava/lang/IllegalArgumentException;
        //  908    968    968    972    Ljava/lang/IllegalArgumentException;
        //  972    1043   1043   1047   Ljava/lang/IllegalArgumentException;
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
    
    private static IllegalArgumentException d(final IllegalArgumentException ex) {
        return ex;
    }
}
