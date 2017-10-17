// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.jetbrains.cidr.lang.OCLog;
import com.jetbrains.cidr.lang.util.OCCallableUtil;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.types.ARCAttribute;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.types.visitors.OCTypeNameVisitor;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.psi.OCFunctionDefinition;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCGeneratedInfo;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import java.util.List;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeSignatureHandler;
import java.util.Collections;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeSignatureActionHandler;
import com.intellij.openapi.application.WriteAction;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.types.OCUnknownType;
import com.jetbrains.cidr.lang.psi.OCArgumentList;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.types.OCVoidType;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.psi.OCCppNewExpression;
import com.jetbrains.cidr.lang.psi.OCCallExpression;
import com.intellij.psi.PsiDirectory;
import com.jetbrains.cidr.lang.actions.newFile.OCNewFileActionBase;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.jetbrains.cidr.lang.formatting.OCFormattingModelBuilder;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.refactoring.introduce.OCIntroducePropertyAction;
import com.jetbrains.cidr.lang.refactoring.introduce.OCIntroduceIvarAction;
import com.jetbrains.cidr.lang.refactoring.introduce.OCIntroduceParameterAction;
import com.jetbrains.cidr.lang.refactoring.introduce.OCIntroduceVariableAction;
import com.jetbrains.cidr.lang.refactoring.introduce.OCBaseIntroduceHandler;
import com.jetbrains.cidr.lang.actions.newFile.OCNewCppClassAction;
import com.jetbrains.cidr.lang.actions.newFile.OCNewProtocolAction;
import com.jetbrains.cidr.lang.actions.newFile.OCNewClassAction;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCSymbolContext;
import java.util.Iterator;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import com.jetbrains.cidr.lang.symbols.objc.OCProtocolSymbol;
import com.jetbrains.cidr.lang.types.OCIdType;
import com.intellij.openapi.application.ApplicationManager;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.util.OCExpectedTypeUtil;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.types.OCType;

public class OCCreateNewDefinitionIntentionAction extends OCCreateDefinitionIntentionAction
{
    private String myName;
    private OCType myType;
    private OCObjectType myReceiverType;
    private OCPropertySymbol myPropertySymbol;
    private OCExpectedTypeUtil.Expectable myExpectableType;
    private boolean myIsStaticMember;
    private boolean mySilentMode;
    private String myMessageSignature;
    
    public OCCreateNewDefinitionIntentionAction(final OCSymbolKind ocSymbolKind, final PsiElement psiElement, @Nullable final OCPropertySymbol myPropertySymbol, final OCSymbol ocSymbol, final String myName, final OCType myType, final boolean myIsStaticMember) {
        super(ocSymbolKind, psiElement, ocSymbol);
        this.mySilentMode = ApplicationManager.getApplication().isUnitTestMode();
        this.myName = myName;
        this.myType = myType;
        this.myPropertySymbol = myPropertySymbol;
        this.myIsStaticMember = myIsStaticMember;
    }
    
    public OCCreateNewDefinitionIntentionAction(final OCSymbolKind ocSymbolKind, final PsiElement psiElement, final OCSymbol ocSymbol, final String s, final OCType ocType) {
        this(ocSymbolKind, psiElement, null, ocSymbol, s, ocType, false);
    }
    
    public OCCreateNewDefinitionIntentionAction(final PsiElement psiElement, final OCSymbol ocSymbol, final String s, final String myMessageSignature, final OCType ocType, @Nullable final OCObjectType myReceiverType) {
        this(OCSymbolKind.METHOD, psiElement, null, ocSymbol, s, ocType, false);
        this.myMessageSignature = myMessageSignature;
        this.myReceiverType = myReceiverType;
        if (myReceiverType instanceof OCIdType) {
            this.myParent = null;
            for (final OCProtocolSymbol myParent : myReceiverType.getAllProtocols()) {
                Label_0098: {
                    try {
                        if (!OCSearchScope.isInProjectSources(myParent)) {
                            continue;
                        }
                        final OCProtocolSymbol ocProtocolSymbol = myParent;
                        final boolean b = ocProtocolSymbol.isPredeclaration();
                        if (!b) {
                            break Label_0098;
                        }
                        continue;
                    }
                    catch (RuntimeException ex) {
                        throw b(ex);
                    }
                    try {
                        final OCProtocolSymbol ocProtocolSymbol = myParent;
                        final boolean b = ocProtocolSymbol.isPredeclaration();
                        if (!b) {
                            this.myParent = myParent;
                            break;
                        }
                        continue;
                    }
                    catch (RuntimeException ex2) {
                        throw b(ex2);
                    }
                }
            }
        }
        if (ApplicationManager.getApplication().isUnitTestMode()) {
            final OCSymbol a = this.a();
            OCSymbol myParent2 = null;
            Label_0150: {
                try {
                    if (a != null) {
                        myParent2 = a;
                        break Label_0150;
                    }
                }
                catch (RuntimeException ex3) {
                    throw b(ex3);
                }
                myParent2 = this.myParent;
            }
            this.myParent = myParent2;
        }
    }
    
    public OCCreateNewDefinitionIntentionAction(final OCSymbolContext ocSymbolContext, final PsiElement psiElement, final String myName) {
        super(ocSymbolContext.getSymbolKind(), psiElement, ocSymbolContext.getParent());
        this.mySilentMode = ApplicationManager.getApplication().isUnitTestMode();
        this.myName = myName;
        this.myExpectableType = ocSymbolContext.getExpectable();
        boolean myIsStaticMember = false;
        Label_0065: {
            if (ocSymbolContext instanceof OCSymbolContext.StructMemberContext) {
                try {
                    if (((OCSymbolContext.StructMemberContext)ocSymbolContext).isStatic()) {
                        myIsStaticMember = true;
                        break Label_0065;
                    }
                }
                catch (RuntimeException ex) {
                    throw b(ex);
                }
            }
            myIsStaticMember = false;
        }
        this.myIsStaticMember = myIsStaticMember;
        this.myPropertySymbol = null;
    }
    
    public void setSilentMode(final boolean mySilentMode) {
        this.mySilentMode = mySilentMode;
    }
    
