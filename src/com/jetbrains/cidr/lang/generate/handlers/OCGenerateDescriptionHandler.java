// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers;

import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.generate.actions.OCActionContext;
import com.jetbrains.cidr.lang.generate.actions.OCObjCActionContext;
import com.jetbrains.cidr.lang.symbols.objc.OCImplementationSymbol;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import java.util.Iterator;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.util.OCCallableUtil;
import com.jetbrains.cidr.lang.psi.OCInterface;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.intellij.psi.PsiElement;
import javax.swing.JComponent;
import java.util.Map;
import com.jetbrains.cidr.lang.settings.OCOption;
import com.intellij.openapi.util.Pair;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import com.jetbrains.cidr.lang.generate.actions.OCGenerateMethodActionContext;
import com.jetbrains.cidr.lang.settings.OCBooleanOption;

public class OCGenerateDescriptionHandler extends OCGenerateMethodHandler
{
    private static final OCBooleanOption INCLUDE_MEMBER_NAMES;
    
    @Override
    protected String getActionTitle() {
        return "Generate -description";
    }
    
    @Override
    protected String[] getMethodNames() {
        return new String[] { "description" };
    }
    
    protected boolean allowEmptySelection(final OCGenerateMethodActionContext ocGenerateMethodActionContext) {
        return true;
    }
    
