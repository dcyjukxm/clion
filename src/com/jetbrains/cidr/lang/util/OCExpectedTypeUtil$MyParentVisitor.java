// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.psi.OCNSDictionaryLiteral;
import com.jetbrains.cidr.lang.types.OCIdType;
import com.jetbrains.cidr.lang.psi.OCNSArrayLiteral;
import com.jetbrains.cidr.lang.psi.OCDesignatedInitializer;
import com.jetbrains.cidr.lang.psi.OCCppNewExpression;
import com.jetbrains.cidr.lang.psi.OCTypeElement;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.psi.OCConstructorFieldInitializer;
import com.intellij.psi.PsiElementVisitor;
import com.jetbrains.cidr.lang.psi.OCCastExpression;
import com.jetbrains.cidr.lang.psi.OCSwitchStatement;
import com.jetbrains.cidr.lang.psi.OCCaseStatement;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.jetbrains.cidr.lang.psi.OCForeachStatement;
import com.jetbrains.cidr.lang.psi.OCDoWhileStatement;
import com.jetbrains.cidr.lang.psi.OCDeclarationOrExpression;
import com.jetbrains.cidr.lang.psi.OCConditionalExpression;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.psi.OCUnaryExpression;
import com.jetbrains.cidr.lang.psi.OCPostfixExpression;
import com.jetbrains.cidr.lang.psi.OCPrefixExpression;
import com.intellij.psi.tree.TokenSet;
import com.jetbrains.cidr.lang.parser.OCPunctuatorElementType;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.intellij.psi.PsiReference;
import com.jetbrains.cidr.lang.types.OCIntType;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.resolve.references.OCOperatorReference;
import com.jetbrains.cidr.lang.psi.OCBinaryExpression;
import com.jetbrains.cidr.lang.psi.OCCompoundInitializer;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.jetbrains.cidr.lang.psi.OCReturnStatement;
import com.jetbrains.cidr.lang.psi.OCAssignmentExpression;
import com.jetbrains.cidr.lang.daemon.OCGetSymbolVisitor;
import com.jetbrains.cidr.lang.types.OCEllipsisType;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCCallExpression;
import com.jetbrains.cidr.lang.psi.OCArgumentList;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.jetbrains.cidr.lang.psi.OCMessageArgument;
import java.util.Iterator;
import java.util.Collection;
import com.jetbrains.cidr.lang.types.OCUnknownType;
import com.jetbrains.cidr.lang.types.OCTypeUtils;
import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import com.jetbrains.cidr.lang.types.OCType;
import java.util.Set;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;

private static class MyParentVisitor extends OCVisitor
{
    private final OCExpression myExpr;
    private boolean myConvertLiteralTypes;
    private OCResolveContext myContext;
    private Set<OCType> myResult;
    private List<OCType> myTypesList;
    
    private MyParentVisitor(final OCExpression myExpr, final boolean myConvertLiteralTypes, @NotNull final OCResolveContext myContext) {
        if (myContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/util/OCExpectedTypeUtil$MyParentVisitor", "<init>"));
        }
        this.myTypesList = new ArrayList<OCType>();
        this.myExpr = myExpr;
        this.myConvertLiteralTypes = myConvertLiteralTypes;
        this.myContext = myContext;
        this.myResult = OCTypeUtils.newTypeSet();
    }
    