    @NotNull
    public String getText() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: ldc             ""
        //     2: astore_1       
        //     3: aload_0        
        //     4: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.mySymbolKind:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //     7: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.INTERFACE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    10: if_acmpne       19
        //    13: ldc             "class"
        //    15: astore_1       
        //    16: goto            118
        //    19: aload_0        
        //    20: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.myIsStaticMember:Z
        //    23: ifeq            77
        //    26: getstatic       com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction$2.$SwitchMap$com$jetbrains$cidr$lang$symbols$OCSymbolKind:[I
        //    29: aload_0        
        //    30: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.mySymbolKind:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    33: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.ordinal:()I
        //    36: iaload         
        //    37: tableswitch {
        //                2: 68
        //                3: 68
        //                4: 68
        //          default: 74
        //        }
        //    64: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    67: athrow         
        //    68: ldc             "class "
        //    70: astore_1       
        //    71: goto            77
        //    74: ldc             "static "
        //    76: astore_1       
        //    77: aload_0        
        //    78: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.mySymbolKind:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    81: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.STRUCT:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    84: if_acmpne       93
        //    87: ldc             "C++ class"
        //    89: astore_1       
        //    90: goto            118
        //    93: new             Ljava/lang/StringBuilder;
        //    96: dup            
        //    97: invokespecial   java/lang/StringBuilder.<init>:()V
        //   100: aload_1        
        //   101: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   104: aload_0        
        //   105: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.mySymbolKind:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   108: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.getNameLowercase:()Ljava/lang/String;
        //   111: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   114: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   117: astore_1       
        //   118: new             Ljava/lang/StringBuilder;
        //   121: dup            
        //   122: invokespecial   java/lang/StringBuilder.<init>:()V
        //   125: astore_2       
        //   126: aload_0        
        //   127: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.myParent:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   130: ifnull          192
        //   133: aload_0        
        //   134: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.myReceiverType:Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   137: ifnull          192
        //   140: goto            147
        //   143: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   146: athrow         
        //   147: aload_0        
        //   148: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.a:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   151: ifnonnull       192
        //   154: goto            161
        //   157: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   160: athrow         
        //   161: aload_2        
        //   162: ldc             "Create new category on "
        //   164: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   167: aload_0        
        //   168: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.myParent:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   171: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //   176: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   179: ldc             " with "
        //   181: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   184: pop            
        //   185: goto            199
        //   188: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   191: athrow         
        //   192: aload_2        
        //   193: ldc             "Create new "
        //   195: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   198: pop            
        //   199: aload_2        
        //   200: aload_1        
        //   201: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   204: ldc             " '"
        //   206: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   209: pop            
        //   210: aload_0        
        //   211: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.mySymbolKind:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   214: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isFunctionOrConstructor:()Z
        //   217: ifeq            269
        //   220: aload_0        
        //   221: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.myType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   224: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   227: ifeq            269
        //   230: goto            237
        //   233: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   236: athrow         
        //   237: aload_2        
        //   238: aload_0        
        //   239: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.myUsage:Lcom/intellij/psi/PsiElement;
        //   242: aload_0        
        //   243: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.myType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   246: checkcast       Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   249: aload_0        
        //   250: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.myName:Ljava/lang/String;
        //   253: iconst_0       
        //   254: aconst_null    
        //   255: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeNameVisitor.getFunctionSignature:(Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/types/OCFunctionType;Ljava/lang/String;ZLjava/util/List;)Ljava/lang/String;
        //   258: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   261: pop            
        //   262: goto            278
        //   265: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   268: athrow         
        //   269: aload_2        
        //   270: aload_0        
        //   271: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.myName:Ljava/lang/String;
        //   274: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   277: pop            
        //   278: aload_2        
        //   279: ldc             "'"
        //   281: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   284: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   287: dup            
        //   288: ifnonnull       325
        //   291: new             Ljava/lang/IllegalStateException;
        //   294: dup            
        //   295: ldc             "@NotNull method %s.%s must not return null"
        //   297: ldc             2
        //   299: anewarray       Ljava/lang/Object;
        //   302: dup            
        //   303: ldc             0
        //   305: ldc             "com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction"
        //   307: aastore        
        //   308: dup            
        //   309: ldc             1
        //   311: ldc             "getText"
        //   313: aastore        
        //   314: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   317: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   320: athrow         
        //   321: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   324: athrow         
        //   325: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  19     64     64     68     Ljava/lang/RuntimeException;
        //  126    140    143    147    Ljava/lang/RuntimeException;
        //  133    154    157    161    Ljava/lang/RuntimeException;
        //  147    188    188    192    Ljava/lang/RuntimeException;
        //  199    230    233    237    Ljava/lang/RuntimeException;
        //  220    265    265    269    Ljava/lang/RuntimeException;
        //  278    321    321    325    Ljava/lang/RuntimeException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0147:
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
    
