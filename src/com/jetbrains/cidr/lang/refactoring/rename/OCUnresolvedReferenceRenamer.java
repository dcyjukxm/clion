// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.rename;

import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.jetbrains.cidr.lang.util.OCExpectedTypeUtil;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.OCSymbolGroupContext;
import java.util.Iterator;
import java.util.LinkedHashSet;
import com.jetbrains.cidr.lang.resolve.OCResolveUtil;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import java.util.Set;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;
import java.util.HashSet;
import com.intellij.openapi.util.Key;
import com.intellij.psi.search.LocalSearchScope;
import com.intellij.util.PairProcessor;
import com.intellij.refactoring.util.TextOccurrencesUtil;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.Pair;
import com.intellij.psi.PsiElementVisitor;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.intellij.psi.PsiElement;
import java.util.List;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;
import java.util.ArrayList;
import java.util.Collection;
import com.intellij.psi.search.SearchScope;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiFile;
import com.intellij.refactoring.rename.inplace.VariableInplaceRenamer;

public class OCUnresolvedReferenceRenamer extends VariableInplaceRenamer
{
    private PsiFile myFile;
    private PsiReference myReference;
    
    public OCUnresolvedReferenceRenamer(@Nullable final PsiNamedElement psiNamedElement, @NotNull final Editor editor, @NotNull final PsiFile myFile, @NotNull final Project project, @NotNull final PsiReference myReference, @Nullable final String myInitialName, @Nullable final String s) {
        if (editor == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/refactoring/rename/OCUnresolvedReferenceRenamer", "<init>"));
        }
        if (myFile == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/refactoring/rename/OCUnresolvedReferenceRenamer", "<init>"));
        }
        if (project == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/refactoring/rename/OCUnresolvedReferenceRenamer", "<init>"));
        }
        if (myReference == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "reference", "com/jetbrains/cidr/lang/refactoring/rename/OCUnresolvedReferenceRenamer", "<init>"));
        }
        super(psiNamedElement, editor, project, myInitialName, s);
        this.myFile = myFile;
        this.myReference = myReference;
        this.myInitialName = myInitialName;
    }
    
    @Override
    protected Collection<PsiReference> collectRefs(final SearchScope searchScope) {
        final ArrayList<PsiReference> list = new ArrayList<PsiReference>();
        this.myFile.accept((PsiElementVisitor)new OCRecursiveVisitor() {
            final /* synthetic */ String val$referenceName = OCUnresolvedReferenceRenamer.this.myReference.getCanonicalText();
            
            @Override
            public void visitElement(final PsiElement psiElement) {
                super.visitElement(psiElement);
                for (final PsiReference psiReference : psiElement.getReferences()) {
                    if (psiReference.getCanonicalText().equals(this.val$referenceName) && psiReference.resolve() == null && !(psiReference.getElement() instanceof OCDeclarator)) {
                        list.add(psiReference);
                    }
                }
            }
        });
        return list;
    }
    
    @Override
    protected void collectAdditionalElementsToRename(final List<Pair<PsiElement, TextRange>> list) {
        TextOccurrencesUtil.processUsagesInStringsAndComments((PsiElement)this.myFile, this.myReference.getCanonicalText(), true, (PairProcessor<PsiElement, TextRange>)((psiElement, textRange) -> {
            try {
                if (psiElement.getContainingFile() == this.myFile) {
                    list.add(Pair.create((Object)psiElement, (Object)textRange));
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            return true;
        }));
    }
    
    @Override
    public boolean performInplaceRename() {
        this.myNameSuggestions = null;
        final Collection<PsiReference> collectRefs = this.collectRefs((SearchScope)new LocalSearchScope((PsiElement)this.myFile));
        this.myEditor.putUserData((Key)OCUnresolvedReferenceRenamer.INPLACE_RENAMER, (Object)this);
        OCUnresolvedReferenceRenamer.ourRenamersStack.push((Object)this);
        final Iterator<PsiReference> iterator = collectRefs.iterator();
        while (iterator.hasNext()) {
            final PsiElement element = iterator.next().getElement();
            final HashSet<String> set = new HashSet<String>();
            if (element instanceof OCQualifiedExpression) {
                ((OCQualifiedExpression)element).processTargets(null, this.a((OCExpression)element, ((OCQualifiedExpression)element).getQualifier().getResolvedType(), ((OCQualifiedExpression)element).getSymbolContext(), set), false, OCTokenTypes.DEREF, true, false, null);
            }
            else {
                if (!(element instanceof OCReferenceElement) || !(element.getParent() instanceof OCReferenceExpression)) {
                    continue;
                }
                final OCSymbolGroupContext symbolContext = ((OCReferenceElement)element).getSymbolContext();
                try {
                    if (symbolContext != null) {
                        OCResolveUtil.processLocalAndMemberSymbols(null, element, this.a((OCExpression)element.getParent(), null, symbolContext, set));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
            }
            try {
                if (this.myNameSuggestions == null) {
                    this.myNameSuggestions = new LinkedHashSet<String>(set);
                    continue;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            this.myNameSuggestions.retainAll(set);
        }
        final ArrayList<Pair<PsiElement, TextRange>> list = new ArrayList<Pair<PsiElement, TextRange>>();
        this.collectAdditionalElementsToRename(list);
        return this.buildTemplateAndStart(collectRefs, list, (PsiElement)this.myFile, this.myFile);
    }
    
    private Processor<OCSymbol> a(final OCExpression ocExpression, @Nullable final OCType ocType, @Nullable final OCSymbolGroupContext ocSymbolGroupContext, final Set<String> set) {
        return (Processor<OCSymbol>)(p5 -> {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_1        
            //     1: ifnull          20
            //     4: aload_1        
            //     5: aload           6
            //     7: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.isSuitableSymbol:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
            //    10: ifeq            142
            //    13: goto            20
            //    16: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCUnresolvedReferenceRenamer.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    19: athrow         
            //    20: aload_2        
            //    21: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnknown:()Z
            //    24: ifne            78
            //    27: goto            34
            //    30: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCUnresolvedReferenceRenamer.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    33: athrow         
            //    34: aload_2        
            //    35: aload           6
            //    37: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
            //    42: aload_3        
            //    43: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isCompatible:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Z
            //    46: ifne            78
            //    49: goto            56
            //    52: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCUnresolvedReferenceRenamer.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    55: athrow         
            //    56: aload           6
            //    58: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
            //    63: aload_2        
            //    64: aload_3        
            //    65: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isCompatible:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Z
            //    68: ifeq            142
            //    71: goto            78
            //    74: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCUnresolvedReferenceRenamer.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    77: athrow         
            //    78: aload           6
            //    80: aload_3        
            //    81: aload           4
            //    83: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.isVisible:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/types/OCType;)Z
            //    86: ifeq            142
            //    89: goto            96
            //    92: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCUnresolvedReferenceRenamer.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    95: athrow         
            //    96: aload_0        
            //    97: aload           6
            //    99: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
            //   104: invokestatic    com/jetbrains/cidr/lang/OCLanguage.getInstance:()Lcom/jetbrains/cidr/lang/OCLanguage;
            //   107: invokevirtual   com/jetbrains/cidr/lang/refactoring/rename/OCUnresolvedReferenceRenamer.isIdentifier:(Ljava/lang/String;Lcom/intellij/lang/Language;)Z
            //   110: ifeq            142
            //   113: goto            120
            //   116: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCUnresolvedReferenceRenamer.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   119: athrow         
            //   120: aload           5
            //   122: aload           6
            //   124: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
            //   129: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
            //   134: pop            
            //   135: goto            142
            //   138: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCUnresolvedReferenceRenamer.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   141: athrow         
            //   142: iconst_1       
            //   143: ireturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  0      13     16     20     Ljava/lang/IllegalArgumentException;
            //  4      27     30     34     Ljava/lang/IllegalArgumentException;
            //  20     49     52     56     Ljava/lang/IllegalArgumentException;
            //  34     71     74     78     Ljava/lang/IllegalArgumentException;
            //  56     89     92     96     Ljava/lang/IllegalArgumentException;
            //  78     113    116    120    Ljava/lang/IllegalArgumentException;
            //  96     135    138    142    Ljava/lang/IllegalArgumentException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0020:
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
        });
    }
    
    @Override
    protected void performOnInvalidIdentifier(final String s, final LinkedHashSet<String> set) {
        this.restoreCaretOffset(this.myReference.getElement().getTextOffset());
        final PsiNamedElement psiNamedElement;
        PsiNamedElement psiNamedElement2;
        final Object o;
        JBPopupFactory.getInstance().createConfirmation("Inserted identifier is not valid", "Continue editing", "Cancel", () -> {
            Label_0023_1: {
                try {
                    // new(com.jetbrains.cidr.lang.refactoring.rename.OCUnresolvedReferenceRenamer.class)
                    if (psiNamedElement instanceof PsiNamedElement) {
                        psiNamedElement2 = psiNamedElement;
                        break Label_0023_1;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
            }
            new OCUnresolvedReferenceRenamer(psiNamedElement2, this.myEditor, this.myFile, this.myProject, this.myReference, s, this.myOldName);
            ((VariableInplaceRenamer)o).performInplaceRename();
        }, 0).showInBestPositionFor(this.myEditor);
    }
    
    @Override
    public String getInitialName() {
        try {
            if (this.myInitialName != null) {
                return this.myInitialName;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return this.myReference.getCanonicalText();
    }
    
    @Override
    protected String getCommandName() {
        return "Rename reference";
    }
    
    @Override
    protected void showDialogAdvertisement(final String s) {
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
