package org.hakunamatata.myhome.database;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.hakunamatata.myhome.model.Comment;
import org.hakunamatata.myhome.model.Favourite;
import org.hakunamatata.myhome.model.Member;

public class Database {

    private static Map<Long, Comment> comments = new HashMap<>();
    private static Map<Long, Member> members = new HashMap<>();
    private static Map<Long, Favourite> favourites = new HashMap<>();

    public static Map<Long, Comment> getComments() {

	Comment c1 = new Comment(1, "Hello", 2000, "Admin", new Date(), "Admin", new Date());
	Comment c2 = new Comment(2, "Hi", 2000, "testuser1", new Date(), "testuser1", new Date());

	comments.put(1L, c1);
	comments.put(2L, c2);

	return comments;
    }

    public static Map<Long, Member> getMembers() {

	Member m1 = new Member((long) 1000, (short) 0, "Admin", "middlename", "lastname", (long) 9876, "admin@abc.com",
		"hyderabad", (short) 0, new Date(), new Date());

	Member m2 = new Member((long) 1001, (short) 0, "Default", "", "group", (long) 9876, "defaultgroup@abc.com",
		"hyderabad", (short) -1, new Date(), new Date());

	members.put(m1.getMemberId(), m1);
	members.put(m2.getMemberId(), m2);

	return members;
    }

    public static Map<Long, Favourite> getFavourites() {

	Favourite f1 = new Favourite(1000, 2000);
	Favourite f2 = new Favourite(1000, 2001);

	favourites.put(1L, f1);
	favourites.put(2L, f2);

	return favourites;
    }
}
