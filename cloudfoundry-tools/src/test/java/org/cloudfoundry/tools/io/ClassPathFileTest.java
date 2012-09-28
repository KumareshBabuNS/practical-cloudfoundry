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

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.cloudfoundry.tools.io.exception.ResourceDoesNotExistException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.core.io.ClassPathResource;

/**
 * Tests for {@link ClassPathFile}.
 * 
 * @author Phillip Webb
 */
public class ClassPathFileTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void shouldLoadRelativeToClass() throws Exception {
		File file = new ClassPathFile(getClass(), "a.txt");
		assertThat(file.getContent().asString(), is("a"));
	}

	@Test
	public void shouldLoadFromPathUsingClass() throws Exception {
		File file = new ClassPathFile(getClass(), "/org/cloudfoundry/tools/io/a.txt");
		assertThat(file.getContent().asString(), is("a"));
	}

	@Test
	public void shouldLoadUsingExactPath() throws Exception {
		ClassPathResource resource = new ClassPathResource("/org/cloudfoundry/tools/io/a.txt");
		System.out.println(resource.getInputStream());
		File file = new ClassPathFile("/org/cloudfoundry/tools/io/a.txt");
		assertThat(file.getContent().asString(), is("a"));
	}

	@Test
	public void shouldSupportNotExists() throws Exception {
		File file = new ClassPathFile("/org/cloudfoundry/tools/io/missing.txt");
		assertThat(file.exists(), is(false));
	}

	@Test
	public void shouldThrowOnLoadNotExists() throws Exception {
		File file = new ClassPathFile(getClass(), "missing.txt");
		this.thrown.expect(ResourceDoesNotExistException.class);
		file.getContent().asString();
	}
}
