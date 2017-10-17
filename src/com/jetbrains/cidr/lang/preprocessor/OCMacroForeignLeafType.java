// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.preprocessor;

import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;
import com.intellij.openapi.util.TextRange;
import com.intellij.lang.ForeignLeafType;

public class OCMacroForeignLeafType extends ForeignLeafType
{
    private String myMacroName;
    private int myMacroArgumentIndex;
    private int myOffsetInTopSubstitution;
    private TextRange myRangeInMacroArgument;
    
    public OCMacroForeignLeafType(final IElementType elementType, final CharSequence charSequence, final String myMacroName, final int myMacroArgumentIndex, final TextRange myRangeInMacroArgument, final int myOffsetInTopSubstitution) {
        super(elementType, charSequence);
        this.myMacroName = myMacroName;
        this.myMacroArgumentIndex = myMacroArgumentIndex;
        this.myRangeInMacroArgument = myRangeInMacroArgument;
        this.myOffsetInTopSubstitution = myOffsetInTopSubstitution;
    }
    
    public String getMacroName() {
        return this.myMacroName;
    }
    
    public void plungeIntoSubstitution(final int myOffsetInTopSubstitution) {
        this.myOffsetInTopSubstitution = myOffsetInTopSubstitution;
    }
    
    public int getOffsetInTopSubstitution() {
        return this.myOffsetInTopSubstitution;
    }
    
    public TextRange getRangeInMacroArgument() {
        return this.myRangeInMacroArgument;
    }
    
    public int getMacroArgumentIndex() {
        return this.myMacroArgumentIndex;
    }
    
    public boolean equals(final Object o) {
        try {
            if (this == o) {
                return true;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        Label_0039: {
            try {
                if (o == null) {
                    return false;
                }
                final OCMacroForeignLeafType ocMacroForeignLeafType = this;
                final Class<? extends OCMacroForeignLeafType> clazz = ocMacroForeignLeafType.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
                break Label_0039;
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            try {
                final OCMacroForeignLeafType ocMacroForeignLeafType = this;
                final Class<? extends OCMacroForeignLeafType> clazz = ocMacroForeignLeafType.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
        }
        final OCMacroForeignLeafType ocMacroForeignLeafType2 = (OCMacroForeignLeafType)o;
        try {
            if (this.myMacroArgumentIndex != ocMacroForeignLeafType2.myMacroArgumentIndex) {
                return false;
            }
        }
        catch (IllegalStateException ex4) {
            throw a(ex4);
        }
        try {
            if (this.myOffsetInTopSubstitution != ocMacroForeignLeafType2.myOffsetInTopSubstitution) {
                return false;
            }
        }
        catch (IllegalStateException ex5) {
            throw a(ex5);
        }
        Label_0161: {
            Label_0154: {
                Label_0126: {
                    Label_0113: {
                        Label_0106: {
                            try {
                                if (this.myMacroName == null) {
                                    break Label_0113;
                                }
                                final OCMacroForeignLeafType ocMacroForeignLeafType3 = this;
                                final String s = ocMacroForeignLeafType3.myMacroName;
                                final OCMacroForeignLeafType ocMacroForeignLeafType4 = ocMacroForeignLeafType2;
                                final String s2 = ocMacroForeignLeafType4.myMacroName;
                                final boolean b = s.equals(s2);
                                if (!b) {
                                    break Label_0106;
                                }
                                break Label_0126;
                            }
                            catch (IllegalStateException ex6) {
                                throw a(ex6);
                            }
                            try {
                                final OCMacroForeignLeafType ocMacroForeignLeafType3 = this;
                                final String s = ocMacroForeignLeafType3.myMacroName;
                                final OCMacroForeignLeafType ocMacroForeignLeafType4 = ocMacroForeignLeafType2;
                                final String s2 = ocMacroForeignLeafType4.myMacroName;
                                final boolean b = s.equals(s2);
                                if (!b) {
                                    return false;
                                }
                                break Label_0126;
                            }
                            catch (IllegalStateException ex7) {
                                throw a(ex7);
                            }
                        }
                        try {
                            if (ocMacroForeignLeafType2.myMacroName != null) {
                                return false;
                            }
                        }
                        catch (IllegalStateException ex8) {
                            throw a(ex8);
                        }
                    }
                    try {
                        if (this.myRangeInMacroArgument == null) {
                            break Label_0161;
                        }
                        final OCMacroForeignLeafType ocMacroForeignLeafType5 = this;
                        final TextRange textRange = ocMacroForeignLeafType5.myRangeInMacroArgument;
                        final OCMacroForeignLeafType ocMacroForeignLeafType6 = ocMacroForeignLeafType2;
                        final TextRange textRange2 = ocMacroForeignLeafType6.myRangeInMacroArgument;
                        final boolean b2 = textRange.equals((Object)textRange2);
                        if (!b2) {
                            break Label_0154;
                        }
                        return true;
                    }
                    catch (IllegalStateException ex9) {
                        throw a(ex9);
                    }
                }
                try {
                    final OCMacroForeignLeafType ocMacroForeignLeafType5 = this;
                    final TextRange textRange = ocMacroForeignLeafType5.myRangeInMacroArgument;
                    final OCMacroForeignLeafType ocMacroForeignLeafType6 = ocMacroForeignLeafType2;
                    final TextRange textRange2 = ocMacroForeignLeafType6.myRangeInMacroArgument;
                    final boolean b2 = textRange.equals((Object)textRange2);
                    if (!b2) {
                        return false;
                    }
                    return true;
                }
                catch (IllegalStateException ex10) {
                    throw a(ex10);
                }
            }
            try {
                if (ocMacroForeignLeafType2.myRangeInMacroArgument != null) {
                    return false;
                }
            }
            catch (IllegalStateException ex11) {
                throw a(ex11);
            }
        }
        return true;
    }
    
    public int hashCode() {
        int hashCode = 0;
        Label_0022: {
            try {
                if (this.myMacroName != null) {
                    hashCode = this.myMacroName.hashCode();
                    break Label_0022;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            hashCode = 0;
        }
        final int n = 31 * hashCode + this.myMacroArgumentIndex;
        int n2;
        try {
            n2 = 31 * n;
            if (this.myRangeInMacroArgument != null) {
                final int hashCode2 = this.myRangeInMacroArgument.hashCode();
                return n2 + hashCode2;
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        final int hashCode2 = 0;
        return n2 + hashCode2;
    }
    
    @NotNull
    @Override
    public ASTNode createLeafNode(final CharSequence charSequence) {
        OCMacroForeignLeafElement ocMacroForeignLeafElement;
        try {
            ocMacroForeignLeafElement = new OCMacroForeignLeafElement(this, this.getValue(), this.myMacroName, this.myMacroArgumentIndex, this.myRangeInMacroArgument, this.myOffsetInTopSubstitution);
            if (ocMacroForeignLeafElement == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafType", "createLeafNode"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return (ASTNode)ocMacroForeignLeafElement;
    }
    
    @Override
    public String toString() {
        final StringBuilder append = new StringBuilder("{").append(this.getDelegate());
        try {
            append.append(", ").append(this.myMacroName);
            append.append(", ").append(this.myOffsetInTopSubstitution);
            if (this.myMacroArgumentIndex >= 0) {
                append.append(", ").append(this.myMacroArgumentIndex);
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (this.myRangeInMacroArgument != null) {
                append.append(" -> ").append(this.myRangeInMacroArgument);
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return append.append("}").toString();
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
