// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.rename;

import com.intellij.openapi.project.Project;
import com.intellij.refactoring.rename.inplace.VariableInplaceRenamer;
import com.intellij.lang.Language;
import com.intellij.openapi.command.impl.StartMarkAction;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import com.jetbrains.cidr.lang.symbols.objc.OCCompatibilityAliasSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.refactoring.introduce.OCBaseInplaceIntroducer;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import javax.swing.border.Border;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import javax.swing.JComponent;
import com.jetbrains.cidr.lang.psi.OCLocalizedString;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.intellij.psi.search.SearchScope;
import com.intellij.psi.search.searches.ReferencesSearch;
import com.intellij.psi.search.LocalSearchScope;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import java.util.List;
import java.util.Iterator;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.psi.OCSelectorExpression;
import com.jetbrains.cidr.lang.psi.OCArgumentSelector;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.intellij.util.Processor;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.Pair;
import com.intellij.psi.PsiReference;
import java.util.Collection;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.intellij.codeInsight.highlighting.ReadWriteAccessDetector;
import com.jetbrains.cidr.lang.search.usages.OCReadWriteAccessDetector;
import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;
import com.intellij.psi.PsiNamedElement;
import com.intellij.openapi.editor.Editor;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.refactoring.rename.inplace.MemberInplaceRenamer;

