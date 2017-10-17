// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.SingleFieldBuilder;
import java.util.Collection;
import com.google.protobuf.RepeatedFieldBuilder;
import com.google.protobuf.UnmodifiableLazyStringList;
import java.util.Collections;
import com.google.protobuf.LazyStringArrayList;
import java.util.ArrayList;
import com.google.protobuf.LazyStringList;
import java.util.List;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractMessage;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.Message;
import java.io.InputStream;
import com.google.protobuf.ByteString;
import java.io.ObjectStreamException;
import com.google.protobuf.CodedOutputStream;
import java.io.IOException;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.Internal;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.Descriptors;

public final class Model
{
    private static Descriptors.Descriptor internal_static_Initialized_Message_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_Initialized_Message_fieldAccessorTable;
    private static Descriptors.Descriptor internal_static_EnvParam_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_EnvParam_fieldAccessorTable;
    private static Descriptors.Descriptor internal_static_CommandLine_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_CommandLine_fieldAccessorTable;
    private static Descriptors.Descriptor internal_static_ThreadStopReasonInfo_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_ThreadStopReasonInfo_fieldAccessorTable;
    private static Descriptors.Descriptor internal_static_LLDBThread_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_LLDBThread_fieldAccessorTable;
    private static Descriptors.Descriptor internal_static_LLDBFrame_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_LLDBFrame_fieldAccessorTable;
    private static Descriptors.Descriptor internal_static_LLDBValue_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_LLDBValue_fieldAccessorTable;
    private static Descriptors.Descriptor internal_static_LLDBValueData_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_LLDBValueData_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor;
    
    public static void registerAllExtensions(final ExtensionRegistry extensionRegistry) {
    }
    
