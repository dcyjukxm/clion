// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.hierarchy.structureVIew;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import com.intellij.psi.PsiComment;
import com.jetbrains.cidr.lang.psi.OCPragma;
import com.intellij.util.ui.EmptyIcon;
import javax.swing.Icon;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import java.util.Iterator;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.psi.OCEnum;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCMacroSymbol;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.navigation.ColoredItemPresentation;
import com.intellij.openapi.editor.markup.EffectType;
import java.awt.Color;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.ui.JBColor;
import com.intellij.pom.Navigatable;
import com.intellij.ide.util.treeView.smartTree.TreeElement;
import com.intellij.navigation.ItemPresentation;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.ide.structureView.StructureViewTreeElement;

public class OCStructureViewElement implements StructureViewTreeElement, OCMarkedStructureViewElement
{
    public static final TextAttributesKey OC_PRAGMA_MARK;
    @NotNull
    private final PsiElement myElement;
    @Nullable
    private final PsiElement myContext;
    @Nullable
    private final PsiElement myParentMark;
    
    public OCStructureViewElement(@NotNull final PsiElement psiElement, @Nullable final PsiElement psiElement2) {
        if (psiElement == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/hierarchy/structureVIew/OCStructureViewElement", "<init>"));
        }
        this(psiElement, psiElement2, null);
    }
    
    private OCStructureViewElement(@NotNull final PsiElement myElement, @Nullable final PsiElement myContext, @Nullable final PsiElement myParentMark) {
        if (myElement == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/hierarchy/structureVIew/OCStructureViewElement", "<init>"));
        }
        this.myElement = myElement;
        this.myContext = myContext;
        this.myParentMark = myParentMark;
    }
    
    public PsiElement getValue() {
        return this.myElement;
    }
    
