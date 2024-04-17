package model;

import java.util.ArrayList;

public class AccountCollection implements java.io.Serializable {
	private ArrayList<Accounts> defAccounts;

	public AccountCollection() {
		defAccounts = new ArrayList<>();

		// this.add(new Accounts("Chris", "1"));
		// this.add(new Accounts("Devon", "22"));
		// this.add(new Accounts("River", "333"));
		// this.add(new Accounts("Ryan", "4444"));
	}

	public void add(Accounts account) {
		defAccounts.add(account);
	}
	
	public int getSize() {
		return defAccounts.size();
	}
	
	public Accounts getElement(int index) {
		return defAccounts.get(index);
	}
	
	public ArrayList<Accounts> getArray() {
		return defAccounts;
	}

	public Accounts getAccount(String name, String password) {
		for (Accounts acct : defAccounts) {
			if (acct.getUsername().equals(name) && acct.getPassword().equals(password)) {
				return acct;
			}
		}
		return null;
	}

	public boolean containsName(String name) {
		for (Accounts acct : defAccounts) {
			if (acct.getUsername().equals(name)) {
				return true;
			}
		}
		return false;
	}

	public boolean containsAccount(Accounts otherAcct) {
		if (defAccounts.contains(otherAcct)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void clear() {
		defAccounts.clear();
	}
}
