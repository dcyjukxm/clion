// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

import com.intellij.navigation.ItemPresentation;
import com.intellij.ui.IconDeferrer;
import com.intellij.openapi.util.registry.Registry;
import javax.swing.Icon;
import com.intellij.util.PsiNavigateUtil;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.util.FilteringProcessor;
import com.intellij.util.CommonProcessors;
import com.intellij.util.Processor;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import org.jetbrains.annotations.NonNls;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.types.OCUnknownType;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.symtable.OCGlobalProjectSymbolsCache;
import com.intellij.openapi.util.Condition;
import org.jetbrains.annotations.NotNull;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;

public abstract class OCSymbolBase<T extends PsiElement> implements OCSymbol<T>
{
    private static final String ATTRIBUTE_FORBIDDEN_BY_ARC_MESSAGE = "\"not available in automatic reference counting mode\"";
    @Nullable
    protected transient Project myProject;
    @Nullable
    protected transient VirtualFile myFile;
    protected long myComplexOffset;
    
    public OCSymbolBase(@Nullable final Project myProject, @Nullable final VirtualFile myFile, final long myComplexOffset) {
        this.myProject = myProject;
        this.myFile = myFile;
        this.myComplexOffset = myComplexOffset;
    }
    
    public OCSymbolBase() {
    }
    
