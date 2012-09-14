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
