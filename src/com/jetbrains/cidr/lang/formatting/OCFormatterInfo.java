// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.formatting;

import org.jetbrains.annotations.Contract;
import gnu.trove.THashMap;
import com.intellij.openapi.util.Computable;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.tree.IElementType;
import com.intellij.formatting.Indent;
import com.intellij.formatting.Alignment;
import org.jetbrains.annotations.Nullable;
import com.intellij.formatting.Wrap;

public class OCFormatterInfo
{
    @Nullable
    public final Wrap wrap;
    @Nullable
    public final Alignment alignment;
    @Nullable
    public final Indent indent;
    public static OCFormatterInfo EMPTY;
    
    private OCFormatterInfo(@Nullable final Wrap wrap, @Nullable final Alignment alignment, @Nullable final Indent indent) {
        this.alignment = alignment;
        this.wrap = wrap;
        this.indent = indent;
    }
    
    @NotNull
    public static OCLocalFormatterData createData(@Nullable final OCLocalFormatterData ocLocalFormatterData, @NotNull final IElementType elementType) {
        try {
            if (elementType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/formatting/OCFormatterInfo", "createData"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCLocalFormatterDataImpl ocLocalFormatterDataImpl;
        try {
            ocLocalFormatterDataImpl = new OCLocalFormatterDataImpl(ocLocalFormatterData, elementType);
            if (ocLocalFormatterDataImpl == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCFormatterInfo", "createData"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return ocLocalFormatterDataImpl;
    }
    
    @NotNull
    static OCFormatterInfo createInfo(@Nullable final Alignment alignment) {
        OCFormatterInfo info;
        try {
            info = createInfo(null, alignment, null);
            if (info == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCFormatterInfo", "createInfo"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return info;
    }
    
    @NotNull
    static OCFormatterInfo createInfo(@Nullable final Indent indent) {
        OCFormatterInfo info;
        try {
            info = createInfo(null, null, indent);
            if (info == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCFormatterInfo", "createInfo"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return info;
    }
    
    @NotNull
    static OCFormatterInfo createInfo(@Nullable final Wrap wrap, @Nullable final Alignment alignment) {
        OCFormatterInfo info;
        try {
            info = createInfo(wrap, alignment, null);
            if (info == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCFormatterInfo", "createInfo"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return info;
    }
    
    @NotNull
    static OCFormatterInfo createInfo(@Nullable final Wrap wrap, @Nullable final Alignment alignment, @Nullable final Indent indent) {
        OCFormatterInfo ocFormatterInfo;
        try {
            ocFormatterInfo = new OCFormatterInfo(wrap, alignment, indent);
            if (ocFormatterInfo == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCFormatterInfo", "createInfo"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return ocFormatterInfo;
    }
    
    static {
        OCFormatterInfo.EMPTY = new OCFormatterInfo(null, null, null);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    private static class OCLocalFormatterDataImpl implements OCLocalFormatterData
    {
        @NotNull
        private final OCLocalFormatterData myParent;
        @NotNull
        private final IElementType myContainerType;
        @Nullable
        private Map<Object, OCFormatterInfo> myData;
        
        public OCLocalFormatterDataImpl(@Nullable final OCLocalFormatterData ocLocalFormatterData, @NotNull final IElementType myContainerType) {
            if (myContainerType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/formatting/OCFormatterInfo$OCLocalFormatterDataImpl", "<init>"));
            }
            this.myContainerType = myContainerType;
            this.myParent = ((ocLocalFormatterData != null) ? ocLocalFormatterData : OCLocalFormatterNullParentData.INSTANCE);
        }
        
        @Override
        public boolean isNull() {
            return false;
        }
        
        @Nullable
        @Override
        public IElementType getType() {
            return this.myContainerType;
        }
        
        @NotNull
        @Override
        public OCLocalFormatterData getParent() {
            OCLocalFormatterData myParent;
            try {
                myParent = this.myParent;
                if (myParent == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCFormatterInfo$OCLocalFormatterDataImpl", "getParent"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return myParent;
        }
        
        @NotNull
        @Override
        public OCLocalFormatterData getAncestor(@NotNull final IElementType elementType) {
            try {
                if (elementType == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "containerType", "com/jetbrains/cidr/lang/formatting/OCFormatterInfo$OCLocalFormatterDataImpl", "getAncestor"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            OCLocalFormatterData ocLocalFormatterData = null;
            Label_0109: {
                try {
                    if (this.myParent.getType() == elementType) {
                        final OCLocalFormatterData ocLocalFormatterData2;
                        ocLocalFormatterData = (ocLocalFormatterData2 = this.myParent);
                        break Label_0109;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    if (this.myParent.getParent().getType() == elementType) {
                        final OCLocalFormatterData ocLocalFormatterData2;
                        ocLocalFormatterData = (ocLocalFormatterData2 = this.myParent.getParent());
                        break Label_0109;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                OCLocalFormatterData ocLocalFormatterData2;
                ocLocalFormatterData = (ocLocalFormatterData2 = OCLocalFormatterNullParentData.INSTANCE.getAncestor(elementType));
                try {
                    if (ocLocalFormatterData2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCFormatterInfo$OCLocalFormatterDataImpl", "getAncestor"));
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            return ocLocalFormatterData;
        }
        
        @Nullable
        @Override
        public OCFormatterInfo get(@NotNull final Object o) {
            try {
                if (o == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "key", "com/jetbrains/cidr/lang/formatting/OCFormatterInfo$OCLocalFormatterDataImpl", "get"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (this.myData == null) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            return this.myData.get(o);
        }
        
        @NotNull
        @Override
        public OCFormatterInfo get(@NotNull final Object o, @NotNull final Computable<OCFormatterInfo> computable) {
            try {
                if (o == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "key", "com/jetbrains/cidr/lang/formatting/OCFormatterInfo$OCLocalFormatterDataImpl", "get"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (computable == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "factory", "com/jetbrains/cidr/lang/formatting/OCFormatterInfo$OCLocalFormatterDataImpl", "get"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            OCFormatterInfo ocFormatterInfo = null;
            Label_0116: {
                try {
                    if (this.myData == null) {
                        ocFormatterInfo = null;
                        break Label_0116;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                ocFormatterInfo = this.myData.get(o);
            }
            OCFormatterInfo put = ocFormatterInfo;
            if (put == null) {
                put = this.put(o, (OCFormatterInfo)computable.compute());
            }
            OCFormatterInfo ocFormatterInfo2;
            try {
                ocFormatterInfo2 = put;
                if (ocFormatterInfo2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCFormatterInfo$OCLocalFormatterDataImpl", "get"));
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            return ocFormatterInfo2;
        }
        
        @Contract("_, null -> null")
        @Override
        public OCFormatterInfo put(@NotNull final Object o, @Nullable final OCFormatterInfo ocFormatterInfo) {
            try {
                if (o == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "key", "com/jetbrains/cidr/lang/formatting/OCFormatterInfo$OCLocalFormatterDataImpl", "put"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            Label_0079: {
                Label_0062: {
                    try {
                        if (this.myData != null) {
                            break Label_0079;
                        }
                        final OCFormatterInfo ocFormatterInfo2 = ocFormatterInfo;
                        if (ocFormatterInfo2 == null) {
                            break Label_0062;
                        }
                        break Label_0062;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    try {
                        final OCFormatterInfo ocFormatterInfo2 = ocFormatterInfo;
                        if (ocFormatterInfo2 == null) {
                            return null;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                }
                this.myData = (Map<Object, OCFormatterInfo>)new THashMap();
                try {
                    if (ocFormatterInfo == null) {
                        this.myData.remove(o);
                        return ocFormatterInfo;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            this.myData.put(o, ocFormatterInfo);
            return ocFormatterInfo;
        }
        
        @Override
        public void merge(@Nullable final OCLocalFormatterData p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_1        
            //     1: ifnull          35
            //     4: aload_1        
            //     5: instanceof      Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo$OCLocalFormatterDataImpl;
            //     8: ifeq            35
            //    11: goto            18
            //    14: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterInfo$OCLocalFormatterDataImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    17: athrow         
            //    18: aload_1        
            //    19: checkcast       Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo$OCLocalFormatterDataImpl;
            //    22: getfield        com/jetbrains/cidr/lang/formatting/OCFormatterInfo$OCLocalFormatterDataImpl.myData:Ljava/util/Map;
            //    25: ifnonnull       40
            //    28: goto            35
            //    31: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterInfo$OCLocalFormatterDataImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    34: athrow         
            //    35: return         
            //    36: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterInfo$OCLocalFormatterDataImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    39: athrow         
            //    40: aload_0        
            //    41: getfield        com/jetbrains/cidr/lang/formatting/OCFormatterInfo$OCLocalFormatterDataImpl.myData:Ljava/util/Map;
            //    44: ifnonnull       72
            //    47: aload_0        
            //    48: new             Lgnu/trove/THashMap;
            //    51: dup            
            //    52: aload_1        
            //    53: checkcast       Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo$OCLocalFormatterDataImpl;
            //    56: getfield        com/jetbrains/cidr/lang/formatting/OCFormatterInfo$OCLocalFormatterDataImpl.myData:Ljava/util/Map;
            //    59: invokespecial   gnu/trove/THashMap.<init>:(Ljava/util/Map;)V
            //    62: putfield        com/jetbrains/cidr/lang/formatting/OCFormatterInfo$OCLocalFormatterDataImpl.myData:Ljava/util/Map;
            //    65: goto            88
            //    68: invokestatic    com/jetbrains/cidr/lang/formatting/OCFormatterInfo$OCLocalFormatterDataImpl.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    71: athrow         
            //    72: aload_0        
            //    73: getfield        com/jetbrains/cidr/lang/formatting/OCFormatterInfo$OCLocalFormatterDataImpl.myData:Ljava/util/Map;
            //    76: aload_1        
            //    77: checkcast       Lcom/jetbrains/cidr/lang/formatting/OCFormatterInfo$OCLocalFormatterDataImpl;
            //    80: getfield        com/jetbrains/cidr/lang/formatting/OCFormatterInfo$OCLocalFormatterDataImpl.myData:Ljava/util/Map;
            //    83: invokeinterface java/util/Map.putAll:(Ljava/util/Map;)V
            //    88: return         
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  0      11     14     18     Ljava/lang/IllegalArgumentException;
            //  4      28     31     35     Ljava/lang/IllegalArgumentException;
            //  18     36     36     40     Ljava/lang/IllegalArgumentException;
            //  40     68     68     72     Ljava/lang/IllegalArgumentException;
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
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    private static class OCLocalFormatterNullParentData implements OCLocalFormatterData
    {
        public static final OCLocalFormatterNullParentData INSTANCE;
        
        @Override
        public boolean isNull() {
            return true;
        }
        
        @Nullable
        @Override
        public IElementType getType() {
            return null;
        }
        
        @NotNull
        @Override
        public OCLocalFormatterData getParent() {
            try {
                if (this == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCFormatterInfo$OCLocalFormatterNullParentData", "getParent"));
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            return this;
        }
        
        @NotNull
        @Override
        public OCLocalFormatterData getAncestor(@NotNull final IElementType elementType) {
            try {
                if (elementType == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/formatting/OCFormatterInfo$OCLocalFormatterNullParentData", "getAncestor"));
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            OCLocalFormatterNullParentData instance;
            try {
                instance = OCLocalFormatterNullParentData.INSTANCE;
                if (instance == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCFormatterInfo$OCLocalFormatterNullParentData", "getAncestor"));
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            return instance;
        }
        
        @Nullable
        @Override
        public OCFormatterInfo get(@NotNull final Object o) {
            try {
                if (o == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "key", "com/jetbrains/cidr/lang/formatting/OCFormatterInfo$OCLocalFormatterNullParentData", "get"));
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            return OCFormatterInfo.EMPTY;
        }
        
        @NotNull
        @Override
        public OCFormatterInfo get(@NotNull final Object o, @NotNull final Computable<OCFormatterInfo> computable) {
            try {
                if (o == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "key", "com/jetbrains/cidr/lang/formatting/OCFormatterInfo$OCLocalFormatterNullParentData", "get"));
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            try {
                if (computable == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "factory", "com/jetbrains/cidr/lang/formatting/OCFormatterInfo$OCLocalFormatterNullParentData", "get"));
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            OCFormatterInfo ocFormatterInfo;
            try {
                ocFormatterInfo = (OCFormatterInfo)computable.compute();
                if (ocFormatterInfo == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCFormatterInfo$OCLocalFormatterNullParentData", "get"));
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
            return ocFormatterInfo;
        }
        
        @Nullable
        @Override
        public OCFormatterInfo put(@NotNull final Object o, @Nullable final OCFormatterInfo ocFormatterInfo) {
            try {
                if (o == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "key", "com/jetbrains/cidr/lang/formatting/OCFormatterInfo$OCLocalFormatterNullParentData", "put"));
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            return ocFormatterInfo;
        }
        
        @Override
        public void merge(@Nullable final OCLocalFormatterData ocLocalFormatterData) {
        }
        
        static {
            INSTANCE = new OCLocalFormatterNullParentData();
        }
        
        private static IllegalStateException a(final IllegalStateException ex) {
            return ex;
        }
    }
}