    public static Descriptors.FileDescriptor getDescriptor() {
        return Model.descriptor;
    }
    
    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[] { "\n\u000bmodel.proto\"\u0015\n\u0013Initialized_Message\"'\n\bEnvParam\u0012\f\n\u0004name\u0018\u0001 \u0002(\t\u0012\r\n\u0005value\u0018\u0002 \u0002(\t\"\u0099\u0001\n\u000bCommandLine\u0012\u0016\n\u0003env\u0018\u0001 \u0003(\u000b2\t.EnvParam\u0012\u0010\n\bexe_path\u0018\u0002 \u0002(\t\u0012\u0013\n\u000bworking_dir\u0018\u0003 \u0002(\t\u0012\r\n\u0005param\u0018\u0004 \u0003(\t\u0012\u0012\n\nstdin_path\u0018\u0005 \u0001(\t\u0012\u0013\n\u000bstdout_path\u0018\u0006 \u0001(\t\u0012\u0013\n\u000bstderr_path\u0018\u0007 \u0001(\t\"\u0093\u0001\n\u0014ThreadStopReasonInfo\u0012&\n\u000bstop_reason\u0018\u0001 \u0002(\u000e2\u0011.ThreadStopReason\u0012\u0018\n\u0010stop_description\u0018\u0002 \u0002(\t\u0012\u000e\n\u0006signal\u0018\u0003 \u0001(\u0005\u0012\u0013\n\u000bsignal_name\u0018\u0004 \u0001(\t\u0012\u0014\n\fcodepoint_id\u0018\u0005 \u0001(\u0005\"f\n\nLLDBThread\u0012\n\n", "\u0002id\u0018\u0001 \u0002(\r\u0012\f\n\u0004name\u0018\u0002 \u0001(\t\u0012\r\n\u0005queue\u0018\u0003 \u0001(\t\u0012/\n\u0010stop_reason_info\u0018\u0004 \u0001(\u000b2\u0015.ThreadStopReasonInfo\"\u0085\u0001\n\tLLDBFrame\u0012\u000e\n\u0006number\u0018\u0001 \u0002(\u0005\u0012\u0010\n\bfunction\u0018\u0002 \u0001(\t\u0012\f\n\u0004file\u0018\u0003 \u0001(\t\u0012\f\n\u0004line\u0018\u0004 \u0001(\u0005\u0012\n\n\u0002pc\u0018\u0005 \u0001(\u0003\u0012\u001b\n\blanguage\u0018\u0006 \u0001(\u000e2\t.Language\u0012\u0011\n\toptimized\u0018\u0007 \u0001(\b\"S\n\tLLDBValue\u0012\n\n\u0002id\u0018\u0001 \u0002(\u0005\u0012\f\n\u0004name\u0018\u0002 \u0002(\t\u0012\f\n\u0004type\u0018\u0003 \u0002(\t\u0012\u001e\n\ntype_class\u0018\u0004 \u0002(\u000e2\n.TypeClass\"\u0084\u0001\n\rLLDBValueData\u0012\r\n\u0005value\u0018\u0001 \u0002(\t\u0012\u0013\n\u000bdescription\u0018\u0002 \u0001(\t\u0012\u001e\n\u0016has_longer_description\u0018\u0003 \u0001(\b\u0012\u0019\n\u0011ma", "y_have_children\u0018\u0004 \u0002(\b\u0012\u0014\n\fis_synthetic\u0018\u0005 \u0002(\b*8\n\nOutputType\u0012\u0014\n\u0010OutputTypeStdout\u0010\u0000\u0012\u0014\n\u0010OutputTypeStderr\u0010\u0001*G\n\u000eDispatchTarget\u0012\u0019\n\u0015DispatchTargetProcess\u0010\u0000\u0012\u001a\n\u0016DispatchTargetDebugger\u0010\u0001*\u00e3\u0002\n\u0010ThreadStopReason\u0012\u001b\n\u0017ThreadStopReasonInvalid\u0010\u0000\u0012\u0018\n\u0014ThreadStopReasonNone\u0010\u0001\u0012\u0019\n\u0015ThreadStopReasonTrace\u0010\u0002\u0012\u001e\n\u001aThreadStopReasonBreakpoint\u0010\u0003\u0012\u001e\n\u001aThreadStopReasonWatchpoint\u0010\u0004\u0012\u001a\n\u0016ThreadStopReasonSignal\u0010\u0005\u0012\u001d\n\u0019ThreadStopReasonException\u0010\u0006", "\u0012\u0018\n\u0014ThreadStopReasonExec\u0010\u0007\u0012 \n\u001cThreadStopReasonPlanComplete\u0010\b\u0012!\n\u001dThreadStopReasonThreadExiting\u0010\t\u0012#\n\u001fThreadStopReasonInstrumentation\u0010\n*\u00e6\u0003\n\tTypeClass\u0012\u0012\n\u000eTypeClassArray\u0010\u0000\u0012\u0019\n\u0015TypeClassBlockPointer\u0010\u0001\u0012\u0014\n\u0010TypeClassBuiltin\u0010\u0002\u0012\u0012\n\u000eTypeClassClass\u0010\u0003\u0012\u0019\n\u0015TypeClassComplexFloat\u0010\u0004\u0012\u001b\n\u0017TypeClassComplexInteger\u0010\u0005\u0012\u0018\n\u0014TypeClassEnumeration\u0010\u0006\u0012\u0015\n\u0011TypeClassFunction\u0010\u0007\u0012\u001a\n\u0016TypeClassMemberPointer\u0010\b\u0012\u0017\n\u0013TypeClassObjCObject\u0010\t\u0012\u001a\n\u0016Typ", "eClassObjCInterface\u0010\n\u0012\u001e\n\u001aTypeClassObjCObjectPointer\u0010\u000b\u0012\u0014\n\u0010TypeClassPointer\u0010\f\u0012\u0016\n\u0012TypeClassReference\u0010\r\u0012\u0013\n\u000fTypeClassStruct\u0010\u000e\u0012\u0014\n\u0010TypeClassTypedef\u0010\u000f\u0012\u0012\n\u000eTypeClassUnion\u0010\u0010\u0012\u0013\n\u000fTypeClassVector\u0010\u0011\u0012\u0012\n\u000eTypeClassOther\u0010\u0012\u0012\u0010\n\fTypeClassAny\u0010\u0013*¾\u0006\n\bLanguage\u0012\u0013\n\u000fLanguageUnknown\u0010\u0000\u0012\u000f\n\u000bLanguageC89\u0010\u0001\u0012\r\n\tLanguageC\u0010\u0002\u0012\u0011\n\rLanguageAda83\u0010\u0003\u0012\u0017\n\u0013LanguageC_plus_plus\u0010\u0004\u0012\u0013\n\u000fLanguageCobol74\u0010\u0005\u0012\u0013\n\u000fLanguageCobol85\u0010\u0006\u0012\u0015\n\u0011LanguageFortran77\u0010\u0007\u0012\u0015\n\u0011", "LanguageFortran90\u0010\b\u0012\u0014\n\u0010LanguagePascal83\u0010\t\u0012\u0013\n\u000fLanguageModula2\u0010\n\u0012\u0010\n\fLanguageJava\u0010\u000b\u0012\u000f\n\u000bLanguageC99\u0010\f\u0012\u0011\n\rLanguageAda95\u0010\r\u0012\u0015\n\u0011LanguageFortran95\u0010\u000e\u0012\u000f\n\u000bLanguagePLI\u0010\u000f\u0012\u0010\n\fLanguageObjC\u0010\u0010\u0012\u001a\n\u0016LanguageObjC_plus_plus\u0010\u0011\u0012\u000f\n\u000bLanguageUPC\u0010\u0012\u0012\r\n\tLanguageD\u0010\u0013\u0012\u0012\n\u000eLanguagePython\u0010\u0014\u0012\u0012\n\u000eLanguageOpenCL\u0010\u0015\u0012\u000e\n\nLanguageGo\u0010\u0016\u0012\u0013\n\u000fLanguageModula3\u0010\u0017\u0012\u0013\n\u000fLanguageHaskell\u0010\u0018\u0012\u001a\n\u0016LanguageC_plus_plus_03\u0010\u0019\u0012\u001a\n\u0016LanguageC_plus_plus_11\u0010\u001a\u0012\u0011\n\rLanguage", "OCaml\u0010\u001b\u0012\u0010\n\fLanguageRust\u0010\u001c\u0012\u000f\n\u000bLanguageC11\u0010\u001d\u0012\u0011\n\rLanguageSwift\u0010\u001e\u0012\u0011\n\rLanguageJulia\u0010\u001f\u0012\u0011\n\rLanguageDylan\u0010 \u0012\u001a\n\u0016LanguageC_plus_plus_14\u0010!\u0012\u0015\n\u0011LanguageFortran03\u0010\"\u0012\u0015\n\u0011LanguageFortran08\u0010#\u0012\u0019\n\u0015LanguageMipsAssembler\u0010$\u0012\u001b\n\u0017LanguageExtRenderScript\u0010%\u0012\u0017\n\u0013UnsupportedLanguage\u0010&BC\nAcom.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated" }, new Descriptors.FileDescriptor[0], (Descriptors.FileDescriptor.InternalDescriptorAssigner)new Descriptors.FileDescriptor.InternalDescriptorAssigner() {
            public ExtensionRegistry assignDescriptors(final Descriptors.FileDescriptor fileDescriptor) {
                Model.descriptor = fileDescriptor;
                Model.internal_static_Initialized_Message_descriptor = Model.getDescriptor().getMessageTypes().get(0);
                Model.internal_static_Initialized_Message_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(Model.internal_static_Initialized_Message_descriptor, new String[0]);
                Model.internal_static_EnvParam_descriptor = Model.getDescriptor().getMessageTypes().get(1);
                Model.internal_static_EnvParam_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(Model.internal_static_EnvParam_descriptor, new String[] { "Name", "Value" });
                Model.internal_static_CommandLine_descriptor = Model.getDescriptor().getMessageTypes().get(2);
                Model.internal_static_CommandLine_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(Model.internal_static_CommandLine_descriptor, new String[] { "Env", "ExePath", "WorkingDir", "Param", "StdinPath", "StdoutPath", "StderrPath" });
                Model.internal_static_ThreadStopReasonInfo_descriptor = Model.getDescriptor().getMessageTypes().get(3);
                Model.internal_static_ThreadStopReasonInfo_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(Model.internal_static_ThreadStopReasonInfo_descriptor, new String[] { "StopReason", "StopDescription", "Signal", "SignalName", "CodepointId" });
                Model.internal_static_LLDBThread_descriptor = Model.getDescriptor().getMessageTypes().get(4);
                Model.internal_static_LLDBThread_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(Model.internal_static_LLDBThread_descriptor, new String[] { "Id", "Name", "Queue", "StopReasonInfo" });
                Model.internal_static_LLDBFrame_descriptor = Model.getDescriptor().getMessageTypes().get(5);
                Model.internal_static_LLDBFrame_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(Model.internal_static_LLDBFrame_descriptor, new String[] { "Number", "Function", "File", "Line", "Pc", "Language", "Optimized" });
                Model.internal_static_LLDBValue_descriptor = Model.getDescriptor().getMessageTypes().get(6);
                Model.internal_static_LLDBValue_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(Model.internal_static_LLDBValue_descriptor, new String[] { "Id", "Name", "Type", "TypeClass" });
                Model.internal_static_LLDBValueData_descriptor = Model.getDescriptor().getMessageTypes().get(7);
                Model.internal_static_LLDBValueData_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(Model.internal_static_LLDBValueData_descriptor, new String[] { "Value", "Description", "HasLongerDescription", "MayHaveChildren", "IsSynthetic" });
                return null;
            }
        });
    }
    
    public enum OutputType implements ProtocolMessageEnum
    {
        OutputTypeStdout(0, 0), 
        OutputTypeStderr(1, 1);
        
        public static final int OutputTypeStdout_VALUE = 0;
        public static final int OutputTypeStderr_VALUE = 1;
        private static Internal.EnumLiteMap<OutputType> internalValueMap;
        private static final OutputType[] VALUES;
        private final int index;
        private final int value;
        
        public final int getNumber() {
            return this.value;
        }
        
        public static OutputType valueOf(final int n) {
            try {
                switch (n) {
                    case 0: {
                        return OutputType.OutputTypeStdout;
                    }
                    case 1: {
                        break;
                    }
                    default: {
                        return null;
                    }
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return OutputType.OutputTypeStderr;
        }
        
        public static Internal.EnumLiteMap<OutputType> internalGetValueMap() {
            return OutputType.internalValueMap;
        }
        
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }
        
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }
        
        public static final Descriptors.EnumDescriptor getDescriptor() {
            return Model.getDescriptor().getEnumTypes().get(0);
        }
        
        public static OutputType valueOf(final Descriptors.EnumValueDescriptor enumValueDescriptor) {
            try {
                if (enumValueDescriptor.getType() != getDescriptor()) {
                    throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return OutputType.VALUES[enumValueDescriptor.getIndex()];
        }
        
        private OutputType(final int index, final int value) {
            this.index = index;
            this.value = value;
        }
        
        static {
            OutputType.internalValueMap = (Internal.EnumLiteMap<OutputType>)new Internal.EnumLiteMap<OutputType>() {
                public OutputType findValueByNumber(final int n) {
                    return OutputType.valueOf(n);
                }
            };
            VALUES = values();
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    public enum DispatchTarget implements ProtocolMessageEnum
    {
        DispatchTargetProcess(0, 0), 
        DispatchTargetDebugger(1, 1);
        
        public static final int DispatchTargetProcess_VALUE = 0;
        public static final int DispatchTargetDebugger_VALUE = 1;
        private static Internal.EnumLiteMap<DispatchTarget> internalValueMap;
        private static final DispatchTarget[] VALUES;
        private final int index;
        private final int value;
        
        public final int getNumber() {
            return this.value;
        }
        
        public static DispatchTarget valueOf(final int n) {
            try {
                switch (n) {
                    case 0: {
                        return DispatchTarget.DispatchTargetProcess;
                    }
                    case 1: {
                        break;
                    }
                    default: {
                        return null;
                    }
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return DispatchTarget.DispatchTargetDebugger;
        }
        
        public static Internal.EnumLiteMap<DispatchTarget> internalGetValueMap() {
            return DispatchTarget.internalValueMap;
        }
        
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }
        
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }
        
        public static final Descriptors.EnumDescriptor getDescriptor() {
            return Model.getDescriptor().getEnumTypes().get(1);
        }
        
        public static DispatchTarget valueOf(final Descriptors.EnumValueDescriptor enumValueDescriptor) {
            try {
                if (enumValueDescriptor.getType() != getDescriptor()) {
                    throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return DispatchTarget.VALUES[enumValueDescriptor.getIndex()];
        }
        
        private DispatchTarget(final int index, final int value) {
            this.index = index;
            this.value = value;
        }
        
        static {
            DispatchTarget.internalValueMap = (Internal.EnumLiteMap<DispatchTarget>)new Internal.EnumLiteMap<DispatchTarget>() {
                public DispatchTarget findValueByNumber(final int n) {
                    return DispatchTarget.valueOf(n);
                }
            };
            VALUES = values();
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    public enum ThreadStopReason implements ProtocolMessageEnum
    {
        ThreadStopReasonInvalid(0, 0), 
        ThreadStopReasonNone(1, 1), 
        ThreadStopReasonTrace(2, 2), 
        ThreadStopReasonBreakpoint(3, 3), 
        ThreadStopReasonWatchpoint(4, 4), 
        ThreadStopReasonSignal(5, 5), 
        ThreadStopReasonException(6, 6), 
        ThreadStopReasonExec(7, 7), 
        ThreadStopReasonPlanComplete(8, 8), 
        ThreadStopReasonThreadExiting(9, 9), 
        ThreadStopReasonInstrumentation(10, 10);
        
        public static final int ThreadStopReasonInvalid_VALUE = 0;
        public static final int ThreadStopReasonNone_VALUE = 1;
        public static final int ThreadStopReasonTrace_VALUE = 2;
        public static final int ThreadStopReasonBreakpoint_VALUE = 3;
        public static final int ThreadStopReasonWatchpoint_VALUE = 4;
        public static final int ThreadStopReasonSignal_VALUE = 5;
        public static final int ThreadStopReasonException_VALUE = 6;
        public static final int ThreadStopReasonExec_VALUE = 7;
        public static final int ThreadStopReasonPlanComplete_VALUE = 8;
        public static final int ThreadStopReasonThreadExiting_VALUE = 9;
        public static final int ThreadStopReasonInstrumentation_VALUE = 10;
        private static Internal.EnumLiteMap<ThreadStopReason> internalValueMap;
        private static final ThreadStopReason[] VALUES;
        private final int index;
        private final int value;
        
        public final int getNumber() {
            return this.value;
        }
        
        public static ThreadStopReason valueOf(final int n) {
            try {
                switch (n) {
                    case 0: {
                        return ThreadStopReason.ThreadStopReasonInvalid;
                    }
                    case 1: {
                        break;
                    }
                    case 2: {
                        return ThreadStopReason.ThreadStopReasonTrace;
                    }
                    case 3: {
                        return ThreadStopReason.ThreadStopReasonBreakpoint;
                    }
                    case 4: {
                        return ThreadStopReason.ThreadStopReasonWatchpoint;
                    }
                    case 5: {
                        return ThreadStopReason.ThreadStopReasonSignal;
                    }
                    case 6: {
                        return ThreadStopReason.ThreadStopReasonException;
                    }
                    case 7: {
                        return ThreadStopReason.ThreadStopReasonExec;
                    }
                    case 8: {
                        return ThreadStopReason.ThreadStopReasonPlanComplete;
                    }
                    case 9: {
                        return ThreadStopReason.ThreadStopReasonThreadExiting;
                    }
                    case 10: {
                        return ThreadStopReason.ThreadStopReasonInstrumentation;
                    }
                    default: {
                        return null;
                    }
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return ThreadStopReason.ThreadStopReasonNone;
        }
        
        public static Internal.EnumLiteMap<ThreadStopReason> internalGetValueMap() {
            return ThreadStopReason.internalValueMap;
        }
        
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }
        
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }
        
        public static final Descriptors.EnumDescriptor getDescriptor() {
            return Model.getDescriptor().getEnumTypes().get(2);
        }
        
        public static ThreadStopReason valueOf(final Descriptors.EnumValueDescriptor enumValueDescriptor) {
            try {
                if (enumValueDescriptor.getType() != getDescriptor()) {
                    throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return ThreadStopReason.VALUES[enumValueDescriptor.getIndex()];
        }
        
        private ThreadStopReason(final int index, final int value) {
            this.index = index;
            this.value = value;
        }
        
        static {
            ThreadStopReason.internalValueMap = (Internal.EnumLiteMap<ThreadStopReason>)new Internal.EnumLiteMap<ThreadStopReason>() {
                public ThreadStopReason findValueByNumber(final int n) {
                    return ThreadStopReason.valueOf(n);
                }
            };
            VALUES = values();
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    public enum TypeClass implements ProtocolMessageEnum
    {
        TypeClassArray(0, 0), 
        TypeClassBlockPointer(1, 1), 
        TypeClassBuiltin(2, 2), 
        TypeClassClass(3, 3), 
        TypeClassComplexFloat(4, 4), 
        TypeClassComplexInteger(5, 5), 
        TypeClassEnumeration(6, 6), 
        TypeClassFunction(7, 7), 
        TypeClassMemberPointer(8, 8), 
        TypeClassObjCObject(9, 9), 
        TypeClassObjCInterface(10, 10), 
        TypeClassObjCObjectPointer(11, 11), 
        TypeClassPointer(12, 12), 
        TypeClassReference(13, 13), 
        TypeClassStruct(14, 14), 
        TypeClassTypedef(15, 15), 
        TypeClassUnion(16, 16), 
        TypeClassVector(17, 17), 
        TypeClassOther(18, 18), 
        TypeClassAny(19, 19);
        
        public static final int TypeClassArray_VALUE = 0;
        public static final int TypeClassBlockPointer_VALUE = 1;
        public static final int TypeClassBuiltin_VALUE = 2;
        public static final int TypeClassClass_VALUE = 3;
        public static final int TypeClassComplexFloat_VALUE = 4;
        public static final int TypeClassComplexInteger_VALUE = 5;
        public static final int TypeClassEnumeration_VALUE = 6;
        public static final int TypeClassFunction_VALUE = 7;
        public static final int TypeClassMemberPointer_VALUE = 8;
        public static final int TypeClassObjCObject_VALUE = 9;
        public static final int TypeClassObjCInterface_VALUE = 10;
        public static final int TypeClassObjCObjectPointer_VALUE = 11;
        public static final int TypeClassPointer_VALUE = 12;
        public static final int TypeClassReference_VALUE = 13;
        public static final int TypeClassStruct_VALUE = 14;
        public static final int TypeClassTypedef_VALUE = 15;
        public static final int TypeClassUnion_VALUE = 16;
        public static final int TypeClassVector_VALUE = 17;
        public static final int TypeClassOther_VALUE = 18;
        public static final int TypeClassAny_VALUE = 19;
        private static Internal.EnumLiteMap<TypeClass> internalValueMap;
        private static final TypeClass[] VALUES;
        private final int index;
        private final int value;
        
        public final int getNumber() {
            return this.value;
        }
        
        public static TypeClass valueOf(final int n) {
            try {
                switch (n) {
                    case 0: {
                        return TypeClass.TypeClassArray;
                    }
                    case 1: {
                        break;
                    }
                    case 2: {
                        return TypeClass.TypeClassBuiltin;
                    }
                    case 3: {
                        return TypeClass.TypeClassClass;
                    }
                    case 4: {
                        return TypeClass.TypeClassComplexFloat;
                    }
                    case 5: {
                        return TypeClass.TypeClassComplexInteger;
                    }
                    case 6: {
                        return TypeClass.TypeClassEnumeration;
                    }
                    case 7: {
                        return TypeClass.TypeClassFunction;
                    }
                    case 8: {
                        return TypeClass.TypeClassMemberPointer;
                    }
                    case 9: {
                        return TypeClass.TypeClassObjCObject;
                    }
                    case 10: {
                        return TypeClass.TypeClassObjCInterface;
                    }
                    case 11: {
                        return TypeClass.TypeClassObjCObjectPointer;
                    }
                    case 12: {
                        return TypeClass.TypeClassPointer;
                    }
                    case 13: {
                        return TypeClass.TypeClassReference;
                    }
                    case 14: {
                        return TypeClass.TypeClassStruct;
                    }
                    case 15: {
                        return TypeClass.TypeClassTypedef;
                    }
                    case 16: {
                        return TypeClass.TypeClassUnion;
                    }
                    case 17: {
                        return TypeClass.TypeClassVector;
                    }
                    case 18: {
                        return TypeClass.TypeClassOther;
                    }
                    case 19: {
                        return TypeClass.TypeClassAny;
                    }
                    default: {
                        return null;
                    }
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return TypeClass.TypeClassBlockPointer;
        }
        
        public static Internal.EnumLiteMap<TypeClass> internalGetValueMap() {
            return TypeClass.internalValueMap;
        }
        
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }
        
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }
        
        public static final Descriptors.EnumDescriptor getDescriptor() {
            return Model.getDescriptor().getEnumTypes().get(3);
        }
        
        public static TypeClass valueOf(final Descriptors.EnumValueDescriptor enumValueDescriptor) {
            try {
                if (enumValueDescriptor.getType() != getDescriptor()) {
                    throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return TypeClass.VALUES[enumValueDescriptor.getIndex()];
        }
        
        private TypeClass(final int index, final int value) {
            this.index = index;
            this.value = value;
        }
        
        static {
            TypeClass.internalValueMap = (Internal.EnumLiteMap<TypeClass>)new Internal.EnumLiteMap<TypeClass>() {
                public TypeClass findValueByNumber(final int n) {
                    return TypeClass.valueOf(n);
                }
            };
            VALUES = values();
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    public enum Language implements ProtocolMessageEnum
    {
        LanguageUnknown(0, 0), 
        LanguageC89(1, 1), 
        LanguageC(2, 2), 
        LanguageAda83(3, 3), 
        LanguageC_plus_plus(4, 4), 
        LanguageCobol74(5, 5), 
        LanguageCobol85(6, 6), 
        LanguageFortran77(7, 7), 
        LanguageFortran90(8, 8), 
        LanguagePascal83(9, 9), 
        LanguageModula2(10, 10), 
        LanguageJava(11, 11), 
        LanguageC99(12, 12), 
        LanguageAda95(13, 13), 
        LanguageFortran95(14, 14), 
        LanguagePLI(15, 15), 
        LanguageObjC(16, 16), 
        LanguageObjC_plus_plus(17, 17), 
        LanguageUPC(18, 18), 
        LanguageD(19, 19), 
        LanguagePython(20, 20), 
        LanguageOpenCL(21, 21), 
        LanguageGo(22, 22), 
        LanguageModula3(23, 23), 
        LanguageHaskell(24, 24), 
        LanguageC_plus_plus_03(25, 25), 
        LanguageC_plus_plus_11(26, 26), 
        LanguageOCaml(27, 27), 
        LanguageRust(28, 28), 
        LanguageC11(29, 29), 
        LanguageSwift(30, 30), 
        LanguageJulia(31, 31), 
        LanguageDylan(32, 32), 
        LanguageC_plus_plus_14(33, 33), 
        LanguageFortran03(34, 34), 
        LanguageFortran08(35, 35), 
        LanguageMipsAssembler(36, 36), 
        LanguageExtRenderScript(37, 37), 
        UnsupportedLanguage(38, 38);
        
        public static final int LanguageUnknown_VALUE = 0;
        public static final int LanguageC89_VALUE = 1;
        public static final int LanguageC_VALUE = 2;
        public static final int LanguageAda83_VALUE = 3;
        public static final int LanguageC_plus_plus_VALUE = 4;
        public static final int LanguageCobol74_VALUE = 5;
        public static final int LanguageCobol85_VALUE = 6;
        public static final int LanguageFortran77_VALUE = 7;
        public static final int LanguageFortran90_VALUE = 8;
        public static final int LanguagePascal83_VALUE = 9;
        public static final int LanguageModula2_VALUE = 10;
        public static final int LanguageJava_VALUE = 11;
        public static final int LanguageC99_VALUE = 12;
        public static final int LanguageAda95_VALUE = 13;
        public static final int LanguageFortran95_VALUE = 14;
        public static final int LanguagePLI_VALUE = 15;
        public static final int LanguageObjC_VALUE = 16;
        public static final int LanguageObjC_plus_plus_VALUE = 17;
        public static final int LanguageUPC_VALUE = 18;
        public static final int LanguageD_VALUE = 19;
        public static final int LanguagePython_VALUE = 20;
        public static final int LanguageOpenCL_VALUE = 21;
        public static final int LanguageGo_VALUE = 22;
        public static final int LanguageModula3_VALUE = 23;
        public static final int LanguageHaskell_VALUE = 24;
        public static final int LanguageC_plus_plus_03_VALUE = 25;
        public static final int LanguageC_plus_plus_11_VALUE = 26;
        public static final int LanguageOCaml_VALUE = 27;
        public static final int LanguageRust_VALUE = 28;
        public static final int LanguageC11_VALUE = 29;
        public static final int LanguageSwift_VALUE = 30;
        public static final int LanguageJulia_VALUE = 31;
        public static final int LanguageDylan_VALUE = 32;
        public static final int LanguageC_plus_plus_14_VALUE = 33;
        public static final int LanguageFortran03_VALUE = 34;
        public static final int LanguageFortran08_VALUE = 35;
        public static final int LanguageMipsAssembler_VALUE = 36;
        public static final int LanguageExtRenderScript_VALUE = 37;
        public static final int UnsupportedLanguage_VALUE = 38;
        private static Internal.EnumLiteMap<Language> internalValueMap;
        private static final Language[] VALUES;
        private final int index;
        private final int value;
        
        public final int getNumber() {
            return this.value;
        }
        
        public static Language valueOf(final int n) {
            try {
                switch (n) {
                    case 0: {
                        return Language.LanguageUnknown;
                    }
                    case 1: {
                        break;
                    }
                    case 2: {
                        return Language.LanguageC;
                    }
                    case 3: {
                        return Language.LanguageAda83;
                    }
                    case 4: {
                        return Language.LanguageC_plus_plus;
                    }
                    case 5: {
                        return Language.LanguageCobol74;
                    }
                    case 6: {
                        return Language.LanguageCobol85;
                    }
                    case 7: {
                        return Language.LanguageFortran77;
                    }
                    case 8: {
                        return Language.LanguageFortran90;
                    }
                    case 9: {
                        return Language.LanguagePascal83;
                    }
                    case 10: {
                        return Language.LanguageModula2;
                    }
                    case 11: {
                        return Language.LanguageJava;
                    }
                    case 12: {
                        return Language.LanguageC99;
                    }
                    case 13: {
                        return Language.LanguageAda95;
                    }
                    case 14: {
                        return Language.LanguageFortran95;
                    }
                    case 15: {
                        return Language.LanguagePLI;
                    }
                    case 16: {
                        return Language.LanguageObjC;
                    }
                    case 17: {
                        return Language.LanguageObjC_plus_plus;
                    }
                    case 18: {
                        return Language.LanguageUPC;
                    }
                    case 19: {
                        return Language.LanguageD;
                    }
                    case 20: {
                        return Language.LanguagePython;
                    }
                    case 21: {
                        return Language.LanguageOpenCL;
                    }
                    case 22: {
                        return Language.LanguageGo;
                    }
                    case 23: {
                        return Language.LanguageModula3;
                    }
                    case 24: {
                        return Language.LanguageHaskell;
                    }
                    case 25: {
                        return Language.LanguageC_plus_plus_03;
                    }
                    case 26: {
                        return Language.LanguageC_plus_plus_11;
                    }
                    case 27: {
                        return Language.LanguageOCaml;
                    }
                    case 28: {
                        return Language.LanguageRust;
                    }
                    case 29: {
                        return Language.LanguageC11;
                    }
                    case 30: {
                        return Language.LanguageSwift;
                    }
                    case 31: {
                        return Language.LanguageJulia;
                    }
                    case 32: {
                        return Language.LanguageDylan;
                    }
                    case 33: {
                        return Language.LanguageC_plus_plus_14;
                    }
                    case 34: {
                        return Language.LanguageFortran03;
                    }
                    case 35: {
                        return Language.LanguageFortran08;
                    }
                    case 36: {
                        return Language.LanguageMipsAssembler;
                    }
                    case 37: {
                        return Language.LanguageExtRenderScript;
                    }
                    case 38: {
                        return Language.UnsupportedLanguage;
                    }
                    default: {
                        return null;
                    }
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return Language.LanguageC89;
        }
        
        public static Internal.EnumLiteMap<Language> internalGetValueMap() {
            return Language.internalValueMap;
        }
        
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }
        
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }
        
        public static final Descriptors.EnumDescriptor getDescriptor() {
            return Model.getDescriptor().getEnumTypes().get(4);
        }
        
        public static Language valueOf(final Descriptors.EnumValueDescriptor enumValueDescriptor) {
            try {
                if (enumValueDescriptor.getType() != getDescriptor()) {
                    throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return Language.VALUES[enumValueDescriptor.getIndex()];
        }
        
        private Language(final int index, final int value) {
            this.index = index;
            this.value = value;
        }
        
        static {
            Language.internalValueMap = (Internal.EnumLiteMap<Language>)new Internal.EnumLiteMap<Language>() {
                public Language findValueByNumber(final int n) {
                    return Language.valueOf(n);
                }
            };
            VALUES = values();
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    public static final class Initialized_Message extends GeneratedMessage implements Initialized_MessageOrBuilder
    {
        private static final Initialized_Message defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<Initialized_Message> PARSER;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private static final long serialVersionUID = 0L;
        
        private Initialized_Message(final GeneratedMessage.Builder<?> builder) {
            super((GeneratedMessage.Builder)builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }
        
        private Initialized_Message(final boolean b) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }
        
        public static Initialized_Message getDefaultInstance() {
            return Initialized_Message.defaultInstance;
        }
        
        public Initialized_Message getDefaultInstanceForType() {
            return Initialized_Message.defaultInstance;
        }
        
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }
        
        private Initialized_Message(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.a();
            final UnknownFieldSet.Builder builder = UnknownFieldSet.newBuilder();
            try {
                int i = 0;
                while (i == 0) {
                    final int tag = codedInputStream.readTag();
                    switch (tag) {
                        case 0: {
                            i = 1;
                            continue;
                        }
                        default: {
                            if (!this.parseUnknownField(codedInputStream, builder, extensionRegistryLite, tag)) {
                                i = 1;
                                continue;
                            }
                            continue;
                        }
                    }
                }
            }
            catch (InvalidProtocolBufferException ex) {
                throw ex.setUnfinishedMessage((MessageLite)this);
            }
            catch (IOException ex2) {
                throw new InvalidProtocolBufferException(ex2.getMessage()).setUnfinishedMessage((MessageLite)this);
            }
            finally {
                this.unknownFields = builder.build();
                this.makeExtensionsImmutable();
            }
        }
        
        public static final Descriptors.Descriptor getDescriptor() {
            return Model.internal_static_Initialized_Message_descriptor;
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Model.internal_static_Initialized_Message_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)Initialized_Message.class, (Class)Builder.class);
        }
        
        public Parser<Initialized_Message> getParserForType() {
            return Initialized_Message.PARSER;
        }
        
        private void a() {
        }
        
        public final boolean isInitialized() {
            final byte memoizedIsInitialized = this.memoizedIsInitialized;
            if (memoizedIsInitialized != -1) {
                return memoizedIsInitialized == 1;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }
        
        public void writeTo(final CodedOutputStream codedOutputStream) throws IOException {
            this.getSerializedSize();
            this.getUnknownFields().writeTo(codedOutputStream);
        }
        
        public int getSerializedSize() {
            final int memoizedSerializedSize = this.memoizedSerializedSize;
            if (memoizedSerializedSize != -1) {
                return memoizedSerializedSize;
            }
            return this.memoizedSerializedSize = 0 + this.getUnknownFields().getSerializedSize();
        }
        
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }
        
        public static Initialized_Message parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
            return (Initialized_Message)Initialized_Message.PARSER.parseFrom(byteString);
        }
        
        public static Initialized_Message parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Initialized_Message)Initialized_Message.PARSER.parseFrom(byteString, extensionRegistryLite);
        }
        
        public static Initialized_Message parseFrom(final byte[] array) throws InvalidProtocolBufferException {
            return (Initialized_Message)Initialized_Message.PARSER.parseFrom(array);
        }
        
        public static Initialized_Message parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Initialized_Message)Initialized_Message.PARSER.parseFrom(array, extensionRegistryLite);
        }
        
        public static Initialized_Message parseFrom(final InputStream inputStream) throws IOException {
            return (Initialized_Message)Initialized_Message.PARSER.parseFrom(inputStream);
        }
        
        public static Initialized_Message parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Initialized_Message)Initialized_Message.PARSER.parseFrom(inputStream, extensionRegistryLite);
        }
        
        public static Initialized_Message parseDelimitedFrom(final InputStream inputStream) throws IOException {
            return (Initialized_Message)Initialized_Message.PARSER.parseDelimitedFrom(inputStream);
        }
        
        public static Initialized_Message parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Initialized_Message)Initialized_Message.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }
        
        public static Initialized_Message parseFrom(final CodedInputStream codedInputStream) throws IOException {
            return (Initialized_Message)Initialized_Message.PARSER.parseFrom(codedInputStream);
        }
        
        public static Initialized_Message parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Initialized_Message)Initialized_Message.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }
        
        public static Builder newBuilder() {
            return a();
        }
        
        public Builder newBuilderForType() {
            return newBuilder();
        }
        
        public static Builder newBuilder(final Initialized_Message initialized_Message) {
            return newBuilder().mergeFrom(initialized_Message);
        }
        
        public Builder toBuilder() {
            return newBuilder(this);
        }
        
        protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
            return new Builder(builderParent);
        }
        
        static {
            Initialized_Message.PARSER = (Parser<Initialized_Message>)new AbstractParser<Initialized_Message>() {
                public Initialized_Message parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new Initialized_Message(codedInputStream, extensionRegistryLite);
                }
            };
            (defaultInstance = new Initialized_Message(true)).a();
        }
        
        public static final class Builder extends GeneratedMessage.Builder<Builder> implements Initialized_MessageOrBuilder
        {
            public static final Descriptors.Descriptor getDescriptor() {
                return Model.internal_static_Initialized_Message_descriptor;
            }
            
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return Model.internal_static_Initialized_Message_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)Initialized_Message.class, (Class)Builder.class);
            }
            
            private Builder() {
                this.b();
            }
            
            private Builder(final GeneratedMessage.BuilderParent builderParent) {
                super(builderParent);
                this.b();
            }
            
            private void b() {
                if (Initialized_Message.alwaysUseFieldBuilders) {}
            }
            
            private static Builder a() {
                return new Builder();
            }
            
            public Builder clear() {
                super.clear();
                return this;
            }
            
            public Builder clone() {
                return a().mergeFrom(this.buildPartial());
            }
            
            public Descriptors.Descriptor getDescriptorForType() {
                return Model.internal_static_Initialized_Message_descriptor;
            }
            
            public Initialized_Message getDefaultInstanceForType() {
                return Initialized_Message.getDefaultInstance();
            }
            
            public Initialized_Message build() {
                final Initialized_Message buildPartial = this.buildPartial();
                if (!buildPartial.isInitialized()) {
                    throw newUninitializedMessageException((Message)buildPartial);
                }
                return buildPartial;
            }
            
            public Initialized_Message buildPartial() {
                final Initialized_Message initialized_Message = new Initialized_Message((GeneratedMessage.Builder)this);
                this.onBuilt();
                return initialized_Message;
            }
            
            public Builder mergeFrom(final Message message) {
                if (message instanceof Initialized_Message) {
                    return this.mergeFrom((Initialized_Message)message);
                }
                super.mergeFrom(message);
                return this;
            }
            
            public Builder mergeFrom(final Initialized_Message initialized_Message) {
                if (initialized_Message == Initialized_Message.getDefaultInstance()) {
                    return this;
                }
                this.mergeUnknownFields(initialized_Message.getUnknownFields());
                return this;
            }
            
            public final boolean isInitialized() {
                return true;
            }
            
            public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
                Initialized_Message initialized_Message = null;
                try {
                    initialized_Message = (Initialized_Message)Initialized_Message.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                }
                catch (InvalidProtocolBufferException ex) {
                    initialized_Message = (Initialized_Message)ex.getUnfinishedMessage();
                    throw ex;
                }
                finally {
                    try {
                        if (initialized_Message != null) {
                            this.mergeFrom(initialized_Message);
                        }
                    }
                    catch (InvalidProtocolBufferException ex2) {
                        throw b(ex2);
                    }
                }
                return this;
            }
            
            private static InvalidProtocolBufferException b(final InvalidProtocolBufferException ex) {
                return ex;
            }
        }
    }
    
    public static final class EnvParam extends GeneratedMessage implements EnvParamOrBuilder
    {
        private static final EnvParam defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<EnvParam> PARSER;
        private int bitField0_;
        public static final int NAME_FIELD_NUMBER = 1;
        private Object name_;
        public static final int VALUE_FIELD_NUMBER = 2;
        private Object value_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private static final long serialVersionUID = 0L;
        
        private EnvParam(final GeneratedMessage.Builder<?> builder) {
            super((GeneratedMessage.Builder)builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }
        
        private EnvParam(final boolean b) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }
        
        public static EnvParam getDefaultInstance() {
            return EnvParam.defaultInstance;
        }
        
        public EnvParam getDefaultInstanceForType() {
            return EnvParam.defaultInstance;
        }
        
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }
        
        private EnvParam(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.a();
            final UnknownFieldSet.Builder builder = UnknownFieldSet.newBuilder();
            try {
                int i = 0;
                while (i == 0) {
                    final int tag = codedInputStream.readTag();
                    switch (tag) {
                        case 0: {
                            i = 1;
                            continue;
                        }
                        default: {
                            if (!this.parseUnknownField(codedInputStream, builder, extensionRegistryLite, tag)) {
                                i = 1;
                                continue;
                            }
                            continue;
                        }
                        case 10: {
                            this.bitField0_ |= 0x1;
                            this.name_ = codedInputStream.readBytes();
                            continue;
                        }
                        case 18: {
                            this.bitField0_ |= 0x2;
                            this.value_ = codedInputStream.readBytes();
                            continue;
                        }
                    }
                }
            }
            catch (InvalidProtocolBufferException ex) {
                throw ex.setUnfinishedMessage((MessageLite)this);
            }
            catch (IOException ex2) {
                throw new InvalidProtocolBufferException(ex2.getMessage()).setUnfinishedMessage((MessageLite)this);
            }
            finally {
                this.unknownFields = builder.build();
                this.makeExtensionsImmutable();
            }
        }
        
        public static final Descriptors.Descriptor getDescriptor() {
            return Model.internal_static_EnvParam_descriptor;
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Model.internal_static_EnvParam_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)EnvParam.class, (Class)Builder.class);
        }
        
        public Parser<EnvParam> getParserForType() {
            return EnvParam.PARSER;
        }
        
        public boolean hasName() {
            return (this.bitField0_ & 0x1) == 0x1;
        }
        
        public String getName() {
            final Object name_ = this.name_;
            if (name_ instanceof String) {
                return (String)name_;
            }
            final ByteString byteString = (ByteString)name_;
            final String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.name_ = stringUtf8;
            }
            return stringUtf8;
        }
        
        public ByteString getNameBytes() {
            final Object name_ = this.name_;
            if (name_ instanceof String) {
                return (ByteString)(this.name_ = ByteString.copyFromUtf8((String)name_));
            }
            return (ByteString)name_;
        }
        
        public boolean hasValue() {
            return (this.bitField0_ & 0x2) == 0x2;
        }
        
        public String getValue() {
            final Object value_ = this.value_;
            if (value_ instanceof String) {
                return (String)value_;
            }
            final ByteString byteString = (ByteString)value_;
            final String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.value_ = stringUtf8;
            }
            return stringUtf8;
        }
        
        public ByteString getValueBytes() {
            final Object value_ = this.value_;
            if (value_ instanceof String) {
                return (ByteString)(this.value_ = ByteString.copyFromUtf8((String)value_));
            }
            return (ByteString)value_;
        }
        
        private void a() {
            this.name_ = "";
            this.value_ = "";
        }
        
        public final boolean isInitialized() {
            final byte memoizedIsInitialized = this.memoizedIsInitialized;
            if (memoizedIsInitialized != -1) {
                return memoizedIsInitialized == 1;
            }
            if (!this.hasName()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (!this.hasValue()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }
        
        public void writeTo(final CodedOutputStream codedOutputStream) throws IOException {
            try {
                this.getSerializedSize();
                if ((this.bitField0_ & 0x1) == 0x1) {
                    codedOutputStream.writeBytes(1, this.getNameBytes());
                }
            }
            catch (IOException ex) {
                throw a(ex);
            }
            try {
                if ((this.bitField0_ & 0x2) == 0x2) {
                    codedOutputStream.writeBytes(2, this.getValueBytes());
                }
            }
            catch (IOException ex2) {
                throw a(ex2);
            }
            this.getUnknownFields().writeTo(codedOutputStream);
        }
        
        public int getSerializedSize() {
            final int memoizedSerializedSize = this.memoizedSerializedSize;
            if (memoizedSerializedSize != -1) {
                return memoizedSerializedSize;
            }
            int n = 0;
            if ((this.bitField0_ & 0x1) == 0x1) {
                n += CodedOutputStream.computeBytesSize(1, this.getNameBytes());
            }
            if ((this.bitField0_ & 0x2) == 0x2) {
                n += CodedOutputStream.computeBytesSize(2, this.getValueBytes());
            }
            return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
        }
        
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }
        
        public static EnvParam parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
            return (EnvParam)EnvParam.PARSER.parseFrom(byteString);
        }
        
        public static EnvParam parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (EnvParam)EnvParam.PARSER.parseFrom(byteString, extensionRegistryLite);
        }
        
        public static EnvParam parseFrom(final byte[] array) throws InvalidProtocolBufferException {
            return (EnvParam)EnvParam.PARSER.parseFrom(array);
        }
        
        public static EnvParam parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (EnvParam)EnvParam.PARSER.parseFrom(array, extensionRegistryLite);
        }
        
        public static EnvParam parseFrom(final InputStream inputStream) throws IOException {
            return (EnvParam)EnvParam.PARSER.parseFrom(inputStream);
        }
        
        public static EnvParam parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (EnvParam)EnvParam.PARSER.parseFrom(inputStream, extensionRegistryLite);
        }
        
        public static EnvParam parseDelimitedFrom(final InputStream inputStream) throws IOException {
            return (EnvParam)EnvParam.PARSER.parseDelimitedFrom(inputStream);
        }
        
        public static EnvParam parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (EnvParam)EnvParam.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }
        
        public static EnvParam parseFrom(final CodedInputStream codedInputStream) throws IOException {
            return (EnvParam)EnvParam.PARSER.parseFrom(codedInputStream);
        }
        
        public static EnvParam parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (EnvParam)EnvParam.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }
        
        public static Builder newBuilder() {
            return a();
        }
        
        public Builder newBuilderForType() {
            return newBuilder();
        }
        
        public static Builder newBuilder(final EnvParam envParam) {
            return newBuilder().mergeFrom(envParam);
        }
        
        public Builder toBuilder() {
            return newBuilder(this);
        }
        
        protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
            return new Builder(builderParent);
        }
        
        static {
            EnvParam.PARSER = (Parser<EnvParam>)new AbstractParser<EnvParam>() {
                public EnvParam parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new EnvParam(codedInputStream, extensionRegistryLite);
                }
            };
            (defaultInstance = new EnvParam(true)).a();
        }
        
        private static IOException a(final IOException ex) {
            return ex;
        }
        
        public static final class Builder extends GeneratedMessage.Builder<Builder> implements EnvParamOrBuilder
        {
            private int bitField0_;
            private Object name_;
            private Object value_;
            
            public static final Descriptors.Descriptor getDescriptor() {
                return Model.internal_static_EnvParam_descriptor;
            }
            
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return Model.internal_static_EnvParam_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)EnvParam.class, (Class)Builder.class);
            }
            
            private Builder() {
                this.name_ = "";
                this.value_ = "";
                this.b();
            }
            
            private Builder(final GeneratedMessage.BuilderParent builderParent) {
                super(builderParent);
                this.name_ = "";
                this.value_ = "";
                this.b();
            }
            
            private void b() {
                if (EnvParam.alwaysUseFieldBuilders) {}
            }
            
            private static Builder a() {
                return new Builder();
            }
            
            public Builder clear() {
                super.clear();
                this.name_ = "";
                this.bitField0_ &= 0xFFFFFFFE;
                this.value_ = "";
                this.bitField0_ &= 0xFFFFFFFD;
                return this;
            }
            
            public Builder clone() {
                return a().mergeFrom(this.buildPartial());
            }
            
            public Descriptors.Descriptor getDescriptorForType() {
                return Model.internal_static_EnvParam_descriptor;
            }
            
            public EnvParam getDefaultInstanceForType() {
                return EnvParam.getDefaultInstance();
            }
            
            public EnvParam build() {
                final EnvParam buildPartial = this.buildPartial();
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
            
            public EnvParam buildPartial() {
                final EnvParam envParam = new EnvParam((GeneratedMessage.Builder)this);
                final int bitField0_ = this.bitField0_;
                int n = 0;
                if ((bitField0_ & 0x1) == 0x1) {
                    n |= 0x1;
                }
                envParam.name_ = this.name_;
                if ((bitField0_ & 0x2) == 0x2) {
                    n |= 0x2;
                }
                envParam.value_ = this.value_;
                envParam.bitField0_ = n;
                this.onBuilt();
                return envParam;
            }
            
            public Builder mergeFrom(final Message message) {
                try {
                    if (message instanceof EnvParam) {
                        return this.mergeFrom((EnvParam)message);
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                super.mergeFrom(message);
                return this;
            }
            
            public Builder mergeFrom(final EnvParam envParam) {
                try {
                    if (envParam == EnvParam.getDefaultInstance()) {
                        return this;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                try {
                    if (envParam.hasName()) {
                        this.bitField0_ |= 0x1;
                        this.name_ = envParam.name_;
                        this.onChanged();
                    }
                }
                catch (NullPointerException ex2) {
                    throw b(ex2);
                }
                try {
                    if (envParam.hasValue()) {
                        this.bitField0_ |= 0x2;
                        this.value_ = envParam.value_;
                        this.onChanged();
                    }
                }
                catch (NullPointerException ex3) {
                    throw b(ex3);
                }
                this.mergeUnknownFields(envParam.getUnknownFields());
                return this;
            }
            
            public final boolean isInitialized() {
                try {
                    if (!this.hasName()) {
                        return false;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                try {
                    if (!this.hasValue()) {
                        return false;
                    }
                }
                catch (NullPointerException ex2) {
                    throw b(ex2);
                }
                return true;
            }
            
            public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
                EnvParam envParam = null;
                try {
                    envParam = (EnvParam)EnvParam.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                }
                catch (InvalidProtocolBufferException ex) {
                    envParam = (EnvParam)ex.getUnfinishedMessage();
                    throw ex;
                }
                finally {
                    try {
                        if (envParam != null) {
                            this.mergeFrom(envParam);
                        }
                    }
                    catch (InvalidProtocolBufferException ex2) {
                        throw b((Exception)ex2);
                    }
                }
                return this;
            }
            
            public boolean hasName() {
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
                this.bitField0_ |= 0x1;
                this.name_ = name_;
                this.onChanged();
                return this;
            }
            
            public Builder clearName() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.name_ = EnvParam.getDefaultInstance().getName();
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
                this.bitField0_ |= 0x1;
                this.name_ = name_;
                this.onChanged();
                return this;
            }
            
            public boolean hasValue() {
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
                this.bitField0_ |= 0x2;
                this.value_ = value_;
                this.onChanged();
                return this;
            }
            
            public Builder clearValue() {
                this.bitField0_ &= 0xFFFFFFFD;
                this.value_ = EnvParam.getDefaultInstance().getValue();
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
                this.bitField0_ |= 0x2;
                this.value_ = value_;
                this.onChanged();
                return this;
            }
            
            private static Exception b(final Exception ex) {
                return ex;
            }
        }
    }
    
    public static final class CommandLine extends GeneratedMessage implements CommandLineOrBuilder
    {
        private static final CommandLine defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<CommandLine> PARSER;
        private int bitField0_;
        public static final int ENV_FIELD_NUMBER = 1;
        private List<EnvParam> env_;
        public static final int EXE_PATH_FIELD_NUMBER = 2;
        private Object exePath_;
        public static final int WORKING_DIR_FIELD_NUMBER = 3;
        private Object workingDir_;
        public static final int PARAM_FIELD_NUMBER = 4;
        private LazyStringList param_;
        public static final int STDIN_PATH_FIELD_NUMBER = 5;
        private Object stdinPath_;
        public static final int STDOUT_PATH_FIELD_NUMBER = 6;
        private Object stdoutPath_;
        public static final int STDERR_PATH_FIELD_NUMBER = 7;
        private Object stderrPath_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private static final long serialVersionUID = 0L;
        
        private CommandLine(final GeneratedMessage.Builder<?> builder) {
            super((GeneratedMessage.Builder)builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }
        
        private CommandLine(final boolean b) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }
        
        public static CommandLine getDefaultInstance() {
            return CommandLine.defaultInstance;
        }
        
        public CommandLine getDefaultInstanceForType() {
            return CommandLine.defaultInstance;
        }
        
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }
        
        private CommandLine(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.a();
            int n = 0;
            final UnknownFieldSet.Builder builder = UnknownFieldSet.newBuilder();
            try {
                int i = 0;
                while (i == 0) {
                    final int tag = codedInputStream.readTag();
                    switch (tag) {
                        case 0: {
                            i = 1;
                            continue;
                        }
                        default: {
                            if (!this.parseUnknownField(codedInputStream, builder, extensionRegistryLite, tag)) {
                                i = 1;
                                continue;
                            }
                            continue;
                        }
                        case 10: {
                            if ((n & 0x1) != 0x1) {
                                this.env_ = new ArrayList<EnvParam>();
                                n |= 0x1;
                            }
                            this.env_.add((EnvParam)codedInputStream.readMessage((Parser)EnvParam.PARSER, extensionRegistryLite));
                            continue;
                        }
                        case 18: {
                            this.bitField0_ |= 0x1;
                            this.exePath_ = codedInputStream.readBytes();
                            continue;
                        }
                        case 26: {
                            this.bitField0_ |= 0x2;
                            this.workingDir_ = codedInputStream.readBytes();
                            continue;
                        }
                        case 34: {
                            if ((n & 0x8) != 0x8) {
                                this.param_ = (LazyStringList)new LazyStringArrayList();
                                n |= 0x8;
                            }
                            this.param_.add(codedInputStream.readBytes());
                            continue;
                        }
                        case 42: {
                            this.bitField0_ |= 0x4;
                            this.stdinPath_ = codedInputStream.readBytes();
                            continue;
                        }
                        case 50: {
                            this.bitField0_ |= 0x8;
                            this.stdoutPath_ = codedInputStream.readBytes();
                            continue;
                        }
                        case 58: {
                            this.bitField0_ |= 0x10;
                            this.stderrPath_ = codedInputStream.readBytes();
                            continue;
                        }
                    }
                }
            }
            catch (InvalidProtocolBufferException ex) {
                throw ex.setUnfinishedMessage((MessageLite)this);
            }
            catch (IOException ex2) {
                throw new InvalidProtocolBufferException(ex2.getMessage()).setUnfinishedMessage((MessageLite)this);
            }
            finally {
                try {
                    if ((n & 0x1) == 0x1) {
                        this.env_ = Collections.unmodifiableList((List<? extends EnvParam>)this.env_);
                    }
                }
                catch (InvalidProtocolBufferException ex3) {
                    throw a((IOException)ex3);
                }
                try {
                    if ((n & 0x8) == 0x8) {
                        this.param_ = (LazyStringList)new UnmodifiableLazyStringList(this.param_);
                    }
                }
                catch (InvalidProtocolBufferException ex4) {
                    throw a((IOException)ex4);
                }
                this.unknownFields = builder.build();
                this.makeExtensionsImmutable();
            }
        }
        
        public static final Descriptors.Descriptor getDescriptor() {
            return Model.internal_static_CommandLine_descriptor;
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Model.internal_static_CommandLine_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)CommandLine.class, (Class)Builder.class);
        }
        
        public Parser<CommandLine> getParserForType() {
            return CommandLine.PARSER;
        }
        
        public List<EnvParam> getEnvList() {
            return this.env_;
        }
        
        public List<? extends EnvParamOrBuilder> getEnvOrBuilderList() {
            return this.env_;
        }
        
        public int getEnvCount() {
            return this.env_.size();
        }
        
        public EnvParam getEnv(final int n) {
            return this.env_.get(n);
        }
        
        public EnvParamOrBuilder getEnvOrBuilder(final int n) {
            return this.env_.get(n);
        }
        
        public boolean hasExePath() {
            return (this.bitField0_ & 0x1) == 0x1;
        }
        
        public String getExePath() {
            final Object exePath_ = this.exePath_;
            if (exePath_ instanceof String) {
                return (String)exePath_;
            }
            final ByteString byteString = (ByteString)exePath_;
            final String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.exePath_ = stringUtf8;
            }
            return stringUtf8;
        }
        
        public ByteString getExePathBytes() {
            final Object exePath_ = this.exePath_;
            if (exePath_ instanceof String) {
                return (ByteString)(this.exePath_ = ByteString.copyFromUtf8((String)exePath_));
            }
            return (ByteString)exePath_;
        }
        
        public boolean hasWorkingDir() {
            return (this.bitField0_ & 0x2) == 0x2;
        }
        
        public String getWorkingDir() {
            final Object workingDir_ = this.workingDir_;
            if (workingDir_ instanceof String) {
                return (String)workingDir_;
            }
            final ByteString byteString = (ByteString)workingDir_;
            final String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.workingDir_ = stringUtf8;
            }
            return stringUtf8;
        }
        
        public ByteString getWorkingDirBytes() {
            final Object workingDir_ = this.workingDir_;
            if (workingDir_ instanceof String) {
                return (ByteString)(this.workingDir_ = ByteString.copyFromUtf8((String)workingDir_));
            }
            return (ByteString)workingDir_;
        }
        
        public List<String> getParamList() {
            return (List<String>)this.param_;
        }
        
        public int getParamCount() {
            return this.param_.size();
        }
        
        public String getParam(final int n) {
            return (String)this.param_.get(n);
        }
        
        public ByteString getParamBytes(final int n) {
            return this.param_.getByteString(n);
        }
        
        public boolean hasStdinPath() {
            return (this.bitField0_ & 0x4) == 0x4;
        }
        
        public String getStdinPath() {
            final Object stdinPath_ = this.stdinPath_;
            if (stdinPath_ instanceof String) {
                return (String)stdinPath_;
            }
            final ByteString byteString = (ByteString)stdinPath_;
            final String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.stdinPath_ = stringUtf8;
            }
            return stringUtf8;
        }
        
        public ByteString getStdinPathBytes() {
            final Object stdinPath_ = this.stdinPath_;
            if (stdinPath_ instanceof String) {
                return (ByteString)(this.stdinPath_ = ByteString.copyFromUtf8((String)stdinPath_));
            }
            return (ByteString)stdinPath_;
        }
        
        public boolean hasStdoutPath() {
            return (this.bitField0_ & 0x8) == 0x8;
        }
        
        public String getStdoutPath() {
            final Object stdoutPath_ = this.stdoutPath_;
            if (stdoutPath_ instanceof String) {
                return (String)stdoutPath_;
            }
            final ByteString byteString = (ByteString)stdoutPath_;
            final String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.stdoutPath_ = stringUtf8;
            }
            return stringUtf8;
        }
        
        public ByteString getStdoutPathBytes() {
            final Object stdoutPath_ = this.stdoutPath_;
            if (stdoutPath_ instanceof String) {
                return (ByteString)(this.stdoutPath_ = ByteString.copyFromUtf8((String)stdoutPath_));
            }
            return (ByteString)stdoutPath_;
        }
        
        public boolean hasStderrPath() {
            return (this.bitField0_ & 0x10) == 0x10;
        }
        
        public String getStderrPath() {
            final Object stderrPath_ = this.stderrPath_;
            if (stderrPath_ instanceof String) {
                return (String)stderrPath_;
            }
            final ByteString byteString = (ByteString)stderrPath_;
            final String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.stderrPath_ = stringUtf8;
            }
            return stringUtf8;
        }
        
        public ByteString getStderrPathBytes() {
            final Object stderrPath_ = this.stderrPath_;
            if (stderrPath_ instanceof String) {
                return (ByteString)(this.stderrPath_ = ByteString.copyFromUtf8((String)stderrPath_));
            }
            return (ByteString)stderrPath_;
        }
        
        private void a() {
            this.env_ = Collections.emptyList();
            this.exePath_ = "";
            this.workingDir_ = "";
            this.param_ = LazyStringArrayList.EMPTY;
            this.stdinPath_ = "";
            this.stdoutPath_ = "";
            this.stderrPath_ = "";
        }
        
        public final boolean isInitialized() {
            final byte memoizedIsInitialized = this.memoizedIsInitialized;
            if (memoizedIsInitialized != -1) {
                return memoizedIsInitialized == 1;
            }
            if (!this.hasExePath()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (!this.hasWorkingDir()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            for (int i = 0; i < this.getEnvCount(); ++i) {
                if (!this.getEnv(i).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            this.memoizedIsInitialized = 1;
            return true;
        }
        
        public void writeTo(final CodedOutputStream codedOutputStream) throws IOException {
            this.getSerializedSize();
            int i = 0;
            try {
                while (i < this.env_.size()) {
                    codedOutputStream.writeMessage(1, (MessageLite)this.env_.get(i));
                    ++i;
                }
            }
            catch (IOException ex) {
                throw a(ex);
            }
            try {
                if ((this.bitField0_ & 0x1) == 0x1) {
                    codedOutputStream.writeBytes(2, this.getExePathBytes());
                }
            }
            catch (IOException ex2) {
                throw a(ex2);
            }
            try {
                if ((this.bitField0_ & 0x2) == 0x2) {
                    codedOutputStream.writeBytes(3, this.getWorkingDirBytes());
                }
            }
            catch (IOException ex3) {
                throw a(ex3);
            }
            int j = 0;
            try {
                while (j < this.param_.size()) {
                    codedOutputStream.writeBytes(4, this.param_.getByteString(j));
                    ++j;
                }
            }
            catch (IOException ex4) {
                throw a(ex4);
            }
            try {
                if ((this.bitField0_ & 0x4) == 0x4) {
                    codedOutputStream.writeBytes(5, this.getStdinPathBytes());
                }
            }
            catch (IOException ex5) {
                throw a(ex5);
            }
            try {
                if ((this.bitField0_ & 0x8) == 0x8) {
                    codedOutputStream.writeBytes(6, this.getStdoutPathBytes());
                }
            }
            catch (IOException ex6) {
                throw a(ex6);
            }
            try {
                if ((this.bitField0_ & 0x10) == 0x10) {
                    codedOutputStream.writeBytes(7, this.getStderrPathBytes());
                }
            }
            catch (IOException ex7) {
                throw a(ex7);
            }
            this.getUnknownFields().writeTo(codedOutputStream);
        }
        
        public int getSerializedSize() {
            final int memoizedSerializedSize = this.memoizedSerializedSize;
            if (memoizedSerializedSize != -1) {
                return memoizedSerializedSize;
            }
            int n = 0;
            for (int i = 0; i < this.env_.size(); ++i) {
                n += CodedOutputStream.computeMessageSize(1, (MessageLite)this.env_.get(i));
            }
            if ((this.bitField0_ & 0x1) == 0x1) {
                n += CodedOutputStream.computeBytesSize(2, this.getExePathBytes());
            }
            if ((this.bitField0_ & 0x2) == 0x2) {
                n += CodedOutputStream.computeBytesSize(3, this.getWorkingDirBytes());
            }
            int n2 = 0;
            for (int j = 0; j < this.param_.size(); ++j) {
                n2 += CodedOutputStream.computeBytesSizeNoTag(this.param_.getByteString(j));
            }
            int n3 = n + n2 + 1 * this.getParamList().size();
            if ((this.bitField0_ & 0x4) == 0x4) {
                n3 += CodedOutputStream.computeBytesSize(5, this.getStdinPathBytes());
            }
            if ((this.bitField0_ & 0x8) == 0x8) {
                n3 += CodedOutputStream.computeBytesSize(6, this.getStdoutPathBytes());
            }
            if ((this.bitField0_ & 0x10) == 0x10) {
                n3 += CodedOutputStream.computeBytesSize(7, this.getStderrPathBytes());
            }
            return this.memoizedSerializedSize = n3 + this.getUnknownFields().getSerializedSize();
        }
        
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }
        
        public static CommandLine parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
            return (CommandLine)CommandLine.PARSER.parseFrom(byteString);
        }
        
        public static CommandLine parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CommandLine)CommandLine.PARSER.parseFrom(byteString, extensionRegistryLite);
        }
        
        public static CommandLine parseFrom(final byte[] array) throws InvalidProtocolBufferException {
            return (CommandLine)CommandLine.PARSER.parseFrom(array);
        }
        
        public static CommandLine parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CommandLine)CommandLine.PARSER.parseFrom(array, extensionRegistryLite);
        }
        
        public static CommandLine parseFrom(final InputStream inputStream) throws IOException {
            return (CommandLine)CommandLine.PARSER.parseFrom(inputStream);
        }
        
        public static CommandLine parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CommandLine)CommandLine.PARSER.parseFrom(inputStream, extensionRegistryLite);
        }
        
        public static CommandLine parseDelimitedFrom(final InputStream inputStream) throws IOException {
            return (CommandLine)CommandLine.PARSER.parseDelimitedFrom(inputStream);
        }
        
        public static CommandLine parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CommandLine)CommandLine.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }
        
        public static CommandLine parseFrom(final CodedInputStream codedInputStream) throws IOException {
            return (CommandLine)CommandLine.PARSER.parseFrom(codedInputStream);
        }
        
        public static CommandLine parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CommandLine)CommandLine.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }
        
        public static Builder newBuilder() {
            return b();
        }
        
        public Builder newBuilderForType() {
            return newBuilder();
        }
        
        public static Builder newBuilder(final CommandLine commandLine) {
            return newBuilder().mergeFrom(commandLine);
        }
        
        public Builder toBuilder() {
            return newBuilder(this);
        }
        
        protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
            return new Builder(builderParent);
        }
        
        static {
            CommandLine.PARSER = (Parser<CommandLine>)new AbstractParser<CommandLine>() {
                public CommandLine parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new CommandLine(codedInputStream, extensionRegistryLite);
                }
            };
            (defaultInstance = new CommandLine(true)).a();
        }
        
        private static IOException a(final IOException ex) {
            return ex;
        }
        
        public static final class Builder extends GeneratedMessage.Builder<Builder> implements CommandLineOrBuilder
        {
            private int bitField0_;
            private List<EnvParam> env_;
            private RepeatedFieldBuilder<EnvParam, EnvParam.Builder, EnvParamOrBuilder> envBuilder_;
            private Object exePath_;
            private Object workingDir_;
            private LazyStringList param_;
            private Object stdinPath_;
            private Object stdoutPath_;
            private Object stderrPath_;
            
            public static final Descriptors.Descriptor getDescriptor() {
                return Model.internal_static_CommandLine_descriptor;
            }
            
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return Model.internal_static_CommandLine_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)CommandLine.class, (Class)Builder.class);
            }
            
            private Builder() {
                this.env_ = Collections.emptyList();
                this.exePath_ = "";
                this.workingDir_ = "";
                this.param_ = LazyStringArrayList.EMPTY;
                this.stdinPath_ = "";
                this.stdoutPath_ = "";
                this.stderrPath_ = "";
                this.c();
            }
            
            private Builder(final GeneratedMessage.BuilderParent builderParent) {
                super(builderParent);
                this.env_ = Collections.emptyList();
                this.exePath_ = "";
                this.workingDir_ = "";
                this.param_ = LazyStringArrayList.EMPTY;
                this.stdinPath_ = "";
                this.stdoutPath_ = "";
                this.stderrPath_ = "";
                this.c();
            }
            
            private void c() {
                try {
                    if (CommandLine.alwaysUseFieldBuilders) {
                        this.d();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
            }
            
            private static Builder b() {
                return new Builder();
            }
            
            public Builder clear() {
                Label_0044: {
                    try {
                        super.clear();
                        if (this.envBuilder_ == null) {
                            this.env_ = Collections.emptyList();
                            this.bitField0_ &= 0xFFFFFFFE;
                            break Label_0044;
                        }
                    }
                    catch (NullPointerException ex) {
                        throw b(ex);
                    }
                    this.envBuilder_.clear();
                }
                this.exePath_ = "";
                this.bitField0_ &= 0xFFFFFFFD;
                this.workingDir_ = "";
                this.bitField0_ &= 0xFFFFFFFB;
                this.param_ = LazyStringArrayList.EMPTY;
                this.bitField0_ &= 0xFFFFFFF7;
                this.stdinPath_ = "";
                this.bitField0_ &= 0xFFFFFFEF;
                this.stdoutPath_ = "";
                this.bitField0_ &= 0xFFFFFFDF;
                this.stderrPath_ = "";
                this.bitField0_ &= 0xFFFFFFBF;
                return this;
            }
            
            public Builder clone() {
                return b().mergeFrom(this.buildPartial());
            }
            
            public Descriptors.Descriptor getDescriptorForType() {
                return Model.internal_static_CommandLine_descriptor;
            }
            
            public CommandLine getDefaultInstanceForType() {
                return CommandLine.getDefaultInstance();
            }
            
            public CommandLine build() {
                final CommandLine buildPartial = this.buildPartial();
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
            
            public CommandLine buildPartial() {
                final CommandLine commandLine = new CommandLine((GeneratedMessage.Builder)this);
                final int bitField0_ = this.bitField0_;
                int n = 0;
                Label_0094: {
                    Label_0082: {
                        Label_0041: {
                            try {
                                if (this.envBuilder_ != null) {
                                    break Label_0082;
                                }
                                final Builder builder = this;
                                final int n2 = builder.bitField0_;
                                final boolean b = true;
                                final boolean b2 = (n2 & (b ? 1 : 0)) != 0x0;
                                final boolean b3 = true;
                                if (b2 == b3) {
                                    break Label_0041;
                                }
                                break Label_0041;
                            }
                            catch (NullPointerException ex) {
                                throw b(ex);
                            }
                            try {
                                final Builder builder = this;
                                final int n2 = builder.bitField0_;
                                final boolean b = true;
                                final boolean b2 = (n2 & (b ? 1 : 0)) != 0x0;
                                final boolean b3 = true;
                                if (b2 == b3) {
                                    this.env_ = Collections.unmodifiableList((List<? extends EnvParam>)this.env_);
                                    this.bitField0_ &= 0xFFFFFFFE;
                                }
                            }
                            catch (NullPointerException ex2) {
                                throw b(ex2);
                            }
                        }
                        commandLine.env_ = this.env_;
                        break Label_0094;
                    }
                    commandLine.env_ = (List<EnvParam>)this.envBuilder_.build();
                }
                if ((bitField0_ & 0x2) == 0x2) {
                    n |= 0x1;
                }
                commandLine.exePath_ = this.exePath_;
                if ((bitField0_ & 0x4) == 0x4) {
                    n |= 0x2;
                }
                try {
                    commandLine.workingDir_ = this.workingDir_;
                    if ((this.bitField0_ & 0x8) == 0x8) {
                        this.param_ = (LazyStringList)new UnmodifiableLazyStringList(this.param_);
                        this.bitField0_ &= 0xFFFFFFF7;
                    }
                }
                catch (NullPointerException ex3) {
                    throw b(ex3);
                }
                commandLine.param_ = this.param_;
                if ((bitField0_ & 0x10) == 0x10) {
                    n |= 0x4;
                }
                commandLine.stdinPath_ = this.stdinPath_;
                if ((bitField0_ & 0x20) == 0x20) {
                    n |= 0x8;
                }
                commandLine.stdoutPath_ = this.stdoutPath_;
                if ((bitField0_ & 0x40) == 0x40) {
                    n |= 0x10;
                }
                commandLine.stderrPath_ = this.stderrPath_;
                commandLine.bitField0_ = n;
                this.onBuilt();
                return commandLine;
            }
            
            public Builder mergeFrom(final Message message) {
                try {
                    if (message instanceof CommandLine) {
                        return this.mergeFrom((CommandLine)message);
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                super.mergeFrom(message);
                return this;
            }
            
            public Builder mergeFrom(final CommandLine p0) {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_1        
                //     1: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine;
                //     4: if_acmpne       13
                //     7: aload_0        
                //     8: areturn        
                //     9: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                //    12: athrow         
                //    13: aload_0        
                //    14: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.envBuilder_:Lcom/google/protobuf/RepeatedFieldBuilder;
                //    17: ifnonnull       109
                //    20: aload_1        
                //    21: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine.access$2400:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine;)Ljava/util/List;
                //    24: invokeinterface java/util/List.isEmpty:()Z
                //    29: ifne            213
                //    32: goto            39
                //    35: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                //    38: athrow         
                //    39: aload_0        
                //    40: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.env_:Ljava/util/List;
                //    43: invokeinterface java/util/List.isEmpty:()Z
                //    48: ifeq            84
                //    51: goto            58
                //    54: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                //    57: athrow         
                //    58: aload_0        
                //    59: aload_1        
                //    60: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine.access$2400:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine;)Ljava/util/List;
                //    63: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.env_:Ljava/util/List;
                //    66: aload_0        
                //    67: aload_0        
                //    68: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.bitField0_:I
                //    71: bipush          -2
                //    73: iand           
                //    74: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.bitField0_:I
                //    77: goto            102
                //    80: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                //    83: athrow         
                //    84: aload_0        
                //    85: invokespecial   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.e:()V
                //    88: aload_0        
                //    89: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.env_:Ljava/util/List;
                //    92: aload_1        
                //    93: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine.access$2400:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine;)Ljava/util/List;
                //    96: invokeinterface java/util/List.addAll:(Ljava/util/Collection;)Z
                //   101: pop            
                //   102: aload_0        
                //   103: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.onChanged:()V
                //   106: goto            213
                //   109: aload_1        
                //   110: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine.access$2400:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine;)Ljava/util/List;
                //   113: invokeinterface java/util/List.isEmpty:()Z
                //   118: ifne            213
                //   121: aload_0        
                //   122: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.envBuilder_:Lcom/google/protobuf/RepeatedFieldBuilder;
                //   125: invokevirtual   com/google/protobuf/RepeatedFieldBuilder.isEmpty:()Z
                //   128: ifeq            201
                //   131: goto            138
                //   134: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                //   137: athrow         
                //   138: aload_0        
                //   139: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.envBuilder_:Lcom/google/protobuf/RepeatedFieldBuilder;
                //   142: invokevirtual   com/google/protobuf/RepeatedFieldBuilder.dispose:()V
                //   145: aload_0        
                //   146: aconst_null    
                //   147: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.envBuilder_:Lcom/google/protobuf/RepeatedFieldBuilder;
                //   150: aload_0        
                //   151: aload_1        
                //   152: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine.access$2400:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine;)Ljava/util/List;
                //   155: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.env_:Ljava/util/List;
                //   158: aload_0        
                //   159: aload_0        
                //   160: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.bitField0_:I
                //   163: bipush          -2
                //   165: iand           
                //   166: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.bitField0_:I
                //   169: aload_0        
                //   170: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine.access$3200:()Z
                //   173: ifeq            194
                //   176: goto            183
                //   179: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                //   182: athrow         
                //   183: aload_0        
                //   184: invokespecial   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.d:()Lcom/google/protobuf/RepeatedFieldBuilder;
                //   187: goto            195
                //   190: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                //   193: athrow         
                //   194: aconst_null    
                //   195: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.envBuilder_:Lcom/google/protobuf/RepeatedFieldBuilder;
                //   198: goto            213
                //   201: aload_0        
                //   202: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.envBuilder_:Lcom/google/protobuf/RepeatedFieldBuilder;
                //   205: aload_1        
                //   206: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine.access$2400:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine;)Ljava/util/List;
                //   209: invokevirtual   com/google/protobuf/RepeatedFieldBuilder.addAllMessages:(Ljava/lang/Iterable;)Lcom/google/protobuf/RepeatedFieldBuilder;
                //   212: pop            
                //   213: aload_1        
                //   214: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine.hasExePath:()Z
                //   217: ifeq            249
                //   220: aload_0        
                //   221: dup            
                //   222: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.bitField0_:I
                //   225: iconst_2       
                //   226: ior            
                //   227: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.bitField0_:I
                //   230: aload_0        
                //   231: aload_1        
                //   232: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine.access$2500:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine;)Ljava/lang/Object;
                //   235: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.exePath_:Ljava/lang/Object;
                //   238: aload_0        
                //   239: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.onChanged:()V
                //   242: goto            249
                //   245: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                //   248: athrow         
                //   249: aload_1        
                //   250: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine.hasWorkingDir:()Z
                //   253: ifeq            285
                //   256: aload_0        
                //   257: dup            
                //   258: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.bitField0_:I
                //   261: iconst_4       
                //   262: ior            
                //   263: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.bitField0_:I
                //   266: aload_0        
                //   267: aload_1        
                //   268: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine.access$2600:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine;)Ljava/lang/Object;
                //   271: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.workingDir_:Ljava/lang/Object;
                //   274: aload_0        
                //   275: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.onChanged:()V
                //   278: goto            285
                //   281: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                //   284: athrow         
                //   285: aload_1        
                //   286: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine.access$2700:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine;)Lcom/google/protobuf/LazyStringList;
                //   289: invokeinterface com/google/protobuf/LazyStringList.isEmpty:()Z
                //   294: ifne            364
                //   297: aload_0        
                //   298: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.param_:Lcom/google/protobuf/LazyStringList;
                //   301: invokeinterface com/google/protobuf/LazyStringList.isEmpty:()Z
                //   306: ifeq            342
                //   309: goto            316
                //   312: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                //   315: athrow         
                //   316: aload_0        
                //   317: aload_1        
                //   318: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine.access$2700:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine;)Lcom/google/protobuf/LazyStringList;
                //   321: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.param_:Lcom/google/protobuf/LazyStringList;
                //   324: aload_0        
                //   325: aload_0        
                //   326: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.bitField0_:I
                //   329: bipush          -9
                //   331: iand           
                //   332: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.bitField0_:I
                //   335: goto            360
                //   338: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                //   341: athrow         
                //   342: aload_0        
                //   343: invokespecial   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.a:()V
                //   346: aload_0        
                //   347: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.param_:Lcom/google/protobuf/LazyStringList;
                //   350: aload_1        
                //   351: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine.access$2700:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine;)Lcom/google/protobuf/LazyStringList;
                //   354: invokeinterface com/google/protobuf/LazyStringList.addAll:(Ljava/util/Collection;)Z
                //   359: pop            
                //   360: aload_0        
                //   361: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.onChanged:()V
                //   364: aload_1        
                //   365: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine.hasStdinPath:()Z
                //   368: ifeq            401
                //   371: aload_0        
                //   372: dup            
                //   373: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.bitField0_:I
                //   376: bipush          16
                //   378: ior            
                //   379: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.bitField0_:I
                //   382: aload_0        
                //   383: aload_1        
                //   384: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine.access$2800:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine;)Ljava/lang/Object;
                //   387: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.stdinPath_:Ljava/lang/Object;
                //   390: aload_0        
                //   391: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.onChanged:()V
                //   394: goto            401
                //   397: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                //   400: athrow         
                //   401: aload_1        
                //   402: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine.hasStdoutPath:()Z
                //   405: ifeq            438
                //   408: aload_0        
                //   409: dup            
                //   410: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.bitField0_:I
                //   413: bipush          32
                //   415: ior            
                //   416: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.bitField0_:I
                //   419: aload_0        
                //   420: aload_1        
                //   421: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine.access$2900:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine;)Ljava/lang/Object;
                //   424: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.stdoutPath_:Ljava/lang/Object;
                //   427: aload_0        
                //   428: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.onChanged:()V
                //   431: goto            438
                //   434: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                //   437: athrow         
                //   438: aload_1        
                //   439: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine.hasStderrPath:()Z
                //   442: ifeq            475
                //   445: aload_0        
                //   446: dup            
                //   447: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.bitField0_:I
                //   450: bipush          64
                //   452: ior            
                //   453: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.bitField0_:I
                //   456: aload_0        
                //   457: aload_1        
                //   458: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine.access$3000:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine;)Ljava/lang/Object;
                //   461: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.stderrPath_:Ljava/lang/Object;
                //   464: aload_0        
                //   465: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.onChanged:()V
                //   468: goto            475
                //   471: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                //   474: athrow         
                //   475: aload_0        
                //   476: aload_1        
                //   477: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine.getUnknownFields:()Lcom/google/protobuf/UnknownFieldSet;
                //   480: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$CommandLine$Builder.mergeUnknownFields:(Lcom/google/protobuf/UnknownFieldSet;)Lcom/google/protobuf/GeneratedMessage$Builder;
                //   483: pop            
                //   484: aload_0        
                //   485: areturn        
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                            
                //  -----  -----  -----  -----  --------------------------------
                //  0      9      9      13     Ljava/lang/NullPointerException;
                //  13     32     35     39     Ljava/lang/NullPointerException;
                //  20     51     54     58     Ljava/lang/NullPointerException;
                //  39     80     80     84     Ljava/lang/NullPointerException;
                //  109    131    134    138    Ljava/lang/NullPointerException;
                //  121    176    179    183    Ljava/lang/NullPointerException;
                //  138    190    190    194    Ljava/lang/NullPointerException;
                //  213    242    245    249    Ljava/lang/NullPointerException;
                //  249    278    281    285    Ljava/lang/NullPointerException;
                //  285    309    312    316    Ljava/lang/NullPointerException;
                //  297    338    338    342    Ljava/lang/NullPointerException;
                //  364    394    397    401    Ljava/lang/NullPointerException;
                //  401    431    434    438    Ljava/lang/NullPointerException;
                //  438    468    471    475    Ljava/lang/NullPointerException;
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0039:
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
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
            
            public final boolean isInitialized() {
                try {
                    if (!this.hasExePath()) {
                        return false;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                try {
                    if (!this.hasWorkingDir()) {
                        return false;
                    }
                }
                catch (NullPointerException ex2) {
                    throw b(ex2);
                }
                int n = 0;
                while (true) {
                    Label_0060: {
                        try {
                            if (n >= this.getEnvCount()) {
                                break;
                            }
                            final Builder builder = this;
                            final int n2 = n;
                            final EnvParam envParam = builder.getEnv(n2);
                            final boolean b = envParam.isInitialized();
                            if (!b) {
                                return false;
                            }
                            break Label_0060;
                        }
                        catch (NullPointerException ex3) {
                            throw b(ex3);
                        }
                        try {
                            final Builder builder = this;
                            final int n2 = n;
                            final EnvParam envParam = builder.getEnv(n2);
                            final boolean b = envParam.isInitialized();
                            if (!b) {
                                return false;
                            }
                        }
                        catch (NullPointerException ex4) {
                            throw b(ex4);
                        }
                    }
                    ++n;
                }
                return true;
            }
            
            public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
                CommandLine commandLine = null;
                try {
                    commandLine = (CommandLine)CommandLine.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                }
                catch (InvalidProtocolBufferException ex) {
                    commandLine = (CommandLine)ex.getUnfinishedMessage();
                    throw ex;
                }
                finally {
                    try {
                        if (commandLine != null) {
                            this.mergeFrom(commandLine);
                        }
                    }
                    catch (InvalidProtocolBufferException ex2) {
                        throw b((Exception)ex2);
                    }
                }
                return this;
            }
            
            private void e() {
                try {
                    if ((this.bitField0_ & 0x1) != 0x1) {
                        this.env_ = new ArrayList<EnvParam>(this.env_);
                        this.bitField0_ |= 0x1;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
            }
            
            public List<EnvParam> getEnvList() {
                try {
                    if (this.envBuilder_ == null) {
                        return Collections.unmodifiableList((List<? extends EnvParam>)this.env_);
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                return (List<EnvParam>)this.envBuilder_.getMessageList();
            }
            
            public int getEnvCount() {
                try {
                    if (this.envBuilder_ == null) {
                        return this.env_.size();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                return this.envBuilder_.getCount();
            }
            
            public EnvParam getEnv(final int n) {
                try {
                    if (this.envBuilder_ == null) {
                        return this.env_.get(n);
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                return (EnvParam)this.envBuilder_.getMessage(n);
            }
            
            public Builder setEnv(final int n, final EnvParam envParam) {
                Label_0053: {
                    Label_0018: {
                        try {
                            if (this.envBuilder_ != null) {
                                break Label_0053;
                            }
                            final EnvParam envParam2 = envParam;
                            if (envParam2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final EnvParam envParam2 = envParam;
                            if (envParam2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.e();
                    this.env_.set(n, envParam);
                    this.onChanged();
                    return this;
                }
                this.envBuilder_.setMessage(n, (GeneratedMessage)envParam);
                return this;
            }
            
            public Builder setEnv(final int n, final EnvParam.Builder builder) {
                try {
                    if (this.envBuilder_ == null) {
                        this.e();
                        this.env_.set(n, builder.build());
                        this.onChanged();
                        return this;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.envBuilder_.setMessage(n, (GeneratedMessage)builder.build());
                return this;
            }
            
            public Builder addEnv(final EnvParam envParam) {
                Label_0052: {
                    Label_0018: {
                        try {
                            if (this.envBuilder_ != null) {
                                break Label_0052;
                            }
                            final EnvParam envParam2 = envParam;
                            if (envParam2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final EnvParam envParam2 = envParam;
                            if (envParam2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.e();
                    this.env_.add(envParam);
                    this.onChanged();
                    return this;
                }
                this.envBuilder_.addMessage((GeneratedMessage)envParam);
                return this;
            }
            
            public Builder addEnv(final int n, final EnvParam envParam) {
                Label_0052: {
                    Label_0018: {
                        try {
                            if (this.envBuilder_ != null) {
                                break Label_0052;
                            }
                            final EnvParam envParam2 = envParam;
                            if (envParam2 == null) {
                                break Label_0018;
                            }
                            break Label_0018;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            final EnvParam envParam2 = envParam;
                            if (envParam2 == null) {
                                throw new NullPointerException();
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    this.e();
                    this.env_.add(n, envParam);
                    this.onChanged();
                    return this;
                }
                this.envBuilder_.addMessage(n, (GeneratedMessage)envParam);
                return this;
            }
            
            public Builder addEnv(final EnvParam.Builder builder) {
                try {
                    if (this.envBuilder_ == null) {
                        this.e();
                        this.env_.add(builder.build());
                        this.onChanged();
                        return this;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.envBuilder_.addMessage((GeneratedMessage)builder.build());
                return this;
            }
            
            public Builder addEnv(final int n, final EnvParam.Builder builder) {
                try {
                    if (this.envBuilder_ == null) {
                        this.e();
                        this.env_.add(n, builder.build());
                        this.onChanged();
                        return this;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.envBuilder_.addMessage(n, (GeneratedMessage)builder.build());
                return this;
            }
            
            public Builder addAllEnv(final Iterable<? extends EnvParam> iterable) {
                try {
                    if (this.envBuilder_ == null) {
                        this.e();
                        GeneratedMessage.Builder.addAll((Iterable)iterable, (Collection)this.env_);
                        this.onChanged();
                        return this;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.envBuilder_.addAllMessages((Iterable)iterable);
                return this;
            }
            
            public Builder clearEnv() {
                try {
                    if (this.envBuilder_ == null) {
                        this.env_ = Collections.emptyList();
                        this.bitField0_ &= 0xFFFFFFFE;
                        this.onChanged();
                        return this;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.envBuilder_.clear();
                return this;
            }
            
            public Builder removeEnv(final int n) {
                try {
                    if (this.envBuilder_ == null) {
                        this.e();
                        this.env_.remove(n);
                        this.onChanged();
                        return this;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.envBuilder_.remove(n);
                return this;
            }
            
            public EnvParam.Builder getEnvBuilder(final int n) {
                return (EnvParam.Builder)this.d().getBuilder(n);
            }
            
            public EnvParamOrBuilder getEnvOrBuilder(final int n) {
                try {
                    if (this.envBuilder_ == null) {
                        return this.env_.get(n);
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                return (EnvParamOrBuilder)this.envBuilder_.getMessageOrBuilder(n);
            }
            
            public List<? extends EnvParamOrBuilder> getEnvOrBuilderList() {
                try {
                    if (this.envBuilder_ != null) {
                        return (List<? extends EnvParamOrBuilder>)this.envBuilder_.getMessageOrBuilderList();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                return Collections.unmodifiableList((List<? extends EnvParamOrBuilder>)this.env_);
            }
            
            public EnvParam.Builder addEnvBuilder() {
                return (EnvParam.Builder)this.d().addBuilder((GeneratedMessage)EnvParam.getDefaultInstance());
            }
            
            public EnvParam.Builder addEnvBuilder(final int n) {
                return (EnvParam.Builder)this.d().addBuilder(n, (GeneratedMessage)EnvParam.getDefaultInstance());
            }
            
            public List<EnvParam.Builder> getEnvBuilderList() {
                return (List<EnvParam.Builder>)this.d().getBuilderList();
            }
            
            private RepeatedFieldBuilder<EnvParam, EnvParam.Builder, EnvParamOrBuilder> d() {
                Builder builder = null;
                RepeatedFieldBuilder envBuilder_ = null;
                List<EnvParam> list = null;
                boolean b4 = false;
                Label_0042: {
                    Label_0033: {
                        try {
                            if (this.envBuilder_ != null) {
                                return this.envBuilder_;
                            }
                            builder = this;
                            envBuilder_ = new(com.google.protobuf.RepeatedFieldBuilder.class);
                            final Builder builder2 = this;
                            list = builder2.env_;
                            final Builder builder3 = this;
                            final int n = builder3.bitField0_;
                            final boolean b = true;
                            final boolean b2 = (n & (b ? 1 : 0)) != 0x0;
                            final boolean b3 = true;
                            if (b2 == b3) {
                                break Label_0033;
                            }
                            break Label_0033;
                        }
                        catch (NullPointerException ex) {
                            throw b(ex);
                        }
                        try {
                            builder = this;
                            envBuilder_ = new(com.google.protobuf.RepeatedFieldBuilder.class);
                            final Builder builder2 = this;
                            list = builder2.env_;
                            final Builder builder3 = this;
                            final int n = builder3.bitField0_;
                            final boolean b = true;
                            final boolean b2 = (n & (b ? 1 : 0)) != 0x0;
                            final boolean b3 = true;
                            if (b2 == b3) {
                                b4 = true;
                                break Label_0042;
                            }
                        }
                        catch (NullPointerException ex2) {
                            throw b(ex2);
                        }
                    }
                    b4 = false;
                }
                new RepeatedFieldBuilder((List)list, b4, this.getParentForChildren(), this.isClean());
                builder.envBuilder_ = (RepeatedFieldBuilder<EnvParam, EnvParam.Builder, EnvParamOrBuilder>)envBuilder_;
                this.env_ = null;
                return this.envBuilder_;
            }
            
            public boolean hasExePath() {
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
            
            public String getExePath() {
                final Object exePath_ = this.exePath_;
                if (!(exePath_ instanceof String)) {
                    return (String)(this.exePath_ = ((ByteString)exePath_).toStringUtf8());
                }
                return (String)exePath_;
            }
            
            public ByteString getExePathBytes() {
                final Object exePath_ = this.exePath_;
                if (exePath_ instanceof String) {
                    return (ByteString)(this.exePath_ = ByteString.copyFromUtf8((String)exePath_));
                }
                return (ByteString)exePath_;
            }
            
            public Builder setExePath(final String exePath_) {
                try {
                    if (exePath_ == null) {
                        throw new NullPointerException();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.bitField0_ |= 0x2;
                this.exePath_ = exePath_;
                this.onChanged();
                return this;
            }
            
            public Builder clearExePath() {
                this.bitField0_ &= 0xFFFFFFFD;
                this.exePath_ = CommandLine.getDefaultInstance().getExePath();
                this.onChanged();
                return this;
            }
            
            public Builder setExePathBytes(final ByteString exePath_) {
                try {
                    if (exePath_ == null) {
                        throw new NullPointerException();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.bitField0_ |= 0x2;
                this.exePath_ = exePath_;
                this.onChanged();
                return this;
            }
            
            public boolean hasWorkingDir() {
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
            
            public String getWorkingDir() {
                final Object workingDir_ = this.workingDir_;
                if (!(workingDir_ instanceof String)) {
                    return (String)(this.workingDir_ = ((ByteString)workingDir_).toStringUtf8());
                }
                return (String)workingDir_;
            }
            
            public ByteString getWorkingDirBytes() {
                final Object workingDir_ = this.workingDir_;
                if (workingDir_ instanceof String) {
                    return (ByteString)(this.workingDir_ = ByteString.copyFromUtf8((String)workingDir_));
                }
                return (ByteString)workingDir_;
            }
            
            public Builder setWorkingDir(final String workingDir_) {
                try {
                    if (workingDir_ == null) {
                        throw new NullPointerException();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.bitField0_ |= 0x4;
                this.workingDir_ = workingDir_;
                this.onChanged();
                return this;
            }
            
            public Builder clearWorkingDir() {
                this.bitField0_ &= 0xFFFFFFFB;
                this.workingDir_ = CommandLine.getDefaultInstance().getWorkingDir();
                this.onChanged();
                return this;
            }
            
            public Builder setWorkingDirBytes(final ByteString workingDir_) {
                try {
                    if (workingDir_ == null) {
                        throw new NullPointerException();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.bitField0_ |= 0x4;
                this.workingDir_ = workingDir_;
                this.onChanged();
                return this;
            }
            
            private void a() {
                try {
                    if ((this.bitField0_ & 0x8) != 0x8) {
                        this.param_ = (LazyStringList)new LazyStringArrayList(this.param_);
                        this.bitField0_ |= 0x8;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
            }
            
            public List<String> getParamList() {
                return Collections.unmodifiableList((List<? extends String>)this.param_);
            }
            
            public int getParamCount() {
                return this.param_.size();
            }
            
            public String getParam(final int n) {
                return (String)this.param_.get(n);
            }
            
            public ByteString getParamBytes(final int n) {
                return this.param_.getByteString(n);
            }
            
            public Builder setParam(final int n, final String s) {
                try {
                    if (s == null) {
                        throw new NullPointerException();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.a();
                this.param_.set(n, (Object)s);
                this.onChanged();
                return this;
            }
            
            public Builder addParam(final String s) {
                try {
                    if (s == null) {
                        throw new NullPointerException();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.a();
                this.param_.add((Object)s);
                this.onChanged();
                return this;
            }
            
            public Builder addAllParam(final Iterable<String> iterable) {
                this.a();
                GeneratedMessage.Builder.addAll((Iterable)iterable, (Collection)this.param_);
                this.onChanged();
                return this;
            }
            
            public Builder clearParam() {
                this.param_ = LazyStringArrayList.EMPTY;
                this.bitField0_ &= 0xFFFFFFF7;
                this.onChanged();
                return this;
            }
            
            public Builder addParamBytes(final ByteString byteString) {
                try {
                    if (byteString == null) {
                        throw new NullPointerException();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.a();
                this.param_.add(byteString);
                this.onChanged();
                return this;
            }
            
            public boolean hasStdinPath() {
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
            
            public String getStdinPath() {
                final Object stdinPath_ = this.stdinPath_;
                if (!(stdinPath_ instanceof String)) {
                    return (String)(this.stdinPath_ = ((ByteString)stdinPath_).toStringUtf8());
                }
                return (String)stdinPath_;
            }
            
            public ByteString getStdinPathBytes() {
                final Object stdinPath_ = this.stdinPath_;
                if (stdinPath_ instanceof String) {
                    return (ByteString)(this.stdinPath_ = ByteString.copyFromUtf8((String)stdinPath_));
                }
                return (ByteString)stdinPath_;
            }
            
            public Builder setStdinPath(final String stdinPath_) {
                try {
                    if (stdinPath_ == null) {
                        throw new NullPointerException();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.bitField0_ |= 0x10;
                this.stdinPath_ = stdinPath_;
                this.onChanged();
                return this;
            }
            
            public Builder clearStdinPath() {
                this.bitField0_ &= 0xFFFFFFEF;
                this.stdinPath_ = CommandLine.getDefaultInstance().getStdinPath();
                this.onChanged();
                return this;
            }
            
            public Builder setStdinPathBytes(final ByteString stdinPath_) {
                try {
                    if (stdinPath_ == null) {
                        throw new NullPointerException();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.bitField0_ |= 0x10;
                this.stdinPath_ = stdinPath_;
                this.onChanged();
                return this;
            }
            
            public boolean hasStdoutPath() {
                try {
                    if ((this.bitField0_ & 0x20) == 0x20) {
                        return true;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                return false;
            }
            
            public String getStdoutPath() {
                final Object stdoutPath_ = this.stdoutPath_;
                if (!(stdoutPath_ instanceof String)) {
                    return (String)(this.stdoutPath_ = ((ByteString)stdoutPath_).toStringUtf8());
                }
                return (String)stdoutPath_;
            }
            
            public ByteString getStdoutPathBytes() {
                final Object stdoutPath_ = this.stdoutPath_;
                if (stdoutPath_ instanceof String) {
                    return (ByteString)(this.stdoutPath_ = ByteString.copyFromUtf8((String)stdoutPath_));
                }
                return (ByteString)stdoutPath_;
            }
            
            public Builder setStdoutPath(final String stdoutPath_) {
                try {
                    if (stdoutPath_ == null) {
                        throw new NullPointerException();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.bitField0_ |= 0x20;
                this.stdoutPath_ = stdoutPath_;
                this.onChanged();
                return this;
            }
            
            public Builder clearStdoutPath() {
                this.bitField0_ &= 0xFFFFFFDF;
                this.stdoutPath_ = CommandLine.getDefaultInstance().getStdoutPath();
                this.onChanged();
                return this;
            }
            
            public Builder setStdoutPathBytes(final ByteString stdoutPath_) {
                try {
                    if (stdoutPath_ == null) {
                        throw new NullPointerException();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.bitField0_ |= 0x20;
                this.stdoutPath_ = stdoutPath_;
                this.onChanged();
                return this;
            }
            
            public boolean hasStderrPath() {
                try {
                    if ((this.bitField0_ & 0x40) == 0x40) {
                        return true;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                return false;
            }
            
            public String getStderrPath() {
                final Object stderrPath_ = this.stderrPath_;
                if (!(stderrPath_ instanceof String)) {
                    return (String)(this.stderrPath_ = ((ByteString)stderrPath_).toStringUtf8());
                }
                return (String)stderrPath_;
            }
            
            public ByteString getStderrPathBytes() {
                final Object stderrPath_ = this.stderrPath_;
                if (stderrPath_ instanceof String) {
                    return (ByteString)(this.stderrPath_ = ByteString.copyFromUtf8((String)stderrPath_));
                }
                return (ByteString)stderrPath_;
            }
            
            public Builder setStderrPath(final String stderrPath_) {
                try {
                    if (stderrPath_ == null) {
                        throw new NullPointerException();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.bitField0_ |= 0x40;
                this.stderrPath_ = stderrPath_;
                this.onChanged();
                return this;
            }
            
            public Builder clearStderrPath() {
                this.bitField0_ &= 0xFFFFFFBF;
                this.stderrPath_ = CommandLine.getDefaultInstance().getStderrPath();
                this.onChanged();
                return this;
            }
            
            public Builder setStderrPathBytes(final ByteString stderrPath_) {
                try {
                    if (stderrPath_ == null) {
                        throw new NullPointerException();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.bitField0_ |= 0x40;
                this.stderrPath_ = stderrPath_;
                this.onChanged();
                return this;
            }
            
            private static Exception b(final Exception ex) {
                return ex;
            }
        }
    }
    
    public static final class ThreadStopReasonInfo extends GeneratedMessage implements ThreadStopReasonInfoOrBuilder
    {
        private static final ThreadStopReasonInfo defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<ThreadStopReasonInfo> PARSER;
        private int bitField0_;
        public static final int STOP_REASON_FIELD_NUMBER = 1;
        private ThreadStopReason stopReason_;
        public static final int STOP_DESCRIPTION_FIELD_NUMBER = 2;
        private Object stopDescription_;
        public static final int SIGNAL_FIELD_NUMBER = 3;
        private int signal_;
        public static final int SIGNAL_NAME_FIELD_NUMBER = 4;
        private Object signalName_;
        public static final int CODEPOINT_ID_FIELD_NUMBER = 5;
        private int codepointId_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private static final long serialVersionUID = 0L;
        
        private ThreadStopReasonInfo(final GeneratedMessage.Builder<?> builder) {
            super((GeneratedMessage.Builder)builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }
        
        private ThreadStopReasonInfo(final boolean b) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }
        
        public static ThreadStopReasonInfo getDefaultInstance() {
            return ThreadStopReasonInfo.defaultInstance;
        }
        
        public ThreadStopReasonInfo getDefaultInstanceForType() {
            return ThreadStopReasonInfo.defaultInstance;
        }
        
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }
        
        private ThreadStopReasonInfo(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.a();
            final UnknownFieldSet.Builder builder = UnknownFieldSet.newBuilder();
            try {
                int i = 0;
                while (i == 0) {
                    final int tag = codedInputStream.readTag();
                    switch (tag) {
                        case 0: {
                            i = 1;
                            continue;
                        }
                        default: {
                            if (!this.parseUnknownField(codedInputStream, builder, extensionRegistryLite, tag)) {
                                i = 1;
                                continue;
                            }
                            continue;
                        }
                        case 8: {
                            final int enum1 = codedInputStream.readEnum();
                            final ThreadStopReason value = ThreadStopReason.valueOf(enum1);
                            try {
                                if (value == null) {
                                    builder.mergeVarintField(1, enum1);
                                    continue;
                                }
                            }
                            catch (InvalidProtocolBufferException ex) {
                                throw a((IOException)ex);
                            }
                            this.bitField0_ |= 0x1;
                            this.stopReason_ = value;
                            continue;
                        }
                        case 18: {
                            this.bitField0_ |= 0x2;
                            this.stopDescription_ = codedInputStream.readBytes();
                            continue;
                        }
                        case 24: {
                            this.bitField0_ |= 0x4;
                            this.signal_ = codedInputStream.readInt32();
                            continue;
                        }
                        case 34: {
                            this.bitField0_ |= 0x8;
                            this.signalName_ = codedInputStream.readBytes();
                            continue;
                        }
                        case 40: {
                            this.bitField0_ |= 0x10;
                            this.codepointId_ = codedInputStream.readInt32();
                            continue;
                        }
                    }
                }
            }
            catch (InvalidProtocolBufferException ex2) {
                throw ex2.setUnfinishedMessage((MessageLite)this);
            }
            catch (IOException ex3) {
                throw new InvalidProtocolBufferException(ex3.getMessage()).setUnfinishedMessage((MessageLite)this);
            }
            finally {
                this.unknownFields = builder.build();
                this.makeExtensionsImmutable();
            }
        }
        
        public static final Descriptors.Descriptor getDescriptor() {
            return Model.internal_static_ThreadStopReasonInfo_descriptor;
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Model.internal_static_ThreadStopReasonInfo_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)ThreadStopReasonInfo.class, (Class)Builder.class);
        }
        
        public Parser<ThreadStopReasonInfo> getParserForType() {
            return ThreadStopReasonInfo.PARSER;
        }
        
        public boolean hasStopReason() {
            return (this.bitField0_ & 0x1) == 0x1;
        }
        
        public ThreadStopReason getStopReason() {
            return this.stopReason_;
        }
        
        public boolean hasStopDescription() {
            return (this.bitField0_ & 0x2) == 0x2;
        }
        
        public String getStopDescription() {
            final Object stopDescription_ = this.stopDescription_;
            if (stopDescription_ instanceof String) {
                return (String)stopDescription_;
            }
            final ByteString byteString = (ByteString)stopDescription_;
            final String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.stopDescription_ = stringUtf8;
            }
            return stringUtf8;
        }
        
        public ByteString getStopDescriptionBytes() {
            final Object stopDescription_ = this.stopDescription_;
            if (stopDescription_ instanceof String) {
                return (ByteString)(this.stopDescription_ = ByteString.copyFromUtf8((String)stopDescription_));
            }
            return (ByteString)stopDescription_;
        }
        
        public boolean hasSignal() {
            return (this.bitField0_ & 0x4) == 0x4;
        }
        
        public int getSignal() {
            return this.signal_;
        }
        
        public boolean hasSignalName() {
            return (this.bitField0_ & 0x8) == 0x8;
        }
        
        public String getSignalName() {
            final Object signalName_ = this.signalName_;
            if (signalName_ instanceof String) {
                return (String)signalName_;
            }
            final ByteString byteString = (ByteString)signalName_;
            final String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.signalName_ = stringUtf8;
            }
            return stringUtf8;
        }
        
        public ByteString getSignalNameBytes() {
            final Object signalName_ = this.signalName_;
            if (signalName_ instanceof String) {
                return (ByteString)(this.signalName_ = ByteString.copyFromUtf8((String)signalName_));
            }
            return (ByteString)signalName_;
        }
        
        public boolean hasCodepointId() {
            return (this.bitField0_ & 0x10) == 0x10;
        }
        
        public int getCodepointId() {
            return this.codepointId_;
        }
        
        private void a() {
            this.stopReason_ = ThreadStopReason.ThreadStopReasonInvalid;
            this.stopDescription_ = "";
            this.signal_ = 0;
            this.signalName_ = "";
            this.codepointId_ = 0;
        }
        
        public final boolean isInitialized() {
            final byte memoizedIsInitialized = this.memoizedIsInitialized;
            if (memoizedIsInitialized != -1) {
                return memoizedIsInitialized == 1;
            }
            if (!this.hasStopReason()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (!this.hasStopDescription()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }
        
        public void writeTo(final CodedOutputStream codedOutputStream) throws IOException {
            try {
                this.getSerializedSize();
                if ((this.bitField0_ & 0x1) == 0x1) {
                    codedOutputStream.writeEnum(1, this.stopReason_.getNumber());
                }
            }
            catch (IOException ex) {
                throw a(ex);
            }
            try {
                if ((this.bitField0_ & 0x2) == 0x2) {
                    codedOutputStream.writeBytes(2, this.getStopDescriptionBytes());
                }
            }
            catch (IOException ex2) {
                throw a(ex2);
            }
            try {
                if ((this.bitField0_ & 0x4) == 0x4) {
                    codedOutputStream.writeInt32(3, this.signal_);
                }
            }
            catch (IOException ex3) {
                throw a(ex3);
            }
            try {
                if ((this.bitField0_ & 0x8) == 0x8) {
                    codedOutputStream.writeBytes(4, this.getSignalNameBytes());
                }
            }
            catch (IOException ex4) {
                throw a(ex4);
            }
            try {
                if ((this.bitField0_ & 0x10) == 0x10) {
                    codedOutputStream.writeInt32(5, this.codepointId_);
                }
            }
            catch (IOException ex5) {
                throw a(ex5);
            }
            this.getUnknownFields().writeTo(codedOutputStream);
        }
        
        public int getSerializedSize() {
            final int memoizedSerializedSize = this.memoizedSerializedSize;
            if (memoizedSerializedSize != -1) {
                return memoizedSerializedSize;
            }
            int n = 0;
            if ((this.bitField0_ & 0x1) == 0x1) {
                n += CodedOutputStream.computeEnumSize(1, this.stopReason_.getNumber());
            }
            if ((this.bitField0_ & 0x2) == 0x2) {
                n += CodedOutputStream.computeBytesSize(2, this.getStopDescriptionBytes());
            }
            if ((this.bitField0_ & 0x4) == 0x4) {
                n += CodedOutputStream.computeInt32Size(3, this.signal_);
            }
            if ((this.bitField0_ & 0x8) == 0x8) {
                n += CodedOutputStream.computeBytesSize(4, this.getSignalNameBytes());
            }
            if ((this.bitField0_ & 0x10) == 0x10) {
                n += CodedOutputStream.computeInt32Size(5, this.codepointId_);
            }
            return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
        }
        
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }
        
        public static ThreadStopReasonInfo parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
            return (ThreadStopReasonInfo)ThreadStopReasonInfo.PARSER.parseFrom(byteString);
        }
        
        public static ThreadStopReasonInfo parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ThreadStopReasonInfo)ThreadStopReasonInfo.PARSER.parseFrom(byteString, extensionRegistryLite);
        }
        
        public static ThreadStopReasonInfo parseFrom(final byte[] array) throws InvalidProtocolBufferException {
            return (ThreadStopReasonInfo)ThreadStopReasonInfo.PARSER.parseFrom(array);
        }
        
        public static ThreadStopReasonInfo parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ThreadStopReasonInfo)ThreadStopReasonInfo.PARSER.parseFrom(array, extensionRegistryLite);
        }
        
        public static ThreadStopReasonInfo parseFrom(final InputStream inputStream) throws IOException {
            return (ThreadStopReasonInfo)ThreadStopReasonInfo.PARSER.parseFrom(inputStream);
        }
        
        public static ThreadStopReasonInfo parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ThreadStopReasonInfo)ThreadStopReasonInfo.PARSER.parseFrom(inputStream, extensionRegistryLite);
        }
        
        public static ThreadStopReasonInfo parseDelimitedFrom(final InputStream inputStream) throws IOException {
            return (ThreadStopReasonInfo)ThreadStopReasonInfo.PARSER.parseDelimitedFrom(inputStream);
        }
        
        public static ThreadStopReasonInfo parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ThreadStopReasonInfo)ThreadStopReasonInfo.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }
        
        public static ThreadStopReasonInfo parseFrom(final CodedInputStream codedInputStream) throws IOException {
            return (ThreadStopReasonInfo)ThreadStopReasonInfo.PARSER.parseFrom(codedInputStream);
        }
        
        public static ThreadStopReasonInfo parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ThreadStopReasonInfo)ThreadStopReasonInfo.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }
        
        public static Builder newBuilder() {
            return a();
        }
        
        public Builder newBuilderForType() {
            return newBuilder();
        }
        
        public static Builder newBuilder(final ThreadStopReasonInfo threadStopReasonInfo) {
            return newBuilder().mergeFrom(threadStopReasonInfo);
        }
        
        public Builder toBuilder() {
            return newBuilder(this);
        }
        
        protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
            return new Builder(builderParent);
        }
        
        static {
            ThreadStopReasonInfo.PARSER = (Parser<ThreadStopReasonInfo>)new AbstractParser<ThreadStopReasonInfo>() {
                public ThreadStopReasonInfo parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new ThreadStopReasonInfo(codedInputStream, extensionRegistryLite);
                }
            };
            (defaultInstance = new ThreadStopReasonInfo(true)).a();
        }
        
        private static IOException a(final IOException ex) {
            return ex;
        }
        
        public static final class Builder extends GeneratedMessage.Builder<Builder> implements ThreadStopReasonInfoOrBuilder
        {
            private int bitField0_;
            private ThreadStopReason stopReason_;
            private Object stopDescription_;
            private int signal_;
            private Object signalName_;
            private int codepointId_;
            
            public static final Descriptors.Descriptor getDescriptor() {
                return Model.internal_static_ThreadStopReasonInfo_descriptor;
            }
            
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return Model.internal_static_ThreadStopReasonInfo_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)ThreadStopReasonInfo.class, (Class)Builder.class);
            }
            
            private Builder() {
                this.stopReason_ = ThreadStopReason.ThreadStopReasonInvalid;
                this.stopDescription_ = "";
                this.signalName_ = "";
                this.b();
            }
            
            private Builder(final GeneratedMessage.BuilderParent builderParent) {
                super(builderParent);
                this.stopReason_ = ThreadStopReason.ThreadStopReasonInvalid;
                this.stopDescription_ = "";
                this.signalName_ = "";
                this.b();
            }
            
            private void b() {
                if (ThreadStopReasonInfo.alwaysUseFieldBuilders) {}
            }
            
            private static Builder a() {
                return new Builder();
            }
            
            public Builder clear() {
                super.clear();
                this.stopReason_ = ThreadStopReason.ThreadStopReasonInvalid;
                this.bitField0_ &= 0xFFFFFFFE;
                this.stopDescription_ = "";
                this.bitField0_ &= 0xFFFFFFFD;
                this.signal_ = 0;
                this.bitField0_ &= 0xFFFFFFFB;
                this.signalName_ = "";
                this.bitField0_ &= 0xFFFFFFF7;
                this.codepointId_ = 0;
                this.bitField0_ &= 0xFFFFFFEF;
                return this;
            }
            
            public Builder clone() {
                return a().mergeFrom(this.buildPartial());
            }
            
            public Descriptors.Descriptor getDescriptorForType() {
                return Model.internal_static_ThreadStopReasonInfo_descriptor;
            }
            
            public ThreadStopReasonInfo getDefaultInstanceForType() {
                return ThreadStopReasonInfo.getDefaultInstance();
            }
            
            public ThreadStopReasonInfo build() {
                final ThreadStopReasonInfo buildPartial = this.buildPartial();
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
            
            public ThreadStopReasonInfo buildPartial() {
                final ThreadStopReasonInfo threadStopReasonInfo = new ThreadStopReasonInfo((GeneratedMessage.Builder)this);
                final int bitField0_ = this.bitField0_;
                int n = 0;
                if ((bitField0_ & 0x1) == 0x1) {
                    n |= 0x1;
                }
                threadStopReasonInfo.stopReason_ = this.stopReason_;
                if ((bitField0_ & 0x2) == 0x2) {
                    n |= 0x2;
                }
                threadStopReasonInfo.stopDescription_ = this.stopDescription_;
                if ((bitField0_ & 0x4) == 0x4) {
                    n |= 0x4;
                }
                threadStopReasonInfo.signal_ = this.signal_;
                if ((bitField0_ & 0x8) == 0x8) {
                    n |= 0x8;
                }
                threadStopReasonInfo.signalName_ = this.signalName_;
                if ((bitField0_ & 0x10) == 0x10) {
                    n |= 0x10;
                }
                threadStopReasonInfo.codepointId_ = this.codepointId_;
                threadStopReasonInfo.bitField0_ = n;
                this.onBuilt();
                return threadStopReasonInfo;
            }
            
            public Builder mergeFrom(final Message message) {
                try {
                    if (message instanceof ThreadStopReasonInfo) {
                        return this.mergeFrom((ThreadStopReasonInfo)message);
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                super.mergeFrom(message);
                return this;
            }
            
            public Builder mergeFrom(final ThreadStopReasonInfo threadStopReasonInfo) {
                try {
                    if (threadStopReasonInfo == ThreadStopReasonInfo.getDefaultInstance()) {
                        return this;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                try {
                    if (threadStopReasonInfo.hasStopReason()) {
                        this.setStopReason(threadStopReasonInfo.getStopReason());
                    }
                }
                catch (NullPointerException ex2) {
                    throw b(ex2);
                }
                try {
                    if (threadStopReasonInfo.hasStopDescription()) {
                        this.bitField0_ |= 0x2;
                        this.stopDescription_ = threadStopReasonInfo.stopDescription_;
                        this.onChanged();
                    }
                }
                catch (NullPointerException ex3) {
                    throw b(ex3);
                }
                try {
                    if (threadStopReasonInfo.hasSignal()) {
                        this.setSignal(threadStopReasonInfo.getSignal());
                    }
                }
                catch (NullPointerException ex4) {
                    throw b(ex4);
                }
                try {
                    if (threadStopReasonInfo.hasSignalName()) {
                        this.bitField0_ |= 0x8;
                        this.signalName_ = threadStopReasonInfo.signalName_;
                        this.onChanged();
                    }
                }
                catch (NullPointerException ex5) {
                    throw b(ex5);
                }
                try {
                    if (threadStopReasonInfo.hasCodepointId()) {
                        this.setCodepointId(threadStopReasonInfo.getCodepointId());
                    }
                }
                catch (NullPointerException ex6) {
                    throw b(ex6);
                }
                this.mergeUnknownFields(threadStopReasonInfo.getUnknownFields());
                return this;
            }
            
            public final boolean isInitialized() {
                try {
                    if (!this.hasStopReason()) {
                        return false;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                try {
                    if (!this.hasStopDescription()) {
                        return false;
                    }
                }
                catch (NullPointerException ex2) {
                    throw b(ex2);
                }
                return true;
            }
            
            public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
                ThreadStopReasonInfo threadStopReasonInfo = null;
                try {
                    threadStopReasonInfo = (ThreadStopReasonInfo)ThreadStopReasonInfo.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                }
                catch (InvalidProtocolBufferException ex) {
                    threadStopReasonInfo = (ThreadStopReasonInfo)ex.getUnfinishedMessage();
                    throw ex;
                }
                finally {
                    try {
                        if (threadStopReasonInfo != null) {
                            this.mergeFrom(threadStopReasonInfo);
                        }
                    }
                    catch (InvalidProtocolBufferException ex2) {
                        throw b((Exception)ex2);
                    }
                }
                return this;
            }
            
            public boolean hasStopReason() {
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
            
            public ThreadStopReason getStopReason() {
                return this.stopReason_;
            }
            
            public Builder setStopReason(final ThreadStopReason stopReason_) {
                try {
                    if (stopReason_ == null) {
                        throw new NullPointerException();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.bitField0_ |= 0x1;
                this.stopReason_ = stopReason_;
                this.onChanged();
                return this;
            }
            
            public Builder clearStopReason() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.stopReason_ = ThreadStopReason.ThreadStopReasonInvalid;
                this.onChanged();
                return this;
            }
            
            public boolean hasStopDescription() {
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
            
            public String getStopDescription() {
                final Object stopDescription_ = this.stopDescription_;
                if (!(stopDescription_ instanceof String)) {
                    return (String)(this.stopDescription_ = ((ByteString)stopDescription_).toStringUtf8());
                }
                return (String)stopDescription_;
            }
            
            public ByteString getStopDescriptionBytes() {
                final Object stopDescription_ = this.stopDescription_;
                if (stopDescription_ instanceof String) {
                    return (ByteString)(this.stopDescription_ = ByteString.copyFromUtf8((String)stopDescription_));
                }
                return (ByteString)stopDescription_;
            }
            
            public Builder setStopDescription(final String stopDescription_) {
                try {
                    if (stopDescription_ == null) {
                        throw new NullPointerException();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.bitField0_ |= 0x2;
                this.stopDescription_ = stopDescription_;
                this.onChanged();
                return this;
            }
            
            public Builder clearStopDescription() {
                this.bitField0_ &= 0xFFFFFFFD;
                this.stopDescription_ = ThreadStopReasonInfo.getDefaultInstance().getStopDescription();
                this.onChanged();
                return this;
            }
            
            public Builder setStopDescriptionBytes(final ByteString stopDescription_) {
                try {
                    if (stopDescription_ == null) {
                        throw new NullPointerException();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.bitField0_ |= 0x2;
                this.stopDescription_ = stopDescription_;
                this.onChanged();
                return this;
            }
            
            public boolean hasSignal() {
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
            
            public int getSignal() {
                return this.signal_;
            }
            
            public Builder setSignal(final int signal_) {
                this.bitField0_ |= 0x4;
                this.signal_ = signal_;
                this.onChanged();
                return this;
            }
            
            public Builder clearSignal() {
                this.bitField0_ &= 0xFFFFFFFB;
                this.signal_ = 0;
                this.onChanged();
                return this;
            }
            
            public boolean hasSignalName() {
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
            
            public String getSignalName() {
                final Object signalName_ = this.signalName_;
                if (!(signalName_ instanceof String)) {
                    return (String)(this.signalName_ = ((ByteString)signalName_).toStringUtf8());
                }
                return (String)signalName_;
            }
            
            public ByteString getSignalNameBytes() {
                final Object signalName_ = this.signalName_;
                if (signalName_ instanceof String) {
                    return (ByteString)(this.signalName_ = ByteString.copyFromUtf8((String)signalName_));
                }
                return (ByteString)signalName_;
            }
            
            public Builder setSignalName(final String signalName_) {
                try {
                    if (signalName_ == null) {
                        throw new NullPointerException();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.bitField0_ |= 0x8;
                this.signalName_ = signalName_;
                this.onChanged();
                return this;
            }
            
            public Builder clearSignalName() {
                this.bitField0_ &= 0xFFFFFFF7;
                this.signalName_ = ThreadStopReasonInfo.getDefaultInstance().getSignalName();
                this.onChanged();
                return this;
            }
            
            public Builder setSignalNameBytes(final ByteString signalName_) {
                try {
                    if (signalName_ == null) {
                        throw new NullPointerException();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.bitField0_ |= 0x8;
                this.signalName_ = signalName_;
                this.onChanged();
                return this;
            }
            
            public boolean hasCodepointId() {
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
            
            public int getCodepointId() {
                return this.codepointId_;
            }
            
            public Builder setCodepointId(final int codepointId_) {
                this.bitField0_ |= 0x10;
                this.codepointId_ = codepointId_;
                this.onChanged();
                return this;
            }
            
            public Builder clearCodepointId() {
                this.bitField0_ &= 0xFFFFFFEF;
                this.codepointId_ = 0;
                this.onChanged();
                return this;
            }
            
            private static Exception b(final Exception ex) {
                return ex;
            }
        }
    }
    
    public static final class LLDBThread extends GeneratedMessage implements LLDBThreadOrBuilder
    {
        private static final LLDBThread defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<LLDBThread> PARSER;
        private int bitField0_;
        public static final int ID_FIELD_NUMBER = 1;
        private int id_;
        public static final int NAME_FIELD_NUMBER = 2;
        private Object name_;
        public static final int QUEUE_FIELD_NUMBER = 3;
        private Object queue_;
        public static final int STOP_REASON_INFO_FIELD_NUMBER = 4;
        private ThreadStopReasonInfo stopReasonInfo_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private static final long serialVersionUID = 0L;
        
        private LLDBThread(final GeneratedMessage.Builder<?> builder) {
            super((GeneratedMessage.Builder)builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }
        
        private LLDBThread(final boolean b) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }
        
        public static LLDBThread getDefaultInstance() {
            return LLDBThread.defaultInstance;
        }
        
        public LLDBThread getDefaultInstanceForType() {
            return LLDBThread.defaultInstance;
        }
        
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }
        
        private LLDBThread(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.a();
            final UnknownFieldSet.Builder builder = UnknownFieldSet.newBuilder();
            try {
                int i = 0;
                while (i == 0) {
                    final int tag = codedInputStream.readTag();
                    switch (tag) {
                        case 0: {
                            i = 1;
                            continue;
                        }
                        default: {
                            if (!this.parseUnknownField(codedInputStream, builder, extensionRegistryLite, tag)) {
                                i = 1;
                                continue;
                            }
                            continue;
                        }
                        case 8: {
                            this.bitField0_ |= 0x1;
                            this.id_ = codedInputStream.readUInt32();
                            continue;
                        }
                        case 18: {
                            this.bitField0_ |= 0x2;
                            this.name_ = codedInputStream.readBytes();
                            continue;
                        }
                        case 26: {
                            this.bitField0_ |= 0x4;
                            this.queue_ = codedInputStream.readBytes();
                            continue;
                        }
                        case 34: {
                            ThreadStopReasonInfo.Builder builder2 = null;
                            if ((this.bitField0_ & 0x8) == 0x8) {
                                builder2 = this.stopReasonInfo_.toBuilder();
                            }
                            try {
                                this.stopReasonInfo_ = (ThreadStopReasonInfo)codedInputStream.readMessage((Parser)ThreadStopReasonInfo.PARSER, extensionRegistryLite);
                                if (builder2 != null) {
                                    builder2.mergeFrom(this.stopReasonInfo_);
                                    this.stopReasonInfo_ = builder2.buildPartial();
                                }
                            }
                            catch (InvalidProtocolBufferException ex) {
                                throw a((IOException)ex);
                            }
                            this.bitField0_ |= 0x8;
                            continue;
                        }
                    }
                }
            }
            catch (InvalidProtocolBufferException ex2) {
                throw ex2.setUnfinishedMessage((MessageLite)this);
            }
            catch (IOException ex3) {
                throw new InvalidProtocolBufferException(ex3.getMessage()).setUnfinishedMessage((MessageLite)this);
            }
            finally {
                this.unknownFields = builder.build();
                this.makeExtensionsImmutable();
            }
        }
        
        public static final Descriptors.Descriptor getDescriptor() {
            return Model.internal_static_LLDBThread_descriptor;
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Model.internal_static_LLDBThread_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)LLDBThread.class, (Class)Builder.class);
        }
        
        public Parser<LLDBThread> getParserForType() {
            return LLDBThread.PARSER;
        }
        
        public boolean hasId() {
            return (this.bitField0_ & 0x1) == 0x1;
        }
        
        public int getId() {
            return this.id_;
        }
        
        public boolean hasName() {
            return (this.bitField0_ & 0x2) == 0x2;
        }
        
        public String getName() {
            final Object name_ = this.name_;
            if (name_ instanceof String) {
                return (String)name_;
            }
            final ByteString byteString = (ByteString)name_;
            final String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.name_ = stringUtf8;
            }
            return stringUtf8;
        }
        
        public ByteString getNameBytes() {
            final Object name_ = this.name_;
            if (name_ instanceof String) {
                return (ByteString)(this.name_ = ByteString.copyFromUtf8((String)name_));
            }
            return (ByteString)name_;
        }
        
        public boolean hasQueue() {
            return (this.bitField0_ & 0x4) == 0x4;
        }
        
        public String getQueue() {
            final Object queue_ = this.queue_;
            if (queue_ instanceof String) {
                return (String)queue_;
            }
            final ByteString byteString = (ByteString)queue_;
            final String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.queue_ = stringUtf8;
            }
            return stringUtf8;
        }
        
        public ByteString getQueueBytes() {
            final Object queue_ = this.queue_;
            if (queue_ instanceof String) {
                return (ByteString)(this.queue_ = ByteString.copyFromUtf8((String)queue_));
            }
            return (ByteString)queue_;
        }
        
        public boolean hasStopReasonInfo() {
            return (this.bitField0_ & 0x8) == 0x8;
        }
        
        public ThreadStopReasonInfo getStopReasonInfo() {
            return this.stopReasonInfo_;
        }
        
        public ThreadStopReasonInfoOrBuilder getStopReasonInfoOrBuilder() {
            return this.stopReasonInfo_;
        }
        
        private void a() {
            this.id_ = 0;
            this.name_ = "";
            this.queue_ = "";
            this.stopReasonInfo_ = ThreadStopReasonInfo.getDefaultInstance();
        }
        
        public final boolean isInitialized() {
            final byte memoizedIsInitialized = this.memoizedIsInitialized;
            if (memoizedIsInitialized != -1) {
                return memoizedIsInitialized == 1;
            }
            if (!this.hasId()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (this.hasStopReasonInfo() && !this.getStopReasonInfo().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }
        
        public void writeTo(final CodedOutputStream codedOutputStream) throws IOException {
            try {
                this.getSerializedSize();
                if ((this.bitField0_ & 0x1) == 0x1) {
                    codedOutputStream.writeUInt32(1, this.id_);
                }
            }
            catch (IOException ex) {
                throw a(ex);
            }
            try {
                if ((this.bitField0_ & 0x2) == 0x2) {
                    codedOutputStream.writeBytes(2, this.getNameBytes());
                }
            }
            catch (IOException ex2) {
                throw a(ex2);
            }
            try {
                if ((this.bitField0_ & 0x4) == 0x4) {
                    codedOutputStream.writeBytes(3, this.getQueueBytes());
                }
            }
            catch (IOException ex3) {
                throw a(ex3);
            }
            try {
                if ((this.bitField0_ & 0x8) == 0x8) {
                    codedOutputStream.writeMessage(4, (MessageLite)this.stopReasonInfo_);
                }
            }
            catch (IOException ex4) {
                throw a(ex4);
            }
            this.getUnknownFields().writeTo(codedOutputStream);
        }
        
        public int getSerializedSize() {
            final int memoizedSerializedSize = this.memoizedSerializedSize;
            if (memoizedSerializedSize != -1) {
                return memoizedSerializedSize;
            }
            int n = 0;
            if ((this.bitField0_ & 0x1) == 0x1) {
                n += CodedOutputStream.computeUInt32Size(1, this.id_);
            }
            if ((this.bitField0_ & 0x2) == 0x2) {
                n += CodedOutputStream.computeBytesSize(2, this.getNameBytes());
            }
            if ((this.bitField0_ & 0x4) == 0x4) {
                n += CodedOutputStream.computeBytesSize(3, this.getQueueBytes());
            }
            if ((this.bitField0_ & 0x8) == 0x8) {
                n += CodedOutputStream.computeMessageSize(4, (MessageLite)this.stopReasonInfo_);
            }
            return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
        }
        
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }
        
        public static LLDBThread parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
            return (LLDBThread)LLDBThread.PARSER.parseFrom(byteString);
        }
        
        public static LLDBThread parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (LLDBThread)LLDBThread.PARSER.parseFrom(byteString, extensionRegistryLite);
        }
        
        public static LLDBThread parseFrom(final byte[] array) throws InvalidProtocolBufferException {
            return (LLDBThread)LLDBThread.PARSER.parseFrom(array);
        }
        
        public static LLDBThread parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (LLDBThread)LLDBThread.PARSER.parseFrom(array, extensionRegistryLite);
        }
        
        public static LLDBThread parseFrom(final InputStream inputStream) throws IOException {
            return (LLDBThread)LLDBThread.PARSER.parseFrom(inputStream);
        }
        
        public static LLDBThread parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (LLDBThread)LLDBThread.PARSER.parseFrom(inputStream, extensionRegistryLite);
        }
        
        public static LLDBThread parseDelimitedFrom(final InputStream inputStream) throws IOException {
            return (LLDBThread)LLDBThread.PARSER.parseDelimitedFrom(inputStream);
        }
        
        public static LLDBThread parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (LLDBThread)LLDBThread.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }
        
        public static LLDBThread parseFrom(final CodedInputStream codedInputStream) throws IOException {
            return (LLDBThread)LLDBThread.PARSER.parseFrom(codedInputStream);
        }
        
        public static LLDBThread parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (LLDBThread)LLDBThread.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }
        
        public static Builder newBuilder() {
            return b();
        }
        
        public Builder newBuilderForType() {
            return newBuilder();
        }
        
        public static Builder newBuilder(final LLDBThread lldbThread) {
            return newBuilder().mergeFrom(lldbThread);
        }
        
        public Builder toBuilder() {
            return newBuilder(this);
        }
        
        protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
            return new Builder(builderParent);
        }
        
        static {
            LLDBThread.PARSER = (Parser<LLDBThread>)new AbstractParser<LLDBThread>() {
                public LLDBThread parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new LLDBThread(codedInputStream, extensionRegistryLite);
                }
            };
            (defaultInstance = new LLDBThread(true)).a();
        }
        
        private static IOException a(final IOException ex) {
            return ex;
        }
        
        public static final class Builder extends GeneratedMessage.Builder<Builder> implements LLDBThreadOrBuilder
        {
            private int bitField0_;
            private int id_;
            private Object name_;
            private Object queue_;
            private ThreadStopReasonInfo stopReasonInfo_;
            private SingleFieldBuilder<ThreadStopReasonInfo, ThreadStopReasonInfo.Builder, ThreadStopReasonInfoOrBuilder> stopReasonInfoBuilder_;
            
            public static final Descriptors.Descriptor getDescriptor() {
                return Model.internal_static_LLDBThread_descriptor;
            }
            
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return Model.internal_static_LLDBThread_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)LLDBThread.class, (Class)Builder.class);
            }
            
            private Builder() {
                this.name_ = "";
                this.queue_ = "";
                this.stopReasonInfo_ = ThreadStopReasonInfo.getDefaultInstance();
                this.c();
            }
            
            private Builder(final GeneratedMessage.BuilderParent builderParent) {
                super(builderParent);
                this.name_ = "";
                this.queue_ = "";
                this.stopReasonInfo_ = ThreadStopReasonInfo.getDefaultInstance();
                this.c();
            }
            
            private void c() {
                try {
                    if (LLDBThread.alwaysUseFieldBuilders) {
                        this.a();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
            }
            
            private static Builder b() {
                return new Builder();
            }
            
            public Builder clear() {
                Label_0084: {
                    try {
                        super.clear();
                        this.id_ = 0;
                        this.bitField0_ &= 0xFFFFFFFE;
                        this.name_ = "";
                        this.bitField0_ &= 0xFFFFFFFD;
                        this.queue_ = "";
                        this.bitField0_ &= 0xFFFFFFFB;
                        if (this.stopReasonInfoBuilder_ == null) {
                            this.stopReasonInfo_ = ThreadStopReasonInfo.getDefaultInstance();
                            break Label_0084;
                        }
                    }
                    catch (NullPointerException ex) {
                        throw b(ex);
                    }
                    this.stopReasonInfoBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFF7;
                return this;
            }
            
            public Builder clone() {
                return b().mergeFrom(this.buildPartial());
            }
            
            public Descriptors.Descriptor getDescriptorForType() {
                return Model.internal_static_LLDBThread_descriptor;
            }
            
            public LLDBThread getDefaultInstanceForType() {
                return LLDBThread.getDefaultInstance();
            }
            
            public LLDBThread build() {
                final LLDBThread buildPartial = this.buildPartial();
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
            
            public LLDBThread buildPartial() {
                final LLDBThread lldbThread = new LLDBThread((GeneratedMessage.Builder)this);
                final int bitField0_ = this.bitField0_;
                int n = 0;
                if ((bitField0_ & 0x1) == 0x1) {
                    n |= 0x1;
                }
                lldbThread.id_ = this.id_;
                if ((bitField0_ & 0x2) == 0x2) {
                    n |= 0x2;
                }
                lldbThread.name_ = this.name_;
                if ((bitField0_ & 0x4) == 0x4) {
                    n |= 0x4;
                }
                lldbThread.queue_ = this.queue_;
                if ((bitField0_ & 0x8) == 0x8) {
                    n |= 0x8;
                }
                Label_0129: {
                    try {
                        if (this.stopReasonInfoBuilder_ == null) {
                            lldbThread.stopReasonInfo_ = this.stopReasonInfo_;
                            break Label_0129;
                        }
                    }
                    catch (NullPointerException ex) {
                        throw b(ex);
                    }
                    lldbThread.stopReasonInfo_ = (ThreadStopReasonInfo)this.stopReasonInfoBuilder_.build();
                }
                lldbThread.bitField0_ = n;
                this.onBuilt();
                return lldbThread;
            }
            
            public Builder mergeFrom(final Message message) {
                try {
                    if (message instanceof LLDBThread) {
                        return this.mergeFrom((LLDBThread)message);
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                super.mergeFrom(message);
                return this;
            }
            
            public Builder mergeFrom(final LLDBThread lldbThread) {
                try {
                    if (lldbThread == LLDBThread.getDefaultInstance()) {
                        return this;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                try {
                    if (lldbThread.hasId()) {
                        this.setId(lldbThread.getId());
                    }
                }
                catch (NullPointerException ex2) {
                    throw b(ex2);
                }
                try {
                    if (lldbThread.hasName()) {
                        this.bitField0_ |= 0x2;
                        this.name_ = lldbThread.name_;
                        this.onChanged();
                    }
                }
                catch (NullPointerException ex3) {
                    throw b(ex3);
                }
                try {
                    if (lldbThread.hasQueue()) {
                        this.bitField0_ |= 0x4;
                        this.queue_ = lldbThread.queue_;
                        this.onChanged();
                    }
                }
                catch (NullPointerException ex4) {
                    throw b(ex4);
                }
                try {
                    if (lldbThread.hasStopReasonInfo()) {
                        this.mergeStopReasonInfo(lldbThread.getStopReasonInfo());
                    }
                }
                catch (NullPointerException ex5) {
                    throw b(ex5);
                }
                this.mergeUnknownFields(lldbThread.getUnknownFields());
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
                    if (!this.hasStopReasonInfo()) {
                        return true;
                    }
                    final Builder builder = this;
                    final ThreadStopReasonInfo threadStopReasonInfo = builder.getStopReasonInfo();
                    final boolean b = threadStopReasonInfo.isInitialized();
                    if (!b) {
                        return false;
                    }
                    return true;
                }
                catch (NullPointerException ex2) {
                    throw b(ex2);
                }
                try {
                    final Builder builder = this;
                    final ThreadStopReasonInfo threadStopReasonInfo = builder.getStopReasonInfo();
                    final boolean b = threadStopReasonInfo.isInitialized();
                    if (!b) {
                        return false;
                    }
                }
                catch (NullPointerException ex3) {
                    throw b(ex3);
                }
                return true;
            }
            
            public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
                LLDBThread lldbThread = null;
                try {
                    lldbThread = (LLDBThread)LLDBThread.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                }
                catch (InvalidProtocolBufferException ex) {
                    lldbThread = (LLDBThread)ex.getUnfinishedMessage();
                    throw ex;
                }
                finally {
                    try {
                        if (lldbThread != null) {
                            this.mergeFrom(lldbThread);
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
                this.name_ = LLDBThread.getDefaultInstance().getName();
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
            
            public boolean hasQueue() {
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
            
            public String getQueue() {
                final Object queue_ = this.queue_;
                if (!(queue_ instanceof String)) {
                    return (String)(this.queue_ = ((ByteString)queue_).toStringUtf8());
                }
                return (String)queue_;
            }
            
            public ByteString getQueueBytes() {
                final Object queue_ = this.queue_;
                if (queue_ instanceof String) {
                    return (ByteString)(this.queue_ = ByteString.copyFromUtf8((String)queue_));
                }
                return (ByteString)queue_;
            }
            
            public Builder setQueue(final String queue_) {
                try {
                    if (queue_ == null) {
                        throw new NullPointerException();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.bitField0_ |= 0x4;
                this.queue_ = queue_;
                this.onChanged();
                return this;
            }
            
            public Builder clearQueue() {
                this.bitField0_ &= 0xFFFFFFFB;
                this.queue_ = LLDBThread.getDefaultInstance().getQueue();
                this.onChanged();
                return this;
            }
            
            public Builder setQueueBytes(final ByteString queue_) {
                try {
                    if (queue_ == null) {
                        throw new NullPointerException();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.bitField0_ |= 0x4;
                this.queue_ = queue_;
                this.onChanged();
                return this;
            }
            
            public boolean hasStopReasonInfo() {
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
            
            public ThreadStopReasonInfo getStopReasonInfo() {
                try {
                    if (this.stopReasonInfoBuilder_ == null) {
                        return this.stopReasonInfo_;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                return (ThreadStopReasonInfo)this.stopReasonInfoBuilder_.getMessage();
            }
            
            public Builder setStopReasonInfo(final ThreadStopReasonInfo threadStopReasonInfo) {
                Label_0051: {
                    Label_0042: {
                        Label_0018: {
                            try {
                                if (this.stopReasonInfoBuilder_ != null) {
                                    break Label_0042;
                                }
                                final ThreadStopReasonInfo threadStopReasonInfo2 = threadStopReasonInfo;
                                if (threadStopReasonInfo2 == null) {
                                    break Label_0018;
                                }
                                break Label_0018;
                            }
                            catch (NullPointerException ex) {
                                throw b(ex);
                            }
                            try {
                                final ThreadStopReasonInfo threadStopReasonInfo2 = threadStopReasonInfo;
                                if (threadStopReasonInfo2 == null) {
                                    throw new NullPointerException();
                                }
                            }
                            catch (NullPointerException ex2) {
                                throw b(ex2);
                            }
                        }
                        this.stopReasonInfo_ = threadStopReasonInfo;
                        this.onChanged();
                        break Label_0051;
                    }
                    this.stopReasonInfoBuilder_.setMessage((GeneratedMessage)threadStopReasonInfo);
                }
                this.bitField0_ |= 0x8;
                return this;
            }
            
            public Builder setStopReasonInfo(final ThreadStopReasonInfo.Builder builder) {
                Label_0038: {
                    try {
                        if (this.stopReasonInfoBuilder_ == null) {
                            this.stopReasonInfo_ = builder.build();
                            this.onChanged();
                            break Label_0038;
                        }
                    }
                    catch (NullPointerException ex) {
                        throw b(ex);
                    }
                    this.stopReasonInfoBuilder_.setMessage((GeneratedMessage)builder.build());
                }
                this.bitField0_ |= 0x8;
                return this;
            }
            
            public Builder mergeStopReasonInfo(final ThreadStopReasonInfo p0) {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_0        
                //     1: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread$Builder.stopReasonInfoBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
                //     4: ifnonnull       80
                //     7: aload_0        
                //     8: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread$Builder.bitField0_:I
                //    11: bipush          8
                //    13: iand           
                //    14: bipush          8
                //    16: if_icmpne       68
                //    19: goto            26
                //    22: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                //    25: athrow         
                //    26: aload_0        
                //    27: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread$Builder.stopReasonInfo_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$ThreadStopReasonInfo;
                //    30: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$ThreadStopReasonInfo.getDefaultInstance:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$ThreadStopReasonInfo;
                //    33: if_acmpeq       68
                //    36: goto            43
                //    39: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                //    42: athrow         
                //    43: aload_0        
                //    44: aload_0        
                //    45: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread$Builder.stopReasonInfo_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$ThreadStopReasonInfo;
                //    48: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$ThreadStopReasonInfo.newBuilder:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$ThreadStopReasonInfo;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$ThreadStopReasonInfo$Builder;
                //    51: aload_1        
                //    52: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$ThreadStopReasonInfo$Builder.mergeFrom:(Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$ThreadStopReasonInfo;)Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$ThreadStopReasonInfo$Builder;
                //    55: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$ThreadStopReasonInfo$Builder.buildPartial:()Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$ThreadStopReasonInfo;
                //    58: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread$Builder.stopReasonInfo_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$ThreadStopReasonInfo;
                //    61: goto            73
                //    64: invokestatic    com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread$Builder.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
                //    67: athrow         
                //    68: aload_0        
                //    69: aload_1        
                //    70: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread$Builder.stopReasonInfo_:Lcom/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$ThreadStopReasonInfo;
                //    73: aload_0        
                //    74: invokevirtual   com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread$Builder.onChanged:()V
                //    77: goto            89
                //    80: aload_0        
                //    81: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread$Builder.stopReasonInfoBuilder_:Lcom/google/protobuf/SingleFieldBuilder;
                //    84: aload_1        
                //    85: invokevirtual   com/google/protobuf/SingleFieldBuilder.mergeFrom:(Lcom/google/protobuf/GeneratedMessage;)Lcom/google/protobuf/SingleFieldBuilder;
                //    88: pop            
                //    89: aload_0        
                //    90: dup            
                //    91: getfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread$Builder.bitField0_:I
                //    94: bipush          8
                //    96: ior            
                //    97: putfield        com/jetbrains/cidr/execution/debugger/backend/lldb/auto_generated/Model$LLDBThread$Builder.bitField0_:I
                //   100: aload_0        
                //   101: areturn        
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                            
                //  -----  -----  -----  -----  --------------------------------
                //  0      19     22     26     Ljava/lang/NullPointerException;
                //  7      36     39     43     Ljava/lang/NullPointerException;
                //  26     64     64     68     Ljava/lang/NullPointerException;
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0026:
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
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
            
            public Builder clearStopReasonInfo() {
                Label_0033: {
                    try {
                        if (this.stopReasonInfoBuilder_ == null) {
                            this.stopReasonInfo_ = ThreadStopReasonInfo.getDefaultInstance();
                            this.onChanged();
                            break Label_0033;
                        }
                    }
                    catch (NullPointerException ex) {
                        throw b(ex);
                    }
                    this.stopReasonInfoBuilder_.clear();
                }
                this.bitField0_ &= 0xFFFFFFF7;
                return this;
            }
            
            public ThreadStopReasonInfo.Builder getStopReasonInfoBuilder() {
                this.bitField0_ |= 0x8;
                this.onChanged();
                return (ThreadStopReasonInfo.Builder)this.a().getBuilder();
            }
            
            public ThreadStopReasonInfoOrBuilder getStopReasonInfoOrBuilder() {
                try {
                    if (this.stopReasonInfoBuilder_ != null) {
                        return (ThreadStopReasonInfoOrBuilder)this.stopReasonInfoBuilder_.getMessageOrBuilder();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                return this.stopReasonInfo_;
            }
            
            private SingleFieldBuilder<ThreadStopReasonInfo, ThreadStopReasonInfo.Builder, ThreadStopReasonInfoOrBuilder> a() {
                try {
                    if (this.stopReasonInfoBuilder_ == null) {
                        this.stopReasonInfoBuilder_ = (SingleFieldBuilder<ThreadStopReasonInfo, ThreadStopReasonInfo.Builder, ThreadStopReasonInfoOrBuilder>)new SingleFieldBuilder((GeneratedMessage)this.stopReasonInfo_, this.getParentForChildren(), this.isClean());
                        this.stopReasonInfo_ = null;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                return this.stopReasonInfoBuilder_;
            }
            
            private static Exception b(final Exception ex) {
                return ex;
            }
        }
    }
    
    public static final class LLDBFrame extends GeneratedMessage implements LLDBFrameOrBuilder
    {
        private static final LLDBFrame defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<LLDBFrame> PARSER;
        private int bitField0_;
        public static final int NUMBER_FIELD_NUMBER = 1;
        private int number_;
        public static final int FUNCTION_FIELD_NUMBER = 2;
        private Object function_;
        public static final int FILE_FIELD_NUMBER = 3;
        private Object file_;
        public static final int LINE_FIELD_NUMBER = 4;
        private int line_;
        public static final int PC_FIELD_NUMBER = 5;
        private long pc_;
        public static final int LANGUAGE_FIELD_NUMBER = 6;
        private Language language_;
        public static final int OPTIMIZED_FIELD_NUMBER = 7;
        private boolean optimized_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private static final long serialVersionUID = 0L;
        
        private LLDBFrame(final GeneratedMessage.Builder<?> builder) {
            super((GeneratedMessage.Builder)builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }
        
        private LLDBFrame(final boolean b) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }
        
        public static LLDBFrame getDefaultInstance() {
            return LLDBFrame.defaultInstance;
        }
        
        public LLDBFrame getDefaultInstanceForType() {
            return LLDBFrame.defaultInstance;
        }
        
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }
        
        private LLDBFrame(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.a();
            final UnknownFieldSet.Builder builder = UnknownFieldSet.newBuilder();
            try {
                int i = 0;
                while (i == 0) {
                    final int tag = codedInputStream.readTag();
                    switch (tag) {
                        case 0: {
                            i = 1;
                            continue;
                        }
                        default: {
                            if (!this.parseUnknownField(codedInputStream, builder, extensionRegistryLite, tag)) {
                                i = 1;
                                continue;
                            }
                            continue;
                        }
                        case 8: {
                            this.bitField0_ |= 0x1;
                            this.number_ = codedInputStream.readInt32();
                            continue;
                        }
                        case 18: {
                            this.bitField0_ |= 0x2;
                            this.function_ = codedInputStream.readBytes();
                            continue;
                        }
                        case 26: {
                            this.bitField0_ |= 0x4;
                            this.file_ = codedInputStream.readBytes();
                            continue;
                        }
                        case 32: {
                            this.bitField0_ |= 0x8;
                            this.line_ = codedInputStream.readInt32();
                            continue;
                        }
                        case 40: {
                            this.bitField0_ |= 0x10;
                            this.pc_ = codedInputStream.readInt64();
                            continue;
                        }
                        case 48: {
                            final int enum1 = codedInputStream.readEnum();
                            final Language value = Language.valueOf(enum1);
                            try {
                                if (value == null) {
                                    builder.mergeVarintField(6, enum1);
                                    continue;
                                }
                            }
                            catch (InvalidProtocolBufferException ex) {
                                throw a((IOException)ex);
                            }
                            this.bitField0_ |= 0x20;
                            this.language_ = value;
                            continue;
                        }
                        case 56: {
                            this.bitField0_ |= 0x40;
                            this.optimized_ = codedInputStream.readBool();
                            continue;
                        }
                    }
                }
            }
            catch (InvalidProtocolBufferException ex2) {
                throw ex2.setUnfinishedMessage((MessageLite)this);
            }
            catch (IOException ex3) {
                throw new InvalidProtocolBufferException(ex3.getMessage()).setUnfinishedMessage((MessageLite)this);
            }
            finally {
                this.unknownFields = builder.build();
                this.makeExtensionsImmutable();
            }
        }
        
        public static final Descriptors.Descriptor getDescriptor() {
            return Model.internal_static_LLDBFrame_descriptor;
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Model.internal_static_LLDBFrame_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)LLDBFrame.class, (Class)Builder.class);
        }
        
        public Parser<LLDBFrame> getParserForType() {
            return LLDBFrame.PARSER;
        }
        
        public boolean hasNumber() {
            return (this.bitField0_ & 0x1) == 0x1;
        }
        
        public int getNumber() {
            return this.number_;
        }
        
        public boolean hasFunction() {
            return (this.bitField0_ & 0x2) == 0x2;
        }
        
        public String getFunction() {
            final Object function_ = this.function_;
            if (function_ instanceof String) {
                return (String)function_;
            }
            final ByteString byteString = (ByteString)function_;
            final String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.function_ = stringUtf8;
            }
            return stringUtf8;
        }
        
        public ByteString getFunctionBytes() {
            final Object function_ = this.function_;
            if (function_ instanceof String) {
                return (ByteString)(this.function_ = ByteString.copyFromUtf8((String)function_));
            }
            return (ByteString)function_;
        }
        
        public boolean hasFile() {
            return (this.bitField0_ & 0x4) == 0x4;
        }
        
        public String getFile() {
            final Object file_ = this.file_;
            if (file_ instanceof String) {
                return (String)file_;
            }
            final ByteString byteString = (ByteString)file_;
            final String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.file_ = stringUtf8;
            }
            return stringUtf8;
        }
        
        public ByteString getFileBytes() {
            final Object file_ = this.file_;
            if (file_ instanceof String) {
                return (ByteString)(this.file_ = ByteString.copyFromUtf8((String)file_));
            }
            return (ByteString)file_;
        }
        
        public boolean hasLine() {
            return (this.bitField0_ & 0x8) == 0x8;
        }
        
        public int getLine() {
            return this.line_;
        }
        
        public boolean hasPc() {
            return (this.bitField0_ & 0x10) == 0x10;
        }
        
        public long getPc() {
            return this.pc_;
        }
        
        public boolean hasLanguage() {
            return (this.bitField0_ & 0x20) == 0x20;
        }
        
        public Language getLanguage() {
            return this.language_;
        }
        
        public boolean hasOptimized() {
            return (this.bitField0_ & 0x40) == 0x40;
        }
        
        public boolean getOptimized() {
            return this.optimized_;
        }
        
        private void a() {
            this.number_ = 0;
            this.function_ = "";
            this.file_ = "";
            this.line_ = 0;
            this.pc_ = 0L;
            this.language_ = Language.LanguageUnknown;
            this.optimized_ = false;
        }
        
        public final boolean isInitialized() {
            final byte memoizedIsInitialized = this.memoizedIsInitialized;
            if (memoizedIsInitialized != -1) {
                return memoizedIsInitialized == 1;
            }
            if (!this.hasNumber()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }
        
        public void writeTo(final CodedOutputStream codedOutputStream) throws IOException {
            try {
                this.getSerializedSize();
                if ((this.bitField0_ & 0x1) == 0x1) {
                    codedOutputStream.writeInt32(1, this.number_);
                }
            }
            catch (IOException ex) {
                throw a(ex);
            }
            try {
                if ((this.bitField0_ & 0x2) == 0x2) {
                    codedOutputStream.writeBytes(2, this.getFunctionBytes());
                }
            }
            catch (IOException ex2) {
                throw a(ex2);
            }
            try {
                if ((this.bitField0_ & 0x4) == 0x4) {
                    codedOutputStream.writeBytes(3, this.getFileBytes());
                }
            }
            catch (IOException ex3) {
                throw a(ex3);
            }
            try {
                if ((this.bitField0_ & 0x8) == 0x8) {
                    codedOutputStream.writeInt32(4, this.line_);
                }
            }
            catch (IOException ex4) {
                throw a(ex4);
            }
            try {
                if ((this.bitField0_ & 0x10) == 0x10) {
                    codedOutputStream.writeInt64(5, this.pc_);
                }
            }
            catch (IOException ex5) {
                throw a(ex5);
            }
            try {
                if ((this.bitField0_ & 0x20) == 0x20) {
                    codedOutputStream.writeEnum(6, this.language_.getNumber());
                }
            }
            catch (IOException ex6) {
                throw a(ex6);
            }
            try {
                if ((this.bitField0_ & 0x40) == 0x40) {
                    codedOutputStream.writeBool(7, this.optimized_);
                }
            }
            catch (IOException ex7) {
                throw a(ex7);
            }
            this.getUnknownFields().writeTo(codedOutputStream);
        }
        
        public int getSerializedSize() {
            final int memoizedSerializedSize = this.memoizedSerializedSize;
            if (memoizedSerializedSize != -1) {
                return memoizedSerializedSize;
            }
            int n = 0;
            if ((this.bitField0_ & 0x1) == 0x1) {
                n += CodedOutputStream.computeInt32Size(1, this.number_);
            }
            if ((this.bitField0_ & 0x2) == 0x2) {
                n += CodedOutputStream.computeBytesSize(2, this.getFunctionBytes());
            }
            if ((this.bitField0_ & 0x4) == 0x4) {
                n += CodedOutputStream.computeBytesSize(3, this.getFileBytes());
            }
            if ((this.bitField0_ & 0x8) == 0x8) {
                n += CodedOutputStream.computeInt32Size(4, this.line_);
            }
            if ((this.bitField0_ & 0x10) == 0x10) {
                n += CodedOutputStream.computeInt64Size(5, this.pc_);
            }
            if ((this.bitField0_ & 0x20) == 0x20) {
                n += CodedOutputStream.computeEnumSize(6, this.language_.getNumber());
            }
            if ((this.bitField0_ & 0x40) == 0x40) {
                n += CodedOutputStream.computeBoolSize(7, this.optimized_);
            }
            return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
        }
        
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }
        
        public static LLDBFrame parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
            return (LLDBFrame)LLDBFrame.PARSER.parseFrom(byteString);
        }
        
        public static LLDBFrame parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (LLDBFrame)LLDBFrame.PARSER.parseFrom(byteString, extensionRegistryLite);
        }
        
        public static LLDBFrame parseFrom(final byte[] array) throws InvalidProtocolBufferException {
            return (LLDBFrame)LLDBFrame.PARSER.parseFrom(array);
        }
        
        public static LLDBFrame parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (LLDBFrame)LLDBFrame.PARSER.parseFrom(array, extensionRegistryLite);
        }
        
        public static LLDBFrame parseFrom(final InputStream inputStream) throws IOException {
            return (LLDBFrame)LLDBFrame.PARSER.parseFrom(inputStream);
        }
        
        public static LLDBFrame parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (LLDBFrame)LLDBFrame.PARSER.parseFrom(inputStream, extensionRegistryLite);
        }
        
        public static LLDBFrame parseDelimitedFrom(final InputStream inputStream) throws IOException {
            return (LLDBFrame)LLDBFrame.PARSER.parseDelimitedFrom(inputStream);
        }
        
        public static LLDBFrame parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (LLDBFrame)LLDBFrame.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }
        
        public static LLDBFrame parseFrom(final CodedInputStream codedInputStream) throws IOException {
            return (LLDBFrame)LLDBFrame.PARSER.parseFrom(codedInputStream);
        }
        
        public static LLDBFrame parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (LLDBFrame)LLDBFrame.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }
        
        public static Builder newBuilder() {
            return b();
        }
        
        public Builder newBuilderForType() {
            return newBuilder();
        }
        
        public static Builder newBuilder(final LLDBFrame lldbFrame) {
            return newBuilder().mergeFrom(lldbFrame);
        }
        
        public Builder toBuilder() {
            return newBuilder(this);
        }
        
        protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
            return new Builder(builderParent);
        }
        
        static {
            LLDBFrame.PARSER = (Parser<LLDBFrame>)new AbstractParser<LLDBFrame>() {
                public LLDBFrame parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new LLDBFrame(codedInputStream, extensionRegistryLite);
                }
            };
            (defaultInstance = new LLDBFrame(true)).a();
        }
        
        private static IOException a(final IOException ex) {
            return ex;
        }
        
        public static final class Builder extends GeneratedMessage.Builder<Builder> implements LLDBFrameOrBuilder
        {
            private int bitField0_;
            private int number_;
            private Object function_;
            private Object file_;
            private int line_;
            private long pc_;
            private Language language_;
            private boolean optimized_;
            
            public static final Descriptors.Descriptor getDescriptor() {
                return Model.internal_static_LLDBFrame_descriptor;
            }
            
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return Model.internal_static_LLDBFrame_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)LLDBFrame.class, (Class)Builder.class);
            }
            
            private Builder() {
                this.function_ = "";
                this.file_ = "";
                this.language_ = Language.LanguageUnknown;
                this.a();
            }
            
            private Builder(final GeneratedMessage.BuilderParent builderParent) {
                super(builderParent);
                this.function_ = "";
                this.file_ = "";
                this.language_ = Language.LanguageUnknown;
                this.a();
            }
            
            private void a() {
                if (LLDBFrame.alwaysUseFieldBuilders) {}
            }
            
            private static Builder b() {
                return new Builder();
            }
            
            public Builder clear() {
                super.clear();
                this.number_ = 0;
                this.bitField0_ &= 0xFFFFFFFE;
                this.function_ = "";
                this.bitField0_ &= 0xFFFFFFFD;
                this.file_ = "";
                this.bitField0_ &= 0xFFFFFFFB;
                this.line_ = 0;
                this.bitField0_ &= 0xFFFFFFF7;
                this.pc_ = 0L;
                this.bitField0_ &= 0xFFFFFFEF;
                this.language_ = Language.LanguageUnknown;
                this.bitField0_ &= 0xFFFFFFDF;
                this.optimized_ = false;
                this.bitField0_ &= 0xFFFFFFBF;
                return this;
            }
            
            public Builder clone() {
                return b().mergeFrom(this.buildPartial());
            }
            
            public Descriptors.Descriptor getDescriptorForType() {
                return Model.internal_static_LLDBFrame_descriptor;
            }
            
            public LLDBFrame getDefaultInstanceForType() {
                return LLDBFrame.getDefaultInstance();
            }
            
            public LLDBFrame build() {
                final LLDBFrame buildPartial = this.buildPartial();
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
            
            public LLDBFrame buildPartial() {
                final LLDBFrame lldbFrame = new LLDBFrame((GeneratedMessage.Builder)this);
                final int bitField0_ = this.bitField0_;
                int n = 0;
                if ((bitField0_ & 0x1) == 0x1) {
                    n |= 0x1;
                }
                lldbFrame.number_ = this.number_;
                if ((bitField0_ & 0x2) == 0x2) {
                    n |= 0x2;
                }
                lldbFrame.function_ = this.function_;
                if ((bitField0_ & 0x4) == 0x4) {
                    n |= 0x4;
                }
                lldbFrame.file_ = this.file_;
                if ((bitField0_ & 0x8) == 0x8) {
                    n |= 0x8;
                }
                lldbFrame.line_ = this.line_;
                if ((bitField0_ & 0x10) == 0x10) {
                    n |= 0x10;
                }
                lldbFrame.pc_ = this.pc_;
                if ((bitField0_ & 0x20) == 0x20) {
                    n |= 0x20;
                }
                lldbFrame.language_ = this.language_;
                if ((bitField0_ & 0x40) == 0x40) {
                    n |= 0x40;
                }
                lldbFrame.optimized_ = this.optimized_;
                lldbFrame.bitField0_ = n;
                this.onBuilt();
                return lldbFrame;
            }
            
            public Builder mergeFrom(final Message message) {
                try {
                    if (message instanceof LLDBFrame) {
                        return this.mergeFrom((LLDBFrame)message);
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                super.mergeFrom(message);
                return this;
            }
            
            public Builder mergeFrom(final LLDBFrame lldbFrame) {
                try {
                    if (lldbFrame == LLDBFrame.getDefaultInstance()) {
                        return this;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                try {
                    if (lldbFrame.hasNumber()) {
                        this.setNumber(lldbFrame.getNumber());
                    }
                }
                catch (NullPointerException ex2) {
                    throw b(ex2);
                }
                try {
                    if (lldbFrame.hasFunction()) {
                        this.bitField0_ |= 0x2;
                        this.function_ = lldbFrame.function_;
                        this.onChanged();
                    }
                }
                catch (NullPointerException ex3) {
                    throw b(ex3);
                }
                try {
                    if (lldbFrame.hasFile()) {
                        this.bitField0_ |= 0x4;
                        this.file_ = lldbFrame.file_;
                        this.onChanged();
                    }
                }
                catch (NullPointerException ex4) {
                    throw b(ex4);
                }
                try {
                    if (lldbFrame.hasLine()) {
                        this.setLine(lldbFrame.getLine());
                    }
                }
                catch (NullPointerException ex5) {
                    throw b(ex5);
                }
                try {
                    if (lldbFrame.hasPc()) {
                        this.setPc(lldbFrame.getPc());
                    }
                }
                catch (NullPointerException ex6) {
                    throw b(ex6);
                }
                try {
                    if (lldbFrame.hasLanguage()) {
                        this.setLanguage(lldbFrame.getLanguage());
                    }
                }
                catch (NullPointerException ex7) {
                    throw b(ex7);
                }
                try {
                    if (lldbFrame.hasOptimized()) {
                        this.setOptimized(lldbFrame.getOptimized());
                    }
                }
                catch (NullPointerException ex8) {
                    throw b(ex8);
                }
                this.mergeUnknownFields(lldbFrame.getUnknownFields());
                return this;
            }
            
            public final boolean isInitialized() {
                try {
                    if (!this.hasNumber()) {
                        return false;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                return true;
            }
            
            public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
                LLDBFrame lldbFrame = null;
                try {
                    lldbFrame = (LLDBFrame)LLDBFrame.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                }
                catch (InvalidProtocolBufferException ex) {
                    lldbFrame = (LLDBFrame)ex.getUnfinishedMessage();
                    throw ex;
                }
                finally {
                    try {
                        if (lldbFrame != null) {
                            this.mergeFrom(lldbFrame);
                        }
                    }
                    catch (InvalidProtocolBufferException ex2) {
                        throw b((Exception)ex2);
                    }
                }
                return this;
            }
            
            public boolean hasNumber() {
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
            
            public int getNumber() {
                return this.number_;
            }
            
            public Builder setNumber(final int number_) {
                this.bitField0_ |= 0x1;
                this.number_ = number_;
                this.onChanged();
                return this;
            }
            
            public Builder clearNumber() {
                this.bitField0_ &= 0xFFFFFFFE;
                this.number_ = 0;
                this.onChanged();
                return this;
            }
            
            public boolean hasFunction() {
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
            
            public String getFunction() {
                final Object function_ = this.function_;
                if (!(function_ instanceof String)) {
                    return (String)(this.function_ = ((ByteString)function_).toStringUtf8());
                }
                return (String)function_;
            }
            
            public ByteString getFunctionBytes() {
                final Object function_ = this.function_;
                if (function_ instanceof String) {
                    return (ByteString)(this.function_ = ByteString.copyFromUtf8((String)function_));
                }
                return (ByteString)function_;
            }
            
            public Builder setFunction(final String function_) {
                try {
                    if (function_ == null) {
                        throw new NullPointerException();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.bitField0_ |= 0x2;
                this.function_ = function_;
                this.onChanged();
                return this;
            }
            
            public Builder clearFunction() {
                this.bitField0_ &= 0xFFFFFFFD;
                this.function_ = LLDBFrame.getDefaultInstance().getFunction();
                this.onChanged();
                return this;
            }
            
            public Builder setFunctionBytes(final ByteString function_) {
                try {
                    if (function_ == null) {
                        throw new NullPointerException();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.bitField0_ |= 0x2;
                this.function_ = function_;
                this.onChanged();
                return this;
            }
            
            public boolean hasFile() {
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
            
            public String getFile() {
                final Object file_ = this.file_;
                if (!(file_ instanceof String)) {
                    return (String)(this.file_ = ((ByteString)file_).toStringUtf8());
                }
                return (String)file_;
            }
            
            public ByteString getFileBytes() {
                final Object file_ = this.file_;
                if (file_ instanceof String) {
                    return (ByteString)(this.file_ = ByteString.copyFromUtf8((String)file_));
                }
                return (ByteString)file_;
            }
            
            public Builder setFile(final String file_) {
                try {
                    if (file_ == null) {
                        throw new NullPointerException();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.bitField0_ |= 0x4;
                this.file_ = file_;
                this.onChanged();
                return this;
            }
            
            public Builder clearFile() {
                this.bitField0_ &= 0xFFFFFFFB;
                this.file_ = LLDBFrame.getDefaultInstance().getFile();
                this.onChanged();
                return this;
            }
            
            public Builder setFileBytes(final ByteString file_) {
                try {
                    if (file_ == null) {
                        throw new NullPointerException();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.bitField0_ |= 0x4;
                this.file_ = file_;
                this.onChanged();
                return this;
            }
            
            public boolean hasLine() {
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
            
            public int getLine() {
                return this.line_;
            }
            
            public Builder setLine(final int line_) {
                this.bitField0_ |= 0x8;
                this.line_ = line_;
                this.onChanged();
                return this;
            }
            
            public Builder clearLine() {
                this.bitField0_ &= 0xFFFFFFF7;
                this.line_ = 0;
                this.onChanged();
                return this;
            }
            
            public boolean hasPc() {
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
            
            public long getPc() {
                return this.pc_;
            }
            
            public Builder setPc(final long pc_) {
                this.bitField0_ |= 0x10;
                this.pc_ = pc_;
                this.onChanged();
                return this;
            }
            
            public Builder clearPc() {
                this.bitField0_ &= 0xFFFFFFEF;
                this.pc_ = 0L;
                this.onChanged();
                return this;
            }
            
            public boolean hasLanguage() {
                try {
                    if ((this.bitField0_ & 0x20) == 0x20) {
                        return true;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                return false;
            }
            
            public Language getLanguage() {
                return this.language_;
            }
            
            public Builder setLanguage(final Language language_) {
                try {
                    if (language_ == null) {
                        throw new NullPointerException();
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                this.bitField0_ |= 0x20;
                this.language_ = language_;
                this.onChanged();
                return this;
            }
            
            public Builder clearLanguage() {
                this.bitField0_ &= 0xFFFFFFDF;
                this.language_ = Language.LanguageUnknown;
                this.onChanged();
                return this;
            }
            
            public boolean hasOptimized() {
                try {
                    if ((this.bitField0_ & 0x40) == 0x40) {
                        return true;
                    }
                }
                catch (NullPointerException ex) {
                    throw b(ex);
                }
                return false;
            }
            
            public boolean getOptimized() {
                return this.optimized_;
            }
            
            public Builder setOptimized(final boolean optimized_) {
                this.bitField0_ |= 0x40;
                this.optimized_ = optimized_;
                this.onChanged();
                return this;
            }
            
            public Builder clearOptimized() {
                this.bitField0_ &= 0xFFFFFFBF;
                this.optimized_ = false;
                this.onChanged();
                return this;
            }
            
            private static Exception b(final Exception ex) {
                return ex;
            }
        }
    }
    
    public static final class LLDBValue extends GeneratedMessage implements LLDBValueOrBuilder
    {
        private static final LLDBValue defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<LLDBValue> PARSER;
        private int bitField0_;
        public static final int ID_FIELD_NUMBER = 1;
        private int id_;
        public static final int NAME_FIELD_NUMBER = 2;
        private Object name_;
        public static final int TYPE_FIELD_NUMBER = 3;
        private Object type_;
        public static final int TYPE_CLASS_FIELD_NUMBER = 4;
        private TypeClass typeClass_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private static final long serialVersionUID = 0L;
        
        private LLDBValue(final GeneratedMessage.Builder<?> builder) {
            super((GeneratedMessage.Builder)builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }
        
        private LLDBValue(final boolean b) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }
        
        public static LLDBValue getDefaultInstance() {
            return LLDBValue.defaultInstance;
        }
        
        public LLDBValue getDefaultInstanceForType() {
            return LLDBValue.defaultInstance;
        }
        
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }
        
        private LLDBValue(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.a();
            final UnknownFieldSet.Builder builder = UnknownFieldSet.newBuilder();
            try {
                int i = 0;
                while (i == 0) {
                    final int tag = codedInputStream.readTag();
                    switch (tag) {
                        case 0: {
                            i = 1;
                            continue;
                        }
                        default: {
                            if (!this.parseUnknownField(codedInputStream, builder, extensionRegistryLite, tag)) {
                                i = 1;
                                continue;
                            }
                            continue;
                        }
                        case 8: {
                            this.bitField0_ |= 0x1;
                            this.id_ = codedInputStream.readInt32();
                            continue;
                        }
                        case 18: {
                            this.bitField0_ |= 0x2;
                            this.name_ = codedInputStream.readBytes();
                            continue;
                        }
                        case 26: {
                            this.bitField0_ |= 0x4;
                            this.type_ = codedInputStream.readBytes();
                            continue;
                        }
                        case 32: {
                            final int enum1 = codedInputStream.readEnum();
                            final TypeClass value = TypeClass.valueOf(enum1);
                            try {
                                if (value == null) {
                                    builder.mergeVarintField(4, enum1);
                                    continue;
                                }
                            }
                            catch (InvalidProtocolBufferException ex) {
                                throw a((IOException)ex);
                            }
                            this.bitField0_ |= 0x8;
                            this.typeClass_ = value;
                            continue;
                        }
                    }
                }
            }
            catch (InvalidProtocolBufferException ex2) {
                throw ex2.setUnfinishedMessage((MessageLite)this);
            }
            catch (IOException ex3) {
                throw new InvalidProtocolBufferException(ex3.getMessage()).setUnfinishedMessage((MessageLite)this);
            }
            finally {
                this.unknownFields = builder.build();
                this.makeExtensionsImmutable();
            }
        }
        
        public static final Descriptors.Descriptor getDescriptor() {
            return Model.internal_static_LLDBValue_descriptor;
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Model.internal_static_LLDBValue_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)LLDBValue.class, (Class)Builder.class);
        }
        
        public Parser<LLDBValue> getParserForType() {
            return LLDBValue.PARSER;
        }
        
        public boolean hasId() {
            return (this.bitField0_ & 0x1) == 0x1;
        }
        
        public int getId() {
            return this.id_;
        }
        
        public boolean hasName() {
            return (this.bitField0_ & 0x2) == 0x2;
        }
        
        public String getName() {
            final Object name_ = this.name_;
            if (name_ instanceof String) {
                return (String)name_;
            }
            final ByteString byteString = (ByteString)name_;
            final String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.name_ = stringUtf8;
            }
            return stringUtf8;
        }
        
        public ByteString getNameBytes() {
            final Object name_ = this.name_;
            if (name_ instanceof String) {
                return (ByteString)(this.name_ = ByteString.copyFromUtf8((String)name_));
            }
            return (ByteString)name_;
        }
        
        public boolean hasType() {
            return (this.bitField0_ & 0x4) == 0x4;
        }
        
        public String getType() {
            final Object type_ = this.type_;
            if (type_ instanceof String) {
                return (String)type_;
            }
            final ByteString byteString = (ByteString)type_;
            final String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.type_ = stringUtf8;
            }
            return stringUtf8;
        }
        
        public ByteString getTypeBytes() {
            final Object type_ = this.type_;
            if (type_ instanceof String) {
                return (ByteString)(this.type_ = ByteString.copyFromUtf8((String)type_));
            }
            return (ByteString)type_;
        }
        
        public boolean hasTypeClass() {
            return (this.bitField0_ & 0x8) == 0x8;
        }
        
        public TypeClass getTypeClass() {
            return this.typeClass_;
        }
        
        private void a() {
            this.id_ = 0;
            this.name_ = "";
            this.type_ = "";
            this.typeClass_ = TypeClass.TypeClassArray;
        }
        
        public final boolean isInitialized() {
            final byte memoizedIsInitialized = this.memoizedIsInitialized;
            if (memoizedIsInitialized != -1) {
                return memoizedIsInitialized == 1;
            }
            if (!this.hasId()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (!this.hasName()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (!this.hasType()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (!this.hasTypeClass()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }
        
        public void writeTo(final CodedOutputStream codedOutputStream) throws IOException {
            try {
                this.getSerializedSize();
                if ((this.bitField0_ & 0x1) == 0x1) {
                    codedOutputStream.writeInt32(1, this.id_);
                }
            }
            catch (IOException ex) {
                throw a(ex);
            }
            try {
                if ((this.bitField0_ & 0x2) == 0x2) {
                    codedOutputStream.writeBytes(2, this.getNameBytes());
                }
            }
            catch (IOException ex2) {
                throw a(ex2);
            }
            try {
                if ((this.bitField0_ & 0x4) == 0x4) {
                    codedOutputStream.writeBytes(3, this.getTypeBytes());
                }
            }
            catch (IOException ex3) {
                throw a(ex3);
            }
            try {
                if ((this.bitField0_ & 0x8) == 0x8) {
                    codedOutputStream.writeEnum(4, this.typeClass_.getNumber());
                }
            }
            catch (IOException ex4) {
                throw a(ex4);
            }
            this.getUnknownFields().writeTo(codedOutputStream);
        }
        
        public int getSerializedSize() {
            final int memoizedSerializedSize = this.memoizedSerializedSize;
            if (memoizedSerializedSize != -1) {
                return memoizedSerializedSize;
            }
            int n = 0;
            if ((this.bitField0_ & 0x1) == 0x1) {
                n += CodedOutputStream.computeInt32Size(1, this.id_);
            }
            if ((this.bitField0_ & 0x2) == 0x2) {
                n += CodedOutputStream.computeBytesSize(2, this.getNameBytes());
            }
            if ((this.bitField0_ & 0x4) == 0x4) {
                n += CodedOutputStream.computeBytesSize(3, this.getTypeBytes());
            }
            if ((this.bitField0_ & 0x8) == 0x8) {
                n += CodedOutputStream.computeEnumSize(4, this.typeClass_.getNumber());
            }
            return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
        }
        
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }
        
        public static LLDBValue parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
            return (LLDBValue)LLDBValue.PARSER.parseFrom(byteString);
        }
        
        public static LLDBValue parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (LLDBValue)LLDBValue.PARSER.parseFrom(byteString, extensionRegistryLite);
        }
        
        public static LLDBValue parseFrom(final byte[] array) throws InvalidProtocolBufferException {
            return (LLDBValue)LLDBValue.PARSER.parseFrom(array);
        }
        
        public static LLDBValue parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (LLDBValue)LLDBValue.PARSER.parseFrom(array, extensionRegistryLite);
        }
        
        public static LLDBValue parseFrom(final InputStream inputStream) throws IOException {
            return (LLDBValue)LLDBValue.PARSER.parseFrom(inputStream);
        }
        
        public static LLDBValue parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (LLDBValue)LLDBValue.PARSER.parseFrom(inputStream, extensionRegistryLite);
        }
        
        public static LLDBValue parseDelimitedFrom(final InputStream inputStream) throws IOException {
            return (LLDBValue)LLDBValue.PARSER.parseDelimitedFrom(inputStream);
        }
        
        public static LLDBValue parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (LLDBValue)LLDBValue.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }
        
        public static LLDBValue parseFrom(final CodedInputStream codedInputStream) throws IOException {
            return (LLDBValue)LLDBValue.PARSER.parseFrom(codedInputStream);
        }
        
        public static LLDBValue parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (LLDBValue)LLDBValue.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }
        
        public static Builder newBuilder() {
            return a();
        }
        
        public Builder newBuilderForType() {
            return newBuilder();
        }
        
        public static Builder newBuilder(final LLDBValue lldbValue) {
            return newBuilder().mergeFrom(lldbValue);
        }
        
        public Builder toBuilder() {
            return newBuilder(this);
        }
        
        protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
            return new Builder(builderParent);
        }
        
        static {
            LLDBValue.PARSER = (Parser<LLDBValue>)new AbstractParser<LLDBValue>() {
                public LLDBValue parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new LLDBValue(codedInputStream, extensionRegistryLite);
                }
            };
            (defaultInstance = new LLDBValue(true)).a();
        }
        
        private static IOException a(final IOException ex) {
            return ex;
        }
        
        public static final class Builder extends GeneratedMessage.Builder<Builder> implements LLDBValueOrBuilder
        {
            private int bitField0_;
            private int id_;
            private Object name_;
            private Object type_;
            private TypeClass typeClass_;
            
            public static final Descriptors.Descriptor getDescriptor() {
                return Model.internal_static_LLDBValue_descriptor;
            }
            
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return Model.internal_static_LLDBValue_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)LLDBValue.class, (Class)Builder.class);
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
                if (LLDBValue.alwaysUseFieldBuilders) {}
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
                return Model.internal_static_LLDBValue_descriptor;
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
                lldbValue.id_ = this.id_;
                if ((bitField0_ & 0x2) == 0x2) {
                    n |= 0x2;
                }
                lldbValue.name_ = this.name_;
                if ((bitField0_ & 0x4) == 0x4) {
                    n |= 0x4;
                }
                lldbValue.type_ = this.type_;
                if ((bitField0_ & 0x8) == 0x8) {
                    n |= 0x8;
                }
                lldbValue.typeClass_ = this.typeClass_;
                lldbValue.bitField0_ = n;
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
                        this.name_ = lldbValue.name_;
                        this.onChanged();
                    }
                }
                catch (NullPointerException ex3) {
                    throw b(ex3);
                }
                try {
                    if (lldbValue.hasType()) {
                        this.bitField0_ |= 0x4;
                        this.type_ = lldbValue.type_;
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
    }
    
    public static final class LLDBValueData extends GeneratedMessage implements LLDBValueDataOrBuilder
    {
        private static final LLDBValueData defaultInstance;
        private final UnknownFieldSet unknownFields;
        public static Parser<LLDBValueData> PARSER;
        private int bitField0_;
        public static final int VALUE_FIELD_NUMBER = 1;
        private Object value_;
        public static final int DESCRIPTION_FIELD_NUMBER = 2;
        private Object description_;
        public static final int HAS_LONGER_DESCRIPTION_FIELD_NUMBER = 3;
        private boolean hasLongerDescription_;
        public static final int MAY_HAVE_CHILDREN_FIELD_NUMBER = 4;
        private boolean mayHaveChildren_;
        public static final int IS_SYNTHETIC_FIELD_NUMBER = 5;
        private boolean isSynthetic_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private static final long serialVersionUID = 0L;
        
        private LLDBValueData(final GeneratedMessage.Builder<?> builder) {
            super((GeneratedMessage.Builder)builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }
        
        private LLDBValueData(final boolean b) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }
        
        public static LLDBValueData getDefaultInstance() {
            return LLDBValueData.defaultInstance;
        }
        
        public LLDBValueData getDefaultInstanceForType() {
            return LLDBValueData.defaultInstance;
        }
        
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }
        
        private LLDBValueData(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.a();
            final UnknownFieldSet.Builder builder = UnknownFieldSet.newBuilder();
            try {
                int i = 0;
                while (i == 0) {
                    final int tag = codedInputStream.readTag();
                    switch (tag) {
                        case 0: {
                            i = 1;
                            continue;
                        }
                        default: {
                            if (!this.parseUnknownField(codedInputStream, builder, extensionRegistryLite, tag)) {
                                i = 1;
                                continue;
                            }
                            continue;
                        }
                        case 10: {
                            this.bitField0_ |= 0x1;
                            this.value_ = codedInputStream.readBytes();
                            continue;
                        }
                        case 18: {
                            this.bitField0_ |= 0x2;
                            this.description_ = codedInputStream.readBytes();
                            continue;
                        }
                        case 24: {
                            this.bitField0_ |= 0x4;
                            this.hasLongerDescription_ = codedInputStream.readBool();
                            continue;
                        }
                        case 32: {
                            this.bitField0_ |= 0x8;
                            this.mayHaveChildren_ = codedInputStream.readBool();
                            continue;
                        }
                        case 40: {
                            this.bitField0_ |= 0x10;
                            this.isSynthetic_ = codedInputStream.readBool();
                            continue;
                        }
                    }
                }
            }
            catch (InvalidProtocolBufferException ex) {
                throw ex.setUnfinishedMessage((MessageLite)this);
            }
            catch (IOException ex2) {
                throw new InvalidProtocolBufferException(ex2.getMessage()).setUnfinishedMessage((MessageLite)this);
            }
            finally {
                this.unknownFields = builder.build();
                this.makeExtensionsImmutable();
            }
        }
        
        public static final Descriptors.Descriptor getDescriptor() {
            return Model.internal_static_LLDBValueData_descriptor;
        }
        
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return Model.internal_static_LLDBValueData_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)LLDBValueData.class, (Class)Builder.class);
        }
        
        public Parser<LLDBValueData> getParserForType() {
            return LLDBValueData.PARSER;
        }
        
        public boolean hasValue() {
            return (this.bitField0_ & 0x1) == 0x1;
        }
        
        public String getValue() {
            final Object value_ = this.value_;
            if (value_ instanceof String) {
                return (String)value_;
            }
            final ByteString byteString = (ByteString)value_;
            final String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.value_ = stringUtf8;
            }
            return stringUtf8;
        }
        
        public ByteString getValueBytes() {
            final Object value_ = this.value_;
            if (value_ instanceof String) {
                return (ByteString)(this.value_ = ByteString.copyFromUtf8((String)value_));
            }
            return (ByteString)value_;
        }
        
        public boolean hasDescription() {
            return (this.bitField0_ & 0x2) == 0x2;
        }
        
        public String getDescription() {
            final Object description_ = this.description_;
            if (description_ instanceof String) {
                return (String)description_;
            }
            final ByteString byteString = (ByteString)description_;
            final String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.description_ = stringUtf8;
            }
            return stringUtf8;
        }
        
        public ByteString getDescriptionBytes() {
            final Object description_ = this.description_;
            if (description_ instanceof String) {
                return (ByteString)(this.description_ = ByteString.copyFromUtf8((String)description_));
            }
            return (ByteString)description_;
        }
        
        public boolean hasHasLongerDescription() {
            return (this.bitField0_ & 0x4) == 0x4;
        }
        
        public boolean getHasLongerDescription() {
            return this.hasLongerDescription_;
        }
        
        public boolean hasMayHaveChildren() {
            return (this.bitField0_ & 0x8) == 0x8;
        }
        
        public boolean getMayHaveChildren() {
            return this.mayHaveChildren_;
        }
        
        public boolean hasIsSynthetic() {
            return (this.bitField0_ & 0x10) == 0x10;
        }
        
        public boolean getIsSynthetic() {
            return this.isSynthetic_;
        }
        
        private void a() {
            this.value_ = "";
            this.description_ = "";
            this.hasLongerDescription_ = false;
            this.mayHaveChildren_ = false;
            this.isSynthetic_ = false;
        }
        
        public final boolean isInitialized() {
            final byte memoizedIsInitialized = this.memoizedIsInitialized;
            if (memoizedIsInitialized != -1) {
                return memoizedIsInitialized == 1;
            }
            if (!this.hasValue()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (!this.hasMayHaveChildren()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            if (!this.hasIsSynthetic()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }
        
        public void writeTo(final CodedOutputStream codedOutputStream) throws IOException {
            try {
                this.getSerializedSize();
                if ((this.bitField0_ & 0x1) == 0x1) {
                    codedOutputStream.writeBytes(1, this.getValueBytes());
                }
            }
            catch (IOException ex) {
                throw a(ex);
            }
            try {
                if ((this.bitField0_ & 0x2) == 0x2) {
                    codedOutputStream.writeBytes(2, this.getDescriptionBytes());
                }
            }
            catch (IOException ex2) {
                throw a(ex2);
            }
            try {
                if ((this.bitField0_ & 0x4) == 0x4) {
                    codedOutputStream.writeBool(3, this.hasLongerDescription_);
                }
            }
            catch (IOException ex3) {
                throw a(ex3);
            }
            try {
                if ((this.bitField0_ & 0x8) == 0x8) {
                    codedOutputStream.writeBool(4, this.mayHaveChildren_);
                }
            }
            catch (IOException ex4) {
                throw a(ex4);
            }
            try {
                if ((this.bitField0_ & 0x10) == 0x10) {
                    codedOutputStream.writeBool(5, this.isSynthetic_);
                }
            }
            catch (IOException ex5) {
                throw a(ex5);
            }
            this.getUnknownFields().writeTo(codedOutputStream);
        }
        
        public int getSerializedSize() {
            final int memoizedSerializedSize = this.memoizedSerializedSize;
            if (memoizedSerializedSize != -1) {
                return memoizedSerializedSize;
            }
            int n = 0;
            if ((this.bitField0_ & 0x1) == 0x1) {
                n += CodedOutputStream.computeBytesSize(1, this.getValueBytes());
            }
            if ((this.bitField0_ & 0x2) == 0x2) {
                n += CodedOutputStream.computeBytesSize(2, this.getDescriptionBytes());
            }
            if ((this.bitField0_ & 0x4) == 0x4) {
                n += CodedOutputStream.computeBoolSize(3, this.hasLongerDescription_);
            }
            if ((this.bitField0_ & 0x8) == 0x8) {
                n += CodedOutputStream.computeBoolSize(4, this.mayHaveChildren_);
            }
            if ((this.bitField0_ & 0x10) == 0x10) {
                n += CodedOutputStream.computeBoolSize(5, this.isSynthetic_);
            }
            return this.memoizedSerializedSize = n + this.getUnknownFields().getSerializedSize();
        }
        
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }
        
        public static LLDBValueData parseFrom(final ByteString byteString) throws InvalidProtocolBufferException {
            return (LLDBValueData)LLDBValueData.PARSER.parseFrom(byteString);
        }
        
        public static LLDBValueData parseFrom(final ByteString byteString, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (LLDBValueData)LLDBValueData.PARSER.parseFrom(byteString, extensionRegistryLite);
        }
        
        public static LLDBValueData parseFrom(final byte[] array) throws InvalidProtocolBufferException {
            return (LLDBValueData)LLDBValueData.PARSER.parseFrom(array);
        }
        
        public static LLDBValueData parseFrom(final byte[] array, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (LLDBValueData)LLDBValueData.PARSER.parseFrom(array, extensionRegistryLite);
        }
        
        public static LLDBValueData parseFrom(final InputStream inputStream) throws IOException {
            return (LLDBValueData)LLDBValueData.PARSER.parseFrom(inputStream);
        }
        
        public static LLDBValueData parseFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (LLDBValueData)LLDBValueData.PARSER.parseFrom(inputStream, extensionRegistryLite);
        }
        
        public static LLDBValueData parseDelimitedFrom(final InputStream inputStream) throws IOException {
            return (LLDBValueData)LLDBValueData.PARSER.parseDelimitedFrom(inputStream);
        }
        
        public static LLDBValueData parseDelimitedFrom(final InputStream inputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (LLDBValueData)LLDBValueData.PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }
        
        public static LLDBValueData parseFrom(final CodedInputStream codedInputStream) throws IOException {
            return (LLDBValueData)LLDBValueData.PARSER.parseFrom(codedInputStream);
        }
        
        public static LLDBValueData parseFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (LLDBValueData)LLDBValueData.PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }
        
        public static Builder newBuilder() {
            return a();
        }
        
        public Builder newBuilderForType() {
            return newBuilder();
        }
        
        public static Builder newBuilder(final LLDBValueData lldbValueData) {
            return newBuilder().mergeFrom(lldbValueData);
        }
        
        public Builder toBuilder() {
            return newBuilder(this);
        }
        
        protected Builder newBuilderForType(final GeneratedMessage.BuilderParent builderParent) {
            return new Builder(builderParent);
        }
        
        static {
            LLDBValueData.PARSER = (Parser<LLDBValueData>)new AbstractParser<LLDBValueData>() {
                public LLDBValueData parsePartialFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new LLDBValueData(codedInputStream, extensionRegistryLite);
                }
            };
            (defaultInstance = new LLDBValueData(true)).a();
        }
        
        private static IOException a(final IOException ex) {
            return ex;
        }
        
        public static final class Builder extends GeneratedMessage.Builder<Builder> implements LLDBValueDataOrBuilder
        {
            private int bitField0_;
            private Object value_;
            private Object description_;
            private boolean hasLongerDescription_;
            private boolean mayHaveChildren_;
            private boolean isSynthetic_;
            
            public static final Descriptors.Descriptor getDescriptor() {
                return Model.internal_static_LLDBValueData_descriptor;
            }
            
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return Model.internal_static_LLDBValueData_fieldAccessorTable.ensureFieldAccessorsInitialized((Class)LLDBValueData.class, (Class)Builder.class);
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
                if (LLDBValueData.alwaysUseFieldBuilders) {}
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
                return Model.internal_static_LLDBValueData_descriptor;
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
                lldbValueData.value_ = this.value_;
                if ((bitField0_ & 0x2) == 0x2) {
                    n |= 0x2;
                }
                lldbValueData.description_ = this.description_;
                if ((bitField0_ & 0x4) == 0x4) {
                    n |= 0x4;
                }
                lldbValueData.hasLongerDescription_ = this.hasLongerDescription_;
                if ((bitField0_ & 0x8) == 0x8) {
                    n |= 0x8;
                }
                lldbValueData.mayHaveChildren_ = this.mayHaveChildren_;
                if ((bitField0_ & 0x10) == 0x10) {
                    n |= 0x10;
                }
                lldbValueData.isSynthetic_ = this.isSynthetic_;
                lldbValueData.bitField0_ = n;
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
                        this.value_ = lldbValueData.value_;
                        this.onChanged();
                    }
                }
                catch (NullPointerException ex2) {
                    throw b(ex2);
                }
                try {
                    if (lldbValueData.hasDescription()) {
                        this.bitField0_ |= 0x2;
                        this.description_ = lldbValueData.description_;
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
    }
    
    public interface LLDBValueDataOrBuilder extends MessageOrBuilder
    {
        boolean hasValue();
        
        String getValue();
        
        ByteString getValueBytes();
        
        boolean hasDescription();
        
        String getDescription();
        
        ByteString getDescriptionBytes();
        
        boolean hasHasLongerDescription();
        
        boolean getHasLongerDescription();
        
        boolean hasMayHaveChildren();
        
        boolean getMayHaveChildren();
        
        boolean hasIsSynthetic();
        
        boolean getIsSynthetic();
    }
    
    public interface LLDBValueOrBuilder extends MessageOrBuilder
    {
        boolean hasId();
        
        int getId();
        
        boolean hasName();
        
        String getName();
        
        ByteString getNameBytes();
        
        boolean hasType();
        
        String getType();
        
        ByteString getTypeBytes();
        
        boolean hasTypeClass();
        
        TypeClass getTypeClass();
    }
    
    public interface LLDBFrameOrBuilder extends MessageOrBuilder
    {
        boolean hasNumber();
        
        int getNumber();
        
        boolean hasFunction();
        
        String getFunction();
        
        ByteString getFunctionBytes();
        
        boolean hasFile();
        
        String getFile();
        
        ByteString getFileBytes();
        
        boolean hasLine();
        
        int getLine();
        
        boolean hasPc();
        
        long getPc();
        
        boolean hasLanguage();
        
        Language getLanguage();
        
        boolean hasOptimized();
        
        boolean getOptimized();
    }
    
    public interface LLDBThreadOrBuilder extends MessageOrBuilder
    {
        boolean hasId();
        
        int getId();
        
        boolean hasName();
        
        String getName();
        
        ByteString getNameBytes();
        
        boolean hasQueue();
        
        String getQueue();
        
        ByteString getQueueBytes();
        
        boolean hasStopReasonInfo();
        
        ThreadStopReasonInfo getStopReasonInfo();
        
        ThreadStopReasonInfoOrBuilder getStopReasonInfoOrBuilder();
    }
    
    public interface ThreadStopReasonInfoOrBuilder extends MessageOrBuilder
    {
        boolean hasStopReason();
        
        ThreadStopReason getStopReason();
        
        boolean hasStopDescription();
        
        String getStopDescription();
        
        ByteString getStopDescriptionBytes();
        
        boolean hasSignal();
        
        int getSignal();
        
        boolean hasSignalName();
        
        String getSignalName();
        
        ByteString getSignalNameBytes();
        
        boolean hasCodepointId();
        
        int getCodepointId();
    }
    
    public interface CommandLineOrBuilder extends MessageOrBuilder
    {
        List<EnvParam> getEnvList();
        
        EnvParam getEnv(final int p0);
        
        int getEnvCount();
        
        List<? extends EnvParamOrBuilder> getEnvOrBuilderList();
        
        EnvParamOrBuilder getEnvOrBuilder(final int p0);
        
        boolean hasExePath();
        
        String getExePath();
        
        ByteString getExePathBytes();
        
        boolean hasWorkingDir();
        
        String getWorkingDir();
        
        ByteString getWorkingDirBytes();
        
        List<String> getParamList();
        
        int getParamCount();
        
        String getParam(final int p0);
        
        ByteString getParamBytes(final int p0);
        
        boolean hasStdinPath();
        
        String getStdinPath();
        
        ByteString getStdinPathBytes();
        
        boolean hasStdoutPath();
        
        String getStdoutPath();
        
        ByteString getStdoutPathBytes();
        
        boolean hasStderrPath();
        
        String getStderrPath();
        
        ByteString getStderrPathBytes();
    }
    
    public interface EnvParamOrBuilder extends MessageOrBuilder
    {
        boolean hasName();
        
        String getName();
        
        ByteString getNameBytes();
        
        boolean hasValue();
        
        String getValue();
        
        ByteString getValueBytes();
    }
    
    public interface Initialized_MessageOrBuilder extends MessageOrBuilder
    {
    }
}
