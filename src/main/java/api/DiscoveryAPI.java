package api;

import com.ibm.watson.developer_cloud.discovery.v1.Discovery;
import com.ibm.watson.developer_cloud.discovery.v1.model.QueryOptions;
import com.ibm.watson.developer_cloud.discovery.v1.model.QueryOptions.Builder;
import com.ibm.watson.developer_cloud.discovery.v1.model.QueryResponse;

import data.Collection;
import data.Configuration;


public class DiscoveryAPI{
	
	protected Discovery discovery;
	private Collection collection;
	private QueryResponse queryResponse;

	
	public String query(String queryString, String natural_language_query, int limit, String filter, boolean passages, boolean deduplicate, boolean highlight){
	
		try{
			Builder options = new QueryOptions.Builder(collection.environmentId, collection.id);
	        
			if (natural_language_query!=null) {
				options.naturalLanguageQuery(natural_language_query);
			}else if (queryString!=null) {				
				options.query(queryString);
			}
			
			if (filter!=null) options.filter(filter);
			
			if (passages){ 
				options.passages(passages);
				if (passages) {
		        	//options.passagesFields(Arrays.asList("searchText"));	        
		        	options.passagesCount(limit);
		        	options.passagesCharacters(100);
				}
			}
	        //options.returnFields(Arrays.asList("title","winsId","searchText"));
	        options.count(limit);
	        options.deduplicate(deduplicate);
	        options.highlight(highlight);
	        
	        //System.out.println("natural_language_query:" + natural_language_query);
			//System.out.println("query:" + queryString);
			//System.out.println("filter:" + filter);
	        
			queryResponse = discovery.query(options.build()).execute();
			
			return queryResponse.toString();

		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return "null";
	}
	
	public DiscoveryAPI(Configuration config, Collection collection){							
		this.collection = collection;
		
		this.discovery =  new Discovery(config.apiVersion); 
		this.discovery.setEndPoint(config.apiPoint);
		this.discovery.setUsernameAndPassword(config.user, config.password);
	}

}
