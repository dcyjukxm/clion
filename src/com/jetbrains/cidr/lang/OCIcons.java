// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang;

import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.PlatformIcons;
import com.intellij.icons.AllIcons;
import com.intellij.ui.RowIcon;
import com.intellij.ui.LayeredIcon;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.util.Pair;
import java.util.Map;
import javax.swing.Icon;
import icons.CidrLangIcons;

public class OCIcons extends CidrLangIcons
{
    private static final Icon STATIC_MARK_ICON;
    private static final Icon CONST_MARK_ICON;
    private static final Icon NON_VIRTUAL_MARK_ICON;
    private static final Icon TEST_MARK_ICON;
    private static final Icon PUBLIC_MARK_ICON;
    private static final Icon PACKAGE_MARK_ICON;
    private static final Icon PROTECTED_MARK_ICON;
    private static final Icon PRIVATE_MARK_ICON;
    private static final Map<Pair<Icon, Icon>, Icon> ourPatchedIcons;
    
    private static Icon b(final String s) {
        return IconLoader.getIcon(s, (Class)OCIcons.class);
    }
    
    public static Icon getMethodIcon(final boolean b, final boolean b2, final boolean b3) {
        Icon icon = null;
        Label_0031: {
            try {
                if (b2) {
                    icon = OCIcons.CodeAssistantMemberOptional;
                    break Label_0031;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (b3) {
                    icon = OCIcons.CodeAssistantMemberAbstract;
                    break Label_0031;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            icon = OCIcons.CodeAssistantMember;
        }
        final Icon icon2 = icon;
        try {
            if (b) {
                return getStaticIcon(icon2);
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return icon2;
    }
    
    public static Icon getPropertyIcon(final boolean b, final boolean b2) {
        Icon icon = null;
        Label_0017: {
            try {
                if (b2) {
                    icon = OCIcons.CodeAssistantPropertyOptional;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            icon = OCIcons.CodeAssistantProperty;
        }
        final Icon icon2 = icon;
        try {
            if (b) {
                return getStaticIcon(icon2);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return icon2;
    }
    
    public static Icon getFunctionIcon(final boolean b, final boolean b2, final boolean b3, final OCVisibility ocVisibility) {
        Icon icon = null;
        Label_0017: {
            try {
                if (b2) {
                    icon = OCIcons.CodeAssistantFunctionAbstract;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            icon = OCIcons.CodeAssistantFunction;
        }
        final Icon icon2 = icon;
        Icon staticIcon = null;
        Label_0037: {
            try {
                if (b) {
                    staticIcon = getStaticIcon(icon2);
                    break Label_0037;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            staticIcon = icon2;
        }
        final Icon icon3 = staticIcon;
        try {
            if (b3) {
                final Icon a = a(icon3, OCIcons.NON_VIRTUAL_MARK_ICON, false);
                return getVisibilityIcon(ocVisibility, a);
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final Icon a = icon3;
        return getVisibilityIcon(ocVisibility, a);
    }
    
    @Nullable
    private static Icon a(@Nullable final Icon icon, @NotNull final Icon icon2, final boolean b) {
        try {
            if (icon2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "newLayer", "com/jetbrains/cidr/lang/OCIcons", "addLayer"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (icon == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final Pair create = Pair.create((Object)icon, (Object)icon2);
        Object o = OCIcons.ourPatchedIcons.get(create);
        Label_0232: {
            Label_0176: {
                Label_0093: {
                    try {
                        if (o != null) {
                            return (Icon)o;
                        }
                        final Icon icon3 = icon;
                        final boolean b2 = icon3 instanceof LayeredIcon;
                        if (b2) {
                            break Label_0093;
                        }
                        break Label_0176;
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                    try {
                        final Icon icon3 = icon;
                        final boolean b2 = icon3 instanceof LayeredIcon;
                        if (!b2) {
                            break Label_0176;
                        }
                        if (b) {
                            break Label_0176;
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                }
                final Icon[] allLayers = ((LayeredIcon)icon).getAllLayers();
                final LayeredIcon layeredIcon = new LayeredIcon(allLayers.length + 1);
                int i = 0;
                try {
                    while (i < allLayers.length) {
                        layeredIcon.setIcon(allLayers[i], i);
                        ++i;
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
                layeredIcon.setIcon(icon2, allLayers.length);
                o = layeredIcon;
                break Label_0232;
            }
            if (b) {
                final RowIcon rowIcon = new RowIcon(2);
                rowIcon.setIcon(icon, 0);
                rowIcon.setIcon(icon2, 1);
                o = rowIcon;
            }
            else {
                o = new LayeredIcon(new Icon[] { icon, icon2 });
            }
        }
        OCIcons.ourPatchedIcons.put((Pair<Icon, Icon>)create, (Icon)o);
        return (Icon)o;
    }
    
    public static Icon getStaticIcon(@Nullable final Icon icon) {
        return a(icon, OCIcons.STATIC_MARK_ICON, false);
    }
    
    public static Icon getConstIcon(@Nullable final Icon icon) {
        return a(icon, OCIcons.CONST_MARK_ICON, false);
    }
    
    public static Icon getVisibilityIcon(@Nullable final OCVisibility ocVisibility, final Icon icon) {
        try {
            if (ocVisibility == null) {
                return icon;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            switch (ocVisibility) {
                case PUBLIC: {
                    return a(icon, OCIcons.PUBLIC_MARK_ICON, true);
                }
                case PACKAGE: {
                    break;
                }
                case PROTECTED: {
                    return a(icon, OCIcons.PROTECTED_MARK_ICON, true);
                }
                case PRIVATE: {
                    return a(icon, OCIcons.PRIVATE_MARK_ICON, true);
                }
                default: {
                    return icon;
                }
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return a(icon, OCIcons.PACKAGE_MARK_ICON, true);
    }
    
    @Nullable
    public static Icon getTestIcon(@Nullable final Icon icon) {
        return a(icon, OCIcons.TEST_MARK_ICON, false);
    }
    
    static {
        STATIC_MARK_ICON = AllIcons.Nodes.StaticMark;
        CONST_MARK_ICON = AllIcons.Nodes.FinalMark;
        NON_VIRTUAL_MARK_ICON = AllIcons.Nodes.FinalMark;
        TEST_MARK_ICON = AllIcons.RunConfigurations.TestMark;
        PUBLIC_MARK_ICON = PlatformIcons.PUBLIC_ICON;
        PACKAGE_MARK_ICON = PlatformIcons.PACKAGE_LOCAL_ICON;
        PROTECTED_MARK_ICON = PlatformIcons.PROTECTED_ICON;
        PRIVATE_MARK_ICON = PlatformIcons.PRIVATE_ICON;
        ourPatchedIcons = ContainerUtil.newConcurrentMap();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
