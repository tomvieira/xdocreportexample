package br.com.tomvieira.relatoriotemplate;

import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.ConverterTypeVia;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tom
 */
public class GeradorRelatorio {

    public static void main(String[] args) {
        try {
            InputStream in
                    = Thread.currentThread().getContextClassLoader().getResourceAsStream("relatorio.odt");
            IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);

            FieldsMetadata metadata = report.createFieldsMetadata();
            //metadata.load( "developers", Developer.class, true );
            metadata.addFieldAsList("developers.nome");
            metadata.addFieldAsList("developers.telefone");
            metadata.addFieldAsList("developers.linguagem");
            report.setFieldsMetadata(metadata);

            // 2) Create context Java model
            IContext context = report.createContext();

            List<Developer> developers = new ArrayList<>();
            developers.add(new Developer("José", "(11)96596-0277", "JAVA"));
            developers.add(new Developer("Maria", "(11)98952-6499", "DELPHI"));
            context.put("developers", developers);
            
            Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.ODFDOM);
            
            OutputStream out = new FileOutputStream(new File("/tmp/saida.pdf"));
            //report.process(context, out);  
            report.convert(context, options, out);
            
            System.out.println("Finalizado");            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
