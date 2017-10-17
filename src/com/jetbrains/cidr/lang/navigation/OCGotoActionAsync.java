// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.navigation;

import com.intellij.openapi.application.ex.ApplicationManagerEx;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.util.text.StringUtil;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import com.intellij.openapi.project.Project;
import javax.swing.ListCellRenderer;
import com.intellij.codeInsight.daemon.impl.PsiElementListNavigator;
import java.util.Comparator;
import com.intellij.codeInsight.navigation.GotoTargetHandler;
import java.util.Collections;
import com.intellij.psi.NavigatablePsiElement;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.Nullable;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import com.intellij.psi.PsiElement;
import com.intellij.codeInsight.navigation.ListBackgroundUpdaterTask;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Query;

public class OCGotoActionAsync extends OCGotoAction
{
    @NotNull
    private final Query<? extends OCSymbol> myTargetQuery;
    private ListBackgroundUpdaterTask updater;
    
    public OCGotoActionAsync(@NotNull final PsiElement psiElement, @NotNull final String s, @NotNull final Icon icon, @NotNull final Query<? extends OCSymbol> myTargetQuery) {
        if (psiElement == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "source", "com/jetbrains/cidr/lang/navigation/OCGotoActionAsync", "<init>"));
        }
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/navigation/OCGotoActionAsync", "<init>"));
        }
        if (icon == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "icon", "com/jetbrains/cidr/lang/navigation/OCGotoActionAsync", "<init>"));
        }
        if (myTargetQuery == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "query", "com/jetbrains/cidr/lang/navigation/OCGotoActionAsync", "<init>"));
        }
        super(psiElement, s, icon);
        this.updater = null;
        this.myTargetQuery = myTargetQuery;
    }
    
    @Override
    public void navigate(@Nullable final MouseEvent mouseEvent, final Editor editor) {
        try {
            if (this.updater != null) {
                this.updater.cancelTask();
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCSymbol ocSymbol;
        synchronized (this.myTargetQuery) {
            ocSymbol = (OCSymbol)this.myTargetQuery.findFirst();
        }
        try {
            if (ocSymbol == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final PsiElement locateDefinition = ocSymbol.locateDefinition();
        try {
            if (!(locateDefinition instanceof NavigatablePsiElement)) {
                return;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final NavigatablePsiElement[] array = { (NavigatablePsiElement)locateDefinition };
        final CellRenderer cellRenderer = new CellRenderer(new GotoTargetHandler.GotoData(this.mySource, (PsiElement[])array, Collections.emptyList()));
        try {
            this.updater = this.createGotoAsyncUpdater(this.mySource.getProject(), this.myName, this.myTargetQuery, cellRenderer.getComparator());
            if (mouseEvent != null) {
                PsiElementListNavigator.openTargets(mouseEvent, array, this.myName, this.myName, cellRenderer, this.updater);
                return;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        PsiElementListNavigator.openTargets(editor, array, this.myName, this.myName, cellRenderer, this.updater);
    }
    
    protected ListBackgroundUpdaterTask createGotoAsyncUpdater(@Nullable final Project project, @NotNull final String s, @NotNull final Query<? extends OCSymbol> query, @Nullable final Comparator comparator) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "title", "com/jetbrains/cidr/lang/navigation/OCGotoActionAsync", "createGotoAsyncUpdater"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (query == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "targetQuery", "com/jetbrains/cidr/lang/navigation/OCGotoActionAsync", "createGotoAsyncUpdater"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return new GotoAsyncUpdater(project, s, query, comparator);
    }
    
    @Nullable
    @Override
    public List<? extends OCSymbol> getTargets() {
        return new ArrayList<OCSymbol>(this.myTargetQuery.findAll());
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    protected static class GotoAsyncUpdater extends ListBackgroundUpdaterTask
    {
        @NotNull
        private final Query<? extends OCSymbol> myTargetQuery;
        @Nullable
        private final Comparator myComparator;
        
        public GotoAsyncUpdater(@Nullable final Project project, @NotNull final String s, @NotNull final Query<? extends OCSymbol> myTargetQuery, @Nullable final Comparator myComparator) {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "title", "com/jetbrains/cidr/lang/navigation/OCGotoActionAsync$GotoAsyncUpdater", "<init>"));
            }
            if (myTargetQuery == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "targetQuery", "com/jetbrains/cidr/lang/navigation/OCGotoActionAsync$GotoAsyncUpdater", "<init>"));
            }
            super(project, s);
            this.myTargetQuery = myTargetQuery;
            this.myComparator = myComparator;
        }
        
        @Override
        public String getCaption(final int n) {
            return this.getTitle() + " (" + n + " " + StringUtil.pluralize("element", n) + " found)";
        }
        
        @Override
        public void run(@NotNull final ProgressIndicator p0) {
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
            //    18: ldc             "indicator"
            //    20: aastore        
            //    21: dup            
            //    22: ldc             1
            //    24: ldc             "com/jetbrains/cidr/lang/navigation/OCGotoActionAsync$GotoAsyncUpdater"
            //    26: aastore        
            //    27: dup            
            //    28: ldc             2
            //    30: ldc             "run"
            //    32: aastore        
            //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
            //    39: athrow         
            //    40: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoActionAsync$GotoAsyncUpdater.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    43: athrow         
            //    44: aload_0        
            //    45: aload_1        
            //    46: invokespecial   com/intellij/codeInsight/navigation/ListBackgroundUpdaterTask.run:(Lcom/intellij/openapi/progress/ProgressIndicator;)V
            //    49: aload_0        
            //    50: aload_1        
            //    51: invokedynamic   run:(Lcom/jetbrains/cidr/lang/navigation/OCGotoActionAsync$GotoAsyncUpdater;Lcom/intellij/openapi/progress/ProgressIndicator;)Ljava/lang/Runnable;
            //    56: astore_2       
            //    57: new             Lcom/intellij/openapi/util/Ref;
            //    60: dup            
            //    61: getstatic       java/lang/Boolean.FALSE:Ljava/lang/Boolean;
            //    64: invokespecial   com/intellij/openapi/util/Ref.<init>:(Ljava/lang/Object;)V
            //    67: astore_3       
            //    68: aload_3        
            //    69: aload_2        
            //    70: invokedynamic   run:(Lcom/intellij/openapi/util/Ref;Ljava/lang/Runnable;)Ljava/lang/Runnable;
            //    75: aload_1        
            //    76: invokestatic    com/intellij/openapi/progress/util/ProgressIndicatorUtils.runWithWriteActionPriority:(Ljava/lang/Runnable;Lcom/intellij/openapi/progress/ProgressIndicator;)Z
            //    79: istore          4
            //    81: iload           4
            //    83: ifeq            106
            //    86: aload_3        
            //    87: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
            //    90: checkcast       Ljava/lang/Boolean;
            //    93: invokevirtual   java/lang/Boolean.booleanValue:()Z
            //    96: ifne            122
            //    99: goto            106
            //   102: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoActionAsync$GotoAsyncUpdater.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   105: athrow         
            //   106: aload_0        
            //   107: invokedynamic   run:(Lcom/jetbrains/cidr/lang/navigation/OCGotoActionAsync$GotoAsyncUpdater;)Ljava/lang/Runnable;
            //   112: invokestatic    com/intellij/util/ui/UIUtil.invokeAndWaitIfNeeded:(Ljava/lang/Runnable;)V
            //   115: goto            122
            //   118: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoActionAsync$GotoAsyncUpdater.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   121: athrow         
            //   122: return         
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  0      40     40     44     Ljava/lang/IllegalArgumentException;
            //  81     99     102    106    Ljava/lang/IllegalArgumentException;
            //  86     115    118    122    Ljava/lang/IllegalArgumentException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Could not infer any expression.
            //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:374)
            //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:96)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:344)
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
        
        @Override
        public void onFinished() {
            super.onFinished();
            final PsiElement theOnlyOneElement = this.getTheOnlyOneElement();
            try {
                if (theOnlyOneElement instanceof NavigatablePsiElement) {
                    ((NavigatablePsiElement)theOnlyOneElement).navigate(true);
                    this.myPopup.cancel();
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
        }
        
        private static IllegalArgumentException c(final IllegalArgumentException ex) {
            return ex;
        }
    }
}
