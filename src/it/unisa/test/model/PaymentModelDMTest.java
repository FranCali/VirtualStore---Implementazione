package it.unisa.test.model;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import it.unisa.beans.PaymentBean;
import it.unisa.model.PaymentModelDM;

//test effettuati con successo (nessun problema chiavi esterne database)
class PaymentModelDMTest {

	private static PaymentModelDM paymentModelDM;
	private static PaymentBean paymentBean;

	@BeforeAll
	public static void setUp() {
		paymentModelDM = new PaymentModelDM();
		paymentBean = createPaymentMethod();
	}

	@AfterEach
	public void tearDown() {
		try {
			ArrayList<PaymentBean> listContent = new ArrayList<PaymentBean>(paymentModelDM.doRetriveAll(""));
			for (PaymentBean paymentBean : listContent) {
				paymentModelDM.doDelete(paymentBean.getEmail(), paymentBean.getIdentifier());
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testDoInsert() {
		try {
			paymentModelDM.doInsert(paymentBean);
			PaymentBean result = paymentModelDM.doRetriveByEmail(paymentBean.getEmail());
			assertNotNull(result);
			assertEquals(result.getEmail(), paymentBean.getEmail());
			assertEquals(result.getIdentifier(), paymentBean.getIdentifier());
			assertEquals(result.getExpireDate(), paymentBean.getExpireDate());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testDoDelete() {
		try {
			paymentModelDM.doInsert(paymentBean);
			paymentModelDM.doDelete(paymentBean.getEmail(), paymentBean.getIdentifier());
			PaymentBean result = paymentModelDM.doRetriveByEmail(paymentBean.getEmail());
			assertNull(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testDoRetriveByEmail() {
		try {
			paymentModelDM.doInsert(paymentBean);
			PaymentBean result = paymentModelDM.doRetriveByEmail(paymentBean.getEmail());
			assertNotNull(result);
			assertEquals(result.getEmail(), paymentBean.getEmail());
			assertEquals(result.getIdentifier(), paymentBean.getIdentifier());
			assertEquals(result.getExpireDate(), paymentBean.getExpireDate());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testDoRetriveAll() {
		try {
			paymentModelDM.doInsert(paymentBean);
			PaymentBean result = paymentModelDM.doRetriveByEmail(paymentBean.getEmail());
			assertNotNull(result);
			assertEquals(result.getEmail(), paymentBean.getEmail());
			assertEquals(result.getIdentifier(), paymentBean.getIdentifier());
			assertEquals(result.getExpireDate(), paymentBean.getExpireDate());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static PaymentBean createPaymentMethod() {
		PaymentBean paymentBean = new PaymentBean();
		paymentBean.setEmail("domenicomarino42@gmail.com");
		paymentBean.setIdentifier("1");
		paymentBean.setExpireDate("2020-01-01");
		return paymentBean;
	}
}
