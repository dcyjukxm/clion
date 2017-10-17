// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.objc;

import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.OCIcons;
import com.jetbrains.cidr.lang.OCTestFrameworks;
import icons.CidrLangIcons;
import javax.swing.Icon;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.resolve.OCResolveUtil;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.symtable.OCGlobalProjectSymbolsCache;
import java.util.Set;
import com.intellij.psi.PsiElement;
import com.intellij.util.Processor;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import java.util.List;
import com.intellij.util.containers.MostlySingularMultiMap;
import com.jetbrains.cidr.lang.psi.OCClassDeclarationBase;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;

public abstract class OCClassSymbolImpl extends OCSymbolWithQualifiedName<OCClassDeclarationBase> implements OCClassSymbol
{
    private String myCategoryName;
    private MostlySingularMultiMap<String, OCMemberSymbol> myMembers;
    private List<String> myProtocolNames;
    private OCReferenceType mySuperType;
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public OCClassSymbolImpl(@NotNull final Project project, @Nullable final VirtualFile virtualFile, final long n, @NotNull final String s, @NotNull final List<String> list, @Nullable final String myCategoryName, @Nullable final MostlySingularMultiMap<String, OCMemberSymbol> myMembers, @NotNull final List<String> myProtocolNames, @Nullable final OCReferenceType mySuperType) {
        if (project == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl", "<init>"));
        }
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl", "<init>"));
        }
        if (list == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "attributes", "com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl", "<init>"));
        }
        if (myProtocolNames == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "protocolNames", "com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl", "<init>"));
        }
        super(project, virtualFile, n, null, OCQualifiedName.interned(s), list, null);
        this.myCategoryName = myCategoryName;
        this.myMembers = myMembers;
        this.myProtocolNames = myProtocolNames;
        this.mySuperType = mySuperType;
    }
    
    public OCClassSymbolImpl() {
    }
    
    @NotNull
    @Override
    public Project getProject() {
        Project project;
        try {
            project = super.getProject();
            if (project == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl", "getProject"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return project;
    }
    
    @Override
    public String getCategoryName() {
        return this.myCategoryName;
    }
    
    @Override
    public void setSuperType(@NotNull final OCReferenceType mySuperType) {
        try {
            if (mySuperType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl", "setSuperType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        Label_0064: {
            try {
                if (OCClassSymbolImpl.$assertionsDisabled) {
                    break Label_0064;
                }
                final OCClassSymbolImpl ocClassSymbolImpl = this;
                final OCReferenceType ocReferenceType = ocClassSymbolImpl.mySuperType;
                if (ocReferenceType != null) {
                    break Label_0064;
                }
                break Label_0064;
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
            try {
                final OCClassSymbolImpl ocClassSymbolImpl = this;
                final OCReferenceType ocReferenceType = ocClassSymbolImpl.mySuperType;
                if (ocReferenceType != null) {
                    throw new AssertionError((Object)"Super type is already set");
                }
            }
            catch (IllegalArgumentException ex3) {
                throw c(ex3);
            }
        }
        this.mySuperType = mySuperType;
    }
    
    @NotNull
    @Override
    public OCReferenceType getSuperType() {
        OCReferenceType mySuperType = null;
        Label_0034: {
            Label_0020: {
                try {
                    if (OCClassSymbolImpl.$assertionsDisabled) {
                        break Label_0034;
                    }
                    final OCClassSymbolImpl ocClassSymbolImpl = this;
                    final OCReferenceType ocReferenceType = ocClassSymbolImpl.mySuperType;
                    if (ocReferenceType == null) {
                        break Label_0020;
                    }
                    break Label_0034;
                }
                catch (IllegalArgumentException ex) {
                    throw c(ex);
                }
                try {
                    final OCClassSymbolImpl ocClassSymbolImpl = this;
                    final OCReferenceType ocReferenceType = ocClassSymbolImpl.mySuperType;
                    if (ocReferenceType == null) {
                        throw new AssertionError((Object)"Super type has to be set in symbol builder");
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw c(ex2);
                }
            }
            try {
                mySuperType = this.mySuperType;
                if (mySuperType == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl", "getSuperType"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw c(ex3);
            }
        }
        return mySuperType;
    }
    
    @NotNull
    @Override
    public String getSuperClassName() {
        String notNullize;
        try {
            notNullize = StringUtil.notNullize(this.getSuperType().getReference().getQualifiedName().getName());
            if (notNullize == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl", "getSuperClassName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return notNullize;
    }
    
    @Override
    public void updateOffset(final int n, final int n2, final int n3) {
        try {
            super.updateOffset(n, n2, n3);
            if (this.myMembers != null) {
                this.myMembers.processAllValues(ocMemberSymbol -> {
                    ocMemberSymbol.updateOffset(n, n2, n3);
                    return true;
                });
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
    }
    
    @Override
    public void compact() {
        try {
            super.compact();
            if (this.myMembers != null) {
                this.myMembers.processAllValues(ocMemberSymbol -> {
                    ocMemberSymbol.compact();
                    return true;
                });
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
    }
    
    @NotNull
    @Override
    public List<String> getProtocolNames() {
        List<String> myProtocolNames;
        try {
            myProtocolNames = this.myProtocolNames;
            if (myProtocolNames == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl", "getProtocolNames"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return myProtocolNames;
    }
    
    @Override
    public boolean isDefinition() {
        return false;
    }
    
    @Override
    public boolean isPredeclaration() {
        try {
            if (this.myMembers == null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return false;
    }
    
    @Override
    public int getMembersCount() {
        try {
            if (this.myMembers != null) {
                return this.myMembers.size();
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return 0;
    }
    
    @Override
    public int hashCodeExcludingOffset() {
        return 31 * super.hashCodeExcludingOffset() + this.getMembersCount();
    }
    
    @Override
    public boolean equals(final Object o) {
        Label_0031: {
            try {
                if (!super.equals(o)) {
                    return false;
                }
                final OCClassSymbolImpl ocClassSymbolImpl = this;
                final int n = ocClassSymbolImpl.getMembersCount();
                final Object o2 = o;
                final OCClassSymbol ocClassSymbol = (OCClassSymbol)o2;
                final int n2 = ocClassSymbol.getMembersCount();
                if (n == n2) {
                    break Label_0031;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            try {
                final OCClassSymbolImpl ocClassSymbolImpl = this;
                final int n = ocClassSymbolImpl.getMembersCount();
                final Object o2 = o;
                final OCClassSymbol ocClassSymbol = (OCClassSymbol)o2;
                final int n2 = ocClassSymbol.getMembersCount();
                if (n == n2) {
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
    public boolean isGlobal() {
        return true;
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw c(ex3);
        }
        try {
            if (!super.deepEqualStep(comparator, o, o2)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw c(ex4);
        }
        final OCClassSymbolImpl ocClassSymbolImpl = (OCClassSymbolImpl)o;
        final OCClassSymbolImpl ocClassSymbolImpl2 = (OCClassSymbolImpl)o2;
        try {
            if (!Comparing.equal(ocClassSymbolImpl.myCategoryName, ocClassSymbolImpl2.myCategoryName)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw c(ex5);
        }
        try {
            if (!Comparing.equal((Object)ocClassSymbolImpl.myProtocolNames, (Object)ocClassSymbolImpl2.myProtocolNames)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw c(ex6);
        }
        try {
            if (!comparator.equalObjects(ocClassSymbolImpl.mySuperType, (DeepEqual.Equality<Object>)ocClassSymbolImpl2.mySuperType)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex7) {
            throw c(ex7);
        }
        return comparator.equalMultiMaps(ocClassSymbolImpl.myMembers, ocClassSymbolImpl2.myMembers);
    }
    
    @Override
    public boolean processMembers(final String s, @NotNull final Processor<OCMemberSymbol> processor) {
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl", "processMembers"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (this.myMembers == null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        try {
            if (s == null) {
                return this.myMembers.processAllValues((Processor)processor);
            }
        }
        catch (IllegalArgumentException ex3) {
            throw c(ex3);
        }
        return this.myMembers.processForKey((Object)s, (Processor)processor);
    }
    
    @Override
    public <T extends OCMemberSymbol> boolean processMembers(final Class<T> clazz, final Processor<? super T> processor) {
        return this.processMembers((String)null, (Class<? extends OCMemberSymbol>)clazz, (com.intellij.util.Processor<? super OCMemberSymbol>)processor);
    }
    
    @Override
    public <T extends OCMemberSymbol> boolean processMembers(@Nullable final String s, @NotNull final Class<? extends T> clazz, final Processor<? super T> processor) {
        try {
            if (clazz == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "memberClass", "com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl", "processMembers"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (this.myMembers == null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        final Processor processor2 = ocMemberSymbol -> {
            try {
                if (clazz == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "memberClass", "com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl", "lambda$processMembers$2"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            Label_0072: {
                try {
                    if (!clazz.isAssignableFrom(ocMemberSymbol.getClass())) {
                        break Label_0072;
                    }
                    final Processor processor2 = processor;
                    final OCMemberSymbol ocMemberSymbol2 = ocMemberSymbol;
                    final boolean b = processor2.process((Object)ocMemberSymbol2);
                    if (b) {
                        break Label_0072;
                    }
                    return false;
                }
                catch (IllegalArgumentException ex2) {
                    throw c(ex2);
                }
                try {
                    final Processor processor2 = processor;
                    final OCMemberSymbol ocMemberSymbol2 = ocMemberSymbol;
                    final boolean b = processor2.process((Object)ocMemberSymbol2);
                    if (b) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw c(ex3);
                }
            }
            return false;
        };
        try {
            if (s == null) {
                return this.myMembers.processAllValues(processor2);
            }
        }
        catch (IllegalArgumentException ex3) {
            throw c(ex3);
        }
        return this.myMembers.processForKey((Object)s, processor2);
    }
    
    @Override
    public <T extends OCMemberSymbol> boolean processMembersInAllCategories(@Nullable final String s, final Class<? extends T> clazz, final Processor<? super T> processor) {
        return this.processMembersInAllCategories(s, (Class<? extends OCMemberSymbol>)clazz, (com.intellij.util.Processor<? super OCMemberSymbol>)processor, true);
    }
    
    @Override
    public <T extends OCMemberSymbol> boolean processMembersInAllCategories(@Nullable final String s, final Class<? extends T> clazz, final Processor<? super T> processor, final boolean b) {
        return this.processCategories((Processor<? super OCClassSymbol>)(ocClassSymbol -> ocClassSymbol.processMembers(s, (Class<? extends OCMemberSymbol>)clazz, (com.intellij.util.Processor<? super OCMemberSymbol>)processor)), b, null);
    }
    
    @Override
    public <T extends OCMemberSymbol> boolean processMembers(@Nullable final String s, @Nullable final String s2, @NotNull final Class<? extends T> clazz, @NotNull final Processor<? super T> processor, final boolean b) {
        try {
            if (clazz == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "memberClass", "com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl", "processMembers"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl", "processMembers"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        return this.processCategories((Processor<? super OCClassSymbol>)(ocClassSymbol -> {
            try {
                if (clazz == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "memberClass", "com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl", "lambda$processMembers$4"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            try {
                if (processor == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl", "lambda$processMembers$4"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
            Label_0122: {
                try {
                    if (!Comparing.equal(s, ocClassSymbol.getCategoryName())) {
                        break Label_0122;
                    }
                    final OCClassSymbol ocClassSymbol2 = ocClassSymbol;
                    final String s3 = s2;
                    final Class<? extends T> clazz2 = (Class<? extends T>)clazz;
                    final Processor processor2 = processor;
                    final boolean b = ocClassSymbol2.processMembers(s3, (Class<? extends OCMemberSymbol>)clazz2, (com.intellij.util.Processor<? super OCMemberSymbol>)processor2);
                    if (b) {
                        break Label_0122;
                    }
                    return false;
                }
                catch (IllegalArgumentException ex3) {
                    throw c(ex3);
                }
                try {
                    final OCClassSymbol ocClassSymbol2 = ocClassSymbol;
                    final String s3 = s2;
                    final Class<? extends T> clazz2 = (Class<? extends T>)clazz;
                    final Processor processor2 = processor;
                    final boolean b = ocClassSymbol2.processMembers(s3, (Class<? extends OCMemberSymbol>)clazz2, (com.intellij.util.Processor<? super OCMemberSymbol>)processor2);
                    if (b) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw c(ex4);
                }
            }
            return false;
        }), b, null);
    }
    
    @Override
    public boolean processAllMethods(@Nullable final String p0, @NotNull final Processor<OCMethodSymbol> p1, @Nullable final Set<String> p2, @Nullable final PsiElement p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "processor"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "processAllMethods"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCProtocolSymbol;
        //    48: ifeq            90
        //    51: aload_3        
        //    52: ifnull          90
        //    55: goto            62
        //    58: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    61: athrow         
        //    62: aload_3        
        //    63: aload_0        
        //    64: invokevirtual   com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl.getName:()Ljava/lang/String;
        //    67: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //    72: ifeq            90
        //    75: goto            82
        //    78: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    81: athrow         
        //    82: iconst_1       
        //    83: goto            91
        //    86: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    89: athrow         
        //    90: iconst_0       
        //    91: istore          5
        //    93: aload_0        
        //    94: aload_1        
        //    95: ldc             Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;.class
        //    97: iload           5
        //    99: aload_2        
        //   100: invokedynamic   process:(ZLcom/intellij/util/Processor;)Lcom/intellij/util/Processor;
        //   105: invokevirtual   com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl.processMembers:(Ljava/lang/String;Ljava/lang/Class;Lcom/intellij/util/Processor;)Z
        //   108: ifne            117
        //   111: iconst_0       
        //   112: ireturn        
        //   113: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   116: athrow         
        //   117: iload           5
        //   119: ifeq            140
        //   122: aload_3        
        //   123: aload_0        
        //   124: getfield        com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl.myProtocolNames:Ljava/util/List;
        //   127: invokeinterface java/util/Set.addAll:(Ljava/util/Collection;)Z
        //   132: pop            
        //   133: goto            140
        //   136: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   139: athrow         
        //   140: aload_0        
        //   141: getfield        com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl.myProtocolNames:Ljava/util/List;
        //   144: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   149: astore          6
        //   151: aload           6
        //   153: invokeinterface java/util/Iterator.hasNext:()Z
        //   158: ifeq            238
        //   161: aload           6
        //   163: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   168: checkcast       Ljava/lang/String;
        //   171: astore          7
        //   173: new             Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl$1;
        //   176: dup            
        //   177: aload_0        
        //   178: invokespecial   com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl$1.<init>:(Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl;)V
        //   181: astore          8
        //   183: aload           7
        //   185: aload           4
        //   187: aload           8
        //   189: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.processGlobalSymbols:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;Lcom/intellij/util/Processor;)Z
        //   192: pop            
        //   193: aload           8
        //   195: invokevirtual   com/intellij/util/CommonProcessors$FindFirstProcessor.isFound:()Z
        //   198: ifeq            235
        //   201: aload           8
        //   203: invokevirtual   com/intellij/util/CommonProcessors$FindFirstProcessor.getFoundValue:()Ljava/lang/Object;
        //   206: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCProtocolSymbol;
        //   209: aload_1        
        //   210: aload_2        
        //   211: aload_3        
        //   212: aload           4
        //   214: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCProtocolSymbol.processAllMethods:(Ljava/lang/String;Lcom/intellij/util/Processor;Ljava/util/Set;Lcom/intellij/psi/PsiElement;)Z
        //   219: ifne            235
        //   222: goto            229
        //   225: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   228: athrow         
        //   229: iconst_0       
        //   230: ireturn        
        //   231: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   234: athrow         
        //   235: goto            151
        //   238: iconst_1       
        //   239: ireturn        
        //    Signature:
        //  (Ljava/lang/String;Lcom/intellij/util/Processor<Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;>;Ljava/util/Set<Ljava/lang/String;>;Lcom/intellij/psi/PsiElement;)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     55     58     62     Ljava/lang/IllegalArgumentException;
        //  51     75     78     82     Ljava/lang/IllegalArgumentException;
        //  62     86     86     90     Ljava/lang/IllegalArgumentException;
        //  93     113    113    117    Ljava/lang/IllegalArgumentException;
        //  117    133    136    140    Ljava/lang/IllegalArgumentException;
        //  183    222    225    229    Ljava/lang/IllegalArgumentException;
        //  201    231    231    235    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0062:
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
    public boolean processCategories(final Processor<? super OCClassSymbol> processor, final boolean b, @Nullable final PsiElement psiElement) {
        try {
            if (!processor.process((Object)this)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        final Processor processor2 = p2 -> {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_3        
            //     1: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
            //     4: ifeq            85
            //     7: iload_1        
            //     8: ifeq            39
            //    11: goto            18
            //    14: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    17: athrow         
            //    18: aload_3        
            //    19: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
            //    22: aload_0        
            //    23: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
            //    26: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
            //    29: ifeq            85
            //    32: goto            39
            //    35: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    38: athrow         
            //    39: aload_3        
            //    40: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isPredeclaration:()Z
            //    45: ifne            85
            //    48: goto            55
            //    51: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    54: athrow         
            //    55: aload_3        
            //    56: aload_0        
            //    57: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
            //    60: ifne            85
            //    63: goto            70
            //    66: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    69: athrow         
            //    70: aload_2        
            //    71: aload_3        
            //    72: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
            //    75: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
            //    80: ireturn        
            //    81: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    84: athrow         
            //    85: iconst_1       
            //    86: ireturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  0      11     14     18     Ljava/lang/IllegalArgumentException;
            //  7      32     35     39     Ljava/lang/IllegalArgumentException;
            //  18     48     51     55     Ljava/lang/IllegalArgumentException;
            //  39     63     66     70     Ljava/lang/IllegalArgumentException;
            //  55     81     81     85     Ljava/lang/IllegalArgumentException;
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        };
        try {
            if (psiElement == null) {
                return OCGlobalProjectSymbolsCache.processTopLevelSymbols(this.myProject, (Processor<OCSymbol>)processor2, this.myName);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        return OCResolveUtil.processGlobalSymbols(this.myName, null, (OCFile)psiElement.getContainingFile(), this.getOffset(), (Processor<OCSymbol>)processor2);
    }
    
    @Override
    public boolean processSameSymbols(final Processor<OCSymbol> processor) {
        return OCGlobalProjectSymbolsCache.processTopLevelSymbols(this.myProject, (Processor<OCSymbol>)(p1 -> {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_2        
            //     1: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl;
            //     4: ifeq            42
            //     7: aload_0        
            //     8: aload_2        
            //     9: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl;
            //    12: invokevirtual   com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl.isSameClass:(Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;)Z
            //    15: ifeq            42
            //    18: goto            25
            //    21: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    24: athrow         
            //    25: aload_1        
            //    26: aload_2        
            //    27: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
            //    32: ifeq            50
            //    35: goto            42
            //    38: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    41: athrow         
            //    42: iconst_1       
            //    43: goto            51
            //    46: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    49: athrow         
            //    50: iconst_0       
            //    51: ireturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  0      18     21     25     Ljava/lang/IllegalArgumentException;
            //  7      35     38     42     Ljava/lang/IllegalArgumentException;
            //  25     46     46     50     Ljava/lang/IllegalArgumentException;
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
        }), this.myName);
    }
    
    @Nullable
    @Override
    public OCClassSymbol getDefinitionSymbol() {
        return (OCClassSymbol)super.getDefinitionSymbol();
    }
    
    @Nullable
    @Override
    public OCInterfaceSymbol getInterface() {
        return null;
    }
    
    @Nullable
    @Override
    public OCClassSymbol getInterfaceOrProtocol() {
        return this.getInterface();
    }
    
    @Nullable
    @Override
    public OCImplementationSymbol getImplementation() {
        return null;
    }
    
    @Nullable
    @Override
    public OCInterfaceSymbol getMainInterface() {
        return this.a(this.getInterface());
    }
    
    @Nullable
    @Override
    public OCImplementationSymbol getMainImplementation() {
        return this.a(this.getImplementation());
    }
    
    protected MostlySingularMultiMap<String, OCMemberSymbol> getMembers() {
        return this.myMembers;
    }
    
    @Nullable
    private <T extends OCClassSymbol> T a(@Nullable final T t) {
        try {
            if (t == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (this.myCategoryName == null) {
                return t;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        final CommonProcessors.FindFirstProcessor<OCClassSymbol> findFirstProcessor = new CommonProcessors.FindFirstProcessor<OCClassSymbol>() {
            protected boolean accept(final OCClassSymbol ocClassSymbol) {
                return ocClassSymbol.getCategoryName() == null;
            }
        };
        t.processCategories((Processor<? super OCClassSymbol>)findFirstProcessor, true, null);
        return (T)findFirstProcessor.getFoundValue();
    }
    
    @NotNull
    @Override
    public String getPresentableName() {
        String classNameWithCategory;
        try {
            classNameWithCategory = OCCodeInsightUtil.getClassNameWithCategory(super.getPresentableName(), this.myCategoryName);
            if (classNameWithCategory == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl", "getPresentableName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return classNameWithCategory;
    }
    
    @Nullable
    @Override
    public Icon getBaseIcon() {
        try {
            if (this.myCategoryName == null) {
                return super.getBaseIcon();
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return CidrLangIcons.CodeAssistantClassExtension;
    }
    
    @Override
    public Icon computeFullIconNow(@Nullable final OCClassDeclarationBase ocClassDeclarationBase) {
        Icon icon = null;
        Label_0022: {
            try {
                if (this.myCategoryName == null) {
                    icon = super.computeFullIconNow(ocClassDeclarationBase);
                    break Label_0022;
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            icon = CidrLangIcons.CodeAssistantClassExtension;
        }
        final Icon icon2 = icon;
        try {
            if (icon2 == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        Label_0064: {
            Label_0051: {
                try {
                    if (ocClassDeclarationBase == null) {
                        break Label_0064;
                    }
                    final OCClassDeclarationBase ocClassDeclarationBase2 = ocClassDeclarationBase;
                    final boolean b = OCTestFrameworks.isTestClass((PsiElement)ocClassDeclarationBase2);
                    if (b) {
                        break Label_0051;
                    }
                    return icon2;
                }
                catch (IllegalArgumentException ex3) {
                    throw c(ex3);
                }
                try {
                    final OCClassDeclarationBase ocClassDeclarationBase2 = ocClassDeclarationBase;
                    final boolean b = OCTestFrameworks.isTestClass((PsiElement)ocClassDeclarationBase2);
                    if (b) {
                        return OCIcons.getTestIcon(icon2);
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw c(ex4);
                }
            }
            return icon2;
            try {
                if (OCTestFrameworks.isTestClassOrStruct(this)) {
                    return OCIcons.getTestIcon(icon2);
                }
            }
            catch (IllegalArgumentException ex5) {
                throw c(ex5);
            }
        }
        return icon2;
    }
    
    @NotNull
    @Override
    public OCType getType() {
        OCReferenceType fromText;
        try {
            fromText = OCReferenceType.fromText(this.getName());
            if (fromText == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl", "getType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return fromText;
    }
    
    @Nullable
    @Override
    public OCObjectType getResolvedType(final boolean b) {
        final OCType resolvedType = super.getResolvedType(b);
        try {
            if (resolvedType instanceof OCObjectType) {
                return (OCObjectType)resolvedType;
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        return null;
    }
    
    @Override
    public boolean isSameClass(final OCClassSymbol ocClassSymbol) {
        Label_0034: {
            try {
                if (!Comparing.equal(this.myName, ocClassSymbol.getName())) {
                    return false;
                }
                final OCClassSymbolImpl ocClassSymbolImpl = this;
                final boolean b = ocClassSymbolImpl instanceof OCProtocolSymbol;
                final OCClassSymbol ocClassSymbol2 = ocClassSymbol;
                final boolean b2 = ocClassSymbol2 instanceof OCProtocolSymbol;
                if (b == b2) {
                    break Label_0034;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            try {
                final OCClassSymbolImpl ocClassSymbolImpl = this;
                final boolean b = ocClassSymbolImpl instanceof OCProtocolSymbol;
                final OCClassSymbol ocClassSymbol2 = ocClassSymbol;
                final boolean b2 = ocClassSymbol2 instanceof OCProtocolSymbol;
                if (b == b2) {
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
    public boolean isSameSymbol(final OCSymbol ocSymbol) {
        Label_0025: {
            try {
                if (!(ocSymbol instanceof OCClassSymbolImpl)) {
                    return false;
                }
                final OCClassSymbolImpl ocClassSymbolImpl = this;
                final OCClassSymbolImpl ocClassSymbolImpl2 = (OCClassSymbolImpl)ocSymbol;
                final OCClassSymbolImpl ocClassSymbolImpl3 = ocClassSymbolImpl2;
                final boolean b = ocClassSymbolImpl.isSameClass(ocClassSymbolImpl3);
                if (b) {
                    break Label_0025;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            try {
                final OCClassSymbolImpl ocClassSymbolImpl = this;
                final OCClassSymbolImpl ocClassSymbolImpl2 = (OCClassSymbolImpl)ocSymbol;
                final OCClassSymbolImpl ocClassSymbolImpl3 = ocClassSymbolImpl2;
                final boolean b = ocClassSymbolImpl.isSameClass(ocClassSymbolImpl3);
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
    public boolean isSameCategory(final OCSymbol p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl;
        //     4: ifeq            95
        //     7: aload_0        
        //     8: getfield        com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl.myName:Ljava/lang/String;
        //    11: aload_1        
        //    12: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //    17: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    20: ifeq            95
        //    23: goto            30
        //    26: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    29: athrow         
        //    30: aload_0        
        //    31: getfield        com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl.myCategoryName:Ljava/lang/String;
        //    34: ifnonnull       53
        //    37: goto            44
        //    40: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: ldc             ""
        //    46: goto            57
        //    49: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    52: athrow         
        //    53: aload_0        
        //    54: getfield        com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl.myCategoryName:Ljava/lang/String;
        //    57: astore_2       
        //    58: aload_1        
        //    59: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //    62: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getCategoryName:()Ljava/lang/String;
        //    67: ifnonnull       79
        //    70: ldc             ""
        //    72: goto            88
        //    75: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    78: athrow         
        //    79: aload_1        
        //    80: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //    83: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getCategoryName:()Ljava/lang/String;
        //    88: astore_3       
        //    89: aload_2        
        //    90: aload_3        
        //    91: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    94: ireturn        
        //    95: iconst_0       
        //    96: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      23     26     30     Ljava/lang/IllegalArgumentException;
        //  7      37     40     44     Ljava/lang/IllegalArgumentException;
        //  30     49     49     53     Ljava/lang/IllegalArgumentException;
        //  58     75     75     79     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0030:
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
    public boolean isSubclass(final OCClassSymbol p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getName:()Ljava/lang/String;
        //     6: astore_2       
        //     7: aload_0        
        //     8: aload_1        
        //     9: invokevirtual   com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl.isSameClass:(Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;)Z
        //    12: ifne            31
        //    15: aload_2        
        //    16: ldc             "id"
        //    18: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    21: ifeq            37
        //    24: goto            31
        //    27: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    30: athrow         
        //    31: iconst_1       
        //    32: ireturn        
        //    33: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    36: athrow         
        //    37: aload_1        
        //    38: invokestatic    com/jetbrains/cidr/lang/search/scopes/OCSearchScope.isInLibraries:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //    41: istore_3       
        //    42: iload_3        
        //    43: ifne            76
        //    46: aload_1        
        //    47: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getCategoryName:()Ljava/lang/String;
        //    52: ifnull          76
        //    55: goto            62
        //    58: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    61: athrow         
        //    62: aload_1        
        //    63: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getMainInterface:()Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
        //    68: astore          4
        //    70: aload           4
        //    72: invokestatic    com/jetbrains/cidr/lang/search/scopes/OCSearchScope.isInLibraries:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //    75: istore_3       
        //    76: new             Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl$1DFS;
        //    79: dup            
        //    80: aload_0        
        //    81: aload_2        
        //    82: aload_1        
        //    83: invokespecial   com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl$1DFS.<init>:(Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl;Ljava/lang/String;Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;)V
        //    86: astore          4
        //    88: aload_0        
        //    89: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCProtocolSymbol;
        //    92: ifeq            122
        //    95: aload           4
        //    97: aload_0        
        //    98: invokevirtual   com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl.getName:()Ljava/lang/String;
        //   101: ldc             Lcom/jetbrains/cidr/lang/symbols/objc/OCProtocolSymbol;.class
        //   103: invokevirtual   com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl$1DFS.traverse:(Ljava/lang/String;Ljava/lang/Class;)Z
        //   106: ifeq            177
        //   109: goto            116
        //   112: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   115: athrow         
        //   116: iconst_1       
        //   117: ireturn        
        //   118: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   121: athrow         
        //   122: aload           4
        //   124: aload_0        
        //   125: invokevirtual   com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl.getName:()Ljava/lang/String;
        //   128: ldc             Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;.class
        //   130: invokevirtual   com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl$1DFS.traverse:(Ljava/lang/String;Ljava/lang/Class;)Z
        //   133: ifeq            142
        //   136: iconst_1       
        //   137: ireturn        
        //   138: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   141: athrow         
        //   142: aload           4
        //   144: getfield        com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl$1DFS.wasProcessed:Z
        //   147: ifne            177
        //   150: aload           4
        //   152: aload_0        
        //   153: invokevirtual   com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl.getName:()Ljava/lang/String;
        //   156: ldc             Lcom/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol;.class
        //   158: invokevirtual   com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl$1DFS.traverse:(Ljava/lang/String;Ljava/lang/Class;)Z
        //   161: ifeq            177
        //   164: goto            171
        //   167: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   170: athrow         
        //   171: iconst_1       
        //   172: ireturn        
        //   173: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   176: athrow         
        //   177: aload           4
        //   179: getfield        com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl$1DFS.stack:Ljava/util/Stack;
        //   182: invokevirtual   java/util/Stack.isEmpty:()Z
        //   185: ifne            378
        //   188: aload           4
        //   190: getfield        com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl$1DFS.stack:Ljava/util/Stack;
        //   193: invokevirtual   java/util/Stack.pop:()Ljava/lang/Object;
        //   196: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   199: astore          5
        //   201: iload_3        
        //   202: ifne            227
        //   205: aload           5
        //   207: invokestatic    com/jetbrains/cidr/lang/search/scopes/OCSearchScope.isInLibraries:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //   210: ifeq            227
        //   213: goto            220
        //   216: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   219: athrow         
        //   220: goto            177
        //   223: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   226: athrow         
        //   227: aload           5
        //   229: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
        //   232: ifne            250
        //   235: aload           5
        //   237: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol;
        //   240: ifeq            318
        //   243: goto            250
        //   246: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   249: athrow         
        //   250: aload           4
        //   252: aload           5
        //   254: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getSuperClassName:()Ljava/lang/String;
        //   259: ldc             Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;.class
        //   261: invokevirtual   com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl$1DFS.traverse:(Ljava/lang/String;Ljava/lang/Class;)Z
        //   264: ifeq            280
        //   267: goto            274
        //   270: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   273: athrow         
        //   274: iconst_1       
        //   275: ireturn        
        //   276: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   279: athrow         
        //   280: aload           4
        //   282: getfield        com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl$1DFS.wasProcessed:Z
        //   285: ifne            318
        //   288: aload           4
        //   290: aload           5
        //   292: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getSuperClassName:()Ljava/lang/String;
        //   297: ldc             Lcom/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol;.class
        //   299: invokevirtual   com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl$1DFS.traverse:(Ljava/lang/String;Ljava/lang/Class;)Z
        //   302: ifeq            318
        //   305: goto            312
        //   308: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   311: athrow         
        //   312: iconst_1       
        //   313: ireturn        
        //   314: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   317: athrow         
        //   318: aload           5
        //   320: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getProtocolNames:()Ljava/util/List;
        //   325: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   330: astore          6
        //   332: aload           6
        //   334: invokeinterface java/util/Iterator.hasNext:()Z
        //   339: ifeq            375
        //   342: aload           6
        //   344: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   349: checkcast       Ljava/lang/String;
        //   352: astore          7
        //   354: aload           4
        //   356: aload           7
        //   358: ldc             Lcom/jetbrains/cidr/lang/symbols/objc/OCProtocolSymbol;.class
        //   360: invokevirtual   com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl$1DFS.traverse:(Ljava/lang/String;Ljava/lang/Class;)Z
        //   363: ifeq            372
        //   366: iconst_1       
        //   367: ireturn        
        //   368: invokestatic    com/jetbrains/cidr/lang/symbols/objc/OCClassSymbolImpl.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   371: athrow         
        //   372: goto            332
        //   375: goto            177
        //   378: iconst_0       
        //   379: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  7      24     27     31     Ljava/lang/IllegalArgumentException;
        //  15     33     33     37     Ljava/lang/IllegalArgumentException;
        //  42     55     58     62     Ljava/lang/IllegalArgumentException;
        //  88     109    112    116    Ljava/lang/IllegalArgumentException;
        //  95     118    118    122    Ljava/lang/IllegalArgumentException;
        //  122    138    138    142    Ljava/lang/IllegalArgumentException;
        //  142    164    167    171    Ljava/lang/IllegalArgumentException;
        //  150    173    173    177    Ljava/lang/IllegalArgumentException;
        //  201    213    216    220    Ljava/lang/IllegalArgumentException;
        //  205    223    223    227    Ljava/lang/IllegalArgumentException;
        //  227    243    246    250    Ljava/lang/IllegalArgumentException;
        //  235    267    270    274    Ljava/lang/IllegalArgumentException;
        //  250    276    276    280    Ljava/lang/IllegalArgumentException;
        //  280    305    308    312    Ljava/lang/IllegalArgumentException;
        //  288    314    314    318    Ljava/lang/IllegalArgumentException;
        //  354    368    368    372    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0250:
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
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCClassSymbolImpl.class.desiredAssertionStatus()) {
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
