package edu.montgomerycollege.drdoom.Services;

import java.io.File;

public interface EmailService {
    void send(String from, String to, String title, String body, File file);
}
