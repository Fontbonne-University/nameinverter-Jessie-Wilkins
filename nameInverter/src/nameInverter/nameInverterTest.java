package nameInverter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class nameInverterTest {

	@Before
	public void setUp() throws Exception {
	}

	/*@Test
	public void nothing() {

	}*/
	@Test
	public void givenNull_returnsEmptyString() {
		String invertedName = "";
		String originalName = null;
		assertInverted(invertedName, originalName);
	}

	private void assertInverted(String invertedName, String originalName) {
		assertEquals(invertedName, invertName(originalName));
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
	

	private String invertName(String name) {
		// TODO Auto-generated method stub
		if(name == null || name.length()<=0) {
			return "";
		}
		else {
			
			String[] names = splitNames(name);
			if(names.length == 1) {
				return names[0];
			}
			
			else if(isHonorific(names) && names.length>3) {
				String postNominals = "";
				
				postNominals = getPostNominals(names, postNominals, isHonorific(names));
				return names[2]+", "+names[1]+" "+postNominals.trim();
			}
				
			else if(isHonorific(names)) {
				return names[2]+", "+names[1];
			}
			else if(isPostNominal(names)) {
				String postNominals = "";
				
				postNominals = getPostNominals(names, postNominals, isHonorific(names));
				return names[1]+", "+names[0]+" "+postNominals.trim();
			}
			
			else {
				return names[1]+", "+names[0];
			}
			
			
		}
		
		
	}

	private String getPostNominals(String[] names, String postNominals, boolean honorific) {
		if(honorific) {
			for(int i = 3; i<names.length; i++) {
				postNominals+=names[i]+" ";
			}
		}
		else {
			
			for(int i = 2; i<names.length; i++) {
				postNominals+=names[i]+" ";
				
			}
			
		}
		
		return postNominals;
	}

	private boolean isPostNominal(String[] names) {
		return names.length > 2;
		
	}

	private boolean isHonorific(String[] names) {
		return names[0].equals("Mr.") || names[0].equals("Mrs.") || 
				names[0].equals("Ms.") || names[0].equals("Dr.");
	}

	private String[] splitNames(String name) {
		String names[] = name.trim().split("\\s+");
		return names;
	}

}
