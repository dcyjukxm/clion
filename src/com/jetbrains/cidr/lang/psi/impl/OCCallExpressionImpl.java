// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.intellij.psi.PsiReference;
import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.lang.types.OCIntType;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.types.OCNumericType;
import com.jetbrains.cidr.lang.types.OCMagicType;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.types.OCUnknownType;
import com.jetbrains.cidr.lang.resolve.OCArgumentsList;
import com.jetbrains.cidr.lang.types.OCTypeUtils;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import java.util.Iterator;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import java.util.Collection;
import java.util.Collections;
import com.jetbrains.cidr.lang.psi.OCArgumentList;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import java.util.ArrayList;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.types.OCTypeOwner;
import com.intellij.psi.PsiElement;
import java.util.List;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.intellij.psi.MultiRangeReference;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.types.OCType;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCCallExpression;
import com.jetbrains.cidr.lang.resolve.references.OCOperatorReference;

public class OCCallExpressionImpl extends OCExpressionWithReferenceBase<OCOperatorReference> implements OCCallExpression
{
    public OCCallExpressionImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCCallExpressionImpl", "<init>"));
        }
        super(astNode);
    }
    
    @NotNull
    @Override
    protected OCOperatorReference createReference() {
        OCOperatorReference reference;
        try {
            reference = this.createReference(this.getFunctionReferenceExpression(), null);
            if (reference == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCCallExpressionImpl", "createReference"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return reference;
    }
    
    public OCOperatorReference createReference(final OCExpression ocExpression, @Nullable final OCType ocType) {
        List<OCType> singletonList = null;
        Label_0016: {
            try {
                if (ocType != null) {
                    singletonList = Collections.singletonList(ocType);
                    break Label_0016;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            singletonList = null;
        }
        final List<OCType> list = singletonList;
        final ArrayList<OCExpression> list2 = new ArrayList<OCExpression>();
        list2.add(ocExpression);
        list2.addAll((Collection<?>)this.getArguments());
        class 1OCCallOperatorReference extends OCOperatorReference implements MultiRangeReference
        {
            final /* synthetic */ OCCallExpressionImpl this$0;
            
            1OCCallOperatorReference(final List<OCExpression> list, final List<OCType> list2) {
                super(ocElement, "()", OperatorPlacement.POSTFIX, null, list, list2);
            }
            
            @Override
            public boolean isValid() {
                Label_0040: {
                    try {
                        if (!super.isValid()) {
                            return false;
                        }
                        final 1OCCallOperatorReference 1ocCallOperatorReference = this;
                        final List<? extends OCTypeOwner> list = 1ocCallOperatorReference.myArguments;
                        final int n = list.size();
                        final 1OCCallOperatorReference 1ocCallOperatorReference2 = this;
                        final OCCallExpressionImpl ocCallExpressionImpl = 1ocCallOperatorReference2.this$0;
                        final List<OCExpression> list2 = ocCallExpressionImpl.getArguments();
                        final int n2 = list2.size();
                        final int n3 = 1;
                        final int n4 = n2 + n3;
                        if (n == n4) {
                            break Label_0040;
                        }
                        return false;
                    }
                    catch (IllegalStateException ex) {
                        throw a(ex);
                    }
                    try {
                        final 1OCCallOperatorReference 1ocCallOperatorReference = this;
                        final List<? extends OCTypeOwner> list = 1ocCallOperatorReference.myArguments;
                        final int n = list.size();
                        final 1OCCallOperatorReference 1ocCallOperatorReference2 = this;
                        final OCCallExpressionImpl ocCallExpressionImpl = 1ocCallOperatorReference2.this$0;
                        final List<OCExpression> list2 = ocCallExpressionImpl.getArguments();
                        final int n2 = list2.size();
                        final int n3 = 1;
                        final int n4 = n2 + n3;
                        if (n == n4) {
                            return true;
                        }
                    }
                    catch (IllegalStateException ex2) {
                        throw a(ex2);
                    }
                }
                return false;
            }
            
            @NotNull
            public List<TextRange> getRanges() {
                final ArrayList<TextRange> list = new ArrayList<TextRange>();
                final OCArgumentList argumentList = OCCallExpressionImpl.this.getArgumentList();
                final TextRange rangeInParent = OCElementUtil.getRangeInParent((PsiElement)argumentList);
                final PsiElement leftPar = argumentList.getLeftPar();
                try {
                    if (leftPar != null) {
                        list.add(OCElementUtil.getRangeInParent(leftPar).shiftRight(rangeInParent.getStartOffset()));
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                final PsiElement rightPar = argumentList.getRightPar();
                try {
                    if (rightPar != null) {
                        list.add(OCElementUtil.getRangeInParent(rightPar).shiftRight(rangeInParent.getStartOffset()));
                    }
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
                ArrayList<TextRange> list2;
                try {
                    list2 = list;
                    if (list2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCCallExpressionImpl$1OCCallOperatorReference", "getRanges"));
                    }
                }
                catch (IllegalStateException ex3) {
                    throw a(ex3);
                }
                return list2;
            }
            
            private static IllegalStateException a(final IllegalStateException ex) {
                return ex;
            }
        }
        return new 1OCCallOperatorReference(this, list2, list);
    }
    
    @NotNull
    @Override
    public OCExpression getFunctionReferenceExpression() {
        OCExpression ocExpression;
        try {
            ocExpression = this.findChildByType(OCElementTypes.EXPRESSIONS);
            if (ocExpression == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCCallExpressionImpl", "getFunctionReferenceExpression"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return ocExpression;
    }
    
    @NotNull
    @Override
    public OCArgumentList getArgumentList() {
        OCArgumentList list;
        try {
            list = this.findNotNullChildByType(OCElementTypes.ARGUMENT_LIST);
            if (list == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCCallExpressionImpl", "getArgumentList"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return list;
    }
    
    @NotNull
    @Override
    public List<OCExpression> getArguments() {
        List<OCExpression> arguments;
        try {
            arguments = this.getArgumentList().getArguments();
            if (arguments == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCCallExpressionImpl", "getArguments"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return arguments;
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCCallExpressionImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ocVisitor.visitCallExpression(this);
    }
    
    @NotNull
    @Override
    public OCType getType(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCCallExpressionImpl", "getType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCExpression functionReferenceExpression = this.getFunctionReferenceExpression();
        final OCType calleeType = this.getCalleeType(ocResolveContext);
        final OCOperatorReference reference = this.createReference(functionReferenceExpression, calleeType);
        if (reference != null) {
            for (final OCSymbol ocSymbol : reference.resolveToSymbols(ocResolveContext)) {
                OCType ocType = null;
                Label_0129: {
                    try {
                        if (!(ocSymbol instanceof OCFunctionSymbol)) {
                            continue;
                        }
                        final OCSymbol ocSymbol2 = ocSymbol;
                        ocType = ocSymbol2.getEffectiveType();
                        if (ocType == null) {
                            break Label_0129;
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
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCCallExpressionImpl", "getType"));
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                }
                return ocType;
            }
        }
        OCSymbol ocSymbol3 = null;
        if (functionReferenceExpression instanceof OCReferenceExpression) {
            final OCReferenceElement referenceElement = ((OCReferenceExpression)functionReferenceExpression).getReferenceElement();
            OCSymbol resolveToSymbol = null;
            Label_0209: {
                try {
                    if (referenceElement != null) {
                        resolveToSymbol = referenceElement.resolveToSymbol(ocResolveContext);
                        break Label_0209;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
                resolveToSymbol = null;
            }
            ocSymbol3 = resolveToSymbol;
        }
        final OCType callExprType = getCallExprType(calleeType, ocSymbol3, ocResolveContext);
        final OCType a = this.a();
        OCType ocType2 = null;
        Label_0247: {
            try {
                if (a != null) {
                    final OCType cloneWithGuessedType;
                    ocType2 = (cloneWithGuessedType = callExprType.cloneWithGuessedType(a));
                    break Label_0247;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
            OCType cloneWithGuessedType;
            ocType2 = (cloneWithGuessedType = callExprType);
            try {
                if (cloneWithGuessedType == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCCallExpressionImpl", "getType"));
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
        }
        return ocType2;
    }
    
    @Nullable
    @Override
    public OCType getCalleeType(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCCallExpressionImpl", "getCalleeType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCType ocType = this.getFunctionReferenceExpression().getResolvedType(ocResolveContext);
        if (OCTypeUtils.isUnresolvedLambdaAutoType(ocType)) {
            ocType = OCTypeUtils.resolveLambdaAutoType(ocType, ocResolveContext, OCArgumentsList.getArgumentList(this.getArguments()), false);
        }
        return ocType;
    }
    
    @NotNull
    public static OCType getCallExprType(@Nullable OCType terminalType, @Nullable final OCSymbol ocSymbol, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCCallExpressionImpl", "getCallExprType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCType ocType = OCUnknownType.INSTANCE;
        OCType ocType3 = null;
        Label_0211: {
            OCType ocType2 = null;
            Project project = null;
            Label_0207: {
                Label_0193: {
                    Label_0174: {
                        if (ocSymbol instanceof OCStructSymbol) {
                            ocType = ocSymbol.getType();
                        }
                        else {
                            Label_0104: {
                                Label_0088: {
                                    try {
                                        if (ocSymbol == null) {
                                            break Label_0104;
                                        }
                                        final OCSymbol ocSymbol2 = ocSymbol;
                                        final OCSymbolKind ocSymbolKind = ocSymbol2.getKind();
                                        final OCSymbolKind ocSymbolKind2 = OCSymbolKind.TYPEDEF;
                                        if (ocSymbolKind == ocSymbolKind2) {
                                            break Label_0088;
                                        }
                                        break Label_0104;
                                    }
                                    catch (IllegalArgumentException ex2) {
                                        throw a(ex2);
                                    }
                                    try {
                                        final OCSymbol ocSymbol2 = ocSymbol;
                                        final OCSymbolKind ocSymbolKind = ocSymbol2.getKind();
                                        final OCSymbolKind ocSymbolKind2 = OCSymbolKind.TYPEDEF;
                                        if (ocSymbolKind != ocSymbolKind2) {
                                            break Label_0104;
                                        }
                                        if (terminalType == null) {
                                            break Label_0104;
                                        }
                                    }
                                    catch (IllegalArgumentException ex3) {
                                        throw a(ex3);
                                    }
                                }
                                ocType = terminalType;
                                break Label_0174;
                            }
                            if (terminalType != null) {
                                terminalType = terminalType.getTerminalType();
                                if (terminalType instanceof OCStructType) {
                                    ocType = terminalType;
                                }
                                else if (terminalType instanceof OCFunctionType) {
                                    ocType = ((OCFunctionType)terminalType).getReturnType();
                                }
                                else {
                                    try {
                                        if (!terminalType.isMagicInside(ocResolveContext) || terminalType.isUnresolved(ocResolveContext)) {
                                            break Label_0174;
                                        }
                                    }
                                    catch (IllegalArgumentException ex4) {
                                        throw a(ex4);
                                    }
                                    ocType = new OCMagicType();
                                }
                            }
                        }
                        try {
                            if (!(ocType instanceof OCNumericType)) {
                                break Label_0211;
                            }
                            ocType2 = ocType;
                            final OCSymbol ocSymbol3 = ocSymbol;
                            if (ocSymbol3 != null) {
                                break Label_0193;
                            }
                            break Label_0193;
                        }
                        catch (IllegalArgumentException ex5) {
                            throw a(ex5);
                        }
                    }
                    try {
                        ocType2 = ocType;
                        final OCSymbol ocSymbol3 = ocSymbol;
                        if (ocSymbol3 != null) {
                            project = ocSymbol.getProject();
                            break Label_0207;
                        }
                    }
                    catch (IllegalArgumentException ex6) {
                        throw a(ex6);
                    }
                }
                project = null;
            }
            ocType = ocType2.cloneWithoutCVQualifiers(project);
            try {
                ocType3 = ocType;
                if (ocType3 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCCallExpressionImpl", "getCallExprType"));
                }
            }
            catch (IllegalArgumentException ex7) {
                throw a(ex7);
            }
        }
        return ocType3;
    }
    
    @Nullable
    private OCType a() {
        final OCExpression functionReferenceExpression = this.getFunctionReferenceExpression();
        if (functionReferenceExpression instanceof OCReferenceExpression) {
            final OCSymbol resolveToSymbol = ((OCReferenceExpression)functionReferenceExpression).resolveToSymbol();
            try {
                if (!(resolveToSymbol instanceof OCFunctionSymbol) || !resolveToSymbol.getName().startsWith("is")) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            final VirtualFile containingFile = resolveToSymbol.getContainingFile();
            Label_0080: {
                try {
                    if (containingFile == null) {
                        return null;
                    }
                    final VirtualFile virtualFile = containingFile;
                    final String s = virtualFile.getName();
                    final String s2 = "ctype.h";
                    final boolean b = s.equals(s2);
                    if (b) {
                        break Label_0080;
                    }
                    return null;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final VirtualFile virtualFile = containingFile;
                    final String s = virtualFile.getName();
                    final String s2 = "ctype.h";
                    final boolean b = s.equals(s2);
                    if (b) {
                        return OCIntType.getAppropriateBool(this);
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
        }
        return null;
    }
    
    @Override
    protected List<OCExpression> getDependentExpressions() {
        final OCExpression functionReferenceExpression = this.getFunctionReferenceExpression();
        try {
            if (functionReferenceExpression instanceof OCQualifiedExpression) {
                return Collections.singletonList(((OCQualifiedExpression)functionReferenceExpression).getQualifier());
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return Collections.emptyList();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
