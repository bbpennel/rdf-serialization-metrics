package edu.unc.lib.rdf.metrics;

import static edu.unc.lib.rdf.metrics.ModelUtil.readModel;
import static edu.unc.lib.rdf.metrics.ModelUtil.streamModel;
import static org.apache.jena.rdf.model.ResourceFactory.createProperty;
import static org.apache.jena.rdf.model.ResourceFactory.createResource;

import java.io.InputStream;
import java.util.Calendar;
import java.util.UUID;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.riot.RDFFormat;
import org.apache.jena.vocabulary.RDF;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tests to record execution time for Jena to serialize or deserialize models
 * containing varying numbers of relations or literals.
 *
 * @author bbpennel
 *
 */
public class SerializationTypeTest {
    private static final Logger log = LoggerFactory.getLogger(SerializationTypeTest.class);

    private static final String URI_BASE = "http://example.com/";
    private static final Resource PARENT_RESC = createResource(URI_BASE + "parent");
    private static final Property TEST_PROPERTY = createProperty(URI_BASE + "testProperty");

    private static final int ITERATIONS = 1000;
    private static final int SHORT_ITERATIONS = 100;

    @Test
    public void testSerializationOfBaselineFcrepoRecord() throws Exception {
        Model model = makeFcrepoContainerModel();

        String description = "base_records";

        testSerialization(description, 10, ITERATIONS, RDFFormat.TURTLE, model);
        testSerialization(description, 10, ITERATIONS, RDFFormat.JSONLD_EXPAND_FLAT, model);
        testSerialization(description, 10, ITERATIONS, RDFFormat.NTRIPLES, model);
    }

    @Test
    public void testDeserializationOfBaselineFcrepoRecord() throws Exception {
        Model model = makeFcrepoContainerModel();

        String description = "base_records";

        testDeserialization(description, 10, ITERATIONS, RDFFormat.TURTLE, model);
        testDeserialization(description, 10, ITERATIONS, RDFFormat.JSONLD_EXPAND_FLAT, model);
        testDeserialization(description, 10, ITERATIONS, RDFFormat.NTRIPLES, model);
    }

    @Test
    public void testSerialization100Literals() throws Exception {
        serializeNLiterals(100, ITERATIONS);
    }

    @Test
    public void testSerialization1000Literals() throws Exception {
        serializeNLiterals(1000, SHORT_ITERATIONS);
    }

    @Test
    public void testSerialization5000Literals() throws Exception {
        serializeNLiterals(5000, SHORT_ITERATIONS);
    }

    @Test
    public void testSerialization10000Literals() throws Exception {
        serializeNLiterals(10000, SHORT_ITERATIONS);
    }

    @Test
    public void testSerialization1000000Literals() throws Exception {
        serializeNLiterals(1000000, 1);
    }

    private void serializeNLiterals(int numLits, int numIts) throws Exception {
        Model model = makeFcrepoContainerModel();
        addStringLiterals(model, TEST_PROPERTY, numLits);

        String description = "records_literals";

        testSerialization(description, numLits, numIts, RDFFormat.TURTLE, model);
        testSerialization(description, numLits, numIts, RDFFormat.JSONLD_EXPAND_FLAT, model);
        testSerialization(description, numLits, numIts, RDFFormat.NTRIPLES, model);
    }

    @Test
    public void testDeserialization100Literals() throws Exception {
        deserializeNLiterals(100, ITERATIONS);
    }

    @Test
    public void testDeserialization1000Literals() throws Exception {
        deserializeNLiterals(1000, SHORT_ITERATIONS);
    }

    @Test
    public void testDeserialization5000Literals() throws Exception {
        deserializeNLiterals(5000, SHORT_ITERATIONS);
    }

    @Test
    public void testDeserialization10000Literals() throws Exception {
        deserializeNLiterals(10000, SHORT_ITERATIONS);
    }

    @Test
    public void testDeserialization1000000Literals() throws Exception {
        deserializeNLiterals(1000000, 1);
    }

    private void deserializeNLiterals(int numLits, int numIts) throws Exception {
        Model model = makeFcrepoContainerModel();
        addStringLiterals(model, TEST_PROPERTY, numLits);

        String description = "records_literals";

        testDeserialization(description, numLits, numIts, RDFFormat.TURTLE, model);
        testDeserialization(description, numLits, numIts, RDFFormat.JSONLD_EXPAND_FLAT, model);
        testDeserialization(description, numLits, numIts, RDFFormat.NTRIPLES, model);
    }

    @Test
    public void testSerialization100Properties() throws Exception {
        serializeNProperties(100, ITERATIONS);
    }

    @Test
    public void testSerialization1000Properties() throws Exception {
        serializeNProperties(1000, SHORT_ITERATIONS);
    }