    @Override
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
        //    24: ldc             "com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isAvailable"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    43: athrow         
        //    44: aload_0        
        //    45: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.myName:Ljava/lang/String;
        //    48: ifnull          116
        //    51: aload_0        
        //    52: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.myUsage:Lcom/intellij/psi/PsiElement;
        //    55: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.isValid:(Lcom/intellij/psi/PsiElement;)Z
        //    58: ifeq            116
        //    61: goto            68
        //    64: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    67: athrow         
        //    68: aload_0        
        //    69: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.myParent:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    72: ifnull          122
        //    75: goto            82
        //    78: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    81: athrow         
        //    82: aload_0        
        //    83: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.myParent:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    86: invokestatic    com/jetbrains/cidr/lang/search/scopes/OCSearchScope.isInProjectSources:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //    89: ifne            122
        //    92: goto            99
        //    95: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    98: athrow         
        //    99: aload_0        
        //   100: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.mySymbolKind:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   103: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.METHOD:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   106: if_acmpeq       122
        //   109: goto            116
        //   112: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   115: athrow         
        //   116: iconst_0       
        //   117: ireturn        
        //   118: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   121: athrow         
        //   122: aload_0        
        //   123: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.mySymbolKind:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   126: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isClass:()Z
        //   129: ifne            216
        //   132: aload_0        
        //   133: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.mySymbolKind:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   136: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.STRUCT:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   139: if_acmpeq       216
        //   142: goto            149
        //   145: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   148: athrow         
        //   149: aload_0        
        //   150: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.myUsage:Lcom/intellij/psi/PsiElement;
        //   153: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //   156: ifeq            216
        //   159: goto            166
        //   162: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   165: athrow         
        //   166: aload_0        
        //   167: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.myUsage:Lcom/intellij/psi/PsiElement;
        //   170: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   175: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //   178: ifne            216
        //   181: goto            188
        //   184: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   187: athrow         
        //   188: aload_0        
        //   189: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.myUsage:Lcom/intellij/psi/PsiElement;
        //   192: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   197: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSynthesizeProperty;
        //   200: ifne            216
        //   203: goto            210
        //   206: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   209: athrow         
        //   210: iconst_0       
        //   211: ireturn        
        //   212: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   215: athrow         
        //   216: aload_0        
        //   217: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.myType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   220: ifnonnull       257
        //   223: aload_0        
        //   224: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.myExpectableType:Lcom/jetbrains/cidr/lang/util/OCExpectedTypeUtil$Expectable;
        //   227: ifnull          257
        //   230: goto            237
        //   233: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   236: athrow         
        //   237: aload_0        
        //   238: aload_0        
        //   239: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.myExpectableType:Lcom/jetbrains/cidr/lang/util/OCExpectedTypeUtil$Expectable;
        //   242: invokeinterface com/jetbrains/cidr/lang/util/OCExpectedTypeUtil$Expectable.getExpectedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   247: putfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.myType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   250: goto            257
        //   253: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   256: athrow         
        //   257: aload_0        
        //   258: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.myType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   261: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   264: istore          4
        //   266: getstatic       com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction$2.$SwitchMap$com$jetbrains$cidr$lang$symbols$OCSymbolKind:[I
        //   269: aload_0        
        //   270: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.mySymbolKind:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   273: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.ordinal:()I
        //   276: iaload         
        //   277: tableswitch {
        //                2: 812
        //                3: 812
        //                4: 541
        //                5: 328
        //                6: 464
        //                7: 512
        //                8: 646
        //                9: 646
        //               10: 729
        //          default: 844
        //        }
        //   328: iload           4
        //   330: ifeq            373
        //   333: goto            340
        //   336: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   339: athrow         
        //   340: aload_0        
        //   341: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.myParent:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   344: ifnull          373
        //   347: goto            354
        //   350: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   353: athrow         
        //   354: aload_0        
        //   355: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.myParent:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   358: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isPredeclaration:()Z
        //   363: ifeq            379
        //   366: goto            373
        //   369: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   372: athrow         
        //   373: iconst_0       
        //   374: ireturn        
        //   375: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   378: athrow         
        //   379: aload_0        
        //   380: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.myUsage:Lcom/intellij/psi/PsiElement;
        //   383: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //   386: ifeq            412
        //   389: aload_0        
        //   390: aload_0        
        //   391: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.myUsage:Lcom/intellij/psi/PsiElement;
        //   394: checkcast       Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //   397: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceExpression.getReferenceElement:()Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //   402: putfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.myUsage:Lcom/intellij/psi/PsiElement;
        //   405: goto            412
        //   408: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   411: athrow         
        //   412: aload_0        
        //   413: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.myUsage:Lcom/intellij/psi/PsiElement;
        //   416: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //   419: ifeq            462
        //   422: aload_0        
        //   423: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.myUsage:Lcom/intellij/psi/PsiElement;
        //   426: checkcast       Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //   429: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceElement.getName:()Ljava/lang/String;
        //   434: aload_0        
        //   435: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.myParent:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   438: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //   443: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   446: ifne            462
        //   449: goto            456
        //   452: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   455: athrow         
        //   456: iconst_0       
        //   457: ireturn        
        //   458: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   461: athrow         
        //   462: iconst_1       
        //   463: ireturn        
        //   464: iload           4
        //   466: ifeq            510
        //   469: aload_0        
        //   470: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.myParent:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   473: ifnull          502
        //   476: goto            483
        //   479: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   482: athrow         
        //   483: aload_0        
        //   484: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.myParent:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   487: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isPredeclaration:()Z
        //   492: ifne            510
        //   495: goto            502
        //   498: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   501: athrow         
        //   502: iconst_1       
        //   503: goto            511
        //   506: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   509: athrow         
        //   510: iconst_0       
        //   511: ireturn        
        //   512: aload_0        
        //   513: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.myParent:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   516: ifnull          539
        //   519: iload           4
        //   521: ifne            539
        //   524: goto            531
        //   527: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   530: athrow         
        //   531: iconst_1       
        //   532: goto            540
        //   535: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   538: athrow         
        //   539: iconst_0       
        //   540: ireturn        
        //   541: aload_0        
        //   542: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.myParent:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   545: ifnull          584
        //   548: aload_0        
        //   549: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.myParent:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   552: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   555: ifeq            644
        //   558: goto            565
        //   561: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   564: athrow         
        //   565: aload_0        
        //   566: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.myParent:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   569: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isPredeclaration:()Z
        //   574: ifne            644
        //   577: goto            584
        //   580: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   583: athrow         
        //   584: aload_0        
        //   585: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.myMessageSignature:Ljava/lang/String;
        //   588: ifnull          644
        //   591: goto            598
        //   594: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   597: athrow         
        //   598: aload_0        
        //   599: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.myMessageSignature:Ljava/lang/String;
        //   602: ldc             "+"
        //   604: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   607: ifne            636
        //   610: goto            617
        //   613: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   616: athrow         
        //   617: aload_0        
        //   618: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.myMessageSignature:Ljava/lang/String;
        //   621: ldc             "-"
        //   623: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   626: ifeq            644
        //   629: goto            636
        //   632: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   635: athrow         
        //   636: iconst_1       
        //   637: goto            645
        //   640: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   643: athrow         
        //   644: iconst_0       
        //   645: ireturn        
        //   646: aload_3        
        //   647: ifnull          688
        //   650: aload_3        
        //   651: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   654: ifeq            727
        //   657: goto            664
        //   660: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   663: athrow         
        //   664: aload_3        
        //   665: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   668: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getKind:()Lcom/jetbrains/cidr/lang/OCLanguageKind;
        //   673: invokeinterface com/jetbrains/cidr/lang/OCLanguageKind.isObjC:()Z
        //   678: ifeq            727
        //   681: goto            688
        //   684: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   687: athrow         
        //   688: invokestatic    com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase.isNewFileActionSupported:()Z
        //   691: ifeq            727
        //   694: goto            701
        //   697: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   700: athrow         
        //   701: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //   704: invokeinterface com/intellij/openapi/application/Application.isUnitTestMode:()Z
        //   709: ifne            727
        //   712: goto            719
        //   715: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   718: athrow         
        //   719: iconst_1       
        //   720: goto            728
        //   723: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   726: athrow         
        //   727: iconst_0       
        //   728: ireturn        
        //   729: aload_3        
        //   730: ifnull          771
        //   733: aload_3        
        //   734: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   737: ifeq            810
        //   740: goto            747
        //   743: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   746: athrow         
        //   747: aload_3        
        //   748: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   751: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getKind:()Lcom/jetbrains/cidr/lang/OCLanguageKind;
        //   756: invokeinterface com/jetbrains/cidr/lang/OCLanguageKind.isCpp:()Z
        //   761: ifeq            810
        //   764: goto            771
        //   767: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   770: athrow         
        //   771: invokestatic    com/jetbrains/cidr/lang/actions/newFile/OCNewFileActionBase.isNewFileActionSupported:()Z
        //   774: ifeq            810
        //   777: goto            784
        //   780: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   783: athrow         
        //   784: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //   787: invokeinterface com/intellij/openapi/application/Application.isUnitTestMode:()Z
        //   792: ifne            810
        //   795: goto            802
        //   798: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   801: athrow         
        //   802: iconst_1       
        //   803: goto            811
        //   806: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   809: athrow         
        //   810: iconst_0       
        //   811: ireturn        
        //   812: aload_0        
        //   813: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.myParent:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   816: ifnull          838
        //   819: aload_0        
        //   820: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.myParent:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   823: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isPredeclaration:()Z
        //   828: ifeq            844
        //   831: goto            838
        //   834: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   837: athrow         
        //   838: iconst_0       
        //   839: ireturn        
        //   840: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   843: athrow         
        //   844: aload_0        
        //   845: aload_1        
        //   846: aload_2        
        //   847: aload_3        
        //   848: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCCreateDefinitionIntentionAction.isAvailable:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;)Z
        //   851: ifeq            888
        //   854: aload_0        
        //   855: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.myType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   858: ifnull          888
        //   861: goto            868
        //   864: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   867: athrow         
        //   868: iload           4
        //   870: ifne            888
        //   873: goto            880
        //   876: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   879: athrow         
        //   880: iconst_1       
        //   881: goto            889
        //   884: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   887: athrow         
        //   888: iconst_0       
        //   889: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  0      40     40     44     Ljava/lang/RuntimeException;
        //  44     61     64     68     Ljava/lang/RuntimeException;
        //  51     75     78     82     Ljava/lang/RuntimeException;
        //  68     92     95     99     Ljava/lang/RuntimeException;
        //  82     109    112    116    Ljava/lang/RuntimeException;
        //  99     118    118    122    Ljava/lang/RuntimeException;
        //  122    142    145    149    Ljava/lang/RuntimeException;
        //  132    159    162    166    Ljava/lang/RuntimeException;
        //  149    181    184    188    Ljava/lang/RuntimeException;
        //  166    203    206    210    Ljava/lang/RuntimeException;
        //  188    212    212    216    Ljava/lang/RuntimeException;
        //  216    230    233    237    Ljava/lang/RuntimeException;
        //  223    250    253    257    Ljava/lang/RuntimeException;
        //  266    333    336    340    Ljava/lang/RuntimeException;
        //  328    347    350    354    Ljava/lang/RuntimeException;
        //  340    366    369    373    Ljava/lang/RuntimeException;
        //  354    375    375    379    Ljava/lang/RuntimeException;
        //  379    405    408    412    Ljava/lang/RuntimeException;
        //  412    449    452    456    Ljava/lang/RuntimeException;
        //  422    458    458    462    Ljava/lang/RuntimeException;
        //  464    476    479    483    Ljava/lang/RuntimeException;
        //  469    495    498    502    Ljava/lang/RuntimeException;
        //  483    506    506    510    Ljava/lang/RuntimeException;
        //  512    524    527    531    Ljava/lang/RuntimeException;
        //  519    535    535    539    Ljava/lang/RuntimeException;
        //  541    558    561    565    Ljava/lang/RuntimeException;
        //  548    577    580    584    Ljava/lang/RuntimeException;
        //  565    591    594    598    Ljava/lang/RuntimeException;
        //  584    610    613    617    Ljava/lang/RuntimeException;
        //  598    629    632    636    Ljava/lang/RuntimeException;
        //  617    640    640    644    Ljava/lang/RuntimeException;
        //  646    657    660    664    Ljava/lang/RuntimeException;
        //  650    681    684    688    Ljava/lang/RuntimeException;
        //  664    694    697    701    Ljava/lang/RuntimeException;
        //  688    712    715    719    Ljava/lang/RuntimeException;
        //  701    723    723    727    Ljava/lang/RuntimeException;
        //  729    740    743    747    Ljava/lang/RuntimeException;
        //  733    764    767    771    Ljava/lang/RuntimeException;
        //  747    777    780    784    Ljava/lang/RuntimeException;
        //  771    795    798    802    Ljava/lang/RuntimeException;
        //  784    806    806    810    Ljava/lang/RuntimeException;
        //  812    831    834    838    Ljava/lang/RuntimeException;
        //  819    840    840    844    Ljava/lang/RuntimeException;
        //  844    861    864    868    Ljava/lang/RuntimeException;
        //  854    873    876    880    Ljava/lang/RuntimeException;
        //  868    884    884    888    Ljava/lang/RuntimeException;
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
    private OCSymbol a() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.myParent:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //     4: ifnull          38
        //     7: aload_0        
        //     8: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.myParent:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    11: invokestatic    com/jetbrains/cidr/lang/search/scopes/OCSearchScope.isInProjectSources:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //    14: ifne            38
        //    17: goto            24
        //    20: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    23: athrow         
        //    24: aload_0        
        //    25: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.myReceiverType:Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //    28: ifnonnull       47
        //    31: goto            38
        //    34: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    37: athrow         
        //    38: aload_0        
        //    39: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.myParent:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    42: areturn        
        //    43: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    46: athrow         
        //    47: aload_0        
        //    48: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.myReceiverType:Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //    51: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.getAugmentedProtocols:()Ljava/util/List;
        //    54: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    59: astore_1       
        //    60: aload_1        
        //    61: invokeinterface java/util/Iterator.hasNext:()Z
        //    66: ifeq            95
        //    69: aload_1        
        //    70: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    75: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCProtocolSymbol;
        //    78: astore_2       
        //    79: aload_2        
        //    80: invokestatic    com/jetbrains/cidr/lang/search/scopes/OCSearchScope.isInProjectSources:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //    83: ifeq            92
        //    86: aload_2        
        //    87: areturn        
        //    88: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    91: athrow         
        //    92: goto            60
        //    95: aload_0        
        //    96: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.myReceiverType:Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //    99: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.getCategoryInterfaces:()Ljava/util/List;
        //   102: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   107: astore_1       
        //   108: aload_1        
        //   109: invokeinterface java/util/Iterator.hasNext:()Z
        //   114: ifeq            143
        //   117: aload_1        
        //   118: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   123: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
        //   126: astore_2       
        //   127: aload_2        
        //   128: invokestatic    com/jetbrains/cidr/lang/search/scopes/OCSearchScope.isInProjectSources:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //   131: ifeq            140
        //   134: aload_2        
        //   135: areturn        
        //   136: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   139: athrow         
        //   140: goto            108
        //   143: aconst_null    
        //   144: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  0      17     20     24     Ljava/lang/RuntimeException;
        //  7      31     34     38     Ljava/lang/RuntimeException;
        //  24     43     43     47     Ljava/lang/RuntimeException;
        //  79     88     88     92     Ljava/lang/RuntimeException;
        //  127    136    136    140    Ljava/lang/RuntimeException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0024:
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
    
