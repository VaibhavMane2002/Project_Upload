package com.tka.OrganizationSystem.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tka.OrganizationSystem.entity.Country;
import com.tka.OrganizationSystem.entity.Employee;

@Repository
public class MainDAO {

	@Autowired
	SessionFactory factory;

	public String addCountry(Country c) {

		Session session = null;
		Transaction ts = null;
		String msg = null;

		try {
			session = factory.openSession();
			ts = session.beginTransaction();

			session.persist(c);
			ts.commit();

			msg = "Country is added successfully";

		} catch (Exception e) {

			if (ts != null) {
				ts.rollback();
			}
			e.printStackTrace();

		} finally {
			if (session != null) {
				session.close();
			}
		}

		return msg;

	}

	public String updateCountry(int id, Country c) {

		Session session = null;
		Transaction ts = null;
		String msg = null;

		try {
			session = factory.openSession();
			ts = session.beginTransaction();

			Country country = session.get(Country.class, id);
			country.setCname(c.getCname());
			session.merge(country);
			ts.commit();

			msg = "Country is updated Successfuly";

		} catch (Exception e) {

			if (ts != null) {
				ts.rollback();
			}

			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return msg;

	}

	public String deleteCountry(int id) {

		Session session = null;
		Transaction ts = null;
		String msg = null;

		try {
			session = factory.openSession();
			ts = session.beginTransaction();

			Country country = session.get(Country.class, id);

			session.remove(country);
			ts.commit();

			msg = "Country deleted Successfully";

		} catch (Exception e) {

			if (ts != null) {
				ts.rollback();
			}
			e.printStackTrace();

		} finally {
			if (session != null) {
				session.close();
			}
		}
		return msg;
	}

	public List<Country> getAllCountry() {

		Session session = null;
		Transaction ts = null;
		List<Country> list = null;
		String hqlQuery = "from Country";

		try {

			session = factory.openSession();
			ts = session.beginTransaction();
			Query<Country> query = session.createQuery(hqlQuery, Country.class);

			list = query.list();
			ts.commit();

		} catch (Exception e) {
			if (ts != null) {
				ts.rollback();
			}
			e.printStackTrace();

		} finally {
			if (session != null) {
				session.close();
			}
		}

		return list;
	}

	public String addEmployee(Employee emp) {

		Session session = null;
		Transaction ts = null;
		String msg = null;

		try {
			session = factory.openSession();
			ts = session.beginTransaction();

			session.persist(emp);
			ts.commit();

			msg = "Employee added Successfully";

		} catch (Exception e) {

			if (ts != null) {
				ts.rollback();
			}

			e.printStackTrace();

		} finally {
			if (session != null) {
				session.close();
			}
		}

		return msg;
	}

	public String updateEmployee(Employee emp) {

		Session session = null;
		Transaction ts = null;
		String msg = null;

		try {

			session = factory.openSession();
			ts = session.beginTransaction();
			Employee employee = session.get(Employee.class, emp.getId());

			employee.setName(emp.getName());
			employee.setCountry(emp.getCountry());
			employee.setCreatedby(emp.getCreatedby());
			employee.setCreateddtm(emp.getCreateddtm());
			employee.setUpdatedby(emp.getUpdatedby());
			employee.setDepartment(emp.getDepartment());
			employee.setStatus(emp.getStatus());
			employee.setPhoneno(emp.getPhoneno());
			employee.setEmailid(emp.getEmailid());
			employee.setSalary(emp.getSalary());
			employee.setGender(emp.getGender());

			session.merge(employee);
			ts.commit();
			msg = "Employee updated Successfully";

		} catch (Exception e) {
			if (ts != null) {
				ts.rollback();
			}
			e.printStackTrace();

		} finally {
			if (session != null) {
				session.close();
			}
		}

		return msg;
	}

	public String deleteEmployee(int id) {

		Session session = null;
		Transaction ts = null;
		String msg = null;

		try {

			session = factory.openSession();
			ts = session.beginTransaction();

			Employee emp = session.get(Employee.class, id);
			session.remove(emp);
			ts.commit();
			msg = "Employee is Deleted";

		} catch (Exception e) {
			if (ts != null) {
				ts.rollback();
			}
			e.printStackTrace();

		} finally {
			if (session != null) {
				session.close();
			}
		}

		return msg;
	}

	public List<Employee> getAllEmployee() {

		Session session = null;
		Transaction ts = null;
		List<Employee> list = null;
		String hqlQuery = "from Employee";

		try {

			session = factory.openSession();
			ts = session.beginTransaction();
			Query<Employee> query = session.createQuery(hqlQuery, Employee.class);

			list = query.list();
			ts.commit();

		} catch (Exception e) {
			if (ts != null) {
				ts.rollback();
			}
			e.printStackTrace();

		} finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}

	public Employee getEmployeeById(int id) {

		Session session = null;
		Transaction ts = null;
		Employee emp = null;

		try {
			session = factory.openSession();
			ts = session.beginTransaction();

			emp = session.get(Employee.class, id);
			ts.commit();

		} catch (Exception e) {
			if (ts != null) {
				ts.rollback();
			}
			e.printStackTrace();

		} finally {
			if (session != null) {
				session.close();
			}
		}

		return emp;
	}

	public List<Employee> getEmployeeByStatus(String status) {

		Session session = null;
		Transaction ts = null;
		List<Employee> list = null;
		String hqlQuery = "from Employee where status=:mystatus";

		try {

			session = factory.openSession();
			ts = session.beginTransaction();
			Query<Employee> query = session.createQuery(hqlQuery, Employee.class);

			query.setParameter("mystatus", status);

			list = query.list();
			ts.commit();

		} catch (Exception e) {
			if (ts != null) {
				ts.rollback();
			}
			e.printStackTrace();

		} finally {
			if (session != null) {
				session.close();
			}
		}

		return list;
	}

	public Employee loginCheck(Employee emp) {

		Session session = null;
		Transaction ts = null;
		Employee employee = null;
		String hqlQuery = "from Employee where emailid=:myemailid and phoneno=:myphoneno";

		try {

			session = factory.openSession();
			ts = session.beginTransaction();

			Query<Employee> query = session.createQuery(hqlQuery, Employee.class);

			query.setParameter("myemailid", emp.getEmailid());
			query.setParameter("myphoneno", emp.getPhoneno());

			employee = query.uniqueResult();
			ts.commit();

		} catch (Exception e) {
			if (ts != null) {
				ts.rollback();
			}
			e.printStackTrace();

		} finally {
			if (session != null) {
				session.close();
			}
		}
		return employee;
	}

	public List<Employee> getEmployeeBySalary(double salary) {

		Session session = null;
		Transaction ts = null;
		List<Employee> list = null;
		String hqlQuery = "from Employee where salary > :mysalary";

		try {

			session = factory.openSession();
			ts = session.beginTransaction();

			Query<Employee> query = session.createQuery(hqlQuery, Employee.class);
			query.setParameter("mysalary", salary);
			list = query.list();
			ts.commit();

		} catch (Exception e) {

			if (ts != null) {
				ts.rollback();
			}
			e.printStackTrace();

		} finally {
			if (session != null) {
				session.close();
			}
		}

		return list;
	}

}
