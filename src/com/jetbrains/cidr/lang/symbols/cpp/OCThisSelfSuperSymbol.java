// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.cpp;

import com.jetbrains.cidr.lang.psi.OCElement;
import java.util.List;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import java.security.InvalidParameterException;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import java.util.Collections;
import com.jetbrains.cidr.lang.types.OCType;

public class OCThisSelfSuperSymbol extends OCDeclaratorSymbol
{
    private Kind myKind;
    
    public OCThisSelfSuperSymbol() {
    }
    
    public OCThisSelfSuperSymbol(final Kind myKind, final OCType ocType) {
        super(null, null, -1, null, a(myKind), Collections.emptyList(), ocType, OCSymbolKind.PARAMETER);
        this.myKind = myKind;
    }
    
    private static String a(final Kind kind) {
        try {
            switch (kind) {
                case THIS: {
                    return "this";
                }
                case SELF: {
                    break;
                }
                case SUPER: {
                    return "super";
                }
                case _CMD: {
                    return "_cmd";
                }
                default: {
                    throw new InvalidParameterException("kind");
                }
            }
        }
        catch (InvalidParameterException ex) {
            throw b(ex);
        }
        return "self";
    }
    
    private static Kind a(final String s) {
        try {
            if ("this".equals(s)) {
                return Kind.THIS;
            }
        }
        catch (InvalidParameterException ex) {
            throw b(ex);
        }
        try {
            if ("self".equals(s)) {
                return Kind.SELF;
            }
        }
        catch (InvalidParameterException ex2) {
            throw b(ex2);
        }
        try {
            if ("super".equals(s)) {
                return Kind.SUPER;
            }
        }
        catch (InvalidParameterException ex3) {
            throw b(ex3);
        }
        try {
            if ("_cmd".equals(s)) {
                return Kind._CMD;
            }
        }
        catch (InvalidParameterException ex4) {
            throw b(ex4);
        }
        return null;
    }
    
