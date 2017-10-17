// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import java.util.Iterator;
import com.jetbrains.cidr.lang.inspections.OCNotReleasedIvarInspection;
import com.jetbrains.cidr.lang.psi.OCStatement;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import java.util.Collections;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.intellij.psi.PsiElement;
import com.intellij.psi.SmartPsiElementPointer;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;

public class OCReleaseVariablesIntentionAction extends OCPsiElementQuickFix
{
    private OCSymbolKind mySymbolKind;
    private List<? extends OCSymbol> mySymbols;
    private List<String> myIvarNames;
    private TextRange myLocalScope;
    private SmartPsiElementPointer<PsiElement> myElement;
    
    private OCReleaseVariablesIntentionAction(final OCSymbol ocSymbol) {
        super((ocSymbol != null) ? ocSymbol.locateDefinition() : null);
    }
    
    public OCReleaseVariablesIntentionAction(final List<? extends OCSymbol> mySymbols) {
        this((mySymbols.get(0) instanceof OCMemberSymbol) ? ((OCSymbolWithParent<T, OCClassSymbol>)mySymbols.get(0)).getParent().getImplementation() : ((OCSymbol)mySymbols.get(0)));
        this.mySymbolKind = ((OCSymbol)mySymbols.get(0)).getKind();
        this.mySymbols = mySymbols;
        this.myLocalScope = ((OCSymbol)mySymbols.get(0)).getScope();
    }
    
    public OCReleaseVariablesIntentionAction(final OCSymbolKind mySymbolKind, final OCClassSymbol ocClassSymbol, final List<OCPropertySymbol> mySymbols, final List<String> myIvarNames) {
        this(ocClassSymbol.getImplementation());
        this.mySymbolKind = mySymbolKind;
        this.mySymbols = mySymbols;
        this.myIvarNames = myIvarNames;
    }
    
    public OCReleaseVariablesIntentionAction(final OCSymbol ocSymbol, final PsiElement psiElement) {
        this(Collections.singletonList(ocSymbol));
        this.myElement = OCElementUtil.createPsiElementPointer(psiElement);
    }
    
