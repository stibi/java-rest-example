package com.example.restservice;

import java.io.File;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Map;
import java.util.Properties;
import java.net.InetAddress;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @GetMapping("/")
    public String index() {
        // String hostname = InetAddress.getLocalHost().getHostName();
        String hostname = System.getenv("HOSTNAME");
        String output = String.format("<h1>Hello from %s</h1></br>\n", hostname);

        output += "<h2>List of all env vars:</h2>\n<ul>\n";

        Map<String, String> envVars = System.getenv();
        for (Map.Entry<String, String> entry : envVars.entrySet()) {
            output += String.format("<li>%s : %s</li>\n", entry.getKey(), entry.getValue());
        }
        output += "</ul>\n";
        
        return output;
    }

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/soubory")
    public Set<String> soubory() {
        return Stream.of(new File("/opt").listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());
    }

    @GetMapping("/envtest")
    public String envtest() {
        String envValue = System.getenv("MOJE_TEST_ENV");
        return String.format("MOJE_TEST_ENV hodnota = %s", envValue);
    }
}
