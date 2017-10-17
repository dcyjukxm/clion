// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import com.jetbrains.cidr.lang.types.OCObjectType;
import java.util.Iterator;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import java.util.ArrayDeque;
import com.jetbrains.cidr.lang.symbols.objc.OCProtocolSymbol;
import com.jetbrains.cidr.lang.types.OCTypeArgument;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.types.OCVoidType;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.objc.OCImplementationSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCInterfaceSymbol;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import com.intellij.psi.util.CachedValueProvider;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;
import java.util.Collections;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.types.OCArrayType;
import com.intellij.psi.util.CachedValuesManager;
import com.intellij.openapi.util.UserDataHolderEx;
import com.jetbrains.cidr.lang.symbols.symtable.FileSymbolTablesCache;
import com.intellij.psi.util.PsiModificationTracker;
import com.jetbrains.cidr.lang.symbols.OCTypeParameterSymbol;
import com.jetbrains.cidr.lang.symbols.symtable.OCFileSymbols;
import java.util.Collection;
import com.jetbrains.cidr.lang.symbols.OCSymbolReference;
import com.jetbrains.cidr.lang.types.OCUnknownType;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.jetbrains.cidr.lang.types.OCAutoType;
import java.util.List;
import com.jetbrains.cidr.lang.resolve.OCArgumentsList;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.types.OCStructType;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.psi.OCCodeFragment;
import java.util.HashSet;
import java.util.Comparator;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.OCInternator;
import java.util.Map;
import com.intellij.psi.util.CachedValue;
import com.intellij.openapi.util.Key;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.Set;

public class OCTypeResolveVisitor extends OCNonPrimitiveTypeCloneVisitor
{
    private static final int MAX_TYPES_TO_RESOLVE_PER_CONTEXT = 10000;
    private final boolean myResolveIgnoreImports;
    private final boolean myIsInOldC;
    private Set<VirtualFile> myUsedFiles;
    private OCFile myFile;
    @NotNull
    private final OCResolveContext myContext;
    private int myDepth;
    private int myMaxDepth;
    private static final Key<CachedValue<Map<TypeKey, ResultInfo>>> RESOLVE_CACHE_LOCAL;
    private static final Key<CachedValue<Map<TypeKey, ResultInfo>>> RESOLVE_CACHE_GLOBAL;
    private static final OCInternator<ArrayList<VirtualFile>> USED_FILE_LIST_INTERNATOR;
    private static final Comparator<VirtualFile> FILE_PATH_COMPARATOR;
    
