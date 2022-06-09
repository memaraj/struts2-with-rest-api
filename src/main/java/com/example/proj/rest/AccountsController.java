package com.example.proj.rest;
import java.util.Map;

import com.example.proj.model.Account;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;


public class AccountsController implements ModelDriven<Object> {
	private String id;
	private Account account = new Account();
	private Object accounts;
    private AccountsRepository accountRepository = new AccountsRepository();

    private static Map<String, Account> map;
    {
        try {
			map = null;
            map = accountRepository.findAllAccounts();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HttpHeaders index() {
        accounts = map;
		return new DefaultHttpHeaders("index").disableCaching();
    }

	//GET accounts/id/
	public HttpHeaders show() {
		accounts = accountRepository.getAccountById(getId());
		return new DefaultHttpHeaders("show");
	}

	//POST /accounts
	public HttpHeaders create() throws Exception {
		System.out.println(account.toString());
		System.out.println("POST \t /account:" +account.getId());
		String result = accountRepository.save(account);
		return new DefaultHttpHeaders(result);
	}

	//PUT /accounts/id
	public HttpHeaders update() throws Exception {
		account = map.get(getId());
		System.out.println("PUT \t /account" +getId());
		accounts = accountRepository.update(getId(), account);
		return new DefaultHttpHeaders("update");
	}

	//DELETE /accounts/id
	public HttpHeaders destroy() throws Exception {
		System.out.println("DELETE \t /account:" +getId());
		String result = accountRepository.remove(getId());
		return new DefaultHttpHeaders(result);
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		accounts = accountRepository.getAccountById(id);
		this.id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public Object getModel() {
		return (accounts != null ? accounts : account);
	}
    
}
