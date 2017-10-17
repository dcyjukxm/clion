// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.intellij.psi.SmartPointerManager;
import com.intellij.psi.SmartPsiElementPointer;
import java.util.HashMap;
import java.util.List;
import com.intellij.psi.impl.source.codeStyle.CodeEditUtil;
import java.util.Iterator;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.intellij.openapi.util.Pair;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.types.visitors.OCTypeEqualityAfterResolvingVisitor;
import com.jetbrains.cidr.lang.types.OCTypeParameterType;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.util.OCParenthesesUtils;
import com.jetbrains.cidr.lang.psi.OCCallExpression;
import com.intellij.psi.PsiElementVisitor;
import com.jetbrains.cidr.lang.daemon.OCGetSymbolVisitor;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.types.OCType;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.OCSymbol;

public class OCChangeTypeIntentionAction extends OCQuickFix
{
    @Nullable
    private OCSymbol<?> mySymbol;
    private OCType mySubstitutionType;
    private String mySubject;
    private String myMessageSuffix;
    private boolean myReturnTypeMode;
    private boolean myChangeAssociatedSymbol;
    private boolean myChangeProperty;
    
    public OCChangeTypeIntentionAction(@Nullable final OCSymbol mySymbol, final OCType mySubstitutionType) {
        this.myMessageSuffix = "";
        this.myChangeAssociatedSymbol = true;
        this.mySymbol = (OCSymbol<?>)mySymbol;
        this.mySubstitutionType = mySubstitutionType;
        if (this.mySymbol != null) {
            this.mySubject = this.mySymbol.getNameWithKindLowercase();
        }
    }
    
    public OCChangeTypeIntentionAction(@Nullable final OCSymbol ocSymbol, final OCType ocType, final boolean b) {
        this(ocSymbol, ocType, b, null);
    }
    
