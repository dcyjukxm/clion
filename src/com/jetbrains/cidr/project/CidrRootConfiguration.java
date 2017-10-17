// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.project;

import com.intellij.openapi.module.Module;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.vfs.pointers.VirtualFilePointerManager;
import gnu.trove.THashSet;
import kotlin.Unit;
import com.intellij.openapi.util.Computable;
import com.intellij.util.messages.Topic;
import gnu.trove.THashMap;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VfsUtilCore;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.Iterator;
import java.util.List;
import com.intellij.openapi.vfs.VfsUtil;
import kotlin.collections.CollectionsKt;
import java.util.Set;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import com.intellij.openapi.vfs.pointers.VirtualFilePointer;
import java.util.LinkedHashMap;
import java.util.Map;
import com.intellij.openapi.application.ApplicationManager;
import java.io.File;
import java.util.Collection;
import org.jetbrains.annotations.NotNull;
import kotlin.jvm.JvmField;
import com.intellij.openapi.util.Key;
import kotlin.Metadata;
import com.intellij.openapi.components.State;
import com.intellij.openapi.vfs.pointers.VirtualFilePointerListener;
import org.jdom.Element;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.AbstractProjectComponent;

@com.intellij.openapi.components.State(name = "CidrRootsConfiguration")
@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\b\n\u0002\u0010%\n\u0000\n\u0002\u0010#\n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\b\u0005\b\u0007\u0018\u0000 02\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u00022\u00020\u0004:\u0003012B\r\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rJ\u0010\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000eJ\u0010\u0010\u000f\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rJ\u0010\u0010\u000f\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000eJ\u001a\u0010\u0010\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0010\u0010\u0013\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rJ\u0010\u0010\u0013\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000eJ\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\b\u0010\u0018\u001a\u00020\u000bH\u0002J\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001aJ\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001aJ\u001e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001a2\u0006\u0010\u001d\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001aJ\n\u0010\u001f\u001a\u0004\u0018\u00010\u0003H\u0016J:\u0010 \u001a\u00020\u000b2\u0006\u0010!\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00122\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00120#2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00170%H\u0002J\u0010\u0010&\u001a\u00020\u000b2\u0006\u0010'\u001a\u00020\u0003H\u0016J\u0010\u0010(\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rJ\u0010\u0010(\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000eJ\u001c\u0010)\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000e2\b\u0010*\u001a\u0004\u0018\u00010\u0012H\u0002J&\u0010+\u001a\u00020\u000b2\u0006\u0010!\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001aH\u0002J\u001d\u0010,\u001a\u00020\u000b2\u000e\u0010-\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00150.H\u0016¢\u0006\u0002\u0010/R\u0012\u0010\b\u001a\u00020\t8\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000¨\u00063" }, d2 = { "Lcom/jetbrains/cidr/project/CidrRootConfiguration;", "Lcom/intellij/openapi/components/AbstractProjectComponent;", "Lcom/intellij/openapi/components/PersistentStateComponent;", "Lorg/jdom/Element;", "Lcom/intellij/openapi/vfs/pointers/VirtualFilePointerListener;", "project", "Lcom/intellij/openapi/project/Project;", "(Lcom/intellij/openapi/project/Project;)V", "myState", "Lcom/jetbrains/cidr/project/CidrRootConfiguration$State;", "addExcludeRoot", "", "file", "Lcom/intellij/openapi/vfs/VirtualFile;", "Ljava/io/File;", "addLibraryRoot", "addRoot", "rootType", "Lcom/jetbrains/cidr/project/CidrRootConfiguration$RootType;", "addSourceRoot", "createPointer", "Lcom/intellij/openapi/vfs/pointers/VirtualFilePointer;", "path", "", "fireEvents", "getExcludeRoots", "", "getLibraryRoots", "getRoots", "state", "getSourceRoots", "getState", "loadRoots", "parent", "roots", "", "added", "", "loadState", "element", "removeRoot", "replaceRoot", "replaceWith", "saveRoots", "validityChanged", "pointers", "", "([Lcom/intellij/openapi/vfs/pointers/VirtualFilePointer;)V", "Companion", "RootType", "State", "cidr-common" })
public final class CidrRootConfiguration extends AbstractProjectComponent implements PersistentStateComponent<Element>, VirtualFilePointerListener
{
    private volatile State myState;
    @JvmField
    @NotNull
    public static final Key<Boolean> MODULE_SUPPORTS_ROOTS_CONFIGURATION;
    public static final Companion Companion;
    