public class OCInplaceRenamer extends MemberInplaceRenamer
{
    private OCSymbol<?> mySymbol;
    private boolean myCategoryMode;
    private PsiElement mySelectorIdentifier;
    private int mySelectorIndex;
    private boolean myConvertToSetter;
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public OCInplaceRenamer(@NotNull final OCMethod ocMethod, @NotNull final OCMethodSymbol mySymbol, final PsiElement mySelectorIdentifier, final int mySelectorIndex, final Editor editor, final String s, final String s2) {
        if (ocMethod == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "elementToRename", "com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer", "<init>"));
        }
        if (mySymbol == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer", "<init>"));
        }
        super((PsiNamedElement)ocMethod, (PsiElement)ocMethod, editor, (s != null) ? s : mySelectorIdentifier.getText(), (s2 != null) ? s2 : mySelectorIdentifier.getText());
        this.mySymbol = mySymbol;
        this.myCategoryMode = false;
        this.mySelectorIdentifier = mySelectorIdentifier;
        this.mySelectorIndex = mySelectorIndex;
        boolean myConvertToSetter;
        if (this.mySelectorIdentifier.getParent() instanceof OCQualifiedExpression && new OCReadWriteAccessDetector().getExpressionAccess(this.mySelectorIdentifier.getParent()) == ReadWriteAccessDetector.Access.Write) {
            myConvertToSetter = true;
        }
        else {
            myConvertToSetter = false;
        }
        Label_0211: {
            try {
                this.myConvertToSetter = myConvertToSetter;
                if (OCInplaceRenamer.$assertionsDisabled) {
                    return;
                }
                final OCInplaceRenamer ocInplaceRenamer = this;
                final int n = ocInplaceRenamer.mySelectorIndex;
                final int n2 = -1;
                if (n == n2) {
                    break Label_0211;
                }
                return;
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            try {
                final OCInplaceRenamer ocInplaceRenamer = this;
                final int n = ocInplaceRenamer.mySelectorIndex;
                final int n2 = -1;
                if (n == n2) {
                    throw new AssertionError();
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
        }
    }
    
    public OCInplaceRenamer(@NotNull final PsiNamedElement psiNamedElement, @NotNull final OCSymbol mySymbol, final Editor editor, final boolean myCategoryMode, final String s, final String s2) {
        if (psiNamedElement == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "elementToRename", "com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer", "<init>"));
        }
        if (mySymbol == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer", "<init>"));
        }
        super(psiNamedElement, (PsiElement)psiNamedElement, editor, (s != null) ? s : a(psiNamedElement), (s2 != null) ? s2 : a(psiNamedElement));
        this.mySymbol = (OCSymbol<?>)mySymbol;
        this.myCategoryMode = myCategoryMode;
    }
    
    private static String a(final PsiNamedElement psiNamedElement) {
        try {
            if (!(psiNamedElement instanceof OCDeclarator) || !(psiNamedElement.getParent() instanceof OCFunctionDeclaration)) {
                return psiNamedElement.getName();
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        final OCSymbolWithQualifiedName ocSymbolWithQualifiedName = ((OCFunctionDeclaration)psiNamedElement.getParent()).getSymbol();
        Label_0068: {
            try {
                if (!(ocSymbolWithQualifiedName instanceof OCFunctionSymbol)) {
                    return psiNamedElement.getName();
                }
                final OCSymbolWithQualifiedName ocSymbolWithQualifiedName2 = ocSymbolWithQualifiedName;
                final OCFunctionSymbol ocFunctionSymbol = (OCFunctionSymbol)ocSymbolWithQualifiedName2;
                final boolean b = ocFunctionSymbol.isCppDestructor();
                if (b) {
                    break Label_0068;
                }
                return psiNamedElement.getName();
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
            try {
                final OCSymbolWithQualifiedName ocSymbolWithQualifiedName2 = ocSymbolWithQualifiedName;
                final OCFunctionSymbol ocFunctionSymbol = (OCFunctionSymbol)ocSymbolWithQualifiedName2;
                final boolean b = ocFunctionSymbol.isCppDestructor();
                if (b) {
                    return ocSymbolWithQualifiedName.getName().substring(1);
                }
            }
            catch (IllegalArgumentException ex3) {
                throw c(ex3);
            }
        }
        return psiNamedElement.getName();
    }
    
    @Override
    protected boolean appendAdditionalElement(final Collection<PsiReference> collection, final Collection<Pair<PsiElement, TextRange>> collection2) {
        final boolean appendAdditionalElement = super.appendAdditionalElement(collection, collection2);
        final PsiFile psiFile = PsiDocumentManager.getInstance(this.myProject).getPsiFile(this.myEditor.getDocument());
        VirtualFile virtualFile = null;
        Label_0048: {
            try {
                if (psiFile != null) {
                    virtualFile = psiFile.getVirtualFile();
                    break Label_0048;
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            virtualFile = null;
        }
        this.mySymbol.processSameSymbols((com.intellij.util.Processor<OCSymbol<?>>)(p4 -> {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload           5
            //     2: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getContainingFile:()Lcom/intellij/openapi/vfs/VirtualFile;
            //     7: aload_1        
            //     8: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/Object;Ljava/lang/Object;)Z
            //    11: ifeq            309
            //    14: aload           5
            //    16: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.locateDefinition:()Lcom/intellij/psi/PsiElement;
            //    21: astore          6
            //    23: aload_0        
            //    24: getfield        com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.myCategoryMode:Z
            //    27: ifeq            106
            //    30: aload           6
            //    32: instanceof      Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
            //    35: ifeq            254
            //    38: goto            45
            //    41: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    44: athrow         
            //    45: aload_0        
            //    46: getfield        com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
            //    49: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
            //    52: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getCategoryName:()Ljava/lang/String;
            //    57: aload           5
            //    59: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
            //    62: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getCategoryName:()Ljava/lang/String;
            //    67: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/String;Ljava/lang/String;)Z
            //    70: ifeq            254
            //    73: goto            80
            //    76: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    79: athrow         
            //    80: aload           6
            //    82: checkcast       Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
            //    85: invokeinterface com/jetbrains/cidr/lang/psi/OCClassDeclaration.getCategoryElement:()Lcom/jetbrains/cidr/lang/psi/OCCategoryName;
            //    90: invokeinterface com/jetbrains/cidr/lang/psi/OCCategoryName.getNameIdentifier:()Lcom/intellij/psi/PsiElement;
            //    95: astore          7
            //    97: aload           7
            //    99: aload_2        
            //   100: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.a:(Lcom/intellij/psi/PsiElement;Ljava/util/Collection;)V
            //   103: goto            254
            //   106: aload           6
            //   108: instanceof      Lcom/intellij/psi/PsiNameIdentifierOwner;
            //   111: ifeq            254
            //   114: aload           6
            //   116: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMethod;
            //   119: ifeq            240
            //   122: goto            129
            //   125: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   128: athrow         
            //   129: aload           6
            //   131: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMethod;
            //   134: invokeinterface com/jetbrains/cidr/lang/psi/OCMethod.getParameters:()Ljava/util/List;
            //   139: astore          7
            //   141: aload_0        
            //   142: getfield        com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.mySelectorIndex:I
            //   145: aload           7
            //   147: invokeinterface java/util/List.size:()I
            //   152: if_icmpge       237
            //   155: aload           7
            //   157: aload_0        
            //   158: getfield        com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.mySelectorIndex:I
            //   161: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
            //   166: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMethodSelectorPart;
            //   169: invokeinterface com/jetbrains/cidr/lang/psi/OCMethodSelectorPart.getSelectorIdentifier:()Lcom/intellij/psi/PsiElement;
            //   174: astore          8
            //   176: aload           8
            //   178: ifnull          237
            //   181: aload_0        
            //   182: getfield        com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.mySelectorIdentifier:Lcom/intellij/psi/PsiElement;
            //   185: ifnull          237
            //   188: goto            195
            //   191: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   194: athrow         
            //   195: aload           8
            //   197: invokeinterface com/intellij/psi/PsiElement.getText:()Ljava/lang/String;
            //   202: aload_0        
            //   203: getfield        com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.mySelectorIdentifier:Lcom/intellij/psi/PsiElement;
            //   206: invokeinterface com/intellij/psi/PsiElement.getText:()Ljava/lang/String;
            //   211: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
            //   214: ifeq            237
            //   217: goto            224
            //   220: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   223: athrow         
            //   224: aload           8
            //   226: aload_2        
            //   227: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.a:(Lcom/intellij/psi/PsiElement;Ljava/util/Collection;)V
            //   230: goto            237
            //   233: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   236: athrow         
            //   237: goto            254
            //   240: aload           6
            //   242: checkcast       Lcom/intellij/psi/PsiNameIdentifierOwner;
            //   245: invokeinterface com/intellij/psi/PsiNameIdentifierOwner.getNameIdentifier:()Lcom/intellij/psi/PsiElement;
            //   250: aload_2        
            //   251: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.a:(Lcom/intellij/psi/PsiElement;Ljava/util/Collection;)V
            //   254: aload           5
            //   256: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCLocalizedStringSymbol;
            //   259: ifeq            309
            //   262: aload_3        
            //   263: new             Lcom/jetbrains/cidr/lang/resolve/references/OCStringResourceReference;
            //   266: dup            
            //   267: aload_0        
            //   268: getfield        com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.myElementToRename:Lcom/intellij/psi/PsiNamedElement;
            //   271: invokeinterface com/intellij/psi/PsiNamedElement.getFirstChild:()Lcom/intellij/psi/PsiElement;
            //   276: aload_0        
            //   277: getfield        com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.myElementToRename:Lcom/intellij/psi/PsiNamedElement;
            //   280: invokeinterface com/intellij/psi/PsiNamedElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
            //   285: invokeinterface com/intellij/psi/PsiFile.getName:()Ljava/lang/String;
            //   290: invokestatic    com/intellij/openapi/util/io/FileUtil.getNameWithoutExtension:(Ljava/lang/String;)Ljava/lang/String;
            //   293: invokespecial   com/jetbrains/cidr/lang/resolve/references/OCStringResourceReference.<init>:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;)V
            //   296: invokeinterface java/util/Collection.add:(Ljava/lang/Object;)Z
            //   301: pop            
            //   302: goto            309
            //   305: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   308: athrow         
            //   309: aload           5
            //   311: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
            //   314: ifeq            336
            //   317: aload           5
            //   319: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
            //   322: aload_2        
            //   323: aload_3        
            //   324: aload           4
            //   326: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.a:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;Ljava/util/Collection;Ljava/util/Collection;Lcom/intellij/psi/PsiFile;)V
            //   329: goto            417
            //   332: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   335: athrow         
            //   336: aload           5
            //   338: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
            //   341: ifeq            417
            //   344: aload           5
            //   346: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
            //   349: astore          6
            //   351: aload           6
            //   353: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isCppConstructor:()Z
            //   356: ifne            374
            //   359: aload           6
            //   361: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isCppDestructor:()Z
            //   364: ifeq            417
            //   367: goto            374
            //   370: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   373: athrow         
            //   374: aload           6
            //   376: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getResolvedOwner:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
            //   379: astore          7
            //   381: aload           7
            //   383: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
            //   386: ifeq            417
            //   389: aload           7
            //   391: aload_2        
            //   392: aload_3        
            //   393: aload           4
            //   395: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.a:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Ljava/util/Collection;Ljava/util/Collection;Lcom/intellij/psi/PsiFile;)V
            //   398: aload           7
            //   400: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
            //   403: aload_2        
            //   404: aload_3        
            //   405: aload           4
            //   407: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.a:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;Ljava/util/Collection;Ljava/util/Collection;Lcom/intellij/psi/PsiFile;)V
            //   410: goto            417
            //   413: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   416: athrow         
            //   417: iconst_1       
            //   418: ireturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  23     38     41     45     Ljava/lang/IllegalArgumentException;
            //  30     73     76     80     Ljava/lang/IllegalArgumentException;
            //  106    122    125    129    Ljava/lang/IllegalArgumentException;
            //  176    188    191    195    Ljava/lang/IllegalArgumentException;
            //  181    217    220    224    Ljava/lang/IllegalArgumentException;
            //  195    230    233    237    Ljava/lang/IllegalArgumentException;
            //  254    302    305    309    Ljava/lang/IllegalArgumentException;
            //  309    332    332    336    Ljava/lang/IllegalArgumentException;
            //  351    367    370    374    Ljava/lang/IllegalArgumentException;
            //  381    410    413    417    Ljava/lang/IllegalArgumentException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0195:
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
        }));
        if (this.mySelectorIdentifier != null) {
            final Iterator<PsiReference> iterator = collection.iterator();
            while (iterator.hasNext()) {
                final PsiElement element = iterator.next().getElement();
                PsiElement psiElement = null;
                if (element instanceof OCSendMessageExpression) {
                    final List<OCArgumentSelector> argumentSelectors = ((OCSendMessageExpression)element).getArgumentSelectors();
                    if (this.mySelectorIndex < argumentSelectors.size()) {
                        psiElement = argumentSelectors.get(this.mySelectorIndex).getSelectorIdentifier();
                    }
                }
                else if (element instanceof OCQualifiedExpression) {
                    psiElement = ((OCQualifiedExpression)element).getNameIdentifier();
                }
                else if (element instanceof OCSelectorExpression) {
                    psiElement = ((OCSelectorExpression)element).getSelectorParts().get(this.mySelectorIndex);
                }
                Label_0268: {
                    try {
                        if (psiElement == null) {
                            continue;
                        }
                        final PsiElement psiElement2 = psiElement;
                        final String s = psiElement2.getText();
                        final OCInplaceRenamer ocInplaceRenamer = this;
                        final PsiElement psiElement3 = ocInplaceRenamer.mySelectorIdentifier;
                        final String s2 = psiElement3.getText();
                        final boolean b = s.equals(s2);
                        if (b) {
                            break Label_0268;
                        }
                        continue;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw c(ex2);
                    }
                    try {
                        final PsiElement psiElement2 = psiElement;
                        final String s = psiElement2.getText();
                        final OCInplaceRenamer ocInplaceRenamer = this;
                        final PsiElement psiElement3 = ocInplaceRenamer.mySelectorIdentifier;
                        final String s2 = psiElement3.getText();
                        final boolean b = s.equals(s2);
                        if (!b) {
                            continue;
                        }
                        a(psiElement, collection2);
                    }
                    catch (IllegalArgumentException ex3) {
                        throw c(ex3);
                    }
                }
            }
            collection.clear();
        }
        return appendAdditionalElement;
    }
    
    private static void a(final PsiElement psiElement, final Collection<Pair<PsiElement, TextRange>> collection) {
        Label_0018: {
            try {
                if (psiElement == null) {
                    return;
                }
                final PsiElement psiElement2 = psiElement;
                final boolean b = OCElementUtil.isPartOfMacroSubstitution(psiElement2);
                if (!b) {
                    break Label_0018;
                }
                return;
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            try {
                final PsiElement psiElement2 = psiElement;
                final boolean b = OCElementUtil.isPartOfMacroSubstitution(psiElement2);
                if (!b) {
                    collection.add((Pair<PsiElement, TextRange>)Pair.create((Object)psiElement, (Object)new TextRange(0, psiElement.getTextLength())));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
        }
    }
    
    private static void a(final OCStructSymbol ocStructSymbol, final Collection<Pair<PsiElement, TextRange>> collection, final Collection<PsiReference> collection2, final PsiFile psiFile) {
        final Processor processor = ocFunctionSymbol -> {
            a(ocFunctionSymbol, collection, collection2, psiFile);
            return true;
        };
        ocStructSymbol.processConstructors((Processor<? super OCFunctionSymbol>)processor, true);
        ocStructSymbol.processDestructors((Processor<OCFunctionSymbol>)processor, true);
    }
    
    private static void a(final OCSymbol ocSymbol, final Collection<Pair<PsiElement, TextRange>> collection, final Collection<PsiReference> collection2, final PsiFile psiFile) {
        try {
            if (!Comparing.equal((Object)ocSymbol.getContainingFile(), (Object)psiFile.getVirtualFile())) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        final PsiElement locateDefinition = ocSymbol.locateDefinition();
        if (locateDefinition instanceof PsiNameIdentifierOwner) {
            final PsiElement nameIdentifier = ((PsiNameIdentifierOwner)locateDefinition).getNameIdentifier();
            if (nameIdentifier != null) {
                a(nameIdentifier, collection);
                for (final PsiReference psiReference : ReferencesSearch.search(locateDefinition, (SearchScope)new LocalSearchScope((PsiElement)psiFile), false).findAll()) {
                    try {
                        if (psiReference.getElement() instanceof OCDeclarator) {
                            continue;
                        }
                        collection2.add(psiReference);
                    }
                    catch (IllegalArgumentException ex2) {
                        throw c(ex2);
                    }
                }
            }
        }
    }
    
    @Override
    protected PsiElement checkLocalScope() {
        try {
            if (this.mySymbol.getKind() != OCSymbolKind.PARAMETER || this.mySymbol.getAssociatedSymbol() == null) {
                return super.checkLocalScope();
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        final PsiFile psiFile = PsiDocumentManager.getInstance(this.myProject).getPsiFile(this.myEditor.getDocument());
        try {
            if (psiFile != null) {
                return (PsiElement)psiFile;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        return super.checkLocalScope();
    }
    
    @Override
    protected PsiNamedElement getVariable() {
        Label_0024: {
            try {
                if (this.myRenameOffset == null) {
                    break Label_0024;
                }
                final OCInplaceRenamer ocInplaceRenamer = this;
                final PsiNamedElement psiNamedElement = ocInplaceRenamer.myElementToRename;
                final boolean b = psiNamedElement instanceof OCLocalizedString;
                if (b) {
                    break Label_0024;
                }
                return super.getVariable();
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            try {
                final OCInplaceRenamer ocInplaceRenamer = this;
                final PsiNamedElement psiNamedElement = ocInplaceRenamer.myElementToRename;
                final boolean b = psiNamedElement instanceof OCLocalizedString;
                if (b) {
                    return this.myElementToRename;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
        }
        return super.getVariable();
    }
    
    @Override
    public PsiElement getSubstituted() {
        Label_0024: {
            try {
                if (this.myRenameOffset == null) {
                    break Label_0024;
                }
                final OCInplaceRenamer ocInplaceRenamer = this;
                final PsiNamedElement psiNamedElement = ocInplaceRenamer.myElementToRename;
                final boolean b = psiNamedElement instanceof OCLocalizedString;
                if (b) {
                    break Label_0024;
                }
                return super.getSubstituted();
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            try {
                final OCInplaceRenamer ocInplaceRenamer = this;
                final PsiNamedElement psiNamedElement = ocInplaceRenamer.myElementToRename;
                final boolean b = psiNamedElement instanceof OCLocalizedString;
                if (b) {
                    final Object o = this.myElementToRename;
                    return (PsiElement)o;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
        }
        final Object o = super.getSubstituted();
        return (PsiElement)o;
    }
    
    @Override
    protected JComponent getComponent() {
        try {
            if (this.myCategoryMode) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        final OCCodeStyleSettings ocCodeStyleSettings = (OCCodeStyleSettings)CodeStyleSettingsManager.getSettings(this.myProject).getCustomSettings((Class)OCCodeStyleSettings.class);
        final JPanel panel = new JPanel(new GridBagLayout());
        try {
            panel.setBorder(null);
            OCRenameProcessor.processAssociatedSymbols(this.mySymbol, new OCRenameProcessor.AssociatedElementsProcessor() {
                @Override
                public boolean processIvar(final OCInstanceVariableSymbol ocInstanceVariableSymbol, final OCPropertySymbol ocPropertySymbol) {
                    if (ocInstanceVariableSymbol.isClang4ImplicitIvar()) {
                        return true;
                    }
                    OCBaseInplaceIntroducer.createCheckBox(OCInplaceRenamer.this.myProject, OCInplaceRenamer.this.myTitle, panel, "&Rename " + ocInstanceVariableSymbol.getNameWithKindLowercase(), ocCodeStyleSettings.REFACTOR_PROPERTIES_AND_IVARS, (Processor<Boolean>)(b -> ocCodeStyleSettings.REFACTOR_PROPERTIES_AND_IVARS = b));
                    return false;
                }
                
                @Override
                public boolean processPropertyAccessors(final OCPropertySymbol ocPropertySymbol) {
                    return false;
                }
                
                @Override
                public boolean processProperty(final OCPropertySymbol ocPropertySymbol, final OCSymbol ocSymbol) {
                    if (!(ocSymbol instanceof OCInstanceVariableSymbol) || ((OCInstanceVariableSymbol)ocSymbol).isClang4ImplicitIvar()) {
                        return true;
                    }
                    OCBaseInplaceIntroducer.createCheckBox(OCInplaceRenamer.this.myProject, OCInplaceRenamer.this.myTitle, panel, "&Rename " + ocPropertySymbol.getNameWithKindLowercase(), ocCodeStyleSettings.REFACTOR_PROPERTIES_AND_IVARS, (Processor<Boolean>)(b -> ocCodeStyleSettings.REFACTOR_PROPERTIES_AND_IVARS = b));
                    return false;
                }
                
                @Override
                public boolean processClassAlias(final OCClassSymbol ocClassSymbol, final OCCompatibilityAliasSymbol ocCompatibilityAliasSymbol) {
                    OCBaseInplaceIntroducer.createCheckBox(OCInplaceRenamer.this.myProject, OCInplaceRenamer.this.myTitle, panel, "&Rename class aliases", ocCodeStyleSettings.REFACTOR_PROPERTIES_AND_IVARS, (Processor<Boolean>)(b -> ocCodeStyleSettings.REFACTOR_COMPATIBILITY_ALIASES_AND_CLASSES = b));
                    return false;
                }
                
                @Override
                public boolean processClass(final OCCompatibilityAliasSymbol ocCompatibilityAliasSymbol, final OCClassSymbol ocClassSymbol) {
                    OCBaseInplaceIntroducer.createCheckBox(OCInplaceRenamer.this.myProject, OCInplaceRenamer.this.myTitle, panel, "&Rename " + ocClassSymbol.getNameWithKindLowercase(), ocCodeStyleSettings.REFACTOR_PROPERTIES_AND_IVARS, (Processor<Boolean>)(b -> ocCodeStyleSettings.REFACTOR_COMPATIBILITY_ALIASES_AND_CLASSES = b));
                    return false;
                }
            }, (SearchScope)OCSearchScope.getProjectSourcesScope(this.myProject));
            if (panel.getComponentCount() > 0) {
                return panel;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        return null;
    }
    
    public void performRenameInner(final PsiElement p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.mySelectorIdentifier:Lcom/intellij/psi/PsiElement;
        //     4: ifnonnull       46
        //     7: aload_0        
        //     8: getfield        com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    11: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //    14: ifeq            191
        //    17: goto            24
        //    20: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    23: athrow         
        //    24: aload_0        
        //    25: getfield        com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    28: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    33: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isConstructorOrDestructor:()Z
        //    36: ifne            191
        //    39: goto            46
        //    42: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    45: athrow         
        //    46: aload_0        
        //    47: getfield        com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.myConvertToSetter:Z
        //    50: ifeq            83
        //    53: goto            60
        //    56: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    59: athrow         
        //    60: new             Ljava/lang/StringBuilder;
        //    63: dup            
        //    64: invokespecial   java/lang/StringBuilder.<init>:()V
        //    67: ldc             "set"
        //    69: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    72: aload_2        
        //    73: invokestatic    com/intellij/openapi/util/text/StringUtil.capitalize:(Ljava/lang/String;)Ljava/lang/String;
        //    76: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    79: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    82: astore_2       
        //    83: aload_1        
        //    84: iconst_0       
        //    85: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.findRenameTargetDefinition:(Lcom/intellij/psi/PsiElement;Z)Lcom/intellij/psi/PsiElement;
        //    88: astore_3       
        //    89: aload_3        
        //    90: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //    93: ifeq            188
        //    96: aload_3        
        //    97: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   100: astore          4
        //   102: aload           4
        //   104: aload_1        
        //   105: iconst_0       
        //   106: iconst_1       
        //   107: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //   110: invokeinterface com/intellij/openapi/application/Application.isUnitTestMode:()Z
        //   115: ifne            126
        //   118: iconst_1       
        //   119: goto            127
        //   122: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   125: athrow         
        //   126: iconst_0       
        //   127: iconst_0       
        //   128: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureActionHandler.getHandler:(Lcom/jetbrains/cidr/lang/psi/OCCallable;Lcom/intellij/psi/PsiElement;ZZZZ)Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandler;
        //   131: astore          5
        //   133: aload           5
        //   135: aload_0        
        //   136: invokevirtual   com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.getCommandName:()Ljava/lang/String;
        //   139: invokeinterface com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandler.setTitle:(Ljava/lang/String;)V
        //   144: aload_0        
        //   145: getfield        com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   148: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   151: ifeq            169
        //   154: aload           5
        //   156: aload_2        
        //   157: invokeinterface com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandler.setName:(Ljava/lang/String;)V
        //   162: goto            181
        //   165: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   168: athrow         
        //   169: aload           5
        //   171: aload_0        
        //   172: getfield        com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.mySelectorIndex:I
        //   175: aload_2        
        //   176: invokeinterface com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandler.renameSelector:(ILjava/lang/String;)V
        //   181: aload           5
        //   183: invokeinterface com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandler.invoke:()V
        //   188: goto            302
        //   191: aload_0        
        //   192: getfield        com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   195: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //   198: ifeq            259
        //   201: aload_0        
        //   202: getfield        com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   205: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //   208: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.isClang4ImplicitIvar:()Z
        //   213: ifeq            259
        //   216: goto            223
        //   219: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   222: athrow         
        //   223: aload_2        
        //   224: ldc             "_"
        //   226: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   229: ifne            259
        //   232: goto            239
        //   235: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   238: athrow         
        //   239: new             Ljava/lang/StringBuilder;
        //   242: dup            
        //   243: invokespecial   java/lang/StringBuilder.<init>:()V
        //   246: ldc             "_"
        //   248: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   251: aload_2        
        //   252: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   255: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   258: astore_2       
        //   259: aload_2        
        //   260: astore_3       
        //   261: aload_1        
        //   262: invokestatic    com/intellij/refactoring/rename/RenamePsiElementProcessor.forElement:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/refactoring/rename/RenamePsiElementProcessor;
        //   265: astore          4
        //   267: new             Lcom/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer$2;
        //   270: dup            
        //   271: aload_0        
        //   272: aload_0        
        //   273: getfield        com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.myProject:Lcom/intellij/openapi/project/Project;
        //   276: aload_1        
        //   277: aload_2        
        //   278: aload           4
        //   280: aload_1        
        //   281: invokevirtual   com/intellij/refactoring/rename/RenamePsiElementProcessor.isToSearchInComments:(Lcom/intellij/psi/PsiElement;)Z
        //   284: aload           4
        //   286: aload_1        
        //   287: invokevirtual   com/intellij/refactoring/rename/RenamePsiElementProcessor.isToSearchForTextOccurrences:(Lcom/intellij/psi/PsiElement;)Z
        //   290: aload_1        
        //   291: aload_3        
        //   292: invokespecial   com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer$2.<init>:(Lcom/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer;Lcom/intellij/openapi/project/Project;Lcom/intellij/psi/PsiElement;Ljava/lang/String;ZZLcom/intellij/psi/PsiElement;Ljava/lang/String;)V
        //   295: astore          5
        //   297: aload           5
        //   299: invokevirtual   com/intellij/refactoring/rename/RenameProcessor.run:()V
        //   302: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      17     20     24     Ljava/lang/IllegalArgumentException;
        //  7      39     42     46     Ljava/lang/IllegalArgumentException;
        //  24     53     56     60     Ljava/lang/IllegalArgumentException;
        //  102    122    122    126    Ljava/lang/IllegalArgumentException;
        //  133    165    165    169    Ljava/lang/IllegalArgumentException;
        //  191    216    219    223    Ljava/lang/IllegalArgumentException;
        //  201    232    235    239    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0024:
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
    
    @Override
    public boolean performInplaceRename() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Ljava/util/LinkedHashSet;
        //     3: dup            
        //     4: invokespecial   java/util/LinkedHashSet.<init>:()V
        //     7: astore_1       
        //     8: aload_0        
        //     9: getfield        com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.myElementToRename:Lcom/intellij/psi/PsiNamedElement;
        //    12: invokeinterface com/intellij/psi/PsiNamedElement.getName:()Ljava/lang/String;
        //    17: astore_2       
        //    18: aload_0        
        //    19: getfield        com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.myElementToRename:Lcom/intellij/psi/PsiNamedElement;
        //    22: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //    25: ifeq            74
        //    28: aload_2        
        //    29: ifnonnull       49
        //    32: goto            39
        //    35: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    38: athrow         
        //    39: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //    42: goto            53
        //    45: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    48: athrow         
        //    49: aload_2        
        //    50: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
        //    53: astore_3       
        //    54: aload_1        
        //    55: aload_0        
        //    56: getfield        com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.myElementToRename:Lcom/intellij/psi/PsiNamedElement;
        //    59: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //    62: iconst_1       
        //    63: aload_3        
        //    64: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.suggestForDeclaration:(Lcom/jetbrains/cidr/lang/psi/OCDeclarator;ZLjava/util/Collection;)Ljava/util/Collection;
        //    67: invokevirtual   java/util/LinkedHashSet.addAll:(Ljava/util/Collection;)Z
        //    70: pop            
        //    71: goto            264
        //    74: aload_0        
        //    75: getfield        com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.myElementToRename:Lcom/intellij/psi/PsiNamedElement;
        //    78: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMethodSelectorPart;
        //    81: ifeq            264
        //    84: new             Ljava/util/ArrayList;
        //    87: dup            
        //    88: invokespecial   java/util/ArrayList.<init>:()V
        //    91: astore_3       
        //    92: aload_0        
        //    93: getfield        com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.myElementToRename:Lcom/intellij/psi/PsiNamedElement;
        //    96: invokeinterface com/intellij/psi/PsiNamedElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   101: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //   104: astore          4
        //   106: aload           4
        //   108: invokeinterface com/jetbrains/cidr/lang/psi/OCMethod.getParameters:()Ljava/util/List;
        //   113: astore          5
        //   115: aload           5
        //   117: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   122: astore          6
        //   124: aload           6
        //   126: invokeinterface java/util/Iterator.hasNext:()Z
        //   131: ifeq            179
        //   134: aload           6
        //   136: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   141: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMethodSelectorPart;
        //   144: astore          7
        //   146: aload           7
        //   148: invokeinterface com/jetbrains/cidr/lang/psi/OCMethodSelectorPart.getParameterName:()Ljava/lang/String;
        //   153: astore          8
        //   155: aload           8
        //   157: ifnull          176
        //   160: aload_3        
        //   161: aload           8
        //   163: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   168: pop            
        //   169: goto            176
        //   172: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   175: athrow         
        //   176: goto            124
        //   179: aload_0        
        //   180: getfield        com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.myElementToRename:Lcom/intellij/psi/PsiNamedElement;
        //   183: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMethodSelectorPart;
        //   186: astore          6
        //   188: aload_1        
        //   189: aload           6
        //   191: invokeinterface com/jetbrains/cidr/lang/psi/OCMethodSelectorPart.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   196: aload           6
        //   198: aload_3        
        //   199: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.suggestForType:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;Ljava/util/Collection;)Ljava/util/Collection;
        //   202: invokevirtual   java/util/LinkedHashSet.addAll:(Ljava/util/Collection;)Z
        //   205: pop            
        //   206: aload_1        
        //   207: aload_3        
        //   208: aload           5
        //   210: iconst_0       
        //   211: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   216: aload_0        
        //   217: getfield        com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.myElementToRename:Lcom/intellij/psi/PsiNamedElement;
        //   220: if_acmpne       231
        //   223: iconst_1       
        //   224: goto            232
        //   227: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   230: athrow         
        //   231: iconst_0       
        //   232: aload           6
        //   234: invokeinterface com/jetbrains/cidr/lang/psi/OCMethodSelectorPart.getSelectorPart:()Ljava/lang/String;
        //   239: aload           6
        //   241: invokeinterface com/jetbrains/cidr/lang/psi/OCMethodSelectorPart.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   246: aload           4
        //   248: invokeinterface com/jetbrains/cidr/lang/psi/OCMethod.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   253: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   256: aconst_null    
        //   257: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.suggestForParameter:(Ljava/util/Collection;ZLjava/lang/String;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;Lcom/jetbrains/cidr/lang/psi/OCExpression;)Ljava/lang/String;
        //   260: invokevirtual   java/util/LinkedHashSet.add:(Ljava/lang/Object;)Z
        //   263: pop            
        //   264: aload_0        
        //   265: aload_0        
        //   266: getfield        com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.myElementToRename:Lcom/intellij/psi/PsiNamedElement;
        //   269: invokeinterface com/intellij/psi/PsiNamedElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   274: invokeinterface com/intellij/psi/PsiFile.getVirtualFile:()Lcom/intellij/openapi/vfs/VirtualFile;
        //   279: invokevirtual   com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.getReferencesSearchScope:(Lcom/intellij/openapi/vfs/VirtualFile;)Lcom/intellij/psi/search/SearchScope;
        //   282: astore_3       
        //   283: aload_0        
        //   284: aload_3        
        //   285: invokevirtual   com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.collectRefs:(Lcom/intellij/psi/search/SearchScope;)Ljava/util/Collection;
        //   288: astore          4
        //   290: aload           4
        //   292: invokeinterface java/util/Collection.iterator:()Ljava/util/Iterator;
        //   297: astore          5
        //   299: aload           5
        //   301: invokeinterface java/util/Iterator.hasNext:()Z
        //   306: ifeq            425
        //   309: aload           5
        //   311: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   316: checkcast       Lcom/intellij/psi/PsiReference;
        //   319: astore          6
        //   321: aconst_null    
        //   322: astore          7
        //   324: aload           6
        //   326: invokeinterface com/intellij/psi/PsiReference.getElement:()Lcom/intellij/psi/PsiElement;
        //   331: astore          8
        //   333: aload           8
        //   335: instanceof      Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //   338: ifeq            351
        //   341: aload           8
        //   343: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   346: astore          7
        //   348: goto            391
        //   351: aload           8
        //   353: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //   356: ifeq            391
        //   359: aload           8
        //   361: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   366: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //   369: ifeq            391
        //   372: goto            379
        //   375: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   378: athrow         
        //   379: aload           8
        //   381: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   386: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   389: astore          7
        //   391: aload           7
        //   393: ifnull          422
        //   396: aload_1        
        //   397: aload_0        
        //   398: getfield        com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.mySymbol:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   401: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   406: aload           7
        //   408: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.suggestForExpression:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Lcom/jetbrains/cidr/lang/psi/OCExpression;)Ljava/util/Collection;
        //   411: invokevirtual   java/util/LinkedHashSet.addAll:(Ljava/util/Collection;)Z
        //   414: pop            
        //   415: goto            422
        //   418: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   421: athrow         
        //   422: goto            299
        //   425: aload_1        
        //   426: invokevirtual   java/util/LinkedHashSet.iterator:()Ljava/util/Iterator;
        //   429: astore          5
        //   431: aload           5
        //   433: invokeinterface java/util/Iterator.hasNext:()Z
        //   438: ifeq            514
        //   441: aload           5
        //   443: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   448: checkcast       Ljava/lang/String;
        //   451: astore          6
        //   453: aload_2        
        //   454: ifnull          511
        //   457: aload           6
        //   459: aload_2        
        //   460: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   463: ifeq            511
        //   466: goto            473
        //   469: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   472: athrow         
        //   473: aload           6
        //   475: aload_2        
        //   476: invokevirtual   java/lang/String.length:()I
        //   479: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   482: ldc             "[0-9]*"
        //   484: invokevirtual   java/lang/String.matches:(Ljava/lang/String;)Z
        //   487: ifeq            511
        //   490: goto            497
        //   493: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   496: athrow         
        //   497: aload           5
        //   499: invokeinterface java/util/Iterator.remove:()V
        //   504: goto            511
        //   507: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   510: athrow         
        //   511: goto            431
        //   514: aload_0        
        //   515: aload_1        
        //   516: invokevirtual   com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.performInplaceRefactoring:(Ljava/util/LinkedHashSet;)Z
        //   519: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  18     32     35     39     Ljava/lang/IllegalArgumentException;
        //  28     45     45     49     Ljava/lang/IllegalArgumentException;
        //  155    169    172    176    Ljava/lang/IllegalArgumentException;
        //  188    227    227    231    Ljava/lang/IllegalArgumentException;
        //  351    372    375    379    Ljava/lang/IllegalArgumentException;
        //  391    415    418    422    Ljava/lang/IllegalArgumentException;
        //  453    466    469    473    Ljava/lang/IllegalArgumentException;
        //  457    490    493    497    Ljava/lang/IllegalArgumentException;
        //  473    504    507    511    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0473:
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
    
    public void performRefactoringForTest(final String s) {
        this.performRefactoringRename(s, null);
    }
    
    @Override
    protected boolean acceptReference(final PsiReference psiReference) {
        Label_0040: {
            try {
                if (OCElementUtil.isPartOfMacroSubstitution(psiReference.getElement())) {
                    return false;
                }
                final PsiReference psiReference2 = psiReference;
                final String s = psiReference2.getCanonicalText();
                final OCInplaceRenamer ocInplaceRenamer = this;
                final PsiNamedElement psiNamedElement = ocInplaceRenamer.myElementToRename;
                final String s2 = psiNamedElement.getName();
                final boolean b = Comparing.equal(s, s2);
                if (b) {
                    break Label_0040;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            try {
                final PsiReference psiReference2 = psiReference;
                final String s = psiReference2.getCanonicalText();
                final OCInplaceRenamer ocInplaceRenamer = this;
                final PsiNamedElement psiNamedElement = ocInplaceRenamer.myElementToRename;
                final String s2 = psiNamedElement.getName();
                final boolean b = Comparing.equal(s, s2);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
        }
        return false;
    }
    
    @Override
    protected boolean isIdentifier(final String p0, final Language p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.myElementToRename:Lcom/intellij/psi/PsiNamedElement;
        //     4: instanceof      Lcom/jetbrains/cidr/lang/psi/OCLocalizedString;
        //     7: ifeq            24
        //    10: aload_1        
        //    11: invokevirtual   java/lang/String.isEmpty:()Z
        //    14: ifeq            68
        //    17: goto            24
        //    20: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    23: athrow         
        //    24: aload_0        
        //    25: getfield        com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.mySelectorIdentifier:Lcom/intellij/psi/PsiElement;
        //    28: ifnull          52
        //    31: goto            38
        //    34: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    37: athrow         
        //    38: aload_1        
        //    39: invokevirtual   java/lang/String.isEmpty:()Z
        //    42: ifne            68
        //    45: goto            52
        //    48: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    51: athrow         
        //    52: aload_0        
        //    53: aload_1        
        //    54: aload_2        
        //    55: invokespecial   com/intellij/refactoring/rename/inplace/MemberInplaceRenamer.isIdentifier:(Ljava/lang/String;Lcom/intellij/lang/Language;)Z
        //    58: ifeq            76
        //    61: goto            68
        //    64: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    67: athrow         
        //    68: iconst_1       
        //    69: goto            77
        //    72: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    75: athrow         
        //    76: iconst_0       
        //    77: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      17     20     24     Ljava/lang/IllegalArgumentException;
        //  10     31     34     38     Ljava/lang/IllegalArgumentException;
        //  24     45     48     52     Ljava/lang/IllegalArgumentException;
        //  38     61     64     68     Ljava/lang/IllegalArgumentException;
        //  52     72     72     76     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0024:
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
    
    @NotNull
    @Override
    protected VariableInplaceRenamer createInplaceRenamerToRestart(final PsiNamedElement psiNamedElement, final Editor editor, final String s) {
        VariableInplaceRenamer renamer;
        try {
            renamer = OCInplaceRenameHandler.createRenamer((PsiElement)psiNamedElement, editor, this.myOldName, this.myOldName);
            if (renamer == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/rename/OCInplaceRenamer", "createInplaceRenamerToRestart"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return renamer;
    }
    
    @Override
    protected PsiElement getNameIdentifier() {
        PsiElement psiElement = null;
        Label_0022: {
            try {
                if (this.mySelectorIdentifier != null) {
                    psiElement = this.mySelectorIdentifier;
                    break Label_0022;
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            psiElement = super.getNameIdentifier();
        }
        final PsiElement psiElement2 = psiElement;
        Label_0041: {
            try {
                if (psiElement2 == null) {
                    return psiElement2;
                }
                final PsiElement psiElement3 = psiElement2;
                final boolean b = OCElementUtil.isPartOfMacroSubstitution(psiElement3);
                if (b) {
                    break Label_0041;
                }
                return psiElement2;
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
            try {
                final PsiElement psiElement3 = psiElement2;
                final boolean b = OCElementUtil.isPartOfMacroSubstitution(psiElement3);
                if (b) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw c(ex3);
            }
        }
        return psiElement2;
    }
    
    @Override
    protected String getCommandName() {
        final PsiNamedElement variable = this.getVariable();
        Label_0025: {
            try {
                if (variable == null) {
                    return super.getCommandName();
                }
                final PsiNamedElement psiNamedElement = variable;
                final boolean b = psiNamedElement.isValid();
                if (!b) {
                    break Label_0025;
                }
                return super.getCommandName();
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            try {
                final PsiNamedElement psiNamedElement = variable;
                final boolean b = psiNamedElement.isValid();
                if (!b) {
                    return "Rename";
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
        }
        return super.getCommandName();
    }
    
    @Override
    protected void showDialogAdvertisement(final String s) {
    }
    
    @Override
    protected boolean isReferenceAtCaret(final PsiElement psiElement, final PsiReference psiReference) {
        return false;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCInplaceRenamer.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}