    public OCChangeTypeIntentionAction(@Nullable final OCSymbol p0, final OCType p1, final boolean p2, @Nullable final String p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aload_1        
        //     2: aload_2        
        //     3: aload           4
        //     5: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/types/OCType;Ljava/lang/String;)V
        //     8: aload_0        
        //     9: iload_3        
        //    10: putfield        com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.myReturnTypeMode:Z
        //    13: aload_0        
        //    14: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.myReturnTypeMode:Z
        //    17: ifeq            77
        //    20: aload_1        
        //    21: ifnull          77
        //    24: aload_1        
        //    25: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    30: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isFunction:()Z
        //    33: ifeq            77
        //    36: goto            43
        //    39: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    42: athrow         
        //    43: aload_2        
        //    44: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getTerminalType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    47: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //    50: ifeq            77
        //    53: goto            60
        //    56: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    59: athrow         
        //    60: aload_0        
        //    61: invokestatic    com/jetbrains/cidr/lang/types/OCVoidType.instance:()Lcom/jetbrains/cidr/lang/types/OCVoidType;
        //    64: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.to:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //    67: putfield        com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.mySubstitutionType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    70: goto            77
        //    73: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    76: athrow         
        //    77: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  20     36     39     43     Ljava/lang/IllegalArgumentException;
        //  24     53     56     60     Ljava/lang/IllegalArgumentException;
        //  43     70     73     77     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0043:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createConstructor(AstBuilder.java:692)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:529)
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
    
    public OCChangeTypeIntentionAction(@Nullable final OCSymbol ocSymbol, final OCType ocType, @Nullable final String mySubject) {
        this(ocSymbol, ocType);
        if (mySubject != null) {
            this.mySubject = mySubject;
        }
    }
    
    public OCChangeTypeIntentionAction(final OCSymbol ocSymbol, final OCType ocType, final String myMessageSuffix, final boolean myChangeAssociatedSymbol) {
        this(ocSymbol, ocType);
        this.myMessageSuffix = myMessageSuffix;
        this.myChangeAssociatedSymbol = myChangeAssociatedSymbol;
    }
    
    public static OCChangeTypeIntentionAction getAction(@NotNull final OCExpression ocExpression, OCType to) {
        try {
            if (ocExpression == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expression", "com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction", "getAction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCGetSymbolVisitor ocGetSymbolVisitor = new OCGetSymbolVisitor(true);
        ocExpression.accept((PsiElementVisitor)ocGetSymbolVisitor);
        final boolean b = OCParenthesesUtils.diveIntoParentheses(ocExpression) instanceof OCCallExpression;
        if (!b) {
            to = OCPointerType.to(to, ocGetSymbolVisitor.getNumOfDereferences());
        }
        return new OCChangeTypeIntentionAction(ocGetSymbolVisitor.getSymbol(), to, b);
    }
    
    @Override
    protected String getTextInternal() {
        StringBuilder append = null;
        Label_0036: {
            try {
                append = new StringBuilder().append("Change ");
                if (this.myReturnTypeMode) {
                    break Label_0036;
                }
                final OCChangeTypeIntentionAction ocChangeTypeIntentionAction = this;
                final OCSymbol<?> ocSymbol = ocChangeTypeIntentionAction.mySymbol;
                final boolean b = ocSymbol instanceof OCMethodSymbol;
                if (b) {
                    break Label_0036;
                }
                break Label_0036;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCChangeTypeIntentionAction ocChangeTypeIntentionAction = this;
                final OCSymbol<?> ocSymbol = ocChangeTypeIntentionAction.mySymbol;
                final boolean b = ocSymbol instanceof OCMethodSymbol;
                if (b) {
                    final String s = "return ";
                    return append.append(s).append("type of ").append(this.mySubject).append(" to '").append(this.mySubstitutionType.getName((PsiElement)this.mySymbol.getContainingOCFile())).append("'").append(this.myMessageSuffix).toString();
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        final String s = "";
        return append.append(s).append("type of ").append(this.mySubject).append(" to '").append(this.mySubstitutionType.getName((PsiElement)this.mySymbol.getContainingOCFile())).append("'").append(this.myMessageSuffix).toString();
    }
    
    @NotNull
    public String getFamilyName() {
        String s;
        try {
            s = "Change type";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction", "getFamilyName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return s;
    }
    
    @Override
    public boolean isAvailable() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //     4: invokestatic    com/jetbrains/cidr/lang/search/scopes/OCSearchScope.isInProjectSources:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //     7: ifeq            71
        //    10: aload_0        
        //    11: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    14: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.locateDefinition:()Lcom/intellij/psi/PsiElement;
        //    19: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.isValid:(Lcom/intellij/psi/PsiElement;)Z
        //    22: ifne            77
        //    25: goto            32
        //    28: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    31: athrow         
        //    32: aload_0        
        //    33: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    36: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //    39: ifeq            71
        //    42: goto            49
        //    45: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    48: athrow         
        //    49: aload_0        
        //    50: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    53: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //    56: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.isClang4ImplicitIvar:()Z
        //    61: ifne            77
        //    64: goto            71
        //    67: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    70: athrow         
        //    71: iconst_0       
        //    72: ireturn        
        //    73: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    76: athrow         
        //    77: aload_0        
        //    78: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    81: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    86: astore_1       
        //    87: aload_0        
        //    88: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    91: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //    94: ifeq            185
        //    97: aload_1        
        //    98: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   101: ifeq            185
        //   104: goto            111
        //   107: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   110: athrow         
        //   111: aload_1        
        //   112: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   115: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.isPredeclaration:()Z
        //   118: ifne            185
        //   121: goto            128
        //   124: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   127: athrow         
        //   128: aload_0        
        //   129: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.mySubstitutionType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   132: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   135: ifeq            183
        //   138: goto            145
        //   141: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   144: athrow         
        //   145: aload_0        
        //   146: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.mySubstitutionType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   149: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   152: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   155: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:()Ljava/lang/String;
        //   158: aload_1        
        //   159: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:()Ljava/lang/String;
        //   162: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   165: ifeq            183
        //   168: goto            175
        //   171: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   174: athrow         
        //   175: iconst_1       
        //   176: goto            184
        //   179: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   182: athrow         
        //   183: iconst_0       
        //   184: ireturn        
        //   185: aload_0        
        //   186: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   189: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   192: ifeq            255
        //   195: aload_1        
        //   196: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   199: ifeq            255
        //   202: goto            209
        //   205: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   208: athrow         
        //   209: aload_0        
        //   210: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.mySubstitutionType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   213: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   216: ifeq            255
        //   219: goto            226
        //   222: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   225: athrow         
        //   226: aload_0        
        //   227: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.mySubstitutionType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   230: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   233: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   236: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   239: ifeq            255
        //   242: goto            249
        //   245: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   248: athrow         
        //   249: iconst_0       
        //   250: ireturn        
        //   251: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   254: athrow         
        //   255: aload_0        
        //   256: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.mySubstitutionType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   259: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isInstanceable:()Z
        //   262: ifne            290
        //   265: aload_0        
        //   266: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   269: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isCallable:()Z
        //   274: ifne            290
        //   277: goto            284
        //   280: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   283: athrow         
        //   284: iconst_0       
        //   285: ireturn        
        //   286: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   289: athrow         
        //   290: iconst_1       
        //   291: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      25     28     32     Ljava/lang/IllegalArgumentException;
        //  10     42     45     49     Ljava/lang/IllegalArgumentException;
        //  32     64     67     71     Ljava/lang/IllegalArgumentException;
        //  49     73     73     77     Ljava/lang/IllegalArgumentException;
        //  87     104    107    111    Ljava/lang/IllegalArgumentException;
        //  97     121    124    128    Ljava/lang/IllegalArgumentException;
        //  111    138    141    145    Ljava/lang/IllegalArgumentException;
        //  128    168    171    175    Ljava/lang/IllegalArgumentException;
        //  145    179    179    183    Ljava/lang/IllegalArgumentException;
        //  185    202    205    209    Ljava/lang/IllegalArgumentException;
        //  195    219    222    226    Ljava/lang/IllegalArgumentException;
        //  209    242    245    249    Ljava/lang/IllegalArgumentException;
        //  226    251    251    255    Ljava/lang/IllegalArgumentException;
        //  255    277    280    284    Ljava/lang/IllegalArgumentException;
        //  265    286    286    290    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0032:
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
    protected void invoke(final PsiFile p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Ljava/util/ArrayList;
        //     3: dup            
        //     4: invokespecial   java/util/ArrayList.<init>:()V
        //     7: astore_2       
        //     8: aload_0        
        //     9: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    12: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getAssociatedSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    17: astore_3       
        //    18: aload_0        
        //    19: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    22: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;
        //    25: ifeq            96
        //    28: aload_0        
        //    29: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.myChangeAssociatedSymbol:Z
        //    32: ifeq            96
        //    35: goto            42
        //    38: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    41: athrow         
        //    42: aload_0        
        //    43: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    46: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;
        //    49: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    54: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //    57: aload_0        
        //    58: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    61: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //    66: aload_0        
        //    67: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    70: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //    73: aload_0        
        //    74: aload_3        
        //    75: aload_1        
        //    76: aload_2        
        //    77: invokedynamic   process:(Lcom/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiFile;Ljava/util/List;)Lcom/intellij/util/Processor;
        //    82: iconst_0       
        //    83: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.processMembersInAllCategories:(Ljava/lang/String;Ljava/lang/Class;Lcom/intellij/util/Processor;Z)Z
        //    88: pop            
        //    89: goto            173
        //    92: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    95: athrow         
        //    96: aload_0        
        //    97: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   100: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   103: ifne            123
        //   106: aload_0        
        //   107: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   110: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   113: ifeq            162
        //   116: goto            123
        //   119: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   122: athrow         
        //   123: aload_0        
        //   124: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.myChangeAssociatedSymbol:Z
        //   127: ifeq            162
        //   130: goto            137
        //   133: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   136: athrow         
        //   137: aload_0        
        //   138: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   141: aload_0        
        //   142: aload_1        
        //   143: aload_2        
        //   144: invokedynamic   process:(Lcom/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction;Lcom/intellij/psi/PsiFile;Ljava/util/List;)Lcom/intellij/util/Processor;
        //   149: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.processSameSymbols:(Lcom/intellij/util/Processor;)Z
        //   154: pop            
        //   155: goto            173
        //   158: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   161: athrow         
        //   162: aload_2        
        //   163: aload_0        
        //   164: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   167: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   172: pop            
        //   173: aload_0        
        //   174: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.myChangeAssociatedSymbol:Z
        //   177: ifeq            592
        //   180: aload_3        
        //   181: ifnull          222
        //   184: goto            191
        //   187: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   190: athrow         
        //   191: aload_0        
        //   192: aload_3        
        //   193: aload_1        
        //   194: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiFile;)Z
        //   197: ifeq            222
        //   200: goto            207
        //   203: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   206: athrow         
        //   207: aload_2        
        //   208: aload_3        
        //   209: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   214: pop            
        //   215: goto            222
        //   218: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   221: athrow         
        //   222: aload_0        
        //   223: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   226: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   229: ifeq            403
        //   232: aload_0        
        //   233: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   236: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   239: aload_2        
        //   240: invokedynamic   process:(Ljava/util/List;)Lcom/intellij/util/Processor;
        //   245: iconst_1       
        //   246: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.processAccessorMethods:(Lcom/intellij/util/Processor;Z)Z
        //   251: pop            
        //   252: aload_0        
        //   253: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   256: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   259: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.getAssociatedIvar:()Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //   264: astore          4
        //   266: aload           4
        //   268: ifnull          400
        //   271: aload_0        
        //   272: aload           4
        //   274: aload_1        
        //   275: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiFile;)Z
        //   278: ifeq            400
        //   281: goto            288
        //   284: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   287: athrow         
        //   288: new             Ljava/lang/StringBuilder;
        //   291: dup            
        //   292: invokespecial   java/lang/StringBuilder.<init>:()V
        //   295: ldc             "Do you want to change type of "
        //   297: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   300: aload           4
        //   302: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.getNameWithKindLowercase:()Ljava/lang/String;
        //   307: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   310: ldc             " as well?"
        //   312: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   315: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   318: astore          5
        //   320: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //   323: invokeinterface com/intellij/openapi/application/Application.isUnitTestMode:()Z
        //   328: ifne            348
        //   331: aload           4
        //   333: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.isClang4ImplicitIvar:()Z
        //   338: ifeq            356
        //   341: goto            348
        //   344: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   347: athrow         
        //   348: iconst_0       
        //   349: goto            366
        //   352: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   355: athrow         
        //   356: aload           5
        //   358: ldc             "Change Type"
        //   360: invokestatic    com/intellij/openapi/ui/Messages.getQuestionIcon:()Ljavax/swing/Icon;
        //   363: invokestatic    com/intellij/openapi/ui/Messages.showYesNoCancelDialog:(Ljava/lang/String;Ljava/lang/String;Ljavax/swing/Icon;)I
        //   366: istore          6
        //   368: iload           6
        //   370: ifne            389
        //   373: aload_2        
        //   374: aload           4
        //   376: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   381: pop            
        //   382: goto            400
        //   385: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   388: athrow         
        //   389: iload           6
        //   391: iconst_2       
        //   392: if_icmpne       400
        //   395: return         
        //   396: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   399: athrow         
        //   400: goto            592
        //   403: aload_0        
        //   404: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   407: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //   410: ifeq            592
        //   413: aload_0        
        //   414: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   417: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //   420: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.getAssociatedProperty:()Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   425: astore          4
        //   427: aload           4
        //   429: ifnull          592
        //   432: aload_0        
        //   433: aload           4
        //   435: aload_1        
        //   436: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiFile;)Z
        //   439: ifeq            592
        //   442: goto            449
        //   445: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   448: athrow         
        //   449: aload_0        
        //   450: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   453: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //   456: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.getGeneratedFromProperty:()Ljava/lang/String;
        //   461: ifnull          492
        //   464: goto            471
        //   467: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   470: athrow         
        //   471: aload_2        
        //   472: aload           4
        //   474: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   479: pop            
        //   480: aload_0        
        //   481: iconst_1       
        //   482: putfield        com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.myChangeProperty:Z
        //   485: goto            592
        //   488: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   491: athrow         
        //   492: new             Ljava/lang/StringBuilder;
        //   495: dup            
        //   496: invokespecial   java/lang/StringBuilder.<init>:()V
        //   499: ldc             "Do you want to change type of "
        //   501: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   504: aload           4
        //   506: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.getNameWithKindLowercase:()Ljava/lang/String;
        //   511: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   514: ldc             " as well?"
        //   516: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   519: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   522: astore          5
        //   524: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //   527: invokeinterface com/intellij/openapi/application/Application.isUnitTestMode:()Z
        //   532: ifeq            543
        //   535: iconst_0       
        //   536: goto            553
        //   539: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   542: athrow         
        //   543: aload           5
        //   545: ldc             "Change Type"
        //   547: invokestatic    com/intellij/openapi/ui/Messages.getQuestionIcon:()Ljavax/swing/Icon;
        //   550: invokestatic    com/intellij/openapi/ui/Messages.showYesNoCancelDialog:(Ljava/lang/String;Ljava/lang/String;Ljavax/swing/Icon;)I
        //   553: istore          6
        //   555: iload           6
        //   557: ifne            581
        //   560: aload_2        
        //   561: aload           4
        //   563: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   568: pop            
        //   569: aload_0        
        //   570: iconst_1       
        //   571: putfield        com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.myChangeProperty:Z
        //   574: goto            592
        //   577: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   580: athrow         
        //   581: iload           6
        //   583: iconst_2       
        //   584: if_icmpne       592
        //   587: return         
        //   588: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   591: athrow         
        //   592: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //   595: aload_0        
        //   596: aload_2        
        //   597: invokedynamic   run:(Lcom/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction;Ljava/util/List;)Ljava/lang/Runnable;
        //   602: invokeinterface com/intellij/openapi/application/Application.runWriteAction:(Ljava/lang/Runnable;)V
        //   607: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  18     35     38     42     Ljava/lang/IllegalArgumentException;
        //  28     92     92     96     Ljava/lang/IllegalArgumentException;
        //  96     116    119    123    Ljava/lang/IllegalArgumentException;
        //  106    130    133    137    Ljava/lang/IllegalArgumentException;
        //  123    158    158    162    Ljava/lang/IllegalArgumentException;
        //  173    184    187    191    Ljava/lang/IllegalArgumentException;
        //  180    200    203    207    Ljava/lang/IllegalArgumentException;
        //  191    215    218    222    Ljava/lang/IllegalArgumentException;
        //  266    281    284    288    Ljava/lang/IllegalArgumentException;
        //  320    341    344    348    Ljava/lang/IllegalArgumentException;
        //  331    352    352    356    Ljava/lang/IllegalArgumentException;
        //  368    385    385    389    Ljava/lang/IllegalArgumentException;
        //  389    396    396    400    Ljava/lang/IllegalArgumentException;
        //  427    442    445    449    Ljava/lang/IllegalArgumentException;
        //  432    464    467    471    Ljava/lang/IllegalArgumentException;
        //  449    488    488    492    Ljava/lang/IllegalArgumentException;
        //  524    539    539    543    Ljava/lang/IllegalArgumentException;
        //  555    577    577    581    Ljava/lang/IllegalArgumentException;
        //  581    588    588    592    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0123:
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
    
    private boolean a(final OCSymbol ocSymbol, @NotNull final PsiFile psiFile) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction", "needToChangeType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCType ocType = null;
        Label_0073: {
            try {
                if (ocSymbol instanceof OCMethodSymbol) {
                    ocType = ((OCMethodSymbol)ocSymbol).getReturnType();
                    break Label_0073;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            ocType = ocSymbol.getType();
        }
        OCType returnType = ocType;
        Label_0109: {
            Label_0101: {
                try {
                    if (!this.myReturnTypeMode) {
                        break Label_0109;
                    }
                    final OCType ocType2 = returnType;
                    final boolean b = ocType2 instanceof OCFunctionType;
                    if (!b) {
                        return true;
                    }
                    break Label_0101;
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    final OCType ocType2 = returnType;
                    final boolean b = ocType2 instanceof OCFunctionType;
                    if (!b) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            returnType = ((OCFunctionType)returnType).getReturnType();
            try {
                if (returnType.resolve(psiFile) instanceof OCTypeParameterType) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        try {
            if (!new OCTypeEqualityAfterResolvingVisitor(this.mySubstitutionType, false, false, false, true, false, psiFile).equal(returnType)) {
                return true;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        return false;
    }
    
    private void a(final PsiElement p0, final OCSymbol p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: invokestatic    com/intellij/codeInsight/FileModificationService.getInstance:()Lcom/intellij/codeInsight/FileModificationService;
        //     3: aload_1        
        //     4: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //     9: invokevirtual   com/intellij/codeInsight/FileModificationService.prepareFileForWrite:(Lcom/intellij/psi/PsiFile;)Z
        //    12: ifne            20
        //    15: return         
        //    16: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    19: athrow         
        //    20: aconst_null    
        //    21: astore_3       
        //    22: aload_0        
        //    23: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.mySubstitutionType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    26: astore          5
        //    28: aload_1        
        //    29: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //    32: ifeq            59
        //    35: aload_1        
        //    36: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //    39: astore_3       
        //    40: aload_1        
        //    41: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    46: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //    49: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getTypeElement:()Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //    54: astore          4
        //    56: goto            233
        //    59: aload_1        
        //    60: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMethodSelectorPart;
        //    63: ifeq            80
        //    66: aload_1        
        //    67: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMethodSelectorPart;
        //    70: invokeinterface com/jetbrains/cidr/lang/psi/OCMethodSelectorPart.getTypeElement:()Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //    75: astore          4
        //    77: goto            233
        //    80: aload_1        
        //    81: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //    84: ifeq            101
        //    87: aload_1        
        //    88: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //    91: invokeinterface com/jetbrains/cidr/lang/psi/OCMethod.getReturnTypeElement:()Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //    96: astore          4
        //    98: goto            233
        //   101: aload_1        
        //   102: instanceof      Lcom/jetbrains/cidr/lang/psi/OCBlockExpression;
        //   105: ifeq            122
        //   108: aload_1        
        //   109: checkcast       Lcom/jetbrains/cidr/lang/psi/OCBlockExpression;
        //   112: invokeinterface com/jetbrains/cidr/lang/psi/OCBlockExpression.getReturnTypeElement:()Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //   117: astore          4
        //   119: goto            233
        //   122: aload_1        
        //   123: instanceof      Lcom/jetbrains/cidr/lang/psi/OCLambdaExpression;
        //   126: ifeq            143
        //   129: aload_1        
        //   130: checkcast       Lcom/jetbrains/cidr/lang/psi/OCLambdaExpression;
        //   133: invokeinterface com/jetbrains/cidr/lang/psi/OCLambdaExpression.getReturnTypeElement:()Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //   138: astore          4
        //   140: goto            233
        //   143: aload_1        
        //   144: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //   147: ifeq            232
        //   150: aload_1        
        //   151: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   156: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSynthesizeProperty;
        //   159: ifeq            232
        //   162: goto            169
        //   165: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   168: athrow         
        //   169: aload_0        
        //   170: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.myChangeProperty:Z
        //   173: ifeq            232
        //   176: goto            183
        //   179: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   182: athrow         
        //   183: aload_2        
        //   184: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //   187: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.getAssociatedProperty:()Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   192: astore_2       
        //   193: aload_2        
        //   194: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.locateDefinition:()Lcom/intellij/psi/PsiElement;
        //   199: astore_1       
        //   200: aload_1        
        //   201: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   204: ifeq            231
        //   207: aload_1        
        //   208: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   211: astore_3       
        //   212: aload_1        
        //   213: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   218: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //   221: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getTypeElement:()Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //   226: astore          4
        //   228: goto            233
        //   231: return         
        //   232: return         
        //   233: aload           4
        //   235: ifnonnull       303
        //   238: aload_1        
        //   239: instanceof      Lcom/jetbrains/cidr/lang/psi/OCBlockExpression;
        //   242: ifeq            302
        //   245: goto            252
        //   248: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   251: athrow         
        //   252: aload           5
        //   254: aload_1        
        //   255: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getBestNameInContext:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   258: aload_1        
        //   259: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.typeElementFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //   262: astore          6
        //   264: aload_1        
        //   265: checkcast       Lcom/jetbrains/cidr/lang/psi/OCBlockExpression;
        //   268: invokeinterface com/jetbrains/cidr/lang/psi/OCBlockExpression.getParameterList:()Lcom/jetbrains/cidr/lang/psi/OCParameterList;
        //   273: astore          7
        //   275: aload           7
        //   277: ifnonnull       291
        //   280: aload_1        
        //   281: checkcast       Lcom/jetbrains/cidr/lang/psi/OCBlockExpression;
        //   284: invokeinterface com/jetbrains/cidr/lang/psi/OCBlockExpression.getBody:()Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //   289: astore          7
        //   291: aload_1        
        //   292: aload           6
        //   294: aload           7
        //   296: invokeinterface com/intellij/psi/PsiElement.addBefore:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   301: pop            
        //   302: return         
        //   303: aload_3        
        //   304: ifnull          941
        //   307: aload_3        
        //   308: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCNormalizeUtil.normalizeDeclarator:(Lcom/jetbrains/cidr/lang/psi/OCDeclarator;)Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //   311: astore          6
        //   313: aload           6
        //   315: ifnonnull       323
        //   318: return         
        //   319: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   322: athrow         
        //   323: aload           6
        //   325: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getDeclarators:()Ljava/util/List;
        //   330: iconst_0       
        //   331: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   336: checkcast       Lcom/intellij/psi/PsiElement;
        //   339: astore_1       
        //   340: ldc             "*"
        //   342: aload_1        
        //   343: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.binaryOperatorFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   346: astore          7
        //   348: ldc             "^"
        //   350: aload_1        
        //   351: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.binaryOperatorFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   354: astore          8
        //   356: ldc             "&"
        //   358: aload_1        
        //   359: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.binaryOperatorFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   362: astore          9
        //   364: ldc             "&&"
        //   366: aload_1        
        //   367: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.binaryOperatorFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   370: astore          10
        //   372: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.CONST_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   375: aload_1        
        //   376: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.create:(Lcom/jetbrains/cidr/lang/parser/OCElementType;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   379: astore          11
        //   381: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.VOLATILE_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   384: aload_1        
        //   385: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.create:(Lcom/jetbrains/cidr/lang/parser/OCElementType;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   388: astore          12
        //   390: new             Ljava/util/ArrayList;
        //   393: dup            
        //   394: invokespecial   java/util/ArrayList.<init>:()V
        //   397: astore          13
        //   399: aload           5
        //   401: instanceof      Lcom/jetbrains/cidr/lang/types/OCBlockPointerType;
        //   404: istore          14
        //   406: aload_0        
        //   407: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.myReturnTypeMode:Z
        //   410: ifne            839
        //   413: aload           5
        //   415: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   418: ifeq            443
        //   421: goto            428
        //   424: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   427: athrow         
        //   428: aload           5
        //   430: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isPointerToID:()Z
        //   433: ifeq            458
        //   436: goto            443
        //   439: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   442: athrow         
        //   443: aload           5
        //   445: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   448: ifeq            636
        //   451: goto            458
        //   454: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   457: athrow         
        //   458: aload           5
        //   460: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getAliasName:()Ljava/lang/String;
        //   463: ifnonnull       636
        //   466: goto            473
        //   469: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   472: athrow         
        //   473: aload           5
        //   475: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isConst:()Z
        //   478: ifeq            505
        //   481: goto            488
        //   484: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   487: athrow         
        //   488: aload           13
        //   490: iconst_0       
        //   491: aload           11
        //   493: invokeinterface java/util/List.add:(ILjava/lang/Object;)V
        //   498: goto            505
        //   501: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   504: athrow         
        //   505: aload           5
        //   507: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isVolatile:()Z
        //   510: ifeq            530
        //   513: aload           13
        //   515: iconst_0       
        //   516: aload           12
        //   518: invokeinterface java/util/List.add:(ILjava/lang/Object;)V
        //   523: goto            530
        //   526: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   529: athrow         
        //   530: aload           5
        //   532: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   535: ifeq            582
        //   538: aload           5
        //   540: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   543: astore          15
        //   545: aload           13
        //   547: iconst_0       
        //   548: aload           15
        //   550: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.isRvalueRef:()Z
        //   553: ifeq            565
        //   556: aload           10
        //   558: goto            567
        //   561: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   564: athrow         
        //   565: aload           9
        //   567: invokeinterface java/util/List.add:(ILjava/lang/Object;)V
        //   572: aload           15
        //   574: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   577: astore          5
        //   579: goto            413
        //   582: aload           13
        //   584: iconst_0       
        //   585: iload           14
        //   587: ifeq            616
        //   590: aload           13
        //   592: invokeinterface java/util/List.isEmpty:()Z
        //   597: ifeq            616
        //   600: goto            607
        //   603: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   606: athrow         
        //   607: aload           8
        //   609: goto            618
        //   612: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   615: athrow         
        //   616: aload           7
        //   618: invokeinterface java/util/List.add:(ILjava/lang/Object;)V
        //   623: aload           5
        //   625: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   628: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   631: astore          5
        //   633: goto            413
        //   636: aload           6
        //   638: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getDeclarators:()Ljava/util/List;
        //   643: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   648: astore          15
        //   650: aload           15
        //   652: invokeinterface java/util/Iterator.hasNext:()Z
        //   657: ifeq            756
        //   660: aload           15
        //   662: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   667: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   670: astore          16
        //   672: aload           16
        //   674: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.isPointerToFunction:()Z
        //   679: istore          17
        //   681: aload_0        
        //   682: aload           16
        //   684: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Lcom/intellij/psi/PsiElement;)V
        //   687: aload           6
        //   689: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //   692: ifeq            707
        //   695: iload           17
        //   697: ifeq            753
        //   700: goto            707
        //   703: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   706: athrow         
        //   707: aload           13
        //   709: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   714: astore          18
        //   716: aload           18
        //   718: invokeinterface java/util/Iterator.hasNext:()Z
        //   723: ifeq            753
        //   726: aload           18
        //   728: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   733: checkcast       Lcom/intellij/psi/PsiElement;
        //   736: astore          19
        //   738: aload           16
        //   740: aload           19
        //   742: invokeinterface com/intellij/psi/PsiElement.copy:()Lcom/intellij/psi/PsiElement;
        //   747: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)V
        //   750: goto            716
        //   753: goto            650
        //   756: aload           5
        //   758: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   761: ifeq            892
        //   764: aload_1        
        //   765: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   768: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getParameterList:()Lcom/jetbrains/cidr/lang/psi/OCParameterList;
        //   773: ifnull          802
        //   776: goto            783
        //   779: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   782: athrow         
        //   783: aload_1        
        //   784: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   787: aload           5
        //   789: checkcast       Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   792: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Lcom/jetbrains/cidr/lang/psi/OCDeclarator;Lcom/jetbrains/cidr/lang/types/OCFunctionType;)V
        //   795: goto            838
        //   798: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   801: athrow         
        //   802: aload_3        
        //   803: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getName:()Ljava/lang/String;
        //   808: aload_0        
        //   809: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.mySubstitutionType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   812: aload_3        
        //   813: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getInitializer:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   818: aload           6
        //   820: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.declarationStatement:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCDeclarationStatement;
        //   823: astore          15
        //   825: aload           6
        //   827: aload           15
        //   829: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarationStatement.getDeclaration:()Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //   834: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   837: pop            
        //   838: return         
        //   839: aload           6
        //   841: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //   844: ifeq            892
        //   847: aload           6
        //   849: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getDeclarators:()Ljava/util/List;
        //   854: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   859: astore          15
        //   861: aload           15
        //   863: invokeinterface java/util/Iterator.hasNext:()Z
        //   868: ifeq            892
        //   871: aload           15
        //   873: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   878: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   881: astore          16
        //   883: aload_0        
        //   884: aload           16
        //   886: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Lcom/intellij/psi/PsiElement;)V
        //   889: goto            861
        //   892: aload           6
        //   894: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getTypeElement:()Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //   899: astore          4
        //   901: aload_0        
        //   902: aload           4
        //   904: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Lcom/intellij/psi/PsiElement;)V
        //   907: aload           4
        //   909: ifnull          936
        //   912: aload           4
        //   914: invokeinterface com/jetbrains/cidr/lang/psi/OCTypeElement.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   919: aload           5
        //   921: aload           4
        //   923: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equals:(Ljava/lang/Object;Lcom/intellij/psi/PsiElement;)Z
        //   926: ifeq            941
        //   929: goto            936
        //   932: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   935: athrow         
        //   936: return         
        //   937: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   940: athrow         
        //   941: aload_1        
        //   942: instanceof      Lcom/jetbrains/cidr/lang/psi/OCBlockExpression;
        //   945: ifeq            975
        //   948: aload           5
        //   950: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isVoid:()Z
        //   953: ifeq            975
        //   956: goto            963
        //   959: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   962: athrow         
        //   963: aload           4
        //   965: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.delete:(Lcom/intellij/psi/PsiElement;)V
        //   968: goto            1012
        //   971: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   974: athrow         
        //   975: aload           5
        //   977: aload           4
        //   979: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getBestNameInContext:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   982: aload_1        
        //   983: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.typeElementFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //   986: astore          6
        //   988: aload           6
        //   990: aload           4
        //   992: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.replaceDeclarationQualifiers:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)V
        //   995: aload           4
        //   997: aload           6
        //   999: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //  1002: checkcast       Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //  1005: astore          4
        //  1007: aload           4
        //  1009: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.fixAllSymbolsRecursively:(Lcom/intellij/psi/PsiElement;)V
        //  1012: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      16     16     20     Ljava/lang/IllegalArgumentException;
        //  143    162    165    169    Ljava/lang/IllegalArgumentException;
        //  150    176    179    183    Ljava/lang/IllegalArgumentException;
        //  233    245    248    252    Ljava/lang/IllegalArgumentException;
        //  313    319    319    323    Ljava/lang/IllegalArgumentException;
        //  406    421    424    428    Ljava/lang/IllegalArgumentException;
        //  413    436    439    443    Ljava/lang/IllegalArgumentException;
        //  428    451    454    458    Ljava/lang/IllegalArgumentException;
        //  443    466    469    473    Ljava/lang/IllegalArgumentException;
        //  458    481    484    488    Ljava/lang/IllegalArgumentException;
        //  473    498    501    505    Ljava/lang/IllegalArgumentException;
        //  505    523    526    530    Ljava/lang/IllegalArgumentException;
        //  545    561    561    565    Ljava/lang/IllegalArgumentException;
        //  582    600    603    607    Ljava/lang/IllegalArgumentException;
        //  590    612    612    616    Ljava/lang/IllegalArgumentException;
        //  681    700    703    707    Ljava/lang/IllegalArgumentException;
        //  756    776    779    783    Ljava/lang/IllegalArgumentException;
        //  764    798    798    802    Ljava/lang/IllegalArgumentException;
        //  901    929    932    936    Ljava/lang/IllegalArgumentException;
        //  912    937    937    941    Ljava/lang/IllegalArgumentException;
        //  941    956    959    963    Ljava/lang/IllegalArgumentException;
        //  948    971    971    975    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0413:
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
    
    private static void a(final OCDeclarator p0, final OCFunctionType p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getParameterList:()Lcom/jetbrains/cidr/lang/psi/OCParameterList;
        //     6: astore_2       
        //     7: aload_0        
        //     8: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getParent:()Lcom/intellij/psi/PsiElement;
        //    13: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //    16: ifeq            77
        //    19: aload_0        
        //    20: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getParent:()Lcom/intellij/psi/PsiElement;
        //    25: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //    28: astore_3       
        //    29: aload_3        
        //    30: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDeclaration.getReturnType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    35: aload_1        
        //    36: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.getReturnType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    39: aload_0        
        //    40: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //    45: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equalsAfterResolving:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Z
        //    48: ifne            77
        //    51: aload_1        
        //    52: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.getReturnType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    55: aload_0        
        //    56: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getBestNameInContext:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //    59: aload_0        
        //    60: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.typeElementFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //    63: astore          4
        //    65: aload_3        
        //    66: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDeclaration.getTypeElement:()Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //    71: aload           4
        //    73: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //    76: pop            
        //    77: aload_2        
        //    78: ifnonnull       86
        //    81: return         
        //    82: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    85: athrow         
        //    86: aload_1        
        //    87: iconst_1       
        //    88: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.getParameterTypes:(Z)Ljava/util/List;
        //    91: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    96: astore_3       
        //    97: new             Ljava/util/HashSet;
        //   100: dup            
        //   101: invokespecial   java/util/HashSet.<init>:()V
        //   104: astore          4
        //   106: aload_2        
        //   107: invokeinterface com/jetbrains/cidr/lang/psi/OCParameterList.getParameterDeclarations:()Ljava/util/List;
        //   112: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   117: astore          5
        //   119: aload           5
        //   121: invokeinterface java/util/Iterator.hasNext:()Z
        //   126: ifeq            316
        //   129: aload           5
        //   131: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   136: checkcast       Lcom/jetbrains/cidr/lang/psi/OCParameterDeclaration;
        //   139: astore          6
        //   141: aload_3        
        //   142: invokeinterface java/util/Iterator.hasNext:()Z
        //   147: ifeq            308
        //   150: aload_3        
        //   151: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   156: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   159: astore          7
        //   161: aload           6
        //   163: invokeinterface com/jetbrains/cidr/lang/psi/OCParameterDeclaration.getDeclarator:()Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   168: astore          8
        //   170: aload           8
        //   172: ifnull          256
        //   175: aload           8
        //   177: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   182: aload           7
        //   184: aload           6
        //   186: invokeinterface com/jetbrains/cidr/lang/psi/OCParameterDeclaration.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   191: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equalsAfterResolving:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Z
        //   194: ifne            305
        //   197: goto            204
        //   200: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   203: athrow         
        //   204: aload           8
        //   206: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getNameIdentifier:()Lcom/intellij/psi/PsiElement;
        //   211: ifnull          235
        //   214: goto            221
        //   217: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   220: athrow         
        //   221: aload           8
        //   223: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getName:()Ljava/lang/String;
        //   228: goto            237
        //   231: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   234: athrow         
        //   235: ldc             ""
        //   237: astore          9
        //   239: aload           6
        //   241: aload           9
        //   243: aload           7
        //   245: aload_0        
        //   246: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.paramDeclarationByNameAndType:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCParameterDeclaration;
        //   249: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.replaceHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   252: pop            
        //   253: goto            305
        //   256: aload           7
        //   258: aload_0        
        //   259: aload           4
        //   261: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.suggestForType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;Ljava/util/Collection;)Ljava/util/Collection;
        //   264: invokeinterface java/util/Collection.iterator:()Ljava/util/Iterator;
        //   269: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   274: checkcast       Ljava/lang/String;
        //   277: astore          9
        //   279: aload           4
        //   281: aload           9
        //   283: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   288: pop            
        //   289: aload           6
        //   291: aload           9
        //   293: aload           7
        //   295: aload_0        
        //   296: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.paramDeclarationByNameAndType:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCParameterDeclaration;
        //   299: invokeinterface com/jetbrains/cidr/lang/psi/OCParameterDeclaration.replace:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   304: pop            
        //   305: goto            313
        //   308: aload           6
        //   310: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.delete:(Lcom/intellij/psi/PsiElement;)V
        //   313: goto            119
        //   316: aload_3        
        //   317: invokeinterface java/util/Iterator.hasNext:()Z
        //   322: ifeq            385
        //   325: aload_3        
        //   326: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   331: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   334: astore          5
        //   336: aload           5
        //   338: aload_0        
        //   339: aload           4
        //   341: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.suggestForType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;Ljava/util/Collection;)Ljava/util/Collection;
        //   344: invokeinterface java/util/Collection.iterator:()Ljava/util/Iterator;
        //   349: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   354: checkcast       Ljava/lang/String;
        //   357: astore          6
        //   359: aload           4
        //   361: aload           6
        //   363: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   368: pop            
        //   369: aload_2        
        //   370: aload           6
        //   372: aload           5
        //   374: aload_0        
        //   375: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.paramDeclarationByNameAndType:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCParameterDeclaration;
        //   378: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.add:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   381: pop            
        //   382: goto            316
        //   385: aload_1        
        //   386: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.isConst:()Z
        //   389: ifeq            419
        //   392: aload_0        
        //   393: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getParent:()Lcom/intellij/psi/PsiElement;
        //   398: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.CONST_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   401: aload_0        
        //   402: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.create:(Lcom/jetbrains/cidr/lang/parser/OCElementType;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   405: aload_0        
        //   406: invokeinterface com/intellij/psi/PsiElement.addAfter:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   411: pop            
        //   412: goto            419
        //   415: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   418: athrow         
        //   419: aload_1        
        //   420: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.isVolatile:()Z
        //   423: ifeq            453
        //   426: aload_0        
        //   427: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getParent:()Lcom/intellij/psi/PsiElement;
        //   432: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.VOLATILE_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   435: aload_0        
        //   436: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.create:(Lcom/jetbrains/cidr/lang/parser/OCElementType;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   439: aload_0        
        //   440: invokeinterface com/intellij/psi/PsiElement.addAfter:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   445: pop            
        //   446: goto            453
        //   449: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangeTypeIntentionAction.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   452: athrow         
        //   453: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  77     82     82     86     Ljava/lang/IllegalArgumentException;
        //  170    197    200    204    Ljava/lang/IllegalArgumentException;
        //  175    214    217    221    Ljava/lang/IllegalArgumentException;
        //  204    231    231    235    Ljava/lang/IllegalArgumentException;
        //  385    412    415    419    Ljava/lang/IllegalArgumentException;
        //  419    446    449    453    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0204:
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
    
    private void a(final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ASTNode firstChildNode = psiElement.getNode().getFirstChildNode();
        final ArrayList<PsiElement> list = new ArrayList<PsiElement>();
        final ArrayList<Pair> list2 = new ArrayList<Pair>();
        Object psi = null;
        while (firstChildNode != null) {
            final IElementType elementType = firstChildNode.getElementType();
            final ASTNode treeNext = firstChildNode.getTreeNext();
            Label_0193: {
                Label_0093: {
                    Label_0086: {
                        try {
                            if (!this.myReturnTypeMode) {
                                break Label_0093;
                            }
                            final IElementType elementType2 = elementType;
                            final OCElementType ocElementType = OCElementTypes.PARAMETER_LIST;
                            if (elementType2 == ocElementType) {
                                break Label_0086;
                            }
                            break Label_0093;
                        }
                        catch (IllegalArgumentException ex2) {
                            throw a(ex2);
                        }
                        try {
                            final IElementType elementType2 = elementType;
                            final OCElementType ocElementType = OCElementTypes.PARAMETER_LIST;
                            if (elementType2 == ocElementType) {
                                break;
                            }
                        }
                        catch (IllegalArgumentException ex3) {
                            throw a(ex3);
                        }
                    }
                    try {
                        if (OCTokenTypes.DECLARATOR_MODIFIERS.contains(elementType)) {
                            list.add(firstChildNode.getPsi());
                            break Label_0193;
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                }
                if (elementType == OCTokenTypes.LBRACKET) {
                    psi = firstChildNode.getPsi();
                }
                else {
                    Label_0164: {
                        try {
                            if (elementType != OCTokenTypes.RBRACKET) {
                                break Label_0193;
                            }
                            final Object o = psi;
                            if (o != null) {
                                break Label_0164;
                            }
                            break Label_0164;
                        }
                        catch (IllegalArgumentException ex5) {
                            throw a(ex5);
                        }
                        try {
                            final Object o = psi;
                            if (o != null) {
                                list2.add(Pair.create(psi, (Object)firstChildNode.getPsi()));
                            }
                        }
                        catch (IllegalArgumentException ex6) {
                            throw a(ex6);
                        }
                    }
                    psi = null;
                }
            }
            firstChildNode = treeNext;
        }
        final Iterator<Object> iterator = list.iterator();
        while (iterator.hasNext()) {
            iterator.next().delete();
        }
        for (final Pair pair : list2) {
            psiElement.deleteChildRange((PsiElement)pair.getFirst(), (PsiElement)pair.getSecond());
        }
    }
    
    private static void a(final PsiElement psiElement, final PsiElement psiElement2) {
        try {
            if (psiElement == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final ASTNode node = psiElement.getNode();
        ASTNode anchorBefore = node.getFirstChildNode();
        while (true) {
            Label_0046: {
                try {
                    if (anchorBefore == null) {
                        break;
                    }
                    final ASTNode astNode = anchorBefore;
                    final IElementType elementType = astNode.getElementType();
                    final OCElementType ocElementType = OCTokenTypes.IDENTIFIER;
                    if (elementType == ocElementType) {
                        break Label_0046;
                    }
                    break Label_0046;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final ASTNode astNode = anchorBefore;
                    final IElementType elementType = astNode.getElementType();
                    final OCElementType ocElementType = OCTokenTypes.IDENTIFIER;
                    if (elementType == ocElementType) {
                        CodeEditUtil.addChild(node, psiElement2.getNode(), anchorBefore);
                        return;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            anchorBefore = anchorBefore.getTreeNext();
        }
        CodeEditUtil.addChild(node, psiElement2.getNode(), null);
    }
    
    @Override
    public boolean startInWriteAction() {
        return false;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
