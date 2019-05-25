package org.hakunamatata.myhome.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hakunamatata.myhome.database.Database;
import org.hakunamatata.myhome.model.Member;

public class MemberService {

    private Map<Long, Member> members = Database.getMembers();

    public List<Member> getAllMembers() {
	return new ArrayList<Member>(members.values());
    }

    public Member getMember(long memberId) {
	return members.get(memberId);
    }

    public Member addMember(Member member) {
	member.setMemberId(members.size() + 1);
	members.put(member.getMemberId(), member);
	return member;
    }

    public Member updateMember(Member member) {
	if (member.getMemberId() <= 0)
	    return null;
	members.put((long)member.getMemberId(), member);
	return member;
    }

    public Member deleteMember(long memberId) {
	return members.remove(memberId);
    }
}
