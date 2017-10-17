// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature;

import com.intellij.refactoring.changeSignature.ParameterInfo;
import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.cidr.lang.refactoring.OCNameSuggester;
import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import com.intellij.psi.PsiCodeFragment;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.types.OCType;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.fileTypes.FileType;
import com.jetbrains.cidr.lang.OCFileType;
import com.intellij.util.ui.ColumnInfo;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.refactoring.changeSignature.ParameterTableModelItemBase;
import com.intellij.refactoring.changeSignature.ParameterTableModelBase;

public class OCParameterTableModel extends ParameterTableModelBase<OCParameterInfo, ParameterTableModelItemBase<OCParameterInfo>>
{
    public OCParameterTableModel(final PsiElement psiElement, final boolean b) {
        super(psiElement, psiElement, a(psiElement.getProject(), b));
    }
    
    private static ColumnInfo[] a(final Project project, final boolean b) {
        if (b) {
            return new ColumnInfo[] { new SelectorColumn(project), new TypeColumn<Object, Object>(project, (FileType)OCFileType.INSTANCE), new NameColumn<Object, Object>(project) };
        }
        return new ColumnInfo[] { new TypeColumn<Object, Object>(project, (FileType)OCFileType.INSTANCE), new NameColumn<Object, Object>(project) };
    }
    
    public void updateColumns(final Project project, final boolean b) {
        this.setColumnInfos(a(project, b));
    }
    
    @Override
    protected ParameterTableModelItemBase<OCParameterInfo> createRowItem(@Nullable OCParameterInfo ocParameterInfo) {
        if (ocParameterInfo == null) {
            ocParameterInfo = new OCParameterInfo("", "", null, -1, this.myTypeContext);
        }
        final boolean ellipsisType = ocParameterInfo.isEllipsisType();
        final OCType type = ocParameterInfo.getType();
        String s = "";
        if (type != null) {
            if (((OCPointerType)type).getClass().equals(OCPointerType.class) && ((OCPointerType)type).getLengthInBrackets() != null) {
                s = ocParameterInfo.getTypeText();
            }
            else {
                s = type.getBestNameInContext(this.myTypeContext, ocParameterInfo.getTypeText());
            }
        }
        return new ParameterTableModelItemBase<OCParameterInfo>(ocParameterInfo, OCElementFactory.getTypeCodeFragmentInWriteAction(s, this.myTypeContext.getProject(), this.myTypeContext), null) {
            @Override
            public boolean isEllipsisType() {
                return ellipsisType;
            }
        };
    }
    
    public void addFirstRow(final OCParameterInfo ocParameterInfo) {
        final ArrayList<ParameterTableModelItemBase<OCParameterInfo>> items = new ArrayList<ParameterTableModelItemBase<OCParameterInfo>>();
        items.add(this.createRowItem(ocParameterInfo));
        items.addAll((Collection<?>)this.getItems());
        this.setItems((List)items);
    }
    
    public void addLastRow(final OCParameterInfo ocParameterInfo) {
        this.addRow((Object)this.createRowItem(ocParameterInfo));
    }
    
    public void addRow(final OCParameterInfo ocParameterInfo, final int n) {
        final ArrayList<ParameterTableModelItemBase<OCParameterInfo>> items = new ArrayList<ParameterTableModelItemBase<OCParameterInfo>>(this.getItems());
        items.add(n, this.createRowItem(ocParameterInfo));
        this.setItems((List)items);
    }
    
    public void setValueAt(final Object o, final int n, final int n2) {
        this.setValueAtWithoutUpdate(o, n, n2);
    }
    
    public void exchangeRows(final int n, final int n2) {
        if (n == 0 && n2 != 0) {
            final OCParameterInfo ocParameterInfo = (OCParameterInfo)((ParameterTableModelItemBase)this.getItem(n)).parameter;
            final OCParameterInfo ocParameterInfo2 = (OCParameterInfo)((ParameterTableModelItemBase)this.getItem(n2)).parameter;
            final String string = OCNameSuggester.getSelectorNameWithoutParameter(ocParameterInfo.getSelector(), ocParameterInfo.getName()) + StringUtil.capitalize(OCNameSuggester.removeSelectorPrefixes(ocParameterInfo2.getSelector()));
            final String removeVariablePrefixes = OCNameSuggester.removeVariablePrefixes(ocParameterInfo.getName());
            ocParameterInfo2.setSelector(string);
            ocParameterInfo.setSelector(removeVariablePrefixes);
        }
        else if (n2 == 0 && n != 0) {
            this.exchangeRows(n2, n);
            return;
        }
        super.exchangeRows(n, n2);
    }
    
    public void removeRow(final int n) {
        this.removeRow(n, true);
    }
    
    public void removeRow(final int n, final boolean b) {
        if (b && n == 0 && this.getRowCount() > 1) {
            final OCParameterInfo ocParameterInfo = (OCParameterInfo)((ParameterTableModelItemBase)this.getItem(0)).parameter;
            final OCParameterInfo ocParameterInfo2 = (OCParameterInfo)((ParameterTableModelItemBase)this.getItem(1)).parameter;
            ocParameterInfo2.setSelector(OCNameSuggester.getSelectorNameWithoutParameter(ocParameterInfo.getSelector(), ocParameterInfo.getName()) + StringUtil.capitalize(OCNameSuggester.removeSelectorPrefixes(ocParameterInfo2.getSelector())));
        }
        super.removeRow(n);
    }
    
    public static boolean isTypeColumn(final ColumnInfo columnInfo) {
        return columnInfo instanceof TypeColumn;
    }
    
    private static class SelectorColumn extends NameColumn<OCParameterInfo, ParameterTableModelItemBase<OCParameterInfo>>
    {
        public SelectorColumn(final Project project) {
            super(project);
            this.setName("Selector part:");
        }
        
        @Override
        public String valueOf(final ParameterTableModelItemBase<OCParameterInfo> parameterTableModelItemBase) {
            return parameterTableModelItemBase.parameter.getSelector();
        }
        
        @Override
        public void setValue(final ParameterTableModelItemBase<OCParameterInfo> parameterTableModelItemBase, final String selector) {
            parameterTableModelItemBase.parameter.setSelector(selector);
        }
    }
}
