/*
 * Copyright 2010-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.cloudfoundry.tools.timeout;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * Tests for {@link TimeoutProtectionHttpHeader}.
 * 
 * @author Phillip Webb
 */
public class TimeoutProtectionHttpHeaderTest {

	@Test
	public void shouldHaveInitialRequest() {
		assertThat(TimeoutProtectionHttpHeader.INITIAL_REQUEST, is("x-cloudfoundry-timeout-protection-initial-request"));
	}

	@Test
	public void shouldHavePollRequest() throws Exception {
		assertThat(TimeoutProtectionHttpHeader.POLL, is("x-cloudfoundry-timeout-protection-poll"));
	}

}
