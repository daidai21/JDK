/*
 * Copyright (c) 2003, 2017, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package jdk.javadoc.internal.doclets.toolkit;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

import com.sun.source.doctree.DocTree;
import jdk.javadoc.internal.doclets.toolkit.util.DocFileIOException;

/**
 * The interface for writing serialized form output.
 *
 *  <p><b>This is NOT part of any supported API.
 *  If you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 *
 * @author Jamie Ho
 */

public interface SerializedFormWriter {

    /**
     * Get the header.
     *
     * @param header the header to write.
     * @return the header content tree
     */
    public Content getHeader(String header);

    /**
     * Get the serialized form summaries header.
     *
     * @return the serialized form summary header tree
     */
    public Content getSerializedSummariesHeader();

    /**
     * Get the package serialized form header.
     *
     * @return the package serialized form header tree
     */
    public Content getPackageSerializedHeader();

    /**
     * Add the serialized tree per package to the serialized summaries tree.
     *
     * @param serializedSummariesTree the serialized tree to which the package serialized tree will be added
     * @param packageSerializedTree the serialized tree per package that needs to be added
     */
    public void addPackageSerializedTree(Content serializedSummariesTree, Content packageSerializedTree);

    /**
     * Get the given package header.
     *
     * @param packageName the package header to write
     * @return a content tree for the package header
     */
    public Content getPackageHeader(String packageName);

    /**
     * Get the serialized class header.
     *
     * @return a content tree for the serialized class header
     */
    public Content getClassSerializedHeader();

    /**
     * Get the heading for the serializable class.
     *
     * @param typeElement the class being processed
     * @return a content tree for the class heading
     */
    public Content getClassHeader(TypeElement typeElement);

    /**
     * Get the serial UID info header.
     *
     * @return a content tree for the serial uid info header
     */
    public Content getSerialUIDInfoHeader();

    /**
     * Adds the serial UID info.
     *
     * @param header the header that will show up before the UID.
     * @param serialUID the serial UID to print.
     * @param serialUidTree the serial UID tree to which the content will be added.
     */
    public void addSerialUIDInfo(String header, String serialUID,
            Content serialUidTree);

    /**
     * Get the class serialize content header.
     *
     * @return a content tree for the class serialize content header
     */
    public Content getClassContentHeader();

    /**
     * Return an instance of a SerialFieldWriter for a class.
     *
     * @param typeElement the class
     * @return an instance of a SerialFieldWriter.
     */
    public SerialFieldWriter getSerialFieldWriter(TypeElement typeElement);

    /**
     * Return an instance of a SerialMethodWriter for a class.
     *
     * @param typeElement the class
     * @return an instance of a SerialMethodWriter.
     */
    public SerialMethodWriter getSerialMethodWriter(TypeElement typeElement);

    /**
     * Get the serialized content.
     *
     * @param serializedTreeContent content for serialized data
     * @return a content tree for serialized information
     */
    public Content getSerializedContent(Content serializedTreeContent);

    /**
     * Add the footer.
     *
     * @param serializedTree the serialized tree to be added
     */
    public void addFooter(Content serializedTree);

    /**
     * Print the serialized form document.
     *
     * @param serializedTree the content tree that will be printed
     * @throws DocFileIOException if there is a problem while writing the document
     */
    public abstract void printDocument(Content serializedTree) throws DocFileIOException;

    /**
     * Gets the member tree.
     *
     * @param contentTree the tree used to generate the complete member tree
     * @return a content tree for the member
     */
    public Content getMemberTree(Content contentTree);

    /**
     * Write the serialized form for a given field.
     */
    public interface SerialFieldWriter {

        /**
         * Get the serializable field header.
         *
         * @return serialized fields header content tree
         */
        public Content getSerializableFieldsHeader();

        /**
         * Get the field content header.
         *
         * @param isLastContent true if this is the last content to be documented
         * @return fields header content tree
         */
        public Content getFieldsContentHeader(boolean isLastContent);

