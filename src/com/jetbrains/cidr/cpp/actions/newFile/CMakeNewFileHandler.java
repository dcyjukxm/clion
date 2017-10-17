// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.actions.newFile;

import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.editor.markup.MarkupModel;
import com.intellij.openapi.editor.ScrollingModel;
import com.intellij.openapi.editor.markup.HighlighterTargetArea;
import com.intellij.openapi.editor.colors.EditorColors;
import com.intellij.openapi.editor.ScrollType;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.codeInsight.hint.EditorFragmentComponent;
import com.intellij.openapi.diagnostic.Logger;
import com.jetbrains.cidr.cpp.CPPLog;
import com.intellij.openapi.editor.ex.EditorEx;
import java.awt.Dimension;
import com.intellij.openapi.editor.colors.EditorFontType;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.Document;
import com.jetbrains.cidr.cpp.cmake.CMakeListsFileType;
import com.intellij.ui.EditorTextField;
import com.intellij.openapi.util.io.FileUtil;
import com.jetbrains.cidr.cpp.cmake.psi.util.CMakeFileLocationUtil;
import com.intellij.psi.SmartPointerManager;
import com.intellij.psi.SmartPsiElementPointer;
import com.jetbrains.cidr.cpp.cmake.workspace.CMakeResolveConfiguration;
import gnu.trove.THashSet;
import com.intellij.openapi.util.Comparing;
import gnu.trove.TObjectIntProcedure;
import gnu.trove.TObjectIntHashMap;
import com.intellij.openapi.util.Pair;
import com.jetbrains.cidr.cpp.cmake.model.CMakeTarget;
import com.jetbrains.cidr.lang.workspace.OCResolveConfiguration;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContextUtil;
import javax.swing.JComponent;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeArgument;
import com.intellij.openapi.util.text.StringUtil;
import java.util.Comparator;
import java.util.Map;
import com.intellij.psi.PsiElement;
import java.util.Collection;
import java.util.Locale;
import com.jetbrains.cidr.cpp.cmake.interpreter.CMakeScope;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeCommand;
import java.util.Set;
import com.jetbrains.cidr.cpp.cmake.interpreter.CMakeInterpreter;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.containers.MultiMap;
import java.util.Stack;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.Iterator;
import java.util.Collections;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeFile;
import com.intellij.psi.PsiManager;
import com.intellij.openapi.roots.FileIndexFacade;
import java.io.File;
import com.intellij.openapi.vfs.LocalFileSystem;
import java.util.ArrayList;
import com.intellij.openapi.project.Project;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.cpp.cmake.workspace.CMakeWorkspace;

public class CMakeNewFileHandler implements NewFileHandler<CMakeNewFileTarget>
{
    @NotNull
    private final CMakeWorkspace myWorkspace;
    @NotNull
    private final List<CMakeNewFileTarget> myTargets;
    
    public CMakeNewFileHandler(@NotNull final Project project) {
        if (project == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler", "<init>"));
        }
        this.myWorkspace = CMakeWorkspace.getInstance(project);
        this.myTargets = a(project, this.myWorkspace);
    }
    
