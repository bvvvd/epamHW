package com.epam.java.se.task6andtask7;

import java.lang.annotation.*;

@Documented
public @interface  Schooner {
    String author();
    String date();
    String version();
}