    @Nullable
    public static PsiElement locateDefinition(@Nullable final OCSymbol ocSymbol) {
        try {
            if (ocSymbol != null) {
                return ocSymbol.locateDefinition();
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return null;
    }
    
    public static List<PsiElement> locateDefinitions(final List<? extends OCSymbol> list) {
        final ArrayList<PsiElement> list2 = new ArrayList<PsiElement>(list.size());
        final Iterator<? extends OCSymbol> iterator = list.iterator();
        while (iterator.hasNext()) {
            final PsiElement locateDefinition = ((OCSymbol<PsiElement>)iterator.next()).locateDefinition();
            try {
                if (locateDefinition == null) {
                    continue;
                }
                list2.add(locateDefinition);
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
        }
        return list2;
    }
    
    @NotNull
    public static String getSymbolName(@Nullable final OCSymbol ocSymbol) {
        String s = null;
        Label_0019: {
            try {
                if (ocSymbol == null) {
                    final String name;
                    s = (name = "<unnamed>");
                    break Label_0019;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            String name;
            s = (name = ocSymbol.getName());
            try {
                if (name == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolBase", "getSymbolName"));
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        return s;
    }
    
    @NotNull
    public static String getTagOfStructLike(final OCSymbol ocSymbol) {
        final String string = ocSymbol.getKind().getNameLowercase() + " ";
        final String presentableName = ocSymbol.getPresentableName();
        String s4 = null;
        Label_0097: {
            String s3 = null;
            Label_0062: {
                try {
                    if (!presentableName.startsWith(string)) {
                        break Label_0097;
                    }
                    final String s = presentableName;
                    final String s2 = string;
                    final int n = s2.length();
                    s3 = s.substring(n);
                    if (s3 == null) {
                        break Label_0062;
                    }
                    return s3;
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                try {
                    final String s = presentableName;
                    final String s2 = string;
                    final int n = s2.length();
                    s3 = s.substring(n);
                    if (s3 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolBase", "getTagOfStructLike"));
                    }
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
            }
            return s3;
            try {
                s4 = presentableName;
                if (s4 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolBase", "getTagOfStructLike"));
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
        }
        return s4;
    }
    
    @Nullable
    public static <T extends OCSymbol> T findSymbolDefinition(final String s, final OCSymbolKind ocSymbolKind, final Project project, final VirtualFile virtualFile) {
        return findSymbolDefinition(s, ocSymbolKind, project, virtualFile, (com.intellij.openapi.util.Condition<T>)null);
    }
    
    @Nullable
    public static <T extends OCSymbol> T findSymbolDefinition(final String s, final OCSymbolKind ocSymbolKind, final Project project, final VirtualFile virtualFile, @Nullable final Condition<T> condition) {
        return (T)OCGlobalProjectSymbolsCache.findNearestTopLevelSymbol(project, s, (Condition<OCSymbol>)(p2 -> {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_2        
            //     1: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
            //     6: aload_0        
            //     7: if_acmpne       62
            //    10: aload_2        
            //    11: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isPredeclaration:()Z
            //    16: ifne            62
            //    19: goto            26
            //    22: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolBase.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
            //    25: athrow         
            //    26: aload_1        
            //    27: ifnull          54
            //    30: goto            37
            //    33: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolBase.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
            //    36: athrow         
            //    37: aload_1        
            //    38: aload_2        
            //    39: invokeinterface com/intellij/openapi/util/Condition.value:(Ljava/lang/Object;)Z
            //    44: ifeq            62
            //    47: goto            54
            //    50: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolBase.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
            //    53: athrow         
            //    54: iconst_1       
            //    55: goto            63
            //    58: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolBase.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
            //    61: athrow         
            //    62: iconst_0       
            //    63: ireturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                             
            //  -----  -----  -----  -----  ---------------------------------
            //  0      19     22     26     Ljava/lang/IllegalStateException;
            //  10     30     33     37     Ljava/lang/IllegalStateException;
            //  26     47     50     54     Ljava/lang/IllegalStateException;
            //  37     58     58     62     Ljava/lang/IllegalStateException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0026:
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
        }), virtualFile);
    }
    
    public void init(@Nullable final Project myProject, @Nullable final VirtualFile myFile) {
        this.myProject = myProject;
        this.myFile = myFile;
    }
    
    @NotNull
    @Override
    public OCType getType() {
        OCUnknownType instance;
        try {
            instance = OCUnknownType.INSTANCE;
            if (instance == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolBase", "getType"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return instance;
    }
    
    @NotNull
    @Override
    public OCType getResolvedType() {
        OCType resolve;
        try {
            resolve = this.getType().resolve((PsiFile)this.getContainingOCFile());
            if (resolve == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolBase", "getResolvedType"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return resolve;
    }
    
    @Override
    public OCType getResolvedType(final boolean b) {
        return this.getType().resolve((PsiFile)this.getContainingOCFile(), b);
    }
    
    @Override
    public boolean isGlobal() {
        return false;
    }
    
    @Override
    public boolean isTopLevel() {
        return this.isGlobal();
    }
    
    @Override
    public boolean isCallable() {
        return this.getKind().isCallable();
    }
    
    @Override
    public boolean isDefinition() {
        try {
            if (!this.isPredeclaration()) {
                return true;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return false;
    }
    
    @Override
    public boolean isPredeclaration() {
        return this.getKind().isPredeclaration();
    }
    
    @Override
    public boolean isUnnamed() {
        return "<unnamed>".equals(this.getName());
    }
    
    protected Class<? extends T> getPsiElementClass() {
        return (Class<? extends T>)this.getKind().getPsiElementClass();
    }
    
    @Override
    public Object getData(@NonNls final String s) {
        try {
            if (CommonDataKeys.PSI_ELEMENT.is(s)) {
                return new OCSymbolHolderVirtualPsiElement(this);
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return null;
    }
    
    @Nullable
    @Override
    public T locateDefinition() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolBase.getPsiElementClass:()Ljava/lang/Class;
        //     4: astore_1       
        //     5: aload_0        
        //     6: getfield        com/jetbrains/cidr/lang/symbols/OCSymbolBase.myFile:Lcom/intellij/openapi/vfs/VirtualFile;
        //     9: ifnull          43
        //    12: aload_0        
        //    13: getfield        com/jetbrains/cidr/lang/symbols/OCSymbolBase.myProject:Lcom/intellij/openapi/project/Project;
        //    16: ifnull          43
        //    19: goto            26
        //    22: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolBase.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    25: athrow         
        //    26: aload_0        
        //    27: getfield        com/jetbrains/cidr/lang/symbols/OCSymbolBase.myFile:Lcom/intellij/openapi/vfs/VirtualFile;
        //    30: invokevirtual   com/intellij/openapi/vfs/VirtualFile.isValid:()Z
        //    33: ifne            49
        //    36: goto            43
        //    39: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolBase.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    42: athrow         
        //    43: aconst_null    
        //    44: areturn        
        //    45: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolBase.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    48: athrow         
        //    49: aload_0        
        //    50: getfield        com/jetbrains/cidr/lang/symbols/OCSymbolBase.myProject:Lcom/intellij/openapi/project/Project;
        //    53: invokestatic    com/intellij/psi/PsiManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/PsiManager;
        //    56: aload_0        
        //    57: getfield        com/jetbrains/cidr/lang/symbols/OCSymbolBase.myFile:Lcom/intellij/openapi/vfs/VirtualFile;
        //    60: invokevirtual   com/intellij/psi/PsiManager.findFile:(Lcom/intellij/openapi/vfs/VirtualFile;)Lcom/intellij/psi/PsiFile;
        //    63: astore_2       
        //    64: aload_2        
        //    65: ifnonnull       74
        //    68: aconst_null    
        //    69: areturn        
        //    70: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolBase.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    73: athrow         
        //    74: aload_2        
        //    75: aload_0        
        //    76: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolBase.getOffset:()I
        //    79: invokeinterface com/intellij/psi/PsiFile.findElementAt:(I)Lcom/intellij/psi/PsiElement;
        //    84: astore_3       
        //    85: aload_3        
        //    86: ifnonnull       95
        //    89: aconst_null    
        //    90: areturn        
        //    91: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolBase.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    94: athrow         
        //    95: aload_3        
        //    96: invokestatic    com/intellij/psi/util/PsiTreeUtil.getDeepestLast:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //    99: astore_3       
        //   100: aload_3        
        //   101: ifnull          205
        //   104: aload_3        
        //   105: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolOffsetUtil.getComplexOffset:(Lcom/intellij/psi/PsiElement;)J
        //   108: lstore          4
        //   110: aload_0        
        //   111: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolBase.getComplexOffset:()J
        //   114: lstore          6
        //   116: lload           4
        //   118: lload           6
        //   120: lcmp           
        //   121: ifne            178
        //   124: aload_3        
        //   125: aload_1        
        //   126: lload           6
        //   128: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolBase.a:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;J)Lcom/intellij/psi/PsiElement;
        //   131: astore          8
        //   133: aload           8
        //   135: ifnull          145
        //   138: aload           8
        //   140: areturn        
        //   141: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolBase.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   144: athrow         
        //   145: aload_0        
        //   146: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolBase.isUnnamed:()Z
        //   149: ifeq            176
        //   152: aload_3        
        //   153: invokestatic    com/intellij/psi/util/PsiTreeUtil.prevLeaf:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   156: aload_1        
        //   157: lload           6
        //   159: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolBase.a:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;J)Lcom/intellij/psi/PsiElement;
        //   162: astore          9
        //   164: aload           9
        //   166: ifnull          176
        //   169: aload           9
        //   171: areturn        
        //   172: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolBase.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   175: athrow         
        //   176: aconst_null    
        //   177: areturn        
        //   178: lload           4
        //   180: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolOffsetUtil.getTextOffset:(J)I
        //   183: lload           6
        //   185: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolOffsetUtil.getTextOffset:(J)I
        //   188: if_icmpeq       197
        //   191: aconst_null    
        //   192: areturn        
        //   193: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolBase.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   196: athrow         
        //   197: aload_3        
        //   198: invokestatic    com/intellij/psi/util/PsiTreeUtil.prevLeaf:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   201: astore_3       
        //   202: goto            100
        //   205: aconst_null    
        //   206: areturn        
        //    Signature:
        //  ()TT;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  5      19     22     26     Ljava/lang/IllegalStateException;
        //  12     36     39     43     Ljava/lang/IllegalStateException;
        //  26     45     45     49     Ljava/lang/IllegalStateException;
        //  64     70     70     74     Ljava/lang/IllegalStateException;
        //  85     91     91     95     Ljava/lang/IllegalStateException;
        //  133    141    141    145    Ljava/lang/IllegalStateException;
        //  164    172    172    176    Ljava/lang/IllegalStateException;
        //  178    193    193    197    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0026:
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
    
    @Nullable
    private static <T extends PsiElement> T a(@Nullable final PsiElement psiElement, @NotNull final Class<T> clazz, final long n) {
        try {
            if (clazz == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "elementClass", "com/jetbrains/cidr/lang/symbols/OCSymbolBase", "findParentDefinition"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                return null;
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        PsiElement parentOfType = psiElement;
        do {
            if (clazz.isInstance(parentOfType)) {
                try {
                    if (OCSymbolOffsetUtil.getVirtualComplexOffset(parentOfType) == n) {
                        return (T)parentOfType;
                    }
                }
                catch (IllegalStateException ex3) {
                    throw a(ex3);
                }
            }
            parentOfType = PsiTreeUtil.getParentOfType(parentOfType, (Class)clazz, true);
        } while (parentOfType != null);
        return null;
    }
    
    @Override
    public void updateOffset(final int n, final int n2, final int n3) {
        try {
            if (this.getOffset() > n) {
                this.myComplexOffset += n3;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
    }
    
    @NotNull
    @Override
    public String getSignature() {
        String name;
        try {
            name = this.getName();
            if (name == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolBase", "getSignature"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return name;
    }
    
    @Nullable
    @Override
    public TextRange getScope() {
        return null;
    }
    
    @NotNull
    @Override
    public String getPresentableName() {
        String name;
        try {
            name = this.getName();
            if (name == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolBase", "getPresentableName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return name;
    }
    
    @NotNull
    @Override
    public String getPresentableText() {
        String presentableName;
        try {
            presentableName = this.getPresentableName();
            if (presentableName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolBase", "getPresentableText"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return presentableName;
    }
    
    @Nullable
    @Override
    public String getLocationString() {
        try {
            if (this.myFile == null) {
                return null;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return this.myFile.getName();
    }
    
    @Override
    public boolean isSynthetic() {
        try {
            if (this.myProject == null) {
                return true;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return false;
    }
    
    @Override
    public String toString() {
        return this.getKind() + "[" + this.getName() + "]@" + this.getOffset();
    }
    
    @NotNull
    @Override
    public abstract OCSymbolKind getKind();
    
    @NotNull
    @Override
    public String getNameWithKindLowercase() {
        String string;
        try {
            string = this.getKindLowercase() + " '" + this.getPresentableName() + "'";
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolBase", "getNameWithKindLowercase"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return string;
    }
    
    @NotNull
    @Override
    public String getNameWithKindUppercase() {
        String string;
        try {
            string = this.getKindUppercase() + " '" + this.getPresentableName() + "'";
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolBase", "getNameWithKindUppercase"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return string;
    }
    
    @Override
    public String getKindLowercase() {
        return StringUtil.decapitalize(this.getKindUppercase());
    }
    
    @Override
    public String getKindUppercase() {
        return this.getKind().getNameUppercase();
    }
    
    @Nullable
    @Override
    public OCSymbol getDefinitionSymbol() {
        try {
            if (this.isPredeclaration()) {
                return findSymbolDefinition(this.getName(), this.getKind(), this.myProject, this.myFile);
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return this;
    }
    
    @Override
    public boolean processAssociatedSymbols(final Processor<OCSymbol> processor) {
        final OCSymbol associatedSymbol = this.getAssociatedSymbol();
        Label_0026: {
            try {
                if (associatedSymbol == null) {
                    break Label_0026;
                }
                final Processor<OCSymbol> processor2 = processor;
                final OCSymbol ocSymbol = associatedSymbol;
                final boolean b = processor2.process((Object)ocSymbol);
                if (b) {
                    break Label_0026;
                }
                return false;
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            try {
                final Processor<OCSymbol> processor2 = processor;
                final OCSymbol ocSymbol = associatedSymbol;
                final boolean b = processor2.process((Object)ocSymbol);
                if (b) {
                    return true;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    @Nullable
    @Override
    public OCSymbol getAssociatedSymbol() {
        return null;
    }
    
    @Override
    public OCSymbol getFirstPredeclaration() {
        final CommonProcessors.FindFirstProcessor findFirstProcessor = new CommonProcessors.FindFirstProcessor();
        this.processPredeclarations((Processor<OCSymbol>)findFirstProcessor);
        return (OCSymbol)findFirstProcessor.getFoundValue();
    }
    
    @Override
    public boolean processPredeclarations(final Processor<OCSymbol> processor) {
        return this.processSameSymbols((Processor<OCSymbol>)new FilteringProcessor(OCSymbolBase.PREDIFINITION_CONDITION, (Processor)processor));
    }
    
    @Override
    public boolean processSameSymbols(final Processor<OCSymbol> processor) {
        return OCGlobalProjectSymbolsCache.processTopLevelSymbols(this.myProject, (Processor<OCSymbol>)(ocSymbol -> {
            Label_0028: {
                try {
                    if (ocSymbol.getClass() != this.getClass()) {
                        break Label_0028;
                    }
                    final Processor processor2 = processor;
                    final OCSymbol ocSymbol2 = ocSymbol;
                    final boolean b = processor2.process((Object)ocSymbol2);
                    if (b) {
                        break Label_0028;
                    }
                    return false;
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                try {
                    final Processor processor2 = processor;
                    final OCSymbol ocSymbol2 = ocSymbol;
                    final boolean b = processor2.process((Object)ocSymbol2);
                    if (b) {
                        return true;
                    }
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
            }
            return false;
        }), this.getName());
    }
    
    @Nullable
    public VirtualFile getContainingFile() {
        return this.myFile;
    }
    
    @Nullable
    @Override
    public PsiFile getContainingPsiFile() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolBase.getContainingFile:()Lcom/intellij/openapi/vfs/VirtualFile;
        //     4: astore_1       
        //     5: aload_0        
        //     6: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolBase.getProject:()Lcom/intellij/openapi/project/Project;
        //     9: astore_2       
        //    10: aload_2        
        //    11: ifnull          52
        //    14: aload_1        
        //    15: ifnull          52
        //    18: goto            25
        //    21: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolBase.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    24: athrow         
        //    25: aload_1        
        //    26: invokevirtual   com/intellij/openapi/vfs/VirtualFile.isValid:()Z
        //    29: ifeq            52
        //    32: goto            39
        //    35: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolBase.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    38: athrow         
        //    39: aload_2        
        //    40: invokestatic    com/intellij/psi/PsiManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/PsiManager;
        //    43: aload_1        
        //    44: invokevirtual   com/intellij/psi/PsiManager.findFile:(Lcom/intellij/openapi/vfs/VirtualFile;)Lcom/intellij/psi/PsiFile;
        //    47: areturn        
        //    48: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolBase.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    51: athrow         
        //    52: aconst_null    
        //    53: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  10     18     21     25     Ljava/lang/IllegalStateException;
        //  14     32     35     39     Ljava/lang/IllegalStateException;
        //  25     48     48     52     Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0025:
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
    
    @Nullable
    @Override
    public OCFile getContainingOCFile() {
        final PsiFile containingPsiFile = this.getContainingPsiFile();
        try {
            if (containingPsiFile instanceof OCFile) {
                return (OCFile)containingPsiFile;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return null;
    }
    
    @Override
    public int getOffset() {
        return OCSymbolOffsetUtil.getTextOffset(this.myComplexOffset);
    }
    
    @Override
    public long getComplexOffset() {
        return this.myComplexOffset;
    }
    
    @Deprecated
    public PsiElement getTargetElement() {
        return (PsiElement)new OCSymbolHolderVirtualPsiElement(this);
    }
    
    @Nullable
    public Project getProject() {
        return this.myProject;
    }
    
    public void navigate(final boolean b) {
        PsiNavigateUtil.navigate(this.locateDefinition());
    }
    
    public boolean canNavigate() {
        try {
            if (this.locateDefinition() != null) {
                return true;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return false;
    }
    
    public boolean canNavigateToSource() {
        return this.canNavigate();
    }
    
    @Nullable
    @Override
    public Icon getIcon() {
        try {
            if (Registry.is("psi.deferIconLoading")) {
                return IconDeferrer.getInstance().defer(this.getBaseIcon(), (Object)this, ocSymbolBase -> this.computeFullIconNow(null));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return this.computeFullIconNow(null);
    }
    
    @Nullable
    @Override
    public Icon getBaseIcon() {
        return this.getKind().getIcon();
    }
    
    @Nullable
    @Override
    public Icon computeFullIconNow(@Nullable final T t) {
        return this.getBaseIcon();
    }
    
    public ItemPresentation getPresentation() {
        return (ItemPresentation)new ItemPresentation() {
            public String getPresentableText() {
                return OCSymbolBase.this.getPresentableText();
            }
            
            public String getLocationString() {
                return OCSymbolBase.this.getLocationString();
            }
            
            public Icon getIcon(final boolean b) {
                return OCSymbolBase.this.getIcon();
            }
        };
    }
    
    @Override
    public boolean isSameSymbol(@Nullable final OCSymbol ocSymbol) {
        return this.equals(ocSymbol);
    }
    
    @Override
    public int hashCode() {
        return 31 * this.hashCodeExcludingOffset() + this.getOffset();
    }
    
    public int compareTo(final OCSymbol ocSymbol) {
        return this.getPresentableName().compareTo(ocSymbol.getPresentableName());
    }
    
    @Override
    public OCType getEffectiveType() {
        return this.getType();
    }
    
    @NotNull
    @Override
    public OCType getEffectiveResolvedType() {
        OCType resolvedType;
        try {
            resolvedType = this.getResolvedType();
            if (resolvedType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolBase", "getEffectiveResolvedType"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return resolvedType;
    }
    
    @Override
    public boolean isDeprecated() {
        return this.hasAttribute("deprecated");
    }
    
    @Override
    public String getDeprecatedMessage() {
        final String string = this.getNameWithKindUppercase() + " is deprecated";
        final String attributeParameters = this.getAttributeParameters("deprecated");
        try {
            if (attributeParameters != null) {
                return string + ": " + StringUtil.unquoteString(attributeParameters);
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return string;
    }
    
    @Override
    public boolean isUnavailable() {
        Label_0030: {
            try {
                if (!this.hasAttribute("unavailable")) {
                    return false;
                }
                final String s = "\"not available in automatic reference counting mode\"";
                final OCSymbolBase ocSymbolBase = this;
                final String s2 = "unavailable";
                final String s3 = ocSymbolBase.getAttributeParameters(s2);
                final boolean b = s.equals(s3);
                if (!b) {
                    break Label_0030;
                }
                return false;
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            try {
                final String s = "\"not available in automatic reference counting mode\"";
                final OCSymbolBase ocSymbolBase = this;
                final String s2 = "unavailable";
                final String s3 = ocSymbolBase.getAttributeParameters(s2);
                final boolean b = s.equals(s3);
                if (!b) {
                    return true;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    @Override
    public String getUnavailableMessage() {
        final String string = this.getNameWithKindUppercase() + " is unavailable";
        final String attributeParameters = this.getAttributeParameters("unavailable");
        try {
            if (attributeParameters != null) {
                return string + ": " + StringUtil.unquoteString(attributeParameters);
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return string;
    }
    
    @Override
    public boolean isForbiddenByARC(@NotNull final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "context"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/symbols/OCSymbolBase"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isForbiddenByARC"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolBase.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    43: athrow         
        //    44: aload_0        
        //    45: ldc             "unavailable"
        //    47: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolBase.hasAttribute:(Ljava/lang/String;)Z
        //    50: ifeq            101
        //    53: ldc             "\"not available in automatic reference counting mode\""
        //    55: aload_0        
        //    56: ldc             "unavailable"
        //    58: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolBase.getAttributeParameters:(Ljava/lang/String;)Ljava/lang/String;
        //    61: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    64: ifeq            101
        //    67: goto            74
        //    70: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolBase.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    73: athrow         
        //    74: aload_1        
        //    75: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //    80: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.isArcEnabled:(Lcom/intellij/psi/PsiFile;)Z
        //    83: ifeq            101
        //    86: goto            93
        //    89: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolBase.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    92: athrow         
        //    93: iconst_1       
        //    94: goto            102
        //    97: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolBase.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   100: athrow         
        //   101: iconst_0       
        //   102: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      40     40     44     Ljava/lang/IllegalStateException;
        //  44     67     70     74     Ljava/lang/IllegalStateException;
        //  53     86     89     93     Ljava/lang/IllegalStateException;
        //  74     97     97     101    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0074:
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
    public boolean isTransparentUnion() {
        return this.hasAttribute("transparent_union");
    }
    
    @Override
    public boolean hasAttribute(final String s) {
        final List<String> attributes = this.getAttributes();
        Label_0055: {
            try {
                if (attributes.contains(s)) {
                    break Label_0055;
                }
                final List<String> list = attributes;
                final StringBuilder sb = new StringBuilder();
                final String s2 = "__";
                final StringBuilder sb2 = sb.append(s2);
                final String s3 = s;
                final StringBuilder sb3 = sb2.append(s3);
                final String s4 = "__";
                final StringBuilder sb4 = sb3.append(s4);
                final String s5 = sb4.toString();
                final boolean b = list.contains(s5);
                if (b) {
                    break Label_0055;
                }
                return false;
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            try {
                final List<String> list = attributes;
                final StringBuilder sb = new StringBuilder();
                final String s2 = "__";
                final StringBuilder sb2 = sb.append(s2);
                final String s3 = s;
                final StringBuilder sb3 = sb2.append(s3);
                final String s4 = "__";
                final StringBuilder sb4 = sb3.append(s4);
                final String s5 = sb4.toString();
                final boolean b = list.contains(s5);
                if (b) {
                    return true;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    @Nullable
    @Override
    public String getAttributeParameters(final String s) {
        for (final String s2 : this.getAttributes()) {
            try {
                if (s2.startsWith(s + '#')) {
                    return s2.substring(s.length() + 1);
                }
                continue;
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
        }
        return null;
    }
    
    @NotNull
    @Override
    public OCSymbol getDelegate() {
        try {
            if (this == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/OCSymbolBase", "getDelegate"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return this;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