    @NotNull
    public final Collection<File> getSourceRoots() {
        return this.a(this.myState, RootType.SOURCE);
    }
    
    @NotNull
    public final Collection<File> getLibraryRoots() {
        return this.a(this.myState, RootType.LIBRARY);
    }
    
    @NotNull
    public final Collection<File> getExcludeRoots() {
        return this.a(this.myState, RootType.EXCLUDED);
    }
    
    private final Collection<File> a(final State state, final RootType rootType) {
        ApplicationManager.getApplication().assertReadAccessAllowed();
        final Map map = (Map)state.getRoots();
        final LinkedHashMap<VirtualFilePointer, RootType> linkedHashMap = new LinkedHashMap<VirtualFilePointer, RootType>();
        for (final Map.Entry<K, RootType> entry : map.entrySet()) {
            if (Intrinsics.areEqual((Object)entry.getValue(), (Object)rootType)) {
                linkedHashMap.put((VirtualFilePointer)entry.getKey(), entry.getValue());
            }
        }
        final Set<VirtualFilePointer> set;
        final ArrayList<Object> list = new ArrayList<Object>(CollectionsKt.collectionSizeOrDefault((Iterable)(set = linkedHashMap.keySet()), 10));
        final Iterator<Object> iterator2 = set.iterator();
        while (iterator2.hasNext()) {
            list.add(new File(VfsUtil.urlToPath(iterator2.next().getUrl())));
        }
        return (List<File>)list;
    }
    
    public final void addSourceRoot(@Nullable final VirtualFile virtualFile) {
        if (virtualFile != null) {
            this.addSourceRoot(VfsUtilCore.virtualToIoFile(virtualFile));
        }
    }
    
    public final void addSourceRoot(@Nullable final File file) {
        this.a(file, RootType.SOURCE);
    }
    
    public final void addLibraryRoot(@Nullable final VirtualFile virtualFile) {
        if (virtualFile != null) {
            this.addLibraryRoot(VfsUtilCore.virtualToIoFile(virtualFile));
        }
    }
    
    public final void addLibraryRoot(@Nullable final File file) {
        this.a(file, RootType.LIBRARY);
    }
    
    public final void addExcludeRoot(@Nullable final VirtualFile virtualFile) {
        if (virtualFile != null) {
            this.addExcludeRoot(VfsUtilCore.virtualToIoFile(virtualFile));
        }
    }
    
    public final void addExcludeRoot(@Nullable final File file) {
        this.a(file, RootType.EXCLUDED);
    }
    
    private final void a(final File file, final RootType rootType) {
        this.b(file, rootType);
    }
    
    public final void removeRoot(@Nullable final VirtualFile virtualFile) {
        if (virtualFile != null) {
            this.removeRoot(VfsUtilCore.virtualToIoFile(virtualFile));
        }
    }
    
    public final void removeRoot(@Nullable final File file) {
        this.b(file, null);
    }
    
    private final void b(final File file, final RootType rootType) {
        ApplicationManager.getApplication().assertWriteAccessAllowed();
        if (file == null) {
            return;
        }
        final THashMap<VirtualFilePointer, RootType> roots = this.myState.getRoots();
        boolean b = false;
        boolean b2 = true;
        final Iterator iterator = roots.keySet().iterator();
        while (iterator.hasNext()) {
            final VirtualFilePointer virtualFilePointer = iterator.next();
            if (FileUtil.pathsEqual(file.getPath(), VfsUtil.urlToPath(virtualFilePointer.getUrl()))) {
                if (Intrinsics.areEqual((Object)roots.get((Object)virtualFilePointer), (Object)rootType) ^ true) {
                    iterator.remove();
                    b = true;
                }
                else {
                    b2 = false;
                }
            }
        }
        if (b2 && rootType != null) {
            final String path = file.getPath();
            Intrinsics.checkExpressionValueIsNotNull((Object)path, "file.path");
            roots.put((Object)this.a(path), (Object)rootType);
            b = true;
        }
        if (b) {
            this.a();
        }
    }
    
