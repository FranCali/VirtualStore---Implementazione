package it.unisa.test.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import it.unisa.beans.ContentBean;
import it.unisa.model.ContentModelDM;

//casi di test perfettamente funzionanti 
class ContentModelDMTest {

	private static ContentModelDM contentModelDM;
	private static ContentBean contentBean;
	private static final double DELTA = 1e-15;

	@BeforeAll
	public static void setUp() {
		contentModelDM = new ContentModelDM();
		contentBean = createContent();
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
	void testDoRetrieveAll() {
		ArrayList<ContentBean> listContents;
		contentBean = createContent();
		try {
			listContents = new ArrayList<ContentBean>(contentModelDM.doRetrieveAll(""));
			ContentBean result = listContents.get(0);
			assertNotNull(contentBean);
			assertEquals(contentBean.getId(), result.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testDoRetrieveByKey() {
		try {
			ContentBean result = contentModelDM.doRetrieveByKey(contentBean.getId());
			assertNotNull(result);
			assertEquals(contentBean.getId(), result.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testDoRetrieveByType() {
		ArrayList<ContentBean> listContents;
		contentBean = createContent();
		try {
			listContents = new ArrayList<ContentBean>(contentModelDM.doRetrieveByType(contentBean.getType(), "nome"));
			ContentBean result = listContents.get(0);
			assertNotNull(contentBean);
			assertEquals(contentBean.getType(), result.getType());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testDoSearch() {
		ArrayList<ContentBean> listContents;
		contentBean = createContent();
		try {
			listContents = new ArrayList<ContentBean>(contentModelDM.doSearch(contentBean.getName(), "nome"));
			ContentBean result = listContents.get(0);
			assertNotNull(contentBean);
			assertEquals(contentBean.getName(), result.getName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testDoSave() {
		ArrayList<ContentBean> listContents;
		contentBean = createContent();
		try {
			listContents = new ArrayList<ContentBean>(contentModelDM.doSearch(contentBean.getName(), "nome"));
			ContentBean result = listContents.get(0);
			assertNotNull(contentBean);
			assertEquals(contentBean.getId(), result.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testDoDelete() {
		contentBean = createContent();
		try {
			boolean result = contentModelDM.doDelete(contentBean);
			assertNotNull(result);
			assertEquals(true, result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testDoRetrieveByCovers() {
		ArrayList<ContentBean> listContents;
		contentBean = createContent();
		contentBean.setCover("images/covers/");
		try {
			listContents = new ArrayList<ContentBean>(contentModelDM.doRetrieveByCovers());
			ContentBean result = listContents.get(0);
			assertNotNull(result);
			assertEquals(contentBean.getCover(), result.getCover());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testDoRetrieveByPrice() {
		ArrayList<ContentBean> listContents;
		contentBean = createContent();
		try {
			listContents = new ArrayList<ContentBean>(contentModelDM.doRetrieveByPrice(10));
			ContentBean result = listContents.get(0);
			assertNotNull(result);
			assertEquals(contentBean.getPrice(), result.getPrice(), DELTA);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static ContentBean createContent() {
		ContentBean contentBean = new ContentBean();
		contentBean.setName("Mock");
		contentBean.setType("Mock");
		contentBean.setPrice(20);
		try {
			contentModelDM.doSave(contentBean);
			ArrayList<ContentBean> listContents = new ArrayList<ContentBean>(contentModelDM.doRetrieveAll(""));
			contentBean = listContents.get(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contentBean;
	}
}