        /**
         * Get the fields content.
         *
         * @param heading the heading to write.
         * @param contentTree content tree to which the heading will be added
         * @return serializable fields content tree
         */
        public Content getSerializableFields(String heading, Content contentTree);

        /**
         * Adds the deprecated information for this member.
         *
         * @param field the field to document.
         * @param contentTree content tree to which the deprecated information will be added
         */
        public void addMemberDeprecatedInfo(VariableElement field, Content contentTree);

        /**
         * Adds the description text for this member.
         *
         * @param field the field to document
         * @param contentTree content tree to which the member description will be added
         */
        public void addMemberDescription(VariableElement field, Content contentTree);

        /**
         * Adds the description text for this member represented by the tag.
         *
         * @param field the field to document
         * @param serialFieldTag the field to document (represented by tag)
         * @param contentTree content tree to which the member description will be added
         */
        public void addMemberDescription(VariableElement field, DocTree serialFieldTag, Content contentTree);

        /**
         * Adds the tag information for this member.
         *
         * @param field the field to document
         * @param contentTree content tree to which the member tags will be added
         */
        public void addMemberTags(VariableElement field, Content contentTree);

        /**
         * Adds the member header.
         *
         * @param fieldType the type of the field
         * @param fieldTypeStr the type of the field in string format, used
         *                     only if the type cannot be linked
         * @param fieldDimensions the dimensions of the field
         * @param fieldName the name of the field
         * @param contentTree content tree to which the member header will be added
         */
        public void addMemberHeader(TypeElement fieldType, String fieldTypeStr,
            String fieldDimensions, String fieldName, Content contentTree);

        /**
         * Adds the member header.
         *
         * @param fieldType the type of the field
         * @param fieldName the name of the field
         * @param contentTree content tree to which the member header will be added
         */
        public void addMemberHeader(TypeMirror fieldType, String fieldName, Content contentTree);

        /**
         * Check to see if overview details should be printed. If
         * nocomment option set or if there is no text to be printed
         * for deprecation info, inline comment or tags,
         * do not print overview details.
         *
         * @param field the field to check overview details for
         * @return true if overview details need to be printed
         */
        public boolean shouldPrintOverview(VariableElement field);
    }

    /**
     * Write the serialized form for a given field.
     */
    public interface SerialMethodWriter {

        /**
         * Get the serializable method header.
         *
         * @return serializable methods content tree
         */
        public Content getSerializableMethodsHeader();

        /**
         * Get the method content header.
         *
         * @param isLastContent true if this is the last content to be documented
         * @return methods content tree
         */
        public Content getMethodsContentHeader(boolean isLastContent);

        /**
         * Write the given heading.
         *
         * @param heading the heading to write
         * @param serializableMethodTree content tree which will be added
         * @return serializable methods content tree
         */
        public Content getSerializableMethods(String heading, Content serializableMethodTree);

        /**
         * Write a warning that no serializable methods exist.
         *
         * @param msg the warning to print
         * @return no customization message tree
         */
        public Content getNoCustomizationMsg(String msg);

        /**
         * Adds the header.
         *
         * @param member the member to write the header for
         * @param methodsContentTree content tree to which the header will be added
         */
        public void addMemberHeader(ExecutableElement member, Content methodsContentTree);

        /**
         * Adds the deprecated information for this member.
         *
         * @param member the member to write the deprecated information for
         * @param methodsContentTree content tree to which the deprecated
         * information will be added
         */
        public void addDeprecatedMemberInfo(ExecutableElement member, Content methodsContentTree);

        /**
         * Adds the description for this member.
         *
         * @param member the member to write the information for
         * @param methodsContentTree content tree to which the member
         * information will be added
         */
        public void addMemberDescription(ExecutableElement member, Content methodsContentTree);

        /**
         * Adds the tag information for this member.
         *
         * @param member the member to write the tags information for
         * @param methodsContentTree content tree to which the tags
         * information will be added
         */
        public void addMemberTags(ExecutableElement member, Content methodsContentTree);
    }
}