    private final void a() {
        ((CidrRootConfigurationListener)this.myProject.getMessageBus().syncPublisher((Topic)CidrRootConfigurationListener.TOPIC)).configurationChanged();
    }
    
    @Nullable
    public Element getState() {
        final Element element = new Element("roots");
        ApplicationManager.getApplication().runReadAction((Computable)new Computable<T>(this, element) {
            public final T compute() {
                final State access$getMyState$p = CidrRootConfiguration.access$getMyState$p(this.this$0);
                this.this$0.a(this.$roots$inlined, RootType.SOURCE, this.this$0.a(access$getMyState$p, RootType.SOURCE));
                this.this$0.a(this.$roots$inlined, RootType.LIBRARY, this.this$0.a(access$getMyState$p, RootType.LIBRARY));
                this.this$0.a(this.$roots$inlined, RootType.EXCLUDED, this.this$0.a(access$getMyState$p, RootType.EXCLUDED));
                return (T)Unit.INSTANCE;
            }
        });
        return element;
    }
    
    public void loadState(@NotNull final Element element) {
        Intrinsics.checkParameterIsNotNull((Object)element, "element");
        final State myState = new State();
        final THashSet set = new THashSet(FileUtil.PATH_HASHING_STRATEGY);
        this.a(element, RootType.SOURCE, (Map)myState.getRoots(), (Set<String>)set);
        this.a(element, RootType.LIBRARY, (Map)myState.getRoots(), (Set<String>)set);
        this.a(element, RootType.EXCLUDED, (Map)myState.getRoots(), (Set<String>)set);
        final boolean b = Intrinsics.areEqual((Object)this.myState.getRoots(), (Object)myState.getRoots()) ^ true;
        this.myState = myState;
        if (b && ApplicationManager.getApplication().isDispatchThread()) {
            ApplicationManager.getApplication().runWriteAction((Runnable)new CidrRootConfiguration$loadState.CidrRootConfiguration$loadState$1(this));
        }
    }
    
    private final void a(final Element element, final RootType rootType, final Collection<? extends File> collection) {
        Element element2 = null;
        for (final File file : CollectionsKt.sorted((Iterable)collection)) {
            final Element element3 = new Element("file");
            element3.setAttribute("path", FileUtil.toSystemIndependentName(file.getPath()));
            if (element2 == null) {
                element2 = new Element(rootType.getElementName());
            }
            element2.addContent(element3);
        }
        if (element2 != null) {
            element.addContent(element2);
        }
    }
    
    private final void a(final Element element, final RootType rootType, final Map<VirtualFilePointer, RootType> map, final Set<String> set) {
        final Element child = element.getChild(rootType.getElementName());
        if (child != null) {
            final Iterator iterator = child.getChildren("file").iterator();
            while (iterator.hasNext()) {
                final String attributeValue = iterator.next().getAttributeValue("path");
                if (attributeValue != null) {
                    final String s = attributeValue;
                    if (!set.add(s)) {
                        continue;
                    }
                    final VirtualFilePointer a = this.a(s);
                    Intrinsics.checkExpressionValueIsNotNull((Object)a, "createPointer(path)");
                    map.put(a, rootType);
                }
            }
        }
    }
    
    private final VirtualFilePointer a(final String s) {
        return VirtualFilePointerManager.getInstance().create(VfsUtil.pathToUrl(s), (Disposable)this.myProject, (VirtualFilePointerListener)this);
    }
    
    public void validityChanged(@NotNull final VirtualFilePointer[] array) {
        Intrinsics.checkParameterIsNotNull((Object)array, "pointers");
        boolean b = false;
        final THashMap<VirtualFilePointer, RootType> roots = this.myState.getRoots();
    Label_0155:
        for (int i = 0; i < array.length; ++i) {
            final VirtualFilePointer virtualFilePointer = array[i];
            if (virtualFilePointer.isValid() && roots.contains((Object)virtualFilePointer)) {
                while (true) {
                    for (final VirtualFilePointer virtualFilePointer2 : roots.keySet()) {
                        if ((Intrinsics.areEqual((Object)virtualFilePointer2, (Object)virtualFilePointer) ^ true) && Intrinsics.areEqual((Object)virtualFilePointer2.getUrl(), (Object)virtualFilePointer.getUrl())) {
                            final boolean b2 = true;
                            if (b2) {
                                roots.remove((Object)virtualFilePointer);
                                b = true;
                            }
                            continue Label_0155;
                        }
                    }
                    final boolean b2 = false;
                    continue;
                }
            }
        }
        if (b) {
            this.a();
        }
    }
    
