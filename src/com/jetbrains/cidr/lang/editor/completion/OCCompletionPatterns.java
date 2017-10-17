// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.jetbrains.cidr.lang.psi.impl.OCImportModuleStatementImpl;
import com.jetbrains.cidr.lang.psi.OCStruct;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;
import com.jetbrains.cidr.lang.psi.OCIfStatement;
import com.jetbrains.cidr.lang.psi.OCAvailabilityExpression;
import com.jetbrains.cidr.lang.psi.OCGenericSelectionAssociation;
import com.jetbrains.cidr.lang.psi.OCSwitchStatement;
import com.jetbrains.cidr.lang.psi.OCForStatement;
import com.jetbrains.cidr.lang.psi.OCDeclarationOrExpression;
import com.jetbrains.cidr.lang.psi.OCLoopStatement;
import com.jetbrains.cidr.lang.psi.OCProperty;
import com.jetbrains.cidr.lang.psi.OCPropertyAttribute;
import com.jetbrains.cidr.lang.psi.OCMessageArgument;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import com.jetbrains.cidr.lang.psi.OCDeclarationStatement;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.psi.OCCppNamespace;
import com.jetbrains.cidr.lang.psi.OCStructLike;
import com.jetbrains.cidr.lang.psi.OCParenthesizedExpression;
import com.jetbrains.cidr.lang.psi.OCTemplateArgumentList;
import com.jetbrains.cidr.lang.psi.OCArgumentList;
import com.jetbrains.cidr.lang.psi.OCExpressionStatement;
import com.jetbrains.cidr.lang.psi.impl.OCObjCErrorKeywordImpl;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.psi.OCLiteralExpression;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.lang.Language;
import com.jetbrains.cidr.lang.OCLanguage;
import com.intellij.psi.PsiErrorElement;
import com.intellij.patterns.StandardPatterns;
import com.jetbrains.cidr.lang.psi.OCCatchSection;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCTryStatement;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.intellij.patterns.PlatformPatterns;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.jetbrains.cidr.lang.psi.OCTypeElement;
import com.intellij.patterns.PatternCondition;
import com.intellij.patterns.ElementPattern;
import com.intellij.psi.PsiElement;
import com.intellij.patterns.PsiElementPattern;

