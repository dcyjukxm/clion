// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import java.util.Collection;
import com.intellij.codeInsight.FileModificationService;
import java.util.ArrayList;
import com.intellij.psi.PsiDocumentManager;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.IncorrectOperationException;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.intellij.codeInsight.intention.IntentionAction;

public abstract class OCCreateDefinitionIntentionAction implements IntentionAction
{
    protected OCSymbolKind mySymbolKind;
    protected PsiElement myUsage;
    protected OCSymbol myParent;
    
    public OCCreateDefinitionIntentionAction(final OCSymbolKind mySymbolKind, final PsiElement myUsage, final OCSymbol myParent) {
        this.mySymbolKind = mySymbolKind;
        this.myUsage = myUsage;
        this.myParent = myParent;
    }
    
    @NotNull
    public String getFamilyName() {
        String text;
        try {
            text = this.getText();
            if (text == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction", "getFamilyName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return text;
    }
    
    public boolean isAvailable(@NotNull final Project p0, final Editor p1, final PsiFile p2) {
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
        //    18: ldc             "project"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isAvailable"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    43: athrow         
        //    44: aload_0        
        //    45: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.myUsage:Lcom/intellij/psi/PsiElement;
        //    48: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.isValid:(Lcom/intellij/psi/PsiElement;)Z
        //    51: ifeq            85
        //    54: aload_0        
        //    55: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.myParent:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    58: ifnull          91
        //    61: goto            68
        //    64: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    67: athrow         
        //    68: aload_0        
        //    69: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.myParent:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    72: invokestatic    com/jetbrains/cidr/lang/search/scopes/OCSearchScope.isInProjectSources:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //    75: ifne            91
        //    78: goto            85
        //    81: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    84: athrow         
        //    85: iconst_0       
        //    86: ireturn        
        //    87: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    90: athrow         
        //    91: getstatic       com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction$1.$SwitchMap$com$jetbrains$cidr$lang$symbols$OCSymbolKind:[I
        //    94: aload_0        
        //    95: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.mySymbolKind:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    98: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.ordinal:()I
        //   101: iaload         
        //   102: tableswitch {
        //                2: 152
        //                3: 152
        //                4: 152
        //                5: 152
        //                6: 152
        //                7: 152
        //                8: 158
        //                9: 175
        //               10: 214
        //          default: 277
        //        }
        //   152: iconst_1       
        //   153: ireturn        
        //   154: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   157: athrow         
        //   158: aload_0        
        //   159: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.myParent:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   162: ifnull          173
        //   165: iconst_1       
        //   166: goto            174
        //   169: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   172: athrow         
        //   173: iconst_0       
        //   174: ireturn        
        //   175: aload_0        
        //   176: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.myParent:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   179: ifnull          212
        //   182: aload_0        
        //   183: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.myParent:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   186: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   191: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.ENUM:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   194: if_acmpne       212
        //   197: goto            204
        //   200: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   203: athrow         
        //   204: iconst_1       
        //   205: goto            213
        //   208: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   211: athrow         
        //   212: iconst_0       
        //   213: ireturn        
        //   214: aload_0        
        //   215: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.myUsage:Lcom/intellij/psi/PsiElement;
        //   218: ldc             Lcom/jetbrains/cidr/lang/psi/OCMethod;.class
        //   220: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   223: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //   226: astore          4
        //   228: aload           4
        //   230: ifnull          267
        //   233: aload_0        
        //   234: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.myUsage:Lcom/intellij/psi/PsiElement;
        //   237: instanceof      Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //   240: ifne            267
        //   243: goto            250
        //   246: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   249: athrow         
        //   250: aload           4
        //   252: invokeinterface com/jetbrains/cidr/lang/psi/OCMethod.isInstanceMethod:()Z
        //   257: ifeq            275
        //   260: goto            267
        //   263: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   266: athrow         
        //   267: iconst_1       
        //   268: goto            276
        //   271: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   274: athrow         
        //   275: iconst_0       
        //   276: ireturn        
        //   277: iconst_0       
        //   278: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  44     61     64     68     Lcom/intellij/util/IncorrectOperationException;
        //  54     78     81     85     Lcom/intellij/util/IncorrectOperationException;
        //  68     87     87     91     Lcom/intellij/util/IncorrectOperationException;
        //  91     154    154    158    Lcom/intellij/util/IncorrectOperationException;
        //  158    169    169    173    Lcom/intellij/util/IncorrectOperationException;
        //  175    197    200    204    Lcom/intellij/util/IncorrectOperationException;
        //  182    208    208    212    Lcom/intellij/util/IncorrectOperationException;
        //  228    243    246    250    Lcom/intellij/util/IncorrectOperationException;
        //  233    260    263    267    Lcom/intellij/util/IncorrectOperationException;
        //  250    271    271    275    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0068:
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
    protected abstract PsiElement getDefinition(final Project p0, final Editor p1, final PsiFile p2);
    
    public void invoke(@NotNull final Project project, final Editor editor, final PsiFile psiFile) throws IncorrectOperationException {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction", "invoke"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        PsiDocumentManager.getInstance(project).commitAllDocuments();
        final ArrayList<PsiFile> list = new ArrayList<PsiFile>();
        Label_0120: {
            Label_0096: {
                try {
                    list.add(psiFile);
                    if (this.mySymbolKind == OCSymbolKind.STRUCT_FIELD) {
                        break Label_0096;
                    }
                    final OCCreateDefinitionIntentionAction ocCreateDefinitionIntentionAction = this;
                    final OCSymbolKind ocSymbolKind = ocCreateDefinitionIntentionAction.mySymbolKind;
                    final OCSymbolKind ocSymbolKind2 = OCSymbolKind.ENUM_CONST;
                    if (ocSymbolKind == ocSymbolKind2) {
                        break Label_0096;
                    }
                    break Label_0120;
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
                try {
                    final OCCreateDefinitionIntentionAction ocCreateDefinitionIntentionAction = this;
                    final OCSymbolKind ocSymbolKind = ocCreateDefinitionIntentionAction.mySymbolKind;
                    final OCSymbolKind ocSymbolKind2 = OCSymbolKind.ENUM_CONST;
                    if (ocSymbolKind == ocSymbolKind2) {
                        list.add((PsiFile)this.myParent.getContainingOCFile());
                    }
                }
                catch (IncorrectOperationException ex3) {
                    throw a(ex3);
                }
            }
            try {
                if (FileModificationService.getInstance().preparePsiElementsForWrite((Collection)list)) {
                    this.doCreate(project, editor, psiFile);
                }
            }
            catch (IncorrectOperationException ex4) {
                throw a(ex4);
            }
        }
    }
    
    protected boolean doCreate(final Project p0, final Editor p1, final PsiFile p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.myParent:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //     4: ifnull          23
        //     7: aload_0        
        //     8: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.myParent:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    11: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.locateDefinition:()Lcom/intellij/psi/PsiElement;
        //    16: goto            24
        //    19: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    22: athrow         
        //    23: aconst_null    
        //    24: astore          4
        //    26: aload_0        
        //    27: aload_1        
        //    28: aload_2        
        //    29: aload_3        
        //    30: invokevirtual   com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.getDefinition:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;)Lcom/intellij/psi/PsiElement;
        //    33: astore          5
        //    35: aload           5
        //    37: ifnonnull       46
        //    40: iconst_0       
        //    41: ireturn        
        //    42: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    45: athrow         
        //    46: aload_0        
        //    47: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.mySymbolKind:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    50: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.FUNCTION_DECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    53: if_acmpeq       155
        //    56: aload_0        
        //    57: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.mySymbolKind:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    60: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.METHOD:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    63: if_acmpeq       155
        //    66: goto            73
        //    69: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    72: athrow         
        //    73: aload_0        
        //    74: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.mySymbolKind:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    77: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.GLOBAL_VARIABLE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    80: if_acmpeq       155
        //    83: goto            90
        //    86: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    89: athrow         
        //    90: aload_0        
        //    91: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.mySymbolKind:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    94: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.LOCAL_VARIABLE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    97: if_acmpeq       155
        //   100: goto            107
        //   103: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   106: athrow         
        //   107: aload_0        
        //   108: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.mySymbolKind:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   111: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.MACRO:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   114: if_acmpeq       155
        //   117: goto            124
        //   120: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   123: athrow         
        //   124: aload_0        
        //   125: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.mySymbolKind:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   128: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.ENUM_CONST:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   131: if_acmpne       223
        //   134: goto            141
        //   137: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   140: athrow         
        //   141: aload_0        
        //   142: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.myParent:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   145: ifnull          223
        //   148: goto            155
        //   151: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   154: athrow         
        //   155: aload_0        
        //   156: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.myParent:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   159: ifnull          183
        //   162: goto            169
        //   165: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   168: athrow         
        //   169: aload_0        
        //   170: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.myParent:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   173: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.locateDefinition:()Lcom/intellij/psi/PsiElement;
        //   178: astore          7
        //   180: goto            196
        //   183: aload_0        
        //   184: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.mySymbolKind:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   187: aload_0        
        //   188: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.myUsage:Lcom/intellij/psi/PsiElement;
        //   191: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.getAppropriateParent:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   194: astore          7
        //   196: aload           7
        //   198: ifnonnull       207
        //   201: iconst_0       
        //   202: ireturn        
        //   203: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   206: athrow         
        //   207: aload           7
        //   209: aload           5
        //   211: aload_0        
        //   212: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.myUsage:Lcom/intellij/psi/PsiElement;
        //   215: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.addBefore:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   218: astore          6
        //   220: goto            358
        //   223: aload_0        
        //   224: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.mySymbolKind:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   227: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.STRUCT_FIELD:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   230: if_acmpeq       250
        //   233: aload_0        
        //   234: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.mySymbolKind:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   237: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.PROPERTY:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   240: if_acmpne       294
        //   243: goto            250
        //   246: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   249: athrow         
        //   250: aload_0        
        //   251: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.myParent:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   254: ifnull          276
        //   257: goto            264
        //   260: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   263: athrow         
        //   264: aload           4
        //   266: ifnonnull       282
        //   269: goto            276
        //   272: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   275: athrow         
        //   276: iconst_0       
        //   277: ireturn        
        //   278: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   281: athrow         
        //   282: aload           4
        //   284: aload           5
        //   286: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.add:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   289: astore          6
        //   291: goto            358
        //   294: aload_0        
        //   295: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.mySymbolKind:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   298: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.INSTANCE_VARIABLE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   301: if_acmpne       356
        //   304: aload_0        
        //   305: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.myParent:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   308: ifnull          330
        //   311: goto            318
        //   314: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   317: athrow         
        //   318: aload           4
        //   320: ifnonnull       336
        //   323: goto            330
        //   326: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   329: athrow         
        //   330: iconst_0       
        //   331: ireturn        
        //   332: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   335: athrow         
        //   336: aload           4
        //   338: checkcast       Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
        //   341: invokeinterface com/jetbrains/cidr/lang/psi/OCClassDeclaration.getInstanceVariablesList:()Lcom/jetbrains/cidr/lang/psi/OCInstanceVariablesList;
        //   346: aload           5
        //   348: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.add:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   351: astore          6
        //   353: goto            358
        //   356: iconst_0       
        //   357: ireturn        
        //   358: aload           6
        //   360: ifnull          504
        //   363: aload_0        
        //   364: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.mySymbolKind:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   367: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.FUNCTION_DECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   370: if_acmpeq       504
        //   373: goto            380
        //   376: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   379: athrow         
        //   380: aload_0        
        //   381: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.mySymbolKind:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   384: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.METHOD:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   387: if_acmpeq       504
        //   390: goto            397
        //   393: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   396: athrow         
        //   397: aload_2        
        //   398: ifnull          499
        //   401: goto            408
        //   404: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   407: athrow         
        //   408: aload_0        
        //   409: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.mySymbolKind:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   412: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.GLOBAL_VARIABLE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   415: if_acmpne       499
        //   418: goto            425
        //   421: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   424: athrow         
        //   425: aload           5
        //   427: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //   430: ifeq            499
        //   433: goto            440
        //   436: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   439: athrow         
        //   440: aload_2        
        //   441: invokeinterface com/intellij/openapi/editor/Editor.getCaretModel:()Lcom/intellij/openapi/editor/CaretModel;
        //   446: aload           6
        //   448: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //   451: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getDeclarators:()Ljava/util/List;
        //   456: iconst_0       
        //   457: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   462: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   465: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   470: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   473: invokeinterface com/intellij/openapi/editor/CaretModel.moveToOffset:(I)V
        //   478: aload_2        
        //   479: invokeinterface com/intellij/openapi/editor/Editor.getScrollingModel:()Lcom/intellij/openapi/editor/ScrollingModel;
        //   484: getstatic       com/intellij/openapi/editor/ScrollType.MAKE_VISIBLE:Lcom/intellij/openapi/editor/ScrollType;
        //   487: invokeinterface com/intellij/openapi/editor/ScrollingModel.scrollToCaret:(Lcom/intellij/openapi/editor/ScrollType;)V
        //   492: goto            504
        //   495: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   498: athrow         
        //   499: aload           6
        //   501: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.selectElement:(Lcom/intellij/psi/PsiElement;)V
        //   504: iconst_1       
        //   505: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      19     19     23     Lcom/intellij/util/IncorrectOperationException;
        //  35     42     42     46     Lcom/intellij/util/IncorrectOperationException;
        //  46     66     69     73     Lcom/intellij/util/IncorrectOperationException;
        //  56     83     86     90     Lcom/intellij/util/IncorrectOperationException;
        //  73     100    103    107    Lcom/intellij/util/IncorrectOperationException;
        //  90     117    120    124    Lcom/intellij/util/IncorrectOperationException;
        //  107    134    137    141    Lcom/intellij/util/IncorrectOperationException;
        //  124    148    151    155    Lcom/intellij/util/IncorrectOperationException;
        //  141    162    165    169    Lcom/intellij/util/IncorrectOperationException;
        //  196    203    203    207    Lcom/intellij/util/IncorrectOperationException;
        //  223    243    246    250    Lcom/intellij/util/IncorrectOperationException;
        //  233    257    260    264    Lcom/intellij/util/IncorrectOperationException;
        //  250    269    272    276    Lcom/intellij/util/IncorrectOperationException;
        //  264    278    278    282    Lcom/intellij/util/IncorrectOperationException;
        //  294    311    314    318    Lcom/intellij/util/IncorrectOperationException;
        //  304    323    326    330    Lcom/intellij/util/IncorrectOperationException;
        //  318    332    332    336    Lcom/intellij/util/IncorrectOperationException;
        //  358    373    376    380    Lcom/intellij/util/IncorrectOperationException;
        //  363    390    393    397    Lcom/intellij/util/IncorrectOperationException;
        //  380    401    404    408    Lcom/intellij/util/IncorrectOperationException;
        //  397    418    421    425    Lcom/intellij/util/IncorrectOperationException;
        //  408    433    436    440    Lcom/intellij/util/IncorrectOperationException;
        //  425    495    495    499    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0073:
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
    
    public boolean startInWriteAction() {
        return true;
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
