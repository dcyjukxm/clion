// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.parser.OCElementType;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Collections;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.symtable.OCGlobalProjectSymbolsCache;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.intellij.util.CommonProcessors;
import java.util.List;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.IncorrectOperationException;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.resolve.references.OCPolyVariantReferenceImpl;
import com.intellij.psi.PsiReference;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCClassPredeclaration;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;

public class OCClassPredeclarationImpl extends OCClassDeclarationBaseImpl<OCClassSymbol> implements OCClassPredeclaration
{
    public OCClassPredeclarationImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCClassPredeclarationImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCClassPredeclarationImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        ocVisitor.visitClassPredeclaration(this);
    }
    
    @Override
    public String getName() {
        final ASTNode a = this.a();
        try {
            if (a != null) {
                return a.getText();
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return null;
    }
    
    @Nullable
    private ASTNode a() {
        return this.getNode().findChildByType((IElementType)OCTokenTypes.IDENTIFIER);
    }
    
    @Nullable
    @Override
    protected PsiReference createReference() {
        return (PsiReference)new OCPolyVariantReferenceImpl() {
            static final /* synthetic */ boolean $assertionsDisabled;
            
            public PsiElement getElement() {
                return (PsiElement)OCClassPredeclarationImpl.this;
            }
            
            public TextRange getRangeInElement() {
                final ASTNode access$000 = OCClassPredeclarationImpl.this.a();
                try {
                    if (access$000 != null) {
                        return OCElementUtil.getRangeInParent(access$000);
                    }
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                return TextRange.EMPTY_RANGE;
            }
            
            @NotNull
            public String getCanonicalText() {
                final String name = OCClassPredeclarationImpl.this.getName();
                String s = null;
                Label_0022: {
                    try {
                        if (name != null) {
                            final String s2;
                            s = (s2 = name);
                            break Label_0022;
                        }
                    }
                    catch (IncorrectOperationException ex) {
                        throw a(ex);
                    }
                    String s2;
                    s = (s2 = "<unnamed>");
                    try {
                        if (s2 == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCClassPredeclarationImpl$1", "getCanonicalText"));
                        }
                    }
                    catch (IncorrectOperationException ex2) {
                        throw a(ex2);
                    }
                }
                return s;
            }
            
            public PsiElement handleElementRename(final String s) throws IncorrectOperationException {
                return (PsiElement)OCClassPredeclarationImpl.this;
            }
            
            @Override
            public PsiElement bindToSymbol(@NotNull final OCSymbol ocSymbol) {
                try {
                    if (ocSymbol == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/psi/impl/OCClassPredeclarationImpl$1", "bindToSymbol"));
                    }
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                Label_0064: {
                    try {
                        if (OCClassPredeclarationImpl$1.$assertionsDisabled) {
                            return this.handleElementRename(ocSymbol.getName());
                        }
                        final OCSymbol ocSymbol2 = ocSymbol;
                        final boolean b = ocSymbol2 instanceof OCClassSymbol;
                        if (!b) {
                            break Label_0064;
                        }
                        return this.handleElementRename(ocSymbol.getName());
                    }
                    catch (IncorrectOperationException ex2) {
                        throw a(ex2);
                    }
                    try {
                        final OCSymbol ocSymbol2 = ocSymbol;
                        final boolean b = ocSymbol2 instanceof OCClassSymbol;
                        if (!b) {
                            throw new AssertionError();
                        }
                    }
                    catch (IncorrectOperationException ex3) {
                        throw a(ex3);
                    }
                }
                return this.handleElementRename(ocSymbol.getName());
            }
            
            public PsiElement bindToElement(@NotNull final PsiElement psiElement) throws IncorrectOperationException {
                try {
                    if (psiElement == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/psi/impl/OCClassPredeclarationImpl$1", "bindToElement"));
                    }
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                final OCSymbol symbol = ((OCSymbolDeclarator)psiElement).getSymbol();
                try {
                    if (symbol != null) {
                        return this.bindToSymbol(symbol);
                    }
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
                return psiElement;
            }
            
            public boolean isSoft() {
                return false;
            }
            
            @NotNull
            @Override
            public List<? extends OCSymbol> resolveToSymbols() {
                final CommonProcessors.CollectProcessor<OCSymbol> collectProcessor = new CommonProcessors.CollectProcessor<OCSymbol>() {
                    final /* synthetic */ boolean val$isProtocol = OCClassPredeclarationImpl.this.isProtocol();
                    
                    public boolean process(final OCSymbol ocSymbol) {
                        return (ocSymbol.getKind() != OCSymbolKind.INTERFACE && ocSymbol.getKind() != OCSymbolKind.PROTOCOL) || ocSymbol.isPredeclaration() || ((OCClassSymbol)ocSymbol).getCategoryName() != null || this.val$isProtocol != (ocSymbol.getKind() == OCSymbolKind.PROTOCOL) || super.process((Object)ocSymbol);
                    }
                };
                final String name = OCClassPredeclarationImpl.this.getName();
                try {
                    if (name != null) {
                        OCGlobalProjectSymbolsCache.processTopLevelSymbols(OCClassPredeclarationImpl.this.getProject(), (Processor<OCSymbol>)collectProcessor, name);
                    }
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                ArrayList list2 = null;
                Label_0111: {
                    List<? extends OCSymbol> list = null;
                    Label_0076: {
                        try {
                            if (!collectProcessor.getResults().isEmpty()) {
                                break Label_0111;
                            }
                            list = Collections.emptyList();
                            if (list == null) {
                                break Label_0076;
                            }
                            return list;
                        }
                        catch (IncorrectOperationException ex2) {
                            throw a(ex2);
                        }
                        try {
                            list = Collections.emptyList();
                            if (list == null) {
                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCClassPredeclarationImpl$1", "resolveToSymbols"));
                            }
                        }
                        catch (IncorrectOperationException ex3) {
                            throw a(ex3);
                        }
                    }
                    return list;
                    try {
                        list2 = new ArrayList(collectProcessor.getResults());
                        if (list2 == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCClassPredeclarationImpl$1", "resolveToSymbols"));
                        }
                    }
                    catch (IncorrectOperationException ex4) {
                        throw a(ex4);
                    }
                }
                return (List<? extends OCSymbol>)list2;
            }
            
            static {
                boolean $assertionsDisabled2 = false;
                Label_0017: {
                    try {
                        if (!OCClassPredeclarationImpl.class.desiredAssertionStatus()) {
                            $assertionsDisabled2 = true;
                            break Label_0017;
                        }
                    }
                    catch (IncorrectOperationException ex) {
                        throw a(ex);
                    }
                    $assertionsDisabled2 = false;
                }
                $assertionsDisabled = $assertionsDisabled2;
            }
            
            private static IncorrectOperationException a(final IncorrectOperationException ex) {
                return ex;
            }
        };
    }
    
    @Override
    public boolean isProtocol() {
        final PsiElement parent = this.getParent();
        Label_0031: {
            try {
                if (parent == null) {
                    return false;
                }
                final PsiElement psiElement = parent;
                final ASTNode astNode = psiElement.getNode();
                final OCElementType ocElementType = OCTokenTypes.PROTOCOL_KEYWORD;
                final ASTNode astNode2 = OCElementUtil.findObjCKeyword(astNode, ocElementType);
                if (astNode2 != null) {
                    break Label_0031;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            try {
                final PsiElement psiElement = parent;
                final ASTNode astNode = psiElement.getNode();
                final OCElementType ocElementType = OCTokenTypes.PROTOCOL_KEYWORD;
                final ASTNode astNode2 = OCElementUtil.findObjCKeyword(astNode, ocElementType);
                if (astNode2 != null) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
        }
        return false;
    }
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}