    @Test
    public void testSerialization5000Properties() throws Exception {
        serializeNProperties(5000, SHORT_ITERATIONS);
    }

    @Test
    public void testSerialization10000Properties() throws Exception {
        serializeNProperties(10000, SHORT_ITERATIONS);
    }

    @Test
    public void testSerialization1000000Properties() throws Exception {
        serializeNProperties(1000000, 1);
    }

    private void serializeNProperties(int numLits, int numIts) throws Exception {
        Model model = makeFcrepoContainerModel();
        addProperties(model, TEST_PROPERTY, numLits);

        String description = "records_properties";

        testSerialization(description, numLits, numIts, RDFFormat.TURTLE, model);
        testSerialization(description, numLits, numIts, RDFFormat.JSONLD_EXPAND_FLAT, model);
        testSerialization(description, numLits, numIts, RDFFormat.NTRIPLES, model);
    }

    @Test
    public void testDeserialization100Properties() throws Exception {
        deserializeNProperties(100, ITERATIONS);
    }

    @Test
    public void testDeserialization1000Properties() throws Exception {
        deserializeNProperties(1000, SHORT_ITERATIONS);
    }

    @Test
    public void testDeserialization5000Properties() throws Exception {
        deserializeNProperties(5000, SHORT_ITERATIONS);
    }

    @Test
    public void testDeserialization10000Properties() throws Exception {
        deserializeNProperties(10000, SHORT_ITERATIONS);
    }

    @Test
    public void testDeserialization1000000Properties() throws Exception {
        deserializeNProperties(1000000, 1);
    }

    private void deserializeNProperties(int numLits, int numIts) throws Exception {
        Model model = makeFcrepoContainerModel();
        addProperties(model, TEST_PROPERTY, numLits);

        String description = "records_properties";

        testDeserialization(description, numLits, numIts, RDFFormat.TURTLE, model);
        testDeserialization(description, numLits, numIts, RDFFormat.JSONLD_EXPAND_FLAT, model);
        testDeserialization(description, numLits, numIts, RDFFormat.NTRIPLES, model);
    }

    private void testSerialization(String description, int numProperties, int iterations, RDFFormat format, Model model)
            throws Exception {
        streamModel(model, format);
        StopWatch timer = StopWatch.createStarted();
        for (int i = 0; i < iterations; i++) {
            streamModel(model, format);
        }
        timer.stop();
        long runTime = timer.getNanoTime();
        double timePerIteration = ((double) runTime / iterations) / 1000000;
        log.info("Serialize,{},{},{},{},{},{}", iterations, description, numProperties, format.toString(),
                runTime / 1000000, timePerIteration);
    }

    private void testDeserialization(String description, int numProperties, int iterations, RDFFormat format, Model model)
            throws Exception {
        InputStream stream = streamModel(model, format);

        StopWatch timer = StopWatch.createStarted();
        for (int i = 0; i < iterations; i++) {
                readModel(stream, format);
                stream.reset();
        }
        timer.stop();
        long runTime = timer.getNanoTime();
        double timePerIteration = ((double) runTime / iterations) / 1000000;
        log.info("Deserialize,{},{},{},{},{},{}", iterations, description, numProperties, format.toString(),
                runTime / 1000000, timePerIteration);
    }

    private void addStringLiterals(Model model, Property property, int times) {
        Resource resc = model.listResourcesWithProperty(RDF.type).next();
        for (int i = 0; i < times; i++) {
            resc.addLiteral(property, "val" + i);
        }
    }

    private void addProperties(Model model, Property property, int times) {
        Resource resc = model.listResourcesWithProperty(RDF.type).next();
        for (int i = 0; i < times; i++) {
            resc.addProperty(property, resourceUri());
        }
    }

    protected String resourceUri() {
        return URI_BASE + UUID.randomUUID().toString();
    }

    protected Model makeFcrepoContainerModel() {
        Model model = ModelFactory.createDefaultModel();
        Resource resc = model.getResource(resourceUri());
        populateFcrepoContainer(resc);

        return model;
    }

    protected void populateFcrepoContainer(Resource resc) {
        resc.addProperty(RDF.type, Fcrepo4.Container);
        resc.addProperty(RDF.type, Fcrepo4.Resource);
        resc.addProperty(RDF.type, Ldp.Container);
        resc.addProperty(RDF.type, Ldp.RDFSource);
        Literal now = resc.getModel().createTypedLiteral(Calendar.getInstance());
        resc.addLiteral(Fcrepo4.lastModified, now);
        resc.addLiteral(Fcrepo4.created, now);
        resc.addLiteral(Fcrepo4.lastModifiedBy, "admin");
        resc.addLiteral(Fcrepo4.createdBy, "admin");
        resc.addLiteral(Fcrepo4.writable, true);
        resc.addProperty(Fcrepo4.hasParent, PARENT_RESC);
    }
}
