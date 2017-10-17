// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.util.OCDeclarationKind;
import com.jetbrains.cidr.lang.psi.OCInstanceVariablesList;
import com.jetbrains.cidr.lang.psi.OCImplementation;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.intellij.psi.PsiNamedElement;
import com.jetbrains.cidr.lang.psi.OCInterface;
import com.jetbrains.cidr.lang.resolve.OCResolveUtil;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.util.OCCommonProcessors;
import com.jetbrains.cidr.lang.symbols.objc.OCProtocolSymbol;
import com.intellij.util.CommonProcessors;
import java.util.Iterator;
import com.jetbrains.cidr.lang.symbols.objc.OCInterfaceSymbol;
import com.jetbrains.cidr.lang.psi.OCProtocol;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import java.util.ArrayList;
import java.util.Collections;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.intellij.openapi.vfs.VirtualFile;
import javax.swing.Icon;
import com.intellij.navigation.ItemPresentation;
import com.jetbrains.cidr.lang.psi.OCCategoryName;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import org.jetbrains.annotations.NonNls;
import com.jetbrains.cidr.lang.symbols.OCSymbolOffsetUtil;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiReference;
import com.jetbrains.cidr.lang.psi.OCSynthesizePropertiesList;
import com.jetbrains.cidr.lang.psi.OCProperty;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCMethod;
import java.util.List;
import com.jetbrains.cidr.lang.psi.OCProtocolList;
import com.intellij.util.IncorrectOperationException;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.psi.OCSuperClassRef;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;

