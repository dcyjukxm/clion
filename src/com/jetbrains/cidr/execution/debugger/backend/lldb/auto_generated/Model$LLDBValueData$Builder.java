// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.MessageLite;
import com.google.protobuf.AbstractMessage;
import com.google.protobuf.ByteString;
import java.io.IOException;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.Message;
import com.google.protobuf.Descriptors;
import com.google.protobuf.GeneratedMessage;

public static final class Builder extends GeneratedMessage.Builder<Builder> implements LLDBValueDataOrBuilder
{
    private int bitField0_;
    private Object value_;
    private Object description_;
    private boolean hasLongerDescription_;
    private boolean mayHaveChildren_;
    private boolean isSynthetic_;
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Model.access$8500();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Model.access$8600().ensureFieldAccessorsInitialized((Class)LLDBValueData.class, (Class)Builder.class);
    }
    
    private Builder() {
        this.value_ = "";
        this.description_ = "";
        this.b();
    }
    
    private Builder(final GeneratedMessage.BuilderParent builderParent) {
        super(builderParent);
        this.value_ = "";
        this.description_ = "";
        this.b();
    }
    
    private void b() {
        if (LLDBValueData.access$9000()) {}
    }
    
    private static Builder a() {
        return new Builder();
    }
    
    public Builder clear() {
        super.clear();
        this.value_ = "";
        this.bitField0_ &= 0xFFFFFFFE;
        this.description_ = "";
        this.bitField0_ &= 0xFFFFFFFD;
        this.hasLongerDescription_ = false;
        this.bitField0_ &= 0xFFFFFFFB;
        this.mayHaveChildren_ = false;
        this.bitField0_ &= 0xFFFFFFF7;
        this.isSynthetic_ = false;
        this.bitField0_ &= 0xFFFFFFEF;
        return this;
    }
    
    public Builder clone() {
        return a().mergeFrom(this.buildPartial());
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
        return Model.access$8500();
    }
    
    public LLDBValueData getDefaultInstanceForType() {
        return LLDBValueData.getDefaultInstance();
    }
    
    public LLDBValueData build() {
        final LLDBValueData buildPartial = this.buildPartial();
        try {
            if (!buildPartial.isInitialized()) {
                throw newUninitializedMessageException((Message)buildPartial);
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return buildPartial;
    }
    
    public LLDBValueData buildPartial() {
        final LLDBValueData lldbValueData = new LLDBValueData((GeneratedMessage.Builder)this);
        final int bitField0_ = this.bitField0_;
        int n = 0;
        if ((bitField0_ & 0x1) == 0x1) {
            n |= 0x1;
        }
        LLDBValueData.access$9202(lldbValueData, this.value_);
        if ((bitField0_ & 0x2) == 0x2) {
            n |= 0x2;
        }
        LLDBValueData.access$9302(lldbValueData, this.description_);
        if ((bitField0_ & 0x4) == 0x4) {
            n |= 0x4;
        }
        LLDBValueData.access$9402(lldbValueData, this.hasLongerDescription_);
        if ((bitField0_ & 0x8) == 0x8) {
            n |= 0x8;
        }
        LLDBValueData.access$9502(lldbValueData, this.mayHaveChildren_);
        if ((bitField0_ & 0x10) == 0x10) {
            n |= 0x10;
        }
        LLDBValueData.access$9602(lldbValueData, this.isSynthetic_);
        LLDBValueData.access$9702(lldbValueData, n);
        this.onBuilt();
        return lldbValueData;
    }
    
    public Builder mergeFrom(final Message message) {
        try {
            if (message instanceof LLDBValueData) {
                return this.mergeFrom((LLDBValueData)message);
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        super.mergeFrom(message);
        return this;
    }
    
    public Builder mergeFrom(final LLDBValueData lldbValueData) {
        try {
            if (lldbValueData == LLDBValueData.getDefaultInstance()) {
                return this;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (lldbValueData.hasValue()) {
                this.bitField0_ |= 0x1;
                this.value_ = LLDBValueData.access$9200(lldbValueData);
                this.onChanged();
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        try {
            if (lldbValueData.hasDescription()) {
                this.bitField0_ |= 0x2;
                this.description_ = LLDBValueData.access$9300(lldbValueData);
                this.onChanged();
            }
        }
        catch (NullPointerException ex3) {
            throw b(ex3);
        }
        try {
            if (lldbValueData.hasHasLongerDescription()) {
                this.setHasLongerDescription(lldbValueData.getHasLongerDescription());
            }
        }
        catch (NullPointerException ex4) {
            throw b(ex4);
        }
        try {
            if (lldbValueData.hasMayHaveChildren()) {
                this.setMayHaveChildren(lldbValueData.getMayHaveChildren());
            }
        }
        catch (NullPointerException ex5) {
            throw b(ex5);
        }
        try {
            if (lldbValueData.hasIsSynthetic()) {
                this.setIsSynthetic(lldbValueData.getIsSynthetic());
            }
        }
        catch (NullPointerException ex6) {
            throw b(ex6);
        }
        this.mergeUnknownFields(lldbValueData.getUnknownFields());
        return this;
    }
    
    public final boolean isInitialized() {
        try {
            if (!this.hasValue()) {
                return false;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (!this.hasMayHaveChildren()) {
                return false;
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        try {
            if (!this.hasIsSynthetic()) {
                return false;
            }
        }
        catch (NullPointerException ex3) {
            throw b(ex3);
        }
        return true;
    }
    
    public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        LLDBValueData lldbValueData = null;
        try {
            lldbValueData = (LLDBValueData)LLDBValueData.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
        }
        catch (InvalidProtocolBufferException ex) {
            lldbValueData = (LLDBValueData)ex.getUnfinishedMessage();
            throw ex;
        }
        finally {
            try {
                if (lldbValueData != null) {
                    this.mergeFrom(lldbValueData);
                }
            }
            catch (InvalidProtocolBufferException ex2) {
                throw b((Exception)ex2);
            }
        }
        return this;
    }
    
    public boolean hasValue() {
        try {
            if ((this.bitField0_ & 0x1) == 0x1) {
                return true;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return false;
    }
    
    public String getValue() {
        final Object value_ = this.value_;
        if (!(value_ instanceof String)) {
            return (String)(this.value_ = ((ByteString)value_).toStringUtf8());
        }
        return (String)value_;
    }
    
    public ByteString getValueBytes() {
        final Object value_ = this.value_;
        if (value_ instanceof String) {
            return (ByteString)(this.value_ = ByteString.copyFromUtf8((String)value_));
        }
        return (ByteString)value_;
    }
    
    public Builder setValue(final String value_) {
        try {
            if (value_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x1;
        this.value_ = value_;
        this.onChanged();
        return this;
    }
    
    public Builder clearValue() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.value_ = LLDBValueData.getDefaultInstance().getValue();
        this.onChanged();
        return this;
    }
    
    public Builder setValueBytes(final ByteString value_) {
        try {
            if (value_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x1;
        this.value_ = value_;
        this.onChanged();
        return this;
    }
    
    public boolean hasDescription() {
        try {
            if ((this.bitField0_ & 0x2) == 0x2) {
                return true;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return false;
    }
    
    public String getDescription() {
        final Object description_ = this.description_;
        if (!(description_ instanceof String)) {
            return (String)(this.description_ = ((ByteString)description_).toStringUtf8());
        }
        return (String)description_;
    }
    
    public ByteString getDescriptionBytes() {
        final Object description_ = this.description_;
        if (description_ instanceof String) {
            return (ByteString)(this.description_ = ByteString.copyFromUtf8((String)description_));
        }
        return (ByteString)description_;
    }
    
    public Builder setDescription(final String description_) {
        try {
            if (description_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x2;
        this.description_ = description_;
        this.onChanged();
        return this;
    }
    
    public Builder clearDescription() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.description_ = LLDBValueData.getDefaultInstance().getDescription();
        this.onChanged();
        return this;
    }
    
    public Builder setDescriptionBytes(final ByteString description_) {
        try {
            if (description_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x2;
        this.description_ = description_;
        this.onChanged();
        return this;
    }
    
    public boolean hasHasLongerDescription() {
        try {
            if ((this.bitField0_ & 0x4) == 0x4) {
                return true;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return false;
    }
    
    public boolean getHasLongerDescription() {
        return this.hasLongerDescription_;
    }
    
    public Builder setHasLongerDescription(final boolean hasLongerDescription_) {
        this.bitField0_ |= 0x4;
        this.hasLongerDescription_ = hasLongerDescription_;
        this.onChanged();
        return this;
    }
    
    public Builder clearHasLongerDescription() {
        this.bitField0_ &= 0xFFFFFFFB;
        this.hasLongerDescription_ = false;
        this.onChanged();
        return this;
    }
    
    public boolean hasMayHaveChildren() {
        try {
            if ((this.bitField0_ & 0x8) == 0x8) {
                return true;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return false;
    }
    
    public boolean getMayHaveChildren() {
        return this.mayHaveChildren_;
    }
    
    public Builder setMayHaveChildren(final boolean mayHaveChildren_) {
        this.bitField0_ |= 0x8;
        this.mayHaveChildren_ = mayHaveChildren_;
        this.onChanged();
        return this;
    }
    
    public Builder clearMayHaveChildren() {
        this.bitField0_ &= 0xFFFFFFF7;
        this.mayHaveChildren_ = false;
        this.onChanged();
        return this;
    }
    
    public boolean hasIsSynthetic() {
        try {
            if ((this.bitField0_ & 0x10) == 0x10) {
                return true;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return false;
    }
    
    public boolean getIsSynthetic() {
        return this.isSynthetic_;
    }
    
    public Builder setIsSynthetic(final boolean isSynthetic_) {
        this.bitField0_ |= 0x10;
        this.isSynthetic_ = isSynthetic_;
        this.onChanged();
        return this;
    }
    
    public Builder clearIsSynthetic() {
        this.bitField0_ &= 0xFFFFFFEF;
        this.isSynthetic_ = false;
        this.onChanged();
        return this;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
