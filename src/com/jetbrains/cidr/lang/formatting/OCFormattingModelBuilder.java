// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.formatting;

import com.intellij.openapi.editor.Document;
import com.intellij.formatting.FormattingDocumentModel;
import com.intellij.psi.formatter.FormattingDocumentModelImpl;
import com.intellij.formatting.Spacing;
import com.intellij.formatting.Block;
import java.util.List;
import com.intellij.formatting.Alignment;
import com.intellij.formatting.Wrap;
import com.intellij.psi.formatter.common.AbstractBlock;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.intellij.openapi.util.TextRange;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.intellij.formatting.FormatTextRanges;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.formatting.FormattingMode;
import com.intellij.formatting.FormattingModel;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.PsiElement;
import java.util.concurrent.atomic.AtomicInteger;
import com.intellij.openapi.util.Key;
import com.intellij.formatting.FormattingModelBuilderEx;

public class OCFormattingModelBuilder implements FormattingModelBuilderEx
{
    public static final Key<Boolean> ALWAYS_CREATE_FULL_MODEL;
    private static final ThreadLocal<AtomicInteger> myAlwaysCreateFullModel;
    
    @NotNull
    public FormattingModel createModel(final PsiElement psiElement, final CodeStyleSettings codeStyleSettings) {
        FormattingModel model;
        try {
            model = this.createModel(psiElement, codeStyleSettings, FormattingMode.REFORMAT);
            if (model == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCFormattingModelBuilder", "createModel"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return model;
    }
    
    @NotNull
    @Override
    public FormattingModel createModel(@NotNull final PsiElement p0, @NotNull final CodeStyleSettings p1, @NotNull final FormattingMode p2) {
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
        //    18: ldc             "element"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/formatting/OCFormattingModelBuilder"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "createModel"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormattingModelBuilder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    43: athrow         
        //    44: aload_2        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "settings"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/formatting/OCFormattingModelBuilder"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "createModel"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormattingModelBuilder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
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
        //   106: ldc             "mode"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/formatting/OCFormattingModelBuilder"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "createModel"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormattingModelBuilder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   131: athrow         
        //   132: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormattingModelBuilder.a:()Z
        //   135: ifne            163
        //   138: getstatic       java/lang/Boolean.TRUE:Ljava/lang/Boolean;
        //   141: aload_1        
        //   142: getstatic       com/jetbrains/cidr/lang/formatting/OCFormattingModelBuilder.ALWAYS_CREATE_FULL_MODEL:Lcom/intellij/openapi/util/Key;
        //   145: invokeinterface com/intellij/psi/PsiElement.getUserData:(Lcom/intellij/openapi/util/Key;)Ljava/lang/Object;
        //   150: invokevirtual   java/lang/Boolean.equals:(Ljava/lang/Object;)Z
        //   153: ifeq            171
        //   156: goto            163
        //   159: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormattingModelBuilder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   162: athrow         
        //   163: iconst_1       
        //   164: goto            172
        //   167: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormattingModelBuilder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   170: athrow         
        //   171: iconst_0       
        //   172: istore          4
        //   174: iload           4
        //   176: ifne            251
        //   179: aload_1        
        //   180: invokeinterface com/intellij/psi/PsiElement.getProject:()Lcom/intellij/openapi/project/Project;
        //   185: invokestatic    com/intellij/openapi/project/DumbService.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/openapi/project/DumbService;
        //   188: invokevirtual   com/intellij/openapi/project/DumbService.isDumb:()Z
        //   191: ifeq            251
        //   194: goto            201
        //   197: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormattingModelBuilder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   200: athrow         
        //   201: aload_1        
        //   202: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormattingModelBuilder.a:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/formatting/FormattingModel;
        //   205: dup            
        //   206: ifnonnull       250
        //   209: goto            216
        //   212: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormattingModelBuilder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   215: athrow         
        //   216: new             Ljava/lang/IllegalStateException;
        //   219: dup            
        //   220: ldc             "@NotNull method %s.%s must not return null"
        //   222: ldc             2
        //   224: anewarray       Ljava/lang/Object;
        //   227: dup            
        //   228: ldc             0
        //   230: ldc             "com/jetbrains/cidr/lang/formatting/OCFormattingModelBuilder"
        //   232: aastore        
        //   233: dup            
        //   234: ldc             1
        //   236: ldc             "createModel"
        //   238: aastore        
        //   239: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   242: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   245: athrow         
        //   246: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormattingModelBuilder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   249: athrow         
        //   250: areturn        
        //   251: aload_1        
        //   252: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   257: astore          5
        //   259: new             Lcom/jetbrains/cidr/lang/formatting/OCPsiBasedFormattingModel;
        //   262: dup            
        //   263: aload           5
        //   265: aload_2        
        //   266: aload_3        
        //   267: invokespecial   com/jetbrains/cidr/lang/formatting/OCPsiBasedFormattingModel.<init>:(Lcom/intellij/psi/PsiFile;Lcom/intellij/psi/codeStyle/CodeStyleSettings;Lcom/intellij/formatting/FormattingMode;)V
        //   270: dup            
        //   271: ifnonnull       308
        //   274: new             Ljava/lang/IllegalStateException;
        //   277: dup            
        //   278: ldc             "@NotNull method %s.%s must not return null"
        //   280: ldc             2
        //   282: anewarray       Ljava/lang/Object;
        //   285: dup            
        //   286: ldc             0
        //   288: ldc             "com/jetbrains/cidr/lang/formatting/OCFormattingModelBuilder"
        //   290: aastore        
        //   291: dup            
        //   292: ldc             1
        //   294: ldc             "createModel"
        //   296: aastore        
        //   297: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   300: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   303: athrow         
        //   304: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormattingModelBuilder.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   307: athrow         
        //   308: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      40     40     44     Ljava/lang/IllegalStateException;
        //  44     84     84     88     Ljava/lang/IllegalStateException;
        //  88     128    128    132    Ljava/lang/IllegalStateException;
        //  132    156    159    163    Ljava/lang/IllegalStateException;
        //  138    167    167    171    Ljava/lang/IllegalStateException;
        //  174    194    197    201    Ljava/lang/IllegalStateException;
        //  179    209    212    216    Ljava/lang/IllegalStateException;
        //  201    246    246    250    Ljava/lang/IllegalStateException;
        //  259    304    304    308    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0201:
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
    @Override
    public CommonCodeStyleSettings.IndentOptions getIndentOptionsToUse(@NotNull final PsiFile psiFile, @NotNull final FormatTextRanges formatTextRanges, @NotNull final CodeStyleSettings codeStyleSettings) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/formatting/OCFormattingModelBuilder", "getIndentOptionsToUse"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (formatTextRanges == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ranges", "com/jetbrains/cidr/lang/formatting/OCFormattingModelBuilder", "getIndentOptionsToUse"));
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        try {
            if (codeStyleSettings == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/lang/formatting/OCFormattingModelBuilder", "getIndentOptionsToUse"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        return null;
    }
    
    public TextRange getRangeAffectingIndent(final PsiFile psiFile, final int n, final ASTNode astNode) {
        final OCElement ocElement = (OCElement)PsiTreeUtil.getNonStrictParentOfType(psiFile.findElementAt(n), new Class[] { OCMethod.class, OCExpression.class });
        try {
            if (ocElement != null) {
                return ocElement.getTextRange();
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return null;
    }
    
    public static void requestAlwaysCreateFullModel() {
        OCFormattingModelBuilder.myAlwaysCreateFullModel.get().incrementAndGet();
    }
    
    public static void releaseAlwaysCreateFullModel() {
        OCFormattingModelBuilder.myAlwaysCreateFullModel.get().decrementAndGet();
    }
    
    private static boolean a() {
        try {
            if (OCFormattingModelBuilder.myAlwaysCreateFullModel.get().get() > 0) {
                return true;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return false;
    }
    
    private static FormattingModel a(final PsiElement psiElement) {
        return (FormattingModel)new FormattingModel() {
            final /* synthetic */ AbstractBlock val$block = new AbstractBlock(psiElement.getNode(), null, null) {
                @Override
                protected List<Block> buildChildren() {
                    return OCFormattingModelBuilder$2.EMPTY;
                }
                
                @Nullable
                public Spacing getSpacing(@Nullable final Block block, @NotNull final Block block2) {
                    try {
                        if (block2 == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "child2", "com/jetbrains/cidr/lang/formatting/OCFormattingModelBuilder$2", "getSpacing"));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw b(ex);
                    }
                    return null;
                }
                
                public boolean isLeaf() {
                    return false;
                }
                
                private static IllegalArgumentException b(final IllegalArgumentException ex) {
                    return ex;
                }
            };
            final /* synthetic */ FormattingDocumentModel val$model = new FormattingDocumentModel(FormattingDocumentModelImpl.createOn(psiElement.getContainingFile()).getDocument()) {
                final /* synthetic */ Document val$document;
                
                public int getLineNumber(final int n) {
                    return this.val$document.getLineNumber(n);
                }
                
                public int getLineStartOffset(final int n) {
                    return this.val$document.getLineStartOffset(n);
                }
                
                public CharSequence getText(final TextRange textRange) {
                    return this.val$document.getText(textRange);
                }
                
                public int getTextLength() {
                    return this.val$document.getTextLength();
                }
                
                @NotNull
                public Document getDocument() {
                    Document val$document;
                    try {
                        val$document = this.val$document;
                        if (val$document == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCFormattingModelBuilder$3", "getDocument"));
                        }
                    }
                    catch (IllegalStateException ex) {
                        throw a(ex);
                    }
                    return val$document;
                }
                
                public boolean containsWhiteSpaceSymbolsOnly(final int n, final int n2) {
                    return false;
                }
                
                @NotNull
                public CharSequence adjustWhiteSpaceIfNecessary(@NotNull final CharSequence charSequence, final int n, final int n2, final ASTNode astNode, final boolean b) {
                    try {
                        if (charSequence == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "whiteSpaceText", "com/jetbrains/cidr/lang/formatting/OCFormattingModelBuilder$3", "adjustWhiteSpaceIfNecessary"));
                        }
                    }
                    catch (IllegalStateException ex) {
                        throw a(ex);
                    }
                    try {
                        if (charSequence == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCFormattingModelBuilder$3", "adjustWhiteSpaceIfNecessary"));
                        }
                    }
                    catch (IllegalStateException ex2) {
                        throw a(ex2);
                    }
                    return charSequence;
                }
                
                private static IllegalStateException a(final IllegalStateException ex) {
                    return ex;
                }
            };
            
            @NotNull
            public Block getRootBlock() {
                AbstractBlock val$block;
                try {
                    val$block = this.val$block;
                    if (val$block == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCFormattingModelBuilder$4", "getRootBlock"));
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                return (Block)val$block;
            }
            
            @NotNull
            public FormattingDocumentModel getDocumentModel() {
                FormattingDocumentModel val$model;
                try {
                    val$model = this.val$model;
                    if (val$model == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCFormattingModelBuilder$4", "getDocumentModel"));
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                return val$model;
            }
            
            public TextRange replaceWhiteSpace(final TextRange textRange, final String s) {
                return textRange;
            }
            
            public TextRange shiftIndentInsideRange(final ASTNode astNode, final TextRange textRange, final int n) {
                return textRange;
            }
            
            public void commitChanges() {
            }
            
            private static IllegalStateException a(final IllegalStateException ex) {
                return ex;
            }
        };
    }
    
    static {
        ALWAYS_CREATE_FULL_MODEL = new Key("Never use DumbModel in dumb app state");
        myAlwaysCreateFullModel = new ThreadLocal<AtomicInteger>() {
            @Override
            protected AtomicInteger initialValue() {
                return new AtomicInteger(0);
            }
        };
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
