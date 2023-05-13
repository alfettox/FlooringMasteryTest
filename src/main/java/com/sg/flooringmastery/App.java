package com.sg.flooringmastery;

import com.sg.flooringmastery.controller.ClassFlooringMasteryController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.Map;

/**
 * GROUP 2
 **/

public class App {
    public static void main(String[] args) throws Exception {

        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.getEnvironment().setActiveProfiles("Application");
        appContext.scan("com.sg.flooringmastery");
        appContext.refresh();

        Map<String, Object> beans = appContext.getBeansOfType(Object.class);
        ClassFlooringMasteryController controller = appContext.getBean(ClassFlooringMasteryController.class);
        controller.run();
    }
}
