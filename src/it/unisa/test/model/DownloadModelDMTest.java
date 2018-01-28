package it.unisa.test.model;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import it.unisa.beans.ContentBean;
import it.unisa.model.ContentModelDM;
import it.unisa.model.DownloadModelDM;
import it.unisa.util.InfoDownload;

//test vanno a buon fine (problemi con chiavi esterne)
class DownloadModelDMTest {

	private static DownloadModelDM downloadModelDM;
	private static ContentModelDM contentModelDM;
	private static InfoDownload info;

	@BeforeAll
	public static void setUp() {
		downloadModelDM = new DownloadModelDM();
		contentModelDM = new ContentModelDM();
		info = createInfoDownload();
	}

	@AfterEach
	public void tearDown() {
		try {
			ArrayList<InfoDownload> listDownload = new ArrayList<InfoDownload>(
					downloadModelDM.doRetriveAllByEmail(info.getEmail()));
			for (InfoDownload info : listDownload) {
				downloadModelDM.doDelete(info.getEmail());
			}
			ArrayList<ContentBean> listContent = new ArrayList<ContentBean>(contentModelDM.doRetrieveAll(""));
			for (ContentBean contentBean : listContent) {
				contentModelDM.doDelete(contentBean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testDoSave() {
		try {
			downloadModelDM.doSave(info);
			ArrayList<InfoDownload> listInfoDownload = new ArrayList<InfoDownload>(
					downloadModelDM.doRetriveAllByEmail(info.getEmail()));
			InfoDownload result = listInfoDownload.get(0);
			assertNotNull(result);
			assertEquals(info.getEmail(), result.getEmail());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testDoDelete() {
		try {
			downloadModelDM.doSave(info);
			ArrayList<InfoDownload> listInfoDownload = new ArrayList<InfoDownload>(
					downloadModelDM.doRetriveAllByEmail(info.getEmail()));
			InfoDownload result = listInfoDownload.get(0);
			assertNull(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void testDoRetriveAllByEmail() {
		try {
			downloadModelDM.doSave(info);
			ArrayList<InfoDownload> listInfoDownload = new ArrayList<InfoDownload>(
					downloadModelDM.doRetriveAllByEmail(info.getEmail()));
			InfoDownload result = listInfoDownload.get(0);
			assertNotNull(result);
			assertEquals(info.getEmail(), result.getEmail());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void testDoRetriveByIdandEmail() {
		try {
			downloadModelDM.doSave(info);
			ArrayList<InfoDownload> listInfoDownload = new ArrayList<InfoDownload>(
					downloadModelDM.doRetriveAllByEmail(info.getEmail()));
			InfoDownload result = listInfoDownload.get(0);
			assertNotNull(result);
			assertEquals(info.getContent().getId(), result.getContent().getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static InfoDownload createInfoDownload() {
		InfoDownload infoDownload = new InfoDownload();
		ContentBean contentBean = new ContentBean();
		try {
			contentModelDM.doSave(contentBean);
			ArrayList<ContentBean> listContents = new ArrayList<ContentBean>(contentModelDM.doRetrieveAll(""));
			contentBean = listContents.get(0);
			infoDownload.setContent(contentBean);
			infoDownload.setEmail("domenicomarino42@gmail.com");
			infoDownload.setDate("2020-01-01");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return infoDownload;

	}
}
