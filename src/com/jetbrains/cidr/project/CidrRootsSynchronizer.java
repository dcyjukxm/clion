// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.project;

import java.util.Map;
import com.jetbrains.cidr.lang.workspace.headerRoots.PathTree;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.psi.search.PsiElementProcessor;
import com.jetbrains.cidr.lang.workspace.headerRoots.RealFramework;
import com.jetbrains.cidr.lang.workspace.headerRoots.HeadersSearchRootProcessor;
import com.jetbrains.cidr.lang.workspace.headerRoots.HeadersSearchRoot;
import com.intellij.openapi.vfs.VfsUtilCore;
import java.util.ArrayList;
import com.intellij.openapi.roots.ModuleFileIndex;
import com.intellij.openapi.vfs.newvfs.events.VFileMoveEvent;
import com.intellij.openapi.vfs.VirtualFile;
import gnu.trove.THashSet;
import com.intellij.openapi.roots.impl.ModuleLibraryOrderEntryImpl;
import com.intellij.openapi.roots.OrderEntry;
import com.intellij.util.containers.FactoryMap;
import com.intellij.openapi.vfs.newvfs.events.VFileEvent;
import com.intellij.openapi.vfs.newvfs.BulkFileListener;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.openapi.roots.ExcludeFolder;
import com.intellij.openapi.roots.SourceFolder;
import com.intellij.openapi.roots.ContentEntry;
import java.util.Iterator;
import com.intellij.openapi.roots.OrderRootType;
import java.util.Stack;
import com.jetbrains.cidr.CidrBundle;
import com.intellij.openapi.roots.impl.libraries.LibraryEx;
import com.intellij.util.containers.ContainerUtil;
import java.util.Collection;
import java.io.File;
import java.util.List;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.facet.Facet;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.module.Module;
import com.intellij.facet.FacetManager;
import com.intellij.openapi.roots.ex.ProjectRootManagerEx;
import java.util.Collections;
import com.intellij.openapi.roots.ModuleRootModel;
import com.intellij.openapi.roots.impl.storage.ClasspathStorage;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.util.Disposer;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.vfs.LocalFileSystem;
import java.util.Set;
import com.intellij.openapi.module.ModuleType;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.Disposable;

public class CidrRootsSynchronizer implements Disposable
{
    @NotNull
    protected final Project myProject;
    @NotNull
    private final ModuleType myModuleType;
    @Nullable
    private Set<LocalFileSystem.WatchRequest> myWatchRequest;
    