    @Override
    protected String getTextInternal() {
        final StringBuilder sb = new StringBuilder("Release ");
        Label_0070: {
            try {
                if (this.mySymbols.size() == 1) {
                    sb.append("'").append(((OCSymbol)this.mySymbols.get(0)).getName()).append("'");
                    break Label_0070;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            sb.append("symbols");
            try {
                if (this.mySymbolKind == OCSymbolKind.INSTANCE_VARIABLE) {
                    sb.append(" in 'dealloc'");
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        return sb.toString();
    }
    
    @NotNull
    public String getFamilyName() {
        String s;
        try {
            s = "Release variables";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCReleaseVariablesIntentionAction", "getFamilyName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    @Override
    public boolean isAvailable() {
        Label_0024: {
            try {
                if (this.mySymbolKind == OCSymbolKind.INSTANCE_VARIABLE) {
                    break Label_0024;
                }
                final OCReleaseVariablesIntentionAction ocReleaseVariablesIntentionAction = this;
                final TextRange textRange = ocReleaseVariablesIntentionAction.myLocalScope;
                if (textRange != null) {
                    break Label_0024;
                }
                return false;
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            try {
                final OCReleaseVariablesIntentionAction ocReleaseVariablesIntentionAction = this;
                final TextRange textRange = ocReleaseVariablesIntentionAction.myLocalScope;
                if (textRange != null) {
                    return true;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    @Override
    protected void invoke(final PsiFile p0, @NotNull final PsiElement p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "parent"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/quickfixes/OCReleaseVariablesIntentionAction"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "invoke"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCReleaseVariablesIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    43: athrow         
        //    44: new             Ljava/lang/StringBuilder;
        //    47: dup            
        //    48: invokespecial   java/lang/StringBuilder.<init>:()V
        //    51: astore_3       
        //    52: aload_1        
        //    53: invokeinterface com/intellij/psi/PsiFile.getProject:()Lcom/intellij/openapi/project/Project;
        //    58: invokestatic    com/intellij/psi/codeStyle/CodeStyleSettingsManager.getSettings:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/codeStyle/CodeStyleSettings;
        //    61: ldc             Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;.class
        //    63: invokevirtual   com/intellij/psi/codeStyle/CodeStyleSettings.getCustomSettings:(Ljava/lang/Class;)Lcom/intellij/psi/codeStyle/CustomCodeStyleSettings;
        //    66: checkcast       Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //    69: astore          4
        //    71: aload           4
        //    73: ifnull          88
        //    76: aload           4
        //    78: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.RELEASE_STYLE:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings$ReleaseStyle;
        //    81: goto            91
        //    84: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCReleaseVariablesIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    87: athrow         
        //    88: getstatic       com/jetbrains/cidr/lang/settings/OCCodeStyleSettings$ReleaseStyle.IVAR:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings$ReleaseStyle;
        //    91: astore          5
        //    93: iconst_0       
        //    94: istore          6
        //    96: iload           6
        //    98: aload_0        
        //    99: getfield        com/jetbrains/cidr/lang/quickfixes/OCReleaseVariablesIntentionAction.mySymbols:Ljava/util/List;
        //   102: invokeinterface java/util/List.size:()I
        //   107: if_icmpge       426
        //   110: aload_0        
        //   111: getfield        com/jetbrains/cidr/lang/quickfixes/OCReleaseVariablesIntentionAction.mySymbols:Ljava/util/List;
        //   114: iload           6
        //   116: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   121: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   124: astore          7
        //   126: aload_3        
        //   127: invokevirtual   java/lang/StringBuilder.length:()I
        //   130: ifle            147
        //   133: aload_3        
        //   134: ldc             ";\n"
        //   136: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   139: pop            
        //   140: goto            147
        //   143: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCReleaseVariablesIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   146: athrow         
        //   147: aload           7
        //   149: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //   154: astore          8
        //   156: aconst_null    
        //   157: astore          9
        //   159: aload           7
        //   161: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   164: ifeq            193
        //   167: aload           7
        //   169: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   172: astore          9
        //   174: aload_0        
        //   175: getfield        com/jetbrains/cidr/lang/quickfixes/OCReleaseVariablesIntentionAction.myIvarNames:Ljava/util/List;
        //   178: iload           6
        //   180: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   185: checkcast       Ljava/lang/String;
        //   188: astore          8
        //   190: goto            213
        //   193: aload           7
        //   195: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //   198: ifeq            213
        //   201: aload           7
        //   203: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //   206: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.getAssociatedProperty:()Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   211: astore          9
        //   213: aload           9
        //   215: ifnull          244
        //   218: aload           9
        //   220: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.isReadonly:()Z
        //   225: ifeq            244
        //   228: goto            235
        //   231: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCReleaseVariablesIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   234: athrow         
        //   235: aload           9
        //   237: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.getAssociatedPropertyInPrivateCategory:()Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   242: astore          9
        //   244: aload           5
        //   246: astore          10
        //   248: aload           10
        //   250: getstatic       com/jetbrains/cidr/lang/settings/OCCodeStyleSettings$ReleaseStyle.PROPERTY:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings$ReleaseStyle;
        //   253: if_acmpne       307
        //   256: aload           9
        //   258: ifnull          302
        //   261: goto            268
        //   264: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCReleaseVariablesIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   267: athrow         
        //   268: aload           9
        //   270: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.isRetained:()Z
        //   275: ifeq            302
        //   278: goto            285
        //   281: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCReleaseVariablesIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   284: athrow         
        //   285: aload           9
        //   287: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.isReadonly:()Z
        //   292: ifeq            307
        //   295: goto            302
        //   298: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCReleaseVariablesIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   301: athrow         
        //   302: getstatic       com/jetbrains/cidr/lang/settings/OCCodeStyleSettings$ReleaseStyle.IVAR:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings$ReleaseStyle;
        //   305: astore          10
        //   307: getstatic       com/jetbrains/cidr/lang/quickfixes/OCReleaseVariablesIntentionAction$1.$SwitchMap$com$jetbrains$cidr$lang$settings$OCCodeStyleSettings$ReleaseStyle:[I
        //   310: aload           10
        //   312: invokevirtual   com/jetbrains/cidr/lang/settings/OCCodeStyleSettings$ReleaseStyle.ordinal:()I
        //   315: iaload         
        //   316: tableswitch {
        //                2: 344
        //                3: 368
        //                4: 398
        //          default: 420
        //        }
        //   344: aload_3        
        //   345: ldc             "["
        //   347: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   350: aload           8
        //   352: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   355: ldc             " release]"
        //   357: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   360: pop            
        //   361: goto            420
        //   364: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCReleaseVariablesIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   367: athrow         
        //   368: aload_3        
        //   369: ldc             "["
        //   371: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   374: aload           8
        //   376: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   379: ldc             " release], "
        //   381: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   384: aload           8
        //   386: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   389: ldc             " = nil"
        //   391: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   394: pop            
        //   395: goto            420
        //   398: aload_3        
        //   399: ldc             "self."
        //   401: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   404: aload           9
        //   406: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.getName:()Ljava/lang/String;
        //   411: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   414: ldc             " = nil"
        //   416: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   419: pop            
        //   420: iinc            6, 1
        //   423: goto            96
        //   426: aload_0        
        //   427: getfield        com/jetbrains/cidr/lang/quickfixes/OCReleaseVariablesIntentionAction.mySymbolKind:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   430: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.INSTANCE_VARIABLE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   433: if_acmpne       585
        //   436: aload_2        
        //   437: instanceof      Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
        //   440: ifne            455
        //   443: goto            450
        //   446: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCReleaseVariablesIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   449: athrow         
        //   450: return         
        //   451: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCReleaseVariablesIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   454: athrow         
        //   455: aload_0        
        //   456: aload_2        
        //   457: checkcast       Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
        //   460: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCReleaseVariablesIntentionAction.a:(Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;)Lcom/intellij/psi/PsiElement;
        //   463: astore          8
        //   465: aload           8
        //   467: ifnull          511
        //   470: aload           8
        //   472: invokeinterface com/intellij/psi/PsiElement.getTextOffset:()I
        //   477: istore          6
        //   479: new             Ljava/lang/StringBuilder;
        //   482: dup            
        //   483: invokespecial   java/lang/StringBuilder.<init>:()V
        //   486: aload_3        
        //   487: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   490: ldc             ";\n"
        //   492: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   495: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   498: astore          7
        //   500: aload           8
        //   502: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   507: astore_1       
        //   508: goto            553
        //   511: getstatic       com/jetbrains/cidr/lang/util/OCDeclarationKind.DeallocMethod:Lcom/jetbrains/cidr/lang/util/OCDeclarationKind;
        //   514: aload_2        
        //   515: invokevirtual   com/jetbrains/cidr/lang/util/OCDeclarationKind.getChildrenEndOffset:(Lcom/intellij/psi/PsiElement;)I
        //   518: istore          6
        //   520: new             Ljava/lang/StringBuilder;
        //   523: dup            
        //   524: invokespecial   java/lang/StringBuilder.<init>:()V
        //   527: ldc             "-(void) dealloc { "
        //   529: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   532: aload_3        
        //   533: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   536: ldc             "; [super dealloc]; }"
        //   538: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   541: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   544: astore          7
        //   546: aload_2        
        //   547: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   552: astore_1       
        //   553: iload           6
        //   555: iflt            582
        //   558: aload_1        
        //   559: invokeinterface com/intellij/psi/PsiFile.getProject:()Lcom/intellij/openapi/project/Project;
        //   564: aload_1        
        //   565: iload           6
        //   567: iconst_0       
        //   568: aload           7
        //   570: iconst_0       
        //   571: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.changeText:(Lcom/intellij/openapi/project/Project;Lcom/intellij/psi/PsiFile;IILjava/lang/String;Z)Z
        //   574: pop            
        //   575: goto            582
        //   578: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCReleaseVariablesIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   581: athrow         
        //   582: goto            1015
        //   585: aload_0        
        //   586: getfield        com/jetbrains/cidr/lang/quickfixes/OCReleaseVariablesIntentionAction.myElement:Lcom/intellij/psi/SmartPsiElementPointer;
        //   589: ifnull          1015
        //   592: aload_1        
        //   593: aload_0        
        //   594: getfield        com/jetbrains/cidr/lang/quickfixes/OCReleaseVariablesIntentionAction.myLocalScope:Lcom/intellij/openapi/util/TextRange;
        //   597: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   600: invokeinterface com/intellij/psi/PsiFile.findElementAt:(I)Lcom/intellij/psi/PsiElement;
        //   605: astore          6
        //   607: aload           6
        //   609: ifnull          632
        //   612: aload           6
        //   614: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   619: instanceof      Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //   622: ifne            637
        //   625: goto            632
        //   628: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCReleaseVariablesIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   631: athrow         
        //   632: return         
        //   633: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCReleaseVariablesIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   636: athrow         
        //   637: new             Ljava/lang/StringBuilder;
        //   640: dup            
        //   641: invokespecial   java/lang/StringBuilder.<init>:()V
        //   644: aload_3        
        //   645: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   648: ldc             ";"
        //   650: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   653: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   656: aload           6
        //   658: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.statementFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   661: astore          7
        //   663: aload           6
        //   665: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   670: checkcast       Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //   673: astore          8
        //   675: aload           8
        //   677: invokeinterface com/jetbrains/cidr/lang/psi/OCBlockStatement.getStatements:()Ljava/util/List;
        //   682: astore          9
        //   684: aload           8
        //   686: ldc             Lcom/jetbrains/cidr/lang/psi/OCCallable;.class
        //   688: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   691: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   694: astore          10
        //   696: aload           9
        //   698: ifnull          1007
        //   701: aload           10
        //   703: ifnull          1007
        //   706: goto            713
        //   709: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCReleaseVariablesIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   712: athrow         
        //   713: new             Lcom/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer;
        //   716: dup            
        //   717: aload           10
        //   719: aconst_null    
        //   720: invokespecial   com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.<init>:(Lcom/intellij/psi/PsiElement;Lcom/intellij/openapi/util/TextRange;)V
        //   723: astore          11
        //   725: aload           11
        //   727: invokevirtual   com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.buildControlFlowGraph:()V
        //   730: aload           11
        //   732: invokevirtual   com/jetbrains/cidr/lang/dfa/OCDataFlowAnalyzer.getGraph:()Lcom/jetbrains/cidr/lang/dfa/OCControlFlowGraph;
        //   735: aload_0        
        //   736: getfield        com/jetbrains/cidr/lang/quickfixes/OCReleaseVariablesIntentionAction.mySymbols:Ljava/util/List;
        //   739: iconst_0       
        //   740: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   745: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   748: invokevirtual   com/jetbrains/cidr/lang/dfa/OCControlFlowGraph.getInstructions:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Lcom/intellij/util/containers/MultiMap;
        //   751: astore          12
        //   753: aload           12
        //   755: ifnull          920
        //   758: aload           12
        //   760: getstatic       com/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind.KILL:Lcom/jetbrains/cidr/lang/dfa/OCInstruction$InstructionKind;
        //   763: invokevirtual   com/intellij/util/containers/MultiMap.get:(Ljava/lang/Object;)Ljava/util/Collection;
        //   766: astore          13
        //   768: aload           9
        //   770: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   775: astore          14
        //   777: aload           14
        //   779: invokeinterface java/util/Iterator.hasNext:()Z
        //   784: ifeq            920
        //   787: aload           14
        //   789: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   794: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   797: astore          15
        //   799: aload           15
        //   801: invokeinterface com/jetbrains/cidr/lang/psi/OCStatement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   806: astore          16
        //   808: aload_0        
        //   809: getfield        com/jetbrains/cidr/lang/quickfixes/OCReleaseVariablesIntentionAction.myElement:Lcom/intellij/psi/SmartPsiElementPointer;
        //   812: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getPsiElementByPointer:(Lcom/intellij/psi/SmartPsiElementPointer;)Lcom/intellij/psi/PsiElement;
        //   815: ifnull          917
        //   818: aload           16
        //   820: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   823: aload_0        
        //   824: getfield        com/jetbrains/cidr/lang/quickfixes/OCReleaseVariablesIntentionAction.myElement:Lcom/intellij/psi/SmartPsiElementPointer;
        //   827: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getPsiElementByPointer:(Lcom/intellij/psi/SmartPsiElementPointer;)Lcom/intellij/psi/PsiElement;
        //   830: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   835: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   838: if_icmple       917
        //   841: goto            848
        //   844: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCReleaseVariablesIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   847: athrow         
        //   848: aload           13
        //   850: invokeinterface java/util/Collection.iterator:()Ljava/util/Iterator;
        //   855: astore          17
        //   857: aload           17
        //   859: invokeinterface java/util/Iterator.hasNext:()Z
        //   864: ifeq            917
        //   867: aload           17
        //   869: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   874: checkcast       Lcom/jetbrains/cidr/lang/dfa/OCInstruction;
        //   877: astore          18
        //   879: aload           16
        //   881: aload           18
        //   883: invokevirtual   com/jetbrains/cidr/lang/dfa/OCInstruction.getLValue:()Lcom/intellij/psi/PsiElement;
        //   886: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   891: invokevirtual   com/intellij/openapi/util/TextRange.contains:(Lcom/intellij/openapi/util/TextRange;)Z
        //   894: ifeq            914
        //   897: aload           8
        //   899: aload           7
        //   901: aload           15
        //   903: invokeinterface com/jetbrains/cidr/lang/psi/OCBlockStatement.addBefore:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   908: pop            
        //   909: return         
        //   910: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCReleaseVariablesIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   913: athrow         
        //   914: goto            857
        //   917: goto            777
        //   920: aload           9
        //   922: aload           9
        //   924: invokeinterface java/util/List.size:()I
        //   929: iconst_1       
        //   930: isub           
        //   931: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   936: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   939: astore          13
        //   941: aload           13
        //   943: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReturnStatement;
        //   946: ifne            979
        //   949: aload           13
        //   951: instanceof      Lcom/jetbrains/cidr/lang/psi/OCContinueStatement;
        //   954: ifne            979
        //   957: goto            964
        //   960: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCReleaseVariablesIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   963: athrow         
        //   964: aload           13
        //   966: instanceof      Lcom/jetbrains/cidr/lang/psi/OCBreakStatement;
        //   969: ifeq            996
        //   972: goto            979
        //   975: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCReleaseVariablesIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   978: athrow         
        //   979: aload           8
        //   981: aload           7
        //   983: aload           13
        //   985: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.addBefore:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   988: pop            
        //   989: goto            1004
        //   992: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCReleaseVariablesIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   995: athrow         
        //   996: aload           8
        //   998: aload           7
        //  1000: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.add:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //  1003: pop            
        //  1004: goto            1015
        //  1007: aload           8
        //  1009: aload           7
        //  1011: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.add:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //  1014: pop            
        //  1015: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      40     40     44     Ljava/lang/IllegalStateException;
        //  71     84     84     88     Ljava/lang/IllegalStateException;
        //  126    140    143    147    Ljava/lang/IllegalStateException;
        //  213    228    231    235    Ljava/lang/IllegalStateException;
        //  248    261    264    268    Ljava/lang/IllegalStateException;
        //  256    278    281    285    Ljava/lang/IllegalStateException;
        //  268    295    298    302    Ljava/lang/IllegalStateException;
        //  307    364    364    368    Ljava/lang/IllegalStateException;
        //  426    443    446    450    Ljava/lang/IllegalStateException;
        //  436    451    451    455    Ljava/lang/IllegalStateException;
        //  553    575    578    582    Ljava/lang/IllegalStateException;
        //  607    625    628    632    Ljava/lang/IllegalStateException;
        //  612    633    633    637    Ljava/lang/IllegalStateException;
        //  696    706    709    713    Ljava/lang/IllegalStateException;
        //  808    841    844    848    Ljava/lang/IllegalStateException;
        //  879    910    910    914    Ljava/lang/IllegalStateException;
        //  941    957    960    964    Ljava/lang/IllegalStateException;
        //  949    972    975    979    Ljava/lang/IllegalStateException;
        //  964    992    992    996    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0268:
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
    private PsiElement a(final OCClassDeclaration<OCClassSymbol> ocClassDeclaration) {
        for (final OCMethod ocMethod : ocClassDeclaration.getMethods()) {
            final OCBlockStatement body = ocMethod.getBody();
            if (ocMethod.getSelector().equals("dealloc")) {
                try {
                    if (body == null) {
                        continue;
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                final int size = body.getStatements().size();
                OCSendMessageExpression callToSuper = null;
                Label_0114: {
                    try {
                        if (size > 0) {
                            callToSuper = OCNotReleasedIvarInspection.getCallToSuper(body.getStatements().get(size - 1));
                            break Label_0114;
                        }
                    }
                    catch (IllegalStateException ex2) {
                        throw a(ex2);
                    }
                    callToSuper = null;
                }
                final OCSendMessageExpression ocSendMessageExpression = callToSuper;
                try {
                    if (ocSendMessageExpression != null) {
                        final Object closingBrace = ocSendMessageExpression;
                        return (PsiElement)closingBrace;
                    }
                }
                catch (IllegalStateException ex3) {
                    throw a(ex3);
                }
                final Object closingBrace = body.getClosingBrace();
                return (PsiElement)closingBrace;
            }
        }
        return null;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
