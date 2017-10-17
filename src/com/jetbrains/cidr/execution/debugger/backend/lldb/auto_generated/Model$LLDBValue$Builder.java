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

public static final class Builder extends GeneratedMessage.Builder<Builder> implements LLDBValueOrBuilder
{
    private int bitField0_;
    private int id_;
    private Object name_;
    private Object type_;
    private TypeClass typeClass_;
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Model.access$7300();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Model.access$7400().ensureFieldAccessorsInitialized((Class)LLDBValue.class, (Class)Builder.class);
    }
    
    private Builder() {
        this.name_ = "";
        this.type_ = "";
        this.typeClass_ = TypeClass.TypeClassArray;
        this.b();
    }
    
    private Builder(final GeneratedMessage.BuilderParent builderParent) {
        super(builderParent);
        this.name_ = "";
        this.type_ = "";
        this.typeClass_ = TypeClass.TypeClassArray;
        this.b();
    }
    
    private void b() {
        if (LLDBValue.access$7800()) {}
    }
    
    private static Builder a() {
        return new Builder();
    }
    
    public Builder clear() {
        super.clear();
        this.id_ = 0;
        this.bitField0_ &= 0xFFFFFFFE;
        this.name_ = "";
        this.bitField0_ &= 0xFFFFFFFD;
        this.type_ = "";
        this.bitField0_ &= 0xFFFFFFFB;
        this.typeClass_ = TypeClass.TypeClassArray;
        this.bitField0_ &= 0xFFFFFFF7;
        return this;
    }
    
    public Builder clone() {
        return a().mergeFrom(this.buildPartial());
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
        return Model.access$7300();
    }
    
    public LLDBValue getDefaultInstanceForType() {
        return LLDBValue.getDefaultInstance();
    }
    
    public LLDBValue build() {
        final LLDBValue buildPartial = this.buildPartial();
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
    
    public LLDBValue buildPartial() {
        final LLDBValue lldbValue = new LLDBValue((GeneratedMessage.Builder)this);
        final int bitField0_ = this.bitField0_;
        int n = 0;
        if ((bitField0_ & 0x1) == 0x1) {
            n |= 0x1;
        }
        LLDBValue.access$8002(lldbValue, this.id_);
        if ((bitField0_ & 0x2) == 0x2) {
            n |= 0x2;
        }
        LLDBValue.access$8102(lldbValue, this.name_);
        if ((bitField0_ & 0x4) == 0x4) {
            n |= 0x4;
        }
        LLDBValue.access$8202(lldbValue, this.type_);
        if ((bitField0_ & 0x8) == 0x8) {
            n |= 0x8;
        }
        LLDBValue.access$8302(lldbValue, this.typeClass_);
        LLDBValue.access$8402(lldbValue, n);
        this.onBuilt();
        return lldbValue;
    }
    
    public Builder mergeFrom(final Message message) {
        try {
            if (message instanceof LLDBValue) {
                return this.mergeFrom((LLDBValue)message);
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        super.mergeFrom(message);
        return this;
    }
    
    public Builder mergeFrom(final LLDBValue lldbValue) {
        try {
            if (lldbValue == LLDBValue.getDefaultInstance()) {
                return this;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (lldbValue.hasId()) {
                this.setId(lldbValue.getId());
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        try {
            if (lldbValue.hasName()) {
                this.bitField0_ |= 0x2;
                this.name_ = LLDBValue.access$8100(lldbValue);
                this.onChanged();
            }
        }
        catch (NullPointerException ex3) {
            throw b(ex3);
        }
        try {
            if (lldbValue.hasType()) {
                this.bitField0_ |= 0x4;
                this.type_ = LLDBValue.access$8200(lldbValue);
                this.onChanged();
            }
        }
        catch (NullPointerException ex4) {
            throw b(ex4);
        }
        try {
            if (lldbValue.hasTypeClass()) {
                this.setTypeClass(lldbValue.getTypeClass());
            }
        }
        catch (NullPointerException ex5) {
            throw b(ex5);
        }
        this.mergeUnknownFields(lldbValue.getUnknownFields());
        return this;
    }
    
    public final boolean isInitialized() {
        try {
            if (!this.hasId()) {
                return false;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (!this.hasName()) {
                return false;
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        try {
            if (!this.hasType()) {
                return false;
            }
        }
        catch (NullPointerException ex3) {
            throw b(ex3);
        }
        try {
            if (!this.hasTypeClass()) {
                return false;
            }
        }
        catch (NullPointerException ex4) {
            throw b(ex4);
        }
        return true;
    }
    
    public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        LLDBValue lldbValue = null;
        try {
            lldbValue = (LLDBValue)LLDBValue.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
        }
        catch (InvalidProtocolBufferException ex) {
            lldbValue = (LLDBValue)ex.getUnfinishedMessage();
            throw ex;
        }
        finally {
            try {
                if (lldbValue != null) {
                    this.mergeFrom(lldbValue);
                }
            }
            catch (InvalidProtocolBufferException ex2) {
                throw b((Exception)ex2);
            }
        }
        return this;
    }
    
    public boolean hasId() {
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
    
    public int getId() {
        return this.id_;
    }
    
    public Builder setId(final int id_) {
        this.bitField0_ |= 0x1;
        this.id_ = id_;
        this.onChanged();
        return this;
    }
    
    public Builder clearId() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.id_ = 0;
        this.onChanged();
        return this;
    }
    
    public boolean hasName() {
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
    
    public String getName() {
        final Object name_ = this.name_;
        if (!(name_ instanceof String)) {
            return (String)(this.name_ = ((ByteString)name_).toStringUtf8());
        }
        return (String)name_;
    }
    
    public ByteString getNameBytes() {
        final Object name_ = this.name_;
        if (name_ instanceof String) {
            return (ByteString)(this.name_ = ByteString.copyFromUtf8((String)name_));
        }
        return (ByteString)name_;
    }
    
    public Builder setName(final String name_) {
        try {
            if (name_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x2;
        this.name_ = name_;
        this.onChanged();
        return this;
    }
    
    public Builder clearName() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.name_ = LLDBValue.getDefaultInstance().getName();
        this.onChanged();
        return this;
    }
    
    public Builder setNameBytes(final ByteString name_) {
        try {
            if (name_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x2;
        this.name_ = name_;
        this.onChanged();
        return this;
    }
    
    public boolean hasType() {
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
    
    public String getType() {
        final Object type_ = this.type_;
        if (!(type_ instanceof String)) {
            return (String)(this.type_ = ((ByteString)type_).toStringUtf8());
        }
        return (String)type_;
    }
    
    public ByteString getTypeBytes() {
        final Object type_ = this.type_;
        if (type_ instanceof String) {
            return (ByteString)(this.type_ = ByteString.copyFromUtf8((String)type_));
        }
        return (ByteString)type_;
    }
    
    public Builder setType(final String type_) {
        try {
            if (type_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x4;
        this.type_ = type_;
        this.onChanged();
        return this;
    }
    
    public Builder clearType() {
        this.bitField0_ &= 0xFFFFFFFB;
        this.type_ = LLDBValue.getDefaultInstance().getType();
        this.onChanged();
        return this;
    }
    
    public Builder setTypeBytes(final ByteString type_) {
        try {
            if (type_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x4;
        this.type_ = type_;
        this.onChanged();
        return this;
    }
    
    public boolean hasTypeClass() {
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
    
    public TypeClass getTypeClass() {
        return this.typeClass_;
    }
    
    public Builder setTypeClass(final TypeClass typeClass_) {
        try {
            if (typeClass_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x8;
        this.typeClass_ = typeClass_;
        this.onChanged();
        return this;
    }
    
    public Builder clearTypeClass() {
        this.bitField0_ &= 0xFFFFFFF7;
        this.typeClass_ = TypeClass.TypeClassArray;
        this.onChanged();
        return this;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