    public CidrRootConfiguration(@NotNull final Project project) {
        Intrinsics.checkParameterIsNotNull((Object)project, "project");
        super(project);
        this.myState = new State();
    }
    
    static {
        Companion = new Companion(null);
        final Key create = Key.create("MODULE_SUPPORTS_ROOTS_CONFIGURATION");
        Intrinsics.checkExpressionValueIsNotNull((Object)create, "Key.create(\"MODULE_SUPPORTS_ROOTS_CONFIGURATION\")");
        MODULE_SUPPORTS_ROOTS_CONFIGURATION = create;
    }
    
    @NotNull
    public static final /* synthetic */ State access$getMyState$p(final CidrRootConfiguration cidrRootConfiguration) {
        return cidrRootConfiguration.myState;
    }
    
    @JvmStatic
    @NotNull
    public static final CidrRootConfiguration getInstance(@NotNull final Project project) {
        Intrinsics.checkParameterIsNotNull((Object)project, "project");
        return CidrRootConfiguration.Companion.getInstance(project);
    }
    
    @JvmStatic
    public static final void setEnabledFor(@NotNull final Module enabledFor) {
        Intrinsics.checkParameterIsNotNull((Object)enabledFor, "module");
        CidrRootConfiguration.Companion.setEnabledFor(enabledFor);
    }
    
    @Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n" }, d2 = { "Lcom/jetbrains/cidr/project/CidrRootConfiguration$RootType;", "", "elementName", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getElementName", "()Ljava/lang/String;", "SOURCE", "LIBRARY", "EXCLUDED", "cidr-common" })
    public enum RootType
    {
        SOURCE("sourceRoots"), 
        LIBRARY("libraryRoots"), 
        EXCLUDED("excludeRoots");
        
        @NotNull
        private final String elementName;
        
        @NotNull
        public final String getElementName() {
            return this.elementName;
        }
        
        protected RootType(final String elementName) {
            Intrinsics.checkParameterIsNotNull((Object)elementName, "elementName");
            super(s, n);
            this.elementName = elementName;
        }
    }
    
    @Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001d\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t" }, d2 = { "Lcom/jetbrains/cidr/project/CidrRootConfiguration$State;", "", "()V", "roots", "Lgnu/trove/THashMap;", "Lcom/intellij/openapi/vfs/pointers/VirtualFilePointer;", "Lcom/jetbrains/cidr/project/CidrRootConfiguration$RootType;", "getRoots", "()Lgnu/trove/THashMap;", "cidr-common" })
    private static final class State
    {
        @NotNull
        private final THashMap<VirtualFilePointer, RootType> roots;
        
        @NotNull
        public final THashMap<VirtualFilePointer, RootType> getRoots() {
            return this.roots;
        }
        
        public State() {
            this.roots = (THashMap<VirtualFilePointer, RootType>)new THashMap();
        }
    }
    
    @Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0007R\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e" }, d2 = { "Lcom/jetbrains/cidr/project/CidrRootConfiguration$Companion;", "", "()V", "MODULE_SUPPORTS_ROOTS_CONFIGURATION", "Lcom/intellij/openapi/util/Key;", "", "getInstance", "Lcom/jetbrains/cidr/project/CidrRootConfiguration;", "project", "Lcom/intellij/openapi/project/Project;", "setEnabledFor", "", "module", "Lcom/intellij/openapi/module/Module;", "cidr-common" })
    public static final class Companion
    {
        @JvmStatic
        @NotNull
        public final CidrRootConfiguration getInstance(@NotNull final Project project) {
            Intrinsics.checkParameterIsNotNull((Object)project, "project");
            final Object component = project.getComponent((Class)CidrRootConfiguration.class);
            if (component == null) {
                Intrinsics.throwNpe();
            }
            return (CidrRootConfiguration)component;
        }
        
        @JvmStatic
        public final void setEnabledFor(@NotNull final Module module) {
            Intrinsics.checkParameterIsNotNull((Object)module, "module");
            module.putUserData((Key)CidrRootConfiguration.MODULE_SUPPORTS_ROOTS_CONFIGURATION, (Object)true);
        }
    }
}
