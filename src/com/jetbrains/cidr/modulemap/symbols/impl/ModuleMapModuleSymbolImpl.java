// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.symbols.impl;

import kotlin.collections.MapsKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import java.util.Map;
import java.util.List;
import com.jetbrains.cidr.modulemap.resolve.ModuleMapPathResolver;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import com.jetbrains.cidr.modulemap.symbols.ModuleMapModuleSymbol;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010$\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 *2\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002:\u0001*B\u0089\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0001\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0004\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u000b\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u000b\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u000b\u0012\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0011\u0012\u0006\u0010\u0013\u001a\u00020\u0011¢\u0006\u0002\u0010\u0014J \u0010$\u001a\u00020\u00112\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u00002\u0006\u0010(\u001a\u00020\u0000H\u0016J\b\u0010)\u001a\u00020\u0004H\u0016R\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u0014\u0010\u0012\u001a\u00020\u0011X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0019R\u0014\u0010\u0010\u001a\u00020\u0011X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0019R\u0014\u0010\u0013\u001a\u00020\u0011X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0019R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u0001X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR \u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0016\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001bR\u0016\u0010\b\u001a\u0004\u0018\u00010\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001b¨\u0006+" }, d2 = { "Lcom/jetbrains/cidr/modulemap/symbols/impl/ModuleMapModuleSymbolImpl;", "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapModuleSymbol;", "Lcom/jetbrains/cidr/lang/symbols/DeepEqual$Equality;", "name", "", "parent", "pathResolver", "Lcom/jetbrains/cidr/modulemap/resolve/ModuleMapPathResolver;", "umbrellaHeader", "umbrellaDir", "headers", "", "excludeHeaders", "exportModules", "subModules", "", "isFramework", "", "isExplicit", "isInferred", "(Ljava/lang/String;Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapModuleSymbol;Lcom/jetbrains/cidr/modulemap/resolve/ModuleMapPathResolver;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/Map;ZZZ)V", "getExcludeHeaders", "()Ljava/util/List;", "getExportModules", "getHeaders", "()Z", "getName", "()Ljava/lang/String;", "getParent", "()Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapModuleSymbol;", "getPathResolver", "()Lcom/jetbrains/cidr/modulemap/resolve/ModuleMapPathResolver;", "getSubModules", "()Ljava/util/Map;", "getUmbrellaDir", "getUmbrellaHeader", "deepEqualStep", "c", "Lcom/jetbrains/cidr/lang/symbols/DeepEqual$Comparator;", "first", "second", "toString", "Companion", "cidr-lang" })
public final class ModuleMapModuleSymbolImpl implements ModuleMapModuleSymbol, Equality<ModuleMapModuleSymbolImpl>
{
    @NotNull
    private final String name;
    @Nullable
    private final ModuleMapModuleSymbol parent;
    @NotNull
    private final ModuleMapPathResolver pathResolver;
    @Nullable
    private final String umbrellaHeader;
    @Nullable
    private final String umbrellaDir;
    @NotNull
    private final List<String> headers;
    @NotNull
    private final List<String> excludeHeaders;
    @NotNull
    private final List<String> exportModules;
    @NotNull
    private final Map<String, ModuleMapModuleSymbol> subModules;
    private final boolean isFramework;
    private final boolean isExplicit;
    private final boolean isInferred;
    public static final Companion Companion;
    
    @NotNull
    @Override
    public String toString() {
        return this.getQualifiedName();
    }
    
    public boolean deepEqualStep(@NotNull final Comparator comparator, @NotNull final ModuleMapModuleSymbolImpl moduleMapModuleSymbolImpl, @NotNull final ModuleMapModuleSymbolImpl moduleMapModuleSymbolImpl2) {
        Intrinsics.checkParameterIsNotNull((Object)comparator, "c");
        Intrinsics.checkParameterIsNotNull((Object)moduleMapModuleSymbolImpl, "first");
        Intrinsics.checkParameterIsNotNull((Object)moduleMapModuleSymbolImpl2, "second");
        return moduleMapModuleSymbolImpl.isFramework() == moduleMapModuleSymbolImpl2.isFramework() && moduleMapModuleSymbolImpl.isExplicit() == moduleMapModuleSymbolImpl2.isExplicit() && moduleMapModuleSymbolImpl.isInferred() == moduleMapModuleSymbolImpl2.isInferred() && comparator.equalObjects(moduleMapModuleSymbolImpl.getName(), moduleMapModuleSymbolImpl2.getName()) && comparator.equalObjects(moduleMapModuleSymbolImpl.getUmbrellaHeader(), moduleMapModuleSymbolImpl2.getUmbrellaHeader()) && comparator.equalObjects(moduleMapModuleSymbolImpl.getUmbrellaDir(), moduleMapModuleSymbolImpl2.getUmbrellaDir()) && comparator.equalObjects(moduleMapModuleSymbolImpl.getParent(), moduleMapModuleSymbolImpl2.getParent()) && comparator.equalIterable(moduleMapModuleSymbolImpl.getHeaders(), moduleMapModuleSymbolImpl2.getHeaders()) && comparator.equalIterable(moduleMapModuleSymbolImpl.getExcludeHeaders(), moduleMapModuleSymbolImpl2.getExcludeHeaders()) && comparator.equalIterable(moduleMapModuleSymbolImpl.getExportModules(), moduleMapModuleSymbolImpl2.getExportModules()) && comparator.equalMaps(moduleMapModuleSymbolImpl.getSubModules(), moduleMapModuleSymbolImpl2.getSubModules());
    }
    
