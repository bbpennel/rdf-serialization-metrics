package edu.unc.lib.rdf.metrics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.concurrent.TimeUnit;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.ScheduledReporter;
import com.codahale.metrics.Timer;
import com.fasterxml.jackson.core.JsonProcessingException;

public class SplunkCsvReporter extends ScheduledReporter {

    protected SplunkCsvReporter(MetricRegistry registry, String name, MetricFilter filter, TimeUnit rateUnit,
            TimeUnit durationUnit) {
        super(registry, name, filter, rateUnit, durationUnit);
    }

    @Override
    public void report(SortedMap<String, Gauge> gauges, SortedMap<String, Counter> counters, SortedMap<String, Histogram> histograms, SortedMap<String, Meter> meters, SortedMap<String, Timer> timers) {
        if (gauges.size() >0 || counters.size() > 0 || histograms.size() > 0 || meters.size() > 0 || timers.size() > 0) {
            List<Map<String, Object>> result = new ArrayList<>();
            result.addAll(rearrangeMetrics(gauges, "g"));
            result.addAll(rearrangeMetrics(counters, "c"));
            result.addAll(rearrangeMetrics(histograms, "h"));
            result.addAll(rearrangeMetrics(meters, "m"));
            result.addAll(rearrangeMetrics(timers, "t"));
            try {
                for (Map<String, Object> e : result) {
                    receiver.submit(index, splunkArgs, mapper.writeValueAsString(e));
                }
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Error in serializing metrics to JSON", e);
                // TODO: figure out what to do here.
            }
        }
    }

    private List<Map<String, Object>> rearrangeMetrics(Map<String, ? extends Object> metrics, String label) {
        List<Map<String, Object>> retval = new ArrayList<>(metrics.size());
        for (Map.Entry<String, ? extends Object> s : metrics.entrySet()) {
            Map<String, Object> metric = new HashMap<>();
            metric.put("name", s.getKey());
            metric.put("val", s.getValue());
            metric.put("type", label);
            retval.add(metric);
        }
        return retval;
    }
}
