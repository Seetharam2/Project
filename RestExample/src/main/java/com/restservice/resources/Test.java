package com.restservice.resources;

public class Test {

	public static void main(String[] args) {
		String searchCriteria = "Hyundai Elantra";
		String getAdsQuery = "";
		if(searchCriteria!=null && !searchCriteria.isEmpty())
		{
			getAdsQuery = "select * from ads where cat_id = "+ 5;
			String[] searchWords = searchCriteria.split(" ");
			for(int i=0; i < searchWords.length; i++)
			{
				getAdsQuery = getAdsQuery + " OR sub_cat_name like " + "'%"+searchWords[i]+"%'" 
						+" OR ad_title like " + "'%"+searchWords[i]+"%'"+" OR ad_description like " + "'%"+searchWords[i]+"%'"
						+" OR brand like " + "'%"+searchWords[i]+"%'"+" OR state like " + "'%"+searchWords[i]+"%'" 
						+" OR city like " + "'%"+searchWords[i]+"%'" +" OR model like " + "'%"+searchWords[i]+"%'"
						+" OR make_year like " + "'%"+searchWords[i]+"%'" +" OR miles_driven like " + "'%"+searchWords[i]+"%'"
						+" OR gas_type like " + "'%"+searchWords[i]+"%'";
			}
			System.out.println(getAdsQuery);
		}
		

	}

}
