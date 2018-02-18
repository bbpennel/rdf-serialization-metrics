package edu.unc.lib.rdf.metrics;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFFormat;

public class ModelUtil {

    public final static String ACCEPT_JSON_LD = "application/ld+json";
    public final static String ACCEPT_NTRIPLES = "application/n-triples";
    public final static String ACCEPT_TURTLE = "text/turtle";

    private ModelUtil() {
    }

    public static InputStream streamModel(Model model, RDFFormat format) throws IOException {
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
}
