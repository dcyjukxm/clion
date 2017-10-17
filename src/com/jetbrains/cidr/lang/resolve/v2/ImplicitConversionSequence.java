// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve.v2;

import com.jetbrains.cidr.lang.types.OCType;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.OCTypeOwner;

public class ImplicitConversionSequence
{
    Kind ConversionKind;
    boolean StdInitializerListElement;
    StandardConversionSequence Standard;
    UserDefinedConversionSequence UserDefined;
    AmbiguousConversionSequence Ambiguous;
    BadConversionSequence Bad;
    private boolean hasMagic;
    
    void setKind(final Kind conversionKind) {
        this.destruct();
        this.ConversionKind = conversionKind;
    }
    
    void destruct() {
    }
    
    public ImplicitConversionSequence() {
        this.ConversionKind = Kind.Uninitialized;
        this.hasMagic = false;
    }
    
    public ImplicitConversionSequence(final ImplicitConversionSequence implicitConversionSequence) {
        this.ConversionKind = Kind.Uninitialized;
        this.hasMagic = false;
        this.ConversionKind = implicitConversionSequence.ConversionKind;
        this.StdInitializerListElement = implicitConversionSequence.StdInitializerListElement;
        switch (this.ConversionKind) {
            case StandardConversion: {
                this.Standard = implicitConversionSequence.Standard;
                break;
            }
            case UserDefinedConversion: {
                this.UserDefined = implicitConversionSequence.UserDefined;
                break;
            }
            case AmbiguousConversion: {
                this.Ambiguous = implicitConversionSequence.Ambiguous;
            }
            case BadConversion: {
                this.Bad = implicitConversionSequence.Bad;
                break;
            }
        }
    }
    
    public Kind getKind() {
        assert this.isInitialized() : "querying uninitialized conversion";
        return this.ConversionKind;
    }
    
    public int getKindRank() {
        switch (this.getKind()) {
            case StandardConversion: {
                return 0;
            }
            case UserDefinedConversion:
            case AmbiguousConversion: {
                return 1;
            }
            case EllipsisConversion: {
                return 2;
            }
            case BadConversion: {
                return 3;
            }
            default: {
                return -1;
            }
        }
    }
    
    public boolean isBad() {
        return this.getKind() == Kind.BadConversion;
    }
    
    public boolean isStandard() {
        return this.getKind() == Kind.StandardConversion;
    }
    
    public boolean isEllipsis() {
        return this.getKind() == Kind.EllipsisConversion;
    }
    
    public boolean isAmbiguous() {
        return this.getKind() == Kind.AmbiguousConversion;
    }
    
    public boolean isUserDefined() {
        return this.getKind() == Kind.UserDefinedConversion;
    }
    
    public boolean isFailure() {
        return this.isBad() || this.isAmbiguous();
    }
    
    boolean isInitialized() {
        return this.ConversionKind != Kind.Uninitialized;
    }
    
    void setBad(final BadConversionSequence.FailureKind failureKind, @Nullable final OCTypeOwner ocTypeOwner, final OCType ocType, final OCType ocType2) {
        this.setKind(Kind.BadConversion);
        this.Bad = new BadConversionSequence(failureKind, ocTypeOwner, ocType, ocType2);
    }
    
    void setBad(final BadConversionSequence.FailureKind failureKind, final OCType ocType, final OCType ocType2) {
        this.setKind(Kind.BadConversion);
        this.Bad = new BadConversionSequence(failureKind, ocType, ocType2);
    }
    
    public void setStandard() {
        this.setKind(Kind.StandardConversion);
        if (this.Standard == null) {
            this.Standard = new StandardConversionSequence();
        }
    }
    
    public void setEllipsis() {
        this.setKind(Kind.EllipsisConversion);
    }
    
    public void setUserDefined() {
        this.setKind(Kind.UserDefinedConversion);
    }
    
    void setAmbiguous() {
        if (this.ConversionKind == Kind.AmbiguousConversion) {
            return;
        }
        this.ConversionKind = Kind.AmbiguousConversion;
    }
    
    boolean isStdInitializerListElement() {
        return this.StdInitializerListElement;
    }
    
    void setStdInitializerListElement() {
        this.setStdInitializerListElement(true);
    }
    
    void setStdInitializerListElement(final boolean stdInitializerListElement) {
        this.StdInitializerListElement = stdInitializerListElement;
    }
    
    public boolean hasMagic() {
        return this.hasMagic;
    }
    
    public static ImplicitConversionSequence magic() {
        final ImplicitConversionSequence implicitConversionSequence = new ImplicitConversionSequence();
        implicitConversionSequence.setStandard();
        implicitConversionSequence.hasMagic = true;
        return implicitConversionSequence;
    }
    
    @Override
    public String toString() {
        return "ICS{Kind=" + this.ConversionKind + ", magic=" + this.hasMagic + '}';
    }
    
    enum Kind
    {
        StandardConversion, 
        UserDefinedConversion, 
        AmbiguousConversion, 
        EllipsisConversion, 
        BadConversion, 
        Uninitialized;
    }
    
    public enum CompareKind
    {
        Better, 
        Indistinguishable, 
        Worse;
    }
}
