// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.hierarchy.structureVIew;

import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import java.util.Set;
import com.intellij.ide.util.treeView.smartTree.ActionPresentationData;
import com.intellij.icons.AllIcons;
import com.intellij.ide.util.treeView.smartTree.ActionPresentation;
import java.util.List;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.intellij.psi.PsiElement;
import java.util.Collections;
import java.util.ArrayList;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import java.util.HashSet;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import java.util.Collection;
import com.intellij.ide.util.treeView.smartTree.TreeElement;
import com.intellij.openapi.keymap.KeymapUtil;
import com.intellij.openapi.actionSystem.Shortcut;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.NonNls;
import com.intellij.ide.util.FileStructureNodeProvider;

public class OCInheritorsAndCategoriesNodeProvider implements FileStructureNodeProvider<OCStructureViewElement>
{
    @NonNls
    private static final String ID = "INHERITORS_AND_CATEGORIES";
    
    @NotNull
    public String getCheckBoxText() {
        String s;
        try {
            s = "Other Categories and Inherited Members";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/hierarchy/structureVIew/OCInheritorsAndCategoriesNodeProvider", "getCheckBoxText"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    @NotNull
    public Shortcut[] getShortcut() {
        Shortcut[] shortcuts;
        try {
            shortcuts = KeymapUtil.getActiveKeymapShortcuts("FileStructurePopup").getShortcuts();
            if (shortcuts == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/hierarchy/structureVIew/OCInheritorsAndCategoriesNodeProvider", "getShortcut"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return shortcuts;
    }
    
    @NotNull
    public Collection<OCStructureViewElement> provideNodes(@NotNull final TreeElement treeElement) {
        try {
            if (treeElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/hierarchy/structureVIew/OCInheritorsAndCategoriesNodeProvider", "provideNodes"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        List<OCStructureViewElement> emptyList = null;
        Label_0218: {
            try {
                if (!(treeElement instanceof OCStructureViewElement) || !((OCStructureViewElement)treeElement).isAllowInheritorsAndCategories()) {
                    break Label_0218;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            final PsiElement value = ((OCStructureViewElement)treeElement).getValue();
            if (value instanceof OCClassDeclaration) {
                final OCClassSymbol symbol = ((OCClassDeclaration)value).getSymbol();
                if (symbol != null) {
                    final HashSet set = new HashSet();
                    symbol.processMembers(OCMemberSymbol.class, (com.intellij.util.Processor<? super OCMemberSymbol>)(ocMemberSymbol -> {
                        set.add(ocMemberSymbol.getNameWithParent());
                        return true;
                    }));
                    final OCObjectType type = ((OCClassDeclaration)value).getType(true);
                    if (type != null) {
                        final ArrayList list = new ArrayList<OCStructureViewElement>();
                        final HashSet set2 = new HashSet();
                        ArrayList list2;
                        try {
                            type.processMembers(OCMemberSymbol.class, (com.intellij.util.Processor<? super OCMemberSymbol>)(ocMemberSymbol -> {
                                try {
                                    if (ocMemberSymbol instanceof OCInstanceVariableSymbol) {
                                        return true;
                                    }
                                }
                                catch (IllegalStateException ex) {
                                    throw a(ex);
                                }
                                final OCClassSymbol ocClassSymbol = ((OCSymbolWithParent<T, OCClassSymbol>)ocMemberSymbol).getParent();
                                Label_0055: {
                                    try {
                                        if (ocClassSymbol == null) {
                                            return true;
                                        }
                                        final Set<OCClassSymbol> set3 = (Set<OCClassSymbol>)set2;
                                        final OCClassSymbol ocClassSymbol2 = ocClassSymbol;
                                        final boolean b = set3.contains(ocClassSymbol2);
                                        if (b) {
                                            return true;
                                        }
                                        break Label_0055;
                                    }
                                    catch (IllegalStateException ex2) {
                                        throw a(ex2);
                                    }
                                    try {
                                        final Set<OCClassSymbol> set3 = (Set<OCClassSymbol>)set2;
                                        final OCClassSymbol ocClassSymbol2 = ocClassSymbol;
                                        final boolean b = set3.contains(ocClassSymbol2);
                                        if (b) {
                                            return true;
                                        }
                                    }
                                    catch (IllegalStateException ex3) {
                                        throw a(ex3);
                                    }
                                    try {
                                        if (!set.add(ocMemberSymbol.getNameWithParent())) {
                                            return true;
                                        }
                                    }
                                    catch (IllegalStateException ex4) {
                                        throw a(ex4);
                                    }
                                }
                                Label_0150: {
                                    try {
                                        if (!set2.add(ocClassSymbol)) {
                                            return true;
                                        }
                                        if (!"NSObject".equals(ocClassSymbol.getName())) {
                                            break Label_0150;
                                        }
                                    }
                                    catch (IllegalStateException ex5) {
                                        throw a(ex5);
                                    }
                                    final String categoryName = ocClassSymbol.getCategoryName();
                                    try {
                                        if (StringUtil.isEmpty(categoryName)) {
                                            break Label_0150;
                                        }
                                        final String s = categoryName;
                                        final String s2 = "NSKeyValue";
                                        final boolean b2 = s.startsWith(s2);
                                        if (!b2) {
                                            return true;
                                        }
                                        break Label_0150;
                                    }
                                    catch (IllegalStateException ex6) {
                                        throw a(ex6);
                                    }
                                    try {
                                        final String s = categoryName;
                                        final String s2 = "NSKeyValue";
                                        final boolean b2 = s.startsWith(s2);
                                        if (!b2) {
                                            return true;
                                        }
                                    }
                                    catch (IllegalStateException ex7) {
                                        throw a(ex7);
                                    }
                                }
                                final PsiElement locateDefinition = ((OCSymbol<PsiElement>)ocClassSymbol).locateDefinition();
                                try {
                                    if (locateDefinition != null) {
                                        list.add(new OCStructureViewElement(locateDefinition, value));
                                    }
                                }
                                catch (IllegalStateException ex8) {
                                    throw a(ex8);
                                }
                                return true;
                            }));
                            list2 = list;
                            if (list2 == null) {
                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/hierarchy/structureVIew/OCInheritorsAndCategoriesNodeProvider", "provideNodes"));
                            }
                        }
                        catch (IllegalStateException ex3) {
                            throw a(ex3);
                        }
                        return (Collection<OCStructureViewElement>)list2;
                    }
                }
            }
            try {
                emptyList = Collections.emptyList();
                if (emptyList == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/hierarchy/structureVIew/OCInheritorsAndCategoriesNodeProvider", "provideNodes"));
                }
            }
            catch (IllegalStateException ex4) {
                throw a(ex4);
            }
        }
        return emptyList;
    }
    
    @NotNull
    public ActionPresentation getPresentation() {
        ActionPresentationData actionPresentationData;
        try {
            actionPresentationData = new ActionPresentationData("Show Inherited", "Show inherited members and members in other categories", AllIcons.Hierarchy.Supertypes);
            if (actionPresentationData == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/hierarchy/structureVIew/OCInheritorsAndCategoriesNodeProvider", "getPresentation"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (ActionPresentation)actionPresentationData;
    }
    
    @NotNull
    public String getName() {
        String s;
        try {
            s = "INHERITORS_AND_CATEGORIES";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/hierarchy/structureVIew/OCInheritorsAndCategoriesNodeProvider", "getName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