    public CidrRootsSynchronizer(@NotNull final Project myProject, @NotNull final ModuleType myModuleType) {
        if (myProject == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/project/CidrRootsSynchronizer", "<init>"));
        }
        if (myModuleType == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "moduleType", "com/jetbrains/cidr/project/CidrRootsSynchronizer", "<init>"));
        }
        this.myProject = myProject;
        this.myModuleType = myModuleType;
        Disposer.register((Disposable)myProject, (Disposable)this);
        this.b();
    }
    
    public void installClasspathStorage(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "id", "com/jetbrains/cidr/project/CidrRootsSynchronizer", "installClasspathStorage"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        ClasspathStorage.setStorageType((ModuleRootModel)ModuleRootManager.getInstance(this.getModule()), s);
    }
    
    public void dispose() {
        this.a(Collections.emptyList());
    }
    
    public void updateRoots(@NotNull final RootsInfo rootsInfo) {
        try {
            if (rootsInfo == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "info", "com/jetbrains/cidr/project/CidrRootsSynchronizer", "updateRoots"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.updateRoots(rootsInfo, false);
    }
    
    public void updateRoots(@NotNull final RootsInfo rootsInfo, final boolean b) {
        try {
            if (rootsInfo == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "info", "com/jetbrains/cidr/project/CidrRootsSynchronizer", "updateRoots"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final Module module = this.getModule();
        if (module != null) {
            this.updateModuleRoots(ModuleRootManager.getInstance(module).getModifiableModel(), rootsInfo);
            final ModifiableRootModel modifiableRootModel;
            final Module module2;
            final Facet[] array;
            int length;
            int i;
            ProjectRootManagerEx.getInstanceEx(this.myProject).mergeRootsChangesDuring(() -> {
                Label_0028_1: {
                    try {
                        if (modifiableRootModel.isChanged()) {
                            modifiableRootModel.commit();
                            break Label_0028_1;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    modifiableRootModel.dispose();
                    try {
                        if (b) {
                            ProjectRootManagerEx.getInstanceEx(this.myProject).makeRootsChange(() -> {}, false, true);
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                }
                FacetManager.getInstance(module2).getAllFacets();
                for (length = array.length; i < length; ++i) {
                    array[i].initFacet();
                }
                return;
            });
        }
        this.a(rootsInfo.watchRoots);
    }
    
    public void updateModuleRoots(@NotNull final ModifiableRootModel modifiableRootModel, @NotNull final RootsInfo rootsInfo) {
        try {
            if (modifiableRootModel == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "model", "com/jetbrains/cidr/project/CidrRootsSynchronizer", "updateModuleRoots"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (rootsInfo == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "info", "com/jetbrains/cidr/project/CidrRootsSynchronizer", "updateModuleRoots"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        a(modifiableRootModel, rootsInfo);
    }
    
    @Nullable
    public Module getModule() {
        return getModule(this.myProject, this.myModuleType);
    }
    
    @Nullable
    public static Module getModule(@NotNull final Project project, @NotNull final ModuleType moduleType) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/project/CidrRootsSynchronizer", "getModule"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (moduleType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/project/CidrRootsSynchronizer", "getModule"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        for (final Module module : ModuleManager.getInstance(project).getModules()) {
            try {
                if (moduleType.equals((Object)ModuleType.get(module))) {
                    return module;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return null;
    }
    
    private void a(@NotNull final List<File> list) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "roots", "com/jetbrains/cidr/project/CidrRootsSynchronizer", "registerWatchRoots"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (this.myWatchRequest != null) {
                LocalFileSystem.getInstance().removeWatchedRoots((Collection)this.myWatchRequest);
                this.myWatchRequest = null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        if (!list.isEmpty()) {
            this.myWatchRequest = (Set<LocalFileSystem.WatchRequest>)LocalFileSystem.getInstance().addRootsToWatch((Collection)ContainerUtil.map((Collection)list, file -> file.getPath()), true);
        }
    }
    
    private static void a(final ModifiableRootModel modifiableRootModel, @NotNull final RootsInfo rootsInfo) {
        try {
            if (rootsInfo == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "info", "com/jetbrains/cidr/project/CidrRootsSynchronizer", "registerRoots"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        modifiableRootModel.clear();
        final RootTree rootTree = new RootTree();
        rootTree.addAll(rootsInfo.contentRoots, RootKind.CONTENT);
        rootTree.addAll(rootsInfo.sourceFiles, RootKind.SOURCE);
        rootTree.addAll(rootsInfo.explicitLibraryRoots, RootKind.EXPLICIT_LIBRARY);
        rootTree.addAll(rootsInfo.explicitSourceFolders, RootKind.EXPLICIT_SOURCE);
        rootTree.addAll(rootsInfo.explicitExcludeFolders, RootKind.EXPLICIT_EXCLUDE);
        rootTree.addAll(rootsInfo.headerSearchRoots.systemHeaderRoots, RootKind.LIBRARY);
        rootTree.addAll(rootsInfo.headerSearchRoots.excludeRoots, RootKind.LIBRARY_EXCLUDE);
        rootTree.addAll(a((Collection<File>)rootsInfo.headerSearchRoots.userHeaderRoots), RootKind.CONTENT);
        final LibraryEx.ModifiableModelEx modifiableModelEx = (LibraryEx.ModifiableModelEx)modifiableRootModel.getModuleLibraryTable().createLibrary(CidrBundle.message("project.view.library.node", new Object[0])).getModifiableModel();
        rootTree.accept(new RootTree.Visitor() {
            final Stack<State> stack = new Stack<State>();
            State state;
            
            @Override
            public void enter() {
                State state = null;
                Label_0047: {
                    try {
                        this.stack.push(this.state);
                        if (this.state == null) {
                            state = new State();
                            break Label_0047;
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    state = new State(this.state);
                }
                this.state = state;
            }
            
            @Override
            public boolean visit(@NotNull final Set<RootItem> p0) {
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
                //    18: ldc             "items"
                //    20: aastore        
                //    21: dup            
                //    22: ldc             1
                //    24: ldc             "com/jetbrains/cidr/project/CidrRootsSynchronizer$1"
                //    26: aastore        
                //    27: dup            
                //    28: ldc             2
                //    30: ldc             "visit"
                //    32: aastore        
                //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
                //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
                //    39: athrow         
                //    40: invokestatic    com/jetbrains/cidr/project/CidrRootsSynchronizer$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    43: athrow         
                //    44: aload_1        
                //    45: invokeinterface java/util/Set.isEmpty:()Z
                //    50: ifeq            59
                //    53: iconst_1       
                //    54: ireturn        
                //    55: invokestatic    com/jetbrains/cidr/project/CidrRootsSynchronizer$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    58: athrow         
                //    59: aload_0        
                //    60: aload_1        
                //    61: getstatic       com/jetbrains/cidr/project/CidrRootsSynchronizer$RootKind.EXPLICIT_EXCLUDE:Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootKind;
                //    64: invokespecial   com/jetbrains/cidr/project/CidrRootsSynchronizer$1.a:(Ljava/util/Set;Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootKind;)Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootItem;
                //    67: astore_2       
                //    68: aload_2        
                //    69: ifnull          153
                //    72: aload_0        
                //    73: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1.state:Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$1$State;
                //    76: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1$State.contentEntry:Lcom/intellij/openapi/roots/ContentEntry;
                //    79: ifnull          113
                //    82: goto            89
                //    85: invokestatic    com/jetbrains/cidr/project/CidrRootsSynchronizer$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    88: athrow         
                //    89: aload_0        
                //    90: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1.state:Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$1$State;
                //    93: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1$State.contentEntry:Lcom/intellij/openapi/roots/ContentEntry;
                //    96: aload_2        
                //    97: invokevirtual   com/jetbrains/cidr/project/CidrRootsSynchronizer$RootItem.getUrl:()Ljava/lang/String;
                //   100: invokeinterface com/intellij/openapi/roots/ContentEntry.addExcludeFolder:(Ljava/lang/String;)Lcom/intellij/openapi/roots/ExcludeFolder;
                //   105: pop            
                //   106: goto            113
                //   109: invokestatic    com/jetbrains/cidr/project/CidrRootsSynchronizer$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   112: athrow         
                //   113: aload_0        
                //   114: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1.state:Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$1$State;
                //   117: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1$State.isLibraryRoot:Z
                //   120: ifeq            151
                //   123: aload_0        
                //   124: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1.val$libraryModel:Lcom/intellij/openapi/roots/impl/libraries/LibraryEx$ModifiableModelEx;
                //   127: aload_2        
                //   128: invokevirtual   com/jetbrains/cidr/project/CidrRootsSynchronizer$RootItem.getUrl:()Ljava/lang/String;
                //   131: invokeinterface com/intellij/openapi/roots/impl/libraries/LibraryEx$ModifiableModelEx.addExcludedRoot:(Ljava/lang/String;)V
                //   136: aload_0        
                //   137: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1.state:Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$1$State;
                //   140: iconst_0       
                //   141: putfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1$State.isLibraryRoot:Z
                //   144: goto            151
                //   147: invokestatic    com/jetbrains/cidr/project/CidrRootsSynchronizer$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   150: athrow         
                //   151: iconst_0       
                //   152: ireturn        
                //   153: aload_0        
                //   154: aload_1        
                //   155: getstatic       com/jetbrains/cidr/project/CidrRootsSynchronizer$RootKind.CONTENT:Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootKind;
                //   158: invokespecial   com/jetbrains/cidr/project/CidrRootsSynchronizer$1.a:(Ljava/util/Set;Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootKind;)Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootItem;
                //   161: astore_3       
                //   162: aload_3        
                //   163: ifnull          248
                //   166: aload_0        
                //   167: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1.state:Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$1$State;
                //   170: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1$State.contentEntry:Lcom/intellij/openapi/roots/ContentEntry;
                //   173: ifnonnull       248
                //   176: goto            183
                //   179: invokestatic    com/jetbrains/cidr/project/CidrRootsSynchronizer$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   182: athrow         
                //   183: aload_0        
                //   184: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1.state:Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$1$State;
                //   187: aload_0        
                //   188: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1.val$model:Lcom/intellij/openapi/roots/ModifiableRootModel;
                //   191: aload_3        
                //   192: invokevirtual   com/jetbrains/cidr/project/CidrRootsSynchronizer$RootItem.getUrl:()Ljava/lang/String;
                //   195: invokeinterface com/intellij/openapi/roots/ModifiableRootModel.addContentEntry:(Ljava/lang/String;)Lcom/intellij/openapi/roots/ContentEntry;
                //   200: putfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1$State.contentEntry:Lcom/intellij/openapi/roots/ContentEntry;
                //   203: aload_0        
                //   204: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1.state:Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$1$State;
                //   207: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1$State.isLibraryRoot:Z
                //   210: ifeq            248
                //   213: goto            220
                //   216: invokestatic    com/jetbrains/cidr/project/CidrRootsSynchronizer$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   219: athrow         
                //   220: aload_0        
                //   221: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1.val$libraryModel:Lcom/intellij/openapi/roots/impl/libraries/LibraryEx$ModifiableModelEx;
                //   224: aload_3        
                //   225: invokevirtual   com/jetbrains/cidr/project/CidrRootsSynchronizer$RootItem.getUrl:()Ljava/lang/String;
                //   228: invokeinterface com/intellij/openapi/roots/impl/libraries/LibraryEx$ModifiableModelEx.addExcludedRoot:(Ljava/lang/String;)V
                //   233: aload_0        
                //   234: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1.state:Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$1$State;
                //   237: iconst_0       
                //   238: putfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1$State.isLibraryRoot:Z
                //   241: goto            248
                //   244: invokestatic    com/jetbrains/cidr/project/CidrRootsSynchronizer$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   247: athrow         
                //   248: aload_0        
                //   249: aload_1        
                //   250: getstatic       com/jetbrains/cidr/project/CidrRootsSynchronizer$RootKind.EXPLICIT_SOURCE:Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootKind;
                //   253: invokespecial   com/jetbrains/cidr/project/CidrRootsSynchronizer$1.a:(Ljava/util/Set;Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootKind;)Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootItem;
                //   256: astore          4
                //   258: aload           4
                //   260: ifnull          283
                //   263: aload_0        
                //   264: aload           4
                //   266: invokespecial   com/jetbrains/cidr/project/CidrRootsSynchronizer$1.a:(Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootItem;)V
                //   269: aload_0        
                //   270: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1.state:Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$1$State;
                //   273: iconst_1       
                //   274: putfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1$State.isExplicitRoot:Z
                //   277: iconst_1       
                //   278: ireturn        
                //   279: invokestatic    com/jetbrains/cidr/project/CidrRootsSynchronizer$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   282: athrow         
                //   283: aload_0        
                //   284: aload_1        
                //   285: getstatic       com/jetbrains/cidr/project/CidrRootsSynchronizer$RootKind.EXPLICIT_LIBRARY:Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootKind;
                //   288: invokespecial   com/jetbrains/cidr/project/CidrRootsSynchronizer$1.a:(Ljava/util/Set;Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootKind;)Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootItem;
                //   291: astore          5
                //   293: aload           5
                //   295: ifnull          318
                //   298: aload_0        
                //   299: aload           5
                //   301: invokespecial   com/jetbrains/cidr/project/CidrRootsSynchronizer$1.b:(Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootItem;)V
                //   304: aload_0        
                //   305: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1.state:Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$1$State;
                //   308: iconst_1       
                //   309: putfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1$State.isExplicitRoot:Z
                //   312: iconst_1       
                //   313: ireturn        
                //   314: invokestatic    com/jetbrains/cidr/project/CidrRootsSynchronizer$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   317: athrow         
                //   318: aload_0        
                //   319: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1.state:Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$1$State;
                //   322: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1$State.isExplicitRoot:Z
                //   325: ifeq            334
                //   328: iconst_1       
                //   329: ireturn        
                //   330: invokestatic    com/jetbrains/cidr/project/CidrRootsSynchronizer$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   333: athrow         
                //   334: aload_0        
                //   335: aload_1        
                //   336: getstatic       com/jetbrains/cidr/project/CidrRootsSynchronizer$RootKind.SOURCE:Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootKind;
                //   339: invokespecial   com/jetbrains/cidr/project/CidrRootsSynchronizer$1.a:(Ljava/util/Set;Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootKind;)Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootItem;
                //   342: astore          4
                //   344: aload           4
                //   346: ifnull          361
                //   349: aload_0        
                //   350: aload           4
                //   352: invokespecial   com/jetbrains/cidr/project/CidrRootsSynchronizer$1.a:(Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootItem;)V
                //   355: iconst_1       
                //   356: ireturn        
                //   357: invokestatic    com/jetbrains/cidr/project/CidrRootsSynchronizer$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   360: athrow         
                //   361: aload_0        
                //   362: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1.val$info:Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootsInfo;
                //   365: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$RootsInfo.registerSystemHeaderRootUnderContentRootAsLibraries:Z
                //   368: ifne            388
                //   371: aload_0        
                //   372: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1.state:Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$1$State;
                //   375: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1$State.contentEntry:Lcom/intellij/openapi/roots/ContentEntry;
                //   378: ifnonnull       481
                //   381: goto            388
                //   384: invokestatic    com/jetbrains/cidr/project/CidrRootsSynchronizer$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   387: athrow         
                //   388: aload_0        
                //   389: aload_1        
                //   390: getstatic       com/jetbrains/cidr/project/CidrRootsSynchronizer$RootKind.LIBRARY_EXCLUDE:Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootKind;
                //   393: invokespecial   com/jetbrains/cidr/project/CidrRootsSynchronizer$1.a:(Ljava/util/Set;Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootKind;)Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootItem;
                //   396: astore          6
                //   398: aload           6
                //   400: ifnull          449
                //   403: aload_0        
                //   404: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1.state:Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$1$State;
                //   407: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1$State.isLibraryRoot:Z
                //   410: ifeq            449
                //   413: goto            420
                //   416: invokestatic    com/jetbrains/cidr/project/CidrRootsSynchronizer$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   419: athrow         
                //   420: aload_0        
                //   421: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1.val$libraryModel:Lcom/intellij/openapi/roots/impl/libraries/LibraryEx$ModifiableModelEx;
                //   424: aload           6
                //   426: invokevirtual   com/jetbrains/cidr/project/CidrRootsSynchronizer$RootItem.getUrl:()Ljava/lang/String;
                //   429: invokeinterface com/intellij/openapi/roots/impl/libraries/LibraryEx$ModifiableModelEx.addExcludedRoot:(Ljava/lang/String;)V
                //   434: aload_0        
                //   435: getfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1.state:Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$1$State;
                //   438: iconst_0       
                //   439: putfield        com/jetbrains/cidr/project/CidrRootsSynchronizer$1$State.isLibraryRoot:Z
                //   442: goto            481
                //   445: invokestatic    com/jetbrains/cidr/project/CidrRootsSynchronizer$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   448: athrow         
                //   449: aload_3        
                //   450: ifnonnull       481
                //   453: aload_0        
                //   454: aload_1        
                //   455: getstatic       com/jetbrains/cidr/project/CidrRootsSynchronizer$RootKind.LIBRARY:Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootKind;
                //   458: invokespecial   com/jetbrains/cidr/project/CidrRootsSynchronizer$1.a:(Ljava/util/Set;Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootKind;)Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootItem;
                //   461: astore          5
                //   463: aload           5
                //   465: ifnull          481
                //   468: aload_0        
                //   469: aload           5
                //   471: invokespecial   com/jetbrains/cidr/project/CidrRootsSynchronizer$1.b:(Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootItem;)V
                //   474: goto            481
                //   477: invokestatic    com/jetbrains/cidr/project/CidrRootsSynchronizer$1.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   480: athrow         
                //   481: iconst_1       
                //   482: ireturn        
                //    Signature:
                //  (Ljava/util/Set<Lcom/jetbrains/cidr/project/CidrRootsSynchronizer$RootItem;>;)Z
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                                
                //  -----  -----  -----  -----  ------------------------------------
                //  0      40     40     44     Ljava/lang/IllegalArgumentException;
                //  44     55     55     59     Ljava/lang/IllegalArgumentException;
                //  68     82     85     89     Ljava/lang/IllegalArgumentException;
                //  72     106    109    113    Ljava/lang/IllegalArgumentException;
                //  113    144    147    151    Ljava/lang/IllegalArgumentException;
                //  162    176    179    183    Ljava/lang/IllegalArgumentException;
                //  166    213    216    220    Ljava/lang/IllegalArgumentException;
                //  183    241    244    248    Ljava/lang/IllegalArgumentException;
                //  258    279    279    283    Ljava/lang/IllegalArgumentException;
                //  293    314    314    318    Ljava/lang/IllegalArgumentException;
                //  318    330    330    334    Ljava/lang/IllegalArgumentException;
                //  344    357    357    361    Ljava/lang/IllegalArgumentException;
                //  361    381    384    388    Ljava/lang/IllegalArgumentException;
                //  398    413    416    420    Ljava/lang/IllegalArgumentException;
                //  403    445    445    449    Ljava/lang/IllegalArgumentException;
                //  463    474    477    481    Ljava/lang/IllegalArgumentException;
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0183:
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
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1163)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1010)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
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
            
            private void a(@NotNull final RootItem rootItem) {
                try {
                    if (rootItem == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "item", "com/jetbrains/cidr/project/CidrRootsSynchronizer$1", "addSourceFolder"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    if (this.state.contentEntry == null) {
                        this.state.contentEntry = modifiableRootModel.addContentEntry(rootItem.getUrl());
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    if (this.state.sourceFolder == null) {
                        this.state.sourceFolder = this.state.contentEntry.addSourceFolder(rootItem.getUrl(), false);
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    if (this.state.isLibraryRoot) {
                        modifiableModelEx.addExcludedRoot(rootItem.getUrl());
                        this.state.isLibraryRoot = false;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            
            private void b(@NotNull final RootItem rootItem) {
                try {
                    if (rootItem == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "item", "com/jetbrains/cidr/project/CidrRootsSynchronizer$1", "addLibraryRoot"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                if (!this.state.isLibraryRoot) {
                    final String url = rootItem.getUrl();
                    modifiableModelEx.addRoot(url, OrderRootType.CLASSES);
                    modifiableModelEx.addRoot(url, OrderRootType.SOURCES);
                    this.state.isLibraryRoot = true;
                    this.state.sourceFolder = null;
                }
            }
            
            @Nullable
            private RootItem a(final Set<RootItem> set, final RootKind rootKind) {
                for (final RootItem rootItem : set) {
                    try {
                        if (rootItem.kind == rootKind) {
                            return rootItem;
                        }
                        continue;
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                }
                return null;
            }
            
            @Override
            public void exit() {
                this.state = this.stack.pop();
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
            
            class State
            {
                @Nullable
                ContentEntry contentEntry;
                @Nullable
                SourceFolder sourceFolder;
                @Nullable
                ExcludeFolder excludeFolder;
                boolean isLibraryRoot;
                boolean isExplicitRoot;
                
                public State() {
                }
                
                public State(final State state) {
                    this.contentEntry = state.contentEntry;
                    this.sourceFolder = state.sourceFolder;
                    this.excludeFolder = state.excludeFolder;
                    this.isLibraryRoot = state.isLibraryRoot;
                    this.isExplicitRoot = state.isExplicitRoot;
                }
            }
        });
        modifiableModelEx.commit();
    }
    
    private void b() {
        this.myProject.getMessageBus().connect((Disposable)this).subscribe(VirtualFileManager.VFS_CHANGES, (Object)new BulkFileListener() {
            public void after(@NotNull final List<? extends VFileEvent> list) {
                try {
                    if (list == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "events", "com/jetbrains/cidr/project/CidrRootsSynchronizer$2", "after"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                final Module module = CidrRootsSynchronizer.this.getModule();
                try {
                    if (module == null) {
                        return;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                final ModuleRootManager instance = ModuleRootManager.getInstance(module);
                final ModuleFileIndex fileIndex = instance.getFileIndex();
                final FactoryMap<OrderEntry, LibraryEx.ModifiableModelEx> factoryMap = new FactoryMap<OrderEntry, LibraryEx.ModifiableModelEx>() {
                    @Nullable
                    protected LibraryEx.ModifiableModelEx create(final OrderEntry orderEntry) {
                        return (LibraryEx.ModifiableModelEx)((ModuleLibraryOrderEntryImpl)orderEntry).getLibrary().getModifiableModel();
                    }
                };
                final THashSet set = new THashSet();
                Collections.addAll((Collection<? super VirtualFile>)set, instance.getContentRoots());
                Collections.addAll((Collection<? super VirtualFile>)set, instance.getSourceRoots());
                Collections.addAll((Collection<? super VirtualFile>)set, instance.getExcludeRoots());
                for (final VFileEvent vFileEvent : list) {
                    try {
                        if (!(vFileEvent instanceof VFileMoveEvent)) {
                            continue;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                    for (final VirtualFile virtualFile : this.a(((VFileMoveEvent)vFileEvent).getFile(), (Collection<VirtualFile>)set)) {
                        final OrderEntry orderEntryForFile = fileIndex.getOrderEntryForFile(((VFileMoveEvent)vFileEvent).getOldParent());
                        final OrderEntry orderEntryForFile2 = fileIndex.getOrderEntryForFile(virtualFile.getParent());
                        Label_0268: {
                            try {
                                if (orderEntryForFile instanceof ModuleLibraryOrderEntryImpl == orderEntryForFile2 instanceof ModuleLibraryOrderEntryImpl) {
                                    continue;
                                }
                                final OrderEntry orderEntry = orderEntryForFile2;
                                final boolean b = orderEntry instanceof ModuleLibraryOrderEntryImpl;
                                if (b) {
                                    break Label_0268;
                                }
                                break Label_0268;
                            }
                            catch (IllegalArgumentException ex4) {
                                throw a(ex4);
                            }
                            try {
                                final OrderEntry orderEntry = orderEntryForFile2;
                                final boolean b = orderEntry instanceof ModuleLibraryOrderEntryImpl;
                                if (b) {
                                    ((Map<K, LibraryEx.ModifiableModelEx>)factoryMap).get(orderEntryForFile2).addExcludedRoot(virtualFile.getUrl());
                                    continue;
                                }
                            }
                            catch (IllegalArgumentException ex5) {
                                throw a(ex5);
                            }
                        }
                        ((Map<K, LibraryEx.ModifiableModelEx>)factoryMap).get(orderEntryForFile).removeExcludedRoot(virtualFile.getUrl());
                    }
                }
                final Iterator<LibraryEx.ModifiableModelEx> iterator3 = ((Map<K, LibraryEx.ModifiableModelEx>)factoryMap).values().iterator();
                while (iterator3.hasNext()) {
                    iterator3.next().commit();
                }
            }
            
            private List<VirtualFile> a(final VirtualFile virtualFile, final Collection<VirtualFile> collection) {
                final ArrayList<VirtualFile> list = new ArrayList<VirtualFile>();
                VfsUtilCore.processFilesRecursively(virtualFile, virtualFile -> {
                    try {
                        if (collection.contains(virtualFile)) {
                            list.add(virtualFile);
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    return true;
                });
                return list;
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        });
    }
    
    public static void collectHeaderSearchRoots(@NotNull final Collection<? extends HeadersSearchRoot> collection, @NotNull final HeaderSearchRoots headerSearchRoots) {
        try {
            if (collection == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "headerRoots", "com/jetbrains/cidr/project/CidrRootsSynchronizer", "collectHeaderSearchRoots"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (headerSearchRoots == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/project/CidrRootsSynchronizer", "collectHeaderSearchRoots"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        for (final HeadersSearchRoot headersSearchRoot : collection) {
            headersSearchRoot.processChildren((PsiElementProcessor)new HeadersSearchRootProcessor() {
                @Override
                public boolean shouldProcessRootsOnly() {
                    return true;
                }
                
                @Override
                public boolean processFramework(@NotNull final RealFramework realFramework) {
                    try {
                        if (realFramework == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "framework", "com/jetbrains/cidr/project/CidrRootsSynchronizer$3", "processFramework"));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    headerSearchRoots.excludeRoots.add(new File(realFramework.getVirtualFile().getPath()));
                    return true;
                }
                
                @Override
                public boolean process(@NotNull final VirtualFile virtualFile) {
                    try {
                        if (virtualFile == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/project/CidrRootsSynchronizer$3", "process"));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    Label_0068: {
                        try {
                            if (!virtualFile.isDirectory()) {
                                return true;
                            }
                            final HeadersSearchRootProcessor headersSearchRootProcessor = this;
                            final HeadersSearchRoot headersSearchRoot = headersSearchRoot;
                            final boolean b = headersSearchRoot.isUserHeaders();
                            if (b) {
                                break Label_0068;
                            }
                            break Label_0068;
                        }
                        catch (IllegalArgumentException ex2) {
                            throw a(ex2);
                        }
                        try {
                            final HeadersSearchRootProcessor headersSearchRootProcessor = this;
                            final HeadersSearchRoot headersSearchRoot = headersSearchRoot;
                            final boolean b = headersSearchRoot.isUserHeaders();
                            if (b) {
                                headerSearchRoots.userHeaderRoots.add(new File(virtualFile.getPath()));
                                return true;
                            }
                        }
                        catch (IllegalArgumentException ex3) {
                            throw a(ex3);
                        }
                    }
                    headerSearchRoots.systemHeaderRoots.add(new File(virtualFile.getPath()));
                    return true;
                }
                
                private static IllegalArgumentException a(final IllegalArgumentException ex) {
                    return ex;
                }
            });
        }
    }
    
    public static boolean isUnder(@Nullable final Collection<File> collection, @Nullable final File file) {
        return isUnder(collection, file, false);
    }
    
    public static boolean isUnder(@Nullable final Collection<File> collection, @Nullable final File file, final boolean b) {
        Label_0021: {
            try {
                if (collection == null) {
                    return false;
                }
                final File file2 = file;
                if (file2 == null) {
                    return false;
                }
                break Label_0021;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final File file2 = file;
                if (file2 == null) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        for (final File file3 : collection) {
            try {
                if (FileUtil.isAncestor(file3, file, b)) {
                    return true;
                }
                continue;
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return false;
    }
    
    private static List<File> a(final Collection<File> collection) {
        final ArrayList arrayList = ContainerUtil.newArrayList((Iterable)collection);
        Collections.sort((List<Object>)arrayList, (file, file2) -> FileUtil.compareFiles(file, file2));
        final Iterator<File> iterator = arrayList.iterator();
        File file3 = null;
        while (iterator.hasNext()) {
            final File file4 = iterator.next();
            Label_0064: {
                try {
                    if (file3 == null) {
                        break Label_0064;
                    }
                    final File file5 = file3;
                    final File file6 = file4;
                    final boolean b = false;
                    final boolean b2 = FileUtil.isAncestor(file5, file6, b);
                    if (b2) {
                        break Label_0064;
                    }
                    break Label_0064;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final File file5 = file3;
                    final File file6 = file4;
                    final boolean b = false;
                    final boolean b2 = FileUtil.isAncestor(file5, file6, b);
                    if (b2) {
                        iterator.remove();
                        continue;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            file3 = file4;
        }
        return (List<File>)arrayList;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    public static class HeaderSearchRoots
    {
        @NotNull
        public final List<File> systemHeaderRoots;
        @NotNull
        public final List<File> userHeaderRoots;
        @NotNull
        public final List<File> excludeRoots;
        
        public HeaderSearchRoots() {
            this.systemHeaderRoots = new ArrayList<File>();
            this.userHeaderRoots = new ArrayList<File>();
            this.excludeRoots = new ArrayList<File>();
        }
    }
    
    public static class RootsInfo
    {
        @NotNull
        public final List<File> watchRoots;
        @NotNull
        public final List<File> contentRoots;
        @NotNull
        public final List<File> sourceFiles;
        @NotNull
        public final List<File> explicitSourceFolders;
        @NotNull
        public final List<File> explicitLibraryRoots;
        @NotNull
        public final List<File> explicitExcludeFolders;
        @NotNull
        public final HeaderSearchRoots headerSearchRoots;
        public boolean registerSystemHeaderRootUnderContentRootAsLibraries;
        
        public RootsInfo() {
            this.watchRoots = new ArrayList<File>();
            this.contentRoots = new ArrayList<File>();
            this.sourceFiles = new ArrayList<File>();
            this.explicitSourceFolders = new ArrayList<File>();
            this.explicitLibraryRoots = new ArrayList<File>();
            this.explicitExcludeFolders = new ArrayList<File>();
            this.headerSearchRoots = new HeaderSearchRoots();
            this.registerSystemHeaderRootUnderContentRootAsLibraries = true;
        }
        
        public void fillHeaderSearchRoots(@NotNull final Collection<? extends HeadersSearchRoot> collection) {
            try {
                if (collection == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "headerRoots", "com/jetbrains/cidr/project/CidrRootsSynchronizer$RootsInfo", "fillHeaderSearchRoots"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            CidrRootsSynchronizer.collectHeaderSearchRoots(collection, this.headerSearchRoots);
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    private enum RootKind
    {
        CONTENT, 
        SOURCE, 
        LIBRARY, 
        LIBRARY_EXCLUDE, 
        EXPLICIT_SOURCE, 
        EXPLICIT_LIBRARY, 
        EXPLICIT_EXCLUDE;
    }
    
    private static class RootItem
    {
        @NotNull
        final File file;
        @NotNull
        final RootKind kind;
        
        public RootItem(@NotNull final File file, @NotNull final RootKind kind) {
            if (file == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/project/CidrRootsSynchronizer$RootItem", "<init>"));
            }
            if (kind == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "kind", "com/jetbrains/cidr/project/CidrRootsSynchronizer$RootItem", "<init>"));
            }
            this.kind = kind;
            this.file = file;
        }
        
        @NotNull
        String getUrl() {
            String pathToUrl;
            try {
                pathToUrl = VfsUtilCore.pathToUrl(FileUtil.toSystemIndependentName(this.file.getPath()));
                if (pathToUrl == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/project/CidrRootsSynchronizer$RootItem", "getUrl"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return pathToUrl;
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    private static class RootTree extends PathTree<RootTree, RootItem>
    {
        @Override
        protected RootTree createNewTree(@Nullable final RootTree rootTree) {
            return new RootTree();
        }
        
        public void addAll(@NotNull final Collection<File> collection, @NotNull final RootKind rootKind) {
            try {
                if (collection == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "files", "com/jetbrains/cidr/project/CidrRootsSynchronizer$RootTree", "addAll"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                if (rootKind == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "kind", "com/jetbrains/cidr/project/CidrRootsSynchronizer$RootTree", "addAll"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            for (final File file : collection) {
                ((PathTree<Self, RootItem>)this).addItem(file.getPath(), new RootItem(file, rootKind));
            }
        }
        
        public void accept(@NotNull final Visitor visitor) {
            try {
                if (visitor == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/project/CidrRootsSynchronizer$RootTree", "accept"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            visitor.enter();
            if (visitor.visit(((PathTree<Self, RootItem>)this).getItems())) {
                final Iterator<RootTree> iterator = ((PathTree<RootTree, T>)this).getChildren().iterator();
                while (iterator.hasNext()) {
                    iterator.next().accept(visitor);
                }
            }
            visitor.exit();
        }
        
        private static IllegalArgumentException b(final IllegalArgumentException ex) {
            return ex;
        }
        
        public interface Visitor
        {
            void enter();
            
            boolean visit(@NotNull final Set<RootItem> p0);
            
            void exit();
        }
    }
}
