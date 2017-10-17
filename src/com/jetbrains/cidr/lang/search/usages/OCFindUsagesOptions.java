// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search.usages;

import com.intellij.openapi.components.ServiceManager;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.State;
import org.jdom.Element;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.find.findUsages.FindUsagesOptions;

@State(name = "OCFindUsagesOptions", storages = { @Storage("$WORKSPACE_FILE$") })
public class OCFindUsagesOptions extends FindUsagesOptions implements PersistentStateComponent<Element>
{
    public boolean isSearchForIvars;
    public boolean isSearchForProperties;
    public boolean isSearchForDerivedClasses;
    
    public OCFindUsagesOptions(@NotNull final Project project) {
        if (project == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/search/usages/OCFindUsagesOptions", "<init>"));
        }
        super(project);
        this.isSearchForIvars = false;
        this.isSearchForProperties = true;
        this.isSearchForDerivedClasses = false;
        this.isUsages = true;
    }
    
    public static OCFindUsagesOptions getInstance(@NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/search/usages/OCFindUsagesOptions", "getInstance"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (OCFindUsagesOptions)ServiceManager.getService(project, (Class)OCFindUsagesOptions.class);
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
                final OCFindUsagesOptions ocFindUsagesOptions = this;
                final Class<? extends OCFindUsagesOptions> clazz = ocFindUsagesOptions.getClass();
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
                final OCFindUsagesOptions ocFindUsagesOptions = this;
                final Class<? extends OCFindUsagesOptions> clazz = ocFindUsagesOptions.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                if (!super.equals(o)) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        final OCFindUsagesOptions ocFindUsagesOptions2 = (OCFindUsagesOptions)o;
        try {
            if (this.isSearchForDerivedClasses != ocFindUsagesOptions2.isSearchForDerivedClasses) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        try {
            if (this.isSearchForIvars != ocFindUsagesOptions2.isSearchForIvars) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        try {
            if (this.isSearchForProperties != ocFindUsagesOptions2.isSearchForProperties) {
                return false;
            }
        }
        catch (IllegalArgumentException ex7) {
            throw a(ex7);
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        final int hashCode = super.hashCode();
        int n = 0;
        int n2 = 0;
        Label_0025: {
            try {
                n = 31 * hashCode;
                if (this.isSearchForIvars) {
                    n2 = 1;
                    break Label_0025;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            n2 = 0;
        }
        final int n3 = n + n2;
        int n4 = 0;
        int n5 = 0;
        Label_0047: {
            try {
                n4 = 31 * n3;
                if (this.isSearchForProperties) {
                    n5 = 1;
                    break Label_0047;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            n5 = 0;
        }
        final int n6 = n4 + n5;
        int n7;
        try {
            n7 = 31 * n6;
            if (this.isSearchForDerivedClasses) {
                final int n8 = 1;
                return n7 + n8;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final int n8 = 0;
        return n7 + n8;
    }
    
    public Element getState() {
        final Element element = new Element("state");
        element.setAttribute("text", Boolean.toString(this.isSearchForTextOccurrences));
        element.setAttribute("ivars", Boolean.toString(this.isSearchForIvars));
        element.setAttribute("properties", Boolean.toString(this.isSearchForProperties));
        element.setAttribute("derivedClasses", Boolean.toString(this.isSearchForDerivedClasses));
        return element;
    }
    
    public void loadState(final Element element) {
        this.isSearchForTextOccurrences = Boolean.parseBoolean(element.getAttributeValue("text"));
        this.isSearchForIvars = Boolean.parseBoolean(element.getAttributeValue("ivars"));
        this.isSearchForProperties = Boolean.parseBoolean(element.getAttributeValue("properties"));
        this.isSearchForDerivedClasses = Boolean.parseBoolean(element.getAttributeValue("derivedClasses"));
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
