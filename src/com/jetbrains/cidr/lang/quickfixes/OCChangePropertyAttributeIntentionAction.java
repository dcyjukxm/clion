// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.IncorrectOperationException;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.psi.OCProperty;

public class OCChangePropertyAttributeIntentionAction extends OCPsiElementQuickFix<OCProperty>
{
    private OCPropertySymbol myPropertySymbol;
    private OCPropertySymbol.PropertyAttribute myOldAttr;
    private OCPropertySymbol.PropertyAttribute myNewAttr;
    private String myNewValue;
    private String mySubject;
    
    public OCChangePropertyAttributeIntentionAction(final OCPropertySymbol myPropertySymbol, @Nullable final OCPropertySymbol.PropertyAttribute myOldAttr, @Nullable final OCPropertySymbol.PropertyAttribute myNewAttr, @Nullable final String myNewValue) {
        super(null);
        this.mySubject = "property";
        this.myPropertySymbol = myPropertySymbol;
        this.myOldAttr = myOldAttr;
        this.myNewAttr = myNewAttr;
        this.myNewValue = myNewValue;
    }
    
    public OCChangePropertyAttributeIntentionAction(final OCPropertySymbol ocPropertySymbol, @Nullable final OCPropertySymbol.PropertyAttribute propertyAttribute, @Nullable final OCPropertySymbol.PropertyAttribute propertyAttribute2, @Nullable final String s, final String mySubject) {
        this(ocPropertySymbol, propertyAttribute, propertyAttribute2, s);
        this.mySubject = mySubject;
    }
    
    public OCChangePropertyAttributeIntentionAction(final OCProperty ocProperty, final OCPropertySymbol.PropertyAttribute myOldAttr, final OCPropertySymbol.PropertyAttribute myNewAttr) {
        super((PsiElement)ocProperty);
        this.mySubject = "property";
        this.myOldAttr = myOldAttr;
        this.myNewAttr = myNewAttr;
    }
    
    @Override
    protected String getTextInternal() {
        String string = null;
        Label_0043: {
            try {
                if (this.myPropertySymbol != null) {
                    string = "' for " + this.myPropertySymbol.getNameWithKindLowercase();
                    break Label_0043;
                }
            }
            catch (IncorrectOperationException ex) {
                throw c(ex);
            }
            string = "'";
        }
        final String s = string;
        try {
            if (this.myOldAttr == null) {
                return "Enable attribute '" + this.myNewAttr.getTokenName() + s;
            }
        }
        catch (IncorrectOperationException ex2) {
            throw c(ex2);
        }
        try {
            if (this.myNewAttr == null) {
                return "Disable attribute '" + this.myOldAttr.getTokenName() + s;
            }
        }
        catch (IncorrectOperationException ex3) {
            throw c(ex3);
        }
        try {
            if (this.myNewValue != null) {
                return "Change value of attribute '" + this.myOldAttr.getTokenName() + "' to '" + this.myNewValue + "'";
            }
        }
        catch (IncorrectOperationException ex4) {
            throw c(ex4);
        }
        return "Change " + this.mySubject + " attribute '" + this.myOldAttr.getTokenName() + "' to '" + this.myNewAttr.getTokenName() + "'";
    }
    
