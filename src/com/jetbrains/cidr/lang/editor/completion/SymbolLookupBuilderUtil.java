// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import com.intellij.openapi.editor.Editor;
import com.jetbrains.cidr.lang.psi.OCStructLike;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.symbols.OCTypeParameterSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.psi.OCCppNewExpression;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;
import com.intellij.openapi.editor.Document;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.jetbrains.cidr.lang.refactoring.util.OCBindUtil;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.editor.OCTypedHandlerDelegate;
import com.jetbrains.cidr.lang.psi.OCCppNamespaceAlias;
import com.jetbrains.cidr.lang.psi.OCCppNamespace;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCCppUsingStatement;
import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.psi.PsiDocumentManager;
import com.jetbrains.cidr.lang.quickfixes.OCImportSymbolFix;
import com.intellij.codeInsight.completion.InsertionContext;
import javax.swing.Icon;
import com.intellij.codeInsight.lookup.LookupElement;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCSymbolGroupContext;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.openapi.util.Key;

public class SymbolLookupBuilderUtil
{
    public static final Key<Boolean> DONT_GO_NEXT_TEMPLATE;
    
    public static LookupElementBuilder lookup(final OCSymbol ocSymbol) {
        return lookup(ocSymbol, false, null, null, null, null);
    }
    
    public static LookupElement lookup(@NotNull final OCSymbol ocSymbol, @Nullable final String s, @Nullable final OCObjectType ocObjectType, @Nullable final PsiElement psiElement, final boolean b, @Nullable final OCSymbolGroupContext ocSymbolGroupContext) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil", "lookup"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final LookupElementBuilder lookup = lookup(ocSymbol, false, s, ocObjectType, psiElement, ocSymbolGroupContext);
        OCCompletionPriority high_PRIORITY;
        if (b) {
            high_PRIORITY = OCCompletionPriority.HIGH_PRIORITY;
        }
        else {
            high_PRIORITY = null;
        }
        try {
            if (high_PRIORITY != null) {
                final Object elementWithPriority = OCCompletionPriority.elementWithPriority((LookupElement)lookup, high_PRIORITY);
                return (LookupElement)elementWithPriority;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final Object elementWithPriority = lookup;
        return (LookupElement)elementWithPriority;
    }
    
    public static LookupElementBuilder lookup(@NotNull final OCSymbol ocSymbol, @Nullable final PsiElement psiElement, @Nullable final OCSymbolGroupContext ocSymbolGroupContext) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil", "lookup"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return lookup(ocSymbol.getName(), ocSymbol, false, null, null, psiElement, ocSymbolGroupContext, ocSymbol.getIcon());
    }
    
