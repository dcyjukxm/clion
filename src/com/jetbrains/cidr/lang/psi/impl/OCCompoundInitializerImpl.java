// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.psi.OCLiteralExpression;
import com.jetbrains.cidr.lang.util.OCParenthesesUtils;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.psi.OCDesignatedInitializer;
import java.util.Iterator;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.jetbrains.cidr.lang.types.OCTypeArgument;
import com.jetbrains.cidr.lang.symbols.OCQualifiedNameWithArguments;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import java.util.Collections;
import com.jetbrains.cidr.lang.types.OCTypeOwner;
import com.jetbrains.cidr.lang.resolve.OCArgumentsList;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.intellij.openapi.util.Pair;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.types.OCArrayType;
import com.jetbrains.cidr.lang.psi.OCElement;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.util.OCExpectedTypeUtil;
import com.jetbrains.cidr.lang.types.OCType;
import java.util.Collection;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.psi.OCCompoundInitializerMember;
import java.util.List;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCCompoundInitializer;

public class OCCompoundInitializerImpl extends OCExpressionBase implements OCCompoundInitializer
{
    public OCCompoundInitializerImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCCompoundInitializerImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCCompoundInitializerImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ocVisitor.visitCompoundInitializer(this);
    }
    
    @NotNull
    @Override
    public List<OCCompoundInitializerMember> getInitializers() {
        final ArrayList<OCCompoundInitializerMember> list = new ArrayList<OCCompoundInitializerMember>();
        for (ASTNode astNode = this.getNode().getFirstChildNode(); astNode != null; astNode = astNode.getTreeNext()) {
            final IElementType elementType = astNode.getElementType();
            try {
                if (OCElementTypes.EXPRESSIONS.contains(elementType)) {
                    list.add((OCCompoundInitializerMember)astNode.getPsi());
                    continue;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (elementType == OCElementTypes.DESIGNATED_INITIALIZER) {
                    list.add((OCCompoundInitializerMember)astNode.getPsi());
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        ArrayList<OCCompoundInitializerMember> list2;
        try {
            list2 = list;
            if (list2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCCompoundInitializerImpl", "getInitializers"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return list2;
    }
    
    @NotNull
    @Override
    public List<OCExpression> getInitializerExpressions() {
        List map;
        try {
            map = ContainerUtil.map((Collection)this.getInitializers(), ocCompoundInitializerMember -> {
                try {
                    if (ocCompoundInitializerMember instanceof OCDesignatedInitializer) {
                        return ((OCDesignatedInitializer)ocCompoundInitializerMember).getInitializer();
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                return (OCExpression)ocCompoundInitializerMember;
            });
            if (map == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCCompoundInitializerImpl", "getInitializerExpressions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (List<OCExpression>)map;
    }
    
    @Nullable
    @Override
    public OCType inferType() {
        return (OCType)ContainerUtil.getFirstItem((Collection)OCExpectedTypeUtil.getExpectedTypes(this, false));
    }
    
    @Nullable
    @Override
    public OCType inferChildType(final OCElement ocElement) {
        final OCType inferType = this.inferType();
        try {
            if (inferType == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final List<PsiElement> childrenByType = this.findChildrenByType(OCElementTypes.COMPOUND_INITIALIZER_EXPRESSIONS);
        final OCType resolve = inferType.resolve(this.getContainingFile());
        try {
            if (inferType instanceof OCArrayType) {
                return new ChildTypeInference((List)childrenByType, (PsiElement)ocElement).a((OCArrayType)resolve, null);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (resolve instanceof OCStructType) {
                return new ChildTypeInference((List)childrenByType, (PsiElement)ocElement).a((OCStructType)resolve, new HashSet());
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return null;
    }
    
    @Override
    public void inferChildTypes(final Map<PsiElement, Pair<OCType, OCSymbol>> map, final Set<OCSymbol> set) {
        Pair pair;
        if (map.containsKey(this)) {
            pair = map.get(this);
        }
        else {
            pair = new Pair((Object)this.inferType(), (Object)null);
            map.put((PsiElement)this, (Pair<OCType, OCSymbol>)pair);
        }
        try {
            if (pair == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final List<PsiElement> childrenByType = this.findChildrenByType(OCElementTypes.COMPOUND_INITIALIZER_EXPRESSIONS);
        final OCType resolve = ((OCType)pair.first).resolve(this.getContainingFile());
        try {
            if (resolve instanceof OCArrayType) {
                new ChildTypeInference(childrenByType, map).a((OCArrayType)resolve, null);
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (resolve instanceof OCStructType) {
                new ChildTypeInference(childrenByType, map).a((OCStructType)resolve, set);
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
    }
    
    @NotNull
    @Override
    public OCType getType(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCCompoundInitializerImpl", "getType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCType compoundInitializerType = null;
        Label_0105: {
            OCType ocType = null;
            Label_0070: {
                try {
                    if (OCCompilerFeatures.supportsInitializerLists((PsiFile)this.getContainingOCFile())) {
                        break Label_0105;
                    }
                    final OCCompoundInitializerImpl ocCompoundInitializerImpl = this;
                    final OCResolveContext ocResolveContext2 = ocResolveContext;
                    ocType = ocCompoundInitializerImpl.getType(ocResolveContext2);
                    if (ocType == null) {
                        break Label_0070;
                    }
                    return ocType;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final OCCompoundInitializerImpl ocCompoundInitializerImpl = this;
                    final OCResolveContext ocResolveContext2 = ocResolveContext;
                    ocType = ocCompoundInitializerImpl.getType(ocResolveContext2);
                    if (ocType == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCCompoundInitializerImpl", "getType"));
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            return ocType;
            try {
                compoundInitializerType = getCompoundInitializerType(OCArgumentsList.getArgumentList(this.getInitializerExpressions()), ocResolveContext);
                if (compoundInitializerType == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCCompoundInitializerImpl", "getType"));
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return compoundInitializerType;
    }
    
    @NotNull
    public static OCType getCompoundInitializerType(@NotNull final OCArgumentsList<? extends OCTypeOwner> list, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "arguments", "com/jetbrains/cidr/lang/psi/impl/OCCompoundInitializerImpl", "getCompoundInitializerType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCCompoundInitializerImpl", "getCompoundInitializerType"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        OCType ocType = null;
        for (final OCType ocType2 : list.getTypes()) {
            OCType leastCommonType = null;
            Label_0143: {
                try {
                    if (ocType == null) {
                        leastCommonType = ocType2;
                        break Label_0143;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                leastCommonType = ocType.getLeastCommonType(ocType2, ocResolveContext.getElement());
            }
            ocType = leastCommonType;
        }
        Object o = null;
        Label_0165: {
            try {
                if (ocType != null) {
                    o = Collections.singletonList(ocType);
                    break Label_0165;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            o = Collections.emptyList();
        }
        final Object o2 = o;
        OCReferenceType fromLocalText;
        try {
            fromLocalText = OCReferenceType.fromLocalText(new OCQualifiedNameWithArguments(OCQualifiedName.parse("std::initializer_list"), (List<OCTypeArgument>)o2));
            if (fromLocalText == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCCompoundInitializerImpl", "getCompoundInitializerType"));
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        return fromLocalText;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    private static class ChildTypeInference
    {
        private final PsiElement myTarget;
        private final List<PsiElement> myChildren;
        private final Map<PsiElement, Pair<OCType, OCSymbol>> myAcc;
        private final PsiFile myFile;
        private final int myChildrenSize;
        int myChildCounter;
        
        private ChildTypeInference(final List<PsiElement> myChildren, final PsiElement myTarget) {
            this.myChildren = myChildren;
            this.myChildrenSize = this.myChildren.size();
            this.myChildCounter = 0;
            this.myTarget = myTarget;
            this.myAcc = null;
            this.myFile = this.myTarget.getContainingFile();
        }
        
        public ChildTypeInference(final List<PsiElement> myChildren, final Map<PsiElement, Pair<OCType, OCSymbol>> myAcc) {
            this.myChildren = myChildren;
            this.myChildrenSize = this.myChildren.size();
            this.myChildCounter = 0;
            this.myTarget = null;
            this.myAcc = myAcc;
            this.myFile = (this.myChildren.isEmpty() ? null : this.myChildren.get(0).getContainingFile());
        }
        
        @Nullable
        private OCType a(final OCStructType ocStructType, final Set<OCSymbol> set) {
            final List<OCDeclaratorSymbol> fields = ocStructType.getFields();
            final int size = fields.size();
            int n = 0;
            while (n < size && this.myChildCounter < this.myChildrenSize) {
                final OCDeclaratorSymbol ocDeclaratorSymbol = fields.get(n);
                final PsiElement psiElement = this.myChildren.get(this.myChildCounter);
                if (ocDeclaratorSymbol.getKind() != OCSymbolKind.STRUCT_FIELD || set.contains(ocDeclaratorSymbol) || (ocDeclaratorSymbol.isUnnamed() && !(psiElement instanceof OCCompoundInitializer))) {
                    ++n;
                }
                else if (psiElement instanceof OCDesignatedInitializer) {
                    if (this.myAcc != null) {
                        this.myAcc.put(psiElement, (Pair<OCType, OCSymbol>)new Pair((Object)ocDeclaratorSymbol.getType(), (Object)ocDeclaratorSymbol));
                    }
                    if (psiElement == this.myTarget) {
                        return ocDeclaratorSymbol.getType();
                    }
                    set.add(((OCDesignatedInitializer)psiElement).getDesignation().resolveToSymbol());
                    ++this.myChildCounter;
                }
                else if (psiElement instanceof OCCompoundInitializer) {
                    if (this.myAcc != null) {
                        this.myAcc.put(psiElement, (Pair<OCType, OCSymbol>)new Pair((Object)ocDeclaratorSymbol.getType(), (Object)ocDeclaratorSymbol));
                        ((OCCompoundInitializer)psiElement).inferChildTypes(this.myAcc, ocDeclaratorSymbol.isUnnamed() ? set : new HashSet<OCSymbol>());
                    }
                    if (psiElement == this.myTarget) {
                        return ocDeclaratorSymbol.getType();
                    }
                    set.add(ocDeclaratorSymbol);
                    ++n;
                    ++this.myChildCounter;
                }
                else {
                    boolean b = false;
                    final OCType resolve = ocDeclaratorSymbol.getType().resolve(psiElement.getContainingFile());
                    if (psiElement instanceof OCExpression) {
                        final OCCompoundInitializer ocCompoundInitializer = (OCCompoundInitializer)psiElement;
                        if (resolve.isCompatible(ocCompoundInitializer.getResolvedType(), (PsiElement)ocCompoundInitializer.getContainingOCFile())) {
                            b = true;
                        }
                    }
                    if (!b && resolve instanceof OCStructType && ((OCStructType)resolve).getKind() == OCSymbolKind.STRUCT) {
                        final OCType a = this.a((OCStructType)resolve, new HashSet<OCSymbol>());
                        if (a != null) {
                            return a;
                        }
                        set.add(ocDeclaratorSymbol);
                        ++n;
                    }
                    else if (!b && resolve instanceof OCArrayType) {
                        final OCType a2 = this.a((OCArrayType)resolve, ocDeclaratorSymbol);
                        if (a2 != null) {
                            return a2;
                        }
                        set.add(ocDeclaratorSymbol);
                        ++n;
                    }
                    else {
                        if (this.myAcc != null) {
                            this.myAcc.put(psiElement, (Pair<OCType, OCSymbol>)new Pair((Object)ocDeclaratorSymbol.getType(), (Object)ocDeclaratorSymbol));
                        }
                        if (psiElement == this.myTarget) {
                            return ocDeclaratorSymbol.getType();
                        }
                        set.add(ocDeclaratorSymbol);
                        ++n;
                        ++this.myChildCounter;
                    }
                }
            }
            return null;
        }
        
        @Nullable
        private OCType a(final OCArrayType ocArrayType, @Nullable final OCSymbol ocSymbol) {
            OCType ocType = ocArrayType.getRefType();
            if (!this.myChildren.isEmpty()) {
                ocType = ocType.resolve(this.myChildren.get(0).getContainingFile());
            }
            int n = 0;
            final boolean b = (ocType instanceof OCStructType && ((OCStructType)ocType).isEmpty(new HashSet<OCStructSymbol>())) || (ocType instanceof OCArrayType && ((OCArrayType)ocType).isEmpty(this.myFile, new HashSet<OCStructSymbol>()));
            final int length = ocArrayType.getLength();
            while ((!ocArrayType.hasLength() || n < length) && this.myChildCounter < this.myChildrenSize) {
                final PsiElement psiElement = this.myChildren.get(this.myChildCounter);
                boolean b2 = false;
                if (psiElement instanceof OCDesignatedInitializer || psiElement instanceof OCCompoundInitializer) {
                    b2 = true;
                }
                else if (psiElement instanceof OCExpression && ocType.isCompatible(((OCExpression)psiElement).getResolvedType(), psiElement)) {
                    b2 = true;
                }
                if (b2) {
                    if (this.myAcc != null) {
                        this.myAcc.put(psiElement, (Pair<OCType, OCSymbol>)new Pair((Object)ocArrayType.getRefType(), (Object)null));
                    }
                    if (psiElement == this.myTarget) {
                        if (psiElement instanceof OCExpression && b) {
                            break;
                        }
                        return ocArrayType.getRefType();
                    }
                    else {
                        ++this.myChildCounter;
                        ++n;
                    }
                }
                else if (ocType instanceof OCStructType && ((OCStructType)ocType).getKind() == OCSymbolKind.STRUCT) {
                    if (b) {
                        break;
                    }
                    final OCType a = this.a((OCStructType)ocType, new HashSet<OCSymbol>());
                    if (a != null) {
                        return a;
                    }
                    ++n;
                }
                else if (ocType instanceof OCArrayType) {
                    if (b) {
                        break;
                    }
                    final OCType a2 = this.a((OCArrayType)ocType, null);
                    if (a2 != null) {
                        return a2;
                    }
                    ++n;
                }
                else {
                    boolean b3 = false;
                    OCExpression diveIntoParentheses = null;
                    if (psiElement instanceof OCExpression) {
                        diveIntoParentheses = OCParenthesesUtils.diveIntoParentheses((OCExpression)psiElement);
                    }
                    if (diveIntoParentheses instanceof OCLiteralExpression && ocArrayType.isCString() && ((OCExpression)psiElement).getResolvedType().isCString()) {
                        b3 = true;
                    }
                    final OCType ocType2 = b3 ? ocArrayType : ocArrayType.getRefType();
                    final OCSymbol ocSymbol2 = b3 ? ocSymbol : null;
                    if (this.myAcc != null) {
                        this.myAcc.put(psiElement, (Pair<OCType, OCSymbol>)Pair.create((Object)ocType2, (Object)ocSymbol2));
                    }
                    if (psiElement == this.myTarget) {
                        return ocType2;
                    }
                    ++this.myChildCounter;
                    if (b3) {
                        break;
                    }
                    ++n;
                }
            }
            return null;
        }
    }
}