    @NotNull
    public String getFamilyName() {
        String s;
        try {
            s = "Change property attribute";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction", "getFamilyName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw c(ex);
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
        //     1: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.myElementPtr:Lcom/intellij/psi/SmartPsiElementPointer;
        //     4: ifnull          26
        //     7: aload_0        
        //     8: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.myElementPtr:Lcom/intellij/psi/SmartPsiElementPointer;
        //    11: invokeinterface com/intellij/psi/SmartPsiElementPointer.getElement:()Lcom/intellij/psi/PsiElement;
        //    16: checkcast       Lcom/jetbrains/cidr/lang/psi/OCProperty;
        //    19: goto            27
        //    22: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    25: athrow         
        //    26: aconst_null    
        //    27: astore_1       
        //    28: aload_1        
        //    29: ifnull          53
        //    32: aload_1        
        //    33: invokestatic    com/jetbrains/cidr/lang/search/scopes/OCSearchScope.isInProjectSources:(Lcom/intellij/psi/PsiElement;)Z
        //    36: ifeq            103
        //    39: goto            46
        //    42: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    45: athrow         
        //    46: goto            63
        //    49: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    52: athrow         
        //    53: aload_0        
        //    54: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.myPropertySymbol:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //    57: invokestatic    com/jetbrains/cidr/lang/search/scopes/OCSearchScope.isInProjectSources:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //    60: ifeq            103
        //    63: aload_0        
        //    64: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.myOldAttr:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //    67: aload_0        
        //    68: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.myNewAttr:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //    71: if_acmpne       95
        //    74: goto            81
        //    77: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    80: athrow         
        //    81: aload_0        
        //    82: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.myNewValue:Ljava/lang/String;
        //    85: ifnull          103
        //    88: goto            95
        //    91: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    94: athrow         
        //    95: iconst_1       
        //    96: goto            104
        //    99: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   102: athrow         
        //   103: iconst_0       
        //   104: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      22     22     26     Lcom/intellij/util/IncorrectOperationException;
        //  28     39     42     46     Lcom/intellij/util/IncorrectOperationException;
        //  32     49     49     53     Lcom/intellij/util/IncorrectOperationException;
        //  53     74     77     81     Lcom/intellij/util/IncorrectOperationException;
        //  63     88     91     95     Lcom/intellij/util/IncorrectOperationException;
        //  81     99     99     103    Lcom/intellij/util/IncorrectOperationException;
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
    
    @Override
    public void invoke(@NotNull final Project p0, @Nullable final Editor p1, final PsiFile p2) throws IncorrectOperationException {
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
        //    24: ldc             "com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "invoke"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    43: athrow         
        //    44: aload_1        
        //    45: invokestatic    com/intellij/psi/PsiDocumentManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/PsiDocumentManager;
        //    48: invokevirtual   com/intellij/psi/PsiDocumentManager.commitAllDocuments:()V
        //    51: aload_0        
        //    52: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.myElementPtr:Lcom/intellij/psi/SmartPsiElementPointer;
        //    55: ifnull          77
        //    58: aload_0        
        //    59: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.myElementPtr:Lcom/intellij/psi/SmartPsiElementPointer;
        //    62: invokeinterface com/intellij/psi/SmartPsiElementPointer.getElement:()Lcom/intellij/psi/PsiElement;
        //    67: checkcast       Lcom/jetbrains/cidr/lang/psi/OCProperty;
        //    70: goto            78
        //    73: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    76: athrow         
        //    77: aconst_null    
        //    78: astore          4
        //    80: aload           4
        //    82: ifnonnull       104
        //    85: aload_0        
        //    86: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.myPropertySymbol:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //    89: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.locateDefinition:()Lcom/intellij/psi/PsiElement;
        //    94: ldc             Lcom/jetbrains/cidr/lang/psi/OCProperty;.class
        //    96: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //    99: checkcast       Lcom/jetbrains/cidr/lang/psi/OCProperty;
        //   102: astore          4
        //   104: aload           4
        //   106: ifnull          132
        //   109: invokestatic    com/intellij/codeInsight/FileModificationService.getInstance:()Lcom/intellij/codeInsight/FileModificationService;
        //   112: aload           4
        //   114: invokeinterface com/jetbrains/cidr/lang/psi/OCProperty.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   119: invokevirtual   com/intellij/codeInsight/FileModificationService.prepareFileForWrite:(Lcom/intellij/psi/PsiFile;)Z
        //   122: ifne            137
        //   125: goto            132
        //   128: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   131: athrow         
        //   132: return         
        //   133: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   136: athrow         
        //   137: aload           4
        //   139: invokeinterface com/jetbrains/cidr/lang/psi/OCProperty.getPropertyAttributesList:()Lcom/jetbrains/cidr/lang/psi/OCPropertyAttributesList;
        //   144: astore          5
        //   146: aconst_null    
        //   147: astore          6
        //   149: aconst_null    
        //   150: astore          7
        //   152: aload_0        
        //   153: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.myNewAttr:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   156: ifnull          191
        //   159: aload_0        
        //   160: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.myNewAttr:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   163: aload_0        
        //   164: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.myNewValue:Ljava/lang/String;
        //   167: aload_3        
        //   168: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.propertyAttributeList:(Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCPropertyAttributesList;
        //   171: astore          6
        //   173: aload           6
        //   175: invokeinterface com/jetbrains/cidr/lang/psi/OCPropertyAttributesList.getAttributes:()Ljava/util/List;
        //   180: iconst_0       
        //   181: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   186: checkcast       Lcom/jetbrains/cidr/lang/psi/OCPropertyAttribute;
        //   189: astore          7
        //   191: aload           5
        //   193: ifnonnull       271
        //   196: aload_0        
        //   197: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.myNewAttr:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   200: ifnull          270
        //   203: goto            210
        //   206: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   209: athrow         
        //   210: aload           4
        //   212: invokeinterface com/jetbrains/cidr/lang/psi/OCProperty.getDeclaration:()Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //   217: astore          8
        //   219: aload           8
        //   221: ifnull          238
        //   224: aload           8
        //   226: invokeinterface com/intellij/psi/PsiElement.getPrevSibling:()Lcom/intellij/psi/PsiElement;
        //   231: goto            239
        //   234: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   237: athrow         
        //   238: aconst_null    
        //   239: astore          9
        //   241: ldc             "IBOutlet"
        //   243: aload           9
        //   245: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getTextWithMacros:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   248: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   251: ifeq            258
        //   254: aload           9
        //   256: astore          8
        //   258: aload           4
        //   260: aload           6
        //   262: aload           8
        //   264: invokeinterface com/jetbrains/cidr/lang/psi/OCProperty.addBefore:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   269: pop            
        //   270: return         
        //   271: aload           5
        //   273: invokeinterface com/jetbrains/cidr/lang/psi/OCPropertyAttributesList.getAttributes:()Ljava/util/List;
        //   278: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   283: astore          8
        //   285: aload           8
        //   287: invokeinterface java/util/Iterator.hasNext:()Z
        //   292: ifeq            443
        //   295: aload           8
        //   297: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   302: checkcast       Lcom/jetbrains/cidr/lang/psi/OCPropertyAttribute;
        //   305: astore          9
        //   307: aload_0        
        //   308: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.myOldAttr:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   311: ifnull          401
        //   314: aload           9
        //   316: invokeinterface com/jetbrains/cidr/lang/psi/OCPropertyAttribute.getName:()Ljava/lang/String;
        //   321: aload_0        
        //   322: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.myOldAttr:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   325: invokevirtual   com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.getTokenName:()Ljava/lang/String;
        //   328: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/String;Ljava/lang/String;)Z
        //   331: ifeq            401
        //   334: goto            341
        //   337: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   340: athrow         
        //   341: aload           7
        //   343: ifnonnull       365
        //   346: goto            353
        //   349: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   352: athrow         
        //   353: aload           9
        //   355: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.delete:(Lcom/intellij/psi/PsiElement;)V
        //   358: goto            400
        //   361: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   364: athrow         
        //   365: aload_0        
        //   366: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.myNewValue:Ljava/lang/String;
        //   369: ifnull          390
        //   372: aload           9
        //   374: aload_0        
        //   375: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.myNewValue:Ljava/lang/String;
        //   378: invokeinterface com/jetbrains/cidr/lang/psi/OCPropertyAttribute.setValue:(Ljava/lang/String;)V
        //   383: goto            400
        //   386: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   389: athrow         
        //   390: aload           9
        //   392: aload           7
        //   394: invokeinterface com/jetbrains/cidr/lang/psi/OCPropertyAttribute.replace:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   399: pop            
        //   400: return         
        //   401: aload_0        
        //   402: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.myNewAttr:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   405: ifnull          440
        //   408: aload           9
        //   410: invokeinterface com/jetbrains/cidr/lang/psi/OCPropertyAttribute.getName:()Ljava/lang/String;
        //   415: aload_0        
        //   416: getfield        com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.myNewAttr:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
        //   419: invokevirtual   com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.getTokenName:()Ljava/lang/String;
        //   422: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/String;Ljava/lang/String;)Z
        //   425: ifeq            440
        //   428: goto            435
        //   431: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   434: athrow         
        //   435: return         
        //   436: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   439: athrow         
        //   440: goto            285
        //   443: aload           7
        //   445: ifnull          463
        //   448: aload           5
        //   450: aload           7
        //   452: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.add:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   455: pop            
        //   456: goto            463
        //   459: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCChangePropertyAttributeIntentionAction.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   462: athrow         
        //   463: return         
        //    Exceptions:
        //  throws com.intellij.util.IncorrectOperationException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  44     73     73     77     Lcom/intellij/util/IncorrectOperationException;
        //  104    125    128    132    Lcom/intellij/util/IncorrectOperationException;
        //  109    133    133    137    Lcom/intellij/util/IncorrectOperationException;
        //  191    203    206    210    Lcom/intellij/util/IncorrectOperationException;
        //  219    234    234    238    Lcom/intellij/util/IncorrectOperationException;
        //  307    334    337    341    Lcom/intellij/util/IncorrectOperationException;
        //  314    346    349    353    Lcom/intellij/util/IncorrectOperationException;
        //  341    361    361    365    Lcom/intellij/util/IncorrectOperationException;
        //  365    386    386    390    Lcom/intellij/util/IncorrectOperationException;
        //  401    428    431    435    Lcom/intellij/util/IncorrectOperationException;
        //  408    436    436    440    Lcom/intellij/util/IncorrectOperationException;
        //  443    456    459    463    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0341:
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
    
    private static IncorrectOperationException c(final IncorrectOperationException ex) {
        return ex;
    }
}
