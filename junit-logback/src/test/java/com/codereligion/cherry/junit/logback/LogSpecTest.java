/**
 * Copyright 2015 www.codereligion.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.codereligion.cherry.junit.logback;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.junit.Test;
import org.slf4j.LoggerFactory;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class LogSpecTest {

    @Test
    public void allowsToSetLoggerByName() {

        // given
        final LogSpec logSpec = new LogSpec("foo", Level.ERROR);

        // when
        final Logger logger = logSpec.getLogger();

        // then
        assertThat(logger, is(LoggerFactory.getLogger("foo")));
    }

    @Test
    public void allowsToSetLoggerByClassName() {

        // given
        final LogSpec logSpec = new LogSpec(this.getClass(), Level.ERROR);

        // when
        final Logger logger = logSpec.getLogger();

        // then
        assertThat(logger, is(LoggerFactory.getLogger(this.getClass())));
    }

    @Test
    public void toStringResultReflectsObjectState() {

        // given
        final LogSpec logSpec = new LogSpec("foo", Level.ERROR);

        // when
        final String toString = logSpec.toString();

        // then
        assertThat(toString, containsString("foo"));
        assertThat(toString, containsString("ERROR"));
    }

    @Test
    public void overridesEqualsAccordingToItsContract() {

        // given
        final LogSpec first = new LogSpec("foo", Level.ERROR);
        final LogSpec second = new LogSpec("foo", Level.ERROR);
        final LogSpec third = new LogSpec("foo", Level.ERROR);
        final LogSpec shouldNotBeEqual = new LogSpec("bar", Level.ERROR);


        // not equals to null
        assertThat(first, is(not(nullValue())));

        // reflexive
        assertThat(first, is(first));

        // symmetric
        assertThat(first, is(second));

        // transitive
        assertThat(first, is(third));

        // negative test
        assertThat(first, is(not(shouldNotBeEqual)));
    }

    @Test
    public void overridesHashCodeAccordingToItsContract() {
        // given
        final LogSpec first = new LogSpec("foo", Level.ERROR);
        final LogSpec second = new LogSpec("foo", Level.ERROR);

        assertThat(first, is(second));
        assertThat(first.hashCode(), is(second.hashCode()));
    }
}