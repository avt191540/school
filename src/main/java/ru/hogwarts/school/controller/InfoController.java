package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@RestController
public class InfoController {

    @Value("${server.port}")
    private int port;

    @GetMapping("/getPort")
    public String getPort() {
        return "Port = " + port;
    }

    @GetMapping("/stream/{limit}")
    public String getCalculationResult(@PathVariable int limit) {

        long start1 = System.currentTimeMillis();
        int sum1 = Stream.iterate(1, a -> a +1)
                .limit(limit)
                .collect(Collectors.toList())
                .parallelStream()
                .reduce(0, (a, b) -> a + b);
        long finish1 = System.currentTimeMillis();
        long time1 = finish1 - start1;

        long start2 = System.currentTimeMillis();
        int sum2 = Stream.iterate(1, a -> a +1)
                .limit(limit)
                .reduce(0, (a, b) -> a + b);
        long finish2 = System.currentTimeMillis();
        long time2 = finish2 - start2;

        return "Option-1 summary: " + sum1 + " time spent: " + time1 + "ms. " +
                "Option-2 summary: " + sum2 + " time spent: " + time2 + "ms.";
    }
}
