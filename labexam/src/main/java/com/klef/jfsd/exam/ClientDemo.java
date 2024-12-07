package com.klef.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ClientDemo {
    public static void main(String[] args) {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();

        
        insertDepartment(session, "IT", "Building A", "John Doe");
        insertDepartment(session, "HR", "Building B", "Jane Smith");

      
        deleteDepartmentById(session, 1);

        session.close();
        factory.close();
    }

    public static void insertDepartment(Session session, String name, String location, String hodName) {
        Transaction tx = session.beginTransaction();
        Department department = new Department(name, location, hodName);
        session.save(department);
        tx.commit();
        System.out.println("Department inserted: " + department);
    }

    public static void deleteDepartmentById(Session session, int id) {
        Transaction tx = session.beginTransaction();
        String hql = "DELETE FROM Department WHERE id = :deptId";
        int result = session.createQuery(hql).setParameter("deptId", id).executeUpdate();
        tx.commit();
        System.out.println("Number of records deleted: " + result);
    }
}