public class OCCompletionPatterns
{
    public static final PsiElementPattern.Capture<PsiElement> ELEMENT;
    public static final ElementPattern<PsiElement> TOP_LEVEL;
    public static final PsiElementPattern.Capture<PsiElement> TOP_LEVEL_ELEMENT;
    public static final PsiElementPattern.Capture<PsiElement> REFERENCE_ELEMENT;
    public static final PsiElementPattern.Capture<PsiElement> LITERAL_EXPRESSION;
    public static final PatternCondition<PsiElement> CPP_ELEMENT_PATTERN;
    public static final PatternCondition<PsiElement> C_OR_OBJC_ELEMENT_PATTERN;
    public static final PatternCondition<PsiElement> OBJC_ELEMENT_PATTERN;
    private static final PsiElementPattern.Capture<OCTypeElement> TYPE_DECLARATION;
    public static final PsiElementPattern.Capture<PsiElement> TILDE;
    public static final PsiElementPattern.Capture<PsiElement> AFTER_TILDE;
    public static final PsiElementPattern.Capture<PsiElement> WITH_QUALIFIER;
    public static final PsiElementPattern.Capture<PsiElement> AFTER_DOT;
    public static final PsiElementPattern.Capture<PsiElement> AT;
    public static final PsiElementPattern.Capture<PsiElement> WHITESPACES_AND_COMMENTS;
    public static final PsiElementPattern.Capture<PsiElement> OBJC_ERROR_KEYWORD;
    public static final PsiElementPattern.Capture<PsiElement> TYPE_IN_DECLARATION;
    public static final PsiElementPattern.Capture<PsiElement> SIMPLE_TYPE_IN_DECLARATION;
    public static final PsiElementPattern.Capture<PsiElement> TYPE_IN_CAST;
    public static final PsiElementPattern.Capture<PsiElement> SIMPLE_TYPE_IN_CAST;
    public static final PsiElementPattern.Capture<PsiElement> SIMPLE_TYPE_IN_TOP_LEVEL_DECLARATION;
    public static final PsiElementPattern.Capture<PsiElement> SIMPLE_TYPE_IN_NON_BLOCK_DECLARATION;
    public static final PsiElementPattern.Capture<PsiElement> SIMPLE_TYPE_IN_NAMESPACE_DECLARATION;
    public static final PsiElementPattern.Capture<PsiElement> IDENTIFIER;
    public static final PsiElementPattern.Capture<PsiElement> NAME_IN_DECLARATION;
    public static final PsiElementPattern.Capture<PsiElement> NAME_IN_CLASS_DECLARATION;
    public static final PsiElementPattern.Capture<PsiElement> OC_ELEMENT_CAPTURE;
    public static final PsiElementPattern.Capture<OCTypeElement> DECLARATION_IN_DECLARATION_STATEMENT;
    public static final PsiElementPattern.Capture<OCReferenceExpression> REFERENCE_EXPRESSION_IN_EXPRESSION_STATEMENT;
    public static final PsiElementPattern.Capture<PsiElement> AT_STATEMENT_LEVEL;
    public static final PsiElementPattern.Capture<PsiElement> AT_EXPRESSION_LEVEL;
    public static final PsiElementPattern.Capture<PsiElement> AT_ANY_PROPERTY_ATTRIBUTE;
    public static final PsiElementPattern.Capture<PsiElement> AT_PROPERTY_ATTRIBUTE;
    public static final PsiElementPattern.Capture<PsiElement> IN_STRUCT;
    public static final PsiElementPattern.Capture<PsiElement> IN_CPP_STRUCT;
    public static final PsiElementPattern.Capture<PsiElement> IN_CPP_NAMESPACE;
    public static final PsiElementPattern.Capture<PsiElement> IN_LOOP;
    public static final PsiElementPattern.Capture<PsiElement> IN_FOR;
    public static final PsiElementPattern.Capture<PsiElement> IN_SWITCH;
    public static final PsiElementPattern.Capture<PsiElement> IN_GENERIC_ASSOCIATION;
    public static final PsiElementPattern.Capture<PsiElement> IN_AVAILABILITY_EXPRESSION;
    public static final ElementPattern<PsiElement> ELSE_POSITION;
    public static final PsiElementPattern.Capture<PsiElement> AT_CLASS_FUNCTION_BODY_PLACEHOLDER;
    public static final PsiElementPattern.Capture<PsiElement> AT_IMPORT_MODULE_STATEMENT;
    
    private static PsiElementPattern.Capture<PsiElement> a(final ElementPattern<PsiElement> elementPattern) {
        return (PsiElementPattern.Capture<PsiElement>)((PsiElementPattern.Capture)((PsiElementPattern.Capture)PlatformPatterns.psiElement().andOr(new ElementPattern[] { PlatformPatterns.psiElement().withSuperParent(2, (ElementPattern)PlatformPatterns.psiElement((Class)OCTypeElement.class).withSuperParent(2, (ElementPattern)elementPattern)), PlatformPatterns.psiElement().withParent((ElementPattern)((PsiElementPattern.Capture)PlatformPatterns.psiElement((Class)OCDeclarator.class).afterSibling((ElementPattern)OCCompletionPatterns.TYPE_DECLARATION)).withSuperParent(2, (ElementPattern)elementPattern)) })).andNot((ElementPattern)OCCompletionPatterns.WITH_QUALIFIER)).andNot((ElementPattern)OCCompletionPatterns.AFTER_DOT);
    }
    
