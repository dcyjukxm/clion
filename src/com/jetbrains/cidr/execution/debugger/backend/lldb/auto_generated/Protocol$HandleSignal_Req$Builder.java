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

public static final class Builder extends GeneratedMessage.Builder<Builder> implements HandleSignal_ReqOrBuilder
{
    private int bitField0_;
    private Object signal_;
    private boolean stop_;
    private boolean pass_;
    private boolean notify_;
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Protocol.access$60800();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$60900().ensureFieldAccessorsInitialized((Class)HandleSignal_Req.class, (Class)Builder.class);
    }
    
    private Builder() {
        this.signal_ = "";
        this.a();
    }
    
    private Builder(final GeneratedMessage.BuilderParent builderParent) {
        super(builderParent);
        this.signal_ = "";
        this.a();
    }
    
    private void a() {
        if (HandleSignal_Req.access$61300()) {}
    }
    
    private static Builder b() {
        return new Builder();
    }
    
    public Builder clear() {
        super.clear();
        this.signal_ = "";
        this.bitField0_ &= 0xFFFFFFFE;
        this.stop_ = false;
        this.bitField0_ &= 0xFFFFFFFD;
        this.pass_ = false;
        this.bitField0_ &= 0xFFFFFFFB;
        this.notify_ = false;
        this.bitField0_ &= 0xFFFFFFF7;
        return this;
    }
    
    public Builder clone() {
        return b().mergeFrom(this.buildPartial());
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
        return Protocol.access$60800();
    }
    
    public HandleSignal_Req getDefaultInstanceForType() {
        return HandleSignal_Req.getDefaultInstance();
    }
    
    public HandleSignal_Req build() {
        final HandleSignal_Req buildPartial = this.buildPartial();
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
    
    public HandleSignal_Req buildPartial() {
        final HandleSignal_Req handleSignal_Req = new HandleSignal_Req((GeneratedMessage.Builder)this);
        final int bitField0_ = this.bitField0_;
        int n = 0;
        if ((bitField0_ & 0x1) == 0x1) {
            n |= 0x1;
        }
        HandleSignal_Req.access$61502(handleSignal_Req, this.signal_);
        if ((bitField0_ & 0x2) == 0x2) {
            n |= 0x2;
        }
        HandleSignal_Req.access$61602(handleSignal_Req, this.stop_);
        if ((bitField0_ & 0x4) == 0x4) {
            n |= 0x4;
        }
        HandleSignal_Req.access$61702(handleSignal_Req, this.pass_);
        if ((bitField0_ & 0x8) == 0x8) {
            n |= 0x8;
        }
        HandleSignal_Req.access$61802(handleSignal_Req, this.notify_);
        HandleSignal_Req.access$61902(handleSignal_Req, n);
        this.onBuilt();
        return handleSignal_Req;
    }
    
    public Builder mergeFrom(final Message message) {
        try {
            if (message instanceof HandleSignal_Req) {
                return this.mergeFrom((HandleSignal_Req)message);
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        super.mergeFrom(message);
        return this;
    }
    
    public Builder mergeFrom(final HandleSignal_Req handleSignal_Req) {
        try {
            if (handleSignal_Req == HandleSignal_Req.getDefaultInstance()) {
                return this;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (handleSignal_Req.hasSignal()) {
                this.bitField0_ |= 0x1;
                this.signal_ = HandleSignal_Req.access$61500(handleSignal_Req);
                this.onChanged();
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        try {
            if (handleSignal_Req.hasStop()) {
                this.setStop(handleSignal_Req.getStop());
            }
        }
        catch (NullPointerException ex3) {
            throw b(ex3);
        }
        try {
            if (handleSignal_Req.hasPass()) {
                this.setPass(handleSignal_Req.getPass());
            }
        }
        catch (NullPointerException ex4) {
            throw b(ex4);
        }
        try {
            if (handleSignal_Req.hasNotify()) {
                this.setNotify(handleSignal_Req.getNotify());
            }
        }
        catch (NullPointerException ex5) {
            throw b(ex5);
        }
        this.mergeUnknownFields(handleSignal_Req.getUnknownFields());
        return this;
    }
    
    public final boolean isInitialized() {
        try {
            if (!this.hasSignal()) {
                return false;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (!this.hasStop()) {
                return false;
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        try {
            if (!this.hasPass()) {
                return false;
            }
        }
        catch (NullPointerException ex3) {
            throw b(ex3);
        }
        try {
            if (!this.hasNotify()) {
                return false;
            }
        }
        catch (NullPointerException ex4) {
            throw b(ex4);
        }
        return true;
    }
    
    public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        HandleSignal_Req handleSignal_Req = null;
        try {
            handleSignal_Req = (HandleSignal_Req)HandleSignal_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
        }
        catch (InvalidProtocolBufferException ex) {
            handleSignal_Req = (HandleSignal_Req)ex.getUnfinishedMessage();
            throw ex;
        }
        finally {
            try {
                if (handleSignal_Req != null) {
                    this.mergeFrom(handleSignal_Req);
                }
            }
            catch (InvalidProtocolBufferException ex2) {
                throw b((Exception)ex2);
            }
        }
        return this;
    }
    
    public boolean hasSignal() {
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
    
    public String getSignal() {
        final Object signal_ = this.signal_;
        if (!(signal_ instanceof String)) {
            return (String)(this.signal_ = ((ByteString)signal_).toStringUtf8());
        }
        return (String)signal_;
    }
    
    public ByteString getSignalBytes() {
        final Object signal_ = this.signal_;
        if (signal_ instanceof String) {
            return (ByteString)(this.signal_ = ByteString.copyFromUtf8((String)signal_));
        }
        return (ByteString)signal_;
    }
    
    public Builder setSignal(final String signal_) {
        try {
            if (signal_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x1;
        this.signal_ = signal_;
        this.onChanged();
        return this;
    }
    
    public Builder clearSignal() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.signal_ = HandleSignal_Req.getDefaultInstance().getSignal();
        this.onChanged();
        return this;
    }
    
    public Builder setSignalBytes(final ByteString signal_) {
        try {
            if (signal_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x1;
        this.signal_ = signal_;
        this.onChanged();
        return this;
    }
    
    public boolean hasStop() {
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
    
    public boolean getStop() {
        return this.stop_;
    }
    
    public Builder setStop(final boolean stop_) {
        this.bitField0_ |= 0x2;
        this.stop_ = stop_;
        this.onChanged();
        return this;
    }
    
    public Builder clearStop() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.stop_ = false;
        this.onChanged();
        return this;
    }
    
    public boolean hasPass() {
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
    
    public boolean getPass() {
        return this.pass_;
    }
    
    public Builder setPass(final boolean pass_) {
        this.bitField0_ |= 0x4;
        this.pass_ = pass_;
        this.onChanged();
        return this;
    }
    
    public Builder clearPass() {
        this.bitField0_ &= 0xFFFFFFFB;
        this.pass_ = false;
        this.onChanged();
        return this;
    }
    
    public boolean hasNotify() {
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
    
    public boolean getNotify() {
        return this.notify_;
    }
    
    public Builder setNotify(final boolean notify_) {
        this.bitField0_ |= 0x8;
        this.notify_ = notify_;
        this.onChanged();
        return this;
    }
    
    public Builder clearNotify() {
        this.bitField0_ &= 0xFFFFFFF7;
        this.notify_ = false;
        this.onChanged();
        return this;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