    @Override
    protected boolean doCreate(final Project project, final Editor editor, final PsiFile psiFile) {
        OCNewFileActionBase ocNewFileActionBase = null;
        OCBaseIntroduceHandler ocBaseIntroduceHandler = null;
        if (this.mySymbolKind == OCSymbolKind.INTERFACE) {
            ocNewFileActionBase = new OCNewClassAction();
        }
        else if (this.mySymbolKind == OCSymbolKind.PROTOCOL) {
            ocNewFileActionBase = new OCNewProtocolAction();
        }
        else if (this.mySymbolKind == OCSymbolKind.STRUCT) {
            ocNewFileActionBase = new OCNewCppClassAction();
            this.a(ocNewFileActionBase);
        }
        else if (this.mySymbolKind == OCSymbolKind.LOCAL_VARIABLE) {
            ocBaseIntroduceHandler = (OCBaseIntroduceHandler)new OCIntroduceVariableAction().getHandler();
        }
        else if (this.mySymbolKind == OCSymbolKind.PARAMETER) {
            ocBaseIntroduceHandler = (OCBaseIntroduceHandler)new OCIntroduceParameterAction().getHandler();
        }
        else if (this.mySymbolKind == OCSymbolKind.INSTANCE_VARIABLE) {
            ocBaseIntroduceHandler = (OCBaseIntroduceHandler)new OCIntroduceIvarAction().getHandler();
        }
        else {
            if (this.mySymbolKind != OCSymbolKind.PROPERTY) {
                try {
                    if (this.mySymbolKind.isFunctionOrConstructor()) {
                        return this.a(psiFile);
                    }
                }
                catch (RuntimeException ex) {
                    throw b(ex);
                }
                try {
                    if (this.mySymbolKind == OCSymbolKind.METHOD) {
                        return this.b();
                    }
                }
                catch (RuntimeException ex2) {
                    throw b(ex2);
                }
                return super.doCreate(project, editor, psiFile);
            }
            ocBaseIntroduceHandler = (OCBaseIntroduceHandler)new OCIntroducePropertyAction().getHandler();
        }
        if (ocBaseIntroduceHandler != null) {
            PsiElement psiElement;
            final OCBaseIntroduceHandler<PsiElement> ocBaseIntroduceHandler2;
            final Runnable runnable = () -> {
                Label_0094_1: {
                    Label_0048_1: {
                        try {
                            if (this.mySymbolKind != OCSymbolKind.PROPERTY) {
                                if (this.mySymbolKind != OCSymbolKind.INSTANCE_VARIABLE) {
                                    break Label_0048_1;
                                }
                            }
                        }
                        catch (RuntimeException ex3) {
                            throw b(ex3);
                        }
                        psiElement = PsiTreeUtil.getParentOfType(this.myUsage, (Class)OCElement.class, (boolean)(0 != 0));
                        break Label_0094_1;
                        try {
                            if (!(this.myUsage instanceof OCReferenceElement) || !(this.myUsage.getParent() instanceof OCReferenceExpression)) {
                                break Label_0094_1;
                            }
                        }
                        catch (RuntimeException ex4) {
                            throw b(ex4);
                        }
                    }
                    psiElement = this.myUsage.getParent();
                    try {
                        if (psiElement != null) {
                            ocBaseIntroduceHandler2.invoke(project, editor, psiElement, this.myType.resolve(psiFile), true, false, this.myName, this.myParent);
                        }
                    }
                    catch (RuntimeException ex5) {
                        throw b(ex5);
                    }
                }
                return;
            };
            try {
                if (ApplicationManager.getApplication().isUnitTestMode()) {
                    runnable.run();
                    return true;
                }
            }
            catch (RuntimeException ex6) {
                throw b(ex6);
            }
            ApplicationManager.getApplication().invokeLater(runnable);
            return true;
        }
        Label_0334: {
            try {
                if (ocNewFileActionBase == null) {
                    break Label_0334;
                }
                final OCNewFileActionBase ocNewFileActionBase2 = ocNewFileActionBase;
                final Project project2 = project;
                final PsiFile psiFile2 = psiFile;
                final PsiDirectory psiDirectory = psiFile2.getParent();
                final PsiFile psiFile3 = psiFile;
                final OCCreateNewDefinitionIntentionAction ocCreateNewDefinitionIntentionAction = this;
                final String s = ocCreateNewDefinitionIntentionAction.myName;
                final PsiFile[] array = ocNewFileActionBase2.performAction(project2, psiDirectory, psiFile3, s);
                if (array == null) {
                    return false;
                }
                break Label_0334;
            }
            catch (RuntimeException ex7) {
                throw b(ex7);
            }
            try {
                final OCNewFileActionBase ocNewFileActionBase2 = ocNewFileActionBase;
                final Project project2 = project;
                final PsiFile psiFile2 = psiFile;
                final PsiDirectory psiDirectory = psiFile2.getParent();
                final PsiFile psiFile3 = psiFile;
                final OCCreateNewDefinitionIntentionAction ocCreateNewDefinitionIntentionAction = this;
                final String s = ocCreateNewDefinitionIntentionAction.myName;
                final PsiFile[] array = ocNewFileActionBase2.performAction(project2, psiDirectory, psiFile3, s);
                if (array == null) {
                    return false;
                }
            }
            catch (RuntimeException ex8) {
                throw b(ex8);
            }
            try {
                if (!(this.myUsage instanceof OCReferenceElement) || !this.myUsage.getText().equals(this.myName)) {
                    return true;
                }
            }
            catch (RuntimeException ex9) {
                throw b(ex9);
            }
        }
        final OCImportSymbolFix ocImportSymbolFix = new OCImportSymbolFix((OCReferenceElement)this.myUsage);
        try {
            OCFormattingModelBuilder.requestAlwaysCreateFullModel();
            new WriteCommandAction(project, new PsiFile[0]) {
                protected void run(@NotNull final Result result) throws Throwable {
                    try {
                        if (result == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/quickfixes/OCCreateNewDefinitionIntentionAction$1", "run"));
                        }
                    }
                    catch (Throwable t) {
                        throw a(t);
                    }
                    ocImportSymbolFix.fixFirstItem(project, psiFile);
                }
                
                private static Throwable a(final Throwable t) {
                    return t;
                }
            }.execute();
        }
        finally {
            OCFormattingModelBuilder.releaseAlwaysCreateFullModel();
        }
        return true;
    }
    
