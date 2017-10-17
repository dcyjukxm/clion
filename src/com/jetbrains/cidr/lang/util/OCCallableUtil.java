// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.psi.OCStructLike;
import com.jetbrains.cidr.lang.psi.OCCppNamespace;
import java.util.List;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import java.util.Collection;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.symbols.OCSymbolAttribute;
import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.types.OCEllipsisType;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import java.util.Iterator;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.refactoring.OCNameSuggester;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.jetbrains.cidr.lang.types.OCVoidType;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import com.intellij.ide.fileTemplates.FileTemplate;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import java.util.Properties;
import com.intellij.ide.fileTemplates.FileTemplateManager;
import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.Nullable;
import java.util.Map;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;

public class OCCallableUtil
{
    public static final String IMPLEMENTED_METHOD_TEMPLATE_NAME = "OC Implemented Method Body.m";
    public static final String OVERRIDDEN_METHOD_TEMPLATE_NAME = "OC Overridden Method Body.m";
    private static final String OVERRIDDEN_INIT_METHOD_TEMPLATE_NAME = "OC Overridden InitWith Method Body.m";
    private static final String OVERRIDDEN_IS_EQUAL_METHOD_TEMPLATE_NAME = "OC Overridden IsEqual Method Body.m";
    public static final String OVERRIDDEN_DESCRIPTION_TEMPLATE_NAME1 = "OC Overridden Description Body 1.m";
    public static final String OVERRIDDEN_DESCRIPTION_TEMPLATE_NAME2 = "OC Overridden Description Body 2.m";
    public static final String OVERRIDDEN_DESCRIPTION_TEMPLATE_NAME3 = "OC Overridden Description Body 3.m";
    public static final String OVERRIDDEN_COPY_TEMPLATE_NAME1 = "OC Overridden CopyWithZone Body 1.m";
    public static final String OVERRIDDEN_COPY_TEMPLATE_NAME2 = "OC Overridden CopyWithZone Body 2.m";
    public static final String OVERRIDDEN_COPY_TEMPLATE_NAME3 = "OC Overridden CopyWithZone Body 3.m";
    private static final String PROPERTY_GETTER_TEMPLATE_NAME = "OC Property Getter Body.m";
    private static final String PROPERTY_SETTER_TEMPLATE_NAME = "OC Property Setter Body.m";
    private static final String CPP_IMPLEMENTED_FUNCTION_TEMPLATE_NAME = "C++ Implemented Function Body.cc";
    private static final String CPP_OVERRIDDEN_FUNCTION_TEMPLATE_NAME = "C++ Overridden Function Body.cc";
    private static final String CONTAINING_CLASS_TEMPLATE_PROPERTY = "CONTAINING_CLASS";
    private static final String RETURN_TYPE_TEMPLATE_PROPERTY = "RETURN_TYPE";
    private static final String DEFAULT_RETURN_VALUE_TEMPLATE_PROPERTY = "DEFAULT_RETURN_VALUE";
    private static final String CALL_SUPER_TEMPLATE_PROPERTY = "CALL_SUPER";
    private static final String CUSTOM_CODE_TEMPLATE_PROPERTY = "CUSTOM_CODE";
    public static final String IVAR_IS_AVAILABLE_TEMPLATE_PROPERTY = "IVAR_IS_AVAILABLE";
    private static final String IVAR_TEMPLATE_PROPERTY = "IVAR";
    private static final String PARAM_TEMPLATE_PROPERTY = "PARAM";
    private static final String SETTER_SEMANTICS_TEMPLATE_PROPERTY = "SETTER_SEMANTICS";
    
    public static String methodText(@NotNull final OCMethodSymbol ocMethodSymbol, final String s, final PsiElement psiElement) {
        try {
            if (ocMethodSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "baseMethod", "com/jetbrains/cidr/lang/util/OCCallableUtil", "methodText"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        return methodText(ocMethodSymbol, null, methodSignature(ocMethodSymbol, psiElement), s, psiElement);
    }
    
    public static String methodFromTemplate(@NotNull final OCMethodSymbol ocMethodSymbol, final String s, final PsiElement psiElement, @Nullable final Map<String, String> map) {
        try {
            if (ocMethodSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "baseMethod", "com/jetbrains/cidr/lang/util/OCCallableUtil", "methodFromTemplate"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        return a(ocMethodSymbol, ocMethodSymbol.getReturnType().resolve((PsiFile)ocMethodSymbol.getContainingOCFile()), s, methodSignature(ocMethodSymbol, psiElement), "", psiElement, map);
    }
    
    public static String methodFromTemplate(@NotNull final OCMethodSymbol ocMethodSymbol, final String s, final String s2, final PsiElement psiElement) {
        try {
            if (ocMethodSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "baseMethod", "com/jetbrains/cidr/lang/util/OCCallableUtil", "methodFromTemplate"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        return methodText(ocMethodSymbol, s, methodSignature(ocMethodSymbol, psiElement), s2, psiElement);
    }
    
    public static String methodWithSignature(@NotNull final OCType ocType, final String s, final PsiElement psiElement) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "returnType", "com/jetbrains/cidr/lang/util/OCCallableUtil", "methodWithSignature"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        return a(null, ocType, "OC Implemented Method Body.m", s, "", psiElement, null);
    }
    
    public static String functionWithSignature(@NotNull final OCType ocType, final String s, final PsiElement psiElement) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "returnType", "com/jetbrains/cidr/lang/util/OCCallableUtil", "functionWithSignature"));
            }
        }
        catch (Exception ex) {
            throw b(ex);
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(s).append("{\n ");
        final FileTemplateManager instance = FileTemplateManager.getInstance(psiElement.getProject());
        final FileTemplate codeTemplate = instance.getCodeTemplate("C++ Implemented Function Body.cc");
        if (codeTemplate != null) {
            final Properties properties = new Properties(FileTemplateManager.getInstance(psiElement.getProject()).getDefaultProperties());
            final OCType resolve = ocType.resolve(new OCResolveContext(psiElement));
            properties.setProperty("RETURN_TYPE", resolve.getBestNameInContext(psiElement));
            properties.setProperty("DEFAULT_RETURN_VALUE", resolve.getDefaultValue(psiElement));
            try {
                sb.append(codeTemplate.getText(properties));
            }
            catch (Exception ex2) {
                throw new RuntimeException(String.format("Unable to load template for file template '%s'!", instance.internalTemplateToSubject("C++ Implemented Function Body.cc")), ex2);
            }
        }
        sb.append("\n}\n");
        return sb.toString();
    }
    
    public static String methodWithSignature(@NotNull final OCMethodSymbol ocMethodSymbol, final String s, final String s2, final PsiElement psiElement) {
        try {
            if (ocMethodSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "baseMethod", "com/jetbrains/cidr/lang/util/OCCallableUtil", "methodWithSignature"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        return methodText(ocMethodSymbol, null, s, s2, psiElement);
    }
    
    public static String methodText(@NotNull final OCMethodSymbol ocMethodSymbol, final String s, final String s2, final String s3, final PsiElement psiElement) {
        try {
            if (ocMethodSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "baseMethod", "com/jetbrains/cidr/lang/util/OCCallableUtil", "methodText"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        return a(ocMethodSymbol, ocMethodSymbol.getReturnType().resolve((PsiFile)ocMethodSymbol.getContainingOCFile()), s, s2, s3, psiElement, null);
    }
    
    public static String methodText(@NotNull final String s, @NotNull final String s2, @NotNull final PsiElement psiElement) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "signature", "com/jetbrains/cidr/lang/util/OCCallableUtil", "methodText"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "body", "com/jetbrains/cidr/lang/util/OCCallableUtil", "methodText"));
            }
        }
        catch (RuntimeException ex2) {
            throw b(ex2);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/util/OCCallableUtil", "methodText"));
            }
        }
        catch (RuntimeException ex3) {
            throw b(ex3);
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(s);
        final OCCodeStyleSettings ocCodeStyleSettings = (OCCodeStyleSettings)CodeStyleSettingsManager.getSettings(psiElement.getProject()).getCustomSettings((Class)OCCodeStyleSettings.class);
        Label_0189: {
            try {
                if (ocCodeStyleSettings == null) {
                    break Label_0189;
                }
                final OCCodeStyleSettings ocCodeStyleSettings2 = ocCodeStyleSettings;
                final boolean b = ocCodeStyleSettings2.SEMICOLON_AFTER_METHOD_SIGNATURE;
                if (b) {
                    break Label_0189;
                }
                break Label_0189;
            }
            catch (RuntimeException ex4) {
                throw b(ex4);
            }
            try {
                final OCCodeStyleSettings ocCodeStyleSettings2 = ocCodeStyleSettings;
                final boolean b = ocCodeStyleSettings2.SEMICOLON_AFTER_METHOD_SIGNATURE;
                if (b) {
                    sb.append(";");
                }
            }
            catch (RuntimeException ex5) {
                throw b(ex5);
            }
        }
        sb.append("{\n ");
        sb.append(s2);
        sb.append("\n}\n");
        return sb.toString();
    }
    
