// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

import com.intellij.openapi.util.Comparing;
import com.intellij.util.containers.ContainerUtil;
import java.util.Collection;
import java.util.ArrayList;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.project.Project;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;

public abstract class OCSymbolImpl<T extends PsiElement> extends OCSymbolBase<T> implements OCSymbol<T>
{
    @NotNull
    protected String myName;
    @NotNull
    private volatile List<String> myAttributes;
    
    public OCSymbolImpl(@Nullable final Project project, @Nullable final VirtualFile virtualFile, final long n, @Nullable final String s, @NotNull final List<String> myAttributes) {
        if (myAttributes == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "attributes", "com/jetbrains/cidr/lang/symbols/OCSymbolImpl", "<init>"));
        }
        super(project, virtualFile, n);
        this.myName = ((s == null) ? "<unnamed>" : s);
        this.myAttributes = myAttributes;
    }
    
    public OCSymbolImpl() {
    }
    
    @NotNull
    @Override
    public List<String> getAttributes() {
        List<String> myAttributes;
        try {
            myAttributes = this.myAttributes;
            if (myAttributes == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolImpl", "getAttributes"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myAttributes;
    }
    
    @Override
    public void addAttributes(final List<String> myAttributes) {
        try {
            if (this.myAttributes.isEmpty()) {
                this.myAttributes = myAttributes;
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final ArrayList myAttributes2 = new ArrayList<Object>(this.myAttributes.size() + myAttributes.size());
        myAttributes2.addAll(this.myAttributes);
        myAttributes2.addAll(myAttributes);
        this.myAttributes = (List<String>)myAttributes2;
    }
    
    @Override
    public void compact() {
        this.myAttributes = (List<String>)ContainerUtil.trimToSize((List)this.myAttributes);
    }
    
    @NotNull
    @Override
    public String getName() {
        String myName;
        try {
            myName = this.myName;
            if (myName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolImpl", "getName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myName;
    }
    
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/symbols/OCSymbolImpl", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/symbols/OCSymbolImpl", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/symbols/OCSymbolImpl", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final OCSymbolImpl ocSymbolImpl = (OCSymbolImpl)o;
        final OCSymbolImpl ocSymbolImpl2 = (OCSymbolImpl)o2;
        try {
            if (ocSymbolImpl.myComplexOffset != ocSymbolImpl2.myComplexOffset) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        try {
            if (!ocSymbolImpl.myName.equals(ocSymbolImpl2.myName)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        try {
            if (!Comparing.equal((Object)ocSymbolImpl.myFile, (Object)ocSymbolImpl2.myFile)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        try {
            if (!Comparing.equal((Object)ocSymbolImpl.myProject, (Object)ocSymbolImpl2.myProject)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex7) {
            throw a(ex7);
        }
        try {
            if (!ocSymbolImpl.myAttributes.equals(ocSymbolImpl2.myAttributes)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex8) {
            throw a(ex8);
        }
        return true;
    }
    
    @Override
    public boolean equals(final Object o) {
        try {
            if (this == o) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0039: {
            try {
                if (o == null) {
                    return false;
                }
                final OCSymbolImpl ocSymbolImpl = this;
                final Class<? extends OCSymbolImpl> clazz = ocSymbolImpl.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
                break Label_0039;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final OCSymbolImpl ocSymbolImpl = this;
                final Class<? extends OCSymbolImpl> clazz = ocSymbolImpl.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        final OCSymbolImpl ocSymbolImpl2 = (OCSymbolImpl)o;
        try {
            if (this.myComplexOffset != ocSymbolImpl2.myComplexOffset) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        try {
            if (!this.myAttributes.equals(ocSymbolImpl2.myAttributes)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        final VirtualFile myFile = this.myFile;
        Label_0131: {
            Label_0118: {
                Label_0111: {
                    try {
                        if (myFile == null) {
                            break Label_0118;
                        }
                        final VirtualFile virtualFile = myFile;
                        final OCSymbolImpl ocSymbolImpl3 = ocSymbolImpl2;
                        final VirtualFile virtualFile2 = ocSymbolImpl3.myFile;
                        final boolean b = virtualFile.equals(virtualFile2);
                        if (!b) {
                            break Label_0111;
                        }
                        break Label_0131;
                    }
                    catch (IllegalArgumentException ex6) {
                        throw a(ex6);
                    }
                    try {
                        final VirtualFile virtualFile = myFile;
                        final OCSymbolImpl ocSymbolImpl3 = ocSymbolImpl2;
                        final VirtualFile virtualFile2 = ocSymbolImpl3.myFile;
                        final boolean b = virtualFile.equals(virtualFile2);
                        if (!b) {
                            return false;
                        }
                        break Label_0131;
                    }
                    catch (IllegalArgumentException ex7) {
                        throw a(ex7);
                    }
                }
                try {
                    if (ocSymbolImpl2.myFile != null) {
                        return false;
                    }
                }
                catch (IllegalArgumentException ex8) {
                    throw a(ex8);
                }
            }
            try {
                if (!this.myName.equals(ocSymbolImpl2.myName)) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex9) {
                throw a(ex9);
            }
        }
        final Project myProject = this.myProject;
        Label_0188: {
            Label_0181: {
                try {
                    if (myProject == null) {
                        break Label_0188;
                    }
                    final Project project = myProject;
                    final OCSymbolImpl ocSymbolImpl4 = ocSymbolImpl2;
                    final Project project2 = ocSymbolImpl4.myProject;
                    final boolean b2 = project.equals(project2);
                    if (!b2) {
                        break Label_0181;
                    }
                    return true;
                }
                catch (IllegalArgumentException ex10) {
                    throw a(ex10);
                }
                try {
                    final Project project = myProject;
                    final OCSymbolImpl ocSymbolImpl4 = ocSymbolImpl2;
                    final Project project2 = ocSymbolImpl4.myProject;
                    final boolean b2 = project.equals(project2);
                    if (!b2) {
                        return false;
                    }
                    return true;
                }
                catch (IllegalArgumentException ex11) {
                    throw a(ex11);
                }
            }
            try {
                if (ocSymbolImpl2.myProject != null) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex12) {
                throw a(ex12);
            }
        }
        return true;
    }
    
    @Override
    public int hashCodeExcludingOffset() {
        int hashCode = 0;
        Label_0022: {
            try {
                if (this.myProject != null) {
                    hashCode = this.myProject.hashCode();
                    break Label_0022;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            hashCode = 0;
        }
        final int n = 31 * hashCode + OCSymbolOffsetUtil.getSubstOffset(this.myComplexOffset);
        int n2 = 0;
        int hashCode2 = 0;
        Label_0062: {
            try {
                n2 = 31 * n;
                if (this.myFile != null) {
                    hashCode2 = this.myFile.hashCode();
                    break Label_0062;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            hashCode2 = 0;
        }
        final int n3 = n2 + hashCode2;
        int n4 = 0;
        int hashCode3 = 0;
        Label_0090: {
            try {
                n4 = 31 * n3;
                if (this.myName != null) {
                    hashCode3 = this.myName.hashCode();
                    break Label_0090;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            hashCode3 = 0;
        }
        final int n5 = n4 + hashCode3;
        int n6;
        try {
            n6 = 31 * n5;
            if (this.myAttributes != null) {
                final int hashCode4 = this.myAttributes.hashCode();
                return n6 + hashCode4;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        final int hashCode4 = 0;
        return n6 + hashCode4;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
