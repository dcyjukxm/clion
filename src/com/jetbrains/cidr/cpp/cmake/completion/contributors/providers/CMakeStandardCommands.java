// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.completion.contributors.providers;

import com.jetbrains.cidr.cpp.cmake.psi.CMakeCommandName;

public enum CMakeStandardCommands
{
    ELSE("else", true), 
    ELSEIF("elseif", true), 
    ENDFOREACH("endforeach", true), 
    ENDFUNCTION("endfunction", true), 
    ENDIF("endif", true), 
    ENDMACRO("endmacro", true), 
    ENDWHILE("endwhile", true), 
    FOREACH("foreach", true), 
    FUNCTION("function", true), 
    IF("if", true), 
    MACRO("macro", true), 
    WHILE("while", true), 
    ADD_COMPILE_OPTIONS("add_compile_options"), 
    ADD_CUSTOM_COMMAND("add_custom_command"), 
    ADD_CUSTOM_TARGET_COMMAND_NAME("add_custom_target"), 
    ADD_DEFINITIONS("add_definitions"), 
    ADD_DEPENDENCIES("add_dependencies"), 
    ADD_EXECUTABLE_COMMAND_NAME("add_executable"), 
    ADD_LIBRARY_COMMAND_NAME("add_library"), 
    ADD_SUBDIRECTORY("add_subdirectory"), 
    ADD_TEST("add_test"), 
    AUX_SOURCE_DIRECTORY("aux_source_directory"), 
    BUILD_COMMAND("build_command"), 
    CMAKE_HOST_SYSTEM_INFORMATION("cmake_host_system_information"), 
    CMAKE_MINIMUM_REQUIRED("cmake_minimum_required"), 
    CMAKE_PARSE_ARGUMENTS("cmake_parse_arguments"), 
    CMAKE_POLICY("cmake_policy"), 
    CONFIGURE_FILE("configure_file"), 
    CREATE_TEST_SOURCELIST("create_test_sourcelist"), 
    DEFINE_PROPERTY("define_property"), 
    ENABLE_LANGUAGE("enable_language"), 
    ENABLE_TESTING("enable_testing"), 
    EXECUTE_PROCESS("execute_process"), 
    EXPORT("export"), 
    FILE("file"), 
    FIND_FILE("find_file"), 
    FIND_LIBRARY("find_library"), 
    FIND_PACKAGE("find_package"), 
    FIND_PATH("find_path"), 
    FIND_PROGRAM("find_program"), 
    FLTK_WRAP_UI_COMMAND_NAME("fltk_wrap_ui"), 
    GET_CMAKE_PROPERTY("get_cmake_property"), 
    GET_DIRECTORY_PROPERTY("get_directory_property"), 
    GET_FILENAME_COMPONENT("get_filename_component"), 
    GET_PROPERTY("get_property"), 
    GET_SOURCE_FILE_PROPERTY("get_source_file_property"), 
    GET_TARGET_PROPERTY("get_target_property"), 
    GET_TEST_PROPERTY("get_test_property"), 
    INCLUDE_DIRECTORIES("include_directories"), 
    INCLUDE_EXTERNAL_MSPROJECT("include_external_msproject"), 
    INCLUDE_REGULAR_EXPRESSION("include_regular_expression"), 
    INCLUDE_COMMAND_NAME("include"), 
    INSTALL("install"), 
    LINK_DIRECTORIES("link_directories"), 
    LINK_LIBRARIES("link_libraries"), 
    LIST("list"), 
    LOAD_CACHE("load_cache"), 
    LOAD_COMMAND("load_command"), 
    MARK_AS_ADVANCED("mark_as_advanced"), 
    MATH("math"), 
    MESSAGE("message"), 
    OPTION("option"), 
    PROJECT("project"), 
    QT_WRAP_CPP_COMMAND_NAME("qt_wrap_cpp"), 
    QT_WRAP_UI_COMMAND_NAME("qt_wrap_ui"), 
    REMOVE_DEFINITIONS("remove_definitions"), 
    RETURN("return"), 
    SEPARATE_ARGUMENTS("separate_arguments"), 
    SET_DIRECTORY_PROPERTIES("set_directory_properties"), 
    SET_PROPERTY_COMMAND_NAME("set_property"), 
    SET_COMMAND_NAME("set"), 
    SET_SOURCE_FILES_PROPERTIES_COMMAND_NAME("set_source_files_properties"), 
    SET_TARGET_PROPERTIES("set_target_properties"), 
    SET_TESTS_PROPERTIES("set_tests_properties"), 
    SITE_NAME("site_name"), 
    SOURCE_GROUP("source_group"), 
    STRING("string"), 
    TARGET_COMPILE_DEFINITIONS("target_compile_definitions"), 
    TARGET_COMPILE_FEATURES("target_compile_features"), 
    TARGET_COMPILE_OPTIONS("target_compile_options"), 
    TARGET_INCLUDE_DIRECTORIES("target_include_directories"), 
    TARGET_LINK_LIBRARIES("target_link_libraries"), 
    TARGET_SOURCES("target_sources"), 
    TRY_COMPILE_COMMAND_NAME("try_compile"), 
    TRY_RUN("try_run"), 
    UNSET("unset"), 
    VARIABLE_WATCH("variable_watch");
    
    private final String myCommandName;
    private final boolean myLexerToken;
    
    private CMakeStandardCommands(final String s2) {
        this(s2, false);
    }
    
    private CMakeStandardCommands(final String myCommandName, final boolean myLexerToken) {
        this.myCommandName = myCommandName;
        this.myLexerToken = myLexerToken;
    }
    
    public boolean isLexerToken() {
        return this.myLexerToken;
    }
    
    public String getCommandName() {
        return this.myCommandName;
    }
    
    public static boolean isCommand(final CMakeStandardCommands cMakeStandardCommands, final CMakeCommandName cMakeCommandName) {
        return cMakeStandardCommands.getCommandName().equalsIgnoreCase(cMakeCommandName.getName());
    }
}
