// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap.symbols;

import kotlin._Assertions;
import kotlin.text.StringsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import java.util.Map;
import java.util.List;
import com.jetbrains.cidr.modulemap.resolve.ModuleMapPathResolver;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b\r\bf\u0018\u0000 *2\u00020\u0001:\u0001*J\u0012\u0010'\u001a\u0004\u0018\u00010\u00002\u0006\u0010\u0011\u001a\u00020\u0004H\u0002J\u0012\u0010(\u001a\u0004\u0018\u00010\u00002\u0006\u0010)\u001a\u00020\u0004H\u0016R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0018\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006R\u0018\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u0006R\u0012\u0010\u000b\u001a\u00020\fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\rR\u0012\u0010\u000e\u001a\u00020\fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\rR\u0012\u0010\u000f\u001a\u00020\fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\rR\u0014\u0010\u0010\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\rR\u0012\u0010\u0011\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u0004\u0018\u00010\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0012\u0010\u0017\u001a\u00020\u0018X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0013R\u001e\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00000\u001eX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0014\u0010!\u001a\u00020\u00008VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010\u0016R\u0014\u0010#\u001a\u0004\u0018\u00010\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b$\u0010\u0013R\u0014\u0010%\u001a\u0004\u0018\u00010\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b&\u0010\u0013¨\u0006+" }, d2 = { "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapModuleSymbol;", "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapSymbol;", "excludeHeaders", "", "", "getExcludeHeaders", "()Ljava/util/List;", "exportModules", "getExportModules", "headers", "getHeaders", "isExplicit", "", "()Z", "isFramework", "isInferred", "isWildcardExport", "name", "getName", "()Ljava/lang/String;", "parent", "getParent", "()Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapModuleSymbol;", "pathResolver", "Lcom/jetbrains/cidr/modulemap/resolve/ModuleMapPathResolver;", "getPathResolver", "()Lcom/jetbrains/cidr/modulemap/resolve/ModuleMapPathResolver;", "qualifiedName", "getQualifiedName", "subModules", "", "getSubModules", "()Ljava/util/Map;", "topmostParentOrThis", "getTopmostParentOrThis", "umbrellaDir", "getUmbrellaDir", "umbrellaHeader", "getUmbrellaHeader", "findChildOrThis", "findSubModule", "partlyQualifiedName", "Companion", "cidr-lang" })
public interface ModuleMapModuleSymbol extends ModuleMapSymbol
{
    public static final Companion Companion = new Companion(null);
    
    @NotNull
    String getName();
    
    @Nullable
    ModuleMapModuleSymbol getParent();
    
    @NotNull
    ModuleMapPathResolver getPathResolver();
    
    @Nullable
    String getUmbrellaHeader();
    
    @Nullable
    String getUmbrellaDir();
    
    @NotNull
    List<String> getHeaders();
    
    @NotNull
    List<String> getExcludeHeaders();
    
    @NotNull
    List<String> getExportModules();
    
    @NotNull
    Map<String, ModuleMapModuleSymbol> getSubModules();
    
    boolean isFramework();
    
    boolean isExplicit();
    
    boolean isInferred();
    
    @NotNull
    String getQualifiedName();
    
    @NotNull
    ModuleMapModuleSymbol getTopmostParentOrThis();
    
    boolean isWildcardExport();
    
    @Nullable
    ModuleMapModuleSymbol findSubModule(@NotNull final String p0);
    
    @Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b" }, d2 = { "Lcom/jetbrains/cidr/modulemap/symbols/ModuleMapModuleSymbol$Companion;", "", "()V", "MODULE_EXPORT_WILDCARD", "", "", "getMODULE_EXPORT_WILDCARD", "()Ljava/util/List;", "MODULE_ID_WILDCARD", "getMODULE_ID_WILDCARD", "()Ljava/lang/String;", "cidr-lang" })
    public static final class Companion
    {
        @NotNull
        private static final String MODULE_ID_WILDCARD = "*";
        @NotNull
        private static final List<String> MODULE_EXPORT_WILDCARD;
        
        @NotNull
        public final String getMODULE_ID_WILDCARD() {
            return Companion.MODULE_ID_WILDCARD;
        }
        
        @NotNull
        public final List<String> getMODULE_EXPORT_WILDCARD() {
            return Companion.MODULE_EXPORT_WILDCARD;
        }
        
        private Companion() {
            MODULE_ID_WILDCARD = "*";
            MODULE_EXPORT_WILDCARD = CollectionsKt.listOf((Object)this.getMODULE_ID_WILDCARD());
        }
    }
    
    @Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 3)
    public static final class DefaultImpls
    {
        @NotNull
        public static String getQualifiedName(final ModuleMapModuleSymbol moduleMapModuleSymbol) {
            String s;
            if (moduleMapModuleSymbol.getParent() == null) {
                s = moduleMapModuleSymbol.getName();
            }
            else {
                final StringBuilder append = new StringBuilder().append("");
                final ModuleMapModuleSymbol parent = moduleMapModuleSymbol.getParent();
                if (parent == null) {
                    Intrinsics.throwNpe();
                }
                s = append.append(parent.getQualifiedName()).append('.').append(moduleMapModuleSymbol.getName()).toString();
            }
            return s;
        }
        
        @NotNull
        public static ModuleMapModuleSymbol getTopmostParentOrThis(final ModuleMapModuleSymbol moduleMapModuleSymbol) {
            ModuleMapModuleSymbol topmostParentOrThis;
            if (moduleMapModuleSymbol.getParent() == null) {
                topmostParentOrThis = moduleMapModuleSymbol;
            }
            else {
                final ModuleMapModuleSymbol parent = moduleMapModuleSymbol.getParent();
                if (parent == null) {
                    Intrinsics.throwNpe();
                }
                topmostParentOrThis = parent.getTopmostParentOrThis();
            }
            return topmostParentOrThis;
        }
        
        public static boolean isWildcardExport(final ModuleMapModuleSymbol moduleMapModuleSymbol) {
            return Intrinsics.areEqual((Object)moduleMapModuleSymbol.getExportModules(), (Object)ModuleMapModuleSymbol.Companion.getMODULE_EXPORT_WILDCARD());
        }
        
        @Nullable
        public static ModuleMapModuleSymbol findSubModule(@NotNull final ModuleMapModuleSymbol moduleMapModuleSymbol, final String s) {
            Intrinsics.checkParameterIsNotNull((Object)s, "partlyQualifiedName");
            if (!StringsKt.contains$default((CharSequence)s, '.', false, 2, (Object)null)) {
                return a(moduleMapModuleSymbol, s);
            }
            final List split$default = StringsKt.split$default((CharSequence)s, new char[] { '.' }, false, 2, 2, (Object)null);
            final boolean b = split$default.size() == 2;
            if (_Assertions.ENABLED && !b) {
                throw new AssertionError((Object)"Assertion failed");
            }
            final ModuleMapModuleSymbol a = a(moduleMapModuleSymbol, split$default.get(0));
            return (a != null) ? a.findSubModule(split$default.get(1)) : null;
        }
        
        private static ModuleMapModuleSymbol a(final ModuleMapModuleSymbol moduleMapModuleSymbol, final String s) {
            return Intrinsics.areEqual((Object)moduleMapModuleSymbol.getName(), (Object)s) ? moduleMapModuleSymbol : moduleMapModuleSymbol.getSubModules().get(s);
        }
    }
}
