// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.asm.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElementVisitor;

public class AsmVisitor extends PsiElementVisitor
{
    public void visitDirective(@NotNull final AsmDirective asmDirective) {
        try {
            if (asmDirective == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/lang/asm/psi/AsmVisitor", "visitDirective"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitPsiElement((PsiElement)asmDirective);
    }
    
    public void visitExpression(@NotNull final AsmExpression asmExpression) {
        try {
            if (asmExpression == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/lang/asm/psi/AsmVisitor", "visitExpression"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitPsiElement((PsiElement)asmExpression);
    }
    
    public void visitImmediate(@NotNull final AsmImmediate asmImmediate) {
        try {
            if (asmImmediate == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/lang/asm/psi/AsmVisitor", "visitImmediate"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitPsiElement((PsiElement)asmImmediate);
    }
    
    public void visitInstruction(@NotNull final AsmInstruction asmInstruction) {
        try {
            if (asmInstruction == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/lang/asm/psi/AsmVisitor", "visitInstruction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitPsiElement((PsiElement)asmInstruction);
    }
    
    public void visitJmpAbsolute(@NotNull final AsmJmpAbsolute asmJmpAbsolute) {
        try {
            if (asmJmpAbsolute == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/lang/asm/psi/AsmVisitor", "visitJmpAbsolute"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitPsiElement((PsiElement)asmJmpAbsolute);
    }
    
    public void visitMemory(@NotNull final AsmMemory asmMemory) {
        try {
            if (asmMemory == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/lang/asm/psi/AsmVisitor", "visitMemory"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitPsiElement((PsiElement)asmMemory);
    }
    
    public void visitNumber(@NotNull final AsmNumber asmNumber) {
        try {
            if (asmNumber == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/lang/asm/psi/AsmVisitor", "visitNumber"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitPsiElement((PsiElement)asmNumber);
    }
    
    public void visitOperand(@NotNull final AsmOperand asmOperand) {
        try {
            if (asmOperand == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/lang/asm/psi/AsmVisitor", "visitOperand"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitPsiElement((PsiElement)asmOperand);
    }
    
    public void visitRegister(@NotNull final AsmRegister asmRegister) {
        try {
            if (asmRegister == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/lang/asm/psi/AsmVisitor", "visitRegister"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitPsiElement((PsiElement)asmRegister);
    }
    
    public void visitSymbol(@NotNull final AsmSymbol asmSymbol) {
        try {
            if (asmSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/lang/asm/psi/AsmVisitor", "visitSymbol"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitPsiElement((PsiElement)asmSymbol);
    }
    
    public void visitSymbolDyld(@NotNull final AsmSymbolDyld asmSymbolDyld) {
        try {
            if (asmSymbolDyld == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/lang/asm/psi/AsmVisitor", "visitSymbolDyld"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitPsiElement((PsiElement)asmSymbolDyld);
    }
    
    public void visitPsiElement(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/jetbrains/cidr/lang/asm/psi/AsmVisitor", "visitPsiElement"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.visitElement(psiElement);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
