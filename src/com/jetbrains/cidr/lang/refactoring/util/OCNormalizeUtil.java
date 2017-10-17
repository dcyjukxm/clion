// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.util;

import com.jetbrains.cidr.lang.psi.OCSynthesizePropertiesList;
import com.jetbrains.cidr.lang.psi.OCSynthesizeProperty;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import java.util.Iterator;
import com.intellij.lang.ASTNode;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.TokenSet;
import com.intellij.psi.impl.source.codeStyle.CodeEditUtil;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.intellij.util.IncorrectOperationException;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.psi.OCDeclarator;

public class OCNormalizeUtil
{
    @Nullable
    public static OCDeclaration normalizeDeclarator(final OCDeclarator ocDeclarator) throws IncorrectOperationException {
        final Ref create = Ref.create((Object)null);
        final OCDeclaration ocDeclaration;
        final ASTNode astNode;
        final Object o;
        final Object o2;
        final Object o3;
        final Object o4;
        final Ref ref;
        final ASTNode parent;
        final ASTNode child;
        final ASTNode astNode2;
        final ASTNode[] array;
        final int length;
        int i;
        ASTNode astNode3;
        final OCDeclaration ocDeclaration2;
        final int n;
        final OCDeclarator ocDeclarator2;
        final Iterator<OCDeclarator> iterator;
        OCDeclarator ocDeclarator3;
        ApplicationManager.getApplication().runWriteAction(() -> {
            ocDeclaration = (OCDeclaration)ocDeclarator.getParent();
            ocDeclaration.getNode();
            ocDeclaration.getDeclarators().indexOf(ocDeclarator);
            Label_0057_1: {
                try {
                    if (astNode != null) {
                        ((OCDeclaration)o).getDeclarators();
                        ((List)o2).size();
                        if (o3 <= o4) {
                            break Label_0057_1;
                        }
                        else {
                            break Label_0057_1;
                        }
                    }
                    else {
                        break Label_0057_1;
                    }
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                try {
                    ((OCDeclaration)o).getDeclarators();
                    ((List)o2).size();
                    if (o3 <= o4) {
                        ref.set((Object)ocDeclaration);
                        return;
                    }
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
            }
            astNode.getTreeParent();
            Label_0119_1: {
                try {
                    if (parent.getElementType() != OCElementTypes.DECLARATION_STATEMENT) {
                        if (parent.getElementType() != OCElementTypes.PROPERTY) {
                            break Label_0119_1;
                        }
                    }
                }
                catch (IncorrectOperationException ex3) {
                    throw a(ex3);
                }
                astNode.getTreeParent();
            }
            astNode.copyElement();
            CodeEditUtil.addChild(parent, child, astNode.getTreeNext());
            if (astNode2.getElementType() != OCElementTypes.DECLARATION) {
                astNode2.getChildren((TokenSet)null);
                length = array.length;
                while (i < length) {
                    astNode3 = array[i];
                    if (astNode3.getElementType() == OCElementTypes.DECLARATION) {
                        break;
                    }
                    else {
                        ++i;
                    }
                }
            }
            try {
                if (astNode2.getElementType() != OCElementTypes.DECLARATION) {
                    return;
                }
            }
            catch (IncorrectOperationException ex4) {
                throw a(ex4);
            }
            ocDeclaration2 = (OCDeclaration)astNode2.getPsi((Class)OCDeclaration.class);
            try {
                if (ocDeclaration2 == null) {
                    return;
                }
            }
            catch (IncorrectOperationException ex5) {
                throw a(ex5);
            }
            ocDeclarator2 = ocDeclaration2.getDeclarators().get(n);
            OCChangeUtil.delete((PsiElement)ocDeclarator);
            ocDeclaration2.getDeclarators().iterator();
            while (iterator.hasNext()) {
                ocDeclarator3 = iterator.next();
                try {
                    if (ocDeclarator3 != ocDeclarator2) {
                        OCChangeUtil.delete((PsiElement)ocDeclarator3);
                    }
                    else {
                        continue;
                    }
                }
                catch (IncorrectOperationException ex6) {
                    throw a(ex6);
                }
            }
            CodeStyleManager.getInstance(ocDeclaration2.getProject()).reformat((PsiElement)ocDeclaration2);
            ref.set((Object)ocDeclaration2);
            return;
        });
        return (OCDeclaration)create.get();
    }
    
    public static List<OCDeclaration> normalizeDeclaration(final OCDeclaration ocDeclaration) {
        final ArrayList<OCDeclaration> list = new ArrayList<OCDeclaration>();
        for (int size = ocDeclaration.getDeclarators().size(), i = 0; i < size; ++i) {
            final List<OCDeclarator> declarators = ocDeclaration.getDeclarators();
            list.add(normalizeDeclarator(declarators.get(declarators.size() - 1)));
        }
        return list;
    }
    
    public static OCSynthesizePropertiesList normalizeSynthesizeStatement(final OCSynthesizeProperty ocSynthesizeProperty) {
        final Ref create = Ref.create((Object)null);
        final OCSynthesizePropertiesList list;
        final ASTNode astNode;
        final Object o;
        final Object o2;
        final Object o3;
        final Object o4;
        final Ref ref;
        final ASTNode parent;
        final ASTNode child;
        final OCSynthesizePropertiesList list2;
        final int n;
        final OCSynthesizeProperty ocSynthesizeProperty2;
        final Iterator<OCSynthesizeProperty> iterator;
        OCSynthesizeProperty ocSynthesizeProperty3;
        ApplicationManager.getApplication().runWriteAction(() -> {
            list = (OCSynthesizePropertiesList)ocSynthesizeProperty.getParent();
            list.getNode();
            list.getProperties().indexOf(ocSynthesizeProperty);
            Label_0057_1: {
                try {
                    if (astNode != null) {
                        ((OCSynthesizePropertiesList)o).getProperties();
                        ((List)o2).size();
                        if (o3 <= o4) {
                            break Label_0057_1;
                        }
                        else {
                            break Label_0057_1;
                        }
                    }
                    else {
                        break Label_0057_1;
                    }
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                try {
                    ((OCSynthesizePropertiesList)o).getProperties();
                    ((List)o2).size();
                    if (o3 <= o4) {
                        ref.set((Object)list);
                        return;
                    }
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
            }
            astNode.getTreeParent();
            astNode.copyElement();
            CodeEditUtil.addChild(parent, child, astNode.getTreeNext());
            list2 = (OCSynthesizePropertiesList)child.getPsi();
            try {
                if (list2 == null) {
                    return;
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
            ocSynthesizeProperty2 = list2.getProperties().get(n);
            OCChangeUtil.delete((PsiElement)ocSynthesizeProperty);
            list2.getProperties().iterator();
            while (iterator.hasNext()) {
                ocSynthesizeProperty3 = iterator.next();
                try {
                    if (ocSynthesizeProperty3 != ocSynthesizeProperty2) {
                        OCChangeUtil.delete((PsiElement)ocSynthesizeProperty3);
                    }
                    else {
                        continue;
                    }
                }
                catch (IncorrectOperationException ex4) {
                    throw a(ex4);
                }
            }
            CodeStyleManager.getInstance(list2.getProject()).reformat((PsiElement)list2);
            ref.set((Object)list2);
            return;
        });
        return (OCSynthesizePropertiesList)create.get();
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
