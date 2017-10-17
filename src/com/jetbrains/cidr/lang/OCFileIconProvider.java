// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.workspace.OCLanguageKindCalculator;
import icons.CidrLangIcons;
import com.jetbrains.cidr.lang.psi.OCFile;
import javax.swing.Icon;
import com.intellij.openapi.util.Iconable;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.project.DumbAware;
import com.intellij.ide.IconProvider;

public class OCFileIconProvider extends IconProvider implements DumbAware
{
    @Nullable
    public Icon getIcon(@NotNull final PsiElement psiElement, @Iconable.IconFlags final int n) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/OCFileIconProvider", "getIcon"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (!(psiElement instanceof OCFile)) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final OCFile ocFile = (OCFile)psiElement;
        final String name = ocFile.getName();
        try {
            if (OCFileTypeHelpers.isHeaderFile(name)) {
                return CidrLangIcons.FileType_h;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final OCLanguageKind tryFileTypeAndExtension = OCLanguageKindCalculator.tryFileTypeAndExtension(psiElement.getProject(), ocFile.getVirtualFile());
        Label_0156: {
            try {
                if (!(tryFileTypeAndExtension instanceof CLanguageKind)) {
                    return null;
                }
                final int[] array = OCFileIconProvider$1.$SwitchMap$com$jetbrains$cidr$lang$CLanguageKind;
                final OCLanguageKind ocLanguageKind = tryFileTypeAndExtension;
                final CLanguageKind cLanguageKind = (CLanguageKind)ocLanguageKind;
                final int n2 = cLanguageKind.ordinal();
                final int n3 = array[n2];
                switch (n3) {
                    case 1: {
                        break Label_0156;
                        break Label_0156;
                    }
                    case 2: {
                        return CidrLangIcons.FileType_cpp;
                    }
                    case 3:
                    case 4: {
                        return CidrLangIcons.FileType_m;
                    }
                    default: {
                        return null;
                    }
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            try {
                final int[] array = OCFileIconProvider$1.$SwitchMap$com$jetbrains$cidr$lang$CLanguageKind;
                final OCLanguageKind ocLanguageKind = tryFileTypeAndExtension;
                final CLanguageKind cLanguageKind = (CLanguageKind)ocLanguageKind;
                final int n2 = cLanguageKind.ordinal();
                final int n3 = array[n2];
                switch (n3) {
                    case 1: {
                        return CidrLangIcons.FileType_c;
                    }
                    case 2: {
                        break;
                    }
                    case 3:
                    case 4: {
                        return CidrLangIcons.FileType_m;
                    }
                    default: {
                        return null;
                    }
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        return CidrLangIcons.FileType_cpp;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