    private void a(final OCNewFileActionBase ocNewFileActionBase) {
        final PsiElement parent = this.myUsage.getParent();
        Label_0096: {
            Label_0057: {
                try {
                    if (!(parent instanceof OCReferenceExpression) || !(parent.getParent() instanceof OCCallExpression)) {
                        break Label_0057;
                    }
                }
                catch (RuntimeException ex) {
                    throw b(ex);
                }
                final Object parent2 = parent;
                final OCArgumentList list = ((OCCallExpression)parent.getParent()).getArgumentList();
                break Label_0096;
            }
            OCArgumentList list;
            if (parent.getParent() instanceof OCCppNewExpression) {
                final Object parent2 = parent.getParent();
                list = ((OCCppNewExpression)parent2).getArgumentList();
            }
            else {
                final Object parent2 = null;
                list = null;
            }
            try {
                if (list != null) {
                    int length;
                    int i;
                    final OCSymbol ocSymbol;
                    final OCType ocType;
                    final OCArgumentList list2;
                    final PsiElement psiElement;
                    final OCCreateNewDefinitionIntentionAction ocCreateNewDefinitionIntentionAction;
                    final PsiFile psiFile;
                    ocNewFileActionBase.addAuxAction(array -> {
                        for (length = array.length; i < length; ++i) {
                            ((OCFile)array[i]).getSameNamedClass();
                            try {
                                if (ocSymbol != null) {
                                    break;
                                }
                            }
                            catch (RuntimeException ex2) {
                                throw b(ex2);
                            }
                        }
                        if (ocSymbol != null) {
                            new OCFunctionType(OCVoidType.instance(), list2.getArgumentTypes(new OCResolveContext(this.myUsage)));
                            ocCreateNewDefinitionIntentionAction = new OCCreateNewDefinitionIntentionAction(OCSymbolKind.CPP_CONSTRUCTOR_DECLARATION, psiElement, ocSymbol, this.myName, ocType);
                            ocCreateNewDefinitionIntentionAction.setSilentMode(true);
                            ocSymbol.getContainingOCFile();
                            try {
                                if (ocCreateNewDefinitionIntentionAction.isAvailable(psiFile.getProject(), null, psiFile)) {
                                    ocCreateNewDefinitionIntentionAction.invoke(psiFile.getProject(), null, psiFile);
                                }
                            }
                            catch (RuntimeException ex3) {
                                throw b(ex3);
                            }
                        }
                    });
                }
            }
            catch (RuntimeException ex4) {
                throw b(ex4);
            }
        }
    }
    