    public OCTypeResolveVisitor(@NotNull final OCResolveContext myContext, final boolean myResolveIgnoreImports) {
        if (myContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor", "<init>"));
        }
        this.myUsedFiles = new HashSet<VirtualFile>();
        this.myMaxDepth = 256;
        final PsiFile file = myContext.getFile();
        boolean myIsInOldC = false;
        Label_0136: {
            Label_0127: {
                Label_0087: {
                    try {
                        if (file instanceof OCFile) {
                            final OCFile myFile = (OCFile)file;
                            break Label_0087;
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw b(ex);
                    }
                    final OCFile myFile = null;
                    try {
                        this.myFile = myFile;
                        this.myResolveIgnoreImports = myResolveIgnoreImports;
                        this.myContext = myContext;
                        if (this.myFile == null) {
                            break Label_0127;
                        }
                        final OCTypeResolveVisitor ocTypeResolveVisitor = this;
                        final OCFile ocFile = ocTypeResolveVisitor.myFile;
                        final boolean b = ocFile.isCpp();
                        if (!b) {
                            break Label_0127;
                        }
                        break Label_0127;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw b(ex2);
                    }
                }
                try {
                    final OCTypeResolveVisitor ocTypeResolveVisitor = this;
                    final OCFile ocFile = ocTypeResolveVisitor.myFile;
                    final boolean b = ocFile.isCpp();
                    if (!b) {
                        myIsInOldC = true;
                        break Label_0136;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw b(ex3);
                }
            }
            myIsInOldC = false;
        }
        this.myIsInOldC = myIsInOldC;
        if (this.myFile instanceof OCCodeFragment) {
            final PsiElement context = this.myFile.getContext();
            OCFile myFile2 = null;
            Label_0184: {
                try {
                    if (context != null) {
                        myFile2 = (OCFile)context.getContainingFile();
                        break Label_0184;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw b(ex4);
                }
                myFile2 = null;
            }
            this.myFile = myFile2;
        }
    }
    
    public OCTypeResolveVisitor(@Nullable final PsiFile psiFile, final boolean b) {
        this(new OCResolveContext((PsiElement)psiFile), b);
    }
    
    public OCTypeResolveVisitor(@NotNull final OCResolveContext ocResolveContext) {
        if (ocResolveContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor", "<init>"));
        }
        this(ocResolveContext, false);
    }
    
    @Override
    public OCType visitStructType(final OCStructType ocStructType) {
        return ocStructType;
    }
    
    @Override
    public OCType visitFunctionType(final OCFunctionType ocFunctionType) {
        final List<String> parameterNames = ocFunctionType.getParameterNames(true);
        final OCFunctionType ocFunctionType2 = (OCFunctionType)super.visitFunctionType(new OCFunctionType(ocFunctionType.getReturnType(), OCArgumentsList.expandVariadicTypes(ocFunctionType.getParameterTypes(true), this.myContext), null, ocFunctionType.isConst(), ocFunctionType.isVolatile(), ocFunctionType.isLValueRef(), ocFunctionType.isRValueRef()));
        final List<OCType> parameterTypes = ocFunctionType2.getParameterTypes(true);
        List<String> list = null;
        if (parameterNames != null) {
            list = new ArrayList<String>();
            int n = 0;
            while (true) {
                List<String> list2 = null;
                String s = null;
                Label_0135: {
                    Label_0116: {
                        try {
                            if (n >= parameterTypes.size()) {
                                break;
                            }
                            list2 = list;
                            final int n2 = n;
                            final List<String> list3 = parameterNames;
                            final int n3 = list3.size();
                            if (n2 < n3) {
                                break Label_0116;
                            }
                            break Label_0116;
                        }
                        catch (IllegalArgumentException ex) {
                            throw b(ex);
                        }
                        try {
                            list2 = list;
                            final int n2 = n;
                            final List<String> list3 = parameterNames;
                            final int n3 = list3.size();
                            if (n2 < n3) {
                                s = parameterNames.get(n);
                                break Label_0135;
                            }
                        }
                        catch (IllegalArgumentException ex2) {
                            throw b(ex2);
                        }
                    }
                    s = null;
                }
                list2.add(s);
                ++n;
            }
        }
        return new OCFunctionType(ocFunctionType2.getReturnType(), parameterTypes, list, ocFunctionType2.isConst(), ocFunctionType2.isVolatile(), ocFunctionType2.isLValueRef(), ocFunctionType2.isRValueRef());
    }
    
    @Override
    public OCType visitAutoType(final OCAutoType ocAutoType) {
        return this.getCachedOrResolve(ocAutoType, this.a(ocAutoType), true).resolvedType;
    }
    
    @Override
    public OCType visitReferenceType(final OCReferenceType ocReferenceType) {
        try {
            if (ocReferenceType.getReference((PsiFile)this.myFile).getQualifiedName().getName() == null) {
                return OCUnknownType.INSTANCE;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (this.myFile == null) {
                return ocReferenceType;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (this.myDepth > this.myMaxDepth) {
                return ocReferenceType;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        ++this.myDepth;
        final TypeKey a = this.a(ocReferenceType);
        ResultInfo cachedOrResolve = null;
        Label_0162: {
            Label_0110: {
                try {
                    if (this.myResolveIgnoreImports || !(ocReferenceType.getReference((PsiFile)this.myFile) instanceof OCSymbolReference.GlobalReference)) {
                        break Label_0110;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw b(ex4);
                }
                cachedOrResolve = this.getCachedOrResolve(ocReferenceType, a, false);
                break Label_0162;
            }
            final OCType a2 = this.a(ocReferenceType, a);
            final Set<OCTypeParameterSymbol> typeDependencies = this.myContext.getTypeDependencies();
            OCType ocType = null;
            Set<VirtualFile> myUsedFiles = null;
            Set<OCTypeParameterSymbol> set = null;
            Label_0157: {
                try {
                    ocType = a2;
                    myUsedFiles = this.myUsedFiles;
                    if (!typeDependencies.isEmpty()) {
                        set = typeDependencies;
                        break Label_0157;
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw b(ex5);
                }
                set = null;
            }
            cachedOrResolve = new ResultInfo(ocType, (Collection)myUsedFiles, (Set)set);
        }
        --this.myDepth;
        OCFileSymbols.markImportsNeeded(this.myFile, cachedOrResolve.usedFiles);
        final OCType guessedType = ocReferenceType.getGuessedType();
        try {
            if (guessedType != ocReferenceType) {
                return cachedOrResolve.resolvedType.cloneWithGuessedType(guessedType.resolve((PsiFile)this.myFile));
            }
        }
        catch (IllegalArgumentException ex6) {
            throw b(ex6);
        }
        return cachedOrResolve.resolvedType;
    }
    
    protected ResultInfo getCachedOrResolve(final OCType ocType, final TypeKey typeKey, final boolean b) {
        Key<CachedValue<Map<TypeKey, ResultInfo>>> key = null;
        Label_0017: {
            try {
                if (b) {
                    key = OCTypeResolveVisitor.RESOLVE_CACHE_LOCAL;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            key = OCTypeResolveVisitor.RESOLVE_CACHE_GLOBAL;
        }
        final Key<CachedValue<Map<TypeKey, ResultInfo>>> key2 = key;
        CachedValue cachedValue = (CachedValue)this.myFile.getUserData((Key)key2);
        Object o = null;
        Label_0064: {
            try {
                if (b) {
                    o = PsiModificationTracker.MODIFICATION_COUNT;
                    break Label_0064;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            o = FileSymbolTablesCache.getInstance(this.myFile.getProject()).getOutOfBlockModificationTracker();
        }
        final Object o2 = o;
        if (cachedValue == null) {
            cachedValue = (CachedValue)((UserDataHolderEx)this.myFile).putUserDataIfAbsent((Key)key2, (Object)CachedValuesManager.getManager(this.myFile.getProject()).createCachedValue(() -> new CachedValueProvider.Result((Object)ContainerUtil.newConcurrentMap(), new Object[] { o2 }), false));
        }
        final Map map = (Map)cachedValue.getValue();
        final ResultInfo resultInfo = map.get(typeKey);
        ResultInfo resultInfo2;
        if (resultInfo == null) {
            final Set<OCTypeParameterSymbol> typeDependencies = this.myContext.getTypeDependencies();
            this.myContext.clearTypeDependencies();
            OCType ocType2;
            if (ocType instanceof OCReferenceType) {
                final OCReferenceType ocReferenceType = (OCReferenceType)ocType;
                ocType2 = this.a(ocReferenceType, typeKey);
                Label_0215: {
                    try {
                        if (!(ocType2 instanceof OCArrayType) || !ocReferenceType.isFunctionParameterType()) {
                            break Label_0215;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw b(ex3);
                    }
                    ocType2 = OCFunctionType.convertArrayParameterType(ocType2);
                }
            }
            else {
                ocType2 = this.doResolve((OCAutoType)ocType, typeKey);
            }
            final Set<OCTypeParameterSymbol> typeDependencies2 = this.myContext.getTypeDependencies();
            Set<OCTypeParameterSymbol> set = null;
            Label_0258: {
                try {
                    if (!typeDependencies2.isEmpty()) {
                        set = typeDependencies2;
                        break Label_0258;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw b(ex4);
                }
                set = null;
            }
            resultInfo2 = new ResultInfo(ocType2, (Collection)this.a(), (Set)set);
            Label_0310: {
                try {
                    this.myContext.addTypeDependencies(typeDependencies);
                    if (ocType2 instanceof OCUnknownType) {
                        return resultInfo2;
                    }
                    final OCType ocType3 = ocType2;
                    final boolean b2 = ocType3 instanceof OCReferenceType;
                    if (!b2) {
                        break Label_0310;
                    }
                    return resultInfo2;
                }
                catch (IllegalArgumentException ex5) {
                    throw b(ex5);
                }
                try {
                    final OCType ocType3 = ocType2;
                    final boolean b2 = ocType3 instanceof OCReferenceType;
                    if (!b2) {
                        map.put(typeKey, resultInfo2);
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw b(ex6);
                }
            }
        }
        else {
            resultInfo2 = resultInfo;
            try {
                ContainerUtil.addAllNotNull((Collection)this.myUsedFiles, (Iterable)resultInfo.usedFiles);
                if (resultInfo.typeDependencies != null) {
                    this.myContext.addTypeDependencies(resultInfo.typeDependencies);
                }
            }
            catch (IllegalArgumentException ex7) {
                throw b(ex7);
            }
        }
        return resultInfo2;
    }
    
    @NotNull
    private TypeKey a(final OCType ocType) {
        OCTypeSubstitution ocTypeSubstitution = this.myContext.getSubstitution();
        if (!(ocType instanceof OCAutoType)) {
            ocTypeSubstitution = ocTypeSubstitution.getMinimalDependentSubstitution(ocType, this.myContext);
        }
        TypeKey typeKey;
        try {
            typeKey = new TypeKey(ocType, ocTypeSubstitution, this.myContext.isInSFINAE());
            if (typeKey == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor", "getTypeKey"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return typeKey;
    }
    
    @NotNull
    private Collection<VirtualFile> a() {
        Label_0061: {
            List<VirtualFile> list = null;
            Label_0026: {
                try {
                    if (!this.myUsedFiles.isEmpty()) {
                        break Label_0061;
                    }
                    list = Collections.emptyList();
                    if (list == null) {
                        break Label_0026;
                    }
                    return list;
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                try {
                    list = Collections.emptyList();
                    if (list == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor", "internedUsedFilesCopy"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
            }
            return list;
        }
        final ArrayList<Object> list2 = new ArrayList<Object>(this.myUsedFiles);
        ArrayList<VirtualFile> list3;
        try {
            Collections.sort(list2, (Comparator<? super Object>)OCTypeResolveVisitor.FILE_PATH_COMPARATOR);
            list3 = OCTypeResolveVisitor.USED_FILE_LIST_INTERNATOR.intern((ArrayList<VirtualFile>)list2);
            if (list3 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor", "internedUsedFilesCopy"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        return list3;
    }
    
    private OCType a(final OCReferenceType p0, final TypeKey p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //     4: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getTypeResolveCounter:()I
        //     7: istore_3       
        //     8: aload_0        
        //     9: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    12: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.incTypeResolveCounter:()V
        //    15: aload_1        
        //    16: aload_0        
        //    17: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.myFile:Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    20: invokevirtual   com/jetbrains/cidr/lang/types/OCReferenceType.getReference:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;
        //    23: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolReference.getQualifiedName:()Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //    26: astore          4
        //    28: iload_3        
        //    29: sipush          10000
        //    32: if_icmple       48
        //    35: new             Lcom/jetbrains/cidr/lang/types/OCMagicType;
        //    38: dup            
        //    39: aload_1        
        //    40: invokespecial   com/jetbrains/cidr/lang/types/OCMagicType.<init>:(Lcom/jetbrains/cidr/lang/types/OCType;)V
        //    43: areturn        
        //    44: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    47: athrow         
        //    48: new             Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver;
        //    51: dup            
        //    52: aload_0        
        //    53: aload_1        
        //    54: aconst_null    
        //    55: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.<init>:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;Lcom/jetbrains/cidr/lang/types/OCReferenceType;Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$1;)V
        //    58: astore          5
        //    60: aload_1        
        //    61: aload_0        
        //    62: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.myFile:Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    65: invokevirtual   com/jetbrains/cidr/lang/types/OCReferenceType.getCanonicalName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //    68: astore          6
        //    70: aload           6
        //    72: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolReference.removeTypeToken:(Ljava/lang/String;)Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference$NameWithToken;
        //    75: astore          7
        //    77: aload           7
        //    79: getfield        com/jetbrains/cidr/lang/symbols/OCSymbolReference$NameWithToken.typeToken:Ljava/lang/String;
        //    82: ifnull          105
        //    85: aload           5
        //    87: aload           7
        //    89: getfield        com/jetbrains/cidr/lang/symbols/OCSymbolReference$NameWithToken.typeToken:Ljava/lang/String;
        //    92: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolKind.parse:(Ljava/lang/String;)Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    95: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.setPreferableKind:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)V
        //    98: goto            105
        //   101: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   104: athrow         
        //   105: aload_0        
        //   106: aload_2        
        //   107: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.b:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$TypeKey;)Z
        //   110: ifeq            319
        //   113: aload           4
        //   115: invokevirtual   com/jetbrains/cidr/lang/symbols/OCQualifiedName.getQualifier:()Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   118: ifnonnull       208
        //   121: goto            128
        //   124: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   127: athrow         
        //   128: ldc             "id"
        //   130: aload           4
        //   132: invokevirtual   com/jetbrains/cidr/lang/symbols/OCQualifiedName.getName:()Ljava/lang/String;
        //   135: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   138: ifeq            208
        //   141: goto            148
        //   144: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   147: athrow         
        //   148: new             Lcom/jetbrains/cidr/lang/types/OCIdType;
        //   151: dup            
        //   152: aload           5
        //   154: iconst_0       
        //   155: iconst_1       
        //   156: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.getProtocols:(ZZ)Ljava/util/List;
        //   159: aload           5
        //   161: iconst_0       
        //   162: iconst_0       
        //   163: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.getProtocols:(ZZ)Ljava/util/List;
        //   166: aload_0        
        //   167: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.myFile:Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   170: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getProject:()Lcom/intellij/openapi/project/Project;
        //   175: iconst_0       
        //   176: iconst_0       
        //   177: invokespecial   com/jetbrains/cidr/lang/types/OCIdType.<init>:(Ljava/util/List;Ljava/util/List;Lcom/intellij/openapi/project/Project;ZZ)V
        //   180: astore          8
        //   182: aload           8
        //   184: aload_1        
        //   185: invokevirtual   com/jetbrains/cidr/lang/types/OCReferenceType.getNullability:()Lcom/jetbrains/cidr/lang/types/OCNullability;
        //   188: invokevirtual   com/jetbrains/cidr/lang/types/OCIdType.attachNullability:(Lcom/jetbrains/cidr/lang/types/OCNullability;)V
        //   191: aload           8
        //   193: aload_1        
        //   194: invokevirtual   com/jetbrains/cidr/lang/types/OCReferenceType.getARCAttribute:()Lcom/jetbrains/cidr/lang/types/ARCAttribute;
        //   197: aconst_null    
        //   198: aload_1        
        //   199: invokevirtual   com/jetbrains/cidr/lang/types/OCReferenceType.getNullability:()Lcom/jetbrains/cidr/lang/types/OCNullability;
        //   202: iconst_0       
        //   203: iconst_0       
        //   204: invokestatic    com/jetbrains/cidr/lang/types/OCPointerType.to:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/ARCAttribute;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCNullability;ZZ)Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   207: areturn        
        //   208: aload_0        
        //   209: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.myResolveIgnoreImports:Z
        //   212: ifne            252
        //   215: aload_0        
        //   216: aload_1        
        //   217: aload           5
        //   219: iconst_0       
        //   220: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.a:(Lcom/jetbrains/cidr/lang/types/OCReferenceType;Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver;Z)Z
        //   223: ifne            268
        //   226: goto            233
        //   229: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   232: athrow         
        //   233: aload_0        
        //   234: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.myFile:Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   237: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.isInLibraries:()Z
        //   242: ifeq            268
        //   245: goto            252
        //   248: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   251: athrow         
        //   252: aload_0        
        //   253: aload_1        
        //   254: aload           5
        //   256: iconst_1       
        //   257: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.a:(Lcom/jetbrains/cidr/lang/types/OCReferenceType;Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver;Z)Z
        //   260: pop            
        //   261: goto            268
        //   264: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   267: athrow         
        //   268: aload           5
        //   270: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.getAnswer:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   273: astore          8
        //   275: aload           8
        //   277: ifnull          298
        //   280: aload           8
        //   282: aload_1        
        //   283: invokevirtual   com/jetbrains/cidr/lang/types/OCReferenceType.getCVQualifiers:()Lcom/jetbrains/cidr/lang/types/CVQualifiers;
        //   286: aload_0        
        //   287: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   290: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getProject:()Lcom/intellij/openapi/project/Project;
        //   293: invokevirtual   com/jetbrains/cidr/lang/types/OCType.cloneWithAddedCVQualifiers:(Lcom/jetbrains/cidr/lang/types/CVQualifiers;Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   296: astore          8
        //   298: aload_0        
        //   299: aload_2        
        //   300: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.c:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$TypeKey;)V
        //   303: aload           8
        //   305: ifnull          317
        //   308: aload           8
        //   310: goto            318
        //   313: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   316: athrow         
        //   317: aload_1        
        //   318: areturn        
        //   319: getstatic       com/jetbrains/cidr/lang/types/OCUnknownType.INSTANCE:Lcom/jetbrains/cidr/lang/types/OCUnknownType;
        //   322: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  28     44     44     48     Ljava/lang/IllegalArgumentException;
        //  77     98     101    105    Ljava/lang/IllegalArgumentException;
        //  105    121    124    128    Ljava/lang/IllegalArgumentException;
        //  113    141    144    148    Ljava/lang/IllegalArgumentException;
        //  208    226    229    233    Ljava/lang/IllegalArgumentException;
        //  215    245    248    252    Ljava/lang/IllegalArgumentException;
        //  233    261    264    268    Ljava/lang/IllegalArgumentException;
        //  298    313    313    317    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0233:
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
    
    protected OCType doResolve(final OCAutoType p0, final TypeKey p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //     4: aload_1        
        //     5: invokevirtual   com/jetbrains/cidr/lang/types/OCAutoType.getSubstitution:()Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;
        //     8: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.substituteFirst:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;)Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    11: astore_3       
        //    12: aload_1        
        //    13: invokevirtual   com/jetbrains/cidr/lang/types/OCAutoType.needsAutoParamsResolving:()Z
        //    16: ifeq            25
        //    19: aload_1        
        //    20: areturn        
        //    21: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    24: athrow         
        //    25: aload_0        
        //    26: aload_2        
        //    27: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.b:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$TypeKey;)Z
        //    30: ifne            41
        //    33: getstatic       com/jetbrains/cidr/lang/types/OCUnknownType.INSTANCE:Lcom/jetbrains/cidr/lang/types/OCUnknownType;
        //    36: areturn        
        //    37: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    40: athrow         
        //    41: aload_1        
        //    42: invokevirtual   com/jetbrains/cidr/lang/types/OCAutoType.getExpressionSymbol:()Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //    45: astore          4
        //    47: aload_1        
        //    48: invokevirtual   com/jetbrains/cidr/lang/types/OCAutoType.getExpressionElement:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    51: astore          5
        //    53: aload_1        
        //    54: invokevirtual   com/jetbrains/cidr/lang/types/OCAutoType.getLambdaExpression:()Lcom/jetbrains/cidr/lang/psi/OCLambdaExpression;
        //    57: ifnull          170
        //    60: aload_1        
        //    61: invokevirtual   com/jetbrains/cidr/lang/types/OCAutoType.getLambdaExpression:()Lcom/jetbrains/cidr/lang/psi/OCLambdaExpression;
        //    64: iconst_0       
        //    65: aload_0        
        //    66: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    69: invokestatic    com/jetbrains/cidr/lang/util/OCExpectedTypeUtil.getExpectedTypes:(Lcom/jetbrains/cidr/lang/psi/OCExpression;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Ljava/util/Collection;
        //    72: astore          7
        //    74: aload           7
        //    76: getstatic       com/jetbrains/cidr/lang/types/OCUnknownType.INSTANCE:Lcom/jetbrains/cidr/lang/types/OCUnknownType;
        //    79: invokestatic    com/intellij/util/containers/ContainerUtil.getFirstItem:(Ljava/util/Collection;Ljava/lang/Object;)Ljava/lang/Object;
        //    82: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //    85: aload_0        
        //    86: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    89: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //    92: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getTerminalType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    95: astore          8
        //    97: aload           8
        //    99: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   102: ifeq            163
        //   105: aload           8
        //   107: checkcast       Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   110: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.getParameterTypes:()Ljava/util/List;
        //   113: astore          9
        //   115: aload_1        
        //   116: invokevirtual   com/jetbrains/cidr/lang/types/OCAutoType.getParameterIndex:()I
        //   119: aload           9
        //   121: invokeinterface java/util/List.size:()I
        //   126: if_icmpge       163
        //   129: aload           9
        //   131: aload_1        
        //   132: invokevirtual   com/jetbrains/cidr/lang/types/OCAutoType.getParameterIndex:()I
        //   135: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   140: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   143: astore          6
        //   145: aload           6
        //   147: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getTerminalType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   150: instanceof      Lcom/jetbrains/cidr/lang/types/OCAutoType;
        //   153: ifne            163
        //   156: aload           6
        //   158: areturn        
        //   159: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   162: athrow         
        //   163: aload_0        
        //   164: aload_2        
        //   165: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.c:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$TypeKey;)V
        //   168: aload_1        
        //   169: areturn        
        //   170: aload           4
        //   172: instanceof      Lcom/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol;
        //   175: ifeq            240
        //   178: aload           4
        //   180: checkcast       Lcom/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol;
        //   183: invokevirtual   com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol.getFunctionSymbol:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   186: astore          7
        //   188: aload           7
        //   190: ifnull          240
        //   193: aload           7
        //   195: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isConstexpr:()Z
        //   198: ifeq            240
        //   201: goto            208
        //   204: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   207: athrow         
        //   208: aload_1        
        //   209: invokevirtual   com/jetbrains/cidr/lang/types/OCAutoType.getIncompleteType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   212: ifnull          240
        //   215: goto            222
        //   218: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   221: athrow         
        //   222: aload_0        
        //   223: aload_2        
        //   224: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.c:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$TypeKey;)V
        //   227: aload_1        
        //   228: invokevirtual   com/jetbrains/cidr/lang/types/OCAutoType.getIncompleteType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   231: aload_3        
        //   232: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   235: areturn        
        //   236: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   239: athrow         
        //   240: aload           5
        //   242: ifnull          290
        //   245: aload           5
        //   247: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.isValid:()Z
        //   252: ifeq            290
        //   255: goto            262
        //   258: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   261: athrow         
        //   262: aload           5
        //   264: instanceof      Lcom/jetbrains/cidr/lang/psi/OCLambdaExpression;
        //   267: ifne            290
        //   270: goto            277
        //   273: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   276: athrow         
        //   277: aload           5
        //   279: aload_3        
        //   280: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   285: astore          6
        //   287: goto            398
        //   290: aload           5
        //   292: ifnull          377
        //   295: aload           5
        //   297: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.isValid:()Z
        //   302: ifne            377
        //   305: goto            312
        //   308: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   311: athrow         
        //   312: aload_0        
        //   313: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   316: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getFile:()Lcom/intellij/psi/PsiFile;
        //   319: astore          7
        //   321: aload           7
        //   323: ifnull          369
        //   326: getstatic       com/jetbrains/cidr/lang/OCLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //   329: ldc             "Invalid PSI context in auto type"
        //   331: iconst_1       
        //   332: anewarray       Lcom/intellij/openapi/diagnostic/Attachment;
        //   335: dup            
        //   336: iconst_0       
        //   337: new             Lcom/intellij/openapi/diagnostic/Attachment;
        //   340: dup            
        //   341: aload           7
        //   343: invokeinterface com/intellij/psi/PsiFile.getName:()Ljava/lang/String;
        //   348: aload           7
        //   350: invokeinterface com/intellij/psi/PsiFile.getText:()Ljava/lang/String;
        //   355: invokespecial   com/intellij/openapi/diagnostic/Attachment.<init>:(Ljava/lang/String;Ljava/lang/String;)V
        //   358: aastore        
        //   359: invokevirtual   com/intellij/openapi/diagnostic/Logger.error:(Ljava/lang/String;[Lcom/intellij/openapi/diagnostic/Attachment;)V
        //   362: goto            377
        //   365: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   368: athrow         
        //   369: getstatic       com/jetbrains/cidr/lang/OCLog.LOG:Lcom/intellij/openapi/diagnostic/Logger;
        //   372: ldc             "Invalid PSI context in auto type"
        //   374: invokevirtual   com/intellij/openapi/diagnostic/Logger.error:(Ljava/lang/String;)V
        //   377: aload           4
        //   379: ifnull          395
        //   382: aload           4
        //   384: aload_3        
        //   385: invokevirtual   com/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol.getResolvedType:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   388: goto            396
        //   391: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   394: athrow         
        //   395: aconst_null    
        //   396: astore          6
        //   398: aload           6
        //   400: ifnull          418
        //   403: aload           6
        //   405: aload_1        
        //   406: invokevirtual   com/jetbrains/cidr/lang/types/OCAutoType.getCVQualifiers:()Lcom/jetbrains/cidr/lang/types/CVQualifiers;
        //   409: aload_3        
        //   410: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getProject:()Lcom/intellij/openapi/project/Project;
        //   413: invokevirtual   com/jetbrains/cidr/lang/types/OCType.cloneWithAddedCVQualifiers:(Lcom/jetbrains/cidr/lang/types/CVQualifiers;Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   416: astore          6
        //   418: aload_0        
        //   419: aload_2        
        //   420: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.c:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$TypeKey;)V
        //   423: aload_1        
        //   424: invokevirtual   com/jetbrains/cidr/lang/types/OCAutoType.getIncompleteType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   427: astore          7
        //   429: aload           6
        //   431: instanceof      Lcom/jetbrains/cidr/lang/types/OCAutoType;
        //   434: ifeq            489
        //   437: aload           6
        //   439: checkcast       Lcom/jetbrains/cidr/lang/types/OCAutoType;
        //   442: invokevirtual   com/jetbrains/cidr/lang/types/OCAutoType.needsAutoParamsResolving:()Z
        //   445: ifeq            489
        //   448: goto            455
        //   451: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   454: athrow         
        //   455: aload           7
        //   457: ifnull          482
        //   460: goto            467
        //   463: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   466: athrow         
        //   467: aload           7
        //   469: instanceof      Lcom/jetbrains/cidr/lang/types/OCAutoType;
        //   472: ifeq            489
        //   475: goto            482
        //   478: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   481: athrow         
        //   482: aload           6
        //   484: areturn        
        //   485: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   488: athrow         
        //   489: aload           6
        //   491: ifnull          548
        //   494: aload           5
        //   496: ifnull          548
        //   499: goto            506
        //   502: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   505: athrow         
        //   506: aload           5
        //   508: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getParent:()Lcom/intellij/psi/PsiElement;
        //   513: instanceof      Lcom/jetbrains/cidr/lang/psi/OCForeachStatement;
        //   516: ifeq            548
        //   519: goto            526
        //   522: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   525: athrow         
        //   526: aload           5
        //   528: aload           6
        //   530: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.getCollectionElementType:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   533: astore          6
        //   535: aload           6
        //   537: ifnonnull       548
        //   540: getstatic       com/jetbrains/cidr/lang/types/OCUnknownType.INSTANCE:Lcom/jetbrains/cidr/lang/types/OCUnknownType;
        //   543: areturn        
        //   544: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   547: athrow         
        //   548: aload           6
        //   550: ifnull          776
        //   553: aload           7
        //   555: ifnull          776
        //   558: goto            565
        //   561: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   564: athrow         
        //   565: aload           7
        //   567: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   570: ifne            635
        //   573: goto            580
        //   576: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   579: athrow         
        //   580: aload           6
        //   582: getstatic       com/jetbrains/cidr/lang/types/CVQualifiers.EMPTY:Lcom/jetbrains/cidr/lang/types/CVQualifiers;
        //   585: aload_3        
        //   586: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getProject:()Lcom/intellij/openapi/project/Project;
        //   589: invokevirtual   com/jetbrains/cidr/lang/types/OCType.cloneWithCVQualifiers:(Lcom/jetbrains/cidr/lang/types/CVQualifiers;Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   592: astore          6
        //   594: aload           6
        //   596: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   599: ifeq            635
        //   602: aload           6
        //   604: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   607: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   610: getstatic       com/jetbrains/cidr/lang/types/CVQualifiers.EMPTY:Lcom/jetbrains/cidr/lang/types/CVQualifiers;
        //   613: aload_3        
        //   614: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getProject:()Lcom/intellij/openapi/project/Project;
        //   617: invokevirtual   com/jetbrains/cidr/lang/types/OCType.cloneWithCVQualifiers:(Lcom/jetbrains/cidr/lang/types/CVQualifiers;Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   620: aload           6
        //   622: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   625: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.isRvalueRef:()Z
        //   628: iconst_0       
        //   629: iconst_0       
        //   630: invokestatic    com/jetbrains/cidr/lang/types/OCCppReferenceType.to:(Lcom/jetbrains/cidr/lang/types/OCType;ZZZ)Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   633: astore          6
        //   635: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.newTypesMap:()Ljava/util/Map;
        //   638: astore          8
        //   640: aload           7
        //   642: aload           8
        //   644: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.replaceAutoTypesWithTypeParameters:(Lcom/jetbrains/cidr/lang/types/OCType;Ljava/util/Map;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   647: astore          7
        //   649: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.newTypeParameterMap:()Ljava/util/Map;
        //   652: astore          9
        //   654: aload           7
        //   656: aload           6
        //   658: aload           5
        //   660: aload           9
        //   662: aconst_null    
        //   663: iconst_1       
        //   664: iconst_0       
        //   665: aload_3        
        //   666: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.unify:(Lcom/jetbrains/cidr/lang/types/OCTypeArgument;Lcom/jetbrains/cidr/lang/types/OCTypeArgument;Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Ljava/util/Map;Ljava/util/Set;ZZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   669: getstatic       com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.NOT_UNIFIED:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   672: if_acmpeq       772
        //   675: aload           8
        //   677: invokeinterface java/util/Map.values:()Ljava/util/Collection;
        //   682: invokeinterface java/util/Collection.iterator:()Ljava/util/Iterator;
        //   687: astore          10
        //   689: aload           10
        //   691: invokeinterface java/util/Iterator.hasNext:()Z
        //   696: ifeq            756
        //   699: aload           10
        //   701: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   706: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   709: astore          11
        //   711: aload           11
        //   713: checkcast       Lcom/jetbrains/cidr/lang/types/OCTypeParameterType;
        //   716: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeParameterType.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;
        //   719: astore          12
        //   721: aload           9
        //   723: aload           12
        //   725: invokeinterface java/util/Map.containsKey:(Ljava/lang/Object;)Z
        //   730: ifne            753
        //   733: aload           9
        //   735: aload           12
        //   737: getstatic       com/jetbrains/cidr/lang/types/OCUnknownType.INSTANCE:Lcom/jetbrains/cidr/lang/types/OCUnknownType;
        //   740: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   745: pop            
        //   746: goto            753
        //   749: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   752: athrow         
        //   753: goto            689
        //   756: new             Lcom/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution;
        //   759: dup            
        //   760: aload           9
        //   762: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.<init>:(Ljava/util/Map;)V
        //   765: aload           7
        //   767: aload_3        
        //   768: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCSimpleTypeSubstitution.substitute:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   771: areturn        
        //   772: getstatic       com/jetbrains/cidr/lang/types/OCUnknownType.INSTANCE:Lcom/jetbrains/cidr/lang/types/OCUnknownType;
        //   775: areturn        
        //   776: aload_3        
        //   777: aload_1        
        //   778: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getAutoParamTypeMapping:(Lcom/jetbrains/cidr/lang/types/OCAutoType;)Lcom/jetbrains/cidr/lang/types/OCTypeParameterType;
        //   781: ifnull          828
        //   784: aload_3        
        //   785: aload_1        
        //   786: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getAutoParamTypeMapping:(Lcom/jetbrains/cidr/lang/types/OCAutoType;)Lcom/jetbrains/cidr/lang/types/OCTypeParameterType;
        //   789: astore          8
        //   791: aload           8
        //   793: ifnull          828
        //   796: aload_3        
        //   797: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getSubstitution:()Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;
        //   800: aload           8
        //   802: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeParameterType.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;
        //   805: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.getSubstitutionFor:(Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;)Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   808: astore          9
        //   810: aload           9
        //   812: instanceof      Lcom/jetbrains/cidr/lang/types/OCType;
        //   815: ifeq            828
        //   818: aload           9
        //   820: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   823: areturn        
        //   824: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   827: athrow         
        //   828: aload           6
        //   830: ifnull          842
        //   833: aload           6
        //   835: goto            857
        //   838: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   841: athrow         
        //   842: aload           7
        //   844: ifnull          856
        //   847: aload           7
        //   849: goto            857
        //   852: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   855: athrow         
        //   856: aload_1        
        //   857: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  12     21     21     25     Ljava/lang/IllegalArgumentException;
        //  25     37     37     41     Ljava/lang/IllegalArgumentException;
        //  145    159    159    163    Ljava/lang/IllegalArgumentException;
        //  188    201    204    208    Ljava/lang/IllegalArgumentException;
        //  193    215    218    222    Ljava/lang/IllegalArgumentException;
        //  208    236    236    240    Ljava/lang/IllegalArgumentException;
        //  240    255    258    262    Ljava/lang/IllegalArgumentException;
        //  245    270    273    277    Ljava/lang/IllegalArgumentException;
        //  290    305    308    312    Ljava/lang/IllegalArgumentException;
        //  321    365    365    369    Ljava/lang/IllegalArgumentException;
        //  377    391    391    395    Ljava/lang/IllegalArgumentException;
        //  429    448    451    455    Ljava/lang/IllegalArgumentException;
        //  437    460    463    467    Ljava/lang/IllegalArgumentException;
        //  455    475    478    482    Ljava/lang/IllegalArgumentException;
        //  467    485    485    489    Ljava/lang/IllegalArgumentException;
        //  489    499    502    506    Ljava/lang/IllegalArgumentException;
        //  494    519    522    526    Ljava/lang/IllegalArgumentException;
        //  535    544    544    548    Ljava/lang/IllegalArgumentException;
        //  548    558    561    565    Ljava/lang/IllegalArgumentException;
        //  553    573    576    580    Ljava/lang/IllegalArgumentException;
        //  721    746    749    753    Ljava/lang/IllegalArgumentException;
        //  810    824    824    828    Ljava/lang/IllegalArgumentException;
        //  828    838    838    842    Ljava/lang/IllegalArgumentException;
        //  842    852    852    856    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0208:
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
    
    private boolean a(final OCReferenceType ocReferenceType, final Resolver resolver, final boolean processNonImported) {
        final OCResolveContext substituteFirst = this.myContext.substituteFirst(ocReferenceType.getSubstitution());
        substituteFirst.setProcessNonImported(processNonImported);
        final List<OCSymbol> resolveToSymbols = substituteFirst.resolveToSymbols(ocReferenceType.getReference((PsiFile)this.myFile), false, false, false, true);
        final int myMaxDepth = this.myMaxDepth;
        try {
            if (substituteFirst.wasDependentType()) {
                this.myMaxDepth = 6;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            ContainerUtil.process((List)resolveToSymbols, (Processor)resolver);
            this.myMaxDepth = myMaxDepth;
            this.myContext.addTypeDependencies(substituteFirst.getTypeDependencies());
            if (!resolveToSymbols.isEmpty()) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return false;
    }
    
    private boolean b(final TypeKey typeKey) {
        return this.myContext.getResolvingTypes().add(typeKey);
    }
    
    private void c(final TypeKey typeKey) {
        this.myContext.getResolvingTypes().remove(typeKey);
    }
    
    private boolean a(final TypeKey typeKey) {
        return this.myContext.getResolvingTypes().contains(typeKey);
    }
    
    static {
        RESOLVE_CACHE_LOCAL = Key.create("RESOLVE_CACHE_LOCAL");
        RESOLVE_CACHE_GLOBAL = Key.create("RESOLVE_CACHE_GLOBAL");
        USED_FILE_LIST_INTERNATOR = new OCInternator<ArrayList<VirtualFile>>() {
            @NotNull
            @Override
            protected ArrayList<VirtualFile> valueToStore(@NotNull final ArrayList<VirtualFile> list) {
                try {
                    if (list == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "original", "com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$1", "valueToStore"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                try {
                    list.trimToSize();
                    if (list == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$1", "valueToStore"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
                return list;
            }
            
            private static IllegalArgumentException b(final IllegalArgumentException ex) {
                return ex;
            }
        };
        FILE_PATH_COMPARATOR = ((virtualFile, virtualFile2) -> Comparing.compare((Comparable)virtualFile.getPath(), (Comparable)virtualFile2.getPath()));
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
    
    private static class ResultInfo
    {
        final OCType resolvedType;
        final Collection<VirtualFile> usedFiles;
        final Set<OCTypeParameterSymbol> typeDependencies;
        
        private ResultInfo(final OCType resolvedType, final Collection<VirtualFile> usedFiles, final Set<OCTypeParameterSymbol> typeDependencies) {
            this.resolvedType = resolvedType;
            this.usedFiles = usedFiles;
            this.typeDependencies = typeDependencies;
        }
    }
    
    public static class TypeKey
    {
        private OCType type;
        private OCTypeSubstitution substitution;
        private final boolean isInSFINAE;
        
        public TypeKey(final OCType type, final OCTypeSubstitution substitution, final boolean isInSFINAE) {
            this.type = type;
            this.substitution = substitution;
            this.isInSFINAE = isInSFINAE;
        }
        
        @Override
        public boolean equals(final Object o) {
            if (!(o instanceof TypeKey)) {
                return false;
            }
            final TypeKey typeKey = (TypeKey)o;
            return DeepEqual.equalObjects(this.type, typeKey.type) && DeepEqual.equalObjects(this.substitution, typeKey.substitution) && this.isInSFINAE == typeKey.isInSFINAE;
        }
        
        @Override
        public int hashCode() {
            return 31 * (31 * ((this.type != null) ? this.type.hashCode() : 0) + ((this.substitution != null) ? this.substitution.hashCode() : 0)) + (this.isInSFINAE ? 1 : 0);
        }
    }
    
    private class Resolver implements Processor<OCSymbol>
    {
        private OCReferenceType myType;
        private OCType myAnswer;
        private OCInterfaceSymbol myInterface;
        private OCImplementationSymbol myImplementation;
        private List<OCInterfaceSymbol> myCategoryInterfaces;
        private List<OCImplementationSymbol> myCategoryImplementations;
        private OCSymbolKind myPreferableKind;
        final /* synthetic */ OCTypeResolveVisitor this$0;
        
        private Resolver(final OCReferenceType myType) {
            this.myCategoryInterfaces = new ArrayList<OCInterfaceSymbol>();
            this.myCategoryImplementations = new ArrayList<OCImplementationSymbol>();
            this.myType = myType;
        }
        
        void setPreferableKind(final OCSymbolKind myPreferableKind) {
            this.myPreferableKind = myPreferableKind;
        }
        
        private int a(final OCInterfaceSymbol ocInterfaceSymbol) {
            try {
                if (ocInterfaceSymbol == null) {
                    return 0;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (ocInterfaceSymbol.isPredeclaration()) {
                    return 1;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            return 2;
        }
        
        public boolean process(final OCSymbol p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.this$0:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;
            //     4: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.access$200:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;)Z
            //     7: ifeq            39
            //    10: aload_1        
            //    11: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isPredeclaration:()Z
            //    16: ifeq            39
            //    19: goto            26
            //    22: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    25: athrow         
            //    26: aload_1        
            //    27: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getDefinitionSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
            //    32: astore_2       
            //    33: aload_2        
            //    34: ifnull          39
            //    37: aload_2        
            //    38: astore_1       
            //    39: aload_1        
            //    40: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
            //    43: ifeq            227
            //    46: aload_1        
            //    47: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
            //    50: astore_2       
            //    51: aload_2        
            //    52: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getCategoryName:()Ljava/lang/String;
            //    57: ifnonnull       142
            //    60: aload_2        
            //    61: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
            //    64: ifeq            120
            //    67: goto            74
            //    70: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    73: athrow         
            //    74: aload_0        
            //    75: aload_2        
            //    76: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
            //    79: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;)I
            //    82: aload_0        
            //    83: aload_0        
            //    84: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myInterface:Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
            //    87: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;)I
            //    90: if_icmple       120
            //    93: goto            100
            //    96: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    99: athrow         
            //   100: aload_0        
            //   101: aload_2        
            //   102: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
            //   105: putfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myInterface:Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
            //   108: aload_0        
            //   109: aload_1        
            //   110: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
            //   113: goto            224
            //   116: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   119: athrow         
            //   120: aload_2        
            //   121: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol;
            //   124: ifeq            224
            //   127: aload_0        
            //   128: aload_2        
            //   129: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol;
            //   132: putfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myImplementation:Lcom/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol;
            //   135: goto            224
            //   138: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   141: athrow         
            //   142: aload_2        
            //   143: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
            //   146: ifeq            196
            //   149: aload_0        
            //   150: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myCategoryInterfaces:Ljava/util/List;
            //   153: aload_2        
            //   154: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
            //   157: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
            //   162: pop            
            //   163: aload_2        
            //   164: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getProtocolNames:()Ljava/util/List;
            //   169: invokeinterface java/util/List.isEmpty:()Z
            //   174: ifne            224
            //   177: goto            184
            //   180: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   183: athrow         
            //   184: aload_0        
            //   185: aload_1        
            //   186: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
            //   189: goto            224
            //   192: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   195: athrow         
            //   196: aload_2        
            //   197: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol;
            //   200: ifeq            224
            //   203: aload_0        
            //   204: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myCategoryImplementations:Ljava/util/List;
            //   207: aload_2        
            //   208: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol;
            //   211: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
            //   216: pop            
            //   217: goto            224
            //   220: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   223: athrow         
            //   224: goto            1373
            //   227: aload_1        
            //   228: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
            //   231: ifne            262
            //   234: aload_1        
            //   235: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCCompatibilityAliasSymbol;
            //   238: ifne            262
            //   241: goto            248
            //   244: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   247: athrow         
            //   248: aload_1        
            //   249: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCAliasUsingSymbol;
            //   252: ifeq            931
            //   255: goto            262
            //   258: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   261: athrow         
            //   262: aload_1        
            //   263: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
            //   268: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isTypedefOrAlias:()Z
            //   271: ifeq            1373
            //   274: goto            281
            //   277: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   280: athrow         
            //   281: aload_0        
            //   282: aload_0        
            //   283: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myAnswer:Lcom/jetbrains/cidr/lang/types/OCType;
            //   286: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
            //   289: ifne            316
            //   292: goto            299
            //   295: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   298: athrow         
            //   299: aload_0        
            //   300: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myAnswer:Lcom/jetbrains/cidr/lang/types/OCType;
            //   303: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
            //   306: ifeq            1373
            //   309: goto            316
            //   312: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   315: athrow         
            //   316: aload_0        
            //   317: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myType:Lcom/jetbrains/cidr/lang/types/OCReferenceType;
            //   320: invokevirtual   com/jetbrains/cidr/lang/types/OCReferenceType.getSubstitution:()Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;
            //   323: aload_1        
            //   324: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
            //   329: aload_0        
            //   330: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.this$0:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;
            //   333: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.access$300:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;)Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
            //   336: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.substitute:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
            //   339: astore_2       
            //   340: aload_2        
            //   341: aload_0        
            //   342: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myType:Lcom/jetbrains/cidr/lang/types/OCReferenceType;
            //   345: invokevirtual   com/jetbrains/cidr/lang/types/OCReferenceType.getCVQualifiers:()Lcom/jetbrains/cidr/lang/types/CVQualifiers;
            //   348: aload_0        
            //   349: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.this$0:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;
            //   352: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.access$300:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;)Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
            //   355: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getProject:()Lcom/intellij/openapi/project/Project;
            //   358: invokevirtual   com/jetbrains/cidr/lang/types/OCType.cloneWithAddedCVQualifiers:(Lcom/jetbrains/cidr/lang/types/CVQualifiers;Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCType;
            //   361: astore_2       
            //   362: aload_2        
            //   363: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:()Ljava/lang/String;
            //   366: ldc             "__builtin"
            //   368: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
            //   371: ifeq            392
            //   374: aload_0        
            //   375: new             Lcom/jetbrains/cidr/lang/types/OCMagicType;
            //   378: dup            
            //   379: invokespecial   com/jetbrains/cidr/lang/types/OCMagicType.<init>:()V
            //   382: putfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myAnswer:Lcom/jetbrains/cidr/lang/types/OCType;
            //   385: goto            888
            //   388: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   391: athrow         
            //   392: aload_2        
            //   393: instanceof      Lcom/jetbrains/cidr/lang/types/OCReferenceType;
            //   396: ifeq            424
            //   399: aload_0        
            //   400: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.this$0:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;
            //   403: aload_0        
            //   404: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.this$0:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;
            //   407: aload_2        
            //   408: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.access$400:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$TypeKey;
            //   411: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.access$500:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$TypeKey;)Z
            //   414: ifne            881
            //   417: goto            424
            //   420: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   423: athrow         
            //   424: aload_1        
            //   425: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getContainingFile:()Lcom/intellij/openapi/vfs/VirtualFile;
            //   430: ifnull          881
            //   433: goto            440
            //   436: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   439: athrow         
            //   440: aload_1        
            //   441: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getProject:()Lcom/intellij/openapi/project/Project;
            //   446: ifnull          881
            //   449: goto            456
            //   452: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   455: athrow         
            //   456: aload_1        
            //   457: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
            //   462: astore_3       
            //   463: aload_3        
            //   464: ifnull          878
            //   467: aload_2        
            //   468: aload_0        
            //   469: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.this$0:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;
            //   472: invokevirtual   com/jetbrains/cidr/lang/types/OCType.accept:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeVisitor;)Ljava/lang/Object;
            //   475: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
            //   478: astore          4
            //   480: aload_0        
            //   481: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myType:Lcom/jetbrains/cidr/lang/types/OCReferenceType;
            //   484: invokevirtual   com/jetbrains/cidr/lang/types/OCReferenceType.getSubstitution:()Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;
            //   487: aload           4
            //   489: aload_0        
            //   490: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.this$0:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;
            //   493: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.access$300:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;)Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
            //   496: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.substitute:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
            //   499: astore          4
            //   501: aload           4
            //   503: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
            //   506: ifeq            596
            //   509: aload           4
            //   511: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
            //   514: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getStructs:()Ljava/util/List;
            //   517: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
            //   522: astore          5
            //   524: aload           5
            //   526: invokeinterface java/util/Iterator.hasNext:()Z
            //   531: ifeq            571
            //   534: aload           5
            //   536: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
            //   541: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
            //   544: astore          6
            //   546: aload_0        
            //   547: aload           6
            //   549: aload           4
            //   551: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
            //   554: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getTypedefName:()Ljava/lang/String;
            //   557: aload           4
            //   559: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
            //   562: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getArguments:()Ljava/util/List;
            //   565: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;Ljava/lang/String;Ljava/util/List;)V
            //   568: goto            524
            //   571: aload_0        
            //   572: aload_0        
            //   573: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myAnswer:Lcom/jetbrains/cidr/lang/types/OCType;
            //   576: aload           4
            //   578: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getCVQualifiers:()Lcom/jetbrains/cidr/lang/types/CVQualifiers;
            //   581: aload_1        
            //   582: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getProject:()Lcom/intellij/openapi/project/Project;
            //   587: invokevirtual   com/jetbrains/cidr/lang/types/OCType.cloneWithAddedCVQualifiers:(Lcom/jetbrains/cidr/lang/types/CVQualifiers;Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCType;
            //   590: putfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myAnswer:Lcom/jetbrains/cidr/lang/types/OCType;
            //   593: goto            698
            //   596: aload_0        
            //   597: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myAnswer:Lcom/jetbrains/cidr/lang/types/OCType;
            //   600: ifnull          681
            //   603: aload_0        
            //   604: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myAnswer:Lcom/jetbrains/cidr/lang/types/OCType;
            //   607: instanceof      Lcom/jetbrains/cidr/lang/types/OCTypeParameterType;
            //   610: ifeq            635
            //   613: goto            620
            //   616: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   619: athrow         
            //   620: aload           4
            //   622: instanceof      Lcom/jetbrains/cidr/lang/types/OCTypeParameterType;
            //   625: ifeq            681
            //   628: goto            635
            //   631: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   634: athrow         
            //   635: aload_0        
            //   636: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myAnswer:Lcom/jetbrains/cidr/lang/types/OCType;
            //   639: aload_0        
            //   640: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.this$0:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;
            //   643: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.access$300:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;)Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
            //   646: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnresolved:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
            //   649: ifeq            698
            //   652: goto            659
            //   655: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   658: athrow         
            //   659: aload           4
            //   661: aload_0        
            //   662: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.this$0:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;
            //   665: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.access$300:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;)Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
            //   668: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isUnresolved:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
            //   671: ifne            698
            //   674: goto            681
            //   677: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   680: athrow         
            //   681: aload_0        
            //   682: aload_0        
            //   683: aload           4
            //   685: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.b:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCType;
            //   688: putfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myAnswer:Lcom/jetbrains/cidr/lang/types/OCType;
            //   691: goto            698
            //   694: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   697: athrow         
            //   698: aload_1        
            //   699: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
            //   702: ifeq            878
            //   705: aload_0        
            //   706: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.this$0:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;
            //   709: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.access$300:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;)Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
            //   712: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getSubstitution:()Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;
            //   715: aload_1        
            //   716: aload_0        
            //   717: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.this$0:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;
            //   720: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.access$300:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;)Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
            //   723: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.substitute:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
            //   726: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
            //   729: astore          5
            //   731: aload_0        
            //   732: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myType:Lcom/jetbrains/cidr/lang/types/OCReferenceType;
            //   735: invokevirtual   com/jetbrains/cidr/lang/types/OCReferenceType.getSubstitution:()Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;
            //   738: aload           5
            //   740: aload_0        
            //   741: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.this$0:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;
            //   744: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.access$300:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;)Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
            //   747: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.substitute:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
            //   750: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
            //   753: astore          5
            //   755: aload           5
            //   757: iconst_1       
            //   758: aload_0        
            //   759: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.this$0:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;
            //   762: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.access$300:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;)Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
            //   765: iconst_1       
            //   766: iconst_1       
            //   767: iconst_1       
            //   768: iconst_1       
            //   769: iconst_1       
            //   770: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getResolvedQualifiedName:(ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;ZZZZZ)Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
            //   773: astore          6
            //   775: aload           6
            //   777: ifnull          878
            //   780: aload           6
            //   782: getstatic       com/jetbrains/cidr/lang/types/OCType$Presentation.FULL:Lcom/jetbrains/cidr/lang/types/OCType$Presentation;
            //   785: iconst_0       
            //   786: aload_0        
            //   787: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.this$0:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;
            //   790: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.access$300:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;)Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
            //   793: iconst_0       
            //   794: invokevirtual   com/jetbrains/cidr/lang/symbols/OCQualifiedName.getCanonicalName:(Lcom/jetbrains/cidr/lang/types/OCType$Presentation;ZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;I)Ljava/lang/String;
            //   797: astore          7
            //   799: aload           7
            //   801: aload_0        
            //   802: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myType:Lcom/jetbrains/cidr/lang/types/OCReferenceType;
            //   805: aload_0        
            //   806: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.this$0:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;
            //   809: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.access$300:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;)Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
            //   812: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getProject:()Lcom/intellij/openapi/project/Project;
            //   815: invokestatic    com/jetbrains/cidr/lang/types/CVQualifiers.appendCVQualifiers:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/openapi/project/Project;)Ljava/lang/String;
            //   818: astore          7
            //   820: aload_0        
            //   821: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myAnswer:Lcom/jetbrains/cidr/lang/types/OCType;
            //   824: getstatic       com/jetbrains/cidr/lang/types/OCIntType.BOOL:Lcom/jetbrains/cidr/lang/types/OCIntType;
            //   827: aload_3        
            //   828: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equals:(Ljava/lang/Object;Lcom/intellij/psi/PsiElement;)Z
            //   831: ifeq            865
            //   834: ldc             "BOOL"
            //   836: aload           7
            //   838: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/String;Ljava/lang/String;)Z
            //   841: ifeq            865
            //   844: goto            851
            //   847: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   850: athrow         
            //   851: aload_0        
            //   852: getstatic       com/jetbrains/cidr/lang/types/OCIntType.BOOL:Lcom/jetbrains/cidr/lang/types/OCIntType;
            //   855: putfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myAnswer:Lcom/jetbrains/cidr/lang/types/OCType;
            //   858: goto            878
            //   861: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   864: athrow         
            //   865: aload_0        
            //   866: aload_0        
            //   867: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myAnswer:Lcom/jetbrains/cidr/lang/types/OCType;
            //   870: aload           7
            //   872: invokevirtual   com/jetbrains/cidr/lang/types/OCType.cloneWithAliasName:(Ljava/lang/String;)Lcom/jetbrains/cidr/lang/types/OCType;
            //   875: putfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myAnswer:Lcom/jetbrains/cidr/lang/types/OCType;
            //   878: goto            888
            //   881: aload_0        
            //   882: getstatic       com/jetbrains/cidr/lang/types/OCUnknownType.INSTANCE:Lcom/jetbrains/cidr/lang/types/OCUnknownType;
            //   885: putfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myAnswer:Lcom/jetbrains/cidr/lang/types/OCType;
            //   888: aload_0        
            //   889: aload_1        
            //   890: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
            //   893: aload_0        
            //   894: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myAnswer:Lcom/jetbrains/cidr/lang/types/OCType;
            //   897: ifnonnull       929
            //   900: aload_0        
            //   901: aload_2        
            //   902: aload_0        
            //   903: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myType:Lcom/jetbrains/cidr/lang/types/OCReferenceType;
            //   906: aload_0        
            //   907: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.this$0:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;
            //   910: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.access$600:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;)Lcom/jetbrains/cidr/lang/psi/OCFile;
            //   913: invokevirtual   com/jetbrains/cidr/lang/types/OCReferenceType.getCanonicalName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
            //   916: invokevirtual   com/jetbrains/cidr/lang/types/OCType.cloneWithAliasName:(Ljava/lang/String;)Lcom/jetbrains/cidr/lang/types/OCType;
            //   919: putfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myAnswer:Lcom/jetbrains/cidr/lang/types/OCType;
            //   922: goto            929
            //   925: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   928: athrow         
            //   929: iconst_1       
            //   930: ireturn        
            //   931: aload_1        
            //   932: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
            //   935: ifeq            1026
            //   938: aload_0        
            //   939: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myPreferableKind:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
            //   942: ifnull          972
            //   945: goto            952
            //   948: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   951: athrow         
            //   952: aload_0        
            //   953: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myPreferableKind:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
            //   956: aload_1        
            //   957: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
            //   962: if_acmpne       1003
            //   965: goto            972
            //   968: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   971: athrow         
            //   972: aload_0        
            //   973: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.this$0:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;
            //   976: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.access$700:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;)Z
            //   979: ifeq            1009
            //   982: goto            989
            //   985: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   988: athrow         
            //   989: aload_0        
            //   990: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myPreferableKind:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
            //   993: ifnonnull       1009
            //   996: goto            1003
            //   999: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //  1002: athrow         
            //  1003: iconst_1       
            //  1004: ireturn        
            //  1005: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //  1008: athrow         
            //  1009: aload_0        
            //  1010: aload_1        
            //  1011: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
            //  1014: aload_0        
            //  1015: aload_1        
            //  1016: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
            //  1019: aconst_null    
            //  1020: aconst_null    
            //  1021: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;Ljava/lang/String;Ljava/util/List;)V
            //  1024: iconst_1       
            //  1025: ireturn        
            //  1026: aload_1        
            //  1027: instanceof      Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;
            //  1030: ifeq            1338
            //  1033: aload_0        
            //  1034: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myType:Lcom/jetbrains/cidr/lang/types/OCReferenceType;
            //  1037: invokevirtual   com/jetbrains/cidr/lang/types/OCReferenceType.getSubstitution:()Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;
            //  1040: aload_1        
            //  1041: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;
            //  1044: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.getSubstitutionFor:(Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;)Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
            //  1047: astore_2       
            //  1048: aconst_null    
            //  1049: astore_3       
            //  1050: aload_2        
            //  1051: ifnonnull       1072
            //  1054: aload_0        
            //  1055: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.this$0:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;
            //  1058: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.access$300:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;)Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
            //  1061: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.getSubstitution:()Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;
            //  1064: aload_1        
            //  1065: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;
            //  1068: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.getSubstitutionFor:(Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;)Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
            //  1071: astore_2       
            //  1072: aload_2        
            //  1073: instanceof      Lcom/jetbrains/cidr/lang/types/OCReferenceType;
            //  1076: ifne            1093
            //  1079: aload_2        
            //  1080: instanceof      Lcom/jetbrains/cidr/lang/types/OCAutoType;
            //  1083: ifeq            1151
            //  1086: goto            1093
            //  1089: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //  1092: athrow         
            //  1093: aload_0        
            //  1094: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myType:Lcom/jetbrains/cidr/lang/types/OCReferenceType;
            //  1097: invokevirtual   com/jetbrains/cidr/lang/types/OCReferenceType.getSubstitution:()Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;
            //  1100: aload_2        
            //  1101: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
            //  1104: aload_0        
            //  1105: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.this$0:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;
            //  1108: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.access$300:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;)Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
            //  1111: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.substitute:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
            //  1114: astore          4
            //  1116: aload_0        
            //  1117: aload           4
            //  1119: aload_0        
            //  1120: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.this$0:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;
            //  1123: invokevirtual   com/jetbrains/cidr/lang/types/OCType.accept:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeVisitor;)Ljava/lang/Object;
            //  1126: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
            //  1129: aload_0        
            //  1130: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myType:Lcom/jetbrains/cidr/lang/types/OCReferenceType;
            //  1133: aload_0        
            //  1134: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.this$0:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;
            //  1137: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.access$600:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;)Lcom/jetbrains/cidr/lang/psi/OCFile;
            //  1140: invokevirtual   com/jetbrains/cidr/lang/types/OCReferenceType.getCanonicalName:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
            //  1143: invokevirtual   com/jetbrains/cidr/lang/types/OCType.cloneWithAliasName:(Ljava/lang/String;)Lcom/jetbrains/cidr/lang/types/OCType;
            //  1146: putfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myAnswer:Lcom/jetbrains/cidr/lang/types/OCType;
            //  1149: iconst_1       
            //  1150: ireturn        
            //  1151: aload_2        
            //  1152: instanceof      Lcom/jetbrains/cidr/lang/types/OCTypeParameterType;
            //  1155: ifeq            1249
            //  1158: aload_0        
            //  1159: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.this$0:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;
            //  1162: aload_2        
            //  1163: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
            //  1166: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.access$400:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$TypeKey;
            //  1169: astore          4
            //  1171: aload_0        
            //  1172: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.this$0:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;
            //  1175: aload           4
            //  1177: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.access$800:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$TypeKey;)Z
            //  1180: ifeq            1249
            //  1183: aload_0        
            //  1184: aload_2        
            //  1185: checkcast       Lcom/jetbrains/cidr/lang/types/OCTypeParameterType;
            //  1188: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeParameterType.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;
            //  1191: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
            //  1194: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.process:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
            //  1197: istore          5
            //  1199: aload_0        
            //  1200: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.this$0:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;
            //  1203: aload           4
            //  1205: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.access$900:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$TypeKey;)V
            //  1208: aload_0        
            //  1209: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myAnswer:Lcom/jetbrains/cidr/lang/types/OCType;
            //  1212: ifnull          1246
            //  1215: aload_0        
            //  1216: aload_0        
            //  1217: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myAnswer:Lcom/jetbrains/cidr/lang/types/OCType;
            //  1220: aload_2        
            //  1221: checkcast       Lcom/jetbrains/cidr/lang/types/OCTypeParameterType;
            //  1224: invokevirtual   com/jetbrains/cidr/lang/types/OCTypeParameterType.getCVQualifiers:()Lcom/jetbrains/cidr/lang/types/CVQualifiers;
            //  1227: aload_1        
            //  1228: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getProject:()Lcom/intellij/openapi/project/Project;
            //  1233: invokevirtual   com/jetbrains/cidr/lang/types/OCType.cloneWithAddedCVQualifiers:(Lcom/jetbrains/cidr/lang/types/CVQualifiers;Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCType;
            //  1236: putfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myAnswer:Lcom/jetbrains/cidr/lang/types/OCType;
            //  1239: goto            1246
            //  1242: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //  1245: athrow         
            //  1246: iload           5
            //  1248: ireturn        
            //  1249: aload_2        
            //  1250: instanceof      Lcom/jetbrains/cidr/lang/types/OCType;
            //  1253: ifeq            1271
            //  1256: aload_0        
            //  1257: aload_2        
            //  1258: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
            //  1261: putfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myAnswer:Lcom/jetbrains/cidr/lang/types/OCType;
            //  1264: goto            1300
            //  1267: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //  1270: athrow         
            //  1271: aload_0        
            //  1272: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myAnswer:Lcom/jetbrains/cidr/lang/types/OCType;
            //  1275: ifnonnull       1300
            //  1278: aload_0        
            //  1279: new             Lcom/jetbrains/cidr/lang/types/OCTypeParameterType;
            //  1282: dup            
            //  1283: aload_1        
            //  1284: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;
            //  1287: invokespecial   com/jetbrains/cidr/lang/types/OCTypeParameterType.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;)V
            //  1290: putfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myAnswer:Lcom/jetbrains/cidr/lang/types/OCType;
            //  1293: goto            1300
            //  1296: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //  1299: athrow         
            //  1300: aload_3        
            //  1301: ifnull          1323
            //  1304: aload_0        
            //  1305: aload_0        
            //  1306: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myAnswer:Lcom/jetbrains/cidr/lang/types/OCType;
            //  1309: aload_3        
            //  1310: invokevirtual   com/jetbrains/cidr/lang/types/OCType.cloneWithAliasName:(Ljava/lang/String;)Lcom/jetbrains/cidr/lang/types/OCType;
            //  1313: putfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myAnswer:Lcom/jetbrains/cidr/lang/types/OCType;
            //  1316: goto            1323
            //  1319: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //  1322: athrow         
            //  1323: aload_0        
            //  1324: aload_0        
            //  1325: aload_0        
            //  1326: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myAnswer:Lcom/jetbrains/cidr/lang/types/OCType;
            //  1329: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.b:(Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCType;
            //  1332: putfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myAnswer:Lcom/jetbrains/cidr/lang/types/OCType;
            //  1335: goto            1373
            //  1338: aload_1        
            //  1339: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCUsingSymbol;
            //  1342: ifeq            1373
            //  1345: aload_0        
            //  1346: aload_1        
            //  1347: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
            //  1350: aload_1        
            //  1351: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCUsingSymbol;
            //  1354: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCUsingSymbol.getSymbolReference:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;
            //  1357: aload_0        
            //  1358: aload_0        
            //  1359: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.this$0:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;
            //  1362: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.access$600:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;)Lcom/jetbrains/cidr/lang/psi/OCFile;
            //  1365: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolReference.processPossibleSymbols:(Lcom/intellij/util/Processor;Lcom/intellij/psi/PsiFile;)Z
            //  1368: ireturn        
            //  1369: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //  1372: athrow         
            //  1373: iconst_1       
            //  1374: ireturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  0      19     22     26     Ljava/lang/IllegalArgumentException;
            //  51     67     70     74     Ljava/lang/IllegalArgumentException;
            //  60     93     96     100    Ljava/lang/IllegalArgumentException;
            //  74     116    116    120    Ljava/lang/IllegalArgumentException;
            //  120    138    138    142    Ljava/lang/IllegalArgumentException;
            //  142    177    180    184    Ljava/lang/IllegalArgumentException;
            //  149    192    192    196    Ljava/lang/IllegalArgumentException;
            //  196    217    220    224    Ljava/lang/IllegalArgumentException;
            //  227    241    244    248    Ljava/lang/IllegalArgumentException;
            //  234    255    258    262    Ljava/lang/IllegalArgumentException;
            //  248    274    277    281    Ljava/lang/IllegalArgumentException;
            //  262    292    295    299    Ljava/lang/IllegalArgumentException;
            //  281    309    312    316    Ljava/lang/IllegalArgumentException;
            //  362    388    388    392    Ljava/lang/IllegalArgumentException;
            //  392    417    420    424    Ljava/lang/IllegalArgumentException;
            //  399    433    436    440    Ljava/lang/IllegalArgumentException;
            //  424    449    452    456    Ljava/lang/IllegalArgumentException;
            //  596    613    616    620    Ljava/lang/IllegalArgumentException;
            //  603    628    631    635    Ljava/lang/IllegalArgumentException;
            //  620    652    655    659    Ljava/lang/IllegalArgumentException;
            //  635    674    677    681    Ljava/lang/IllegalArgumentException;
            //  659    691    694    698    Ljava/lang/IllegalArgumentException;
            //  820    844    847    851    Ljava/lang/IllegalArgumentException;
            //  834    861    861    865    Ljava/lang/IllegalArgumentException;
            //  888    922    925    929    Ljava/lang/IllegalArgumentException;
            //  931    945    948    952    Ljava/lang/IllegalArgumentException;
            //  938    965    968    972    Ljava/lang/IllegalArgumentException;
            //  952    982    985    989    Ljava/lang/IllegalArgumentException;
            //  972    996    999    1003   Ljava/lang/IllegalArgumentException;
            //  989    1005   1005   1009   Ljava/lang/IllegalArgumentException;
            //  1072   1086   1089   1093   Ljava/lang/IllegalArgumentException;
            //  1199   1239   1242   1246   Ljava/lang/IllegalArgumentException;
            //  1249   1267   1267   1271   Ljava/lang/IllegalArgumentException;
            //  1271   1293   1296   1300   Ljava/lang/IllegalArgumentException;
            //  1300   1316   1319   1323   Ljava/lang/IllegalArgumentException;
            //  1338   1369   1369   1373   Ljava/lang/IllegalArgumentException;
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
        
        private OCType b(final OCType ocType) {
            Label_0034: {
                try {
                    if (!this.myType.isFunctionParameterType()) {
                        return ocType;
                    }
                    final OCType ocType2 = ocType;
                    final Resolver resolver = this;
                    final OCTypeResolveVisitor ocTypeResolveVisitor = resolver.this$0;
                    final OCResolveContext ocResolveContext = ocTypeResolveVisitor.myContext;
                    final OCType ocType3 = ocType2.resolve(ocResolveContext);
                    final boolean b = ocType3 instanceof OCVoidType;
                    if (b) {
                        break Label_0034;
                    }
                    return ocType;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final OCType ocType2 = ocType;
                    final Resolver resolver = this;
                    final OCTypeResolveVisitor ocTypeResolveVisitor = resolver.this$0;
                    final OCResolveContext ocResolveContext = ocTypeResolveVisitor.myContext;
                    final OCType ocType3 = ocType2.resolve(ocResolveContext);
                    final boolean b = ocType3 instanceof OCVoidType;
                    if (b) {
                        return OCUnknownType.INSTANCE;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return ocType;
        }
        
        private boolean a(@Nullable final OCType p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_1        
            //     1: ifnull          32
            //     4: aload_1        
            //     5: instanceof      Lcom/jetbrains/cidr/lang/types/OCReferenceType;
            //     8: ifne            32
            //    11: goto            18
            //    14: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    17: athrow         
            //    18: aload_1        
            //    19: instanceof      Lcom/jetbrains/cidr/lang/types/OCTypeParameterType;
            //    22: ifeq            40
            //    25: goto            32
            //    28: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    31: athrow         
            //    32: iconst_1       
            //    33: goto            41
            //    36: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    39: athrow         
            //    40: iconst_0       
            //    41: ireturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  0      11     14     18     Ljava/lang/IllegalArgumentException;
            //  4      25     28     32     Ljava/lang/IllegalArgumentException;
            //  18     36     36     40     Ljava/lang/IllegalArgumentException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0018:
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
        
        private void a(@NotNull final OCSymbol ocSymbol) {
            try {
                if (ocSymbol == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver", "addUsedImport"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            ContainerUtil.addIfNotNull((Collection)OCTypeResolveVisitor.this.myUsedFiles, (Object)OCFileSymbols.getFileToImport(OCTypeResolveVisitor.this.myFile, ocSymbol));
        }
        
        private void a(final OCStructSymbol p0, final String p1, @Nullable final List<OCTypeArgument> p2) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myType:Lcom/jetbrains/cidr/lang/types/OCReferenceType;
            //     4: invokevirtual   com/jetbrains/cidr/lang/types/OCReferenceType.getSubstitution:()Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;
            //     7: aload_1        
            //     8: aload_0        
            //     9: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.this$0:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;
            //    12: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.access$300:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;)Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
            //    15: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.substitute:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
            //    18: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
            //    21: astore          4
            //    23: aload_0        
            //    24: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myType:Lcom/jetbrains/cidr/lang/types/OCReferenceType;
            //    27: aload_0        
            //    28: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.this$0:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;
            //    31: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.access$600:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;)Lcom/jetbrains/cidr/lang/psi/OCFile;
            //    34: invokevirtual   com/jetbrains/cidr/lang/types/OCReferenceType.getReference:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;
            //    37: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolReference.getQualifiedName:()Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
            //    40: astore          5
            //    42: aload           5
            //    44: instanceof      Lcom/jetbrains/cidr/lang/symbols/OCQualifiedNameWithArguments;
            //    47: ifeq            65
            //    50: aload           5
            //    52: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCQualifiedNameWithArguments;
            //    55: invokevirtual   com/jetbrains/cidr/lang/symbols/OCQualifiedNameWithArguments.getArguments:()Ljava/util/List;
            //    58: goto            66
            //    61: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    64: athrow         
            //    65: aload_3        
            //    66: astore          6
            //    68: aload_0        
            //    69: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myAnswer:Lcom/jetbrains/cidr/lang/types/OCType;
            //    72: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
            //    75: ifeq            283
            //    78: aconst_null    
            //    79: astore          7
            //    81: aload_0        
            //    82: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myAnswer:Lcom/jetbrains/cidr/lang/types/OCType;
            //    85: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
            //    88: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.isPredeclaration:()Z
            //    91: ifeq            136
            //    94: aload_1        
            //    95: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.isPredeclaration:()Z
            //    98: ifne            136
            //   101: goto            108
            //   104: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   107: athrow         
            //   108: new             Ljava/util/ArrayList;
            //   111: dup            
            //   112: invokespecial   java/util/ArrayList.<init>:()V
            //   115: astore          7
            //   117: aload_0        
            //   118: new             Lcom/jetbrains/cidr/lang/types/OCStructType;
            //   121: dup            
            //   122: aload           7
            //   124: aload_2        
            //   125: aload           6
            //   127: invokespecial   com/jetbrains/cidr/lang/types/OCStructType.<init>:(Ljava/util/List;Ljava/lang/String;Ljava/util/List;)V
            //   130: putfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myAnswer:Lcom/jetbrains/cidr/lang/types/OCType;
            //   133: goto            202
            //   136: aload_0        
            //   137: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myAnswer:Lcom/jetbrains/cidr/lang/types/OCType;
            //   140: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
            //   143: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.isPredeclaration:()Z
            //   146: aload_1        
            //   147: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.isPredeclaration:()Z
            //   150: if_icmpne       202
            //   153: aload_0        
            //   154: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myAnswer:Lcom/jetbrains/cidr/lang/types/OCType;
            //   157: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
            //   160: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getStructs:()Ljava/util/List;
            //   163: invokeinterface java/util/List.stream:()Ljava/util/stream/Stream;
            //   168: aload           4
            //   170: invokedynamic   test:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;)Ljava/util/function/Predicate;
            //   175: invokeinterface java/util/stream/Stream.noneMatch:(Ljava/util/function/Predicate;)Z
            //   180: ifeq            202
            //   183: goto            190
            //   186: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   189: athrow         
            //   190: aload_0        
            //   191: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myAnswer:Lcom/jetbrains/cidr/lang/types/OCType;
            //   194: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
            //   197: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getStructs:()Ljava/util/List;
            //   200: astore          7
            //   202: aload           7
            //   204: ifnull          280
            //   207: aload           7
            //   209: invokeinterface java/util/List.isEmpty:()Z
            //   214: ifne            270
            //   217: goto            224
            //   220: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   223: athrow         
            //   224: aload           7
            //   226: iconst_0       
            //   227: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
            //   232: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
            //   235: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getName:()Ljava/lang/String;
            //   238: aload           4
            //   240: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getName:()Ljava/lang/String;
            //   243: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/String;Ljava/lang/String;)Z
            //   246: ifne            270
            //   249: goto            256
            //   252: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   255: athrow         
            //   256: aload_0        
            //   257: getstatic       com/jetbrains/cidr/lang/types/OCUnknownType.INSTANCE:Lcom/jetbrains/cidr/lang/types/OCUnknownType;
            //   260: putfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myAnswer:Lcom/jetbrains/cidr/lang/types/OCType;
            //   263: goto            270
            //   266: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   269: athrow         
            //   270: aload           7
            //   272: aload           4
            //   274: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
            //   279: pop            
            //   280: goto            327
            //   283: aload_0        
            //   284: aload_0        
            //   285: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myAnswer:Lcom/jetbrains/cidr/lang/types/OCType;
            //   288: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Lcom/jetbrains/cidr/lang/types/OCType;)Z
            //   291: ifeq            327
            //   294: new             Ljava/util/ArrayList;
            //   297: dup            
            //   298: invokespecial   java/util/ArrayList.<init>:()V
            //   301: astore          7
            //   303: aload           7
            //   305: aload           4
            //   307: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
            //   310: pop            
            //   311: aload_0        
            //   312: new             Lcom/jetbrains/cidr/lang/types/OCStructType;
            //   315: dup            
            //   316: aload           7
            //   318: aload_2        
            //   319: aload           6
            //   321: invokespecial   com/jetbrains/cidr/lang/types/OCStructType.<init>:(Ljava/util/List;Ljava/lang/String;Ljava/util/List;)V
            //   324: putfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myAnswer:Lcom/jetbrains/cidr/lang/types/OCType;
            //   327: return         
            //    Signature:
            //  (Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;Ljava/lang/String;Ljava/util/List<Lcom/jetbrains/cidr/lang/types/OCTypeArgument;>;)V
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  42     61     61     65     Ljava/lang/IllegalArgumentException;
            //  81     101    104    108    Ljava/lang/IllegalArgumentException;
            //  136    183    186    190    Ljava/lang/IllegalArgumentException;
            //  202    217    220    224    Ljava/lang/IllegalArgumentException;
            //  207    249    252    256    Ljava/lang/IllegalArgumentException;
            //  224    263    266    270    Ljava/lang/IllegalArgumentException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0224:
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
        
        public List<OCProtocolSymbol> getProtocols(final boolean b, final boolean b2) {
            final ArrayDeque<Object> arrayDeque = new ArrayDeque<Object>(this.myType.getProtocolNames());
            Label_0106: {
                Label_0033: {
                    try {
                        if (!b) {
                            break Label_0106;
                        }
                        final Resolver resolver = this;
                        final OCInterfaceSymbol ocInterfaceSymbol = resolver.myInterface;
                        if (ocInterfaceSymbol != null) {
                            break Label_0033;
                        }
                        break Label_0033;
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        final Resolver resolver = this;
                        final OCInterfaceSymbol ocInterfaceSymbol = resolver.myInterface;
                        if (ocInterfaceSymbol != null) {
                            arrayDeque.addAll(this.myInterface.getProtocolNames());
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
                final Iterator<OCInterfaceSymbol> iterator = this.myCategoryInterfaces.iterator();
                while (iterator.hasNext()) {
                    arrayDeque.addAll(iterator.next().getProtocolNames());
                }
            }
            final com.intellij.util.containers.HashSet set = new com.intellij.util.containers.HashSet();
            final ArrayList<OCProtocolSymbol> list = new ArrayList<OCProtocolSymbol>();
            while (!arrayDeque.isEmpty()) {
                final String s = arrayDeque.poll();
                try {
                    if (((Set)set).contains(s)) {
                        continue;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                ((Set<String>)set).add(s);
                OCProtocolSymbol ocProtocolSymbol = null;
                for (final OCSymbol ocSymbol : OCSymbolReference.getGlobalReference(s, null).resolveToSymbols((PsiFile)OCTypeResolveVisitor.this.myFile)) {
                    Label_0246: {
                        Label_0256: {
                            try {
                                if (!(ocSymbol instanceof OCProtocolSymbol)) {
                                    continue;
                                }
                                if (ocSymbol.isPredeclaration()) {
                                    break Label_0256;
                                }
                            }
                            catch (IllegalArgumentException ex4) {
                                throw a(ex4);
                            }
                            break Label_0246;
                        }
                        ocProtocolSymbol = (OCProtocolSymbol)ocSymbol;
                        continue;
                    }
                    ocProtocolSymbol = (OCProtocolSymbol)ocSymbol;
                    break;
                }
                Label_0292: {
                    try {
                        if (ocProtocolSymbol == null) {
                            continue;
                        }
                        final ArrayList<OCProtocolSymbol> list2 = list;
                        final OCProtocolSymbol ocProtocolSymbol2 = ocProtocolSymbol;
                        list2.add(ocProtocolSymbol2);
                        final boolean b3 = b2;
                        if (b3) {
                            break Label_0292;
                        }
                        continue;
                    }
                    catch (IllegalArgumentException ex5) {
                        throw a(ex5);
                    }
                    try {
                        final ArrayList<OCProtocolSymbol> list2 = list;
                        final OCProtocolSymbol ocProtocolSymbol2 = ocProtocolSymbol;
                        list2.add(ocProtocolSymbol2);
                        final boolean b3 = b2;
                        if (!b3) {
                            continue;
                        }
                        arrayDeque.addAll(ocProtocolSymbol.getProtocolNames());
                    }
                    catch (IllegalArgumentException ex6) {
                        throw a(ex6);
                    }
                }
            }
            return list;
        }
        
        @Nullable
        public OCType getAnswer() {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myAnswer:Lcom/jetbrains/cidr/lang/types/OCType;
            //     4: ifnull          16
            //     7: aload_0        
            //     8: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myAnswer:Lcom/jetbrains/cidr/lang/types/OCType;
            //    11: areturn        
            //    12: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    15: athrow         
            //    16: aload_0        
            //    17: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myInterface:Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
            //    20: ifnonnull       59
            //    23: aload_0        
            //    24: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myImplementation:Lcom/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol;
            //    27: ifnonnull       59
            //    30: goto            37
            //    33: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    36: athrow         
            //    37: aload_0        
            //    38: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myType:Lcom/jetbrains/cidr/lang/types/OCReferenceType;
            //    41: invokevirtual   com/jetbrains/cidr/lang/types/OCReferenceType.getProtocolNames:()Ljava/util/List;
            //    44: invokeinterface java/util/List.size:()I
            //    49: ifle            200
            //    52: goto            59
            //    55: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    58: athrow         
            //    59: aconst_null    
            //    60: astore_1       
            //    61: aload_0        
            //    62: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myInterface:Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
            //    65: ifnonnull       82
            //    68: aload_0        
            //    69: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myImplementation:Lcom/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol;
            //    72: ifnull          141
            //    75: goto            82
            //    78: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    81: athrow         
            //    82: aload_0        
            //    83: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myInterface:Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
            //    86: ifnull          112
            //    89: goto            96
            //    92: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    95: athrow         
            //    96: aload_0        
            //    97: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myInterface:Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
            //   100: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol.getSuperType:()Lcom/jetbrains/cidr/lang/types/OCReferenceType;
            //   105: goto            119
            //   108: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   111: athrow         
            //   112: aload_0        
            //   113: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myImplementation:Lcom/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol;
            //   116: invokevirtual   com/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol.getSuperType:()Lcom/jetbrains/cidr/lang/types/OCReferenceType;
            //   119: astore_2       
            //   120: aload_0        
            //   121: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.this$0:Lcom/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor;
            //   124: aload_2        
            //   125: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor.visitReferenceType:(Lcom/jetbrains/cidr/lang/types/OCReferenceType;)Lcom/jetbrains/cidr/lang/types/OCType;
            //   128: astore_3       
            //   129: aload_3        
            //   130: instanceof      Lcom/jetbrains/cidr/lang/types/OCObjectType;
            //   133: ifeq            141
            //   136: aload_3        
            //   137: checkcast       Lcom/jetbrains/cidr/lang/types/OCObjectType;
            //   140: astore_1       
            //   141: new             Lcom/jetbrains/cidr/lang/types/OCObjectType;
            //   144: dup            
            //   145: aload_0        
            //   146: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myInterface:Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
            //   149: aload_0        
            //   150: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myImplementation:Lcom/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol;
            //   153: aload_0        
            //   154: iconst_1       
            //   155: iconst_1       
            //   156: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.getProtocols:(ZZ)Ljava/util/List;
            //   159: aload_0        
            //   160: iconst_0       
            //   161: iconst_0       
            //   162: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.getProtocols:(ZZ)Ljava/util/List;
            //   165: aload_0        
            //   166: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myCategoryInterfaces:Ljava/util/List;
            //   169: aload_0        
            //   170: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myCategoryImplementations:Ljava/util/List;
            //   173: aload_1        
            //   174: iconst_0       
            //   175: iconst_0       
            //   176: aload_0        
            //   177: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myType:Lcom/jetbrains/cidr/lang/types/OCReferenceType;
            //   180: invokevirtual   com/jetbrains/cidr/lang/types/OCReferenceType.isKindof:()Z
            //   183: invokespecial   com/jetbrains/cidr/lang/types/OCObjectType.<init>:(Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;Lcom/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Lcom/jetbrains/cidr/lang/types/OCObjectType;ZZZ)V
            //   186: astore_2       
            //   187: aload_2        
            //   188: aload_0        
            //   189: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeResolveVisitor$Resolver.myType:Lcom/jetbrains/cidr/lang/types/OCReferenceType;
            //   192: invokevirtual   com/jetbrains/cidr/lang/types/OCReferenceType.getNullability:()Lcom/jetbrains/cidr/lang/types/OCNullability;
            //   195: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.attachNullability:(Lcom/jetbrains/cidr/lang/types/OCNullability;)V
            //   198: aload_2        
            //   199: areturn        
            //   200: aconst_null    
            //   201: areturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  0      12     12     16     Ljava/lang/IllegalArgumentException;
            //  16     30     33     37     Ljava/lang/IllegalArgumentException;
            //  23     52     55     59     Ljava/lang/IllegalArgumentException;
            //  61     75     78     82     Ljava/lang/IllegalArgumentException;
            //  68     89     92     96     Ljava/lang/IllegalArgumentException;
            //  82     108    108    112    Ljava/lang/IllegalArgumentException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0082:
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    public static class OCObjectTypeReResolver extends OCTypeCloneVisitor
    {
        private PsiFile myFile;
        
        public OCObjectTypeReResolver(@Nullable final PsiFile myFile) {
            super(false);
            this.myFile = myFile;
        }
        
        @Override
        public OCType visitObjectType(final OCObjectType ocObjectType) {
            return OCReferenceType.fromText(ocObjectType.getClassName(), ContainerUtil.map((Collection)ocObjectType.getAllProtocols(), ocProtocolSymbol -> ocProtocolSymbol.getName())).resolve(this.myFile, true);
        }
    }
}