    public boolean isAllowInheritorsAndCategories() {
        try {
            if (this.myContext == null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    @NotNull
    public ItemPresentation getPresentation() {
        OCItemPresentation ocItemPresentation;
        try {
            ocItemPresentation = new OCItemPresentation(this.myElement);
            if (ocItemPresentation == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/hierarchy/structureVIew/OCStructureViewElement", "getPresentation"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (ItemPresentation)ocItemPresentation;
    }
    
    @NotNull
    public TreeElement[] getChildren() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/hierarchy/structureVIew/OCStructureViewElement.myElement:Lcom/intellij/psi/PsiElement;
        //     4: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //     7: ifne            44
        //    10: aload_0        
        //    11: getfield        com/jetbrains/cidr/lang/hierarchy/structureVIew/OCStructureViewElement.myElement:Lcom/intellij/psi/PsiElement;
        //    14: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDefinition;
        //    17: ifne            44
        //    20: goto            27
        //    23: invokestatic    com/jetbrains/cidr/lang/hierarchy/structureVIew/OCStructureViewElement.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    26: athrow         
        //    27: aload_0        
        //    28: getfield        com/jetbrains/cidr/lang/hierarchy/structureVIew/OCStructureViewElement.myElement:Lcom/intellij/psi/PsiElement;
        //    31: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //    34: ifeq            93
        //    37: goto            44
        //    40: invokestatic    com/jetbrains/cidr/lang/hierarchy/structureVIew/OCStructureViewElement.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: getstatic       com/intellij/ide/structureView/StructureViewTreeElement.EMPTY_ARRAY:[Lcom/intellij/ide/structureView/StructureViewTreeElement;
        //    47: dup            
        //    48: ifnonnull       92
        //    51: goto            58
        //    54: invokestatic    com/jetbrains/cidr/lang/hierarchy/structureVIew/OCStructureViewElement.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    57: athrow         
        //    58: new             Ljava/lang/IllegalStateException;
        //    61: dup            
        //    62: ldc             "@NotNull method %s.%s must not return null"
        //    64: ldc             2
        //    66: anewarray       Ljava/lang/Object;
        //    69: dup            
        //    70: ldc             0
        //    72: ldc             "com/jetbrains/cidr/lang/hierarchy/structureVIew/OCStructureViewElement"
        //    74: aastore        
        //    75: dup            
        //    76: ldc             1
        //    78: ldc             "getChildren"
        //    80: aastore        
        //    81: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    84: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    87: athrow         
        //    88: invokestatic    com/jetbrains/cidr/lang/hierarchy/structureVIew/OCStructureViewElement.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    91: athrow         
        //    92: areturn        
        //    93: new             Ljava/util/ArrayList;
        //    96: dup            
        //    97: invokespecial   java/util/ArrayList.<init>:()V
        //   100: astore_1       
        //   101: aload_0        
        //   102: getfield        com/jetbrains/cidr/lang/hierarchy/structureVIew/OCStructureViewElement.myElement:Lcom/intellij/psi/PsiElement;
        //   105: new             Lcom/jetbrains/cidr/lang/hierarchy/structureVIew/OCStructureViewElement$1;
        //   108: dup            
        //   109: aload_0        
        //   110: aload_1        
        //   111: invokespecial   com/jetbrains/cidr/lang/hierarchy/structureVIew/OCStructureViewElement$1.<init>:(Lcom/jetbrains/cidr/lang/hierarchy/structureVIew/OCStructureViewElement;Ljava/util/List;)V
        //   114: invokeinterface com/intellij/psi/PsiElement.acceptChildren:(Lcom/intellij/psi/PsiElementVisitor;)V
        //   119: new             Ljava/util/ArrayList;
        //   122: dup            
        //   123: invokespecial   java/util/ArrayList.<init>:()V
        //   126: astore_2       
        //   127: aconst_null    
        //   128: astore_3       
        //   129: aload_1        
        //   130: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   135: astore          4
        //   137: aload           4
        //   139: invokeinterface java/util/Iterator.hasNext:()Z
        //   144: ifeq            362
        //   147: aload           4
        //   149: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   154: checkcast       Lcom/intellij/psi/PsiElement;
        //   157: astore          5
        //   159: aload           5
        //   161: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   164: ifne            182
        //   167: aload           5
        //   169: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStructLike;
        //   172: ifeq            260
        //   175: goto            182
        //   178: invokestatic    com/jetbrains/cidr/lang/hierarchy/structureVIew/OCStructureViewElement.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   181: athrow         
        //   182: aload           5
        //   184: checkcast       Lcom/intellij/psi/PsiNamedElement;
        //   187: invokeinterface com/intellij/psi/PsiNamedElement.getName:()Ljava/lang/String;
        //   192: ifnonnull       209
        //   195: goto            202
        //   198: invokestatic    com/jetbrains/cidr/lang/hierarchy/structureVIew/OCStructureViewElement.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   201: athrow         
        //   202: goto            137
        //   205: invokestatic    com/jetbrains/cidr/lang/hierarchy/structureVIew/OCStructureViewElement.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   208: athrow         
        //   209: aload_0        
        //   210: getfield        com/jetbrains/cidr/lang/hierarchy/structureVIew/OCStructureViewElement.myContext:Lcom/intellij/psi/PsiElement;
        //   213: ifnull          260
        //   216: aload           5
        //   218: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSymbolDeclarator;
        //   221: invokeinterface com/jetbrains/cidr/lang/psi/OCSymbolDeclarator.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   226: astore          6
        //   228: aload           6
        //   230: ifnull          260
        //   233: aload           6
        //   235: aload_0        
        //   236: getfield        com/jetbrains/cidr/lang/hierarchy/structureVIew/OCStructureViewElement.myContext:Lcom/intellij/psi/PsiElement;
        //   239: aconst_null    
        //   240: invokestatic    com/jetbrains/cidr/lang/symbols/OCVisibility.isVisible:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   243: ifne            260
        //   246: goto            253
        //   249: invokestatic    com/jetbrains/cidr/lang/hierarchy/structureVIew/OCStructureViewElement.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   252: athrow         
        //   253: goto            137
        //   256: invokestatic    com/jetbrains/cidr/lang/hierarchy/structureVIew/OCStructureViewElement.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   259: athrow         
        //   260: aload           5
        //   262: instanceof      Lcom/jetbrains/cidr/lang/psi/OCPragma;
        //   265: ifne            283
        //   268: aload           5
        //   270: instanceof      Lcom/intellij/psi/PsiComment;
        //   273: ifeq            338
        //   276: goto            283
        //   279: invokestatic    com/jetbrains/cidr/lang/hierarchy/structureVIew/OCStructureViewElement.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   282: athrow         
        //   283: aload           5
        //   285: invokestatic    com/jetbrains/cidr/lang/hierarchy/structureVIew/OCMark.createFromElement:(Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/hierarchy/structureVIew/OCMark;
        //   288: astore          6
        //   290: aload           6
        //   292: ifnull          335
        //   295: aload           5
        //   297: astore_3       
        //   298: aload           6
        //   300: invokevirtual   com/jetbrains/cidr/lang/hierarchy/structureVIew/OCMark.getText:()Ljava/lang/String;
        //   303: ifnull          335
        //   306: aload_2        
        //   307: new             Lcom/jetbrains/cidr/lang/hierarchy/structureVIew/OCStructureViewElement;
        //   310: dup            
        //   311: aload           5
        //   313: aload_0        
        //   314: getfield        com/jetbrains/cidr/lang/hierarchy/structureVIew/OCStructureViewElement.myContext:Lcom/intellij/psi/PsiElement;
        //   317: aload           5
        //   319: invokespecial   com/jetbrains/cidr/lang/hierarchy/structureVIew/OCStructureViewElement.<init>:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)V
        //   322: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   327: pop            
        //   328: goto            335
        //   331: invokestatic    com/jetbrains/cidr/lang/hierarchy/structureVIew/OCStructureViewElement.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   334: athrow         
        //   335: goto            359
        //   338: aload_2        
        //   339: new             Lcom/jetbrains/cidr/lang/hierarchy/structureVIew/OCStructureViewElement;
        //   342: dup            
        //   343: aload           5
        //   345: aload_0        
        //   346: getfield        com/jetbrains/cidr/lang/hierarchy/structureVIew/OCStructureViewElement.myContext:Lcom/intellij/psi/PsiElement;
        //   349: aload_3        
        //   350: invokespecial   com/jetbrains/cidr/lang/hierarchy/structureVIew/OCStructureViewElement.<init>:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)V
        //   353: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   358: pop            
        //   359: goto            137
        //   362: aload_2        
        //   363: aload_2        
        //   364: invokeinterface java/util/List.size:()I
        //   369: anewarray       Lcom/intellij/ide/util/treeView/smartTree/TreeElement;
        //   372: invokeinterface java/util/List.toArray:([Ljava/lang/Object;)[Ljava/lang/Object;
        //   377: checkcast       [Lcom/intellij/ide/util/treeView/smartTree/TreeElement;
        //   380: dup            
        //   381: ifnonnull       418
        //   384: new             Ljava/lang/IllegalStateException;
        //   387: dup            
        //   388: ldc             "@NotNull method %s.%s must not return null"
        //   390: ldc             2
        //   392: anewarray       Ljava/lang/Object;
        //   395: dup            
        //   396: ldc             0
        //   398: ldc             "com/jetbrains/cidr/lang/hierarchy/structureVIew/OCStructureViewElement"
        //   400: aastore        
        //   401: dup            
        //   402: ldc             1
        //   404: ldc             "getChildren"
        //   406: aastore        
        //   407: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   410: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   413: athrow         
        //   414: invokestatic    com/jetbrains/cidr/lang/hierarchy/structureVIew/OCStructureViewElement.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   417: athrow         
        //   418: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      20     23     27     Ljava/lang/IllegalArgumentException;
        //  10     37     40     44     Ljava/lang/IllegalArgumentException;
        //  27     51     54     58     Ljava/lang/IllegalArgumentException;
        //  44     88     88     92     Ljava/lang/IllegalArgumentException;
        //  159    175    178    182    Ljava/lang/IllegalArgumentException;
        //  167    195    198    202    Ljava/lang/IllegalArgumentException;
        //  182    205    205    209    Ljava/lang/IllegalArgumentException;
        //  228    246    249    253    Ljava/lang/IllegalArgumentException;
        //  233    256    256    260    Ljava/lang/IllegalArgumentException;
        //  260    276    279    283    Ljava/lang/IllegalArgumentException;
        //  298    328    331    335    Ljava/lang/IllegalArgumentException;
        //  362    414    414    418    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0027:
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
    
    public void navigate(final boolean b) {
        ((Navigatable)this.myElement).navigate(b);
    }
    
    public boolean canNavigate() {
        return ((Navigatable)this.myElement).canNavigate();
    }
    
    public boolean canNavigateToSource() {
        return ((Navigatable)this.myElement).canNavigateToSource();
    }
    
    @Nullable
    public PsiElement getParentMarkElement() {
        return this.myParentMark;
    }
    
    static {
        OC_PRAGMA_MARK = TextAttributesKey.createTextAttributesKey("OC_PRAGMA_MARK", new TextAttributes(JBColor.foreground(), (Color)null, (Color)null, (EffectType)null, 1));
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    private static class OCItemPresentation implements ColoredItemPresentation
    {
        private final PsiElement myElement;
        private final OCSymbol mySymbol;
        
        private OCItemPresentation(final PsiElement myElement) {
            this.myElement = myElement;
            if (this.myElement instanceof OCSymbolDeclarator && this.myElement.isValid()) {
                this.mySymbol = ((OCSymbolDeclarator)this.myElement).getSymbol();
            }
            else {
                this.mySymbol = null;
            }
        }
        
        public String getPresentableText() {
            if (this.mySymbol instanceof OCMacroSymbol) {
                return ((OCMacroSymbol)this.mySymbol).getPresentableSignature();
            }
            if (this.mySymbol instanceof OCFunctionSymbol) {
                return ((OCFunctionSymbol)this.mySymbol).getSignatureWithoutParamNames();
            }
            if (this.mySymbol instanceof OCDeclaratorSymbol) {
                return a((OCDeclaratorSymbol)this.mySymbol);
            }
            if (this.mySymbol instanceof OCStructSymbol) {
                return a((OCStructSymbol)this.mySymbol);
            }
            if (this.myElement instanceof PsiFile) {
                return ((PsiFile)this.myElement).getName();
            }
            if (this.myElement instanceof OCMethod) {
                return ((OCMethod)this.myElement).getSelector();
            }
            if (this.myElement instanceof OCClassDeclaration) {
                final OCClassDeclaration ocClassDeclaration = (OCClassDeclaration)this.myElement;
                final String name = ocClassDeclaration.getName();
                if (name == null) {
                    return "<unnamed>";
                }
                return OCCodeInsightUtil.getClassNameWithCategory(name, ocClassDeclaration.getCategory());
            }
            else {
                if (this.a()) {
                    final OCMark fromElement = OCMark.createFromElement(this.myElement);
                    return (fromElement != null) ? fromElement.getText() : "<unnamed>";
                }
                if (!(this.myElement instanceof PsiNameIdentifierOwner)) {
                    return "<unknown>";
                }
                final String name2 = ((PsiNameIdentifierOwner)this.myElement).getName();
                if (name2 == null && this.myElement instanceof OCEnum) {
                    final StringBuilder sb = new StringBuilder("enum {");
                    int n = 1;
                    for (final OCDeclaration ocDeclaration : ((OCEnum)this.myElement).getFields()) {
                        if (n != 0) {
                            n = 0;
                        }
                        else {
                            sb.append(", ");
                        }
                        if (sb.length() > 30) {
                            sb.append("...");
                            break;
                        }
                        sb.append(ocDeclaration.getDeclarators().get(0).getName());
                    }
                    sb.append("}");
                    return sb.toString();
                }
                return (name2 == null) ? "<unnamed>" : name2;
            }
        }
        
        private static String a(final OCStructSymbol ocStructSymbol) {
            return ocStructSymbol.getQualifiedName().toString();
        }
        
        private static String a(final OCDeclaratorSymbol ocDeclaratorSymbol) {
            final StringBuilder sb = new StringBuilder();
            final String canonicalName = ocDeclaratorSymbol.getQualifiedName().getCanonicalName(true);
            sb.append(canonicalName);
            if (ocDeclaratorSymbol.getKind() != OCSymbolKind.ENUM_CONST) {
                final String name = ocDeclaratorSymbol.getType().getName();
                if (!Comparing.equal(name, canonicalName)) {
                    sb.append(" : ");
                    sb.append(name);
                }
            }
            return sb.toString();
        }
        
        public String getLocationString() {
            return null;
        }
        
        public Icon getIcon(final boolean b) {
            return this.a() ? EmptyIcon.ICON_16 : (this.myElement.isValid() ? this.myElement.getIcon(0) : null);
        }
        
        public TextAttributesKey getTextAttributesKey() {
            return this.a() ? OCStructureViewElement.OC_PRAGMA_MARK : null;
        }
        
        private boolean a() {
            return this.myElement instanceof OCPragma || this.myElement instanceof PsiComment;
        }
    }
}
