/**
 * Copyright 2008 The University of North Carolina at Chapel Hill
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.unc.lib.rdf.metrics;

import static org.apache.jena.rdf.model.ResourceFactory.createProperty;
import static org.apache.jena.rdf.model.ResourceFactory.createResource;

import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

/**
 * Vocabulary definitions from rdf-schemas/fcrepo4Repository.rdf
 * @author Auto-generated by schemagen on 21 Apr 2016 12:16
 */
public class Fcrepo4 {
    private Fcrepo4() {
    }

    /** The namespace of the vocabulary as a string */
    public static final String NS = "http://fedora.info/definitions/v4/repository#";

    /** The namespace of the vocabulary as a string
     *  @see #NS */
    public static String getURI() {
        return NS; }

    /** The namespace of the vocabulary as a resource */
    public static final Resource NAMESPACE = createResource( NS );

    /** The ontology's owl:versionInfo as a string */
    public static final String VERSION_INFO = "v4/2016/03/01";

    /** A Resource that maintains properties in its own right. */
    public static final Resource AnnotatedResource = createResource(
            "http://fedora.info/definitions/v4/repository#AnnotatedResource" );

    /** A bitstream, with no further data properties. */
    public static final Resource Binary = createResource(
            "http://fedora.info/definitions/v4/repository#Binary" );

    /** A container for transform configuration. */
    public static final Resource Configuration = createResource(
            "http://fedora.info/definitions/v4/repository#Configuration" );

    /** A Fedora Container: the fundamental quantum of durable content in a Fedora
     *  repository.
     */
    public static final Resource Container = createResource(
            "http://fedora.info/definitions/v4/repository#Container" );

    /** The set of triples representing child resources of a given resource. */
    public static final Resource EmbedResources = createResource(
            "http://fedora.info/definitions/v4/repository#EmbedResources" );

    /** The set of triples representing other repository resources which link to a
     *  given resource.
     */
    public static final Resource InboundReferences = createResource(
            "http://fedora.info/definitions/v4/repository#InboundReferences" );

    /** A container for transform node type configuration. */
    public static final Resource NodeTypeConfiguration = createResource(
            "http://fedora.info/definitions/v4/repository#NodeTypeConfiguration" );

    /** A container for a bitstream and associated properties. */
    public static final Resource NonRdfSourceDescription = createResource(
            "http://fedora.info/definitions/v4/repository#NonRdfSourceDescription" );

    /** An entity that is a an intermediary node created in a PairTree hierarchy. */
    public static final Resource Pairtree = createResource(
            "http://fedora.info/definitions/v4/repository#Pairtree" );

    /** An entity that may be related to other repository entities. */
    public static final Resource Relations = createResource(
            "http://fedora.info/definitions/v4/repository#Relations" );

    /** A repository root. */
    public static final Resource RepositoryRoot = createResource(
            "http://fedora.info/definitions/v4/repository#RepositoryRoot" );

    /** An entity that has been committed to the repository for safekeeping. For example,
     *  Fedora objects and datastreams are resources. A Fixity is not, because the
     *  provenance of the instance is entirely internal to the repository.
     */
    public static final Resource Resource = createResource(
            "http://fedora.info/definitions/v4/repository#Resource" );

    /** The system-generated triples for a given resource (as opposed to explicity-declared
     *  properties).
     */
    public static final Resource ServerManaged = createResource(
            "http://fedora.info/definitions/v4/repository#ServerManaged" );

    /** An entity that is a representation of an RDF Skolem node. */
    public static final Resource Skolem = createResource(
            "http://fedora.info/definitions/v4/repository#Skolem" );

    /** Something that is contemplated in the Fedora repository model. */
    public static final Resource Thing = createResource(
            "http://fedora.info/definitions/v4/repository#Thing" );

    /** An entity that is a marker for a deleted node. */
    public static final Resource Tombstone = createResource(
            "http://fedora.info/definitions/v4/repository#Tombstone" );

    public static final Resource Version = createResource(
            "http://fedora.info/definitions/v4/repository#Version" );

    public static final Property created = createProperty(
            "http://fedora.info/definitions/v4/repository#created" );

    public static final Property lastModified = createProperty(
            "http://fedora.info/definitions/v4/repository#lastModified" );

    public static final Property lastModifiedBy = createProperty(
            "http://fedora.info/definitions/v4/repository#lastModifiedBy" );

    public static final Property createdBy = createProperty(
            "http://fedora.info/definitions/v4/repository#createdBy" );

    public static final Property writable = createProperty(
            "http://fedora.info/definitions/v4/repository#writable" );

    public static final Property hasParent = createProperty(
            "http://fedora.info/definitions/v4/repository#hasParent" );
}
