package me.boops;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;

import me.boops.jumblr.BlogFollowing;
import me.boops.jumblr.BlogUnfollow;

public class Main {
	
	public static void main(String[] args){
		
		Config Conf = new Config();
		
		BlogUnfollow blog = new BlogUnfollow(Conf.getCustomerKey(), Conf.getCustomerSecret(), Conf.getToken(), Conf.getTokenSecret());
		BlogFollowing following = new BlogFollowing(Conf.getCustomerKey(), Conf.getCustomerSecret(), Conf.getToken(), Conf.getTokenSecret());
		
		//blog.getBlog("sir-boops");
		following.getFollowing();
		
		System.out.println("You are following: " + following.getTotalFollowing() + " Blogs in total");
		
		
		int runs = 0;
		int total_to_unfollow = 0;
		while(runs<following.getTotalFollowing()){
			
			for(int i=0; i<following.getFollowingBlogs().length(); i++){
				
				following.decodeBlog((JSONObject) following.getFollowingBlogs().get(i));
				if(following.getBlogLastUpdated() < 1496296800){
					Date last_active = new Date((long)following.getBlogLastUpdated()*1000L);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String formattedDate = sdf.format(last_active);
					total_to_unfollow++;
					System.out.println("Unfollowing: " + following.getBlogName() + ", Last active: " + formattedDate);
					blog.unfollow(following.getBlogName());
				}
			}
			
			runs+=following.getFollowingBlogs().length();
			following.setOffset(runs);
			following.getFollowing();
		}
		
		System.out.println("I unfollowed " + total_to_unfollow + " blogs");
		
	}
}
