package edu.unc.lib.rdf.metrics;

import static org.apache.jena.rdf.model.ResourceFactory.createProperty;
import static org.apache.jena.rdf.model.ResourceFactory.createResource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.UUID;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFFormat;
import org.apache.jena.vocabulary.RDF;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SerializationTypeTest {
    private static final Logger log = LoggerFactory.getLogger(SerializationTypeTest.class);

    private static final String URI_BASE = "http://example.com/";
    private static final Resource PARENT_RESC = createResource(URI_BASE + "parent");
    private static final Property TEST_PROPERTY = createProperty(URI_BASE + "testProperty");

    private static final int ITERATIONS = 10000;
    private static final int SHORT_ITERATIONS = 100;
    private static final int TINY_ITERATIONS = 10;

    public SerializationTypeTest() throws Exception {
    }

    @BeforeClass
    public static void initReporters() throws Exception {
//        registry = new MetricsRegistry(Clock.SYSTEM, metrics);
//        serialTimer = registry.timer("serialTimer");
//        deserialTimer = registry.timer("deserialTimer");
//
//        File logFile = new File("metrics");//File.createTempFile("metrics", ".csv");
//        //logFile.createNewFile();
//        CsvReporter consoleReporter = CsvReporter.forRegistry(metrics)
//                .convertRatesTo(TimeUnit.NANOSECONDS)
//                .convertDurationsTo(TimeUnit.NANOSECONDS)
//                .formatFor(Locale.US)
//                .build(logFile);
//        consoleReporter.start(250, TimeUnit.MILLISECONDS);
//        log.info("{}", logFile.getAbsolutePath());
//        log.info("{}", logFile.exists());
//        final Graphite graphite = new Graphite(new InetSocketAddress("localhost", 2003));
//        final GraphiteReporter reporter = GraphiteReporter.forRegistry(metrics)
//                                                          .convertRatesTo(TimeUnit.SECONDS)
//                                                          .convertDurationsTo(TimeUnit.MILLISECONDS)
//                                                          .filter(MetricFilter.ALL)
//                                                          .build(graphite);
        // reporter.start(250, TimeUnit.MILLISECONDS);

//        HttpService.setSslSecurityProtocol(SSLSecurityProtocol.TLSv1_2);
//        ServiceArgs args = new ServiceArgs();
//        args.setToken("ea951237-38ad-456e-a3e7-8afa3193d74e");
//        args.setPort(8088);
//        args.setScheme("https");
//        Service splunkService = new Service(args);
//        SplunkReporter splunkReporter = SplunkReporter.forRegistry(metrics)
//                .convertRatesTo(TimeUnit.SECONDS)
//                .convertDurationsTo(TimeUnit.MILLISECONDS)
//                .withIndex("test")
//                .build(splunkService);
//        splunkReporter.start(250, TimeUnit.MILLISECONDS);
    }

    @Test
    public void testSerializationOfBaselineFcrepoRecord() throws Exception {
        Model model = makeFcrepoContainerModel();

        String description = "base_records";

        testSerialization(description, ITERATIONS, RDFFormat.TURTLE, model);
        testSerialization(description, ITERATIONS, RDFFormat.JSONLD_EXPAND_FLAT, model);
        testSerialization(description, ITERATIONS, RDFFormat.NTRIPLES, model);
    }

    @Test
    public void testDeserializationOfBaselineFcrepoRecord() throws Exception {
        Model model = makeFcrepoContainerModel();

        String description = "base_records";

        testDeserialization(description, ITERATIONS, RDFFormat.TURTLE, model);
        testDeserialization(description, ITERATIONS, RDFFormat.JSONLD_EXPAND_FLAT, model);
        testDeserialization(description, ITERATIONS, RDFFormat.NTRIPLES, model);
    }

    @Test
    public void testSerialization100Literals() throws Exception {
        Model model = makeFcrepoContainerModel();
        addStringLiterals(model, TEST_PROPERTY, 100);

        String description = "records_100_literals";

        testSerialization(description, ITERATIONS, RDFFormat.TURTLE, model);
        testSerialization(description, ITERATIONS, RDFFormat.JSONLD_EXPAND_FLAT, model);
        testSerialization(description, ITERATIONS, RDFFormat.NTRIPLES, model);
    }

    @Test
    public void testDeserialization100Literals() throws Exception {
        Model model = makeFcrepoContainerModel();
        addStringLiterals(model, TEST_PROPERTY, 100);

        String description = "records_100_literals";

        testDeserialization(description, ITERATIONS, RDFFormat.TURTLE, model);
        testDeserialization(description, ITERATIONS, RDFFormat.JSONLD_EXPAND_FLAT, model);
        testDeserialization(description, ITERATIONS, RDFFormat.NTRIPLES, model);
    }

    @Test
    public void testSerialization1000Literals() throws Exception {
        Model model = makeFcrepoContainerModel();
        addStringLiterals(model, TEST_PROPERTY, 1000);

        String description = "records_1000_literals";

        testSerialization(description, SHORT_ITERATIONS, RDFFormat.TURTLE, model);
        testSerialization(description, SHORT_ITERATIONS, RDFFormat.JSONLD_EXPAND_FLAT, model);
        testSerialization(description, SHORT_ITERATIONS, RDFFormat.NTRIPLES, model);
    }

    @Test
    public void testDeserialization1000Literals() throws Exception {
        Model model = makeFcrepoContainerModel();
        addStringLiterals(model, TEST_PROPERTY, 1000);

        String description = "records_1000_literals";

        testDeserialization(description, SHORT_ITERATIONS, RDFFormat.TURTLE, model);
        testDeserialization(description, SHORT_ITERATIONS, RDFFormat.JSONLD_EXPAND_FLAT, model);
        testDeserialization(description, SHORT_ITERATIONS, RDFFormat.NTRIPLES, model);
    }

    @Test
    public void testSerialization100Properties() throws Exception {
        Model model = makeFcrepoContainerModel();
        addProperties(model, TEST_PROPERTY, 100);

        String description = "records_100_properties";

        testSerialization(description, ITERATIONS, RDFFormat.TURTLE, model);
        testSerialization(description, ITERATIONS, RDFFormat.JSONLD_EXPAND_FLAT, model);
        testSerialization(description, ITERATIONS, RDFFormat.NTRIPLES, model);
    }

    @Test
    public void testDeserialization100Properties() throws Exception {
        Model model = makeFcrepoContainerModel();
        addProperties(model, TEST_PROPERTY, 100);

        String description = "records_100_properties";

        testDeserialization(description, ITERATIONS, RDFFormat.TURTLE, model);
        testDeserialization(description, ITERATIONS, RDFFormat.JSONLD_EXPAND_FLAT, model);
        testDeserialization(description, ITERATIONS, RDFFormat.NTRIPLES, model);
    }

    @Test
    public void testSerialization1000Properties() throws Exception {
        Model model = makeFcrepoContainerModel();
        addProperties(model, TEST_PROPERTY, 1000);

        String description = "records_1000_properties";

        testSerialization(description, SHORT_ITERATIONS, RDFFormat.TURTLE, model);
        testSerialization(description, SHORT_ITERATIONS, RDFFormat.JSONLD_EXPAND_FLAT, model);
        testSerialization(description, SHORT_ITERATIONS, RDFFormat.NTRIPLES, model);
    }

    @Test
    public void testDeserialization1000Properties() throws Exception {
        Model model = makeFcrepoContainerModel();
        addProperties(model, TEST_PROPERTY, 1000);

        String description = "records_1000_properties";

        testDeserialization(description, SHORT_ITERATIONS, RDFFormat.TURTLE, model);
        testDeserialization(description, SHORT_ITERATIONS, RDFFormat.JSONLD_EXPAND_FLAT, model);
        testDeserialization(description, SHORT_ITERATIONS, RDFFormat.NTRIPLES, model);
    }

    @Test
    public void testSerialization5000Properties() throws Exception {
        Model model = makeFcrepoContainerModel();
        addProperties(model, TEST_PROPERTY, 5000);

        String description = "records_5000_properties";

        testSerialization(description, TINY_ITERATIONS, RDFFormat.TURTLE, model);
        testSerialization(description, TINY_ITERATIONS, RDFFormat.JSONLD_EXPAND_FLAT, model);
        testSerialization(description, TINY_ITERATIONS, RDFFormat.NTRIPLES, model);
    }

    @Test
    public void testDeserialization5000Properties() throws Exception {
        Model model = makeFcrepoContainerModel();
        addProperties(model, TEST_PROPERTY, 5000);

        String description = "records_5000_properties";

        testDeserialization(description, TINY_ITERATIONS, RDFFormat.TURTLE, model);
        testDeserialization(description, TINY_ITERATIONS, RDFFormat.JSONLD_EXPAND_FLAT, model);
        testDeserialization(description, TINY_ITERATIONS, RDFFormat.NTRIPLES, model);
    }

    private void testSerialization(String description, int iterations, RDFFormat format, Model model)
            throws Exception {
        streamModel(model, format);
        StopWatch timer = StopWatch.createStarted();
        for (int i = 0; i < iterations; i++) {
            streamModel(model, format);
        }
        timer.stop();
        log.info("Serialized,{},{},{},{},{}", iterations, description, format.toString(),
                timer.getNanoTime() / 1000000, ((double) timer.getNanoTime() / iterations) / 1000000);
    }

    private void testDeserialization(String description, int iterations, RDFFormat format, Model model)
            throws Exception {
        InputStream stream = streamModel(model, format);

        StopWatch timer = StopWatch.createStarted();
        for (int i = 0; i < iterations; i++) {
                readModel(stream, format);
                stream.reset();
        }
        timer.stop();
        log.info("Deserialized,{},{},{},{},{}", iterations, description, format.toString(),
                timer.getNanoTime() / 1000000, ((double) timer.getNanoTime() / iterations) / 1000000);
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

    protected InputStream streamModel(Model model, RDFFormat format) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            RDFDataMgr.write(bos, model, format);
            // log.info("{}", new String(bos.toByteArray()));
            return new ByteArrayInputStream(bos.toByteArray());
        }
    }

    public static Model readModel(InputStream inStream, RDFFormat format) {
        Model model = ModelFactory.createDefaultModel();
        model.read(inStream, null, format.getLang().getName());
        return model;
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
