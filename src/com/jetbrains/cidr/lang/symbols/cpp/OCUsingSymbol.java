// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.cpp;

import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import java.util.List;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.visitors.OCTypeSubstitution;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.OCSymbolReference;
import com.jetbrains.cidr.lang.symbols.OCSymbolWithSubstitution;
import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.symbols.OCSymbolImpl;

public class OCUsingSymbol extends OCSymbolImpl<OCElement> implements OCSymbolWithParent<OCElement, OCSymbolWithQualifiedName>, OCSymbolWithSubstitution
{
    private OCSymbolReference mySymbolReference;
    private OCSymbolKind myKind;
    private TextRange myScope;
    @Nullable
    private OCVisibility myVisibility;
    @Nullable
    private OCSymbolWithQualifiedName myParent;
    @NotNull
    private OCTypeSubstitution mySubstitution;
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/symbols/cpp/OCUsingSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/symbols/cpp/OCUsingSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/symbols/cpp/OCUsingSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        try {
            if (!super.deepEqualStep(comparator, o, o2)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        final OCUsingSymbol ocUsingSymbol = (OCUsingSymbol)o;
        final OCUsingSymbol ocUsingSymbol2 = (OCUsingSymbol)o2;
        try {
            if (ocUsingSymbol.myKind != ocUsingSymbol2.myKind) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw b(ex5);
        }
        final TextRange myScope = ocUsingSymbol.myScope;
        Label_0232: {
            Label_0218: {
                Label_0211: {
                    try {
                        if (myScope == null) {
                            break Label_0218;
                        }
                        final TextRange textRange = myScope;
                        final OCUsingSymbol ocUsingSymbol3 = ocUsingSymbol2;
                        final TextRange textRange2 = ocUsingSymbol3.myScope;
                        final boolean b = textRange.equals((Object)textRange2);
                        if (!b) {
                            break Label_0211;
                        }
                        break Label_0232;
                    }
                    catch (IllegalArgumentException ex6) {
                        throw b(ex6);
                    }
                    try {
                        final TextRange textRange = myScope;
                        final OCUsingSymbol ocUsingSymbol3 = ocUsingSymbol2;
                        final TextRange textRange2 = ocUsingSymbol3.myScope;
                        final boolean b = textRange.equals((Object)textRange2);
                        if (!b) {
                            return false;
                        }
                        break Label_0232;
                    }
                    catch (IllegalArgumentException ex7) {
                        throw b(ex7);
                    }
                }
                try {
                    if (ocUsingSymbol2.myScope != null) {
                        return false;
                    }
                }
                catch (IllegalArgumentException ex8) {
                    throw b(ex8);
                }
            }
            try {
                if (ocUsingSymbol.myVisibility != ocUsingSymbol2.myVisibility) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex9) {
                throw b(ex9);
            }
        }
        try {
            if (!comparator.equalObjects(ocUsingSymbol.myParent, (DeepEqual.Equality<Object>)ocUsingSymbol2.myParent)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex10) {
            throw b(ex10);
        }
        try {
            if (!comparator.equalObjects(ocUsingSymbol.mySubstitution, (DeepEqual.Equality<Object>)ocUsingSymbol2.mySubstitution)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex11) {
            throw b(ex11);
        }
        try {
            if (!comparator.equalObjects((DeepEqual.Equality<Object>)ocUsingSymbol.mySymbolReference, (DeepEqual.Equality<Object>)ocUsingSymbol2.mySymbolReference)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex12) {
            throw b(ex12);
        }
        return true;
    }
    
    public OCUsingSymbol() {
        this.mySubstitution = OCTypeSubstitution.ID;
    }
    
    @Nullable
    @Override
    public OCSymbolWithQualifiedName getParent() {
        return this.myParent;
    }
    
    @NotNull
    @Override
    public String getNameWithParent() {
        String myName = null;
        Label_0085: {
            String s4 = null;
            Label_0050: {
                try {
                    if (this.myParent == null) {
                        break Label_0085;
                    }
                    final StringBuilder sb = new StringBuilder();
                    final OCUsingSymbol ocUsingSymbol = this;
                    final OCSymbolWithQualifiedName ocSymbolWithQualifiedName = ocUsingSymbol.myParent;
                    final String s = ocSymbolWithQualifiedName.getName();
                    final StringBuilder sb2 = sb.append(s);
                    final String s2 = "::";
                    final StringBuilder sb3 = sb2.append(s2);
                    final OCUsingSymbol ocUsingSymbol2 = this;
                    final String s3 = ocUsingSymbol2.myName;
                    final StringBuilder sb4 = sb3.append(s3);
                    s4 = sb4.toString();
                    if (s4 == null) {
                        break Label_0050;
                    }
                    return s4;
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                try {
                    final StringBuilder sb = new StringBuilder();
                    final OCUsingSymbol ocUsingSymbol = this;
                    final OCSymbolWithQualifiedName ocSymbolWithQualifiedName = ocUsingSymbol.myParent;
                    final String s = ocSymbolWithQualifiedName.getName();
                    final StringBuilder sb2 = sb.append(s);
                    final String s2 = "::";
                    final StringBuilder sb3 = sb2.append(s2);
                    final OCUsingSymbol ocUsingSymbol2 = this;
                    final String s3 = ocUsingSymbol2.myName;
                    final StringBuilder sb4 = sb3.append(s3);
                    s4 = sb4.toString();
                    if (s4 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCUsingSymbol", "getNameWithParent"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
            }
            return s4;
            try {
                myName = this.myName;
                if (myName == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCUsingSymbol", "getNameWithParent"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
        }
        return myName;
    }
    
    public OCUsingSymbol(@Nullable final Project project, @Nullable final VirtualFile virtualFile, final long n, @Nullable final OCSymbolWithQualifiedName myParent, final OCSymbolReference mySymbolReference, final OCSymbolKind myKind, final OCVisibility myVisibility, @NotNull final List<String> list, final TextRange myScope) {
        if (list == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "attributes", "com/jetbrains/cidr/lang/symbols/cpp/OCUsingSymbol", "<init>"));
        }
        super(project, virtualFile, n, mySymbolReference.getQualifiedName().getName(), list);
        this.mySubstitution = OCTypeSubstitution.ID;
        this.mySymbolReference = mySymbolReference;
        this.myKind = myKind;
        this.myScope = myScope;
        this.myVisibility = myVisibility;
        this.myParent = myParent;
    }
    
    public OCUsingSymbol(@Nullable final Project project, @Nullable final VirtualFile virtualFile, final int n, @Nullable final OCSymbolWithQualifiedName myParent, final String s, final OCSymbolReference mySymbolReference, final OCSymbolKind myKind, final OCVisibility myVisibility, @NotNull final List<String> list, final TextRange myScope) {
        if (list == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "attributes", "com/jetbrains/cidr/lang/symbols/cpp/OCUsingSymbol", "<init>"));
        }
        super(project, virtualFile, n, s, list);
        this.mySubstitution = OCTypeSubstitution.ID;
        this.mySymbolReference = mySymbolReference;
        this.myKind = myKind;
        this.myScope = myScope;
        this.myVisibility = myVisibility;
        this.myParent = myParent;
    }
    
    public OCUsingSymbol(final OCUsingSymbol ocUsingSymbol, final OCTypeSubstitution ocTypeSubstitution, @NotNull final OCResolveContext ocResolveContext) {
        if (ocResolveContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/cpp/OCUsingSymbol", "<init>"));
        }
        super(ocUsingSymbol.getProject(), ocUsingSymbol.getContainingFile(), ocUsingSymbol.getOffset(), ocUsingSymbol.getName(), ocUsingSymbol.getAttributes());
        this.mySubstitution = OCTypeSubstitution.ID;
        this.mySymbolReference = ocUsingSymbol.getSymbolReference();
        this.myKind = ocUsingSymbol.getKind();
        this.myScope = ocUsingSymbol.getScope();
        this.mySubstitution = OCTypeSubstitution.compose(ocUsingSymbol.mySubstitution, ocTypeSubstitution, ocResolveContext);
        this.myVisibility = ocUsingSymbol.getVisibility();
        this.myParent = ocUsingSymbol.getParent();
    }
    
    @Nullable
    public OCVisibility getVisibility() {
        return this.myVisibility;
    }
    
    public OCSymbolReference getSymbolReference() {
        return this.mySymbolReference;
    }
    
    @Override
    public TextRange getScope() {
        return this.myScope;
    }
    
    @NotNull
    @Override
    public OCSymbolKind getKind() {
        OCSymbolKind myKind;
        try {
            myKind = this.myKind;
            if (myKind == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCUsingSymbol", "getKind"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return myKind;
    }
    
    @NotNull
    @Override
    public OCTypeSubstitution getSubstitution() {
        OCTypeSubstitution mySubstitution;
        try {
            mySubstitution = this.mySubstitution;
            if (mySubstitution == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCUsingSymbol", "getSubstitution"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return mySubstitution;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