    public Kind getSelfSuperThisKind() {
        return this.myKind;
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/symbols/cpp/OCThisSelfSuperSymbol", "deepEqualStep"));
            }
        }
        catch (InvalidParameterException ex) {
            throw b(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/symbols/cpp/OCThisSelfSuperSymbol", "deepEqualStep"));
            }
        }
        catch (InvalidParameterException ex2) {
            throw b(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/symbols/cpp/OCThisSelfSuperSymbol", "deepEqualStep"));
            }
        }
        catch (InvalidParameterException ex3) {
            throw b(ex3);
        }
        try {
            if (!super.deepEqualStep(comparator, o, o2)) {
                return false;
            }
        }
        catch (InvalidParameterException ex4) {
            throw b(ex4);
        }
        try {
            if (((OCThisSelfSuperSymbol)o).myKind == ((OCThisSelfSuperSymbol)o2).myKind) {
                return true;
            }
        }
        catch (InvalidParameterException ex5) {
            throw b(ex5);
        }
        return false;
    }
    
    public static OCSymbol tryResolveThisSelfSuper(final String s, @NotNull final PsiElement psiElement, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "elementContext", "com/jetbrains/cidr/lang/symbols/cpp/OCThisSelfSuperSymbol", "tryResolveThisSelfSuper"));
            }
        }
        catch (InvalidParameterException ex) {
            throw b(ex);
        }
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/cpp/OCThisSelfSuperSymbol", "tryResolveThisSelfSuper"));
            }
        }
        catch (InvalidParameterException ex2) {
            throw b(ex2);
        }
        final Kind a = a(s);
        try {
            if (a == null) {
                return null;
            }
        }
        catch (InvalidParameterException ex3) {
            throw b(ex3);
        }
        final OCSymbol[] array = { null };
        processThisSelfSuperInContext(s, psiElement, ocResolveContext, (Processor<OCSymbol>)(ocSymbol -> {
            try {
                if (((OCThisSelfSuperSymbol)ocSymbol).getSelfSuperThisKind() == a) {
                    array[0] = ocSymbol;
                    return false;
                }
            }
            catch (InvalidParameterException ex) {
                throw b(ex);
            }
            return true;
        }), true);
        return array[0];
    }
    
    public static void processThisSelfSuperInContext(final OCReferenceElement ocReferenceElement, final Processor<OCSymbol> processor, final boolean b) {
        processThisSelfSuperInContext(ocReferenceElement.getCanonicalText(), (PsiElement)ocReferenceElement, new OCResolveContext((PsiElement)ocReferenceElement), processor, b);
    }
    
    public static void processThisSelfSuperInContext(final String s, @NotNull final PsiElement psiElement, @NotNull final OCResolveContext ocResolveContext, @NotNull final Processor<OCSymbol> processor, final boolean b) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "elementContext", "com/jetbrains/cidr/lang/symbols/cpp/OCThisSelfSuperSymbol", "processThisSelfSuperInContext"));
            }
        }
        catch (InvalidParameterException ex) {
            throw b(ex);
        }
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/cpp/OCThisSelfSuperSymbol", "processThisSelfSuperInContext"));
            }
        }
        catch (InvalidParameterException ex2) {
            throw b(ex2);
        }
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/symbols/cpp/OCThisSelfSuperSymbol", "processThisSelfSuperInContext"));
            }
        }
        catch (InvalidParameterException ex3) {
            throw b(ex3);
        }
        final OCFile ocFile = (OCFile)psiElement.getContainingFile();
        final OCMethod ocMethod = (OCMethod)PsiTreeUtil.getContextOfType(psiElement, (Class)OCMethod.class, false);
        Label_0366: {
            Label_0172: {
                try {
                    if (ocMethod == null) {
                        break Label_0366;
                    }
                    final boolean b2 = b;
                    if (b2) {
                        break Label_0172;
                    }
                    break Label_0172;
                }
                catch (InvalidParameterException ex4) {
                    throw b(ex4);
                }
                try {
                    final boolean b2 = b;
                    if (b2) {
                        if (OCElementUtil.getSelfSuperToken(s, psiElement, ocResolveContext) == null) {
                            break Label_0366;
                        }
                    }
                }
                catch (InvalidParameterException ex5) {
                    throw b(ex5);
                }
            }
            final OCType resolvedFromText = OCReferenceType.resolvedFromText(ocMethod.getContainingClass().getName(), (PsiFile)ocFile, false);
            OCType ocType;
            if (ocMethod.isInstanceMethod()) {
                ocType = OCPointerType.to(resolvedFromText);
            }
            else {
                ocType = OCReferenceType.resolvedFromText("Class", (PsiFile)ocFile, false);
            }
            processor.process((Object)new OCThisSelfSuperSymbol(Kind.SELF, ocType));
            processor.process((Object)new OCThisSelfSuperSymbol(Kind._CMD, OCReferenceType.fromText("SEL")));
            if (resolvedFromText instanceof OCObjectType) {
                final OCObjectType superType = ((OCObjectType)resolvedFromText).getSuperType();
                OCType ocType2 = null;
                Label_0347: {
                    Label_0337: {
                        try {
                            if (superType == null) {
                                break Label_0366;
                            }
                            if (!ocMethod.isInstanceMethod()) {
                                break Label_0337;
                            }
                        }
                        catch (InvalidParameterException ex6) {
                            throw b(ex6);
                        }
                        ocType2 = OCPointerType.to(superType);
                        break Label_0347;
                    }
                    ocType2 = OCReferenceType.resolvedFromText("Class", (PsiFile)ocFile, false);
                }
                processor.process((Object)new OCThisSelfSuperSymbol(Kind.SUPER, ocType2));
            }
        }
        for (OCDeclaration ocDeclaration = (OCDeclaration)PsiTreeUtil.getContextOfType(psiElement, (Class)OCDeclaration.class, false); ocDeclaration != null; ocDeclaration = (OCDeclaration)PsiTreeUtil.getContextOfType((PsiElement)ocDeclaration, new Class[] { OCDeclaration.class })) {
            final List<OCDeclarator> declarators = ocDeclaration.getDeclarators();
            if (declarators.size() == 1) {
                final OCSymbol symbol = declarators.get(0).getSymbol();
                if (symbol instanceof OCFunctionSymbol) {
                    final OCSymbolWithQualifiedName<OCElement> resolvedOwner = ((OCFunctionSymbol)symbol).getResolvedOwner(ocResolveContext, true);
                    if (resolvedOwner instanceof OCStructSymbol) {
                        OCType ocType3 = resolvedOwner.getType();
                        if (((OCFunctionSymbol)symbol).isConst()) {
                            ocType3 = ocType3.cloneWithConstModifier(psiElement.getProject());
                        }
                        processor.process((Object)new OCThisSelfSuperSymbol(Kind.THIS, OCPointerType.to(ocType3)));
                        return;
                    }
                }
            }
        }
    }
    
    public boolean isKeywordLike() {
        try {
            if (this.getSelfSuperThisKind() != Kind._CMD) {
                return true;
            }
        }
        catch (InvalidParameterException ex) {
            throw b(ex);
        }
        return false;
    }
    
    private static InvalidParameterException b(final InvalidParameterException ex) {
        return ex;
    }
    
    public enum Kind
    {
        THIS, 
        SELF, 
        SUPER, 
        _CMD;
    }
}