    private boolean b() {
        Label_0024: {
            try {
                if (this.myType == null) {
                    break Label_0024;
                }
                final OCCreateNewDefinitionIntentionAction ocCreateNewDefinitionIntentionAction = this;
                final OCType ocType = ocCreateNewDefinitionIntentionAction.myType;
                final OCUnknownType ocUnknownType = OCUnknownType.INSTANCE;
                if (ocType == ocUnknownType) {
                    break Label_0024;
                }
                break Label_0024;
            }
            catch (RuntimeException ex) {
                throw b(ex);
            }
            try {
                final OCCreateNewDefinitionIntentionAction ocCreateNewDefinitionIntentionAction = this;
                final OCType ocType = ocCreateNewDefinitionIntentionAction.myType;
                final OCUnknownType ocUnknownType = OCUnknownType.INSTANCE;
                if (ocType == ocUnknownType) {
                    this.myType = OCVoidType.instance();
                }
            }
            catch (RuntimeException ex2) {
                throw b(ex2);
            }
        }
        final PsiElement appropriateParent = OCChangeUtil.getAppropriateParent(this.mySymbolKind, this.myUsage);
        PsiElement realAnchorForInsertion = null;
        Label_0070: {
            try {
                if (appropriateParent != null) {
                    realAnchorForInsertion = OCChangeUtil.getRealAnchorForInsertion(appropriateParent, this.myUsage);
                    break Label_0070;
                }
            }
            catch (RuntimeException ex3) {
                throw b(ex3);
            }
            realAnchorForInsertion = null;
        }
        final PsiElement psiElement = realAnchorForInsertion;
        PsiElement myUsage = null;
        Label_0087: {
            try {
                if (psiElement != null) {
                    myUsage = psiElement;
                    break Label_0087;
                }
            }
            catch (RuntimeException ex4) {
                throw b(ex4);
            }
            myUsage = this.myUsage;
        }
        final OCChangeSignatureHandler handler = OCChangeSignatureActionHandler.getHandler((OCCallable)WriteAction.compute(() -> OCElementFactory.methodFromText(OCCallableUtil.methodWithSignature(this.myType, this.myMessageSignature, this.myUsage), this.myUsage, true)), myUsage);
        List<? extends OCClassSymbol> list = null;
        Label_0146: {
            try {
                handler.getGeneratedInfo().setMethodReference(this.myUsage);
                if (this.myReceiverType != null) {
                    list = this.myReceiverType.getAugmentedProtocols();
                    break Label_0146;
                }
            }
            catch (RuntimeException ex5) {
                throw b(ex5);
            }
            list = Collections.emptyList();
        }
        final List<? extends OCClassSymbol> list2 = list;
        Label_0250: {
            Label_0235: {
                Label_0193: {
                    OCGeneratedInfo generatedInfo;
                    try {
                        handler.setParentClass(this.myParent, true, list2);
                        handler.setChangeUsages(false);
                        generatedInfo = handler.getGeneratedInfo();
                        if (!this.mySilentMode) {
                            final boolean selectMethod = true;
                            break Label_0193;
                        }
                    }
                    catch (RuntimeException ex6) {
                        throw b(ex6);
                    }
                    final boolean selectMethod = false;
                    try {
                        generatedInfo.setSelectMethod(selectMethod);
                        handler.setTitle("Create new method");
                        handler.setRefactorButtonText("Create");
                        if (this.myParent == null) {
                            break Label_0235;
                        }
                        final OCCreateNewDefinitionIntentionAction ocCreateNewDefinitionIntentionAction2 = this;
                        final OCObjectType ocObjectType = ocCreateNewDefinitionIntentionAction2.myReceiverType;
                        if (ocObjectType == null) {
                            break Label_0235;
                        }
                        break Label_0250;
                    }
                    catch (RuntimeException ex7) {
                        throw b(ex7);
                    }
                }
                try {
                    final OCCreateNewDefinitionIntentionAction ocCreateNewDefinitionIntentionAction2 = this;
                    final OCObjectType ocObjectType = ocCreateNewDefinitionIntentionAction2.myReceiverType;
                    if (ocObjectType == null) {
                        handler.setChangeParentClassPossible(true);
                    }
                }
                catch (RuntimeException ex8) {
                    throw b(ex8);
                }
            }
            try {
                if (this.mySilentMode) {
                    handler.invokeSynchronously();
                    return true;
                }
            }
            catch (RuntimeException ex9) {
                throw b(ex9);
            }
        }
        handler.invoke();
        return true;
    }
    
