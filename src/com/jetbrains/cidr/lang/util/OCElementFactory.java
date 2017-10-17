// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import com.intellij.psi.codeStyle.CodeStyleManager;
import com.jetbrains.cidr.lang.psi.impl.OCCodeFragmentImpl;
import com.intellij.psi.impl.source.codeStyle.CodeEditUtil;
import com.intellij.psi.impl.source.codeStyle.IndentHelper;
import com.intellij.psi.impl.source.tree.TreeElement;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.intellij.psi.tree.IFileElementType;
import com.jetbrains.cidr.lang.psi.OCCodeFragment;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import com.intellij.util.StringBuilderSpinAllocator;
import com.jetbrains.cidr.lang.psi.OCStatement;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.psi.OCTypeElement;
import com.jetbrains.cidr.lang.psi.OCMacroCall;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiWhiteSpace;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.psi.OCCppNamespaceQualifier;
import com.jetbrains.cidr.lang.preprocessor.OCMacroForeignLeafElement;
import com.jetbrains.cidr.lang.types.ARCAttribute;
import java.io.Serializable;
import com.jetbrains.cidr.lang.types.visitors.OCTypeNameVisitor;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import com.jetbrains.cidr.lang.types.OCBlockPointerType;
import com.jetbrains.cidr.lang.types.OCIdType;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.types.OCArrayType;
import com.jetbrains.cidr.lang.psi.OCSynthesizeProperty;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.psi.OCSynthesizePropertiesList;
import com.jetbrains.cidr.lang.psi.OCPropertyAttributesList;
import com.jetbrains.cidr.lang.psi.OCConstructorFieldInitializer;
import com.jetbrains.cidr.lang.psi.OCConstructorInitializationList;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;
import com.jetbrains.cidr.lang.psi.OCStruct;
import com.jetbrains.cidr.lang.psi.OCInstanceVariablesList;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCBinaryExpression;
import com.jetbrains.cidr.lang.psi.impl.OCUDLiteralExpressionImpl;
import com.jetbrains.cidr.lang.psi.OCUnaryExpression;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.jetbrains.cidr.lang.psi.OCDefineDirective;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.psi.OCInterface;
import com.jetbrains.cidr.lang.psi.OCCallExpression;
import java.util.Iterator;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.jetbrains.cidr.lang.types.OCIntType;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.psi.OCEnum;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.psi.OCProperty;
import com.jetbrains.cidr.lang.psi.OCFunctionDefinition;
import com.jetbrains.cidr.lang.psi.OCParameterDeclaration;
import java.util.List;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import java.util.Collections;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCDeclarationStatement;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.openapi.diagnostic.Logger;

public class OCElementFactory
{
    private static final Logger LOG;
    
