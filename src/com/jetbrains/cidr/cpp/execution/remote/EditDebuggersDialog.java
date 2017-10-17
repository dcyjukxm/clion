// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.execution.remote;

import com.intellij.openapi.project.Project;
import kotlin.jvm.internal.Intrinsics;
import java.awt.Dimension;
import com.intellij.ui.AddEditRemovePanel$TableModel;
import javax.swing.JComponent;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import com.intellij.ui.table.JBTable;
import kotlin.Metadata;
import com.intellij.openapi.ui.DialogWrapper;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\bJ\n\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\b\u0010\u0013\u001a\u00020\u0014H\u0014R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0015" }, d2 = { "Lcom/jetbrains/cidr/cpp/execution/remote/EditDebuggersDialog;", "Lcom/intellij/openapi/ui/DialogWrapper;", "project", "Lcom/intellij/openapi/project/Project;", "list", "", "", "selected", "(Lcom/intellij/openapi/project/Project;Ljava/util/List;Ljava/lang/String;)V", "getList", "()Ljava/util/List;", "myTable", "Lcom/intellij/ui/table/JBTable;", "getSelected", "()Ljava/lang/String;", "setSelected", "(Ljava/lang/String;)V", "createCenterPanel", "Ljavax/swing/JComponent;", "doOKAction", "", "clion" })
public final class EditDebuggersDialog extends DialogWrapper
{
    private JBTable myTable;
    @NotNull
    private final List<String> list;
    @Nullable
    private String selected;
    
    @Nullable
    protected JComponent createCenterPanel() {
        final EditDebuggersDialog$createCenterPanel$model.EditDebuggersDialog$createCenterPanel$model$1 editDebuggersDialog$createCenterPanel$model$1 = new EditDebuggersDialog$createCenterPanel$model.EditDebuggersDialog$createCenterPanel$model$1();
        final EditDebuggersDialog$createCenterPanel$panel.EditDebuggersDialog$createCenterPanel$panel$1 editDebuggersDialog$createCenterPanel$panel$1 = new EditDebuggersDialog$createCenterPanel$panel.EditDebuggersDialog$createCenterPanel$panel$1(this, editDebuggersDialog$createCenterPanel$model$1, (AddEditRemovePanel$TableModel)editDebuggersDialog$createCenterPanel$model$1, (List)this.list);
        editDebuggersDialog$createCenterPanel$panel$1.getEmptyText().setText("No debuggers configured");
        editDebuggersDialog$createCenterPanel$panel$1.setPreferredSize(new Dimension(400, 150));
        final JBTable table = editDebuggersDialog$createCenterPanel$panel$1.getTable();
        Intrinsics.checkExpressionValueIsNotNull((Object)table, "panel.table");
        this.myTable = table;
        final int index = editDebuggersDialog$createCenterPanel$panel$1.getData().indexOf(this.selected);
        if (index != -1) {
            final JBTable myTable = this.myTable;
            if (myTable == null) {
                Intrinsics.throwUninitializedPropertyAccessException("myTable");
            }
            myTable.setRowSelectionInterval(index, index);
        }
        return (JComponent)editDebuggersDialog$createCenterPanel$panel$1;
    }
    
    protected void doOKAction() {
        super.doOKAction();
        final JBTable myTable = this.myTable;
        if (myTable == null) {
            Intrinsics.throwUninitializedPropertyAccessException("myTable");
        }
        if (myTable.getSelectedRowCount() != 0) {
            final List<String> list = this.list;
            final JBTable myTable2 = this.myTable;
            if (myTable2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("myTable");
            }
            this.selected = list.get(myTable2.getSelectedRow());
        }
    }
    
    @NotNull
    public final List<String> getList() {
        return this.list;
    }
    
    @Nullable
    public final String getSelected() {
        return this.selected;
    }
    
    public final void setSelected(@Nullable final String selected) {
        this.selected = selected;
    }
    
    public EditDebuggersDialog(@NotNull final Project project, @NotNull final List<String> list, @Nullable final String selected) {
        Intrinsics.checkParameterIsNotNull((Object)project, "project");
        Intrinsics.checkParameterIsNotNull((Object)list, "list");
        super(project);
        this.list = list;
        this.selected = selected;
        this.setTitle("Debuggers");
        this.init();
    }
}
