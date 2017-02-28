package com.epam.java.se.task6andtask7;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by chris on 28.02.2017.
 */
public class AtomicSubmarineTest {

    @Test
    public void testLaunchedSubmarineHaveWorkingEngine() throws Exception {
        final AtomicSubmarine submarine = new AtomicSubmarine();

        submarine.start();

        assertThat(submarine.getStatus(), is("launched"));
        assertThat(submarine.getEngineStatus(), is("switched on"));
    }

    @Test
    public void testNotLaunchedSubmarineHaveNotWorkingEngine() throws Exception {
        final AtomicSubmarine submarine = new AtomicSubmarine();

        assertThat(submarine.getStatus(), is("not launched"));
        assertThat(submarine.getEngineStatus(), is("switched off"));
    }

    @Test
    public void testUnLaunchedSubmarineHaveNotWorkingEngine() throws Exception {
        final AtomicSubmarine submarine = new AtomicSubmarine();

        submarine.start();
        submarine.stop();

        assertThat(submarine.getStatus(), is("not launched"));
        assertThat(submarine.getEngineStatus(), is("switched off"));
    }
}
