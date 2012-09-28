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
package org.cloudfoundry.tools.io;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.io.InputStream;

import org.junit.Test;

/**
 * Tests for {@link NoCloseInputStream}.
 * 
 * @author Phillip Webb
 */
public class NoCloseInputStreamTest {

	@Test
	public void shouldNotClose() throws Exception {
		InputStream inputStream = mock(InputStream.class);
		NoCloseInputStream noCloseInputStream = new NoCloseInputStream(inputStream);
		noCloseInputStream.read();
		noCloseInputStream.close();
		verify(inputStream).read();
		verify(inputStream, never()).close();
	}

}
