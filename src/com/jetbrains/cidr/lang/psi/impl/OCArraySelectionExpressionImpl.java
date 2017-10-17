// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.intellij.psi.PsiReference;
import com.intellij.codeInsight.highlighting.ReadWriteAccessDetector;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.jetbrains.cidr.lang.search.usages.OCReadWriteAccessDetector;
import com.jetbrains.cidr.lang.types.OCUnknownType;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.types.OCIdType;
import com.jetbrains.cidr.lang.types.OCMagicType;
import com.jetbrains.cidr.lang.types.OCCppReferenceType;
import java.util.Iterator;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.jetbrains.cidr.lang.parser.OCPunctuatorElementType;
import com.intellij.psi.tree.TokenSet;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import java.util.Collections;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import java.util.ArrayList;
import com.intellij.openapi.util.TextRange;
import java.util.List;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.intellij.psi.MultiRangeReference;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCArraySelectionExpression;
import com.jetbrains.cidr.lang.resolve.references.OCOperatorReference;

public class OCArraySelectionExpressionImpl extends OCExpressionWithReferenceBase<OCOperatorReference> implements OCArraySelectionExpression
{
    public OCArraySelectionExpressionImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCArraySelectionExpressionImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Nullable
    @Override
    protected OCOperatorReference createReference() {
        final OCExpression arrayExpression = this.getArrayExpression();
        final OCExpression indexExpression = this.getIndexExpression();
        try {
            if (indexExpression != null) {
                class 1OCSubscriptOperatorReference extends OCOperatorReference implements MultiRangeReference
                {
                    public 1OCSubscriptOperatorReference(final OCExpression... array) {
                        super(ocElement, "[]", OperatorPlacement.POSTFIX, null, array);
                    }
                    
                    @NotNull
                    public List<TextRange> getRanges() {
                        final ArrayList<TextRange> list = new ArrayList<TextRange>();
                        final PsiElement access$000 = OCArraySelectionExpressionImpl.this.findChildByType(OCTokenTypes.LBRACKET);
                        try {
                            if (access$000 != null) {
                                list.add(OCElementUtil.getRangeInParent(access$000));
                            }
                        }
                        catch (IllegalStateException ex) {
                            throw a(ex);
                        }
                        final PsiElement access$2 = OCArraySelectionExpressionImpl.this.findChildByType(OCTokenTypes.RBRACKET);
                        try {
                            if (access$2 != null) {
                                list.add(OCElementUtil.getRangeInParent(access$2));
                            }
                        }
                        catch (IllegalStateException ex2) {
                            throw a(ex2);
                        }
                        ArrayList<TextRange> list2;
                        try {
                            list2 = list;
                            if (list2 == null) {
                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCArraySelectionExpressionImpl$1OCSubscriptOperatorReference", "getRanges"));
                            }
                        }
                        catch (IllegalStateException ex3) {
                            throw a(ex3);
                        }
                        return list2;
                    }
                    
                    @NotNull
                    @Override
                    public List<OCSymbol> resolveToSymbols(@NotNull final OCResolveContext ocResolveContext) {
                        try {
                            if (ocResolveContext == null) {
                                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCArraySelectionExpressionImpl$1OCSubscriptOperatorReference", "resolveToSymbols"));
                            }
                        }
                        catch (IllegalStateException ex) {
                            throw a(ex);
                        }
                        final List<OCSymbol> resolveToSubscript = this.resolveToSubscript(ocResolveContext);
                        List<OCSymbol> resolveToSymbols = null;
                        Label_0101: {
                            List<OCSymbol> list = null;
                            Label_0066: {
                                try {
                                    if (resolveToSubscript == null) {
                                        break Label_0101;
                                    }
                                    list = resolveToSubscript;
                                    if (list == null) {
                                        break Label_0066;
                                    }
                                    return list;
                                }
                                catch (IllegalStateException ex2) {
                                    throw a(ex2);
                                }
                                try {
                                    list = resolveToSubscript;
                                    if (list == null) {
                                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCArraySelectionExpressionImpl$1OCSubscriptOperatorReference", "resolveToSymbols"));
                                    }
                                }
                                catch (IllegalStateException ex3) {
                                    throw a(ex3);
                                }
                            }
                            return list;
                            try {
                                resolveToSymbols = super.resolveToSymbols(ocResolveContext);
                                if (resolveToSymbols == null) {
                                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCArraySelectionExpressionImpl$1OCSubscriptOperatorReference", "resolveToSymbols"));
                                }
                            }
                            catch (IllegalStateException ex4) {
                                throw a(ex4);
                            }
                        }
                        return resolveToSymbols;
                    }
                    
                    @NotNull
                    @Override
                    public List<OCSymbol> resolveToSymbols() {
                        final List<OCSymbol> resolveToSubscript = this.resolveToSubscript(new OCResolveContext((PsiElement)OCArraySelectionExpressionImpl.this));
                        List<OCSymbol> resolveToSymbols = null;
                        Label_0067: {
                            List<OCSymbol> list = null;
                            Label_0032: {
                                try {
                                    if (resolveToSubscript == null) {
                                        break Label_0067;
                                    }
                                    list = resolveToSubscript;
                                    if (list == null) {
                                        break Label_0032;
                                    }
                                    return list;
                                }
                                catch (IllegalStateException ex) {
                                    throw a(ex);
                                }
                                try {
                                    list = resolveToSubscript;
                                    if (list == null) {
                                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCArraySelectionExpressionImpl$1OCSubscriptOperatorReference", "resolveToSymbols"));
                                    }
                                }
                                catch (IllegalStateException ex2) {
                                    throw a(ex2);
                                }
                            }
                            return list;
                            try {
                                resolveToSymbols = super.resolveToSymbols();
                                if (resolveToSymbols == null) {
                                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCArraySelectionExpressionImpl$1OCSubscriptOperatorReference", "resolveToSymbols"));
                                }
                            }
                            catch (IllegalStateException ex3) {
                                throw a(ex3);
                            }
                        }
                        return resolveToSymbols;
                    }
                    
                    @Nullable
                    protected List<OCSymbol> resolveToSubscript(@NotNull final OCResolveContext ocResolveContext) {
                        try {
                            if (ocResolveContext == null) {
                                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCArraySelectionExpressionImpl$1OCSubscriptOperatorReference", "resolveToSubscript"));
                            }
                        }
                        catch (IllegalStateException ex) {
                            throw a(ex);
                        }
                        if (OCCompilerFeatures.supportsSubscripting((PsiFile)OCArraySelectionExpressionImpl.this.getContainingOCFile())) {
                            final OCMethodSymbol arraySubscriptMethod = OCArraySelectionExpressionImpl.this.getArraySubscriptMethod(ocResolveContext);
                            try {
                                if (arraySubscriptMethod != null) {
                                    return (List<OCSymbol>)Collections.singletonList(arraySubscriptMethod);
                                }
                            }
                            catch (IllegalStateException ex2) {
                                throw a(ex2);
                            }
                        }
                        return null;
                    }
                    
                    private static IllegalStateException a(final IllegalStateException ex) {
                        return ex;
                    }
                }
                return new 1OCSubscriptOperatorReference(this, new OCExpression[] { arrayExpression, indexExpression });
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return null;
    }
    
    @NotNull
    @Override
    public OCExpression getArrayExpression() {
        for (ASTNode astNode = this.getNode().getFirstChildNode(); astNode != null; astNode = astNode.getTreeNext()) {
            final IElementType elementType = astNode.getElementType();
            try {
                if (elementType == OCTokenTypes.LBRACKET) {
                    break;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            OCExpression ocExpression = null;
            Label_0065: {
                try {
                    if (!OCElementTypes.EXPRESSIONS.contains(elementType)) {
                        continue;
                    }
                    final ASTNode astNode2 = astNode;
                    final PsiElement psiElement = astNode2.getPsi();
                    ocExpression = (OCExpression)psiElement;
                    if (ocExpression == null) {
                        break Label_0065;
                    }
                    return ocExpression;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final ASTNode astNode2 = astNode;
                    final PsiElement psiElement = astNode2.getPsi();
                    ocExpression = (OCExpression)psiElement;
                    if (ocExpression == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCArraySelectionExpressionImpl", "getArrayExpression"));
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            return ocExpression;
        }
        try {
            assert false;
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        OCExpression ocExpression2;
        try {
            ocExpression2 = null;
            if (ocExpression2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCArraySelectionExpressionImpl", "getArrayExpression"));
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        return ocExpression2;
    }
    
    @Override
    public OCExpression getIndexExpression() {
        boolean b = false;
        for (ASTNode astNode = this.getNode().getFirstChildNode(); astNode != null; astNode = astNode.getTreeNext()) {
            final IElementType elementType = astNode.getElementType();
            if (elementType == OCTokenTypes.LBRACKET) {
                b = true;
            }
            else {
                Label_0056: {
                    try {
                        if (!b) {
                            continue;
                        }
                        final TokenSet set = OCElementTypes.EXPRESSIONS;
                        final OCPunctuatorElementType ocPunctuatorElementType = (OCPunctuatorElementType)elementType;
                        final boolean b2 = set.contains((IElementType)ocPunctuatorElementType);
                        if (b2) {
                            break Label_0056;
                        }
                        continue;
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        final TokenSet set = OCElementTypes.EXPRESSIONS;
                        final OCPunctuatorElementType ocPunctuatorElementType = (OCPunctuatorElementType)elementType;
                        final boolean b2 = set.contains((IElementType)ocPunctuatorElementType);
                        if (b2) {
                            return (OCExpression)astNode.getPsi();
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
            }
        }
        return null;
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCArraySelectionExpressionImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ocVisitor.visitArraySelectionExpression(this);
    }
    
    @NotNull
    @Override
    public OCType getType(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCArraySelectionExpressionImpl", "getType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCOperatorReference ocOperatorReference = this.getReference();
        if (ocOperatorReference != null) {
            for (final OCSymbol ocSymbol : ocOperatorReference.resolveToSymbols(ocResolveContext)) {
                Label_0148: {
                    OCType ocType = null;
                    Label_0113: {
                        try {
                            if (!(ocSymbol instanceof OCFunctionSymbol)) {
                                break Label_0148;
                            }
                            final OCSymbol ocSymbol2 = ocSymbol;
                            ocType = ocSymbol2.getEffectiveType();
                            if (ocType == null) {
                                break Label_0113;
                            }
                            return ocType;
                        }
                        catch (IllegalArgumentException ex2) {
                            throw a(ex2);
                        }
                        try {
                            final OCSymbol ocSymbol2 = ocSymbol;
                            ocType = ocSymbol2.getEffectiveType();
                            if (ocType == null) {
                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCArraySelectionExpressionImpl", "getType"));
                            }
                        }
                        catch (IllegalArgumentException ex3) {
                            throw a(ex3);
                        }
                    }
                    return ocType;
                }
                if (ocSymbol instanceof OCMethodSymbol) {
                    final OCMethodSymbol ocMethodSymbol = (OCMethodSymbol)ocSymbol;
                    final List<OCMethodSymbol.SelectorPartSymbol> selectors = ocMethodSymbol.getSelectors();
                    OCType ocType2 = null;
                    Label_0216: {
                        try {
                            if (selectors.size() == 1) {
                                final OCType ocType3;
                                ocType2 = (ocType3 = ocMethodSymbol.getReturnType());
                                break Label_0216;
                            }
                        }
                        catch (IllegalArgumentException ex4) {
                            throw a(ex4);
                        }
                        OCType ocType3;
                        ocType2 = (ocType3 = selectors.get(0).getParameter().getType());
                        try {
                            if (ocType3 == null) {
                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCArraySelectionExpressionImpl", "getType"));
                            }
                        }
                        catch (IllegalArgumentException ex5) {
                            throw a(ex5);
                        }
                    }
                    return ocType2;
                }
            }
        }
        OCType arrayIndexExprType;
        try {
            arrayIndexExprType = getArrayIndexExprType(this.getArrayExpression().getResolvedType(ocResolveContext), ocResolveContext);
            if (arrayIndexExprType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCArraySelectionExpressionImpl", "getType"));
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        return arrayIndexExprType;
    }
    
    public static OCType getArrayIndexExprType(@NotNull OCType refType, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (refType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "arrayType", "com/jetbrains/cidr/lang/psi/impl/OCArraySelectionExpressionImpl", "getArrayIndexExprType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCArraySelectionExpressionImpl", "getArrayIndexExprType"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        if (refType instanceof OCCppReferenceType) {
            refType = ((OCCppReferenceType)refType).getRefType();
        }
        try {
            if (refType instanceof OCMagicType) {
                return ((OCMagicType)refType).getRefType();
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        Label_0158: {
            Label_0146: {
                try {
                    if (!refType.isPointerToObject()) {
                        break Label_0158;
                    }
                    final OCResolveContext ocResolveContext2 = ocResolveContext;
                    final PsiFile psiFile = ocResolveContext2.getFile();
                    final boolean b = OCCompilerFeatures.supportsSubscripting(psiFile);
                    if (b) {
                        break Label_0146;
                    }
                    break Label_0158;
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
                try {
                    final OCResolveContext ocResolveContext2 = ocResolveContext;
                    final PsiFile psiFile = ocResolveContext2.getFile();
                    final boolean b = OCCompilerFeatures.supportsSubscripting(psiFile);
                    if (b) {
                        return OCIdType.pointerToID(ocResolveContext.getProject());
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
            }
            try {
                if (refType instanceof OCPointerType) {
                    return ((OCPointerType)refType).getRefType();
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
        }
        return OCUnknownType.INSTANCE;
    }
    
    @Override
    protected List<OCExpression> getDependentExpressions() {
        return Collections.singletonList(this.getArrayExpression());
    }
    
    @Nullable
    @Override
    public OCMethodSymbol getArraySubscriptMethod(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCArraySelectionExpressionImpl", "getArraySubscriptMethod"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCExpression arrayExpression = this.getArrayExpression();
        final OCExpression indexExpression = this.getIndexExpression();
        try {
            if (indexExpression == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final PsiFile containingFile = this.getContainingFile();
        final OCType resolvedType = arrayExpression.getResolvedType(ocResolveContext);
        final OCType resolvedType2 = indexExpression.getResolvedType();
        final ReadWriteAccessDetector.Access expressionAccess = new OCReadWriteAccessDetector().getExpressionAccess((PsiElement)this);
        final String a = this.a(resolvedType2);
        final String arraySubscriptAccessorName = this.getArraySubscriptAccessorName(resolvedType2, expressionAccess);
        Label_0135: {
            try {
                if (arraySubscriptAccessorName == null) {
                    break Label_0135;
                }
                final String s = a;
                if (s == null) {
                    break Label_0135;
                }
                break Label_0135;
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                final String s = a;
                if (s == null) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        OCType ocType;
        if (resolvedType.isPointerToID()) {
            ocType = OCReferenceType.resolvedFromText(a, containingFile);
        }
        else {
            if (!resolvedType.isPointerToObject()) {
                return null;
            }
            ocType = resolvedType.getTerminalType();
        }
        try {
            if (ocType instanceof OCObjectType) {
                return ((OCObjectType)ocType).findMember(arraySubscriptAccessorName, OCMethodSymbol.class);
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        return null;
    }
    
    @Nullable
    private String a(final OCType ocType) {
        try {
            if (ocType.isIntegerCompatible((PsiElement)this)) {
                return "NSMutableArray";
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocType.isPointerToObjectCompatible()) {
                return "NSMutableDictionary";
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return null;
    }
    
    public static boolean isArraySubscriptMethod(final OCMethodSymbol p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getName:()Ljava/lang/String;
        //     6: astore_1       
        //     7: ldc             "objectAtIndexedSubscript:"
        //     9: aload_1        
        //    10: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    13: ifne            64
        //    16: ldc             "setObject:atIndexedSubscript:"
        //    18: aload_1        
        //    19: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    22: ifne            64
        //    25: goto            32
        //    28: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCArraySelectionExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    31: athrow         
        //    32: ldc             "objectForKeyedSubscript:"
        //    34: aload_1        
        //    35: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    38: ifne            64
        //    41: goto            48
        //    44: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCArraySelectionExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    47: athrow         
        //    48: ldc             "setObject:forKeyedSubscript:"
        //    50: aload_1        
        //    51: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    54: ifeq            72
        //    57: goto            64
        //    60: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCArraySelectionExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    63: athrow         
        //    64: iconst_1       
        //    65: goto            73
        //    68: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCArraySelectionExpressionImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    71: athrow         
        //    72: iconst_0       
        //    73: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  7      25     28     32     Ljava/lang/IllegalArgumentException;
        //  16     41     44     48     Ljava/lang/IllegalArgumentException;
        //  32     57     60     64     Ljava/lang/IllegalArgumentException;
        //  48     68     68     72     Ljava/lang/IllegalArgumentException;
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
    
    @Nullable
    @Override
    public String getArraySubscriptAccessorName(final OCType ocType, final ReadWriteAccessDetector.Access access) {
        Label_0055: {
            Label_0034: {
                Label_0022: {
                    try {
                        if (!ocType.isIntegerCompatible((PsiElement)this)) {
                            break Label_0034;
                        }
                        final ReadWriteAccessDetector.Access access2 = access;
                        final ReadWriteAccessDetector.Access access3 = ReadWriteAccessDetector.Access.Read;
                        if (access2 == access3) {
                            break Label_0022;
                        }
                        return "setObject:atIndexedSubscript:";
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        final ReadWriteAccessDetector.Access access2 = access;
                        final ReadWriteAccessDetector.Access access3 = ReadWriteAccessDetector.Access.Read;
                        if (access2 == access3) {
                            return "objectAtIndexedSubscript:";
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
                return "setObject:atIndexedSubscript:";
                try {
                    if (!ocType.isPointerToObjectCompatible()) {
                        return null;
                    }
                    final ReadWriteAccessDetector.Access access4 = access;
                    final ReadWriteAccessDetector.Access access5 = ReadWriteAccessDetector.Access.Read;
                    if (access4 == access5) {
                        break Label_0055;
                    }
                    return "setObject:forKeyedSubscript:";
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            try {
                final ReadWriteAccessDetector.Access access4 = access;
                final ReadWriteAccessDetector.Access access5 = ReadWriteAccessDetector.Access.Read;
                if (access4 == access5) {
                    return "objectForKeyedSubscript:";
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return "setObject:forKeyedSubscript:";
    }
    
    @Nullable
    @Override
    public String getArraySubscriptMethodSignature(final OCType ocType, final ReadWriteAccessDetector.Access access) {
        Label_0055: {
            Label_0034: {
                Label_0022: {
                    try {
                        if (!ocType.isIntegerCompatible((PsiElement)this)) {
                            break Label_0034;
                        }
                        final ReadWriteAccessDetector.Access access2 = access;
                        final ReadWriteAccessDetector.Access access3 = ReadWriteAccessDetector.Access.Read;
                        if (access2 == access3) {
                            break Label_0022;
                        }
                        return "- (void)setObject:(id)obj atIndexedSubscript:(NSInteger)idx";
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        final ReadWriteAccessDetector.Access access2 = access;
                        final ReadWriteAccessDetector.Access access3 = ReadWriteAccessDetector.Access.Read;
                        if (access2 == access3) {
                            return "- (id)objectAtIndexedSubscript:(NSInteger)idx";
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
                return "- (void)setObject:(id)obj atIndexedSubscript:(NSInteger)idx";
                try {
                    if (!ocType.isPointerToObjectCompatible()) {
                        return null;
                    }
                    final ReadWriteAccessDetector.Access access4 = access;
                    final ReadWriteAccessDetector.Access access5 = ReadWriteAccessDetector.Access.Read;
                    if (access4 == access5) {
                        break Label_0055;
                    }
                    return "- (void)setObject:(id)obj forKeyedSubscript:(id <NSCopying>)key";
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            try {
                final ReadWriteAccessDetector.Access access4 = access;
                final ReadWriteAccessDetector.Access access5 = ReadWriteAccessDetector.Access.Read;
                if (access4 == access5) {
                    return "- (id)objectForKeyedSubscript:(id)key";
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return "- (void)setObject:(id)obj forKeyedSubscript:(id <NSCopying>)key";
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCArraySelectionExpressionImpl.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
