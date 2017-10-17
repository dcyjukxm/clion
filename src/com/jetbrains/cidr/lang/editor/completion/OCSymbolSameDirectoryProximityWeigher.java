// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.UserDataHolder;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.util.NullableLazyKey;
import com.intellij.psi.util.ProximityLocation;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.psi.Weigher;

public class OCSymbolSameDirectoryProximityWeigher extends Weigher<OCSymbol, ProximityLocation>
{
    private static final NullableLazyKey<VirtualFile, ProximityLocation> PLACE_DIRECTORY;
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public Comparable weigh(@NotNull final OCSymbol ocSymbol, @NotNull final ProximityLocation proximityLocation) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/editor/completion/OCSymbolSameDirectoryProximityWeigher", "weigh"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (proximityLocation == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "location", "com/jetbrains/cidr/lang/editor/completion/OCSymbolSameDirectoryProximityWeigher", "weigh"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (proximityLocation.getPosition() == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final VirtualFile virtualFile = (VirtualFile)OCSymbolSameDirectoryProximityWeigher.PLACE_DIRECTORY.getValue((UserDataHolder)proximityLocation);
        final VirtualFile containingFile = ocSymbol.getContainingFile();
        Label_0136: {
            try {
                if (virtualFile == null) {
                    break Label_0136;
                }
                final VirtualFile virtualFile2 = containingFile;
                if (virtualFile2 != null) {
                    break Label_0136;
                }
                break Label_0136;
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            try {
                final VirtualFile virtualFile2 = containingFile;
                if (virtualFile2 != null) {
                    return virtualFile.equals(containingFile.getParent());
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        return false;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCSymbolSameDirectoryProximityWeigher.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
        PLACE_DIRECTORY = NullableLazyKey.create("placeDirectory", proximityLocation -> {
            final PsiElement position = proximityLocation.getPosition();
            Label_0022: {
                try {
                    if (OCSymbolSameDirectoryProximityWeigher.$assertionsDisabled) {
                        return position.getContainingFile().getOriginalFile().getVirtualFile().getParent();
                    }
                    final PsiElement psiElement = position;
                    if (psiElement == null) {
                        break Label_0022;
                    }
                    return position.getContainingFile().getOriginalFile().getVirtualFile().getParent();
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final PsiElement psiElement = position;
                    if (psiElement == null) {
                        throw new AssertionError();
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return position.getContainingFile().getOriginalFile().getVirtualFile().getParent();
        });
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