    public static LookupElementBuilder lookup(@NotNull final OCSymbol ocSymbol, final boolean b, @Nullable final String s, @Nullable final OCObjectType ocObjectType, @Nullable final PsiElement psiElement, @Nullable final OCSymbolGroupContext ocSymbolGroupContext) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil", "lookup"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return lookup(ocSymbol.getName(), ocSymbol, b, s, ocObjectType, psiElement, ocSymbolGroupContext, ocSymbol.getIcon());
    }
    
    public static LookupElementBuilder lookup(@NotNull final String p0, @NotNull final OCSymbol p1, final boolean p2, @Nullable final String p3, @Nullable final OCObjectType p4, @Nullable final PsiElement p5, @Nullable final OCSymbolGroupContext p6, @Nullable final Icon p7) {
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
        //    18: ldc             "name"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "lookup"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "symbol"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "lookup"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_0        
        //    89: bipush          32
        //    91: invokevirtual   java/lang/String.indexOf:(I)I
        //    94: istore          8
        //    96: iload           8
        //    98: iflt            110
        //   101: aload_0        
        //   102: iload           8
        //   104: iconst_1       
        //   105: iadd           
        //   106: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   109: astore_0       
        //   110: aload_1        
        //   111: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isDeprecated:()Z
        //   116: ifne            135
        //   119: aload_1        
        //   120: aload           5
        //   122: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.isSymbolAvailable:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;)Z
        //   125: ifne            143
        //   128: goto            135
        //   131: invokestatic    com/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   134: athrow         
        //   135: iconst_1       
        //   136: goto            144
        //   139: invokestatic    com/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   142: athrow         
        //   143: iconst_0       
        //   144: istore          9
        //   146: aload_1        
        //   147: aload_0        
        //   148: invokestatic    com/intellij/codeInsight/lookup/LookupElementBuilder.create:(Ljava/lang/Object;Ljava/lang/String;)Lcom/intellij/codeInsight/lookup/LookupElementBuilder;
        //   151: aload           7
        //   153: invokevirtual   com/intellij/codeInsight/lookup/LookupElementBuilder.withIcon:(Ljavax/swing/Icon;)Lcom/intellij/codeInsight/lookup/LookupElementBuilder;
        //   156: iload           9
        //   158: invokevirtual   com/intellij/codeInsight/lookup/LookupElementBuilder.withStrikeoutness:(Z)Lcom/intellij/codeInsight/lookup/LookupElementBuilder;
        //   161: astore          10
        //   163: aload           5
        //   165: ifnonnull       176
        //   168: aconst_null    
        //   169: goto            183
        //   172: invokestatic    com/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   175: athrow         
        //   176: aload           5
        //   178: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   183: astore          11
        //   185: aload_1        
        //   186: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   191: astore          12
        //   193: aload_1        
        //   194: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   197: ifeq            553
        //   200: aload_1        
        //   201: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getContainingFile:()Lcom/intellij/openapi/vfs/VirtualFile;
        //   206: astore          13
        //   208: aload           13
        //   210: ifnull          550
        //   213: aload           11
        //   215: instanceof      Lcom/intellij/psi/impl/source/PsiFileImpl;
        //   218: ifeq            249
        //   221: goto            228
        //   224: invokestatic    com/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   227: athrow         
        //   228: aload           11
        //   230: checkcast       Lcom/intellij/psi/impl/source/PsiFileImpl;
        //   233: invokevirtual   com/intellij/psi/impl/source/PsiFileImpl.getContentElementType:()Lcom/intellij/psi/tree/IElementType;
        //   236: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.TYPE_CODE_FRAGMENT:Lcom/jetbrains/cidr/lang/parser/OCFileElementType;
        //   239: if_acmpeq       550
        //   242: goto            249
        //   245: invokestatic    com/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   248: athrow         
        //   249: aload           10
        //   251: aload           13
        //   253: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getName:()Ljava/lang/String;
        //   256: iconst_1       
        //   257: invokevirtual   com/intellij/codeInsight/lookup/LookupElementBuilder.withTypeText:(Ljava/lang/String;Z)Lcom/intellij/codeInsight/lookup/LookupElementBuilder;
        //   260: astore          10
        //   262: aload           5
        //   264: ifnull          281
        //   267: aload           5
        //   269: invokeinterface com/intellij/psi/PsiElement.getContext:()Lcom/intellij/psi/PsiElement;
        //   274: goto            282
        //   277: invokestatic    com/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   280: athrow         
        //   281: aconst_null    
        //   282: astore          14
        //   284: aload           5
        //   286: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   289: ifeq            349
        //   292: aload           5
        //   294: invokeinterface com/intellij/psi/PsiElement.getPrevSibling:()Lcom/intellij/psi/PsiElement;
        //   299: astore          15
        //   301: aload           15
        //   303: instanceof      Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //   306: ifeq            346
        //   309: aload           15
        //   311: invokeinterface com/intellij/psi/PsiElement.getText:()Ljava/lang/String;
        //   316: invokevirtual   java/lang/String.isEmpty:()Z
        //   319: ifeq            346
        //   322: goto            329
        //   325: invokestatic    com/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   328: athrow         
        //   329: aload           10
        //   331: new             Lcom/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil$ClassInsertHandler;
        //   334: dup            
        //   335: aload           6
        //   337: aconst_null    
        //   338: invokespecial   com/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil$ClassInsertHandler.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;Lcom/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil$a;)V
        //   341: invokevirtual   com/intellij/codeInsight/lookup/LookupElementBuilder.withInsertHandler:(Lcom/intellij/codeInsight/completion/InsertHandler;)Lcom/intellij/codeInsight/lookup/LookupElementBuilder;
        //   344: astore          10
        //   346: goto            550
        //   349: aload           14
        //   351: instanceof      Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //   354: ifne            412
        //   357: aload           14
        //   359: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //   362: ifeq            516
        //   365: goto            372
        //   368: invokestatic    com/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   371: athrow         
        //   372: aload           14
        //   374: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   379: instanceof      Lcom/jetbrains/cidr/lang/psi/OCParenthesizedExpression;
        //   382: ifne            412
        //   385: goto            392
        //   388: invokestatic    com/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   391: athrow         
        //   392: aload           14
        //   394: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   399: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpressionStatement;
        //   402: ifeq            516
        //   405: goto            412
        //   408: invokestatic    com/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   411: athrow         
        //   412: aload           14
        //   414: invokeinterface com/intellij/psi/PsiElement.getContext:()Lcom/intellij/psi/PsiElement;
        //   419: astore          15
        //   421: aload           15
        //   423: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //   426: ifne            479
        //   429: aload           15
        //   431: instanceof      Lcom/jetbrains/cidr/lang/psi/OCProtocolExpression;
        //   434: ifne            479
        //   437: goto            444
        //   440: invokestatic    com/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   443: athrow         
        //   444: aload           15
        //   446: instanceof      Lcom/jetbrains/cidr/lang/psi/OCTemplateArgumentList;
        //   449: ifne            479
        //   452: goto            459
        //   455: invokestatic    com/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   458: athrow         
        //   459: aload           10
        //   461: new             Lcom/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil$ClassInsertHandler;
        //   464: dup            
        //   465: aload           6
        //   467: aconst_null    
        //   468: invokespecial   com/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil$ClassInsertHandler.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;Lcom/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil$a;)V
        //   471: invokevirtual   com/intellij/codeInsight/lookup/LookupElementBuilder.withInsertHandler:(Lcom/intellij/codeInsight/completion/InsertHandler;)Lcom/intellij/codeInsight/lookup/LookupElementBuilder;
        //   474: astore          10
        //   476: goto            513
        //   479: aload           10
        //   481: new             Lcom/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil$WithAutoImportFixHandler;
        //   484: dup            
        //   485: aload           6
        //   487: aload           12
        //   489: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.PROTOCOL:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   492: if_acmpeq       503
        //   495: iconst_1       
        //   496: goto            504
        //   499: invokestatic    com/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   502: athrow         
        //   503: iconst_0       
        //   504: aconst_null    
        //   505: invokespecial   com/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil$WithAutoImportFixHandler.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;ZLcom/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil$a;)V
        //   508: invokevirtual   com/intellij/codeInsight/lookup/LookupElementBuilder.withInsertHandler:(Lcom/intellij/codeInsight/completion/InsertHandler;)Lcom/intellij/codeInsight/lookup/LookupElementBuilder;
        //   511: astore          10
        //   513: goto            550
        //   516: aload           10
        //   518: new             Lcom/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil$WithAutoImportFixHandler;
        //   521: dup            
        //   522: aload           6
        //   524: aload           12
        //   526: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.PROTOCOL:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   529: if_acmpeq       540
        //   532: iconst_1       
        //   533: goto            541
        //   536: invokestatic    com/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   539: athrow         
        //   540: iconst_0       
        //   541: aconst_null    
        //   542: invokespecial   com/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil$WithAutoImportFixHandler.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;ZLcom/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil$a;)V
        //   545: invokevirtual   com/intellij/codeInsight/lookup/LookupElementBuilder.withInsertHandler:(Lcom/intellij/codeInsight/completion/InsertHandler;)Lcom/intellij/codeInsight/lookup/LookupElementBuilder;
        //   548: astore          10
        //   550: goto            1369
        //   553: aload           12
        //   555: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isType:()Z
        //   558: ifeq            582
        //   561: aload           10
        //   563: new             Lcom/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil$TypeInsertHandler;
        //   566: dup            
        //   567: aload_1        
        //   568: aload           5
        //   570: aconst_null    
        //   571: invokespecial   com/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil$TypeInsertHandler.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil$a;)V
        //   574: invokevirtual   com/intellij/codeInsight/lookup/LookupElementBuilder.withInsertHandler:(Lcom/intellij/codeInsight/completion/InsertHandler;)Lcom/intellij/codeInsight/lookup/LookupElementBuilder;
        //   577: astore          10
        //   579: goto            1369
        //   582: aload           12
        //   584: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.NAMESPACE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   587: if_acmpeq       605
        //   590: aload           12
        //   592: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.NAMESPACE_ALIAS:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   595: if_acmpne       625
        //   598: goto            605
        //   601: invokestatic    com/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   604: athrow         
        //   605: aload           10
        //   607: new             Lcom/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil$NamespaceInsertHandler;
        //   610: dup            
        //   611: aload           5
        //   613: aconst_null    
        //   614: invokespecial   com/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil$NamespaceInsertHandler.<init>:(Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil$a;)V
        //   617: invokevirtual   com/intellij/codeInsight/lookup/LookupElementBuilder.withInsertHandler:(Lcom/intellij/codeInsight/completion/InsertHandler;)Lcom/intellij/codeInsight/lookup/LookupElementBuilder;
        //   620: astore          10
        //   622: goto            1369
        //   625: aload_1        
        //   626: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol;
        //   629: ifeq            766
        //   632: aload           5
        //   634: iconst_1       
        //   635: anewarray       Ljava/lang/Class;
        //   638: dup            
        //   639: iconst_0       
        //   640: ldc             Lcom/jetbrains/cidr/lang/psi/OCDirective;.class
        //   642: aastore        
        //   643: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   646: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDirective;
        //   649: astore          13
        //   651: aload           13
        //   653: ifnull          680
        //   656: aload           13
        //   658: invokeinterface com/jetbrains/cidr/lang/psi/OCDirective.getHeaderToken:()Lcom/intellij/psi/PsiElement;
        //   663: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
        //   668: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   673: goto            681
        //   676: invokestatic    com/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   679: athrow         
        //   680: aconst_null    
        //   681: astore          14
        //   683: aload           14
        //   685: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IFDEF_DIRECTIVE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   688: if_acmpeq       763
        //   691: aload           14
        //   693: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IFNDEF_DIRECTIVE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   696: if_acmpeq       763
        //   699: goto            706
        //   702: invokestatic    com/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   705: athrow         
        //   706: aload           14
        //   708: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.UNDEF_DIRECTIVE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   711: if_acmpeq       763
        //   714: goto            721
        //   717: invokestatic    com/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   720: athrow         
        //   721: aload_1        
        //   722: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol;
        //   725: astore          15
        //   727: aload           15
        //   729: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol.hasParameterList:()Z
        //   732: ifeq            747
        //   735: aload           10
        //   737: aload           15
        //   739: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol.getParametersSignature:()Ljava/lang/String;
        //   742: invokevirtual   com/intellij/codeInsight/lookup/LookupElementBuilder.withTailText:(Ljava/lang/String;)Lcom/intellij/codeInsight/lookup/LookupElementBuilder;
        //   745: astore          10
        //   747: aload           10
        //   749: new             Lcom/jetbrains/cidr/lang/editor/completion/MacroInsertHandler;
        //   752: dup            
        //   753: aload           15
        //   755: invokespecial   com/jetbrains/cidr/lang/editor/completion/MacroInsertHandler.<init>:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol;)V
        //   758: invokevirtual   com/intellij/codeInsight/lookup/LookupElementBuilder.withInsertHandler:(Lcom/intellij/codeInsight/completion/InsertHandler;)Lcom/intellij/codeInsight/lookup/LookupElementBuilder;
        //   761: astore          10
        //   763: goto            1369
        //   766: aload_1        
        //   767: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;
        //   770: ifne            801
        //   773: aload_1        
        //   774: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   777: ifne            801
        //   780: goto            787
        //   783: invokestatic    com/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   786: athrow         
        //   787: aload_1        
        //   788: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   791: ifeq            1369
        //   794: goto            801
        //   797: invokestatic    com/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   800: athrow         
        //   801: aload_1        
        //   802: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   805: ifeq            1057
        //   808: goto            815
        //   811: invokestatic    com/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   814: athrow         
        //   815: aload_1        
        //   816: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   819: astore          14
        //   821: aload_3        
        //   822: ifnonnull       903
        //   825: aload           5
        //   827: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //   830: ifeq            900
        //   833: goto            840
        //   836: invokestatic    com/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   839: athrow         
        //   840: aload           14
        //   842: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.isStatic:()Z
        //   847: ifeq            894
        //   850: goto            857
        //   853: invokestatic    com/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   856: athrow         
        //   857: new             Ljava/lang/StringBuilder;
        //   860: dup            
        //   861: invokespecial   java/lang/StringBuilder.<init>:()V
        //   864: ldc             "["
        //   866: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   869: aload           14
        //   871: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   876: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   879: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getName:()Ljava/lang/String;
        //   884: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   887: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   890: astore_3       
        //   891: goto            903
        //   894: ldc             "[self"
        //   896: astore_3       
        //   897: goto            903
        //   900: ldc             ""
        //   902: astore_3       
        //   903: new             Ljava/lang/StringBuilder;
        //   906: dup            
        //   907: aload_3        
        //   908: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   911: astore          15
        //   913: aload           15
        //   915: invokevirtual   java/lang/StringBuilder.length:()I
        //   918: ifle            936
        //   921: aload           15
        //   923: bipush          32
        //   925: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   928: pop            
        //   929: goto            936
        //   932: invokestatic    com/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   935: athrow         
        //   936: aload           15
        //   938: aload           14
        //   940: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getName:()Ljava/lang/String;
        //   945: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   948: pop            
        //   949: aload_3        
        //   950: invokevirtual   java/lang/String.isEmpty:()Z
        //   953: ifne            971
        //   956: aload           15
        //   958: bipush          93
        //   960: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   963: pop            
        //   964: goto            971
        //   967: invokestatic    com/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   970: athrow         
        //   971: iload_2        
        //   972: ifeq            1003
        //   975: aload_3        
        //   976: ldc             "["
        //   978: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   981: ifeq            1003
        //   984: goto            991
        //   987: invokestatic    com/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   990: athrow         
        //   991: aload_3        
        //   992: iconst_1       
        //   993: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   996: goto            1004
        //   999: invokestatic    com/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1002: athrow         
        //  1003: aload_3        
        //  1004: astore          16
        //  1006: aload           10
        //  1008: new             Lcom/jetbrains/cidr/lang/editor/completion/MethodInsertHandler;
        //  1011: dup            
        //  1012: aload           14
        //  1014: aload           5
        //  1016: iload_2        
        //  1017: aload           16
        //  1019: invokespecial   com/jetbrains/cidr/lang/editor/completion/MethodInsertHandler.<init>:(Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;Lcom/intellij/psi/PsiElement;ZLjava/lang/String;)V
        //  1022: invokevirtual   com/intellij/codeInsight/lookup/LookupElementBuilder.withInsertHandler:(Lcom/intellij/codeInsight/completion/InsertHandler;)Lcom/intellij/codeInsight/lookup/LookupElementBuilder;
        //  1025: aload           15
        //  1027: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1030: invokevirtual   com/intellij/codeInsight/lookup/LookupElementBuilder.withPresentableText:(Ljava/lang/String;)Lcom/intellij/codeInsight/lookup/LookupElementBuilder;
        //  1033: aload           15
        //  1035: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1038: invokevirtual   com/intellij/codeInsight/lookup/LookupElementBuilder.withLookupString:(Ljava/lang/String;)Lcom/intellij/codeInsight/lookup/LookupElementBuilder;
        //  1041: astore          10
        //  1043: aload           14
        //  1045: aload           4
        //  1047: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getReturnType:(Lcom/jetbrains/cidr/lang/types/OCObjectType;)Lcom/jetbrains/cidr/lang/types/OCType;
        //  1052: astore          13
        //  1054: goto            1323
        //  1057: aload_1        
        //  1058: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //  1061: ifeq            1111
        //  1064: aload_1        
        //  1065: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //  1068: astore          14
        //  1070: aload           14
        //  1072: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //  1075: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.getReturnType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //  1078: astore          13
        //  1080: aload           10
        //  1082: aload           14
        //  1084: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getParametersSignature:()Ljava/lang/String;
        //  1087: invokevirtual   com/intellij/codeInsight/lookup/LookupElementBuilder.withTailText:(Ljava/lang/String;)Lcom/intellij/codeInsight/lookup/LookupElementBuilder;
        //  1090: astore          10
        //  1092: aload           10
        //  1094: aload_1        
        //  1095: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //  1098: aload           5
        //  1100: invokestatic    com/jetbrains/cidr/lang/editor/completion/CallableInsertUtils.createHandler:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;Lcom/intellij/psi/PsiElement;)Lcom/intellij/codeInsight/completion/InsertHandler;
        //  1103: invokevirtual   com/intellij/codeInsight/lookup/LookupElementBuilder.withInsertHandler:(Lcom/intellij/codeInsight/completion/InsertHandler;)Lcom/intellij/codeInsight/lookup/LookupElementBuilder;
        //  1106: astore          10
        //  1108: goto            1323
        //  1111: aload           5
        //  1113: instanceof      Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //  1116: ifeq            1184
        //  1119: aload           12
        //  1121: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.STRUCT_FIELD:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1124: if_acmpeq       1164
        //  1127: goto            1134
        //  1130: invokestatic    com/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1133: athrow         
        //  1134: aload           12
        //  1136: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.ENUM_CONST:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1139: if_acmpeq       1164
        //  1142: goto            1149
        //  1145: invokestatic    com/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1148: athrow         
        //  1149: aload           12
        //  1151: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isFunction:()Z
        //  1154: ifeq            1184
        //  1157: goto            1164
        //  1160: invokestatic    com/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1163: athrow         
        //  1164: aload           10
        //  1166: new             Lcom/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil$StructFieldHandler;
        //  1169: dup            
        //  1170: aload_1        
        //  1171: aload           5
        //  1173: invokespecial   com/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil$StructFieldHandler.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;)V
        //  1176: invokevirtual   com/intellij/codeInsight/lookup/LookupElementBuilder.withInsertHandler:(Lcom/intellij/codeInsight/completion/InsertHandler;)Lcom/intellij/codeInsight/lookup/LookupElementBuilder;
        //  1179: astore          10
        //  1181: goto            1265
        //  1184: aload_1        
        //  1185: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //  1188: ifeq            1216
        //  1191: aload           10
        //  1193: new             Lcom/jetbrains/cidr/lang/editor/completion/PropertyInsertHandler;
        //  1196: dup            
        //  1197: aload           5
        //  1199: aload_1        
        //  1200: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //  1203: aload           6
        //  1205: invokespecial   com/jetbrains/cidr/lang/editor/completion/PropertyInsertHandler.<init>:(Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;)V
        //  1208: invokevirtual   com/intellij/codeInsight/lookup/LookupElementBuilder.withInsertHandler:(Lcom/intellij/codeInsight/completion/InsertHandler;)Lcom/intellij/codeInsight/lookup/LookupElementBuilder;
        //  1211: astore          10
        //  1213: goto            1265
        //  1216: aload           10
        //  1218: new             Lcom/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil$WithAutoImportFixHandler;
        //  1221: dup            
        //  1222: aload           6
        //  1224: iconst_0       
        //  1225: aconst_null    
        //  1226: invokespecial   com/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil$WithAutoImportFixHandler.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;ZLcom/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil$a;)V
        //  1229: invokevirtual   com/intellij/codeInsight/lookup/LookupElementBuilder.withInsertHandler:(Lcom/intellij/codeInsight/completion/InsertHandler;)Lcom/intellij/codeInsight/lookup/LookupElementBuilder;
        //  1232: astore          10
        //  1234: aload_1        
        //  1235: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCThisSelfSuperSymbol;
        //  1238: ifeq            1265
        //  1241: aload_1        
        //  1242: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCThisSelfSuperSymbol;
        //  1245: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCThisSelfSuperSymbol.isKeywordLike:()Z
        //  1248: ifeq            1265
        //  1251: goto            1258
        //  1254: invokestatic    com/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1257: athrow         
        //  1258: aload           10
        //  1260: invokevirtual   com/intellij/codeInsight/lookup/LookupElementBuilder.bold:()Lcom/intellij/codeInsight/lookup/LookupElementBuilder;
        //  1263: astore          10
        //  1265: aload_1        
        //  1266: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //  1271: astore          13
        //  1273: aload           12
        //  1275: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.ENUM_CONST:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1278: if_acmpne       1323
        //  1281: aload_1        
        //  1282: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbolWithParent;
        //  1285: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbolWithParent.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //  1290: astore          14
        //  1292: aload           14
        //  1294: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //  1297: ifeq            1318
        //  1300: aload           14
        //  1302: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //  1305: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.isEnumClass:()Z
        //  1308: ifne            1323
        //  1311: goto            1318
        //  1314: invokestatic    com/jetbrains/cidr/lang/editor/completion/SymbolLookupBuilderUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1317: athrow         
        //  1318: getstatic       com/jetbrains/cidr/lang/types/OCUnknownType.INSTANCE:Lcom/jetbrains/cidr/lang/types/OCUnknownType;
        //  1321: astore          13
        //  1323: aload           13
        //  1325: getstatic       com/jetbrains/cidr/lang/types/OCUnknownType.INSTANCE:Lcom/jetbrains/cidr/lang/types/OCUnknownType;
        //  1328: if_acmpeq       1369
        //  1331: aload           13
        //  1333: new             Lcom/jetbrains/cidr/lang/types/visitors/OCTypeParameterResolveVisitor;
        //  1336: dup            
        //  1337: aload           11
        //  1339: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeParameterResolveVisitor.<init>:(Lcom/intellij/psi/PsiFile;)V
        //  1342: invokevirtual   com/jetbrains/cidr/lang/types/OCType.accept:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeVisitor;)Ljava/lang/Object;
        //  1345: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //  1348: astore          14
        //  1350: aload           14
        //  1352: ifnull          1369
        //  1355: aload           10
        //  1357: aload           14
        //  1359: aload           5
        //  1361: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //  1364: invokevirtual   com/intellij/codeInsight/lookup/LookupElementBuilder.withTypeText:(Ljava/lang/String;)Lcom/intellij/codeInsight/lookup/LookupElementBuilder;
        //  1367: astore          10
        //  1369: aload           10
        //  1371: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  110    128    131    135    Ljava/lang/IllegalArgumentException;
        //  119    139    139    143    Ljava/lang/IllegalArgumentException;
        //  163    172    172    176    Ljava/lang/IllegalArgumentException;
        //  208    221    224    228    Ljava/lang/IllegalArgumentException;
        //  213    242    245    249    Ljava/lang/IllegalArgumentException;
        //  262    277    277    281    Ljava/lang/IllegalArgumentException;
        //  301    322    325    329    Ljava/lang/IllegalArgumentException;
        //  349    365    368    372    Ljava/lang/IllegalArgumentException;
        //  357    385    388    392    Ljava/lang/IllegalArgumentException;
        //  372    405    408    412    Ljava/lang/IllegalArgumentException;
        //  421    437    440    444    Ljava/lang/IllegalArgumentException;
        //  429    452    455    459    Ljava/lang/IllegalArgumentException;
        //  479    499    499    503    Ljava/lang/IllegalArgumentException;
        //  516    536    536    540    Ljava/lang/IllegalArgumentException;
        //  582    598    601    605    Ljava/lang/IllegalArgumentException;
        //  651    676    676    680    Ljava/lang/IllegalArgumentException;
        //  683    699    702    706    Ljava/lang/IllegalArgumentException;
        //  691    714    717    721    Ljava/lang/IllegalArgumentException;
        //  766    780    783    787    Ljava/lang/IllegalArgumentException;
        //  773    794    797    801    Ljava/lang/IllegalArgumentException;
        //  787    808    811    815    Ljava/lang/IllegalArgumentException;
        //  821    833    836    840    Ljava/lang/IllegalArgumentException;
        //  825    850    853    857    Ljava/lang/IllegalArgumentException;
        //  913    929    932    936    Ljava/lang/IllegalArgumentException;
        //  936    964    967    971    Ljava/lang/IllegalArgumentException;
        //  971    984    987    991    Ljava/lang/IllegalArgumentException;
        //  975    999    999    1003   Ljava/lang/IllegalArgumentException;
        //  1111   1127   1130   1134   Ljava/lang/IllegalArgumentException;
        //  1119   1142   1145   1149   Ljava/lang/IllegalArgumentException;
        //  1134   1157   1160   1164   Ljava/lang/IllegalArgumentException;
        //  1234   1251   1254   1258   Ljava/lang/IllegalArgumentException;
        //  1292   1311   1314   1318   Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0372:
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
    
    private static void a(final InsertionContext insertionContext, final OCSymbolGroupContext ocSymbolGroupContext) {
        OCImportSymbolFix.fixAtCaret(insertionContext.getEditor(), insertionContext.getFile(), ocSymbolGroupContext);
        PsiDocumentManager.getInstance(insertionContext.getProject()).doPostponedOperationsAndUnblockDocument(insertionContext.getDocument());
    }
    
    static {
        DONT_GO_NEXT_TEMPLATE = Key.create("TEMPLATE_LOOKUP");
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    private static class NamespaceInsertHandler implements InsertHandler<LookupElement>
    {
        private final PsiElement myContextExpression;
        
        private NamespaceInsertHandler(final PsiElement myContextExpression) {
            this.myContextExpression = myContextExpression;
        }
        
        public void handleInsert(final InsertionContext insertionContext, final LookupElement lookupElement) {
            final OCCppUsingStatement ocCppUsingStatement = (OCCppUsingStatement)PsiTreeUtil.getContextOfType(this.myContextExpression, new Class[] { OCCppUsingStatement.class });
            if (ocCppUsingStatement != null && ocCppUsingStatement.isNamespaceUsing()) {
                return;
            }
            final PsiElement parent = this.myContextExpression.getParent();
            if (parent instanceof OCCppNamespace) {
                return;
            }
            if (parent instanceof OCCppNamespaceAlias) {
                return;
            }
            final char completionChar = insertionContext.getCompletionChar();
            String text;
            int tailOffset;
            for (text = insertionContext.getDocument().getText(), tailOffset = insertionContext.getTailOffset(); tailOffset < text.length() && Character.isSpaceChar(text.charAt(tailOffset)); ++tailOffset) {}
            if (tailOffset >= text.length() || text.charAt(tailOffset) != ':') {
                String s;
                if (completionChar == ':') {
                    s = ":";
                }
                else {
                    s = "::";
                }
                insertionContext.getDocument().insertString(insertionContext.getTailOffset(), (CharSequence)s);
                insertionContext.getEditor().getCaretModel().moveCaretRelatively(s.length(), 0, false, false, true);
                OCTypedHandlerDelegate.overTypeNextColon();
            }
        }
    }
    
    private static class WithAutoImportFixHandler implements InsertHandler<LookupElement>
    {
        private OCSymbolGroupContext mySymbolContext;
        private boolean myInsertSpace;
        
        private WithAutoImportFixHandler(final OCSymbolGroupContext mySymbolContext, final boolean myInsertSpace) {
            this.mySymbolContext = mySymbolContext;
            this.myInsertSpace = myInsertSpace;
        }
        
        public void handleInsert(final InsertionContext insertionContext, final LookupElement lookupElement) {
            a(insertionContext, this.mySymbolContext);
            if (this.myInsertSpace && insertionContext.getCompletionChar() != ' ') {
                final int tailOffset = insertionContext.getTailOffset();
                final CharSequence charsSequence = insertionContext.getDocument().getCharsSequence();
                if (tailOffset < charsSequence.length()) {
                    final char char1 = charsSequence.charAt(tailOffset);
                    if (char1 != ' ' && char1 != '>' && char1 != '<') {
                        insertionContext.getDocument().insertString(tailOffset, (CharSequence)" ");
                    }
                }
                insertionContext.getEditor().getCaretModel().moveCaretRelatively(1, 0, false, false, true);
            }
        }
    }
    
    public static class OCEnumConstInsertHandler implements InsertHandler<LookupElement>
    {
        private final OCDeclaratorSymbol mySymbol;
        private final PsiElement myContextExpression;
        
        public OCEnumConstInsertHandler(final OCDeclaratorSymbol mySymbol, final PsiElement myContextExpression) {
            this.mySymbol = mySymbol;
            this.myContextExpression = myContextExpression;
        }
        
        public void handleInsert(final InsertionContext insertionContext, final LookupElement lookupElement) {
            if (this.myContextExpression instanceof OCReferenceExpression) {
                final OCQualifiedName shortestPossibleName = OCBindUtil.getShortestPossibleName(this.mySymbol.getResolvedQualifiedName(), ((OCReferenceExpression)this.myContextExpression).getReferenceElement(), this.mySymbol);
                if (shortestPossibleName != null) {
                    final Document document = insertionContext.getDocument();
                    document.replaceString(insertionContext.getStartOffset(), insertionContext.getTailOffset(), (CharSequence)shortestPossibleName.getCanonicalName(true));
                    PsiDocumentManager.getInstance(insertionContext.getProject()).commitDocument(document);
                }
            }
            OCImportSymbolFix.fixAtCaret(insertionContext.getEditor(), insertionContext.getFile(), this.mySymbol);
        }
    }
    
    private static class StructFieldHandler implements InsertHandler<LookupElement>
    {
        private final OCSymbol mySymbol;
        private final PsiElement myContextExpression;
        
        public StructFieldHandler(final OCSymbol mySymbol, final PsiElement myContextExpression) {
            this.mySymbol = mySymbol;
            this.myContextExpression = myContextExpression;
        }
        
        public void handleInsert(final InsertionContext insertionContext, final LookupElement lookupElement) {
            FunctionInsertHandler.changeQualifyingTokenIfNeeded(insertionContext, this.myContextExpression, lookupElement);
            OCImportSymbolFix.fixAtCaret(insertionContext.getEditor(), insertionContext.getFile(), this.mySymbol);
        }
    }
    
    private static class ClassInsertHandler implements InsertHandler<LookupElement>
    {
        private OCSymbolGroupContext mySymbolContext;
        
        private ClassInsertHandler(final OCSymbolGroupContext mySymbolContext) {
            this.mySymbolContext = mySymbolContext;
        }
        
        public void handleInsert(final InsertionContext insertionContext, final LookupElement lookupElement) {
            final char completionChar = insertionContext.getCompletionChar();
            if (completionChar == '.' || completionChar == ']') {
                a(insertionContext, this.mySymbolContext);
                return;
            }
            String text;
            int tailOffset;
            for (text = insertionContext.getDocument().getText(), tailOffset = insertionContext.getTailOffset(); tailOffset < text.length() && Character.isSpaceChar(text.charAt(tailOffset)); ++tailOffset) {}
            if (tailOffset < text.length() && (text.charAt(tailOffset) == '*' || text.charAt(tailOffset) == '<')) {
                a(insertionContext, this.mySymbolContext);
                return;
            }
            final StringBuilder sb = new StringBuilder((completionChar == '*') ? "" : "*");
            final CodeStyleSettings settings = CodeStyleSettingsManager.getSettings(insertionContext.getProject());
            if (settings != null) {
                final OCCodeStyleSettings ocCodeStyleSettings = (OCCodeStyleSettings)settings.getCustomSettings((Class)OCCodeStyleSettings.class);
                if (ocCodeStyleSettings != null) {
                    sb.setLength(0);
                    if (ocCodeStyleSettings.SPACE_BEFORE_POINTER_IN_DECLARATION) {
                        sb.append(' ');
                    }
                    if (completionChar != '*') {
                        sb.append('*');
                        if (ocCodeStyleSettings.SPACE_AFTER_POINTER_IN_DECLARATION) {
                            sb.append(' ');
                        }
                        else {
                            OCTypedHandlerDelegate.overTypeNextStar();
                        }
                    }
                }
            }
            if (completionChar == ' ') {
                insertionContext.setAddCompletionChar(false);
            }
            a(insertionContext, this.mySymbolContext);
            insertionContext.getDocument().insertString(insertionContext.getTailOffset(), (CharSequence)sb.toString());
            insertionContext.getEditor().getCaretModel().moveCaretRelatively(sb.length(), 0, false, false, true);
        }
    }
    
    private static class TypeInsertHandler implements InsertHandler<LookupElement>
    {
        private final OCSymbol mySymbol;
        private final PsiElement myContext;
        
        private TypeInsertHandler(final OCSymbol mySymbol, final PsiElement myContext) {
            this.mySymbol = mySymbol;
            this.myContext = myContext;
        }
        
        public void handleInsert(final InsertionContext insertionContext, final LookupElement lookupElement) {
            final Document document = insertionContext.getDocument();
            final Editor editor = insertionContext.getEditor();
            final CharSequence charsSequence = document.getCharsSequence();
            final int tailOffset = insertionContext.getTailOffset();
            final CodeStyleSettings settings = CodeStyleSettingsManager.getSettings(insertionContext.getProject());
            if (!(this.myContext.getParent().getParent() instanceof OCCppNewExpression)) {
                if (settings != null && ((OCCodeStyleSettings)settings.getCustomSettings((Class)OCCodeStyleSettings.class)).SPACE_BEFORE_POINTER_IN_DECLARATION) {
                    boolean b = true;
                    if ((this.mySymbol.getKind() != OCSymbolKind.ENUM && this.mySymbol instanceof OCNamespaceSymbol) || this.mySymbol instanceof OCTypeParameterSymbol) {
                        b = false;
                    }
                    if (this.mySymbol.getKind() == OCSymbolKind.TYPEDEF && this.mySymbol.getType().resolve((PsiFile)this.mySymbol.getContainingOCFile()) instanceof OCStructType) {
                        b = false;
                    }
                    if (this.mySymbol.getKind() == OCSymbolKind.SYMBOL_USING_SYMBOL) {
                        b = false;
                    }
                    if (b && tailOffset < charsSequence.length()) {
                        final char char1 = charsSequence.charAt(tailOffset);
                        if (char1 == ')' || char1 == ']') {
                            return;
                        }
                        if (char1 != ' ' && char1 != '\t' && insertionContext.getCompletionChar() != ' ') {
                            document.insertString(tailOffset, (CharSequence)" ");
                        }
                        editor.getCaretModel().moveToOffset(tailOffset + 1);
                    }
                }
            }
            final PsiElement parent = this.myContext.getParent();
            if (this.mySymbol.getKind().isStructLike() && this.myContext != null && OCCodeInsightUtil.isInPlainOldC(this.myContext) && parent != null && !(parent instanceof OCStructLike)) {
                document.insertString(insertionContext.getStartOffset(), (CharSequence)(this.mySymbol.getKind().getNameLowercase() + " "));
            }
            PsiDocumentManager.getInstance(insertionContext.getProject()).commitDocument(document);
            OCImportSymbolFix.fixAtCaret(insertionContext.getEditor(), insertionContext.getFile(), this.mySymbol);
            PsiDocumentManager.getInstance(insertionContext.getProject()).doPostponedOperationsAndUnblockDocument(insertionContext.getDocument());
        }
    }
}