public abstract class OCClassDeclarationBaseImpl<T extends OCClassSymbol> extends OCElementWithReferenceBase implements OCClassDeclaration<T>
{
    public OCClassDeclarationBaseImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCClassDeclarationBaseImpl", "<init>"));
        }
        super(astNode);
    }
    
    @NotNull
    @Override
    public OCSuperClassRef getSuperClassRef() {
        OCSuperClassRef ocSuperClassRef;
        try {
            ocSuperClassRef = this.findNotNullChildByType(OCElementTypes.SUPER_CLASS_REF);
            if (ocSuperClassRef == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCClassDeclarationBaseImpl", "getSuperClassRef"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return ocSuperClassRef;
    }
    
    @NotNull
    @Override
    public OCProtocolList getProtocolList() {
        OCProtocolList list;
        try {
            list = this.findNotNullChildByType(OCElementTypes.PROTOCOL_LIST);
            if (list == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCClassDeclarationBaseImpl", "getProtocolList"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return list;
    }
    
    @NotNull
    @Override
    public List<OCMethod> getMethods() {
        List<PsiElement> childrenByType;
        try {
            childrenByType = (List<PsiElement>)this.findChildrenByType(OCElementTypes.METHOD);
            if (childrenByType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCClassDeclarationBaseImpl", "getMethods"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return (List<OCMethod>)childrenByType;
    }
    
    @NotNull
    @Override
    public List<OCProperty> getProperties() {
        List<PsiElement> childrenByType;
        try {
            childrenByType = (List<PsiElement>)this.findChildrenByType(OCElementTypes.PROPERTY);
            if (childrenByType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCClassDeclarationBaseImpl", "getProperties"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return (List<OCProperty>)childrenByType;
    }
    
    @NotNull
    @Override
    public List<OCSynthesizePropertiesList> getSynthesizes() {
        List<PsiElement> childrenByType;
        try {
            childrenByType = (List<PsiElement>)this.findChildrenByType(OCElementTypes.SYNTHESIZED_PROPERTIES_LIST);
            if (childrenByType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCClassDeclarationBaseImpl", "getSynthesizes"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return (List<OCSynthesizePropertiesList>)childrenByType;
    }
    
    @Nullable
    @Override
    protected PsiReference createReference() {
        return null;
    }
    
    @Override
    public String getName() {
        final PsiElement nameIdentifier = this.getNameIdentifier();
        try {
            if (nameIdentifier != null) {
                return nameIdentifier.getText();
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return null;
    }
    
    @Nullable
    @Override
    public String getCanonicalName() {
        return OCCodeInsightUtil.getClassNameWithCategory(this.getName(), this.getCategory());
    }
    
    @Nullable
    public PsiElement getNameIdentifier() {
        for (ASTNode astNode = this.getNode().getFirstChildNode(); astNode != null; astNode = astNode.getTreeNext()) {
            final IElementType elementType = astNode.getElementType();
            try {
                if (elementType == OCTokenTypes.IDENTIFIER) {
                    return astNode.getPsi();
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            try {
                if (elementType == OCTokenTypes.LPAR) {
                    return null;
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
        }
        return null;
    }
    
    @Override
    public int getTextOffset() {
        return OCSymbolOffsetUtil.getTextOffset(this.getComplexOffset());
    }
    
    @Override
    public long getComplexOffset() {
        final PsiElement nameIdentifier = this.getNameIdentifier();
        try {
            if (nameIdentifier == null) {
                return super.getComplexOffset();
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return OCSymbolOffsetUtil.getComplexOffset(nameIdentifier);
    }
    
    public PsiElement setName(@NonNls @NotNull String substring) throws IncorrectOperationException {
        try {
            if (substring == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/psi/impl/OCClassDeclarationBaseImpl", "setName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final int index = substring.indexOf(43);
        if (index != -1) {
            final OCCategoryName categoryElement = this.getCategoryElement();
            try {
                if (categoryElement != null) {
                    categoryElement.setName(substring.substring(index + 1));
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
            substring = substring.substring(0, index);
        }
        OCElementUtil.replaceWithIdentifier(this.getNameIdentifier(), substring, (PsiElement)this);
        return (PsiElement)this;
    }
    
    @Nullable
    @Override
    public String getCategory() {
        final OCCategoryName categoryElement = this.getCategoryElement();
        try {
            if (categoryElement != null) {
                return categoryElement.getName();
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return null;
    }
    
    @Nullable
    @Override
    public OCCategoryName getCategoryElement() {
        return this.findChildByType(OCElementTypes.CATEGORY_NAME);
    }
    
    @Override
    public ItemPresentation getPresentation() {
        return (ItemPresentation)new ItemPresentation() {
            public String getPresentableText() {
                return OCCodeInsightUtil.getClassNameWithCategory(OCClassDeclarationBaseImpl.this.getName(), OCClassDeclarationBaseImpl.this.getCategory());
            }
            
            public String getLocationString() {
                final String access$000 = a(OCClassDeclarationBaseImpl.this.getContainingFile().getVirtualFile());
                return (access$000.length() == 0) ? OCClassDeclarationBaseImpl.this.getContainingFile().getVirtualFile().getName() : access$000;
            }
            
            public Icon getIcon(final boolean b) {
                return OCClassDeclarationBaseImpl.this.getIcon(0);
            }
        };
    }
    
    private static String a(final VirtualFile p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Ljava/lang/StringBuilder;
        //     3: dup            
        //     4: invokespecial   java/lang/StringBuilder.<init>:()V
        //     7: astore_1       
        //     8: aload_0        
        //     9: ifnull          78
        //    12: ldc             "framework"
        //    14: aload_0        
        //    15: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getExtension:()Ljava/lang/String;
        //    18: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    21: ifeq            70
        //    24: goto            31
        //    27: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCClassDeclarationBaseImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    30: athrow         
        //    31: aload_1        
        //    32: invokevirtual   java/lang/StringBuilder.length:()I
        //    35: ifle            60
        //    38: goto            45
        //    41: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCClassDeclarationBaseImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    44: athrow         
        //    45: aload_1        
        //    46: iconst_0       
        //    47: ldc             "::"
        //    49: invokevirtual   java/lang/StringBuilder.insert:(ILjava/lang/String;)Ljava/lang/StringBuilder;
        //    52: pop            
        //    53: goto            60
        //    56: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCClassDeclarationBaseImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    59: athrow         
        //    60: aload_1        
        //    61: iconst_0       
        //    62: aload_0        
        //    63: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getNameWithoutExtension:()Ljava/lang/String;
        //    66: invokevirtual   java/lang/StringBuilder.insert:(ILjava/lang/String;)Ljava/lang/StringBuilder;
        //    69: pop            
        //    70: aload_0        
        //    71: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getParent:()Lcom/intellij/openapi/vfs/VirtualFile;
        //    74: astore_0       
        //    75: goto            8
        //    78: aload_1        
        //    79: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    82: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  8      24     27     31     Lcom/intellij/util/IncorrectOperationException;
        //  12     38     41     45     Lcom/intellij/util/IncorrectOperationException;
        //  31     53     56     60     Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0031:
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
    public OCObjectType getType(final boolean b) {
        final OCType resolvedFromText = OCReferenceType.resolvedFromText(this.getName(), (PsiFile)this.getContainingOCFile(), b);
        try {
            if (resolvedFromText instanceof OCObjectType) {
                return (OCObjectType)resolvedFromText;
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return null;
    }
    
    @Nullable
    @Override
    public OCObjectType getType() {
        return this.getType(false);
    }
    
    @Override
    public T getSymbol() {
        return this.getContainingOCFile().findSymbol(this, this.getSymbolClass());
    }
    
    protected Class<? extends OCClassSymbol> getSymbolClass() {
        return OCClassSymbol.class;
    }
    
    @Override
    public List<OCClassDeclaration> getSuperTypes() {
        final OCObjectType type = this.getType();
        try {
            if (type == null) {
                return (List<OCClassDeclaration>)Collections.emptyList();
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final OCObjectType superType = type.getSuperType();
        final ArrayList<OCClassDeclaration> list = new ArrayList<OCClassDeclaration>();
        try {
            if (superType != null) {
                a(list, (OCSymbol)superType.getClassSymbol());
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        Label_0081: {
            try {
                if (this instanceof OCProtocol) {
                    this.a(list, this.getSymbol());
                    break Label_0081;
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
            this.a(list, type.getClassSymbol());
        }
        final Iterator<OCInterfaceSymbol> iterator = type.getCategoryInterfaces().iterator();
        while (iterator.hasNext()) {
            this.a(list, iterator.next());
        }
        return list;
    }
    
    private static void a(final List<OCClassDeclaration> list, @Nullable final OCSymbol ocSymbol) {
        if (ocSymbol != null) {
            final PsiElement locateDefinition = ocSymbol.locateDefinition();
            try {
                if (locateDefinition instanceof OCClassDeclaration) {
                    list.add((OCClassDeclaration)locateDefinition);
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
        }
    }
    
    private void a(final List<OCClassDeclaration> list, @Nullable final OCClassSymbol ocClassSymbol) {
        if (ocClassSymbol != null) {
            for (final String s : ocClassSymbol.getProtocolNames()) {
                final CommonProcessors.FindFirstProcessor findFirstProcessor = new CommonProcessors.FindFirstProcessor();
                try {
                    OCResolveUtil.processGlobalSymbols(s, (PsiElement)this, (Processor<OCSymbol>)new OCCommonProcessors.TypeFilteredProcessor((com.intellij.util.Processor<Object>)findFirstProcessor, OCProtocolSymbol.class));
                    if (!findFirstProcessor.isFound()) {
                        continue;
                    }
                    a(list, (OCSymbol)findFirstProcessor.getFoundValue());
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
            }
        }
    }
    
    @Override
    public OCInterface getSuperClass() {
        final OCObjectType type = this.getType();
        try {
            if (type == null) {
                return null;
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final OCObjectType superType = type.getSuperType();
        try {
            if (superType == null) {
                return null;
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        final OCClassSymbol classSymbol = superType.getClassSymbol();
        try {
            if (classSymbol == null) {
                return null;
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        final PsiNamedElement psiNamedElement = ((OCSymbol<PsiNamedElement>)classSymbol).locateDefinition();
        try {
            if (psiNamedElement instanceof OCInterface) {
                return (OCInterface)psiNamedElement;
            }
        }
        catch (IncorrectOperationException ex4) {
            throw a(ex4);
        }
        return null;
    }
    
    @Override
    public List<OCSymbol> getSuperSymbols() {
        final OCObjectType type = this.getType();
        try {
            if (type == null) {
                return (List<OCSymbol>)Collections.emptyList();
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final OCProtocolList protocolList = this.getProtocolList();
        final ArrayList<OCSymbol> list = new ArrayList<OCSymbol>();
        final Iterator<OCReferenceElement> iterator = protocolList.getProtocols().iterator();
        while (iterator.hasNext()) {
            final OCSymbol resolveToSymbol = iterator.next().resolveToSymbol();
            try {
                if (!(resolveToSymbol instanceof OCProtocolSymbol)) {
                    continue;
                }
                list.add(resolveToSymbol);
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
        }
        final OCObjectType superType = type.getSuperType();
        Object o = null;
        Label_0143: {
            Label_0126: {
                try {
                    if (superType == null) {
                        return list;
                    }
                    final OCClassDeclarationBaseImpl ocClassDeclarationBaseImpl = this;
                    final boolean b = ocClassDeclarationBaseImpl instanceof OCImplementation;
                    if (b) {
                        break Label_0126;
                    }
                    break Label_0126;
                }
                catch (IncorrectOperationException ex3) {
                    throw a(ex3);
                }
                try {
                    final OCClassDeclarationBaseImpl ocClassDeclarationBaseImpl = this;
                    final boolean b = ocClassDeclarationBaseImpl instanceof OCImplementation;
                    if (b) {
                        o = superType.getImplementation();
                        break Label_0143;
                    }
                }
                catch (IncorrectOperationException ex4) {
                    throw a(ex4);
                }
            }
            o = superType.getInterface();
        }
        OCSymbol classSymbol = (OCSymbol)o;
        if (classSymbol == null) {
            classSymbol = superType.getClassSymbol();
        }
        try {
            if (classSymbol != null) {
                list.add(classSymbol);
            }
        }
        catch (IncorrectOperationException ex5) {
            throw a(ex5);
        }
        return list;
    }
    
    @NotNull
    @Override
    public OCInstanceVariablesList getInstanceVariablesList() {
        OCInstanceVariablesList list;
        try {
            list = this.findNotNullChildByType(OCElementTypes.INSTANCE_VARIABLES_LIST);
            if (list == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCClassDeclarationBaseImpl", "getInstanceVariablesList"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return list;
    }
    
    private int b() {
        return this.getMethodsStartOffset(false);
    }
    
    @Override
    public int getMethodsStartOffset(final boolean b) {
        int n = -1;
        final Iterator<OCDeclarationKind> iterator = OCDeclarationKind.ourMethodKinds.iterator();
        while (iterator.hasNext()) {
            final int childrenStartOffset = iterator.next().getChildrenStartOffset((PsiElement)this, b);
            try {
                if (n != -1) {
                    if (n <= childrenStartOffset) {
                        continue;
                    }
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            n = childrenStartOffset;
        }
        return n;
    }
    
    private int a() {
        return this.getMethodsEndOffset(false);
    }
    
    @Override
    public int getMethodsEndOffset(final boolean b) {
        int n = -1;
        final Iterator<OCDeclarationKind> iterator = OCDeclarationKind.ourMethodKinds.iterator();
        while (iterator.hasNext()) {
            final int childrenStartOffset = iterator.next().getChildrenStartOffset((PsiElement)this, b);
            try {
                if (n != -1) {
                    if (n >= childrenStartOffset) {
                        continue;
                    }
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            n = childrenStartOffset;
        }
        return n;
    }
    
    @Override
    public int getMethodsInsertPosition(final boolean b, PsiElement context, final int n) {
        int n2 = 0;
        Label_0019: {
            try {
                if (b) {
                    n2 = this.b();
                    break Label_0019;
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            n2 = this.a();
        }
        final int n3 = n2;
        try {
            if (n == -1) {
                return n3;
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        while (true) {
            try {
                if (context == null || Comparing.equal((Object)context.getContext(), (Object)this)) {
                    break;
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
            context = context.getContext();
        }
        try {
            if (context == null || n < this.getMethodsStartOffset(true)) {
                return n3;
            }
        }
        catch (IncorrectOperationException ex4) {
            throw a(ex4);
        }
        final TextRange textRange = context.getTextRange();
        Label_0159: {
            Label_0130: {
                try {
                    if (!OCTokenTypes.WHITESPACES.contains(OCElementUtil.getElementType(context))) {
                        break Label_0130;
                    }
                    final TextRange textRange2 = textRange;
                    final int n4 = n;
                    final boolean b2 = textRange2.contains(n4);
                    if (b2) {
                        return n;
                    }
                    break Label_0130;
                }
                catch (IncorrectOperationException ex5) {
                    throw a(ex5);
                }
                try {
                    final TextRange textRange2 = textRange;
                    final int n4 = n;
                    final boolean b2 = textRange2.contains(n4);
                    if (b2) {
                        return n;
                    }
                }
                catch (IncorrectOperationException ex6) {
                    throw a(ex6);
                }
                try {
                    if (!OCElementUtil.isWhitespace(context)) {
                        return textRange.getStartOffset();
                    }
                    final PsiElement psiElement = context;
                    final PsiElement psiElement2 = psiElement.getPrevSibling();
                    final IElementType elementType = OCElementUtil.getElementType(psiElement2);
                    final OCElementType ocElementType = OCTokenTypes.EOL_COMMENT;
                    if (elementType == ocElementType) {
                        break Label_0159;
                    }
                    return textRange.getStartOffset();
                }
                catch (IncorrectOperationException ex7) {
                    throw a(ex7);
                }
            }
            try {
                final PsiElement psiElement = context;
                final PsiElement psiElement2 = psiElement.getPrevSibling();
                final IElementType elementType = OCElementUtil.getElementType(psiElement2);
                final OCElementType ocElementType = OCTokenTypes.EOL_COMMENT;
                if (elementType == ocElementType) {
                    return textRange.getStartOffset() + 1;
                }
            }
            catch (IncorrectOperationException ex8) {
                throw a(ex8);
            }
        }
        return textRange.getStartOffset();
    }
    
    @Nullable
    @Override
    public PsiElement getBestMemberPlace(final OCMemberSymbol ocMemberSymbol) {
        final OCClassSymbol symbol = this.getSymbol();
        try {
            if (symbol == null) {
                return null;
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final OCClassDeclarationBaseImpl.1MyProcessor 1MyProcessor = new OCClassDeclarationBaseImpl.1MyProcessor(ocMemberSymbol);
        symbol.processMembers(ocMemberSymbol.getClass(), (com.intellij.util.Processor<? super OCMemberSymbol>)1MyProcessor);
        return 1MyProcessor.getCandidate();
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
