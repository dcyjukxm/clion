// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.modulemap;

import org.jetbrains.annotations.Nullable;
import kotlin.jvm.internal.Reflection;
import com.jetbrains.cidr.modulemap.symbols.ModuleMapModuleSymbol;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.KProperty1;
import kotlin.Metadata;
import kotlin.jvm.internal.PropertyReference1;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 3)
final class ModuleMapModulesImpl$add$1 extends PropertyReference1
{
    public static final KProperty1 INSTANCE;
    
    static {
        INSTANCE = (KProperty1)new ModuleMapModulesImpl$add$1();
    }
    
    public String getName() {
        return "qualifiedName";
    }
    
    public String getSignature() {
        return "getQualifiedName()Ljava/lang/String;";
    }
    
    public KDeclarationContainer getOwner() {
        return (KDeclarationContainer)Reflection.getOrCreateKotlinClass((Class)ModuleMapModuleSymbol.class);
    }
    
    @Nullable
    public Object get(@Nullable final Object o) {
        return ((ModuleMapModuleSymbol)o).getQualifiedName();
    }
}