    public static PsiElementPattern.Capture<PsiElement> afterTry(final boolean b) {
        return (PsiElementPattern.Capture<PsiElement>)PlatformPatterns.psiElement().afterLeaf((ElementPattern)((PsiElementPattern.Capture)PlatformPatterns.psiElement().withText("}")).withSuperParent(2, StandardPatterns.or(new ElementPattern[] { (PsiElementPattern.Capture)PlatformPatterns.psiElement((Class)OCTryStatement.class).with((PatternCondition)new PatternCondition<OCTryStatement>("fromLanguage") {
                public boolean accepts(@NotNull final OCTryStatement ocTryStatement, final ProcessingContext processingContext) {
                    try {
                        if (ocTryStatement == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "statement", "com/jetbrains/cidr/lang/editor/completion/OCCompletionPatterns$4", "accepts"));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        if (ocTryStatement.isCppStatement() == b) {
                            return true;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    return false;
                }
                
                private static IllegalArgumentException a(final IllegalArgumentException ex) {
                    return ex;
                }
            }), (PsiElementPattern.Capture)PlatformPatterns.psiElement((Class)OCCatchSection.class).with((PatternCondition)new PatternCondition<OCCatchSection>("fromLanguage") {
                public boolean accepts(@NotNull final OCCatchSection ocCatchSection, final ProcessingContext processingContext) {
                    try {
                        if (ocCatchSection == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "section", "com/jetbrains/cidr/lang/editor/completion/OCCompletionPatterns$5", "accepts"));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        if (ocCatchSection.isCppStatement() == b) {
                            return true;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    return false;
                }
                
                private static IllegalArgumentException a(final IllegalArgumentException ex) {
                    return ex;
                }
            }) })));
    }
    
    public static ElementPattern<PsiElement> like(final Class<? extends PsiElement> clazz) {
        return (ElementPattern<PsiElement>)StandardPatterns.or(new ElementPattern[] { PlatformPatterns.psiElement((Class)clazz), PlatformPatterns.psiElement((Class)PsiErrorElement.class).withParent((Class)clazz) });
    }
    
    static {
        ELEMENT = (PsiElementPattern.Capture)PlatformPatterns.psiElement().withLanguage((Language)OCLanguage.getInstance());
        TOP_LEVEL = like((Class<? extends PsiElement>)OCFile.class);
        TOP_LEVEL_ELEMENT = (PsiElementPattern.Capture)OCCompletionPatterns.ELEMENT.withParent((ElementPattern)OCCompletionPatterns.TOP_LEVEL);
        REFERENCE_ELEMENT = (PsiElementPattern.Capture)OCCompletionPatterns.ELEMENT.withParent((Class)OCReferenceElement.class);
        LITERAL_EXPRESSION = (PsiElementPattern.Capture)OCCompletionPatterns.ELEMENT.withParent((Class)OCLiteralExpression.class);
        CPP_ELEMENT_PATTERN = new PatternCondition<PsiElement>("CPP_ELEMENT") {
            public boolean accepts(@NotNull final PsiElement psiElement, final ProcessingContext processingContext) {
                try {
                    if (psiElement == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "psiElement", "com/jetbrains/cidr/lang/editor/completion/OCCompletionPatterns$1", "accepts"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                final PsiFile originalFile = psiElement.getContainingFile().getOriginalFile();
                Label_0087: {
                    try {
                        if (!(originalFile instanceof OCFile)) {
                            return false;
                        }
                        final OCFile ocFile = (OCFile)originalFile;
                        final OCFile ocFile2 = ocFile;
                        final OCLanguageKind ocLanguageKind = ocFile2.getKind();
                        final boolean b = ocLanguageKind.isCpp();
                        if (b) {
                            break Label_0087;
                        }
                        return false;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    try {
                        final OCFile ocFile = (OCFile)originalFile;
                        final OCFile ocFile2 = ocFile;
                        final OCLanguageKind ocLanguageKind = ocFile2.getKind();
                        final boolean b = ocLanguageKind.isCpp();
                        if (b) {
                            return true;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                }
                return false;
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        };
        C_OR_OBJC_ELEMENT_PATTERN = new PatternCondition<PsiElement>("C_OBJC_ELEMENT") {
            public boolean accepts(@NotNull final PsiElement p0, final ProcessingContext p1) {
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
                //    18: ldc             "psiElement"
                //    20: aastore        
                //    21: dup            
                //    22: ldc             1
                //    24: ldc             "com/jetbrains/cidr/lang/editor/completion/OCCompletionPatterns$2"
                //    26: aastore        
                //    27: dup            
                //    28: ldc             2
                //    30: ldc             "accepts"
                //    32: aastore        
                //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
                //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
                //    39: athrow         
                //    40: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCCompletionPatterns$2.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    43: athrow         
                //    44: aload_1        
                //    45: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
                //    50: invokeinterface com/intellij/psi/PsiFile.getOriginalFile:()Lcom/intellij/psi/PsiFile;
                //    55: astore_3       
                //    56: aload_3        
                //    57: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
                //    60: ifeq            115
                //    63: aload_3        
                //    64: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
                //    67: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getKind:()Lcom/jetbrains/cidr/lang/OCLanguageKind;
                //    72: getstatic       com/jetbrains/cidr/lang/OCLanguageKind.C:Lcom/jetbrains/cidr/lang/OCLanguageKind;
                //    75: if_acmpeq       107
                //    78: goto            85
                //    81: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCCompletionPatterns$2.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    84: athrow         
                //    85: aload_3        
                //    86: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
                //    89: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getKind:()Lcom/jetbrains/cidr/lang/OCLanguageKind;
                //    94: getstatic       com/jetbrains/cidr/lang/OCLanguageKind.OBJ_C:Lcom/jetbrains/cidr/lang/OCLanguageKind;
                //    97: if_acmpne       115
                //   100: goto            107
                //   103: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCCompletionPatterns$2.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   106: athrow         
                //   107: iconst_1       
                //   108: goto            116
                //   111: invokestatic    com/jetbrains/cidr/lang/editor/completion/OCCompletionPatterns$2.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   114: athrow         
                //   115: iconst_0       
                //   116: ireturn        
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                                
                //  -----  -----  -----  -----  ------------------------------------
                //  0      40     40     44     Ljava/lang/IllegalArgumentException;
                //  56     78     81     85     Ljava/lang/IllegalArgumentException;
                //  63     100    103    107    Ljava/lang/IllegalArgumentException;
                //  85     111    111    115    Ljava/lang/IllegalArgumentException;
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
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        };
        OBJC_ELEMENT_PATTERN = new PatternCondition<PsiElement>("OBJC_ELEMENT") {
            public boolean accepts(@NotNull final PsiElement psiElement, final ProcessingContext processingContext) {
                try {
                    if (psiElement == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "psiElement", "com/jetbrains/cidr/lang/editor/completion/OCCompletionPatterns$3", "accepts"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                final PsiFile originalFile = psiElement.getContainingFile().getOriginalFile();
                Label_0087: {
                    try {
                        if (!(originalFile instanceof OCFile)) {
                            return false;
                        }
                        final OCFile ocFile = (OCFile)originalFile;
                        final OCFile ocFile2 = ocFile;
                        final OCLanguageKind ocLanguageKind = ocFile2.getKind();
                        final boolean b = ocLanguageKind.isObjC();
                        if (b) {
                            break Label_0087;
                        }
                        return false;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    try {
                        final OCFile ocFile = (OCFile)originalFile;
                        final OCFile ocFile2 = ocFile;
                        final OCLanguageKind ocLanguageKind = ocFile2.getKind();
                        final boolean b = ocLanguageKind.isObjC();
                        if (b) {
                            return true;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                }
                return false;
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        };
        TYPE_DECLARATION = (PsiElementPattern.Capture)PlatformPatterns.psiElement((Class)OCTypeElement.class).withChild((ElementPattern)PlatformPatterns.psiElement((IElementType)OCElementTypes.EMPTY_NAME));
        TILDE = (PsiElementPattern.Capture)PlatformPatterns.psiElement().withText("~");
        AFTER_TILDE = (PsiElementPattern.Capture)PlatformPatterns.psiElement().afterLeaf((ElementPattern)OCCompletionPatterns.TILDE);
        WITH_QUALIFIER = (PsiElementPattern.Capture)PlatformPatterns.psiElement().afterLeafSkipping((ElementPattern)OCCompletionPatterns.TILDE, (ElementPattern)PlatformPatterns.psiElement().withText("::"));
        AFTER_DOT = (PsiElementPattern.Capture)PlatformPatterns.psiElement().afterLeaf((ElementPattern)PlatformPatterns.psiElement().withText((ElementPattern)StandardPatterns.string().endsWith(".")));
        AT = (PsiElementPattern.Capture)PlatformPatterns.psiElement().withText((ElementPattern)StandardPatterns.string().endsWith("@"));
        WHITESPACES_AND_COMMENTS = (PsiElementPattern.Capture)PlatformPatterns.psiElement().withElementType(OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET);
        OBJC_ERROR_KEYWORD = (PsiElementPattern.Capture)((PsiElementPattern.Capture)((PsiElementPattern.Capture)PlatformPatterns.psiElement().with((PatternCondition)OCCompletionPatterns.OBJC_ELEMENT_PATTERN)).afterLeafSkipping((ElementPattern)OCCompletionPatterns.WHITESPACES_AND_COMMENTS, (ElementPattern)OCCompletionPatterns.AT)).withParent((Class)OCObjCErrorKeywordImpl.class);
        TYPE_IN_DECLARATION = (PsiElementPattern.Capture)((PsiElementPattern.Capture)PlatformPatterns.psiElement().andOr(new ElementPattern[] { PlatformPatterns.psiElement().withSuperParent(2, (Class)OCTypeElement.class), ((PsiElementPattern.Capture)PlatformPatterns.psiElement().withSuperParent(2, (Class)OCReferenceExpression.class)).withSuperParent(3, (Class)OCExpressionStatement.class), PlatformPatterns.psiElement().withParent((ElementPattern)PlatformPatterns.psiElement((Class)OCDeclarator.class).afterSibling((ElementPattern)OCCompletionPatterns.TYPE_DECLARATION)), PlatformPatterns.psiElement().withSuperParent(2, (ElementPattern)PlatformPatterns.psiElement((Class)OCReferenceExpression.class).withParent((ElementPattern)PlatformPatterns.psiElement((Class)OCArgumentList.class).withParent((Class)OCDeclarator.class))), ((PsiElementPattern.Capture)((PsiElementPattern.Capture)((PsiElementPattern.Capture)PlatformPatterns.psiElement().withSuperParent(2, (Class)OCReferenceExpression.class)).withSuperParent(3, (Class)OCTemplateArgumentList.class)).andNot((ElementPattern)OCCompletionPatterns.WITH_QUALIFIER)).andNot((ElementPattern)OCCompletionPatterns.AFTER_DOT) })).andNot((ElementPattern)OCNewExpressionCompletionContributor.TYPE_IN_NEW_EXPRESSION);
        SIMPLE_TYPE_IN_DECLARATION = (PsiElementPattern.Capture)((PsiElementPattern.Capture)OCCompletionPatterns.TYPE_IN_DECLARATION.andNot((ElementPattern)OCCompletionPatterns.WITH_QUALIFIER)).andNot((ElementPattern)OCCompletionPatterns.AFTER_DOT);
        TYPE_IN_CAST = (PsiElementPattern.Capture)((PsiElementPattern.Capture)PlatformPatterns.psiElement().withSuperParent(2, (ElementPattern)PlatformPatterns.psiElement((Class)OCReferenceExpression.class))).withSuperParent(3, (Class)OCParenthesizedExpression.class);
        SIMPLE_TYPE_IN_CAST = (PsiElementPattern.Capture)((PsiElementPattern.Capture)OCCompletionPatterns.TYPE_IN_CAST.andNot((ElementPattern)OCCompletionPatterns.WITH_QUALIFIER)).andNot((ElementPattern)OCCompletionPatterns.AFTER_DOT);
        SIMPLE_TYPE_IN_TOP_LEVEL_DECLARATION = a(OCCompletionPatterns.TOP_LEVEL);
        SIMPLE_TYPE_IN_NON_BLOCK_DECLARATION = a((ElementPattern<PsiElement>)StandardPatterns.or(new ElementPattern[] { OCCompletionPatterns.TOP_LEVEL, like((Class<? extends PsiElement>)OCStructLike.class), like((Class<? extends PsiElement>)OCCppNamespace.class) }));
        SIMPLE_TYPE_IN_NAMESPACE_DECLARATION = a(like((Class<? extends PsiElement>)OCCppNamespace.class));
        IDENTIFIER = PlatformPatterns.psiElement((IElementType)OCTokenTypes.IDENTIFIER);
        NAME_IN_DECLARATION = (PsiElementPattern.Capture)((PsiElementPattern.Capture)((PsiElementPattern.Capture)OCCompletionPatterns.IDENTIFIER.andNot((ElementPattern)OCCompletionPatterns.WITH_QUALIFIER)).withParent((Class)OCDeclarator.class)).andNot((ElementPattern)OCCompletionPatterns.TYPE_IN_DECLARATION);
        NAME_IN_CLASS_DECLARATION = (PsiElementPattern.Capture)OCCompletionPatterns.IDENTIFIER.withParent((Class)OCClassDeclaration.class);
        OC_ELEMENT_CAPTURE = (PsiElementPattern.Capture)((PsiElementPattern.Capture)((PsiElementPattern.Capture)PlatformPatterns.psiElement().withLanguage((Language)OCLanguage.getInstance())).andNot((ElementPattern)PlatformPatterns.psiElement().afterLeaf(new String[] { "::" }))).andNot((ElementPattern)OCCompletionPatterns.AFTER_DOT);
        DECLARATION_IN_DECLARATION_STATEMENT = (PsiElementPattern.Capture)PlatformPatterns.psiElement((Class)OCTypeElement.class).withSuperParent(2, (Class)OCDeclarationStatement.class);
        REFERENCE_EXPRESSION_IN_EXPRESSION_STATEMENT = (PsiElementPattern.Capture)PlatformPatterns.psiElement((Class)OCReferenceExpression.class).withParent((Class)OCExpressionStatement.class);
        AT_STATEMENT_LEVEL = (PsiElementPattern.Capture)((PsiElementPattern.Capture)OCCompletionPatterns.OC_ELEMENT_CAPTURE.inside((Class)OCBlockStatement.class)).andOr(new ElementPattern[] { PlatformPatterns.psiElement().withParent((ElementPattern)PlatformPatterns.psiElement((IElementType)OCElementTypes.REFERENCE_ELEMENT).withParent(StandardPatterns.or(new ElementPattern[] { OCCompletionPatterns.DECLARATION_IN_DECLARATION_STATEMENT, OCCompletionPatterns.REFERENCE_EXPRESSION_IN_EXPRESSION_STATEMENT }))), ((PsiElementPattern.Capture)PlatformPatterns.psiElement().withParent((Class)PsiErrorElement.class)).andNot((ElementPattern)PlatformPatterns.psiElement().withSuperParent(2, (Class)OCMessageArgument.class)) });
        AT_EXPRESSION_LEVEL = (PsiElementPattern.Capture)OCCompletionPatterns.OC_ELEMENT_CAPTURE.withParent((ElementPattern)PlatformPatterns.psiElement((IElementType)OCElementTypes.REFERENCE_ELEMENT).withParent((ElementPattern)PlatformPatterns.psiElement((IElementType)OCElementTypes.REFERENCE_EXPRESSION)));
        AT_ANY_PROPERTY_ATTRIBUTE = (PsiElementPattern.Capture)((PsiElementPattern.Capture)OCCompletionPatterns.OC_ELEMENT_CAPTURE.withParent((ElementPattern)like((Class<? extends PsiElement>)OCPropertyAttribute.class))).andNot((ElementPattern)OCCompletionPatterns.OC_ELEMENT_CAPTURE.afterLeaf((ElementPattern)PlatformPatterns.psiElement().withText("=")));
        AT_PROPERTY_ATTRIBUTE = (PsiElementPattern.Capture)((PsiElementPattern.Capture)OCCompletionPatterns.OC_ELEMENT_CAPTURE.withSuperParent(3, (Class)OCProperty.class)).and((ElementPattern)OCCompletionPatterns.AT_ANY_PROPERTY_ATTRIBUTE);
        IN_STRUCT = (PsiElementPattern.Capture)((PsiElementPattern.Capture)OCCompletionPatterns.SIMPLE_TYPE_IN_DECLARATION.inside((Class)OCStructLike.class)).andNot((ElementPattern)OCCompletionPatterns.AT_STATEMENT_LEVEL);
        IN_CPP_STRUCT = (PsiElementPattern.Capture)OCCompletionPatterns.IN_STRUCT.with((PatternCondition)OCCompletionPatterns.CPP_ELEMENT_PATTERN);
        IN_CPP_NAMESPACE = (PsiElementPattern.Capture)((PsiElementPattern.Capture)OCCompletionPatterns.SIMPLE_TYPE_IN_DECLARATION.with((PatternCondition)OCCompletionPatterns.CPP_ELEMENT_PATTERN)).withSuperParent(3, StandardPatterns.or(new ElementPattern[] { PlatformPatterns.psiElement((Class)OCCppNamespace.class), PlatformPatterns.psiElement((Class)OCFile.class) }));
        IN_LOOP = (PsiElementPattern.Capture)OCCompletionPatterns.AT_STATEMENT_LEVEL.inside((Class)OCLoopStatement.class);
        IN_FOR = (PsiElementPattern.Capture)((PsiElementPattern.Capture)OCCompletionPatterns.OC_ELEMENT_CAPTURE.withSuperParent(3, (Class)OCDeclarationOrExpression.class)).withSuperParent(4, (Class)OCForStatement.class);
        IN_SWITCH = (PsiElementPattern.Capture)OCCompletionPatterns.AT_STATEMENT_LEVEL.inside((Class)OCSwitchStatement.class);
        IN_GENERIC_ASSOCIATION = (PsiElementPattern.Capture)((PsiElementPattern.Capture)OCCompletionPatterns.ELEMENT.inside((Class)OCGenericSelectionAssociation.class)).inside((Class)OCTypeElement.class);
        IN_AVAILABILITY_EXPRESSION = (PsiElementPattern.Capture)OCCompletionPatterns.ELEMENT.inside((Class)OCAvailabilityExpression.class);
        ELSE_POSITION = StandardPatterns.or(new ElementPattern[] { PlatformPatterns.psiElement().afterLeaf((ElementPattern)((PsiElementPattern.Capture)PlatformPatterns.psiElement().withText("}")).withSuperParent(2, (Class)OCIfStatement.class)), PlatformPatterns.psiElement().afterLeaf((ElementPattern)((PsiElementPattern.Capture)PlatformPatterns.psiElement().withText(";")).withSuperParent(1, (Class)OCIfStatement.class)), PlatformPatterns.psiElement().afterLeaf((ElementPattern)((PsiElementPattern.Capture)PlatformPatterns.psiElement().withText(";")).withSuperParent(2, (Class)OCIfStatement.class)), PlatformPatterns.psiElement().afterLeaf((ElementPattern)((PsiElementPattern.Capture)PlatformPatterns.psiElement().withText(";")).withSuperParent(3, (Class)OCIfStatement.class)) });
        AT_CLASS_FUNCTION_BODY_PLACEHOLDER = (PsiElementPattern.Capture)((PsiElementPattern.Capture)PlatformPatterns.psiElement().withSuperParent(4, (ElementPattern)PlatformPatterns.psiElement((Class)OCFunctionDeclaration.class).withParent((Class)OCStruct.class))).afterLeafSkipping((ElementPattern)OCCompletionPatterns.WHITESPACES_AND_COMMENTS, (ElementPattern)PlatformPatterns.psiElement((IElementType)OCTokenTypes.EQ));
        AT_IMPORT_MODULE_STATEMENT = (PsiElementPattern.Capture)OCCompletionPatterns.ELEMENT.withParent((Class)OCImportModuleStatementImpl.class);
    }
}
