// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.project.Project;
import java.util.Iterator;
import com.jetbrains.cidr.lang.psi.OCFile;
import java.util.List;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContextUtil;
import java.util.Collection;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.BuilderDriverBase;
import com.intellij.util.diff.FlyweightCapableTreeStructure;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.symbols.OCBuilderDriver;
import com.intellij.psi.impl.source.tree.ASTStructure;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContext;
import java.util.ArrayList;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.jetbrains.cidr.lang.symbols.cpp.OCLabelSymbol;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import org.jetbrains.annotations.NonNls;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.psi.OCStatement;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCLabeledStatement;

public class OCLabeledStatementImpl extends OCElementBase implements OCLabeledStatement
{
    public OCLabeledStatementImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCLabeledStatementImpl", "<init>"));
        }
        super(astNode);
    }
    
    @NotNull
    @Override
    public String getLabel() {
        final PsiElement childByType = this.findChildByType(OCTokenTypes.IDENTIFIER);
        String s = null;
        Label_0027: {
            try {
                if (childByType != null) {
                    final String text;
                    s = (text = childByType.getText());
                    break Label_0027;
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            String text;
            s = (text = "");
            try {
                if (text == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCLabeledStatementImpl", "getLabel"));
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
        }
        return s;
    }
    
    @Nullable
    @Override
    public PsiElement getLabelElement() {
        return this.getNode().getFirstChildNode().getPsi();
    }
    
    @Override
    public OCStatement getStatement() {
        return this.findChildByType(OCElementTypes.STATEMENTS);
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCLabeledStatementImpl", "accept"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        ocVisitor.visitLabeledStatement(this);
    }
    
    public PsiElement getNameIdentifier() {
        return this.findChildByType(OCTokenTypes.IDENTIFIER);
    }
    
    @NotNull
    public String getName() {
        String label;
        try {
            label = this.getLabel();
            if (label == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCLabeledStatementImpl", "getName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return label;
    }
    
    public PsiElement setName(@NonNls @NotNull final String s) throws IncorrectOperationException {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/psi/impl/OCLabeledStatementImpl", "setName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        OCElementUtil.replaceWithIdentifier(this.getNameIdentifier(), s, (PsiElement)this);
        return (PsiElement)this;
    }
    
    @Nullable
    @Override
    public OCLabelSymbol getLocalSymbol() {
        final OCFile containingOCFile = this.getContainingOCFile();
        final OCCallable ocCallable = (OCCallable)PsiTreeUtil.getContextOfType((PsiElement)this, new Class[] { OCCallable.class });
        final ArrayList<String> list = new ArrayList<String>();
        final Iterator<PsiElement> iterator = this.findChildrenByType(OCElementTypes.ATTRIBUTES).iterator();
        while (iterator.hasNext()) {
            final ASTNode treeParent = iterator.next().getNode().getTreeParent();
            list.addAll(new OCBuilderDriver<ASTNode>(containingOCFile, OCInclusionContext.empty(containingOCFile.getKind(), (PsiFile)containingOCFile), (com.intellij.util.diff.FlyweightCapableTreeStructure<Object>)new ASTStructure(treeParent), (BuilderDriverBase.NamedNodeStructure<Object>)OCBuilderDriver.AST_NAMED_NODE_STRUCTURE, (Processor<OCSymbol>)CommonProcessors.alwaysTrue()).processAttributeList(treeParent));
        }
        Project project = null;
        VirtualFile virtualFile = null;
        int textOffset = 0;
        String name = null;
        Label_0194: {
            try {
                list.trimToSize();
                project = containingOCFile.getProject();
                virtualFile = OCInclusionContextUtil.getVirtualFile((PsiFile)containingOCFile);
                textOffset = this.getTextOffset();
                name = this.getName();
                if (ocCallable == null || ocCallable.getBody() == null) {
                    break Label_0194;
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            final TextRange textRange = ocCallable.getBody().getTextRange();
            return new OCLabelSymbol(project, virtualFile, textOffset, name, textRange, list);
        }
        final TextRange textRange = null;
        return new OCLabelSymbol(project, virtualFile, textOffset, name, textRange, list);
    }
    
    public OCLabelSymbol getSymbol() {
        return this.getLocalSymbol();
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
