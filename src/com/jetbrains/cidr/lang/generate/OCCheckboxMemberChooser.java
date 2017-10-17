// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate;

import com.intellij.codeInsight.generation.MemberChooserObject;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import com.intellij.openapi.util.Ref;
import com.intellij.codeInsight.generation.ClassMember;
import java.util.Iterator;
import java.util.Comparator;
import java.util.Collections;
import com.intellij.ui.SimpleColoredComponent;
import com.intellij.ide.util.MemberChooser;
import javax.swing.JTree;
import java.util.Collection;
import org.jetbrains.annotations.NotNull;
import javax.swing.Action;
import com.intellij.ui.CheckboxTree;
import com.intellij.ui.CheckedTreeNode;
import com.intellij.ui.treeStructure.Tree;
import java.util.ArrayList;
import javax.swing.JComponent;
import com.jetbrains.cidr.lang.generate.handlers.OCDeclareMembersHandler;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.settings.OCOption;
import com.intellij.openapi.util.Pair;
import java.util.List;

public class OCCheckboxMemberChooser extends OCMemberChooser
{
    private List<OCMemberChooserObject> myChosenElements;
    
    public OCCheckboxMemberChooser(final OCMemberChooserObject[] array, final List<Pair<OCOption, Object>> list, final Project project, final boolean b, final boolean b2, final OCDeclareMembersHandler.MemberChooserHeaderPanel memberChooserHeaderPanel) {
        super(array, b, b2, list, memberChooserHeaderPanel, project);
        this.myChosenElements = new ArrayList<OCMemberChooserObject>();
    }
    
    @Override
    protected Tree createTree() {
        return (Tree)new CheckboxTree(this.getTreeCellRenderer(), new CheckedTreeNode((Object)null));
    }
    
