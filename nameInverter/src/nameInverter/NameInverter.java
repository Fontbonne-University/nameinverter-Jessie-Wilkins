package nameInverter;

import java.util.ArrayList;
import java.util.Arrays;

public class NameInverter {


	public String invertName(String name) {
		// TODO Auto-generated method stub
		if(name == null || name.length()<=0) {
			return "";
		}
		else {
			
			ArrayList<String> names = splitNames(name);
			if(names.size() == 1) {
				return names.get(0);
			}
			
			else if(isHonorific(names) && names.size()>3) {
				String postNominals = "";
				
				postNominals = getPostNominals(names, postNominals, isHonorific(names));
				return names.get(2)+", "+names.get(1)+" "+postNominals.trim();
			}
				
			else if(isHonorific(names)) {
				return names.get(2)+", "+names.get(1);
			}
			else if(isPostNominal(names)) {
				String postNominals = "";
				
				postNominals = getPostNominals(names, postNominals, isHonorific(names));
				return names.get(1)+", "+names.get(0)+" "+postNominals.trim();
			}
			
			else {
				return names.get(1)+", "+names.get(0);
			}
			
			
		}
		
		
	}

	public String getPostNominals(ArrayList<String> names, String postNominals, boolean honorific) {
		if(honorific) {
			for(int i = 3; i<names.size(); i++) {
				postNominals+=names.get(i)+" ";
			}
		}
		else {
			
			for(int i = 2; i<names.size(); i++) {
				postNominals+=names.get(i)+" ";
				
			}
			
		}
		
		return postNominals;
	}

	public boolean isPostNominal(ArrayList<String> names) {
		return names.size() > 2;
		
	}

	public boolean isHonorific(ArrayList<String> names) {
		return names.get(0).equals("Mr.") || names.get(0).equals("Mrs.") || 
				names.get(0).equals("Ms.") || names.get(0).equals("Dr.");
	}

	public ArrayList<String> splitNames(String name) {
		ArrayList<String> names = new ArrayList<String> (Arrays.asList(name.trim().split("\\s+")));
		return names;
	}

}