    private static String a(@Nullable final OCMethodSymbol p0, @NotNull final OCType p1, @Nullable final String p2, final String p3, final String p4, @NotNull final PsiElement p5, @Nullable final Map<String, String> p6) {
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
        //    18: ldc             "returnType"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/util/OCCallableUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "methodText"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    43: athrow         
        //    44: aload           5
        //    46: ifnonnull       89
        //    49: new             Ljava/lang/IllegalArgumentException;
        //    52: dup            
        //    53: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    55: ldc             3
        //    57: anewarray       Ljava/lang/Object;
        //    60: dup            
        //    61: ldc             0
        //    63: ldc             "context"
        //    65: aastore        
        //    66: dup            
        //    67: ldc             1
        //    69: ldc             "com/jetbrains/cidr/lang/util/OCCallableUtil"
        //    71: aastore        
        //    72: dup            
        //    73: ldc             2
        //    75: ldc             "methodText"
        //    77: aastore        
        //    78: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    81: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    84: athrow         
        //    85: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    88: athrow         
        //    89: new             Ljava/lang/StringBuilder;
        //    92: dup            
        //    93: invokespecial   java/lang/StringBuilder.<init>:()V
        //    96: astore          7
        //    98: aload           7
        //   100: aload_3        
        //   101: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   104: pop            
        //   105: aload           5
        //   107: invokeinterface com/intellij/psi/PsiElement.getProject:()Lcom/intellij/openapi/project/Project;
        //   112: astore          8
        //   114: aload           8
        //   116: invokestatic    com/intellij/psi/codeStyle/CodeStyleSettingsManager.getSettings:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/codeStyle/CodeStyleSettings;
        //   119: ldc             Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;.class
        //   121: invokevirtual   com/intellij/psi/codeStyle/CodeStyleSettings.getCustomSettings:(Ljava/lang/Class;)Lcom/intellij/psi/codeStyle/CustomCodeStyleSettings;
        //   124: checkcast       Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   127: astore          9
        //   129: aload           9
        //   131: ifnull          164
        //   134: aload           9
        //   136: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.SEMICOLON_AFTER_METHOD_SIGNATURE:Z
        //   139: ifeq            164
        //   142: goto            149
        //   145: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   148: athrow         
        //   149: aload           7
        //   151: ldc             ";"
        //   153: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   156: pop            
        //   157: goto            164
        //   160: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   163: athrow         
        //   164: aload           7
        //   166: ldc             "{\n "
        //   168: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   171: pop            
        //   172: aload           8
        //   174: invokestatic    com/intellij/ide/fileTemplates/FileTemplateManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/ide/fileTemplates/FileTemplateManager;
        //   177: astore          10
        //   179: aload_0        
        //   180: ifnull          196
        //   183: aload_0        
        //   184: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getGeneratedFromProperty:()Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   189: goto            197
        //   192: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   195: athrow         
        //   196: aconst_null    
        //   197: astore          12
        //   199: aload           12
        //   201: ifnull          218
        //   204: aload           12
        //   206: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.getAssociatedIvar:()Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //   211: goto            219
        //   214: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   217: athrow         
        //   218: aconst_null    
        //   219: astore          13
        //   221: aload           5
        //   223: ldc             Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;.class
        //   225: iconst_0       
        //   226: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Z)Lcom/intellij/psi/PsiElement;
        //   229: checkcast       Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
        //   232: astore          14
        //   234: aload           12
        //   236: ifnull          283
        //   239: aload           13
        //   241: ifnull          283
        //   244: goto            251
        //   247: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   250: athrow         
        //   251: aload_0        
        //   252: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.isSetter:()Z
        //   257: ifeq            276
        //   260: goto            267
        //   263: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   266: athrow         
        //   267: ldc             "OC Property Setter Body.m"
        //   269: goto            278
        //   272: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   275: athrow         
        //   276: ldc             "OC Property Getter Body.m"
        //   278: astore          11
        //   280: goto            448
        //   283: aload_2        
        //   284: ifnull          306
        //   287: aload_2        
        //   288: ldc             "OC Overridden Method Body.m"
        //   290: if_acmpeq       306
        //   293: goto            300
        //   296: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   299: athrow         
        //   300: aload_2        
        //   301: astore          11
        //   303: goto            448
        //   306: aload_0        
        //   307: ifnull          338
        //   310: aload_0        
        //   311: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getName:()Ljava/lang/String;
        //   316: ldc             "init"
        //   318: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.startsWithWord:(Ljava/lang/String;Ljava/lang/String;)Z
        //   321: ifeq            338
        //   324: goto            331
        //   327: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   330: athrow         
        //   331: ldc             "OC Overridden InitWith Method Body.m"
        //   333: astore          11
        //   335: goto            448
        //   338: aload_0        
        //   339: ifnull          370
        //   342: aload_0        
        //   343: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getName:()Ljava/lang/String;
        //   348: ldc             "isEqual:"
        //   350: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   353: ifeq            370
        //   356: goto            363
        //   359: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   362: athrow         
        //   363: ldc             "OC Overridden IsEqual Method Body.m"
        //   365: astore          11
        //   367: goto            448
        //   370: aload_0        
        //   371: ifnull          402
        //   374: aload_0        
        //   375: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getName:()Ljava/lang/String;
        //   380: ldc             "description"
        //   382: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   385: ifeq            402
        //   388: goto            395
        //   391: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   394: athrow         
        //   395: ldc             "OC Overridden Description Body 2.m"
        //   397: astore          11
        //   399: goto            448
        //   402: aload_0        
        //   403: ifnull          434
        //   406: aload_0        
        //   407: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getName:()Ljava/lang/String;
        //   412: ldc             "copyWithZone:"
        //   414: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   417: ifeq            434
        //   420: goto            427
        //   423: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   426: athrow         
        //   427: ldc             "OC Overridden CopyWithZone Body 2.m"
        //   429: astore          11
        //   431: goto            448
        //   434: aload_2        
        //   435: ifnull          444
        //   438: aload_2        
        //   439: astore          11
        //   441: goto            448
        //   444: ldc             "OC Overridden Method Body.m"
        //   446: astore          11
        //   448: aload           10
        //   450: aload           11
        //   452: invokevirtual   com/intellij/ide/fileTemplates/FileTemplateManager.getCodeTemplate:(Ljava/lang/String;)Lcom/intellij/ide/fileTemplates/FileTemplate;
        //   455: astore          15
        //   457: aload           15
        //   459: ifnull          945
        //   462: new             Ljava/util/Properties;
        //   465: dup            
        //   466: aload           8
        //   468: invokestatic    com/intellij/ide/fileTemplates/FileTemplateManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/ide/fileTemplates/FileTemplateManager;
        //   471: invokevirtual   com/intellij/ide/fileTemplates/FileTemplateManager.getDefaultProperties:()Ljava/util/Properties;
        //   474: invokespecial   java/util/Properties.<init>:(Ljava/util/Properties;)V
        //   477: astore          16
        //   479: aload           6
        //   481: ifnull          549
        //   484: aload           6
        //   486: invokeinterface java/util/Map.entrySet:()Ljava/util/Set;
        //   491: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //   496: astore          17
        //   498: aload           17
        //   500: invokeinterface java/util/Iterator.hasNext:()Z
        //   505: ifeq            549
        //   508: aload           17
        //   510: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   515: checkcast       Ljava/util/Map$Entry;
        //   518: astore          18
        //   520: aload           16
        //   522: aload           18
        //   524: invokeinterface java/util/Map$Entry.getKey:()Ljava/lang/Object;
        //   529: checkcast       Ljava/lang/String;
        //   532: aload           18
        //   534: invokeinterface java/util/Map$Entry.getValue:()Ljava/lang/Object;
        //   539: checkcast       Ljava/lang/String;
        //   542: invokevirtual   java/util/Properties.setProperty:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;
        //   545: pop            
        //   546: goto            498
        //   549: aload           16
        //   551: ldc             "CONTAINING_CLASS"
        //   553: aload           14
        //   555: ifnull          572
        //   558: aload           14
        //   560: invokeinterface com/jetbrains/cidr/lang/psi/OCClassDeclaration.getName:()Ljava/lang/String;
        //   565: goto            574
        //   568: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   571: athrow         
        //   572: ldc             ""
        //   574: invokevirtual   java/util/Properties.setProperty:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;
        //   577: pop            
        //   578: aload           16
        //   580: ldc             "RETURN_TYPE"
        //   582: aload_1        
        //   583: aload           5
        //   585: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getBestNameInContext:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   588: invokevirtual   java/util/Properties.setProperty:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;
        //   591: pop            
        //   592: aload           16
        //   594: ldc             "DEFAULT_RETURN_VALUE"
        //   596: aload_1        
        //   597: aload           5
        //   599: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getDefaultValue:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   602: invokevirtual   java/util/Properties.setProperty:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;
        //   605: pop            
        //   606: aload           16
        //   608: ldc             "CALL_SUPER"
        //   610: aload_0        
        //   611: aload           5
        //   613: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.a:(Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   616: invokevirtual   java/util/Properties.setProperty:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;
        //   619: pop            
        //   620: aload           16
        //   622: ldc             "CUSTOM_CODE"
        //   624: aload           4
        //   626: ifnull          638
        //   629: aload           4
        //   631: goto            640
        //   634: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   637: athrow         
        //   638: ldc             ""
        //   640: invokevirtual   java/util/Properties.setProperty:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;
        //   643: pop            
        //   644: aload           5
        //   646: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   651: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.isArcEnabled:(Lcom/intellij/psi/PsiFile;)Z
        //   654: istore          17
        //   656: aload           12
        //   658: ifnull          852
        //   661: aload           13
        //   663: ifnull          852
        //   666: goto            673
        //   669: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   672: athrow         
        //   673: aload           16
        //   675: ldc             "IVAR"
        //   677: aload           13
        //   679: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.getName:()Ljava/lang/String;
        //   684: invokevirtual   java/util/Properties.setProperty:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;
        //   687: pop            
        //   688: aload_0        
        //   689: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.isSetter:()Z
        //   694: ifeq            753
        //   697: goto            704
        //   700: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   703: athrow         
        //   704: aload_0        
        //   705: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getSelectors:()Ljava/util/List;
        //   710: iconst_0       
        //   711: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   716: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol$SelectorPartSymbol;
        //   719: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol$SelectorPartSymbol.getParameter:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   724: astore          18
        //   726: aload           18
        //   728: ifnull          753
        //   731: aload           16
        //   733: ldc             "PARAM"
        //   735: aload           18
        //   737: aload           5
        //   739: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.suggestUniqueName:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   742: invokevirtual   java/util/Properties.setProperty:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;
        //   745: pop            
        //   746: goto            753
        //   749: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   752: athrow         
        //   753: aload           12
        //   755: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.COPY:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   758: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.hasAttribute:(Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;)Z
        //   763: ifeq            800
        //   766: aload           16
        //   768: ldc             "SETTER_SEMANTICS"
        //   770: iload           17
        //   772: ifeq            791
        //   775: goto            782
        //   778: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   781: athrow         
        //   782: ldc             "copyArc"
        //   784: goto            793
        //   787: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   790: athrow         
        //   791: ldc             "copy"
        //   793: invokevirtual   java/util/Properties.setProperty:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;
        //   796: pop            
        //   797: goto            852
        //   800: aload           12
        //   802: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.RETAIN:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   805: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.hasAttribute:(Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;)Z
        //   810: ifeq            842
        //   813: iload           17
        //   815: ifne            842
        //   818: goto            825
        //   821: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   824: athrow         
        //   825: aload           16
        //   827: ldc             "SETTER_SEMANTICS"
        //   829: ldc             "retain"
        //   831: invokevirtual   java/util/Properties.setProperty:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;
        //   834: pop            
        //   835: goto            852
        //   838: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   841: athrow         
        //   842: aload           16
        //   844: ldc             "SETTER_SEMANTICS"
        //   846: ldc             "assign"
        //   848: invokevirtual   java/util/Properties.setProperty:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;
        //   851: pop            
        //   852: aload_0        
        //   853: ifnull          889
        //   856: aload_0        
        //   857: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getName:()Ljava/lang/String;
        //   862: ldc             "dealloc"
        //   864: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   867: ifeq            889
        //   870: goto            877
        //   873: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   876: athrow         
        //   877: iload           17
        //   879: ifne            911
        //   882: goto            889
        //   885: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   888: athrow         
        //   889: aload           7
        //   891: aload           15
        //   893: aload           16
        //   895: invokeinterface com/intellij/ide/fileTemplates/FileTemplate.getText:(Ljava/util/Properties;)Ljava/lang/String;
        //   900: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   903: pop            
        //   904: goto            911
        //   907: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   910: athrow         
        //   911: goto            945
        //   914: astore          18
        //   916: new             Ljava/lang/RuntimeException;
        //   919: dup            
        //   920: ldc             "Unable to load template for file template '%s'!"
        //   922: iconst_1       
        //   923: anewarray       Ljava/lang/Object;
        //   926: dup            
        //   927: iconst_0       
        //   928: aload           10
        //   930: aload           11
        //   932: invokevirtual   com/intellij/ide/fileTemplates/FileTemplateManager.internalTemplateToSubject:(Ljava/lang/String;)Ljava/lang/String;
        //   935: aastore        
        //   936: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   939: aload           18
        //   941: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   944: athrow         
        //   945: aload           7
        //   947: ldc             "\n}\n"
        //   949: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   952: pop            
        //   953: aload           7
        //   955: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   958: areturn        
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;Lcom/jetbrains/cidr/lang/types/OCType;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/intellij/psi/PsiElement;Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;)Ljava/lang/String;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  877    904    907    911    Ljava/lang/Exception;
        //  856    882    885    889    Ljava/lang/Exception;
        //  852    870    873    877    Ljava/lang/Exception;
        //  813    838    838    842    Ljava/lang/Exception;
        //  800    818    821    825    Ljava/lang/Exception;
        //  766    787    787    791    Ljava/lang/Exception;
        //  753    775    778    782    Ljava/lang/Exception;
        //  726    746    749    753    Ljava/lang/Exception;
        //  661    697    700    704    Ljava/lang/Exception;
        //  656    666    669    673    Ljava/lang/Exception;
        //  574    634    634    638    Ljava/lang/Exception;
        //  549    568    568    572    Ljava/lang/Exception;
        //  402    420    423    427    Ljava/lang/Exception;
        //  370    388    391    395    Ljava/lang/Exception;
        //  338    356    359    363    Ljava/lang/Exception;
        //  306    324    327    331    Ljava/lang/Exception;
        //  283    293    296    300    Ljava/lang/Exception;
        //  251    272    272    276    Ljava/lang/Exception;
        //  239    260    263    267    Ljava/lang/Exception;
        //  234    244    247    251    Ljava/lang/Exception;
        //  199    214    214    218    Ljava/lang/Exception;
        //  179    192    192    196    Ljava/lang/Exception;
        //  134    157    160    164    Ljava/lang/Exception;
        //  129    142    145    149    Ljava/lang/Exception;
        //  44     85     85     89     Ljava/lang/Exception;
        //  0      40     40     44     Ljava/lang/Exception;
        //  852    911    914    945    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0251:
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
    
