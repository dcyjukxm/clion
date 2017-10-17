// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.cpp;

import com.jetbrains.cidr.lang.types.OCVariadicType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.types.visitors.OCTypeSubstitution;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.types.OCTypeArgument;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.types.OCType;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.expression.OCExpressionSymbol;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.types.OCExpressionTypeArgument;
import com.jetbrains.cidr.lang.symbols.OCTypeParameterSymbol;

public class OCTypeParameterValueSymbol extends OCDeclaratorSymbol implements OCTypeParameterSymbol<OCExpressionTypeArgument>
{
    public OCTypeParameterValueSymbol() {
    }
    
    public OCTypeParameterValueSymbol(final Project project, final VirtualFile virtualFile, final long n, final OCSymbolWithQualifiedName ocSymbolWithQualifiedName, final OCQualifiedName ocQualifiedName, @Nullable final OCExpressionSymbol ocExpressionSymbol, @NotNull final List<String> list, @NotNull final OCType ocType, final OCSymbolKind ocSymbolKind, final int[] array, @NotNull final List<OCTypeParameterSymbol> list2, @Nullable final List<OCTypeArgument> list3, final int n2, final int n3, @Nullable final TextRange textRange, @Nullable final OCVisibility ocVisibility) {
        if (list == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "attributes", "com/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterValueSymbol", "<init>"));
        }
        if (ocType == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterValueSymbol", "<init>"));
        }
        if (list2 == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "templateParameters", "com/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterValueSymbol", "<init>"));
        }
        super(project, virtualFile, n, ocSymbolWithQualifiedName, ocQualifiedName, list, ocType, ocSymbolKind, array, ocExpressionSymbol, list2, list3, n2, n3, textRange, ocVisibility);
    }
    
    public OCTypeParameterValueSymbol(final OCTypeParameterValueSymbol ocTypeParameterValueSymbol, final OCTypeSubstitution ocTypeSubstitution, @NotNull final OCResolveContext ocResolveContext) {
        if (ocResolveContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/cpp/OCTypeParameterValueSymbol", "<init>"));
        }
        super(ocTypeParameterValueSymbol, ocTypeSubstitution, ocTypeParameterValueSymbol.getQualifiedName(), ocTypeParameterValueSymbol.getParent(), ocResolveContext);
    }
    
    @Nullable
    @Override
    public OCExpressionTypeArgument getDefaultValue() {
        try {
            if (this.myInitializer != null) {
                return new OCExpressionTypeArgument(this.myInitializer);
            }
        }
        catch (IllegalArgumentException ex) {
            throw e(ex);
        }
        return null;
    }
    
    @Override
    public boolean isVariadic() {
        return this.getType() instanceof OCVariadicType;
    }
    
    private static IllegalArgumentException e(final IllegalArgumentException ex) {
        return ex;
    }
}
