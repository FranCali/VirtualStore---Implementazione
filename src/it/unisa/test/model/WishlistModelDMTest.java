package it.unisa.test.model;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import it.unisa.beans.ContentBean;
import it.unisa.beans.WishlistBean;
import it.unisa.model.ContentModelDM;
import it.unisa.model.WishlistModelDM;

//test effettuati con successo (qualche problema con chiavi esterne )
class WishlistModelDMTest {

	private static WishlistModelDM wishlistModelDM;;
	private static WishlistBean wishlistBean;
	private static ContentModelDM contentModelDM;

	@BeforeAll
	public static void setUp() {
		wishlistModelDM = new WishlistModelDM();
		contentModelDM = new ContentModelDM();
		wishlistBean = createWishlist();
	}

	@AfterEach
	public void tearDown() {
		try {
			ArrayList<ContentBean> listContent = new ArrayList<ContentBean>(contentModelDM.doRetrieveAll(""));
			for (ContentBean contentBean : listContent) {
				contentModelDM.doDelete(contentBean);
			}
			wishlistModelDM.doRemoveEmailByWishlist(wishlistBean.getEmail());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testDoInsert() {
		wishlistBean = createWishlist();
		try {
			String result = wishlistModelDM.doRetrieveEmailByWishlist(wishlistBean.getEmail());
			assertNotNull(result);
			assertEquals(wishlistBean.getEmail(), result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testDoRetriveAll() {
		try {
			wishlistModelDM.doInsertContent(wishlistBean.getEmail(), wishlistBean.getId());
			ArrayList<ContentBean> listContent = new ArrayList<ContentBean>(
					wishlistModelDM.doRetriveAll(wishlistBean.getEmail()));
			ContentBean result = listContent.get(0);
			assertNotNull(result);
			assertEquals(wishlistBean.getId(), result.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testDoInsertContent() {

		try {

			ContentBean contentBean = wishlistBean.getElementById(wishlistBean.getId());
			wishlistModelDM.doInsertContent(wishlistBean.getEmail(), contentBean.getId());
			ArrayList<ContentBean> newListContent = new ArrayList<ContentBean>(
					wishlistModelDM.doRetriveAll(wishlistBean.getEmail()));
			ContentBean resultContent = newListContent.get(0);
			String resultEmail = wishlistModelDM.doRetrieveEmailByWishlist(wishlistBean.getEmail());
			assertNotNull(resultContent);
			assertNotNull(resultEmail);
			assertEquals(resultEmail, wishlistBean.getEmail());
			assertEquals(contentBean.getId(), wishlistBean.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testDoRemoveContent() {

		try {
			wishlistModelDM.doInsertContent("domenicomarino42@gmail.com", wishlistBean.getId());
			boolean result = wishlistModelDM.doRemoveContent(wishlistBean.getEmail(), wishlistBean.getId());
			assertNotNull(result);
			assertEquals(true, result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testDoRemoveAllContents() {
		try {
			wishlistModelDM.doInsertContent("domenicomarino42@gmail.com", wishlistBean.getId());
			wishlistModelDM.doRemoveAllContents(wishlistBean.getEmail());
			ArrayList<ContentBean> listContent = new ArrayList<ContentBean>(
					wishlistModelDM.doRetriveAll(wishlistBean.getEmail()));
			ContentBean result = listContent.get(0);
			assertNull(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static WishlistBean createWishlist() {
		ContentModelDM contentModelDM = new ContentModelDM();
		wishlistBean = new WishlistBean();
		try {
			contentModelDM.doSave(new ContentBean());
			ArrayList<ContentBean> listContent = new ArrayList<ContentBean>(contentModelDM.doRetrieveAll(""));
			ContentBean contentBean = listContent.get(0);
			wishlistBean.insertContent(contentBean);
			wishlistBean.setId(contentBean.getId());
			wishlistBean.setEmail("domenicomarino42@gmail.com");
			wishlistModelDM.doInsert("domenicomarino42@gmail.com");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return wishlistBean;
	}
}
