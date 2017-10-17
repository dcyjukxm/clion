// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature;

import java.awt.Component;
import javax.swing.JTable;
import java.awt.Container;
import com.intellij.ui.table.JBTable;
import javax.swing.SwingUtilities;
import com.intellij.util.Alarm;
import com.intellij.refactoring.changeSignature.ParameterTableModelItemBase;
import com.jetbrains.cidr.lang.psi.OCFunctionDefinition;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.psi.PsiDocumentManager;

private class DialogChangeSignatureHandlerImpl extends OCChangeSignatureHandlerImpl
{
    public DialogChangeSignatureHandlerImpl() {
        super((OCParameterTableModel)OCChangeSignatureDialog.access$1400(OCChangeSignatureDialog.this), (OCMethodDescriptor)OCChangeSignatureDialog.access$1500(OCChangeSignatureDialog.this), OCChangeSignatureDialog.access$1600(OCChangeSignatureDialog.this));
    }
    
    @Override
    public void setTitle(final String s) {
        super.setTitle(s);
        OCChangeSignatureDialog.this.setTitle(s);
    }
    
    @Override
    public void setHelpId(final String s) {
        super.setHelpId(s);
        OCChangeSignatureDialog.this.setHelpId(s);
    }
    
    public void setCallableKindToModel(final OCCallableKind callableKind) {
        super.setCallableKind(callableKind);
    }
    
    @Override
    public void setCallableKind(final OCCallableKind ocCallableKind) {
        super.setCallableKind(ocCallableKind);
        OCChangeSignatureDialog.access$1700(OCChangeSignatureDialog.this).setVisibility(ocCallableKind);
    }
    
    @Override
    public void setChangeParentClassPossible(final boolean visible) {
        OCChangeSignatureDialog.access$1802(OCChangeSignatureDialog.this, visible);
        OCChangeSignatureDialog.access$1900(OCChangeSignatureDialog.this).setVisible(visible);
    }
    
    @Override
    public void setNameVisible(final boolean visible) {
        OCChangeSignatureDialog.access$2000(OCChangeSignatureDialog.this).setVisible(visible);
    }
    
    @Override
    public void setName(final String name) {
        super.setName(name);
        OCChangeSignatureDialog.access$2100(OCChangeSignatureDialog.this).setText(OCChangeSignatureDialog.access$200(OCChangeSignatureDialog.this).isMethod() ? this.calculateMethodName() : name);
        PsiDocumentManager.getInstance(OCChangeSignatureDialog.access$2200(OCChangeSignatureDialog.this)).commitAllDocuments();
        OCChangeSignatureDialog.this.updateSignatureAlarmFired();
    }
    
    @Override
    public void setReturnType(final OCType returnType) {
        super.setReturnType(returnType);
        if (OCChangeSignatureDialog.access$2300(OCChangeSignatureDialog.this) != null) {
            OCChangeSignatureDialog.access$2400(OCChangeSignatureDialog.this).setText(this.myReturnTypeText);
        }
    }
    
    @Override
    public void setParentClass(final OCSymbol ocSymbol, final boolean b, final List<? extends OCClassSymbol> list) {
        super.setParentClass(ocSymbol, b, list);
        OCChangeSignatureDialog.access$2500(OCChangeSignatureDialog.this).setText((ocSymbol != null) ? ocSymbol.getName() : "");
    }
    
    @Override
    OCType getReturnType() {
        final OCType type = OCElementUtil.getType((PsiElement)OCChangeSignatureDialog.access$2600(OCChangeSignatureDialog.this));
        return (type != null) ? type : this.myReturnType;
    }
    
    @Override
    String getReturnTypeText() {
        return (OCChangeSignatureDialog.access$2700(OCChangeSignatureDialog.this) != null) ? OCChangeSignatureDialog.access$2800(OCChangeSignatureDialog.this).getText().trim() : "";
    }
    
    @Override
    String getMethodName() {
        return OCChangeSignatureDialog.this.getMethodName();
    }
    
    @Nullable
    @Override
    OCClassDeclaration getContainerClass() {
        if (this.myCallableKind == OCCallableKind.METHOD) {
            return OCElementUtil.resolveClassDeclaration(OCChangeSignatureDialog.access$2500(OCChangeSignatureDialog.this).getClassDeclaration(OCChangeSignatureDialog.access$2900(OCChangeSignatureDialog.this)));
        }
        return null;
    }
    
    @Override
    public void setRefactorButtonText(final String s) {
        OCChangeSignatureDialog.access$3000(OCChangeSignatureDialog.this).putValue("Name", s);
    }
    
    @Override
    public void invoke() {
        final List items = this.myParametersTableModel.getItems();
        if (OCChangeSignatureDialog.access$200(OCChangeSignatureDialog.this).getGeneratedInfo().getMethodParent() instanceof OCClassSymbol) {
            OCChangeSignatureDialog.access$3100(OCChangeSignatureDialog.this).setVisible(!OCChangeSignatureDialog.access$1800(OCChangeSignatureDialog.this));
            ((OCTargetSymbolPanel)OCChangeSignatureDialog.access$3200(OCChangeSignatureDialog.this)).fillData(OCChangeSignatureDialog.access$200(OCChangeSignatureDialog.this).getGeneratedInfo());
            OCChangeSignatureDialog.access$2500(OCChangeSignatureDialog.this).setText(OCChangeSignatureDialog.access$200(OCChangeSignatureDialog.this).getGeneratedInfo().getMethodParent().getName());
        }
        else if (this.myMethod.getMethod() instanceof OCFunctionDefinition && ((OCFunctionDefinition)this.myMethod.getMethod()).getNamespaceQualifier() == null && this.myGeneratedInfo.getMethodReference() != null) {
            OCChangeSignatureDialog.access$3300(OCChangeSignatureDialog.this).setVisible(true);
        }
        if (this.myMethod.isConstructor()) {
            if (OCChangeSignatureDialog.access$3400(OCChangeSignatureDialog.this) != null) {
                OCChangeSignatureDialog.access$3500(OCChangeSignatureDialog.this).setEnabled(false);
            }
            OCChangeSignatureDialog.access$3600(OCChangeSignatureDialog.this).setEnabled(false);
            OCChangeSignatureDialog.access$3700(OCChangeSignatureDialog.this).setVisible(false);
        }
        if (OCChangeSignatureDialog.access$200(OCChangeSignatureDialog.this).isMethod() && !items.isEmpty()) {
            final JBTable access$3800 = OCChangeSignatureDialog.access$3800(OCChangeSignatureDialog.this);
            if (items.size() > 0 && ((OCParameterInfo)items.get(0).parameter).getSelector().isEmpty()) {
                SwingUtilities.invokeLater(() -> new Alarm(Alarm.ThreadToUse.SWING_THREAD).addRequest(() -> ((JTable)access$3800).editCellAt(0, 0), 300));
            }
            final Container parent = ((Component)access$3800).getParent();
            if (parent == null) {
                ((Component)access$3800).repaint();
            }
            else {
                parent.repaint();
            }
        }
        OCChangeSignatureDialog.this.show();
    }
}
