package com.dxctraining.dao;

import com.dxctraining.entities.Account;
import com.dxctraining.entities.Customer;
import com.dxctraining.exceptions.*;
import java.util.*;

public class CustomerDaoImpl  implements ICustomerDao{
    private Map<String,Customer>store=new HashMap<>();

    private int generatedId;

     String generateId(){
        generatedId++;
        String idStr=""+generatedId;
        return idStr;
    }


    @Override
    public Customer findById(String id) {
        boolean exists= store.containsKey(id);
        if(!exists){
            throw new CustomerNotFoundException("customer not found for id="+id);
        }
        Customer customer=store.get(id);
        return customer;
    }

    @Override
    public void add(Customer customer) {
       String id=generateId();
       store.put(id,customer);
       customer.setId(id);

    }

    @Override
    public void delete(String id) {
        store.remove(id);
    }

    @Override
    public List<Customer> findAll() {
         Collection<Customer>collection=store.values();
         List<Customer>list=new ArrayList<>();
         for (Customer customer:collection){
             list.add(customer);
         }
        return list;
    }

    @Override
    public Customer updateName(String id, String name) {
       Customer customer=  findById(id);
       customer.setName(name);
       return customer;
     }
}