    @NotNull
    private static List<CMakeNewFileTarget> a(@NotNull final Project project, @NotNull final CMakeWorkspace cMakeWorkspace) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler", "readTargets"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (cMakeWorkspace == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "workspace", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler", "readTargets"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final ArrayList<CMakeNewFileTarget> list = new ArrayList<CMakeNewFileTarget>();
        final Iterator<File> iterator = cMakeWorkspace.getCMakeFiles().iterator();
        while (iterator.hasNext()) {
            final VirtualFile fileByIoFile = LocalFileSystem.getInstance().findFileByIoFile((File)iterator.next());
            if (fileByIoFile != null) {
                try {
                    if (!FileIndexFacade.getInstance(project).isInContent(fileByIoFile)) {
                        continue;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                final PsiFile file = PsiManager.getInstance(project).findFile(fileByIoFile);
                try {
                    if (!(file instanceof CMakeFile)) {
                        continue;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
                collectTargets(list, (CMakeFile)file);
            }
        }
        List<Object> unmodifiableList;
        try {
            a(list);
            unmodifiableList = (List<Object>)Collections.unmodifiableList((List<? extends CMakeNewFileTarget>)list);
            if (unmodifiableList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler", "readTargets"));
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        return (List<CMakeNewFileTarget>)unmodifiableList;
    }
    
    static void collectTargets(@NotNull final ArrayList<CMakeNewFileTarget> list, @NotNull final CMakeFile cMakeFile) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "targets", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler", "collectTargets"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (cMakeFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cf", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler", "collectTargets"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        PsiDocumentManager.getInstance(cMakeFile.getProject()).commitAllDocuments();
        final VarDependencies varDependencies = new VarDependencies();
        final Stack stack = new Stack();
        final Stack stack2 = new Stack();
        final MultiMap set = MultiMap.createSet();
        final Set set2 = ContainerUtil.set((Object[])new CMakeNewFileTarget[0]);
        CMakeInterpreter.interpret((PsiElement)cMakeFile, new CMakeInterpreter.Listener() {
            @Override
            public void beforeCommand(@NotNull final CMakeCommand cMakeCommand, @NotNull final CMakeScope cMakeScope) {
                try {
                    if (cMakeCommand == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cmd", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$1", "beforeCommand"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    if (cMakeScope == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "scope", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$1", "beforeCommand"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                final String lowerCase = cMakeCommand.getName().toLowerCase(Locale.getDefault());
                Label_0208: {
                    Label_0126: {
                        try {
                            if ("add_executable".equals(lowerCase)) {
                                break Label_0126;
                            }
                            final String s = "add_library";
                            final String s2 = lowerCase;
                            final boolean b = s.equals(s2);
                            if (b) {
                                break Label_0126;
                            }
                            break Label_0208;
                        }
                        catch (IllegalArgumentException ex3) {
                            throw a(ex3);
                        }
                        try {
                            final String s = "add_library";
                            final String s2 = lowerCase;
                            final boolean b = s.equals(s2);
                            if (!b) {
                                break Label_0208;
                            }
                            if (!cMakeCommand.canAppendArguments()) {
                                break Label_0208;
                            }
                        }
                        catch (IllegalArgumentException ex4) {
                            throw a(ex4);
                        }
                    }
                    final String access$000 = a(cMakeCommand, cMakeScope);
                    try {
                        if (access$000 != null) {
                            set2.add(new CMakeNewFileTarget(cMakeCommand, Collections.singletonList(access$000), access$000, 1));
                            a(cMakeCommand, varDependencies, new CommandDependencyHandler() {
                                @Override
                                public void handleDependency(@NotNull final CommandInfo commandInfo) {
                                    try {
                                        if (commandInfo == null) {
                                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "commandInfo", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$1$1", "handleDependency"));
                                        }
                                    }
                                    catch (IllegalArgumentException ex) {
                                        throw a(ex);
                                    }
                                    set.putValue((Object)commandInfo, (Object)access$000);
                                }
                                
                                private static IllegalArgumentException a(final IllegalArgumentException ex) {
                                    return ex;
                                }
                            });
                        }
                    }
                    catch (IllegalArgumentException ex5) {
                        throw a(ex5);
                    }
                    return;
                }
                if ("set".equals(lowerCase)) {
                    final String access$2 = a(cMakeCommand, cMakeScope);
                    if (access$2 != null) {
                        final String string = "${" + access$2 + "}";
                        final ArrayList<CommandInfo> list = new ArrayList<CommandInfo>();
                        list.add(new CommandInfo(string, cMakeCommand));
                        a(cMakeCommand, varDependencies, new CommandDependencyHandler() {
                            @Override
                            public void handleDependency(@NotNull final CommandInfo commandInfo) {
                                try {
                                    if (commandInfo == null) {
                                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "commandInfo", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$1$2", "handleDependency"));
                                    }
                                }
                                catch (IllegalArgumentException ex) {
                                    throw a(ex);
                                }
                                list.add(commandInfo);
                            }
                            
                            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                                return ex;
                            }
                        });
                        varDependencies.set(string, list);
                    }
                }
            }
            
            @Override
            public void onEnterIf() {
                this.a();
            }
            
            @Override
            public void onExitIf() {
                this.b();
            }
            
            @Override
            public void onEnterWhile() {
                this.a();
            }
            
            @Override
            public void onExitWhile() {
                this.b();
            }
            
            @Override
            public void onEnterForeach() {
                this.a();
            }
            
            @Override
            public void onExitForeach() {
                this.b();
            }
            
            private void b() {
                varDependencies.setAll(stack2.pop());
            }
            
            private void a() {
                stack2.push(new VarDependencies(varDependencies));
            }
            
            @Override
            public boolean onEnterLoop(final int n) {
                return true;
            }
            
            @Override
            public void onExitLoop() {
                stack2.peek().addAll(varDependencies);
            }
            
            @Override
            public void onEnterFunction() {
                this.a();
            }
            
            @Override
            public void onExitFunction() {
                this.b();
            }
            
            @Override
            public void onEnterMacro() {
                this.a();
            }
            
            @Override
            public void onExitMacro() {
                this.b();
            }
            
            @Override
            public boolean onEnterBranch(final int n) {
                stack.push(new VarDependencies(varDependencies));
                return true;
            }
            
            @Override
            public void onExitBranch() {
                stack2.peek().addAll(varDependencies);
                varDependencies.setAll(stack.pop());
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        });
        list.addAll(set2);
        for (final Map.Entry<CommandInfo, V> entry : set.entrySet()) {
            final CommandInfo commandInfo = entry.getKey();
            final String varRef = commandInfo.varRef;
            final CMakeCommand command = commandInfo.command;
            final ArrayList list2 = new ArrayList<String>((Collection<? extends String>)entry.getValue());
            Collections.sort((List<E>)list2, (Comparator<? super E>)String.CASE_INSENSITIVE_ORDER);
            final String string = StringUtil.join((Collection)list2, ", ") + " <- " + varRef;
            int n = 0;
            Label_0306: {
                try {
                    if (list2.size() > 1) {
                        n = 0;
                        break Label_0306;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                n = 2;
            }
            list.add(new CMakeNewFileTarget(command, (List<String>)list2, string, n));
        }
    }
    
    private static void a(@NotNull final CMakeCommand cMakeCommand, @NotNull final VarDependencies varDependencies, @NotNull final CommandDependencyHandler commandDependencyHandler) {
        try {
            if (cMakeCommand == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cmd", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler", "processCommandDependencies"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (varDependencies == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "varDependencies", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler", "processCommandDependencies"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (commandDependencyHandler == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "handler", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler", "processCommandDependencies"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final List<CMakeArgument> cMakeArgumentList = cMakeCommand.getCMakeArgumentList();
        if (cMakeArgumentList != null) {
            for (int i = 1; i < cMakeArgumentList.size(); ++i) {
                final Iterator<CommandInfo> iterator = varDependencies.get(cMakeArgumentList.get(i).getValue()).iterator();
                while (iterator.hasNext()) {
                    commandDependencyHandler.handleDependency(iterator.next());
                }
            }
        }
    }
    
    @Nullable
    private static String a(@NotNull final CMakeCommand cMakeCommand, @NotNull final CMakeScope cMakeScope) {
        try {
            if (cMakeCommand == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cmd", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler", "getEffectiveFirstArg"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (cMakeScope == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "scope", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler", "getEffectiveFirstArg"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final List<CMakeArgument> cMakeArgumentList = cMakeCommand.getCMakeArgumentList();
        final CMakeArgument cMakeArgument = (CMakeArgument)ContainerUtil.getFirstItem((List)cMakeArgumentList);
        if (cMakeArgument != null) {
            final ArrayList<String> list = new ArrayList<String>();
            CMakeInterpreter.expandArguments(Collections.singletonList(cMakeArgument), cMakeScope, list);
            String s = null;
            if (!list.isEmpty()) {
                final ArrayList<String> list2 = new ArrayList<String>();
                CMakeInterpreter.expandArguments(cMakeArgumentList, cMakeScope, list2);
                s = (String)ContainerUtil.getFirstItem((List)list2);
            }
            try {
                if (s == null) {
                    return cMakeArgument.getValue();
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            return s;
        }
        return null;
    }
    
    @NotNull
    @Override
    public List<CMakeNewFileTarget> getTargets() {
        List<CMakeNewFileTarget> myTargets;
        try {
            myTargets = this.myTargets;
            if (myTargets == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler", "getTargets"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myTargets;
    }
    
    @Nullable
    @Override
    public JComponent createPreviewComponent() {
        try {
            if (this.myTargets.isEmpty()) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final CMakeNewFileTarget target = this.myTargets.get(0);
        final MyEditorTextField myEditorTextField = new MyEditorTextField(target.myPointer.getProject());
        myEditorTextField.setTarget(target);
        return (JComponent)myEditorTextField;
    }
    
    @Override
    public void updatePreviewComponent(@NotNull final JComponent component, @Nullable final CMakeNewFileTarget target) {
        try {
            if (component == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "previewComponent", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler", "updatePreviewComponent"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        boolean visible = false;
        Label_0058: {
            try {
                if (target != null) {
                    visible = true;
                    break Label_0058;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            visible = false;
        }
        component.setVisible(visible);
        if (target != null) {
            ((MyEditorTextField)component).setTarget(target);
        }
    }
    
    @Override
    public void handleNewFiles(@NotNull final List<Integer> list, @NotNull final List<VirtualFile> list2) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "selectedIndices", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler", "handleNewFiles"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (list2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "addedFiles", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler", "handleNewFiles"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        boolean b = false;
        final Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            b |= this.myTargets.get(iterator.next()).addFiles(list2);
        }
        try {
            if (b) {
                this.myWorkspace.scheduleReload(false);
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
    }
    
    @NotNull
    @Override
    public List<CMakeNewFileTarget> bestTargets(@Nullable final PsiFile psiFile, @Nullable final String s) {
        final List<CMakeNewFileTarget> myTargets = this.myTargets;
        Label_0172: {
            if (psiFile != null) {
                final ArrayList<CMakeNewFileTarget> list = new ArrayList<CMakeNewFileTarget>();
                final Iterator iterator = b(CMakeWorkspace.getInstance(psiFile.getProject()).getConfigurationsForFile(OCInclusionContextUtil.getVirtualFile(psiFile))).iterator();
                while (iterator.hasNext()) {
                    final CMakeNewFileTarget a = a(myTargets, iterator.next());
                    try {
                        if (a == null) {
                            continue;
                        }
                        list.add(a);
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                }
                ArrayList<CMakeNewFileTarget> list3 = null;
                Label_0137: {
                    try {
                        if (list.isEmpty()) {
                            break Label_0172;
                        }
                        final ArrayList<CMakeNewFileTarget> list2 = list;
                        a(list2);
                        list3 = list;
                        if (list3 == null) {
                            break Label_0137;
                        }
                        return list3;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    try {
                        final ArrayList<CMakeNewFileTarget> list2 = list;
                        a(list2);
                        list3 = list;
                        if (list3 == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler", "bestTargets"));
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                }
                return list3;
            }
        }
        Pair<CMakeNewFileTarget, String> pair = null;
        if (s != null) {
            List<CMakeNewFileTarget> bestTargetsByCurrentDirFiles = this.bestTargetsByCurrentDirFiles(myTargets, s);
            if (bestTargetsByCurrentDirFiles.isEmpty()) {
                bestTargetsByCurrentDirFiles = myTargets;
            }
            for (final Pair pair2 : ContainerUtil.map((Collection)bestTargetsByCurrentDirFiles, cMakeNewFileTarget -> Pair.create((Object)cMakeNewFileTarget, (Object)cMakeNewFileTarget.calcRelativePath(s)))) {
                if (b((Pair<CMakeNewFileTarget, String>)pair2, pair)) {
                    pair = (Pair<CMakeNewFileTarget, String>)pair2;
                }
            }
        }
        Object o = null;
        Label_0284: {
            try {
                if (pair == null) {
                    final Object o2;
                    o = (o2 = Collections.emptyList());
                    break Label_0284;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            Object o2;
            o = (o2 = Collections.singletonList(pair.first));
            try {
                if (o2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler", "bestTargets"));
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        return (List<CMakeNewFileTarget>)o;
    }
    
    @NotNull
    public List<CMakeNewFileTarget> bestTargetsByCurrentDirFiles(@NotNull final List<CMakeNewFileTarget> list, @NotNull final String s) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fileTargets", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler", "bestTargetsByCurrentDirFiles"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "currentPath", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler", "bestTargetsByCurrentDirFiles"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final TObjectIntHashMap tObjectIntHashMap = new TObjectIntHashMap(ContainerUtil.identityStrategy());
        final VirtualFile refreshAndFindFileByIoFile = LocalFileSystem.getInstance().refreshAndFindFileByIoFile(new File(s));
        Label_0243: {
            try {
                if (refreshAndFindFileByIoFile == null || !refreshAndFindFileByIoFile.isDirectory()) {
                    break Label_0243;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            for (final VirtualFile virtualFile : refreshAndFindFileByIoFile.getChildren()) {
                if (!virtualFile.isDirectory()) {
                    for (final CMakeTarget cMakeTarget : b(this.myWorkspace.getConfigurationsForFile(virtualFile))) {
                        tObjectIntHashMap.put((Object)cMakeTarget, tObjectIntHashMap.get((Object)cMakeTarget) + 1);
                    }
                }
            }
        }
        final ArrayList<Pair> list2 = (ArrayList<Pair>)new ArrayList<Object>();
        Label_0324: {
            List<CMakeNewFileTarget> list3 = null;
            Label_0289: {
                try {
                    tObjectIntHashMap.forEachEntry((TObjectIntProcedure)new TObjectIntProcedure<CMakeTarget>() {
                        public boolean execute(final CMakeTarget cMakeTarget, final int n) {
                            list2.add(Pair.create((Object)cMakeTarget, (Object)n));
                            return true;
                        }
                    });
                    if (!list2.isEmpty()) {
                        break Label_0324;
                    }
                    list3 = Collections.emptyList();
                    if (list3 == null) {
                        break Label_0289;
                    }
                    return list3;
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
                try {
                    list3 = Collections.emptyList();
                    if (list3 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler", "bestTargetsByCurrentDirFiles"));
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
            }
            return list3;
        }
        Collections.sort((List<Object>)list2, (pair, pair2) -> Comparing.compare((Comparable)pair2.second, (Comparable)pair.second));
        final ArrayList<CMakeNewFileTarget> list4 = new ArrayList<CMakeNewFileTarget>();
        final int intValue = (int)list2.get(0).second;
        for (final Pair pair3 : list2) {
            try {
                if ((int)pair3.second < intValue) {
                    break;
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
            final CMakeNewFileTarget a = a(list, (CMakeTarget)pair3.first);
            try {
                if (a == null) {
                    continue;
                }
                list4.add(a);
            }
            catch (IllegalArgumentException ex7) {
                throw a(ex7);
            }
        }
        ArrayList<CMakeNewFileTarget> list5;
        try {
            a(list4);
            list5 = list4;
            if (list5 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler", "bestTargetsByCurrentDirFiles"));
            }
        }
        catch (IllegalArgumentException ex8) {
            throw a(ex8);
        }
        return list5;
    }
    
    @Nullable
    private static CMakeNewFileTarget a(@NotNull final List<CMakeNewFileTarget> list, @NotNull final CMakeTarget cMakeTarget) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "targets", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler", "bestTargetByCMakeTarget"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (cMakeTarget == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cMakeTarget", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler", "bestTargetByCMakeTarget"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final String name = cMakeTarget.getName();
        CMakeNewFileTarget cMakeNewFileTarget = null;
        for (final CMakeNewFileTarget cMakeNewFileTarget2 : list) {
            final List<String> targetNames = cMakeNewFileTarget2.getTargetNames();
            Label_0185: {
                Label_0168: {
                    try {
                        if (targetNames.size() != 1) {
                            continue;
                        }
                        final List<String> list2 = targetNames;
                        final int n = 0;
                        final String s = list2.get(n);
                        final String s2 = s;
                        final String s3 = name;
                        final boolean b = s2.equals(s3);
                        if (b) {
                            break Label_0168;
                        }
                        continue;
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                    try {
                        final List<String> list2 = targetNames;
                        final int n = 0;
                        final String s = list2.get(n);
                        final String s2 = s;
                        final String s3 = name;
                        final boolean b = s2.equals(s3);
                        if (!b) {
                            continue;
                        }
                        if (cMakeNewFileTarget != null) {
                            break Label_0185;
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                }
                cMakeNewFileTarget = cMakeNewFileTarget2;
                continue;
            }
            final int compare = CMakeNewFileTarget.selectionOrder().compare(cMakeNewFileTarget2, cMakeNewFileTarget);
            Label_0215: {
                try {
                    if (compare > 0) {
                        break Label_0215;
                    }
                    final int n2 = compare;
                    if (n2 == 0) {
                        break Label_0215;
                    }
                    continue;
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
                try {
                    final int n2 = compare;
                    if (n2 != 0) {
                        continue;
                    }
                    if (CMakeNewFileTarget.fileOrder().compare(cMakeNewFileTarget2, cMakeNewFileTarget) <= 0) {
                        continue;
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
            }
            cMakeNewFileTarget = cMakeNewFileTarget2;
        }
        return cMakeNewFileTarget;
    }
    
    @NotNull
    private static THashSet<CMakeTarget> b(@NotNull final List<? extends OCResolveConfiguration> list) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "preferredConfigurations", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler", "collectUniqueTargets"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final THashSet identityTroveSet = ContainerUtil.newIdentityTroveSet();
        for (final OCResolveConfiguration ocResolveConfiguration : list) {
            try {
                if (!(ocResolveConfiguration instanceof CMakeResolveConfiguration)) {
                    continue;
                }
                identityTroveSet.add((Object)((CMakeResolveConfiguration)ocResolveConfiguration).getTarget());
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        THashSet set;
        try {
            set = identityTroveSet;
            if (set == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler", "collectUniqueTargets"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return (THashSet<CMakeTarget>)set;
    }
    
    private static boolean b(@NotNull final Pair<CMakeNewFileTarget, String> p0, @Nullable final Pair<CMakeNewFileTarget, String> p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "candidate"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isBetterTarget"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: ifnonnull       54
        //    48: iconst_1       
        //    49: ireturn        
        //    50: invokestatic    com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    53: athrow         
        //    54: aload_0        
        //    55: getfield        com/intellij/openapi/util/Pair.second:Ljava/lang/Object;
        //    58: checkcast       Ljava/lang/String;
        //    61: astore_2       
        //    62: aload_1        
        //    63: getfield        com/intellij/openapi/util/Pair.second:Ljava/lang/Object;
        //    66: checkcast       Ljava/lang/String;
        //    69: astore_3       
        //    70: aload_2        
        //    71: aload_3        
        //    72: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/String;Ljava/lang/String;)Z
        //    75: ifeq            205
        //    78: aload_0        
        //    79: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //    82: checkcast       Lcom/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$CMakeNewFileTarget;
        //    85: astore          4
        //    87: aload_1        
        //    88: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //    91: checkcast       Lcom/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$CMakeNewFileTarget;
        //    94: astore          5
        //    96: invokestatic    com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$CMakeNewFileTarget.selectionOrder:()Ljava/util/Comparator;
        //    99: aload           4
        //   101: aload           5
        //   103: invokeinterface java/util/Comparator.compare:(Ljava/lang/Object;Ljava/lang/Object;)I
        //   108: istore          6
        //   110: iload           6
        //   112: ifeq            137
        //   115: iload           6
        //   117: ifle            135
        //   120: goto            127
        //   123: invokestatic    com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   126: athrow         
        //   127: iconst_1       
        //   128: goto            136
        //   131: invokestatic    com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   134: athrow         
        //   135: iconst_0       
        //   136: ireturn        
        //   137: invokestatic    com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$CMakeNewFileTarget.nameOrder:()Ljava/util/Comparator;
        //   140: aload           4
        //   142: aload           5
        //   144: invokeinterface java/util/Comparator.compare:(Ljava/lang/Object;Ljava/lang/Object;)I
        //   149: istore          7
        //   151: iload           7
        //   153: ifeq            180
        //   156: iload           7
        //   158: ifge            176
        //   161: goto            168
        //   164: invokestatic    com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   167: athrow         
        //   168: iconst_1       
        //   169: goto            204
        //   172: invokestatic    com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   175: athrow         
        //   176: iconst_0       
        //   177: goto            204
        //   180: invokestatic    com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$CMakeNewFileTarget.fileOrder:()Ljava/util/Comparator;
        //   183: aload           4
        //   185: aload           5
        //   187: invokeinterface java/util/Comparator.compare:(Ljava/lang/Object;Ljava/lang/Object;)I
        //   192: ifle            203
        //   195: iconst_1       
        //   196: goto            204
        //   199: invokestatic    com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   202: athrow         
        //   203: iconst_0       
        //   204: ireturn        
        //   205: aload_2        
        //   206: invokestatic    com/intellij/openapi/util/io/FileUtil.isAbsolute:(Ljava/lang/String;)Z
        //   209: istore          4
        //   211: aload_3        
        //   212: invokestatic    com/intellij/openapi/util/io/FileUtil.isAbsolute:(Ljava/lang/String;)Z
        //   215: istore          5
        //   217: iload           4
        //   219: iload           5
        //   221: if_icmpeq       246
        //   224: iload           4
        //   226: ifne            244
        //   229: goto            236
        //   232: invokestatic    com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   235: athrow         
        //   236: iconst_1       
        //   237: goto            245
        //   240: invokestatic    com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   243: athrow         
        //   244: iconst_0       
        //   245: ireturn        
        //   246: aload_2        
        //   247: ldc             ".."
        //   249: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   252: istore          6
        //   254: aload_3        
        //   255: ldc             ".."
        //   257: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   260: istore          7
        //   262: iload           6
        //   264: iload           7
        //   266: if_icmpeq       291
        //   269: iload           6
        //   271: ifne            289
        //   274: goto            281
        //   277: invokestatic    com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   280: athrow         
        //   281: iconst_1       
        //   282: goto            290
        //   285: invokestatic    com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   288: athrow         
        //   289: iconst_0       
        //   290: ireturn        
        //   291: aload_2        
        //   292: invokevirtual   java/lang/String.isEmpty:()Z
        //   295: istore          8
        //   297: aload_3        
        //   298: invokevirtual   java/lang/String.isEmpty:()Z
        //   301: istore          9
        //   303: iload           8
        //   305: ifne            320
        //   308: iload           9
        //   310: ifeq            342
        //   313: goto            320
        //   316: invokestatic    com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   319: athrow         
        //   320: iload           9
        //   322: ifne            340
        //   325: goto            332
        //   328: invokestatic    com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   331: athrow         
        //   332: iconst_1       
        //   333: goto            341
        //   336: invokestatic    com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   339: athrow         
        //   340: iconst_0       
        //   341: ireturn        
        //   342: aload_2        
        //   343: ldc             "/"
        //   345: invokevirtual   java/lang/String.split:(Ljava/lang/String;)[Ljava/lang/String;
        //   348: arraylength    
        //   349: istore          10
        //   351: aload_3        
        //   352: ldc             "/"
        //   354: invokevirtual   java/lang/String.split:(Ljava/lang/String;)[Ljava/lang/String;
        //   357: arraylength    
        //   358: istore          11
        //   360: iload           10
        //   362: iload           11
        //   364: if_icmpge       375
        //   367: iconst_1       
        //   368: goto            376
        //   371: invokestatic    com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   374: athrow         
        //   375: iconst_0       
        //   376: ireturn        
        //    Signature:
        //  (Lcom/intellij/openapi/util/Pair<Lcom/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$CMakeNewFileTarget;Ljava/lang/String;>;Lcom/intellij/openapi/util/Pair<Lcom/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$CMakeNewFileTarget;Ljava/lang/String;>;)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     50     50     54     Ljava/lang/IllegalArgumentException;
        //  110    120    123    127    Ljava/lang/IllegalArgumentException;
        //  115    131    131    135    Ljava/lang/IllegalArgumentException;
        //  151    161    164    168    Ljava/lang/IllegalArgumentException;
        //  156    172    172    176    Ljava/lang/IllegalArgumentException;
        //  180    199    199    203    Ljava/lang/IllegalArgumentException;
        //  217    229    232    236    Ljava/lang/IllegalArgumentException;
        //  224    240    240    244    Ljava/lang/IllegalArgumentException;
        //  262    274    277    281    Ljava/lang/IllegalArgumentException;
        //  269    285    285    289    Ljava/lang/IllegalArgumentException;
        //  303    313    316    320    Ljava/lang/IllegalArgumentException;
        //  308    325    328    332    Ljava/lang/IllegalArgumentException;
        //  320    336    336    340    Ljava/lang/IllegalArgumentException;
        //  360    371    371    375    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0320:
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
    
    private static void a(@NotNull final List<CMakeNewFileTarget> list) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "targets", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler", "sortTargets"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Collections.sort((List<Object>)list, (Comparator<? super Object>)CMakeNewFileTarget.visualOrder());
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    private static class VarDependencies
    {
        final MultiMap<String, CommandInfo> myMap;
        
        public VarDependencies() {
            this.myMap = (MultiMap<String, CommandInfo>)MultiMap.createSet();
        }
        
        public VarDependencies(@NotNull final VarDependencies varDependencies) {
            if (varDependencies == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "original", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$VarDependencies", "<init>"));
            }
            this();
            this.myMap.putAllValues((MultiMap)varDependencies.myMap);
        }
        
        @NotNull
        public Collection<CommandInfo> get(@NotNull final String s) {
            try {
                if (s == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "varRef", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$VarDependencies", "get"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            Collection value;
            try {
                value = this.myMap.get((Object)s);
                if (value == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$VarDependencies", "get"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            return (Collection<CommandInfo>)value;
        }
        
        public void set(@NotNull final String s, @NotNull final Collection<CommandInfo> collection) {
            try {
                if (s == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "varRef", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$VarDependencies", "set"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (collection == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "deps", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$VarDependencies", "set"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            this.myMap.remove((Object)s);
            this.myMap.put((Object)s, (Collection)collection);
        }
        
        public void setAll(@NotNull final VarDependencies varDependencies) {
            try {
                if (varDependencies == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "original", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$VarDependencies", "setAll"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            this.myMap.clear();
            this.addAll(varDependencies);
        }
        
        public void addAll(@NotNull final VarDependencies varDependencies) {
            try {
                if (varDependencies == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "original", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$VarDependencies", "addAll"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            this.myMap.putAllValues((MultiMap)varDependencies.myMap);
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    private static class CommandInfo
    {
        @NotNull
        final String varRef;
        @NotNull
        final CMakeCommand command;
        
        private CommandInfo(@NotNull final String varRef, @NotNull final CMakeCommand command) {
            if (varRef == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$CommandInfo", "<init>"));
            }
            if (command == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "command", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$CommandInfo", "<init>"));
            }
            this.varRef = varRef;
            this.command = command;
        }
        
        @Override
        public boolean equals(final Object o) {
            try {
                if (this == o) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            Label_0039: {
                try {
                    if (o == null) {
                        return false;
                    }
                    final CommandInfo commandInfo = this;
                    final Class<? extends CommandInfo> clazz = commandInfo.getClass();
                    final Object o2 = o;
                    final Class<?> clazz2 = o2.getClass();
                    if (clazz != clazz2) {
                        return false;
                    }
                    break Label_0039;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final CommandInfo commandInfo = this;
                    final Class<? extends CommandInfo> clazz = commandInfo.getClass();
                    final Object o2 = o;
                    final Class<?> clazz2 = o2.getClass();
                    if (clazz != clazz2) {
                        return false;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            final CommandInfo commandInfo2 = (CommandInfo)o;
            try {
                if (!this.varRef.equals(commandInfo2.varRef)) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            try {
                if (!this.command.equals(commandInfo2.command)) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
            return true;
        }
        
        @Override
        public int hashCode() {
            return 31 * this.varRef.hashCode() + this.command.hashCode();
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    public static class CMakeNewFileTarget implements NewFileTarget
    {
        @NotNull
        private final SmartPsiElementPointer<CMakeCommand> myPointer;
        @NotNull
        private final List<String> myTargetNames;
        @NotNull
        private final String myName;
        @NotNull
        private final String myAdditionalInfo;
        private final int mySelectionPriority;
        private static final Comparator<CMakeNewFileTarget> FILE_ORDER;
        private static final Comparator<CMakeNewFileTarget> NAME_ORDER;
        private static final Comparator<CMakeNewFileTarget> SELECTION_ORDER;
        private static final Comparator<CMakeNewFileTarget> VISUAL_ORDER;
        
        public CMakeNewFileTarget(@NotNull final CMakeCommand cMakeCommand, @NotNull final List<String> list, @NotNull final String myName, final int mySelectionPriority) {
            if (cMakeCommand == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "command", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$CMakeNewFileTarget", "<init>"));
            }
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "targetNames", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$CMakeNewFileTarget", "<init>"));
            }
            if (myName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$CMakeNewFileTarget", "<init>"));
            }
            this.mySelectionPriority = mySelectionPriority;
            this.myTargetNames = new ArrayList<String>(list);
            this.myName = myName;
            this.myPointer = (SmartPsiElementPointer<CMakeCommand>)SmartPointerManager.getInstance(cMakeCommand.getProject()).createSmartPsiElementPointer((PsiElement)cMakeCommand);
            this.myAdditionalInfo = CMakeFileLocationUtil.getLocationInFile((PsiElement)cMakeCommand, true);
        }
        
        @Override
        public boolean addFiles(@NotNull final List<VirtualFile> list) {
            try {
                if (list == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "addedFiles", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$CMakeNewFileTarget", "addFiles"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            this.myPointer.getVirtualFile().refresh(false, false);
            final CMakeCommand b = this.b();
            try {
                if (b == null) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            boolean b2 = false;
            final Iterator<VirtualFile> iterator = list.iterator();
            while (iterator.hasNext()) {
                b.appendArgument(this.calcRelativePath(iterator.next().getPath()));
                b2 = true;
            }
            return b2;
        }
        
        @NotNull
        public String calcRelativePath(@NotNull final String s) {
            try {
                if (s == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "filePath", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$CMakeNewFileTarget", "calcRelativePath"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            final PsiFile containingFile = this.myPointer.getContainingFile();
            String path = null;
            Label_0078: {
                try {
                    if (containingFile == null) {
                        path = null;
                        break Label_0078;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                path = containingFile.getVirtualFile().getParent().getPath();
            }
            final String s2 = path;
            String relativePath = null;
            Label_0110: {
                try {
                    if (s2 == null) {
                        relativePath = null;
                        break Label_0110;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                relativePath = FileUtil.getRelativePath(new File(s2), new File(s));
            }
            final String s3 = relativePath;
            String canonicalPath = null;
            Label_0127: {
                try {
                    if (s3 == null) {
                        final String s4 = s;
                        break Label_0127;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
                final String s4 = s3;
                try {
                    canonicalPath = FileUtil.toCanonicalPath(s4);
                    if (canonicalPath == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$CMakeNewFileTarget", "calcRelativePath"));
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
            }
            return canonicalPath;
        }
        
        public int getSelectionPriority() {
            return this.mySelectionPriority;
        }
        
        @NotNull
        public List<String> getTargetNames() {
            List<String> myTargetNames;
            try {
                myTargetNames = this.myTargetNames;
                if (myTargetNames == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$CMakeNewFileTarget", "getTargetNames"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return myTargetNames;
        }
        
        @NotNull
        public String getName() {
            String myName;
            try {
                myName = this.myName;
                if (myName == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$CMakeNewFileTarget", "getName"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return myName;
        }
        
        @NotNull
        @Override
        public String getAdditionalInfo() {
            String myAdditionalInfo;
            try {
                myAdditionalInfo = this.myAdditionalInfo;
                if (myAdditionalInfo == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$CMakeNewFileTarget", "getAdditionalInfo"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return myAdditionalInfo;
        }
        
        @Nullable
        private CMakeCommand b() {
            final CMakeCommand cMakeCommand = (CMakeCommand)this.myPointer.getElement();
            Label_0033: {
                try {
                    if (cMakeCommand == null) {
                        return null;
                    }
                    final CMakeCommand cMakeCommand2 = cMakeCommand;
                    final boolean b = cMakeCommand2.isValid();
                    if (b) {
                        break Label_0033;
                    }
                    return null;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final CMakeCommand cMakeCommand2 = cMakeCommand;
                    final boolean b = cMakeCommand2.isValid();
                    if (b) {
                        return cMakeCommand;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return null;
        }
        
        @Override
        public String toString() {
            return this.getName();
        }
        
        @NotNull
        public static Comparator<CMakeNewFileTarget> visualOrder() {
            Comparator<CMakeNewFileTarget> visual_ORDER;
            try {
                visual_ORDER = CMakeNewFileTarget.VISUAL_ORDER;
                if (visual_ORDER == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$CMakeNewFileTarget", "visualOrder"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return visual_ORDER;
        }
        
        @NotNull
        public static Comparator<CMakeNewFileTarget> nameOrder() {
            Comparator<CMakeNewFileTarget> name_ORDER;
            try {
                name_ORDER = CMakeNewFileTarget.NAME_ORDER;
                if (name_ORDER == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$CMakeNewFileTarget", "nameOrder"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return name_ORDER;
        }
        
        @NotNull
        public static Comparator<CMakeNewFileTarget> selectionOrder() {
            Comparator<CMakeNewFileTarget> selection_ORDER;
            try {
                selection_ORDER = CMakeNewFileTarget.SELECTION_ORDER;
                if (selection_ORDER == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$CMakeNewFileTarget", "selectionOrder"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return selection_ORDER;
        }
        
        @NotNull
        public static Comparator<CMakeNewFileTarget> fileOrder() {
            Comparator<CMakeNewFileTarget> file_ORDER;
            try {
                file_ORDER = CMakeNewFileTarget.FILE_ORDER;
                if (file_ORDER == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$CMakeNewFileTarget", "fileOrder"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return file_ORDER;
        }
        
        @NotNull
        private String a() {
            String s = null;
            Label_0037: {
                try {
                    if (this.myTargetNames.size() == 1) {
                        final String myName;
                        s = (myName = this.myTargetNames.get(0));
                        break Label_0037;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                String myName;
                s = (myName = this.myName);
                try {
                    if (myName == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$CMakeNewFileTarget", "getSingleTargetNameOrFullName"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return s;
        }
        
        @Override
        public boolean equals(final Object o) {
            try {
                if (this == o) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (o == null) {
                    return false;
                }
                final CMakeNewFileTarget cMakeNewFileTarget = this;
                final Class<? extends CMakeNewFileTarget> clazz = cMakeNewFileTarget.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
                return this.myPointer.equals(((CMakeNewFileTarget)o).myPointer);
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final CMakeNewFileTarget cMakeNewFileTarget = this;
                final Class<? extends CMakeNewFileTarget> clazz = cMakeNewFileTarget.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            return this.myPointer.equals(((CMakeNewFileTarget)o).myPointer);
        }
        
        @Override
        public int hashCode() {
            return this.myPointer.hashCode();
        }
        
        static {
            final CMakeCommand cMakeCommand;
            final Object o;
            final int n;
            final CMakeCommand cMakeCommand2;
            FILE_ORDER = ((cMakeNewFileTarget, cMakeNewFileTarget5) -> {
                cMakeNewFileTarget.b();
                cMakeNewFileTarget5.b();
                Label_0035_4: {
                    Label_0025_4: {
                        try {
                            if (cMakeCommand == null) {
                                if (o == null) {
                                    break Label_0025_4;
                                }
                                else {
                                    return 1;
                                }
                            }
                            else {
                                break Label_0035_4;
                            }
                        }
                        catch (IllegalArgumentException ex) {
                            throw a(ex);
                        }
                        try {
                            if (o == null) {
                                return n;
                            }
                        }
                        catch (IllegalArgumentException ex2) {
                            throw a(ex2);
                        }
                    }
                    return n;
                    try {
                        if (cMakeCommand2 == null) {
                            return -1;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                }
                return Comparing.compare(cMakeCommand.getTextOffset(), cMakeCommand2.getTextOffset());
            });
            NAME_ORDER = ((cMakeNewFileTarget2, cMakeNewFileTarget6) -> String.CASE_INSENSITIVE_ORDER.compare(cMakeNewFileTarget2.a(), cMakeNewFileTarget6.a()));
            SELECTION_ORDER = ((cMakeNewFileTarget3, cMakeNewFileTarget7) -> Comparing.compare(cMakeNewFileTarget3.getSelectionPriority(), cMakeNewFileTarget7.getSelectionPriority()));
            final int n2;
            final int n3;
            VISUAL_ORDER = ((cMakeNewFileTarget4, cMakeNewFileTarget8) -> {
                CMakeNewFileTarget.NAME_ORDER.compare(cMakeNewFileTarget4, cMakeNewFileTarget8);
                try {
                    if (n2 != 0) {
                        return n3;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
                CMakeNewFileTarget.FILE_ORDER.compare(cMakeNewFileTarget4, cMakeNewFileTarget8);
                return n3;
            });
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    private static class MyEditorTextField extends EditorTextField
    {
        private CMakeNewFileTarget myTarget;
        
        public MyEditorTextField(final Project project) {
            super(null, project, CMakeListsFileType.INSTANCE, true, false);
            this.setFont(EditorColorsManager.getInstance().getGlobalScheme().getFont(EditorFontType.PLAIN));
            this.setPreferredSize(new Dimension(500, 130));
            this.setMinimumSize(new Dimension(500, 130));
            this.setFocusable(false);
            this.setFocusTraversalPolicyProvider(false);
        }
        
        @Override
        protected EditorEx createEditor() {
            final CMakeNewFileTarget myTarget = this.myTarget;
            Logger log = null;
            boolean b = false;
            Label_0021: {
                try {
                    log = CPPLog.LOG;
                    if (myTarget != null) {
                        b = true;
                        break Label_0021;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                b = false;
            }
            log.assertTrue(b);
            this.a(myTarget);
            return a(myTarget, super.createEditor());
        }
        
        private static EditorEx a(@NotNull final CMakeNewFileTarget cMakeNewFileTarget, @NotNull final EditorEx editorEx) {
            try {
                if (cMakeNewFileTarget == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "target", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$MyEditorTextField", "configureNewEditor"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                if (editorEx == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$MyEditorTextField", "configureNewEditor"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            editorEx.setBackgroundColor(EditorFragmentComponent.getBackgroundColor((Editor)editorEx));
            editorEx.setVerticalScrollbarVisible(true);
            editorEx.setHorizontalScrollbarVisible(true);
            final IllegalArgumentException ex3;
            final IllegalArgumentException ex5;
            ApplicationManager.getApplication().invokeLater(() -> {
                try {
                    if (editorEx == null) {
                        new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$MyEditorTextField", "lambda$configureNewEditor$0"));
                        throw ex3;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw b(ex4);
                }
                try {
                    if (cMakeNewFileTarget == null) {
                        new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "target", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$MyEditorTextField", "lambda$configureNewEditor$0"));
                        throw ex5;
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw b(ex6);
                }
                try {
                    if (!editorEx.isDisposed()) {
                        a((Editor)editorEx, cMakeNewFileTarget);
                    }
                }
                catch (IllegalArgumentException ex7) {
                    throw b(ex7);
                }
                return;
            });
            return editorEx;
        }
        
        public void setTarget(@NotNull final CMakeNewFileTarget myTarget) {
            try {
                if (myTarget == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "target", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$MyEditorTextField", "setTarget"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            this.a(this.myTarget = myTarget);
            a(this.getEditor(), myTarget);
        }
        
        private static void a(@Nullable final Editor editor, @NotNull final CMakeNewFileTarget cMakeNewFileTarget) {
            try {
                if (cMakeNewFileTarget == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "target", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$MyEditorTextField", "updateWithTarget"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                if (editor == null) {
                    return;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            final CMakeCommand access$400 = cMakeNewFileTarget.b();
            try {
                if (access$400 == null) {
                    return;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            editor.getCaretModel().moveToOffset(access$400.getTextOffset());
            final ScrollingModel scrollingModel = editor.getScrollingModel();
            scrollingModel.disableAnimation();
            scrollingModel.scrollToCaret(ScrollType.CENTER);
            scrollingModel.enableAnimation();
            final MarkupModel markupModel = editor.getMarkupModel();
            markupModel.removeAllHighlighters();
            final TextRange textRange = access$400.getTextRange();
            markupModel.addRangeHighlighter(textRange.getStartOffset(), textRange.getEndOffset(), 5500, editor.getColorsScheme().getAttributes(EditorColors.SEARCH_RESULT_ATTRIBUTES), HighlighterTargetArea.EXACT_RANGE);
        }
        
        private void a(@NotNull final CMakeNewFileTarget cMakeNewFileTarget) {
            try {
                if (cMakeNewFileTarget == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "target", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$MyEditorTextField", "initDocument"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            final PsiFile containingFile = cMakeNewFileTarget.myPointer.getContainingFile();
            Logger log = null;
            boolean b = false;
            Label_0070: {
                try {
                    log = CPPLog.LOG;
                    if (containingFile != null) {
                        b = true;
                        break Label_0070;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
                b = false;
            }
            log.assertTrue(b);
            final Document document = PsiDocumentManager.getInstance(containingFile.getProject()).getDocument(containingFile);
            try {
                if (document != this.getDocument()) {
                    this.setDocument(document);
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
        }
        
        private static IllegalArgumentException b(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    private interface CommandDependencyHandler
    {
        void handleDependency(@NotNull final CommandInfo p0);
    }
}