    private boolean a(final PsiFile psiFile) {
        try {
            if (!(this.myType instanceof OCFunctionType)) {
                return false;
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        PsiElement psiElement = null;
        Label_0050: {
            try {
                if (this.myParent != null) {
                    psiElement = this.myParent.locateDefinition();
                    break Label_0050;
                }
            }
            catch (RuntimeException ex2) {
                throw b(ex2);
            }
            psiElement = OCChangeUtil.getAppropriateParent(this.mySymbolKind, this.myUsage);
        }
        final PsiElement psiElement2 = psiElement;
        final OCFunctionDefinition ocFunctionDefinition = (OCFunctionDefinition)PsiTreeUtil.getParentOfType(this.myUsage, (Class)OCFunctionDefinition.class);
        OCFunctionSymbol symbol = null;
        Label_0082: {
            try {
                if (ocFunctionDefinition != null) {
                    symbol = ocFunctionDefinition.getSymbol();
                    break Label_0082;
                }
            }
            catch (RuntimeException ex3) {
                throw b(ex3);
            }
            symbol = null;
        }
        final OCFunctionSymbol ocFunctionSymbol = symbol;
        final PsiElement realAnchorForInsertion = OCChangeUtil.getRealAnchorForInsertion(psiElement2, this.myUsage);
        PsiElement myUsage = null;
        Label_0112: {
            try {
                if (realAnchorForInsertion != null) {
                    myUsage = realAnchorForInsertion;
                    break Label_0112;
                }
            }
            catch (RuntimeException ex4) {
                throw b(ex4);
            }
            myUsage = this.myUsage;
        }
        final PsiElement psiElement3 = myUsage;
        Label_0182: {
            if (ocFunctionSymbol != null) {
                final OCSymbolWithQualifiedName<OCElement> resolvedOwner = ocFunctionSymbol.getResolvedOwner();
                Label_0150: {
                    try {
                        if (resolvedOwner == null) {
                            break Label_0182;
                        }
                        final OCSymbolWithQualifiedName<OCElement> ocSymbolWithQualifiedName = resolvedOwner;
                        final OCCreateNewDefinitionIntentionAction ocCreateNewDefinitionIntentionAction = this;
                        final OCSymbol ocSymbol = ocCreateNewDefinitionIntentionAction.myParent;
                        final boolean b = ocSymbolWithQualifiedName.isSameSymbol(ocSymbol);
                        if (b) {
                            break Label_0150;
                        }
                        break Label_0182;
                    }
                    catch (RuntimeException ex5) {
                        throw b(ex5);
                    }
                    try {
                        final OCSymbolWithQualifiedName<OCElement> ocSymbolWithQualifiedName = resolvedOwner;
                        final OCCreateNewDefinitionIntentionAction ocCreateNewDefinitionIntentionAction = this;
                        final OCSymbol ocSymbol = ocCreateNewDefinitionIntentionAction.myParent;
                        final boolean b = ocSymbolWithQualifiedName.isSameSymbol(ocSymbol);
                        if (b) {
                            this.myType = this.myType.cloneWithAddedCVQualifiers(ocFunctionSymbol.getType().getCVQualifiers(), ocFunctionDefinition.getProject());
                        }
                    }
                    catch (RuntimeException ex6) {
                        throw b(ex6);
                    }
                }
            }
        }
        final OCCallable ocCallable = (OCCallable)WriteAction.compute(() -> {
            OCDeclaration constructorFromText;
            if (this.mySymbolKind.isConstructorOrDestructor()) {
                constructorFromText = OCElementFactory.constructorFromText(s, (PsiElement)psiFile, true);
            }
            else if (this.myParent instanceof OCStructSymbol) {
                constructorFromText = (OCDeclaration)OCElementFactory.topLevelDeclarationFromText(s, (PsiElement)psiFile, true);
            }
            else {
                constructorFromText = (OCDeclaration)OCElementFactory.topLevelDeclarationFromText(OCCallableUtil.functionWithSignature(((OCFunctionType)this.myType).getReturnType(), s, this.myUsage), (PsiElement)psiFile, true);
            }
            OCLog.LOG.assertTrue(constructorFromText instanceof OCCallable, (Object)constructorFromText.getTextWithMacros());
            return (OCCallable)constructorFromText;
        });
        OCChangeSignatureHandler ocChangeSignatureHandler = null;
        Label_0349: {
            OCChangeSignatureHandler ocChangeSignatureHandler2 = null;
            Label_0323: {
                OCGeneratedInfo generatedInfo = null;
                Label_0271: {
                    Label_0258: {
                        OCCallable ocCallable2 = null;
                        PsiElement psiElement4 = null;
                        boolean b2 = false;
                        boolean b3 = false;
                        boolean b5 = false;
                        Label_0249: {
                            Label_0240: {
                                try {
                                    if (!this.mySymbolKind.isConstructorOrDestructor()) {
                                        break Label_0258;
                                    }
                                    ocCallable2 = ocCallable;
                                    psiElement4 = psiElement3;
                                    b2 = false;
                                    final OCCreateNewDefinitionIntentionAction ocCreateNewDefinitionIntentionAction2 = this;
                                    b3 = ocCreateNewDefinitionIntentionAction2.mySilentMode;
                                    final OCCreateNewDefinitionIntentionAction ocCreateNewDefinitionIntentionAction3 = this;
                                    final boolean b4 = ocCreateNewDefinitionIntentionAction3.mySilentMode;
                                    if (!b4) {
                                        break Label_0240;
                                    }
                                    break Label_0240;
                                }
                                catch (RuntimeException ex7) {
                                    throw b(ex7);
                                }
                                try {
                                    ocCallable2 = ocCallable;
                                    psiElement4 = psiElement3;
                                    b2 = false;
                                    final OCCreateNewDefinitionIntentionAction ocCreateNewDefinitionIntentionAction2 = this;
                                    b3 = ocCreateNewDefinitionIntentionAction2.mySilentMode;
                                    final OCCreateNewDefinitionIntentionAction ocCreateNewDefinitionIntentionAction3 = this;
                                    final boolean b4 = ocCreateNewDefinitionIntentionAction3.mySilentMode;
                                    if (!b4) {
                                        b5 = true;
                                        break Label_0249;
                                    }
                                }
                                catch (RuntimeException ex8) {
                                    throw b(ex8);
                                }
                            }
                            b5 = false;
                        }
                        ocChangeSignatureHandler = OCChangeSignatureActionHandler.getHandler(ocCallable2, psiElement4, b2, b3, b5, true);
                        break Label_0271;
                    }
                    ocChangeSignatureHandler = OCChangeSignatureActionHandler.getHandler(ocCallable, psiElement3, this.mySilentMode);
                    try {
                        ocChangeSignatureHandler.getGeneratedInfo().setMethodReference(this.myUsage);
                        ocChangeSignatureHandler.setParentClass(this.myParent, false, Collections.emptyList());
                        generatedInfo = ocChangeSignatureHandler.getGeneratedInfo();
                        if (!this.mySilentMode) {
                            final boolean selectMethod = true;
                            break Label_0323;
                        }
                    }
                    catch (RuntimeException ex9) {
                        throw b(ex9);
                    }
                }
                final boolean selectMethod = false;
                try {
                    generatedInfo.setSelectMethod(selectMethod);
                    ocChangeSignatureHandler2 = ocChangeSignatureHandler;
                    if (this.mySymbolKind.isConstructorOrDestructor()) {
                        final String title = "Create new constructor";
                        break Label_0349;
                    }
                }
                catch (RuntimeException ex10) {
                    throw b(ex10);
                }
            }
            final String title = "Create new function";
            try {
                ocChangeSignatureHandler2.setTitle(title);
                ocChangeSignatureHandler.setRefactorButtonText("Create");
                if (this.mySilentMode) {
                    ocChangeSignatureHandler.invokeSynchronously();
                    return true;
                }
            }
            catch (RuntimeException ex11) {
                throw b(ex11);
            }
        }
        ocChangeSignatureHandler.invoke();
        return true;
    }
    
    protected String getFunctionSignature(final PsiElement psiElement) {
        final StringBuilder sb = new StringBuilder();
        Label_0118: {
            Label_0072: {
                Label_0032: {
                    try {
                        if (this.mySymbolKind.isConstructorOrDestructor()) {
                            break Label_0072;
                        }
                        final OCCreateNewDefinitionIntentionAction ocCreateNewDefinitionIntentionAction = this;
                        final boolean b = ocCreateNewDefinitionIntentionAction.myIsStaticMember;
                        if (b) {
                            break Label_0032;
                        }
                        break Label_0032;
                    }
                    catch (RuntimeException ex) {
                        throw b(ex);
                    }
                    try {
                        final OCCreateNewDefinitionIntentionAction ocCreateNewDefinitionIntentionAction = this;
                        final boolean b = ocCreateNewDefinitionIntentionAction.myIsStaticMember;
                        if (b) {
                            sb.append("static ");
                        }
                    }
                    catch (RuntimeException ex2) {
                        throw b(ex2);
                    }
                }
                sb.append(((OCFunctionType)this.myType).getReturnType().getBestNameInContext(psiElement));
                sb.append(' ');
                try {
                    sb.append(this.myName);
                    if (!(this.myUsage instanceof OCReferenceElement)) {
                        break Label_0118;
                    }
                    final OCCreateNewDefinitionIntentionAction ocCreateNewDefinitionIntentionAction2 = this;
                    final PsiElement psiElement2 = ocCreateNewDefinitionIntentionAction2.myUsage;
                    final PsiElement psiElement3 = psiElement2.getParent();
                    final PsiElement psiElement4 = psiElement3.getParent();
                    final boolean b2 = psiElement4 instanceof OCCallExpression;
                    if (b2) {
                        break Label_0118;
                    }
                    break Label_0118;
                }
                catch (RuntimeException ex3) {
                    throw b(ex3);
                }
            }
            try {
                final OCCreateNewDefinitionIntentionAction ocCreateNewDefinitionIntentionAction2 = this;
                final PsiElement psiElement2 = ocCreateNewDefinitionIntentionAction2.myUsage;
                final PsiElement psiElement3 = psiElement2.getParent();
                final PsiElement psiElement4 = psiElement3.getParent();
                final boolean b2 = psiElement4 instanceof OCCallExpression;
                if (b2) {
                    final List<OCExpression> arguments = ((OCCallExpression)this.myUsage.getParent().getParent()).getArguments();
                    return OCTypeNameVisitor.getFunctionSignature(psiElement, (OCFunctionType)this.myType, sb.toString(), true, arguments);
                }
            }
            catch (RuntimeException ex4) {
                throw b(ex4);
            }
        }
        final List<OCExpression> arguments = null;
        return OCTypeNameVisitor.getFunctionSignature(psiElement, (OCFunctionType)this.myType, sb.toString(), true, arguments);
    }
    
    @Nullable
    public PsiElement getDefinition(final Project project, final Editor editor, final PsiFile psiFile) {
        switch (this.mySymbolKind) {
            case INSTANCE_VARIABLE:
            case GLOBAL_VARIABLE:
            case LOCAL_VARIABLE: {
                String tokenName = null;
                OCType resolvedType = null;
                Label_0107: {
                    try {
                        if (this.myPropertySymbol != null) {
                            resolvedType = this.myPropertySymbol.getResolvedType();
                            break Label_0107;
                        }
                    }
                    catch (RuntimeException ex) {
                        throw b(ex);
                    }
                    resolvedType = null;
                }
                final OCType ocType = resolvedType;
                Label_0133: {
                    try {
                        if (!(this.myPropertySymbol instanceof OCPropertySymbol)) {
                            return (PsiElement)OCElementFactory.declaration(tokenName, this.myName, this.myType, this.myUsage);
                        }
                        final PsiFile psiFile2 = psiFile;
                        final boolean b = OCCompilerFeatures.isArcEnabled(psiFile2);
                        if (b) {
                            break Label_0133;
                        }
                        return (PsiElement)OCElementFactory.declaration(tokenName, this.myName, this.myType, this.myUsage);
                    }
                    catch (RuntimeException ex2) {
                        throw b(ex2);
                    }
                    try {
                        final PsiFile psiFile2 = psiFile;
                        final boolean b = OCCompilerFeatures.isArcEnabled(psiFile2);
                        if (!b) {
                            return (PsiElement)OCElementFactory.declaration(tokenName, this.myName, this.myType, this.myUsage);
                        }
                        if (!ocType.isPointerToObjectCompatible()) {
                            return (PsiElement)OCElementFactory.declaration(tokenName, this.myName, this.myType, this.myUsage);
                        }
                    }
                    catch (RuntimeException ex3) {
                        throw b(ex3);
                    }
                }
                final OCPropertySymbol.PropertyAttribute attributeOfGroup = this.myPropertySymbol.getAttributeOfGroup(OCPropertySymbol.PropertyAttribute.ASSIGN, ocType, (PsiElement)psiFile);
                if (attributeOfGroup != null) {
                    final ARCAttribute ivarCompatibleARCAttribute = attributeOfGroup.getIvarCompatibleARCAttribute();
                    if (!ivarCompatibleARCAttribute.isDefault()) {
                        tokenName = ivarCompatibleARCAttribute.getTokenName();
                    }
                }
                return (PsiElement)OCElementFactory.declaration(tokenName, this.myName, this.myType, this.myUsage);
            }
            case STRUCT_FIELD:
            case ENUM_CONST: {
                Label_0252: {
                    Label_0239: {
                        try {
                            if (this.myParent == null) {
                                break Label_0252;
                            }
                            final OCCreateNewDefinitionIntentionAction ocCreateNewDefinitionIntentionAction = this;
                            final OCSymbol ocSymbol = ocCreateNewDefinitionIntentionAction.myParent;
                            final OCSymbolKind ocSymbolKind = ocSymbol.getKind();
                            final OCSymbolKind ocSymbolKind2 = OCSymbolKind.ENUM;
                            if (ocSymbolKind == ocSymbolKind2) {
                                break Label_0239;
                            }
                            break Label_0252;
                        }
                        catch (RuntimeException ex4) {
                            throw b(ex4);
                        }
                        try {
                            final OCCreateNewDefinitionIntentionAction ocCreateNewDefinitionIntentionAction = this;
                            final OCSymbol ocSymbol = ocCreateNewDefinitionIntentionAction.myParent;
                            final OCSymbolKind ocSymbolKind = ocSymbol.getKind();
                            final OCSymbolKind ocSymbolKind2 = OCSymbolKind.ENUM;
                            if (ocSymbolKind == ocSymbolKind2) {
                                return (PsiElement)OCElementFactory.enumConst(this.myName, (PsiElement)psiFile);
                            }
                        }
                        catch (RuntimeException ex5) {
                            throw b(ex5);
                        }
                    }
                    try {
                        if (this.myIsStaticMember) {
                            final String s = "static";
                            return (PsiElement)OCElementFactory.declaration(s, this.myName, this.myType, this.myUsage);
                        }
                    }
                    catch (RuntimeException ex6) {
                        throw b(ex6);
                    }
                }
                final String s = null;
                return (PsiElement)OCElementFactory.declaration(s, this.myName, this.myType, this.myUsage);
            }
            case PROPERTY: {
                return (PsiElement)OCElementFactory.propertyDeclaration(this.myName, this.myType.resolve(psiFile), (PsiElement)psiFile);
            }
            case MACRO: {
                return (PsiElement)OCElementFactory.macroDeclarationFromText(this.myName, this.myType.resolve(psiFile).getDefaultValue((PsiElement)psiFile), psiFile);
            }
            default: {
                return null;
            }
        }
    }
    
    @Override
    public boolean startInWriteAction() {
        try {
            switch (this.mySymbolKind) {
                case INSTANCE_VARIABLE:
                case PROPERTY:
                case METHOD:
                case CPP_CONSTRUCTOR_DECLARATION:
                case FUNCTION_DECLARATION:
                case PARAMETER:
                case INTERFACE:
                case PROTOCOL:
                case STRUCT:
                case LOCAL_VARIABLE:
                case FUNCTION_PREDECLARATION:
                case CPP_CONSTRUCTOR_PREDECLARATION: {
                    return false;
                }
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        return super.startInWriteAction();
    }
    
    private static RuntimeException b(final RuntimeException ex) {
        return ex;
    }
}
