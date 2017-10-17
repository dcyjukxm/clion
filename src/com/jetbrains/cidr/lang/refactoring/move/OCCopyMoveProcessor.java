// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move;

import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiFileSystemItem;
import org.jetbrains.annotations.Nullable;
import java.util.Collection;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.openapi.util.Pair;
import com.intellij.psi.search.GlobalSearchScope;
import java.util.Iterator;
import com.intellij.openapi.command.CommandProcessor;
import java.util.List;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.util.Comparing;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.ArrayList;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import java.util.Set;

public abstract class OCCopyMoveProcessor<M, R, G extends R, P>
{
    protected Set<PsiElement> myElements;
    protected Helper<M, R, G, P> myHelper;
    
    protected OCCopyMoveProcessor(final PsiElement[] p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokespecial   java/lang/Object.<init>:()V
        //     4: aload_0        
        //     5: aload_0        
        //     6: invokevirtual   com/jetbrains/cidr/lang/refactoring/move/OCCopyMoveProcessor.getHelper:()Lcom/jetbrains/cidr/lang/refactoring/move/OCCopyMoveProcessor$Helper;
        //     9: putfield        com/jetbrains/cidr/lang/refactoring/move/OCCopyMoveProcessor.myHelper:Lcom/jetbrains/cidr/lang/refactoring/move/OCCopyMoveProcessor$Helper;
        //    12: aload_0        
        //    13: new             Ljava/util/HashSet;
        //    16: dup            
        //    17: aload_1        
        //    18: invokestatic    java/util/Arrays.asList:([Ljava/lang/Object;)Ljava/util/List;
        //    21: invokespecial   java/util/HashSet.<init>:(Ljava/util/Collection;)V
        //    24: putfield        com/jetbrains/cidr/lang/refactoring/move/OCCopyMoveProcessor.myElements:Ljava/util/Set;
        //    27: aload_1        
        //    28: astore_2       
        //    29: aload_2        
        //    30: arraylength    
        //    31: istore_3       
        //    32: iconst_0       
        //    33: istore          4
        //    35: iload           4
        //    37: iload_3        
        //    38: if_icmpge       271
        //    41: aload_2        
        //    42: iload           4
        //    44: aaload         
        //    45: astore          5
        //    47: aload           5
        //    49: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    52: ifeq            265
        //    55: aload           5
        //    57: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    60: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getVirtualFile:()Lcom/intellij/openapi/vfs/VirtualFile;
        //    65: astore          6
        //    67: aload_0        
        //    68: getfield        com/jetbrains/cidr/lang/refactoring/move/OCCopyMoveProcessor.myHelper:Lcom/jetbrains/cidr/lang/refactoring/move/OCCopyMoveProcessor$Helper;
        //    71: aload_1        
        //    72: iconst_0       
        //    73: aaload         
        //    74: invokeinterface com/intellij/psi/PsiElement.getProject:()Lcom/intellij/openapi/project/Project;
        //    79: aload           6
        //    81: invokeinterface com/jetbrains/cidr/lang/refactoring/move/OCCopyMoveProcessor$Helper.getProjectFile:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;)Ljava/lang/Object;
        //    86: astore          7
        //    88: aload           5
        //    90: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    93: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getAssociatedFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    98: astore          8
        //   100: aload           8
        //   102: ifnull          265
        //   105: aload           8
        //   107: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getVirtualFile:()Lcom/intellij/openapi/vfs/VirtualFile;
        //   112: astore          9
        //   114: aload           7
        //   116: ifnull          226
        //   119: aload           6
        //   121: ifnull          226
        //   124: goto            131
        //   127: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCCopyMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   130: athrow         
        //   131: aload           9
        //   133: ifnull          226
        //   136: goto            143
        //   139: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCCopyMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   142: athrow         
        //   143: aload_0        
        //   144: getfield        com/jetbrains/cidr/lang/refactoring/move/OCCopyMoveProcessor.myHelper:Lcom/jetbrains/cidr/lang/refactoring/move/OCCopyMoveProcessor$Helper;
        //   147: aload           6
        //   149: aload           7
        //   151: invokeinterface com/jetbrains/cidr/lang/refactoring/move/OCCopyMoveProcessor$Helper.findReferences:(Lcom/intellij/openapi/vfs/VirtualFile;Ljava/lang/Object;)Ljava/util/Collection;
        //   156: invokestatic    com/intellij/util/containers/ContainerUtil.getFirstItem:(Ljava/util/Collection;)Ljava/lang/Object;
        //   159: astore          10
        //   161: aload_0        
        //   162: getfield        com/jetbrains/cidr/lang/refactoring/move/OCCopyMoveProcessor.myHelper:Lcom/jetbrains/cidr/lang/refactoring/move/OCCopyMoveProcessor$Helper;
        //   165: aload           9
        //   167: aload           7
        //   169: invokeinterface com/jetbrains/cidr/lang/refactoring/move/OCCopyMoveProcessor$Helper.findReferences:(Lcom/intellij/openapi/vfs/VirtualFile;Ljava/lang/Object;)Ljava/util/Collection;
        //   174: invokestatic    com/intellij/util/containers/ContainerUtil.getFirstItem:(Ljava/util/Collection;)Ljava/lang/Object;
        //   177: astore          11
        //   179: aload           10
        //   181: ifnull          226
        //   184: aload           11
        //   186: ifnull          226
        //   189: goto            196
        //   192: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCCopyMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   195: athrow         
        //   196: aload_0        
        //   197: getfield        com/jetbrains/cidr/lang/refactoring/move/OCCopyMoveProcessor.myHelper:Lcom/jetbrains/cidr/lang/refactoring/move/OCCopyMoveProcessor$Helper;
        //   200: aload           10
        //   202: aload           11
        //   204: invokeinterface com/jetbrains/cidr/lang/refactoring/move/OCCopyMoveProcessor$Helper.areFromSameParent:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //   209: ifne            226
        //   212: goto            219
        //   215: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCCopyMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   218: athrow         
        //   219: goto            265
        //   222: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCCopyMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   225: athrow         
        //   226: aload           8
        //   228: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getContainingDirectory:()Lcom/intellij/psi/PsiDirectory;
        //   233: aload           5
        //   235: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   238: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getContainingDirectory:()Lcom/intellij/psi/PsiDirectory;
        //   243: if_acmpeq       253
        //   246: goto            265
        //   249: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCCopyMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   252: athrow         
        //   253: aload_0        
        //   254: getfield        com/jetbrains/cidr/lang/refactoring/move/OCCopyMoveProcessor.myElements:Ljava/util/Set;
        //   257: aload           8
        //   259: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   264: pop            
        //   265: iinc            4, 1
        //   268: goto            35
        //   271: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  114    124    127    131    Ljava/lang/IllegalArgumentException;
        //  119    136    139    143    Ljava/lang/IllegalArgumentException;
        //  179    189    192    196    Ljava/lang/IllegalArgumentException;
        //  184    212    215    219    Ljava/lang/IllegalArgumentException;
        //  196    222    222    226    Ljava/lang/IllegalArgumentException;
        //  226    249    249    253    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0196:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createConstructor(AstBuilder.java:692)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:529)
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
    
    public void process(final Project project, final PsiElement psiElement) {
        final ArrayList<VirtualFile> list = new ArrayList<VirtualFile>();
        for (final PsiElement psiElement2 : this.myElements) {
            try {
                if (Comparing.equal((Object)psiElement2, (Object)psiElement)) {
                    return;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            Label_0143: {
                VirtualFile virtualFile = null;
                Label_0119: {
                    Label_0099: {
                        try {
                            if (this.myHelper.isGroupElement(psiElement2)) {
                                continue;
                            }
                            if (!(psiElement2 instanceof PsiFile)) {
                                break Label_0099;
                            }
                        }
                        catch (IllegalArgumentException ex2) {
                            throw a(ex2);
                        }
                        virtualFile = ((PsiFile)psiElement2).getVirtualFile();
                        break Label_0119;
                    }
                    if (!(psiElement2 instanceof PsiDirectory)) {
                        continue;
                    }
                    virtualFile = ((PsiDirectory)psiElement2).getVirtualFile();
                    try {
                        if (virtualFile == null) {
                            continue;
                        }
                        final Project project2 = project;
                        final GlobalSearchScope globalSearchScope = OCSearchScope.getProjectSourcesScope(project2);
                        final VirtualFile virtualFile2 = virtualFile;
                        final boolean b = globalSearchScope.contains(virtualFile2);
                        if (!b) {
                            break Label_0143;
                        }
                        continue;
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                }
                try {
                    final Project project2 = project;
                    final GlobalSearchScope globalSearchScope = OCSearchScope.getProjectSourcesScope(project2);
                    final VirtualFile virtualFile2 = virtualFile;
                    final boolean b = globalSearchScope.contains(virtualFile2);
                    if (b) {
                        continue;
                    }
                    list.add(virtualFile);
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
        }
        if (!list.isEmpty()) {
            PsiDirectory psiDirectory = null;
            Object first = null;
            Object o;
            if (this.myHelper.isGroupElement(psiElement)) {
                final com.intellij.openapi.util.Pair<G, P> takeFromGroupElement = this.myHelper.takeFromGroupElement(psiElement);
                first = takeFromGroupElement.getFirst();
                o = takeFromGroupElement.getSecond();
                psiDirectory = (PsiDirectory)psiElement;
            }
            else {
                if (psiElement instanceof PsiDirectory) {
                    psiDirectory = (PsiDirectory)psiElement;
                }
                else if (psiElement != null) {
                    final PsiFile containingFile = psiElement.getContainingFile();
                    PsiDirectory containingDirectory = null;
                    Label_0273: {
                        try {
                            if (containingFile != null) {
                                containingDirectory = containingFile.getContainingDirectory();
                                break Label_0273;
                            }
                        }
                        catch (IllegalArgumentException ex5) {
                            throw a(ex5);
                        }
                        containingDirectory = null;
                    }
                    psiDirectory = containingDirectory;
                }
                try {
                    if (psiDirectory == null) {
                        return;
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
                o = this.myHelper.getProjectFile(project, psiDirectory.getVirtualFile());
            }
            try {
                if (o != null) {
                    this.doActionForExternalFiles(project, (P)o, list, (G)first, psiDirectory);
                }
            }
            catch (IllegalArgumentException ex7) {
                throw a(ex7);
            }
        }
        else if (psiElement instanceof PsiDirectory) {
            final ArrayList<R> list2 = new ArrayList<R>();
            Object o2;
            Object o3;
            if (this.myHelper.isGroupElement(psiElement)) {
                final com.intellij.openapi.util.Pair<G, P> takeFromGroupElement2 = this.myHelper.takeFromGroupElement(psiElement);
                o2 = takeFromGroupElement2.getFirst();
                o3 = takeFromGroupElement2.getSecond();
            }
            else {
                VirtualFile virtualFile3 = ((PsiDirectory)psiElement).getVirtualFile();
                do {
                    o3 = this.myHelper.getProjectFile(project, virtualFile3);
                    if (o3 != null) {
                        break;
                    }
                    virtualFile3 = virtualFile3.getParent();
                } while (virtualFile3 != null);
                try {
                    if (o3 == null) {
                        return;
                    }
                }
                catch (IllegalArgumentException ex8) {
                    throw a(ex8);
                }
                o2 = this.myHelper.getTargetGroup(this.myHelper.findReferences(virtualFile3, (P)o3), null);
            }
            try {
                if (this.myHelper.badTargetGroup((G)o2)) {
                    return;
                }
            }
            catch (IllegalArgumentException ex9) {
                throw a(ex9);
            }
            final Iterator<PsiElement> iterator2 = this.myElements.iterator();
            while (iterator2.hasNext()) {
                final R sourceRef = this.getSourceRef(iterator2.next(), (G)o2);
                try {
                    if (sourceRef == null) {
                        continue;
                    }
                    list2.add(sourceRef);
                }
                catch (IllegalArgumentException ex10) {
                    throw a(ex10);
                }
            }
            boolean b2 = false;
            Label_0596: {
                try {
                    if (this.myElements.size() == list2.size()) {
                        b2 = true;
                        break Label_0596;
                    }
                }
                catch (IllegalArgumentException ex11) {
                    throw a(ex11);
                }
                b2 = false;
            }
            final boolean b3 = b2;
            try {
                if (!b3 || list2.isEmpty()) {
                    return;
                }
            }
            catch (IllegalArgumentException ex12) {
                throw a(ex12);
            }
            CommandProcessor.getInstance().executeCommand(project, () -> this.doAction((Helper<M, R, Object, Object>)this.myHelper, o3, (PsiDirectory)psiElement, o2, list2), (String)null, (Object)null);
        }
    }
    
    protected abstract Helper<M, R, G, P> getHelper();
    
    @Nullable
    R getSourceRef(final PsiElement psiElement, final G g) {
        if (!this.myHelper.isGroupElement(psiElement)) {
            Object o = null;
            VirtualFile virtualFile;
            if (psiElement instanceof PsiFile) {
                virtualFile = ((PsiFile)psiElement).getVirtualFile();
            }
            else {
                if (!(psiElement instanceof PsiDirectory)) {
                    return null;
                }
                virtualFile = ((PsiDirectory)psiElement).getVirtualFile();
                if (this.myHelper.isGroupElement(psiElement)) {
                    o = this.myHelper.takeFromGroupElement(psiElement).getSecond();
                }
            }
            try {
                if (virtualFile == null) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            if (o == null) {
                o = this.myHelper.getProjectFile(psiElement.getProject(), virtualFile);
            }
            try {
                if (o == null) {
                    final Object firstItem = null;
                    return (R)firstItem;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            final Object firstItem = ContainerUtil.getFirstItem((Collection)this.myHelper.findReferences(virtualFile, (P)o));
            return (R)firstItem;
        }
        return this.myHelper.getSourceRefFromGroupElement(psiElement, g);
    }
    
    @Nullable
    public static String checkOperationIsValid(@NotNull final PsiFileSystemItem p0, @NotNull final PsiDirectory p1, @NotNull final String p2, final boolean p3, final boolean p4) {
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
        //    18: ldc             "source"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/refactoring/move/OCCopyMoveProcessor"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "checkOperationIsValid"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCCopyMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "target"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/refactoring/move/OCCopyMoveProcessor"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "checkOperationIsValid"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCCopyMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_2        
        //    89: ifnonnull       132
        //    92: new             Ljava/lang/IllegalArgumentException;
        //    95: dup            
        //    96: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    98: ldc             3
        //   100: anewarray       Ljava/lang/Object;
        //   103: dup            
        //   104: ldc             0
        //   106: ldc             "newName"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/refactoring/move/OCCopyMoveProcessor"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "checkOperationIsValid"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCCopyMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   131: athrow         
        //   132: aload_0        
        //   133: invokeinterface com/intellij/psi/PsiFileSystemItem.getVirtualFile:()Lcom/intellij/openapi/vfs/VirtualFile;
        //   138: astore          5
        //   140: aload_1        
        //   141: invokeinterface com/intellij/psi/PsiDirectory.getVirtualFile:()Lcom/intellij/openapi/vfs/VirtualFile;
        //   146: astore          6
        //   148: aload           5
        //   150: ifnull          231
        //   153: aload           5
        //   155: aload           6
        //   157: iconst_0       
        //   158: invokestatic    com/intellij/openapi/vfs/VfsUtilCore.isAncestor:(Lcom/intellij/openapi/vfs/VirtualFile;Lcom/intellij/openapi/vfs/VirtualFile;Z)Z
        //   161: ifeq            231
        //   164: goto            171
        //   167: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCCopyMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   170: athrow         
        //   171: new             Ljava/lang/StringBuilder;
        //   174: dup            
        //   175: invokespecial   java/lang/StringBuilder.<init>:()V
        //   178: ldc             "Can't "
        //   180: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   183: iload           4
        //   185: ifeq            204
        //   188: goto            195
        //   191: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCCopyMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   194: athrow         
        //   195: ldc             "move"
        //   197: goto            206
        //   200: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCCopyMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   203: athrow         
        //   204: ldc             "copy"
        //   206: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   209: ldc             " '"
        //   211: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   214: aload           5
        //   216: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getPath:()Ljava/lang/String;
        //   219: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   222: ldc             "' to itself"
        //   224: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   227: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   230: areturn        
        //   231: iload           4
        //   233: ifeq            293
        //   236: aload           5
        //   238: ifnull          293
        //   241: goto            248
        //   244: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCCopyMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   247: athrow         
        //   248: aload           5
        //   250: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getParent:()Lcom/intellij/openapi/vfs/VirtualFile;
        //   253: aload           6
        //   255: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   258: ifeq            293
        //   261: goto            268
        //   264: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCCopyMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   267: athrow         
        //   268: aload           5
        //   270: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getName:()Ljava/lang/String;
        //   273: aload_2        
        //   274: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   277: ifeq            293
        //   280: goto            287
        //   283: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCCopyMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   286: athrow         
        //   287: aconst_null    
        //   288: areturn        
        //   289: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCCopyMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   292: athrow         
        //   293: iload_3        
        //   294: ifeq            303
        //   297: aconst_null    
        //   298: areturn        
        //   299: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCCopyMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   302: athrow         
        //   303: new             Ljava/io/File;
        //   306: dup            
        //   307: aload           6
        //   309: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getPath:()Ljava/lang/String;
        //   312: aload_2        
        //   313: invokespecial   java/io/File.<init>:(Ljava/lang/String;Ljava/lang/String;)V
        //   316: astore          7
        //   318: aload           7
        //   320: invokevirtual   java/io/File.exists:()Z
        //   323: ifeq            399
        //   326: aload           5
        //   328: ifnull          396
        //   331: goto            338
        //   334: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCCopyMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   337: athrow         
        //   338: new             Ljava/lang/StringBuilder;
        //   341: dup            
        //   342: invokespecial   java/lang/StringBuilder.<init>:()V
        //   345: aload           7
        //   347: invokevirtual   java/io/File.isDirectory:()Z
        //   350: ifeq            369
        //   353: goto            360
        //   356: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCCopyMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   359: athrow         
        //   360: ldc             "Directory"
        //   362: goto            371
        //   365: invokestatic    com/jetbrains/cidr/lang/refactoring/move/OCCopyMoveProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   368: athrow         
        //   369: ldc             "File"
        //   371: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   374: ldc             " already exists at '"
        //   376: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   379: aload           7
        //   381: invokevirtual   java/io/File.getPath:()Ljava/lang/String;
        //   384: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   387: ldc             "'"
        //   389: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   392: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   395: areturn        
        //   396: ldc             "File already exists"
        //   398: areturn        
        //   399: aconst_null    
        //   400: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     128    128    132    Ljava/lang/IllegalArgumentException;
        //  148    164    167    171    Ljava/lang/IllegalArgumentException;
        //  153    188    191    195    Ljava/lang/IllegalArgumentException;
        //  171    200    200    204    Ljava/lang/IllegalArgumentException;
        //  231    241    244    248    Ljava/lang/IllegalArgumentException;
        //  236    261    264    268    Ljava/lang/IllegalArgumentException;
        //  248    280    283    287    Ljava/lang/IllegalArgumentException;
        //  268    289    289    293    Ljava/lang/IllegalArgumentException;
        //  293    299    299    303    Ljava/lang/IllegalArgumentException;
        //  318    331    334    338    Ljava/lang/IllegalArgumentException;
        //  326    353    356    360    Ljava/lang/IllegalArgumentException;
        //  338    365    365    369    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0171:
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
    
    protected abstract void doActionForExternalFiles(final Project p0, @NotNull final P p1, @NotNull final List<VirtualFile> p2, @Nullable final G p3, @NotNull final PsiDirectory p4);
    
    protected abstract void doAction(@NotNull final Helper<M, R, G, P> p0, @NotNull final P p1, @NotNull final PsiDirectory p2, @NotNull final G p3, @NotNull final List<R> p4);
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    public interface Helper<M, R, G extends R, P>
    {
        boolean isGroupElement(final PsiElement p0);
        
        Pair<G, P> takeFromGroupElement(final PsiElement p0);
        
        @Nullable
        R getSourceRefFromGroupElement(final PsiElement p0, final G p1);
        
        M getManipulator(final P p0);
        
        void completeManipulation(@NotNull final M p0);
        
        Collection<R> findReferences(final VirtualFile p0, final P p1);
        
        boolean areFromSameParent(final R p0, final R p1);
        
        P getProjectFile(final Project p0, final VirtualFile p1);
        
        G getTargetGroup(final Collection<R> p0, final R p1);
        
        boolean badTargetGroup(final G p0);
    }
}
