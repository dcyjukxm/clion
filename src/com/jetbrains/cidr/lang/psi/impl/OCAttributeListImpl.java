// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.psi.tree.TokenSet;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import java.util.Iterator;
import java.util.ArrayList;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.psi.OCAttribute;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCAttributesList;

public class OCAttributeListImpl extends OCElementBase implements OCAttributesList
{
    public OCAttributeListImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCAttributeListImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Override
    public List<OCAttribute> getAttributes() {
        return this.findChildrenByType(OCElementTypes.ATTRIBUTE);
    }
    
    @Override
    public List<String> getAttributeValues() {
        final ArrayList<String> list = new ArrayList<String>();
        final Iterator<OCAttribute> iterator = this.getAttributes().iterator();
        while (iterator.hasNext()) {
            final String trim = iterator.next().getValue().trim();
            try {
                if (trim.isEmpty()) {
                    continue;
                }
                list.add(trim);
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        return list;
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCAttributeListImpl", "accept"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ocVisitor.visitAttributeList(this);
    }
    
    @Override
    public boolean isMicrosoftAttributes() {
        try {
            if (this.findChildByType(TokenSet.orSet(new TokenSet[] { TokenSet.create(new IElementType[] { OCTokenTypes.__DECLSPEC_KEYWORD }), OCTokenTypes.CALL_CONVENTIONS })) != null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