    @NotNull
    private static String a(@NotNull final OCFunctionSymbol ocFunctionSymbol, @Nullable final OCFunctionSymbol ocFunctionSymbol2, @NotNull final PsiElement psiElement) {
        try {
            if (ocFunctionSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "function", "com/jetbrains/cidr/lang/util/OCCallableUtil", "fillFunctionBodyTemplate"));
            }
        }
        catch (Exception ex) {
            throw b(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/util/OCCallableUtil", "fillFunctionBodyTemplate"));
            }
        }
        catch (Exception ex2) {
            throw b(ex2);
        }
        final FileTemplateManager instance = FileTemplateManager.getInstance(ocFunctionSymbol.getProject());
        String s = null;
        Label_0111: {
            try {
                if (ocFunctionSymbol2 != null) {
                    s = "C++ Overridden Function Body.cc";
                    break Label_0111;
                }
            }
            catch (Exception ex3) {
                throw b(ex3);
            }
            s = "C++ Implemented Function Body.cc";
        }
        final String s2 = s;
        final FileTemplate codeTemplate = instance.getCodeTemplate(s2);
        OCType ocType = ocFunctionSymbol.getEffectiveResolvedType();
        Label_0153: {
            try {
                if (!ocFunctionSymbol.isCppConstructor()) {
                    if (!ocFunctionSymbol.isCppDestructor()) {
                        break Label_0153;
                    }
                }
            }
            catch (Exception ex4) {
                throw b(ex4);
            }
            ocType = OCVoidType.instance();
        }
        if (codeTemplate != null) {
            final Properties properties = new Properties(FileTemplateManager.getInstance(ocFunctionSymbol.getProject()).getDefaultProperties());
            Label_0248: {
                Properties properties2 = null;
                String s3 = null;
                String b = null;
                Label_0244: {
                    Label_0230: {
                        try {
                            properties.setProperty("RETURN_TYPE", ocType.getBestNameInContext(psiElement));
                            properties.setProperty("DEFAULT_RETURN_VALUE", ocType.getDefaultValue(psiElement));
                            if (ocFunctionSymbol2 == null) {
                                break Label_0248;
                            }
                            properties2 = properties;
                            s3 = "CALL_SUPER";
                            final OCFunctionSymbol ocFunctionSymbol3 = ocFunctionSymbol2;
                            final OCVisibility ocVisibility = ocFunctionSymbol3.getVisibility();
                            final OCVisibility ocVisibility2 = OCVisibility.PRIVATE;
                            if (ocVisibility != ocVisibility2) {
                                break Label_0230;
                            }
                            break Label_0230;
                        }
                        catch (Exception ex5) {
                            throw b(ex5);
                        }
                        try {
                            properties2 = properties;
                            s3 = "CALL_SUPER";
                            final OCFunctionSymbol ocFunctionSymbol3 = ocFunctionSymbol2;
                            final OCVisibility ocVisibility = ocFunctionSymbol3.getVisibility();
                            final OCVisibility ocVisibility2 = OCVisibility.PRIVATE;
                            if (ocVisibility != ocVisibility2) {
                                b = b(ocFunctionSymbol, ocFunctionSymbol2);
                                break Label_0244;
                            }
                        }
                        catch (Exception ex6) {
                            throw b(ex6);
                        }
                    }
                    b = "";
                }
                properties2.setProperty(s3, b);
                try {
                    final String text = codeTemplate.getText(properties);
                    if (text == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCCallableUtil", "fillFunctionBodyTemplate"));
                    }
                    return text;
                }
                catch (Exception ex7) {
                    throw new RuntimeException(String.format("Unable to load template for file template '%s'!", instance.internalTemplateToSubject(s2)), ex7);
                }
            }
        }
        String s4;
        try {
            s4 = "";
            if (s4 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCCallableUtil", "fillFunctionBodyTemplate"));
            }
        }
        catch (Exception ex8) {
            throw b(ex8);
        }
        return s4;
    }
    
    private static String a(final String s, final boolean b) {
        Label_0027: {
            try {
                if (!s.startsWith("isEqual")) {
                    break Label_0027;
                }
                final boolean b2 = b;
                if (b2) {
                    return "other";
                }
                break Label_0027;
            }
            catch (RuntimeException ex) {
                throw b(ex);
            }
            try {
                final boolean b2 = b;
                if (b2) {
                    return "other";
                }
            }
            catch (RuntimeException ex2) {
                throw b(ex2);
            }
            try {
                if (s.equals("encodeWithCoder:")) {
                    return "coder";
                }
                final String s2 = s;
                final String s3 = "initWithCoder:";
                final boolean b3 = s2.equals(s3);
                if (b3) {
                    return "coder";
                }
                return null;
            }
            catch (RuntimeException ex3) {
                throw b(ex3);
            }
        }
        try {
            final String s2 = s;
            final String s3 = "initWithCoder:";
            final boolean b3 = s2.equals(s3);
            if (b3) {
                return "coder";
            }
        }
        catch (RuntimeException ex4) {
            throw b(ex4);
        }
        return null;
    }
    
    private static String a(@Nullable final OCMethodSymbol ocMethodSymbol, @NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/util/OCCallableUtil", "getCallSuperText"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        try {
            if (ocMethodSymbol == null) {
                return "";
            }
        }
        catch (RuntimeException ex2) {
            throw b(ex2);
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("[super ");
        boolean b = true;
        for (final OCMethodSymbol.SelectorPartSymbol selectorPartSymbol : ocMethodSymbol.getSelectors()) {
            try {
                if (!b) {
                    sb.append(' ');
                }
            }
            catch (RuntimeException ex3) {
                throw b(ex3);
            }
            sb.append(selectorPartSymbol.getSelectorName()).append(' ');
            final OCDeclaratorSymbol parameter = selectorPartSymbol.getParameter();
            Label_0198: {
                if (parameter != null) {
                    final String a = a(ocMethodSymbol.getName(), b);
                    try {
                        if (a != null) {
                            sb.append(a);
                            break Label_0198;
                        }
                    }
                    catch (RuntimeException ex4) {
                        throw b(ex4);
                    }
                    sb.append(OCNameSuggester.suggestUniqueName(parameter, psiElement));
                }
            }
            b = false;
        }
        sb.append("]");
        return sb.toString();
    }
    
    @NotNull
    private static String b(@NotNull final OCFunctionSymbol p0, @NotNull final OCFunctionSymbol p1) {
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
        //    18: ldc             "override"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/util/OCCallableUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getBaseCallText"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
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
        //    62: ldc             "baseToCall"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/util/OCCallableUtil"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "getBaseCallText"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    87: athrow         
        //    88: aload_1        
        //    89: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isCppConstructor:()Z
        //    92: ifne            109
        //    95: aload_1        
        //    96: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isCppDestructor:()Z
        //    99: ifeq            157
        //   102: goto            109
        //   105: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   108: athrow         
        //   109: ldc             ""
        //   111: dup            
        //   112: ifnonnull       156
        //   115: goto            122
        //   118: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   121: athrow         
        //   122: new             Ljava/lang/IllegalStateException;
        //   125: dup            
        //   126: ldc             "@NotNull method %s.%s must not return null"
        //   128: ldc             2
        //   130: anewarray       Ljava/lang/Object;
        //   133: dup            
        //   134: ldc             0
        //   136: ldc             "com/jetbrains/cidr/lang/util/OCCallableUtil"
        //   138: aastore        
        //   139: dup            
        //   140: ldc             1
        //   142: ldc             "getBaseCallText"
        //   144: aastore        
        //   145: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   148: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   151: athrow         
        //   152: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   155: athrow         
        //   156: areturn        
        //   157: new             Ljava/lang/StringBuilder;
        //   160: dup            
        //   161: invokespecial   java/lang/StringBuilder.<init>:()V
        //   164: aload_1        
        //   165: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   168: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getName:()Ljava/lang/String;
        //   171: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   174: ldc             "::"
        //   176: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   179: aload_0        
        //   180: aload_1        
        //   181: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.a:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;)Ljava/lang/String;
        //   184: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   187: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   190: dup            
        //   191: ifnonnull       228
        //   194: new             Ljava/lang/IllegalStateException;
        //   197: dup            
        //   198: ldc             "@NotNull method %s.%s must not return null"
        //   200: ldc             2
        //   202: anewarray       Ljava/lang/Object;
        //   205: dup            
        //   206: ldc             0
        //   208: ldc             "com/jetbrains/cidr/lang/util/OCCallableUtil"
        //   210: aastore        
        //   211: dup            
        //   212: ldc             1
        //   214: ldc             "getBaseCallText"
        //   216: aastore        
        //   217: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   220: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   223: athrow         
        //   224: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   227: athrow         
        //   228: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  0      40     40     44     Ljava/lang/RuntimeException;
        //  44     84     84     88     Ljava/lang/RuntimeException;
        //  88     102    105    109    Ljava/lang/RuntimeException;
        //  95     115    118    122    Ljava/lang/RuntimeException;
        //  109    152    152    156    Ljava/lang/RuntimeException;
        //  157    224    224    228    Ljava/lang/RuntimeException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0109:
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
    
    @NotNull
    private static String a(@NotNull final OCFunctionSymbol ocFunctionSymbol, @NotNull final OCFunctionSymbol ocFunctionSymbol2) {
        try {
            if (ocFunctionSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "override", "com/jetbrains/cidr/lang/util/OCCallableUtil", "getBaseCallExpression"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        try {
            if (ocFunctionSymbol2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "baseToCall", "com/jetbrains/cidr/lang/util/OCCallableUtil", "getBaseCallExpression"));
            }
        }
        catch (RuntimeException ex2) {
            throw b(ex2);
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(ocFunctionSymbol2.getName()).append("(");
        int n = 1;
        for (final OCDeclaratorSymbol ocDeclaratorSymbol : ocFunctionSymbol.getParameterSymbols()) {
            final OCType type = ocDeclaratorSymbol.getType();
            Label_0182: {
                Label_0175: {
                    try {
                        if (type instanceof OCEllipsisType) {
                            break;
                        }
                        final OCType ocType = type;
                        final boolean b = ocType instanceof OCVoidType;
                        if (b) {
                            break Label_0175;
                        }
                        break Label_0182;
                    }
                    catch (RuntimeException ex3) {
                        throw b(ex3);
                    }
                    try {
                        final OCType ocType = type;
                        final boolean b = ocType instanceof OCVoidType;
                        if (b) {
                            break;
                        }
                    }
                    catch (RuntimeException ex4) {
                        throw b(ex4);
                    }
                }
                try {
                    if (n == 0) {
                        sb.append(",");
                    }
                }
                catch (RuntimeException ex5) {
                    throw b(ex5);
                }
            }
            sb.append(ocDeclaratorSymbol.getName());
            n = 0;
        }
        String string;
        try {
            sb.append(")");
            string = sb.toString();
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCCallableUtil", "getBaseCallExpression"));
            }
        }
        catch (RuntimeException ex6) {
            throw b(ex6);
        }
        return string;
    }
    
    @NotNull
    public static String methodSignature(final OCMethodSymbol ocMethodSymbol, final PsiElement psiElement) {
        final StringBuilder sb = new StringBuilder();
        Label_0103: {
            Label_0070: {
                Label_0029: {
                    StringBuilder sb2;
                    try {
                        sb2 = sb;
                        if (ocMethodSymbol.isStatic()) {
                            final char c = '+';
                            break Label_0029;
                        }
                    }
                    catch (RuntimeException ex) {
                        throw b(ex);
                    }
                    final char c = '-';
                    try {
                        sb2.append(c);
                        sb.append('(');
                        if (!ocMethodSymbol.getReturnType().isVoid()) {
                            break Label_0070;
                        }
                        final OCMethodSymbol ocMethodSymbol2 = ocMethodSymbol;
                        final String s = "ibaction";
                        final boolean b = ocMethodSymbol2.hasAttribute(s);
                        if (b) {
                            break Label_0070;
                        }
                        break Label_0070;
                    }
                    catch (RuntimeException ex2) {
                        throw b(ex2);
                    }
                }
                try {
                    final OCMethodSymbol ocMethodSymbol2 = ocMethodSymbol;
                    final String s = "ibaction";
                    final boolean b = ocMethodSymbol2.hasAttribute(s);
                    if (b) {
                        sb.append("IBAction");
                        break Label_0103;
                    }
                }
                catch (RuntimeException ex3) {
                    throw b(ex3);
                }
            }
            sb.append(ocMethodSymbol.getReturnType().getBestNameInContext(psiElement, OCElementUtil.getReturnTypeText(ocMethodSymbol)));
        }
        sb.append(')');
        boolean b2 = true;
        for (final OCMethodSymbol.SelectorPartSymbol selectorPartSymbol : ocMethodSymbol.getSelectors()) {
            try {
                if (!b2) {
                    sb.append(' ');
                }
            }
            catch (RuntimeException ex4) {
                throw b(ex4);
            }
            sb.append(selectorPartSymbol.getSelectorName());
            final OCDeclaratorSymbol parameter = selectorPartSymbol.getParameter();
            Label_0266: {
                if (parameter != null) {
                    sb.append('(').append(parameter.getType().getBestNameInContext(psiElement, OCElementUtil.getTypeTextWithModifiers(parameter))).append(')');
                    final String a = a(ocMethodSymbol.getName(), b2);
                    try {
                        if (a != null) {
                            sb.append(a);
                            break Label_0266;
                        }
                    }
                    catch (RuntimeException ex5) {
                        throw b(ex5);
                    }
                    sb.append(OCNameSuggester.suggestUniqueName(parameter, psiElement));
                }
            }
            b2 = false;
        }
        try {
            if (ocMethodSymbol.isVararg()) {
                sb.append(",...");
            }
        }
        catch (RuntimeException ex6) {
            throw b(ex6);
        }
        String string;
        try {
            string = sb.toString();
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCCallableUtil", "methodSignature"));
            }
        }
        catch (RuntimeException ex7) {
            throw b(ex7);
        }
        return string;
    }
    
    @NotNull
    public static String functionSignature(@NotNull final OCFunctionSymbol p0, @NotNull final String p1, @NotNull final PsiElement p2) {
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
        //    18: ldc             "function"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/util/OCCallableUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "functionSignature"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
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
        //    62: ldc             "qualifier"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/util/OCCallableUtil"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "functionSignature"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    87: athrow         
        //    88: aload_2        
        //    89: ifnonnull       132
        //    92: new             Ljava/lang/IllegalArgumentException;
        //    95: dup            
        //    96: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    98: ldc             3
        //   100: anewarray       Ljava/lang/Object;
        //   103: dup            
        //   104: ldc             0
        //   106: ldc             "context"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/util/OCCallableUtil"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "functionSignature"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   131: athrow         
        //   132: new             Ljava/lang/StringBuilder;
        //   135: dup            
        //   136: invokespecial   java/lang/StringBuilder.<init>:()V
        //   139: astore_3       
        //   140: aload_0        
        //   141: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.locateFunctionDefinition:()Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //   144: astore          4
        //   146: aload           4
        //   148: ifnull          165
        //   151: aload           4
        //   153: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDeclaration.getTemplateParameterList:()Lcom/jetbrains/cidr/lang/psi/OCTemplateParameterList;
        //   158: goto            166
        //   161: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   164: athrow         
        //   165: aconst_null    
        //   166: astore          5
        //   168: aload           5
        //   170: ifnull          206
        //   173: aload_3        
        //   174: ldc             "template"
        //   176: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   179: pop            
        //   180: aload_3        
        //   181: aload           5
        //   183: invokeinterface com/jetbrains/cidr/lang/psi/OCTemplateParameterList.getTextWithMacros:()Ljava/lang/String;
        //   188: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   191: pop            
        //   192: aload_3        
        //   193: bipush          32
        //   195: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   198: pop            
        //   199: goto            206
        //   202: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   205: athrow         
        //   206: aload_0        
        //   207: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isConstexpr:()Z
        //   210: ifeq            227
        //   213: aload_3        
        //   214: ldc             "constexpr "
        //   216: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   219: pop            
        //   220: goto            227
        //   223: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   226: athrow         
        //   227: aload_0        
        //   228: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isVirtual:()Z
        //   231: ifeq            248
        //   234: aload_3        
        //   235: ldc             "virtual "
        //   237: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   240: pop            
        //   241: goto            248
        //   244: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   247: athrow         
        //   248: aload_0        
        //   249: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isFriend:()Z
        //   252: ifeq            269
        //   255: aload_3        
        //   256: ldc             "friend "
        //   258: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   261: pop            
        //   262: goto            269
        //   265: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   268: athrow         
        //   269: aload_0        
        //   270: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isStatic:()Z
        //   273: ifeq            290
        //   276: aload_3        
        //   277: ldc             "static "
        //   279: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   282: pop            
        //   283: goto            290
        //   286: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   289: athrow         
        //   290: aload_0        
        //   291: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isExplicit:()Z
        //   294: ifeq            311
        //   297: aload_3        
        //   298: ldc             "explicit "
        //   300: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   303: pop            
        //   304: goto            311
        //   307: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   310: athrow         
        //   311: aload_0        
        //   312: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isCppConstructor:()Z
        //   315: ifne            377
        //   318: aload_0        
        //   319: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isCppDestructor:()Z
        //   322: ifne            377
        //   325: goto            332
        //   328: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   331: athrow         
        //   332: aload_0        
        //   333: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isCppConversionOperator:()Z
        //   336: ifne            377
        //   339: goto            346
        //   342: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   345: athrow         
        //   346: aload_3        
        //   347: aload_0        
        //   348: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getEffectiveResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   351: aload_2        
        //   352: aload_0        
        //   353: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getReturnTypeTextWithModifiers:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;)Ljava/lang/String;
        //   356: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getBestNameInContext:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;)Ljava/lang/String;
        //   359: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   362: pop            
        //   363: aload_3        
        //   364: bipush          32
        //   366: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   369: pop            
        //   370: goto            377
        //   373: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   376: athrow         
        //   377: aload_3        
        //   378: aload_1        
        //   379: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   382: pop            
        //   383: aload_3        
        //   384: aload_0        
        //   385: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getName:()Ljava/lang/String;
        //   388: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   391: pop            
        //   392: aload_3        
        //   393: bipush          40
        //   395: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   398: pop            
        //   399: iconst_1       
        //   400: istore          6
        //   402: aload_0        
        //   403: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getParameterSymbols:()Ljava/util/List;
        //   406: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   411: astore          7
        //   413: aload           7
        //   415: invokeinterface java/util/Iterator.hasNext:()Z
        //   420: ifeq            484
        //   423: aload           7
        //   425: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   430: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   433: astore          8
        //   435: iload           6
        //   437: ifne            454
        //   440: aload_3        
        //   441: ldc             ", "
        //   443: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   446: pop            
        //   447: goto            454
        //   450: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   453: athrow         
        //   454: aload_3        
        //   455: aload           8
        //   457: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getName:()Ljava/lang/String;
        //   460: aload           8
        //   462: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   465: aload           8
        //   467: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getTypeTextWithModifiers:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;)Ljava/lang/String;
        //   470: aload_2        
        //   471: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.declarationText:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/types/OCType;Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   474: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   477: pop            
        //   478: iconst_0       
        //   479: istore          6
        //   481: goto            413
        //   484: aload_3        
        //   485: bipush          41
        //   487: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   490: pop            
        //   491: aload_0        
        //   492: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   495: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.getCVQualifiers:()Lcom/jetbrains/cidr/lang/types/CVQualifiers;
        //   498: aload_3        
        //   499: invokevirtual   com/jetbrains/cidr/lang/types/CVQualifiers.appendCVQualifiers:(Ljava/lang/StringBuilder;)V
        //   502: aload_0        
        //   503: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   506: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.isLValueRef:()Z
        //   509: ifeq            526
        //   512: aload_3        
        //   513: ldc             " &"
        //   515: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   518: pop            
        //   519: goto            550
        //   522: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   525: athrow         
        //   526: aload_0        
        //   527: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   530: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.isRValueRef:()Z
        //   533: ifeq            550
        //   536: aload_3        
        //   537: ldc             " &&"
        //   539: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   542: pop            
        //   543: goto            550
        //   546: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   549: athrow         
        //   550: aload_0        
        //   551: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isOverride:()Z
        //   554: ifeq            571
        //   557: aload_3        
        //   558: ldc             " override"
        //   560: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   563: pop            
        //   564: goto            571
        //   567: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   570: athrow         
        //   571: aload_0        
        //   572: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isFinal:()Z
        //   575: ifeq            592
        //   578: aload_3        
        //   579: ldc             " final"
        //   581: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   584: pop            
        //   585: goto            592
        //   588: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   591: athrow         
        //   592: aload_0        
        //   593: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isPureVirtual:()Z
        //   596: ifeq            613
        //   599: aload_3        
        //   600: ldc             " = 0"
        //   602: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   605: pop            
        //   606: goto            613
        //   609: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   612: athrow         
        //   613: aload_0        
        //   614: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isDefault:()Z
        //   617: ifeq            634
        //   620: aload_3        
        //   621: ldc             " = default"
        //   623: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   626: pop            
        //   627: goto            634
        //   630: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   633: athrow         
        //   634: aload_0        
        //   635: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isDelete:()Z
        //   638: ifeq            655
        //   641: aload_3        
        //   642: ldc             " = delete"
        //   644: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   647: pop            
        //   648: goto            655
        //   651: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   654: athrow         
        //   655: aload           4
        //   657: ifnull          718
        //   660: aload           4
        //   662: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDeclaration.getTrailingReturnTypeElement:()Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //   667: astore          7
        //   669: aload           7
        //   671: ifnull          718
        //   674: aload_3        
        //   675: ldc             " -> "
        //   677: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   680: aload           7
        //   682: invokeinterface com/jetbrains/cidr/lang/psi/OCTypeElement.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   687: aload_2        
        //   688: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   693: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   696: aload_2        
        //   697: aload           7
        //   699: invokeinterface com/jetbrains/cidr/lang/psi/OCTypeElement.getTextWithMacros:()Ljava/lang/String;
        //   704: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getBestNameInContext:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;)Ljava/lang/String;
        //   707: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   710: pop            
        //   711: goto            718
        //   714: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   717: athrow         
        //   718: aload_3        
        //   719: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   722: dup            
        //   723: ifnonnull       760
        //   726: new             Ljava/lang/IllegalStateException;
        //   729: dup            
        //   730: ldc             "@NotNull method %s.%s must not return null"
        //   732: ldc             2
        //   734: anewarray       Ljava/lang/Object;
        //   737: dup            
        //   738: ldc             0
        //   740: ldc             "com/jetbrains/cidr/lang/util/OCCallableUtil"
        //   742: aastore        
        //   743: dup            
        //   744: ldc             1
        //   746: ldc             "functionSignature"
        //   748: aastore        
        //   749: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   752: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   755: athrow         
        //   756: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   759: athrow         
        //   760: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  0      40     40     44     Ljava/lang/RuntimeException;
        //  44     84     84     88     Ljava/lang/RuntimeException;
        //  88     128    128    132    Ljava/lang/RuntimeException;
        //  146    161    161    165    Ljava/lang/RuntimeException;
        //  168    199    202    206    Ljava/lang/RuntimeException;
        //  206    220    223    227    Ljava/lang/RuntimeException;
        //  227    241    244    248    Ljava/lang/RuntimeException;
        //  248    262    265    269    Ljava/lang/RuntimeException;
        //  269    283    286    290    Ljava/lang/RuntimeException;
        //  290    304    307    311    Ljava/lang/RuntimeException;
        //  311    325    328    332    Ljava/lang/RuntimeException;
        //  318    339    342    346    Ljava/lang/RuntimeException;
        //  332    370    373    377    Ljava/lang/RuntimeException;
        //  435    447    450    454    Ljava/lang/RuntimeException;
        //  484    522    522    526    Ljava/lang/RuntimeException;
        //  526    543    546    550    Ljava/lang/RuntimeException;
        //  550    564    567    571    Ljava/lang/RuntimeException;
        //  571    585    588    592    Ljava/lang/RuntimeException;
        //  592    606    609    613    Ljava/lang/RuntimeException;
        //  613    627    630    634    Ljava/lang/RuntimeException;
        //  634    648    651    655    Ljava/lang/RuntimeException;
        //  669    711    714    718    Ljava/lang/RuntimeException;
        //  718    756    756    760    Ljava/lang/RuntimeException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0332:
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
    
    @NotNull
    public static String getFunctionParentQualifier(@NotNull final OCFunctionSymbol p0, @NotNull final PsiElement p1) {
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
        //    18: ldc             "function"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/util/OCCallableUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getFunctionParentQualifier"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
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
        //    62: ldc             "context"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/util/OCCallableUtil"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "getFunctionParentQualifier"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    87: athrow         
        //    88: ldc             ""
        //    90: astore_2       
        //    91: aload_0        
        //    92: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //    95: astore_3       
        //    96: aload_3        
        //    97: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol;
        //   100: ifeq            362
        //   103: iconst_0       
        //   104: istore          4
        //   106: aload_1        
        //   107: iconst_2       
        //   108: anewarray       Ljava/lang/Class;
        //   111: dup            
        //   112: iconst_0       
        //   113: ldc             Lcom/jetbrains/cidr/lang/psi/OCStruct;.class
        //   115: aastore        
        //   116: dup            
        //   117: iconst_1       
        //   118: ldc             Lcom/jetbrains/cidr/lang/psi/OCCppNamespace;.class
        //   120: aastore        
        //   121: invokestatic    com/intellij/psi/util/PsiTreeUtil.getNonStrictParentOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   124: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSymbolDeclarator;
        //   127: astore          5
        //   129: aload           5
        //   131: ifnull          236
        //   134: aload           5
        //   136: invokeinterface com/jetbrains/cidr/lang/psi/OCSymbolDeclarator.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   141: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol;
        //   144: astore          6
        //   146: aload           6
        //   148: ifnull          209
        //   151: aload_3        
        //   152: aload           6
        //   154: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.isSameSymbol:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //   157: ifne            206
        //   160: goto            167
        //   163: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   166: athrow         
        //   167: aload           6
        //   169: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   172: ifeq            209
        //   175: goto            182
        //   178: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   181: athrow         
        //   182: aload           6
        //   184: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   187: aload_3        
        //   188: invokedynamic   process:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;)Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol$BaseClassProcessor;
        //   193: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.processAllBaseClasses:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol$BaseClassProcessor;)Z
        //   196: ifne            209
        //   199: goto            206
        //   202: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   205: athrow         
        //   206: iconst_1       
        //   207: istore          4
        //   209: aload           5
        //   211: iconst_2       
        //   212: anewarray       Ljava/lang/Class;
        //   215: dup            
        //   216: iconst_0       
        //   217: ldc             Lcom/jetbrains/cidr/lang/psi/OCStruct;.class
        //   219: aastore        
        //   220: dup            
        //   221: iconst_1       
        //   222: ldc             Lcom/jetbrains/cidr/lang/psi/OCCppNamespace;.class
        //   224: aastore        
        //   225: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   228: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSymbolDeclarator;
        //   231: astore          5
        //   233: goto            129
        //   236: iload           4
        //   238: ifne            362
        //   241: aload_3        
        //   242: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   245: ifeq            333
        //   248: goto            255
        //   251: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   254: athrow         
        //   255: aload_3        
        //   256: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   259: aload_1        
        //   260: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getBestNameInContext:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   263: astore_2       
        //   264: aload_0        
        //   265: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isFriend:()Z
        //   268: ifeq            362
        //   271: aload_3        
        //   272: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getParent:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   275: astore          6
        //   277: aload           6
        //   279: ifnull          310
        //   282: aload           6
        //   284: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   287: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.NAMESPACE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   290: if_acmpeq       310
        //   293: goto            300
        //   296: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   299: athrow         
        //   300: aload           6
        //   302: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getParent:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   305: astore          6
        //   307: goto            277
        //   310: aload           6
        //   312: ifnull          327
        //   315: aload           6
        //   317: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getResolvedQualifiedName:()Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   320: invokevirtual   com/jetbrains/cidr/lang/symbols/OCQualifiedName.getNameWithParent:()Ljava/lang/String;
        //   323: astore_2       
        //   324: goto            330
        //   327: ldc             ""
        //   329: astore_2       
        //   330: goto            362
        //   333: aload_3        
        //   334: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getResolvedQualifiedName:()Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   337: astore          6
        //   339: aload           6
        //   341: ifnull          357
        //   344: aload           6
        //   346: iconst_1       
        //   347: invokevirtual   com/jetbrains/cidr/lang/symbols/OCQualifiedName.getCanonicalName:(Z)Ljava/lang/String;
        //   350: goto            361
        //   353: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   356: athrow         
        //   357: aload_3        
        //   358: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getName:()Ljava/lang/String;
        //   361: astore_2       
        //   362: aload_2        
        //   363: invokevirtual   java/lang/String.isEmpty:()Z
        //   366: ifeq            377
        //   369: aload_2        
        //   370: goto            396
        //   373: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   376: athrow         
        //   377: new             Ljava/lang/StringBuilder;
        //   380: dup            
        //   381: invokespecial   java/lang/StringBuilder.<init>:()V
        //   384: aload_2        
        //   385: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   388: ldc             "::"
        //   390: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   393: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   396: dup            
        //   397: ifnonnull       434
        //   400: new             Ljava/lang/IllegalStateException;
        //   403: dup            
        //   404: ldc             "@NotNull method %s.%s must not return null"
        //   406: ldc             2
        //   408: anewarray       Ljava/lang/Object;
        //   411: dup            
        //   412: ldc             0
        //   414: ldc             "com/jetbrains/cidr/lang/util/OCCallableUtil"
        //   416: aastore        
        //   417: dup            
        //   418: ldc             1
        //   420: ldc             "getFunctionParentQualifier"
        //   422: aastore        
        //   423: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   426: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   429: athrow         
        //   430: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   433: athrow         
        //   434: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  0      40     40     44     Ljava/lang/RuntimeException;
        //  44     84     84     88     Ljava/lang/RuntimeException;
        //  146    160    163    167    Ljava/lang/RuntimeException;
        //  151    175    178    182    Ljava/lang/RuntimeException;
        //  167    199    202    206    Ljava/lang/RuntimeException;
        //  236    248    251    255    Ljava/lang/RuntimeException;
        //  277    293    296    300    Ljava/lang/RuntimeException;
        //  339    353    353    357    Ljava/lang/RuntimeException;
        //  362    373    373    377    Ljava/lang/RuntimeException;
        //  396    430    430    434    Ljava/lang/RuntimeException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0167:
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
    
    @NotNull
    public static String defaultFunctionBody(@NotNull final OCFunctionSymbol ocFunctionSymbol, @Nullable final OCFunctionSymbol ocFunctionSymbol2, @NotNull final PsiElement psiElement) {
        try {
            if (ocFunctionSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "function", "com/jetbrains/cidr/lang/util/OCCallableUtil", "defaultFunctionBody"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/util/OCCallableUtil", "defaultFunctionBody"));
            }
        }
        catch (RuntimeException ex2) {
            throw b(ex2);
        }
        final StringBuilder sb = new StringBuilder();
        String string = null;
        Label_0138: {
            Label_0114: {
                try {
                    if (!ocFunctionSymbol.isCppConstructor()) {
                        break Label_0138;
                    }
                    final OCFunctionSymbol ocFunctionSymbol3 = ocFunctionSymbol2;
                    if (ocFunctionSymbol3 != null) {
                        break Label_0114;
                    }
                    break Label_0138;
                }
                catch (RuntimeException ex3) {
                    throw b(ex3);
                }
                try {
                    final OCFunctionSymbol ocFunctionSymbol3 = ocFunctionSymbol2;
                    if (ocFunctionSymbol3 != null) {
                        sb.append(':');
                        sb.append(a(ocFunctionSymbol, ocFunctionSymbol2));
                    }
                }
                catch (RuntimeException ex4) {
                    throw b(ex4);
                }
            }
            try {
                sb.append("{\n");
                sb.append(a(ocFunctionSymbol, ocFunctionSymbol2, psiElement));
                sb.append("\n}");
                string = sb.toString();
                if (string == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCCallableUtil", "defaultFunctionBody"));
                }
            }
            catch (RuntimeException ex5) {
                throw b(ex5);
            }
        }
        return string;
    }
    
    public static String getOverridingFunctionName(final OCFunctionSymbol ocFunctionSymbol, final OCSymbolWithQualifiedName ocSymbolWithQualifiedName) {
        Label_0026: {
            try {
                if (ocSymbolWithQualifiedName == null || !ocFunctionSymbol.isCppConstructor()) {
                    break Label_0026;
                }
            }
            catch (RuntimeException ex) {
                throw b(ex);
            }
            return ocSymbolWithQualifiedName.getName();
            try {
                if (ocSymbolWithQualifiedName == null || !ocFunctionSymbol.isCppDestructor()) {
                    return ocFunctionSymbol.getName();
                }
            }
            catch (RuntimeException ex2) {
                throw b(ex2);
            }
        }
        return "~" + ocSymbolWithQualifiedName.getName();
        s = ocFunctionSymbol.getName();
        return s;
    }
    
    @NotNull
    public static OCFunctionSymbol createOverridingFunction(@NotNull final OCFunctionSymbol ocFunctionSymbol, @NotNull final OCNamespaceSymbol ocNamespaceSymbol, @NotNull final PsiElement psiElement, @Nullable final OCVisibility ocVisibility, final boolean b, final boolean b2) {
        try {
            if (ocFunctionSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "base", "com/jetbrains/cidr/lang/util/OCCallableUtil", "createOverridingFunction"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        try {
            if (ocNamespaceSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "newParent", "com/jetbrains/cidr/lang/util/OCCallableUtil", "createOverridingFunction"));
            }
        }
        catch (RuntimeException ex2) {
            throw b(ex2);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/util/OCCallableUtil", "createOverridingFunction"));
            }
        }
        catch (RuntimeException ex3) {
            throw b(ex3);
        }
        final int functionAttributes = ocFunctionSymbol.getFunctionAttributes();
        final int n = ocFunctionSymbol.getFunctionProperties() & ~OCFunctionSymbol.Property.IS_PURE_VIRTUAL.getMask();
        int n3 = 0;
        Label_0249: {
            int n2 = 0;
            Label_0236: {
                Label_0203: {
                    Label_0190: {
                        try {
                            if (!b2 || ocFunctionSymbol.isCppConstructor()) {
                                break Label_0190;
                            }
                        }
                        catch (RuntimeException ex4) {
                            throw b(ex4);
                        }
                        n2 = (functionAttributes | OCSymbolAttribute.VIRTUAL.getMask());
                        break Label_0203;
                    }
                    n2 = (functionAttributes & ~OCSymbolAttribute.VIRTUAL.getMask());
                    try {
                        if (!b || ocFunctionSymbol.isCppConstructor()) {
                            break Label_0236;
                        }
                    }
                    catch (RuntimeException ex5) {
                        throw b(ex5);
                    }
                }
                n3 = (n2 | OCSymbolAttribute.OVERRIDE.getMask());
                break Label_0249;
            }
            n3 = (n2 & ~OCSymbolAttribute.OVERRIDE.getMask());
        }
        final List<OCDeclaratorSymbol> parameterSymbols = ocFunctionSymbol.getParameterSymbols();
        final ArrayList list = new ArrayList<OCDeclaratorSymbol>(parameterSymbols.size());
        final ArrayList<String> list2 = new ArrayList<String>();
        for (final OCDeclaratorSymbol ocDeclaratorSymbol : parameterSymbols) {
            final OCType type = ocDeclaratorSymbol.getType();
            String name = null;
            Label_0412: {
                Label_0405: {
                    Label_0341: {
                        try {
                            if (!ocDeclaratorSymbol.isUnnamed()) {
                                break Label_0405;
                            }
                            final OCType ocType = type;
                            final boolean b3 = ocType instanceof OCEllipsisType;
                            if (!b3) {
                                break Label_0341;
                            }
                            break Label_0405;
                        }
                        catch (RuntimeException ex6) {
                            throw b(ex6);
                        }
                        try {
                            final OCType ocType = type;
                            final boolean b3 = ocType instanceof OCEllipsisType;
                            if (b3) {
                                break Label_0405;
                            }
                            if (type instanceof OCVoidType) {
                                break Label_0405;
                            }
                        }
                        catch (RuntimeException ex7) {
                            throw b(ex7);
                        }
                    }
                    final Collection<String> suggestForType = OCNameSuggester.suggestForType(type, psiElement, list2);
                    String s = null;
                    Label_0400: {
                        try {
                            if (suggestForType.isEmpty()) {
                                s = "param";
                                break Label_0400;
                            }
                        }
                        catch (RuntimeException ex8) {
                            throw b(ex8);
                        }
                        s = suggestForType.iterator().next();
                    }
                    name = s;
                    break Label_0412;
                }
                name = ocDeclaratorSymbol.getName();
            }
            list2.add(name);
            list.add(new OCDeclaratorSymbol(ocDeclaratorSymbol, ocDeclaratorSymbol.getSubstitution(), OCQualifiedName.with(ocDeclaratorSymbol.getQualifier(), name), null, new OCResolveContext(psiElement)));
        }
        OCFunctionSymbol ocFunctionSymbol2;
        try {
            ocFunctionSymbol2 = new OCFunctionSymbol(ocFunctionSymbol.getProject(), null, 4294967296L, ocNamespaceSymbol, OCQualifiedName.with(null, getOverridingFunctionName(ocFunctionSymbol, ocNamespaceSymbol)), ocFunctionSymbol.getTemplateParameters(), ocFunctionSymbol.getTemplateSpecialization(), n, n3, ocFunctionSymbol.getAttributes(), ocFunctionSymbol.getType(), (List<OCDeclaratorSymbol>)list, ocFunctionSymbol.getKind(), ocVisibility);
            if (ocFunctionSymbol2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCCallableUtil", "createOverridingFunction"));
            }
        }
        catch (RuntimeException ex9) {
            throw b(ex9);
        }
        return ocFunctionSymbol2;
    }
    
    @NotNull
    public static OCFunctionSymbol removeDeclarationSpecifiers(@NotNull final OCFunctionSymbol ocFunctionSymbol) {
        try {
            if (ocFunctionSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "base", "com/jetbrains/cidr/lang/util/OCCallableUtil", "removeDeclarationSpecifiers"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        final int n = ocFunctionSymbol.getFunctionAttributes() & ~(OCSymbolAttribute.VIRTUAL.getMask() | OCSymbolAttribute.FRIEND.getMask() | OCSymbolAttribute.STATIC.getMask() | OCSymbolAttribute.EXPLICIT.getMask() | OCSymbolAttribute.FINAL.getMask() | OCSymbolAttribute.OVERRIDE.getMask() | OCSymbolAttribute.DEFAULT.getMask() | OCSymbolAttribute.DELETE.getMask());
        final int n2 = ocFunctionSymbol.getFunctionProperties() & ~OCFunctionSymbol.Property.IS_PURE_VIRTUAL.getMask();
        OCFunctionSymbol ocFunctionSymbol2;
        try {
            ocFunctionSymbol2 = new OCFunctionSymbol(ocFunctionSymbol.getProject(), ocFunctionSymbol.getContainingFile(), ocFunctionSymbol.getOffset(), ocFunctionSymbol.getParent(), ocFunctionSymbol.getQualifiedName(), ocFunctionSymbol.getTemplateParameters(), ocFunctionSymbol.getTemplateSpecialization(), n2, n, ocFunctionSymbol.getAttributes(), ocFunctionSymbol.getType(), ocFunctionSymbol.getParameterSymbols(), ocFunctionSymbol.getKind(), ocFunctionSymbol.getVisibility());
            if (ocFunctionSymbol2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCCallableUtil", "removeDeclarationSpecifiers"));
            }
        }
        catch (RuntimeException ex2) {
            throw b(ex2);
        }
        return ocFunctionSymbol2;
    }
    
    @Nullable
    public static OCFunctionSymbol getDefaultBaseToCall(final OCFunctionSymbol p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isCppDestructor:()Z
        //     4: ifne            38
        //     7: aload_0        
        //     8: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isCppConstructor:()Z
        //    11: ifeq            46
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    20: athrow         
        //    21: aload_0        
        //    22: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //    25: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.hasNoParameters:()Z
        //    28: ifeq            46
        //    31: goto            38
        //    34: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    37: athrow         
        //    38: aconst_null    
        //    39: goto            75
        //    42: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    45: athrow         
        //    46: new             Lcom/intellij/util/FilteredQuery;
        //    49: dup            
        //    50: new             Lcom/jetbrains/cidr/lang/search/OCFunctionAncestorsQuery;
        //    53: dup            
        //    54: aload_0        
        //    55: iconst_0       
        //    56: iconst_0       
        //    57: iconst_1       
        //    58: invokespecial   com/jetbrains/cidr/lang/search/OCFunctionAncestorsQuery.<init>:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;ZZZ)V
        //    61: invokedynamic   value:()Lcom/intellij/openapi/util/Condition;
        //    66: invokespecial   com/intellij/util/FilteredQuery.<init>:(Lcom/intellij/util/Query;Lcom/intellij/openapi/util/Condition;)V
        //    69: invokevirtual   com/intellij/util/FilteredQuery.findFirst:()Ljava/lang/Object;
        //    72: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //    75: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  0      14     17     21     Ljava/lang/RuntimeException;
        //  7      31     34     38     Ljava/lang/RuntimeException;
        //  21     42     42     46     Ljava/lang/RuntimeException;
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
    
    @NotNull
    public static PsiElement getCorrectContextToCalculateNames(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "container", "com/jetbrains/cidr/lang/util/OCCallableUtil", "getCorrectContextToCalculateNames"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        Label_0142: {
            try {
                if (!(psiElement instanceof OCCppNamespace)) {
                    if (!(psiElement instanceof OCStructLike)) {
                        break Label_0142;
                    }
                }
            }
            catch (RuntimeException ex2) {
                throw b(ex2);
            }
            final ASTNode childByType = psiElement.getNode().findChildByType((IElementType)OCTokenTypes.LBRACE);
            if (childByType != null) {
                final PsiElement psi = childByType.getPsi();
                PsiElement psiElement2 = null;
                Label_0107: {
                    try {
                        if (psi == null) {
                            break Label_0142;
                        }
                        psiElement2 = psi;
                        if (psiElement2 == null) {
                            break Label_0107;
                        }
                        return psiElement2;
                    }
                    catch (RuntimeException ex3) {
                        throw b(ex3);
                    }
                    try {
                        psiElement2 = psi;
                        if (psiElement2 == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCCallableUtil", "getCorrectContextToCalculateNames"));
                        }
                    }
                    catch (RuntimeException ex4) {
                        throw b(ex4);
                    }
                }
                return psiElement2;
            }
            try {
                if (psiElement == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCCallableUtil", "getCorrectContextToCalculateNames"));
                }
            }
            catch (RuntimeException ex5) {
                throw b(ex5);
            }
        }
        return psiElement;
    }
    
    public static boolean isParameterNameEmpty(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "paramName", "com/jetbrains/cidr/lang/util/OCCallableUtil", "isParameterNameEmpty"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        Label_0067: {
            try {
                if (s.isEmpty()) {
                    break Label_0067;
                }
                final String s2 = s;
                final String s3 = "<unnamed>";
                final boolean b = s2.equals(s3);
                if (b) {
                    break Label_0067;
                }
                return false;
            }
            catch (RuntimeException ex2) {
                throw b(ex2);
            }
            try {
                final String s2 = s;
                final String s3 = "<unnamed>";
                final boolean b = s2.equals(s3);
                if (b) {
                    return true;
                }
            }
            catch (RuntimeException ex3) {
                throw b(ex3);
            }
        }
        return false;
    }
    
    public static boolean resolveIsVirtual(@NotNull final OCFunctionSymbol p0) {
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
        //    18: ldc             "symbol"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/util/OCCallableUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "resolveIsVirtual"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    43: athrow         
        //    44: aload_0        
        //    45: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isVirtual:()Z
        //    48: ifne            94
        //    51: aload_0        
        //    52: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isOverride:()Z
        //    55: ifne            94
        //    58: goto            65
        //    61: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    64: athrow         
        //    65: aload_0        
        //    66: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isFinal:()Z
        //    69: ifne            94
        //    72: goto            79
        //    75: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    78: athrow         
        //    79: aload_0        
        //    80: iconst_0       
        //    81: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionAncestorsQuery.findFirstVirtual:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;Z)Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //    84: ifnull          102
        //    87: goto            94
        //    90: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    93: athrow         
        //    94: iconst_1       
        //    95: goto            103
        //    98: invokestatic    com/jetbrains/cidr/lang/util/OCCallableUtil.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   101: athrow         
        //   102: iconst_0       
        //   103: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  0      40     40     44     Ljava/lang/RuntimeException;
        //  44     58     61     65     Ljava/lang/RuntimeException;
        //  51     72     75     79     Ljava/lang/RuntimeException;
        //  65     87     90     94     Ljava/lang/RuntimeException;
        //  79     98     98     102    Ljava/lang/RuntimeException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0065:
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
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