    @NotNull
    @Override
    protected Action[] createActions() {
        Action[] array;
        try {
            array = new Action[] { this.getOKAction(), this.getCancelAction() };
            if (array == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/OCCheckboxMemberChooser", "createActions"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return array;
    }
    
    @Override
    public List<OCMemberChooserObject> getChosenElements() {
        return new ArrayList<OCMemberChooserObject>(this.myChosenElements);
    }
    
    protected CheckboxTree.CheckboxTreeCellRenderer getTreeCellRenderer() {
        return new CheckboxTree.CheckboxTreeCellRenderer() {
            public void customizeRenderer(final JTree tree, final Object o, final boolean b, final boolean b2, final boolean b3, final int n, final boolean b4) {
                if (o instanceof ElementNode) {
                    final boolean memberEnabled = OCCheckboxMemberChooser.this.isMemberEnabled((OCMemberChooserObject)((ElementNode)o).getDelegate());
                    if (o instanceof MyMemberNode) {
                        this.myCheckbox.setEnabled(memberEnabled);
                    }
                    this.getTextRenderer().setEnabled(memberEnabled);
                    ((ElementNode)o).getDelegate().renderTreeNode((SimpleColoredComponent)this.getTextRenderer(), tree);
                }
            }
        };
    }
    
    protected boolean isMemberEnabled(final OCMemberChooserObject ocMemberChooserObject) {
        return true;
    }
    
    public void refreshChosenMembers() {
        this.myChosenElements.clear();
        final ArrayList<Object> list = new ArrayList<Object>(this.myNodeToParentMap.keySet());
        Collections.sort(list, (Comparator<? super Object>)new OrderComparator());
        for (final MyMemberNode myMemberNode : list) {
            Label_0094: {
                try {
                    if (!myMemberNode.isChecked()) {
                        continue;
                    }
                    final OCCheckboxMemberChooser ocCheckboxMemberChooser = this;
                    final MyMemberNode myMemberNode2 = myMemberNode;
                    final OCMemberChooserObject ocMemberChooserObject = myMemberNode2.getDelegate();
                    final boolean b = ocCheckboxMemberChooser.isMemberEnabled(ocMemberChooserObject);
                    if (b) {
                        break Label_0094;
                    }
                    continue;
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                try {
                    final OCCheckboxMemberChooser ocCheckboxMemberChooser = this;
                    final MyMemberNode myMemberNode2 = myMemberNode;
                    final OCMemberChooserObject ocMemberChooserObject = myMemberNode2.getDelegate();
                    final boolean b = ocCheckboxMemberChooser.isMemberEnabled(ocMemberChooserObject);
                    if (!b) {
                        continue;
                    }
                    this.myChosenElements.add(myMemberNode.getDelegate());
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
            }
        }
        boolean okActionEnabled = false;
        Label_0155: {
            Label_0146: {
                try {
                    if (this.myAllowEmptySelection) {
                        break Label_0146;
                    }
                    final OCCheckboxMemberChooser ocCheckboxMemberChooser2 = this;
                    final List<OCMemberChooserObject> list2 = ocCheckboxMemberChooser2.myChosenElements;
                    final boolean b2 = list2.isEmpty();
                    if (!b2) {
                        break Label_0146;
                    }
                    break Label_0146;
                }
                catch (IllegalStateException ex3) {
                    throw a(ex3);
                }
                try {
                    final OCCheckboxMemberChooser ocCheckboxMemberChooser2 = this;
                    final List<OCMemberChooserObject> list2 = ocCheckboxMemberChooser2.myChosenElements;
                    final boolean b2 = list2.isEmpty();
                    if (!b2) {
                        okActionEnabled = true;
                        break Label_0155;
                    }
                }
                catch (IllegalStateException ex4) {
                    throw a(ex4);
                }
            }
            okActionEnabled = false;
        }
        this.setOKActionEnabled(okActionEnabled);
    }
    
    @Override
    public void selectElements(final ClassMember[] array) {
        super.selectElements(array);
        for (int length = array.length, i = 0; i < length; ++i) {
            final MemberNode memberNode = (MemberNode)this.myElementToNodeMap.get((Object)array[i]);
            try {
                if (memberNode instanceof MyMemberNode) {
                    ((MyMemberNode)memberNode).setChecked(true);
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
        }
    }
    
    @Override
    protected MemberNode createMemberNode(final Ref<Integer> ref, final OCMemberChooserObject ocMemberChooserObject, final ParentNode parentNode) {
        return new MyMemberNode(ref, ocMemberChooserObject, parentNode);
    }
    
    @Override
    public void resetElements(final OCMemberChooserObject[] p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aload_1        
        //     2: invokespecial   com/jetbrains/cidr/lang/generate/OCMemberChooser.resetElements:([Lcom/intellij/codeInsight/generation/ClassMember;)V
        //     5: aload_0        
        //     6: aload_0        
        //     7: getfield        com/jetbrains/cidr/lang/generate/OCCheckboxMemberChooser.myAllowEmptySelection:Z
        //    10: ifne            46
        //    13: aload_0        
        //    14: getfield        com/jetbrains/cidr/lang/generate/OCCheckboxMemberChooser.myChosenElements:Ljava/util/List;
        //    17: ifnull          54
        //    20: goto            27
        //    23: invokestatic    com/jetbrains/cidr/lang/generate/OCCheckboxMemberChooser.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    26: athrow         
        //    27: aload_0        
        //    28: getfield        com/jetbrains/cidr/lang/generate/OCCheckboxMemberChooser.myChosenElements:Ljava/util/List;
        //    31: invokeinterface java/util/List.isEmpty:()Z
        //    36: ifne            54
        //    39: goto            46
        //    42: invokestatic    com/jetbrains/cidr/lang/generate/OCCheckboxMemberChooser.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    45: athrow         
        //    46: iconst_1       
        //    47: goto            55
        //    50: invokestatic    com/jetbrains/cidr/lang/generate/OCCheckboxMemberChooser.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    53: athrow         
        //    54: iconst_0       
        //    55: invokevirtual   com/jetbrains/cidr/lang/generate/OCCheckboxMemberChooser.setOKActionEnabled:(Z)V
        //    58: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      20     23     27     Ljava/lang/IllegalStateException;
        //  13     39     42     46     Ljava/lang/IllegalStateException;
        //  27     50     50     54     Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0027:
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
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
    
    private class MyMemberNode extends CheckedTreeNode implements MemberNode
    {
        private MemberNodeImpl myDelegate;
        
        public MyMemberNode(final Ref<Integer> ref, final OCMemberChooserObject ocMemberChooserObject, final ParentNode parentNode) {
            super((Object)null);
            this.myDelegate = new MemberNodeImpl(null, (ClassMember)ocMemberChooserObject, ref);
            super.setChecked(false);
            if (parentNode != null) {
                parentNode.add(this);
            }
        }
        
        public OCMemberChooserObject getDelegate() {
            return (OCMemberChooserObject)this.myDelegate.getDelegate();
        }
        
        public int getOrder() {
            return this.myDelegate.getOrder();
        }
        
        public void setChecked(final boolean checked) {
            if (OCCheckboxMemberChooser.this.isMemberEnabled((OCMemberChooserObject)this.myDelegate.getDelegate())) {
                super.setChecked(checked);
                OCCheckboxMemberChooser.this.refreshChosenMembers();
            }
        }
    }
}