    @NotNull
    @Override
    public String getName() {
        return this.name;
    }
    
    @Nullable
    @Override
    public ModuleMapModuleSymbol getParent() {
        return this.parent;
    }
    
    @NotNull
    @Override
    public ModuleMapPathResolver getPathResolver() {
        return this.pathResolver;
    }
    
    @Nullable
    @Override
    public String getUmbrellaHeader() {
        return this.umbrellaHeader;
    }
    
    @Nullable
    @Override
    public String getUmbrellaDir() {
        return this.umbrellaDir;
    }
    
    @NotNull
    @Override
    public List<String> getHeaders() {
        return this.headers;
    }
    
    @NotNull
    @Override
    public List<String> getExcludeHeaders() {
        return this.excludeHeaders;
    }
    
    @NotNull
    @Override
    public List<String> getExportModules() {
        return this.exportModules;
    }
    
    @NotNull
    @Override
    public Map<String, ModuleMapModuleSymbol> getSubModules() {
        return this.subModules;
    }
    
    @Override
    public boolean isFramework() {
        return this.isFramework;
    }
    
    @Override
    public boolean isExplicit() {
        return this.isExplicit;
    }
    
    @Override
    public boolean isInferred() {
        return this.isInferred;
    }
    
    public ModuleMapModuleSymbolImpl(@NotNull final String name, @Nullable final ModuleMapModuleSymbol parent, @NotNull final ModuleMapPathResolver pathResolver, @Nullable final String umbrellaHeader, @Nullable final String umbrellaDir, @NotNull final List<String> headers, @NotNull final List<String> excludeHeaders, @NotNull final List<String> exportModules, @NotNull final Map<String, ? extends ModuleMapModuleSymbol> subModules, final boolean isFramework, final boolean isExplicit, final boolean isInferred) {
        Intrinsics.checkParameterIsNotNull((Object)name, "name");
        Intrinsics.checkParameterIsNotNull((Object)pathResolver, "pathResolver");
        Intrinsics.checkParameterIsNotNull((Object)headers, "headers");
        Intrinsics.checkParameterIsNotNull((Object)excludeHeaders, "excludeHeaders");
        Intrinsics.checkParameterIsNotNull((Object)exportModules, "exportModules");
        Intrinsics.checkParameterIsNotNull((Object)subModules, "subModules");
        this.name = name;
        this.parent = parent;
        this.pathResolver = pathResolver;
        this.umbrellaHeader = umbrellaHeader;
        this.umbrellaDir = umbrellaDir;
        this.headers = headers;
        this.excludeHeaders = excludeHeaders;
        this.exportModules = exportModules;
        this.subModules = (Map<String, ModuleMapModuleSymbol>)subModules;
        this.isFramework = isFramework;
        this.isExplicit = isExplicit;
        this.isInferred = isInferred;
    }
    
    static {
        Companion = new Companion(null);
    }
    
    @NotNull
    @Override
    public String getQualifiedName() {
        return DefaultImpls.getQualifiedName(this);
    }
    
    @NotNull
    @Override
    public ModuleMapModuleSymbol getTopmostParentOrThis() {
        return DefaultImpls.getTopmostParentOrThis(this);
    }
    
    @Override
    public boolean isWildcardExport() {
        return DefaultImpls.isWildcardExport(this);
    }
    
    @Nullable
    @Override
    public ModuleMapModuleSymbol findSubModule(@NotNull final String s) {
        Intrinsics.checkParameterIsNotNull((Object)s, "partlyQualifiedName");
        return DefaultImpls.findSubModule(this, s);
    }
    
    @Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005" }, d2 = { "Lcom/jetbrains/cidr/modulemap/symbols/impl/ModuleMapModuleSymbolImpl$Companion;", "", "()V", "createUninitialized", "Lcom/jetbrains/cidr/modulemap/symbols/impl/ModuleMapModuleSymbolImpl;", "cidr-lang" })
    public static final class Companion
    {
        @NotNull
        public final ModuleMapModuleSymbolImpl createUninitialized() {
            return new ModuleMapModuleSymbolImpl("", null, ModuleMapPathResolver.Companion.getNULL(), null, null, CollectionsKt.emptyList(), CollectionsKt.emptyList(), CollectionsKt.emptyList(), MapsKt.emptyMap(), false, false, false);
        }
    }
}
