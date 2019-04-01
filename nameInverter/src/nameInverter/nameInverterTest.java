package nameInverter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class nameInverterTest {

	NameInverter nameMethods;
	
	@Before
	public void setUp() throws Exception {
		nameMethods = new NameInverter();
	}

	private void assertInverted(String invertedName, String originalName) {
		assertEquals(invertedName, nameMethods.invertName(originalName));
	}
	
	public void givenNull_returnsEmptyString() {
		assertInverted("", null);
	}

	@Test public void givenEmptyString_returnEmptyString() {
		assertInverted("", "");
	}
	
	@Test public void givenSimpleName_returnSimpleName() {
		assertInverted("John", "John");
	}
	
	@Test
	public void givenFirstLast_returnLastFirst() {
		assertInverted("Doe, John", "John Doe");
	}
	@Test
	public void givenASimpleNameWithSpaces_returnSimpleNameWithoutSpaces() {
		assertInverted("John", " John ");
	}
	@Test
	public void givenFirstLastWithExtraSpaces_returnLastFirst() {
		assertInverted("Doe, John", " John  Doe ");
	}
	
	@Test
	public void ignoreHonorific() {
		assertInverted("Doe, John", "Mr. John Doe");
	}
	@Test
	public void postNominals_stayAtEnd() {
		assertInverted("Doe, John II", "John Doe II");
		assertInverted("Doe, John BS. Phd.", "John Doe BS. Phd.");
	}
	
	@Test
	public void integration() {
		assertInverted("Doe, John I Sr. Phd.", "  Dr.  John  Doe  I Sr. Phd.");
	}

}
