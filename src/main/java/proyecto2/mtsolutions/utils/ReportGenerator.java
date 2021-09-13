package proyecto2.mtsolutions.utils;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ReportGenerator {
	@Autowired
	private CommonUtils u;
	@Autowired
	private ServletContext scx;

	public byte[] getByteReports(String tipo, Boolean subReport, List<?> datos, HashMap<String, Object> map,
								 ServletContext ctx, String... fileName) throws Exception {
		Boolean atr1 = false;
		byte[] archivo = null;
		if (u.isNull(tipo)) {
			throw new Exception("Falta definir tipo de archivo a generar (E o P)");
		} else if (u.isNull(fileName)) {
			throw new Exception("Falta definir nombre del archivo");
		} else if (u.isNull(subReport)) {
			throw new Exception("Falta definir si hay o no subreportes");
		} else if (u.isNull(datos)) {
			atr1 = true;
		}
		if (tipo.equalsIgnoreCase("E")) {
			archivo = this.excelGenerator(atr1, datos, map == null ? new HashMap<>() : map, fileName);
		} else if (tipo.equalsIgnoreCase("P")) {
			archivo = this.pdfGenerator(atr1, subReport, datos, map == null ? new HashMap<>() : map, ctx, fileName);
		}
		return archivo;
	}

	private byte[] excelGenerator(Boolean atr1, List<?> datos, HashMap<String, Object> map, String... fileName)
			throws JRException {
		ArrayList<JasperPrint> sheets = new ArrayList<>();
		for (String nombre : fileName) {
			InputStream stream = this.getClass().getResourceAsStream("/".concat(nombre));
			JasperReport report = JasperCompileManager.compileReport(stream);
			JasperPrint jasperPrint;
			if (atr1) {
				JREmptyDataSource dato = new JREmptyDataSource();
				jasperPrint = JasperFillManager.fillReport(report, map, dato);
			} else {
				JRBeanCollectionDataSource dato = new JRBeanCollectionDataSource(datos);
				jasperPrint = JasperFillManager.fillReport(report, map, dato);
			}
			sheets = new ArrayList<>();
			sheets.add(jasperPrint);
		}
		try {
			JRXlsxExporter exporter = new JRXlsxExporter();
			ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();
			exporter.setExporterInput(SimpleExporterInput.getInstance(sheets));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(xlsReport));
			SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
			configuration.setOnePagePerSheet(Boolean.FALSE);
			configuration.setDetectCellType(Boolean.FALSE);
			configuration.setCollapseRowSpan(Boolean.TRUE);
			configuration.setRemoveEmptySpaceBetweenRows(Boolean.TRUE);
			configuration.setRemoveEmptySpaceBetweenColumns(Boolean.FALSE);
			configuration.setCellHidden(Boolean.TRUE);
			exporter.setConfiguration(configuration);
			exporter.exportReport();
			return xlsReport.toByteArray();
		} catch (Exception e) {
			throw e;
		}
	}

	private byte[] pdfGenerator(Boolean atr1, Boolean subreport, List<?> datos, HashMap<String, Object> map,
			ServletContext ctx, String... fileName) throws JRException {
		JasperPrint jasperPrint = null;
		InputStream stream = this.getClass().getResourceAsStream("/".concat(fileName[0]));
		JasperReport report = JasperCompileManager.compileReport(stream);
		if (subreport) {
			map.put("subreport_url", ctx.getRealPath("WEB-I/classes".concat("url")));
		}
		try {
			if (atr1) {
				JREmptyDataSource dato = new JREmptyDataSource();
				jasperPrint = JasperFillManager.fillReport(report, new HashMap<>(), dato);
			} else {
				JRBeanCollectionDataSource dato = new JRBeanCollectionDataSource(datos);
				jasperPrint = JasperFillManager.fillReport(report, map, dato);
			}
			return JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (Exception e) {
			throw e;
		}
	}
}