    private void b(@NotNull final OCType ocType) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/util/OCExpectedTypeUtil$MyParentVisitor", "addType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0071: {
            try {
                if (ocType == OCUnknownType.INSTANCE) {
                    return;
                }
                final MyParentVisitor myParentVisitor = this;
                final Set<OCType> set = myParentVisitor.myResult;
                final OCType ocType2 = ocType;
                final boolean b = set.add(ocType2);
                if (b) {
                    break Label_0071;
                }
                return;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final MyParentVisitor myParentVisitor = this;
                final Set<OCType> set = myParentVisitor.myResult;
                final OCType ocType2 = ocType;
                final boolean b = set.add(ocType2);
                if (b) {
                    this.myTypesList.add(ocType);
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
    }
    
    private void a(@NotNull final Collection<OCType> collection) {
        try {
            if (collection == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "types", "com/jetbrains/cidr/lang/util/OCExpectedTypeUtil$MyParentVisitor", "addTypes"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final Iterator<OCType> iterator = collection.iterator();
        while (iterator.hasNext()) {
            this.b(iterator.next());
        }
    }
    
    @Override
    public void visitMessageArgument(final OCMessageArgument ocMessageArgument) {
        final OCSendMessageExpression ocSendMessageExpression = (OCSendMessageExpression)ocMessageArgument.getParent();
        final int index = ocSendMessageExpression.getArguments().indexOf(ocMessageArgument);
        for (final OCMethodSymbol ocMethodSymbol : ocSendMessageExpression.getProbableResponders().getAllResponders()) {
            final List<OCMethodSymbol.SelectorPartSymbol> selectors = ocMethodSymbol.getSelectors();
            if (index < selectors.size()) {
                final OCDeclaratorSymbol parameter = selectors.get(index).getParameter();
                try {
                    if (parameter == null) {
                        continue;
                    }
                    this.b(parameter.getType());
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
            }
            else {
                final OCType formatArgumentType = OCFormatSpecifiersUtil.getFormatArgumentType(ocMethodSymbol, index, ocSendMessageExpression.getArgumentExpressions());
                try {
                    if (formatArgumentType == null) {
                        continue;
                    }
                    this.b(formatArgumentType);
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
        }
    }
    
    @Override
    public void visitArgumentList(final OCArgumentList p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokeinterface com/jetbrains/cidr/lang/psi/OCArgumentList.getParent:()Lcom/intellij/psi/PsiElement;
        //     6: astore_2       
        //     7: aload_2        
        //     8: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCallExpression;
        //    11: ifeq            224
        //    14: aload_2        
        //    15: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCallExpression;
        //    18: astore_3       
        //    19: aload_3        
        //    20: invokeinterface com/jetbrains/cidr/lang/psi/OCCallExpression.getFunctionReferenceExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    25: astore          4
        //    27: aload           4
        //    29: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //    32: ifeq            138
        //    35: aload           4
        //    37: checkcast       Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //    40: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceExpression.getReferenceElement:()Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //    45: astore          5
        //    47: aload           5
        //    49: ifnull          135
        //    52: aload           5
        //    54: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceElement.resolveToOverloadsSymbols:()Ljava/util/Collection;
        //    59: astore          6
        //    61: aload           6
        //    63: invokeinterface java/util/Collection.iterator:()Ljava/util/Iterator;
        //    68: astore          7
        //    70: aload           7
        //    72: invokeinterface java/util/Iterator.hasNext:()Z
        //    77: ifeq            135
        //    80: aload           7
        //    82: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    87: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    90: astore          8
        //    92: aload           8
        //    94: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    99: astore          9
        //   101: aload           9
        //   103: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   106: ifeq            132
        //   109: aload_0        
        //   110: aload_3        
        //   111: aload           9
        //   113: checkcast       Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   116: aload_3        
        //   117: invokeinterface com/jetbrains/cidr/lang/psi/OCCallExpression.getArguments:()Ljava/util/List;
        //   122: invokespecial   com/jetbrains/cidr/lang/util/OCExpectedTypeUtil$MyParentVisitor.a:(Lcom/jetbrains/cidr/lang/psi/OCCallExpression;Lcom/jetbrains/cidr/lang/types/OCFunctionType;Ljava/util/List;)V
        //   125: goto            132
        //   128: invokestatic    com/jetbrains/cidr/lang/util/OCExpectedTypeUtil$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   131: athrow         
        //   132: goto            70
        //   135: goto            221
        //   138: aload           4
        //   140: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   145: astore          5
        //   147: aload_3        
        //   148: invokeinterface com/jetbrains/cidr/lang/psi/OCCallExpression.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   153: astore          6
        //   155: aload           5
        //   157: instanceof      Lcom/jetbrains/cidr/lang/types/OCReferenceType;
        //   160: ifeq            172
        //   163: aload           5
        //   165: aload           6
        //   167: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   170: astore          5
        //   172: aload           5
        //   174: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   177: ifeq            190
        //   180: aload           5
        //   182: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   185: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   188: astore          5
        //   190: aload           5
        //   192: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   195: ifeq            221
        //   198: aload_0        
        //   199: aload_3        
        //   200: aload           5
        //   202: checkcast       Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   205: aload_3        
        //   206: invokeinterface com/jetbrains/cidr/lang/psi/OCCallExpression.getArguments:()Ljava/util/List;
        //   211: invokespecial   com/jetbrains/cidr/lang/util/OCExpectedTypeUtil$MyParentVisitor.a:(Lcom/jetbrains/cidr/lang/psi/OCCallExpression;Lcom/jetbrains/cidr/lang/types/OCFunctionType;Ljava/util/List;)V
        //   214: goto            221
        //   217: invokestatic    com/jetbrains/cidr/lang/util/OCExpectedTypeUtil$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   220: athrow         
        //   221: goto            618
        //   224: aconst_null    
        //   225: astore_3       
        //   226: aconst_null    
        //   227: astore          4
        //   229: aload_2        
        //   230: instanceof      Lcom/jetbrains/cidr/lang/psi/OCConstructorFieldInitializer;
        //   233: ifeq            284
        //   236: aload_2        
        //   237: checkcast       Lcom/jetbrains/cidr/lang/psi/OCConstructorFieldInitializer;
        //   240: astore          5
        //   242: aload           5
        //   244: invokeinterface com/jetbrains/cidr/lang/psi/OCConstructorFieldInitializer.getReferenceElement:()Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //   249: astore          6
        //   251: aload           6
        //   253: ifnull          270
        //   256: aload           6
        //   258: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceElement.resolveToSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   263: goto            271
        //   266: invokestatic    com/jetbrains/cidr/lang/util/OCExpectedTypeUtil$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   269: athrow         
        //   270: aconst_null    
        //   271: astore_3       
        //   272: aload           5
        //   274: invokeinterface com/jetbrains/cidr/lang/psi/OCConstructorFieldInitializer.getArguments:()Ljava/util/List;
        //   279: astore          4
        //   281: goto            466
        //   284: aload_2        
        //   285: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   288: ifeq            416
        //   291: aload_2        
        //   292: invokeinterface com/intellij/psi/PsiElement.getReference:()Lcom/intellij/psi/PsiReference;
        //   297: astore          5
        //   299: aload           5
        //   301: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReference;
        //   304: ifeq            324
        //   307: aload           5
        //   309: checkcast       Lcom/jetbrains/cidr/lang/psi/OCReference;
        //   312: invokeinterface com/jetbrains/cidr/lang/psi/OCReference.resolveToSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   317: goto            325
        //   320: invokestatic    com/jetbrains/cidr/lang/util/OCExpectedTypeUtil$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   323: athrow         
        //   324: aconst_null    
        //   325: astore_3       
        //   326: aload_2        
        //   327: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   330: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getArgumentList:()Lcom/jetbrains/cidr/lang/psi/OCArgumentList;
        //   335: astore          6
        //   337: aload           6
        //   339: ifnull          356
        //   342: aload           6
        //   344: invokeinterface com/jetbrains/cidr/lang/psi/OCArgumentList.getArguments:()Ljava/util/List;
        //   349: goto            357
        //   352: invokestatic    com/jetbrains/cidr/lang/util/OCExpectedTypeUtil$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   355: athrow         
        //   356: aconst_null    
        //   357: astore          4
        //   359: aload_3        
        //   360: ifnonnull       413
        //   363: aload           4
        //   365: ifnull          413
        //   368: goto            375
        //   371: invokestatic    com/jetbrains/cidr/lang/util/OCExpectedTypeUtil$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   374: athrow         
        //   375: aload           4
        //   377: invokeinterface java/util/List.size:()I
        //   382: iconst_1       
        //   383: if_icmpne       413
        //   386: goto            393
        //   389: invokestatic    com/jetbrains/cidr/lang/util/OCExpectedTypeUtil$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   392: athrow         
        //   393: aload_0        
        //   394: aload_2        
        //   395: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   398: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   403: invokespecial   com/jetbrains/cidr/lang/util/OCExpectedTypeUtil$MyParentVisitor.b:(Lcom/jetbrains/cidr/lang/types/OCType;)V
        //   406: goto            413
        //   409: invokestatic    com/jetbrains/cidr/lang/util/OCExpectedTypeUtil$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   412: athrow         
        //   413: goto            466
        //   416: aload_2        
        //   417: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCppNewExpression;
        //   420: ifeq            466
        //   423: aload_2        
        //   424: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCppNewExpression;
        //   427: invokeinterface com/jetbrains/cidr/lang/psi/OCCppNewExpression.getReferenceElement:()Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //   432: astore          5
        //   434: aload           5
        //   436: ifnull          453
        //   439: aload           5
        //   441: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceElement.resolveToSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   446: goto            454
        //   449: invokestatic    com/jetbrains/cidr/lang/util/OCExpectedTypeUtil$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   452: athrow         
        //   453: aconst_null    
        //   454: astore_3       
        //   455: aload_2        
        //   456: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCppNewExpression;
        //   459: invokeinterface com/jetbrains/cidr/lang/psi/OCCppNewExpression.getArguments:()Ljava/util/List;
        //   464: astore          4
        //   466: aload_3        
        //   467: ifnull          523
        //   470: aload_0        
        //   471: getfield        com/jetbrains/cidr/lang/util/OCExpectedTypeUtil$MyParentVisitor.myExpr:Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   474: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCompoundInitializer;
        //   477: ifeq            523
        //   480: goto            487
        //   483: invokestatic    com/jetbrains/cidr/lang/util/OCExpectedTypeUtil$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   486: athrow         
        //   487: aload_3        
        //   488: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   493: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isConstructorOrDestructor:()Z
        //   496: ifeq            523
        //   499: goto            506
        //   502: invokestatic    com/jetbrains/cidr/lang/util/OCExpectedTypeUtil$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   505: athrow         
        //   506: aload_0        
        //   507: aload_3        
        //   508: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getEffectiveResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   513: invokespecial   com/jetbrains/cidr/lang/util/OCExpectedTypeUtil$MyParentVisitor.b:(Lcom/jetbrains/cidr/lang/types/OCType;)V
        //   516: goto            523
        //   519: invokestatic    com/jetbrains/cidr/lang/util/OCExpectedTypeUtil$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   522: athrow         
        //   523: aload_3        
        //   524: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   527: ifeq            565
        //   530: aload           4
        //   532: ifnull          565
        //   535: goto            542
        //   538: invokestatic    com/jetbrains/cidr/lang/util/OCExpectedTypeUtil$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   541: athrow         
        //   542: aload_0        
        //   543: aconst_null    
        //   544: aload_3        
        //   545: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   550: checkcast       Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   553: aload           4
        //   555: invokespecial   com/jetbrains/cidr/lang/util/OCExpectedTypeUtil$MyParentVisitor.a:(Lcom/jetbrains/cidr/lang/psi/OCCallExpression;Lcom/jetbrains/cidr/lang/types/OCFunctionType;Ljava/util/List;)V
        //   558: goto            618
        //   561: invokestatic    com/jetbrains/cidr/lang/util/OCExpectedTypeUtil$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   564: athrow         
        //   565: aload_3        
        //   566: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   569: ifeq            589
        //   572: aload_0        
        //   573: aload_3        
        //   574: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   579: invokespecial   com/jetbrains/cidr/lang/util/OCExpectedTypeUtil$MyParentVisitor.b:(Lcom/jetbrains/cidr/lang/types/OCType;)V
        //   582: goto            618
        //   585: invokestatic    com/jetbrains/cidr/lang/util/OCExpectedTypeUtil$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   588: athrow         
        //   589: aload_3        
        //   590: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   593: ifeq            618
        //   596: aload_0        
        //   597: new             Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   600: dup            
        //   601: aload_3        
        //   602: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   605: invokespecial   com/jetbrains/cidr/lang/types/OCStructType.<init>:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;)V
        //   608: invokespecial   com/jetbrains/cidr/lang/util/OCExpectedTypeUtil$MyParentVisitor.b:(Lcom/jetbrains/cidr/lang/types/OCType;)V
        //   611: goto            618
        //   614: invokestatic    com/jetbrains/cidr/lang/util/OCExpectedTypeUtil$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   617: athrow         
        //   618: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  101    125    128    132    Ljava/lang/IllegalArgumentException;
        //  190    214    217    221    Ljava/lang/IllegalArgumentException;
        //  251    266    266    270    Ljava/lang/IllegalArgumentException;
        //  299    320    320    324    Ljava/lang/IllegalArgumentException;
        //  337    352    352    356    Ljava/lang/IllegalArgumentException;
        //  359    368    371    375    Ljava/lang/IllegalArgumentException;
        //  363    386    389    393    Ljava/lang/IllegalArgumentException;
        //  375    406    409    413    Ljava/lang/IllegalArgumentException;
        //  434    449    449    453    Ljava/lang/IllegalArgumentException;
        //  466    480    483    487    Ljava/lang/IllegalArgumentException;
        //  470    499    502    506    Ljava/lang/IllegalArgumentException;
        //  487    516    519    523    Ljava/lang/IllegalArgumentException;
        //  523    535    538    542    Ljava/lang/IllegalArgumentException;
        //  530    561    561    565    Ljava/lang/IllegalArgumentException;
        //  565    585    585    589    Ljava/lang/IllegalArgumentException;
        //  589    611    614    618    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0375:
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
    
    private void a(@Nullable final OCCallExpression ocCallExpression, final OCFunctionType ocFunctionType, final List<OCExpression> list) {
        int n = 0;
        for (final OCExpression ocExpression : list) {
            try {
                if (ocExpression == this.myExpr) {
                    break;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            ++n;
        }
        final List<OCType> parameterTypes = ocFunctionType.getParameterTypes();
        Label_0095: {
            try {
                if (n >= parameterTypes.size()) {
                    break Label_0095;
                }
                final List<OCType> list2 = parameterTypes;
                final int n2 = n;
                final OCType ocType = list2.get(n2);
                final boolean b = ocType instanceof OCEllipsisType;
                if (!b) {
                    break Label_0095;
                }
                break Label_0095;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final List<OCType> list2 = parameterTypes;
                final int n2 = n;
                final OCType ocType = list2.get(n2);
                final boolean b = ocType instanceof OCEllipsisType;
                if (!b) {
                    this.b(parameterTypes.get(n));
                    return;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        if (ocCallExpression != null) {
            final OCSymbol symbol = OCGetSymbolVisitor.getSymbol(ocCallExpression.getFunctionReferenceExpression(), this.myContext);
            OCType formatArgumentType = null;
            Label_0158: {
                try {
                    if (symbol != null) {
                        formatArgumentType = OCFormatSpecifiersUtil.getFormatArgumentType(symbol, n, list);
                        break Label_0158;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
                formatArgumentType = null;
            }
            final OCType ocType2 = formatArgumentType;
            try {
                if (ocType2 != null) {
                    this.b(ocType2);
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
    }
    
    @Override
    public void visitAssignmentExpression(final OCAssignmentExpression ocAssignmentExpression) {
        final OCExpression sourceExpression = ocAssignmentExpression.getSourceExpression();
        final OCExpression receiverExpression = ocAssignmentExpression.getReceiverExpression();
        try {
            if (this.myExpr == sourceExpression) {
                this.b(OCExpectedTypeUtil.getExpressionType(receiverExpression, this.myConvertLiteralTypes));
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0060: {
            try {
                if (this.myExpr != receiverExpression) {
                    return;
                }
                final OCExpression ocExpression = sourceExpression;
                if (ocExpression != null) {
                    break Label_0060;
                }
                return;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final OCExpression ocExpression = sourceExpression;
                if (ocExpression != null) {
                    this.b(OCExpectedTypeUtil.getExpressionType(sourceExpression, this.myConvertLiteralTypes));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
    }
    
    @Override
    public void visitReturnStatement(final OCReturnStatement ocReturnStatement) {
        final OCCallable ocCallable = (OCCallable)PsiTreeUtil.getContextOfType((PsiElement)ocReturnStatement, new Class[] { OCCallable.class });
        try {
            if (ocCallable != null) {
                this.b(ocCallable.getReturnType());
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
    }
    
    @Override
    public void visitDeclarator(final OCDeclarator ocDeclarator) {
        Label_0030: {
            try {
                if (this.myExpr instanceof OCCompoundInitializer) {
                    break Label_0030;
                }
                final MyParentVisitor myParentVisitor = this;
                final OCExpression ocExpression = myParentVisitor.myExpr;
                final OCDeclarator ocDeclarator2 = ocDeclarator;
                final OCExpression ocExpression2 = ocDeclarator2.getInitializer();
                if (ocExpression == ocExpression2) {
                    break Label_0030;
                }
                return;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final MyParentVisitor myParentVisitor = this;
                final OCExpression ocExpression = myParentVisitor.myExpr;
                final OCDeclarator ocDeclarator2 = ocDeclarator;
                final OCExpression ocExpression2 = ocDeclarator2.getInitializer();
                if (ocExpression == ocExpression2) {
                    this.b(ocDeclarator.getType());
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
    }
    
    @Override
    public void visitBinaryExpression(final OCBinaryExpression ocBinaryExpression) {
        final PsiReference reference = ocBinaryExpression.getReference();
        if (reference instanceof OCOperatorReference) {
            final List<OCSymbol> resolveToSymbols = ((OCOperatorReference)reference).resolveToSymbols(false, this.myContext);
            int n = 0;
            Label_0049: {
                try {
                    if (this.myExpr == ocBinaryExpression.getLeft()) {
                        n = 0;
                        break Label_0049;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                n = 1;
            }
            final int n2 = n;
            for (final OCSymbol ocSymbol : resolveToSymbols) {
                final OCType type = ocSymbol.getType();
                if (type instanceof OCFunctionType) {
                    final List<OCType> parameterTypes = ((OCFunctionType)type).getParameterTypes();
                    try {
                        if (parameterTypes.size() > 1) {
                            this.b(parameterTypes.get(n2).resolve((PsiFile)ocSymbol.getContainingOCFile()).getGuessedType());
                            continue;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    Label_0178: {
                        try {
                            if (parameterTypes.isEmpty()) {
                                continue;
                            }
                            final int n3 = n2;
                            final boolean b = true;
                            if (n3 == (b ? 1 : 0)) {
                                break Label_0178;
                            }
                            continue;
                        }
                        catch (IllegalArgumentException ex3) {
                            throw a(ex3);
                        }
                        try {
                            final int n3 = n2;
                            final boolean b = true;
                            if (n3 != (b ? 1 : 0)) {
                                continue;
                            }
                            this.b(parameterTypes.get(0).resolve((PsiFile)ocSymbol.getContainingOCFile()).getGuessedType());
                        }
                        catch (IllegalArgumentException ex4) {
                            throw a(ex4);
                        }
                    }
                }
            }
        }
        final OCElementType operationSign = ocBinaryExpression.getOperationSign();
        Label_0244: {
            try {
                if (operationSign == OCTokenTypes.OR) {
                    break Label_0244;
                }
                final OCPunctuatorElementType ocPunctuatorElementType = (OCPunctuatorElementType)operationSign;
                final OCPunctuatorElementType ocPunctuatorElementType2 = OCTokenTypes.AND;
                if (ocPunctuatorElementType == ocPunctuatorElementType2) {
                    break Label_0244;
                }
                break Label_0244;
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
            try {
                final OCPunctuatorElementType ocPunctuatorElementType = (OCPunctuatorElementType)operationSign;
                final OCPunctuatorElementType ocPunctuatorElementType2 = OCTokenTypes.AND;
                if (ocPunctuatorElementType == ocPunctuatorElementType2) {
                    this.a(OCExpectedTypeUtil.getExpectedTypes(ocBinaryExpression, this.myConvertLiteralTypes, this.myContext));
                    return;
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
        }
        final OCExpression left = ocBinaryExpression.getLeft();
        Label_0365: {
            if (this.myExpr == left) {
                final OCExpression right = ocBinaryExpression.getRight();
                try {
                    if (right != null) {
                        this.b(OCExpectedTypeUtil.getExpressionType(right, this.myConvertLiteralTypes));
                    }
                }
                catch (IllegalArgumentException ex7) {
                    throw a(ex7);
                }
            }
            else {
                Label_0345: {
                    try {
                        if (this.myExpr != ocBinaryExpression.getRight()) {
                            break Label_0365;
                        }
                        final OCExpression ocExpression = left;
                        if (ocExpression != null) {
                            break Label_0345;
                        }
                        break Label_0365;
                    }
                    catch (IllegalArgumentException ex8) {
                        throw a(ex8);
                    }
                    try {
                        final OCExpression ocExpression = left;
                        if (ocExpression != null) {
                            this.b(OCExpectedTypeUtil.getExpressionType(left, this.myConvertLiteralTypes));
                        }
                    }
                    catch (IllegalArgumentException ex9) {
                        throw a(ex9);
                    }
                }
            }
            try {
                if (this.myResult.isEmpty()) {
                    this.a(OCExpectedTypeUtil.getExpectedTypes(ocBinaryExpression, this.myConvertLiteralTypes, this.myContext));
                }
            }
            catch (IllegalArgumentException ex10) {
                throw a(ex10);
            }
        }
        Label_0429: {
            try {
                if (!this.myResult.isEmpty()) {
                    return;
                }
                final TokenSet set = OCTokenTypes.ARITHMETIC_OPERATIONS;
                final OCPunctuatorElementType ocPunctuatorElementType3 = (OCPunctuatorElementType)operationSign;
                final boolean b2 = set.contains((IElementType)ocPunctuatorElementType3);
                if (b2) {
                    break Label_0429;
                }
                return;
            }
            catch (IllegalArgumentException ex11) {
                throw a(ex11);
            }
            try {
                final TokenSet set = OCTokenTypes.ARITHMETIC_OPERATIONS;
                final OCPunctuatorElementType ocPunctuatorElementType3 = (OCPunctuatorElementType)operationSign;
                final boolean b2 = set.contains((IElementType)ocPunctuatorElementType3);
                if (b2) {
                    this.b(OCIntType.INT);
                }
            }
            catch (IllegalArgumentException ex12) {
                throw a(ex12);
            }
        }
    }
    
    @Override
    public void visitPrefixExpression(final OCPrefixExpression ocPrefixExpression) {
        final OCElementType operationSign = ocPrefixExpression.getOperationSign();
        Label_0052: {
            try {
                this.a(OCExpectedTypeUtil.getExpectedTypes(ocPrefixExpression, this.myConvertLiteralTypes, this.myContext));
                if (!this.myResult.isEmpty()) {
                    return;
                }
                final TokenSet set = OCTokenTypes.ARITHMETIC_OPERATIONS;
                final OCElementType ocElementType = operationSign;
                final boolean b = set.contains((IElementType)ocElementType);
                if (b) {
                    break Label_0052;
                }
                return;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final TokenSet set = OCTokenTypes.ARITHMETIC_OPERATIONS;
                final OCElementType ocElementType = operationSign;
                final boolean b = set.contains((IElementType)ocElementType);
                if (b) {
                    this.b(OCIntType.INT);
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
    }
    
    @Override
    public void visitPostfixExpression(final OCPostfixExpression ocPostfixExpression) {
        final OCElementType operationSign = ocPostfixExpression.getOperationSign();
        Label_0052: {
            try {
                this.a(OCExpectedTypeUtil.getExpectedTypes(ocPostfixExpression, this.myConvertLiteralTypes, this.myContext));
                if (!this.myResult.isEmpty()) {
                    return;
                }
                final TokenSet set = OCTokenTypes.ARITHMETIC_OPERATIONS;
                final OCElementType ocElementType = operationSign;
                final boolean b = set.contains((IElementType)ocElementType);
                if (b) {
                    break Label_0052;
                }
                return;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final TokenSet set = OCTokenTypes.ARITHMETIC_OPERATIONS;
                final OCElementType ocElementType = operationSign;
                final boolean b = set.contains((IElementType)ocElementType);
                if (b) {
                    this.b(OCIntType.INT);
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
    }
    
    @Override
    public void visitUnaryExpression(final OCUnaryExpression ocUnaryExpression) {
        final PsiReference reference = ocUnaryExpression.getReference();
        if (reference instanceof OCOperatorReference) {
            for (final OCSymbol ocSymbol : ((OCOperatorReference)reference).resolveToSymbols(false)) {
                final OCType type = ocSymbol.getType();
                if (type instanceof OCFunctionType) {
                    final List<OCType> parameterTypes = ((OCFunctionType)type).getParameterTypes();
                    try {
                        if (parameterTypes.size() <= 0) {
                            continue;
                        }
                        this.b(parameterTypes.get(0).resolve((PsiFile)ocSymbol.getContainingOCFile()).getGuessedType());
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                }
            }
        }
        try {
            if (ocUnaryExpression.getOperationSign() == OCTokenTypes.EXCL) {
                this.b(OCIntType.getAppropriateBool(ocUnaryExpression));
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (ocUnaryExpression.isGetAddress()) {
                this.a(ContainerUtil.mapNotNull((Collection)OCExpectedTypeUtil.getExpectedTypes(ocUnaryExpression, this.myConvertLiteralTypes, this.myContext), ocType -> {
                    try {
                        if (ocType instanceof OCPointerType) {
                            return ((OCPointerType)ocType).getRefType();
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    return null;
                }));
                return;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (ocUnaryExpression.getOperationSign() == OCTokenTypes.MUL) {
                this.a(ContainerUtil.mapNotNull((Collection)OCExpectedTypeUtil.getExpectedTypes(ocUnaryExpression, this.myConvertLiteralTypes, this.myContext), ocType -> {
                    try {
                        if (!ocType.isUnknown()) {
                            return OCPointerType.to(ocType);
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    return null;
                }));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
    }
    
    @Override
    public void visitConditionalExpression(final OCConditionalExpression ocConditionalExpression) {
        Label_0119: {
            try {
                if (this.myExpr == ocConditionalExpression.getCondition()) {
                    this.b(OCIntType.getAppropriateBool(ocConditionalExpression));
                    break Label_0119;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            final OCExpression negativeExpression = ocConditionalExpression.getNegativeExpression();
            final OCExpression positiveExpression = ocConditionalExpression.getPositiveExpression(true);
            Label_0100: {
                Label_0081: {
                    Label_0062: {
                        try {
                            if (this.myExpr != negativeExpression) {
                                break Label_0081;
                            }
                            final OCExpression ocExpression = positiveExpression;
                            if (ocExpression != null) {
                                break Label_0062;
                            }
                            break Label_0081;
                        }
                        catch (IllegalArgumentException ex2) {
                            throw a(ex2);
                        }
                        try {
                            final OCExpression ocExpression = positiveExpression;
                            if (ocExpression != null) {
                                this.b(OCExpectedTypeUtil.getExpressionType(positiveExpression, this.myConvertLiteralTypes));
                                break Label_0119;
                            }
                        }
                        catch (IllegalArgumentException ex3) {
                            throw a(ex3);
                        }
                    }
                    try {
                        if (this.myExpr != positiveExpression) {
                            break Label_0119;
                        }
                        final OCExpression ocExpression2 = negativeExpression;
                        if (ocExpression2 != null) {
                            break Label_0100;
                        }
                        break Label_0119;
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                }
                try {
                    final OCExpression ocExpression2 = negativeExpression;
                    if (ocExpression2 != null) {
                        this.b(OCExpectedTypeUtil.getExpressionType(negativeExpression, this.myConvertLiteralTypes));
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
            }
            try {
                if (this.myResult.isEmpty()) {
                    this.b(OCExpectedTypeUtil.getExpectedType(ocConditionalExpression, this.myConvertLiteralTypes));
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
        }
    }
    
    @Override
    public void visitDeclarationOrExpression(final OCDeclarationOrExpression p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/util/OCExpectedTypeUtil$MyParentVisitor.myExpr:Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //     4: aload_1        
        //     5: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarationOrExpression.getExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    10: if_acmpne       70
        //    13: aload_1        
        //    14: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarationOrExpression.getParent:()Lcom/intellij/psi/PsiElement;
        //    19: astore_2       
        //    20: aload_2        
        //    21: instanceof      Lcom/jetbrains/cidr/lang/psi/OCIfStatement;
        //    24: ifne            55
        //    27: aload_2        
        //    28: instanceof      Lcom/jetbrains/cidr/lang/psi/OCForStatement;
        //    31: ifne            55
        //    34: goto            41
        //    37: invokestatic    com/jetbrains/cidr/lang/util/OCExpectedTypeUtil$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    40: athrow         
        //    41: aload_2        
        //    42: instanceof      Lcom/jetbrains/cidr/lang/psi/OCWhileStatement;
        //    45: ifeq            70
        //    48: goto            55
        //    51: invokestatic    com/jetbrains/cidr/lang/util/OCExpectedTypeUtil$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    54: athrow         
        //    55: aload_0        
        //    56: aload_1        
        //    57: invokestatic    com/jetbrains/cidr/lang/types/OCIntType.getAppropriateBool:(Lcom/jetbrains/cidr/lang/psi/OCElement;)Lcom/jetbrains/cidr/lang/types/OCIntType;
        //    60: invokespecial   com/jetbrains/cidr/lang/util/OCExpectedTypeUtil$MyParentVisitor.b:(Lcom/jetbrains/cidr/lang/types/OCType;)V
        //    63: goto            70
        //    66: invokestatic    com/jetbrains/cidr/lang/util/OCExpectedTypeUtil$MyParentVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    69: athrow         
        //    70: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  20     34     37     41     Ljava/lang/IllegalArgumentException;
        //  27     48     51     55     Ljava/lang/IllegalArgumentException;
        //  41     63     66     70     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0041:
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
    public void visitDoWhileStatement(final OCDoWhileStatement ocDoWhileStatement) {
        try {
            if (this.myExpr == ocDoWhileStatement.getCondition()) {
                this.b(OCIntType.getAppropriateBool(ocDoWhileStatement));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
    }
    
    @Override
    public void visitForeachStatement(final OCForeachStatement ocForeachStatement) {
        Label_0029: {
            try {
                if (this.myExpr != ocForeachStatement.getCollectionExpression()) {
                    return;
                }
                final OCForeachStatement ocForeachStatement2 = ocForeachStatement;
                final boolean b = ocForeachStatement2.isCpp11Foreach();
                if (!b) {
                    break Label_0029;
                }
                return;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCForeachStatement ocForeachStatement2 = ocForeachStatement;
                final boolean b = ocForeachStatement2.isCpp11Foreach();
                if (!b) {
                    this.b(OCReferenceType.resolvedFromText("id", "NSFastEnumeration", (PsiFile)ocForeachStatement.getContainingOCFile()));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
    }
    
    @Override
    public void visitCaseStatement(final OCCaseStatement ocCaseStatement) {
        if (this.myExpr == ocCaseStatement.getExpression()) {
            final OCSwitchStatement ocSwitchStatement = (OCSwitchStatement)PsiTreeUtil.getParentOfType((PsiElement)ocCaseStatement, (Class)OCSwitchStatement.class);
            OCDeclarationOrExpression expression = null;
            Label_0041: {
                try {
                    if (ocSwitchStatement != null) {
                        expression = ocSwitchStatement.getExpression();
                        break Label_0041;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                expression = null;
            }
            final OCDeclarationOrExpression ocDeclarationOrExpression = expression;
            try {
                if (ocDeclarationOrExpression != null) {
                    this.b(ocDeclarationOrExpression.getType());
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
    }
    
    @Override
    public void visitCastExpression(final OCCastExpression ocCastExpression) {
        try {
            if (this.myExpr instanceof OCCompoundInitializer) {
                this.b(ocCastExpression.getCastType());
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ocCastExpression.getParent().accept((PsiElementVisitor)this);
    }
    
    @Override
    public void visitConstructorFieldInitializer(final OCConstructorFieldInitializer ocConstructorFieldInitializer) {
        if (this.myExpr instanceof OCCompoundInitializer) {
            final OCReferenceElement referenceElement = ocConstructorFieldInitializer.getReferenceElement();
            OCSymbol resolveToSymbol = null;
            Label_0035: {
                try {
                    if (referenceElement != null) {
                        resolveToSymbol = referenceElement.resolveToSymbol();
                        break Label_0035;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                resolveToSymbol = null;
            }
            final OCSymbol ocSymbol = resolveToSymbol;
            try {
                if (ocSymbol != null) {
                    this.b(ocSymbol.getEffectiveResolvedType());
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
    }
    
    @Override
    public void visitTypeElement(final OCTypeElement ocTypeElement) {
        try {
            if (!(this.myExpr instanceof OCCompoundInitializer) || !(ocTypeElement.getParent() instanceof OCCppNewExpression)) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.b(((OCCppNewExpression)ocTypeElement.getParent()).getConstructingType().resolve(this.myExpr.getContainingFile()));
    }
    
    @Override
    public void visitDesignatedInitializer(final OCDesignatedInitializer ocDesignatedInitializer) {
        if (this.myExpr instanceof OCCompoundInitializer) {
            final OCSymbol resolveToSymbol = ocDesignatedInitializer.getDesignation().resolveToSymbol();
            try {
                if (resolveToSymbol instanceof OCDeclaratorSymbol) {
                    this.b(resolveToSymbol.getType());
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
    }
    
    @Override
    public void visitCompoundInitializer(final OCCompoundInitializer ocCompoundInitializer) {
        if (this.myExpr instanceof OCCompoundInitializer) {
            final OCType inferChildType = ocCompoundInitializer.inferChildType(this.myExpr);
            try {
                if (inferChildType != null) {
                    this.b(inferChildType);
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
    }
    
    @Override
    public void visitArrayLiteral(final OCNSArrayLiteral ocnsArrayLiteral) {
        this.b(OCIdType.pointerToID(ocnsArrayLiteral.getProject()));
    }
    
    @Override
    public void visitDictionaryLiteral(final OCNSDictionaryLiteral ocnsDictionaryLiteral) {
        this.b(OCIdType.pointerToID(ocnsDictionaryLiteral.getProject()));
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
