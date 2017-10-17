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

public static final class Builder extends GeneratedMessage.Builder<Builder> implements AddWatchpoint_ReqOrBuilder
{
    private int bitField0_;
    private int valueId_;
    private int toResolveLocation_;
    private int read_;
    private int write_;
    private Object condition_;
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Protocol.access$31400();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$31500().ensureFieldAccessorsInitialized((Class)AddWatchpoint_Req.class, (Class)Builder.class);
    }
    
    private Builder() {
        this.condition_ = "";
        this.b();
    }
    
    private Builder(final GeneratedMessage.BuilderParent builderParent) {
        super(builderParent);
        this.condition_ = "";
        this.b();
    }
    
    private void b() {
        if (AddWatchpoint_Req.access$31900()) {}
    }
    
    private static Builder a() {
        return new Builder();
    }
    
    public Builder clear() {
        super.clear();
        this.valueId_ = 0;
        this.bitField0_ &= 0xFFFFFFFE;
        this.toResolveLocation_ = 0;
        this.bitField0_ &= 0xFFFFFFFD;
        this.read_ = 0;
        this.bitField0_ &= 0xFFFFFFFB;
        this.write_ = 0;
        this.bitField0_ &= 0xFFFFFFF7;
        this.condition_ = "";
        this.bitField0_ &= 0xFFFFFFEF;
        return this;
    }
    
    public Builder clone() {
        return a().mergeFrom(this.buildPartial());
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
        return Protocol.access$31400();
    }
    
    public AddWatchpoint_Req getDefaultInstanceForType() {
        return AddWatchpoint_Req.getDefaultInstance();
    }
    
    public AddWatchpoint_Req build() {
        final AddWatchpoint_Req buildPartial = this.buildPartial();
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
    
    public AddWatchpoint_Req buildPartial() {
        final AddWatchpoint_Req addWatchpoint_Req = new AddWatchpoint_Req((GeneratedMessage.Builder)this);
        final int bitField0_ = this.bitField0_;
        int n = 0;
        if ((bitField0_ & 0x1) == 0x1) {
            n |= 0x1;
        }
        AddWatchpoint_Req.access$32102(addWatchpoint_Req, this.valueId_);
        if ((bitField0_ & 0x2) == 0x2) {
            n |= 0x2;
        }
        AddWatchpoint_Req.access$32202(addWatchpoint_Req, this.toResolveLocation_);
        if ((bitField0_ & 0x4) == 0x4) {
            n |= 0x4;
        }
        AddWatchpoint_Req.access$32302(addWatchpoint_Req, this.read_);
        if ((bitField0_ & 0x8) == 0x8) {
            n |= 0x8;
        }
        AddWatchpoint_Req.access$32402(addWatchpoint_Req, this.write_);
        if ((bitField0_ & 0x10) == 0x10) {
            n |= 0x10;
        }
        AddWatchpoint_Req.access$32502(addWatchpoint_Req, this.condition_);
        AddWatchpoint_Req.access$32602(addWatchpoint_Req, n);
        this.onBuilt();
        return addWatchpoint_Req;
    }
    
    public Builder mergeFrom(final Message message) {
        try {
            if (message instanceof AddWatchpoint_Req) {
                return this.mergeFrom((AddWatchpoint_Req)message);
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        super.mergeFrom(message);
        return this;
    }
    
    public Builder mergeFrom(final AddWatchpoint_Req addWatchpoint_Req) {
        try {
            if (addWatchpoint_Req == AddWatchpoint_Req.getDefaultInstance()) {
                return this;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (addWatchpoint_Req.hasValueId()) {
                this.setValueId(addWatchpoint_Req.getValueId());
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        try {
            if (addWatchpoint_Req.hasToResolveLocation()) {
                this.setToResolveLocation(addWatchpoint_Req.getToResolveLocation());
            }
        }
        catch (NullPointerException ex3) {
            throw b(ex3);
        }
        try {
            if (addWatchpoint_Req.hasRead()) {
                this.setRead(addWatchpoint_Req.getRead());
            }
        }
        catch (NullPointerException ex4) {
            throw b(ex4);
        }
        try {
            if (addWatchpoint_Req.hasWrite()) {
                this.setWrite(addWatchpoint_Req.getWrite());
            }
        }
        catch (NullPointerException ex5) {
            throw b(ex5);
        }
        try {
            if (addWatchpoint_Req.hasCondition()) {
                this.bitField0_ |= 0x10;
                this.condition_ = AddWatchpoint_Req.access$32500(addWatchpoint_Req);
                this.onChanged();
            }
        }
        catch (NullPointerException ex6) {
            throw b(ex6);
        }
        this.mergeUnknownFields(addWatchpoint_Req.getUnknownFields());
        return this;
    }
    
    public final boolean isInitialized() {
        try {
            if (!this.hasValueId()) {
                return false;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (!this.hasToResolveLocation()) {
                return false;
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        try {
            if (!this.hasRead()) {
                return false;
            }
        }
        catch (NullPointerException ex3) {
            throw b(ex3);
        }
        try {
            if (!this.hasWrite()) {
                return false;
            }
        }
        catch (NullPointerException ex4) {
            throw b(ex4);
        }
        return true;
    }
    
    public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        AddWatchpoint_Req addWatchpoint_Req = null;
        try {
            addWatchpoint_Req = (AddWatchpoint_Req)AddWatchpoint_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
        }
        catch (InvalidProtocolBufferException ex) {
            addWatchpoint_Req = (AddWatchpoint_Req)ex.getUnfinishedMessage();
            throw ex;
        }
        finally {
            try {
                if (addWatchpoint_Req != null) {
                    this.mergeFrom(addWatchpoint_Req);
                }
            }
            catch (InvalidProtocolBufferException ex2) {
                throw b((Exception)ex2);
            }
        }
        return this;
    }
    
    public boolean hasValueId() {
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
    
    public int getValueId() {
        return this.valueId_;
    }
    
    public Builder setValueId(final int valueId_) {
        this.bitField0_ |= 0x1;
        this.valueId_ = valueId_;
        this.onChanged();
        return this;
    }
    
    public Builder clearValueId() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.valueId_ = 0;
        this.onChanged();
        return this;
    }
    
    public boolean hasToResolveLocation() {
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
    
    public int getToResolveLocation() {
        return this.toResolveLocation_;
    }
    
    public Builder setToResolveLocation(final int toResolveLocation_) {
        this.bitField0_ |= 0x2;
        this.toResolveLocation_ = toResolveLocation_;
        this.onChanged();
        return this;
    }
    
    public Builder clearToResolveLocation() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.toResolveLocation_ = 0;
        this.onChanged();
        return this;
    }
    
    public boolean hasRead() {
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
    
    public int getRead() {
        return this.read_;
    }
    
    public Builder setRead(final int read_) {
        this.bitField0_ |= 0x4;
        this.read_ = read_;
        this.onChanged();
        return this;
    }
    
    public Builder clearRead() {
        this.bitField0_ &= 0xFFFFFFFB;
        this.read_ = 0;
        this.onChanged();
        return this;
    }
    
    public boolean hasWrite() {
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
    
    public int getWrite() {
        return this.write_;
    }
    
    public Builder setWrite(final int write_) {
        this.bitField0_ |= 0x8;
        this.write_ = write_;
        this.onChanged();
        return this;
    }
    
    public Builder clearWrite() {
        this.bitField0_ &= 0xFFFFFFF7;
        this.write_ = 0;
        this.onChanged();
        return this;
    }
    
    public boolean hasCondition() {
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
    
    public String getCondition() {
        final Object condition_ = this.condition_;
        if (!(condition_ instanceof String)) {
            return (String)(this.condition_ = ((ByteString)condition_).toStringUtf8());
        }
        return (String)condition_;
    }
    
    public ByteString getConditionBytes() {
        final Object condition_ = this.condition_;
        if (condition_ instanceof String) {
            return (ByteString)(this.condition_ = ByteString.copyFromUtf8((String)condition_));
        }
        return (ByteString)condition_;
    }
    
    public Builder setCondition(final String condition_) {
        try {
            if (condition_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x10;
        this.condition_ = condition_;
        this.onChanged();
        return this;
    }
    
    public Builder clearCondition() {
        this.bitField0_ &= 0xFFFFFFEF;
        this.condition_ = AddWatchpoint_Req.getDefaultInstance().getCondition();
        this.onChanged();
        return this;
    }
    
    public Builder setConditionBytes(final ByteString condition_) {
        try {
            if (condition_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x10;
        this.condition_ = condition_;
        this.onChanged();
        return this;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
