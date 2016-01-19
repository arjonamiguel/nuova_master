package com.nuova.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.io.ByteSource;
import com.nuova.model.Orden;
import com.nuova.service.OrdenManager;
import com.nuova.utils.ConstantControllers;
import com.nuova.utils.ConstantRedirect;

@Controller
public class ReportController {
    private static final String FILE_TYPE = ".pdf";
    private static final String ORDEN_EMITIDA_REPORT_JRXML = "reports/reporteOrdenEmitida.jrxml";

    @Autowired
    OrdenManager ordenManager;

    @RequestMapping(value = ConstantControllers.REPORT_ORDEN_EMITIDA, method = RequestMethod.GET)
    public String formAddOrden(ModelMap map,
            @PathVariable("ordenId") Integer ordenId,
            HttpServletResponse response) throws IOException {
        Orden orden = ordenManager.findOrdenById(ordenId);
        ByteSource source = ByteSource.wrap(createOrdenReport(orden));
        source.copyTo(response.getOutputStream());
        response.setContentType("application/pdf");
        String fileName = ("Orden" + FILE_TYPE);
        String contentDispositionValue = String.format("attachment; filename=\"%s\"", fileName);
        response.setHeader(com.google.common.net.HttpHeaders.CONTENT_DISPOSITION, contentDispositionValue);
        response.setHeader(com.google.common.net.HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS,
                com.google.common.net.HttpHeaders.CONTENT_DISPOSITION);
        response.getOutputStream().write(source.read());
        response.getOutputStream().flush();
        response.getOutputStream().close();

        return ConstantRedirect.VIEW_MAIN_ORDEN;
    }

    private byte[] createOrdenReport(Orden orden) {
        byte[] pdf = {};
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(ORDEN_EMITIDA_REPORT_JRXML);
            JasperReport report = JasperCompileManager.compileReport(inputStream);

            Map<String, Object> parameters = new HashMap<String, Object>();
            // InputStream imgStream = getClass().getResourceAsStream(KEY_IMG_PATH);
            // Renderable img = RenderableUtil.getInstance(null).getRenderable(imgStream, OnErrorTypeEnum.BLANK);
            // parameters.put("img", img);
            // parameters.put("code", code);

            JasperPrint response = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(response, outputStream);
            pdf = outputStream.toByteArray();

        } catch (JRException ex) {
            throw new RuntimeException(ex);
        }
        return pdf;
    }

}
