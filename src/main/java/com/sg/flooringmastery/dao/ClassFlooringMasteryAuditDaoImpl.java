package com.sg.flooringmastery.dao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * GROUP 2
 * Wiley Edge
 **/
@Repository
@Profile("Application")
public class ClassFlooringMasteryAuditDaoImpl implements ClassFlooringMasteryAuditDao{

    public static final String AUDIT_FILE = "audit.txt";

    public void writeAuditEntry(String entry) throws ClassFlooringMasteryPersistenceException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e) {
            throw new ClassFlooringMasteryPersistenceException("Could not persist audit information.", e);
        }
        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString() + " : " + entry);
        out.flush();
    }
}