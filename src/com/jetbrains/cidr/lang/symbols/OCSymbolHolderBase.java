// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import javax.swing.Icon;
import com.intellij.navigation.ItemPresentation;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.NavigatablePsiElement;
import com.intellij.psi.impl.FakePsiElement;

public abstract class OCSymbolHolderBase<T extends OCSymbol> extends FakePsiElement implements NavigatablePsiElement
{
    @NotNull
    protected final T mySymbol;
    
    public OCSymbolHolderBase(@NotNull final T mySymbol) {
        if (mySymbol == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/symbols/OCSymbolHolderBase", "<init>"));
        }
        this.mySymbol = mySymbol;
    }
    
    @NotNull
    public String getName() {
        String name;
        try {
            name = this.mySymbol.getName();
            if (name == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolHolderBase", "getName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return name;
    }
    
    @Override
    public ItemPresentation getPresentation() {
        return this.mySymbol.getPresentation();
    }
    
    protected Icon getBaseIcon() {
        return this.mySymbol.getIcon();
    }
    
    @Override
    public Icon getIcon(final boolean b) {
        return this.mySymbol.getIcon();
    }
    
    public void navigate(final boolean b) {
        final int offset = this.mySymbol.getOffset();
        final VirtualFile containingFile = this.mySymbol.getContainingFile();
        Label_0038: {
            try {
                if (containingFile == null) {
                    return;
                }
                final VirtualFile virtualFile = containingFile;
                final boolean b2 = virtualFile.isValid();
                if (b2) {
                    break Label_0038;
                }
                return;
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            try {
                final VirtualFile virtualFile = containingFile;
                final boolean b2 = virtualFile.isValid();
                if (b2) {
                    new OpenFileDescriptor(this.mySymbol.getProject(), containingFile, offset).navigate(true);
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
        }
    }
    
    public boolean canNavigateToSource() {
        final VirtualFile containingFile = this.mySymbol.getContainingFile();
        Label_0028: {
            try {
                if (containingFile == null) {
                    return false;
                }
                final VirtualFile virtualFile = containingFile;
                final boolean b = virtualFile.isValid();
                if (b) {
                    break Label_0028;
                }
                return false;
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            try {
                final VirtualFile virtualFile = containingFile;
                final boolean b = virtualFile.isValid();
                if (b) {
                    return true;
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    public PsiElement getParent() {
        return (PsiElement)this.mySymbol.getContainingPsiFile();
    }
    
    public PsiFile getContainingFile() {
        return this.mySymbol.getContainingPsiFile();
    }
    
    @NotNull
    public Project getProject() {
        Project project;
        try {
            project = this.mySymbol.getProject();
            if (project == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolHolderBase", "getProject"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return project;
    }
    
    @Override
    public int getTextOffset() {
        return this.mySymbol.getOffset();
    }
    
    @Override
    public TextRange getTextRange() {
        return new TextRange(this.mySymbol.getOffset(), this.mySymbol.getOffset());
    }
    
    @Override
    public void delete() throws IncorrectOperationException {
    }
    
    public boolean equals(final Object p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnull          54
        //     4: aload_1        
        //     5: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //     8: aload_0        
        //     9: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //    12: if_acmpne       54
        //    15: goto            22
        //    18: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolHolderBase.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    21: athrow         
        //    22: aload_0        
        //    23: getfield        com/jetbrains/cidr/lang/symbols/OCSymbolHolderBase.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    26: aload_1        
        //    27: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbolHolderBase;
        //    30: getfield        com/jetbrains/cidr/lang/symbols/OCSymbolHolderBase.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    33: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //    36: ifeq            54
        //    39: goto            46
        //    42: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolHolderBase.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    45: athrow         
        //    46: iconst_1       
        //    47: goto            55
        //    50: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolHolderBase.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    53: athrow         
        //    54: iconst_0       
        //    55: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      15     18     22     Lcom/intellij/util/IncorrectOperationException;
        //  4      39     42     46     Lcom/intellij/util/IncorrectOperationException;
        //  22     50     50     54     Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0022:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public int hashCode() {
        return this.mySymbol.hashCode();
    }
    
    @NotNull
    public T getSymbol() {
        OCSymbol mySymbol;
        try {
            mySymbol = this.mySymbol;
            if (mySymbol == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolHolderBase", "getSymbol"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return (T)mySymbol;
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