    protected boolean defaultIncludeMemberNames(@Nullable final OCCodeStyleSettings ocCodeStyleSettings) {
        Label_0018: {
            try {
                if (ocCodeStyleSettings == null) {
                    break Label_0018;
                }
                final OCCodeStyleSettings ocCodeStyleSettings2 = ocCodeStyleSettings;
                final boolean b = ocCodeStyleSettings2.DESCRIPTION_INCLUDE_MEMBER_NAMES;
                if (b) {
                    break Label_0018;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw d(ex);
            }
            try {
                final OCCodeStyleSettings ocCodeStyleSettings2 = ocCodeStyleSettings;
                final boolean b = ocCodeStyleSettings2.DESCRIPTION_INCLUDE_MEMBER_NAMES;
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
    
    @Override
    protected void loadOptions(final PsiFile psiFile, final Editor editor, @NotNull final OCGenerateMethodActionContext ocGenerateMethodActionContext, @Nullable final OCCodeStyleSettings ocCodeStyleSettings, @NotNull final List<Pair<OCOption, Object>> list) {
        try {
            if (ocGenerateMethodActionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "actionContext", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateDescriptionHandler", "loadOptions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "options", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateDescriptionHandler", "loadOptions"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw d(ex2);
        }
        list.add((Pair<OCOption, Object>)new Pair((Object)OCGenerateDescriptionHandler.INCLUDE_MEMBER_NAMES, (Object)this.defaultIncludeMemberNames(ocCodeStyleSettings)));
        super.loadOptions(psiFile, editor, ocGenerateMethodActionContext, ocCodeStyleSettings, list);
    }
    
    @Override
    protected void saveOptions(final PsiFile psiFile, @NotNull final OCCodeStyleSettings ocCodeStyleSettings, final Map<OCOption, Object> map) {
        try {
            if (ocCodeStyleSettings == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateDescriptionHandler", "saveOptions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        ocCodeStyleSettings.DESCRIPTION_INCLUDE_MEMBER_NAMES = OCClassActionHandlerBase.getOption(map, (OCOption<Boolean, JComponent>)OCGenerateDescriptionHandler.INCLUDE_MEMBER_NAMES);
        super.saveOptions(psiFile, ocCodeStyleSettings, map);
    }
    
    @NotNull
    @Override
    protected String getInsertText(@NotNull final PsiElement psiElement, @Nullable final PsiElement psiElement2, @NotNull final List<OCInstanceVariableSymbol> list, @NotNull final OCGenerateMethodActionContext ocGenerateMethodActionContext) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateDescriptionHandler", "getInsertText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw d(ex);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ivars", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateDescriptionHandler", "getInsertText"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw d(ex2);
        }
        try {
            if (ocGenerateMethodActionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "actionContext", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateDescriptionHandler", "getInsertText"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw d(ex3);
        }
        Label_0213: {
            String s3 = null;
            Label_0178: {
                try {
                    if (!(psiElement instanceof OCInterface)) {
                        break Label_0213;
                    }
                    final StringBuilder sb = new StringBuilder();
                    final OCGenerateMethodActionContext ocGenerateMethodActionContext2 = ocGenerateMethodActionContext;
                    final OCMethodSymbol ocMethodSymbol = ocGenerateMethodActionContext2.getBaseMethod();
                    final PsiElement psiElement3 = psiElement;
                    final String s = OCCallableUtil.methodSignature(ocMethodSymbol, psiElement3);
                    final StringBuilder sb2 = sb.append(s);
                    final String s2 = ";";
                    final StringBuilder sb3 = sb2.append(s2);
                    s3 = sb3.toString();
                    if (s3 == null) {
                        break Label_0178;
                    }
                    return s3;
                }
                catch (IllegalArgumentException ex4) {
                    throw d(ex4);
                }
                try {
                    final StringBuilder sb = new StringBuilder();
                    final OCGenerateMethodActionContext ocGenerateMethodActionContext2 = ocGenerateMethodActionContext;
                    final OCMethodSymbol ocMethodSymbol = ocGenerateMethodActionContext2.getBaseMethod();
                    final PsiElement psiElement3 = psiElement;
                    final String s = OCCallableUtil.methodSignature(ocMethodSymbol, psiElement3);
                    final StringBuilder sb2 = sb.append(s);
                    final String s2 = ";";
                    final StringBuilder sb3 = sb2.append(s2);
                    s3 = sb3.toString();
                    if (s3 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateDescriptionHandler", "getInsertText"));
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw d(ex5);
                }
            }
            return s3;
        }
        final boolean booleanValue = ((OCClassActionHandlerBase<P, M, C>)this).getOption((C)ocGenerateMethodActionContext, (OCOption<Boolean, JComponent>)OCGenerateDescriptionHandler.INCLUDE_MEMBER_NAMES);
        final ArrayList<Pair<String, String>> list2 = new ArrayList<Pair<String, String>>();
        final StringBuilder sb4 = new StringBuilder();
        int n = 1;
        for (final OCInstanceVariableSymbol ocInstanceVariableSymbol : list) {
            final OCPropertySymbol associatedProperty = ocInstanceVariableSymbol.getAssociatedProperty();
            final OCType resolve = ocInstanceVariableSymbol.getType().resolve(psiElement.getContainingFile(), true);
            ArrayList<Pair<String, String>> list3 = null;
            String s4 = null;
            Label_0355: {
                try {
                    list3 = list2;
                    if (associatedProperty != null) {
                        s4 = "self." + associatedProperty.getName();
                        break Label_0355;
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw d(ex6);
                }
                s4 = ocInstanceVariableSymbol.getName();
            }
            a(list3, s4, resolve, booleanValue);
        }
        for (final Pair pair : list2) {
            try {
                sb4.append("[description appendFormat: @\"");
                if (n == 0) {
                    sb4.append(", ");
                }
            }
            catch (IllegalArgumentException ex7) {
                throw d(ex7);
            }
            sb4.append((String)pair.getFirst()).append("\", ").append((String)pair.getSecond()).append("];\n");
            n = 0;
        }
        final OCObjectType superType = ocGenerateMethodActionContext.getType().getSuperType();
        OCImplementationSymbol implementation = null;
        Label_0493: {
            try {
                if (superType != null) {
                    implementation = superType.getImplementation();
                    break Label_0493;
                }
            }
            catch (IllegalArgumentException ex8) {
                throw d(ex8);
            }
            implementation = null;
        }
        final OCImplementationSymbol ocImplementationSymbol = implementation;
        String methodFromTemplate = null;
        Label_0557: {
            Label_0536: {
                try {
                    if (ocImplementationSymbol == null || ocImplementationSymbol.processMembersInAllCategories("description", (Class<? extends OCMemberSymbol>)OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMemberSymbol>)new CommonProcessors.FindFirstProcessor(), true)) {
                        break Label_0536;
                    }
                }
                catch (IllegalArgumentException ex9) {
                    throw d(ex9);
                }
                final String s5 = "OC Overridden Description Body 1.m";
                break Label_0557;
            }
            String s5;
            if (list2.size() > 0) {
                s5 = "OC Overridden Description Body 2.m";
            }
            else {
                s5 = "OC Overridden Description Body 3.m";
            }
            try {
                methodFromTemplate = OCCallableUtil.methodFromTemplate(ocGenerateMethodActionContext.getBaseMethod(), s5, sb4.toString().trim(), psiElement);
                if (methodFromTemplate == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateDescriptionHandler", "getInsertText"));
                }
            }
            catch (IllegalArgumentException ex10) {
                throw d(ex10);
            }
        }
        return methodFromTemplate;
    }
    
    private static void a(final List<Pair<String, String>> p0, final String p1, final OCType p2, final boolean p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //     4: ifeq            114
        //     7: aload_2        
        //     8: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isScalar:()Z
        //    11: ifne            114
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateDescriptionHandler.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    20: athrow         
        //    21: aload_2        
        //    22: aload_0        
        //    23: aload_1        
        //    24: iload_3        
        //    25: invokedynamic   process:(Ljava/util/List;Ljava/lang/String;Z)Lcom/intellij/util/Processor;
        //    30: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateDescriptionHandler.processStructFields:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/util/Processor;)Z
        //    33: ifne            166
        //    36: goto            43
        //    39: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateDescriptionHandler.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    42: athrow         
        //    43: aload_0        
        //    44: iload_3        
        //    45: ifeq            81
        //    48: goto            55
        //    51: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateDescriptionHandler.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    54: athrow         
        //    55: new             Ljava/lang/StringBuilder;
        //    58: dup            
        //    59: invokespecial   java/lang/StringBuilder.<init>:()V
        //    62: aload_1        
        //    63: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    66: ldc             "=%s"
        //    68: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    71: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    74: goto            83
        //    77: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateDescriptionHandler.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    80: athrow         
        //    81: ldc             "%s"
        //    83: new             Ljava/lang/StringBuilder;
        //    86: dup            
        //    87: invokespecial   java/lang/StringBuilder.<init>:()V
        //    90: ldc             "(const char*)"
        //    92: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    95: aload_1        
        //    96: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    99: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   102: invokestatic    com/intellij/openapi/util/Pair.create:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/intellij/openapi/util/Pair;
        //   105: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   110: pop            
        //   111: goto            166
        //   114: aload_0        
        //   115: iload_3        
        //   116: ifeq            152
        //   119: new             Ljava/lang/StringBuilder;
        //   122: dup            
        //   123: invokespecial   java/lang/StringBuilder.<init>:()V
        //   126: aload_1        
        //   127: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   130: ldc             "="
        //   132: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   135: aload_2        
        //   136: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getFormatString:()Ljava/lang/String;
        //   139: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   142: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   145: goto            156
        //   148: invokestatic    com/jetbrains/cidr/lang/generate/handlers/OCGenerateDescriptionHandler.d:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   151: athrow         
        //   152: aload_2        
        //   153: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getFormatString:()Ljava/lang/String;
        //   156: aload_1        
        //   157: invokestatic    com/intellij/openapi/util/Pair.create:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/intellij/openapi/util/Pair;
        //   160: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   165: pop            
        //   166: return         
        //    Signature:
        //  (Ljava/util/List<Lcom/intellij/openapi/util/Pair<Ljava/lang/String;Ljava/lang/String;>;>;Ljava/lang/String;Lcom/jetbrains/cidr/lang/types/OCType;Z)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      14     17     21     Ljava/lang/IllegalArgumentException;
        //  7      36     39     43     Ljava/lang/IllegalArgumentException;
        //  21     48     51     55     Ljava/lang/IllegalArgumentException;
        //  43     77     77     81     Ljava/lang/IllegalArgumentException;
        //  114    148    148    152    Ljava/lang/IllegalArgumentException;
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
    
    static {
        INCLUDE_MEMBER_NAMES = new OCBooleanOption("Include member names");
    }
    
    private static IllegalArgumentException d(final IllegalArgumentException ex) {
        return ex;
    }
}
