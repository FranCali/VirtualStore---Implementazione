package it.unisa.test.model;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import it.unisa.beans.ContentBean;
import it.unisa.beans.ReviewBean;
import it.unisa.model.ContentModelDM;
import it.unisa.model.ReviewModelDM;

//test effettuati con successo (nessun problema chiavi esterne database)
class ReviewModelDMTest {

	private static ReviewModelDM reviewModelDM;
	private static ReviewBean reviewBean;
	private static ContentModelDM contentModelDM;

	@BeforeAll
	public static void setUp() {
		reviewModelDM = new ReviewModelDM();
		contentModelDM = new ContentModelDM();
		reviewBean = createReview();
	}

	@AfterEach
	public void tearDown() {
		try {
			ArrayList<ContentBean> listContent = new ArrayList<ContentBean>(contentModelDM.doRetrieveAll(""));
			for (ContentBean contentBean : listContent) {
				contentModelDM.doDelete(contentBean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testDoRetrieveByContentId() {
		try {
			ContentModelDM contentModelDM = new ContentModelDM();
			ArrayList<ContentBean> listContent = new ArrayList<ContentBean>(contentModelDM.doRetrieveAll(""));
			ContentBean contentBean = listContent.get(0);
			reviewModelDM.doSave(reviewBean);
			ArrayList<ReviewBean> listReview = new ArrayList<ReviewBean>(
					reviewModelDM.doRetrieveByContentId(contentBean.getId()));
			ReviewBean result = listReview.get(0);
			assertNotNull(result);
			assertEquals(reviewBean.getUser_email(), result.getUser_email());
			assertEquals(reviewBean.getContent_id(), result.getContent_id());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testDoSave() {
		try {
			ReviewBean review = createReview();
			reviewModelDM.doSave(review);
			ArrayList<ReviewBean> listReview = new ArrayList<ReviewBean>(
					reviewModelDM.doRetrieveByContentId(review.getContent_id()));
			ReviewBean result = listReview.get(0);
			assertNotNull(result);
			assertEquals(reviewBean.getUser_email(), result.getUser_email());
			assertEquals(reviewBean.getContent_id(), result.getContent_id());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testDoDelete() {
		try {
			reviewBean = createReview();
			reviewModelDM.doSave(reviewBean);
			ArrayList<ContentBean> listContent = new ArrayList<ContentBean>(contentModelDM.doRetrieveAll(""));
			ContentBean contentBean = listContent.get(0);
			reviewBean.setContent_id(contentBean.getId());
			reviewModelDM.doDelete(reviewBean);
			ReviewBean result = reviewModelDM.doRetrieveByContentIdandUserEmail(reviewBean.getContent_id(),
					reviewBean.getUser_email());
			assertNull(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Test
	void testDoRetrieveByContentIdandUserEmail() {
		try {
			ReviewBean review = createReview();
			reviewModelDM.doSave(review);
			ReviewBean result = reviewModelDM.doRetrieveByContentIdandUserEmail(review.getContent_id(),
					review.getUser_email());
			assertNotNull(result);
			assertEquals(reviewBean.getUser_email(), result.getUser_email());
			assertEquals(reviewBean.getContent_id(), result.getContent_id());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testDoUpdate() {
		try {
			ReviewBean review = createReview();
			reviewModelDM.doSave(review);
			review.setTitle("Mock");
			reviewModelDM.doUpdate(review);
			ReviewBean result = reviewModelDM.doRetrieveByContentIdandUserEmail(review.getContent_id(),
					review.getUser_email());
			assertNotNull(result);
			assertEquals("Mock", result.getTitle());

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static ReviewBean createReview() {
		ContentModelDM contentModelDM = new ContentModelDM();
		try {
			contentModelDM.doSave(new ContentBean());
			ArrayList<ContentBean> listContent = new ArrayList<ContentBean>(contentModelDM.doRetrieveAll(""));
			ContentBean contentBean = listContent.get(0);
			reviewBean = new ReviewBean();
			reviewBean.setUser_email("domenicomarino42@gmail.com");
			reviewBean.setContent_id(contentBean.getId());
			reviewBean.setReview_date("2018-01-01");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return reviewBean;
	}
}