    @NotNull
    public static OCDeclarationStatement declarationStatement(final String s, final OCType ocType, @Nullable final OCExpression ocExpression, final PsiElement psiElement) {
        OCDeclarationStatement declarationStatement;
        try {
            declarationStatement = declarationStatement(null, s, ocType, ocExpression, psiElement);
            if (declarationStatement == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "declarationStatement"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return declarationStatement;
    }
    
    @NotNull
    public static OCDeclaration declaration(final String s, final OCType ocType, @Nullable final OCExpression ocExpression, final PsiElement psiElement) {
        OCDeclaration declaration;
        try {
            declaration = declarationStatement(null, s, ocType, ocExpression, psiElement).getDeclaration();
            if (declaration == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "declaration"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return declaration;
    }
    
    @NotNull
    public static OCDeclaration declaration(@Nullable final String s, final String s2, final OCType ocType, final PsiElement psiElement) {
        OCDeclaration declaration;
        try {
            declaration = declarationStatement(s, s2, ocType, null, psiElement).getDeclaration();
            if (declaration == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "declaration"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return declaration;
    }
    
    @NotNull
    public static OCDeclarationStatement declarationStatement(@Nullable final String s, final String s2, final OCType ocType, @Nullable final OCExpression ocExpression, final PsiElement psiElement) {
        List<String> list = null;
        String s3 = null;
        Label_0034: {
            Label_0018: {
                try {
                    if (s != null) {
                        list = Collections.singletonList(s);
                        break Label_0018;
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                list = Collections.emptyList();
                try {
                    if (ocExpression != null) {
                        s3 = "0";
                        break Label_0034;
                    }
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
            }
            s3 = null;
        }
        final OCDeclarationStatement ocDeclarationStatement = (OCDeclarationStatement)statementFromText(declarationText(list, s2, ocType, s3, psiElement, null, false), psiElement, true);
        try {
            if (ocExpression != null) {
                OCChangeUtil.replaceHandlingMacros((PsiElement)ocDeclarationStatement.getDeclaration().getDeclarators().get(0).getInitializer(), (PsiElement)ocExpression);
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        OCDeclarationStatement ocDeclarationStatement2;
        try {
            ocDeclarationStatement2 = ocDeclarationStatement;
            if (ocDeclarationStatement2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "declarationStatement"));
            }
        }
        catch (IllegalStateException ex4) {
            throw a(ex4);
        }
        return ocDeclarationStatement2;
    }
    
    @NotNull
    public static OCDeclaration declarationFromText(final String s, final PsiElement psiElement) {
        OCDeclaration ocDeclaration;
        try {
            ocDeclaration = (OCDeclaration)topLevelDeclarationFromText(s + ";", psiElement);
            if (ocDeclaration == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "declarationFromText"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return ocDeclaration;
    }
    
    @NotNull
    public static OCDeclaration declarationFromText(final String s, final PsiElement psiElement, final boolean b) {
        OCDeclaration ocDeclaration;
        try {
            ocDeclaration = (OCDeclaration)topLevelDeclarationFromText(s + ";", psiElement, b);
            if (ocDeclaration == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "declarationFromText"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return ocDeclaration;
    }
    
    @NotNull
    public static String declarationText(final String s, final OCType ocType, @NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/util/OCElementFactory", "declarationText"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        String declarationText;
        try {
            declarationText = declarationText(Collections.emptyList(), s, ocType, null, psiElement, null, false);
            if (declarationText == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "declarationText"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return declarationText;
    }
    
    @NotNull
    public static String declarationText(final String s, final OCType ocType, final String s2, @NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/util/OCElementFactory", "declarationText"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        String declarationText;
        try {
            declarationText = declarationText(Collections.emptyList(), s, ocType, null, psiElement, s2, false);
            if (declarationText == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "declarationText"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return declarationText;
    }
    
    @NotNull
    public static OCDeclaration declarationByNameAndType(final String s, final OCType ocType, final PsiElement psiElement) {
        OCDeclaration declaration;
        try {
            declaration = declarationStatement(s, ocType, null, psiElement).getDeclaration();
            if (declaration == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "declarationByNameAndType"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return declaration;
    }
    
    @NotNull
    public static OCParameterDeclaration paramDeclarationFromText(final String s, @NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/util/OCElementFactory", "paramDeclarationFromText"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        OCParameterDeclaration ocParameterDeclaration;
        try {
            ocParameterDeclaration = ((OCFunctionDefinition)topLevelDeclarationFromText("void f(" + s + "){}", psiElement)).getParameterList().getParameterDeclarations().get(0);
            if (ocParameterDeclaration == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "paramDeclarationFromText"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return ocParameterDeclaration;
    }
    
    @NotNull
    public static OCParameterDeclaration paramDeclarationByNameAndType(final String s, final OCType ocType, final PsiElement psiElement) {
        OCParameterDeclaration paramDeclarationFromText;
        try {
            paramDeclarationFromText = paramDeclarationFromText(declarationText(s, ocType, psiElement), psiElement);
            if (paramDeclarationFromText == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "paramDeclarationByNameAndType"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return paramDeclarationFromText;
    }
    
    @NotNull
    public static OCProperty propertyDeclaration(final String s, final OCType ocType, final PsiElement psiElement) {
        OCProperty propertyDeclaration;
        try {
            propertyDeclaration = propertyDeclaration(s, ocType, psiElement, null, false);
            if (propertyDeclaration == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "propertyDeclaration"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return propertyDeclaration;
    }
    
    @NotNull
    public static OCProperty propertyDeclaration(final String s, final OCType ocType, final PsiElement psiElement, @Nullable final OCPropertySymbol.PropertySemantics propertySemantics, final boolean b) {
        OCProperty propertyDeclaration;
        try {
            propertyDeclaration = propertyDeclaration(s, ocType, psiElement, propertySemantics, b, null);
            if (propertyDeclaration == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "propertyDeclaration"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return propertyDeclaration;
    }
    
    @NotNull
    public static OCProperty propertyDeclaration(final String s, final OCType ocType, final PsiElement psiElement, @Nullable final OCPropertySymbol.PropertySemantics propertySemantics, final boolean b, @Nullable final String s2) {
        final OCCodeStyleSettings ocCodeStyleSettings = (OCCodeStyleSettings)CodeStyleSettingsManager.getSettings(psiElement.getProject()).getCustomSettings((Class)OCCodeStyleSettings.class);
        OCProperty propertyDeclaration = null;
        Label_0052: {
            Label_0043: {
                try {
                    if (ocCodeStyleSettings == null) {
                        break Label_0043;
                    }
                    final OCCodeStyleSettings ocCodeStyleSettings2 = ocCodeStyleSettings;
                    final boolean b2 = ocCodeStyleSettings2.PROPERTY_NONATOMIC;
                    if (b2) {
                        break Label_0043;
                    }
                    break Label_0043;
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                try {
                    final OCCodeStyleSettings ocCodeStyleSettings2 = ocCodeStyleSettings;
                    final boolean b2 = ocCodeStyleSettings2.PROPERTY_NONATOMIC;
                    if (b2) {
                        final boolean b3 = true;
                        break Label_0052;
                    }
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
            }
            final boolean b3 = false;
            try {
                propertyDeclaration = propertyDeclaration(s, ocType, psiElement, propertySemantics, b3, b, s2);
                if (propertyDeclaration == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "propertyDeclaration"));
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
        }
        return propertyDeclaration;
    }
    
    @NotNull
    public static OCProperty propertyDeclaration(final String p0, final OCType p1, final PsiElement p2, @Nullable final OCPropertySymbol.PropertySemantics p3, final boolean p4, final boolean p5, @Nullable final String p6) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Ljava/util/ArrayList;
        //     3: dup            
        //     4: invokespecial   java/util/ArrayList.<init>:()V
        //     7: astore          7
        //     9: iload           4
        //    11: ifeq            31
        //    14: aload           7
        //    16: ldc             "nonatomic"
        //    18: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //    23: pop            
        //    24: goto            31
        //    27: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    30: athrow         
        //    31: iload           5
        //    33: ifeq            53
        //    36: aload           7
        //    38: ldc             "readonly"
        //    40: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //    45: pop            
        //    46: goto            53
        //    49: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    52: athrow         
        //    53: aload_3        
        //    54: ifnonnull       77
        //    57: aload_1        
        //    58: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObjectCompatible:()Z
        //    61: ifeq            77
        //    64: goto            71
        //    67: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    70: athrow         
        //    71: aload_1        
        //    72: aload_2        
        //    73: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbolImpl.getDefaultSemanticsForType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertySemantics;
        //    76: astore_3       
        //    77: aload_3        
        //    78: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertySemantics.ASSIGN:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertySemantics;
        //    81: if_acmpne       101
        //    84: aload           7
        //    86: ldc             "assign"
        //    88: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //    93: pop            
        //    94: goto            223
        //    97: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   100: athrow         
        //   101: aload_3        
        //   102: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertySemantics.WEAK:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertySemantics;
        //   105: if_acmpne       125
        //   108: aload           7
        //   110: ldc             "weak"
        //   112: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   117: pop            
        //   118: goto            223
        //   121: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   124: athrow         
        //   125: aload_1        
        //   126: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToObjectCompatible:()Z
        //   129: ifeq            223
        //   132: iload           5
        //   134: ifne            223
        //   137: goto            144
        //   140: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   143: athrow         
        //   144: aload_3        
        //   145: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertySemantics.STRONG:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertySemantics;
        //   148: if_acmpne       175
        //   151: goto            158
        //   154: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   157: athrow         
        //   158: aload           7
        //   160: ldc             "strong"
        //   162: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   167: pop            
        //   168: goto            223
        //   171: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   174: athrow         
        //   175: aload_3        
        //   176: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertySemantics.RETAIN:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertySemantics;
        //   179: if_acmpne       199
        //   182: aload           7
        //   184: ldc             "retain"
        //   186: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   191: pop            
        //   192: goto            223
        //   195: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   198: athrow         
        //   199: aload_3        
        //   200: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertySemantics.COPY:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertySemantics;
        //   203: if_acmpne       223
        //   206: aload           7
        //   208: ldc             "copy"
        //   210: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   215: pop            
        //   216: goto            223
        //   219: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   222: athrow         
        //   223: new             Ljava/lang/StringBuilder;
        //   226: dup            
        //   227: invokespecial   java/lang/StringBuilder.<init>:()V
        //   230: astore          8
        //   232: aload           7
        //   234: invokeinterface java/util/List.isEmpty:()Z
        //   239: ifne            278
        //   242: aload           8
        //   244: bipush          40
        //   246: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   249: pop            
        //   250: aload           8
        //   252: aload           7
        //   254: ldc             ","
        //   256: invokestatic    com/intellij/openapi/util/text/StringUtil.join:(Ljava/util/Collection;Ljava/lang/String;)Ljava/lang/String;
        //   259: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   262: pop            
        //   263: aload           8
        //   265: bipush          41
        //   267: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   270: pop            
        //   271: goto            278
        //   274: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   277: athrow         
        //   278: aload           6
        //   280: invokestatic    com/intellij/openapi/util/text/StringUtil.isEmpty:(Ljava/lang/String;)Z
        //   283: ifne            332
        //   286: aload           8
        //   288: invokevirtual   java/lang/StringBuilder.length:()I
        //   291: ifle            316
        //   294: goto            301
        //   297: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   300: athrow         
        //   301: aload           8
        //   303: bipush          32
        //   305: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   308: pop            
        //   309: goto            316
        //   312: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   315: athrow         
        //   316: aload           8
        //   318: aload           6
        //   320: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   323: pop            
        //   324: aload           8
        //   326: bipush          32
        //   328: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   331: pop            
        //   332: new             Ljava/lang/StringBuilder;
        //   335: dup            
        //   336: invokespecial   java/lang/StringBuilder.<init>:()V
        //   339: ldc             "@interface c @property "
        //   341: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   344: aload           8
        //   346: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   349: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   352: aload_0        
        //   353: aload_1        
        //   354: aconst_null    
        //   355: aload_2        
        //   356: aconst_null    
        //   357: iconst_1       
        //   358: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.declarationText:(Ljava/util/List;Ljava/lang/String;Lcom/jetbrains/cidr/lang/types/OCType;Ljava/lang/String;Lcom/intellij/psi/PsiElement;Ljava/lang/String;Z)Ljava/lang/String;
        //   361: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   364: ldc             "; @end"
        //   366: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   369: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   372: astore          9
        //   374: aload           9
        //   376: aload_2        
        //   377: iconst_1       
        //   378: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.topLevelDeclarationFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;Z)Lcom/intellij/psi/PsiElement;
        //   381: checkcast       Lcom/jetbrains/cidr/lang/psi/OCInterface;
        //   384: invokeinterface com/jetbrains/cidr/lang/psi/OCInterface.getProperties:()Ljava/util/List;
        //   389: iconst_0       
        //   390: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   395: checkcast       Lcom/jetbrains/cidr/lang/psi/OCProperty;
        //   398: dup            
        //   399: ifnonnull       436
        //   402: new             Ljava/lang/IllegalStateException;
        //   405: dup            
        //   406: ldc             "@NotNull method %s.%s must not return null"
        //   408: ldc             2
        //   410: anewarray       Ljava/lang/Object;
        //   413: dup            
        //   414: ldc             0
        //   416: ldc             "com/jetbrains/cidr/lang/util/OCElementFactory"
        //   418: aastore        
        //   419: dup            
        //   420: ldc             1
        //   422: ldc             "propertyDeclaration"
        //   424: aastore        
        //   425: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   428: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   431: athrow         
        //   432: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   435: athrow         
        //   436: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  9      24     27     31     Ljava/lang/IllegalStateException;
        //  31     46     49     53     Ljava/lang/IllegalStateException;
        //  53     64     67     71     Ljava/lang/IllegalStateException;
        //  77     97     97     101    Ljava/lang/IllegalStateException;
        //  101    121    121    125    Ljava/lang/IllegalStateException;
        //  125    137    140    144    Ljava/lang/IllegalStateException;
        //  132    151    154    158    Ljava/lang/IllegalStateException;
        //  144    171    171    175    Ljava/lang/IllegalStateException;
        //  175    195    195    199    Ljava/lang/IllegalStateException;
        //  199    216    219    223    Ljava/lang/IllegalStateException;
        //  232    271    274    278    Ljava/lang/IllegalStateException;
        //  278    294    297    301    Ljava/lang/IllegalStateException;
        //  286    309    312    316    Ljava/lang/IllegalStateException;
        //  374    432    432    436    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0144:
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
    public static OCDeclaration enumConst(final String s, final PsiElement psiElement) {
        OCDeclaration ocDeclaration;
        try {
            ocDeclaration = ((OCEnum)typeElementFromText("enum { " + s + "}", psiElement).getFirstChild()).getFields().get(0);
            if (ocDeclaration == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "enumConst"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return ocDeclaration;
    }
    
    @NotNull
    public static OCExpression booleanConstant(final boolean b, final PsiElement psiElement) {
        final String value = OCIntType.getAppropriateBool((OCElement)psiElement.getContainingFile()).getValue(b, psiElement);
        OCExpression expressionFromText;
        try {
            expressionFromText = expressionFromText(value, psiElement, false);
            if (expressionFromText == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "booleanConstant"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return expressionFromText;
    }
    
    @NotNull
    public static OCSendMessageExpression sendMessageExpression(final List<String> list, final PsiElement psiElement) {
        final StringBuilder sb = new StringBuilder("[ 0 ");
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next()).append(' ');
        }
        OCSendMessageExpression ocSendMessageExpression;
        try {
            sb.append("]");
            ocSendMessageExpression = (OCSendMessageExpression)expressionFromText(sb.toString(), psiElement);
            if (ocSendMessageExpression == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "sendMessageExpression"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return ocSendMessageExpression;
    }
    
    @NotNull
    public static OCCallExpression callExpression(@Nullable String s, final List<String> list, final PsiElement psiElement) {
        Label_0021: {
            try {
                if (s != null) {
                    if (!s.isEmpty()) {
                        break Label_0021;
                    }
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            s = "__empty";
        }
        final StringBuilder sb = new StringBuilder(s);
        sb.append('(');
        int n = 1;
        for (final String s2 : list) {
            try {
                if (n == 0) {
                    sb.append(',');
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            sb.append(s2);
            n = 0;
        }
        OCCallExpression ocCallExpression;
        try {
            sb.append(')');
            ocCallExpression = (OCCallExpression)expressionFromText(sb.toString(), psiElement);
            if (ocCallExpression == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "callExpression"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        return ocCallExpression;
    }
    
    @NotNull
    public static OCInterface interfaceByName(final String s, final PsiElement psiElement) {
        OCInterface ocInterface;
        try {
            ocInterface = (OCInterface)topLevelDeclarationFromText("@interface " + s + " @end", psiElement, true);
            if (ocInterface == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "interfaceByName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return ocInterface;
    }
    
    @NotNull
    public static OCMethod methodFromText(final String s, final PsiElement psiElement, final boolean b) {
        OCMethod ocMethod;
        try {
            ocMethod = ((OCInterface)topLevelDeclarationFromText("@interface i " + s + " @end", psiElement, b)).getMethods().get(0);
            if (ocMethod == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "methodFromText"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return ocMethod;
    }
    
    @NotNull
    public static OCDefineDirective macroDeclarationFromText(final String s, final String s2, final PsiFile psiFile) {
        OCDefineDirective ocDefineDirective;
        try {
            ocDefineDirective = (OCDefineDirective)topLevelDeclarationFromText("#define " + s + " " + s2, (PsiElement)psiFile);
            if (ocDefineDirective == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "macroDeclarationFromText"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return ocDefineDirective;
    }
    
    @NotNull
    public static OCMethod methodFromSignature(@NotNull final String s, @NotNull final PsiElement psiElement, final boolean b, final boolean b2) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "signature", "com/jetbrains/cidr/lang/util/OCElementFactory", "methodFromSignature"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/util/OCElementFactory", "methodFromSignature"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        final OCCodeStyleSettings ocCodeStyleSettings = (OCCodeStyleSettings)CodeStyleSettingsManager.getSettings(psiElement.getProject()).getCustomSettings((Class)OCCodeStyleSettings.class);
        String s2 = null;
        Label_0138: {
            Label_0127: {
                try {
                    if (ocCodeStyleSettings == null) {
                        break Label_0127;
                    }
                    final OCCodeStyleSettings ocCodeStyleSettings2 = ocCodeStyleSettings;
                    final boolean b3 = ocCodeStyleSettings2.SEMICOLON_AFTER_METHOD_SIGNATURE;
                    if (b3) {
                        break Label_0127;
                    }
                    break Label_0127;
                }
                catch (IllegalStateException ex3) {
                    throw a(ex3);
                }
                try {
                    final OCCodeStyleSettings ocCodeStyleSettings2 = ocCodeStyleSettings;
                    final boolean b3 = ocCodeStyleSettings2.SEMICOLON_AFTER_METHOD_SIGNATURE;
                    if (b3) {
                        s2 = ";";
                        break Label_0138;
                    }
                }
                catch (IllegalStateException ex4) {
                    throw a(ex4);
                }
            }
            s2 = "";
        }
        final String s3 = s2;
        OCMethod methodFromText = null;
        Label_0184: {
            StringBuilder append;
            try {
                append = new StringBuilder().append(s);
                if (b) {
                    final String string = s3 + "{\n}";
                    break Label_0184;
                }
            }
            catch (IllegalStateException ex5) {
                throw a(ex5);
            }
            final String string = ";";
            try {
                methodFromText = methodFromText(append.append(string).toString(), psiElement, b2);
                if (methodFromText == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "methodFromSignature"));
                }
            }
            catch (IllegalStateException ex6) {
                throw a(ex6);
            }
        }
        return methodFromText;
    }
    
    @NotNull
    public static OCUnaryExpression unaryExpression(final PsiElement psiElement, final OCElementType ocElementType) {
        final OCUnaryExpression ocUnaryExpression = (OCUnaryExpression)expressionFromText(ocElementType.getName() + " a", psiElement, false);
        OCUnaryExpression ocUnaryExpression2;
        try {
            OCChangeUtil.replaceHandlingMacros((PsiElement)ocUnaryExpression.getOperand(), psiElement);
            ocUnaryExpression2 = ocUnaryExpression;
            if (ocUnaryExpression2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "unaryExpression"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return ocUnaryExpression2;
    }
    
    @NotNull
    public static PsiElement createUDLSuffux(final String s, final PsiElement psiElement) {
        final OCUDLiteralExpressionImpl ocudLiteralExpressionImpl = (OCUDLiteralExpressionImpl)expressionFromText("\"\"" + s, psiElement, false);
        PsiElement nameIdentifier;
        try {
            nameIdentifier = ocudLiteralExpressionImpl.getNameIdentifier();
            if (nameIdentifier == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "createUDLSuffux"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return nameIdentifier;
    }
    
    @NotNull
    public static OCBinaryExpression binaryExpression(final PsiElement psiElement, final PsiElement psiElement2, final OCElementType ocElementType) {
        final OCBinaryExpression ocBinaryExpression = (OCBinaryExpression)expressionFromText("a " + ocElementType.getName() + " b", psiElement, false);
        OCBinaryExpression ocBinaryExpression2;
        try {
            OCChangeUtil.replaceHandlingMacros((PsiElement)ocBinaryExpression.getLeft(), psiElement);
            OCChangeUtil.replaceHandlingMacros((PsiElement)ocBinaryExpression.getRight(), psiElement2);
            ocBinaryExpression2 = ocBinaryExpression;
            if (ocBinaryExpression2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "binaryExpression"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return ocBinaryExpression2;
    }
    
    @NotNull
    public static PsiElement binaryOperatorFromText(final String s, final PsiElement psiElement) {
        PsiElement psi;
        try {
            psi = ((OCBinaryExpression)expressionFromText("a" + s + "b", psiElement)).getOperationSignNode().getPsi();
            if (psi == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "binaryOperatorFromText"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return psi;
    }
    
    @NotNull
    public static ASTNode typeModifierFromText(final String s, final PsiElement psiElement) {
        ASTNode firstChildNode;
        try {
            firstChildNode = typeElementFromText(s + " int", psiElement).getNode().getFirstChildNode();
            if (firstChildNode == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "typeModifierFromText"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return firstChildNode;
    }
    
    @NotNull
    public static PsiElement ivarScopeSpecifier(final OCVisibility p0, final PsiElement p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Ljava/lang/StringBuilder;
        //     3: dup            
        //     4: invokespecial   java/lang/StringBuilder.<init>:()V
        //     7: ldc             "@interface c { @"
        //     9: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    12: aload_0        
        //    13: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    16: ldc             "} #end"
        //    18: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    21: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    24: aload_1        
        //    25: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.topLevelDeclarationFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //    28: checkcast       Lcom/jetbrains/cidr/lang/psi/OCInterface;
        //    31: invokeinterface com/jetbrains/cidr/lang/psi/OCInterface.getInstanceVariablesList:()Lcom/jetbrains/cidr/lang/psi/OCInstanceVariablesList;
        //    36: astore_2       
        //    37: aload_2        
        //    38: invokeinterface com/jetbrains/cidr/lang/psi/OCInstanceVariablesList.getFirstChild:()Lcom/intellij/psi/PsiElement;
        //    43: astore_3       
        //    44: aload_3        
        //    45: ifnull          120
        //    48: aload_3        
        //    49: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.getVisibilityFromElement:(Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/symbols/OCVisibility;
        //    52: aload_0        
        //    53: if_acmpne       110
        //    56: goto            63
        //    59: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    62: athrow         
        //    63: aload_3        
        //    64: dup            
        //    65: ifnonnull       109
        //    68: goto            75
        //    71: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    74: athrow         
        //    75: new             Ljava/lang/IllegalStateException;
        //    78: dup            
        //    79: ldc             "@NotNull method %s.%s must not return null"
        //    81: ldc             2
        //    83: anewarray       Ljava/lang/Object;
        //    86: dup            
        //    87: ldc             0
        //    89: ldc             "com/jetbrains/cidr/lang/util/OCElementFactory"
        //    91: aastore        
        //    92: dup            
        //    93: ldc             1
        //    95: ldc             "ivarScopeSpecifier"
        //    97: aastore        
        //    98: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   101: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   104: athrow         
        //   105: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   108: athrow         
        //   109: areturn        
        //   110: aload_3        
        //   111: invokeinterface com/intellij/psi/PsiElement.getNextSibling:()Lcom/intellij/psi/PsiElement;
        //   116: astore_3       
        //   117: goto            44
        //   120: aconst_null    
        //   121: dup            
        //   122: ifnonnull       159
        //   125: new             Ljava/lang/IllegalStateException;
        //   128: dup            
        //   129: ldc             "@NotNull method %s.%s must not return null"
        //   131: ldc             2
        //   133: anewarray       Ljava/lang/Object;
        //   136: dup            
        //   137: ldc             0
        //   139: ldc             "com/jetbrains/cidr/lang/util/OCElementFactory"
        //   141: aastore        
        //   142: dup            
        //   143: ldc             1
        //   145: ldc             "ivarScopeSpecifier"
        //   147: aastore        
        //   148: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   151: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   154: athrow         
        //   155: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   158: athrow         
        //   159: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  44     56     59     63     Ljava/lang/IllegalStateException;
        //  48     68     71     75     Ljava/lang/IllegalStateException;
        //  63     105    105    109    Ljava/lang/IllegalStateException;
        //  120    155    155    159    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0063:
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
    public static OCInstanceVariablesList instanceVariableList(final PsiElement psiElement) {
        OCInstanceVariablesList instanceVariablesList;
        try {
            instanceVariablesList = ((OCInterface)topLevelDeclarationFromText("@interface c {} @end", psiElement)).getInstanceVariablesList();
            if (instanceVariablesList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "instanceVariableList"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return instanceVariablesList;
    }
    
    @NotNull
    public static OCDeclaration constructorFromText(final String s, final PsiElement psiElement) {
        OCDeclaration constructorFromText;
        try {
            constructorFromText = constructorFromText(s, psiElement, false);
            if (constructorFromText == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "constructorFromText"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return constructorFromText;
    }
    
    @NotNull
    public static OCDeclaration constructorFromText(final String s, final PsiElement psiElement, final boolean b) {
        final OCStruct ocStruct = (OCStruct)typeElementFromTextOrNull("struct " + s.substring(0, s.indexOf(40)) + " { " + s + ";}", psiElement, b).getFirstChild();
        OCDeclaration ocDeclaration;
        try {
            ocDeclaration = (OCDeclaration)PsiTreeUtil.getChildOfType((PsiElement)ocStruct, (Class)OCFunctionDeclaration.class);
            if (ocDeclaration == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "constructorFromText"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return ocDeclaration;
    }
    
    @NotNull
    public static OCConstructorInitializationList constructorInitializationList(final OCElement ocElement) {
        final OCConstructorInitializationList constructorInitializationList = ((OCFunctionDefinition)constructorFromText("XXX() : y(1) {}", (PsiElement)ocElement)).getConstructorInitializationList();
        OCConstructorInitializationList list;
        try {
            OCChangeUtil.delete((PsiElement)constructorInitializationList.getInitializers().get(0));
            list = constructorInitializationList;
            if (list == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "constructorInitializationList"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return list;
    }
    
    @NotNull
    public static OCConstructorFieldInitializer constructorFieldInitializerFromText(final String s, final PsiElement psiElement) {
        final OCConstructorInitializationList constructorInitializationList = ((OCFunctionDefinition)constructorFromText("XXX() : " + s + "{}", psiElement)).getConstructorInitializationList();
        OCConstructorFieldInitializer ocConstructorFieldInitializer;
        try {
            ocConstructorFieldInitializer = constructorInitializationList.getInitializers().get(0);
            if (ocConstructorFieldInitializer == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "constructorFieldInitializerFromText"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return ocConstructorFieldInitializer;
    }
    
    @NotNull
    public static OCPropertyAttributesList propertyAttributeList(final OCPropertySymbol.PropertyAttribute propertyAttribute, @Nullable final String s, final PsiElement psiElement) {
        StringBuilder append = null;
        String string = null;
        Label_0051: {
            try {
                append = new StringBuilder().append("@interface c @property (").append(propertyAttribute.getTokenName());
                if (s != null) {
                    string = "=" + s;
                    break Label_0051;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            string = "";
        }
        final String string2 = append.append(string).append(") int x; @end").toString();
        OCPropertyAttributesList propertyAttributesList;
        try {
            propertyAttributesList = ((OCInterface)topLevelDeclarationFromText(string2, psiElement, true)).getProperties().get(0).getPropertyAttributesList();
            if (propertyAttributesList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "propertyAttributeList"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return propertyAttributesList;
    }
    
    @NotNull
    public static OCSynthesizePropertiesList synthesizeList(final String s, final String s2, @Nullable final String s3, final PsiElement psiElement) {
        StringBuilder append = null;
        String string = null;
        Label_0057: {
            try {
                append = new StringBuilder().append("@implementation i ").append(s).append(" ").append(s2);
                if (s3 != null) {
                    string = "=" + s3;
                    break Label_0057;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            string = "";
        }
        final ASTNode node = topLevelDeclarationFromText(append.append(string).append("; @end").toString(), psiElement, true).getNode();
        OCSynthesizePropertiesList list;
        try {
            list = (OCSynthesizePropertiesList)node.findChildByType((IElementType)OCElementTypes.SYNTHESIZED_PROPERTIES_LIST).getPsi((Class)OCSynthesizePropertiesList.class);
            if (list == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "synthesizeList"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return list;
    }
    
    @NotNull
    public static OCSynthesizeProperty synthesize(final String s, @Nullable final String s2, final PsiElement psiElement) {
        StringBuilder append = null;
        String string = null;
        Label_0048: {
            try {
                append = new StringBuilder().append("@implementation i @synthesize ").append(s);
                if (s2 != null) {
                    string = "=" + s2;
                    break Label_0048;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            string = "";
        }
        final ASTNode node = topLevelDeclarationFromText(append.append(string).append("; @end").toString(), psiElement, true).getNode();
        OCSynthesizeProperty ocSynthesizeProperty;
        try {
            ocSynthesizeProperty = ((OCSynthesizePropertiesList)node.findChildByType((IElementType)OCElementTypes.SYNTHESIZED_PROPERTIES_LIST).getPsi((Class)OCSynthesizePropertiesList.class)).getProperties().get(0);
            if (ocSynthesizeProperty == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "synthesize"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return ocSynthesizeProperty;
    }
    
    @NotNull
    private static String a(String s, final OCType ocType, final PsiElement psiElement, final boolean b) {
        if ("<unnamed>".equals(s)) {
            s = "";
        }
        String string4 = null;
        Label_0523: {
            Label_0178: {
                try {
                    if (ocType.getAliasName() != null) {
                        break Label_0523;
                    }
                    if (!(ocType instanceof OCArrayType)) {
                        break Label_0178;
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                final OCArrayType ocArrayType = (OCArrayType)ocType;
                final int length = ocArrayType.getLength();
                if (s.startsWith("*")) {
                    s = "(" + s + ")";
                }
                String a = null;
                Label_0118: {
                    StringBuilder append;
                    try {
                        append = new StringBuilder().append(s).append(" [");
                        if (ocArrayType.hasLength()) {
                            final Serializable value = length;
                            break Label_0118;
                        }
                    }
                    catch (IllegalStateException ex2) {
                        throw a(ex2);
                    }
                    final Serializable value = "";
                    try {
                        a = a(append.append(value).append("]").toString(), ocArrayType.getRefType(), psiElement, b);
                        if (a == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "declaratorText"));
                        }
                    }
                    catch (IllegalStateException ex3) {
                        throw a(ex3);
                    }
                }
                return a;
            }
            if (ocType instanceof OCPointerType) {
                final OCPointerType ocPointerType = (OCPointerType)ocType;
                String string2 = null;
                Label_0350: {
                    StringBuilder sb2 = null;
                    Label_0315: {
                        Label_0289: {
                            StringBuilder append3 = null;
                            String s4 = null;
                            Label_0278: {
                                Label_0256: {
                                    StringBuilder append2 = null;
                                    Label_0234: {
                                        StringBuilder sb = null;
                                        Label_0223: {
                                            try {
                                                if (ocPointerType.getRefType() instanceof OCIdType) {
                                                    break Label_0289;
                                                }
                                                sb = new StringBuilder();
                                                final OCType ocType2 = ocType;
                                                final boolean b2 = ocType2 instanceof OCBlockPointerType;
                                                if (b2) {
                                                    break Label_0223;
                                                }
                                                break Label_0223;
                                            }
                                            catch (IllegalStateException ex4) {
                                                throw a(ex4);
                                            }
                                            try {
                                                sb = new StringBuilder();
                                                final OCType ocType2 = ocType;
                                                final boolean b2 = ocType2 instanceof OCBlockPointerType;
                                                if (b2) {
                                                    final String s2 = "^";
                                                    break Label_0234;
                                                }
                                            }
                                            catch (IllegalStateException ex5) {
                                                throw a(ex5);
                                            }
                                        }
                                        final String s2 = "*";
                                        try {
                                            append2 = sb.append(s2);
                                            if (ocPointerType.isConst()) {
                                                final String s3 = " const ";
                                                break Label_0256;
                                            }
                                        }
                                        catch (IllegalStateException ex6) {
                                            throw a(ex6);
                                        }
                                    }
                                    final String s3 = "";
                                    try {
                                        append3 = append2.append(s3);
                                        if (ocPointerType.isVolatile()) {
                                            s4 = " volatile ";
                                            break Label_0278;
                                        }
                                    }
                                    catch (IllegalStateException ex7) {
                                        throw a(ex7);
                                    }
                                }
                                s4 = "";
                            }
                            s = append3.append(s4).append(s).toString();
                            try {
                                sb2 = new StringBuilder();
                                if (b) {
                                    break Label_0315;
                                }
                                final OCPointerType ocPointerType2 = ocPointerType;
                                final ARCAttribute arcAttribute = ocPointerType2.getARCAttribute();
                                if (arcAttribute != null) {
                                    break Label_0315;
                                }
                                break Label_0315;
                            }
                            catch (IllegalStateException ex8) {
                                throw a(ex8);
                            }
                        }
                        try {
                            final OCPointerType ocPointerType2 = ocPointerType;
                            final ARCAttribute arcAttribute = ocPointerType2.getARCAttribute();
                            if (arcAttribute != null) {
                                final String string = ocPointerType.getARCAttribute().getTokenName() + " ";
                                break Label_0350;
                            }
                        }
                        catch (IllegalStateException ex9) {
                            throw a(ex9);
                        }
                    }
                    final String string = "";
                    try {
                        string2 = sb2.append(string).append(a(s, ocPointerType.getRefType(), psiElement, b)).toString();
                        if (string2 == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "declaratorText"));
                        }
                    }
                    catch (IllegalStateException ex10) {
                        throw a(ex10);
                    }
                }
                return string2;
            }
            if (ocType instanceof OCFunctionType) {
                final OCFunctionType ocFunctionType = (OCFunctionType)ocType;
                final StringBuilder sb3 = new StringBuilder();
                String string3;
                try {
                    sb3.append(ocFunctionType.getReturnType().getBestNameInContext(psiElement));
                    sb3.append(" (").append(s).append(")");
                    sb3.append(OCTypeNameVisitor.getFunctionSignature(psiElement, ocFunctionType, "", false, null));
                    string3 = sb3.toString();
                    if (string3 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "declaratorText"));
                    }
                }
                catch (IllegalStateException ex11) {
                    throw a(ex11);
                }
                return string3;
            }
            try {
                string4 = ocType.getBestNameInContext(psiElement) + " " + s;
                if (string4 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "declaratorText"));
                }
            }
            catch (IllegalStateException ex12) {
                throw a(ex12);
            }
        }
        return string4;
    }
    
    @NotNull
    public static String declarationText(@NotNull final List<String> p0, final String p1, final OCType p2, @Nullable final String p3, @NotNull final PsiElement p4, @Nullable final String p5, final boolean p6) {
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
        //    18: ldc             "modifiers"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/util/OCElementFactory"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "declarationText"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    43: athrow         
        //    44: aload           4
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
        //    69: ldc             "com/jetbrains/cidr/lang/util/OCElementFactory"
        //    71: aastore        
        //    72: dup            
        //    73: ldc             2
        //    75: ldc             "declarationText"
        //    77: aastore        
        //    78: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    81: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    84: athrow         
        //    85: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    88: athrow         
        //    89: aload_2        
        //    90: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //    93: ifeq            115
        //    96: aload_2        
        //    97: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getAliasName:()Ljava/lang/String;
        //   100: ifnonnull       115
        //   103: goto            110
        //   106: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   109: athrow         
        //   110: aload_2        
        //   111: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.to:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   114: astore_2       
        //   115: aload_2        
        //   116: ifnonnull       167
        //   119: ldc             ""
        //   121: dup            
        //   122: ifnonnull       166
        //   125: goto            132
        //   128: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   131: athrow         
        //   132: new             Ljava/lang/IllegalStateException;
        //   135: dup            
        //   136: ldc             "@NotNull method %s.%s must not return null"
        //   138: ldc             2
        //   140: anewarray       Ljava/lang/Object;
        //   143: dup            
        //   144: ldc             0
        //   146: ldc             "com/jetbrains/cidr/lang/util/OCElementFactory"
        //   148: aastore        
        //   149: dup            
        //   150: ldc             1
        //   152: ldc             "declarationText"
        //   154: aastore        
        //   155: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   158: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   161: athrow         
        //   162: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   165: athrow         
        //   166: areturn        
        //   167: ldc             "<unnamed>"
        //   169: aload_1        
        //   170: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   173: istore          7
        //   175: new             Ljava/lang/StringBuilder;
        //   178: dup            
        //   179: invokespecial   java/lang/StringBuilder.<init>:()V
        //   182: astore          8
        //   184: aload_0        
        //   185: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   190: astore          9
        //   192: aload           9
        //   194: invokeinterface java/util/Iterator.hasNext:()Z
        //   199: ifeq            230
        //   202: aload           9
        //   204: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   209: checkcast       Ljava/lang/String;
        //   212: astore          10
        //   214: aload           8
        //   216: aload           10
        //   218: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   221: bipush          32
        //   223: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   226: pop            
        //   227: goto            192
        //   230: aload_2        
        //   231: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   234: ifeq            273
        //   237: aload_2        
        //   238: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getAliasName:()Ljava/lang/String;
        //   241: ifnonnull       273
        //   244: goto            251
        //   247: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   250: athrow         
        //   251: aload           8
        //   253: aload_1        
        //   254: aload_2        
        //   255: aload           4
        //   257: iload           6
        //   259: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.a:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;Z)Ljava/lang/String;
        //   262: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   265: pop            
        //   266: goto            389
        //   269: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   272: athrow         
        //   273: aload_2        
        //   274: instanceof      Lcom/jetbrains/cidr/lang/types/OCUnknownType;
        //   277: ifeq            353
        //   280: aload           4
        //   282: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   287: astore          9
        //   289: aload           9
        //   291: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   294: ifeq            342
        //   297: aload           8
        //   299: aload           9
        //   301: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   304: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getKind:()Lcom/jetbrains/cidr/lang/OCLanguageKind;
        //   309: invokeinterface com/jetbrains/cidr/lang/OCLanguageKind.isObjC:()Z
        //   314: ifeq            333
        //   317: goto            324
        //   320: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   323: athrow         
        //   324: ldc             "id"
        //   326: goto            335
        //   329: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   332: athrow         
        //   333: ldc             "int"
        //   335: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   338: pop            
        //   339: goto            350
        //   342: aload           8
        //   344: ldc             "id"
        //   346: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   349: pop            
        //   350: goto            365
        //   353: aload           8
        //   355: aload_2        
        //   356: aload           4
        //   358: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getBestNameInContext:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   361: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   364: pop            
        //   365: iload           7
        //   367: ifne            389
        //   370: aload           8
        //   372: bipush          32
        //   374: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   377: aload_1        
        //   378: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   381: pop            
        //   382: goto            389
        //   385: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   388: athrow         
        //   389: aload           8
        //   391: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   394: astore          9
        //   396: aload           5
        //   398: ifnull          506
        //   401: aload           8
        //   403: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   406: aload_1        
        //   407: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.isSimpleDeclaration:(Ljava/lang/String;Ljava/lang/String;)Z
        //   410: ifeq            506
        //   413: goto            420
        //   416: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   419: athrow         
        //   420: aload_0        
        //   421: invokeinterface java/util/List.isEmpty:()Z
        //   426: ifeq            506
        //   429: goto            436
        //   432: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   435: athrow         
        //   436: aload_2        
        //   437: aload           5
        //   439: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   442: dup            
        //   443: aload           4
        //   445: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
        //   448: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equalsAfterResolving:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   451: ifeq            506
        //   454: goto            461
        //   457: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   460: athrow         
        //   461: iload           7
        //   463: ifeq            480
        //   466: goto            473
        //   469: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   472: athrow         
        //   473: aload           5
        //   475: astore          9
        //   477: goto            506
        //   480: new             Ljava/lang/StringBuilder;
        //   483: dup            
        //   484: invokespecial   java/lang/StringBuilder.<init>:()V
        //   487: aload           5
        //   489: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   492: ldc             " "
        //   494: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   497: aload_1        
        //   498: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   501: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   504: astore          9
        //   506: aload_3        
        //   507: ifnull          580
        //   510: new             Ljava/lang/StringBuilder;
        //   513: dup            
        //   514: invokespecial   java/lang/StringBuilder.<init>:()V
        //   517: aload           9
        //   519: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   522: bipush          61
        //   524: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   527: aload_3        
        //   528: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   531: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   534: dup            
        //   535: ifnonnull       579
        //   538: goto            545
        //   541: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   544: athrow         
        //   545: new             Ljava/lang/IllegalStateException;
        //   548: dup            
        //   549: ldc             "@NotNull method %s.%s must not return null"
        //   551: ldc             2
        //   553: anewarray       Ljava/lang/Object;
        //   556: dup            
        //   557: ldc             0
        //   559: ldc             "com/jetbrains/cidr/lang/util/OCElementFactory"
        //   561: aastore        
        //   562: dup            
        //   563: ldc             1
        //   565: ldc             "declarationText"
        //   567: aastore        
        //   568: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   571: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   574: athrow         
        //   575: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   578: athrow         
        //   579: areturn        
        //   580: aload           9
        //   582: dup            
        //   583: ifnonnull       620
        //   586: new             Ljava/lang/IllegalStateException;
        //   589: dup            
        //   590: ldc             "@NotNull method %s.%s must not return null"
        //   592: ldc             2
        //   594: anewarray       Ljava/lang/Object;
        //   597: dup            
        //   598: ldc             0
        //   600: ldc             "com/jetbrains/cidr/lang/util/OCElementFactory"
        //   602: aastore        
        //   603: dup            
        //   604: ldc             1
        //   606: ldc             "declarationText"
        //   608: aastore        
        //   609: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   612: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   615: athrow         
        //   616: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   619: athrow         
        //   620: areturn        
        //    Signature:
        //  (Ljava/util/List<Ljava/lang/String;>;Ljava/lang/String;Lcom/jetbrains/cidr/lang/types/OCType;Ljava/lang/String;Lcom/intellij/psi/PsiElement;Ljava/lang/String;Z)Ljava/lang/String;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      40     40     44     Ljava/lang/IllegalStateException;
        //  44     85     85     89     Ljava/lang/IllegalStateException;
        //  89     103    106    110    Ljava/lang/IllegalStateException;
        //  115    125    128    132    Ljava/lang/IllegalStateException;
        //  119    162    162    166    Ljava/lang/IllegalStateException;
        //  230    244    247    251    Ljava/lang/IllegalStateException;
        //  237    269    269    273    Ljava/lang/IllegalStateException;
        //  289    317    320    324    Ljava/lang/IllegalStateException;
        //  297    329    329    333    Ljava/lang/IllegalStateException;
        //  365    382    385    389    Ljava/lang/IllegalStateException;
        //  396    413    416    420    Ljava/lang/IllegalStateException;
        //  401    429    432    436    Ljava/lang/IllegalStateException;
        //  420    454    457    461    Ljava/lang/IllegalStateException;
        //  436    466    469    473    Ljava/lang/IllegalStateException;
        //  506    538    541    545    Ljava/lang/IllegalStateException;
        //  510    575    575    579    Ljava/lang/IllegalStateException;
        //  580    616    616    620    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0420:
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
    public static PsiElement createIdentifier(@NotNull final String s, @NotNull final PsiElement psiElement) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/util/OCElementFactory", "createIdentifier"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/util/OCElementFactory", "createIdentifier"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        final OCDeclaration ocDeclaration = (OCDeclaration)PsiTreeUtil.getChildOfType((PsiElement)codeFragment("int " + s, psiElement.getProject(), psiElement, false, false), (Class)OCElement.class);
        List<OCDeclarator> declarators = null;
        Label_0148: {
            try {
                if (ocDeclaration != null) {
                    declarators = ocDeclaration.getDeclarators();
                    break Label_0148;
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
            declarators = null;
        }
        final List<OCDeclarator> list = declarators;
        PsiElement nameIdentifier = null;
        Label_0184: {
            Logger log = null;
            Label_0175: {
                try {
                    log = OCElementFactory.LOG;
                    if (list == null) {
                        break Label_0175;
                    }
                    final List<OCDeclarator> list2 = list;
                    final boolean b = list2.isEmpty();
                    if (!b) {
                        break Label_0175;
                    }
                    break Label_0175;
                }
                catch (IllegalStateException ex4) {
                    throw a(ex4);
                }
                try {
                    final List<OCDeclarator> list2 = list;
                    final boolean b = list2.isEmpty();
                    if (!b) {
                        final boolean b2 = true;
                        break Label_0184;
                    }
                }
                catch (IllegalStateException ex5) {
                    throw a(ex5);
                }
            }
            final boolean b2 = false;
            try {
                log.assertTrue(b2, (Object)("Bad identifier name " + s));
                nameIdentifier = list.get(0).getNameIdentifier();
                if (nameIdentifier == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "createIdentifier"));
                }
            }
            catch (IllegalStateException ex6) {
                throw a(ex6);
            }
        }
        return nameIdentifier;
    }
    
    @NotNull
    public static OCMacroForeignLeafElement createMacroForeignIdentifier(@NotNull final String s, @NotNull final OCMacroForeignLeafElement ocMacroForeignLeafElement) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/util/OCElementFactory", "createMacroForeignIdentifier"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (ocMacroForeignLeafElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sample", "com/jetbrains/cidr/lang/util/OCElementFactory", "createMacroForeignIdentifier"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        final OCDeclaration ocDeclaration = (OCDeclaration)PsiTreeUtil.getChildOfType((PsiElement)codeFragment("#define M(x) x\nint M(" + s + ")", ocMacroForeignLeafElement.getProject(), null, false, false), (Class)OCDeclaration.class);
        List<OCDeclarator> declarators = null;
        Label_0151: {
            try {
                if (ocDeclaration != null) {
                    declarators = ocDeclaration.getDeclarators();
                    break Label_0151;
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
            declarators = null;
        }
        final List<OCDeclarator> list = declarators;
        Logger log = null;
        boolean b2 = false;
        Label_0187: {
            Label_0178: {
                try {
                    log = OCElementFactory.LOG;
                    if (list == null) {
                        break Label_0178;
                    }
                    final List<OCDeclarator> list2 = list;
                    final boolean b = list2.isEmpty();
                    if (!b) {
                        break Label_0178;
                    }
                    break Label_0178;
                }
                catch (IllegalStateException ex4) {
                    throw a(ex4);
                }
                try {
                    final List<OCDeclarator> list2 = list;
                    final boolean b = list2.isEmpty();
                    if (!b) {
                        b2 = true;
                        break Label_0187;
                    }
                }
                catch (IllegalStateException ex5) {
                    throw a(ex5);
                }
            }
            b2 = false;
        }
        log.assertTrue(b2, (Object)("Bad identifier name " + s));
        final OCMacroForeignLeafElement ocMacroForeignLeafElement2 = (OCMacroForeignLeafElement)list.get(0).getNameIdentifier();
        OCMacroForeignLeafElement ocMacroForeignLeafElement3;
        try {
            ocMacroForeignLeafElement2.copyFromElement(ocMacroForeignLeafElement);
            ocMacroForeignLeafElement3 = ocMacroForeignLeafElement2;
            if (ocMacroForeignLeafElement3 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "createMacroForeignIdentifier"));
            }
        }
        catch (IllegalStateException ex6) {
            throw a(ex6);
        }
        return ocMacroForeignLeafElement3;
    }
    
    @NotNull
    public static OCCppNamespaceQualifier createNamespaceQualifier(final String s, final PsiElement psiElement) {
        final OCStruct ocStruct = (OCStruct)declarationFromText("struct " + s + "::XXXX {}", psiElement, true).getTypeElement().getFirstChild();
        OCCppNamespaceQualifier namespaceQualifier;
        try {
            namespaceQualifier = ocStruct.getNamespaceQualifier();
            if (namespaceQualifier == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "createNamespaceQualifier"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return namespaceQualifier;
    }
    
    @NotNull
    public static ASTNode createColon2x(final PsiElement psiElement) {
        final OCStruct ocStruct = (OCStruct)declarationFromText("struct A::B{}", psiElement).getTypeElement().getFirstChild();
        ASTNode childByType;
        try {
            childByType = ocStruct.getNode().findChildByType((IElementType)OCTokenTypes.COLON2X);
            if (childByType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "createColon2x"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return childByType;
    }
    
    @NotNull
    public static PsiElement topLevelDeclarationFromText(@NotNull final String s, @NotNull final PsiElement psiElement) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/lang/util/OCElementFactory", "topLevelDeclarationFromText"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/util/OCElementFactory", "topLevelDeclarationFromText"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        PsiElement topLevelDeclarationFromText;
        try {
            topLevelDeclarationFromText = topLevelDeclarationFromText(s, psiElement, false);
            if (topLevelDeclarationFromText == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "topLevelDeclarationFromText"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        return topLevelDeclarationFromText;
    }
    
    @NotNull
    public static PsiElement topLevelDeclarationFromText(@NotNull final String s, @NotNull final PsiElement psiElement, final boolean b) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/lang/util/OCElementFactory", "topLevelDeclarationFromText"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/util/OCElementFactory", "topLevelDeclarationFromText"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        PsiElement psiElement2 = ((PsiElement)codeFragment(s, psiElement.getProject(), psiElement, false, b)).getFirstChild();
        while (true) {
            Label_0133: {
                try {
                    if (psiElement2 instanceof PsiWhiteSpace) {
                        break Label_0133;
                    }
                    final PsiElement psiElement3 = psiElement2;
                    final boolean b2 = psiElement3 instanceof PsiComment;
                    if (!b2) {
                        break Label_0133;
                    }
                    break Label_0133;
                }
                catch (IllegalStateException ex3) {
                    throw a(ex3);
                }
                try {
                    final PsiElement psiElement3 = psiElement2;
                    final boolean b2 = psiElement3 instanceof PsiComment;
                    if (!b2) {
                        if (!(psiElement2 instanceof OCMacroCall)) {
                            break;
                        }
                    }
                }
                catch (IllegalStateException ex4) {
                    throw a(ex4);
                }
            }
            psiElement2 = psiElement2.getNextSibling();
        }
        PsiElement psiElement4;
        try {
            psiElement4 = psiElement2;
            if (psiElement4 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "topLevelDeclarationFromText"));
            }
        }
        catch (IllegalStateException ex5) {
            throw a(ex5);
        }
        return psiElement4;
    }
    
    @NotNull
    public static OCTypeElement typeElementFromText(@NotNull final String s, @NotNull final PsiElement psiElement) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/lang/util/OCElementFactory", "typeElementFromText"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/util/OCElementFactory", "typeElementFromText"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        OCTypeElement typeElementFromTextOrNull;
        try {
            typeElementFromTextOrNull = typeElementFromTextOrNull(s, psiElement, true);
            if (typeElementFromTextOrNull == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "typeElementFromText"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        return typeElementFromTextOrNull;
    }
    
    @Nullable
    public static OCTypeElement typeElementFromTextOrNull(@NotNull final String s, @NotNull final PsiElement psiElement, final boolean b) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/lang/util/OCElementFactory", "typeElementFromTextOrNull"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/util/OCElementFactory", "typeElementFromTextOrNull"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return (OCTypeElement)PsiTreeUtil.getChildOfType((PsiElement)typeCodeFragment(s, psiElement.getProject(), psiElement, false, b), (Class)OCTypeElement.class);
    }
    
    @Nullable
    public static OCReferenceElement referenceElementFromText(@NotNull final String s, @NotNull final PsiElement psiElement, final boolean b) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/util/OCElementFactory", "referenceElementFromText"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/util/OCElementFactory", "referenceElementFromText"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return (OCReferenceElement)PsiTreeUtil.getChildOfType((PsiElement)expressionFromText(s, psiElement, b), (Class)OCReferenceElement.class);
    }
    
    @Nullable
    public static OCExpression expressionFromText(@NotNull final String s, @NotNull final PsiElement psiElement) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/lang/util/OCElementFactory", "expressionFromText"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/util/OCElementFactory", "expressionFromText"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return expressionFromText(s, psiElement, true);
    }
    
    @Nullable
    public static OCExpression expressionFromText(@NotNull final String s, @NotNull final PsiElement psiElement, final boolean b) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/lang/util/OCElementFactory", "expressionFromText"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/util/OCElementFactory", "expressionFromText"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return (OCExpression)PsiTreeUtil.getChildOfType((PsiElement)expressionCodeFragment(s, psiElement.getProject(), psiElement, false, b), (Class)OCExpression.class);
    }
    
    public static OCStatement statementFromText(@NotNull final String s, @NotNull final PsiElement psiElement) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/lang/util/OCElementFactory", "statementFromText"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/util/OCElementFactory", "statementFromText"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return statementFromText(s, psiElement, false);
    }
    
    public static OCStatement statementFromText(@NotNull String string, @NotNull final PsiElement psiElement, final boolean b) {
        try {
            if (string == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/lang/util/OCElementFactory", "statementFromText"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/util/OCElementFactory", "statementFromText"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        final StringBuilder alloc = StringBuilderSpinAllocator.alloc();
        string = alloc.append("void _______dummy(){").append(string).append(";}").toString();
        StringBuilderSpinAllocator.dispose(alloc);
        return (OCStatement)PsiTreeUtil.getChildOfType(PsiTreeUtil.getChildOfType(PsiTreeUtil.getChildOfType((PsiElement)codeFragment(string, psiElement.getProject(), psiElement, false, b), (Class)OCFunctionDefinition.class), (Class)OCBlockStatement.class), (Class)OCStatement.class);
    }
    
    @NotNull
    public static OCCodeFragment codeFragment(@NotNull final String s, @NotNull final Project project, @Nullable final PsiElement psiElement, final boolean b, final boolean b2) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/lang/util/OCElementFactory", "codeFragment"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/util/OCElementFactory", "codeFragment"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        OCCodeFragment codeFragment;
        try {
            codeFragment = codeFragment(s, project, psiElement, OCTokenTypes.OC_FILE, b, b2);
            if (codeFragment == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "codeFragment"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        return codeFragment;
    }
    
    @NotNull
    public static OCCodeFragment expressionCodeFragment(@NotNull final String s, @NotNull final Project project, @Nullable final PsiElement psiElement, final boolean b, final boolean b2) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/lang/util/OCElementFactory", "expressionCodeFragment"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/util/OCElementFactory", "expressionCodeFragment"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        OCCodeFragment codeFragment;
        try {
            codeFragment = codeFragment(s, project, psiElement, OCElementTypes.EXPRESSION_CODE_FRAGMENT, b, b2);
            if (codeFragment == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "expressionCodeFragment"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        return codeFragment;
    }
    
    @NotNull
    public static OCCodeFragment expressionCodeFragment(@NotNull final String s, @NotNull final Project project, @Nullable final PsiElement psiElement, final boolean b, final boolean b2, final OCLanguageKind ocLanguageKind) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/lang/util/OCElementFactory", "expressionCodeFragment"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/util/OCElementFactory", "expressionCodeFragment"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        OCCodeFragment codeFragment;
        try {
            codeFragment = codeFragment(s, project, psiElement, OCElementTypes.EXPRESSION_CODE_FRAGMENT, b, b2, ocLanguageKind);
            if (codeFragment == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "expressionCodeFragment"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        return codeFragment;
    }
    
    @NotNull
    public static OCCodeFragment expressionCodeFragmentCpp(@NotNull final String s, @NotNull final Project project, @Nullable final PsiElement psiElement, final boolean b, final boolean b2) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/lang/util/OCElementFactory", "expressionCodeFragmentCpp"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/util/OCElementFactory", "expressionCodeFragmentCpp"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        OCCodeFragment codeFragment;
        try {
            codeFragment = codeFragment(s, project, psiElement, OCElementTypes.EXPRESSION_CODE_FRAGMENT, b, true, b2);
            if (codeFragment == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "expressionCodeFragmentCpp"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        return codeFragment;
    }
    
    @NotNull
    public static OCCodeFragment expressionOrStatementsCodeFragment(@NotNull final String s, @NotNull final Project project, @Nullable final PsiElement psiElement, final boolean b, final boolean b2) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/lang/util/OCElementFactory", "expressionOrStatementsCodeFragment"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/util/OCElementFactory", "expressionOrStatementsCodeFragment"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        OCCodeFragment codeFragment;
        try {
            codeFragment = codeFragment(s, project, psiElement, OCElementTypes.EXPRESSION_OR_STATEMENTS_CODE_FRAGMENT, b, b2);
            if (codeFragment == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "expressionOrStatementsCodeFragment"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        return codeFragment;
    }
    
    @NotNull
    public static OCCodeFragment typeCodeFragment(@NotNull final String s, @NotNull final PsiElement psiElement) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/lang/util/OCElementFactory", "typeCodeFragment"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/util/OCElementFactory", "typeCodeFragment"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        OCCodeFragment typeCodeFragment;
        try {
            typeCodeFragment = typeCodeFragment(s, psiElement.getProject(), psiElement, true, false);
            if (typeCodeFragment == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "typeCodeFragment"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        return typeCodeFragment;
    }
    
    @NotNull
    public static OCCodeFragment typeCodeFragment(@NotNull final String s, @NotNull final Project project, @Nullable final PsiElement psiElement, final boolean b, final boolean b2) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/lang/util/OCElementFactory", "typeCodeFragment"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/util/OCElementFactory", "typeCodeFragment"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        OCCodeFragment codeFragment;
        try {
            codeFragment = codeFragment(s, project, psiElement, OCElementTypes.TYPE_CODE_FRAGMENT, b, b2);
            if (codeFragment == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "typeCodeFragment"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        return codeFragment;
    }
    
    public static OCCodeFragment getTypeCodeFragmentInWriteAction(final String s, @NotNull final Project project, @Nullable final PsiElement psiElement) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/util/OCElementFactory", "getTypeCodeFragmentInWriteAction"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (OCCodeFragment)new WriteCommandAction<OCCodeFragment>(project, new PsiFile[0]) {
            protected void run(@NotNull final Result<OCCodeFragment> result) throws Throwable {
                try {
                    if (result == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/util/OCElementFactory$1", "run"));
                    }
                }
                catch (Throwable t) {
                    throw a(t);
                }
                result.setResult((Object)OCElementFactory.typeCodeFragment(s, project, psiElement, true, true));
            }
            
            private static Throwable a(final Throwable t) {
                return t;
            }
        }.execute().getResultObject();
    }
    
    @NotNull
    public static OCCodeFragment codeFragment(@NotNull final String s, @NotNull final Project project, @Nullable final PsiElement psiElement, @NotNull final IFileElementType fileElementType, final boolean b, final boolean b2) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/lang/util/OCElementFactory", "codeFragment"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/util/OCElementFactory", "codeFragment"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        try {
            if (fileElementType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/util/OCElementFactory", "codeFragment"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        boolean cpp = false;
        if (psiElement != null) {
            final PsiFile containingFile = psiElement.getContainingFile();
            if (containingFile instanceof OCFile) {
                cpp = ((OCFile)containingFile).isCpp();
            }
        }
        OCCodeFragment codeFragment;
        try {
            codeFragment = codeFragment(s, project, psiElement, fileElementType, b, cpp, b2);
            if (codeFragment == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "codeFragment"));
            }
        }
        catch (IllegalStateException ex4) {
            throw a(ex4);
        }
        return codeFragment;
    }
    
    @NotNull
    public static OCCodeFragment codeFragment(@NotNull final String s, @NotNull final Project project, @Nullable final PsiElement psiElement, @NotNull final IFileElementType fileElementType, final boolean b, final boolean b2, final boolean b3) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/lang/util/OCElementFactory", "codeFragment"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/util/OCElementFactory", "codeFragment"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        try {
            if (fileElementType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/util/OCElementFactory", "codeFragment"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        OCCodeFragment codeFragment = null;
        Label_0158: {
            try {
                if (b2) {
                    final OCLanguageKind ocLanguageKind = OCLanguageKind.OBJ_CPP;
                    break Label_0158;
                }
            }
            catch (IllegalStateException ex4) {
                throw a(ex4);
            }
            final OCLanguageKind ocLanguageKind = OCLanguageKind.OBJ_C;
            try {
                codeFragment = codeFragment(s, project, psiElement, fileElementType, b, b3, ocLanguageKind);
                if (codeFragment == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "codeFragment"));
                }
            }
            catch (IllegalStateException ex5) {
                throw a(ex5);
            }
        }
        return codeFragment;
    }
    
    @NotNull
    public static OCCodeFragment codeFragment(@NotNull final String p0, @NotNull final Project p1, @Nullable final PsiElement p2, @NotNull final IFileElementType p3, final boolean p4, final boolean p5, @NotNull final OCLanguageKind p6) {
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
        //    18: ldc             "text"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/util/OCElementFactory"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "codeFragment"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
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
        //    62: ldc             "project"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/util/OCElementFactory"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "codeFragment"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    87: athrow         
        //    88: aload_3        
        //    89: ifnonnull       132
        //    92: new             Ljava/lang/IllegalArgumentException;
        //    95: dup            
        //    96: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    98: ldc             3
        //   100: anewarray       Ljava/lang/Object;
        //   103: dup            
        //   104: ldc             0
        //   106: ldc             "type"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/util/OCElementFactory"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "codeFragment"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   131: athrow         
        //   132: aload           6
        //   134: ifnonnull       177
        //   137: new             Ljava/lang/IllegalArgumentException;
        //   140: dup            
        //   141: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //   143: ldc             3
        //   145: anewarray       Ljava/lang/Object;
        //   148: dup            
        //   149: ldc             0
        //   151: ldc             "kind"
        //   153: aastore        
        //   154: dup            
        //   155: ldc             1
        //   157: ldc             "com/jetbrains/cidr/lang/util/OCElementFactory"
        //   159: aastore        
        //   160: dup            
        //   161: ldc             2
        //   163: ldc             "codeFragment"
        //   165: aastore        
        //   166: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   169: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   172: athrow         
        //   173: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   176: athrow         
        //   177: aload_0        
        //   178: invokevirtual   java/lang/String.trim:()Ljava/lang/String;
        //   181: astore_0       
        //   182: new             Lcom/jetbrains/cidr/lang/psi/impl/OCCodeFragmentImpl;
        //   185: dup            
        //   186: aload_1        
        //   187: aload           6
        //   189: aload_0        
        //   190: iload           4
        //   192: aload_3        
        //   193: invokespecial   com/jetbrains/cidr/lang/psi/impl/OCCodeFragmentImpl.<init>:(Lcom/intellij/openapi/project/Project;Lcom/jetbrains/cidr/lang/OCLanguageKind;Ljava/lang/CharSequence;ZLcom/intellij/psi/tree/IElementType;)V
        //   196: astore          7
        //   198: aload_2        
        //   199: ifnull          231
        //   202: aload_2        
        //   203: invokeinterface com/intellij/psi/PsiElement.isValid:()Z
        //   208: ifeq            231
        //   211: goto            218
        //   214: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   217: athrow         
        //   218: aload           7
        //   220: aload_2        
        //   221: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCCodeFragmentImpl.setContext:(Lcom/intellij/psi/PsiElement;)V
        //   224: goto            231
        //   227: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   230: athrow         
        //   231: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //   234: astore          8
        //   236: iload           5
        //   238: ifeq            300
        //   241: aload           8
        //   243: invokeinterface com/intellij/openapi/application/Application.isWriteAccessAllowed:()Z
        //   248: ifne            270
        //   251: goto            258
        //   254: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   257: athrow         
        //   258: iload           4
        //   260: ifne            292
        //   263: goto            270
        //   266: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   269: athrow         
        //   270: aload_1        
        //   271: invokestatic    com/intellij/psi/impl/source/PostprocessReformattingAspect.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/impl/source/PostprocessReformattingAspect;
        //   274: aload_1        
        //   275: aload           7
        //   277: invokedynamic   run:(Lcom/intellij/openapi/project/Project;Lcom/jetbrains/cidr/lang/psi/impl/OCCodeFragmentImpl;)Ljava/lang/Runnable;
        //   282: invokevirtual   com/intellij/psi/impl/source/PostprocessReformattingAspect.disablePostprocessFormattingInside:(Ljava/lang/Runnable;)V
        //   285: goto            300
        //   288: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   291: athrow         
        //   292: getstatic       com/jetbrains/cidr/lang/util/OCElementFactory.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //   295: ldc             "Write access is required"
        //   297: invokevirtual   com/intellij/openapi/diagnostic/Logger.error:(Ljava/lang/String;)V
        //   300: aload           7
        //   302: dup            
        //   303: ifnonnull       340
        //   306: new             Ljava/lang/IllegalStateException;
        //   309: dup            
        //   310: ldc             "@NotNull method %s.%s must not return null"
        //   312: ldc             2
        //   314: anewarray       Ljava/lang/Object;
        //   317: dup            
        //   318: ldc             0
        //   320: ldc             "com/jetbrains/cidr/lang/util/OCElementFactory"
        //   322: aastore        
        //   323: dup            
        //   324: ldc             1
        //   326: ldc             "codeFragment"
        //   328: aastore        
        //   329: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   332: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   335: athrow         
        //   336: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   339: athrow         
        //   340: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      40     40     44     Ljava/lang/IllegalStateException;
        //  44     84     84     88     Ljava/lang/IllegalStateException;
        //  88     128    128    132    Ljava/lang/IllegalStateException;
        //  132    173    173    177    Ljava/lang/IllegalStateException;
        //  198    211    214    218    Ljava/lang/IllegalStateException;
        //  202    224    227    231    Ljava/lang/IllegalStateException;
        //  236    251    254    258    Ljava/lang/IllegalStateException;
        //  241    263    266    270    Ljava/lang/IllegalStateException;
        //  258    288    288    292    Ljava/lang/IllegalStateException;
        //  300    336    336    340    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0258:
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
    public static PsiElement create(@NotNull final OCElementType ocElementType, @NotNull final PsiElement psiElement) {
        try {
            if (ocElementType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/util/OCElementFactory", "create"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/util/OCElementFactory", "create"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        final OCCodeFragment codeFragment = codeFragment(ocElementType.getName(), psiElement.getProject(), psiElement, true, false);
        PsiElement childOfType;
        try {
            childOfType = PsiTreeUtil.findChildOfType((PsiElement)codeFragment, (Class)LeafPsiElement.class);
            if (childOfType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "create"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        return childOfType;
    }
    
    @NotNull
    public static PsiElement spaceFromText(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/util/OCElementFactory", "spaceFromText"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        PsiElement psiElement2;
        try {
            psiElement2 = OCElementUtil.getAllChildren(topLevelDeclarationFromText("a b", psiElement)).get(1);
            if (psiElement2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "spaceFromText"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return psiElement2;
    }
    
    @NotNull
    public static PsiElement newlineFromText(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/util/OCElementFactory", "newlineFromText"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        PsiElement psiElement2;
        try {
            psiElement2 = OCElementUtil.getAllChildren(topLevelDeclarationFromText("a\nb", psiElement)).get(1);
            if (psiElement2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementFactory", "newlineFromText"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return psiElement2;
    }
    
    public static void initIndentFromContext(@NotNull final PsiElement psiElement, @NotNull final PsiElement psiElement2) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "oldElement", "com/jetbrains/cidr/lang/util/OCElementFactory", "initIndentFromContext"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (psiElement2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "newElement", "com/jetbrains/cidr/lang/util/OCElementFactory", "initIndentFromContext"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        final PsiFile containingFile = psiElement.getContainingFile();
        CodeEditUtil.setOldIndentation((TreeElement)psiElement2.getNode(), IndentHelper.getInstance().getIndent(containingFile.getProject(), containingFile.getFileType(), psiElement.getNode()));
    }
    
    static {
        LOG = Logger.getInstance("#com.jetbrains.cidr.lang.util.OCElementFactory");
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
